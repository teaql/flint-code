use crate::task_package::TaskPackageData;

/// Build the core system prompt — kept minimal.
/// Only sets the model's role. All domain knowledge comes from context files.
pub fn build_system_prompt() -> String {
    std::fs::read_to_string("prompts/system.txt")
        .unwrap_or_else(|_| include_str!("../../../prompts/system.txt").to_string())
}

/// Load a context file from the prompts directory.
/// Returns None if the file doesn't exist (optional context).
fn load_context_file(path: &str) -> Option<String> {
    std::fs::read_to_string(path).ok()
}

/// Assemble all reference context files into a single block.
/// These are NOT system prompts — they are reference documents
/// loaded from local files and presented as user context.
fn build_context_block(task: &TaskPackageData) -> String {
    let mut parts = Vec::new();

    // Grammar example (from task package)
    if let Some(example) = &task.grammar_example {
        parts.push(format!("## Grammar Example\n\nFollow this structure exactly:\n\n```xml\n{example}\n```"));
    }

    // Value whitelist (from task package)
    if let Some(whitelist) = &task.value_whitelist {
        parts.push(format!("## Allowed Value Forms\n\nUse only these value patterns:\n\n{whitelist}"));
    }

    // Any additional context files from prompts/context.d/ directory
    if let Ok(entries) = std::fs::read_dir("prompts/context.d") {
        let mut files: Vec<_> = entries.flatten()
            .filter(|e| e.path().is_file())
            .collect();
        files.sort_by_key(|e| e.file_name());
        for entry in files {
            if let Ok(content) = std::fs::read_to_string(entry.path()) {
                let name = entry.file_name().to_string_lossy().to_string();
                parts.push(format!("## Context: {name}\n\n{content}"));
            }
        }
    }

    if parts.is_empty() {
        String::new()
    } else {
        format!("# Reference Context\n\nThe following are reference documents loaded from local files.\n\n{}", parts.join("\n\n---\n\n"))
    }
}

/// Build the complete prompt messages for a generation request.
/// Returns Vec of (role, content) pairs.
///
/// Structure:
///   system: minimal role description (from prompts/system.txt)
///   user:   context block (KSML rules + grammar + whitelist) + task
pub fn build_generation_messages(task: &TaskPackageData) -> Vec<(String, String)> {
    let mut messages = vec![
        ("system".to_string(), build_system_prompt()),
    ];

    // Build the user message: context block + task
    let context = build_context_block(task);
    let user_message = if context.is_empty() {
        task.task_content.clone()
    } else {
        format!("{context}\n\n---\n\n# Task\n\n{}", task.task_content)
    };

    messages.push(("user".to_string(), user_message));

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

    // Same context block for repair
    let context = build_context_block(task);
    let user_message = if context.is_empty() {
        task.task_content.clone()
    } else {
        format!("{context}\n\n---\n\n# Task\n\n{}", task.task_content)
    };

    messages.push(("user".to_string(), user_message));

    // Rejected candidate
    messages.push((
        "assistant".to_string(),
        rejected_candidate.to_string(),
    ));

    // Truncated diagnostic with actionable errors only
    let template = std::fs::read_to_string("prompts/repair-domain.txt")
        .unwrap_or_else(|_| include_str!("../../../prompts/repair-domain.txt").to_string());
    
    let mut errors_str = String::new();
    for err in actionable_errors {
        let truncated: String = err.chars().take(diagnostic_limit / actionable_errors.len().max(1)).collect();
        errors_str.push_str(&format!("- {truncated}\n"));
    }
    
    let diagnostic = template.replace("{{errors}}", &errors_str);

    messages.push(("user".to_string(), diagnostic));

    messages
}
