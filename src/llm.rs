//! LLM client for DGX Spark local inference endpoints.
//!
//! Communicates with NIM (NVIDIA Inference Microservice) running on the DGX Spark
//! via OpenAI-compatible API.

use anyhow::Result;
use futures::StreamExt;
use reqwest::Client;
use serde::{Deserialize, Serialize};
use tokio::sync::mpsc;

use crate::context::ApiMessage;

/// Client for the local LLM endpoint on DGX Spark
pub struct LlmClient {
    client: Client,
    endpoint: String,
    model: String,
    temperature: f32,
    max_tokens: usize,
}

#[derive(Debug, Serialize)]
struct ChatRequest {
    model: String,
    messages: Vec<ApiMessage>,
    temperature: f32,
    max_tokens: usize,
    stream: bool,
}

#[derive(Debug, Deserialize)]
struct ChatResponse {
    choices: Vec<Choice>,
    usage: Option<UsageInfo>,
}

#[derive(Debug, Deserialize)]
struct Choice {
    message: Option<MessageContent>,
    delta: Option<DeltaContent>,
    finish_reason: Option<String>,
}

#[derive(Debug, Deserialize)]
struct MessageContent {
    content: Option<String>,
}

#[derive(Debug, Deserialize)]
struct DeltaContent {
    content: Option<String>,
}

#[derive(Debug, Deserialize, Clone)]
pub struct UsageInfo {
    pub prompt_tokens: usize,
    pub completion_tokens: usize,
    pub total_tokens: usize,
}

#[derive(Debug, Deserialize)]
struct StreamChunk {
    choices: Vec<Choice>,
}

/// Events emitted during streaming
#[derive(Debug, Clone)]
pub enum LlmEvent {
    /// A token/chunk of text
    Token(String),
    /// Stream completed
    Done { usage: Option<UsageInfo> },
    /// Error occurred
    Error(String),
}

impl LlmClient {
    pub fn new(endpoint: String, model: String, temperature: f32, max_tokens: usize) -> Self {
        Self {
            client: Client::new(),
            endpoint,
            model,
            temperature,
            max_tokens,
        }
    }

    /// Send a chat completion request with streaming
    pub async fn chat_stream(
        &self,
        messages: Vec<ApiMessage>,
    ) -> Result<mpsc::Receiver<LlmEvent>> {
        let (tx, rx) = mpsc::channel(256);

        let request = ChatRequest {
            model: self.model.clone(),
            messages,
            temperature: self.temperature,
            max_tokens: self.max_tokens,
            stream: true,
        };

        let url = format!("{}/chat/completions", self.endpoint);
        let response = self.client
            .post(&url)
            .json(&request)
            .send()
            .await?;

        if !response.status().is_success() {
            let status = response.status();
            let body = response.text().await.unwrap_or_default();
            tx.send(LlmEvent::Error(format!("LLM API error {}: {}", status, body))).await.ok();
            return Ok(rx);
        }

        tokio::spawn(async move {
            let mut stream = response.bytes_stream();
            let mut buffer = String::new();

            while let Some(chunk_result) = stream.next().await {
                match chunk_result {
                    Ok(bytes) => {
                        buffer.push_str(&String::from_utf8_lossy(&bytes));

                        // Process complete SSE lines
                        while let Some(newline_pos) = buffer.find('\n') {
                            let line = buffer[..newline_pos].trim().to_string();
                            buffer = buffer[newline_pos + 1..].to_string();

                            if line.is_empty() || line.starts_with(':') {
                                continue;
                            }

                            if let Some(data) = line.strip_prefix("data: ") {
                                if data.trim() == "[DONE]" {
                                    tx.send(LlmEvent::Done { usage: None }).await.ok();
                                    return;
                                }

                                if let Ok(chunk) = serde_json::from_str::<StreamChunk>(data) {
                                    for choice in &chunk.choices {
                                        if let Some(delta) = &choice.delta {
                                            if let Some(content) = &delta.content {
                                                tx.send(LlmEvent::Token(content.clone())).await.ok();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Err(e) => {
                        tx.send(LlmEvent::Error(format!("Stream error: {}", e))).await.ok();
                        return;
                    }
                }
            }

            tx.send(LlmEvent::Done { usage: None }).await.ok();
        });

        Ok(rx)
    }

    /// Non-streaming chat completion
    pub async fn chat(&self, messages: Vec<ApiMessage>) -> Result<(String, Option<UsageInfo>)> {
        let request = ChatRequest {
            model: self.model.clone(),
            messages,
            temperature: self.temperature,
            max_tokens: self.max_tokens,
            stream: false,
        };

        let url = format!("{}/chat/completions", self.endpoint);
        let response = self.client
            .post(&url)
            .json(&request)
            .send()
            .await?
            .json::<ChatResponse>()
            .await?;

        let content = response.choices
            .first()
            .and_then(|c| c.message.as_ref())
            .and_then(|m| m.content.clone())
            .unwrap_or_default();

        Ok((content, response.usage))
    }
}
