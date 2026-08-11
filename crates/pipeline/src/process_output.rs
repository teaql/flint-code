//! Bounded, timeout-aware capture for child processes.

use std::process::{ExitStatus, Stdio};
use std::time::Duration;
use tokio::io::{AsyncRead, AsyncReadExt};

/// Completed process output with each stream bounded in memory.
pub(crate) struct CapturedOutput {
    pub status: ExitStatus,
    pub stdout: Vec<u8>,
    pub stderr: Vec<u8>,
}

/// Run a child while draining both pipes continuously and retaining at most
/// `max_stream_bytes` from each stream.
pub(crate) async fn run_bounded_output(
    command: &mut tokio::process::Command,
    timeout: Duration,
    max_stream_bytes: usize,
) -> Result<CapturedOutput, String> {
    command.stdout(Stdio::piped()).stderr(Stdio::piped());
    let mut child = command
        .spawn()
        .map_err(|error| format!("failed to start process: {error}"))?;
    let stdout = child
        .stdout
        .take()
        .ok_or_else(|| "failed to capture process stdout".to_string())?;
    let stderr = child
        .stderr
        .take()
        .ok_or_else(|| "failed to capture process stderr".to_string())?;

    let completion = async {
        let (status, (stdout, stderr)) = tokio::join!(child.wait(), async {
            tokio::join!(
                read_bounded(stdout, max_stream_bytes),
                read_bounded(stderr, max_stream_bytes)
            )
        });
        let status = status.map_err(|error| format!("failed to wait for process: {error}"))?;
        let stdout = stdout.map_err(|error| format!("failed to read process stdout: {error}"))?;
        let stderr = stderr.map_err(|error| format!("failed to read process stderr: {error}"))?;
        Ok::<_, String>(CapturedOutput {
            status,
            stdout,
            stderr,
        })
    };

    match tokio::time::timeout(timeout, completion).await {
        Ok(result) => result,
        Err(_) => {
            let _ = child.kill().await;
            Err(format!(
                "process timed out after {} seconds",
                timeout.as_secs()
            ))
        }
    }
}

async fn read_bounded<R>(mut reader: R, limit: usize) -> std::io::Result<Vec<u8>>
where
    R: AsyncRead + Unpin,
{
    let mut retained = Vec::with_capacity(limit.min(16 * 1024));
    let mut total = 0usize;
    let mut buffer = [0u8; 8 * 1024];
    loop {
        let count = reader.read(&mut buffer).await?;
        if count == 0 {
            break;
        }
        total = total.saturating_add(count);
        if retained.len() < limit {
            let keep = count.min(limit - retained.len());
            retained.extend_from_slice(&buffer[..keep]);
        }
    }
    if total > retained.len() {
        retained.extend_from_slice(format!("\n[output truncated: {total} bytes total]").as_bytes());
    }
    Ok(retained)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[tokio::test]
    async fn capture_drains_but_bounds_large_output() {
        let mut command = tokio::process::Command::new("sh");
        command.args(["-c", "yes x | head -c 100000"]);
        let output = run_bounded_output(&mut command, Duration::from_secs(5), 1_024)
            .await
            .unwrap();

        assert!(output.status.success());
        assert!(output.stdout.len() < 1_100);
        assert!(String::from_utf8_lossy(&output.stdout).contains("output truncated"));
    }
}
