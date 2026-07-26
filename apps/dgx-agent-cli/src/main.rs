//! DGX Agent CLI — Headless batch runner for evaluation.
//!
//! Usage:
//!   dgx-agent evaluate --plan <plan.toml> --profile <profile.toml> --output <dir>
//!   dgx-agent health --profile <profile.toml>

use anyhow::Result;
use clap::{Parser, Subcommand};
use std::path::PathBuf;
use tracing_subscriber::{EnvFilter, fmt, prelude::*};

#[derive(Parser)]
#[command(
    name = "dgx-agent",
    about = "DGX Spark Agent — Headless evaluation and batch runner",
    version
)]
struct Cli {
    #[command(subcommand)]
    command: Commands,
}

#[derive(Subcommand)]
enum Commands {
    /// Run an evaluation suite against a DGX Spark model
    Evaluate {
        /// Path to the evaluation plan (TOML)
        #[arg(long)]
        plan: PathBuf,

        /// Path to the model profile (TOML)
        #[arg(long, default_value = "profiles/dgx-spark-nemotron-3-super-64k.toml")]
        profile: PathBuf,

        /// Output directory for run artifacts
        #[arg(long, default_value = "runs")]
        output: PathBuf,

        /// Report formats (comma-separated: json,junit,markdown)
        #[arg(long, default_value = "json,markdown")]
        report: String,
    },

    /// Check if the model service is healthy
    Health {
        /// Path to the model profile (TOML)
        #[arg(long, default_value = "profiles/dgx-spark-nemotron-3-super-64k.toml")]
        profile: PathBuf,
    },

    /// Run a single task package
    Run {
        /// Path to the task package directory
        #[arg(long)]
        task: PathBuf,

        /// Path to the model profile (TOML)
        #[arg(long, default_value = "profiles/dgx-spark-nemotron-3-super-64k.toml")]
        profile: PathBuf,

        /// Output directory
        #[arg(long, default_value = "runs")]
        output: PathBuf,
    },
}

#[tokio::main]
async fn main() -> Result<()> {
    tracing_subscriber::registry()
        .with(fmt::layer().with_ansi(true))
        .with(EnvFilter::try_from_default_env().unwrap_or_else(|_| EnvFilter::new("info")))
        .init();

    let cli = Cli::parse();

    match cli.command {
        Commands::Evaluate { plan, profile, output, report } => {
            tracing::info!(
                plan = %plan.display(),
                profile = %profile.display(),
                output = %output.display(),
                "Starting evaluation suite"
            );

            let model_profile = model_vllm::profile::ModelProfile::load(&profile)?;
            tracing::info!(
                model = %model_profile.model.name,
                context = model_profile.context.model_context_tokens,
                "Profile loaded"
            );

            // TODO: Load evaluation plan, iterate test cases, run pipeline
            tracing::warn!("Evaluation suite execution not yet implemented");
            eprintln!("✗ Evaluation suite execution not yet implemented.");
            eprintln!("  Plan: {}", plan.display());
            eprintln!("  Profile: {} ({})", model_profile.model.name, profile.display());
            eprintln!("  Output: {}", output.display());
            std::process::exit(1);
        }

        Commands::Health { profile } => {
            let model_profile = model_vllm::profile::ModelProfile::load(&profile)?;
            let client = model_vllm::client::VllmClient::new(model_profile.clone());

            eprint!("Checking {} at {} ... ", model_profile.model.name, model_profile.resolve_endpoint());

            match client.health_check().await {
                Ok(true) => {
                    eprintln!("✓ healthy");
                    eprintln!("  Model: {}", model_profile.model.name);
                    eprintln!("  Context: {} tokens", model_profile.context.model_context_tokens);
                    eprintln!("  Prompt limit: {} tokens", model_profile.context.max_prompt_tokens);
                    eprintln!("  Completion limit: {} tokens", model_profile.context.max_completion_tokens);
                    eprintln!("  Safety reserve: {} tokens", model_profile.context.safety_tokens);
                    eprintln!("  Concurrency: {}", model_profile.concurrency.max_in_flight);
                    eprintln!("  Thinking: {}", if model_profile.thinking.enabled { "on" } else { "off" });
                }
                Ok(false) => {
                    eprintln!("✗ unhealthy");
                    std::process::exit(1);
                }
                Err(e) => {
                    eprintln!("✗ error: {e}");
                    std::process::exit(1);
                }
            }
        }

        Commands::Run { task, profile, output } => {
            tracing::info!(
                task = %task.display(),
                profile = %profile.display(),
                "Running single task"
            );

            let model_profile = model_vllm::profile::ModelProfile::load(&profile)?;
            tracing::info!(model = %model_profile.model.name, "Profile loaded");

            // TODO: Load task package, create run controller, execute pipeline
            tracing::warn!("Single task execution not yet implemented");
            eprintln!("✗ Single task execution not yet implemented.");
            std::process::exit(1);
        }
    }

    Ok(())
}
