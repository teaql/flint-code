//! End-to-end SSH runner conformance probe.

use anyhow::{Context, Result, bail};
use serde::Serialize;
use std::path::PathBuf;
use std::time::Duration;
use tool_runner::remote_protocol::{
    ClientPolicy, ExecRequest, ListRequest, OperationRecord, OperationState, ReadRangeRequest,
    RunnerRequest, RunnerResponse, WriteAtomicRequest,
};
use tool_runner::ssh_backend::{SessionMode, SshRunnerConnection, SshTargetConfig};
use uuid::Uuid;

#[tokio::main]
async fn main() -> Result<()> {
    let options = Options::parse(std::env::args().skip(1))?;
    let mut config = SshTargetConfig::new(&options.target, &options.runner);
    config.bootstrap_command = options.bootstrap;
    config.ssh_config_path = options.ssh_config;
    config.known_hosts_path = options.known_hosts;

    let policy = probe_policy();
    let mode = options.session_id.map_or_else(
        || SessionMode::Create {
            policy: policy.clone(),
        },
        |session_id| SessionMode::Attach {
            session_id,
            policy: policy.clone(),
        },
    );
    let connection = SshRunnerConnection::connect(config.clone(), mode)
        .await
        .context("connect to SSH runner")?;
    require_capabilities(&connection)?;
    if connection.session().workspace != "." {
        bail!("runner exposed a non-logical workspace reference");
    }

    let probe_id = Uuid::new_v4().simple().to_string();
    let probe_directory = format!(".klintcode-probe/{probe_id}");
    let probe_file = format!("{probe_directory}/probe.txt");
    let content = format!("klintcode-remote-probe:{probe_id}\n");

    expect_response(
        connection
            .request(RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
                path: probe_file.clone(),
                content: content.clone(),
                create_parents: true,
            }))
            .await?,
        "write_atomic",
        |response| matches!(response, RunnerResponse::WriteAtomic(_)),
    )?;
    let read = connection
        .request(RunnerRequest::FsReadRange(ReadRangeRequest {
            path: probe_file.clone(),
            offset: 0,
            length: content.len() as u64,
        }))
        .await?;
    match read {
        RunnerResponse::ReadRange(read) if read.content == content && read.eof => {}
        _ => bail!("read_range did not return the exact probe content"),
    }
    let list = connection
        .request(RunnerRequest::FsList(ListRequest {
            path: probe_directory.clone(),
            max_entries: Some(16),
        }))
        .await?;
    match list {
        RunnerResponse::List(list)
            if list.entries.iter().any(|entry| entry.name == "probe.txt") => {}
        _ => bail!("list did not include the written probe file"),
    }

    let operation_id = format!("probe-cargo-{probe_id}");
    let exec = ExecRequest {
        operation_id: operation_id.clone(),
        program: "cargo".into(),
        argv: vec!["--version".into()],
        cwd: ".".into(),
        env: Default::default(),
        env_refs: Vec::new(),
        timeout_secs: Some(30),
        max_output_bytes: Some(64 * 1024),
        stream_output: false,
    };
    let start = connection.start_or_replay(exec.clone()).await?;
    if start.replayed {
        bail!("fresh probe operation was unexpectedly replayed");
    }
    let terminal = wait_for_terminal(&connection, &operation_id).await?;
    let result = terminal
        .result
        .as_ref()
        .context("terminal operation has no result")?;
    if terminal.state != OperationState::Exited
        || result.exit_code != Some(0)
        || !result.stdout.to_ascii_lowercase().contains("cargo")
    {
        bail!("structured cargo probe did not exit successfully");
    }
    let replay = connection.start_or_replay(exec).await?;
    if !replay.replayed || replay.operation != terminal {
        bail!("same operation ID did not replay the persisted result");
    }

    let session_id = connection.session_id().to_owned();
    let runner_sha256 = connection.runner_sha256().to_owned();
    let policy_digest = connection.session().policy_digest.clone();
    connection.close().await.context("detach initial bridge")?;

    let recovered = SshRunnerConnection::connect(
        config,
        SessionMode::Attach {
            session_id: session_id.clone(),
            policy,
        },
    )
    .await
    .context("reattach persisted runner session")?;
    let recovered_operation = recovered.operation_status(&operation_id).await?;
    if recovered_operation != terminal {
        bail!("reattach did not recover the persisted operation record");
    }
    let recovered_read = recovered
        .request(RunnerRequest::FsReadRange(ReadRangeRequest {
            path: probe_file,
            offset: 0,
            length: content.len() as u64,
        }))
        .await?;
    if !matches!(recovered_read, RunnerResponse::ReadRange(read) if read.content == content) {
        bail!("reattach did not preserve the remote workspace");
    }
    recovered.close().await.context("detach recovered bridge")?;

    let report = ProbeReport {
        passed: true,
        target: options.target,
        session_id,
        runner_sha256,
        policy_digest,
        operation_id,
        operation_state: terminal.state,
        exit_code: result.exit_code,
        reconnect_verified: true,
    };
    if options.json {
        println!("{}", serde_json::to_string_pretty(&report)?);
    } else {
        println!("SSH runner probe passed");
        println!("  target: {}", report.target);
        println!("  session: {}", report.session_id);
        println!("  runner: {}", report.runner_sha256);
        println!(
            "  operation: {} ({:?})",
            report.operation_id, report.operation_state
        );
        println!("  reconnect: verified");
    }
    Ok(())
}

fn probe_policy() -> ClientPolicy {
    ClientPolicy {
        allowed_programs: Some(vec!["cargo".into()]),
        allowed_env: Some(Vec::new()),
        readable: Some(vec![".klintcode-probe/**".into()]),
        writable: Some(vec![".klintcode-probe/**".into()]),
        max_timeout_secs: Some(30),
        max_output_bytes: Some(64 * 1024),
        max_read_bytes: Some(64 * 1024),
        max_write_bytes: Some(64 * 1024),
        max_list_entries: Some(64),
        ..ClientPolicy::default()
    }
}

fn require_capabilities(connection: &SshRunnerConnection) -> Result<()> {
    for capability in [
        "session.attach_or_create",
        "session.detach",
        "fs.read_range",
        "fs.list",
        "fs.write_atomic",
        "exec.structured",
        "exec.idempotent",
        "exec.cancel",
    ] {
        if !connection
            .hello()
            .capabilities
            .iter()
            .any(|value| value == capability)
        {
            bail!("runner is missing required capability {capability}");
        }
    }
    Ok(())
}

fn expect_response(
    response: RunnerResponse,
    operation: &str,
    predicate: impl FnOnce(&RunnerResponse) -> bool,
) -> Result<()> {
    if predicate(&response) {
        Ok(())
    } else {
        bail!("runner returned the wrong response type for {operation}")
    }
}

async fn wait_for_terminal(
    connection: &SshRunnerConnection,
    operation_id: &str,
) -> Result<OperationRecord> {
    let deadline = tokio::time::Instant::now() + Duration::from_secs(40);
    loop {
        let operation = connection.operation_status(operation_id).await?;
        if operation.state != OperationState::Running {
            return Ok(operation);
        }
        if tokio::time::Instant::now() >= deadline {
            let _ = connection.cancel_operation(operation_id).await;
            bail!("operation did not reach a terminal state before the probe deadline");
        }
        tokio::time::sleep(Duration::from_millis(100)).await;
    }
}

#[derive(Debug, Serialize)]
struct ProbeReport {
    passed: bool,
    target: String,
    session_id: String,
    runner_sha256: String,
    policy_digest: String,
    operation_id: String,
    operation_state: OperationState,
    exit_code: Option<i32>,
    reconnect_verified: bool,
}

struct Options {
    target: String,
    runner: PathBuf,
    bootstrap: String,
    ssh_config: Option<PathBuf>,
    known_hosts: Option<PathBuf>,
    session_id: Option<String>,
    json: bool,
}

impl Options {
    fn parse(args: impl Iterator<Item = String>) -> Result<Self> {
        let mut target = None;
        let mut runner = None;
        let mut bootstrap = "klintcode-bootstrap".to_owned();
        let mut ssh_config = None;
        let mut known_hosts = None;
        let mut session_id = None;
        let mut json = false;
        let mut args = args.peekable();
        while let Some(argument) = args.next() {
            match argument.as_str() {
                "--target" => target = Some(next_value(&mut args, "--target")?),
                "--runner" => runner = Some(PathBuf::from(next_value(&mut args, "--runner")?)),
                "--bootstrap" => bootstrap = next_value(&mut args, "--bootstrap")?,
                "--ssh-config" => {
                    ssh_config = Some(PathBuf::from(next_value(&mut args, "--ssh-config")?))
                }
                "--known-hosts" => {
                    known_hosts = Some(PathBuf::from(next_value(&mut args, "--known-hosts")?))
                }
                "--session-id" => session_id = Some(next_value(&mut args, "--session-id")?),
                "--json" => json = true,
                "-h" | "--help" => {
                    println!("{}", usage());
                    std::process::exit(0);
                }
                other => bail!("unknown argument {other:?}; {}", usage()),
            }
        }
        Ok(Self {
            target: target.context("--target is required")?,
            runner: runner.context("--runner is required")?,
            bootstrap,
            ssh_config,
            known_hosts,
            session_id,
            json,
        })
    }
}

fn next_value(args: &mut impl Iterator<Item = String>, option: &str) -> Result<String> {
    args.next()
        .with_context(|| format!("{option} needs a value"))
}

fn usage() -> &'static str {
    "usage: klintcode-remote-probe --target HOST_ALIAS --runner LOCAL_RUNNER \
     [--bootstrap REMOTE_COMMAND] [--ssh-config PATH] [--known-hosts PATH] \
     [--session-id ID] [--json]"
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn parses_minimal_probe_options() {
        let options = Options::parse(
            [
                "--target".into(),
                "ca-mini".into(),
                "--runner".into(),
                "/tmp/runner".into(),
                "--json".into(),
            ]
            .into_iter(),
        )
        .unwrap();
        assert_eq!(options.target, "ca-mini");
        assert_eq!(options.runner, PathBuf::from("/tmp/runner"));
        assert!(options.json);
    }
}
