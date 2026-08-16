//! Agentic build loop using LLM tool calling.
//!
//! Implements a ReAct (Reason-Act) loop where the LLM inspects a project,
//! runs build commands, reads/writes files, and fixes compilation errors
//! autonomously. This makes the build pipeline language-agnostic — the LLM
//! determines the appropriate build commands based on the project structure.

use std::path::Path;
use std::sync::Arc;
use tracing::{error, info, warn};

use crate::tools::{
    DeclaredCommandEnvironment, build_tool_definitions, execute_tool_remote_with_environment,
    execute_tool_with_environment,
};
use agent_core::loop_guard::LoopGuard;
use model_vllm::backend::ModelClient;
use model_vllm::chat::{ChatMessage, FunctionCall, ToolCall};

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
        total_tool_calls: usize,
    },
    /// Agent hit the maximum iteration limit without completing
    MaxIterationsReached {
        iterations: usize,
        total_tool_calls: usize,
        last_failed_build: Option<String>,
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
/// * `client` - The configured model backend for inference
/// * `sandbox_dir` - The project directory (all tool operations are sandboxed here)
/// * `system_prompt` - System prompt describing the agent's role
/// * `user_prompt` - Initial task description with project context
/// * `max_iterations` - Maximum number of LLM round-trips before giving up
pub async fn run_agent_loop(
    client: &ModelClient,
    sandbox_dir: &Path,
    system_prompt: &str,
    user_prompt: &str,
    max_iterations: usize,
    event_tx: Option<tokio::sync::mpsc::Sender<agent_core::RunEvent>>,
) -> AgentLoopResult {
    run_agent_loop_with_environment(
        client,
        sandbox_dir,
        system_prompt,
        user_prompt,
        max_iterations,
        event_tx,
        &DeclaredCommandEnvironment::default(),
    )
    .await
}

/// Run an agent loop whose allowlisted runtime commands receive only the
/// opaque environment declared by the current typed follow-up contract.
pub(crate) async fn run_agent_loop_with_environment(
    client: &ModelClient,
    sandbox_dir: &Path,
    system_prompt: &str,
    user_prompt: &str,
    max_iterations: usize,
    event_tx: Option<tokio::sync::mpsc::Sender<agent_core::RunEvent>>,
    command_environment: &DeclaredCommandEnvironment,
) -> AgentLoopResult {
    run_agent_loop_in_workspace(
        client,
        AgentWorkspace::Local(sandbox_dir),
        system_prompt,
        user_prompt,
        max_iterations,
        event_tx,
        command_environment,
    )
    .await
}

/// Run an agent loop whose only project I/O and command surface is one remote
/// runner session. There is deliberately no local fallback in this path.
pub(crate) async fn run_agent_loop_remote_with_environment(
    client: &ModelClient,
    execution: Arc<crate::execution::RemoteExecution>,
    remote_cwd: &str,
    system_prompt: &str,
    user_prompt: &str,
    max_iterations: usize,
    event_tx: Option<tokio::sync::mpsc::Sender<agent_core::RunEvent>>,
    command_environment: &DeclaredCommandEnvironment,
) -> AgentLoopResult {
    run_agent_loop_in_workspace(
        client,
        AgentWorkspace::Remote {
            execution,
            cwd: remote_cwd,
        },
        system_prompt,
        user_prompt,
        max_iterations,
        event_tx,
        command_environment,
    )
    .await
}

enum AgentWorkspace<'a> {
    Local(&'a Path),
    Remote {
        execution: Arc<crate::execution::RemoteExecution>,
        cwd: &'a str,
    },
}

async fn run_agent_loop_in_workspace(
    client: &ModelClient,
    workspace: AgentWorkspace<'_>,
    system_prompt: &str,
    user_prompt: &str,
    max_iterations: usize,
    event_tx: Option<tokio::sync::mpsc::Sender<agent_core::RunEvent>>,
    command_environment: &DeclaredCommandEnvironment,
) -> AgentLoopResult {
    let tools = build_tool_definitions();

    let mut messages = vec![
        ChatMessage::system(system_prompt),
        ChatMessage::user(user_prompt),
    ];

    let mut total_tool_calls = 0usize;
    let mut iterations_without_progress = 0usize;
    let mut last_failed_build = None;
    let mut last_failed_tool = None;
    let mut loop_guard = LoopGuard::new(3);
    let max_prompt_bytes = (client.profile().context.max_prompt_tokens as usize).saturating_mul(3);

    for iteration in 0..max_iterations {
        compact_messages(&mut messages, max_prompt_bytes);
        if let Some(detection) = loop_guard.record_prompt(&messages) {
            return AgentLoopResult::Failed {
                error: format!("Agent conversation loop detected: {detection}"),
                iterations: iteration + 1,
                total_tool_calls,
            };
        }
        info!(
            iteration,
            total_tool_calls,
            msgs = messages.len(),
            "Agent loop iteration"
        );

        // Call the model with tool definitions
        let result = client
            .chat(messages.clone(), Some(tools.clone()), None)
            .await;

        match result {
            Ok(model_result) => {
                if let Some(tx) = &event_tx {
                    let _ = tx
                        .send(agent_core::RunEvent::ModelUsageRecorded(
                            model_result.usage.clone(),
                        ))
                        .await;
                }

                let tool_calls = model_result.tool_calls.map(|calls| {
                    calls
                        .into_iter()
                        .map(|call| ToolCall {
                            id: call.id,
                            r#type: "function".to_string(),
                            function: FunctionCall {
                                name: call.name,
                                arguments: call.arguments,
                            },
                        })
                        .collect::<Vec<_>>()
                });

                match tool_calls {
                    Some(ref calls) if !calls.is_empty() => {
                        // Model wants to call tools — execute them
                        info!(
                            iteration,
                            num_calls = calls.len(),
                            "Agent requested tool calls"
                        );

                        let mut iteration_made_progress = false;

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

                            let command_str = if tc.function.name == "run_command" {
                                if let Ok(args) = serde_json::from_str::<serde_json::Value>(
                                    &tc.function.arguments,
                                ) {
                                    args.get("command")
                                        .and_then(|c| c.as_str())
                                        .unwrap_or(&tc.function.arguments)
                                        .to_string()
                                } else {
                                    tc.function.arguments.clone()
                                }
                            } else {
                                format!("{} {}", tc.function.name, tc.function.arguments)
                            };

                            let cmd_id = total_tool_calls as u64;

                            if let Some(tx) = &event_tx {
                                tx.send(agent_core::RunEvent::ToolProcessStarted {
                                    id: cmd_id,
                                    command: command_str.clone(),
                                })
                                .await
                                .ok();
                            }

                            let tool_result = match &workspace {
                                AgentWorkspace::Local(sandbox_dir) => {
                                    execute_tool_with_environment(
                                        &tc.function.name,
                                        &tc.function.arguments,
                                        sandbox_dir,
                                        command_environment,
                                    )
                                    .await
                                }
                                AgentWorkspace::Remote { execution, cwd } => {
                                    execute_tool_remote_with_environment(
                                        &tc.function.name,
                                        &tc.function.arguments,
                                        execution,
                                        cwd,
                                        command_environment,
                                    )
                                    .await
                                }
                            };

                            if !tool_result.success {
                                last_failed_tool = Some(format!(
                                    "Tool `{}` failed for `{}`:\n{}",
                                    tc.function.name, command_str, tool_result.output
                                ));
                                if crate::executor::is_infrastructure_diagnostic(
                                    &tool_result.output,
                                ) {
                                    return AgentLoopResult::Failed {
                                        error: format!(
                                            "{} Agent tool `{}` stopped on an infrastructure failure:\n{}",
                                            agent_core::event::INFRASTRUCTURE_FAILURE_PREFIX,
                                            tc.function.name,
                                            tool_result.output
                                        ),
                                        iterations: iteration + 1,
                                        total_tool_calls,
                                    };
                                }
                            }

                            if tc.function.name == "write_file" && tool_result.success {
                                iteration_made_progress = true;
                            }
                            if tc.function.name == "run_command" {
                                if tool_result.success && is_build_command(&command_str) {
                                    iteration_made_progress = true;
                                } else if !tool_result.success
                                    && is_build_command(&command_str)
                                    && (last_failed_build.is_none()
                                        || tool_result
                                            .output
                                            .to_ascii_lowercase()
                                            .contains("error"))
                                {
                                    last_failed_build = Some(tool_result.output.clone());
                                }
                            }

                            if let Some(detection) = loop_guard.record_tool_call(
                                &tc.function.name,
                                &tc.function.arguments,
                                &tool_result.output,
                            ) {
                                return AgentLoopResult::Failed {
                                    error: format!("Repeated tool loop detected: {detection}"),
                                    iterations: iteration + 1,
                                    total_tool_calls,
                                };
                            }

                            if let Some(tx) = &event_tx {
                                tx.send(agent_core::RunEvent::ToolProcessFinished {
                                    id: cmd_id,
                                    success: tool_result.success,
                                    exit_code: tool_result.exit_code,
                                })
                                .await
                                .ok();
                            }

                            messages.push(ChatMessage::tool_result(&tc.id, &tool_result.output));
                        }

                        if iteration_made_progress {
                            iterations_without_progress = 0;
                        } else {
                            iterations_without_progress += 1;
                        }

                        // Start nudging after 2 idle iterations and repeat every
                        // round with an escalating message and a hard countdown.
                        // Weaker models often ignore a single nudge; repeating it
                        // with a visible remaining-chance counter significantly
                        // improves the chance they take the required action.
                        const NUDGE_AFTER: usize = 2;
                        const FAIL_AFTER: usize = 5;
                        if iterations_without_progress >= NUDGE_AFTER
                            && iterations_without_progress < FAIL_AFTER
                        {
                            let remaining = FAIL_AFTER - iterations_without_progress;
                            warn!(
                                iteration,
                                iterations_without_progress,
                                remaining,
                                "Agent stuck in exploration — injecting escalating nudge"
                            );
                            let urgency = if iterations_without_progress >= NUDGE_AFTER + 2 {
                                "⛔ FINAL WARNING"
                            } else {
                                "⚠️ WARNING"
                            };
                            messages.push(ChatMessage::user(format!(
                                "{urgency}: You have made no build progress for {iterations_without_progress} consecutive iterations. \
                                 You have {remaining} attempt(s) remaining before this task is aborted.\n\
                                 STOP reading files. Take immediate action:\n\
                                 1. If Cargo.toml exists → run_command({{\"command\": \"cargo check\"}})\n\
                                 2. If pom.xml exists    → run_command({{\"command\": \"mvn compile -f pom.xml\"}})\n\
                                 3. If business logic is missing → use write_file to create it NOW.\n\
                                 RULES: Never read or modify lib/src or other generated library source.\n\
                                 DO NOT call list_directory or read_file again. Compile immediately."
                            )));
                        }
                        if iterations_without_progress >= FAIL_AFTER {
                            let diagnostic = last_failed_build
                                .as_deref()
                                .or(last_failed_tool.as_deref())
                                .unwrap_or("No failed tool or build diagnostic was captured; the agent only explored successfully");
                            return AgentLoopResult::Failed {
                                error: format!(
                                    "Agent made no build progress for {iterations_without_progress} consecutive iterations. Last failed build:\n{diagnostic}"
                                ),
                                iterations: iteration + 1,
                                total_tool_calls,
                            };
                        }
                    }
                    _ => {
                        // No tool calls — agent has finished
                        info!(
                            iteration,
                            total_tool_calls, "Agent completed with text response"
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
                if let Some(tx) = &event_tx {
                    let _ = tx
                        .send(agent_core::RunEvent::ModelFailed(
                            agent_core::error::AgentError::InfrastructureError {
                                detail: e.to_string(),
                            },
                        ))
                        .await;
                }
                error!(iteration, %e, "Model call failed in agent loop");
                return AgentLoopResult::Failed {
                    error: format!(
                        "{} Agent-loop model transport failed: {e}",
                        agent_core::event::INFRASTRUCTURE_FAILURE_PREFIX
                    ),
                    iterations: iteration + 1,
                    total_tool_calls,
                };
            }
        }
    }

    warn!(
        max_iterations,
        total_tool_calls, "Agent loop reached max iterations without completing"
    );
    AgentLoopResult::MaxIterationsReached {
        iterations: max_iterations,
        total_tool_calls,
        last_failed_build,
    }
}

fn is_build_command(command: &str) -> bool {
    let normalized = command.to_ascii_lowercase();
    normalized.contains("cargo check")
        || normalized.contains("cargo test")
        || normalized.contains("mvn compile")
        || normalized.contains("mvn test")
        || normalized.contains("gradle build")
        || normalized.contains("gradlew build")
}

fn message_bytes(message: &ChatMessage) -> usize {
    let content = message.content.as_ref().map_or(0, String::len);
    let calls = message.tool_calls.as_ref().map_or(0, |calls| {
        calls
            .iter()
            .map(|call| call.function.name.len() + call.function.arguments.len())
            .sum()
    });
    content + calls
}

fn total_message_bytes(messages: &[ChatMessage]) -> usize {
    messages.iter().map(message_bytes).sum()
}

/// Bound historical context while retaining the system prompt, initial task, and
/// complete assistant/tool turn boundaries required by tool-calling APIs.
fn compact_messages(messages: &mut Vec<ChatMessage>, max_bytes: usize) {
    if total_message_bytes(messages) <= max_bytes || messages.len() <= 2 {
        return;
    }

    for message in messages.iter_mut().skip(2) {
        if let Some(calls) = message.tool_calls.as_mut() {
            for call in calls {
                if call.function.arguments.len() > 512 {
                    call.function.arguments = r#"{"context_compacted":true}"#.to_string();
                }
            }
        }
        if message.role == "tool" {
            if let Some(content) = message.content.as_mut() {
                if content.len() > 2_000 {
                    *content = format!(
                        "[Historical tool output compacted; original size: {} bytes]",
                        content.len()
                    );
                }
            }
        }
    }

    while total_message_bytes(messages) > max_bytes && messages.len() > 4 {
        let start = 2;
        let mut end = start + 1;
        if messages[start].role == "assistant" {
            while end < messages.len() && messages[end].role == "tool" {
                end += 1;
            }
        }
        if end >= messages.len() {
            break;
        }
        messages.drain(start..end);
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn tool_turn(id: &str, payload: &str) -> Vec<ChatMessage> {
        vec![
            ChatMessage::assistant_with_tool_calls(
                "",
                vec![ToolCall {
                    id: id.to_string(),
                    r#type: "function".to_string(),
                    function: FunctionCall {
                        name: "write_file".to_string(),
                        arguments: payload.to_string(),
                    },
                }],
            ),
            ChatMessage::tool_result(id, payload),
        ]
    }

    #[test]
    fn context_compaction_keeps_complete_tool_turns() {
        let mut messages = vec![ChatMessage::system("system"), ChatMessage::user("task")];
        messages.extend(tool_turn("old", &"x".repeat(10_000)));
        messages.extend(tool_turn("new", &"y".repeat(10_000)));

        compact_messages(&mut messages, 1_000);

        assert_eq!(messages[0].role, "system");
        assert_eq!(messages[1].role, "user");
        assert!(total_message_bytes(&messages) <= 1_000);
        for tool in messages.iter().filter(|message| message.role == "tool") {
            let id = tool.tool_call_id.as_deref().expect("tool call id");
            assert!(messages.iter().any(|message| {
                message
                    .tool_calls
                    .as_ref()
                    .is_some_and(|calls| calls.iter().any(|call| call.id == id))
            }));
        }
    }
}
