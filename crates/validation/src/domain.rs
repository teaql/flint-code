//! L3: Domain validation — TeaQL evaluate or other domain validators

use agent_core::event::ValidationResult;

pub fn is_infrastructure_output(output: &str) -> bool {
    let normalized = output.to_ascii_lowercase();
    [
        "internal server error",
        "bad gateway",
        "service unavailable",
        "gateway timeout",
        "connection refused",
        "failed to connect",
        "could not connect",
        "network is unreachable",
        "name resolution",
        "dns error",
        "tls error",
        "certificate error",
        "error (500)",
        "error (502)",
        "error (503)",
        "error (504)",
    ]
    .iter()
    .any(|marker| normalized.contains(marker))
}

/// Parse TeaQL evaluate output to extract error/warning/suggestion counts.
pub fn parse_teaql_output(output: &str) -> ValidationResult {
    let mut errors = Vec::new();
    let mut error_count: u32 = 0;
    let mut warning_count: u32 = 0;
    let mut suggestion_count: u32 = 0;
    let mut in_error_table = false;

    for line in output.lines() {
        let line = line.trim();

        // Parse summary counts from "- **Errors**: 3" format
        if line.starts_with("- **Errors**:") {
            if let Some(count) = extract_count(line) {
                error_count = count;
            }
        }
        if line.starts_with("- **Warnings**:") {
            if let Some(count) = extract_count(line) {
                warning_count = count;
            }
        }
        if line.starts_with("- **Suggestions**:") {
            if let Some(count) = extract_count(line) {
                suggestion_count = count;
            }
        }

        // Detect error table section
        if line.contains("## ❌ Errors") || line.contains("## Errors") {
            in_error_table = true;
            errors.push(line.to_string());
            continue;
        }
        // End of error table on next section
        if in_error_table && line.starts_with("## ") {
            in_error_table = false;
        }
        // Collect error table rows (start with |, skip header/separator)
        if in_error_table && line.starts_with("| `") {
            errors.push(line.to_string());
        }
    }

    ValidationResult {
        level: 3,
        level_name: "domain".to_string(),
        passed: error_count == 0,
        error_count,
        warning_count,
        suggestion_count,
        actionable_errors: errors,
        structured_errors: vec![],
        diagnostic: output.to_string(),
        elapsed_secs: 0.0,
    }
}

/// Try to extract a numeric count from a line like "5 errors"
fn extract_count(line: &str) -> Option<u32> {
    for word in line.split_whitespace() {
        if let Ok(n) = word.parse::<u32>() {
            return Some(n);
        }
    }
    None
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn teaql_transport_failures_are_classified_as_infrastructure() {
        assert!(is_infrastructure_output(
            "## Internal Server Error (500)\nStringTemplate failed"
        ));
        assert!(is_infrastructure_output(
            "connection refused while contacting validator"
        ));
        assert!(!is_infrastructure_output(
            "KSML-DOMAIN-ROOT-002 disconnected graph"
        ));
    }
}
