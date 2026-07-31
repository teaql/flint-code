//! External process execution with pipeline event reporting.
//!
//! [`ProcessRunner`] abstracts over external process execution so that
//! pipeline tests can supply a scripted implementation without spawning
//! real subprocesses.

use agent_core::event::RunEvent;
use std::path::Path;
use std::process::Output;
use tokio::sync::mpsc;
use tracing::error;

/// Renders a command and its arguments into a human-readable shell string.
pub fn render_command(program: &str, args: &[&str]) -> String {
    std::iter::once(program)
        .chain(args.iter().copied())
        .map(shell_quote)
        .collect::<Vec<_>>()
        .join(" ")
}

fn shell_quote(value: &str) -> String {
    if !value.is_empty()
        && value
            .chars()
            .all(|character| character.is_ascii_alphanumeric() || "_-./:" .contains(character))
    {
        value.to_string()
    } else {
        format!("'{}'", value.replace('\'', "'\"'\"'"))
    }
}

/// Default process runner that spawns real subprocesses and reports
/// [`RunEvent::ToolProcessStarted`] / [`RunEvent::ToolProcessFinished`]
/// events through the pipeline channel.
pub struct ProcessRunner {
    event_tx: mpsc::Sender<RunEvent>,
    next_id: u64,
}

impl ProcessRunner {
    pub fn new(event_tx: mpsc::Sender<RunEvent>) -> Self {
        Self {
            event_tx,
            next_id: 1,
        }
    }

    /// Execute an external command, reporting lifecycle events through the
    /// pipeline channel.
    ///
    /// Rejects commands containing shell operators (`|`, `;`, `&`, `` ` ``,
    /// `$(`) to prevent injection.
    pub async fn run(
        &mut self,
        program: &str,
        args: &[&str],
        cwd: Option<&Path>,
    ) -> std::io::Result<Output> {
        let id = self.next_id;
        self.next_id += 1;
        let command = render_command(program, args);

        // Guard: reject shell operators (from tool-runner policy)
        if command.contains('|')
            || command.contains(';')
            || command.contains('&')
            || command.contains('`')
            || command.contains("$(")
        {
            self.send_event(RunEvent::ToolProcessStarted {
                id,
                command: command.clone(),
            })
            .await;
            self.send_event(RunEvent::ToolProcessFinished {
                id,
                success: false,
                exit_code: None,
            })
            .await;
            return Err(std::io::Error::new(
                std::io::ErrorKind::PermissionDenied,
                format!("Shell operators not allowed in command: {command}"),
            ));
        }

        self.send_event(RunEvent::ToolProcessStarted { id, command })
            .await;

        let mut process = tokio::process::Command::new(program);
        process.args(args);
        if let Some(directory) = cwd {
            process.current_dir(directory);
        }
        let result = process.output().await;
        let (success, exit_code) = match &result {
            Ok(output) => (output.status.success(), output.status.code()),
            Err(_) => (false, None),
        };
        self.send_event(RunEvent::ToolProcessFinished {
            id,
            success,
            exit_code,
        })
        .await;
        result
    }

    /// Access the underlying event sender (used by executor for direct events).
    pub fn event_tx(&self) -> &mpsc::Sender<RunEvent> {
        &self.event_tx
    }

    async fn send_event(&self, event: RunEvent) {
        if self.event_tx.send(event).await.is_err() {
            error!("Failed to send event — channel closed");
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn rendered_command_quotes_arguments_with_shell_metacharacters() {
        assert_eq!(
            render_command("cargo", &["teaql", "--input", "model path", "evaluate"]),
            "cargo teaql --input 'model path' evaluate"
        );
        assert_eq!(shell_quote("it's"), "'it'\"'\"'s'");
    }

    #[test]
    fn safe_strings_are_not_quoted() {
        assert_eq!(shell_quote("cargo"), "cargo");
        assert_eq!(shell_quote("--input"), "--input");
        assert_eq!(shell_quote("model/path"), "model/path");
    }
}
