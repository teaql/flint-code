# Agent integrations

Each directory contains a thin adapter from one Agent's tool and lifecycle API
to `flintcode-agent-bridge`. Adapters do not receive SSH credentials and do not
execute project commands locally.

Current integration:

- `pi/extension/` — package `@teaql/flintcode-pi`

New integrations should reuse the generic bridge protocol rather than fork the
Runner or the policy implementation.
