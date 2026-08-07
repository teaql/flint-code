#!/usr/bin/env bash
# Launch KlintCode CLI (headless batch runner)
#
# Usage:
#   ./start-cli.sh run --task benchmarks/tasks/xxx --profile profiles/qwen-3.6-coder.toml
#   ./start-cli.sh health --profile profiles/local-qwen.toml
#
# Subcommands:
#   run      Run a task through the full pipeline
#            --task <path>          Task directory or inline text (required)
#            --profile <path>      Model profile TOML (default: profiles/default.toml)
#            --build-target <name> Build target: rust-lib-core | java-spring-boot-lib-core
#   health   Check model service connectivity
#            --profile <path>      Model profile TOML (default: profiles/default.toml)

set -euo pipefail
cd "$(dirname "$0")"

cargo run --release -p klintcode-cli -- "$@"
