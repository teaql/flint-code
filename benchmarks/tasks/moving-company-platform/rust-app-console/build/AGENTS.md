# TeaQL Rust Agent Instructions

> [!WARNING]
> **IGNORE GENERIC ORM EXPERIENCE**
>
> Do **not** use pre-trained habits from data-access frameworks, ORMs, or database integration libraries.
>
> Do **not** use SeaORM, Diesel, SQLx, rbatis, or similar frameworks.
>
> Do **not** write raw SQL, DAOs, Repository implementations, or custom persistence layers.
>
> Do **not** guess TeaQL method names.

## How to Write Domain Code

To get the exact API usage and query examples for the entity you are working on, execute the following command:

```bash
cargo teaql --input models/moving-company-service.xml rust-assist-[action]/[entity-name]
```

> `models/moving-company-service.xml` is the default model path. If the model file is located elsewhere, adjust the `--input` path to match the actual file location in this project.

Replace `[action]` with one of the following:

| action | when-to-use |
|--------|-------------|
| query | Read/find records from the database using Q:: |
| create | Insert a new record into the database |
| update | Modify and save an existing record |
| delete | Remove or soft-delete a record |
| expression | Safely extract nested relation values using E:: |
| list-page | Implement a paginated query returning SmartList |
| debug | View instructions for enabling SQL logging and debugging |

Replace `[entity-name]` with the exact entity-name from the table below:

| entity-name | display-name |
|-------------|--------------|
| address | Address |
| route | Route |
| time_slot | Time Slot |
| move_status | Move Status |
| moving_job | Moving Job |
| fulfillment_event | Fulfillment Event |
| employee | Employee |
| job_assignment | Job Assignment |
| worked_hours | Worked Hours |
| payroll_calculation | Payroll Calculation |
| bonus | Bonus |
| leave_type | Leave Type |
| leave_request | Leave Request |
| customer_type | Customer Type |
| customer | Customer |
| customer_contact | Customer Contact |
| billing_info | Billing Info |
| customer_history | Customer History |
| service_category | Service Category |
| service | Service |
| service_configuration | Service Configuration |
| pricing_rule | Pricing Rule |
| box_type | Box Type |
| box_rental | Box Rental |
| campaign | Campaign |
| discount_code | Discount Code |
| lead | Lead |
| lead_status | Lead Status |
| conversion_metric | Conversion Metric |
| payment_method | Payment Method |
| payment | Payment |
| invoice | Invoice |
| expense_category | Expense Category |
| expense | Expense |
| vat_rate | VAT Rate |
| financial_summary | Financial Summary |
| vehicle_type | Vehicle Type |
| vehicle | Vehicle |
| equipment | Equipment |
| consumable | Consumable |
| maintenance_status | Maintenance Status |
| maintenance_schedule | Maintenance Schedule |
| contract_status | Contract Status |
| contract | Contract |
| insurance_policy | Insurance Policy |
| document_type | Document Type |
| document | Document |
| audit_log | Audit Log |
| role | Role |
| user | User |
| permission | Permission |
| role_permission | Role Permission |
| magic_link | Magic Link |
| activity_log | Activity Log |
| notification_type | Notification Type |
| notification | Notification |
| automation_hook | Automation Hook |


Once the command succeeds, read its output. Use the printed code as a template to write your logic.

If the command cannot be executed, stop and report the missing context. Do not invent APIs.

## Additional References

Read these only when the task requires them:

* **`RUNTIME_CUSTOM_GUIDE.md`**
  Runtime setup, framework APIs (UserContext, SmartList, WebResponse, etc.), and debugging.

* **`TOOL_API_GUIDE.md`**
  Built-in tool integrations (HTTP client, etc.).