// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/service_configuration
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "ServiceConfiguration", table = "service_configuration_data", data_service = "sqlite")]
pub struct ServiceConfiguration {
#[teaql(id)]
    id: u64,

// @source products.xml:48
    config_key: String,

// @source products.xml:48
    config_value: i64,

// @source products.xml:48
    description: String,

// @source products.xml:48
    create_time: chrono::DateTime<chrono::Utc>,

// @source products.xml:48
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source products.xml:48
#[teaql(column = "service")]
    service_id: u64,
// @source products.xml:48
#[teaql(relation(target = "Service", local_key = "service_id", foreign_key = "id"))]
    service: Option<crate::Service>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl ServiceConfiguration {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            config_key: String::new(),
            config_value: 0_i64,
            description: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            service_id: 0_u64,
            service: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("ServiceConfiguration", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.service {
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

    pub fn config_key(&self) -> String {
        self.changed_config_key().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.config_key.clone())
    }

    pub fn update_config_key(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.config_key = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.config_key.clone());
        self.root.set(self.entity_key(), "config_key", value);
        self
    }

    pub fn changed_config_key(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "config_key")
    }

    pub fn eval_config_key(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("config_key") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "config_key".to_string(), attempted_path: "config_key".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.config_key())
                }}

    pub fn config_value(&self) -> i64 {
        self.changed_config_value().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.config_value)
    }

    pub fn update_config_value(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.config_value = value.try_i64().map(|value| value as i64).unwrap_or(self.config_value.clone());
        self.root.set(self.entity_key(), "config_value", value);
        self
    }

    pub fn changed_config_value(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "config_value")
    }

    pub fn eval_config_value(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("config_value") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "config_value".to_string(), attempted_path: "config_value".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.config_value())
                }}

    pub fn description(&self) -> String {
        self.changed_description().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.description.clone())
    }

    pub fn update_description(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.description = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.description.clone());
        self.root.set(self.entity_key(), "description", value);
        self
    }

    pub fn changed_description(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "description")
    }

    pub fn eval_description(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("description") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "description".to_string(), attempted_path: "description".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.description())
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
    pub fn service_id(&self) -> u64 {
        self.changed_service_id().and_then(|value| value.try_u64()).unwrap_or(self.service_id)
    }

    pub fn update_service_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.service_id = value.try_u64().unwrap_or(self.service_id.clone());
        self.root.set(self.entity_key(), "service_id", value);
        self
    }

    pub fn changed_service_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "service_id")
    }

    pub fn eval_service_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("service_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_id".to_string(), attempted_path: "service_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.service_id())
                }}
    pub fn service(&self) -> Option<&crate::Service> {
        self.service.as_ref()
    }

    pub fn eval_service(&self) -> teaql_core::eval::EvalResult<&crate::Service> {
        if !self.is_loaded("service") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "service".to_string(), attempted_path: "service".to_string() }
        } else {
            match &self.service {
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

