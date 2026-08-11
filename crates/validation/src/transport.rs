//! L0: Transport validation — HTTP status, JSON parse, finish_reason

use agent_core::event::{ModelResult, ValidationResult};

/// Validate the transport-level response.
/// Only finish_reason=stop with non-empty content passes.
pub fn validate_transport(result: &ModelResult) -> ValidationResult {
    let start = std::time::Instant::now();
    let mut errors = Vec::new();

    if result.http_status != 200 {
        errors.push(format!("HTTP status {}", result.http_status));
    }

    if result.finish_reason != "stop" {
        errors.push(format!(
            "finish_reason='{}', expected 'stop'",
            result.finish_reason
        ));
    }

    if result.content.trim().is_empty() {
        errors.push("Empty content".to_string());
    }

    let elapsed = start.elapsed().as_secs_f64();
    if errors.is_empty() {
        super::pass(0, "transport", elapsed)
    } else {
        super::fail(0, "transport", errors.clone(), errors.join("; "), elapsed)
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use agent_core::event::TokenUsage;

    fn result(finish_reason: &str, content: &str) -> ModelResult {
        ModelResult {
            content: content.to_string(),
            reasoning_content: None,
            tool_calls: None,
            finish_reason: finish_reason.to_string(),
            usage: TokenUsage {
                prompt_tokens: 1,
                completion_tokens: 1,
                total_tokens: 2,
            },
            elapsed_secs: 0.1,
            http_status: 200,
        }
    }

    #[test]
    fn truncated_or_empty_model_content_never_passes_transport_gate() {
        assert!(!validate_transport(&result("length", "<root>")).passed);
        assert!(!validate_transport(&result("stop", "   ")).passed);
        assert!(validate_transport(&result("stop", "<root/>")).passed);
    }
}
