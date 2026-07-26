//! Context window management for the 64K-limited DGX Spark models.
//!
//! This module tracks token usage across the conversation and implements
//! compaction strategies to stay within the context budget.

use anyhow::Result;
use serde::{Deserialize, Serialize};
use chrono::Utc;

/// A message in the conversation context
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ContextMessage {
    pub role: Role,
    pub content: String,
    pub token_count: usize,
    pub timestamp: String,
    /// Whether this message can be compacted/summarized
    pub compactable: bool,
    /// Priority: higher = keep longer during compaction
    pub priority: u8,
}

#[derive(Debug, Clone, Serialize, Deserialize, PartialEq)]
pub enum Role {
    System,
    User,
    Assistant,
    Tool,
}

impl std::fmt::Display for Role {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        match self {
            Role::System => write!(f, "system"),
            Role::User => write!(f, "user"),
            Role::Assistant => write!(f, "assistant"),
            Role::Tool => write!(f, "tool"),
        }
    }
}

/// Manages the conversation context within the token budget
pub struct ContextManager {
    messages: Vec<ContextMessage>,
    system_prompt: String,
    system_prompt_tokens: usize,
    total_tokens: usize,
    budget: usize,
    /// Number of times context was compacted
    compaction_count: usize,
}

impl ContextManager {
    pub fn new(budget: usize, system_prompt: String) -> Self {
        let system_prompt_tokens = estimate_tokens(&system_prompt);
        Self {
            messages: Vec::new(),
            system_prompt,
            system_prompt_tokens,
            total_tokens: system_prompt_tokens,
            budget,
            compaction_count: 0,
        }
    }

    /// Add a message to the context
    pub fn add_message(&mut self, role: Role, content: String, priority: u8) {
        let token_count = estimate_tokens(&content);
        let compactable = role != Role::System;
        let msg = ContextMessage {
            role,
            content,
            token_count,
            timestamp: Utc::now().to_rfc3339(),
            compactable,
            priority,
        };
        self.total_tokens += token_count;
        self.messages.push(msg);
    }

    /// Get current token usage
    pub fn token_usage(&self) -> TokenUsage {
        TokenUsage {
            used: self.total_tokens,
            budget: self.budget,
            system_prompt: self.system_prompt_tokens,
            messages: self.total_tokens - self.system_prompt_tokens,
            compaction_count: self.compaction_count,
        }
    }

    /// Check if context needs compaction
    pub fn needs_compaction(&self) -> bool {
        self.total_tokens > (self.budget as f64 * 0.85) as usize
    }

    /// Check if context is critically full
    pub fn is_critical(&self) -> bool {
        self.total_tokens > (self.budget as f64 * 0.95) as usize
    }

    /// Compact the context by summarizing older messages
    pub fn compact(&mut self) -> Result<CompactionResult> {
        let before = self.total_tokens;

        // Strategy: Keep system prompt, keep last N high-priority messages,
        // summarize everything else
        let keep_count = 6; // Keep last 6 messages
        if self.messages.len() <= keep_count {
            return Ok(CompactionResult {
                tokens_before: before,
                tokens_after: before,
                messages_removed: 0,
            });
        }

        let split_point = self.messages.len() - keep_count;
        let old_messages: Vec<ContextMessage> = self.messages.drain(..split_point).collect();
        let removed_count = old_messages.len();

        // Create a summary of removed messages
        let summary = create_summary(&old_messages);
        let summary_tokens = estimate_tokens(&summary);

        // Insert summary as first message
        self.messages.insert(0, ContextMessage {
            role: Role::System,
            content: summary,
            token_count: summary_tokens,
            timestamp: Utc::now().to_rfc3339(),
            compactable: true,
            priority: 5,
        });

        // Recalculate total tokens
        self.total_tokens = self.system_prompt_tokens
            + self.messages.iter().map(|m| m.token_count).sum::<usize>();
        self.compaction_count += 1;

        Ok(CompactionResult {
            tokens_before: before,
            tokens_after: self.total_tokens,
            messages_removed: removed_count,
        })
    }

    /// Get messages for API call (including system prompt)
    pub fn get_api_messages(&self) -> Vec<ApiMessage> {
        let mut msgs = vec![ApiMessage {
            role: "system".to_string(),
            content: self.system_prompt.clone(),
        }];
        for m in &self.messages {
            msgs.push(ApiMessage {
                role: m.role.to_string(),
                content: m.content.clone(),
            });
        }
        msgs
    }

    /// Get all messages for display
    pub fn messages(&self) -> &[ContextMessage] {
        &self.messages
    }
}

#[derive(Debug, Clone)]
pub struct TokenUsage {
    pub used: usize,
    pub budget: usize,
    pub system_prompt: usize,
    pub messages: usize,
    pub compaction_count: usize,
}

impl TokenUsage {
    pub fn percentage(&self) -> f64 {
        if self.budget == 0 {
            return 100.0;
        }
        (self.used as f64 / self.budget as f64) * 100.0
    }
}

#[derive(Debug)]
pub struct CompactionResult {
    pub tokens_before: usize,
    pub tokens_after: usize,
    pub messages_removed: usize,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ApiMessage {
    pub role: String,
    pub content: String,
}

/// Estimate token count (roughly 4 chars per token for English, 2 for CJK)
fn estimate_tokens(text: &str) -> usize {
    // Simple heuristic: ~4 chars per token for ASCII, ~2 for CJK
    let mut count = 0;
    for ch in text.chars() {
        if ch.is_ascii() {
            count += 1;
        } else {
            count += 2; // CJK chars are roughly 2 tokens
        }
    }
    // Divide by ~4 chars per token average
    (count + 3) / 4
}

/// Create a compact summary of old messages
fn create_summary(messages: &[ContextMessage]) -> String {
    let mut summary = String::from("[Context Summary] Previous conversation covered:\n");
    for msg in messages {
        let preview: String = msg.content.chars().take(100).collect();
        let ellipsis = if msg.content.len() > 100 { "..." } else { "" };
        summary.push_str(&format!("- [{}] {}{}\n", msg.role, preview, ellipsis));
    }
    summary
}
