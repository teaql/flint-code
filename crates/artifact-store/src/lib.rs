//! Structured run artifact storage and report generation.
//!
//! Each run creates an independent directory:
//! ```text
//! runs/<run-id>/
//! ├── run-config.json
//! ├── events.jsonl
//! ├── attempt-01/
//! │   ├── request.json
//! │   ├── response.json
//! │   ├── candidate
//! │   ├── local-validation.json
//! │   └── domain-validation.log
//! ├── final-artifact
//! ├── final-workspace/        # verified generated code snapshot
//! └── summary.json
//! ```

mod report;
mod run;

pub use report::*;
pub use run::*;
