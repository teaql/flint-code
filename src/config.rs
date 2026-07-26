//! Agent configuration with DGX Spark context budget management.
//!
//! Supports environment variable overrides:
//! - `DGX_AGENT_BASE_URL` → `llm_endpoint`
//! - `DGX_AGENT_MODEL` → `model_name`

use anyhow::Result;
use serde::{Deserialize, Serialize};
use std::path::PathBuf;

/// DGX Spark context budget defaults (nemotron-3-super profile)
const DEFAULT_MAX_PROMPT_TOKENS: usize = 48_000;
const DEFAULT_MAX_COMPLETION_TOKENS: usize = 4_096;
const DEFAULT_SAFETY_TOKENS: usize = 8_192;

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct AgentConfig {
    // ── Context budget ──────────────────────────────────────────────
    /// Max tokens the model accepts as prompt input
    pub max_prompt_tokens: usize,
    /// Max tokens the model can generate in one completion
    pub max_completion_tokens: usize,
    /// Safety margin reserved to avoid overflow
    pub safety_tokens: usize,
    /// Effective context budget (auto-calculated)
    ///   = max_prompt_tokens − safety_tokens
    pub context_budget: usize,

    // ── LLM connection ──────────────────────────────────────────────
    /// LLM endpoint — override with `DGX_AGENT_BASE_URL` env var
    pub llm_endpoint: String,
    /// Model name — override with `DGX_AGENT_MODEL` env var
    pub model_name: String,

    // ── Sampling ────────────────────────────────────────────────────
    pub temperature: f32,
    pub top_p: f32,

    // ── Model behavior ──────────────────────────────────────────────
    /// Enable thinking/reasoning mode (chain-of-thought)
    pub thinking: bool,
    /// Model request timeout in seconds
    pub model_timeout_secs: u64,
    /// Max repair attempts on compilation/test failure
    pub max_repairs: usize,
    /// Max concurrent model requests (DGX Spark = 1)
    pub model_request_concurrency: usize,

    // ── Workspace ───────────────────────────────────────────────────
    pub workspace_root: PathBuf,
    pub agent_kit_path: Option<PathBuf>,

    // ── TeaQL ───────────────────────────────────────────────────────
    pub cargo_teaql_version: String,

    // ── UI ───────────────────────────────────────────────────────────
    pub show_token_usage: bool,
    pub auto_compact: bool,
    pub theme: ThemeConfig,

    // ── Legacy aliases kept for backward compat ─────────────────────
    /// Alias for max_prompt_tokens + safety_tokens + max_completion_tokens
    #[serde(skip_serializing_if = "Option::is_none")]
    pub max_context_window: Option<usize>,
    #[serde(skip_serializing_if = "Option::is_none")]
    pub reserved_tokens: Option<usize>,
    #[serde(skip_serializing_if = "Option::is_none")]
    pub max_output_tokens: Option<usize>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ThemeConfig {
    pub name: String,
}

impl Default for AgentConfig {
    fn default() -> Self {
        Self {
            max_prompt_tokens: DEFAULT_MAX_PROMPT_TOKENS,
            max_completion_tokens: DEFAULT_MAX_COMPLETION_TOKENS,
            safety_tokens: DEFAULT_SAFETY_TOKENS,
            context_budget: DEFAULT_MAX_PROMPT_TOKENS - DEFAULT_SAFETY_TOKENS,
            llm_endpoint: "http://localhost:8000/v1".to_string(),
            model_name: "nemotron-3-super".to_string(),
            temperature: 0.0,
            top_p: 1.0,
            thinking: false,
            model_timeout_secs: 300,
            max_repairs: 1,
            model_request_concurrency: 1,
            workspace_root: PathBuf::from("."),
            agent_kit_path: None,
            cargo_teaql_version: "2.0.8".to_string(),
            show_token_usage: true,
            auto_compact: true,
            theme: ThemeConfig {
                name: "spark".to_string(),
            },
            max_context_window: None,
            reserved_tokens: None,
            max_output_tokens: None,
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
        let mut config = if path.exists() {
            let content = std::fs::read_to_string(&path)?;
            toml::from_str(&content)?
        } else {
            let config = Self::default();
            let content = toml::to_string_pretty(&config)?;
            std::fs::write(&path, content)?;
            config
        };

        // Environment variable overrides (not persisted)
        if let Ok(url) = std::env::var("DGX_AGENT_BASE_URL") {
            config.llm_endpoint = format!("{}/v1", url.trim_end_matches('/'));
        }
        if let Ok(model) = std::env::var("DGX_AGENT_MODEL") {
            config.model_name = model;
        }

        // Migrate legacy fields
        if let Some(max_output) = config.max_output_tokens {
            config.max_completion_tokens = max_output;
        }
        if let Some(reserved) = config.reserved_tokens {
            config.safety_tokens = reserved;
        }

        config.recalculate_budget();
        Ok(config)
    }

    pub fn save(&self) -> Result<()> {
        let path = Self::config_path()?;
        let content = toml::to_string_pretty(self)?;
        std::fs::write(&path, content)?;
        Ok(())
    }

    /// Recalculate effective context budget
    pub fn recalculate_budget(&mut self) {
        self.context_budget = self.max_prompt_tokens
            .saturating_sub(self.safety_tokens);
    }

    /// Check if adding `additional_tokens` would exceed the budget
    pub fn would_exceed_budget(&self, current_tokens: usize, additional_tokens: usize) -> bool {
        current_tokens + additional_tokens > self.context_budget
    }

    /// Total context window (for display purposes)
    pub fn total_context_window(&self) -> usize {
        self.max_prompt_tokens + self.max_completion_tokens
    }
}
