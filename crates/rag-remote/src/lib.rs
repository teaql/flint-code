use agent_core::rag::{KnowledgeRetriever, RagDocument};
use anyhow::Result;
use reqwest::Client;
use serde_json::json;
use tracing::info;

pub struct WeaviateRetriever {
    client: Client,
    endpoint: String,
}

impl WeaviateRetriever {
    pub fn new(endpoint: &str) -> Self {
        Self {
            client: Client::new(),
            endpoint: endpoint.to_string(),
        }
    }

    async fn do_query(&self, intent: &str, limit: usize) -> Result<Vec<RagDocument>> {
        let query = format!(
            r#"{{
                Get {{
                    Skill(
                        nearText: {{ concepts: ["{}"] }}
                        limit: {}
                    ) {{
                        title
                        content
                    }}
                }}
            }}"#,
            intent.replace("\"", "\\\""), limit
        );

        let res = self.client.post(&self.endpoint)
            .json(&json!({ "query": query }))
            .send()
            .await?;

        let body: serde_json::Value = res.json().await?;
        let mut docs = Vec::new();

        if let Some(skills) = body.pointer("/data/Get/Skill").and_then(|v| v.as_array()) {
            for skill in skills {
                let content = skill["content"].as_str().unwrap_or("").to_string();
                let title = skill["title"].as_str().unwrap_or("unknown").to_string();
                docs.push(RagDocument {
                    id: title,
                    content,
                    similarity_score: 1.0, // Weaviate GraphQL nearText doesn't return distance by default without _additional { distance }
                });
            }
        }

        Ok(docs)
    }
}

impl std::fmt::Debug for WeaviateRetriever {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        f.debug_struct("WeaviateRetriever")
            .field("endpoint", &self.endpoint)
            .finish()
    }
}

#[async_trait::async_trait]
impl KnowledgeRetriever for WeaviateRetriever {
    async fn retrieve_for_error(&self, error_message: &str) -> Result<Vec<RagDocument>> {
        info!("Querying remote Weaviate DB for error: {:.50}", error_message);
        self.do_query(error_message, 3).await
    }

    async fn search_by_intent(&self, task_description: &str) -> Result<Vec<RagDocument>> {
        info!("Querying remote Weaviate DB for intent: {:.50}", task_description);
        self.do_query(task_description, 5).await
    }
}
