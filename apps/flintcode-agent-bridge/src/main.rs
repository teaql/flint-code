use std::path::PathBuf;
use std::sync::Arc;

use anyhow::{Context, Result};
use clap::Parser;
use tool_runner::remote_config::RemoteExecutionConfig;
use tool_runner::remote_execution::RemoteExecution;

#[derive(Debug, Parser)]
#[command(
    name = "flintcode-agent-bridge",
    author,
    version,
    about = "Agent-independent JSONL bridge to the FlintCode SSH Runner"
)]
struct Cli {
    /// Strict SSH execution target configuration.
    #[arg(long)]
    execution_config: PathBuf,
    /// Named target from the execution configuration.
    #[arg(long)]
    execution_target: Option<String>,
    /// Existing durable Runner session. A replacement is never created.
    #[arg(long)]
    session_id: String,
    /// Workspace-relative application directory inside the Runner session.
    #[arg(long)]
    remote_cwd: String,
    /// Operation-ID namespace identifying the calling Agent adapter.
    #[arg(long, default_value = "agent-adapter")]
    operation_prefix: String,
}

#[tokio::main]
async fn main() -> Result<()> {
    let cli = Cli::parse();
    let config = RemoteExecutionConfig::load(&cli.execution_config)?;
    let target = config.build(cli.execution_target.as_deref())?;
    let execution = RemoteExecution::attach_with_environment(
        target.ssh,
        cli.session_id,
        target.client_policy,
        target.environment_refs,
        cli.operation_prefix,
    )
    .await
    .context("failed to attach Agent bridge to the existing SSH Runner session")?;
    agent_bridge_core::serve(Arc::new(execution), cli.remote_cwd)
        .await
        .context("FlintCode Agent Bridge failed")
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn bridge_cli_requires_an_existing_session_and_remote_cwd() {
        let cli = Cli::try_parse_from([
            "flintcode-agent-bridge",
            "--execution-config",
            "/tmp/execution.toml",
            "--session-id",
            "session-1",
            "--remote-cwd",
            "attempt-01/build",
        ])
        .unwrap();
        assert_eq!(cli.session_id, "session-1");
        assert_eq!(cli.remote_cwd, "attempt-01/build");
    }
}
