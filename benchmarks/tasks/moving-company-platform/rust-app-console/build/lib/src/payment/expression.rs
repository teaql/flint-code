#[derive(Clone)]
pub struct PaymentExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::Payment>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> PaymentExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::Payment>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::Payment> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::Payment> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::Payment {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_amount(self) -> crate::ValueExpression<'a, rust_decimal::Decimal> {
        let next = self.result.and_then("amount", |entity| entity.eval_amount());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_payment_date(self) -> crate::ValueExpression<'a, chrono::NaiveDate> {
        let next = self.result.and_then("payment_date", |entity| entity.eval_payment_date());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_transaction_id(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("transaction_id", |entity| entity.eval_transaction_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_status(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("status", |entity| entity.eval_status());
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
    pub fn get_customer_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("customer_id", |entity| entity.eval_customer_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_moving_job_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("moving_job_id", |entity| entity.eval_moving_job_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_payment_method_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("payment_method_id", |entity| entity.eval_payment_method_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_customer(self) -> crate::CustomerExpression<'a> {
        let next = self.result.and_then("customer", |entity| entity.eval_customer());
        crate::CustomerExpression::new(next, self.root_desc.clone())
    }

    pub fn get_moving_job(self) -> crate::MovingJobExpression<'a> {
        let next = self.result.and_then("moving_job", |entity| entity.eval_moving_job());
        crate::MovingJobExpression::new(next, self.root_desc.clone())
    }

    pub fn get_payment_method(self) -> crate::PaymentMethodExpression<'a> {
        let next = self.result.and_then("payment_method", |entity| entity.eval_payment_method());
        crate::PaymentMethodExpression::new(next, self.root_desc.clone())
    }
    pub fn payment_method_is_credit_card(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("payment_method_id", |entity| {
            if !entity.is_loaded("payment_method_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "payment_method_id".to_string(), attempted_path: "payment_method_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.payment_method_is_credit_card())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn payment_method_is_bank_transfer(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("payment_method_id", |entity| {
            if !entity.is_loaded("payment_method_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "payment_method_id".to_string(), attempted_path: "payment_method_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.payment_method_is_bank_transfer())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn payment_method_is_cash(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("payment_method_id", |entity| {
            if !entity.is_loaded("payment_method_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "payment_method_id".to_string(), attempted_path: "payment_method_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.payment_method_is_cash())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn payment_method_is_check(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("payment_method_id", |entity| {
            if !entity.is_loaded("payment_method_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "payment_method_id".to_string(), attempted_path: "payment_method_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.payment_method_is_check())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct PaymentListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Payment>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> PaymentListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Payment>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::Payment>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::Payment>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::Payment> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::PaymentExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::PaymentExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::PaymentExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::PaymentExpression::new(next, self.root_desc.clone())
    }
}