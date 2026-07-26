use serde::{Deserialize, Serialize};
use anyhow::Result;
use std::path::Path;

/// Model profile captures verified runtime characteristics.
/// Loaded from profiles/*.toml files.
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ModelProfile {
    pub model: ModelConfig,
    pub context: ContextConfig,
    pub sampling: SamplingConfig,
    pub thinking: ThinkingConfig,
    pub concurrency: ConcurrencyConfig,
    pub timeouts: TimeoutConfig,
    pub run: RunConfig,
    pub prompt: PromptConfig,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ModelConfig {
    pub name: String,
    pub endpoint_env: String,
    pub default_endpoint: String,
    pub api_path: String,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ContextConfig {
    pub model_context_tokens: u32,
    pub max_prompt_tokens: u32,
    pub max_completion_tokens: u32,
    pub safety_tokens: u32,
}

impl ContextConfig {
    /// Agent usable limit = max_prompt + max_completion + safety
    pub fn agent_limit(&self) -> u32 {
        self.max_prompt_tokens + self.max_completion_tokens + self.safety_tokens
    }

    /// Whether a given prompt size fits the budget
    pub fn admits(&self, estimated_prompt_tokens: u32) -> bool {
        estimated_prompt_tokens + self.max_completion_tokens + self.safety_tokens
            <= self.model_context_tokens
    }
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct SamplingConfig {
    pub temperature: f32,
    pub top_p: f32,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ThinkingConfig {
    pub enabled: bool,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ConcurrencyConfig {
    pub max_in_flight: u8,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct TimeoutConfig {
    pub health_secs: u64,
    pub model_secs: u64,
    pub domain_validation_secs: u64,
    pub build_secs: u64,
    pub test_secs: u64,
    pub cancel_grace_secs: u64,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct RunConfig {
    pub max_repairs: u8,
    pub retry_http_4xx: bool,
    pub retry_http_5xx_count: u8,
    pub diagnostic_character_limit: usize,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct PromptConfig {
    pub max_system_prompt_tokens: u32,
}

impl ModelProfile {
    /// Load a profile from a TOML file
    pub fn load(path: &Path) -> Result<Self> {
        let content = std::fs::read_to_string(path)?;
        let profile: Self = toml::from_str(&content)?;
        Ok(profile)
    }

    /// Resolve the actual endpoint URL, checking env var first
    pub fn resolve_endpoint(&self) -> String {
        std::env::var(&self.model.endpoint_env)
            .unwrap_or_else(|_| self.model.default_endpoint.clone())
    }

    /// Full URL for chat completions
    pub fn chat_url(&self) -> String {
        let base = self.resolve_endpoint();
        let base = base.trim_end_matches('/');
        format!("{}{}", base, self.model.api_path)
    }
}
