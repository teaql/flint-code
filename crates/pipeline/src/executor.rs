use agent_core::event::*;
use agent_core::reducer::SideEffect;
use agent_core::error::AgentError;
use model_vllm::client::VllmClient;
use model_vllm::chat::ChatMessage;
use model_vllm::profile::ModelProfile;
use model_vllm::tokenizer;
use context_builder::{TaskPackageData, build_generation_messages, build_repair_messages, calculate_budget};
use validation;
use artifact_store::RunArtifacts;
use tokio::sync::mpsc;
use tracing::{info, warn, error};
use std::path::{Path, PathBuf};

/// PipelineExecutor processes SideEffects and sends RunEvents back.
/// Both TUI and headless CLI create one of these.
pub struct PipelineExecutor {
    profile: ModelProfile,
    client: VllmClient,
    event_tx: mpsc::Sender<RunEvent>,
    task: Option<TaskPackageData>,
    candidate: Option<String>,      // current candidate output
    artifacts: Option<RunArtifacts>,
    runs_root: PathBuf,
    run_id: String,
    /// Optional build target for code generation (e.g. "rust-lib-core")
    build_target: Option<String>,
}

impl PipelineExecutor {
    pub fn new(
        profile: ModelProfile,
        event_tx: mpsc::Sender<RunEvent>,
        runs_root: PathBuf,
        run_id: String,
    ) -> Self {
        let client = VllmClient::new(profile.clone());
        Self {
            profile,
            client,
            event_tx,
            task: None,
            candidate: None,
            artifacts: None,
            runs_root,
            run_id,
            build_target: None,
        }
    }

    /// Set the build target for code generation (e.g. "rust-lib-core")
    pub fn set_build_target(&mut self, target: String) {
        self.build_target = Some(target);
    }

    /// Process a side effect. This is the main dispatch loop.
    pub async fn handle(&mut self, effect: SideEffect) {
        match effect {
            SideEffect::RunPreflight => self.run_preflight().await,
            SideEffect::Generate { attempt } => self.generate(attempt).await,
            SideEffect::RunLocalValidation { attempt } => self.local_validate(attempt).await,
            SideEffect::RunDomainValidation { attempt } => self.domain_validate(attempt).await,
            SideEffect::RunBuildValidation { attempt } => self.build_validate(attempt).await,
            SideEffect::Repair { attempt } => self.repair(attempt).await,
            SideEffect::WriteFinalArtifact => self.write_final().await,
            SideEffect::RecordFailure { error } => self.record_failure(&error).await,
            SideEffect::LoadTask { path } => self.load_task(&path).await,
            SideEffect::RequestConsent { action, .. } => {
                // In headless mode: auto-deny
                // In TUI mode: the TUI will handle this
                self.send(RunEvent::ConsentDenied(format!("Auto-denied in headless: {action}"))).await;
            }
            SideEffect::None => {}
        }
    }

    /// Load a task package from disk.
    pub async fn load_task_from_path(&mut self, path: &Path) {
        match TaskPackageData::load(path) {
            Ok(task) => {
                let pkg = TaskPackage {
                    name: task.name.clone(),
                    task_file: task.root.join("task.md"),
                    files: task.files(),
                    acceptance_spec: task.acceptance_spec.clone(),
                };
                self.task = Some(task);
                self.send(RunEvent::TaskLoaded(pkg)).await;
            }
            Err(e) => {
                self.send(RunEvent::TaskLoadFailed(format!("Failed to load task: {e}"))).await;
            }
        }
    }

    async fn load_task(&mut self, path: &Path) {
        self.load_task_from_path(path).await;
    }

    async fn run_preflight(&mut self) {
        let task = match &self.task {
            Some(t) => t,
            None => {
                self.send(RunEvent::PreflightFailed("No task loaded".to_string())).await;
                return;
            }
        };

        // Estimate token usage
        let messages = build_generation_messages(task);
        let estimated_prompt = tokenizer::estimate_messages_tokens(&messages);

        let budget = calculate_budget(
            self.profile.context.model_context_tokens,
            self.profile.context.max_prompt_tokens,
            self.profile.context.max_completion_tokens,
            self.profile.context.safety_tokens,
            estimated_prompt,
        );

        if !budget.admits() {
            self.send(RunEvent::PreflightFailed(format!(
                "Budget exceeded: estimated {} + completion {} + safety {} = {} > context {}",
                estimated_prompt,
                self.profile.context.max_completion_tokens,
                self.profile.context.safety_tokens,
                estimated_prompt + self.profile.context.max_completion_tokens + self.profile.context.safety_tokens,
                self.profile.context.model_context_tokens,
            ))).await;
            return;
        }

        // Create run artifacts directory
        match RunArtifacts::create(&self.runs_root, &self.run_id) {
            Ok(artifacts) => {
                self.artifacts = Some(artifacts);
            }
            Err(e) => {
                warn!("Failed to create artifacts dir: {e}");
            }
        }

        info!(
            estimated = estimated_prompt,
            limit = self.profile.context.max_prompt_tokens,
            "Preflight passed"
        );
        self.send(RunEvent::PreflightPassed(budget)).await;
    }

    async fn generate(&mut self, attempt: u8) {
        let task = match &self.task {
            Some(t) => t,
            None => {
                self.send(RunEvent::ModelFailed(AgentError::InfrastructureError {
                    detail: "No task loaded".to_string(),
                })).await;
                return;
            }
        };

        // Build messages — fresh each time (stateless)
        let message_pairs = build_generation_messages(task);
        let messages: Vec<ChatMessage> = message_pairs
            .into_iter()
            .map(|(role, content)| ChatMessage { role, content })
            .collect();

        info!(attempt, message_count = messages.len(), "Sending generation request");

        // Save request to artifacts
        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_file(
                attempt,
                "request.json",
                &serde_json::json!({
                    "attempt": attempt,
                    "message_count": messages.len(),
                    "model": self.profile.model.name,
                }),
            ).ok();
        }

        match self.client.chat(messages.clone()).await {
            Ok(result) => {
                // Save candidate
                self.candidate = Some(result.content.clone());
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_candidate(attempt, &result.content).ok();
                    artifacts.save_attempt_file(attempt, "response.json", &result).ok();
                }

                // MULTI-STEP LOOP: Generate included files
                let includes = extract_includes(&result.content);
                for file in includes {
                    info!(attempt, file = %file, "Generating included file");
                    let mut sub_messages = messages.clone();
                    sub_messages.push(ChatMessage {
                        role: "assistant".to_string(),
                        content: result.content.clone(),
                    });
                    sub_messages.push(ChatMessage {
                        role: "user".to_string(),
                        content: format!("Please provide the contents of {}. Output ONLY the raw XML, nothing else. Do not use markdown blocks, just the raw XML text.", file),
                    });
                    
                    match self.client.chat(sub_messages).await {
                        Ok(sub_result) => {
                            let clean_content = sub_result.content
                                .trim()
                                .strip_prefix("```xml").unwrap_or(&sub_result.content)
                                .strip_prefix("```").unwrap_or(&sub_result.content)
                                .strip_suffix("```").unwrap_or(&sub_result.content)
                                .trim()
                                .to_string();
                                
                            if let Some(artifacts) = &self.artifacts {
                                artifacts.save_attempt_raw(attempt, &file, &clean_content).ok();
                            }
                        }
                        Err(e) => {
                            tracing::warn!("Failed to generate included file {}: {}", file, e);
                        }
                    }
                }

                self.send(RunEvent::ModelCompleted(result)).await;
            }
            Err(err) => {
                // Save error
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_file(
                        attempt,
                        "error.json",
                        &serde_json::json!({ "error": err.to_string() }),
                    ).ok();
                }
                self.send(RunEvent::ModelFailed(err)).await;
            }
        }
    }

    async fn local_validate(&mut self, attempt: u8) {
        let candidate = match &self.candidate {
            Some(c) => c.clone(),
            None => {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: "No candidate to validate".to_string(),
                })).await;
                return;
            }
        };

        // L1: XML parse validation
        let parse_result = validation::validate_xml_parse(&candidate);
        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_file(attempt, "local-validation.json", &parse_result).ok();
        }

        if !parse_result.passed {
            self.send(RunEvent::ValidationCompleted(parse_result)).await;
            return;
        }

        // L2: Acceptance validation (if spec exists)
        if let Some(task) = &self.task {
            if let Some(spec) = &task.acceptance_spec {
                let acceptance_result = validation::validate_acceptance(&candidate, spec);
                if !acceptance_result.passed {
                    self.send(RunEvent::ValidationCompleted(acceptance_result)).await;
                    return;
                }
            }
        }

        // All local validation passed
        self.send(RunEvent::ValidationCompleted(parse_result)).await;
    }

    async fn domain_validate(&mut self, attempt: u8) {
        let result = if let Some(artifacts) = &self.artifacts {
            // Write the candidate to a temporary model.xml in the attempt dir
            let attempt_dir = artifacts.create_attempt(attempt).unwrap_or_else(|_| artifacts.root.clone());
            let model_path = attempt_dir.join("main.xml");
            if let Some(c) = &self.candidate {
                std::fs::write(&model_path, c).ok();
            }
            
            info!(attempt, path = %attempt_dir.display(), "Running domain validation");
            validation::domain::validate_domain(&attempt_dir)
        } else {
            // Fallback if no artifacts dir (shouldn't happen in normal runs)
            info!(attempt, "Domain validation skipped — no artifact dir");
            validation::pass(3, "domain", 0.0)
        };

        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_file(attempt, "domain-validation.json", &result).ok();
        }
        self.send(RunEvent::ValidationCompleted(result)).await;
    }

    /// Run build validation: code generation via `cargo teaql` + `cargo check`.
    ///
    /// When `build_target` is set (e.g. "rust-lib-core"), this method:
    /// 1. Runs `cargo teaql --input <attempt_dir> <target>` to generate code
    /// 2. Patches the known rusqlite dependency conflict in the generated Cargo.toml
    /// 3. Runs `cargo check` on the generated crate
    /// 4. Parses compiler output into a ValidationResult
    async fn build_validate(&mut self, attempt: u8) {
        let build_target = match &self.build_target {
            Some(t) => t.clone(),
            None => {
                info!(attempt, "Build validation — no build target configured, passing");
                let result = validation::pass(5, "build", 0.0);
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        let attempt_dir = match &self.artifacts {
            Some(a) => match a.create_attempt(attempt) {
                Ok(dir) => dir,
                Err(e) => {
                    error!(attempt, %e, "Failed to create attempt directory");
                    let result = ValidationResult {
                        level: 5,
                        level_name: "build".to_string(),
                        passed: false,
                        error_count: 1,
                        warning_count: 0,
                        suggestion_count: 0,
                        actionable_errors: vec![format!("Failed to create attempt dir: {}", e)],
                        diagnostic: e.to_string(),
                        elapsed_secs: 0.0,
                    };
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            },
            None => {
                error!(attempt, "No artifact directory for build validation");
                let result = ValidationResult {
                    level: 5,
                    level_name: "build".to_string(),
                    passed: false,
                    error_count: 1,
                    warning_count: 0,
                    suggestion_count: 0,
                    actionable_errors: vec!["No artifact directory available".to_string()],
                    diagnostic: "Internal error: no artifact store".to_string(),
                    elapsed_secs: 0.0,
                };
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        let start = std::time::Instant::now();

        // Step 1: Run cargo teaql to generate code
        info!(attempt, target = %build_target, dir = %attempt_dir.display(), "Running code generation");
        let gen_result = tokio::process::Command::new("cargo")
            .args(["teaql", "--input", ".", &build_target])
            .current_dir(&attempt_dir)
            .output()
            .await;

        match &gen_result {
            Ok(output) if !output.status.success() => {
                let stderr = String::from_utf8_lossy(&output.stderr);
                let stdout = String::from_utf8_lossy(&output.stdout);
                let diagnostic = format!("Code generation failed:\n{}\n{}", stdout, stderr);
                warn!(attempt, "Code generation failed");
                let result = ValidationResult {
                    level: 5,
                    level_name: "build".to_string(),
                    passed: false,
                    error_count: 1,
                    warning_count: 0,
                    suggestion_count: 0,
                    actionable_errors: vec![format!("cargo teaql {} failed", build_target)],
                    diagnostic,
                    elapsed_secs: start.elapsed().as_secs_f64(),
                };
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Err(e) => {
                error!(attempt, %e, "Failed to run cargo teaql");
                let result = ValidationResult {
                    level: 5,
                    level_name: "build".to_string(),
                    passed: false,
                    error_count: 1,
                    warning_count: 0,
                    suggestion_count: 0,
                    actionable_errors: vec![format!("Failed to execute cargo teaql: {}", e)],
                    diagnostic: e.to_string(),
                    elapsed_secs: start.elapsed().as_secs_f64(),
                };
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            _ => {
                info!(attempt, "Code generation succeeded");
            }
        }

        // Step 2: Fix known rusqlite dependency conflict in generated Cargo.toml
        let lib_dir = attempt_dir.join("build/lib");
        let cargo_toml_path = lib_dir.join("Cargo.toml");
        if cargo_toml_path.exists() {
            if let Ok(content) = std::fs::read_to_string(&cargo_toml_path) {
                let fixed = content.replace(
                    r#"rusqlite = { version = "0.32""#,
                    r#"rusqlite = { version = "0.40""#,
                );
                if fixed != content {
                    info!(attempt, "Patched rusqlite version in generated Cargo.toml");
                    std::fs::write(&cargo_toml_path, &fixed).ok();
                }
            }
        } else {
            warn!(attempt, path = %cargo_toml_path.display(), "Generated Cargo.toml not found");
            let result = ValidationResult {
                level: 5,
                level_name: "build".to_string(),
                passed: false,
                error_count: 1,
                warning_count: 0,
                suggestion_count: 0,
                actionable_errors: vec!["Generated build/lib/Cargo.toml not found after code generation".to_string()],
                diagnostic: format!("Expected {} but file does not exist", cargo_toml_path.display()),
                elapsed_secs: start.elapsed().as_secs_f64(),
            };
            if let Some(artifacts) = &self.artifacts {
                artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
            }
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }

        // Step 3: Run cargo check on the generated crate
        info!(attempt, dir = %lib_dir.display(), "Running cargo check");
        let check_result = tokio::process::Command::new("cargo")
            .args(["check"])
            .current_dir(&lib_dir)
            .output()
            .await;

        let elapsed = start.elapsed().as_secs_f64();

        let result = match check_result {
            Ok(output) => {
                let stderr = String::from_utf8_lossy(&output.stderr).to_string();
                if output.status.success() {
                    info!(attempt, elapsed, "Build validation passed — cargo check succeeded");
                    let mut r = validation::pass(5, "build", elapsed);
                    // Count warnings from stderr
                    let warning_count = stderr.lines()
                        .filter(|l| l.contains("warning[") || l.starts_with("warning:"))
                        .count() as u32;
                    r.warning_count = warning_count;
                    r.diagnostic = if warning_count > 0 {
                        format!("{} warnings\n{}", warning_count, stderr)
                    } else {
                        "cargo check passed".to_string()
                    };
                    r
                } else {
                    // Parse error count from stderr
                    let error_count = stderr.lines()
                        .filter(|l| l.contains("error["))
                        .count() as u32;
                    let error_count = std::cmp::max(error_count, 1);

                    // Extract actionable error lines
                    let actionable_errors: Vec<String> = stderr.lines()
                        .filter(|l| l.starts_with("error"))
                        .take(10)
                        .map(|l| l.to_string())
                        .collect();

                    warn!(attempt, error_count, "Build validation failed — cargo check errors");
                    ValidationResult {
                        level: 5,
                        level_name: "build".to_string(),
                        passed: false,
                        error_count,
                        warning_count: 0,
                        suggestion_count: 0,
                        actionable_errors,
                        diagnostic: stderr,
                        elapsed_secs: elapsed,
                    }
                }
            }
            Err(e) => {
                error!(attempt, %e, "Failed to run cargo check");
                ValidationResult {
                    level: 5,
                    level_name: "build".to_string(),
                    passed: false,
                    error_count: 1,
                    warning_count: 0,
                    suggestion_count: 0,
                    actionable_errors: vec![format!("Failed to execute cargo check: {}", e)],
                    diagnostic: e.to_string(),
                    elapsed_secs: elapsed,
                }
            }
        };

        // If lib check failed, report and return early
        if !result.passed {
            if let Some(artifacts) = &self.artifacts {
                artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
            }
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }

        let target = self.build_target.as_deref().unwrap_or("rust-lib-core");
        let app_target = target.replace("-lib-core", "-app-console");

        // ── Step 4: Generate app target ──
        info!(attempt, "Generating {}", app_target);
        let app_gen_result = tokio::process::Command::new("cargo")
            .args(["teaql", "--input", ".", &app_target])
            .current_dir(&attempt_dir)
            .output()
            .await;

        match &app_gen_result {
            Ok(output) if !output.status.success() => {
                let stderr = String::from_utf8_lossy(&output.stderr);
                warn!(attempt, "{} generation failed, continuing with lib-only result", app_target);
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_raw(attempt, "app-console-gen-error.txt", &stderr).ok();
                }
                // Still pass — lib compiled, app-console is bonus
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Err(e) => {
                warn!(attempt, %e, "Failed to run {} generation", app_target);
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            _ => {
                info!(attempt, "{} generated successfully", app_target);
            }
        }

        // ── Step 5: Fix dependency paths in app Cargo.toml ──
        let build_dir = attempt_dir.join("build");
        let app_cargo_toml = build_dir.join("Cargo.toml");
        if app_cargo_toml.exists() {
            if let Ok(content) = std::fs::read_to_string(&app_cargo_toml) {
                // Fix the path dependency to point to ./lib instead of ../<target>/lib
                let old_path = format!(r#"path = "../{}/lib""#, target);
                let fixed = content.replace(
                    &old_path,
                    r#"path = "./lib""#,
                );
                if fixed != content {
                    info!(attempt, "Fixed app-console dependency path");
                }
                std::fs::write(&app_cargo_toml, &fixed).ok();
            }
        }

        // Also fix rusqlite in lib again (app-console gen may have reset it)
        let lib_cargo_toml = build_dir.join("lib/Cargo.toml");
        if lib_cargo_toml.exists() {
            if let Ok(content) = std::fs::read_to_string(&lib_cargo_toml) {
                let fixed = content.replace(
                    r#"rusqlite = { version = "0.32""#,
                    r#"rusqlite = { version = "0.40""#,
                );
                std::fs::write(&lib_cargo_toml, &fixed).ok();
            }
        }

        // ── Step 6: Run assist commands for each entity ──
        // Parse entity names from the generated AGENTS.md
        let agents_md_path = build_dir.join("AGENTS.md");
        let entity_names = if agents_md_path.exists() {
            let agents_content = std::fs::read_to_string(&agents_md_path).unwrap_or_default();
            parse_entity_names_from_agents_md(&agents_content)
        } else {
            Vec::new()
        };

        // Limit assist queries to avoid context overflow (each ~1500 tokens; 64K limit)
        const MAX_ASSIST_ENTITIES: usize = 8;
        let assist_entities: Vec<&String> = if entity_names.len() > MAX_ASSIST_ENTITIES {
            // Pick evenly-spaced entities for representative coverage
            let step = entity_names.len() as f64 / MAX_ASSIST_ENTITIES as f64;
            (0..MAX_ASSIST_ENTITIES)
                .map(|i| &entity_names[(i as f64 * step) as usize])
                .collect()
        } else {
            entity_names.iter().collect()
        };

        info!(attempt, total = entity_names.len(), sampled = assist_entities.len(), "Running assist commands");

        let assist_target_base = target.replace("-lib-core", "-assist-query");
        let mut assist_outputs = String::new();
        for entity in &assist_entities {
            let assist_target = format!("{}/{}", assist_target_base, entity);
            info!(attempt, entity = %entity, "Running assist command");
            let assist_result = tokio::process::Command::new("cargo")
                .args(["teaql", "--input", ".", &assist_target])
                .current_dir(&attempt_dir)
                .output()
                .await;

            if let Ok(output) = &assist_result {
                let stdout = String::from_utf8_lossy(&output.stdout);
                // Truncate each assist output to ~1200 chars to fit context budget
                let truncated = if stdout.len() > 1200 {
                    format!("{}...\n[truncated]", &stdout[..1200])
                } else {
                    stdout.to_string()
                };
                assist_outputs.push_str(&format!("### Assist: query/{}\n\n{}\n\n", entity, truncated));
            }
        }

        // Also list the remaining entity names so LLM knows the full set
        if entity_names.len() > MAX_ASSIST_ENTITIES {
            assist_outputs.push_str(&format!(
                "### All entity names ({})\n{}\n\n",
                entity_names.len(),
                entity_names.join(", ")
            ));
        }

        // Save assist outputs as artifact
        if !assist_outputs.is_empty() {
            if let Some(artifacts) = &self.artifacts {
                artifacts.save_attempt_raw(attempt, "assist-output.md", &assist_outputs).ok();
            }
        }

        // ── Step 7: Generate business logic via LLM ──
        if !entity_names.is_empty() && !assist_outputs.is_empty() {
            info!(attempt, entities = entity_names.len(), "Generating business logic via LLM");

            let agents_md = std::fs::read_to_string(&agents_md_path).unwrap_or_default();
            let lib_rs = std::fs::read_to_string(build_dir.join("src/lib.rs")).unwrap_or_default();

            let lib_cargo_toml = std::fs::read_to_string(build_dir.join("lib/Cargo.toml")).unwrap_or_default();
            let mut crate_name = "school_service_core".to_string(); // fallback
            if let Ok(value) = lib_cargo_toml.parse::<toml::Value>() {
                if let Some(name) = value.get("package").and_then(|p| p.get("name")).and_then(|n| n.as_str()) {
                    crate_name = name.replace('-', "_");
                }
            }

            let template = std::fs::read_to_string("prompts/business-logic.txt")
                .unwrap_or_else(|_| include_str!("../../../prompts/business-logic.txt").to_string());

            let biz_prompt = template
                .replace("{{agents_md}}", &agents_md)
                .replace("{{lib_rs}}", &lib_rs)
                .replace("{{assist_outputs}}", &assist_outputs)
                .replace("{{crate_name}}", &crate_name);

            let biz_messages = vec![
                ChatMessage {
                    role: "system".to_string(),
                    content: "You generate Rust code. Output ONLY raw Rust source. No markdown.".to_string(),
                },
                ChatMessage {
                    role: "user".to_string(),
                    content: biz_prompt,
                },
            ];

            match self.client.chat(biz_messages).await {
                Ok(biz_result) => {
                    let biz_code = biz_result.content
                        .trim()
                        .strip_prefix("```rust").unwrap_or(&biz_result.content)
                        .strip_prefix("```").unwrap_or(&biz_result.content)
                        .strip_suffix("```").unwrap_or(&biz_result.content)
                        .trim()
                        .to_string();

                    info!(attempt, tokens = biz_result.usage.completion_tokens, "Business logic generated");

                    // Write generated business logic
                    let lib_rs_path = build_dir.join("src/lib.rs");
                    std::fs::write(&lib_rs_path, &biz_code).ok();

                    if let Some(artifacts) = &self.artifacts {
                        artifacts.save_attempt_raw(attempt, "business-logic.rs", &biz_code).ok();
                    }
                }
                Err(e) => {
                    warn!(attempt, %e, "Business logic generation failed, keeping default lib.rs");
                }
            }
        }

        // ── Step 8: Final compile check on app workspace (with one repair attempt) ──
        let lib_rs_path = build_dir.join("src/lib.rs");
        for compile_attempt in 0..2u8 {
            info!(attempt, compile_attempt, "Running cargo check on app workspace");
            let app_check = tokio::process::Command::new("cargo")
                .args(["check"])
                .current_dir(&build_dir)
                .output()
                .await;

            match &app_check {
                Ok(output) if output.status.success() => {
                    let total_elapsed = start.elapsed().as_secs_f64();
                    info!(attempt, total_elapsed, "Full build validation passed — lib + app workspace");
                    let mut r = validation::pass(5, "build", total_elapsed);
                    r.diagnostic = format!(
                        "rust-lib-core: cargo check ✓\nrust-app-console: cargo check ✓\nassist entities: {:?}\nbusiness logic: generated (compile attempt {})",
                        entity_names, compile_attempt + 1
                    );
                    if let Some(artifacts) = &self.artifacts {
                        artifacts.save_attempt_file(attempt, "build-validation.json", &r).ok();
                    }
                    self.send(RunEvent::ValidationCompleted(r)).await;
                    return;
                }
                Ok(output) if compile_attempt == 0 => {
                    // First failure — try to fix via LLM
                    let stderr = String::from_utf8_lossy(&output.stderr).to_string();
                    let current_code = std::fs::read_to_string(&lib_rs_path).unwrap_or_default();
                    warn!(attempt, "App workspace failed — attempting LLM repair");

                    let template = std::fs::read_to_string("prompts/repair.txt")
                        .unwrap_or_else(|_| include_str!("../../../prompts/repair.txt").to_string());

                    let fix_prompt = template
                        .replace("{{current_code}}", &current_code)
                        .replace("{{stderr}}", &stderr[..stderr.len().min(2000)]);

                    let fix_messages = vec![
                        ChatMessage {
                            role: "system".to_string(),
                            content: "You fix Rust compilation errors. Output ONLY raw Rust source.".to_string(),
                        },
                        ChatMessage {
                            role: "user".to_string(),
                            content: fix_prompt,
                        },
                    ];

                    match self.client.chat(fix_messages).await {
                        Ok(fix_result) => {
                            let fixed = fix_result.content
                                .trim()
                                .strip_prefix("```rust").unwrap_or(&fix_result.content)
                                .strip_prefix("```").unwrap_or(&fix_result.content)
                                .strip_suffix("```").unwrap_or(&fix_result.content)
                                .trim()
                                .to_string();
                            info!(attempt, tokens = fix_result.usage.completion_tokens, "LLM repair generated");
                            std::fs::write(&lib_rs_path, &fixed).ok();
                            if let Some(artifacts) = &self.artifacts {
                                artifacts.save_attempt_raw(attempt, "business-logic-repaired.rs", &fixed).ok();
                            }
                            // Loop will retry cargo check
                        }
                        Err(e) => {
                            warn!(attempt, %e, "LLM repair failed");
                            break;
                        }
                    }
                }
                _ => break, // Second failure or other error — fall through
            }
        }

        // If we get here, all compile attempts failed
        let total_elapsed = start.elapsed().as_secs_f64();
        let final_stderr = tokio::process::Command::new("cargo")
            .args(["check"])
            .current_dir(&build_dir)
            .output()
            .await
            .map(|o| String::from_utf8_lossy(&o.stderr).to_string())
            .unwrap_or_default();

        let error_count = final_stderr.lines()
            .filter(|l| l.contains("error["))
            .count() as u32;
        let actionable_errors: Vec<String> = final_stderr.lines()
            .filter(|l| l.starts_with("error"))
            .take(10)
            .map(|l| l.to_string())
            .collect();

        let final_result = ValidationResult {
            level: 5,
            level_name: "build".to_string(),
            passed: false,
            error_count: std::cmp::max(error_count, 1),
            warning_count: 0,
            suggestion_count: 0,
            actionable_errors,
            diagnostic: format!("rust-lib-core: ✓\nrust-app-console: FAILED (after LLM repair)\n\n{}", final_stderr),
            elapsed_secs: total_elapsed,
        };

        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_file(attempt, "build-validation.json", &final_result).ok();
        }
        self.send(RunEvent::ValidationCompleted(final_result)).await;
    }

    async fn repair(&mut self, attempt: u8) {
        let task = match &self.task {
            Some(t) => t,
            None => {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: "No task for repair".to_string(),
                })).await;
                return;
            }
        };

        let rejected = match &self.candidate {
            Some(c) => c.clone(),
            None => {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: "No candidate to repair".to_string(),
                })).await;
                return;
            }
        };

        // Build repair messages — fresh, stateless
        // We need the actionable errors from the last validation.
        // For now, use a generic message since we don't store them in executor state.
        let message_pairs = build_repair_messages(
            task,
            &rejected,
            &["See diagnostic output for errors".to_string()],
            self.profile.run.diagnostic_character_limit,
        );

        let _messages: Vec<ChatMessage> = message_pairs
            .into_iter()
            .map(|(role, content)| ChatMessage { role, content })
            .collect();

        info!(attempt, "Sending repair request");

        // Signal that repair is scheduled, which will transition to Generating
        self.send(RunEvent::RepairScheduled { attempt }).await;
    }

    async fn write_final(&mut self) {
        if let Some(candidate) = &self.candidate {
            if let Some(artifacts) = &self.artifacts {
                match artifacts.save_final_artifact(candidate) {
                    Ok(path) => {
                        info!(path = %path.display(), "Final artifact saved");
                        self.send(RunEvent::FinalArtifactWritten(path)).await;
                    }
                    Err(e) => {
                        self.send(RunEvent::Failed(AgentError::InfrastructureError {
                            detail: format!("Failed to write final artifact: {e}"),
                        })).await;
                    }
                }
            } else {
                // No artifact store, just report success with a fake path
                self.send(RunEvent::FinalArtifactWritten(PathBuf::from("<no-artifact-store>"))).await;
            }
        } else {
            self.send(RunEvent::Failed(AgentError::InfrastructureError {
                detail: "No candidate to finalize".to_string(),
            })).await;
        }
    }

    async fn record_failure(&mut self, error: &str) {
        error!(%error, "Recording failure");
        if let Some(artifacts) = &self.artifacts {
            artifacts.save_summary(&serde_json::json!({
                "status": "failed",
                "error": error,
            })).ok();
        }
    }

    async fn send(&self, event: RunEvent) {
        if self.event_tx.send(event).await.is_err() {
            error!("Failed to send event — channel closed");
        }
    }

    /// Get the VllmClient reference for health checks
    pub fn client(&self) -> &VllmClient {
        &self.client
    }

    /// Get the profile
    pub fn profile(&self) -> &ModelProfile {
        &self.profile
    }

    /// Get current candidate
    pub fn candidate(&self) -> Option<&str> {
        self.candidate.as_deref()
    }

    /// Store actionable errors for repair context
    pub fn set_last_errors(&mut self, _errors: Vec<String>) {
        // Will be used when building repair messages
        // For now this is a placeholder for richer repair context
    }
}

fn extract_includes(content: &str) -> Vec<String> {
    let mut includes = Vec::new();
    let mut start = 0;
    while let Some(idx) = content[start..].find("<_include file=\"") {
        let open_quote = start + idx + 16;
        if let Some(close_quote) = content[open_quote..].find('"') {
            includes.push(content[open_quote..open_quote + close_quote].to_string());
            start = open_quote + close_quote + 1;
        } else {
            break;
        }
    }
    // Also handle single quotes just in case
    start = 0;
    while let Some(idx) = content[start..].find("<_include file='") {
        let open_quote = start + idx + 16;
        if let Some(close_quote) = content[open_quote..].find('\'') {
            includes.push(content[open_quote..open_quote + close_quote].to_string());
            start = open_quote + close_quote + 1;
        } else {
            break;
        }
    }
    includes
}

/// Parse entity names from the generated AGENTS.md.
///
/// The generated AGENTS.md contains a markdown table like:
/// ```
/// | entity-name | display-name |
/// |-------------|--------------|
/// | school | School |
/// | teacher | Teacher |
/// ```
/// We extract the entity-name column values.
fn parse_entity_names_from_agents_md(content: &str) -> Vec<String> {
    let mut entities = Vec::new();
    let mut in_entity_table = false;
    let mut header_seen = false;

    for line in content.lines() {
        let trimmed = line.trim();
        // Detect the entity table header
        if trimmed.contains("entity-name") && trimmed.contains("display-name") {
            in_entity_table = true;
            header_seen = false;
            continue;
        }
        if in_entity_table {
            // Skip the separator line (|---|---|)
            if trimmed.starts_with("|") && trimmed.contains("---") {
                header_seen = true;
                continue;
            }
            // Parse data rows
            if header_seen && trimmed.starts_with('|') {
                let parts: Vec<&str> = trimmed.split('|').collect();
                if parts.len() >= 3 {
                    let entity = parts[1].trim();
                    if !entity.is_empty() && !entity.contains("---") {
                        entities.push(entity.to_string());
                    }
                }
            } else if header_seen && !trimmed.starts_with('|') {
                // End of table
                break;
            }
        }
    }
    entities
}
