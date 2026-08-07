# KlintCode TUI Design & Layout Rules

This document serves as the single source of truth for the UI layout and interaction rules in the KlintCode TUI application (`apps/klintcode-tui`). Following these rules prevents UI regressions and ensures a consistent user experience.

## 1. Global View Structure
The main terminal window is divided into four fixed horizontal chunks:
1. **Main Surface**: The largest upper area. Contains either the Main View (Transcript + Sidebar) or specialized full-screen views (e.g., Detail, Eval).
2. **Spacer**: A fixed 1-line empty gap between the Main Surface and the input area to prevent visual overlap.
3. **Composer (Input Area)**: Dynamic height based on input length + 2 lines. Always anchored at the bottom.
4. **Bottom Hint**: Fixed 1-line footer for global keyboard shortcuts.

## 2. Main View Layout (Right Sidebar)
When in the Main View with the right panel enabled, the screen is split horizontally (75% Transcript, 25% Sidebar). 
To prevent UI jumps, **the right sidebar layout order is strictly defined (top to bottom)**:
1. **System Status**: Fixed 6 lines.
2. **Context Metrics**: Fixed 3 lines.
3. **Context Size Bar**: Fixed 3 lines.
4. **Plan / Compact Status**: Dynamic height (`status_panel_height + 1`). Shows active execution plan.
5. **Active Tools**: Dynamic height (up to 9 lines). **Must stay at the bottom** so its height fluctuations do not push the `Plan` component up and down.

## 3. Transcript Log Rules
To keep the chat timeline readable and avoid giant wall-of-text logs:
- **System/Activity Logs**: Background tasks, successes, and errors are **strictly limited to 1 line**.
- **Auto-Truncation**: Any log exceeding 1行 or >188 characters is forcefully truncated.
- **Truncation Suffix**: Truncated logs append a fixed suffix format: `... <truncated N chars> [/detail i]`.
- *Note: Fully displayed messages do NOT carry the `[/detail i]` suffix to keep the UI clean.*

## 4. Navigation & Interactivity
- **Mouse Click Support**: The `[/detail i]` logs are clickable. Left-clicking the truncated line in the transcript maps the `(x, y)` coordinate and instantly opens the detail view.
- **Command Equivalents**: Users can also type `/detail <i>` or `/id <i>` in the composer to achieve the exact same expansion.
- **View Modals**: Sub-features like RAG Search (`/rag`), Model Eval (`/eval`), VLLM Test (`/vllm`), and Detail View (`/detail`) take over the `Main Surface`.
- **Global Escape**: Pressing the `ESC` key inside any specialized view instantly dismisses it and returns the user to the Main View.

## 5. Visual Styling Principles
To keep the TUI feeling modern and avoid an outdated "1980s terminal" look:
- **No Heavy Borders**: Never use full box borders (`Borders::ALL`) to wrap components.
- **Minimalist Separation**: Use blank space (padding/margins) or simple horizontal lines to separate unit areas.
- **Clean Aesthetic**: The interface must remain breathable and uncluttered without unnecessary bounding boxes around every panel.
