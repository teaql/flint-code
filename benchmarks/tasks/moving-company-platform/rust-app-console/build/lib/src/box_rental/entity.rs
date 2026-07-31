// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/box_rental
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "BoxRental", table = "box_rental_data", data_service = "sqlite")]
pub struct BoxRental {
#[teaql(id)]
    id: u64,

// @source products.xml:83
    quantity: i64,

// @source products.xml:83
    rental_start: chrono::NaiveDate,

// @source products.xml:83
    rental_end: chrono::NaiveDate,

// @source products.xml:83
    total_cost: rust_decimal::Decimal,

// @source products.xml:83
    create_time: chrono::DateTime<chrono::Utc>,

// @source products.xml:83
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source products.xml:83
#[teaql(column = "customer")]
    customer_id: u64,

// @source products.xml:83
#[teaql(column = "box_type")]
    box_type_id: u64,
// @source products.xml:83
#[teaql(relation(target = "Customer", local_key = "customer_id", foreign_key = "id"))]
    customer: Option<crate::Customer>,

// @source products.xml:83
#[teaql(relation(target = "BoxType", local_key = "box_type_id", foreign_key = "id"))]
    box_type: Option<crate::BoxType>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl BoxRental {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            quantity: 0_i64,
            rental_start: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            rental_end: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            total_cost: rust_decimal::Decimal::ZERO,
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            customer_id: 0_u64,
            box_type_id: 0_u64,
            customer: None,
            box_type: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("BoxRental", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.customer {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.box_type {
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

    pub fn quantity(&self) -> i64 {
        self.changed_quantity().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.quantity)
    }

    pub fn update_quantity(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.quantity = value.try_i64().map(|value| value as i64).unwrap_or(self.quantity.clone());
        self.root.set(self.entity_key(), "quantity", value);
        self
    }

    pub fn changed_quantity(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "quantity")
    }

    pub fn eval_quantity(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("quantity") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "quantity".to_string(), attempted_path: "quantity".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.quantity())
                }}

    pub fn rental_start(&self) -> chrono::NaiveDate {
        self.changed_rental_start().and_then(|value| value.try_date()).unwrap_or(self.rental_start)
    }

    pub fn update_rental_start(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.rental_start = value.try_date().unwrap_or(self.rental_start.clone());
        self.root.set(self.entity_key(), "rental_start", value);
        self
    }

    pub fn changed_rental_start(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "rental_start")
    }

    pub fn eval_rental_start(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("rental_start") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "rental_start".to_string(), attempted_path: "rental_start".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.rental_start())
                }}

    pub fn rental_end(&self) -> chrono::NaiveDate {
        self.changed_rental_end().and_then(|value| value.try_date()).unwrap_or(self.rental_end)
    }

    pub fn update_rental_end(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.rental_end = value.try_date().unwrap_or(self.rental_end.clone());
        self.root.set(self.entity_key(), "rental_end", value);
        self
    }

    pub fn changed_rental_end(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "rental_end")
    }

    pub fn eval_rental_end(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("rental_end") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "rental_end".to_string(), attempted_path: "rental_end".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.rental_end())
                }}

    pub fn total_cost(&self) -> rust_decimal::Decimal {
        self.changed_total_cost().and_then(|value| value.try_decimal()).unwrap_or(self.total_cost)
    }

    pub fn update_total_cost(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.total_cost = value.try_decimal().unwrap_or(self.total_cost.clone());
        self.root.set(self.entity_key(), "total_cost", value);
        self
    }

    pub fn changed_total_cost(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "total_cost")
    }

    pub fn eval_total_cost(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("total_cost") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "total_cost".to_string(), attempted_path: "total_cost".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.total_cost())
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
    pub fn customer_id(&self) -> u64 {
        self.changed_customer_id().and_then(|value| value.try_u64()).unwrap_or(self.customer_id)
    }

    pub fn update_customer_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.customer_id = value.try_u64().unwrap_or(self.customer_id.clone());
        self.root.set(self.entity_key(), "customer_id", value);
        self
    }

    pub fn changed_customer_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "customer_id")
    }

    pub fn eval_customer_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("customer_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_id".to_string(), attempted_path: "customer_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.customer_id())
                }}

    pub fn box_type_id(&self) -> u64 {
        self.changed_box_type_id().and_then(|value| value.try_u64()).unwrap_or(self.box_type_id)
    }

    pub fn update_box_type_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.box_type_id = value.try_u64().unwrap_or(self.box_type_id.clone());
        self.root.set(self.entity_key(), "box_type_id", value);
        self
    }

    pub fn changed_box_type_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "box_type_id")
    }

    pub fn eval_box_type_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("box_type_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "box_type_id".to_string(), attempted_path: "box_type_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.box_type_id())
                }}
    pub fn customer(&self) -> Option<&crate::Customer> {
        self.customer.as_ref()
    }

    pub fn eval_customer(&self) -> teaql_core::eval::EvalResult<&crate::Customer> {
        if !self.is_loaded("customer") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer".to_string(), attempted_path: "customer".to_string() }
        } else {
            match &self.customer {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn box_type(&self) -> Option<&crate::BoxType> {
        self.box_type.as_ref()
    }

    pub fn eval_box_type(&self) -> teaql_core::eval::EvalResult<&crate::BoxType> {
        if !self.is_loaded("box_type") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "box_type".to_string(), attempted_path: "box_type".to_string() }
        } else {
            match &self.box_type {
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

