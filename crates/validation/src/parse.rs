//! L1: File format parsing — XML parse check

use agent_core::event::ValidationResult;

/// Validate that the candidate content is well-formed XML.
pub fn validate_xml_parse(content: &str) -> ValidationResult {
    let start = std::time::Instant::now();

    if content.trim().is_empty() {
        return super::fail(
            1,
            "parse",
            vec!["Candidate is empty; expected one XML root element".to_string()],
            "Candidate is empty; expected one XML root element".to_string(),
            start.elapsed().as_secs_f64(),
        );
    }
    if content.trim_start().starts_with("```") {
        return super::fail(
            1,
            "parse",
            vec!["Candidate contains a Markdown fence; output raw XML only".to_string()],
            "Candidate contains a Markdown fence; output raw XML only".to_string(),
            start.elapsed().as_secs_f64(),
        );
    }

    let mut buf = Vec::new();
    let mut reader = quick_xml::Reader::from_str(content);
    reader.config_mut().check_end_names = true;
    let mut errors = Vec::new();
    let mut depth = 0_u32;
    let mut root_count = 0_u32;

    loop {
        match reader.read_event_into(&mut buf) {
            Ok(quick_xml::events::Event::Eof) => break,
            Ok(quick_xml::events::Event::Start(_)) => {
                if depth == 0 {
                    root_count += 1;
                }
                depth += 1;
            }
            Ok(quick_xml::events::Event::Empty(_)) => {
                if depth == 0 {
                    root_count += 1;
                }
            }
            Ok(quick_xml::events::Event::End(_)) => {
                depth = depth.saturating_sub(1);
            }
            Ok(_) => {}
            Err(e) => {
                errors.push(format!(
                    "XML parse error at position {}: {e}",
                    reader.error_position()
                ));
            }
        }
        buf.clear();
    }

    if root_count != 1 {
        errors.push(format!(
            "Expected exactly one XML root element, found {root_count}"
        ));
    }

    let elapsed = start.elapsed().as_secs_f64();
    if errors.is_empty() {
        super::pass(1, "parse", elapsed)
    } else {
        let diagnostic = errors.join("\n");
        super::fail(1, "parse", errors, diagnostic, elapsed)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn natural_language_is_not_a_valid_xml_candidate() {
        let result = validate_xml_parse("I am an AI assistant.");

        assert!(!result.passed);
        assert_eq!(result.error_count, 1);
        assert!(result.diagnostic.contains("one XML root"));
    }

    #[test]
    fn markdown_wrapped_xml_is_rejected() {
        let result = validate_xml_parse("```xml\n<root />\n```");

        assert!(!result.passed);
        assert!(result.diagnostic.contains("Markdown fence"));
    }

    #[test]
    fn one_raw_xml_root_is_accepted() {
        assert!(validate_xml_parse("<?xml version=\"1.0\"?><root />").passed);
    }
}
