Continue in the same Rust application workspace and preserve the verified
school-registration behavior from the previous task. Do not regenerate or
change `model/main.xml`, and never read, search, or modify generated library
source under `lib/src`.

Use only exact syntax from the workspace `AGENTS.md` and cached or freshly
requested `cargo teaql --input model/main.xml rust-assist-*` output.

Extend `src/school_workflow.rs` with a separate school-information change:

- Locate the previously registered `TST-001`; fail if it is missing or
  ambiguous rather than silently creating a replacement.
- Change its name to `Klint Synthetic Future School`, address to
  `2 Test Campus Way`, and phone to `0000-00000002`.
- Every Q execution must include both `purpose()` and `comment()` and must
  propagate errors with `.await?`.
- Use whichever exact query terminal the TeaQL assist output demonstrates;
  `execute_for_one`, `execute_for_list`, and `execute` are all acceptable when
  used with the required metadata and direct error propagation.
- Persist the change with `audit_as("Change school TST-001 information")`
  immediately before the exact save or update terminal shown by assist.
- Re-query the SQLite-backed store after the update and verify all three new
  persisted values. In-memory-only checks do not count. Only after that live Q
  execution succeeds and the values match, print the exact
  standalone line `KLINTCODE_SCHOOL_INFORMATION_UPDATED` from the same
  function scope.
- Preserve the registration marker and workflow. The final runtime must first
  invoke the idempotent registration workflow against the same database to
  establish the original `Klint Synthetic Test School`, `1 Test Campus Way`,
  and `0000-00000001` baseline, printing
  `KLINTCODE_SCHOOL_REGISTRATION_OK`, and then perform and verify the change.
- Markers may not be printed unconditionally or from tests. Any query, save,
  update, missing-school, ambiguity, or value-check failure must make the
  runtime exit non-zero.
- Add a second focused application-owned test for the information-change
  behavior. If either focused test opens SQLite, it must obtain its database
  URL from the explicit `SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL`
  environment variable; do not silently substitute an in-memory or hard-coded
  database. Every verification query must explicitly select every field its
  checks read; unselected TeaQL fields are not persistence evidence. The
  runner gives this task execution an isolated SQLite database that is not
  shared with other agents. Tests must only read the injected URL and must not
  mutate the process environment.

Do not introduce or depend on an application-level shared test lock. Run the
exact commands `cargo test --quiet -- --test-threads=1` and
`cargo run --quiet`; the typed contract injects the runner-owned
`SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` into those child processes and
repeats the serial test command five times to verify idempotence. Do not prefix
either command with an inline shell assignment. Yield only after both commands
pass.
