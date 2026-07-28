//! FlintCode TUI — Ratatui observation and control interface.

mod app;
mod ui;

use anyhow::Result;
use std::path::PathBuf;
use tracing_appender;
use tracing_subscriber::{EnvFilter, fmt, prelude::*};

#[tokio::main]
async fn main() -> Result<()> {
    // File-based logging (don't pollute the TUI)
    let log_dir = dirs::data_local_dir()
        .unwrap_or_else(|| PathBuf::from("."))
        .join("flintcode")
        .join("logs");
    std::fs::create_dir_all(&log_dir)?;
    let file_appender = tracing_appender::rolling::daily(&log_dir, "tui.log");
    let (non_blocking, _guard) = tracing_appender::non_blocking(file_appender);

    tracing_subscriber::registry()
        .with(fmt::layer().with_writer(non_blocking).with_ansi(false))
        .with(EnvFilter::try_from_default_env().unwrap_or_else(|_| EnvFilter::new("info")))
        .init();

    tracing::info!("FlintCode TUI starting");

    let mut app = app::App::new()?;
    if let Ok(task) = std::env::var("FLINTCODE_TASK") {
        app.start_task(std::path::Path::new(&task)).await?;
    }
    ui::run(&mut app).await?;

    tracing::info!("FlintCode TUI shutdown");
    Ok(())
}
