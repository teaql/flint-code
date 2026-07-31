use moving_company_service_core::{Q, AuditedSave};
use moving_company_service_core::teaql_core::Entity;
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
async fn seed_addresses(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(u64, u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Addresses");

    let mut addr1 = Q::addresses()
        .purpose("Seed demo data: warehouse address")
        .new_entity(ctx);
    addr1.update_street("100 Warehouse Blvd");
    addr1.update_city("Springfield");
    addr1.update_state("IL");
    addr1.update_zip_code(62701);
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
    addr2.update_zip_code(62704);
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
    addr3.update_zip_code(60601);
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
async fn seed_employees(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(u64, u64, u64), Box<dyn std::error::Error>> {
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
    emp3.update_last_name("Wilson");
    emp3.update_email("james.wilson@fastmovers.com");
    emp3.update_phone("555-100-2003");
    emp3.update_hire_date(NaiveDate::from_ymd_opt(2021, 8, 20).unwrap());
    emp3.update_position("Driver");
    emp3.update_is_active(true);
    emp3.clone()
        .audit_as("Seed: create driver employee")
        .save(ctx).await?;
    let emp3_id = emp3.id();
    println!("  ✓ Employee #{}: James Wilson (Driver)", emp3_id);

    Ok((emp1_id, emp2_id, emp3_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 3. SEED: Customers
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_customers(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(u64, u64), Box<dyn std::error::Error>> {
    section("Seeding Customers");

    let mut cust1 = Q::customers()
        .purpose("Seed demo data: private customer")
        .new_entity(ctx);
    cust1.update_first_name("Jane");
    cust1.update_last_name("Smith");
    cust1.update_email("jane.smith@email.com");
    cust1.update_phone("555-200-1001");
    cust1.update_customer_type_to_private();
    cust1.clone()
        .audit_as("Seed: create private customer")
        .save(ctx).await?;
    let cust1_id = cust1.id();
    println!("  ✓ Customer #{}: Jane Smith (Private)", cust1_id);

    let mut cust2 = Q::customers()
        .purpose("Seed demo data: corporate customer")
        .new_entity(ctx);
    cust2.update_company_name("Acme Corp");
    cust2.update_email("moves@acmecorp.com");
    cust2.update_phone("555-200-2002");
    cust2.update_customer_type_to_corporate();
    cust2.clone()
        .audit_as("Seed: create corporate customer")
        .save(ctx).await?;
    let cust2_id = cust2.id();
    println!("  ✓ Customer #{}: Acme Corp (Corporate)", cust2_id);

    Ok((cust1_id, cust2_id))
}

// ─────────────────────────────────────────────────────────────────────────────
// 4. SEED: Routes
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_routes(ctx: &moving_company_service_core::ServiceRuntime, _addr1: u64, addr2: u64, addr3: u64) -> Result<u64, Box<dyn std::error::Error>> {
    section("Seeding Routes");

    let mut route = Q::routes()
        .purpose("Seed demo data: local route")
        .new_entity(ctx);
    route.update_name("Springfield to Chicago");
    route.update_origin_id(addr2);
    route.update_destination_id(addr3);
    route.update_estimated_duration(180);
    route.update_distance_miles(dec(200.0));
    route.clone()
        .audit_as("Seed: create Springfield to Chicago route")
        .save(ctx).await?;
    let route_id = route.id();
    println!("  ✓ Route #{}: Springfield to Chicago (200 miles)", route_id);

    Ok(route_id)
}

// ─────────────────────────────────────────────────────────────────────────────
// 5. SEED: Moving Jobs
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_moving_jobs(ctx: &moving_company_service_core::ServiceRuntime, cust1: u64, route: u64) -> Result<u64, Box<dyn std::error::Error>> {
    section("Seeding Moving Jobs");

    let mut job = Q::moving_jobs()
        .purpose("Seed demo data: residential move")
        .new_entity(ctx);
    job.update_customer_id(cust1);
    job.update_route_id(route);
    job.update_status_to_scheduled();
    job.update_scheduled_date(NaiveDate::from_ymd_opt(2025, 6, 15).unwrap());
    job.update_notes("3-bedroom house move, fragile items included");
    job.clone()
        .audit_as("Seed: create residential moving job")
        .save(ctx).await?;
    let job_id = job.id();
    println!("  ✓ Moving Job #{}: Residential move for Jane Smith", job_id);

    Ok(job_id)
}

// ─────────────────────────────────────────────────────────────────────────────
// 6. SEED: Job Assignments
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_job_assignments(ctx: &moving_company_service_core::ServiceRuntime, job: u64, emp1: u64, emp2: u64, emp3: u64) -> Result<(), Box<dyn std::error::Error>> {
    section("Seeding Job Assignments");

    let mut assign1 = Q::job_assignments()
        .purpose("Seed demo data: lead mover assignment")
        .new_entity(ctx);
    assign1.update_employee_id(emp1);
    assign1.update_moving_job_id(job);
    assign1.update_role("Lead Mover");
    assign1.update_assigned_date(NaiveDate::from_ymd_opt(2025, 6, 10).unwrap());
    assign1.clone()
        .audit_as("Seed: assign lead mover to job")
        .save(ctx).await?;
    println!("  ✓ Assignment #{}: Marcus Rivera as Lead Mover", assign1.id());

    let mut assign2 = Q::job_assignments()
        .purpose("Seed demo data: mover assignment")
        .new_entity(ctx);
    assign2.update_employee_id(emp2);
    assign2.update_moving_job_id(job);
    assign2.update_role("Mover");
    assign2.update_assigned_date(NaiveDate::from_ymd_opt(2025, 6, 10).unwrap());
    assign2.clone()
        .audit_as("Seed: assign mover to job")
        .save(ctx).await?;
    println!("  ✓ Assignment #{}: Sarah Chen as Mover", assign2.id());

    let mut assign3 = Q::job_assignments()
        .purpose("Seed demo data: driver assignment")
        .new_entity(ctx);
    assign3.update_employee_id(emp3);
    assign3.update_moving_job_id(job);
    assign3.update_role("Driver");
    assign3.update_assigned_date(NaiveDate::from_ymd_opt(2025, 6, 10).unwrap());
    assign3.clone()
        .audit_as("Seed: assign driver to job")
        .save(ctx).await?;
    println!("  ✓ Assignment #{}: James Wilson as Driver", assign3.id());

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 7. QUERY: List all employees
// ─────────────────────────────────────────────────────────────────────────────
async fn query_employees(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Querying Employees");

    let employees = Q::employees()
        .purpose("List all active employees")
        .comment("Retrieve employee roster for operations dashboard")
        .execute_for_list(ctx).await?;

    println!("  Found {} employees:", employees.len());
    for emp in employees.iter() {
        println!("    • {} {} - {} (Hired: {})", 
            emp.first_name(), 
            emp.last_name(), 
            emp.position(),
            emp.hire_date()
        );
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 8. QUERY: List all customers
// ─────────────────────────────────────────────────────────────────────────────
async fn query_customers(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Querying Customers");

    let customers = Q::customers()
        .purpose("List all customers")
        .comment("Retrieve customer list for CRM overview")
        .execute_for_list(ctx).await?;

    println!("  Found {} customers:", customers.len());
    for cust in customers.iter() {
        if cust.company_name().is_empty() {
            println!("    • {} {} ({})", 
                cust.first_name(), 
                cust.last_name(), 
                cust.email()
            );
        } else {
            println!("    • {} ({})", 
                cust.company_name(), 
                cust.email()
            );
        }
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 9. QUERY: List moving jobs with status
// ─────────────────────────────────────────────────────────────────────────────
async fn query_moving_jobs(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Querying Moving Jobs");

    let jobs = Q::moving_jobs()
        .purpose("List all moving jobs")
        .comment("Retrieve moving jobs for operations tracking")
        .execute_for_list(ctx).await?;

    println!("  Found {} moving jobs:", jobs.len());
    for job in jobs.iter() {
        println!("    • Job #{}: Scheduled {} - {}", 
            job.id(), 
            job.scheduled_date(),
            job.notes()
        );
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 10. UPDATE: Update job status
// ─────────────────────────────────────────────────────────────────────────────
async fn update_job_status(ctx: &moving_company_service_core::ServiceRuntime, job_id: u64) -> Result<(), Box<dyn std::error::Error>> {
    section("Updating Job Status");

    let mut job = Q::moving_jobs()
        .purpose("Update moving job status to In Progress")
        .comment("Mark job as started when crew arrives")
        .with_id_is(job_id)
        .execute_for_first(ctx).await?
        .ok_or("Job not found")?;

    println!("  Current status: Scheduled");
    job.update_status_to_in_progress();
    job.update_notes("Crew arrived, loading started");
    job.clone()
        .audit_as("Update: mark job as in progress")
        .save(ctx).await?;

    println!("  ✓ Job #{} status updated to In Progress", job_id);

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 11. SEED: Services and Pricing
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_services(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Seeding Services & Pricing");

    let mut svc1 = Q::services()
        .purpose("Seed demo data: standard moving service")
        .new_entity(ctx);
    svc1.update_name("Standard Moving");
    svc1.update_description("Full-service residential moving");
    svc1.update_base_price(dec(500.00));
    svc1.update_service_category_to_moving();
    svc1.clone()
        .audit_as("Seed: create standard moving service")
        .save(ctx).await?;
    println!("  ✓ Service #{}: Standard Moving ($500.00)", svc1.id());

    let mut svc2 = Q::services()
        .purpose("Seed demo data: packing service")
        .new_entity(ctx);
    svc2.update_name("Packing Service");
    svc2.update_description("Professional packing and unpacking");
    svc2.update_base_price(dec(200.00));
    svc2.update_service_category_to_additional();
    svc2.clone()
        .audit_as("Seed: create packing service")
        .save(ctx).await?;
    println!("  ✓ Service #{}: Packing Service ($200.00)", svc2.id());

    let mut svc3 = Q::services()
        .purpose("Seed demo data: cleaning service")
        .new_entity(ctx);
    svc3.update_name("Post-Move Cleaning");
    svc3.update_description("Deep cleaning after move-out");
    svc3.update_base_price(dec(150.00));
    svc3.update_service_category_to_cleaning();
    svc3.clone()
        .audit_as("Seed: create cleaning service")
        .save(ctx).await?;
    println!("  ✓ Service #{}: Post-Move Cleaning ($150.00)", svc3.id());

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 12. SEED: Vehicles
// ─────────────────────────────────────────────────────────────────────────────
async fn seed_vehicles(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Seeding Vehicles");

    let mut veh1 = Q::vehicles()
        .purpose("Seed demo data: large truck")
        .new_entity(ctx);
    veh1.update_make("Ford");
    veh1.update_model("F-650 Box Truck");
    veh1.update_year(2023);
    veh1.update_license_plate("IL-12345");
    veh1.update_vehicle_type_to_box_truck();
    veh1.update_mileage(25000);
    veh1.update_status("available");
    veh1.clone()
        .audit_as("Seed: create large moving truck")
        .save(ctx).await?;
    println!("  ✓ Vehicle #{}: {} {} ({})", veh1.id(), veh1.make(), veh1.model(), veh1.license_plate());

    let mut veh2 = Q::vehicles()
        .purpose("Seed demo data: medium van")
        .new_entity(ctx);
    veh2.update_make("Mercedes");
    veh2.update_model("Sprinter Van");
    veh2.update_year(2024);
    veh2.update_license_plate("IL-67890");
    veh2.update_vehicle_type_to_van();
    veh2.update_mileage(12000);
    veh2.update_status("available");
    veh2.clone()
        .audit_as("Seed: create medium moving van")
        .save(ctx).await?;
    println!("  ✓ Vehicle #{}: {} {} ({})", veh2.id(), veh2.make(), veh2.model(), veh2.license_plate());

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 13. QUERY: List services
// ─────────────────────────────────────────────────────────────────────────────
async fn query_services(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Querying Services");

    let services = Q::services()
        .purpose("List all available services")
        .comment("Retrieve service catalog for customer quotes")
        .execute_for_list(ctx).await?;

    println!("  Found {} services:", services.len());
    for svc in services.iter() {
        println!("    • {}: ${} - {}", 
            svc.name(), 
            svc.base_price(),
            svc.description()
        );
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// 14. QUERY: List vehicles
// ─────────────────────────────────────────────────────────────────────────────
async fn query_vehicles(ctx: &moving_company_service_core::ServiceRuntime) -> Result<(), Box<dyn std::error::Error>> {
    section("Querying Vehicles");

    let vehicles = Q::vehicles()
        .purpose("List all vehicles")
        .comment("Retrieve vehicle fleet for dispatch planning")
        .execute_for_list(ctx).await?;

    println!("  Found {} vehicles:", vehicles.len());
    for veh in vehicles.iter() {
        println!("    • {} {} {} ({}) - {} mi - {}", 
            veh.make(),
            veh.model(),
            veh.year(),
            veh.license_plate(),
            veh.mileage(),
            veh.status()
        );
    }

    Ok(())
}

// ─────────────────────────────────────────────────────────────────────────────
// MAIN
// ─────────────────────────────────────────────────────────────────────────────
#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    banner("MOVING COMPANY PLATFORM - DEMO APPLICATION");
    println!("  Initializing TeaQL runtime...");

    let ctx = moving_company_service_core::service_runtime_from_env().await?;
    ctx.ensure_schema().await?;

    println!("  ✓ Database schema initialized");

    // Seed data
    let (addr1, addr2, addr3) = seed_addresses(&ctx).await?;
    let (emp1, emp2, emp3) = seed_employees(&ctx).await?;
    let (cust1, _cust2) = seed_customers(&ctx).await?;
    let route = seed_routes(&ctx, addr1, addr2, addr3).await?;
    let job = seed_moving_jobs(&ctx, cust1, route).await?;
    seed_job_assignments(&ctx, job, emp1, emp2, emp3).await?;
    seed_services(&ctx).await?;
    seed_vehicles(&ctx).await?;

    // Query demonstrations
    query_employees(&ctx).await?;
    query_customers(&ctx).await?;
    query_moving_jobs(&ctx).await?;
    query_services(&ctx).await?;
    query_vehicles(&ctx).await?;

    // Update demonstration
    update_job_status(&ctx, job).await?;

    banner("DEMO COMPLETE");
    println!("  All operations completed successfully!");
    println!("  Database: moving_company.db");
    println!("{}", "═".repeat(70));

    Ok(())
}
