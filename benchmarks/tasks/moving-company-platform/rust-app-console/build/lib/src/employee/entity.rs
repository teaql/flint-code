// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/employee
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
#[teaql(entity = "Employee", table = "employee_data", data_service = "sqlite", audit_mask_fields = "email,phone,first_name,last_name")]
pub struct Employee {
#[teaql(id)]
    id: u64,

// @source employees.xml:20
    first_name: String,

// @source employees.xml:20
    last_name: String,

// @source employees.xml:20
    email: String,

// @source employees.xml:20
    phone: String,

// @source employees.xml:20
    hire_date: chrono::NaiveDate,

// @source employees.xml:20
    position: String,

// @source employees.xml:20
    is_active: bool,

// @source employees.xml:20
    create_time: chrono::DateTime<chrono::Utc>,

// @source employees.xml:20
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
    #[teaql(boxed_relations)]
    pub _relations: Box<EmployeeReverseRelations>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Employee {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            first_name: String::new(),
            last_name: String::new(),
            email: String::new(),
            phone: String::new(),
            hire_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            position: String::new(),
            is_active: false,
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            _relations: Box::new(EmployeeReverseRelations::new()),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Employee", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
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

    pub fn first_name(&self) -> String {
        self.changed_first_name().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.first_name.clone())
    }

    pub fn update_first_name(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.first_name = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.first_name.clone());
        self.root.set(self.entity_key(), "first_name", value);
        self
    }

    pub fn changed_first_name(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "first_name")
    }

    pub fn eval_first_name(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("first_name") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "first_name".to_string(), attempted_path: "first_name".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.first_name())
                }}

    pub fn last_name(&self) -> String {
        self.changed_last_name().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.last_name.clone())
    }

    pub fn update_last_name(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.last_name = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.last_name.clone());
        self.root.set(self.entity_key(), "last_name", value);
        self
    }

    pub fn changed_last_name(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "last_name")
    }

    pub fn eval_last_name(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("last_name") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "last_name".to_string(), attempted_path: "last_name".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.last_name())
                }}

    pub fn email(&self) -> String {
        self.changed_email().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.email.clone())
    }

    pub fn update_email(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.email = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.email.clone());
        self.root.set(self.entity_key(), "email", value);
        self
    }

    pub fn changed_email(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "email")
    }

    pub fn eval_email(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("email") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "email".to_string(), attempted_path: "email".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.email())
                }}

    pub fn phone(&self) -> String {
        self.changed_phone().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.phone.clone())
    }

    pub fn update_phone(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.phone = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.phone.clone());
        self.root.set(self.entity_key(), "phone", value);
        self
    }

    pub fn changed_phone(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "phone")
    }

    pub fn eval_phone(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("phone") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "phone".to_string(), attempted_path: "phone".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.phone())
                }}

    pub fn hire_date(&self) -> chrono::NaiveDate {
        self.changed_hire_date().and_then(|value| value.try_date()).unwrap_or(self.hire_date)
    }

    pub fn update_hire_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.hire_date = value.try_date().unwrap_or(self.hire_date.clone());
        self.root.set(self.entity_key(), "hire_date", value);
        self
    }

    pub fn changed_hire_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "hire_date")
    }

    pub fn eval_hire_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("hire_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "hire_date".to_string(), attempted_path: "hire_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.hire_date())
                }}

    pub fn position(&self) -> String {
        self.changed_position().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.position.clone())
    }

    pub fn update_position(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.position = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.position.clone());
        self.root.set(self.entity_key(), "position", value);
        self
    }

    pub fn changed_position(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "position")
    }

    pub fn eval_position(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("position") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "position".to_string(), attempted_path: "position".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.position())
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

    pub fn worked_hours_list(&self) -> &SmartList<crate::WorkedHours> {
        &self._relations.worked_hours_list
    }

    pub fn worked_hours_list_mut(&mut self) -> &mut SmartList<crate::WorkedHours> {
        &mut self._relations.worked_hours_list
    }

    pub fn eval_worked_hours_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::WorkedHours>> {
        if !self.is_loaded("worked_hours_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "worked_hours_list".to_string(), attempted_path: "worked_hours_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.worked_hours_list)
        }
    }

    pub fn payroll_calculation_list(&self) -> &SmartList<crate::PayrollCalculation> {
        &self._relations.payroll_calculation_list
    }

    pub fn payroll_calculation_list_mut(&mut self) -> &mut SmartList<crate::PayrollCalculation> {
        &mut self._relations.payroll_calculation_list
    }

    pub fn eval_payroll_calculation_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::PayrollCalculation>> {
        if !self.is_loaded("payroll_calculation_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "payroll_calculation_list".to_string(), attempted_path: "payroll_calculation_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.payroll_calculation_list)
        }
    }

    pub fn bonus_list(&self) -> &SmartList<crate::Bonus> {
        &self._relations.bonus_list
    }

    pub fn bonus_list_mut(&mut self) -> &mut SmartList<crate::Bonus> {
        &mut self._relations.bonus_list
    }

    pub fn eval_bonus_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::Bonus>> {
        if !self.is_loaded("bonus_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "bonus_list".to_string(), attempted_path: "bonus_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.bonus_list)
        }
    }

    pub fn leave_request_list(&self) -> &SmartList<crate::LeaveRequest> {
        &self._relations.leave_request_list
    }

    pub fn leave_request_list_mut(&mut self) -> &mut SmartList<crate::LeaveRequest> {
        &mut self._relations.leave_request_list
    }

    pub fn eval_leave_request_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::LeaveRequest>> {
        if !self.is_loaded("leave_request_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "leave_request_list".to_string(), attempted_path: "leave_request_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.leave_request_list)
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
pub struct EmployeeReverseRelations {
#[teaql(relation(target = "JobAssignment", local_key = "id", foreign_key = "employee_id", many))]
    job_assignment_list: SmartList<crate::JobAssignment>,
#[teaql(relation(target = "WorkedHours", local_key = "id", foreign_key = "employee_id", many))]
    worked_hours_list: SmartList<crate::WorkedHours>,
#[teaql(relation(target = "PayrollCalculation", local_key = "id", foreign_key = "employee_id", many))]
    payroll_calculation_list: SmartList<crate::PayrollCalculation>,
#[teaql(relation(target = "Bonus", local_key = "id", foreign_key = "employee_id", many))]
    bonus_list: SmartList<crate::Bonus>,
#[teaql(relation(target = "LeaveRequest", local_key = "id", foreign_key = "employee_id", many))]
    leave_request_list: SmartList<crate::LeaveRequest>,
}

impl EmployeeReverseRelations {
    pub fn new() -> Self {
        Self {
            job_assignment_list: Default::default(),
            worked_hours_list: Default::default(),
            payroll_calculation_list: Default::default(),
            bonus_list: Default::default(),
            leave_request_list: Default::default(),
        }
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        for entity in &mut self.job_assignment_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.worked_hours_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.payroll_calculation_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.bonus_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.leave_request_list {
            entity.attach_root_recursive(root.clone());
        }
    }
}
