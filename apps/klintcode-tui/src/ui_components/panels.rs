use crate::ui_components::*;
use crate::ui_components::helpers::*;
use ratatui::{
    Frame,
    layout::{Alignment, Constraint, Direction, Layout, Rect},
    style::{Color, Modifier, Style},
    text::{Line, Span, Text},
    widgets::{Block, Borders, Clear, List, ListItem, Padding, Paragraph, Wrap},
};
use agent_core::state::PipelineState;
use agent_core::shared::{PlanStepStatus, ToolProcessStatus};
use crate::app::{App, InputMode, ServiceHealth, TimelineRole, View};
use crate::widgets::*;

pub fn draw_plan(f: &mut Frame, app: &App, area: Rect) {
    let run = app.run_state();
    let (completed, total) = app.plan_progress();
    let items: Vec<ListItem> = run
        .plan
        .iter()
        .enumerate()
        .map(|(index, step)| {
            let (icon, style) = plan_step_style(step.status, app.plan_pulse_phase);
            let mut lines = vec![Line::from(vec![
                Span::styled(format!(" {icon} "), style),
                Span::styled(format!("{}. {}", index + 1, step.title), style),
            ])];
            if let Some(detail) = &step.detail {
                lines.push(Line::from(vec![
                    Span::raw("     "),
                    Span::styled(detail, style),
                ]));
            }
            ListItem::new(lines)
        })
        .collect();

    let block = Block::default()
        .title(format!(" Plan {completed}/{total} "))
        .borders(Borders::BOTTOM)
        .padding(Padding::horizontal(1))
        .border_style(Style::default().fg(
            if matches!(&run.state, PipelineState::AwaitingConsent { .. }) {
                Color::Yellow
            } else {
                Color::DarkGray
            },
        ));
    let list = List::new(items).block(block);
    f.render_widget(list, area);
}

pub fn draw_validation_history(f: &mut Frame, app: &App, area: Rect) {
    let run = app.run_state();
    let items: Vec<ListItem> = if run.validation_history.is_empty() {
        vec![ListItem::new(Line::from(vec![
            Span::styled(" ○ ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                "Waiting for validation",
                Style::default().fg(Color::DarkGray),
            ),
        ]))]
    } else {
        run.validation_history
            .iter()
            .enumerate()
            .map(|(index, result)| {
                let (icon, color) = if result.passed {
                    ("✓", Color::Green)
                } else {
                    ("✗", Color::Red)
                };
                ListItem::new(Line::from(vec![
                    Span::styled(format!(" {icon} "), Style::default().fg(color)),
                    Span::styled(
                        format!("L{} {}", result.level, result.level_name),
                        Style::default().fg(color).add_modifier(Modifier::BOLD),
                    ),
                    Span::styled(
                        format!(
                            "  check {} · {:.1}s · {} errors · {} warnings",
                            index + 1,
                            result.elapsed_secs,
                            result.error_count,
                            result.warning_count
                        ),
                        Style::default().fg(Color::DarkGray),
                    ),
                ]))
            })
            .collect()
    };
    let block = Block::default()
        .title(" Validation Timeline ")
        .borders(Borders::BOTTOM)
        .padding(Padding::horizontal(1))
        .border_style(Style::default().fg(Color::DarkGray));
    f.render_widget(List::new(items).block(block), area);
}

pub fn draw_tool_history(f: &mut Frame, app: &App, area: Rect) {
    let processes = &app.run_state().tool_processes;
    let visible_rows = usize::from(area.height.saturating_sub(1));
    let start = processes.len().saturating_sub(visible_rows);
    let items = if processes.is_empty() {
        vec![ListItem::new(Line::from(Span::styled(
            " ○ No running tools",
            Style::default().fg(Color::DarkGray),
        )))]
    } else {
        processes[start..]
            .iter()
            .map(|process| {
                let (icon, label, color) = match process.status {
                    ToolProcessStatus::Running => ("●", "running", Color::Cyan),
                    ToolProcessStatus::Succeeded => ("✓", "completed", Color::Green),
                    ToolProcessStatus::Failed => ("✗", "failed", Color::Red),
                };
                ListItem::new(Line::from(vec![
                    Span::styled(format!(" {icon} "), Style::default().fg(color)),
                    Span::styled(format!("{label} · "), Style::default().fg(Color::DarkGray)),
                    Span::styled(
                        format!("$ {}", process.command),
                        Style::default().fg(Color::White),
                    ),
                ]))
            })
            .collect()
    };
    f.render_widget(
        List::new(items).block(
            Block::default()
                .title(" Tool History ")
                .borders(Borders::BOTTOM)
                .padding(Padding::horizontal(1))
                .border_style(Style::default().fg(Color::DarkGray)),
        ),
        area,
    );
}

pub fn draw_waiting_panel(f: &mut Frame, app: &App, area: Rect) {
    let action = match &app.run_state().state {
        PipelineState::AwaitingConsent { action } => action.as_str(),
        _ => "Your input is required to continue",
    };
    let content = vec![
        Line::from(Span::styled(
            "◆ Waiting for your response",
            Style::default()
                .fg(Color::Yellow)
                .add_modifier(Modifier::BOLD),
        )),
        Line::from(""),
        Line::from(action),
        Line::from(""),
        Line::from(vec![
            Span::styled("[y]", Style::default().fg(Color::Green)),
            Span::raw(" Allow  "),
            Span::styled("[n]", Style::default().fg(Color::Red)),
            Span::raw(" Deny"),
        ]),
    ];
    let block = Block::default()
        .title(" USER INPUT REQUIRED ")
        .borders(Borders::BOTTOM)
        .padding(Padding::horizontal(1))
        .border_style(Style::default().fg(Color::Yellow));
    f.render_widget(Paragraph::new(content).block(block), area);
}

pub fn draw_right_panel(f: &mut Frame, app: &App, area: Rect) {
    let (title, content) = match app.view {
        View::Candidate => (" Candidate / Diff ", app.candidate.as_str()),
        View::Diagnostics => (" Diagnostics ", app.diagnostics.as_str()),
        View::Help => (" Help ", HELP_TEXT),
        _ => (" Diagnostics ", app.diagnostics.as_str()),
    };

    let block = Block::default()
        .title(title)
        .borders(Borders::BOTTOM)
        .padding(Padding::horizontal(1))
        .border_style(Style::default().fg(Color::DarkGray));
    let paragraph = Paragraph::new(content)
        .block(block)
        .wrap(Wrap { trim: false })
        .scroll((app.scroll_offset, 0));
    f.render_widget(paragraph, area);
}

pub fn draw_token_bar(f: &mut Frame, app: &App, area: Rect) {
    let run = app.run_state();
    let prompt_limit = run
        .context_budget
        .as_ref()
        .map(|budget| budget.prompt_limit)
        .unwrap_or(app.profile.context.max_prompt_tokens);
    let model_in_flight = matches!(
        run.state,
        PipelineState::Generating { .. } | PipelineState::Repairing { .. }
    );
    let context_tokens = if model_in_flight {
        None
    } else {
        run.current_context_tokens()
    };
    let context_ratio = context_tokens.map(|tokens| {
        if prompt_limit == 0 {
            0.0
        } else {
            f64::from(tokens) / f64::from(prompt_limit)
        }
    });
    let context_color = match context_ratio {
        Some(ratio) if ratio >= 0.9 => Color::Red,
        Some(ratio) if ratio >= 0.8 => Color::LightRed,
        Some(ratio) if ratio >= 0.6 => Color::Yellow,
        Some(_) => Color::Green,
        None => Color::DarkGray,
    };
    let (completed, total) = app.plan_progress();

    let mut spans = vec![Span::raw(" Context ")];
    match (context_tokens, context_ratio) {
        (Some(tokens), Some(ratio)) => {
            spans.push(Span::styled(
                format!(
                    "{} / {} · {:.0}% · reported",
                    format_tokens(u64::from(tokens)),
                    format_tokens(u64::from(prompt_limit)),
                    ratio * 100.0
                ),
                Style::default()
                    .fg(context_color)
                    .add_modifier(Modifier::BOLD),
            ));
        }
        _ if model_in_flight => {
            spans.push(Span::styled(
                "waiting for model usage",
                Style::default().fg(Color::Cyan),
            ));
        }
        _ => {
            spans.push(Span::styled(
                "reported after response",
                Style::default().fg(Color::DarkGray),
            ));
        }
    }
    spans.push(Span::raw("    Session "));
    if run.token_totals.model_calls == 0 {
        spans.push(Span::styled(
            "reported after response",
            Style::default().fg(Color::DarkGray),
        ));
    } else {
        spans.push(Span::styled(
            format!("↑{}", format_tokens(run.token_totals.input_tokens)),
            Style::default().fg(Color::Cyan),
        ));
        spans.push(Span::raw(" "));
        spans.push(Span::styled(
            format!("↓{}", format_tokens(run.token_totals.output_tokens)),
            Style::default().fg(Color::Green),
        ));
        spans.push(Span::styled(
            format!(" · {} calls", run.token_totals.model_calls),
            Style::default().fg(Color::DarkGray),
        ));
    }
    spans.push(Span::raw("    Plan "));
    spans.push(Span::styled(
        format!("{completed}/{total}"),
        Style::default().fg(Color::White),
    ));
    let line = Line::from(spans);

    let block = Block::default()
        .borders(Borders::TOP | Borders::BOTTOM)
        .title(" Context & Tokens ")
        .padding(Padding::horizontal(1))
        .border_style(Style::default().fg(context_color));
    f.render_widget(Paragraph::new(line).block(block), area);
}

