// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/vehicle
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
#[teaql(entity = "Vehicle", table = "vehicle_data", data_service = "sqlite")]
pub struct Vehicle {
#[teaql(id)]
    id: u64,

// @source assets.xml:38
    make: String,

// @source assets.xml:38
    model: String,

// @source assets.xml:38
    year: i64,

// @source assets.xml:38
    license_plate: String,

// @source assets.xml:38
    vin: String,

// @source assets.xml:38
    mileage: i64,

// @source assets.xml:38
    status: String,

// @source assets.xml:38
    create_time: chrono::DateTime<chrono::Utc>,

// @source assets.xml:38
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source assets.xml:38
#[teaql(column = "vehicle_type")]
    vehicle_type_id: u64,
// @source assets.xml:38
#[teaql(relation(target = "VehicleType", local_key = "vehicle_type_id", foreign_key = "id"))]
    vehicle_type: Option<crate::VehicleType>,
#[teaql(relation(target = "Expense", local_key = "id", foreign_key = "vehicle_id", many))]
    expense_list: SmartList<crate::Expense>,
#[teaql(relation(target = "MaintenanceSchedule", local_key = "id", foreign_key = "vehicle_id", many))]
    maintenance_schedule_list: SmartList<crate::MaintenanceSchedule>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Vehicle {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            make: String::new(),
            model: String::new(),
            year: 0_i64,
            license_plate: String::new(),
            vin: String::new(),
            mileage: 0_i64,
            status: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            vehicle_type_id: 0_u64,
            vehicle_type: None,
            expense_list: Default::default(),
            maintenance_schedule_list: Default::default(),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Vehicle", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.vehicle_type {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.expense_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.maintenance_schedule_list {
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

    pub fn make(&self) -> String {
        self.changed_make().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.make.clone())
    }

    pub fn update_make(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.make = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.make.clone());
        self.root.set(self.entity_key(), "make", value);
        self
    }

    pub fn changed_make(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "make")
    }

    pub fn eval_make(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("make") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "make".to_string(), attempted_path: "make".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.make())
                }}

    pub fn model(&self) -> String {
        self.changed_model().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.model.clone())
    }

    pub fn update_model(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.model = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.model.clone());
        self.root.set(self.entity_key(), "model", value);
        self
    }

    pub fn changed_model(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "model")
    }

    pub fn eval_model(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("model") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "model".to_string(), attempted_path: "model".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.model())
                }}

    pub fn year(&self) -> i64 {
        self.changed_year().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.year)
    }

    pub fn update_year(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.year = value.try_i64().map(|value| value as i64).unwrap_or(self.year.clone());
        self.root.set(self.entity_key(), "year", value);
        self
    }

    pub fn changed_year(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "year")
    }

    pub fn eval_year(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("year") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "year".to_string(), attempted_path: "year".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.year())
                }}

    pub fn license_plate(&self) -> String {
        self.changed_license_plate().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.license_plate.clone())
    }

    pub fn update_license_plate(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.license_plate = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.license_plate.clone());
        self.root.set(self.entity_key(), "license_plate", value);
        self
    }

    pub fn changed_license_plate(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "license_plate")
    }

    pub fn eval_license_plate(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("license_plate") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "license_plate".to_string(), attempted_path: "license_plate".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.license_plate())
                }}

    pub fn vin(&self) -> String {
        self.changed_vin().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.vin.clone())
    }

    pub fn update_vin(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.vin = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.vin.clone());
        self.root.set(self.entity_key(), "vin", value);
        self
    }

    pub fn changed_vin(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "vin")
    }

    pub fn eval_vin(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("vin") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "vin".to_string(), attempted_path: "vin".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.vin())
                }}

    pub fn mileage(&self) -> i64 {
        self.changed_mileage().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.mileage)
    }

    pub fn update_mileage(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.mileage = value.try_i64().map(|value| value as i64).unwrap_or(self.mileage.clone());
        self.root.set(self.entity_key(), "mileage", value);
        self
    }

    pub fn changed_mileage(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "mileage")
    }

    pub fn eval_mileage(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("mileage") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "mileage".to_string(), attempted_path: "mileage".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.mileage())
                }}

    pub fn status(&self) -> String {
        self.changed_status().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.status.clone())
    }

    pub fn update_status(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.status = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.status.clone());
        self.root.set(self.entity_key(), "status", value);
        self
    }

    pub fn changed_status(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "status")
    }

    pub fn eval_status(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("status") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "status".to_string(), attempted_path: "status".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.status())
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
    pub fn vehicle_type_id(&self) -> u64 {
        self.changed_vehicle_type_id().and_then(|value| value.try_u64()).unwrap_or(self.vehicle_type_id)
    }

    pub(crate) fn update_vehicle_type_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.vehicle_type_id = value.try_u64().unwrap_or(self.vehicle_type_id.clone());
        self.root.set(self.entity_key(), "vehicle_type_id", value);
        self
    }

    pub fn changed_vehicle_type_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "vehicle_type_id")
    }

    pub fn eval_vehicle_type_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("vehicle_type_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "vehicle_type_id".to_string(), attempted_path: "vehicle_type_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.vehicle_type_id())
                }}
    pub fn update_vehicle_type_to_box_truck(&mut self) -> &mut Self {
        self.update_vehicle_type_id(1001_u64)
    }

    pub fn vehicle_type_is_box_truck(&self) -> bool {
        self.vehicle_type_id() == 1001_u64
    }
    pub fn update_vehicle_type_to_flatbed(&mut self) -> &mut Self {
        self.update_vehicle_type_id(1002_u64)
    }

    pub fn vehicle_type_is_flatbed(&self) -> bool {
        self.vehicle_type_id() == 1002_u64
    }
    pub fn update_vehicle_type_to_van(&mut self) -> &mut Self {
        self.update_vehicle_type_id(1003_u64)
    }

    pub fn vehicle_type_is_van(&self) -> bool {
        self.vehicle_type_id() == 1003_u64
    }
    pub fn vehicle_type(&self) -> Option<&crate::VehicleType> {
        self.vehicle_type.as_ref()
    }

    pub fn eval_vehicle_type(&self) -> teaql_core::eval::EvalResult<&crate::VehicleType> {
        if !self.is_loaded("vehicle_type") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "vehicle_type".to_string(), attempted_path: "vehicle_type".to_string() }
        } else {
            match &self.vehicle_type {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }
    pub fn expense_list(&self) -> &SmartList<crate::Expense> {
        &self.expense_list
    }

    pub fn expense_list_mut(&mut self) -> &mut SmartList<crate::Expense> {
        &mut self.expense_list
    }

    pub fn eval_expense_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::Expense>> {
        if !self.is_loaded("expense_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_list".to_string(), attempted_path: "expense_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.expense_list)
        }
    }

    pub fn maintenance_schedule_list(&self) -> &SmartList<crate::MaintenanceSchedule> {
        &self.maintenance_schedule_list
    }

    pub fn maintenance_schedule_list_mut(&mut self) -> &mut SmartList<crate::MaintenanceSchedule> {
        &mut self.maintenance_schedule_list
    }

    pub fn eval_maintenance_schedule_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::MaintenanceSchedule>> {
        if !self.is_loaded("maintenance_schedule_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "maintenance_schedule_list".to_string(), attempted_path: "maintenance_schedule_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.maintenance_schedule_list)
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

