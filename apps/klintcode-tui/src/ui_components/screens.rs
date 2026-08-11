#![allow(dead_code)]
use crate::ui_components::*;
use crate::ui_components::helpers::*;
use ratatui::{
    Frame,
    layout::{Constraint, Direction, Layout, Rect},
    style::{Color, Modifier, Style},
    text::{Line, Span},
    widgets::{Paragraph, Wrap},
};
use agent_core::state::PipelineState;
use agent_core::shared::ToolProcessStatus;
use crate::app::App;
use crate::widgets::*;

pub fn draw_stats_screen(f: &mut Frame, app: &App, area: Rect) {
    let run = app.run_state();
    let (completed, total) = app.plan_progress();
    let mut lines = Vec::new();

    // Header
    lines.push(Line::from(vec![
        Span::styled(
            " Statistics",
            Style::default().fg(Color::Cyan).add_modifier(Modifier::BOLD),
        ),
        Span::styled("  Esc back", Style::default().fg(Color::DarkGray)),
    ]));
    lines.push(Line::from(""));

    // Plan
    lines.push(Line::from(Span::styled(
        format!(" Plan {completed}/{total}"),
        Style::default().fg(Color::White).add_modifier(Modifier::BOLD),
    )));
    for (index, step) in run.plan.iter().enumerate() {
        let (icon, style) = plan_step_style(step.status, app.plan_pulse_phase);
        lines.push(Line::from(vec![
            Span::styled(format!("   {icon} "), style),
            Span::styled(format!("{}. {}", index + 1, step.title), style),
        ]));
    }
    lines.push(Line::from(""));

    // Validation history
    lines.push(Line::from(Span::styled(
        " Validation",
        Style::default().fg(Color::White).add_modifier(Modifier::BOLD),
    )));
    if run.validation_history.is_empty() {
        lines.push(Line::from(Span::styled(
            "   ○ waiting",
            Style::default().fg(Color::DarkGray),
        )));
    } else {
        for (index, result) in run.validation_history.iter().enumerate() {
            let (icon, color) = if result.passed {
                ("✓", Color::Green)
            } else {
                ("✗", Color::Red)
            };
            lines.push(Line::from(vec![
                Span::styled(format!("   {icon} "), Style::default().fg(color)),
                Span::styled(
                    format!(
                        "L{} {} · check {} · {:.1}s · {} err · {} warn",
                        result.level, result.level_name, index + 1,
                        result.elapsed_secs, result.error_count, result.warning_count
                    ),
                    Style::default().fg(Color::DarkGray),
                ),
            ]));
        }
    }
    lines.push(Line::from(""));

    // Tokens
    let totals = &run.token_totals;
    lines.push(Line::from(Span::styled(
        " Tokens",
        Style::default().fg(Color::White).add_modifier(Modifier::BOLD),
    )));
    lines.push(Line::from(vec![
        Span::styled("   ↑", Style::default().fg(Color::Green)),
        Span::styled(
            format_tokens(totals.input_tokens),
            Style::default().fg(Color::Green),
        ),
        Span::styled(" + ", Style::default().fg(Color::DarkGray)),
        Span::styled("↓", Style::default().fg(Color::Yellow)),
        Span::styled(
            format_tokens(totals.output_tokens),
            Style::default().fg(Color::Yellow),
        ),
        Span::styled(" = ", Style::default().fg(Color::DarkGray)),
        Span::styled(
            format_tokens(totals.input_tokens + totals.output_tokens),
            Style::default().fg(Color::Cyan).add_modifier(Modifier::BOLD),
        ),
        Span::styled(" (", Style::default().fg(Color::DarkGray)),
        Span::styled(format!("{} reqs", totals.model_calls), Style::default().fg(Color::White)),
        Span::styled(")", Style::default().fg(Color::DarkGray)),
    ]));
    lines.push(Line::from(""));

    // Tool processes
    let processes = &run.tool_processes;
    if !processes.is_empty() {
        lines.push(Line::from(Span::styled(
            " Tools",
            Style::default().fg(Color::White).add_modifier(Modifier::BOLD),
        )));
        for process in processes.iter().rev().take(10) {
            let (icon, color) = match process.status {
                ToolProcessStatus::Running => ("●", Color::Cyan),
                ToolProcessStatus::Succeeded => ("✓", Color::Green),
                ToolProcessStatus::Failed => ("✗", Color::Red),
            };
            lines.push(Line::from(vec![
                Span::styled(format!("   {icon} "), Style::default().fg(color)),
                Span::styled(
                    format!("$ {}", process.command),
                    Style::default().fg(Color::DarkGray),
                ),
            ]));
        }
    }

    f.render_widget(
        Paragraph::new(lines)
            .wrap(Wrap { trim: false })
            .scroll((app.scroll_offset, 0)),
        area,
    );
}

pub fn draw_stats_dashboard(f: &mut Frame, app: &App, area: Rect) {
    if area.width < 100 {
        let rows = Layout::default()
            .direction(Direction::Vertical)
            .constraints([
                Constraint::Percentage(45),
                Constraint::Percentage(30),
                Constraint::Percentage(25),
            ])
            .split(area);
        draw_plan(f, app, rows[0]);
        if matches!(
            &app.run_state().state,
            PipelineState::AwaitingConsent { .. }
        ) {
            draw_waiting_panel(f, app, rows[1]);
        } else {
            draw_right_panel(f, app, rows[1]);
        }
        draw_tool_history(f, app, rows[2]);
        return;
    }

    let cols = Layout::default()
        .direction(Direction::Horizontal)
        .constraints([
            Constraint::Percentage(64),
            Constraint::Length(3),
            Constraint::Min(24),
        ])
        .split(area);
    let left_rows = if matches!(
        &app.run_state().state,
        PipelineState::AwaitingConsent { .. }
    ) {
        Layout::default()
            .direction(Direction::Vertical)
            .constraints([
                Constraint::Length(8),
                Constraint::Percentage(38),
                Constraint::Percentage(62),
            ])
            .split(cols[0])
    } else {
        Layout::default()
            .direction(Direction::Vertical)
            .constraints([
                Constraint::Length(0),
                Constraint::Percentage(42),
                Constraint::Percentage(58),
            ])
            .split(cols[0])
    };
    let right_rows = Layout::default()
        .direction(Direction::Vertical)
        .constraints([Constraint::Percentage(72), Constraint::Percentage(28)])
        .split(cols[2]);

    if left_rows[0].height > 0 {
        draw_waiting_panel(f, app, left_rows[0]);
    }
    draw_validation_history(f, app, left_rows[1]);
    draw_right_panel(f, app, left_rows[2]);
    draw_plan(f, app, right_rows[0]);
    draw_tool_history(f, app, right_rows[1]);
}

pub fn draw_plain_screen(f: &mut Frame, title: &str, content: &str, area: Rect) {
    let lines = vec![
        Line::from(vec![
            Span::styled(
                format!(" {title}"),
                Style::default()
                    .fg(Color::Cyan)
                    .add_modifier(Modifier::BOLD),
            ),
            Span::styled(
                " · /main back · /stats",
                Style::default().fg(Color::DarkGray),
            ),
        ]),
        Line::from(""),
        Line::from(content.to_string()),
    ];
    f.render_widget(Paragraph::new(lines).wrap(Wrap { trim: false }), area);
}
