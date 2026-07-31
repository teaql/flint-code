#[derive(Clone)]
pub struct CustomerExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::Customer>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> CustomerExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::Customer>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::Customer> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::Customer> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::Customer {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_first_name(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("first_name", |entity| entity.eval_first_name());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_last_name(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("last_name", |entity| entity.eval_last_name());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_email(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("email", |entity| entity.eval_email());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_phone(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("phone", |entity| entity.eval_phone());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_company_name(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("company_name", |entity| entity.eval_company_name());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_create_time(self) -> crate::ValueExpression<'a, chrono::DateTime<chrono::Utc>> {
        let next = self.result.and_then("create_time", |entity| entity.eval_create_time());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_update_time(self) -> crate::ValueExpression<'a, chrono::DateTime<chrono::Utc>> {
        let next = self.result.and_then("update_time", |entity| entity.eval_update_time());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_version(self) -> crate::ValueExpression<'a, i64> {
        let next = self.result.and_then("version", |entity| entity.eval_version());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_customer_type_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("customer_type_id", |entity| entity.eval_customer_type_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_customer_type(self) -> crate::CustomerTypeExpression<'a> {
        let next = self.result.and_then("customer_type", |entity| entity.eval_customer_type());
        crate::CustomerTypeExpression::new(next, self.root_desc.clone())
    }
    pub fn customer_type_is_private(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("customer_type_id", |entity| {
            if !entity.is_loaded("customer_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_type_id".to_string(), attempted_path: "customer_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.customer_type_is_private())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn customer_type_is_corporate(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("customer_type_id", |entity| {
            if !entity.is_loaded("customer_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_type_id".to_string(), attempted_path: "customer_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.customer_type_is_corporate())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_moving_job_list(self) -> crate::MovingJobListExpression<'a> {
        let next = self.result.and_then("moving_job_list", |entity| entity.eval_moving_job_list());
        crate::MovingJobListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_customer_contact_list(self) -> crate::CustomerContactListExpression<'a> {
        let next = self.result.and_then("customer_contact_list", |entity| entity.eval_customer_contact_list());
        crate::CustomerContactListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_billing_info_list(self) -> crate::BillingInfoListExpression<'a> {
        let next = self.result.and_then("billing_info_list", |entity| entity.eval_billing_info_list());
        crate::BillingInfoListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_customer_history_list(self) -> crate::CustomerHistoryListExpression<'a> {
        let next = self.result.and_then("customer_history_list", |entity| entity.eval_customer_history_list());
        crate::CustomerHistoryListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_box_rental_list(self) -> crate::BoxRentalListExpression<'a> {
        let next = self.result.and_then("box_rental_list", |entity| entity.eval_box_rental_list());
        crate::BoxRentalListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_payment_list(self) -> crate::PaymentListExpression<'a> {
        let next = self.result.and_then("payment_list", |entity| entity.eval_payment_list());
        crate::PaymentListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_invoice_list(self) -> crate::InvoiceListExpression<'a> {
        let next = self.result.and_then("invoice_list", |entity| entity.eval_invoice_list());
        crate::InvoiceListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_contract_list(self) -> crate::ContractListExpression<'a> {
        let next = self.result.and_then("contract_list", |entity| entity.eval_contract_list());
        crate::ContractListExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct CustomerListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Customer>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> CustomerListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Customer>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::Customer>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::Customer>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::Customer> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::CustomerExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::CustomerExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::CustomerExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::CustomerExpression::new(next, self.root_desc.clone())
    }
}