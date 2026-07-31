// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/campaign
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
#[teaql(entity = "Campaign", table = "campaign_data", data_service = "sqlite")]
pub struct Campaign {
#[teaql(id)]
    id: u64,

// @source marketing.xml:18
    name: String,

// @source marketing.xml:18
    description: String,

// @source marketing.xml:18
    start_date: chrono::NaiveDate,

// @source marketing.xml:18
    end_date: chrono::NaiveDate,

// @source marketing.xml:18
    budget: rust_decimal::Decimal,

// @source marketing.xml:18
    status: String,

// @source marketing.xml:18
    create_time: chrono::DateTime<chrono::Utc>,

// @source marketing.xml:18
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
#[teaql(relation(target = "DiscountCode", local_key = "id", foreign_key = "campaign_id", many))]
    discount_code_list: SmartList<crate::DiscountCode>,
#[teaql(relation(target = "ConversionMetric", local_key = "id", foreign_key = "campaign_id", many))]
    conversion_metric_list: SmartList<crate::ConversionMetric>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Campaign {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            name: String::new(),
            description: String::new(),
            start_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            end_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            budget: rust_decimal::Decimal::ZERO,
            status: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            discount_code_list: Default::default(),
            conversion_metric_list: Default::default(),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Campaign", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        for entity in &mut self.discount_code_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.conversion_metric_list {
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

    pub fn start_date(&self) -> chrono::NaiveDate {
        self.changed_start_date().and_then(|value| value.try_date()).unwrap_or(self.start_date)
    }

    pub fn update_start_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.start_date = value.try_date().unwrap_or(self.start_date.clone());
        self.root.set(self.entity_key(), "start_date", value);
        self
    }

    pub fn changed_start_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "start_date")
    }

    pub fn eval_start_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("start_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "start_date".to_string(), attempted_path: "start_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.start_date())
                }}

    pub fn end_date(&self) -> chrono::NaiveDate {
        self.changed_end_date().and_then(|value| value.try_date()).unwrap_or(self.end_date)
    }

    pub fn update_end_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.end_date = value.try_date().unwrap_or(self.end_date.clone());
        self.root.set(self.entity_key(), "end_date", value);
        self
    }

    pub fn changed_end_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "end_date")
    }

    pub fn eval_end_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("end_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "end_date".to_string(), attempted_path: "end_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.end_date())
                }}

    pub fn budget(&self) -> rust_decimal::Decimal {
        self.changed_budget().and_then(|value| value.try_decimal()).unwrap_or(self.budget)
    }

    pub fn update_budget(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.budget = value.try_decimal().unwrap_or(self.budget.clone());
        self.root.set(self.entity_key(), "budget", value);
        self
    }

    pub fn changed_budget(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "budget")
    }

    pub fn eval_budget(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("budget") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "budget".to_string(), attempted_path: "budget".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.budget())
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
    pub fn discount_code_list(&self) -> &SmartList<crate::DiscountCode> {
        &self.discount_code_list
    }

    pub fn discount_code_list_mut(&mut self) -> &mut SmartList<crate::DiscountCode> {
        &mut self.discount_code_list
    }

    pub fn eval_discount_code_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::DiscountCode>> {
        if !self.is_loaded("discount_code_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "discount_code_list".to_string(), attempted_path: "discount_code_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.discount_code_list)
        }
    }

    pub fn conversion_metric_list(&self) -> &SmartList<crate::ConversionMetric> {
        &self.conversion_metric_list
    }

    pub fn conversion_metric_list_mut(&mut self) -> &mut SmartList<crate::ConversionMetric> {
        &mut self.conversion_metric_list
    }

    pub fn eval_conversion_metric_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::ConversionMetric>> {
        if !self.is_loaded("conversion_metric_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "conversion_metric_list".to_string(), attempted_path: "conversion_metric_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.conversion_metric_list)
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

