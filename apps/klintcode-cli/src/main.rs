use anyhow::{Context, Result, bail};
use clap::{Parser, Subcommand, ValueEnum};
use std::collections::VecDeque;
use std::path::{Path, PathBuf};
use tracing_subscriber::{EnvFilter, FmtSubscriber};

#[derive(Parser)]
#[command(author, version, about = "Klint Code - Agentic Workflow V2")]
struct Cli {
    #[command(subcommand)]
    command: Commands,
}

#[derive(Subcommand)]
enum Commands {
    Health {
        #[arg(long, default_value = "profiles/default.toml")]
        profile: PathBuf,
        /// Emit the structured probe report as JSON.
        #[arg(long)]
        json: bool,
    },
    /// Exercise live OpenAI-compatible backend capabilities.
    Probe {
        #[arg(long, default_value = "profiles/default.toml")]
        profile: PathBuf,
        #[arg(
            long = "check",
            value_enum,
            value_delimiter = ',',
            default_values_t = [ProbeKind::Models, ProbeKind::Chat, ProbeKind::Stream, ProbeKind::Tools]
        )]
        checks: Vec<ProbeKind>,
        #[arg(long)]
        json: bool,
    },
    /// Run a benchmark suite against a real or simulated backend.
    Evaluate {
        #[arg(long)]
        plan: PathBuf,
        #[arg(long, default_value = "profiles/default.toml")]
        profile: PathBuf,
        #[arg(long, default_value = "runs")]
        output: PathBuf,
    },
    Run {
        /// Primary tasks to run as an ordered queue. Repeat --task to add entries.
        #[arg(long, required = true, num_args = 1)]
        task: Vec<PathBuf>,
        #[arg(long, default_value = "profiles/default.toml")]
        profile: PathBuf,
        #[arg(long, default_value = "runs")]
        output: PathBuf,
        /// Explicit modeling skill applied to every primary task.
        #[arg(long)]
        skill: Option<PathBuf>,
        #[arg(long)]
        build_target: Option<String>,
        /// Repeat the complete primary-task queue this many times.
        #[arg(long, default_value_t = 1, value_parser = clap::value_parser!(u32).range(1..))]
        repeat: u32,
        /// Continue on the generated workspace. Repeat to enqueue follow-ups.
        #[arg(long, num_args = 1)]
        follow_up: Vec<PathBuf>,
        /// Machine-verifiable acceptance contract for each follow-up, in queue order.
        #[arg(long, num_args = 1, requires = "follow_up")]
        follow_up_acceptance: Vec<PathBuf>,
        /// Permit ad-hoc follow-ups without machine acceptance (never for evaluations).
        #[arg(long)]
        allow_unverified_follow_up: bool,
        /// Stop the primary-task queue after its first failed run.
        #[arg(long)]
        fail_fast: bool,
    },
}

#[derive(Debug, Clone, Copy, PartialEq, Eq, ValueEnum)]
enum ProbeKind {
    Models,
    Chat,
    Stream,
    Tools,
}

#[derive(Debug, Clone, PartialEq, Eq)]
struct RunJob {
    task: PathBuf,
    cycle: u32,
}

#[derive(Debug)]
struct SessionOutcome {
    completed: bool,
    final_artifacts: Vec<PathBuf>,
    error: Option<String>,
}

#[tokio::main]
async fn main() -> Result<()> {
    let subscriber = FmtSubscriber::builder()
        .with_env_filter(EnvFilter::from_default_env().add_directive(tracing::Level::INFO.into()))
        .with_target(true)
        .with_file(false)
        .with_line_number(false)
        .finish();

    tracing::subscriber::set_global_default(subscriber).expect("Failed to set tracing subscriber");
    let cli = Cli::parse();

    match cli.command {
        Commands::Health { profile, json } => {
            let model_profile = model_vllm::profile::ModelProfile::load(&profile)?;
            let client = model_vllm::backend::ModelClient::from_profile(model_profile)?;
            let report = client
                .probe(model_vllm::backend::ProbeOptions::health())
                .await;
            print_probe_report(&report, json)?;
            if !report.passed {
                bail!("backend health check failed");
            }
        }
        Commands::Probe {
            profile,
            checks,
            json,
        } => {
            let model_profile = model_vllm::profile::ModelProfile::load(&profile)?;
            let client = model_vllm::backend::ModelClient::from_profile(model_profile)?;
            let mut options = model_vllm::backend::ProbeOptions::default();
            for check in checks {
                match check {
                    ProbeKind::Models => options.models = true,
                    ProbeKind::Chat => options.chat = true,
                    ProbeKind::Stream => options.stream = true,
                    ProbeKind::Tools => options.tools = true,
                }
            }
            let report = client.probe(options).await;
            print_probe_report(&report, json)?;
            if !report.passed {
                bail!("one or more backend conformance checks failed");
            }
        }
        Commands::Evaluate {
            plan,
            profile,
            output,
        } => {
            run_evaluation(&plan, &profile, &output).await?;
        }
        Commands::Run {
            task,
            profile,
            output,
            skill,
            build_target,
            repeat,
            follow_up,
            follow_up_acceptance,
            allow_unverified_follow_up,
            fail_fast,
        } => {
            run_queue(RunQueueOptions {
                tasks: task,
                profile,
                output,
                skill,
                build_target,
                repeat,
                follow_ups: follow_up,
                follow_up_acceptance,
                allow_unverified_follow_up,
                fail_fast,
            })
            .await?;
        }
    }
    Ok(())
}

struct RunQueueOptions {
    tasks: Vec<PathBuf>,
    profile: PathBuf,
    output: PathBuf,
    skill: Option<PathBuf>,
    build_target: Option<String>,
    repeat: u32,
    follow_ups: Vec<PathBuf>,
    follow_up_acceptance: Vec<PathBuf>,
    allow_unverified_follow_up: bool,
    fail_fast: bool,
}

fn print_probe_report(report: &model_vllm::backend::BackendProbeReport, json: bool) -> Result<()> {
    if json {
        println!("{}", serde_json::to_string_pretty(report)?);
        return Ok(());
    }

    eprintln!(
        "Backend: {} · Model: {} · Endpoint: {}",
        report.backend, report.model, report.endpoint
    );
    for check in &report.checks {
        eprintln!(
            "{} {:<7} {:>6}ms · {}",
            if check.passed { "✓" } else { "✗" },
            check.name,
            check.elapsed_ms,
            check.detail
        );
    }
    Ok(())
}

async fn run_evaluation(plan_path: &Path, profile_path: &Path, output: &Path) -> Result<()> {
    let plan = pipeline::suite::SuitePlan::load(plan_path)?;
    let profile = model_vllm::profile::ModelProfile::load(profile_path)?;
    let base_dir = plan_path.parent().unwrap_or_else(|| Path::new("."));
    tokio::fs::create_dir_all(output).await?;

    let result = pipeline::suite::run_suite(&plan, &profile, output, base_dir).await?;
    let safe_name = plan
        .suite
        .name
        .chars()
        .map(|character| {
            if character.is_ascii_alphanumeric() || matches!(character, '-' | '_') {
                character
            } else {
                '-'
            }
        })
        .collect::<String>();
    let json_path = output.join(format!("{safe_name}-result.json"));
    let markdown_path = output.join(format!("{safe_name}-report.md"));
    tokio::fs::write(&json_path, serde_json::to_vec_pretty(&result)?).await?;
    tokio::fs::write(
        &markdown_path,
        pipeline::suite::format_suite_markdown(&result),
    )
    .await?;
    eprintln!("JSON report: {}", json_path.display());
    eprintln!("Markdown report: {}", markdown_path.display());

    if result.failed > 0 {
        bail!("evaluation suite failed {} case(s)", result.failed);
    }
    Ok(())
}

async fn run_queue(options: RunQueueOptions) -> Result<()> {
    if let Some(skill) = &options.skill
        && !skill.is_file()
    {
        bail!(
            "Modeling skill does not exist or is not a file: {}",
            skill.display()
        );
    }

    let model_profile = model_vllm::profile::ModelProfile::load(&options.profile)?;
    let follow_ups = options
        .follow_ups
        .iter()
        .map(|path| read_instruction(path))
        .collect::<Result<Vec<_>>>()?;
    let follow_up_acceptance = options
        .follow_up_acceptance
        .iter()
        .map(|path| read_followup_acceptance(path))
        .collect::<Result<Vec<_>>>()?;
    let task_sidecars_available = follow_ups.len() == 1
        && options
            .tasks
            .iter()
            .all(|task| followup_sidecar_path(task).is_file());
    validate_followup_contract_count(
        follow_ups.len(),
        follow_up_acceptance.len(),
        options.allow_unverified_follow_up,
        task_sidecars_available,
    )?;
    let jobs = build_run_queue(&options.tasks, options.repeat);
    let total = jobs.len();
    let mut failures = Vec::new();

    for (index, job) in jobs.iter().enumerate() {
        eprintln!(
            "▶ Run {}/{} (cycle {}): {}",
            index + 1,
            total,
            job.cycle,
            job.task.display()
        );
        let outcome = run_session(
            index + 1,
            &job.task,
            &model_profile,
            &options.output,
            options.skill.as_deref(),
            options.build_target.as_deref(),
            &follow_ups,
            &follow_up_acceptance,
        )
        .await?;

        if outcome.completed {
            eprintln!(
                "✓ Run {}/{} completed ({} artifact snapshot(s))",
                index + 1,
                total,
                outcome.final_artifacts.len()
            );
        } else {
            let error = outcome
                .error
                .unwrap_or_else(|| "pipeline did not reach Completed".to_string());
            eprintln!("✗ Run {}/{} failed: {}", index + 1, total, error);
            failures.push(format!("{}: {}", job.task.display(), error));
            if options.fail_fast {
                break;
            }
        }
    }

    if failures.is_empty() {
        eprintln!("✓ Queue complete: {total} run(s) passed");
        Ok(())
    } else {
        bail!(
            "{} queued run(s) failed:\n{}",
            failures.len(),
            failures.join("\n")
        )
    }
}

#[allow(clippy::too_many_arguments)]
async fn run_session(
    sequence: usize,
    task: &Path,
    model_profile: &model_vllm::profile::ModelProfile,
    output: &Path,
    skill: Option<&Path>,
    build_target: Option<&str>,
    follow_ups: &[String],
    follow_up_acceptance: &[pipeline::followup_acceptance::FollowUpAcceptanceSpec],
) -> Result<SessionOutcome> {
    tracing::info!(task = %task.display(), "Pipeline starting");
    let run_id = format!(
        "run-{}-{sequence:03}",
        chrono::Utc::now().format("%Y%m%d-%H%M%S-%3f")
    );

    let (discard_effect_tx, discard_effect_rx) = tokio::sync::mpsc::channel(32);
    drop(discard_effect_rx);
    let (mut controller, controller_event_tx) = agent_core::run_controller::RunController::new(
        run_id.clone(),
        model_profile.run.max_repairs,
        discard_effect_tx,
    );

    let (proxy_event_tx, mut proxy_event_rx) = tokio::sync::mpsc::channel(64);
    let mut executor = pipeline::executor::PipelineExecutor::new(
        model_profile.clone(),
        proxy_event_tx,
        output.to_path_buf(),
        run_id,
    )?;
    if let Some(target) = build_target {
        executor.set_build_target(target.to_string());
    }
    if let Some(skill_path) = skill {
        executor.set_modeling_skill_path(skill_path.to_path_buf());
    }
    executor.set_followup_acceptance_specs(follow_up_acceptance.to_vec());

    load_primary_task(&mut executor, task).await;

    let (effect_tx, mut effect_rx) = tokio::sync::mpsc::channel(32);
    let worker = tokio::spawn(async move {
        while let Some(effect) = effect_rx.recv().await {
            executor.handle(effect).await;
        }
    });

    let mut pending_follow_ups = VecDeque::from(follow_ups.to_vec());
    let mut final_artifacts = Vec::new();
    let mut finish_when_terminal = false;

    loop {
        tokio::select! {
            event = proxy_event_rx.recv() => {
                let Some(event) = event else {
                    break;
                };
                print_event(&event);

                let final_artifact = match &event {
                    agent_core::event::RunEvent::FinalArtifactWritten(path) => Some(path.clone()),
                    _ => None,
                };
                let terminal_failure = matches!(
                    &event,
                    agent_core::event::RunEvent::PreflightFailed(_)
                        | agent_core::event::RunEvent::TaskLoadFailed(_)
                        | agent_core::event::RunEvent::Failed(_)
                );

                controller_event_tx
                    .send(event)
                    .await
                    .context("Run controller event channel closed")?;

                if let Some(path) = final_artifact {
                    final_artifacts.push(path);
                    if let Some(instruction) = pending_follow_ups.pop_front() {
                        eprintln!("↪ Continuing on the same workspace: {}", one_line(&instruction));
                        controller_event_tx
                            .send(agent_core::event::RunEvent::ContinueTask(instruction))
                            .await
                            .context("Failed to enqueue follow-up task")?;
                    } else {
                        finish_when_terminal = true;
                    }
                }
                if terminal_failure {
                    finish_when_terminal = true;
                }
            }
            effect = controller.process_next() => {
                match effect {
                    Some(agent_core::reducer::SideEffect::RequestConsent { action, .. }) => {
                        eprintln!("Consent auto-granted for CLI mode: {action}");
                        controller_event_tx
                            .send(agent_core::event::RunEvent::ConsentGranted(
                                agent_core::event::ExportConsent {
                                    domain: "local".to_string(),
                                    files: vec![],
                                    total_bytes: 0,
                                    purpose: "CLI auto-grant".to_string(),
                                    approved: true,
                                    approved_by: Some("cli".to_string()),
                                },
                            ))
                            .await
                            .context("Failed to grant CLI consent")?;
                    }
                    Some(agent_core::reducer::SideEffect::None) => {}
                    Some(effect) => {
                        effect_tx
                            .send(effect)
                            .await
                            .context("Pipeline executor channel closed")?;
                    }
                    None => break,
                }
            }
        }

        if matches!(
            controller.pipeline_state(),
            agent_core::state::PipelineState::Failed { .. }
                | agent_core::state::PipelineState::Cancelled
                | agent_core::state::PipelineState::SkippedByPolicy { .. }
        ) {
            finish_when_terminal = true;
        }
        if finish_when_terminal && controller.pipeline_state().is_terminal() {
            break;
        }
    }

    drop(effect_tx);
    worker.await.context("Pipeline executor worker failed")?;

    let completed = matches!(
        controller.pipeline_state(),
        agent_core::state::PipelineState::Completed
    );
    let error = match controller.pipeline_state() {
        agent_core::state::PipelineState::Failed { error } => Some(error.clone()),
        agent_core::state::PipelineState::Cancelled => Some("cancelled".to_string()),
        agent_core::state::PipelineState::SkippedByPolicy { reason } => Some(reason.clone()),
        state if !completed => Some(format!("ended in {} state", state.label())),
        _ => None,
    };

    Ok(SessionOutcome {
        completed,
        final_artifacts,
        error,
    })
}

async fn load_primary_task(executor: &mut pipeline::executor::PipelineExecutor, task: &Path) {
    if task.is_dir() {
        executor.load_task_from_path(task).await;
    } else if task.is_file() {
        let text =
            std::fs::read_to_string(task).unwrap_or_else(|_| task.to_string_lossy().into_owned());
        executor.load_task_from_text(&text).await;
    } else {
        executor.load_task_from_text(&task.to_string_lossy()).await;
    }
}

fn build_run_queue(tasks: &[PathBuf], repeat: u32) -> Vec<RunJob> {
    (1..=repeat)
        .flat_map(|cycle| {
            tasks
                .iter()
                .cloned()
                .map(move |task| RunJob { task, cycle })
        })
        .collect()
}

fn read_instruction(path_or_text: &Path) -> Result<String> {
    if path_or_text.is_file() {
        std::fs::read_to_string(path_or_text)
            .with_context(|| format!("Failed to read follow-up: {}", path_or_text.display()))
    } else {
        Ok(path_or_text.to_string_lossy().into_owned())
    }
}

fn read_followup_acceptance(
    path: &Path,
) -> Result<pipeline::followup_acceptance::FollowUpAcceptanceSpec> {
    let content = std::fs::read_to_string(path)
        .with_context(|| format!("Failed to read follow-up acceptance: {}", path.display()))?;
    let spec: pipeline::followup_acceptance::FollowUpAcceptanceSpec =
        serde_json::from_str(&content)
            .with_context(|| format!("Invalid follow-up acceptance JSON: {}", path.display()))?;
    spec.validate().map_err(anyhow::Error::msg)?;
    Ok(spec)
}

fn validate_followup_contract_count(
    follow_ups: usize,
    contracts: usize,
    allow_unverified: bool,
    task_sidecars_available: bool,
) -> Result<()> {
    if contracts == follow_ups
        || (contracts == 0 && allow_unverified)
        || (contracts == 0 && follow_ups == 1 && task_sidecars_available)
    {
        return Ok(());
    }
    bail!(
        "provide one --follow-up-acceptance per --follow-up ({follow_ups} follow-up(s), {contracts} contract(s)); use --allow-unverified-follow-up only for ad-hoc, non-evaluation work"
    )
}

fn followup_sidecar_path(task: &Path) -> PathBuf {
    if task.is_dir() {
        task.join("followup-acceptance.json")
    } else {
        task.parent()
            .unwrap_or_else(|| Path::new("."))
            .join("followup-acceptance.json")
    }
}

fn one_line(text: &str) -> String {
    const LIMIT: usize = 120;
    let compact = text.split_whitespace().collect::<Vec<_>>().join(" ");
    let mut chars = compact.chars();
    let prefix = chars.by_ref().take(LIMIT).collect::<String>();
    if chars.next().is_some() {
        format!("{prefix}…")
    } else {
        prefix
    }
}

fn print_event(event: &agent_core::event::RunEvent) {
    match event {
        agent_core::event::RunEvent::TaskLoaded(task) => {
            eprintln!("✓ Task loaded: {}", task.name);
        }
        agent_core::event::RunEvent::PreflightPassed(budget) => {
            eprintln!(
                "✓ Preflight passed (estimated {}, limit {})",
                budget.estimated_prompt, budget.prompt_limit
            );
        }
        agent_core::event::RunEvent::PreflightFailed(reason) => {
            eprintln!("✗ Preflight failed: {reason}");
        }
        agent_core::event::RunEvent::TaskLoadFailed(reason) => {
            eprintln!("✗ Task load failed: {reason}");
        }
        agent_core::event::RunEvent::ModelCompleted(_) => {
            eprintln!("✓ Model generated response");
        }
        agent_core::event::RunEvent::ValidationCompleted(result) => {
            let status = if result.passed { "passed" } else { "failed" };
            eprintln!(
                "{} L{} {} {} ({} errors, {} warnings)",
                if result.passed { "✓" } else { "✗" },
                result.level,
                result.level_name,
                status,
                result.error_count,
                result.warning_count
            );
            if !result.passed {
                for error in result.actionable_errors.iter().take(5) {
                    eprintln!("  ▸ {error}");
                }
            }
        }
        agent_core::event::RunEvent::RepairScheduled { attempt } => {
            eprintln!("↻ Scheduling repair attempt {attempt}");
        }
        agent_core::event::RunEvent::FinalArtifactWritten(path) => {
            eprintln!("✓ Task complete. Final artifact: {}", path.display());
        }
        agent_core::event::RunEvent::Failed(error) => {
            eprintln!("✗ Run failed: {error}");
        }
        agent_core::event::RunEvent::ModelFailed(error) => {
            eprintln!("⚠ Model attempt failed: {error}");
        }
        agent_core::event::RunEvent::ConsentRequired { action } => {
            eprintln!("⚠ Awaiting consent: {action}");
        }
        agent_core::event::RunEvent::ToolProcessFinished { id, success, .. } => {
            eprintln!(
                "{} Tool execution: process {id}",
                if *success { "✓" } else { "✗" }
            );
        }
        _ => {}
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn run_command_accepts_a_queue_repeat_and_follow_ups() {
        let cli = Cli::try_parse_from([
            "klintcode",
            "run",
            "--task",
            "task-a",
            "--task",
            "task-b",
            "--repeat",
            "2",
            "--follow-up",
            "add-tests.md",
            "--follow-up",
            "fix warnings",
            "--follow-up-acceptance",
            "add-tests.acceptance.json",
            "--follow-up-acceptance",
            "fix-warnings.acceptance.json",
            "--output",
            "custom-runs",
            "--skill",
            "SKILL.md",
        ])
        .expect("CLI should parse");

        let Commands::Run {
            task,
            repeat,
            follow_up,
            follow_up_acceptance,
            output,
            skill,
            ..
        } = cli.command
        else {
            panic!("expected run command");
        };
        assert_eq!(task, vec![PathBuf::from("task-a"), PathBuf::from("task-b")]);
        assert_eq!(repeat, 2);
        assert_eq!(
            follow_up,
            vec![PathBuf::from("add-tests.md"), PathBuf::from("fix warnings")]
        );
        assert_eq!(
            follow_up_acceptance,
            vec![
                PathBuf::from("add-tests.acceptance.json"),
                PathBuf::from("fix-warnings.acceptance.json")
            ]
        );
        assert_eq!(output, PathBuf::from("custom-runs"));
        assert_eq!(skill, Some(PathBuf::from("SKILL.md")));
    }

    #[test]
    fn queue_repeats_all_tasks_in_order() {
        let tasks = vec![PathBuf::from("a"), PathBuf::from("b")];
        assert_eq!(
            build_run_queue(&tasks, 2),
            vec![
                RunJob {
                    task: PathBuf::from("a"),
                    cycle: 1
                },
                RunJob {
                    task: PathBuf::from("b"),
                    cycle: 1
                },
                RunJob {
                    task: PathBuf::from("a"),
                    cycle: 2
                },
                RunJob {
                    task: PathBuf::from("b"),
                    cycle: 2
                },
            ]
        );
    }

    #[test]
    fn one_line_bounds_multiline_follow_up_text() {
        let text = format!("first\n{}", "界".repeat(130));
        let compact = one_line(&text);
        assert!(!compact.contains('\n'));
        assert!(compact.ends_with('…'));
        assert_eq!(compact.chars().count(), 121);
    }

    #[test]
    fn cli_followups_require_aligned_contracts_unless_explicitly_unverified() {
        assert!(validate_followup_contract_count(2, 2, false, false).is_ok());
        assert!(validate_followup_contract_count(2, 0, false, false).is_err());
        assert!(validate_followup_contract_count(2, 0, true, false).is_ok());
        assert!(validate_followup_contract_count(2, 1, true, false).is_err());
        assert!(validate_followup_contract_count(1, 0, false, true).is_ok());
    }

    #[test]
    fn operational_backend_commands_parse() {
        let health = Cli::try_parse_from([
            "klintcode",
            "health",
            "--profile",
            "profiles/test.toml",
            "--json",
        ])
        .expect("health command");
        assert!(matches!(
            health.command,
            Commands::Health { json: true, .. }
        ));

        let probe = Cli::try_parse_from(["klintcode", "probe", "--check", "models,tools"])
            .expect("probe command");
        assert!(matches!(
            probe.command,
            Commands::Probe { checks, .. }
                if checks == vec![ProbeKind::Models, ProbeKind::Tools]
        ));

        let evaluate =
            Cli::try_parse_from(["klintcode", "evaluate", "--plan", "benchmarks/suite.toml"])
                .expect("evaluate command");
        assert!(matches!(evaluate.command, Commands::Evaluate { .. }));
    }
}
