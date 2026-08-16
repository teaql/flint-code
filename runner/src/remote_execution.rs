//! Remote execution-plane adapter used by FlintCode control-plane clients.
//!
//! The SSH process is only a transport.  Project files and processes live in
//! one durable runner session and are addressed with workspace-relative paths.

use std::collections::BTreeMap;
use std::sync::atomic::{AtomicU64, Ordering};
use std::time::Duration;

use crate::remote_protocol::{
    ClientPolicy, EnvironmentRef, ErrorCode, ExecRequest, ExecResult, ExpectedFileState,
    ListRequest, ListResponse, OperationOutputRequest, OperationOutputResponse, OperationRecord,
    OperationState, ReadRangeRequest, ReadRangeResponse, RunnerRequest, RunnerResponse,
    SearchRequest, SearchResponse, SessionInfo, SnapshotRequest, SnapshotResponse, StatRequest,
    StatResponse, WalkRequest, WalkResponse, WriteAtomicRequest, WriteAtomicResponse,
    WriteCasRequest, WriteCasResponse,
};
use crate::ssh_backend::{SessionMode, SshBackendError, SshRunnerConnection, SshTargetConfig};
use thiserror::Error;
use tokio::sync::Mutex;

const READ_CHUNK_BYTES: u64 = 128 * 1024;
const POLL_INTERVAL: Duration = Duration::from_millis(100);
const CLIENT_TIMEOUT_GRACE: Duration = Duration::from_secs(15);

/// One remote execution session shared by initial generation and every
/// continuation turn of a task.
pub struct RemoteExecution {
    connection: Mutex<SshRunnerConnection>,
    operation_prefix: String,
    next_operation: AtomicU64,
}

impl RemoteExecution {
    /// Connect and create a new durable runner session.
    pub async fn create(
        config: SshTargetConfig,
        policy: ClientPolicy,
        operation_prefix: impl AsRef<str>,
    ) -> Result<Self, RemoteExecutionError> {
        let connection =
            SshRunnerConnection::connect(config, SessionMode::Create { policy }).await?;
        Ok(Self::new(connection, operation_prefix.as_ref()))
    }

    /// Create a session with runner-resolved environment references fixed for
    /// its full lifetime.
    pub async fn create_with_environment(
        config: SshTargetConfig,
        policy: ClientPolicy,
        environment_refs: BTreeMap<String, EnvironmentRef>,
        operation_prefix: impl AsRef<str>,
    ) -> Result<Self, RemoteExecutionError> {
        let connection = SshRunnerConnection::connect(
            config,
            SessionMode::CreateWithEnvironment {
                policy,
                environment_refs,
            },
        )
        .await?;
        Ok(Self::new(connection, operation_prefix.as_ref()))
    }

    /// Reattach to an existing session without silently creating a replacement.
    pub async fn attach(
        config: SshTargetConfig,
        session_id: impl Into<String>,
        policy: ClientPolicy,
        operation_prefix: impl AsRef<str>,
    ) -> Result<Self, RemoteExecutionError> {
        let connection = SshRunnerConnection::connect(
            config,
            SessionMode::Attach {
                session_id: session_id.into(),
                policy,
            },
        )
        .await?;
        Ok(Self::new(connection, operation_prefix.as_ref()))
    }

    /// Reattach with the exact environment-reference declaration used when the
    /// durable session was created.
    pub async fn attach_with_environment(
        config: SshTargetConfig,
        session_id: impl Into<String>,
        policy: ClientPolicy,
        environment_refs: BTreeMap<String, EnvironmentRef>,
        operation_prefix: impl AsRef<str>,
    ) -> Result<Self, RemoteExecutionError> {
        let connection = SshRunnerConnection::connect(
            config,
            SessionMode::AttachWithEnvironment {
                session_id: session_id.into(),
                policy,
                environment_refs,
            },
        )
        .await?;
        Ok(Self::new(connection, operation_prefix.as_ref()))
    }

    fn new(connection: SshRunnerConnection, operation_prefix: &str) -> Self {
        Self {
            connection: Mutex::new(connection),
            operation_prefix: safe_identifier(operation_prefix),
            next_operation: AtomicU64::new(1),
        }
    }

    /// Stable runner session identifier. It does not change across SSH reconnects.
    pub async fn session_id(&self) -> String {
        self.connection.lock().await.session_id().to_owned()
    }

    /// Current session handshake and effective policy.
    pub async fn session_info(&self) -> SessionInfo {
        self.connection.lock().await.session().clone()
    }

    /// Read a complete UTF-8 file using bounded range requests.
    pub async fn read_text(&self, path: impl Into<String>) -> Result<String, RemoteExecutionError> {
        let path = path.into();
        let mut result = String::new();
        let mut offset = 0_u64;
        loop {
            let response = self
                .request_with_reconnect(RunnerRequest::FsReadRange(ReadRangeRequest {
                    path: path.clone(),
                    offset,
                    length: READ_CHUNK_BYTES,
                }))
                .await?;
            let RunnerResponse::ReadRange(page) = response else {
                return Err(RemoteExecutionError::UnexpectedResponse);
            };
            if page.offset != offset || page.bytes != page.content.len() as u64 {
                return Err(RemoteExecutionError::InvalidRemoteResult(
                    "read range metadata did not match its UTF-8 payload",
                ));
            }
            result.push_str(&page.content);
            offset = offset.saturating_add(page.bytes);
            if page.eof {
                return Ok(result);
            }
            if page.bytes == 0 {
                return Err(RemoteExecutionError::InvalidRemoteResult(
                    "read range made no forward progress",
                ));
            }
        }
    }

    /// Read one bounded UTF-8 range without downloading the rest of the file.
    pub async fn read_range(
        &self,
        path: impl Into<String>,
        offset: u64,
        length: u64,
    ) -> Result<ReadRangeResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsReadRange(ReadRangeRequest {
                path: path.into(),
                offset,
                length,
            }))
            .await?;
        match response {
            RunnerResponse::ReadRange(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Atomically replace a UTF-8 workspace file.
    pub async fn write_text(
        &self,
        path: impl Into<String>,
        content: impl Into<String>,
        create_parents: bool,
    ) -> Result<WriteAtomicResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                path: path.into(),
                content: content.into(),
                create_parents,
            }))
            .await?;
        match response {
            RunnerResponse::WriteAtomic(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// List one remote workspace directory.
    pub async fn list(
        &self,
        path: impl Into<String>,
        max_entries: Option<u32>,
    ) -> Result<ListResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsList(ListRequest {
                path: path.into(),
                max_entries,
            }))
            .await?;
        match response {
            RunnerResponse::List(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Inspect one workspace-relative path without following a symlink target.
    pub async fn stat(
        &self,
        path: impl Into<String>,
        include_sha256: bool,
    ) -> Result<StatResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsStat(StatRequest {
                path: path.into(),
                include_sha256,
            }))
            .await?;
        match response {
            RunnerResponse::Stat(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Deterministically enumerate a readable subtree.
    pub async fn walk(
        &self,
        path: impl Into<String>,
        max_entries: Option<u32>,
        max_depth: Option<u32>,
    ) -> Result<WalkResponse, RemoteExecutionError> {
        self.walk_excluding(path, max_entries, max_depth, Vec::new())
            .await
    }

    /// Deterministically enumerate a readable subtree without traversing the
    /// supplied workspace-relative protected paths.
    pub async fn walk_excluding(
        &self,
        path: impl Into<String>,
        max_entries: Option<u32>,
        max_depth: Option<u32>,
        excluded_paths: Vec<String>,
    ) -> Result<WalkResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsWalk(WalkRequest {
                path: path.into(),
                max_entries,
                max_depth,
                excluded_paths,
            }))
            .await?;
        match response {
            RunnerResponse::Walk(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Search readable remote files for a bounded literal string.
    pub async fn search(
        &self,
        path: impl Into<String>,
        query: impl Into<String>,
        max_matches: Option<u32>,
        max_file_bytes: Option<u64>,
        excluded_paths: Vec<String>,
    ) -> Result<SearchResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsSearch(SearchRequest {
                path: path.into(),
                query: query.into(),
                max_matches,
                max_file_bytes,
                excluded_paths,
            }))
            .await?;
        match response {
            RunnerResponse::Search(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Obtain the runner-authored manifest and tree digest for a subtree.
    pub async fn snapshot(
        &self,
        path: impl Into<String>,
        max_entries: Option<u32>,
    ) -> Result<SnapshotResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsSnapshot(SnapshotRequest {
                path: path.into(),
                max_entries,
            }))
            .await?;
        match response {
            RunnerResponse::Snapshot(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Atomically write only if the current file state matches the supplied
    /// digest (or remains absent). This is the normal model-authored edit path.
    pub async fn write_text_cas(
        &self,
        path: impl Into<String>,
        content: impl Into<String>,
        create_parents: bool,
        expected: ExpectedFileState,
    ) -> Result<WriteCasResponse, RemoteExecutionError> {
        let response = self
            .request_with_reconnect(RunnerRequest::FsWriteCas(WriteCasRequest {
                path: path.into(),
                content: content.into(),
                create_parents,
                expected,
            }))
            .await?;
        match response {
            RunnerResponse::WriteCas(response) => Ok(response),
            _ => Err(RemoteExecutionError::UnexpectedResponse),
        }
    }

    /// Execute one structured command and wait for its persisted terminal state.
    ///
    /// If SSH drops around the start acknowledgement, recovery first queries the
    /// original operation ID. It never blindly submits a second side-effecting
    /// command under a different identity.
    pub async fn exec(
        &self,
        program: impl Into<String>,
        argv: Vec<String>,
        cwd: impl Into<String>,
        env: BTreeMap<String, String>,
        timeout: Duration,
        max_output_bytes: u64,
    ) -> Result<ExecResult, RemoteExecutionError> {
        self.exec_with_environment_refs(
            program,
            argv,
            cwd,
            env,
            Vec::new(),
            timeout,
            max_output_bytes,
        )
        .await
    }

    /// Execute with runner-resolved environment references. Reference names
    /// were fixed at session attach time; their values never cross SSH in an
    /// execution request or enter the local process environment.
    #[allow(clippy::too_many_arguments)]
    pub async fn exec_with_environment_refs(
        &self,
        program: impl Into<String>,
        argv: Vec<String>,
        cwd: impl Into<String>,
        env: BTreeMap<String, String>,
        env_refs: Vec<String>,
        timeout: Duration,
        max_output_bytes: u64,
    ) -> Result<ExecResult, RemoteExecutionError> {
        let operation_id = self.next_operation_id();
        let request = ExecRequest {
            operation_id: operation_id.clone(),
            program: program.into(),
            argv,
            cwd: cwd.into(),
            env,
            env_refs,
            timeout_secs: Some(timeout.as_secs().max(1)),
            max_output_bytes: Some(max_output_bytes),
            stream_output: false,
        };

        let record = self.start_recovering(request).await?;
        self.wait_for_operation(record, timeout + CLIENT_TIMEOUT_GRACE)
            .await
    }

    /// Start an output-streaming operation without exposing environment values.
    pub async fn start_streaming_operation(
        &self,
        program: impl Into<String>,
        argv: Vec<String>,
        cwd: impl Into<String>,
        timeout: Duration,
        max_output_bytes: u64,
    ) -> Result<OperationRecord, RemoteExecutionError> {
        let request = ExecRequest {
            operation_id: self.next_operation_id(),
            program: program.into(),
            argv,
            cwd: cwd.into(),
            env: BTreeMap::new(),
            env_refs: Vec::new(),
            timeout_secs: Some(timeout.as_secs().max(1)),
            max_output_bytes: Some(max_output_bytes),
            stream_output: true,
        };
        self.start_recovering(request).await
    }

    /// Read a bounded incremental output page, reconnecting before retrying the
    /// same cursor request when transport fails.
    pub async fn operation_output(
        &self,
        operation_id: impl Into<String>,
        stdout_offset: u64,
        stderr_offset: u64,
        max_bytes: u64,
    ) -> Result<OperationOutputResponse, RemoteExecutionError> {
        let request = OperationOutputRequest {
            operation_id: operation_id.into(),
            stdout_offset,
            stderr_offset,
            max_bytes,
        };
        let mut connection = self.connection.lock().await;
        match connection.operation_output(request.clone()).await {
            Ok(response) => Ok(response),
            Err(error) if is_transport_failure(&error) => {
                connection.reconnect().await?;
                Ok(connection.operation_output(request).await?)
            }
            Err(error) => Err(error.into()),
        }
    }

    /// Query one operation's durable state.
    pub async fn operation_status(
        &self,
        operation_id: impl Into<String>,
    ) -> Result<OperationRecord, RemoteExecutionError> {
        self.operation_status_recovering(operation_id.into()).await
    }

    /// Idempotently cancel an operation and wait for the remote process group
    /// to reach a persisted terminal state.
    pub async fn cancel_operation(
        &self,
        operation_id: impl Into<String>,
    ) -> Result<OperationRecord, RemoteExecutionError> {
        let operation_id = operation_id.into();
        let mut connection = self.connection.lock().await;
        match connection.cancel_operation(operation_id.clone()).await {
            Ok(response) => Ok(response.operation),
            Err(error) if is_transport_failure(&error) => {
                connection.reconnect().await?;
                Ok(connection.cancel_operation(operation_id).await?.operation)
            }
            Err(error) => Err(error.into()),
        }
    }

    /// Detach the SSH bridge while retaining the durable runner session.
    pub async fn detach(&self) -> Result<(), RemoteExecutionError> {
        self.connection.lock().await.close().await?;
        Ok(())
    }

    async fn request_with_reconnect(
        &self,
        request: RunnerRequest,
    ) -> Result<RunnerResponse, RemoteExecutionError> {
        let mut connection = self.connection.lock().await;
        match connection.request(request.clone()).await {
            Ok(response) => Ok(response),
            Err(error) if is_transport_failure(&error) => {
                connection.reconnect().await?;
                Ok(connection.request(request).await?)
            }
            Err(error) => Err(error.into()),
        }
    }

    async fn start_recovering(
        &self,
        request: ExecRequest,
    ) -> Result<OperationRecord, RemoteExecutionError> {
        let operation_id = request.operation_id.clone();
        let mut connection = self.connection.lock().await;
        match connection.start_or_replay(request.clone()).await {
            Ok(response) => Ok(response.operation),
            Err(error) if is_transport_failure(&error) => {
                connection.reconnect().await?;
                match connection.operation_status(&operation_id).await {
                    Ok(record) => Ok(record),
                    Err(SshBackendError::RemoteRejected {
                        code: ErrorCode::NotFound,
                        ..
                    }) => Ok(connection.start_or_replay(request).await?.operation),
                    Err(error) => Err(error.into()),
                }
            }
            Err(error) => Err(error.into()),
        }
    }

    async fn wait_for_operation(
        &self,
        mut record: OperationRecord,
        deadline_after: Duration,
    ) -> Result<ExecResult, RemoteExecutionError> {
        let deadline = tokio::time::Instant::now() + deadline_after;
        while record.state == OperationState::Running {
            if tokio::time::Instant::now() >= deadline {
                let cancelled = self
                    .connection
                    .lock()
                    .await
                    .cancel_operation(record.operation_id.clone())
                    .await?;
                record = cancelled.operation;
                break;
            }
            tokio::time::sleep(POLL_INTERVAL).await;
            record = self
                .operation_status_recovering(record.operation_id.clone())
                .await?;
        }

        if record.state != OperationState::Exited {
            return Err(RemoteExecutionError::AbnormalCommandState {
                operation_id: record.operation_id,
                state: record.state,
                detail: record
                    .message
                    .unwrap_or_else(|| "runner returned no terminal diagnostic".to_string()),
            });
        }
        record
            .result
            .ok_or(RemoteExecutionError::MissingCommandResult {
                operation_id: record.operation_id,
                state: record.state,
            })
    }

    async fn operation_status_recovering(
        &self,
        operation_id: String,
    ) -> Result<OperationRecord, RemoteExecutionError> {
        let mut connection = self.connection.lock().await;
        match connection.operation_status(operation_id.clone()).await {
            Ok(record) => Ok(record),
            Err(error) if is_transport_failure(&error) => {
                connection.reconnect().await?;
                Ok(connection.operation_status(operation_id).await?)
            }
            Err(error) => Err(error.into()),
        }
    }

    fn next_operation_id(&self) -> String {
        let ordinal = self.next_operation.fetch_add(1, Ordering::Relaxed);
        format!("{}-{ordinal:016x}", self.operation_prefix)
    }
}

fn safe_identifier(value: &str) -> String {
    let mut result = value
        .chars()
        .map(|character| {
            if character.is_ascii_alphanumeric() || matches!(character, '-' | '_') {
                character
            } else {
                '-'
            }
        })
        .take(80)
        .collect::<String>();
    if result.is_empty() {
        result.push_str("klintcode");
    }
    result
}

fn is_transport_failure(error: &SshBackendError) -> bool {
    matches!(
        error,
        SshBackendError::Io { .. }
            | SshBackendError::Timeout { .. }
            | SshBackendError::ResponseTooLarge
            | SshBackendError::UnexpectedEof
            | SshBackendError::InvalidResponse
            | SshBackendError::UnexpectedResponseId
    )
}

/// An execution-plane failure. Pipeline callers must classify every variant as
/// infrastructure and must never send it to the coding model as a code error.
#[derive(Debug, Error)]
pub enum RemoteExecutionError {
    #[error("remote runner transport/session failed: {0}")]
    Backend(#[from] SshBackendError),
    #[error("remote runner returned an unexpected response kind")]
    UnexpectedResponse,
    #[error("remote runner returned an invalid result: {0}")]
    InvalidRemoteResult(&'static str),
    #[error("remote operation {operation_id} ended as {state:?} without a command result")]
    MissingCommandResult {
        operation_id: String,
        state: OperationState,
    },
    #[error("remote operation {operation_id} ended as {state:?}: {detail}")]
    AbnormalCommandState {
        operation_id: String,
        state: OperationState,
        detail: String,
    },
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn operation_prefix_is_protocol_safe_and_bounded() {
        assert_eq!(safe_identifier("run 1/a"), "run-1-a");
        assert_eq!(safe_identifier(""), "klintcode");
        assert!(safe_identifier(&"x".repeat(200)).len() <= 80);
    }
}
