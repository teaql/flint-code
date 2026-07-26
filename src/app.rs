//! Application state and event loop.

use anyhow::Result;
use crate::config::AgentConfig;
use crate::context::{ContextManager, Role};
use crate::llm::LlmClient;
use crate::workspace::WorkspaceState;

/// Active panel in the TUI
#[derive(Debug, Clone, Copy, PartialEq)]
pub enum ActivePanel {
    Chat,
    FileTree,
    Editor,
    TokenBudget,
    CommandPalette,
}

/// Application mode
#[derive(Debug, Clone, Copy, PartialEq)]
pub enum AppMode {
    Normal,
    Insert,
    Command,
    Help,
}

/// Agent execution status
#[derive(Debug, Clone, PartialEq)]
pub enum AgentStatus {
    Idle,
    Thinking,
    Streaming(String),
    Executing(String),
    Error(String),
    ContextWarning(String),
}

pub struct App {
    pub config: AgentConfig,
    pub context: ContextManager,
    pub llm: LlmClient,
    pub workspace: WorkspaceState,
    pub active_panel: ActivePanel,
    pub mode: AppMode,
    pub status: AgentStatus,
    pub input_buffer: String,
    pub input_cursor: usize,
    pub chat_scroll: u16,
    pub should_quit: bool,
    pub show_help: bool,
    pub command_history: Vec<String>,
    pub history_index: Option<usize>,
    pub streaming_response: String,
    pub session_id: String,
}

impl App {
    pub fn new(config: AgentConfig) -> Result<Self> {
        let system_prompt = build_system_prompt(&config);
        let context = ContextManager::new(config.context_budget, system_prompt);
        let llm = LlmClient::new(
            config.llm_endpoint.clone(),
            config.model_name.clone(),
            config.temperature,
            config.max_output_tokens,
        );
        let workspace = WorkspaceState::new(config.workspace_root.clone());

        Ok(Self {
            config,
            context,
            llm,
            workspace,
            active_panel: ActivePanel::Chat,
            mode: AppMode::Normal,
            status: AgentStatus::Idle,
            input_buffer: String::new(),
            input_cursor: 0,
            chat_scroll: 0,
            should_quit: false,
            show_help: false,
            command_history: Vec::new(),
            history_index: None,
            streaming_response: String::new(),
            session_id: uuid::Uuid::new_v4().to_string(),
        })
    }

    /// Submit the current input to the LLM
    pub async fn submit_input(&mut self) -> Result<()> {
        let input = self.input_buffer.trim().to_string();
        if input.is_empty() {
            return Ok(());
        }

        // Check for slash commands
        if input.starts_with('/') {
            return self.handle_command(&input).await;
        }

        // Auto-compact if needed
        if self.context.needs_compaction() && self.config.auto_compact {
            let result = self.context.compact()?;
            self.status = AgentStatus::ContextWarning(
                format!("Context compacted: {}→{} tokens ({} msgs removed)",
                    result.tokens_before, result.tokens_after, result.messages_removed)
            );
        }

        // Add user message
        self.context.add_message(Role::User, input.clone(), 10);
        self.command_history.push(input);
        self.input_buffer.clear();
        self.input_cursor = 0;
        self.history_index = None;

        // Start streaming response
        self.status = AgentStatus::Thinking;
        self.streaming_response.clear();

        let messages = self.context.get_api_messages();
        let mut rx = self.llm.chat_stream(messages).await?;

        self.status = AgentStatus::Streaming(String::new());

        while let Some(event) = rx.recv().await {
            match event {
                crate::llm::LlmEvent::Token(token) => {
                    self.streaming_response.push_str(&token);
                    self.status = AgentStatus::Streaming(
                        format!("Receiving... ({} chars)", self.streaming_response.len())
                    );
                }
                crate::llm::LlmEvent::Done { .. } => {
                    break;
                }
                crate::llm::LlmEvent::Error(e) => {
                    self.status = AgentStatus::Error(e);
                    return Ok(());
                }
            }
        }

        // Add assistant response to context
        let response = std::mem::take(&mut self.streaming_response);
        self.context.add_message(Role::Assistant, response, 8);
        self.status = AgentStatus::Idle;

        Ok(())
    }

    /// Handle slash commands
    async fn handle_command(&mut self, cmd: &str) -> Result<()> {
        let parts: Vec<&str> = cmd.splitn(2, ' ').collect();
        match parts[0] {
            "/quit" | "/q" => {
                self.should_quit = true;
            }
            "/help" | "/h" => {
                self.show_help = !self.show_help;
            }
            "/clear" => {
                let system_prompt = build_system_prompt(&self.config);
                self.context = ContextManager::new(self.config.context_budget, system_prompt);
                self.status = AgentStatus::Idle;
            }
            "/compact" => {
                let result = self.context.compact()?;
                self.status = AgentStatus::ContextWarning(
                    format!("Compacted: {} → {} tokens", result.tokens_before, result.tokens_after)
                );
            }
            "/tokens" => {
                let usage = self.context.token_usage();
                self.status = AgentStatus::ContextWarning(
                    format!("Tokens: {}/{} ({:.1}%) | System: {} | Messages: {} | Compactions: {}",
                        usage.used, usage.budget, usage.percentage(),
                        usage.system_prompt, usage.messages, usage.compaction_count)
                );
            }
            "/model" => {
                if let Some(model) = parts.get(1) {
                    self.config.model_name = model.to_string();
                    self.llm = LlmClient::new(
                        self.config.llm_endpoint.clone(),
                        self.config.model_name.clone(),
                        self.config.temperature,
                        self.config.max_output_tokens,
                    );
                    self.status = AgentStatus::ContextWarning(format!("Model: {}", model));
                }
            }
            _ => {
                self.status = AgentStatus::Error(format!("Unknown command: {}", parts[0]));
            }
        }
        self.input_buffer.clear();
        self.input_cursor = 0;
        Ok(())
    }

    /// Navigate command history
    pub fn history_up(&mut self) {
        if self.command_history.is_empty() {
            return;
        }
        let idx = match self.history_index {
            Some(i) => i.saturating_sub(1),
            None => self.command_history.len() - 1,
        };
        self.history_index = Some(idx);
        self.input_buffer = self.command_history[idx].clone();
        self.input_cursor = self.input_buffer.len();
    }

    pub fn history_down(&mut self) {
        if let Some(i) = self.history_index {
            if i + 1 < self.command_history.len() {
                self.history_index = Some(i + 1);
                self.input_buffer = self.command_history[i + 1].clone();
            } else {
                self.history_index = None;
                self.input_buffer.clear();
            }
            self.input_cursor = self.input_buffer.len();
        }
    }
}

/// Build the TeaQL system prompt for the DGX Spark agent
fn build_system_prompt(config: &AgentConfig) -> String {
    format!(
        r#"You are a TeaQL coding agent running on an NVIDIA DGX Spark.

Your context window is limited to {} tokens. Be concise and efficient.

## Core Rules
1. Never guess method names — use generated AGENTS.md and assist output.
2. Never edit generated files under rust-lib-core/, java-lib-core/, etc.
3. After generation, check for local AGENTS.md in generated outputs.
4. Every query must use .purpose("why") and .comment("what").
5. Every save must use .audit_as("description").
6. Use cargo teaql --input <model> for all TeaQL operations.
7. Required: cargo-teaql exactly 2.0.8.

## KSML Modeling Rules
- Business objects: must have _name, _module, _module_key. Never declare id.
- Constant objects: must have id="id()", name="string()", code="string()",
  _constant="true", _identifier="code".
- Use <root> as document element.
- References: use object_name() directly.

## DGX Spark Constraints
- Max context: {} tokens (budget: {} usable)
- Be concise. Prefer code over explanation.
- Use streaming for large outputs.
- Report token usage when asked.

Workspace: {}
Model: {}
Version: cargo-teaql {}
"#,
        config.max_context_window,
        config.max_context_window,
        config.context_budget,
        config.workspace_root.display(),
        config.model_name,
        config.cargo_teaql_version,
    )
}
