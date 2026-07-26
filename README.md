# TeaQL DGX Spark Agent

⚡ A TUI-based coding agent optimized for NVIDIA DGX Spark, built with Rust
and [ratatui](https://ratatui.rs). Designed for autonomous TeaQL business
software tasks within a **64K token context budget**.

## Overview

The TeaQL DGX Spark Agent is a terminal-native coding assistant that runs
locally on NVIDIA DGX Spark hardware. It communicates with local LLM
inference endpoints (NIM) and manages context carefully to work within the
smaller model context windows available on DGX Spark.

### Key Features

- **64K Context Budget Management** — Automatic tracking, compaction, and
  warning when approaching context limits
- **Ratatui TUI** — Full terminal interface with file tree, chat, token
  gauge, and vim-like keybindings
- **TeaQL Integration** — Built-in support for `cargo-teaql` 2.0.8 model
  evaluation, code generation, and object-specific assist
- **Streaming LLM** — OpenAI-compatible API streaming with token-by-token
  display
- **Context Compaction** — Automatic summarization of older conversation
  turns to stay within budget
- **Autonomous Mode** — Follows
  [teaql-agent-kit autonomous branch](https://github.com/teaql/teaql-agent-kit/tree/autonomous)
  evaluation rules

## Architecture

```
┌─────────────────────────────────────────────────────────┐
│                  TeaQL DGX Spark Agent                   │
├─────────────┬───────────────────────┬───────────────────┤
│  File Tree  │       Chat Panel      │   Token Gauge     │
│  (25%)      │       (75%)           │   [████░░░] 62%   │
│             │                       │                   │
│  📂 src/    │  👤 You: ...          │   48K budget      │
│  🦀 main.rs │  🤖 Agent: ...       │   8K reserved     │
│  📄 model.. │                       │   8K output       │
│             │                       │                   │
├─────────────┴───────────────────────┴───────────────────┤
│  [INSERT] ⏳ Thinking... │ Session: abc12...            │
├─────────────────────────────────────────────────────────┤
│  > Type your message here...                             │
└─────────────────────────────────────────────────────────┘
```

## Prerequisites

- **NVIDIA DGX Spark** with NIM (NVIDIA Inference Microservice) running
- **Rust** 1.75+ with cargo
- **cargo-teaql** exactly `2.0.8`:
  ```bash
  cargo install cargo-teaql --version 2.0.8
  cargo-teaql install-links
  ```

## Installation

```bash
# Clone the repository
git clone https://github.com/teaql/teaql-dgx-spark-agent.git
cd teaql-dgx-spark-agent

# Build (optimized for DGX Spark)
cargo build --release

# Run
./target/release/teaql-dgx-spark-agent
```

## Configuration

Configuration is stored at `~/.config/teaql-dgx-spark-agent/config.toml`:

```toml
# Context window management (DGX Spark optimized)
max_context_window = 64000
reserved_tokens = 8000
max_output_tokens = 8000
context_budget = 48000

# Local LLM endpoint (NIM on DGX Spark)
llm_endpoint = "http://localhost:8000/v1"
model_name = "meta/llama-3.1-70b-instruct"
temperature = 0.1

# Workspace
workspace_root = "."

# TeaQL
cargo_teaql_version = "2.0.8"

# UI
show_token_usage = true
auto_compact = true
```

## Keybindings

### Normal Mode
| Key       | Action              |
|-----------|---------------------|
| `i`       | Enter insert mode   |
| `/`       | Start command       |
| `?`       | Toggle help         |
| `Tab`     | Cycle panels        |
| `j`/`k`   | Scroll chat         |
| `g`/`G`   | Top/bottom of chat  |
| `r`       | Refresh file tree   |
| `Ctrl+C`  | Quit                |

### Insert Mode
| Key         | Action            |
|-------------|-------------------|
| `Enter`     | Send message      |
| `Shift+Enter` | New line        |
| `Esc`       | Normal mode       |
| `↑`/`↓`    | History nav       |

### Commands
| Command       | Description                |
|---------------|----------------------------|
| `/help`       | Toggle help overlay        |
| `/clear`      | Clear conversation         |
| `/compact`    | Force context compaction   |
| `/tokens`     | Show token usage details   |
| `/model <n>`  | Switch model               |
| `/quit`       | Exit                       |

## Context Budget Strategy

The DGX Spark's models have a 64K token context limit. The agent manages
this with a three-tier strategy:

1. **Budget Allocation**: 48K usable tokens (8K reserved for system prompt,
   8K for output)
2. **Auto-Compact at 85%**: When context reaches ~41K tokens, older messages
   are summarized automatically
3. **Critical Warning at 95%**: Visual alert when approaching the hard limit

## TeaQL Agent Kit Integration

This agent follows the rules from the
[TeaQL Agent Kit autonomous branch](https://github.com/teaql/teaql-agent-kit/tree/autonomous):

- Never guess method names — use generated `AGENTS.md` and assist output
- Never edit generated files
- Every query: `.purpose("why")` and `.comment("what")`
- Every save: `.audit_as("description")`
- Use `cargo teaql --input <model>` for all operations
- Required: `cargo-teaql` exactly `2.0.8`

## Project Structure

```
teaql-dgx-spark-agent/
├── Cargo.toml              # Dependencies (ratatui, tokio, etc.)
├── README.md               # This file
├── AGENTS.md               # Rules for AI agents
├── LICENSE                 # MIT License
└── src/
    ├── main.rs             # Entry point, logging setup
    ├── app.rs              # Application state & event handling
    ├── config.rs           # Configuration & context budget
    ├── context.rs          # Context window management
    ├── llm.rs              # LLM client (NIM/OpenAI-compatible)
    ├── tui.rs              # Ratatui TUI rendering
    ├── workspace.rs        # File tree & workspace state
    ├── agent.rs            # Tool execution & orchestration
    └── teaql.rs            # TeaQL-specific operations
```

## License

MIT

## Related

- [teaql-agent-kit](https://github.com/teaql/teaql-agent-kit) — Evaluation
  framework for AI coding agents on TeaQL
- [TeaQL](https://teaql.io) — Deterministic execution for non-deterministic AI
- [ratatui](https://ratatui.rs) — Rust terminal UI framework
- [NVIDIA DGX Spark](https://www.nvidia.com/en-us/data-center/dgx-spark/) —
  AI supercomputer for desktop
