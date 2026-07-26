//! TeaQL DGX Spark Agent — A TUI coding agent optimized for NVIDIA DGX Spark
//! with 64K context budget management.

mod app;
mod config;
mod context;
mod llm;
mod tui;
mod workspace;
mod agent;
mod teaql;

use anyhow::Result;
use tracing_subscriber::{EnvFilter, fmt, prelude::*};
use tracing_appender;
use std::path::PathBuf;

#[tokio::main]
async fn main() -> Result<()> {
    // Set up file-based logging (don't pollute the TUI)
    let log_dir = dirs::data_local_dir()
        .unwrap_or_else(|| PathBuf::from("."))
        .join("teaql-dgx-spark-agent")
        .join("logs");
    std::fs::create_dir_all(&log_dir)?;
    let file_appender = tracing_appender::rolling::daily(&log_dir, "agent.log");
    let (non_blocking, _guard) = tracing_appender::non_blocking(file_appender);

    tracing_subscriber::registry()
        .with(
            fmt::layer()
                .with_writer(non_blocking)
                .with_ansi(false)
        )
        .with(EnvFilter::try_from_default_env().unwrap_or_else(|_| EnvFilter::new("info")))
        .init();

    tracing::info!("TeaQL DGX Spark Agent starting");

    // Load or create config
    let config = config::AgentConfig::load_or_create()?;
    tracing::info!(context_budget = config.context_budget, "Config loaded");

    // Initialize and run the TUI application
    let mut app = app::App::new(config)?;
    tui::run(&mut app).await?;

    tracing::info!("TeaQL DGX Spark Agent shutdown complete");
    Ok(())
}
