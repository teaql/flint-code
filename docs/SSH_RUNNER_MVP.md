# SSH Runner MVP

FlintCode uses SSH as the mandatory project execution plane for both the
CLI Pipeline and the TUI. The local process remains the control plane: it owns
the model connection, task inputs, conversation state, RAG, Skills, logs, and
locator reports. Project files, TeaQL/code-generation commands, builds, tests,
and follow-up edits belong to one durable remote runner workspace.

There is no local project-execution fallback. A missing execution profile,
SSH/bootstrap failure, runner capability mismatch, lost session, or remote
policy rejection is an infrastructure failure and stops the task.

## Trust boundary

SSH is only the byte transport. The remote `klintcode-runner` owns:

- the durable workspace and session identity;
- structured file operations and deterministic workspace snapshots;
- direct program execution without `sh -c`;
- the effective policy, output limits, cancellation, and operation journal;
- reconnect/replay by stable session and operation IDs.

The local client may select a named target, upload a digest-pinned runner,
send typed requests, and reattach a previous session. It never substitutes a
local project command when remote execution fails.

The protocol currently supports stat/existence/hash, UTF-8 ranged reads,
bounded binary artifact chunks, list/walk/literal search, deterministic
snapshots, atomic and compare-and-swap writes, structured execution,
cancellation, operation replay, detach, and reattach. Paths are
workspace-relative, traversal and symlink traversal are rejected, and frames
are bounded.

## Binaries and lifecycle

- `klintcode-bootstrap` is the small preinstalled trust anchor. Its `check`,
  `install`, and `launch` commands use an exact SHA-256 and a content-addressed
  cache.
- `klintcode-runner` serves the versioned NDJSON protocol on stdin/stdout.
  Its stderr is reserved for bounded operator diagnostics.
- `klintcode-remote-probe` is a low-level transport/session conformance probe.

Every client connection performs:

1. bootstrap cache check and, on a miss, verified runner upload;
2. launch of that exact runner digest;
3. protocol hello and required-capability negotiation;
4. create or attach of one durable task session;
5. typed file/process operations;
6. detach of the SSH bridge while preserving session state.

The identifiers have deliberately different lifetimes:

- task: the user's continuous coding task;
- session ID: the durable remote workspace for that task;
- operation ID: one idempotent process request and its persisted result;
- SSH connection: disposable transport that may be re-established.

After a disconnect the client reattaches with the exact session ID,
environment-reference declaration, and no-broader policy. It queries the
original operation ID instead of blindly launching a duplicate. With the
current stdio runner, a process that was still running when the bridge died is
recorded as interrupted on the next attach; a resident runner daemon is not
implemented yet.

## Build Linux x86-64 artifacts

The uploaded binaries must match the remote OS and CPU. For `ca-mini`, build
static Linux x86-64 artifacts from the repository root:

```bash
cargo zigbuild -p tool-runner --release --offline \
  --target x86_64-unknown-linux-musl \
  --bin klintcode-bootstrap \
  --bin klintcode-runner
```

The two artifacts are then:

```text
target/x86_64-unknown-linux-musl/release/klintcode-bootstrap
target/x86_64-unknown-linux-musl/release/klintcode-runner
```

A macOS binary cannot run on `ca-mini`. The `target_triple` field in the
execution profile is validated deployment metadata; the operator is still
responsible for building the correct artifact.

## Configure the `ca-mini` SSH alias

Rust launches `ssh` directly and does not expand shell aliases such as
`ca-mini='ssh -p ...'`. Put the target in OpenSSH configuration instead:

```sshconfig
Host ca-mini
    HostName iot.doublechaintech.com
    User philip
    Port 56022
    IdentityFile /Users/Philip/.ssh/id_ed25519
    IdentitiesOnly yes
```

Place the administrator-verified host key in
`/Users/Philip/.ssh/known_hosts`. Do not accept an unverified key merely to
make the first smoke test pass. The client enables strict host-key checking
and disables PTY, agent/X11 forwarding, local forwarding, and interactive
authentication.

## Install the bootstrap and host policy

Bootstrap installation is an explicit operator action. The commands below are
deployment commands, not model-executable project commands:

```bash
scp target/x86_64-unknown-linux-musl/release/klintcode-bootstrap \
  ca-mini:/tmp/klintcode-bootstrap

scp /Users/Philip/.config/klintcode/ca-mini-runner-policy.toml \
  ca-mini:/tmp/klintcode-runner-policy.toml

ssh ca-mini install -d -m 700 /home/philip/.local/bin
ssh ca-mini install -d -m 700 /home/philip/.config/klintcode
ssh ca-mini install -d -m 700 /home/philip/.local/share/klintcode-runner
ssh ca-mini install -m 700 /tmp/klintcode-bootstrap \
  /home/philip/.local/bin/klintcode-bootstrap
ssh ca-mini install -m 600 /tmp/klintcode-runner-policy.toml \
  /home/philip/.config/klintcode/runner-policy.toml
```

A practical MVP hard policy is shown below. It deliberately does not inherit
the real host `CARGO_HOME` or `RUSTUP_HOME`. The runner creates a private
`CARGO_HOME` under the task session and constructs a server-owned safe `PATH`.

```toml
allowed_programs = ["cargo", "rustc", "git", "mvn", "java"]

# Optional absolute mappings. Unmapped standard tools are resolved only from
# fixed server-owned locations. For cargo/rustc, the runner resolves the
# concrete default rustup toolchain before considering ~/.cargo/bin proxies.
program_paths = { git = "/usr/bin/git" }

allowed_env = []
allowed_env_refs = []
inherited_env = ["PATH", "LANG", "LC_ALL", "USER"]

readable = ["**"]
writable = ["**"]
denied = [
  ".env", "**/.env",
  "*.key", "**/*.key",
  "*.pem", "**/*.pem",
  "secrets", "secrets/**", "**/secrets/**",
  ".git", ".git/**", "**/.git", "**/.git/**",
  "lib/src", "lib/src/**", "**/lib/src", "**/lib/src/**",
  ".klintcode", ".klintcode/**",
]

max_timeout_secs = 3600
max_output_bytes = 262144
max_read_bytes = 262144
max_write_bytes = 1048576
max_list_entries = 2000
max_args = 256
max_arg_bytes = 65536
allow_session_sqlite = true
```

If the operator chooses an explicit Cargo mapping, use the concrete toolchain
path returned by the following command, not the rustup proxy, unless an
isolated rustup home has also been provisioned:

```bash
ssh ca-mini /home/philip/.cargo/bin/rustup which cargo
ssh ca-mini /home/philip/.cargo/bin/rustup which rustc
ssh ca-mini /home/philip/.cargo/bin/cargo-teaql --version
```

`cargo-teaql` must report exactly `2.0.11`. The runner preserves rustup proxy
`argv[0]` semantics when a proxy is explicitly mapped, but it does not expose
the real host rustup/cargo homes by default.

## Complete execution profile

Both `klintcode-cli run`/`evaluate` and `flintcode-tui-legacy` require an execution
profile. Unknown fields are rejected. All local and remote paths shown here
must be absolute; substitute paths only with their real absolute values.

Save this example as
`/Users/Philip/.config/klintcode/remote-execution.toml`:

```toml
schema_version = 1
default_target = "ca-mini"

[targets.ca-mini]
kind = "ssh"
host_alias = "ca-mini"
ssh_program = "/usr/bin/ssh"
ssh_config_path = "/Users/Philip/.ssh/config"
known_hosts_path = "/Users/Philip/.ssh/known_hosts"
bootstrap_command = "/home/philip/.local/bin/klintcode-bootstrap"

[targets.ca-mini.environment_refs.DATABASE_URL]
kind = "session_sqlite"

[targets.ca-mini.environment_refs.SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL]
kind = "session_sqlite"

[targets.ca-mini.environment_refs.MOVING_COMPANY_SERVICE_CORE_DATABASE_URL]
kind = "session_sqlite"

[targets.ca-mini.runner]
local_runner_path = "/Users/Philip/githome/flint-code/target/x86_64-unknown-linux-musl/release/klintcode-runner"
target_triple = "x86_64-unknown-linux-musl"
remote_session_root = "/home/philip/.local/share/klintcode-runner"
remote_hard_policy_path = "/home/philip/.config/klintcode/runner-policy.toml"

[targets.ca-mini.timeouts]
connect_secs = 15
upload_secs = 300
rpc_secs = 3600
close_secs = 10

[targets.ca-mini.limits]
max_line_bytes = 8388608
max_install_output_bytes = 65536

[targets.ca-mini.policy]
allowed_programs = ["cargo", "rustc", "git", "mvn", "java"]
allowed_env = []
allowed_env_refs = [
  "DATABASE_URL",
  "SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL",
  "MOVING_COMPANY_SERVICE_CORE_DATABASE_URL",
]
inherited_env = ["PATH", "LANG", "LC_ALL", "USER"]
readable = ["**"]
writable = ["**"]
denied = [
  ".env", "**/.env",
  "*.key", "**/*.key",
  "*.pem", "**/*.pem",
  "secrets/**", "**/secrets/**",
  ".git/**", "**/.git/**",
  "lib/src/**", "**/lib/src/**",
  ".klintcode", ".klintcode/**",
]
max_timeout_secs = 3600
max_output_bytes = 262144
max_read_bytes = 262144
max_write_bytes = 1048576
max_list_entries = 2000
allow_session_sqlite = true
```

The schema fields are:

| Section | Fields | Meaning |
|---|---|---|
| top level | `schema_version`, `default_target` | Version 1 and the named target used when `--execution-target` is omitted. |
| `targets.<name>` | `kind`, `host_alias`, `ssh_program`, `ssh_config_path`, `known_hosts_path`, `bootstrap_command` | Strict SSH transport and remote bootstrap selection. `kind` is currently only `ssh`. |
| `targets.<name>.environment_refs` | map of names to `{ kind = "host" }` or `{ kind = "session_sqlite" }` | Declares values resolved by the runner. Values never appear in this file. Secret-looking names are rejected; SQLite names must be `DATABASE_URL` or end in `_DATABASE_URL`. |
| `.runner` | `local_runner_path`, `target_triple`, `remote_session_root`, `remote_hard_policy_path` | Local upload artifact plus remote `--root` and `--policy` launch arguments. |
| `.timeouts` | `connect_secs`, `upload_secs`, `rpc_secs`, `close_secs` | SSH connect, upload, per-RPC, and graceful-close deadlines. Build/acceptance RPCs may be configured up to 3600 seconds. |
| `.limits` | `max_line_bytes`, `max_install_output_bytes` | Bounded NDJSON frame and bootstrap diagnostic limits. |
| `.policy` | `allowed_programs`, `allowed_env`, `allowed_env_refs`, `inherited_env`, `readable`, `writable`, `denied` | Client restrictions intersected with the host hard policy. They can only narrow access. |
| `.policy` | `max_timeout_secs`, `max_output_bytes`, `max_read_bytes`, `max_write_bytes`, `max_list_entries`, `allow_session_sqlite` | Client resource limits and SQLite capability selection. |

`allowed_env` contains names for explicit per-command values.
`allowed_env_refs` contains declared reference names, not values. `PATH`,
`HOME`, and `CARGO_HOME` are runner-owned and cannot be overridden by a model
request. Do not add the host `CARGO_HOME` to the profile: it can contain Cargo
registry credentials.

## `ca-mini` smoke tests

First run the low-level probe. It verifies bootstrap cache/install, digest-pinned
launch, hello/capabilities, write/read/list, structured `cargo --version`,
operation replay, detach, and reattach:

```bash
cargo run --release -p tool-runner --bin klintcode-remote-probe -- \
  --target ca-mini \
  --runner /Users/Philip/githome/flint-code/target/x86_64-unknown-linux-musl/release/klintcode-runner \
  --bootstrap /home/philip/.local/bin/klintcode-bootstrap \
  --ssh-config /Users/Philip/.ssh/config \
  --known-hosts /Users/Philip/.ssh/known_hosts \
  --json
```

The probe launches the runner with its default root/policy. It is a transport
conformance test, not a substitute for validating the configured host policy.
Its JSON includes the full session ID. A second probe may explicitly reattach:

```bash
cargo run --release -p tool-runner --bin klintcode-remote-probe -- \
  --target ca-mini \
  --runner /Users/Philip/githome/flint-code/target/x86_64-unknown-linux-musl/release/klintcode-runner \
  --bootstrap /home/philip/.local/bin/klintcode-bootstrap \
  --ssh-config /Users/Philip/.ssh/config \
  --known-hosts /Users/Philip/.ssh/known_hosts \
  --session-id FULL_SESSION_ID
```

Then exercise the actual Pipeline configuration with a small task:

```bash
cargo run -p klintcode-cli -- run \
  --task benchmarks/tasks/school-continuous-rust \
  --profile profiles/mimo-v2.5-pro.toml \
  --output runs/ca-mini-smoke \
  --execution-config /Users/Philip/.config/klintcode/remote-execution.toml \
  --execution-target ca-mini \
  --fail-fast
```

The final local artifact is a locator manifest, not a copied project. It
contains the full durable `session_id`, logical remote workspace, build target,
model-file names, and verified-interaction count needed to locate or resume the
authoritative remote workspace.

Start the TUI against the same target with:

```bash
cargo run -p flintcode-tui-legacy -- \
  --profile profiles/mimo-v2.5-pro.toml \
  --execution-config /Users/Philip/.config/klintcode/remote-execution.toml \
  --execution-target ca-mini
```

To attach a previously persisted workspace, add
`--resume-session FULL_SESSION_ID` to either the single-task CLI command or TUI
command. Attach is strict: if the session is absent or its effective policy,
runner digest, or environment-reference contract is incompatible, recovery
stops instead of creating a replacement workspace.

## Session reuse

- One CLI `run` invocation uses the same session for its primary task and every
  `--follow-up`. Queue entries and repeats are separate tasks and therefore get
  separate sessions.
- CLI `--resume-session` requires exactly one `--task` and `--repeat 1`.
- The TUI starts in attached task mode. After a completed run, ordinary task
  input continues on the same reusable workspace/session. `/ask` and `/chat`
  answer without changing it.
- `/done` leaves task-input mode after the current operation stops. `/task ...`
  can explicitly continue. `/new` detaches the old durable session and prepares
  a new task/session; it does not delete the old remote workspace.
- Quitting detaches the SSH bridge but retains the durable session for an
  explicit future `--resume-session`.

## No local fallback

The CLI and TUI require `--execution-config` for project work. Loading the
model profile, task instructions, Skills, and local result manifests remains a
control-plane activity. The following always remain remote for an active task:

- project workspace reads and writes;
- TeaQL generation and assist commands;
- Cargo, Rust, Maven, Java, Git, and acceptance commands;
- build/test repair loops and follow-up modifications.

An SSH error is not model-repairable. The queue stops on infrastructure
failure, and the TUI reports the infrastructure failure. Neither path invokes
the older local executor as a fallback.

## Security status and limitations

The current `ca-mini` account (`philip`) is a functional smoke-test target, not
a production sandbox. The runner's protocol policy protects its file API and
controls which top-level programs it starts, but it is not an OS isolation
boundary. Cargo build scripts, proc macros, Maven/Gradle plugins, test binaries,
Git hooks, and child processes execute with the remote Unix user's authority.
They may access files or networks that the structured file API denies.

In particular:

- session-private `CARGO_HOME` prevents accidental use of the host Cargo
  credentials, but it does not stop malicious code from opening other files
  visible to the same Unix user;
- the ordinary account has an existing home and tools and must be assumed to
  contain sensitive state;
- Docker being installed on the host does not mean this runner automatically
  executes inside a container;
- the command allowlist, path globs, and SSH transport are not substitutes for
  a disposable VM/container or microVM.

Before production use, run the runner as a dedicated secret-free user inside a
disposable, resource-limited VM/container with no host-home mounts, controlled
network egress, blocked cloud metadata, short-lived credentials, a TTL, and an
administrator-owned hard policy. Use a forced SSH command and restricted key.

Other MVP limitations:

- bootstrap SHA-256 verifies transport/storage integrity, not publisher
  identity; production needs an administrator signature or signed index;
- RPC is sequential and returns bounded terminal output rather than live
  output events;
- an in-flight process does not survive loss of the stdio bridge;
- Unix session locking/process-group cleanup are not portable to every target;
- same-user filesystem races do not yet use Linux `openat2`/directory FDs for
  every operation;
- chunked artifact export exists, but final project export/publish is not yet
  automatic; the normal final artifact remains a locator manifest;
- session sealing, TTL cleanup, image provisioning, and verified Git publish
  remain deployment work.
