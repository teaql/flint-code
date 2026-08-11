//! Ratatui rendering — pipeline observation and control UI.

use crate::ui_components::*;
use crate::widgets::*;
use anyhow::Result;
use crossterm::{
    event::{
        DisableBracketedPaste, EnableBracketedPaste, Event, KeyCode, KeyModifiers, MouseButton,
        MouseEventKind,
    },
    execute,
    terminal::{EnterAlternateScreen, LeaveAlternateScreen, disable_raw_mode, enable_raw_mode},
};
use ratatui::{
    Frame, Terminal,
    backend::CrosstermBackend,
    layout::{Constraint, Direction, Layout, Rect},
};
use std::io;
use std::time::Duration;

use agent_core::event::ExportConsent;
use agent_core::shared::ToolProcessStatus;
use agent_core::state::PipelineState;

use crate::app::{App, InputMode, View};

/// Run the TUI event loop.
pub async fn run(app: &mut App) -> Result<()> {
    enable_raw_mode()?;
    let mut stdout = io::stdout();
    execute!(stdout, EnterAlternateScreen, EnableBracketedPaste)?;
    let backend = CrosstermBackend::new(stdout);
    let mut terminal = Terminal::new(backend)?;

    let result = event_loop(&mut terminal, app).await;

    disable_raw_mode()?;
    execute!(
        terminal.backend_mut(),
        LeaveAlternateScreen,
        DisableBracketedPaste
    )?;
    terminal.show_cursor()?;

    result
}

use futures::StreamExt;

async fn event_loop(
    terminal: &mut Terminal<CrosstermBackend<io::Stdout>>,
    app: &mut App,
) -> Result<()> {
    let (input_tx, mut input_rx) = tokio::sync::mpsc::channel(32);
    tokio::spawn(async move {
        let mut reader = crossterm::event::EventStream::new();
        while let Some(Ok(event)) = reader.next().await {
            if input_tx.send(event).await.is_err() {
                break;
            }
        }
    });

    let mut cursor_blink = tokio::time::interval(Duration::from_millis(500));
    cursor_blink.set_missed_tick_behavior(tokio::time::MissedTickBehavior::Skip);
    let mut plan_pulse = tokio::time::interval(Duration::from_millis(850));
    plan_pulse.set_missed_tick_behavior(tokio::time::MissedTickBehavior::Skip);
    let health_profile = app.profile.clone();
    let llm_health_probe = App::probe_model_service(health_profile);
    let rag_health_probe = App::probe_rag_service();
    tokio::pin!(llm_health_probe);
    tokio::pin!(rag_health_probe);
    let mut llm_health_probe_complete = false;
    let mut rag_health_probe_complete = false;
    loop {
        terminal.draw(|f| draw(f, app))?;

        tokio::select! {
            // 1. Terminal events
            maybe_event = input_rx.recv() => {
                if let Some(event) = maybe_event {
                  match event {
                    Event::Paste(text) => {
                        if app.input_mode != InputMode::Editing {
                            app.input_mode = InputMode::Editing;
                            app.input_cursor_visible = true;
                            app.input_notice = None;
                        }
                        for ch in text.chars() {
                            app.insert_input_char(ch);
                        }
                    }
                    Event::Key(key) => {
                    if key.code == KeyCode::Char('c')
                        && key.modifiers.contains(KeyModifiers::CONTROL)
                    {
                        app.should_quit = true;
                    } else if key.code == KeyCode::Esc
                        && app.view == View::TranscriptDetail
                    {
                        app.close_transcript_detail();
                    } else if matches!(
                        &app.run_state().state,
                        PipelineState::AwaitingConsent { .. }
                    ) && matches!(key.code, KeyCode::Char('y') | KeyCode::Char('n'))
                    {
                        if let Some(tx) = app.controller_event_tx.as_ref() {
                            match key.code {
                                KeyCode::Char('y') => {
                                    let consent = ExportConsent {
                                        domain: "local".to_string(),
                                        files: Vec::new(),
                                        total_bytes: 0,
                                        purpose: "TUI approval".to_string(),
                                        approved: true,
                                        approved_by: Some("tui-user".to_string()),
                                    };
                                    let _ = tx
                                        .try_send(agent_core::event::RunEvent::ConsentGranted(consent));
                                }
                                KeyCode::Char('n') => {
                                    let _ = tx
                                        .try_send(agent_core::event::RunEvent::ConsentDenied(
                                            "User denied the requested action".to_string(),
                                        ));
                                }
                                _ => {}
                            }
                        }
                    } else if app.input_mode == InputMode::Editing {
                        match key.code {
                            KeyCode::Esc => {
                                app.input_mode = InputMode::Navigation;
                                app.input_cursor_visible = false;
                            }
                            KeyCode::Enter
                                if key.modifiers.intersects(
                                    KeyModifiers::SHIFT | KeyModifiers::ALT,
                                ) =>
                            {
                                app.insert_input_newline();
                            }
                            KeyCode::Enter => {
                                app.submit_input().await?;
                            }
                            KeyCode::Backspace => app.backspace_input(),
                            KeyCode::Delete => app.delete_input(),
                            KeyCode::Left => app.move_input_left(),
                            KeyCode::Right => app.move_input_right(),
                            KeyCode::Home => app.move_input_home(),
                            KeyCode::End => app.move_input_end(),
                            KeyCode::Char(character)
                                if !key.modifiers.contains(KeyModifiers::CONTROL) =>
                            {
                                app.insert_input_char(character);
                            }
                            _ => {}
                        }
                    } else {
                        match key.code {
                            KeyCode::Char('i') => {
                                app.input_mode = InputMode::Editing;
                                app.input_cursor_visible = true;
                                app.input_notice = None;
                            }
                            KeyCode::Char('q') => app.should_quit = true,
                            KeyCode::Char('c') => {
                                if let Some(tx) = app.controller_event_tx.as_ref() {
                                    let _ = tx.try_send(agent_core::event::RunEvent::CancelRequested);
                                }
                            }
                            KeyCode::Char('g') => app.view = View::Main,
                            KeyCode::Char('v') => app.view = View::Stats,
                            KeyCode::Char('d') => app.view = View::Candidate,

                        KeyCode::Char('?') => {
                                app.view = if app.view == View::Help { View::Main } else { View::Help };
                        }
                        KeyCode::Char('j') | KeyCode::Down => {
                            if app.view == View::Main {
                                app.transcript_scroll_back =
                                    app.transcript_scroll_back.saturating_sub(1);
                            } else {
                                app.scroll_offset = app.scroll_offset.saturating_add(1);
                            }
                        }
                        KeyCode::Char('k') | KeyCode::Up => {
                            if app.view == View::Main {
                                app.transcript_scroll_back =
                                    app.transcript_scroll_back.saturating_add(1);
                            } else {
                                app.scroll_offset = app.scroll_offset.saturating_sub(1);
                            }
                        }
                        _ => {}
                        }
                    }
                    }
                    Event::Mouse(mouse) => {
                        match mouse.kind {
                            MouseEventKind::Down(MouseButton::Left) => {
                                if app.view == View::Main {
                                    app.open_timeline_entry_at(mouse.column, mouse.row);
                                }
                            }
                            MouseEventKind::ScrollUp => {
                                if app.view == View::Main {
                                    app.transcript_scroll_back =
                                        app.transcript_scroll_back.saturating_add(3);
                                } else {
                                    app.scroll_offset = app.scroll_offset.saturating_sub(3);
                                }
                            }
                            MouseEventKind::ScrollDown => {
                                if app.view == View::Main {
                                    app.transcript_scroll_back =
                                        app.transcript_scroll_back.saturating_sub(3);
                                } else {
                                    app.scroll_offset = app.scroll_offset.saturating_add(3);
                                }
                            }
                            _ => {}
                        }
                    }
                    _ => {}
                  }
                }
            }

            // 2. Events from Executor (proxy)
            proxy_res = async {
                if let Some(rx) = app.proxy_event_rx.as_mut() {
                    rx.recv().await
                } else {
                    std::future::pending().await
                }
            } => {
                match proxy_res {
                    Some(event) => {
                        // Update UI from event
                        match &event {
                            agent_core::event::RunEvent::ValidationCompleted(res) => {
                                app.diagnostics = res.diagnostic.clone();
                            }
                            _ => {}
                        }
                        app.observe_event(&event);

                        if let Some(tx) = app.controller_event_tx.as_mut() {
                            let _ = tx.try_send(event);
                        }
                    }
                    None => {
                        app.proxy_event_rx = None;
                    }
                }
            }

            // 3. Process controller (reduces events and emits side effects)
            ctrl_res = async {
                if let Some(controller) = app.controller.as_mut() {
                    controller.process_next().await
                } else {
                    std::future::pending().await
                }
            } => {
                match ctrl_res {
                    Some(effect) => {
                        // Show dispatched effects in timeline for debugging
                        let effect_label = match &effect {
                            agent_core::reducer::SideEffect::RunPreflight => Some("→ RunPreflight"),
                            agent_core::reducer::SideEffect::Generate { attempt } => Some(if *attempt == 1 { "→ Generate" } else { "→ Repair/Generate" }),
                            agent_core::reducer::SideEffect::RunLocalValidation { .. } => Some("→ LocalValidation"),
                            agent_core::reducer::SideEffect::RunDomainValidation { .. } => Some("→ DomainValidation"),
                            agent_core::reducer::SideEffect::RunBuildValidation { .. } => Some("→ BuildValidation"),
                            agent_core::reducer::SideEffect::Repair { .. } => Some("→ Repair"),
                            agent_core::reducer::SideEffect::WriteFinalArtifact => Some("→ WriteFinalArtifact"),
                            agent_core::reducer::SideEffect::RecordFailure { error } => {
                                app.timeline.push(crate::app::TimelineEntry {
                                    role: crate::app::TimelineRole::Error,
                                    content: format!("→ RecordFailure: {error}"),
                                });
                                None
                            }
                            _ => None,
                        };
                        if let Some(label) = effect_label {
                            app.timeline.push(crate::app::TimelineEntry {
                                role: crate::app::TimelineRole::Activity,
                                content: label.to_string(),
                            });
                        }

                        match effect {
                            agent_core::reducer::SideEffect::RequestConsent { action, .. } => {
                                if let Some(tx) = app.controller_event_tx.as_ref() {
                                    let _ = tx
                                        .try_send(agent_core::event::RunEvent::ConsentRequired { action });
                                }
                            }
                            effect => {
                                if let Some(tx) = app.executor_effect_tx.as_ref() {
                                    let _ = tx.try_send(effect);
                                }
                            }
                        }
                    }
                    None => {
                        app.controller = None;
                    }
                }
            }

            // 4. Lightweight conversational responses bypass the task pipeline.
            chat_res = async {
                if let Some(rx) = app.chat_event_rx.as_mut() {
                    rx.recv().await
                } else {
                    std::future::pending().await
                }
            } => {
                match chat_res {
                    Some(event) => app.observe_chat_event(event),
                    None => app.chat_event_rx = None,
                }
            }

            // 5. Blink only while the prompt owns keyboard focus.
            _ = cursor_blink.tick() => {
                if app.input_mode == InputMode::Editing {
                    app.input_cursor_visible = !app.input_cursor_visible;
                } else {
                    app.input_cursor_visible = false;
                }
            }

            // 6. Pulse the current plan step independently from input focus.
            _ = plan_pulse.tick() => {
                app.plan_pulse_phase = (app.plan_pulse_phase + 1) % 4;
            }

            // 7. Probe once after the interface is visible.
            health = &mut llm_health_probe, if !llm_health_probe_complete => {
                app.llm_service_health = health;
                llm_health_probe_complete = true;
            }

            rag_health = &mut rag_health_probe, if !rag_health_probe_complete => {
                app.rag_service_health = rag_health;
                rag_health_probe_complete = true;
            }
        }

        if app.should_quit {
            return Ok(());
        }
    }
}

fn draw(f: &mut Frame, app: &mut App) {
    let composer_lines = visible_composer_lines(app);
    let composer_height = composer_lines + 2;
    let chunks = Layout::default()
        .direction(Direction::Vertical)
        .constraints([
            Constraint::Min(8),
            Constraint::Length(1),
            Constraint::Length(composer_height),
            Constraint::Length(1),
        ])
        .split(f.area());

    match app.view {
        View::Stats => draw_stats_screen(f, app, chunks[0]),
        View::Candidate => {
            draw_plain_screen(f, "Candidate", &app.candidate, app.scroll_offset, chunks[0])
        }
        View::Diagnostics => draw_plain_screen(
            f,
            "Diagnostics",
            &app.diagnostics,
            app.scroll_offset,
            chunks[0],
        ),
        View::TranscriptDetail => {
            if let Some((id, entry)) = app.timeline_detail() {
                draw_plain_screen(
                    f,
                    &format!("Transcript T{id:03} · Esc back"),
                    &entry.content,
                    app.scroll_offset,
                    chunks[0],
                );
            } else {
                draw_plain_screen(
                    f,
                    "Transcript",
                    "Transcript entry not found",
                    app.scroll_offset,
                    chunks[0],
                );
            }
        }
        View::Help => draw_plain_screen(f, "Help", HELP_TEXT, app.scroll_offset, chunks[0]),
        _ => draw_main_surface(f, app, chunks[0]),
    }

    draw_composer(f, app, chunks[2]);
    draw_bottom_hint(f, app, chunks[3]);
}

fn draw_main_surface(f: &mut Frame, app: &mut App, area: Rect) {
    if app.show_right_panel {
        let columns = Layout::default()
            .direction(Direction::Horizontal)
            .constraints([Constraint::Percentage(75), Constraint::Percentage(25)])
            .split(area);
        draw_transcript(f, app, columns[0]);

        let running_count = app
            .run_state()
            .tool_processes
            .iter()
            .filter(|p| p.status == ToolProcessStatus::Running)
            .count();
        let tools_height = if running_count > 0 {
            (running_count.min(6) + 3) as u16
        } else {
            3
        };

        if app.task_surface_active {
            let status_rows = Layout::default()
                .direction(Direction::Vertical)
                .constraints([
                    Constraint::Length(7),
                    Constraint::Length(3),
                    Constraint::Length(3),
                    Constraint::Length(status_panel_height(app) + 1),
                    Constraint::Length(tools_height),
                    Constraint::Min(0),
                ])
                .split(columns[1]);
            draw_system_status(f, app, status_rows[0]);
            draw_context_metrics(f, app, status_rows[1]);
            draw_context_size(f, app, status_rows[2]);
            draw_compact_status(f, app, status_rows[3]);
            draw_tool_commands(f, app, status_rows[4]);
        } else {
            let status_rows = Layout::default()
                .direction(Direction::Vertical)
                .constraints([
                    Constraint::Length(7),
                    Constraint::Length(3),
                    Constraint::Length(3),
                    Constraint::Length(tools_height),
                    Constraint::Min(0),
                ])
                .split(columns[1]);
            draw_system_status(f, app, status_rows[0]);
            draw_context_metrics(f, app, status_rows[1]);
            draw_context_size(f, app, status_rows[2]);
            draw_tool_commands(f, app, status_rows[3]);
        }
    } else {
        draw_transcript(f, app, area);
    }
}

#[cfg(test)]
pub mod tests {
    use super::*;
    use ratatui::Terminal;
    use ratatui::backend::TestBackend;
    pub fn rendered_screen(app: &mut crate::app::App, width: u16, height: u16) -> String {
        let backend = TestBackend::new(width, height);
        let mut terminal = Terminal::new(backend).expect("test terminal");
        terminal
            .draw(|frame| draw(frame, app))
            .expect("render screen");
        let buffer = terminal.backend().buffer();
        let mut rendered = String::new();
        for y in 0..height {
            for x in 0..width {
                rendered.push_str(buffer[(x, y)].symbol());
            }
            rendered.push('\n');
        }
        rendered
    }

    #[test]
    fn right_sidebar_keeps_plan_above_active_tools() {
        let mut app = crate::app::App::new(None).expect("app");
        app.task_surface_active = true;
        app.dummy_run.state = agent_core::state::PipelineState::Generating { attempt: 1 };

        let screen = rendered_screen(&mut app, 120, 40);
        assert!(screen.contains("LLM service:"));
        assert!(screen.contains("RAG service:"));
        assert!(screen.contains("window:"));
        let flint = screen.find("Flint").expect("Flint panel");
        let tokens = screen.find("Tokens").expect("Tokens panel");
        let context = screen.find("Context").expect("Context panel");
        let plan = screen.find("Plan").expect("Plan panel");
        let tools = screen.find("Active Tools").expect("Active Tools panel");

        assert!(flint < tokens);
        assert!(tokens < context);
        assert!(context < plan);
        assert!(plan < tools);
    }

    #[test]
    fn transcript_entries_are_one_line_and_clickable_by_id() {
        let mut app = crate::app::App::new(None).expect("app");
        app.timeline.push(crate::app::TimelineEntry {
            role: crate::app::TimelineRole::Agent,
            content: "first line\nsecond line with full detail".to_string(),
        });

        let screen = rendered_screen(&mut app, 120, 40);

        assert!(screen.contains("[T001]"));
        assert!(screen.contains("chars omitted"));
        assert!(!screen.contains("second line with full detail"));
        let hitbox = app.transcript_hitboxes[0];
        assert!(app.open_timeline_entry_at(hitbox.left, hitbox.row));
        assert_eq!(app.view, crate::app::View::TranscriptDetail);
        assert_eq!(
            app.timeline_detail().unwrap().1.content,
            "first line\nsecond line with full detail"
        );
    }

    #[test]
    fn transcript_detail_preserves_newlines_and_supports_scrolling() {
        let mut app = crate::app::App::new(None).expect("app");
        let content = (1..=40)
            .map(|line| format!("detail-line-{line:02}"))
            .collect::<Vec<_>>()
            .join("\n");
        app.timeline.push(crate::app::TimelineEntry {
            role: crate::app::TimelineRole::Agent,
            content,
        });
        assert!(app.open_timeline_entry(1));

        let first_screen = rendered_screen(&mut app, 100, 18);
        let rows = first_screen.lines().collect::<Vec<_>>();
        let first_row = rows
            .iter()
            .position(|row| row.contains("detail-line-01"))
            .expect("first detail line");
        let second_row = rows
            .iter()
            .position(|row| row.contains("detail-line-02"))
            .expect("second detail line");
        assert_eq!(second_row, first_row + 1);

        app.scroll_offset = 12;
        let scrolled = rendered_screen(&mut app, 100, 18);
        assert!(!scrolled.contains("detail-line-01"));
        assert!(scrolled.contains("detail-line-11"));
    }
}
