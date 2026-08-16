#![allow(dead_code)]

use crate::app::{App, ServiceHealth};
use agent_core::state::PipelineState;
use ratatui::{
    style::{Color, Style},
    text::{Line, Span},
};

pub const HELP_TEXT: &str = r#"FlintCode TUI Legacy — Keyboard Shortcuts

  g     Main conversation
  v     Statistics
  d     Candidate / diff view
  ?     Toggle help
  j/k   Scroll down/up
  click Open a transcript entry by its T### ID
  c     Cancel current operation
  i     Focus interactive prompt
  q     Quit

While editing the prompt:
  Enter             Submit in the current session
  Shift/Alt+Enter   Insert a new line
  Esc               Return to dashboard shortcuts

Task sessions:
  Project files, tools, builds, and tests run only in the selected SSH runner
  The target is required at startup; there is no local project fallback
  Startup is attached: ordinary input starts or continues one coding task
  Follow-ups reuse the same durable SSH runner session and workspace
  /ask and /chat answer once without leaving the attached task
  /done leaves the task after its current operation has stopped

Slash commands:
  /show T###         Open the full transcript entry
  /task <request>    Attach and submit a development task
  /task <package-dir> Attach and load a task package plus acceptance sidecars
  /ask <question>    Ask once without changing the session
  /chat <question>   Alias for /ask
  /done              Leave the current task session
  /new               Detach the old SSH session and prepare a fresh task session
  /main              Main conversation
  /stats             Plan, validation, context, and Token usage"#;

pub fn state_presentation(state: &PipelineState) -> (&'static str, &'static str, Color) {
    match state {
        PipelineState::Idle => ("○", "IDLE", Color::Gray),
        PipelineState::LoadingTask | PipelineState::Preflight => ("●", "PREFLIGHT", Color::Cyan),
        PipelineState::AwaitingConsent { .. } => ("◆", "WAITING FOR YOU", Color::Yellow),
        PipelineState::Generating { .. } => ("●", "GENERATING", Color::Cyan),
        PipelineState::LocalValidation { .. }
        | PipelineState::DomainValidation { .. }
        | PipelineState::BuildValidation { .. } => ("●", "VALIDATING", Color::Cyan),
        PipelineState::FollowUpValidation { .. } => ("●", "CONTINUING", Color::Cyan),
        PipelineState::Repairing { .. } => ("↻", "REPAIRING", Color::Yellow),
        PipelineState::Finalizing => ("●", "FINALIZING", Color::Cyan),
        PipelineState::Completed => ("✓", "COMPLETED", Color::Green),
        PipelineState::Failed { .. } => ("✗", "FAILED", Color::Red),
        PipelineState::Cancelled => ("–", "CANCELLED", Color::Yellow),
        PipelineState::SkippedByPolicy { .. } => ("■", "BLOCKED BY POLICY", Color::Red),
    }
}

pub fn format_tokens(tokens: u64) -> String {
    if tokens >= 1_000_000 {
        format!("{:.1}M", tokens as f64 / 1_000_000.0)
    } else if tokens >= 1_000 {
        format!("{:.1}K", tokens as f64 / 1_000.0)
    } else {
        tokens.to_string()
    }
}

pub fn endpoint_line(app: &App) -> Line<'static> {
    Line::from(vec![
        Span::styled(" endpoint · ", Style::default().fg(Color::DarkGray)),
        Span::styled(
            app.profile.resolve_endpoint(),
            Style::default().fg(Color::DarkGray),
        ),
    ])
}

pub fn service_health_line(health: &ServiceHealth) -> Line<'static> {
    let (icon, label, color) = match health {
        ServiceHealth::Checking => ("●", "checking LLM service".to_string(), Color::Yellow),
        ServiceHealth::Healthy => ("✓", "LLM service healthy".to_string(), Color::Green),
        ServiceHealth::Unavailable(detail) => (
            "✗",
            format!("LLM service unavailable · {detail}"),
            Color::Red,
        ),
    };
    Line::from(vec![
        Span::styled(" service  · ", Style::default().fg(Color::DarkGray)),
        Span::styled(format!("{icon} {label}"), Style::default().fg(color)),
    ])
}

#[cfg(test)]
mod tests {
    use super::HELP_TEXT;
    use agent_core::shared::PlanStepStatus;

    use crate::widgets::plan_step_style;

    #[test]
    fn plan_step_visuals_collapse_to_three_states_and_current_step_pulses() {
        assert_eq!(plan_step_style(PlanStepStatus::Pending, 2).0, "○");
        assert_eq!(plan_step_style(PlanStepStatus::Failed, 2).0, "✗");
        assert_eq!(plan_step_style(PlanStepStatus::InProgress, 2).0, "●");
        assert_eq!(plan_step_style(PlanStepStatus::WaitingUser, 2).0, "●");
        assert_eq!(plan_step_style(PlanStepStatus::Completed, 2).0, "✓");

        let bright = plan_step_style(PlanStepStatus::InProgress, 2).1;
        let dim = plan_step_style(PlanStepStatus::InProgress, 0).1;
        assert_ne!(bright.fg, dim.fg);
    }

    #[test]
    fn help_explains_persistent_task_session_commands() {
        assert!(HELP_TEXT.contains("Startup is attached"));
        assert!(HELP_TEXT.contains("no local project fallback"));
        assert!(HELP_TEXT.contains("reuse the same durable SSH runner session"));
        assert!(HELP_TEXT.contains("/ask and /chat answer once"));
        assert!(HELP_TEXT.contains("/done leaves the task"));
        assert!(HELP_TEXT.contains("/new"));
    }
}
