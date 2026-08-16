//! Pure, Agent-independent policy for FlintCode workspace tools.
//!
//! This crate performs lexical admission only. The remote Runner remains the
//! authoritative enforcement boundary and revalidates every operation.

use std::path::{Component, Path};

/// A no-shell command admitted for remote Runner execution.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct ApprovedRemoteCommand {
    pub program: String,
    pub args: Vec<String>,
}

/// Parse one command string into a program and argv using the shared remote
/// allowlist. Shell syntax, quoting, variables, and chaining are rejected.
pub fn approve_remote_command(command: &str) -> Result<ApprovedRemoteCommand, String> {
    let words = strict_command_words(command)?;
    let Some((program, args)) = words.split_first() else {
        return Err("empty command".to_string());
    };
    validate_remote_command(program, args)?;
    Ok(ApprovedRemoteCommand {
        program: program.clone(),
        args: args.to_vec(),
    })
}

/// Validate a structured remote program and argv.
pub fn validate_remote_command(program: &str, args: &[String]) -> Result<(), String> {
    match program {
        "cargo" => validate_remote_cargo_args(args),
        "mvn" => validate_remote_maven_args(args),
        "gradle" => validate_gradle_args(args),
        _ => Err(format!(
            "command `{program}` is not allowed; use cargo check/test/run, cargo fmt --check, cargo teaql assist, mvn compile/test, or gradle build/test"
        )),
    }
}

/// Normalize a workspace-relative path without accessing a filesystem.
pub fn checked_remote_workspace_path(requested: &str) -> Result<String, String> {
    if requested.is_empty() || requested.as_bytes().contains(&0) || requested.contains('\\') {
        return Err("Error: empty or invalid remote workspace path".to_string());
    }
    let path = Path::new(requested);
    if path.is_absolute()
        || path.components().any(|component| {
            matches!(
                component,
                Component::ParentDir | Component::Prefix(_) | Component::RootDir
            )
        })
    {
        return Err(format!(
            "Error: path `{requested}` is outside the remote project directory; use a workspace-relative path"
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
    Ok(if normalized.is_empty() {
        ".".to_string()
    } else {
        normalized
    })
}

/// Join two already-normalized workspace-relative paths.
pub fn join_remote_workspace_path(root: &str, relative: &str) -> String {
    let root = root.trim_end_matches('/');
    if relative == "." {
        root.to_string()
    } else if root.is_empty() || root == "." {
        relative.to_string()
    } else {
        format!("{root}/{relative}")
    }
}

/// Return whether a path is generated library source that Agents may not read,
/// search, or modify.
pub fn is_generated_library_source_relative(path: &str) -> bool {
    let normalized = path.to_ascii_lowercase();
    normalized == "lib/src"
        || normalized.starts_with("lib/src/")
        || normalized == "rust-lib-core/lib/src"
        || normalized.ends_with("/rust-lib-core/lib/src")
        || normalized.contains("/rust-lib-core/lib/src/")
        || normalized.starts_with("rust-lib-core/lib/src/")
        || normalized == "java-lib-core"
        || normalized.contains("/java-lib-core/")
        || normalized.starts_with("java-lib-core/")
        || normalized == "java-web-spring-boot"
        || normalized.contains("/java-web-spring-boot/")
        || normalized.starts_with("java-web-spring-boot/")
}

/// Return whether Agent writes would mutate Pipeline-owned evidence or inputs.
pub fn is_read_only_workspace_evidence_relative(path: &str) -> bool {
    let normalized = path.to_ascii_lowercase();
    normalized == "agents.md"
        || normalized == "model"
        || normalized.starts_with("model/")
        || normalized == ".klintcode"
        || normalized.starts_with(".klintcode/")
}

fn strict_command_words(command: &str) -> Result<Vec<String>, String> {
    if command.trim() != command || command.is_empty() {
        return Err("commands must not have leading/trailing whitespace".to_string());
    }
    if command.chars().any(|character| {
        character.is_control()
            || matches!(
                character,
                '|' | '&'
                    | ';'
                    | '<'
                    | '>'
                    | '$'
                    | '`'
                    | '*'
                    | '?'
                    | '['
                    | ']'
                    | '{'
                    | '}'
                    | '('
                    | ')'
                    | '\''
                    | '"'
                    | '\\'
            )
    }) {
        return Err(
            "shell syntax, quoting, variables, globs, substitutions, and command chaining are not allowed"
                .to_string(),
        );
    }
    let words = command
        .split_ascii_whitespace()
        .map(str::to_string)
        .collect::<Vec<_>>();
    if words.is_empty() {
        Err("empty command".to_string())
    } else {
        Ok(words)
    }
}

fn validate_remote_cargo_args(args: &[String]) -> Result<(), String> {
    let Some(subcommand) = args.first().map(String::as_str) else {
        return Err("cargo requires an allowlisted subcommand".to_string());
    };
    match subcommand {
        "check" => validate_remote_cargo_build_flags(&args[1..]),
        "test" | "run" => {
            let (cargo_args, direct_args) = split_direct_arguments(&args[1..]);
            validate_remote_cargo_build_flags(cargo_args)?;
            validate_direct_program_arguments(direct_args)
        }
        "fmt" => match &args[1..] {
            [flag] if flag == "--check" => Ok(()),
            [separator, flag] if separator == "--" && flag == "--check" => Ok(()),
            _ => Err(
                "only `cargo fmt --check` is allowed because formatting generated libraries would modify protected source"
                    .to_string(),
            ),
        },
        "teaql" => validate_remote_teaql_args(&args[1..]),
        _ => Err(format!(
            "cargo subcommand `{subcommand}` is not allowed; use check, test, run, fmt --check, or a TeaQL assist command"
        )),
    }
}

fn validate_remote_cargo_build_flags(args: &[String]) -> Result<(), String> {
    const VALUE_FLAGS: &[&str] = &[
        "--package",
        "-p",
        "--bin",
        "--test",
        "--features",
        "--jobs",
        "-j",
    ];
    const SWITCH_FLAGS: &[&str] = &[
        "--quiet",
        "-q",
        "--workspace",
        "--all-targets",
        "--all-features",
        "--no-default-features",
        "--locked",
        "--offline",
        "--release",
    ];
    let mut index = 0;
    while index < args.len() {
        let argument = &args[index];
        if SWITCH_FLAGS.contains(&argument.as_str()) {
            index += 1;
            continue;
        }
        if VALUE_FLAGS.contains(&argument.as_str()) {
            let value = args
                .get(index + 1)
                .ok_or_else(|| format!("cargo flag `{argument}` requires a value"))?;
            validate_remote_plain_argument(value)?;
            index += 2;
            continue;
        }
        return Err(format!(
            "cargo argument `{argument}` is not in the safe build/test allowlist"
        ));
    }
    Ok(())
}

fn validate_remote_plain_argument(argument: &str) -> Result<(), String> {
    if argument.is_empty()
        || !argument.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.' | ':' | ',')
        })
    {
        return Err(format!("unsafe command argument `{argument}`"));
    }
    Ok(())
}

fn validate_remote_teaql_args(args: &[String]) -> Result<(), String> {
    if args.len() != 3 || args[0] != "--input" {
        return Err(
            "TeaQL commands must be `cargo teaql --input <model> rust-assist-<action>/<entity>`"
                .to_string(),
        );
    }
    let input = checked_remote_workspace_path(&args[1])?;
    if is_generated_library_source_relative(&input) {
        return Err("TeaQL input cannot point into generated library source".to_string());
    }
    let action = &args[2];
    if !action.starts_with("rust-assist-")
        || !action.contains('/')
        || !action.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '/')
        })
    {
        return Err(format!(
            "Invalid TeaQL action `{action}`. Only rust-assist-query/create/update/delete are allowed"
        ));
    }
    Ok(())
}

fn validate_remote_maven_args(args: &[String]) -> Result<(), String> {
    let Some(goal) = args.first().map(String::as_str) else {
        return Err("mvn requires compile or test".to_string());
    };
    if !matches!(goal, "compile" | "test") {
        return Err(format!("Maven goal `{goal}` is not allowed"));
    }
    match &args[1..] {
        [] => Ok(()),
        [flag, pom] if flag == "-f" || flag == "--file" => {
            let path = checked_remote_workspace_path(pom)?;
            if is_generated_library_source_relative(&path) || !path.ends_with("pom.xml") {
                Err("Maven -f/--file must reference a workspace pom.xml".to_string())
            } else {
                Ok(())
            }
        }
        _ => Err("Maven accepts only `mvn compile|test [-f pom.xml]`".to_string()),
    }
}

fn validate_gradle_args(args: &[String]) -> Result<(), String> {
    match args {
        [goal] if matches!(goal.as_str(), "build" | "test" | "classes") => Ok(()),
        _ => Err(
            "Gradle accepts only `gradle build`, `gradle test`, or `gradle classes`".to_string(),
        ),
    }
}

fn split_direct_arguments(args: &[String]) -> (&[String], &[String]) {
    match args.iter().position(|argument| argument == "--") {
        Some(index) => (&args[..index], &args[index + 1..]),
        None => (args, &[]),
    }
}

fn validate_direct_program_arguments(args: &[String]) -> Result<(), String> {
    for argument in args {
        if argument.is_empty()
            || !argument.chars().all(|character| {
                character.is_ascii_alphanumeric()
                    || matches!(character, '-' | '_' | '.' | ':' | ',' | '=')
            })
        {
            return Err(format!("unsafe direct program argument `{argument}`"));
        }
    }
    Ok(())
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn paths_are_normalized_and_protected_case_insensitively() {
        assert_eq!(
            checked_remote_workspace_path("./src/main.rs").unwrap(),
            "src/main.rs"
        );
        assert!(checked_remote_workspace_path("../outside").is_err());
        assert!(is_generated_library_source_relative("LIB/SRC/entity.rs"));
        assert!(is_read_only_workspace_evidence_relative("Model/main.xml"));
    }

    #[test]
    fn commands_are_structured_without_shell_syntax() {
        let approved = approve_remote_command("cargo test --offline").unwrap();
        assert_eq!(approved.program, "cargo");
        assert_eq!(approved.args, ["test", "--offline"]);
        assert!(approve_remote_command("cargo test && env").is_err());
        assert!(approve_remote_command("cargo publish").is_err());
    }
}
