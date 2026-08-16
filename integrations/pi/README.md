# FlintCode Pi integration

This extension keeps Pi's interaction loop and tool UI while routing project
file access and project commands through FlintCode's existing SSH runner.
FlintCode remains authoritative for path policy, command policy, the durable
remote workspace, and verification.

## Build the bridge

```bash
cargo build -p flintcode-agent-bridge
```

The bridge only attaches to an existing runner session. It never silently
creates a replacement session and it has no local project-execution fallback.

## Start Pi

Set these values from the final remote-workspace locator produced by a
FlintCode run:

```bash
export FLINTCODE_AGENT_BRIDGE_BIN="$PWD/target/debug/flintcode-agent-bridge"
export FLINTCODE_EXECUTION_CONFIG="$PWD/config/remote-execution.toml"
export FLINTCODE_EXECUTION_TARGET="ca-mini"
export FLINTCODE_RUNNER_SESSION_ID="<runner-session-id>"
export FLINTCODE_REMOTE_CWD="attempt-01/build"

pi --no-builtin-tools \
  --no-extensions \
  --no-context-files \
  --no-skills \
  --no-prompt-templates \
  --offline \
  -e "$PWD/integrations/pi/extension/index.ts"
```

`--no-builtin-tools` is mandatory: it ensures Pi cannot fall back to local
read/write/edit/bash tools. `--no-extensions` prevents unrelated installed
extensions from adding another execution path; the explicit `-e` extension is
still loaded by Pi. Local context files and discovered skills are disabled so
they cannot describe a different local workspace. The extension injects the
remote workspace's bounded `AGENTS.md` into Pi's system prompt instead; audited
skills can still be supplied explicitly later.

The child bridge receives only a small environment allowlist. API keys are not
forwarded. `SSH_AUTH_SOCK` is also excluded unless
`FLINTCODE_ALLOW_SSH_AUTH_SOCK=1` is explicitly set.

## Execution semantics

- `grep` performs a bounded literal search on the runner; it never launches a
  local `rg` process and generated library source is excluded.
- `find` walks the bounded remote workspace view and applies the requested glob
  in the extension; it never launches local `fd`.
- `bash` is a compatibility name only. Commands are parsed into an allowlisted
  program plus argv, started without a shell, and streamed through cursor-based
  runner output pages.
- Pi's `AbortSignal` sends `exec.cancel` and waits for the runner to persist a
  terminal operation state after stopping the remote process group.

## Remaining boundary

- Use a dedicated disposable remote account/container. The SSH runner policy
  is not a substitute for OS isolation when compiling untrusted code.
