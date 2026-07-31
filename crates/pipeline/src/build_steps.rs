//! Decomposed build validation steps.
//!
//! Each step operates on a shared [`BuildContext`] and returns
//! `Ok(())` to continue the pipeline or `Err(ValidationResult)` to
//! short-circuit with a terminal result.

use agent_core::event::{CompilerError, ErrorSeverity, RunEvent, ValidationResult};
use artifact_store::RunArtifacts;
use model_vllm::backend::ModelClient;
use model_vllm::chat::ChatMessage;
use std::collections::HashMap;
use std::path::PathBuf;
use tracing::{error, info, warn};

use crate::process_runner::ProcessRunner;

/// Shared context for all build steps within a single attempt.
pub struct BuildContext {
    pub attempt: u8,
    pub attempt_dir: PathBuf,
    pub build_target: String,
    pub patches: Option<HashMap<String, String>>,
    pub artifacts: Option<RunArtifacts>,
    pub start: std::time::Instant,
}

impl BuildContext {
    pub fn build_dir(&self) -> PathBuf {
        self.attempt_dir.join("build")
    }

    pub fn lib_dir(&self) -> PathBuf {
        self.attempt_dir.join("build/lib")
    }

    pub fn elapsed(&self) -> f64 {
        self.start.elapsed().as_secs_f64()
    }

    /// Save a JSON artifact for this attempt.
    pub fn save_attempt_file(&self, filename: &str, data: &impl serde::Serialize) {
        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_file(self.attempt, filename, data).ok();
        }
    }

    /// Save a raw text artifact for this attempt.
    pub fn save_attempt_raw(&self, filename: &str, content: &str) {
        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_raw(self.attempt, filename, content).ok();
        }
    }
}

// ── Step 1: Generate lib code via `cargo teaql` ─────────────────────────

pub async fn step_generate_code(
    ctx: &BuildContext,
    runner: &mut ProcessRunner,
) -> Result<(), ValidationResult> {
    info!(
        attempt = ctx.attempt,
        target = %ctx.build_target,
        dir = %ctx.attempt_dir.display(),
        "Running code generation"
    );
    let gen_result = runner
        .run(
            "cargo",
            &["teaql", "--input", "model", &ctx.build_target],
            Some(&ctx.attempt_dir),
        )
        .await;

    match &gen_result {
        Ok(output) if !output.status.success() => {
            let stderr = String::from_utf8_lossy(&output.stderr);
            let stdout = String::from_utf8_lossy(&output.stdout);
            let diagnostic = format!("Code generation failed:\n{}\n{}", stdout, stderr);
            warn!(attempt = ctx.attempt, "Code generation failed");
            Err(build_fail(
                vec![format!("cargo teaql {} failed", ctx.build_target)],
                diagnostic,
                ctx.elapsed(),
            ))
        }
        Err(e) => {
            error!(attempt = ctx.attempt, %e, "Failed to run cargo teaql");
            Err(build_fail(
                vec![format!("Failed to execute cargo teaql: {}", e)],
                e.to_string(),
                ctx.elapsed(),
            ))
        }
        _ => {
            info!(attempt = ctx.attempt, "Code generation succeeded");
            Ok(())
        }
    }
}

// ── Step 2: Patch generated Cargo.toml ──────────────────────────────────

pub fn step_patch_cargo_toml(ctx: &BuildContext) -> Result<(), ValidationResult> {
    let cargo_toml_path = ctx.lib_dir().join("Cargo.toml");
    if cargo_toml_path.exists() {
        if let Ok(content) = std::fs::read_to_string(&cargo_toml_path) {
            let mut fixed = content.clone();
            if let Some(patches) = &ctx.patches {
                for (find, replace) in patches {
                    fixed = fixed.replace(find, replace);
                }
            }
            if fixed != content {
                info!(attempt = ctx.attempt, "Patched rusqlite version in generated Cargo.toml");
                std::fs::write(&cargo_toml_path, &fixed).ok();
            }
        }
        Ok(())
    } else {
        warn!(
            attempt = ctx.attempt,
            path = %cargo_toml_path.display(),
            "Generated Cargo.toml not found"
        );
        Err(build_fail(
            vec!["Generated build/lib/Cargo.toml not found after code generation".to_string()],
            format!("Expected {} but file does not exist", cargo_toml_path.display()),
            ctx.elapsed(),
        ))
    }
}

// ── Step 3: Run `cargo check` on lib crate ──────────────────────────────

pub async fn step_cargo_check_lib(
    ctx: &BuildContext,
    runner: &mut ProcessRunner,
) -> Result<ValidationResult, ValidationResult> {
    let lib_dir = ctx.lib_dir();
    info!(
        attempt = ctx.attempt,
        dir = %lib_dir.display(),
        "Running cargo check"
    );
    let check_result = runner.run("cargo", &["check"], Some(&lib_dir)).await;
    let elapsed = ctx.elapsed();

    match check_result {
        Ok(output) => {
            let stderr = String::from_utf8_lossy(&output.stderr).to_string();
            if output.status.success() {
                info!(
                    attempt = ctx.attempt,
                    elapsed,
                    "Build validation passed — cargo check succeeded"
                );
                let mut r = validation::pass(5, "build", elapsed);
                let warning_count = count_warnings(&stderr);
                r.warning_count = warning_count;
                r.diagnostic = if warning_count > 0 {
                    format!("{} warnings\n{}", warning_count, stderr)
                } else {
                    "cargo check passed".to_string()
                };
                Ok(r)
            } else {
                let (error_count, actionable_errors) = parse_cargo_errors(&stderr);
                warn!(
                    attempt = ctx.attempt,
                    error_count,
                    "Build validation failed — cargo check errors"
                );
                Err(ValidationResult {
                    level: 5,
                    level_name: "build".to_string(),
                    passed: false,
                    error_count,
                    warning_count: 0,
                    suggestion_count: 0,
                    actionable_errors,
                    structured_errors: Vec::new(),
                    diagnostic: stderr,
                    elapsed_secs: elapsed,
                })
            }
        }
        Err(e) => {
            error!(attempt = ctx.attempt, %e, "Failed to run cargo check");
            Err(build_fail(
                vec![format!("Failed to execute cargo check: {}", e)],
                e.to_string(),
                elapsed,
            ))
        }
    }
}

// ── Step 4: Generate app target ─────────────────────────────────────────

pub async fn step_generate_app(
    ctx: &BuildContext,
    runner: &mut ProcessRunner,
) -> bool {
    let app_target = ctx.build_target.replace("-lib-core", "-app-console");
    info!(attempt = ctx.attempt, "Generating {}", app_target);
    let app_gen_result = runner
        .run(
            "cargo",
            &["teaql", "--input", "model", &app_target],
            Some(&ctx.attempt_dir),
        )
        .await;

    match &app_gen_result {
        Ok(output) if !output.status.success() => {
            let stderr = String::from_utf8_lossy(&output.stderr);
            warn!(
                attempt = ctx.attempt,
                "{} generation failed, continuing with lib-only result", app_target
            );
            ctx.save_attempt_raw("app-console-gen-error.txt", &stderr);
            false
        }
        Err(e) => {
            warn!(attempt = ctx.attempt, %e, "Failed to run {} generation", app_target);
            false
        }
        _ => {
            info!(attempt = ctx.attempt, "{} generated successfully", app_target);
            true
        }
    }
}

// ── Step 5: Fix dependency paths in app Cargo.toml ──────────────────────

pub fn step_fix_app_dependencies(ctx: &BuildContext) {
    let build_dir = ctx.build_dir();

    // Fix the path dependency to point to ./lib
    let app_cargo_toml = build_dir.join("Cargo.toml");
    if app_cargo_toml.exists() {
        if let Ok(content) = std::fs::read_to_string(&app_cargo_toml) {
            let old_path = format!(r#"path = "../{}/lib""#, ctx.build_target);
            let fixed = content.replace(&old_path, r#"path = "./lib""#);
            if fixed != content {
                info!(attempt = ctx.attempt, "Fixed app-console dependency path");
            }
            std::fs::write(&app_cargo_toml, &fixed).ok();
        }
    }

    // Also fix rusqlite in lib again (app-console gen may have reset it)
    let lib_cargo_toml = build_dir.join("lib/Cargo.toml");
    if lib_cargo_toml.exists() {
        if let Ok(content) = std::fs::read_to_string(&lib_cargo_toml) {
            let mut fixed = content;
            if let Some(patches) = &ctx.patches {
                for (find, replace) in patches {
                    fixed = fixed.replace(find, replace);
                }
            }
            std::fs::write(&lib_cargo_toml, &fixed).ok();
        }
    }
}

// ── Step 6: Run assist commands ─────────────────────────────────────────

/// Maximum number of entities to query via assist (context budget guard).
const MAX_ASSIST_ENTITIES: usize = 8;

pub async fn step_run_assist(
    ctx: &BuildContext,
    runner: &mut ProcessRunner,
) -> (Vec<String>, String) {
    let build_dir = ctx.build_dir();
    let agents_md_path = build_dir.join("AGENTS.md");
    let entity_names = if agents_md_path.exists() {
        let agents_content = std::fs::read_to_string(&agents_md_path).unwrap_or_default();
        parse_entity_names_from_agents_md(&agents_content)
    } else {
        Vec::new()
    };

    let assist_entities: Vec<&String> = if entity_names.len() > MAX_ASSIST_ENTITIES {
        let step = entity_names.len() as f64 / MAX_ASSIST_ENTITIES as f64;
        (0..MAX_ASSIST_ENTITIES)
            .map(|i| &entity_names[(i as f64 * step) as usize])
            .collect()
    } else {
        entity_names.iter().collect()
    };

    info!(
        attempt = ctx.attempt,
        total = entity_names.len(),
        sampled = assist_entities.len(),
        "Running assist commands"
    );

    let assist_target_base = ctx.build_target.replace("-lib-core", "-assist-query");
    let mut assist_outputs = String::new();
    for entity in &assist_entities {
        let assist_target = format!("{}/{}", assist_target_base, entity);
        info!(attempt = ctx.attempt, entity = %entity, "Running assist command");
        let assist_result = runner
            .run(
                "cargo",
                &["teaql", "--input", "model", &assist_target],
                Some(&ctx.attempt_dir),
            )
            .await;

        if let Ok(output) = &assist_result {
            let stdout = String::from_utf8_lossy(&output.stdout);
            let truncated = if stdout.len() > 1200 {
                format!("{}...\n[truncated]", &stdout[..1200])
            } else {
                stdout.to_string()
            };
            assist_outputs.push_str(&format!(
                "### Assist: query/{}\n\n{}\n\n",
                entity, truncated
            ));
        }
    }

    if entity_names.len() > MAX_ASSIST_ENTITIES {
        assist_outputs.push_str(&format!(
            "### All entity names ({})\n{}\n\n",
            entity_names.len(),
            entity_names.join(", ")
        ));
    }

    if !assist_outputs.is_empty() {
        ctx.save_attempt_raw("assist-output.md", &assist_outputs);
    }

    (entity_names, assist_outputs)
}

// ── Step 7: Generate business logic via LLM ─────────────────────────────

pub async fn step_generate_business_logic(
    ctx: &BuildContext,
    client: &ModelClient,
    event_tx: &tokio::sync::mpsc::Sender<RunEvent>,
    entity_names: &[String],
    assist_outputs: &str,
) {
    if entity_names.is_empty() || assist_outputs.is_empty() {
        return;
    }

    info!(
        attempt = ctx.attempt,
        entities = entity_names.len(),
        "Generating business logic via LLM"
    );

    let build_dir = ctx.build_dir();
    let agents_md_path = build_dir.join("AGENTS.md");
    let agents_md = std::fs::read_to_string(&agents_md_path).unwrap_or_default();
    let lib_rs = std::fs::read_to_string(build_dir.join("src/lib.rs")).unwrap_or_default();

    // Context budget guards — prevent template expansion from exceeding model context.
    // Each variable is capped at ~3K tokens (≈12K chars for code).
    const MAX_TEMPLATE_VAR_CHARS: usize = 12_000;
    let agents_md = truncate_with_notice(&agents_md, MAX_TEMPLATE_VAR_CHARS);
    let lib_rs = truncate_with_notice(&lib_rs, MAX_TEMPLATE_VAR_CHARS);

    let lib_cargo_toml =
        std::fs::read_to_string(build_dir.join("lib/Cargo.toml")).unwrap_or_default();
    let mut crate_name = "school_service_core".to_string();
    if let Ok(value) = lib_cargo_toml.parse::<toml::Value>() {
        if let Some(name) = value
            .get("package")
            .and_then(|p| p.get("name"))
            .and_then(|n| n.as_str())
        {
            crate_name = name.replace('-', "_");
        }
    }

    let template =
        std::fs::read_to_string("prompts/business-logic.txt").unwrap_or_else(|_| {
            "Please generate the business logic.".to_string()
        });

    let biz_prompt = template
        .replace("{{agents_md}}", &agents_md)
        .replace("{{lib_rs}}", &lib_rs)
        .replace("{{assist_outputs}}", assist_outputs)
        .replace("{{crate_name}}", &crate_name);

    let biz_messages = vec![
        ChatMessage {
            role: "system".to_string(),
            content: "You generate Rust code. Output ONLY raw Rust source. No markdown."
                .to_string(),
        },
        ChatMessage {
            role: "user".to_string(),
            content: biz_prompt,
        },
    ];

    match client.chat(biz_messages).await {
        Ok(biz_result) => {
            event_tx
                .send(RunEvent::ModelUsageRecorded(biz_result.usage.clone()))
                .await
                .ok();
            let biz_code = strip_code_fences(&biz_result.content);

            info!(
                attempt = ctx.attempt,
                tokens = biz_result.usage.completion_tokens,
                "Business logic generated"
            );

            let lib_rs_path = build_dir.join("src/lib.rs");
            std::fs::write(&lib_rs_path, &biz_code).ok();
            ctx.save_attempt_raw("business-logic.rs", &biz_code);
        }
        Err(e) => {
            warn!(
                attempt = ctx.attempt,
                %e,
                "Business logic generation failed, keeping default lib.rs"
            );
        }
    }
}

// ── Step 8: Final compile with LLM repair loop ──────────────────────────

pub async fn step_final_compile(
    ctx: &BuildContext,
    runner: &mut ProcessRunner,
    client: &ModelClient,
    event_tx: &tokio::sync::mpsc::Sender<RunEvent>,
    entity_names: &[String],
) -> ValidationResult {
    let build_dir = ctx.build_dir();
    let lib_rs_path = build_dir.join("src/lib.rs");

    for compile_attempt in 0..2u8 {
        info!(
            attempt = ctx.attempt,
            compile_attempt,
            "Running cargo check on app workspace"
        );
        let app_check = runner.run("cargo", &["check"], Some(&build_dir)).await;

        match &app_check {
            Ok(output) if output.status.success() => {
                let total_elapsed = ctx.elapsed();
                info!(
                    attempt = ctx.attempt,
                    total_elapsed,
                    "Full build validation passed — lib + app workspace"
                );
                let mut r = validation::pass(5, "build", total_elapsed);
                r.diagnostic = format!(
                    "rust-lib-core: cargo check ✓\nrust-app-console: cargo check ✓\nassist entities: {:?}\nbusiness logic: generated (compile attempt {})",
                    entity_names,
                    compile_attempt + 1
                );
                return r;
            }
            Ok(output) if compile_attempt == 0 => {
                let stderr = String::from_utf8_lossy(&output.stderr).to_string();
                let current_code =
                    std::fs::read_to_string(&lib_rs_path).unwrap_or_default();
                warn!(
                    attempt = ctx.attempt,
                    "App workspace failed — attempting LLM repair"
                );

                let template =
                    std::fs::read_to_string("prompts/repair.txt").unwrap_or_else(|_| {
                        "Please fix the code: {{current_code}}\nErrors: {{stderr}}".to_string()
                    });

                let fix_prompt = template
                    .replace("{{current_code}}", &current_code)
                    .replace("{{stderr}}", &stderr[..stderr.len().min(2000)]);

                let fix_messages = vec![
                    ChatMessage {
                        role: "system".to_string(),
                        content:
                            "You fix Rust compilation errors. Output ONLY raw Rust source."
                                .to_string(),
                    },
                    ChatMessage {
                        role: "user".to_string(),
                        content: fix_prompt,
                    },
                ];

                match client.chat(fix_messages).await {
                    Ok(fix_result) => {
                        event_tx
                            .send(RunEvent::ModelUsageRecorded(fix_result.usage.clone()))
                            .await
                            .ok();
                        let fixed = strip_code_fences(&fix_result.content);
                        info!(
                            attempt = ctx.attempt,
                            tokens = fix_result.usage.completion_tokens,
                            "LLM repair generated"
                        );
                        std::fs::write(&lib_rs_path, &fixed).ok();
                        ctx.save_attempt_raw("business-logic-repaired.rs", &fixed);
                        // Loop will retry cargo check
                    }
                    Err(e) => {
                        warn!(attempt = ctx.attempt, %e, "LLM repair failed");
                        break;
                    }
                }
            }
            _ => break,
        }
    }

    // All compile attempts failed — run one final check for diagnostics
    let total_elapsed = ctx.elapsed();
    let final_stderr = runner
        .run("cargo", &["check"], Some(&build_dir))
        .await
        .map(|o| String::from_utf8_lossy(&o.stderr).to_string())
        .unwrap_or_default();

    let (error_count, actionable_errors) = parse_cargo_errors(&final_stderr);
    ValidationResult {
        level: 5,
        level_name: "build".to_string(),
        passed: false,
        error_count: std::cmp::max(error_count, 1),
        warning_count: 0,
        suggestion_count: 0,
        actionable_errors,
        structured_errors: Vec::new(),
        diagnostic: format!(
            "rust-lib-core: ✓\nrust-app-console: FAILED (after LLM repair)\n\n{}",
            final_stderr
        ),
        elapsed_secs: total_elapsed,
    }
}

// ── Shared helpers ──────────────────────────────────────────────────────

/// Construct a failing build `ValidationResult`.
fn build_fail(
    errors: Vec<String>,
    diagnostic: String,
    elapsed_secs: f64,
) -> ValidationResult {
    validation::fail(5, "build", errors, diagnostic, elapsed_secs)
}

/// Truncate content to `max_chars` on a line boundary, appending a notice.
fn truncate_with_notice(content: &str, max_chars: usize) -> String {
    if content.len() <= max_chars {
        return content.to_string();
    }
    // Find the last newline before the limit to avoid cutting mid-line
    let cut = content[..max_chars]
        .rfind('\n')
        .unwrap_or(max_chars);
    format!(
        "{}\n\n... [truncated — {} of {} chars shown]",
        &content[..cut],
        cut,
        content.len()
    )
}

/// Parse error counts and actionable lines from `cargo check` stderr.
pub fn parse_cargo_errors(stderr: &str) -> (u32, Vec<String>) {
    let error_count = stderr
        .lines()
        .filter(|l| l.contains("error["))
        .count() as u32;
    let error_count = std::cmp::max(error_count, 1);
    let actionable_errors: Vec<String> = stderr
        .lines()
        .filter(|l| l.starts_with("error"))
        .take(10)
        .map(|l| l.to_string())
        .collect();
    (error_count, actionable_errors)
}

/// Count compiler warnings in stderr output.
pub fn count_warnings(stderr: &str) -> u32 {
    stderr
        .lines()
        .filter(|l| l.contains("warning[") || l.starts_with("warning:"))
        .count() as u32
}

/// Strip common markdown code fences from model output.
pub fn strip_code_fences(content: &str) -> String {
    let trimmed = content.trim();
    // Try to strip ```rust or ``` prefix, falling back to original
    let without_prefix = trimmed
        .strip_prefix("```rust")
        .or_else(|| trimmed.strip_prefix("```"))
        .unwrap_or(trimmed);
    // Strip trailing ``` if present
    let without_suffix = without_prefix
        .strip_suffix("```")
        .unwrap_or(without_prefix);
    without_suffix.trim().to_string()
}

/// Parse entity names from the generated AGENTS.md.
///
/// The generated AGENTS.md contains a markdown table like:
/// ```text
/// | entity-name | display-name |
/// |-------------|--------------|
/// | school | School |
/// | teacher | Teacher |
/// ```
/// We extract the entity-name column values.
pub fn parse_entity_names_from_agents_md(content: &str) -> Vec<String> {
    let mut entities = Vec::new();
    let mut in_entity_table = false;
    let mut header_seen = false;

    for line in content.lines() {
        let trimmed = line.trim();
        if trimmed.contains("entity-name") && trimmed.contains("display-name") {
            in_entity_table = true;
            header_seen = false;
            continue;
        }
        if in_entity_table {
            if trimmed.starts_with("|") && trimmed.contains("---") {
                header_seen = true;
                continue;
            }
            if header_seen && trimmed.starts_with('|') {
                let parts: Vec<&str> = trimmed.split('|').collect();
                if parts.len() >= 3 {
                    let entity = parts[1].trim();
                    if !entity.is_empty() && !entity.contains("---") {
                        entities.push(entity.to_string());
                    }
                }
            } else if header_seen && !trimmed.starts_with('|') {
                break;
            }
        }
    }
    entities
}

/// Parse structured errors from `cargo check --message-format=json` output.
pub fn parse_cargo_json_diagnostics(json_output: &str) -> Vec<agent_core::event::CompilerError> {
    // Each line is a JSON object. We want the ones with "reason": "compiler-message"
    // The format is:
    // {"reason":"compiler-message","message":{"code":{"code":"E0308",...},"level":"error","message":"...","spans":[{"file_name":"...","line_start":5,"column_start":10,...}],...}}
    json_output.lines().filter_map(|line| {
        let v: serde_json::Value = serde_json::from_str(line).ok()?;
        if v.get("reason")?.as_str()? != "compiler-message" {
            return None;
        }
        let msg = v.get("message")?;
        let level = msg.get("level")?.as_str()?;
        let severity = match level {
            "error" => ErrorSeverity::Error,
            "warning" => ErrorSeverity::Warning,
            _ => ErrorSeverity::Note,
        };
        let message = msg.get("message")?.as_str()?.to_string();
        let code = msg.get("code")
            .and_then(|c| c.get("code"))
            .and_then(|c| c.as_str())
            .map(|s| s.to_string());
        let spans = msg.get("spans").and_then(|s| s.as_array());
        let (file, line, column) = if let Some(spans) = spans {
            if let Some(span) = spans.first() {
                (
                    span.get("file_name").and_then(|f| f.as_str()).map(|s| s.to_string()),
                    span.get("line_start").and_then(|l| l.as_u64()).map(|l| l as u32),
                    span.get("column_start").and_then(|c| c.as_u64()).map(|c| c as u32),
                )
            } else {
                (None, None, None)
            }
        } else {
            (None, None, None)
        };
        Some(CompilerError {
            file,
            line,
            column,
            code,
            message,
            severity,
        })
    }).collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn strip_code_fences_handles_rust_block() {
        assert_eq!(strip_code_fences("```rust\nfn main() {}\n```"), "fn main() {}");
    }

    #[test]
    fn strip_code_fences_handles_plain_block() {
        assert_eq!(strip_code_fences("```\ncode\n```"), "code");
    }

    #[test]
    fn strip_code_fences_passes_through_clean_content() {
        assert_eq!(strip_code_fences("fn main() {}"), "fn main() {}");
    }

    #[test]
    fn parse_cargo_errors_extracts_error_lines() {
        let stderr = "error[E0308]: mismatched types\n  --> src/lib.rs:5:10\nerror: aborting\nwarning: unused variable\n";
        let (count, errors) = parse_cargo_errors(stderr);
        assert_eq!(count, 1);
        assert_eq!(errors.len(), 2); // error[E0308] and error: aborting
    }

    #[test]
    fn count_warnings_matches_both_patterns() {
        let stderr = "warning[unused]: x\nwarning: field is never read\ninfo: ok\n";
        assert_eq!(count_warnings(stderr), 2);
    }

    #[test]
    fn parse_agents_md_extracts_entity_names() {
        let content = r#"
## Entities

| entity-name | display-name |
|-------------|--------------|
| school | School |
| teacher | Teacher |
| student | Student |

## Other section
"#;
        let entities = parse_entity_names_from_agents_md(content);
        assert_eq!(entities, vec!["school", "teacher", "student"]);
    }

    #[test]
    fn parse_agents_md_handles_empty_content() {
        assert!(parse_entity_names_from_agents_md("no table here").is_empty());
    }

    #[test]
    fn parse_cargo_json_extracts_structured_errors() {
        let json = r#"{"reason":"compiler-message","message":{"code":{"code":"E0308"},"level":"error","message":"mismatched types","spans":[{"file_name":"src/lib.rs","line_start":5,"column_start":10}]}}
{"reason":"build-finished","success":false}"#;
        let errors = parse_cargo_json_diagnostics(json);
        assert_eq!(errors.len(), 1);
        assert_eq!(errors[0].code.as_deref(), Some("E0308"));
        assert_eq!(errors[0].message, "mismatched types");
        assert_eq!(errors[0].file.as_deref(), Some("src/lib.rs"));
        assert_eq!(errors[0].line, Some(5));
        assert_eq!(errors[0].severity, ErrorSeverity::Error);
    }
}
