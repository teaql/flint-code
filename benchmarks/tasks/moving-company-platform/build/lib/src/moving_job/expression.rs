#[derive(Clone)]
pub struct MovingJobExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::MovingJob>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> MovingJobExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::MovingJob>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::MovingJob> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::MovingJob> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::MovingJob {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_scheduled_date(self) -> crate::ValueExpression<'a, chrono::NaiveDate> {
        let next = self.result.and_then("scheduled_date", |entity| entity.eval_scheduled_date());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_notes(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("notes", |entity| entity.eval_notes());
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

    pub fn get_route_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("route_id", |entity| entity.eval_route_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_time_slot_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("time_slot_id", |entity| entity.eval_time_slot_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_status_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("status_id", |entity| entity.eval_status_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_customer(self) -> crate::CustomerExpression<'a> {
        let next = self.result.and_then("customer", |entity| entity.eval_customer());
        crate::CustomerExpression::new(next, self.root_desc.clone())
    }

    pub fn get_route(self) -> crate::RouteExpression<'a> {
        let next = self.result.and_then("route", |entity| entity.eval_route());
        crate::RouteExpression::new(next, self.root_desc.clone())
    }

    pub fn get_time_slot(self) -> crate::TimeSlotExpression<'a> {
        let next = self.result.and_then("time_slot", |entity| entity.eval_time_slot());
        crate::TimeSlotExpression::new(next, self.root_desc.clone())
    }

    pub fn get_status(self) -> crate::MoveStatusExpression<'a> {
        let next = self.result.and_then("status", |entity| entity.eval_status());
        crate::MoveStatusExpression::new(next, self.root_desc.clone())
    }
    pub fn status_is_scheduled(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("status_id", |entity| {
            if !entity.is_loaded("status_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "status_id".to_string(), attempted_path: "status_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.status_is_scheduled())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn status_is_in_progress(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("status_id", |entity| {
            if !entity.is_loaded("status_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "status_id".to_string(), attempted_path: "status_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.status_is_in_progress())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn status_is_completed(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("status_id", |entity| {
            if !entity.is_loaded("status_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "status_id".to_string(), attempted_path: "status_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.status_is_completed())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn status_is_cancelled(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("status_id", |entity| {
            if !entity.is_loaded("status_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "status_id".to_string(), attempted_path: "status_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.status_is_cancelled())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_fulfillment_event_list(self) -> crate::FulfillmentEventListExpression<'a> {
        let next = self.result.and_then("fulfillment_event_list", |entity| entity.eval_fulfillment_event_list());
        crate::FulfillmentEventListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_job_assignment_list(self) -> crate::JobAssignmentListExpression<'a> {
        let next = self.result.and_then("job_assignment_list", |entity| entity.eval_job_assignment_list());
        crate::JobAssignmentListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_payment_list(self) -> crate::PaymentListExpression<'a> {
        let next = self.result.and_then("payment_list", |entity| entity.eval_payment_list());
        crate::PaymentListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_invoice_list(self) -> crate::InvoiceListExpression<'a> {
        let next = self.result.and_then("invoice_list", |entity| entity.eval_invoice_list());
        crate::InvoiceListExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct MovingJobListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::MovingJob>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> MovingJobListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::MovingJob>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::MovingJob>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::MovingJob>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::MovingJob> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::MovingJobExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::MovingJobExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::MovingJobExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::MovingJobExpression::new(next, self.root_desc.clone())
    }
}