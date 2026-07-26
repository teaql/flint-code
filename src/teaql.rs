//! TeaQL-specific operations — model evaluation, code generation, assist.

use anyhow::Result;
use std::path::{Path, PathBuf};
use crate::agent::{run_cargo_teaql, ToolResult};

pub const REQUIRED_CARGO_TEAQL_VERSION: &str = "2.0.8";

/// Check if cargo-teaql is installed with the correct version
pub fn check_cargo_teaql() -> Result<ToolResult> {
    let output = std::process::Command::new("cargo")
        .args(["teaql", "--version"])
        .output()?;

    let stdout = String::from_utf8_lossy(&output.stdout).to_string();
    let success = output.status.success() && stdout.contains(REQUIRED_CARGO_TEAQL_VERSION);

    Ok(ToolResult {
        tool: "cargo teaql --version".to_string(),
        success,
        output: if success {
            format!("cargo-teaql {} installed ✓", REQUIRED_CARGO_TEAQL_VERSION)
        } else {
            format!("BLOCKER: Expected cargo-teaql {}, got: {}", REQUIRED_CARGO_TEAQL_VERSION, stdout.trim())
        },
        token_cost: 20,
    })
}

/// Evaluate a KSML model
pub fn evaluate_model(model_path: &Path, cwd: &Path) -> Result<ToolResult> {
    let model_str = model_path.to_string_lossy();
    run_cargo_teaql(&["--input", &model_str, "evaluate"], cwd)
}

/// Generate Rust library from model
pub fn generate_rust_lib(model_path: &Path, output_dir: &Path, cwd: &Path) -> Result<ToolResult> {
    let model_str = model_path.to_string_lossy();
    let output_str = output_dir.to_string_lossy();
    run_cargo_teaql(
        &["--input", &model_str, "generate", "--target", "rust-lib-core", "--output", &output_str],
        cwd,
    )
}

/// Generate Rust console app from model
pub fn generate_rust_app(model_path: &Path, output_dir: &Path, cwd: &Path) -> Result<ToolResult> {
    let model_str = model_path.to_string_lossy();
    let output_str = output_dir.to_string_lossy();
    run_cargo_teaql(
        &["--input", &model_str, "generate", "--target", "rust-app-console", "--output", &output_str],
        cwd,
    )
}

/// Run object-specific assist
pub fn assist_object(model_path: &Path, object_name: &str, cwd: &Path) -> Result<ToolResult> {
    let model_str = model_path.to_string_lossy();
    let assist_cmd = format!("rust-assist-{}", object_name);
    run_cargo_teaql(&["--input", &model_str, &assist_cmd], cwd)
}

/// Get the playground workspace layout
pub fn playground_layout(project_root: &Path) -> PlaygroundLayout {
    PlaygroundLayout {
        root: project_root.to_path_buf(),
        models_dir: project_root.join("app-playground/models"),
        model_file: project_root.join("app-playground/models/model.xml"),
        rust_lib: project_root.join("app-playground/rust-lib-core"),
        rust_app: project_root.join("app-playground/rust-app-console"),
    }
}

#[derive(Debug)]
pub struct PlaygroundLayout {
    pub root: PathBuf,
    pub models_dir: PathBuf,
    pub model_file: PathBuf,
    pub rust_lib: PathBuf,
    pub rust_app: PathBuf,
}

impl PlaygroundLayout {
    /// Ensure all playground directories exist
    pub fn ensure_dirs(&self) -> Result<()> {
        std::fs::create_dir_all(&self.models_dir)?;
        Ok(())
    }
}
