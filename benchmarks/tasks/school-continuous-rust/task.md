# School Registry — Small Continuous Rust Evaluation

Create a complete KSML model for a small school registry and generate its Rust
application workspace. This is the initial modeling turn of a continuous
evaluation; six later turns will incrementally add school registration,
school information changes, teacher and student workflows in the same
workspace.

You MUST generate EXACTLY 3 business objects:

1. `school` — the registry root, with these fields:
   - `school_code="TST-001"`
   - `school_name="Klint Synthetic Test School"`
   - `school_address="1 Test Campus Way"`
   - `contact_phone="0000-00000001"`
   - `create_time="createTime()"`
   - `update_time="updateTime()"`
2. `teacher` — belongs to `school`, with `teacher_name="Test Teacher"`,
   `subject_area="Mathematics"`, `email_address="teacher@example.invalid"`,
   `create_time="createTime()"`, and `update_time="updateTime()"`.
3. `student` — belongs to `school`, with `student_name="Test Student"`, integer
   `grade_level="6"`, `enrollment_date="2026-09-01"`,
   `create_time="createTime()"`, and `update_time="updateTime()"`.

## Model contract

- Use root name `school-registry-service`, `org="example"`, SQLite data
  service, and `_module_key="root"`.
- Give every object a proper `_name`, `_module="Core"`, and
  `_module_key="core"`.
- Keep `school_code` as a normal business field. Do not set `_identifier`:
  TeaQL's domain validator reserves identifiers for constant-backed fields,
  while the registration workflow enforces code uniqueness by querying before
  save.
- Express both child relationships with `school="school()"`; keep `school` as
  the root object with no parent/container reference.
- Keep the complete model in one `main.xml` file.
- Do not add registration, address, contact, status, audit, or lookup objects;
  registration remains an application workflow over the `school` object.
- Do not declare an `id` field and do not use reserved field names.
- Do not add Boolean fields; this evaluation intentionally avoids Bool so it
  remains isolated from SQLite Boolean compatibility issues.
- Follow the supplied grammar structure and value forms literally.
- Save the complete model before running any TeaQL command.
- Every TeaQL command MUST use `cargo teaql --input <model> <command>`.
- Evaluate the saved model and repair it to zero errors before generation.
- Generate the Rust targets required by the supplied acceptance contract.

The primary turn ends after the generated Rust workspace passes its normal
build verification. Do not pre-implement any of the six later workflow tasks.
