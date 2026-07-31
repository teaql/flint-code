use teaql_core::Entity;
use moving_company_service_core::{Q, E, TeaqlRuntime, TeaqlRepositoryProvider, AuditedSave};
use chrono::NaiveDate;
use rust_decimal::Decimal;

fn banner(title: &str) {
    println!("\n{}", "═".repeat(70));
    println!("  {}", title);
    println!("{}", "═".repeat(70));
}

fn section(title: &str) {
    println!("\n── {} {}", title, "─".repeat(60_usize.saturating_sub(title.len() + 4)));
}

fn dec(v: f64) -> Decimal {
    Decimal::try_from(v).unwrap_or_default()
}

// ─────────────────────────────────────────────────────────────────────────────
// 1. SEED: Addresses
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_addresses(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider)) -> Result<(u64, u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Addresses");

    let mut addr1 = Q::addresses()
        .purpose("Seed demo data: warehouse address")
        .new_entity(ctx);
    addr1.update_street("100 Warehouse Blvd");
    addr1.update_city("Springfield");
    addr1.update_state("IL");
    addr1.update_zip_code("62701");
    addr1.update_country("USA");
    addr1.clone()
        .audit_as("Seed: create warehouse address for demo")
        .save(ctx).await?;
    let addr1_id = addr1.id();
    println!("  ✓ Address #{}: 100 Warehouse Blvd, Springfield, IL", addr1_id);

    let mut addr2 = Q::addresses()
        .purpose("Seed demo data: customer origin address")
        .new_entity(ctx);
    addr2.update_street("42 Maple Street");
    addr2.update_city("Springfield");
    addr2.update_state("IL");
    addr2.update_zip_code("62704");
    addr2.update_country("USA");
    addr2.clone()
        .audit_as("Seed: create customer origin address")
        .save(ctx).await?;
    let addr2_id = addr2.id();
    println!("  ✓ Address #{}: 42 Maple Street, Springfield, IL", addr2_id);

    let mut addr3 = Q::addresses()
        .purpose("Seed demo data: customer destination address")
        .new_entity(ctx);
    addr3.update_street("789 Oak Avenue");
    addr3.update_city("Chicago");
    addr3.update_state("IL");
    addr3.update_zip_code("60601");
    addr3.update_country("USA");
    addr3.clone()
        .audit_as("Seed: create customer destination address")
        .save(ctx).await?;
    let addr3_id = addr3.id();
    println!("  ✓ Address #{}: 789 Oak Avenue, Chicago, IL", addr3_id);

    Ok((addr1_id, addr2_id, addr3_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 2. SEED: Employees
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_employees(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider)) -> Result<(u64, u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Employees");

    let mut emp1 = Q::employees()
        .purpose("Seed demo data: lead mover")
        .new_entity(ctx);
    emp1.update_first_name("Marcus");
    emp1.update_last_name("Rivera");
    emp1.update_email("marcus.rivera@fastmovers.com");
    emp1.update_phone("555-100-2001");
    emp1.update_hire_date(NaiveDate::from_ymd_opt(2022, 3, 15).unwrap());
    emp1.update_position("Lead Mover");
    emp1.update_is_active(true);
    emp1.clone()
        .audit_as("Seed: create lead mover employee")
        .save(ctx).await?;
    let emp1_id = emp1.id();
    println!("  ✓ Employee #{}: Marcus Rivera (Lead Mover)", emp1_id);

    let mut emp2 = Q::employees()
        .purpose("Seed demo data: mover")
        .new_entity(ctx);
    emp2.update_first_name("Sarah");
    emp2.update_last_name("Chen");
    emp2.update_email("sarah.chen@fastmovers.com");
    emp2.update_phone("555-100-2002");
    emp2.update_hire_date(NaiveDate::from_ymd_opt(2023, 1, 10).unwrap());
    emp2.update_position("Mover");
    emp2.update_is_active(true);
    emp2.clone()
        .audit_as("Seed: create mover employee")
        .save(ctx).await?;
    let emp2_id = emp2.id();
    println!("  ✓ Employee #{}: Sarah Chen (Mover)", emp2_id);

    let mut emp3 = Q::employees()
        .purpose("Seed demo data: driver")
        .new_entity(ctx);
    emp3.update_first_name("James");
    emp3.update_last_name("Okafor");
    emp3.update_email("james.okafor@fastmovers.com");
    emp3.update_phone("555-100-2003");
    emp3.update_hire_date(NaiveDate::from_ymd_opt(2021, 8, 20).unwrap());
    emp3.update_position("Driver");
    emp3.update_is_active(true);
    emp3.clone()
        .audit_as("Seed: create driver employee")
        .save(ctx).await?;
    let emp3_id = emp3.id();
    println!("  ✓ Employee #{}: James Okafor (Driver)", emp3_id);

    Ok((emp1_id, emp2_id, emp3_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 3. SEED: Customers
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_customers(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider)) -> Result<(u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Customers");

    let mut cust1 = Q::customers()
        .purpose("Seed demo data: private customer")
        .new_entity(ctx);
    cust1.update_customer_type_to_private();
    cust1.update_first_name("Emily");
    cust1.update_last_name("Watson");
    cust1.update_email("emily.watson@email.com");
    cust1.update_phone("555-300-4001");
    cust1.clone()
        .audit_as("Seed: create private customer Emily Watson")
        .save(ctx).await?;
    let cust1_id = cust1.id();
    println!("  ✓ Customer #{}: Emily Watson (Private)", cust1_id);

    let mut cust2 = Q::customers()
        .purpose("Seed demo data: corporate customer")
        .new_entity(ctx);
    cust2.update_customer_type_to_corporate();
    cust2.update_first_name("Robert");
    cust2.update_last_name("Kim");
    cust2.update_email("rkim@techcorp.com");
    cust2.update_phone("555-300-4002");
    cust2.update_company_name("TechCorp Solutions");
    cust2.clone()
        .audit_as("Seed: create corporate customer TechCorp Solutions")
        .save(ctx).await?;
    let cust2_id = cust2.id();
    println!("  ✓ Customer #{}: Robert Kim / TechCorp Solutions (Corporate)", cust2_id);

    Ok((cust1_id, cust2_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 4. SEED: Routes & Time Slots
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_routes_and_slots(
    ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider),
    origin_id: u64,
    dest_id: u64,
) -> Result<(u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Routes & Time Slots");

    let mut route = Q::routes()
        .purpose("Seed demo data: Springfield to Chicago route")
        .new_entity(ctx);
    route.update_name("Springfield → Chicago");
    route.update_origin_id(origin_id);
    route.update_destination_id(dest_id);
    route.update_estimated_duration(210);
    route.update_distance_miles(dec(200.0));
    route.clone()
        .audit_as("Seed: create Springfield-Chicago route")
        .save(ctx).await?;
    let route_id = route.id();
    println!("  ✓ Route #{}: Springfield → Chicago (200 mi, ~3.5h)", route_id);

    let mut slot = Q::time_slots()
        .purpose("Seed demo data: morning time slot")
        .new_entity(ctx);
    slot.update_start_time(chrono::NaiveDateTime::parse_from_str("2025-07-15T08:00:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
    slot.update_end_time(chrono::NaiveDateTime::parse_from_str("2025-07-15T12:00:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
    slot.update_is_available(true);
    slot.clone()
        .audit_as("Seed: create morning time slot for July 15")
        .save(ctx).await?;
    let slot_id = slot.id();
    println!("  ✓ Time Slot #{}: 2025-07-15 08:00–12:00", slot_id);

    Ok((route_id, slot_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 5. SEED: Services
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_services(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider)) -> Result<(u64, u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Services");

    let mut svc1 = Q::services()
        .purpose("Seed demo data: local moving service")
        .new_entity(ctx);
    svc1.update_service_category_to_moving();
    svc1.update_name("Local Moving");
    svc1.update_description("Full-service local moving within city limits");
    svc1.update_base_price(dec(500.0));
    svc1.update_price_per_hour(dec(75.0));
    svc1.update_is_active(true);
    svc1.clone()
        .audit_as("Seed: create local moving service")
        .save(ctx).await?;
    let svc1_id = svc1.id();
    println!("  ✓ Service #{}: Local Moving ($500 base + $75/hr)", svc1_id);

    let mut svc2 = Q::services()
        .purpose("Seed demo data: long distance moving service")
        .new_entity(ctx);
    svc2.update_service_category_to_moving();
    svc2.update_name("Long Distance Moving");
    svc2.update_description("Interstate moving services with full logistics");
    svc2.update_base_price(dec(1500.0));
    svc2.update_price_per_hour(dec(95.0));
    svc2.update_is_active(true);
    svc2.clone()
        .audit_as("Seed: create long distance moving service")
        .save(ctx).await?;
    let svc2_id = svc2.id();
    println!("  ✓ Service #{}: Long Distance Moving ($1500 base + $95/hr)", svc2_id);

    let mut svc3 = Q::services()
        .purpose("Seed demo data: cleaning service")
        .new_entity(ctx);
    svc3.update_service_category_to_cleaning();
    svc3.update_name("Post-Move Cleaning");
    svc3.update_description("Deep cleaning after moving out");
    svc3.update_base_price(dec(200.0));
    svc3.update_price_per_hour(dec(45.0));
    svc3.update_is_active(true);
    svc3.clone()
        .audit_as("Seed: create post-move cleaning service")
        .save(ctx).await?;
    let svc3_id = svc3.id();
    println!("  ✓ Service #{}: Post-Move Cleaning ($200 base + $45/hr)", svc3_id);

    Ok((svc1_id, svc2_id, svc3_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 6. SEED: Vehicles
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_vehicles(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider)) -> Result<(u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Vehicles");

    let mut v1 = Q::vehicles()
        .purpose("Seed demo data: box truck")
        .new_entity(ctx);
    v1.update_vehicle_type_to_box_truck();
    v1.update_make("Ford");
    v1.update_model("E-450");
    v1.update_year(2023);
    v1.update_license_plate("IL-MV-1001");
    v1.update_vin("1FTNE45P13DA00001");
    v1.update_mileage(45000);
    v1.update_status("available");
    v1.clone()
        .audit_as("Seed: create box truck Ford E-450")
        .save(ctx).await?;
    let v1_id = v1.id();
    println!("  ✓ Vehicle #{}: Ford E-450 Box Truck (IL-MV-1001)", v1_id);

    let mut v2 = Q::vehicles()
        .purpose("Seed demo data: van")
        .new_entity(ctx);
    v2.update_vehicle_type_to_van();
    v2.update_make("Mercedes");
    v2.update_model("Sprinter");
    v2.update_year(2024);
    v2.update_license_plate("IL-MV-1002");
    v2.update_vin("WD3PE7CC5E5000002");
    v2.update_mileage(12000);
    v2.update_status("available");
    v2.clone()
        .audit_as("Seed: create van Mercedes Sprinter")
        .save(ctx).await?;
    let v2_id = v2.id();
    println!("  ✓ Vehicle #{}: Mercedes Sprinter Van (IL-MV-1002)", v2_id);

    Ok((v1_id, v2_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 7. CREATE: Moving Jobs
// ─────────────────────────────────────────────────────────────────────────────
async fn create_moving_jobs(
    ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider),
    customer_id: u64,
    route_id: u64,
    slot_id: u64,
) -> Result<(u64, u64), Box<dyn std::error::Error>> {
    section("Creating Moving Jobs");

    let mut job1 = Q::moving_jobs()
        .purpose("Create a scheduled moving job for private customer")
        .new_entity(ctx);
    job1.update_customer_id(customer_id);
    job1.update_route_id(route_id);
    job1.update_time_slot_id(slot_id);
    job1.update_status_to_scheduled();
    job1.update_scheduled_date(NaiveDate::from_ymd_opt(2025, 7, 15).unwrap());
    job1.update_notes("Fragile items — handle with care. 3-bedroom house.");
    job1.clone()
        .audit_as("Create moving job: Emily Watson Springfield to Chicago")
        .save(ctx).await?;
    let job1_id = job1.id();
    println!("  ✓ Moving Job #{}: Scheduled for 2025-07-15 (Customer #{})", job1_id, customer_id);

    let mut slot2 = Q::time_slots()
        .purpose("Create afternoon time slot for second job")
        .new_entity(ctx);
    slot2.update_start_time(chrono::NaiveDateTime::parse_from_str("2025-07-16T13:00:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
    slot2.update_end_time(chrono::NaiveDateTime::parse_from_str("2025-07-16T17:00:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
    slot2.update_is_available(true);
    slot2.clone()
        .audit_as("Create afternoon time slot for July 16")
        .save(ctx).await?;
    let slot2_id = slot2.id();

    let mut job2 = Q::moving_jobs()
        .purpose("Create a second scheduled moving job")
        .new_entity(ctx);
    job2.update_customer_id(customer_id);
    job2.update_route_id(route_id);
    job2.update_time_slot_id(slot2_id);
    job2.update_status_to_scheduled();
    job2.update_scheduled_date(NaiveDate::from_ymd_opt(2025, 7, 16).unwrap());
    job2.update_notes("Office furniture — heavy items require extra crew.");
    job2.clone()
        .audit_as("Create moving job: second job for July 16")
        .save(ctx).await?;
    let job2_id = job2.id();
    println!("  ✓ Moving Job #{}: Scheduled for 2025-07-16 (Customer #{})", job2_id, customer_id);

    Ok((job1_id, job2_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 8. ASSIGN: Job Assignments
// ─────────────────────────────────────────────────────────────────────────────
async fn assign_jobs(
    ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider),
    job_id: u64,
    emp_ids: &[u64],
) -> Result<(), Box<dyn std::error::Error>> {
    section("Assigning Employees to Moving Job");

    let roles = ["Lead Mover", "Mover", "Driver"];
    for (i, &emp_id) in emp_ids.iter().enumerate() {
        let role = roles.get(i).unwrap_or(&"Assistant").to_string();
        let mut assignment = Q::job_assignments()
            .purpose("Assign employee to moving job")
            .new_entity(ctx);
        assignment.update_employee_id(emp_id);
        assignment.update_moving_job_id(job_id);
        assignment.update_role(role.clone());
        assignment.update_assigned_date(NaiveDate::from_ymd_opt(2025, 7, 10).unwrap());
        assignment.update_start_time(chrono::NaiveDateTime::parse_from_str("2025-07-15T08:00:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
        assignment.update_end_time(chrono::NaiveDateTime::parse_from_str("2025-07-15T16:00:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
        assignment.clone()
            .audit_as(&format!("Assign employee #{} as {} to job #{}", emp_id, role, job_id))
            .save(ctx).await?;
        println!("  ✓ Assigned Employee #{} as {} to Job #{}", emp_id, role, job_id);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 9. UPDATE: Progress a job to In Progress
// ─────────────────────────────────────────────────────────────────────────────
async fn progress_job(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider), job_id: u64) -> Result<(), Box<dyn std::error::Error>> {
    section("Updating Job Status");

    if let Some(mut job) = Q::moving_jobs()
        .with_id_is(job_id)
        .comment("what: Fetch moving job to update status")
        .purpose("why: Progress job from Scheduled to In Progress")
        .execute_for_one(ctx)
        .await?
    {
        job.update_status_to_in_progress();
        job.audit_as(&format!("Move job #{} started — crew dispatched", job_id))
            .save(ctx).await?;
        println!("  ✓ Moving Job #{} → In Progress", job_id);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 10. CREATE: Fulfillment Event
// ─────────────────────────────────────────────────────────────────────────────
async fn create_fulfillment_event(
    ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider),
    job_id: u64,
    address_id: u64,
) -> Result<(), Box<dyn std::error::Error>> {
    section("Recording Fulfillment Events");

    let mut evt = Q::fulfillment_events()
        .purpose("Record pickup fulfillment event")
        .new_entity(ctx);
    evt.update_moving_job_id(job_id);
    evt.update_event_type("pickup");
    evt.update_timestamp(chrono::NaiveDateTime::parse_from_str("2025-07-15T08:30:00", "%Y-%m-%dT%H:%M:%S").unwrap().and_utc());
    evt.update_location_id(address_id);
    evt.update_notes("All items loaded successfully. 42 boxes + furniture.");
    evt.clone()
        .audit_as(&format!("Record pickup event for job #{}", job_id))
        .save(ctx).await?;
    println!("  ✓ Fulfillment Event: Pickup at 08:30 for Job #{}", job_id);

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 11. COMPLETE: Mark job as completed
// ─────────────────────────────────────────────────────────────────────────────
async fn complete_job(ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider), job_id: u64) -> Result<(), Box<dyn std::error::Error>> {
    section("Completing Moving Job");

    if let Some(mut job) = Q::moving_jobs()
        .with_id_is(job_id)
        .comment("what: Fetch moving job to mark complete")
        .purpose("why: Complete the moving job after delivery")
        .execute_for_one(ctx)
        .await?
    {
        job.update_status_to_completed();
        job.audit_as(&format!("Move job #{} completed — all items delivered", job_id))
            .save(ctx).await?;
        println!("  ✓ Moving Job #{} → Completed", job_id);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 12. FINANCE: Create Invoice & Payment
// ─────────────────────────────────────────────────────────────────────────────
async fn create_financial_records(
    ctx: &(impl TeaqlRuntime + TeaqlRepositoryProvider),
    customer_id: u64,
    job_id: u64,
) -> Result<(), Box<dyn std::error::Error>> {
    section("Creating Financial Records");

    let mut invoice = Q::invoices()
        .purpose("Create invoice for completed moving job")
        .new_entity(ctx);
    invoice.update_customer_id(customer_id);
    invoice.update_moving_job_id(job_id);
    invoice.update_invoice_number("INV-2025-0001");
    invoice.update_issue_date(NaiveDate::from_ymd_opt(2025, 7, 15).unwrap());
    invoice.update_due_date(NaiveDate::from_ymd_opt(2025, 8, 14).unwrap());
    invoice.update_subtotal(dec(1850.0));
    invoice.update_tax_amount(dec(148.0));
    invoice.update_total_amount(dec(1998.0));
    invoice.update_status("issued");
    invoice.clone()
        .audit_as("Create invoice for Emily Watson moving job")
        .save(ctx).await?;
    println!("  ✓ Invoice #{}: INV-2025-0001 — Total: $1,998.00", invoice.id());

    let mut payment = Q::payments()
        .purpose("Record credit card payment from customer")
        .new_entity(ctx);
    payment.update_customer_id(customer_id);
    payment.update_moving_job_id(job_id);
    payment.update_payment_method_to_credit_card();
    payment.update_amount(dec(1998.0));
    payment.update_payment_date(NaiveDate::from_ymd_opt(2025, 7, 16).unwrap());
    payment.update_transaction_id("TXN-CC-20250716-001");
    payment.update_status("completed");
    payment.clone()
        .audit_as("Record credit card payment from Emily Watson")
        .save(ctx).await?;
    println!("  ✓ Payment #{}: $1,998.00 via Credit Card (TXN-CC-20250716-001)", payment.id());

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 13. QUERY: Dashboard — list all customers
// ─────────────────────────────────────────────────────────────────────────────
async fn query_customers(ctx: &impl TeaqlRepositoryProvider) -> Result<(), Box<dyn std::error::Error>> {
    section("Query: All Customers");

    let customers = Q::customers_minimal()
        .select_customer_type()
        .select_first_name()
        .select_last_name()
        .select_email()
        .select_company_name()
        .order_by_id_desc()
        .limit(20)
        .comment("what: List all customers for dashboard view")
        .purpose("why: Display customer registry in terminal demo")
        .execute_for_list(ctx)
        .await?;

    println!("  Found {} customer(s):\n", customers.len());
    for c in customers.iter() {
        let first = E::customer(c).get_first_name().eval().unwrap_or_default();
        let last = E::customer(c).get_last_name().eval().unwrap_or_default();
        let email = E::customer(c).get_email().eval().unwrap_or_default();
        let company = E::customer(c).get_company_name().eval().unwrap_or_default();
        let type_name = E::customer(c).get_customer_type().get_name().eval().unwrap_or_default();
        let company_display = if company.is_empty() { String::from("—") } else { company };
        println!("    [{:<10}] {} {} | {} | Company: {}", type_name, first, last, email, company_display);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 14. QUERY: All employees
// ─────────────────────────────────────────────────────────────────────────────
async fn query_employees(ctx: &impl TeaqlRepositoryProvider) -> Result<(), Box<dyn std::error::Error>> {
    section("Query: All Employees");

    let employees = Q::employees_minimal()
        .select_first_name()
        .select_last_name()
        .select_email()
        .select_position()
        .select_is_active()
        .order_by_id_asc()
        .limit(20)
        .comment("what: List all employees for dashboard view")
        .purpose("why: Display employee registry in terminal demo")
        .execute_for_list(ctx)
        .await?;

    println!("  Found {} employee(s):\n", employees.len());
    for e in employees.iter() {
        let first = E::employee(e).get_first_name().eval().unwrap_or_default();
        let last = E::employee(e).get_last_name().eval().unwrap_or_default();
        let email = E::employee(e).get_email().eval().unwrap_or_default();
        let pos = E::employee(e).get_position().eval().unwrap_or_default();
        let active = E::employee(e).get_is_active().eval().unwrap_or(false);
        let status = if active { "Active" } else { "Inactive" };
        println!("    {} {} | {} | {} [{}]", first, last, email, pos, status);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 15. QUERY: All moving jobs with status
// ─────────────────────────────────────────────────────────────────────────────
async fn query_moving_jobs(ctx: &impl TeaqlRepositoryProvider) -> Result<(), Box<dyn std::error::Error>> {
    section("Query: All Moving Jobs");

    let jobs = Q::moving_jobs_minimal()
        .select_customer()
        .select_route()
        .select_status()
        .select_scheduled_date()
        .select_notes()
        .order_by_id_desc()
        .limit(20)
        .comment("what: List all moving jobs with status")
        .purpose("why: Display job board in terminal demo")
        .execute_for_list(ctx)
        .await?;

    println!("  Found {} moving job(s):\n", jobs.len());
    for j in jobs.iter() {
        let status_name = E::moving_job(j).get_status().get_name().eval().unwrap_or_default();
        let date = E::moving_job(j).get_scheduled_date().eval()
            .map(|d| format!("{}", d))
            .unwrap_or_else(|| "—".to_string());
        let notes = E::moving_job(j).get_notes().eval().unwrap_or_default();
        let cust_first = E::moving_job(j).get_customer().get_first_name().eval().unwrap_or_default();
        let cust_last = E::moving_job(j).get_customer().get_last_name().eval().unwrap_or_default();
        let route_name = E::moving_job(j).get_route().get_name().eval().unwrap_or_default();
        println!("    Job #{} | {} {} | {} | {} | {} | {}",
            j.id(), cust_first, cust_last, route_name, date, status_name, notes);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 16. QUERY: Vehicles
// ─────────────────────────────────────────────────────────────────────────────
async fn query_vehicles(ctx: &impl TeaqlRepositoryProvider) -> Result<(), Box<dyn std::error::Error>> {
    section("Query: Fleet Vehicles");

    let vehicles = Q::vehicles_minimal()
        .select_vehicle_type()
        .select_make()
        .select_model()
        .select_year()
        .select_license_plate()
        .select_mileage()
        .select_status()
        .order_by_id_asc()
        .limit(20)
        .comment("what: List all fleet vehicles")
        .purpose("why: Display fleet status in terminal demo")
        .execute_for_list(ctx)
        .await?;

    println!("  Found {} vehicle(s):\n", vehicles.len());
    for v in vehicles.iter() {
        let make = E::vehicle(v).get_make().eval().unwrap_or_default();
        let model = E::vehicle(v).get_model().eval().unwrap_or_default();
        let year = E::vehicle(v).get_year().eval().unwrap_or_default();
        let plate = E::vehicle(v).get_license_plate().eval().unwrap_or_default();
        let mileage = E::vehicle(v).get_mileage().eval().unwrap_or_default();
        let status = E::vehicle(v).get_status().eval().unwrap_or_default();
        let vtype = E::vehicle(v).get_vehicle_type().get_name().eval().unwrap_or_default();
        println!("    {} {} {} ({}) | {} mi | Plate: {} [{}]", vtype, year, make, model, mileage, plate, status);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 17. QUERY: Services
// ─────────────────────────────────────────────────────────────────────────────
async fn query_services(ctx: &impl TeaqlRepositoryProvider) -> Result<(), Box<dyn std::error::Error>> {
    section("Query: Available Services");

    let services = Q::services_minimal()
        .select_service_category()
        .select_name()
        .select_description()
        .select_base_price()
        .select_price_per_hour()
        .select_is_active()
        .order_by_id_asc()
        .limit(20)
        .comment("what: List all available services")
        .purpose("why: Display service catalog in terminal demo")
        .execute_for_list(ctx)
        .await?;

    println!("  Found {} service(s):\n", services.len());
    for s in services.iter() {
        let name = E::service(s).get_name().eval().unwrap_or_default();
        let desc = E::service(s).get_description().eval().unwrap_or_default();
        let base = E::service(s).get_base_price().eval().unwrap_or_default();
        let hourly = E::service(s).get_price_per_hour().eval().unwrap_or_default();
        let cat = E::service(s).get_service_category().get_name().eval().unwrap_or_default();
        let active = E::service(s).get_is_active().eval().unwrap_or(false);
        let status = if active { "Active" } else { "Inactive" };
        println!("    [{}] {} — ${} base + ${}/hr [{}]\n      {}", cat, name, base, hourly, status, desc);
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// MAIN
// ─────────────────────────────────────────────────────────────────────────────
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    banner("🚚 Moving Company Platform — TeaQL Terminal Demo");
    println!("  Initializing runtime...");

    let ctx = moving_company_service_core::service_runtime_from_env().await?;
    ctx.ensure_schema().await?;
    println!("  ✓ Database schema ensured.");

    // ── Phase 1: Seed Data ──────────────────────────────────────────────
    banner("📦 Phase 1: Seeding Reference Data");

    let (_addr_warehouse, addr_origin, addr_dest) = seed_addresses(&ctx).await?;
    let (emp1, emp2, emp3) = seed_employees(&ctx).await?;
    let (cust_private, _cust_corporate) = seed_customers(&ctx).await?;
    let (route_id, slot_id) = seed_routes_and_slots(&ctx, addr_origin, addr_dest).await?;
    let (_svc_local, _svc_longdist, _svc_cleaning) = seed_services(&ctx).await?;
    let (_v1, _v2) = seed_vehicles(&ctx).await?;

    // ── Phase 2: Operational Workflow ───────────────────────────────────
    banner("🔧 Phase 2: Operational Workflow");

    let (job1_id, _job2_id) = create_moving_jobs(&ctx, cust_private, route_id, slot_id).await?;
    assign_jobs(&ctx, job1_id, &[emp1, emp2, emp3]).await?;
    progress_job(&ctx, job1_id).await?;
    create_fulfillment_event(&ctx, job1_id, addr_origin).await?;
    complete_job(&ctx, job1_id).await?;

    // ── Phase 3: Financial Processing ───────────────────────────────────
    banner("💰 Phase 3: Financial Processing");
    create_financial_records(&ctx, cust_private, job1_id).await?;

    // ── Phase 4: Dashboard Queries ──────────────────────────────────────
    banner("📊 Phase 4: Dashboard Queries");

    query_customers(&ctx).await?;
    query_employees(&ctx).await?;
    query_moving_jobs(&ctx).await?;
    query_vehicles(&ctx).await?;
    query_services(&ctx).await?;

    // ── Done ────────────────────────────────────────────────────────────
    banner("✅ Demo Complete");
    println!("  All operations executed successfully.");
    println!("  Database: sqlite (in-memory or file-based)");
    println!("  Entities used: Address, Employee, Customer, Route, TimeSlot,");
    println!("                 MovingJob, JobAssignment, FulfillmentEvent,");
    println!("                 Service, Vehicle, Invoice, Payment");
    println!();

    Ok(())
}
