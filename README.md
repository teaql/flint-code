# KlintCode

> [!WARNING]
> **Active development:** KlintCode is under active development. APIs,
> configuration, workflows, and the TUI may change without notice. It is not
> yet recommended for production use.

> *Strike code without the cloud.* — 燧石取火，离线生码。

**KlintCode** is an AI coding agent designed for **air-gapped and compliance-restricted environments**. It runs entirely inside customer-controlled infrastructure, requires no public internet connection, and produces **compiler-verified** production code. The target architecture keeps the Agent, memory, Skills, and enterprise knowledge access on the local control plane while all project file operations and tool execution happen on a disposable remote machine.

Built with Rust. Powered by [TeaQL](https://teaql.io).

## TUI Preview

![KlintCode interactive TUI showing the validated execution plan](docs/images/klintcode-tui.png)

## Why KlintCode?

Every major AI coding tool today — Cursor, Copilot, Devin, Windsurf — requires cloud connectivity. For organizations bound by regulatory, security, or data sovereignty constraints, **none of them are usable**.

KlintCode fills this gap:

| | Cloud Coding Agents | KlintCode |
|--|---------------------|-----------|
| **Network** | Requires internet | Air-gapped / offline |
| **Data privacy** | Code sent to cloud | Data never leaves premises |
| **Model size** | 100B+ parameters | Works with small local models |
| **Correctness** | Hope it compiles | **Compiler-verified** |
| **Compliance** | ❌ GDPR / HIPAA / 等保 | ✅ Fully compliant |

## How It Works

KlintCode doesn't try to be a general-purpose coding agent. Instead, it combines a **small local model** with a **deterministic validation pipeline** to guarantee correct output:

```
Business Requirements
       │
       ▼
┌──────────────────┐
│ LLM: Generate    │ ← Small model (Nemotron-3-Super, Llama, etc.)
│ KSML Domain Model│
└────────┬─────────┘
         │
    ┌────▼────┐
    │ L1-L2   │ Local XML validation
    │ L3      │ TeaQL domain validation (real semantics check)
    │ L4      │ cargo teaql rust-lib-core (generate runtime code)
    │ L5      │ cargo check (compiler verification ✓)
    │ L6      │ cargo teaql assist (extract real API signatures)
    │ L7      │ LLM: Generate business logic (guided by real APIs)
    │ L8      │ cargo check (final compilation ✓)
    └────┬────┘
         │
         ▼
  Verified, Compilable
  Production Rust Code
```

**The key insight**: We don't let the LLM guess APIs. TeaQL's `assist` system feeds the model **real, compiler-generated API signatures**. Combined with multi-level validation, even a small 8B model can produce code that **compiles on the first try**.

## Remote Execution Architecture and Delivery Checklist

This section is the source of truth for the isolation architecture. It records
both what the current prototype already supports and what is still planned.

Status legend:

- [x] Implemented in the current codebase
- [ ] Planned or not yet wired into the production execution path

### Isolation environment setup

The intended trust boundary is strict: the local KlintCode process is a control
plane, not a code execution environment. It may persist Agent memory, task
state, Skills, RAG results, and audit metadata, but it must not read or write a
project workspace or execute project commands. A disposable remote machine is
the only execution plane.

- [x] Local Agent state machine, TUI, model client, context budgeting, and Skill
  orchestration foundations exist.
- [x] Enterprise RAG client and retrieval status are represented in the current
  application.
- [x] Continuous follow-up tasks retain bounded context, validation summaries,
  and a session ledger.
- [x] Deterministic Cargo/Maven verification and real command exit status are
  available in the current local pipeline.
- [ ] Remove local project file tools and local command execution from the
  production Agent path. There must be no automatic local fallback.
- [ ] Replace the current SSH prototype, which invokes the host `ssh` process,
  with a production remote execution backend and authenticated runner protocol.
- [ ] Provide structured remote operations for ranged file reads, directory
  listing, search, patch application, validation, cancellation, Git status,
  commit, and push.
- [ ] Provision every task into a fresh VM or microVM with a fixed TTL. Containers
  may be supported where the customer accepts the weaker isolation boundary.
- [ ] Run the remote workspace as an unprivileged user under a fixed root such as
  `/workspace/project`, without host mounts or host filesystem visibility.
- [ ] Canonicalize every runner-side path and reject traversal, device files,
  sockets, and symlink escapes outside the workspace.
- [ ] Disable public outbound networking and cloud metadata access. Allow only
  explicitly configured internal Git, package mirror, and artifact endpoints.
- [ ] Use pinned SSH host identities and short-lived, task-scoped credentials;
  never bake long-lived credentials into an image.
- [ ] Install all required toolchains in the remote image, including exactly
  `cargo-teaql` 2.0.8, and validate image capabilities before cloning code.
- [ ] Confirm that the resulting commit exists in the enterprise Git service
  before the remote environment is destroyed.
- [ ] Destroy the environment on success, failure, cancellation, or TTL expiry,
  and retain only approved audit metadata and result identifiers locally.

### System design

```text
                    customer-controlled network

  Local control plane              Enterprise knowledge plane
  +----------------------+         +--------------------------+
  | model orchestration  | <-----> | read-only RAG knowledge  |
  | memory and context   |         | APIs, rules, examples    |
  | Skills and task FSM  |         +--------------------------+
  | audit metadata       |
  +----------+-----------+
             |
             | authenticated SSH / structured runner protocol
             v
  +----------------------+         +--------------------------+
  | disposable execution | ------> | internal Git / artifacts |
  | project files        | commit  | source of durable results|
  | build and test tools | + push  +--------------------------+
  +----------+-----------+
             |
             v
         destroyed
```

- [ ] Introduce one production `RemoteExecutionBackend`; keep any local backend
  available only to isolated tests and explicit developer fixtures.
- [ ] Give the model logical remote paths only. Local filesystem paths must never
  appear in model-visible tool results.
- [ ] Keep RAG access on the control plane and send only bounded, task-relevant
  knowledge excerpts to the model. The disposable machine does not receive
  unrestricted access to the enterprise knowledge base.
- [ ] Define a versioned runner capability handshake covering protocol version,
  workspace root, OS, architecture, installed toolchains, resource limits, and
  network policy.
- [ ] Implement an explicit lifecycle: provision, connect, preflight, clone,
  code, validate, commit, push, confirm, destroy.
- [ ] Make every operation idempotent where practical and attach task,
  environment, image digest, and operation identifiers to every audit event.
- [ ] Support reconnect and cancellation without losing the durable Agent memory
  or accidentally starting the task on a different workspace.
- [ ] Treat the enterprise Git commit SHA as the durable result. stdout, patches,
  and local workspace snapshots are not the primary handoff mechanism.

### Why this design

- Models cannot be relied on to obey prompt-only filesystem boundaries. An Agent
  with a local shell can inspect `/`, `$HOME`, parent directories, or symlinked
  paths even when instructed not to do so.
- A disposable VM makes the machine itself the security boundary. If the Agent
  explores its root filesystem, it sees only a short-lived environment prepared
  for that task.
- Keeping source code and build tools off the control machine reduces the blast
  radius of model-generated commands and makes cleanup deterministic.
- Keeping RAG on the control plane prevents a disposable worker from obtaining
  broad access to enterprise knowledge and allows context to be filtered before
  it reaches the model.
- Prebuilt images avoid shipping multi-gigabyte Rust, Java, Maven, TeaQL, and
  dependency caches with the KlintCode application.
- Commit-and-push before destruction produces an auditable, reproducible result
  that survives the temporary machine.
- A versioned environment contract separates KlintCode from any specific cloud,
  hypervisor, container runtime, or physical distribution medium.

### Future deployment and portable images

KlintCode should consume a capability contract rather than depend on one image
format. The same standard environment may be delivered through several channels:

- [ ] OCI image in an internal registry for Kubernetes or approved container
  platforms.
- [ ] OVA/OVF image for VMware-based enterprise environments.
- [ ] QCOW2 image for KVM and OpenStack, including microVM-based task workers.
- [ ] Cloud marketplace image for customer-owned VPC/VNet deployments.
- [ ] Encrypted ISO, appliance disk, or other physical media for fully isolated
  sites without a connected image registry.
- [ ] Preinstalled physical-machine pool for sites that cannot create VMs on
  demand, with secure wiping and re-imaging after every task.
- [ ] Signed image manifest containing image digest, SBOM, protocol version,
  toolchain versions, supported capabilities, and compatibility range.
- [ ] Offline update bundles containing only changed image layers or packages,
  with signature and checksum verification.
- [ ] Customer-operated provisioner adapters so the same lifecycle can target
  VMware, OpenStack, Kubernetes, a cloud marketplace image, or a physical pool.

The application package should remain small: it contains the control plane,
configuration, Skills, and environment contracts. Compilers, test toolchains,
TeaQL tooling, and dependency caches belong in the separately distributed and
replaceable execution image.

## Benchmark Results (NVIDIA DGX Spark)

30 business objects, full pipeline — from natural language to compilable Rust:

| Step | Duration | Description |
|------|----------|-------------|
| KSML Generation | ~315s | 10 LLM calls, ~5000 tokens |
| Domain Validation | ~1s | TeaQL semantic check |
| Code Generation | ~6s | `cargo teaql` for lib + app |
| Compilation | ~22s | Two `cargo check` passes |
| Assist Discovery | ~18s | 8 entity API signatures |
| Business Logic | ~30s | LLM writes query functions |
| **Total** | **~7 min** | **vs. 1-2 weeks manual** |

## Quick Start

### Prerequisites

The commands below describe the **current developer-mode pipeline**, which still
executes build and validation tools locally. They are not the final isolated
deployment model tracked in the checklist above.

- Local LLM inference endpoint (NIM, vLLM, Ollama, etc.)
- Rust 1.75+ with cargo
- `cargo-teaql` 2.0.8:
  ```bash
  cargo install cargo-teaql --version 2.0.8
  cargo-teaql install-links
  ```

### Build & Run

```bash
git clone https://github.com/teaql/klint-code.git
cd klint-code

# Build
cargo build --release

# Run one backend code-generation task
LOCAL_API_KEY="<key-if-required>" \
  ./target/release/klintcode run \
    --task benchmarks/tasks/school-service-rust \
    --profile profiles/local-qwen.toml \
    --build-target rust-lib-core \
    --output runs/

# Queue tasks, repeat the queue, and continue on each generated workspace
MIMO_API_KEY="<key>" \
  ./target/release/klintcode run \
    --task benchmarks/tasks/school-service-rust \
    --task benchmarks/tasks/simple-greeting \
    --profile profiles/mimo-v2.5-pro.toml \
    --build-target rust-lib-core \
    --repeat 2 \
    --follow-up "Add backend tests for the generated services" \
    --follow-up "Run cargo check and fix remaining warnings" \
    --output runs/

# Interactive TUI
./target/release/klintcode-tui
```

Each repeated `--task` adds a fresh primary run to the queue. `--repeat N`
replays the complete queue, while repeated `--follow-up` values reuse the
workspace and bounded session history created by each primary run. A follow-up
may be literal instruction text or a path to a text file. Queued primary runs
continue after failures by default; add `--fail-fast` to stop at the first
failure. `--skill` applies an explicit modeling skill to every primary task.

### Backend Interface Checks

`health` performs an authenticated `/models` request, parses the response, and
requires the configured model ID to be present. It exits non-zero on transport,
authentication, protocol, or model-selection failures:

```bash
MIMO_API_KEY="<key>" \
  ./target/release/klintcode health \
    --profile profiles/mimo-v2.5-pro.toml
```

`probe` runs explicit live conformance checks. Chat, stream, and tool checks
send small generation requests and may consume provider quota. Use `--json`
for machine-readable output, or select checks with repeated/comma-separated
`--check` values:

```bash
MIMO_API_KEY="<key>" \
  ./target/release/klintcode probe \
    --profile profiles/mimo-v2.5-pro.toml \
    --check models,chat,stream,tools \
    --json
```

Run a benchmark plan with per-case timeouts, token accounting, JSON output,
Markdown output, and a non-zero exit status when any case fails:

```bash
MIMO_API_KEY="<key>" \
  ./target/release/klintcode evaluate \
    --plan benchmarks/rust-build-suite.toml \
    --profile profiles/mimo-v2.5-pro.toml \
    --output runs/evaluation/
```

The TUI opens with its prompt composer focused. Type a task and press `Enter`
to submit it through the normal Pipeline. Use `Shift+Enter` or `Alt+Enter` for
a new line. The composer grows from one to eight visible lines; additional
lines remain editable while the viewport follows the latest eight. Use `Esc`
for dashboard shortcuts and `i` to return to the composer.
The default surface stays minimal; enter `/stats` for Plan, Validation,
Context, and Token details, then `/main` to return.

### Dynamic Plan and Tool Probe

Use the persistent protocol probe to test whether an OpenAI-compatible model
can generate a task-specific plan, publish explicit plan status transitions,
call constrained tools, edit an isolated Rust fixture, and pass its tests:

```bash
MIMO_API_KEY="<key>" \
FLINTCODE_ENDPOINT="https://example.com/v1" \
FLINTCODE_MODEL="model-id" \
node scripts/model-agent-probe.mjs
```

The probe never edits the repository. It writes a detailed `report.json` to
its temporary workspace.

### Bounded Skill Composition Probe

Test whether MiMo can turn one immutable task goal and a bounded candidate
skill set into a dependency-safe execution plan:

```bash
MIMO_API_KEY="<key>" \
FLINTCODE_MODEL="mimo-v2.5-pro" \
node scripts/mimo-skill-planner-probe.mjs \
  --output /tmp/mimo-skill-plan-report.json
```

The probe includes phase-incompatible and unauthorized distractor skills. It
checks goal preservation, skill identity, phase and permission constraints,
input/output bindings, DAG validity, and acceptance-criterion coverage. Use
`--dry-run` to inspect the static test payload without contacting a model.

Add `--with-tools` to run the hierarchical DAG test. After the model creates
the Skill TaskGraph, a second bounded request expands every selected Skill into
a Tool ExecutionGraph. The local validator checks Skill-to-Tool allowlists,
permissions, bindings, exports, traceability, cycles, and the merged topological
execution waves:

```bash
MIMO_API_KEY="<key>" \
node scripts/mimo-skill-planner-probe.mjs \
  --with-tools \
  --output /tmp/mimo-hierarchical-dag-report.json
```

### Configuration

Profiles are stored in `profiles/`. Example:

```toml
[model]
name = "nemotron-3-super"
endpoint = "http://localhost:8000/v1"
max_context = 65536
temperature = 0.1
```

## Project Structure

```
klintcode/
├── apps/
│   ├── klintcode-cli/        # Headless CLI for batch evaluation
│   └── klintcode-tui/        # Interactive TUI (ratatui)
├── crates/
│   ├── agent-core/           # State machine, reducer, event loop
│   ├── pipeline/             # Evaluation suite runner, build validation
│   ├── model-vllm/           # LLM client (OpenAI-compatible)
│   ├── validation/           # Multi-level validation engine
│   ├── context-builder/      # Prompt construction, token budgeting
│   ├── artifact-store/       # Run output and artifact management
│   ├── tool-runner/          # External tool execution
│   └── workspace-guard/      # Workspace isolation
├── benchmarks/
│   ├── tasks/                # Test cases (school-service, moving-company, etc.)
│   ├── rust-build-suite.toml # Quick validation suite
│   └── rust-full-30obj-bench.toml  # Full 30-object benchmark
└── profiles/                 # Model/hardware configuration
```

## Validation Pipeline

KlintCode's multi-level validation is what makes small models reliable:

| Level | What | How |
|-------|------|-----|
| **L1** | XML well-formedness | Local parser |
| **L2** | Schema conformance | Structure check |
| **L3** | Domain semantics | `cargo teaql evaluate` (real TeaQL rules) |
| **L4** | Code generation | `cargo teaql rust-lib-core` |
| **L5** | Compilation | `cargo check` (Rust compiler) |
| **L6** | API discovery | `cargo teaql assist` (real API signatures) |
| **L7** | Business logic | LLM + assist output → query functions |
| **L8** | Full compilation | `cargo check` on complete workspace |

If any level fails, the pipeline triggers an automatic **repair loop** — the LLM receives the specific error and regenerates.

## Target Hardware

KlintCode is hardware-agnostic but optimized for local inference:

| Device | Model Size | Speed | Context |
|--------|-----------|-------|---------|
| **DGX Spark** | ≤ 70B (quantized) | ~16 tok/s | 64K |
| **DGX Station** | ≤ 1T | ~150+ tok/s | 128K+ |
| **Any GPU server** | Varies | Varies | Varies |

## TeaQL Integration

KlintCode follows the [TeaQL Agent Kit](https://github.com/teaql/teaql-agent-kit) rules:

- Never guess method names — use `assist` output
- Never edit generated files in `rust-lib-core/`
- Every query: `.purpose("why")` and `.comment("what")`
- Every save: `.audit_as("description")`
- Required: `cargo-teaql` 2.0.8

## License

Licensed under either of

- [MIT License](LICENSE-MIT)
- [Apache License, Version 2.0](LICENSE-APACHE)

at your option.

## Related

- [TeaQL](https://teaql.io) — Deterministic execution for non-deterministic AI
- [teaql-agent-kit](https://github.com/teaql/teaql-agent-kit) — Evaluation framework for AI coding agents
- [ratatui](https://ratatui.rs) — Rust terminal UI framework
