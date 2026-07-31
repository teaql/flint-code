// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/payroll_calculation
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "PayrollCalculation", table = "payroll_calculation_data", data_service = "sqlite")]
pub struct PayrollCalculation {
#[teaql(id)]
    id: u64,

// @source employees.xml:59
    period_start: chrono::NaiveDate,

// @source employees.xml:59
    period_end: chrono::NaiveDate,

// @source employees.xml:59
    regular_hours: rust_decimal::Decimal,

// @source employees.xml:59
    overtime_hours: rust_decimal::Decimal,

// @source employees.xml:59
    hourly_rate: rust_decimal::Decimal,

// @source employees.xml:59
    gross_pay: rust_decimal::Decimal,

// @source employees.xml:59
    deductions: rust_decimal::Decimal,

// @source employees.xml:59
    net_pay: rust_decimal::Decimal,

// @source employees.xml:59
    create_time: chrono::DateTime<chrono::Utc>,

// @source employees.xml:59
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source employees.xml:59
#[teaql(column = "employee")]
    employee_id: u64,
// @source employees.xml:59
#[teaql(relation(target = "Employee", local_key = "employee_id", foreign_key = "id"))]
    employee: Option<crate::Employee>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl PayrollCalculation {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            period_start: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            period_end: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            regular_hours: rust_decimal::Decimal::ZERO,
            overtime_hours: rust_decimal::Decimal::ZERO,
            hourly_rate: rust_decimal::Decimal::ZERO,
            gross_pay: rust_decimal::Decimal::ZERO,
            deductions: rust_decimal::Decimal::ZERO,
            net_pay: rust_decimal::Decimal::ZERO,
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            employee_id: 0_u64,
            employee: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("PayrollCalculation", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.employee {
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

    pub fn period_start(&self) -> chrono::NaiveDate {
        self.changed_period_start().and_then(|value| value.try_date()).unwrap_or(self.period_start)
    }

    pub fn update_period_start(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.period_start = value.try_date().unwrap_or(self.period_start.clone());
        self.root.set(self.entity_key(), "period_start", value);
        self
    }

    pub fn changed_period_start(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "period_start")
    }

    pub fn eval_period_start(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("period_start") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "period_start".to_string(), attempted_path: "period_start".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.period_start())
                }}

    pub fn period_end(&self) -> chrono::NaiveDate {
        self.changed_period_end().and_then(|value| value.try_date()).unwrap_or(self.period_end)
    }

    pub fn update_period_end(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.period_end = value.try_date().unwrap_or(self.period_end.clone());
        self.root.set(self.entity_key(), "period_end", value);
        self
    }

    pub fn changed_period_end(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "period_end")
    }

    pub fn eval_period_end(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("period_end") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "period_end".to_string(), attempted_path: "period_end".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.period_end())
                }}

    pub fn regular_hours(&self) -> rust_decimal::Decimal {
        self.changed_regular_hours().and_then(|value| value.try_decimal()).unwrap_or(self.regular_hours)
    }

    pub fn update_regular_hours(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.regular_hours = value.try_decimal().unwrap_or(self.regular_hours.clone());
        self.root.set(self.entity_key(), "regular_hours", value);
        self
    }

    pub fn changed_regular_hours(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "regular_hours")
    }

    pub fn eval_regular_hours(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("regular_hours") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "regular_hours".to_string(), attempted_path: "regular_hours".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.regular_hours())
                }}

    pub fn overtime_hours(&self) -> rust_decimal::Decimal {
        self.changed_overtime_hours().and_then(|value| value.try_decimal()).unwrap_or(self.overtime_hours)
    }

    pub fn update_overtime_hours(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.overtime_hours = value.try_decimal().unwrap_or(self.overtime_hours.clone());
        self.root.set(self.entity_key(), "overtime_hours", value);
        self
    }

    pub fn changed_overtime_hours(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "overtime_hours")
    }

    pub fn eval_overtime_hours(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("overtime_hours") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "overtime_hours".to_string(), attempted_path: "overtime_hours".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.overtime_hours())
                }}

    pub fn hourly_rate(&self) -> rust_decimal::Decimal {
        self.changed_hourly_rate().and_then(|value| value.try_decimal()).unwrap_or(self.hourly_rate)
    }

    pub fn update_hourly_rate(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.hourly_rate = value.try_decimal().unwrap_or(self.hourly_rate.clone());
        self.root.set(self.entity_key(), "hourly_rate", value);
        self
    }

    pub fn changed_hourly_rate(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "hourly_rate")
    }

    pub fn eval_hourly_rate(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("hourly_rate") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "hourly_rate".to_string(), attempted_path: "hourly_rate".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.hourly_rate())
                }}

    pub fn gross_pay(&self) -> rust_decimal::Decimal {
        self.changed_gross_pay().and_then(|value| value.try_decimal()).unwrap_or(self.gross_pay)
    }

    pub fn update_gross_pay(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.gross_pay = value.try_decimal().unwrap_or(self.gross_pay.clone());
        self.root.set(self.entity_key(), "gross_pay", value);
        self
    }

    pub fn changed_gross_pay(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "gross_pay")
    }

    pub fn eval_gross_pay(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("gross_pay") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "gross_pay".to_string(), attempted_path: "gross_pay".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.gross_pay())
                }}

    pub fn deductions(&self) -> rust_decimal::Decimal {
        self.changed_deductions().and_then(|value| value.try_decimal()).unwrap_or(self.deductions)
    }

    pub fn update_deductions(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.deductions = value.try_decimal().unwrap_or(self.deductions.clone());
        self.root.set(self.entity_key(), "deductions", value);
        self
    }

    pub fn changed_deductions(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "deductions")
    }

    pub fn eval_deductions(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("deductions") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "deductions".to_string(), attempted_path: "deductions".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.deductions())
                }}

    pub fn net_pay(&self) -> rust_decimal::Decimal {
        self.changed_net_pay().and_then(|value| value.try_decimal()).unwrap_or(self.net_pay)
    }

    pub fn update_net_pay(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.net_pay = value.try_decimal().unwrap_or(self.net_pay.clone());
        self.root.set(self.entity_key(), "net_pay", value);
        self
    }

    pub fn changed_net_pay(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "net_pay")
    }

    pub fn eval_net_pay(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("net_pay") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "net_pay".to_string(), attempted_path: "net_pay".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.net_pay())
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
    pub fn employee_id(&self) -> u64 {
        self.changed_employee_id().and_then(|value| value.try_u64()).unwrap_or(self.employee_id)
    }

    pub fn update_employee_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.employee_id = value.try_u64().unwrap_or(self.employee_id.clone());
        self.root.set(self.entity_key(), "employee_id", value);
        self
    }

    pub fn changed_employee_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "employee_id")
    }

    pub fn eval_employee_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("employee_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "employee_id".to_string(), attempted_path: "employee_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.employee_id())
                }}
    pub fn employee(&self) -> Option<&crate::Employee> {
        self.employee.as_ref()
    }

    pub fn eval_employee(&self) -> teaql_core::eval::EvalResult<&crate::Employee> {
        if !self.is_loaded("employee") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "employee".to_string(), attempted_path: "employee".to_string() }
        } else {
            match &self.employee {
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

