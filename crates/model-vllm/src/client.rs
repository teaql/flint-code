use anyhow::Result;
use futures::StreamExt;
use reqwest::Client;
use serde::Deserialize;
use std::time::{Duration, Instant};
use tokio::sync::mpsc;
use tracing::info;

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
#[derive(Clone)]
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
            .pool_max_idle_per_host(0)
            .build()
            .unwrap_or_else(|e| {
                tracing::warn!(
                    "Failed to build custom HTTP client: {e}. Falling back to default client."
                );
                Client::new()
            });
        Self { client, profile }
    }

    /// Check if the model service is healthy
    pub async fn health_check(&self) -> Result<bool> {
        let models = self.list_models().await.map_err(anyhow::Error::new)?;
        Ok(models
            .iter()
            .any(|model| model.id == self.profile.model.name))
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
            let body = bounded_text(&response.text().await.unwrap_or_default(), 2_000);
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

    /// Build a ChatRequest from messages.
    /// `max_tokens` is computed dynamically:
    ///   model_context_tokens - estimated_prompt_tokens - safety_tokens
    /// so the model always gets the maximum possible output budget.
    fn build_request(
        &self,
        messages: Vec<ChatMessage>,
        tools: Option<Vec<Tool>>,
        tool_choice: Option<ToolChoice>,
    ) -> ChatRequest {
        // Rough estimate: 1 token ≈ 4 chars for English/code, 1.5 chars for CJK.
        // We use a conservative 4 chars/token to avoid severely overestimating XML/Code.
        let estimated_prompt_tokens: u32 = messages
            .iter()
            .map(|m| {
                let mut chars = m.content.as_deref().unwrap_or("").len();
                if let Some(calls) = &m.tool_calls {
                    for c in calls {
                        chars += c.function.name.len();
                        chars += c.function.arguments.len();
                    }
                }
                (chars as u32) / 4 + 10 // +10 for role/overhead per message
            })
            .sum();

        let dynamic_max = self
            .profile
            .context
            .model_context_tokens
            .saturating_sub(estimated_prompt_tokens)
            .saturating_sub(self.profile.context.safety_tokens);

        // Never request more completion tokens than the remaining context.
        let max_tokens = dynamic_max
            .min(self.profile.context.max_completion_tokens)
            .max(1);

        ChatRequest {
            model: self.profile.model.name.clone(),
            messages,
            temperature: self.profile.sampling.temperature,
            top_p: self.profile.sampling.top_p,
            max_tokens,
            max_completion_tokens: Some(max_tokens),
            stream: false,
            tools,
            tool_choice,
            chat_template_kwargs: if !self.profile.thinking.enabled {
                Some(ChatTemplateKwargs {
                    enable_thinking: false,
                })
            } else {
                Some(ChatTemplateKwargs {
                    enable_thinking: true,
                })
            },
            thinking: if self.profile.thinking.supported {
                if !self.profile.thinking.enabled {
                    Some(ThinkingParam {
                        r#type: "disabled".to_string(),
                    })
                } else {
                    Some(ThinkingParam {
                        r#type: "enabled".to_string(),
                    })
                }
            } else {
                None
            },
        }
    }

    /// Non-streaming chat completion. Returns ModelResult.
    pub async fn chat(
        &self,
        messages: Vec<ChatMessage>,
        tools: Option<Vec<Tool>>,
        tool_choice: Option<ToolChoice>,
    ) -> std::result::Result<ModelResult, AgentError> {
        let max_attempts = self.profile.run.retry_http_5xx_count.max(1) as u32;
        let mut attempt = 1;

        loop {
            match self
                .chat_internal(messages.clone(), tools.clone(), tool_choice.clone())
                .await
            {
                Ok(res) => return Ok(res),
                Err(e) => {
                    if attempt >= max_attempts {
                        return Err(e);
                    }

                    // Check if we should actually retry this error
                    let should_retry =
                        if let agent_core::error::AgentError::TransportError { status, .. } = e {
                            if status >= 400 && status < 500 {
                                self.profile.run.retry_http_4xx
                            } else {
                                true
                            }
                        } else {
                            true
                        };

                    if !should_retry {
                        return Err(e);
                    }

                    tracing::warn!(
                        "Model API error (attempt {}/{}): {}. Retrying in 2 seconds...",
                        attempt,
                        max_attempts,
                        e
                    );
                    tokio::time::sleep(std::time::Duration::from_secs(2)).await;
                    attempt += 1;
                }
            }
        }
    }

    async fn chat_internal(
        &self,
        messages: Vec<ChatMessage>,
        tools: Option<Vec<Tool>>,
        tool_choice: Option<ToolChoice>,
    ) -> std::result::Result<ModelResult, AgentError> {
        let request = self.build_request(messages, tools, tool_choice);
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

        let raw_body = response.text().await.unwrap_or_default();
        let chat_response: ChatResponse = serde_json::from_str(&raw_body).map_err(|e| {
            let truncated = bounded_text(&raw_body, 1_000);
            AgentError::InfrastructureError {
                detail: format!("Failed to parse response JSON: {e}. Body: {truncated}"),
            }
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
        let tool_calls = message.tool_calls.clone().map(|calls| {
            calls
                .into_iter()
                .map(|tc| agent_core::event::ModelToolCall {
                    id: tc.id,
                    name: tc.function.name,
                    arguments: tc.function.arguments,
                })
                .collect()
        });
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

        // finish_reason=tool_calls means the model wants to call tools.
        match finish_reason.as_str() {
            "stop" | "length" | "tool_calls" => {}
            other => {
                return Err(AgentError::IncompleteGeneration {
                    reason: other.to_string(),
                });
            }
        }

        if content.trim().is_empty() && tool_calls.is_none() {
            return Err(AgentError::IncompleteGeneration {
                reason: format!(
                    "empty content and no tools with finish_reason={}",
                    finish_reason
                ),
            });
        }

        // Warn when output was truncated so the executor can adapt
        if finish_reason == "length" {
            tracing::warn!(
                completion_tokens = usage.completion_tokens,
                "Model output truncated (finish_reason=length); partial content returned"
            );
        }

        Ok(ModelResult {
            content,
            reasoning_content,
            tool_calls,
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
        let mut request = self.build_request(messages, None, None);
        request.stream = true;
        let url = self.profile.chat_url();
        let timeout_secs = self.profile.timeouts.model_secs;

        let (tx, rx) = mpsc::channel(256);
        let client_clone = self.clone();

        tokio::spawn(async move {
            let max_attempts = client_clone.profile.run.retry_http_5xx_count.max(1) as u32;
            let mut attempt = 1;

            loop {
                let response = match client_clone
                    .authorize(client_clone.client.post(&url))
                    .json(&request)
                    .send()
                    .await
                {
                    Ok(res) => res,
                    Err(e) => {
                        let is_timeout = e.is_timeout();
                        let error_msg = if is_timeout {
                            format!("Timeout after {}s", timeout_secs)
                        } else {
                            format!("HTTP request failed: {}", e)
                        };

                        if attempt >= max_attempts {
                            tx.send(StreamEvent::Error(error_msg)).await.ok();
                            return;
                        }
                        tracing::warn!(
                            "Stream setup error (attempt {}/{}): {}. Retrying in 2 seconds...",
                            attempt,
                            max_attempts,
                            error_msg
                        );
                        tokio::time::sleep(std::time::Duration::from_secs(2)).await;
                        attempt += 1;
                        continue;
                    }
                };

                let status = response.status().as_u16();
                if !response.status().is_success() {
                    let body = response.text().await.unwrap_or_default();
                    let error_msg = format!("HTTP {}: {}", status, bounded_text(&body, 2_000));
                    let should_retry =
                        !(400..500).contains(&status) || client_clone.profile.run.retry_http_4xx;
                    if !should_retry || attempt >= max_attempts {
                        tx.send(StreamEvent::Error(error_msg)).await.ok();
                        return;
                    }
                    tracing::warn!(
                        "Stream setup error (attempt {}/{}): {}. Retrying in 2 seconds...",
                        attempt,
                        max_attempts,
                        error_msg
                    );
                    tokio::time::sleep(std::time::Duration::from_secs(2)).await;
                    attempt += 1;
                    continue;
                }

                let mut stream = response.bytes_stream();
                let mut buffer = String::new();
                let mut full_content = String::new();
                let mut full_reasoning = String::new();
                let mut last_finish_reason = String::new();
                let mut last_usage: Option<ChatUsage> = None;
                let mut stream_failed = false;

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

                                if let Some(data) = line.strip_prefix("data:").map(str::trim_start)
                                {
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

                                    match serde_json::from_str::<StreamChunk>(data) {
                                        Ok(chunk) => {
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
                                                        tx.send(StreamEvent::Token(c.clone()))
                                                            .await
                                                            .ok();
                                                    }
                                                    if let Some(r) = &delta.reasoning_content {
                                                        full_reasoning.push_str(r);
                                                        tx.send(StreamEvent::ReasoningToken(
                                                            r.clone(),
                                                        ))
                                                        .await
                                                        .ok();
                                                    }
                                                }
                                            }
                                        }
                                        Err(error) => {
                                            tx.send(StreamEvent::Error(format!(
                                                "Malformed SSE JSON: {error}"
                                            )))
                                            .await
                                            .ok();
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        Err(e) => {
                            let error_msg = format!("Stream error: {e}");
                            if attempt >= max_attempts {
                                tx.send(StreamEvent::Error(error_msg)).await.ok();
                                return;
                            }
                            tracing::warn!(
                                "Stream read error (attempt {}/{}): {}. Retrying in 2 seconds...",
                                attempt,
                                max_attempts,
                                error_msg
                            );
                            stream_failed = true;
                            break;
                        }
                    }
                }

                if stream_failed {
                    tokio::time::sleep(std::time::Duration::from_secs(2)).await;
                    attempt += 1;
                    continue;
                }

                if !buffer.trim().is_empty() {
                    tx.send(StreamEvent::Error(
                        "Stream ended with an unterminated SSE event".to_string(),
                    ))
                    .await
                    .ok();
                    return;
                }
                if last_finish_reason.is_empty() {
                    tx.send(StreamEvent::Error(
                        "Stream ended without [DONE] or a finish_reason".to_string(),
                    ))
                    .await
                    .ok();
                    return;
                }

                // Some compatible servers close after a terminal finish_reason
                // instead of sending the optional [DONE] sentinel.
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
                return;
            }
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

fn bounded_text(text: &str, max_chars: usize) -> String {
    let mut chars = text.chars();
    let prefix = chars.by_ref().take(max_chars).collect::<String>();
    if chars.next().is_some() {
        format!("{prefix}…")
    } else {
        prefix
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::sync::atomic::{AtomicUsize, Ordering};
    use tokio::io::{AsyncReadExt, AsyncWriteExt};
    use tokio::net::TcpListener;
    use tokio::sync::oneshot;

    static TEST_KEY_SEQUENCE: AtomicUsize = AtomicUsize::new(1);

    fn http_profile(endpoint: &str) -> ModelProfile {
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.enabled = false;
        profile.model.name = "test-model".to_string();
        profile.model.default_endpoint = endpoint.to_string();
        profile.model.endpoint_env = format!(
            "FLINTCODE_HTTP_TEST_ENDPOINT_{}",
            TEST_KEY_SEQUENCE.fetch_add(1, Ordering::Relaxed)
        );
        profile.model.api_path = "/v1/chat/completions".to_string();
        profile.model.api_key_env = None;
        profile.run.retry_http_5xx_count = 1;
        profile.timeouts.health_secs = 2;
        profile.timeouts.model_secs = 2;
        profile
    }

    async fn serve_once(
        status: &str,
        content_type: &str,
        body: String,
    ) -> (String, oneshot::Receiver<String>) {
        let listener = TcpListener::bind("127.0.0.1:0")
            .await
            .expect("bind test server");
        let address = listener.local_addr().expect("test address");
        let (request_tx, request_rx) = oneshot::channel();
        let status = status.to_string();
        let content_type = content_type.to_string();
        tokio::spawn(async move {
            let (mut socket, _) = listener.accept().await.expect("accept request");
            let request = read_http_request(&mut socket).await;
            let response = format!(
                "HTTP/1.1 {status}\r\nContent-Type: {content_type}\r\nContent-Length: {}\r\nConnection: close\r\n\r\n{body}",
                body.len()
            );
            socket
                .write_all(response.as_bytes())
                .await
                .expect("write response");
            let _ = request_tx.send(request);
        });
        (format!("http://{address}"), request_rx)
    }

    async fn read_http_request(socket: &mut tokio::net::TcpStream) -> String {
        let mut request = Vec::new();
        let mut buffer = [0_u8; 2048];
        loop {
            let read = socket.read(&mut buffer).await.expect("read request");
            if read == 0 {
                break;
            }
            request.extend_from_slice(&buffer[..read]);
            if let Some(header_end) = request.windows(4).position(|part| part == b"\r\n\r\n") {
                let header_text = String::from_utf8_lossy(&request[..header_end + 4]);
                let content_length = header_text
                    .lines()
                    .find_map(|line| {
                        line.split_once(':').and_then(|(name, value)| {
                            name.eq_ignore_ascii_case("content-length")
                                .then(|| value.trim().parse::<usize>().ok())
                                .flatten()
                        })
                    })
                    .unwrap_or(0);
                if request.len() >= header_end + 4 + content_length {
                    break;
                }
            }
        }
        String::from_utf8(request).expect("UTF-8 request")
    }

    #[test]
    fn completion_allocation_does_not_exceed_remaining_context() {
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.context.model_context_tokens = 1_000;
        profile.context.safety_tokens = 100;
        profile.context.max_completion_tokens = 4_096;
        let client = VllmClient::new(profile);

        let request = client.build_request(vec![ChatMessage::user("x".repeat(3_400))], None, None);

        assert_eq!(request.max_tokens, 40);
    }

    #[tokio::test]
    async fn health_rejects_authentication_errors() {
        let (endpoint, request) = serve_once(
            "401 Unauthorized",
            "application/json",
            r#"{"error":"invalid key"}"#.to_string(),
        )
        .await;
        let client = VllmClient::new(http_profile(&endpoint));

        let error = client
            .health_check()
            .await
            .expect_err("401 must fail health");

        assert!(error.to_string().contains("HTTP 401"));
        assert!(
            request
                .await
                .expect("captured request")
                .starts_with("GET /v1/models ")
        );
    }

    #[tokio::test]
    async fn chat_uses_bearer_auth_and_parses_tool_calls() {
        let response = serde_json::json!({
            "id": "response-1",
            "choices": [{
                "index": 0,
                "message": {
                    "role": "assistant",
                    "content": null,
                    "tool_calls": [{
                        "id": "call-1",
                        "type": "function",
                        "function": {
                            "name": "inspect",
                            "arguments": "{\"path\":\"src/lib.rs\"}"
                        }
                    }]
                },
                "finish_reason": "tool_calls"
            }],
            "usage": {"prompt_tokens": 10, "completion_tokens": 5, "total_tokens": 15}
        })
        .to_string();
        let (endpoint, request) = serve_once("200 OK", "application/json", response).await;
        let mut profile = http_profile(&endpoint);
        let key_env = format!(
            "FLINTCODE_HTTP_TEST_KEY_{}",
            TEST_KEY_SEQUENCE.fetch_add(1, Ordering::Relaxed)
        );
        profile.model.api_key_env = Some(key_env.clone());
        unsafe { std::env::set_var(&key_env, "test-secret") };
        let client = VllmClient::new(profile);
        let tool = Tool {
            r#type: "function".to_string(),
            function: Function {
                name: "inspect".to_string(),
                description: None,
                parameters: None,
            },
        };

        let result = client
            .chat(vec![ChatMessage::user("inspect")], Some(vec![tool]), None)
            .await
            .expect("chat response");
        unsafe { std::env::remove_var(&key_env) };
        let request = request.await.expect("captured request");

        assert!(request.contains("authorization: Bearer test-secret\r\n"));
        assert!(request.contains("POST /v1/chat/completions "));
        assert_eq!(result.finish_reason, "tool_calls");
        assert_eq!(result.tool_calls.expect("tool calls")[0].name, "inspect");
    }

    #[tokio::test]
    async fn malformed_stream_json_is_reported_as_an_error() {
        let (endpoint, _request) = serve_once(
            "200 OK",
            "text/event-stream",
            "data: {not-json}\n\n".to_string(),
        )
        .await;
        let client = VllmClient::new(http_profile(&endpoint));
        let mut receiver = client
            .chat_stream(vec![ChatMessage::user("stream")])
            .await
            .expect("stream receiver");

        let event = receiver.recv().await.expect("terminal event");

        assert!(matches!(event, StreamEvent::Error(error) if error.contains("Malformed SSE JSON")));
    }

    #[tokio::test]
    async fn stream_does_not_retry_http_4xx_by_default() {
        let (endpoint, _request) = serve_once(
            "401 Unauthorized",
            "application/json",
            r#"{"error":"invalid key"}"#.to_string(),
        )
        .await;
        let mut profile = http_profile(&endpoint);
        profile.run.retry_http_5xx_count = 3;
        profile.run.retry_http_4xx = false;
        let client = VllmClient::new(profile);
        let mut receiver = client
            .chat_stream(vec![ChatMessage::user("stream")])
            .await
            .expect("stream receiver");

        let event = tokio::time::timeout(Duration::from_millis(500), receiver.recv())
            .await
            .expect("4xx should not enter retry delay")
            .expect("terminal event");

        assert!(matches!(event, StreamEvent::Error(error) if error.contains("HTTP 401")));
    }

    #[tokio::test]
    async fn stream_accepts_terminal_finish_reason_without_done_sentinel() {
        let body = concat!(
            "data: {\"id\":\"1\",\"choices\":[{\"index\":0,\"delta\":{\"content\":\"OK\"},\"finish_reason\":null}]}\n\n",
            "data: {\"id\":\"1\",\"choices\":[{\"index\":0,\"delta\":{},\"finish_reason\":\"stop\"}]}\n\n"
        )
        .to_string();
        let (endpoint, _request) = serve_once("200 OK", "text/event-stream", body).await;
        let client = VllmClient::new(http_profile(&endpoint));
        let mut receiver = client
            .chat_stream(vec![ChatMessage::user("stream")])
            .await
            .expect("stream receiver");
        let mut terminal = None;
        while let Some(event) = receiver.recv().await {
            if matches!(event, StreamEvent::Done { .. } | StreamEvent::Error(_)) {
                terminal = Some(event);
                break;
            }
        }

        assert!(matches!(
            terminal,
            Some(StreamEvent::Done { content, finish_reason, .. })
                if content == "OK" && finish_reason == "stop"
        ));
    }
}
