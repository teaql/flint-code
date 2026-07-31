// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/audit_log
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "AuditLog", table = "audit_log_data", data_service = "sqlite")]
pub struct AuditLog {
#[teaql(id)]
    id: u64,

// @source admin.xml:97
    action: String,

// @source admin.xml:97
    entity_type: String,

// @source admin.xml:97
    entity_id: i64,

// @source admin.xml:97
    old_value: String,

// @source admin.xml:97
    new_value: String,

// @source admin.xml:97
    timestamp: String,

// @source admin.xml:97
    ip_address: String,

// @source admin.xml:97
    create_time: chrono::DateTime<chrono::Utc>,

// @source admin.xml:97
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source admin.xml:97
#[teaql(column = "\"user\"")]
    user_id: u64,
// @source admin.xml:97
#[teaql(relation(target = "User", local_key = "user_id", foreign_key = "id"))]
    user: Option<crate::User>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl AuditLog {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            action: String::new(),
            entity_type: String::new(),
            entity_id: 0_i64,
            old_value: String::new(),
            new_value: String::new(),
            timestamp: String::new(),
            ip_address: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            user_id: 0_u64,
            user: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("AuditLog", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.user {
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

    pub fn action(&self) -> String {
        self.changed_action().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.action.clone())
    }

    pub fn update_action(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.action = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.action.clone());
        self.root.set(self.entity_key(), "action", value);
        self
    }

    pub fn changed_action(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "action")
    }

    pub fn eval_action(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("action") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "action".to_string(), attempted_path: "action".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.action())
                }}

    pub fn entity_type(&self) -> String {
        self.changed_entity_type().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.entity_type.clone())
    }

    pub fn update_entity_type(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.entity_type = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.entity_type.clone());
        self.root.set(self.entity_key(), "entity_type", value);
        self
    }

    pub fn changed_entity_type(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "entity_type")
    }

    pub fn eval_entity_type(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("entity_type") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "entity_type".to_string(), attempted_path: "entity_type".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.entity_type())
                }}

    pub fn entity_id(&self) -> i64 {
        self.changed_entity_id().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.entity_id)
    }

    pub fn update_entity_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.entity_id = value.try_i64().map(|value| value as i64).unwrap_or(self.entity_id.clone());
        self.root.set(self.entity_key(), "entity_id", value);
        self
    }

    pub fn changed_entity_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "entity_id")
    }

    pub fn eval_entity_id(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("entity_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "entity_id".to_string(), attempted_path: "entity_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.entity_id())
                }}

    pub fn old_value(&self) -> String {
        self.changed_old_value().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.old_value.clone())
    }

    pub fn update_old_value(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.old_value = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.old_value.clone());
        self.root.set(self.entity_key(), "old_value", value);
        self
    }

    pub fn changed_old_value(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "old_value")
    }

    pub fn eval_old_value(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("old_value") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "old_value".to_string(), attempted_path: "old_value".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.old_value())
                }}

    pub fn new_value(&self) -> String {
        self.changed_new_value().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.new_value.clone())
    }

    pub fn update_new_value(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.new_value = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.new_value.clone());
        self.root.set(self.entity_key(), "new_value", value);
        self
    }

    pub fn changed_new_value(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "new_value")
    }

    pub fn eval_new_value(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("new_value") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "new_value".to_string(), attempted_path: "new_value".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.new_value())
                }}

    pub fn timestamp(&self) -> String {
        self.changed_timestamp().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.timestamp.clone())
    }

    pub fn update_timestamp(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.timestamp = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.timestamp.clone());
        self.root.set(self.entity_key(), "timestamp", value);
        self
    }

    pub fn changed_timestamp(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "timestamp")
    }

    pub fn eval_timestamp(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("timestamp") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "timestamp".to_string(), attempted_path: "timestamp".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.timestamp())
                }}

    pub fn ip_address(&self) -> String {
        self.changed_ip_address().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.ip_address.clone())
    }

    pub fn update_ip_address(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.ip_address = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.ip_address.clone());
        self.root.set(self.entity_key(), "ip_address", value);
        self
    }

    pub fn changed_ip_address(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "ip_address")
    }

    pub fn eval_ip_address(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("ip_address") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "ip_address".to_string(), attempted_path: "ip_address".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.ip_address())
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
    pub fn user_id(&self) -> u64 {
        self.changed_user_id().and_then(|value| value.try_u64()).unwrap_or(self.user_id)
    }

    pub fn update_user_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.user_id = value.try_u64().unwrap_or(self.user_id.clone());
        self.root.set(self.entity_key(), "user_id", value);
        self
    }

    pub fn changed_user_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "user_id")
    }

    pub fn eval_user_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("user_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "user_id".to_string(), attempted_path: "user_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.user_id())
                }}
    pub fn user(&self) -> Option<&crate::User> {
        self.user.as_ref()
    }

    pub fn eval_user(&self) -> teaql_core::eval::EvalResult<&crate::User> {
        if !self.is_loaded("user") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "user".to_string(), attempted_path: "user".to_string() }
        } else {
            match &self.user {
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

