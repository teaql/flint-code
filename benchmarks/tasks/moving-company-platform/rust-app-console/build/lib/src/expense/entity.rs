// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/expense
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "Expense", table = "expense_data", data_service = "sqlite")]
pub struct Expense {
#[teaql(id)]
    id: u64,

// @source finance.xml:86
    amount: rust_decimal::Decimal,

// @source finance.xml:86
    expense_date: chrono::NaiveDate,

// @source finance.xml:86
    description: String,

// @source finance.xml:86
    receipt_number: String,

// @source finance.xml:86
    create_time: chrono::DateTime<chrono::Utc>,

// @source finance.xml:86
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source finance.xml:86
#[teaql(column = "expense_category")]
    expense_category_id: u64,

// @source finance.xml:86
#[teaql(column = "vehicle")]
    vehicle_id: u64,
// @source finance.xml:86
#[teaql(relation(target = "ExpenseCategory", local_key = "expense_category_id", foreign_key = "id"))]
    expense_category: Option<crate::ExpenseCategory>,

// @source finance.xml:86
#[teaql(relation(target = "Vehicle", local_key = "vehicle_id", foreign_key = "id"))]
    vehicle: Option<crate::Vehicle>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Expense {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            amount: rust_decimal::Decimal::ZERO,
            expense_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            description: String::new(),
            receipt_number: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            expense_category_id: 0_u64,
            vehicle_id: 0_u64,
            expense_category: None,
            vehicle: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Expense", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.expense_category {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.vehicle {
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

    pub fn amount(&self) -> rust_decimal::Decimal {
        self.changed_amount().and_then(|value| value.try_decimal()).unwrap_or(self.amount)
    }

    pub fn update_amount(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.amount = value.try_decimal().unwrap_or(self.amount.clone());
        self.root.set(self.entity_key(), "amount", value);
        self
    }

    pub fn changed_amount(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "amount")
    }

    pub fn eval_amount(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("amount") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "amount".to_string(), attempted_path: "amount".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.amount())
                }}

    pub fn expense_date(&self) -> chrono::NaiveDate {
        self.changed_expense_date().and_then(|value| value.try_date()).unwrap_or(self.expense_date)
    }

    pub fn update_expense_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.expense_date = value.try_date().unwrap_or(self.expense_date.clone());
        self.root.set(self.entity_key(), "expense_date", value);
        self
    }

    pub fn changed_expense_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "expense_date")
    }

    pub fn eval_expense_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("expense_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_date".to_string(), attempted_path: "expense_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.expense_date())
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

    pub fn receipt_number(&self) -> String {
        self.changed_receipt_number().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.receipt_number.clone())
    }

    pub fn update_receipt_number(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.receipt_number = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.receipt_number.clone());
        self.root.set(self.entity_key(), "receipt_number", value);
        self
    }

    pub fn changed_receipt_number(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "receipt_number")
    }

    pub fn eval_receipt_number(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("receipt_number") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "receipt_number".to_string(), attempted_path: "receipt_number".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.receipt_number())
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
    pub fn expense_category_id(&self) -> u64 {
        self.changed_expense_category_id().and_then(|value| value.try_u64()).unwrap_or(self.expense_category_id)
    }

    pub(crate) fn update_expense_category_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.expense_category_id = value.try_u64().unwrap_or(self.expense_category_id.clone());
        self.root.set(self.entity_key(), "expense_category_id", value);
        self
    }

    pub fn changed_expense_category_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "expense_category_id")
    }

    pub fn eval_expense_category_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("expense_category_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_category_id".to_string(), attempted_path: "expense_category_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.expense_category_id())
                }}

    pub fn vehicle_id(&self) -> u64 {
        self.changed_vehicle_id().and_then(|value| value.try_u64()).unwrap_or(self.vehicle_id)
    }

    pub fn update_vehicle_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.vehicle_id = value.try_u64().unwrap_or(self.vehicle_id.clone());
        self.root.set(self.entity_key(), "vehicle_id", value);
        self
    }

    pub fn changed_vehicle_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "vehicle_id")
    }

    pub fn eval_vehicle_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("vehicle_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "vehicle_id".to_string(), attempted_path: "vehicle_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.vehicle_id())
                }}
    pub fn update_expense_category_to_fuel(&mut self) -> &mut Self {
        self.update_expense_category_id(1001_u64)
    }

    pub fn expense_category_is_fuel(&self) -> bool {
        self.expense_category_id() == 1001_u64
    }
    pub fn update_expense_category_to_maintenance(&mut self) -> &mut Self {
        self.update_expense_category_id(1002_u64)
    }

    pub fn expense_category_is_maintenance(&self) -> bool {
        self.expense_category_id() == 1002_u64
    }
    pub fn update_expense_category_to_supplies(&mut self) -> &mut Self {
        self.update_expense_category_id(1003_u64)
    }

    pub fn expense_category_is_supplies(&self) -> bool {
        self.expense_category_id() == 1003_u64
    }
    pub fn update_expense_category_to_insurance(&mut self) -> &mut Self {
        self.update_expense_category_id(1004_u64)
    }

    pub fn expense_category_is_insurance(&self) -> bool {
        self.expense_category_id() == 1004_u64
    }
    pub fn expense_category(&self) -> Option<&crate::ExpenseCategory> {
        self.expense_category.as_ref()
    }

    pub fn eval_expense_category(&self) -> teaql_core::eval::EvalResult<&crate::ExpenseCategory> {
        if !self.is_loaded("expense_category") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_category".to_string(), attempted_path: "expense_category".to_string() }
        } else {
            match &self.expense_category {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn vehicle(&self) -> Option<&crate::Vehicle> {
        self.vehicle.as_ref()
    }

    pub fn eval_vehicle(&self) -> teaql_core::eval::EvalResult<&crate::Vehicle> {
        if !self.is_loaded("vehicle") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "vehicle".to_string(), attempted_path: "vehicle".to_string() }
        } else {
            match &self.vehicle {
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

