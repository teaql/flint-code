use serde::{Deserialize, Serialize};
use std::path::PathBuf;
use crate::error::AgentError;

/// Token usage from a model response
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TokenUsage {
    pub prompt_tokens: u32,
    pub completion_tokens: u32,
    pub total_tokens: u32,
}

/// Context budget calculated during preflight
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ContextBudget {
    pub model_context: u32,
    pub prompt_limit: u32,
    pub completion_limit: u32,
    pub safety_reserve: u32,
    pub estimated_prompt: u32,
}

impl ContextBudget {
    /// Whether the estimated prompt fits within budget
    pub fn admits(&self) -> bool {
        self.estimated_prompt
            + self.completion_limit
            + self.safety_reserve
            <= self.model_context
    }

    /// How many tokens are remaining for prompt content
    pub fn remaining_prompt_tokens(&self) -> u32 {
        self.prompt_limit.saturating_sub(self.estimated_prompt)
    }
}

/// Loaded task package metadata
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TaskPackage {
    pub name: String,
    pub task_file: PathBuf,
    pub files: Vec<PathBuf>,
    pub acceptance_spec: Option<serde_json::Value>,
}

/// Result from model inference
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ModelResult {
    pub content: String,
    pub reasoning_content: Option<String>,
    pub finish_reason: String,
    pub usage: TokenUsage,
    pub elapsed_secs: f64,
    pub http_status: u16,
}

/// Result from a validation gate
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ValidationResult {
    /// Validation level (0-6)
    pub level: u8,
    pub level_name: String,
    pub passed: bool,
    pub error_count: u32,
    pub warning_count: u32,
    pub suggestion_count: u32,
    /// Actionable errors for repair diagnostic
    pub actionable_errors: Vec<String>,
    /// Full diagnostic output (may be truncated)
    pub diagnostic: String,
    pub elapsed_secs: f64,
}

/// Export consent record
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ExportConsent {
    pub domain: String,
    pub files: Vec<String>,
    pub total_bytes: u64,
    pub purpose: String,
    pub approved: bool,
    pub approved_by: Option<String>,
}

/// Events that drive the pipeline state machine.
/// TUI and headless runner produce these; the reducer consumes them.
#[derive(Debug)]
pub enum RunEvent {
    /// A task package was loaded and parsed
    TaskLoaded(TaskPackage),
    /// Task loading failed
    TaskLoadFailed(String),
    /// Preflight checks passed, budget is safe
    PreflightPassed(ContextBudget),
    /// Preflight checks failed
    PreflightFailed(String),
    /// User granted consent for external operation
    ConsentGranted(ExportConsent),
    /// User denied consent
    ConsentDenied(String),
    /// Model generation request started
    ModelStarted { attempt: u8 },
    /// Model generation completed successfully
    ModelCompleted(ModelResult),
    /// Model generation failed (HTTP error, timeout, etc.)
    ModelFailed(AgentError),
    /// Validation gate completed
    ValidationCompleted(ValidationResult),
    /// Repair cycle scheduled
    RepairScheduled { attempt: u8 },
    /// Final artifact written to disk
    FinalArtifactWritten(PathBuf),
    /// User requested cancellation
    CancelRequested,
    /// Unrecoverable error
    Failed(AgentError),
}
