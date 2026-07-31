#[derive(Clone)]
pub struct ServiceExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::Service>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> ServiceExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::Service>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::Service> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::Service> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::Service {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_name(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("name", |entity| entity.eval_name());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_description(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("description", |entity| entity.eval_description());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_base_price(self) -> crate::ValueExpression<'a, rust_decimal::Decimal> {
        let next = self.result.and_then("base_price", |entity| entity.eval_base_price());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_price_per_hour(self) -> crate::ValueExpression<'a, rust_decimal::Decimal> {
        let next = self.result.and_then("price_per_hour", |entity| entity.eval_price_per_hour());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_is_active(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("is_active", |entity| entity.eval_is_active());
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
    pub fn get_service_category_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("service_category_id", |entity| entity.eval_service_category_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_service_category(self) -> crate::ServiceCategoryExpression<'a> {
        let next = self.result.and_then("service_category", |entity| entity.eval_service_category());
        crate::ServiceCategoryExpression::new(next, self.root_desc.clone())
    }
    pub fn service_category_is_moving(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("service_category_id", |entity| {
            if !entity.is_loaded("service_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_category_id".to_string(), attempted_path: "service_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.service_category_is_moving())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn service_category_is_cleaning(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("service_category_id", |entity| {
            if !entity.is_loaded("service_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_category_id".to_string(), attempted_path: "service_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.service_category_is_cleaning())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn service_category_is_box_rental(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("service_category_id", |entity| {
            if !entity.is_loaded("service_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_category_id".to_string(), attempted_path: "service_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.service_category_is_box_rental())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn service_category_is_additional(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("service_category_id", |entity| {
            if !entity.is_loaded("service_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_category_id".to_string(), attempted_path: "service_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.service_category_is_additional())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_service_configuration_list(self) -> crate::ServiceConfigurationListExpression<'a> {
        let next = self.result.and_then("service_configuration_list", |entity| entity.eval_service_configuration_list());
        crate::ServiceConfigurationListExpression::new(next, self.root_desc.clone())
    }

    pub fn get_pricing_rule_list(self) -> crate::PricingRuleListExpression<'a> {
        let next = self.result.and_then("pricing_rule_list", |entity| entity.eval_pricing_rule_list());
        crate::PricingRuleListExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct ServiceListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Service>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> ServiceListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Service>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::Service>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::Service>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::Service> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::ServiceExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::ServiceExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::ServiceExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::ServiceExpression::new(next, self.root_desc.clone())
    }
}