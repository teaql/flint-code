//! Strict configuration boundary for remote execution targets.
//!
//! This module deliberately contains no environment-value or credential
//! fields. Authentication remains owned by OpenSSH, while client policy can
//! only narrow the hard policy installed on the remote runner host.

use crate::remote_protocol::{ClientPolicy, EnvironmentRef, MAX_FRAME_BYTES};
use crate::ssh_backend::SshTargetConfig;
use serde::Deserialize;
use std::collections::BTreeMap;
use std::fs::File;
use std::io::Read;
use std::path::{Path, PathBuf};
use std::time::Duration;
use thiserror::Error;

/// The only execution-profile schema accepted by this version of KlintCode.
pub const REMOTE_CONFIG_SCHEMA_VERSION: u32 = 1;

const MAX_CONFIG_BYTES: u64 = 1024 * 1024;
const MAX_TARGET_NAME_BYTES: usize = 128;
const MAX_HOST_ALIAS_BYTES: usize = 255;
const MAX_REMOTE_TOKEN_BYTES: usize = 4096;
const MAX_TARGET_TRIPLE_BYTES: usize = 128;
const MAX_POLICY_ITEMS: usize = 512;
const MAX_POLICY_ITEM_BYTES: usize = 1024;

const MAX_CONNECT_TIMEOUT_SECS: u64 = 300;
const MAX_UPLOAD_TIMEOUT_SECS: u64 = 3_600;
const MAX_RPC_TIMEOUT_SECS: u64 = 3_600;
const MAX_CLOSE_TIMEOUT_SECS: u64 = 120;
const MAX_INSTALL_OUTPUT_BYTES: usize = 1024 * 1024;
const MAX_CLIENT_TIMEOUT_SECS: u64 = 86_400;
const MAX_CLIENT_PAYLOAD_BYTES: u64 = (MAX_FRAME_BYTES as u64 - 64 * 1024) / 6;
const MAX_CLIENT_LIST_ENTRIES: u32 = 100_000;

/// One versioned file containing named remote execution targets.
#[derive(Debug, Clone, Deserialize)]
#[serde(deny_unknown_fields)]
pub struct RemoteExecutionConfig {
    schema_version: u32,
    default_target: String,
    targets: BTreeMap<String, RemoteTargetConfig>,
}

/// A target selected from a validated execution configuration.
#[derive(Debug, Clone, Copy)]
pub struct SelectedRemoteTarget<'a> {
    name: &'a str,
    target: &'a RemoteTargetConfig,
}

/// Runtime values ready to pass to the SSH backend and session attach call.
#[derive(Debug, Clone)]
pub struct ResolvedRemoteTarget {
    /// Stable profile-local target name.
    pub name: String,
    /// SSH transport, bootstrap, timeout, and framing configuration.
    pub ssh: SshTargetConfig,
    /// Client restrictions intersected with the remote host's hard policy.
    pub client_policy: ClientPolicy,
    /// Explicit runner-resolved values fixed when the task session is created.
    pub environment_refs: BTreeMap<String, EnvironmentRef>,
    /// Validated runner deployment and launch metadata.
    pub runner_launch: RunnerLaunchConfig,
}

/// Validated metadata for selecting and launching the uploaded runner.
///
/// These tokens are assigned to `SshTargetConfig::runner_args` and appended
/// after the bootstrap's `--` separator without invoking a client-side shell.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct RunnerLaunchConfig {
    /// Rust target triple of `SshTargetConfig::local_runner_path`.
    pub target_triple: String,
    /// Absolute path on the remote host that owns persistent task sessions.
    pub remote_session_root: String,
    /// Absolute, host-owned hard-policy file loaded by the remote runner.
    pub remote_hard_policy_path: String,
}

impl RunnerLaunchConfig {
    /// Returns strict runner argv tokens to place after bootstrap `--`.
    pub fn argv(&self) -> Vec<String> {
        vec![
            "--stdio".to_owned(),
            "--root".to_owned(),
            self.remote_session_root.clone(),
            "--policy".to_owned(),
            self.remote_hard_policy_path.clone(),
        ]
    }
}

#[derive(Debug, Clone, Deserialize)]
#[serde(deny_unknown_fields)]
struct RemoteTargetConfig {
    kind: RemoteTargetKind,
    host_alias: String,
    ssh_program: PathBuf,
    ssh_config_path: PathBuf,
    known_hosts_path: PathBuf,
    bootstrap_command: String,
    #[serde(default)]
    environment_refs: BTreeMap<String, EnvironmentRefConfig>,
    runner: RunnerConfig,
    #[serde(default)]
    timeouts: SshTimeoutConfig,
    #[serde(default)]
    limits: SshLimitConfig,
    #[serde(default)]
    policy: ClientPolicyConfig,
}

#[derive(Debug, Clone, Copy, Deserialize, PartialEq, Eq)]
#[serde(rename_all = "snake_case")]
enum RemoteTargetKind {
    Ssh,
}

#[derive(Debug, Clone, Deserialize)]
#[serde(deny_unknown_fields)]
struct EnvironmentRefConfig {
    kind: EnvironmentRefKind,
}

#[derive(Debug, Clone, Copy, Deserialize, PartialEq, Eq)]
#[serde(rename_all = "snake_case")]
enum EnvironmentRefKind {
    Host,
    SessionSqlite,
}

#[derive(Debug, Clone, Deserialize)]
#[serde(deny_unknown_fields)]
struct RunnerConfig {
    local_runner_path: PathBuf,
    target_triple: String,
    remote_session_root: String,
    remote_hard_policy_path: String,
}

#[derive(Debug, Clone, Deserialize)]
#[serde(default, deny_unknown_fields)]
struct SshTimeoutConfig {
    connect_secs: u64,
    upload_secs: u64,
    rpc_secs: u64,
    close_secs: u64,
}

impl Default for SshTimeoutConfig {
    fn default() -> Self {
        Self {
            connect_secs: 15,
            upload_secs: 120,
            rpc_secs: 60,
            close_secs: 5,
        }
    }
}

#[derive(Debug, Clone, Deserialize)]
#[serde(default, deny_unknown_fields)]
struct SshLimitConfig {
    max_line_bytes: usize,
    max_install_output_bytes: usize,
}

impl Default for SshLimitConfig {
    fn default() -> Self {
        Self {
            max_line_bytes: MAX_FRAME_BYTES,
            max_install_output_bytes: 64 * 1024,
        }
    }
}

/// Secret-free, client-side restrictions for a remote task session.
///
/// Environment entries are names only. Values are resolved by the runner or
/// carried in individual redacted execution requests, never in this profile.
#[derive(Debug, Clone, Default, Deserialize)]
#[serde(default, deny_unknown_fields)]
struct ClientPolicyConfig {
    allowed_programs: Option<Vec<String>>,
    allowed_env: Option<Vec<String>>,
    allowed_env_refs: Option<Vec<String>>,
    inherited_env: Option<Vec<String>>,
    readable: Option<Vec<String>>,
    writable: Option<Vec<String>>,
    denied: Vec<String>,
    max_timeout_secs: Option<u64>,
    max_output_bytes: Option<u64>,
    max_read_bytes: Option<u64>,
    max_write_bytes: Option<u64>,
    max_list_entries: Option<u32>,
    allow_session_sqlite: Option<bool>,
}

/// Failures loading, selecting, or validating a remote execution profile.
#[derive(Debug, Error)]
pub enum RemoteConfigError {
    /// The configuration file could not be opened or read.
    #[error("failed to read remote execution configuration {path}")]
    Read {
        /// File that failed to load.
        path: PathBuf,
        /// Underlying filesystem failure.
        #[source]
        source: std::io::Error,
    },
    /// A bounded configuration exceeded the supported input size.
    #[error("remote execution configuration exceeds {limit} bytes")]
    TooLarge {
        /// Maximum accepted encoded configuration size.
        limit: u64,
    },
    /// Configuration input was not UTF-8.
    #[error("remote execution configuration must be UTF-8")]
    NotUtf8(#[source] std::str::Utf8Error),
    /// TOML did not match the strict schema.
    #[error("invalid remote execution configuration syntax")]
    Toml(#[source] toml::de::Error),
    /// A schema other than version 1 was requested.
    #[error(
        "unsupported remote execution schema version {found}; expected {REMOTE_CONFIG_SCHEMA_VERSION}"
    )]
    UnsupportedSchema {
        /// Version found in the profile.
        found: u32,
    },
    /// A top-level field failed semantic validation.
    #[error("invalid remote execution field `{field}`: {reason}")]
    InvalidConfig {
        /// Stable schema field name; values are deliberately not echoed.
        field: &'static str,
        /// Non-sensitive explanation.
        reason: &'static str,
    },
    /// One target failed semantic validation.
    #[error("remote target `{target}` has invalid `{field}`: {reason}")]
    InvalidTarget {
        /// Target map key, never a credential.
        target: String,
        /// Stable schema field name; values are deliberately not echoed.
        field: &'static str,
        /// Non-sensitive explanation.
        reason: &'static str,
    },
    /// A requested or default target does not exist.
    #[error("remote target `{0}` is not configured")]
    UnknownTarget(String),
}

impl RemoteExecutionConfig {
    /// Loads and validates a bounded UTF-8 TOML configuration file.
    pub fn load(path: &Path) -> Result<Self, RemoteConfigError> {
        let file = File::open(path).map_err(|source| RemoteConfigError::Read {
            path: path.to_path_buf(),
            source,
        })?;
        let mut bytes = Vec::new();
        file.take(MAX_CONFIG_BYTES + 1)
            .read_to_end(&mut bytes)
            .map_err(|source| RemoteConfigError::Read {
                path: path.to_path_buf(),
                source,
            })?;
        if bytes.len() as u64 > MAX_CONFIG_BYTES {
            return Err(RemoteConfigError::TooLarge {
                limit: MAX_CONFIG_BYTES,
            });
        }
        let contents = std::str::from_utf8(&bytes).map_err(RemoteConfigError::NotUtf8)?;
        Self::from_toml_str(contents)
    }

    /// Parses and validates a strict version-1 TOML configuration.
    pub fn from_toml_str(contents: &str) -> Result<Self, RemoteConfigError> {
        if contents.len() as u64 > MAX_CONFIG_BYTES {
            return Err(RemoteConfigError::TooLarge {
                limit: MAX_CONFIG_BYTES,
            });
        }
        let config: Self = toml::from_str(contents).map_err(RemoteConfigError::Toml)?;
        config.validate()?;
        Ok(config)
    }

    /// Selects an explicit target or the configured default.
    pub fn select(
        &self,
        requested_target: Option<&str>,
    ) -> Result<SelectedRemoteTarget<'_>, RemoteConfigError> {
        self.validate()?;
        let name = requested_target.unwrap_or(&self.default_target);
        if !is_simple_identifier(name, MAX_TARGET_NAME_BYTES) {
            return Err(RemoteConfigError::InvalidConfig {
                field: "requested_target",
                reason: "must be a simple ASCII target name",
            });
        }
        let (name, target) = self
            .targets
            .get_key_value(name)
            .ok_or_else(|| RemoteConfigError::UnknownTarget(name.to_owned()))?;
        Ok(SelectedRemoteTarget { name, target })
    }

    /// Selects and builds one target for an SSH connection and session attach.
    pub fn build(
        &self,
        requested_target: Option<&str>,
    ) -> Result<ResolvedRemoteTarget, RemoteConfigError> {
        self.select(requested_target)?.build()
    }

    fn validate(&self) -> Result<(), RemoteConfigError> {
        if self.schema_version != REMOTE_CONFIG_SCHEMA_VERSION {
            return Err(RemoteConfigError::UnsupportedSchema {
                found: self.schema_version,
            });
        }
        if self.targets.is_empty() {
            return Err(RemoteConfigError::InvalidConfig {
                field: "targets",
                reason: "must contain at least one SSH target",
            });
        }
        if !is_simple_identifier(&self.default_target, MAX_TARGET_NAME_BYTES) {
            return Err(RemoteConfigError::InvalidConfig {
                field: "default_target",
                reason: "must be a simple ASCII target name",
            });
        }
        if !self.targets.contains_key(&self.default_target) {
            return Err(RemoteConfigError::UnknownTarget(
                self.default_target.clone(),
            ));
        }
        for (name, target) in &self.targets {
            if !is_simple_identifier(name, MAX_TARGET_NAME_BYTES) {
                return Err(RemoteConfigError::InvalidConfig {
                    field: "targets",
                    reason: "map keys must be simple ASCII target names",
                });
            }
            target.validate(name)?;
        }
        Ok(())
    }
}

impl SelectedRemoteTarget<'_> {
    /// Returns the selected profile-local name.
    pub fn name(&self) -> &str {
        self.name
    }

    /// Converts the selected strict profile to runtime types.
    pub fn build(&self) -> Result<ResolvedRemoteTarget, RemoteConfigError> {
        self.target.validate(self.name)?;
        let target = self.target;
        let mut ssh = SshTargetConfig::new(
            target.host_alias.clone(),
            target.runner.local_runner_path.clone(),
        );
        ssh.ssh_program = target.ssh_program.clone();
        ssh.ssh_config_path = Some(target.ssh_config_path.clone());
        ssh.known_hosts_path = Some(target.known_hosts_path.clone());
        ssh.bootstrap_command = target.bootstrap_command.clone();
        ssh.connect_timeout = Duration::from_secs(target.timeouts.connect_secs);
        ssh.upload_timeout = Duration::from_secs(target.timeouts.upload_secs);
        ssh.rpc_timeout = Duration::from_secs(target.timeouts.rpc_secs);
        ssh.close_timeout = Duration::from_secs(target.timeouts.close_secs);
        ssh.max_line_bytes = target.limits.max_line_bytes;
        ssh.max_install_output_bytes = target.limits.max_install_output_bytes;

        let runner_launch = RunnerLaunchConfig {
            target_triple: target.runner.target_triple.clone(),
            remote_session_root: target.runner.remote_session_root.clone(),
            remote_hard_policy_path: target.runner.remote_hard_policy_path.clone(),
        };
        ssh.runner_args = runner_launch.argv();

        Ok(ResolvedRemoteTarget {
            name: self.name.to_owned(),
            ssh,
            client_policy: target.policy.to_runtime(),
            environment_refs: target
                .environment_refs
                .iter()
                .map(|(name, config)| (name.clone(), config.to_runtime()))
                .collect(),
            runner_launch,
        })
    }
}

impl RemoteTargetConfig {
    fn validate(&self, target: &str) -> Result<(), RemoteConfigError> {
        match self.kind {
            RemoteTargetKind::Ssh => {}
        }
        validate_target_identifier(target, "host_alias", &self.host_alias, MAX_HOST_ALIAS_BYTES)?;
        validate_local_absolute_path(target, "ssh_program", &self.ssh_program, false)?;
        validate_local_absolute_path(target, "ssh_config_path", &self.ssh_config_path, false)?;
        validate_local_absolute_path(target, "known_hosts_path", &self.known_hosts_path, true)?;
        validate_remote_executable(target, &self.bootstrap_command)?;
        validate_local_absolute_path(
            target,
            "runner.local_runner_path",
            &self.runner.local_runner_path,
            false,
        )?;
        if !is_simple_identifier(&self.runner.target_triple, MAX_TARGET_TRIPLE_BYTES) {
            return Err(invalid_target(
                target,
                "runner.target_triple",
                "must be a bounded ASCII target triple",
            ));
        }
        validate_remote_absolute_path(
            target,
            "runner.remote_session_root",
            &self.runner.remote_session_root,
        )?;
        validate_remote_absolute_path(
            target,
            "runner.remote_hard_policy_path",
            &self.runner.remote_hard_policy_path,
        )?;
        validate_nonzero_bounded(
            target,
            "timeouts.connect_secs",
            self.timeouts.connect_secs,
            MAX_CONNECT_TIMEOUT_SECS,
        )?;
        validate_nonzero_bounded(
            target,
            "timeouts.upload_secs",
            self.timeouts.upload_secs,
            MAX_UPLOAD_TIMEOUT_SECS,
        )?;
        validate_nonzero_bounded(
            target,
            "timeouts.rpc_secs",
            self.timeouts.rpc_secs,
            MAX_RPC_TIMEOUT_SECS,
        )?;
        validate_nonzero_bounded(
            target,
            "timeouts.close_secs",
            self.timeouts.close_secs,
            MAX_CLOSE_TIMEOUT_SECS,
        )?;
        validate_nonzero_bounded(
            target,
            "limits.max_line_bytes",
            self.limits.max_line_bytes as u64,
            MAX_FRAME_BYTES as u64,
        )?;
        validate_nonzero_bounded(
            target,
            "limits.max_install_output_bytes",
            self.limits.max_install_output_bytes as u64,
            MAX_INSTALL_OUTPUT_BYTES as u64,
        )?;
        self.policy.validate(target)?;
        validate_environment_refs(target, &self.environment_refs, &self.policy)
    }
}

impl EnvironmentRefConfig {
    fn to_runtime(&self) -> EnvironmentRef {
        match self.kind {
            EnvironmentRefKind::Host => EnvironmentRef::Host,
            EnvironmentRefKind::SessionSqlite => EnvironmentRef::SessionSqlite,
        }
    }
}

impl ClientPolicyConfig {
    fn validate(&self, target: &str) -> Result<(), RemoteConfigError> {
        validate_optional_list(
            target,
            "policy.allowed_programs",
            self.allowed_programs.as_deref(),
            is_safe_program,
            "entries must be safe executable names",
        )?;
        for (field, values) in [
            ("policy.allowed_env", self.allowed_env.as_deref()),
            ("policy.allowed_env_refs", self.allowed_env_refs.as_deref()),
            ("policy.inherited_env", self.inherited_env.as_deref()),
        ] {
            validate_optional_list(
                target,
                field,
                values,
                is_environment_name,
                "entries must be environment variable names, never values",
            )?;
        }
        for (field, values) in [
            ("policy.readable", self.readable.as_deref()),
            ("policy.writable", self.writable.as_deref()),
            ("policy.denied", Some(self.denied.as_slice())),
        ] {
            validate_optional_list(
                target,
                field,
                values,
                is_safe_workspace_glob,
                "entries must be bounded relative workspace globs",
            )?;
        }
        validate_optional_nonzero_bounded(
            target,
            "policy.max_timeout_secs",
            self.max_timeout_secs,
            MAX_CLIENT_TIMEOUT_SECS,
        )?;
        validate_optional_nonzero_bounded(
            target,
            "policy.max_output_bytes",
            self.max_output_bytes,
            MAX_CLIENT_PAYLOAD_BYTES,
        )?;
        validate_optional_nonzero_bounded(
            target,
            "policy.max_read_bytes",
            self.max_read_bytes,
            MAX_CLIENT_PAYLOAD_BYTES,
        )?;
        validate_optional_nonzero_bounded(
            target,
            "policy.max_write_bytes",
            self.max_write_bytes,
            MAX_CLIENT_PAYLOAD_BYTES,
        )?;
        if let Some(value) = self.max_list_entries
            && (value == 0 || value > MAX_CLIENT_LIST_ENTRIES)
        {
            return Err(invalid_target(
                target,
                "policy.max_list_entries",
                "must be non-zero and within the client safety bound",
            ));
        }
        Ok(())
    }

    fn to_runtime(&self) -> ClientPolicy {
        ClientPolicy {
            allowed_programs: normalized_optional(self.allowed_programs.clone()),
            allowed_env: normalized_optional(self.allowed_env.clone()),
            allowed_env_refs: normalized_optional(self.allowed_env_refs.clone()),
            inherited_env: normalized_optional(self.inherited_env.clone()),
            readable: normalized_optional(self.readable.clone()),
            writable: normalized_optional(self.writable.clone()),
            denied: normalized(self.denied.clone()),
            max_timeout_secs: self.max_timeout_secs,
            max_output_bytes: self.max_output_bytes,
            max_read_bytes: self.max_read_bytes,
            max_write_bytes: self.max_write_bytes,
            max_list_entries: self.max_list_entries,
            allow_session_sqlite: self.allow_session_sqlite,
        }
    }
}

fn validate_target_identifier(
    target: &str,
    field: &'static str,
    value: &str,
    max_bytes: usize,
) -> Result<(), RemoteConfigError> {
    if !is_simple_identifier(value, max_bytes) {
        return Err(invalid_target(
            target,
            field,
            "must be a simple ASCII SSH Host alias",
        ));
    }
    Ok(())
}

fn validate_local_absolute_path(
    target: &str,
    field: &'static str,
    path: &Path,
    reject_whitespace: bool,
) -> Result<(), RemoteConfigError> {
    let Some(text) = path.to_str() else {
        return Err(invalid_target(target, field, "must be a UTF-8 path"));
    };
    if !path.is_absolute()
        || text.is_empty()
        || text == "/"
        || text.contains('\0')
        || path.components().any(|component| {
            matches!(
                component,
                std::path::Component::CurDir | std::path::Component::ParentDir
            )
        })
    {
        return Err(invalid_target(
            target,
            field,
            "must be an absolute local path",
        ));
    }
    if reject_whitespace && text.chars().any(char::is_whitespace) {
        return Err(invalid_target(
            target,
            field,
            "must be absolute and contain no whitespace",
        ));
    }
    Ok(())
}

fn validate_remote_executable(target: &str, value: &str) -> Result<(), RemoteConfigError> {
    if value.is_empty()
        || value == "/"
        || value == "."
        || value.len() > MAX_REMOTE_TOKEN_BYTES
        || !value.chars().all(is_safe_remote_token_character)
        || has_parent_component(value)
    {
        return Err(invalid_target(
            target,
            "bootstrap_command",
            "must be one safe executable token without shell syntax",
        ));
    }
    Ok(())
}

fn validate_remote_absolute_path(
    target: &str,
    field: &'static str,
    value: &str,
) -> Result<(), RemoteConfigError> {
    if !value.starts_with('/')
        || value == "/"
        || value.len() > MAX_REMOTE_TOKEN_BYTES
        || !value.chars().all(is_safe_remote_token_character)
        || has_parent_component(value)
    {
        return Err(invalid_target(
            target,
            field,
            "must be a non-root absolute remote path and one safe argv token",
        ));
    }
    Ok(())
}

fn is_safe_remote_token_character(character: char) -> bool {
    character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.' | '/')
}

fn has_parent_component(value: &str) -> bool {
    value.split('/').any(|component| component == "..")
}

fn is_simple_identifier(value: &str, max_bytes: usize) -> bool {
    let mut characters = value.chars();
    !value.is_empty()
        && value.len() <= max_bytes
        && characters
            .next()
            .is_some_and(|character| character.is_ascii_alphanumeric())
        && characters.all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.')
        })
}

fn is_safe_program(value: &str) -> bool {
    !value.is_empty()
        && value.len() <= MAX_POLICY_ITEM_BYTES
        && value.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.' | '+')
        })
}

fn is_environment_name(value: &str) -> bool {
    let mut characters = value.chars();
    !value.is_empty()
        && value.len() <= MAX_POLICY_ITEM_BYTES
        && characters
            .next()
            .is_some_and(|character| character.is_ascii_alphabetic() || character == '_')
        && characters.all(|character| character.is_ascii_alphanumeric() || character == '_')
}

fn is_secret_like_environment_name(value: &str) -> bool {
    const SECRET_WORDS: &[&str] = &[
        "secret",
        "secrets",
        "token",
        "tokens",
        "password",
        "passwd",
        "credential",
        "credentials",
        "private",
        "key",
    ];
    value.split('_').any(|word| {
        SECRET_WORDS
            .iter()
            .any(|secret| word.eq_ignore_ascii_case(secret))
    })
}

fn validate_environment_refs(
    target: &str,
    refs: &BTreeMap<String, EnvironmentRefConfig>,
    policy: &ClientPolicyConfig,
) -> Result<(), RemoteConfigError> {
    if refs.len() > MAX_POLICY_ITEMS {
        return Err(invalid_target(
            target,
            "environment_refs",
            "contains too many named environment references",
        ));
    }
    for (name, config) in refs {
        if !is_environment_name(name) || is_secret_like_environment_name(name) {
            return Err(invalid_target(
                target,
                "environment_refs",
                "names must be safe and must not look like secrets",
            ));
        }
        if policy
            .allowed_env_refs
            .as_ref()
            .is_some_and(|allowed| !allowed.iter().any(|item| item == name))
        {
            return Err(invalid_target(
                target,
                "environment_refs",
                "each name must be permitted by client policy allowed_env_refs",
            ));
        }
        if config.kind == EnvironmentRefKind::SessionSqlite
            && policy.allow_session_sqlite == Some(false)
        {
            return Err(invalid_target(
                target,
                "environment_refs",
                "session_sqlite cannot be disabled by the client policy",
            ));
        }
    }
    Ok(())
}

fn is_safe_workspace_glob(value: &str) -> bool {
    !value.is_empty()
        && value.len() <= MAX_POLICY_ITEM_BYTES
        && !value.starts_with('/')
        && !value.contains('\0')
        && !value.contains('\\')
        && !value.chars().any(char::is_control)
        && !has_parent_component(value)
}

fn validate_optional_list(
    target: &str,
    field: &'static str,
    values: Option<&[String]>,
    predicate: fn(&str) -> bool,
    reason: &'static str,
) -> Result<(), RemoteConfigError> {
    let Some(values) = values else {
        return Ok(());
    };
    if values.len() > MAX_POLICY_ITEMS || values.iter().any(|value| !predicate(value)) {
        return Err(invalid_target(target, field, reason));
    }
    Ok(())
}

fn validate_nonzero_bounded(
    target: &str,
    field: &'static str,
    value: u64,
    max: u64,
) -> Result<(), RemoteConfigError> {
    if value == 0 || value > max {
        return Err(invalid_target(
            target,
            field,
            "must be non-zero and within the supported bound",
        ));
    }
    Ok(())
}

fn validate_optional_nonzero_bounded(
    target: &str,
    field: &'static str,
    value: Option<u64>,
    max: u64,
) -> Result<(), RemoteConfigError> {
    if let Some(value) = value {
        validate_nonzero_bounded(target, field, value, max)?;
    }
    Ok(())
}

fn invalid_target(target: &str, field: &'static str, reason: &'static str) -> RemoteConfigError {
    RemoteConfigError::InvalidTarget {
        target: target.to_owned(),
        field,
        reason,
    }
}

fn normalized_optional(values: Option<Vec<String>>) -> Option<Vec<String>> {
    values.map(normalized)
}

fn normalized(mut values: Vec<String>) -> Vec<String> {
    values.sort();
    values.dedup();
    values
}

#[cfg(test)]
mod tests {
    use super::*;

    const VALID: &str = r#"
schema_version = 1
default_target = "ca-mini"

[targets.ca-mini]
kind = "ssh"
host_alias = "ca-mini"
ssh_program = "/usr/bin/ssh"
ssh_config_path = "/tmp/klintcode-ssh-config"
known_hosts_path = "/tmp/klintcode-known-hosts"
bootstrap_command = "/usr/local/bin/klintcode-bootstrap"
environment_refs = { DATABASE_URL = { kind = "session_sqlite" }, CARGO_HOME = { kind = "host" } }

[targets.ca-mini.runner]
local_runner_path = "/tmp/x86_64-unknown-linux-musl/klintcode-runner"
target_triple = "x86_64-unknown-linux-musl"
remote_session_root = "/var/lib/klintcode/sessions"
remote_hard_policy_path = "/etc/klintcode/runner-policy.toml"

[targets.ca-mini.timeouts]
connect_secs = 10
upload_secs = 240
rpc_secs = 90
close_secs = 10

[targets.ca-mini.limits]
max_line_bytes = 8388608
max_install_output_bytes = 65536

[targets.ca-mini.policy]
allowed_programs = ["cargo", "cargo", "git"]
allowed_env = ["RUST_LOG"]
allowed_env_refs = ["CARGO_HOME", "DATABASE_URL"]
inherited_env = ["PATH"]
readable = ["**"]
writable = ["src/**", "Cargo.toml"]
denied = [".git/**", "**/.env"]
max_timeout_secs = 900
max_output_bytes = 262144
max_read_bytes = 262144
max_write_bytes = 1048576
max_list_entries = 2000
allow_session_sqlite = true
"#;

    #[test]
    fn loads_selects_and_builds_strict_ssh_target() {
        let config = RemoteExecutionConfig::from_toml_str(VALID).unwrap();
        let selected = config.select(None).unwrap();
        assert_eq!(selected.name(), "ca-mini");

        let built = selected.build().unwrap();
        assert_eq!(built.name, "ca-mini");
        assert_eq!(built.ssh.target_alias, "ca-mini");
        assert_eq!(built.ssh.ssh_program, Path::new("/usr/bin/ssh"));
        assert_eq!(
            built.ssh.known_hosts_path.as_deref(),
            Some(Path::new("/tmp/klintcode-known-hosts"))
        );
        assert_eq!(built.ssh.rpc_timeout, Duration::from_secs(90));
        assert_eq!(built.ssh.runner_args, built.runner_launch.argv());
        assert_eq!(
            built.client_policy.allowed_programs,
            Some(vec!["cargo".to_owned(), "git".to_owned()])
        );
        assert_eq!(built.client_policy.allow_session_sqlite, Some(true));
        assert_eq!(
            built.environment_refs.get("DATABASE_URL"),
            Some(&EnvironmentRef::SessionSqlite)
        );
        assert_eq!(
            built.environment_refs.get("CARGO_HOME"),
            Some(&EnvironmentRef::Host)
        );
        assert_eq!(
            built.runner_launch.argv(),
            vec![
                "--stdio",
                "--root",
                "/var/lib/klintcode/sessions",
                "--policy",
                "/etc/klintcode/runner-policy.toml",
            ]
        );
    }

    #[test]
    fn rejects_unknown_fields_at_every_nested_boundary() {
        let with_unknown = VALID.replace(
            "allow_session_sqlite = true",
            "allow_session_sqlite = true\nsecret_value = \"must-not-be-accepted\"",
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&with_unknown),
            Err(RemoteConfigError::Toml(_))
        ));

        let with_runner_unknown = VALID.replace(
            "target_triple = \"x86_64-unknown-linux-musl\"",
            "target_triple = \"x86_64-unknown-linux-musl\"\narguments = [\"--unsafe\"]",
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&with_runner_unknown),
            Err(RemoteConfigError::Toml(_))
        ));
    }

    #[test]
    fn enforces_schema_default_and_target_selection() {
        let wrong_version = VALID.replacen("schema_version = 1", "schema_version = 2", 1);
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&wrong_version),
            Err(RemoteConfigError::UnsupportedSchema { found: 2 })
        ));

        let wrong_default = VALID.replacen(
            "default_target = \"ca-mini\"",
            "default_target = \"missing\"",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&wrong_default),
            Err(RemoteConfigError::UnknownTarget(name)) if name == "missing"
        ));

        let config = RemoteExecutionConfig::from_toml_str(VALID).unwrap();
        assert!(matches!(
            config.select(Some("other")),
            Err(RemoteConfigError::UnknownTarget(name)) if name == "other"
        ));
    }

    #[test]
    fn rejects_shell_destinations_and_unsafe_remote_tokens() {
        let direct_destination = VALID.replacen(
            "host_alias = \"ca-mini\"",
            "host_alias = \"philip@iot.doublechaintech.com\"",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&direct_destination),
            Err(RemoteConfigError::InvalidTarget {
                field: "host_alias",
                ..
            })
        ));

        let shell_bootstrap = VALID.replacen(
            "bootstrap_command = \"/usr/local/bin/klintcode-bootstrap\"",
            "bootstrap_command = \"bootstrap; id\"",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&shell_bootstrap),
            Err(RemoteConfigError::InvalidTarget {
                field: "bootstrap_command",
                ..
            })
        ));

        let traversing_root = VALID.replacen(
            "remote_session_root = \"/var/lib/klintcode/sessions\"",
            "remote_session_root = \"/var/lib/../secrets\"",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&traversing_root),
            Err(RemoteConfigError::InvalidTarget {
                field: "runner.remote_session_root",
                ..
            })
        ));
    }

    #[test]
    fn requires_absolute_local_paths_and_known_hosts() {
        let relative_runner = VALID.replacen(
            "local_runner_path = \"/tmp/x86_64-unknown-linux-musl/klintcode-runner\"",
            "local_runner_path = \"target/release/klintcode-runner\"",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&relative_runner),
            Err(RemoteConfigError::InvalidTarget {
                field: "runner.local_runner_path",
                ..
            })
        ));

        let missing_known_hosts =
            VALID.replace("known_hosts_path = \"/tmp/klintcode-known-hosts\"\n", "");
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&missing_known_hosts),
            Err(RemoteConfigError::Toml(_))
        ));
    }

    #[test]
    fn bounds_transport_and_client_policy_limits() {
        let zero_timeout = VALID.replacen("rpc_secs = 90", "rpc_secs = 0", 1);
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&zero_timeout),
            Err(RemoteConfigError::InvalidTarget {
                field: "timeouts.rpc_secs",
                ..
            })
        ));

        let oversized_frame =
            VALID.replacen("max_line_bytes = 8388608", "max_line_bytes = 8388609", 1);
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&oversized_frame),
            Err(RemoteConfigError::InvalidTarget {
                field: "limits.max_line_bytes",
                ..
            })
        ));

        let zero_policy_limit = VALID.replacen("max_timeout_secs = 900", "max_timeout_secs = 0", 1);
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&zero_policy_limit),
            Err(RemoteConfigError::InvalidTarget {
                field: "policy.max_timeout_secs",
                ..
            })
        ));
    }

    #[test]
    fn client_policy_accepts_names_but_never_secret_values() {
        let secret_assignment = VALID.replacen(
            "allowed_env = [\"RUST_LOG\"]",
            "allowed_env = [\"MIMO_API_KEY=secret\"]",
            1,
        );
        let error = RemoteExecutionConfig::from_toml_str(&secret_assignment).unwrap_err();
        assert!(matches!(
            error,
            RemoteConfigError::InvalidTarget {
                field: "policy.allowed_env",
                ..
            }
        ));
        assert!(!error.to_string().contains("secret"));

        let traversal = VALID.replacen(
            "writable = [\"src/**\", \"Cargo.toml\"]",
            "writable = [\"../outside/**\"]",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&traversal),
            Err(RemoteConfigError::InvalidTarget {
                field: "policy.writable",
                ..
            })
        ));
    }

    #[test]
    fn environment_references_require_explicit_safe_kinds_and_policy() {
        let secret_ref = VALID.replacen(
            "environment_refs = { DATABASE_URL = { kind = \"session_sqlite\" }, CARGO_HOME = { kind = \"host\" } }",
            "environment_refs = { MIMO_API_KEY = { kind = \"host\" } }",
            1,
        );
        let error = RemoteExecutionConfig::from_toml_str(&secret_ref).unwrap_err();
        assert!(matches!(
            error,
            RemoteConfigError::InvalidTarget {
                field: "environment_refs",
                ..
            }
        ));
        assert!(!error.to_string().contains("MIMO_API_KEY"));

        let narrowed_out = VALID.replacen(
            "allowed_env_refs = [\"CARGO_HOME\", \"DATABASE_URL\"]",
            "allowed_env_refs = [\"CARGO_HOME\"]",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&narrowed_out),
            Err(RemoteConfigError::InvalidTarget {
                field: "environment_refs",
                ..
            })
        ));

        let sqlite_disabled = VALID.replacen(
            "allow_session_sqlite = true",
            "allow_session_sqlite = false",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&sqlite_disabled),
            Err(RemoteConfigError::InvalidTarget {
                field: "environment_refs",
                ..
            })
        ));

        let inferred_kind = VALID.replacen(
            "CARGO_HOME = { kind = \"host\" }",
            "CARGO_HOME = { kind = \"host\", value = \"not-accepted\" }",
            1,
        );
        assert!(matches!(
            RemoteExecutionConfig::from_toml_str(&inferred_kind),
            Err(RemoteConfigError::Toml(_))
        ));
    }
}
