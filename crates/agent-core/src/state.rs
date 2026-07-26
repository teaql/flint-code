use serde::{Deserialize, Serialize};
use chrono::{DateTime, Utc};

/// Pipeline state — must be an explicit enum, never inferred from log text.
/// State transitions are handled exclusively by the reducer.
#[derive(Debug, Clone, Serialize, Deserialize, PartialEq)]
pub enum PipelineState {
    /// No task loaded
    Idle,
    /// Loading and validating a task package
    LoadingTask,
    /// Checking budget, permissions, workspace access
    Preflight,
    /// Waiting for user consent (e.g., external data transfer)
    AwaitingConsent { action: String },
    /// Sending generation request to model
    Generating { attempt: u8 },
    /// Running local validation (L0-L2)
    LocalValidation { attempt: u8 },
    /// Running domain validation (L3, e.g. TeaQL evaluate)
    DomainValidation { attempt: u8 },
    /// Running build validation (L4-L6, cargo check/test)
    BuildValidation { attempt: u8 },
    /// Creating a fresh repair request
    Repairing { attempt: u8 },
    /// All gates passed, writing final artifact
    Finalizing,
    /// Terminal: success
    Completed,
    /// Terminal: failure
    Failed { error: String },
    /// Terminal: user cancelled
    Cancelled,
    /// Terminal: policy prevented the action (headless mode)
    SkippedByPolicy { reason: String },
}

impl PipelineState {
    /// Whether this is a terminal state
    pub fn is_terminal(&self) -> bool {
        matches!(
            self,
            PipelineState::Completed
            | PipelineState::Failed { .. }
            | PipelineState::Cancelled
            | PipelineState::SkippedByPolicy { .. }
        )
    }

    /// Human-readable label for TUI display
    pub fn label(&self) -> &'static str {
        match self {
            PipelineState::Idle => "Idle",
            PipelineState::LoadingTask => "Loading Task",
            PipelineState::Preflight => "Preflight",
            PipelineState::AwaitingConsent { .. } => "Awaiting Consent",
            PipelineState::Generating { .. } => "Generating",
            PipelineState::LocalValidation { .. } => "Local Validation",
            PipelineState::DomainValidation { .. } => "Domain Validation",
            PipelineState::BuildValidation { .. } => "Build Validation",
            PipelineState::Repairing { .. } => "Repairing",
            PipelineState::Finalizing => "Finalizing",
            PipelineState::Completed => "Completed",
            PipelineState::Failed { .. } => "Failed",
            PipelineState::Cancelled => "Cancelled",
            PipelineState::SkippedByPolicy { .. } => "Skipped",
        }
    }

    /// Whether the state machine is currently active (not idle or terminal)
    pub fn is_active(&self) -> bool {
        !matches!(self, PipelineState::Idle) && !self.is_terminal()
    }
}

impl std::fmt::Display for PipelineState {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{}", self.label())
    }
}

/// Timing information for each pipeline stage
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct StageTiming {
    pub stage: String,
    pub started: DateTime<Utc>,
    pub completed: Option<DateTime<Utc>>,
}

impl StageTiming {
    pub fn new(stage: impl Into<String>) -> Self {
        Self {
            stage: stage.into(),
            started: Utc::now(),
            completed: None,
        }
    }

    pub fn complete(&mut self) {
        self.completed = Some(Utc::now());
    }

    pub fn elapsed_secs(&self) -> f64 {
        let end = self.completed.unwrap_or_else(Utc::now);
        (end - self.started).num_milliseconds() as f64 / 1000.0
    }
}

/// Complete run state, owned by the reducer
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct RunState {
    pub run_id: String,
    pub state: PipelineState,
    pub task_name: Option<String>,
    pub timings: Vec<StageTiming>,
    pub current_attempt: u8,
    pub max_repairs: u8,
    pub created_at: DateTime<Utc>,
}

impl RunState {
    pub fn new(run_id: String, max_repairs: u8) -> Self {
        Self {
            run_id,
            state: PipelineState::Idle,
            task_name: None,
            timings: Vec::new(),
            current_attempt: 0,
            max_repairs,
            created_at: Utc::now(),
        }
    }

    pub fn start_stage(&mut self, stage: impl Into<String>) {
        // Complete the previous stage if any
        if let Some(last) = self.timings.last_mut() {
            if last.completed.is_none() {
                last.complete();
            }
        }
        self.timings.push(StageTiming::new(stage));
    }

    pub fn complete_current_stage(&mut self) {
        if let Some(last) = self.timings.last_mut() {
            last.complete();
        }
    }
}
