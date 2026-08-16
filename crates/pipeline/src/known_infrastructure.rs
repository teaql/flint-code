//! Detection for known framework/runtime incompatibilities in generated workspaces.
//!
//! This module deliberately inspects only workspace manifests, the lockfile, and
//! copied KSML files. Generated library source is neither needed nor permitted.

use agent_core::event::INFRASTRUCTURE_FAILURE_PREFIX;
use anyhow::{Context, Result, bail};
use quick_xml::Reader;
use quick_xml::events::{BytesStart, Event};
use std::collections::BTreeSet;
use std::path::{Path, PathBuf};
use tool_runner::remote_protocol::{ErrorCode, FileKind};
use tool_runner::ssh_backend::SshBackendError;

/// Stable identifier for the TeaQL 4.2.5 SQLite boolean decoding defect.
/// Fixed in teaql-provider-sqlite 4.2.7. The TUI automatically patches
/// generated workspaces to 4.2.7 via set_patches(), so this detector only
/// fires when a workspace still resolves 4.2.5 (pinned lockfile, un-patched
/// benchmark case, etc.).
pub const SQLITE_BOOL_PROVIDER_425_CODE: &str = "TEAQL-SQLITE-BOOL-4.2.5";

const AFFECTED_SQLITE_PROVIDER_VERSION: &str = "4.2.5";

/// A named in-memory source used by the content-level detector.
#[derive(Debug, Clone, Copy)]
pub struct TextSource<'a> {
    pub name: &'a str,
    pub content: &'a str,
}

impl<'a> TextSource<'a> {
    pub const fn new(name: &'a str, content: &'a str) -> Self {
        Self { name, content }
    }
}

/// A framework failure that application or model changes cannot repair.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct KnownInfrastructureFailure {
    pub code: &'static str,
    pub provider_version: String,
    pub boolean_fields: Vec<String>,
}

impl KnownInfrastructureFailure {
    /// Actionable validation error. The prefix makes the reducer stop instead
    /// of sending the failure back to the model for another repair attempt.
    pub fn actionable_error(&self) -> String {
        format!(
            "{INFRASTRUCTURE_FAILURE_PREFIX} [{}] generated SQLite workspace uses affected \
             teaql-provider-sqlite {}; model repair cannot fix this runtime incompatibility",
            self.code, self.provider_version
        )
    }

    /// Full evidence and remediation guidance for logs and reports.
    pub fn diagnostic(&self) -> String {
        let fields = summarize_fields(&self.boolean_fields);
        format!(
            "{}\n\
             Evidence: the KSML model selects SQLite and declares boolean field(s): {fields}. \
             teaql-provider-sqlite {} declares those columns as INTEGER and returns SQLite 0/1 \
             values as I64, while the generated bool mapping requires a Bool value. Queries can \
             therefore fail with 'invalid field ...: I64(1)'. Upgrade teaql-provider-sqlite and \
             teaql-macros together to a release containing SQLite boolean round-trip compatibility. \
             Do not inspect or patch generated library source under lib/src.",
            self.actionable_error(),
            self.provider_version
        )
    }
}

/// Inspect a generated workspace using only Cargo.lock, known Cargo.toml
/// locations, and XML files below model/.
pub fn detect_generated_workspace_infrastructure_failure(
    workspace_root: &Path,
) -> Result<Option<KnownInfrastructureFailure>> {
    let lock_path = workspace_root.join("Cargo.lock");
    let lock_content = read_optional_file(&lock_path)?;
    let lock_source = lock_content
        .as_deref()
        .map(|content| TextSource::new("Cargo.lock", content));

    // Generated Rust application workspaces currently have the application
    // manifest at the root and the generated domain manifest under lib/.
    // Keeping this list explicit guarantees that this detector never walks or
    // reads generated source directories.
    let manifest_paths = [
        workspace_root.join("Cargo.toml"),
        workspace_root.join("lib/Cargo.toml"),
    ];
    let mut manifest_contents = Vec::new();
    for path in manifest_paths {
        if let Some(content) = read_optional_file(&path)? {
            manifest_contents.push((path, content));
        }
    }
    let manifest_names: Vec<_> = manifest_contents
        .iter()
        .map(|(path, _)| path.to_string_lossy().into_owned())
        .collect();
    let manifest_sources: Vec<_> = manifest_contents
        .iter()
        .zip(&manifest_names)
        .map(|((_, content), name)| TextSource::new(name, content))
        .collect();

    let model_dir = workspace_root.join("model");
    let model_paths = collect_xml_files(&model_dir)?;
    let mut model_contents = Vec::with_capacity(model_paths.len());
    for path in model_paths {
        let content = std::fs::read_to_string(&path)
            .with_context(|| format!("failed to read KSML model {}", path.display()))?;
        model_contents.push((path, content));
    }
    let model_names: Vec<_> = model_contents
        .iter()
        .map(|(path, _)| path.to_string_lossy().into_owned())
        .collect();
    let model_sources: Vec<_> = model_contents
        .iter()
        .zip(&model_names)
        .map(|((_, content), name)| TextSource::new(name, content))
        .collect();

    detect_known_infrastructure_failure_from_sources(lock_source, &manifest_sources, &model_sources)
}

/// Inspect an authoritative SSH workspace without reading generated library
/// source. Only explicit manifests, the lockfile, and copied model XML are
/// transferred to the control plane for pure content analysis.
pub async fn detect_generated_workspace_infrastructure_failure_remote(
    execution: &crate::execution::RemoteExecution,
    workspace_root: &str,
) -> Result<Option<KnownInfrastructureFailure>> {
    let lock_path = remote_join(workspace_root, "Cargo.lock");
    let lock_content = remote_read_optional(execution, &lock_path).await?;
    let lock_source = lock_content
        .as_deref()
        .map(|content| TextSource::new("Cargo.lock", content));

    let manifest_paths = [
        remote_join(workspace_root, "Cargo.toml"),
        remote_join(workspace_root, "lib/Cargo.toml"),
    ];
    let mut manifest_contents = Vec::new();
    for path in manifest_paths {
        if let Some(content) = remote_read_optional(execution, &path).await? {
            manifest_contents.push((path, content));
        }
    }
    let manifest_sources = manifest_contents
        .iter()
        .map(|(path, content)| TextSource::new(path, content))
        .collect::<Vec<_>>();

    let model_root = remote_join(workspace_root, "model");
    let model_paths = collect_remote_xml_files(execution, &model_root).await?;
    let mut model_contents = Vec::with_capacity(model_paths.len());
    for path in model_paths {
        let content = execution
            .read_text(path.clone())
            .await
            .map_err(|error| anyhow::anyhow!("failed to read remote KSML model {path}: {error}"))?;
        model_contents.push((path, content));
    }
    let model_sources = model_contents
        .iter()
        .map(|(path, content)| TextSource::new(path, content))
        .collect::<Vec<_>>();

    detect_known_infrastructure_failure_from_sources(lock_source, &manifest_sources, &model_sources)
}

async fn remote_read_optional(
    execution: &crate::execution::RemoteExecution,
    path: &str,
) -> Result<Option<String>> {
    match execution.read_text(path.to_string()).await {
        Ok(content) => Ok(Some(content)),
        Err(crate::execution::RemoteExecutionError::Backend(SshBackendError::RemoteRejected {
            code: ErrorCode::NotFound,
            ..
        })) => Ok(None),
        Err(error) => Err(anyhow::anyhow!(
            "failed to read remote workspace file {path}: {error}"
        )),
    }
}

async fn collect_remote_xml_files(
    execution: &crate::execution::RemoteExecution,
    model_root: &str,
) -> Result<Vec<String>> {
    let mut directories = vec![model_root.to_string()];
    let mut files = Vec::new();
    while let Some(directory) = directories.pop() {
        let listing = execution
            .list(directory.clone(), None)
            .await
            .map_err(|error| {
                anyhow::anyhow!("failed to list remote model directory {directory}: {error}")
            })?;
        if listing.truncated {
            bail!("remote model directory listing was truncated: {directory}");
        }
        for entry in listing.entries {
            let path = remote_join(&directory, &entry.name);
            match entry.kind {
                FileKind::Directory => directories.push(path),
                FileKind::File
                    if entry
                        .name
                        .rsplit_once('.')
                        .is_some_and(|(_, extension)| extension.eq_ignore_ascii_case("xml")) =>
                {
                    files.push(path);
                }
                FileKind::Symlink => {
                    bail!("remote model directory contains a symlink: {path}")
                }
                _ => {}
            }
        }
    }
    files.sort();
    Ok(files)
}

fn remote_join(root: &str, relative: &str) -> String {
    let root = root.trim_end_matches('/');
    let relative = relative.trim_start_matches('/');
    if root.is_empty() || root == "." {
        relative.to_string()
    } else {
        format!("{root}/{relative}")
    }
}

/// Content-level detector for callers that already hold generated artifacts.
pub fn detect_known_infrastructure_failure_from_sources(
    cargo_lock: Option<TextSource<'_>>,
    cargo_manifests: &[TextSource<'_>],
    ksml_files: &[TextSource<'_>],
) -> Result<Option<KnownInfrastructureFailure>> {
    if !uses_affected_sqlite_provider(cargo_lock, cargo_manifests)? {
        return Ok(None);
    }

    let model = inspect_ksml_files(ksml_files)?;
    if !model.uses_sqlite || model.boolean_fields.is_empty() {
        return Ok(None);
    }

    Ok(Some(KnownInfrastructureFailure {
        code: SQLITE_BOOL_PROVIDER_425_CODE,
        provider_version: AFFECTED_SQLITE_PROVIDER_VERSION.to_string(),
        boolean_fields: model.boolean_fields.into_iter().collect(),
    }))
}

fn read_optional_file(path: &Path) -> Result<Option<String>> {
    match std::fs::read_to_string(path) {
        Ok(content) => Ok(Some(content)),
        Err(error) if error.kind() == std::io::ErrorKind::NotFound => Ok(None),
        Err(error) => Err(error).with_context(|| format!("failed to read {}", path.display())),
    }
}

fn collect_xml_files(model_dir: &Path) -> Result<Vec<PathBuf>> {
    if !model_dir.is_dir() {
        bail!(
            "generated workspace model directory is missing: {}",
            model_dir.display()
        );
    }

    fn visit(directory: &Path, files: &mut Vec<PathBuf>) -> Result<()> {
        for entry in std::fs::read_dir(directory)
            .with_context(|| format!("failed to read model directory {}", directory.display()))?
        {
            let entry = entry?;
            let file_type = entry.file_type()?;
            let path = entry.path();
            if file_type.is_dir() {
                visit(&path, files)?;
            } else if file_type.is_file()
                && path
                    .extension()
                    .and_then(|extension| extension.to_str())
                    .is_some_and(|extension| extension.eq_ignore_ascii_case("xml"))
            {
                files.push(path);
            }
            // Symlinks are intentionally ignored so model discovery cannot
            // escape into generated source or another workspace directory.
        }
        Ok(())
    }

    let mut files = Vec::new();
    visit(model_dir, &mut files)?;
    files.sort();
    Ok(files)
}

fn uses_affected_sqlite_provider(
    cargo_lock: Option<TextSource<'_>>,
    cargo_manifests: &[TextSource<'_>],
) -> Result<bool> {
    if let Some(lock) = cargo_lock {
        let versions = sqlite_provider_versions_from_lock(lock)?;
        if !versions.is_empty() {
            return Ok(versions
                .iter()
                .any(|version| version == AFFECTED_SQLITE_PROVIDER_VERSION));
        }
    }

    for manifest in cargo_manifests {
        let value: toml::Value = toml::from_str(manifest.content)
            .with_context(|| format!("failed to parse Cargo manifest {}", manifest.name))?;
        if manifest_declares_affected_provider(&value) {
            return Ok(true);
        }
    }
    Ok(false)
}

fn sqlite_provider_versions_from_lock(source: TextSource<'_>) -> Result<Vec<String>> {
    let value: toml::Value = toml::from_str(source.content)
        .with_context(|| format!("failed to parse Cargo lockfile {}", source.name))?;
    let mut versions = value
        .get("package")
        .and_then(toml::Value::as_array)
        .into_iter()
        .flatten()
        .filter(|package| {
            package.get("name").and_then(toml::Value::as_str) == Some("teaql-provider-sqlite")
        })
        .filter_map(|package| {
            package
                .get("version")
                .and_then(toml::Value::as_str)
                .map(str::to_owned)
        })
        .collect::<Vec<_>>();
    versions.sort();
    versions.dedup();
    Ok(versions)
}

fn manifest_declares_affected_provider(value: &toml::Value) -> bool {
    let Some(table) = value.as_table() else {
        return false;
    };

    for (key, child) in table {
        if matches!(
            key.as_str(),
            "dependencies" | "dev-dependencies" | "build-dependencies"
        ) && dependency_table_declares_affected_provider(child)
        {
            return true;
        }
        if manifest_declares_affected_provider(child) {
            return true;
        }
    }
    false
}

fn dependency_table_declares_affected_provider(value: &toml::Value) -> bool {
    value.as_table().is_some_and(|dependencies| {
        dependencies.iter().any(|(dependency_name, declaration)| {
            let package_name = declaration
                .as_table()
                .and_then(|table| table.get("package"))
                .and_then(toml::Value::as_str)
                .unwrap_or(dependency_name);
            if package_name != "teaql-provider-sqlite" {
                return false;
            }

            let requirement = declaration.as_str().or_else(|| {
                declaration
                    .as_table()
                    .and_then(|table| table.get("version"))
                    .and_then(toml::Value::as_str)
            });
            requirement.is_some_and(requirement_mentions_affected_version)
        })
    })
}

fn requirement_mentions_affected_version(requirement: &str) -> bool {
    let Some(affected) = parse_numeric_version(AFFECTED_SQLITE_PROVIDER_VERSION) else {
        return false;
    };
    requirement.split("||").any(|alternative| {
        let comparators = alternative
            .split(',')
            .flat_map(str::split_whitespace)
            .filter(|part| !part.is_empty())
            .collect::<Vec<_>>();
        !comparators.is_empty()
            && comparators
                .into_iter()
                .all(|comparator| comparator_allows_version(comparator, affected))
    })
}

fn parse_numeric_version(value: &str) -> Option<(u64, u64, u64)> {
    let mut components = value
        .trim()
        .trim_start_matches('v')
        .split('.')
        .map(|component| component.parse::<u64>().ok());
    Some((
        components.next().flatten()?,
        components.next().flatten().unwrap_or(0),
        components.next().flatten().unwrap_or(0),
    ))
}

fn comparator_allows_version(comparator: &str, target: (u64, u64, u64)) -> bool {
    let comparator = comparator.trim();
    for (operator, predicate) in [
        (">=", fn_ge as fn(_, _) -> _),
        ("<=", fn_le),
        (">", fn_gt),
        ("<", fn_lt),
        ("=", fn_eq),
    ] {
        if let Some(version) = comparator.strip_prefix(operator) {
            return parse_numeric_version(version)
                .is_some_and(|version| predicate(target, version));
        }
    }

    let (kind, version) = comparator
        .strip_prefix('^')
        .map(|value| ('^', value))
        .or_else(|| comparator.strip_prefix('~').map(|value| ('~', value)))
        .unwrap_or(('^', comparator));
    if version.contains('*') || version.to_ascii_lowercase().contains('x') {
        let prefix = version
            .split('.')
            .take_while(|component| !matches!(*component, "*" | "x" | "X"))
            .filter_map(|component| component.parse::<u64>().ok())
            .collect::<Vec<_>>();
        return prefix.get(0).is_none_or(|major| *major == target.0)
            && prefix.get(1).is_none_or(|minor| *minor == target.1)
            && prefix.get(2).is_none_or(|patch| *patch == target.2);
    }
    let Some(floor) = parse_numeric_version(version) else {
        return false;
    };
    if target < floor {
        return false;
    }
    match kind {
        '~' => target < (floor.0, floor.1.saturating_add(1), 0),
        '^' if floor.0 > 0 => target < (floor.0.saturating_add(1), 0, 0),
        '^' if floor.1 > 0 => target < (0, floor.1.saturating_add(1), 0),
        '^' => target < (0, 0, floor.2.saturating_add(1)),
        _ => false,
    }
}

fn fn_ge(left: (u64, u64, u64), right: (u64, u64, u64)) -> bool {
    left >= right
}
fn fn_le(left: (u64, u64, u64), right: (u64, u64, u64)) -> bool {
    left <= right
}
fn fn_gt(left: (u64, u64, u64), right: (u64, u64, u64)) -> bool {
    left > right
}
fn fn_lt(left: (u64, u64, u64), right: (u64, u64, u64)) -> bool {
    left < right
}
fn fn_eq(left: (u64, u64, u64), right: (u64, u64, u64)) -> bool {
    left == right
}

#[derive(Debug, Default)]
struct KsmlFeatures {
    uses_sqlite: bool,
    boolean_fields: BTreeSet<String>,
}

fn inspect_ksml_files(sources: &[TextSource<'_>]) -> Result<KsmlFeatures> {
    let mut features = KsmlFeatures::default();
    for source in sources {
        inspect_ksml(source, &mut features)?;
    }
    Ok(features)
}

fn inspect_ksml(source: &TextSource<'_>, features: &mut KsmlFeatures) -> Result<()> {
    let mut reader = Reader::from_str(source.content);
    reader.config_mut().check_end_names = true;
    let mut buffer = Vec::new();
    let mut depth = 0usize;

    loop {
        match reader.read_event_into(&mut buffer) {
            Ok(Event::Start(element)) => {
                inspect_ksml_element(&element, depth, features)
                    .with_context(|| format!("invalid KSML attributes in {}", source.name))?;
                depth += 1;
            }
            Ok(Event::Empty(element)) => {
                inspect_ksml_element(&element, depth, features)
                    .with_context(|| format!("invalid KSML attributes in {}", source.name))?;
            }
            Ok(Event::End(_)) => depth = depth.saturating_sub(1),
            Ok(Event::Eof) => break,
            Ok(_) => {}
            Err(error) => {
                bail!(
                    "failed to parse KSML {} at position {}: {error}",
                    source.name,
                    reader.error_position()
                );
            }
        }
        buffer.clear();
    }
    Ok(())
}

fn inspect_ksml_element(
    element: &BytesStart<'_>,
    depth: usize,
    features: &mut KsmlFeatures,
) -> Result<()> {
    let element_name = String::from_utf8_lossy(element.name().as_ref()).into_owned();
    let is_document_root = depth == 0;
    let is_business_object = depth == 1 && !element_name.starts_with('_');
    for attribute in element.attributes() {
        let attribute = attribute?;
        let key = String::from_utf8_lossy(attribute.key.as_ref());
        let value = String::from_utf8_lossy(attribute.value.as_ref());

        if is_document_root && key.eq_ignore_ascii_case("data_service") {
            features.uses_sqlite |= value.trim().eq_ignore_ascii_case("sqlite");
            continue;
        }
        if !is_business_object || key.starts_with('_') {
            continue;
        }
        if value.trim().eq_ignore_ascii_case("true") || value.trim().eq_ignore_ascii_case("false") {
            features
                .boolean_fields
                .insert(format!("{element_name}.{key}"));
        }
    }
    Ok(())
}

fn summarize_fields(fields: &[String]) -> String {
    const MAX_FIELDS: usize = 8;
    let shown = fields
        .iter()
        .take(MAX_FIELDS)
        .cloned()
        .collect::<Vec<_>>()
        .join(", ");
    if fields.len() > MAX_FIELDS {
        format!("{shown} (+{} more)", fields.len() - MAX_FIELDS)
    } else {
        shown
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    const AFFECTED_LOCK: &str = r#"
version = 4

[[package]]
name = "teaql-provider-sqlite"
version = "4.2.5"
"#;

    const OTHER_VERSION_LOCK: &str = r#"
version = 4

[[package]]
name = "teaql-provider-sqlite"
version = "4.2.6"
"#;

    const SQLITE_BOOL_MODEL: &str = r#"
<root data_service="sqlite" cfg_mask_china_mobile="false">
  <employee _name="Employee" is_active="true"/>
  <vehicle _name="Vehicle" is_available="false"/>
</root>
"#;

    #[test]
    fn detects_affected_sqlite_boolean_workspace_from_lockfile() {
        let failure = detect_known_infrastructure_failure_from_sources(
            Some(TextSource::new("Cargo.lock", AFFECTED_LOCK)),
            &[],
            &[TextSource::new("model/main.xml", SQLITE_BOOL_MODEL)],
        )
        .unwrap()
        .unwrap();

        assert_eq!(failure.code, SQLITE_BOOL_PROVIDER_425_CODE);
        assert_eq!(
            failure.boolean_fields,
            vec!["employee.is_active", "vehicle.is_available"]
        );
        assert!(
            failure
                .actionable_error()
                .starts_with(INFRASTRUCTURE_FAILURE_PREFIX)
        );
        assert!(failure.diagnostic().contains("invalid field ...: I64(1)"));
        assert!(failure.diagnostic().contains("Do not inspect or patch"));
    }

    #[test]
    fn resolved_lockfile_version_takes_precedence_over_manifest_range() {
        let failure = detect_known_infrastructure_failure_from_sources(
            Some(TextSource::new("Cargo.lock", OTHER_VERSION_LOCK)),
            &[TextSource::new(
                "lib/Cargo.toml",
                r#"
[dependencies]
teaql-provider-sqlite = "4.2.5"
"#,
            )],
            &[TextSource::new("model/main.xml", SQLITE_BOOL_MODEL)],
        )
        .unwrap();

        assert!(failure.is_none());
    }

    #[test]
    fn manifest_is_used_when_lockfile_is_absent() {
        let failure = detect_known_infrastructure_failure_from_sources(
            None,
            &[TextSource::new(
                "lib/Cargo.toml",
                r#"
[dependencies]
sqlite_runtime = { package = "teaql-provider-sqlite", version = "=4.2.5" }
"#,
            )],
            &[TextSource::new("model/main.xml", SQLITE_BOOL_MODEL)],
        )
        .unwrap();

        assert!(failure.is_some());
    }

    #[test]
    fn manifest_range_that_can_resolve_affected_version_is_detected_without_lockfile() {
        let failure = detect_known_infrastructure_failure_from_sources(
            None,
            &[TextSource::new(
                "lib/Cargo.toml",
                r#"
[dependencies]
teaql-provider-sqlite = "^4.2.4"
"#,
            )],
            &[TextSource::new("model/main.xml", SQLITE_BOOL_MODEL)],
        )
        .unwrap();

        assert!(failure.is_some());
    }

    #[test]
    fn ignores_non_sqlite_models_and_metadata_boolean_attributes() {
        let postgres = r#"<root data_service="postgres"><employee is_active="true"/></root>"#;
        let metadata_only = r#"
<root data_service="sqlite" cfg_mask_china_mobile="false">
  <status _constant="true" _log="false" code="ACTIVE">
    <_value name="true" unique="true"/>
  </status>
</root>
"#;

        for model in [postgres, metadata_only] {
            let failure = detect_known_infrastructure_failure_from_sources(
                Some(TextSource::new("Cargo.lock", AFFECTED_LOCK)),
                &[],
                &[TextSource::new("model/main.xml", model)],
            )
            .unwrap();
            assert!(failure.is_none());
        }
    }

    #[test]
    fn malformed_ksml_is_reported() {
        let error = detect_known_infrastructure_failure_from_sources(
            Some(TextSource::new("Cargo.lock", AFFECTED_LOCK)),
            &[],
            &[TextSource::new(
                "model/main.xml",
                r#"<root data_service="sqlite"><employee is_active="true"></root>"#,
            )],
        )
        .unwrap_err();

        assert!(error.to_string().contains("failed to parse KSML"));
    }

    #[test]
    fn filesystem_detector_reads_only_explicit_manifests_and_model_xml() {
        let workspace = tempfile::tempdir().unwrap();
        std::fs::create_dir_all(workspace.path().join("lib/src")).unwrap();
        std::fs::create_dir_all(workspace.path().join("model/includes")).unwrap();
        std::fs::write(workspace.path().join("Cargo.lock"), AFFECTED_LOCK).unwrap();
        std::fs::write(workspace.path().join("Cargo.toml"), "[workspace]").unwrap();
        std::fs::write(
            workspace.path().join("lib/Cargo.toml"),
            r#"
[dependencies]
teaql-provider-sqlite = "4.2.5"
"#,
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("model/main.xml"),
            r#"<root data_service="sqlite"><_include file="includes/domain.xml"/></root>"#,
        )
        .unwrap();
        std::fs::write(
            workspace.path().join("model/includes/domain.xml"),
            r#"<root><employee is_active="true"/></root>"#,
        )
        .unwrap();
        let failure = detect_generated_workspace_infrastructure_failure(workspace.path())
            .unwrap()
            .unwrap();

        assert_eq!(failure.boolean_fields, vec!["employee.is_active"]);
    }
}
