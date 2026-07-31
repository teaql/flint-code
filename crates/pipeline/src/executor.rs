use agent_core::error::AgentError;
use agent_core::event::*;
use agent_core::reducer::SideEffect;
use artifact_store::RunArtifacts;
use context_builder::{
    TaskPackageData, build_generation_messages, build_repair_messages, calculate_budget,
    extract_includes,
};
use model_vllm::backend::ModelClient;
use model_vllm::chat::ChatMessage;
use model_vllm::profile::ModelProfile;
use model_vllm::tokenizer;
use std::path::{Path, PathBuf};
use tokio::sync::mpsc;
use tracing::{error, info, warn};
use validation;

use crate::build_steps::{self, BuildContext};
use crate::process_runner::ProcessRunner;

/// PipelineExecutor processes SideEffects and sends RunEvents back.
/// Both TUI and headless CLI create one of these.
pub struct PipelineExecutor {
    profile: ModelProfile,
    client: ModelClient,
    event_tx: mpsc::Sender<RunEvent>,
    runner: ProcessRunner,
    task: Option<TaskPackageData>,
    candidate: Option<String>, // current candidate output
    artifacts: Option<RunArtifacts>,
    runs_root: PathBuf,
    run_id: String,
    /// Optional build target for code generation (e.g. "rust-lib-core")
    build_target: Option<String>,
    /// Optional patches to apply to generated Cargo.toml files
    patches: Option<std::collections::HashMap<String, String>>,
    repair_messages: Option<Vec<ChatMessage>>,
    last_actionable_errors: Vec<String>,
    /// Workspace file-access guard — enforces read/write policies when a task
    /// ships a `workspace-manifest.toml`.
    workspace_guard: Option<workspace_guard::WorkspaceGuard>,
}

impl PipelineExecutor {
    pub fn new(
        profile: ModelProfile,
        event_tx: mpsc::Sender<RunEvent>,
        runs_root: PathBuf,
        run_id: String,
    ) -> Result<Self, AgentError> {
        let client = ModelClient::from_profile(profile.clone())?;
        let runner = ProcessRunner::new(event_tx.clone());
        Ok(Self {
            profile,
            client,
            event_tx,
            runner,
            task: None,
            candidate: None,
            artifacts: None,
            runs_root,
            run_id,
            build_target: None,
            patches: None,
            repair_messages: None,
            last_actionable_errors: Vec::new(),
            workspace_guard: None,
        })
    }

    /// Set the build target for code generation (e.g. "rust-lib-core")
    pub fn set_build_target(&mut self, target: String) {
        self.build_target = Some(target);
    }

    /// Set patches for generated Cargo.toml files
    pub fn set_patches(&mut self, patches: std::collections::HashMap<String, String>) {
        self.patches = Some(patches);
    }

    /// Process a side effect. This is the main dispatch loop.
    ///
    /// Each handler is expected to send at least one `RunEvent`. If a handler
    /// returns without sending an event (e.g. due to a missed error path),
    /// the reducer's state machine stalls. The handlers below are audited to
    /// always send an event on every code path.
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
                self.send(RunEvent::ConsentDenied(format!(
                    "Auto-denied in headless: {action}"
                )))
                .await;
            }
            SideEffect::None => {}
        }
    }

    /// Load a task package from disk.
    pub async fn load_task_from_path(&mut self, path: &Path) {
        match TaskPackageData::load(path) {
            Ok(task) => self.load_task_data(task, false).await,
            Err(e) => {
                self.send(RunEvent::TaskLoadFailed(format!(
                    "Failed to load task: {e}"
                )))
                .await;
            }
        }
    }

    /// Load an in-memory task entered by an interactive client.
    pub async fn load_prompt(&mut self, name: impl Into<String>, prompt: impl Into<String>) {
        let workspace_root = std::env::current_dir().unwrap_or_else(|_| PathBuf::from("."));
        let task = TaskPackageData::from_prompt(name, prompt, workspace_root);
        self.load_task_data(task, true).await;
    }

    async fn load_task_data(&mut self, task: TaskPackageData, in_memory: bool) {
        let pkg = TaskPackage {
            name: task.name.clone(),
            task_file: if in_memory {
                PathBuf::from("<interactive-input>")
            } else {
                task.root.join("task.md")
            },
            files: if in_memory { Vec::new() } else { task.files() },
            acceptance_spec: task.acceptance_spec.clone(),
        };
        // Initialize workspace guard if the task ships a manifest.
        if let Some(ws_manifest) = &task.workspace_manifest {
            let manifest = workspace_guard::Manifest {
                workspace_root: task.root.clone(),
                readable: ws_manifest.readable.clone(),
                writable: ws_manifest.writable.clone(),
                denied: ws_manifest.denied.clone(),
                max_single_file_bytes: ws_manifest.max_single_file_bytes,
                max_total_read_bytes: ws_manifest.max_total_read_bytes,
                follow_symlinks: false,
                recursive_discovery: ws_manifest.recursive_discovery,
            };
            match workspace_guard::WorkspaceGuard::new(manifest) {
                Ok(guard) => {
                    info!("Workspace guard active for {}", task.root.display());
                    self.workspace_guard = Some(guard);
                }
                Err(e) => {
                    warn!("Failed to initialize workspace guard: {e}");
                }
            }
        }
        self.task = Some(task);
        self.send(RunEvent::TaskLoaded(pkg)).await;
    }

    async fn load_task(&mut self, path: &Path) {
        self.load_task_from_path(path).await;
    }

    async fn run_preflight(&mut self) {
        let task = match &self.task {
            Some(t) => t,
            None => {
                self.send(RunEvent::PreflightFailed("No task loaded".to_string()))
                    .await;
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
                estimated_prompt
                    + self.profile.context.max_completion_tokens
                    + self.profile.context.safety_tokens,
                self.profile.context.model_context_tokens,
            )))
            .await;
            return;
        }

        // Check cargo-teaql availability if domain validation will be needed.
        // This gives a clear error instead of failing mid-pipeline.
        if let Some(task) = &self.task {
            let needs_domain = task.acceptance_spec.is_some()
                || task.grammar_example.is_some();
            if needs_domain {
                match std::process::Command::new("cargo")
                    .args(["teaql", "--version"])
                    .output()
                {
                    Ok(out) if out.status.success() => {
                        let version = String::from_utf8_lossy(&out.stdout);
                        info!(version = %version.trim(), "cargo-teaql available");
                    }
                    Ok(_) | Err(_) => {
                        warn!("cargo-teaql not found; domain validation may fail. \
                               Install with: cargo install cargo-teaql --version 2.0.10");
                    }
                }
            }
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
                }))
                .await;
                return;
            }
        };

        // Build messages — fresh each time (stateless)
        let messages: Vec<ChatMessage> = if let Some(repair_msgs) = self.repair_messages.take() {
            repair_msgs
        } else {
            build_generation_messages(task)
                .into_iter()
                .map(|(role, content)| ChatMessage { role, content })
                .collect()
        };

        info!(
            attempt,
            message_count = messages.len(),
            "Sending generation request"
        );

        // Save request to artifacts
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(
                    attempt,
                    "request.json",
                    &serde_json::json!({
                        "attempt": attempt,
                        "message_count": messages.len(),
                        "model": self.profile.model.name,
                        "messages": messages,
                    }),
                )
                .ok();
        }

        // Use streaming to provide incremental progress feedback.
        // Fall back to non-streaming if streaming setup fails.
        let start = std::time::Instant::now();
        match self.client.chat_stream(messages.clone()).await {
            Ok(mut rx) => {
                let mut content = String::new();
                let mut reasoning_content: Option<String> = None;
                let mut finish_reason = "unknown".to_string();
                let mut usage = TokenUsage::default();

                while let Some(stream_event) = rx.recv().await {
                    match stream_event {
                        model_vllm::client::StreamEvent::Token(tok) => {
                            content.push_str(&tok);
                            self.send(RunEvent::ModelToken(tok)).await;
                        }
                        model_vllm::client::StreamEvent::ReasoningToken(tok) => {
                            reasoning_content
                                .get_or_insert_with(String::new)
                                .push_str(&tok);
                        }
                        model_vllm::client::StreamEvent::Done {
                            content: final_content,
                            reasoning_content: final_reasoning,
                            finish_reason: fr,
                            usage: stream_usage,
                        } => {
                            content = final_content;
                            reasoning_content = final_reasoning;
                            finish_reason = fr;
                            if let Some(u) = stream_usage {
                                usage = TokenUsage {
                                    prompt_tokens: u.prompt_tokens,
                                    completion_tokens: u.completion_tokens,
                                    total_tokens: u.total_tokens,
                                };
                            }
                        }
                        model_vllm::client::StreamEvent::Error(e) => {
                            error!(attempt, error = %e, "Stream error");
                            if let Some(artifacts) = &self.artifacts {
                                artifacts
                                    .save_attempt_file(
                                        attempt,
                                        "error.json",
                                        &serde_json::json!({ "error": e }),
                                    )
                                    .ok();
                            }
                            self.send(RunEvent::ModelFailed(
                                AgentError::InfrastructureError { detail: e },
                            ))
                            .await;
                            return;
                        }
                    }
                }

                let elapsed = start.elapsed().as_secs_f64();

                if finish_reason != "stop" {
                    self.send(RunEvent::ModelFailed(AgentError::IncompleteGeneration {
                        reason: finish_reason,
                    }))
                    .await;
                    return;
                }

                if content.trim().is_empty() {
                    self.send(RunEvent::ModelFailed(AgentError::IncompleteGeneration {
                        reason: "empty content with finish_reason=stop".to_string(),
                    }))
                    .await;
                    return;
                }

                let mut clean_content = content.clone();
                if let Some(end) = clean_content.rfind("</root>") {
                    if let Some(start) = clean_content.find("<?xml") {
                        if start < end {
                            clean_content = clean_content[start..end + 7].to_string();
                        } else if let Some(root_start) = clean_content.find("<root") {
                            clean_content = clean_content[root_start..end + 7].to_string();
                        }
                    } else if let Some(root_start) = clean_content.find("<root") {
                        clean_content = clean_content[root_start..end + 7].to_string();
                    } else {
                        clean_content = clean_content[..end + 7].to_string();
                    }
                }

                let result = ModelResult {
                    content: clean_content,
                    reasoning_content,
                    finish_reason,
                    usage,
                    elapsed_secs: elapsed,
                    http_status: 200,
                };

                // Save candidate
                self.candidate = Some(result.content.clone());
                if let Some(artifacts) = &self.artifacts {
                    artifacts.save_candidate(attempt, &result.content).ok();
                    artifacts
                        .save_attempt_file(attempt, "response.json", &result)
                        .ok();
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
                            self.send(RunEvent::ModelUsageRecorded(sub_result.usage.clone()))
                                .await;
                            let clean_content = sub_result
                                .content
                                .trim()
                                .strip_prefix("```xml")
                                .unwrap_or(&sub_result.content)
                                .strip_prefix("```")
                                .unwrap_or(&sub_result.content)
                                .strip_suffix("```")
                                .unwrap_or(&sub_result.content)
                                .trim()
                                .to_string();

                            if let Some(artifacts) = &self.artifacts {
                                artifacts
                                    .save_attempt_raw(attempt, &file, &clean_content)
                                    .ok();
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
                    artifacts
                        .save_attempt_file(
                            attempt,
                            "error.json",
                            &serde_json::json!({ "error": err.to_string() }),
                        )
                        .ok();
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
                }))
                .await;
                return;
            }
        };

        // L1: XML parse validation
        let parse_result = validation::validate_xml_parse(&candidate);
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "local-validation.json", &parse_result)
                .ok();
        }

        if !parse_result.passed {
            self.last_actionable_errors = parse_result.actionable_errors.clone();
            self.send(RunEvent::ValidationCompleted(parse_result)).await;
            return;
        }

        // L2: Acceptance validation (if spec exists)
        if let Some(task) = &self.task {
            if let Some(spec) = &task.acceptance_spec {
                let acceptance_result = validation::validate_acceptance(&candidate, spec);
                if !acceptance_result.passed {
                    self.last_actionable_errors = acceptance_result.actionable_errors.clone();
                    self.send(RunEvent::ValidationCompleted(acceptance_result))
                        .await;
                    return;
                }
            }
        }

        // All local validation passed
        self.send(RunEvent::ValidationCompleted(parse_result)).await;
    }

    async fn domain_validate(&mut self, attempt: u8) {
        let model_dir = if let Some(artifacts) = &self.artifacts {
            // Write the candidate to a temporary model.xml in the attempt dir
            let attempt_dir = artifacts
                .create_attempt(attempt)
                .unwrap_or_else(|_| artifacts.root.clone());
            let model_path = attempt_dir.join("main.xml");
            if let Some(c) = &self.candidate {
                std::fs::write(&model_path, c).ok();
            }

            let model_dir = attempt_dir.join("model");
            std::fs::create_dir_all(&model_dir).ok();
            if let Ok(entries) = std::fs::read_dir(&attempt_dir) {
                for entry in entries.flatten() {
                    let path = entry.path();
                    if path.is_file() {
                        if let Some(ext) = path.extension() {
                            if ext == "xml" || ext == "ksml" {
                                std::fs::copy(&path, model_dir.join(path.file_name().unwrap()))
                                    .ok();
                            }
                        }
                    }
                }
            }

            info!(attempt, path = %attempt_dir.display(), "Running domain validation");
            Some(model_dir)
        } else {
            // Fallback if no artifacts dir (shouldn't happen in normal runs)
            info!(attempt, "Domain validation skipped — no artifact dir");
            None
        };

        let result = if let Some(model_dir) = model_dir {
            let input = model_dir.to_string_lossy().to_string();
            let start = std::time::Instant::now();
            match self
                .runner.run("cargo", &["teaql", "--input", &input, "evaluate"], None)
                .await
            {
                Ok(output) => {
                    let stdout = String::from_utf8_lossy(&output.stdout);
                    let stderr = String::from_utf8_lossy(&output.stderr);
                    let combined = format!("{stdout}\n{stderr}");
                    let mut result = validation::domain::parse_teaql_output(&combined);
                    result.elapsed_secs = start.elapsed().as_secs_f64();
                    if !output.status.success() && result.error_count == 0 {
                        result.passed = false;
                        result.error_count = 1;
                        // parse_teaql_output already places the head of the output into actionable_errors
                    } else if !output.status.success() {
                        result.passed = false;
                    }
                    result
                }
                Err(error) => validation::fail(
                    3,
                    "domain",
                    vec![error.to_string()],
                    error.to_string(),
                    start.elapsed().as_secs_f64(),
                ),
            }
        } else {
            validation::pass(3, "domain", 0.0)
        };

        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "domain-validation.json", &result)
                .ok();
        }
        if !result.passed {
            self.last_actionable_errors = result.actionable_errors.clone();
        }
        self.send(RunEvent::ValidationCompleted(result)).await;
    }

    /// Run build validation: code generation via `cargo teaql` + `cargo check`.
    ///
    /// Delegates to decomposed steps in [`build_steps`](crate::build_steps).
    async fn build_validate(&mut self, attempt: u8) {
        let build_target = match &self.build_target {
            Some(t) => t.clone(),
            None => {
                info!(
                    attempt,
                    "Build validation — no build target configured, passing"
                );
                let result = validation::pass(5, "build", 0.0);
                self.emit_build_result(attempt, result).await;
                return;
            }
        };

        let attempt_dir = match &self.artifacts {
            Some(a) => match a.create_attempt(attempt) {
                Ok(dir) => dir,
                Err(e) => {
                    error!(attempt, %e, "Failed to create attempt directory");
                    let result = validation::fail(
                        5, "build",
                        vec![format!("Failed to create attempt dir: {}", e)],
                        e.to_string(), 0.0,
                    );
                    self.emit_build_result(attempt, result).await;
                    return;
                }
            },
            None => {
                error!(attempt, "No artifact directory for build validation");
                let result = validation::fail(
                    5, "build",
                    vec!["No artifact directory available".to_string()],
                    "Internal error: no artifact store".to_string(), 0.0,
                );
                self.emit_build_result(attempt, result).await;
                return;
            }
        };

        let ctx = BuildContext {
            attempt,
            attempt_dir,
            build_target,
            patches: self.patches.clone(),
            artifacts: self.artifacts.clone(),
            start: std::time::Instant::now(),
        };

        // Step 1: Generate lib code
        if let Err(r) = build_steps::step_generate_code(&ctx, &mut self.runner).await {
            self.emit_build_result(attempt, r).await;
            return;
        }

        // Step 2: Patch Cargo.toml
        if let Err(r) = build_steps::step_patch_cargo_toml(&ctx) {
            self.emit_build_result(attempt, r).await;
            return;
        }

        // Step 3: cargo check on lib
        let lib_result = match build_steps::step_cargo_check_lib(&ctx, &mut self.runner).await {
            Ok(r) => r,
            Err(r) => {
                self.emit_build_result(attempt, r).await;
                return;
            }
        };

        // Step 4: Generate app target
        let app_ok = build_steps::step_generate_app(&ctx, &mut self.runner).await;
        if !app_ok {
            // App generation failed — still pass with lib-only result
            self.emit_build_result(attempt, lib_result).await;
            return;
        }

        // Step 5: Fix dependency paths
        build_steps::step_fix_app_dependencies(&ctx);

        // Step 6: Run assist commands
        let (entity_names, assist_outputs) =
            build_steps::step_run_assist(&ctx, &mut self.runner).await;

        // Step 7: Generate business logic via LLM
        build_steps::step_generate_business_logic(
            &ctx, &self.client, &self.event_tx, &entity_names, &assist_outputs,
        )
        .await;

        // Step 8: Final compile with LLM repair loop
        let result = build_steps::step_final_compile(
            &ctx, &mut self.runner, &self.client, &self.event_tx, &entity_names,
        )
        .await;

        self.emit_build_result(attempt, result).await;
    }

    /// Save a build validation result as artifact, update actionable errors,
    /// and send the event.
    async fn emit_build_result(&mut self, attempt: u8, result: ValidationResult) {
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "build-validation.json", &result)
                .ok();
        }
        if !result.passed {
            self.last_actionable_errors = result.actionable_errors.clone();
        }
        self.send(RunEvent::ValidationCompleted(result)).await;
    }

    async fn repair(&mut self, attempt: u8) {
        let task = match &self.task {
            Some(t) => t,
            None => {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: "No task for repair".to_string(),
                }))
                .await;
                return;
            }
        };

        let rejected = match &self.candidate {
            Some(c) => c.clone(),
            None => {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: "No candidate to repair".to_string(),
                }))
                .await;
                return;
            }
        };

        // Build repair messages — fresh, stateless
        // We need the actionable errors from the last validation.
        // For now, use a generic message since we don't store them in executor state.
        let message_pairs = build_repair_messages(
            task,
            &rejected,
            &self.last_actionable_errors,
            self.profile.run.diagnostic_character_limit,
        );

        let messages: Vec<ChatMessage> = message_pairs
            .into_iter()
            .map(|(role, content)| ChatMessage { role, content })
            .collect();

        self.repair_messages = Some(messages);

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
                        }))
                        .await;
                    }
                }
            } else {
                // No artifact store, just report success with a fake path
                self.send(RunEvent::FinalArtifactWritten(PathBuf::from(
                    "<no-artifact-store>",
                )))
                .await;
            }
        } else {
            self.send(RunEvent::Failed(AgentError::InfrastructureError {
                detail: "No candidate to finalize".to_string(),
            }))
            .await;
        }
    }

    async fn record_failure(&mut self, error: &str) {
        error!(%error, "Recording failure");
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_summary(&serde_json::json!({
                    "status": "failed",
                    "error": error,
                }))
                .ok();
        }
    }

    async fn send(&self, event: RunEvent) {
        if self.event_tx.send(event).await.is_err() {
            error!("Failed to send event — channel closed");
        }
    }

    /// Get the VllmClient reference for health checks
    pub fn client(&self) -> &ModelClient {
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
    pub fn set_last_errors(&mut self, errors: Vec<String>) {
        self.last_actionable_errors = errors;
    }
}
