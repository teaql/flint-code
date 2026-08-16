#![cfg(unix)]

use std::collections::BTreeMap;
use std::os::unix::fs::PermissionsExt;
use std::path::{Path, PathBuf};
use std::time::Duration;
use tempfile::TempDir;
use tool_runner::remote_protocol::{
    ClientPolicy, ExecRequest, OperationState, RunnerRequest, RunnerResponse, WriteAtomicRequest,
};
use tool_runner::ssh_backend::{SessionMode, SshRunnerConnection, SshTargetConfig};

#[tokio::test]
async fn fake_ssh_covers_cache_session_replay_and_reattach() {
    let fixture = TempDir::new().unwrap();
    let fake_ssh = fixture.path().join("fake-ssh");
    let cache_root = fixture.path().join("cache");
    let session_root = fixture.path().join("sessions");
    let invocation_log = fixture.path().join("ssh-invocations.log");
    write_fake_ssh(&fake_ssh, &cache_root, &session_root, &invocation_log);

    let mut config = SshTargetConfig::new("fixture-target", env!("CARGO_BIN_EXE_klintcode-runner"));
    config.ssh_program = fake_ssh;
    config.bootstrap_command = env!("CARGO_BIN_EXE_klintcode-bootstrap").to_owned();
    config.connect_timeout = Duration::from_secs(10);
    config.upload_timeout = Duration::from_secs(30);
    config.rpc_timeout = Duration::from_secs(10);

    let connection = SshRunnerConnection::connect(
        config.clone(),
        SessionMode::Create {
            policy: ClientPolicy::default(),
        },
    )
    .await
    .unwrap();
    assert!(connection.session().created);
    assert_eq!(connection.session().workspace, ".");
    let session_id = connection.session_id().to_owned();

    let write = connection
        .request(RunnerRequest::FsWriteAtomic(WriteAtomicRequest {
            path: "probe.txt".into(),
            content: "persisted".into(),
            create_parents: false,
        }))
        .await
        .unwrap();
    assert!(matches!(write, RunnerResponse::WriteAtomic(_)));

    let request = ExecRequest {
        operation_id: "fixture-cargo-version".into(),
        program: "cargo".into(),
        argv: vec!["--version".into()],
        cwd: ".".into(),
        env: BTreeMap::new(),
        env_refs: Vec::new(),
        timeout_secs: Some(10),
        max_output_bytes: Some(64 * 1024),
        stream_output: false,
    };
    let started = connection.start_or_replay(request.clone()).await.unwrap();
    assert!(!started.replayed);
    let terminal = wait_for_terminal(&connection, &request.operation_id).await;
    assert_eq!(terminal.state, OperationState::Exited);
    assert_eq!(terminal.result.as_ref().unwrap().exit_code, Some(0));
    let replay = connection.start_or_replay(request).await.unwrap();
    assert!(replay.replayed);
    assert_eq!(replay.operation, terminal);
    connection.close().await.unwrap();

    let recovered = SshRunnerConnection::connect(
        config,
        SessionMode::Attach {
            session_id,
            policy: ClientPolicy::default(),
        },
    )
    .await
    .unwrap();
    assert!(!recovered.session().created);
    assert_eq!(
        recovered
            .operation_status("fixture-cargo-version")
            .await
            .unwrap(),
        terminal
    );
    recovered.close().await.unwrap();

    let invocations = std::fs::read_to_string(invocation_log).unwrap();
    assert_eq!(
        invocations
            .lines()
            .filter(|line| line.contains(" check "))
            .count(),
        2
    );
    assert_eq!(
        invocations
            .lines()
            .filter(|line| line.contains(" install "))
            .count(),
        1,
        "the content-addressed runner should only upload on the first cache miss"
    );
    assert_eq!(
        invocations
            .lines()
            .filter(|line| line.contains(" launch "))
            .count(),
        2
    );
}

async fn wait_for_terminal(
    connection: &SshRunnerConnection,
    operation_id: &str,
) -> tool_runner::remote_protocol::OperationRecord {
    for _ in 0..100 {
        let operation = connection.operation_status(operation_id).await.unwrap();
        if operation.state != OperationState::Running {
            return operation;
        }
        tokio::time::sleep(Duration::from_millis(25)).await;
    }
    panic!("operation did not complete")
}

fn write_fake_ssh(path: &Path, cache_root: &Path, session_root: &Path, invocation_log: &Path) {
    let script = format!(
        "#!/bin/sh\n\
         while [ \"$#\" -gt 0 ]; do\n\
           case \"$1\" in\n\
             -T) shift ;;\n\
             -o|-F) shift 2 ;;\n\
             *) break ;;\n\
           esac\n\
         done\n\
         [ \"$#\" -gt 0 ] || exit 91\n\
         shift\n\
         printf '%s\\n' \"$*\" >> {}\n\
         export KLINTCODE_RUNNER_CACHE_ROOT={}\n\
         export KLINTCODE_SESSION_ROOT={}\n\
         exec \"$@\"\n",
        shell_quote(invocation_log),
        shell_quote(cache_root),
        shell_quote(session_root),
    );
    std::fs::write(path, script).unwrap();
    std::fs::set_permissions(path, std::fs::Permissions::from_mode(0o700)).unwrap();
}

fn shell_quote(path: &Path) -> String {
    let value = path.to_string_lossy();
    format!("'{}'", value.replace('\'', "'\\''"))
}

#[test]
fn binary_paths_are_available_to_the_integration_fixture() {
    for path in [
        PathBuf::from(env!("CARGO_BIN_EXE_klintcode-bootstrap")),
        PathBuf::from(env!("CARGO_BIN_EXE_klintcode-runner")),
    ] {
        assert!(path.is_file(), "missing fixture binary {}", path.display());
    }
}
