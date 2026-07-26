//! DGX Agent TUI — Ratatui observation and control interface.

mod app;
mod ui;
mod input;

use anyhow::Result;
use tracing_subscriber::{EnvFilter, fmt, prelude::*};
use tracing_appender;
use std::path::PathBuf;

#[tokio::main]
async fn main() -> Result<()> {
    // File-based logging (don't pollute the TUI)
    let log_dir = dirs::data_local_dir()
        .unwrap_or_else(|| PathBuf::from("."))
        .join("dgx-agent")
        .join("logs");
    std::fs::create_dir_all(&log_dir)?;
    let file_appender = tracing_appender::rolling::daily(&log_dir, "tui.log");
    let (non_blocking, _guard) = tracing_appender::non_blocking(file_appender);

    tracing_subscriber::registry()
        .with(fmt::layer().with_writer(non_blocking).with_ansi(false))
        .with(EnvFilter::try_from_default_env().unwrap_or_else(|_| EnvFilter::new("info")))
        .init();

    tracing::info!("DGX Agent TUI starting");

    let mut app = app::App::new()?;
    ui::run(&mut app).await?;

    tracing::info!("DGX Agent TUI shutdown");
    Ok(())
}
