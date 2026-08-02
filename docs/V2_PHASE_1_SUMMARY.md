# Flint Code V2：第一阶段（上下文管理架构）总结报告

**生成时间**：2026-08-02
**阶段状态**：✅ 第一阶段核心架构与机制验证完成，准备开启新会话进入下一阶段。

---

## 1. 目前的状态 (Current Status)

目前，我们已经成功对 `flint-code` 的核心 Agent 引擎（`agent-core`）进行了深度改造。摒弃了传统的“流水线追加式（Pipeline/History）”上下文管理，转向了以 **AST（抽象语法树）** 为核心的动态知识管理，并结合了极其激进的 **“阅后即焚（Ephemeral）”** 机制。

与之配合的，我们在业务端（`teaql-agent-kit` 和 `teaql-code-gen`）完成了流程图的合并，将繁琐的“建模（Model Generation）”和“评估（Evaluate/Repair）”统一为了单个生命周期 `phase_modeling`，以便在生命周期结束时实现上下文的精准切割。

---

## 2. 架构与设计 (Architecture & Design)

本阶段确立了三大核心引擎设计：

### 2.1 基于 AST 的动态上下文树 (ContextManager)
- **底层数据结构**：采用 `BTreeMap` 维护按照优先级排布的 `SkillBlock` 节点树。大模型看到的 Prompt 不再是线性的历史记录，而是由 AST 实时渲染出的 `active_prompt`。
- **标签驱动**：通过在 Prompt 或文件里埋点 `<!-- BLOCK_ID: phase_modeling -->` 挂载知识块，通过 `<!-- DISCARD_BLOCK: phase_modeling -->` 触发卸载。
- **阶段跃迁**：当 Agent 从“排错建模阶段”迈入“代码编写阶段”时，代码生成器打出的 `DISCARD` 标签会触发 AST 剪枝，大模型的“大脑”被瞬间清空，彻底抛弃前置流程的知识包袱。

### 2.2 阅后即焚 (Ephemeral Memory)
- **长文本截断**：在 `AgentLoop` 中拦截所有 Tool Response，当工具（如 `cargo teaql evaluate`）返回的终端日志长度超过 1000 字节时，截断其内容并替换为 `[EPHEMERAL: Content > 1000 bytes truncated...]`。
- **设计初衷**：在多次重试和修复（Repair）的死循环中，避免上万字的报错日志撑爆 400 Context Limit，确保 Agent 能够永远保持清醒，聚焦于当前的修复动作。

### 2.3 背景调试探针 (Debug HTTP Server)
- 引擎内嵌了一个异步的 `TcpListener`（默认挂载于 `127.0.0.1:8888`），使得开发者可以在运行期间，通过 `curl http://localhost:8888/context` 实时拉取并监控当前 AST 树上挂载了哪些知识节点。

---

## 3. 压测结果 (Testing Results)

我们并发了 **5 轮** `moving-company-platform`（搬家公司平台）端到端任务压测。该任务包含 40+ 业务对象和 8 大模块，逻辑极其复杂。

- **成功率**：5/5 (100% 成功通关，均在 10 分钟内完成，通过 `cargo check`)
- **极端场景验证**：其中 Stress 3 用例遭遇了严重的建模错误，触发了 3 轮评估与修复（Evaluation Rounds 3）。
- **优化收益印证**：
  1. 在多次试错期间，“阅后即焚”成功阻止了千字报错堆积，大模型未出现历史污染与幻觉。
  2. 在修复完成，进入 Rust 业务逻辑编写阶段的一瞬间，`phase_modeling` 卸载标签生效。Agent 上下文瞬间减负，使得其在编写 `main.rs` 时注意力 100% 集中，精准命中 `.purpose()`, `.comment()` 和 `.audit_as()` 等安全与审计 API 的要求。

---

## 4. 下阶段展望 (Next Steps)

本阶段的“上下文保护伞”已经成型，当前会话（Session）圆满结束。
在新会话中，我们将推进：
1. **Tool-Runner 的硬化与沙盒设计**：处理 Agent 执行命令时的安全、超时与环境隔离。
2. **多 Agent 协作网络**：基于当前清晰的上下文机制，引入更复杂的并发任务分配。
