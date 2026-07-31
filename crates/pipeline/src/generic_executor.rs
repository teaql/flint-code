use agent_core::generic_event::GenericRunEvent;
use agent_core::generic_reducer::GenericSideEffect;
use anyhow::Result;
use model_vllm::client::VllmClient;
use model_vllm::chat::ChatMessage;
use model_vllm::profile::ModelProfile;
use std::path::PathBuf;
use tokio::sync::mpsc;
use tracing::{error, info, warn};

pub struct GenericPipelineExecutor {
    profile: ModelProfile,
    event_tx: mpsc::UnboundedSender<GenericRunEvent>,
    output_root: PathBuf,
    run_id: String,
    
    // Config
    task_path: PathBuf,
    skill_path: Option<PathBuf>,
    workspace_root: PathBuf,
    
    // Agent state
    client: VllmClient,
    messages: Vec<ChatMessage>,
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
        Ok(Self {
            profile,
            event_tx,
            output_root,
            run_id,
            task_path,
            skill_path,
            workspace_root,
            client,
            messages: Vec::new(),
        })
    }

    pub async fn handle(&mut self, effect: GenericSideEffect) {
        match effect {
            GenericSideEffect::RunPreflight => {
                info!("Running preflight checks");
                
                // Initialize the system prompt and instructions
                let system_prompt = "\
You are Flint, an autonomous coding agent. You can execute bash commands by wrapping them in <execute>...</execute> tags. Only output ONE command per response.

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
                info!("Executing reasoning phase with LLM");
                
                match self.client.chat(self.messages.clone()).await {
                    Ok(result) => {
                        let content = result.content.clone();
                        info!(%content, "Model responded");
                        
                        self.messages.push(ChatMessage {
                            role: "assistant".to_string(),
                            content: content.clone(),
                        });
                        
                        // Parse tool calls
                        if let Some(cmd) = parse_execute_tag(&content) {
                            self.send(GenericRunEvent::ToolCallRequested { command: cmd }).await;
                        } else if content.contains("<done>") {
                            self.send(GenericRunEvent::TaskCompleted {
                                summary: content,
                            }).await;
                        } else {
                            warn!("Model output didn't contain tool call or done tag. Prompting to continue.");
                            self.messages.push(ChatMessage {
                                role: "user".to_string(),
                                content: "You did not use <execute> or <done>. Please output a tool call or finish the task.".to_string(),
                            });
                            // Trigger another reasoning cycle implicitly via a dummy execution
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
                        let out = format!("STDOUT:\n{}\nSTDERR:\n{}", res.stdout, res.stderr);
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

fn parse_execute_tag(content: &str) -> Option<String> {
    if let Some(start) = content.find("<execute>") {
        if let Some(end) = content[start..].find("</execute>") {
            let cmd = &content[start + 9..start + end];
            return Some(cmd.trim().to_string());
        }
    }
    None
}
