Continue in the same Rust application workspace and preserve the verified
school-registration, school-information-change, and teacher-registration
workflows from the previous tasks. Do not regenerate or change
`model/main.xml`, and never read, search, or modify generated library source
under `lib/src`.

Read the workspace `AGENTS.md` and the complete cached TeaQL assist responses.
If the exact student creation, school relationship, setter, query, selection,
or save syntax is not already present, run the relevant `cargo teaql --input
model/main.xml rust-assist-*` command. Copy the returned syntax literally; do
not infer TeaQL APIs from other frameworks.

Create a focused `src/student_workflow.rs` module for the idempotent
student-enrollment workflow, declare it from the existing crate, and wire it
into `src/main.rs`. Keep school logic in `src/school_workflow.rs` and teacher
logic in `src/teacher_workflow.rs`; do not duplicate or combine those modules:

- Name the production entry point `enroll_student` and call that entry point
  from `src/main.rs` after `register_teacher` succeeds.

- In the final runtime, first run the existing workflows in their established
  order: register `TST-001`, change its information, and register
  `teacher@example.invalid`. Preserve their exact audits, persisted checks, and
  success markers. All four workflows must use the same SQLite store.
- Locate the one persisted `TST-001` school with a live Q query. Fail on a
  missing or ambiguous school; never create a replacement from enrollment.
- Enroll one student whose name is `Test Student`, integer grade level is `6`,
  and enrollment date is `2026-09-01`. Bind the student to the persisted
  `TST-001` school using the exact relationship syntax demonstrated by TeaQL
  assist.
- Make enrollment idempotent for acceptance retries. Treat the combination of
  the synthetic student name and its `TST-001` school relationship as the
  enrollment identity. If exactly one matching student already exists,
  normalize its name, grade level, date, and school relationship and persist
  the normalized entity. If more than one matches, fail rather than selecting
  one or creating another.
- Every Q execution must include both `purpose()` and `comment()` and must
  propagate errors with `.await?`. Use only a query terminal demonstrated by
  TeaQL assist; `execute_for_one`, `execute_for_list`, and `execute` are all
  acceptable.
- Every student save or update must include
  `audit_as("Enroll student Test Student")` immediately before the exact
  persistence terminal demonstrated by assist.
- After saving or updating, issue a new SQLite-backed Q query. Explicitly
  select every student field and the school relationship used by verification,
  then prove that exactly one matching enrollment has the requested name,
  integer grade, date, and relationship to the same persisted `TST-001`
  school. Checking only an in-memory entity or only a foreign-key presence does
  not count.
- Only after the live query and relationship check succeed, print the exact
  standalone line `KLINTCODE_STUDENT_ENROLLMENT_OK` from the same function
  scope as that Q execution. Never print the marker unconditionally, from a
  test, or after converting an error into a successful note.
- Update `src/main.rs` so `cargo run --quiet` must execute registration,
  information change, teacher
  registration, and student enrollment in that order. Any query, ambiguity,
  save/update, relationship, value, or date-check failure must make the process
  exit non-zero.
- Add a fourth focused application-owned test beside the student module for
  idempotent student enrollment and its persisted school relationship. If a
  test opens SQLite, it must obtain
  its database URL from the explicit
  `SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` environment variable; do not
  silently substitute an in-memory or hard-coded database. The runner gives
  this task execution an isolated SQLite database that is not shared with
  other agents. Tests must only read the injected URL and must not set, remove,
  or otherwise mutate the process environment.

Do not introduce or depend on an application-level shared test lock. Run the
exact commands `cargo test --quiet -- --test-threads=1` and
`cargo run --quiet`; the typed contract injects the runner-owned
`SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` into those child processes and
repeats the serial test command five times to verify idempotence. Do not prefix
either command with an inline shell assignment. Yield only after both commands
pass. Keep all editable business logic outside generated library source, and
do not add Boolean fields.
