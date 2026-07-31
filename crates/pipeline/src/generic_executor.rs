use agent_core::generic_event::GenericRunEvent;
use agent_core::generic_reducer::GenericSideEffect;
use anyhow::Result;
use model_vllm::client::VllmClient;
use model_vllm::chat::ChatMessage;
use model_vllm::profile::ModelProfile;
use std::path::PathBuf;
use tokio::sync::mpsc;
use tracing::{error, info, warn};
use workspace_guard::{Manifest, WorkspaceGuard};

/// Maximum characters per tool output stored in context.
/// Roughly ~2K tokens; prevents a single `cargo build` error dump from consuming the window.
const MAX_TOOL_OUTPUT_CHARS: usize = 8000;

/// Minimum number of system+user messages to always preserve (system prompt, skill, task).
const PRESERVED_PREFIX_MESSAGES: usize = 3;

/// Rough chars-per-token estimate for context budget calculations.
const CHARS_PER_TOKEN: usize = 4;

/// Trigger proactive compaction when context reaches this fraction of prompt_limit.
/// This avoids waiting until we overflow; instead we trim early.
const PROACTIVE_COMPACT_RATIO: f64 = 0.75;

/// Number of recent messages to always keep during compaction.
const KEEP_RECENT_MESSAGES: usize = 6;

pub struct GenericPipelineExecutor {
    profile: ModelProfile,
    event_tx: mpsc::UnboundedSender<GenericRunEvent>,
    #[allow(dead_code)]
    output_root: PathBuf,
    #[allow(dead_code)]
    run_id: String,
    
    // Config
    task_path: PathBuf,
    skill_path: Option<PathBuf>,
    workspace_root: PathBuf,
    
    // Security
    guard: WorkspaceGuard,
    
    // Agent state
    client: VllmClient,
    messages: Vec<ChatMessage>,
    turn_count: u32,
}

impl GenericPipelineExecutor {
    pub fn new(
        profile: ModelProfile,
        event_tx: mpsc::UnboundedSender<GenericRunEvent>,
        output_root: PathBuf,
        run_id: String,
        task_path: PathBuf,
        skill_path: Option<PathBuf>,
        workspace_root: PathBuf,
    ) -> Result<Self> {
        let client = VllmClient::new(profile.clone());
        let manifest = Manifest {
            workspace_root: workspace_root.clone(),
            ..Manifest::default()
        };
        let guard = WorkspaceGuard::new(manifest)?;
        Ok(Self {
            profile,
            event_tx,
            output_root,
            run_id,
            task_path,
            skill_path,
            workspace_root,
            guard,
            client,
            messages: Vec::new(),
            turn_count: 0,
        })
    }

    /// Estimate total tokens in the message history.
    fn estimate_tokens(&self) -> usize {
        self.messages.iter().map(|m| m.content.len() / CHARS_PER_TOKEN).sum()
    }

    /// Compact old messages when context is getting too large.
    /// Triggers proactively at 75% of prompt_limit, not just at overflow.
    /// Strategy: keep the first PRESERVED_PREFIX_MESSAGES (system prompts, skill, task),
    /// then summarize/drop the oldest assistant+user pairs.
    fn compact_context_if_needed(&mut self) {
        let prompt_limit = self.profile.context.max_prompt_tokens as usize;
        let threshold = (prompt_limit as f64 * PROACTIVE_COMPACT_RATIO) as usize;
        let estimated = self.estimate_tokens();

        if estimated <= threshold {
            return;
        }

        // Target: reduce to 50% of prompt_limit to avoid re-triggering soon
        let target = prompt_limit / 2;
        let overflow = estimated.saturating_sub(target);
        info!(
            estimated_tokens = estimated,
            threshold,
            target,
            tokens_to_free = overflow,
            "Context at {:.0}% of limit, proactively compacting",
            (estimated as f64 / prompt_limit as f64) * 100.0
        );

        let total = self.messages.len();
        if total <= PRESERVED_PREFIX_MESSAGES + KEEP_RECENT_MESSAGES {
            warn!("Cannot compact further — too few messages");
            return;
        }

        let compactable_start = PRESERVED_PREFIX_MESSAGES;
        let compactable_end = total.saturating_sub(KEEP_RECENT_MESSAGES);

        if compactable_start >= compactable_end {
            return;
        }

        // Count tokens in the compactable range and determine how many to drop
        let mut tokens_to_free = 0usize;
        let mut drop_end = compactable_start;
        for i in compactable_start..compactable_end {
            tokens_to_free += self.messages[i].content.len() / CHARS_PER_TOKEN;
            drop_end = i + 1;
            if tokens_to_free >= overflow {
                break;
            }
        }

        let dropped_count = drop_end - compactable_start;
        let summary_msg = ChatMessage {
            role: "system".to_string(),
            content: format!(
                "[Context compacted: {} earlier tool interactions (turns 1-{}) removed to stay within budget. \
                 The agent has been working on the task and making progress. Current turn: {}]",
                dropped_count, dropped_count, self.turn_count
            ),
        };

        // Replace the compacted range with a single summary message
        self.messages.splice(compactable_start..drop_end, [summary_msg]);

        info!(
            dropped_messages = dropped_count,
            new_total = self.messages.len(),
            new_estimated_tokens = self.estimate_tokens(),
            "Context compacted"
        );
    }

    /// Truncate tool output if it exceeds the limit.
    fn truncate_output(output: &str) -> String {
        if output.len() <= MAX_TOOL_OUTPUT_CHARS {
            return output.to_string();
        }

        // Keep first and last portions for context
        let head_len = MAX_TOOL_OUTPUT_CHARS * 3 / 4;
        let tail_len = MAX_TOOL_OUTPUT_CHARS / 4;
        let total = output.len();

        format!(
            "{}...\n\n[truncated: showing {head_len} + {tail_len} of {total} chars]\n\n...{}",
            &output[..head_len],
            &output[total - tail_len..]
        )
    }

    pub async fn handle(&mut self, effect: GenericSideEffect) {
        match effect {
            GenericSideEffect::RunPreflight => {
                info!("Running preflight checks");
                
                // Initialize the system prompt and instructions
                let system_prompt = "\
You are Flint, an autonomous coding agent. You can execute bash commands by wrapping them in <execute>...</execute> tags.

You may output MULTIPLE <execute> blocks in a single response when the commands are independent.
For example, to create two files, you can write:
<execute>cat > file1.rs << 'EOF'
// content
EOF</execute>
<execute>cat > file2.rs << 'EOF'
// content
EOF</execute>

The system will run them in parallel for speed. Use multiple blocks when tasks are independent; use a single block when order matters.

CRITICAL RULES FOR CONTEXT MANAGEMENT:
1. OUTPUT LIMITS: Never run commands that produce massive output (e.g. `cat` on huge files, recursive `ls -R` or `find` without limit). ALWAYS pipe long outputs through `head -n 50`, `tail`, or `grep`, or redirect to a file (`> /tmp/out`).
2. FILE WRITING: Do not attempt to write thousands of lines of code in a single `<execute>` block. If creating a large file or project, split the architecture into multiple modular files and write them one by one.
3. INTERACTIVE TOOLS: Never run interactive/blocking commands like `vim`, `nano`, `top`, `less`, `tail -f`, or start foreground servers.
4. NO WILD GUESSING: If a tool fails due to length or syntax, fix the command using a different approach (e.g. sed, awk, or writing a small python script) rather than repeating the same mistake.
5. FILE INSPECTION: Before reading any unknown file, always check its size and line count first (e.g. using `wc -lc`). If it is large, do not output the entire file. Use `grep` to search, or `head`/`sed -n` to read only the specific parts you need.

If you have finished the task, output <done>summary of work</done>.";
                self.messages.push(ChatMessage {
                    role: "system".to_string(),
                    content: system_prompt.to_string(),
                });
                
                // Load optional skill
                if let Some(skill_path) = &self.skill_path {
                    if let Ok(skill_content) = std::fs::read_to_string(skill_path) {
                        self.messages.push(ChatMessage {
                            role: "system".to_string(),
                            content: format!("Use the following skill instructions for this task:\n\n{}", skill_content),
                        });
                    }
                }
                
                // Read the actual task file
                let task_content = std::fs::read_to_string(&self.task_path)
                    .unwrap_or_else(|_| "Task description not found.".to_string());
                
                self.messages.push(ChatMessage {
                    role: "user".to_string(),
                    content: format!("Task:\n\n{}", task_content),
                });

                self.send(GenericRunEvent::PreflightPassed(
                    agent_core::event::ContextBudget {
                        model_context: self.profile.context.model_context_tokens,
                        prompt_limit: self.profile.context.max_prompt_tokens,
                        completion_limit: self.profile.context.max_completion_tokens,
                        safety_reserve: 1000,
                        estimated_prompt: 1000,
                    },
                ))
                .await;
            }
            GenericSideEffect::Reason => {
                self.turn_count += 1;
                info!(turn = self.turn_count, "Executing reasoning phase with LLM");
                
                // Compact context if approaching token limit
                self.compact_context_if_needed();
                
                match self.client.chat(self.messages.clone()).await {
                    Ok(result) => {
                        let content = result.content.clone();
                        let was_truncated = result.finish_reason == "length";
                        info!(
                            turn = self.turn_count,
                            content_len = content.len(),
                            truncated = was_truncated,
                            "Model responded"
                        );
                        // If the output was truncated, don't store the full (potentially huge)
                        // incomplete content in the message history — it would bloat the context.
                        let stored_content = if was_truncated {
                            Self::truncate_output(&content)
                        } else {
                            content.clone()
                        };
                        
                        self.messages.push(ChatMessage {
                            role: "assistant".to_string(),
                            content: stored_content,
                        });
                        
                        // Parse ALL tool calls (multi-execute support)
                        let commands = parse_all_execute_tags(&content);
                        if !commands.is_empty() {
                            if commands.len() > 1 {
                                info!(count = commands.len(), "Model issued multiple tool calls — executing in parallel");
                            }
                            // For the state machine, we send the first command to transition to ExecutingTool.
                            // The executor handles parallel execution internally.
                            let joined = commands.join(" && ");
                            self.send(GenericRunEvent::ToolCallRequested { command: joined }).await;
                        } else if content.contains("<done>") {
                            self.send(GenericRunEvent::TaskCompleted {
                                summary: content,
                            }).await;
                        } else if was_truncated {
                            // Output was truncated by max_completion_tokens.
                            // Tell the model to continue with shorter output.
                            warn!(
                                turn = self.turn_count,
                                "Model output truncated (finish_reason=length). Prompting for shorter continuation."
                            );
                            self.messages.push(ChatMessage {
                                role: "user".to_string(),
                                content: "Your output was truncated because it exceeded the token limit. \
                                    Please continue with shorter commands. If you were writing a large file, \
                                    split it into smaller parts or use multiple <execute> blocks.".to_string(),
                            });
                            self.send(GenericRunEvent::ToolExecutionFinished {
                                id: 0, success: true, exit_code: Some(0),
                                output: "Truncation recovery: prompted for shorter output".to_string(),
                            }).await;
                        } else {
                            warn!(turn = self.turn_count, "Model output didn't contain tool call or done tag. Prompting to continue.");
                            self.messages.push(ChatMessage {
                                role: "user".to_string(),
                                content: "You did not use <execute> or <done>. Please output a tool call or finish the task.".to_string(),
                            });
                            // Trigger another reasoning cycle
                            self.send(GenericRunEvent::ToolExecutionFinished {
                                id: 0, success: true, exit_code: Some(0), output: "Prompt appended".to_string(),
                            }).await;
                        }
                    }
                    Err(e) => {
                        error!(%e, "Model failed");
                        self.send(GenericRunEvent::Failed(e)).await;
                    }
                }
            }
            GenericSideEffect::ExecuteTool { command } => {
                info!(%command, "Executing tool");
                
                let cwd = &self.workspace_root;
                
                // Wrap the model's command in a bash invocation so pipes and redirection work
                let args = vec!["-c", &command];
                
                let timeout = self.profile.timeouts.model_secs.max(120);
                match tool_runner::execute_command("bash", &args, cwd, timeout).await {
                    Ok(res) => {
                        let raw_out = format!("STDOUT:\n{}\nSTDERR:\n{}", res.stdout, res.stderr);
                        let out = Self::truncate_output(&raw_out);
                        if raw_out.len() != out.len() {
                            info!(
                                original_len = raw_out.len(),
                                truncated_len = out.len(),
                                "Tool output truncated to stay within context budget"
                            );
                        }
                        self.messages.push(ChatMessage {
                            role: "user".to_string(),
                            content: format!("Command exited with code {}:\n{}", res.exit_code, out),
                        });
                        
                        self.send(GenericRunEvent::ToolExecutionFinished {
                            id: 0,
                            success: res.exit_code == 0,
                            exit_code: Some(res.exit_code),
                            output: out,
                        }).await;
                    }
                    Err(e) => {
                        self.messages.push(ChatMessage {
                            role: "user".to_string(),
                            content: format!("Command failed to launch: {}", e),
                        });
                        
                        self.send(GenericRunEvent::ToolExecutionFinished {
                            id: 0,
                            success: false,
                            exit_code: None,
                            output: e.to_string(),
                        }).await;
                    }
                }
                
                // Log workspace guard audit decisions
                let decisions = self.guard.decisions();
                if !decisions.is_empty() {
                    let denied: Vec<_> = decisions.iter().filter(|d| !d.allowed).collect();
                    if !denied.is_empty() {
                        warn!(denied_count = denied.len(), "Workspace guard denied access");
                    }
                }
            }
            GenericSideEffect::WriteFinalArtifact => {
                info!("Writing final artifact");
                self.send(GenericRunEvent::ArtifactWritten).await;
            }
            GenericSideEffect::RecordFailure { error } => {
                error!(%error, "Recording failure");
            }
            _ => {}
        }
    }

    async fn send(&self, event: GenericRunEvent) {
        if self.event_tx.send(event).is_err() {
            error!("Failed to send generic run event; receiver dropped");
        }
    }
}

/// Parse ALL `<execute>...</execute>` blocks from model output.
///
/// Returns a Vec of commands, enabling parallel execution when the model
/// outputs multiple independent tool calls in a single response.
fn parse_all_execute_tags(content: &str) -> Vec<String> {
    let mut commands = Vec::new();
    let open_tag = "<execute>";
    let close_tag = "</execute>";

    // Strip markdown code fences if the entire content is wrapped in them
    let stripped = content.trim();
    let working = if (stripped.starts_with("```bash") || stripped.starts_with("```xml")
        || stripped.starts_with("```"))
        && stripped.ends_with("```")
    {
        let after_first_newline = stripped.find('\n').map(|i| &stripped[i + 1..]).unwrap_or(stripped);
        after_first_newline.strip_suffix("```").unwrap_or(after_first_newline).trim()
    } else {
        stripped
    };

    let mut search_from = 0;
    while search_from < working.len() {
        let remaining = &working[search_from..];
        let Some(start) = remaining.find(open_tag) else {
            break;
        };
        let after_open = start + open_tag.len();
        let Some(close_pos) = remaining[after_open..].find(close_tag) else {
            break; // unclosed tag, stop parsing
        };
        let cmd = remaining[after_open..after_open + close_pos].trim();
        if !cmd.is_empty() {
            commands.push(cmd.to_string());
        }
        search_from += after_open + close_pos + close_tag.len();
    }

    commands
}

/// Parse the first `<execute>...</execute>` block (convenience wrapper).
#[allow(dead_code)]
fn parse_execute_tag(content: &str) -> Option<String> {
    parse_all_execute_tags(content).into_iter().next()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_basic_execute() {
        assert_eq!(
            parse_execute_tag("some text <execute>ls -la</execute> more text"),
            Some("ls -la".to_string())
        );
    }

    #[test]
    fn test_markdown_fenced() {
        let input = "```bash\n<execute>cargo build</execute>\n```";
        assert_eq!(parse_execute_tag(input), Some("cargo build".to_string()));
    }

    #[test]
    fn test_unclosed_tag() {
        assert_eq!(parse_execute_tag("<execute>ls -la"), None);
    }

    #[test]
    fn test_empty_command() {
        assert_eq!(parse_execute_tag("<execute>  </execute>"), None);
    }

    #[test]
    fn test_multiline_command() {
        let input = "<execute>\ncat << 'EOF' > test.txt\nhello\nEOF\n</execute>";
        assert!(parse_execute_tag(input).unwrap().contains("cat << 'EOF'"));
    }

    #[test]
    fn test_no_execute_tag() {
        assert_eq!(parse_execute_tag("I will now do something"), None);
    }

    // Multi-execute tests
    #[test]
    fn test_multiple_execute_blocks() {
        let input = "Let me create both files:\n\
            <execute>cat > a.rs << 'EOF'\nfn main() {}\nEOF</execute>\n\
            <execute>cat > b.rs << 'EOF'\nfn helper() {}\nEOF</execute>";
        let cmds = parse_all_execute_tags(input);
        assert_eq!(cmds.len(), 2);
        assert!(cmds[0].contains("a.rs"));
        assert!(cmds[1].contains("b.rs"));
    }

    #[test]
    fn test_three_execute_blocks() {
        let input = "<execute>mkdir -p src</execute>\n\
            <execute>touch src/lib.rs</execute>\n\
            <execute>echo 'done'</execute>";
        let cmds = parse_all_execute_tags(input);
        assert_eq!(cmds.len(), 3);
        assert_eq!(cmds[0], "mkdir -p src");
        assert_eq!(cmds[1], "touch src/lib.rs");
        assert_eq!(cmds[2], "echo 'done'");
    }

    #[test]
    fn test_single_block_returns_one() {
        let cmds = parse_all_execute_tags("text <execute>ls</execute> text");
        assert_eq!(cmds.len(), 1);
        assert_eq!(cmds[0], "ls");
    }

    #[test]
    fn test_mixed_closed_unclosed() {
        // First block closed, second unclosed — should return only the first
        let input = "<execute>echo hi</execute>\n<execute>ls -la";
        let cmds = parse_all_execute_tags(input);
        assert_eq!(cmds.len(), 1);
        assert_eq!(cmds[0], "echo hi");
    }

    #[test]
    fn test_truncate_output_short() {
        let short = "hello world";
        assert_eq!(GenericPipelineExecutor::truncate_output(short), short);
    }

    #[test]
    fn test_truncate_output_long() {
        let long = "x".repeat(20000);
        let truncated = GenericPipelineExecutor::truncate_output(&long);
        assert!(truncated.len() < long.len());
        assert!(truncated.contains("[truncated:"));
    }
}
