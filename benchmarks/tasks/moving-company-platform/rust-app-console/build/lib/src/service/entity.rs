// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/service
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
#[teaql(entity = "Service", table = "service_data", data_service = "sqlite")]
pub struct Service {
#[teaql(id)]
    id: u64,

// @source products.xml:38
    name: String,

// @source products.xml:38
    description: String,

// @source products.xml:38
    base_price: rust_decimal::Decimal,

// @source products.xml:38
    price_per_hour: rust_decimal::Decimal,

// @source products.xml:38
    is_active: bool,

// @source products.xml:38
    create_time: chrono::DateTime<chrono::Utc>,

// @source products.xml:38
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source products.xml:38
#[teaql(column = "service_category")]
    service_category_id: u64,
// @source products.xml:38
#[teaql(relation(target = "ServiceCategory", local_key = "service_category_id", foreign_key = "id"))]
    service_category: Option<crate::ServiceCategory>,
#[teaql(relation(target = "ServiceConfiguration", local_key = "id", foreign_key = "service_id", many))]
    service_configuration_list: SmartList<crate::ServiceConfiguration>,
#[teaql(relation(target = "PricingRule", local_key = "id", foreign_key = "service_id", many))]
    pricing_rule_list: SmartList<crate::PricingRule>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Service {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            name: String::new(),
            description: String::new(),
            base_price: rust_decimal::Decimal::ZERO,
            price_per_hour: rust_decimal::Decimal::ZERO,
            is_active: false,
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            service_category_id: 0_u64,
            service_category: None,
            service_configuration_list: Default::default(),
            pricing_rule_list: Default::default(),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Service", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.service_category {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.service_configuration_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.pricing_rule_list {
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

    pub fn base_price(&self) -> rust_decimal::Decimal {
        self.changed_base_price().and_then(|value| value.try_decimal()).unwrap_or(self.base_price)
    }

    pub fn update_base_price(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.base_price = value.try_decimal().unwrap_or(self.base_price.clone());
        self.root.set(self.entity_key(), "base_price", value);
        self
    }

    pub fn changed_base_price(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "base_price")
    }

    pub fn eval_base_price(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("base_price") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "base_price".to_string(), attempted_path: "base_price".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.base_price())
                }}

    pub fn price_per_hour(&self) -> rust_decimal::Decimal {
        self.changed_price_per_hour().and_then(|value| value.try_decimal()).unwrap_or(self.price_per_hour)
    }

    pub fn update_price_per_hour(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.price_per_hour = value.try_decimal().unwrap_or(self.price_per_hour.clone());
        self.root.set(self.entity_key(), "price_per_hour", value);
        self
    }

    pub fn changed_price_per_hour(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "price_per_hour")
    }

    pub fn eval_price_per_hour(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("price_per_hour") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "price_per_hour".to_string(), attempted_path: "price_per_hour".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.price_per_hour())
                }}

    pub fn is_active(&self) -> bool {
        self.changed_is_active().and_then(|value| value.try_bool()).unwrap_or(self.is_active)
    }

    pub fn update_is_active(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.is_active = value.try_bool().unwrap_or(self.is_active.clone());
        self.root.set(self.entity_key(), "is_active", value);
        self
    }

    pub fn changed_is_active(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "is_active")
    }

    pub fn eval_is_active(&self) -> teaql_core::eval::EvalResult<bool> {
        if !self.is_loaded("is_active") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "is_active".to_string(), attempted_path: "is_active".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.is_active())
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
    pub fn service_category_id(&self) -> u64 {
        self.changed_service_category_id().and_then(|value| value.try_u64()).unwrap_or(self.service_category_id)
    }

    pub(crate) fn update_service_category_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.service_category_id = value.try_u64().unwrap_or(self.service_category_id.clone());
        self.root.set(self.entity_key(), "service_category_id", value);
        self
    }

    pub fn changed_service_category_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "service_category_id")
    }

    pub fn eval_service_category_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("service_category_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_category_id".to_string(), attempted_path: "service_category_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.service_category_id())
                }}
    pub fn update_service_category_to_moving(&mut self) -> &mut Self {
        self.update_service_category_id(1001_u64)
    }

    pub fn service_category_is_moving(&self) -> bool {
        self.service_category_id() == 1001_u64
    }
    pub fn update_service_category_to_cleaning(&mut self) -> &mut Self {
        self.update_service_category_id(1002_u64)
    }

    pub fn service_category_is_cleaning(&self) -> bool {
        self.service_category_id() == 1002_u64
    }
    pub fn update_service_category_to_box_rental(&mut self) -> &mut Self {
        self.update_service_category_id(1003_u64)
    }

    pub fn service_category_is_box_rental(&self) -> bool {
        self.service_category_id() == 1003_u64
    }
    pub fn update_service_category_to_additional(&mut self) -> &mut Self {
        self.update_service_category_id(1004_u64)
    }

    pub fn service_category_is_additional(&self) -> bool {
        self.service_category_id() == 1004_u64
    }
    pub fn service_category(&self) -> Option<&crate::ServiceCategory> {
        self.service_category.as_ref()
    }

    pub fn eval_service_category(&self) -> teaql_core::eval::EvalResult<&crate::ServiceCategory> {
        if !self.is_loaded("service_category") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_category".to_string(), attempted_path: "service_category".to_string() }
        } else {
            match &self.service_category {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }
    pub fn service_configuration_list(&self) -> &SmartList<crate::ServiceConfiguration> {
        &self.service_configuration_list
    }

    pub fn service_configuration_list_mut(&mut self) -> &mut SmartList<crate::ServiceConfiguration> {
        &mut self.service_configuration_list
    }

    pub fn eval_service_configuration_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::ServiceConfiguration>> {
        if !self.is_loaded("service_configuration_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "service_configuration_list".to_string(), attempted_path: "service_configuration_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.service_configuration_list)
        }
    }

    pub fn pricing_rule_list(&self) -> &SmartList<crate::PricingRule> {
        &self.pricing_rule_list
    }

    pub fn pricing_rule_list_mut(&mut self) -> &mut SmartList<crate::PricingRule> {
        &mut self.pricing_rule_list
    }

    pub fn eval_pricing_rule_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::PricingRule>> {
        if !self.is_loaded("pricing_rule_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "pricing_rule_list".to_string(), attempted_path: "pricing_rule_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.pricing_rule_list)
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

