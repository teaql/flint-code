//! Stateful remote execution server used behind an SSH stdio bridge.
//!
//! SSH is only the transport. This module owns session persistence, path
//! confinement, policy intersection, direct process spawning, bounded output,
//! and the operation journal used for idempotent reconnects.

use crate::remote_protocol::*;
use glob::{MatchOptions, Pattern};
use serde::{Deserialize, Serialize};
use sha2::{Digest, Sha256};
use std::collections::{BTreeMap, BTreeSet, HashMap};
use std::ffi::{OsStr, OsString};
use std::fs::{File, OpenOptions};
use std::io::{Read, Seek, SeekFrom, Write};
use std::path::{Component, Path, PathBuf};
use std::process::Stdio;
use std::sync::atomic::{AtomicU64, Ordering};
use std::sync::{Arc, Mutex};
use std::time::{Duration, Instant, SystemTime, UNIX_EPOCH};
use tokio::io::{
    AsyncBufRead, AsyncBufReadExt, AsyncRead, AsyncReadExt, AsyncWrite, AsyncWriteExt, BufReader,
};
use tokio::process::Command;
use tokio_util::sync::CancellationToken;
use uuid::Uuid;

const RUNNER_NAME: &str = "klintcode-runner";
const MAX_REQUEST_ID_BYTES: usize = 128;
const MAX_SESSION_ID_BYTES: usize = 128;
const MAX_OPERATION_ID_BYTES: usize = 128;
const MAX_WALK_DEPTH: u32 = 64;
const MAX_SEARCH_QUERY_BYTES: usize = 1024;
const MAX_SEARCH_PREVIEW_CHARS: usize = 512;
const MAX_OUTPUT_CHUNK_BYTES: u64 = 64 * 1024;
const FRAME_METADATA_RESERVE_BYTES: u64 = 64 * 1024;
const OUTPUT_DRAIN_TIMEOUT: Duration = Duration::from_secs(2);
const PROCESS_EXIT_TIMEOUT: Duration = Duration::from_secs(2);

/// Server configuration. The hard policy is owned by the remote host and is
/// always enforced, regardless of values sent by a client.
#[derive(Debug, Clone)]
pub struct RunnerConfig {
    pub root: PathBuf,
    pub hard_policy: HardPolicy,
}

impl RunnerConfig {
    pub fn new(root: impl Into<PathBuf>, hard_policy: HardPolicy) -> Self {
        Self {
            root: root.into(),
            hard_policy,
        }
    }
}

/// One runner bridge connection. Sessions are persisted below the configured
/// root and can be attached by a later bridge process.
pub struct RemoteRunner {
    root: PathBuf,
    hard_policy: HardPolicy,
    server_environment: BTreeMap<String, OsString>,
    hello_complete: bool,
    session: Option<RunnerSession>,
}

impl RemoteRunner {
    pub fn new(config: RunnerConfig) -> Result<Self, ProtocolError> {
        let mut hard_policy = config.hard_policy;
        resolve_program_paths(&mut hard_policy)?;
        validate_hard_policy(&hard_policy)?;
        let server_environment = build_server_environment(&hard_policy)?;
        let root = prepare_runner_root(&config.root)?;
        Ok(Self {
            root,
            hard_policy,
            server_environment,
            hello_complete: false,
            session: None,
        })
    }

    /// Handle one decoded frame. Useful for embedded transports and tests.
    pub async fn handle_frame(&mut self, frame: RequestFrame) -> ResponseFrame {
        let request_id = frame.request_id.clone();
        if request_id.is_empty() || request_id.len() > MAX_REQUEST_ID_BYTES {
            return ResponseFrame::error(
                "",
                ProtocolError::new(
                    ErrorCode::InvalidRequest,
                    "request_id must contain 1..=128 bytes",
                ),
            );
        }
        if frame.protocol_version != PROTOCOL_VERSION {
            return ResponseFrame::error(
                request_id,
                ProtocolError::new(
                    ErrorCode::ProtocolMismatch,
                    format!(
                        "unsupported protocol version {}; runner requires {}",
                        frame.protocol_version, PROTOCOL_VERSION
                    ),
                ),
            );
        }
        if !self.hello_complete && !matches!(&frame.request, RunnerRequest::Hello(_)) {
            return ResponseFrame::error(
                request_id,
                ProtocolError::new(ErrorCode::HelloRequired, "hello must be the first request"),
            );
        }

        let response = match self.handle_request(frame.request).await {
            Ok(response) => ResponseFrame::success(request_id, response),
            Err(error) => ResponseFrame::error(request_id, error),
        };
        bound_response_frame(response)
    }

    async fn handle_request(
        &mut self,
        request: RunnerRequest,
    ) -> Result<RunnerResponse, ProtocolError> {
        match request {
            RunnerRequest::Hello(request) => {
                if self.hello_complete {
                    return Err(ProtocolError::new(
                        ErrorCode::InvalidRequest,
                        "hello has already completed for this connection",
                    ));
                }
                if request.client_name.trim().is_empty() {
                    return Err(ProtocolError::new(
                        ErrorCode::InvalidRequest,
                        "client_name cannot be empty",
                    ));
                }
                self.hello_complete = true;
                Ok(RunnerResponse::Hello(HelloResponse {
                    runner_name: RUNNER_NAME.into(),
                    runner_version: env!("CARGO_PKG_VERSION").into(),
                    protocol_version: PROTOCOL_VERSION,
                    capabilities: vec![
                        "session.attach_or_create".into(),
                        "session.detach".into(),
                        "session.environment_refs".into(),
                        "session.sqlite_private".into(),
                        "fs.stat".into(),
                        "fs.read_range".into(),
                        "fs.list".into(),
                        "fs.walk".into(),
                        "fs.search_literal".into(),
                        "fs.snapshot".into(),
                        "fs.write_atomic".into(),
                        "fs.write_cas".into(),
                        "artifact.read_chunk".into(),
                        "exec.structured".into(),
                        "exec.idempotent".into(),
                        "exec.cancel".into(),
                        "exec.output_cursor".into(),
                        "policy.monotonic".into(),
                    ],
                }))
            }
            RunnerRequest::SessionAttachOrCreate(request) => {
                if self.session.is_some() {
                    return Err(ProtocolError::new(
                        ErrorCode::SessionBusy,
                        "detach the current session before attaching another session",
                    ));
                }
                let (session, info) = RunnerSession::attach_or_create(
                    &self.root,
                    request.session_id.as_deref(),
                    request.require_existing,
                    &self.hard_policy,
                    &self.server_environment,
                    &request.policy,
                    &request.environment_refs,
                )?;
                self.session = Some(session);
                Ok(RunnerResponse::Session(info))
            }
            RunnerRequest::SessionDetach => {
                let session = self.session.as_mut().ok_or_else(no_session_error)?;
                if session.has_running_operations()? {
                    return Err(retryable_session_busy(
                        "cannot detach while an operation is running; cancel it first",
                    ));
                }
                let session = self.session.take().ok_or_else(no_session_error)?;
                Ok(RunnerResponse::SessionDetached(SessionDetachedResponse {
                    session_id: session.id,
                }))
            }
            RunnerRequest::FsReadRange(request) => Ok(RunnerResponse::ReadRange(
                self.session_mut()?.read_range(request)?,
            )),
            RunnerRequest::FsStat(request) => {
                Ok(RunnerResponse::Stat(self.session_mut()?.stat(request)?))
            }
            RunnerRequest::FsList(request) => {
                Ok(RunnerResponse::List(self.session_mut()?.list(request)?))
            }
            RunnerRequest::FsWalk(request) => {
                Ok(RunnerResponse::Walk(self.session_mut()?.walk(request)?))
            }
            RunnerRequest::FsSearch(request) => {
                Ok(RunnerResponse::Search(self.session_mut()?.search(request)?))
            }
            RunnerRequest::FsSnapshot(request) => Ok(RunnerResponse::Snapshot(
                self.session_mut()?.snapshot(request)?,
            )),
            RunnerRequest::FsWriteAtomic(request) => Ok(RunnerResponse::WriteAtomic(
                self.session_mut()?.write_atomic(request)?,
            )),
            RunnerRequest::FsWriteCas(request) => Ok(RunnerResponse::WriteCas(
                self.session_mut()?.write_cas(request)?,
            )),
            RunnerRequest::ArtifactReadChunk(request) => Ok(RunnerResponse::ArtifactChunk(
                self.session_mut()?.read_artifact_chunk(request)?,
            )),
            RunnerRequest::ExecStart(request) => {
                Ok(RunnerResponse::Exec(self.session_mut()?.start(request)?))
            }
            RunnerRequest::ExecStatus(request) => Ok(RunnerResponse::OperationStatus(
                self.session_mut()?
                    .operation_status(&request.operation_id)?,
            )),
            RunnerRequest::ExecOutput(request) => Ok(RunnerResponse::OperationOutput(
                self.session_mut()?.operation_output(request)?,
            )),
            RunnerRequest::ExecCancel(request) => Ok(RunnerResponse::ExecCancelled(
                self.session_mut()?.cancel(&request.operation_id).await?,
            )),
        }
    }

    fn session_mut(&mut self) -> Result<&mut RunnerSession, ProtocolError> {
        self.session.as_mut().ok_or_else(no_session_error)
    }
}

fn no_session_error() -> ProtocolError {
    ProtocolError::new(
        ErrorCode::NoSession,
        "attach or create a session before accessing the workspace",
    )
}

fn retryable_session_busy(message: impl Into<String>) -> ProtocolError {
    ProtocolError {
        code: ErrorCode::SessionBusy,
        message: message.into(),
        retryable: true,
    }
}

/// Serve the protocol as NDJSON over asynchronous streams. Normally these are
/// stdin/stdout of an SSH forced command.
pub async fn serve_ndjson<R, W>(
    reader: R,
    mut writer: W,
    mut runner: RemoteRunner,
) -> std::io::Result<()>
where
    R: AsyncRead + Unpin,
    W: AsyncWrite + Unpin,
{
    let mut reader = BufReader::new(reader);
    loop {
        let response = match read_bounded_line(&mut reader, MAX_FRAME_BYTES).await? {
            BoundedLine::Eof => return Ok(()),
            BoundedLine::TooLarge => ResponseFrame::error(
                "",
                ProtocolError::new(
                    ErrorCode::TooLarge,
                    format!("request frame exceeds {MAX_FRAME_BYTES} bytes"),
                ),
            ),
            BoundedLine::Line(mut bytes) => {
                if bytes.last() == Some(&b'\n') {
                    bytes.pop();
                    if bytes.last() == Some(&b'\r') {
                        bytes.pop();
                    }
                }
                if bytes.is_empty() {
                    continue;
                }
                match serde_json::from_slice::<RequestFrame>(&bytes) {
                    Ok(frame) => runner.handle_frame(frame).await,
                    Err(error) => ResponseFrame::error(
                        "",
                        ProtocolError::new(ErrorCode::InvalidJson, error.to_string()),
                    ),
                }
            }
        };
        write_frame(&mut writer, &response).await?;
    }
}

async fn write_frame<W: AsyncWrite + Unpin>(
    writer: &mut W,
    response: &ResponseFrame,
) -> std::io::Result<()> {
    let response = bound_response_frame(response.clone());
    let bytes = serde_json::to_vec(&response).map_err(std::io::Error::other)?;
    writer.write_all(&bytes).await?;
    writer.write_all(b"\n").await?;
    writer.flush().await
}

fn bound_response_frame(response: ResponseFrame) -> ResponseFrame {
    match serde_json::to_vec(&response) {
        Ok(bytes) if bytes.len() <= MAX_FRAME_BYTES => response,
        Ok(_) => ResponseFrame::error(
            response.request_id,
            ProtocolError::new(
                ErrorCode::TooLarge,
                format!("response frame exceeds {MAX_FRAME_BYTES} bytes"),
            ),
        ),
        Err(_) => ResponseFrame::error(
            response.request_id,
            ProtocolError::new(ErrorCode::Internal, "failed to serialize response frame"),
        ),
    }
}

enum BoundedLine {
    Line(Vec<u8>),
    TooLarge,
    Eof,
}

async fn read_bounded_line<R: AsyncBufRead + Unpin>(
    reader: &mut R,
    limit: usize,
) -> std::io::Result<BoundedLine> {
    let mut result = Vec::new();
    let mut too_large = false;
    let mut saw_bytes = false;
    loop {
        let available = reader.fill_buf().await?;
        if available.is_empty() {
            return if !saw_bytes {
                Ok(BoundedLine::Eof)
            } else if too_large {
                Ok(BoundedLine::TooLarge)
            } else {
                Ok(BoundedLine::Line(result))
            };
        }
        let consumed = available
            .iter()
            .position(|byte| *byte == b'\n')
            .map_or(available.len(), |index| index + 1);
        saw_bytes = true;
        if !too_large {
            let remaining = limit.saturating_sub(result.len());
            let copied = remaining.min(consumed);
            result.extend_from_slice(&available[..copied]);
            if copied < consumed
                || (result.len() == limit && available[..consumed].last() != Some(&b'\n'))
            {
                too_large = true;
            }
        }
        let ended = available[..consumed].last() == Some(&b'\n');
        reader.consume(consumed);
        if ended {
            return if too_large {
                Ok(BoundedLine::TooLarge)
            } else {
                Ok(BoundedLine::Line(result))
            };
        }
    }
}

#[derive(Debug, Clone, Serialize, Deserialize)]
struct SessionMetadata {
    protocol_version: u32,
    session_id: String,
    effective_policy: EffectivePolicy,
    policy_digest: String,
    #[serde(default)]
    environment_refs: BTreeMap<String, EnvironmentRef>,
}

#[derive(Clone)]
struct OperationStore {
    session_id: String,
    session_directory: PathBuf,
    operations: PathBuf,
    journal_lock: Arc<Mutex<()>>,
}

struct ActiveOperation {
    cancel: CancellationToken,
    completed: CancellationToken,
    output: Option<Arc<Mutex<LiveOperationOutput>>>,
}

#[derive(Default)]
struct LiveOperationOutput {
    stdout: Vec<u8>,
    stderr: Vec<u8>,
    stdout_truncated: bool,
    stderr_truncated: bool,
}

/// An OS-owned lease for one attached bridge. Closing the descriptor releases
/// the lease even when the runner process crashes.
struct SessionBridgeLock {
    _file: File,
}

struct RunnerSession {
    id: String,
    workspace: PathBuf,
    metadata: SessionMetadata,
    server_environment: BTreeMap<String, OsString>,
    store: OperationStore,
    active: HashMap<String, ActiveOperation>,
    filesystem_lock: Mutex<()>,
    _bridge_lock: SessionBridgeLock,
}

struct WorkspaceWalkEntry {
    relative: String,
    absolute: PathBuf,
    kind: FileKind,
    bytes: u64,
}

#[cfg(unix)]
impl SessionBridgeLock {
    fn acquire(session_directory: &Path) -> Result<Self, ProtocolError> {
        use std::os::fd::AsRawFd;
        use std::os::unix::fs::OpenOptionsExt;

        let path = session_directory.join("bridge.lock");
        reject_symlink_if_present(&path)?;
        let file = OpenOptions::new()
            .read(true)
            .write(true)
            .create(true)
            .mode(0o600)
            .custom_flags(libc::O_CLOEXEC | libc::O_NOFOLLOW)
            .open(&path)
            .map_err(|error| io_error("open session bridge lock", error))?;
        let result = unsafe { libc::flock(file.as_raw_fd(), libc::LOCK_EX | libc::LOCK_NB) };
        if result != 0 {
            let error = std::io::Error::last_os_error();
            let raw_error = error.raw_os_error();
            if raw_error == Some(libc::EWOULDBLOCK) || raw_error == Some(libc::EAGAIN) {
                return Err(retryable_session_busy(
                    "session already has an attached bridge",
                ));
            }
            return Err(io_error("lock session bridge", error));
        }
        Ok(Self { _file: file })
    }
}

#[cfg(not(unix))]
impl SessionBridgeLock {
    fn acquire(_session_directory: &Path) -> Result<Self, ProtocolError> {
        Err(ProtocolError::new(
            ErrorCode::SessionError,
            "exclusive session bridge locking is unsupported on this platform",
        ))
    }
}

impl RunnerSession {
    fn attach_or_create(
        root: &Path,
        requested_id: Option<&str>,
        require_existing: bool,
        hard_policy: &HardPolicy,
        server_environment: &BTreeMap<String, OsString>,
        client_policy: &ClientPolicy,
        requested_environment_refs: &BTreeMap<String, EnvironmentRef>,
    ) -> Result<(Self, SessionInfo), ProtocolError> {
        validate_client_policy(client_policy)?;
        if require_existing && requested_id.is_none() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidRequest,
                "require_existing needs an explicit session_id",
            ));
        }
        let sessions_root = root.join("sessions");
        let (id, directory, created) = if let Some(id) = requested_id {
            validate_identifier(id, MAX_SESSION_ID_BYTES, "session_id")?;
            let directory = sessions_root.join(id);
            let created = create_session_directory(&directory, require_existing)?;
            (id.to_owned(), directory, created)
        } else {
            loop {
                let id = Uuid::new_v4().to_string();
                let directory = sessions_root.join(&id);
                match std::fs::create_dir(&directory) {
                    Ok(()) => break (id, directory, true),
                    Err(error) if error.kind() == std::io::ErrorKind::AlreadyExists => continue,
                    Err(error) => return Err(io_error("create session directory", error)),
                }
            }
        };
        set_private_directory_permissions(&directory)?;
        let bridge_lock = SessionBridgeLock::acquire(&directory)?;

        let workspace = directory.join("workspace");
        let operations = directory.join("operations");
        let metadata_path = directory.join("session.json");
        let mut metadata = if created {
            std::fs::create_dir(&workspace)
                .map_err(|error| io_error("create session workspace", error))?;
            std::fs::create_dir(&operations)
                .map_err(|error| io_error("create operations directory", error))?;
            set_private_directory_permissions(&workspace)?;
            set_private_directory_permissions(&operations)?;
            let effective_policy =
                EffectivePolicy::from_hard_policy(hard_policy).narrow(client_policy);
            validate_environment_refs(requested_environment_refs, &effective_policy)?;
            let metadata = SessionMetadata {
                protocol_version: PROTOCOL_VERSION,
                session_id: id.clone(),
                policy_digest: policy_digest(&effective_policy)?,
                effective_policy,
                environment_refs: requested_environment_refs.clone(),
            };
            atomic_json_write(&metadata_path, &metadata)?;
            metadata
        } else {
            reject_symlink(&metadata_path)?;
            let bytes = std::fs::read(&metadata_path)
                .map_err(|error| io_error("read session metadata", error))?;
            let mut metadata: SessionMetadata =
                serde_json::from_slice(&bytes).map_err(|error| {
                    ProtocolError::new(
                        ErrorCode::SessionError,
                        format!("invalid session metadata: {error}"),
                    )
                })?;
            if metadata.protocol_version != PROTOCOL_VERSION || metadata.session_id != id {
                return Err(ProtocolError::new(
                    ErrorCode::SessionError,
                    "session metadata does not match requested session",
                ));
            }
            if !workspace.is_dir() || !operations.is_dir() {
                return Err(ProtocolError::new(
                    ErrorCode::SessionError,
                    "session workspace or operations directory is missing",
                ));
            }
            reject_symlink(&workspace)?;
            reject_symlink(&operations)?;
            metadata.effective_policy = metadata
                .effective_policy
                .narrow_with_hard_policy(hard_policy)
                .narrow(client_policy);
            if !requested_environment_refs.is_empty()
                && requested_environment_refs != &metadata.environment_refs
            {
                return Err(ProtocolError::new(
                    ErrorCode::SessionError,
                    "environment references cannot change while reattaching a session",
                ));
            }
            validate_environment_refs(&metadata.environment_refs, &metadata.effective_policy)?;
            metadata.policy_digest = policy_digest(&metadata.effective_policy)?;
            atomic_json_write(&metadata_path, &metadata)?;
            metadata
        };
        metadata.policy_digest = policy_digest(&metadata.effective_policy)?;
        let store = OperationStore {
            session_id: id.clone(),
            session_directory: directory,
            operations,
            journal_lock: Arc::new(Mutex::new(())),
        };
        store.mark_stale_operations_interrupted()?;
        let workspace = std::fs::canonicalize(&workspace)
            .map_err(|error| io_error("canonicalize session workspace", error))?;
        let info = SessionInfo {
            session_id: id.clone(),
            workspace: ".".into(),
            created,
            policy_digest: metadata.policy_digest.clone(),
            effective_policy: metadata.effective_policy.clone(),
            environment_refs: metadata.environment_refs.keys().cloned().collect(),
        };
        Ok((
            Self {
                id,
                workspace,
                metadata,
                server_environment: server_environment.clone(),
                store,
                active: HashMap::new(),
                filesystem_lock: Mutex::new(()),
                _bridge_lock: bridge_lock,
            },
            info,
        ))
    }

    fn stat(&self, request: StatRequest) -> Result<StatResponse, ProtocolError> {
        let (relative, relative_text) = normalize_relative(&request.path, true)?;
        self.require_readable(&relative_text)?;
        let Some(path) = self.resolve_optional(&relative)? else {
            return Ok(StatResponse {
                path: relative_text,
                exists: false,
                kind: None,
                bytes: None,
                sha256: None,
            });
        };
        let metadata = path
            .symlink_metadata()
            .map_err(|error| map_path_io(&path, error))?;
        if metadata.file_type().is_symlink() {
            return Err(symlink_error(&path));
        }
        let kind = file_kind(&metadata);
        let sha256 = if request.include_sha256 && kind == FileKind::File {
            Some(hash_regular_file(&path)?)
        } else {
            None
        };
        Ok(StatResponse {
            path: relative_text,
            exists: true,
            kind: Some(kind),
            bytes: Some(metadata.len()),
            sha256,
        })
    }

    fn read_range(&self, request: ReadRangeRequest) -> Result<ReadRangeResponse, ProtocolError> {
        if request.length > self.metadata.effective_policy.max_read_bytes {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!(
                    "requested {} bytes; session limit is {}",
                    request.length, self.metadata.effective_policy.max_read_bytes
                ),
            ));
        }
        let (relative, relative_text) = normalize_relative(&request.path, false)?;
        self.require_readable(&relative_text)?;
        let path = self.resolve_existing(&relative)?;
        let mut file = open_regular_file(&path)?;
        let metadata = file.metadata().map_err(|error| map_path_io(&path, error))?;
        if !metadata.is_file() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "read_range target must be a regular file",
            ));
        }
        let bytes = read_utf8_range(&mut file, request.offset, request.length, metadata.len())?;
        let content = String::from_utf8(bytes).map_err(|_| {
            ProtocolError::new(ErrorCode::Internal, "validated UTF-8 range became invalid")
        })?;
        let read_bytes = content.len() as u64;
        Ok(ReadRangeResponse {
            path: relative_text,
            offset: request.offset,
            bytes: read_bytes,
            eof: request.offset.saturating_add(read_bytes) >= metadata.len(),
            content,
        })
    }

    fn read_artifact_chunk(
        &self,
        request: ArtifactReadChunkRequest,
    ) -> Result<ArtifactReadChunkResponse, ProtocolError> {
        if request.length > self.metadata.effective_policy.max_read_bytes {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!(
                    "requested {} bytes; session limit is {}",
                    request.length, self.metadata.effective_policy.max_read_bytes
                ),
            ));
        }
        let (relative, relative_text) = normalize_relative(&request.path, false)?;
        self.require_readable(&relative_text)?;
        let path = self.resolve_existing(&relative)?;
        let mut file = open_regular_file(&path)?;
        let total_bytes = file
            .metadata()
            .map_err(|error| map_path_io(&path, error))?
            .len();
        file.seek(SeekFrom::Start(request.offset))
            .map_err(|error| io_error("seek artifact", error))?;
        let capacity = usize::try_from(request.length.min(64 * 1024)).unwrap_or(64 * 1024);
        let mut bytes = Vec::with_capacity(capacity);
        file.take(request.length)
            .read_to_end(&mut bytes)
            .map_err(|error| io_error("read artifact chunk", error))?;
        let read_bytes = bytes.len() as u64;
        Ok(ArtifactReadChunkResponse {
            path: relative_text,
            offset: request.offset,
            bytes: read_bytes,
            total_bytes,
            eof: request.offset.saturating_add(read_bytes) >= total_bytes,
            data_hex: hex_bytes(&bytes),
            chunk_sha256: sha256_bytes(&bytes),
        })
    }

    fn list(&self, request: ListRequest) -> Result<ListResponse, ProtocolError> {
        let (relative, relative_text) = normalize_relative(&request.path, true)?;
        self.require_readable(&relative_text)?;
        let path = self.resolve_existing(&relative)?;
        if !path.is_dir() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "list target must be a directory",
            ));
        }
        let limit = request
            .max_entries
            .unwrap_or(self.metadata.effective_policy.max_list_entries)
            .min(self.metadata.effective_policy.max_list_entries) as usize;
        let mut entries = Vec::new();
        let mut truncated = false;
        let read_dir = std::fs::read_dir(&path).map_err(|error| map_path_io(&path, error))?;
        for entry in read_dir {
            let entry = entry.map_err(|error| io_error("read directory entry", error))?;
            let name = entry.file_name().to_string_lossy().into_owned();
            let child_relative = if relative_text == "." {
                name.clone()
            } else {
                format!("{relative_text}/{name}")
            };
            if !self.is_readable(&child_relative) {
                continue;
            }
            if entries.len() == limit {
                truncated = true;
                break;
            }
            let metadata = entry
                .path()
                .symlink_metadata()
                .map_err(|error| io_error("inspect directory entry", error))?;
            let file_type = metadata.file_type();
            let kind = if file_type.is_symlink() {
                FileKind::Symlink
            } else if file_type.is_file() {
                FileKind::File
            } else if file_type.is_dir() {
                FileKind::Directory
            } else {
                FileKind::Other
            };
            entries.push(DirectoryEntry {
                name,
                kind,
                bytes: metadata.len(),
            });
        }
        entries.sort_by(|left, right| left.name.cmp(&right.name));
        Ok(ListResponse {
            path: relative_text,
            entries,
            truncated,
        })
    }

    fn walk(&self, request: WalkRequest) -> Result<WalkResponse, ProtocolError> {
        let (relative, relative_text) = normalize_relative(&request.path, true)?;
        self.require_readable(&relative_text)?;
        let path = self.resolve_existing(&relative)?;
        if !path.is_dir() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "walk target must be a directory",
            ));
        }
        let limit = self.entry_limit(request.max_entries);
        let max_depth = request
            .max_depth
            .unwrap_or(MAX_WALK_DEPTH)
            .min(MAX_WALK_DEPTH);
        let excluded_paths = request
            .excluded_paths
            .iter()
            .map(|path| normalize_relative(path, false).map(|(_, text)| text))
            .collect::<Result<Vec<_>, _>>()?;
        let (entries, truncated) =
            self.collect_walk(&path, &relative_text, limit, max_depth, &excluded_paths)?;
        Ok(WalkResponse {
            path: relative_text,
            entries: entries
                .into_iter()
                .map(|entry| WalkEntry {
                    path: entry.relative,
                    kind: entry.kind,
                    bytes: entry.bytes,
                })
                .collect(),
            truncated,
        })
    }

    fn search(&self, request: SearchRequest) -> Result<SearchResponse, ProtocolError> {
        if request.query.is_empty()
            || request.query.len() > MAX_SEARCH_QUERY_BYTES
            || request.query.contains(['\0', '\r', '\n'])
        {
            return Err(ProtocolError::new(
                ErrorCode::InvalidRequest,
                format!(
                    "search query must contain 1..={MAX_SEARCH_QUERY_BYTES} bytes and no line breaks"
                ),
            ));
        }
        let (relative, relative_text) = normalize_relative(&request.path, true)?;
        let excluded_paths = request
            .excluded_paths
            .iter()
            .map(|path| normalize_relative(path, false).map(|(_, text)| text))
            .collect::<Result<Vec<_>, _>>()?;
        self.require_readable(&relative_text)?;
        let path = self.resolve_existing(&relative)?;
        if !path.is_dir() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "search target must be a directory",
            ));
        }
        let match_limit = self.entry_limit(request.max_matches);
        let max_file_bytes = request
            .max_file_bytes
            .unwrap_or(self.metadata.effective_policy.max_read_bytes)
            .min(self.metadata.effective_policy.max_read_bytes);
        let walk_limit = self.metadata.effective_policy.max_list_entries as usize;
        let (entries, walk_truncated) =
            self.collect_walk(&path, &relative_text, walk_limit, MAX_WALK_DEPTH, &[])?;
        let mut matches = Vec::new();
        let mut files_scanned = 0_u32;
        let mut files_skipped = 0_u32;
        let mut bytes_scanned = 0_u64;
        let mut truncated = walk_truncated;
        for entry in entries {
            if excluded_paths.iter().any(|excluded| {
                entry.relative == *excluded
                    || entry
                        .relative
                        .strip_prefix(excluded)
                        .is_some_and(|suffix| suffix.starts_with('/'))
            }) {
                continue;
            }
            if entry.kind != FileKind::File {
                continue;
            }
            if entry.bytes > max_file_bytes {
                files_skipped = files_skipped.saturating_add(1);
                continue;
            }
            let mut file = open_regular_file(&entry.absolute)?;
            let capacity = usize::try_from(entry.bytes).map_err(|_| {
                ProtocolError::new(ErrorCode::TooLarge, "search file does not fit in memory")
            })?;
            let mut bytes = Vec::with_capacity(capacity);
            file.read_to_end(&mut bytes)
                .map_err(|error| io_error("read search file", error))?;
            let Ok(text) = String::from_utf8(bytes) else {
                files_skipped = files_skipped.saturating_add(1);
                continue;
            };
            files_scanned = files_scanned.saturating_add(1);
            bytes_scanned = bytes_scanned.saturating_add(text.len() as u64);
            for (line_index, line) in text.lines().enumerate() {
                let Some(column) = line.find(&request.query) else {
                    continue;
                };
                if matches.len() == match_limit {
                    truncated = true;
                    break;
                }
                matches.push(SearchMatch {
                    path: entry.relative.clone(),
                    line: line_index as u64 + 1,
                    column: column as u64 + 1,
                    preview: line.chars().take(MAX_SEARCH_PREVIEW_CHARS).collect(),
                });
            }
            if matches.len() == match_limit && truncated {
                break;
            }
        }
        Ok(SearchResponse {
            path: relative_text,
            matches,
            files_scanned,
            files_skipped,
            bytes_scanned,
            truncated,
        })
    }

    fn snapshot(&self, request: SnapshotRequest) -> Result<SnapshotResponse, ProtocolError> {
        let (relative, relative_text) = normalize_relative(&request.path, true)?;
        self.require_readable(&relative_text)?;
        let path = self.resolve_existing(&relative)?;
        if !path.is_dir() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "snapshot target must be a directory",
            ));
        }
        let limit = self.entry_limit(request.max_entries);
        let (walked, truncated) =
            self.collect_walk(&path, &relative_text, limit, MAX_WALK_DEPTH, &[])?;
        let mut total_file_bytes = 0_u64;
        let mut entries = Vec::with_capacity(walked.len());
        for entry in walked {
            let (bytes, sha256) = if entry.kind == FileKind::File {
                total_file_bytes = total_file_bytes.saturating_add(entry.bytes);
                (entry.bytes, Some(hash_regular_file(&entry.absolute)?))
            } else {
                (0, None)
            };
            entries.push(SnapshotEntry {
                path: entry.relative,
                kind: entry.kind,
                bytes,
                sha256,
            });
        }
        let tree_sha256 = snapshot_digest(&entries);
        Ok(SnapshotResponse {
            path: relative_text,
            entries,
            tree_sha256,
            total_file_bytes,
            truncated,
        })
    }

    fn write_atomic(
        &self,
        request: WriteAtomicRequest,
    ) -> Result<WriteAtomicResponse, ProtocolError> {
        let _filesystem_guard = self
            .filesystem_lock
            .lock()
            .map_err(|_| ProtocolError::new(ErrorCode::Internal, "filesystem lock is poisoned"))?;
        let bytes = request.content.as_bytes();
        if bytes.len() as u64 > self.metadata.effective_policy.max_write_bytes {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!(
                    "write contains {} bytes; session limit is {}",
                    bytes.len(),
                    self.metadata.effective_policy.max_write_bytes
                ),
            ));
        }
        let (relative, relative_text) = normalize_relative(&request.path, false)?;
        self.require_writable(&relative_text)?;
        let parent_relative = relative.parent().unwrap_or_else(|| Path::new(""));
        let parent = self.ensure_directory(parent_relative, request.create_parents)?;
        let file_name = relative.file_name().ok_or_else(|| {
            ProtocolError::new(ErrorCode::InvalidPath, "write target has no file name")
        })?;
        let target = parent.join(file_name);
        if let Ok(metadata) = target.symlink_metadata() {
            if metadata.file_type().is_symlink() {
                return Err(symlink_error(&target));
            }
            if metadata.is_dir() {
                return Err(ProtocolError::new(
                    ErrorCode::InvalidPath,
                    "write target is a directory",
                ));
            }
        }

        let temp_path = parent.join(format!(".klintcode-write-{}.tmp", Uuid::new_v4()));
        let write_result = (|| -> Result<(), ProtocolError> {
            let mut temp = OpenOptions::new()
                .write(true)
                .create_new(true)
                .open(&temp_path)
                .map_err(|error| io_error("create temporary file", error))?;
            temp.write_all(bytes)
                .map_err(|error| io_error("write temporary file", error))?;
            temp.sync_all()
                .map_err(|error| io_error("sync temporary file", error))?;
            std::fs::rename(&temp_path, &target)
                .map_err(|error| io_error("atomically replace file", error))?;
            sync_directory(&parent)?;
            Ok(())
        })();
        if write_result.is_err() {
            let _ = std::fs::remove_file(&temp_path);
        }
        write_result?;
        Ok(WriteAtomicResponse {
            path: relative_text,
            bytes: bytes.len() as u64,
        })
    }

    fn write_cas(&self, request: WriteCasRequest) -> Result<WriteCasResponse, ProtocolError> {
        let _filesystem_guard = self
            .filesystem_lock
            .lock()
            .map_err(|_| ProtocolError::new(ErrorCode::Internal, "filesystem lock is poisoned"))?;
        let bytes = request.content.as_bytes();
        if bytes.len() as u64 > self.metadata.effective_policy.max_write_bytes {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!(
                    "write contains {} bytes; session limit is {}",
                    bytes.len(),
                    self.metadata.effective_policy.max_write_bytes
                ),
            ));
        }
        let (relative, relative_text) = normalize_relative(&request.path, false)?;
        self.require_writable(&relative_text)?;
        let expected_sha256 = match &request.expected {
            ExpectedFileState::Missing => None,
            ExpectedFileState::Sha256 { value } => {
                self.require_readable(&relative_text)?;
                Some(validate_sha256(value)?)
            }
        };
        let parent_relative = relative.parent().unwrap_or_else(|| Path::new(""));
        let parent = self.ensure_directory(parent_relative, request.create_parents)?;
        let file_name = relative.file_name().ok_or_else(|| {
            ProtocolError::new(ErrorCode::InvalidPath, "write target has no file name")
        })?;
        let target = parent.join(file_name);
        reject_symlink_if_present(&target)?;

        let temp_path = parent.join(format!(".klintcode-cas-{}.tmp", Uuid::new_v4()));
        let new_sha256 = sha256_bytes(bytes);
        let result = (|| -> Result<(), ProtocolError> {
            let mut temp = OpenOptions::new()
                .write(true)
                .create_new(true)
                .open(&temp_path)
                .map_err(|error| io_error("create temporary CAS file", error))?;
            temp.write_all(bytes)
                .map_err(|error| io_error("write temporary CAS file", error))?;
            temp.sync_all()
                .map_err(|error| io_error("sync temporary CAS file", error))?;
            drop(temp);

            match expected_sha256 {
                None => match std::fs::hard_link(&temp_path, &target) {
                    Ok(()) => {
                        std::fs::remove_file(&temp_path)
                            .map_err(|error| io_error("remove linked CAS temporary file", error))?;
                    }
                    Err(error) if error.kind() == std::io::ErrorKind::AlreadyExists => {
                        return Err(ProtocolError::new(
                            ErrorCode::CasMismatch,
                            "CAS expected the target to be missing",
                        ));
                    }
                    Err(error) => return Err(io_error("publish new CAS file", error)),
                },
                Some(expected) => {
                    let current = match hash_regular_file(&target) {
                        Ok(digest) => digest,
                        Err(error) if error.code == ErrorCode::NotFound => {
                            return Err(ProtocolError::new(
                                ErrorCode::CasMismatch,
                                "CAS expected an existing file",
                            ));
                        }
                        Err(error) => return Err(error),
                    };
                    if current != expected {
                        return Err(ProtocolError::new(
                            ErrorCode::CasMismatch,
                            "CAS expected hash does not match the current file",
                        ));
                    }
                    std::fs::rename(&temp_path, &target)
                        .map_err(|error| io_error("atomically publish CAS file", error))?;
                }
            }
            sync_directory(&parent)
        })();
        if result.is_err() {
            let _ = std::fs::remove_file(&temp_path);
        }
        result?;
        Ok(WriteCasResponse {
            path: relative_text,
            bytes: bytes.len() as u64,
            sha256: new_sha256,
        })
    }

    /// Start an operation and return its persisted Running record immediately.
    /// Completion is observed through ExecStatus or ExecCancel.
    fn start(&mut self, request: ExecRequest) -> Result<OperationResponse, ProtocolError> {
        validate_identifier(
            &request.operation_id,
            MAX_OPERATION_ID_BYTES,
            "operation_id",
        )?;
        let request_digest = digest_exec_request(&request)?;
        if let Some(existing) = self.store.load_if_exists(&request.operation_id)? {
            if !request.env.is_empty() {
                return Err(ProtocolError::new(
                    ErrorCode::OperationConflict,
                    "an operation with explicit environment values cannot be replayed; use exec_status",
                ));
            }
            if existing.request_digest != request_digest {
                return Err(ProtocolError::new(
                    ErrorCode::OperationConflict,
                    "operation_id was already used for a different request",
                ));
            }
            if existing.state != OperationState::Running {
                self.active.remove(&request.operation_id);
            }
            return Ok(OperationResponse {
                replayed: true,
                operation: existing,
            });
        }

        // Validate before recording Running. Environment values are retained
        // only in the spawned task and never written to state or the journal.
        let prepared = self.prepare_exec(&request)?;
        let operation = OperationRecord {
            operation_id: request.operation_id.clone(),
            request_digest,
            state: OperationState::Running,
            result: None,
            message: None,
        };
        self.store.persist(&operation)?;
        self.store.append_journal(&operation)?;

        let cancel = CancellationToken::new();
        let completed = CancellationToken::new();
        let live_output = request
            .stream_output
            .then(|| Arc::new(Mutex::new(LiveOperationOutput::default())));
        self.active.insert(
            request.operation_id.clone(),
            ActiveOperation {
                cancel: cancel.clone(),
                completed: completed.clone(),
                output: live_output.clone(),
            },
        );
        let store = self.store.clone();
        let operation_id = request.operation_id;
        let request_digest = operation.request_digest.clone();
        tokio::spawn(async move {
            let outcome = run_prepared_process(prepared, cancel, live_output).await;
            let record = OperationRecord {
                operation_id,
                request_digest,
                state: outcome.state,
                result: outcome.result,
                message: outcome.message,
            };
            // A journal I/O failure must not leave waiters hanging. Persist is
            // attempted first, and completion notification is unconditional.
            let _ = store.persist(&record);
            let _ = store.append_journal(&record);
            completed.cancel();
        });
        Ok(OperationResponse {
            replayed: false,
            operation,
        })
    }

    fn has_running_operations(&mut self) -> Result<bool, ProtocolError> {
        let operation_ids: Vec<String> = self.active.keys().cloned().collect();
        let mut running = false;
        for operation_id in operation_ids {
            match self.store.load_if_exists(&operation_id)? {
                Some(operation) if operation.state == OperationState::Running => running = true,
                Some(_) | None => {
                    self.active.remove(&operation_id);
                }
            }
        }
        Ok(running)
    }

    fn operation_status(&mut self, operation_id: &str) -> Result<OperationRecord, ProtocolError> {
        validate_identifier(operation_id, MAX_OPERATION_ID_BYTES, "operation_id")?;
        let operation = self.store.load_if_exists(operation_id)?.ok_or_else(|| {
            ProtocolError::new(
                ErrorCode::OperationNotFound,
                format!("operation {operation_id} does not exist"),
            )
        })?;
        if operation.state != OperationState::Running {
            self.active.remove(operation_id);
        }
        Ok(operation)
    }

    fn operation_output(
        &mut self,
        request: OperationOutputRequest,
    ) -> Result<OperationOutputResponse, ProtocolError> {
        validate_identifier(
            &request.operation_id,
            MAX_OPERATION_ID_BYTES,
            "operation_id",
        )?;
        if request.max_bytes == 0 || request.max_bytes > MAX_OUTPUT_CHUNK_BYTES {
            return Err(ProtocolError::new(
                ErrorCode::InvalidRequest,
                format!("max_bytes must be in 1..={MAX_OUTPUT_CHUNK_BYTES}"),
            ));
        }
        let record = self
            .store
            .load_if_exists(&request.operation_id)?
            .ok_or_else(|| {
                ProtocolError::new(
                    ErrorCode::OperationNotFound,
                    format!("operation {} does not exist", request.operation_id),
                )
            })?;
        let (stdout, stderr, stdout_truncated, stderr_truncated) =
            if record.state == OperationState::Running {
                let active = self.active.get(&request.operation_id).ok_or_else(|| {
                    ProtocolError::new(
                        ErrorCode::SessionError,
                        "running operation is not owned by this runner process",
                    )
                })?;
                let output = active.output.as_ref().ok_or_else(|| {
                    ProtocolError::new(
                        ErrorCode::InvalidRequest,
                        "operation was not started with stream_output enabled",
                    )
                })?;
                let output = output.lock().map_err(|_| {
                    ProtocolError::new(ErrorCode::Internal, "live output lock is poisoned")
                })?;
                (
                    stable_live_text(&output.stdout),
                    stable_live_text(&output.stderr),
                    output.stdout_truncated,
                    output.stderr_truncated,
                )
            } else {
                let result = record.result.as_ref().ok_or_else(|| {
                    ProtocolError::new(
                        ErrorCode::SessionError,
                        "terminal operation has no output result",
                    )
                })?;
                (
                    result.stdout.clone(),
                    result.stderr.clone(),
                    result.stdout_truncated,
                    result.stderr_truncated,
                )
            };
        let stdout_chunk = utf8_output_chunk(&stdout, request.stdout_offset, request.max_bytes)?;
        let remaining = request.max_bytes.saturating_sub(stdout_chunk.len() as u64);
        let stderr_chunk = utf8_output_chunk(&stderr, request.stderr_offset, remaining)?;
        Ok(OperationOutputResponse {
            operation_id: request.operation_id,
            state: record.state,
            stdout_offset: request.stdout_offset,
            stdout_next_offset: request.stdout_offset + stdout_chunk.len() as u64,
            stdout: stdout_chunk,
            stderr_offset: request.stderr_offset,
            stderr_next_offset: request.stderr_offset + stderr_chunk.len() as u64,
            stderr: stderr_chunk,
            stdout_truncated,
            stderr_truncated,
        })
    }

    /// Idempotently cancel an operation. The response is emitted only after
    /// the child has exited and the terminal operation record is persisted.
    async fn cancel(
        &mut self,
        operation_id: &str,
    ) -> Result<CancelOperationResponse, ProtocolError> {
        let current = self.operation_status(operation_id)?;
        if current.state != OperationState::Running {
            self.active.remove(operation_id);
            return Ok(CancelOperationResponse {
                already_terminal: true,
                operation: current,
            });
        }
        let active = self.active.get(operation_id).ok_or_else(|| {
            ProtocolError::new(
                ErrorCode::SessionError,
                "running operation is not owned by this runner process",
            )
        })?;
        active.cancel.cancel();
        active.completed.cancelled().await;
        let operation = self.operation_status(operation_id)?;
        self.active.remove(operation_id);
        if operation.state == OperationState::Running {
            return Err(ProtocolError::new(
                ErrorCode::Internal,
                "operation completion was not persisted",
            ));
        }
        Ok(CancelOperationResponse {
            already_terminal: false,
            operation,
        })
    }

    fn prepare_exec(&self, request: &ExecRequest) -> Result<PreparedExec, ProtocolError> {
        let policy = &self.metadata.effective_policy;
        if request.stream_output && (!request.env.is_empty() || !request.env_refs.is_empty()) {
            return Err(ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                "stream_output cannot be combined with explicit env values or environment references",
            ));
        }
        validate_program(&request.program)?;
        if !policy.allowed_programs.contains(&request.program) {
            return Err(ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!("program {} is not allowed", request.program),
            ));
        }
        let executable = policy.program_paths.get(&request.program).ok_or_else(|| {
            ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!(
                    "program {} has no validated host executable",
                    request.program
                ),
            )
        })?;
        let expected_identity =
            policy
                .program_identities
                .get(&request.program)
                .ok_or_else(|| {
                    ProtocolError::new(
                        ErrorCode::DeniedByPolicy,
                        format!(
                            "program {} has no runner-attested identity",
                            request.program
                        ),
                    )
                })?;
        let current_identity = resolve_executable(Path::new(executable)).map_err(|_| {
            ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!("program {} executable is no longer valid", request.program),
            )
        })?;
        if &current_identity.identity != expected_identity {
            return Err(ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!("program {} executable identity changed", request.program),
            ));
        }
        if request.argv.len() > policy.max_args as usize {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!("argument count exceeds {}", policy.max_args),
            ));
        }
        let arg_bytes = request
            .argv
            .iter()
            .try_fold(0_u64, |total, argument| {
                if argument.contains('\0') {
                    None
                } else {
                    total.checked_add(argument.len() as u64)
                }
            })
            .ok_or_else(|| {
                ProtocolError::new(
                    ErrorCode::InvalidRequest,
                    "arguments contain NUL or exceed numeric limits",
                )
            })?;
        if arg_bytes > policy.max_arg_bytes {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!("argument bytes exceed {}", policy.max_arg_bytes),
            ));
        }
        for key in request.env.keys() {
            validate_env_name(key)?;
            if matches!(key.as_str(), "PATH" | "HOME" | "CARGO_HOME") {
                return Err(ProtocolError::new(
                    ErrorCode::DeniedByPolicy,
                    format!("{key} is owned by the remote runner"),
                ));
            }
            if !policy.allowed_env.contains(key) {
                return Err(ProtocolError::new(
                    ErrorCode::DeniedByPolicy,
                    format!("environment variable {key} is not allowed"),
                ));
            }
        }
        if request.env.values().any(|value| value.contains('\0')) {
            return Err(ProtocolError::new(
                ErrorCode::InvalidRequest,
                "environment values cannot contain NUL",
            ));
        }
        if request.env_refs.len() > policy.max_args as usize {
            return Err(ProtocolError::new(
                ErrorCode::TooLarge,
                format!("environment reference count exceeds {}", policy.max_args),
            ));
        }
        let mut resolved_env = Vec::new();
        let mut resolved_names = std::collections::BTreeSet::new();
        let mut resolved_redactions = Vec::new();
        for name in &request.env_refs {
            validate_env_name(name)?;
            if matches!(name.as_str(), "PATH" | "HOME" | "CARGO_HOME") {
                return Err(ProtocolError::new(
                    ErrorCode::DeniedByPolicy,
                    format!("{name} is owned by the remote runner"),
                ));
            }
            if !resolved_names.insert(name.clone()) {
                return Err(ProtocolError::new(
                    ErrorCode::InvalidRequest,
                    format!("duplicate environment reference {name}"),
                ));
            }
            if request.env.contains_key(name) {
                return Err(ProtocolError::new(
                    ErrorCode::InvalidRequest,
                    format!("environment value {name} is both explicit and referenced"),
                ));
            }
            let reference = self.metadata.environment_refs.get(name).ok_or_else(|| {
                ProtocolError::new(
                    ErrorCode::DeniedByPolicy,
                    format!("environment reference {name} is not declared for this session"),
                )
            })?;
            let value = match reference {
                EnvironmentRef::Host => {
                    if !policy.allowed_env_refs.contains(name) {
                        return Err(ProtocolError::new(
                            ErrorCode::DeniedByPolicy,
                            format!("host environment reference {name} is not allowed"),
                        ));
                    }
                    let value = std::env::var_os(name).ok_or_else(|| {
                        ProtocolError::new(
                            ErrorCode::InvalidRequest,
                            format!("host environment reference {name} is unavailable"),
                        )
                    })?;
                    let text = value.to_string_lossy();
                    if !text.is_empty() {
                        resolved_redactions.push(text.into_owned());
                    }
                    value
                }
                EnvironmentRef::SessionSqlite => {
                    if !policy.allow_session_sqlite {
                        return Err(ProtocolError::new(
                            ErrorCode::DeniedByPolicy,
                            "session SQLite environment references are disabled",
                        ));
                    }
                    self.session_sqlite_url(name)?.into()
                }
            };
            resolved_env.push((name.clone(), value));
        }

        let (cwd_relative, cwd_text) = normalize_relative(&request.cwd, true)?;
        if self.is_denied(&cwd_text) {
            return Err(ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!("working directory {cwd_text} is denied"),
            ));
        }
        let cwd = self.resolve_existing(&cwd_relative)?;
        if !cwd.is_dir() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "command cwd must be a directory",
            ));
        }
        let timeout_secs = request
            .timeout_secs
            .unwrap_or(policy.max_timeout_secs)
            .min(policy.max_timeout_secs);
        let max_output_bytes = request
            .max_output_bytes
            .unwrap_or(policy.max_output_bytes)
            .min(policy.max_output_bytes);
        let cargo_home = self.session_cargo_home()?;
        let inherited_env = policy
            .inherited_env
            .iter()
            .filter_map(|key| {
                self.server_environment
                    .get(key)
                    .cloned()
                    .map(|value| (key.clone(), value))
            })
            .collect();
        Ok(PreparedExec {
            program_name: request.program.clone(),
            executable: current_identity.entry_path,
            argv: request.argv.clone(),
            cwd,
            workspace: self.workspace.clone(),
            cargo_home,
            inherited_env,
            explicit_env: request.env.clone(),
            resolved_env,
            redactions: request
                .env
                .values()
                .filter(|value| !value.is_empty())
                .cloned()
                .chain(resolved_redactions)
                .collect(),
            timeout_secs,
            max_output_bytes,
        })
    }

    fn session_sqlite_url(&self, name: &str) -> Result<String, ProtocolError> {
        let _filesystem_guard = self
            .filesystem_lock
            .lock()
            .map_err(|_| ProtocolError::new(ErrorCode::Internal, "filesystem lock is poisoned"))?;
        let internal = ensure_private_child_directory(&self.workspace, ".klintcode")?;
        let runtime = ensure_private_child_directory(&internal, "runtime")?;
        let digest = sha256_bytes(name.as_bytes());
        let database = runtime.join(format!("{}.sqlite3", &digest[..24]));
        reject_symlink_if_present(&database)?;
        Ok(format!("sqlite://{}", database.display()))
    }

    fn session_cargo_home(&self) -> Result<PathBuf, ProtocolError> {
        let _filesystem_guard = self
            .filesystem_lock
            .lock()
            .map_err(|_| ProtocolError::new(ErrorCode::Internal, "filesystem lock is poisoned"))?;
        let internal = ensure_private_child_directory(&self.workspace, ".klintcode")?;
        ensure_private_child_directory(&internal, "cargo-home")
    }

    fn require_readable(&self, relative: &str) -> Result<(), ProtocolError> {
        if self.is_readable(relative) {
            Ok(())
        } else {
            Err(ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!("read access denied for {relative}"),
            ))
        }
    }

    fn require_writable(&self, relative: &str) -> Result<(), ProtocolError> {
        if self.is_denied(relative)
            || !matches_all_layers(&self.metadata.effective_policy.writable_layers, relative)
        {
            Err(ProtocolError::new(
                ErrorCode::DeniedByPolicy,
                format!("write access denied for {relative}"),
            ))
        } else {
            Ok(())
        }
    }

    fn is_readable(&self, relative: &str) -> bool {
        !self.is_denied(relative)
            && matches_all_layers(&self.metadata.effective_policy.readable_layers, relative)
    }

    fn is_denied(&self, relative: &str) -> bool {
        matches_any(&self.metadata.effective_policy.denied, relative)
    }

    fn entry_limit(&self, requested: Option<u32>) -> usize {
        requested
            .unwrap_or(self.metadata.effective_policy.max_list_entries)
            .min(self.metadata.effective_policy.max_list_entries) as usize
    }

    fn collect_walk(
        &self,
        root: &Path,
        root_relative: &str,
        limit: usize,
        max_depth: u32,
        excluded_paths: &[String],
    ) -> Result<(Vec<WorkspaceWalkEntry>, bool), ProtocolError> {
        let mut entries = Vec::new();
        let mut truncated = false;
        self.collect_walk_directory(
            root,
            root_relative,
            0,
            max_depth,
            limit,
            excluded_paths,
            &mut entries,
            &mut truncated,
        )?;
        Ok((entries, truncated))
    }

    #[allow(clippy::too_many_arguments)]
    fn collect_walk_directory(
        &self,
        directory: &Path,
        directory_relative: &str,
        depth: u32,
        max_depth: u32,
        limit: usize,
        excluded_paths: &[String],
        output: &mut Vec<WorkspaceWalkEntry>,
        truncated: &mut bool,
    ) -> Result<(), ProtocolError> {
        let metadata = directory
            .symlink_metadata()
            .map_err(|error| map_path_io(directory, error))?;
        if metadata.file_type().is_symlink() {
            return Err(symlink_error(directory));
        }
        if !metadata.is_dir() {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "walk encountered a non-directory parent",
            ));
        }

        let mut children = Vec::new();
        for entry in std::fs::read_dir(directory).map_err(|error| map_path_io(directory, error))? {
            let entry = entry.map_err(|error| io_error("read directory entry", error))?;
            let name = entry.file_name().into_string().map_err(|_| {
                ProtocolError::new(
                    ErrorCode::InvalidUtf8,
                    "workspace paths must contain valid UTF-8",
                )
            })?;
            children.push((name, entry.path()));
        }
        children.sort_by(|left, right| left.0.cmp(&right.0));

        for (name, path) in children {
            let relative = if directory_relative == "." {
                name
            } else {
                format!("{directory_relative}/{name}")
            };
            if excluded_paths.iter().any(|excluded| {
                relative == *excluded
                    || relative
                        .strip_prefix(excluded)
                        .is_some_and(|suffix| suffix.starts_with('/'))
            }) {
                continue;
            }
            if !self.is_readable(&relative) {
                continue;
            }
            if output.len() == limit {
                *truncated = true;
                return Ok(());
            }
            let metadata = path
                .symlink_metadata()
                .map_err(|error| map_path_io(&path, error))?;
            let kind = file_kind(&metadata);
            output.push(WorkspaceWalkEntry {
                relative: relative.clone(),
                absolute: path.clone(),
                kind,
                bytes: metadata.len(),
            });
            if kind == FileKind::Directory {
                if depth < max_depth {
                    self.collect_walk_directory(
                        &path,
                        &relative,
                        depth + 1,
                        max_depth,
                        limit,
                        excluded_paths,
                        output,
                        truncated,
                    )?;
                    if *truncated {
                        return Ok(());
                    }
                } else {
                    *truncated = true;
                }
            }
        }
        Ok(())
    }

    fn resolve_optional(&self, relative: &Path) -> Result<Option<PathBuf>, ProtocolError> {
        let mut current = self.workspace.clone();
        for component in relative.components() {
            let Component::Normal(part) = component else {
                continue;
            };
            current.push(part);
            let metadata = match current.symlink_metadata() {
                Ok(metadata) => metadata,
                Err(error) if error.kind() == std::io::ErrorKind::NotFound => return Ok(None),
                Err(error) => return Err(map_path_io(&current, error)),
            };
            if metadata.file_type().is_symlink() {
                return Err(symlink_error(&current));
            }
        }
        let canonical =
            std::fs::canonicalize(&current).map_err(|error| map_path_io(&current, error))?;
        if !canonical.starts_with(&self.workspace) {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "path escapes session workspace",
            ));
        }
        Ok(Some(canonical))
    }

    fn resolve_existing(&self, relative: &Path) -> Result<PathBuf, ProtocolError> {
        let mut current = self.workspace.clone();
        for component in relative.components() {
            let Component::Normal(part) = component else {
                continue;
            };
            current.push(part);
            let metadata = current
                .symlink_metadata()
                .map_err(|error| map_path_io(&current, error))?;
            if metadata.file_type().is_symlink() {
                return Err(symlink_error(&current));
            }
        }
        let canonical =
            std::fs::canonicalize(&current).map_err(|error| map_path_io(&current, error))?;
        if !canonical.starts_with(&self.workspace) {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "path escapes session workspace",
            ));
        }
        Ok(canonical)
    }

    fn ensure_directory(&self, relative: &Path, create: bool) -> Result<PathBuf, ProtocolError> {
        let mut current = self.workspace.clone();
        for component in relative.components() {
            let Component::Normal(part) = component else {
                continue;
            };
            current.push(part);
            match current.symlink_metadata() {
                Ok(metadata) => {
                    if metadata.file_type().is_symlink() {
                        return Err(symlink_error(&current));
                    }
                    if !metadata.is_dir() {
                        return Err(ProtocolError::new(
                            ErrorCode::InvalidPath,
                            format!("{} is not a directory", current.display()),
                        ));
                    }
                }
                Err(error) if error.kind() == std::io::ErrorKind::NotFound && create => {
                    std::fs::create_dir(&current)
                        .map_err(|error| io_error("create parent directory", error))?;
                }
                Err(error) => return Err(map_path_io(&current, error)),
            }
        }
        let canonical =
            std::fs::canonicalize(&current).map_err(|error| map_path_io(&current, error))?;
        if !canonical.starts_with(&self.workspace) {
            return Err(ProtocolError::new(
                ErrorCode::InvalidPath,
                "parent path escapes session workspace",
            ));
        }
        Ok(canonical)
    }
}

impl OperationStore {
    fn operation_path(&self, operation_id: &str) -> PathBuf {
        self.operations.join(format!("{operation_id}.json"))
    }

    fn load_if_exists(&self, operation_id: &str) -> Result<Option<OperationRecord>, ProtocolError> {
        let path = self.operation_path(operation_id);
        reject_symlink_if_present(&path)?;
        let bytes = match std::fs::read(&path) {
            Ok(bytes) => bytes,
            Err(error) if error.kind() == std::io::ErrorKind::NotFound => return Ok(None),
            Err(error) => return Err(io_error("read operation record", error)),
        };
        let operation = serde_json::from_slice(&bytes).map_err(|error| {
            ProtocolError::new(
                ErrorCode::SessionError,
                format!("invalid operation record: {error}"),
            )
        })?;
        Ok(Some(operation))
    }

    fn persist(&self, operation: &OperationRecord) -> Result<(), ProtocolError> {
        atomic_json_write(&self.operation_path(&operation.operation_id), operation)
    }

    fn append_journal(&self, operation: &OperationRecord) -> Result<(), ProtocolError> {
        let entry = JournalEntry {
            timestamp_ms: unix_timestamp_ms(),
            session_id: self.session_id.clone(),
            operation: operation.clone(),
        };
        let mut bytes = serde_json::to_vec(&entry).map_err(serialization_error)?;
        bytes.push(b'\n');
        let _guard = self.journal_lock.lock().map_err(|_| {
            ProtocolError::new(ErrorCode::Internal, "operation journal lock is poisoned")
        })?;
        let path = self.session_directory.join("journal.ndjson");
        reject_symlink_if_present(&path)?;
        let mut file = OpenOptions::new()
            .create(true)
            .append(true)
            .open(path)
            .map_err(|error| io_error("open operation journal", error))?;
        file.write_all(&bytes)
            .map_err(|error| io_error("append operation journal", error))?;
        file.sync_data()
            .map_err(|error| io_error("sync operation journal", error))
    }

    fn mark_stale_operations_interrupted(&self) -> Result<(), ProtocolError> {
        let entries = std::fs::read_dir(&self.operations)
            .map_err(|error| io_error("read operations directory", error))?;
        for entry in entries {
            let entry = entry.map_err(|error| io_error("read operation entry", error))?;
            if entry.path().extension() != Some(OsStr::new("json")) {
                continue;
            }
            reject_symlink(&entry.path())?;
            let bytes = std::fs::read(entry.path())
                .map_err(|error| io_error("read operation record", error))?;
            let mut operation: OperationRecord =
                serde_json::from_slice(&bytes).map_err(|error| {
                    ProtocolError::new(
                        ErrorCode::SessionError,
                        format!("invalid operation record: {error}"),
                    )
                })?;
            if operation.state == OperationState::Running {
                operation.state = OperationState::Interrupted;
                operation.message =
                    Some("runner stopped before recording operation completion".into());
                self.persist(&operation)?;
                self.append_journal(&operation)?;
            }
        }
        Ok(())
    }
}

#[derive(Serialize)]
struct JournalEntry {
    timestamp_ms: u128,
    session_id: String,
    operation: OperationRecord,
}

struct PreparedExec {
    program_name: String,
    executable: PathBuf,
    argv: Vec<String>,
    cwd: PathBuf,
    workspace: PathBuf,
    cargo_home: PathBuf,
    inherited_env: Vec<(String, std::ffi::OsString)>,
    explicit_env: BTreeMap<String, String>,
    resolved_env: Vec<(String, std::ffi::OsString)>,
    redactions: Vec<String>,
    timeout_secs: u64,
    max_output_bytes: u64,
}

struct ProcessOutcome {
    state: OperationState,
    result: Option<ExecResult>,
    message: Option<String>,
}

/// Kills a newly-created process group if the execution future is dropped
/// before it reaches the explicit cleanup path (for example when an SSH
/// bridge process loses its runtime during disconnect).
#[cfg(unix)]
struct ProcessGroupGuard {
    process_group: Option<i32>,
}

#[cfg(unix)]
impl ProcessGroupGuard {
    fn new(child_id: Option<u32>) -> Self {
        Self {
            process_group: child_id.and_then(|id| i32::try_from(id).ok()),
        }
    }

    fn kill_now(&mut self) -> std::io::Result<()> {
        let Some(process_group) = self.process_group.take() else {
            return Ok(());
        };
        let result = unsafe { libc::kill(-process_group, libc::SIGKILL) };
        if result == 0 {
            return Ok(());
        }
        let error = std::io::Error::last_os_error();
        if error.raw_os_error() == Some(libc::ESRCH) {
            Ok(())
        } else {
            Err(error)
        }
    }
}

#[cfg(unix)]
impl Drop for ProcessGroupGuard {
    fn drop(&mut self) {
        let _ = self.kill_now();
    }
}

#[cfg(not(unix))]
struct ProcessGroupGuard;

#[cfg(not(unix))]
impl ProcessGroupGuard {
    fn new(_child_id: Option<u32>) -> Self {
        Self
    }
}

async fn run_prepared_process(
    prepared: PreparedExec,
    cancel: CancellationToken,
    live_output: Option<Arc<Mutex<LiveOperationOutput>>>,
) -> ProcessOutcome {
    let started = Instant::now();
    let mut command = Command::new(&prepared.executable);
    command
        .args(&prepared.argv)
        .current_dir(&prepared.cwd)
        .env_clear()
        .envs(prepared.inherited_env)
        .env("HOME", &prepared.workspace)
        .env("CARGO_HOME", &prepared.cargo_home)
        .env("PAGER", "cat")
        .env("GIT_PAGER", "cat")
        .env("GIT_TERMINAL_PROMPT", "0")
        .env("CARGO_TERM_COLOR", "never")
        .env("NO_COLOR", "1")
        .env("CI", "1")
        .envs(&prepared.explicit_env)
        .envs(prepared.resolved_env)
        .stdin(Stdio::null())
        .stdout(Stdio::piped())
        .stderr(Stdio::piped())
        .kill_on_drop(true);
    #[cfg(unix)]
    {
        use std::os::unix::process::CommandExt;
        command.as_std_mut().process_group(0);
    }
    let mut child = match command.spawn() {
        Ok(child) => child,
        Err(error) => {
            return ProcessOutcome {
                state: OperationState::SpawnFailed,
                result: None,
                message: Some(format!(
                    "failed to spawn validated program {}: {error}",
                    prepared.program_name
                )),
            };
        }
    };
    let mut process_group = ProcessGroupGuard::new(child.id());
    let stdout = child.stdout.take();
    let stderr = child.stderr.take();
    let remaining = Arc::new(AtomicU64::new(prepared.max_output_bytes));
    let stdout_task = stdout.map(|stream| {
        tokio::spawn(read_bounded_output(
            stream,
            Arc::clone(&remaining),
            live_output.clone(),
            OutputStream::Stdout,
        ))
    });
    let stderr_task = stderr.map(|stream| {
        tokio::spawn(read_bounded_output(
            stream,
            Arc::clone(&remaining),
            live_output.clone(),
            OutputStream::Stderr,
        ))
    });

    enum StopReason {
        Status(std::process::ExitStatus),
        WaitFailed(String),
        TimedOut,
        Cancelled,
    }
    let stopped = tokio::select! {
        biased;
        _ = cancel.cancelled() => StopReason::Cancelled,
        result = tokio::time::timeout(
            Duration::from_secs(prepared.timeout_secs),
            child.wait(),
        ) => match result {
            Ok(Ok(status)) => StopReason::Status(status),
            Ok(Err(error)) => StopReason::WaitFailed(error.to_string()),
            Err(_) => StopReason::TimedOut,
        },
    };
    let (state, status, message) = match stopped {
        StopReason::Status(status) => {
            let cleanup = stop_process_tree(&mut child, &mut process_group, true).await;
            (OperationState::Exited, Some(status), cleanup)
        }
        StopReason::WaitFailed(error) => {
            let cleanup = stop_process_tree(&mut child, &mut process_group, false).await;
            (
                OperationState::Interrupted,
                None,
                Some(with_cleanup_message(
                    format!("failed while waiting for process: {error}"),
                    cleanup,
                )),
            )
        }
        StopReason::TimedOut => {
            let cleanup = stop_process_tree(&mut child, &mut process_group, false).await;
            (
                OperationState::TimedOut,
                None,
                Some(with_cleanup_message(
                    format!("command timed out after {}s", prepared.timeout_secs),
                    cleanup,
                )),
            )
        }
        StopReason::Cancelled => {
            let cleanup = stop_process_tree(&mut child, &mut process_group, false).await;
            (
                OperationState::Cancelled,
                None,
                Some(with_cleanup_message("operation cancelled".into(), cleanup)),
            )
        }
    };
    let (stdout, stderr) = tokio::join!(join_output(stdout_task), join_output(stderr_task));
    let exit_code = status.as_ref().and_then(|status| status.code());
    #[cfg(unix)]
    let signal = {
        use std::os::unix::process::ExitStatusExt;
        status.as_ref().and_then(|status| status.signal())
    };
    #[cfg(not(unix))]
    let signal = None;
    let mut stdout_text = String::from_utf8_lossy(&stdout.bytes).into_owned();
    let mut stderr_text = String::from_utf8_lossy(&stderr.bytes).into_owned();
    redact_values(&mut stdout_text, &prepared.redactions);
    redact_values(&mut stderr_text, &prepared.redactions);
    ProcessOutcome {
        state,
        result: Some(ExecResult {
            exit_code,
            signal,
            stdout: stdout_text,
            stderr: stderr_text,
            stdout_truncated: stdout.truncated,
            stderr_truncated: stderr.truncated,
            elapsed_ms: started.elapsed().as_millis().min(u64::MAX as u128) as u64,
        }),
        message,
    }
}

fn with_cleanup_message(message: String, cleanup: Option<String>) -> String {
    cleanup.map_or(message.clone(), |cleanup| format!("{message}; {cleanup}"))
}

#[cfg(unix)]
async fn stop_process_tree(
    child: &mut tokio::process::Child,
    process_group: &mut ProcessGroupGuard,
    leader_reaped: bool,
) -> Option<String> {
    let mut failures = Vec::new();
    let had_process_group = process_group.process_group.is_some();
    if let Err(error) = process_group.kill_now() {
        failures.push(format!("process-group kill failed: {error}"));
        if !leader_reaped && let Err(error) = child.start_kill() {
            failures.push(format!("leader kill failed: {error}"));
        }
    } else if !had_process_group
        && !leader_reaped
        && let Err(error) = child.start_kill()
    {
        failures.push(format!("leader kill failed: {error}"));
    }

    if !leader_reaped {
        match tokio::time::timeout(PROCESS_EXIT_TIMEOUT, child.wait()).await {
            Ok(Ok(_)) => {}
            Ok(Err(error)) => failures.push(format!("leader wait failed: {error}")),
            Err(_) => failures.push("leader did not exit after kill".into()),
        }
    }
    if failures.is_empty() {
        None
    } else {
        Some(failures.join("; "))
    }
}

#[cfg(not(unix))]
async fn stop_process_tree(
    child: &mut tokio::process::Child,
    _process_group: &mut ProcessGroupGuard,
    leader_reaped: bool,
) -> Option<String> {
    let mut failures = Vec::new();
    if !leader_reaped {
        if let Err(error) = child.start_kill() {
            failures.push(format!("leader kill failed: {error}"));
        }
        match tokio::time::timeout(PROCESS_EXIT_TIMEOUT, child.wait()).await {
            Ok(Ok(_)) => {}
            Ok(Err(error)) => failures.push(format!("leader wait failed: {error}")),
            Err(_) => failures.push("leader did not exit after kill".into()),
        }
    }
    if failures.is_empty() {
        None
    } else {
        Some(failures.join("; "))
    }
}

struct BoundedOutput {
    bytes: Vec<u8>,
    truncated: bool,
}

#[derive(Clone, Copy)]
enum OutputStream {
    Stdout,
    Stderr,
}

async fn read_bounded_output<R: AsyncRead + Unpin>(
    mut reader: R,
    remaining: Arc<AtomicU64>,
    live_output: Option<Arc<Mutex<LiveOperationOutput>>>,
    stream: OutputStream,
) -> std::io::Result<BoundedOutput> {
    let mut retained = Vec::new();
    let mut truncated = false;
    let mut buffer = [0_u8; 8192];
    loop {
        let count = reader.read(&mut buffer).await?;
        if count == 0 {
            break;
        }
        let previous = remaining
            .try_update(Ordering::Relaxed, Ordering::Relaxed, |value| {
                Some(value.saturating_sub(count as u64))
            })
            .unwrap_or(0);
        let keep = previous.min(count as u64) as usize;
        retained.extend_from_slice(&buffer[..keep]);
        truncated |= keep < count;
        if let Some(live_output) = &live_output {
            let mut live = live_output
                .lock()
                .map_err(|_| std::io::Error::other("live output lock is poisoned"))?;
            match stream {
                OutputStream::Stdout => {
                    live.stdout.extend_from_slice(&buffer[..keep]);
                    live.stdout_truncated |= keep < count;
                }
                OutputStream::Stderr => {
                    live.stderr.extend_from_slice(&buffer[..keep]);
                    live.stderr_truncated |= keep < count;
                }
            }
        }
    }
    Ok(BoundedOutput {
        bytes: retained,
        truncated,
    })
}

fn stable_live_text(bytes: &[u8]) -> String {
    let mut sequence_start = bytes.len().saturating_sub(1);
    while sequence_start > 0
        && bytes[sequence_start] & 0b1100_0000 == 0b1000_0000
        && bytes.len() - sequence_start < 4
    {
        sequence_start -= 1;
    }
    let expected = bytes.get(sequence_start).map_or(0, |byte| match byte {
        0x00..=0x7f => 1,
        0xc2..=0xdf => 2,
        0xe0..=0xef => 3,
        0xf0..=0xf4 => 4,
        _ => 1,
    });
    let stable_len = if expected > bytes.len().saturating_sub(sequence_start) {
        sequence_start
    } else {
        bytes.len()
    };
    String::from_utf8_lossy(&bytes[..stable_len]).into_owned()
}

fn utf8_output_chunk(content: &str, offset: u64, max_bytes: u64) -> Result<String, ProtocolError> {
    let offset = usize::try_from(offset)
        .map_err(|_| ProtocolError::new(ErrorCode::InvalidRequest, "output offset is too large"))?;
    if offset > content.len() || !content.is_char_boundary(offset) {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            "output offset is outside the retained UTF-8 output",
        ));
    }
    let mut end = offset.saturating_add(max_bytes as usize).min(content.len());
    while end > offset && !content.is_char_boundary(end) {
        end -= 1;
    }
    Ok(content[offset..end].to_owned())
}

async fn join_output(
    task: Option<tokio::task::JoinHandle<std::io::Result<BoundedOutput>>>,
) -> BoundedOutput {
    match task {
        Some(mut task) => match tokio::time::timeout(OUTPUT_DRAIN_TIMEOUT, &mut task).await {
            Ok(Ok(Ok(output))) => output,
            Ok(Ok(Err(_))) | Ok(Err(_)) => BoundedOutput {
                bytes: Vec::new(),
                truncated: true,
            },
            Err(_) => {
                task.abort();
                BoundedOutput {
                    bytes: Vec::new(),
                    truncated: true,
                }
            }
        },
        None => BoundedOutput {
            bytes: Vec::new(),
            truncated: false,
        },
    }
}

fn prepare_runner_root(configured_root: &Path) -> Result<PathBuf, ProtocolError> {
    let root_created = match configured_root.symlink_metadata() {
        Ok(metadata) => {
            if metadata.file_type().is_symlink() || !metadata.is_dir() {
                return Err(ProtocolError::new(
                    ErrorCode::SessionError,
                    "runner root must be a real directory",
                ));
            }
            false
        }
        Err(error) if error.kind() == std::io::ErrorKind::NotFound => {
            std::fs::create_dir_all(configured_root)
                .map_err(|error| io_error("create runner root", error))?;
            true
        }
        Err(error) => return Err(io_error("inspect runner root", error)),
    };
    let root = std::fs::canonicalize(configured_root)
        .map_err(|error| io_error("canonicalize runner root", error))?;
    if root_created {
        set_private_directory_permissions(&root)?;
    }

    let sessions = root.join("sessions");
    let sessions_created = match sessions.symlink_metadata() {
        Ok(metadata) => {
            if metadata.file_type().is_symlink() || !metadata.is_dir() {
                return Err(ProtocolError::new(
                    ErrorCode::SessionError,
                    "runner sessions path must be a real directory",
                ));
            }
            false
        }
        Err(error) if error.kind() == std::io::ErrorKind::NotFound => {
            std::fs::create_dir(&sessions)
                .map_err(|error| io_error("create sessions directory", error))?;
            true
        }
        Err(error) => return Err(io_error("inspect sessions directory", error)),
    };
    if sessions_created {
        set_private_directory_permissions(&sessions)?;
    }
    Ok(root)
}

fn create_session_directory(path: &Path, require_existing: bool) -> Result<bool, ProtocolError> {
    if require_existing {
        match path.symlink_metadata() {
            Ok(metadata) => {
                if metadata.file_type().is_symlink() || !metadata.is_dir() {
                    return Err(ProtocolError::new(
                        ErrorCode::SessionError,
                        "session path must be a real directory",
                    ));
                }
                return Ok(false);
            }
            Err(error) if error.kind() == std::io::ErrorKind::NotFound => {
                return Err(ProtocolError::new(
                    ErrorCode::NotFound,
                    "requested session does not exist",
                ));
            }
            Err(error) => return Err(io_error("inspect session directory", error)),
        }
    }
    match std::fs::create_dir(path) {
        Ok(()) => Ok(true),
        Err(error) if error.kind() == std::io::ErrorKind::AlreadyExists => {
            reject_symlink(path)?;
            if !path.is_dir() {
                return Err(ProtocolError::new(
                    ErrorCode::SessionError,
                    "session path exists but is not a directory",
                ));
            }
            Ok(false)
        }
        Err(error) => Err(io_error("create session directory", error)),
    }
}

fn normalize_relative(path: &str, allow_root: bool) -> Result<(PathBuf, String), ProtocolError> {
    if path.contains('\0') {
        return Err(ProtocolError::new(
            ErrorCode::InvalidPath,
            "path cannot contain NUL",
        ));
    }
    let input = if path.is_empty() {
        Path::new(".")
    } else {
        Path::new(path)
    };
    if input.is_absolute() {
        return Err(ProtocolError::new(
            ErrorCode::InvalidPath,
            "absolute paths are not allowed",
        ));
    }
    let mut normalized = PathBuf::new();
    for component in input.components() {
        match component {
            Component::Normal(part) => normalized.push(part),
            Component::CurDir => {}
            Component::ParentDir | Component::RootDir | Component::Prefix(_) => {
                return Err(ProtocolError::new(
                    ErrorCode::InvalidPath,
                    "path traversal is not allowed",
                ));
            }
        }
    }
    if normalized.as_os_str().is_empty() && !allow_root {
        return Err(ProtocolError::new(
            ErrorCode::InvalidPath,
            "workspace root is not a valid file target",
        ));
    }
    let text = if normalized.as_os_str().is_empty() {
        ".".into()
    } else {
        normalized.to_string_lossy().replace('\\', "/")
    };
    Ok((normalized, text))
}

fn validate_identifier(value: &str, max: usize, name: &str) -> Result<(), ProtocolError> {
    if value.is_empty()
        || value.len() > max
        || !value
            .bytes()
            .all(|byte| byte.is_ascii_alphanumeric() || matches!(byte, b'-' | b'_' | b'.'))
        || value == "."
        || value == ".."
    {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            format!("invalid {name}"),
        ));
    }
    Ok(())
}

fn validate_program(program: &str) -> Result<(), ProtocolError> {
    validate_identifier(program, 128, "program")?;
    const SHELLS: &[&str] = &[
        "sh",
        "bash",
        "dash",
        "zsh",
        "fish",
        "cmd",
        "cmd.exe",
        "powershell",
        "powershell.exe",
        "pwsh",
    ];
    if SHELLS.contains(&program) {
        return Err(ProtocolError::new(
            ErrorCode::DeniedByPolicy,
            "shell programs are not accepted by structured exec",
        ));
    }
    Ok(())
}

fn resolve_program_paths(policy: &mut HardPolicy) -> Result<(), ProtocolError> {
    for program in &policy.allowed_programs {
        validate_program(program)?;
    }
    for program in policy.program_paths.keys() {
        validate_program(program)?;
        if !policy.allowed_programs.contains(program) {
            return Err(ProtocolError::new(
                ErrorCode::InvalidRequest,
                format!("program path configured for non-allowed program {program}"),
            ));
        }
    }

    let mut resolved = BTreeMap::new();
    let mut identities = BTreeMap::new();
    let mut available = Vec::new();
    for program in &policy.allowed_programs {
        let executable = if let Some(configured) = policy.program_paths.get(program) {
            Some(resolve_executable(Path::new(configured)).map_err(|error| {
                ProtocolError::new(
                    ErrorCode::InvalidRequest,
                    format!(
                        "invalid executable mapping for {program}: {}",
                        error.message
                    ),
                )
            })?)
        } else {
            fixed_program_candidates(program)
                .into_iter()
                .find_map(|candidate| resolve_executable(&candidate).ok())
        };
        if let Some(executable) = executable {
            available.push(program.clone());
            resolved.insert(
                program.clone(),
                executable.entry_path.to_string_lossy().into_owned(),
            );
            identities.insert(program.clone(), executable.identity);
        }
    }
    available.sort();
    available.dedup();
    policy.allowed_programs = available;
    policy.program_paths = resolved;
    policy.program_identities = identities;
    Ok(())
}

fn fixed_program_candidates(program: &str) -> Vec<PathBuf> {
    let mut candidates = Vec::new();
    if matches!(program, "cargo" | "rustc")
        && let Some(candidate) = rustup_default_toolchain_executable(program)
    {
        candidates.push(candidate);
    }
    for variable in [
        "CARGO_HOME",
        "JAVA_HOME",
        "MAVEN_HOME",
        "M2_HOME",
        "GRADLE_HOME",
    ] {
        if let Some(root) = std::env::var_os(variable) {
            candidates.push(PathBuf::from(root).join("bin").join(program));
        }
    }
    if let Some(home) = std::env::var_os("HOME") {
        candidates.push(PathBuf::from(home).join(".cargo/bin").join(program));
    }
    for directory in ["/usr/local/bin", "/usr/bin", "/bin", "/opt/homebrew/bin"] {
        candidates.push(Path::new(directory).join(program));
    }
    candidates
}

fn rustup_default_toolchain_executable(program: &str) -> Option<PathBuf> {
    let rustup_home = std::env::var_os("RUSTUP_HOME")
        .map(PathBuf::from)
        .or_else(|| std::env::var_os("HOME").map(|home| PathBuf::from(home).join(".rustup")))?;
    let rustup_home = canonical_host_directory(&rustup_home)?;
    let toolchain = std::env::var("RUSTUP_TOOLCHAIN")
        .ok()
        .filter(|value| valid_rustup_toolchain_name(value))
        .or_else(|| {
            let settings = std::fs::read_to_string(rustup_home.join("settings.toml")).ok()?;
            if settings.len() > 64 * 1024 {
                return None;
            }
            let value = settings.parse::<toml::Value>().ok()?;
            value
                .get("default_toolchain")?
                .as_str()
                .filter(|name| valid_rustup_toolchain_name(name))
                .map(str::to_owned)
        })?;
    Some(
        rustup_home
            .join("toolchains")
            .join(toolchain)
            .join("bin")
            .join(program),
    )
}

fn valid_rustup_toolchain_name(value: &str) -> bool {
    !value.is_empty()
        && value.len() <= 128
        && !value.contains("..")
        && value
            .bytes()
            .all(|byte| byte.is_ascii_alphanumeric() || matches!(byte, b'-' | b'_' | b'.'))
}

fn build_server_environment(
    policy: &HardPolicy,
) -> Result<BTreeMap<String, OsString>, ProtocolError> {
    let mut environment = BTreeMap::new();
    for name in &policy.inherited_env {
        if name == "PATH" || name == "CARGO_HOME" || name == "HOME" {
            continue;
        }
        let Some(value) = std::env::var_os(name) else {
            continue;
        };
        if is_host_directory_environment(name) {
            let Some(path) = canonical_host_directory(Path::new(&value)) else {
                continue;
            };
            environment.insert(name.clone(), path.into_os_string());
        } else {
            environment.insert(name.clone(), value);
        }
    }
    if policy.inherited_env.iter().any(|name| name == "PATH") {
        environment.insert("PATH".into(), safe_server_path(policy)?);
    }
    Ok(environment)
}

fn safe_server_path(policy: &HardPolicy) -> Result<OsString, ProtocolError> {
    let mut directories = Vec::new();
    let mut seen = BTreeSet::new();
    for identity in policy.program_identities.values() {
        if let Some(parent) = Path::new(&identity.entry_path).parent() {
            add_safe_path_directory(&mut directories, &mut seen, parent);
        }
    }
    if let Some(home) = std::env::var_os("HOME") {
        let home = PathBuf::from(home);
        if home.is_absolute() {
            add_safe_path_directory(&mut directories, &mut seen, &home.join(".cargo/bin"));
        }
    }
    for name in ["JAVA_HOME", "MAVEN_HOME", "M2_HOME", "GRADLE_HOME"] {
        if let Some(root) = std::env::var_os(name) {
            add_safe_path_directory(
                &mut directories,
                &mut seen,
                &PathBuf::from(root).join("bin"),
            );
        }
    }
    for directory in ["/usr/local/bin", "/usr/bin", "/bin", "/opt/homebrew/bin"] {
        add_safe_path_directory(&mut directories, &mut seen, Path::new(directory));
    }
    std::env::join_paths(directories).map_err(|error| {
        ProtocolError::new(
            ErrorCode::InvalidRequest,
            format!("cannot construct safe server PATH: {error}"),
        )
    })
}

fn add_safe_path_directory(
    directories: &mut Vec<PathBuf>,
    seen: &mut BTreeSet<PathBuf>,
    path: &Path,
) {
    if let Some(canonical) = canonical_host_directory(path)
        && seen.insert(canonical.clone())
    {
        directories.push(canonical);
    }
}

fn canonical_host_directory(path: &Path) -> Option<PathBuf> {
    if !path.is_absolute()
        || path
            .components()
            .any(|component| matches!(component, Component::CurDir | Component::ParentDir))
    {
        return None;
    }
    let canonical = std::fs::canonicalize(path).ok()?;
    canonical.is_dir().then_some(canonical)
}

fn is_host_directory_environment(name: &str) -> bool {
    matches!(
        name,
        "RUSTUP_HOME"
            | "JAVA_HOME"
            | "MAVEN_HOME"
            | "M2_HOME"
            | "GRADLE_HOME"
            | "GRADLE_USER_HOME"
            | "TMPDIR"
    )
}

struct ResolvedExecutable {
    entry_path: PathBuf,
    identity: ProgramIdentity,
}

fn resolve_executable(path: &Path) -> Result<ResolvedExecutable, ProtocolError> {
    if !path.is_absolute() {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            "program executable path must be absolute",
        ));
    }
    if path
        .components()
        .any(|component| matches!(component, Component::CurDir | Component::ParentDir))
    {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            "program executable path cannot contain . or .. components",
        ));
    }
    let file_name = path.file_name().ok_or_else(|| {
        ProtocolError::new(
            ErrorCode::InvalidRequest,
            "program executable path must name a file",
        )
    })?;
    let parent = path.parent().ok_or_else(|| {
        ProtocolError::new(
            ErrorCode::InvalidRequest,
            "program executable path must have a parent directory",
        )
    })?;
    let canonical_parent = std::fs::canonicalize(parent)
        .map_err(|error| io_error("canonicalize program executable parent", error))?;
    let entry_path = canonical_parent.join(file_name);
    let entry_metadata = entry_path
        .symlink_metadata()
        .map_err(|error| map_path_io(&entry_path, error))?;
    if !entry_metadata.file_type().is_symlink() && !entry_metadata.is_file() {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            "program executable entry must be a regular file or symlink",
        ));
    }
    let canonical_target = std::fs::canonicalize(&entry_path)
        .map_err(|error| io_error("canonicalize program executable", error))?;
    let metadata = canonical_target
        .symlink_metadata()
        .map_err(|error| map_path_io(&canonical_target, error))?;
    if metadata.file_type().is_symlink() || !metadata.is_file() {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            "program executable must resolve to a regular file",
        ));
    }
    #[cfg(unix)]
    {
        use std::os::unix::fs::PermissionsExt;
        if metadata.permissions().mode() & 0o111 == 0 {
            return Err(ProtocolError::new(
                ErrorCode::InvalidRequest,
                "program executable is not executable",
            ));
        }
    }
    let entry_text = entry_path
        .to_str()
        .ok_or_else(|| {
            ProtocolError::new(
                ErrorCode::InvalidRequest,
                "program executable entry path must be UTF-8",
            )
        })?
        .to_owned();
    let target_text = canonical_target
        .to_str()
        .ok_or_else(|| {
            ProtocolError::new(
                ErrorCode::InvalidRequest,
                "program executable target path must be UTF-8",
            )
        })?
        .to_owned();
    let target_sha256 = hash_regular_file(&canonical_target)?;
    Ok(ResolvedExecutable {
        entry_path,
        identity: ProgramIdentity {
            entry_path: entry_text,
            canonical_target: target_text,
            target_sha256,
        },
    })
}

fn validate_env_name(name: &str) -> Result<(), ProtocolError> {
    if name.is_empty()
        || !name
            .bytes()
            .all(|byte| byte.is_ascii_alphanumeric() || byte == b'_')
    {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            format!("invalid environment variable name {name:?}"),
        ));
    }
    Ok(())
}

fn validate_environment_refs(
    references: &BTreeMap<String, EnvironmentRef>,
    policy: &EffectivePolicy,
) -> Result<(), ProtocolError> {
    if references.len() > policy.max_args as usize {
        return Err(ProtocolError::new(
            ErrorCode::TooLarge,
            format!("environment reference count exceeds {}", policy.max_args),
        ));
    }
    for (name, reference) in references {
        validate_env_name(name)?;
        match reference {
            EnvironmentRef::Host if !policy.allowed_env_refs.contains(name) => {
                return Err(ProtocolError::new(
                    ErrorCode::DeniedByPolicy,
                    format!("host environment reference {name} is not allowed"),
                ));
            }
            EnvironmentRef::SessionSqlite
                if !policy.allow_session_sqlite || !is_database_environment_name(name) =>
            {
                return Err(ProtocolError::new(
                    ErrorCode::DeniedByPolicy,
                    format!(
                        "session SQLite reference {name} is disabled or is not a DATABASE_URL name"
                    ),
                ));
            }
            EnvironmentRef::Host | EnvironmentRef::SessionSqlite => {}
        }
    }
    Ok(())
}

fn is_database_environment_name(name: &str) -> bool {
    name == "DATABASE_URL" || name.ends_with("_DATABASE_URL")
}

fn validate_sha256(value: &str) -> Result<String, ProtocolError> {
    if value.len() != 64 || !value.bytes().all(|byte| byte.is_ascii_hexdigit()) {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            "SHA-256 value must contain exactly 64 hexadecimal characters",
        ));
    }
    Ok(value.to_ascii_lowercase())
}

fn validate_hard_policy(policy: &HardPolicy) -> Result<(), ProtocolError> {
    validate_patterns(&policy.readable)?;
    validate_patterns(&policy.writable)?;
    validate_patterns(&policy.denied)?;
    for name in policy
        .allowed_env
        .iter()
        .chain(&policy.allowed_env_refs)
        .chain(&policy.inherited_env)
    {
        validate_env_name(name)?;
    }
    validate_frame_payload_limit("max_read_bytes", policy.max_read_bytes)?;
    validate_frame_payload_limit("max_write_bytes", policy.max_write_bytes)?;
    validate_frame_payload_limit("max_output_bytes", policy.max_output_bytes)
}

fn validate_frame_payload_limit(name: &str, value: u64) -> Result<(), ProtocolError> {
    let worst_case_frame = value
        .checked_mul(6)
        .and_then(|encoded| encoded.checked_add(FRAME_METADATA_RESERVE_BYTES));
    if match worst_case_frame {
        Some(bytes) => bytes > MAX_FRAME_BYTES as u64,
        None => true,
    } {
        return Err(ProtocolError::new(
            ErrorCode::InvalidRequest,
            format!(
                "hard policy {name}={value} can exceed the {MAX_FRAME_BYTES}-byte encoded frame limit"
            ),
        ));
    }
    Ok(())
}

fn validate_client_policy(policy: &ClientPolicy) -> Result<(), ProtocolError> {
    if let Some(patterns) = &policy.readable {
        validate_patterns(patterns)?;
    }
    if let Some(patterns) = &policy.writable {
        validate_patterns(patterns)?;
    }
    if let Some(names) = &policy.allowed_env_refs {
        for name in names {
            validate_env_name(name)?;
        }
    }
    validate_patterns(&policy.denied)
}

fn validate_patterns(patterns: &[String]) -> Result<(), ProtocolError> {
    for pattern in patterns {
        if pattern != "**" {
            Pattern::new(pattern).map_err(|error| {
                ProtocolError::new(
                    ErrorCode::InvalidRequest,
                    format!("invalid policy glob {pattern:?}: {error}"),
                )
            })?;
        }
    }
    Ok(())
}

fn matches_all_layers(layers: &[Vec<String>], path: &str) -> bool {
    !layers.is_empty() && layers.iter().all(|patterns| matches_any(patterns, path))
}

fn matches_any(patterns: &[String], path: &str) -> bool {
    let options = MatchOptions {
        case_sensitive: true,
        require_literal_separator: false,
        require_literal_leading_dot: false,
    };
    patterns.iter().any(|pattern| {
        pattern == "**"
            || Pattern::new(pattern)
                .map(|pattern| pattern.matches_with(path, options))
                .unwrap_or(false)
    })
}

fn file_kind(metadata: &std::fs::Metadata) -> FileKind {
    let file_type = metadata.file_type();
    if file_type.is_symlink() {
        FileKind::Symlink
    } else if file_type.is_file() {
        FileKind::File
    } else if file_type.is_dir() {
        FileKind::Directory
    } else {
        FileKind::Other
    }
}

fn open_regular_file(path: &Path) -> Result<File, ProtocolError> {
    let metadata = path
        .symlink_metadata()
        .map_err(|error| map_path_io(path, error))?;
    if metadata.file_type().is_symlink() {
        return Err(symlink_error(path));
    }
    if !metadata.is_file() {
        return Err(ProtocolError::new(
            ErrorCode::InvalidPath,
            "target must be a regular file",
        ));
    }

    let mut options = OpenOptions::new();
    options.read(true);
    #[cfg(unix)]
    {
        use std::os::unix::fs::OpenOptionsExt;
        options.custom_flags(libc::O_CLOEXEC | libc::O_NOFOLLOW);
    }
    let file = options.open(path).map_err(|error| {
        #[cfg(unix)]
        if error.raw_os_error() == Some(libc::ELOOP) {
            return symlink_error(path);
        }
        map_path_io(path, error)
    })?;
    let opened_metadata = file.metadata().map_err(|error| map_path_io(path, error))?;
    if !opened_metadata.is_file() {
        return Err(ProtocolError::new(
            ErrorCode::InvalidPath,
            "target changed before it could be opened as a regular file",
        ));
    }
    Ok(file)
}

fn read_utf8_range(
    file: &mut File,
    offset: u64,
    length: u64,
    total_bytes: u64,
) -> Result<Vec<u8>, ProtocolError> {
    if offset < total_bytes {
        file.seek(SeekFrom::Start(offset))
            .map_err(|error| io_error("seek file for UTF-8 boundary check", error))?;
        let mut first = [0_u8; 1];
        file.read_exact(&mut first)
            .map_err(|error| io_error("read file for UTF-8 boundary check", error))?;
        if first[0] & 0b1100_0000 == 0b1000_0000 {
            return Err(ProtocolError::new(
                ErrorCode::InvalidUtf8,
                "read_range offset is not on a UTF-8 character boundary",
            ));
        }
    }
    file.seek(SeekFrom::Start(offset))
        .map_err(|error| io_error("seek file", error))?;
    if length == 0 || offset >= total_bytes {
        return Ok(Vec::new());
    }

    let mut bytes = Vec::with_capacity(length.min(64 * 1024) as usize);
    Read::take(&mut *file, length)
        .read_to_end(&mut bytes)
        .map_err(|error| io_error("read file range", error))?;
    loop {
        match std::str::from_utf8(&bytes) {
            Ok(_) => return Ok(bytes),
            Err(error) if error.error_len().is_some() => {
                return Err(ProtocolError::new(
                    ErrorCode::InvalidUtf8,
                    "file contains invalid UTF-8",
                ));
            }
            Err(error) if error.valid_up_to() > 0 => {
                bytes.truncate(error.valid_up_to());
                return Ok(bytes);
            }
            Err(_)
                if bytes.len() < 4 && offset.saturating_add(bytes.len() as u64) < total_bytes =>
            {
                let mut next = [0_u8; 1];
                file.read_exact(&mut next)
                    .map_err(|error| io_error("complete UTF-8 character", error))?;
                bytes.push(next[0]);
            }
            Err(_) => {
                return Err(ProtocolError::new(
                    ErrorCode::InvalidUtf8,
                    "file ends with an incomplete UTF-8 character",
                ));
            }
        }
    }
}

fn hash_regular_file(path: &Path) -> Result<String, ProtocolError> {
    let mut file = open_regular_file(path)?;
    let mut hasher = Sha256::new();
    let mut buffer = [0_u8; 64 * 1024];
    loop {
        let count = file
            .read(&mut buffer)
            .map_err(|error| io_error("hash file", error))?;
        if count == 0 {
            break;
        }
        hasher.update(&buffer[..count]);
    }
    Ok(hex_bytes(&hasher.finalize()))
}

fn sha256_bytes(bytes: &[u8]) -> String {
    hex_bytes(&Sha256::digest(bytes))
}

fn hex_bytes(bytes: &[u8]) -> String {
    const HEX: &[u8; 16] = b"0123456789abcdef";
    let mut encoded = String::with_capacity(bytes.len().saturating_mul(2));
    for byte in bytes {
        encoded.push(HEX[(byte >> 4) as usize] as char);
        encoded.push(HEX[(byte & 0x0f) as usize] as char);
    }
    encoded
}

fn snapshot_digest(entries: &[SnapshotEntry]) -> String {
    let mut hasher = Sha256::new();
    for entry in entries {
        hasher.update((entry.path.len() as u64).to_be_bytes());
        hasher.update(entry.path.as_bytes());
        let kind = match entry.kind {
            FileKind::File => 1,
            FileKind::Directory => 2,
            FileKind::Symlink => 3,
            FileKind::Other => 4,
        };
        hasher.update([kind]);
        hasher.update(entry.bytes.to_be_bytes());
        if let Some(digest) = &entry.sha256 {
            hasher.update(digest.as_bytes());
        }
    }
    hex_bytes(&hasher.finalize())
}

fn sync_directory(path: &Path) -> Result<(), ProtocolError> {
    File::open(path)
        .and_then(|directory| directory.sync_all())
        .map_err(|error| io_error("sync directory", error))
}

fn ensure_private_child_directory(parent: &Path, name: &str) -> Result<PathBuf, ProtocolError> {
    let path = parent.join(name);
    match path.symlink_metadata() {
        Ok(metadata) => {
            if metadata.file_type().is_symlink() || !metadata.is_dir() {
                return Err(ProtocolError::new(
                    ErrorCode::SymlinkRejected,
                    "internal runtime path must be a real directory",
                ));
            }
        }
        Err(error) if error.kind() == std::io::ErrorKind::NotFound => {
            std::fs::create_dir(&path)
                .map_err(|error| io_error("create internal runtime directory", error))?;
            set_private_directory_permissions(&path)?;
            sync_directory(parent)?;
        }
        Err(error) => return Err(map_path_io(&path, error)),
    }
    Ok(path)
}

fn reject_symlink(path: &Path) -> Result<(), ProtocolError> {
    let metadata = path
        .symlink_metadata()
        .map_err(|error| map_path_io(path, error))?;
    if metadata.file_type().is_symlink() {
        Err(symlink_error(path))
    } else {
        Ok(())
    }
}

fn reject_symlink_if_present(path: &Path) -> Result<(), ProtocolError> {
    match path.symlink_metadata() {
        Ok(metadata) if metadata.file_type().is_symlink() => Err(symlink_error(path)),
        Ok(_) => Ok(()),
        Err(error) if error.kind() == std::io::ErrorKind::NotFound => Ok(()),
        Err(error) => Err(map_path_io(path, error)),
    }
}

fn symlink_error(path: &Path) -> ProtocolError {
    ProtocolError::new(
        ErrorCode::SymlinkRejected,
        format!("symlink not allowed: {}", path.display()),
    )
}

fn map_path_io(path: &Path, error: std::io::Error) -> ProtocolError {
    if error.kind() == std::io::ErrorKind::NotFound {
        ProtocolError::new(
            ErrorCode::NotFound,
            format!("path not found: {}", path.display()),
        )
    } else {
        io_error("filesystem operation", error)
    }
}

fn io_error(action: &str, error: std::io::Error) -> ProtocolError {
    ProtocolError::new(ErrorCode::Io, format!("{action}: {error}"))
}

fn serialization_error(error: serde_json::Error) -> ProtocolError {
    ProtocolError::new(ErrorCode::Internal, format!("serialize state: {error}"))
}

fn digest_serializable<T: Serialize>(value: &T) -> Result<String, ProtocolError> {
    let bytes = serde_json::to_vec(value).map_err(serialization_error)?;
    let digest = Sha256::digest(bytes);
    Ok(digest.iter().map(|byte| format!("{byte:02x}")).collect())
}

fn digest_exec_request(request: &ExecRequest) -> Result<String, ProtocolError> {
    // Environment values can be low-entropy secrets. Persisting an ordinary
    // hash of them would still create an offline guessing oracle, so only
    // environment names participate in the idempotency fingerprint.
    let mut redacted = request.clone();
    for value in redacted.env.values_mut() {
        value.clear();
        value.push_str("<redacted>");
    }
    digest_serializable(&redacted)
}

fn redact_values(text: &mut String, values: &[String]) {
    for value in values {
        if !value.is_empty() && text.contains(value) {
            let replacement = "*".repeat(value.chars().count());
            *text = text.replace(value, &replacement);
        }
    }
}

fn policy_digest(policy: &EffectivePolicy) -> Result<String, ProtocolError> {
    digest_serializable(policy)
}

fn atomic_json_write<T: Serialize>(path: &Path, value: &T) -> Result<(), ProtocolError> {
    let parent = path.parent().ok_or_else(|| {
        ProtocolError::new(ErrorCode::Internal, "state path has no parent directory")
    })?;
    let temp_path = parent.join(format!(".klintcode-state-{}.tmp", Uuid::new_v4()));
    let result = (|| -> Result<(), ProtocolError> {
        let bytes = serde_json::to_vec(value).map_err(serialization_error)?;
        let mut file = OpenOptions::new()
            .write(true)
            .create_new(true)
            .open(&temp_path)
            .map_err(|error| io_error("create temporary state file", error))?;
        file.write_all(&bytes)
            .map_err(|error| io_error("write state file", error))?;
        file.sync_all()
            .map_err(|error| io_error("sync state file", error))?;
        std::fs::rename(&temp_path, path)
            .map_err(|error| io_error("atomically replace state file", error))?;
        Ok(())
    })();
    if result.is_err() {
        let _ = std::fs::remove_file(&temp_path);
    }
    result
}

fn unix_timestamp_ms() -> u128 {
    SystemTime::now()
        .duration_since(UNIX_EPOCH)
        .unwrap_or_default()
        .as_millis()
}

#[cfg(unix)]
fn set_private_directory_permissions(path: &Path) -> Result<(), ProtocolError> {
    use std::os::unix::fs::PermissionsExt;
    std::fs::set_permissions(path, std::fs::Permissions::from_mode(0o700))
        .map_err(|error| io_error("set directory permissions", error))
}

#[cfg(not(unix))]
fn set_private_directory_permissions(_path: &Path) -> Result<(), ProtocolError> {
    Ok(())
}

#[cfg(test)]
mod tests {
    use super::*;
    use tempfile::TempDir;

    fn frame(request_id: &str, request: RunnerRequest) -> RequestFrame {
        RequestFrame {
            request_id: request_id.into(),
            protocol_version: PROTOCOL_VERSION,
            request,
        }
    }

    fn hello() -> RequestFrame {
        frame(
            "hello",
            RunnerRequest::Hello(HelloRequest {
                client_name: "runner-test".into(),
                client_version: "1".into(),
            }),
        )
    }

    async fn attached_runner(
        root: &TempDir,
        hard_policy: HardPolicy,
        client_policy: ClientPolicy,
    ) -> (RemoteRunner, SessionInfo) {
        attached_runner_with_environment(root, hard_policy, client_policy, BTreeMap::new()).await
    }

    async fn attached_runner_with_environment(
        root: &TempDir,
        hard_policy: HardPolicy,
        client_policy: ClientPolicy,
        environment_refs: BTreeMap<String, EnvironmentRef>,
    ) -> (RemoteRunner, SessionInfo) {
        let mut runner = RemoteRunner::new(RunnerConfig::new(root.path(), hard_policy)).unwrap();
        assert!(matches!(
            runner.handle_frame(hello()).await.outcome,
            ResponseOutcome::Ok { .. }
        ));
        let response = runner
            .handle_frame(frame(
                "session",
                RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
                    session_id: Some("session-1".into()),
                    require_existing: false,
                    policy: client_policy,
                    environment_refs,
                }),
            ))
            .await;
        match response.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::Session(info),
            } => (runner, info),
            outcome => panic!("session failed: {outcome:?}"),
        }
    }

    fn session_directory(root: &TempDir, info: &SessionInfo) -> PathBuf {
        root.path().join("sessions").join(&info.session_id)
    }

    #[cfg(unix)]
    fn install_process_fixture(directory: &Path) -> PathBuf {
        let source = directory.join("runner_fixture.rs");
        let executable = directory.join("runner-fixture");
        std::fs::write(
            &source,
            r#"
use std::path::Path;
use std::io::Write;
use std::process::{Command, Stdio};
use std::time::Duration;

unsafe extern "C" {
    fn setsid() -> i32;
}

fn main() {
    let args: Vec<String> = std::env::args().collect();
    if args.get(1).map(String::as_str) == Some("output") {
        println!("first-output");
        std::io::stdout().flush().unwrap();
        std::thread::sleep(Duration::from_millis(400));
        eprintln!("second-output");
        return;
    }
    if args.get(1).map(String::as_str) == Some("child") {
        let marker = &args[2];
        let mode = &args[3];
        if mode == "escape" {
            unsafe { setsid(); }
        }
        std::fs::write(format!("{marker}.beat"), "0").unwrap();
        std::fs::write(marker, std::process::id().to_string()).unwrap();
        for tick in 1_u64.. {
            std::fs::write(format!("{marker}.beat"), tick.to_string()).unwrap();
            std::thread::sleep(Duration::from_millis(20));
        }
        return;
    }

    let mode = args.get(1).expect("mode");
    let marker = args.get(2).expect("marker");
    let mut child = Command::new(std::env::current_exe().unwrap());
    child
        .arg("child")
        .arg(marker)
        .arg(mode)
        .stdin(Stdio::null())
        .stdout(Stdio::inherit())
        .stderr(Stdio::inherit());
    child.spawn().unwrap();
    for _ in 0..200 {
        if Path::new(marker).exists() {
            if mode == "hold" {
                std::thread::sleep(Duration::from_secs(60));
            }
            return;
        }
        std::thread::sleep(Duration::from_millis(5));
    }
    std::process::exit(3);
}
"#,
        )
        .unwrap();
        let status = std::process::Command::new("rustc")
            .arg("--edition=2021")
            .arg(&source)
            .arg("-o")
            .arg(&executable)
            .status()
            .unwrap();
        assert!(status.success(), "failed to compile process fixture");
        executable
    }

    #[cfg(unix)]
    fn install_rustup_proxy_fixture(directory: &Path) -> (PathBuf, PathBuf) {
        use std::os::unix::fs::symlink;

        let bin = directory.join("host-bin");
        std::fs::create_dir(&bin).unwrap();
        let source = directory.join("rustup_proxy_fixture.rs");
        let target = bin.join("rustup");
        std::fs::write(
            &source,
            r#"
use std::path::Path;
use std::process::Command;

fn executable_name() -> String {
    let value = std::env::args_os().next().unwrap();
    Path::new(&value).file_name().unwrap().to_string_lossy().into_owned()
}

fn main() {
    let name = executable_name();
    if name == "cargo" {
        let plugin = Command::new("cargo-teaql").arg("--probe").output().unwrap();
        assert!(plugin.status.success());
        println!(
            "{}|{}|{}|{}",
            name,
            String::from_utf8(plugin.stdout).unwrap().trim(),
            std::env::var("CARGO_HOME").unwrap(),
            std::env::var("HOME").unwrap(),
        );
    } else {
        println!("{name}");
    }
}
"#,
        )
        .unwrap();
        let status = std::process::Command::new("rustc")
            .arg("--edition=2021")
            .arg(&source)
            .arg("-o")
            .arg(&target)
            .status()
            .unwrap();
        assert!(status.success(), "failed to compile rustup proxy fixture");
        let cargo = bin.join("cargo");
        symlink("rustup", &cargo).unwrap();
        symlink("rustup", bin.join("cargo-teaql")).unwrap();
        (target, cargo)
    }

    #[cfg(unix)]
    fn fixture_request(
        operation_id: &str,
        mode: &str,
        marker: &str,
        _workspace: &Path,
        timeout_secs: u64,
    ) -> ExecRequest {
        ExecRequest {
            operation_id: operation_id.into(),
            program: "runner-fixture".into(),
            argv: vec![mode.into(), marker.into()],
            cwd: ".".into(),
            env: BTreeMap::new(),
            env_refs: Vec::new(),
            timeout_secs: Some(timeout_secs),
            max_output_bytes: None,
            stream_output: false,
        }
    }

    #[cfg(unix)]
    async fn wait_for_fixture_marker(workspace: &Path, marker: &str) -> i32 {
        let marker = workspace.join(marker);
        for _ in 0..200 {
            if let Ok(value) = std::fs::read_to_string(&marker) {
                return value.trim().parse().unwrap();
            }
            tokio::time::sleep(Duration::from_millis(5)).await;
        }
        panic!("fixture marker was not created");
    }

    #[cfg(unix)]
    async fn assert_fixture_stopped(workspace: &Path, marker: &str) {
        let heartbeat = workspace.join(format!("{marker}.beat"));
        let before = std::fs::read_to_string(&heartbeat).unwrap();
        tokio::time::sleep(Duration::from_millis(120)).await;
        let after = std::fs::read_to_string(&heartbeat).unwrap();
        assert_eq!(before, after, "fixture descendant is still running");
    }

    #[cfg(unix)]
    fn kill_fixture(pid: i32) {
        unsafe {
            libc::kill(pid, libc::SIGKILL);
        }
    }

    async fn wait_for_terminal(runner: &mut RemoteRunner, operation_id: &str) -> OperationRecord {
        for attempt in 0..500 {
            let response = runner
                .handle_frame(frame(
                    &format!("status-{attempt}"),
                    RunnerRequest::ExecStatus(OperationStatusRequest {
                        operation_id: operation_id.into(),
                    }),
                ))
                .await;
            match response.outcome {
                ResponseOutcome::Ok {
                    result: RunnerResponse::OperationStatus(operation),
                } if operation.state != OperationState::Running => return operation,
                ResponseOutcome::Ok { .. } => {}
                outcome => panic!("status failed: {outcome:?}"),
            }
            tokio::time::sleep(Duration::from_millis(10)).await;
        }
        panic!("operation did not reach a terminal state");
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn rustup_proxy_keeps_entry_name_and_uses_safe_path_and_private_cargo_home() {
        let root = TempDir::new().unwrap();
        let (target, cargo) = install_rustup_proxy_fixture(root.path());
        let (mut runner, info) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["cargo".into()],
                program_paths: BTreeMap::from([(
                    "cargo".into(),
                    cargo.to_string_lossy().into_owned(),
                )]),
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;

        let identity = info
            .effective_policy
            .program_identities
            .get("cargo")
            .unwrap();
        let cargo_entry = std::fs::canonicalize(cargo.parent().unwrap())
            .unwrap()
            .join("cargo");
        assert_eq!(identity.entry_path, cargo_entry.to_string_lossy());
        assert_eq!(
            identity.canonical_target,
            std::fs::canonicalize(&target).unwrap().to_string_lossy()
        );
        assert_eq!(identity.target_sha256, hash_regular_file(&target).unwrap());

        let start = runner
            .handle_frame(frame(
                "proxy-start",
                RunnerRequest::ExecStart(ExecRequest {
                    operation_id: "proxy-op".into(),
                    program: "cargo".into(),
                    argv: Vec::new(),
                    cwd: ".".into(),
                    env: BTreeMap::new(),
                    env_refs: Vec::new(),
                    timeout_secs: Some(5),
                    max_output_bytes: None,
                    stream_output: false,
                }),
            ))
            .await;
        assert!(matches!(start.outcome, ResponseOutcome::Ok { .. }));
        let terminal = wait_for_terminal(&mut runner, "proxy-op").await;
        assert_eq!(terminal.state, OperationState::Exited);
        let output = terminal.result.unwrap().stdout;
        let fields: Vec<&str> = output.trim().split('|').collect();
        assert_eq!(&fields[..2], ["cargo", "cargo-teaql"]);
        let workspace =
            std::fs::canonicalize(session_directory(&root, &info).join("workspace")).unwrap();
        assert_eq!(
            Path::new(fields[2]),
            workspace.join(".klintcode/cargo-home")
        );
        assert_eq!(Path::new(fields[3]), workspace);
        assert!(workspace.join(".klintcode/cargo-home").is_dir());

        std::fs::write(&target, b"replaced executable").unwrap();
        let replaced = runner
            .handle_frame(frame(
                "proxy-replaced",
                RunnerRequest::ExecStart(ExecRequest {
                    operation_id: "proxy-op-2".into(),
                    program: "cargo".into(),
                    argv: Vec::new(),
                    cwd: ".".into(),
                    env: BTreeMap::new(),
                    env_refs: Vec::new(),
                    timeout_secs: Some(5),
                    max_output_bytes: None,
                    stream_output: false,
                }),
            ))
            .await;
        assert!(matches!(
            replaced.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::DeniedByPolicy,
                    ..
                }
            }
        ));
    }

    #[tokio::test]
    async fn remote_hello_is_required_before_session() {
        let root = TempDir::new().unwrap();
        let mut runner =
            RemoteRunner::new(RunnerConfig::new(root.path(), HardPolicy::default())).unwrap();
        let response = runner
            .handle_frame(frame(
                "early",
                RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
                    session_id: None,
                    require_existing: false,
                    policy: ClientPolicy::default(),
                    environment_refs: BTreeMap::new(),
                }),
            ))
            .await;
        assert!(matches!(
            response.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::HelloRequired,
                    ..
                }
            }
        ));
    }

    #[tokio::test]
    async fn remote_hello_and_session_attach_are_single_transition_states() {
        let root = TempDir::new().unwrap();
        let mut runner =
            RemoteRunner::new(RunnerConfig::new(root.path(), HardPolicy::default())).unwrap();
        assert!(matches!(
            runner.handle_frame(hello()).await.outcome,
            ResponseOutcome::Ok { .. }
        ));
        let duplicate_hello = runner.handle_frame(hello()).await;
        assert!(matches!(
            duplicate_hello.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::InvalidRequest,
                    ..
                }
            }
        ));

        let attach = RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
            session_id: Some("single-session".into()),
            require_existing: false,
            policy: ClientPolicy::default(),
            environment_refs: BTreeMap::new(),
        });
        assert!(matches!(
            runner
                .handle_frame(frame("attach", attach.clone()))
                .await
                .outcome,
            ResponseOutcome::Ok { .. }
        ));
        let duplicate_attach = runner.handle_frame(frame("attach-again", attach)).await;
        assert!(matches!(
            duplicate_attach.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::SessionBusy,
                    ..
                }
            }
        ));
    }

    #[test]
    fn remote_hard_policy_limits_must_fit_encoded_frames() {
        let root = TempDir::new().unwrap();
        let oversized = (MAX_FRAME_BYTES as u64 / 6) + 1;
        let result = RemoteRunner::new(RunnerConfig::new(
            root.path(),
            HardPolicy {
                max_write_bytes: oversized,
                ..HardPolicy::default()
            },
        ));
        assert!(matches!(
            result,
            Err(ProtocolError {
                code: ErrorCode::InvalidRequest,
                ..
            })
        ));
    }

    #[tokio::test]
    async fn remote_require_existing_never_creates_replacement_session() {
        let root = TempDir::new().unwrap();
        let mut runner =
            RemoteRunner::new(RunnerConfig::new(root.path(), HardPolicy::default())).unwrap();
        runner.handle_frame(hello()).await;
        let response = runner
            .handle_frame(frame(
                "attach-missing",
                RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
                    session_id: Some("missing-session".into()),
                    require_existing: true,
                    policy: ClientPolicy::default(),
                    environment_refs: BTreeMap::new(),
                }),
            ))
            .await;
        assert!(matches!(
            response.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::NotFound,
                    ..
                }
            }
        ));
        assert!(!root.path().join("sessions/missing-session").exists());
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_concurrent_attach_is_retryable_and_does_not_mark_operations_stale() {
        let root = TempDir::new().unwrap();
        let (holder, info) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        let operation = OperationRecord {
            operation_id: "still-running".into(),
            request_digest: "digest".into(),
            state: OperationState::Running,
            result: None,
            message: None,
        };
        let operation_path = session_directory(&root, &info)
            .join("operations")
            .join("still-running.json");
        atomic_json_write(&operation_path, &operation).unwrap();

        let mut contender =
            RemoteRunner::new(RunnerConfig::new(root.path(), HardPolicy::default())).unwrap();
        contender.handle_frame(hello()).await;
        let attach_request = RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
            session_id: Some(info.session_id.clone()),
            require_existing: true,
            policy: ClientPolicy::default(),
            environment_refs: BTreeMap::new(),
        });
        let busy = contender
            .handle_frame(frame("busy", attach_request.clone()))
            .await;
        match busy.outcome {
            ResponseOutcome::Error { error } => {
                assert_eq!(error.code, ErrorCode::SessionBusy);
                assert!(error.retryable);
            }
            outcome => panic!("concurrent attach unexpectedly succeeded: {outcome:?}"),
        }
        let unchanged: OperationRecord =
            serde_json::from_slice(&std::fs::read(&operation_path).unwrap()).unwrap();
        assert_eq!(unchanged.state, OperationState::Running);

        drop(holder);
        let attached = contender.handle_frame(frame("retry", attach_request)).await;
        assert!(matches!(attached.outcome, ResponseOutcome::Ok { .. }));
        let interrupted: OperationRecord =
            serde_json::from_slice(&std::fs::read(&operation_path).unwrap()).unwrap();
        assert_eq!(interrupted.state, OperationState::Interrupted);
    }

    #[tokio::test]
    async fn remote_reattach_applies_current_hard_policy_to_old_session() {
        let root = TempDir::new().unwrap();
        let (first, first_info) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["cargo".into(), "git".into()],
                readable: vec!["**".into()],
                max_timeout_secs: 300,
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;
        drop(first);
        let (_second, second_info) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["cargo".into()],
                readable: vec!["src/**".into()],
                max_timeout_secs: 10,
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;
        assert!(
            first_info
                .effective_policy
                .allowed_programs
                .contains(&"git".into())
        );
        assert_eq!(second_info.effective_policy.allowed_programs, vec!["cargo"]);
        assert_eq!(second_info.effective_policy.max_timeout_secs, 10);
        assert!(
            second_info
                .effective_policy
                .readable_layers
                .contains(&vec!["src/**".into()])
        );
    }

    #[tokio::test]
    async fn remote_file_operations_are_atomic_bounded_and_policy_checked() {
        let root = TempDir::new().unwrap();
        let (mut runner, _) = attached_runner(
            &root,
            HardPolicy {
                writable: vec!["src/**".into()],
                readable: vec!["src/**".into()],
                max_read_bytes: 8,
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;

        let response = runner
            .handle_frame(frame(
                "write",
                RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                    path: "src/main.rs".into(),
                    content: "12345678".into(),
                    create_parents: true,
                }),
            ))
            .await;
        assert!(matches!(response.outcome, ResponseOutcome::Ok { .. }));

        let response = runner
            .handle_frame(frame(
                "read",
                RunnerRequest::FsReadRange(ReadRangeRequest {
                    path: "src/main.rs".into(),
                    offset: 2,
                    length: 4,
                }),
            ))
            .await;
        match response.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::ReadRange(read),
            } => {
                assert_eq!(read.content, "3456");
                assert!(!read.eof);
            }
            outcome => panic!("read failed: {outcome:?}"),
        }

        let denied = runner
            .handle_frame(frame(
                "denied",
                RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                    path: "outside.txt".into(),
                    content: "no".into(),
                    create_parents: false,
                }),
            ))
            .await;
        assert!(matches!(
            denied.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::DeniedByPolicy,
                    ..
                }
            }
        ));
    }

    #[tokio::test]
    async fn remote_utf8_ranges_make_progress_without_splitting_characters() {
        let root = TempDir::new().unwrap();
        let (mut runner, _) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        let text = "a你b好c";
        let write = runner
            .handle_frame(frame(
                "utf8-write",
                RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                    path: "unicode.txt".into(),
                    content: text.into(),
                    create_parents: false,
                }),
            ))
            .await;
        assert!(matches!(write.outcome, ResponseOutcome::Ok { .. }));

        let mut offset = 0_u64;
        let mut reconstructed = String::new();
        loop {
            let response = runner
                .handle_frame(frame(
                    &format!("utf8-{offset}"),
                    RunnerRequest::FsReadRange(ReadRangeRequest {
                        path: "unicode.txt".into(),
                        offset,
                        length: 2,
                    }),
                ))
                .await;
            let ResponseOutcome::Ok {
                result: RunnerResponse::ReadRange(read),
            } = response.outcome
            else {
                panic!("UTF-8 page failed: {:?}", response.outcome);
            };
            if !read.eof {
                assert!(read.bytes > 0, "non-EOF UTF-8 page made no progress");
            }
            reconstructed.push_str(&read.content);
            offset = offset.saturating_add(read.bytes);
            if read.eof {
                break;
            }
        }
        assert_eq!(reconstructed, text);
        assert_eq!(offset, text.len() as u64);

        let split_offset = runner
            .handle_frame(frame(
                "utf8-split-offset",
                RunnerRequest::FsReadRange(ReadRangeRequest {
                    path: "unicode.txt".into(),
                    offset: 2,
                    length: 2,
                }),
            ))
            .await;
        assert!(matches!(
            split_offset.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::InvalidUtf8,
                    ..
                }
            }
        ));
    }

    #[tokio::test]
    async fn remote_workspace_manifest_search_artifact_and_cas_are_bounded() {
        let root = TempDir::new().unwrap();
        let (mut runner, info) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        for (request_id, path, content) in [
            ("write-a", "project/a.txt", "alpha\nneedle here\n"),
            ("write-b", "project/sub/b.txt", "beta\n"),
        ] {
            let response = runner
                .handle_frame(frame(
                    request_id,
                    RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                        path: path.into(),
                        content: content.into(),
                        create_parents: true,
                    }),
                ))
                .await;
            assert!(matches!(response.outcome, ResponseOutcome::Ok { .. }));
        }
        let workspace = session_directory(&root, &info).join("workspace");
        std::fs::write(workspace.join("project/blob.bin"), [0_u8, 0xff, 0x10, 0x20]).unwrap();

        let missing = runner
            .handle_frame(frame(
                "stat-missing",
                RunnerRequest::FsStat(StatRequest {
                    path: "project/missing.txt".into(),
                    include_sha256: true,
                }),
            ))
            .await;
        assert!(matches!(
            missing.outcome,
            ResponseOutcome::Ok {
                result: RunnerResponse::Stat(StatResponse { exists: false, .. })
            }
        ));

        let stat = runner
            .handle_frame(frame(
                "stat-a",
                RunnerRequest::FsStat(StatRequest {
                    path: "project/a.txt".into(),
                    include_sha256: true,
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::Stat(stat),
        } = stat.outcome
        else {
            panic!("stat failed: {:?}", stat.outcome);
        };
        assert_eq!(stat.kind, Some(FileKind::File));
        assert_eq!(stat.sha256, Some(sha256_bytes(b"alpha\nneedle here\n")));

        let walk = runner
            .handle_frame(frame(
                "walk",
                RunnerRequest::FsWalk(WalkRequest {
                    path: "project".into(),
                    max_entries: Some(16),
                    max_depth: None,
                    excluded_paths: Vec::new(),
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::Walk(walk),
        } = walk.outcome
        else {
            panic!("walk failed: {:?}", walk.outcome);
        };
        assert!(!walk.truncated);
        assert_eq!(
            walk.entries
                .iter()
                .map(|entry| entry.path.as_str())
                .collect::<Vec<_>>(),
            [
                "project/a.txt",
                "project/blob.bin",
                "project/sub",
                "project/sub/b.txt"
            ]
        );

        let excluded_walk = runner
            .handle_frame(frame(
                "walk-excluded",
                RunnerRequest::FsWalk(WalkRequest {
                    path: "project".into(),
                    max_entries: Some(16),
                    max_depth: None,
                    excluded_paths: vec!["project/sub".into()],
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::Walk(excluded_walk),
        } = excluded_walk.outcome
        else {
            panic!("excluded walk failed: {:?}", excluded_walk.outcome);
        };
        assert!(
            excluded_walk
                .entries
                .iter()
                .all(|entry| !entry.path.starts_with("project/sub"))
        );

        let search = runner
            .handle_frame(frame(
                "search",
                RunnerRequest::FsSearch(SearchRequest {
                    path: "project".into(),
                    query: "needle".into(),
                    max_matches: Some(4),
                    max_file_bytes: Some(64),
                    excluded_paths: Vec::new(),
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::Search(search),
        } = search.outcome
        else {
            panic!("search failed: {:?}", search.outcome);
        };
        assert_eq!(search.matches.len(), 1);
        assert_eq!(search.matches[0].path, "project/a.txt");
        assert_eq!(search.matches[0].line, 2);
        assert_eq!(search.matches[0].column, 1);
        assert_eq!(search.files_skipped, 1, "binary file should be skipped");

        let excluded = runner
            .handle_frame(frame(
                "search-excluded",
                RunnerRequest::FsSearch(SearchRequest {
                    path: "project".into(),
                    query: "needle".into(),
                    max_matches: Some(4),
                    max_file_bytes: Some(64),
                    excluded_paths: vec!["project/a.txt".into()],
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::Search(excluded),
        } = excluded.outcome
        else {
            panic!("excluded search failed: {:?}", excluded.outcome);
        };
        assert!(excluded.matches.is_empty());

        let first_snapshot = runner
            .handle_frame(frame(
                "snapshot-1",
                RunnerRequest::FsSnapshot(SnapshotRequest {
                    path: "project".into(),
                    max_entries: Some(16),
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::Snapshot(first_snapshot),
        } = first_snapshot.outcome
        else {
            panic!("snapshot failed: {:?}", first_snapshot.outcome);
        };
        assert!(!first_snapshot.truncated);
        let repeated_snapshot = runner
            .handle_frame(frame(
                "snapshot-2",
                RunnerRequest::FsSnapshot(SnapshotRequest {
                    path: "project".into(),
                    max_entries: Some(16),
                }),
            ))
            .await;
        assert!(matches!(
            repeated_snapshot.outcome,
            ResponseOutcome::Ok {
                result: RunnerResponse::Snapshot(SnapshotResponse { tree_sha256, .. })
            } if tree_sha256 == first_snapshot.tree_sha256
        ));

        let artifact = runner
            .handle_frame(frame(
                "artifact",
                RunnerRequest::ArtifactReadChunk(ArtifactReadChunkRequest {
                    path: "project/blob.bin".into(),
                    offset: 1,
                    length: 2,
                }),
            ))
            .await;
        assert!(matches!(
            artifact.outcome,
            ResponseOutcome::Ok {
                result: RunnerResponse::ArtifactChunk(ArtifactReadChunkResponse {
                    data_hex,
                    chunk_sha256,
                    eof: false,
                    ..
                })
            } if data_hex == "ff10" && chunk_sha256 == sha256_bytes(&[0xff, 0x10])
        ));

        let create = runner
            .handle_frame(frame(
                "cas-create",
                RunnerRequest::FsWriteCas(WriteCasRequest {
                    path: "project/cas.txt".into(),
                    content: "first".into(),
                    create_parents: false,
                    expected: ExpectedFileState::Missing,
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::WriteCas(created),
        } = create.outcome
        else {
            panic!("CAS create failed: {:?}", create.outcome);
        };
        let conflict = runner
            .handle_frame(frame(
                "cas-conflict",
                RunnerRequest::FsWriteCas(WriteCasRequest {
                    path: "project/cas.txt".into(),
                    content: "wrong".into(),
                    create_parents: false,
                    expected: ExpectedFileState::Missing,
                }),
            ))
            .await;
        assert!(matches!(
            conflict.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::CasMismatch,
                    ..
                }
            }
        ));
        let update = runner
            .handle_frame(frame(
                "cas-update",
                RunnerRequest::FsWriteCas(WriteCasRequest {
                    path: "project/cas.txt".into(),
                    content: "second".into(),
                    create_parents: false,
                    expected: ExpectedFileState::Sha256 {
                        value: created.sha256,
                    },
                }),
            ))
            .await;
        assert!(matches!(update.outcome, ResponseOutcome::Ok { .. }));
        assert_eq!(
            std::fs::read_to_string(workspace.join("project/cas.txt")).unwrap(),
            "second"
        );
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_session_sqlite_env_ref_is_stable_private_and_explicit() {
        let root = TempDir::new().unwrap();
        let database_name = "SCHOOL_SERVICE_DATABASE_URL";
        let references = BTreeMap::from([(database_name.into(), EnvironmentRef::SessionSqlite)]);
        let (mut runner, info) = attached_runner_with_environment(
            &root,
            HardPolicy {
                allowed_programs: vec!["printenv".into()],
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
            references,
        )
        .await;
        assert_eq!(info.environment_refs, [database_name]);

        let request = |operation_id: &str| ExecRequest {
            operation_id: operation_id.into(),
            program: "printenv".into(),
            argv: vec![database_name.into()],
            cwd: ".".into(),
            env: BTreeMap::new(),
            env_refs: vec![database_name.into()],
            timeout_secs: Some(5),
            max_output_bytes: None,
            stream_output: false,
        };
        let first = runner
            .handle_frame(frame(
                "sqlite-first",
                RunnerRequest::ExecStart(request("sqlite-op-1")),
            ))
            .await;
        assert!(matches!(first.outcome, ResponseOutcome::Ok { .. }));
        let first = wait_for_terminal(&mut runner, "sqlite-op-1").await;
        let first_url = first.result.unwrap().stdout.trim().to_owned();
        assert!(first_url.starts_with("sqlite://"));
        assert!(first_url.contains("/.klintcode/runtime/"));

        runner
            .handle_frame(frame(
                "sqlite-second",
                RunnerRequest::ExecStart(request("sqlite-op-2")),
            ))
            .await;
        let second = wait_for_terminal(&mut runner, "sqlite-op-2").await;
        assert_eq!(second.result.unwrap().stdout.trim(), first_url);

        let undeclared = runner
            .handle_frame(frame(
                "sqlite-undeclared",
                RunnerRequest::ExecStart(ExecRequest {
                    operation_id: "sqlite-op-3".into(),
                    env_refs: vec!["OTHER_DATABASE_URL".into()],
                    ..request("sqlite-op-3")
                }),
            ))
            .await;
        assert!(matches!(
            undeclared.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::DeniedByPolicy,
                    ..
                }
            }
        ));

        let snapshot = runner
            .handle_frame(frame(
                "sqlite-snapshot",
                RunnerRequest::FsSnapshot(SnapshotRequest {
                    path: ".".into(),
                    max_entries: None,
                }),
            ))
            .await;
        assert!(matches!(
            snapshot.outcome,
            ResponseOutcome::Ok {
                result: RunnerResponse::Snapshot(SnapshotResponse { entries, .. })
            } if entries.iter().all(|entry| {
                entry.path != ".klintcode/runtime"
                    && !entry.path.starts_with(".klintcode/runtime/")
                    && entry.path != ".klintcode/cargo-home"
                    && !entry.path.starts_with(".klintcode/cargo-home/")
            })
        ));

        let denied_root = TempDir::new().unwrap();
        let mut denied_runner =
            RemoteRunner::new(RunnerConfig::new(denied_root.path(), HardPolicy::default()))
                .unwrap();
        denied_runner.handle_frame(hello()).await;
        let secret_ref = denied_runner
            .handle_frame(frame(
                "secret-ref",
                RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
                    session_id: None,
                    require_existing: false,
                    policy: ClientPolicy::default(),
                    environment_refs: BTreeMap::from([(
                        "MIMO_API_KEY".into(),
                        EnvironmentRef::Host,
                    )]),
                }),
            ))
            .await;
        assert!(matches!(
            secret_ref.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::DeniedByPolicy,
                    ..
                }
            }
        ));
    }

    #[tokio::test]
    async fn default_policy_allows_pipeline_evidence_and_denies_runner_private_state() {
        let root = TempDir::new().unwrap();
        let (mut runner, _) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        let evidence = [
            (
                "attempts/attempt-01/build/.klintcode/assist/query-school.md",
                "assist",
            ),
            (
                "attempts/attempt-01/build/.klintcode/validation-evidence.json",
                "validation",
            ),
            (
                "attempts/attempt-01/build/.klintcode/followup-acceptance-report.json",
                "follow-up",
            ),
        ];

        for (index, (path, content)) in evidence.iter().enumerate() {
            let write = runner
                .handle_frame(frame(
                    &format!("pipeline-evidence-write-{index}"),
                    RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                        path: (*path).into(),
                        content: (*content).into(),
                        create_parents: true,
                    }),
                ))
                .await;
            assert!(
                matches!(&write.outcome, ResponseOutcome::Ok { .. }),
                "default policy rejected pipeline evidence {path}: {:?}",
                write.outcome
            );

            let read = runner
                .handle_frame(frame(
                    &format!("pipeline-evidence-read-{index}"),
                    RunnerRequest::FsReadRange(ReadRangeRequest {
                        path: (*path).into(),
                        offset: 0,
                        length: 128,
                    }),
                ))
                .await;
            assert!(
                matches!(
                    &read.outcome,
                    ResponseOutcome::Ok {
                        result: RunnerResponse::ReadRange(ReadRangeResponse { content: actual, .. })
                    } if actual == *content
                ),
                "default policy rejected reading pipeline evidence {path}: {:?}",
                read.outcome
            );
        }

        for (index, path) in [
            ".klintcode/runtime/forbidden.txt",
            ".klintcode/cargo-home/forbidden.txt",
        ]
        .iter()
        .enumerate()
        {
            let response = runner
                .handle_frame(frame(
                    &format!("runner-private-write-{index}"),
                    RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                        path: (*path).into(),
                        content: "forbidden".into(),
                        create_parents: true,
                    }),
                ))
                .await;
            assert!(matches!(
                response.outcome,
                ResponseOutcome::Error {
                    error: ProtocolError {
                        code: ErrorCode::DeniedByPolicy,
                        ..
                    }
                }
            ));
        }
    }

    #[test]
    fn oversized_success_response_is_replaced_by_bounded_error() {
        let response = ResponseFrame::success(
            "bounded",
            RunnerResponse::ReadRange(ReadRangeResponse {
                path: "large.txt".into(),
                offset: 0,
                bytes: (MAX_FRAME_BYTES + 1) as u64,
                eof: true,
                content: "x".repeat(MAX_FRAME_BYTES + 1),
            }),
        );
        let bounded = bound_response_frame(response);
        assert!(matches!(
            bounded.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::TooLarge,
                    ..
                }
            }
        ));
        assert!(serde_json::to_vec(&bounded).unwrap().len() <= MAX_FRAME_BYTES);
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_symlink_escape_is_rejected() {
        use std::os::unix::fs::symlink;

        let root = TempDir::new().unwrap();
        let outside = TempDir::new().unwrap();
        std::fs::write(outside.path().join("secret.txt"), "secret").unwrap();
        let (mut runner, info) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        symlink(
            outside.path(),
            session_directory(&root, &info).join("workspace/escape"),
        )
        .unwrap();
        let response = runner
            .handle_frame(frame(
                "read",
                RunnerRequest::FsReadRange(ReadRangeRequest {
                    path: "escape/secret.txt".into(),
                    offset: 0,
                    length: 6,
                }),
            ))
            .await;
        assert!(matches!(
            response.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::SymlinkRejected,
                    ..
                }
            }
        ));

        for (request_id, request) in [
            (
                "stat-escape",
                RunnerRequest::FsStat(StatRequest {
                    path: "escape/secret.txt".into(),
                    include_sha256: true,
                }),
            ),
            (
                "artifact-escape",
                RunnerRequest::ArtifactReadChunk(ArtifactReadChunkRequest {
                    path: "escape/secret.txt".into(),
                    offset: 0,
                    length: 6,
                }),
            ),
            (
                "cas-escape",
                RunnerRequest::FsWriteCas(WriteCasRequest {
                    path: "escape/new.txt".into(),
                    content: "no".into(),
                    create_parents: false,
                    expected: ExpectedFileState::Missing,
                }),
            ),
        ] {
            let response = runner.handle_frame(frame(request_id, request)).await;
            assert!(matches!(
                response.outcome,
                ResponseOutcome::Error {
                    error: ProtocolError {
                        code: ErrorCode::SymlinkRejected,
                        ..
                    }
                }
            ));
        }
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_duplicate_operation_replays_bounded_result() {
        let root = TempDir::new().unwrap();
        let (mut runner, _) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["printf".into()],
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;
        let request = ExecRequest {
            operation_id: "op-1".into(),
            program: "printf".into(),
            argv: vec!["hello".into()],
            cwd: ".".into(),
            env: BTreeMap::new(),
            env_refs: Vec::new(),
            timeout_secs: Some(5),
            max_output_bytes: Some(3),
            stream_output: false,
        };
        let first = runner
            .handle_frame(frame("first", RunnerRequest::ExecStart(request.clone())))
            .await;
        match first.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::Exec(operation),
            } => {
                assert!(!operation.replayed);
                assert_eq!(operation.operation.state, OperationState::Running);
            }
            outcome => panic!("start failed: {outcome:?}"),
        }
        let terminal = wait_for_terminal(&mut runner, "op-1").await;
        assert_eq!(terminal.state, OperationState::Exited);
        assert_eq!(terminal.result.as_ref().unwrap().stdout, "hel");
        assert!(terminal.result.as_ref().unwrap().stdout_truncated);

        let duplicate = runner
            .handle_frame(frame(
                "duplicate",
                RunnerRequest::ExecStart(request.clone()),
            ))
            .await;
        match duplicate.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::Exec(operation),
            } => {
                assert!(operation.replayed);
                assert_eq!(operation.operation, terminal);
            }
            outcome => panic!("duplicate failed: {outcome:?}"),
        }

        let mut changed = request;
        changed.argv = vec!["different".into()];
        let conflict = runner
            .handle_frame(frame("conflict", RunnerRequest::ExecStart(changed)))
            .await;
        assert!(matches!(
            conflict.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::OperationConflict,
                    ..
                }
            }
        ));
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_cancel_waits_for_exit_and_is_idempotent() {
        let root = TempDir::new().unwrap();
        let (mut runner, _) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["sleep".into()],
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;
        let start = runner
            .handle_frame(frame(
                "start",
                RunnerRequest::ExecStart(ExecRequest {
                    operation_id: "long-op".into(),
                    program: "sleep".into(),
                    argv: vec!["10".into()],
                    cwd: ".".into(),
                    env: BTreeMap::new(),
                    env_refs: Vec::new(),
                    timeout_secs: Some(20),
                    max_output_bytes: None,
                    stream_output: false,
                }),
            ))
            .await;
        assert!(matches!(start.outcome, ResponseOutcome::Ok { .. }));

        let detach = runner
            .handle_frame(frame("detach-running", RunnerRequest::SessionDetach))
            .await;
        match detach.outcome {
            ResponseOutcome::Error { error } => {
                assert_eq!(error.code, ErrorCode::SessionBusy);
                assert!(error.retryable);
            }
            outcome => panic!("detach unexpectedly succeeded: {outcome:?}"),
        }

        let cancelled = runner
            .handle_frame(frame(
                "cancel",
                RunnerRequest::ExecCancel(OperationStatusRequest {
                    operation_id: "long-op".into(),
                }),
            ))
            .await;
        match cancelled.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::ExecCancelled(cancelled),
            } => {
                assert!(!cancelled.already_terminal);
                assert_eq!(cancelled.operation.state, OperationState::Cancelled);
            }
            outcome => panic!("cancel failed: {outcome:?}"),
        }

        let duplicate = runner
            .handle_frame(frame(
                "cancel-again",
                RunnerRequest::ExecCancel(OperationStatusRequest {
                    operation_id: "long-op".into(),
                }),
            ))
            .await;
        match duplicate.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::ExecCancelled(cancelled),
            } => {
                assert!(cancelled.already_terminal);
                assert_eq!(cancelled.operation.state, OperationState::Cancelled);
            }
            outcome => panic!("second cancel failed: {outcome:?}"),
        }
    }

    #[tokio::test]
    async fn remote_stale_running_operation_becomes_interrupted_on_attach() {
        let root = TempDir::new().unwrap();
        let (runner, info) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        drop(runner);
        let operation = OperationRecord {
            operation_id: "stale-op".into(),
            request_digest: "digest".into(),
            state: OperationState::Running,
            result: None,
            message: None,
        };
        let session_dir = session_directory(&root, &info);
        atomic_json_write(&session_dir.join("operations/stale-op.json"), &operation).unwrap();

        let (mut runner, _) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        let response = runner
            .handle_frame(frame(
                "status",
                RunnerRequest::ExecStatus(OperationStatusRequest {
                    operation_id: "stale-op".into(),
                }),
            ))
            .await;
        match response.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::OperationStatus(operation),
            } => assert_eq!(operation.state, OperationState::Interrupted),
            outcome => panic!("status failed: {outcome:?}"),
        }
    }

    #[tokio::test]
    async fn remote_operation_journal_serializes_concurrent_appends() {
        let root = TempDir::new().unwrap();
        let (runner, info) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        let store = runner.session.as_ref().unwrap().store.clone();
        let barrier = Arc::new(std::sync::Barrier::new(8));
        let mut threads = Vec::new();
        for worker in 0..8 {
            let store = store.clone();
            let barrier = Arc::clone(&barrier);
            threads.push(std::thread::spawn(move || {
                barrier.wait();
                for entry in 0..4 {
                    store
                        .append_journal(&OperationRecord {
                            operation_id: format!("worker-{worker}-{entry}"),
                            request_digest: "digest".into(),
                            state: OperationState::Interrupted,
                            result: None,
                            message: Some("x".repeat(64 * 1024)),
                        })
                        .unwrap();
                }
            }));
        }
        for thread in threads {
            thread.join().unwrap();
        }

        let journal =
            std::fs::read_to_string(session_directory(&root, &info).join("journal.ndjson"))
                .unwrap();
        let lines: Vec<&str> = journal.lines().collect();
        assert_eq!(lines.len(), 32);
        for line in lines {
            serde_json::from_str::<serde_json::Value>(line).unwrap();
        }
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_process_groups_clean_descendants_and_pipe_drain_is_bounded() {
        let root = TempDir::new().unwrap();
        let executable = install_process_fixture(root.path());
        let (mut runner, info) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["runner-fixture".into()],
                program_paths: BTreeMap::from([(
                    "runner-fixture".into(),
                    executable.to_string_lossy().into_owned(),
                )]),
                max_timeout_secs: 10,
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;
        let session_dir = session_directory(&root, &info);
        let workspace = session_dir.join("workspace");

        let mut output_request = fixture_request("output-op", "output", "unused", &workspace, 5);
        output_request.stream_output = true;
        let output_start = runner
            .handle_frame(frame(
                "output-start",
                RunnerRequest::ExecStart(output_request),
            ))
            .await;
        assert!(matches!(output_start.outcome, ResponseOutcome::Ok { .. }));
        let mut first_page = None;
        for _ in 0..20 {
            tokio::time::sleep(Duration::from_millis(25)).await;
            let page = runner
                .handle_frame(frame(
                    "output-first-page",
                    RunnerRequest::ExecOutput(OperationOutputRequest {
                        operation_id: "output-op".into(),
                        stdout_offset: 0,
                        stderr_offset: 0,
                        max_bytes: 1024,
                    }),
                ))
                .await;
            let ResponseOutcome::Ok {
                result: RunnerResponse::OperationOutput(page),
            } = page.outcome
            else {
                panic!("live output page failed: {:?}", page.outcome);
            };
            if !page.stdout.is_empty() {
                first_page = Some(page);
                break;
            }
        }
        let first_page = first_page.expect("live stdout did not become visible");
        assert_eq!(first_page.state, OperationState::Running);
        assert_eq!(first_page.stdout, "first-output\n");
        let output = wait_for_terminal(&mut runner, "output-op").await;
        assert_eq!(output.state, OperationState::Exited);
        let final_page = runner
            .handle_frame(frame(
                "output-final-page",
                RunnerRequest::ExecOutput(OperationOutputRequest {
                    operation_id: "output-op".into(),
                    stdout_offset: first_page.stdout_next_offset,
                    stderr_offset: first_page.stderr_next_offset,
                    max_bytes: 1024,
                }),
            ))
            .await;
        let ResponseOutcome::Ok {
            result: RunnerResponse::OperationOutput(final_page),
        } = final_page.outcome
        else {
            panic!("final output page failed: {:?}", final_page.outcome);
        };
        assert!(final_page.stdout.is_empty());
        assert_eq!(final_page.stderr, "second-output\n");

        let mut unsafe_stream = fixture_request("unsafe-stream", "output", "unused", &workspace, 5);
        unsafe_stream.stream_output = true;
        unsafe_stream.env.insert("VISIBLE".into(), "secret".into());
        let unsafe_stream = runner
            .handle_frame(frame(
                "unsafe-stream-start",
                RunnerRequest::ExecStart(unsafe_stream),
            ))
            .await;
        assert!(
            matches!(
                unsafe_stream.outcome,
                ResponseOutcome::Error {
                    error: ProtocolError {
                        code: ErrorCode::DeniedByPolicy,
                        ..
                    }
                }
            ),
            "unexpected stream rejection: {:?}",
            unsafe_stream.outcome
        );

        let normal = runner
            .handle_frame(frame(
                "normal-start",
                RunnerRequest::ExecStart(fixture_request(
                    "normal-op",
                    "normal",
                    "normal.pid",
                    &workspace,
                    5,
                )),
            ))
            .await;
        assert!(matches!(normal.outcome, ResponseOutcome::Ok { .. }));
        let normal = wait_for_terminal(&mut runner, "normal-op").await;
        assert_eq!(normal.state, OperationState::Exited);
        assert!(normal.result.as_ref().unwrap().elapsed_ms < 1_500);
        let normal_pid = wait_for_fixture_marker(&workspace, "normal.pid").await;
        assert_fixture_stopped(&workspace, "normal.pid").await;
        kill_fixture(normal_pid);

        let timed = runner
            .handle_frame(frame(
                "timeout-start",
                RunnerRequest::ExecStart(fixture_request(
                    "timeout-op",
                    "hold",
                    "timeout.pid",
                    &workspace,
                    1,
                )),
            ))
            .await;
        assert!(matches!(timed.outcome, ResponseOutcome::Ok { .. }));
        let timed = wait_for_terminal(&mut runner, "timeout-op").await;
        assert_eq!(timed.state, OperationState::TimedOut);
        let timeout_pid = wait_for_fixture_marker(&workspace, "timeout.pid").await;
        assert_fixture_stopped(&workspace, "timeout.pid").await;
        kill_fixture(timeout_pid);

        let cancel_start = runner
            .handle_frame(frame(
                "cancel-start",
                RunnerRequest::ExecStart(fixture_request(
                    "cancel-tree-op",
                    "hold",
                    "cancel.pid",
                    &workspace,
                    5,
                )),
            ))
            .await;
        assert!(matches!(cancel_start.outcome, ResponseOutcome::Ok { .. }));
        let cancel_pid = wait_for_fixture_marker(&workspace, "cancel.pid").await;
        let cancelled = runner
            .handle_frame(frame(
                "cancel-tree",
                RunnerRequest::ExecCancel(OperationStatusRequest {
                    operation_id: "cancel-tree-op".into(),
                }),
            ))
            .await;
        match cancelled.outcome {
            ResponseOutcome::Ok {
                result: RunnerResponse::ExecCancelled(cancelled),
            } => assert_eq!(cancelled.operation.state, OperationState::Cancelled),
            outcome => panic!("tree cancellation failed: {outcome:?}"),
        }
        assert_fixture_stopped(&workspace, "cancel.pid").await;
        kill_fixture(cancel_pid);

        let escaped = runner
            .handle_frame(frame(
                "escape-start",
                RunnerRequest::ExecStart(fixture_request(
                    "escape-op",
                    "escape",
                    "escape.pid",
                    &workspace,
                    5,
                )),
            ))
            .await;
        assert!(matches!(escaped.outcome, ResponseOutcome::Ok { .. }));
        let escape_pid = wait_for_fixture_marker(&workspace, "escape.pid").await;
        let escaped = wait_for_terminal(&mut runner, "escape-op").await;
        assert_eq!(escaped.state, OperationState::Exited);
        let result = escaped.result.unwrap();
        assert!(result.elapsed_ms >= 1_800);
        assert!(result.elapsed_ms < 4_000);
        assert!(result.stdout_truncated && result.stderr_truncated);
        let heartbeat = workspace.join("escape.pid.beat");
        let before = std::fs::read_to_string(&heartbeat).unwrap();
        tokio::time::sleep(Duration::from_millis(120)).await;
        let after = std::fs::read_to_string(&heartbeat).unwrap();
        assert_ne!(before, after, "escaped fixture should still be alive");
        kill_fixture(escape_pid);

        let abrupt_request = fixture_request("abrupt-op", "hold", "abrupt.pid", &workspace, 5);
        let prepared = runner
            .session
            .as_ref()
            .unwrap()
            .prepare_exec(&abrupt_request)
            .unwrap();
        let execution = tokio::spawn(run_prepared_process(
            prepared,
            CancellationToken::new(),
            None,
        ));
        let abrupt_pid = wait_for_fixture_marker(&workspace, "abrupt.pid").await;
        execution.abort();
        match execution.await {
            Err(error) => assert!(error.is_cancelled()),
            Ok(_) => panic!("aborted execution unexpectedly completed"),
        }
        assert_fixture_stopped(&workspace, "abrupt.pid").await;
        kill_fixture(abrupt_pid);
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn remote_environment_values_never_enter_journal() {
        let root = TempDir::new().unwrap();
        let secret = "do-not-persist-this-value";
        let (mut runner, info) = attached_runner(
            &root,
            HardPolicy {
                allowed_programs: vec!["printenv".into()],
                allowed_env: vec!["RUNNER_TEST_SECRET".into()],
                ..HardPolicy::default()
            },
            ClientPolicy::default(),
        )
        .await;
        let request = ExecRequest {
            operation_id: "secret-op".into(),
            program: "printenv".into(),
            argv: vec!["RUNNER_TEST_SECRET".into()],
            cwd: ".".into(),
            env: BTreeMap::from([("RUNNER_TEST_SECRET".into(), secret.into())]),
            env_refs: Vec::new(),
            timeout_secs: Some(5),
            max_output_bytes: None,
            stream_output: false,
        };
        let response = runner
            .handle_frame(frame("start", RunnerRequest::ExecStart(request.clone())))
            .await;
        assert!(matches!(response.outcome, ResponseOutcome::Ok { .. }));
        let terminal = wait_for_terminal(&mut runner, "secret-op").await;
        assert!(!terminal.result.unwrap().stdout.contains(secret));
        let replay = runner
            .handle_frame(frame("replay", RunnerRequest::ExecStart(request)))
            .await;
        assert!(matches!(
            replay.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::OperationConflict,
                    ..
                }
            }
        ));
        let journal =
            std::fs::read_to_string(session_directory(&root, &info).join("journal.ndjson"))
                .unwrap();
        assert!(!journal.contains(secret));
    }

    #[tokio::test]
    async fn remote_detach_has_explicit_lifecycle_semantics() {
        let root = TempDir::new().unwrap();
        let (mut runner, _) =
            attached_runner(&root, HardPolicy::default(), ClientPolicy::default()).await;
        let detached = runner
            .handle_frame(frame("detach", RunnerRequest::SessionDetach))
            .await;
        assert!(matches!(
            detached.outcome,
            ResponseOutcome::Ok {
                result: RunnerResponse::SessionDetached(_)
            }
        ));
        let no_session = runner
            .handle_frame(frame(
                "list",
                RunnerRequest::FsList(ListRequest {
                    path: ".".into(),
                    max_entries: None,
                }),
            ))
            .await;
        assert!(matches!(
            no_session.outcome,
            ResponseOutcome::Error {
                error: ProtocolError {
                    code: ErrorCode::NoSession,
                    ..
                }
            }
        ));
    }

    #[tokio::test]
    async fn remote_ndjson_transport_emits_one_response_per_line() {
        let root = TempDir::new().unwrap();
        let runner =
            RemoteRunner::new(RunnerConfig::new(root.path(), HardPolicy::default())).unwrap();
        let (client, server) = tokio::io::duplex(16 * 1024);
        let (server_read, server_write) = tokio::io::split(server);
        let task = tokio::spawn(serve_ndjson(server_read, server_write, runner));
        let (client_read, mut client_write) = tokio::io::split(client);
        let mut client_read = BufReader::new(client_read);
        let line = serde_json::to_string(&hello()).unwrap();
        client_write.write_all(line.as_bytes()).await.unwrap();
        client_write.write_all(b"\n").await.unwrap();
        client_write.flush().await.unwrap();
        let mut response_line = String::new();
        client_read.read_line(&mut response_line).await.unwrap();
        let response: ResponseFrame = serde_json::from_str(&response_line).unwrap();
        assert!(matches!(response.outcome, ResponseOutcome::Ok { .. }));
        client_write.shutdown().await.unwrap();
        drop(client_write);
        task.await.unwrap().unwrap();
    }
}
