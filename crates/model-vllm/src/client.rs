use anyhow::Result;
use futures::StreamExt;
use reqwest::Client;
use serde::Deserialize;
use std::time::{Duration, Instant};
use tokio::sync::mpsc;
use tracing::{error, info, warn};

use crate::chat::*;
use crate::profile::ModelProfile;
use agent_core::error::AgentError;
use agent_core::event::{ModelResult, TokenUsage};

/// Events during streaming
#[derive(Debug, Clone)]
pub enum StreamEvent {
    Token(String),
    ReasoningToken(String),
    Done {
        content: String,
        reasoning_content: Option<String>,
        finish_reason: String,
        usage: Option<ChatUsage>,
    },
    Error(String),
}

/// vLLM client for DGX Spark
pub struct VllmClient {
    client: Client,
    profile: ModelProfile,
}

/// Basic model metadata returned by an OpenAI-compatible `/models` endpoint.
#[derive(Debug, Clone, Deserialize, PartialEq, Eq)]
pub struct AvailableModel {
    pub id: String,
    #[serde(default)]
    pub owned_by: String,
}

#[derive(Debug, Deserialize)]
struct ModelList {
    data: Vec<AvailableModel>,
}

impl VllmClient {
    pub fn new(profile: ModelProfile) -> Self {
        let client = Client::builder()
            .timeout(Duration::from_secs(profile.timeouts.model_secs))
            .build()
            .unwrap_or_else(|_| Client::new());
        Self { client, profile }
    }

    /// Check if the model service is healthy
    pub async fn health_check(&self) -> Result<bool> {
        let resp = self
            .authorize(self.client.get(self.profile.models_url()))
            .timeout(Duration::from_secs(self.profile.timeouts.health_secs))
            .send()
            .await;
        match resp {
            Ok(r) => Ok(r.status().is_success()),
            Err(_) => Ok(false),
        }
    }

    /// List model IDs exposed by the configured endpoint.
    pub async fn list_models(&self) -> std::result::Result<Vec<AvailableModel>, AgentError> {
        let response = self
            .authorize(self.client.get(self.profile.models_url()))
            .timeout(Duration::from_secs(self.profile.timeouts.health_secs))
            .send()
            .await
            .map_err(|error| AgentError::InfrastructureError {
                detail: format!("Failed to list models: {error}"),
            })?;
        let status = response.status().as_u16();
        if !response.status().is_success() {
            let body = response.text().await.unwrap_or_default();
            return Err(AgentError::TransportError { status, body });
        }
        response
            .json::<ModelList>()
            .await
            .map(|models| models.data)
            .map_err(|error| AgentError::InfrastructureError {
                detail: format!("Failed to parse model list: {error}"),
            })
    }

    /// Build a ChatRequest from messages
    fn build_request(&self, messages: Vec<ChatMessage>) -> ChatRequest {
        ChatRequest {
            model: self.profile.model.name.clone(),
            messages,
            temperature: self.profile.sampling.temperature,
            top_p: self.profile.sampling.top_p,
            max_tokens: self.profile.context.max_completion_tokens,
            stream: false,
            chat_template_kwargs: if !self.profile.thinking.enabled {
                Some(ChatTemplateKwargs {
                    enable_thinking: false,
                })
            } else {
                Some(ChatTemplateKwargs {
                    enable_thinking: true,
                })
            },
        }
    }

    /// Non-streaming chat completion. Returns ModelResult.
    pub async fn chat(
        &self,
        messages: Vec<ChatMessage>,
    ) -> std::result::Result<ModelResult, AgentError> {
        let request = self.build_request(messages);
        let url = self.profile.chat_url();
        let start = Instant::now();

        let response = self
            .authorize(self.client.post(&url))
            .json(&request)
            .send()
            .await
            .map_err(|e| {
                if e.is_timeout() {
                    AgentError::Timeout {
                        seconds: self.profile.timeouts.model_secs,
                        state: "Generating".to_string(),
                    }
                } else {
                    AgentError::InfrastructureError {
                        detail: format!("HTTP request failed: {e}"),
                    }
                }
            })?;

        let status = response.status().as_u16();
        let elapsed = start.elapsed().as_secs_f64();

        if !response.status().is_success() {
            let body = response.text().await.unwrap_or_default();
            return Err(AgentError::TransportError { status, body });
        }

        let chat_response: ChatResponse =
            response
                .json()
                .await
                .map_err(|e| AgentError::InfrastructureError {
                    detail: format!("Failed to parse response JSON: {e}"),
                })?;

        let choice =
            chat_response
                .choices
                .first()
                .ok_or_else(|| AgentError::InfrastructureError {
                    detail: "No choices in response".to_string(),
                })?;

        let message = choice
            .message
            .as_ref()
            .ok_or_else(|| AgentError::InfrastructureError {
                detail: "No message in choice".to_string(),
            })?;

        let content = message.content.clone().unwrap_or_default();
        let reasoning_content = message.reasoning_content.clone();
        let finish_reason = choice
            .finish_reason
            .clone()
            .unwrap_or_else(|| "unknown".to_string());

        let usage = chat_response
            .usage
            .map(|u| TokenUsage {
                prompt_tokens: u.prompt_tokens,
                completion_tokens: u.completion_tokens,
                total_tokens: u.total_tokens,
            })
            .unwrap_or(TokenUsage {
                prompt_tokens: 0,
                completion_tokens: 0,
                total_tokens: 0,
            });

        info!(
            status,
            finish_reason = %finish_reason,
            prompt_tokens = usage.prompt_tokens,
            completion_tokens = usage.completion_tokens,
            elapsed_secs = elapsed,
            "Model response received"
        );

        // Only finish_reason=stop with non-empty content is valid
        if finish_reason != "stop" {
            return Err(AgentError::IncompleteGeneration {
                reason: finish_reason,
            });
        }

        if content.trim().is_empty() {
            return Err(AgentError::IncompleteGeneration {
                reason: "empty content with finish_reason=stop".to_string(),
            });
        }

        Ok(ModelResult {
            content,
            reasoning_content,
            finish_reason,
            usage,
            elapsed_secs: elapsed,
            http_status: status,
        })
    }

    /// Streaming chat completion. Returns a receiver for StreamEvents.
    pub async fn chat_stream(
        &self,
        messages: Vec<ChatMessage>,
    ) -> std::result::Result<mpsc::Receiver<StreamEvent>, AgentError> {
        let mut request = self.build_request(messages);
        request.stream = true;
        let url = self.profile.chat_url();

        let (tx, rx) = mpsc::channel(256);

        let response = self
            .authorize(self.client.post(&url))
            .json(&request)
            .send()
            .await
            .map_err(|e| {
                if e.is_timeout() {
                    AgentError::Timeout {
                        seconds: self.profile.timeouts.model_secs,
                        state: "Generating".to_string(),
                    }
                } else {
                    AgentError::InfrastructureError {
                        detail: format!("HTTP request failed: {e}"),
                    }
                }
            })?;

        let status = response.status().as_u16();
        if !response.status().is_success() {
            let body = response.text().await.unwrap_or_default();
            return Err(AgentError::TransportError { status, body });
        }

        tokio::spawn(async move {
            let mut stream = response.bytes_stream();
            let mut buffer = String::new();
            let mut full_content = String::new();
            let mut full_reasoning = String::new();
            let mut last_finish_reason = String::new();
            let mut last_usage: Option<ChatUsage> = None;

            while let Some(chunk_result) = stream.next().await {
                match chunk_result {
                    Ok(bytes) => {
                        buffer.push_str(&String::from_utf8_lossy(&bytes));

                        while let Some(pos) = buffer.find('\n') {
                            let line = buffer[..pos].trim().to_string();
                            buffer = buffer[pos + 1..].to_string();

                            if line.is_empty() || line.starts_with(':') {
                                continue;
                            }

                            if let Some(data) = line.strip_prefix("data: ") {
                                if data.trim() == "[DONE]" {
                                    let reasoning = if full_reasoning.is_empty() {
                                        None
                                    } else {
                                        Some(full_reasoning.clone())
                                    };
                                    tx.send(StreamEvent::Done {
                                        content: full_content.clone(),
                                        reasoning_content: reasoning,
                                        finish_reason: last_finish_reason.clone(),
                                        usage: last_usage.clone(),
                                    })
                                    .await
                                    .ok();
                                    return;
                                }

                                if let Ok(chunk) = serde_json::from_str::<StreamChunk>(data) {
                                    if let Some(usage) = chunk.usage {
                                        last_usage = Some(usage);
                                    }
                                    for choice in &chunk.choices {
                                        if let Some(fr) = &choice.finish_reason {
                                            last_finish_reason = fr.clone();
                                        }
                                        if let Some(delta) = &choice.delta {
                                            if let Some(c) = &delta.content {
                                                full_content.push_str(c);
                                                tx.send(StreamEvent::Token(c.clone())).await.ok();
                                            }
                                            if let Some(r) = &delta.reasoning_content {
                                                full_reasoning.push_str(r);
                                                tx.send(StreamEvent::ReasoningToken(r.clone()))
                                                    .await
                                                    .ok();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Err(e) => {
                        tx.send(StreamEvent::Error(format!("Stream error: {e}")))
                            .await
                            .ok();
                        return;
                    }
                }
            }

            // Stream ended without [DONE]
            let reasoning = if full_reasoning.is_empty() {
                None
            } else {
                Some(full_reasoning)
            };
            tx.send(StreamEvent::Done {
                content: full_content,
                reasoning_content: reasoning,
                finish_reason: last_finish_reason,
                usage: last_usage,
            })
            .await
            .ok();
        });

        Ok(rx)
    }

    /// Get the profile
    pub fn profile(&self) -> &ModelProfile {
        &self.profile
    }

    fn authorize(&self, request: reqwest::RequestBuilder) -> reqwest::RequestBuilder {
        match self.profile.resolve_api_key() {
            Some(key) => request.bearer_auth(key),
            None => request,
        }
    }
}
