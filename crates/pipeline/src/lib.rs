pub mod agent_loop;
pub mod execution;
pub mod executor;
pub mod followup_acceptance;
pub mod known_infrastructure;
mod process_env;
mod process_output;
pub mod remote_config;
pub mod suite;
pub mod tools;

pub use agent_loop::{AgentLoopResult, run_agent_loop};
pub use executor::PipelineExecutor;
pub use suite::{SuitePlan, SuiteResult, format_suite_markdown, run_suite};
