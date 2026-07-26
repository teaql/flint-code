//! Agent configuration with DGX Spark context budget management.

use anyhow::Result;
use serde::{Deserialize, Serialize};
use std::path::PathBuf;

/// DGX Spark has a 64K token limit. We reserve headroom for safety.
const DEFAULT_CONTEXT_BUDGET: usize = 48_000;
const DEFAULT_MAX_CONTEXT: usize = 64_000;
const DEFAULT_RESERVED_TOKENS: usize = 8_000;
const DEFAULT_MAX_OUTPUT_TOKENS: usize = 8_000;

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct AgentConfig {
    /// Total context window of the target model (DGX Spark local models)
    pub max_context_window: usize,
    /// Tokens reserved for system prompt + agent instructions
    pub reserved_tokens: usize,
    /// Max tokens for model output
    pub max_output_tokens: usize,
    /// Effective context budget = max_context_window - reserved - max_output
    pub context_budget: usize,
    /// LLM endpoint (local DGX Spark NIM endpoint)
    pub llm_endpoint: String,
    /// Model name to use
    pub model_name: String,
    /// Temperature
    pub temperature: f32,
    /// Workspace root path
    pub workspace_root: PathBuf,
    /// TeaQL agent-kit path
    pub agent_kit_path: Option<PathBuf>,
    /// cargo-teaql version requirement
    pub cargo_teaql_version: String,
    /// Enable token usage display
    pub show_token_usage: bool,
    /// Auto-compact context when budget exceeded
    pub auto_compact: bool,
    /// Theme
    pub theme: ThemeConfig,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ThemeConfig {
    pub name: String,
}

impl Default for AgentConfig {
    fn default() -> Self {
        Self {
            max_context_window: DEFAULT_MAX_CONTEXT,
            reserved_tokens: DEFAULT_RESERVED_TOKENS,
            max_output_tokens: DEFAULT_MAX_OUTPUT_TOKENS,
            context_budget: DEFAULT_CONTEXT_BUDGET,
            llm_endpoint: "http://localhost:8000/v1".to_string(),
            model_name: "meta/llama-3.1-70b-instruct".to_string(),
            temperature: 0.1,
            workspace_root: PathBuf::from("."),
            agent_kit_path: None,
            cargo_teaql_version: "2.0.8".to_string(),
            show_token_usage: true,
            auto_compact: true,
            theme: ThemeConfig {
                name: "spark".to_string(),
            },
        }
    }
}

impl AgentConfig {
    pub fn config_path() -> Result<PathBuf> {
        let config_dir = dirs::config_dir()
            .unwrap_or_else(|| PathBuf::from("."))
            .join("teaql-dgx-spark-agent");
        std::fs::create_dir_all(&config_dir)?;
        Ok(config_dir.join("config.toml"))
    }

    pub fn load_or_create() -> Result<Self> {
        let path = Self::config_path()?;
        if path.exists() {
            let content = std::fs::read_to_string(&path)?;
            let mut config: Self = toml::from_str(&content)?;
            config.recalculate_budget();
            Ok(config)
        } else {
            let config = Self::default();
            let content = toml::to_string_pretty(&config)?;
            std::fs::write(&path, content)?;
            Ok(config)
        }
    }

    pub fn save(&self) -> Result<()> {
        let path = Self::config_path()?;
        let content = toml::to_string_pretty(self)?;
        std::fs::write(&path, content)?;
        Ok(())
    }

    /// Recalculate effective context budget from window size and reservations
    pub fn recalculate_budget(&mut self) {
        self.context_budget = self.max_context_window
            .saturating_sub(self.reserved_tokens)
            .saturating_sub(self.max_output_tokens);
    }

    /// Check if adding `additional_tokens` would exceed the budget
    pub fn would_exceed_budget(&self, current_tokens: usize, additional_tokens: usize) -> bool {
        current_tokens + additional_tokens > self.context_budget
    }
}
