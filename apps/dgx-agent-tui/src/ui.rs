//! Ratatui rendering — pipeline observation and control UI.

use anyhow::Result;
use crossterm::{
    event::{self, Event, KeyCode, KeyModifiers},
    execute,
    terminal::{disable_raw_mode, enable_raw_mode, EnterAlternateScreen, LeaveAlternateScreen},
};
use ratatui::{
    backend::CrosstermBackend,
    layout::{Constraint, Direction, Layout, Rect},
    style::{Color, Modifier, Style},
    text::{Line, Span},
    widgets::{Block, Borders, Gauge, List, ListItem, Paragraph, Wrap},
    Frame, Terminal,
};
use std::io;
use std::time::Duration;

use crate::app::{App, StageStatus, View};

/// Run the TUI event loop.
pub async fn run(app: &mut App) -> Result<()> {
    enable_raw_mode()?;
    let mut stdout = io::stdout();
    execute!(stdout, EnterAlternateScreen)?;
    let backend = CrosstermBackend::new(stdout);
    let mut terminal = Terminal::new(backend)?;

    let result = event_loop(&mut terminal, app).await;

    disable_raw_mode()?;
    execute!(terminal.backend_mut(), LeaveAlternateScreen)?;
    terminal.show_cursor()?;

    result
}

async fn event_loop(
    terminal: &mut Terminal<CrosstermBackend<io::Stdout>>,
    app: &mut App,
) -> Result<()> {
    loop {
        terminal.draw(|f| draw(f, app))?;

        if event::poll(Duration::from_millis(100))? {
            if let Event::Key(key) = event::read()? {
                match key.code {
                    KeyCode::Char('q') => app.should_quit = true,
                    KeyCode::Char('c') if key.modifiers.contains(KeyModifiers::CONTROL) => {
                        app.should_quit = true;
                    }
                    KeyCode::Char('g') => app.view = View::Pipeline,
                    KeyCode::Char('v') => app.view = View::Diagnostics,
                    KeyCode::Char('d') => app.view = View::Candidate,
                    KeyCode::Char('t') => app.view = View::Task,
                    KeyCode::Char('?') => {
                        app.view = if app.view == View::Help { View::Pipeline } else { View::Help };
                    }
                    KeyCode::Char('j') | KeyCode::Down => {
                        app.scroll_offset = app.scroll_offset.saturating_add(1);
                    }
                    KeyCode::Char('k') | KeyCode::Up => {
                        app.scroll_offset = app.scroll_offset.saturating_sub(1);
                    }
                    _ => {}
                }
            }
        }

        if app.should_quit {
            return Ok(());
        }
    }
}

fn draw(f: &mut Frame, app: &App) {
    let chunks = Layout::default()
        .direction(Direction::Vertical)
        .constraints([
            Constraint::Length(2),  // Header
            Constraint::Length(1),  // Run info
            Constraint::Min(10),   // Main content
            Constraint::Length(2),  // Token bar
            Constraint::Length(1),  // Keybindings
        ])
        .split(f.area());

    draw_header(f, app, chunks[0]);
    draw_run_info(f, app, chunks[1]);
    draw_main(f, app, chunks[2]);
    draw_token_bar(f, app, chunks[3]);
    draw_keybindings(f, chunks[4]);
}

fn draw_header(f: &mut Frame, app: &App, area: Rect) {
    let in_flight = if app.run.state.is_active() { "1" } else { "0" };
    let header = Line::from(vec![
        Span::styled(" DGX Agent", Style::default().fg(Color::Cyan).add_modifier(Modifier::BOLD)),
        Span::raw(" ─ Model: "),
        Span::styled(&app.profile.model.name, Style::default().fg(Color::Green)),
        Span::raw(" ─ "),
        Span::styled(
            format!("{}K", app.profile.context.model_context_tokens / 1000),
            Style::default().fg(Color::Yellow),
        ),
        Span::raw(" ─ In-flight: "),
        Span::styled(
            format!("{in_flight}/{}", app.profile.concurrency.max_in_flight),
            Style::default().fg(if in_flight == "0" { Color::Gray } else { Color::Cyan }),
        ),
    ]);
    let block = Block::default().borders(Borders::TOP | Borders::LEFT | Borders::RIGHT);
    let paragraph = Paragraph::new(header).block(block);
    f.render_widget(paragraph, area);
}

fn draw_run_info(f: &mut Frame, app: &App, area: Rect) {
    let state_color = match &app.run.state {
        agent_core::state::PipelineState::Completed => Color::Green,
        agent_core::state::PipelineState::Failed { .. } => Color::Red,
        agent_core::state::PipelineState::Cancelled => Color::Yellow,
        _ if app.run.state.is_active() => Color::Cyan,
        _ => Color::Gray,
    };

    let run_name = app.run.task_name.as_deref().unwrap_or(&app.run.run_id);
    let line = Line::from(vec![
        Span::raw(" Run: "),
        Span::styled(run_name, Style::default().fg(Color::White).add_modifier(Modifier::BOLD)),
        Span::raw("  │  State: "),
        Span::styled(app.run.state.label(), Style::default().fg(state_color).add_modifier(Modifier::BOLD)),
    ]);
    let block = Block::default().borders(Borders::LEFT | Borders::RIGHT);
    f.render_widget(Paragraph::new(line).block(block), area);
}

fn draw_main(f: &mut Frame, app: &App, area: Rect) {
    // Split main area into left (pipeline/task) and right (candidate/diagnostics)
    let cols = Layout::default()
        .direction(Direction::Horizontal)
        .constraints([Constraint::Percentage(40), Constraint::Percentage(60)])
        .split(area);

    // Left column: Pipeline + Task files
    let left_rows = Layout::default()
        .direction(Direction::Vertical)
        .constraints([Constraint::Percentage(60), Constraint::Percentage(40)])
        .split(cols[0]);

    draw_pipeline(f, app, left_rows[0]);
    draw_task_files(f, app, left_rows[1]);

    // Right column: Candidate/Diagnostics
    draw_right_panel(f, app, cols[1]);
}

fn draw_pipeline(f: &mut Frame, app: &App, area: Rect) {
    let stages = app.stage_statuses();
    let items: Vec<ListItem> = stages
        .iter()
        .map(|(name, status)| {
            let (icon, color) = match status {
                StageStatus::Done => ("✓", Color::Green),
                StageStatus::Active => ("▶", Color::Cyan),
                StageStatus::Pending => ("○", Color::DarkGray),
                StageStatus::Failed => ("✗", Color::Red),
            };

            // Find timing for this stage
            let timing = app.run.timings.iter().find(|t| {
                t.stage.contains(&name.to_lowercase().replace(' ', "_"))
            });
            let time_str = timing
                .map(|t| format!("  {:.1}s", t.elapsed_secs()))
                .unwrap_or_default();

            ListItem::new(Line::from(vec![
                Span::styled(format!(" {icon} "), Style::default().fg(color)),
                Span::styled(*name, Style::default().fg(color)),
                Span::styled(time_str, Style::default().fg(Color::DarkGray)),
            ]))
        })
        .collect();

    let block = Block::default()
        .title(" Pipeline ")
        .borders(Borders::ALL)
        .border_style(Style::default().fg(Color::DarkGray));
    let list = List::new(items).block(block);
    f.render_widget(list, area);
}

fn draw_task_files(f: &mut Frame, app: &App, area: Rect) {
    let items: Vec<ListItem> = app
        .task_files
        .iter()
        .map(|f_name| {
            ListItem::new(Line::from(vec![
                Span::styled(" ✓ ", Style::default().fg(Color::Green)),
                Span::raw(f_name),
            ]))
        })
        .collect();

    let block = Block::default()
        .title(" Task / Files ")
        .borders(Borders::ALL)
        .border_style(Style::default().fg(Color::DarkGray));
    let list = List::new(items).block(block);
    f.render_widget(list, area);
}

fn draw_right_panel(f: &mut Frame, app: &App, area: Rect) {
    let (title, content) = match app.view {
        View::Candidate => (" Candidate / Diff ", &app.candidate),
        View::Diagnostics => (" Diagnostics ", &app.diagnostics),
        View::Help => (" Help ", &HELP_TEXT.to_string()),
        _ => (" Diagnostics ", &app.diagnostics),
    };

    let block = Block::default()
        .title(title)
        .borders(Borders::ALL)
        .border_style(Style::default().fg(Color::DarkGray));
    let paragraph = Paragraph::new(content.as_str())
        .block(block)
        .wrap(Wrap { trim: false })
        .scroll((app.scroll_offset, 0));
    f.render_widget(paragraph, area);
}

fn draw_token_bar(f: &mut Frame, app: &App, area: Rect) {
    let prompt_limit = app.profile.context.max_prompt_tokens;
    let completion_limit = app.profile.context.max_completion_tokens;
    let safety = app.profile.context.safety_tokens;

    let line = Line::from(vec![
        Span::raw(" Prompt "),
        Span::styled(
            format!("{:>5}", app.token_prompt),
            Style::default().fg(Color::Cyan),
        ),
        Span::styled(
            format!(" / {prompt_limit:>5}"),
            Style::default().fg(Color::DarkGray),
        ),
        Span::raw(" │ Completion "),
        Span::styled(
            format!("{:>5}", app.token_completion),
            Style::default().fg(Color::Green),
        ),
        Span::styled(
            format!(" / {completion_limit:>4}"),
            Style::default().fg(Color::DarkGray),
        ),
        Span::raw(" │ Reserve "),
        Span::styled(
            format!("{safety:>5}"),
            Style::default().fg(Color::Yellow),
        ),
    ]);

    let block = Block::default()
        .borders(Borders::ALL)
        .title(" Tokens ")
        .border_style(Style::default().fg(Color::DarkGray));
    f.render_widget(Paragraph::new(line).block(block), area);
}

fn draw_keybindings(f: &mut Frame, area: Rect) {
    let line = Line::from(vec![
        Span::styled(" [g]", Style::default().fg(Color::Cyan)),
        Span::raw(" Run  "),
        Span::styled("[v]", Style::default().fg(Color::Cyan)),
        Span::raw(" Validate  "),
        Span::styled("[d]", Style::default().fg(Color::Cyan)),
        Span::raw(" Diff  "),
        Span::styled("[t]", Style::default().fg(Color::Cyan)),
        Span::raw(" Task  "),
        Span::styled("[c]", Style::default().fg(Color::Cyan)),
        Span::raw(" Cancel  "),
        Span::styled("[?]", Style::default().fg(Color::Cyan)),
        Span::raw(" Help  "),
        Span::styled("[q]", Style::default().fg(Color::Red)),
        Span::raw(" Quit"),
    ]);
    f.render_widget(Paragraph::new(line), area);
}

const HELP_TEXT: &str = r#"DGX Agent TUI — Keyboard Shortcuts

  g     Pipeline view (default)
  v     Validation / diagnostics view
  d     Candidate / diff view
  t     Task files view
  ?     Toggle help
  j/k   Scroll down/up
  c     Cancel current operation
  q     Quit

The TUI observes the pipeline state machine.
All execution is driven by the agent-core reducer."#;
