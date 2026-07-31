use std::marker::PhantomData;

use serde_json::Value as JsonValue;
use teaql_core::{Aggregate, AggregateFunction, EntityDescriptor, Expr, Record, SelectQuery, SmartList};
use teaql_runtime::{DataServiceError, RuntimeError};

use crate::request_support::*;

impl EntityReference for crate::Employee {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(&self)
    }
}

impl EntityReference for &crate::Employee {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(self)
    }
}

// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/employee
#[derive(Debug)]
pub struct EmployeeRequest<R = crate::Employee> {
    query: SelectQuery,
    relation_selections: Vec<RelationSelection>,
    relation_filters: Vec<RelationFilter>,
    child_enhancements: Vec<QuerySelection>,
    query_options: QueryOptions,
    marker: PhantomData<R>,
}

impl<R> Clone for EmployeeRequest<R> {
    fn clone(&self) -> Self {
        Self {
            query: self.query.clone(),
            relation_selections: self.relation_selections.clone(),
            relation_filters: self.relation_filters.clone(),
            child_enhancements: self.child_enhancements.clone(),
            query_options: self.query_options.clone(),
            marker: PhantomData,
        }
    }
}

impl<R> EmployeeRequest<R> {
    pub(crate) fn new() -> Self {
        Self {
            query: SelectQuery::new("Employee")
                .project("id")
                .project("version"),
            relation_selections: Vec::new(),
            relation_filters: Vec::new(),
            child_enhancements: Vec::new(),
            query_options: QueryOptions::default(),
            marker: PhantomData,
        }
    }

    pub fn return_type<T>(self) -> EmployeeRequest<T> {
        EmployeeRequest {
            query: self.query,
            relation_selections: self.relation_selections,
            relation_filters: self.relation_filters,
            child_enhancements: self.child_enhancements,
            query_options: self.query_options,
            marker: PhantomData,
        }
    }

    pub fn query(&self) -> &SelectQuery {
        &self.query
    }

    pub fn relation_selections(&self) -> &[RelationSelection] {
        &self.relation_selections
    }

    pub fn relation_filters(&self) -> &[RelationFilter] {
        &self.relation_filters
    }

    pub fn child_enhancements(&self) -> &[QuerySelection] {
        &self.child_enhancements
    }

    pub fn query_options(&self) -> &QueryOptions {
        &self.query_options
    }

    pub fn into_query(self) -> SelectQuery {
        self.query
    }


    pub fn purpose(self, purpose: impl Into<String>) -> crate::PurposedQuery<Self> {
        crate::PurposedQuery::new(self, purpose)
    }

    pub(crate) async fn _execute_for_list<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let repository = ctx
            .employee_repository()
            .map_err(|err| DataServiceError::Runtime(RuntimeError::Graph(err.to_string())))?;
        let query_options = self.query_options.clone();
        let relation_aggregates = runtime_relation_aggregates(&query_options);
        let query = authorize_query(apply_runtime_metadata(
            self.query,
            &query_options,
            &self.child_enhancements,
        )).map_err(DataServiceError::Runtime)?;
        let mut rows = repository.fetch_enhanced_entities_with_relation_aggregates::<R>(
            &query,
            &relation_aggregates,
        ).await?;
        let facets = execute_facets(ctx, query.as_query(), &query_options)
            .await
            .map_err(DataServiceError::Runtime)?;
        attach_facets(&mut rows, facets);
        Ok(rows)
    }

    pub(crate) async fn _execute_for_stream<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<Vec<teaql_data_service::StreamChunk>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .employee_repository()
            .map_err(|err| DataServiceError::Runtime(RuntimeError::Graph(err.to_string())))?;
        let query_options = self.query_options.clone();
        let query = authorize_query(apply_runtime_metadata(
            self.query,
            &query_options,
            &self.child_enhancements,
        )).map_err(DataServiceError::Runtime)?;
        let chunks = repository.fetch_stream(&query)
            .await?;
        Ok(chunks)
    }

    pub(crate) async fn _execute_for_first<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<Option<R>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let rows = self.limit(1)._execute_for_list(ctx).await?;
        Ok(rows.into_iter().next())
    }

    pub(crate) async fn _execute_for_one<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<Option<R>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        self._execute_for_first(ctx).await
    }


    pub(crate) async fn _execute_for_page<'a, C>(
        self,
        ctx: &'a C,
        offset: u64,
        limit: u64,
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let total_count = self.clone()._execute_for_count(ctx).await?;
        let mut rows = self.page_offset(offset, limit)._execute_for_list(ctx).await?;
        rows.total_count = Some(total_count);
        Ok(rows)
    }

    pub(crate) async fn _execute_for_count<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<u64, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .employee_repository()
            .map_err(|err| DataServiceError::Runtime(RuntimeError::Graph(err.to_string())))?;
        let mut query = self.query;
        query.projection.clear();
        query.expr_projection.clear();
        query.order_by.clear();
        query.slice = None;
        query.relations.clear();
        query = query.count(COUNT_ALIAS);
        let query = authorize_query(query).map_err(DataServiceError::Runtime)?;
        let rows = repository.fetch_all(&query).await?;
        rows.first()
            .and_then(|row| row.get(COUNT_ALIAS))
            .and_then(teaql_core::Value::try_u64)
            .ok_or_else(|| DataServiceError::Runtime(RuntimeError::Graph(format!("count result for Employee is missing or not numeric"))))
    }

    pub(crate) async fn _execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .employee_repository()
            .map_err(|err| DataServiceError::Runtime(RuntimeError::Graph(err.to_string())))?;
        let mut query = self.query.limit(1);
        query.relations.clear();
        let query = authorize_query(query).map_err(DataServiceError::Runtime)?;
        let rows = repository.fetch_all(&query).await?;
        Ok(!rows.is_empty())
    }

    pub(crate) async fn _execute_for_records<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<SmartList<Record>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .employee_repository()
            .map_err(|err| DataServiceError::Runtime(RuntimeError::Graph(err.to_string())))?;
        let query_options = self.query_options.clone();
        let outer_query = self.query.clone();
        let relation_aggregates = runtime_relation_aggregates(&query_options);
        let query = authorize_query(apply_runtime_metadata(
            self.query,
            &query_options,
            &self.child_enhancements,
        )).map_err(DataServiceError::Runtime)?;
        let mut rows = repository.fetch_smart_list_with_relation_aggregates(&query, &relation_aggregates).await?;
        let facets = execute_facets(ctx, &outer_query, &query_options)
            .await
            .map_err(DataServiceError::Runtime)?;
        attach_facets(&mut rows, facets);
        Ok(rows)
    }

    pub(crate) async fn _execute_for_record<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<Option<Record>, TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let records = self.limit(1)._execute_for_records(ctx).await?;
        Ok(records.into_iter().next())
    }

    pub fn search_with_text(mut self, text: impl Into<String>) -> Self {
        self.query = self.query.search_with_text(text);
        self
    }

    pub fn filter(mut self, filter: Expr) -> Self {
        self.query = self.query.filter(filter);
        self
    }

    pub fn and_filter(mut self, filter: Expr) -> Self {
        self.query = self.query.and_filter(filter);
        self
    }

    pub fn or_filter(mut self, filter: Expr) -> Self {
        self.query = self.query.or_filter(filter);
        self
    }

    pub fn append_search_criteria(self, criteria: Expr) -> Self {
        self.and_filter(criteria)
    }

    pub fn filter_property(
        mut self,
        property1: impl AsRef<str>,
        operator: FieldOperator,
        property2: impl AsRef<str>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_column_expr(
            property1.as_ref(),
            operator,
            property2.as_ref(),
        ));
        self
    }

    pub fn with_deleted_rows(mut self) -> Self {
        self.query.filter = remove_default_live_filter(self.query.filter);
        self
    }

    pub fn deleted_rows_only(mut self) -> Self {
        self.query.filter = remove_default_live_filter(self.query.filter);
        self.query = self.query.and_filter(Expr::lte("version", 0_i64));
        self
    }

    pub fn match_types(
        mut self,
        types: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(TYPE_FIELD, types.into_iter().map(Into::into)));
        self
    }


    pub fn with_type_group(mut self) -> Self {
        self.query = self.query.project(TYPE_GROUP_FIELD);
        self
    }

    pub fn matching_any_of(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        let entity = EntityDescriptor::new(selection.query.entity.clone());
        self.query = self.query.and_filter(Expr::in_subquery("id", entity, selection.query.clone(), "id"));
        self
    }

    pub fn match_any_of(self, request: impl Into<QuerySelection>) -> Self {
        self.matching_any_of(request)
    }

    pub fn enhance_child(mut self, request: impl Into<QuerySelection>) -> Self {
        self.child_enhancements.push(request.into());
        self
    }

    pub fn enhance_children_if_needed(self) -> Self {
        let request = self;
        request
    }


    pub fn comment(mut self, comment: impl Into<String>) -> Self {
        self.query_options.comment = Some(comment.into());
        self
    }

    pub fn raw_sql(self, raw_sql: impl Into<String>) -> Self {
        self.unsafe_raw_sql(UnsafeRawSqlSegment::trusted(raw_sql))
    }

    pub fn unsafe_raw_sql(mut self, raw_sql: UnsafeRawSqlSegment) -> Self {
        self.query_options.raw_sql = Some(raw_sql.into_sql());
        self
    }

    pub fn raw_sql_filter(self, raw_sql: impl Into<String>) -> Self {
        self.unsafe_raw_sql_filter(UnsafeRawSqlSegment::trusted(raw_sql))
    }

    pub fn unsafe_raw_sql_filter(mut self, raw_sql: UnsafeRawSqlSegment) -> Self {
        self.query_options.raw_sql_search_criteria.push(raw_sql.into_sql());
        self
    }
    pub fn filter_with_json(self, json_expr: impl Into<String>) -> Self {
        self.merge_dynamic_json_expr(json_expr.into())
    }

    fn merge_dynamic_json_expr(self, json_expr: String) -> Self {
        let json = serde_json::from_str::<JsonValue>(&json_expr)
            .unwrap_or_else(|_| panic!("Input JSON format error: {json_expr}"));
        self.merge_dynamic_json(&json)
    }

    fn merge_dynamic_json(mut self, json: &JsonValue) -> Self {
        let Some(object) = json.as_object() else {
            return self;
        };

        for (field, value) in object {
            if field.starts_with('_') {
                continue;
            }
            self = self.apply_dynamic_json_filter(field, value);
        }

        self = self.apply_dynamic_json_order_by(object.get("_orderBy"));

        if let Some(offset) = dynamic_json_u64_field(object, "_start") {
            self = self.skip(offset);
        }
        if let Some(size) = dynamic_json_u64_field(object, "_size") {
            self = self.limit(size);
        }

        if let Some(page_size) = dynamic_json_u64_field(object, "_pageSize") {
            self = self.limit(page_size);
        }
        if let Some(page_number) = dynamic_json_u64_field(object, "_page") {
            if page_number > 0 {
                let size = dynamic_json_u64_field(object, "_pageSize")
                    .or_else(|| self.query.slice.as_ref().and_then(|slice| slice.limit))
                    .unwrap_or(10);
                let offset = page_number.saturating_sub(1).saturating_mul(size);
                self = self.page_offset(offset, size);
            }
        }

        self
    }

    pub(crate) fn apply_dynamic_json_filter(self, field: &str, value: &JsonValue) -> Self {
        if let Some((head, tail)) = field.split_once('.') {
            self.apply_dynamic_json_chain_filter(head, tail, value)
        } else if let Some(storage_field) = Self::dynamic_json_self_field(field) {
            self.and_filter(dynamic_json_filter_expr(storage_field, value))
        } else {
            self
        }
    }

    fn apply_dynamic_json_order_by(mut self, order_by: Option<&JsonValue>) -> Self {
        match order_by {
            Some(JsonValue::String(field)) => {
                if let Some(storage_field) = Self::dynamic_json_self_field(field) {
                    self.query = self.query.order_desc(storage_field);
                }
            }
            Some(JsonValue::Object(order_by)) => {
                self = self.apply_dynamic_json_single_order_by(order_by);
            }
            Some(JsonValue::Array(order_bys)) => {
                for order_by in order_bys {
                    if let Some(order_by) = order_by.as_object() {
                        self = self.apply_dynamic_json_single_order_by(order_by);
                    }
                }
            }
            _ => {}
        }
        self
    }

    fn apply_dynamic_json_single_order_by(
        mut self,
        order_by: &serde_json::Map<String, JsonValue>,
    ) -> Self {
        let Some(field) = order_by.get("field").and_then(JsonValue::as_str) else {
            return self;
        };
        let Some(storage_field) = Self::dynamic_json_self_field(field) else {
            return self;
        };
        if order_by
            .get("useAsc")
            .and_then(JsonValue::as_bool)
            .unwrap_or(false)
        {
            self.query = self.query.order_asc(storage_field);
        } else {
            self.query = self.query.order_desc(storage_field);
        }
        self
    }

    fn dynamic_json_self_field(field: &str) -> Option<&'static str> {
        match field {
            "id" => Some("id"),
            "first_name" => Some("first_name"),
            "last_name" => Some("last_name"),
            "email" => Some("email"),
            "phone" => Some("phone"),
            "hire_date" => Some("hire_date"),
            "position" => Some("position"),
            "is_active" => Some("is_active"),
            "create_time" => Some("create_time"),
            "update_time" => Some("update_time"),
            "version" => Some("version"),
            _ => None,
        }
    }

    fn apply_dynamic_json_chain_filter(self, head: &str, tail: &str, value: &JsonValue) -> Self {
        let _ = (tail, value);
        match head {
            "job_assignment_list" => {
                self.with_job_assignment_list_matching(
                    crate::Q::job_assignments_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "worked_hours_list" => {
                self.with_worked_hours_list_matching(
                    crate::Q::worked_hourses_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "payroll_calculation_list" => {
                self.with_payroll_calculation_list_matching(
                    crate::Q::payroll_calculations_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "bonus_list" => {
                self.with_bonus_list_matching(
                    crate::Q::bonuses_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "leave_request_list" => {
                self.with_leave_request_list_matching(
                    crate::Q::leave_requests_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            _ => self,
        }
    }

    pub fn create_property_as(
        self,
        property_name: impl Into<String>,
        raw_sql_segment: impl Into<String>,
    ) -> Self {
        self.unsafe_create_property_as(property_name, UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn unsafe_create_property_as(
        mut self,
        property_name: impl Into<String>,
        raw_sql_segment: UnsafeRawSqlSegment,
    ) -> Self {
        self.query_options
            .dynamic_properties
            .push(RawDynamicProperty::new(property_name, raw_sql_segment));
        self
    }

    pub fn limit(mut self, limit: u64) -> Self {
        self.query = self.query.limit(limit);
        self
    }

    pub fn skip(mut self, offset: u64) -> Self {
        self.query = self.query.offset(offset);
        self
    }

    pub fn offset_only(self, offset: u64) -> Self {
        self.skip(offset)
    }

    pub fn offset(self, offset: u64, size: u64) -> Self {
        self.page_offset(offset, size)
    }

    pub fn page_offset(mut self, offset: u64, limit: u64) -> Self {
        self.query = self.query.page(offset, limit);
        self
    }

    pub fn top(self, top_n: u64) -> Self {
        self.limit(top_n)
    }

    pub fn offset_size(self, offset: u64, size: u64) -> Self {
        self.offset(offset, size)
    }

    pub fn unlimited(mut self) -> Self {
        self.query.slice = None;
        self
    }

    pub fn page_number(self, page_number: u64, page_size: u64) -> Self {
        let offset = page_number.saturating_sub(1).saturating_mul(page_size);
        self.page_offset(offset, page_size)
    }

    pub fn page_number_default(self, page_number: u64) -> Self {
        self.page_number(page_number, 10)
    }

    pub fn page(self, page_number: u64, page_size: u64) -> Self {
        self.page_number(page_number, page_size)
    }

    pub fn page_default(self, page_number: u64) -> Self {
        self.page_number_default(page_number)
    }

    pub fn select_self(mut self) -> Self {
        self.query = self.query.project("id");
        self.query = self.query.project("first_name");
        self.query = self.query.project("last_name");
        self.query = self.query.project("email");
        self.query = self.query.project("phone");
        self.query = self.query.project("hire_date");
        self.query = self.query.project("position");
        self.query = self.query.project("is_active");
        self.query = self.query.project("create_time");
        self.query = self.query.project("update_time");
        self.query = self.query.project("version");
        self
    }

    pub fn select_self_fields(self) -> Self {
        self.select_self()
    }

    pub fn select_self_without_parent(self) -> Self {
        self.select_self_fields()
    }

    pub fn select_all(self) -> Self {
        self.select_self()
    }

    pub fn select_children(self) -> Self {
        let mut request = self.select_all();
        request = request.select_job_assignment_list();
        request = request.select_worked_hours_list();
        request = request.select_payroll_calculation_list();
        request = request.select_bonus_list();
        request = request.select_leave_request_list();
        request
    }

    pub fn select_any(self) -> Self {
        self.select_children()
    }

    pub fn group_by(mut self, field: impl Into<String>) -> Self {
        self.query = self.query.group_by(field);
        self
    }

    pub fn aggregate_count(mut self, alias: impl Into<String>) -> Self {
        self.query = self.query.count(alias);
        self
    }

    pub fn aggregate_count_field(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.count_field(field, alias);
        self
    }

    pub fn aggregate_with_function(
        mut self,
        field: impl Into<String>,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.query = self.query.aggregate(Aggregate::new(function, field, alias));
        self
    }

    pub fn aggregate_sum(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.sum(field, alias);
        self
    }

    pub fn aggregate_avg(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.avg(field, alias);
        self
    }

    pub fn aggregate_min(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.min(field, alias);
        self
    }

    pub fn aggregate_max(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.max(field, alias);
        self
    }

    pub fn aggregate_stddev(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.stddev(field, alias);
        self
    }

    pub fn aggregate_stddev_pop(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.stddev_pop(field, alias);
        self
    }

    pub fn aggregate_var_samp(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.var_samp(field, alias);
        self
    }

    pub fn aggregate_var_pop(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.var_pop(field, alias);
        self
    }

    pub fn aggregate_bit_and(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.bit_and(field, alias);
        self
    }

    pub fn aggregate_bit_or(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.bit_or(field, alias);
        self
    }

    pub fn aggregate_bit_xor(mut self, field: impl Into<String>, alias: impl Into<String>) -> Self {
        self.query = self.query.bit_xor(field, alias);
        self
    }

    pub fn enable_aggregation_cache(mut self) -> Self {
        self.query = self.query.enable_aggregation_cache();
        self
    }

    pub fn enable_aggregation_cache_for(mut self, cache_expired_millis: u64) -> Self {
        self.query = self.query.enable_aggregation_cache_for(cache_expired_millis);
        self
    }

    pub fn propagate_aggregation_cache(mut self, cache_expired_millis: u64) -> Self {
        self.query = self.query.propagate_aggregation_cache(cache_expired_millis);
        self
    }

    pub fn group_by_id(self) -> Self {
        self.group_by("id")
    }

    pub fn group_by_id_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("id");
        request.query = request
            .query
            .project_expr(alias, Expr::column("id"));
        request
    }

    pub fn group_by_id_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("id")
            .aggregate_with_function("id", alias, function)
    }

    pub fn count_id(self) -> Self {
        self.count_id_as("id_count")
    }

    pub fn count_id_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("id", alias)
    }

    pub fn sum_id(self) -> Self {
        self.sum_id_as("sum_id")
    }

    pub fn sum_id_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("id", alias)
    }

    pub fn avg_id(self) -> Self {
        self.avg_id_as("avg_id")
    }

    pub fn avg_id_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("id", alias)
    }

    pub fn min_id(self) -> Self {
        self.min_id_as("min_id")
    }

    pub fn min_id_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("id", alias)
    }

    pub fn max_id(self) -> Self {
        self.max_id_as("max_id")
    }

    pub fn max_id_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("id", alias)
    }


    pub fn with_id(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "id",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_id_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "id",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_id_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("id", value));
        self
    }



    pub fn with_id_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("id", value));
        self
    }

    pub fn with_id_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "id",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_id_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "id",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn order_by_id_asc(mut self) -> Self {
        self.query = self.query.order_asc("id");
        self
    }

    pub fn order_by_id_desc(mut self) -> Self {
        self.query = self.query.order_desc("id");
        self
    }

    pub fn order_by_id_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("id");
        self
    }

    pub fn order_by_id_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("id");
        self
    }


    pub fn select_first_name(mut self) -> Self {
        self.query = self.query.project("first_name");
        self
    }

    pub fn project_first_name(self) -> Self {
        self.select_first_name()
    }

    pub fn select_first_name_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_first_name_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_first_name_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("first_name", raw_sql_segment));
        self
    }

    pub fn group_by_first_name(self) -> Self {
        self.group_by("first_name")
    }

    pub fn group_by_first_name_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("first_name");
        request.query = request
            .query
            .project_expr(alias, Expr::column("first_name"));
        request
    }

    pub fn group_by_first_name_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("first_name")
            .aggregate_with_function("first_name", alias, function)
    }

    pub fn count_first_name(self) -> Self {
        self.count_first_name_as("first_name_count")
    }

    pub fn count_first_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("first_name", alias)
    }

    pub fn sum_first_name(self) -> Self {
        self.sum_first_name_as("sum_first_name")
    }

    pub fn sum_first_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("first_name", alias)
    }

    pub fn avg_first_name(self) -> Self {
        self.avg_first_name_as("avg_first_name")
    }

    pub fn avg_first_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("first_name", alias)
    }

    pub fn min_first_name(self) -> Self {
        self.min_first_name_as("min_first_name")
    }

    pub fn min_first_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("first_name", alias)
    }

    pub fn max_first_name(self) -> Self {
        self.max_first_name_as("max_first_name")
    }

    pub fn max_first_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("first_name", alias)
    }

    pub fn unselect_first_name(mut self) -> Self {
        self.query.projection.retain(|field| field != "first_name");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "first_name");
        self
    }


    pub fn with_first_name(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "first_name",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_first_name_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "first_name",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_first_name_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("first_name", value));
        self
    }



    pub fn with_first_name_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("first_name", value));
        self
    }

    pub fn with_first_name_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("first_name", value));
        self
    }

    pub fn with_first_name_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("first_name", value));
        self
    }

    pub fn with_first_name_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("first_name", value));
        self
    }

    pub fn with_first_name_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("first_name", value));
        self
    }

    pub fn with_first_name_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("first_name", lower, upper));
        self
    }

    pub fn with_first_name_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "first_name",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_first_name_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "first_name",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_first_name_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "first_name",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_first_name_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::contain("first_name", value));
        self
    }

    pub fn with_first_name_not_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_contain("first_name", value));
        self
    }

    pub fn with_first_name_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::begin_with("first_name", value));
        self
    }

    pub fn with_first_name_not_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_begin_with("first_name", value));
        self
    }

    pub fn with_first_name_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::end_with("first_name", value));
        self
    }

    pub fn with_first_name_not_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_end_with("first_name", value));
        self
    }

    pub fn with_first_name_sounding_like(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::sound_like("first_name", value));
        self
    }
    pub fn with_first_name_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("first_name", value));
        self
    }

    pub fn with_first_name_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("first_name", value));
        self
    }

    pub fn with_first_name_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("first_name"));
        self
    }



    pub fn with_first_name_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("first_name"));
        self
    }


    pub fn order_by_first_name_asc(mut self) -> Self {
        self.query = self.query.order_asc("first_name");
        self
    }

    pub fn order_by_first_name_desc(mut self) -> Self {
        self.query = self.query.order_desc("first_name");
        self
    }

    pub fn order_by_first_name_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("first_name");
        self
    }

    pub fn order_by_first_name_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("first_name");
        self
    }


    pub fn select_last_name(mut self) -> Self {
        self.query = self.query.project("last_name");
        self
    }

    pub fn project_last_name(self) -> Self {
        self.select_last_name()
    }

    pub fn select_last_name_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_last_name_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_last_name_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("last_name", raw_sql_segment));
        self
    }

    pub fn group_by_last_name(self) -> Self {
        self.group_by("last_name")
    }

    pub fn group_by_last_name_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("last_name");
        request.query = request
            .query
            .project_expr(alias, Expr::column("last_name"));
        request
    }

    pub fn group_by_last_name_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("last_name")
            .aggregate_with_function("last_name", alias, function)
    }

    pub fn count_last_name(self) -> Self {
        self.count_last_name_as("last_name_count")
    }

    pub fn count_last_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("last_name", alias)
    }

    pub fn sum_last_name(self) -> Self {
        self.sum_last_name_as("sum_last_name")
    }

    pub fn sum_last_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("last_name", alias)
    }

    pub fn avg_last_name(self) -> Self {
        self.avg_last_name_as("avg_last_name")
    }

    pub fn avg_last_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("last_name", alias)
    }

    pub fn min_last_name(self) -> Self {
        self.min_last_name_as("min_last_name")
    }

    pub fn min_last_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("last_name", alias)
    }

    pub fn max_last_name(self) -> Self {
        self.max_last_name_as("max_last_name")
    }

    pub fn max_last_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("last_name", alias)
    }

    pub fn unselect_last_name(mut self) -> Self {
        self.query.projection.retain(|field| field != "last_name");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "last_name");
        self
    }


    pub fn with_last_name(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "last_name",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_last_name_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "last_name",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_last_name_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("last_name", value));
        self
    }



    pub fn with_last_name_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("last_name", value));
        self
    }

    pub fn with_last_name_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("last_name", value));
        self
    }

    pub fn with_last_name_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("last_name", value));
        self
    }

    pub fn with_last_name_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("last_name", value));
        self
    }

    pub fn with_last_name_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("last_name", value));
        self
    }

    pub fn with_last_name_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("last_name", lower, upper));
        self
    }

    pub fn with_last_name_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "last_name",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_last_name_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "last_name",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_last_name_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "last_name",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_last_name_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::contain("last_name", value));
        self
    }

    pub fn with_last_name_not_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_contain("last_name", value));
        self
    }

    pub fn with_last_name_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::begin_with("last_name", value));
        self
    }

    pub fn with_last_name_not_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_begin_with("last_name", value));
        self
    }

    pub fn with_last_name_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::end_with("last_name", value));
        self
    }

    pub fn with_last_name_not_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_end_with("last_name", value));
        self
    }

    pub fn with_last_name_sounding_like(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::sound_like("last_name", value));
        self
    }
    pub fn with_last_name_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("last_name", value));
        self
    }

    pub fn with_last_name_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("last_name", value));
        self
    }

    pub fn with_last_name_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("last_name"));
        self
    }



    pub fn with_last_name_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("last_name"));
        self
    }


    pub fn order_by_last_name_asc(mut self) -> Self {
        self.query = self.query.order_asc("last_name");
        self
    }

    pub fn order_by_last_name_desc(mut self) -> Self {
        self.query = self.query.order_desc("last_name");
        self
    }

    pub fn order_by_last_name_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("last_name");
        self
    }

    pub fn order_by_last_name_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("last_name");
        self
    }


    pub fn select_email(mut self) -> Self {
        self.query = self.query.project("email");
        self
    }

    pub fn project_email(self) -> Self {
        self.select_email()
    }

    pub fn select_email_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_email_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_email_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("email", raw_sql_segment));
        self
    }

    pub fn group_by_email(self) -> Self {
        self.group_by("email")
    }

    pub fn group_by_email_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("email");
        request.query = request
            .query
            .project_expr(alias, Expr::column("email"));
        request
    }

    pub fn group_by_email_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("email")
            .aggregate_with_function("email", alias, function)
    }

    pub fn count_email(self) -> Self {
        self.count_email_as("email_count")
    }

    pub fn count_email_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("email", alias)
    }

    pub fn sum_email(self) -> Self {
        self.sum_email_as("sum_email")
    }

    pub fn sum_email_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("email", alias)
    }

    pub fn avg_email(self) -> Self {
        self.avg_email_as("avg_email")
    }

    pub fn avg_email_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("email", alias)
    }

    pub fn min_email(self) -> Self {
        self.min_email_as("min_email")
    }

    pub fn min_email_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("email", alias)
    }

    pub fn max_email(self) -> Self {
        self.max_email_as("max_email")
    }

    pub fn max_email_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("email", alias)
    }

    pub fn unselect_email(mut self) -> Self {
        self.query.projection.retain(|field| field != "email");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "email");
        self
    }


    pub fn with_email(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "email",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_email_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "email",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_email_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("email", value));
        self
    }



    pub fn with_email_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("email", value));
        self
    }

    pub fn with_email_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("email", value));
        self
    }

    pub fn with_email_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("email", value));
        self
    }

    pub fn with_email_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("email", value));
        self
    }

    pub fn with_email_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("email", value));
        self
    }

    pub fn with_email_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("email", lower, upper));
        self
    }

    pub fn with_email_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "email",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_email_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "email",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_email_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "email",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_email_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::contain("email", value));
        self
    }

    pub fn with_email_not_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_contain("email", value));
        self
    }

    pub fn with_email_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::begin_with("email", value));
        self
    }

    pub fn with_email_not_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_begin_with("email", value));
        self
    }

    pub fn with_email_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::end_with("email", value));
        self
    }

    pub fn with_email_not_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_end_with("email", value));
        self
    }

    pub fn with_email_sounding_like(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::sound_like("email", value));
        self
    }
    pub fn with_email_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("email", value));
        self
    }

    pub fn with_email_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("email", value));
        self
    }

    pub fn with_email_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("email"));
        self
    }



    pub fn with_email_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("email"));
        self
    }


    pub fn order_by_email_asc(mut self) -> Self {
        self.query = self.query.order_asc("email");
        self
    }

    pub fn order_by_email_desc(mut self) -> Self {
        self.query = self.query.order_desc("email");
        self
    }

    pub fn order_by_email_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("email");
        self
    }

    pub fn order_by_email_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("email");
        self
    }


    pub fn select_phone(mut self) -> Self {
        self.query = self.query.project("phone");
        self
    }

    pub fn project_phone(self) -> Self {
        self.select_phone()
    }

    pub fn select_phone_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_phone_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_phone_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("phone", raw_sql_segment));
        self
    }

    pub fn group_by_phone(self) -> Self {
        self.group_by("phone")
    }

    pub fn group_by_phone_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("phone");
        request.query = request
            .query
            .project_expr(alias, Expr::column("phone"));
        request
    }

    pub fn group_by_phone_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("phone")
            .aggregate_with_function("phone", alias, function)
    }

    pub fn count_phone(self) -> Self {
        self.count_phone_as("phone_count")
    }

    pub fn count_phone_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("phone", alias)
    }

    pub fn sum_phone(self) -> Self {
        self.sum_phone_as("sum_phone")
    }

    pub fn sum_phone_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("phone", alias)
    }

    pub fn avg_phone(self) -> Self {
        self.avg_phone_as("avg_phone")
    }

    pub fn avg_phone_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("phone", alias)
    }

    pub fn min_phone(self) -> Self {
        self.min_phone_as("min_phone")
    }

    pub fn min_phone_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("phone", alias)
    }

    pub fn max_phone(self) -> Self {
        self.max_phone_as("max_phone")
    }

    pub fn max_phone_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("phone", alias)
    }

    pub fn unselect_phone(mut self) -> Self {
        self.query.projection.retain(|field| field != "phone");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "phone");
        self
    }


    pub fn with_phone(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "phone",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_phone_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "phone",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_phone_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("phone", value));
        self
    }



    pub fn with_phone_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("phone", value));
        self
    }

    pub fn with_phone_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("phone", value));
        self
    }

    pub fn with_phone_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("phone", value));
        self
    }

    pub fn with_phone_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("phone", value));
        self
    }

    pub fn with_phone_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("phone", value));
        self
    }

    pub fn with_phone_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("phone", lower, upper));
        self
    }

    pub fn with_phone_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "phone",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_phone_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "phone",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_phone_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "phone",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_phone_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::contain("phone", value));
        self
    }

    pub fn with_phone_not_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_contain("phone", value));
        self
    }

    pub fn with_phone_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::begin_with("phone", value));
        self
    }

    pub fn with_phone_not_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_begin_with("phone", value));
        self
    }

    pub fn with_phone_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::end_with("phone", value));
        self
    }

    pub fn with_phone_not_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_end_with("phone", value));
        self
    }

    pub fn with_phone_sounding_like(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::sound_like("phone", value));
        self
    }
    pub fn with_phone_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("phone", value));
        self
    }

    pub fn with_phone_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("phone", value));
        self
    }

    pub fn with_phone_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("phone"));
        self
    }



    pub fn with_phone_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("phone"));
        self
    }


    pub fn order_by_phone_asc(mut self) -> Self {
        self.query = self.query.order_asc("phone");
        self
    }

    pub fn order_by_phone_desc(mut self) -> Self {
        self.query = self.query.order_desc("phone");
        self
    }

    pub fn order_by_phone_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("phone");
        self
    }

    pub fn order_by_phone_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("phone");
        self
    }


    pub fn select_hire_date(mut self) -> Self {
        self.query = self.query.project("hire_date");
        self
    }

    pub fn project_hire_date(self) -> Self {
        self.select_hire_date()
    }

    pub fn select_hire_date_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_hire_date_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_hire_date_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("hire_date", raw_sql_segment));
        self
    }

    pub fn group_by_hire_date(self) -> Self {
        self.group_by("hire_date")
    }

    pub fn group_by_hire_date_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("hire_date");
        request.query = request
            .query
            .project_expr(alias, Expr::column("hire_date"));
        request
    }

    pub fn group_by_hire_date_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("hire_date")
            .aggregate_with_function("hire_date", alias, function)
    }

    pub fn count_hire_date(self) -> Self {
        self.count_hire_date_as("hire_date_count")
    }

    pub fn count_hire_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("hire_date", alias)
    }

    pub fn sum_hire_date(self) -> Self {
        self.sum_hire_date_as("sum_hire_date")
    }

    pub fn sum_hire_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("hire_date", alias)
    }

    pub fn avg_hire_date(self) -> Self {
        self.avg_hire_date_as("avg_hire_date")
    }

    pub fn avg_hire_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("hire_date", alias)
    }

    pub fn min_hire_date(self) -> Self {
        self.min_hire_date_as("min_hire_date")
    }

    pub fn min_hire_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("hire_date", alias)
    }

    pub fn max_hire_date(self) -> Self {
        self.max_hire_date_as("max_hire_date")
    }

    pub fn max_hire_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("hire_date", alias)
    }

    pub fn unselect_hire_date(mut self) -> Self {
        self.query.projection.retain(|field| field != "hire_date");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "hire_date");
        self
    }


    pub fn with_hire_date(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "hire_date",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_hire_date_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "hire_date",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_hire_date_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("hire_date", value));
        self
    }



    pub fn with_hire_date_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("hire_date", value));
        self
    }

    pub fn with_hire_date_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("hire_date", value));
        self
    }

    pub fn with_hire_date_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("hire_date", value));
        self
    }

    pub fn with_hire_date_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("hire_date", value));
        self
    }

    pub fn with_hire_date_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("hire_date", value));
        self
    }

    pub fn with_hire_date_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("hire_date", lower, upper));
        self
    }

    pub fn with_hire_date_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "hire_date",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_hire_date_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "hire_date",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_hire_date_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "hire_date",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_hire_date_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("hire_date", value));
        self
    }

    pub fn with_hire_date_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("hire_date", value));
        self
    }

    pub fn with_hire_date_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("hire_date"));
        self
    }



    pub fn with_hire_date_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("hire_date"));
        self
    }


    pub fn order_by_hire_date_asc(mut self) -> Self {
        self.query = self.query.order_asc("hire_date");
        self
    }

    pub fn order_by_hire_date_desc(mut self) -> Self {
        self.query = self.query.order_desc("hire_date");
        self
    }

    pub fn order_by_hire_date_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("hire_date");
        self
    }

    pub fn order_by_hire_date_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("hire_date");
        self
    }


    pub fn select_position(mut self) -> Self {
        self.query = self.query.project("position");
        self
    }

    pub fn project_position(self) -> Self {
        self.select_position()
    }

    pub fn select_position_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_position_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_position_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("position", raw_sql_segment));
        self
    }

    pub fn group_by_position(self) -> Self {
        self.group_by("position")
    }

    pub fn group_by_position_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("position");
        request.query = request
            .query
            .project_expr(alias, Expr::column("position"));
        request
    }

    pub fn group_by_position_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("position")
            .aggregate_with_function("position", alias, function)
    }

    pub fn count_position(self) -> Self {
        self.count_position_as("position_count")
    }

    pub fn count_position_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("position", alias)
    }

    pub fn sum_position(self) -> Self {
        self.sum_position_as("sum_position")
    }

    pub fn sum_position_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("position", alias)
    }

    pub fn avg_position(self) -> Self {
        self.avg_position_as("avg_position")
    }

    pub fn avg_position_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("position", alias)
    }

    pub fn min_position(self) -> Self {
        self.min_position_as("min_position")
    }

    pub fn min_position_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("position", alias)
    }

    pub fn max_position(self) -> Self {
        self.max_position_as("max_position")
    }

    pub fn max_position_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("position", alias)
    }

    pub fn unselect_position(mut self) -> Self {
        self.query.projection.retain(|field| field != "position");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "position");
        self
    }


    pub fn with_position(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "position",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_position_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "position",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_position_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("position", value));
        self
    }



    pub fn with_position_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("position", value));
        self
    }

    pub fn with_position_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("position", value));
        self
    }

    pub fn with_position_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("position", value));
        self
    }

    pub fn with_position_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("position", value));
        self
    }

    pub fn with_position_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("position", value));
        self
    }

    pub fn with_position_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("position", lower, upper));
        self
    }

    pub fn with_position_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "position",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_position_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "position",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_position_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "position",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_position_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::contain("position", value));
        self
    }

    pub fn with_position_not_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_contain("position", value));
        self
    }

    pub fn with_position_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::begin_with("position", value));
        self
    }

    pub fn with_position_not_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_begin_with("position", value));
        self
    }

    pub fn with_position_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::end_with("position", value));
        self
    }

    pub fn with_position_not_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_end_with("position", value));
        self
    }

    pub fn with_position_sounding_like(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::sound_like("position", value));
        self
    }
    pub fn with_position_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("position", value));
        self
    }

    pub fn with_position_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("position", value));
        self
    }

    pub fn with_position_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("position"));
        self
    }



    pub fn with_position_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("position"));
        self
    }


    pub fn order_by_position_asc(mut self) -> Self {
        self.query = self.query.order_asc("position");
        self
    }

    pub fn order_by_position_desc(mut self) -> Self {
        self.query = self.query.order_desc("position");
        self
    }

    pub fn order_by_position_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("position");
        self
    }

    pub fn order_by_position_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("position");
        self
    }


    pub fn select_is_active(mut self) -> Self {
        self.query = self.query.project("is_active");
        self
    }

    pub fn project_is_active(self) -> Self {
        self.select_is_active()
    }

    pub fn select_is_active_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_is_active_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_is_active_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("is_active", raw_sql_segment));
        self
    }

    pub fn group_by_is_active(self) -> Self {
        self.group_by("is_active")
    }

    pub fn group_by_is_active_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("is_active");
        request.query = request
            .query
            .project_expr(alias, Expr::column("is_active"));
        request
    }

    pub fn group_by_is_active_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("is_active")
            .aggregate_with_function("is_active", alias, function)
    }

    pub fn count_is_active(self) -> Self {
        self.count_is_active_as("is_active_count")
    }

    pub fn count_is_active_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("is_active", alias)
    }

    pub fn sum_is_active(self) -> Self {
        self.sum_is_active_as("sum_is_active")
    }

    pub fn sum_is_active_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("is_active", alias)
    }

    pub fn avg_is_active(self) -> Self {
        self.avg_is_active_as("avg_is_active")
    }

    pub fn avg_is_active_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("is_active", alias)
    }

    pub fn min_is_active(self) -> Self {
        self.min_is_active_as("min_is_active")
    }

    pub fn min_is_active_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("is_active", alias)
    }

    pub fn max_is_active(self) -> Self {
        self.max_is_active_as("max_is_active")
    }

    pub fn max_is_active_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("is_active", alias)
    }

    pub fn unselect_is_active(mut self) -> Self {
        self.query.projection.retain(|field| field != "is_active");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "is_active");
        self
    }


    pub fn with_is_active(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "is_active",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_is_active_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "is_active",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_is_active_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("is_active", value));
        self
    }



    pub fn with_is_active_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("is_active", value));
        self
    }

    pub fn with_is_active_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("is_active", value));
        self
    }

    pub fn with_is_active_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("is_active", value));
        self
    }

    pub fn with_is_active_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("is_active", value));
        self
    }

    pub fn with_is_active_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("is_active", value));
        self
    }

    pub fn with_is_active_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("is_active", lower, upper));
        self
    }

    pub fn with_is_active_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "is_active",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_is_active_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "is_active",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_is_active_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "is_active",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_is_active_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("is_active", value));
        self
    }

    pub fn with_is_active_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("is_active", value));
        self
    }

    pub fn with_is_active_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("is_active"));
        self
    }



    pub fn with_is_active_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("is_active"));
        self
    }


    pub fn order_by_is_active_asc(mut self) -> Self {
        self.query = self.query.order_asc("is_active");
        self
    }

    pub fn order_by_is_active_desc(mut self) -> Self {
        self.query = self.query.order_desc("is_active");
        self
    }

    pub fn order_by_is_active_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("is_active");
        self
    }

    pub fn order_by_is_active_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("is_active");
        self
    }


    pub fn select_create_time(mut self) -> Self {
        self.query = self.query.project("create_time");
        self
    }

    pub fn project_create_time(self) -> Self {
        self.select_create_time()
    }

    pub fn select_create_time_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_create_time_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_create_time_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("create_time", raw_sql_segment));
        self
    }

    pub fn group_by_create_time(self) -> Self {
        self.group_by("create_time")
    }

    pub fn group_by_create_time_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("create_time");
        request.query = request
            .query
            .project_expr(alias, Expr::column("create_time"));
        request
    }

    pub fn group_by_create_time_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("create_time")
            .aggregate_with_function("create_time", alias, function)
    }

    pub fn count_create_time(self) -> Self {
        self.count_create_time_as("create_time_count")
    }

    pub fn count_create_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("create_time", alias)
    }

    pub fn sum_create_time(self) -> Self {
        self.sum_create_time_as("sum_create_time")
    }

    pub fn sum_create_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("create_time", alias)
    }

    pub fn avg_create_time(self) -> Self {
        self.avg_create_time_as("avg_create_time")
    }

    pub fn avg_create_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("create_time", alias)
    }

    pub fn min_create_time(self) -> Self {
        self.min_create_time_as("min_create_time")
    }

    pub fn min_create_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("create_time", alias)
    }

    pub fn max_create_time(self) -> Self {
        self.max_create_time_as("max_create_time")
    }

    pub fn max_create_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("create_time", alias)
    }

    pub fn unselect_create_time(mut self) -> Self {
        self.query.projection.retain(|field| field != "create_time");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "create_time");
        self
    }


    pub fn with_create_time(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "create_time",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_create_time_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "create_time",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_create_time_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("create_time", value));
        self
    }



    pub fn with_create_time_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("create_time", value));
        self
    }

    pub fn with_create_time_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("create_time", value));
        self
    }

    pub fn with_create_time_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("create_time", value));
        self
    }

    pub fn with_create_time_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("create_time", value));
        self
    }

    pub fn with_create_time_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("create_time", value));
        self
    }

    pub fn with_create_time_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("create_time", lower, upper));
        self
    }

    pub fn with_create_time_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "create_time",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_create_time_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "create_time",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_create_time_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "create_time",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_create_time_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("create_time", value));
        self
    }

    pub fn with_create_time_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("create_time", value));
        self
    }

    pub fn with_create_time_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("create_time"));
        self
    }



    pub fn with_create_time_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("create_time"));
        self
    }


    pub fn order_by_create_time_asc(mut self) -> Self {
        self.query = self.query.order_asc("create_time");
        self
    }

    pub fn order_by_create_time_desc(mut self) -> Self {
        self.query = self.query.order_desc("create_time");
        self
    }

    pub fn order_by_create_time_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("create_time");
        self
    }

    pub fn order_by_create_time_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("create_time");
        self
    }


    pub fn select_update_time(mut self) -> Self {
        self.query = self.query.project("update_time");
        self
    }

    pub fn project_update_time(self) -> Self {
        self.select_update_time()
    }

    pub fn select_update_time_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_update_time_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_update_time_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("update_time", raw_sql_segment));
        self
    }

    pub fn group_by_update_time(self) -> Self {
        self.group_by("update_time")
    }

    pub fn group_by_update_time_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("update_time");
        request.query = request
            .query
            .project_expr(alias, Expr::column("update_time"));
        request
    }

    pub fn group_by_update_time_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("update_time")
            .aggregate_with_function("update_time", alias, function)
    }

    pub fn count_update_time(self) -> Self {
        self.count_update_time_as("update_time_count")
    }

    pub fn count_update_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("update_time", alias)
    }

    pub fn sum_update_time(self) -> Self {
        self.sum_update_time_as("sum_update_time")
    }

    pub fn sum_update_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("update_time", alias)
    }

    pub fn avg_update_time(self) -> Self {
        self.avg_update_time_as("avg_update_time")
    }

    pub fn avg_update_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("update_time", alias)
    }

    pub fn min_update_time(self) -> Self {
        self.min_update_time_as("min_update_time")
    }

    pub fn min_update_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("update_time", alias)
    }

    pub fn max_update_time(self) -> Self {
        self.max_update_time_as("max_update_time")
    }

    pub fn max_update_time_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("update_time", alias)
    }

    pub fn unselect_update_time(mut self) -> Self {
        self.query.projection.retain(|field| field != "update_time");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "update_time");
        self
    }


    pub fn with_update_time(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "update_time",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_update_time_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "update_time",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_update_time_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("update_time", value));
        self
    }



    pub fn with_update_time_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("update_time", value));
        self
    }

    pub fn with_update_time_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("update_time", value));
        self
    }

    pub fn with_update_time_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("update_time", value));
        self
    }

    pub fn with_update_time_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("update_time", value));
        self
    }

    pub fn with_update_time_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("update_time", value));
        self
    }

    pub fn with_update_time_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("update_time", lower, upper));
        self
    }

    pub fn with_update_time_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "update_time",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_update_time_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "update_time",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_update_time_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "update_time",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_update_time_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("update_time", value));
        self
    }

    pub fn with_update_time_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("update_time", value));
        self
    }

    pub fn with_update_time_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("update_time"));
        self
    }



    pub fn with_update_time_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("update_time"));
        self
    }


    pub fn order_by_update_time_asc(mut self) -> Self {
        self.query = self.query.order_asc("update_time");
        self
    }

    pub fn order_by_update_time_desc(mut self) -> Self {
        self.query = self.query.order_desc("update_time");
        self
    }

    pub fn order_by_update_time_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("update_time");
        self
    }

    pub fn order_by_update_time_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("update_time");
        self
    }

    pub fn group_by_version(self) -> Self {
        self.group_by("version")
    }

    pub fn group_by_version_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("version");
        request.query = request
            .query
            .project_expr(alias, Expr::column("version"));
        request
    }

    pub fn group_by_version_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("version")
            .aggregate_with_function("version", alias, function)
    }

    pub fn count_version(self) -> Self {
        self.count_version_as("version_count")
    }

    pub fn count_version_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("version", alias)
    }

    pub fn sum_version(self) -> Self {
        self.sum_version_as("sum_version")
    }

    pub fn sum_version_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("version", alias)
    }

    pub fn avg_version(self) -> Self {
        self.avg_version_as("avg_version")
    }

    pub fn avg_version_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("version", alias)
    }

    pub fn min_version(self) -> Self {
        self.min_version_as("min_version")
    }

    pub fn min_version_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("version", alias)
    }

    pub fn max_version(self) -> Self {
        self.max_version_as("max_version")
    }

    pub fn max_version_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("version", alias)
    }

    pub fn order_by_version_asc(mut self) -> Self {
        self.query = self.query.order_asc("version");
        self
    }

    pub fn order_by_version_desc(mut self) -> Self {
        self.query = self.query.order_desc("version");
        self
    }

    pub fn order_by_version_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("version");
        self
    }

    pub fn order_by_version_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("version");
        self
    }
    pub fn first_name_is_john(self) -> Self {
        self.with_first_name_is("John")
    }

    pub fn with_first_name_is_john(self) -> Self {
        self.with_first_name_is("John")
    }



    pub fn with_first_name_is_not_john(self) -> Self {
        self.with_first_name_is_not("John")
    }



    pub fn last_name_is_doe(self) -> Self {
        self.with_last_name_is("Doe")
    }

    pub fn with_last_name_is_doe(self) -> Self {
        self.with_last_name_is("Doe")
    }



    pub fn with_last_name_is_not_doe(self) -> Self {
        self.with_last_name_is_not("Doe")
    }



    pub fn email_is_john_doe_example_com(self) -> Self {
        self.with_email_is("john.doe@example.com")
    }

    pub fn with_email_is_john_doe_example_com(self) -> Self {
        self.with_email_is("john.doe@example.com")
    }



    pub fn with_email_is_not_john_doe_example_com(self) -> Self {
        self.with_email_is_not("john.doe@example.com")
    }



    pub fn phone_is_value_555_123_4567(self) -> Self {
        self.with_phone_is("555-123-4567")
    }

    pub fn with_phone_is_value_555_123_4567(self) -> Self {
        self.with_phone_is("555-123-4567")
    }



    pub fn with_phone_is_not_value_555_123_4567(self) -> Self {
        self.with_phone_is_not("555-123-4567")
    }



    pub fn hire_date_is_value_2023_01_15(self) -> Self {
        self.with_hire_date_is("2023-01-15")
    }

    pub fn with_hire_date_is_value_2023_01_15(self) -> Self {
        self.with_hire_date_is("2023-01-15")
    }



    pub fn with_hire_date_is_not_value_2023_01_15(self) -> Self {
        self.with_hire_date_is_not("2023-01-15")
    }



    pub fn position_is_mover(self) -> Self {
        self.with_position_is("Mover")
    }

    pub fn with_position_is_mover(self) -> Self {
        self.with_position_is("Mover")
    }



    pub fn with_position_is_not_mover(self) -> Self {
        self.with_position_is_not("Mover")
    }



    pub fn is_active_is_true(self) -> Self {
        self.with_is_active_is("true")
    }

    pub fn with_is_active_is_true(self) -> Self {
        self.with_is_active_is("true")
    }



    pub fn with_is_active_is_not_true(self) -> Self {
        self.with_is_active_is_not("true")
    }



    pub fn create_time_is_create_time(self) -> Self {
        self.with_create_time_is("createTime()")
    }

    pub fn with_create_time_is_create_time(self) -> Self {
        self.with_create_time_is("createTime()")
    }



    pub fn with_create_time_is_not_create_time(self) -> Self {
        self.with_create_time_is_not("createTime()")
    }



    pub fn update_time_is_update_time(self) -> Self {
        self.with_update_time_is("updateTime()")
    }

    pub fn with_update_time_is_update_time(self) -> Self {
        self.with_update_time_is("updateTime()")
    }



    pub fn with_update_time_is_not_update_time(self) -> Self {
        self.with_update_time_is_not("updateTime()")
    }




    pub fn have_job_assignments(self) -> Self {
        self.with_job_assignment_list_matching(SelectQuery::new("JobAssignment"))
    }

    pub fn have_no_job_assignments(self) -> Self {
        self.without_job_assignment_list_matching(SelectQuery::new("JobAssignment"))
    }

    pub fn with_job_assignment_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::JobAssignment as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("job_assignment_list", selection));
        self
    }

    pub fn without_job_assignment_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::JobAssignment as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("job_assignment_list", selection));
        self
    }

    pub fn select_job_assignment_list(mut self) -> Self {
        self.query = self.query.relation("job_assignment_list");
        self
    }

    pub fn select_job_assignment_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("job_assignment_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("job_assignment_list", selection));
        self
}

    pub fn have_worked_hourses(self) -> Self {
        self.with_worked_hours_list_matching(SelectQuery::new("WorkedHours"))
    }

    pub fn have_no_worked_hourses(self) -> Self {
        self.without_worked_hours_list_matching(SelectQuery::new("WorkedHours"))
    }

    pub fn with_worked_hours_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::WorkedHours as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("worked_hours_list", selection));
        self
    }

    pub fn without_worked_hours_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::WorkedHours as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("worked_hours_list", selection));
        self
    }

    pub fn select_worked_hours_list(mut self) -> Self {
        self.query = self.query.relation("worked_hours_list");
        self
    }

    pub fn select_worked_hours_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("worked_hours_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("worked_hours_list", selection));
        self
}

    pub fn have_payroll_calculations(self) -> Self {
        self.with_payroll_calculation_list_matching(SelectQuery::new("PayrollCalculation"))
    }

    pub fn have_no_payroll_calculations(self) -> Self {
        self.without_payroll_calculation_list_matching(SelectQuery::new("PayrollCalculation"))
    }

    pub fn with_payroll_calculation_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::PayrollCalculation as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("payroll_calculation_list", selection));
        self
    }

    pub fn without_payroll_calculation_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::PayrollCalculation as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("payroll_calculation_list", selection));
        self
    }

    pub fn select_payroll_calculation_list(mut self) -> Self {
        self.query = self.query.relation("payroll_calculation_list");
        self
    }

    pub fn select_payroll_calculation_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("payroll_calculation_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("payroll_calculation_list", selection));
        self
}

    pub fn have_bonuses(self) -> Self {
        self.with_bonus_list_matching(SelectQuery::new("Bonus"))
    }

    pub fn have_no_bonuses(self) -> Self {
        self.without_bonus_list_matching(SelectQuery::new("Bonus"))
    }

    pub fn with_bonus_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::Bonus as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("bonus_list", selection));
        self
    }

    pub fn without_bonus_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::Bonus as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("bonus_list", selection));
        self
    }

    pub fn select_bonus_list(mut self) -> Self {
        self.query = self.query.relation("bonus_list");
        self
    }

    pub fn select_bonus_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("bonus_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("bonus_list", selection));
        self
}

    pub fn have_leave_requests(self) -> Self {
        self.with_leave_request_list_matching(SelectQuery::new("LeaveRequest"))
    }

    pub fn have_no_leave_requests(self) -> Self {
        self.without_leave_request_list_matching(SelectQuery::new("LeaveRequest"))
    }

    pub fn with_leave_request_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::LeaveRequest as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("leave_request_list", selection));
        self
    }

    pub fn without_leave_request_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::LeaveRequest as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "employee_id",
        ));
        self.relation_filters.push(RelationFilter::new("leave_request_list", selection));
        self
    }

    pub fn select_leave_request_list(mut self) -> Self {
        self.query = self.query.relation("leave_request_list");
        self
    }

    pub fn select_leave_request_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("leave_request_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("leave_request_list", selection));
        self
}
    pub fn count_job_assignments(self) -> Self {
        self.count_job_assignments_as("count_job_assignments")
    }

    pub fn count_job_assignments_as(self, alias: impl Into<String>) -> Self {
        self.count_job_assignments_with(alias, crate::Q::job_assignments().unlimited())
    }

    pub fn count_job_assignments_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "job_assignment_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_job_assignments(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as("refinements", request)
    }

    pub fn stats_from_job_assignments_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "job_assignment_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_job_assignments_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments(request)
    }


    pub fn min_assigned_date_of_job_assignments(self) -> Self {
        self.min_assigned_date_of_job_assignments_as("min_assigned_date_of_job_assignments", crate::Q::job_assignments().unlimited())
    }

    pub fn min_assigned_date_of_job_assignments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as(alias, request.into().into_query().min("assigned_date", "min_assigned_date"))
    }
    pub fn max_assigned_date_of_job_assignments(self) -> Self {
        self.max_assigned_date_of_job_assignments_as("max_assigned_date_of_job_assignments", crate::Q::job_assignments().unlimited())
    }

    pub fn max_assigned_date_of_job_assignments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as(alias, request.into().into_query().max("assigned_date", "max_assigned_date"))
    }
    pub fn min_create_time_of_job_assignments(self) -> Self {
        self.min_create_time_of_job_assignments_as("min_create_time_of_job_assignments", crate::Q::job_assignments().unlimited())
    }

    pub fn min_create_time_of_job_assignments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_job_assignments(self) -> Self {
        self.max_create_time_of_job_assignments_as("max_create_time_of_job_assignments", crate::Q::job_assignments().unlimited())
    }

    pub fn max_create_time_of_job_assignments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_job_assignments(self) -> Self {
        self.min_update_time_of_job_assignments_as("min_update_time_of_job_assignments", crate::Q::job_assignments().unlimited())
    }

    pub fn min_update_time_of_job_assignments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_job_assignments(self) -> Self {
        self.max_update_time_of_job_assignments_as("max_update_time_of_job_assignments", crate::Q::job_assignments().unlimited())
    }

    pub fn max_update_time_of_job_assignments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_job_assignments_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_worked_hourses(self) -> Self {
        self.count_worked_hourses_as("count_worked_hourses")
    }

    pub fn count_worked_hourses_as(self, alias: impl Into<String>) -> Self {
        self.count_worked_hourses_with(alias, crate::Q::worked_hourses().unlimited())
    }

    pub fn count_worked_hourses_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "worked_hours_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_worked_hourses(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as("refinements", request)
    }

    pub fn stats_from_worked_hourses_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "worked_hours_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_worked_hourses_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses(request)
    }


    pub fn min_date_of_worked_hourses(self) -> Self {
        self.min_date_of_worked_hourses_as("min_date_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn min_date_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().min("date", "min_date"))
    }
    pub fn max_date_of_worked_hourses(self) -> Self {
        self.max_date_of_worked_hourses_as("max_date_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn max_date_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().max("date", "max_date"))
    }
    pub fn sum_hours_worked_of_worked_hourses(self) -> Self {
        self.sum_hours_worked_of_worked_hourses_as("sum_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn sum_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().sum("hours_worked", "sum_hours_worked"))
    }
    pub fn min_hours_worked_of_worked_hourses(self) -> Self {
        self.min_hours_worked_of_worked_hourses_as("min_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn min_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().min("hours_worked", "min_hours_worked"))
    }
    pub fn max_hours_worked_of_worked_hourses(self) -> Self {
        self.max_hours_worked_of_worked_hourses_as("max_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn max_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().max("hours_worked", "max_hours_worked"))
    }
    pub fn avg_hours_worked_of_worked_hourses(self) -> Self {
        self.avg_hours_worked_of_worked_hourses_as("avg_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn avg_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().avg("hours_worked", "avg_hours_worked"))
    }
    pub fn standard_deviation_hours_worked_of_worked_hourses(self) -> Self {
        self.standard_deviation_hours_worked_of_worked_hourses_as("standard_deviation_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn standard_deviation_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().stddev("hours_worked", "stdDev_hours_worked"))
    }
    pub fn square_root_of_population_standard_deviation_hours_worked_of_worked_hourses(self) -> Self {
        self.square_root_of_population_standard_deviation_hours_worked_of_worked_hourses_as("square_root_of_population_standard_deviation_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().stddev_pop("hours_worked", "stdDevPop_hours_worked"))
    }
    pub fn sample_variance_hours_worked_of_worked_hourses(self) -> Self {
        self.sample_variance_hours_worked_of_worked_hourses_as("sample_variance_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn sample_variance_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().var_samp("hours_worked", "varSamp_hours_worked"))
    }
    pub fn sample_population_variance_hours_worked_of_worked_hourses(self) -> Self {
        self.sample_population_variance_hours_worked_of_worked_hourses_as("sample_population_variance_hours_worked_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn sample_population_variance_hours_worked_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().var_pop("hours_worked", "varPop_hours_worked"))
    }
    pub fn sum_overtime_hours_of_worked_hourses(self) -> Self {
        self.sum_overtime_hours_of_worked_hourses_as("sum_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn sum_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().sum("overtime_hours", "sum_overtime_hours"))
    }
    pub fn min_overtime_hours_of_worked_hourses(self) -> Self {
        self.min_overtime_hours_of_worked_hourses_as("min_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn min_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().min("overtime_hours", "min_overtime_hours"))
    }
    pub fn max_overtime_hours_of_worked_hourses(self) -> Self {
        self.max_overtime_hours_of_worked_hourses_as("max_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn max_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().max("overtime_hours", "max_overtime_hours"))
    }
    pub fn avg_overtime_hours_of_worked_hourses(self) -> Self {
        self.avg_overtime_hours_of_worked_hourses_as("avg_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn avg_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().avg("overtime_hours", "avg_overtime_hours"))
    }
    pub fn standard_deviation_overtime_hours_of_worked_hourses(self) -> Self {
        self.standard_deviation_overtime_hours_of_worked_hourses_as("standard_deviation_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn standard_deviation_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().stddev("overtime_hours", "stdDev_overtime_hours"))
    }
    pub fn square_root_of_population_standard_deviation_overtime_hours_of_worked_hourses(self) -> Self {
        self.square_root_of_population_standard_deviation_overtime_hours_of_worked_hourses_as("square_root_of_population_standard_deviation_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().stddev_pop("overtime_hours", "stdDevPop_overtime_hours"))
    }
    pub fn sample_variance_overtime_hours_of_worked_hourses(self) -> Self {
        self.sample_variance_overtime_hours_of_worked_hourses_as("sample_variance_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn sample_variance_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().var_samp("overtime_hours", "varSamp_overtime_hours"))
    }
    pub fn sample_population_variance_overtime_hours_of_worked_hourses(self) -> Self {
        self.sample_population_variance_overtime_hours_of_worked_hourses_as("sample_population_variance_overtime_hours_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn sample_population_variance_overtime_hours_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().var_pop("overtime_hours", "varPop_overtime_hours"))
    }
    pub fn min_create_time_of_worked_hourses(self) -> Self {
        self.min_create_time_of_worked_hourses_as("min_create_time_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn min_create_time_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_worked_hourses(self) -> Self {
        self.max_create_time_of_worked_hourses_as("max_create_time_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn max_create_time_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_worked_hourses(self) -> Self {
        self.min_update_time_of_worked_hourses_as("min_update_time_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn min_update_time_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_worked_hourses(self) -> Self {
        self.max_update_time_of_worked_hourses_as("max_update_time_of_worked_hourses", crate::Q::worked_hourses().unlimited())
    }

    pub fn max_update_time_of_worked_hourses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_worked_hourses_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_payroll_calculations(self) -> Self {
        self.count_payroll_calculations_as("count_payroll_calculations")
    }

    pub fn count_payroll_calculations_as(self, alias: impl Into<String>) -> Self {
        self.count_payroll_calculations_with(alias, crate::Q::payroll_calculations().unlimited())
    }

    pub fn count_payroll_calculations_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "payroll_calculation_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_payroll_calculations(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as("refinements", request)
    }

    pub fn stats_from_payroll_calculations_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "payroll_calculation_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_payroll_calculations_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations(request)
    }


    pub fn min_period_start_of_payroll_calculations(self) -> Self {
        self.min_period_start_of_payroll_calculations_as("min_period_start_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_period_start_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("period_start", "min_period_start"))
    }
    pub fn max_period_start_of_payroll_calculations(self) -> Self {
        self.max_period_start_of_payroll_calculations_as("max_period_start_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_period_start_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("period_start", "max_period_start"))
    }
    pub fn min_period_end_of_payroll_calculations(self) -> Self {
        self.min_period_end_of_payroll_calculations_as("min_period_end_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_period_end_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("period_end", "min_period_end"))
    }
    pub fn max_period_end_of_payroll_calculations(self) -> Self {
        self.max_period_end_of_payroll_calculations_as("max_period_end_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_period_end_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("period_end", "max_period_end"))
    }
    pub fn sum_regular_hours_of_payroll_calculations(self) -> Self {
        self.sum_regular_hours_of_payroll_calculations_as("sum_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sum_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().sum("regular_hours", "sum_regular_hours"))
    }
    pub fn min_regular_hours_of_payroll_calculations(self) -> Self {
        self.min_regular_hours_of_payroll_calculations_as("min_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("regular_hours", "min_regular_hours"))
    }
    pub fn max_regular_hours_of_payroll_calculations(self) -> Self {
        self.max_regular_hours_of_payroll_calculations_as("max_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("regular_hours", "max_regular_hours"))
    }
    pub fn avg_regular_hours_of_payroll_calculations(self) -> Self {
        self.avg_regular_hours_of_payroll_calculations_as("avg_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn avg_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().avg("regular_hours", "avg_regular_hours"))
    }
    pub fn standard_deviation_regular_hours_of_payroll_calculations(self) -> Self {
        self.standard_deviation_regular_hours_of_payroll_calculations_as("standard_deviation_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn standard_deviation_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev("regular_hours", "stdDev_regular_hours"))
    }
    pub fn square_root_of_population_standard_deviation_regular_hours_of_payroll_calculations(self) -> Self {
        self.square_root_of_population_standard_deviation_regular_hours_of_payroll_calculations_as("square_root_of_population_standard_deviation_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev_pop("regular_hours", "stdDevPop_regular_hours"))
    }
    pub fn sample_variance_regular_hours_of_payroll_calculations(self) -> Self {
        self.sample_variance_regular_hours_of_payroll_calculations_as("sample_variance_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_variance_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_samp("regular_hours", "varSamp_regular_hours"))
    }
    pub fn sample_population_variance_regular_hours_of_payroll_calculations(self) -> Self {
        self.sample_population_variance_regular_hours_of_payroll_calculations_as("sample_population_variance_regular_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_population_variance_regular_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_pop("regular_hours", "varPop_regular_hours"))
    }
    pub fn sum_overtime_hours_of_payroll_calculations(self) -> Self {
        self.sum_overtime_hours_of_payroll_calculations_as("sum_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sum_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().sum("overtime_hours", "sum_overtime_hours"))
    }
    pub fn min_overtime_hours_of_payroll_calculations(self) -> Self {
        self.min_overtime_hours_of_payroll_calculations_as("min_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("overtime_hours", "min_overtime_hours"))
    }
    pub fn max_overtime_hours_of_payroll_calculations(self) -> Self {
        self.max_overtime_hours_of_payroll_calculations_as("max_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("overtime_hours", "max_overtime_hours"))
    }
    pub fn avg_overtime_hours_of_payroll_calculations(self) -> Self {
        self.avg_overtime_hours_of_payroll_calculations_as("avg_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn avg_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().avg("overtime_hours", "avg_overtime_hours"))
    }
    pub fn standard_deviation_overtime_hours_of_payroll_calculations(self) -> Self {
        self.standard_deviation_overtime_hours_of_payroll_calculations_as("standard_deviation_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn standard_deviation_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev("overtime_hours", "stdDev_overtime_hours"))
    }
    pub fn square_root_of_population_standard_deviation_overtime_hours_of_payroll_calculations(self) -> Self {
        self.square_root_of_population_standard_deviation_overtime_hours_of_payroll_calculations_as("square_root_of_population_standard_deviation_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev_pop("overtime_hours", "stdDevPop_overtime_hours"))
    }
    pub fn sample_variance_overtime_hours_of_payroll_calculations(self) -> Self {
        self.sample_variance_overtime_hours_of_payroll_calculations_as("sample_variance_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_variance_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_samp("overtime_hours", "varSamp_overtime_hours"))
    }
    pub fn sample_population_variance_overtime_hours_of_payroll_calculations(self) -> Self {
        self.sample_population_variance_overtime_hours_of_payroll_calculations_as("sample_population_variance_overtime_hours_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_population_variance_overtime_hours_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_pop("overtime_hours", "varPop_overtime_hours"))
    }
    pub fn sum_hourly_rate_of_payroll_calculations(self) -> Self {
        self.sum_hourly_rate_of_payroll_calculations_as("sum_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sum_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().sum("hourly_rate", "sum_hourly_rate"))
    }
    pub fn min_hourly_rate_of_payroll_calculations(self) -> Self {
        self.min_hourly_rate_of_payroll_calculations_as("min_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("hourly_rate", "min_hourly_rate"))
    }
    pub fn max_hourly_rate_of_payroll_calculations(self) -> Self {
        self.max_hourly_rate_of_payroll_calculations_as("max_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("hourly_rate", "max_hourly_rate"))
    }
    pub fn avg_hourly_rate_of_payroll_calculations(self) -> Self {
        self.avg_hourly_rate_of_payroll_calculations_as("avg_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn avg_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().avg("hourly_rate", "avg_hourly_rate"))
    }
    pub fn standard_deviation_hourly_rate_of_payroll_calculations(self) -> Self {
        self.standard_deviation_hourly_rate_of_payroll_calculations_as("standard_deviation_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn standard_deviation_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev("hourly_rate", "stdDev_hourly_rate"))
    }
    pub fn square_root_of_population_standard_deviation_hourly_rate_of_payroll_calculations(self) -> Self {
        self.square_root_of_population_standard_deviation_hourly_rate_of_payroll_calculations_as("square_root_of_population_standard_deviation_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev_pop("hourly_rate", "stdDevPop_hourly_rate"))
    }
    pub fn sample_variance_hourly_rate_of_payroll_calculations(self) -> Self {
        self.sample_variance_hourly_rate_of_payroll_calculations_as("sample_variance_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_variance_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_samp("hourly_rate", "varSamp_hourly_rate"))
    }
    pub fn sample_population_variance_hourly_rate_of_payroll_calculations(self) -> Self {
        self.sample_population_variance_hourly_rate_of_payroll_calculations_as("sample_population_variance_hourly_rate_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_population_variance_hourly_rate_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_pop("hourly_rate", "varPop_hourly_rate"))
    }
    pub fn sum_gross_pay_of_payroll_calculations(self) -> Self {
        self.sum_gross_pay_of_payroll_calculations_as("sum_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sum_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().sum("gross_pay", "sum_gross_pay"))
    }
    pub fn min_gross_pay_of_payroll_calculations(self) -> Self {
        self.min_gross_pay_of_payroll_calculations_as("min_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("gross_pay", "min_gross_pay"))
    }
    pub fn max_gross_pay_of_payroll_calculations(self) -> Self {
        self.max_gross_pay_of_payroll_calculations_as("max_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("gross_pay", "max_gross_pay"))
    }
    pub fn avg_gross_pay_of_payroll_calculations(self) -> Self {
        self.avg_gross_pay_of_payroll_calculations_as("avg_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn avg_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().avg("gross_pay", "avg_gross_pay"))
    }
    pub fn standard_deviation_gross_pay_of_payroll_calculations(self) -> Self {
        self.standard_deviation_gross_pay_of_payroll_calculations_as("standard_deviation_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn standard_deviation_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev("gross_pay", "stdDev_gross_pay"))
    }
    pub fn square_root_of_population_standard_deviation_gross_pay_of_payroll_calculations(self) -> Self {
        self.square_root_of_population_standard_deviation_gross_pay_of_payroll_calculations_as("square_root_of_population_standard_deviation_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev_pop("gross_pay", "stdDevPop_gross_pay"))
    }
    pub fn sample_variance_gross_pay_of_payroll_calculations(self) -> Self {
        self.sample_variance_gross_pay_of_payroll_calculations_as("sample_variance_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_variance_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_samp("gross_pay", "varSamp_gross_pay"))
    }
    pub fn sample_population_variance_gross_pay_of_payroll_calculations(self) -> Self {
        self.sample_population_variance_gross_pay_of_payroll_calculations_as("sample_population_variance_gross_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_population_variance_gross_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_pop("gross_pay", "varPop_gross_pay"))
    }
    pub fn sum_deductions_of_payroll_calculations(self) -> Self {
        self.sum_deductions_of_payroll_calculations_as("sum_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sum_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().sum("deductions", "sum_deductions"))
    }
    pub fn min_deductions_of_payroll_calculations(self) -> Self {
        self.min_deductions_of_payroll_calculations_as("min_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("deductions", "min_deductions"))
    }
    pub fn max_deductions_of_payroll_calculations(self) -> Self {
        self.max_deductions_of_payroll_calculations_as("max_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("deductions", "max_deductions"))
    }
    pub fn avg_deductions_of_payroll_calculations(self) -> Self {
        self.avg_deductions_of_payroll_calculations_as("avg_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn avg_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().avg("deductions", "avg_deductions"))
    }
    pub fn standard_deviation_deductions_of_payroll_calculations(self) -> Self {
        self.standard_deviation_deductions_of_payroll_calculations_as("standard_deviation_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn standard_deviation_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev("deductions", "stdDev_deductions"))
    }
    pub fn square_root_of_population_standard_deviation_deductions_of_payroll_calculations(self) -> Self {
        self.square_root_of_population_standard_deviation_deductions_of_payroll_calculations_as("square_root_of_population_standard_deviation_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev_pop("deductions", "stdDevPop_deductions"))
    }
    pub fn sample_variance_deductions_of_payroll_calculations(self) -> Self {
        self.sample_variance_deductions_of_payroll_calculations_as("sample_variance_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_variance_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_samp("deductions", "varSamp_deductions"))
    }
    pub fn sample_population_variance_deductions_of_payroll_calculations(self) -> Self {
        self.sample_population_variance_deductions_of_payroll_calculations_as("sample_population_variance_deductions_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_population_variance_deductions_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_pop("deductions", "varPop_deductions"))
    }
    pub fn sum_net_pay_of_payroll_calculations(self) -> Self {
        self.sum_net_pay_of_payroll_calculations_as("sum_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sum_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().sum("net_pay", "sum_net_pay"))
    }
    pub fn min_net_pay_of_payroll_calculations(self) -> Self {
        self.min_net_pay_of_payroll_calculations_as("min_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("net_pay", "min_net_pay"))
    }
    pub fn max_net_pay_of_payroll_calculations(self) -> Self {
        self.max_net_pay_of_payroll_calculations_as("max_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("net_pay", "max_net_pay"))
    }
    pub fn avg_net_pay_of_payroll_calculations(self) -> Self {
        self.avg_net_pay_of_payroll_calculations_as("avg_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn avg_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().avg("net_pay", "avg_net_pay"))
    }
    pub fn standard_deviation_net_pay_of_payroll_calculations(self) -> Self {
        self.standard_deviation_net_pay_of_payroll_calculations_as("standard_deviation_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn standard_deviation_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev("net_pay", "stdDev_net_pay"))
    }
    pub fn square_root_of_population_standard_deviation_net_pay_of_payroll_calculations(self) -> Self {
        self.square_root_of_population_standard_deviation_net_pay_of_payroll_calculations_as("square_root_of_population_standard_deviation_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().stddev_pop("net_pay", "stdDevPop_net_pay"))
    }
    pub fn sample_variance_net_pay_of_payroll_calculations(self) -> Self {
        self.sample_variance_net_pay_of_payroll_calculations_as("sample_variance_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_variance_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_samp("net_pay", "varSamp_net_pay"))
    }
    pub fn sample_population_variance_net_pay_of_payroll_calculations(self) -> Self {
        self.sample_population_variance_net_pay_of_payroll_calculations_as("sample_population_variance_net_pay_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn sample_population_variance_net_pay_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().var_pop("net_pay", "varPop_net_pay"))
    }
    pub fn min_create_time_of_payroll_calculations(self) -> Self {
        self.min_create_time_of_payroll_calculations_as("min_create_time_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_create_time_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_payroll_calculations(self) -> Self {
        self.max_create_time_of_payroll_calculations_as("max_create_time_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_create_time_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_payroll_calculations(self) -> Self {
        self.min_update_time_of_payroll_calculations_as("min_update_time_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn min_update_time_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_payroll_calculations(self) -> Self {
        self.max_update_time_of_payroll_calculations_as("max_update_time_of_payroll_calculations", crate::Q::payroll_calculations().unlimited())
    }

    pub fn max_update_time_of_payroll_calculations_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payroll_calculations_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_bonuses(self) -> Self {
        self.count_bonuses_as("count_bonuses")
    }

    pub fn count_bonuses_as(self, alias: impl Into<String>) -> Self {
        self.count_bonuses_with(alias, crate::Q::bonuses().unlimited())
    }

    pub fn count_bonuses_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "bonus_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_bonuses(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as("refinements", request)
    }

    pub fn stats_from_bonuses_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "bonus_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_bonuses_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses(request)
    }


    pub fn sum_amount_of_bonuses(self) -> Self {
        self.sum_amount_of_bonuses_as("sum_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn sum_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().sum("amount", "sum_amount"))
    }
    pub fn min_amount_of_bonuses(self) -> Self {
        self.min_amount_of_bonuses_as("min_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn min_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().min("amount", "min_amount"))
    }
    pub fn max_amount_of_bonuses(self) -> Self {
        self.max_amount_of_bonuses_as("max_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn max_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().max("amount", "max_amount"))
    }
    pub fn avg_amount_of_bonuses(self) -> Self {
        self.avg_amount_of_bonuses_as("avg_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn avg_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().avg("amount", "avg_amount"))
    }
    pub fn standard_deviation_amount_of_bonuses(self) -> Self {
        self.standard_deviation_amount_of_bonuses_as("standard_deviation_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn standard_deviation_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().stddev("amount", "stdDev_amount"))
    }
    pub fn square_root_of_population_standard_deviation_amount_of_bonuses(self) -> Self {
        self.square_root_of_population_standard_deviation_amount_of_bonuses_as("square_root_of_population_standard_deviation_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().stddev_pop("amount", "stdDevPop_amount"))
    }
    pub fn sample_variance_amount_of_bonuses(self) -> Self {
        self.sample_variance_amount_of_bonuses_as("sample_variance_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn sample_variance_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().var_samp("amount", "varSamp_amount"))
    }
    pub fn sample_population_variance_amount_of_bonuses(self) -> Self {
        self.sample_population_variance_amount_of_bonuses_as("sample_population_variance_amount_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn sample_population_variance_amount_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().var_pop("amount", "varPop_amount"))
    }
    pub fn min_awarded_date_of_bonuses(self) -> Self {
        self.min_awarded_date_of_bonuses_as("min_awarded_date_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn min_awarded_date_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().min("awarded_date", "min_awarded_date"))
    }
    pub fn max_awarded_date_of_bonuses(self) -> Self {
        self.max_awarded_date_of_bonuses_as("max_awarded_date_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn max_awarded_date_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().max("awarded_date", "max_awarded_date"))
    }
    pub fn min_create_time_of_bonuses(self) -> Self {
        self.min_create_time_of_bonuses_as("min_create_time_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn min_create_time_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_bonuses(self) -> Self {
        self.max_create_time_of_bonuses_as("max_create_time_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn max_create_time_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_bonuses(self) -> Self {
        self.min_update_time_of_bonuses_as("min_update_time_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn min_update_time_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_bonuses(self) -> Self {
        self.max_update_time_of_bonuses_as("max_update_time_of_bonuses", crate::Q::bonuses().unlimited())
    }

    pub fn max_update_time_of_bonuses_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_bonuses_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_leave_requests(self) -> Self {
        self.count_leave_requests_as("count_leave_requests")
    }

    pub fn count_leave_requests_as(self, alias: impl Into<String>) -> Self {
        self.count_leave_requests_with(alias, crate::Q::leave_requests().unlimited())
    }

    pub fn count_leave_requests_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "leave_request_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_leave_requests(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as("refinements", request)
    }

    pub fn stats_from_leave_requests_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "leave_request_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_leave_requests_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests(request)
    }


    pub fn min_start_date_of_leave_requests(self) -> Self {
        self.min_start_date_of_leave_requests_as("min_start_date_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn min_start_date_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().min("start_date", "min_start_date"))
    }
    pub fn max_start_date_of_leave_requests(self) -> Self {
        self.max_start_date_of_leave_requests_as("max_start_date_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn max_start_date_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().max("start_date", "max_start_date"))
    }
    pub fn min_end_date_of_leave_requests(self) -> Self {
        self.min_end_date_of_leave_requests_as("min_end_date_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn min_end_date_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().min("end_date", "min_end_date"))
    }
    pub fn max_end_date_of_leave_requests(self) -> Self {
        self.max_end_date_of_leave_requests_as("max_end_date_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn max_end_date_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().max("end_date", "max_end_date"))
    }
    pub fn min_create_time_of_leave_requests(self) -> Self {
        self.min_create_time_of_leave_requests_as("min_create_time_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn min_create_time_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_leave_requests(self) -> Self {
        self.max_create_time_of_leave_requests_as("max_create_time_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn max_create_time_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_leave_requests(self) -> Self {
        self.min_update_time_of_leave_requests_as("min_update_time_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn min_update_time_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_leave_requests(self) -> Self {
        self.max_update_time_of_leave_requests_as("max_update_time_of_leave_requests", crate::Q::leave_requests().unlimited())
    }

    pub fn max_update_time_of_leave_requests_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_leave_requests_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }
}

impl<R> Default for EmployeeRequest<R> {
    fn default() -> Self {
        Self::new()
    }
}

impl<R> From< EmployeeRequest<R> > for SelectQuery {
    fn from(request: EmployeeRequest<R>) -> Self {
        QuerySelection::from(request).into_query()
    }
}

impl<R> From< EmployeeRequest<R> > for QuerySelection {
    fn from(request: EmployeeRequest<R>) -> Self {
        Self {
            query: request.query,
            relation_selections: request.relation_selections,
            relation_filters: request.relation_filters,
            child_enhancements: request.child_enhancements,
            query_options: request.query_options,
        }
    }
}


impl<'a, C> crate::request_support::AuditedSave<'a, C> for teaql_core::Audited<crate::Employee> 
where C: crate::request_support::TeaqlRepositoryProvider + ?Sized + 'a
{
    type Error = crate::TeaqlDataServiceError<C::EmployeeRepository<'a>>;
    fn save(self, ctx: &'a C) -> std::pin::Pin<Box<dyn std::future::Future<Output = Result<teaql_runtime::GraphNode, Self::Error>> + '_>> {
        Box::pin(async move {
            teaql_runtime::save_audited_ledger_entity(self, ctx.user_context())
                .await
                .map_err(DataServiceError::Runtime)
        })
    }
}

impl<R: teaql_core::Entity> crate::PurposedQuery<EmployeeRequest<R>> {
    pub fn new_entity<C>(&self, ctx: &C) -> crate::Employee
    where
        C: crate::TeaqlRuntime + ?Sized,
    {
        crate::Employee::runtime_new(ctx.user_context().entity_root())
    }

    fn into_inner_with_trace(mut self) -> EmployeeRequest<R> {
        self.inner.query.trace_chain.push(teaql_core::TraceNode::new(
            self.inner.query.entity.clone(),
            None,
            self.purpose,
        ));
        self.inner
    }

    pub async fn execute_for_page<'a, C>(
        self,
        ctx: &'a C,
        offset: u64,
        limit: u64,
    ) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_page(ctx, offset, limit).await
    }

    pub async fn execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_exists(ctx).await
    }

    pub async fn execute_for_list<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_list(ctx).await
    }

    /// Execute query in streaming mode (chunked).
    /// Returns a Vec of StreamChunk, each containing up to chunk_size rows.
    /// Set chunk size via .stream(chunk_size) or .stream_default() on the query.
    pub async fn execute_for_stream<'a, C>(self, ctx: &'a C) -> Result<Vec<teaql_data_service::StreamChunk>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_stream(ctx).await
    }

    pub async fn execute_for_first<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_first(ctx).await
    }

    pub async fn execute_for_one<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_one(ctx).await
    }


    pub async fn execute_for_records<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_records(ctx).await
    }

    pub async fn execute_for_record<'a, C>(self, ctx: &'a C) -> Result<Option<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_record(ctx).await
    }

    pub async fn execute_for_count<'a, C>(self, ctx: &'a C) -> Result<u64, crate::request_support::TeaqlDataServiceError<C::EmployeeRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_count(ctx).await
    }
}
