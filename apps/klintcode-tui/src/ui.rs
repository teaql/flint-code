//! Ratatui rendering — pipeline observation and control UI.

use crate::ui_components::*;
use crate::widgets::*;

use anyhow::Result;
use crossterm::{
    event::{Event, KeyCode, KeyModifiers, EnableBracketedPaste, DisableBracketedPaste},
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
use agent_core::state::PipelineState;
use agent_core::shared::ToolProcessStatus;

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
    execute!(terminal.backend_mut(), LeaveAlternateScreen, DisableBracketedPaste)?;
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
    let health_probe = App::probe_model_service(health_profile);
    tokio::pin!(health_probe);
    let mut health_probe_complete = false;
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
                            KeyCode::Esc => app.view = View::Main,

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
                            crossterm::event::MouseEventKind::ScrollUp => {
                                if app.view == View::Main {
                                    app.transcript_scroll_back =
                                        app.transcript_scroll_back.saturating_add(3);
                                } else {
                                    app.scroll_offset = app.scroll_offset.saturating_sub(3);
                                }
                            }
                            crossterm::event::MouseEventKind::ScrollDown => {
                                if app.view == View::Main {
                                    app.transcript_scroll_back =
                                        app.transcript_scroll_back.saturating_sub(3);
                                } else {
                                    app.scroll_offset = app.scroll_offset.saturating_add(3);
                                }
                            }
                            crossterm::event::MouseEventKind::Down(crossterm::event::MouseButton::Left) => {
                                if app.view == View::Main {
                                    let layout = app.transcript_layout.borrow();
                                    for &(start_y, end_y, id) in layout.iter() {
                                        if mouse.row >= start_y && mouse.row < end_y {
                                            app.detail_entry_id = Some(id);
                                            app.view = View::TranscriptDetail;
                                            app.input_notice = Some("Opened detail view via click".to_string());
                                            app.input_mode = InputMode::Navigation;
                                            app.input_cursor_visible = false;
                                            break;
                                        }
                                    }
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
            health = &mut health_probe, if !health_probe_complete => {
                app.service_health = health;
                health_probe_complete = true;
            }
        }

        if app.should_quit {
            return Ok(());
        }
    }
}

fn draw(f: &mut Frame, app: &App) {
    let composer_lines = visible_composer_lines(app);
    let composer_height = composer_lines + 2;
    let chunks = Layout::default()
        .direction(Direction::Vertical)
        .constraints([
            Constraint::Min(8),
            Constraint::Length(1), // Spacer between main surface and composer
            Constraint::Length(composer_height),
            Constraint::Length(1),
        ])
        .split(f.area());

    match app.view {
        View::Stats => draw_stats_screen(f, app, chunks[0]),
        View::Candidate => draw_plain_screen(f, "Candidate", &app.candidate, chunks[0]),
        View::Diagnostics => draw_plain_screen(f, "Diagnostics", &app.diagnostics, chunks[0]),
        View::Help => draw_plain_screen(f, "Help", HELP_TEXT, chunks[0]),
        View::RagSearch => {
            let content = if app.rag_search_results.is_empty() {
                "Type a query and press Enter to search.".to_string()
            } else {
                app.rag_search_results.join("\n\n")
            };
            draw_plain_screen(f, "RAG Search (Test) - Esc to return", &content, chunks[0])
        }
        View::ModelEval => {
            draw_plain_screen(f, "Model Evaluation Report - Esc to return", "Latest evaluation results would be here.", chunks[0])
        }
        View::VllmTest => {
            let content = if app.vllm_test_output.is_empty() {
                "Type a prompt and press Enter to test remote VLLM.".to_string()
            } else {
                app.vllm_test_output.clone()
            };
            draw_plain_screen(f, "VLLM Remote Manual Test - Esc to return", &content, chunks[0])
        }
        View::TranscriptDetail => {
            let content = app.detail_entry_id
                .and_then(|id| app.timeline.get(id))
                .map(|e| e.content.as_str())
                .unwrap_or("No detail available.");
            draw_plain_screen(f, "Transcript Detail - Esc to return", content, chunks[0])
        }
        _ => draw_main_surface(f, app, chunks[0]),
    }


    draw_composer(f, app, chunks[2]);
    draw_bottom_hint(f, app, chunks[3]);
}

fn draw_main_surface(f: &mut Frame, app: &App, area: Rect) {
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
        let tools_height = if running_count > 0 { (running_count.min(6) + 3) as u16 } else { 3 };

        if app.task_surface_active {
            let status_rows = Layout::default()
                .direction(Direction::Vertical)
                .constraints([
                    Constraint::Length(6),
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
                    Constraint::Length(6),
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
    use ratatui::backend::TestBackend;
    use ratatui::Terminal;
    pub fn rendered_screen(app: &crate::app::App, width: u16, height: u16) -> String {
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
    fn right_sidebar_layout_order_is_fixed() {
        let mut app = crate::app::App::new(None).unwrap();
        app.task_surface_active = true;
        // Mock a plan so the Plan panel renders
        app.dummy_run.state = agent_core::state::PipelineState::Generating { attempt: 1 };
        
        let screen = rendered_screen(&app, 120, 40);
        
        let flint_idx = screen.find("Flint").unwrap_or(0);
        let tokens_idx = screen.find("Tokens").unwrap_or(0);
        let context_idx = screen.find("Context").unwrap_or(0);
        let plan_idx = screen.find("Plan").unwrap_or(0);
        let tools_idx = screen.find("Active Tools").unwrap_or(0);
        
        // Assert the vertical stacking order
        assert!(flint_idx > 0, "Flint panel missing");
        assert!(tokens_idx > flint_idx, "Tokens panel should be below Flint panel");
        assert!(context_idx > tokens_idx, "Context panel should be below Tokens panel");
        // We might not render Plan if there are no steps, but let's assume Plan renders if task_surface_active.
        // Even if empty, the title is " Plan 0/0 ".
        assert!(plan_idx > context_idx, "Plan panel should be below Context panel");
        assert!(tools_idx > plan_idx, "Active Tools panel should be at the very bottom, below Plan panel");
    }
}
