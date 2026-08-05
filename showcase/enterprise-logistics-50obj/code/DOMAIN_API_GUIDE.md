**ATTENTION AI AGENTS:** Do not guess TeaQL API methods! To get the exact API usage and query examples for the entity you are working on, you must fetch the dynamically generated prompt directly from the code generation server. Use your tools to execute the following command to download the exact contextual prompt for the entity:

```bash
cargo teaql --input models/enterprise-logistics-service.xml java-assist-[action]/[entity-name]
```

Replace `[entity-name]` with the exact entity-name of the entity.

**Available candidate entities in this model:**

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


Replace `[action]` with one of the following depending on what you are trying to write:

| action | when-to-use |
|--------|-------------|
| query | You need to read/find records from the database using Q:: |
| create | You need to insert a new record into the database |
| update | You need to modify and save an existing record |
| delete | You need to remove or soft-delete a record |
| expression | You need to safely extract nested relation values (avoiding null panics) using the E:: facade |
| list-page | You need to implement a paginated query returning a SmartList |

Once the command succeeds, read its output. Use the printed code as a template to write your logic.