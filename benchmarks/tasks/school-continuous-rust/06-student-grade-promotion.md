Continue in the same Rust application workspace and preserve all five verified
workflows from the preceding tasks. Do not regenerate or change
`model/main.xml`, and never read, search, or modify generated library source
under `lib/src`.

Use only exact syntax from the workspace `AGENTS.md` and cached or freshly
requested `cargo teaql --input model/main.xml rust-assist-*` output. Copy the
assist syntax literally; do not guess TeaQL constructors, setters, query
methods, or save/update terminals.

Keep the focused module split from the previous task: school behavior stays in
`src/school_workflow.rs`, teacher behavior stays in
`src/teacher_workflow.rs`, and student behavior stays in
`src/student_workflow.rs`. Extend `src/student_workflow.rs` with a public
`promote_student_to_grade_7` workflow, and update `src/main.rs` to invoke it
after the preceding five workflows. Do not copy the existing school or teacher
implementations into the student module.

Implement the separate student grade-promotion workflow as follows:

- Locate exactly one previously enrolled `Test Student` who belongs to school
  `TST-001` and has the persisted grade level `6`. Fail if the school or
  student is missing or ambiguous; do not create a replacement.
- Change only the student's grade level to integer `7`. Preserve the student
  name, enrollment date `2026-09-01`, and existing school relationship. Do not
  create a second student or school.
- Every Q execution must include both `purpose()` and `comment()` and must
  propagate errors with `.await?`.
- Use whichever exact query terminal the TeaQL assist output demonstrates;
  `execute_for_one`, `execute_for_list`, and `execute` are all acceptable when
  used with the required metadata and direct error propagation.
- Persist the change with
  `audit_as("Promote student Test Student to grade 7")` immediately before the
  exact save or update terminal shown by assist.
- Re-query the SQLite-backed store after the update and verify the persisted
  grade level is integer `7`, while the student name and enrollment date remain
  unchanged. Every verification query must explicitly select every field its
  checks read; unselected TeaQL fields retain defaults and are not persistence
  evidence.
- Only after the live Q execution succeeds and all persisted values match,
  print the exact standalone line
  `KLINTCODE_STUDENT_PROMOTED_TO_GRADE_7` from the same function scope as that
  Q execution. Never print the marker unconditionally, from a test, or after
  converting an error into a successful note.
- Add a sixth focused application-owned test for student grade promotion. If a
  test opens SQLite, it must obtain its database URL from the explicit
  `SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` environment variable; do not
  silently substitute an in-memory or hard-coded database. Keep database tests
  deterministic. The runner gives this task execution an isolated SQLite
  database that is not shared with other agents. Tests must only read the
  injected URL and must not set, remove, or otherwise mutate the process
  environment.

The final runtime must invoke all six workflows in order against the same
database: idempotent school registration, school information change,
idempotent teacher registration, idempotent student enrollment, teacher
information change, and then this student grade promotion. Preserve and emit
the five earlier exact runtime markers from their existing verified Q scopes,
followed by `KLINTCODE_STUDENT_PROMOTED_TO_GRADE_7` from the new verification
scope. Teacher information change must preserve its stable synthetic email
`teacher@example.invalid`, and idempotent student enrollment must first
re-establish grade level `6` during retries before this workflow promotes it to
`7`.

Any query, save/update, relationship, ambiguity, missing-record, or persisted-
value check failure must make the runtime exit non-zero. Do not suppress an
error to make a marker appear.

Do not introduce or depend on an application-level shared test lock. Run the
exact commands `cargo test --quiet -- --test-threads=1` and
`cargo run --quiet`; the typed contract injects the runner-owned
`SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` into those child processes and
repeats the serial test command five times to verify idempotence. Do not prefix
either command with an inline shell assignment. Yield only after both commands
pass.
