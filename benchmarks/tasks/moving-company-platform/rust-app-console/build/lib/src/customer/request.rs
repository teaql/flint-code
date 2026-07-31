use std::marker::PhantomData;

use serde_json::Value as JsonValue;
use teaql_core::{Aggregate, AggregateFunction, EntityDescriptor, Expr, Record, SelectQuery, SmartList};
use teaql_runtime::{DataServiceError, RuntimeError};

use crate::request_support::*;

impl EntityReference for crate::Customer {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(&self)
    }
}

impl EntityReference for &crate::Customer {
    fn entity_id_value(self) -> teaql_core::Value {
        teaql_core::IdentifiableEntity::id_value(self)
    }
}

// ⛔ AI agents: DO NOT read this file for API discovery. Instead run: cargo teaql --input modeling/MODEL.xml rust-assist-query/customer
#[derive(Debug)]
pub struct CustomerRequest<R = crate::Customer> {
    query: SelectQuery,
    relation_selections: Vec<RelationSelection>,
    relation_filters: Vec<RelationFilter>,
    child_enhancements: Vec<QuerySelection>,
    query_options: QueryOptions,
    marker: PhantomData<R>,
}

impl<R> Clone for CustomerRequest<R> {
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

impl<R> CustomerRequest<R> {
    pub(crate) fn new() -> Self {
        Self {
            query: SelectQuery::new("Customer")
                .project("id")
                .project("version"),
            relation_selections: Vec::new(),
            relation_filters: Vec::new(),
            child_enhancements: Vec::new(),
            query_options: QueryOptions::default(),
            marker: PhantomData,
        }
    }

    pub fn return_type<T>(self) -> CustomerRequest<T> {
        CustomerRequest {
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
        R: teaql_core::Entity,
    {
        let repository = ctx
            .customer_repository()
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
    ) -> Result<Vec<teaql_data_service::StreamChunk>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .customer_repository()
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
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
    ) -> Result<Option<R>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
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
    ) -> Result<SmartList<R>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
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
    ) -> Result<u64, TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .customer_repository()
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
            .ok_or_else(|| DataServiceError::Runtime(RuntimeError::Graph(format!("count result for Customer is missing or not numeric"))))
    }

    pub(crate) async fn _execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .customer_repository()
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
    ) -> Result<SmartList<Record>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: TeaqlRepositoryProvider + ?Sized,
    {
        let repository = ctx
            .customer_repository()
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
    ) -> Result<Option<Record>, TeaqlDataServiceError<C::CustomerRepository<'a>>>
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
            "company_name" => Some("company_name"),
            "create_time" => Some("create_time"),
            "update_time" => Some("update_time"),
            "version" => Some("version"),
            "customer_type" | "customer_type_id" => Some("customer_type_id"),
            _ => None,
        }
    }

    fn apply_dynamic_json_chain_filter(self, head: &str, tail: &str, value: &JsonValue) -> Self {
        let _ = (tail, value);
        match head {
            "customer_type" => {
                self.with_customer_type_matching(
                    crate::Q::customer_types_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "moving_job_list" => {
                self.with_moving_job_list_matching(
                    crate::Q::moving_jobs_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "customer_contact_list" => {
                self.with_customer_contact_list_matching(
                    crate::Q::customer_contacts_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "billing_info_list" => {
                self.with_billing_info_list_matching(
                    crate::Q::billing_info_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "customer_history_list" => {
                self.with_customer_history_list_matching(
                    crate::Q::customer_histories_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "box_rental_list" => {
                self.with_box_rental_list_matching(
                    crate::Q::box_rentals_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "payment_list" => {
                self.with_payment_list_matching(
                    crate::Q::payments_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "invoice_list" => {
                self.with_invoice_list_matching(
                    crate::Q::invoices_minimal()
                        .apply_dynamic_json_filter(tail, value),
                )
            }
            "contract_list" => {
                self.with_contract_list_matching(
                    crate::Q::contracts_minimal()
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
        self.query = self.query.project("company_name");
        self.query = self.query.project("create_time");
        self.query = self.query.project("update_time");
        self.query = self.query.project("version");
        self.query = self.query.project("customer_type_id");
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
        request = request.select_customer_type();
        request
    }

    pub fn select_children(self) -> Self {
        let mut request = self.select_all();
        request = request.select_moving_job_list();
        request = request.select_customer_contact_list();
        request = request.select_billing_info_list();
        request = request.select_customer_history_list();
        request = request.select_box_rental_list();
        request = request.select_payment_list();
        request = request.select_invoice_list();
        request = request.select_contract_list();
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


    pub fn select_company_name(mut self) -> Self {
        self.query = self.query.project("company_name");
        self
    }

    pub fn project_company_name(self) -> Self {
        self.select_company_name()
    }

    pub fn select_company_name_raw(self, raw_sql_segment: impl Into<String>) -> Self {
        self.select_company_name_unsafe_raw(UnsafeRawSqlSegment::trusted(raw_sql_segment))
    }

    pub fn select_company_name_unsafe_raw(mut self, raw_sql_segment: UnsafeRawSqlSegment) -> Self {
        self.query_options
            .raw_projections
            .push(RawProjection::new("company_name", raw_sql_segment));
        self
    }

    pub fn group_by_company_name(self) -> Self {
        self.group_by("company_name")
    }

    pub fn group_by_company_name_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("company_name");
        request.query = request
            .query
            .project_expr(alias, Expr::column("company_name"));
        request
    }

    pub fn group_by_company_name_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("company_name")
            .aggregate_with_function("company_name", alias, function)
    }

    pub fn count_company_name(self) -> Self {
        self.count_company_name_as("company_name_count")
    }

    pub fn count_company_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("company_name", alias)
    }

    pub fn sum_company_name(self) -> Self {
        self.sum_company_name_as("sum_company_name")
    }

    pub fn sum_company_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_sum("company_name", alias)
    }

    pub fn avg_company_name(self) -> Self {
        self.avg_company_name_as("avg_company_name")
    }

    pub fn avg_company_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_avg("company_name", alias)
    }

    pub fn min_company_name(self) -> Self {
        self.min_company_name_as("min_company_name")
    }

    pub fn min_company_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_min("company_name", alias)
    }

    pub fn max_company_name(self) -> Self {
        self.max_company_name_as("max_company_name")
    }

    pub fn max_company_name_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_max("company_name", alias)
    }

    pub fn unselect_company_name(mut self) -> Self {
        self.query.projection.retain(|field| field != "company_name");
        self.query_options.raw_projections.retain(|projection| projection.property_name != "company_name");
        self
    }


    pub fn with_company_name(
        mut self,
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(field_operator_expr(
            "company_name",
            operator,
            values.into_iter().map(Into::into).collect(),
        ));
        self
    }

    pub fn create_company_name_criteria(
        operator: FieldOperator,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Expr {
        field_operator_expr(
            "company_name",
            operator,
            values.into_iter().map(Into::into).collect(),
        )
    }

    pub fn with_company_name_is(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::eq("company_name", value));
        self
    }



    pub fn with_company_name_is_not(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::ne("company_name", value));
        self
    }

    pub fn with_company_name_greater_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("company_name", value));
        self
    }

    pub fn with_company_name_greater_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gte("company_name", value));
        self
    }

    pub fn with_company_name_less_than(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("company_name", value));
        self
    }

    pub fn with_company_name_less_than_or_equal_to(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lte("company_name", value));
        self
    }

    pub fn with_company_name_between(
        mut self,
        lower: impl Into<teaql_core::Value>,
        upper: impl Into<teaql_core::Value>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::between("company_name", lower, upper));
        self
    }

    pub fn with_company_name_between_range<T>(mut self, range: DateRange<T>) -> Self
    where
        T: Into<teaql_core::Value>,
    {
        self.query = self.query.and_filter(Expr::between(
            "company_name",
            range.start,
            range.end,
        ));
        self
    }

    pub fn with_company_name_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::in_list(
            "company_name",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_company_name_not_in(
        mut self,
        values: impl IntoIterator<Item = impl Into<teaql_core::Value>>,
    ) -> Self {
        self.query = self.query.and_filter(Expr::not_in_list(
            "company_name",
            values.into_iter().map(Into::into),
        ));
        self
    }

    pub fn with_company_name_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::contain("company_name", value));
        self
    }

    pub fn with_company_name_not_containing(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_contain("company_name", value));
        self
    }

    pub fn with_company_name_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::begin_with("company_name", value));
        self
    }

    pub fn with_company_name_not_starting_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_begin_with("company_name", value));
        self
    }

    pub fn with_company_name_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::end_with("company_name", value));
        self
    }

    pub fn with_company_name_not_ending_with(mut self, value: impl Into<String>) -> Self {
        self.query = self.query.and_filter(Expr::not_end_with("company_name", value));
        self
    }

    pub fn with_company_name_sounding_like(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::sound_like("company_name", value));
        self
    }
    pub fn with_company_name_before(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::lt("company_name", value));
        self
    }

    pub fn with_company_name_after(mut self, value: impl Into<teaql_core::Value>) -> Self {
        self.query = self.query.and_filter(Expr::gt("company_name", value));
        self
    }

    pub fn with_company_name_is_unknown(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("company_name"));
        self
    }



    pub fn with_company_name_is_known(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("company_name"));
        self
    }


    pub fn order_by_company_name_asc(mut self) -> Self {
        self.query = self.query.order_asc("company_name");
        self
    }

    pub fn order_by_company_name_desc(mut self) -> Self {
        self.query = self.query.order_desc("company_name");
        self
    }

    pub fn order_by_company_name_asc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_asc("company_name");
        self
    }

    pub fn order_by_company_name_desc_using_gbk(mut self) -> Self {
        self.query = self.query.order_gbk_desc("company_name");
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
    /// Please use `with_customer_type_is` instead
    pub(crate) fn filter_by_customer_type(mut self, value: impl EntityReference) -> Self {
        self.query = self.query.and_filter(Expr::eq("customer_type_id", value.entity_id_value()));
        self
    }
    /// Complex relation filter for `customer_type`.
    ///
    /// **Usage Priority:**
    ///
    /// 1. **Preferred**: If you only want to filter by specific known constants, please **prefer** the generated semantic shortcut methods, such as:
    ///    - [`Self::with_customer_type_is_xxx`]
    ///
    ///    This gives the best code readability.
    ///
    /// 2. **Advanced**: Only use this method when you need to perform advanced searches, dynamic subqueries, or filter based on complex relation conditions.
    ///
    /// # Example
    /// ```rust
    /// // Only use when building dynamic queries
    /// let dynamic_query = crate::Q::customer_types_minimal().filter(...);
    /// let request = crate::Q::customers().with_customer_type_matching(dynamic_query);
    /// ```
    pub fn with_customer_type_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "customer_type_id",
            <crate::CustomerType as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("customer_type", selection));
        self
    }


    /// Complex relation filter for `customer_type`.
    ///
    /// **Usage Priority:**
    ///
    /// 1. **Preferred**: If you only want to filter by specific known constants, please **prefer** the generated semantic shortcut methods, such as:
    ///    - [`Self::with_customer_type_is_not_xxx`]
    ///
    ///    This gives the best code readability.
    ///
    /// 2. **Advanced**: Only use this method when you need to perform advanced searches, dynamic subqueries, or filter based on complex relation conditions.
    ///
    /// # Example
    /// ```rust
    /// // Only use when building dynamic queries
    /// let dynamic_query = crate::Q::customer_types_minimal().filter(...);
    /// let request = crate::Q::customers().without_customer_type_matching(dynamic_query);
    /// ```
    pub fn without_customer_type_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "customer_type_id",
            <crate::CustomerType as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "id",
        ));
        self.relation_filters.push(RelationFilter::new("customer_type", selection));
        self
    }


    pub fn have_customer_type(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_not_null("customer_type_id"));
        self
    }

    pub fn have_no_customer_type(mut self) -> Self {
        self.query = self.query.and_filter(Expr::is_null("customer_type_id"));
        self
    }


    pub fn group_by_customer_type(self) -> Self {
        self.group_by("customer_type_id")
    }

    pub fn group_by_customer_type_as(self, alias: impl Into<String>) -> Self {
        let alias = alias.into();
        let mut request = self.group_by("customer_type_id");
        request.query = request
            .query
            .project_expr(alias, Expr::column("customer_type_id"));
        request
    }

    pub fn group_by_customer_type_with_function(
        self,
        alias: impl Into<String>,
        function: AggregateFunction,
    ) -> Self {
        self.group_by("customer_type_id")
            .aggregate_with_function("customer_type_id", alias, function)
    }

    pub fn group_by_customer_type_with(mut self, request: impl Into<QuerySelection>) -> Self {
        self.query = self.query.group_by("customer_type_id");
        self.query_options.object_group_bys.push(ObjectGroupBy::new(
            "customer_type",
            "customer_type_id",
            request,
        ));
        self
    }

    pub fn group_by_customer_type_with_details(self) -> Self {
        self.group_by_customer_type_with_details_from(crate::Q::customer_types().unlimited())
    }

    pub fn group_by_customer_type_with_details_from(self, request: impl Into<QuerySelection>) -> Self {
        self.group_by_customer_type_with(request)
    }


    pub fn roll_up_to_customer_type(self) -> Self {
        self.roll_up_to_customer_type_with(crate::Q::customer_types().unlimited())
    }

    pub fn roll_up_to_customer_type_with(self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.with_customer_type_matching(selection.clone())
            .group_by_customer_type_with(selection)
    }

    pub fn count_customer_type(self) -> Self {
        self.count_customer_type_as("customer_type_count")
    }

    pub fn count_customer_type_as(self, alias: impl Into<String>) -> Self {
        self.aggregate_count_field("customer_type_id", alias)
    }

    pub fn unselect_customer_type(mut self) -> Self {
        self.query.projection.retain(|field| field != "customer_type_id");
        self.query.relations.retain(|relation| relation.name != "customer_type");
        self
    }
    pub fn customer_type_is_private(self) -> Self {
        self.filter_by_customer_type(1001_u64)
    }

    pub fn with_customer_type_is_private(self) -> Self {
        self.filter_by_customer_type(1001_u64)
    }



    pub fn with_customer_type_is_not_private(mut self) -> Self {
        self.query = self.query.and_filter(Expr::ne("customer_type_id", 1001_u64));
        self
    }


    pub fn customer_type_is_corporate(self) -> Self {
        self.filter_by_customer_type(1002_u64)
    }

    pub fn with_customer_type_is_corporate(self) -> Self {
        self.filter_by_customer_type(1002_u64)
    }



    pub fn with_customer_type_is_not_corporate(mut self) -> Self {
        self.query = self.query.and_filter(Expr::ne("customer_type_id", 1002_u64));
        self
    }


    pub fn select_customer_type(mut self) -> Self {
        self.query = self.query.relation("customer_type");
        self
    }

    pub fn select_customer_type_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("customer_type", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("customer_type", selection));
        self
}

    pub fn facet_by_customer_type_as(self, facet_name: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.facet_by_customer_type_as_with_options(facet_name, request, true)
    }

    pub fn facet_by_customer_type_as_with_options(
        mut self,
        facet_name: impl Into<String>,
        request: impl Into<QuerySelection>,
        include_all_facets: bool,
    ) -> Self {
        self.query_options.facets.push(FacetRequest::new(
            facet_name,
            "customer_type",
            request,
            include_all_facets,
        ));
        self
    }
    pub fn have_moving_jobs(self) -> Self {
        self.with_moving_job_list_matching(SelectQuery::new("MovingJob"))
    }

    pub fn have_no_moving_jobs(self) -> Self {
        self.without_moving_job_list_matching(SelectQuery::new("MovingJob"))
    }

    pub fn with_moving_job_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::MovingJob as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("moving_job_list", selection));
        self
    }

    pub fn without_moving_job_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::MovingJob as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("moving_job_list", selection));
        self
    }

    pub fn select_moving_job_list(mut self) -> Self {
        self.query = self.query.relation("moving_job_list");
        self
    }

    pub fn select_moving_job_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("moving_job_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("moving_job_list", selection));
        self
}

    pub fn have_customer_contacts(self) -> Self {
        self.with_customer_contact_list_matching(SelectQuery::new("CustomerContact"))
    }

    pub fn have_no_customer_contacts(self) -> Self {
        self.without_customer_contact_list_matching(SelectQuery::new("CustomerContact"))
    }

    pub fn with_customer_contact_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::CustomerContact as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("customer_contact_list", selection));
        self
    }

    pub fn without_customer_contact_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::CustomerContact as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("customer_contact_list", selection));
        self
    }

    pub fn select_customer_contact_list(mut self) -> Self {
        self.query = self.query.relation("customer_contact_list");
        self
    }

    pub fn select_customer_contact_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("customer_contact_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("customer_contact_list", selection));
        self
}

    pub fn have_billing_info(self) -> Self {
        self.with_billing_info_list_matching(SelectQuery::new("BillingInfo"))
    }

    pub fn have_no_billing_info(self) -> Self {
        self.without_billing_info_list_matching(SelectQuery::new("BillingInfo"))
    }

    pub fn with_billing_info_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::BillingInfo as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("billing_info_list", selection));
        self
    }

    pub fn without_billing_info_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::BillingInfo as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("billing_info_list", selection));
        self
    }

    pub fn select_billing_info_list(mut self) -> Self {
        self.query = self.query.relation("billing_info_list");
        self
    }

    pub fn select_billing_info_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("billing_info_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("billing_info_list", selection));
        self
}

    pub fn have_customer_histories(self) -> Self {
        self.with_customer_history_list_matching(SelectQuery::new("CustomerHistory"))
    }

    pub fn have_no_customer_histories(self) -> Self {
        self.without_customer_history_list_matching(SelectQuery::new("CustomerHistory"))
    }

    pub fn with_customer_history_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::CustomerHistory as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("customer_history_list", selection));
        self
    }

    pub fn without_customer_history_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::CustomerHistory as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("customer_history_list", selection));
        self
    }

    pub fn select_customer_history_list(mut self) -> Self {
        self.query = self.query.relation("customer_history_list");
        self
    }

    pub fn select_customer_history_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("customer_history_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("customer_history_list", selection));
        self
}

    pub fn have_box_rentals(self) -> Self {
        self.with_box_rental_list_matching(SelectQuery::new("BoxRental"))
    }

    pub fn have_no_box_rentals(self) -> Self {
        self.without_box_rental_list_matching(SelectQuery::new("BoxRental"))
    }

    pub fn with_box_rental_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::BoxRental as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("box_rental_list", selection));
        self
    }

    pub fn without_box_rental_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::BoxRental as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("box_rental_list", selection));
        self
    }

    pub fn select_box_rental_list(mut self) -> Self {
        self.query = self.query.relation("box_rental_list");
        self
    }

    pub fn select_box_rental_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("box_rental_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("box_rental_list", selection));
        self
}

    pub fn have_payments(self) -> Self {
        self.with_payment_list_matching(SelectQuery::new("Payment"))
    }

    pub fn have_no_payments(self) -> Self {
        self.without_payment_list_matching(SelectQuery::new("Payment"))
    }

    pub fn with_payment_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::Payment as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("payment_list", selection));
        self
    }

    pub fn without_payment_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::Payment as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("payment_list", selection));
        self
    }

    pub fn select_payment_list(mut self) -> Self {
        self.query = self.query.relation("payment_list");
        self
    }

    pub fn select_payment_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("payment_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("payment_list", selection));
        self
}

    pub fn have_invoices(self) -> Self {
        self.with_invoice_list_matching(SelectQuery::new("Invoice"))
    }

    pub fn have_no_invoices(self) -> Self {
        self.without_invoice_list_matching(SelectQuery::new("Invoice"))
    }

    pub fn with_invoice_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::Invoice as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("invoice_list", selection));
        self
    }

    pub fn without_invoice_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::Invoice as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("invoice_list", selection));
        self
    }

    pub fn select_invoice_list(mut self) -> Self {
        self.query = self.query.relation("invoice_list");
        self
    }

    pub fn select_invoice_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("invoice_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("invoice_list", selection));
        self
}

    pub fn have_contracts(self) -> Self {
        self.with_contract_list_matching(SelectQuery::new("Contract"))
    }

    pub fn have_no_contracts(self) -> Self {
        self.without_contract_list_matching(SelectQuery::new("Contract"))
    }

    pub fn with_contract_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::in_subquery(
            "id",
            <crate::Contract as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("contract_list", selection));
        self
    }

    pub fn without_contract_list_matching(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.and_filter(Expr::not_in_subquery(
            "id",
            <crate::Contract as teaql_core::TeaqlEntity>::entity_descriptor(),
            selection.query.clone(),
            "customer_id",
        ));
        self.relation_filters.push(RelationFilter::new("contract_list", selection));
        self
    }

    pub fn select_contract_list(mut self) -> Self {
        self.query = self.query.relation("contract_list");
        self
    }

    pub fn select_contract_list_with(mut self, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query = self.query.relation_query("contract_list", selection.clone().into_query());
        self.relation_selections.push(RelationSelection::new("contract_list", selection));
        self
}
    pub fn count_moving_jobs(self) -> Self {
        self.count_moving_jobs_as("count_moving_jobs")
    }

    pub fn count_moving_jobs_as(self, alias: impl Into<String>) -> Self {
        self.count_moving_jobs_with(alias, crate::Q::moving_jobs().unlimited())
    }

    pub fn count_moving_jobs_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "moving_job_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_moving_jobs(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as("refinements", request)
    }

    pub fn stats_from_moving_jobs_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "moving_job_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_moving_jobs_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs(request)
    }


    pub fn min_scheduled_date_of_moving_jobs(self) -> Self {
        self.min_scheduled_date_of_moving_jobs_as("min_scheduled_date_of_moving_jobs", crate::Q::moving_jobs().unlimited())
    }

    pub fn min_scheduled_date_of_moving_jobs_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as(alias, request.into().into_query().min("scheduled_date", "min_scheduled_date"))
    }
    pub fn max_scheduled_date_of_moving_jobs(self) -> Self {
        self.max_scheduled_date_of_moving_jobs_as("max_scheduled_date_of_moving_jobs", crate::Q::moving_jobs().unlimited())
    }

    pub fn max_scheduled_date_of_moving_jobs_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as(alias, request.into().into_query().max("scheduled_date", "max_scheduled_date"))
    }
    pub fn min_create_time_of_moving_jobs(self) -> Self {
        self.min_create_time_of_moving_jobs_as("min_create_time_of_moving_jobs", crate::Q::moving_jobs().unlimited())
    }

    pub fn min_create_time_of_moving_jobs_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_moving_jobs(self) -> Self {
        self.max_create_time_of_moving_jobs_as("max_create_time_of_moving_jobs", crate::Q::moving_jobs().unlimited())
    }

    pub fn max_create_time_of_moving_jobs_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_moving_jobs(self) -> Self {
        self.min_update_time_of_moving_jobs_as("min_update_time_of_moving_jobs", crate::Q::moving_jobs().unlimited())
    }

    pub fn min_update_time_of_moving_jobs_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_moving_jobs(self) -> Self {
        self.max_update_time_of_moving_jobs_as("max_update_time_of_moving_jobs", crate::Q::moving_jobs().unlimited())
    }

    pub fn max_update_time_of_moving_jobs_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_moving_jobs_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_customer_contacts(self) -> Self {
        self.count_customer_contacts_as("count_customer_contacts")
    }

    pub fn count_customer_contacts_as(self, alias: impl Into<String>) -> Self {
        self.count_customer_contacts_with(alias, crate::Q::customer_contacts().unlimited())
    }

    pub fn count_customer_contacts_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "customer_contact_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_customer_contacts(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_contacts_as("refinements", request)
    }

    pub fn stats_from_customer_contacts_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "customer_contact_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_customer_contacts_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_contacts(request)
    }


    pub fn min_create_time_of_customer_contacts(self) -> Self {
        self.min_create_time_of_customer_contacts_as("min_create_time_of_customer_contacts", crate::Q::customer_contacts().unlimited())
    }

    pub fn min_create_time_of_customer_contacts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_contacts_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_customer_contacts(self) -> Self {
        self.max_create_time_of_customer_contacts_as("max_create_time_of_customer_contacts", crate::Q::customer_contacts().unlimited())
    }

    pub fn max_create_time_of_customer_contacts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_contacts_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_customer_contacts(self) -> Self {
        self.min_update_time_of_customer_contacts_as("min_update_time_of_customer_contacts", crate::Q::customer_contacts().unlimited())
    }

    pub fn min_update_time_of_customer_contacts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_contacts_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_customer_contacts(self) -> Self {
        self.max_update_time_of_customer_contacts_as("max_update_time_of_customer_contacts", crate::Q::customer_contacts().unlimited())
    }

    pub fn max_update_time_of_customer_contacts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_contacts_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_billing_info(self) -> Self {
        self.count_billing_info_as("count_billing_info")
    }

    pub fn count_billing_info_as(self, alias: impl Into<String>) -> Self {
        self.count_billing_info_with(alias, crate::Q::billing_info().unlimited())
    }

    pub fn count_billing_info_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "billing_info_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_billing_info(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as("refinements", request)
    }

    pub fn stats_from_billing_info_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "billing_info_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_billing_info_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info(request)
    }


    pub fn sum_card_last_four_of_billing_info(self) -> Self {
        self.sum_card_last_four_of_billing_info_as("sum_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn sum_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().sum("card_last_four", "sum_card_last_four"))
    }
    pub fn min_card_last_four_of_billing_info(self) -> Self {
        self.min_card_last_four_of_billing_info_as("min_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn min_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().min("card_last_four", "min_card_last_four"))
    }
    pub fn max_card_last_four_of_billing_info(self) -> Self {
        self.max_card_last_four_of_billing_info_as("max_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn max_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().max("card_last_four", "max_card_last_four"))
    }
    pub fn avg_card_last_four_of_billing_info(self) -> Self {
        self.avg_card_last_four_of_billing_info_as("avg_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn avg_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().avg("card_last_four", "avg_card_last_four"))
    }
    pub fn standard_deviation_card_last_four_of_billing_info(self) -> Self {
        self.standard_deviation_card_last_four_of_billing_info_as("standard_deviation_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn standard_deviation_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().stddev("card_last_four", "stdDev_card_last_four"))
    }
    pub fn square_root_of_population_standard_deviation_card_last_four_of_billing_info(self) -> Self {
        self.square_root_of_population_standard_deviation_card_last_four_of_billing_info_as("square_root_of_population_standard_deviation_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().stddev_pop("card_last_four", "stdDevPop_card_last_four"))
    }
    pub fn sample_variance_card_last_four_of_billing_info(self) -> Self {
        self.sample_variance_card_last_four_of_billing_info_as("sample_variance_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn sample_variance_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().var_samp("card_last_four", "varSamp_card_last_four"))
    }
    pub fn sample_population_variance_card_last_four_of_billing_info(self) -> Self {
        self.sample_population_variance_card_last_four_of_billing_info_as("sample_population_variance_card_last_four_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn sample_population_variance_card_last_four_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().var_pop("card_last_four", "varPop_card_last_four"))
    }
    pub fn min_create_time_of_billing_info(self) -> Self {
        self.min_create_time_of_billing_info_as("min_create_time_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn min_create_time_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_billing_info(self) -> Self {
        self.max_create_time_of_billing_info_as("max_create_time_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn max_create_time_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_billing_info(self) -> Self {
        self.min_update_time_of_billing_info_as("min_update_time_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn min_update_time_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_billing_info(self) -> Self {
        self.max_update_time_of_billing_info_as("max_update_time_of_billing_info", crate::Q::billing_info().unlimited())
    }

    pub fn max_update_time_of_billing_info_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_billing_info_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_customer_histories(self) -> Self {
        self.count_customer_histories_as("count_customer_histories")
    }

    pub fn count_customer_histories_as(self, alias: impl Into<String>) -> Self {
        self.count_customer_histories_with(alias, crate::Q::customer_histories().unlimited())
    }

    pub fn count_customer_histories_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "customer_history_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_customer_histories(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as("refinements", request)
    }

    pub fn stats_from_customer_histories_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "customer_history_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_customer_histories_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories(request)
    }


    pub fn min_event_date_of_customer_histories(self) -> Self {
        self.min_event_date_of_customer_histories_as("min_event_date_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn min_event_date_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().min("event_date", "min_event_date"))
    }
    pub fn max_event_date_of_customer_histories(self) -> Self {
        self.max_event_date_of_customer_histories_as("max_event_date_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn max_event_date_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().max("event_date", "max_event_date"))
    }
    pub fn sum_amount_of_customer_histories(self) -> Self {
        self.sum_amount_of_customer_histories_as("sum_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn sum_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().sum("amount", "sum_amount"))
    }
    pub fn min_amount_of_customer_histories(self) -> Self {
        self.min_amount_of_customer_histories_as("min_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn min_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().min("amount", "min_amount"))
    }
    pub fn max_amount_of_customer_histories(self) -> Self {
        self.max_amount_of_customer_histories_as("max_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn max_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().max("amount", "max_amount"))
    }
    pub fn avg_amount_of_customer_histories(self) -> Self {
        self.avg_amount_of_customer_histories_as("avg_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn avg_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().avg("amount", "avg_amount"))
    }
    pub fn standard_deviation_amount_of_customer_histories(self) -> Self {
        self.standard_deviation_amount_of_customer_histories_as("standard_deviation_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn standard_deviation_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().stddev("amount", "stdDev_amount"))
    }
    pub fn square_root_of_population_standard_deviation_amount_of_customer_histories(self) -> Self {
        self.square_root_of_population_standard_deviation_amount_of_customer_histories_as("square_root_of_population_standard_deviation_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().stddev_pop("amount", "stdDevPop_amount"))
    }
    pub fn sample_variance_amount_of_customer_histories(self) -> Self {
        self.sample_variance_amount_of_customer_histories_as("sample_variance_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn sample_variance_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().var_samp("amount", "varSamp_amount"))
    }
    pub fn sample_population_variance_amount_of_customer_histories(self) -> Self {
        self.sample_population_variance_amount_of_customer_histories_as("sample_population_variance_amount_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn sample_population_variance_amount_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().var_pop("amount", "varPop_amount"))
    }
    pub fn min_create_time_of_customer_histories(self) -> Self {
        self.min_create_time_of_customer_histories_as("min_create_time_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn min_create_time_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_customer_histories(self) -> Self {
        self.max_create_time_of_customer_histories_as("max_create_time_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn max_create_time_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_customer_histories(self) -> Self {
        self.min_update_time_of_customer_histories_as("min_update_time_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn min_update_time_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_customer_histories(self) -> Self {
        self.max_update_time_of_customer_histories_as("max_update_time_of_customer_histories", crate::Q::customer_histories().unlimited())
    }

    pub fn max_update_time_of_customer_histories_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_customer_histories_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_box_rentals(self) -> Self {
        self.count_box_rentals_as("count_box_rentals")
    }

    pub fn count_box_rentals_as(self, alias: impl Into<String>) -> Self {
        self.count_box_rentals_with(alias, crate::Q::box_rentals().unlimited())
    }

    pub fn count_box_rentals_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "box_rental_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_box_rentals(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as("refinements", request)
    }

    pub fn stats_from_box_rentals_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "box_rental_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_box_rentals_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals(request)
    }


    pub fn sum_quantity_of_box_rentals(self) -> Self {
        self.sum_quantity_of_box_rentals_as("sum_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn sum_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().sum("quantity", "sum_quantity"))
    }
    pub fn min_quantity_of_box_rentals(self) -> Self {
        self.min_quantity_of_box_rentals_as("min_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn min_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().min("quantity", "min_quantity"))
    }
    pub fn max_quantity_of_box_rentals(self) -> Self {
        self.max_quantity_of_box_rentals_as("max_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn max_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().max("quantity", "max_quantity"))
    }
    pub fn avg_quantity_of_box_rentals(self) -> Self {
        self.avg_quantity_of_box_rentals_as("avg_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn avg_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().avg("quantity", "avg_quantity"))
    }
    pub fn standard_deviation_quantity_of_box_rentals(self) -> Self {
        self.standard_deviation_quantity_of_box_rentals_as("standard_deviation_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn standard_deviation_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().stddev("quantity", "stdDev_quantity"))
    }
    pub fn square_root_of_population_standard_deviation_quantity_of_box_rentals(self) -> Self {
        self.square_root_of_population_standard_deviation_quantity_of_box_rentals_as("square_root_of_population_standard_deviation_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().stddev_pop("quantity", "stdDevPop_quantity"))
    }
    pub fn sample_variance_quantity_of_box_rentals(self) -> Self {
        self.sample_variance_quantity_of_box_rentals_as("sample_variance_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn sample_variance_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().var_samp("quantity", "varSamp_quantity"))
    }
    pub fn sample_population_variance_quantity_of_box_rentals(self) -> Self {
        self.sample_population_variance_quantity_of_box_rentals_as("sample_population_variance_quantity_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn sample_population_variance_quantity_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().var_pop("quantity", "varPop_quantity"))
    }
    pub fn min_rental_start_of_box_rentals(self) -> Self {
        self.min_rental_start_of_box_rentals_as("min_rental_start_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn min_rental_start_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().min("rental_start", "min_rental_start"))
    }
    pub fn max_rental_start_of_box_rentals(self) -> Self {
        self.max_rental_start_of_box_rentals_as("max_rental_start_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn max_rental_start_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().max("rental_start", "max_rental_start"))
    }
    pub fn min_rental_end_of_box_rentals(self) -> Self {
        self.min_rental_end_of_box_rentals_as("min_rental_end_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn min_rental_end_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().min("rental_end", "min_rental_end"))
    }
    pub fn max_rental_end_of_box_rentals(self) -> Self {
        self.max_rental_end_of_box_rentals_as("max_rental_end_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn max_rental_end_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().max("rental_end", "max_rental_end"))
    }
    pub fn sum_total_cost_of_box_rentals(self) -> Self {
        self.sum_total_cost_of_box_rentals_as("sum_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn sum_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().sum("total_cost", "sum_total_cost"))
    }
    pub fn min_total_cost_of_box_rentals(self) -> Self {
        self.min_total_cost_of_box_rentals_as("min_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn min_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().min("total_cost", "min_total_cost"))
    }
    pub fn max_total_cost_of_box_rentals(self) -> Self {
        self.max_total_cost_of_box_rentals_as("max_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn max_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().max("total_cost", "max_total_cost"))
    }
    pub fn avg_total_cost_of_box_rentals(self) -> Self {
        self.avg_total_cost_of_box_rentals_as("avg_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn avg_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().avg("total_cost", "avg_total_cost"))
    }
    pub fn standard_deviation_total_cost_of_box_rentals(self) -> Self {
        self.standard_deviation_total_cost_of_box_rentals_as("standard_deviation_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn standard_deviation_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().stddev("total_cost", "stdDev_total_cost"))
    }
    pub fn square_root_of_population_standard_deviation_total_cost_of_box_rentals(self) -> Self {
        self.square_root_of_population_standard_deviation_total_cost_of_box_rentals_as("square_root_of_population_standard_deviation_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().stddev_pop("total_cost", "stdDevPop_total_cost"))
    }
    pub fn sample_variance_total_cost_of_box_rentals(self) -> Self {
        self.sample_variance_total_cost_of_box_rentals_as("sample_variance_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn sample_variance_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().var_samp("total_cost", "varSamp_total_cost"))
    }
    pub fn sample_population_variance_total_cost_of_box_rentals(self) -> Self {
        self.sample_population_variance_total_cost_of_box_rentals_as("sample_population_variance_total_cost_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn sample_population_variance_total_cost_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().var_pop("total_cost", "varPop_total_cost"))
    }
    pub fn min_create_time_of_box_rentals(self) -> Self {
        self.min_create_time_of_box_rentals_as("min_create_time_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn min_create_time_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_box_rentals(self) -> Self {
        self.max_create_time_of_box_rentals_as("max_create_time_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn max_create_time_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_box_rentals(self) -> Self {
        self.min_update_time_of_box_rentals_as("min_update_time_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn min_update_time_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_box_rentals(self) -> Self {
        self.max_update_time_of_box_rentals_as("max_update_time_of_box_rentals", crate::Q::box_rentals().unlimited())
    }

    pub fn max_update_time_of_box_rentals_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_box_rentals_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_payments(self) -> Self {
        self.count_payments_as("count_payments")
    }

    pub fn count_payments_as(self, alias: impl Into<String>) -> Self {
        self.count_payments_with(alias, crate::Q::payments().unlimited())
    }

    pub fn count_payments_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "payment_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_payments(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as("refinements", request)
    }

    pub fn stats_from_payments_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "payment_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_payments_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments(request)
    }


    pub fn sum_amount_of_payments(self) -> Self {
        self.sum_amount_of_payments_as("sum_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn sum_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().sum("amount", "sum_amount"))
    }
    pub fn min_amount_of_payments(self) -> Self {
        self.min_amount_of_payments_as("min_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn min_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().min("amount", "min_amount"))
    }
    pub fn max_amount_of_payments(self) -> Self {
        self.max_amount_of_payments_as("max_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn max_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().max("amount", "max_amount"))
    }
    pub fn avg_amount_of_payments(self) -> Self {
        self.avg_amount_of_payments_as("avg_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn avg_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().avg("amount", "avg_amount"))
    }
    pub fn standard_deviation_amount_of_payments(self) -> Self {
        self.standard_deviation_amount_of_payments_as("standard_deviation_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn standard_deviation_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().stddev("amount", "stdDev_amount"))
    }
    pub fn square_root_of_population_standard_deviation_amount_of_payments(self) -> Self {
        self.square_root_of_population_standard_deviation_amount_of_payments_as("square_root_of_population_standard_deviation_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().stddev_pop("amount", "stdDevPop_amount"))
    }
    pub fn sample_variance_amount_of_payments(self) -> Self {
        self.sample_variance_amount_of_payments_as("sample_variance_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn sample_variance_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().var_samp("amount", "varSamp_amount"))
    }
    pub fn sample_population_variance_amount_of_payments(self) -> Self {
        self.sample_population_variance_amount_of_payments_as("sample_population_variance_amount_of_payments", crate::Q::payments().unlimited())
    }

    pub fn sample_population_variance_amount_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().var_pop("amount", "varPop_amount"))
    }
    pub fn min_payment_date_of_payments(self) -> Self {
        self.min_payment_date_of_payments_as("min_payment_date_of_payments", crate::Q::payments().unlimited())
    }

    pub fn min_payment_date_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().min("payment_date", "min_payment_date"))
    }
    pub fn max_payment_date_of_payments(self) -> Self {
        self.max_payment_date_of_payments_as("max_payment_date_of_payments", crate::Q::payments().unlimited())
    }

    pub fn max_payment_date_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().max("payment_date", "max_payment_date"))
    }
    pub fn min_create_time_of_payments(self) -> Self {
        self.min_create_time_of_payments_as("min_create_time_of_payments", crate::Q::payments().unlimited())
    }

    pub fn min_create_time_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_payments(self) -> Self {
        self.max_create_time_of_payments_as("max_create_time_of_payments", crate::Q::payments().unlimited())
    }

    pub fn max_create_time_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_payments(self) -> Self {
        self.min_update_time_of_payments_as("min_update_time_of_payments", crate::Q::payments().unlimited())
    }

    pub fn min_update_time_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_payments(self) -> Self {
        self.max_update_time_of_payments_as("max_update_time_of_payments", crate::Q::payments().unlimited())
    }

    pub fn max_update_time_of_payments_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_payments_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_invoices(self) -> Self {
        self.count_invoices_as("count_invoices")
    }

    pub fn count_invoices_as(self, alias: impl Into<String>) -> Self {
        self.count_invoices_with(alias, crate::Q::invoices().unlimited())
    }

    pub fn count_invoices_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "invoice_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_invoices(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as("refinements", request)
    }

    pub fn stats_from_invoices_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "invoice_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_invoices_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices(request)
    }


    pub fn min_issue_date_of_invoices(self) -> Self {
        self.min_issue_date_of_invoices_as("min_issue_date_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_issue_date_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("issue_date", "min_issue_date"))
    }
    pub fn max_issue_date_of_invoices(self) -> Self {
        self.max_issue_date_of_invoices_as("max_issue_date_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_issue_date_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("issue_date", "max_issue_date"))
    }
    pub fn min_due_date_of_invoices(self) -> Self {
        self.min_due_date_of_invoices_as("min_due_date_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_due_date_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("due_date", "min_due_date"))
    }
    pub fn max_due_date_of_invoices(self) -> Self {
        self.max_due_date_of_invoices_as("max_due_date_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_due_date_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("due_date", "max_due_date"))
    }
    pub fn sum_subtotal_of_invoices(self) -> Self {
        self.sum_subtotal_of_invoices_as("sum_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sum_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().sum("subtotal", "sum_subtotal"))
    }
    pub fn min_subtotal_of_invoices(self) -> Self {
        self.min_subtotal_of_invoices_as("min_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("subtotal", "min_subtotal"))
    }
    pub fn max_subtotal_of_invoices(self) -> Self {
        self.max_subtotal_of_invoices_as("max_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("subtotal", "max_subtotal"))
    }
    pub fn avg_subtotal_of_invoices(self) -> Self {
        self.avg_subtotal_of_invoices_as("avg_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn avg_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().avg("subtotal", "avg_subtotal"))
    }
    pub fn standard_deviation_subtotal_of_invoices(self) -> Self {
        self.standard_deviation_subtotal_of_invoices_as("standard_deviation_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn standard_deviation_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().stddev("subtotal", "stdDev_subtotal"))
    }
    pub fn square_root_of_population_standard_deviation_subtotal_of_invoices(self) -> Self {
        self.square_root_of_population_standard_deviation_subtotal_of_invoices_as("square_root_of_population_standard_deviation_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().stddev_pop("subtotal", "stdDevPop_subtotal"))
    }
    pub fn sample_variance_subtotal_of_invoices(self) -> Self {
        self.sample_variance_subtotal_of_invoices_as("sample_variance_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sample_variance_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().var_samp("subtotal", "varSamp_subtotal"))
    }
    pub fn sample_population_variance_subtotal_of_invoices(self) -> Self {
        self.sample_population_variance_subtotal_of_invoices_as("sample_population_variance_subtotal_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sample_population_variance_subtotal_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().var_pop("subtotal", "varPop_subtotal"))
    }
    pub fn sum_tax_amount_of_invoices(self) -> Self {
        self.sum_tax_amount_of_invoices_as("sum_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sum_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().sum("tax_amount", "sum_tax_amount"))
    }
    pub fn min_tax_amount_of_invoices(self) -> Self {
        self.min_tax_amount_of_invoices_as("min_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("tax_amount", "min_tax_amount"))
    }
    pub fn max_tax_amount_of_invoices(self) -> Self {
        self.max_tax_amount_of_invoices_as("max_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("tax_amount", "max_tax_amount"))
    }
    pub fn avg_tax_amount_of_invoices(self) -> Self {
        self.avg_tax_amount_of_invoices_as("avg_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn avg_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().avg("tax_amount", "avg_tax_amount"))
    }
    pub fn standard_deviation_tax_amount_of_invoices(self) -> Self {
        self.standard_deviation_tax_amount_of_invoices_as("standard_deviation_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn standard_deviation_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().stddev("tax_amount", "stdDev_tax_amount"))
    }
    pub fn square_root_of_population_standard_deviation_tax_amount_of_invoices(self) -> Self {
        self.square_root_of_population_standard_deviation_tax_amount_of_invoices_as("square_root_of_population_standard_deviation_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().stddev_pop("tax_amount", "stdDevPop_tax_amount"))
    }
    pub fn sample_variance_tax_amount_of_invoices(self) -> Self {
        self.sample_variance_tax_amount_of_invoices_as("sample_variance_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sample_variance_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().var_samp("tax_amount", "varSamp_tax_amount"))
    }
    pub fn sample_population_variance_tax_amount_of_invoices(self) -> Self {
        self.sample_population_variance_tax_amount_of_invoices_as("sample_population_variance_tax_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sample_population_variance_tax_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().var_pop("tax_amount", "varPop_tax_amount"))
    }
    pub fn sum_total_amount_of_invoices(self) -> Self {
        self.sum_total_amount_of_invoices_as("sum_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sum_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().sum("total_amount", "sum_total_amount"))
    }
    pub fn min_total_amount_of_invoices(self) -> Self {
        self.min_total_amount_of_invoices_as("min_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("total_amount", "min_total_amount"))
    }
    pub fn max_total_amount_of_invoices(self) -> Self {
        self.max_total_amount_of_invoices_as("max_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("total_amount", "max_total_amount"))
    }
    pub fn avg_total_amount_of_invoices(self) -> Self {
        self.avg_total_amount_of_invoices_as("avg_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn avg_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().avg("total_amount", "avg_total_amount"))
    }
    pub fn standard_deviation_total_amount_of_invoices(self) -> Self {
        self.standard_deviation_total_amount_of_invoices_as("standard_deviation_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn standard_deviation_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().stddev("total_amount", "stdDev_total_amount"))
    }
    pub fn square_root_of_population_standard_deviation_total_amount_of_invoices(self) -> Self {
        self.square_root_of_population_standard_deviation_total_amount_of_invoices_as("square_root_of_population_standard_deviation_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn square_root_of_population_standard_deviation_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().stddev_pop("total_amount", "stdDevPop_total_amount"))
    }
    pub fn sample_variance_total_amount_of_invoices(self) -> Self {
        self.sample_variance_total_amount_of_invoices_as("sample_variance_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sample_variance_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().var_samp("total_amount", "varSamp_total_amount"))
    }
    pub fn sample_population_variance_total_amount_of_invoices(self) -> Self {
        self.sample_population_variance_total_amount_of_invoices_as("sample_population_variance_total_amount_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn sample_population_variance_total_amount_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().var_pop("total_amount", "varPop_total_amount"))
    }
    pub fn min_create_time_of_invoices(self) -> Self {
        self.min_create_time_of_invoices_as("min_create_time_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_create_time_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_invoices(self) -> Self {
        self.max_create_time_of_invoices_as("max_create_time_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_create_time_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_invoices(self) -> Self {
        self.min_update_time_of_invoices_as("min_update_time_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn min_update_time_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_invoices(self) -> Self {
        self.max_update_time_of_invoices_as("max_update_time_of_invoices", crate::Q::invoices().unlimited())
    }

    pub fn max_update_time_of_invoices_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_invoices_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }

    pub fn count_contracts(self) -> Self {
        self.count_contracts_as("count_contracts")
    }

    pub fn count_contracts_as(self, alias: impl Into<String>) -> Self {
        self.count_contracts_with(alias, crate::Q::contracts().unlimited())
    }

    pub fn count_contracts_with(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "contract_list",
            alias,
            selection,
            true,
        ));
        self
    }

    pub fn stats_from_contracts(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as("refinements", request)
    }

    pub fn stats_from_contracts_as(mut self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        let selection = request.into();
        self.query_options.relation_aggregates.push(RelationAggregate::new(
            "contract_list",
            alias,
            selection,
            false,
        ));
        self
    }

    pub fn group_by_contracts_with_details(self, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts(request)
    }


    pub fn min_start_date_of_contracts(self) -> Self {
        self.min_start_date_of_contracts_as("min_start_date_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn min_start_date_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().min("start_date", "min_start_date"))
    }
    pub fn max_start_date_of_contracts(self) -> Self {
        self.max_start_date_of_contracts_as("max_start_date_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn max_start_date_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().max("start_date", "max_start_date"))
    }
    pub fn min_end_date_of_contracts(self) -> Self {
        self.min_end_date_of_contracts_as("min_end_date_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn min_end_date_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().min("end_date", "min_end_date"))
    }
    pub fn max_end_date_of_contracts(self) -> Self {
        self.max_end_date_of_contracts_as("max_end_date_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn max_end_date_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().max("end_date", "max_end_date"))
    }
    pub fn min_create_time_of_contracts(self) -> Self {
        self.min_create_time_of_contracts_as("min_create_time_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn min_create_time_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().min("create_time", "min_create_time"))
    }
    pub fn max_create_time_of_contracts(self) -> Self {
        self.max_create_time_of_contracts_as("max_create_time_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn max_create_time_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().max("create_time", "max_create_time"))
    }
    pub fn min_update_time_of_contracts(self) -> Self {
        self.min_update_time_of_contracts_as("min_update_time_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn min_update_time_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().min("update_time", "min_update_time"))
    }
    pub fn max_update_time_of_contracts(self) -> Self {
        self.max_update_time_of_contracts_as("max_update_time_of_contracts", crate::Q::contracts().unlimited())
    }

    pub fn max_update_time_of_contracts_as(self, alias: impl Into<String>, request: impl Into<QuerySelection>) -> Self {
        self.stats_from_contracts_as(alias, request.into().into_query().max("update_time", "max_update_time"))
    }
}

impl<R> Default for CustomerRequest<R> {
    fn default() -> Self {
        Self::new()
    }
}

impl<R> From< CustomerRequest<R> > for SelectQuery {
    fn from(request: CustomerRequest<R>) -> Self {
        QuerySelection::from(request).into_query()
    }
}

impl<R> From< CustomerRequest<R> > for QuerySelection {
    fn from(request: CustomerRequest<R>) -> Self {
        Self {
            query: request.query,
            relation_selections: request.relation_selections,
            relation_filters: request.relation_filters,
            child_enhancements: request.child_enhancements,
            query_options: request.query_options,
        }
    }
}


impl<'a, C> crate::request_support::AuditedSave<'a, C> for teaql_core::Audited<crate::Customer> 
where C: crate::request_support::TeaqlRepositoryProvider + ?Sized + 'a
{
    type Error = crate::TeaqlDataServiceError<C::CustomerRepository<'a>>;
    fn save(self, ctx: &'a C) -> std::pin::Pin<Box<dyn std::future::Future<Output = Result<teaql_runtime::GraphNode, Self::Error>> + '_>> {
        Box::pin(async move {
            teaql_runtime::save_audited_ledger_entity(self, ctx.user_context())
                .await
                .map_err(DataServiceError::Runtime)
        })
    }
}

impl<R: teaql_core::Entity> crate::PurposedQuery<CustomerRequest<R>> {
    pub fn new_entity<C>(&self, ctx: &C) -> crate::Customer
    where
        C: crate::TeaqlRuntime + ?Sized,
    {
        crate::Customer::runtime_new(ctx.user_context().entity_root())
    }

    fn into_inner_with_trace(mut self) -> CustomerRequest<R> {
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
    ) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_page(ctx, offset, limit).await
    }

    pub async fn execute_for_exists<'a, C>(
        self,
        ctx: &'a C,
    ) -> Result<bool, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_exists(ctx).await
    }

    pub async fn execute_for_list<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<R>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_list(ctx).await
    }

    /// Execute query in streaming mode (chunked).
    /// Returns a Vec of StreamChunk, each containing up to chunk_size rows.
    /// Set chunk size via .stream(chunk_size) or .stream_default() on the query.
    pub async fn execute_for_stream<'a, C>(self, ctx: &'a C) -> Result<Vec<teaql_data_service::StreamChunk>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_stream(ctx).await
    }

    pub async fn execute_for_first<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_first(ctx).await
    }

    pub async fn execute_for_one<'a, C>(self, ctx: &'a C) -> Result<Option<R>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_one(ctx).await
    }


    pub async fn execute_for_records<'a, C>(self, ctx: &'a C) -> Result<teaql_core::SmartList<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_records(ctx).await
    }

    pub async fn execute_for_record<'a, C>(self, ctx: &'a C) -> Result<Option<teaql_core::Record>, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_record(ctx).await
    }

    pub async fn execute_for_count<'a, C>(self, ctx: &'a C) -> Result<u64, crate::request_support::TeaqlDataServiceError<C::CustomerRepository<'a>>>
    where
        C: crate::request_support::TeaqlRepositoryProvider + ?Sized,
    {
        self.into_inner_with_trace()._execute_for_count(ctx).await
    }
}
