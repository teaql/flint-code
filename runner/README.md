# FlintCode Runner

This directory is the trusted remote execution boundary shared by every Agent
integration in the TeaQL ecosystem. The crate currently retains the Cargo
package name `tool-runner` for source compatibility; new public documentation
and components call it FlintCode Runner.

It contains the versioned protocol, SSH client, content-addressed bootstrap,
durable remote session, filesystem operations, process-group cancellation, and
server-side policy enforcement. Agent-specific behavior does not belong here.
