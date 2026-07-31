#[derive(Clone)]
pub struct ServiceConfigurationExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::ServiceConfiguration>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> ServiceConfigurationExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::ServiceConfiguration>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::ServiceConfiguration> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::ServiceConfiguration> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::ServiceConfiguration {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_config_key(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("config_key", |entity| entity.eval_config_key());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_config_value(self) -> crate::ValueExpression<'a, i64> {
        let next = self.result.and_then("config_value", |entity| entity.eval_config_value());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_description(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("description", |entity| entity.eval_description());
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
    pub fn get_service_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("service_id", |entity| entity.eval_service_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_service(self) -> crate::ServiceExpression<'a> {
        let next = self.result.and_then("service", |entity| entity.eval_service());
        crate::ServiceExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct ServiceConfigurationListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::ServiceConfiguration>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> ServiceConfigurationListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::ServiceConfiguration>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::ServiceConfiguration>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::ServiceConfiguration>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::ServiceConfiguration> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::ServiceConfigurationExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::ServiceConfigurationExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::ServiceConfigurationExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::ServiceConfigurationExpression::new(next, self.root_desc.clone())
    }
}