// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/role_permission
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "RolePermission", table = "role_permission_data", data_service = "sqlite")]
pub struct RolePermission {
#[teaql(id)]
    id: u64,

// @source platform.xml:70
    resource: String,

// @source platform.xml:70
    create_time: chrono::DateTime<chrono::Utc>,

// @source platform.xml:70
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source platform.xml:70
#[teaql(column = "role")]
    role_id: u64,

// @source platform.xml:70
#[teaql(column = "permission")]
    permission_id: u64,
// @source platform.xml:70
#[teaql(relation(target = "Role", local_key = "role_id", foreign_key = "id"))]
    role: Option<crate::Role>,

// @source platform.xml:70
#[teaql(relation(target = "Permission", local_key = "permission_id", foreign_key = "id"))]
    permission: Option<crate::Permission>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl RolePermission {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            resource: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            role_id: 0_u64,
            permission_id: 0_u64,
            role: None,
            permission: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("RolePermission", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.role {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.permission {
            entity.attach_root_recursive(root.clone());
        }
    }

    pub fn is_loaded(&self, field_or_relation: &str) -> bool {
        self.__load_state.is_loaded(field_or_relation)
    }

    pub fn set_load_state(&mut self, state: teaql_core::eval::LoadState) {
        self.__load_state = state;
    }

    pub fn id(&self) -> u64 {
        self.changed_id().and_then(|value| value.try_u64()).unwrap_or(self.id)
    }

    pub fn update_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.id = value.try_u64().unwrap_or(self.id.clone());
        self.root.set(self.entity_key(), "id", value);
        self
    }

    pub fn changed_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "id")
    }

    pub fn eval_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "id".to_string(), attempted_path: "id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.id())
                }}

    pub fn resource(&self) -> String {
        self.changed_resource().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.resource.clone())
    }

    pub fn update_resource(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.resource = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.resource.clone());
        self.root.set(self.entity_key(), "resource", value);
        self
    }

    pub fn changed_resource(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "resource")
    }

    pub fn eval_resource(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("resource") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "resource".to_string(), attempted_path: "resource".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.resource())
                }}

    pub fn create_time(&self) -> chrono::DateTime<chrono::Utc> {
        self.changed_create_time().and_then(|value| value.try_timestamp()).unwrap_or(self.create_time)
    }

    pub fn update_create_time(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.create_time = value.try_timestamp().unwrap_or(self.create_time.clone());
        self.root.set(self.entity_key(), "create_time", value);
        self
    }

    pub fn changed_create_time(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "create_time")
    }

    pub fn eval_create_time(&self) -> teaql_core::eval::EvalResult<chrono::DateTime<chrono::Utc>> {
        if !self.is_loaded("create_time") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "create_time".to_string(), attempted_path: "create_time".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.create_time())
                }}

    pub fn update_time(&self) -> chrono::DateTime<chrono::Utc> {
        self.changed_update_time().and_then(|value| value.try_timestamp()).unwrap_or(self.update_time)
    }

    pub fn update_update_time(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.update_time = value.try_timestamp().unwrap_or(self.update_time.clone());
        self.root.set(self.entity_key(), "update_time", value);
        self
    }

    pub fn changed_update_time(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "update_time")
    }

    pub fn eval_update_time(&self) -> teaql_core::eval::EvalResult<chrono::DateTime<chrono::Utc>> {
        if !self.is_loaded("update_time") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "update_time".to_string(), attempted_path: "update_time".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.update_time())
                }}

    pub fn version(&self) -> i64 {
        self.changed_version().and_then(|value| value.try_i64()).unwrap_or(self.version)
    }

    pub fn update_version(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.version = value.try_i64().unwrap_or(self.version.clone());
        self.root.set(self.entity_key(), "version", value);
        self
    }

    pub fn changed_version(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "version")
    }

    pub fn eval_version(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("version") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "version".to_string(), attempted_path: "version".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.version())
                }}
    pub fn role_id(&self) -> u64 {
        self.changed_role_id().and_then(|value| value.try_u64()).unwrap_or(self.role_id)
    }

    pub(crate) fn update_role_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.role_id = value.try_u64().unwrap_or(self.role_id.clone());
        self.root.set(self.entity_key(), "role_id", value);
        self
    }

    pub fn changed_role_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "role_id")
    }

    pub fn eval_role_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("role_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "role_id".to_string(), attempted_path: "role_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.role_id())
                }}

    pub fn permission_id(&self) -> u64 {
        self.changed_permission_id().and_then(|value| value.try_u64()).unwrap_or(self.permission_id)
    }

    pub(crate) fn update_permission_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.permission_id = value.try_u64().unwrap_or(self.permission_id.clone());
        self.root.set(self.entity_key(), "permission_id", value);
        self
    }

    pub fn changed_permission_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "permission_id")
    }

    pub fn eval_permission_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("permission_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "permission_id".to_string(), attempted_path: "permission_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.permission_id())
                }}
    pub fn update_role_to_admin(&mut self) -> &mut Self {
        self.update_role_id(1001_u64)
    }

    pub fn role_is_admin(&self) -> bool {
        self.role_id() == 1001_u64
    }
    pub fn update_role_to_manager(&mut self) -> &mut Self {
        self.update_role_id(1002_u64)
    }

    pub fn role_is_manager(&self) -> bool {
        self.role_id() == 1002_u64
    }
    pub fn update_role_to_employee(&mut self) -> &mut Self {
        self.update_role_id(1003_u64)
    }

    pub fn role_is_employee(&self) -> bool {
        self.role_id() == 1003_u64
    }
    pub fn update_role_to_customer(&mut self) -> &mut Self {
        self.update_role_id(1004_u64)
    }

    pub fn role_is_customer(&self) -> bool {
        self.role_id() == 1004_u64
    }

    pub fn update_permission_to_read(&mut self) -> &mut Self {
        self.update_permission_id(1001_u64)
    }

    pub fn permission_is_read(&self) -> bool {
        self.permission_id() == 1001_u64
    }
    pub fn update_permission_to_write(&mut self) -> &mut Self {
        self.update_permission_id(1002_u64)
    }

    pub fn permission_is_write(&self) -> bool {
        self.permission_id() == 1002_u64
    }
    pub fn update_permission_to_delete(&mut self) -> &mut Self {
        self.update_permission_id(1003_u64)
    }

    pub fn permission_is_delete(&self) -> bool {
        self.permission_id() == 1003_u64
    }
    pub fn update_permission_to_admin(&mut self) -> &mut Self {
        self.update_permission_id(1004_u64)
    }

    pub fn permission_is_admin(&self) -> bool {
        self.permission_id() == 1004_u64
    }
    pub fn role(&self) -> Option<&crate::Role> {
        self.role.as_ref()
    }

    pub fn eval_role(&self) -> teaql_core::eval::EvalResult<&crate::Role> {
        if !self.is_loaded("role") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "role".to_string(), attempted_path: "role".to_string() }
        } else {
            match &self.role {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn permission(&self) -> Option<&crate::Permission> {
        self.permission.as_ref()
    }

    pub fn eval_permission(&self) -> teaql_core::eval::EvalResult<&crate::Permission> {
        if !self.is_loaded("permission") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "permission".to_string(), attempted_path: "permission".to_string() }
        } else {
            match &self.permission {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn mark_as_delete(&mut self) -> &mut Self {
        self.root.mark_as_delete(self.entity_key());
        self
    }

    pub fn set_comment(&mut self, comment: impl Into<String>) -> &mut Self {
        self.root.set_comment(comment);
        self
    }
}

