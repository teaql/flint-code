//! Typed, deterministic acceptance contracts for application follow-up work.
//!
//! Natural-language instructions are intentionally not interpreted here. A
//! contract names exact files, Rust API expression chains, and commands whose
//! results must be observed independently by the pipeline.

use serde::{Deserialize, Serialize};
use std::collections::{BTreeMap, BTreeSet};
use std::path::{Component, Path, PathBuf};
use std::sync::Arc;
use syn::parse::Parser;
use syn::visit::{self, Visit};
use tool_runner::remote_protocol::{ErrorCode, FileKind};
use tool_runner::ssh_backend::SshBackendError;

/// Current on-disk schema identifier.
pub const FOLLOWUP_ACCEPTANCE_SCHEMA: &str = "klintcode-followup-acceptance-v1";

/// Snapshot of user-editable workspace files before a follow-up starts.
pub type WorkspaceSnapshot = BTreeMap<PathBuf, Vec<u8>>;

const REMOTE_LIST_LIMIT: u32 = 10_000;
const MAX_REMOTE_SNAPSHOT_FILES: usize = 20_000;
const MAX_REMOTE_UTF8_FILE_BYTES: u64 = 4 * 1024 * 1024;
const REMOTE_COMMAND_OUTPUT_BYTES: u64 = 256 * 1024;

/// Digest-complete application snapshot captured from one remote task session.
///
/// Binary and oversized files retain their digest for integrity checks but are
/// never copied to the local control plane. Only bounded UTF-8 content is
/// available to file-marker and Rust-AST checks.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct RemoteWorkspaceSnapshot {
    workspace: String,
    files: BTreeMap<PathBuf, RemoteSnapshotFile>,
}

#[derive(Debug, Clone, PartialEq, Eq)]
struct RemoteSnapshotFile {
    bytes: u64,
    sha256: String,
    utf8_content: Option<String>,
}

impl RemoteWorkspaceSnapshot {
    /// Normalized runner workspace path represented by this snapshot.
    pub fn workspace(&self) -> &str {
        &self.workspace
    }

    /// Number of application-owned files represented by this snapshot.
    pub fn len(&self) -> usize {
        self.files.len()
    }

    /// Whether the snapshot contains no application-owned files.
    pub fn is_empty(&self) -> bool {
        self.files.is_empty()
    }
}

/// Complete acceptance contract for one follow-up.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(deny_unknown_fields)]
pub struct FollowUpAcceptanceSpec {
    pub schema: String,
    #[serde(default)]
    pub files: Vec<FileRequirement>,
    #[serde(default)]
    pub rust_api: Vec<RustApiRequirement>,
    #[serde(default)]
    pub commands: Vec<CommandRequirement>,
}

/// Required workspace file.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(deny_unknown_fields)]
pub struct FileRequirement {
    pub path: PathBuf,
    #[serde(default)]
    pub must_change: bool,
    #[serde(default = "default_min_bytes")]
    pub min_bytes: u64,
    #[serde(default)]
    pub contains: Vec<String>,
    #[serde(default)]
    pub not_contains: Vec<String>,
}

fn default_min_bytes() -> u64 {
    1
}

/// Root namespace for a TeaQL Rust expression chain.
#[derive(Debug, Clone, Copy, Serialize, Deserialize, PartialEq, Eq)]
pub enum RustApiReceiver {
    Q,
    E,
}

impl RustApiReceiver {
    fn as_str(self) -> &'static str {
        match self {
            Self::Q => "Q",
            Self::E => "E",
        }
    }
}

/// Minimum number of AST-proven TeaQL API chains.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(deny_unknown_fields)]
pub struct RustApiRequirement {
    pub receiver: RustApiReceiver,
    pub terminal: Vec<String>,
    #[serde(default = "default_minimum")]
    pub min: usize,
    /// Exact runtime markers emitted from the same compiled function scopes as
    /// the accepted API chains. Each marker must also be required by a command.
    #[serde(default)]
    pub runtime_markers: Vec<String>,
}

fn default_minimum() -> usize {
    1
}

/// Executable families allowed in acceptance contracts.
#[derive(Debug, Clone, Copy, Serialize, Deserialize, PartialEq, Eq)]
#[serde(rename_all = "lowercase")]
pub enum CommandProgram {
    Cargo,
    Mvn,
    Gradle,
}

impl CommandProgram {
    pub(crate) fn as_str(self) -> &'static str {
        match self {
            Self::Cargo => "cargo",
            Self::Mvn => "mvn",
            Self::Gradle => "gradle",
        }
    }
}

/// Exact command to execute without a shell.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(deny_unknown_fields)]
pub struct CommandRequirement {
    pub program: CommandProgram,
    #[serde(default)]
    pub args: Vec<String>,
    #[serde(default)]
    pub env_ref: Vec<String>,
    /// Number of independent executions required for this command. Repetition
    /// is intentionally part of the typed contract so stress checks do not
    /// need shell loops or scripts.
    #[serde(default = "default_repeat")]
    pub repeat: usize,
    #[serde(default = "default_timeout_secs")]
    pub timeout_secs: u64,
    #[serde(default)]
    pub expect: CommandExpectation,
}

fn default_timeout_secs() -> u64 {
    120
}

fn default_repeat() -> usize {
    1
}

/// Observable command outcome.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
#[serde(deny_unknown_fields)]
pub struct CommandExpectation {
    #[serde(default)]
    pub exit_code: i32,
    #[serde(default)]
    pub min_tests: Option<usize>,
    #[serde(default)]
    pub stdout_contains: Vec<String>,
    #[serde(default)]
    pub stdout_not_contains: Vec<String>,
}

impl Default for CommandExpectation {
    fn default() -> Self {
        Self {
            exit_code: 0,
            min_tests: None,
            stdout_contains: Vec::new(),
            stdout_not_contains: Vec::new(),
        }
    }
}

/// One independently observed acceptance fact.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct FollowUpAcceptanceCheck {
    pub id: String,
    pub kind: String,
    pub passed: bool,
    pub detail: String,
}

/// Full deterministic acceptance report.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq, Eq)]
pub struct FollowUpAcceptanceReport {
    pub schema: String,
    pub passed: bool,
    pub checks: Vec<FollowUpAcceptanceCheck>,
}

impl FollowUpAcceptanceReport {
    /// Compact human-readable report suitable for validation diagnostics.
    pub fn diagnostic(&self) -> String {
        let mut lines = vec![format!(
            "Follow-up acceptance: {} ({} check(s))",
            if self.passed { "passed" } else { "failed" },
            self.checks.len()
        )];
        for check in &self.checks {
            lines.push(format!(
                "{} [{}] {}: {}",
                if check.passed { "✓" } else { "✗" },
                check.kind,
                check.id,
                check.detail
            ));
        }
        lines.join("\n")
    }
}

/// Capture an application-only remote snapshot without reading generated
/// libraries, local environment values, or any local project workspace.
pub async fn snapshot_remote_workspace(
    execution: &crate::execution::RemoteExecution,
    workspace: &str,
) -> Result<RemoteWorkspaceSnapshot, String> {
    let workspace = normalize_remote_workspace(workspace)?;
    let mut pending = vec![(PathBuf::new(), workspace.clone())];
    let mut files = BTreeMap::new();

    while let Some((relative_directory, remote_directory)) = pending.pop() {
        let listing = execution
            .list(remote_directory.clone(), Some(REMOTE_LIST_LIMIT))
            .await
            .map_err(|error| {
                remote_infrastructure_error("failed to list remote acceptance workspace", error)
            })?;
        if listing.path != remote_directory {
            return Err(remote_infrastructure_error(
                "remote directory listing returned a mismatched path",
                "protocol result rejected",
            ));
        }
        if listing.truncated {
            return Err(remote_infrastructure_error(
                "remote acceptance snapshot exceeded the per-directory entry limit",
                "raise the host policy only after reviewing the workspace",
            ));
        }

        for entry in listing.entries {
            if !is_safe_remote_component(&entry.name) {
                return Err(remote_infrastructure_error(
                    "remote acceptance snapshot contained an unsafe path component",
                    "protocol result rejected",
                ));
            }
            let relative = relative_directory.join(&entry.name);
            if remote_snapshot_path_is_excluded(&relative, entry.kind == FileKind::Directory) {
                continue;
            }
            let remote_path = join_remote_workspace_path(&workspace, &relative)?;
            match entry.kind {
                FileKind::Directory => pending.push((relative, remote_path)),
                FileKind::Symlink | FileKind::Other => continue,
                FileKind::File => {
                    if files.len() == MAX_REMOTE_SNAPSHOT_FILES {
                        return Err(remote_infrastructure_error(
                            "remote acceptance snapshot exceeded its total file limit",
                            "workspace is too large for deterministic acceptance",
                        ));
                    }
                    let before =
                        execution
                            .stat(remote_path.clone(), true)
                            .await
                            .map_err(|error| {
                                remote_infrastructure_error(
                                    "failed to hash a remote acceptance file",
                                    error,
                                )
                            })?;
                    let (bytes, sha256) =
                        validated_remote_file_stat(&remote_path, entry.bytes, &before)?;
                    let utf8_content = if bytes <= MAX_REMOTE_UTF8_FILE_BYTES {
                        match execution.read_text(remote_path.clone()).await {
                            Ok(content) => Some(content),
                            Err(error) if is_remote_invalid_utf8(&error) => None,
                            Err(error) => {
                                return Err(remote_infrastructure_error(
                                    "failed to read a remote acceptance file",
                                    error,
                                ));
                            }
                        }
                    } else {
                        None
                    };
                    let after =
                        execution
                            .stat(remote_path.clone(), true)
                            .await
                            .map_err(|error| {
                                remote_infrastructure_error(
                                    "failed to re-hash a remote acceptance file",
                                    error,
                                )
                            })?;
                    if before != after
                        || utf8_content
                            .as_ref()
                            .is_some_and(|content| content.len() as u64 != bytes)
                    {
                        return Err(remote_infrastructure_error(
                            "remote acceptance file changed while its snapshot was captured",
                            "retry after concurrent workspace activity stops",
                        ));
                    }
                    files.insert(
                        relative,
                        RemoteSnapshotFile {
                            bytes,
                            sha256,
                            utf8_content,
                        },
                    );
                }
            }
        }
    }

    Ok(RemoteWorkspaceSnapshot { workspace, files })
}

fn normalize_remote_workspace(workspace: &str) -> Result<String, String> {
    if workspace.is_empty() || workspace.contains('\0') || workspace.contains('\\') {
        return Err(remote_infrastructure_error(
            "invalid remote acceptance workspace",
            "path must be a non-empty runner-relative UTF-8 path",
        ));
    }
    let path = Path::new(workspace);
    if path.is_absolute()
        || path.components().any(|component| {
            matches!(
                component,
                Component::ParentDir | Component::RootDir | Component::Prefix(_)
            )
        })
    {
        return Err(remote_infrastructure_error(
            "invalid remote acceptance workspace",
            "absolute paths and parent traversal are forbidden",
        ));
    }
    let normalized = path
        .components()
        .filter_map(|component| match component {
            Component::Normal(value) => value.to_str(),
            Component::CurDir => None,
            _ => None,
        })
        .collect::<Vec<_>>()
        .join("/");
    if normalized.is_empty() {
        return Ok(".".to_string());
    }
    // The generated application workspace itself is conventionally named
    // `build`; only child build-output directories are excluded below.
    if remote_snapshot_path_is_excluded(Path::new(&normalized), false) {
        return Err(remote_infrastructure_error(
            "invalid remote acceptance workspace",
            "generated, internal, and secret paths are forbidden",
        ));
    }
    Ok(normalized)
}

fn is_safe_remote_component(name: &str) -> bool {
    !name.is_empty()
        && name != "."
        && name != ".."
        && !name.contains('\0')
        && !name.contains('/')
        && !name.contains('\\')
        && !name.chars().any(char::is_control)
        && Path::new(name)
            .components()
            .all(|component| matches!(component, Component::Normal(_)))
}

fn join_remote_workspace_path(workspace: &str, relative: &Path) -> Result<String, String> {
    let relative = relative.to_str().ok_or_else(|| {
        remote_infrastructure_error(
            "remote acceptance path is not UTF-8",
            "protocol result rejected",
        )
    })?;
    let relative = relative.replace(std::path::MAIN_SEPARATOR, "/");
    Ok(if workspace == "." {
        relative
    } else {
        format!("{workspace}/{relative}")
    })
}

fn remote_snapshot_path_is_excluded(path: &Path, is_directory: bool) -> bool {
    let names = path
        .components()
        .filter_map(|component| component.as_os_str().to_str())
        .map(str::to_ascii_lowercase)
        .collect::<Vec<_>>();
    if names.is_empty()
        || names.first().is_some_and(|name| {
            matches!(
                name.as_str(),
                "lib" | "rust-lib-core" | "java-lib-core" | "java-web-spring-boot" | "model"
            )
        })
        || names.iter().any(|name| {
            matches!(
                name.as_str(),
                "target" | ".gradle" | ".git" | ".klintcode" | "secrets"
            )
        })
        || (is_directory && names.last().is_some_and(|name| name == "build"))
        || names
            .windows(2)
            .any(|parts| parts[0] == "lib" && parts[1] == "src")
    {
        return true;
    }
    if is_directory {
        return false;
    }
    let file_name = names.last().map(String::as_str).unwrap_or_default();
    let extension = Path::new(file_name)
        .extension()
        .and_then(|value| value.to_str());
    file_name == "cargo.lock"
        || file_name == ".env"
        || file_name.starts_with(".env.")
        || matches!(
            extension,
            Some("db" | "sqlite" | "sqlite3" | "log" | "key" | "pem")
        )
}

fn validated_remote_file_stat(
    requested_path: &str,
    listed_bytes: u64,
    stat: &tool_runner::remote_protocol::StatResponse,
) -> Result<(u64, String), String> {
    let bytes = stat.bytes;
    let sha256 = stat.sha256.as_deref();
    if stat.path != requested_path
        || !stat.exists
        || stat.kind != Some(FileKind::File)
        || bytes != Some(listed_bytes)
        || !sha256.is_some_and(is_sha256)
    {
        return Err(remote_infrastructure_error(
            "remote acceptance file metadata was inconsistent",
            "workspace changed or the protocol result was invalid",
        ));
    }
    Ok((
        bytes.unwrap_or_default(),
        sha256.unwrap_or_default().to_owned(),
    ))
}

fn is_sha256(value: &str) -> bool {
    value.len() == 64
        && value
            .bytes()
            .all(|byte| byte.is_ascii_digit() || (b'a'..=b'f').contains(&byte))
}

fn is_remote_invalid_utf8(error: &crate::execution::RemoteExecutionError) -> bool {
    matches!(
        error,
        crate::execution::RemoteExecutionError::Backend(SshBackendError::RemoteRejected {
            code: ErrorCode::InvalidUtf8,
            ..
        })
    )
}

fn remote_infrastructure_error(stage: &str, error: impl std::fmt::Display) -> String {
    format!("[infrastructure] {stage}: {error}")
}

impl FollowUpAcceptanceSpec {
    /// Load and validate a JSON contract.
    pub fn load(path: &Path) -> Result<Self, String> {
        let content = std::fs::read_to_string(path)
            .map_err(|error| format!("Failed to read {}: {error}", path.display()))?;
        Self::parse_json(&content)
            .map_err(|error| format!("Invalid contract {}: {error}", path.display()))
    }

    /// Parse and validate a JSON contract.
    pub fn parse_json(content: &str) -> Result<Self, String> {
        let spec: Self = serde_json::from_str(content)
            .map_err(|error| format!("Invalid follow-up acceptance JSON: {error}"))?;
        spec.validate()?;
        Ok(spec)
    }

    /// Validate schema and all path/command invariants without touching a workspace.
    pub fn validate(&self) -> Result<(), String> {
        if self.schema != FOLLOWUP_ACCEPTANCE_SCHEMA {
            return Err(format!(
                "Unsupported schema `{}`; expected `{FOLLOWUP_ACCEPTANCE_SCHEMA}`",
                self.schema
            ));
        }
        if self.files.is_empty() && self.rust_api.is_empty() && self.commands.is_empty() {
            return Err("Acceptance contract must contain at least one check".to_string());
        }

        for requirement in &self.files {
            validate_relative_path(&requirement.path)?;
            if requirement.min_bytes == 0 {
                return Err(format!(
                    "File `{}` must set min_bytes to at least 1",
                    requirement.path.display()
                ));
            }
            if requirement
                .contains
                .iter()
                .chain(&requirement.not_contains)
                .any(|marker| marker.is_empty())
            {
                return Err(format!(
                    "File `{}` content markers cannot be empty",
                    requirement.path.display()
                ));
            }
        }
        let mut all_runtime_markers = BTreeSet::new();
        let command_markers = self
            .commands
            .iter()
            .flat_map(|command| command.expect.stdout_contains.iter())
            .collect::<BTreeSet<_>>();
        for requirement in &self.rust_api {
            if requirement.min == 0 {
                return Err("Rust API requirement min must be at least 1".to_string());
            }
            if requirement.terminal.is_empty() {
                return Err(format!(
                    "Rust API receiver {} must name at least one terminal method",
                    requirement.receiver.as_str()
                ));
            }
            for terminal in &requirement.terminal {
                if !is_rust_identifier(terminal) {
                    return Err(format!("Invalid Rust terminal method `{terminal}`"));
                }
            }
            if !requirement.runtime_markers.is_empty()
                && requirement.runtime_markers.len() != requirement.min
            {
                return Err(format!(
                    "Rust API receiver {} must provide exactly {} runtime marker(s), one per required chain",
                    requirement.receiver.as_str(),
                    requirement.min
                ));
            }
            for marker in &requirement.runtime_markers {
                if marker.is_empty() {
                    return Err("Rust API runtime markers cannot be empty".to_string());
                }
                if !all_runtime_markers.insert(marker) {
                    return Err(format!("Rust API runtime marker `{marker}` is duplicated"));
                }
                if !command_markers.contains(marker) {
                    return Err(format!(
                        "Rust API runtime marker `{marker}` must also appear in a command stdout_contains expectation"
                    ));
                }
            }
            if !requirement.runtime_markers.is_empty()
                && !self.commands.iter().any(|command| {
                    command.program == CommandProgram::Cargo
                        && command
                            .args
                            .first()
                            .is_some_and(|argument| argument == "run")
                        && requirement.runtime_markers.iter().all(|marker| {
                            command
                                .expect
                                .stdout_contains
                                .iter()
                                .any(|item| item == marker)
                        })
                })
            {
                return Err(format!(
                    "Rust API receiver {} runtime markers must all be required by the same cargo run command",
                    requirement.receiver.as_str()
                ));
            }
        }
        for requirement in &self.commands {
            if requirement.repeat == 0 || requirement.repeat > 20 {
                return Err("Command repeat must be between 1 and 20".to_string());
            }
            if requirement.timeout_secs == 0 || requirement.timeout_secs > 3_600 {
                return Err("Command timeout_secs must be between 1 and 3600".to_string());
            }
            if requirement
                .args
                .iter()
                .any(|argument| argument.contains('\0'))
            {
                return Err("Command arguments cannot contain NUL bytes".to_string());
            }
            crate::tools::validate_remote_command(requirement.program.as_str(), &requirement.args)
                .map_err(|error| format!("Unsafe acceptance command: {error}"))?;
            for name in &requirement.env_ref {
                if !is_environment_name(name) {
                    return Err(format!("Invalid environment variable name `{name}`"));
                }
                if is_sensitive_environment_name(name) {
                    return Err(format!(
                        "Acceptance commands cannot receive secret-like environment variable `{name}`; keep model/API credentials in the backend profile process only"
                    ));
                }
            }
            if requirement.program == CommandProgram::Cargo
                && requirement.args.first().is_some_and(|arg| arg == "teaql")
                && !requirement.args.iter().any(|arg| arg == "--input")
            {
                return Err("Every cargo teaql acceptance command must include --input".to_string());
            }
            if requirement
                .expect
                .stdout_contains
                .iter()
                .chain(&requirement.expect.stdout_not_contains)
                .any(|marker| marker.is_empty())
            {
                return Err("Command output markers cannot be empty".to_string());
            }
        }
        Ok(())
    }

    /// Render a secret-free checklist for the coding agent's prompt.
    pub fn render_checklist(&self) -> String {
        let mut lines = vec![format!("Schema: `{}`", self.schema)];
        for requirement in &self.files {
            lines.push(format!(
                "- file `{}`: at least {} bytes{}",
                requirement.path.display(),
                requirement.min_bytes,
                if requirement.must_change {
                    ", changed by this follow-up"
                } else {
                    ""
                }
            ));
            if !requirement.contains.is_empty() {
                lines.push(format!(
                    "  required text: [{}]",
                    requirement.contains.join(", ")
                ));
            }
            if !requirement.not_contains.is_empty() {
                lines.push(format!(
                    "  forbidden text: [{}]",
                    requirement.not_contains.join(", ")
                ));
            }
        }
        for requirement in &self.rust_api {
            lines.push(format!(
                "- Rust AST: at least {} {} chain(s) ending in one of [{}]",
                requirement.min,
                requirement.receiver.as_str(),
                requirement.terminal.join(", ")
            ));
            if !requirement.runtime_markers.is_empty() {
                lines.push(format!(
                    "  runtime-bound markers (one unique marker per chain scope): [{}]",
                    requirement.runtime_markers.join(", ")
                ));
            }
        }
        for requirement in &self.commands {
            let env = if requirement.env_ref.is_empty() {
                String::new()
            } else {
                format!(
                    "; runner-injected environment references [{}] (invoke the command exactly as shown; do not prefix shell assignments)",
                    requirement.env_ref.join(", ")
                )
            };
            lines.push(format!(
                "- command (no shell): `{} {}`; repeat {} time(s); timeout {}s per execution; expect exit {}{}",
                requirement.program.as_str(),
                requirement.args.join(" "),
                requirement.repeat,
                requirement.timeout_secs,
                requirement.expect.exit_code,
                env
            ));
            if let Some(min_tests) = requirement.expect.min_tests {
                lines.push(format!("  minimum observed tests: {min_tests}"));
            }
            if !requirement.expect.stdout_contains.is_empty() {
                lines.push(format!(
                    "  required output: [{}]",
                    requirement.expect.stdout_contains.join(", ")
                ));
            }
            if !requirement.expect.stdout_not_contains.is_empty() {
                lines.push(format!(
                    "  forbidden output: [{}]",
                    requirement.expect.stdout_not_contains.join(", ")
                ));
            }
        }
        lines.join("\n")
    }

    /// Verify every contract item against the final workspace and independent
    /// command results. Acceptance failures are returned as a report; only an
    /// invalid contract is returned as `Err`.
    pub async fn verify(
        &self,
        workspace: &Path,
        before: &WorkspaceSnapshot,
    ) -> Result<FollowUpAcceptanceReport, String> {
        let sqlite_isolation = crate::process_env::SqliteDatabaseIsolation::new()
            .map_err(|error| format!("Failed to create isolated SQLite runtime: {error}"))?;
        let mut all = BTreeMap::new();
        let mut cargo_test_names = BTreeSet::new();
        let mut cargo_run_names = BTreeSet::new();
        for requirement in &self.commands {
            for name in &requirement.env_ref {
                if let Ok(value) = std::env::var(name) {
                    all.entry(name.clone())
                        .or_insert_with(|| sqlite_isolation.isolate_value(name, &value));
                }
            }
            if requirement.program == CommandProgram::Cargo {
                match requirement.args.first().map(String::as_str) {
                    Some("test") => cargo_test_names.extend(requirement.env_ref.iter().cloned()),
                    Some("run") => cargo_run_names.extend(requirement.env_ref.iter().cloned()),
                    _ => {}
                }
            }
        }
        let select = |names: BTreeSet<String>| {
            names
                .into_iter()
                .filter_map(|name| all.get(&name).cloned().map(|value| (name, value)))
                .collect::<BTreeMap<_, _>>()
        };
        let cargo_test = select(cargo_test_names);
        let cargo_run = select(cargo_run_names);
        let environment =
            crate::tools::DeclaredCommandEnvironment::new_with_all(all, cargo_test, cargo_run);
        self.verify_with_environment(workspace, before, &environment)
            .await
    }

    /// Verify with runtime inputs already resolved and isolated by the owning
    /// pipeline executor. This deliberately never reads the parent process
    /// environment, preventing acceptance commands from bypassing isolation.
    pub(crate) async fn verify_with_environment(
        &self,
        workspace: &Path,
        before: &WorkspaceSnapshot,
        command_environment: &crate::tools::DeclaredCommandEnvironment,
    ) -> Result<FollowUpAcceptanceReport, String> {
        self.validate()?;
        let mut checks = Vec::new();

        for (index, requirement) in self.files.iter().enumerate() {
            checks.push(check_file(workspace, before, index, requirement));
        }

        let rust_observation = collect_rust_api_chains(workspace);
        for (index, requirement) in self.rust_api.iter().enumerate() {
            checks.push(match &rust_observation {
                Ok(observation) => {
                    let matching_chains = observation
                        .chains
                        .iter()
                        .filter(|chain| {
                            chain.receiver == requirement.receiver
                                && requirement
                                    .terminal
                                    .iter()
                                    .any(|terminal| terminal == &chain.terminal)
                        })
                        .collect::<Vec<_>>();
                    let count = matching_chains.len();
                    let marker_failures = runtime_marker_binding_failures(
                        observation,
                        &matching_chains,
                        &requirement.runtime_markers,
                    );
                    let marker_detail = if requirement.runtime_markers.is_empty() {
                        String::new()
                    } else if marker_failures.is_empty() {
                        format!(
                            "; all {} runtime marker(s) uniquely bound to matching compiled scopes",
                            requirement.runtime_markers.len()
                        )
                    } else {
                        format!("; runtime binding failed: {}", marker_failures.join("; "))
                    };
                    FollowUpAcceptanceCheck {
                        id: format!("rust-api-{index}"),
                        kind: "rust-api".to_string(),
                        passed: count >= requirement.min && marker_failures.is_empty(),
                        detail: format!(
                            "observed {count}, required {} {} chain(s) ending in [{}]{}",
                            requirement.min,
                            requirement.receiver.as_str(),
                            requirement.terminal.join(", "),
                            marker_detail
                        ),
                    }
                }
                Err(error) => FollowUpAcceptanceCheck {
                    id: format!("rust-api-{index}"),
                    kind: "rust-api".to_string(),
                    passed: false,
                    detail: error.clone(),
                },
            });
        }

        let before_commands = crate::executor::application_workspace_snapshot(workspace);
        let runtime_markers = self
            .rust_api
            .iter()
            .flat_map(|requirement| requirement.runtime_markers.iter())
            .map(String::as_str)
            .collect::<BTreeSet<_>>();
        for (index, requirement) in self.commands.iter().enumerate() {
            checks.push(
                run_command_check(
                    workspace,
                    index,
                    requirement,
                    &runtime_markers,
                    command_environment,
                )
                .await,
            );
        }
        let after_commands = crate::executor::application_workspace_snapshot(workspace);
        checks.push(FollowUpAcceptanceCheck {
            id: "command-workspace-integrity".to_string(),
            kind: "integrity".to_string(),
            passed: before_commands == after_commands,
            detail: if before_commands == after_commands {
                "acceptance commands did not change application-owned workspace files".to_string()
            } else {
                "acceptance commands changed application-owned workspace files; command-side mutations are not accepted"
                    .to_string()
            },
        });

        Ok(FollowUpAcceptanceReport {
            schema: FOLLOWUP_ACCEPTANCE_SCHEMA.to_string(),
            passed: checks.iter().all(|check| check.passed),
            checks,
        })
    }

    /// Verify the same typed contract against the authoritative SSH workspace.
    ///
    /// File and AST checks use a digest-stable, application-only snapshot.
    /// Commands execute through the structured runner protocol with named
    /// session environment references; this path never reads parent process
    /// environment values and never runs a command in a local project.
    pub async fn verify_remote_with_environment(
        &self,
        execution: &Arc<crate::execution::RemoteExecution>,
        workspace: &str,
        before: &RemoteWorkspaceSnapshot,
        env_ref_names: &BTreeSet<String>,
    ) -> Result<FollowUpAcceptanceReport, String> {
        self.validate()?;
        let workspace = normalize_remote_workspace(workspace)?;
        if before.workspace != workspace {
            return Err(remote_infrastructure_error(
                "remote acceptance baseline belongs to a different workspace",
                "snapshot identity mismatch",
            ));
        }

        let current = snapshot_remote_workspace(execution.as_ref(), &workspace).await?;
        let mut checks = self
            .files
            .iter()
            .enumerate()
            .map(|(index, requirement)| check_remote_file(&current, before, index, requirement))
            .collect::<Vec<_>>();

        let materialized = materialize_remote_snapshot(&current)?;
        let rust_observation = collect_rust_api_chains(materialized.path());
        for (index, requirement) in self.rust_api.iter().enumerate() {
            checks.push(match &rust_observation {
                Ok(observation) => {
                    let matching_chains = observation
                        .chains
                        .iter()
                        .filter(|chain| {
                            chain.receiver == requirement.receiver
                                && requirement
                                    .terminal
                                    .iter()
                                    .any(|terminal| terminal == &chain.terminal)
                        })
                        .collect::<Vec<_>>();
                    let count = matching_chains.len();
                    let marker_failures = runtime_marker_binding_failures(
                        observation,
                        &matching_chains,
                        &requirement.runtime_markers,
                    );
                    let marker_detail = if requirement.runtime_markers.is_empty() {
                        String::new()
                    } else if marker_failures.is_empty() {
                        format!(
                            "; all {} runtime marker(s) uniquely bound to matching compiled scopes",
                            requirement.runtime_markers.len()
                        )
                    } else {
                        format!("; runtime binding failed: {}", marker_failures.join("; "))
                    };
                    FollowUpAcceptanceCheck {
                        id: format!("rust-api-{index}"),
                        kind: "rust-api".to_string(),
                        passed: count >= requirement.min && marker_failures.is_empty(),
                        detail: format!(
                            "observed {count}, required {} {} chain(s) ending in [{}]{}",
                            requirement.min,
                            requirement.receiver.as_str(),
                            requirement.terminal.join(", "),
                            marker_detail
                        ),
                    }
                }
                Err(error) => FollowUpAcceptanceCheck {
                    id: format!("rust-api-{index}"),
                    kind: "rust-api".to_string(),
                    passed: false,
                    detail: error.clone(),
                },
            });
        }

        let before_commands = snapshot_remote_workspace(execution.as_ref(), &workspace).await?;
        let runtime_markers = self
            .rust_api
            .iter()
            .flat_map(|requirement| requirement.runtime_markers.iter())
            .map(String::as_str)
            .collect::<BTreeSet<_>>();
        for (index, requirement) in self.commands.iter().enumerate() {
            checks.push(
                run_remote_command_check(
                    execution.as_ref(),
                    &workspace,
                    index,
                    requirement,
                    &runtime_markers,
                    env_ref_names,
                )
                .await?,
            );
        }
        let after_commands = snapshot_remote_workspace(execution.as_ref(), &workspace).await?;
        checks.push(FollowUpAcceptanceCheck {
            id: "command-workspace-integrity".to_string(),
            kind: "integrity".to_string(),
            passed: before_commands == after_commands,
            detail: if before_commands == after_commands {
                "acceptance commands did not change application-owned remote workspace files"
                    .to_string()
            } else {
                "acceptance commands changed application-owned remote workspace files; command-side mutations are not accepted"
                    .to_string()
            },
        });

        Ok(FollowUpAcceptanceReport {
            schema: FOLLOWUP_ACCEPTANCE_SCHEMA.to_string(),
            passed: checks.iter().all(|check| check.passed),
            checks,
        })
    }
}

fn validate_relative_path(path: &Path) -> Result<(), String> {
    if path.as_os_str().is_empty() || path.is_absolute() {
        return Err(format!(
            "Acceptance file path must be non-empty and relative: {}",
            path.display()
        ));
    }
    let components = path.components().collect::<Vec<_>>();
    if components
        .iter()
        .any(|component| !matches!(component, Component::Normal(_)))
    {
        return Err(format!(
            "Acceptance file path cannot contain `.` or `..`: {}",
            path.display()
        ));
    }
    let names = components
        .iter()
        .filter_map(|component| component.as_os_str().to_str())
        .map(|component| component.to_ascii_lowercase())
        .collect::<Vec<_>>();
    if names.first().is_some_and(|name| {
        matches!(
            name.as_str(),
            "lib"
                | "rust-lib-core"
                | "java-lib-core"
                | "java-web-spring-boot"
                | "target"
                | ".git"
                | ".klintcode"
                | "model"
        )
    }) || names
        .windows(2)
        .any(|parts| parts[0] == "lib" && parts[1] == "src")
    {
        return Err(format!(
            "Acceptance file path targets generated or internal content: {}",
            path.display()
        ));
    }
    Ok(())
}

fn is_rust_identifier(value: &str) -> bool {
    let mut chars = value.chars();
    chars
        .next()
        .is_some_and(|character| character == '_' || character.is_ascii_alphabetic())
        && chars.all(|character| character == '_' || character.is_ascii_alphanumeric())
}

fn is_environment_name(value: &str) -> bool {
    let mut chars = value.chars();
    chars
        .next()
        .is_some_and(|character| character == '_' || character.is_ascii_alphabetic())
        && chars.all(|character| character == '_' || character.is_ascii_alphanumeric())
}

fn is_sensitive_environment_name(value: &str) -> bool {
    let normalized = value.to_ascii_uppercase();
    [
        "API_KEY",
        "ACCESS_KEY",
        "PRIVATE_KEY",
        "PASSWORD",
        "PASSWD",
        "TOKEN",
        "SECRET",
        "CREDENTIAL",
        "AUTHORIZATION",
    ]
    .iter()
    .any(|marker| normalized.contains(marker))
}

fn check_file(
    workspace: &Path,
    before: &WorkspaceSnapshot,
    index: usize,
    requirement: &FileRequirement,
) -> FollowUpAcceptanceCheck {
    let id = format!("file-{index}:{}", requirement.path.display());
    let failure = |detail: String| FollowUpAcceptanceCheck {
        id: id.clone(),
        kind: "file".to_string(),
        passed: false,
        detail,
    };

    if let Err(error) = reject_symlink_components(workspace, &requirement.path) {
        return failure(error);
    }
    let path = workspace.join(&requirement.path);
    if let Ok(canonical) = path.canonicalize()
        && is_protected_acceptance_target(workspace, &canonical)
    {
        return failure(format!(
            "acceptance path resolves to generated or internal content: {}",
            requirement.path.display()
        ));
    }
    let content = match std::fs::read(&path) {
        Ok(content) => content,
        Err(error) => return failure(format!("cannot read {}: {error}", path.display())),
    };
    if content.len() < requirement.min_bytes as usize {
        return failure(format!(
            "{} has {} bytes; requires at least {}",
            requirement.path.display(),
            content.len(),
            requirement.min_bytes
        ));
    }
    if !content.iter().any(|byte| !byte.is_ascii_whitespace()) {
        return failure(format!(
            "{} contains only whitespace",
            requirement.path.display()
        ));
    }
    let text = String::from_utf8_lossy(&content);
    for marker in &requirement.contains {
        if !text.contains(marker) {
            return failure(format!(
                "{} is missing required text marker `{marker}`",
                requirement.path.display()
            ));
        }
    }
    for marker in &requirement.not_contains {
        if text.contains(marker) {
            return failure(format!(
                "{} contains forbidden text marker `{marker}`",
                requirement.path.display()
            ));
        }
    }
    if requirement.must_change
        && before
            .get(&requirement.path)
            .is_some_and(|old_content| old_content == &content)
    {
        return failure(format!(
            "{} was not changed by this follow-up",
            requirement.path.display()
        ));
    }

    FollowUpAcceptanceCheck {
        id,
        kind: "file".to_string(),
        passed: true,
        detail: format!(
            "{} exists with {} bytes{}",
            requirement.path.display(),
            content.len(),
            if requirement.must_change {
                " and changed"
            } else {
                ""
            }
        ),
    }
}

fn check_remote_file(
    current: &RemoteWorkspaceSnapshot,
    before: &RemoteWorkspaceSnapshot,
    index: usize,
    requirement: &FileRequirement,
) -> FollowUpAcceptanceCheck {
    let id = format!("file-{index}:{}", requirement.path.display());
    let failure = |detail: String| FollowUpAcceptanceCheck {
        id: id.clone(),
        kind: "file".to_string(),
        passed: false,
        detail,
    };
    let Some(file) = current.files.get(&requirement.path) else {
        return failure(format!(
            "cannot read {} from the application-only remote snapshot",
            requirement.path.display()
        ));
    };
    if file.bytes < requirement.min_bytes {
        return failure(format!(
            "{} has {} bytes; requires at least {}",
            requirement.path.display(),
            file.bytes,
            requirement.min_bytes
        ));
    }
    let Some(text) = &file.utf8_content else {
        return failure(format!(
            "{} is not a bounded UTF-8 application file",
            requirement.path.display()
        ));
    };
    if !text.bytes().any(|byte| !byte.is_ascii_whitespace()) {
        return failure(format!(
            "{} contains only whitespace",
            requirement.path.display()
        ));
    }
    for marker in &requirement.contains {
        if !text.contains(marker) {
            return failure(format!(
                "{} is missing required text marker `{marker}`",
                requirement.path.display()
            ));
        }
    }
    for marker in &requirement.not_contains {
        if text.contains(marker) {
            return failure(format!(
                "{} contains forbidden text marker `{marker}`",
                requirement.path.display()
            ));
        }
    }
    if requirement.must_change
        && before
            .files
            .get(&requirement.path)
            .is_some_and(|old| old.bytes == file.bytes && old.sha256 == file.sha256)
    {
        return failure(format!(
            "{} was not changed by this follow-up",
            requirement.path.display()
        ));
    }

    FollowUpAcceptanceCheck {
        id,
        kind: "file".to_string(),
        passed: true,
        detail: format!(
            "{} exists remotely with {} bytes{}",
            requirement.path.display(),
            file.bytes,
            if requirement.must_change {
                " and changed"
            } else {
                ""
            }
        ),
    }
}

fn materialize_remote_snapshot(
    snapshot: &RemoteWorkspaceSnapshot,
) -> Result<tempfile::TempDir, String> {
    let directory = tempfile::Builder::new()
        .prefix("klintcode-remote-acceptance-")
        .tempdir()
        .map_err(|error| {
            remote_infrastructure_error(
                "failed to create remote acceptance control-plane directory",
                error,
            )
        })?;
    for (relative, file) in &snapshot.files {
        let Some(content) = &file.utf8_content else {
            continue;
        };
        validate_relative_path(relative).map_err(|error| {
            remote_infrastructure_error(
                "remote acceptance snapshot contained a protected path",
                error,
            )
        })?;
        if remote_snapshot_path_is_excluded(relative, false) {
            return Err(remote_infrastructure_error(
                "remote acceptance snapshot contained excluded content",
                "snapshot rejected before materialization",
            ));
        }
        let destination = directory.path().join(relative);
        if let Some(parent) = destination.parent() {
            std::fs::create_dir_all(parent).map_err(|error| {
                remote_infrastructure_error(
                    "failed to create remote acceptance control-plane directory",
                    error,
                )
            })?;
        }
        std::fs::write(&destination, content).map_err(|error| {
            remote_infrastructure_error(
                "failed to materialize a remote acceptance UTF-8 file",
                error,
            )
        })?;
    }
    Ok(directory)
}

fn is_protected_acceptance_target(workspace: &Path, path: &Path) -> bool {
    let Ok(root) = workspace.canonicalize() else {
        return true;
    };
    let Ok(relative) = path.strip_prefix(root) else {
        return true;
    };
    let names = relative
        .components()
        .filter_map(|component| component.as_os_str().to_str())
        .map(|component| component.to_ascii_lowercase())
        .collect::<Vec<_>>();
    names.first().is_some_and(|name| {
        matches!(
            name.as_str(),
            "lib"
                | "rust-lib-core"
                | "java-lib-core"
                | "java-web-spring-boot"
                | "target"
                | ".git"
                | ".klintcode"
                | "model"
        )
    }) || names
        .windows(2)
        .any(|parts| parts[0] == "lib" && parts[1] == "src")
}

fn reject_symlink_components(workspace: &Path, relative: &Path) -> Result<(), String> {
    let mut current = workspace.to_path_buf();
    for component in relative.components() {
        current.push(component.as_os_str());
        match std::fs::symlink_metadata(&current) {
            Ok(metadata) if metadata.file_type().is_symlink() => {
                return Err(format!(
                    "Acceptance path traverses symlink: {}",
                    current.display()
                ));
            }
            Ok(_) => {}
            Err(error) if error.kind() == std::io::ErrorKind::NotFound => break,
            Err(error) => {
                return Err(format!("Cannot inspect {}: {error}", current.display()));
            }
        }
    }
    Ok(())
}

fn collect_rust_api_chains(workspace: &Path) -> Result<RustApiObservation, String> {
    let sources = collect_compiled_rust_sources(workspace)?;
    let mut observation = RustApiObservation::default();
    for path in sources {
        let content = std::fs::read_to_string(&path)
            .map_err(|error| format!("Failed to read Rust source {}: {error}", path.display()))?;
        let syntax = syn::parse_file(&content)
            .map_err(|error| format!("Failed to parse Rust source {}: {error}", path.display()))?;
        let label = path
            .strip_prefix(workspace)
            .unwrap_or(&path)
            .display()
            .to_string();
        let mut visitor = ApiChainVisitor::new(label);
        visitor.visit_file(&syntax);
        observation.chains.extend(visitor.observation.chains);
        for (scope, markers) in visitor.observation.emitted_markers {
            observation
                .emitted_markers
                .entry(scope)
                .or_default()
                .extend(markers);
        }
    }

    Ok(observation)
}

fn collect_compiled_rust_sources(workspace: &Path) -> Result<Vec<PathBuf>, String> {
    let source_root = workspace.join("src");
    let mut roots = BTreeSet::new();
    let manifest_path = workspace.join("Cargo.toml");
    let mut autobins = true;
    let mut explicit_lib = false;
    if manifest_path.is_file() {
        let manifest = std::fs::read_to_string(&manifest_path)
            .map_err(|error| format!("Failed to read {}: {error}", manifest_path.display()))?;
        let manifest: toml::Value = toml::from_str(&manifest)
            .map_err(|error| format!("Failed to parse {}: {error}", manifest_path.display()))?;
        autobins = manifest
            .get("package")
            .and_then(|package| package.get("autobins"))
            .and_then(toml::Value::as_bool)
            .unwrap_or(true);
        if let Some(path) = manifest
            .get("lib")
            .and_then(|lib| lib.get("path"))
            .and_then(toml::Value::as_str)
        {
            explicit_lib = true;
            roots.insert(application_source_path(workspace, Path::new(path))?);
        }
        if let Some(bins) = manifest.get("bin").and_then(toml::Value::as_array) {
            for bin in bins {
                if let Some(path) = bin.get("path").and_then(toml::Value::as_str) {
                    roots.insert(application_source_path(workspace, Path::new(path))?);
                }
            }
        }
    }

    let default_lib = source_root.join("lib.rs");
    if !explicit_lib && default_lib.is_file() {
        roots.insert(default_lib);
    }
    let default_main = source_root.join("main.rs");
    if autobins && default_main.is_file() {
        roots.insert(default_main);
    }
    if autobins {
        collect_auto_bin_roots(&source_root.join("bin"), &mut roots)?;
    }
    if roots.is_empty() {
        return Err(format!(
            "No Rust crate roots were found from {} or Cargo.toml target paths",
            source_root.display(),
        ));
    }

    let mut visited = BTreeSet::new();
    let mut sources = Vec::new();
    for root in roots {
        collect_module_sources(workspace, &root, &mut visited, &mut sources)?;
    }
    sources.sort();
    Ok(sources)
}

fn collect_auto_bin_roots(root: &Path, roots: &mut BTreeSet<PathBuf>) -> Result<(), String> {
    if !root.is_dir() {
        return Ok(());
    }
    let metadata = std::fs::symlink_metadata(root)
        .map_err(|error| format!("Cannot inspect {}: {error}", root.display()))?;
    if metadata.file_type().is_symlink() {
        return Err(format!("Rust source path is a symlink: {}", root.display()));
    }
    for entry in std::fs::read_dir(root)
        .map_err(|error| format!("Cannot read {}: {error}", root.display()))?
    {
        let entry = entry.map_err(|error| error.to_string())?;
        let file_type = entry.file_type().map_err(|error| error.to_string())?;
        if file_type.is_symlink() {
            continue;
        }
        let path = entry.path();
        if file_type.is_file()
            && path.extension().and_then(|extension| extension.to_str()) == Some("rs")
        {
            roots.insert(path);
        } else if file_type.is_dir() {
            let main = path.join("main.rs");
            if main.is_file() {
                roots.insert(main);
            }
        }
    }
    Ok(())
}

fn application_source_path(workspace: &Path, relative: &Path) -> Result<PathBuf, String> {
    validate_relative_path(relative)?;
    let path = workspace.join(relative);
    reject_symlink_components(workspace, relative)?;
    let canonical = path
        .canonicalize()
        .map_err(|error| format!("Cannot resolve Rust crate root {}: {error}", path.display()))?;
    if is_protected_acceptance_target(workspace, &canonical) {
        return Err(format!(
            "Rust crate root targets generated or internal content: {}",
            relative.display()
        ));
    }
    Ok(path)
}

fn collect_module_sources(
    workspace: &Path,
    path: &Path,
    visited: &mut BTreeSet<PathBuf>,
    sources: &mut Vec<PathBuf>,
) -> Result<(), String> {
    let relative = path.strip_prefix(workspace).map_err(|_| {
        format!(
            "Rust source is outside the application workspace: {}",
            path.display()
        )
    })?;
    reject_symlink_components(workspace, relative)?;
    let canonical = path
        .canonicalize()
        .map_err(|error| format!("Cannot resolve Rust source {}: {error}", path.display()))?;
    if is_protected_acceptance_target(workspace, &canonical) {
        return Err(format!(
            "Rust source targets generated or internal content: {}",
            path.display()
        ));
    }
    if !visited.insert(canonical) {
        return Ok(());
    }

    let content = std::fs::read_to_string(path)
        .map_err(|error| format!("Failed to read Rust source {}: {error}", path.display()))?;
    let syntax = syn::parse_file(&content)
        .map_err(|error| format!("Failed to parse Rust source {}: {error}", path.display()))?;
    sources.push(path.to_path_buf());

    let file_name = path.file_name().and_then(|name| name.to_str());
    let module_base = if matches!(file_name, Some("lib.rs" | "main.rs" | "mod.rs")) {
        path.parent().unwrap_or(workspace).to_path_buf()
    } else {
        path.parent()
            .unwrap_or(workspace)
            .join(path.file_stem().unwrap_or_default())
    };
    collect_declared_modules(
        workspace,
        path.parent().unwrap_or(workspace),
        &module_base,
        &syntax.items,
        visited,
        sources,
    )
}

fn collect_declared_modules(
    workspace: &Path,
    containing_dir: &Path,
    module_base: &Path,
    items: &[syn::Item],
    visited: &mut BTreeSet<PathBuf>,
    sources: &mut Vec<PathBuf>,
) -> Result<(), String> {
    for item in items {
        let syn::Item::Mod(module) = item else {
            continue;
        };
        if has_statically_disabled_cfg(&module.attrs) {
            continue;
        }
        if let Some((_, inline_items)) = &module.content {
            collect_declared_modules(
                workspace,
                containing_dir,
                &module_base.join(module.ident.to_string()),
                inline_items,
                visited,
                sources,
            )?;
            continue;
        }

        let explicit_path = module.attrs.iter().find_map(|attribute| {
            if !attribute.path().is_ident("path") {
                return None;
            }
            match &attribute.meta {
                syn::Meta::NameValue(value) => match &value.value {
                    syn::Expr::Lit(syn::ExprLit {
                        lit: syn::Lit::Str(literal),
                        ..
                    }) => Some(containing_dir.join(literal.value())),
                    _ => None,
                },
                _ => None,
            }
        });
        let module_path = if let Some(path) = explicit_path {
            path
        } else {
            let flat = module_base.join(format!("{}.rs", module.ident));
            let nested = module_base.join(module.ident.to_string()).join("mod.rs");
            match (flat.is_file(), nested.is_file()) {
                (true, false) => flat,
                (false, true) => nested,
                (true, true) => {
                    return Err(format!(
                        "Rust module `{}` has both {} and {}",
                        module.ident,
                        flat.display(),
                        nested.display()
                    ));
                }
                (false, false) => {
                    return Err(format!(
                        "Rust module `{}` declared in {} has no source file",
                        module.ident,
                        containing_dir.display()
                    ));
                }
            }
        };
        collect_module_sources(workspace, &module_path, visited, sources)?;
    }
    Ok(())
}

fn has_statically_disabled_cfg(attributes: &[syn::Attribute]) -> bool {
    attributes.iter().any(|attribute| {
        let syn::Meta::List(list) = &attribute.meta else {
            return false;
        };
        attribute.path().is_ident("cfg")
            && syn::parse2::<syn::Meta>(list.tokens.clone())
                .ok()
                .and_then(|meta| evaluate_cfg_meta(&meta))
                == Some(false)
    })
}

fn evaluate_cfg_meta(meta: &syn::Meta) -> Option<bool> {
    match meta {
        syn::Meta::Path(path) => {
            let name = path.get_ident()?.to_string();
            match name.as_str() {
                "false" | "test" => Some(false),
                "true" | "debug_assertions" => Some(true),
                "unix" => Some(cfg!(unix)),
                "windows" => Some(cfg!(windows)),
                _ => None,
            }
        }
        syn::Meta::NameValue(value) => {
            let name = value.path.get_ident()?.to_string();
            let syn::Expr::Lit(syn::ExprLit {
                lit: syn::Lit::Str(literal),
                ..
            }) = &value.value
            else {
                return None;
            };
            match name.as_str() {
                "target_os" => Some(literal.value() == std::env::consts::OS),
                "target_arch" => Some(literal.value() == std::env::consts::ARCH),
                _ => None,
            }
        }
        syn::Meta::List(list) => {
            let name = list.path.get_ident()?.to_string();
            let parser = syn::punctuated::Punctuated::<syn::Meta, syn::Token![,]>::parse_terminated;
            let values = parser.parse2(list.tokens.clone()).ok()?;
            let evaluated = values.iter().map(evaluate_cfg_meta).collect::<Vec<_>>();
            match name.as_str() {
                "any" => {
                    if evaluated.iter().any(|value| *value == Some(true)) {
                        Some(true)
                    } else if evaluated.iter().all(Option::is_some) {
                        Some(false)
                    } else {
                        None
                    }
                }
                "all" => {
                    if evaluated.iter().any(|value| *value == Some(false)) {
                        Some(false)
                    } else if evaluated.iter().all(|value| *value == Some(true)) {
                        Some(true)
                    } else {
                        None
                    }
                }
                "not" if evaluated.len() == 1 => evaluated[0].map(|value| !value),
                _ => None,
            }
        }
    }
}

fn has_non_runtime_attribute(attributes: &[syn::Attribute]) -> bool {
    has_statically_disabled_cfg(attributes)
        || attributes
            .iter()
            .any(|attribute| attribute.path().is_ident("test"))
}

#[derive(Debug, Default)]
struct RustApiObservation {
    chains: Vec<ObservedApiChain>,
    emitted_markers: BTreeMap<String, Vec<String>>,
}

#[derive(Debug, Clone)]
struct ObservedApiChain {
    receiver: RustApiReceiver,
    terminal: String,
    scope: String,
}

struct ApiChainVisitor {
    file_label: String,
    next_scope: usize,
    scope_stack: Vec<String>,
    observation: RustApiObservation,
}

impl ApiChainVisitor {
    fn new(file_label: String) -> Self {
        Self {
            file_label,
            next_scope: 0,
            scope_stack: Vec::new(),
            observation: RustApiObservation::default(),
        }
    }

    fn enter_scope(&mut self, label: &str) {
        let scope = format!("{}::{label}#{}", self.file_label, self.next_scope);
        self.next_scope += 1;
        self.scope_stack.push(scope);
    }

    fn exit_scope(&mut self) {
        self.scope_stack.pop();
    }

    fn current_scope(&self) -> Option<&str> {
        self.scope_stack.last().map(String::as_str)
    }
}

impl<'ast> Visit<'ast> for ApiChainVisitor {
    fn visit_item_mod(&mut self, item: &'ast syn::ItemMod) {
        if has_statically_disabled_cfg(&item.attrs) {
            return;
        }
        if let Some((_, items)) = &item.content {
            for item in items {
                self.visit_item(item);
            }
        }
    }

    fn visit_item_fn(&mut self, function: &'ast syn::ItemFn) {
        if has_non_runtime_attribute(&function.attrs) {
            return;
        }
        self.enter_scope(&format!("fn {}", function.sig.ident));
        self.visit_block(&function.block);
        self.exit_scope();
    }

    fn visit_item_impl(&mut self, item: &'ast syn::ItemImpl) {
        if has_statically_disabled_cfg(&item.attrs) {
            return;
        }
        visit::visit_item_impl(self, item);
    }

    fn visit_impl_item_fn(&mut self, function: &'ast syn::ImplItemFn) {
        if has_non_runtime_attribute(&function.attrs) {
            return;
        }
        self.enter_scope(&format!("method {}", function.sig.ident));
        self.visit_block(&function.block);
        self.exit_scope();
    }

    fn visit_item_trait(&mut self, _item: &'ast syn::ItemTrait) {
        // Trait defaults are not independently reachable application runtime code.
    }

    fn visit_expr_closure(&mut self, closure: &'ast syn::ExprClosure) {
        self.enter_scope("closure");
        self.visit_expr(&closure.body);
        self.exit_scope();
    }

    fn visit_expr_method_call(&mut self, expression: &'ast syn::ExprMethodCall) {
        let terminal = expression.method.to_string();
        if let (Some(scope), Some(receiver)) = (
            self.current_scope().map(str::to_string),
            root_receiver(&expression.receiver),
        ) {
            let methods = receiver_method_names(&expression.receiver);
            let query_has_metadata = receiver != RustApiReceiver::Q
                || !is_query_execution_terminal(&terminal)
                || (methods.iter().any(|method| method == "purpose")
                    && methods.iter().any(|method| method == "comment"));
            let is_query_execution =
                receiver == RustApiReceiver::Q && is_query_execution_terminal(&terminal);
            if query_has_metadata && !is_query_execution {
                self.observation.chains.push(ObservedApiChain {
                    receiver,
                    terminal,
                    scope,
                });
            }
        }
        visit::visit_expr_method_call(self, expression);
    }

    fn visit_expr_try(&mut self, expression: &'ast syn::ExprTry) {
        if let Some(scope) = self.current_scope().map(str::to_string)
            && let Some(call) = awaited_query_execution(&expression.expr)
        {
            self.observation.chains.push(ObservedApiChain {
                receiver: RustApiReceiver::Q,
                terminal: call.method.to_string(),
                scope,
            });
        }
        visit::visit_expr_try(self, expression);
    }

    fn visit_macro(&mut self, mac: &'ast syn::Macro) {
        let is_output = mac.path.segments.last().is_some_and(|segment| {
            matches!(segment.ident.to_string().as_str(), "print" | "println")
        });
        if is_output
            && let Some(scope) = self.current_scope().map(str::to_string)
            && let Some(marker) = first_macro_string_argument(mac)
        {
            self.observation
                .emitted_markers
                .entry(scope)
                .or_default()
                .push(marker);
        }
        visit::visit_macro(self, mac);
    }
}

fn first_macro_string_argument(mac: &syn::Macro) -> Option<String> {
    let parser = syn::punctuated::Punctuated::<syn::Expr, syn::Token![,]>::parse_terminated;
    let arguments = parser.parse2(mac.tokens.clone()).ok()?;
    match arguments.first()? {
        syn::Expr::Lit(syn::ExprLit {
            lit: syn::Lit::Str(literal),
            ..
        }) => Some(literal.value()),
        _ => None,
    }
}

fn awaited_query_execution(expression: &syn::Expr) -> Option<&syn::ExprMethodCall> {
    let expression = unwrap_expression(expression);
    let syn::Expr::Await(awaited) = expression else {
        return None;
    };
    let syn::Expr::MethodCall(call) = unwrap_expression(&awaited.base) else {
        return None;
    };
    if !is_query_execution_terminal(&call.method.to_string())
        || root_receiver(&call.receiver) != Some(RustApiReceiver::Q)
    {
        return None;
    }
    let methods = receiver_method_names(&call.receiver);
    (methods.iter().any(|method| method == "purpose")
        && methods.iter().any(|method| method == "comment"))
    .then_some(call)
}

fn is_query_execution_terminal(method: &str) -> bool {
    matches!(method, "execute" | "execute_for_list" | "execute_for_one")
}

fn unwrap_expression(mut expression: &syn::Expr) -> &syn::Expr {
    loop {
        expression = match expression {
            syn::Expr::Paren(value) => &value.expr,
            syn::Expr::Group(value) => &value.expr,
            _ => return expression,
        };
    }
}

fn runtime_marker_binding_failures(
    observation: &RustApiObservation,
    matching_chains: &[&ObservedApiChain],
    required_markers: &[String],
) -> Vec<String> {
    if required_markers.is_empty() {
        return Vec::new();
    }
    let mut capacity = BTreeMap::<&str, usize>::new();
    for chain in matching_chains {
        *capacity.entry(&chain.scope).or_default() += 1;
    }

    let mut failures = Vec::new();
    for marker in required_markers {
        let emitters = observation
            .emitted_markers
            .iter()
            .flat_map(|(scope, markers)| {
                markers
                    .iter()
                    .filter(move |observed| *observed == marker)
                    .map(move |_| scope.as_str())
            })
            .collect::<Vec<_>>();
        if emitters.len() != 1 {
            failures.push(format!(
                "marker `{marker}` is emitted {} times in compiled function scopes (expected exactly once)",
                emitters.len()
            ));
            continue;
        }
        let scope = emitters[0];
        match capacity.get_mut(scope) {
            Some(remaining) if *remaining > 0 => *remaining -= 1,
            _ => failures.push(format!(
                "marker `{marker}` is not colocated with an unbound matching API chain"
            )),
        }
    }
    failures
}

fn root_receiver(expression: &syn::Expr) -> Option<RustApiReceiver> {
    match expression {
        syn::Expr::Path(path) => path.path.segments.first().and_then(|segment| {
            match segment.ident.to_string().as_str() {
                "Q" => Some(RustApiReceiver::Q),
                "E" => Some(RustApiReceiver::E),
                _ => None,
            }
        }),
        syn::Expr::Call(call) => root_receiver(&call.func),
        syn::Expr::MethodCall(call) => root_receiver(&call.receiver),
        syn::Expr::Await(value) => root_receiver(&value.base),
        syn::Expr::Try(value) => root_receiver(&value.expr),
        syn::Expr::Paren(value) => root_receiver(&value.expr),
        syn::Expr::Group(value) => root_receiver(&value.expr),
        syn::Expr::Reference(value) => root_receiver(&value.expr),
        _ => None,
    }
}

fn receiver_method_names(expression: &syn::Expr) -> Vec<String> {
    let mut methods = Vec::new();
    let mut current = expression;
    loop {
        match current {
            syn::Expr::MethodCall(call) => {
                methods.push(call.method.to_string());
                current = &call.receiver;
            }
            syn::Expr::Await(value) => current = &value.base,
            syn::Expr::Try(value) => current = &value.expr,
            syn::Expr::Paren(value) => current = &value.expr,
            syn::Expr::Group(value) => current = &value.expr,
            syn::Expr::Reference(value) => current = &value.expr,
            _ => break,
        }
    }
    methods
}

async fn run_remote_command_check(
    execution: &crate::execution::RemoteExecution,
    workspace: &str,
    index: usize,
    requirement: &CommandRequirement,
    exact_stdout_lines: &BTreeSet<&str>,
    env_ref_names: &BTreeSet<String>,
) -> Result<FollowUpAcceptanceCheck, String> {
    let id = format!(
        "command-{index}:{} {}",
        requirement.program.as_str(),
        requirement.args.join(" ")
    );
    let failure = |detail: String| FollowUpAcceptanceCheck {
        id: id.clone(),
        kind: "command".to_string(),
        passed: false,
        detail,
    };
    let missing_refs = requirement
        .env_ref
        .iter()
        .filter(|name| !env_ref_names.contains(*name))
        .cloned()
        .collect::<Vec<_>>();
    if !missing_refs.is_empty() {
        return Err(remote_infrastructure_error(
            "remote acceptance command requested unbound environment references",
            missing_refs.join(", "),
        ));
    }

    let mut last_exit = None;
    let mut last_stdout_bytes = 0;
    let mut last_stderr_bytes = 0;
    for attempt in 1..=requirement.repeat {
        // `RemoteExecution` allocates a fresh operation ID for every call, so
        // typed repetition never aliases an earlier persisted execution.
        let result = execution
            .exec_with_environment_refs(
                requirement.program.as_str(),
                requirement.args.clone(),
                workspace.to_owned(),
                BTreeMap::new(),
                requirement.env_ref.clone(),
                std::time::Duration::from_secs(requirement.timeout_secs),
                REMOTE_COMMAND_OUTPUT_BYTES,
            )
            .await
            .map_err(|error| {
                remote_infrastructure_error(
                    "remote acceptance command transport or runner failed",
                    error,
                )
            })?;
        let combined = format!("{}\n{}", result.stdout, result.stderr);
        let mut failures = Vec::new();
        if result.exit_code != Some(requirement.expect.exit_code) {
            failures.push(match result.exit_code {
                Some(actual) => format!(
                    "exit code {actual}, expected {}",
                    requirement.expect.exit_code
                ),
                None => format!("no exit code, expected {}", requirement.expect.exit_code),
            });
        }
        if result.stdout_truncated || result.stderr_truncated {
            failures.push("runner output was truncated before acceptance could observe it".into());
        }
        if let Some(minimum) = requirement.expect.min_tests {
            let observed = observed_test_count(&combined);
            if observed < minimum {
                failures.push(format!(
                    "observed {observed} tests, expected at least {minimum}"
                ));
            }
        }
        for marker in &requirement.expect.stdout_contains {
            if !required_output_is_present(
                &result.stdout,
                &combined,
                marker,
                exact_stdout_lines.contains(marker.as_str()),
            ) {
                failures.push(format!("missing required output marker `{marker}`"));
            }
        }
        for marker in &requirement.expect.stdout_not_contains {
            if combined.contains(marker) {
                failures.push(format!("forbidden output marker was present: `{marker}`"));
            }
        }

        if !failures.is_empty() {
            return Ok(failure(format!(
                "execution {attempt}/{}: {}; output retained only as {} stdout byte(s) and {} stderr byte(s)",
                requirement.repeat,
                failures.join("; "),
                result.stdout.len(),
                result.stderr.len()
            )));
        }
        last_exit = result.exit_code;
        last_stdout_bytes = result.stdout.len();
        last_stderr_bytes = result.stderr.len();
    }

    Ok(FollowUpAcceptanceCheck {
        id,
        kind: "command".to_string(),
        passed: true,
        detail: format!(
            "{}/{} distinct remote execution(s) passed; final exit code {}; output retained only as {last_stdout_bytes} stdout byte(s) and {last_stderr_bytes} stderr byte(s)",
            requirement.repeat,
            requirement.repeat,
            last_exit
                .map(|value| value.to_string())
                .unwrap_or_else(|| "none".to_string())
        ),
    })
}

async fn run_command_check(
    workspace: &Path,
    index: usize,
    requirement: &CommandRequirement,
    exact_stdout_lines: &BTreeSet<&str>,
    command_environment: &crate::tools::DeclaredCommandEnvironment,
) -> FollowUpAcceptanceCheck {
    let id = format!(
        "command-{index}:{} {}",
        requirement.program.as_str(),
        requirement.args.join(" ")
    );
    let failure = |detail: String| FollowUpAcceptanceCheck {
        id: id.clone(),
        kind: "command".to_string(),
        passed: false,
        detail,
    };

    let declared_values = match command_environment.values_for_names(&requirement.env_ref) {
        Ok(values) => values,
        Err(error) => return failure(error),
    };

    let mut last_exit = -1;
    let mut last_output = String::new();
    for attempt in 1..=requirement.repeat {
        let mut command = tokio::process::Command::new(requirement.program.as_str());
        crate::process_env::apply_safe_environment(&mut command, workspace);
        command
            .args(&requirement.args)
            .current_dir(workspace)
            .kill_on_drop(true);
        for (name, value) in &declared_values {
            command.env(name, value);
        }
        if requirement.program == CommandProgram::Cargo
            && requirement.args.first().is_some_and(|arg| arg == "test")
            && !requirement.args.iter().any(|argument| {
                argument == "--test-threads" || argument.starts_with("--test-threads=")
            })
        {
            command.env("RUST_TEST_THREADS", "1");
        }
        let output = match crate::process_output::run_bounded_output(
            &mut command,
            std::time::Duration::from_secs(requirement.timeout_secs),
            256 * 1024,
        )
        .await
        {
            Ok(output) => output,
            Err(error) => {
                return failure(format!(
                    "execution {attempt}/{} failed: {error}",
                    requirement.repeat
                ));
            }
        };

        let stdout_raw = String::from_utf8_lossy(&output.stdout);
        let combined_raw = format!(
            "{}\n{}",
            stdout_raw,
            String::from_utf8_lossy(&output.stderr)
        );
        let actual_exit = output.status.code().unwrap_or(-1);
        let mut failures = Vec::new();
        if actual_exit != requirement.expect.exit_code {
            failures.push(format!(
                "exit code {actual_exit}, expected {}",
                requirement.expect.exit_code
            ));
        }
        if let Some(minimum) = requirement.expect.min_tests {
            let observed = observed_test_count(&combined_raw);
            if observed < minimum {
                failures.push(format!(
                    "observed {observed} tests, expected at least {minimum}"
                ));
            }
        }
        for marker in &requirement.expect.stdout_contains {
            let present = required_output_is_present(
                &stdout_raw,
                &combined_raw,
                marker,
                exact_stdout_lines.contains(marker.as_str()),
            );
            if !present {
                failures.push(format!("missing required output marker `{marker}`"));
            }
        }
        for marker in &requirement.expect.stdout_not_contains {
            if combined_raw.contains(marker) {
                failures.push(format!("forbidden output marker was present: `{marker}`"));
            }
        }

        let redacted_output = bound_text(
            &command_environment.redact_values(&combined_raw, &declared_values),
            12_000,
        );
        if !failures.is_empty() {
            return failure(format!(
                "execution {attempt}/{}: {}\nOutput:\n{redacted_output}",
                requirement.repeat,
                failures.join("; ")
            ));
        }
        last_exit = actual_exit;
        last_output = redacted_output;
    }

    FollowUpAcceptanceCheck {
        id,
        kind: "command".to_string(),
        passed: true,
        detail: format!(
            "{}/{} execution(s) passed; final exit code {last_exit}; final output:\n{last_output}",
            requirement.repeat, requirement.repeat
        ),
    }
}

fn required_output_is_present(
    stdout: &str,
    combined: &str,
    marker: &str,
    exact_stdout_line: bool,
) -> bool {
    if exact_stdout_line {
        stdout.lines().any(|line| line.trim() == marker)
    } else {
        combined.contains(marker)
    }
}

fn bound_text(content: &str, max_bytes: usize) -> String {
    if content.len() <= max_bytes {
        return content.to_string();
    }
    let mut boundary = max_bytes;
    while boundary > 0 && !content.is_char_boundary(boundary) {
        boundary -= 1;
    }
    format!("{}\n[output truncated]", &content[..boundary])
}

fn observed_test_count(output: &str) -> usize {
    let mut rust_counts = Vec::new();
    let mut maven_counts = Vec::new();
    let mut gradle_counts = Vec::new();
    for line in output.lines() {
        let normalized = line.trim().to_ascii_lowercase();
        let words = normalized.split_whitespace().collect::<Vec<_>>();
        if words.len() >= 3 && words[0] == "running" && matches!(words[2], "test" | "tests") {
            if let Ok(count) = words[1].parse::<usize>() {
                rust_counts.push(count);
            }
        }
        if let Some(rest) = normalized.split("tests run:").nth(1) {
            if let Some(value) = rest
                .trim()
                .split(|character: char| !character.is_ascii_digit())
                .next()
                && let Ok(count) = value.parse::<usize>()
            {
                maven_counts.push(count);
            }
        }
        if let Some(index) = words.iter().position(|word| *word == "tests")
            && words.get(index + 1).is_some_and(|word| {
                word.trim_matches(|character: char| !character.is_ascii_alphanumeric())
                    == "completed"
            })
            && index > 0
            && let Ok(count) = words[index - 1].parse::<usize>()
        {
            gradle_counts.push(count);
        }
    }
    if !rust_counts.is_empty() {
        rust_counts.into_iter().sum()
    } else if !maven_counts.is_empty() {
        maven_counts.into_iter().sum()
    } else {
        gradle_counts.into_iter().sum()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn spec_json(extra: &str) -> String {
        format!(r#"{{"schema":"{FOLLOWUP_ACCEPTANCE_SCHEMA}",{extra}}}"#)
    }

    #[test]
    fn schema_rejects_unknown_fields_and_parent_paths() {
        assert!(FollowUpAcceptanceSpec::parse_json(&spec_json("\"unknown\":[]")).is_err());
        let content =
            spec_json(r#""files":[{"path":"../secret","must_change":true,"min_bytes":1}]"#);
        assert!(FollowUpAcceptanceSpec::parse_json(&content).is_err());
        let protected = spec_json(r#""files":[{"path":"LIB/SRC/entity.rs","min_bytes":1}]"#);
        assert!(FollowUpAcceptanceSpec::parse_json(&protected).is_err());
    }

    #[test]
    fn moving_company_evaluation_contract_is_valid() {
        let spec = FollowUpAcceptanceSpec::parse_json(include_str!(
            "../../../benchmarks/tasks/moving-company-platform/followup-acceptance.json"
        ))
        .expect("moving-company follow-up contract");

        assert_eq!(spec.files.len(), 2);
        assert_eq!(spec.rust_api.len(), 2);
        assert_eq!(spec.commands.len(), 2);
        let checklist = spec.render_checklist();
        assert!(checklist.contains("forbidden output: [invalid field, query note]"));
        assert!(checklist.contains("KLINTCODE_RUN_OK"));
        assert!(checklist.contains("minimum observed tests: 2"));
        assert!(checklist.contains("timeout 180s"));
    }

    #[test]
    fn school_continuation_contracts_are_valid_and_cumulative() {
        let contracts = [
            include_str!(
                "../../../benchmarks/tasks/school-continuous-rust/01-school-registration.acceptance.json"
            ),
            include_str!(
                "../../../benchmarks/tasks/school-continuous-rust/02-school-information-change.acceptance.json"
            ),
            include_str!(
                "../../../benchmarks/tasks/school-continuous-rust/03-teacher-registration.acceptance.json"
            ),
            include_str!(
                "../../../benchmarks/tasks/school-continuous-rust/04-student-enrollment.acceptance.json"
            ),
            include_str!(
                "../../../benchmarks/tasks/school-continuous-rust/05-teacher-information-change.acceptance.json"
            ),
            include_str!(
                "../../../benchmarks/tasks/school-continuous-rust/06-student-grade-promotion.acceptance.json"
            ),
        ]
        .into_iter()
        .map(FollowUpAcceptanceSpec::parse_json)
        .collect::<Result<Vec<_>, _>>()
        .expect("all six school continuation contracts");
        let registration = &contracts[0];
        let information_change = &contracts[1];

        assert_eq!(registration.rust_api[0].min, 1);
        assert_eq!(information_change.rust_api[0].min, 2);
        assert_eq!(contracts.len(), 6);
        assert_eq!(
            contracts
                .iter()
                .map(|contract| contract.rust_api[0].min)
                .collect::<Vec<_>>(),
            vec![1, 2, 3, 4, 5, 6]
        );
        assert_eq!(contracts.last().unwrap().files.len(), 4);
        assert_eq!(
            contracts.last().unwrap().rust_api[0].runtime_markers.len(),
            6
        );
        assert!(
            registration.rust_api[0]
                .terminal
                .iter()
                .any(|value| value == "execute_for_one")
        );
        assert!(
            information_change.files[0]
                .contains
                .iter()
                .any(|value| value == "Klint Synthetic Test School")
        );
        assert!(
            information_change.files[0]
                .contains
                .iter()
                .any(|value| value == "Klint Synthetic Future School")
        );
        assert!(
            contracts
                .iter()
                .all(|contract| contract.commands.iter().all(|command| command
                    .env_ref
                    .iter()
                    .any(|name| name == "SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL")))
        );
        for contract in &contracts {
            let test_command = contract
                .commands
                .iter()
                .find(|command| command.args.first().is_some_and(|arg| arg == "test"))
                .expect("school contract cargo test command");
            assert_eq!(test_command.repeat, 5);
            assert_eq!(
                test_command.args,
                ["test", "--quiet", "--", "--test-threads=1"]
            );
        }
        assert!(
            registration
                .render_checklist()
                .contains("invoke the command exactly as shown; do not prefix shell assignments")
        );
    }

    #[test]
    fn files_must_exist_be_nonempty_and_change_when_required() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::write(workspace.path().join("REPORT.md"), "old report").unwrap();
        let mut before = WorkspaceSnapshot::new();
        before.insert(PathBuf::from("REPORT.md"), b"old report".to_vec());
        let requirement = FileRequirement {
            path: PathBuf::from("REPORT.md"),
            must_change: true,
            min_bytes: 5,
            contains: Vec::new(),
            not_contains: Vec::new(),
        };
        assert!(!check_file(workspace.path(), &before, 0, &requirement).passed);
        std::fs::write(workspace.path().join("REPORT.md"), "new verified report").unwrap();
        assert!(check_file(workspace.path(), &before, 0, &requirement).passed);
    }

    #[test]
    fn rust_ast_ignores_comments_strings_and_separated_expressions() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"
                // E::school(x).eval()
                const FAKE: &str = "Q::school().execute_for_list(ctx)";
                fn not_a_chain(x: X) { let _ = E::school(x); other().eval(); }
            "#,
        )
        .unwrap();
        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(
            !observation
                .chains
                .iter()
                .any(|chain| { chain.receiver == RustApiReceiver::E && chain.terminal == "eval" })
        );
        assert!(!observation.chains.iter().any(|chain| {
            chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_list"
        }));
    }

    #[test]
    fn rust_ast_counts_same_chain_q_and_e_calls() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"
                async fn query(ctx: &Ctx, x: X) {
                    let _ = Q::school().purpose("list").comment("all").execute_for_list(ctx).await?;
                    let _ = E::school(x).get_name().eval();
                }
            "#,
        )
        .unwrap();
        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert_eq!(
            observation
                .chains
                .iter()
                .filter(|chain| chain.receiver == RustApiReceiver::Q
                    && chain.terminal == "execute_for_list")
                .count(),
            1
        );
        assert_eq!(
            observation
                .chains
                .iter()
                .filter(|chain| chain.receiver == RustApiReceiver::E && chain.terminal == "eval")
                .count(),
            1
        );
    }

    #[test]
    fn q_execution_without_purpose_and_comment_does_not_count() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            "async fn query(ctx: &Ctx) { Q::school().execute_for_list(ctx).await; }",
        )
        .unwrap();
        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(!observation.chains.iter().any(|chain| {
            chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_list"
        }));
    }

    #[test]
    fn q_execution_that_does_not_propagate_its_error_does_not_count() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"async fn query(ctx: &Ctx) {
                let _ = Q::school().purpose("live").comment("query").execute_for_list(ctx).await;
            }"#,
        )
        .unwrap();
        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(!observation.chains.iter().any(|chain| {
            chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_list"
        }));
    }

    #[test]
    fn execute_for_one_with_metadata_and_await_try_binds_runtime_marker() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"async fn query(ctx: &Ctx) {
                let _ = Q::school()
                    .purpose("load one school")
                    .comment("school lookup")
                    .execute_for_one(ctx)
                    .await?;
                println!("ONE_SCHOOL_LOADED");
            }"#,
        )
        .unwrap();

        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        let matching = observation
            .chains
            .iter()
            .filter(|chain| {
                chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_one"
            })
            .collect::<Vec<_>>();
        assert_eq!(matching.len(), 1);
        assert!(
            runtime_marker_binding_failures(
                &observation,
                &matching,
                &["ONE_SCHOOL_LOADED".to_string()]
            )
            .is_empty()
        );
    }

    #[test]
    fn execute_for_one_missing_either_metadata_method_does_not_count() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"async fn no_metadata(ctx: &Ctx) {
                let _ = Q::school().execute_for_one(ctx).await?;
            }
            async fn purpose_only(ctx: &Ctx) {
                let _ = Q::school()
                    .purpose("load one school")
                    .execute_for_one(ctx)
                    .await?;
            }
            async fn comment_only(ctx: &Ctx) {
                let _ = Q::school()
                    .comment("school lookup")
                    .execute_for_one(ctx)
                    .await?;
            }"#,
        )
        .unwrap();

        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(!observation.chains.iter().any(|chain| {
            chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_one"
        }));
    }

    #[test]
    fn execute_for_one_without_direct_await_try_does_not_count() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"async fn query(ctx: &Ctx) {
                let _ = Q::school()
                    .purpose("load one school")
                    .comment("school lookup")
                    .execute_for_one(ctx)
                    .await;
            }"#,
        )
        .unwrap();

        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(!observation.chains.iter().any(|chain| {
            chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_one"
        }));
    }

    #[test]
    fn rust_ast_ignores_orphan_modules_until_declared() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(workspace.path().join("src/lib.rs"), "pub fn live() {}\n").unwrap();
        std::fs::write(
            workspace.path().join("src/orphan.rs"),
            r#"
                async fn query(ctx: &Ctx, x: X) {
                    Q::school().purpose("live").comment("query").execute_for_list(ctx).await?;
                    E::school(x).get_name().eval();
                }
            "#,
        )
        .unwrap();

        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(observation.chains.is_empty());

        std::fs::write(workspace.path().join("src/lib.rs"), "mod orphan;\n").unwrap();
        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert_eq!(
            observation
                .chains
                .iter()
                .filter(|chain| matches!(chain.terminal.as_str(), "execute_for_list" | "eval"))
                .count(),
            2
        );
    }

    #[test]
    fn rust_ast_keeps_enabled_cfg_and_ignores_statically_disabled_modules() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"
                #[cfg(any())]
                mod disabled;
                #[cfg_attr(test, allow(dead_code))]
                mod live;
            "#,
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("src/disabled.rs"),
            r#"fn disabled(x: X) { E::school(x).get_name().eval(); }"#,
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("src/live.rs"),
            r#"fn live(x: X) { E::school(x).get_name().eval(); }"#,
        )
        .unwrap();

        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert_eq!(
            observation
                .chains
                .iter()
                .filter(|chain| chain.receiver == RustApiReceiver::E && chain.terminal == "eval")
                .count(),
            1
        );
    }

    #[test]
    fn rust_ast_supports_explicit_crate_root_outside_src() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::write(
            workspace.path().join("Cargo.toml"),
            "[package]\nname='custom-root'\nversion='0.1.0'\nedition='2024'\n[lib]\npath='app.rs'\n",
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("app.rs"),
            r#"fn live(x: X) { E::school(x).get_name().eval(); }"#,
        )
        .unwrap();

        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        assert!(
            observation
                .chains
                .iter()
                .any(|chain| chain.receiver == RustApiReceiver::E && chain.terminal == "eval")
        );
    }

    #[test]
    fn runtime_markers_bind_uniquely_to_matching_compiled_scopes() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("src/main.rs"),
            r#"
                async fn live(ctx: &Ctx) {
                    Q::school().purpose("live").comment("query").execute_for_list(ctx).await?;
                    println!("Q_LIVE");
                }
                fn fake_marker() { println!("DUPLICATE"); }
                fn other_fake_marker() { println!("DUPLICATE"); }
            "#,
        )
        .unwrap();
        let observation = collect_rust_api_chains(workspace.path()).unwrap();
        let matching = observation
            .chains
            .iter()
            .filter(|chain| {
                chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_list"
            })
            .collect::<Vec<_>>();
        assert!(
            runtime_marker_binding_failures(&observation, &matching, &["Q_LIVE".to_string()])
                .is_empty()
        );
        assert!(
            !runtime_marker_binding_failures(&observation, &matching, &["DUPLICATE".to_string()])
                .is_empty()
        );
    }

    #[test]
    fn schema_rejects_secret_environment_refs_and_accepts_database_url() {
        let secret = spec_json(
            r#""commands":[{"program":"cargo","args":["run"],"env_ref":["MIMO_API_KEY"]}]"#,
        );
        assert!(FollowUpAcceptanceSpec::parse_json(&secret).is_err());
        let database = spec_json(
            r#""commands":[{"program":"cargo","args":["run"],"env_ref":["MOVING_COMPANY_SERVICE_CORE_DATABASE_URL"]}]"#,
        );
        assert!(FollowUpAcceptanceSpec::parse_json(&database).is_ok());
    }

    #[test]
    fn schema_validates_and_renders_shell_free_command_repetition() {
        let repeated = spec_json(
            r#""commands":[{"program":"cargo","args":["test","--quiet","--","--test-threads=6"],"repeat":8}]"#,
        );
        let spec = FollowUpAcceptanceSpec::parse_json(&repeated).expect("repeated command");
        assert_eq!(spec.commands[0].repeat, 8);
        assert!(
            spec.render_checklist()
                .contains("repeat 8 time(s); timeout 120s per execution")
        );

        let omitted = spec_json(r#""commands":[{"program":"cargo","args":["test"]}]"#);
        let spec = FollowUpAcceptanceSpec::parse_json(&omitted).expect("default repetition");
        assert_eq!(spec.commands[0].repeat, 1);

        let zero = spec_json(r#""commands":[{"program":"cargo","args":["test"],"repeat":0}]"#);
        assert!(FollowUpAcceptanceSpec::parse_json(&zero).is_err());
        let excessive =
            spec_json(r#""commands":[{"program":"cargo","args":["test"],"repeat":21}]"#);
        assert!(FollowUpAcceptanceSpec::parse_json(&excessive).is_err());
    }

    #[test]
    fn test_count_supports_rust_maven_and_gradle_summaries() {
        assert_eq!(observed_test_count("running 3 tests\nrunning 2 tests"), 5);
        assert_eq!(observed_test_count("Tests run: 7, Failures: 0"), 7);
        assert_eq!(observed_test_count("12 tests completed, 0 failed"), 12);
    }

    #[test]
    fn runtime_marker_requires_an_independent_stdout_line() {
        let marker = "KLINTCODE_Q_OK:1";
        let warning = format!("warning: source contains println!(\"{marker}\")");
        assert!(!required_output_is_present("", &warning, marker, true));
        assert!(required_output_is_present(
            &format!("other\n{marker}\n"),
            &warning,
            marker,
            true
        ));
        assert!(required_output_is_present("", &warning, marker, false));
    }

    #[tokio::test]
    async fn command_check_requires_exit_markers_and_test_count() {
        static ENV_LOCK: std::sync::Mutex<()> = std::sync::Mutex::new(());
        let _guard = ENV_LOCK.lock().unwrap();
        // SAFETY: this test serializes mutation of its unique environment key
        // and removes it before releasing the lock.
        unsafe {
            std::env::set_var("KLINTCODE_PARENT_SECRET_TEST", "must-not-reach-child");
        }
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("Cargo.toml"),
            "[package]\nname='acceptance-fixture'\nversion='0.1.0'\nedition='2024'\n[workspace]\n",
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            "#[test] fn works() { assert!(std::env::var(\"KLINTCODE_PARENT_SECRET_TEST\").is_err()); println!(\"KLINT_OK\"); }",
        )
        .unwrap();
        let requirement = CommandRequirement {
            program: CommandProgram::Cargo,
            args: vec![
                "test".to_string(),
                "--".to_string(),
                "--nocapture".to_string(),
            ],
            env_ref: Vec::new(),
            repeat: 1,
            timeout_secs: 60,
            expect: CommandExpectation {
                exit_code: 0,
                min_tests: Some(1),
                stdout_contains: vec!["KLINT_OK".to_string()],
                stdout_not_contains: vec!["should-not-appear".to_string()],
            },
        };
        let check = run_command_check(
            workspace.path(),
            0,
            &requirement,
            &BTreeSet::new(),
            &crate::tools::DeclaredCommandEnvironment::default(),
        )
        .await;
        // SAFETY: paired with the serialized set_var above.
        unsafe {
            std::env::remove_var("KLINTCODE_PARENT_SECRET_TEST");
        }
        assert!(check.passed, "{}", check.detail);
    }

    #[tokio::test]
    async fn command_check_uses_resolved_environment_without_parent_lookup() {
        const NAME: &str = "KLINTCODE_ACCEPTANCE_DATABASE_URL";
        const VALUE: &str = "sqlite:///isolated/acceptance.sqlite3";
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("Cargo.toml"),
            "[package]\nname='acceptance-env-fixture'\nversion='0.1.0'\nedition='2024'\n[workspace]\n",
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("src/main.rs"),
            format!(
                r#"fn main() {{
    assert!(std::env::var("MIMO_API_KEY").is_err());
    println!("KLINT_ENV_OK:{{}}", std::env::var("{NAME}").unwrap());
}}"#
            ),
        )
        .unwrap();
        let requirement = CommandRequirement {
            program: CommandProgram::Cargo,
            args: vec!["run".to_string(), "--quiet".to_string()],
            env_ref: vec![NAME.to_string()],
            repeat: 2,
            timeout_secs: 60,
            expect: CommandExpectation {
                exit_code: 0,
                min_tests: None,
                stdout_contains: vec!["KLINT_ENV_OK:".to_string()],
                stdout_not_contains: Vec::new(),
            },
        };
        let environment = crate::tools::DeclaredCommandEnvironment::new(
            BTreeMap::new(),
            BTreeMap::from([(NAME.to_string(), VALUE.to_string())]),
        );

        let check = run_command_check(
            workspace.path(),
            0,
            &requirement,
            &BTreeSet::new(),
            &environment,
        )
        .await;

        assert!(check.passed, "{}", check.detail);
        assert!(!check.detail.contains(VALUE));
        assert!(check.detail.contains(&format!("[REDACTED:{NAME}]")));
    }

    #[tokio::test]
    async fn command_check_executes_every_typed_repetition_without_a_shell() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("Cargo.toml"),
            "[package]\nname='repeat-fixture'\nversion='0.1.0'\nedition='2024'\n[workspace]\n",
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            r#"#[test]
fn records_each_process() {
    std::fs::create_dir_all("target").unwrap();
    use std::io::Write;
    writeln!(
        std::fs::OpenOptions::new()
            .create(true)
            .append(true)
            .open("target/repetition-count")
            .unwrap(),
        "run"
    )
    .unwrap();
}"#,
        )
        .unwrap();
        let requirement = CommandRequirement {
            program: CommandProgram::Cargo,
            args: vec!["test".to_string(), "--quiet".to_string()],
            env_ref: Vec::new(),
            repeat: 3,
            timeout_secs: 60,
            expect: CommandExpectation {
                exit_code: 0,
                min_tests: Some(1),
                stdout_contains: Vec::new(),
                stdout_not_contains: Vec::new(),
            },
        };

        let check = run_command_check(
            workspace.path(),
            0,
            &requirement,
            &BTreeSet::new(),
            &crate::tools::DeclaredCommandEnvironment::default(),
        )
        .await;
        assert!(check.passed, "{}", check.detail);
        let count = std::fs::read_to_string(workspace.path().join("target/repetition-count"))
            .unwrap()
            .lines()
            .count();
        assert_eq!(count, 3);
        assert!(check.detail.contains("3/3 execution(s) passed"));
    }

    #[test]
    fn remote_snapshot_filter_never_admits_generated_or_sensitive_content() {
        for (path, directory) in [
            ("rust-lib-core", true),
            ("rust-lib-core/lib/src/entity.rs", false),
            ("nested/lib/src/entity.rs", false),
            ("target/debug/app", false),
            (".klintcode/evidence.json", false),
            ("secrets/token.txt", false),
            (".env", false),
            ("keys/server.pem", false),
        ] {
            assert!(
                remote_snapshot_path_is_excluded(Path::new(path), directory),
                "{path}"
            );
        }
        for path in ["Cargo.toml", "src/lib.rs", "tests/api.rs", "README.md"] {
            assert!(
                !remote_snapshot_path_is_excluded(Path::new(path), false),
                "{path}"
            );
        }
    }

    #[test]
    fn remote_file_requirement_uses_digest_for_change_and_utf8_for_markers() {
        let path = PathBuf::from("REPORT.md");
        let old = RemoteSnapshotFile {
            bytes: 10,
            sha256: "a".repeat(64),
            utf8_content: Some("old report".to_string()),
        };
        let new = RemoteSnapshotFile {
            bytes: 19,
            sha256: "b".repeat(64),
            utf8_content: Some("new verified report".to_string()),
        };
        let before = RemoteWorkspaceSnapshot {
            workspace: "attempt-01/build".to_string(),
            files: BTreeMap::from([(path.clone(), old.clone())]),
        };
        let unchanged = RemoteWorkspaceSnapshot {
            workspace: before.workspace.clone(),
            files: BTreeMap::from([(path.clone(), old)]),
        };
        let changed = RemoteWorkspaceSnapshot {
            workspace: before.workspace.clone(),
            files: BTreeMap::from([(path.clone(), new)]),
        };
        let requirement = FileRequirement {
            path,
            must_change: true,
            min_bytes: 5,
            contains: vec!["verified".to_string()],
            not_contains: vec!["forbidden".to_string()],
        };
        assert!(!check_remote_file(&unchanged, &before, 0, &requirement).passed);
        assert!(check_remote_file(&changed, &before, 0, &requirement).passed);
    }

    #[test]
    fn remote_utf8_snapshot_is_materialized_only_for_control_plane_ast() {
        let source = r#"async fn query(ctx: &Ctx) {
            Q::school().purpose("live").comment("query").execute_for_list(ctx).await?;
        }"#;
        let snapshot = RemoteWorkspaceSnapshot {
            workspace: "attempt-01/build".to_string(),
            files: BTreeMap::from([(
                PathBuf::from("src/lib.rs"),
                RemoteSnapshotFile {
                    bytes: source.len() as u64,
                    sha256: "c".repeat(64),
                    utf8_content: Some(source.to_string()),
                },
            )]),
        };
        let materialized = materialize_remote_snapshot(&snapshot).unwrap();
        let observation = collect_rust_api_chains(materialized.path()).unwrap();
        assert!(observation.chains.iter().any(|chain| {
            chain.receiver == RustApiReceiver::Q && chain.terminal == "execute_for_list"
        }));

        let protected = RemoteWorkspaceSnapshot {
            workspace: snapshot.workspace.clone(),
            files: BTreeMap::from([(
                PathBuf::from("nested/lib/src/entity.rs"),
                RemoteSnapshotFile {
                    bytes: 1,
                    sha256: "d".repeat(64),
                    utf8_content: Some("x".to_string()),
                },
            )]),
        };
        assert!(materialize_remote_snapshot(&protected).is_err());
    }

    #[test]
    fn remote_workspace_paths_are_runner_relative_and_normalized() {
        assert_eq!(
            normalize_remote_workspace("./attempt-01/build").unwrap(),
            "attempt-01/build"
        );
        assert_eq!(normalize_remote_workspace(".").unwrap(), ".");
        for path in ["", "../outside", "/absolute", "windows\\path"] {
            assert!(normalize_remote_workspace(path).is_err(), "{path}");
        }
    }
}
