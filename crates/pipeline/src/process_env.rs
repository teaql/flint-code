//! Minimal child-process environment for generated and model-authored code.
//!
//! API keys and unrelated parent variables must never be inherited by build,
//! test, assist, or acceptance commands. Callers may add explicitly declared
//! runtime inputs after applying this baseline.

use std::ffi::OsString;
use std::path::{Path, PathBuf};

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
