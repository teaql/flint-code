use crate::app::{App, ServiceHealth};
use agent_core::shared::{PlanStepStatus, ToolProcessStatus};
use ratatui::{
    Frame,
    layout::{Alignment, Rect},
    style::{Color, Modifier, Style},
    text::{Line, Span},
    widgets::{Block, Borders, Paragraph},
};

pub fn draw_system_status(f: &mut Frame, app: &App, area: Rect) {
    let (llm_icon, llm_label, llm_color) = match &app.llm_service_health {
        ServiceHealth::Checking => ("●", "checking".to_string(), Color::Yellow),
        ServiceHealth::Healthy => ("✓", "healthy".to_string(), Color::Green),
        ServiceHealth::Unavailable(detail) => ("✗", format!("unavailable: {detail}"), Color::Red),
    };
    let (rag_icon, rag_label, rag_color) = match &app.rag_service_health {
        ServiceHealth::Checking => ("●", "checking".to_string(), Color::Yellow),
        ServiceHealth::Healthy => ("✓", "healthy".to_string(), Color::Green),
        ServiceHealth::Unavailable(detail) => ("✗", format!("unavailable: {detail}"), Color::Red),
    };

    let text = vec![
        Line::from(vec![
            Span::styled(" target:   ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                app.remote_target.name.clone(),
                Style::default().fg(Color::DarkGray),
            ),
        ]),
        Line::from(vec![
            Span::styled(" session:  ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                app.remote_session_id
                    .as_deref()
                    .map(short_session_id)
                    .unwrap_or("not attached"),
                Style::default().fg(if app.remote_session_id.is_some() {
                    Color::Cyan
                } else {
                    Color::DarkGray
                }),
            ),
        ]),
        Line::from(vec![
            Span::styled(" endpoint: ", Style::default().fg(Color::DarkGray)),
            Span::styled(app.profile.chat_url(), Style::default().fg(Color::DarkGray)),
        ]),
        Line::from(vec![
            Span::styled(" LLM service: ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                format!("{llm_icon} {llm_label}"),
                Style::default().fg(llm_color),
            ),
        ]),
        Line::from(vec![
            Span::styled(" RAG service: ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                format!("{rag_icon} {rag_label}"),
                Style::default().fg(rag_color),
            ),
        ]),
        Line::from(vec![Span::styled(
            " * toggle the panel with /hud",
            Style::default().fg(Color::DarkGray),
        )]),
    ];
    let block = Block::default()
        .title(Span::styled(
            " Flint ",
            Style::default().fg(Color::DarkGray),
        ))
        .title_alignment(Alignment::Center)
        .borders(Borders::TOP)
        .border_style(Style::default().fg(Color::DarkGray))
        .padding(ratatui::widgets::Padding::horizontal(1));
    f.render_widget(
        Paragraph::new(text).block(block).alignment(Alignment::Left),
        area,
    );
}

fn short_session_id(session_id: &str) -> &str {
    session_id.get(..8).unwrap_or(session_id)
}

pub fn draw_context_metrics(f: &mut Frame, app: &App, area: Rect) {
    let input = app.run_state().token_totals.input_tokens;
    let output = app.run_state().token_totals.output_tokens;
    let total = input + output;

    let g_input = app.global_input_tokens;
    let g_output = app.global_output_tokens;
    let g_total = g_input + g_output;

    let format_k = |v: u64| {
        let s = if v >= 1000 {
            format!("{}K", v / 1000)
        } else {
            v.to_string()
        };
        format!("{:>5}", s)
    };

    let reqs = app.run_state().token_totals.model_calls;
    let g_reqs = app.global_model_calls;

    let text = vec![
        Line::from(vec![
            Span::styled(" global: ", Style::default().fg(Color::DarkGray)),
            Span::styled("↑", Style::default().fg(Color::Green)),
            Span::styled(format_k(g_input), Style::default().fg(Color::Green)),
            Span::styled(" + ", Style::default().fg(Color::DarkGray)),
            Span::styled("↓", Style::default().fg(Color::Yellow)),
            Span::styled(format_k(g_output), Style::default().fg(Color::Yellow)),
            Span::styled(" = ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                format_k(g_total),
                Style::default()
                    .fg(Color::Cyan)
                    .add_modifier(Modifier::BOLD),
            ),
            Span::styled(
                format!(" ({} reqs)", g_reqs),
                Style::default().fg(Color::DarkGray),
            ),
        ]),
        Line::from(vec![
            Span::styled(" task:   ", Style::default().fg(Color::DarkGray)),
            Span::styled("↑", Style::default().fg(Color::Green)),
            Span::styled(format_k(input), Style::default().fg(Color::Green)),
            Span::styled(" + ", Style::default().fg(Color::DarkGray)),
            Span::styled("↓", Style::default().fg(Color::Yellow)),
            Span::styled(format_k(output), Style::default().fg(Color::Yellow)),
            Span::styled(" = ", Style::default().fg(Color::DarkGray)),
            Span::styled(
                format_k(total),
                Style::default()
                    .fg(Color::Cyan)
                    .add_modifier(Modifier::BOLD),
            ),
            Span::styled(
                format!(" ({} reqs)", reqs),
                Style::default().fg(Color::DarkGray),
            ),
        ]),
    ];
    let block = Block::default()
        .title(Span::styled(
            " Tokens ",
            Style::default().fg(Color::DarkGray),
        ))
        .title_alignment(Alignment::Center)
        .borders(Borders::TOP)
        .border_style(Style::default().fg(Color::DarkGray))
        .padding(ratatui::widgets::Padding::horizontal(1));
    f.render_widget(Paragraph::new(text).block(block), area);
}

pub fn draw_context_size(f: &mut Frame, app: &App, area: Rect) {
    let latest = app.latest_prompt_tokens;
    let max_observed = app.max_observed_prompt_tokens;
    let context_window = u64::from(app.profile.context.model_context_tokens);

    let format_k = |v: u64| {
        if v >= 1000 {
            format!("{}K", v / 1000)
        } else {
            v.to_string()
        }
    };

    let text = vec![Line::from(vec![
        Span::styled(" window: ", Style::default().fg(Color::DarkGray)),
        Span::styled(
            format_k(context_window),
            Style::default()
                .fg(Color::Blue)
                .add_modifier(Modifier::BOLD),
        ),
        Span::styled(" latest: ", Style::default().fg(Color::DarkGray)),
        Span::styled(format_k(latest), Style::default().fg(Color::Cyan)),
        Span::styled("  max seen: ", Style::default().fg(Color::DarkGray)),
        Span::styled(
            format_k(max_observed),
            Style::default()
                .fg(Color::Magenta)
                .add_modifier(Modifier::BOLD),
        ),
    ])];
    let block = Block::default()
        .title(Span::styled(
            " Context ",
            Style::default().fg(Color::DarkGray),
        ))
        .title_alignment(Alignment::Center)
        .borders(Borders::TOP)
        .border_style(Style::default().fg(Color::DarkGray))
        .padding(ratatui::widgets::Padding::horizontal(1));
    f.render_widget(Paragraph::new(text).block(block), area);
}

pub fn draw_tool_commands(f: &mut Frame, app: &App, area: Rect) {
    if area.height == 0 {
        return;
    }
    let running: Vec<_> = app
        .run_state()
        .tool_processes
        .iter()
        .filter(|process| process.status == ToolProcessStatus::Running)
        .rev()
        .collect();

    let mut virtual_lines = Vec::new();

    let blink_style = if app.plan_pulse_phase % 2 == 0 {
        Style::default()
            .fg(Color::LightCyan)
            .add_modifier(Modifier::BOLD)
    } else {
        Style::default().fg(Color::Cyan)
    };
    let arrow = if app.plan_pulse_phase % 2 == 0 {
        "▶"
    } else {
        "▷"
    };

    if app.vllm_in_flight {
        virtual_lines.push(Line::from(vec![
            Span::styled(format!(" {} ", arrow), blink_style),
            Span::styled("VLLM (Generating)", Style::default().fg(Color::White)),
        ]));
    } else {
        virtual_lines.push(Line::from(vec![
            Span::styled(" ▶ ", Style::default().fg(Color::DarkGray)),
            Span::styled("VLLM (Standby)", Style::default().fg(Color::DarkGray)),
        ]));
    }

    if app.rag_in_flight {
        virtual_lines.push(Line::from(vec![
            Span::styled(format!(" {} ", arrow), blink_style),
            Span::styled(
                "RAG (Retrieving Context)",
                Style::default().fg(Color::White),
            ),
        ]));
    } else {
        virtual_lines.push(Line::from(vec![
            Span::styled(" ▶ ", Style::default().fg(Color::DarkGray)),
            Span::styled("RAG (Standby)", Style::default().fg(Color::DarkGray)),
        ]));
    }

    let count = virtual_lines.len() + running.len();

    // Maximum 6 lines available for content (from ui.rs tools_height)
    // If we exceed 5 lines of tools, we reserve 1 line for the "... and X more" text.
    let max_displayed_tools = if count > 5 { 5 } else { count };
    let display_count = max_displayed_tools.saturating_sub(virtual_lines.len());

    let mut lines = virtual_lines;
    lines.extend(running.into_iter().take(display_count).map(|process| {
        Line::from(vec![
            Span::styled(" ● ", Style::default().fg(Color::Cyan)),
            Span::styled(process.command.clone(), Style::default().fg(Color::White)),
        ])
    }));

    if count > 5 {
        lines.push(Line::from(vec![Span::styled(
            format!("   ... and {} more", count - max_displayed_tools),
            Style::default().fg(Color::DarkGray),
        )]));
    }

    let block = Block::default()
        .title(Span::styled(
            " Active Tools ",
            Style::default().fg(Color::DarkGray),
        ))
        .title_alignment(Alignment::Center)
        .borders(Borders::TOP)
        .border_style(Style::default().fg(Color::DarkGray))
        .padding(ratatui::widgets::Padding::horizontal(1));

    f.render_widget(Paragraph::new(lines).block(block), area);
}

pub fn draw_compact_status(f: &mut Frame, app: &App, area: Rect) {
    use ratatui::widgets::Wrap;
    let run = app.run_state();
    let (completed, total) = app.plan_progress();
    let content = run.plan.iter().enumerate().map(|(index, step)| {
        let (step_icon, step_style) = plan_step_style(step.status, app.plan_pulse_phase);
        Line::from(vec![
            Span::styled(format!("{step_icon} "), step_style),
            Span::styled(format!("{}. {}", index + 1, step.title), step_style),
        ])
    });
    f.render_widget(
        Paragraph::new(content.collect::<Vec<_>>())
            .block(
                Block::default()
                    .title(format!(" Plan {completed}/{total} "))
                    .title_alignment(Alignment::Center)
                    .borders(Borders::TOP)
                    .padding(ratatui::widgets::Padding::horizontal(1))
                    .border_style(Style::default().fg(Color::DarkGray)),
            )
            .wrap(Wrap { trim: true }),
        area,
    );
}

pub fn status_panel_height(app: &App) -> u16 {
    (app.run_state().plan.len() as u16 + 1).clamp(4, 10)
}

pub fn plan_step_style(status: PlanStepStatus, pulse_phase: u8) -> (&'static str, Style) {
    match status {
        PlanStepStatus::Completed => ("✓", Style::default().fg(Color::Green)),
        PlanStepStatus::InProgress | PlanStepStatus::WaitingUser => {
            let (color, modifier) = match pulse_phase % 4 {
                0 => (Color::DarkGray, Modifier::empty()),
                2 => (Color::LightCyan, Modifier::BOLD),
                _ => (Color::Cyan, Modifier::empty()),
            };
            ("●", Style::default().fg(color).add_modifier(modifier))
        }
        PlanStepStatus::Failed => ("✗", Style::default().fg(Color::Red)),
        PlanStepStatus::Blocked | PlanStepStatus::Cancelled => {
            ("-", Style::default().fg(Color::Yellow))
        }
        _ => ("○", Style::default().fg(Color::DarkGray)),
    }
}
