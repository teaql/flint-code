#[derive(Clone)]
pub struct ExpenseExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a crate::Expense>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> ExpenseExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a crate::Expense>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a crate::Expense> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a crate::Expense> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a crate::Expense {
        self.resolve().expect("Relation was legitimately null in database!")
    }

    pub fn get_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("id", |entity| entity.eval_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_amount(self) -> crate::ValueExpression<'a, rust_decimal::Decimal> {
        let next = self.result.and_then("amount", |entity| entity.eval_amount());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_expense_date(self) -> crate::ValueExpression<'a, chrono::NaiveDate> {
        let next = self.result.and_then("expense_date", |entity| entity.eval_expense_date());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_description(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("description", |entity| entity.eval_description());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_receipt_number(self) -> crate::ValueExpression<'a, String> {
        let next = self.result.and_then("receipt_number", |entity| entity.eval_receipt_number());
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
    pub fn get_expense_category_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("expense_category_id", |entity| entity.eval_expense_category_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn get_vehicle_id(self) -> crate::ValueExpression<'a, u64> {
        let next = self.result.and_then("vehicle_id", |entity| entity.eval_vehicle_id());
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
    pub fn get_expense_category(self) -> crate::ExpenseCategoryExpression<'a> {
        let next = self.result.and_then("expense_category", |entity| entity.eval_expense_category());
        crate::ExpenseCategoryExpression::new(next, self.root_desc.clone())
    }

    pub fn get_vehicle(self) -> crate::VehicleExpression<'a> {
        let next = self.result.and_then("vehicle", |entity| entity.eval_vehicle());
        crate::VehicleExpression::new(next, self.root_desc.clone())
    }
    pub fn expense_category_is_fuel(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("expense_category_id", |entity| {
            if !entity.is_loaded("expense_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_category_id".to_string(), attempted_path: "expense_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.expense_category_is_fuel())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn expense_category_is_maintenance(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("expense_category_id", |entity| {
            if !entity.is_loaded("expense_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_category_id".to_string(), attempted_path: "expense_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.expense_category_is_maintenance())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn expense_category_is_supplies(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("expense_category_id", |entity| {
            if !entity.is_loaded("expense_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_category_id".to_string(), attempted_path: "expense_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.expense_category_is_supplies())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn expense_category_is_insurance(self) -> crate::ValueExpression<'a, bool> {
        let next = self.result.and_then("expense_category_id", |entity| {
            if !entity.is_loaded("expense_category_id") {
                teaql_core::eval::EvalResult::NotLoaded { failed_node: "expense_category_id".to_string(), attempted_path: "expense_category_id".to_string() }
            } else {
                teaql_core::eval::EvalResult::Value(entity.expense_category_is_insurance())
            }
        });
        crate::ValueExpression::new(next, self.root_desc.clone())
    }
}

#[derive(Clone)]
pub struct ExpenseListExpression<'a> {
    result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Expense>>,
    root_desc: std::sync::Arc<String>,
}

impl<'a> ExpenseListExpression<'a> {
    pub fn new(result: teaql_core::eval::EvalResult<&'a teaql_core::SmartList<crate::Expense>>, root_desc: std::sync::Arc<String>) -> Self {
        Self { result, root_desc }
    }

    fn resolve(&self) -> Option<&'a teaql_core::SmartList<crate::Expense>> {
        match &self.result {
            teaql_core::eval::EvalResult::Value(v) => Some(*v),
            teaql_core::eval::EvalResult::Null => None,
            teaql_core::eval::EvalResult::NotLoaded { failed_node, attempted_path } => {
                crate::trigger_logic_bug_panic(&self.root_desc, &failed_node, &attempted_path)
            }
        }
    }

    pub fn eval(&self) -> Option<&'a teaql_core::SmartList<crate::Expense>> {
        self.resolve()
    }

    pub fn unwrap(&self) -> &'a teaql_core::SmartList<crate::Expense> {
        self.resolve().expect("List relation was legitimately null in database!")
    }

    pub fn size(&self) -> crate::ValueExpression<'a, usize> {
        let next = self.result.clone().and_then("size", |list| teaql_core::eval::EvalResult::Value(list.len()));
        crate::ValueExpression::new(next, self.root_desc.clone())
    }

    pub fn first(&self) -> crate::ExpenseExpression<'a> {
        let next = self.result.clone().and_then("first", |list| {
            if let Some(item) = list.first() {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::ExpenseExpression::new(next, self.root_desc.clone())
    }

    pub fn get(&self, index: usize) -> crate::ExpenseExpression<'a> {
        let next = self.result.clone().and_then("get", |list| {
            if let Some(item) = list.get(index) {
                teaql_core::eval::EvalResult::Value(item)
            } else {
                teaql_core::eval::EvalResult::Null
            }
        });
        crate::ExpenseExpression::new(next, self.root_desc.clone())
    }
}