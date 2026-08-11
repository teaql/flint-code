use crate::app::{App, TimelineRole, TranscriptHitbox};
use crate::ui_components::helpers::*;
use agent_core::state::PipelineState;
use ratatui::{
    Frame,
    layout::{Alignment, Rect},
    style::{Color, Modifier, Style},
    text::{Line, Span},
    widgets::{Clear, Paragraph},
};

pub const KLINT_TEXT_MARK: [&str; 3] = [
    " ██▀ █   █ █▄ █ ▀█▀",
    " █▀  █   █ █ ▀█  █",
    " ▀   ▀▀▀ ▀ ▀  ▀  ▀",
];
pub const KLINT_SLOGAN: &str = "Built for coding where networks can't reach.";

pub fn draw_transcript(f: &mut Frame, app: &mut App, area: Rect) {
    let mut lines = if app.timeline.is_empty() {
        let mut header = KLINT_TEXT_MARK
            .iter()
            .map(|line| {
                Line::from(Span::styled(
                    *line,
                    Style::default()
                        .fg(Color::Cyan)
                        .add_modifier(Modifier::BOLD),
                ))
            })
            .collect::<Vec<_>>();
        header.extend([
            Line::from(vec![
                Span::styled(" flint code", Style::default().fg(Color::Cyan)),
                Span::styled("  coding agent", Style::default().fg(Color::DarkGray)),
            ]),
            Line::from(Span::styled(
                format!(" {KLINT_SLOGAN}"),
                Style::default()
                    .fg(Color::Yellow)
                    .add_modifier(Modifier::ITALIC),
            )),
            Line::from(Span::styled(
                " Built by TeaQL",
                Style::default().fg(Color::DarkGray),
            )),
            Line::from(""),
        ]);
        header
    } else {
        vec![]
    };

    if app.timeline.is_empty() {
        lines.push(Line::from(Span::styled(
            "  Describe a task and I will inspect the project and begin.",
            Style::default().fg(Color::DarkGray),
        )));
        lines.push(Line::from(Span::styled(
            "  Use /stats to inspect the plan, validation, and tokens.",
            Style::default().fg(Color::DarkGray),
        )));
    } else {
        let mut entry_lines = Vec::with_capacity(app.timeline.len());
        for (i, entry) in app.timeline.iter().enumerate() {
            let (marker, color) = match entry.role {
                TimelineRole::User => ("❯", Color::White),
                TimelineRole::Agent => ("●", Color::Cyan),
                TimelineRole::Activity => ("⎿", Color::DarkGray),
                TimelineRole::Success => ("✓", Color::Green),
                TimelineRole::Error => ("✗", Color::Red),
            };
            let id = i + 1;
            let prefix = format!(" [T{id:03}] {marker} ");
            let preview_width = usize::from(area.width).saturating_sub(prefix.chars().count());
            let preview = one_line_preview(&entry.content, preview_width);
            entry_lines.push((id, lines.len()));
            lines.push(Line::from(vec![
                Span::styled(
                    prefix,
                    Style::default().fg(color).add_modifier(Modifier::BOLD),
                ),
                Span::styled(preview, Style::default().fg(color)),
            ]));
        }

        app.transcript_hitboxes.clear();
        app.transcript_hitboxes.reserve(entry_lines.len());
        // Hitboxes are populated after the final scroll offset is known.
        for (id, line) in entry_lines {
            app.transcript_hitboxes.push(TranscriptHitbox {
                id,
                row: line.min(usize::from(u16::MAX)) as u16,
                left: area.x,
                right: area.right(),
            });
        }
    }

    let run = app.run_state();
    if app.chat_in_flight {
        lines.push(Line::from(vec![
            Span::styled(" ● ", Style::default().fg(Color::Cyan)),
            Span::styled("Answering", Style::default().fg(Color::Cyan)),
        ]));
    }
    if run.state.is_active() {
        let (state_icon, label, color) = state_presentation(&run.state);
        let action = run
            .current_plan_step()
            .and_then(|(_, step)| step.detail.as_deref())
            .unwrap_or_else(|| run.state.label());
        lines.push(Line::from(vec![
            Span::styled(format!(" {state_icon} "), Style::default().fg(color)),
            Span::styled(format!("{label} · {action}"), Style::default().fg(color)),
        ]));
        if matches!(run.state, PipelineState::AwaitingConsent { .. }) {
            lines.push(Line::from(vec![
                Span::styled("   [y]", Style::default().fg(Color::Green)),
                Span::raw(" Allow  "),
                Span::styled("[n]", Style::default().fg(Color::Red)),
                Span::raw(" Deny"),
            ]));
        }
    }

    let transcript_area = area;

    let rendered_line_count = lines.len();
    let paragraph = Paragraph::new(lines);
    let max_scroll = rendered_line_count
        .saturating_sub(usize::from(transcript_area.height))
        .min(usize::from(u16::MAX)) as u16;
    let browse_back = app.transcript_scroll_back.min(max_scroll);
    let scroll = max_scroll.saturating_sub(browse_back);
    app.transcript_hitboxes.retain_mut(|hitbox| {
        let logical_row = hitbox.row;
        if logical_row < scroll || logical_row >= scroll.saturating_add(transcript_area.height) {
            return false;
        }
        hitbox.row = transcript_area.y + logical_row - scroll;
        true
    });
    f.render_widget(paragraph.scroll((scroll, 0)), transcript_area);

    if browse_back > 0 && transcript_area.height > 0 {
        let indicator_width = transcript_area.width.min(30);
        let indicator_area = Rect::new(
            transcript_area.right().saturating_sub(indicator_width),
            transcript_area.bottom().saturating_sub(1),
            indicator_width,
            1,
        );
        f.render_widget(Clear, indicator_area);
        f.render_widget(
            Paragraph::new(Line::from(Span::styled(
                format!("↓ Latest · j · {browse_back} lines above"),
                Style::default()
                    .fg(Color::Cyan)
                    .add_modifier(Modifier::BOLD),
            )))
            .alignment(Alignment::Right),
            indicator_area,
        );
    }
}

pub fn one_line_preview(content: &str, max_chars: usize) -> String {
    if max_chars == 0 {
        return String::new();
    }

    let total_chars = content.chars().count();
    let first_line = content.lines().next().unwrap_or("");
    let first_line_chars = first_line.chars().count();
    let mut shown = first_line_chars.min(max_chars);

    loop {
        let omitted = total_chars.saturating_sub(shown);
        if omitted == 0 {
            return first_line.chars().take(shown).collect();
        }
        let suffix = format!(" … {omitted} chars omitted");
        let allowed = max_chars.saturating_sub(suffix.chars().count());
        let next_shown = first_line_chars.min(allowed);
        if next_shown == shown {
            let mut preview = first_line.chars().take(shown).collect::<String>();
            preview.push_str(&suffix);
            return preview.chars().take(max_chars).collect();
        }
        shown = next_shown;
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn multiline_preview_reports_exact_omitted_characters() {
        let preview = one_line_preview("alpha\nbeta", 40);

        assert_eq!(preview, "alpha … 5 chars omitted");
        assert!(!preview.contains('\n'));
    }

    #[test]
    fn long_single_line_is_bounded() {
        let preview = one_line_preview(&"x".repeat(200), 32);

        assert_eq!(preview.chars().count(), 32);
        assert!(preview.contains("chars omitted"));
    }
}
