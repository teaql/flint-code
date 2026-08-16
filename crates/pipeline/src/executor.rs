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
use std::collections::BTreeMap;
use std::collections::VecDeque;
use std::path::{Path, PathBuf};
use std::sync::Arc;
use std::time::Duration;
use tokio::sync::mpsc;
use tool_runner::remote_protocol::{ExecResult, FileKind};
use tracing::{error, info, warn};
use validation;

#[derive(Debug, Clone, Copy, Default, PartialEq, Eq, serde::Serialize, serde::Deserialize)]
#[serde(rename_all = "snake_case")]
enum SessionRecordPhase {
    InitialBuild,
    #[default]
    FollowUp,
}

#[derive(Debug, Clone, PartialEq, serde::Serialize, serde::Deserialize)]
struct SessionLedgerRecord {
    /// Defaults to `follow_up` so ledgers written before the phase field was
    /// introduced remain readable.
    #[serde(default)]
    phase: SessionRecordPhase,
    attempt: u8,
    instruction: String,
    summary: String,
    /// Independent verifier passes performed for this successful interaction.
    #[serde(default)]
    verification_rounds: usize,
    /// LLM round-trips observed directly by the agent loop.
    #[serde(default)]
    model_iterations: usize,
    /// Tool invocations observed directly by the agent loop.
    #[serde(default)]
    tool_calls: usize,
    /// Wall-clock duration measured by the executor, including verification.
    #[serde(default)]
    elapsed_secs: f64,
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
    /// Authoritative remote runner used by production SSH execution.
    ///
    /// `None` exists only for the legacy/local constructor and unit tests. A
    /// remote run never falls back to that path after it has been selected.
    remote_execution: Option<Arc<crate::execution::RemoteExecution>>,
    /// Workspace-relative cwd inside the attached runner session.
    remote_workspace_dir: Option<String>,
    /// Bounded TeaQL API examples gathered during initial workspace generation.
    assist_context: String,
    /// Compact summaries of deterministically verified continuation turns.
    followup_history: Vec<SessionLedgerRecord>,
    /// Cumulative structured interaction metrics persisted after each success.
    session_ledger: Vec<SessionLedgerRecord>,
    /// Explicit machine-verifiable contracts, aligned with queued follow-ups.
    followup_acceptance_specs: VecDeque<crate::followup_acceptance::FollowUpAcceptanceSpec>,
    /// Actionable errors from the most recent failed validation, fed into repair prompts.
    last_actionable_errors: Vec<String>,
    /// Most recent L3 result after task-specific forbidden diagnostics are enforced.
    last_domain_validation: Option<ValidationResult>,
    /// RAG retriever for skills and error troubleshooting.
    retriever: Option<Arc<dyn KnowledgeRetriever>>,
    /// Stable SQLite database namespace for the legacy local path only.
    /// Remote sessions resolve isolated database references inside the runner.
    sqlite_isolation: Option<crate::process_env::SqliteDatabaseIsolation>,
}

impl PipelineExecutor {
    /// Legacy local constructor retained for unit tests and migration callers.
    /// Production CLI/TUI execution must use [`Self::new_remote`].
    pub fn new(
        profile: ModelProfile,
        event_tx: mpsc::Sender<RunEvent>,
        runs_root: PathBuf,
        run_id: String,
    ) -> Result<Self, AgentError> {
        Self::new_inner(profile, event_tx, runs_root, run_id, None)
    }

    /// Construct a pipeline whose project filesystem and every project
    /// process are owned by one SSH runner session.
    pub fn new_remote(
        profile: ModelProfile,
        event_tx: mpsc::Sender<RunEvent>,
        runs_root: PathBuf,
        run_id: String,
        execution: Arc<crate::execution::RemoteExecution>,
    ) -> Result<Self, AgentError> {
        Self::new_inner(profile, event_tx, runs_root, run_id, Some(execution))
    }

    fn new_inner(
        profile: ModelProfile,
        event_tx: mpsc::Sender<RunEvent>,
        runs_root: PathBuf,
        run_id: String,
        remote_execution: Option<Arc<crate::execution::RemoteExecution>>,
    ) -> Result<Self, AgentError> {
        let client = ModelClient::from_profile(profile.clone())?;
        let sqlite_isolation = if remote_execution.is_none() {
            Some(
                crate::process_env::SqliteDatabaseIsolation::new().map_err(|error| {
                    AgentError::InfrastructureError {
                        detail: format!(
                            "Failed to create isolated SQLite runtime directory: {error}"
                        ),
                    }
                })?,
            )
        } else {
            None
        };
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
            remote_execution,
            remote_workspace_dir: None,
            assist_context: String::new(),
            followup_history: Vec::new(),
            session_ledger: Vec::new(),
            followup_acceptance_specs: VecDeque::new(),
            last_actionable_errors: Vec::new(),
            last_domain_validation: None,
            retriever: None,
            sqlite_isolation,
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
            return Err(format!(
                "{} RAG retriever is not configured",
                INFRASTRUCTURE_FAILURE_PREFIX
            ));
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
                Err(format!(
                    "{} RAG skill retrieval failed: {error}",
                    INFRASTRUCTURE_FAILURE_PREFIX
                ))
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

    /// Borrow the contract for the current follow-up without consuming it.
    ///
    /// A failed or interrupted follow-up can then be retried against exactly
    /// the same deterministic contract. The queue advances only after the
    /// independent verifier accepts the turn.
    fn current_followup_acceptance_spec(
        &self,
    ) -> Option<crate::followup_acceptance::FollowUpAcceptanceSpec> {
        self.followup_acceptance_specs.front().cloned()
    }

    fn settle_followup_acceptance_spec(&mut self, deterministically_verified: bool) {
        if deterministically_verified {
            self.followup_acceptance_specs.pop_front();
        }
    }

    /// Persist a cumulative, machine-readable ledger before announcing that an
    /// interaction succeeded. Replacing the same phase/attempt makes retries
    /// idempotent without losing earlier successful turns.
    async fn persist_session_record(&mut self, record: SessionLedgerRecord) -> Result<(), String> {
        let artifacts = self.artifacts.clone().ok_or_else(|| {
            format!(
                "{} Run artifact store is unavailable; session metrics cannot be persisted",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })?;
        let mut ledger = self.session_ledger.clone();
        if let Some(existing) = ledger
            .iter_mut()
            .find(|existing| existing.attempt == record.attempt && existing.phase == record.phase)
        {
            *existing = record.clone();
        } else {
            ledger.push(record.clone());
        }
        ledger.sort_by_key(|entry| entry.attempt);
        artifacts
            .save_attempt_file(record.attempt, "session-ledger.json", &ledger)
            .await
            .map_err(|error| {
                format!(
                    "{} Failed to persist structured session metrics for attempt {}: {error}",
                    INFRASTRUCTURE_FAILURE_PREFIX, record.attempt
                )
            })?;
        self.session_ledger = ledger;
        Ok(())
    }

    /// Process a side effect. This is the main dispatch loop.
    pub async fn handle(&mut self, effect: SideEffect) {
        // The local and remote orchestration futures are intentionally large.
        // Keep the dispatch frame heap-backed so callers with ordinary Tokio
        // worker stacks do not reserve the maximum branch size on every turn.
        self.handle_boxed(effect).await;
    }

    fn handle_boxed(
        &mut self,
        effect: SideEffect,
    ) -> std::pin::Pin<Box<dyn std::future::Future<Output = ()> + Send + '_>> {
        Box::pin(async move {
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
        })
    }

    /// Run follow-up task on the existing workspace
    async fn run_followup(&mut self, task: String, attempt: u8) {
        if let Some(execution) = self.remote_execution.clone() {
            self.run_followup_remote(execution, task, attempt).await;
            return;
        }
        let acceptance_spec = self.current_followup_acceptance_spec();
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
        let Some(sqlite_isolation) = self.sqlite_isolation.as_ref() else {
            let result = infrastructure_validation_failure(
                "Local follow-up runtime isolation is unavailable",
                format!(
                    "{} A local execution path was selected without its local SQLite isolation; refusing to continue.",
                    INFRASTRUCTURE_FAILURE_PREFIX
                ),
                0.0,
            );
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        };
        let command_environment =
            match declared_followup_command_environment(acceptance_spec.as_ref(), sqlite_isolation)
            {
                Ok(environment) => environment,
                Err(error) => {
                    let result = infrastructure_validation_failure(
                        "Required follow-up environment is unavailable",
                        error,
                        0.0,
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            };
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
            let loop_result = crate::agent_loop::run_agent_loop_with_environment(
                &self.client,
                &build_dir,
                &system_prompt,
                &next_prompt,
                ITERATIONS_PER_ROUND,
                Some(self.event_tx.clone()),
                &command_environment,
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
                crate::agent_loop::AgentLoopResult::Failed {
                    error,
                    iterations,
                    total_tool_calls: tool_calls,
                } => {
                    total_iterations += iterations;
                    total_tool_calls += tool_calls;
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
                    &command_environment,
                    self.artifacts.as_ref(),
                    attempt,
                    verification_round,
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
                    let total_elapsed = start.elapsed().as_secs_f64();
                    let record = SessionLedgerRecord {
                        phase: SessionRecordPhase::FollowUp,
                        attempt,
                        instruction: bounded_text(&task, 4_000),
                        summary: bounded_text(&summary, 2_000),
                        verification_rounds: verification_round,
                        model_iterations: total_iterations,
                        tool_calls: total_tool_calls,
                        elapsed_secs: total_elapsed,
                    };
                    if let Err(error) = self.persist_session_record(record.clone()).await {
                        let result = infrastructure_validation_failure(
                            "Successful follow-up metrics could not be persisted",
                            error,
                            total_elapsed,
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    self.followup_history.push(record);
                    self.settle_followup_acceptance_spec(true);
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

    /// Continue coding inside the authoritative runner workspace. Once a
    /// remote session has been selected, this path never observes a local
    /// project directory or executes a local project command.
    async fn run_followup_remote(
        &mut self,
        execution: Arc<crate::execution::RemoteExecution>,
        task: String,
        attempt: u8,
    ) {
        let start = std::time::Instant::now();
        let acceptance_spec = self.current_followup_acceptance_spec();
        if let Some(spec) = acceptance_spec.as_ref()
            && let Err(error) = spec.validate()
        {
            let result = infrastructure_validation_failure(
                "Remote follow-up acceptance contract is invalid",
                format!("{INFRASTRUCTURE_FAILURE_PREFIX} {error}"),
                start.elapsed().as_secs_f64(),
            );
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }
        let command_environment = declared_remote_command_environment(acceptance_spec.iter());
        let build_dir = match require_authoritative_remote_workspace(
            self.remote_workspace_dir.as_deref(),
            self.workspace_dir.as_deref(),
        ) {
            Ok(workspace) => workspace.to_string(),
            Err(error) => {
                let result = infrastructure_validation_failure(
                    "Remote follow-up workspace is unavailable",
                    error,
                    start.elapsed().as_secs_f64(),
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        match crate::known_infrastructure::detect_generated_workspace_infrastructure_failure_remote(
            execution.as_ref(),
            &build_dir,
        )
        .await
        {
            Ok(Some(failure)) => {
                let result = validation::fail(
                    5,
                    "follow-up infrastructure",
                    vec![failure.actionable_error()],
                    failure.diagnostic(),
                    start.elapsed().as_secs_f64(),
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Ok(None) => {}
            Err(error) => {
                let result = infrastructure_validation_failure(
                    "Failed to inspect the remote follow-up workspace",
                    format!("{INFRASTRUCTURE_FAILURE_PREFIX} {error}"),
                    start.elapsed().as_secs_f64(),
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        }

        let workspace_before = match crate::followup_acceptance::snapshot_remote_workspace(
            execution.as_ref(),
            &build_dir,
        )
        .await
        {
            Ok(snapshot) => snapshot,
            Err(error) => {
                let result = infrastructure_validation_failure(
                    "Failed to snapshot the remote follow-up workspace",
                    error,
                    start.elapsed().as_secs_f64(),
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };

        let system_template = std::fs::read_to_string("prompts/agentic-followup.txt")
            .unwrap_or_else(|_| include_str!("../../../prompts/agentic-followup.txt").to_string());
        let system_prompt =
            render_agentic_system_prompt_remote(&system_template, &build_dir, &self.assist_context);
        let original_task = self
            .task
            .as_ref()
            .map(|task| task.task_content.as_str())
            .unwrap_or("");
        let user_prompt = build_followup_prompt_remote(
            &build_dir,
            &task,
            original_task,
            &self.followup_history,
            acceptance_spec.as_ref(),
        );

        info!(remote_cwd = %build_dir, "Launching remote agentic follow-up loop");
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
            let loop_result = crate::agent_loop::run_agent_loop_remote_with_environment(
                &self.client,
                execution.clone(),
                &build_dir,
                &system_prompt,
                &next_prompt,
                ITERATIONS_PER_ROUND,
                Some(self.event_tx.clone()),
                &command_environment.tool_environment,
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
                crate::agent_loop::AgentLoopResult::Failed {
                    error,
                    iterations,
                    total_tool_calls: tool_calls,
                } => {
                    total_iterations += iterations;
                    total_tool_calls += tool_calls;
                    if is_infrastructure_diagnostic(&error) {
                        let mut result = infrastructure_validation_failure(
                            "Remote follow-up agent stopped on an infrastructure failure",
                            error.clone(),
                            start.elapsed().as_secs_f64(),
                        );
                        result.diagnostic = format!(
                            "Remote agentic follow-up stopped after {total_iterations} iteration(s): {error}"
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    if acceptance_spec.is_none() {
                        let result = validation::fail(
                            5,
                            "follow-up acceptance",
                            vec!["Unverified remote follow-up agent loop failed".to_string()],
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
                            vec!["Remote agent reached its iteration limit without an explicit, verifiable acceptance contract".to_string()],
                            "The model did not yield completion. Provide a typed follow-up acceptance contract; compilation alone is not accepted at the iteration limit.".to_string(),
                            start.elapsed().as_secs_f64(),
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    last_summary = format!(
                        "Round {verification_round} reached its iteration limit; deterministic remote verification was still required. Last failed build: {}",
                        last_failed_build
                            .as_deref()
                            .unwrap_or("no compiler diagnostic captured")
                    );
                    (false, None)
                }
            };

            let verification = match crate::followup_acceptance::snapshot_remote_workspace(
                execution.as_ref(),
                &build_dir,
            )
            .await
            {
                Err(error) => Err(error),
                Ok(after) if requires_workspace_change && after == workspace_before => Err(
                    "The follow-up has not changed any application-owned file required by the acceptance contract."
                        .to_string(),
                ),
                Ok(_) => {
                    verify_followup_outcome_remote(
                        &execution,
                        &build_dir,
                        &workspace_before,
                        acceptance_spec.as_ref(),
                        verification_target,
                        &command_environment,
                        self.artifacts.as_ref(),
                        attempt,
                        verification_round,
                    )
                    .await
                }
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
                    let total_elapsed = start.elapsed().as_secs_f64();
                    let record = SessionLedgerRecord {
                        phase: SessionRecordPhase::FollowUp,
                        attempt,
                        instruction: bounded_text(&task, 4_000),
                        summary: bounded_text(&summary, 2_000),
                        verification_rounds: verification_round,
                        model_iterations: total_iterations,
                        tool_calls: total_tool_calls,
                        elapsed_secs: total_elapsed,
                    };
                    if let Err(error) = self.persist_session_record(record.clone()).await {
                        let result = infrastructure_validation_failure(
                            "Successful remote follow-up metrics could not be persisted",
                            error,
                            total_elapsed,
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    self.followup_history.push(record);
                    self.settle_followup_acceptance_spec(true);
                    let mut result = validation::pass(5, "build", total_elapsed);
                    result.diagnostic = format!(
                        "Remote follow-up: ✓ ({verification_round} verification round(s), {total_iterations} model iteration(s), {total_tool_calls} tool call(s))\n\nAgent summary: {summary}\n\n{acceptance_diagnostic}"
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                Err(diagnostic) if is_infrastructure_diagnostic(&diagnostic) => {
                    let result = infrastructure_validation_failure(
                        "Deterministic remote follow-up verification hit an infrastructure failure",
                        diagnostic,
                        start.elapsed().as_secs_f64(),
                    );
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                Err(diagnostic) if verification_round < MAX_VERIFICATION_ROUNDS => {
                    next_prompt = format!(
                        "{user_prompt}\n\n# Independent Remote Acceptance Feedback (round {verification_round})\nThe previous attempt did not pass. Fix every failed check below, rerun the relevant commands, and only then yield. Do not regenerate or modify the model or validation evidence.\n\n{}\n\nPrevious agent summary:\n{}",
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
                            "Deterministic remote follow-up acceptance failed after {MAX_VERIFICATION_ROUNDS} verification rounds"
                        )],
                        diagnostic,
                        total_elapsed,
                    );
                    if let Some(round_error) = round_diagnostic {
                        result.diagnostic = format!(
                            "Last remote agent-loop error: {round_error}\n\n{}",
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

        if self.remote_execution.is_some() {
            for spec in &self.followup_acceptance_specs {
                if let Err(error) = spec.validate() {
                    self.send(RunEvent::PreflightFailed(format!(
                        "{} Invalid remote follow-up environment contract: {error}",
                        INFRASTRUCTURE_FAILURE_PREFIX
                    )))
                    .await;
                    return;
                }
            }
        }

        // Local mode resolves legacy `env_ref` values from the parent process.
        // Remote mode resolves session-declared references inside the runner;
        // it must never require or read those values on the control host.
        if self.remote_execution.is_none() {
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
            let version_result = if let Some(execution) = &self.remote_execution {
                verify_cargo_teaql_version_remote(execution).await
            } else {
                verify_cargo_teaql_version().await
            };
            if let Err(error) = version_result {
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
        let result = if let Some(execution) = &self.remote_execution {
            match upload_remote_model_files(
                execution,
                attempt,
                &self.candidate_files,
                self.candidate.as_deref(),
            )
            .await
            {
                Ok(model_dir) => {
                    info!(attempt, path = %model_dir, "Running remote domain validation");
                    run_domain_validation_remote(execution, &model_dir).await
                }
                Err(error) => infrastructure_validation_failure(
                    "Failed to prepare remote domain validation input",
                    format!("{} {error}", INFRASTRUCTURE_FAILURE_PREFIX),
                    0.0,
                ),
            }
        } else if let Some(artifacts) = &self.artifacts {
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

        if let Some(execution) = self.remote_execution.clone() {
            self.build_validate_remote(execution, attempt, build_target)
                .await;
            return;
        }

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
                "### Assist: query/{entity}\nFull response: `{assist_relative}`\n\n{stdout}\n\n",
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
            self.assist_context = bounded_text(&assist_outputs, 40_000);
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
                    let total_elapsed = start.elapsed().as_secs_f64();
                    let ledger_record = SessionLedgerRecord {
                        phase: SessionRecordPhase::InitialBuild,
                        attempt,
                        instruction: "Initial application build".to_string(),
                        summary: bounded_text(summary, 2_000),
                        verification_rounds: 1,
                        model_iterations: *iterations,
                        tool_calls: *total_tool_calls,
                        elapsed_secs: total_elapsed,
                    };
                    if let Err(error) = self.persist_session_record(ledger_record).await {
                        let result = infrastructure_validation_failure(
                            "Successful initial-build metrics could not be persisted",
                            error,
                            total_elapsed,
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
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
                crate::agent_loop::AgentLoopResult::Failed {
                    error, iterations, ..
                } => {
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
        let mut r = match verify_generated_build(&build_dir, &build_target).await {
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
        if r.passed {
            let total_elapsed = start.elapsed().as_secs_f64();
            r.elapsed_secs = total_elapsed;
            let ledger_record = SessionLedgerRecord {
                phase: SessionRecordPhase::InitialBuild,
                attempt,
                instruction: "Initial application build".to_string(),
                summary: "No agent loop was needed; deterministic lib-only verification passed."
                    .to_string(),
                verification_rounds: 1,
                model_iterations: 0,
                tool_calls: 0,
                elapsed_secs: total_elapsed,
            };
            if let Err(error) = self.persist_session_record(ledger_record).await {
                let result = infrastructure_validation_failure(
                    "Successful initial-build metrics could not be persisted",
                    error,
                    total_elapsed,
                );
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        }
        if let Some(artifacts) = &self.artifacts {
            artifacts
                .save_attempt_file(attempt, "build-validation.json", &r)
                .await
                .ok();
        }
        self.send(RunEvent::ValidationCompleted(r)).await;
    }

    /// Initial build pipeline whose project state is authoritative only in the
    /// attached runner session. No operation in this method probes or executes
    /// against a local project workspace.
    async fn build_validate_remote(
        &mut self,
        execution: Arc<crate::execution::RemoteExecution>,
        attempt: u8,
        build_target: String,
    ) {
        let started = std::time::Instant::now();
        let attempt_root = remote_attempt_root(attempt);
        let remote_command_environment =
            declared_remote_command_environment(self.followup_acceptance_specs.iter());
        if let Err(error) = upload_remote_model_files(
            &execution,
            attempt,
            &self.candidate_files,
            self.candidate.as_deref(),
        )
        .await
        {
            self.send_remote_infrastructure_failure(
                attempt,
                "Failed to upload the validated model to the SSH workspace",
                error,
                started.elapsed().as_secs_f64(),
            )
            .await;
            return;
        }

        info!(attempt, target = %build_target, cwd = %attempt_root, "Running remote code generation");
        let generation = match remote_exec(
            &execution,
            "cargo",
            vec![
                "teaql".into(),
                "--input".into(),
                "model".into(),
                build_target.clone(),
            ],
            &attempt_root,
            BTreeMap::new(),
            Duration::from_secs(300),
            512 * 1024,
        )
        .await
        {
            Ok(result) => result,
            Err(error) => {
                self.send_remote_infrastructure_failure(
                    attempt,
                    "Failed to execute remote cargo teaql generation",
                    error,
                    started.elapsed().as_secs_f64(),
                )
                .await;
                return;
            }
        };
        if generation.exit_code != Some(0) {
            let diagnostic = remote_command_diagnostic(&generation);
            let result = generation_validation_failure(
                format!("cargo teaql {build_target} failed on the SSH runner"),
                diagnostic,
                started.elapsed().as_secs_f64(),
            );
            self.persist_build_result(attempt, &result).await;
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }

        self.send(RunEvent::WorkspaceGenerationStarted).await;
        let app_target = if build_target.contains("-lib-core") {
            let app = if build_target.starts_with("java") {
                build_target.replace("-lib-core", "-web-spring-boot")
            } else {
                build_target.replace("-lib-core", "-app-console")
            };
            let app_generation = match remote_exec(
                &execution,
                "cargo",
                vec![
                    "teaql".into(),
                    "--input".into(),
                    "model".into(),
                    app.clone(),
                ],
                &attempt_root,
                BTreeMap::new(),
                Duration::from_secs(300),
                512 * 1024,
            )
            .await
            {
                Ok(result) => result,
                Err(error) => {
                    self.send_remote_infrastructure_failure(
                        attempt,
                        format!("Failed to execute remote cargo teaql {app}"),
                        error,
                        started.elapsed().as_secs_f64(),
                    )
                    .await;
                    return;
                }
            };
            if app_generation.exit_code != Some(0) {
                let result = generation_validation_failure(
                    format!("cargo teaql {app} failed on the SSH runner"),
                    remote_command_diagnostic(&app_generation),
                    started.elapsed().as_secs_f64(),
                );
                self.persist_build_result(attempt, &result).await;
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Some(app)
        } else {
            None
        };

        let build_dir = remote_join(&attempt_root, "build");
        self.remote_workspace_dir = Some(build_dir.clone());
        if let Err(error) = prepare_remote_workspace_context(
            &execution,
            &build_dir,
            &self.candidate_files,
            self.candidate.as_deref(),
        )
        .await
        {
            self.send_remote_infrastructure_failure(
                attempt,
                "Failed to prepare model and agent context in the remote workspace",
                error,
                started.elapsed().as_secs_f64(),
            )
            .await;
            return;
        }
        if let Err(error) = self
            .write_remote_workspace_validation_evidence(&execution, &build_dir)
            .await
        {
            self.send_remote_infrastructure_failure(
                attempt,
                "Failed to write deterministic evidence to the remote workspace",
                error,
                started.elapsed().as_secs_f64(),
            )
            .await;
            return;
        }
        if let Err(error) =
            apply_remote_manifest_patches(&execution, &build_dir, self.patches.as_ref()).await
        {
            self.send_remote_infrastructure_failure(
                attempt,
                "Failed to patch generated remote manifests",
                error,
                started.elapsed().as_secs_f64(),
            )
            .await;
            return;
        }
        if app_target.is_some()
            && let Err(error) =
                normalize_remote_app_manifest(&execution, &build_dir, &build_target).await
        {
            self.send_remote_infrastructure_failure(
                attempt,
                "Failed to normalize the remote application manifest",
                error,
                started.elapsed().as_secs_f64(),
            )
            .await;
            return;
        }

        match crate::known_infrastructure::detect_generated_workspace_infrastructure_failure_remote(
            &execution, &build_dir,
        )
        .await
        {
            Ok(Some(failure)) => {
                let result = validation::fail(
                    5,
                    "build infrastructure",
                    vec![failure.actionable_error()],
                    failure.diagnostic(),
                    started.elapsed().as_secs_f64(),
                );
                self.persist_build_result(attempt, &result).await;
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            Ok(None) => {}
            Err(error) => {
                self.send_remote_infrastructure_failure(
                    attempt,
                    "Failed to inspect the remote workspace for known incompatibilities",
                    error.to_string(),
                    started.elapsed().as_secs_f64(),
                )
                .await;
                return;
            }
        }

        let agents_path = remote_join(&build_dir, "AGENTS.md");
        let agents_md = match execution.read_text(agents_path).await {
            Ok(content) => content,
            Err(error) => {
                self.send_remote_infrastructure_failure(
                    attempt,
                    "Generated remote AGENTS.md is unavailable",
                    error.to_string(),
                    started.elapsed().as_secs_f64(),
                )
                .await;
                return;
            }
        };
        let entity_names = parse_entity_names_from_agents_md(&agents_md);
        const MAX_ASSIST_ENTITIES: usize = 8;
        let assist_entities: Vec<&String> = if entity_names.len() > MAX_ASSIST_ENTITIES {
            let step = entity_names.len() as f64 / MAX_ASSIST_ENTITIES as f64;
            (0..MAX_ASSIST_ENTITIES)
                .map(|index| &entity_names[(index as f64 * step) as usize])
                .collect()
        } else {
            entity_names.iter().collect()
        };

        let agents_context = remote_agents_context(&agents_md);
        let mut assist_outputs = format!(
            "{agents_context}Complete TeaQL assist responses are saved under `.klintcode/assist/`. Read the relevant file before writing TeaQL business code. The canonical model path is `model/main.xml`.\n\n"
        );
        let assist_target_base = build_target.replace("-lib-core", "-assist-query");
        for entity in assist_entities {
            let assist_target = format!("{assist_target_base}/{entity}");
            let output = match remote_exec(
                &execution,
                "cargo",
                vec![
                    "teaql".into(),
                    "--input".into(),
                    "model".into(),
                    assist_target,
                ],
                &attempt_root,
                BTreeMap::new(),
                Duration::from_secs(120),
                256 * 1024,
            )
            .await
            {
                Ok(output) => output,
                Err(error) => {
                    self.send_remote_infrastructure_failure(
                        attempt,
                        format!("Failed to execute remote TeaQL assist for `{entity}`"),
                        error,
                        started.elapsed().as_secs_f64(),
                    )
                    .await;
                    return;
                }
            };
            if output.exit_code != Some(0) {
                let result = generation_validation_failure(
                    format!("Remote TeaQL assist failed for `{entity}`"),
                    remote_command_diagnostic(&output),
                    started.elapsed().as_secs_f64(),
                );
                self.persist_build_result(attempt, &result).await;
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
            let assist_relative = format!(".klintcode/assist/query-{entity}.md");
            if let Err(error) = execution
                .write_text(
                    remote_join(&build_dir, &assist_relative),
                    output.stdout.clone(),
                    true,
                )
                .await
            {
                self.send_remote_infrastructure_failure(
                    attempt,
                    format!("Failed to store remote assist output for `{entity}`"),
                    error.to_string(),
                    started.elapsed().as_secs_f64(),
                )
                .await;
                return;
            }
            assist_outputs.push_str(&format!(
                "### Assist: query/{entity}\nFull response: `{assist_relative}`\n\n{}\n\n",
                output.stdout
            ));
        }
        if entity_names.len() > MAX_ASSIST_ENTITIES {
            assist_outputs.push_str(&format!(
                "### All entity names ({})\n{}\n\n",
                entity_names.len(),
                entity_names.join(", ")
            ));
        }
        self.assist_context = bounded_text(&assist_outputs, 40_000);
        if let Some(artifacts) = &self.artifacts {
            let _ = artifacts
                .save_attempt_raw(attempt, "assist-output.md", &assist_outputs)
                .await;
        }

        if !entity_names.is_empty() {
            let system_template = std::fs::read_to_string("prompts/agentic-build.txt")
                .unwrap_or_else(|_| include_str!("../../../prompts/agentic-build.txt").to_string());
            let system_prompt =
                render_agentic_system_prompt_remote(&system_template, &build_dir, &assist_outputs);
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
                "You are already operating at the remote project workspace root `{build_dir}`. Do not use an absolute host path. {compile_hint}\n\n# Original User Task\n{original_task}\n\n# Model Acceptance Context\n{model_acceptance}\n\nCompile first. Fix only application code or workspace configuration. Never read, search, or modify generated library source such as lib/src. Complete every requested API example, test, review, report, and runtime check before yielding."
            );
            let loop_result = crate::agent_loop::run_agent_loop_remote_with_environment(
                &self.client,
                Arc::clone(&execution),
                &build_dir,
                &system_prompt,
                &user_prompt,
                20,
                Some(self.event_tx.clone()),
                &remote_command_environment.tool_environment,
            )
            .await;

            match loop_result {
                crate::agent_loop::AgentLoopResult::Completed {
                    summary,
                    iterations,
                    total_tool_calls,
                } => {
                    let build_diagnostic =
                        match verify_generated_build_remote(&execution, &build_dir, &build_target)
                            .await
                        {
                            Ok(diagnostic) => diagnostic,
                            Err(error) => {
                                let result = remote_verification_failure(
                                    "Deterministic remote build verification failed",
                                    error,
                                    started.elapsed().as_secs_f64(),
                                );
                                self.persist_build_result(attempt, &result).await;
                                self.send(RunEvent::ValidationCompleted(result)).await;
                                return;
                            }
                        };
                    let (test_diagnostic, observed_tests) = match verify_generated_tests_remote(
                        &execution,
                        &build_dir,
                        &build_target,
                        &remote_command_environment.cargo_test_refs,
                    )
                    .await
                    {
                        Ok(result) => result,
                        Err(error) => {
                            let result = remote_verification_failure(
                                "Deterministic remote test verification failed",
                                error,
                                started.elapsed().as_secs_f64(),
                            );
                            self.persist_build_result(attempt, &result).await;
                            self.send(RunEvent::ValidationCompleted(result)).await;
                            return;
                        }
                    };
                    let elapsed = started.elapsed().as_secs_f64();
                    let ledger = SessionLedgerRecord {
                        phase: SessionRecordPhase::InitialBuild,
                        attempt,
                        instruction: "Initial remote application build".to_string(),
                        summary: bounded_text(&summary, 2_000),
                        verification_rounds: 1,
                        model_iterations: iterations,
                        tool_calls: total_tool_calls,
                        elapsed_secs: elapsed,
                    };
                    if let Err(error) = self.persist_session_record(ledger).await {
                        let result = infrastructure_validation_failure(
                            "Successful remote build metrics could not be persisted",
                            error,
                            elapsed,
                        );
                        self.send(RunEvent::ValidationCompleted(result)).await;
                        return;
                    }
                    let mut result = validation::pass(5, "build", elapsed);
                    result.diagnostic = format!(
                        "Remote agentic build: ✓ ({iterations} iterations, {total_tool_calls} tool calls; {observed_tests} tests observed)\n\nAgent summary: {summary}\n\n{build_diagnostic}\n\n{test_diagnostic}"
                    );
                    self.persist_build_result(attempt, &result).await;
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                crate::agent_loop::AgentLoopResult::Failed {
                    error, iterations, ..
                } => {
                    let result = if is_infrastructure_diagnostic(&error) {
                        infrastructure_validation_failure(
                            "Remote agentic build stopped on an infrastructure failure",
                            error,
                            started.elapsed().as_secs_f64(),
                        )
                    } else {
                        validation::fail(
                            5,
                            "build",
                            vec![format!(
                                "Remote agent loop failed after {iterations} iterations"
                            )],
                            error,
                            started.elapsed().as_secs_f64(),
                        )
                    };
                    self.persist_build_result(attempt, &result).await;
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
                crate::agent_loop::AgentLoopResult::MaxIterationsReached {
                    iterations,
                    total_tool_calls,
                    last_failed_build,
                } => {
                    let result = validation::fail(
                        5,
                        "build",
                        vec![format!(
                            "Remote agent loop exhausted {iterations} iterations"
                        )],
                        format!(
                            "Remote agentic build did not complete ({total_tool_calls} tool calls). Last failed build:\n{}",
                            last_failed_build.as_deref().unwrap_or("none captured")
                        ),
                        started.elapsed().as_secs_f64(),
                    );
                    self.persist_build_result(attempt, &result).await;
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            }
        }

        let build_diagnostic =
            match verify_generated_build_remote(&execution, &build_dir, &build_target).await {
                Ok(diagnostic) => diagnostic,
                Err(error) => {
                    let result = remote_verification_failure(
                        "Generated remote project failed build verification",
                        error,
                        started.elapsed().as_secs_f64(),
                    );
                    self.persist_build_result(attempt, &result).await;
                    self.send(RunEvent::ValidationCompleted(result)).await;
                    return;
                }
            };
        let (test_diagnostic, observed_tests) = match verify_generated_tests_remote(
            &execution,
            &build_dir,
            &build_target,
            &remote_command_environment.cargo_test_refs,
        )
        .await
        {
            Ok(result) => result,
            Err(error) => {
                let result = remote_verification_failure(
                    "Generated remote project failed test verification",
                    error,
                    started.elapsed().as_secs_f64(),
                );
                self.persist_build_result(attempt, &result).await;
                self.send(RunEvent::ValidationCompleted(result)).await;
                return;
            }
        };
        let elapsed = started.elapsed().as_secs_f64();
        let ledger = SessionLedgerRecord {
            phase: SessionRecordPhase::InitialBuild,
            attempt,
            instruction: "Initial remote application build".to_string(),
            summary:
                "No agent loop was needed; deterministic remote build and test verification passed."
                    .to_string(),
            verification_rounds: 1,
            model_iterations: 0,
            tool_calls: 0,
            elapsed_secs: elapsed,
        };
        if let Err(error) = self.persist_session_record(ledger).await {
            let result = infrastructure_validation_failure(
                "Successful remote build metrics could not be persisted",
                error,
                elapsed,
            );
            self.persist_build_result(attempt, &result).await;
            self.send(RunEvent::ValidationCompleted(result)).await;
            return;
        }
        let mut result = validation::pass(5, "build", elapsed);
        result.diagnostic = format!(
            "Remote lib-only deterministic verification passed ({observed_tests} tests observed).\n\n{build_diagnostic}\n\n{test_diagnostic}"
        );
        self.persist_build_result(attempt, &result).await;
        self.send(RunEvent::ValidationCompleted(result)).await;
    }

    async fn write_remote_workspace_validation_evidence(
        &self,
        execution: &crate::execution::RemoteExecution,
        workspace: &str,
    ) -> Result<(), String> {
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
        let content = serde_json::to_string_pretty(&evidence)
            .map_err(|error| format!("Failed to serialize validation evidence: {error}"))?;
        execution
            .write_text(
                remote_join(workspace, ".klintcode/validation-evidence.json"),
                content,
                true,
            )
            .await
            .map_err(|error| error.to_string())?;
        Ok(())
    }

    async fn send_remote_infrastructure_failure(
        &mut self,
        attempt: u8,
        summary: impl Into<String>,
        diagnostic: impl Into<String>,
        elapsed: f64,
    ) {
        let result = infrastructure_validation_failure(summary, diagnostic.into(), elapsed);
        self.persist_build_result(attempt, &result).await;
        self.send(RunEvent::ValidationCompleted(result)).await;
    }

    async fn persist_build_result(&self, attempt: u8, result: &ValidationResult) {
        if let Some(artifacts) = &self.artifacts {
            let _ = artifacts
                .save_attempt_file(attempt, "build-validation.json", result)
                .await;
        }
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
        if let Some(execution) = self.remote_execution.clone() {
            let Some(artifacts) = self.artifacts.clone() else {
                self.send(RunEvent::Failed(AgentError::InfrastructureError {
                    detail: format!(
                        "{} Run artifact store is unavailable; the remote workspace locator cannot be persisted",
                        INFRASTRUCTURE_FAILURE_PREFIX
                    ),
                }))
                .await;
                return;
            };
            let workspace = if self.build_target.is_none() && self.candidate.is_some() {
                // A valid model-only task never creates an application cwd.
                // The durable runner session root still owns the uploaded
                // attempt/model files and is therefore the correct locator.
                ".".to_string()
            } else {
                match require_authoritative_remote_workspace(
                    self.remote_workspace_dir.as_deref(),
                    self.workspace_dir.as_deref(),
                ) {
                    Ok(workspace) => workspace.to_string(),
                    Err(error) => {
                        self.send(RunEvent::Failed(AgentError::InfrastructureError {
                            detail: error,
                        }))
                        .await;
                        return;
                    }
                }
            };
            let session_id = execution.session_id().await;
            let manifest = serde_json::json!({
                "schema": "klintcode-remote-workspace-v1",
                "authority": "tool-runner-session",
                "session_id": session_id,
                "workspace": workspace,
                "build_target": self.build_target,
                "model_files": self.candidate_files.iter().map(|(name, _)| name).collect::<Vec<_>>(),
                "verified_interactions": self.session_ledger.len(),
                "exported": false,
                "note": "The verified workspace remains authoritative in the durable runner session; this control-plane artifact is a locator manifest, not a local project copy."
            });
            let content = match serde_json::to_string_pretty(&manifest) {
                Ok(content) => content,
                Err(error) => {
                    self.send(RunEvent::Failed(AgentError::InfrastructureError {
                        detail: format!(
                            "{} Failed to serialize the remote workspace manifest: {error}",
                            INFRASTRUCTURE_FAILURE_PREFIX
                        ),
                    }))
                    .await;
                    return;
                }
            };
            match artifacts.save_final_artifact(&content).await {
                Ok(path) => {
                    info!(path = %path.display(), "Remote workspace locator manifest saved");
                    self.send(RunEvent::FinalArtifactWritten(path)).await;
                }
                Err(error) => {
                    self.send(RunEvent::Failed(AgentError::InfrastructureError {
                        detail: format!(
                            "{} Failed to persist the remote workspace manifest: {error}",
                            INFRASTRUCTURE_FAILURE_PREFIX
                        ),
                    }))
                    .await;
                }
            }
            return;
        }
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

    /// Return the logical cwd inside the authoritative SSH runner session.
    pub fn remote_workspace_dir(&self) -> Option<&str> {
        self.remote_workspace_dir.as_deref()
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

fn remote_attempt_root(attempt: u8) -> String {
    format!("attempt-{attempt:02}")
}

/// Resolve the authoritative project cwd after SSH mode has been selected.
/// The legacy path is accepted only to make the fail-stop invariant explicit:
/// it is never returned as a fallback.
fn require_authoritative_remote_workspace<'a>(
    remote_workspace: Option<&'a str>,
    legacy_workspace: Option<&Path>,
) -> Result<&'a str, String> {
    let Some(workspace) = remote_workspace else {
        return Err(format!(
            "{} The runner session has no generated application cwd{}; local fallback is forbidden.",
            INFRASTRUCTURE_FAILURE_PREFIX,
            if legacy_workspace.is_some() {
                " even though a legacy local workspace is present"
            } else {
                ""
            }
        ));
    };
    let path = Path::new(workspace);
    if workspace.is_empty()
        || workspace.contains('\\')
        || path.is_absolute()
        || path.components().any(|component| {
            !matches!(
                component,
                std::path::Component::Normal(_) | std::path::Component::CurDir
            )
        })
    {
        return Err(format!(
            "{} Remote application cwd must remain runner-relative: `{workspace}`",
            INFRASTRUCTURE_FAILURE_PREFIX
        ));
    }
    Ok(workspace)
}

fn remote_join(root: &str, relative: &str) -> String {
    let root = root.trim_end_matches('/');
    let relative = relative.trim_start_matches('/');
    if root.is_empty() || root == "." {
        relative.to_string()
    } else if relative.is_empty() || relative == "." {
        root.to_string()
    } else {
        format!("{root}/{relative}")
    }
}

fn remote_parent(path: &str) -> &str {
    path.rsplit_once('/').map_or(".", |(parent, _)| parent)
}

fn normalized_candidate_model_files(
    candidate_files: &[(String, String)],
    candidate: Option<&str>,
) -> Result<Vec<(String, String)>, String> {
    let files = if candidate_files.is_empty() {
        vec![(
            "main.xml".to_string(),
            candidate
                .ok_or_else(|| "No candidate model is available".to_string())?
                .to_string(),
        )]
    } else {
        candidate_files.to_vec()
    };
    for (name, _) in &files {
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
    }
    Ok(files)
}

async fn upload_remote_model_files(
    execution: &crate::execution::RemoteExecution,
    attempt: u8,
    candidate_files: &[(String, String)],
    candidate: Option<&str>,
) -> Result<String, String> {
    let model_dir = remote_join(&remote_attempt_root(attempt), "model");
    for (name, content) in normalized_candidate_model_files(candidate_files, candidate)? {
        execution
            .write_text(
                remote_join(&model_dir, &name),
                strip_markdown_fences(&content),
                true,
            )
            .await
            .map_err(|error| format!("Remote model upload failed for `{name}`: {error}"))?;
    }
    Ok(model_dir)
}

async fn remote_exec(
    execution: &crate::execution::RemoteExecution,
    program: &str,
    argv: Vec<String>,
    cwd: &str,
    env: BTreeMap<String, String>,
    timeout: Duration,
    max_output_bytes: u64,
) -> Result<ExecResult, String> {
    execution
        .exec(
            program.to_string(),
            argv,
            cwd.to_string(),
            env,
            timeout,
            max_output_bytes,
        )
        .await
        .map_err(|error| {
            format!(
                "{} Remote runner operation failed: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })
}

async fn remote_exec_with_environment_refs(
    execution: &crate::execution::RemoteExecution,
    program: &str,
    argv: Vec<String>,
    cwd: &str,
    env_refs: Vec<String>,
    timeout: Duration,
    max_output_bytes: u64,
) -> Result<ExecResult, String> {
    execution
        .exec_with_environment_refs(
            program.to_string(),
            argv,
            cwd.to_string(),
            BTreeMap::new(),
            env_refs,
            timeout,
            max_output_bytes,
        )
        .await
        .map_err(|error| {
            format!(
                "{} Remote runner operation failed: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })
}

fn remote_command_diagnostic(output: &ExecResult) -> String {
    format!(
        "Exit code: {:?}; signal: {:?}; elapsed: {}ms\nSTDOUT:\n{}\nSTDERR:\n{}{}{}",
        output.exit_code,
        output.signal,
        output.elapsed_ms,
        output.stdout,
        output.stderr,
        if output.stdout_truncated {
            "\n[remote stdout truncated]"
        } else {
            ""
        },
        if output.stderr_truncated {
            "\n[remote stderr truncated]"
        } else {
            ""
        }
    )
}

async fn run_domain_validation_remote(
    execution: &crate::execution::RemoteExecution,
    model_dir: &str,
) -> ValidationResult {
    let started = std::time::Instant::now();
    let workspace = remote_parent(model_dir);
    let input = model_dir
        .rsplit_once('/')
        .map_or(model_dir, |(_, name)| name)
        .to_string();
    let output = match remote_exec(
        execution,
        "cargo",
        vec!["teaql".into(), "--input".into(), input, "evaluate".into()],
        workspace,
        BTreeMap::new(),
        Duration::from_secs(120),
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
                    "{} TeaQL domain validator is unavailable on the SSH runner",
                    INFRASTRUCTURE_FAILURE_PREFIX
                )],
                error,
                started.elapsed().as_secs_f64(),
            );
        }
    };
    let combined = format!("{}\n{}", output.stdout, output.stderr);
    if output.exit_code.is_none() {
        return validation::fail(
            3,
            "domain",
            vec![format!(
                "{} Remote TeaQL domain validator ended without an exit code",
                INFRASTRUCTURE_FAILURE_PREFIX
            )],
            remote_command_diagnostic(&output),
            started.elapsed().as_secs_f64(),
        );
    }
    if output.exit_code != Some(0) && is_infrastructure_diagnostic(&combined) {
        return validation::fail(
            3,
            "domain",
            vec![format!(
                "{} TeaQL domain validator is unavailable on the SSH runner",
                INFRASTRUCTURE_FAILURE_PREFIX
            )],
            combined,
            started.elapsed().as_secs_f64(),
        );
    }
    let mut result = validation::domain::parse_teaql_output(&combined);
    result.elapsed_secs = started.elapsed().as_secs_f64();
    if output.exit_code != Some(0) && result.error_count == 0 {
        result.passed = false;
        result.error_count = 1;
        result.actionable_errors.push(format!(
            "Remote TeaQL evaluate failed with exit code {:?}:\n{}",
            output.exit_code,
            bounded_text(combined.trim(), 12_000)
        ));
    }
    result
}

async fn prepare_remote_workspace_context(
    execution: &crate::execution::RemoteExecution,
    build_dir: &str,
    candidate_files: &[(String, String)],
    candidate: Option<&str>,
) -> Result<(), String> {
    for (name, content) in normalized_candidate_model_files(candidate_files, candidate)? {
        execution
            .write_text(
                remote_join(build_dir, &format!("model/{name}")),
                strip_markdown_fences(&content),
                true,
            )
            .await
            .map_err(|error| format!("Failed to copy model `{name}` into remote build: {error}"))?;
    }
    let agents_path = remote_join(build_dir, "AGENTS.md");
    let agents = execution
        .read_text(agents_path.clone())
        .await
        .map_err(|error| format!("Failed to read remote {agents_path}: {error}"))?;
    let mut normalized = canonicalize_teaql_input_paths(&agents);
    normalized.push_str(
        "\n\n## KlintCode Remote Workspace Context\n\n\
         All agent tools run at this remote workspace root. Use relative paths and never use host filesystem paths.\n\
         The validated model is `model/main.xml`. Every TeaQL command must use the exact form\n\
         `cargo teaql --input model/main.xml rust-assist-[action]/[entity-name]`.\n\
         Complete pre-fetched responses are stored under `.klintcode/assist/`.\n\
         Deterministic facts are stored in `.klintcode/validation-evidence.json`.\n\
         Never read, search, or modify generated library source under `lib/src`.\n",
    );
    execution
        .write_text(agents_path, normalized, false)
        .await
        .map_err(|error| format!("Failed to update remote AGENTS.md: {error}"))?;
    Ok(())
}

fn should_skip_remote_manifest_directory(name: &str) -> bool {
    matches!(name, "src" | "target" | ".git" | ".klintcode" | "model")
}

async fn remote_manifest_files(
    execution: &crate::execution::RemoteExecution,
    root: &str,
) -> Result<Vec<String>, String> {
    let mut directories = vec![root.to_string()];
    let mut manifests = Vec::new();
    while let Some(directory) = directories.pop() {
        let listing = execution
            .list(directory.clone(), None)
            .await
            .map_err(|error| format!("Failed to list remote directory `{directory}`: {error}"))?;
        if listing.truncated {
            return Err(format!(
                "Remote directory listing was truncated for `{directory}`"
            ));
        }
        for entry in listing.entries {
            let path = remote_join(&directory, &entry.name);
            match entry.kind {
                FileKind::Directory if !should_skip_remote_manifest_directory(&entry.name) => {
                    directories.push(path);
                }
                FileKind::File
                    if matches!(
                        entry.name.as_str(),
                        "Cargo.toml" | "pom.xml" | "build.gradle"
                    ) =>
                {
                    manifests.push(path);
                }
                FileKind::Symlink => {
                    return Err(format!(
                        "Remote manifest discovery encountered a symlink at `{path}`"
                    ));
                }
                _ => {}
            }
        }
    }
    manifests.sort();
    Ok(manifests)
}

async fn apply_remote_manifest_patches(
    execution: &crate::execution::RemoteExecution,
    build_dir: &str,
    patches: Option<&std::collections::HashMap<String, String>>,
) -> Result<(), String> {
    let Some(patches) = patches else {
        return Ok(());
    };
    for path in remote_manifest_files(execution, build_dir).await? {
        let content = execution
            .read_text(path.clone())
            .await
            .map_err(|error| format!("Failed to read remote manifest `{path}`: {error}"))?;
        let mut fixed = content.clone();
        for (find, replace) in patches {
            fixed = fixed.replace(find, replace);
        }
        if fixed != content {
            execution
                .write_text(path.clone(), fixed, false)
                .await
                .map_err(|error| format!("Failed to patch remote manifest `{path}`: {error}"))?;
        }
    }
    Ok(())
}

async fn normalize_remote_app_manifest(
    execution: &crate::execution::RemoteExecution,
    build_dir: &str,
    build_target: &str,
) -> Result<(), String> {
    let path = remote_join(build_dir, "Cargo.toml");
    let content = execution
        .read_text(path.clone())
        .await
        .map_err(|error| format!("Failed to read remote app manifest: {error}"))?;
    let old_path = format!(r#"path = "../{build_target}/lib""#);
    let fixed = ensure_standalone_cargo_workspace(&content.replace(&old_path, r#"path = "./lib""#));
    if fixed != content {
        execution
            .write_text(path, fixed, false)
            .await
            .map_err(|error| format!("Failed to write remote app manifest: {error}"))?;
    }
    Ok(())
}

fn remote_agents_context(agents_md: &str) -> String {
    if agents_md.is_empty() {
        return String::new();
    }
    let mut output = String::new();
    let mut in_discard = false;
    for line in agents_md.lines() {
        if line.trim().starts_with("<!-- DISCARD_BLOCK:") {
            in_discard = true;
        } else if line.trim() == "<!-- END_DISCARD_BLOCK -->" {
            in_discard = false;
            continue;
        }
        if !in_discard {
            output.push_str(line);
            output.push('\n');
        }
    }
    format!(
        "## Workspace Rules (AGENTS.md)\n{}\n\n",
        bounded_text(&output, 3_000)
    )
}

fn render_agentic_system_prompt_remote(template: &str, workspace: &str, assist: &str) -> String {
    let rendered = template
        .replace("{{project_dir}}", workspace)
        .replace("{{#if assist_outputs}}", "")
        .replace("{{/if}}", "")
        .replace("{{assist_outputs}}", assist);
    if let Some(position) = rendered.find("{{") {
        let remaining = &rendered[position..];
        let end = remaining
            .find("}}")
            .map(|index| index + 2)
            .unwrap_or(40)
            .min(remaining.len());
        panic!(
            "render_agentic_system_prompt_remote: un-substituted template variable '{}'",
            &remaining[..end]
        );
    }
    rendered
}

async fn verify_generated_build_remote(
    execution: &crate::execution::RemoteExecution,
    build_dir: &str,
    build_target: &str,
) -> Result<String, String> {
    let (program, argv) = if build_target.starts_with("java") {
        ("mvn", vec!["compile".into(), "-f".into(), "pom.xml".into()])
    } else {
        ("cargo", vec!["check".into()])
    };
    let output = remote_exec(
        execution,
        program,
        argv,
        build_dir,
        BTreeMap::new(),
        Duration::from_secs(300),
        512 * 1024,
    )
    .await?;
    let diagnostic = remote_command_diagnostic(&output);
    match output.exit_code {
        Some(0) => Ok(diagnostic),
        Some(_) => Err(diagnostic),
        None => Err(format!(
            "{} Remote build ended without an exit code.\n{diagnostic}",
            INFRASTRUCTURE_FAILURE_PREFIX
        )),
    }
}

async fn verify_generated_tests_remote(
    execution: &crate::execution::RemoteExecution,
    build_dir: &str,
    build_target: &str,
    environment_refs: &[String],
) -> Result<(String, usize), String> {
    let (program, argv) = if build_target.starts_with("java") {
        ("mvn", vec!["test".into(), "-f".into(), "pom.xml".into()])
    } else {
        (
            "cargo",
            vec!["test".into(), "--".into(), "--test-threads=1".into()],
        )
    };
    let output = remote_exec_with_environment_refs(
        execution,
        program,
        argv,
        build_dir,
        environment_refs.to_vec(),
        Duration::from_secs(300),
        512 * 1024,
    )
    .await?;
    let diagnostic = remote_command_diagnostic(&output);
    match output.exit_code {
        Some(0) => {
            let combined = format!("{}\n{}", output.stdout, output.stderr);
            Ok((diagnostic, observed_test_count(&combined)))
        }
        Some(_) => Err(diagnostic),
        None => Err(format!(
            "{} Remote tests ended without an exit code.\n{diagnostic}",
            INFRASTRUCTURE_FAILURE_PREFIX
        )),
    }
}

fn remote_verification_failure(
    summary: &str,
    diagnostic: String,
    elapsed: f64,
) -> ValidationResult {
    if is_infrastructure_diagnostic(&diagnostic) {
        infrastructure_validation_failure(summary, diagnostic, elapsed)
    } else {
        validation::fail(5, "build", vec![summary.to_string()], diagnostic, elapsed)
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
    let model_dir = match resolve_domain_model_dir(model_dir) {
        Ok(path) => path,
        Err(error) => {
            return infrastructure_validation_failure(
                "Failed to resolve TeaQL domain validation input",
                format!(
                    "{} Cannot canonicalize model input {}: {error}",
                    INFRASTRUCTURE_FAILURE_PREFIX,
                    model_dir.display()
                ),
                started.elapsed().as_secs_f64(),
            );
        }
    };
    let workspace = model_dir.parent().unwrap_or(&model_dir);
    let mut command = tokio::process::Command::new("cargo");
    crate::process_env::apply_safe_environment(&mut command, workspace);
    command
        .args(["teaql", "--input"])
        .arg(&model_dir)
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
        let output_detail = combined.trim();
        result.actionable_errors.push(if output_detail.is_empty() {
            format!(
                "TeaQL evaluate failed with exit code {:?} and produced no diagnostic output",
                output.status.code()
            )
        } else {
            format!(
                "TeaQL evaluate failed with exit code {:?}:\n{}",
                output.status.code(),
                bounded_text(output_detail, 12_000)
            )
        });
    }
    result
}

fn resolve_domain_model_dir(model_dir: &Path) -> std::io::Result<PathBuf> {
    std::fs::canonicalize(model_dir)
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
    command_environment: &crate::tools::DeclaredCommandEnvironment,
    artifacts: Option<&RunArtifacts>,
    attempt: u8,
    verification_round: usize,
) -> Result<String, String> {
    let build_diagnostic = verify_generated_build(workspace, build_target)
        .await
        .map_err(|diagnostic| {
            format!("Independent follow-up build verification failed:\n{diagnostic}")
        })?;
    let (test_diagnostic, test_count) =
        verify_generated_tests_with_environment(workspace, build_target, command_environment)
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

    let report = acceptance
        .verify_with_environment(workspace, before, command_environment)
        .await?;
    persist_followup_acceptance_report(workspace, artifacts, attempt, verification_round, &report)
        .await?;

    if report.passed {
        Ok(format!("{default_evidence}\n\n{}", report.diagnostic()))
    } else {
        Err(format!("{default_evidence}\n\n{}", report.diagnostic()))
    }
}

async fn verify_followup_outcome_remote(
    execution: &Arc<crate::execution::RemoteExecution>,
    workspace: &str,
    before: &crate::followup_acceptance::RemoteWorkspaceSnapshot,
    acceptance: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
    build_target: &str,
    command_environment: &RemoteDeclaredCommandEnvironment,
    artifacts: Option<&RunArtifacts>,
    attempt: u8,
    verification_round: usize,
) -> Result<String, String> {
    let build_diagnostic = verify_generated_build_remote(execution, workspace, build_target)
        .await
        .map_err(|diagnostic| {
            format!("Independent remote follow-up build verification failed:\n{diagnostic}")
        })?;
    let (test_diagnostic, test_count) = verify_generated_tests_remote(
        execution,
        workspace,
        build_target,
        &command_environment.cargo_test_refs,
    )
    .await
    .map_err(|diagnostic| {
        format!("Independent remote follow-up test verification failed:\n{diagnostic}")
    })?;

    let default_evidence = format!(
        "Independent remote build and test verification passed ({test_count} tests observed).\n\n{}\n\n{}",
        bounded_text(&build_diagnostic, 8_000),
        bounded_text(&test_diagnostic, 8_000)
    );
    let Some(acceptance) = acceptance else {
        return Ok(format!(
            "{default_evidence}\n\nNo explicit machine acceptance contract was supplied; this yielded result is verified only for remote workspace change, build, and test command success."
        ));
    };

    let report = acceptance
        .verify_remote_with_environment(execution, workspace, before, &command_environment.all_refs)
        .await?;
    persist_followup_acceptance_report_remote(
        execution,
        workspace,
        artifacts,
        attempt,
        verification_round,
        &report,
    )
    .await?;

    if report.passed {
        Ok(format!("{default_evidence}\n\n{}", report.diagnostic()))
    } else {
        Err(format!("{default_evidence}\n\n{}", report.diagnostic()))
    }
}

async fn persist_followup_acceptance_report_remote(
    execution: &crate::execution::RemoteExecution,
    workspace: &str,
    artifacts: Option<&RunArtifacts>,
    attempt: u8,
    verification_round: usize,
    report: &crate::followup_acceptance::FollowUpAcceptanceReport,
) -> Result<(), String> {
    let report_content = serde_json::to_string_pretty(report).map_err(|error| {
        format!(
            "{} Failed to serialize remote follow-up acceptance report: {error}",
            INFRASTRUCTURE_FAILURE_PREFIX
        )
    })?;
    execution
        .write_text(
            remote_join(workspace, ".klintcode/followup-acceptance-report.json"),
            report_content.clone(),
            true,
        )
        .await
        .map_err(|error| {
            format!(
                "{} Failed to persist acceptance evidence in the remote workspace: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })?;

    let artifacts = artifacts.ok_or_else(|| {
        format!(
            "{} Run artifact store is unavailable; remote acceptance evidence cannot be persisted",
            INFRASTRUCTURE_FAILURE_PREFIX
        )
    })?;
    let versioned_name = format!("followup-acceptance-report-round-{verification_round:02}.json");
    artifacts
        .save_attempt_raw(attempt, &versioned_name, &report_content)
        .await
        .map_err(|error| {
            format!(
                "{} Failed to persist remote acceptance evidence for attempt {attempt}, verification round {verification_round}: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })?;
    artifacts
        .save_attempt_raw(attempt, "followup-acceptance-report.json", &report_content)
        .await
        .map_err(|error| {
            format!(
                "{} Failed to refresh remote acceptance evidence for attempt {attempt}: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })?;
    Ok(())
}

async fn persist_followup_acceptance_report(
    workspace: &Path,
    artifacts: Option<&RunArtifacts>,
    attempt: u8,
    verification_round: usize,
    report: &crate::followup_acceptance::FollowUpAcceptanceReport,
) -> Result<(), String> {
    let report_path = workspace.join(".klintcode/followup-acceptance-report.json");
    if let Some(parent) = report_path.parent() {
        std::fs::create_dir_all(parent)
            .map_err(|error| format!("Failed to create follow-up evidence directory: {error}"))?;
    }
    let report_content = serde_json::to_string_pretty(report)
        .map_err(|error| format!("Failed to serialize follow-up acceptance report: {error}"))?;
    std::fs::write(&report_path, &report_content).map_err(|error| {
        format!(
            "Failed to write follow-up acceptance report {}: {error}",
            report_path.display()
        )
    })?;

    let artifacts = artifacts.ok_or_else(|| {
        format!(
            "{} Run artifact store is unavailable; acceptance evidence cannot be persisted",
            INFRASTRUCTURE_FAILURE_PREFIX
        )
    })?;
    let versioned_name = format!("followup-acceptance-report-round-{verification_round:02}.json");
    artifacts
        .save_attempt_raw(attempt, &versioned_name, &report_content)
        .await
        .map_err(|error| {
            format!(
                "{} Failed to persist acceptance evidence for attempt {attempt}, verification round {verification_round}: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })?;
    artifacts
        .save_attempt_raw(attempt, "followup-acceptance-report.json", &report_content)
        .await
        .map_err(|error| {
            format!(
                "{} Failed to refresh acceptance evidence for attempt {attempt}: {error}",
                INFRASTRUCTURE_FAILURE_PREFIX
            )
        })?;
    Ok(())
}

async fn verify_generated_tests(
    build_dir: &Path,
    build_target: &str,
) -> Result<(String, usize), String> {
    verify_generated_tests_with_environment(
        build_dir,
        build_target,
        &crate::tools::DeclaredCommandEnvironment::default(),
    )
    .await
}

async fn verify_generated_tests_with_environment(
    build_dir: &Path,
    build_target: &str,
    command_environment: &crate::tools::DeclaredCommandEnvironment,
) -> Result<(String, usize), String> {
    let mut command = if build_target.starts_with("java") {
        let mut command = tokio::process::Command::new("mvn");
        command.args(["test", "-f", "pom.xml"]);
        command
    } else {
        let mut command = tokio::process::Command::new("cargo");
        command.args(["test", "--", "--test-threads=1"]);
        command
    };
    crate::process_env::apply_safe_environment(&mut command, build_dir);
    if !build_target.starts_with("java") {
        command_environment.apply_to_cargo_test(&mut command);
    }
    command.current_dir(build_dir);
    let output = crate::process_output::run_bounded_output(
        &mut command,
        std::time::Duration::from_secs(300),
        512 * 1024,
    )
    .await
    .map_err(|error| format!("Deterministic test verification failed: {error}"))?;
    let diagnostic = command_environment.redact_cargo_test_output(&format!(
        "Exit status: {}\nSTDOUT:\n{}\nSTDERR:\n{}",
        output.status,
        String::from_utf8_lossy(&output.stdout),
        String::from_utf8_lossy(&output.stderr)
    ));
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

async fn verify_cargo_teaql_version_remote(
    execution: &crate::execution::RemoteExecution,
) -> Result<(), String> {
    let output = remote_exec(
        execution,
        "cargo",
        vec!["teaql".into(), "--version".into()],
        ".",
        BTreeMap::new(),
        Duration::from_secs(10),
        16 * 1024,
    )
    .await?;
    if output.exit_code != Some(0) {
        return Err(format!(
            "remote `cargo teaql --version` failed. {}",
            remote_command_diagnostic(&output)
        ));
    }
    let observed = parse_cargo_teaql_version(&output.stdout).ok_or_else(|| {
        format!(
            "Could not parse remote cargo-teaql version from `{}`; required exactly {REQUIRED_CARGO_TEAQL_VERSION}",
            bounded_text(output.stdout.trim(), 1_000)
        )
    })?;
    if observed != REQUIRED_CARGO_TEAQL_VERSION {
        return Err(format!(
            "remote cargo-teaql version {observed} is installed; required exactly {REQUIRED_CARGO_TEAQL_VERSION}"
        ));
    }
    Ok(())
}

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
        "infrastructure error (not model-repairable)",
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
        let fragment = &rendered[pos..][..rendered[pos..]
            .find("}}")
            .map(|e| e + 2)
            .unwrap_or(40)
            .min(rendered[pos..].len())];
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

struct RemoteDeclaredCommandEnvironment {
    tool_environment: crate::tools::DeclaredCommandEnvironment,
    all_refs: std::collections::BTreeSet<String>,
    cargo_test_refs: Vec<String>,
}

/// Build a name-only environment selection for the runner. Placeholder values
/// never reach a remote command; the remote tool adapter forwards only their
/// keys through `ExecRequest.env_refs`, whose values were fixed at session
/// attach time.
fn declared_remote_command_environment<'a>(
    specs: impl IntoIterator<Item = &'a crate::followup_acceptance::FollowUpAcceptanceSpec>,
) -> RemoteDeclaredCommandEnvironment {
    let mut all_names = std::collections::BTreeSet::new();
    let mut cargo_test_names = std::collections::BTreeSet::new();
    let mut cargo_run_names = std::collections::BTreeSet::new();
    for requirement in specs.into_iter().flat_map(|spec| &spec.commands) {
        all_names.extend(requirement.env_ref.iter().cloned());
        if requirement.program == crate::followup_acceptance::CommandProgram::Cargo {
            match requirement.args.first().map(String::as_str) {
                Some("test") => cargo_test_names.extend(requirement.env_ref.iter().cloned()),
                Some("run") => cargo_run_names.extend(requirement.env_ref.iter().cloned()),
                _ => {}
            }
        }
    }
    let placeholders = |names: &std::collections::BTreeSet<String>| {
        names
            .iter()
            .cloned()
            .map(|name| (name, String::new()))
            .collect::<BTreeMap<_, _>>()
    };
    RemoteDeclaredCommandEnvironment {
        tool_environment: crate::tools::DeclaredCommandEnvironment::new_with_all(
            placeholders(&all_names),
            placeholders(&cargo_test_names),
            placeholders(&cargo_run_names),
        ),
        all_refs: all_names,
        cargo_test_refs: cargo_test_names.into_iter().collect(),
    }
}

fn declared_followup_command_environment(
    spec: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
    sqlite_isolation: &crate::process_env::SqliteDatabaseIsolation,
) -> Result<crate::tools::DeclaredCommandEnvironment, String> {
    declared_followup_command_environment_with(spec, sqlite_isolation, |name| {
        std::env::var(name).ok()
    })
}

fn declared_followup_command_environment_with(
    spec: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
    sqlite_isolation: &crate::process_env::SqliteDatabaseIsolation,
    mut lookup: impl FnMut(&str) -> Option<String>,
) -> Result<crate::tools::DeclaredCommandEnvironment, String> {
    let Some(spec) = spec else {
        return Ok(crate::tools::DeclaredCommandEnvironment::default());
    };

    let mut cargo_test_names = std::collections::BTreeSet::new();
    let mut cargo_run_names = std::collections::BTreeSet::new();
    let mut all_names = std::collections::BTreeSet::new();
    for requirement in &spec.commands {
        all_names.extend(requirement.env_ref.iter().cloned());
        if requirement.program != crate::followup_acceptance::CommandProgram::Cargo {
            continue;
        }
        let names = match requirement.args.first().map(String::as_str) {
            Some("test") => &mut cargo_test_names,
            Some("run") => &mut cargo_run_names,
            _ => continue,
        };
        names.extend(requirement.env_ref.iter().cloned());
    }

    let mut missing = Vec::new();
    let all = all_names
        .into_iter()
        .filter_map(|name| match lookup(&name) {
            Some(value) => Some((name.clone(), sqlite_isolation.isolate_value(&name, &value))),
            None => {
                missing.push(name);
                None
            }
        })
        .collect::<std::collections::BTreeMap<_, _>>();
    if !missing.is_empty() {
        missing.sort();
        missing.dedup();
        return Err(format!(
            "Missing or non-Unicode environment variable(s): {}",
            missing.join(", ")
        ));
    }

    let select = |names: std::collections::BTreeSet<String>| {
        names
            .into_iter()
            .filter_map(|name| all.get(&name).cloned().map(|value| (name, value)))
            .collect::<std::collections::BTreeMap<_, _>>()
    };
    let cargo_test = select(cargo_test_names);
    let cargo_run = select(cargo_run_names);

    Ok(crate::tools::DeclaredCommandEnvironment::new_with_all(
        all, cargo_test, cargo_run,
    ))
}

fn build_followup_prompt(
    workspace: &Path,
    instruction: &str,
    original_task: &str,
    history: &[SessionLedgerRecord],
    acceptance: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
) -> String {
    const FOLLOWUP_HISTORY_BUDGET_BYTES: usize = 6_000;
    let previous_changes = render_followup_history(history, FOLLOWUP_HISTORY_BUDGET_BYTES);
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

fn build_followup_prompt_remote(
    workspace: &str,
    instruction: &str,
    original_task: &str,
    history: &[SessionLedgerRecord],
    acceptance: Option<&crate::followup_acceptance::FollowUpAcceptanceSpec>,
) -> String {
    const FOLLOWUP_HISTORY_BUDGET_BYTES: usize = 6_000;
    let previous_changes = render_followup_history(history, FOLLOWUP_HISTORY_BUDGET_BYTES);
    let acceptance = acceptance.map_or_else(
        || {
            "No explicit machine acceptance contract was supplied. You must still complete the requested change, run the independent build and test commands, and yield only after both pass. A max-iteration result will be rejected."
                .to_string()
        },
        crate::followup_acceptance::FollowUpAcceptanceSpec::render_checklist,
    );

    format!(
        "You are already operating at the remote workspace root `{workspace}`. Do not `cd` to this path and do not prefix tool paths with it; use `.` for the root. The validated TeaQL model is `model/main.xml`, and complete cached assist responses are under `.klintcode/assist/`. All project files and commands are authoritative only in the attached runner session.\n\n\
         # Original Task\n{original_task}\n\n\
         # Previously Verified Follow-ups\n{previous_changes}\n\n\
         # Current Follow-up\n{instruction}\n\n\
         # Machine Acceptance\n{acceptance}\n\n\
         Inspect only the application code and workspace configuration and apply the requested changes using write_file/run_command. Compilation alone does not complete this follow-up. Run every required build, test, runtime, and artifact check. Respond with a concise summary only after the full acceptance checklist passes; otherwise keep fixing the remote application workspace.",
        original_task = bounded_text(original_task, 6_000),
        instruction = bounded_text(instruction, 6_000),
        acceptance = bounded_text(&acceptance, 6_000),
    )
}

/// Render every verified follow-up as one compact line under a fixed budget.
///
/// The attempt counter is a `u8`, so even the maximum possible history leaves
/// enough room for every line's stable identifier. Remaining bytes are shared
/// across all instructions and summaries instead of dropping older turns.
fn render_followup_history(history: &[SessionLedgerRecord], max_bytes: usize) -> String {
    if history.is_empty() {
        return "- No previous follow-up changes.".to_string();
    }

    let fixed_bytes = history
        .iter()
        .map(|record| format!("- A{}:  => ", record.attempt).len())
        .sum::<usize>()
        + history.len().saturating_sub(1);
    debug_assert!(
        fixed_bytes <= max_bytes,
        "follow-up identifiers must fit inside the history budget"
    );
    let shared_bytes = max_bytes.saturating_sub(fixed_bytes);
    let bytes_per_record = shared_bytes / history.len();
    let extra_bytes = shared_bytes % history.len();

    history
        .iter()
        .enumerate()
        .map(|(index, record)| {
            let content_budget = bytes_per_record + usize::from(index < extra_bytes);
            let instruction_budget = content_budget / 3;
            let summary_budget = content_budget.saturating_sub(instruction_budget);
            format!(
                "- A{}: {} => {}",
                record.attempt,
                bounded_inline(&record.instruction, instruction_budget),
                bounded_inline(&record.summary, summary_budget)
            )
        })
        .collect::<Vec<_>>()
        .join("\n")
}

fn bounded_inline(text: &str, max_bytes: usize) -> String {
    let compact = text.split_whitespace().collect::<Vec<_>>().join(" ");
    if compact.len() <= max_bytes {
        return compact;
    }
    if max_bytes == 0 {
        return String::new();
    }
    const ELLIPSIS: &str = "…";
    if max_bytes < ELLIPSIS.len() {
        return ".".repeat(max_bytes);
    }
    let mut boundary = max_bytes - ELLIPSIS.len();
    while boundary > 0 && !compact.is_char_boundary(boundary) {
        boundary -= 1;
    }
    format!("{}{}", &compact[..boundary], ELLIPSIS)
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
    fn followup_acceptance_contract_is_retained_until_deterministic_success() {
        let first = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","files":[{{"path":"src/first.rs","contains":["FIRST_CONTRACT"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();
        let second = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","files":[{{"path":"src/second.rs","contains":["SECOND_CONTRACT"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();
        let mut executor = test_executor();
        executor.set_followup_acceptance_specs(vec![first.clone(), second.clone()]);

        assert_eq!(
            executor.current_followup_acceptance_spec(),
            Some(first.clone())
        );
        executor.settle_followup_acceptance_spec(false);
        assert_eq!(
            executor.current_followup_acceptance_spec(),
            Some(first),
            "infrastructure and verification failures must retain the current contract for retry"
        );

        executor.settle_followup_acceptance_spec(true);
        assert_eq!(
            executor.current_followup_acceptance_spec(),
            Some(second),
            "only deterministic success may advance the contract queue"
        );
    }

    #[test]
    fn legacy_session_ledger_defaults_new_metrics_without_parsing_summary() {
        let legacy = r#"[{"attempt":2,"instruction":"continue","summary":"free-form text"}]"#;
        let records: Vec<SessionLedgerRecord> = serde_json::from_str(legacy).unwrap();

        assert_eq!(records.len(), 1);
        assert_eq!(records[0].phase, SessionRecordPhase::FollowUp);
        assert_eq!(records[0].verification_rounds, 0);
        assert_eq!(records[0].model_iterations, 0);
        assert_eq!(records[0].tool_calls, 0);
        assert_eq!(records[0].elapsed_secs, 0.0);
    }

    #[tokio::test]
    async fn structured_session_ledger_is_cumulative_and_retry_safe() {
        let test_root = tempfile::tempdir().unwrap();
        let artifacts = RunArtifacts::create(test_root.path(), "metrics-run")
            .await
            .unwrap();
        let mut executor = test_executor();
        executor.artifacts = Some(artifacts);

        let first = SessionLedgerRecord {
            phase: SessionRecordPhase::InitialBuild,
            attempt: 1,
            instruction: "initial".to_string(),
            summary: "passed".to_string(),
            verification_rounds: 1,
            model_iterations: 4,
            tool_calls: 7,
            elapsed_secs: 2.5,
        };
        executor
            .persist_session_record(first.clone())
            .await
            .unwrap();
        let followup = SessionLedgerRecord {
            phase: SessionRecordPhase::FollowUp,
            attempt: 2,
            instruction: "change".to_string(),
            summary: "passed".to_string(),
            verification_rounds: 2,
            model_iterations: 13,
            tool_calls: 21,
            elapsed_secs: 8.75,
        };
        executor
            .persist_session_record(followup.clone())
            .await
            .unwrap();
        let mut retried = followup;
        retried.verification_rounds = 3;
        retried.model_iterations = 19;
        executor
            .persist_session_record(retried.clone())
            .await
            .unwrap();

        let ledger_path = test_root
            .path()
            .join("metrics-run/attempt-02/session-ledger.json");
        let records: Vec<SessionLedgerRecord> =
            serde_json::from_str(&std::fs::read_to_string(ledger_path).unwrap()).unwrap();
        assert_eq!(records, vec![first, retried]);
    }

    #[tokio::test]
    async fn acceptance_reports_keep_each_verification_round() {
        let test_root = tempfile::tempdir().unwrap();
        let artifacts = RunArtifacts::create(test_root.path(), "evidence-run")
            .await
            .unwrap();
        let workspace = test_root.path().join("workspace");
        std::fs::create_dir_all(&workspace).unwrap();
        let first = crate::followup_acceptance::FollowUpAcceptanceReport {
            schema: crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA.to_string(),
            passed: false,
            checks: vec![],
        };
        let second = crate::followup_acceptance::FollowUpAcceptanceReport {
            schema: crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA.to_string(),
            passed: true,
            checks: vec![],
        };

        persist_followup_acceptance_report(&workspace, Some(&artifacts), 2, 1, &first)
            .await
            .unwrap();
        persist_followup_acceptance_report(&workspace, Some(&artifacts), 2, 2, &second)
            .await
            .unwrap();

        let attempt = test_root.path().join("evidence-run/attempt-02");
        let round_one: crate::followup_acceptance::FollowUpAcceptanceReport = serde_json::from_str(
            &std::fs::read_to_string(attempt.join("followup-acceptance-report-round-01.json"))
                .unwrap(),
        )
        .unwrap();
        let round_two: crate::followup_acceptance::FollowUpAcceptanceReport = serde_json::from_str(
            &std::fs::read_to_string(attempt.join("followup-acceptance-report-round-02.json"))
                .unwrap(),
        )
        .unwrap();
        let latest: crate::followup_acceptance::FollowUpAcceptanceReport = serde_json::from_str(
            &std::fs::read_to_string(attempt.join("followup-acceptance-report.json")).unwrap(),
        )
        .unwrap();
        assert!(!round_one.passed);
        assert!(round_two.passed);
        assert_eq!(latest, second);
        assert!(
            workspace
                .join(".klintcode/followup-acceptance-report.json")
                .is_file()
        );
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

    #[test]
    fn remote_workspace_selection_fails_closed_instead_of_using_legacy_local_state() {
        let legacy = Path::new("/tmp/legacy-workspace-must-not-run");
        let error = require_authoritative_remote_workspace(None, Some(legacy)).unwrap_err();

        assert!(error.contains(INFRASTRUCTURE_FAILURE_PREFIX));
        assert!(error.contains("local fallback is forbidden"));
        assert_eq!(
            require_authoritative_remote_workspace(Some("attempt-01/build"), Some(legacy),)
                .unwrap(),
            "attempt-01/build"
        );
        assert!(
            require_authoritative_remote_workspace(Some("/local/absolute/path"), None).is_err(),
            "runner cwd must remain logical and relative"
        );
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
        let history = vec![SessionLedgerRecord {
            phase: SessionRecordPhase::FollowUp,
            attempt: 2,
            instruction: "add a query".to_string(),
            summary: "implemented the query".to_string(),
            verification_rounds: 1,
            model_iterations: 3,
            tool_calls: 4,
            elapsed_secs: 1.25,
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
    fn seventh_followup_prompt_retains_all_six_prior_turns_within_history_budget() {
        let history = (1_u8..=6)
            .map(|ordinal| SessionLedgerRecord {
                phase: SessionRecordPhase::FollowUp,
                attempt: ordinal + 1,
                instruction: format!(
                    "TURN_{ordinal}_REQUEST {}",
                    "long request details\n".repeat(300)
                ),
                summary: format!(
                    "TURN_{ordinal}_RESULT {}",
                    "long verified result details\n".repeat(300)
                ),
                verification_rounds: 1,
                model_iterations: 2,
                tool_calls: 3,
                elapsed_secs: 1.0,
            })
            .collect::<Vec<_>>();

        let rendered = render_followup_history(&history, 6_000);
        assert!(rendered.len() <= 6_000);
        assert_eq!(rendered.lines().count(), 6);
        for ordinal in 1_u8..=6 {
            assert!(rendered.contains(&format!("- A{}:", ordinal + 1)));
            assert!(rendered.contains(&format!("TURN_{ordinal}_REQUEST")));
            assert!(rendered.contains(&format!("TURN_{ordinal}_RESULT")));
        }

        let prompt = build_followup_prompt(
            Path::new("/workspace/build"),
            "perform the seventh follow-up",
            "build a school service",
            &history,
            None,
        );
        for ordinal in 1_u8..=6 {
            assert!(prompt.contains(&format!("TURN_{ordinal}_REQUEST")));
            assert!(prompt.contains(&format!("TURN_{ordinal}_RESULT")));
        }
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
    fn missing_followup_environment_checks_non_agent_commands_too() {
        let spec = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","commands":[{{"program":"mvn","args":["test"],"env_ref":["KLINTCODE_DEFINITELY_MISSING_MAVEN_ENV_91827"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();

        assert_eq!(
            missing_followup_environment(std::iter::once(&spec)),
            vec!["KLINTCODE_DEFINITELY_MISSING_MAVEN_ENV_91827"]
        );
    }

    #[test]
    fn typed_followup_environment_resolves_names_without_exposing_values() {
        let spec = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","commands":[{{"program":"cargo","args":["test"],"env_ref":["KLINTCODE_TEST_DATABASE_URL"]}},{{"program":"cargo","args":["run"],"env_ref":["KLINTCODE_RUN_DATABASE_URL"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();
        let isolation = crate::process_env::SqliteDatabaseIsolation::new().unwrap();
        let environment =
            declared_followup_command_environment_with(Some(&spec), &isolation, |name| {
                Some(format!("opaque-value-for-{name}"))
            })
            .unwrap();
        let debug = format!("{environment:?}");

        assert!(debug.contains("KLINTCODE_TEST_DATABASE_URL"));
        assert!(debug.contains("KLINTCODE_RUN_DATABASE_URL"));
        assert!(!debug.contains("opaque-value"));
    }

    #[test]
    fn remote_followup_environment_is_name_only_and_ignores_local_values() {
        let spec = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","commands":[{{"program":"cargo","args":["test"],"env_ref":["PATH"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();
        let environment = declared_remote_command_environment(std::iter::once(&spec));

        assert!(environment.all_refs.contains("PATH"));
        assert_eq!(environment.cargo_test_refs, vec!["PATH"]);
        assert_eq!(
            environment
                .tool_environment
                .values_for_names(&["PATH".to_string()])
                .unwrap()["PATH"],
            "",
            "the controller's PATH value must never cross into a remote request"
        );
    }

    #[test]
    fn sqlite_followup_environment_is_stable_per_executor_and_not_shared() {
        const NAME: &str = "KLINTCODE_SHARED_DATABASE_URL";
        let spec = crate::followup_acceptance::FollowUpAcceptanceSpec::parse_json(&format!(
            r#"{{"schema":"{}","commands":[{{"program":"cargo","args":["test"],"env_ref":["{NAME}"]}},{{"program":"cargo","args":["run"],"env_ref":["{NAME}"]}}]}}"#,
            crate::followup_acceptance::FOLLOWUP_ACCEPTANCE_SCHEMA
        ))
        .unwrap();
        let isolation = crate::process_env::SqliteDatabaseIsolation::new().unwrap();
        let resolve = |isolation| {
            declared_followup_command_environment_with(Some(&spec), isolation, |_| {
                Some("sqlite://caller-shared.db?mode=ro".to_string())
            })
            .unwrap()
            .values_for_names(&[NAME.to_string()])
            .unwrap()[NAME]
                .clone()
        };

        let first = resolve(&isolation);
        let retry = resolve(&isolation);
        let other = resolve(&crate::process_env::SqliteDatabaseIsolation::new().unwrap());

        assert_eq!(first, retry);
        assert_ne!(first, other);
        assert!(first.starts_with("sqlite:///"));
        assert!(!first.contains("caller-shared.db"));
        assert!(!first.contains("mode=ro"));
    }

    #[tokio::test]
    async fn deterministic_followup_tests_receive_declared_environment_and_redact_it() {
        const NAME: &str = "KLINTCODE_VERIFY_DATABASE_URL";
        const VALUE: &str = "sqlite://opaque-verifier-runtime.db";

        let workspace = tempfile::tempdir().expect("temporary workspace");
        std::fs::create_dir_all(workspace.path().join("src")).unwrap();
        std::fs::write(
            workspace.path().join("Cargo.toml"),
            "[package]\nname = \"verify-env-smoke\"\nversion = \"0.1.0\"\nedition = \"2024\"\n\n[workspace]\n",
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("src/lib.rs"),
            format!(
                r#"#[test]
fn declared_runtime_is_available() {{
    assert_eq!(std::env::var("{NAME}").as_deref(), Ok("{VALUE}"));
    assert!(std::env::var("MIMO_API_KEY").is_err());
    println!("verifier-runtime={{}}", std::env::var("{NAME}").unwrap());
}}
"#
            ),
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("build.rs"),
            format!(
                r#"fn main() {{
    println!("cargo:rerun-if-env-changed={NAME}");
    println!("cargo:warning=verifier-runtime={{}}", std::env::var("{NAME}").unwrap());
}}
"#
            ),
        )
        .unwrap();
        let environment = crate::tools::DeclaredCommandEnvironment::new(
            std::collections::BTreeMap::from([(NAME.to_string(), VALUE.to_string())]),
            std::collections::BTreeMap::new(),
        );

        let (diagnostic, _observed) = verify_generated_tests_with_environment(
            workspace.path(),
            "rust-lib-core",
            &environment,
        )
        .await
        .expect("declared environment should reach deterministic cargo test");

        assert!(diagnostic.contains(&format!("[REDACTED:{NAME}]")));
        assert!(!diagnostic.contains(VALUE));
        assert!(
            verify_generated_tests(workspace.path(), "rust-lib-core")
                .await
                .is_err(),
            "the default initial/no-contract verifier must not inherit follow-up runtime inputs"
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
        assert!(
            !requires_change,
            "read-only spec should not require workspace modification"
        );
    }

    #[test]
    fn domain_validation_resolves_relative_model_input_before_changing_directory() {
        let current = std::env::current_dir().expect("current directory");
        let attempt = tempfile::tempdir_in(&current).expect("attempt directory");
        let model = attempt.path().join("model");
        std::fs::create_dir(&model).expect("model directory");
        let relative = model
            .strip_prefix(&current)
            .expect("model beneath current directory");

        let resolved = resolve_domain_model_dir(relative).expect("resolve relative model input");

        assert!(resolved.is_absolute());
        assert_eq!(resolved, std::fs::canonicalize(model).unwrap());
    }
}
