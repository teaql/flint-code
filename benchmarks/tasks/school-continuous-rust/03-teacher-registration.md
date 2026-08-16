Continue in the same Rust application workspace and preserve the verified
school-registration and school-information-change workflows from the previous
tasks. Do not regenerate or change `model/main.xml`, and never read, search, or
modify generated library source under `lib/src`.

Read the workspace `AGENTS.md` and the complete cached TeaQL assist responses.
If the exact teacher creation, school relationship, setter, query, selection,
or save syntax is not already present, run the relevant `cargo teaql --input
model/main.xml rust-assist-*` command. Copy the returned syntax literally; do
not infer TeaQL APIs from other frameworks.

Create a focused `src/teacher_workflow.rs` module for the idempotent
teacher-registration workflow, declare it from the existing crate, and wire it
into `src/main.rs`. Keep the earlier school logic in `src/school_workflow.rs`;
do not copy that module into the new file or grow it with teacher logic:

- Name the production entry point `register_teacher` and call that entry point
  from `src/main.rs` after the two existing school workflow calls.

- Run the existing idempotent school-registration workflow and then the
  existing school-information-change workflow first. They must establish one
  `TST-001` school with the current persisted values `Klint Synthetic Future
  School`, `2 Test Campus Way`, and `0000-00000002` in the same SQLite store.
- Locate `TST-001` with a live Q query. Fail on a missing or ambiguous school;
  never silently create another school from this workflow.
- Register one teacher whose name is `Test Teacher`, subject area is
  `Mathematics`, and email address is `teacher@example.invalid`. Bind that
  teacher to the persisted `TST-001` school using the exact relationship syntax
  demonstrated by TeaQL assist.
- Make the operation idempotent for acceptance retries. Query by the synthetic
  email identity before creating. If exactly one matching teacher already
  exists, normalize its name, subject area, email, and school relationship and
  persist the normalized entity. If more than one matches, fail instead of
  choosing one or creating another.
- Every Q execution must include both `purpose()` and `comment()` and must
  propagate errors with `.await?`. Use only a query terminal demonstrated by
  TeaQL assist; `execute_for_one`, `execute_for_list`, and `execute` are all
  acceptable.
- Every teacher save or update must include
  `audit_as("Register teacher teacher@example.invalid")` immediately before the
  exact persistence terminal demonstrated by assist.
- After saving or updating, issue a new SQLite-backed Q query. Explicitly
  select every teacher field and the school relationship used by verification,
  then prove that exactly one matching teacher has all three requested values
  and is related to the same persisted `TST-001` school. Checking only an
  in-memory entity or only the foreign-key presence does not count.
- Only after the live query and relationship check succeed, print the exact
  standalone line `KLINTCODE_TEACHER_REGISTRATION_OK` from the same function
  scope as that Q execution. Never print the marker unconditionally, from a
  test, or after converting an error into a successful note.
- Preserve the two earlier workflows and markers. Update `src/main.rs` so
  `cargo run --quiet` must
  execute, against one database and in this order: registration, information
  change, teacher registration. Any query, ambiguity, save/update, relationship,
  or value-check failure must make the process exit non-zero.
- Add a third focused application-owned test for idempotent teacher
  registration and its persisted school relationship. Keep this test beside
  the focused teacher module rather than adding it to `school_workflow.rs`. If
  a test opens SQLite,
  it must obtain its database URL from the explicit
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
