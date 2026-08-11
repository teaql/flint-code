//! L5-L6: Build and test validation

use agent_core::event::ValidationResult;

/// Parse cargo check output to create a validation result.
pub fn parse_cargo_check_output(
    exit_code: i32,
    stderr: &str,
    elapsed_secs: f64,
) -> ValidationResult {
    if exit_code == 0 {
        let warning_count = stderr.matches("warning").count() as u32;
        let mut result = super::pass(5, "build", elapsed_secs);
        result.warning_count = warning_count;
        result
    } else {
        let mut errors: Vec<String> = stderr
            .lines()
            .filter(|line| {
                let normalized = line.trim_start().to_ascii_lowercase();
                normalized.starts_with("error") || normalized.contains("exception")
            })
            .map(str::to_string)
            .collect();

        if errors.is_empty() {
            errors.push(format!(
                "Build command failed with exit code {exit_code}; inspect the complete diagnostic"
            ));
        }

        let diagnostic = truncate_at_char_boundary(stderr, 12_000);

        super::fail(5, "build", errors, diagnostic, elapsed_secs)
    }
}

/// Parse cargo test output to create a validation result.
pub fn parse_cargo_test_output(
    exit_code: i32,
    stdout: &str,
    stderr: &str,
    elapsed_secs: f64,
) -> ValidationResult {
    if exit_code == 0 {
        super::pass(6, "test", elapsed_secs)
    } else {
        let mut errors: Vec<String> = Vec::new();
        for line in stdout.lines().chain(stderr.lines()) {
            if line.contains("FAILED") || line.contains("panicked") || line.contains("assertion") {
                errors.push(line.to_string());
            }
        }

        let combined = format!("{stdout}\n{stderr}");
        let diagnostic = truncate_at_char_boundary(&combined, 12_000);

        super::fail(6, "test", errors, diagnostic, elapsed_secs)
    }
}

fn truncate_at_char_boundary(text: &str, max_bytes: usize) -> String {
    if text.len() <= max_bytes {
        return text.to_string();
    }
    let mut boundary = max_bytes;
    while boundary > 0 && !text.is_char_boundary(boundary) {
        boundary -= 1;
    }
    text[..boundary].to_string()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn cargo_errors_are_matched_case_insensitively() {
        let result = parse_cargo_check_output(101, "ERROR[E0592]: duplicate definition", 1.0);

        assert!(!result.passed);
        assert_eq!(result.error_count, 1);
        assert!(result.actionable_errors[0].contains("E0592"));
    }

    #[test]
    fn failed_build_without_formatted_error_stays_actionable() {
        let result = parse_cargo_check_output(1, "compiler terminated", 1.0);

        assert!(!result.passed);
        assert_eq!(result.error_count, 1);
        assert!(result.actionable_errors[0].contains("exit code 1"));
    }
}
