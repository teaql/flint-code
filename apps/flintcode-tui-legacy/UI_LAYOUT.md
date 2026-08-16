# FlintCode TUI Legacy Layout Rules

This document records the maintenance-only layout contract for `apps/flintcode-tui-legacy`.

## Global structure

The terminal is split vertically into four regions:

1. Main surface: transcript and sidebar, or a full-screen secondary view.
2. Spacer: one blank line separating output from input.
3. Composer: input height plus two border lines, anchored near the bottom.
4. Bottom hint: one line of keyboard shortcuts.

## Main view

With the right panel enabled, the main view uses a 75/25 split. The sidebar order is fixed:

1. Flint service status: 6 lines.
2. Token metrics: 3 lines.
3. Context size: 3 lines.
4. Plan/status: dynamic height.
5. Active tools: dynamic height, placed after the plan so plan placement remains stable.

Avoid heavy borders and prefer whitespace or simple horizontal separators.
