# FlintCode

> *Strike code without the cloud.* — 燧石取火，离线生码。

**FlintCode** is an AI coding agent designed for **air-gapped and compliance-restricted environments**. It runs entirely on local hardware (NVIDIA DGX Spark / DGX Station), requires no internet connection, and produces **compiler-verified** production code.

Built with Rust. Powered by [TeaQL](https://teaql.io).

## Why FlintCode?

Every major AI coding tool today — Cursor, Copilot, Devin, Windsurf — requires cloud connectivity. For organizations bound by regulatory, security, or data sovereignty constraints, **none of them are usable**.

FlintCode fills this gap:

| | Cloud Coding Agents | FlintCode |
|--|---------------------|-----------|
| **Network** | Requires internet | Air-gapped / offline |
| **Data privacy** | Code sent to cloud | Data never leaves premises |
| **Model size** | 100B+ parameters | Works with small local models |
| **Correctness** | Hope it compiles | **Compiler-verified** |
| **Compliance** | ❌ GDPR / HIPAA / 等保 | ✅ Fully compliant |

## How It Works

FlintCode doesn't try to be a general-purpose coding agent. Instead, it combines a **small local model** with a **deterministic validation pipeline** to guarantee correct output:

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

- Local LLM inference endpoint (NIM, vLLM, Ollama, etc.)
- Rust 1.75+ with cargo
- `cargo-teaql` 2.0.8:
  ```bash
  cargo install cargo-teaql --version 2.0.8
  cargo-teaql install-links
  ```

### Build & Run

```bash
git clone https://github.com/teaql/flint-code.git
cd flint-code

# Build
cargo build --release

# Run evaluation suite
FLINTCODE_BASE_URL="http://localhost:8000" \
  ./target/release/flintcode evaluate \
    --plan benchmarks/rust-build-suite.toml \
    --output runs/

# Interactive TUI
./target/release/flintcode-tui
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
flintcode/
├── apps/
│   ├── flintcode-cli/        # Headless CLI for batch evaluation
│   └── flintcode-tui/        # Interactive TUI (ratatui)
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

FlintCode's multi-level validation is what makes small models reliable:

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

FlintCode is hardware-agnostic but optimized for local inference:

| Device | Model Size | Speed | Context |
|--------|-----------|-------|---------|
| **DGX Spark** | ≤ 70B (quantized) | ~16 tok/s | 64K |
| **DGX Station** | ≤ 1T | ~150+ tok/s | 128K+ |
| **Any GPU server** | Varies | Varies | Varies |

## TeaQL Integration

FlintCode follows the [TeaQL Agent Kit](https://github.com/teaql/teaql-agent-kit) rules:

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
