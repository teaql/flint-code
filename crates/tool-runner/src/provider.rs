use crate::{ToolError, ToolResult};
use async_trait::async_trait;
use std::path::Path;
use std::time::Duration;
use tokio::process::Command;
use tracing::info;

#[async_trait]
pub trait EnvironmentProvider: Send + Sync {
    /// Execute a command in the environment
    async fn execute_command(
        &self,
        command: &str,
        args: &[&str],
        cwd: &Path,
        timeout_secs: u64,
    ) -> Result<ToolResult, ToolError>;

    /// Read file content from the environment
    async fn read_file(&self, path: &Path) -> Result<String, ToolError>;

    /// Write file content to the environment
    async fn write_file(&self, path: &Path, content: &str) -> Result<(), ToolError>;
}

/// Local execution environment (Host mode)
pub struct LocalEnvironment;

#[async_trait]
impl EnvironmentProvider for LocalEnvironment {
    async fn execute_command(
        &self,
        command: &str,
        args: &[&str],
        cwd: &Path,
        timeout_secs: u64,
    ) -> Result<ToolResult, ToolError> {
        let full = format!("{} {}", command, args.join(" "));
        info!(command = %full, "Executing tool command locally");

        let start = std::time::Instant::now();
        let output = tokio::time::timeout(
            Duration::from_secs(timeout_secs),
            Command::new(command).args(args).current_dir(cwd).output(),
        )
        .await
        .map_err(|_| ToolError::Timeout {
            seconds: timeout_secs,
        })?
        .map_err(|e| ToolError::ExecFailed {
            code: -1,
            stderr: e.to_string(),
        })?;

        let elapsed = start.elapsed().as_secs_f64();
        let stdout = String::from_utf8_lossy(&output.stdout).to_string();
        let stderr = String::from_utf8_lossy(&output.stderr).to_string();
        let code = output.status.code().unwrap_or(-1);

        Ok(ToolResult {
            command: full,
            exit_code: code,
            stdout,
            stderr,
            elapsed_secs: elapsed,
        })
    }

    async fn read_file(&self, path: &Path) -> Result<String, ToolError> {
        tokio::fs::read_to_string(path)
            .await
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: format!("Failed to read file {}: {}", path.display(), e),
            })
    }

    async fn write_file(&self, path: &Path, content: &str) -> Result<(), ToolError> {
        if let Some(parent) = path.parent() {
            let _ = tokio::fs::create_dir_all(parent).await;
        }
        tokio::fs::write(path, content)
            .await
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: format!("Failed to write file {}: {}", path.display(), e),
            })
    }
}

/// Docker execution environment (stub)
pub struct DockerEnvironment {
    pub container_id: String,
}

#[async_trait]
impl EnvironmentProvider for DockerEnvironment {
    async fn execute_command(
        &self,
        cmd: &str,
        args: &[&str],
        cwd: &Path,
        timeout: u64,
    ) -> Result<ToolResult, ToolError> {
        let full = format!(
            "docker exec {} {} {}",
            self.container_id,
            cmd,
            args.join(" ")
        );
        info!(command = %full, "Executing tool command in Docker");

        let start = std::time::Instant::now();
        let cwd_str = cwd.to_str().unwrap_or("/");
        let mut docker_args = vec!["exec", "-w", cwd_str, &self.container_id, cmd];
        docker_args.extend(args);

        let output = tokio::time::timeout(
            Duration::from_secs(timeout),
            Command::new("docker").args(&docker_args).output(),
        )
        .await
        .map_err(|_| ToolError::Timeout { seconds: timeout })?
        .map_err(|e| ToolError::ExecFailed {
            code: -1,
            stderr: e.to_string(),
        })?;

        let elapsed = start.elapsed().as_secs_f64();
        let stdout = String::from_utf8_lossy(&output.stdout).to_string();
        let stderr = String::from_utf8_lossy(&output.stderr).to_string();
        let code = output.status.code().unwrap_or(-1);

        Ok(ToolResult {
            command: full,
            exit_code: code,
            stdout,
            stderr,
            elapsed_secs: elapsed,
        })
    }

    async fn read_file(&self, path: &Path) -> Result<String, ToolError> {
        let output = Command::new("docker")
            .args([
                "exec",
                &self.container_id,
                "cat",
                path.to_str().unwrap_or(""),
            ])
            .output()
            .await
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: e.to_string(),
            })?;

        if output.status.success() {
            Ok(String::from_utf8_lossy(&output.stdout).to_string())
        } else {
            Err(ToolError::ExecFailed {
                code: output.status.code().unwrap_or(-1),
                stderr: String::from_utf8_lossy(&output.stderr).to_string(),
            })
        }
    }

    async fn write_file(&self, path: &Path, content: &str) -> Result<(), ToolError> {
        if let Some(parent) = path.parent() {
            let _ = Command::new("docker")
                .args([
                    "exec",
                    &self.container_id,
                    "mkdir",
                    "-p",
                    parent.to_str().unwrap_or(""),
                ])
                .output()
                .await;
        }

        let sh_cmd = format!("cat > '{}'", path.display());
        let mut child = Command::new("docker")
            .args(["exec", "-i", &self.container_id, "sh", "-c", &sh_cmd])
            .stdin(std::process::Stdio::piped())
            .spawn()
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: e.to_string(),
            })?;

        if let Some(mut stdin) = child.stdin.take() {
            use tokio::io::AsyncWriteExt;
            let _ = stdin.write_all(content.as_bytes()).await;
        }

        let output = child
            .wait_with_output()
            .await
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: e.to_string(),
            })?;

        if output.status.success() {
            Ok(())
        } else {
            Err(ToolError::ExecFailed {
                code: output.status.code().unwrap_or(-1),
                stderr: String::from_utf8_lossy(&output.stderr).to_string(),
            })
        }
    }
}

/// SSH execution environment (stub)
pub struct SshEnvironment {
    pub host: String,
}

#[async_trait]
impl EnvironmentProvider for SshEnvironment {
    async fn execute_command(
        &self,
        cmd: &str,
        args: &[&str],
        cwd: &Path,
        timeout: u64,
    ) -> Result<ToolResult, ToolError> {
        let full = format!(
            "ssh {} cd {} && {} {}",
            self.host,
            cwd.display(),
            cmd,
            args.join(" ")
        );
        info!(command = %full, "Executing tool command via SSH");

        let start = std::time::Instant::now();
        let cwd_str = cwd.to_str().unwrap_or("/");
        let remote_cmd = format!("cd '{}' && {} {}", cwd_str, cmd, args.join(" "));

        let output = tokio::time::timeout(
            Duration::from_secs(timeout),
            Command::new("ssh").args([&self.host, &remote_cmd]).output(),
        )
        .await
        .map_err(|_| ToolError::Timeout { seconds: timeout })?
        .map_err(|e| ToolError::ExecFailed {
            code: -1,
            stderr: e.to_string(),
        })?;

        let elapsed = start.elapsed().as_secs_f64();
        let stdout = String::from_utf8_lossy(&output.stdout).to_string();
        let stderr = String::from_utf8_lossy(&output.stderr).to_string();
        let code = output.status.code().unwrap_or(-1);

        Ok(ToolResult {
            command: full,
            exit_code: code,
            stdout,
            stderr,
            elapsed_secs: elapsed,
        })
    }

    async fn read_file(&self, path: &Path) -> Result<String, ToolError> {
        let output = Command::new("ssh")
            .args([&self.host, "cat", path.to_str().unwrap_or("")])
            .output()
            .await
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: e.to_string(),
            })?;

        if output.status.success() {
            Ok(String::from_utf8_lossy(&output.stdout).to_string())
        } else {
            Err(ToolError::ExecFailed {
                code: output.status.code().unwrap_or(-1),
                stderr: String::from_utf8_lossy(&output.stderr).to_string(),
            })
        }
    }

    async fn write_file(&self, path: &Path, content: &str) -> Result<(), ToolError> {
        if let Some(parent) = path.parent() {
            let _ = Command::new("ssh")
                .args([&self.host, "mkdir", "-p", parent.to_str().unwrap_or("")])
                .output()
                .await;
        }

        let sh_cmd = format!("cat > '{}'", path.display());
        let mut child = Command::new("ssh")
            .args([&self.host, &sh_cmd])
            .stdin(std::process::Stdio::piped())
            .spawn()
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: e.to_string(),
            })?;

        if let Some(mut stdin) = child.stdin.take() {
            use tokio::io::AsyncWriteExt;
            let _ = stdin.write_all(content.as_bytes()).await;
        }

        let output = child
            .wait_with_output()
            .await
            .map_err(|e| ToolError::ExecFailed {
                code: -1,
                stderr: e.to_string(),
            })?;

        if output.status.success() {
            Ok(())
        } else {
            Err(ToolError::ExecFailed {
                code: output.status.code().unwrap_or(-1),
                stderr: String::from_utf8_lossy(&output.stderr).to_string(),
            })
        }
    }
}
