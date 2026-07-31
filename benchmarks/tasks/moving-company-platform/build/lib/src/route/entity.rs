// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/route
use std::collections::BTreeMap;

use teaql_core::SmartList;
use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "Route", table = "route_data", data_service = "sqlite")]
pub struct Route {
#[teaql(id)]
    id: u64,

// @source operations.xml:28
    name: String,

// @source operations.xml:28
    estimated_duration: i64,

// @source operations.xml:28
    distance_miles: rust_decimal::Decimal,

// @source operations.xml:28
    create_time: chrono::DateTime<chrono::Utc>,

// @source operations.xml:28
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source operations.xml:28
#[teaql(column = "origin")]
    origin_id: u64,

// @source operations.xml:28
#[teaql(column = "destination")]
    destination_id: u64,
// @source operations.xml:28
#[teaql(relation(target = "Address", local_key = "origin_id", foreign_key = "id"))]
    origin: Option<crate::Address>,

// @source operations.xml:28
#[teaql(relation(target = "Address", local_key = "destination_id", foreign_key = "id"))]
    destination: Option<crate::Address>,
#[teaql(relation(target = "MovingJob", local_key = "id", foreign_key = "route_id", many))]
    moving_job_list: SmartList<crate::MovingJob>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Route {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            name: String::new(),
            estimated_duration: 0_i64,
            distance_miles: rust_decimal::Decimal::ZERO,
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            origin_id: 0_u64,
            destination_id: 0_u64,
            origin: None,
            destination: None,
            moving_job_list: Default::default(),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Route", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.origin {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.destination {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.moving_job_list {
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

    pub fn name(&self) -> String {
        self.changed_name().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.name.clone())
    }

    pub fn update_name(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.name = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.name.clone());
        self.root.set(self.entity_key(), "name", value);
        self
    }

    pub fn changed_name(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "name")
    }

    pub fn eval_name(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("name") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "name".to_string(), attempted_path: "name".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.name())
                }}

    pub fn estimated_duration(&self) -> i64 {
        self.changed_estimated_duration().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.estimated_duration)
    }

    pub fn update_estimated_duration(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.estimated_duration = value.try_i64().map(|value| value as i64).unwrap_or(self.estimated_duration.clone());
        self.root.set(self.entity_key(), "estimated_duration", value);
        self
    }

    pub fn changed_estimated_duration(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "estimated_duration")
    }

    pub fn eval_estimated_duration(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("estimated_duration") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "estimated_duration".to_string(), attempted_path: "estimated_duration".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.estimated_duration())
                }}

    pub fn distance_miles(&self) -> rust_decimal::Decimal {
        self.changed_distance_miles().and_then(|value| value.try_decimal()).unwrap_or(self.distance_miles)
    }

    pub fn update_distance_miles(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.distance_miles = value.try_decimal().unwrap_or(self.distance_miles.clone());
        self.root.set(self.entity_key(), "distance_miles", value);
        self
    }

    pub fn changed_distance_miles(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "distance_miles")
    }

    pub fn eval_distance_miles(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("distance_miles") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "distance_miles".to_string(), attempted_path: "distance_miles".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.distance_miles())
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
    pub fn origin_id(&self) -> u64 {
        self.changed_origin_id().and_then(|value| value.try_u64()).unwrap_or(self.origin_id)
    }

    pub fn update_origin_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.origin_id = value.try_u64().unwrap_or(self.origin_id.clone());
        self.root.set(self.entity_key(), "origin_id", value);
        self
    }

    pub fn changed_origin_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "origin_id")
    }

    pub fn eval_origin_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("origin_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "origin_id".to_string(), attempted_path: "origin_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.origin_id())
                }}

    pub fn destination_id(&self) -> u64 {
        self.changed_destination_id().and_then(|value| value.try_u64()).unwrap_or(self.destination_id)
    }

    pub fn update_destination_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.destination_id = value.try_u64().unwrap_or(self.destination_id.clone());
        self.root.set(self.entity_key(), "destination_id", value);
        self
    }

    pub fn changed_destination_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "destination_id")
    }

    pub fn eval_destination_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("destination_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "destination_id".to_string(), attempted_path: "destination_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.destination_id())
                }}
    pub fn origin(&self) -> Option<&crate::Address> {
        self.origin.as_ref()
    }

    pub fn eval_origin(&self) -> teaql_core::eval::EvalResult<&crate::Address> {
        if !self.is_loaded("origin") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "origin".to_string(), attempted_path: "origin".to_string() }
        } else {
            match &self.origin {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn destination(&self) -> Option<&crate::Address> {
        self.destination.as_ref()
    }

    pub fn eval_destination(&self) -> teaql_core::eval::EvalResult<&crate::Address> {
        if !self.is_loaded("destination") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "destination".to_string(), attempted_path: "destination".to_string() }
        } else {
            match &self.destination {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }
    pub fn moving_job_list(&self) -> &SmartList<crate::MovingJob> {
        &self.moving_job_list
    }

    pub fn moving_job_list_mut(&mut self) -> &mut SmartList<crate::MovingJob> {
        &mut self.moving_job_list
    }

    pub fn eval_moving_job_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::MovingJob>> {
        if !self.is_loaded("moving_job_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "moving_job_list".to_string(), attempted_path: "moving_job_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.moving_job_list)
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

