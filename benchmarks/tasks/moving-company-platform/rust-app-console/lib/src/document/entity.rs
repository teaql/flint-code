// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/document
use std::collections::BTreeMap;

use teaql_macros::TeaqlEntity;

/// [TEAQL AI WARNING]
/// TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
/// DO NOT GUESS METHOD NAMES!
/// The methods listed below are the ONLY valid ways to interact with this entity.
/// If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
/// Read the method signatures in this file before proceeding.
#[derive(Clone, Debug, PartialEq, TeaqlEntity)]
#[teaql(entity = "Document", table = "document_data", data_service = "sqlite")]
pub struct Document {
#[teaql(id)]
    id: u64,

// @source admin.xml:83
    title: String,

// @source admin.xml:83
    file_path: String,

// @source admin.xml:83
    file_size: i64,

// @source admin.xml:83
    upload_date: chrono::NaiveDate,

// @source admin.xml:83
    create_time: chrono::DateTime<chrono::Utc>,

// @source admin.xml:83
    update_time: chrono::DateTime<chrono::Utc>,
#[teaql(version)]
    version: i64,
// @source admin.xml:83
#[teaql(column = "document_type")]
    document_type_id: u64,
// @source admin.xml:83
#[teaql(relation(target = "DocumentType", local_key = "document_type_id", foreign_key = "id"))]
    document_type: Option<crate::DocumentType>,
    #[teaql(dynamic)]
    dynamic: BTreeMap<String, teaql_core::Value>,
    #[teaql(skip)]
    root: teaql_runtime::EntityRoot,
    #[teaql(skip)]
    pub __load_state: teaql_core::eval::LoadState,
}

impl Document {
    pub fn with_id(id: u64) -> teaql_core::Value {
        teaql_core::Value::U64(id)
    }

    pub(crate) fn runtime_new(root: teaql_runtime::EntityRoot) -> Self {
        Self {
            id: 0_u64,
            title: String::new(),
            file_path: String::new(),
            file_size: 0_i64,
            upload_date: chrono::NaiveDate::from_ymd_opt(1970, 1, 1).unwrap(),
            create_time: chrono::Utc::now(),
            update_time: chrono::Utc::now(),
            version: 0_i64,
            document_type_id: 0_u64,
            document_type: None,
            dynamic: BTreeMap::new(),
            root,
            __load_state: teaql_core::eval::LoadState::FullyLoaded,
        }
    }

    pub fn entity_key(&self) -> teaql_runtime::EntityKey {
        teaql_runtime::EntityKey::new("Document", self.id)
    }

    pub fn attach_root_recursive(&mut self, root: teaql_runtime::EntityRoot) {
        self.root = root.clone();
        if let Some(entity) = &mut self.document_type {
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

    pub fn file_path(&self) -> String {
        self.changed_file_path().and_then(|value| value.try_text().map(|value| value.to_owned())).unwrap_or_else(|| self.file_path.clone())
    }

    pub fn update_file_path(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.file_path = value.try_text().map(|value| value.trim().to_owned()).unwrap_or_else(|| self.file_path.clone());
        self.root.set(self.entity_key(), "file_path", value);
        self
    }

    pub fn changed_file_path(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "file_path")
    }

    pub fn eval_file_path(&self) -> teaql_core::eval::EvalResult<String> {
        if !self.is_loaded("file_path") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "file_path".to_string(), attempted_path: "file_path".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.file_path())
                }}

    pub fn file_size(&self) -> i64 {
        self.changed_file_size().and_then(|value| value.try_i64()).map(|value| value as i64).unwrap_or(self.file_size)
    }

    pub fn update_file_size(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.file_size = value.try_i64().map(|value| value as i64).unwrap_or(self.file_size.clone());
        self.root.set(self.entity_key(), "file_size", value);
        self
    }

    pub fn changed_file_size(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "file_size")
    }

    pub fn eval_file_size(&self) -> teaql_core::eval::EvalResult<i64> {
        if !self.is_loaded("file_size") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "file_size".to_string(), attempted_path: "file_size".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.file_size())
                }}

    pub fn upload_date(&self) -> chrono::NaiveDate {
        self.changed_upload_date().and_then(|value| value.try_date()).unwrap_or(self.upload_date)
    }

    pub fn update_upload_date(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.upload_date = value.try_date().unwrap_or(self.upload_date.clone());
        self.root.set(self.entity_key(), "upload_date", value);
        self
    }

    pub fn changed_upload_date(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "upload_date")
    }

    pub fn eval_upload_date(&self) -> teaql_core::eval::EvalResult<chrono::NaiveDate> {
        if !self.is_loaded("upload_date") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "upload_date".to_string(), attempted_path: "upload_date".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.upload_date())
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
    pub fn document_type_id(&self) -> u64 {
        self.changed_document_type_id().and_then(|value| value.try_u64()).unwrap_or(self.document_type_id)
    }

    pub(crate) fn update_document_type_id(&mut self, value: impl Into<teaql_core::Value>) -> &mut Self {
        let value = value.into();
        self.document_type_id = value.try_u64().unwrap_or(self.document_type_id.clone());
        self.root.set(self.entity_key(), "document_type_id", value);
        self
    }

    pub fn changed_document_type_id(&self) -> Option<teaql_core::Value> {
        self.root.get(&self.entity_key(), "document_type_id")
    }

    pub fn eval_document_type_id(&self) -> teaql_core::eval::EvalResult<u64> {
        if !self.is_loaded("document_type_id") {
                    teaql_core::eval::EvalResult::NotLoaded { failed_node: "document_type_id".to_string(), attempted_path: "document_type_id".to_string() }
                } else {
                    teaql_core::eval::EvalResult::Value(self.document_type_id())
                }}
    pub fn update_document_type_to_contract(&mut self) -> &mut Self {
        self.update_document_type_id(1001_u64)
    }

    pub fn document_type_is_contract(&self) -> bool {
        self.document_type_id() == 1001_u64
    }
    pub fn update_document_type_to_invoice(&mut self) -> &mut Self {
        self.update_document_type_id(1002_u64)
    }

    pub fn document_type_is_invoice(&self) -> bool {
        self.document_type_id() == 1002_u64
    }
    pub fn update_document_type_to_receipt(&mut self) -> &mut Self {
        self.update_document_type_id(1003_u64)
    }

    pub fn document_type_is_receipt(&self) -> bool {
        self.document_type_id() == 1003_u64
    }
    pub fn update_document_type_to_insurance(&mut self) -> &mut Self {
        self.update_document_type_id(1004_u64)
    }

    pub fn document_type_is_insurance(&self) -> bool {
        self.document_type_id() == 1004_u64
    }
    pub fn document_type(&self) -> Option<&crate::DocumentType> {
        self.document_type.as_ref()
    }

    pub fn eval_document_type(&self) -> teaql_core::eval::EvalResult<&crate::DocumentType> {
        if !self.is_loaded("document_type") {
            teaql_core::eval::EvalResult::NotLoaded { failed_node: "document_type".to_string(), attempted_path: "document_type".to_string() }
        } else {
            match &self.document_type {
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

