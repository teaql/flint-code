use crate::task_package::TaskPackageData;

/// Build the core system prompt.
/// Design doc target: < 2K tokens.
pub fn build_system_prompt() -> String {
    r#"You are a code generation model running on a DGX Spark.

You receive a task package and produce a complete artifact.
You do not have access to tools, shell, or file system.
You do not search, browse, or access external services.

Rules:
- Output only the requested artifact, no explanation unless asked.
- Follow the grammar example exactly.
- Use only values from the whitelist when provided.
- Do not invent API methods or fields not in the task specification.
- Do not add objects, files or fields beyond the task scope.
"#.to_string()
}

/// Build the complete prompt messages for a generation request.
/// Returns Vec of (role, content) pairs.
pub fn build_generation_messages(task: &TaskPackageData) -> Vec<(String, String)> {
    let mut messages = vec![
        ("system".to_string(), build_system_prompt()),
    ];

    // Add grammar example if present
    if let Some(example) = &task.grammar_example {
        messages.push((
            "system".to_string(),
            format!("Grammar example (follow this structure exactly):\n\n{example}"),
        ));
    }

    // Add value whitelist if present
    if let Some(whitelist) = &task.value_whitelist {
        messages.push((
            "system".to_string(),
            format!("Allowed value forms (use only these):\n\n{whitelist}"),
        ));
    }

    // Add the actual task
    messages.push(("user".to_string(), task.task_content.clone()));

    messages
}

/// Build the repair prompt messages.
/// This is a FRESH request — not a continuation of the previous conversation.
pub fn build_repair_messages(
    task: &TaskPackageData,
    rejected_candidate: &str,
    actionable_errors: &[String],
    diagnostic_limit: usize,
) -> Vec<(String, String)> {
    let mut messages = vec![
        ("system".to_string(), build_system_prompt()),
    ];

    if let Some(example) = &task.grammar_example {
        messages.push((
            "system".to_string(),
            format!("Grammar example:\n\n{example}"),
        ));
    }

    if let Some(whitelist) = &task.value_whitelist {
        messages.push((
            "system".to_string(),
            format!("Allowed value forms:\n\n{whitelist}"),
        ));
    }

    // Task again
    messages.push(("user".to_string(), task.task_content.clone()));

    // Rejected candidate
    messages.push((
        "assistant".to_string(),
        rejected_candidate.to_string(),
    ));

    // Truncated diagnostic with actionable errors only
    let mut diagnostic = String::from("The previous output was rejected. Fix the following errors and output a complete corrected artifact:\n\n");
    for err in actionable_errors {
        let truncated: String = err.chars().take(diagnostic_limit / actionable_errors.len().max(1)).collect();
        diagnostic.push_str(&format!("- {truncated}\n"));
    }

    messages.push(("user".to_string(), diagnostic));

    messages
}
