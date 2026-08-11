//! Sandboxed tool execution for the agentic build loop.
//!
//! Provides tools that let the LLM inspect, compile, and fix code
//! within a restricted project directory.

use serde_json::json;
use std::collections::BTreeMap;
use std::ffi::{OsStr, OsString};
use std::path::{Component, Path, PathBuf};
use tracing::{info, warn};

use model_vllm::chat::{Function, Tool};

/// Maximum output length per tool result (chars) to avoid context overflow
const MAX_OUTPUT_LEN: usize = 4000;

/// Structured result of an agent tool invocation.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct ToolExecutionResult {
    pub output: String,
    pub success: bool,
    pub exit_code: Option<i32>,
}

impl ToolExecutionResult {
    fn success(output: impl Into<String>) -> Self {
        Self {
            output: output.into(),
            success: true,
            exit_code: None,
        }
    }

    fn failure(output: impl Into<String>) -> Self {
        Self {
            output: output.into(),
            success: false,
            exit_code: None,
        }
    }
}

/// Get the tool definitions for the agentic build loop
pub fn build_tool_definitions() -> Vec<Tool> {
    vec![
        Tool {
            r#type: "function".to_string(),
            function: Function {
                name: "run_command".to_string(),
                description: Some("Execute a build or test command. The working directory is already the project root: never cd to or prefix commands with the displayed workspace path. Generated library source (for example lib/src) must never be read, searched, or modified; use compiler diagnostics and `cargo teaql --input model/main.xml rust-assist-[action]/[entity]` instead.".to_string()),
                parameters: Some(json!({
                    "type": "object",
                    "properties": {
                        "command": {
                            "type": "string",
                            "description": "A direct, allowlisted build/test/TeaQL-assist command. Shell syntax, scripts, variables, globs, redirections, and command chaining are rejected. Supported forms include cargo check/test/run, cargo fmt --check, cargo teaql --input model/main.xml rust-assist-..., mvn compile/test, and gradle build/test."
                        }
                    },
                    "required": ["command"]
                })),
            },
        },
        Tool {
            r#type: "function".to_string(),
            function: Function {
                name: "read_file".to_string(),
                description: Some("Read an application or workspace file. Access to generated library source such as lib/src is denied.".to_string()),
                parameters: Some(json!({
                    "type": "object",
                    "properties": {
                        "path": {
                            "type": "string",
                            "description": "Path to the file, relative to the project root"
                        }
                    },
                    "required": ["path"]
                })),
            },
        },
        Tool {
            r#type: "function".to_string(),
            function: Function {
                name: "write_file".to_string(),
                description: Some("Write an application source, test, configuration, or documentation file. Generated library source, model/**, .klintcode/**, and the root AGENTS.md are read-only.".to_string()),
                parameters: Some(json!({
                    "type": "object",
                    "properties": {
                        "path": {
                            "type": "string",
                            "description": "Path to the file, relative to the project root"
                        },
                        "content": {
                            "type": "string",
                            "description": "The complete content to write to the file"
                        }
                    },
                    "required": ["path", "content"]
                })),
            },
        },
        Tool {
            r#type: "function".to_string(),
            function: Function {
                name: "list_directory".to_string(),
                description: Some("List files and directories at a path.".to_string()),
                parameters: Some(json!({
                    "type": "object",
                    "properties": {
                        "path": {
                            "type": "string",
                            "description": "Directory path relative to project root. Use '.' for root."
                        }
                    },
                    "required": ["path"]
                })),
            },
        },
    ]
}

/// Execute a tool call in a sandboxed directory.
///
/// All file operations are restricted to the sandbox directory.
/// Returns a human-readable result string for the LLM.
pub async fn execute_tool(
    tool_name: &str,
    arguments: &str,
    sandbox_dir: &Path,
) -> ToolExecutionResult {
    let args: serde_json::Value = match serde_json::from_str(arguments) {
        Ok(v) => v,
        Err(e) => return ToolExecutionResult::failure(format!("Error parsing arguments: {}", e)),
    };

    match tool_name {
        "run_command" => execute_run_command(&args, sandbox_dir).await,
        "read_file" => execute_read_file(&args, sandbox_dir),
        "write_file" => execute_write_file(&args, sandbox_dir),
        "list_directory" => execute_list_directory(&args, sandbox_dir),
        _ => ToolExecutionResult::failure(format!("Unknown tool: {}", tool_name)),
    }
}

async fn execute_run_command(args: &serde_json::Value, sandbox_dir: &Path) -> ToolExecutionResult {
    let command = args["command"].as_str().unwrap_or("");
    if command.is_empty() {
        return ToolExecutionResult::failure("Error: empty command");
    }

    let approved = match ApprovedCommand::parse(command, sandbox_dir) {
        Ok(command) => command,
        Err(error) => return ToolExecutionResult::failure(format!("Error: {error}")),
    };

    info!(command, dir = %sandbox_dir.display(), "Agent executing command");

    let mut process = approved.process(sandbox_dir);

    let output = match crate::process_output::run_bounded_output(
        &mut process,
        std::time::Duration::from_secs(60),
        64 * 1024,
    )
    .await
    {
        Ok(output) => output,
        Err(error) => {
            return ToolExecutionResult::failure(format!("Error: {error}"));
        }
    };

    let stdout = String::from_utf8_lossy(&output.stdout);
    let stderr = String::from_utf8_lossy(&output.stderr);
    let status = output.status.code().unwrap_or(-1);

    let stdout_trunc = truncate_output(&stdout, MAX_OUTPUT_LEN);
    let stderr_trunc = truncate_output(&stderr, MAX_OUTPUT_LEN);

    ToolExecutionResult {
        output: format!(
            "Exit code: {}\nStdout:\n{}\nStderr:\n{}",
            status, stdout_trunc, stderr_trunc
        ),
        success: output.status.success(),
        exit_code: Some(status),
    }
}

/// A deliberately small command surface for the build agent.
///
/// Free-form shell execution cannot reliably distinguish a compiler invocation
/// from a path-alias, variable, glob, or helper-script bypass. Keep the public
/// tool argument as a string for model compatibility, but parse it into an
/// executable plus argv and launch it without a shell.
#[derive(Debug, Clone, PartialEq, Eq)]
struct ApprovedCommand {
    program: String,
    args: Vec<String>,
}

impl ApprovedCommand {
    fn parse(command: &str, sandbox_dir: &Path) -> Result<Self, String> {
        let words = strict_command_words(command)?;
        let Some((program, args)) = words.split_first() else {
            return Err("empty command".to_string());
        };

        match program.as_str() {
            "cargo" => validate_cargo_args(args, sandbox_dir)?,
            "mvn" => validate_maven_args(args, sandbox_dir)?,
            "gradle" => validate_gradle_args(args)?,
            _ => {
                return Err(format!(
                    "command `{program}` is not allowed; use cargo check/test/run, cargo fmt --check, cargo teaql assist, mvn compile/test, or gradle build/test"
                ));
            }
        }

        Ok(Self {
            program: program.clone(),
            args: args.to_vec(),
        })
    }

    fn process(&self, sandbox_dir: &Path) -> tokio::process::Command {
        let mut command = tokio::process::Command::new(&self.program);
        command
            .args(&self.args)
            .current_dir(sandbox_dir)
            .kill_on_drop(true)
            .env_clear();
        for (name, value) in minimal_command_environment(sandbox_dir) {
            command.env(name, value);
        }
        command
    }
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

fn validate_cargo_args(args: &[String], sandbox_dir: &Path) -> Result<(), String> {
    let Some(subcommand) = args.first().map(String::as_str) else {
        return Err("cargo requires an allowlisted subcommand".to_string());
    };
    match subcommand {
        "check" => validate_cargo_build_flags(&args[1..], sandbox_dir),
        "test" => validate_cargo_test_args(&args[1..], sandbox_dir),
        "run" => validate_cargo_run_args(&args[1..], sandbox_dir),
        "fmt" => match &args[1..] {
            [flag] if flag == "--check" => Ok(()),
            [separator, flag] if separator == "--" && flag == "--check" => Ok(()),
            _ => Err(
                "only `cargo fmt --check` is allowed because formatting generated libraries would modify protected source"
                    .to_string(),
            ),
        },
        "teaql" => validate_teaql_assist_args(&args[1..], sandbox_dir),
        _ => Err(format!(
            "cargo subcommand `{subcommand}` is not allowed; use check, test, run, fmt --check, or a TeaQL assist command"
        )),
    }
}

fn validate_cargo_test_args(args: &[String], sandbox_dir: &Path) -> Result<(), String> {
    let (cargo_args, harness_args) = split_direct_arguments(args);
    validate_cargo_build_flags(cargo_args, sandbox_dir)?;
    validate_direct_program_arguments(harness_args)
}

fn validate_cargo_run_args(args: &[String], sandbox_dir: &Path) -> Result<(), String> {
    let (cargo_args, program_args) = split_direct_arguments(args);
    validate_cargo_build_flags(cargo_args, sandbox_dir)?;
    validate_direct_program_arguments(program_args)
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

fn validate_cargo_build_flags(args: &[String], sandbox_dir: &Path) -> Result<(), String> {
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
            let Some(value) = args.get(index + 1) else {
                return Err(format!("cargo flag `{argument}` requires a value"));
            };
            validate_plain_argument(value, sandbox_dir)?;
            index += 2;
            continue;
        }
        return Err(format!(
            "cargo argument `{argument}` is not in the safe build/test allowlist"
        ));
    }
    Ok(())
}

fn validate_teaql_assist_args(args: &[String], sandbox_dir: &Path) -> Result<(), String> {
    if args.len() != 3 || args[0] != "--input" {
        return Err(
            "TeaQL commands must be `cargo teaql --input <model> rust-assist-<action>/<entity>`"
                .to_string(),
        );
    }
    let input = checked_workspace_path(sandbox_dir, &args[1], PathIntent::Read)?;
    if is_generated_library_source(&input, sandbox_dir) {
        return Err("TeaQL input cannot point into generated library source".to_string());
    }
    let action = &args[2];
    if !action.starts_with("rust-assist-")
        || !action.contains('/')
        || !action.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '/')
        })
    {
        return Err("only rust-assist-<action>/<entity> TeaQL operations are allowed".to_string());
    }
    Ok(())
}

fn validate_maven_args(args: &[String], sandbox_dir: &Path) -> Result<(), String> {
    let Some(goal) = args.first().map(String::as_str) else {
        return Err("mvn requires compile or test".to_string());
    };
    if !matches!(goal, "compile" | "test") {
        return Err(format!("Maven goal `{goal}` is not allowed"));
    }
    match &args[1..] {
        [] => Ok(()),
        [flag, pom] if flag == "-f" || flag == "--file" => {
            let path = checked_workspace_path(sandbox_dir, pom, PathIntent::Read)?;
            if is_generated_library_source(&path, sandbox_dir) {
                Err("Maven project file cannot point into generated source".to_string())
            } else if path.file_name() != Some(OsStr::new("pom.xml")) {
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

fn validate_plain_argument(argument: &str, sandbox_dir: &Path) -> Result<(), String> {
    if argument.is_empty()
        || !argument.chars().all(|character| {
            character.is_ascii_alphanumeric() || matches!(character, '-' | '_' | '.' | ':' | ',')
        })
    {
        return Err(format!("unsafe command argument `{argument}`"));
    }
    // A package/test/bin name normally is not a path. If it happens to resolve
    // to an existing workspace entry, still reject aliases into protected code.
    let candidate = resolve_path(sandbox_dir, argument);
    if candidate.exists() {
        let resolved = checked_workspace_path(sandbox_dir, argument, PathIntent::Read)?;
        if is_generated_library_source(&resolved, sandbox_dir) {
            return Err("command argument resolves into generated source".to_string());
        }
    }
    Ok(())
}

/// Construct the complete child environment after `env_clear()`.
///
/// API keys, cloud credentials, proxy credentials, SSH agents, dynamic loader
/// hooks, compiler wrappers, and arbitrary caller variables are intentionally
/// not forwarded. Only toolchain discovery and deterministic terminal settings
/// are retained.
fn minimal_command_environment(sandbox_dir: &Path) -> BTreeMap<OsString, OsString> {
    const SAFE_PARENT_VARS: &[&str] = &[
        "PATH",
        "CARGO_HOME",
        "RUSTUP_HOME",
        "JAVA_HOME",
        "M2_HOME",
        "GRADLE_HOME",
        "TMPDIR",
        "LANG",
        "LC_ALL",
    ];

    let parent = std::env::vars_os().collect::<BTreeMap<_, _>>();
    let mut environment = SAFE_PARENT_VARS
        .iter()
        .filter_map(|name| {
            parent
                .get(OsStr::new(name))
                .cloned()
                .and_then(|value| {
                    if *name == "PATH" {
                        sanitized_search_path(&value, sandbox_dir)
                    } else {
                        Some(value)
                    }
                })
                .map(|value| (OsString::from(name), value))
        })
        .collect::<BTreeMap<_, _>>();
    if let Some(home) = parent.get(OsStr::new("HOME")).map(PathBuf::from) {
        if !environment.contains_key(OsStr::new("CARGO_HOME")) {
            let cargo_home = home.join(".cargo");
            if cargo_home.is_dir() {
                environment.insert(OsString::from("CARGO_HOME"), cargo_home.into_os_string());
            }
        }
        if !environment.contains_key(OsStr::new("RUSTUP_HOME")) {
            let rustup_home = home.join(".rustup");
            if rustup_home.is_dir() {
                environment.insert(OsString::from("RUSTUP_HOME"), rustup_home.into_os_string());
            }
        }
    }
    environment.insert(
        OsString::from("HOME"),
        sandbox_dir.as_os_str().to_os_string(),
    );
    environment.insert(OsString::from("PAGER"), OsString::from("cat"));
    environment.insert(OsString::from("CARGO_TERM_COLOR"), OsString::from("never"));
    environment.insert(OsString::from("NO_COLOR"), OsString::from("1"));
    environment
}

fn sanitized_search_path(value: &OsStr, sandbox_dir: &Path) -> Option<OsString> {
    let root = canonical_sandbox(sandbox_dir).ok();
    let paths = std::env::split_paths(value)
        .filter(|path| path.is_absolute())
        .filter(|path| {
            let resolved = path.canonicalize().unwrap_or_else(|_| path.to_path_buf());
            root.as_ref().is_none_or(|root| !resolved.starts_with(root))
        })
        .collect::<Vec<_>>();
    std::env::join_paths(paths).ok()
}

fn execute_read_file(args: &serde_json::Value, sandbox_dir: &Path) -> ToolExecutionResult {
    let path = args["path"].as_str().unwrap_or("");
    if path.is_empty() {
        return ToolExecutionResult::failure("Error: empty path");
    }

    let full_path = match checked_workspace_path(sandbox_dir, path, PathIntent::Read) {
        Ok(path) => path,
        Err(error) => return ToolExecutionResult::failure(error),
    };
    if is_generated_library_source(&full_path, sandbox_dir) {
        return ToolExecutionResult::failure(
            "Error: generated library source is read-protected; rely on compiler output and cargo teaql assist",
        );
    }

    match std::fs::read_to_string(&full_path) {
        Ok(content) => ToolExecutionResult::success(truncate_output(&content, MAX_OUTPUT_LEN * 2)),
        Err(e) => ToolExecutionResult::failure(format!("Error reading file '{}': {}", path, e)),
    }
}

fn execute_write_file(args: &serde_json::Value, sandbox_dir: &Path) -> ToolExecutionResult {
    let path = args["path"].as_str().unwrap_or("");
    let content = args["content"].as_str().unwrap_or("");

    if path.is_empty() {
        return ToolExecutionResult::failure("Error: empty path");
    }

    let full_path = match checked_workspace_path(sandbox_dir, path, PathIntent::Write) {
        Ok(path) => path,
        Err(error) => return ToolExecutionResult::failure(error),
    };
    if is_generated_library_source(&full_path, sandbox_dir) {
        return ToolExecutionResult::failure(
            "Error: generated library source cannot be modified; edit application code or the KSML model instead",
        );
    }
    if is_read_only_workspace_evidence(&full_path, sandbox_dir) {
        return ToolExecutionResult::failure(
            "Error: validated model inputs, cached validation evidence, and the root AGENTS.md are read-only",
        );
    }

    // Create parent directories if needed
    if let Some(parent) = full_path.parent() {
        if let Err(e) = std::fs::create_dir_all(parent) {
            return ToolExecutionResult::failure(format!("Error creating directories: {}", e));
        }
    }

    match std::fs::write(&full_path, content) {
        Ok(_) => {
            info!(path, bytes = content.len(), "Agent wrote file");
            ToolExecutionResult::success(format!(
                "Successfully wrote {} bytes to {}",
                content.len(),
                path
            ))
        }
        Err(e) => ToolExecutionResult::failure(format!("Error writing file '{}': {}", path, e)),
    }
}

fn execute_list_directory(args: &serde_json::Value, sandbox_dir: &Path) -> ToolExecutionResult {
    let path = args["path"].as_str().unwrap_or(".");
    let full_path = match checked_workspace_path(sandbox_dir, path, PathIntent::Read) {
        Ok(path) => path,
        Err(error) => return ToolExecutionResult::failure(error),
    };
    if is_generated_library_source(&full_path, sandbox_dir) {
        return ToolExecutionResult::failure(
            "Error: generated library source directories cannot be listed; use compiler output and cargo teaql assist",
        );
    }

    match std::fs::read_dir(&full_path) {
        Ok(entries) => {
            let mut listing = Vec::new();
            for entry in entries.flatten() {
                let entry_path = entry.path();
                // Do not disclose a protected source directory through an
                // otherwise harmless parent listing or a symlink alias.
                if let Ok(resolved) = resolve_existing_path(sandbox_dir, &entry_path)
                    && is_generated_library_source(&resolved, sandbox_dir)
                {
                    continue;
                }
                let name = entry.file_name().to_string_lossy().to_string();
                let kind = entry
                    .file_type()
                    .map(|t| if t.is_dir() { "dir" } else { "file" })
                    .unwrap_or("?");
                listing.push(format!("  {} ({})", name, kind));
            }
            listing.sort();
            if listing.is_empty() {
                ToolExecutionResult::success("Directory is empty")
            } else {
                ToolExecutionResult::success(listing.join("\n"))
            }
        }
        Err(e) => {
            ToolExecutionResult::failure(format!("Error listing directory '{}': {}", path, e))
        }
    }
}

/// Resolve a relative path against the sandbox directory
fn resolve_path(sandbox: &Path, relative: &str) -> PathBuf {
    let path = Path::new(relative);
    if path.is_absolute() {
        path.to_path_buf()
    } else {
        sandbox.join(path)
    }
}

fn workspace_path_error(requested: &str, sandbox: &Path) -> String {
    format!(
        "Error: path `{requested}` is outside the project directory. Your tool working directory is already `{}`; use paths relative to that root (use `.` to list it) and do not repeat the workspace path.",
        sandbox.display()
    )
}

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
enum PathIntent {
    Read,
    Write,
}

/// Resolve a requested path through every existing symlink component and prove
/// that its effective target remains under the canonical sandbox root.
///
/// For a new write target, canonicalize the closest existing ancestor and then
/// append the still-missing suffix. This blocks aliases such as
/// `editable-link/new.rs` when `editable-link` points at protected source.
fn checked_workspace_path(
    sandbox: &Path,
    requested: &str,
    intent: PathIntent,
) -> Result<PathBuf, String> {
    if requested.is_empty() || requested.as_bytes().contains(&0) {
        return Err("Error: empty or invalid path".to_string());
    }
    let raw = Path::new(requested);
    if raw.components().any(|component| {
        matches!(
            component,
            Component::ParentDir | Component::Prefix(_) | Component::RootDir
        )
    }) {
        return Err(workspace_path_error(requested, sandbox));
    }

    let candidate = resolve_path(sandbox, requested);
    let resolved = if candidate.exists() || intent == PathIntent::Read {
        resolve_existing_path(sandbox, &candidate)
            .map_err(|_| workspace_path_error(requested, sandbox))?
    } else {
        resolve_new_path(sandbox, &candidate)
            .map_err(|_| workspace_path_error(requested, sandbox))?
    };
    Ok(resolved)
}

fn canonical_sandbox(sandbox: &Path) -> std::io::Result<PathBuf> {
    sandbox.canonicalize()
}

fn resolve_existing_path(sandbox: &Path, candidate: &Path) -> std::io::Result<PathBuf> {
    let root = canonical_sandbox(sandbox)?;
    let resolved = candidate.canonicalize()?;
    if resolved.starts_with(&root) {
        Ok(resolved)
    } else {
        warn!(path = %candidate.display(), "Resolved path escapes sandbox");
        Err(std::io::Error::new(
            std::io::ErrorKind::PermissionDenied,
            "resolved path escapes sandbox",
        ))
    }
}

fn resolve_new_path(sandbox: &Path, candidate: &Path) -> std::io::Result<PathBuf> {
    let root = canonical_sandbox(sandbox)?;
    let mut existing = candidate;
    let mut suffix = Vec::new();
    while !existing.exists() {
        let Some(name) = existing.file_name() else {
            return Err(std::io::Error::new(
                std::io::ErrorKind::PermissionDenied,
                "no existing workspace ancestor",
            ));
        };
        suffix.push(name.to_os_string());
        existing = existing.parent().ok_or_else(|| {
            std::io::Error::new(
                std::io::ErrorKind::PermissionDenied,
                "no existing workspace ancestor",
            )
        })?;
    }

    let mut resolved = existing.canonicalize()?;
    if !resolved.starts_with(&root) {
        return Err(std::io::Error::new(
            std::io::ErrorKind::PermissionDenied,
            "resolved path escapes sandbox",
        ));
    }
    for component in suffix.into_iter().rev() {
        resolved.push(component);
    }
    Ok(resolved)
}

fn is_generated_library_source(path: &Path, sandbox: &Path) -> bool {
    let Ok(root) = canonical_sandbox(sandbox) else {
        return false;
    };
    let resolved = if path.exists() {
        path.canonicalize().unwrap_or_else(|_| path.to_path_buf())
    } else {
        path.to_path_buf()
    };
    let Ok(relative) = resolved.strip_prefix(root) else {
        return false;
    };
    let normalized = relative.to_string_lossy().replace('\\', "/").to_lowercase();
    normalized == "lib/src"
        || normalized.starts_with("lib/src/")
        || normalized == "rust-lib-core/lib/src"
        || normalized.ends_with("/rust-lib-core/lib/src")
        || normalized.contains("/rust-lib-core/lib/src/")
        || normalized.starts_with("rust-lib-core/lib/src/")
        || normalized == "java-lib-core"
        || normalized.contains("java-lib-core/")
        || normalized.starts_with("java-lib-core/")
        || normalized == "java-web-spring-boot"
        || normalized.contains("java-web-spring-boot/")
        || normalized.starts_with("java-web-spring-boot/")
}

fn is_read_only_workspace_evidence(path: &Path, sandbox: &Path) -> bool {
    let Ok(root) = canonical_sandbox(sandbox) else {
        return false;
    };
    let resolved = if path.exists() {
        path.canonicalize().unwrap_or_else(|_| path.to_path_buf())
    } else {
        path.to_path_buf()
    };
    let Ok(relative) = resolved.strip_prefix(root) else {
        return false;
    };
    let normalized = relative.to_string_lossy().replace('\\', "/");
    normalized.eq_ignore_ascii_case("AGENTS.md")
        || normalized.eq_ignore_ascii_case("model")
        || normalized
            .get(.."model/".len())
            .is_some_and(|prefix| prefix.eq_ignore_ascii_case("model/"))
        || normalized.eq_ignore_ascii_case(".klintcode")
        || normalized
            .get(..".klintcode/".len())
            .is_some_and(|prefix| prefix.eq_ignore_ascii_case(".klintcode/"))
}

/// Truncate output to avoid blowing the context window
fn truncate_output(text: &str, max_len: usize) -> String {
    if text.len() > max_len {
        let mut boundary = max_len;
        while boundary > 0 && !text.is_char_boundary(boundary) {
            boundary -= 1;
        }
        format!(
            "{}...\n[truncated, {} total bytes]",
            &text[..boundary],
            text.len()
        )
    } else {
        text.to_string()
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn truncation_respects_utf8_boundaries() {
        let text = "你".repeat(2_000);
        let truncated = truncate_output(&text, MAX_OUTPUT_LEN);
        assert!(truncated.contains("[truncated"));
    }

    #[tokio::test]
    async fn shell_syntax_is_rejected_instead_of_executed() {
        let result = execute_tool(
            "run_command",
            r#"{"command":"false | true"}"#,
            Path::new("."),
        )
        .await;

        assert!(!result.success);
        assert_eq!(result.exit_code, None);
        assert!(result.output.contains("shell syntax"));
    }

    #[tokio::test]
    async fn generated_library_source_is_protected_from_all_tools() {
        let project = tempfile::tempdir().expect("temporary project");
        std::fs::create_dir_all(project.path().join("lib/src")).unwrap();
        std::fs::write(project.path().join("lib/src/entity.rs"), "generated").unwrap();

        let read = execute_tool(
            "read_file",
            r#"{"path":"lib/src/entity.rs"}"#,
            project.path(),
        )
        .await;
        let write = execute_tool(
            "write_file",
            r#"{"path":"lib/src/entity.rs","content":"changed"}"#,
            project.path(),
        )
        .await;
        let command = execute_tool(
            "run_command",
            r#"{"command":"sed -n '1,20p' lib/src/entity.rs"}"#,
            project.path(),
        )
        .await;
        let list = execute_tool("list_directory", r#"{"path":"lib/src"}"#, project.path()).await;

        assert!(!read.success);
        assert!(!write.success);
        assert!(!command.success);
        assert!(!list.success);
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn symlink_aliases_to_generated_source_are_protected() {
        use std::os::unix::fs::symlink;

        let project = tempfile::tempdir().expect("temporary project");
        std::fs::create_dir_all(project.path().join("lib/src")).unwrap();
        std::fs::write(project.path().join("lib/src/entity.rs"), "generated").unwrap();
        symlink("lib/src/entity.rs", project.path().join("entity-alias.rs")).unwrap();
        symlink("lib/src", project.path().join("source-alias")).unwrap();

        let read = execute_tool("read_file", r#"{"path":"entity-alias.rs"}"#, project.path()).await;
        let overwrite = execute_tool(
            "write_file",
            r#"{"path":"entity-alias.rs","content":"changed"}"#,
            project.path(),
        )
        .await;
        let create = execute_tool(
            "write_file",
            r#"{"path":"source-alias/new.rs","content":"changed"}"#,
            project.path(),
        )
        .await;
        let list_alias = execute_tool(
            "list_directory",
            r#"{"path":"source-alias"}"#,
            project.path(),
        )
        .await;
        let list_root = execute_tool("list_directory", r#"{"path":"."}"#, project.path()).await;
        let list_library =
            execute_tool("list_directory", r#"{"path":"lib"}"#, project.path()).await;

        assert!(!read.success);
        assert!(!overwrite.success);
        assert!(!create.success);
        assert!(!list_alias.success);
        assert!(list_root.success);
        assert!(!list_root.output.contains("entity-alias.rs"));
        assert!(!list_root.output.contains("source-alias"));
        assert!(list_library.success);
        assert!(!list_library.output.contains("src (dir)"));
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn symlink_alias_outside_workspace_is_rejected() {
        use std::os::unix::fs::symlink;

        let project = tempfile::tempdir().expect("temporary project");
        let outside = tempfile::tempdir().expect("outside directory");
        std::fs::write(outside.path().join("outside.txt"), "private").unwrap();
        symlink(outside.path(), project.path().join("outside-alias")).unwrap();

        let read = execute_tool(
            "read_file",
            r#"{"path":"outside-alias/outside.txt"}"#,
            project.path(),
        )
        .await;
        let write = execute_tool(
            "write_file",
            r#"{"path":"outside-alias/new.txt","content":"no"}"#,
            project.path(),
        )
        .await;

        assert!(!read.success);
        assert!(!write.success);
    }

    #[test]
    fn command_allowlist_covers_build_test_and_assist_without_shell() {
        let project = tempfile::tempdir().expect("temporary project");
        std::fs::create_dir_all(project.path().join("model")).unwrap();
        std::fs::write(project.path().join("model/main.xml"), "<root/>").unwrap();

        assert!(ApprovedCommand::parse("cargo check", project.path()).is_ok());
        assert!(ApprovedCommand::parse("cargo test --workspace", project.path()).is_ok());
        assert!(ApprovedCommand::parse("cargo test -- --nocapture", project.path()).is_ok());
        assert!(ApprovedCommand::parse("cargo run -- --init", project.path()).is_ok());
        assert!(ApprovedCommand::parse("cargo fmt --check", project.path()).is_ok());
        assert!(ApprovedCommand::parse("cargo fmt -- --check", project.path()).is_ok());
        assert!(
            ApprovedCommand::parse(
                "cargo teaql --input model/main.xml rust-assist-query/school",
                project.path(),
            )
            .is_ok()
        );
        assert!(ApprovedCommand::parse("mvn compile -f pom.xml", project.path()).is_err());
        std::fs::write(project.path().join("pom.xml"), "<project/>").unwrap();
        assert!(ApprovedCommand::parse("mvn compile -f pom.xml", project.path()).is_ok());
        assert!(ApprovedCommand::parse("gradle test", project.path()).is_ok());
    }

    #[test]
    fn command_allowlist_rejects_aliases_variables_globs_and_scripts() {
        let project = tempfile::tempdir().expect("temporary project");
        for command in [
            "cargo check; pwd",
            "cargo test | tee result.txt",
            "cargo check $EXTRA",
            "cargo check *",
            "bash build.sh",
            "./build.sh",
            "cargo fmt",
            "cargo fmt --all",
            "cargo check --manifest-path lib/Cargo.toml",
        ] {
            assert!(
                ApprovedCommand::parse(command, project.path()).is_err(),
                "unexpectedly allowed: {command}"
            );
        }
    }

    #[test]
    fn child_environment_does_not_forward_secrets_or_execution_hooks() {
        let project = tempfile::tempdir().expect("temporary project");
        let environment = minimal_command_environment(project.path());
        for forbidden in [
            "MIMO_API_KEY",
            "OPENAI_API_KEY",
            "AWS_SECRET_ACCESS_KEY",
            "SSH_AUTH_SOCK",
            "HTTP_PROXY",
            "HTTPS_PROXY",
            "RUSTC_WRAPPER",
            "RUSTFLAGS",
            "LD_PRELOAD",
            "DYLD_INSERT_LIBRARIES",
        ] {
            assert!(!environment.contains_key(OsStr::new(forbidden)));
        }
        assert_eq!(
            environment.get(OsStr::new("HOME")),
            Some(&project.path().as_os_str().to_os_string())
        );
        assert_eq!(
            environment.get(OsStr::new("PAGER")),
            Some(&OsString::from("cat"))
        );
    }

    #[cfg(unix)]
    #[test]
    fn child_search_path_excludes_relative_and_workspace_entries() {
        let project = tempfile::tempdir().expect("temporary project");
        let source = std::env::join_paths([
            PathBuf::from("."),
            project.path().to_path_buf(),
            PathBuf::from("/usr/bin"),
        ])
        .unwrap();
        let sanitized = sanitized_search_path(&source, project.path()).unwrap();
        let paths = std::env::split_paths(&sanitized).collect::<Vec<_>>();

        assert_eq!(paths, vec![PathBuf::from("/usr/bin")]);
    }

    #[cfg(unix)]
    #[tokio::test]
    async fn model_evidence_and_root_agents_are_read_only_through_aliases() {
        use std::os::unix::fs::symlink;

        let project = tempfile::tempdir().expect("temporary project");
        std::fs::create_dir_all(project.path().join("model")).unwrap();
        std::fs::create_dir_all(project.path().join(".klintcode/assist")).unwrap();
        std::fs::write(project.path().join("model/main.xml"), "<root/>").unwrap();
        std::fs::write(project.path().join(".klintcode/assist/query.md"), "assist").unwrap();
        std::fs::write(project.path().join("AGENTS.md"), "rules").unwrap();
        symlink("model", project.path().join("model-alias")).unwrap();
        symlink("AGENTS.md", project.path().join("rules-alias.md")).unwrap();

        for arguments in [
            r#"{"path":"model/main.xml","content":"changed"}"#,
            r#"{"path":"model/new.xml","content":"changed"}"#,
            r#"{"path":".klintcode/assist/query.md","content":"changed"}"#,
            r#"{"path":"AGENTS.md","content":"changed"}"#,
            r#"{"path":"model-alias/new.xml","content":"changed"}"#,
            r#"{"path":"rules-alias.md","content":"changed"}"#,
        ] {
            let result = execute_tool("write_file", arguments, project.path()).await;
            assert!(!result.success, "unexpected write success: {arguments}");
        }

        let read_model =
            execute_tool("read_file", r#"{"path":"model/main.xml"}"#, project.path()).await;
        let list_model =
            execute_tool("list_directory", r#"{"path":"model"}"#, project.path()).await;
        let list_evidence = execute_tool(
            "list_directory",
            r#"{"path":".klintcode/assist"}"#,
            project.path(),
        )
        .await;

        assert!(read_model.success);
        assert!(list_model.success);
        assert!(list_model.output.contains("main.xml"));
        assert!(list_evidence.success);
        assert!(list_evidence.output.contains("query.md"));
    }

    #[tokio::test]
    async fn allowlisted_cargo_check_executes_directly() {
        let project = tempfile::tempdir().expect("temporary project");
        std::fs::create_dir_all(project.path().join("src")).unwrap();
        std::fs::write(
            project.path().join("Cargo.toml"),
            "[package]\nname = \"tool-smoke\"\nversion = \"0.1.0\"\nedition = \"2024\"\n\n[workspace]\n",
        )
        .unwrap();
        std::fs::write(project.path().join("src/main.rs"), "fn main() {}\n").unwrap();

        let result = execute_tool(
            "run_command",
            r#"{"command":"cargo check"}"#,
            project.path(),
        )
        .await;

        assert!(result.success, "{}", result.output);
        assert_eq!(result.exit_code, Some(0));
    }

    #[tokio::test]
    async fn application_source_remains_editable() {
        let project = tempfile::tempdir().expect("temporary project");
        std::fs::create_dir_all(project.path().join("src")).unwrap();

        let result = execute_tool(
            "write_file",
            r#"{"path":"src/main.rs","content":"fn main() {}"}"#,
            project.path(),
        )
        .await;

        assert!(result.success);
    }
}
