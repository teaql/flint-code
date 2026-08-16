//! Bounded Agent-neutral JSONL bridge to the SSH execution plane.
//!
//! The calling Agent owns its interactive loop. This bridge owns no model
//! state: it translates a small file and command protocol into the same
//! policy-checked `RemoteExecution` calls used by FlintCode itself.

use std::collections::BTreeSet;
use std::sync::Arc;
use std::time::Duration;

use serde::{Deserialize, Serialize};
use serde_json::{Value, json};
use tokio::io::{AsyncBufReadExt, AsyncWriteExt, BufReader};
use tool_runner::remote_protocol::{ExpectedFileState, FileKind, OperationState};

use execution_policy::{
    approve_remote_command, checked_remote_workspace_path, is_generated_library_source_relative,
    is_read_only_workspace_evidence_relative, join_remote_workspace_path,
};
use tool_runner::remote_execution::RemoteExecution;

const PROTOCOL_VERSION: u32 = 1;
const MAX_REQUEST_BYTES: usize = 2 * 1024 * 1024;
const MAX_FILE_BYTES: u64 = 1024 * 1024;
const MAX_LIST_ENTRIES: u32 = 10_000;
const MAX_SEARCH_MATCHES: u32 = 1_000;
const MAX_COMMAND_OUTPUT_BYTES: u64 = 256 * 1024;
const MAX_OUTPUT_PAGE_BYTES: u64 = 32 * 1024;

/// Serve Agent bridge requests on stdin/stdout until EOF or `shutdown`.
///
/// Stdout is protocol-only JSONL. Diagnostics must be written to stderr.
pub async fn serve(
    execution: Arc<RemoteExecution>,
    remote_cwd: String,
) -> Result<(), AgentBridgeError> {
    let remote_cwd = checked_remote_workspace_path(&remote_cwd)
        .map_err(AgentBridgeError::InvalidRemoteWorkspace)?;
    if is_generated_library_source_relative(&remote_cwd) {
        return Err(AgentBridgeError::InvalidRemoteWorkspace(
            "remote cwd cannot be generated library source".to_owned(),
        ));
    }
    let stdin = tokio::io::stdin();
    let mut lines = BufReader::new(stdin).lines();
    let mut stdout = tokio::io::stdout();
    let mut state = BridgeState {
        execution,
        remote_cwd,
        operations: BTreeSet::new(),
    };

    while let Some(line) = lines.next_line().await? {
        let response = if line.len() > MAX_REQUEST_BYTES {
            BridgeResponse::error(Value::Null, "request_too_large", "request exceeds 2 MiB")
        } else {
            match serde_json::from_str::<BridgeRequest>(&line) {
                Ok(request) => handle_request(&mut state, request).await,
                Err(error) => BridgeResponse::error(
                    Value::Null,
                    "invalid_request",
                    format!("invalid JSON request: {error}"),
                ),
            }
        };
        let shutdown = response.shutdown;
        let mut encoded = serde_json::to_vec(&response)?;
        encoded.push(b'\n');
        stdout.write_all(&encoded).await?;
        stdout.flush().await?;
        if shutdown {
            break;
        }
    }
    for operation_id in std::mem::take(&mut state.operations) {
        let _ = state.execution.cancel_operation(operation_id).await;
    }
    state.execution.detach().await?;
    Ok(())
}

struct BridgeState {
    execution: Arc<RemoteExecution>,
    remote_cwd: String,
    operations: BTreeSet<String>,
}

#[derive(Debug, Deserialize)]
#[serde(deny_unknown_fields)]
struct BridgeRequest {
    id: Value,
    method: String,
    #[serde(default)]
    params: Value,
}

#[derive(Debug, Serialize)]
struct BridgeResponse {
    id: Value,
    ok: bool,
    #[serde(skip_serializing_if = "Option::is_none")]
    result: Option<Value>,
    #[serde(skip_serializing_if = "Option::is_none")]
    error: Option<BridgeErrorBody>,
    #[serde(skip)]
    shutdown: bool,
}

#[derive(Debug, Serialize)]
struct BridgeErrorBody {
    code: String,
    message: String,
}

impl BridgeResponse {
    fn success(id: Value, result: Value) -> Self {
        Self {
            id,
            ok: true,
            result: Some(result),
            error: None,
            shutdown: false,
        }
    }

    fn error(id: Value, code: impl Into<String>, message: impl Into<String>) -> Self {
        Self {
            id,
            ok: false,
            result: None,
            error: Some(BridgeErrorBody {
                code: code.into(),
                message: message.into(),
            }),
            shutdown: false,
        }
    }
}

async fn handle_request(state: &mut BridgeState, request: BridgeRequest) -> BridgeResponse {
    let id = request.id;
    let result = match request.method.as_str() {
        "hello" => Ok(json!({
            "protocol": "flintcode-agent-bridge-v1",
            "protocol_version": PROTOCOL_VERSION,
            "session_id": state.execution.session_id().await,
            "remote_cwd": state.remote_cwd,
            "capabilities": ["fs.read", "fs.stat", "fs.write", "fs.list", "fs.walk", "fs.search_literal", "exec.start", "exec.poll", "exec.cancel"]
        })),
        "fs.read" => read_file(&state.execution, &state.remote_cwd, &request.params).await,
        "fs.stat" => stat_file(&state.execution, &state.remote_cwd, &request.params).await,
        "fs.write" => write_file(&state.execution, &state.remote_cwd, &request.params).await,
        "fs.list" => list_directory(&state.execution, &state.remote_cwd, &request.params).await,
        "fs.walk" => walk_files(&state.execution, &state.remote_cwd, &request.params).await,
        "fs.search" => search_files(&state.execution, &state.remote_cwd, &request.params).await,
        "exec.start" => start_command(state, &request.params).await,
        "exec.poll" => poll_command(state, &request.params).await,
        "exec.cancel" => cancel_command(state, &request.params).await,
        "shutdown" => {
            let mut response = BridgeResponse::success(id, json!({ "detached": true }));
            response.shutdown = true;
            return response;
        }
        _ => Err((
            "method_not_found",
            format!("unknown method `{}`", request.method),
        )),
    };
    match result {
        Ok(value) => BridgeResponse::success(id, value),
        Err((code, message)) => BridgeResponse::error(id, code, message),
    }
}

fn string_param<'a>(params: &'a Value, name: &str) -> Result<&'a str, (&'static str, String)> {
    params
        .get(name)
        .and_then(Value::as_str)
        .filter(|value| !value.is_empty())
        .ok_or_else(|| {
            (
                "invalid_params",
                format!("`{name}` must be a non-empty string"),
            )
        })
}

fn checked_path(
    remote_cwd: &str,
    requested: &str,
    write: bool,
) -> Result<(String, String), (&'static str, String)> {
    let relative =
        checked_remote_workspace_path(requested).map_err(|message| ("path_rejected", message))?;
    if is_generated_library_source_relative(&relative) {
        return Err((
            "path_rejected",
            "generated library source is protected; use compiler diagnostics and cargo teaql assist"
                .to_owned(),
        ));
    }
    if write && is_read_only_workspace_evidence_relative(&relative) {
        return Err((
            "path_rejected",
            "validated model inputs, evidence, and the root AGENTS.md are read-only".to_owned(),
        ));
    }
    Ok((
        relative.clone(),
        join_remote_workspace_path(remote_cwd, &relative),
    ))
}

async fn read_file(
    execution: &Arc<RemoteExecution>,
    remote_cwd: &str,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let requested = string_param(params, "path")?;
    let (_, path) = checked_path(remote_cwd, requested, false)?;
    let stat = execution
        .stat(path.clone(), true)
        .await
        .map_err(infrastructure_error)?;
    if !stat.exists || stat.kind != Some(FileKind::File) {
        return Err(("not_found", format!("`{requested}` is not a regular file")));
    }
    let bytes = stat.bytes.unwrap_or(0);
    if bytes > MAX_FILE_BYTES {
        return Err((
            "file_too_large",
            format!("`{requested}` is {bytes} bytes; Pi bridge limit is {MAX_FILE_BYTES}"),
        ));
    }
    let content = execution
        .read_text(path)
        .await
        .map_err(infrastructure_error)?;
    Ok(json!({ "content": content, "bytes": content.len(), "sha256": stat.sha256 }))
}

async fn stat_file(
    execution: &Arc<RemoteExecution>,
    remote_cwd: &str,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let requested = string_param(params, "path")?;
    let (_, path) = checked_path(remote_cwd, requested, false)?;
    let stat = execution
        .stat(path, true)
        .await
        .map_err(infrastructure_error)?;
    Ok(json!({
        "exists": stat.exists,
        "kind": stat.kind,
        "bytes": stat.bytes,
        "sha256": stat.sha256
    }))
}

async fn write_file(
    execution: &Arc<RemoteExecution>,
    remote_cwd: &str,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let requested = string_param(params, "path")?;
    let content = params
        .get("content")
        .and_then(Value::as_str)
        .ok_or_else(|| ("invalid_params", "`content` must be a string".to_owned()))?;
    if content.len() as u64 > MAX_FILE_BYTES {
        return Err((
            "file_too_large",
            format!(
                "write is {} bytes; Pi bridge limit is {MAX_FILE_BYTES}",
                content.len()
            ),
        ));
    }
    let (_, path) = checked_path(remote_cwd, requested, true)?;
    let expected = if let Some(value) = params.get("expected_sha256").and_then(Value::as_str) {
        if value.len() != 64 || !value.bytes().all(|byte| byte.is_ascii_hexdigit()) {
            return Err((
                "invalid_params",
                "`expected_sha256` must be a 64-character hex digest".to_owned(),
            ));
        }
        ExpectedFileState::Sha256 {
            value: value.to_owned(),
        }
    } else {
        let stat = execution
            .stat(path.clone(), true)
            .await
            .map_err(infrastructure_error)?;
        if stat.exists {
            let value = stat.sha256.ok_or_else(|| {
                (
                    "not_a_file",
                    format!("`{requested}` is not a regular writable file"),
                )
            })?;
            ExpectedFileState::Sha256 { value }
        } else {
            ExpectedFileState::Missing
        }
    };
    let written = execution
        .write_text_cas(path, content, true, expected)
        .await
        .map_err(infrastructure_error)?;
    Ok(json!({ "path": requested, "bytes": written.bytes }))
}

async fn list_directory(
    execution: &Arc<RemoteExecution>,
    remote_cwd: &str,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let requested = params.get("path").and_then(Value::as_str).unwrap_or(".");
    let (relative, path) = checked_path(remote_cwd, requested, false)?;
    let listing = execution
        .list(path, Some(MAX_LIST_ENTRIES))
        .await
        .map_err(infrastructure_error)?;
    let entries = listing
        .entries
        .into_iter()
        .filter(|entry| {
            let child = if relative == "." {
                entry.name.clone()
            } else {
                format!("{relative}/{}", entry.name)
            };
            !is_generated_library_source_relative(&child)
        })
        .map(|entry| json!({ "name": entry.name, "kind": entry.kind, "bytes": entry.bytes }))
        .collect::<Vec<_>>();
    Ok(json!({ "entries": entries, "truncated": listing.truncated }))
}

async fn walk_files(
    execution: &Arc<RemoteExecution>,
    remote_cwd: &str,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let requested = params.get("path").and_then(Value::as_str).unwrap_or(".");
    let (_, path) = checked_path(remote_cwd, requested, false)?;
    let excluded_paths = [
        "lib/src",
        "rust-lib-core/lib/src",
        "java-lib-core",
        "java-web-spring-boot",
    ]
    .into_iter()
    .map(|path| join_remote_workspace_path(remote_cwd, path))
    .collect();
    let listing = execution
        .walk_excluding(path, Some(MAX_LIST_ENTRIES), Some(32), excluded_paths)
        .await
        .map_err(infrastructure_error)?;
    let entries = listing
        .entries
        .into_iter()
        .filter_map(|entry| {
            let relative = relative_to_cwd(remote_cwd, &entry.path)?;
            (!is_generated_library_source_relative(&relative)).then_some(json!({
                "path": relative,
                "kind": entry.kind,
                "bytes": entry.bytes
            }))
        })
        .collect::<Vec<_>>();
    Ok(json!({ "entries": entries, "truncated": listing.truncated }))
}

async fn search_files(
    execution: &Arc<RemoteExecution>,
    remote_cwd: &str,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let requested = params.get("path").and_then(Value::as_str).unwrap_or(".");
    let query = string_param(params, "query")?;
    let (_, path) = checked_path(remote_cwd, requested, false)?;
    let excluded_paths = [
        "lib/src",
        "rust-lib-core/lib/src",
        "java-lib-core",
        "java-web-spring-boot",
    ]
    .into_iter()
    .map(|path| join_remote_workspace_path(remote_cwd, path))
    .collect();
    let search = execution
        .search(
            path,
            query,
            Some(MAX_SEARCH_MATCHES),
            Some(MAX_FILE_BYTES),
            excluded_paths,
        )
        .await
        .map_err(infrastructure_error)?;
    let matches = search
        .matches
        .into_iter()
        .filter_map(|matched| {
            let relative = relative_to_cwd(remote_cwd, &matched.path)?;
            (!is_generated_library_source_relative(&relative)).then_some(json!({
                "path": relative,
                "line": matched.line,
                "column": matched.column,
                "preview": matched.preview
            }))
        })
        .collect::<Vec<_>>();
    Ok(json!({
        "matches": matches,
        "files_scanned": search.files_scanned,
        "files_skipped": search.files_skipped,
        "bytes_scanned": search.bytes_scanned,
        "truncated": search.truncated
    }))
}

async fn start_command(
    state: &mut BridgeState,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let command = string_param(params, "command")?;
    let approved = approve_remote_command(command).map_err(|error| ("command_rejected", error))?;
    let timeout_ms = params
        .get("timeout_ms")
        .and_then(Value::as_u64)
        .unwrap_or(300_000)
        .clamp(1_000, 300_000);
    let operation = state
        .execution
        .start_streaming_operation(
            approved.program,
            approved.args,
            state.remote_cwd.clone(),
            Duration::from_millis(timeout_ms),
            MAX_COMMAND_OUTPUT_BYTES,
        )
        .await
        .map_err(infrastructure_error)?;
    state.operations.insert(operation.operation_id.clone());
    Ok(json!({ "operation_id": operation.operation_id, "state": operation.state }))
}

async fn poll_command(
    state: &mut BridgeState,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let operation_id = owned_operation_id(state, params)?;
    let stdout_offset = params
        .get("stdout_offset")
        .and_then(Value::as_u64)
        .unwrap_or(0);
    let stderr_offset = params
        .get("stderr_offset")
        .and_then(Value::as_u64)
        .unwrap_or(0);
    let output = state
        .execution
        .operation_output(
            operation_id.clone(),
            stdout_offset,
            stderr_offset,
            MAX_OUTPUT_PAGE_BYTES,
        )
        .await
        .map_err(infrastructure_error)?;
    let terminal = output.state != OperationState::Running;
    let record = if terminal {
        Some(
            state
                .execution
                .operation_status(operation_id.clone())
                .await
                .map_err(infrastructure_error)?,
        )
    } else {
        None
    };
    if terminal {
        state.operations.remove(&operation_id);
    }
    Ok(json!({
        "operation_id": operation_id,
        "state": output.state,
        "terminal": terminal,
        "stdout": output.stdout,
        "stdout_next_offset": output.stdout_next_offset,
        "stdout_truncated": output.stdout_truncated,
        "stderr": output.stderr,
        "stderr_next_offset": output.stderr_next_offset,
        "stderr_truncated": output.stderr_truncated,
        "exit_code": record.as_ref().and_then(|record| record.result.as_ref()).and_then(|result| result.exit_code),
        "message": record.and_then(|record| record.message)
    }))
}

async fn cancel_command(
    state: &mut BridgeState,
    params: &Value,
) -> Result<Value, (&'static str, String)> {
    let operation_id = owned_operation_id(state, params)?;
    let record = state
        .execution
        .cancel_operation(operation_id.clone())
        .await
        .map_err(infrastructure_error)?;
    state.operations.remove(&operation_id);
    Ok(json!({
        "operation_id": operation_id,
        "state": record.state,
        "exit_code": record.result.as_ref().and_then(|result| result.exit_code),
        "message": record.message
    }))
}

fn owned_operation_id(
    state: &BridgeState,
    params: &Value,
) -> Result<String, (&'static str, String)> {
    let operation_id = string_param(params, "operation_id")?.to_owned();
    if !state.operations.contains(&operation_id) {
        return Err((
            "operation_not_owned",
            "operation does not belong to this Pi bridge".to_owned(),
        ));
    }
    Ok(operation_id)
}

fn relative_to_cwd(remote_cwd: &str, path: &str) -> Option<String> {
    if remote_cwd == "." {
        return Some(path.to_owned());
    }
    path.strip_prefix(remote_cwd)
        .and_then(|suffix| suffix.strip_prefix('/'))
        .map(str::to_owned)
}

fn infrastructure_error(error: impl std::fmt::Display) -> (&'static str, String) {
    ("infrastructure", format!("[infrastructure] {error}"))
}

/// Bridge transport or remote execution failure.
#[derive(Debug, thiserror::Error)]
pub enum AgentBridgeError {
    #[error("bridge I/O failed: {0}")]
    Io(#[from] std::io::Error),
    #[error("bridge JSON encoding failed: {0}")]
    Json(#[from] serde_json::Error),
    #[error("remote execution failed: {0}")]
    Remote(#[from] tool_runner::remote_execution::RemoteExecutionError),
    #[error("invalid remote workspace: {0}")]
    InvalidRemoteWorkspace(String),
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn request_rejects_unknown_fields() {
        let request = r#"{"id":1,"method":"hello","params":{},"extra":true}"#;
        assert!(serde_json::from_str::<BridgeRequest>(request).is_err());
    }

    #[test]
    fn protected_paths_are_rejected_before_remote_access() {
        assert!(checked_path("attempt-01/build", "lib/src/q.rs", false).is_err());
        assert!(checked_path("attempt-01/build", "model/main.xml", true).is_err());
        assert!(checked_path("attempt-01/build", "src/main.rs", true).is_ok());
    }

    #[test]
    fn responses_never_serialize_shutdown_control_state() {
        let mut response = BridgeResponse::success(json!(1), json!({ "ok": true }));
        response.shutdown = true;
        let encoded = serde_json::to_string(&response).unwrap();
        assert!(!encoded.contains("shutdown"));
    }
}
