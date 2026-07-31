#[derive(Clone)]
pub struct MaintenanceScheduleExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::MaintenanceSchedule>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> MaintenanceScheduleExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::MaintenanceSchedule>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::MaintenanceSchedule> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::MaintenanceSchedule> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::MaintenanceSchedule {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_maintenance_type(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("maintenance_type", |entity| entity.eval_maintenance_type());
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
    pub fn get_vehicle_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("vehicle_id", |entity| entity.eval_vehicle_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_status_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("status_id", |entity| entity.eval_status_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_vehicle(self) -> crate::VehicleExpression<'a> {
        let next = self.result.and_then("vehicle", |entity| entity.eval_vehicle());
        crate::VehicleExpression::new(next, self.root_desc.clone())
    }

    pub fn get_status(self) -> crate::MaintenanceStatusExpression<'a> {
        let next = self.result.and_then("status", |entity| entity.eval_status());
        crate::MaintenanceStatusExpression::new(next, self.root_desc.clone())
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
}

#[derive(Clone)]
pub struct MaintenanceScheduleListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::MaintenanceSchedule>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> MaintenanceScheduleListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::MaintenanceSchedule>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::MaintenanceSchedule>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::MaintenanceSchedule>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::MaintenanceSchedule> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::MaintenanceScheduleExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::MaintenanceScheduleExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::MaintenanceScheduleExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::MaintenanceScheduleExpression::new(next, self.root_desc.clone())
    }
}