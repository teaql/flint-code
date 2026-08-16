Continue in the existing generated Rust application workspace. Do not
regenerate or change `model/main.xml`, and never read, search, or modify any
generated library source under `lib/src`.

Read the workspace `AGENTS.md`, the validated model, and the complete cached
assist responses. If the exact creation, setter, query, or save syntax is not
already present, run the relevant `cargo teaql --input model/main.xml
rust-assist-*` command. Copy the returned syntax literally; do not guess TeaQL
methods.

Implement a focused school-registration workflow in
`src/school_workflow.rs`, wired into the compiled application:

- Register school code `TST-001` with name `Klint Synthetic Test School`,
  address `1 Test Campus Way`, and phone `0000-00000001`.
- Make the operation idempotent for acceptance retries: an existing
  `TST-001` must not be duplicated. If it already exists, normalize its name,
  address, and phone to the requested registration values and persist that
  change with the same registration audit before verification. This lets the
  cumulative final runtime safely establish a baseline before changing it.
- Every Q execution must include both `purpose()` and `comment()` and must
  propagate errors with `.await?`.
- Use whichever exact query terminal the TeaQL assist output demonstrates;
  `execute_for_one`, `execute_for_list`, and `execute` are all acceptable when
  used with the required metadata and direct error propagation.
- Every save or update must include `audit_as("Register school TST-001")`.
- Re-query the SQLite-backed store after the save/update and verify the
  persisted registered values. In-memory-only checks do not count. Only after
  the live query succeeds and the values match, print the exact standalone line
  `KLINTCODE_SCHOOL_REGISTRATION_OK` from the same function scope as that Q
  execution.
- Never print the success marker unconditionally, from a test, or after
  converting an error into a successful note.
- Add at least one focused test for application-owned registration behavior.
  Every verification query in production code or tests must explicitly select
  each field that the following checks read; unselected TeaQL fields retain
  defaults and are not valid persistence evidence.
  If a test opens SQLite, it must obtain its database URL from the explicit
  `SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` environment variable; do not
  silently substitute an in-memory or hard-coded database. The runner gives
  this task execution an isolated SQLite database that is not shared with
  other agents. Application tests must only read the injected variable; they
  must not set, remove, or otherwise mutate the process environment.
- `cargo run --quiet` must execute this deterministic runtime self-test against
  the database selected by `SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` and
  exit non-zero on any failed query, save/update, or persisted-value check.

Do not add an application-level shared test lock: database isolation belongs
to the runner, not to the generated business application. Run the exact
commands `cargo test --quiet -- --test-threads=1` and `cargo run --quiet`; the
typed contract injects the runner-owned
`SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` into those child processes and
repeats the serial test command five times to verify idempotence. Do not prefix
either command with an inline shell assignment. Keep all editable business
logic outside generated library source.
