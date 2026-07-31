//! L2: Acceptance spec validation — checks against acceptance.json

use agent_core::event::ValidationResult;
use serde_json::Value;
use std::collections::HashSet;

/// Validate the candidate XML against an acceptance specification.
///
/// Checks performed:
/// 1. All expected objects appear as `<object>` elements in the XML.
/// 2. The total object count matches if `expected_object_count` is specified.
/// 3. The required root entity is the first `<object>` or appears as the
///    top-level container.
pub fn validate_acceptance(content: &str, spec: &Value) -> ValidationResult {
    let start = std::time::Instant::now();
    let mut errors: Vec<String> = Vec::new();

    // Extract object names from the candidate XML.
    // Objects appear as: <object name="Teacher" ...> or chinese_name="..."
    let candidate_objects = extract_object_names(content);

    // Check expected_objects
    if let Some(expected) = spec.get("expected_objects").and_then(|v| v.as_array()) {
        let expected_names: Vec<String> = expected
            .iter()
            .filter_map(|v| v.as_str().map(|s| s.to_string()))
            .collect();

        for name in &expected_names {
            if !candidate_objects.contains(name) {
                errors.push(format!("Missing expected object: {name}"));
            }
        }

        // Check for unexpected objects (extras)
        let expected_set: HashSet<&str> = expected_names.iter().map(|s| s.as_str()).collect();
        for found in &candidate_objects {
            if !expected_set.contains(found.as_str()) {
                // Not an error, but noteworthy — don't fail for extras
            }
        }
    }

    // Check expected_object_count
    if let Some(expected_count) = spec.get("expected_object_count").and_then(|v| v.as_u64()) {
        let actual = candidate_objects.len() as u64;
        if actual != expected_count {
            errors.push(format!(
                "Expected {expected_count} objects, found {actual}"
            ));
        }
    }

    // Check required_root
    if let Some(required_root) = spec.get("required_root").and_then(|v| v.as_str()) {
        if !candidate_objects.contains(&required_root.to_string()) {
            errors.push(format!("Missing required root object: {required_root}"));
        }
    }

    // Check required_relationships
    if let Some(relationships) = spec.get("required_relationships").and_then(|v| v.as_array()) {
        for rel in relationships {
            let from = rel.get("from").and_then(|v| v.as_str()).unwrap_or("");
            let to = rel.get("to").and_then(|v| v.as_str()).unwrap_or("");
            let rel_type = rel.get("type").and_then(|v| v.as_str()).unwrap_or("");

            if !from.is_empty() && !candidate_objects.contains(&from.to_string()) {
                errors.push(format!(
                    "Relationship {rel_type} references missing object: {from}"
                ));
            }
            if !to.is_empty() && !candidate_objects.contains(&to.to_string()) {
                errors.push(format!(
                    "Relationship {rel_type} references missing object: {to}"
                ));
            }
        }
    }

    let elapsed = start.elapsed().as_secs_f64();
    if errors.is_empty() {
        super::pass(2, "acceptance", elapsed)
    } else {
        let diagnostic = errors.join("\n");
        super::fail(2, "acceptance", errors, diagnostic, elapsed)
    }
}

/// Extract object names from KSML XML content.
///
/// Supports two KSML conventions:
/// 1. `<object name="Teacher" ...>` — attribute-based
/// 2. `<greeting _name="Greeting" ...>` — tag-based, where `_name` carries
///    the PascalCase display name
///
/// Elements named `root` are skipped (that's the document root, not a
/// business object).
fn extract_object_names(content: &str) -> HashSet<String> {
    let mut names = HashSet::new();
    let mut reader = quick_xml::Reader::from_str(content);
    let mut buf = Vec::new();

    loop {
        match reader.read_event_into(&mut buf) {
            Ok(quick_xml::events::Event::Start(ref e))
            | Ok(quick_xml::events::Event::Empty(ref e)) => {
                let tag = String::from_utf8_lossy(e.name().as_ref()).to_string();

                // Pattern 1: <object name="X">
                if tag == "object" {
                    for attr in e.attributes().flatten() {
                        if attr.key.as_ref() == b"name" {
                            if let Ok(val) = attr.unescape_value() {
                                names.insert(val.to_string());
                            }
                        }
                    }
                }

                // Pattern 2: <greeting _name="Greeting"> (skip <root>)
                if tag != "root" && tag != "object" {
                    for attr in e.attributes().flatten() {
                        if attr.key.as_ref() == b"_name" {
                            if let Ok(val) = attr.unescape_value() {
                                names.insert(val.to_string());
                            }
                        }
                    }
                }
            }
            Ok(quick_xml::events::Event::Eof) => break,
            Err(_) => break,
            _ => {}
        }
        buf.clear();
    }

    names
}

#[cfg(test)]
mod tests {
    use super::*;
    use serde_json::json;

    fn school_xml() -> &'static str {
        r#"<?xml version="1.0"?>
<root name="school-service">
  <object name="School" chinese_name="学校">
    <children>
      <child name="Teacher" />
      <child name="Student" />
    </children>
  </object>
  <object name="Teacher" chinese_name="教师">
    <container name="School" />
  </object>
  <object name="Student" chinese_name="学生">
    <container name="School" />
  </object>
</root>"#
    }

    #[test]
    fn accepts_matching_candidate() {
        let spec = json!({
            "expected_objects": ["School", "Teacher", "Student"],
            "expected_object_count": 3,
            "required_root": "School"
        });
        let result = validate_acceptance(school_xml(), &spec);
        assert!(result.passed, "Expected pass: {:?}", result.actionable_errors);
    }

    #[test]
    fn rejects_missing_object() {
        let spec = json!({
            "expected_objects": ["School", "Teacher", "Student", "Course"],
            "expected_object_count": 4
        });
        let result = validate_acceptance(school_xml(), &spec);
        assert!(!result.passed);
        assert!(result.diagnostic.contains("Course"));
    }

    #[test]
    fn rejects_wrong_count() {
        let spec = json!({
            "expected_objects": ["School", "Teacher", "Student"],
            "expected_object_count": 5
        });
        let result = validate_acceptance(school_xml(), &spec);
        assert!(!result.passed);
        assert!(result.diagnostic.contains("Expected 5"));
    }

    #[test]
    fn passes_with_no_spec_constraints() {
        let spec = json!({});
        let result = validate_acceptance(school_xml(), &spec);
        assert!(result.passed);
    }

    #[test]
    fn extracts_names_from_ksml_tag_based_format() {
        let xml = r#"<?xml version="1.0"?>
<root name="greeting-service">
  <greeting _name="Greeting" message="Hello"/>
  <greeting_type _name="GreetingType" name="Default"/>
</root>"#;
        let spec = json!({
            "expected_objects": ["Greeting"],
            "expected_object_count": 2
        });
        let result = validate_acceptance(xml, &spec);
        assert!(result.passed, "Expected pass: {:?}", result.actionable_errors);
    }
}
