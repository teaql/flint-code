#![allow(dead_code)]

use ratatui::{
    style::{Color, Style},
    text::{Line, Span},
};
use agent_core::state::PipelineState;
use crate::app::{App, ServiceHealth};

pub const HELP_TEXT: &str = r#"KlintCode TUI — Keyboard Shortcuts

  g     Main conversation
  v     Statistics
  d     Candidate / diff view
  ?     Toggle help
  j/k   Scroll down/up
  c     Cancel current operation
  i     Focus interactive prompt
  q     Quit

While editing the prompt:
  Enter             Route and submit input
  Shift/Alt+Enter   Insert a new line
  Esc               Return to dashboard shortcuts

Slash commands:
  /task <request>    Force the development task pipeline
  /ask <question>    Force lightweight conversation
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
        ServiceHealth::Checking => ("●", "checking model service".to_string(), Color::Yellow),
        ServiceHealth::Healthy => ("✓", "model service healthy".to_string(), Color::Green),
        ServiceHealth::Unavailable(detail) => (
            "✗",
            format!("model service unavailable · {detail}"),
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
    use super::*;
    use ratatui::backend::TestBackend;
    use ratatui::Terminal;
    use crate::ui::tests::rendered_screen;
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
    
}
