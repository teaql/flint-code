#!/usr/bin/env bash
# Launch KlintCode TUI (interactive terminal interface)
#
# Usage:
#   ./start-tui.sh                          # default profile
#   ./start-tui.sh -p profiles/qwen-3.6-coder.toml
#   ./start-tui.sh -t benchmarks/tasks/xxx  # auto-start a task
#   FLINTCODE_PROFILE=profiles/xxx.toml ./start-tui.sh

set -euo pipefail
cd "$(dirname "$0")"

cargo run --release -p klintcode-tui -- "$@"
