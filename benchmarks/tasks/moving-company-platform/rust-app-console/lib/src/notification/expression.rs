#[derive(Clone)]
pub struct NotificationExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::Notification>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> NotificationExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::Notification>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::Notification> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::Notification> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::Notification {
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

    pub fn get_message(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("message", |entity| entity.eval_message());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_is_read(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("is_read", |entity| entity.eval_is_read());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_sent_at(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("sent_at", |entity| entity.eval_sent_at());
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

    pub fn get_notification_type_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("notification_type_id", |entity| entity.eval_notification_type_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_user(self) -> crate::UserExpression<'a> {
        let next = self.result.and_then("user", |entity| entity.eval_user());
        crate::UserExpression::new(next, self.root_desc.clone())
    }

    pub fn get_notification_type(self) -> crate::NotificationTypeExpression<'a> {
        let next = self.result.and_then("notification_type", |entity| entity.eval_notification_type());
        crate::NotificationTypeExpression::new(next, self.root_desc.clone())
    }
    pub fn notification_type_is_email(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("notification_type_id", |entity| {
            if !entity.is_loaded("notification_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "notification_type_id".to_string(), attempted_path: "notification_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.notification_type_is_email())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn notification_type_is_sms(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("notification_type_id", |entity| {
            if !entity.is_loaded("notification_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "notification_type_id".to_string(), attempted_path: "notification_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.notification_type_is_sms())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn notification_type_is_push(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("notification_type_id", |entity| {
            if !entity.is_loaded("notification_type_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "notification_type_id".to_string(), attempted_path: "notification_type_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.notification_type_is_push())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct NotificationListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Notification>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> NotificationListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Notification>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::Notification>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::Notification>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::Notification> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::NotificationExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::NotificationExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::NotificationExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::NotificationExpression::new(next, self.root_desc.clone())
    }
}