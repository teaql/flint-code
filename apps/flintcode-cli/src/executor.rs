use agent_core::agent_loop::ToolExecutor;
use agent_core::error::AgentError;
use std::future::Future;

pub struct StandardToolExecutor;

impl ToolExecutor for StandardToolExecutor {
    async fn execute(&self, name: &str, arguments: &str) -> Result<String, AgentError> {
        match name {
            "read_file" => {
                if let Ok(args) = serde_json::from_str::<serde_json::Value>(arguments) {
                    if let Some(path) = args.get("path").and_then(|v| v.as_str()) {
                        std::fs::read_to_string(path).map_err(|e| AgentError::InfrastructureError { detail: e.to_string() })
                    } else {
                        Ok("Error: missing 'path' argument".to_string())
                    }
                } else {
                    Ok("Error: invalid json arguments".to_string())
                }
            }
            "write_file" => {
                if let Ok(args) = serde_json::from_str::<serde_json::Value>(arguments) {
                    if let (Some(path), Some(content)) = (args.get("path").and_then(|v| v.as_str()), args.get("content").and_then(|v| v.as_str())) {
                        match std::fs::write(path, content) {
                            Ok(_) => Ok(format!("Successfully wrote to {}", path)),
                            Err(e) => Ok(format!("Error writing file: {}", e)),
                        }
                    } else {
                        Ok("Error: missing 'path' or 'content' argument".to_string())
                    }
                } else {
                    Ok("Error: invalid json arguments".to_string())
                }
            }
            "run_command" => {
                if let Ok(args) = serde_json::from_str::<serde_json::Value>(arguments) {
                    if let Some(cmd) = args.get("command").and_then(|v| v.as_str()) {
                        match std::process::Command::new("sh").arg("-c").arg(cmd).output() {
                            Ok(output) => {
                                let stdout = String::from_utf8_lossy(&output.stdout);
                                let stderr = String::from_utf8_lossy(&output.stderr);
                                Ok(format!("Exit Status: {}\nSTDOUT:\n{}\nSTDERR:\n{}", output.status, stdout, stderr))
                            }
                            Err(e) => Ok(format!("Error running command: {}", e)),
                        }
                    } else {
                        Ok("Error: missing 'command' argument".to_string())
                    }
                } else {
                    Ok("Error: invalid json arguments".to_string())
                }
            }
            "finish_task" => {
                Ok("Task recorded as finished.".to_string())
            }
            _ => Ok(format!("Error: unknown tool {}", name)),
        }
    }
}
