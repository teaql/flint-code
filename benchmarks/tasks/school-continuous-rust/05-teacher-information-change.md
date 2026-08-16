Continue in the same Rust application workspace and preserve every verified
workflow from the preceding school registration, school information change,
teacher registration, and student enrollment tasks. Do not regenerate or
change `model/main.xml`, and never read, search, or modify generated library
source under `lib/src`.

Use only exact syntax from the workspace `AGENTS.md` and cached or freshly
requested `cargo teaql --input model/main.xml rust-assist-*` output. Copy the
assist syntax literally; do not guess TeaQL constructors, setters, query
methods, or save/update terminals.

Keep the application split into focused modules so no growing workflow file is
truncated by the coding tools: school behavior stays in
`src/school_workflow.rs`, teacher behavior stays in
`src/teacher_workflow.rs`, and student behavior stays in
`src/student_workflow.rs`. Extend `src/teacher_workflow.rs` with a public
`change_teacher_information` workflow, and update `src/main.rs` to invoke it
after the first four workflows. Do not copy the existing school or student
implementations into the teacher module.

Implement the separate teacher-information change as follows:

- Locate exactly one previously registered teacher whose email is
  `teacher@example.invalid` and who belongs to school `TST-001`. Fail if the
  school or teacher is missing or ambiguous; do not create a replacement.
- Change the teacher's name to `Updated Test Teacher` and subject area to
  `Computer Science`. Keep the email address exactly
  `teacher@example.invalid`; the earlier idempotent registration uses that
  synthetic email as its stable lookup key on acceptance retries.
- Preserve the teacher's existing school relationship. Do not create a second
  teacher or school.
- Every Q execution must include both `purpose()` and `comment()` and must
  propagate errors with `.await?`.
- Use whichever exact query terminal the TeaQL assist output demonstrates;
  `execute_for_one`, `execute_for_list`, and `execute` are all acceptable when
  used with the required metadata and direct error propagation.
- Persist the change with
  `audit_as("Change teacher teacher@example.invalid information")`
  immediately before the exact save or update terminal shown by assist.
- Re-query the SQLite-backed store after the update and verify the persisted
  name and subject area, and that the email address remains exactly
  `teacher@example.invalid`. Every verification query must
  explicitly select every field its checks read; unselected TeaQL fields retain
  defaults and are not persistence evidence.
- Only after the live Q execution succeeds and all persisted values match,
  print the exact standalone line
  `KLINTCODE_TEACHER_INFORMATION_UPDATED` from the same function scope as that
  Q execution. Never print the marker unconditionally, from a test, or after
  converting an error into a successful note.
- Add a fifth focused application-owned test for teacher information change.
  If a test opens SQLite, it must obtain its database URL from the explicit
  `SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` environment variable; do not
  silently substitute an in-memory or hard-coded database. Keep database tests
  deterministic. The runner gives this task execution an isolated SQLite
  database that is not shared with other agents. Tests must only read the
  injected URL and must not set, remove, or otherwise mutate the process
  environment.

The final runtime must invoke all five workflows in order against the same
database: idempotent school registration, school information change,
idempotent teacher registration, idempotent student enrollment, and then this
teacher information change. It must preserve and emit these exact earlier
markers from their existing verified query scopes:

1. `KLINTCODE_SCHOOL_REGISTRATION_OK`
2. `KLINTCODE_SCHOOL_INFORMATION_UPDATED`
3. `KLINTCODE_TEACHER_REGISTRATION_OK`
4. `KLINTCODE_STUDENT_ENROLLMENT_OK`

Any query, save/update, relationship, ambiguity, missing-record, or persisted-
value check failure must make the runtime exit non-zero. The earlier workflows
must remain idempotent so acceptance retries can safely re-establish their
baseline values before applying this change.

Do not introduce or depend on an application-level shared test lock. Run the
exact commands `cargo test --quiet -- --test-threads=1` and
`cargo run --quiet`; the typed contract injects the runner-owned
`SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` into those child processes and
repeats the serial test command five times to verify idempotence. Do not prefix
either command with an inline shell assignment. Yield only after both commands
pass.
