use anyhow::Result;
use chrono::Utc;
use serde::{Deserialize, Serialize};
use std::path::Path;
use tokio::sync::mpsc;
use tracing::info;

use crate::executor::PipelineExecutor;
use agent_core::reducer::SideEffect;
use agent_core::run_controller::RunController;
use agent_core::state::PipelineState;
use model_vllm::profile::ModelProfile;

/// Evaluation suite plan loaded from TOML
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SuitePlan {
    pub suite: SuiteMetadata,
    pub cases: Vec<TestCase>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SuiteMetadata {
    pub name: String,
    pub description: String,
    #[serde(default = "default_version")]
    pub version: String,
}

fn default_version() -> String {
    "1.0".to_string()
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TestCase {
    pub name: String,
    pub task: String, // relative path to task package dir
    pub workflow: String,
    #[serde(default)]
    pub build_target: Option<String>, // e.g. "rust-lib-core"
    #[serde(default = "default_expect")]
    pub expect: String, // "completed", "preflight_rejected", "failed"
    #[serde(default = "default_timeout")]
    pub timeout_secs: u64,
    #[serde(default)]
    pub patches: Option<std::collections::HashMap<String, String>>,
}

fn default_expect() -> String {
    "completed".to_string()
}
fn default_timeout() -> u64 {
    600
}

/// Result of running a single test case
#[derive(Debug, Clone, Serialize)]
pub struct CaseResult {
    pub name: String,
    pub expected: String,
    pub actual: String,
    pub pass: bool,
    pub pass_at_1: bool,
    pub pass_after_repair: bool,
    pub attempts: u8,
    pub prompt_tokens: u64,
    pub completion_tokens: u64,
    pub elapsed_secs: f64,
    pub error: Option<String>,
}

/// Result of an entire suite run
#[derive(Debug, Clone, Serialize)]
pub struct SuiteResult {
    pub suite_name: String,
    pub profile: String,
    pub started_at: String,
    pub completed_at: String,
    pub total_cases: usize,
    pub passed: usize,
    pub failed: usize,
    pub pass_at_1_rate: f64,
    pub pass_after_repair_rate: f64,
    pub cases: Vec<CaseResult>,
}

impl SuitePlan {
    /// Load a suite plan from TOML
    pub fn load(path: &Path) -> Result<Self> {
        let content = std::fs::read_to_string(path)?;
        let plan: Self = toml::from_str(&content)?;
        Ok(plan)
    }
}

/// Run a complete evaluation suite
pub async fn run_suite(
    plan: &SuitePlan,
    profile: &ModelProfile,
    output_root: &Path,
    base_dir: &Path, // base directory for resolving task paths
) -> Result<SuiteResult> {
    let suite_start = Utc::now();
    let mut case_results = Vec::new();
    let mut pass_count = 0;
    let mut pass_at_1_count = 0;
    let mut pass_after_repair_count = 0;

    info!(suite = %plan.suite.name, cases = plan.cases.len(), "Starting evaluation suite");
    eprintln!(
        "\n═══ Suite: {} ({} cases) ═══\n",
        plan.suite.name,
        plan.cases.len()
    );

    for (i, case) in plan.cases.iter().enumerate() {
        eprintln!("[{}/{}] {} ...", i + 1, plan.cases.len(), case.name);
        let case_start = std::time::Instant::now();

        let task_path = base_dir.join(&case.task);
        let run_id = format!(
            "{}-{}-{}",
            plan.suite.name,
            case.name,
            Utc::now().format("%Y%m%d-%H%M%S-%3f")
        );

        // The suite drives effects directly so the whole case can be cancelled
        // by one outer timeout without leaving detached controller tasks.
        let (side_effect_tx, side_effect_rx) = mpsc::channel::<SideEffect>(1);
        drop(side_effect_rx);
        let (mut controller, event_tx) =
            RunController::new(run_id.clone(), profile.run.max_repairs, side_effect_tx);

        // Create executor
        let mut executor = PipelineExecutor::new(
            profile.clone(),
            event_tx.clone(),
            output_root.to_path_buf(),
            run_id.clone(),
        )?;

        // Load the task
        executor.load_task_from_path(&task_path).await;

        if let Some(ref target) = case.build_target {
            executor.set_build_target(target.clone());
        }

        if let Some(ref patches) = case.patches {
            executor.set_patches(patches.clone());
        }

        let case_execution = async move {
            while !controller.state.state.is_terminal() {
                match controller.process_next().await {
                    Some(SideEffect::None) => {}
                    Some(effect) => executor.handle(effect).await,
                    None => break,
                }
            }
            controller
        };
        let controller = tokio::time::timeout(
            std::time::Duration::from_secs(case.timeout_secs),
            case_execution,
        )
        .await
        .ok();

        let elapsed = case_start.elapsed().as_secs_f64();

        // Determine result
        let actual_state = match controller
            .as_ref()
            .map(|controller| &controller.state.state)
        {
            Some(PipelineState::Completed) => "completed",
            Some(PipelineState::Failed { error }) => {
                if error.contains("Budget exceeded") || error.contains("budget") {
                    "preflight_rejected"
                } else {
                    "failed"
                }
            }
            Some(PipelineState::Cancelled) => "cancelled",
            Some(PipelineState::SkippedByPolicy { .. }) => "skipped",
            Some(_) => "unknown",
            None => "timeout",
        };

        let pass = actual_state == case.expect;
        let attempts = controller
            .as_ref()
            .map_or(0, |controller| controller.state.current_attempt);
        let pass_at_1 = actual_state == "completed" && attempts <= 1;
        let pass_after_repair = actual_state == "completed" && attempts > 1;

        if pass {
            pass_count += 1;
        }
        if pass_at_1 {
            pass_at_1_count += 1;
        }
        if pass_after_repair {
            pass_after_repair_count += 1;
        }

        let error = match controller
            .as_ref()
            .map(|controller| &controller.state.state)
        {
            Some(PipelineState::Failed { error }) => Some(error.clone()),
            None => Some(format!(
                "case timed out after {} seconds",
                case.timeout_secs
            )),
            _ => None,
        };
        let token_totals = controller
            .as_ref()
            .map(|controller| controller.state.token_totals.clone())
            .unwrap_or_default();

        let status_icon = if pass { "✓" } else { "✗" };
        eprintln!(
            "  {} {} (expected: {}, got: {}, {:.1}s)",
            status_icon, case.name, case.expect, actual_state, elapsed
        );

        case_results.push(CaseResult {
            name: case.name.clone(),
            expected: case.expect.clone(),
            actual: actual_state.to_string(),
            pass,
            pass_at_1,
            pass_after_repair,
            attempts,
            prompt_tokens: token_totals.input_tokens,
            completion_tokens: token_totals.output_tokens,
            elapsed_secs: elapsed,
            error,
        });
    }

    let suite_end = Utc::now();
    let total = plan.cases.len();
    let completed_count = case_results
        .iter()
        .filter(|c| c.actual == "completed")
        .count();

    let result = SuiteResult {
        suite_name: plan.suite.name.clone(),
        profile: profile.model.name.clone(),
        started_at: suite_start.to_rfc3339(),
        completed_at: suite_end.to_rfc3339(),
        total_cases: total,
        passed: pass_count,
        failed: total - pass_count,
        pass_at_1_rate: if completed_count > 0 {
            pass_at_1_count as f64 / completed_count as f64
        } else {
            0.0
        },
        pass_after_repair_rate: if completed_count > 0 {
            pass_after_repair_count as f64 / completed_count as f64
        } else {
            0.0
        },
        cases: case_results,
    };

    // Print summary
    eprintln!("\n═══ Results ═══");
    eprintln!("Passed: {}/{}", pass_count, total);
    eprintln!("Pass@1: {:.0}%", result.pass_at_1_rate * 100.0);
    eprintln!(
        "Pass after repair: {:.0}%",
        result.pass_after_repair_rate * 100.0
    );

    Ok(result)
}

/// Generate a markdown report from suite results
pub fn format_suite_markdown(result: &SuiteResult) -> String {
    let mut md = String::new();
    md.push_str(&format!("# Evaluation Report: {}\n\n", result.suite_name));
    md.push_str(&format!("**Model:** {}\n\n", result.profile));
    md.push_str(&format!("**Started:** {}\n\n", result.started_at));
    md.push_str(&format!("**Completed:** {}\n\n", result.completed_at));

    md.push_str("## Summary\n\n");
    md.push_str(&format!("| Metric | Value |\n|--------|-------|\n"));
    md.push_str(&format!("| Total cases | {} |\n", result.total_cases));
    md.push_str(&format!("| Passed | {} |\n", result.passed));
    md.push_str(&format!("| Failed | {} |\n", result.failed));
    md.push_str(&format!(
        "| Pass@1 | {:.0}% |\n",
        result.pass_at_1_rate * 100.0
    ));
    md.push_str(&format!(
        "| Pass after repair | {:.0}% |\n\n",
        result.pass_after_repair_rate * 100.0
    ));

    md.push_str("## Cases\n\n");
    md.push_str(
        "| # | Name | Expected | Actual | Pass | Attempts | Prompt tokens | Completion tokens | Time |\n",
    );
    md.push_str(
        "|---|------|----------|--------|------|----------|---------------|-------------------|------|\n",
    );
    for (i, case) in result.cases.iter().enumerate() {
        let pass_icon = if case.pass { "✓" } else { "✗" };
        md.push_str(&format!(
            "| {} | {} | {} | {} | {} | {} | {} | {} | {:.1}s |\n",
            i + 1,
            case.name,
            case.expected,
            case.actual,
            pass_icon,
            case.attempts,
            case.prompt_tokens,
            case.completion_tokens,
            case.elapsed_secs
        ));
    }

    if result.cases.iter().any(|c| c.error.is_some()) {
        md.push_str("\n## Errors\n\n");
        for case in &result.cases {
            if let Some(error) = &case.error {
                md.push_str(&format!("### {}\n\n```\n{}\n```\n\n", case.name, error));
            }
        }
    }

    md
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::io::Write;

    #[tokio::test]
    async fn suite_enforces_case_timeout() {
        let mut scenario = tempfile::NamedTempFile::new().expect("scenario");
        scenario
            .write_all(
                br#"
name = "slow"
default_latency_ms = 2000
repeat_last = true

[[responses]]
id = "slow-response"
content = "<root/>"
"#,
            )
            .expect("write scenario");
        let mut profile: ModelProfile =
            toml::from_str(include_str!("../../../profiles/simulator.toml"))
                .expect("simulator profile");
        profile.simulator.scenario = Some(scenario.path().to_path_buf());
        let plan = SuitePlan {
            suite: SuiteMetadata {
                name: "timeout-test".to_string(),
                description: "timeout".to_string(),
                version: "1.0".to_string(),
            },
            cases: vec![TestCase {
                name: "slow-case".to_string(),
                task: "tasks/simple-greeting".to_string(),
                workflow: "ksml-modeling".to_string(),
                build_target: None,
                expect: "timeout".to_string(),
                timeout_secs: 1,
                patches: None,
            }],
        };
        let output = tempfile::tempdir().expect("output");
        let base_dir = Path::new(env!("CARGO_MANIFEST_DIR")).join("../../benchmarks");

        let result = run_suite(&plan, &profile, output.path(), &base_dir)
            .await
            .expect("suite result");

        assert_eq!(result.passed, 1);
        assert_eq!(result.cases[0].actual, "timeout");
        assert!(
            result.cases[0]
                .error
                .as_deref()
                .is_some_and(|error| error.contains("timed out"))
        );
    }
}
