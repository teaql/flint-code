//! L2: Acceptance spec validation — checks against acceptance.json

use agent_core::event::ValidationResult;
use quick_xml::events::Event;
use serde_json::Value;
use std::collections::HashSet;

/// Validate candidate KSML against deterministic structural constraints.
///
/// `expected_objects` requires every listed object to exist but permits extra
/// objects. `expected_object_count` is an exact count, while
/// `min_object_count` is a lower bound.
pub fn validate_acceptance(content: &str, spec: &Value) -> ValidationResult {
    let start = std::time::Instant::now();
    let candidate_objects = extract_object_names(content);
    validate_object_constraints(candidate_objects, spec, start)
}

/// Validate a KSML model split across a main file and `_include` files.
///
/// Object declarations from every supplied file are aggregated before count
/// and expected-name constraints are evaluated. XML well-formedness and
/// duplicate declarations remain the responsibility of L1 validation.
pub fn validate_acceptance_model_files(
    files: &[(String, String)],
    spec: &Value,
) -> ValidationResult {
    let start = std::time::Instant::now();
    let candidate_objects = extract_model_object_names(files);
    validate_object_constraints(candidate_objects, spec, start)
}

/// Count business-object declarations across all files in a KSML model.
///
/// Callers should run L1 model-file validation first when duplicate object
/// declarations need to be rejected rather than counted.
pub fn count_model_objects(files: &[(String, String)]) -> usize {
    extract_model_object_names(files).len()
}

fn extract_model_object_names(files: &[(String, String)]) -> Vec<ObjectName> {
    files
        .iter()
        .flat_map(|(_, content)| extract_object_names(content))
        .collect()
}

fn validate_object_constraints(
    candidate_objects: Vec<ObjectName>,
    spec: &Value,
    start: std::time::Instant,
) -> ValidationResult {
    let mut errors = validate_spec_shape(spec);

    if let Some(expected) = spec.get("expected_objects").and_then(Value::as_array) {
        for name in expected.iter().filter_map(Value::as_str) {
            if !contains_object(&candidate_objects, name) {
                errors.push(format!("Missing expected object: {name}"));
            }
        }
    }

    let actual_count = candidate_objects.len() as u64;
    if let Some(expected_count) = spec.get("expected_object_count").and_then(Value::as_u64)
        && actual_count != expected_count
    {
        errors.push(format!(
            "Expected exactly {expected_count} objects, found {actual_count}"
        ));
    }

    if let Some(minimum_count) = spec.get("min_object_count").and_then(Value::as_u64)
        && actual_count < minimum_count
    {
        errors.push(format!(
            "Expected at least {minimum_count} objects, found {actual_count}"
        ));
    }

    if let Some(required_root) = spec.get("required_root").and_then(Value::as_str) {
        if let Some(root) = find_object(&candidate_objects, required_root) {
            let referenced_business_objects = root
                .references
                .iter()
                .filter(|reference| contains_object(&candidate_objects, reference))
                .collect::<Vec<_>>();
            if !referenced_business_objects.is_empty() {
                errors.push(format!(
                    "Required root object `{required_root}` contains parent/container references: {}",
                    referenced_business_objects
                        .iter()
                        .map(|reference| reference.as_str())
                        .collect::<Vec<_>>()
                        .join(", ")
                ));
            }
        } else {
            errors.push(format!("Missing required root object: {required_root}"));
        }
    }

    if let Some(relationships) = spec.get("required_relationships").and_then(Value::as_array) {
        for relationship in relationships {
            let Some(from) = relationship.get("from").and_then(Value::as_str) else {
                continue;
            };
            let Some(to) = relationship.get("to").and_then(Value::as_str) else {
                continue;
            };
            let Some(kind) = relationship.get("type").and_then(Value::as_str) else {
                continue;
            };
            let present = match kind {
                // TeaQL models represent a parent's children through the
                // inverse of each child's container reference.
                "children" => object_references(&candidate_objects, to, from),
                "container" => object_references(&candidate_objects, from, to),
                _ => object_references(&candidate_objects, from, to),
            };
            if !present {
                errors.push(format!(
                    "Missing required relationship: {from} -> {to} ({kind})"
                ));
            }
        }
    }

    let elapsed_secs = start.elapsed().as_secs_f64();
    if errors.is_empty() {
        super::pass(2, "acceptance", elapsed_secs)
    } else {
        let diagnostic = errors.join("\n");
        super::fail(2, "acceptance", errors, diagnostic, elapsed_secs)
    }
}

fn validate_spec_shape(spec: &Value) -> Vec<String> {
    const KNOWN_KEYS: &[&str] = &[
        "schema",
        "expected_objects",
        "expected_object_count",
        "min_object_count",
        "forbidden_errors",
        "build_targets",
        "required_root",
        "required_relationships",
        "expected_result",
    ];
    let Some(object) = spec.as_object() else {
        return vec!["Acceptance spec must be a JSON object".to_string()];
    };
    let mut errors = Vec::new();
    for key in object.keys() {
        if !KNOWN_KEYS.contains(&key.as_str()) {
            errors.push(format!("Unknown acceptance field: {key}"));
        }
    }
    if let Some(schema) = object.get("schema")
        && schema.as_str() != Some("ksml-acceptance-v1")
    {
        errors.push("Acceptance schema must be `ksml-acceptance-v1`".to_string());
    }
    validate_string_array(
        object.get("expected_objects"),
        "expected_objects",
        &mut errors,
    );
    validate_string_array(
        object.get("forbidden_errors"),
        "forbidden_errors",
        &mut errors,
    );
    validate_string_array(object.get("build_targets"), "build_targets", &mut errors);
    validate_optional_u64(
        object.get("expected_object_count"),
        "expected_object_count",
        &mut errors,
    );
    validate_optional_u64(
        object.get("min_object_count"),
        "min_object_count",
        &mut errors,
    );
    if object
        .get("required_root")
        .is_some_and(|value| !value.is_string())
    {
        errors.push("Acceptance field `required_root` must be a string".to_string());
    }
    if object
        .get("expected_result")
        .is_some_and(|value| !value.is_string())
    {
        errors.push("Acceptance field `expected_result` must be a string".to_string());
    }
    if let Some(relationships) = object.get("required_relationships") {
        match relationships.as_array() {
            Some(relationships) => {
                for (index, relationship) in relationships.iter().enumerate() {
                    let Some(relationship) = relationship.as_object() else {
                        errors.push(format!("required_relationships[{index}] must be an object"));
                        continue;
                    };
                    for field in ["from", "to", "type"] {
                        if !relationship.get(field).is_some_and(Value::is_string) {
                            errors.push(format!(
                                "required_relationships[{index}].{field} must be a string"
                            ));
                        }
                    }
                    if relationship
                        .get("type")
                        .and_then(Value::as_str)
                        .is_some_and(|kind| !matches!(kind, "children" | "container"))
                    {
                        errors.push(format!(
                            "required_relationships[{index}].type must be `children` or `container`"
                        ));
                    }
                    for field in relationship.keys() {
                        if !matches!(field.as_str(), "from" | "to" | "type") {
                            errors.push(format!(
                                "Unknown required_relationships[{index}] field: {field}"
                            ));
                        }
                    }
                }
            }
            None => errors
                .push("Acceptance field `required_relationships` must be an array".to_string()),
        }
    }
    if let (Some(exact), Some(minimum)) = (
        object.get("expected_object_count").and_then(Value::as_u64),
        object.get("min_object_count").and_then(Value::as_u64),
    ) && exact < minimum
    {
        errors.push(format!(
            "expected_object_count ({exact}) cannot be below min_object_count ({minimum})"
        ));
    }
    errors
}

fn validate_string_array(value: Option<&Value>, field: &str, errors: &mut Vec<String>) {
    let Some(value) = value else {
        return;
    };
    if !value
        .as_array()
        .is_some_and(|items| items.iter().all(Value::is_string))
    {
        errors.push(format!(
            "Acceptance field `{field}` must be an array of strings"
        ));
    }
}

fn validate_optional_u64(value: Option<&Value>, field: &str, errors: &mut Vec<String>) {
    if value.is_some_and(|value| value.as_u64().is_none()) {
        errors.push(format!(
            "Acceptance field `{field}` must be a non-negative integer"
        ));
    }
}

/// Convert forbidden TeaQL diagnostics into domain validation failures.
///
/// This function is deterministic and has no side effects. It is intended to
/// be called after domain validation, because `forbidden_errors` may promote a
/// TeaQL warning (which normally passes L3) to an error required by the task's
/// acceptance specification.
pub fn enforce_forbidden_domain_errors(
    mut domain_result: ValidationResult,
    spec: &Value,
) -> ValidationResult {
    let violations = matching_forbidden_errors(spec, &domain_result.diagnostic);
    if violations.is_empty() {
        return domain_result;
    }

    let violations = violations
        .into_iter()
        .filter(|code| {
            !domain_result
                .actionable_errors
                .iter()
                .any(|error| diagnostic_contains_code(error, code))
        })
        .collect::<Vec<_>>();
    domain_result.passed = false;
    domain_result.error_count = domain_result
        .error_count
        .saturating_add(violations.len() as u32);
    domain_result.actionable_errors.extend(
        violations
            .into_iter()
            .map(|code| format!("Forbidden domain diagnostic encountered: {code}")),
    );
    domain_result
}

/// Return forbidden diagnostic codes present in the TeaQL diagnostic.
///
/// Matches are token-aware, so forbidding `KSML-FOO-001` does not accidentally
/// match `KSML-FOO-0010`. Results preserve specification order and are unique.
pub fn matching_forbidden_errors(spec: &Value, domain_diagnostic: &str) -> Vec<String> {
    let Some(forbidden) = spec.get("forbidden_errors").and_then(Value::as_array) else {
        return Vec::new();
    };

    let mut seen = HashSet::new();
    forbidden
        .iter()
        .filter_map(Value::as_str)
        .filter(|code| diagnostic_contains_code(domain_diagnostic, code))
        .filter(|code| seen.insert((*code).to_string()))
        .map(str::to_string)
        .collect()
}

/// Extract declared business-object names from KSML XML.
///
/// Both the older `<object name="School">` convention and TeaQL's
/// `<school _name="School">` convention are supported. A declaration's tag
/// name is retained as an alias so an acceptance name such as `SchoolType`
/// also matches `<school_type _name="School Type">`.
fn extract_object_names(content: &str) -> Vec<ObjectName> {
    let mut reader = quick_xml::Reader::from_str(content);
    let mut buf = Vec::new();
    let mut depth = 0usize;
    let mut objects = Vec::new();

    loop {
        match reader.read_event_into(&mut buf) {
            Ok(Event::Start(element)) => {
                if depth == 1 {
                    record_object(&element, &mut objects);
                }
                depth += 1;
            }
            Ok(Event::Empty(element)) => {
                if depth == 1 {
                    record_object(&element, &mut objects);
                }
            }
            Ok(Event::End(_)) => depth = depth.saturating_sub(1),
            Ok(Event::Eof) => break,
            Ok(_) => {}
            Err(_) => break,
        }
        buf.clear();
    }

    objects
}

#[derive(Debug, PartialEq, Eq)]
struct ObjectName {
    declared_name: String,
    tag_alias: String,
    references: Vec<String>,
}

fn record_object(element: &quick_xml::events::BytesStart<'_>, objects: &mut Vec<ObjectName>) {
    let tag = String::from_utf8_lossy(element.name().as_ref()).into_owned();
    if tag == "root" || tag.starts_with('_') {
        return;
    }

    let name_attribute: &[u8] = if tag == "object" { b"name" } else { b"_name" };
    let declared_name = element
        .attributes()
        .flatten()
        .find(|attribute| attribute.key.as_ref() == name_attribute)
        .and_then(|attribute| attribute.unescape_value().ok())
        .map(|value| value.into_owned());

    if let Some(declared_name) = declared_name {
        let references = element
            .attributes()
            .flatten()
            .filter(|attribute| !attribute.key.as_ref().starts_with(b"_"))
            .filter_map(|attribute| attribute.unescape_value().ok())
            .filter_map(|value| reference_target(&value))
            .collect();
        objects.push(ObjectName {
            declared_name,
            tag_alias: pascal_case(&tag),
            references,
        });
    }
}

fn reference_target(value: &str) -> Option<String> {
    let target = value.trim().strip_suffix("()")?;
    if target.is_empty()
        || !target
            .chars()
            .all(|character| character == '_' || character.is_ascii_alphanumeric())
    {
        return None;
    }
    Some(pascal_case(target))
}

fn contains_object(objects: &[ObjectName], expected: &str) -> bool {
    objects.iter().any(|object| {
        object.declared_name == expected
            || object.tag_alias == expected
            || canonical_name(&object.declared_name) == canonical_name(expected)
    })
}

fn find_object<'a>(objects: &'a [ObjectName], expected: &str) -> Option<&'a ObjectName> {
    objects
        .iter()
        .find(|object| object_matches(object, expected))
}

fn object_matches(object: &ObjectName, expected: &str) -> bool {
    object.declared_name == expected
        || object.tag_alias == expected
        || canonical_name(&object.declared_name) == canonical_name(expected)
}

fn object_references(objects: &[ObjectName], from: &str, to: &str) -> bool {
    find_object(objects, from).is_some_and(|object| {
        object.references.iter().any(|reference| {
            canonical_name(reference) == canonical_name(to) && contains_object(objects, reference)
        })
    })
}

fn pascal_case(value: &str) -> String {
    value
        .split(|character: char| !character.is_ascii_alphanumeric())
        .filter(|part| !part.is_empty())
        .map(|part| {
            let mut characters = part.chars();
            match characters.next() {
                Some(first) => first.to_uppercase().chain(characters).collect(),
                None => String::new(),
            }
        })
        .collect()
}

fn canonical_name(value: &str) -> String {
    value
        .chars()
        .filter(|character| character.is_ascii_alphanumeric())
        .flat_map(char::to_lowercase)
        .collect()
}

fn diagnostic_contains_code(diagnostic: &str, code: &str) -> bool {
    if code.is_empty() {
        return false;
    }

    diagnostic.match_indices(code).any(|(start, _)| {
        let before = diagnostic[..start].chars().next_back();
        let end = start + code.len();
        let after = diagnostic[end..].chars().next();
        !before.is_some_and(is_code_character) && !after.is_some_and(is_code_character)
    })
}

fn is_code_character(character: char) -> bool {
    character.is_ascii_alphanumeric() || matches!(character, '-' | '_')
}

#[cfg(test)]
mod tests {
    use super::*;
    use serde_json::json;

    fn school_xml() -> &'static str {
        r#"<?xml version="1.0"?>
<root name="school-service">
  <platform _name="Platform" />
  <school_type _name="School Type">
    <_value id="1001" name="Primary" />
  </school_type>
  <school _name="School" />
</root>"#
    }

    #[test]
    fn accepts_expected_objects_and_exact_count() {
        let spec = json!({
            "expected_objects": ["Platform", "School", "SchoolType"],
            "expected_object_count": 3
        });

        let result = validate_acceptance(school_xml(), &spec);

        assert!(result.passed, "{:?}", result.actionable_errors);
    }

    #[test]
    fn expected_objects_allow_unlisted_extras() {
        let spec = json!({ "expected_objects": ["School"] });

        assert!(validate_acceptance(school_xml(), &spec).passed);
    }

    #[test]
    fn rejects_missing_expected_object_and_wrong_exact_count() {
        let spec = json!({
            "expected_objects": ["School", "Teacher"],
            "expected_object_count": 4
        });

        let result = validate_acceptance(school_xml(), &spec);

        assert!(!result.passed);
        assert_eq!(result.error_count, 2);
        assert!(
            result
                .diagnostic
                .contains("Missing expected object: Teacher")
        );
        assert!(
            result
                .diagnostic
                .contains("Expected exactly 4 objects, found 3")
        );
    }

    #[test]
    fn enforces_minimum_object_count() {
        let passing_spec = json!({ "min_object_count": 3 });
        let failing_spec = json!({ "min_object_count": 4 });

        assert!(validate_acceptance(school_xml(), &passing_spec).passed);
        let result = validate_acceptance(school_xml(), &failing_spec);
        assert!(!result.passed);
        assert!(
            result
                .diagnostic
                .contains("Expected at least 4 objects, found 3")
        );
    }

    #[test]
    fn counts_only_top_level_business_objects() {
        let spec = json!({ "expected_object_count": 3 });

        let result = validate_acceptance(school_xml(), &spec);

        assert!(result.passed, "nested _value must not count as an object");
    }

    #[test]
    fn supports_legacy_object_elements() {
        let content = r#"<root><object name="School"/><object name="Teacher"/></root>"#;
        let spec = json!({
            "expected_objects": ["School", "Teacher"],
            "expected_object_count": 2
        });

        assert!(validate_acceptance(content, &spec).passed);
    }

    #[test]
    fn validates_required_root_and_inverse_children_relationships() {
        let content = r#"
            <root>
              <school _name="School" name="Central"/>
              <teacher _name="Teacher" school="school()"/>
              <student _name="Student" school="school()"/>
            </root>
        "#;
        let spec = json!({
            "schema": "ksml-acceptance-v1",
            "required_root": "School",
            "required_relationships": [
                { "from": "School", "to": "Teacher", "type": "children" },
                { "from": "Teacher", "to": "School", "type": "container" },
                { "from": "School", "to": "Student", "type": "children" }
            ]
        });

        let result = validate_acceptance(content, &spec);

        assert!(result.passed, "{}", result.diagnostic);
    }

    #[test]
    fn rejects_missing_relationship_and_malformed_acceptance_fields() {
        let content = r#"<root><school _name="School"/><teacher _name="Teacher"/></root>"#;
        let spec = json!({
            "schema": "wrong-schema",
            "min_objects": 2,
            "required_relationships": [
                { "from": "Teacher", "to": "School", "type": "container" }
            ]
        });

        let result = validate_acceptance(content, &spec);

        assert!(!result.passed);
        assert!(result.diagnostic.contains("Acceptance schema"));
        assert!(
            result
                .diagnostic
                .contains("Unknown acceptance field: min_objects")
        );
        assert!(result.diagnostic.contains("Missing required relationship"));
    }

    #[test]
    fn aggregates_objects_across_included_model_files() {
        let files = vec![
            (
                "main.xml".to_string(),
                r#"<root><_include file="operations.xml"/><company _name="Company"/></root>"#
                    .to_string(),
            ),
            (
                "operations.xml".to_string(),
                r#"<root><move_order _name="Move Order"/><route_plan _name="Route Plan"/></root>"#
                    .to_string(),
            ),
        ];
        let spec = json!({
            "expected_objects": ["Company", "MoveOrder", "RoutePlan"],
            "expected_object_count": 3,
            "min_object_count": 3
        });

        let result = validate_acceptance_model_files(&files, &spec);

        assert!(result.passed, "{:?}", result.actionable_errors);
        assert_eq!(count_model_objects(&files), 3);
    }

    #[test]
    fn included_model_files_contribute_to_minimum_count_failure_diagnostic() {
        let files = vec![
            (
                "main.xml".to_string(),
                r#"<root><_include file="operations.xml"/></root>"#.to_string(),
            ),
            (
                "operations.xml".to_string(),
                r#"<root><move_order _name="Move Order"/></root>"#.to_string(),
            ),
        ];
        let spec = json!({ "min_object_count": 2 });

        let result = validate_acceptance_model_files(&files, &spec);

        assert!(!result.passed);
        assert!(
            result
                .diagnostic
                .contains("Expected at least 2 objects, found 1")
        );
    }

    #[test]
    fn forbidden_warning_is_promoted_to_domain_failure() {
        let spec = json!({
            "forbidden_errors": ["KSML-KEYWORD-002", "KSML-DOMAIN-ROOT-002"]
        });
        let mut domain_result = super::super::pass(3, "domain", 1.25);
        domain_result.warning_count = 1;
        domain_result.diagnostic =
            "| `KSML-DOMAIN-ROOT-002` | Multiple independent roots |".to_string();

        let result = enforce_forbidden_domain_errors(domain_result, &spec);

        assert!(!result.passed);
        assert_eq!(result.level, 3);
        assert_eq!(result.error_count, 1);
        assert_eq!(result.warning_count, 1);
        assert_eq!(
            result.actionable_errors,
            ["Forbidden domain diagnostic encountered: KSML-DOMAIN-ROOT-002"]
        );
    }

    #[test]
    fn unmatched_forbidden_errors_leave_domain_result_unchanged() {
        let spec = json!({ "forbidden_errors": ["KSML-DOMAIN-ROOT-002"] });
        let mut domain_result = super::super::pass(3, "domain", 0.5);
        domain_result.diagnostic = "- **Warnings**: 0".to_string();
        let result = enforce_forbidden_domain_errors(domain_result.clone(), &spec);

        assert_eq!(result.passed, domain_result.passed);
        assert_eq!(result.error_count, domain_result.error_count);
        assert_eq!(result.warning_count, domain_result.warning_count);
        assert_eq!(result.actionable_errors, domain_result.actionable_errors);
        assert_eq!(result.diagnostic, domain_result.diagnostic);
    }

    #[test]
    fn forbidden_code_already_counted_as_domain_error_is_not_double_counted() {
        let spec = json!({ "forbidden_errors": ["KSML-DOMAIN-ROOT-002"] });
        let mut domain_result = super::super::fail(
            3,
            "domain",
            vec!["| `KSML-DOMAIN-ROOT-002` | disconnected |".to_string()],
            "| `KSML-DOMAIN-ROOT-002` | disconnected |".to_string(),
            0.5,
        );
        domain_result.warning_count = 2;

        let result = enforce_forbidden_domain_errors(domain_result, &spec);

        assert!(!result.passed);
        assert_eq!(result.error_count, 1);
        assert_eq!(result.actionable_errors.len(), 1);
    }

    #[test]
    fn forbidden_error_matching_is_token_aware_and_deduplicated() {
        let spec = json!({
            "forbidden_errors": [
                "KSML-FOO-001",
                "KSML-FOO-001",
                "KSML-DOMAIN-ROOT-002"
            ]
        });
        let diagnostic = "KSML-FOO-0010\n`KSML-DOMAIN-ROOT-002`";

        assert_eq!(
            matching_forbidden_errors(&spec, diagnostic),
            ["KSML-DOMAIN-ROOT-002"]
        );
    }
}
