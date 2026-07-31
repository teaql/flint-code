// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/job_assignment
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
#[teaql(entity = "JobAssignment", table = "job_assignment_data", data_service = "sqlite")]
pub struct JobAssignment {
#[teaql(id)]
    id: u64,

// @source employees.xml:32
    role: String,

// @source employees.xml:32
    assigned_date: chrono::NaiveDate,

// @source employees.xml:32
    start_time: String,

// @source employees.xml:32
    end_time: String,

// @source employees.xml:32
    create_time: chrono::DateTime<chrono::Utc>,

// @source employees.xml:32
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source employees.xml:32
#[teaql(column = "employee")]
    employee_id: u64,

// @source employees.xml:32
#[teaql(column = "moving_job")]
    moving_job_id: u64,
// @source employees.xml:32
#[teaql(relation(target = "Employee", local_key = "employee_id", foreign_key = "id"))]
    employee: Option<crate::Employee>,

// @source employees.xml:32
#[teaql(relation(target = "MovingJob", local_key = "moving_job_id", foreign_key = "id"))]
    moving_job: Option<crate::MovingJob>,
#[teaql(relation(target = "WorkedHours", local_key = "id", foreign_key = "job_assignment_id", many))]
    worked_hours_list: SmartList<crate::WorkedHours>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl JobAssignment {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            role: String::new(),
            assigned_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            start_time: String::new(),
            end_time: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            employee_id: 0_u64,
            moving_job_id: 0_u64,
            employee: None,
            moving_job: None,
            worked_hours_list: Default::default(),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("JobAssignment", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.employee {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.moving_job {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.worked_hours_list {
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

    pub fn role(&self) -> String {
        self.changed_role().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.role.clone())
    }

    pub fn update_role(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.role = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.role.clone());
        self.root.set(self.entity_key(), "role", value);
        self
    }

    pub fn changed_role(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "role")
    }

    pub fn eval_role(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("role") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "role".to_string(), attempted_path: "role".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.role())
                }}

    pub fn assigned_date(&self) -> chrono::NaiveDate {
        self.changed_assigned_date().and_then(|value| value.try_date()).unwrap_or(self.assigned_date)
    }

    pub fn update_assigned_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.assigned_date = value.try_date().unwrap_or(self.assigned_date.clone());
        self.root.set(self.entity_key(), "assigned_date", value);
        self
    }

    pub fn changed_assigned_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "assigned_date")
    }

    pub fn eval_assigned_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("assigned_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "assigned_date".to_string(), attempted_path: "assigned_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.assigned_date())
                }}

    pub fn start_time(&self) -> String {
        self.changed_start_time().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.start_time.clone())
    }

    pub fn update_start_time(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.start_time = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.start_time.clone());
        self.root.set(self.entity_key(), "start_time", value);
        self
    }

    pub fn changed_start_time(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "start_time")
    }

    pub fn eval_start_time(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("start_time") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "start_time".to_string(), attempted_path: "start_time".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.start_time())
                }}

    pub fn end_time(&self) -> String {
        self.changed_end_time().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.end_time.clone())
    }

    pub fn update_end_time(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.end_time = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.end_time.clone());
        self.root.set(self.entity_key(), "end_time", value);
        self
    }

    pub fn changed_end_time(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "end_time")
    }

    pub fn eval_end_time(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("end_time") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "end_time".to_string(), attempted_path: "end_time".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.end_time())
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
    pub fn worked_hours_list(&self) -> &SmartList<crate::WorkedHours> {
        &self.worked_hours_list
    }

    pub fn worked_hours_list_mut(&mut self) -> &mut SmartList<crate::WorkedHours> {
        &mut self.worked_hours_list
    }

    pub fn eval_worked_hours_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::WorkedHours>> {
        if !self.is_loaded("worked_hours_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "worked_hours_list".to_string(), attempted_path: "worked_hours_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self.worked_hours_list)
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

