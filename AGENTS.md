# AGENTS.md — Rules for AI Agents Working on This Repository

## READ THIS BEFORE CODING

This is **FlintCode**, a Rust-based AI coding agent for air-gapped environments.
It uses ratatui for TUI and targets local inference hardware with constrained
context windows (64K-128K tokens).

### Core Rules

1. **Context budget awareness**: Local models have limited context (e.g. 64K).
   All system prompts, conversation history, and tool outputs must fit within
   48K usable tokens (8K reserved, 8K for output).

2. **Never guess method names**: Use the generated local `AGENTS.md` and
   object-specific assist output before writing TeaQL business code.

3. **Never edit generated files**: Do not manually modify files under
   `rust-lib-core/`, `java-lib-core/`, `java-web-spring-boot/`, or `bizcore/`.

4. **Query constraints**: Every query using `execute_for_list()` or `execute()`
   must be preceded by `.purpose("why")` and `.comment("what")`.

5. **Save constraints**: Every save using `.save()` or `.update()` must be
   preceded by `.audit_as("description")`.

6. **Use cargo teaql with --input**: Every Rust TeaQL operation must use
   `cargo teaql --input <model> <command>`.

7. **Version requirement**: `cargo-teaql` exactly `2.0.8`.

8. **Diagnosing Errors**: When running commands (like `cargo run`, `mvn build`, etc.) and checking for errors, NEVER use strict case-sensitive tools like `grep "Error"`. Compilers and tools often output `error:`, `ERROR:`, `Exception`, or `Failed`. Instead, use case-insensitive searches (e.g. `grep -i "error"`) or pipe the entire output to a file and read it. If a command fails and your search returns nothing, stop blindly retrying with larger context bounds; dump the raw output instead.

9. **Anti-Hallucination (Strict Copy)**: When implementing TeaQL logic based on `rust-assist-query`, `rust-assist-create`, or `AGENTS.md`, you MUST rigidly copy the syntax shown in the example code (e.g. `let mut entity = Q::xxx().new_entity(ctx)`). Do NOT invent or hallucinate standard Builder patterns (like `E::new_xxx()`) based on your prior knowledge of other frameworks. Trust the generated snippet completely and literally.

### Rust Code Style

- Use `anyhow::Result` for error propagation in application code.
- Use `thiserror` for library-level error types.
- Follow standard Rust formatting (`cargo fmt`).
- Add doc comments for public items.
- Keep modules focused — each file handles one concern.

### Project Layout

- `apps/flintcode-cli/` — Headless CLI for batch evaluation
- `apps/flintcode-tui/` — Interactive TUI (ratatui)
- `crates/agent-core/` — State machine (reducer.rs), events, run controller
- `crates/pipeline/` — Evaluation suite runner (suite.rs), build validation (executor.rs)
- `crates/model-vllm/` — LLM client (OpenAI-compatible API)
- `crates/validation/` — Multi-level validation engine
- `crates/context-builder/` — Prompt construction and token budgeting
- `crates/artifact-store/` — Run output and artifact management

### Testing

- Unit tests go in the same file as the code they test.
- Integration tests go in `tests/`.
- Benchmark suites in `benchmarks/` (TOML format).
- Run tests with `cargo test`.

### TeaQL Integration

- Build validation pipeline in `crates/pipeline/src/executor.rs`.
- State machine transitions in `crates/agent-core/src/reducer.rs`.
- Follow the autonomous branch evaluation rules from
  [teaql-agent-kit](https://github.com/teaql/teaql-agent-kit/tree/autonomous).
