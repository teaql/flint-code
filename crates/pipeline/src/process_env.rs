//! Minimal child-process environment for generated and model-authored code.
//!
//! API keys and unrelated parent variables must never be inherited by build,
//! test, assist, or acceptance commands. Callers may add explicitly declared
//! runtime inputs after applying this baseline.

use std::collections::BTreeMap;
use std::ffi::OsString;
use std::path::{Path, PathBuf};
use std::sync::Arc;
use std::sync::Mutex;

/// Per-executor storage for SQLite databases used by generated applications.
///
/// A `PipelineExecutor` owns one of these for its entire lifetime. Every child
/// command receives a database path below this private directory, so parallel
/// executors never share the caller-provided SQLite file. The directory is
/// removed automatically when the executor is dropped.
#[derive(Clone)]
pub(crate) struct SqliteDatabaseIsolation {
    directory: Arc<tempfile::TempDir>,
    databases: Arc<Mutex<BTreeMap<String, String>>>,
}

impl SqliteDatabaseIsolation {
    pub(crate) fn new() -> std::io::Result<Self> {
        Ok(Self {
            directory: Arc::new(
                tempfile::Builder::new()
                    .prefix("klintcode-sqlite-")
                    .tempdir()?,
            ),
            databases: Arc::new(Mutex::new(BTreeMap::new())),
        })
    }

    /// Replace a declared SQLite URL with a stable file URL owned by this
    /// executor and environment name. Non-SQLite values are returned unchanged.
    ///
    /// Stability is important: the coding agent, independent verifier, typed
    /// acceptance commands, retries, and command repetitions must all observe
    /// the same database during one session. A different executor owns a
    /// different temporary directory and therefore receives a different URL.
    pub(crate) fn isolate_value(&self, name: &str, value: &str) -> String {
        if !is_sqlite_url(value) {
            return value.to_string();
        }
        let mut databases = self
            .databases
            .lock()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        if let Some(isolated) = databases.get(name) {
            return isolated.clone();
        }
        let safe_name = name
            .chars()
            .map(|character| {
                if character.is_ascii_alphanumeric() {
                    character.to_ascii_lowercase()
                } else {
                    '-'
                }
            })
            .take(80)
            .collect::<String>();
        let ordinal = databases.len() + 1;
        let path = self
            .directory
            .path()
            .join(format!("{ordinal:03}-{safe_name}.sqlite3"));
        let isolated = format!("sqlite://{}", path.display());
        databases.insert(name.to_string(), isolated.clone());
        isolated
    }

    #[cfg(test)]
    pub(crate) fn directory(&self) -> &Path {
        self.directory.path()
    }
}

impl std::fmt::Debug for SqliteDatabaseIsolation {
    fn fmt(&self, formatter: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        formatter
            .debug_struct("SqliteDatabaseIsolation")
            .field("directory", &self.directory.path())
            .finish_non_exhaustive()
    }
}

/// `sqlite::memory:` is also converted to a file database because multiple
/// connections and child commands otherwise do not share it. Query parameters
/// are intentionally discarded: `mode=ro`, `immutable=1`, and `mode=memory`
/// are incompatible with the new executor-owned file.
fn is_sqlite_url(value: &str) -> bool {
    let normalized = value.to_ascii_lowercase();
    normalized.starts_with("sqlite:")
}

/// Remove the inherited environment and restore only toolchain/runtime values
/// needed by Cargo, Rustup, Maven, Gradle, and ordinary terminal output.
pub(crate) fn apply_safe_environment(command: &mut tokio::process::Command, workspace: &Path) {
    const ALLOWED: &[&str] = &[
        "CARGO_HOME",
        "RUSTUP_HOME",
        "JAVA_HOME",
        "MAVEN_HOME",
        "M2_HOME",
        "GRADLE_HOME",
        "TMPDIR",
        "LANG",
        "LC_ALL",
    ];

    let mut values = ALLOWED
        .iter()
        .filter_map(|name| std::env::var_os(name).map(|value| (OsString::from(name), value)))
        .collect::<Vec<_>>();
    if let Some(path) =
        std::env::var_os("PATH").and_then(|value| sanitized_search_path(&value, workspace))
    {
        values.push((OsString::from("PATH"), path));
    }
    // Keep dependency caches usable in an air-gapped environment without
    // exposing the caller's home directory as HOME to generated code.
    if let Some(home) = std::env::var_os("HOME") {
        let home = PathBuf::from(home);
        if std::env::var_os("CARGO_HOME").is_none() {
            let cargo_home = home.join(".cargo");
            if cargo_home.is_dir() {
                values.push((OsString::from("CARGO_HOME"), cargo_home.into_os_string()));
            }
        }
        if std::env::var_os("RUSTUP_HOME").is_none() {
            let rustup_home = home.join(".rustup");
            if rustup_home.is_dir() {
                values.push((OsString::from("RUSTUP_HOME"), rustup_home.into_os_string()));
            }
        }
        let maven_repository = home.join(".m2/repository");
        if maven_repository.is_dir() {
            values.push((
                OsString::from("MAVEN_OPTS"),
                OsString::from(format!("-Dmaven.repo.local={}", maven_repository.display())),
            ));
        }
        let gradle_home = home.join(".gradle");
        if gradle_home.is_dir() {
            values.push((
                OsString::from("GRADLE_USER_HOME"),
                gradle_home.into_os_string(),
            ));
        }
    }
    command.env_clear();
    for (name, value) in values {
        command.env(name, value);
    }
    command
        .env("HOME", workspace)
        .env("PAGER", "cat")
        .env("GIT_PAGER", "cat")
        .env("GIT_TERMINAL_PROMPT", "0")
        .env("CARGO_TERM_COLOR", "never")
        .env("NO_COLOR", "1")
        .env("CI", "1")
        .kill_on_drop(true);
}

fn sanitized_search_path(value: &std::ffi::OsStr, workspace: &Path) -> Option<OsString> {
    let root = workspace.canonicalize().ok();
    let entries = std::env::split_paths(value)
        .filter(|path| path.is_absolute())
        .filter(|path| {
            let resolved = path.canonicalize().unwrap_or_else(|_| path.to_path_buf());
            root.as_ref().is_none_or(|root| !resolved.starts_with(root))
        })
        .collect::<Vec<_>>();
    std::env::join_paths(entries).ok()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn sqlite_urls_are_stable_within_one_executor_and_unique_between_executors() {
        let isolation = SqliteDatabaseIsolation::new().expect("SQLite isolation");
        let first =
            isolation.isolate_value("SCHOOL_SERVICE_DATABASE_URL", "sqlite://shared.db?mode=ro");
        let second = isolation.isolate_value("SCHOOL_SERVICE_DATABASE_URL", "sqlite::memory:");
        let case_distinct =
            isolation.isolate_value("school_service_database_url", "sqlite://shared.db");
        let other = SqliteDatabaseIsolation::new()
            .expect("second SQLite isolation")
            .isolate_value("SCHOOL_SERVICE_DATABASE_URL", "sqlite://shared.db");

        assert_eq!(first, second);
        assert_ne!(first, case_distinct);
        assert_ne!(first, other);
        assert!(!first.contains("shared.db"));
        assert!(!first.contains("mode=ro"));
        assert!(first.contains(&isolation.directory().display().to_string()));
    }

    #[test]
    fn non_sqlite_runtime_values_are_not_rewritten() {
        let isolation = SqliteDatabaseIsolation::new().expect("SQLite isolation");
        assert_eq!(
            isolation.isolate_value("DATABASE_URL", "postgres://db/service"),
            "postgres://db/service"
        );
    }
}
