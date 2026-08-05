# TeaQL Java Agent Instructions

> [!WARNING]
> **IGNORE GENERIC ORM EXPERIENCE**
>
> Do **not** use pre-trained habits from data-access frameworks, ORMs, or database integration libraries.
>
> Do **not** use MyBatis, JPA, Hibernate, Spring Data, MyBatis-Plus, or similar frameworks.
>
> Do **not** write raw SQL, DAOs, Repository implementations, or custom persistence layers.
>
> Do **not** guess TeaQL method names.

## How to Write Domain Code

To get the exact API usage and query examples for the entity you are working on, execute the following command:

```bash
cargo teaql --input models/enterprise-logistics-service.xml java-assist-[action]/[entity-name]
```

> `models/enterprise-logistics-service.xml` is the default model path. If the model file is located elsewhere, adjust the `--input` path to match the actual file location in this project.

Replace `[action]` with one of the following:

| action | when-to-use |
|--------|-------------|
| query | Read/find records from the database using Q. |
| create | Insert a new record into the database |
| update | Modify and save an existing record |
| delete | Remove or soft-delete a record |
| expression | Safely extract nested relation values using E. |
| list-page | Implement a paginated query returning SmartList |
| debug | View instructions for enabling SQL logging and debugging |

Replace `[entity-name]` with the exact entity-name from the table below:

| entity-name | display-name |
|-------------|--------------|
| moving_order | Moving Order |
| field | field |
| dispatch_plan | Dispatch Plan |
| transit_route | Transit Route |
| time_slot | Time Slot |
| cargo_item | Cargo Item |
| pickup_address | Pickup Address |
| vehicle | Vehicle |
| telematics_device | Telematics Device |
| gps_log | GPS Log |
| fuel_log | Fuel Log |
| vehicle_maintenance | Vehicle Maintenance |
| driver_assignment | Driver Assignment |
| warehouse | Warehouse |
| storage_container | Storage Container |
| container_unit | Container Unit |
| inventory_check | Inventory Check |
| pallet | Pallet |
| storage_fee | Storage Fee |
| staff_member | Staff Member |
| work_shift | Work Shift |
| worked_hours | Worked Hours |
| salary_slip | Salary Slip |
| performance_review | Performance Review |
| safety_training | Safety Training |
| private_customer | Private Customer |
| corporate_customer | Corporate Customer |
| customer_contact | Customer Contact |
| service_quote | Service Quote |
| feedback_review | Feedback Review |
| customer_loyalty | Customer Loyalty |
| promotion_campaign | Promotion Campaign |
| discount_coupon | Discount Coupon |
| sales_lead | Sales Lead |
| sales_channel | Sales Channel |
| marketing_roi | Marketing ROI |
| invoice | Invoice |
| payment_record | Payment Record |
| expense_item | Expense Item |
| tax_record | Tax Record |
| service_contract | Service Contract |
| insurance_policy | Insurance Policy |
| claims_record | Claims Record |
| customs_declaration | Customs Declaration |
| audit_log | Audit Log |
| user_account | User Account |
| user_role | User Role |
| access_permission | Access Permission |
| system_notification | System Notification |
| system_configuration | System Configuration |


Once the command succeeds, read its output. Use the printed code as a template to write your logic.

If the command cannot be executed, stop and report the missing context. Do not invent APIs.

## Additional References

Read these only when the task requires them:

* **`TOOL_API_GUIDE.md`**
  Framework runtime references and context handling.

* **`RUNTIME_CUSTOM_GUIDE.md`**
  Runtime setup, debugging, and project-specific restrictions.