//! Minimal installer and launcher for content-addressed FlintCode runners.
//!
//! The bootstrap deliberately does not invoke a shell. It accepts runner bytes
//! on standard input, verifies them before publishing them in the cache, and
//! directly replaces itself with the selected runner when launching on Unix.

use anyhow::{Context, Result, bail};
use serde_json::json;
use sha2::{Digest, Sha256};
use std::env;
use std::ffi::{OsStr, OsString};
use std::fmt::Write as _;
use std::fs::{self, File, OpenOptions};
use std::io::{self, Read, Write};
use std::path::{Component, Path, PathBuf};
use std::process::{Command, Stdio};

const ROOT_ENV: &str = "KLINTCODE_RUNNER_CACHE_ROOT";
const RUNNER_FILE: &str = "klintcode-runner";

fn main() {
    if let Err(error) = run() {
        let message = format!("{error:#}");
        eprintln!(
            "{}",
            serde_json::to_string(&json!({
                "ok": false,
                "error": message,
            }))
            .expect("error response is JSON serializable")
        );
        std::process::exit(2);
    }
}

fn run() -> Result<()> {
    let request = parse_args(env::args_os().skip(1))?;
    let root = resolve_cache_root(request.root.as_deref())?;

    match request.command {
        BootstrapCommand::Check { sha256 } => {
            let cached = check_cached_runner(&root, &sha256)?;
            println!(
                "{}",
                serde_json::to_string(&json!({
                    "ok": true,
                    "operation": "check",
                    "sha256": sha256,
                    "path": cached.path.to_string_lossy(),
                    "present": cached.present,
                    "bytes": cached.bytes,
                }))?
            );
            Ok(())
        }
        BootstrapCommand::Install { sha256 } => {
            let stdin = io::stdin();
            let installed = install_from_reader(&root, &sha256, stdin.lock())?;
            println!(
                "{}",
                serde_json::to_string(&json!({
                    "ok": true,
                    "operation": "install",
                    "sha256": sha256,
                    "path": installed.path.to_string_lossy(),
                    "cached": installed.cached,
                    "bytes": installed.bytes,
                }))?
            );
            Ok(())
        }
        BootstrapCommand::Launch { sha256, args } => {
            let exit_code = launch(&root, &sha256, &args)?;
            std::process::exit(exit_code);
        }
    }
}

#[derive(Debug)]
struct BootstrapRequest {
    root: Option<PathBuf>,
    command: BootstrapCommand,
}

#[derive(Debug)]
enum BootstrapCommand {
    Check { sha256: String },
    Install { sha256: String },
    Launch { sha256: String, args: Vec<OsString> },
}

fn parse_args<I>(args: I) -> Result<BootstrapRequest>
where
    I: IntoIterator<Item = OsString>,
{
    let mut args = args.into_iter();
    let mut next = args.next().context(usage())?;
    let root = if next == OsStr::new("--root") {
        let root = args.next().context("--root requires an absolute path")?;
        next = args.next().context(usage())?;
        Some(PathBuf::from(root))
    } else {
        None
    };

    let command = next.to_str().context("command must be valid UTF-8")?;
    let hash_flag = args.next().context("missing --sha256")?;
    if hash_flag != OsStr::new("--sha256") {
        bail!("expected --sha256 immediately after {command}");
    }
    let sha256 = args
        .next()
        .context("--sha256 requires a hexadecimal digest")?;
    let sha256 = sha256
        .to_str()
        .context("SHA-256 digest must be valid UTF-8")?;
    let sha256 = validate_digest(sha256)?;

    let command = match command {
        "check" => {
            if args.next().is_some() {
                bail!("check does not accept trailing arguments");
            }
            BootstrapCommand::Check { sha256 }
        }
        "install" => {
            if args.next().is_some() {
                bail!("install does not accept trailing arguments");
            }
            BootstrapCommand::Install { sha256 }
        }
        "launch" => {
            let mut runner_args: Vec<OsString> = args.collect();
            if runner_args
                .first()
                .is_some_and(|arg| arg == OsStr::new("--"))
            {
                runner_args.remove(0);
            }
            BootstrapCommand::Launch {
                sha256,
                args: runner_args,
            }
        }
        other => bail!("unknown command {other:?}; {}", usage()),
    };

    Ok(BootstrapRequest { root, command })
}

fn usage() -> &'static str {
    "usage: klintcode-bootstrap [--root ABS_PATH] check --sha256 HEX | \
     klintcode-bootstrap [--root ABS_PATH] install --sha256 HEX | \
     klintcode-bootstrap [--root ABS_PATH] launch --sha256 HEX [--] [ARG ...]"
}

fn validate_digest(value: &str) -> Result<String> {
    if value.len() != 64 || !value.bytes().all(|byte| byte.is_ascii_hexdigit()) {
        bail!("SHA-256 digest must contain exactly 64 hexadecimal characters");
    }
    Ok(value.to_ascii_lowercase())
}

fn resolve_cache_root(cli_root: Option<&Path>) -> Result<PathBuf> {
    let root = if let Some(root) = cli_root {
        root.to_path_buf()
    } else if let Some(root) = env::var_os(ROOT_ENV) {
        PathBuf::from(root)
    } else if let Some(cache) = env::var_os("XDG_CACHE_HOME") {
        PathBuf::from(cache).join("klintcode/runners")
    } else if let Some(home) = env::var_os("HOME") {
        PathBuf::from(home).join(".cache/klintcode/runners")
    } else if let Some(cache) = env::var_os("LOCALAPPDATA") {
        PathBuf::from(cache).join("KlintCode/cache/runners")
    } else {
        bail!("cannot determine the user cache directory; set {ROOT_ENV} or pass --root");
    };

    validate_root_path(&root)?;
    Ok(root)
}

fn validate_root_path(root: &Path) -> Result<()> {
    if !root.is_absolute() {
        bail!("runner cache root must be an absolute path");
    }
    for component in root.components() {
        if matches!(component, Component::ParentDir | Component::CurDir) {
            bail!("runner cache root must not contain '.' or '..' components");
        }
    }
    Ok(())
}

#[derive(Debug)]
struct InstallResult {
    path: PathBuf,
    cached: bool,
    bytes: u64,
}

#[derive(Debug)]
struct CacheCheck {
    path: PathBuf,
    present: bool,
    bytes: Option<u64>,
}

fn check_cached_runner(root: &Path, expected_digest: &str) -> Result<CacheCheck> {
    validate_root_path(root)?;
    let expected_digest = validate_digest(expected_digest)?;
    let root_metadata = match fs::symlink_metadata(root) {
        Ok(metadata) => metadata,
        Err(error) if error.kind() == io::ErrorKind::NotFound => {
            return Ok(CacheCheck {
                path: root.join(expected_digest).join(RUNNER_FILE),
                present: false,
                bytes: None,
            });
        }
        Err(error) => {
            return Err(error)
                .with_context(|| format!("inspect runner cache root {}", root.display()));
        }
    };
    if root_metadata.file_type().is_symlink() || !root_metadata.is_dir() {
        bail!("runner cache root must be a real directory, not a symlink");
    }

    let canonical_root = root
        .canonicalize()
        .with_context(|| format!("canonicalize runner cache root {}", root.display()))?;
    let digest_directory = canonical_root.join(&expected_digest);
    let digest_metadata = match fs::symlink_metadata(&digest_directory) {
        Ok(metadata) => metadata,
        Err(error) if error.kind() == io::ErrorKind::NotFound => {
            return Ok(CacheCheck {
                path: digest_directory.join(RUNNER_FILE),
                present: false,
                bytes: None,
            });
        }
        Err(error) => {
            return Err(error).with_context(|| {
                format!(
                    "inspect runner digest directory {}",
                    digest_directory.display()
                )
            });
        }
    };
    if digest_metadata.file_type().is_symlink() || !digest_metadata.is_dir() {
        bail!("runner digest path must be a real directory, not a symlink");
    }

    let runner = digest_directory.join(RUNNER_FILE);
    let metadata = match fs::symlink_metadata(&runner) {
        Ok(metadata) => metadata,
        Err(error) if error.kind() == io::ErrorKind::NotFound => {
            return Ok(CacheCheck {
                path: runner,
                present: false,
                bytes: None,
            });
        }
        Err(error) => {
            return Err(error)
                .with_context(|| format!("inspect installed runner {}", runner.display()));
        }
    };

    if metadata.file_type().is_symlink() || !metadata.is_file() {
        bail!("installed runner must be a regular file, not a symlink");
    }
    verify_runner(&runner, &expected_digest)?;
    Ok(CacheCheck {
        path: runner,
        present: true,
        bytes: Some(metadata.len()),
    })
}

fn install_from_reader<R: Read>(
    root: &Path,
    expected_digest: &str,
    mut source: R,
) -> Result<InstallResult> {
    let expected_digest = validate_digest(expected_digest)?;
    let root = prepare_root(root)?;
    let digest_dir = prepare_digest_dir(&root, &expected_digest)?;
    let target = digest_dir.join(RUNNER_FILE);
    let (temp_path, mut temp) = create_temp_file(&digest_dir)?;
    let mut cleanup = PartialFile::new(temp_path.clone());

    let mut hasher = Sha256::new();
    let mut bytes = 0_u64;
    let mut buffer = [0_u8; 64 * 1024];
    loop {
        let count = match source.read(&mut buffer) {
            Ok(0) => break,
            Ok(count) => count,
            Err(error) if error.kind() == io::ErrorKind::Interrupted => continue,
            Err(error) => return Err(error).context("read runner bytes from stdin"),
        };
        temp.write_all(&buffer[..count])
            .context("write temporary runner")?;
        hasher.update(&buffer[..count]);
        bytes = bytes
            .checked_add(count as u64)
            .context("runner input is too large")?;
    }

    if bytes == 0 {
        bail!("runner input is empty");
    }
    let actual_digest = digest_hex(&hasher.finalize());
    if actual_digest != expected_digest {
        bail!("runner SHA-256 mismatch: expected {expected_digest}, received {actual_digest}");
    }

    set_runner_permissions(&temp_path)?;
    temp.sync_all().context("fsync temporary runner")?;
    drop(temp);

    if target.try_exists().context("inspect cached runner")? {
        match verify_runner(&target, &expected_digest) {
            Ok(()) => {
                set_runner_permissions(&target)?;
                return Ok(InstallResult {
                    path: target,
                    cached: true,
                    bytes,
                });
            }
            Err(_) => {
                // A corrupt cache entry is atomically replaced below. The newly
                // uploaded file has already been verified against its address.
            }
        }
    }

    fs::rename(&temp_path, &target).with_context(|| {
        format!(
            "atomically publish runner {} as {}",
            temp_path.display(),
            target.display()
        )
    })?;
    cleanup.disarm();
    set_runner_permissions(&target)?;
    sync_directory(&digest_dir)?;
    verify_runner(&target, &expected_digest)?;

    Ok(InstallResult {
        path: target,
        cached: false,
        bytes,
    })
}

fn prepare_root(root: &Path) -> Result<PathBuf> {
    validate_root_path(root)?;
    fs::create_dir_all(root)
        .with_context(|| format!("create runner cache root {}", root.display()))?;
    let metadata = fs::symlink_metadata(root)
        .with_context(|| format!("inspect runner cache root {}", root.display()))?;
    if metadata.file_type().is_symlink() || !metadata.is_dir() {
        bail!("runner cache root must be a real directory, not a symlink");
    }
    set_private_directory_permissions(root)?;
    root.canonicalize()
        .with_context(|| format!("canonicalize runner cache root {}", root.display()))
}

fn prepare_digest_dir(root: &Path, digest: &str) -> Result<PathBuf> {
    let digest = validate_digest(digest)?;
    let directory = root.join(digest);
    match fs::create_dir(&directory) {
        Ok(()) => sync_directory(root)?,
        Err(error) if error.kind() == io::ErrorKind::AlreadyExists => {}
        Err(error) => {
            return Err(error).with_context(|| {
                format!("create runner digest directory {}", directory.display())
            });
        }
    }
    let metadata = fs::symlink_metadata(&directory)
        .with_context(|| format!("inspect runner digest directory {}", directory.display()))?;
    if metadata.file_type().is_symlink() || !metadata.is_dir() {
        bail!("runner digest path must be a real directory, not a symlink");
    }
    set_private_directory_permissions(&directory)?;
    Ok(directory)
}

fn create_temp_file(directory: &Path) -> Result<(PathBuf, File)> {
    let process = std::process::id();
    for attempt in 0_u32..1024 {
        let candidate = directory.join(format!(".{RUNNER_FILE}.{process}.{attempt}.partial"));
        let mut options = OpenOptions::new();
        options.write(true).create_new(true);
        #[cfg(unix)]
        {
            use std::os::unix::fs::OpenOptionsExt;
            options.mode(0o700);
        }
        match options.open(&candidate) {
            Ok(file) => return Ok((candidate, file)),
            Err(error) if error.kind() == io::ErrorKind::AlreadyExists => continue,
            Err(error) => {
                return Err(error)
                    .with_context(|| format!("create temporary runner {}", candidate.display()));
            }
        }
    }
    bail!("unable to allocate a unique temporary runner path")
}

fn verify_runner(path: &Path, expected_digest: &str) -> Result<()> {
    let expected_digest = validate_digest(expected_digest)?;
    let metadata = fs::symlink_metadata(path)
        .with_context(|| format!("inspect installed runner {}", path.display()))?;
    if metadata.file_type().is_symlink() || !metadata.is_file() {
        bail!("installed runner must be a regular file, not a symlink");
    }
    let mut runner =
        File::open(path).with_context(|| format!("open installed runner {}", path.display()))?;
    let actual_digest = hash_reader(&mut runner)?;
    if actual_digest != expected_digest {
        bail!(
            "installed runner SHA-256 mismatch: expected {expected_digest}, found {actual_digest}"
        );
    }
    Ok(())
}

fn hash_reader<R: Read>(mut reader: R) -> Result<String> {
    let mut hasher = Sha256::new();
    let mut buffer = [0_u8; 64 * 1024];
    loop {
        let count = match reader.read(&mut buffer) {
            Ok(0) => break,
            Ok(count) => count,
            Err(error) if error.kind() == io::ErrorKind::Interrupted => continue,
            Err(error) => return Err(error).context("read runner for SHA-256 verification"),
        };
        hasher.update(&buffer[..count]);
    }
    Ok(digest_hex(&hasher.finalize()))
}

fn digest_hex(bytes: &[u8]) -> String {
    let mut output = String::with_capacity(bytes.len() * 2);
    for byte in bytes {
        write!(&mut output, "{byte:02x}").expect("writing to String cannot fail");
    }
    output
}

#[cfg(unix)]
fn set_runner_permissions(path: &Path) -> Result<()> {
    use std::os::unix::fs::PermissionsExt;
    fs::set_permissions(path, fs::Permissions::from_mode(0o700))
        .with_context(|| format!("set runner permissions on {}", path.display()))
}

#[cfg(unix)]
fn set_private_directory_permissions(path: &Path) -> Result<()> {
    use std::os::unix::fs::PermissionsExt;
    fs::set_permissions(path, fs::Permissions::from_mode(0o700))
        .with_context(|| format!("set private directory permissions on {}", path.display()))
}

#[cfg(not(unix))]
fn set_private_directory_permissions(_path: &Path) -> Result<()> {
    Ok(())
}

#[cfg(not(unix))]
fn set_runner_permissions(_path: &Path) -> Result<()> {
    Ok(())
}

fn sync_directory(path: &Path) -> Result<()> {
    File::open(path)
        .and_then(|directory| directory.sync_all())
        .with_context(|| format!("fsync directory {}", path.display()))
}

fn runner_path(root: &Path, digest: &str) -> Result<PathBuf> {
    let digest = validate_digest(digest)?;
    let root = prepare_root(root)?;
    let digest_directory = root.join(digest);
    let metadata = fs::symlink_metadata(&digest_directory).with_context(|| {
        format!(
            "inspect runner digest directory {}",
            digest_directory.display()
        )
    })?;
    if metadata.file_type().is_symlink() || !metadata.is_dir() {
        bail!("runner digest path must be a real directory, not a symlink");
    }
    Ok(digest_directory.join(RUNNER_FILE))
}

fn launch(root: &Path, expected_digest: &str, args: &[OsString]) -> Result<i32> {
    let runner = runner_path(root, expected_digest)?;
    verify_runner(&runner, expected_digest)?;

    let mut command = Command::new(&runner);
    command
        .args(args)
        .stdin(Stdio::inherit())
        .stdout(Stdio::inherit())
        .stderr(Stdio::inherit());

    #[cfg(unix)]
    {
        use std::os::unix::process::CommandExt;
        let error = command.exec();
        Err(error).with_context(|| format!("exec runner {}", runner.display()))
    }

    #[cfg(not(unix))]
    {
        let status = command
            .status()
            .with_context(|| format!("spawn runner {}", runner.display()))?;
        Ok(status.code().unwrap_or(1))
    }
}

struct PartialFile {
    path: PathBuf,
    armed: bool,
}

impl PartialFile {
    fn new(path: PathBuf) -> Self {
        Self { path, armed: true }
    }

    fn disarm(&mut self) {
        self.armed = false;
    }
}

impl Drop for PartialFile {
    fn drop(&mut self) {
        if self.armed {
            let _ = fs::remove_file(&self.path);
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::io::Cursor;
    use std::sync::atomic::{AtomicU64, Ordering};

    static NEXT_TEST_DIRECTORY: AtomicU64 = AtomicU64::new(0);

    struct TestDirectory(PathBuf);

    impl TestDirectory {
        fn new() -> Self {
            let sequence = NEXT_TEST_DIRECTORY.fetch_add(1, Ordering::Relaxed);
            let path = env::temp_dir().join(format!(
                "klintcode-bootstrap-test-{}-{sequence}",
                std::process::id()
            ));
            fs::create_dir(&path).expect("create isolated test directory");
            Self(path)
        }
    }

    impl Drop for TestDirectory {
        fn drop(&mut self) {
            let _ = fs::remove_dir_all(&self.0);
        }
    }

    fn sha256(bytes: &[u8]) -> String {
        let mut hasher = Sha256::new();
        hasher.update(bytes);
        digest_hex(&hasher.finalize())
    }

    #[test]
    fn installs_runner_in_content_addressed_cache() {
        let root = TestDirectory::new();
        let runner = b"a runner payload";
        let digest = sha256(runner);

        let result = install_from_reader(&root.0, &digest, Cursor::new(runner)).unwrap();

        assert!(!result.cached);
        assert_eq!(result.bytes, runner.len() as u64);
        assert_eq!(
            result.path,
            root.0
                .canonicalize()
                .unwrap()
                .join(&digest)
                .join(RUNNER_FILE)
        );
        assert_eq!(fs::read(&result.path).unwrap(), runner);
        verify_runner(&result.path, &digest).unwrap();

        #[cfg(unix)]
        {
            use std::os::unix::fs::PermissionsExt;
            assert_eq!(
                fs::metadata(&result.path).unwrap().permissions().mode() & 0o777,
                0o700
            );
        }
    }

    #[test]
    fn identical_install_uses_verified_cache_entry() {
        let root = TestDirectory::new();
        let runner = b"cacheable runner";
        let digest = sha256(runner);

        let first = install_from_reader(&root.0, &digest, Cursor::new(runner)).unwrap();
        let second = install_from_reader(&root.0, &digest, Cursor::new(runner)).unwrap();

        assert!(!first.cached);
        assert!(second.cached);
        assert_eq!(first.path, second.path);
        assert_eq!(fs::read(second.path).unwrap(), runner);
    }

    #[test]
    fn cache_check_reports_verified_presence_without_creating_missing_root() {
        let parent = TestDirectory::new();
        let root = parent.0.join("not-created-yet");
        let runner = b"checked runner";
        let digest = sha256(runner);

        let missing = check_cached_runner(&root, &digest).unwrap();
        assert!(!missing.present);
        assert_eq!(missing.bytes, None);
        assert!(!root.exists());

        let installed = install_from_reader(&root, &digest, Cursor::new(runner)).unwrap();
        let present = check_cached_runner(&root, &digest).unwrap();
        assert!(present.present);
        assert_eq!(present.path, installed.path);
        assert_eq!(present.bytes, Some(runner.len() as u64));
    }

    #[test]
    fn cache_check_rejects_corrupt_entry_instead_of_reporting_cache_miss() {
        let root = TestDirectory::new();
        let runner = b"original runner";
        let digest = sha256(runner);
        let installed = install_from_reader(&root.0, &digest, Cursor::new(runner)).unwrap();
        fs::write(installed.path, b"tampered runner").unwrap();

        let error = check_cached_runner(&root.0, &digest).unwrap_err();
        assert!(error.to_string().contains("SHA-256 mismatch"));
    }

    #[test]
    fn hash_mismatch_is_rejected_without_publishing_runner() {
        let root = TestDirectory::new();
        let expected = sha256(b"expected runner");

        let error =
            install_from_reader(&root.0, &expected, Cursor::new(b"different runner")).unwrap_err();

        assert!(error.to_string().contains("SHA-256 mismatch"));
        let digest_dir = root.0.join(expected);
        assert!(!digest_dir.join(RUNNER_FILE).exists());
        assert_eq!(fs::read_dir(digest_dir).unwrap().count(), 0);
    }

    #[test]
    fn digest_validation_rejects_path_like_values() {
        assert!(validate_digest("../runner").is_err());
        assert!(validate_digest(&"g".repeat(64)).is_err());
        assert!(validate_digest(&"a".repeat(63)).is_err());
    }
}
