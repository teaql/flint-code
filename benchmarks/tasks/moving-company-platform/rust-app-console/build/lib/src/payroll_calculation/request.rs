use std::marker::PhantomData;

use serde_json::Value as JsonValue;
use teaql_core::{Aggregate, AggregateFunction, EntityDescriptor, Expr, Record, SelectQuery, SmartList};
use teaql_runtime::{DataServiceError, RuntimeError};

use crate::request_support::*;

impl EntityReference for crate::PayrollCalculation {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(&self)
    }
}

impl EntityReference for &crate::PayrollCalculation {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(self)
    }
}

// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/payroll_calculation
#[derive(Debug)]
pub struct PayrollCalculationRequest<R = crate::PayrollCalculation> {
    query: SelectQuery,
    relation_selections: Vec<RelationSelection>,
    relation_filters: Vec<RelationFilter>,
    child_enhancements: Vec<QuerySelection>,
    query_options: QueryOptions,
    marker: PhantomData<R>,
}

impl<R> Clone for PayrollCalculationRequest<R> {
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

impl<R> PayrollCalculationRequest<R> {
    pub(crate) fn new() -> Self {
        Self {
            query: SelectQuery::new("PayrollCalculation")
                .project("id")
                .project("version"),
            relation_selections: Vec::new(),
            relation_filters: Vec::new(),
            child_enhancements: Vec::new(),
            query_options: QueryOptions::default(),
            marker: PhantomData,
        }
    }

    pub fn return_type<T>(self) -> PayrollCalculationRequest<T> {
        PayrollCalculationRequest {
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let repository = ctx
            .payroll_calculation_repository()
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
    ) -> Result<Vec<teaql_data_service::StreamChunk>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .payroll_calculation_repository()
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
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
    ) -> Result<u64, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .payroll_calculation_repository()
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
            .ok_or_else(|| DataServiceError::Runtime(RuntimeError::Graph(format!("count result for PayrollCalculation is missing or not numeric"))))
    }

    pub(crate) async fn _execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .payroll_calculation_repository()
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
    ) -> Result<SmartList<Record>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .payroll_calculation_repository()
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
    ) -> Result<Option<Record>, TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
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
            "period_start" => Some("period_start"),
            "period_end" => Some("period_end"),
            "regular_hours" => Some("regular_hours"),
            "overtime_hours" => Some("overtime_hours"),
            "hourly_rate" => Some("hourly_rate"),
            "gross_pay" => Some("gross_pay"),
            "deductions" => Some("deductions"),
            "net_pay" => Some("net_pay"),
            "create_time" => Some("create_time"),
            "update_time" => Some("update_time"),
            "version" => Some("version"),
            "employee" | "employee_id" => Some("employee_id"),
            _ => None,
        }
    }

    fn apply_dynamic_json_chain_filter(self, head: &str, tail: &str, value: &JsonValue) -> Self {
        let _ = (tail, value);
        match head {
            "employee" => {
                self.with_employee_matching(
                    crate::Q::employees_minimal()
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
        self.query = self.query.project("period_start");
        self.query = self.query.project("period_end");
        self.query = self.query.project("regular_hours");
        self.query = self.query.project("overtime_hours");
        self.query = self.query.project("hourly_rate");
        self.query = self.query.project("gross_pay");
        self.query = self.query.project("deductions");
        self.query = self.query.project("net_pay");
        self.query = self.query.project("create_time");
        self.query = self.query.project("update_time");
        self.query = self.query.project("version");
        self.query = self.query.project("employee_id");
        self
    }

    pub fn select_self_fields(self) -> Self {
        self.select_self()
    }

    pub fn select_self_without_parent(self) -> Self {
        self.select_self_fields()
    }

    pub fn select_all(self) -> Self {
        let mut request = self.select_self();
        request = request.select_employee();
        request
    }

    pub fn select_children(self) -> Self {
        self.select_all()
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


    pub fn select_period_start(mut self) -> Self {
        self.query = self.query.project("period_start");
        self
    }

    pub fn project_period_start(self) -> Self {
        self.select_period_start()
    }

    pub fn select_period_start_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_period_start_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_period_start_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("period_start", raw_sql_segment));
        self
    }

    pub fn group_by_period_start(self) -> Self {
        self.group_by("period_start")
    }

    pub fn group_by_period_start_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("period_start");
        request.query = request
            .query
            .project_expr(alias, Expr::column("period_start"));
        request
    }

    pub fn group_by_period_start_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("period_start")
            .aggregate_with_function("period_start", alias, function)
    }

    pub fn count_period_start(self) -> Self {
        self.count_period_start_as("period_start_count")
    }

    pub fn count_period_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("period_start", alias)
    }

    pub fn sum_period_start(self) -> Self {
        self.sum_period_start_as("sum_period_start")
    }

    pub fn sum_period_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("period_start", alias)
    }

    pub fn avg_period_start(self) -> Self {
        self.avg_period_start_as("avg_period_start")
    }

    pub fn avg_period_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("period_start", alias)
    }

    pub fn min_period_start(self) -> Self {
        self.min_period_start_as("min_period_start")
    }

    pub fn min_period_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("period_start", alias)
    }

    pub fn max_period_start(self) -> Self {
        self.max_period_start_as("max_period_start")
    }

    pub fn max_period_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("period_start", alias)
    }

    pub fn unselect_period_start(mut self) -> Self {
        self.query.projection.retain(|field| field != "period_start");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "period_start");
        self
    }


    pub fn with_period_start(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "period_start",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_period_start_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "period_start",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_period_start_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("period_start", value));
        self
    }



    pub fn with_period_start_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("period_start", value));
        self
    }

    pub fn with_period_start_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("period_start", value));
        self
    }

    pub fn with_period_start_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("period_start", value));
        self
    }

    pub fn with_period_start_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("period_start", value));
        self
    }

    pub fn with_period_start_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("period_start", value));
        self
    }

    pub fn with_period_start_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("period_start", lower, upper));
        self
    }

    pub fn with_period_start_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "period_start",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_period_start_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "period_start",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_period_start_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "period_start",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_period_start_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("period_start", value));
        self
    }

    pub fn with_period_start_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("period_start", value));
        self
    }

    pub fn with_period_start_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("period_start"));
        self
    }



    pub fn with_period_start_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("period_start"));
        self
    }


    pub fn order_by_period_start_asc(mut self) -> Self {
        self.query = self.query.order_asc("period_start");
        self
    }

    pub fn order_by_period_start_desc(mut self) -> Self {
        self.query = self.query.order_desc("period_start");
        self
    }

    pub fn order_by_period_start_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("period_start");
        self
    }

    pub fn order_by_period_start_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("period_start");
        self
    }


    pub fn select_period_end(mut self) -> Self {
        self.query = self.query.project("period_end");
        self
    }

    pub fn project_period_end(self) -> Self {
        self.select_period_end()
    }

    pub fn select_period_end_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_period_end_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_period_end_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("period_end", raw_sql_segment));
        self
    }

    pub fn group_by_period_end(self) -> Self {
        self.group_by("period_end")
    }

    pub fn group_by_period_end_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("period_end");
        request.query = request
            .query
            .project_expr(alias, Expr::column("period_end"));
        request
    }

    pub fn group_by_period_end_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("period_end")
            .aggregate_with_function("period_end", alias, function)
    }

    pub fn count_period_end(self) -> Self {
        self.count_period_end_as("period_end_count")
    }

    pub fn count_period_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("period_end", alias)
    }

    pub fn sum_period_end(self) -> Self {
        self.sum_period_end_as("sum_period_end")
    }

    pub fn sum_period_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("period_end", alias)
    }

    pub fn avg_period_end(self) -> Self {
        self.avg_period_end_as("avg_period_end")
    }

    pub fn avg_period_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("period_end", alias)
    }

    pub fn min_period_end(self) -> Self {
        self.min_period_end_as("min_period_end")
    }

    pub fn min_period_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("period_end", alias)
    }

    pub fn max_period_end(self) -> Self {
        self.max_period_end_as("max_period_end")
    }

    pub fn max_period_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("period_end", alias)
    }

    pub fn unselect_period_end(mut self) -> Self {
        self.query.projection.retain(|field| field != "period_end");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "period_end");
        self
    }


    pub fn with_period_end(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "period_end",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_period_end_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "period_end",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_period_end_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("period_end", value));
        self
    }



    pub fn with_period_end_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("period_end", value));
        self
    }

    pub fn with_period_end_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("period_end", value));
        self
    }

    pub fn with_period_end_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("period_end", value));
        self
    }

    pub fn with_period_end_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("period_end", value));
        self
    }

    pub fn with_period_end_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("period_end", value));
        self
    }

    pub fn with_period_end_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("period_end", lower, upper));
        self
    }

    pub fn with_period_end_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "period_end",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_period_end_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "period_end",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_period_end_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "period_end",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_period_end_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("period_end", value));
        self
    }

    pub fn with_period_end_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("period_end", value));
        self
    }

    pub fn with_period_end_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("period_end"));
        self
    }



    pub fn with_period_end_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("period_end"));
        self
    }


    pub fn order_by_period_end_asc(mut self) -> Self {
        self.query = self.query.order_asc("period_end");
        self
    }

    pub fn order_by_period_end_desc(mut self) -> Self {
        self.query = self.query.order_desc("period_end");
        self
    }

    pub fn order_by_period_end_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("period_end");
        self
    }

    pub fn order_by_period_end_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("period_end");
        self
    }


    pub fn select_regular_hours(mut self) -> Self {
        self.query = self.query.project("regular_hours");
        self
    }

    pub fn project_regular_hours(self) -> Self {
        self.select_regular_hours()
    }

    pub fn select_regular_hours_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_regular_hours_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_regular_hours_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("regular_hours", raw_sql_segment));
        self
    }

    pub fn select_regular_hours_with_function(self, function: AggregateFunction) -> Self {
        self.select_regular_hours_as_with_function("regular_hours", function)
    }

    pub fn select_regular_hours_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("regular_hours", alias, function)
    }

    pub fn group_by_regular_hours(self) -> Self {
        self.group_by("regular_hours")
    }

    pub fn group_by_regular_hours_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("regular_hours");
        request.query = request
            .query
            .project_expr(alias, Expr::column("regular_hours"));
        request
    }

    pub fn group_by_regular_hours_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("regular_hours")
            .aggregate_with_function("regular_hours", alias, function)
    }

    pub fn count_regular_hours(self) -> Self {
        self.count_regular_hours_as("regular_hours_count")
    }

    pub fn count_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("regular_hours", alias)
    }

    pub fn sum_regular_hours(self) -> Self {
        self.sum_regular_hours_as("sum_regular_hours")
    }

    pub fn sum_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("regular_hours", alias)
    }

    pub fn avg_regular_hours(self) -> Self {
        self.avg_regular_hours_as("avg_regular_hours")
    }

    pub fn avg_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("regular_hours", alias)
    }

    pub fn min_regular_hours(self) -> Self {
        self.min_regular_hours_as("min_regular_hours")
    }

    pub fn min_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("regular_hours", alias)
    }

    pub fn max_regular_hours(self) -> Self {
        self.max_regular_hours_as("max_regular_hours")
    }

    pub fn max_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("regular_hours", alias)
    }

    pub fn standard_deviation_regular_hours(self) -> Self {
        self.standard_deviation_regular_hours_as("stdDev_regular_hours")
    }

    pub fn standard_deviation_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("regular_hours", alias)
    }

    pub fn square_root_of_population_standard_deviation_regular_hours(self) -> Self {
        self.square_root_of_population_standard_deviation_regular_hours_as("stdDevPop_regular_hours")
    }

    pub fn square_root_of_population_standard_deviation_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("regular_hours", alias)
    }

    pub fn sample_variance_regular_hours(self) -> Self {
        self.sample_variance_regular_hours_as("varSamp_regular_hours")
    }

    pub fn sample_variance_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("regular_hours", alias)
    }

    pub fn sample_population_variance_regular_hours(self) -> Self {
        self.sample_population_variance_regular_hours_as("varPop_regular_hours")
    }

    pub fn sample_population_variance_regular_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("regular_hours", alias)
    }

    pub fn unselect_regular_hours(mut self) -> Self {
        self.query.projection.retain(|field| field != "regular_hours");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "regular_hours");
        self
    }


    pub fn with_regular_hours(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "regular_hours",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_regular_hours_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "regular_hours",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_regular_hours_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("regular_hours", value));
        self
    }



    pub fn with_regular_hours_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("regular_hours", value));
        self
    }

    pub fn with_regular_hours_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("regular_hours", value));
        self
    }

    pub fn with_regular_hours_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("regular_hours", value));
        self
    }

    pub fn with_regular_hours_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("regular_hours", value));
        self
    }

    pub fn with_regular_hours_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("regular_hours", value));
        self
    }

    pub fn with_regular_hours_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("regular_hours", lower, upper));
        self
    }

    pub fn with_regular_hours_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "regular_hours",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_regular_hours_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "regular_hours",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_regular_hours_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "regular_hours",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_regular_hours_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("regular_hours", value));
        self
    }

    pub fn with_regular_hours_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("regular_hours", value));
        self
    }

    pub fn with_regular_hours_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("regular_hours"));
        self
    }



    pub fn with_regular_hours_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("regular_hours"));
        self
    }


    pub fn order_by_regular_hours_asc(mut self) -> Self {
        self.query = self.query.order_asc("regular_hours");
        self
    }

    pub fn order_by_regular_hours_desc(mut self) -> Self {
        self.query = self.query.order_desc("regular_hours");
        self
    }

    pub fn order_by_regular_hours_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("regular_hours");
        self
    }

    pub fn order_by_regular_hours_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("regular_hours");
        self
    }


    pub fn select_overtime_hours(mut self) -> Self {
        self.query = self.query.project("overtime_hours");
        self
    }

    pub fn project_overtime_hours(self) -> Self {
        self.select_overtime_hours()
    }

    pub fn select_overtime_hours_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_overtime_hours_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_overtime_hours_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("overtime_hours", raw_sql_segment));
        self
    }

    pub fn select_overtime_hours_with_function(self, function: AggregateFunction) -> Self {
        self.select_overtime_hours_as_with_function("overtime_hours", function)
    }

    pub fn select_overtime_hours_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("overtime_hours", alias, function)
    }

    pub fn group_by_overtime_hours(self) -> Self {
        self.group_by("overtime_hours")
    }

    pub fn group_by_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("overtime_hours");
        request.query = request
            .query
            .project_expr(alias, Expr::column("overtime_hours"));
        request
    }

    pub fn group_by_overtime_hours_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("overtime_hours")
            .aggregate_with_function("overtime_hours", alias, function)
    }

    pub fn count_overtime_hours(self) -> Self {
        self.count_overtime_hours_as("overtime_hours_count")
    }

    pub fn count_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("overtime_hours", alias)
    }

    pub fn sum_overtime_hours(self) -> Self {
        self.sum_overtime_hours_as("sum_overtime_hours")
    }

    pub fn sum_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("overtime_hours", alias)
    }

    pub fn avg_overtime_hours(self) -> Self {
        self.avg_overtime_hours_as("avg_overtime_hours")
    }

    pub fn avg_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("overtime_hours", alias)
    }

    pub fn min_overtime_hours(self) -> Self {
        self.min_overtime_hours_as("min_overtime_hours")
    }

    pub fn min_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("overtime_hours", alias)
    }

    pub fn max_overtime_hours(self) -> Self {
        self.max_overtime_hours_as("max_overtime_hours")
    }

    pub fn max_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("overtime_hours", alias)
    }

    pub fn standard_deviation_overtime_hours(self) -> Self {
        self.standard_deviation_overtime_hours_as("stdDev_overtime_hours")
    }

    pub fn standard_deviation_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("overtime_hours", alias)
    }

    pub fn square_root_of_population_standard_deviation_overtime_hours(self) -> Self {
        self.square_root_of_population_standard_deviation_overtime_hours_as("stdDevPop_overtime_hours")
    }

    pub fn square_root_of_population_standard_deviation_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("overtime_hours", alias)
    }

    pub fn sample_variance_overtime_hours(self) -> Self {
        self.sample_variance_overtime_hours_as("varSamp_overtime_hours")
    }

    pub fn sample_variance_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("overtime_hours", alias)
    }

    pub fn sample_population_variance_overtime_hours(self) -> Self {
        self.sample_population_variance_overtime_hours_as("varPop_overtime_hours")
    }

    pub fn sample_population_variance_overtime_hours_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("overtime_hours", alias)
    }

    pub fn unselect_overtime_hours(mut self) -> Self {
        self.query.projection.retain(|field| field != "overtime_hours");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "overtime_hours");
        self
    }


    pub fn with_overtime_hours(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "overtime_hours",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_overtime_hours_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "overtime_hours",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_overtime_hours_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("overtime_hours", value));
        self
    }



    pub fn with_overtime_hours_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("overtime_hours", lower, upper));
        self
    }

    pub fn with_overtime_hours_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "overtime_hours",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_overtime_hours_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "overtime_hours",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_overtime_hours_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "overtime_hours",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_overtime_hours_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("overtime_hours", value));
        self
    }

    pub fn with_overtime_hours_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("overtime_hours"));
        self
    }



    pub fn with_overtime_hours_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("overtime_hours"));
        self
    }


    pub fn order_by_overtime_hours_asc(mut self) -> Self {
        self.query = self.query.order_asc("overtime_hours");
        self
    }

    pub fn order_by_overtime_hours_desc(mut self) -> Self {
        self.query = self.query.order_desc("overtime_hours");
        self
    }

    pub fn order_by_overtime_hours_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("overtime_hours");
        self
    }

    pub fn order_by_overtime_hours_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("overtime_hours");
        self
    }


    pub fn select_hourly_rate(mut self) -> Self {
        self.query = self.query.project("hourly_rate");
        self
    }

    pub fn project_hourly_rate(self) -> Self {
        self.select_hourly_rate()
    }

    pub fn select_hourly_rate_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_hourly_rate_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_hourly_rate_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("hourly_rate", raw_sql_segment));
        self
    }

    pub fn select_hourly_rate_with_function(self, function: AggregateFunction) -> Self {
        self.select_hourly_rate_as_with_function("hourly_rate", function)
    }

    pub fn select_hourly_rate_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("hourly_rate", alias, function)
    }

    pub fn group_by_hourly_rate(self) -> Self {
        self.group_by("hourly_rate")
    }

    pub fn group_by_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("hourly_rate");
        request.query = request
            .query
            .project_expr(alias, Expr::column("hourly_rate"));
        request
    }

    pub fn group_by_hourly_rate_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("hourly_rate")
            .aggregate_with_function("hourly_rate", alias, function)
    }

    pub fn count_hourly_rate(self) -> Self {
        self.count_hourly_rate_as("hourly_rate_count")
    }

    pub fn count_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("hourly_rate", alias)
    }

    pub fn sum_hourly_rate(self) -> Self {
        self.sum_hourly_rate_as("sum_hourly_rate")
    }

    pub fn sum_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("hourly_rate", alias)
    }

    pub fn avg_hourly_rate(self) -> Self {
        self.avg_hourly_rate_as("avg_hourly_rate")
    }

    pub fn avg_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("hourly_rate", alias)
    }

    pub fn min_hourly_rate(self) -> Self {
        self.min_hourly_rate_as("min_hourly_rate")
    }

    pub fn min_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("hourly_rate", alias)
    }

    pub fn max_hourly_rate(self) -> Self {
        self.max_hourly_rate_as("max_hourly_rate")
    }

    pub fn max_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("hourly_rate", alias)
    }

    pub fn standard_deviation_hourly_rate(self) -> Self {
        self.standard_deviation_hourly_rate_as("stdDev_hourly_rate")
    }

    pub fn standard_deviation_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("hourly_rate", alias)
    }

    pub fn square_root_of_population_standard_deviation_hourly_rate(self) -> Self {
        self.square_root_of_population_standard_deviation_hourly_rate_as("stdDevPop_hourly_rate")
    }

    pub fn square_root_of_population_standard_deviation_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("hourly_rate", alias)
    }

    pub fn sample_variance_hourly_rate(self) -> Self {
        self.sample_variance_hourly_rate_as("varSamp_hourly_rate")
    }

    pub fn sample_variance_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("hourly_rate", alias)
    }

    pub fn sample_population_variance_hourly_rate(self) -> Self {
        self.sample_population_variance_hourly_rate_as("varPop_hourly_rate")
    }

    pub fn sample_population_variance_hourly_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("hourly_rate", alias)
    }

    pub fn unselect_hourly_rate(mut self) -> Self {
        self.query.projection.retain(|field| field != "hourly_rate");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "hourly_rate");
        self
    }


    pub fn with_hourly_rate(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "hourly_rate",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_hourly_rate_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "hourly_rate",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_hourly_rate_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("hourly_rate", value));
        self
    }



    pub fn with_hourly_rate_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("hourly_rate", lower, upper));
        self
    }

    pub fn with_hourly_rate_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "hourly_rate",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_hourly_rate_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "hourly_rate",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_hourly_rate_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "hourly_rate",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_hourly_rate_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("hourly_rate", value));
        self
    }

    pub fn with_hourly_rate_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("hourly_rate"));
        self
    }



    pub fn with_hourly_rate_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("hourly_rate"));
        self
    }


    pub fn order_by_hourly_rate_asc(mut self) -> Self {
        self.query = self.query.order_asc("hourly_rate");
        self
    }

    pub fn order_by_hourly_rate_desc(mut self) -> Self {
        self.query = self.query.order_desc("hourly_rate");
        self
    }

    pub fn order_by_hourly_rate_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("hourly_rate");
        self
    }

    pub fn order_by_hourly_rate_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("hourly_rate");
        self
    }


    pub fn select_gross_pay(mut self) -> Self {
        self.query = self.query.project("gross_pay");
        self
    }

    pub fn project_gross_pay(self) -> Self {
        self.select_gross_pay()
    }

    pub fn select_gross_pay_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_gross_pay_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_gross_pay_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("gross_pay", raw_sql_segment));
        self
    }

    pub fn select_gross_pay_with_function(self, function: AggregateFunction) -> Self {
        self.select_gross_pay_as_with_function("gross_pay", function)
    }

    pub fn select_gross_pay_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("gross_pay", alias, function)
    }

    pub fn group_by_gross_pay(self) -> Self {
        self.group_by("gross_pay")
    }

    pub fn group_by_gross_pay_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("gross_pay");
        request.query = request
            .query
            .project_expr(alias, Expr::column("gross_pay"));
        request
    }

    pub fn group_by_gross_pay_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("gross_pay")
            .aggregate_with_function("gross_pay", alias, function)
    }

    pub fn count_gross_pay(self) -> Self {
        self.count_gross_pay_as("gross_pay_count")
    }

    pub fn count_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("gross_pay", alias)
    }

    pub fn sum_gross_pay(self) -> Self {
        self.sum_gross_pay_as("sum_gross_pay")
    }

    pub fn sum_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("gross_pay", alias)
    }

    pub fn avg_gross_pay(self) -> Self {
        self.avg_gross_pay_as("avg_gross_pay")
    }

    pub fn avg_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("gross_pay", alias)
    }

    pub fn min_gross_pay(self) -> Self {
        self.min_gross_pay_as("min_gross_pay")
    }

    pub fn min_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("gross_pay", alias)
    }

    pub fn max_gross_pay(self) -> Self {
        self.max_gross_pay_as("max_gross_pay")
    }

    pub fn max_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("gross_pay", alias)
    }

    pub fn standard_deviation_gross_pay(self) -> Self {
        self.standard_deviation_gross_pay_as("stdDev_gross_pay")
    }

    pub fn standard_deviation_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("gross_pay", alias)
    }

    pub fn square_root_of_population_standard_deviation_gross_pay(self) -> Self {
        self.square_root_of_population_standard_deviation_gross_pay_as("stdDevPop_gross_pay")
    }

    pub fn square_root_of_population_standard_deviation_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("gross_pay", alias)
    }

    pub fn sample_variance_gross_pay(self) -> Self {
        self.sample_variance_gross_pay_as("varSamp_gross_pay")
    }

    pub fn sample_variance_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("gross_pay", alias)
    }

    pub fn sample_population_variance_gross_pay(self) -> Self {
        self.sample_population_variance_gross_pay_as("varPop_gross_pay")
    }

    pub fn sample_population_variance_gross_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("gross_pay", alias)
    }

    pub fn unselect_gross_pay(mut self) -> Self {
        self.query.projection.retain(|field| field != "gross_pay");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "gross_pay");
        self
    }


    pub fn with_gross_pay(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "gross_pay",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_gross_pay_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "gross_pay",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_gross_pay_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("gross_pay", value));
        self
    }



    pub fn with_gross_pay_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("gross_pay", value));
        self
    }

    pub fn with_gross_pay_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("gross_pay", value));
        self
    }

    pub fn with_gross_pay_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("gross_pay", value));
        self
    }

    pub fn with_gross_pay_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("gross_pay", value));
        self
    }

    pub fn with_gross_pay_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("gross_pay", value));
        self
    }

    pub fn with_gross_pay_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("gross_pay", lower, upper));
        self
    }

    pub fn with_gross_pay_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "gross_pay",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_gross_pay_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "gross_pay",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_gross_pay_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "gross_pay",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_gross_pay_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("gross_pay", value));
        self
    }

    pub fn with_gross_pay_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("gross_pay", value));
        self
    }

    pub fn with_gross_pay_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("gross_pay"));
        self
    }



    pub fn with_gross_pay_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("gross_pay"));
        self
    }


    pub fn order_by_gross_pay_asc(mut self) -> Self {
        self.query = self.query.order_asc("gross_pay");
        self
    }

    pub fn order_by_gross_pay_desc(mut self) -> Self {
        self.query = self.query.order_desc("gross_pay");
        self
    }

    pub fn order_by_gross_pay_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("gross_pay");
        self
    }

    pub fn order_by_gross_pay_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("gross_pay");
        self
    }


    pub fn select_deductions(mut self) -> Self {
        self.query = self.query.project("deductions");
        self
    }

    pub fn project_deductions(self) -> Self {
        self.select_deductions()
    }

    pub fn select_deductions_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_deductions_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_deductions_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("deductions", raw_sql_segment));
        self
    }

    pub fn select_deductions_with_function(self, function: AggregateFunction) -> Self {
        self.select_deductions_as_with_function("deductions", function)
    }

    pub fn select_deductions_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("deductions", alias, function)
    }

    pub fn group_by_deductions(self) -> Self {
        self.group_by("deductions")
    }

    pub fn group_by_deductions_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("deductions");
        request.query = request
            .query
            .project_expr(alias, Expr::column("deductions"));
        request
    }

    pub fn group_by_deductions_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("deductions")
            .aggregate_with_function("deductions", alias, function)
    }

    pub fn count_deductions(self) -> Self {
        self.count_deductions_as("deductions_count")
    }

    pub fn count_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("deductions", alias)
    }

    pub fn sum_deductions(self) -> Self {
        self.sum_deductions_as("sum_deductions")
    }

    pub fn sum_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("deductions", alias)
    }

    pub fn avg_deductions(self) -> Self {
        self.avg_deductions_as("avg_deductions")
    }

    pub fn avg_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("deductions", alias)
    }

    pub fn min_deductions(self) -> Self {
        self.min_deductions_as("min_deductions")
    }

    pub fn min_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("deductions", alias)
    }

    pub fn max_deductions(self) -> Self {
        self.max_deductions_as("max_deductions")
    }

    pub fn max_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("deductions", alias)
    }

    pub fn standard_deviation_deductions(self) -> Self {
        self.standard_deviation_deductions_as("stdDev_deductions")
    }

    pub fn standard_deviation_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("deductions", alias)
    }

    pub fn square_root_of_population_standard_deviation_deductions(self) -> Self {
        self.square_root_of_population_standard_deviation_deductions_as("stdDevPop_deductions")
    }

    pub fn square_root_of_population_standard_deviation_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("deductions", alias)
    }

    pub fn sample_variance_deductions(self) -> Self {
        self.sample_variance_deductions_as("varSamp_deductions")
    }

    pub fn sample_variance_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("deductions", alias)
    }

    pub fn sample_population_variance_deductions(self) -> Self {
        self.sample_population_variance_deductions_as("varPop_deductions")
    }

    pub fn sample_population_variance_deductions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("deductions", alias)
    }

    pub fn unselect_deductions(mut self) -> Self {
        self.query.projection.retain(|field| field != "deductions");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "deductions");
        self
    }


    pub fn with_deductions(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "deductions",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_deductions_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "deductions",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_deductions_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("deductions", value));
        self
    }



    pub fn with_deductions_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("deductions", value));
        self
    }

    pub fn with_deductions_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("deductions", value));
        self
    }

    pub fn with_deductions_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("deductions", value));
        self
    }

    pub fn with_deductions_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("deductions", value));
        self
    }

    pub fn with_deductions_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("deductions", value));
        self
    }

    pub fn with_deductions_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("deductions", lower, upper));
        self
    }

    pub fn with_deductions_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "deductions",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_deductions_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "deductions",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_deductions_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "deductions",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_deductions_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("deductions", value));
        self
    }

    pub fn with_deductions_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("deductions", value));
        self
    }

    pub fn with_deductions_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("deductions"));
        self
    }



    pub fn with_deductions_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("deductions"));
        self
    }


    pub fn order_by_deductions_asc(mut self) -> Self {
        self.query = self.query.order_asc("deductions");
        self
    }

    pub fn order_by_deductions_desc(mut self) -> Self {
        self.query = self.query.order_desc("deductions");
        self
    }

    pub fn order_by_deductions_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("deductions");
        self
    }

    pub fn order_by_deductions_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("deductions");
        self
    }


    pub fn select_net_pay(mut self) -> Self {
        self.query = self.query.project("net_pay");
        self
    }

    pub fn project_net_pay(self) -> Self {
        self.select_net_pay()
    }

    pub fn select_net_pay_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_net_pay_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_net_pay_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("net_pay", raw_sql_segment));
        self
    }

    pub fn select_net_pay_with_function(self, function: AggregateFunction) -> Self {
        self.select_net_pay_as_with_function("net_pay", function)
    }

    pub fn select_net_pay_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("net_pay", alias, function)
    }

    pub fn group_by_net_pay(self) -> Self {
        self.group_by("net_pay")
    }

    pub fn group_by_net_pay_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("net_pay");
        request.query = request
            .query
            .project_expr(alias, Expr::column("net_pay"));
        request
    }

    pub fn group_by_net_pay_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("net_pay")
            .aggregate_with_function("net_pay", alias, function)
    }

    pub fn count_net_pay(self) -> Self {
        self.count_net_pay_as("net_pay_count")
    }

    pub fn count_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("net_pay", alias)
    }

    pub fn sum_net_pay(self) -> Self {
        self.sum_net_pay_as("sum_net_pay")
    }

    pub fn sum_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("net_pay", alias)
    }

    pub fn avg_net_pay(self) -> Self {
        self.avg_net_pay_as("avg_net_pay")
    }

    pub fn avg_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("net_pay", alias)
    }

    pub fn min_net_pay(self) -> Self {
        self.min_net_pay_as("min_net_pay")
    }

    pub fn min_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("net_pay", alias)
    }

    pub fn max_net_pay(self) -> Self {
        self.max_net_pay_as("max_net_pay")
    }

    pub fn max_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("net_pay", alias)
    }

    pub fn standard_deviation_net_pay(self) -> Self {
        self.standard_deviation_net_pay_as("stdDev_net_pay")
    }

    pub fn standard_deviation_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("net_pay", alias)
    }

    pub fn square_root_of_population_standard_deviation_net_pay(self) -> Self {
        self.square_root_of_population_standard_deviation_net_pay_as("stdDevPop_net_pay")
    }

    pub fn square_root_of_population_standard_deviation_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("net_pay", alias)
    }

    pub fn sample_variance_net_pay(self) -> Self {
        self.sample_variance_net_pay_as("varSamp_net_pay")
    }

    pub fn sample_variance_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("net_pay", alias)
    }

    pub fn sample_population_variance_net_pay(self) -> Self {
        self.sample_population_variance_net_pay_as("varPop_net_pay")
    }

    pub fn sample_population_variance_net_pay_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("net_pay", alias)
    }

    pub fn unselect_net_pay(mut self) -> Self {
        self.query.projection.retain(|field| field != "net_pay");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "net_pay");
        self
    }


    pub fn with_net_pay(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "net_pay",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_net_pay_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "net_pay",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_net_pay_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("net_pay", value));
        self
    }



    pub fn with_net_pay_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("net_pay", value));
        self
    }

    pub fn with_net_pay_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("net_pay", value));
        self
    }

    pub fn with_net_pay_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("net_pay", value));
        self
    }

    pub fn with_net_pay_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("net_pay", value));
        self
    }

    pub fn with_net_pay_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("net_pay", value));
        self
    }

    pub fn with_net_pay_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("net_pay", lower, upper));
        self
    }

    pub fn with_net_pay_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "net_pay",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_net_pay_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "net_pay",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_net_pay_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "net_pay",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_net_pay_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("net_pay", value));
        self
    }

    pub fn with_net_pay_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("net_pay", value));
        self
    }

    pub fn with_net_pay_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("net_pay"));
        self
    }



    pub fn with_net_pay_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("net_pay"));
        self
    }


    pub fn order_by_net_pay_asc(mut self) -> Self {
        self.query = self.query.order_asc("net_pay");
        self
    }

    pub fn order_by_net_pay_desc(mut self) -> Self {
        self.query = self.query.order_desc("net_pay");
        self
    }

    pub fn order_by_net_pay_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("net_pay");
        self
    }

    pub fn order_by_net_pay_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("net_pay");
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
    pub fn filter_by_employee(mut self, value: impl EntityReference) -> Self {
        self.query = self.query.and_filter(Expr::eq("employee_id", value.entity_id_value()));
        self
    }

    pub fn with_employee_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "employee_id",
            <crate::Employee as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("employee", selection));
        self
    }


    pub fn without_employee_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "employee_id",
            <crate::Employee as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("employee", selection));
        self
    }


    pub fn have_employee(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("employee_id"));
        self
    }

    pub fn have_no_employee(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("employee_id"));
        self
    }


    pub fn group_by_employee(self) -> Self {
        self.group_by("employee_id")
    }

    pub fn group_by_employee_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("employee_id");
        request.query = request
            .query
            .project_expr(alias, Expr::column("employee_id"));
        request
    }

    pub fn group_by_employee_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("employee_id")
            .aggregate_with_function("employee_id", alias, function)
    }

    pub fn group_by_employee_with(mut self, request: impl Into<QuerySelection>) -> Self {
        self.query = self.query.group_by("employee_id");
        self.query_options.object_group_bys.push(ObjectGroupBy::new(
            "employee",
            "employee_id",
            request,
        ));
        self
    }

    pub fn group_by_employee_with_details(self) -> Self {
        self.group_by_employee_with_details_from(crate::Q::employees().unlimited())
    }

    pub fn group_by_employee_with_details_from(self, request: impl Into<QuerySelection>) -> Self {
        self.group_by_employee_with(request)
    }


    pub fn roll_up_to_employee(self) -> Self {
        self.roll_up_to_employee_with(crate::Q::employees().unlimited())
    }

    pub fn roll_up_to_employee_with(self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.with_employee_matching(selection.clone())
            .group_by_employee_with(selection)
    }

    pub fn count_employee(self) -> Self {
        self.count_employee_as("employee_count")
    }

    pub fn count_employee_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("employee_id", alias)
    }

    pub fn unselect_employee(mut self) -> Self {
        self.query.projection.retain(|field| field != "employee_id");
        self.query.relations.retain(|relation| relation.name != "employee");
        self
    }
    pub fn select_employee(mut self) -> Self {
        self.query = self.query.relation("employee");
        self
    }

    pub fn select_employee_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("employee", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("employee", selection));
        self
}

    pub fn facet_by_employee_as(self, facet_name: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.facet_by_employee_as_with_options(facet_name, request, true)
    }

    pub fn facet_by_employee_as_with_options(
        mut self,
        facet_name: impl Into<String>,
        request: impl Into<QuerySelection>,
        include_all_facets: bool,
    ) -> Self {
        self.query_options.facets.push(FacetRequest::new(
            facet_name,
            "employee",
            request,
            include_all_facets,
        ));
        self
    }
}

impl<R> Default for PayrollCalculationRequest<R> {
    fn default() -> Self {
        Self::new()
    }
}

impl<R> From< PayrollCalculationRequest<R> > for SelectQuery {
    fn from(request: PayrollCalculationRequest<R>) -> Self {
        QuerySelection::from(request).into_query()
    }
}

impl<R> From< PayrollCalculationRequest<R> > for QuerySelection {
    fn from(request: PayrollCalculationRequest<R>) -> Self {
        Self {
            query: request.query,
            relation_selections: request.relation_selections,
            relation_filters: request.relation_filters,
            child_enhancements: request.child_enhancements,
            query_options: request.query_options,
        }
    }
}


impl<'a, C> crate::request_support::AuditedSave<'a, C> for teaql_core::Audited<crate::PayrollCalculation> 
where C: crate::request_support::TeaqlRepositoryProvider + ?Sized + 'a
{
    type Error = crate::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>;
    fn save(self, ctx: &'a C) -> std::pin::Pin<Box<dyn std::future::Future<Output = Result<teaql_runtime::GraphNode, Self::Error>> + '_>> {
        Box::pin(async move {
            teaql_runtime::save_audited_ledger_entity(self, ctx.user_context())
                .await
                .map_err(DataServiceError::Runtime)
        })
    }
}

impl<R: teaql_core::Entity> crate::PurposedQuery<PayrollCalculationRequest<R>> {
    pub fn new_entity<C>(&self, ctx: &C) -> crate::PayrollCalculation
    where
        C: crate::TeaqlRuntime + ?Sized,
    {
        crate::PayrollCalculation::runtime_new(ctx.user_context().entity_root())
    }

    fn into_inner_with_trace(mut self) -> PayrollCalculationRequest<R> {
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
    ) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_page(ctx, offset, limit).await
    }

    pub async fn execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_exists(ctx).await
    }

    pub async fn execute_for_list<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_list(ctx).await
    }

    /// Execute query in streaming mode (chunked).
    /// Returns a Vec of StreamChunk, each containing up to chunk_size rows.
    /// Set chunk size via .stream(chunk_size) or .stream_default() on the query.
    pub async fn execute_for_stream<'a, C>(self, ctx: &'a C) -> Result<Vec<teaql_data_service::StreamChunk>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_stream(ctx).await
    }

    pub async fn execute_for_first<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_first(ctx).await
    }

    pub async fn execute_for_one<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_one(ctx).await
    }


    pub async fn execute_for_records<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_records(ctx).await
    }

    pub async fn execute_for_record<'a, C>(self, ctx: &'a C) -> Result<Option<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_record(ctx).await
    }

    pub async fn execute_for_count<'a, C>(self, ctx: &'a C) -> Result<u64, crate::request_support::TeaqlDataServiceError<C::PayrollCalculationRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_count(ctx).await
    }
}
