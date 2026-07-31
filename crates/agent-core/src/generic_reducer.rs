use crate::error::AgentError;
use crate::generic_event::GenericRunEvent;
use crate::generic_state::{GenericPipelineState, GenericPlanStepStatus, GenericRunState};
use tracing::{error, info, warn};

#[derive(Debug, Clone)]
pub enum GenericSideEffect {
    RunPreflight,
    RequestConsent {
        action: String,
        domain: String,
        files: Vec<String>,
        bytes: u64,
    },
    /// Ask model to think and optionally emit tool calls
    Reason,
    /// Execute the requested tool
    ExecuteTool { command: String },
    WriteFinalArtifact,
    RecordFailure { error: String },
    None,
}

pub fn reduce(state: &mut GenericRunState, event: GenericRunEvent) -> GenericSideEffect {
    match (&state.state, event) {
        (GenericPipelineState::Idle, GenericRunEvent::ContextLoaded(task)) => {
            info!(task = %task.name, "Context loaded");
            state.state = GenericPipelineState::Preflight;
            GenericSideEffect::RunPreflight
        }
        (GenericPipelineState::Idle, GenericRunEvent::ContextLoadFailed(reason)) => {
            error!(%reason, "Context load failed");
            state.state = GenericPipelineState::Failed { error: reason.clone() };
            GenericSideEffect::RecordFailure { error: reason }
        }
        (GenericPipelineState::Preflight, GenericRunEvent::PreflightPassed(budget)) => {
            info!("Preflight passed, starting reasoning loop");
            state.state = GenericPipelineState::Reasoning;
            GenericSideEffect::Reason
        }
        (GenericPipelineState::Preflight, GenericRunEvent::PreflightFailed(reason)) => {
            warn!(%reason, "Preflight failed");
            state.state = GenericPipelineState::Failed { error: reason.clone() };
            GenericSideEffect::RecordFailure { error: reason }
        }
        (GenericPipelineState::Reasoning, GenericRunEvent::ModelCompleted(result)) => {
            // In a real controller, we'd parse the model's output here to see if it emitted a tool call.
            // For now, the RunController parses it and emits either ToolCallRequested or TaskCompleted.
            GenericSideEffect::None
        }
        (GenericPipelineState::Reasoning, GenericRunEvent::ToolCallRequested { command }) => {
            info!(%command, "Model requested tool call");
            state.state = GenericPipelineState::ExecutingTool { tool_call: command.clone() };
            GenericSideEffect::ExecuteTool { command }
        }
        (GenericPipelineState::Reasoning, GenericRunEvent::TaskCompleted { summary }) => {
            info!("Model completed the task");
            state.state = GenericPipelineState::Finalizing;
            GenericSideEffect::WriteFinalArtifact
        }
        (GenericPipelineState::ExecutingTool { .. }, GenericRunEvent::ToolExecutionFinished { success, output, .. }) => {
            info!(success, "Tool execution finished");
            state.state = GenericPipelineState::Reasoning;
            GenericSideEffect::Reason
        }
        (GenericPipelineState::Finalizing, GenericRunEvent::TaskCompleted { .. }) => {
            state.state = GenericPipelineState::Completed;
            GenericSideEffect::None
        }
        (current, GenericRunEvent::Failed(err)) if current.is_active() => {
            error!(%err, "Pipeline failed");
            state.state = GenericPipelineState::Failed { error: err.to_string() };
            GenericSideEffect::RecordFailure { error: err.to_string() }
        }
        (current, GenericRunEvent::CancelRequested) if current.is_active() => {
            warn!("Run cancelled by user");
            state.state = GenericPipelineState::Cancelled;
            GenericSideEffect::None
        }
        _ => GenericSideEffect::None,
    }
}
