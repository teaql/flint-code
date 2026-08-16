//! Stateful SSH transport for a remote FlintCode runner.
//!
//! SSH is used only as a byte transport. Task operations use the typed NDJSON
//! protocol and are never rendered as remote shell command strings.

use crate::remote_protocol::{
    CancelOperationResponse, ClientPolicy, EnvironmentRef, ErrorCode, ExecRequest, HelloRequest,
    HelloResponse, OperationOutputRequest, OperationOutputResponse, OperationRecord,
    OperationResponse, OperationStatusRequest, PROTOCOL_VERSION, RequestFrame, ResponseFrame,
    ResponseOutcome, RunnerRequest, RunnerResponse, SessionAttachRequest, SessionInfo,
};
use serde::Deserialize;
use sha2::{Digest, Sha256};
use std::collections::BTreeMap;
use std::ffi::OsString;
use std::path::{Path, PathBuf};
use std::process::Stdio;
use std::sync::atomic::{AtomicBool, AtomicU64, Ordering};
use std::time::Duration;
use thiserror::Error;
use tokio::io::{
    AsyncBufRead, AsyncBufReadExt, AsyncRead, AsyncReadExt, AsyncWriteExt, BufReader, BufWriter,
};
use tokio::process::{Child, ChildStdin, ChildStdout, Command};
use tokio::sync::Mutex;
use tokio::task::JoinHandle;

const DEFAULT_MAX_LINE_BYTES: usize = 8 * 1024 * 1024;
const DEFAULT_MAX_INSTALL_OUTPUT_BYTES: usize = 64 * 1024;

/// Connection and bootstrap settings for one SSH target alias.
#[derive(Debug, Clone)]
pub struct SshTargetConfig {
    /// An SSH `Host` alias, not an arbitrary `user@host` expression.
    pub target_alias: String,
    /// Local SSH executable. This is passed directly to `Command::new`.
    pub ssh_program: PathBuf,
    /// Optional local OpenSSH configuration file.
    pub ssh_config_path: Option<PathBuf>,
    /// Optional dedicated known-hosts file. When omitted, OpenSSH's configured
    /// user/system known-hosts files remain authoritative.
    pub known_hosts_path: Option<PathBuf>,
    /// Fixed remote bootstrap executable or absolute path.
    pub bootstrap_command: String,
    /// Local runner binary uploaded through the bootstrap's standard input.
    pub local_runner_path: PathBuf,
    /// Strict argv appended after bootstrap `launch --sha256 ... --`.
    pub runner_args: Vec<String>,
    /// SSH connection timeout, also used by the cache check.
    pub connect_timeout: Duration,
    /// End-to-end deadline for uploading and verifying a missing runner.
    pub upload_timeout: Duration,
    /// Timeout for one request/response exchange.
    pub rpc_timeout: Duration,
    /// Grace period used when closing the long-lived SSH child.
    pub close_timeout: Duration,
    /// Maximum encoded request or response NDJSON payload size.
    pub max_line_bytes: usize,
    /// Maximum combined bootstrap stdout/stderr before install is rejected.
    pub max_install_output_bytes: usize,
}

impl SshTargetConfig {
    /// Creates a target using secure OpenSSH defaults and the standard bootstrap name.
    pub fn new(target_alias: impl Into<String>, local_runner_path: impl Into<PathBuf>) -> Self {
        Self {
            target_alias: target_alias.into(),
            ssh_program: PathBuf::from("ssh"),
            ssh_config_path: None,
            known_hosts_path: None,
            bootstrap_command: "klintcode-bootstrap".to_owned(),
            local_runner_path: local_runner_path.into(),
            runner_args: vec!["--stdio".to_owned()],
            connect_timeout: Duration::from_secs(15),
            upload_timeout: Duration::from_secs(120),
            rpc_timeout: Duration::from_secs(60),
            close_timeout: Duration::from_secs(5),
            max_line_bytes: DEFAULT_MAX_LINE_BYTES,
            max_install_output_bytes: DEFAULT_MAX_INSTALL_OUTPUT_BYTES,
        }
    }

    fn validate(&self) -> Result<(), SshBackendError> {
        validate_target_alias(&self.target_alias)?;
        validate_remote_executable(&self.bootstrap_command)?;
        if self.ssh_program.as_os_str().is_empty() {
            return Err(SshBackendError::InvalidConfig("ssh_program is empty"));
        }
        if self.local_runner_path.as_os_str().is_empty() {
            return Err(SshBackendError::InvalidConfig("local_runner_path is empty"));
        }
        if self.runner_args.is_empty() || self.runner_args.len() > 32 {
            return Err(SshBackendError::InvalidConfig(
                "runner_args must contain 1..=32 safe tokens",
            ));
        }
        for argument in &self.runner_args {
            validate_remote_argument(argument)?;
        }
        if !self
            .runner_args
            .iter()
            .any(|argument| argument == "--stdio" || argument == "bridge")
        {
            return Err(SshBackendError::InvalidConfig(
                "runner_args must select the stdio bridge",
            ));
        }
        if let Some(path) = &self.known_hosts_path
            && (!path.is_absolute()
                || path
                    .to_str()
                    .is_none_or(|value| value.chars().any(char::is_whitespace)))
        {
            return Err(SshBackendError::InvalidConfig(
                "known_hosts_path must be an absolute UTF-8 path without whitespace",
            ));
        }
        if self.connect_timeout.is_zero() {
            return Err(SshBackendError::InvalidConfig(
                "connect_timeout must be non-zero",
            ));
        }
        if self.rpc_timeout.is_zero() {
            return Err(SshBackendError::InvalidConfig(
                "rpc_timeout must be non-zero",
            ));
        }
        if self.upload_timeout.is_zero() {
            return Err(SshBackendError::InvalidConfig(
                "upload_timeout must be non-zero",
            ));
        }
        if self.close_timeout.is_zero() {
            return Err(SshBackendError::InvalidConfig(
                "close_timeout must be non-zero",
            ));
        }
        if self.max_line_bytes == 0 {
            return Err(SshBackendError::InvalidConfig(
                "max_line_bytes must be non-zero",
            ));
        }
        if self.max_install_output_bytes == 0 {
            return Err(SshBackendError::InvalidConfig(
                "max_install_output_bytes must be non-zero",
            ));
        }
        Ok(())
    }
}

/// Selects whether connection setup creates a session or requires an existing one.
#[derive(Debug, Clone)]
pub enum SessionMode {
    /// Lets the runner allocate a new stable session identifier.
    Create {
        /// Client restrictions intersected with the host hard policy.
        policy: ClientPolicy,
    },
    /// Creates a session with server-resolved contract environment values.
    CreateWithEnvironment {
        policy: ClientPolicy,
        environment_refs: BTreeMap<String, EnvironmentRef>,
    },
    /// Attaches to a stable session and fails if its journal has disappeared.
    Attach {
        /// Stable task execution session identifier.
        session_id: String,
        /// The same or narrower client policy used for the session.
        policy: ClientPolicy,
    },
    /// Reattaches with the exact environment declarations from creation.
    AttachWithEnvironment {
        session_id: String,
        policy: ClientPolicy,
        environment_refs: BTreeMap<String, EnvironmentRef>,
    },
}

impl SessionMode {
    /// Creates a session using an unrestricted client layer.
    pub fn create() -> Self {
        Self::Create {
            policy: ClientPolicy::default(),
        }
    }

    /// Attaches to a session using an unrestricted client layer.
    pub fn attach(session_id: impl Into<String>) -> Self {
        Self::Attach {
            session_id: session_id.into(),
            policy: ClientPolicy::default(),
        }
    }

    /// Adds explicit server-resolved environment declarations without
    /// changing the compatibility of the basic Create/Attach variants.
    pub fn with_environment_refs(self, environment_refs: BTreeMap<String, EnvironmentRef>) -> Self {
        match self {
            Self::Create { policy } | Self::CreateWithEnvironment { policy, .. } => {
                Self::CreateWithEnvironment {
                    policy,
                    environment_refs,
                }
            }
            Self::Attach { session_id, policy }
            | Self::AttachWithEnvironment {
                session_id, policy, ..
            } => Self::AttachWithEnvironment {
                session_id,
                policy,
                environment_refs,
            },
        }
    }
}

/// A connected runner session transported by one long-lived SSH process.
pub struct SshRunnerConnection {
    config: SshTargetConfig,
    rpc: RpcClient,
    runner_sha256: String,
    policy: ClientPolicy,
    environment_refs: BTreeMap<String, EnvironmentRef>,
    session: SessionInfo,
    hello: HelloResponse,
    closed: AtomicBool,
}

impl SshRunnerConnection {
    /// Installs the content-addressed runner, launches it, performs `hello`, and
    /// creates or attaches the requested execution session.
    pub async fn connect(
        config: SshTargetConfig,
        session_mode: SessionMode,
    ) -> Result<Self, SshBackendError> {
        config.validate()?;
        let runner_sha256 = install_runner(&config).await?;
        let (requested_session_id, policy, environment_refs, require_existing) = match session_mode
        {
            SessionMode::Create { policy } => (None, policy, BTreeMap::new(), false),
            SessionMode::CreateWithEnvironment {
                policy,
                environment_refs,
            } => (None, policy, environment_refs, false),
            SessionMode::Attach { session_id, policy } => {
                (Some(session_id), policy, BTreeMap::new(), true)
            }
            SessionMode::AttachWithEnvironment {
                session_id,
                policy,
                environment_refs,
            } => (Some(session_id), policy, environment_refs, true),
        };
        if let Some(session_id) = &requested_session_id {
            validate_protocol_string(session_id)?;
        }
        let (rpc, hello, session) = establish_session(
            &config,
            &runner_sha256,
            requested_session_id.as_deref(),
            policy.clone(),
            environment_refs.clone(),
        )
        .await?;

        if require_existing && session.created {
            rpc.shutdown().await;
            return Err(SshBackendError::SessionWasRecreated);
        }

        Ok(Self {
            config,
            rpc,
            runner_sha256,
            policy,
            environment_refs,
            session,
            hello,
            closed: AtomicBool::new(false),
        })
    }

    /// Returns the exact SHA-256 digest pinned for this execution session.
    pub fn runner_sha256(&self) -> &str {
        &self.runner_sha256
    }

    /// Returns the stable remote execution session identifier.
    pub fn session_id(&self) -> &str {
        &self.session.session_id
    }

    /// Returns the negotiated runner capabilities.
    pub fn hello(&self) -> &HelloResponse {
        &self.hello
    }

    /// Returns the current workspace and effective policy handshake.
    pub fn session(&self) -> &SessionInfo {
        &self.session
    }

    /// Returns the exact environment declarations replayed during reconnect.
    pub fn environment_refs(&self) -> &BTreeMap<String, EnvironmentRef> {
        &self.environment_refs
    }

    /// Reopens SSH and attaches to the same persisted session.
    ///
    /// The reconnect fails if the server silently creates an empty replacement
    /// session or if its effective policy digest changed. Callers can then ask
    /// for `operation_status` using the same operation ID without rerunning it.
    pub async fn reconnect(&mut self) -> Result<(), SshBackendError> {
        if self.closed.load(Ordering::Acquire) {
            return Err(SshBackendError::ConnectionClosed);
        }
        self.rpc.shutdown().await;

        // Upload is idempotent and lets reconnect recover if the remote runner
        // cache was cleaned while the task session journal was retained.
        let runner_sha256 = install_runner(&self.config).await?;
        if runner_sha256 != self.runner_sha256 {
            return Err(SshBackendError::RunnerChanged);
        }

        let (rpc, hello, session) = establish_session(
            &self.config,
            &self.runner_sha256,
            Some(&self.session.session_id),
            self.policy.clone(),
            self.environment_refs.clone(),
        )
        .await?;
        if session.created {
            rpc.shutdown().await;
            return Err(SshBackendError::SessionWasRecreated);
        }
        if session.session_id != self.session.session_id {
            rpc.shutdown().await;
            return Err(SshBackendError::SessionChanged);
        }
        if session.policy_digest != self.session.policy_digest
            && !session
                .effective_policy
                .is_narrower_or_equal_to(&self.session.effective_policy)
        {
            rpc.shutdown().await;
            return Err(SshBackendError::PolicyChanged);
        }

        self.rpc = rpc;
        self.hello = hello;
        self.session = session;
        Ok(())
    }

    /// Sends one typed runner request on the sequential NDJSON connection.
    pub async fn request(&self, request: RunnerRequest) -> Result<RunnerResponse, SshBackendError> {
        if self.closed.load(Ordering::Acquire) {
            return Err(SshBackendError::ConnectionClosed);
        }
        self.rpc.request(request).await
    }

    /// Starts an operation or replays the persisted result for an identical request.
    ///
    /// `operation_id`, `program`, `argv`, and `cwd` remain distinct fields on the
    /// wire. Neither this client nor the runner transport invokes `sh -c`.
    pub async fn start_or_replay(
        &self,
        request: ExecRequest,
    ) -> Result<OperationResponse, SshBackendError> {
        validate_exec_request(&request)?;
        match self.request(RunnerRequest::ExecStart(request)).await? {
            RunnerResponse::Exec(response) => Ok(response),
            _ => Err(SshBackendError::UnexpectedResponseType),
        }
    }

    /// Reads the persisted state/result for an operation after reconnect.
    pub async fn operation_status(
        &self,
        operation_id: impl Into<String>,
    ) -> Result<OperationRecord, SshBackendError> {
        let operation_id = operation_id.into();
        validate_protocol_string(&operation_id)?;
        match self
            .request(RunnerRequest::ExecStatus(OperationStatusRequest {
                operation_id,
            }))
            .await?
        {
            RunnerResponse::OperationStatus(record) => Ok(record),
            _ => Err(SshBackendError::UnexpectedResponseType),
        }
    }

    /// Reads one bounded, cursor-addressed output page for an operation.
    pub async fn operation_output(
        &self,
        request: OperationOutputRequest,
    ) -> Result<OperationOutputResponse, SshBackendError> {
        validate_protocol_string(&request.operation_id)?;
        match self.request(RunnerRequest::ExecOutput(request)).await? {
            RunnerResponse::OperationOutput(response) => Ok(response),
            _ => Err(SshBackendError::UnexpectedResponseType),
        }
    }

    /// Idempotently cancels an operation and waits for its terminal record.
    pub async fn cancel_operation(
        &self,
        operation_id: impl Into<String>,
    ) -> Result<CancelOperationResponse, SshBackendError> {
        let operation_id = operation_id.into();
        validate_protocol_string(&operation_id)?;
        match self
            .request(RunnerRequest::ExecCancel(OperationStatusRequest {
                operation_id,
            }))
            .await?
        {
            RunnerResponse::ExecCancelled(response) => Ok(response),
            _ => Err(SshBackendError::UnexpectedResponseType),
        }
    }

    /// Detaches the bridge and closes SSH while preserving the remote session.
    ///
    /// Transport shutdown is attempted even when the detach acknowledgement is
    /// missing, so the remote runner always observes EOF eventually.
    pub async fn close(&self) -> Result<(), SshBackendError> {
        if self.closed.swap(true, Ordering::AcqRel) {
            return Ok(());
        }
        let detach_result = match self.rpc.request(RunnerRequest::SessionDetach).await {
            Ok(RunnerResponse::SessionDetached(detached))
                if detached.session_id == self.session.session_id =>
            {
                Ok(())
            }
            Ok(_) => Err(SshBackendError::UnexpectedResponseType),
            Err(error) => Err(error),
        };
        self.rpc.shutdown().await;
        detach_result
    }
}

/// Errors emitted by runner installation or the stateful SSH transport.
///
/// Error messages intentionally omit request parameters, remote messages,
/// target names, and filesystem contents so secrets cannot be reflected to logs.
#[derive(Debug, Error)]
pub enum SshBackendError {
    /// A static configuration invariant was not satisfied.
    #[error("invalid SSH runner configuration: {0}")]
    InvalidConfig(&'static str),
    /// A local process or pipe operation failed.
    #[error("SSH runner I/O failed during {stage}")]
    Io {
        /// Non-sensitive operation stage.
        stage: &'static str,
        /// Underlying local I/O error, available to diagnostic code.
        #[source]
        source: std::io::Error,
    },
    /// A bounded operation exceeded its deadline.
    #[error("SSH runner operation timed out during {stage}")]
    Timeout {
        /// Non-sensitive operation stage.
        stage: &'static str,
    },
    /// Bootstrap rejected an install without exposing its output.
    #[error("remote runner bootstrap install failed (exit code {code})")]
    InstallFailed {
        /// Process exit code, or `-1` if terminated by a signal.
        code: i32,
    },
    /// Bootstrap cache lookup failed without exposing its output.
    #[error("remote runner bootstrap check failed (exit code {code})")]
    CheckFailed {
        /// Process exit code, or `-1` if terminated by a signal.
        code: i32,
    },
    /// Bootstrap returned malformed or inconsistent cache metadata.
    #[error("remote runner bootstrap returned an invalid check response")]
    InvalidCheckResponse,
    /// Bootstrap produced more diagnostic output than the configured bound.
    #[error("remote runner bootstrap output exceeded its configured bound")]
    InstallOutputTooLarge,
    /// An encoded request exceeded the configured NDJSON payload bound.
    #[error("runner request exceeded its configured line bound")]
    RequestTooLarge,
    /// A response exceeded the configured NDJSON payload bound.
    #[error("runner response exceeded its configured line bound")]
    ResponseTooLarge,
    /// The runner closed stdout before returning a response.
    #[error("remote runner closed the protocol stream")]
    UnexpectedEof,
    /// A frame was not valid for the negotiated protocol.
    #[error("remote runner returned an invalid protocol response")]
    InvalidResponse,
    /// A response did not match the in-flight request ID.
    #[error("remote runner returned an unexpected request id")]
    UnexpectedResponseId,
    /// A successful frame carried a result for a different request kind.
    #[error("remote runner returned an unexpected response type")]
    UnexpectedResponseType,
    /// The runner lacks a capability required by this client version.
    #[error("remote runner is missing required capability {0}")]
    MissingCapability(String),
    /// The runner rejected an operation; its potentially sensitive text is omitted.
    #[error("remote runner rejected the operation ({code:?}, retryable={retryable})")]
    RemoteRejected {
        /// Stable machine-readable error category.
        code: ErrorCode,
        /// Whether the remote runner considers retry safe.
        retryable: bool,
    },
    /// A required existing session was missing and got replaced with an empty one.
    #[error("remote runner recreated the requested session; recovery stopped")]
    SessionWasRecreated,
    /// The server returned a different session identifier during recovery.
    #[error("remote runner changed the session identifier; recovery stopped")]
    SessionChanged,
    /// Effective policy widened while recovering the same session.
    #[error("remote runner policy widened; recovery stopped")]
    PolicyChanged,
    /// Local runner bytes changed during recovery.
    #[error("local runner digest changed; recovery stopped")]
    RunnerChanged,
    /// The local connection has already been closed or invalidated.
    #[error("SSH runner connection is closed")]
    ConnectionClosed,
}

impl SshBackendError {
    fn invalidates_transport(&self) -> bool {
        matches!(
            self,
            Self::Io { .. }
                | Self::Timeout { .. }
                | Self::ResponseTooLarge
                | Self::UnexpectedEof
                | Self::InvalidResponse
                | Self::UnexpectedResponseId
        )
    }
}

struct RpcClient {
    transport: Mutex<RpcTransport>,
    next_request_id: AtomicU64,
    request_timeout: Duration,
    close_timeout: Duration,
    max_line_bytes: usize,
    stderr_task: JoinHandle<()>,
}

struct RpcTransport {
    child: Child,
    stdin: Option<BufWriter<ChildStdin>>,
    stdout: BufReader<ChildStdout>,
    closed: bool,
}

impl RpcClient {
    async fn launch(
        config: &SshTargetConfig,
        runner_sha256: &str,
    ) -> Result<Self, SshBackendError> {
        let mut remote_arguments = vec![
            config.bootstrap_command.as_str(),
            "launch",
            "--sha256",
            runner_sha256,
            "--",
        ];
        remote_arguments.extend(config.runner_args.iter().map(String::as_str));
        let ssh_arguments = build_ssh_arguments(config, &remote_arguments)?;
        let mut child = Command::new(&config.ssh_program)
            .args(&ssh_arguments)
            .stdin(Stdio::piped())
            .stdout(Stdio::piped())
            .stderr(Stdio::piped())
            .kill_on_drop(true)
            .spawn()
            .map_err(|source| SshBackendError::Io {
                stage: "runner launch",
                source,
            })?;

        let stdin = child.stdin.take().ok_or_else(|| SshBackendError::Io {
            stage: "runner stdin setup",
            source: std::io::Error::other("missing child stdin"),
        })?;
        let stdout = child.stdout.take().ok_or_else(|| SshBackendError::Io {
            stage: "runner stdout setup",
            source: std::io::Error::other("missing child stdout"),
        })?;
        let mut stderr = child.stderr.take().ok_or_else(|| SshBackendError::Io {
            stage: "runner stderr setup",
            source: std::io::Error::other("missing child stderr"),
        })?;

        // Drain without retaining remote diagnostics. This bounds memory and
        // prevents a long-lived SSH process from blocking on stderr backpressure.
        let stderr_task = tokio::spawn(async move {
            let _ = tokio::io::copy(&mut stderr, &mut tokio::io::sink()).await;
        });

        Ok(Self {
            transport: Mutex::new(RpcTransport {
                child,
                stdin: Some(BufWriter::new(stdin)),
                stdout: BufReader::new(stdout),
                closed: false,
            }),
            next_request_id: AtomicU64::new(1),
            request_timeout: config.rpc_timeout,
            close_timeout: config.close_timeout,
            max_line_bytes: config.max_line_bytes,
            stderr_task,
        })
    }

    async fn request(&self, request: RunnerRequest) -> Result<RunnerResponse, SshBackendError> {
        let request_number = self.next_request_id.fetch_add(1, Ordering::Relaxed);
        let request_id = format!("req-{request_number:016x}");
        let mut encoded = serde_json::to_vec(&RequestFrame {
            request_id: request_id.clone(),
            protocol_version: PROTOCOL_VERSION,
            request,
        })
        .map_err(|_| SshBackendError::InvalidResponse)?;
        if encoded.len() > self.max_line_bytes {
            return Err(SshBackendError::RequestTooLarge);
        }
        encoded.push(b'\n');

        let mut transport = self.transport.lock().await;
        if transport.closed {
            return Err(SshBackendError::ConnectionClosed);
        }

        let exchange = async {
            let stdin = transport
                .stdin
                .as_mut()
                .ok_or(SshBackendError::ConnectionClosed)?;
            stdin
                .write_all(&encoded)
                .await
                .map_err(|source| SshBackendError::Io {
                    stage: "request write",
                    source,
                })?;
            stdin.flush().await.map_err(|source| SshBackendError::Io {
                stage: "request flush",
                source,
            })?;

            let response_line =
                read_bounded_line(&mut transport.stdout, self.max_line_bytes).await?;
            let response: ResponseFrame = serde_json::from_slice(&response_line)
                .map_err(|_| SshBackendError::InvalidResponse)?;
            if response.protocol_version != PROTOCOL_VERSION {
                return Err(SshBackendError::InvalidResponse);
            }
            if response.request_id != request_id {
                return Err(SshBackendError::UnexpectedResponseId);
            }
            match response.outcome {
                ResponseOutcome::Ok { result } => Ok(result),
                ResponseOutcome::Error { error } => Err(SshBackendError::RemoteRejected {
                    code: error.code,
                    retryable: error.retryable,
                }),
            }
        };

        let result = match tokio::time::timeout(self.request_timeout, exchange).await {
            Ok(result) => result,
            Err(_) => Err(SshBackendError::Timeout {
                stage: "RPC exchange",
            }),
        };
        if result
            .as_ref()
            .is_err_and(SshBackendError::invalidates_transport)
        {
            transport.closed = true;
            let _ = transport.child.start_kill();
        }
        result
    }

    async fn shutdown(&self) {
        let mut transport = self.transport.lock().await;
        if !transport.closed {
            transport.closed = true;
            if let Some(mut stdin) = transport.stdin.take() {
                let _ = stdin.shutdown().await;
                drop(stdin);
            }
        }

        match tokio::time::timeout(self.close_timeout, transport.child.wait()).await {
            Ok(_) => {}
            Err(_) => {
                let _ = transport.child.start_kill();
                let _ = transport.child.wait().await;
            }
        }
    }
}

impl Drop for RpcClient {
    fn drop(&mut self) {
        let transport = self.transport.get_mut();
        transport.closed = true;
        let _ = transport.child.start_kill();
        self.stderr_task.abort();
    }
}

async fn establish_session(
    config: &SshTargetConfig,
    runner_sha256: &str,
    session_id: Option<&str>,
    policy: ClientPolicy,
    environment_refs: BTreeMap<String, EnvironmentRef>,
) -> Result<(RpcClient, HelloResponse, SessionInfo), SshBackendError> {
    let rpc = RpcClient::launch(config, runner_sha256).await?;
    let hello = match rpc
        .request(RunnerRequest::Hello(HelloRequest {
            client_name: "klintcode".to_owned(),
            client_version: env!("CARGO_PKG_VERSION").to_owned(),
        }))
        .await
    {
        Ok(RunnerResponse::Hello(hello)) if hello.protocol_version == PROTOCOL_VERSION => hello,
        Ok(_) => {
            rpc.shutdown().await;
            return Err(SshBackendError::UnexpectedResponseType);
        }
        Err(error) => {
            rpc.shutdown().await;
            return Err(error);
        }
    };
    for capability in [
        "session.attach_or_create",
        "session.detach",
        "fs.stat",
        "fs.read_range",
        "fs.list",
        "fs.walk",
        "fs.search_literal",
        "fs.snapshot",
        "fs.write_atomic",
        "fs.write_cas",
        "artifact.read_chunk",
        "exec.structured",
        "exec.idempotent",
        "exec.cancel",
        "exec.output_cursor",
        "policy.monotonic",
    ] {
        if !hello
            .capabilities
            .iter()
            .any(|advertised| advertised == capability)
        {
            rpc.shutdown().await;
            return Err(SshBackendError::MissingCapability(capability.to_owned()));
        }
    }

    let requested_session_id = session_id.map(str::to_owned);
    let session = match rpc
        .request(RunnerRequest::SessionAttachOrCreate(SessionAttachRequest {
            session_id: requested_session_id.clone(),
            require_existing: requested_session_id.is_some(),
            policy,
            environment_refs: environment_refs.clone(),
        }))
        .await
    {
        Ok(RunnerResponse::Session(session)) => session,
        Ok(_) => {
            rpc.shutdown().await;
            return Err(SshBackendError::UnexpectedResponseType);
        }
        Err(error) => {
            rpc.shutdown().await;
            return Err(error);
        }
    };
    if requested_session_id
        .as_ref()
        .is_some_and(|requested| requested != &session.session_id)
    {
        rpc.shutdown().await;
        return Err(SshBackendError::SessionChanged);
    }
    let advertised_refs = session
        .environment_refs
        .iter()
        .cloned()
        .collect::<std::collections::BTreeSet<_>>();
    let requested_refs = environment_refs
        .keys()
        .cloned()
        .collect::<std::collections::BTreeSet<_>>();
    if !environment_refs.is_empty() && advertised_refs != requested_refs {
        rpc.shutdown().await;
        return Err(SshBackendError::PolicyChanged);
    }
    Ok((rpc, hello, session))
}

async fn install_runner(config: &SshTargetConfig) -> Result<String, SshBackendError> {
    let runner_sha256 = sha256_file(&config.local_runner_path).await?;
    if check_runner_cache(config, &runner_sha256).await? {
        return Ok(runner_sha256);
    }
    let remote_arguments = [
        config.bootstrap_command.as_str(),
        "install",
        "--sha256",
        runner_sha256.as_str(),
    ];
    let ssh_arguments = build_ssh_arguments(config, &remote_arguments)?;

    let mut child = Command::new(&config.ssh_program)
        .args(&ssh_arguments)
        .stdin(Stdio::piped())
        .stdout(Stdio::piped())
        .stderr(Stdio::piped())
        .kill_on_drop(true)
        .spawn()
        .map_err(|source| SshBackendError::Io {
            stage: "bootstrap launch",
            source,
        })?;
    let mut stdin = child.stdin.take().ok_or_else(|| SshBackendError::Io {
        stage: "bootstrap stdin setup",
        source: std::io::Error::other("missing child stdin"),
    })?;
    let mut stdout = child.stdout.take().ok_or_else(|| SshBackendError::Io {
        stage: "bootstrap stdout setup",
        source: std::io::Error::other("missing child stdout"),
    })?;
    let mut stderr = child.stderr.take().ok_or_else(|| SshBackendError::Io {
        stage: "bootstrap stderr setup",
        source: std::io::Error::other("missing child stderr"),
    })?;

    let mut runner = tokio::fs::File::open(&config.local_runner_path)
        .await
        .map_err(|source| SshBackendError::Io {
            stage: "runner upload open",
            source,
        })?;
    let upload = async {
        tokio::io::copy(&mut runner, &mut stdin)
            .await
            .map_err(|source| SshBackendError::Io {
                stage: "runner upload",
                source,
            })?;
        stdin
            .shutdown()
            .await
            .map_err(|source| SshBackendError::Io {
                stage: "runner upload close",
                source,
            })?;
        // `shutdown` is not guaranteed to close a pipe handle. The bootstrap
        // reads until EOF, so release ChildStdin before waiting for it.
        drop(stdin);
        Ok(())
    };
    let stdout_drain = drain_counted(&mut stdout);
    let stderr_drain = drain_counted(&mut stderr);
    let completion = async {
        let (upload_result, stdout_result, stderr_result, wait_result) =
            tokio::join!(upload, stdout_drain, stderr_drain, child.wait());
        upload_result?;
        let stdout_bytes = stdout_result.map_err(|source| SshBackendError::Io {
            stage: "bootstrap stdout",
            source,
        })?;
        let stderr_bytes = stderr_result.map_err(|source| SshBackendError::Io {
            stage: "bootstrap stderr",
            source,
        })?;
        let status = wait_result.map_err(|source| SshBackendError::Io {
            stage: "bootstrap wait",
            source,
        })?;
        if stdout_bytes.saturating_add(stderr_bytes) > config.max_install_output_bytes {
            return Err(SshBackendError::InstallOutputTooLarge);
        }
        if !status.success() {
            return Err(SshBackendError::InstallFailed {
                code: status.code().unwrap_or(-1),
            });
        }
        Ok(())
    };

    match tokio::time::timeout(config.upload_timeout, completion).await {
        Ok(result) => result?,
        Err(_) => {
            let _ = child.start_kill();
            let _ = child.wait().await;
            return Err(SshBackendError::Timeout {
                stage: "bootstrap install",
            });
        }
    }

    Ok(runner_sha256)
}

#[derive(Debug, Deserialize)]
struct BootstrapCheckResponse {
    ok: bool,
    operation: String,
    sha256: String,
    present: bool,
}

async fn check_runner_cache(
    config: &SshTargetConfig,
    runner_sha256: &str,
) -> Result<bool, SshBackendError> {
    let remote_arguments = [
        config.bootstrap_command.as_str(),
        "check",
        "--sha256",
        runner_sha256,
    ];
    let ssh_arguments = build_ssh_arguments(config, &remote_arguments)?;
    let mut child = Command::new(&config.ssh_program)
        .args(&ssh_arguments)
        .stdin(Stdio::null())
        .stdout(Stdio::piped())
        .stderr(Stdio::piped())
        .kill_on_drop(true)
        .spawn()
        .map_err(|source| SshBackendError::Io {
            stage: "bootstrap check launch",
            source,
        })?;
    let mut stdout = child.stdout.take().ok_or_else(|| SshBackendError::Io {
        stage: "bootstrap check stdout setup",
        source: std::io::Error::other("missing child stdout"),
    })?;
    let mut stderr = child.stderr.take().ok_or_else(|| SshBackendError::Io {
        stage: "bootstrap check stderr setup",
        source: std::io::Error::other("missing child stderr"),
    })?;
    let completion = async {
        let (stdout_result, stderr_result, wait_result) = tokio::join!(
            drain_captured(&mut stdout, config.max_install_output_bytes),
            drain_counted(&mut stderr),
            child.wait()
        );
        let stdout = stdout_result.map_err(|source| SshBackendError::Io {
            stage: "bootstrap check stdout",
            source,
        })?;
        let stderr_bytes = stderr_result.map_err(|source| SshBackendError::Io {
            stage: "bootstrap check stderr",
            source,
        })?;
        let status = wait_result.map_err(|source| SshBackendError::Io {
            stage: "bootstrap check wait",
            source,
        })?;
        if stdout.total.saturating_add(stderr_bytes) > config.max_install_output_bytes {
            return Err(SshBackendError::InstallOutputTooLarge);
        }
        if !status.success() {
            return Err(SshBackendError::CheckFailed {
                code: status.code().unwrap_or(-1),
            });
        }
        let response: BootstrapCheckResponse = serde_json::from_slice(&stdout.retained)
            .map_err(|_| SshBackendError::InvalidCheckResponse)?;
        if !response.ok || response.operation != "check" || response.sha256 != runner_sha256 {
            return Err(SshBackendError::InvalidCheckResponse);
        }
        Ok(response.present)
    };

    match tokio::time::timeout(config.connect_timeout, completion).await {
        Ok(result) => result,
        Err(_) => {
            let _ = child.start_kill();
            let _ = child.wait().await;
            Err(SshBackendError::Timeout {
                stage: "bootstrap check",
            })
        }
    }
}

async fn sha256_file(path: &Path) -> Result<String, SshBackendError> {
    let mut file = tokio::fs::File::open(path)
        .await
        .map_err(|source| SshBackendError::Io {
            stage: "runner digest open",
            source,
        })?;
    let mut digest = Sha256::new();
    let mut buffer = [0_u8; 64 * 1024];
    loop {
        let count = file
            .read(&mut buffer)
            .await
            .map_err(|source| SshBackendError::Io {
                stage: "runner digest read",
                source,
            })?;
        if count == 0 {
            break;
        }
        digest.update(&buffer[..count]);
    }
    Ok(format!("{:x}", digest.finalize()))
}

#[cfg(test)]
fn sha256_bytes(bytes: &[u8]) -> String {
    let mut digest = Sha256::new();
    digest.update(bytes);
    format!("{:x}", digest.finalize())
}

fn build_ssh_arguments(
    config: &SshTargetConfig,
    remote_arguments: &[&str],
) -> Result<Vec<OsString>, SshBackendError> {
    config.validate()?;
    for argument in remote_arguments {
        validate_remote_argument(argument)?;
    }

    let mut arguments = vec![
        OsString::from("-T"),
        OsString::from("-o"),
        OsString::from("BatchMode=yes"),
        OsString::from("-o"),
        OsString::from("StrictHostKeyChecking=yes"),
        OsString::from("-o"),
        OsString::from("PasswordAuthentication=no"),
        OsString::from("-o"),
        OsString::from("KbdInteractiveAuthentication=no"),
        OsString::from("-o"),
        OsString::from("NumberOfPasswordPrompts=0"),
        OsString::from("-o"),
        OsString::from("IdentitiesOnly=yes"),
        OsString::from("-o"),
        OsString::from("ServerAliveInterval=15"),
        OsString::from("-o"),
        OsString::from("ServerAliveCountMax=3"),
        OsString::from("-o"),
        OsString::from("ConnectionAttempts=1"),
        OsString::from("-o"),
        OsString::from("ClearAllForwardings=yes"),
        OsString::from("-o"),
        OsString::from("ForwardAgent=no"),
        OsString::from("-o"),
        OsString::from("ForwardX11=no"),
        OsString::from("-o"),
        OsString::from("PermitLocalCommand=no"),
        OsString::from("-o"),
        OsString::from("RequestTTY=no"),
        OsString::from("-o"),
        OsString::from("RemoteCommand=none"),
        OsString::from("-o"),
        OsString::from("EscapeChar=none"),
        OsString::from("-o"),
        OsString::from(format!(
            "ConnectTimeout={}",
            config.connect_timeout.as_secs().max(1)
        )),
    ];
    if let Some(config_path) = &config.ssh_config_path {
        arguments.push(OsString::from("-F"));
        arguments.push(config_path.as_os_str().to_owned());
    }
    if let Some(known_hosts_path) = &config.known_hosts_path {
        arguments.push(OsString::from("-o"));
        arguments.push(OsString::from(format!(
            "UserKnownHostsFile={}",
            known_hosts_path.to_string_lossy()
        )));
    }
    arguments.push(OsString::from(&config.target_alias));
    arguments.extend(
        remote_arguments
            .iter()
            .map(|argument| OsString::from(*argument)),
    );
    Ok(arguments)
}

fn validate_target_alias(alias: &str) -> Result<(), SshBackendError> {
    let mut characters = alias.chars();
    if !characters
        .next()
        .is_some_and(|character| character.is_ascii_alphanumeric())
        || !characters.all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.')
        })
    {
        return Err(SshBackendError::InvalidConfig(
            "target_alias must be a simple SSH Host alias",
        ));
    }
    Ok(())
}

fn validate_remote_executable(executable: &str) -> Result<(), SshBackendError> {
    if executable.is_empty()
        || !executable.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.' | '/')
        })
    {
        return Err(SshBackendError::InvalidConfig(
            "bootstrap_command must be one safe executable token",
        ));
    }
    Ok(())
}

fn validate_remote_argument(argument: &str) -> Result<(), SshBackendError> {
    if argument.is_empty()
        || !argument.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.' | '/')
        })
    {
        return Err(SshBackendError::InvalidConfig(
            "bootstrap arguments must be safe single tokens",
        ));
    }
    Ok(())
}

fn validate_protocol_string(value: &str) -> Result<(), SshBackendError> {
    if value.is_empty() || value.contains('\0') {
        return Err(SshBackendError::InvalidConfig(
            "protocol identifiers must be non-empty and contain no NUL bytes",
        ));
    }
    Ok(())
}

fn validate_exec_request(request: &ExecRequest) -> Result<(), SshBackendError> {
    for value in [
        request.operation_id.as_str(),
        request.program.as_str(),
        request.cwd.as_str(),
    ] {
        validate_protocol_string(value)?;
    }
    if request.argv.iter().any(|argument| argument.contains('\0'))
        || request
            .env
            .iter()
            .any(|(name, value)| name.contains('\0') || value.contains('\0'))
    {
        return Err(SshBackendError::InvalidConfig(
            "process argv and environment cannot contain NUL bytes",
        ));
    }
    Ok(())
}

async fn read_bounded_line<R>(
    reader: &mut R,
    max_line_bytes: usize,
) -> Result<Vec<u8>, SshBackendError>
where
    R: AsyncBufRead + Unpin,
{
    let mut line = Vec::with_capacity(max_line_bytes.min(8 * 1024));
    loop {
        let available = reader
            .fill_buf()
            .await
            .map_err(|source| SshBackendError::Io {
                stage: "response read",
                source,
            })?;
        if available.is_empty() {
            return Err(SshBackendError::UnexpectedEof);
        }

        let newline = available.iter().position(|byte| *byte == b'\n');
        let consumed = newline.map_or(available.len(), |index| index + 1);
        let content_length = newline.unwrap_or(available.len());
        if line.len().saturating_add(content_length) > max_line_bytes {
            return Err(SshBackendError::ResponseTooLarge);
        }
        line.extend_from_slice(&available[..content_length]);
        reader.consume(consumed);
        if newline.is_some() {
            if line.last() == Some(&b'\r') {
                line.pop();
            }
            if line.is_empty() {
                return Err(SshBackendError::InvalidResponse);
            }
            return Ok(line);
        }
    }
}

async fn drain_counted<R>(reader: &mut R) -> std::io::Result<usize>
where
    R: AsyncRead + Unpin,
{
    let mut seen = 0_usize;
    let mut buffer = [0_u8; 8 * 1024];
    loop {
        let count = reader.read(&mut buffer).await?;
        if count == 0 {
            return Ok(seen);
        }
        seen = seen.saturating_add(count);
    }
}

struct CapturedBytes {
    retained: Vec<u8>,
    total: usize,
}

async fn drain_captured<R>(reader: &mut R, retain_limit: usize) -> std::io::Result<CapturedBytes>
where
    R: AsyncRead + Unpin,
{
    let mut retained = Vec::with_capacity(retain_limit.min(8 * 1024));
    let mut total = 0_usize;
    let mut buffer = [0_u8; 8 * 1024];
    loop {
        let count = reader.read(&mut buffer).await?;
        if count == 0 {
            return Ok(CapturedBytes { retained, total });
        }
        total = total.saturating_add(count);
        let keep = retain_limit.saturating_sub(retained.len()).min(count);
        retained.extend_from_slice(&buffer[..keep]);
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn strings(arguments: Vec<OsString>) -> Vec<String> {
        arguments
            .into_iter()
            .map(|argument| argument.into_string().expect("UTF-8 test argument"))
            .collect()
    }

    #[test]
    fn sha256_matches_standard_vector() {
        assert_eq!(
            sha256_bytes(b"abc"),
            "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad"
        );
    }

    #[test]
    fn bootstrap_check_response_is_strictly_typed() {
        let digest = "a".repeat(64);
        let response: BootstrapCheckResponse = serde_json::from_value(serde_json::json!({
            "ok": true,
            "operation": "check",
            "sha256": digest,
            "present": true,
            "bytes": 42
        }))
        .unwrap();
        assert!(response.ok);
        assert_eq!(response.operation, "check");
        assert!(response.present);
    }

    #[test]
    fn ssh_arguments_disable_interactive_and_forwarding_features() {
        let mut config = SshTargetConfig::new("ca-mini", "/tmp/klintcode-runner");
        config.ssh_config_path = Some(PathBuf::from("/tmp/ssh config"));
        config.known_hosts_path = Some(PathBuf::from("/tmp/known_hosts"));
        config.connect_timeout = Duration::from_secs(9);
        let digest = "a".repeat(64);

        let arguments = strings(
            build_ssh_arguments(
                &config,
                &[
                    "klintcode-bootstrap",
                    "launch",
                    "--sha256",
                    &digest,
                    "--",
                    "--stdio",
                ],
            )
            .expect("valid arguments"),
        );

        assert_eq!(arguments.first().map(String::as_str), Some("-T"));
        for setting in [
            "BatchMode=yes",
            "StrictHostKeyChecking=yes",
            "PasswordAuthentication=no",
            "KbdInteractiveAuthentication=no",
            "NumberOfPasswordPrompts=0",
            "IdentitiesOnly=yes",
            "ServerAliveInterval=15",
            "ServerAliveCountMax=3",
            "ConnectionAttempts=1",
            "ClearAllForwardings=yes",
            "ForwardAgent=no",
            "ForwardX11=no",
            "PermitLocalCommand=no",
            "RequestTTY=no",
            "RemoteCommand=none",
            "EscapeChar=none",
            "ConnectTimeout=9",
        ] {
            assert!(arguments.iter().any(|argument| argument == setting));
        }
        assert!(
            arguments
                .windows(2)
                .any(|pair| pair == ["-F", "/tmp/ssh config"])
        );
        assert!(
            arguments
                .iter()
                .any(|argument| argument == "UserKnownHostsFile=/tmp/known_hosts")
        );
        let target_position = arguments
            .iter()
            .position(|argument| argument == "ca-mini")
            .expect("target alias");
        assert_eq!(
            &arguments[target_position + 1..],
            [
                "klintcode-bootstrap",
                "launch",
                "--sha256",
                digest.as_str(),
                "--",
                "--stdio",
            ]
        );
    }

    #[test]
    fn ssh_arguments_reject_shell_syntax() {
        let config = SshTargetConfig::new("ca-mini;touch-pwned", "/tmp/runner");
        assert!(build_ssh_arguments(&config, &["bootstrap", "launch"]).is_err());

        let config = SshTargetConfig::new("ca-mini", "/tmp/runner");
        assert!(build_ssh_arguments(&config, &["bootstrap", "$(bad)"]).is_err());
    }
}
