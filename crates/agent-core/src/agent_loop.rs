use crate::chat::{ChatMessage, Tool, ToolCall, FunctionCall};
use crate::event::ModelResult;
use crate::error::AgentError;
use std::future::Future;
use std::sync::Arc;
use tokio::sync::Mutex;
use crate::context::ContextManager;

pub trait ModelBackend {
    fn chat(
        &self,
        messages: Vec<ChatMessage>,
        tools: Option<Vec<Tool>>,
    ) -> impl Future<Output = Result<ModelResult, AgentError>> + Send;
}

pub trait ToolExecutor {
    fn execute(
        &self,
        name: &str,
        arguments: &str,
    ) -> impl Future<Output = Result<String, AgentError>> + Send;
}

pub struct AgentConfig {
    pub max_iterations: usize,
    pub max_tokens: usize,
    pub trim_ratio: f32,
}

impl Default for AgentConfig {
    fn default() -> Self {
        Self {
            max_iterations: 150,
            max_tokens: 32000,
            trim_ratio: 0.25,
        }
    }
}

pub struct AgentLoop<M, E> {
    model: M,
    executor: E,
    tools: Vec<Tool>,
    config: AgentConfig,
}

impl<M: ModelBackend, E: ToolExecutor> AgentLoop<M, E> {
    pub fn new(model: M, executor: E, tools: Vec<Tool>) -> Self {
        Self::with_config(model, executor, tools, AgentConfig::default())
    }

    pub fn with_config(model: M, executor: E, tools: Vec<Tool>, config: AgentConfig) -> Self {
        Self { model, executor, tools, config }
    }

    /// Helper to trim context while avoiding orphaning tool messages
    fn trim_messages(messages: &mut Vec<ChatMessage>, trim_ratio: f32) {
        if messages.len() <= 2 {
            return;
        }
        let target_drop = ((messages.len() as f32) * trim_ratio) as usize;
        if target_drop == 0 { return; }

        let mut drop_end = 1 + target_drop;
        while drop_end < messages.len() {
            if messages[drop_end].role != "tool" {
                break;
            }
            drop_end += 1;
        }

        if drop_end < messages.len() {
            messages.drain(1..drop_end);
        }
    }

    pub async fn run(&self, mut messages: Vec<ChatMessage>) -> Result<(), AgentError> {
        let mut iterations = 0;

        let context = Arc::new(Mutex::new(ContextManager::new()));
        
        // Parse initial system prompt if present
        if !messages.is_empty() && messages[0].role == "system" {
            if let Some(ref content) = messages[0].content {
                context.lock().await.load_from_str("system", content);
                messages[0].content = Some(context.lock().await.render_active_prompt());
            }
        }
        
        crate::context::start_debug_server(context.clone());

        loop {
            iterations += 1;
            if iterations > self.config.max_iterations {
                tracing::error!("Agent Loop exceeded max iterations ({})", self.config.max_iterations);
                break;
            }

            // --- Ephemeral Cleanup (阅后即焚) ---
            let last_assistant_idx = messages.iter().rposition(|m| m.role == "assistant").unwrap_or(0);
            for i in 0..last_assistant_idx {
                if messages[i].role == "tool" {
                    if let Some(ref content) = messages[i].content {
                        if content.len() > 1000 || content.contains("<!-- ephemeral -->") {
                            tracing::info!("Truncating ephemeral tool output for tool_call_id: {:?}", messages[i].tool_call_id);
                            messages[i].content = Some(format!("[EPHEMERAL: Output omitted for context limits (Original size: {} bytes)]", content.len()));
                        }
                    }
                }
            }

            // --- Explicit Skill Block Discarding ---
            let mut blocks_to_discard = std::collections::HashSet::new();
            for m in &messages {
                if let Some(ref content) = m.content {
                    let mut idx = 0;
                    while let Some(start) = content[idx..].find("<!-- DISCARD_BLOCK: ") {
                        let actual_start = idx + start + "<!-- DISCARD_BLOCK: ".len();
                        if let Some(end) = content[actual_start..].find(" -->") {
                            let block_id = content[actual_start..actual_start + end].trim();
                            blocks_to_discard.insert(block_id.to_string());
                            idx = actual_start + end + " -->".len();
                        } else {
                            break;
                        }
                    }
                }
            }

            if !blocks_to_discard.is_empty() {
                let mut ctx = context.lock().await;
                let mut changed = false;
                for id in &blocks_to_discard {
                    if ctx.discard_block(id) {
                        tracing::info!("Discarding explicit skill block: {}", id);
                        changed = true;
                    }
                }
                if changed && !messages.is_empty() && messages[0].role == "system" {
                    messages[0].content = Some(ctx.render_active_prompt());
                }
            }
            // ------------------------------------

            tracing::info!("Querying LLM (Messages: {})...", messages.len());
            
            let result = self.model.chat(messages.clone(), Some(self.tools.clone())).await?;
            
            if result.usage.prompt_tokens > self.config.max_tokens as u32 {
                tracing::warn!("Context size {} exceeds max tokens {}. Trimming context...", result.usage.prompt_tokens, self.config.max_tokens);
                Self::trim_messages(&mut messages, self.config.trim_ratio);
            }
            
            // Add the assistant's message to the conversation
            messages.push(ChatMessage {
                role: "assistant".to_string(),
                content: if result.content.is_empty() { None } else { Some(result.content.clone()) },
                name: None,
                tool_calls: result.tool_calls.as_ref().map(|calls| {
                    calls.iter().map(|c| ToolCall {
                        id: c.id.clone(),
                        r#type: "function".to_string(),
                        function: FunctionCall {
                            name: c.name.clone(),
                            arguments: c.arguments.clone(),
                        }
                    }).collect()
                }),
                tool_call_id: None,
            });

            if let Some(calls) = result.tool_calls {
                let mut finished = false;
                for call in calls {
                    tracing::info!("Executing Tool: {} with arguments: {}", call.name, call.arguments);
                    
                    let tool_output = match self.executor.execute(&call.name, &call.arguments).await {
                        Ok(output) => {
                            if call.name == "finish_task" {
                                tracing::info!("Task finished by Agent: {}", call.arguments);
                                finished = true;
                            }
                            output
                        }
                        Err(e) => format!("Error executing tool: {}", e),
                    };

                    messages.push(ChatMessage {
                        role: "tool".to_string(),
                        content: Some(tool_output),
                        name: Some(call.name),
                        tool_calls: None,
                        tool_call_id: Some(call.id),
                    });
                }

                if finished {
                    break;
                }
            } else if result.finish_reason == "stop" {
                tracing::warn!("Agent stopped without calling finish_task. Content: {}", result.content);
                break;
            }
        }
        
        Ok(())
    }
}
