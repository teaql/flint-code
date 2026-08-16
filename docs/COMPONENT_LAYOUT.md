# FlintCode component layout

FlintCode is the TeaQL ecosystem's secure harness for coding Agents. Agent UX
and reasoning are replaceable; Runner policy and deterministic verification are
the trusted boundary.

## Top-level ownership

- `runner/` owns the remote protocol, SSH transport, bootstrap, durable session,
  filesystem primitives, process lifecycle, and server-side policy enforcement.
- `crates/execution-policy/` owns Agent-neutral command and workspace rules used
  by both the native pipeline and external Agent bridges.
- `crates/agent-bridge-core/` owns the narrow NDJSON tool bridge. It accepts
  Agent tool requests and dispatches only approved operations to a Runner
  session.
- `apps/flintcode-agent-bridge/` is the standalone bridge process embedded or
  launched by Agent adapters.
- `integrations/<agent>/` contains thin, Agent-specific adapters. An adapter may
  translate tool schemas and lifecycle events, but it must not implement its own
  filesystem, shell, SSH, or policy bypass.
- `crates/pipeline/` owns TeaQL generation, validation, typed acceptance,
  evidence, and evaluation orchestration.
- `apps/flintcode-tui-legacy/` is the maintenance-only historical TUI. It is not
  the primary UX for external Agents.

## Dependency direction

```text
Agent UX (Pi / another Agent)
            |
            v
thin adapter in integrations/<agent>
            |
            v
flintcode-agent-bridge -> agent-bridge-core -> execution-policy
            |                                      |
            +------------------+-------------------+
                               v
                             runner
                               |
                               v
                    isolated remote workspace
```

The pipeline may use the same Runner directly. Neither adapters nor the legacy
TUI are security authorities. A Runner rejection or infrastructure failure is
final and must never cause local execution fallback.

## Naming

Public extensions use the `@teaql/flintcode-*` namespace. `klintcode-*` binary
names that remain are compatibility surfaces and are not names for new modules.
