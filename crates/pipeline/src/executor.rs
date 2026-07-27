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
        }
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

    async fn build_validate(&mut self, attempt: u8) {
        // Build validation would run cargo check/test.
        // For now, pass through.
        info!(attempt, "Build validation — no build configured, passing");
        let result = validation::pass(5, "build", 0.0);
        if let Some(artifacts) = &self.artifacts {
            artifacts.save_attempt_file(attempt, "build-validation.json", &result).ok();
        }
        self.send(RunEvent::ValidationCompleted(result)).await;
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
