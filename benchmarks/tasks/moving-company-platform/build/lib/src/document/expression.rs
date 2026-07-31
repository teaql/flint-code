#[derive(Clone)]
pub struct DocumentExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::Document>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> DocumentExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::Document>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::Document> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::Document> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::Document {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_title(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("title", |entity| entity.eval_title());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_file_path(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("file_path", |entity| entity.eval_file_path());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_file_size(self) -> crate::ValueExpression<'a, i64> {
        let next = self.result.and_then("file_size", |entity| entity.eval_file_size());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_upload_date(self) -> crate::ValueExpression<'a, chrono::NaiveDate> {
        let next = self.result.and_then("upload_date", |entity| entity.eval_upload_date());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_create_time(self) -> crate::ValueExpression<'a, chrono::DateTime<chrono::Utc>> {
        let next = self.result.and_then("create_time", |entity| entity.eval_create_time());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_update_time(self) -> crate::ValueExpression<'a, chrono::DateTime<chrono::Utc>> {
        let next = self.result.and_then("update_time", |entity| entity.eval_update_time());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_version(self) -> crate::ValueExpression<'a, i64> {
        let next = self.result.and_then("version", |entity| entity.eval_version());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_document_type_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("document_type_id", |entity| entity.eval_document_type_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_document_type(self) -> crate::DocumentTypeExpression<'a> {
        let next = self.result.and_then("document_type", |entity| entity.eval_document_type());
        crate::DocumentTypeExpression::new(next, self.root_desc.clone())
    }
    pub fn document_type_is_contract(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("document_type_id", |entity| {
            if !entity.is_loaded("document_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "document_type_id".to_string(), attempted_path: "document_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.document_type_is_contract())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn document_type_is_invoice(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("document_type_id", |entity| {
            if !entity.is_loaded("document_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "document_type_id".to_string(), attempted_path: "document_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.document_type_is_invoice())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn document_type_is_receipt(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("document_type_id", |entity| {
            if !entity.is_loaded("document_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "document_type_id".to_string(), attempted_path: "document_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.document_type_is_receipt())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn document_type_is_insurance(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("document_type_id", |entity| {
            if !entity.is_loaded("document_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "document_type_id".to_string(), attempted_path: "document_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.document_type_is_insurance())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct DocumentListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Document>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> DocumentListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Document>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::Document>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::Document>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::Document> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::DocumentExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::DocumentExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::DocumentExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::DocumentExpression::new(next, self.root_desc.clone())
    }
}