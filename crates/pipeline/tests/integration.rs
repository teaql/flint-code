//! End-to-end integration tests using the in-process simulator.
//!
//! These tests exercise the full RunController → PipelineExecutor loop
//! without requiring an external model service.
//!
//! Run with: `cargo test -p pipeline --test integration`

use agent_core::event::RunEvent;
use agent_core::reducer::{SideEffect, reduce};
use agent_core::state::{PipelineState, RunState};
use model_vllm::profile::ModelProfile;
use pipeline::executor::PipelineExecutor;
use tokio::sync::mpsc;

/// Minimal event loop: feed events from executor → reducer → executor until
/// the pipeline reaches a terminal state. No RunController needed.
async fn run_to_terminal(
    executor: &mut PipelineExecutor,
    event_rx: &mut mpsc::Receiver<RunEvent>,
    state: &mut RunState,
) {
    // Kick off the run by loading a preflight
    let _initial = reduce(state, RunEvent::ModelStarted { attempt: 0 });
    // Actually, we need to start with RunPreflight. Trigger it:
    executor.handle(SideEffect::RunPreflight).await;

    while !state.state.is_terminal() {
        match event_rx.recv().await {
            Some(event) => {
                let effect = reduce(state, event);
                if !matches!(effect, SideEffect::None) {
                    executor.handle(effect).await;
                }
            }
            None => break,
        }
    }
}

#[tokio::test]
async fn happy_path_simulator_terminates_cleanly() {
    let workspace_root = std::path::PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../");
    let mut profile = ModelProfile::load(&workspace_root.join("profiles/simulator.toml"))
        .expect("load simulator profile");
    
    // Fix relative paths in the profile since cargo test runs in the crate dir
    if let Some(scenario) = &mut profile.simulator.scenario {
        *scenario = workspace_root.join(&scenario);
    }

    let max_repairs = profile.run.max_repairs;
    let run_id = format!("test-{}", chrono::Utc::now().format("%H%M%S%3f"));
    let output = tempfile::tempdir().expect("tempdir");

    let (event_tx, mut event_rx) = mpsc::channel::<RunEvent>(128);
    let mut executor = PipelineExecutor::new(
        profile,
        event_tx.clone(),
        output.path().to_path_buf(),
        run_id.clone(),
    )
    .expect("create executor");

    // Load the task directly
    executor.load_task_from_path(&workspace_root.join("benchmarks/tasks/simple-greeting")).await;

    let mut state = RunState::new(run_id, max_repairs);

    // Drain the TaskLoaded event
    let loaded_event = event_rx.recv().await.expect("TaskLoaded event");
    let effect = reduce(&mut state, loaded_event);
    assert!(matches!(effect, SideEffect::RunPreflight));

    // Run the pipeline
    executor.handle(effect).await;

    while !state.state.is_terminal() {
        match event_rx.recv().await {
            Some(event) => {
                let effect = reduce(&mut state, event);
                if !matches!(effect, SideEffect::None) {
                    executor.handle(effect).await;
                }
            }
            None => break,
        }
    }

    // The fixture doesn't pass TeaQL domain validation, so we expect a clean
    // failure after exhausting repairs (not a panic or hang).
    match &state.state {
        PipelineState::Completed => println!("✓ Pipeline completed"),
        PipelineState::Failed { error } => {
            println!("✓ Pipeline terminated cleanly: {error}");
            assert!(
                error.contains("errors") || error.contains("repair") || error.contains("TeaQL"),
                "Unexpected failure: {error}"
            );
        }
        other => panic!("Pipeline stuck in non-terminal state: {other}"),
    }
}

#[tokio::test]
async fn inline_prompt_reaches_generation_phase() {
    let workspace_root = std::path::PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../");
    let mut profile = ModelProfile::load(&workspace_root.join("profiles/simulator.toml"))
        .expect("load simulator profile");
    
    // Fix relative paths in the profile since cargo test runs in the crate dir
    if let Some(scenario) = &mut profile.simulator.scenario {
        *scenario = workspace_root.join(&scenario);
    }

    let max_repairs = profile.run.max_repairs;
    let run_id = format!("test-{}", chrono::Utc::now().format("%H%M%S%3f"));
    let output = tempfile::tempdir().expect("tempdir");

    let (event_tx, mut event_rx) = mpsc::channel::<RunEvent>(128);
    let mut executor = PipelineExecutor::new(
        profile,
        event_tx,
        output.path().to_path_buf(),
        run_id.clone(),
    )
    .expect("create executor");

    // Load an inline prompt (simulating TUI submit)
    executor.load_prompt("inline-test", "Generate a greeting model").await;

    let mut state = RunState::new(run_id, max_repairs);
    let loaded = event_rx.recv().await.expect("TaskLoaded");
    let effect = reduce(&mut state, loaded);
    assert!(matches!(effect, SideEffect::RunPreflight));

    executor.handle(effect).await;

    // Run until we've seen at least one ModelCompleted
    let mut saw_model_completed = false;
    while !state.state.is_terminal() {
        match event_rx.recv().await {
            Some(event) => {
                if matches!(event, RunEvent::ModelCompleted(_)) {
                    saw_model_completed = true;
                }
                let effect = reduce(&mut state, event);
                if !matches!(effect, SideEffect::None) {
                    executor.handle(effect).await;
                }
            }
            None => break,
        }
    }

    assert!(saw_model_completed, "Pipeline never reached ModelCompleted");
    println!("✓ Inline prompt reached generation: {:?}", state.state);
}

#[tokio::test]
async fn streaming_tokens_are_emitted_during_generation() {
    let workspace_root = std::path::PathBuf::from(env!("CARGO_MANIFEST_DIR")).join("../../");
    let mut profile = ModelProfile::load(&workspace_root.join("profiles/simulator.toml"))
        .expect("load simulator profile");
    
    // Fix relative paths in the profile since cargo test runs in the crate dir
    if let Some(scenario) = &mut profile.simulator.scenario {
        *scenario = workspace_root.join(&scenario);
    }

    let max_repairs = profile.run.max_repairs;
    let run_id = format!("test-{}", chrono::Utc::now().format("%H%M%S%3f"));
    let output = tempfile::tempdir().expect("tempdir");

    let (event_tx, mut event_rx) = mpsc::channel::<RunEvent>(512);
    let mut executor = PipelineExecutor::new(
        profile,
        event_tx,
        output.path().to_path_buf(),
        run_id.clone(),
    )
    .expect("create executor");

    executor.load_task_from_path(&workspace_root.join("benchmarks/tasks/simple-greeting")).await;

    let mut state = RunState::new(run_id, max_repairs);
    let loaded = event_rx.recv().await.expect("TaskLoaded");
    let effect = reduce(&mut state, loaded);
    executor.handle(effect).await;

    let mut token_count = 0usize;
    while !state.state.is_terminal() {
        match event_rx.recv().await {
            Some(event) => {
                if matches!(event, RunEvent::ModelToken(_)) {
                    token_count += 1;
                }
                let effect = reduce(&mut state, event);
                if !matches!(effect, SideEffect::None) {
                    executor.handle(effect).await;
                }
            }
            None => break,
        }
    }

    // The simulator fixture is ~822 bytes with 24-char chunks = ~35 tokens
    assert!(
        token_count > 0,
        "Expected streaming tokens, got 0"
    );
    println!("✓ Received {token_count} streaming tokens");
}
