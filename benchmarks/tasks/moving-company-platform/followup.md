Continue in the existing generated Rust workspace; do not regenerate the KSML
model and never read, search, or modify generated library source under
`lib/src`.

First review `model/main.xml`, `AGENTS.md`, the complete saved assist responses,
and `.klintcode/validation-evidence.json`. Write `MODEL_REVIEW.md` from those
validator facts; do not infer or conceal object-count, connectivity, warning, or
forbidden-code results.

Then implement representative application-level Rust examples under `src/`
that cover operations/logistics, customers, employees/payroll, finance, and
assets where available. Demonstrate at least three Q query executions and one E
expression evaluation. Every `execute()` or `execute_for_list()` chain must
include `purpose()` and `comment()`. Use only syntax copied from `AGENTS.md` or
the exact `cargo teaql --input model/main.xml rust-assist-*` output.

Add focused tests and a deterministic runtime self-test. The runtime command
must propagate every query error, return non-zero on failure, and print
`KLINTCODE_Q_OK:1`, `KLINTCODE_Q_OK:2`, and `KLINTCODE_Q_OK:3` only after the
corresponding live Q executions return successfully. Print `KLINTCODE_E_OK:1`
only after the live E evaluation succeeds, then print `KLINTCODE_RUN_OK` only
after all four checks pass. These markers must be emitted from the same control
flow as the checked calls; do not print them unconditionally or from tests.
Every live Q terminal call must use `.await?` so its error is propagated before
the corresponding marker. Never turn an error into a successful "note".

Finally write `RUNNING_REPORT.md` with the model evidence path, implemented
APIs, exact commands, observed test count, live-runtime result, and remaining
limitations. Complete every item in the supplied machine acceptance contract
before yielding.
