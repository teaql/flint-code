//! Agentic build loop using LLM tool calling.
//!
//! Implements a ReAct (Reason-Act) loop where the LLM inspects a project,
//! runs build commands, reads/writes files, and fixes compilation errors
//! autonomously. This makes the build pipeline language-agnostic — the LLM
//! determines the appropriate build commands based on the project structure.

use std::path::Path;
use tracing::{info, warn, error};

use model_vllm::chat::{ChatMessage, ToolCallInfo};
use model_vllm::client::VllmClient;
use crate::tools::{build_tool_definitions, execute_tool};

/// Result of an agentic build loop
#[derive(Debug)]
pub enum AgentLoopResult {
    /// Agent completed successfully (returned text with no more tool calls)
    Completed {
        /// Final text summary from the agent
        summary: String,
        /// Number of LLM round-trips
        iterations: usize,
        /// Total number of tool calls executed
        total_tool_calls: usize,
    },
    /// Agent failed with an unrecoverable error
    Failed {
        error: String,
        iterations: usize,
    },
    /// Agent hit the maximum iteration limit without completing
    MaxIterationsReached {
        iterations: usize,
        total_tool_calls: usize,
    },
}

/// Run an agentic build loop that uses tool calling to compile and fix a project.
///
/// The LLM is given tools (run_command, read_file, write_file, list_directory)
/// and a prompt describing the task. It autonomously inspects the project,
/// identifies the build system, compiles, and fixes errors until the build
/// succeeds or the iteration limit is reached.
///
/// # Arguments
/// * `client` - The vLLM client for model inference
/// * `sandbox_dir` - The project directory (all tool operations are sandboxed here)
/// * `system_prompt` - System prompt describing the agent's role
/// * `user_prompt` - Initial task description with project context
/// * `max_iterations` - Maximum number of LLM round-trips before giving up
pub async fn run_agent_loop(
    client: &VllmClient,
    sandbox_dir: &Path,
    system_prompt: &str,
    user_prompt: &str,
    max_iterations: usize,
) -> AgentLoopResult {
    let tools = build_tool_definitions();

    let mut messages = vec![
        ChatMessage::system(system_prompt),
        ChatMessage::user(user_prompt),
    ];

    let mut total_tool_calls = 0usize;

    for iteration in 0..max_iterations {
        info!(iteration, total_tool_calls, msgs = messages.len(), "Agent loop iteration");

        // Call the model with tool definitions
        let result = client.chat_with_tools(messages.clone(), &tools).await;

        match result {
            Ok(model_result) => {
                // Deserialize tool_calls from the JSON Value
                let tool_calls: Option<Vec<ToolCallInfo>> = model_result
                    .tool_calls
                    .as_ref()
                    .and_then(|v| serde_json::from_value(v.clone()).ok());

                match tool_calls {
                    Some(ref calls) if !calls.is_empty() => {
                        // Model wants to call tools — execute them
                        info!(
                            iteration,
                            num_calls = calls.len(),
                            "Agent requested tool calls"
                        );

                        // Add the assistant's message (with tool calls) to history
                        messages.push(ChatMessage::assistant_with_tool_calls(
                            &model_result.content,
                            calls.clone(),
                        ));

                        // Execute each tool call and append results
                        for tc in calls {
                            total_tool_calls += 1;
                            info!(
                                iteration,
                                tool = %tc.function.name,
                                id = %tc.id,
                                "Executing tool call"
                            );

                            let tool_result = execute_tool(
                                &tc.function.name,
                                &tc.function.arguments,
                                sandbox_dir,
                            )
                            .await;

                            messages.push(ChatMessage::tool_result(&tc.id, &tool_result));
                        }
                    }
                    _ => {
                        // No tool calls — agent has finished
                        info!(
                            iteration,
                            total_tool_calls,
                            "Agent completed with text response"
                        );
                        return AgentLoopResult::Completed {
                            summary: model_result.content,
                            iterations: iteration + 1,
                            total_tool_calls,
                        };
                    }
                }
            }
            Err(e) => {
                error!(iteration, %e, "Model call failed in agent loop");
                return AgentLoopResult::Failed {
                    error: e.to_string(),
                    iterations: iteration + 1,
                };
            }
        }
    }

    warn!(
        max_iterations,
        total_tool_calls,
        "Agent loop reached max iterations without completing"
    );
    AgentLoopResult::MaxIterationsReached {
        iterations: max_iterations,
        total_tool_calls,
    }
}
