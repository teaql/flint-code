use anyhow::Result;
use serde::Serialize;
use sha2::{Digest, Sha256};
use std::path::{Path, PathBuf};
use tracing::info;

/// Manages the artifact directory for a single run.
#[derive(Clone)]
pub struct RunArtifacts {
    pub run_id: String,
    pub root: PathBuf,
}

impl RunArtifacts {
    /// Create a new run directory under the given root.
    pub async fn create(runs_root: &Path, run_id: &str) -> Result<Self> {
        let root = runs_root.join(run_id);
        tokio::fs::create_dir_all(&root).await?;
        info!(run_id, path = %root.display(), "Run directory created");
        Ok(Self {
            run_id: run_id.to_string(),
            root,
        })
    }

    /// Save the run configuration.
    pub async fn save_config(&self, config: &impl Serialize) -> Result<()> {
        let path = self.root.join("run-config.json");
        let json = serde_json::to_string_pretty(config)?;
        tokio::fs::write(&path, &json).await?;
        Ok(())
    }

    /// Append an event to events.jsonl.
    pub async fn append_event(&self, event: &impl Serialize) -> Result<()> {
        use tokio::io::AsyncWriteExt;
        let path = self.root.join("events.jsonl");
        let mut file = tokio::fs::OpenOptions::new()
            .create(true)
            .append(true)
            .open(&path).await?;
        let json = serde_json::to_string(event)?;
        file.write_all(format!("{json}\n").as_bytes()).await?;
        Ok(())
    }

    /// Create an attempt directory and return the path.
    pub async fn create_attempt(&self, attempt: u8) -> Result<PathBuf> {
        let dir = self.root.join(format!("attempt-{attempt:02}"));
        tokio::fs::create_dir_all(&dir).await?;
        Ok(dir)
    }

    /// Save a candidate artifact for an attempt.
    pub async fn save_candidate(&self, attempt: u8, content: &str) -> Result<PathBuf> {
        let dir = self.create_attempt(attempt).await?;
        let path = dir.join("candidate");
        tokio::fs::write(&path, content).await?;
        Ok(path)
    }

    /// Save the final artifact.
    pub async fn save_final_artifact(&self, content: &str) -> Result<PathBuf> {
        let path = self.root.join("final-artifact");
        tokio::fs::write(&path, content).await?;

        // Also save the hash
        let hash = sha256_hex(content.as_bytes());
        tokio::fs::write(self.root.join("final-artifact.sha256"), &hash).await?;

        Ok(path)
    }

    /// Refresh the final snapshot of a deterministically verified code workspace.
    pub async fn save_final_workspace(&self, source: &Path) -> Result<PathBuf> {
        let source = source.to_path_buf();
        let target = self.root.join("final-workspace");
        let copy_target = target.clone();
        tokio::task::spawn_blocking(move || -> Result<()> {
            if copy_target.exists() {
                std::fs::remove_dir_all(&copy_target)?;
            }
            std::fs::create_dir_all(&copy_target)?;
            copy_workspace_directory(&source, &copy_target)
        })
        .await??;
        Ok(target)
    }

    /// Save the run summary.
    pub async fn save_summary(&self, summary: &impl Serialize) -> Result<()> {
        let path = self.root.join("summary.json");
        let json = serde_json::to_string_pretty(summary)?;
        tokio::fs::write(&path, &json).await?;
        Ok(())
    }

    /// Save arbitrary JSON to an attempt directory.
    pub async fn save_attempt_file(
        &self,
        attempt: u8,
        filename: &str,
        content: &impl Serialize,
    ) -> Result<()> {
        let dir = self.create_attempt(attempt).await?;
        let path = dir.join(filename);
        let json = serde_json::to_string_pretty(content)?;
        tokio::fs::write(&path, &json).await?;
        Ok(())
    }

    /// Save arbitrary string content to an attempt directory.
    pub async fn save_attempt_raw(&self, attempt: u8, filename: &str, content: &str) -> Result<PathBuf> {
        let dir = self.create_attempt(attempt).await?;
        let path = dir.join(filename);
        if let Some(p) = path.parent() {
            tokio::fs::create_dir_all(p).await?;
        }
        tokio::fs::write(&path, content).await?;
        Ok(path)
    }
}

fn copy_workspace_directory(source: &Path, target: &Path) -> Result<()> {
    for entry in std::fs::read_dir(source)? {
        let entry = entry?;
        let name = entry.file_name();
        let name_text = name.to_string_lossy();
        if matches!(name_text.as_ref(), ".git" | "target" | "node_modules") {
            continue;
        }
        let metadata = std::fs::symlink_metadata(entry.path())?;
        if metadata.file_type().is_symlink() {
            continue;
        }
        let destination = target.join(&name);
        if metadata.is_dir() {
            std::fs::create_dir_all(&destination)?;
            copy_workspace_directory(&entry.path(), &destination)?;
        } else if metadata.is_file() {
            std::fs::copy(entry.path(), destination)?;
        }
    }
    Ok(())
}

/// Compute SHA-256 hex digest of bytes.
pub fn sha256_hex(data: &[u8]) -> String {
    let mut hasher = Sha256::new();
    hasher.update(data);
    format!("{:x}", hasher.finalize())
}

#[cfg(test)]
mod tests {
    use super::*;

    #[tokio::test]
    async fn workspace_snapshot_refreshes_and_excludes_build_cache() {
        let test_root = std::env::temp_dir().join(format!(
            "klintcode-artifact-test-{}-{}",
            std::process::id(),
            chrono::Utc::now().timestamp_micros()
        ));
        let source = test_root.join("source");
        std::fs::create_dir_all(source.join("src")).expect("create source");
        std::fs::create_dir_all(source.join("target")).expect("create cache");
        std::fs::write(source.join("src/main.rs"), "fn main() {}\n").expect("write source");
        std::fs::write(source.join("target/cache"), "cache").expect("write cache");
        let artifacts = RunArtifacts::create(&test_root.join("runs"), "run-1")
            .await
            .expect("create artifacts");

        let snapshot = artifacts
            .save_final_workspace(&source)
            .await
            .expect("first snapshot");
        assert!(snapshot.join("src/main.rs").is_file());
        assert!(!snapshot.join("target").exists());

        std::fs::write(snapshot.join("stale.rs"), "stale").expect("write stale file");
        std::fs::write(source.join("src/main.rs"), "fn main() { println!(\"new\"); }\n")
            .expect("update source");
        artifacts
            .save_final_workspace(&source)
            .await
            .expect("refresh snapshot");

        assert!(!snapshot.join("stale.rs").exists());
        assert!(
            std::fs::read_to_string(snapshot.join("src/main.rs"))
                .expect("read snapshot")
                .contains("println!")
        );
        std::fs::remove_dir_all(test_root).expect("remove test data");
    }
}
