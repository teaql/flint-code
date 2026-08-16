//! Legacy TUI application state — thin wrapper around agent-core.

use agent_core::event::*;
use agent_core::reducer::SideEffect;
use agent_core::run_controller::RunController;
use agent_core::state::RunState;
use anyhow::Result;
use context_builder::build_chat_messages;
use model_vllm::chat::ChatMessage;
use model_vllm::{backend::ModelClient, profile::ModelProfile};
use pipeline::execution::RemoteExecution;
use pipeline::executor::PipelineExecutor;
use pipeline::remote_config::ResolvedRemoteTarget;
use std::future::Future;
use std::path::{Path, PathBuf};
use std::pin::Pin;
use std::sync::Arc;
use tokio::sync::{mpsc, oneshot};

/// Active view in the TUI
#[derive(Debug, Clone, Copy, PartialEq)]
pub enum View {
    Main,
    Stats,
    Candidate,
    Diagnostics,
    TranscriptDetail,
    Help,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum TimelineRole {
    User,
    Agent,
    Activity,
    Success,
    Error,
}

#[derive(Debug, Clone, PartialEq, Eq)]
pub struct TimelineEntry {
    pub role: TimelineRole,
    pub content: String,
}

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct TranscriptHitbox {
    pub id: usize,
    pub row: u16,
    pub left: u16,
    pub right: u16,
}

#[derive(Debug, Clone, PartialEq, Eq)]
pub enum ServiceHealth {
    Checking,
    Healthy,
    Unavailable(String),
}

#[derive(Debug)]
pub enum ChatEvent {
    Completed(ModelResult),
    Failed(String),
}

#[derive(Debug, Clone, PartialEq, Eq)]
enum InputIntent {
    Chat(String),
    Task(String),
}

/// Whether ordinary composer input belongs to a persistent coding task.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum TaskSessionMode {
    /// Ordinary input starts or continues the current coding task.
    Attached,
    /// Ordinary input is lightweight conversation until a task is attached again.
    Detached,
}

/// Whether printable keys edit the prompt or control the dashboard.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum InputMode {
    Editing,
    Navigation,
}

enum ExecutorWorkerControl {
    Cancel { acknowledged: oneshot::Sender<()> },
}

trait SideEffectHandler: Send + 'static {
    fn handle_effect(
        &mut self,
        effect: SideEffect,
    ) -> Pin<Box<dyn Future<Output = ()> + Send + '_>>;
}

impl SideEffectHandler for PipelineExecutor {
    fn handle_effect(
        &mut self,
        effect: SideEffect,
    ) -> Pin<Box<dyn Future<Output = ()> + Send + '_>> {
        Box::pin(self.handle(effect))
    }
}

async fn run_executor_worker<H: SideEffectHandler>(
    mut handler: H,
    mut effect_rx: mpsc::Receiver<SideEffect>,
    mut control_rx: mpsc::Receiver<ExecutorWorkerControl>,
) {
    'worker: loop {
        tokio::select! {
            biased;
            control = control_rx.recv() => {
                match control {
                    Some(ExecutorWorkerControl::Cancel { acknowledged }) => {
                        while effect_rx.try_recv().is_ok() {}
                        let _ = acknowledged.send(());
                    }
                    None => break,
                }
            }
            effect = effect_rx.recv() => {
                let Some(effect) = effect else { break };
                let running = handler.handle_effect(effect);
                tokio::pin!(running);
                tokio::select! {
                    biased;
                    control = control_rx.recv() => {
                        match control {
                            Some(ExecutorWorkerControl::Cancel { acknowledged }) => {
                                // Leaving this select drops `running`, which cancels the
                                // active model/process future while retaining `handler`.
                                while effect_rx.try_recv().is_ok() {}
                                let _ = acknowledged.send(());
                            }
                            None => break 'worker,
                        }
                    }
                    _ = &mut running => {}
                }
            }
        }
    }
}

/// TUI application state
pub struct App {
    pub profile: ModelProfile,
    /// Validated SSH target used for every project filesystem and process operation.
    pub remote_target: ResolvedRemoteTarget,
    /// Durable runner session currently owned by this task, when attached.
    pub remote_session_id: Option<String>,
    pub controller: Option<RunController>,
    pub dummy_run: RunState,
    pub view: View,
    pub should_quit: bool,
    pub candidate: String,
    pub diagnostics: String,
    pub scroll_offset: u16,
    /// Number of rendered rows to browse backward from the latest transcript.
    pub transcript_scroll_back: u16,
    pub proxy_event_rx: Option<mpsc::Receiver<RunEvent>>,
    pub executor_effect_tx: Option<mpsc::Sender<SideEffect>>,
    executor_control_tx: Option<mpsc::Sender<ExecutorWorkerControl>>,
    executor_worker: Option<tokio::task::JoinHandle<()>>,
    remote_execution: Option<Arc<RemoteExecution>>,
    resume_session: Option<String>,
    chat_worker: Option<tokio::task::JoinHandle<()>>,
    pub chat_event_rx: Option<mpsc::Receiver<ChatEvent>>,
    pub chat_in_flight: bool,
    /// Explicit routing mode for ordinary composer input.
    pub task_session_mode: TaskSessionMode,
    /// A finalized application workspace exists and can accept follow-up work.
    reusable_workspace: bool,
    pub input_mode: InputMode,
    pub input_buffer: String,
    /// Cursor position measured in Unicode scalar values.
    pub input_cursor: usize,
    pub input_cursor_visible: bool,
    pub plan_pulse_phase: u8,
    pub input_notice: Option<String>,
    pub timeline: Vec<TimelineEntry>,
    pub transcript_detail_id: Option<usize>,
    pub transcript_hitboxes: Vec<TranscriptHitbox>,
    pub llm_service_health: ServiceHealth,
    pub rag_service_health: ServiceHealth,
    pub show_right_panel: bool,
    /// Prompt tokens reported by the most recent model call.
    pub latest_prompt_tokens: u64,
    /// Largest prompt-token count actually observed during this TUI session.
    pub max_observed_prompt_tokens: u64,
    pub global_input_tokens: u64,
    pub global_output_tokens: u64,
    pub global_model_calls: u64,
    pub vllm_in_flight: bool,
    pub rag_in_flight: bool,
}

impl App {
    pub fn new(
        profile_path: Option<&std::path::Path>,
        remote_target: ResolvedRemoteTarget,
        resume_session: Option<String>,
    ) -> Result<Self> {
        let profile = if let Some(path) = profile_path {
            ModelProfile::load(path)?
        } else if let Ok(configured) = std::env::var("FLINTCODE_PROFILE") {
            ModelProfile::load(Path::new(&configured))?
        } else {
            let default_profile = PathBuf::from("profiles/local-qwen.toml");
            if default_profile.exists() {
                ModelProfile::load(&default_profile)?
            } else {
                toml::from_str(include_str!("../../../profiles/local-qwen.toml"))
                    .expect("failed to load fallback profile")
            }
        };

        let max_repairs = profile.run.max_repairs;
        let run_id = format!("run-{}", chrono::Utc::now().format("%Y%m%d-%H%M%S"));
        let run = RunState::new(run_id, max_repairs);

        Ok(Self {
            profile,
            remote_target,
            remote_session_id: None,
            controller: None,
            dummy_run: run,
            view: View::Main,
            should_quit: false,
            candidate: String::new(),
            diagnostics: String::new(),
            scroll_offset: 0,
            transcript_scroll_back: 0,
            proxy_event_rx: None,
            executor_effect_tx: None,
            executor_control_tx: None,
            executor_worker: None,
            remote_execution: None,
            resume_session,
            chat_worker: None,
            chat_event_rx: None,
            chat_in_flight: false,
            task_session_mode: TaskSessionMode::Attached,
            reusable_workspace: false,
            input_mode: InputMode::Editing,
            input_buffer: String::new(),
            input_cursor: 0,
            input_cursor_visible: true,
            plan_pulse_phase: 0,
            input_notice: None,
            timeline: Vec::new(),
            transcript_detail_id: None,
            transcript_hitboxes: Vec::new(),
            llm_service_health: ServiceHealth::Checking,
            rag_service_health: ServiceHealth::Checking,
            show_right_panel: true,
            latest_prompt_tokens: 0,
            max_observed_prompt_tokens: 0,
            global_input_tokens: 0,
            global_output_tokens: 0,
            global_model_calls: 0,
            vllm_in_flight: false,
            rag_in_flight: false,
        })
    }

    /// Probe the configured model backend without sending a generation request.
    pub async fn probe_model_service(profile: ModelProfile) -> ServiceHealth {
        let client = match ModelClient::from_profile(profile) {
            Ok(client) => client,
            Err(error) => return ServiceHealth::Unavailable(error.to_string()),
        };
        let report = client
            .probe(model_vllm::backend::ProbeOptions::health())
            .await;
        if report.passed {
            ServiceHealth::Healthy
        } else {
            let detail = report
                .checks
                .into_iter()
                .find(|check| !check.passed)
                .map(|check| check.detail)
                .unwrap_or_else(|| "health check failed".to_string());
            ServiceHealth::Unavailable(detail)
        }
    }

    /// Probe the local Weaviate service used for RAG retrieval.
    pub async fn probe_rag_service() -> ServiceHealth {
        let client = match reqwest::Client::builder()
            .timeout(std::time::Duration::from_secs(5))
            .build()
        {
            Ok(client) => client,
            Err(error) => return ServiceHealth::Unavailable(error.to_string()),
        };

        match client
            .get("http://127.0.0.1:8085/v1/.well-known/ready")
            .send()
            .await
        {
            Ok(response) if response.status().is_success() => ServiceHealth::Healthy,
            Ok(response) => ServiceHealth::Unavailable(format!("HTTP {}", response.status())),
            Err(error) => ServiceHealth::Unavailable(error.to_string()),
        }
    }

    pub async fn start_task(&mut self, task_path: &Path) -> Result<()> {
        self.task_session_mode = TaskSessionMode::Attached;
        let mut executor = self.initialize_run().await?;
        executor.load_task_from_path(task_path).await;
        self.start_executor_worker(executor);
        Ok(())
    }

    async fn initialize_run(&mut self) -> Result<PipelineExecutor> {
        self.stop_executor_and_detach().await?;
        self.controller = None;
        self.reusable_workspace = false;

        let max_repairs = self.profile.run.max_repairs;
        let run_id = format!("run-{}", chrono::Utc::now().format("%Y%m%d-%H%M%S"));

        // Receiver is intentionally dropped: the TUI receives executor events
        // itself, reduces them synchronously with RunController::reduce_event,
        // and dispatches the returned effect to its independent worker. The
        // controller-side channel is only used by process_next (the CLI path).
        let (side_effect_tx, _) = mpsc::channel(32);
        let (controller, _controller_event_tx) =
            RunController::new(run_id.clone(), max_repairs, side_effect_tx);

        let (proxy_event_tx, proxy_event_rx) = mpsc::channel(64);

        let requested_resume_session = self.resume_session.clone();
        let execution_action = if requested_resume_session.is_some() {
            "attach"
        } else {
            "create"
        };
        let execution = if let Some(session_id) = requested_resume_session.as_ref() {
            RemoteExecution::attach_with_environment(
                self.remote_target.ssh.clone(),
                session_id.clone(),
                self.remote_target.client_policy.clone(),
                self.remote_target.environment_refs.clone(),
                &run_id,
            )
            .await
        } else {
            RemoteExecution::create_with_environment(
                self.remote_target.ssh.clone(),
                self.remote_target.client_policy.clone(),
                self.remote_target.environment_refs.clone(),
                &run_id,
            )
            .await
        }
        .map_err(|error| {
            anyhow::anyhow!(
                "[infrastructure] failed to {execution_action} SSH execution target {}: {error}",
                self.remote_target.name
            )
        })?;
        if requested_resume_session.is_some() {
            self.resume_session = None;
        }
        let execution = Arc::new(execution);
        let session_id = execution.session_id().await;

        // Reports and remote-session manifests remain local artifacts. All
        // project files and project processes are owned by `execution`.
        let runs_root = std::env::current_dir()
            .unwrap_or_else(|_| PathBuf::from("."))
            .join("runs");
        let executor = PipelineExecutor::new_remote(
            self.profile.clone(),
            proxy_event_tx,
            runs_root,
            run_id,
            execution.clone(),
        );
        let mut executor = match executor {
            Ok(executor) => executor,
            Err(error) => {
                execution.detach().await.map_err(|detach_error| {
                    anyhow::anyhow!(
                        "[infrastructure] pipeline initialization failed ({error}); also failed to detach SSH session {session_id}: {detach_error}"
                    )
                })?;
                return Err(error.into());
            }
        };

        // Upgrade generated workspaces from teaql 4.2.5 (has SQLite boolean bug)
        // to 4.2.7 which fixes the issue. Applied as a string patch on every
        // Cargo.toml found in the generated workspace before the build step.
        executor.set_patches({
            let mut p = std::collections::HashMap::new();
            p.insert(
                "teaql-core = \"4.2.5\"".to_string(),
                "teaql-core = \"4.2.7\"".to_string(),
            );
            p.insert(
                "teaql-macros = \"4.2.5\"".to_string(),
                "teaql-macros = \"4.2.7\"".to_string(),
            );
            p.insert(
                "teaql-runtime = \"4.2.5\"".to_string(),
                "teaql-runtime = \"4.2.7\"".to_string(),
            );
            p.insert(
                "teaql-sql = \"4.2.5\"".to_string(),
                "teaql-sql = \"4.2.7\"".to_string(),
            );
            p.insert(
                "teaql-data-service = \"4.2.5\"".to_string(),
                "teaql-data-service = \"4.2.7\"".to_string(),
            );
            p.insert(
                "teaql-provider-sqlite = \"4.2.5\"".to_string(),
                "teaql-provider-sqlite = \"4.2.7\"".to_string(),
            );
            // pinned exact-version form: =4.2.5
            p.insert("= \"=4.2.5\"".to_string(), "= \"=4.2.7\"".to_string());
            p
        });

        self.proxy_event_rx = Some(proxy_event_rx);
        self.controller = Some(controller);
        self.remote_session_id = Some(session_id.clone());
        self.remote_execution = Some(execution);
        self.timeline.push(TimelineEntry {
            role: TimelineRole::Activity,
            content: format!(
                "SSH execution attached · target={} · session={}",
                self.remote_target.name,
                short_session_id(&session_id)
            ),
        });

        Ok(executor)
    }

    async fn stop_executor_and_detach(&mut self) -> Result<()> {
        if let Some(worker) = self.executor_worker.take() {
            worker.abort();
            let _ = worker.await;
        }
        self.executor_effect_tx = None;
        self.executor_control_tx = None;
        self.proxy_event_rx = None;

        if let Some(execution) = self.remote_execution.take() {
            execution.detach().await.map_err(|error| {
                anyhow::anyhow!(
                    "[infrastructure] failed to detach SSH execution target {} session {}: {error}",
                    self.remote_target.name,
                    self.remote_session_id.as_deref().unwrap_or("unknown")
                )
            })?;
        }
        self.remote_session_id = None;
        Ok(())
    }

    /// Stop any active work and close the SSH transport while retaining the
    /// durable runner session for an explicit future `--resume-session`.
    pub async fn shutdown(&mut self) -> Result<()> {
        if self.run_state().state.is_active() {
            self.cancel_current_task().await?;
        }
        if let Some(worker) = self.chat_worker.take() {
            worker.abort();
            let _ = worker.await;
        }
        self.chat_in_flight = false;
        self.stop_executor_and_detach().await
    }

    fn start_executor_worker(&mut self, executor: PipelineExecutor) {
        let (effect_tx, effect_rx) = mpsc::channel(32);
        let (control_tx, control_rx) = mpsc::channel(1);
        self.executor_effect_tx = Some(effect_tx);
        self.executor_control_tx = Some(control_tx);
        self.executor_worker = Some(tokio::spawn(run_executor_worker(
            executor, effect_rx, control_rx,
        )));
    }

    /// Reliably enqueue one meaningful controller effect for the executor.
    /// Display-only `None` effects never enter the bounded worker queue.
    pub async fn dispatch_side_effect(&self, effect: SideEffect) -> Result<bool> {
        if matches!(effect, SideEffect::None) {
            return Ok(false);
        }
        let tx = self
            .executor_effect_tx
            .as_ref()
            .ok_or_else(|| anyhow::anyhow!("executor worker is not available"))?;
        tx.send(effect)
            .await
            .map_err(|_| anyhow::anyhow!("executor worker stopped before accepting the effect"))?;
        Ok(true)
    }

    /// Reduce an event directly in the TUI task, then reliably dispatch the
    /// resulting effect to the independent executor worker.
    pub async fn process_controller_event(&mut self, event: RunEvent) -> Result<SideEffect> {
        let effect = self
            .controller
            .as_mut()
            .ok_or_else(|| anyhow::anyhow!("run controller is not available"))?
            .reduce_event(event);
        self.dispatch_controller_effect(effect.clone()).await?;
        Ok(effect)
    }

    async fn dispatch_controller_effect(&mut self, effect: SideEffect) -> Result<()> {
        let effect_label = match &effect {
            SideEffect::RunPreflight => Some("→ RunPreflight"),
            SideEffect::Generate { attempt } => Some(if *attempt == 1 {
                "→ Generate"
            } else {
                "→ Repair/Generate"
            }),
            SideEffect::RunLocalValidation { .. } => Some("→ LocalValidation"),
            SideEffect::RunDomainValidation { .. } => Some("→ DomainValidation"),
            SideEffect::RunBuildValidation { .. } => Some("→ BuildValidation"),
            SideEffect::Repair { .. } => Some("→ Repair"),
            SideEffect::WriteFinalArtifact => Some("→ WriteFinalArtifact"),
            SideEffect::RecordFailure { error } => {
                self.timeline.push(TimelineEntry {
                    role: TimelineRole::Error,
                    content: format!("→ RecordFailure: {error}"),
                });
                None
            }
            _ => None,
        };
        if let Some(label) = effect_label {
            self.timeline.push(TimelineEntry {
                role: TimelineRole::Activity,
                content: label.to_string(),
            });
        }

        match effect {
            SideEffect::RequestConsent { action, .. } => {
                let event = RunEvent::ConsentRequired { action };
                self.observe_event(&event);
                let next = self
                    .controller
                    .as_mut()
                    .ok_or_else(|| anyhow::anyhow!("run controller is not available"))?
                    .reduce_event(event);
                debug_assert!(matches!(next, SideEffect::None));
            }
            SideEffect::None => {}
            effect => {
                self.dispatch_side_effect(effect).await?;
            }
        }
        Ok(())
    }

    /// Stop the active executor future before marking the controller cancelled.
    /// The worker itself remains alive so a completed workspace can still be
    /// used for a later explicit continuation.
    pub async fn cancel_current_task(&mut self) -> Result<bool> {
        if !self.run_state().state.is_active() {
            return Ok(false);
        }

        if let Some(control_tx) = self.executor_control_tx.as_ref() {
            let (acknowledged, acknowledgement) = oneshot::channel();
            if control_tx
                .send(ExecutorWorkerControl::Cancel { acknowledged })
                .await
                .is_ok()
            {
                acknowledgement.await.map_err(|_| {
                    anyhow::anyhow!("executor worker stopped before confirming cancellation")
                })?;
            }
        }

        // Cancellation acknowledgement means the executor cannot emit more
        // events for the old effect. Drop anything it buffered before that
        // acknowledgement, then synchronously cancel and drain the controller.
        if let Some(rx) = self.proxy_event_rx.as_mut() {
            while rx.try_recv().is_ok() {}
        }
        let cancelled = if let Some(controller) = self.controller.as_mut() {
            controller.cancel_current()
        } else {
            matches!(
                agent_core::reducer::reduce(&mut self.dummy_run, RunEvent::CancelRequested),
                SideEffect::None
            ) && matches!(
                self.dummy_run.state,
                agent_core::state::PipelineState::Cancelled
            )
        };
        if cancelled {
            self.timeline.push(TimelineEntry {
                role: TimelineRole::Activity,
                content: "Task cancelled · workspace retained when reusable".to_string(),
            });
            self.input_notice = Some("Task cancelled".to_string());
        }
        Ok(cancelled)
    }

    async fn discard_current_run(&mut self) -> Result<()> {
        self.stop_executor_and_detach().await?;
        self.resume_session = None;
        self.controller = None;

        let run_id = format!("run-{}", chrono::Utc::now().format("%Y%m%d-%H%M%S"));
        self.dummy_run = RunState::new(run_id, self.profile.run.max_repairs);
        self.candidate.clear();
        self.diagnostics.clear();
        self.latest_prompt_tokens = 0;
        self.vllm_in_flight = false;
        self.rag_in_flight = false;
        self.reusable_workspace = false;
        Ok(())
    }

    /// Submit the current composer text as a fresh in-memory task.
    pub async fn submit_input(&mut self) -> Result<()> {
        let prompt = self.input_buffer.trim().to_string();
        if prompt.is_empty() {
            self.input_notice = Some("Enter a task or question".to_string());
            return Ok(());
        }
        if prompt.starts_with('/')
            && !prompt.to_ascii_lowercase().starts_with("/task ")
            && !prompt.to_ascii_lowercase().starts_with("/ask ")
            && !prompt.to_ascii_lowercase().starts_with("/chat ")
        {
            self.execute_slash_command(&prompt).await?;
            return Ok(());
        }
        let intent = classify_input(&prompt, self.task_session_mode);
        match &intent {
            InputIntent::Task(_) if self.run_state().state.is_active() => {
                self.input_notice =
                    Some("A task is already running; press Esc, then c to cancel".to_string());
                return Ok(());
            }
            InputIntent::Task(_) if self.chat_in_flight => {
                self.input_notice =
                    Some("Wait for the current answer before submitting a task".to_string());
                return Ok(());
            }
            InputIntent::Chat(_) if self.chat_in_flight => {
                self.input_notice = Some("A lightweight answer is already in progress".to_string());
                return Ok(());
            }
            _ => {}
        }

        self.timeline.push(TimelineEntry {
            role: TimelineRole::User,
            content: prompt.clone(),
        });
        self.transcript_scroll_back = 0;
        match intent {
            InputIntent::Chat(question) => {
                self.start_chat(question)?;
                self.clear_input("Answering");
            }
            InputIntent::Task(task) => {
                self.task_session_mode = TaskSessionMode::Attached;

                // A task package is always a fresh modeling run. Treating its
                // path as follow-up prose would silently ignore task.md and
                // acceptance.json.
                let task_package = existing_task_package(&task);

                // Preserve terminal runs so follow-up tasks keep editing their workspace.
                if matches!(
                    self.run_state().state,
                    agent_core::state::PipelineState::Completed
                        | agent_core::state::PipelineState::Failed { .. }
                        | agent_core::state::PipelineState::Cancelled
                ) && self.executor_effect_tx.is_some()
                    && self.reusable_workspace
                    && task_package.is_none()
                {
                    if let Some(rx) = self.proxy_event_rx.as_mut() {
                        while rx.try_recv().is_ok() {}
                    }
                    self.process_controller_event(RunEvent::ContinueTask(task))
                        .await?;
                    self.clear_input("Follow-up submitted");
                    return Ok(());
                }

                let mut executor = self.initialize_run().await?;

                if let Some(task_package) = task_package {
                    executor.load_task_from_path(&task_package).await;
                } else {
                    // Determine build target from user intent. Task packages
                    // derive and enforce this from acceptance.json instead.
                    let lower_task = task.to_lowercase();
                    if lower_task.contains("rust") {
                        executor.set_build_target("rust-lib-core".to_string());
                    } else if lower_task.contains("java") {
                        executor.set_build_target("java-spring-boot-lib-core".to_string());
                    }
                    executor.load_task_from_text(&task).await;
                }
                self.start_executor_worker(executor);
                self.clear_input("Task submitted");
            }
        }
        Ok(())
    }

    fn clear_input(&mut self, notice: &str) {
        self.input_buffer.clear();
        self.input_cursor = 0;
        self.input_cursor_visible = true;
        self.input_notice = Some(notice.to_string());
    }

    fn start_chat(&mut self, question: String) -> Result<()> {
        if let Some(worker) = self.chat_worker.take() {
            worker.abort();
        }
        let profile = self.profile.clone();
        let message_pairs = build_chat_messages(&question, &profile.model.name);
        let messages = message_pairs
            .into_iter()
            .map(|(role, content)| ChatMessage {
                role: role.clone(),
                content: Some(content.clone()),
                name: None,
                tool_calls: None,
                tool_call_id: None,
            })
            .collect();
        let client = ModelClient::from_profile(profile)?;
        let (event_tx, event_rx) = mpsc::channel(1);
        self.chat_event_rx = Some(event_rx);
        self.chat_in_flight = true;
        self.chat_worker = Some(tokio::spawn(async move {
            let event = match client.chat(messages, None, None).await {
                Ok(result) => ChatEvent::Completed(result),
                Err(error) => ChatEvent::Failed(error.to_string()),
            };
            let _ = event_tx.send(event).await;
        }));
        Ok(())
    }

    pub fn observe_chat_event(&mut self, event: ChatEvent) {
        self.chat_in_flight = false;
        self.chat_event_rx = None;
        self.chat_worker = None;
        match event {
            ChatEvent::Completed(result) => {
                self.global_input_tokens = self
                    .global_input_tokens
                    .saturating_add(u64::from(result.usage.prompt_tokens));
                self.global_output_tokens = self
                    .global_output_tokens
                    .saturating_add(u64::from(result.usage.completion_tokens));
                self.global_model_calls = self.global_model_calls.saturating_add(1);
                self.timeline.push(TimelineEntry {
                    role: TimelineRole::Agent,
                    content: result.content,
                });
                self.input_notice = Some("Answer complete".to_string());
            }
            ChatEvent::Failed(error) => {
                self.timeline.push(TimelineEntry {
                    role: TimelineRole::Error,
                    content: format!("Answer failed · {error}"),
                });
                self.input_notice = Some("Answer failed".to_string());
            }
        }
        self.transcript_scroll_back = 0;
    }

    async fn execute_slash_command(&mut self, command: &str) -> Result<()> {
        let normalized = command.trim().to_ascii_lowercase();
        if let Some(raw_id) = normalized
            .strip_prefix("/show ")
            .or_else(|| normalized.strip_prefix("/view "))
        {
            let raw_id = raw_id.trim().trim_start_matches('t');
            match raw_id.parse::<usize>() {
                Ok(id) if self.open_timeline_entry(id) => {
                    self.clear_input(&format!("Opened transcript T{id:03}"));
                }
                _ => self.clear_input("Transcript ID not found"),
            }
            return Ok(());
        }
        let notice = match normalized.as_str() {
            "/q" | "/exit" => {
                self.should_quit = true;
                return Ok(());
            }
            "/stats" | "/plan" => {
                self.view = View::Stats;
                "Opened stats"
            }
            "/main" | "/chat" => {
                self.view = View::Main;
                self.transcript_detail_id = None;
                "Returned to main"
            }
            "/candidate" => {
                self.view = View::Candidate;
                "Opened candidate"
            }
            "/diagnostics" => {
                self.view = View::Diagnostics;
                "Opened diagnostics"
            }
            "/help" => {
                self.view = View::Help;
                "Opened help"
            }
            "/clear" => {
                self.timeline.clear();
                self.transcript_scroll_back = 0;
                self.transcript_detail_id = None;
                self.transcript_hitboxes.clear();
                "Main timeline cleared"
            }
            "/done" if self.run_state().state.is_active() => {
                self.clear_input("Task is still running; press Esc, then c to cancel before /done");
                return Ok(());
            }
            "/done" => {
                self.task_session_mode = TaskSessionMode::Detached;
                "Task session detached; use /new or /task … to attach"
            }
            "/new" if self.run_state().state.is_active() => {
                self.clear_input("Task is still running; press Esc, then c to cancel before /new");
                return Ok(());
            }
            "/new" => {
                self.discard_current_run().await?;
                self.task_session_mode = TaskSessionMode::Attached;
                "Ready for a new task"
            }
            "/panel" => {
                self.show_right_panel = !self.show_right_panel;
                if self.show_right_panel {
                    "Panel opened"
                } else {
                    "Panel closed"
                }
            }
            _ => {
                self.input_notice = Some(
                    "Unknown command; use /task … /ask … /done /new /show T### /main /stats /panel /candidate /diagnostics /help"
                        .to_string(),
                );
                return Ok(());
            }
        };
        self.input_buffer.clear();
        self.input_cursor = 0;
        self.input_cursor_visible = true;
        self.input_notice = Some(notice.to_string());
        Ok(())
    }

    /// Add durable, compact activity to the main conversation surface.
    pub fn observe_event(&mut self, event: &RunEvent) {
        match event {
            RunEvent::ModelCompleted(result) => {
                self.candidate = result.content.clone();
                let prompt = u64::from(result.usage.prompt_tokens);
                self.record_prompt_observation(prompt);
                self.global_input_tokens = self.global_input_tokens.saturating_add(prompt);
                self.global_output_tokens = self
                    .global_output_tokens
                    .saturating_add(u64::from(result.usage.completion_tokens));
                self.global_model_calls = self.global_model_calls.saturating_add(1);
            }
            RunEvent::ModelUsageRecorded(usage) => {
                self.record_prompt_observation(u64::from(usage.prompt_tokens));
                self.global_input_tokens = self
                    .global_input_tokens
                    .saturating_add(u64::from(usage.prompt_tokens));
                self.global_output_tokens = self
                    .global_output_tokens
                    .saturating_add(u64::from(usage.completion_tokens));
                self.global_model_calls = self.global_model_calls.saturating_add(1);
            }
            _ => {}
        }
        let entry = match event {
            RunEvent::TaskLoaded(task) => {
                Some((TimelineRole::Activity, format!("Loaded task {}", task.name)))
            }
            RunEvent::ModelCompleted(result) => Some((
                TimelineRole::Agent,
                compact_candidate_message(&result.content),
            )),
            RunEvent::ModelFailed(error) | RunEvent::Failed(error) => {
                Some((TimelineRole::Error, error.to_string()))
            }
            RunEvent::ValidationCompleted(result) => {
                let status = if result.passed { "passed" } else { "failed" };
                let role = if result.passed {
                    TimelineRole::Activity
                } else {
                    TimelineRole::Error
                };
                // Main summary line
                self.timeline.push(TimelineEntry {
                    role: role.clone(),
                    content: format!(
                        "L{} {} {} · {} errors · {} warnings",
                        result.level,
                        result.level_name,
                        status,
                        result.error_count,
                        result.warning_count
                    ),
                });
                // Show actionable errors (up to 5 bullet points)
                if !result.passed {
                    for err in result.actionable_errors.iter().take(5) {
                        self.timeline.push(TimelineEntry {
                            role: TimelineRole::Error,
                            content: format!("  ▸ {err}"),
                        });
                    }
                    // Push the full diagnostic as a separate, clearly-labelled entry
                    // so the user knows exactly where to look. The transcript renderer
                    // will compress it to one line, but the ID is shown — the user
                    // can run /show T### (or click) to read the complete report.
                    if !result.diagnostic.is_empty() {
                        let entry_id = self.timeline.len() + 1;
                        self.timeline.push(TimelineEntry {
                            role: TimelineRole::Error,
                            content: format!(
                                "  Full diagnostic [T{entry_id:03}] — use /show T{entry_id:03} to expand\n{}",
                                result.diagnostic
                            ),
                        });
                    }
                }
                None
            }
            RunEvent::FinalArtifactWritten(path) => Some((TimelineRole::Success, {
                self.reusable_workspace = true;
                format!("Task complete · Artifact {}", path.display())
            })),
            RunEvent::ConsentRequired { action } => Some((
                TimelineRole::Activity,
                format!("Awaiting consent: {action}"),
            )),
            RunEvent::ToolProcessStarted { command, .. } => {
                Some((TimelineRole::Activity, format!("$ {}", command)))
            }
            RunEvent::ToolProcessFinished { id, success, .. } => self
                .run_state()
                .tool_processes
                .iter()
                .find(|process| process.id == *id)
                .map(|process| {
                    (
                        if *success {
                            TimelineRole::Success
                        } else {
                            TimelineRole::Error
                        },
                        format!(
                            "Tool {} · $ {}",
                            if *success { "complete" } else { "failed" },
                            process.command
                        ),
                    )
                }),
            RunEvent::PreflightPassed(budget) => Some((
                TimelineRole::Activity,
                format!(
                    "Preflight passed · estimated {} / limit {}",
                    budget.estimated_prompt, budget.prompt_limit
                ),
            )),
            RunEvent::PreflightFailed(reason) => {
                Some((TimelineRole::Error, format!("Preflight failed · {reason}")))
            }
            RunEvent::TaskLoadFailed(reason) => {
                Some((TimelineRole::Error, format!("Task load failed · {reason}")))
            }
            RunEvent::ConsentDenied(reason) => {
                Some((TimelineRole::Error, format!("Consent denied · {reason}")))
            }
            _ => None,
        };
        if let Some((role, content)) = entry {
            self.timeline.push(TimelineEntry { role, content });
        }
    }

    fn record_prompt_observation(&mut self, prompt_tokens: u64) {
        self.latest_prompt_tokens = prompt_tokens;
        self.max_observed_prompt_tokens = self.max_observed_prompt_tokens.max(prompt_tokens);
    }

    pub fn insert_input_char(&mut self, character: char) {
        let byte_index = self.input_byte_index();
        self.input_buffer.insert(byte_index, character);
        self.input_cursor += 1;
        self.input_cursor_visible = true;
        self.input_notice = None;
    }

    pub fn insert_input_newline(&mut self) {
        self.insert_input_char('\n');
    }

    pub fn backspace_input(&mut self) {
        if self.input_cursor == 0 {
            return;
        }
        let end = self.input_byte_index();
        self.input_cursor -= 1;
        let start = self.input_byte_index();
        self.input_buffer.replace_range(start..end, "");
        self.input_cursor_visible = true;
        self.input_notice = None;
    }

    pub fn delete_input(&mut self) {
        if self.input_cursor >= self.input_buffer.chars().count() {
            return;
        }
        let start = self.input_byte_index();
        let end = self
            .input_buffer
            .char_indices()
            .nth(self.input_cursor + 1)
            .map(|(index, _)| index)
            .unwrap_or(self.input_buffer.len());
        self.input_buffer.replace_range(start..end, "");
        self.input_cursor_visible = true;
        self.input_notice = None;
    }

    pub fn move_input_left(&mut self) {
        self.input_cursor = self.input_cursor.saturating_sub(1);
        self.input_cursor_visible = true;
    }

    pub fn move_input_right(&mut self) {
        self.input_cursor = (self.input_cursor + 1).min(self.input_buffer.chars().count());
        self.input_cursor_visible = true;
    }

    pub fn move_input_home(&mut self) {
        self.input_cursor = 0;
        self.input_cursor_visible = true;
    }

    pub fn move_input_end(&mut self) {
        self.input_cursor = self.input_buffer.chars().count();
        self.input_cursor_visible = true;
    }

    fn input_byte_index(&self) -> usize {
        self.input_buffer
            .char_indices()
            .nth(self.input_cursor)
            .map(|(index, _)| index)
            .unwrap_or(self.input_buffer.len())
    }

    pub fn run_state(&self) -> &RunState {
        self.controller
            .as_ref()
            .map(|c| &c.state)
            .unwrap_or(&self.dummy_run)
    }

    pub fn open_timeline_entry(&mut self, id: usize) -> bool {
        if id == 0 || id > self.timeline.len() {
            return false;
        }
        self.transcript_detail_id = Some(id);
        self.view = View::TranscriptDetail;
        self.scroll_offset = 0;
        true
    }

    pub fn open_timeline_entry_at(&mut self, column: u16, row: u16) -> bool {
        let Some(id) = self
            .transcript_hitboxes
            .iter()
            .find(|hitbox| hitbox.row == row && column >= hitbox.left && column < hitbox.right)
            .map(|hitbox| hitbox.id)
        else {
            return false;
        };
        self.open_timeline_entry(id)
    }

    pub fn close_transcript_detail(&mut self) -> bool {
        if self.view != View::TranscriptDetail {
            return false;
        }
        self.view = View::Main;
        self.transcript_detail_id = None;
        self.scroll_offset = 0;
        true
    }

    pub fn timeline_detail(&self) -> Option<(usize, &TimelineEntry)> {
        let id = self.transcript_detail_id?;
        self.timeline
            .get(id.checked_sub(1)?)
            .map(|entry| (id, entry))
    }

    /// Completed steps and total steps in the first-class run plan.
    pub fn plan_progress(&self) -> (usize, usize) {
        let run = self.run_state();
        let completed = run
            .plan
            .iter()
            .filter(|step| matches!(step.status, agent_core::shared::PlanStepStatus::Completed))
            .count();
        (completed, run.plan.len())
    }
}

fn compact_candidate_message(content: &str) -> String {
    let lines = content.lines().collect::<Vec<_>>();
    let mut message = format!("Generated candidate · {} lines", lines.len());
    if !content.is_empty() {
        message.push('\n');
        message.push_str(content);
    }
    message
}

impl Drop for App {
    fn drop(&mut self) {
        if let Some(worker) = self.executor_worker.take() {
            worker.abort();
        }
        if let Some(worker) = self.chat_worker.take() {
            worker.abort();
        }
        if let Some(session_id) = self.remote_session_id.as_deref() {
            tracing::warn!(
                target = %self.remote_target.name,
                session = %session_id,
                "TUI dropped without completing async detach; terminating the SSH transport and retaining the durable runner session"
            );
        }
    }
}

fn classify_input(prompt: &str, task_session_mode: TaskSessionMode) -> InputIntent {
    let trimmed = prompt.trim();
    let lower = trimmed.to_ascii_lowercase();
    if lower.starts_with("/task ") {
        return InputIntent::Task(trimmed["/task ".len()..].trim().to_string());
    }
    if lower.starts_with("/ask ") {
        return InputIntent::Chat(trimmed["/ask ".len()..].trim().to_string());
    }
    if lower.starts_with("/chat ") {
        return InputIntent::Chat(trimmed["/chat ".len()..].trim().to_string());
    }

    match task_session_mode {
        TaskSessionMode::Attached => InputIntent::Task(trimmed.to_string()),
        TaskSessionMode::Detached => InputIntent::Chat(trimmed.to_string()),
    }
}

fn existing_task_package(input: &str) -> Option<std::path::PathBuf> {
    let input = input.trim().strip_prefix('@').unwrap_or(input.trim());
    let path = std::path::PathBuf::from(input);
    (path.is_dir() && path.join("task.md").is_file()).then_some(path)
}

fn short_session_id(session_id: &str) -> &str {
    session_id.get(..8).unwrap_or(session_id)
}

#[cfg(test)]
pub(crate) fn test_remote_target() -> ResolvedRemoteTarget {
    let mut ssh = tool_runner::ssh_backend::SshTargetConfig::new(
        "klintcode-test-target",
        "/nonexistent/klintcode-runner",
    );
    ssh.ssh_program = PathBuf::from("/nonexistent/ssh");
    ResolvedRemoteTarget {
        name: "test-ssh".to_string(),
        ssh,
        client_policy: tool_runner::remote_protocol::ClientPolicy::default(),
        environment_refs: std::collections::BTreeMap::new(),
        runner_launch: pipeline::remote_config::RunnerLaunchConfig {
            target_triple: "x86_64-unknown-linux-musl".to_string(),
            remote_session_root: "/tmp/klintcode-test-sessions".to_string(),
            remote_hard_policy_path: "/tmp/klintcode-test-policy.toml".to_string(),
        },
    }
}

#[cfg(test)]
impl App {
    pub(crate) fn new_for_test(profile_path: Option<&Path>) -> Result<Self> {
        Self::new(profile_path, test_remote_target(), None)
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use agent_core::state::PipelineState;

    #[test]
    fn composer_edits_unicode_at_character_boundaries() {
        let mut app = App::new_for_test(None).expect("app");
        app.insert_input_char('\u{6539}');
        app.insert_input_char('A');
        app.move_input_left();
        app.insert_input_char('\u{597d}');

        assert_eq!(app.input_buffer, "\u{6539}\u{597d}A");
        assert_eq!(app.input_cursor, 2);

        app.backspace_input();
        assert_eq!(app.input_buffer, "\u{6539}A");
        app.delete_input();
        assert_eq!(app.input_buffer, "\u{6539}");
    }

    #[test]
    fn finished_tool_is_added_to_the_main_timeline_without_exit_code() {
        let mut app = App::new_for_test(None).expect("app");
        app.dummy_run
            .start_tool_process(4, "cargo check".to_string());

        app.observe_event(&RunEvent::ToolProcessFinished {
            id: 4,
            success: false,
            exit_code: Some(101),
        });

        let entry = app.timeline.last().expect("tool timeline entry");
        assert_eq!(entry.role, TimelineRole::Error);
        assert_eq!(entry.content, "Tool failed · $ cargo check");
        assert!(!entry.content.contains("101"));
    }

    #[test]
    fn candidate_timeline_entry_is_bounded_and_points_to_full_view() {
        let content = (1..=20)
            .map(|line| format!("candidate-line-{line}"))
            .collect::<Vec<_>>()
            .join("\n");

        let compact = compact_candidate_message(&content);

        assert!(compact.contains("Generated candidate · 20 lines"));
        assert!(compact.contains("candidate-line-1"));
        assert!(compact.contains("candidate-line-20"));
        assert_eq!(compact.lines().count(), 21);
    }

    #[tokio::test]
    async fn show_command_opens_full_transcript_entry_by_id() {
        let mut app = App::new_for_test(None).expect("app");
        app.timeline.push(TimelineEntry {
            role: TimelineRole::Agent,
            content: "line one\nline two".to_string(),
        });
        app.input_buffer = "/show T001".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        app.submit_input().await.expect("open transcript entry");

        assert_eq!(app.view, View::TranscriptDetail);
        assert_eq!(
            app.timeline_detail().unwrap().1.content,
            "line one\nline two"
        );
        app.scroll_offset = 7;
        assert!(app.close_transcript_detail());
        assert_eq!(app.view, View::Main);
        assert_eq!(app.scroll_offset, 0);
    }

    #[test]
    fn final_artifact_is_a_clear_success_timeline_entry() {
        let mut app = App::new_for_test(None).expect("app");

        app.observe_event(&RunEvent::FinalArtifactWritten(
            "runs/run-1/final-artifact".into(),
        ));

        let entry = app.timeline.last().expect("completion entry");
        assert_eq!(entry.role, TimelineRole::Success);
        assert_eq!(
            entry.content,
            "Task complete · Artifact runs/run-1/final-artifact"
        );
    }

    #[test]
    fn global_token_metrics_accumulate_across_model_events() {
        let mut app = App::new_for_test(None).expect("app");
        let result = ModelResult {
            content: "response".to_string(),
            reasoning_content: None,
            tool_calls: None,
            finish_reason: "stop".to_string(),
            usage: TokenUsage {
                prompt_tokens: 100,
                completion_tokens: 50,
                total_tokens: 150,
            },
            elapsed_secs: 1.0,
            http_status: 200,
        };

        app.observe_event(&RunEvent::ModelCompleted(result));
        app.observe_event(&RunEvent::ModelUsageRecorded(TokenUsage {
            prompt_tokens: 200,
            completion_tokens: 10,
            total_tokens: 210,
        }));

        assert_eq!(app.global_input_tokens, 300);
        assert_eq!(app.global_output_tokens, 60);
        assert_eq!(app.global_model_calls, 2);
        assert_eq!(app.latest_prompt_tokens, 200);
        assert_eq!(app.max_observed_prompt_tokens, 200);
    }

    #[tokio::test]
    async fn active_run_rejects_a_second_submission_without_losing_draft() {
        let mut app = App::new_for_test(None).expect("app");
        app.input_buffer = "next task".to_string();
        app.input_cursor = app.input_buffer.chars().count();
        app.dummy_run.state = PipelineState::Generating { attempt: 1 };

        app.submit_input().await.expect("submission check");

        assert_eq!(app.input_buffer, "next task");
        assert!(
            app.input_notice
                .as_deref()
                .expect("notice")
                .contains("already running")
        );
    }

    #[tokio::test]
    async fn completed_run_routes_task_to_existing_follow_up_executor() {
        let mut app = App::new_for_test(None).expect("app");
        let (side_effect_tx, _side_effect_rx) = mpsc::channel(4);
        let (mut controller, _controller_event_tx) = RunController::new(
            "existing-run".to_string(),
            app.profile.run.max_repairs,
            side_effect_tx,
        );
        controller.state.state = PipelineState::Completed;
        controller.state.current_attempt = 1;
        let (executor_effect_tx, mut executor_effect_rx) = mpsc::channel(4);

        app.controller = Some(controller);
        app.executor_effect_tx = Some(executor_effect_tx);
        app.reusable_workspace = true;
        app.input_buffer = "add another query".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        app.submit_input().await.expect("submit follow-up");
        let effect = executor_effect_rx.recv().await.expect("follow-up effect");

        assert!(matches!(
            effect,
            SideEffect::RunFollowUp { ref task, attempt: 2 }
                if task == "add another query"
        ));
        assert_eq!(app.run_state().run_id, "existing-run");
        assert!(app.executor_effect_tx.is_some());
        assert!(app.input_buffer.is_empty());
    }

    struct DropSignal(std::sync::Arc<std::sync::atomic::AtomicBool>);

    impl Drop for DropSignal {
        fn drop(&mut self) {
            self.0.store(true, std::sync::atomic::Ordering::SeqCst);
        }
    }

    struct TestEffectHandler {
        events: mpsc::UnboundedSender<&'static str>,
        active_effect_dropped: std::sync::Arc<std::sync::atomic::AtomicBool>,
    }

    impl SideEffectHandler for TestEffectHandler {
        fn handle_effect(
            &mut self,
            effect: SideEffect,
        ) -> Pin<Box<dyn Future<Output = ()> + Send + '_>> {
            let events = self.events.clone();
            let dropped = self.active_effect_dropped.clone();
            Box::pin(async move {
                match effect {
                    SideEffect::RunPreflight => {
                        let _drop_signal = DropSignal(dropped);
                        let _ = events.send("preflight-started");
                        std::future::pending::<()>().await;
                    }
                    SideEffect::Generate { .. } => {
                        let _ = events.send("stale-generate");
                    }
                    SideEffect::RunFollowUp { .. } => {
                        let _ = events.send("follow-up");
                    }
                    _ => {
                        let _ = events.send("other");
                    }
                }
            })
        }
    }

    #[tokio::test]
    async fn app_cancel_stops_worker_drains_old_events_and_retains_follow_up_executor() {
        use std::sync::atomic::Ordering;

        let mut app = App::new_for_test(None).expect("app");
        let (controller_side_effect_tx, _controller_side_effect_rx) = mpsc::channel(4);
        let (mut controller, controller_event_tx) = RunController::new(
            "reusable-run".to_string(),
            app.profile.run.max_repairs,
            controller_side_effect_tx,
        );
        controller.state.state = PipelineState::FollowUpValidation { attempt: 2 };
        controller.state.current_attempt = 2;

        // This event belongs to the effect that is about to be cancelled. It
        // must not complete the next follow-up after cancellation.
        controller_event_tx
            .send(RunEvent::ValidationCompleted(ValidationResult {
                level: 5,
                level_name: "stale".to_string(),
                passed: true,
                error_count: 0,
                warning_count: 0,
                suggestion_count: 0,
                actionable_errors: vec![],
                structured_errors: vec![],
                diagnostic: String::new(),
                elapsed_secs: 0.0,
            }))
            .await
            .unwrap();

        let (proxy_tx, proxy_rx) = mpsc::channel(4);
        proxy_tx
            .send(RunEvent::ModelToken("stale".to_string()))
            .await
            .unwrap();
        let (effect_tx, effect_rx) = mpsc::channel(4);
        let (control_tx, control_rx) = mpsc::channel(1);
        let (events_tx, mut events_rx) = mpsc::unbounded_channel();
        let dropped = std::sync::Arc::new(std::sync::atomic::AtomicBool::new(false));
        let worker = tokio::spawn(run_executor_worker(
            TestEffectHandler {
                events: events_tx,
                active_effect_dropped: dropped.clone(),
            },
            effect_rx,
            control_rx,
        ));

        app.controller = Some(controller);
        app.proxy_event_rx = Some(proxy_rx);
        app.executor_effect_tx = Some(effect_tx.clone());
        app.executor_control_tx = Some(control_tx);
        app.executor_worker = Some(worker);
        app.reusable_workspace = true;

        effect_tx.send(SideEffect::RunPreflight).await.unwrap();
        assert_eq!(events_rx.recv().await, Some("preflight-started"));
        effect_tx
            .send(SideEffect::Generate { attempt: 99 })
            .await
            .unwrap();

        assert!(app.cancel_current_task().await.unwrap());
        assert!(dropped.load(Ordering::SeqCst));
        assert_eq!(app.run_state().state, PipelineState::Cancelled);

        app.input_buffer = "continue safely".to_string();
        app.input_cursor = app.input_buffer.chars().count();
        app.submit_input().await.unwrap();
        assert_eq!(events_rx.recv().await, Some("follow-up"));
        assert!(
            events_rx.try_recv().is_err(),
            "queued generate effect ran after cancel"
        );
    }

    #[tokio::test]
    async fn effect_dispatch_filters_none_and_applies_backpressure_without_dropping() {
        let mut app = App::new_for_test(None).expect("app");
        let (effect_tx, mut effect_rx) = mpsc::channel(1);
        app.executor_effect_tx = Some(effect_tx);

        assert!(!app.dispatch_side_effect(SideEffect::None).await.unwrap());
        assert!(effect_rx.try_recv().is_err());

        assert!(
            app.dispatch_side_effect(SideEffect::RunPreflight)
                .await
                .unwrap()
        );
        let mut blocked = Box::pin(app.dispatch_side_effect(SideEffect::Generate { attempt: 1 }));
        assert!(
            tokio::time::timeout(std::time::Duration::from_millis(10), &mut blocked)
                .await
                .is_err(),
            "a full effect queue must exert backpressure"
        );
        assert!(matches!(
            effect_rx.recv().await,
            Some(SideEffect::RunPreflight)
        ));
        assert!(blocked.await.unwrap());
        assert!(matches!(
            effect_rx.recv().await,
            Some(SideEffect::Generate { attempt: 1 })
        ));
    }

    #[tokio::test]
    async fn direct_tui_reduction_cannot_deadlock_on_a_full_controller_event_queue() {
        let mut app = App::new_for_test(None).unwrap();
        let (unused_side_effect_tx, _unused_side_effect_rx) = mpsc::channel(1);
        let (mut controller, controller_event_tx) = RunController::new(
            "full-controller-queue".to_string(),
            app.profile.run.max_repairs,
            unused_side_effect_tx,
        );
        controller.state.state = PipelineState::Preflight;
        for index in 0..64 {
            controller_event_tx
                .try_send(RunEvent::ModelToken(format!("buffered-{index}")))
                .expect("fill the controller event queue exactly to capacity");
        }
        assert!(
            controller_event_tx
                .try_send(RunEvent::ModelToken("overflow".into()))
                .is_err()
        );

        let (executor_effect_tx, mut executor_effect_rx) = mpsc::channel(1);
        app.controller = Some(controller);
        app.executor_effect_tx = Some(executor_effect_tx);
        let budget = agent_core::event::ContextBudget {
            model_context: 65_536,
            prompt_limit: 48_000,
            completion_limit: 4_096,
            safety_reserve: 8_192,
            estimated_prompt: 1_000,
        };

        let effect = tokio::time::timeout(
            std::time::Duration::from_millis(100),
            app.process_controller_event(RunEvent::PreflightPassed(budget)),
        )
        .await
        .expect("direct reducer path must not await its own full event queue")
        .unwrap();

        assert!(matches!(effect, SideEffect::Generate { attempt: 1 }));
        assert!(matches!(
            executor_effect_rx.recv().await,
            Some(SideEffect::Generate { attempt: 1 })
        ));
    }

    #[tokio::test]
    async fn fresh_task_package_fails_closed_when_ssh_is_unavailable() {
        let package = tempfile::tempdir().unwrap();
        std::fs::write(package.path().join("task.md"), "Build a tiny school model").unwrap();
        std::fs::write(
            package.path().join("acceptance.json"),
            r#"{"build_targets":["rust-lib-core"]}"#,
        )
        .unwrap();

        let profile_path =
            PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../profiles/simulator.toml");
        let mut app = App::new_for_test(Some(&profile_path)).unwrap();
        app.profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR"))
                .join("../../simulator/scenarios/happy-path.toml"),
        );
        let (side_effect_tx, _side_effect_rx) = mpsc::channel(4);
        let (mut controller, _controller_event_tx) = RunController::new(
            "old-run".to_string(),
            app.profile.run.max_repairs,
            side_effect_tx,
        );
        controller.state.state = PipelineState::Completed;
        let (old_effect_tx, _old_effect_rx) = mpsc::channel(4);
        app.controller = Some(controller);
        app.executor_effect_tx = Some(old_effect_tx);
        app.reusable_workspace = true;
        app.input_buffer = format!("/task {}", package.path().display());
        app.input_cursor = app.input_buffer.chars().count();

        let error = app
            .submit_input()
            .await
            .expect_err("SSH infrastructure failure must stop the task");

        assert!(error.to_string().contains("[infrastructure]"));
        assert!(error.to_string().contains("SSH execution target"));
        assert!(app.controller.is_none());
        assert!(app.executor_effect_tx.is_none());
        assert!(app.remote_execution.is_none());
        assert!(app.remote_session_id.is_none());
        assert!(!app.reusable_workspace);
    }

    #[tokio::test]
    async fn failed_run_never_falls_back_to_a_local_executor() {
        let profile_path =
            PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../profiles/simulator.toml");
        let mut app = App::new_for_test(Some(&profile_path)).unwrap();
        app.profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR"))
                .join("../../simulator/scenarios/happy-path.toml"),
        );
        let (side_effect_tx, _side_effect_rx) = mpsc::channel(4);
        let (mut controller, _controller_event_tx) = RunController::new(
            "failed-before-workspace".to_string(),
            app.profile.run.max_repairs,
            side_effect_tx,
        );
        controller.state.state = PipelineState::Failed {
            error: "preflight failed".to_string(),
        };
        let (old_effect_tx, _old_effect_rx) = mpsc::channel(4);
        app.controller = Some(controller);
        app.executor_effect_tx = Some(old_effect_tx);
        app.input_buffer = "start a small Rust model".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        let error = app
            .submit_input()
            .await
            .expect_err("unavailable SSH runner must stop a fresh task");

        assert!(error.to_string().contains("[infrastructure]"));
        assert!(app.controller.is_none());
        assert!(app.executor_effect_tx.is_none());
        assert!(app.proxy_event_rx.is_none());
        assert!(app.remote_session_id.is_none());
    }

    #[tokio::test]
    async fn failed_resume_is_not_silently_replaced_with_a_new_session() {
        let mut app = App::new_for_test(None).expect("app");
        app.resume_session = Some("durable-session-42".to_string());
        app.input_buffer = "continue the remote task".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        let error = app
            .submit_input()
            .await
            .expect_err("failed attach must stop instead of creating a replacement");

        assert!(error.to_string().contains("[infrastructure]"));
        assert_eq!(app.resume_session.as_deref(), Some("durable-session-42"));
        assert!(app.remote_session_id.is_none());
        assert!(app.remote_execution.is_none());
    }

    #[test]
    fn lightweight_chat_usage_does_not_pollute_task_totals() {
        let mut app = App::new_for_test(None).unwrap();
        app.latest_prompt_tokens = 7;
        app.max_observed_prompt_tokens = 9;
        app.observe_chat_event(ChatEvent::Completed(ModelResult {
            content: "answer".to_string(),
            reasoning_content: None,
            tool_calls: None,
            finish_reason: "stop".to_string(),
            usage: TokenUsage {
                prompt_tokens: 12,
                completion_tokens: 4,
                total_tokens: 16,
            },
            elapsed_secs: 0.1,
            http_status: 200,
        }));

        assert_eq!(app.global_input_tokens, 12);
        assert_eq!(app.global_output_tokens, 4);
        assert_eq!(app.global_model_calls, 1);
        assert_eq!(app.run_state().token_totals.model_calls, 0);
        assert!(app.run_state().last_model_usage.is_none());
        assert_eq!(app.latest_prompt_tokens, 7);
        assert_eq!(app.max_observed_prompt_tokens, 9);
    }

    #[test]
    fn task_package_path_requires_a_directory_with_task_markdown() {
        let package = tempfile::tempdir().expect("task package");
        assert!(existing_task_package(package.path().to_str().unwrap()).is_none());
        std::fs::write(package.path().join("task.md"), "task").unwrap();
        assert_eq!(
            existing_task_package(&format!("@{}", package.path().display())),
            Some(package.path().to_path_buf())
        );
    }

    #[tokio::test]
    async fn new_command_discards_completed_run_before_next_task() {
        let mut app = App::new_for_test(None).expect("app");
        let (side_effect_tx, _side_effect_rx) = mpsc::channel(4);
        let (mut controller, _controller_event_tx) = RunController::new(
            "completed-run".to_string(),
            app.profile.run.max_repairs,
            side_effect_tx,
        );
        controller.state.state = PipelineState::Completed;
        let (executor_effect_tx, _executor_effect_rx) = mpsc::channel(4);
        app.controller = Some(controller);
        app.executor_effect_tx = Some(executor_effect_tx);
        app.task_session_mode = TaskSessionMode::Detached;
        app.candidate = "old candidate".to_string();
        app.diagnostics = "old diagnostics".to_string();

        app.execute_slash_command("/new").await.unwrap();

        assert!(app.controller.is_none());
        assert!(app.executor_effect_tx.is_none());
        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        assert!(matches!(app.run_state().state, PipelineState::Idle));
        assert!(app.candidate.is_empty());
        assert!(app.diagnostics.is_empty());
        assert_eq!(app.input_notice.as_deref(), Some("Ready for a new task"));
    }

    #[tokio::test]
    async fn new_command_refuses_to_detach_an_active_remote_task() {
        let mut app = App::new_for_test(None).expect("app");
        app.dummy_run.state = PipelineState::BuildValidation { attempt: 1 };

        app.execute_slash_command("/new").await.unwrap();

        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        assert!(matches!(
            app.run_state().state,
            PipelineState::BuildValidation { attempt: 1 }
        ));
        assert!(
            app.input_notice
                .as_deref()
                .expect("active task notice")
                .contains("cancel before /new")
        );
    }

    #[test]
    fn input_router_uses_explicit_session_mode_instead_of_keywords() {
        assert_eq!(
            classify_input("which model?", TaskSessionMode::Attached),
            InputIntent::Task("which model?".to_string())
        );
        assert_eq!(
            classify_input(
                "why does this need an isolated environment?",
                TaskSessionMode::Attached,
            ),
            InputIntent::Task("why does this need an isolated environment?".to_string())
        );
        assert_eq!(
            classify_input("fix input routing and add tests", TaskSessionMode::Detached,),
            InputIntent::Chat("fix input routing and add tests".to_string())
        );
        assert_eq!(
            classify_input(
                "/ask what is the difference between implementation and repair?",
                TaskSessionMode::Attached,
            ),
            InputIntent::Chat(
                "what is the difference between implementation and repair?".to_string()
            )
        );
        assert_eq!(
            classify_input("/chat explain the existing code", TaskSessionMode::Attached,),
            InputIntent::Chat("explain the existing code".to_string())
        );
        assert_eq!(
            classify_input("/task explain the existing code", TaskSessionMode::Detached,),
            InputIntent::Task("explain the existing code".to_string())
        );

        let cjk_task = "\u{4fee}\u{590d}\u{8f93}\u{5165}\u{8def}\u{7531}";
        assert_eq!(
            classify_input(cjk_task, TaskSessionMode::Attached),
            InputIntent::Task(cjk_task.to_string())
        );
    }

    #[tokio::test]
    async fn done_detaches_only_after_the_active_task_stops() {
        let mut app = App::new_for_test(None).expect("app");
        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        app.dummy_run.state = PipelineState::Generating { attempt: 1 };

        app.execute_slash_command("/done").await.unwrap();

        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        assert!(
            app.input_notice
                .as_deref()
                .expect("active task notice")
                .contains("cancel before /done")
        );

        app.dummy_run.state = PipelineState::Cancelled;
        app.execute_slash_command("/done").await.unwrap();

        assert_eq!(app.task_session_mode, TaskSessionMode::Detached);
        assert!(app.input_notice.as_deref().unwrap().contains("detached"));
    }

    #[tokio::test]
    async fn ask_command_is_one_shot_chat_without_detaching_the_task() {
        let mut app = App::new_for_test(None).expect("app");
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../simulator/scenarios/chat.toml"),
        );
        app.profile = profile;
        app.dummy_run.state = PipelineState::Generating { attempt: 1 };
        app.input_buffer = "/ask Who are you?".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        app.submit_input().await.expect("submit chat");

        assert!(app.controller.is_none());
        assert!(app.chat_in_flight);
        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        let event = app
            .chat_event_rx
            .as_mut()
            .expect("chat event receiver")
            .recv()
            .await
            .expect("simulated chat response");
        app.observe_chat_event(event);

        assert!(!app.chat_in_flight);
        assert_eq!(
            app.timeline.last().expect("chat answer").content,
            "I am Klint, an isolated-environment coding agent built by TeaQL."
        );
        assert_eq!(app.dummy_run.token_totals.input_tokens, 0);
        assert_eq!(app.dummy_run.token_totals.output_tokens, 0);
        assert_eq!(app.global_input_tokens, 80);
        assert_eq!(app.global_output_tokens, 18);
        assert_eq!(app.global_model_calls, 1);
    }

    #[tokio::test]
    async fn task_command_reattaches_logically_but_does_not_fallback_when_ssh_fails() {
        let mut app = App::new_for_test(None).expect("app");
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR"))
                .join("../../simulator/scenarios/happy-path.toml"),
        );
        app.profile = profile;
        app.task_session_mode = TaskSessionMode::Detached;
        app.input_buffer = "/task add a school entity".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        let error = app
            .submit_input()
            .await
            .expect_err("SSH failure must remain visible");

        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        assert!(error.to_string().contains("[infrastructure]"));
        assert!(app.controller.is_none());
        assert!(app.executor_effect_tx.is_none());
        assert!(app.remote_session_id.is_none());
    }

    #[tokio::test]
    async fn submitted_text_requires_the_configured_ssh_execution_plane() {
        let mut app = App::new_for_test(None).expect("app");
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR"))
                .join("../../simulator/scenarios/happy-path.toml"),
        );
        app.profile = profile;
        app.input_buffer = "add a school entity".to_string();
        app.input_cursor = app.input_buffer.chars().count();

        let error = app
            .submit_input()
            .await
            .expect_err("inline tasks require SSH");

        assert!(error.to_string().contains("[infrastructure]"));
        assert_eq!(app.task_session_mode, TaskSessionMode::Attached);
        assert!(app.controller.is_none());
        assert!(app.executor_effect_tx.is_none());
        assert!(app.remote_session_id.is_none());
    }

    #[tokio::test]
    async fn slash_commands_switch_surfaces_without_starting_a_run() {
        let mut app = App::new_for_test(None).expect("app");
        app.input_buffer = "/stats".to_string();
        app.input_cursor = app.input_buffer.len();

        app.submit_input().await.expect("switch to stats");
        assert_eq!(app.view, View::Stats);
        assert!(app.controller.is_none());

        app.input_buffer = "/main".to_string();
        app.input_cursor = app.input_buffer.len();
        app.submit_input().await.expect("switch to main");
        assert_eq!(app.view, View::Main);
        assert!(app.controller.is_none());
    }

    #[tokio::test]
    async fn simulator_health_probe_validates_the_configured_scenario() {
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.scenario = Some(
            PathBuf::from(env!("CARGO_MANIFEST_DIR"))
                .join("../../simulator/scenarios/happy-path.toml"),
        );

        let health = App::probe_model_service(profile).await;

        assert_eq!(health, ServiceHealth::Healthy);
    }
}
