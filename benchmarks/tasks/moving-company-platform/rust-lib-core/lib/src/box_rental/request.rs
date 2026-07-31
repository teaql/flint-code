use std::marker::PhantomData;

use serde_json::Value as JsonValue;
use teaql_core::{Aggregate, AggregateFunction, EntityDescriptor, Expr, Record, SelectQuery, SmartList};
use teaql_runtime::{DataServiceError, RuntimeError};

use crate::request_support::*;

impl EntityReference for crate::BoxRental {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(&self)
    }
}

impl EntityReference for &crate::BoxRental {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(self)
    }
}

// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/box_rental
#[derive(Debug)]
pub struct BoxRentalRequest<R = crate::BoxRental> {
    query: SelectQuery,
    relation_selections: Vec<RelationSelection>,
    relation_filters: Vec<RelationFilter>,
    child_enhancements: Vec<QuerySelection>,
    query_options: QueryOptions,
    marker: PhantomData<R>,
}

impl<R> Clone for BoxRentalRequest<R> {
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

impl<R> BoxRentalRequest<R> {
    pub(crate) fn new() -> Self {
        Self {
            query: SelectQuery::new("BoxRental")
                .project("id")
                .project("version"),
            relation_selections: Vec::new(),
            relation_filters: Vec::new(),
            child_enhancements: Vec::new(),
            query_options: QueryOptions::default(),
            marker: PhantomData,
        }
    }

    pub fn return_type<T>(self) -> BoxRentalRequest<T> {
        BoxRentalRequest {
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let repository = ctx
            .box_rental_repository()
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
    ) -> Result<Vec<teaql_data_service::StreamChunk>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .box_rental_repository()
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
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
    ) -> Result<u64, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .box_rental_repository()
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
            .ok_or_else(|| DataServiceError::Runtime(RuntimeError::Graph(format!("count result for BoxRental is missing or not numeric"))))
    }

    pub(crate) async fn _execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .box_rental_repository()
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
    ) -> Result<SmartList<Record>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .box_rental_repository()
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
    ) -> Result<Option<Record>, TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
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
            "quantity" => Some("quantity"),
            "rental_start" => Some("rental_start"),
            "rental_end" => Some("rental_end"),
            "total_cost" => Some("total_cost"),
            "create_time" => Some("create_time"),
            "update_time" => Some("update_time"),
            "version" => Some("version"),
            "customer" | "customer_id" => Some("customer_id"),
            "box_type" | "box_type_id" => Some("box_type_id"),
            _ => None,
        }
    }

    fn apply_dynamic_json_chain_filter(self, head: &str, tail: &str, value: &JsonValue) -> Self {
        let _ = (tail, value);
        match head {
            "customer" => {
                self.with_customer_matching(
                    crate::Q::customers_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "box_type" => {
                self.with_box_type_matching(
                    crate::Q::box_types_minimal()
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
        self.query = self.query.project("quantity");
        self.query = self.query.project("rental_start");
        self.query = self.query.project("rental_end");
        self.query = self.query.project("total_cost");
        self.query = self.query.project("create_time");
        self.query = self.query.project("update_time");
        self.query = self.query.project("version");
        self.query = self.query.project("customer_id");
        self.query = self.query.project("box_type_id");
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
        request = request.select_customer();
        request = request.select_box_type();
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


    pub fn select_quantity(mut self) -> Self {
        self.query = self.query.project("quantity");
        self
    }

    pub fn project_quantity(self) -> Self {
        self.select_quantity()
    }

    pub fn select_quantity_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_quantity_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_quantity_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("quantity", raw_sql_segment));
        self
    }

    pub fn select_quantity_with_function(self, function: AggregateFunction) -> Self {
        self.select_quantity_as_with_function("quantity", function)
    }

    pub fn select_quantity_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("quantity", alias, function)
    }

    pub fn group_by_quantity(self) -> Self {
        self.group_by("quantity")
    }

    pub fn group_by_quantity_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("quantity");
        request.query = request
            .query
            .project_expr(alias, Expr::column("quantity"));
        request
    }

    pub fn group_by_quantity_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("quantity")
            .aggregate_with_function("quantity", alias, function)
    }

    pub fn count_quantity(self) -> Self {
        self.count_quantity_as("quantity_count")
    }

    pub fn count_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("quantity", alias)
    }

    pub fn sum_quantity(self) -> Self {
        self.sum_quantity_as("sum_quantity")
    }

    pub fn sum_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("quantity", alias)
    }

    pub fn avg_quantity(self) -> Self {
        self.avg_quantity_as("avg_quantity")
    }

    pub fn avg_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("quantity", alias)
    }

    pub fn min_quantity(self) -> Self {
        self.min_quantity_as("min_quantity")
    }

    pub fn min_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("quantity", alias)
    }

    pub fn max_quantity(self) -> Self {
        self.max_quantity_as("max_quantity")
    }

    pub fn max_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("quantity", alias)
    }

    pub fn standard_deviation_quantity(self) -> Self {
        self.standard_deviation_quantity_as("stdDev_quantity")
    }

    pub fn standard_deviation_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("quantity", alias)
    }

    pub fn square_root_of_population_standard_deviation_quantity(self) -> Self {
        self.square_root_of_population_standard_deviation_quantity_as("stdDevPop_quantity")
    }

    pub fn square_root_of_population_standard_deviation_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("quantity", alias)
    }

    pub fn sample_variance_quantity(self) -> Self {
        self.sample_variance_quantity_as("varSamp_quantity")
    }

    pub fn sample_variance_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("quantity", alias)
    }

    pub fn sample_population_variance_quantity(self) -> Self {
        self.sample_population_variance_quantity_as("varPop_quantity")
    }

    pub fn sample_population_variance_quantity_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("quantity", alias)
    }

    pub fn unselect_quantity(mut self) -> Self {
        self.query.projection.retain(|field| field != "quantity");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "quantity");
        self
    }


    pub fn with_quantity(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "quantity",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_quantity_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "quantity",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_quantity_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("quantity", value));
        self
    }



    pub fn with_quantity_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("quantity", value));
        self
    }

    pub fn with_quantity_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("quantity", value));
        self
    }

    pub fn with_quantity_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("quantity", value));
        self
    }

    pub fn with_quantity_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("quantity", value));
        self
    }

    pub fn with_quantity_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("quantity", value));
        self
    }

    pub fn with_quantity_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("quantity", lower, upper));
        self
    }

    pub fn with_quantity_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "quantity",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_quantity_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "quantity",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_quantity_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "quantity",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_quantity_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("quantity", value));
        self
    }

    pub fn with_quantity_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("quantity", value));
        self
    }

    pub fn with_quantity_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("quantity"));
        self
    }



    pub fn with_quantity_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("quantity"));
        self
    }


    pub fn order_by_quantity_asc(mut self) -> Self {
        self.query = self.query.order_asc("quantity");
        self
    }

    pub fn order_by_quantity_desc(mut self) -> Self {
        self.query = self.query.order_desc("quantity");
        self
    }

    pub fn order_by_quantity_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("quantity");
        self
    }

    pub fn order_by_quantity_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("quantity");
        self
    }


    pub fn select_rental_start(mut self) -> Self {
        self.query = self.query.project("rental_start");
        self
    }

    pub fn project_rental_start(self) -> Self {
        self.select_rental_start()
    }

    pub fn select_rental_start_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_rental_start_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_rental_start_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("rental_start", raw_sql_segment));
        self
    }

    pub fn group_by_rental_start(self) -> Self {
        self.group_by("rental_start")
    }

    pub fn group_by_rental_start_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("rental_start");
        request.query = request
            .query
            .project_expr(alias, Expr::column("rental_start"));
        request
    }

    pub fn group_by_rental_start_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("rental_start")
            .aggregate_with_function("rental_start", alias, function)
    }

    pub fn count_rental_start(self) -> Self {
        self.count_rental_start_as("rental_start_count")
    }

    pub fn count_rental_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("rental_start", alias)
    }

    pub fn sum_rental_start(self) -> Self {
        self.sum_rental_start_as("sum_rental_start")
    }

    pub fn sum_rental_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("rental_start", alias)
    }

    pub fn avg_rental_start(self) -> Self {
        self.avg_rental_start_as("avg_rental_start")
    }

    pub fn avg_rental_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("rental_start", alias)
    }

    pub fn min_rental_start(self) -> Self {
        self.min_rental_start_as("min_rental_start")
    }

    pub fn min_rental_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("rental_start", alias)
    }

    pub fn max_rental_start(self) -> Self {
        self.max_rental_start_as("max_rental_start")
    }

    pub fn max_rental_start_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("rental_start", alias)
    }

    pub fn unselect_rental_start(mut self) -> Self {
        self.query.projection.retain(|field| field != "rental_start");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "rental_start");
        self
    }


    pub fn with_rental_start(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "rental_start",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_rental_start_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "rental_start",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_rental_start_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("rental_start", value));
        self
    }



    pub fn with_rental_start_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("rental_start", value));
        self
    }

    pub fn with_rental_start_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("rental_start", value));
        self
    }

    pub fn with_rental_start_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("rental_start", value));
        self
    }

    pub fn with_rental_start_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("rental_start", value));
        self
    }

    pub fn with_rental_start_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("rental_start", value));
        self
    }

    pub fn with_rental_start_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("rental_start", lower, upper));
        self
    }

    pub fn with_rental_start_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "rental_start",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_rental_start_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "rental_start",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_rental_start_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "rental_start",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_rental_start_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("rental_start", value));
        self
    }

    pub fn with_rental_start_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("rental_start", value));
        self
    }

    pub fn with_rental_start_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("rental_start"));
        self
    }



    pub fn with_rental_start_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("rental_start"));
        self
    }


    pub fn order_by_rental_start_asc(mut self) -> Self {
        self.query = self.query.order_asc("rental_start");
        self
    }

    pub fn order_by_rental_start_desc(mut self) -> Self {
        self.query = self.query.order_desc("rental_start");
        self
    }

    pub fn order_by_rental_start_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("rental_start");
        self
    }

    pub fn order_by_rental_start_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("rental_start");
        self
    }


    pub fn select_rental_end(mut self) -> Self {
        self.query = self.query.project("rental_end");
        self
    }

    pub fn project_rental_end(self) -> Self {
        self.select_rental_end()
    }

    pub fn select_rental_end_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_rental_end_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_rental_end_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("rental_end", raw_sql_segment));
        self
    }

    pub fn group_by_rental_end(self) -> Self {
        self.group_by("rental_end")
    }

    pub fn group_by_rental_end_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("rental_end");
        request.query = request
            .query
            .project_expr(alias, Expr::column("rental_end"));
        request
    }

    pub fn group_by_rental_end_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("rental_end")
            .aggregate_with_function("rental_end", alias, function)
    }

    pub fn count_rental_end(self) -> Self {
        self.count_rental_end_as("rental_end_count")
    }

    pub fn count_rental_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("rental_end", alias)
    }

    pub fn sum_rental_end(self) -> Self {
        self.sum_rental_end_as("sum_rental_end")
    }

    pub fn sum_rental_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("rental_end", alias)
    }

    pub fn avg_rental_end(self) -> Self {
        self.avg_rental_end_as("avg_rental_end")
    }

    pub fn avg_rental_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("rental_end", alias)
    }

    pub fn min_rental_end(self) -> Self {
        self.min_rental_end_as("min_rental_end")
    }

    pub fn min_rental_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("rental_end", alias)
    }

    pub fn max_rental_end(self) -> Self {
        self.max_rental_end_as("max_rental_end")
    }

    pub fn max_rental_end_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("rental_end", alias)
    }

    pub fn unselect_rental_end(mut self) -> Self {
        self.query.projection.retain(|field| field != "rental_end");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "rental_end");
        self
    }


    pub fn with_rental_end(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "rental_end",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_rental_end_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "rental_end",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_rental_end_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("rental_end", value));
        self
    }



    pub fn with_rental_end_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("rental_end", value));
        self
    }

    pub fn with_rental_end_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("rental_end", value));
        self
    }

    pub fn with_rental_end_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("rental_end", value));
        self
    }

    pub fn with_rental_end_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("rental_end", value));
        self
    }

    pub fn with_rental_end_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("rental_end", value));
        self
    }

    pub fn with_rental_end_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("rental_end", lower, upper));
        self
    }

    pub fn with_rental_end_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "rental_end",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_rental_end_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "rental_end",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_rental_end_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "rental_end",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_rental_end_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("rental_end", value));
        self
    }

    pub fn with_rental_end_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("rental_end", value));
        self
    }

    pub fn with_rental_end_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("rental_end"));
        self
    }



    pub fn with_rental_end_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("rental_end"));
        self
    }


    pub fn order_by_rental_end_asc(mut self) -> Self {
        self.query = self.query.order_asc("rental_end");
        self
    }

    pub fn order_by_rental_end_desc(mut self) -> Self {
        self.query = self.query.order_desc("rental_end");
        self
    }

    pub fn order_by_rental_end_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("rental_end");
        self
    }

    pub fn order_by_rental_end_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("rental_end");
        self
    }


    pub fn select_total_cost(mut self) -> Self {
        self.query = self.query.project("total_cost");
        self
    }

    pub fn project_total_cost(self) -> Self {
        self.select_total_cost()
    }

    pub fn select_total_cost_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_total_cost_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_total_cost_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("total_cost", raw_sql_segment));
        self
    }

    pub fn select_total_cost_with_function(self, function: AggregateFunction) -> Self {
        self.select_total_cost_as_with_function("total_cost", function)
    }

    pub fn select_total_cost_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("total_cost", alias, function)
    }

    pub fn group_by_total_cost(self) -> Self {
        self.group_by("total_cost")
    }

    pub fn group_by_total_cost_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("total_cost");
        request.query = request
            .query
            .project_expr(alias, Expr::column("total_cost"));
        request
    }

    pub fn group_by_total_cost_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("total_cost")
            .aggregate_with_function("total_cost", alias, function)
    }

    pub fn count_total_cost(self) -> Self {
        self.count_total_cost_as("total_cost_count")
    }

    pub fn count_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("total_cost", alias)
    }

    pub fn sum_total_cost(self) -> Self {
        self.sum_total_cost_as("sum_total_cost")
    }

    pub fn sum_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("total_cost", alias)
    }

    pub fn avg_total_cost(self) -> Self {
        self.avg_total_cost_as("avg_total_cost")
    }

    pub fn avg_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("total_cost", alias)
    }

    pub fn min_total_cost(self) -> Self {
        self.min_total_cost_as("min_total_cost")
    }

    pub fn min_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("total_cost", alias)
    }

    pub fn max_total_cost(self) -> Self {
        self.max_total_cost_as("max_total_cost")
    }

    pub fn max_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("total_cost", alias)
    }

    pub fn standard_deviation_total_cost(self) -> Self {
        self.standard_deviation_total_cost_as("stdDev_total_cost")
    }

    pub fn standard_deviation_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("total_cost", alias)
    }

    pub fn square_root_of_population_standard_deviation_total_cost(self) -> Self {
        self.square_root_of_population_standard_deviation_total_cost_as("stdDevPop_total_cost")
    }

    pub fn square_root_of_population_standard_deviation_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("total_cost", alias)
    }

    pub fn sample_variance_total_cost(self) -> Self {
        self.sample_variance_total_cost_as("varSamp_total_cost")
    }

    pub fn sample_variance_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("total_cost", alias)
    }

    pub fn sample_population_variance_total_cost(self) -> Self {
        self.sample_population_variance_total_cost_as("varPop_total_cost")
    }

    pub fn sample_population_variance_total_cost_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("total_cost", alias)
    }

    pub fn unselect_total_cost(mut self) -> Self {
        self.query.projection.retain(|field| field != "total_cost");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "total_cost");
        self
    }


    pub fn with_total_cost(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "total_cost",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_total_cost_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "total_cost",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_total_cost_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("total_cost", value));
        self
    }



    pub fn with_total_cost_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("total_cost", value));
        self
    }

    pub fn with_total_cost_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("total_cost", value));
        self
    }

    pub fn with_total_cost_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("total_cost", value));
        self
    }

    pub fn with_total_cost_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("total_cost", value));
        self
    }

    pub fn with_total_cost_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("total_cost", value));
        self
    }

    pub fn with_total_cost_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("total_cost", lower, upper));
        self
    }

    pub fn with_total_cost_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "total_cost",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_total_cost_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "total_cost",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_total_cost_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "total_cost",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_total_cost_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("total_cost", value));
        self
    }

    pub fn with_total_cost_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("total_cost", value));
        self
    }

    pub fn with_total_cost_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("total_cost"));
        self
    }



    pub fn with_total_cost_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("total_cost"));
        self
    }


    pub fn order_by_total_cost_asc(mut self) -> Self {
        self.query = self.query.order_asc("total_cost");
        self
    }

    pub fn order_by_total_cost_desc(mut self) -> Self {
        self.query = self.query.order_desc("total_cost");
        self
    }

    pub fn order_by_total_cost_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("total_cost");
        self
    }

    pub fn order_by_total_cost_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("total_cost");
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
    pub fn filter_by_customer(mut self, value: impl EntityReference) -> Self {
        self.query = self.query.and_filter(Expr::eq("customer_id", value.entity_id_value()));
        self
    }

    pub fn with_customer_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "customer_id",
            <crate::Customer as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("customer", selection));
        self
    }


    pub fn without_customer_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "customer_id",
            <crate::Customer as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("customer", selection));
        self
    }


    pub fn have_customer(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("customer_id"));
        self
    }

    pub fn have_no_customer(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("customer_id"));
        self
    }


    pub fn group_by_customer(self) -> Self {
        self.group_by("customer_id")
    }

    pub fn group_by_customer_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("customer_id");
        request.query = request
            .query
            .project_expr(alias, Expr::column("customer_id"));
        request
    }

    pub fn group_by_customer_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("customer_id")
            .aggregate_with_function("customer_id", alias, function)
    }

    pub fn group_by_customer_with(mut self, request: impl Into<QuerySelection>) -> Self {
        self.query = self.query.group_by("customer_id");
        self.query_options.object_group_bys.push(ObjectGroupBy::new(
            "customer",
            "customer_id",
            request,
        ));
        self
    }

    pub fn group_by_customer_with_details(self) -> Self {
        self.group_by_customer_with_details_from(crate::Q::customers().unlimited())
    }

    pub fn group_by_customer_with_details_from(self, request: impl Into<QuerySelection>) -> Self {
        self.group_by_customer_with(request)
    }


    pub fn roll_up_to_customer(self) -> Self {
        self.roll_up_to_customer_with(crate::Q::customers().unlimited())
    }

    pub fn roll_up_to_customer_with(self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.with_customer_matching(selection.clone())
            .group_by_customer_with(selection)
    }

    pub fn count_customer(self) -> Self {
        self.count_customer_as("customer_count")
    }

    pub fn count_customer_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("customer_id", alias)
    }

    pub fn unselect_customer(mut self) -> Self {
        self.query.projection.retain(|field| field != "customer_id");
        self.query.relations.retain(|relation| relation.name != "customer");
        self
    }


    pub fn filter_by_box_type(mut self, value: impl EntityReference) -> Self {
        self.query = self.query.and_filter(Expr::eq("box_type_id", value.entity_id_value()));
        self
    }

    pub fn with_box_type_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "box_type_id",
            <crate::BoxType as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("box_type", selection));
        self
    }


    pub fn without_box_type_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "box_type_id",
            <crate::BoxType as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("box_type", selection));
        self
    }


    pub fn have_box_type(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("box_type_id"));
        self
    }

    pub fn have_no_box_type(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("box_type_id"));
        self
    }


    pub fn group_by_box_type(self) -> Self {
        self.group_by("box_type_id")
    }

    pub fn group_by_box_type_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("box_type_id");
        request.query = request
            .query
            .project_expr(alias, Expr::column("box_type_id"));
        request
    }

    pub fn group_by_box_type_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("box_type_id")
            .aggregate_with_function("box_type_id", alias, function)
    }

    pub fn group_by_box_type_with(mut self, request: impl Into<QuerySelection>) -> Self {
        self.query = self.query.group_by("box_type_id");
        self.query_options.object_group_bys.push(ObjectGroupBy::new(
            "box_type",
            "box_type_id",
            request,
        ));
        self
    }

    pub fn group_by_box_type_with_details(self) -> Self {
        self.group_by_box_type_with_details_from(crate::Q::box_types().unlimited())
    }

    pub fn group_by_box_type_with_details_from(self, request: impl Into<QuerySelection>) -> Self {
        self.group_by_box_type_with(request)
    }


    pub fn roll_up_to_box_type(self) -> Self {
        self.roll_up_to_box_type_with(crate::Q::box_types().unlimited())
    }

    pub fn roll_up_to_box_type_with(self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.with_box_type_matching(selection.clone())
            .group_by_box_type_with(selection)
    }

    pub fn count_box_type(self) -> Self {
        self.count_box_type_as("box_type_count")
    }

    pub fn count_box_type_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("box_type_id", alias)
    }

    pub fn unselect_box_type(mut self) -> Self {
        self.query.projection.retain(|field| field != "box_type_id");
        self.query.relations.retain(|relation| relation.name != "box_type");
        self
    }
    pub fn select_customer(mut self) -> Self {
        self.query = self.query.relation("customer");
        self
    }

    pub fn select_customer_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("customer", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("customer", selection));
        self
}

    pub fn facet_by_customer_as(self, facet_name: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.facet_by_customer_as_with_options(facet_name, request, true)
    }

    pub fn facet_by_customer_as_with_options(
        mut self,
        facet_name: impl Into<String>,
        request: impl Into<QuerySelection>,
        include_all_facets: bool,
    ) -> Self {
        self.query_options.facets.push(FacetRequest::new(
            facet_name,
            "customer",
            request,
            include_all_facets,
        ));
        self
    }

    pub fn select_box_type(mut self) -> Self {
        self.query = self.query.relation("box_type");
        self
    }

    pub fn select_box_type_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("box_type", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("box_type", selection));
        self
}

    pub fn facet_by_box_type_as(self, facet_name: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.facet_by_box_type_as_with_options(facet_name, request, true)
    }

    pub fn facet_by_box_type_as_with_options(
        mut self,
        facet_name: impl Into<String>,
        request: impl Into<QuerySelection>,
        include_all_facets: bool,
    ) -> Self {
        self.query_options.facets.push(FacetRequest::new(
            facet_name,
            "box_type",
            request,
            include_all_facets,
        ));
        self
    }
}

impl<R> Default for BoxRentalRequest<R> {
    fn default() -> Self {
        Self::new()
    }
}

impl<R> From< BoxRentalRequest<R> > for SelectQuery {
    fn from(request: BoxRentalRequest<R>) -> Self {
        QuerySelection::from(request).into_query()
    }
}

impl<R> From< BoxRentalRequest<R> > for QuerySelection {
    fn from(request: BoxRentalRequest<R>) -> Self {
        Self {
            query: request.query,
            relation_selections: request.relation_selections,
            relation_filters: request.relation_filters,
            child_enhancements: request.child_enhancements,
            query_options: request.query_options,
        }
    }
}


impl<'a, C> crate::request_support::AuditedSave<'a, C> for teaql_core::Audited<crate::BoxRental> 
where C: crate::request_support::TeaqlRepositoryProvider + ?Sized + 'a
{
    type Error = crate::TeaqlDataServiceError<C::BoxRentalRepository<'a>>;
    fn save(self, ctx: &'a C) -> std::pin::Pin<Box<dyn std::future::Future<Output = Result<teaql_runtime::GraphNode, Self::Error>> + '_>> {
        Box::pin(async move {
            teaql_runtime::save_audited_ledger_entity(self, ctx.user_context())
                .await
                .map_err(DataServiceError::Runtime)
        })
    }
}

impl<R: teaql_core::Entity> crate::PurposedQuery<BoxRentalRequest<R>> {
    pub fn new_entity<C>(&self, ctx: &C) -> crate::BoxRental
    where
        C: crate::TeaqlRuntime + ?Sized,
    {
        crate::BoxRental::runtime_new(ctx.user_context().entity_root())
    }

    fn into_inner_with_trace(mut self) -> BoxRentalRequest<R> {
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
    ) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_page(ctx, offset, limit).await
    }

    pub async fn execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_exists(ctx).await
    }

    pub async fn execute_for_list<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_list(ctx).await
    }

    /// Execute query in streaming mode (chunked).
    /// Returns a Vec of StreamChunk, each containing up to chunk_size rows.
    /// Set chunk size via .stream(chunk_size) or .stream_default() on the query.
    pub async fn execute_for_stream<'a, C>(self, ctx: &'a C) -> Result<Vec<teaql_data_service::StreamChunk>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_stream(ctx).await
    }

    pub async fn execute_for_first<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_first(ctx).await
    }

    pub async fn execute_for_one<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_one(ctx).await
    }


    pub async fn execute_for_records<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_records(ctx).await
    }

    pub async fn execute_for_record<'a, C>(self, ctx: &'a C) -> Result<Option<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_record(ctx).await
    }

    pub async fn execute_for_count<'a, C>(self, ctx: &'a C) -> Result<u64, crate::request_support::TeaqlDataServiceError<C::BoxRentalRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_count(ctx).await
    }
}
