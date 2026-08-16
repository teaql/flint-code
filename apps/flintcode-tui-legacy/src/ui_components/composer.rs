use crate::app::{App, InputMode, TaskSessionMode};
use ratatui::{
    Frame,
    layout::Rect,
    style::{Color, Modifier, Style},
    text::{Line, Span, Text},
    widgets::{Block, Borders, Padding, Paragraph},
};

pub fn draw_composer(f: &mut Frame, app: &App, area: Rect) {
    let editing = app.input_mode == InputMode::Editing;
    let content = composer_text(app);
    let color = if editing {
        Color::Cyan
    } else {
        Color::DarkGray
    };
    let paragraph = Paragraph::new(content)
        .block(
            Block::default()
                .borders(Borders::TOP | Borders::BOTTOM)
                .padding(Padding::horizontal(1))
                .border_style(Style::default().fg(color)),
        )
        .scroll((composer_scroll(app), 0));
    f.render_widget(paragraph, area);
}

pub fn visible_composer_lines(app: &App) -> u16 {
    app.input_buffer
        .split('\n')
        .count()
        .clamp(1, 8)
        .try_into()
        .unwrap_or(8)
}

pub fn composer_scroll(app: &App) -> u16 {
    app.input_buffer
        .split('\n')
        .count()
        .saturating_sub(8)
        .try_into()
        .unwrap_or(u16::MAX)
}

pub fn composer_text(app: &App) -> Text<'static> {
    if app.input_mode != InputMode::Editing {
        return if app.input_buffer.is_empty() {
            Text::styled(
                composer_placeholder(app),
                Style::default().fg(Color::DarkGray),
            )
        } else {
            Text::styled(app.input_buffer.clone(), Style::default().fg(Color::White))
        };
    }

    let cursor = if app.input_cursor_visible { "█" } else { " " };
    let cursor_style = Style::default()
        .fg(Color::Cyan)
        .add_modifier(Modifier::BOLD);
    if app.input_buffer.is_empty() {
        return Text::from(Line::from(vec![
            Span::styled(cursor, cursor_style),
            Span::styled(
                format!(" {}", composer_placeholder(app)),
                Style::default().fg(Color::DarkGray),
            ),
        ]));
    }

    let byte_index = app
        .input_buffer
        .char_indices()
        .nth(app.input_cursor)
        .map(|(index, _)| index)
        .unwrap_or(app.input_buffer.len());
    let before = &app.input_buffer[..byte_index];
    let after = &app.input_buffer[byte_index..];
    let mut before_lines = before.split('\n').map(str::to_string).collect::<Vec<_>>();
    let mut after_lines = after.split('\n').map(str::to_string).collect::<Vec<_>>();
    let current_before = before_lines.pop().unwrap_or_default();
    let current_after = after_lines.first().cloned().unwrap_or_default();
    if !after_lines.is_empty() {
        after_lines.remove(0);
    }

    let mut lines = before_lines
        .into_iter()
        .map(|line| Line::styled(line, Style::default().fg(Color::White)))
        .collect::<Vec<_>>();
    lines.push(Line::from(vec![
        Span::styled(current_before, Style::default().fg(Color::White)),
        Span::styled(cursor, cursor_style),
        Span::styled(current_after, Style::default().fg(Color::White)),
    ]));
    lines.extend(
        after_lines
            .into_iter()
            .map(|line| Line::styled(line, Style::default().fg(Color::White))),
    );
    Text::from(lines)
}

fn composer_placeholder(app: &App) -> &'static str {
    match app.task_session_mode {
        TaskSessionMode::Attached => "Describe the next task for the coding agent…",
        TaskSessionMode::Detached => "Ask a question, or use /new to start a task…",
    }
}

pub fn draw_bottom_hint(f: &mut Frame, app: &App, area: Rect) {
    let mut spans = vec![Span::raw("  ")];
    if let Some(notice) = &app.input_notice {
        spans.push(Span::styled(
            notice.clone(),
            Style::default().fg(Color::Yellow),
        ));
        spans.push(Span::styled(" · ", Style::default().fg(Color::DarkGray)));
    }
    if app.input_mode == InputMode::Editing {
        let (session_label, session_color) = match app.task_session_mode {
            TaskSessionMode::Attached => ("TASK", Color::Green),
            TaskSessionMode::Detached => ("CHAT", Color::Cyan),
        };
        spans.extend([
            Span::styled(session_label, Style::default().fg(session_color)),
            Span::styled(" · ", Style::default().fg(Color::DarkGray)),
            Span::styled("Enter", Style::default().fg(Color::Green)),
            Span::styled(" send · ", Style::default().fg(Color::DarkGray)),
            Span::styled("Shift+Enter", Style::default().fg(Color::Cyan)),
            Span::styled(" newline · ", Style::default().fg(Color::DarkGray)),
        ]);
        match app.task_session_mode {
            TaskSessionMode::Attached => spans.extend([
                Span::styled("/ask", Style::default().fg(Color::Cyan)),
                Span::styled(" ask once · ", Style::default().fg(Color::DarkGray)),
                Span::styled("/done", Style::default().fg(Color::Yellow)),
                Span::styled(
                    " leave task · ? shortcuts",
                    Style::default().fg(Color::DarkGray),
                ),
            ]),
            TaskSessionMode::Detached => spans.extend([
                Span::styled("/new", Style::default().fg(Color::Green)),
                Span::styled(" new task · ", Style::default().fg(Color::DarkGray)),
                Span::styled("/task", Style::default().fg(Color::Cyan)),
                Span::styled(
                    " attach · ? shortcuts",
                    Style::default().fg(Color::DarkGray),
                ),
            ]),
        }
    } else {
        spans.extend([
            Span::styled("i", Style::default().fg(Color::Green)),
            Span::styled(" input · ", Style::default().fg(Color::DarkGray)),
            Span::styled("j/k", Style::default().fg(Color::Cyan)),
            Span::styled(" scroll · ", Style::default().fg(Color::DarkGray)),
            Span::styled("c", Style::default().fg(Color::Yellow)),
            Span::styled(" cancel · ", Style::default().fg(Color::DarkGray)),
            Span::styled("q", Style::default().fg(Color::Red)),
            Span::styled(" quit", Style::default().fg(Color::DarkGray)),
        ]);
    }
    f.render_widget(Paragraph::new(Line::from(spans)), area);
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::app::{TaskSessionMode, View};
    use crate::ui::tests::rendered_screen;

    #[test]
    fn composer_makes_the_explicit_task_session_visible() {
        let mut app = App::new_for_test(None).expect("app");

        let attached = rendered_screen(&mut app, 120, 36);
        assert!(attached.contains("Describe the next task"));
        assert!(attached.contains("TASK · Enter"));
        assert!(attached.contains("/done"));

        app.task_session_mode = TaskSessionMode::Detached;
        let detached = rendered_screen(&mut app, 120, 36);
        assert!(detached.contains("Ask a question, or use /new"));
        assert!(detached.contains("CHAT · Enter"));
        assert!(detached.contains("/new"));
    }

    #[test]
    fn composer_grows_to_eight_lines_then_shows_only_the_latest_eight() {
        let mut app = App::new_for_test(None).expect("app");
        app.input_buffer = [
            "hidden-alpha",
            "hidden-beta",
            "visible-charlie",
            "visible-delta",
            "visible-echo",
            "visible-foxtrot",
            "visible-golf",
            "visible-hotel",
            "visible-india",
            "visible-juliet",
        ]
        .join("\n");
        app.input_cursor = app.input_buffer.chars().count();

        assert_eq!(visible_composer_lines(&app), 8);
        assert_eq!(composer_scroll(&app), 2);

        let screen = rendered_screen(&mut app, 120, 36);
        assert!(!screen.contains("hidden-alpha"));
        assert!(!screen.contains("hidden-beta"));
        assert!(screen.contains("visible-charlie"));
        assert!(screen.contains("visible-juliet"));
        let rows = screen.lines().collect::<Vec<_>>();
        assert!(rows[25].starts_with('─'));
        assert!(rows[34].starts_with('─'));
    }

    #[test]
    fn composer_height_tracks_explicit_new_lines() {
        let mut app = App::new_for_test(None).expect("app");
        assert_eq!(visible_composer_lines(&app), 1);

        app.input_buffer = "first\nsecond".to_string();
        app.input_cursor = app.input_buffer.chars().count();
        assert_eq!(visible_composer_lines(&app), 2);

        let screen = rendered_screen(&mut app, 120, 36);
        let rows = screen.lines().collect::<Vec<_>>();
        assert!(rows[31].starts_with('─'));
        assert!(rows[34].starts_with('─'));
        assert!(screen.contains("first"));
        assert!(screen.contains("second"));
    }

    #[test]
    fn composer_only_shows_tools_that_are_still_running() {
        let mut app = App::new_for_test(None).expect("app");
        for id in 1..=5 {
            app.dummy_run
                .start_tool_process(id, format!("klint-test-command-{id}"));
        }
        app.dummy_run.finish_tool_process(1, true, Some(0));
        app.dummy_run.finish_tool_process(2, false, Some(9));
        app.dummy_run.finish_tool_process(5, false, Some(17));

        let main = rendered_screen(&mut app, 120, 36);
        if !main.contains("klint-test-command-3") {
            panic!("main does not contain klint-test-command-3: \n{}", main);
        }
        assert!(!main.contains("klint-test-command-1"));
        assert!(!main.contains("klint-test-command-2"));
        assert!(main.contains("klint-test-command-3"));
        assert!(main.contains("klint-test-command-4"));
        assert!(!main.contains("klint-test-command-5"));
        assert!(!main.contains("exit 17"));

        app.view = View::Stats;
        let stats = rendered_screen(&mut app, 120, 36);
        assert!(stats.contains(" Tools"));
        assert!(stats.contains("klint-test-command-1"));
        assert!(stats.contains("klint-test-command-5"));
        assert!(!stats.contains("exit 17"));
    }
}
