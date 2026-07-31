// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/billing_info
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "BillingInfo", table = "billing_info_data", data_service = "sqlite")]
pub struct BillingInfo {
#[teaql(id)]
    id: u64,

// @source customers.xml:60
    payment_method: String,

// @source customers.xml:60
    card_last_four: i64,

// @source customers.xml:60
    is_default: bool,

// @source customers.xml:60
    create_time: chrono::DateTime<chrono::Utc>,

// @source customers.xml:60
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source customers.xml:60
#[teaql(column = "customer")]
    customer_id: u64,

// @source customers.xml:60
#[teaql(column = "billing_address")]
    billing_address_id: u64,
// @source customers.xml:60
#[teaql(relation(target = "Customer", local_key = "customer_id", foreign_key = "id"))]
    customer: Option<crate::Customer>,

// @source customers.xml:60
#[teaql(relation(target = "Address", local_key = "billing_address_id", foreign_key = "id"))]
    billing_address: Option<crate::Address>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl BillingInfo {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            payment_method: String::new(),
            card_last_four: 0_i64,
            is_default: false,
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            customer_id: 0_u64,
            billing_address_id: 0_u64,
            customer: None,
            billing_address: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("BillingInfo", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.customer {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.billing_address {
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

    pub fn payment_method(&self) -> String {
        self.changed_payment_method().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.payment_method.clone())
    }

    pub fn update_payment_method(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.payment_method = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.payment_method.clone());
        self.root.set(self.entity_key(), "payment_method", value);
        self
    }

    pub fn changed_payment_method(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "payment_method")
    }

    pub fn eval_payment_method(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("payment_method") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "payment_method".to_string(), attempted_path: "payment_method".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.payment_method())
                }}

    pub fn card_last_four(&self) -> i64 {
        self.changed_card_last_four().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.card_last_four)
    }

    pub fn update_card_last_four(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.card_last_four = value.try_i64().map(|value| value as i64).unwrap_or(self.card_last_four.clone());
        self.root.set(self.entity_key(), "card_last_four", value);
        self
    }

    pub fn changed_card_last_four(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "card_last_four")
    }

    pub fn eval_card_last_four(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("card_last_four") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "card_last_four".to_string(), attempted_path: "card_last_four".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.card_last_four())
                }}

    pub fn is_default(&self) -> bool {
        self.changed_is_default().and_then(|value| value.try_bool()).unwrap_or(self.is_default)
    }

    pub fn update_is_default(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.is_default = value.try_bool().unwrap_or(self.is_default.clone());
        self.root.set(self.entity_key(), "is_default", value);
        self
    }

    pub fn changed_is_default(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "is_default")
    }

    pub fn eval_is_default(&self) -> teaql_core::eval::EvalResult<bool> {
        if !self.is_loaded("is_default") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "is_default".to_string(), attempted_path: "is_default".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.is_default())
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

    pub fn billing_address_id(&self) -> u64 {
        self.changed_billing_address_id().and_then(|value| value.try_u64()).unwrap_or(self.billing_address_id)
    }

    pub fn update_billing_address_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.billing_address_id = value.try_u64().unwrap_or(self.billing_address_id.clone());
        self.root.set(self.entity_key(), "billing_address_id", value);
        self
    }

    pub fn changed_billing_address_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "billing_address_id")
    }

    pub fn eval_billing_address_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("billing_address_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "billing_address_id".to_string(), attempted_path: "billing_address_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.billing_address_id())
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

    pub fn billing_address(&self) -> Option<&crate::Address> {
        self.billing_address.as_ref()
    }

    pub fn eval_billing_address(&self) -> teaql_core::eval::EvalResult<&crate::Address> {
        if !self.is_loaded("billing_address") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "billing_address".to_string(), attempted_path: "billing_address".to_string() }
        } else {
            match &self.billing_address {
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

