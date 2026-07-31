use teaql_core::Expr;

use crate::*;

pub struct PurposedQuery<T> {
    pub inner: T,
    pub purpose: String,
}

impl<T> PurposedQuery<T> {
    pub fn new(inner: T, purpose: impl Into<String>) -> Self {
        Self { inner, purpose: purpose.into() }
    }
}

pub struct Q;

impl Q {
    pub fn addresses() -> AddressRequest {
        AddressRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn addresses_minimal() -> AddressRequest {
        AddressRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn addresses_with_children() -> AddressRequest {
        AddressRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn routes() -> RouteRequest {
        RouteRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn routes_minimal() -> RouteRequest {
        RouteRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn routes_with_children() -> RouteRequest {
        RouteRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn time_slots() -> TimeSlotRequest {
        TimeSlotRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn time_slots_minimal() -> TimeSlotRequest {
        TimeSlotRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn time_slots_with_children() -> TimeSlotRequest {
        TimeSlotRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn move_statuses() -> MoveStatusRequest {
        MoveStatusRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn move_statuses_minimal() -> MoveStatusRequest {
        MoveStatusRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn move_statuses_with_children() -> MoveStatusRequest {
        MoveStatusRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn moving_jobs() -> MovingJobRequest {
        MovingJobRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn moving_jobs_minimal() -> MovingJobRequest {
        MovingJobRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn moving_jobs_with_children() -> MovingJobRequest {
        MovingJobRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn fulfillment_events() -> FulfillmentEventRequest {
        FulfillmentEventRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn fulfillment_events_minimal() -> FulfillmentEventRequest {
        FulfillmentEventRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn fulfillment_events_with_children() -> FulfillmentEventRequest {
        FulfillmentEventRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn employees() -> EmployeeRequest {
        EmployeeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn employees_minimal() -> EmployeeRequest {
        EmployeeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn employees_with_children() -> EmployeeRequest {
        EmployeeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn job_assignments() -> JobAssignmentRequest {
        JobAssignmentRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn job_assignments_minimal() -> JobAssignmentRequest {
        JobAssignmentRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn job_assignments_with_children() -> JobAssignmentRequest {
        JobAssignmentRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn worked_hourses() -> WorkedHoursRequest {
        WorkedHoursRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn worked_hourses_minimal() -> WorkedHoursRequest {
        WorkedHoursRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn worked_hourses_with_children() -> WorkedHoursRequest {
        WorkedHoursRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn payroll_calculations() -> PayrollCalculationRequest {
        PayrollCalculationRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn payroll_calculations_minimal() -> PayrollCalculationRequest {
        PayrollCalculationRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn payroll_calculations_with_children() -> PayrollCalculationRequest {
        PayrollCalculationRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn bonuses() -> BonusRequest {
        BonusRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn bonuses_minimal() -> BonusRequest {
        BonusRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn bonuses_with_children() -> BonusRequest {
        BonusRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn leave_types() -> LeaveTypeRequest {
        LeaveTypeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn leave_types_minimal() -> LeaveTypeRequest {
        LeaveTypeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn leave_types_with_children() -> LeaveTypeRequest {
        LeaveTypeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn leave_requests() -> LeaveRequestRequest {
        LeaveRequestRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn leave_requests_minimal() -> LeaveRequestRequest {
        LeaveRequestRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn leave_requests_with_children() -> LeaveRequestRequest {
        LeaveRequestRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn customer_types() -> CustomerTypeRequest {
        CustomerTypeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customer_types_minimal() -> CustomerTypeRequest {
        CustomerTypeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customer_types_with_children() -> CustomerTypeRequest {
        CustomerTypeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn customers() -> CustomerRequest {
        CustomerRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customers_minimal() -> CustomerRequest {
        CustomerRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customers_with_children() -> CustomerRequest {
        CustomerRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn customer_contacts() -> CustomerContactRequest {
        CustomerContactRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customer_contacts_minimal() -> CustomerContactRequest {
        CustomerContactRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customer_contacts_with_children() -> CustomerContactRequest {
        CustomerContactRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn billing_info() -> BillingInfoRequest {
        BillingInfoRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn billing_info_minimal() -> BillingInfoRequest {
        BillingInfoRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn billing_info_with_children() -> BillingInfoRequest {
        BillingInfoRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn customer_histories() -> CustomerHistoryRequest {
        CustomerHistoryRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customer_histories_minimal() -> CustomerHistoryRequest {
        CustomerHistoryRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn customer_histories_with_children() -> CustomerHistoryRequest {
        CustomerHistoryRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn service_categories() -> ServiceCategoryRequest {
        ServiceCategoryRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn service_categories_minimal() -> ServiceCategoryRequest {
        ServiceCategoryRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn service_categories_with_children() -> ServiceCategoryRequest {
        ServiceCategoryRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn services() -> ServiceRequest {
        ServiceRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn services_minimal() -> ServiceRequest {
        ServiceRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn services_with_children() -> ServiceRequest {
        ServiceRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn service_configurations() -> ServiceConfigurationRequest {
        ServiceConfigurationRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn service_configurations_minimal() -> ServiceConfigurationRequest {
        ServiceConfigurationRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn service_configurations_with_children() -> ServiceConfigurationRequest {
        ServiceConfigurationRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn pricing_rules() -> PricingRuleRequest {
        PricingRuleRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn pricing_rules_minimal() -> PricingRuleRequest {
        PricingRuleRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn pricing_rules_with_children() -> PricingRuleRequest {
        PricingRuleRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn box_types() -> BoxTypeRequest {
        BoxTypeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn box_types_minimal() -> BoxTypeRequest {
        BoxTypeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn box_types_with_children() -> BoxTypeRequest {
        BoxTypeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn box_rentals() -> BoxRentalRequest {
        BoxRentalRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn box_rentals_minimal() -> BoxRentalRequest {
        BoxRentalRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn box_rentals_with_children() -> BoxRentalRequest {
        BoxRentalRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn campaigns() -> CampaignRequest {
        CampaignRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn campaigns_minimal() -> CampaignRequest {
        CampaignRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn campaigns_with_children() -> CampaignRequest {
        CampaignRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn discount_codes() -> DiscountCodeRequest {
        DiscountCodeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn discount_codes_minimal() -> DiscountCodeRequest {
        DiscountCodeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn discount_codes_with_children() -> DiscountCodeRequest {
        DiscountCodeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn leads() -> LeadRequest {
        LeadRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn leads_minimal() -> LeadRequest {
        LeadRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn leads_with_children() -> LeadRequest {
        LeadRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn lead_statuses() -> LeadStatusRequest {
        LeadStatusRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn lead_statuses_minimal() -> LeadStatusRequest {
        LeadStatusRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn lead_statuses_with_children() -> LeadStatusRequest {
        LeadStatusRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn conversion_metrics() -> ConversionMetricRequest {
        ConversionMetricRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn conversion_metrics_minimal() -> ConversionMetricRequest {
        ConversionMetricRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn conversion_metrics_with_children() -> ConversionMetricRequest {
        ConversionMetricRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn payment_methods() -> PaymentMethodRequest {
        PaymentMethodRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn payment_methods_minimal() -> PaymentMethodRequest {
        PaymentMethodRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn payment_methods_with_children() -> PaymentMethodRequest {
        PaymentMethodRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn payments() -> PaymentRequest {
        PaymentRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn payments_minimal() -> PaymentRequest {
        PaymentRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn payments_with_children() -> PaymentRequest {
        PaymentRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn invoices() -> InvoiceRequest {
        InvoiceRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn invoices_minimal() -> InvoiceRequest {
        InvoiceRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn invoices_with_children() -> InvoiceRequest {
        InvoiceRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn expense_categories() -> ExpenseCategoryRequest {
        ExpenseCategoryRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn expense_categories_minimal() -> ExpenseCategoryRequest {
        ExpenseCategoryRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn expense_categories_with_children() -> ExpenseCategoryRequest {
        ExpenseCategoryRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn expenses() -> ExpenseRequest {
        ExpenseRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn expenses_minimal() -> ExpenseRequest {
        ExpenseRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn expenses_with_children() -> ExpenseRequest {
        ExpenseRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn vat_rates() -> VatRateRequest {
        VatRateRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn vat_rates_minimal() -> VatRateRequest {
        VatRateRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn vat_rates_with_children() -> VatRateRequest {
        VatRateRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn financial_summaries() -> FinancialSummaryRequest {
        FinancialSummaryRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn financial_summaries_minimal() -> FinancialSummaryRequest {
        FinancialSummaryRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn financial_summaries_with_children() -> FinancialSummaryRequest {
        FinancialSummaryRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn vehicle_types() -> VehicleTypeRequest {
        VehicleTypeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn vehicle_types_minimal() -> VehicleTypeRequest {
        VehicleTypeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn vehicle_types_with_children() -> VehicleTypeRequest {
        VehicleTypeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn vehicles() -> VehicleRequest {
        VehicleRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn vehicles_minimal() -> VehicleRequest {
        VehicleRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn vehicles_with_children() -> VehicleRequest {
        VehicleRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn equipment() -> EquipmentRequest {
        EquipmentRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn equipment_minimal() -> EquipmentRequest {
        EquipmentRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn equipment_with_children() -> EquipmentRequest {
        EquipmentRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn consumables() -> ConsumableRequest {
        ConsumableRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn consumables_minimal() -> ConsumableRequest {
        ConsumableRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn consumables_with_children() -> ConsumableRequest {
        ConsumableRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn maintenance_statuses() -> MaintenanceStatusRequest {
        MaintenanceStatusRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn maintenance_statuses_minimal() -> MaintenanceStatusRequest {
        MaintenanceStatusRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn maintenance_statuses_with_children() -> MaintenanceStatusRequest {
        MaintenanceStatusRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn maintenance_schedules() -> MaintenanceScheduleRequest {
        MaintenanceScheduleRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn maintenance_schedules_minimal() -> MaintenanceScheduleRequest {
        MaintenanceScheduleRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn maintenance_schedules_with_children() -> MaintenanceScheduleRequest {
        MaintenanceScheduleRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn contract_statuses() -> ContractStatusRequest {
        ContractStatusRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn contract_statuses_minimal() -> ContractStatusRequest {
        ContractStatusRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn contract_statuses_with_children() -> ContractStatusRequest {
        ContractStatusRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn contracts() -> ContractRequest {
        ContractRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn contracts_minimal() -> ContractRequest {
        ContractRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn contracts_with_children() -> ContractRequest {
        ContractRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn insurance_policies() -> InsurancePolicyRequest {
        InsurancePolicyRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn insurance_policies_minimal() -> InsurancePolicyRequest {
        InsurancePolicyRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn insurance_policies_with_children() -> InsurancePolicyRequest {
        InsurancePolicyRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn document_types() -> DocumentTypeRequest {
        DocumentTypeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn document_types_minimal() -> DocumentTypeRequest {
        DocumentTypeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn document_types_with_children() -> DocumentTypeRequest {
        DocumentTypeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn documents() -> DocumentRequest {
        DocumentRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn documents_minimal() -> DocumentRequest {
        DocumentRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn documents_with_children() -> DocumentRequest {
        DocumentRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn audit_logs() -> AuditLogRequest {
        AuditLogRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn audit_logs_minimal() -> AuditLogRequest {
        AuditLogRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn audit_logs_with_children() -> AuditLogRequest {
        AuditLogRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn roles() -> RoleRequest {
        RoleRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn roles_minimal() -> RoleRequest {
        RoleRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn roles_with_children() -> RoleRequest {
        RoleRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn users() -> UserRequest {
        UserRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn users_minimal() -> UserRequest {
        UserRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn users_with_children() -> UserRequest {
        UserRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn permissions() -> PermissionRequest {
        PermissionRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn permissions_minimal() -> PermissionRequest {
        PermissionRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn permissions_with_children() -> PermissionRequest {
        PermissionRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn role_permissions() -> RolePermissionRequest {
        RolePermissionRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn role_permissions_minimal() -> RolePermissionRequest {
        RolePermissionRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn role_permissions_with_children() -> RolePermissionRequest {
        RolePermissionRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn magic_links() -> MagicLinkRequest {
        MagicLinkRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn magic_links_minimal() -> MagicLinkRequest {
        MagicLinkRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn magic_links_with_children() -> MagicLinkRequest {
        MagicLinkRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn activity_logs() -> ActivityLogRequest {
        ActivityLogRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn activity_logs_minimal() -> ActivityLogRequest {
        ActivityLogRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn activity_logs_with_children() -> ActivityLogRequest {
        ActivityLogRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn notification_types() -> NotificationTypeRequest {
        NotificationTypeRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn notification_types_minimal() -> NotificationTypeRequest {
        NotificationTypeRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn notification_types_with_children() -> NotificationTypeRequest {
        NotificationTypeRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn notifications() -> NotificationRequest {
        NotificationRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn notifications_minimal() -> NotificationRequest {
        NotificationRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn notifications_with_children() -> NotificationRequest {
        NotificationRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }

    pub fn automation_hooks() -> AutomationHookRequest {
        AutomationHookRequest::new()
            .select_self()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn automation_hooks_minimal() -> AutomationHookRequest {
        AutomationHookRequest::new()
            .and_filter(Expr::gt("version", 0_i64))
    }

    pub fn automation_hooks_with_children() -> AutomationHookRequest {
        AutomationHookRequest::new()
            .unlimited()
            .select_self_fields()
            .enhance_children_if_needed()
    }
}