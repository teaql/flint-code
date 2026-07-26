//! TUI rendering and event handling using ratatui + crossterm.

use anyhow::Result;
use crossterm::{
    event::{self, Event, KeyCode, KeyModifiers, KeyEventKind},
    execute,
    terminal::{disable_raw_mode, enable_raw_mode, EnterAlternateScreen, LeaveAlternateScreen},
};
use ratatui::{
    backend::CrosstermBackend,
    layout::{Constraint, Direction, Layout, Rect, Alignment},
    style::{Color, Modifier, Style, Stylize},
    text::{Line, Span, Text},
    widgets::{
        Block, Borders, Gauge, List, ListItem, Padding,
        Paragraph, Scrollbar, ScrollbarOrientation, ScrollbarState, Wrap,
    },
    Frame, Terminal,
};
use std::io;
use std::time::Duration;

use crate::app::{ActivePanel, AgentStatus, App, AppMode};
use crate::context::Role;

const TICK_RATE_MS: u64 = 50;

/// Run the TUI application
pub async fn run(app: &mut App) -> Result<()> {
    // Setup terminal
    enable_raw_mode()?;
    let mut stdout = io::stdout();
    execute!(stdout, EnterAlternateScreen)?;
    let backend = CrosstermBackend::new(stdout);
    let mut terminal = Terminal::new(backend)?;
    terminal.clear()?;

    // Main event loop
    let result = event_loop(&mut terminal, app).await;

    // Restore terminal
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
        // Draw UI
        terminal.draw(|frame| draw(frame, app))?;

        // Poll for events
        if event::poll(Duration::from_millis(TICK_RATE_MS))? {
            if let Event::Key(key) = event::read()? {
                if key.kind != KeyEventKind::Press {
                    continue;
                }
                handle_key(app, key.code, key.modifiers).await?;
            }
        }

        if app.should_quit {
            break;
        }
    }
    Ok(())
}

/// Handle keyboard input
async fn handle_key(app: &mut App, key: KeyCode, modifiers: KeyModifiers) -> Result<()> {
    // Global keybindings
    match (key, modifiers) {
        (KeyCode::Char('c'), KeyModifiers::CONTROL) => {
            app.should_quit = true;
            return Ok(());
        }
        (KeyCode::Char('q'), KeyModifiers::CONTROL) => {
            app.should_quit = true;
            return Ok(());
        }
        _ => {}
    }

    match app.mode {
        AppMode::Normal => handle_normal_mode(app, key, modifiers).await,
        AppMode::Insert => handle_insert_mode(app, key, modifiers).await,
        AppMode::Command => handle_insert_mode(app, key, modifiers).await,
        AppMode::Help => {
            // Any key exits help
            app.show_help = false;
            app.mode = AppMode::Normal;
            Ok(())
        }
    }
}

async fn handle_normal_mode(app: &mut App, key: KeyCode, _modifiers: KeyModifiers) -> Result<()> {
    match key {
        KeyCode::Char('i') | KeyCode::Char('/') => {
            app.mode = AppMode::Insert;
            if key == KeyCode::Char('/') {
                app.input_buffer.push('/');
                app.input_cursor = 1;
            }
        }
        KeyCode::Char('?') => {
            app.show_help = true;
            app.mode = AppMode::Help;
        }
        KeyCode::Tab => {
            app.active_panel = match app.active_panel {
                ActivePanel::Chat => ActivePanel::FileTree,
                ActivePanel::FileTree => ActivePanel::Editor,
                ActivePanel::Editor => ActivePanel::TokenBudget,
                ActivePanel::TokenBudget => ActivePanel::Chat,
                ActivePanel::CommandPalette => ActivePanel::Chat,
            };
        }
        KeyCode::Char('j') | KeyCode::Down => {
            app.chat_scroll = app.chat_scroll.saturating_add(1);
        }
        KeyCode::Char('k') | KeyCode::Up => {
            app.chat_scroll = app.chat_scroll.saturating_sub(1);
        }
        KeyCode::Char('G') => {
            app.chat_scroll = u16::MAX; // scroll to bottom
        }
        KeyCode::Char('g') => {
            app.chat_scroll = 0; // scroll to top
        }
        KeyCode::Char('r') => {
            app.workspace.refresh();
        }
        _ => {}
    }
    Ok(())
}

async fn handle_insert_mode(app: &mut App, key: KeyCode, modifiers: KeyModifiers) -> Result<()> {
    match key {
        KeyCode::Esc => {
            app.mode = AppMode::Normal;
        }
        KeyCode::Enter => {
            if modifiers.contains(KeyModifiers::SHIFT) {
                // Shift+Enter for newline in input
                app.input_buffer.insert(app.input_cursor, '\n');
                app.input_cursor += 1;
            } else {
                app.submit_input().await?;
                app.mode = AppMode::Normal;
            }
        }
        KeyCode::Backspace => {
            if app.input_cursor > 0 {
                app.input_cursor -= 1;
                app.input_buffer.remove(app.input_cursor);
            }
        }
        KeyCode::Delete => {
            if app.input_cursor < app.input_buffer.len() {
                app.input_buffer.remove(app.input_cursor);
            }
        }
        KeyCode::Left => {
            app.input_cursor = app.input_cursor.saturating_sub(1);
        }
        KeyCode::Right => {
            if app.input_cursor < app.input_buffer.len() {
                app.input_cursor += 1;
            }
        }
        KeyCode::Home => {
            app.input_cursor = 0;
        }
        KeyCode::End => {
            app.input_cursor = app.input_buffer.len();
        }
        KeyCode::Up => {
            app.history_up();
        }
        KeyCode::Down => {
            app.history_down();
        }
        KeyCode::Char(c) => {
            app.input_buffer.insert(app.input_cursor, c);
            app.input_cursor += 1;
        }
        _ => {}
    }
    Ok(())
}

/// Draw the entire TUI
fn draw(frame: &mut Frame, app: &App) {
    let size = frame.area();

    // Show help overlay
    if app.show_help {
        draw_help(frame, size);
        return;
    }

    // Main layout: header, body, status, input
    let main_layout = Layout::default()
        .direction(Direction::Vertical)
        .constraints([
            Constraint::Length(3),  // Header
            Constraint::Min(10),   // Body
            Constraint::Length(3),  // Token gauge
            Constraint::Length(1),  // Status bar
            Constraint::Length(3),  // Input
        ])
        .split(size);

    draw_header(frame, main_layout[0]);

    // Body: file tree (left) + chat/editor (right)
    let body_layout = Layout::default()
        .direction(Direction::Horizontal)
        .constraints([
            Constraint::Percentage(25), // File tree
            Constraint::Percentage(75), // Chat
        ])
        .split(main_layout[1]);

    draw_file_tree(frame, body_layout[0], app);
    draw_chat(frame, body_layout[1], app);
    draw_token_gauge(frame, main_layout[2], app);
    draw_status_bar(frame, main_layout[3], app);
    draw_input(frame, main_layout[4], app);
}

fn draw_header(frame: &mut Frame, area: Rect) {
    let header_text = vec![
        Line::from(vec![
            Span::styled(" ⚡ TeaQL DGX Spark Agent ", Style::default().fg(Color::Black).bg(Color::Green).add_modifier(Modifier::BOLD)),
            Span::raw(" "),
            Span::styled("v0.1.0", Style::default().fg(Color::DarkGray)),
            Span::raw("  "),
            Span::styled("64K Context", Style::default().fg(Color::Yellow)),
            Span::raw(" │ "),
            Span::styled("cargo-teaql 2.0.8", Style::default().fg(Color::Cyan)),
        ]),
    ];
    let header = Paragraph::new(header_text)
        .block(Block::default()
            .borders(Borders::ALL)
            .border_style(Style::default().fg(Color::Green))
            .title(" NVIDIA DGX Spark ")
            .title_alignment(Alignment::Right)
        );
    frame.render_widget(header, area);
}

fn draw_file_tree(frame: &mut Frame, area: Rect, app: &App) {
    let is_active = app.active_panel == ActivePanel::FileTree;
    let border_color = if is_active { Color::Green } else { Color::DarkGray };

    let items: Vec<ListItem> = app.workspace.flatten_tree()
        .iter()
        .map(|(depth, node)| {
            let indent = "  ".repeat(*depth);
            let icon = if node.is_dir {
                if node.expanded { "📂" } else { "📁" }
            } else {
                match node.path.extension().and_then(|e| e.to_str()) {
                    Some("rs") => "🦀",
                    Some("xml") => "📄",
                    Some("toml") => "⚙️",
                    Some("md") => "📝",
                    _ => "📄",
                }
            };
            let style = if node.is_dir {
                Style::default().fg(Color::Blue).add_modifier(Modifier::BOLD)
            } else {
                Style::default().fg(Color::White)
            };
            ListItem::new(Line::from(vec![
                Span::raw(indent),
                Span::raw(format!("{} ", icon)),
                Span::styled(&node.name, style),
            ]))
        })
        .collect();

    let tree = List::new(items)
        .block(Block::default()
            .title(" Files ")
            .borders(Borders::ALL)
            .border_style(Style::default().fg(border_color))
            .padding(Padding::horizontal(1))
        );
    frame.render_widget(tree, area);
}

fn draw_chat(frame: &mut Frame, area: Rect, app: &App) {
    let is_active = app.active_panel == ActivePanel::Chat;
    let border_color = if is_active { Color::Green } else { Color::DarkGray };

    let mut lines: Vec<Line> = Vec::new();

    for msg in app.context.messages() {
        let (prefix, style) = match msg.role {
            Role::User => (
                "  You ",
                Style::default().fg(Color::Cyan).add_modifier(Modifier::BOLD),
            ),
            Role::Assistant => (
                "  Agent ",
                Style::default().fg(Color::Green).add_modifier(Modifier::BOLD),
            ),
            Role::System => (
                " 🔧 System ",
                Style::default().fg(Color::Yellow),
            ),
            Role::Tool => (
                " ⚙️ Tool ",
                Style::default().fg(Color::Magenta),
            ),
        };

        lines.push(Line::from(vec![
            Span::styled(prefix, style),
            Span::styled(
                format!(" [{}tok]", msg.token_count),
                Style::default().fg(Color::DarkGray),
            ),
        ]));

        // Content lines
        for content_line in msg.content.lines() {
            lines.push(Line::from(Span::raw(format!("    {}", content_line))));
        }
        lines.push(Line::from(""));
    }

    // Show streaming response
    if !app.streaming_response.is_empty() {
        lines.push(Line::from(Span::styled(
            "  Agent ▌",
            Style::default().fg(Color::Green).add_modifier(Modifier::BOLD),
        )));
        for line in app.streaming_response.lines() {
            lines.push(Line::from(Span::styled(
                format!("    {}", line),
                Style::default().fg(Color::Green),
            )));
        }
    }

    let chat = Paragraph::new(lines)
        .block(Block::default()
            .title(" Chat ")
            .borders(Borders::ALL)
            .border_style(Style::default().fg(border_color))
            .padding(Padding::horizontal(1))
        )
        .wrap(Wrap { trim: false })
        .scroll((app.chat_scroll, 0));
    frame.render_widget(chat, area);
}

fn draw_token_gauge(frame: &mut Frame, area: Rect, app: &App) {
    let usage = app.context.token_usage();
    let ratio = (usage.used as f64 / usage.budget as f64).min(1.0);
    let color = if ratio > 0.95 {
        Color::Red
    } else if ratio > 0.85 {
        Color::Yellow
    } else if ratio > 0.7 {
        Color::Blue
    } else {
        Color::Green
    };

    let label = format!(
        " Context: {}/{} tokens ({:.1}%) │ Compactions: {} ",
        usage.used, usage.budget, usage.percentage(), usage.compaction_count
    );

    let gauge = Gauge::default()
        .block(Block::default()
            .title(" Token Budget ")
            .borders(Borders::ALL)
            .border_style(Style::default().fg(Color::DarkGray))
        )
        .gauge_style(Style::default().fg(color))
        .ratio(ratio)
        .label(label);
    frame.render_widget(gauge, area);
}

fn draw_status_bar(frame: &mut Frame, area: Rect, app: &App) {
    let (status_text, status_color) = match &app.status {
        AgentStatus::Idle => ("IDLE".to_string(), Color::Green),
        AgentStatus::Thinking => ("⏳ Thinking...".to_string(), Color::Yellow),
        AgentStatus::Streaming(info) => (format!("📡 {}", info), Color::Cyan),
        AgentStatus::Executing(cmd) => (format!("⚙️ Executing: {}", cmd), Color::Blue),
        AgentStatus::Error(e) => (format!("❌ {}", e), Color::Red),
        AgentStatus::ContextWarning(w) => (format!("⚠️ {}", w), Color::Yellow),
    };

    let mode_text = match app.mode {
        AppMode::Normal => " NORMAL ",
        AppMode::Insert => " INSERT ",
        AppMode::Command => " COMMAND ",
        AppMode::Help => " HELP ",
    };

    let mode_color = match app.mode {
        AppMode::Normal => Color::Blue,
        AppMode::Insert => Color::Green,
        AppMode::Command => Color::Yellow,
        AppMode::Help => Color::Magenta,
    };

    let status = Line::from(vec![
        Span::styled(mode_text, Style::default().fg(Color::Black).bg(mode_color).add_modifier(Modifier::BOLD)),
        Span::raw(" "),
        Span::styled(status_text, Style::default().fg(status_color)),
        Span::raw(" │ "),
        Span::styled(
            format!("Session: {}…", &app.session_id[..8]),
            Style::default().fg(Color::DarkGray),
        ),
    ]);
    frame.render_widget(Paragraph::new(status), area);
}

fn draw_input(frame: &mut Frame, area: Rect, app: &App) {
    let is_insert = matches!(app.mode, AppMode::Insert | AppMode::Command);
    let border_color = if is_insert { Color::Green } else { Color::DarkGray };
    let title = if is_insert { " Input (Esc to cancel, Enter to send) " } else { " Press 'i' to type, '/' for commands, '?' for help " };

    let input = Paragraph::new(app.input_buffer.as_str())
        .block(Block::default()
            .title(title)
            .borders(Borders::ALL)
            .border_style(Style::default().fg(border_color))
        );
    frame.render_widget(input, area);

    // Show cursor in insert mode
    if is_insert {
        frame.set_cursor_position((
            area.x + 1 + app.input_cursor as u16,
            area.y + 1,
        ));
    }
}

fn draw_help(frame: &mut Frame, area: Rect) {
    let help_text = vec![
        Line::from(Span::styled("TeaQL DGX Spark Agent — Help", Style::default().fg(Color::Green).add_modifier(Modifier::BOLD))),
        Line::from(""),
        Line::from(Span::styled("Navigation", Style::default().add_modifier(Modifier::BOLD).fg(Color::Cyan))),
        Line::from("  Tab         Cycle panels"),
        Line::from("  j/k or ↑/↓  Scroll chat"),
        Line::from("  g/G         Top/Bottom of chat"),
        Line::from("  r           Refresh file tree"),
        Line::from(""),
        Line::from(Span::styled("Editing", Style::default().add_modifier(Modifier::BOLD).fg(Color::Cyan))),
        Line::from("  i           Enter insert mode"),
        Line::from("  /           Start command"),
        Line::from("  Enter       Send message"),
        Line::from("  Shift+Enter New line in input"),
        Line::from("  Esc         Back to normal mode"),
        Line::from("  ↑/↓         Command history"),
        Line::from(""),
        Line::from(Span::styled("Commands", Style::default().add_modifier(Modifier::BOLD).fg(Color::Cyan))),
        Line::from("  /help       Toggle this help"),
        Line::from("  /clear      Clear conversation"),
        Line::from("  /compact    Force context compaction"),
        Line::from("  /tokens     Show token usage"),
        Line::from("  /model <n>  Switch model"),
        Line::from("  /quit       Exit agent"),
        Line::from(""),
        Line::from(Span::styled("DGX Spark", Style::default().add_modifier(Modifier::BOLD).fg(Color::Cyan))),
        Line::from("  Context: 64K tokens max"),
        Line::from("  Budget:  48K usable (8K reserved, 8K output)"),
        Line::from("  Auto-compact at 85% usage"),
        Line::from(""),
        Line::from(Span::styled("Press any key to close", Style::default().fg(Color::DarkGray))),
    ];

    let help = Paragraph::new(help_text)
        .block(Block::default()
            .title(" Help ")
            .borders(Borders::ALL)
            .border_style(Style::default().fg(Color::Green))
            .padding(Padding::uniform(2))
        )
        .alignment(Alignment::Left);

    // Center the help dialog
    let help_area = centered_rect(70, 80, area);
    frame.render_widget(ratatui::widgets::Clear, help_area);
    frame.render_widget(help, help_area);
}

/// Create a centered rectangle
fn centered_rect(percent_x: u16, percent_y: u16, area: Rect) -> Rect {
    let popup_layout = Layout::default()
        .direction(Direction::Vertical)
        .constraints([
            Constraint::Percentage((100 - percent_y) / 2),
            Constraint::Percentage(percent_y),
            Constraint::Percentage((100 - percent_y) / 2),
        ])
        .split(area);

    Layout::default()
        .direction(Direction::Horizontal)
        .constraints([
            Constraint::Percentage((100 - percent_x) / 2),
            Constraint::Percentage(percent_x),
            Constraint::Percentage((100 - percent_x) / 2),
        ])
        .split(popup_layout[1])[1]
}
