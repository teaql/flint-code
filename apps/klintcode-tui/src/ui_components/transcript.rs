use crate::ui_components::helpers::*;
use ratatui::{
    Frame,
    layout::{Alignment, Rect},
    style::{Color, Modifier, Style},
    text::{Line, Span},
    widgets::{Clear, Paragraph, Wrap},
};
use agent_core::state::PipelineState;
use crate::app::{App, TimelineRole};

pub const KLINT_TEXT_MARK: [&str; 3] = [
    " ██▀ █   █ █▄ █ ▀█▀",
    " █▀  █   █ █ ▀█  █",
    " ▀   ▀▀▀ ▀ ▀  ▀  ▀",
];
pub const KLINT_SLOGAN: &str = "Built for coding where networks can't reach.";

pub fn draw_transcript(f: &mut Frame, app: &App, area: Rect) {
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
    }
    let mut line_to_entry: Vec<Option<usize>> = vec![None; lines.len()];

    if !app.timeline.is_empty() {
        for (i, entry) in app.timeline.iter().enumerate() {
            let start_len = lines.len();
            if i > 0 {
                match entry.role {
                    TimelineRole::User | TimelineRole::Agent => {
                        lines.push(Line::from(""));
                    }
                    _ => {}
                }
            }
            let (marker, color) = match entry.role {
                TimelineRole::User => ("❯", Color::White),
                TimelineRole::Agent => ("●", Color::Cyan),
                TimelineRole::Activity => ("⎿", Color::DarkGray),
                TimelineRole::Success => ("✓", Color::Green),
                TimelineRole::Error => ("✗", Color::Red),
            };
            let content_lines: Vec<&str> = entry.content.lines().collect();
            let mut display_lines: Vec<String> = content_lines.into_iter().map(|s| s.to_string()).collect();
            let mut remaining_chars = 0;
            let mut truncated = false;
            
            match entry.role {
                TimelineRole::Activity | TimelineRole::Success | TimelineRole::Error => {
                    let total_chars = entry.content.chars().count();
                    if display_lines.len() > 1 {
                        display_lines.truncate(1);
                        truncated = true;
                    }
                    if let Some(line) = display_lines.first_mut() {
                        if line.chars().count() > 188 {
                            if let Some((byte_idx, _)) = line.char_indices().nth(185) {
                                line.truncate(byte_idx);
                                line.push_str("...");
                                truncated = true;
                            }
                        }
                    }
                    if truncated {
                        let displayed_chars = display_lines.first().map(|s| {
                            if s.ends_with("...") {
                                s.chars().count().saturating_sub(3)
                            } else {
                                s.chars().count()
                            }
                        }).unwrap_or(0);
                        remaining_chars = total_chars.saturating_sub(displayed_chars);
                    }
                }
                _ => {}
            }

            for (index, content_line) in display_lines.iter().enumerate() {
                lines.push(Line::from(vec![
                    Span::styled(
                        if index == 0 {
                            match entry.role {
                                TimelineRole::Activity | TimelineRole::Success | TimelineRole::Error => {
                                    format!("   {marker} ")
                                }
                                _ => format!(" {marker} ")
                            }
                        } else {
                            match entry.role {
                                TimelineRole::Activity | TimelineRole::Success | TimelineRole::Error => {
                                    "     ".to_string()
                                }
                                _ => "   ".to_string()
                            }
                        },
                        Style::default().fg(color).add_modifier(if index == 0 {
                            Modifier::BOLD
                        } else {
                            Modifier::empty()
                        }),
                    ),
                    Span::styled(content_line.to_string(), Style::default().fg(color)),
                ]));
            }
            
            if truncated {
                lines.push(Line::from(vec![
                    Span::styled("     ", Style::default().fg(color)),
                    Span::styled(format!("... <truncated {} chars>", remaining_chars), Style::default().fg(color).add_modifier(Modifier::DIM)),
                    Span::styled(format!(" [/detail {}]", i), Style::default().fg(Color::DarkGray)),
                ]));
            }
            
            let end_len = lines.len();
            for _ in start_len..end_len {
                line_to_entry.push(Some(i));
            }
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

    let mut current_y = 0;
    let mut entry_spans: std::collections::BTreeMap<usize, (u16, u16)> = std::collections::BTreeMap::new();

    for (idx, line) in lines.iter().enumerate() {
        let height = wrapped_line_count(std::slice::from_ref(line), transcript_area.width) as u16;
        let entry_id = line_to_entry.get(idx).copied().flatten();
        if let Some(id) = entry_id {
            let span = entry_spans.entry(id).or_insert((current_y, current_y));
            span.1 = current_y + height;
        }
        current_y += height;
    }

    let rendered_line_count = current_y as usize;
    let paragraph = Paragraph::new(lines).wrap(Wrap { trim: false });
    let max_scroll = rendered_line_count
        .saturating_sub(usize::from(transcript_area.height))
        .min(usize::from(u16::MAX)) as u16;
    let browse_back = app.transcript_scroll_back.min(max_scroll);
    let scroll = max_scroll.saturating_sub(browse_back);
    f.render_widget(paragraph.scroll((scroll, 0)), transcript_area);

    let mut layout = Vec::new();
    for (id, (start_y, end_y)) in entry_spans {
        if end_y > scroll && start_y < scroll + transcript_area.height {
            let screen_start = transcript_area.y + start_y.saturating_sub(scroll);
            let screen_end = transcript_area.y + end_y.saturating_sub(scroll);
            layout.push((screen_start, screen_end, id));
        }
    }
    *app.transcript_layout.borrow_mut() = layout;

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

pub fn wrapped_line_count(lines: &[Line<'_>], width: u16) -> usize {
    let width = usize::from(width.max(1));
    lines
        .iter()
        .map(|line| {
            // Get the raw text of the line to simulate word wrapping
            let mut raw_text = String::new();
            for span in &line.spans {
                raw_text.push_str(span.content.as_ref());
            }
            
            let mut line_count = 1;
            let mut current_width = 0;
            
            for word in raw_text.split_whitespace() {
                let word_len = word.chars().count();
                if current_width + word_len + (if current_width > 0 { 1 } else { 0 }) > width {
                    if word_len > width {
                        line_count += word_len.div_ceil(width);
                        current_width = word_len % width;
                    } else {
                        line_count += 1;
                        current_width = word_len;
                    }
                } else {
                    if current_width > 0 {
                        current_width += 1;
                    }
                    current_width += word_len;
                }
            }
            line_count
        })
        .sum()
}

