//! Stdio entry point intended for an SSH forced command.

use anyhow::{Context, Result, bail};
use std::path::{Path, PathBuf};
use tool_runner::remote_protocol::HardPolicy;
use tool_runner::remote_runner::{RemoteRunner, RunnerConfig, serve_ndjson};

#[tokio::main]
async fn main() -> Result<()> {
    let options = Options::parse(std::env::args().skip(1))?;
    if options.help {
        eprintln!(
            "Usage: klintcode-runner [bridge|--stdio] [--root PATH] [--policy PATH]\n\
             Serves the versioned NDJSON protocol on stdin/stdout."
        );
        return Ok(());
    }
    let hard_policy = match options.policy {
        Some(path) => load_policy(&path)?,
        None => HardPolicy::default(),
    };
    let runner = RemoteRunner::new(RunnerConfig::new(options.root, hard_policy))
        .map_err(|error| anyhow::anyhow!("runner initialization failed: {}", error.message))?;
    serve_ndjson(tokio::io::stdin(), tokio::io::stdout(), runner)
        .await
        .context("serve runner protocol")
}

struct Options {
    root: PathBuf,
    policy: Option<PathBuf>,
    help: bool,
}

impl Options {
    fn parse(args: impl Iterator<Item = String>) -> Result<Self> {
        let mut root = std::env::var_os("KLINTCODE_SESSION_ROOT")
            .map(PathBuf::from)
            .or_else(|| {
                std::env::var_os("HOME")
                    .map(PathBuf::from)
                    .map(|home| home.join(".local/share/klintcode-runner"))
            })
            .unwrap_or_else(|| PathBuf::from(".klintcode-runner"));
        let mut policy = std::env::var_os("KLINTCODE_RUNNER_POLICY").map(PathBuf::from);
        let mut help = false;
        let mut args = args.peekable();
        while let Some(argument) = args.next() {
            match argument.as_str() {
                "bridge" | "--stdio" => {}
                "--root" => {
                    root = PathBuf::from(args.next().context("--root requires a path argument")?);
                }
                "--policy" => {
                    policy = Some(PathBuf::from(
                        args.next().context("--policy requires a path argument")?,
                    ));
                }
                "-h" | "--help" => help = true,
                unknown => bail!("unknown argument: {unknown}"),
            }
        }
        Ok(Self { root, policy, help })
    }
}

fn load_policy(path: &Path) -> Result<HardPolicy> {
    let contents = std::fs::read_to_string(path)
        .with_context(|| format!("read runner policy {}", path.display()))?;
    if path.extension().and_then(|value| value.to_str()) == Some("json") {
        serde_json::from_str(&contents)
            .with_context(|| format!("parse JSON runner policy {}", path.display()))
    } else {
        toml::from_str(&contents)
            .with_context(|| format!("parse TOML runner policy {}", path.display()))
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn accepts_bootstrap_compatible_bridge_forms() {
        for prefix in [
            Vec::<String>::new(),
            vec!["bridge".into()],
            vec!["--stdio".into()],
        ] {
            let mut args = prefix;
            args.extend(["--root".into(), "/tmp/runner-test".into()]);
            let options = Options::parse(args.into_iter()).unwrap();
            assert_eq!(options.root, Path::new("/tmp/runner-test"));
        }
    }
}
