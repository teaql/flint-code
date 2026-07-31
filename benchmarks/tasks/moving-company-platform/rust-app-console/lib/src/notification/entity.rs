// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/notification
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "Notification", table = "notification_data", data_service = "sqlite")]
pub struct Notification {
#[teaql(id)]
    id: u64,

// @source platform.xml:124
    title: String,

// @source platform.xml:124
    message: String,

// @source platform.xml:124
    is_read: bool,

// @source platform.xml:124
    sent_at: String,

// @source platform.xml:124
    create_time: chrono::DateTime<chrono::Utc>,

// @source platform.xml:124
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source platform.xml:124
#[teaql(column = "\"user\"")]
    user_id: u64,

// @source platform.xml:124
#[teaql(column = "notification_type")]
    notification_type_id: u64,
// @source platform.xml:124
#[teaql(relation(target = "User", local_key = "user_id", foreign_key = "id"))]
    user: Option<crate::User>,

// @source platform.xml:124
#[teaql(relation(target = "NotificationType", local_key = "notification_type_id", foreign_key = "id"))]
    notification_type: Option<crate::NotificationType>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Notification {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            title: String::new(),
            message: String::new(),
            is_read: false,
            sent_at: String::new(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            user_id: 0_u64,
            notification_type_id: 0_u64,
            user: None,
            notification_type: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Notification", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.user {
            entity.attach_root_recursive(root.clone());
        }
        if let Some(entity) = &mut self.notification_type {
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

    pub fn title(&self) -> String {
        self.changed_title().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.title.clone())
    }

    pub fn update_title(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.title = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.title.clone());
        self.root.set(self.entity_key(), "title", value);
        self
    }

    pub fn changed_title(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "title")
    }

    pub fn eval_title(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("title") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "title".to_string(), attempted_path: "title".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.title())
                }}

    pub fn message(&self) -> String {
        self.changed_message().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.message.clone())
    }

    pub fn update_message(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.message = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.message.clone());
        self.root.set(self.entity_key(), "message", value);
        self
    }

    pub fn changed_message(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "message")
    }

    pub fn eval_message(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("message") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "message".to_string(), attempted_path: "message".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.message())
                }}

    pub fn is_read(&self) -> bool {
        self.changed_is_read().and_then(|value| value.try_bool()).unwrap_or(self.is_read)
    }

    pub fn update_is_read(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.is_read = value.try_bool().unwrap_or(self.is_read.clone());
        self.root.set(self.entity_key(), "is_read", value);
        self
    }

    pub fn changed_is_read(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "is_read")
    }

    pub fn eval_is_read(&self) -> teaql_core::eval::EvalResult<bool> {
        if !self.is_loaded("is_read") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "is_read".to_string(), attempted_path: "is_read".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.is_read())
                }}

    pub fn sent_at(&self) -> String {
        self.changed_sent_at().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.sent_at.clone())
    }

    pub fn update_sent_at(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.sent_at = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.sent_at.clone());
        self.root.set(self.entity_key(), "sent_at", value);
        self
    }

    pub fn changed_sent_at(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "sent_at")
    }

    pub fn eval_sent_at(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("sent_at") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "sent_at".to_string(), attempted_path: "sent_at".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.sent_at())
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
    pub fn user_id(&self) -> u64 {
        self.changed_user_id().and_then(|value| value.try_u64()).unwrap_or(self.user_id)
    }

    pub fn update_user_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.user_id = value.try_u64().unwrap_or(self.user_id.clone());
        self.root.set(self.entity_key(), "user_id", value);
        self
    }

    pub fn changed_user_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "user_id")
    }

    pub fn eval_user_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("user_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "user_id".to_string(), attempted_path: "user_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.user_id())
                }}

    pub fn notification_type_id(&self) -> u64 {
        self.changed_notification_type_id().and_then(|value| value.try_u64()).unwrap_or(self.notification_type_id)
    }

    pub(crate) fn update_notification_type_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.notification_type_id = value.try_u64().unwrap_or(self.notification_type_id.clone());
        self.root.set(self.entity_key(), "notification_type_id", value);
        self
    }

    pub fn changed_notification_type_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "notification_type_id")
    }

    pub fn eval_notification_type_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("notification_type_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "notification_type_id".to_string(), attempted_path: "notification_type_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.notification_type_id())
                }}
    pub fn update_notification_type_to_email(&mut self) -> &mut Self {
        self.update_notification_type_id(1001_u64)
    }

    pub fn notification_type_is_email(&self) -> bool {
        self.notification_type_id() == 1001_u64
    }
    pub fn update_notification_type_to_sms(&mut self) -> &mut Self {
        self.update_notification_type_id(1002_u64)
    }

    pub fn notification_type_is_sms(&self) -> bool {
        self.notification_type_id() == 1002_u64
    }
    pub fn update_notification_type_to_push(&mut self) -> &mut Self {
        self.update_notification_type_id(1003_u64)
    }

    pub fn notification_type_is_push(&self) -> bool {
        self.notification_type_id() == 1003_u64
    }
    pub fn user(&self) -> Option<&crate::User> {
        self.user.as_ref()
    }

    pub fn eval_user(&self) -> teaql_core::eval::EvalResult<&crate::User> {
        if !self.is_loaded("user") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "user".to_string(), attempted_path: "user".to_string() }
        } else {
            match &self.user {
                Some(v) => teaql_core::eval::EvalResult::Value(v),
                None => teaql_core::eval::EvalResult::Null,
            }
        }
    }

    pub fn notification_type(&self) -> Option<&crate::NotificationType> {
        self.notification_type.as_ref()
    }

    pub fn eval_notification_type(&self) -> teaql_core::eval::EvalResult<&crate::NotificationType> {
        if !self.is_loaded("notification_type") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "notification_type".to_string(), attempted_path: "notification_type".to_string() }
        } else {
            match &self.notification_type {
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

