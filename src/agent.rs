//! Agent orchestration — manages tool execution and task planning
//! for TeaQL autonomous coding tasks.

use anyhow::Result;
use std::path::Path;
use std::process::Command;

/// Available tools the agent can invoke
#[derive(Debug, Clone)]
pub enum AgentTool {
    ReadFile { path: String },
    WriteFile { path: String, content: String },
    RunCommand { command: String, cwd: Option<String> },
    ListDir { path: String },
    Search { query: String, path: Option<String> },
    TeaqlEvaluate { model_path: String },
    TeaqlGenerate { model_path: String, target: String, output: String },
    TeaqlAssist { model_path: String, object: String },
}

/// Result of a tool execution
#[derive(Debug, Clone)]
pub struct ToolResult {
    pub tool: String,
    pub success: bool,
    pub output: String,
    pub token_cost: usize,
}

/// Execute a cargo-teaql command
pub fn run_cargo_teaql(args: &[&str], cwd: &Path) -> Result<ToolResult> {
    let output = Command::new("cargo")
        .args(["teaql"])
        .args(args)
        .current_dir(cwd)
        .output()?;

    let stdout = String::from_utf8_lossy(&output.stdout).to_string();
    let stderr = String::from_utf8_lossy(&output.stderr).to_string();
    let combined = if stderr.is_empty() {
        stdout.clone()
    } else {
        format!("{stdout}\n{stderr}")
    };

    Ok(ToolResult {
        tool: format!("cargo teaql {}", args.join(" ")),
        success: output.status.success(),
        output: combined,
        token_cost: estimate_output_tokens(&stdout) + estimate_output_tokens(&stderr),
    })
}

/// Execute a shell command
pub fn run_shell_command(command: &str, cwd: &Path) -> Result<ToolResult> {
    let output = Command::new("sh")
        .args(["-c", command])
        .current_dir(cwd)
        .output()?;

    let stdout = String::from_utf8_lossy(&output.stdout).to_string();
    let stderr = String::from_utf8_lossy(&output.stderr).to_string();

    Ok(ToolResult {
        tool: command.to_string(),
        success: output.status.success(),
        output: format!("{stdout}{stderr}"),
        token_cost: estimate_output_tokens(&stdout) + estimate_output_tokens(&stderr),
    })
}

/// Truncate tool output to fit within token budget
pub fn truncate_output(output: &str, max_tokens: usize) -> String {
    let max_chars = max_tokens * 4; // rough estimate
    if output.len() <= max_chars {
        output.to_string()
    } else {
        let half = max_chars / 2;
        let start: String = output.chars().take(half).collect();
        let end: String = output.chars().rev().take(half).collect::<String>().chars().rev().collect();
        format!("{}\n\n... [truncated {} chars to fit context budget] ...\n\n{}", start, output.len() - max_chars, end)
    }
}

fn estimate_output_tokens(text: &str) -> usize {
    (text.len() + 3) / 4
}
