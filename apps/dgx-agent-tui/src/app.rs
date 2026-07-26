//! TUI application state — thin wrapper around agent-core.

use anyhow::Result;
use agent_core::state::{PipelineState, RunState, StageTiming};
use agent_core::event::*;
use agent_core::reducer::SideEffect;
use agent_core::run_controller::RunController;
use model_vllm::profile::ModelProfile;
use tokio::sync::mpsc;
use std::path::PathBuf;

/// Active view in the TUI
#[derive(Debug, Clone, Copy, PartialEq)]
pub enum View {
    Pipeline,
    Task,
    Candidate,
    Diagnostics,
    Config,
    Help,
}

/// TUI application state
pub struct App {
    pub profile: ModelProfile,
    pub run: RunState,
    pub view: View,
    pub should_quit: bool,
    pub candidate: String,
    pub diagnostics: String,
    pub task_files: Vec<String>,
    pub token_prompt: u32,
    pub token_completion: u32,
    pub scroll_offset: u16,
    pub event_tx: Option<mpsc::Sender<RunEvent>>,
    pub side_effect_rx: Option<mpsc::Receiver<SideEffect>>,
}

impl App {
    pub fn new() -> Result<Self> {
        // Load the default profile
        let profile_path = PathBuf::from("profiles/dgx-spark-nemotron-3-super-64k.toml");
        let profile = if profile_path.exists() {
            ModelProfile::load(&profile_path)?
        } else {
            // Minimal default if profile file not found
            toml::from_str(include_str!("../../../profiles/dgx-spark-nemotron-3-super-64k.toml"))?
        };

        let max_repairs = profile.run.max_repairs;
        let run_id = format!("run-{}", chrono::Utc::now().format("%Y%m%d-%H%M%S"));
        let run = RunState::new(run_id, max_repairs);

        Ok(Self {
            profile,
            run,
            view: View::Pipeline,
            should_quit: false,
            candidate: String::new(),
            diagnostics: String::new(),
            task_files: Vec::new(),
            token_prompt: 0,
            token_completion: 0,
            scroll_offset: 0,
            event_tx: None,
            side_effect_rx: None,
        })
    }

    /// Get pipeline stage statuses for display
    pub fn stage_statuses(&self) -> Vec<(&str, StageStatus)> {
        let stages = [
            "Preflight", "Generate", "Local Gate", "TeaQL", "Build", "Test",
        ];
        let current = self.run.state.label();
        let mut result = Vec::new();
        let mut past_current = false;

        for stage in stages {
            if past_current {
                result.push((stage, StageStatus::Pending));
            } else if current.contains(stage) || is_stage_match(current, stage) {
                result.push((stage, StageStatus::Active));
                past_current = true;
            } else {
                // Check if we have timing for this stage
                let completed = self.run.timings.iter().any(|t| {
                    t.stage.contains(&stage.to_lowercase().replace(' ', "_"))
                        && t.completed.is_some()
                });
                if completed {
                    result.push((stage, StageStatus::Done));
                } else {
                    result.push((stage, StageStatus::Pending));
                }
            }
        }
        result
    }
}

#[derive(Debug, Clone, Copy, PartialEq)]
pub enum StageStatus {
    Done,
    Active,
    Pending,
    Failed,
}

fn is_stage_match(current: &str, stage: &str) -> bool {
    match stage {
        "Preflight" => current == "Preflight",
        "Generate" => current == "Generating" || current == "Repairing",
        "Local Gate" => current == "Local Validation",
        "TeaQL" => current == "Domain Validation",
        "Build" => current == "Build Validation",
        "Test" => current == "Build Validation",
        _ => false,
    }
}
