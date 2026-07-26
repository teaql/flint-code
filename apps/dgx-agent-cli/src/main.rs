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

            // Load evaluation plan, iterate test cases, run pipeline
            let suite_plan = pipeline::suite::SuitePlan::load(&plan)?;
            let base_dir = plan.parent().unwrap_or(std::path::Path::new("."));
            
            let result = pipeline::suite::run_suite(
                &suite_plan,
                &model_profile,
                &output,
                base_dir,
            ).await?;

            std::fs::create_dir_all(&output)?;
            let run_id = format!("{}-{}", result.suite_name, chrono::Utc::now().format("%Y%m%d-%H%M%S"));
            
            let report_types: Vec<&str> = report.split(',').collect();
            if report_types.contains(&"json") {
                let json_path = output.join(format!("{}.json", run_id));
                std::fs::write(&json_path, serde_json::to_string_pretty(&result)?)?;
            }
            if report_types.contains(&"markdown") {
                let md_path = output.join(format!("{}.md", run_id));
                std::fs::write(&md_path, pipeline::suite::format_suite_markdown(&result))?;
            }
            
            if result.failed > 0 {
                std::process::exit(1);
            }
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

            let max_repairs = model_profile.run.max_repairs;
            let run_id = format!("run-{}", chrono::Utc::now().format("%Y%m%d-%H%M%S"));

            let (side_effect_tx, mut side_effect_rx) = tokio::sync::mpsc::channel(32);
            let (mut controller, event_tx) = agent_core::run_controller::RunController::new(run_id.clone(), max_repairs, side_effect_tx);

            let mut executor = pipeline::executor::PipelineExecutor::new(model_profile, event_tx, output.clone(), run_id);
            executor.load_task_from_path(&task).await;

            let controller_task = tokio::spawn(async move {
                controller.run_to_completion().await;
                controller
            });

            while let Some(effect) = side_effect_rx.recv().await {
                executor.handle(effect).await;
            }

            let controller = controller_task.await;
            
            if let agent_core::state::PipelineState::Completed = controller.unwrap().state.state {
                eprintln!("✓ Task completed successfully.");
                std::process::exit(0);
            } else {
                eprintln!("✗ Task failed.");
                std::process::exit(1);
            }
        }
    }

    Ok(())
}
