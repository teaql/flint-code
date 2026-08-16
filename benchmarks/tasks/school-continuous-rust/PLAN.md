# School Continuous Evaluation Plan

This package evaluates seven user task turns in one generated workspace:

1. Model exactly three objects (`school`, `teacher`, and `student`) and
   generate the Rust application.
2. Add an idempotent school-registration workflow.
3. Change the registered school's name, address, and phone without losing the
   registration workflow.
4. Register a teacher related to the persisted school.
5. Enroll a student related to the same school.
6. Change the teacher's name and subject while preserving its stable email and
   school relationship.
7. Promote the student from grade 6 to grade 7 while preserving its enrollment
   date and school relationship.

Use the CLI's ordered follow-up queue for this six-continuation evaluation:

```bash
SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL="sqlite://data.db" \
cargo run --quiet -p klintcode-cli -- run \
  --task benchmarks/tasks/school-continuous-rust \
  --profile profiles/dgx-spark-nemotron-3-super-64k.toml \
  --output runs/school-continuous \
  --skill benchmarks/tasks/school-continuous-rust/skill.md \
  --follow-up benchmarks/tasks/school-continuous-rust/01-school-registration.md \
  --follow-up-acceptance benchmarks/tasks/school-continuous-rust/01-school-registration.acceptance.json \
  --follow-up benchmarks/tasks/school-continuous-rust/02-school-information-change.md \
  --follow-up-acceptance benchmarks/tasks/school-continuous-rust/02-school-information-change.acceptance.json \
  --follow-up benchmarks/tasks/school-continuous-rust/03-teacher-registration.md \
  --follow-up-acceptance benchmarks/tasks/school-continuous-rust/03-teacher-registration.acceptance.json \
  --follow-up benchmarks/tasks/school-continuous-rust/04-student-enrollment.md \
  --follow-up-acceptance benchmarks/tasks/school-continuous-rust/04-student-enrollment.acceptance.json \
  --follow-up benchmarks/tasks/school-continuous-rust/05-teacher-information-change.md \
  --follow-up-acceptance benchmarks/tasks/school-continuous-rust/05-teacher-information-change.acceptance.json \
  --follow-up benchmarks/tasks/school-continuous-rust/06-student-grade-promotion.md \
  --follow-up-acceptance benchmarks/tasks/school-continuous-rust/06-student-grade-promotion.acceptance.json \
  --fail-fast
```

Substitute the backend profile when needed. The caller-provided SQLite URL is
a baseline that identifies the database scheme; the runner derives an isolated
database in a runner-owned temporary area for each `PipelineExecutor` before forwarding
`SCHOOL_REGISTRY_SERVICE_CORE_DATABASE_URL` to its child processes. Parallel
agents therefore never share a database, while `cargo test` and `cargo run`
inside one agent continue to use that agent's database. Follow-up contracts
never receive model/API credentials. During follow-up work, the agent invokes
plain `cargo test` / `cargo run`; generated tests read the injected database
URL but never change the process environment. Do not use
`--allow-unverified-follow-up` for this run.

All six follow-up contracts require source changes, independent build and test
success, AST-proven Q executions (`execute`, `execute_for_list`, or
`execute_for_one`) with `purpose()` and `comment()`, propagated query errors,
and runtime markers bound to the same compiled function scopes as the queries.
The agent-specific database URL is explicitly forwarded to both `cargo test`
and `cargo run`. Each typed contract runs
`cargo test --quiet -- --test-threads=1` five times, without a shell loop, to
verify idempotence. Application code is not responsible for coordinating
multiple agents and must not add a cross-module test lock for runner-owned
infrastructure isolation.

Every contract is cumulative. The final contract requires all three focused
workflow modules, six audit descriptions, six independently bound runtime
markers, at least six application tests, and one final runtime that executes
all six workflows in order against the same SQLite store.
