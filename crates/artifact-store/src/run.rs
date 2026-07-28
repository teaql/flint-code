use anyhow::Result;
use chrono::Utc;
use serde::{Deserialize, Serialize};
use sha2::{Digest, Sha256};
use std::path::{Path, PathBuf};
use tracing::info;

/// Manages the artifact directory for a single run.
pub struct RunArtifacts {
    pub run_id: String,
    pub root: PathBuf,
}

impl RunArtifacts {
    /// Create a new run directory under the given root.
    pub fn create(runs_root: &Path, run_id: &str) -> Result<Self> {
        let root = runs_root.join(run_id);
        std::fs::create_dir_all(&root)?;
        info!(run_id, path = %root.display(), "Run directory created");
        Ok(Self {
            run_id: run_id.to_string(),
            root,
        })
    }

    /// Save the run configuration.
    pub fn save_config(&self, config: &impl Serialize) -> Result<()> {
        let path = self.root.join("run-config.json");
        let json = serde_json::to_string_pretty(config)?;
        std::fs::write(&path, &json)?;
        Ok(())
    }

    /// Append an event to events.jsonl.
    pub fn append_event(&self, event: &impl Serialize) -> Result<()> {
        use std::io::Write;
        let path = self.root.join("events.jsonl");
        let mut file = std::fs::OpenOptions::new()
            .create(true)
            .append(true)
            .open(&path)?;
        let json = serde_json::to_string(event)?;
        writeln!(file, "{json}")?;
        Ok(())
    }

    /// Create an attempt directory and return the path.
    pub fn create_attempt(&self, attempt: u8) -> Result<PathBuf> {
        let dir = self.root.join(format!("attempt-{attempt:02}"));
        std::fs::create_dir_all(&dir)?;
        Ok(dir)
    }

    /// Save a candidate artifact for an attempt.
    pub fn save_candidate(&self, attempt: u8, content: &str) -> Result<PathBuf> {
        let dir = self.create_attempt(attempt)?;
        let path = dir.join("candidate");
        std::fs::write(&path, content)?;
        Ok(path)
    }

    /// Save the final artifact.
    pub fn save_final_artifact(&self, content: &str) -> Result<PathBuf> {
        let path = self.root.join("final-artifact");
        std::fs::write(&path, content)?;

        // Also save the hash
        let hash = sha256_hex(content.as_bytes());
        std::fs::write(self.root.join("final-artifact.sha256"), &hash)?;

        Ok(path)
    }

    /// Save the run summary.
    pub fn save_summary(&self, summary: &impl Serialize) -> Result<()> {
        let path = self.root.join("summary.json");
        let json = serde_json::to_string_pretty(summary)?;
        std::fs::write(&path, &json)?;
        Ok(())
    }

    /// Save arbitrary JSON to an attempt directory.
    pub fn save_attempt_file(
        &self,
        attempt: u8,
        filename: &str,
        content: &impl Serialize,
    ) -> Result<()> {
        let dir = self.create_attempt(attempt)?;
        let path = dir.join(filename);
        let json = serde_json::to_string_pretty(content)?;
        std::fs::write(&path, &json)?;
        Ok(())
    }

    /// Save arbitrary string content to an attempt directory.
    pub fn save_attempt_raw(&self, attempt: u8, filename: &str, content: &str) -> Result<PathBuf> {
        let dir = self.create_attempt(attempt)?;
        let path = dir.join(filename);
        if let Some(p) = path.parent() {
            std::fs::create_dir_all(p)?;
        }
        std::fs::write(&path, content)?;
        Ok(path)
    }
}

/// Compute SHA-256 hex digest of bytes.
pub fn sha256_hex(data: &[u8]) -> String {
    let mut hasher = Sha256::new();
    hasher.update(data);
    format!("{:x}", hasher.finalize())
}
