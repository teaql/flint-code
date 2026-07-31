#[derive(Clone)]
pub struct MagicLinkExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::MagicLink>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> MagicLinkExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::MagicLink>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::MagicLink> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::MagicLink> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::MagicLink {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_token(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("token", |entity| entity.eval_token());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_expires_at(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("expires_at", |entity| entity.eval_expires_at());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_is_used(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("is_used", |entity| entity.eval_is_used());
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
    pub fn get_user_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("user_id", |entity| entity.eval_user_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_user(self) -> crate::UserExpression<'a> {
        let next = self.result.and_then("user", |entity| entity.eval_user());
        crate::UserExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct MagicLinkListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::MagicLink>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> MagicLinkListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::MagicLink>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::MagicLink>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::MagicLink>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::MagicLink> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::MagicLinkExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::MagicLinkExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::MagicLinkExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::MagicLinkExpression::new(next, self.root_desc.clone())
    }
}