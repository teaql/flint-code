use std::marker::PhantomData;

use serde_json::Value as JsonValue;
use teaql_core::{Aggregate, AggregateFunction, EntityDescriptor, Expr, Record, SelectQuery, SmartList};
use teaql_runtime::{DataServiceError, RuntimeError};

use crate::request_support::*;

impl EntityReference for crate::ConversionMetric {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(&self)
    }
}

impl EntityReference for &crate::ConversionMetric {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(self)
    }
}

// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/conversion_metric
#[derive(Debug)]
pub struct ConversionMetricRequest<R = crate::ConversionMetric> {
    query: SelectQuery,
    relation_selections: Vec<RelationSelection>,
    relation_filters: Vec<RelationFilter>,
    child_enhancements: Vec<QuerySelection>,
    query_options: QueryOptions,
    marker: PhantomData<R>,
}

impl<R> Clone for ConversionMetricRequest<R> {
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

impl<R> ConversionMetricRequest<R> {
    pub(crate) fn new() -> Self {
        Self {
            query: SelectQuery::new("ConversionMetric")
                .project("id")
                .project("version"),
            relation_selections: Vec::new(),
            relation_filters: Vec::new(),
            child_enhancements: Vec::new(),
            query_options: QueryOptions::default(),
            marker: PhantomData,
        }
    }

    pub fn return_type<T>(self) -> ConversionMetricRequest<T> {
        ConversionMetricRequest {
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let repository = ctx
            .conversion_metric_repository()
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
    ) -> Result<Vec<teaql_data_service::StreamChunk>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .conversion_metric_repository()
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
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
    ) -> Result<u64, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .conversion_metric_repository()
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
            .ok_or_else(|| DataServiceError::Runtime(RuntimeError::Graph(format!("count result for ConversionMetric is missing or not numeric"))))
    }

    pub(crate) async fn _execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .conversion_metric_repository()
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
    ) -> Result<SmartList<Record>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .conversion_metric_repository()
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
    ) -> Result<Option<Record>, TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
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
            "metric_date" => Some("metric_date"),
            "impressions" => Some("impressions"),
            "clicks" => Some("clicks"),
            "leads_generated" => Some("leads_generated"),
            "conversions" => Some("conversions"),
            "conversion_rate" => Some("conversion_rate"),
            "create_time" => Some("create_time"),
            "update_time" => Some("update_time"),
            "version" => Some("version"),
            "campaign" | "campaign_id" => Some("campaign_id"),
            _ => None,
        }
    }

    fn apply_dynamic_json_chain_filter(self, head: &str, tail: &str, value: &JsonValue) -> Self {
        let _ = (tail, value);
        match head {
            "campaign" => {
                self.with_campaign_matching(
                    crate::Q::campaigns_minimal()
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
        self.query = self.query.project("metric_date");
        self.query = self.query.project("impressions");
        self.query = self.query.project("clicks");
        self.query = self.query.project("leads_generated");
        self.query = self.query.project("conversions");
        self.query = self.query.project("conversion_rate");
        self.query = self.query.project("create_time");
        self.query = self.query.project("update_time");
        self.query = self.query.project("version");
        self.query = self.query.project("campaign_id");
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
        request = request.select_campaign();
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


    pub fn select_metric_date(mut self) -> Self {
        self.query = self.query.project("metric_date");
        self
    }

    pub fn project_metric_date(self) -> Self {
        self.select_metric_date()
    }

    pub fn select_metric_date_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_metric_date_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_metric_date_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("metric_date", raw_sql_segment));
        self
    }

    pub fn group_by_metric_date(self) -> Self {
        self.group_by("metric_date")
    }

    pub fn group_by_metric_date_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("metric_date");
        request.query = request
            .query
            .project_expr(alias, Expr::column("metric_date"));
        request
    }

    pub fn group_by_metric_date_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("metric_date")
            .aggregate_with_function("metric_date", alias, function)
    }

    pub fn count_metric_date(self) -> Self {
        self.count_metric_date_as("metric_date_count")
    }

    pub fn count_metric_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("metric_date", alias)
    }

    pub fn sum_metric_date(self) -> Self {
        self.sum_metric_date_as("sum_metric_date")
    }

    pub fn sum_metric_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("metric_date", alias)
    }

    pub fn avg_metric_date(self) -> Self {
        self.avg_metric_date_as("avg_metric_date")
    }

    pub fn avg_metric_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("metric_date", alias)
    }

    pub fn min_metric_date(self) -> Self {
        self.min_metric_date_as("min_metric_date")
    }

    pub fn min_metric_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("metric_date", alias)
    }

    pub fn max_metric_date(self) -> Self {
        self.max_metric_date_as("max_metric_date")
    }

    pub fn max_metric_date_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("metric_date", alias)
    }

    pub fn unselect_metric_date(mut self) -> Self {
        self.query.projection.retain(|field| field != "metric_date");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "metric_date");
        self
    }


    pub fn with_metric_date(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "metric_date",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_metric_date_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "metric_date",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_metric_date_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("metric_date", value));
        self
    }



    pub fn with_metric_date_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("metric_date", value));
        self
    }

    pub fn with_metric_date_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("metric_date", value));
        self
    }

    pub fn with_metric_date_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("metric_date", value));
        self
    }

    pub fn with_metric_date_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("metric_date", value));
        self
    }

    pub fn with_metric_date_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("metric_date", value));
        self
    }

    pub fn with_metric_date_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("metric_date", lower, upper));
        self
    }

    pub fn with_metric_date_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "metric_date",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_metric_date_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "metric_date",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_metric_date_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "metric_date",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_metric_date_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("metric_date", value));
        self
    }

    pub fn with_metric_date_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("metric_date", value));
        self
    }

    pub fn with_metric_date_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("metric_date"));
        self
    }



    pub fn with_metric_date_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("metric_date"));
        self
    }


    pub fn order_by_metric_date_asc(mut self) -> Self {
        self.query = self.query.order_asc("metric_date");
        self
    }

    pub fn order_by_metric_date_desc(mut self) -> Self {
        self.query = self.query.order_desc("metric_date");
        self
    }

    pub fn order_by_metric_date_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("metric_date");
        self
    }

    pub fn order_by_metric_date_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("metric_date");
        self
    }


    pub fn select_impressions(mut self) -> Self {
        self.query = self.query.project("impressions");
        self
    }

    pub fn project_impressions(self) -> Self {
        self.select_impressions()
    }

    pub fn select_impressions_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_impressions_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_impressions_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("impressions", raw_sql_segment));
        self
    }

    pub fn select_impressions_with_function(self, function: AggregateFunction) -> Self {
        self.select_impressions_as_with_function("impressions", function)
    }

    pub fn select_impressions_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("impressions", alias, function)
    }

    pub fn group_by_impressions(self) -> Self {
        self.group_by("impressions")
    }

    pub fn group_by_impressions_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("impressions");
        request.query = request
            .query
            .project_expr(alias, Expr::column("impressions"));
        request
    }

    pub fn group_by_impressions_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("impressions")
            .aggregate_with_function("impressions", alias, function)
    }

    pub fn count_impressions(self) -> Self {
        self.count_impressions_as("impressions_count")
    }

    pub fn count_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("impressions", alias)
    }

    pub fn sum_impressions(self) -> Self {
        self.sum_impressions_as("sum_impressions")
    }

    pub fn sum_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("impressions", alias)
    }

    pub fn avg_impressions(self) -> Self {
        self.avg_impressions_as("avg_impressions")
    }

    pub fn avg_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("impressions", alias)
    }

    pub fn min_impressions(self) -> Self {
        self.min_impressions_as("min_impressions")
    }

    pub fn min_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("impressions", alias)
    }

    pub fn max_impressions(self) -> Self {
        self.max_impressions_as("max_impressions")
    }

    pub fn max_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("impressions", alias)
    }

    pub fn standard_deviation_impressions(self) -> Self {
        self.standard_deviation_impressions_as("stdDev_impressions")
    }

    pub fn standard_deviation_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("impressions", alias)
    }

    pub fn square_root_of_population_standard_deviation_impressions(self) -> Self {
        self.square_root_of_population_standard_deviation_impressions_as("stdDevPop_impressions")
    }

    pub fn square_root_of_population_standard_deviation_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("impressions", alias)
    }

    pub fn sample_variance_impressions(self) -> Self {
        self.sample_variance_impressions_as("varSamp_impressions")
    }

    pub fn sample_variance_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("impressions", alias)
    }

    pub fn sample_population_variance_impressions(self) -> Self {
        self.sample_population_variance_impressions_as("varPop_impressions")
    }

    pub fn sample_population_variance_impressions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("impressions", alias)
    }

    pub fn unselect_impressions(mut self) -> Self {
        self.query.projection.retain(|field| field != "impressions");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "impressions");
        self
    }


    pub fn with_impressions(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "impressions",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_impressions_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "impressions",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_impressions_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("impressions", value));
        self
    }



    pub fn with_impressions_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("impressions", value));
        self
    }

    pub fn with_impressions_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("impressions", value));
        self
    }

    pub fn with_impressions_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("impressions", value));
        self
    }

    pub fn with_impressions_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("impressions", value));
        self
    }

    pub fn with_impressions_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("impressions", value));
        self
    }

    pub fn with_impressions_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("impressions", lower, upper));
        self
    }

    pub fn with_impressions_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "impressions",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_impressions_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "impressions",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_impressions_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "impressions",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_impressions_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("impressions", value));
        self
    }

    pub fn with_impressions_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("impressions", value));
        self
    }

    pub fn with_impressions_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("impressions"));
        self
    }



    pub fn with_impressions_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("impressions"));
        self
    }


    pub fn order_by_impressions_asc(mut self) -> Self {
        self.query = self.query.order_asc("impressions");
        self
    }

    pub fn order_by_impressions_desc(mut self) -> Self {
        self.query = self.query.order_desc("impressions");
        self
    }

    pub fn order_by_impressions_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("impressions");
        self
    }

    pub fn order_by_impressions_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("impressions");
        self
    }


    pub fn select_clicks(mut self) -> Self {
        self.query = self.query.project("clicks");
        self
    }

    pub fn project_clicks(self) -> Self {
        self.select_clicks()
    }

    pub fn select_clicks_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_clicks_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_clicks_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("clicks", raw_sql_segment));
        self
    }

    pub fn select_clicks_with_function(self, function: AggregateFunction) -> Self {
        self.select_clicks_as_with_function("clicks", function)
    }

    pub fn select_clicks_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("clicks", alias, function)
    }

    pub fn group_by_clicks(self) -> Self {
        self.group_by("clicks")
    }

    pub fn group_by_clicks_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("clicks");
        request.query = request
            .query
            .project_expr(alias, Expr::column("clicks"));
        request
    }

    pub fn group_by_clicks_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("clicks")
            .aggregate_with_function("clicks", alias, function)
    }

    pub fn count_clicks(self) -> Self {
        self.count_clicks_as("clicks_count")
    }

    pub fn count_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("clicks", alias)
    }

    pub fn sum_clicks(self) -> Self {
        self.sum_clicks_as("sum_clicks")
    }

    pub fn sum_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("clicks", alias)
    }

    pub fn avg_clicks(self) -> Self {
        self.avg_clicks_as("avg_clicks")
    }

    pub fn avg_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("clicks", alias)
    }

    pub fn min_clicks(self) -> Self {
        self.min_clicks_as("min_clicks")
    }

    pub fn min_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("clicks", alias)
    }

    pub fn max_clicks(self) -> Self {
        self.max_clicks_as("max_clicks")
    }

    pub fn max_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("clicks", alias)
    }

    pub fn standard_deviation_clicks(self) -> Self {
        self.standard_deviation_clicks_as("stdDev_clicks")
    }

    pub fn standard_deviation_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("clicks", alias)
    }

    pub fn square_root_of_population_standard_deviation_clicks(self) -> Self {
        self.square_root_of_population_standard_deviation_clicks_as("stdDevPop_clicks")
    }

    pub fn square_root_of_population_standard_deviation_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("clicks", alias)
    }

    pub fn sample_variance_clicks(self) -> Self {
        self.sample_variance_clicks_as("varSamp_clicks")
    }

    pub fn sample_variance_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("clicks", alias)
    }

    pub fn sample_population_variance_clicks(self) -> Self {
        self.sample_population_variance_clicks_as("varPop_clicks")
    }

    pub fn sample_population_variance_clicks_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("clicks", alias)
    }

    pub fn unselect_clicks(mut self) -> Self {
        self.query.projection.retain(|field| field != "clicks");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "clicks");
        self
    }


    pub fn with_clicks(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "clicks",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_clicks_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "clicks",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_clicks_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("clicks", value));
        self
    }



    pub fn with_clicks_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("clicks", value));
        self
    }

    pub fn with_clicks_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("clicks", value));
        self
    }

    pub fn with_clicks_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("clicks", value));
        self
    }

    pub fn with_clicks_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("clicks", value));
        self
    }

    pub fn with_clicks_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("clicks", value));
        self
    }

    pub fn with_clicks_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("clicks", lower, upper));
        self
    }

    pub fn with_clicks_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "clicks",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_clicks_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "clicks",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_clicks_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "clicks",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_clicks_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("clicks", value));
        self
    }

    pub fn with_clicks_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("clicks", value));
        self
    }

    pub fn with_clicks_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("clicks"));
        self
    }



    pub fn with_clicks_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("clicks"));
        self
    }


    pub fn order_by_clicks_asc(mut self) -> Self {
        self.query = self.query.order_asc("clicks");
        self
    }

    pub fn order_by_clicks_desc(mut self) -> Self {
        self.query = self.query.order_desc("clicks");
        self
    }

    pub fn order_by_clicks_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("clicks");
        self
    }

    pub fn order_by_clicks_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("clicks");
        self
    }


    pub fn select_leads_generated(mut self) -> Self {
        self.query = self.query.project("leads_generated");
        self
    }

    pub fn project_leads_generated(self) -> Self {
        self.select_leads_generated()
    }

    pub fn select_leads_generated_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_leads_generated_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_leads_generated_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("leads_generated", raw_sql_segment));
        self
    }

    pub fn select_leads_generated_with_function(self, function: AggregateFunction) -> Self {
        self.select_leads_generated_as_with_function("leads_generated", function)
    }

    pub fn select_leads_generated_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("leads_generated", alias, function)
    }

    pub fn group_by_leads_generated(self) -> Self {
        self.group_by("leads_generated")
    }

    pub fn group_by_leads_generated_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("leads_generated");
        request.query = request
            .query
            .project_expr(alias, Expr::column("leads_generated"));
        request
    }

    pub fn group_by_leads_generated_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("leads_generated")
            .aggregate_with_function("leads_generated", alias, function)
    }

    pub fn count_leads_generated(self) -> Self {
        self.count_leads_generated_as("leads_generated_count")
    }

    pub fn count_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("leads_generated", alias)
    }

    pub fn sum_leads_generated(self) -> Self {
        self.sum_leads_generated_as("sum_leads_generated")
    }

    pub fn sum_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("leads_generated", alias)
    }

    pub fn avg_leads_generated(self) -> Self {
        self.avg_leads_generated_as("avg_leads_generated")
    }

    pub fn avg_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("leads_generated", alias)
    }

    pub fn min_leads_generated(self) -> Self {
        self.min_leads_generated_as("min_leads_generated")
    }

    pub fn min_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("leads_generated", alias)
    }

    pub fn max_leads_generated(self) -> Self {
        self.max_leads_generated_as("max_leads_generated")
    }

    pub fn max_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("leads_generated", alias)
    }

    pub fn standard_deviation_leads_generated(self) -> Self {
        self.standard_deviation_leads_generated_as("stdDev_leads_generated")
    }

    pub fn standard_deviation_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("leads_generated", alias)
    }

    pub fn square_root_of_population_standard_deviation_leads_generated(self) -> Self {
        self.square_root_of_population_standard_deviation_leads_generated_as("stdDevPop_leads_generated")
    }

    pub fn square_root_of_population_standard_deviation_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("leads_generated", alias)
    }

    pub fn sample_variance_leads_generated(self) -> Self {
        self.sample_variance_leads_generated_as("varSamp_leads_generated")
    }

    pub fn sample_variance_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("leads_generated", alias)
    }

    pub fn sample_population_variance_leads_generated(self) -> Self {
        self.sample_population_variance_leads_generated_as("varPop_leads_generated")
    }

    pub fn sample_population_variance_leads_generated_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("leads_generated", alias)
    }

    pub fn unselect_leads_generated(mut self) -> Self {
        self.query.projection.retain(|field| field != "leads_generated");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "leads_generated");
        self
    }


    pub fn with_leads_generated(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "leads_generated",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_leads_generated_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "leads_generated",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_leads_generated_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("leads_generated", value));
        self
    }



    pub fn with_leads_generated_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("leads_generated", value));
        self
    }

    pub fn with_leads_generated_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("leads_generated", value));
        self
    }

    pub fn with_leads_generated_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("leads_generated", value));
        self
    }

    pub fn with_leads_generated_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("leads_generated", value));
        self
    }

    pub fn with_leads_generated_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("leads_generated", value));
        self
    }

    pub fn with_leads_generated_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("leads_generated", lower, upper));
        self
    }

    pub fn with_leads_generated_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "leads_generated",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_leads_generated_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "leads_generated",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_leads_generated_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "leads_generated",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_leads_generated_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("leads_generated", value));
        self
    }

    pub fn with_leads_generated_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("leads_generated", value));
        self
    }

    pub fn with_leads_generated_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("leads_generated"));
        self
    }



    pub fn with_leads_generated_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("leads_generated"));
        self
    }


    pub fn order_by_leads_generated_asc(mut self) -> Self {
        self.query = self.query.order_asc("leads_generated");
        self
    }

    pub fn order_by_leads_generated_desc(mut self) -> Self {
        self.query = self.query.order_desc("leads_generated");
        self
    }

    pub fn order_by_leads_generated_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("leads_generated");
        self
    }

    pub fn order_by_leads_generated_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("leads_generated");
        self
    }


    pub fn select_conversions(mut self) -> Self {
        self.query = self.query.project("conversions");
        self
    }

    pub fn project_conversions(self) -> Self {
        self.select_conversions()
    }

    pub fn select_conversions_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_conversions_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_conversions_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("conversions", raw_sql_segment));
        self
    }

    pub fn select_conversions_with_function(self, function: AggregateFunction) -> Self {
        self.select_conversions_as_with_function("conversions", function)
    }

    pub fn select_conversions_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("conversions", alias, function)
    }

    pub fn group_by_conversions(self) -> Self {
        self.group_by("conversions")
    }

    pub fn group_by_conversions_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("conversions");
        request.query = request
            .query
            .project_expr(alias, Expr::column("conversions"));
        request
    }

    pub fn group_by_conversions_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("conversions")
            .aggregate_with_function("conversions", alias, function)
    }

    pub fn count_conversions(self) -> Self {
        self.count_conversions_as("conversions_count")
    }

    pub fn count_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("conversions", alias)
    }

    pub fn sum_conversions(self) -> Self {
        self.sum_conversions_as("sum_conversions")
    }

    pub fn sum_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("conversions", alias)
    }

    pub fn avg_conversions(self) -> Self {
        self.avg_conversions_as("avg_conversions")
    }

    pub fn avg_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("conversions", alias)
    }

    pub fn min_conversions(self) -> Self {
        self.min_conversions_as("min_conversions")
    }

    pub fn min_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("conversions", alias)
    }

    pub fn max_conversions(self) -> Self {
        self.max_conversions_as("max_conversions")
    }

    pub fn max_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("conversions", alias)
    }

    pub fn standard_deviation_conversions(self) -> Self {
        self.standard_deviation_conversions_as("stdDev_conversions")
    }

    pub fn standard_deviation_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("conversions", alias)
    }

    pub fn square_root_of_population_standard_deviation_conversions(self) -> Self {
        self.square_root_of_population_standard_deviation_conversions_as("stdDevPop_conversions")
    }

    pub fn square_root_of_population_standard_deviation_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("conversions", alias)
    }

    pub fn sample_variance_conversions(self) -> Self {
        self.sample_variance_conversions_as("varSamp_conversions")
    }

    pub fn sample_variance_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("conversions", alias)
    }

    pub fn sample_population_variance_conversions(self) -> Self {
        self.sample_population_variance_conversions_as("varPop_conversions")
    }

    pub fn sample_population_variance_conversions_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("conversions", alias)
    }

    pub fn unselect_conversions(mut self) -> Self {
        self.query.projection.retain(|field| field != "conversions");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "conversions");
        self
    }


    pub fn with_conversions(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "conversions",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_conversions_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "conversions",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_conversions_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("conversions", value));
        self
    }



    pub fn with_conversions_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("conversions", value));
        self
    }

    pub fn with_conversions_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("conversions", value));
        self
    }

    pub fn with_conversions_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("conversions", value));
        self
    }

    pub fn with_conversions_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("conversions", value));
        self
    }

    pub fn with_conversions_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("conversions", value));
        self
    }

    pub fn with_conversions_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("conversions", lower, upper));
        self
    }

    pub fn with_conversions_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "conversions",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_conversions_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "conversions",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_conversions_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "conversions",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_conversions_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("conversions", value));
        self
    }

    pub fn with_conversions_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("conversions", value));
        self
    }

    pub fn with_conversions_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("conversions"));
        self
    }



    pub fn with_conversions_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("conversions"));
        self
    }


    pub fn order_by_conversions_asc(mut self) -> Self {
        self.query = self.query.order_asc("conversions");
        self
    }

    pub fn order_by_conversions_desc(mut self) -> Self {
        self.query = self.query.order_desc("conversions");
        self
    }

    pub fn order_by_conversions_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("conversions");
        self
    }

    pub fn order_by_conversions_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("conversions");
        self
    }


    pub fn select_conversion_rate(mut self) -> Self {
        self.query = self.query.project("conversion_rate");
        self
    }

    pub fn project_conversion_rate(self) -> Self {
        self.select_conversion_rate()
    }

    pub fn select_conversion_rate_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_conversion_rate_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_conversion_rate_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("conversion_rate", raw_sql_segment));
        self
    }

    pub fn select_conversion_rate_with_function(self, function: AggregateFunction) -> Self {
        self.select_conversion_rate_as_with_function("conversion_rate", function)
    }

    pub fn select_conversion_rate_as_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.aggregate_with_function("conversion_rate", alias, function)
    }

    pub fn group_by_conversion_rate(self) -> Self {
        self.group_by("conversion_rate")
    }

    pub fn group_by_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("conversion_rate");
        request.query = request
            .query
            .project_expr(alias, Expr::column("conversion_rate"));
        request
    }

    pub fn group_by_conversion_rate_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("conversion_rate")
            .aggregate_with_function("conversion_rate", alias, function)
    }

    pub fn count_conversion_rate(self) -> Self {
        self.count_conversion_rate_as("conversion_rate_count")
    }

    pub fn count_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("conversion_rate", alias)
    }

    pub fn sum_conversion_rate(self) -> Self {
        self.sum_conversion_rate_as("sum_conversion_rate")
    }

    pub fn sum_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("conversion_rate", alias)
    }

    pub fn avg_conversion_rate(self) -> Self {
        self.avg_conversion_rate_as("avg_conversion_rate")
    }

    pub fn avg_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("conversion_rate", alias)
    }

    pub fn min_conversion_rate(self) -> Self {
        self.min_conversion_rate_as("min_conversion_rate")
    }

    pub fn min_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("conversion_rate", alias)
    }

    pub fn max_conversion_rate(self) -> Self {
        self.max_conversion_rate_as("max_conversion_rate")
    }

    pub fn max_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("conversion_rate", alias)
    }

    pub fn standard_deviation_conversion_rate(self) -> Self {
        self.standard_deviation_conversion_rate_as("stdDev_conversion_rate")
    }

    pub fn standard_deviation_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev("conversion_rate", alias)
    }

    pub fn square_root_of_population_standard_deviation_conversion_rate(self) -> Self {
        self.square_root_of_population_standard_deviation_conversion_rate_as("stdDevPop_conversion_rate")
    }

    pub fn square_root_of_population_standard_deviation_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_stddev_pop("conversion_rate", alias)
    }

    pub fn sample_variance_conversion_rate(self) -> Self {
        self.sample_variance_conversion_rate_as("varSamp_conversion_rate")
    }

    pub fn sample_variance_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_samp("conversion_rate", alias)
    }

    pub fn sample_population_variance_conversion_rate(self) -> Self {
        self.sample_population_variance_conversion_rate_as("varPop_conversion_rate")
    }

    pub fn sample_population_variance_conversion_rate_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_var_pop("conversion_rate", alias)
    }

    pub fn unselect_conversion_rate(mut self) -> Self {
        self.query.projection.retain(|field| field != "conversion_rate");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "conversion_rate");
        self
    }


    pub fn with_conversion_rate(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "conversion_rate",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_conversion_rate_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "conversion_rate",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_conversion_rate_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("conversion_rate", value));
        self
    }



    pub fn with_conversion_rate_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("conversion_rate", lower, upper));
        self
    }

    pub fn with_conversion_rate_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "conversion_rate",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_conversion_rate_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "conversion_rate",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_conversion_rate_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "conversion_rate",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_conversion_rate_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("conversion_rate", value));
        self
    }

    pub fn with_conversion_rate_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("conversion_rate"));
        self
    }



    pub fn with_conversion_rate_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("conversion_rate"));
        self
    }


    pub fn order_by_conversion_rate_asc(mut self) -> Self {
        self.query = self.query.order_asc("conversion_rate");
        self
    }

    pub fn order_by_conversion_rate_desc(mut self) -> Self {
        self.query = self.query.order_desc("conversion_rate");
        self
    }

    pub fn order_by_conversion_rate_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("conversion_rate");
        self
    }

    pub fn order_by_conversion_rate_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("conversion_rate");
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
    pub fn filter_by_campaign(mut self, value: impl EntityReference) -> Self {
        self.query = self.query.and_filter(Expr::eq("campaign_id", value.entity_id_value()));
        self
    }

    pub fn with_campaign_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "campaign_id",
            <crate::Campaign as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("campaign", selection));
        self
    }


    pub fn without_campaign_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "campaign_id",
            <crate::Campaign as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("campaign", selection));
        self
    }


    pub fn have_campaign(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("campaign_id"));
        self
    }

    pub fn have_no_campaign(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("campaign_id"));
        self
    }


    pub fn group_by_campaign(self) -> Self {
        self.group_by("campaign_id")
    }

    pub fn group_by_campaign_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("campaign_id");
        request.query = request
            .query
            .project_expr(alias, Expr::column("campaign_id"));
        request
    }

    pub fn group_by_campaign_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("campaign_id")
            .aggregate_with_function("campaign_id", alias, function)
    }

    pub fn group_by_campaign_with(mut self, request: impl Into<QuerySelection>) -> Self {
        self.query = self.query.group_by("campaign_id");
        self.query_options.object_group_bys.push(ObjectGroupBy::new(
            "campaign",
            "campaign_id",
            request,
        ));
        self
    }

    pub fn group_by_campaign_with_details(self) -> Self {
        self.group_by_campaign_with_details_from(crate::Q::campaigns().unlimited())
    }

    pub fn group_by_campaign_with_details_from(self, request: impl Into<QuerySelection>) -> Self {
        self.group_by_campaign_with(request)
    }


    pub fn roll_up_to_campaign(self) -> Self {
        self.roll_up_to_campaign_with(crate::Q::campaigns().unlimited())
    }

    pub fn roll_up_to_campaign_with(self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.with_campaign_matching(selection.clone())
            .group_by_campaign_with(selection)
    }

    pub fn count_campaign(self) -> Self {
        self.count_campaign_as("campaign_count")
    }

    pub fn count_campaign_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("campaign_id", alias)
    }

    pub fn unselect_campaign(mut self) -> Self {
        self.query.projection.retain(|field| field != "campaign_id");
        self.query.relations.retain(|relation| relation.name != "campaign");
        self
    }
    pub fn select_campaign(mut self) -> Self {
        self.query = self.query.relation("campaign");
        self
    }

    pub fn select_campaign_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("campaign", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("campaign", selection));
        self
}

    pub fn facet_by_campaign_as(self, facet_name: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.facet_by_campaign_as_with_options(facet_name, request, true)
    }

    pub fn facet_by_campaign_as_with_options(
        mut self,
        facet_name: impl Into<String>,
        request: impl Into<QuerySelection>,
        include_all_facets: bool,
    ) -> Self {
        self.query_options.facets.push(FacetRequest::new(
            facet_name,
            "campaign",
            request,
            include_all_facets,
        ));
        self
    }
}

impl<R> Default for ConversionMetricRequest<R> {
    fn default() -> Self {
        Self::new()
    }
}

impl<R> From< ConversionMetricRequest<R> > for SelectQuery {
    fn from(request: ConversionMetricRequest<R>) -> Self {
        QuerySelection::from(request).into_query()
    }
}

impl<R> From< ConversionMetricRequest<R> > for QuerySelection {
    fn from(request: ConversionMetricRequest<R>) -> Self {
        Self {
            query: request.query,
            relation_selections: request.relation_selections,
            relation_filters: request.relation_filters,
            child_enhancements: request.child_enhancements,
            query_options: request.query_options,
        }
    }
}


impl<'a, C> crate::request_support::AuditedSave<'a, C> for teaql_core::Audited<crate::ConversionMetric> 
where C: crate::request_support::TeaqlRepositoryProvider + ?Sized + 'a
{
    type Error = crate::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>;
    fn save(self, ctx: &'a C) -> std::pin::Pin<Box<dyn std::future::Future<Output = Result<teaql_runtime::GraphNode, Self::Error>> + '_>> {
        Box::pin(async move {
            teaql_runtime::save_audited_ledger_entity(self, ctx.user_context())
                .await
                .map_err(DataServiceError::Runtime)
        })
    }
}

impl<R: teaql_core::Entity> crate::PurposedQuery<ConversionMetricRequest<R>> {
    pub fn new_entity<C>(&self, ctx: &C) -> crate::ConversionMetric
    where
        C: crate::TeaqlRuntime + ?Sized,
    {
        crate::ConversionMetric::runtime_new(ctx.user_context().entity_root())
    }

    fn into_inner_with_trace(mut self) -> ConversionMetricRequest<R> {
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
    ) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_page(ctx, offset, limit).await
    }

    pub async fn execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_exists(ctx).await
    }

    pub async fn execute_for_list<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_list(ctx).await
    }

    /// Execute query in streaming mode (chunked).
    /// Returns a Vec of StreamChunk, each containing up to chunk_size rows.
    /// Set chunk size via .stream(chunk_size) or .stream_default() on the query.
    pub async fn execute_for_stream<'a, C>(self, ctx: &'a C) -> Result<Vec<teaql_data_service::StreamChunk>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_stream(ctx).await
    }

    pub async fn execute_for_first<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_first(ctx).await
    }

    pub async fn execute_for_one<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_one(ctx).await
    }


    pub async fn execute_for_records<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_records(ctx).await
    }

    pub async fn execute_for_record<'a, C>(self, ctx: &'a C) -> Result<Option<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_record(ctx).await
    }

    pub async fn execute_for_count<'a, C>(self, ctx: &'a C) -> Result<u64, crate::request_support::TeaqlDataServiceError<C::ConversionMetricRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_count(ctx).await
    }
}
