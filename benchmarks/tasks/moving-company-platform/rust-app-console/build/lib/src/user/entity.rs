// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/user
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
#[teaql(entity = "User", table = "user_data", data_service = "sqlite", audit_mask_fields = "password_hash,email,username,first_name,last_name")]
pub struct User {
#[teaql(id)]
    id: u64,

// @source platform.xml:41
    username: String,

// @source platform.xml:41
    email: String,

// @source platform.xml:41
    password_hash: String,

// @source platform.xml:41
    first_name: String,

// @source platform.xml:41
    last_name: String,

// @source platform.xml:41
    is_active: bool,

// @source platform.xml:41
    last_login: String,

// @source platform.xml:41
    create_time: chrono::DateTime<chrono::Utc>,

// @source platform.xml:41
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source platform.xml:41
#[teaql(column = "role")]
    role_id: u64,
// @source platform.xml:41
#[teaql(relation(target = "Role", local_key = "role_id", foreign_key = "id"))]
    role: Option<crate::Role>,
    #[teaql(boxed_relations)]
    pub _relations: Box<UserReverseRelations>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl User {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            username: String::new(),
            email: String::new(),
            password_hash: String::new(),
            first_name: String::new(),
            last_name: String::new(),
            is_active: false,
            last_login: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            role_id: 0_u64,
            role: None,
            _relations: Box::new(UserReverseRelations::new()),
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("User", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.role {
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

    pub fn username(&self) -> String {
        self.changed_username().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.username.clone())
    }

    pub fn update_username(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.username = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.username.clone());
        self.root.set(self.entity_key(), "username", value);
        self
    }

    pub fn changed_username(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "username")
    }

    pub fn eval_username(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("username") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "username".to_string(), attempted_path: "username".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.username())
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

    pub fn password_hash(&self) -> String {
        self.changed_password_hash().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.password_hash.clone())
    }

    pub fn update_password_hash(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.password_hash = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.password_hash.clone());
        self.root.set(self.entity_key(), "password_hash", value);
        self
    }

    pub fn changed_password_hash(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "password_hash")
    }

    pub fn eval_password_hash(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("password_hash") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "password_hash".to_string(), attempted_path: "password_hash".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.password_hash())
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

    pub fn last_login(&self) -> String {
        self.changed_last_login().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.last_login.clone())
    }

    pub fn update_last_login(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.last_login = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.last_login.clone());
        self.root.set(self.entity_key(), "last_login", value);
        self
    }

    pub fn changed_last_login(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "last_login")
    }

    pub fn eval_last_login(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("last_login") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "last_login".to_string(), attempted_path: "last_login".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.last_login())
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
    pub fn role_id(&self) -> u64 {
        self.changed_role_id().and_then(|value| value.try_u64()).unwrap_or(self.role_id)
    }

    pub(crate) fn update_role_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.role_id = value.try_u64().unwrap_or(self.role_id.clone());
        self.root.set(self.entity_key(), "role_id", value);
        self
    }

    pub fn changed_role_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "role_id")
    }

    pub fn eval_role_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("role_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "role_id".to_string(), attempted_path: "role_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.role_id())
                }}
    pub fn update_role_to_admin(&mut self) -> &mut Self {
        self.update_role_id(1001_u64)
    }

    pub fn role_is_admin(&self) -> bool {
        self.role_id() == 1001_u64
    }
    pub fn update_role_to_manager(&mut self) -> &mut Self {
        self.update_role_id(1002_u64)
    }

    pub fn role_is_manager(&self) -> bool {
        self.role_id() == 1002_u64
    }
    pub fn update_role_to_employee(&mut self) -> &mut Self {
        self.update_role_id(1003_u64)
    }

    pub fn role_is_employee(&self) -> bool {
        self.role_id() == 1003_u64
    }
    pub fn update_role_to_customer(&mut self) -> &mut Self {
        self.update_role_id(1004_u64)
    }

    pub fn role_is_customer(&self) -> bool {
        self.role_id() == 1004_u64
    }
    pub fn role(&self) -> Option<&crate::Role> {
        self.role.as_ref()
    }

    pub fn eval_role(&self) -> teaql_core::eval::EvalResult<&crate::Role> {
        if !self.is_loaded("role") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "role".to_string(), attempted_path: "role".to_string() }
        } else {
            match &self.role {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }
    pub fn audit_log_list(&self) -> &SmartList<crate::AuditLog> {
        &self._relations.audit_log_list
    }

    pub fn audit_log_list_mut(&mut self) -> &mut SmartList<crate::AuditLog> {
        &mut self._relations.audit_log_list
    }

    pub fn eval_audit_log_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::AuditLog>> {
        if !self.is_loaded("audit_log_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "audit_log_list".to_string(), attempted_path: "audit_log_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.audit_log_list)
        }
    }

    pub fn magic_link_list(&self) -> &SmartList<crate::MagicLink> {
        &self._relations.magic_link_list
    }

    pub fn magic_link_list_mut(&mut self) -> &mut SmartList<crate::MagicLink> {
        &mut self._relations.magic_link_list
    }

    pub fn eval_magic_link_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::MagicLink>> {
        if !self.is_loaded("magic_link_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "magic_link_list".to_string(), attempted_path: "magic_link_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.magic_link_list)
        }
    }

    pub fn activity_log_list(&self) -> &SmartList<crate::ActivityLog> {
        &self._relations.activity_log_list
    }

    pub fn activity_log_list_mut(&mut self) -> &mut SmartList<crate::ActivityLog> {
        &mut self._relations.activity_log_list
    }

    pub fn eval_activity_log_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::ActivityLog>> {
        if !self.is_loaded("activity_log_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "activity_log_list".to_string(), attempted_path: "activity_log_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.activity_log_list)
        }
    }

    pub fn notification_list(&self) -> &SmartList<crate::Notification> {
        &self._relations.notification_list
    }

    pub fn notification_list_mut(&mut self) -> &mut SmartList<crate::Notification> {
        &mut self._relations.notification_list
    }

    pub fn eval_notification_list(&self) -> teaql_core::eval::EvalResult<&SmartList<crate::Notification>> {
        if !self.is_loaded("notification_list") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "notification_list".to_string(), attempted_path: "notification_list".to_string() }
        } else {
            teaql_core::eval::EvalResult::Value(&self._relations.notification_list)
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
pub struct UserReverseRelations {
#[teaql(relation(target = "AuditLog", local_key = "id", foreign_key = "user_id", many))]
    audit_log_list: SmartList<crate::AuditLog>,
#[teaql(relation(target = "MagicLink", local_key = "id", foreign_key = "user_id", many))]
    magic_link_list: SmartList<crate::MagicLink>,
#[teaql(relation(target = "ActivityLog", local_key = "id", foreign_key = "user_id", many))]
    activity_log_list: SmartList<crate::ActivityLog>,
#[teaql(relation(target = "Notification", local_key = "id", foreign_key = "user_id", many))]
    notification_list: SmartList<crate::Notification>,
}

impl UserReverseRelations {
    pub fn new() -> Self {
        Self {
            audit_log_list: Default::default(),
            magic_link_list: Default::default(),
            activity_log_list: Default::default(),
            notification_list: Default::default(),
        }
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        for entity in &mut self.audit_log_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.magic_link_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.activity_log_list {
            entity.attach_root_recursive(root.clone());
        }
        for entity in &mut self.notification_list {
            entity.attach_root_recursive(root.clone());
        }
    }
}
