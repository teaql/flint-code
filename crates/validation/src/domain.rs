//! L3: Domain validation — TeaQL evaluate or other domain validators

use agent_core::event::ValidationResult;
use std::path::Path;

/// Parse TeaQL evaluate output to extract error/warning/suggestion counts.
pub fn parse_teaql_output(output: &str) -> ValidationResult {
    let start = std::time::Instant::now();
    let mut errors = Vec::new();
    let mut error_count: u32 = 0;
    let mut warning_count: u32 = 0;
    let mut suggestion_count: u32 = 0;

    for line in output.lines() {
        let line = line.trim();
        if line.contains("error") || line.contains("Error") {
            if let Some(count) = extract_count(line) {
                error_count = count;
            } else {
                errors.push(line.to_string());
                error_count += 1;
            }
        }
        if line.contains("warning") || line.contains("Warning") {
            if let Some(count) = extract_count(line) {
                warning_count = count;
            }
        }
        if line.contains("suggestion") || line.contains("Suggestion") {
            if let Some(count) = extract_count(line) {
                suggestion_count = count;
            }
        }
    }

    let elapsed = start.elapsed().as_secs_f64();
    ValidationResult {
        level: 3,
        level_name: "domain".to_string(),
        passed: error_count == 0,
        error_count,
        warning_count,
        suggestion_count,
        actionable_errors: errors,
        diagnostic: output.to_string(),
        elapsed_secs: elapsed,
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
