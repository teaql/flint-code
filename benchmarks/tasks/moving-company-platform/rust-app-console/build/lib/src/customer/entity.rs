// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/customer
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
#[teaql(entity = "Customer", table = "customer_data", data_service = "sqlite", audit_mask_fields = "email,phone,first_name,last_name,company_name")]
pub struct Customer {
#[teaql(id)]
    id: u64,

// @source customers.xml:35
    first_name: String,

// @source customers.xml:35
    last_name: String,

// @source customers.xml:35
    email: String,

// @source customers.xml:35
    phone: String,

// @source customers.xml:35
    company_name: String,

// @source customers.xml:35
    create_time: chrono::DateTime<chrono::Utc>,

// @source customers.xml:35
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source customers.xml:35
#[teaql(column = "customer_type")]
    customer_type_id: u64,
// @source customers.xml:35
#[teaql(relation(target = "CustomerType", local_key = "customer_type_id", foreign_key = "id"))]
    customer_type: Option<crate::CustomerType>,
    #[teaql(boxed_relations)]
    pub _relations: Box<CustomerReverseRelations>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Customer {
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
            company_name: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            customer_type_id: 0_u64,
            customer_type: None,
            _relations: Box::new(CustomerReverseRelations::new()),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Customer", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.customer_type {
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

    pub fn company_name(&self) -> String {
        self.changed_company_name().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.company_name.clone())
    }

    pub fn update_company_name(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.company_name = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.company_name.clone());
        self.root.set(self.entity_key(), "company_name", value);
        self
    }

    pub fn changed_company_name(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "company_name")
    }

    pub fn eval_company_name(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("company_name") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "company_name".to_string(), attempted_path: "company_name".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.company_name())
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
    pub fn customer_type_id(&self) -> u64 {
        self.changed_customer_type_id().and_then(|value| value.try_u64()).unwrap_or(self.customer_type_id)
    }

    pub(crate) fn update_customer_type_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.customer_type_id = value.try_u64().unwrap_or(self.customer_type_id.clone());
        self.root.set(self.entity_key(), "customer_type_id", value);
        self
    }

    pub fn changed_customer_type_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "customer_type_id")
    }

    pub fn eval_customer_type_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("customer_type_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_type_id".to_string(), attempted_path: "customer_type_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.customer_type_id())
                }}
    pub fn update_customer_type_to_private(&mut self) -> &mut Self {
        self.update_customer_type_id(1001_u64)
    }

    pub fn customer_type_is_private(&self) -> bool {
        self.customer_type_id() == 1001_u64
    }
    pub fn update_customer_type_to_corporate(&mut self) -> &mut Self {
        self.update_customer_type_id(1002_u64)
    }

    pub fn customer_type_is_corporate(&self) -> bool {
        self.customer_type_id() == 1002_u64
    }
    pub fn customer_type(&self) -> Option<&crate::CustomerType> {
        self.customer_type.as_ref()
    }

    pub fn eval_customer_type(&self) -> teaql_core::eval::EvalResult<&crate::CustomerType> {
        if !self.is_loaded("customer_type") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_type".to_string(), attempted_path: "customer_type".to_string() }
        } else {
            match &self.customer_type {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }
    pub fn moving_job_list(&self) -> &SmartList<crate::MovingJob> {
        &self._relations.moving_job_list
    }

    pub fn moving_job_list_mut(&mut self) -> &mut SmartList<crate::MovingJob> {
        &mut self._relations.moving_job_list
    }

    pub fn eval_moving_job_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::MovingJob>> {
        if !self.is_loaded("moving_job_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "moving_job_list".to_string(), attempted_path: "moving_job_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.moving_job_list)
        }
    }

    pub fn customer_contact_list(&self) -> &SmartList<crate::CustomerContact> {
        &self._relations.customer_contact_list
    }

    pub fn customer_contact_list_mut(&mut self) -> &mut SmartList<crate::CustomerContact> {
        &mut self._relations.customer_contact_list
    }

    pub fn eval_customer_contact_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::CustomerContact>> {
        if !self.is_loaded("customer_contact_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_contact_list".to_string(), attempted_path: "customer_contact_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.customer_contact_list)
        }
    }

    pub fn billing_info_list(&self) -> &SmartList<crate::BillingInfo> {
        &self._relations.billing_info_list
    }

    pub fn billing_info_list_mut(&mut self) -> &mut SmartList<crate::BillingInfo> {
        &mut self._relations.billing_info_list
    }

    pub fn eval_billing_info_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::BillingInfo>> {
        if !self.is_loaded("billing_info_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "billing_info_list".to_string(), attempted_path: "billing_info_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.billing_info_list)
        }
    }

    pub fn customer_history_list(&self) -> &SmartList<crate::CustomerHistory> {
        &self._relations.customer_history_list
    }

    pub fn customer_history_list_mut(&mut self) -> &mut SmartList<crate::CustomerHistory> {
        &mut self._relations.customer_history_list
    }

    pub fn eval_customer_history_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::CustomerHistory>> {
        if !self.is_loaded("customer_history_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "customer_history_list".to_string(), attempted_path: "customer_history_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.customer_history_list)
        }
    }

    pub fn box_rental_list(&self) -> &SmartList<crate::BoxRental> {
        &self._relations.box_rental_list
    }

    pub fn box_rental_list_mut(&mut self) -> &mut SmartList<crate::BoxRental> {
        &mut self._relations.box_rental_list
    }

    pub fn eval_box_rental_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::BoxRental>> {
        if !self.is_loaded("box_rental_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "box_rental_list".to_string(), attempted_path: "box_rental_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.box_rental_list)
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

    pub fn contract_list(&self) -> &SmartList<crate::Contract> {
        &self._relations.contract_list
    }

    pub fn contract_list_mut(&mut self) -> &mut SmartList<crate::Contract> {
        &mut self._relations.contract_list
    }

    pub fn eval_contract_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::Contract>> {
        if !self.is_loaded("contract_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "contract_list".to_string(), attempted_path: "contract_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.contract_list)
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
pub struct CustomerReverseRelations {
#[teaql(relation(target = "MovingJob", local_key = "id", foreign_key = "customer_id", many))]
    moving_job_list: SmartList<crate::MovingJob>,
#[teaql(relation(target = "CustomerContact", local_key = "id", foreign_key = "customer_id", many))]
    customer_contact_list: SmartList<crate::CustomerContact>,
#[teaql(relation(target = "BillingInfo", local_key = "id", foreign_key = "customer_id", many))]
    billing_info_list: SmartList<crate::BillingInfo>,
#[teaql(relation(target = "CustomerHistory", local_key = "id", foreign_key = "customer_id", many))]
    customer_history_list: SmartList<crate::CustomerHistory>,
#[teaql(relation(target = "BoxRental", local_key = "id", foreign_key = "customer_id", many))]
    box_rental_list: SmartList<crate::BoxRental>,
#[teaql(relation(target = "Payment", local_key = "id", foreign_key = "customer_id", many))]
    payment_list: SmartList<crate::Payment>,
#[teaql(relation(target = "Invoice", local_key = "id", foreign_key = "customer_id", many))]
    invoice_list: SmartList<crate::Invoice>,
#[teaql(relation(target = "Contract", local_key = "id", foreign_key = "customer_id", many))]
    contract_list: SmartList<crate::Contract>,
}

impl CustomerReverseRelations {
    pub fn new() -> Self {
        Self {
            moving_job_list: Default::default(),
            customer_contact_list: Default::default(),
            billing_info_list: Default::default(),
            customer_history_list: Default::default(),
            box_rental_list: Default::default(),
            payment_list: Default::default(),
            invoice_list: Default::default(),
            contract_list: Default::default(),
        }
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        for entity in &mut self.moving_job_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.customer_contact_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.billing_info_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.customer_history_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.box_rental_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.payment_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.invoice_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.contract_list {
            entity.attach_root_recursive(root.clone());
        }
    }
}
