//! Versioned NDJSON protocol shared by Agent bridges and the remote runner.
//!
//! The protocol deliberately represents process launches as `program + argv`.
//! There is no remote shell command string in the wire format.

use serde::{Deserialize, Serialize};
use std::collections::BTreeMap;

/// Current wire protocol version.
pub const PROTOCOL_VERSION: u32 = 1;

/// Maximum encoded size of one NDJSON request or response frame.
///
/// JSON may encode one input byte as a six-byte `\u00xx` escape. Eight MiB
/// therefore leaves room for the default one-MiB write limit plus bounded
/// protocol metadata.
pub const MAX_FRAME_BYTES: usize = 8 * 1024 * 1024;

/// One request per NDJSON line.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct RequestFrame {
    pub request_id: String,
    pub protocol_version: u32,
    #[serde(flatten)]
    pub request: RunnerRequest,
}

/// One response per NDJSON line.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ResponseFrame {
    pub request_id: String,
    pub protocol_version: u32,
    #[serde(flatten)]
    pub outcome: ResponseOutcome,
}

impl ResponseFrame {
    pub fn success(request_id: impl Into<String>, result: RunnerResponse) -> Self {
        Self {
            request_id: request_id.into(),
            protocol_version: PROTOCOL_VERSION,
            outcome: ResponseOutcome::Ok { result },
        }
    }

    pub fn error(request_id: impl Into<String>, error: ProtocolError) -> Self {
        Self {
            request_id: request_id.into(),
            protocol_version: PROTOCOL_VERSION,
            outcome: ResponseOutcome::Error { error },
        }
    }
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(tag = "status", rename_all = "snake_case")]
// This is a short-lived wire value. Keeping the JSON representation direct is
// clearer than adding heap-only indirection for the smaller error branch.
#[allow(clippy::large_enum_variant)]
pub enum ResponseOutcome {
    Ok { result: RunnerResponse },
    Error { error: ProtocolError },
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(tag = "method", content = "params", rename_all = "snake_case")]
pub enum RunnerRequest {
    /// Must be the first valid request on a connection.
    Hello(HelloRequest),
    /// Attach to an existing session or atomically create it if absent.
    SessionAttachOrCreate(SessionAttachRequest),
    /// Detach this bridge without deleting the persistent session workspace.
    SessionDetach,
    FsStat(StatRequest),
    FsReadRange(ReadRangeRequest),
    FsList(ListRequest),
    FsWalk(WalkRequest),
    FsSearch(SearchRequest),
    FsSnapshot(SnapshotRequest),
    FsWriteAtomic(WriteAtomicRequest),
    FsWriteCas(WriteCasRequest),
    ArtifactReadChunk(ArtifactReadChunkRequest),
    ExecStart(ExecRequest),
    ExecStatus(OperationStatusRequest),
    /// Read bounded incremental output for one running operation.
    ExecOutput(OperationOutputRequest),
    /// Idempotently cancel an operation and wait until it is no longer running.
    ExecCancel(OperationStatusRequest),
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(tag = "type", content = "data", rename_all = "snake_case")]
#[allow(clippy::large_enum_variant)] // Keep the public RPC variants inline.
pub enum RunnerResponse {
    Hello(HelloResponse),
    Session(SessionInfo),
    SessionDetached(SessionDetachedResponse),
    Stat(StatResponse),
    ReadRange(ReadRangeResponse),
    List(ListResponse),
    Walk(WalkResponse),
    Search(SearchResponse),
    Snapshot(SnapshotResponse),
    WriteAtomic(WriteAtomicResponse),
    WriteCas(WriteCasResponse),
    ArtifactChunk(ArtifactReadChunkResponse),
    Exec(OperationResponse),
    OperationStatus(OperationRecord),
    OperationOutput(OperationOutputResponse),
    ExecCancelled(CancelOperationResponse),
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct HelloRequest {
    pub client_name: String,
    #[serde(default)]
    pub client_version: String,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct HelloResponse {
    pub runner_name: String,
    pub runner_version: String,
    pub protocol_version: u32,
    pub capabilities: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SessionAttachRequest {
    /// If omitted, the runner generates a cryptographically-random UUID.
    #[serde(default)]
    pub session_id: Option<String>,
    /// Refuse to create a replacement when the requested session is absent.
    /// Clients must set this for attach and reconnect recovery paths.
    #[serde(default)]
    pub require_existing: bool,
    /// A client policy is an additional restriction. It never replaces the
    /// hard policy configured on the remote host.
    #[serde(default)]
    pub policy: ClientPolicy,
    /// Named values that commands may explicitly select through `env_refs`.
    /// Host values are resolved only by the runner and are never persisted.
    #[serde(default)]
    pub environment_refs: BTreeMap<String, EnvironmentRef>,
}

/// A server-resolved environment value declared for one task session.
#[derive(Debug, Clone, Copy, Serialize, Deserialize, PartialEq, Eq)]
#[serde(tag = "kind", rename_all = "snake_case")]
pub enum EnvironmentRef {
    /// Resolve the identically named variable from the runner host. The name
    /// must be present in the host-owned `allowed_env_refs` policy.
    Host,
    /// Generate a stable SQLite URL private to this remote session.
    SessionSqlite,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SessionInfo {
    pub session_id: String,
    /// Logical workspace root. Remote host filesystem paths are never exposed
    /// through the protocol; v1 uses `.` for the attached workspace.
    pub workspace: String,
    pub created: bool,
    pub policy_digest: String,
    pub effective_policy: EffectivePolicy,
    /// Environment references available to structured exec requests.
    #[serde(default)]
    pub environment_refs: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SessionDetachedResponse {
    pub session_id: String,
}

/// Host-owned policy. The client cannot change these upper bounds.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(default, deny_unknown_fields)]
pub struct HardPolicy {
    pub allowed_programs: Vec<String>,
    /// Host-owned logical program name to absolute executable mapping. Missing
    /// defaults are resolved from fixed host toolchain locations at startup.
    pub program_paths: BTreeMap<String, String>,
    /// Runner-resolved executable identities. This is intentionally ignored
    /// when deserializing host policy: clients and policy files may select an
    /// entry path, but only the runner may attest its canonical target.
    #[serde(skip)]
    pub program_identities: BTreeMap<String, ProgramIdentity>,
    pub allowed_env: Vec<String>,
    pub allowed_env_refs: Vec<String>,
    pub inherited_env: Vec<String>,
    pub readable: Vec<String>,
    pub writable: Vec<String>,
    pub denied: Vec<String>,
    pub max_timeout_secs: u64,
    pub max_output_bytes: u64,
    pub max_read_bytes: u64,
    pub max_write_bytes: u64,
    pub max_list_entries: u32,
    pub max_args: u32,
    pub max_arg_bytes: u64,
    pub allow_session_sqlite: bool,
}

impl Default for HardPolicy {
    fn default() -> Self {
        Self {
            allowed_programs: vec![
                "cargo".into(),
                "rustc".into(),
                "git".into(),
                "mvn".into(),
                "java".into(),
            ],
            program_paths: BTreeMap::new(),
            program_identities: BTreeMap::new(),
            allowed_env: Vec::new(),
            allowed_env_refs: vec![
                "JAVA_HOME".into(),
                "MAVEN_HOME".into(),
                "M2_HOME".into(),
                "GRADLE_HOME".into(),
                "GRADLE_USER_HOME".into(),
            ],
            inherited_env: vec![
                "JAVA_HOME".into(),
                "MAVEN_HOME".into(),
                "M2_HOME".into(),
                "GRADLE_HOME".into(),
                "GRADLE_USER_HOME".into(),
                "LANG".into(),
                "LC_ALL".into(),
                "PATH".into(),
                "TMPDIR".into(),
                "USER".into(),
            ],
            readable: vec!["**".into()],
            writable: vec!["**".into()],
            denied: vec![
                ".env".into(),
                "**/.env".into(),
                "*.key".into(),
                "**/*.key".into(),
                "*.pem".into(),
                "**/*.pem".into(),
                "secrets".into(),
                "secrets/**".into(),
                "**/secrets/**".into(),
                ".git".into(),
                ".git/**".into(),
                "**/.git".into(),
                "**/.git/**".into(),
                "lib/src".into(),
                "lib/src/**".into(),
                "**/lib/src".into(),
                "**/lib/src/**".into(),
                // Pipeline-owned evidence below nested `.klintcode` directories
                // must remain available. Only the session-root subtrees created
                // and consumed exclusively by the runner are denied here.
                ".klintcode/runtime".into(),
                ".klintcode/runtime/**".into(),
                ".klintcode/cargo-home".into(),
                ".klintcode/cargo-home/**".into(),
            ],
            max_timeout_secs: 3_600,
            max_output_bytes: 256 * 1024,
            max_read_bytes: 256 * 1024,
            max_write_bytes: 1024 * 1024,
            max_list_entries: 2_000,
            max_args: 256,
            max_arg_bytes: 64 * 1024,
            allow_session_sqlite: true,
        }
    }
}

/// Immutable identity of a validated host executable mapping.
///
/// `entry_path` deliberately preserves a final symlink such as rustup's
/// `cargo` proxy, because its basename controls rustup dispatch. The target
/// digest prevents a persisted session from accepting a replaced proxy.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ProgramIdentity {
    pub entry_path: String,
    pub canonical_target: String,
    pub target_sha256: String,
}

/// Optional restrictions supplied when attaching to a session.
///
/// `None` inherits the current effective limit. `Some([])` denies the
/// corresponding capability. Path allowlists are added as another mandatory
/// matching layer, so even a broad client glob cannot widen the server policy.
#[derive(Debug, Clone, Default, Serialize, Deserialize, PartialEq, Eq)]
#[serde(default)]
pub struct ClientPolicy {
    pub allowed_programs: Option<Vec<String>>,
    pub allowed_env: Option<Vec<String>>,
    pub allowed_env_refs: Option<Vec<String>>,
    pub inherited_env: Option<Vec<String>>,
    pub readable: Option<Vec<String>>,
    pub writable: Option<Vec<String>>,
    pub denied: Vec<String>,
    pub max_timeout_secs: Option<u64>,
    pub max_output_bytes: Option<u64>,
    pub max_read_bytes: Option<u64>,
    pub max_write_bytes: Option<u64>,
    pub max_list_entries: Option<u32>,
    pub allow_session_sqlite: Option<bool>,
}

/// Persisted policy actually enforced for a session.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct EffectivePolicy {
    pub allowed_programs: Vec<String>,
    #[serde(default)]
    pub program_paths: BTreeMap<String, String>,
    #[serde(default)]
    pub program_identities: BTreeMap<String, ProgramIdentity>,
    pub allowed_env: Vec<String>,
    #[serde(default)]
    pub allowed_env_refs: Vec<String>,
    pub inherited_env: Vec<String>,
    /// Every layer must match; this preserves intersection semantics for globs.
    pub readable_layers: Vec<Vec<String>>,
    /// Every layer must match; this preserves intersection semantics for globs.
    pub writable_layers: Vec<Vec<String>>,
    pub denied: Vec<String>,
    pub max_timeout_secs: u64,
    pub max_output_bytes: u64,
    pub max_read_bytes: u64,
    pub max_write_bytes: u64,
    pub max_list_entries: u32,
    pub max_args: u32,
    pub max_arg_bytes: u64,
    #[serde(default)]
    pub allow_session_sqlite: bool,
}

impl EffectivePolicy {
    pub fn from_hard_policy(policy: &HardPolicy) -> Self {
        Self {
            allowed_programs: sorted_dedup(policy.allowed_programs.clone()),
            program_paths: policy.program_paths.clone(),
            program_identities: policy.program_identities.clone(),
            allowed_env: sorted_dedup(policy.allowed_env.clone()),
            allowed_env_refs: sorted_dedup(policy.allowed_env_refs.clone()),
            inherited_env: sorted_dedup(policy.inherited_env.clone()),
            readable_layers: vec![sorted_dedup(policy.readable.clone())],
            writable_layers: vec![sorted_dedup(policy.writable.clone())],
            denied: sorted_dedup(policy.denied.clone()),
            max_timeout_secs: policy.max_timeout_secs,
            max_output_bytes: policy.max_output_bytes,
            max_read_bytes: policy.max_read_bytes,
            max_write_bytes: policy.max_write_bytes,
            max_list_entries: policy.max_list_entries,
            max_args: policy.max_args,
            max_arg_bytes: policy.max_arg_bytes,
            allow_session_sqlite: policy.allow_session_sqlite,
        }
    }

    /// Apply another monotonic restriction to this policy.
    pub fn narrow(&self, policy: &ClientPolicy) -> Self {
        let mut narrowed = self.clone();
        if let Some(items) = &policy.allowed_programs {
            narrowed.allowed_programs = intersect(&narrowed.allowed_programs, items);
            narrowed
                .program_paths
                .retain(|name, _| narrowed.allowed_programs.contains(name));
            narrowed
                .program_identities
                .retain(|name, _| narrowed.allowed_programs.contains(name));
        }
        if let Some(items) = &policy.allowed_env {
            narrowed.allowed_env = intersect(&narrowed.allowed_env, items);
        }
        if let Some(items) = &policy.allowed_env_refs {
            narrowed.allowed_env_refs = intersect(&narrowed.allowed_env_refs, items);
        }
        if let Some(items) = &policy.inherited_env {
            narrowed.inherited_env = intersect(&narrowed.inherited_env, items);
        }
        if let Some(patterns) = &policy.readable {
            push_unique_layer(&mut narrowed.readable_layers, patterns);
        }
        if let Some(patterns) = &policy.writable {
            push_unique_layer(&mut narrowed.writable_layers, patterns);
        }
        narrowed.denied.extend(policy.denied.iter().cloned());
        narrowed.denied = sorted_dedup(narrowed.denied);
        narrowed.max_timeout_secs = min_if_some(narrowed.max_timeout_secs, policy.max_timeout_secs);
        narrowed.max_output_bytes = min_if_some(narrowed.max_output_bytes, policy.max_output_bytes);
        narrowed.max_read_bytes = min_if_some(narrowed.max_read_bytes, policy.max_read_bytes);
        narrowed.max_write_bytes = min_if_some(narrowed.max_write_bytes, policy.max_write_bytes);
        narrowed.max_list_entries = min_if_some(narrowed.max_list_entries, policy.max_list_entries);
        if policy.allow_session_sqlite == Some(false) {
            narrowed.allow_session_sqlite = false;
        }
        narrowed
    }

    /// Re-apply the host policy when attaching an older persistent session.
    /// This makes remote policy tightening effective immediately, while a
    /// later, broader host policy still cannot restore permissions previously
    /// removed from the session.
    pub fn narrow_with_hard_policy(&self, policy: &HardPolicy) -> Self {
        let mut narrowed = self.clone();
        narrowed.allowed_programs = intersect(&narrowed.allowed_programs, &policy.allowed_programs);
        narrowed
            .program_paths
            .retain(|name, path| policy.program_paths.get(name) == Some(path));
        if !policy.program_identities.is_empty() {
            narrowed.program_identities.retain(|name, identity| {
                policy.program_identities.get(name) == Some(identity)
                    && narrowed.program_paths.contains_key(name)
            });
            narrowed.allowed_programs.retain(|name| {
                narrowed.program_paths.contains_key(name)
                    && narrowed.program_identities.contains_key(name)
            });
        }
        narrowed.allowed_env = intersect(&narrowed.allowed_env, &policy.allowed_env);
        narrowed.allowed_env_refs = intersect(&narrowed.allowed_env_refs, &policy.allowed_env_refs);
        narrowed.inherited_env = intersect(&narrowed.inherited_env, &policy.inherited_env);
        push_unique_layer(&mut narrowed.readable_layers, &policy.readable);
        push_unique_layer(&mut narrowed.writable_layers, &policy.writable);
        narrowed.denied.extend(policy.denied.iter().cloned());
        narrowed.denied = sorted_dedup(narrowed.denied);
        narrowed.max_timeout_secs = narrowed.max_timeout_secs.min(policy.max_timeout_secs);
        narrowed.max_output_bytes = narrowed.max_output_bytes.min(policy.max_output_bytes);
        narrowed.max_read_bytes = narrowed.max_read_bytes.min(policy.max_read_bytes);
        narrowed.max_write_bytes = narrowed.max_write_bytes.min(policy.max_write_bytes);
        narrowed.max_list_entries = narrowed.max_list_entries.min(policy.max_list_entries);
        narrowed.max_args = narrowed.max_args.min(policy.max_args);
        narrowed.max_arg_bytes = narrowed.max_arg_bytes.min(policy.max_arg_bytes);
        narrowed.allow_session_sqlite &= policy.allow_session_sqlite;
        narrowed
    }

    /// Return whether this policy is no broader than `previous`.
    ///
    /// This is used during reconnect validation. A host may tighten a
    /// persisted session policy, but a reconnect must never silently restore
    /// a capability that the session had already surrendered.
    pub fn is_narrower_or_equal_to(&self, previous: &Self) -> bool {
        is_subset(&self.allowed_programs, &previous.allowed_programs)
            && self
                .program_paths
                .iter()
                .all(|(name, path)| previous.program_paths.get(name) == Some(path))
            && self
                .program_identities
                .iter()
                .all(|(name, identity)| previous.program_identities.get(name) == Some(identity))
            && is_subset(&self.allowed_env, &previous.allowed_env)
            && is_subset(&self.allowed_env_refs, &previous.allowed_env_refs)
            && is_subset(&self.inherited_env, &previous.inherited_env)
            && previous
                .readable_layers
                .iter()
                .all(|layer| self.readable_layers.contains(layer))
            && previous
                .writable_layers
                .iter()
                .all(|layer| self.writable_layers.contains(layer))
            && is_subset(&previous.denied, &self.denied)
            && self.max_timeout_secs <= previous.max_timeout_secs
            && self.max_output_bytes <= previous.max_output_bytes
            && self.max_read_bytes <= previous.max_read_bytes
            && self.max_write_bytes <= previous.max_write_bytes
            && self.max_list_entries <= previous.max_list_entries
            && self.max_args <= previous.max_args
            && self.max_arg_bytes <= previous.max_arg_bytes
            && (!self.allow_session_sqlite || previous.allow_session_sqlite)
    }
}

fn is_subset(values: &[String], superset: &[String]) -> bool {
    values.iter().all(|value| superset.contains(value))
}

fn intersect(current: &[String], requested: &[String]) -> Vec<String> {
    sorted_dedup(
        current
            .iter()
            .filter(|item| requested.contains(item))
            .cloned()
            .collect(),
    )
}

fn sorted_dedup(mut values: Vec<String>) -> Vec<String> {
    values.sort();
    values.dedup();
    values
}

fn push_unique_layer(layers: &mut Vec<Vec<String>>, values: &[String]) {
    let layer = sorted_dedup(values.to_vec());
    if !layers.contains(&layer) {
        layers.push(layer);
    }
}

fn min_if_some<T: Ord + Copy>(current: T, requested: Option<T>) -> T {
    requested.map_or(current, |value| current.min(value))
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct StatRequest {
    pub path: String,
    #[serde(default)]
    pub include_sha256: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct StatResponse {
    pub path: String,
    pub exists: bool,
    #[serde(default)]
    pub kind: Option<FileKind>,
    #[serde(default)]
    pub bytes: Option<u64>,
    #[serde(default)]
    pub sha256: Option<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ReadRangeRequest {
    pub path: String,
    #[serde(default)]
    pub offset: u64,
    pub length: u64,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ReadRangeResponse {
    pub path: String,
    pub offset: u64,
    pub bytes: u64,
    pub eof: bool,
    pub content: String,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ListRequest {
    /// Empty string or `.` lists the workspace root.
    #[serde(default)]
    pub path: String,
    #[serde(default)]
    pub max_entries: Option<u32>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ListResponse {
    pub path: String,
    pub entries: Vec<DirectoryEntry>,
    pub truncated: bool,
}

/// Deterministic recursive directory enumeration.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WalkRequest {
    /// Empty string or `.` walks the workspace root.
    #[serde(default)]
    pub path: String,
    #[serde(default)]
    pub max_entries: Option<u32>,
    #[serde(default)]
    pub max_depth: Option<u32>,
    /// Workspace-relative subtrees that must not be traversed.
    #[serde(default)]
    pub excluded_paths: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WalkResponse {
    pub path: String,
    pub entries: Vec<WalkEntry>,
    pub truncated: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WalkEntry {
    /// Workspace-relative path, using `/` separators.
    pub path: String,
    pub kind: FileKind,
    pub bytes: u64,
}

/// Bounded literal text search over readable regular files.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SearchRequest {
    #[serde(default)]
    pub path: String,
    pub query: String,
    #[serde(default)]
    pub max_matches: Option<u32>,
    /// Files larger than this are skipped. The effective value cannot exceed
    /// the session `max_read_bytes` policy.
    #[serde(default)]
    pub max_file_bytes: Option<u64>,
    /// Workspace-relative subtrees that must not be traversed by this search.
    #[serde(default)]
    pub excluded_paths: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SearchResponse {
    pub path: String,
    pub matches: Vec<SearchMatch>,
    pub files_scanned: u32,
    pub files_skipped: u32,
    pub bytes_scanned: u64,
    pub truncated: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SearchMatch {
    pub path: String,
    /// One-based line number.
    pub line: u64,
    /// One-based byte column in the UTF-8 line.
    pub column: u64,
    /// A UTF-8-safe, bounded prefix of the matching line.
    pub preview: String,
}

/// Deterministic manifest and digest of the readable workspace view.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SnapshotRequest {
    #[serde(default)]
    pub path: String,
    #[serde(default)]
    pub max_entries: Option<u32>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SnapshotResponse {
    pub path: String,
    pub entries: Vec<SnapshotEntry>,
    pub tree_sha256: String,
    pub total_file_bytes: u64,
    pub truncated: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct SnapshotEntry {
    pub path: String,
    pub kind: FileKind,
    /// Zero for non-file entries.
    pub bytes: u64,
    #[serde(default)]
    pub sha256: Option<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct DirectoryEntry {
    pub name: String,
    pub kind: FileKind,
    pub bytes: u64,
}

#[derive(Debug, Clone, Copy, Serialize, Deserialize, PartialEq, Eq)]
#[serde(rename_all = "snake_case")]
pub enum FileKind {
    File,
    Directory,
    Symlink,
    Other,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WriteAtomicRequest {
    pub path: String,
    pub content: String,
    #[serde(default)]
    pub create_parents: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WriteAtomicResponse {
    pub path: String,
    pub bytes: u64,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WriteCasRequest {
    pub path: String,
    pub content: String,
    #[serde(default)]
    pub create_parents: bool,
    pub expected: ExpectedFileState,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(tag = "state", rename_all = "snake_case")]
pub enum ExpectedFileState {
    Missing,
    Sha256 { value: String },
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct WriteCasResponse {
    pub path: String,
    pub bytes: u64,
    pub sha256: String,
}

/// Binary-safe, bounded artifact export. Bytes use lowercase hexadecimal so
/// the protocol requires no unbounded or platform-specific archive tooling.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ArtifactReadChunkRequest {
    pub path: String,
    #[serde(default)]
    pub offset: u64,
    pub length: u64,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ArtifactReadChunkResponse {
    pub path: String,
    pub offset: u64,
    pub bytes: u64,
    pub total_bytes: u64,
    pub eof: bool,
    pub data_hex: String,
    pub chunk_sha256: String,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ExecRequest {
    pub operation_id: String,
    pub program: String,
    #[serde(default)]
    pub argv: Vec<String>,
    #[serde(default = "default_cwd")]
    pub cwd: String,
    #[serde(default)]
    pub env: BTreeMap<String, String>,
    /// Select session-declared environment values by name. The client never
    /// sends their resolved values over the protocol.
    #[serde(default)]
    pub env_refs: Vec<String>,
    #[serde(default)]
    pub timeout_secs: Option<u64>,
    #[serde(default)]
    pub max_output_bytes: Option<u64>,
    /// Retain bounded in-memory output for cursor polling while the process is
    /// running. This is rejected when explicit env values or env refs exist.
    #[serde(default)]
    pub stream_output: bool,
}

fn default_cwd() -> String {
    ".".into()
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct OperationStatusRequest {
    pub operation_id: String,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct OperationOutputRequest {
    pub operation_id: String,
    #[serde(default)]
    pub stdout_offset: u64,
    #[serde(default)]
    pub stderr_offset: u64,
    pub max_bytes: u64,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct OperationOutputResponse {
    pub operation_id: String,
    pub state: OperationState,
    pub stdout_offset: u64,
    pub stdout_next_offset: u64,
    pub stdout: String,
    pub stderr_offset: u64,
    pub stderr_next_offset: u64,
    pub stderr: String,
    pub stdout_truncated: bool,
    pub stderr_truncated: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct OperationResponse {
    pub replayed: bool,
    pub operation: OperationRecord,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct CancelOperationResponse {
    /// True when the operation had already reached a terminal state.
    pub already_terminal: bool,
    pub operation: OperationRecord,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct OperationRecord {
    pub operation_id: String,
    pub request_digest: String,
    pub state: OperationState,
    #[serde(default)]
    pub result: Option<ExecResult>,
    #[serde(default)]
    pub message: Option<String>,
}

#[derive(Debug, Clone, Copy, Serialize, Deserialize, PartialEq, Eq)]
#[serde(rename_all = "snake_case")]
pub enum OperationState {
    Running,
    Exited,
    TimedOut,
    Interrupted,
    Cancelled,
    SpawnFailed,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ExecResult {
    /// `None` represents a signal, timeout, or failure before an exit status.
    pub exit_code: Option<i32>,
    #[serde(default)]
    pub signal: Option<i32>,
    pub stdout: String,
    pub stderr: String,
    pub stdout_truncated: bool,
    pub stderr_truncated: bool,
    pub elapsed_ms: u64,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct ProtocolError {
    pub code: ErrorCode,
    pub message: String,
    #[serde(default)]
    pub retryable: bool,
}

impl ProtocolError {
    pub fn new(code: ErrorCode, message: impl Into<String>) -> Self {
        Self {
            code,
            message: message.into(),
            retryable: false,
        }
    }
}

#[derive(Debug, Clone, Copy, Serialize, Deserialize, PartialEq, Eq)]
#[serde(rename_all = "snake_case")]
pub enum ErrorCode {
    InvalidJson,
    ProtocolMismatch,
    HelloRequired,
    InvalidRequest,
    NoSession,
    SessionBusy,
    SessionError,
    InvalidPath,
    SymlinkRejected,
    DeniedByPolicy,
    NotFound,
    TooLarge,
    InvalidUtf8,
    OperationNotFound,
    OperationConflict,
    CasMismatch,
    Io,
    Internal,
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn ndjson_frame_round_trips_with_structured_argv() {
        let frame = RequestFrame {
            request_id: "r-1".into(),
            protocol_version: PROTOCOL_VERSION,
            request: RunnerRequest::ExecStart(ExecRequest {
                operation_id: "op-1".into(),
                program: "cargo".into(),
                argv: vec!["test".into(), "value with spaces".into()],
                cwd: ".".into(),
                env: BTreeMap::new(),
                env_refs: Vec::new(),
                timeout_secs: Some(30),
                max_output_bytes: None,
                stream_output: false,
            }),
        };

        let line = serde_json::to_string(&frame).unwrap();
        assert!(!line.contains('\n'));
        let decoded: RequestFrame = serde_json::from_str(&line).unwrap();
        assert_eq!(decoded, frame);
    }

    #[test]
    fn default_policy_never_exposes_host_rust_toolchain_homes() {
        let policy = HardPolicy::default();
        for name in ["CARGO_HOME", "RUSTUP_HOME"] {
            assert!(!policy.inherited_env.iter().any(|item| item == name));
            assert!(!policy.allowed_env_refs.iter().any(|item| item == name));
        }
    }

    #[test]
    fn default_policy_reserves_runner_private_state_without_hiding_pipeline_evidence() {
        let denied = HardPolicy::default().denied;
        for path in [
            ".klintcode/runtime",
            ".klintcode/runtime/**",
            ".klintcode/cargo-home",
            ".klintcode/cargo-home/**",
        ] {
            assert!(denied.iter().any(|item| item == path));
        }
        for path in [".klintcode", ".klintcode/**"] {
            assert!(!denied.iter().any(|item| item == path));
        }
    }

    #[test]
    fn hard_policy_rejects_unknown_fields() {
        let json_error = serde_json::from_str::<HardPolicy>(r#"{"unknown_limit":1}"#)
            .expect_err("unknown JSON policy fields must fail closed");
        assert!(json_error.to_string().contains("unknown field"));

        let toml_error = toml::from_str::<HardPolicy>("unknown_limit = 1")
            .expect_err("unknown TOML policy fields must fail closed");
        assert!(toml_error.to_string().contains("unknown field"));
    }

    #[test]
    fn client_policy_can_only_narrow_hard_policy() {
        let hard = HardPolicy {
            allowed_programs: vec!["cargo".into(), "git".into()],
            readable: vec!["src/**".into()],
            max_timeout_secs: 300,
            ..HardPolicy::default()
        };
        let effective = EffectivePolicy::from_hard_policy(&hard).narrow(&ClientPolicy {
            allowed_programs: Some(vec!["cargo".into(), "bash".into()]),
            readable: Some(vec!["**".into()]),
            max_timeout_secs: Some(900),
            ..ClientPolicy::default()
        });

        assert_eq!(effective.allowed_programs, vec!["cargo"]);
        assert_eq!(effective.max_timeout_secs, 300);
        assert_eq!(effective.readable_layers.len(), 2);
        assert_eq!(effective.readable_layers[0], vec!["src/**"]);
    }

    #[test]
    fn repeated_policy_handshake_is_idempotent() {
        let hard = HardPolicy {
            readable: vec!["src/**".into()],
            ..HardPolicy::default()
        };
        let client = ClientPolicy {
            readable: Some(vec!["src/**/*.rs".into()]),
            ..ClientPolicy::default()
        };
        let once = EffectivePolicy::from_hard_policy(&hard).narrow(&client);
        let twice = once.narrow_with_hard_policy(&hard).narrow(&client);
        assert_eq!(once, twice);
    }

    #[test]
    fn narrower_policy_comparison_accepts_tightening_and_rejects_widening() {
        let previous = EffectivePolicy::from_hard_policy(&HardPolicy {
            allowed_programs: vec!["cargo".into(), "git".into()],
            allowed_env: vec!["RUST_LOG".into()],
            inherited_env: vec!["PATH".into(), "HOME".into()],
            readable: vec!["**".into()],
            writable: vec!["src/**".into()],
            denied: vec![".git/**".into()],
            max_timeout_secs: 100,
            ..HardPolicy::default()
        });
        let tightened = previous.narrow(&ClientPolicy {
            allowed_programs: Some(vec!["cargo".into()]),
            inherited_env: Some(vec!["PATH".into()]),
            readable: Some(vec!["src/**".into()]),
            writable: Some(vec!["src/**/*.rs".into()]),
            denied: vec!["target/**".into()],
            max_timeout_secs: Some(10),
            ..ClientPolicy::default()
        });
        assert!(tightened.is_narrower_or_equal_to(&previous));
        assert!(tightened.is_narrower_or_equal_to(&tightened));
        assert!(!previous.is_narrower_or_equal_to(&tightened));

        let mut widened_limit = tightened.clone();
        widened_limit.max_output_bytes = previous.max_output_bytes + 1;
        assert!(!widened_limit.is_narrower_or_equal_to(&previous));

        let mut removed_deny = previous.clone();
        removed_deny.denied.clear();
        assert!(!removed_deny.is_narrower_or_equal_to(&previous));
    }
}
