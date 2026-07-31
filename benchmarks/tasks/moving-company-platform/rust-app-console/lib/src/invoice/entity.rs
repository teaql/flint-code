// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/invoice
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "Invoice", table = "invoice_data", data_service = "sqlite")]
pub struct Invoice {
#[teaql(id)]
    id: u64,

// @source finance.xml:54
    invoice_number: String,

// @source finance.xml:54
    issue_date: chrono::NaiveDate,

// @source finance.xml:54
    due_date: chrono::NaiveDate,

// @source finance.xml:54
    subtotal: rust_decimal::Decimal,

// @source finance.xml:54
    tax_amount: rust_decimal::Decimal,

// @source finance.xml:54
    total_amount: rust_decimal::Decimal,

// @source finance.xml:54
    status: String,

// @source finance.xml:54
    create_time: chrono::DateTime<chrono::Utc>,

// @source finance.xml:54
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source finance.xml:54
#[teaql(column = "customer")]
    customer_id: u64,

// @source finance.xml:54
#[teaql(column = "moving_job")]
    moving_job_id: u64,
// @source finance.xml:54
#[teaql(relation(target = "Customer", local_key = "customer_id", foreign_key = "id"))]
    customer: Option<crate::Customer>,

// @source finance.xml:54
#[teaql(relation(target = "MovingJob", local_key = "moving_job_id", foreign_key = "id"))]
    moving_job: Option<crate::MovingJob>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Invoice {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            invoice_number: String::new(),
            issue_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            due_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            subtotal: rust_decimal::Decimal::ZERO,
            tax_amount: rust_decimal::Decimal::ZERO,
            total_amount: rust_decimal::Decimal::ZERO,
            status: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            customer_id: 0_u64,
            moving_job_id: 0_u64,
            customer: None,
            moving_job: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Invoice", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.customer {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.moving_job {
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

    pub fn invoice_number(&self) -> String {
        self.changed_invoice_number().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.invoice_number.clone())
    }

    pub fn update_invoice_number(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.invoice_number = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.invoice_number.clone());
        self.root.set(self.entity_key(), "invoice_number", value);
        self
    }

    pub fn changed_invoice_number(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "invoice_number")
    }

    pub fn eval_invoice_number(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("invoice_number") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "invoice_number".to_string(), attempted_path: "invoice_number".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.invoice_number())
                }}

    pub fn issue_date(&self) -> chrono::NaiveDate {
        self.changed_issue_date().and_then(|value| value.try_date()).unwrap_or(self.issue_date)
    }

    pub fn update_issue_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.issue_date = value.try_date().unwrap_or(self.issue_date.clone());
        self.root.set(self.entity_key(), "issue_date", value);
        self
    }

    pub fn changed_issue_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "issue_date")
    }

    pub fn eval_issue_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("issue_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "issue_date".to_string(), attempted_path: "issue_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.issue_date())
                }}

    pub fn due_date(&self) -> chrono::NaiveDate {
        self.changed_due_date().and_then(|value| value.try_date()).unwrap_or(self.due_date)
    }

    pub fn update_due_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.due_date = value.try_date().unwrap_or(self.due_date.clone());
        self.root.set(self.entity_key(), "due_date", value);
        self
    }

    pub fn changed_due_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "due_date")
    }

    pub fn eval_due_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("due_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "due_date".to_string(), attempted_path: "due_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.due_date())
                }}

    pub fn subtotal(&self) -> rust_decimal::Decimal {
        self.changed_subtotal().and_then(|value| value.try_decimal()).unwrap_or(self.subtotal)
    }

    pub fn update_subtotal(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.subtotal = value.try_decimal().unwrap_or(self.subtotal.clone());
        self.root.set(self.entity_key(), "subtotal", value);
        self
    }

    pub fn changed_subtotal(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "subtotal")
    }

    pub fn eval_subtotal(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("subtotal") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "subtotal".to_string(), attempted_path: "subtotal".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.subtotal())
                }}

    pub fn tax_amount(&self) -> rust_decimal::Decimal {
        self.changed_tax_amount().and_then(|value| value.try_decimal()).unwrap_or(self.tax_amount)
    }

    pub fn update_tax_amount(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.tax_amount = value.try_decimal().unwrap_or(self.tax_amount.clone());
        self.root.set(self.entity_key(), "tax_amount", value);
        self
    }

    pub fn changed_tax_amount(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "tax_amount")
    }

    pub fn eval_tax_amount(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("tax_amount") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "tax_amount".to_string(), attempted_path: "tax_amount".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.tax_amount())
                }}

    pub fn total_amount(&self) -> rust_decimal::Decimal {
        self.changed_total_amount().and_then(|value| value.try_decimal()).unwrap_or(self.total_amount)
    }

    pub fn update_total_amount(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.total_amount = value.try_decimal().unwrap_or(self.total_amount.clone());
        self.root.set(self.entity_key(), "total_amount", value);
        self
    }

    pub fn changed_total_amount(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "total_amount")
    }

    pub fn eval_total_amount(&self) -> teaql_core::eval::EvalResult<rust_decimal::Decimal> {
        if !self.is_loaded("total_amount") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "total_amount".to_string(), attempted_path: "total_amount".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.total_amount())
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

    pub fn mark_as_delete(&mut self) -> &mut Self {
        self.root.mark_as_delete(self.entity_key());
        self
    }

    pub fn set_comment(&mut self, comment: impl Into<String>) -> &mut Self {
        self.root.set_comment(comment);
        self
    }
}

