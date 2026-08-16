use crate::event::RunEvent;
use crate::reducer::{SideEffect, reduce};
use crate::state::RunState;
use tokio::sync::mpsc;
use tracing::info;

/// The run controller owns the RunState and processes events.
/// Both TUI and headless CLI use this same controller.
pub struct RunController {
    pub state: RunState,
    event_rx: mpsc::Receiver<RunEvent>,
    event_tx: mpsc::Sender<RunEvent>,
    side_effect_tx: mpsc::Sender<SideEffect>,
}

impl RunController {
    /// Create a new run controller.
    /// Returns the controller and a sender for submitting events.
    pub fn new(
        run_id: String,
        max_repairs: u8,
        side_effect_tx: mpsc::Sender<SideEffect>,
    ) -> (Self, mpsc::Sender<RunEvent>) {
        let (event_tx, event_rx) = mpsc::channel(64);
        let controller = Self {
            state: RunState::new(run_id, max_repairs),
            event_rx,
            event_tx: event_tx.clone(),
            side_effect_tx,
        };
        (controller, event_tx)
    }

    /// Get a clone of the event sender for external use
    pub fn event_sender(&self) -> mpsc::Sender<RunEvent> {
        self.event_tx.clone()
    }

    /// Process the next event from the channel.
    /// Returns the side effect produced, or None if the channel closed.
    pub async fn process_next(&mut self) -> Option<SideEffect> {
        let event = self.event_rx.recv().await?;
        let effect = self.reduce_event(event);

        // Forward the side effect
        if !matches!(effect, SideEffect::None) {
            self.side_effect_tx.send(effect.clone()).await.ok();
        }

        Some(effect)
    }

    /// Reduce an already-delivered event synchronously.
    ///
    /// Interactive event loops should use this when they are themselves the
    /// sole consumer of executor events. It avoids sending into a bounded
    /// channel that the same task would then have to drain. Unlike
    /// [`Self::process_next`], this method does not forward the returned effect;
    /// the caller owns effect dispatch.
    pub fn reduce_event(&mut self, event: RunEvent) -> SideEffect {
        reduce(&mut self.state, event)
    }

    /// Cancel the active run immediately and discard events queued by the
    /// effect that is being cancelled.
    ///
    /// Interactive clients use this after their executor worker has
    /// acknowledged cancellation. Processing cancellation out of band keeps
    /// already-buffered executor events from advancing the state machine
    /// before `CancelRequested` reaches the front of the normal event queue.
    pub fn cancel_current(&mut self) -> bool {
        if !self.state.state.is_active() {
            return false;
        }

        while self.event_rx.try_recv().is_ok() {}
        let effect = reduce(&mut self.state, RunEvent::CancelRequested);
        debug_assert!(matches!(effect, SideEffect::None));
        true
    }

    /// Run the event loop until a terminal state is reached.
    pub async fn run_to_completion(&mut self) {
        while !self.state.state.is_terminal() {
            match self.process_next().await {
                Some(_) => {}
                None => {
                    info!("Event channel closed");
                    break;
                }
            }
        }

        // Broadcast terminal failures to observers (e.g. TUI)
        match &self.state.state {
            crate::state::PipelineState::Failed { error } => {
                let _ = self
                    .event_tx
                    .send(crate::event::RunEvent::Failed(
                        crate::error::AgentError::InfrastructureError {
                            detail: error.clone(),
                        },
                    ))
                    .await;
            }
            crate::state::PipelineState::Cancelled => {
                let _ = self
                    .event_tx
                    .send(crate::event::RunEvent::Failed(
                        crate::error::AgentError::InfrastructureError {
                            detail: "Cancelled by user".to_string(),
                        },
                    ))
                    .await;
            }
            crate::state::PipelineState::SkippedByPolicy { reason } => {
                let _ = self
                    .event_tx
                    .send(crate::event::RunEvent::Failed(
                        crate::error::AgentError::InfrastructureError {
                            detail: format!("Skipped by policy: {}", reason),
                        },
                    ))
                    .await;
            }
            _ => {}
        }

        info!(state = %self.state.state, "Run completed");
    }

    /// Get current pipeline state
    pub fn pipeline_state(&self) -> &crate::state::PipelineState {
        &self.state.state
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::event::RunEvent;
    use crate::state::PipelineState;

    #[tokio::test]
    async fn test_terminal_failure_broadcasts_event() {
        let (side_effect_tx, _side_effect_rx) = tokio::sync::mpsc::channel(32);
        let (mut controller, _tx) = RunController::new("test-run".to_string(), 3, side_effect_tx);

        // Force the state to a terminal failure
        controller.state.state = PipelineState::Failed {
            error: "Max repairs reached".to_string(),
        };

        // Run the controller; it should see the terminal state and exit, broadcasting the failure
        controller.run_to_completion().await;

        // Since event_tx is bounded but has capacity, we should be able to receive the emitted event
        let mut rx = controller.event_rx; // consume the receiver
        let ev = rx.recv().await.unwrap();

        match ev {
            RunEvent::Failed(err) => {
                assert!(err.to_string().contains("Max repairs reached"));
            }
            _ => panic!("Expected RunEvent::Failed"),
        }
    }

    #[tokio::test]
    async fn immediate_cancel_discards_buffered_executor_events() {
        let (side_effect_tx, _side_effect_rx) = tokio::sync::mpsc::channel(32);
        let (mut controller, tx) = RunController::new("test-run".to_string(), 3, side_effect_tx);
        controller.state.state = PipelineState::Generating { attempt: 1 };

        tx.send(RunEvent::ModelFailed(
            crate::error::AgentError::InfrastructureError {
                detail: "late worker failure".to_string(),
            },
        ))
        .await
        .unwrap();

        assert!(controller.cancel_current());
        assert_eq!(controller.state.state, PipelineState::Cancelled);

        let no_event = tokio::time::timeout(
            std::time::Duration::from_millis(10),
            controller.process_next(),
        )
        .await;
        assert!(no_event.is_err(), "queued executor event must be drained");
    }
}
