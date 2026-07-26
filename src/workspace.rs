//! Workspace state management — file tree, active files, etc.

use std::path::{Path, PathBuf};
use serde::{Deserialize, Serialize};

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct FileNode {
    pub name: String,
    pub path: PathBuf,
    pub is_dir: bool,
    pub children: Vec<FileNode>,
    pub expanded: bool,
}

pub struct WorkspaceState {
    pub root: PathBuf,
    pub file_tree: Option<FileNode>,
    pub active_file: Option<PathBuf>,
    pub file_content: Option<String>,
    pub tree_selection: usize,
    pub tree_scroll: u16,
}

impl WorkspaceState {
    pub fn new(root: PathBuf) -> Self {
        let file_tree = build_file_tree(&root, 3).ok();
        Self {
            root,
            file_tree,
            active_file: None,
            file_content: None,
            tree_selection: 0,
            tree_scroll: 0,
        }
    }

    pub fn refresh(&mut self) {
        self.file_tree = build_file_tree(&self.root, 3).ok();
    }

    pub fn open_file(&mut self, path: &Path) -> anyhow::Result<()> {
        let content = std::fs::read_to_string(path)?;
        self.active_file = Some(path.to_path_buf());
        self.file_content = Some(content);
        Ok(())
    }

    /// Flatten tree for display
    pub fn flatten_tree(&self) -> Vec<(usize, &FileNode)> {
        let mut items = Vec::new();
        if let Some(ref tree) = self.file_tree {
            flatten_recursive(tree, 0, &mut items);
        }
        items
    }
}

fn flatten_recursive<'a>(node: &'a FileNode, depth: usize, items: &mut Vec<(usize, &'a FileNode)>) {
    items.push((depth, node));
    if node.is_dir && node.expanded {
        for child in &node.children {
            flatten_recursive(child, depth + 1, items);
        }
    }
}

fn build_file_tree(path: &Path, max_depth: usize) -> anyhow::Result<FileNode> {
    build_tree_recursive(path, 0, max_depth)
}

fn build_tree_recursive(path: &Path, depth: usize, max_depth: usize) -> anyhow::Result<FileNode> {
    let name = path.file_name()
        .map(|n| n.to_string_lossy().to_string())
        .unwrap_or_else(|| path.display().to_string());

    let is_dir = path.is_dir();
    let mut children = Vec::new();

    if is_dir && depth < max_depth {
        if let Ok(entries) = std::fs::read_dir(path) {
            let mut sorted_entries: Vec<_> = entries
                .filter_map(|e| e.ok())
                .filter(|e| {
                    let name = e.file_name().to_string_lossy().to_string();
                    !name.starts_with('.') && name != "target" && name != "node_modules"
                })
                .collect();
            sorted_entries.sort_by(|a, b| {
                let a_dir = a.path().is_dir();
                let b_dir = b.path().is_dir();
                b_dir.cmp(&a_dir).then_with(|| a.file_name().cmp(&b.file_name()))
            });
            for entry in sorted_entries {
                if let Ok(child) = build_tree_recursive(&entry.path(), depth + 1, max_depth) {
                    children.push(child);
                }
            }
        }
    }

    Ok(FileNode {
        name,
        path: path.to_path_buf(),
        is_dir,
        children,
        expanded: depth == 0, // Only expand root by default
    })
}
