# Moving Company Platform

Generate a comprehensive KSML XML model for a large-scale moving company platform based on the following requirements.

## 🔧 Core Modules
- **Operations & logistics:** moves, routes, time slots, fulfillment events, addresses
- **Employees & payroll:** staff registry, job assignments, worked hours, payroll calculations, bonuses, leave tracking
- **Customer management:** private and corporate customers, linked contacts, billing info, customer history
- **Products & services:** moving, cleaning, box rentals, and additional services with configurations and pricing
- **Marketing & sales:** campaigns, discount codes, lead tracking, conversion metrics
- **Finance & accounting:** payments, invoices, expenses, VAT, financial summaries
- **Asset management:** vehicles, equipment, consumables, maintenance schedules
- **Administration & compliance:** contracts, insurance, document storage, audit logs

## 🧩 Platform Modules
- **User & role management:** admin, manager, employee, customer access levels
- **Authentication & permissions:** support for magic links, role-based access control (RBAC)
- **Activity logging & audit trail:** full history of changes, edits, and user actions
- **Versioning & soft deletes:** data recovery and edit history tracking
- **Notifications & automation hooks:** triggers for operational or financial updates

## Requirements
- Follow the grammar example structure exactly.
- Ensure there are at least 30 unique business objects generated (use appropriate entity names and relationships to capture all the complexity requested).
- Do not use deprecated attributes on the root node (such as alias_model_name, chinese_name, english_name).
- If the model is large, split it into multiple modules by business domain.
- After the model passes evaluation, generate the Rust libraries and application workspace based on the toolchains reference.
- Develop a small terminal-based application using Rust in the generated workspace to demonstrate the core features.
