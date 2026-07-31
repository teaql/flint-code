#[derive(Clone)]
pub struct PricingRuleExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::PricingRule>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> PricingRuleExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::PricingRule>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::PricingRule> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::PricingRule> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::PricingRule {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_rule_name(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("rule_name", |entity| entity.eval_rule_name());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_rule_type(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("rule_type", |entity| entity.eval_rule_type());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_value(self) -> crate::ValueExpression<'a, rust_decimal::Decimal> {
        let next = self.result.and_then("value", |entity| entity.eval_value());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_start_date(self) -> crate::ValueExpression<'a, chrono::NaiveDate> {
        let next = self.result.and_then("start_date", |entity| entity.eval_start_date());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_end_date(self) -> crate::ValueExpression<'a, chrono::NaiveDate> {
        let next = self.result.and_then("end_date", |entity| entity.eval_end_date());
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
pub struct PricingRuleListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::PricingRule>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> PricingRuleListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::PricingRule>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::PricingRule>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::PricingRule>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::PricingRule> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::PricingRuleExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::PricingRuleExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::PricingRuleExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::PricingRuleExpression::new(next, self.root_desc.clone())
    }
}