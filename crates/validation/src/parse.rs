//! L1: File format parsing — XML parse check

use agent_core::event::ValidationResult;
use quick_xml::events::{BytesStart, Event};
use std::collections::{HashMap, HashSet};

/// Validate that the candidate content is well-formed XML.
pub fn validate_xml_parse(content: &str) -> ValidationResult {
    let start = std::time::Instant::now();

    let mut buf = Vec::new();
    let mut reader = quick_xml::Reader::from_str(content);
    reader.config_mut().check_end_names = true;
    let mut errors = Vec::new();

    loop {
        match reader.read_event_into(&mut buf) {
            Ok(quick_xml::events::Event::Eof) => break,
            Ok(_) => {}
            Err(e) => {
                errors.push(format!(
                    "XML parse error at position {}: {e}",
                    reader.error_position()
                ));
                break;
            }
        }
        buf.clear();
    }

    let elapsed = start.elapsed().as_secs_f64();
    if errors.is_empty() {
        super::pass(1, "parse", elapsed)
    } else {
        let diagnostic = errors.join("\n");
        super::fail(1, "parse", errors, diagnostic, elapsed)
    }
}

/// Validate every generated KSML file and reject duplicate top-level objects.
///
/// TeaQL models may be split with `_include`, but each business or constant
/// object must be declared exactly once across the complete file set.
pub fn validate_xml_model_files(files: &[(String, String)]) -> ValidationResult {
    let start = std::time::Instant::now();
    let mut errors = Vec::new();
    let mut declarations: HashMap<String, (String, String)> = HashMap::new();
    let available_files: HashSet<&str> = files.iter().map(|(name, _)| name.as_str()).collect();

    for (file_name, content) in files {
        let mut reader = quick_xml::Reader::from_str(content);
        reader.config_mut().check_end_names = true;
        let mut buf = Vec::new();
        let mut depth = 0usize;

        loop {
            match reader.read_event_into(&mut buf) {
                Ok(Event::Start(element)) => {
                    if depth == 1 {
                        record_top_level_object(
                            &element,
                            file_name,
                            &mut declarations,
                            &mut errors,
                        );
                    }
                    depth += 1;
                }
                Ok(Event::Empty(element)) => {
                    if depth == 1 {
                        if element.name().as_ref() == b"_include" {
                            validate_include(&element, file_name, &available_files, &mut errors);
                        } else {
                            record_top_level_object(
                                &element,
                                file_name,
                                &mut declarations,
                                &mut errors,
                            );
                        }
                    }
                }
                Ok(Event::End(_)) => depth = depth.saturating_sub(1),
                Ok(Event::Eof) => break,
                Ok(_) => {}
                Err(error) => {
                    errors.push(format!(
                        "XML parse error in {file_name} at position {}: {error}",
                        reader.error_position()
                    ));
                    break;
                }
            }
            buf.clear();
        }
    }

    let elapsed = start.elapsed().as_secs_f64();
    if errors.is_empty() {
        super::pass(1, "parse", elapsed)
    } else {
        let diagnostic = errors.join("\n");
        super::fail(1, "parse", errors, diagnostic, elapsed)
    }
}

fn record_top_level_object(
    element: &BytesStart<'_>,
    file_name: &str,
    declarations: &mut HashMap<String, (String, String)>,
    errors: &mut Vec<String>,
) {
    let tag = String::from_utf8_lossy(element.name().as_ref()).into_owned();
    if tag.starts_with('_') {
        return;
    }

    let display_name = attribute_value(element, b"_name").unwrap_or_else(|| tag.clone());
    let key = display_name.to_lowercase();
    if let Some((first_file, first_tag)) = declarations.get(&key) {
        errors.push(format!(
            "Duplicate KSML object '{display_name}' (<{tag}>) declared in {first_file} as <{first_tag}> and again in {file_name}; declare each object exactly once across included files"
        ));
    } else {
        declarations.insert(key, (file_name.to_string(), tag));
    }
}

fn validate_include(
    element: &BytesStart<'_>,
    file_name: &str,
    available_files: &HashSet<&str>,
    errors: &mut Vec<String>,
) {
    if let Some(included) = attribute_value(element, b"file")
        && !available_files.contains(included.as_str())
    {
        errors.push(format!(
            "KSML include '{included}' referenced by {file_name} was not generated"
        ));
    }
}

fn attribute_value(element: &BytesStart<'_>, key: &[u8]) -> Option<String> {
    element
        .attributes()
        .flatten()
        .find(|attribute| attribute.key.as_ref() == key)
        .map(|attribute| String::from_utf8_lossy(attribute.value.as_ref()).into_owned())
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn malformed_xml_returns_one_error_instead_of_looping() {
        let result = validate_xml_parse("<root><child></root>");
        assert!(!result.passed);
        assert_eq!(result.error_count, 1);
    }

    #[test]
    fn duplicate_objects_across_includes_are_rejected() {
        let files = vec![
            (
                "main.xml".to_string(),
                r#"<root><_include file="platform.xml"/><_include file="school.xml"/></root>"#
                    .to_string(),
            ),
            (
                "platform.xml".to_string(),
                r#"<root><school_type _name="School Type" _constant="true"/></root>"#
                    .to_string(),
            ),
            (
                "school.xml".to_string(),
                r#"<root><school_type _name="School Type" _constant="true"/><school _name="School"/></root>"#
                    .to_string(),
            ),
        ];

        let result = validate_xml_model_files(&files);

        assert!(!result.passed);
        assert_eq!(result.error_count, 1);
        assert!(result.actionable_errors[0].contains("Duplicate KSML object 'School Type'"));
        assert!(result.actionable_errors[0].contains("platform.xml"));
        assert!(result.actionable_errors[0].contains("school.xml"));
    }

    #[test]
    fn missing_included_file_is_rejected() {
        let files = vec![(
            "main.xml".to_string(),
            r#"<root><_include file="missing.xml"/></root>"#.to_string(),
        )];

        let result = validate_xml_model_files(&files);

        assert!(!result.passed);
        assert!(result.actionable_errors[0].contains("was not generated"));
    }
}
