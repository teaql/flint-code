---
name: build-small-school-registry
description: Model the three-object school registry used by the continuous Rust evaluation.
---

<!-- phase:model_generation -->
Draft and save the complete KSML model before invoking TeaQL. Use the supplied
grammar only as syntax guidance and model the requested school domain instead
of copying the example domain.

The accepted model has exactly three business objects: root `school`, child
`teacher`, and child `student`. Both children reference `school`; do not invent
any additional object, constant, relationship, `id`, or Boolean field. Every
attribute value must be non-empty. The model name is
`school-registry-service`, the data service is SQLite, and the organization is
`example`.

After the complete model exists, use only `cargo teaql --input <model>
<command>`. Evaluate the saved model, repair reported errors, and generate only
after evaluation reaches zero errors.
<!-- /phase:model_generation -->
