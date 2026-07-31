#[derive(Clone)]
pub struct RolePermissionExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::RolePermission>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> RolePermissionExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::RolePermission>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::RolePermission> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::RolePermission> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::RolePermission {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_resource(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("resource", |entity| entity.eval_resource());
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
    pub fn get_role_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("role_id", |entity| entity.eval_role_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_permission_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("permission_id", |entity| entity.eval_permission_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_role(self) -> crate::RoleExpression<'a> {
        let next = self.result.and_then("role", |entity| entity.eval_role());
        crate::RoleExpression::new(next, self.root_desc.clone())
    }

    pub fn get_permission(self) -> crate::PermissionExpression<'a> {
        let next = self.result.and_then("permission", |entity| entity.eval_permission());
        crate::PermissionExpression::new(next, self.root_desc.clone())
    }
    pub fn role_is_admin(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("role_id", |entity| {
            if !entity.is_loaded("role_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "role_id".to_string(), attempted_path: "role_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.role_is_admin())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn role_is_manager(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("role_id", |entity| {
            if !entity.is_loaded("role_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "role_id".to_string(), attempted_path: "role_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.role_is_manager())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn role_is_employee(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("role_id", |entity| {
            if !entity.is_loaded("role_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "role_id".to_string(), attempted_path: "role_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.role_is_employee())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn role_is_customer(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("role_id", |entity| {
            if !entity.is_loaded("role_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "role_id".to_string(), attempted_path: "role_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.role_is_customer())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn permission_is_read(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("permission_id", |entity| {
            if !entity.is_loaded("permission_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "permission_id".to_string(), attempted_path: "permission_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.permission_is_read())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn permission_is_write(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("permission_id", |entity| {
            if !entity.is_loaded("permission_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "permission_id".to_string(), attempted_path: "permission_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.permission_is_write())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn permission_is_delete(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("permission_id", |entity| {
            if !entity.is_loaded("permission_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "permission_id".to_string(), attempted_path: "permission_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.permission_is_delete())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn permission_is_admin(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("permission_id", |entity| {
            if !entity.is_loaded("permission_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "permission_id".to_string(), attempted_path: "permission_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.permission_is_admin())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct RolePermissionListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::RolePermission>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> RolePermissionListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::RolePermission>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::RolePermission>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::RolePermission>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::RolePermission> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::RolePermissionExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::RolePermissionExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::RolePermissionExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::RolePermissionExpression::new(next, self.root_desc.clone())
    }
}