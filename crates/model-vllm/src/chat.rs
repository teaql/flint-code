use serde::{Deserialize, Serialize};

/// Chat message for API requests.
///
/// Supports standard messages (system/user/assistant) and tool-calling
/// messages (assistant with tool_calls, tool with tool_call_id).
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ChatMessage {
    pub role: String,
    #[serde(default)]
    pub content: String,
    #[serde(skip_serializing_if = "Option::is_none", default)]
    pub tool_calls: Option<Vec<ToolCallInfo>>,
    #[serde(skip_serializing_if = "Option::is_none", default)]
    pub tool_call_id: Option<String>,
}

impl Default for ChatMessage {
    fn default() -> Self {
        Self {
            role: String::new(),
            content: String::new(),
            tool_calls: None,
            tool_call_id: None,
        }
    }
}

impl ChatMessage {
    /// Create a system message
    pub fn system(content: impl Into<String>) -> Self {
        Self { role: "system".to_string(), content: content.into(), ..Default::default() }
    }

    /// Create a user message
    pub fn user(content: impl Into<String>) -> Self {
        Self { role: "user".to_string(), content: content.into(), ..Default::default() }
    }

    /// Create an assistant message (text only)
    pub fn assistant(content: impl Into<String>) -> Self {
        Self { role: "assistant".to_string(), content: content.into(), ..Default::default() }
    }

    /// Create an assistant message with tool calls
    pub fn assistant_with_tool_calls(content: &str, tool_calls: Vec<ToolCallInfo>) -> Self {
        Self {
            role: "assistant".to_string(),
            content: content.to_string(),
            tool_calls: Some(tool_calls),
            tool_call_id: None,
        }
    }

    /// Create a tool result message
    pub fn tool_result(tool_call_id: impl Into<String>, content: impl Into<String>) -> Self {
        Self {
            role: "tool".to_string(),
            content: content.into(),
            tool_calls: None,
            tool_call_id: Some(tool_call_id.into()),
        }
    }
}

/// Tool definition for function calling (OpenAI-compatible)
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ToolDefinition {
    #[serde(rename = "type")]
    pub tool_type: String,
    pub function: FunctionDefinition,
}

/// Function definition within a tool
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct FunctionDefinition {
    pub name: String,
    pub description: String,
    pub parameters: serde_json::Value,
}

/// Tool call info returned by the model
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ToolCallInfo {
    pub id: String,
    #[serde(rename = "type", default = "default_function_type")]
    pub call_type: String,
    pub function: FunctionCallInfo,
}

/// Function call details
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct FunctionCallInfo {
    pub name: String,
    pub arguments: String,
}

fn default_function_type() -> String {
    "function".to_string()
}

/// Chat completion request
#[derive(Debug, Clone, Serialize)]
pub struct ChatRequest {
    pub model: String,
    pub messages: Vec<ChatMessage>,
    pub temperature: f32,
    pub top_p: f32,
    pub max_tokens: u32,
    pub stream: bool,
    #[serde(skip_serializing_if = "Option::is_none")]
    pub chat_template_kwargs: Option<ChatTemplateKwargs>,
    #[serde(skip_serializing_if = "Option::is_none")]
    pub tools: Option<Vec<ToolDefinition>>,
    #[serde(skip_serializing_if = "Option::is_none")]
    pub tool_choice: Option<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ChatTemplateKwargs {
    pub enable_thinking: bool,
}

/// Chat completion response
#[derive(Debug, Clone, Deserialize)]
pub struct ChatResponse {
    pub id: Option<String>,
    pub choices: Vec<ChatChoice>,
    pub usage: Option<ChatUsage>,
}

#[derive(Debug, Clone, Deserialize)]
pub struct ChatChoice {
    pub index: u32,
    pub message: Option<ChatMessageContent>,
    pub delta: Option<ChatDelta>,
    pub finish_reason: Option<String>,
}

#[derive(Debug, Clone, Deserialize)]
pub struct ChatMessageContent {
    pub role: Option<String>,
    pub content: Option<String>,
    /// Reasoning content (for thinking mode)
    pub reasoning_content: Option<String>,
    /// Tool calls requested by the model
    pub tool_calls: Option<Vec<ToolCallInfo>>,
}

#[derive(Debug, Clone, Deserialize)]
pub struct ChatDelta {
    pub role: Option<String>,
    pub content: Option<String>,
    pub reasoning_content: Option<String>,
}

#[derive(Debug, Clone, Deserialize)]
pub struct ChatUsage {
    pub prompt_tokens: u32,
    pub completion_tokens: u32,
    pub total_tokens: u32,
}

/// Streaming chunk
#[derive(Debug, Clone, Deserialize)]
pub struct StreamChunk {
    pub id: Option<String>,
    pub choices: Vec<ChatChoice>,
    pub usage: Option<ChatUsage>,
}
