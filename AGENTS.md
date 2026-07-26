# AGENTS.md — Rules for AI Agents Working on This Repository

## READ THIS BEFORE CODING

This is a Rust TUI project using ratatui. It targets NVIDIA DGX Spark devices
with a 64K token context window limit.

### Core Rules

1. **Context budget awareness**: The DGX Spark models have 64K max context.
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

### Rust Code Style

- Use `anyhow::Result` for error propagation in application code.
- Use `thiserror` for library-level error types.
- Follow standard Rust formatting (`cargo fmt`).
- Add doc comments for public items.
- Keep modules focused — each file handles one concern.

### TUI Development

- All rendering goes through `src/tui.rs` using ratatui widgets.
- Application state is in `src/app.rs`.
- Context management (token counting, compaction) in `src/context.rs`.
- LLM communication in `src/llm.rs`.
- File/workspace state in `src/workspace.rs`.

### Testing

- Unit tests go in the same file as the code they test.
- Integration tests go in `tests/`.
- Run tests with `cargo test`.

### TeaQL Integration

- TeaQL operations are in `src/teaql.rs`.
- Agent tool execution is in `src/agent.rs`.
- Follow the autonomous branch evaluation rules from
  [teaql-agent-kit](https://github.com/teaql/teaql-agent-kit/tree/autonomous).
