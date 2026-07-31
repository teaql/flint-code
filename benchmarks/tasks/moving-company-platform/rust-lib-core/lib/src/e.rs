// The `E` expression wrapper provides zero-cost AST traversal
// and will automatically panic if it encounters a NotLoaded error.
pub struct E;

impl E {
    pub fn address<'a>(value: &'a crate::Address) -> crate::AddressExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Address(id={})", value.id()));
        crate::AddressExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn route<'a>(value: &'a crate::Route) -> crate::RouteExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Route(id={})", value.id()));
        crate::RouteExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn time_slot<'a>(value: &'a crate::TimeSlot) -> crate::TimeSlotExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("TimeSlot(id={})", value.id()));
        crate::TimeSlotExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn move_status<'a>(value: &'a crate::MoveStatus) -> crate::MoveStatusExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("MoveStatus(id={})", value.id()));
        crate::MoveStatusExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn moving_job<'a>(value: &'a crate::MovingJob) -> crate::MovingJobExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("MovingJob(id={})", value.id()));
        crate::MovingJobExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn fulfillment_event<'a>(value: &'a crate::FulfillmentEvent) -> crate::FulfillmentEventExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("FulfillmentEvent(id={})", value.id()));
        crate::FulfillmentEventExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn employee<'a>(value: &'a crate::Employee) -> crate::EmployeeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Employee(id={})", value.id()));
        crate::EmployeeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn job_assignment<'a>(value: &'a crate::JobAssignment) -> crate::JobAssignmentExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("JobAssignment(id={})", value.id()));
        crate::JobAssignmentExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn worked_hours<'a>(value: &'a crate::WorkedHours) -> crate::WorkedHoursExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("WorkedHours(id={})", value.id()));
        crate::WorkedHoursExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn payroll_calculation<'a>(value: &'a crate::PayrollCalculation) -> crate::PayrollCalculationExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("PayrollCalculation(id={})", value.id()));
        crate::PayrollCalculationExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn bonus<'a>(value: &'a crate::Bonus) -> crate::BonusExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Bonus(id={})", value.id()));
        crate::BonusExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn leave_type<'a>(value: &'a crate::LeaveType) -> crate::LeaveTypeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("LeaveType(id={})", value.id()));
        crate::LeaveTypeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn leave_request<'a>(value: &'a crate::LeaveRequest) -> crate::LeaveRequestExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("LeaveRequest(id={})", value.id()));
        crate::LeaveRequestExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn customer_type<'a>(value: &'a crate::CustomerType) -> crate::CustomerTypeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("CustomerType(id={})", value.id()));
        crate::CustomerTypeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn customer<'a>(value: &'a crate::Customer) -> crate::CustomerExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Customer(id={})", value.id()));
        crate::CustomerExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn customer_contact<'a>(value: &'a crate::CustomerContact) -> crate::CustomerContactExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("CustomerContact(id={})", value.id()));
        crate::CustomerContactExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn billing_info<'a>(value: &'a crate::BillingInfo) -> crate::BillingInfoExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("BillingInfo(id={})", value.id()));
        crate::BillingInfoExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn customer_history<'a>(value: &'a crate::CustomerHistory) -> crate::CustomerHistoryExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("CustomerHistory(id={})", value.id()));
        crate::CustomerHistoryExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn service_category<'a>(value: &'a crate::ServiceCategory) -> crate::ServiceCategoryExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("ServiceCategory(id={})", value.id()));
        crate::ServiceCategoryExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn service<'a>(value: &'a crate::Service) -> crate::ServiceExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Service(id={})", value.id()));
        crate::ServiceExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn service_configuration<'a>(value: &'a crate::ServiceConfiguration) -> crate::ServiceConfigurationExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("ServiceConfiguration(id={})", value.id()));
        crate::ServiceConfigurationExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn pricing_rule<'a>(value: &'a crate::PricingRule) -> crate::PricingRuleExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("PricingRule(id={})", value.id()));
        crate::PricingRuleExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn box_type<'a>(value: &'a crate::BoxType) -> crate::BoxTypeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("BoxType(id={})", value.id()));
        crate::BoxTypeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn box_rental<'a>(value: &'a crate::BoxRental) -> crate::BoxRentalExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("BoxRental(id={})", value.id()));
        crate::BoxRentalExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn campaign<'a>(value: &'a crate::Campaign) -> crate::CampaignExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Campaign(id={})", value.id()));
        crate::CampaignExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn discount_code<'a>(value: &'a crate::DiscountCode) -> crate::DiscountCodeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("DiscountCode(id={})", value.id()));
        crate::DiscountCodeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn lead<'a>(value: &'a crate::Lead) -> crate::LeadExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Lead(id={})", value.id()));
        crate::LeadExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn lead_status<'a>(value: &'a crate::LeadStatus) -> crate::LeadStatusExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("LeadStatus(id={})", value.id()));
        crate::LeadStatusExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn conversion_metric<'a>(value: &'a crate::ConversionMetric) -> crate::ConversionMetricExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("ConversionMetric(id={})", value.id()));
        crate::ConversionMetricExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn payment_method<'a>(value: &'a crate::PaymentMethod) -> crate::PaymentMethodExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("PaymentMethod(id={})", value.id()));
        crate::PaymentMethodExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn payment<'a>(value: &'a crate::Payment) -> crate::PaymentExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Payment(id={})", value.id()));
        crate::PaymentExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn invoice<'a>(value: &'a crate::Invoice) -> crate::InvoiceExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Invoice(id={})", value.id()));
        crate::InvoiceExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn expense_category<'a>(value: &'a crate::ExpenseCategory) -> crate::ExpenseCategoryExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("ExpenseCategory(id={})", value.id()));
        crate::ExpenseCategoryExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn expense<'a>(value: &'a crate::Expense) -> crate::ExpenseExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Expense(id={})", value.id()));
        crate::ExpenseExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn vat_rate<'a>(value: &'a crate::VatRate) -> crate::VatRateExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("VatRate(id={})", value.id()));
        crate::VatRateExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn financial_summary<'a>(value: &'a crate::FinancialSummary) -> crate::FinancialSummaryExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("FinancialSummary(id={})", value.id()));
        crate::FinancialSummaryExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn vehicle_type<'a>(value: &'a crate::VehicleType) -> crate::VehicleTypeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("VehicleType(id={})", value.id()));
        crate::VehicleTypeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn vehicle<'a>(value: &'a crate::Vehicle) -> crate::VehicleExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Vehicle(id={})", value.id()));
        crate::VehicleExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn equipment<'a>(value: &'a crate::Equipment) -> crate::EquipmentExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Equipment(id={})", value.id()));
        crate::EquipmentExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn consumable<'a>(value: &'a crate::Consumable) -> crate::ConsumableExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Consumable(id={})", value.id()));
        crate::ConsumableExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn maintenance_status<'a>(value: &'a crate::MaintenanceStatus) -> crate::MaintenanceStatusExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("MaintenanceStatus(id={})", value.id()));
        crate::MaintenanceStatusExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn maintenance_schedule<'a>(value: &'a crate::MaintenanceSchedule) -> crate::MaintenanceScheduleExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("MaintenanceSchedule(id={})", value.id()));
        crate::MaintenanceScheduleExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn contract_status<'a>(value: &'a crate::ContractStatus) -> crate::ContractStatusExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("ContractStatus(id={})", value.id()));
        crate::ContractStatusExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn contract<'a>(value: &'a crate::Contract) -> crate::ContractExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Contract(id={})", value.id()));
        crate::ContractExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn insurance_policy<'a>(value: &'a crate::InsurancePolicy) -> crate::InsurancePolicyExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("InsurancePolicy(id={})", value.id()));
        crate::InsurancePolicyExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn document_type<'a>(value: &'a crate::DocumentType) -> crate::DocumentTypeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("DocumentType(id={})", value.id()));
        crate::DocumentTypeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn document<'a>(value: &'a crate::Document) -> crate::DocumentExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Document(id={})", value.id()));
        crate::DocumentExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn audit_log<'a>(value: &'a crate::AuditLog) -> crate::AuditLogExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("AuditLog(id={})", value.id()));
        crate::AuditLogExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn role<'a>(value: &'a crate::Role) -> crate::RoleExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Role(id={})", value.id()));
        crate::RoleExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn user<'a>(value: &'a crate::User) -> crate::UserExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("User(id={})", value.id()));
        crate::UserExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn permission<'a>(value: &'a crate::Permission) -> crate::PermissionExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Permission(id={})", value.id()));
        crate::PermissionExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn role_permission<'a>(value: &'a crate::RolePermission) -> crate::RolePermissionExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("RolePermission(id={})", value.id()));
        crate::RolePermissionExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn magic_link<'a>(value: &'a crate::MagicLink) -> crate::MagicLinkExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("MagicLink(id={})", value.id()));
        crate::MagicLinkExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn activity_log<'a>(value: &'a crate::ActivityLog) -> crate::ActivityLogExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("ActivityLog(id={})", value.id()));
        crate::ActivityLogExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn notification_type<'a>(value: &'a crate::NotificationType) -> crate::NotificationTypeExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("NotificationType(id={})", value.id()));
        crate::NotificationTypeExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn notification<'a>(value: &'a crate::Notification) -> crate::NotificationExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("Notification(id={})", value.id()));
        crate::NotificationExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }

    pub fn automation_hook<'a>(value: &'a crate::AutomationHook) -> crate::AutomationHookExpression<'a> {
        let root_desc = std::sync::Arc::new(format!("AutomationHook(id={})", value.id()));
        crate::AutomationHookExpression::new(teaql_core::eval::EvalResult::Value(value), root_desc)
    }
}


pub fn trigger_logic_bug_panic(root_desc: &str, failed_node: &str, attempted_path: &str) -> ! {
    let parts: Vec<&str> = attempted_path.split('.').collect();
    let break_idx = parts.iter().position(|&p| p == failed_node).unwrap_or(0);

    let mut nested_fix = String::new();
    if break_idx < parts.len() - 1 {
        nested_fix.push_str(&format!("\"select_{}(", failed_node));
        let mut close_parens = 1;
        for i in (break_idx + 1)..parts.len() {
            let sub_field = parts[i];
            let prev_field = parts[i-1];
            let is_last = i == parts.len() - 1;
            if is_last {
                nested_fix.push_str(&format!("Q::{}s().select_{}()", prev_field, sub_field));
            } else {
                nested_fix.push_str(&format!("Q::{}s().select_{}(", prev_field, sub_field));
                close_parens += 1;
            }
        }
        for _ in 0..close_parens {
            nested_fix.push(')');
        }
        nested_fix.push('"');
    } else {
        nested_fix = "null".to_string();
    }

    let suggested_fix = format!("\"select_{}()\"", failed_node);

    let access_path_json = format!("[{}]", parts.iter().map(|s| format!("\"{}\"", s)).collect::<Vec<_>>().join(", "));
    let missing_preload_json = format!("[\"{}\"]", failed_node);

    let human_nested = if nested_fix != "null" { format!(" 或完整嵌套加载 {}", nested_fix) } else { String::new() };
    let root_name = root_desc.split('(').next().unwrap_or("Unknown");

    let mut root_snake = String::new();
    for (i, c) in root_name.chars().enumerate() {
        if c.is_uppercase() {
            if i > 0 {
                root_snake.push('_');
            }
            root_snake.push(c.to_ascii_lowercase());
        } else {
            root_snake.push(c);
        }
    }
    let id_part = root_desc.split('(').nth(1).unwrap_or(")");
    let mut original_expr = format!("E::{}({}", root_snake, id_part);
    for p in &parts {
        original_expr.push_str(&format!(".get_{}()", p));
        if *p == failed_node {
            original_expr.push_str("<broken>");
        }
    }

    let human_message = format!("\"访问 {}.{} 时缺少预加载。请在查询中加入 {}{}\"", root_name, attempted_path, suggested_fix, human_nested);

    panic!("\n\n💥 [Coding Logic Bug]\n\noriginal_expr_with_broken_point: \"{}\"\nroot: {}\naccess_path: {}\nbreak_point: \"{}\"\nmissing_preload: {}\nsuggested_fix: {}\nnested_fix: {}\nseverity: \"error\"\nhuman_message: {}\n", 
        original_expr, root_desc, access_path_json, failed_node, missing_preload_json, suggested_fix, nested_fix, human_message);
}

#[derive(Clone)]
pub struct ValueExpression<'a, T> {
    result: teaql_core::eval::EvalResult<T>,
    root_desc: std::sync::Arc<String>,
    _phantom: std::marker::PhantomData<&'a ()>,
}

impl<'a, T: Clone> ValueExpression<'a, T> {
    pub fn new(result: teaql_core::eval::EvalResult<T>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc, _phantom: std::marker::PhantomData }
    }

    fn resolve(self) -> Option<T> {
        match self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(self) -> Option<T> {
        self.resolve()
    }

    pub fn unwrap(self) -> T {
        self.resolve().expect("Value was legitimately null in database!")
    }

    pub fn or_else(self, default_value: T) -> T {
        self.eval().unwrap_or(default_value)
    }

    pub fn or_else_with(self, default_fn: impl FnOnce() -> T) -> T {
        self.eval().unwrap_or_else(default_fn)
    }

    pub fn or_default(self) -> T where T: Default {
        self.eval().unwrap_or_default()
    }
}

