#[derive(Clone)]
pub struct AuditLogExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::AuditLog>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> AuditLogExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::AuditLog>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::AuditLog> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::AuditLog> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::AuditLog {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_action(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("action", |entity| entity.eval_action());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_entity_type(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("entity_type", |entity| entity.eval_entity_type());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_entity_id(self) -> crate::ValueExpression<'a, i64> {
        let next = self.result.and_then("entity_id", |entity| entity.eval_entity_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_old_value(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("old_value", |entity| entity.eval_old_value());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_new_value(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("new_value", |entity| entity.eval_new_value());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_timestamp(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("timestamp", |entity| entity.eval_timestamp());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_ip_address(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("ip_address", |entity| entity.eval_ip_address());
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
    pub fn get_user_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("user_id", |entity| entity.eval_user_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_user(self) -> crate::UserExpression<'a> {
        let next = self.result.and_then("user", |entity| entity.eval_user());
        crate::UserExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct AuditLogListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::AuditLog>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> AuditLogListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::AuditLog>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::AuditLog>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::AuditLog>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::AuditLog> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::AuditLogExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::AuditLogExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::AuditLogExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::AuditLogExpression::new(next, self.root_desc.clone())
    }
}