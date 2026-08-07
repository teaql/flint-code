use async_trait::async_trait;
use std::fmt::Debug;

/// Represents a retrieved document from the knowledge base.
#[derive(Debug, Clone)]
pub struct RagDocument {
    pub id: String,
    pub content: String,
    pub similarity_score: f32,
}

/// Core contract for Agent knowledge retrieval.
/// Implementations can be backed by LanceDB, Qdrant, SQLite-VSS, or mock structures.
#[async_trait]
pub trait KnowledgeRetriever: Send + Sync + Debug {
    /// Retrieve relevant documents based on an error message (from tool or compiler).
    /// Used during the evaluation/repair loops.
    async fn retrieve_for_error(&self, error_message: &str) -> anyhow::Result<Vec<RagDocument>>;

    /// Retrieve relevant documents based on the task description or user intent.
    /// Used during the initial task setup or preflight planning.
    async fn search_by_intent(&self, task_description: &str) -> anyhow::Result<Vec<RagDocument>>;
}
