use agent_core::error::AgentError;
use agent_core::event::*;
use agent_core::rag::KnowledgeRetriever;
use agent_core::reducer::SideEffect;
use artifact_store::RunArtifacts;
use context_builder::{
    TaskPackageData, build_generation_messages, build_repair_messages, calculate_budget,
};
use model_vllm::backend::ModelClient;
use model_vllm::chat::ChatMessage;
use model_vllm::profile::ModelProfile;
use model_vllm::tokenizer;
use rag_remote::WeaviateRetriever;
use std::collections::VecDeque;
use std::path::{Path, PathBuf};
use std::sync::Arc;
use tokio::sync::mpsc;
use tracing::{error, info, warn};
use validation;

#[derive(Debug, Clone, serde::Serialize)]
struct FollowUpRecord {
    attempt: u8,
    instruction: String,
    summary: String,
}

/// PipelineExecutor processes SideEffects and sends RunEvents back.
/// Both TUI and headless CLI create one of these.
pub struct PipelineExecutor {
    profile: ModelProfile,
    client: ModelClient,
    event_tx: mpsc::Sender<RunEvent>,
    task: Option<TaskPackageData>,
    candidate: Option<String>, // current candidate output
    candidate_files: Vec<(String, String)>,
    artifacts: Option<RunArtifacts>,
    runs_root: PathBuf,
    run_id: String,
    /// Optional build target for code generation (e.g. "rust-lib-core")
    build_target: Option<String>,
    /// Optional explicit modeling skill supplied by a CLI or TUI client.
    modeling_skill_path: Option<PathBuf>,
    /// Optional patches to apply to generated Cargo.toml files
    patches: Option<std::collections::HashMap<String, String>>,
    /// Exact application workspace generated for build and follow-up coding.
    workspace_dir: Option<PathBuf>,
    /// Bounded TeaQL API examples gathered during initial workspace generation.
    assist_context: String,
    /// Compact summaries of deterministically verified continuation turns.
    followup_history: Vec<FollowUpRecord>,
    /// Explicit machine-verifiable contracts, aligned with queued follow-ups.
    followup_acceptance_specs: VecDeque<crate::followup_acceptance::FollowUpAcceptanceSpec>,
    /// Actionable errors from the most recent failed validation, fed into repair prompts.
    last_actionable_errors: Vec<String>,
    /// Most recent L3 result after task-specific forbidden diagnostics are enforced.
    last_domain_validation: Option<ValidationResult>,
    /// RAG retriever for skills and error troubleshooting.
    retriever: Option<Arc<dyn KnowledgeRetriever>>,
}

impl PipelineExecutor {
    pub fn new(
        profile: ModelProfile,
        event_tx: mpsc::Sender<RunEvent>,
        runs_root: PathBuf,
        run_id: String,
    ) -> Result<Self, AgentError> {
        let client = ModelClient::from_profile(profile.clone())?;
        Ok(Self {
            profile,
            client,
            event_tx,
            task: None,
            candidate: None,
            candidate_files: Vec::new(),
            artifacts: None,
            runs_root,
            run_id,
            build_target: None,
            modeling_skill_path: None,
            patches: None,
            workspace_dir: None,
            assist_context: String::new(),
            followup_history: Vec::new(),
            followup_acceptance_specs: VecDeque::new(),
            last_actionable_errors: Vec::new(),
            last_domain_validation: None,
            retriever: None,
        })
    }

    /// Asynchronously initialize the local RAG retriever if it hasn't been initialized yet.
    async fn init_retriever(&mut self) {
        if self.retriever.is_none() {
            let r = WeaviateRetriever::new("http://localhost:8085/v1/graphql");
            info!("Remote Weaviate RAG Retriever initialized on port 8085");
            self.retriever = Some(Arc::new(r));
        }
    }

    async fn retrieve_modeling_skill(&mut self, task_text: &str) -> Result<String, String> {
        self.init_retriever().await;
        let Some(retriever) = &self.retriever else {
            return Err("RAG retriever is not configured".to_string());
        };

        let _ = self.event_tx.send(RunEvent::RagStarted).await;
        match retriever.search_by_intent(task_text).await {
            Ok(documents) => {
                let _ = self
                    .event_tx
                    .send(RunEvent::RagCompleted(documents.len()))
                    .await;
                if !documents.is_empty() {
                    info!(
                        count = documents.len(),
                        "Loaded intent-driven skills from RAG"
                    );
                }
                Ok(documents
                    .into_iter()
                    .map(|document| document.content)
                    .collect::<Vec<_>>()
                    .join("\n\n"))
            }
            Err(error) => {
                let _ = self.event_tx.send(RunEvent::RagCompleted(0)).await;
                Err(format!("RAG skill retrieval failed: {error}"))
            }
        }
    }

    /// Set the build target for code generation (e.g. "rust-lib-core")
    pub fn set_build_target(&mut self, target: String) {
        self.build_target = Some(target);
    }

    /// Set an explicit modeling skill to load with the next task.
    pub fn set_modeling_skill_path(&mut self, path: PathBuf) {
        self.modeling_skill_path = Some(path);
    }

    /// Set patches for generated Cargo.toml files
    pub fn set_patches(&mut self, patches: std::collections::HashMap<String, String>) {
        self.patches = Some(patches);
    }

    /// Set machine-verifiable contracts for queued follow-ups, in queue order.
    pub fn set_followup_acceptance_specs(
        &mut self,
        specs: Vec<crate::followup_acceptance::FollowUpAcceptanceSpec>,
    ) {
        self.followup_acceptance_specs = specs.into();
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
            SideEffect::RunFollowUp { task, attempt } => self.run_followup(task, attempt).await,
            SideEffect::LoadTask { path } => self.load_task(&path).await,
            SideEffect::RequestConsent { action, .. } => {
                // In TUI mode: the TUI will handle this
                self.send(RunEvent::ConsentDenied(format!(
                    "Auto-denied in headless: {action}"
                )))
                .await;
            }
            SideEffect::None => {}
        }
    }

    /// Run follow-up task on the existing workspace
    async fn run_followup(&mut self, task: String, attempt: u8) {
        let acceptance_spec = self.followup_acceptance_specs.pop_front();
        if let Some(spec) = acceptance_spec.as_ref() {
            let missing = missing_followup_environment(std::iter::once(spec));
            if !missing.is_empty() {
                let result = infrastructure_validation_failure(
                    "Required follow-up environment is unavailable",
                    format!("Missing environment variable(s): {}", missing.join(", ")),
                    0.0,
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        }
        let build_dir = match self.followup_workspace() {
            Ok(path) => path,
            Err(error) => {
                warn!(attempt, %error, "Cannot launch follow-up without a workspace");
                let result = validation::fail(5, "build", vec![error.clone()], error, 0.0);
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        info!(
            ?build_dir,
            "Launching agentic build loop for follow-up task"
        );
        match crate::known_infrastructure::detect_generated_workspace_infrastructure_failure(
            &build_dir,
        ) {
            Ok(Some(failure)) => {
                let result = validation::fail(
                    5,
                    "follow-up infrastructure",
                    vec![failure.actionable_error()],
                    failure.diagnostic(),
                    0.0,
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Ok(None) => {}
            Err(error) => {
                let result = infrastructure_validation_failure(
                    "Failed to inspect follow-up workspace for known runtime incompatibilities",
                    error.to_string(),
                    0.0,
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        }
        let workspace_before = application_workspace_snapshot(&build_dir);

        let system_template = std::fs::read_to_string("prompts/agentic-followup.txt")
            .unwrap_or_else(|_| include_str!("../../../prompts/agentic-followup.txt").to_string());
        let system_prompt =
            render_agentic_system_prompt(&system_template, &build_dir, &self.assist_context);

        let original_task = self
            .task
            .as_ref()
            .map(|task| task.task_content.as_str())
            .unwrap_or("");
        let user_prompt = build_followup_prompt(
            &build_dir,
            &task,
            original_task,
            &self.followup_history,
            acceptance_spec.as_ref(),
        );

        let start = std::time::Instant::now();
        const MAX_VERIFICATION_ROUNDS: usize = 3;
        const ITERATIONS_PER_ROUND: usize = 10;
        let mut next_prompt = user_prompt.clone();
        let mut total_iterations = 0usize;
        let mut total_tool_calls = 0usize;
        let mut last_summary = String::new();
        let requires_workspace_change = acceptance_spec
            .as_ref()
            .map(|spec| spec.files.iter().any(|file| file.must_change))
            .unwrap_or(true);
        let verification_target = self.build_target.as_deref().unwrap_or("rust-lib-core");

        for verification_round in 1..=MAX_VERIFICATION_ROUNDS {
            let loop_result = crate::agent_loop::run_agent_loop(
                &self.client,
                &build_dir,
                &system_prompt,
                &next_prompt,
                ITERATIONS_PER_ROUND,
                Some(self.event_tx.clone()),
            )
            .await;

            let (yielded, round_diagnostic) = match loop_result {
                crate::agent_loop::AgentLoopResult::Completed {
                    summary,
                    iterations,
                    total_tool_calls: tool_calls,
                } => {
                    total_iterations += iterations;
                    total_tool_calls += tool_calls;
                    last_summary = summary;
                    (true, None)
                }
                crate::agent_loop::AgentLoopResult::Failed { error, iterations } => {
                    total_iterations += iterations;
                    if is_infrastructure_diagnostic(&error) {
                        let mut result = infrastructure_validation_failure(
                            "Follow-up agent stopped on an infrastructure failure",
                            error.clone(),
                            start.elapsed().as_secs_f64(),
                        );
                        result.diagnostic = format!(
                            "Agentic follow-up stopped after {total_iterations} iteration(s): {error}"
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    if acceptance_spec.is_none() {
                        let result = validation::fail(
                            5,
                            "follow-up acceptance",
                            vec!["Unverified follow-up agent loop failed".to_string()],
                            error,
                            start.elapsed().as_secs_f64(),
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    (false, Some(error))
                }
                crate::agent_loop::AgentLoopResult::MaxIterationsReached {
                    iterations,
                    total_tool_calls: tool_calls,
                    last_failed_build,
                } => {
                    total_iterations += iterations;
                    total_tool_calls += tool_calls;
                    if acceptance_spec.is_none() {
                        let result = validation::fail(
                            5,
                            "follow-up acceptance",
                            vec!["Agent reached its iteration limit without an explicit, verifiable acceptance contract".to_string()],
                            "The model did not yield completion. Natural-language hints are not an acceptance contract; provide a typed follow-up acceptance sidecar or CLI contract. Compilation alone is never accepted at the iteration limit.".to_string(),
                            start.elapsed().as_secs_f64(),
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    last_summary = format!(
                        "Round {verification_round} reached its iteration limit; deterministic verification was still required. Last failed build: {}",
                        last_failed_build
                            .as_deref()
                            .unwrap_or("no compiler diagnostic captured")
                    );
                    (false, None)
                }
            };

            let verification = if requires_workspace_change
                && application_workspace_snapshot(&build_dir) == workspace_before
            {
                Err("The follow-up has not changed any application-owned file required by the acceptance contract.".to_string())
            } else {
                verify_followup_outcome(
                    &build_dir,
                    &workspace_before,
                    acceptance_spec.as_ref(),
                    verification_target,
                )
                .await
            };

            match verification {
                Ok(acceptance_diagnostic) => {
                    let summary = if yielded {
                        last_summary.clone()
                    } else {
                        format!(
                            "The agent did not explicitly yield in verification round {verification_round}, but every item in the explicit machine contract passed independently. {}",
                            last_summary
                        )
                    };
                    self.followup_history.push(FollowUpRecord {
                        attempt,
                        instruction: bounded_text(&task, 4_000),
                        summary: bounded_text(&summary, 2_000),
                    });
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_file(
                                attempt,
                                "session-ledger.json",
                                &self.followup_history,
                            )
                            .await
                            .ok();
                    }
                    let total_elapsed = start.elapsed().as_secs_f64();
                    info!(
                        attempt,
                        verification_round,
                        total_iterations,
                        total_tool_calls,
                        total_elapsed,
                        "Agentic follow-up passed deterministic acceptance"
                    );
                    let mut result = validation::pass(5, "build", total_elapsed);
                    result.diagnostic = format!(
                        "Follow-up: ✓ ({verification_round} verification round(s), {total_iterations} model iteration(s), {total_tool_calls} tool call(s))\n\nAgent summary: {summary}\n\n{acceptance_diagnostic}"
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                Err(diagnostic) if is_infrastructure_diagnostic(&diagnostic) => {
                    let result = infrastructure_validation_failure(
                        "Deterministic follow-up verification hit an infrastructure failure",
                        diagnostic,
                        start.elapsed().as_secs_f64(),
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                Err(diagnostic) if verification_round < MAX_VERIFICATION_ROUNDS => {
                    warn!(
                        attempt,
                        verification_round,
                        "Deterministic acceptance failed; returning evidence to the coding agent"
                    );
                    next_prompt = format!(
                        "{user_prompt}\n\n# Independent Acceptance Feedback (round {verification_round})\nThe previous attempt did not pass. Fix every failed check below, rerun the relevant commands, and only then yield. Do not regenerate or modify the model or validation evidence.\n\n{}\n\nPrevious agent summary:\n{}",
                        bounded_text(&diagnostic, 12_000),
                        bounded_text(&last_summary, 2_000)
                    );
                }
                Err(diagnostic) => {
                    let total_elapsed = start.elapsed().as_secs_f64();
                    let mut result = validation::fail(
                        5,
                        "follow-up acceptance",
                        vec![format!(
                            "Deterministic follow-up acceptance failed after {MAX_VERIFICATION_ROUNDS} verification rounds"
                        )],
                        diagnostic,
                        total_elapsed,
                    );
                    if let Some(round_error) = round_diagnostic {
                        result.diagnostic = format!(
                            "Last agent-loop error: {round_error}\n\n{}",
                            result.diagnostic
                        );
                    }
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            }
        }
    }

    /// Load a task package from disk.
    pub async fn load_task_from_path(&mut self, path: &Path) {
        match TaskPackageData::load(path) {
            Ok(mut task) => {
                let required_build_targets = task
                    .acceptance_spec
                    .as_ref()
                    .and_then(|spec| spec.get("build_targets"))
                    .and_then(serde_json::Value::as_array)
                    .map(|targets| {
                        targets
                            .iter()
                            .filter_map(serde_json::Value::as_str)
                            .map(str::to_owned)
                            .collect::<Vec<_>>()
                    })
                    .unwrap_or_default();
                if required_build_targets.len() > 1 {
                    self.send(RunEvent::TaskLoadFailed(format!(
                        "Task acceptance requires multiple build targets ({}) but this pipeline supports exactly one target per run",
                        required_build_targets.join(", ")
                    )))
                    .await;
                    return;
                }
                if let Some(required_target) = required_build_targets.first() {
                    if let Some(configured_target) = self.build_target.as_deref()
                        && configured_target != required_target
                    {
                        self.send(RunEvent::TaskLoadFailed(format!(
                            "Configured build target `{configured_target}` conflicts with task acceptance target `{required_target}`"
                        )))
                        .await;
                        return;
                    }
                    if self.build_target.is_none() {
                        info!(target = %required_target, "Using build target required by task acceptance");
                        self.build_target = Some(required_target.clone());
                    }
                }
                if self.followup_acceptance_specs.is_empty() {
                    let sidecar = task.root.join("followup-acceptance.json");
                    if sidecar.is_file() {
                        match crate::followup_acceptance::FollowUpAcceptanceSpec::load(&sidecar) {
                            Ok(spec) => {
                                info!(path = %sidecar.display(), "Loaded task follow-up acceptance sidecar");
                                self.followup_acceptance_specs.push_back(spec);
                            }
                            Err(error) => {
                                self.send(RunEvent::TaskLoadFailed(error)).await;
                                return;
                            }
                        }
                    }
                }
                if self.modeling_skill_path.is_none() && !self.profile.simulator.enabled {
                    let task_text = task.task_content.clone();
                    match self.retrieve_modeling_skill(&task_text).await {
                        Ok(skill) if !skill.is_empty() => {
                            task.modeling_skill = Some(match task.modeling_skill.take() {
                                Some(existing) => format!("{existing}\n\n{skill}"),
                                None => skill,
                            });
                        }
                        Ok(_) => {}
                        Err(error) => {
                            self.send(RunEvent::TaskLoadFailed(error)).await;
                            return;
                        }
                    }
                }
                if let Some(skill_path) = &self.modeling_skill_path
                    && let Err(error) = task.load_modeling_skill_from(skill_path)
                {
                    self.send(RunEvent::TaskLoadFailed(format!(
                        "Failed to load modeling skill: {error}"
                    )))
                    .await;
                    return;
                }
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
                self.send(RunEvent::TaskLoadFailed(format!(
                    "Failed to load task: {e}"
                )))
                .await;
            }
        }
    }

    /// Create a task from inline text (typed in TUI).
    pub async fn load_task_from_text(&mut self, text: &str) {
        let mut task = TaskPackageData::from_inline_text(text);

        if self.modeling_skill_path.is_none() && !self.profile.simulator.enabled {
            match self.retrieve_modeling_skill(text).await {
                Ok(skill) if !skill.is_empty() => task.modeling_skill = Some(skill),
                Ok(_) => {}
                Err(error) => {
                    self.send(RunEvent::TaskLoadFailed(error)).await;
                    return;
                }
            }
        }

        if let Some(skill_path) = &self.modeling_skill_path
            && let Err(error) = task.load_modeling_skill_from(skill_path)
        {
            self.send(RunEvent::TaskLoadFailed(format!(
                "Failed to load modeling skill: {error}"
            )))
            .await;
            return;
        }

        let pkg = TaskPackage {
            name: task.name.clone(),
            task_file: task.root.join("task.md"),
            files: vec![],
            acceptance_spec: None,
        };
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

        let missing_environment =
            missing_followup_environment(self.followup_acceptance_specs.iter());
        if !missing_environment.is_empty() {
            self.send(RunEvent::PreflightFailed(format!(
                "{} Required follow-up environment is unavailable: {}",
                INFRASTRUCTURE_FAILURE_PREFIX,
                missing_environment.join(", ")
            )))
            .await;
            return;
        }

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

        if !self.profile.simulator.enabled {
            if let Err(error) = verify_cargo_teaql_version().await {
                self.send(RunEvent::PreflightFailed(format!(
                    "[infrastructure] {error}"
                )))
                .await;
                return;
            }
        }

        // Create run artifacts directory
        match RunArtifacts::create(&self.runs_root, &self.run_id).await {
            Ok(artifacts) => {
                self.artifacts = Some(artifacts);
            }
            Err(e) => {
                self.send(RunEvent::PreflightFailed(format!(
                    "[infrastructure] Failed to create run artifacts: {e}"
                )))
                .await;
                return;
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
        self.init_retriever().await;

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

        // Build messages — fresh for first attempt, repair-aware for retries
        let message_pairs = if attempt > 1 {
            let rejected = self.candidate.clone().unwrap_or_default();
            let mut errors = if self.last_actionable_errors.is_empty() {
                vec!["Validation failed. Please fix all errors.".to_string()]
            } else {
                self.last_actionable_errors.clone()
            };

            // Inject targeted hints for well-known error patterns before RAG.
            let errors_joined = errors.join("\n");
            if errors_joined.contains("is private")
                && (errors_joined.contains("field `") || errors_joined.contains("E0616"))
            {
                errors.push(
                    "HINT: TeaQL entity fields are PRIVATE. \
                     Replace `entity.field_name` with `entity.field_name()` (add parentheses). \
                     Example: `p.id` → `p.id()`, `p.name` → `p.name()`. \
                     This applies to every field on every entity."
                        .to_string(),
                );
            }
            if errors_joined.contains("no method named")
                && (errors_joined.contains("E0599")
                    || errors_joined.contains("PurposedQuery")
                    || ["limit", "fetch", "get", "all", "query", "run", "find"]
                        .iter()
                        .any(|m| errors_joined.contains(&format!("method named `{m}`"))))
            {
                errors.push(
                    "HINT: Do not invent TeaQL query terminal methods. \
                     The ONLY valid way to execute a query is: \
                     `.purpose(\"why\").comment(\"what\").execute_for_list(ctx).await?` \
                     or `.purpose(\"why\").comment(\"what\").execute(ctx).await?`. \
                     Methods like .limit(), .fetch(), .get(), .all(), .run() do NOT exist in TeaQL. \
                     Copy the exact pattern from the assist output."
                        .to_string(),
                );
            }

            // RAG Retrieval for error diagnosis
            if let Some(retriever) = &self.retriever {
                let _ = self.event_tx.send(RunEvent::RagStarted).await;
                if let Some(first_err) = errors.first() {
                    if let Ok(docs) = retriever.retrieve_for_error(first_err).await {
                        let _ = self.event_tx.send(RunEvent::RagCompleted(docs.len())).await;
                        if !docs.is_empty() {
                            info!(count = docs.len(), error = %first_err, "Injected RAG context for repair");
                            let mut rag_context =
                                String::from("\n\n# Relevant Context/Guidelines to Fix This:\n");
                            for doc in docs {
                                rag_context.push_str(&doc.content);
                                rag_context.push_str("\n\n");
                            }
                            errors[0].push_str(&rag_context);
                        }
                    }
                }
            }
            build_repair_messages(
                task,
                &rejected,
                &errors,
                self.profile.run.diagnostic_character_limit,
            )
        } else {
            build_generation_messages(task)
        };
        let messages: Vec<ChatMessage> = message_pairs
            .into_iter()
            .map(|(role, content)| ChatMessage {
                role,
                content: Some(content),
                ..Default::default()
            })
            .collect();

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
                    }),
                )
                .await
                .ok();
        }

        let mut request_client = self.client.clone();
        if attempt > 1 && !self.profile.simulator.enabled {
            let mut dynamic_profile = self.profile.clone();
            let base_temp = dynamic_profile.sampling.temperature;
            let new_temp = (base_temp + (attempt as f32 - 1.0) * 0.15).min(0.8);
            dynamic_profile.sampling.temperature = new_temp;
            request_client = match ModelClient::from_profile(dynamic_profile) {
                Ok(client) => client,
                Err(error) => {
                    self.send(RunEvent::ModelFailed(error)).await;
                    return;
                }
            };
            info!(
                attempt,
                temp = new_temp,
                "Applying dynamic temperature for repair"
            );
        }

        self.send(RunEvent::ModelStarted {
            attempt: attempt as u8,
        })
        .await;
        match request_client.chat(messages.clone(), None, None).await {
            Ok(result) => {
                let transport = validation::validate_transport(&result);
                if !transport.passed {
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_file(attempt, "response.json", &result)
                            .await
                            .ok();
                    }
                    self.send(RunEvent::ModelUsageRecorded(result.usage.clone()))
                        .await;
                    self.send(RunEvent::ModelFailed(AgentError::IncompleteGeneration {
                        reason: transport.diagnostic,
                    }))
                    .await;
                    return;
                }
                // Save candidate
                self.candidate = Some(result.content.clone());
                self.candidate_files = vec![("main.xml".to_string(), result.content.clone())];
                if let Some(artifacts) = &self.artifacts {
                    artifacts
                        .save_candidate(attempt, &result.content)
                        .await
                        .ok();
                    artifacts
                        .save_attempt_file(attempt, "response.json", &result)
                        .await
                        .ok();
                }

                // MULTI-STEP LOOP: Generate included files
                let includes = extract_includes(&result.content);
                for file in includes {
                    info!(attempt, file = %file, "Generating included file");
                    let mut sub_messages = messages.clone();
                    sub_messages.push(ChatMessage::assistant(result.content.clone()));
                    sub_messages.push(ChatMessage::user(format!("Please provide the contents of {}. Output ONLY the raw XML, nothing else. Do not use markdown blocks, just the raw XML text.", file)));

                    match self.client.chat(sub_messages, None, None).await {
                        Ok(sub_result) => {
                            let transport = validation::validate_transport(&sub_result);
                            if !transport.passed {
                                self.send(RunEvent::ModelUsageRecorded(sub_result.usage.clone()))
                                    .await;
                                self.send(RunEvent::ModelFailed(
                                    AgentError::IncompleteGeneration {
                                        reason: format!(
                                            "Included file `{file}` was incomplete: {}",
                                            transport.diagnostic
                                        ),
                                    },
                                ))
                                .await;
                                return;
                            }
                            let clean_content = strip_markdown_fences(&sub_result.content);
                            self.candidate_files
                                .push((file.clone(), clean_content.clone()));

                            if let Some(artifacts) = &self.artifacts {
                                artifacts
                                    .save_attempt_raw(attempt, &file, &clean_content)
                                    .await
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
                        .await
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

        // L1: Parse every generated KSML file and validate the combined model.
        // This catches duplicate object declarations across `_include` files
        // before they reach TeaQL code generation.
        let parse_result = if self.candidate_files.is_empty() {
            validation::validate_xml_parse(&candidate)
        } else {
            validation::validate_xml_model_files(&self.candidate_files)
        };
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "local-validation.json", &parse_result)
                .await
                .ok();
        }

        if !parse_result.passed {
            self.send(RunEvent::ValidationCompleted(parse_result)).await;
            return;
        }
        let mut local_result = parse_result;

        // L2: Acceptance validation (if spec exists)
        if let Some(task) = &self.task {
            if let Some(spec) = &task.acceptance_spec {
                let acceptance_result = if self.candidate_files.is_empty() {
                    validation::validate_acceptance(&candidate, spec)
                } else {
                    validation::validate_acceptance_model_files(&self.candidate_files, spec)
                };
                if !acceptance_result.passed {
                    self.send(RunEvent::ValidationCompleted(acceptance_result))
                        .await;
                    return;
                }
                local_result = acceptance_result;
            }
        }

        // All local validation passed
        self.send(RunEvent::ValidationCompleted(local_result)).await;
    }

    async fn domain_validate(&mut self, attempt: u8) {
        let result = if let Some(artifacts) = &self.artifacts {
            match prepare_domain_model_files(
                artifacts,
                attempt,
                &self.candidate_files,
                self.candidate.as_deref(),
            )
            .await
            {
                Ok(model_dir) => {
                    info!(attempt, path = %model_dir.display(), "Running domain validation");
                    run_domain_validation(&model_dir).await
                }
                Err(error) => validation::fail(
                    3,
                    "domain",
                    vec![format!(
                        "{} Failed to prepare domain validation input",
                        INFRASTRUCTURE_FAILURE_PREFIX
                    )],
                    error,
                    0.0,
                ),
            }
        } else {
            validation::fail(
                3,
                "domain",
                vec!["[infrastructure] Run artifact store is unavailable; domain validation cannot execute".to_string()],
                "Domain validation requires a durable attempt directory and model snapshot".to_string(),
                0.0,
            )
        };
        let result = if let Some(spec) = self
            .task
            .as_ref()
            .and_then(|task| task.acceptance_spec.as_ref())
        {
            validation::enforce_forbidden_domain_errors(result, spec)
        } else {
            result
        };
        self.last_domain_validation = Some(result.clone());

        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "domain-validation.json", &result)
                .await
                .ok();
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
                info!(
                    attempt,
                    "Build validation — no build target configured, passing"
                );
                let result = validation::pass(5, "build", 0.0);
                if let Some(artifacts) = &self.artifacts {
                    artifacts
                        .save_attempt_file(attempt, "build-validation.json", &result)
                        .await
                        .ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        let attempt_dir = match &self.artifacts {
            Some(a) => match a.create_attempt(attempt).await {
                Ok(dir) => dir,
                Err(e) => {
                    error!(attempt, %e, "Failed to create attempt directory");
                    let result = infrastructure_validation_failure(
                        format!("Failed to create attempt directory: {e}"),
                        e.to_string(),
                        0.0,
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            },
            None => {
                error!(attempt, "No artifact directory for build validation");
                let result = infrastructure_validation_failure(
                    "No artifact directory available",
                    "Internal error: no artifact store".to_string(),
                    0.0,
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        let start = std::time::Instant::now();

        // Step 1: Run cargo teaql to generate code
        info!(attempt, target = %build_target, dir = %attempt_dir.display(), "Running code generation");
        let mut generation_command = tokio::process::Command::new("cargo");
        crate::process_env::apply_safe_environment(&mut generation_command, &attempt_dir);
        generation_command
            .args(["teaql", "--input", "model", &build_target])
            .current_dir(&attempt_dir);
        let gen_result = crate::process_output::run_bounded_output(
            &mut generation_command,
            std::time::Duration::from_secs(300),
            512 * 1024,
        )
        .await;

        match &gen_result {
            Ok(output) if !output.status.success() => {
                let stderr = String::from_utf8_lossy(&output.stderr);
                let stdout = String::from_utf8_lossy(&output.stdout);
                let diagnostic = format!("Code generation failed:\n{}\n{}", stdout, stderr);
                warn!(attempt, "Code generation failed");
                let result = generation_validation_failure(
                    format!("cargo teaql {build_target} failed"),
                    diagnostic,
                    start.elapsed().as_secs_f64(),
                );
                if let Some(artifacts) = &self.artifacts {
                    artifacts
                        .save_attempt_file(attempt, "build-validation.json", &result)
                        .await
                        .ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Err(e) => {
                error!(attempt, %e, "Failed to run cargo teaql");
                let result = infrastructure_validation_failure(
                    format!("Failed to execute cargo teaql: {e}"),
                    e.to_string(),
                    start.elapsed().as_secs_f64(),
                );
                if let Some(artifacts) = &self.artifacts {
                    artifacts
                        .save_attempt_file(attempt, "build-validation.json", &result)
                        .await
                        .ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            _ => {
                info!(attempt, "Code generation succeeded");
            }
        }

        // ── Step 2: Run additional generation targets ──
        self.send(agent_core::event::RunEvent::WorkspaceGenerationStarted)
            .await;
        // Derive the app target from the build target (e.g. rust-lib-core → rust-app-console)
        let app_target = if build_target.contains("-lib-core") {
            let app = if build_target.starts_with("java") {
                build_target.replace("-lib-core", "-web-spring-boot")
            } else {
                build_target.replace("-lib-core", "-app-console")
            };
            info!(attempt, target = %app, "Generating app target");
            let mut app_generation_command = tokio::process::Command::new("cargo");
            crate::process_env::apply_safe_environment(&mut app_generation_command, &attempt_dir);
            app_generation_command
                .args(["teaql", "--input", "model", &app])
                .current_dir(&attempt_dir);
            let app_gen = crate::process_output::run_bounded_output(
                &mut app_generation_command,
                std::time::Duration::from_secs(300),
                512 * 1024,
            )
            .await;

            match &app_gen {
                Ok(output) if !output.status.success() => {
                    let stderr = String::from_utf8_lossy(&output.stderr);
                    let stdout = String::from_utf8_lossy(&output.stdout);
                    let diagnostic = format!("Application generation failed:\n{stdout}\n{stderr}");
                    warn!(attempt, "App target generation failed: {}", stderr);
                    let result = generation_validation_failure(
                        format!("cargo teaql {app} failed"),
                        diagnostic,
                        start.elapsed().as_secs_f64(),
                    );
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_raw(attempt, "app-gen-error.txt", &stderr)
                            .await
                            .ok();
                        artifacts
                            .save_attempt_file(attempt, "build-validation.json", &result)
                            .await
                            .ok();
                    }
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                Err(e) => {
                    warn!(attempt, %e, "Failed to run app target generation");
                    let result = infrastructure_validation_failure(
                        format!("Failed to execute cargo teaql {app}: {e}"),
                        e.to_string(),
                        start.elapsed().as_secs_f64(),
                    );
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_file(attempt, "build-validation.json", &result)
                            .await
                            .ok();
                    }
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                _ => info!(attempt, target = %app, "App target generated successfully"),
            }
            Some(app)
        } else {
            None
        };

        // ── Step 3: Apply patches to generated files ──
        let build_dir = attempt_dir.join("build");
        self.workspace_dir = Some(build_dir.clone());
        if let Err(error) = prepare_workspace_context(&attempt_dir, &build_dir) {
            let result = infrastructure_validation_failure(
                "Failed to prepare model and agent context in generated workspace",
                error,
                start.elapsed().as_secs_f64(),
            );
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }
        if let Err(error) = self.write_workspace_validation_evidence(&build_dir) {
            let result = infrastructure_validation_failure(
                "Failed to write deterministic validation evidence",
                error,
                start.elapsed().as_secs_f64(),
            );
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }
        // Apply patches to any Cargo.toml or pom.xml files found
        for entry in walkdir_toml_xml(&build_dir) {
            if let Ok(content) = std::fs::read_to_string(&entry) {
                let mut fixed = content.clone();

                if let Some(patches) = &self.patches {
                    for (find, replace) in patches {
                        fixed = fixed.replace(find, replace);
                    }
                }
                if fixed != content {
                    info!(attempt, file = %entry.display(), "Applied patches");
                    std::fs::write(&entry, &fixed).ok();
                }
            }
        }

        // Fix app-console dependency path if applicable
        if let Some(ref _app) = app_target {
            let app_cargo_toml = build_dir.join("Cargo.toml");
            if app_cargo_toml.exists() {
                if let Ok(content) = std::fs::read_to_string(&app_cargo_toml) {
                    let old_path = format!(r#"path = "../{}/lib""#, build_target);
                    let mut fixed = content.replace(&old_path, r#"path = "./lib""#);
                    fixed = ensure_standalone_cargo_workspace(&fixed);
                    if fixed != content {
                        info!(attempt, "Fixed generated app workspace manifest");
                        std::fs::write(&app_cargo_toml, &fixed).ok();
                    }
                }
            }
        }

        // Fail fast on framework defects that application edits or KSML repair
        // cannot resolve. This detector reads only manifests, Cargo.lock, and
        // the copied model; generated library source remains out of scope.
        match crate::known_infrastructure::detect_generated_workspace_infrastructure_failure(
            &build_dir,
        ) {
            Ok(Some(failure)) => {
                let result = validation::fail(
                    5,
                    "build infrastructure",
                    vec![failure.actionable_error()],
                    failure.diagnostic(),
                    start.elapsed().as_secs_f64(),
                );
                if let Some(artifacts) = &self.artifacts {
                    artifacts
                        .save_attempt_file(attempt, "build-validation.json", &result)
                        .await
                        .ok();
                }
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Ok(None) => {}
            Err(error) => {
                let result = infrastructure_validation_failure(
                    "Failed to inspect generated workspace for known runtime incompatibilities",
                    error.to_string(),
                    start.elapsed().as_secs_f64(),
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        }

        // ── Step 4: Run assist queries for API reference context ──
        let agents_md_path = build_dir.join("AGENTS.md");
        let agents_md = if agents_md_path.exists() {
            std::fs::read_to_string(&agents_md_path).unwrap_or_default()
        } else {
            String::new()
        };

        let entity_names = parse_entity_names_from_agents_md(&agents_md);

        // Limit assist queries to avoid context overflow
        const MAX_ASSIST_ENTITIES: usize = 8;
        let assist_entities: Vec<&String> = if entity_names.len() > MAX_ASSIST_ENTITIES {
            let step = entity_names.len() as f64 / MAX_ASSIST_ENTITIES as f64;
            (0..MAX_ASSIST_ENTITIES)
                .map(|i| &entity_names[(i as f64 * step) as usize])
                .collect()
        } else {
            entity_names.iter().collect()
        };

        info!(
            attempt,
            total = entity_names.len(),
            sampled = assist_entities.len(),
            "Running assist commands"
        );

        let assist_target_base = build_target.replace("-lib-core", "-assist-query");
        let assist_dir = build_dir.join(".klintcode/assist");
        if let Err(error) = std::fs::create_dir_all(&assist_dir) {
            let result = infrastructure_validation_failure(
                "Failed to create workspace assist directory",
                error.to_string(),
                start.elapsed().as_secs_f64(),
            );
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }
        // Prepend the generated AGENTS.md so the model has workspace-specific
        // rules (correct cargo teaql format, banned frameworks, etc.) directly
        // in context. Strip the phase_modeling discard block which is only
        // relevant during modeling, not during the build phase.
        let agents_md_for_context = if !agents_md.is_empty() {
            let stripped = agents_md
                .lines()
                .skip_while(|line| line.trim() == "<!-- DISCARD_BLOCK: phase_modeling -->")
                .collect::<Vec<_>>()
                .join("\n");
            // Remove any remaining DISCARD_BLOCK sections
            let stripped = {
                let mut out = String::new();
                let mut in_discard = false;
                for line in stripped.lines() {
                    if line.trim().starts_with("<!-- DISCARD_BLOCK:") {
                        in_discard = true;
                    } else if line.trim() == "<!-- END_DISCARD_BLOCK -->" {
                        in_discard = false;
                        continue;
                    }
                    if !in_discard {
                        out.push_str(line);
                        out.push('\n');
                    }
                }
                out
            };
            format!(
                "## Workspace Rules (AGENTS.md)\n{}\n\n",
                bounded_text(&stripped, 3_000)
            )
        } else {
            String::new()
        };

        let mut assist_outputs = format!(
            "{agents_md_for_context}Complete TeaQL assist responses are saved under `.klintcode/assist/`. Read the relevant file before writing TeaQL business code. The canonical model path is `model/main.xml`.\n\n",
        );
        for entity in &assist_entities {
            let assist_target = format!("{}/{}", assist_target_base, entity);
            let mut assist_command = tokio::process::Command::new("cargo");
            crate::process_env::apply_safe_environment(&mut assist_command, &attempt_dir);
            assist_command
                .args(["teaql", "--input", "model", &assist_target])
                .current_dir(&attempt_dir);
            let assist_result = crate::process_output::run_bounded_output(
                &mut assist_command,
                std::time::Duration::from_secs(120),
                256 * 1024,
            )
            .await;

            let output = match assist_result {
                Ok(output) if output.status.success() => output,
                Ok(output) => {
                    let diagnostic = format!(
                        "cargo teaql --input model rust-assist-query/{entity} failed with {}\nSTDOUT:\n{}\nSTDERR:\n{}",
                        output.status,
                        String::from_utf8_lossy(&output.stdout),
                        String::from_utf8_lossy(&output.stderr)
                    );
                    let result = infrastructure_validation_failure(
                        format!("TeaQL assist context is unavailable for entity `{entity}`"),
                        diagnostic,
                        start.elapsed().as_secs_f64(),
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                Err(error) => {
                    let result = infrastructure_validation_failure(
                        format!("Failed to execute TeaQL assist for entity `{entity}`"),
                        error.to_string(),
                        start.elapsed().as_secs_f64(),
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            };
            let stdout = String::from_utf8_lossy(&output.stdout);
            let assist_relative = format!(".klintcode/assist/query-{entity}.md");
            if let Err(error) = std::fs::write(build_dir.join(&assist_relative), stdout.as_bytes())
            {
                let result = infrastructure_validation_failure(
                    format!("Failed to save complete assist response for entity `{entity}`"),
                    error.to_string(),
                    start.elapsed().as_secs_f64(),
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            assist_outputs.push_str(&format!(
                "### Assist: query/{entity}\nFull response: `{assist_relative}`\n\n{}\n\n",
                bounded_text(&stdout, 3_500)
            ));
        }

        // List remaining entities so LLM knows the full set
        if entity_names.len() > MAX_ASSIST_ENTITIES {
            assist_outputs.push_str(&format!(
                "### All entity names ({})\n{}\n\n",
                entity_names.len(),
                entity_names.join(", ")
            ));
        }

        if !assist_outputs.is_empty() {
            self.assist_context = bounded_text(&assist_outputs, 20_000);
            if let Some(artifacts) = &self.artifacts {
                artifacts
                    .save_attempt_raw(attempt, "assist-output.md", &assist_outputs)
                    .await
                    .ok();
            }
        }

        // ── Step 5: Launch agentic build loop ──
        // Instead of hardcoding cargo check / mvn compile, we let the LLM
        // autonomously inspect, compile, and fix the project using tools.
        if !entity_names.is_empty() && !assist_outputs.is_empty() {
            info!(attempt, "Launching agentic build loop");

            let system_template = std::fs::read_to_string("prompts/agentic-build.txt")
                .unwrap_or_else(|_| include_str!("../../../prompts/agentic-build.txt").to_string());
            let system_prompt =
                render_agentic_system_prompt(&system_template, &build_dir, &assist_outputs);

            // Give the agent specific first-step instructions based on detected build system
            let compile_hint = if build_target.starts_with("java") {
                "This is a Java/Maven project. Your FIRST action must be: run_command({\"command\": \"mvn compile -f pom.xml\"})"
            } else {
                "This is a Rust/Cargo project. Your FIRST action must be: run_command({\"command\": \"cargo check\"})"
            };
            let original_task = self
                .task
                .as_ref()
                .map(|task| bounded_text(&task.task_content, 8_000))
                .unwrap_or_default();
            let model_acceptance = self
                .task
                .as_ref()
                .and_then(|task| task.acceptance_spec.as_ref())
                .and_then(|spec| serde_json::to_string_pretty(spec).ok())
                .map(|spec| bounded_text(&spec, 4_000))
                .unwrap_or_else(|| "No model acceptance sidecar was supplied.".to_string());

            let user_prompt = format!(
                "You are already operating at the project workspace root. Do not `cd` to `{dir}` and do not prefix tool paths with it; every tool path is relative to the current root. {hint}\n\n\
                 # Original User Task\n{original_task}\n\n\
                 # Model Acceptance Context\n{model_acceptance}\n\n\
                 After seeing the compile output:\n\
                 - If the Exit code is 0, compilation succeeded, but the task is not complete until every requested API example, test, review, report, and runtime check from the original task is complete.\n\
                 - If the Exit code is non-zero, use the complete compiler diagnostic and the TeaQL assist output. Fix only application code or workspace configuration, then recompile.\n\
                 - Write business logic code (one query function per entity) if the src/ files are empty stubs.\n\n\
                 - Never read, search, or modify generated library source such as lib/src. If the compiler reports duplicate generated definitions, stop and report that the KSML model must be repaired.\n\n\
                 Do NOT spend time exploring the directory tree. Compile first, fix errors after. Run the requested tests and runtime checks, then respond with a summary only when the complete original task is satisfied.",
                dir = build_dir.display(),
                hint = compile_hint,
                original_task = original_task,
                model_acceptance = model_acceptance,
            );

            let max_iterations = 20; // Enough for explore → compile → fix → recompile cycles

            let loop_result = crate::agent_loop::run_agent_loop(
                &self.client,
                &build_dir,
                &system_prompt,
                &user_prompt,
                max_iterations,
                Some(self.event_tx.clone()),
            )
            .await;

            match &loop_result {
                crate::agent_loop::AgentLoopResult::Completed {
                    summary,
                    iterations,
                    total_tool_calls,
                } => {
                    let total_elapsed = start.elapsed().as_secs_f64();
                    let build_diagnostic = match verify_generated_build(&build_dir, &build_target)
                        .await
                    {
                        Ok(diagnostic) => diagnostic,
                        Err(diagnostic) => {
                            let result = if is_infrastructure_diagnostic(&diagnostic) {
                                infrastructure_validation_failure(
                                    "Deterministic build verification hit an infrastructure failure",
                                    diagnostic,
                                    total_elapsed,
                                )
                            } else {
                                validation::fail(
                                    5,
                                    "build",
                                    vec!["Deterministic build verification failed".to_string()],
                                    diagnostic,
                                    total_elapsed,
                                )
                            };
                            self.send(RunEvent::ValidationCompleted(result)).await;
                            return;
                        }
                    };
                    let (test_diagnostic, observed_tests) = match verify_generated_tests(
                        &build_dir,
                        &build_target,
                    )
                    .await
                    {
                        Ok(result) => result,
                        Err(diagnostic) => {
                            let result = if is_infrastructure_diagnostic(&diagnostic) {
                                infrastructure_validation_failure(
                                    "Deterministic test verification hit an infrastructure failure",
                                    diagnostic,
                                    total_elapsed,
                                )
                            } else {
                                validation::fail(
                                    5,
                                    "build",
                                    vec!["Deterministic test verification failed".to_string()],
                                    diagnostic,
                                    total_elapsed,
                                )
                            };
                            self.send(RunEvent::ValidationCompleted(result)).await;
                            return;
                        }
                    };
                    info!(
                        attempt,
                        iterations,
                        total_tool_calls,
                        total_elapsed,
                        "Agentic build loop completed successfully"
                    );
                    let mut r = validation::pass(5, "build", total_elapsed);
                    r.diagnostic = format!(
                        "Agentic build: ✓ ({} iterations, {} tool calls; {} tests observed)\n\nAgent summary: {}\n\n{}\n\n{}",
                        iterations,
                        total_tool_calls,
                        observed_tests,
                        summary,
                        build_diagnostic,
                        test_diagnostic,
                    );
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_file(attempt, "build-validation.json", &r)
                            .await
                            .ok();
                        artifacts
                            .save_attempt_raw(attempt, "agent-summary.txt", summary)
                            .await
                            .ok();
                    }
                    self.send(RunEvent::ValidationCompleted(r)).await;
                    return;
                }
                crate::agent_loop::AgentLoopResult::Failed { error, iterations } => {
                    let total_elapsed = start.elapsed().as_secs_f64();
                    warn!(attempt, iterations, %error, "Agentic build loop failed");
                    let result = if is_infrastructure_diagnostic(error) {
                        infrastructure_validation_failure(
                            "Agentic build stopped on an infrastructure failure",
                            format!(
                                "Agentic build failed after {} iterations: {}",
                                iterations, error
                            ),
                            total_elapsed,
                        )
                    } else {
                        ValidationResult {
                            level: 5,
                            level_name: "build".to_string(),
                            passed: false,
                            error_count: 1,
                            warning_count: 0,
                            suggestion_count: 0,
                            actionable_errors: vec![format!("Agent loop failed: {}", error)],
                            structured_errors: vec![],
                            diagnostic: format!(
                                "Agentic build failed after {} iterations: {}",
                                iterations, error
                            ),
                            elapsed_secs: total_elapsed,
                        }
                    };
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_file(attempt, "build-validation.json", &result)
                            .await
                            .ok();
                    }
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                crate::agent_loop::AgentLoopResult::MaxIterationsReached {
                    iterations,
                    total_tool_calls,
                    last_failed_build,
                } => {
                    let total_elapsed = start.elapsed().as_secs_f64();
                    warn!(
                        attempt,
                        iterations, total_tool_calls, "Agentic build loop hit max iterations"
                    );
                    let result = ValidationResult {
                        level: 5,
                        level_name: "build".to_string(),
                        passed: false,
                        error_count: 1,
                        warning_count: 0,
                        suggestion_count: 0,
                        actionable_errors: vec![format!(
                            "Agent loop exhausted {} iterations. Last failed build: {}",
                            iterations,
                            last_failed_build
                                .as_deref()
                                .unwrap_or("no compiler diagnostic captured")
                        )],
                        structured_errors: vec![],
                        diagnostic: format!(
                            "Agentic build did not complete within {} iterations ({} tool calls)\n\n{}",
                            iterations,
                            total_tool_calls,
                            last_failed_build
                                .as_deref()
                                .unwrap_or("No compiler diagnostic captured")
                        ),
                        elapsed_secs: total_elapsed,
                    };
                    if let Some(artifacts) = &self.artifacts {
                        artifacts
                            .save_attempt_file(attempt, "build-validation.json", &result)
                            .await
                            .ok();
                    }
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            }
        }

        // Fallback: no entities found, verify the generated project directly.
        let total_elapsed = start.elapsed().as_secs_f64();
        info!(
            attempt,
            total_elapsed, "No entities for agentic build; lib-only validation"
        );
        let r = match verify_generated_build(&build_dir, &build_target).await {
            Ok(build_diagnostic) => match verify_generated_tests(&build_dir, &build_target).await {
                Ok((test_diagnostic, observed_tests)) => {
                    let mut result = validation::pass(5, "build", total_elapsed);
                    result.diagnostic = format!(
                        "Lib-only deterministic build and test passed ({observed_tests} tests observed).\n\n{build_diagnostic}\n\n{test_diagnostic}"
                    );
                    result
                }
                Err(diagnostic) if is_infrastructure_diagnostic(&diagnostic) => {
                    infrastructure_validation_failure(
                        "Generated project test verification hit an infrastructure failure",
                        diagnostic,
                        total_elapsed,
                    )
                }
                Err(diagnostic) => validation::fail(
                    5,
                    "build",
                    vec!["Generated project failed deterministic test verification".to_string()],
                    diagnostic,
                    total_elapsed,
                ),
            },
            Err(diagnostic) if is_infrastructure_diagnostic(&diagnostic) => {
                infrastructure_validation_failure(
                    "Generated project build verification hit an infrastructure failure",
                    diagnostic,
                    total_elapsed,
                )
            }
            Err(diagnostic) => validation::fail(
                5,
                "build",
                vec!["Generated project failed deterministic build verification".to_string()],
                diagnostic,
                total_elapsed,
            ),
        };
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "build-validation.json", &r)
                .await
                .ok();
        }
        self.send(RunEvent::ValidationCompleted(r)).await;
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
            &["See diagnostic output for errors".to_string()],
            self.profile.run.diagnostic_character_limit,
        );

        let _messages: Vec<ChatMessage> = message_pairs
            .into_iter()
            .map(|(role, content)| ChatMessage {
                role,
                content: Some(content),
                ..Default::default()
            })
            .collect();

        info!(attempt, "Sending repair request");

        // Signal that repair is scheduled, which will transition to Generating
        self.send(RunEvent::RepairScheduled { attempt }).await;
    }

    async fn write_final(&mut self) {
        if let Some(candidate) = &self.candidate {
            if let Some(artifacts) = &self.artifacts {
                match artifacts.save_final_artifact(candidate).await {
                    Ok(model_path) => {
                        let final_path = if let Some(workspace) = &self.workspace_dir {
                            match artifacts.save_final_workspace(workspace).await {
                                Ok(path) => path,
                                Err(error) => {
                                    self.send(RunEvent::Failed(AgentError::InfrastructureError {
                                        detail: format!(
                                            "Failed to snapshot final workspace: {error}"
                                        ),
                                    }))
                                    .await;
                                    return;
                                }
                            }
                        } else {
                            model_path
                        };
                        info!(path = %final_path.display(), "Final artifact saved");
                        self.send(RunEvent::FinalArtifactWritten(final_path)).await;
                    }
                    Err(e) => {
                        self.send(RunEvent::Failed(AgentError::InfrastructureError {
                            detail: format!("Failed to write final artifact: {e}"),
                        }))
                        .await;
                    }
                }
            } else {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: "Run artifact store is unavailable; final output cannot be persisted"
                        .to_string(),
                }))
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
                .await
                .ok();
        }
    }

    async fn send(&mut self, event: RunEvent) {
        // Capture validation errors for repair prompts
        if let RunEvent::ValidationCompleted(ref result) = event {
            if !result.passed {
                self.last_actionable_errors = result.actionable_errors.clone();
            }
        }
        if self.event_tx.send(event).await.is_err() {
            error!("Failed to send event — channel closed");
        }
    }

    /// Get the configured model backend.
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

    /// Get all generated candidate files for interactive previews.
    pub fn candidate_files(&self) -> &[(String, String)] {
        &self.candidate_files
    }

    /// Return the generated application workspace used for follow-up coding.
    pub fn workspace_dir(&self) -> Option<&Path> {
        self.workspace_dir.as_deref()
    }

    fn write_workspace_validation_evidence(&self, workspace: &Path) -> Result<(), String> {
        let model_files = if self.candidate_files.is_empty() {
            self.candidate
                .as_ref()
                .map(|content| vec![("main.xml".to_string(), content.clone())])
                .unwrap_or_default()
        } else {
            self.candidate_files.clone()
        };
        let evidence = serde_json::json!({
            "schema": "klintcode-validation-evidence-v1",
            "model_files": model_files.iter().map(|(name, _)| name).collect::<Vec<_>>(),
            "object_count": validation::count_model_objects(&model_files),
            "acceptance_spec": self.task.as_ref().and_then(|task| task.acceptance_spec.as_ref()),
            "domain_validation": self.last_domain_validation.as_ref(),
        });
        let evidence_dir = workspace.join(".klintcode");
        std::fs::create_dir_all(&evidence_dir)
            .map_err(|error| format!("Failed to create {}: {error}", evidence_dir.display()))?;
        let evidence_path = evidence_dir.join("validation-evidence.json");
        let content = serde_json::to_vec_pretty(&evidence)
            .map_err(|error| format!("Failed to serialize validation evidence: {error}"))?;
        std::fs::write(&evidence_path, content)
            .map_err(|error| format!("Failed to write {}: {error}", evidence_path.display()))
    }

    fn followup_workspace(&self) -> Result<PathBuf, String> {
        let path = self.workspace_dir.as_ref().ok_or_else(|| {
            "No generated workspace is available for follow-up coding".to_string()
        })?;
        if !path.is_dir() {
            return Err(format!(
                "Generated workspace no longer exists: {}",
                path.display()
            ));
        }
        Ok(path.clone())
    }

    /// Store actionable errors for repair context
    pub fn set_last_errors(&mut self, _errors: Vec<String>) {
        // Will be used when building repair messages
        // For now this is a placeholder for richer repair context
    }
}

async fn prepare_domain_model_files(
    artifacts: &RunArtifacts,
    attempt: u8,
    candidate_files: &[(String, String)],
    candidate: Option<&str>,
) -> Result<PathBuf, String> {
    let attempt_dir = artifacts
        .create_attempt(attempt)
        .await
        .map_err(|error| format!("Failed to create attempt directory: {error}"))?;
    let model_dir = attempt_dir.join("model");
    std::fs::create_dir_all(&model_dir)
        .map_err(|error| format!("Failed to create {}: {error}", model_dir.display()))?;

    let fallback;
    let files = if candidate_files.is_empty() {
        fallback = vec![(
            "main.xml".to_string(),
            candidate
                .ok_or_else(|| "No candidate model is available".to_string())?
                .to_string(),
        )];
        fallback.as_slice()
    } else {
        candidate_files
    };
    for (name, content) in files {
        let relative = Path::new(name);
        if relative.is_absolute()
            || relative
                .components()
                .any(|component| !matches!(component, std::path::Component::Normal(_)))
        {
            return Err(format!("Unsafe generated model file name `{name}`"));
        }
        if !matches!(
            relative
                .extension()
                .and_then(|extension| extension.to_str()),
            Some("xml" | "ksml")
        ) {
            return Err(format!("Generated model include is not XML/KSML: `{name}`"));
        }
        let destination = model_dir.join(relative);
        if let Some(parent) = destination.parent() {
            std::fs::create_dir_all(parent)
                .map_err(|error| format!("Failed to create {}: {error}", parent.display()))?;
        }
        std::fs::write(&destination, strip_markdown_fences(content)).map_err(|error| {
            format!(
                "Failed to write domain model file {}: {error}",
                destination.display()
            )
        })?;
    }
    Ok(model_dir)
}

async fn run_domain_validation(model_dir: &Path) -> ValidationResult {
    let started = std::time::Instant::now();
    let workspace = model_dir.parent().unwrap_or(model_dir);
    let mut command = tokio::process::Command::new("cargo");
    crate::process_env::apply_safe_environment(&mut command, workspace);
    command
        .args(["teaql", "--input"])
        .arg(model_dir)
        .arg("evaluate")
        .current_dir(workspace);
    let output = match crate::process_output::run_bounded_output(
        &mut command,
        std::time::Duration::from_secs(120),
        512 * 1024,
    )
    .await
    {
        Ok(output) => output,
        Err(error) => {
            return validation::fail(
                3,
                "domain",
                vec![format!(
                    "{} TeaQL domain validator is unavailable",
                    INFRASTRUCTURE_FAILURE_PREFIX
                )],
                error,
                started.elapsed().as_secs_f64(),
            );
        }
    };
    let combined = format!(
        "{}\n{}",
        String::from_utf8_lossy(&output.stdout),
        String::from_utf8_lossy(&output.stderr)
    );
    if !output.status.success() && is_infrastructure_diagnostic(&combined) {
        return validation::fail(
            3,
            "domain",
            vec![format!(
                "{} TeaQL domain validator is unavailable",
                INFRASTRUCTURE_FAILURE_PREFIX
            )],
            combined,
            started.elapsed().as_secs_f64(),
        );
    }
    let mut result = validation::domain::parse_teaql_output(&combined);
    result.elapsed_secs = started.elapsed().as_secs_f64();
    if !output.status.success() && result.error_count == 0 {
        result.passed = false;
        result.error_count = 1;
        result.actionable_errors.push(format!(
            "TeaQL evaluate failed with exit code {:?}",
            output.status.code()
        ));
    }
    result
}

async fn verify_generated_build(build_dir: &Path, build_target: &str) -> Result<String, String> {
    let mut command = if build_target.starts_with("java") {
        let mut command = tokio::process::Command::new("mvn");
        command.args(["compile", "-f", "pom.xml"]);
        command
    } else {
        let mut command = tokio::process::Command::new("cargo");
        command.arg("check");
        command
    };
    crate::process_env::apply_safe_environment(&mut command, build_dir);
    command.current_dir(build_dir);

    let output = crate::process_output::run_bounded_output(
        &mut command,
        std::time::Duration::from_secs(300),
        512 * 1024,
    )
    .await
    .map_err(|error| format!("Deterministic build verification failed: {error}"))?;

    let diagnostic = format!(
        "Exit status: {}\nSTDOUT:\n{}\nSTDERR:\n{}",
        output.status,
        String::from_utf8_lossy(&output.stdout),
        String::from_utf8_lossy(&output.stderr)
    );

    if output.status.success() {
        Ok(diagnostic)
    } else {
        Err(diagnostic)
    }
}

async fn verify_followup_outcome(
    workspace: &Path,
    before: &crate::followup_acceptance::WorkspaceSnapshot,
    acceptance: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
    build_target: &str,
) -> Result<String, String> {
    let build_diagnostic = verify_generated_build(workspace, build_target)
        .await
        .map_err(|diagnostic| {
            format!("Independent follow-up build verification failed:\n{diagnostic}")
        })?;
    let (test_diagnostic, test_count) = verify_generated_tests(workspace, build_target)
        .await
        .map_err(|diagnostic| {
            format!("Independent follow-up test verification failed:\n{diagnostic}")
        })?;

    let default_evidence = format!(
        "Independent build and test verification passed ({test_count} tests observed).\n\n{}\n\n{}",
        bounded_text(&build_diagnostic, 8_000),
        bounded_text(&test_diagnostic, 8_000)
    );
    let Some(acceptance) = acceptance else {
        return Ok(format!(
            "{default_evidence}\n\nNo explicit machine acceptance contract was supplied; this yielded result is verified only for workspace change, build, and test command success."
        ));
    };

    let report = acceptance.verify(workspace, before).await?;
    let report_path = workspace.join(".klintcode/followup-acceptance-report.json");
    if let Some(parent) = report_path.parent() {
        std::fs::create_dir_all(parent)
            .map_err(|error| format!("Failed to create follow-up evidence directory: {error}"))?;
    }
    let report_content = serde_json::to_vec_pretty(&report)
        .map_err(|error| format!("Failed to serialize follow-up acceptance report: {error}"))?;
    std::fs::write(&report_path, report_content).map_err(|error| {
        format!(
            "Failed to write follow-up acceptance report {}: {error}",
            report_path.display()
        )
    })?;

    if report.passed {
        Ok(format!("{default_evidence}\n\n{}", report.diagnostic()))
    } else {
        Err(format!("{default_evidence}\n\n{}", report.diagnostic()))
    }
}

async fn verify_generated_tests(
    build_dir: &Path,
    build_target: &str,
) -> Result<(String, usize), String> {
    let mut command = if build_target.starts_with("java") {
        let mut command = tokio::process::Command::new("mvn");
        command.args(["test", "-f", "pom.xml"]);
        command
    } else {
        let mut command = tokio::process::Command::new("cargo");
        command.arg("test");
        command
    };
    crate::process_env::apply_safe_environment(&mut command, build_dir);
    command.current_dir(build_dir);
    let output = crate::process_output::run_bounded_output(
        &mut command,
        std::time::Duration::from_secs(300),
        512 * 1024,
    )
    .await
    .map_err(|error| format!("Deterministic test verification failed: {error}"))?;
    let diagnostic = format!(
        "Exit status: {}\nSTDOUT:\n{}\nSTDERR:\n{}",
        output.status,
        String::from_utf8_lossy(&output.stdout),
        String::from_utf8_lossy(&output.stderr)
    );
    if !output.status.success() {
        return Err(diagnostic);
    }

    let combined = format!(
        "{}\n{}",
        String::from_utf8_lossy(&output.stdout),
        String::from_utf8_lossy(&output.stderr)
    );
    Ok((diagnostic, observed_test_count(&combined)))
}

fn observed_test_count(output: &str) -> usize {
    output
        .lines()
        .filter_map(|line| {
            line.trim()
                .strip_prefix("running ")?
                .strip_suffix(" tests")?
                .parse::<usize>()
                .ok()
        })
        .sum()
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

fn ensure_standalone_cargo_workspace(content: &str) -> String {
    let has_workspace = content.lines().any(|line| line.trim() == "[workspace]");
    if has_workspace {
        content.to_string()
    } else {
        format!("{}\n[workspace]\n", content.trim_end())
    }
}

/// Put the validated model and stable TeaQL instructions inside the generated
/// application workspace so every continuation can use the same inputs.
fn prepare_workspace_context(attempt_dir: &Path, build_dir: &Path) -> Result<(), String> {
    let source_model = attempt_dir.join("model");
    if !source_model.is_dir() {
        return Err(format!(
            "Validated model directory is missing: {}",
            source_model.display()
        ));
    }
    copy_directory_files(&source_model, &build_dir.join("model"))?;

    let agents_path = build_dir.join("AGENTS.md");
    let agents = std::fs::read_to_string(&agents_path)
        .map_err(|error| format!("Failed to read {}: {error}", agents_path.display()))?;
    let mut normalized = canonicalize_teaql_input_paths(&agents);
    normalized.push_str(
        "\n\n## KlintCode Workspace Context\n\n\
         All agent tools already run from this workspace root. Use relative paths and do not `cd` back into the workspace.\n\
         The validated model is `model/main.xml`. Every TeaQL command must use the exact form\n\
         `cargo teaql --input model/main.xml rust-assist-[action]/[entity-name]`.\n\
         Complete pre-fetched responses are stored under `.klintcode/assist/`; run the canonical command for actions not yet saved there.\n\
         Deterministic model and domain-validation facts are stored in `.klintcode/validation-evidence.json`.\n\
         Any review or running report must cite that evidence and must not replace validator results with inferred claims.\n",
    );
    std::fs::write(&agents_path, normalized)
        .map_err(|error| format!("Failed to update {}: {error}", agents_path.display()))
}

fn copy_directory_files(source: &Path, destination: &Path) -> Result<(), String> {
    std::fs::create_dir_all(destination)
        .map_err(|error| format!("Failed to create {}: {error}", destination.display()))?;
    let entries = std::fs::read_dir(source)
        .map_err(|error| format!("Failed to read {}: {error}", source.display()))?;
    for entry in entries {
        let entry = entry.map_err(|error| error.to_string())?;
        let source_path = entry.path();
        let destination_path = destination.join(entry.file_name());
        if source_path.is_dir() {
            copy_directory_files(&source_path, &destination_path)?;
        } else if source_path.is_file() {
            std::fs::copy(&source_path, &destination_path).map_err(|error| {
                format!(
                    "Failed to copy {} to {}: {error}",
                    source_path.display(),
                    destination_path.display()
                )
            })?;
        }
    }
    Ok(())
}

fn canonicalize_teaql_input_paths(content: &str) -> String {
    const MARKER: &str = "--input ";
    let mut remaining = content;
    let mut result = String::with_capacity(content.len());
    while let Some(index) = remaining.find(MARKER) {
        let value_start = index + MARKER.len();
        result.push_str(&remaining[..value_start]);
        let value = &remaining[value_start..];
        let value_end = value
            .find(|character: char| character.is_whitespace() || character == '`')
            .unwrap_or(value.len());
        result.push_str("model/main.xml");
        remaining = &value[value_end..];
    }
    result.push_str(remaining);
    result
}

/// Snapshot user-editable workspace files. Generated libraries, build output,
/// cached assist responses, and lockfiles are intentionally excluded.
pub(crate) fn application_workspace_snapshot(
    root: &Path,
) -> std::collections::BTreeMap<PathBuf, Vec<u8>> {
    fn collect(
        root: &Path,
        current: &Path,
        files: &mut std::collections::BTreeMap<PathBuf, Vec<u8>>,
    ) {
        let Ok(entries) = std::fs::read_dir(current) else {
            return;
        };
        for entry in entries.flatten() {
            let path = entry.path();
            let Ok(file_type) = entry.file_type() else {
                continue;
            };
            // Never follow symlinks: a generated source directory could be
            // aliased under an otherwise application-owned path.
            if file_type.is_symlink() {
                continue;
            }
            let Ok(relative) = path.strip_prefix(root) else {
                continue;
            };
            let first = relative
                .components()
                .next()
                .and_then(|part| part.as_os_str().to_str());
            if matches!(
                first,
                Some(
                    "lib"
                        | "rust-lib-core"
                        | "java-lib-core"
                        | "java-web-spring-boot"
                        | "target"
                        | ".git"
                        | ".klintcode"
                        | "model"
                )
            ) {
                continue;
            }
            if file_type.is_dir() {
                collect(root, &path, files);
            } else if file_type.is_file()
                && relative.file_name().and_then(|name| name.to_str()) != Some("Cargo.lock")
                && !matches!(
                    relative
                        .extension()
                        .and_then(|extension| extension.to_str()),
                    Some("db" | "sqlite" | "sqlite3" | "log")
                )
            {
                if let Ok(content) = std::fs::read(&path) {
                    files.insert(relative.to_path_buf(), content);
                }
            }
        }
    }

    let mut files = std::collections::BTreeMap::new();
    collect(root, root, &mut files);
    files
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

/// Recursively find Cargo.toml and pom.xml files for patching
fn walkdir_toml_xml(dir: &Path) -> Vec<PathBuf> {
    let mut results = Vec::new();
    if let Ok(entries) = std::fs::read_dir(dir) {
        for entry in entries.flatten() {
            let path = entry.path();
            let Ok(file_type) = entry.file_type() else {
                continue;
            };
            if file_type.is_symlink() {
                continue;
            }
            if file_type.is_dir() {
                let name = path.file_name().and_then(|name| name.to_str());
                if matches!(
                    name,
                    Some("src" | "target" | ".git" | ".klintcode" | "model")
                ) {
                    continue;
                }
                results.extend(walkdir_toml_xml(&path));
            } else if file_type.is_file() {
                let name = path.file_name().and_then(|n| n.to_str()).unwrap_or("");
                if name == "Cargo.toml" || name == "pom.xml" || name == "build.gradle" {
                    results.push(path);
                }
            }
        }
    }
    results
}

const REQUIRED_CARGO_TEAQL_VERSION: &str = "2.0.11";

async fn verify_cargo_teaql_version() -> Result<(), String> {
    let mut command = tokio::process::Command::new("cargo");
    crate::process_env::apply_safe_environment(&mut command, Path::new("."));
    command.args(["teaql", "--version"]);
    let output = crate::process_output::run_bounded_output(
        &mut command,
        std::time::Duration::from_secs(10),
        16 * 1024,
    )
    .await
    .map_err(|error| format!("Failed to execute `cargo teaql --version`: {error}"))?;

    let stdout = String::from_utf8_lossy(&output.stdout);
    let stderr = String::from_utf8_lossy(&output.stderr);
    if !output.status.success() {
        return Err(format!(
            "`cargo teaql --version` failed with {}. stdout: {}; stderr: {}",
            output.status,
            bounded_text(stdout.trim(), 1_000),
            bounded_text(stderr.trim(), 1_000)
        ));
    }
    let observed = parse_cargo_teaql_version(&stdout).ok_or_else(|| {
        format!(
            "Could not parse cargo-teaql version from `{}`; required exactly {REQUIRED_CARGO_TEAQL_VERSION}",
            bounded_text(stdout.trim(), 1_000)
        )
    })?;
    if observed != REQUIRED_CARGO_TEAQL_VERSION {
        return Err(format!(
            "cargo-teaql version {observed} is installed; required exactly {REQUIRED_CARGO_TEAQL_VERSION}"
        ));
    }
    Ok(())
}

fn parse_cargo_teaql_version(output: &str) -> Option<&str> {
    let words = output.split_whitespace().collect::<Vec<_>>();
    words.windows(2).find_map(|pair| {
        let program = pair[0]
            .trim_matches(|character: char| !character.is_ascii_alphanumeric() && character != '-');
        if matches!(program, "teaql" | "cargo-teaql") {
            Some(
                pair[1].trim_matches(|character: char| {
                    !(character.is_ascii_digit() || character == '.')
                }),
            )
        } else {
            None
        }
    })
}

fn generation_validation_failure(
    summary: impl Into<String>,
    diagnostic: String,
    elapsed_secs: f64,
) -> ValidationResult {
    let summary = summary.into();
    if is_infrastructure_diagnostic(&diagnostic) {
        infrastructure_validation_failure(summary, diagnostic, elapsed_secs)
    } else {
        validation::fail(5, "build", vec![summary], diagnostic, elapsed_secs)
    }
}

fn infrastructure_validation_failure(
    summary: impl Into<String>,
    diagnostic: String,
    elapsed_secs: f64,
) -> ValidationResult {
    validation::fail(
        5,
        "build",
        vec![format!(
            "{} {}",
            INFRASTRUCTURE_FAILURE_PREFIX,
            summary.into()
        )],
        diagnostic,
        elapsed_secs,
    )
}

pub(crate) fn is_infrastructure_diagnostic(diagnostic: &str) -> bool {
    let normalized = diagnostic.to_ascii_lowercase();
    [
        "[infrastructure]",
        "internal server error",
        "http 500",
        "http 502",
        "http 503",
        "http 504",
        "error (500)",
        "error (502)",
        "error (503)",
        "error (504)",
        "bad gateway",
        "service unavailable",
        "gateway timeout",
        "timed out",
        "connection refused",
        "failed to connect",
        "could not connect",
        "network is unreachable",
        "name resolution",
        "dns error",
        "tls error",
        "certificate error",
    ]
    .iter()
    .any(|marker| normalized.contains(marker))
}

fn bounded_text(text: &str, max_bytes: usize) -> String {
    if text.len() <= max_bytes {
        return text.to_string();
    }
    let suffix = "\n[context truncated]";
    let mut boundary = max_bytes.saturating_sub(suffix.len());
    while boundary > 0 && !text.is_char_boundary(boundary) {
        boundary -= 1;
    }
    format!("{}{}", &text[..boundary], suffix)
}

fn render_agentic_system_prompt(template: &str, workspace: &Path, assist: &str) -> String {
    let rendered = template
        .replace("{{project_dir}}", &workspace.display().to_string())
        .replace("{{#if assist_outputs}}", "")
        .replace("{{/if}}", "")
        .replace("{{assist_outputs}}", assist);
    // Detect un-substituted template variables that would mislead the model.
    // This is a hard invariant: if the template gains a new variable and the
    // renderer is not updated, we want an immediate loud failure instead of a
    // silently broken prompt that is hard to debug.
    if let Some(pos) = rendered.find("{{") {
        let fragment = &rendered[pos..][..rendered[pos..].find("}}").map(|e| e + 2).unwrap_or(40).min(rendered[pos..].len())];
        panic!(
            "render_agentic_system_prompt: un-substituted template variable in rendered prompt: '{fragment}'. \
             Add the replacement to this function."
        );
    }
    rendered
}

fn missing_followup_environment<'a>(
    specs: impl IntoIterator<Item = &'a crate::followup_acceptance::FollowUpAcceptanceSpec>,
) -> Vec<String> {
    let mut missing = specs
        .into_iter()
        .flat_map(|spec| &spec.commands)
        .flat_map(|command| &command.env_ref)
        .filter(|name| std::env::var_os(name).is_none())
        .cloned()
        .collect::<Vec<_>>();
    missing.sort();
    missing.dedup();
    missing
}

fn build_followup_prompt(
    workspace: &Path,
    instruction: &str,
    original_task: &str,
    history: &[FollowUpRecord],
    acceptance: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
) -> String {
    let previous_changes = history
        .iter()
        .rev()
        .take(5)
        .rev()
        .map(|record| {
            format!(
                "- Attempt {}: {}\n  Result: {}",
                record.attempt,
                bounded_text(&record.instruction, 1_000),
                bounded_text(&record.summary, 2_000)
            )
        })
        .collect::<Vec<_>>()
        .join("\n");
    let previous_changes = if previous_changes.is_empty() {
        "- No previous follow-up changes.".to_string()
    } else {
        previous_changes
    };
    let acceptance = acceptance.map_or_else(
        || {
            "No explicit machine acceptance contract was supplied. You must still complete the requested change, run the independent build and test commands, and yield only after both pass. A max-iteration result will be rejected."
                .to_string()
        },
        crate::followup_acceptance::FollowUpAcceptanceSpec::render_checklist,
    );

    format!(
        "You are already operating at the workspace root `{workspace}`. Do not `cd` to this path and do not prefix tool paths with it; use `.` for the root. The validated TeaQL model is `model/main.xml`, and complete cached assist responses are under `.klintcode/assist/`.\n\n\
         # Original Task\n{original_task}\n\n\
         # Previously Verified Follow-ups\n{previous_changes}\n\n\
         # Current Follow-up\n{instruction}\n\n\
         # Machine Acceptance\n{acceptance}\n\n\
         Inspect only the application code and workspace configuration and apply the requested changes using write_file/run_command. Compilation alone does not complete this follow-up. Run every required build, test, runtime, and artifact check. Respond with a concise summary only after the full acceptance checklist passes; otherwise keep fixing the application workspace.",
        workspace = workspace.display(),
        original_task = bounded_text(original_task, 6_000),
        instruction = bounded_text(instruction, 6_000),
        acceptance = bounded_text(&acceptance, 6_000),
    )
}

/// Strip markdown code fences from LLM output (e.g. ```xml ... ```)
fn strip_markdown_fences(content: &str) -> String {
    let trimmed = content.trim();

    // Find the first occurrence of ```
    if let Some(start_idx) = trimmed.find("```") {
        let after_start = &trimmed[start_idx + 3..];

        // Skip the optional language identifier (e.g., xml or ksml)
        let content_start = if after_start.to_lowercase().starts_with("xml") {
            after_start[3..].trim_start()
        } else if after_start.to_lowercase().starts_with("ksml") {
            after_start[4..].trim_start()
        } else {
            // Just skip to the next newline if there's any text on the same line
            if let Some(newline_idx) = after_start.find('\n') {
                &after_start[newline_idx + 1..]
            } else {
                after_start
            }
        };

        // Find the matching end fence
        if let Some(end_idx) = content_start.rfind("```") {
            return content_start[..end_idx].trim().to_string();
        }
    }

    // If no markdown fences are found, return the trimmed content
    trimmed.to_string()
}

#[cfg(test)]
mod tests {
    use super::*;

    fn test_executor_with_sender(event_tx: mpsc::Sender<RunEvent>) -> PipelineExecutor {
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR"))
                .join("../../simulator/scenarios/happy-path.toml"),
        );
        PipelineExecutor::new(
            profile,
            event_tx,
            PathBuf::from("runs"),
            "test-run".to_string(),
        )
        .expect("test executor")
    }

    fn test_executor() -> PipelineExecutor {
        let (event_tx, _event_rx) = mpsc::channel(4);
        test_executor_with_sender(event_tx)
    }

    #[test]
    fn workspace_validation_evidence_uses_validator_facts() {
        let workspace = tempfile::tempdir().expect("workspace");
        let mut executor = test_executor();
        executor.candidate_files = vec![
            (
                "main.xml".to_string(),
                r#"<root><_include file="more.xml"/><company _name="Company"/></root>"#.to_string(),
            ),
            (
                "more.xml".to_string(),
                r#"<root><move_order _name="Move Order"/></root>"#.to_string(),
            ),
        ];
        let mut task = TaskPackageData::from_prompt("evidence", "task", PathBuf::from("."));
        task.acceptance_spec = Some(serde_json::json!({ "min_object_count": 2 }));
        executor.task = Some(task);
        let mut domain = validation::pass(3, "domain", 0.1);
        domain.warning_count = 1;
        domain.diagnostic = "KSML-DOMAIN-ROOT-002".to_string();
        executor.last_domain_validation = Some(domain);

        executor
            .write_workspace_validation_evidence(workspace.path())
            .expect("write evidence");

        let evidence: serde_json::Value = serde_json::from_slice(
            &std::fs::read(workspace.path().join(".klintcode/validation-evidence.json"))
                .expect("read evidence"),
        )
        .expect("parse evidence");
        assert_eq!(evidence["object_count"], 2);
        assert_eq!(evidence["domain_validation"]["warning_count"], 1);
        assert_eq!(evidence["acceptance_spec"]["min_object_count"], 2);
    }

    #[tokio::test]
    async fn local_validation_counts_objects_across_include_files() {
        let (event_tx, mut event_rx) = mpsc::channel(4);
        let mut executor = test_executor_with_sender(event_tx);
        let main = r#"<root><_include file="operations.xml"/></root>"#.to_string();
        executor.candidate = Some(main.clone());
        executor.candidate_files = vec![
            ("main.xml".to_string(), main),
            (
                "operations.xml".to_string(),
                r#"<root><move_order _name="Move Order"/><route_plan _name="Route Plan"/></root>"#
                    .to_string(),
            ),
        ];
        let mut task = TaskPackageData::from_inline_text("model operations");
        task.acceptance_spec = Some(serde_json::json!({
            "expected_objects": ["MoveOrder", "RoutePlan"],
            "expected_object_count": 2
        }));
        executor.task = Some(task);

        executor.local_validate(1).await;

        let event = event_rx.recv().await.expect("validation event");
        let RunEvent::ValidationCompleted(result) = event else {
            panic!("expected validation completion");
        };
        assert!(result.passed, "{:?}", result.actionable_errors);
    }

    #[tokio::test]
    async fn task_acceptance_drives_and_protects_the_build_target() {
        let task = tempfile::tempdir().expect("task package");
        std::fs::write(task.path().join("task.md"), "build a model").unwrap();
        std::fs::write(
            task.path().join("acceptance.json"),
            r#"{"schema":"ksml-acceptance-v1","build_targets":["rust-lib-core"]}"#,
        )
        .unwrap();

        let (event_tx, mut event_rx) = mpsc::channel(4);
        let mut executor = test_executor_with_sender(event_tx);
        executor.load_task_from_path(task.path()).await;
        assert_eq!(executor.build_target.as_deref(), Some("rust-lib-core"));
        assert!(matches!(
            event_rx.recv().await,
            Some(RunEvent::TaskLoaded(_))
        ));

        let (event_tx, mut event_rx) = mpsc::channel(4);
        let mut conflicting = test_executor_with_sender(event_tx);
        conflicting.set_build_target("java-lib-core".to_string());
        conflicting.load_task_from_path(task.path()).await;
        assert!(matches!(
            event_rx.recv().await,
            Some(RunEvent::TaskLoadFailed(error)) if error.contains("conflicts")
        ));
    }

    #[tokio::test]
    async fn task_followup_acceptance_sidecar_is_loaded_for_interactive_clients() {
        let task = tempfile::tempdir().expect("task package");
        std::fs::write(task.path().join("task.md"), "build a model").unwrap();
        std::fs::write(
            task.path().join("followup-acceptance.json"),
            format!(
                r#"{{"schema":"{}","commands":[{{"program":"cargo","args":["check"]}}]}}"#,
                crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
            ),
        )
        .unwrap();

        let (event_tx, mut event_rx) = mpsc::channel(4);
        let mut executor = test_executor_with_sender(event_tx);
        executor.load_task_from_path(task.path()).await;

        assert!(matches!(
            event_rx.recv().await,
            Some(RunEvent::TaskLoaded(_))
        ));
        assert_eq!(executor.followup_acceptance_specs.len(), 1);
    }

    #[test]
    fn followups_reuse_the_recorded_generated_workspace() {
        let mut executor = test_executor();
        assert!(executor.followup_workspace().is_err());

        let workspace = std::env::temp_dir().join(format!(
            "klintcode-followup-workspace-{}",
            std::process::id()
        ));
        std::fs::create_dir_all(&workspace).expect("create test workspace");
        executor.workspace_dir = Some(workspace.clone());

        assert_eq!(executor.followup_workspace().unwrap(), workspace);
        assert_eq!(executor.followup_workspace().unwrap(), workspace);

        std::fs::remove_dir_all(workspace).expect("remove test workspace");
    }

    #[tokio::test]
    async fn deterministic_build_verification_rejects_invalid_rust() {
        let workspace =
            std::env::temp_dir().join(format!("klintcode-invalid-build-{}", std::process::id()));
        std::fs::create_dir_all(workspace.join("src")).expect("create test workspace");
        std::fs::write(
            workspace.join("Cargo.toml"),
            "[package]\nname = \"invalid-followup\"\nversion = \"0.1.0\"\nedition = \"2024\"\n",
        )
        .expect("write manifest");
        std::fs::write(workspace.join("src/main.rs"), "fn main() { missing(); }\n")
            .expect("write invalid source");

        let result = verify_generated_build(&workspace, "rust-lib-core").await;

        assert!(result.is_err());
        let diagnostic = result.unwrap_err().to_lowercase();
        assert!(diagnostic.contains("error"));
        std::fs::remove_dir_all(workspace).expect("remove test workspace");
    }

    #[test]
    fn followup_prompt_carries_original_intent_and_bounded_history() {
        let history = vec![FollowUpRecord {
            attempt: 2,
            instruction: "add a query".to_string(),
            summary: "implemented the query".to_string(),
        }];

        let prompt = build_followup_prompt(
            Path::new("/workspace/build"),
            &"修".repeat(4_000),
            "build a school service",
            &history,
            None,
        );

        assert!(prompt.contains("build a school service"));
        assert!(prompt.contains("implemented the query"));
        assert!(prompt.contains("/workspace/build"));
        assert!(prompt.contains("[context truncated]"));
        assert!(prompt.contains("Compilation alone"));
        assert!(prompt.len() < 16_000);
    }

    #[test]
    fn followup_system_prompt_includes_saved_assist_context() {
        let rendered = render_agentic_system_prompt(
            "workspace={{project_dir}}\n{{#if assist_outputs}}{{assist_outputs}}{{/if}}",
            Path::new("/workspace/build"),
            "Q::school().new_entity(ctx)",
        );

        assert!(rendered.contains("workspace=/workspace/build"));
        assert!(rendered.contains("Q::school().new_entity(ctx)"));
        assert!(!rendered.contains("{{"));
    }

    #[test]
    fn teaql_server_500_is_classified_as_infrastructure_failure() {
        let result = generation_validation_failure(
            "cargo teaql rust-lib-core failed",
            "## Internal Server Error (500)\nStringTemplate failed".to_string(),
            1.0,
        );

        assert!(result.is_infrastructure_failure());
        assert!(result.actionable_errors[0].contains("rust-lib-core"));
    }

    #[test]
    fn model_derived_generation_error_remains_repairable() {
        let result = generation_validation_failure(
            "cargo teaql rust-lib-core failed",
            "Unknown KSML object reference: missing_school".to_string(),
            1.0,
        );

        assert!(!result.is_infrastructure_failure());
    }

    #[test]
    fn compiler_symbol_named_timeout_is_not_misclassified_as_infrastructure() {
        assert!(!is_infrastructure_diagnostic(
            "error[E0599]: no method named `timeout` found for struct Request"
        ));
        assert!(is_infrastructure_diagnostic(
            "request timed out while connecting to the backend"
        ));
    }

    #[test]
    fn cargo_teaql_version_parser_requires_the_reported_program_token() {
        assert_eq!(parse_cargo_teaql_version("teaql 2.0.11\n"), Some("2.0.11"));
        assert_eq!(
            parse_cargo_teaql_version("cargo-teaql v2.0.10\n"),
            Some("2.0.10")
        );
        assert_eq!(parse_cargo_teaql_version("2.0.11\n"), None);
    }

    #[test]
    fn missing_followup_environment_is_deduplicated_before_preflight() {
        let spec = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","commands":[{{"program":"cargo","args":["check"],"env_ref":["KLINTCODE_DEFINITELY_MISSING_ENV_91827","KLINTCODE_DEFINITELY_MISSING_ENV_91827"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();

        assert_eq!(
            missing_followup_environment(std::iter::once(&spec)),
            vec!["KLINTCODE_DEFINITELY_MISSING_ENV_91827"]
        );
    }

    #[test]
    fn generated_app_manifest_is_detached_from_parent_workspace() {
        let manifest = "[package]\nname = \"school-app\"\nversion = \"0.1.0\"\n";

        let fixed = ensure_standalone_cargo_workspace(manifest);

        assert!(fixed.ends_with("[workspace]\n"));
        assert_eq!(
            ensure_standalone_cargo_workspace(&fixed)
                .matches("[workspace]")
                .count(),
            1
        );
    }

    #[test]
    fn workspace_context_copies_model_and_rewrites_generated_instructions() {
        let attempt = tempfile::tempdir().expect("attempt directory");
        let build = attempt.path().join("build");
        std::fs::create_dir_all(attempt.path().join("model/nested")).unwrap();
        std::fs::create_dir_all(&build).unwrap();
        std::fs::write(attempt.path().join("model/main.xml"), "<root/>").unwrap();
        std::fs::write(
            attempt.path().join("model/nested/entities.xml"),
            "<school/>",
        )
        .unwrap();
        std::fs::write(
            build.join("AGENTS.md"),
            "cargo teaql --input models/school-service.xml rust-assist-query/school\n",
        )
        .unwrap();

        prepare_workspace_context(attempt.path(), &build).expect("prepare workspace context");

        assert_eq!(
            std::fs::read_to_string(build.join("model/main.xml")).unwrap(),
            "<root/>"
        );
        assert!(build.join("model/nested/entities.xml").is_file());
        let agents = std::fs::read_to_string(build.join("AGENTS.md")).unwrap();
        assert!(agents.contains("cargo teaql --input model/main.xml rust-assist-query/school"));
        assert!(!agents.contains("models/school-service.xml"));
        assert!(agents.contains("already run from this workspace root"));
    }

    #[test]
    fn application_snapshot_detects_source_changes_but_ignores_generated_files() {
        let workspace = tempfile::tempdir().expect("workspace");
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::create_dir_all(workspace.path().join("lib/src")).unwrap();
        std::fs::create_dir_all(workspace.path().join(".klintcode/assist")).unwrap();
        std::fs::write(workspace.path().join("src/lib.rs"), "pub fn first() {}\n").unwrap();
        std::fs::write(workspace.path().join("lib/src/generated.rs"), "generated\n").unwrap();

        let before = application_workspace_snapshot(workspace.path());
        std::fs::write(
            workspace.path().join("lib/src/generated.rs"),
            "regenerated\n",
        )
        .unwrap();
        std::fs::write(
            workspace.path().join(".klintcode/assist/query-school.md"),
            "cached assist\n",
        )
        .unwrap();
        assert_eq!(application_workspace_snapshot(workspace.path()), before);

        std::fs::write(workspace.path().join("src/lib.rs"), "pub fn second() {}\n").unwrap();
        assert_ne!(application_workspace_snapshot(workspace.path()), before);
    }

    #[test]
    fn rust_test_count_sums_all_test_binaries() {
        let output = "running 3 tests\n\ntest result: ok\n\nrunning 2 tests\n";
        assert_eq!(observed_test_count(output), 5);
        assert_eq!(observed_test_count("no test summary"), 0);
    }

    #[test]
    fn render_system_prompt_panics_on_unsubstituted_template_variable() {
        // A template that still contains an unknown placeholder must fail loudly
        // rather than silently pass a broken prompt to the model.
        let result = std::panic::catch_unwind(|| {
            render_agentic_system_prompt(
                "workspace={{project_dir}} unknown={{unknown_var}}",
                Path::new("/workspace"),
                "",
            )
        });
        assert!(result.is_err(), "should panic on unknown template variable");
    }

    #[test]
    fn render_system_prompt_succeeds_with_all_known_variables() {
        // Must NOT panic when all variables are substituted.
        let result = std::panic::catch_unwind(|| {
            render_agentic_system_prompt(
                "dir={{project_dir}}\n{{#if assist_outputs}}{{assist_outputs}}{{/if}}",
                Path::new("/workspace/build"),
                "Q::school()",
            )
        });
        assert!(result.is_ok());
        let rendered = result.unwrap();
        assert!(rendered.contains("/workspace/build"));
        assert!(rendered.contains("Q::school()"));
    }

    #[test]
    fn requires_workspace_change_is_true_when_no_acceptance_spec_is_present() {
        // Without a spec the guard must default to requiring a change,
        // so a no-op follow-up can never silently pass.
        let requires_change = None::<crate::followup_acceptance::FollowUpAcceptanceSpec>
            .as_ref()
            .map(|spec| spec.files.iter().any(|f| f.must_change))
            .unwrap_or(true);
        assert!(requires_change);
    }

    #[test]
    fn requires_workspace_change_is_false_when_spec_has_only_read_only_files() {
        // A spec whose files are all must_change=false (e.g. a query-only
        // verification) must NOT block acceptance just because source files
        // were not modified.
        let json = format!(
            r#"{{"schema":"{}","files":[{{"path":"src/main.rs","must_change":false}}],"commands":[{{"program":"cargo","args":["check"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        );
        let spec = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&json)
            .expect("valid spec");
        let requires_change = spec.files.iter().any(|f| f.must_change);
        assert!(!requires_change, "read-only spec should not require workspace modification");
    }
}
