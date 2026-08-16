//! FlintCode TUI Legacy — maintenance-only Ratatui interface.

mod app;
mod input;
mod ui;
mod ui_components;
mod widgets;

use anyhow::Result;
use clap::Parser;
use std::path::PathBuf;
use tracing_appender;
use tracing_subscriber::{EnvFilter, fmt, prelude::*};

#[derive(Parser, Debug)]
#[command(
    name = "flintcode-tui-legacy",
    about = "Legacy interactive TUI for the FlintCode secure Agent harness"
)]
pub struct CliArgs {
    /// Path to model profile TOML file (e.g. profiles/qwen-3.6-coder.toml)
    #[arg(short, long)]
    pub profile: Option<PathBuf>,

    /// Path to initial task directory or task.toml to run automatically
    #[arg(short, long)]
    pub task: Option<PathBuf>,

    /// Strict SSH execution target configuration. Project work never falls back locally.
    #[arg(long)]
    pub execution_config: PathBuf,

    /// Named target from the execution configuration (defaults to its default_target).
    #[arg(long)]
    pub execution_target: Option<String>,

    /// Reattach the first task to an existing durable runner session.
    #[arg(long)]
    pub resume_session: Option<String>,
}

const PIPELINE_WORKER_STACK_BYTES: usize = 16 * 1024 * 1024;

fn main() -> Result<()> {
    tokio::runtime::Builder::new_multi_thread()
        .enable_all()
        .thread_stack_size(PIPELINE_WORKER_STACK_BYTES)
        .build()?
        .block_on(async_main())
}

async fn async_main() -> Result<()> {
    let args = CliArgs::parse();

    // File-based logging (don't pollute the TUI)
    let log_dir = dirs::data_local_dir()
        .unwrap_or_else(|| PathBuf::from("."))
        .join("klintcode")
        .join("logs");
    std::fs::create_dir_all(&log_dir)?;
    let file_appender = tracing_appender::rolling::daily(&log_dir, "tui.log");
    let (non_blocking, _guard) = tracing_appender::non_blocking(file_appender);

    tracing_subscriber::registry()
        .with(fmt::layer().with_writer(non_blocking).with_ansi(false))
        .with(EnvFilter::try_from_default_env().unwrap_or_else(|_| EnvFilter::new("info")))
        .init();

    tracing::info!(
        "FlintCode TUI Legacy starting with profile override: {:?}",
        args.profile
    );

    let execution_config =
        pipeline::remote_config::RemoteExecutionConfig::load(&args.execution_config)?;
    let remote_target = execution_config.build(args.execution_target.as_deref())?;
    tracing::info!(
        target = %remote_target.name,
        resume_session = ?args.resume_session,
        "SSH execution target selected"
    );
    let mut app = app::App::new(args.profile.as_deref(), remote_target, args.resume_session)?;

    let run_result: Result<()> = async {
        if let Some(task_path) = args.task {
            if task_path.exists() {
                tracing::info!("Auto-starting task: {:?}", task_path);
                app.start_task(&task_path).await?;
            }
        }

        ui::run(&mut app).await
    }
    .await;
    let shutdown_result = app.shutdown().await;
    run_result?;
    shutdown_result?;

    tracing::info!("FlintCode TUI Legacy shutdown");
    Ok(())
}

#[cfg(test)]
mod tests {
    use super::CliArgs;
    use clap::Parser;

    #[test]
    fn execution_config_is_required() {
        let error = CliArgs::try_parse_from(["flintcode-tui-legacy"])
            .expect_err("TUI must reject execution without an SSH config");
        assert!(error.to_string().contains("--execution-config"));
    }

    #[test]
    fn parses_remote_target_and_resume_session() {
        let args = CliArgs::try_parse_from([
            "flintcode-tui-legacy",
            "--execution-config",
            "/tmp/remote.toml",
            "--execution-target",
            "ca-mini",
            "--resume-session",
            "session-123",
        ])
        .expect("remote flags");

        assert_eq!(args.execution_target.as_deref(), Some("ca-mini"));
        assert_eq!(args.resume_session.as_deref(), Some("session-123"));
    }
}
