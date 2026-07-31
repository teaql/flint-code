// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/fulfillment_event
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "FulfillmentEvent", table = "fulfillment_event_data", data_service = "sqlite")]
pub struct FulfillmentEvent {
#[teaql(id)]
    id: u64,

// @source operations.xml:81
    event_type: String,

// @source operations.xml:81
    timestamp: String,

// @source operations.xml:81
    notes: String,

// @source operations.xml:81
    create_time: chrono::DateTime<chrono::Utc>,

// @source operations.xml:81
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source operations.xml:81
#[teaql(column = "moving_job")]
    moving_job_id: u64,

// @source operations.xml:81
#[teaql(column = "location")]
    location_id: u64,
// @source operations.xml:81
#[teaql(relation(target = "MovingJob", local_key = "moving_job_id", foreign_key = "id"))]
    moving_job: Option<crate::MovingJob>,

// @source operations.xml:81
#[teaql(relation(target = "Address", local_key = "location_id", foreign_key = "id"))]
    location: Option<crate::Address>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl FulfillmentEvent {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            event_type: String::new(),
            timestamp: String::new(),
            notes: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            moving_job_id: 0_u64,
            location_id: 0_u64,
            moving_job: None,
            location: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("FulfillmentEvent", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.moving_job {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.location {
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

    pub fn event_type(&self) -> String {
        self.changed_event_type().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.event_type.clone())
    }

    pub fn update_event_type(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.event_type = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.event_type.clone());
        self.root.set(self.entity_key(), "event_type", value);
        self
    }

    pub fn changed_event_type(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "event_type")
    }

    pub fn eval_event_type(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("event_type") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "event_type".to_string(), attempted_path: "event_type".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.event_type())
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

    pub fn notes(&self) -> String {
        self.changed_notes().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.notes.clone())
    }

    pub fn update_notes(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.notes = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.notes.clone());
        self.root.set(self.entity_key(), "notes", value);
        self
    }

    pub fn changed_notes(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "notes")
    }

    pub fn eval_notes(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("notes") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "notes".to_string(), attempted_path: "notes".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.notes())
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
    pub fn moving_job_id(&self) -> u64 {
        self.changed_moving_job_id().and_then(|value| value.try_u64()).unwrap_or(self.moving_job_id)
    }

    pub fn update_moving_job_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.moving_job_id = value.try_u64().unwrap_or(self.moving_job_id.clone());
        self.root.set(self.entity_key(), "moving_job_id", value);
        self
    }

    pub fn changed_moving_job_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "moving_job_id")
    }

    pub fn eval_moving_job_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("moving_job_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "moving_job_id".to_string(), attempted_path: "moving_job_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.moving_job_id())
                }}

    pub fn location_id(&self) -> u64 {
        self.changed_location_id().and_then(|value| value.try_u64()).unwrap_or(self.location_id)
    }

    pub fn update_location_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.location_id = value.try_u64().unwrap_or(self.location_id.clone());
        self.root.set(self.entity_key(), "location_id", value);
        self
    }

    pub fn changed_location_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "location_id")
    }

    pub fn eval_location_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("location_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "location_id".to_string(), attempted_path: "location_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.location_id())
                }}
    pub fn moving_job(&self) -> Option<&crate::MovingJob> {
        self.moving_job.as_ref()
    }

    pub fn eval_moving_job(&self) -> teaql_core::eval::EvalResult<&crate::MovingJob> {
        if !self.is_loaded("moving_job") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "moving_job".to_string(), attempted_path: "moving_job".to_string() }
        } else {
            match &self.moving_job {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn location(&self) -> Option<&crate::Address> {
        self.location.as_ref()
    }

    pub fn eval_location(&self) -> teaql_core::eval::EvalResult<&crate::Address> {
        if !self.is_loaded("location") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "location".to_string(), attempted_path: "location".to_string() }
        } else {
            match &self.location {
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

