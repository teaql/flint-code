// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/moving_job
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
#[teaql(entity = "MovingJob", table = "moving_job_data", data_service = "sqlite")]
pub struct MovingJob {
#[teaql(id)]
    id: u64,

// @source operations.xml:70
    scheduled_date: chrono::NaiveDate,

// @source operations.xml:70
    notes: String,

// @source operations.xml:70
    create_time: chrono::DateTime<chrono::Utc>,

// @source operations.xml:70
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source operations.xml:70
#[teaql(column = "customer")]
    customer_id: u64,

// @source operations.xml:70
#[teaql(column = "route")]
    route_id: u64,

// @source operations.xml:70
#[teaql(column = "time_slot")]
    time_slot_id: u64,

// @source operations.xml:70
#[teaql(column = "status")]
    status_id: u64,
// @source operations.xml:70
#[teaql(relation(target = "Customer", local_key = "customer_id", foreign_key = "id"))]
    customer: Option<crate::Customer>,

// @source operations.xml:70
#[teaql(relation(target = "Route", local_key = "route_id", foreign_key = "id"))]
    route: Option<crate::Route>,

// @source operations.xml:70
#[teaql(relation(target = "TimeSlot", local_key = "time_slot_id", foreign_key = "id"))]
    time_slot: Option<crate::TimeSlot>,

// @source operations.xml:70
#[teaql(relation(target = "MoveStatus", local_key = "status_id", foreign_key = "id"))]
    status: Option<crate::MoveStatus>,
    #[teaql(boxed_relations)]
    pub _relations: Box<MovingJobReverseRelations>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl MovingJob {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            scheduled_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            notes: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            customer_id: 0_u64,
            route_id: 0_u64,
            time_slot_id: 0_u64,
            status_id: 0_u64,
            customer: None,
            route: None,
            time_slot: None,
            status: None,
            _relations: Box::new(MovingJobReverseRelations::new()),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("MovingJob", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.customer {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.route {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.time_slot {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.status {
            entity.attach_root_recursive(root.clone());
        }
        self._relations.attach_root_recursive(root.clone());
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

    pub fn scheduled_date(&self) -> chrono::NaiveDate {
        self.changed_scheduled_date().and_then(|value| value.try_date()).unwrap_or(self.scheduled_date)
    }

    pub fn update_scheduled_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.scheduled_date = value.try_date().unwrap_or(self.scheduled_date.clone());
        self.root.set(self.entity_key(), "scheduled_date", value);
        self
    }

    pub fn changed_scheduled_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "scheduled_date")
    }

    pub fn eval_scheduled_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("scheduled_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "scheduled_date".to_string(), attempted_path: "scheduled_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.scheduled_date())
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

    pub fn route_id(&self) -> u64 {
        self.changed_route_id().and_then(|value| value.try_u64()).unwrap_or(self.route_id)
    }

    pub fn update_route_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.route_id = value.try_u64().unwrap_or(self.route_id.clone());
        self.root.set(self.entity_key(), "route_id", value);
        self
    }

    pub fn changed_route_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "route_id")
    }

    pub fn eval_route_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("route_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "route_id".to_string(), attempted_path: "route_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.route_id())
                }}

    pub fn time_slot_id(&self) -> u64 {
        self.changed_time_slot_id().and_then(|value| value.try_u64()).unwrap_or(self.time_slot_id)
    }

    pub fn update_time_slot_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.time_slot_id = value.try_u64().unwrap_or(self.time_slot_id.clone());
        self.root.set(self.entity_key(), "time_slot_id", value);
        self
    }

    pub fn changed_time_slot_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "time_slot_id")
    }

    pub fn eval_time_slot_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("time_slot_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "time_slot_id".to_string(), attempted_path: "time_slot_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.time_slot_id())
                }}

    pub fn status_id(&self) -> u64 {
        self.changed_status_id().and_then(|value| value.try_u64()).unwrap_or(self.status_id)
    }

    pub(crate) fn update_status_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.status_id = value.try_u64().unwrap_or(self.status_id.clone());
        self.root.set(self.entity_key(), "status_id", value);
        self
    }

    pub fn changed_status_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "status_id")
    }

    pub fn eval_status_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("status_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "status_id".to_string(), attempted_path: "status_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.status_id())
                }}
    pub fn update_status_to_scheduled(&mut self) -> &mut Self {
        self.update_status_id(1001_u64)
    }

    pub fn status_is_scheduled(&self) -> bool {
        self.status_id() == 1001_u64
    }
    pub fn update_status_to_in_progress(&mut self) -> &mut Self {
        self.update_status_id(1002_u64)
    }

    pub fn status_is_in_progress(&self) -> bool {
        self.status_id() == 1002_u64
    }
    pub fn update_status_to_completed(&mut self) -> &mut Self {
        self.update_status_id(1003_u64)
    }

    pub fn status_is_completed(&self) -> bool {
        self.status_id() == 1003_u64
    }
    pub fn update_status_to_cancelled(&mut self) -> &mut Self {
        self.update_status_id(1004_u64)
    }

    pub fn status_is_cancelled(&self) -> bool {
        self.status_id() == 1004_u64
    }
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

    pub fn route(&self) -> Option<&crate::Route> {
        self.route.as_ref()
    }

    pub fn eval_route(&self) -> teaql_core::eval::EvalResult<&crate::Route> {
        if !self.is_loaded("route") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "route".to_string(), attempted_path: "route".to_string() }
        } else {
            match &self.route {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn time_slot(&self) -> Option<&crate::TimeSlot> {
        self.time_slot.as_ref()
    }

    pub fn eval_time_slot(&self) -> teaql_core::eval::EvalResult<&crate::TimeSlot> {
        if !self.is_loaded("time_slot") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "time_slot".to_string(), attempted_path: "time_slot".to_string() }
        } else {
            match &self.time_slot {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn status(&self) -> Option<&crate::MoveStatus> {
        self.status.as_ref()
    }

    pub fn eval_status(&self) -> teaql_core::eval::EvalResult<&crate::MoveStatus> {
        if !self.is_loaded("status") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "status".to_string(), attempted_path: "status".to_string() }
        } else {
            match &self.status {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }
    pub fn fulfillment_event_list(&self) -> &SmartList<crate::FulfillmentEvent> {
        &self._relations.fulfillment_event_list
    }

    pub fn fulfillment_event_list_mut(&mut self) -> &mut SmartList<crate::FulfillmentEvent> {
        &mut self._relations.fulfillment_event_list
    }

    pub fn eval_fulfillment_event_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::FulfillmentEvent>> {
        if !self.is_loaded("fulfillment_event_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "fulfillment_event_list".to_string(), attempted_path: "fulfillment_event_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.fulfillment_event_list)
        }
    }

    pub fn job_assignment_list(&self) -> &SmartList<crate::JobAssignment> {
        &self._relations.job_assignment_list
    }

    pub fn job_assignment_list_mut(&mut self) -> &mut SmartList<crate::JobAssignment> {
        &mut self._relations.job_assignment_list
    }

    pub fn eval_job_assignment_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::JobAssignment>> {
        if !self.is_loaded("job_assignment_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "job_assignment_list".to_string(), attempted_path: "job_assignment_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.job_assignment_list)
        }
    }

    pub fn payment_list(&self) -> &SmartList<crate::Payment> {
        &self._relations.payment_list
    }

    pub fn payment_list_mut(&mut self) -> &mut SmartList<crate::Payment> {
        &mut self._relations.payment_list
    }

    pub fn eval_payment_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::Payment>> {
        if !self.is_loaded("payment_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "payment_list".to_string(), attempted_path: "payment_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.payment_list)
        }
    }

    pub fn invoice_list(&self) -> &SmartList<crate::Invoice> {
        &self._relations.invoice_list
    }

    pub fn invoice_list_mut(&mut self) -> &mut SmartList<crate::Invoice> {
        &mut self._relations.invoice_list
    }

    pub fn eval_invoice_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::Invoice>> {
        if !self.is_loaded("invoice_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "invoice_list".to_string(), attempted_path: "invoice_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.invoice_list)
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

#[derive(Clone, Debug, PartialEq, teaql_macros::TeaqlReverseRelations)]
pub struct MovingJobReverseRelations {
#[teaql(relation(target = "FulfillmentEvent", local_key = "id", foreign_key = "moving_job_id", many))]
    fulfillment_event_list: SmartList<crate::FulfillmentEvent>,
#[teaql(relation(target = "JobAssignment", local_key = "id", foreign_key = "moving_job_id", many))]
    job_assignment_list: SmartList<crate::JobAssignment>,
#[teaql(relation(target = "Payment", local_key = "id", foreign_key = "moving_job_id", many))]
    payment_list: SmartList<crate::Payment>,
#[teaql(relation(target = "Invoice", local_key = "id", foreign_key = "moving_job_id", many))]
    invoice_list: SmartList<crate::Invoice>,
}

impl MovingJobReverseRelations {
    pub fn new() -> Self {
        Self {
            fulfillment_event_list: Default::default(),
            job_assignment_list: Default::default(),
            payment_list: Default::default(),
            invoice_list: Default::default(),
        }
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        for entity in &mut self.fulfillment_event_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.job_assignment_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.payment_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.invoice_list {
            entity.attach_root_recursive(root.clone());
        }
    }
}
