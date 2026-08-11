//! Unified model backend selected from a [`ModelProfile`].

use anyhow::Result;
use serde::Serialize;
use std::time::{Duration, Instant};
use tokio::sync::mpsc;

use agent_core::error::AgentError;
use agent_core::event::ModelResult;

use crate::client::{AvailableModel, StreamEvent, VllmClient};
use crate::profile::ModelProfile;
use crate::simulator::SimulatorClient;
use agent_core::agent_loop::ModelBackend;
use agent_core::chat::{ChatMessage, Tool, ToolChoice};

/// Backend capabilities to exercise during a live probe.
#[derive(Debug, Clone, Copy, Default)]
pub struct ProbeOptions {
    pub models: bool,
    pub chat: bool,
    pub stream: bool,
    pub tools: bool,
}

impl ProbeOptions {
    pub fn health() -> Self {
        Self {
            models: true,
            ..Self::default()
        }
    }

    pub fn all() -> Self {
        Self {
            models: true,
            chat: true,
            stream: true,
            tools: true,
        }
    }
}

/// One independently evaluated backend capability.
#[derive(Debug, Clone, Serialize)]
pub struct ProbeCheck {
    pub name: String,
    pub passed: bool,
    pub elapsed_ms: u64,
    pub detail: String,
}

/// Structured result of probing a configured backend.
#[derive(Debug, Clone, Serialize)]
pub struct BackendProbeReport {
    pub backend: String,
    pub endpoint: String,
    pub model: String,
    pub passed: bool,
    pub checks: Vec<ProbeCheck>,
}

/// Runtime model backend used by Pipeline, CLI, and TUI.
#[derive(Clone)]
pub enum ModelClient {
    Vllm(VllmClient),
    Simulator(SimulatorClient),
}

impl ModelClient {
    /// Select and initialize the backend configured by the profile.
    pub fn from_profile(profile: ModelProfile) -> std::result::Result<Self, AgentError> {
        if profile.simulator.enabled {
            let scenario = profile.simulator.scenario.as_deref().ok_or_else(|| {
                AgentError::InfrastructureError {
                    detail: "Simulator is enabled but no scenario is configured".to_string(),
                }
            })?;
            return SimulatorClient::load(profile.clone(), scenario).map(Self::Simulator);
        }
        Ok(Self::Vllm(VllmClient::new(profile)))
    }

    pub async fn health_check(&self) -> Result<bool> {
        match self {
            Self::Vllm(client) => client.health_check().await,
            Self::Simulator(_) => Ok(true),
        }
    }

    /// Exercise selected backend capabilities and return a structured report.
    pub async fn probe(&self, options: ProbeOptions) -> BackendProbeReport {
        let mut checks = Vec::new();

        if options.models {
            let started = Instant::now();
            let check = match self.list_models().await {
                Ok(models) => {
                    let found = models
                        .iter()
                        .any(|model| model.id == self.profile().model.name);
                    ProbeCheck {
                        name: "models".to_string(),
                        passed: found,
                        elapsed_ms: elapsed_ms(started),
                        detail: if found {
                            format!("configured model found among {} model(s)", models.len())
                        } else {
                            format!(
                                "configured model {:?} not returned by /models",
                                self.profile().model.name
                            )
                        },
                    }
                }
                Err(error) => failed_check("models", started, error.to_string()),
            };
            checks.push(check);
        }

        if options.chat {
            let started = Instant::now();
            let check = match self
                .chat(
                    vec![ChatMessage::user("Reply with exactly OK.")],
                    None,
                    None,
                )
                .await
            {
                Ok(result) if !result.content.trim().is_empty() => ProbeCheck {
                    name: "chat".to_string(),
                    passed: true,
                    elapsed_ms: elapsed_ms(started),
                    detail: format!(
                        "HTTP {} with {} total token(s)",
                        result.http_status, result.usage.total_tokens
                    ),
                },
                Ok(_) => failed_check("chat", started, "empty model response".to_string()),
                Err(error) => failed_check("chat", started, error.to_string()),
            };
            checks.push(check);
        }

        if options.stream {
            let started = Instant::now();
            let check = match self
                .chat_stream(vec![ChatMessage::user("Reply with exactly OK.")])
                .await
            {
                Ok(mut receiver) => {
                    let timeout =
                        Duration::from_secs(self.profile().timeouts.model_secs.clamp(1, 60));
                    let result = tokio::time::timeout(timeout, async move {
                        while let Some(event) = receiver.recv().await {
                            match event {
                                StreamEvent::Done {
                                    content,
                                    finish_reason,
                                    ..
                                } if !content.trim().is_empty()
                                    && !finish_reason.trim().is_empty() =>
                                {
                                    return Ok(format!(
                                        "stream completed with finish_reason={finish_reason}"
                                    ));
                                }
                                StreamEvent::Done { .. } => {
                                    return Err(
                                        "stream completed without content or finish_reason"
                                            .to_string(),
                                    );
                                }
                                StreamEvent::Error(error) => return Err(error),
                                StreamEvent::Token(_) | StreamEvent::ReasoningToken(_) => {}
                            }
                        }
                        Err("stream channel closed without a terminal event".to_string())
                    })
                    .await;
                    match result {
                        Ok(Ok(detail)) => ProbeCheck {
                            name: "stream".to_string(),
                            passed: true,
                            elapsed_ms: elapsed_ms(started),
                            detail,
                        },
                        Ok(Err(error)) => failed_check("stream", started, error),
                        Err(_) => {
                            failed_check("stream", started, "stream probe timed out".to_string())
                        }
                    }
                }
                Err(error) => failed_check("stream", started, error.to_string()),
            };
            checks.push(check);
        }

        if options.tools {
            let started = Instant::now();
            let tool = Tool {
                r#type: "function".to_string(),
                function: agent_core::chat::Function {
                    name: "backend_probe".to_string(),
                    description: Some("Return the backend probe status".to_string()),
                    parameters: Some(serde_json::json!({
                        "type": "object",
                        "properties": { "status": { "type": "string" } },
                        "required": ["status"]
                    })),
                },
            };
            let choice = ToolChoice::Object {
                r#type: "function".to_string(),
                function: agent_core::chat::ToolChoiceFunction {
                    name: "backend_probe".to_string(),
                },
            };
            let check = match self
                .chat(
                    vec![ChatMessage::user(
                        "Call backend_probe with status set to OK.",
                    )],
                    Some(vec![tool]),
                    Some(choice),
                )
                .await
            {
                Ok(result) => {
                    let valid = result.tool_calls.as_ref().is_some_and(|calls| {
                        !calls.is_empty()
                            && calls.iter().all(|call| {
                                call.name == "backend_probe"
                                    && serde_json::from_str::<serde_json::Value>(&call.arguments)
                                        .is_ok()
                            })
                    });
                    if valid {
                        ProbeCheck {
                            name: "tools".to_string(),
                            passed: true,
                            elapsed_ms: elapsed_ms(started),
                            detail: "valid forced tool call returned".to_string(),
                        }
                    } else {
                        failed_check(
                            "tools",
                            started,
                            "model did not return a valid backend_probe tool call".to_string(),
                        )
                    }
                }
                Err(error) => failed_check("tools", started, error.to_string()),
            };
            checks.push(check);
        }

        BackendProbeReport {
            backend: self.backend_label().to_string(),
            endpoint: self.profile().resolve_endpoint(),
            model: self.profile().model.name.clone(),
            passed: checks.iter().all(|check| check.passed),
            checks,
        }
    }

    /// List model IDs exposed by the active backend.
    pub async fn list_models(&self) -> std::result::Result<Vec<AvailableModel>, AgentError> {
        match self {
            Self::Vllm(client) => client.list_models().await,
            Self::Simulator(client) => Ok(vec![AvailableModel {
                id: client.profile().model.name.clone(),
                owned_by: "simulator".to_string(),
            }]),
        }
    }

    pub async fn chat(
        &self,
        messages: Vec<ChatMessage>,
        tools: Option<Vec<Tool>>,
        tool_choice: Option<ToolChoice>,
    ) -> std::result::Result<ModelResult, AgentError> {
        match self {
            Self::Vllm(client) => client.chat(messages, tools, tool_choice).await,
            Self::Simulator(client) => client.chat(messages, tools, tool_choice).await,
        }
    }
}

fn elapsed_ms(started: Instant) -> u64 {
    started.elapsed().as_millis().min(u128::from(u64::MAX)) as u64
}

fn failed_check(name: &str, started: Instant, detail: String) -> ProbeCheck {
    ProbeCheck {
        name: name.to_string(),
        passed: false,
        elapsed_ms: elapsed_ms(started),
        detail: detail.chars().take(500).collect(),
    }
}

impl ModelBackend for ModelClient {
    async fn chat(
        &self,
        messages: Vec<ChatMessage>,
        tools: Option<Vec<Tool>>,
    ) -> Result<ModelResult, AgentError> {
        self.chat(messages, tools, None).await
    }
}

impl ModelClient {
    pub async fn chat_stream(
        &self,
        messages: Vec<ChatMessage>,
    ) -> std::result::Result<mpsc::Receiver<StreamEvent>, AgentError> {
        match self {
            Self::Vllm(client) => client.chat_stream(messages).await,
            Self::Simulator(client) => client.chat_stream(messages).await,
        }
    }

    pub fn profile(&self) -> &ModelProfile {
        match self {
            Self::Vllm(client) => client.profile(),
            Self::Simulator(client) => client.profile(),
        }
    }

    pub fn backend_label(&self) -> &'static str {
        match self {
            Self::Vllm(_) => "vLLM",
            Self::Simulator(_) => "SIMULATOR",
        }
    }

    pub fn simulator(&self) -> Option<&SimulatorClient> {
        match self {
            Self::Simulator(client) => Some(client),
            Self::Vllm(_) => None,
        }
    }
}
