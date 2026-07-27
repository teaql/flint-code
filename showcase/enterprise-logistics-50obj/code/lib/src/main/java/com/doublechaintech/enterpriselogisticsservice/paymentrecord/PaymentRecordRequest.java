package com.doublechaintech.enterpriselogisticsservice.paymentrecord;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class PaymentRecordRequest<T extends PaymentRecord> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PaymentRecordRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PaymentRecordRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PaymentRecordRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PaymentRecordRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PaymentRecordRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PaymentRecordRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PaymentRecordRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PaymentRecordRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PaymentRecordRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PaymentRecordRequest<T> matchingAnyOf(PaymentRecordRequest paymentRecord){
        super.internalMatchAny(paymentRecord);
        return this;
    }

    public PaymentRecordRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PaymentRecordRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PaymentRecordRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PaymentRecordRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectStatus().selectCreatedAt().selectUpdatedAt().selectInvoiceIdOnly().selectVersion();
    }

    public PaymentRecordRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PaymentRecordRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectStatus().selectCreatedAt().selectUpdatedAt().selectInvoice().selectVersion();
    }

    public PaymentRecordRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectStatus().selectCreatedAt().selectUpdatedAt().selectInvoice().selectVersion();
    }


    public PaymentRecordRequest<T> selectId(){
       selectProperty(PaymentRecord.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectId(){
       unselectProperty(PaymentRecord.ID_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectName(){
       selectProperty(PaymentRecord.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectName(){
       unselectProperty(PaymentRecord.NAME_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectCode(){
       selectProperty(PaymentRecord.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectCode(){
       unselectProperty(PaymentRecord.CODE_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectAmount(){
       selectProperty(PaymentRecord.AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the amount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  amount) to fetch amount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the amount with customized aggrFunction, TEAQL uses ({aggrFunction}(amount) AS amount to fetch amount property.
     * @param aggrFunction  aggrFunction
     */
    public PaymentRecordRequest<T> selectAmount(AggrFunction aggrFunction){
       selectProperty(PaymentRecord.AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public PaymentRecordRequest<T> unselectAmount(){
       unselectProperty(PaymentRecord.AMOUNT_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectCurrency(){
       selectProperty(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectCurrency(){
       unselectProperty(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectStatus(){
       selectProperty(PaymentRecord.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectStatus(){
       unselectProperty(PaymentRecord.STATUS_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectCreatedAt(){
       selectProperty(PaymentRecord.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectCreatedAt(){
       unselectProperty(PaymentRecord.CREATED_AT_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectUpdatedAt(){
       selectProperty(PaymentRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectUpdatedAt(){
       unselectProperty(PaymentRecord.UPDATED_AT_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectInvoiceIdOnly(){
       selectProperty(PaymentRecord.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> selectInvoice(){
        return selectInvoiceWith(Q.invoices().unlimited().selectSelf());
    }

    public PaymentRecordRequest<T> selectInvoiceWith(InvoiceRequest invoice){
       selectProperty(PaymentRecord.INVOICE_PROPERTY);
       enhanceRelation(PaymentRecord.INVOICE_PROPERTY, invoice);
       return this;
    }

    public PaymentRecordRequest<T> unselectInvoice(){
       unselectProperty(PaymentRecord.INVOICE_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> selectVersion(){
       selectProperty(PaymentRecord.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRecordRequest<T> unselectVersion(){
       unselectProperty(PaymentRecord.VERSION_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.ID_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PaymentRecordRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PaymentRecordRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public PaymentRecordRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.NAME_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public PaymentRecordRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public PaymentRecordRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public PaymentRecordRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public PaymentRecordRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public PaymentRecordRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public PaymentRecordRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public PaymentRecordRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public PaymentRecordRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public PaymentRecordRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public PaymentRecordRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public PaymentRecordRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.CODE_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public PaymentRecordRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public PaymentRecordRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public PaymentRecordRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public PaymentRecordRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public PaymentRecordRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public PaymentRecordRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public PaymentRecordRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public PaymentRecordRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public PaymentRecordRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public PaymentRecordRequest<T> filterByAmount(BigDecimal... amount){
      if (amount == null || amount.length == 0) {
        throw new IllegalArgumentException("filterByAmount parameter amount cannot be empty");
      }
      return appendSearchCriteria(createAmountCriteria(Operator.EQUAL, (Object[])amount));
    }

    public PaymentRecordRequest<T> withAmount(Operator operator, Object... values){
       return appendSearchCriteria(createAmountCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withAmountIsUnknown(){
       return withAmount(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withAmountIsKnown(){
       return withAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.AMOUNT_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withAmountGreaterThan(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN, amount);
    }

    public PaymentRecordRequest<T> withAmountGreaterThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN_OR_EQUAL, amount);
    }

    public PaymentRecordRequest<T> withAmountLessThan(BigDecimal amount){
       return withAmount(Operator.LESS_THAN, amount);
    }

    public PaymentRecordRequest<T> withAmountLessThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.LESS_THAN_OR_EQUAL, amount);
    }

    public PaymentRecordRequest<T> withAmountBetween(BigDecimal startOfAmount, BigDecimal endOfAmount){
       return withAmount(Operator.BETWEEN, startOfAmount, endOfAmount);
    }



    public PaymentRecordRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public PaymentRecordRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.CURRENCY_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public PaymentRecordRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public PaymentRecordRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public PaymentRecordRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public PaymentRecordRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public PaymentRecordRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public PaymentRecordRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public PaymentRecordRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public PaymentRecordRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public PaymentRecordRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public PaymentRecordRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public PaymentRecordRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.STATUS_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public PaymentRecordRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public PaymentRecordRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public PaymentRecordRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public PaymentRecordRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public PaymentRecordRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public PaymentRecordRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public PaymentRecordRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public PaymentRecordRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public PaymentRecordRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public PaymentRecordRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public PaymentRecordRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.CREATED_AT_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public PaymentRecordRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PaymentRecordRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public PaymentRecordRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public PaymentRecordRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.UPDATED_AT_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public PaymentRecordRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PaymentRecordRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public PaymentRecordRequest<T> filterByInvoice(Invoice... invoice){
      if (invoice == null || invoice.length == 0) {
        throw new IllegalArgumentException("filterByInvoice parameter invoice cannot be empty");
      }
      return appendSearchCriteria(createInvoiceCriteria(Operator.EQUAL, (Object[])invoice));
    }

    public PaymentRecordRequest<T> withInvoice(Operator operator, Object... values){
       return appendSearchCriteria(createInvoiceCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withInvoiceIsUnknown(){
       return withInvoice(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withInvoiceIsKnown(){
       return withInvoice(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createInvoiceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.INVOICE_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> filterByInvoice(Long invoice){
      if(invoice == null){
         return this;
      }
      return withInvoice(Operator.EQUAL, invoice);
    }
    public PaymentRecordRequest<T> withInvoiceMatching(InvoiceRequest invoice){
       return appendSearchCriteria(new SubQuerySearchCriteria(PaymentRecord.INVOICE_PROPERTY, invoice, Invoice.ID_PROPERTY));
    }

    public PaymentRecordRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PaymentRecordRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PaymentRecordRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PaymentRecordRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PaymentRecord.VERSION_PROPERTY, operator, values);
    }

    public PaymentRecordRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PaymentRecordRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PaymentRecordRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PaymentRecordRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PaymentRecordRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public PaymentRecordRequest<T> count(){
        super.count();
        return this;
    }
    public PaymentRecordRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PaymentRecordRequest minAmount(){
        return minAmountAs(prefix("minOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest minAmountAs(String retName){
        super.min(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest maxAmount(){
        return maxAmountAs(prefix("maxOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest maxAmountAs(String retName){
        super.max(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest sumAmount(){
        return sumAmountAs(prefix("sumOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest sumAmountAs(String retName){
        super.sum(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest avgAmount(){
        return avgAmountAs(prefix("avgOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest avgAmountAs(String retName){
        super.avg(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest standardDeviationAmount(){
        return standardDeviationAmountAs(prefix("standardDeviationOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest standardDeviationAmountAs(String retName){
        super.standardDeviation(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest squareRootOfPopulationStandardDeviationAmount(){
        return squareRootOfPopulationStandardDeviationAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest squareRootOfPopulationStandardDeviationAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest sampleVarianceAmount(){
        return sampleVarianceAmountAs(prefix("sampleVarianceOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest sampleVarianceAmountAs(String retName){
        super.sampleVariance(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest samplePopulationVarianceAmount(){
        return samplePopulationVarianceAmountAs(prefix("samplePopulationVarianceOf",PaymentRecord.AMOUNT_PROPERTY));
    }

    public PaymentRecordRequest samplePopulationVarianceAmountAs(String retName){
        super.samplePopulationVariance(retName, PaymentRecord.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRecordRequest<T> groupByInvoiceWithDetails(){
       return groupByInvoiceWithDetails(Q.invoices().unlimited());
    }

    public PaymentRecordRequest<T> groupByInvoiceWithDetails(InvoiceRequest subRequest){
       aggregate(PaymentRecord.INVOICE_PROPERTY, subRequest);
       return this;
    }



    public PaymentRecordRequest<T> groupById(){
       groupBy(PaymentRecord.ID_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByIdAs(String retName){
       groupBy(retName, PaymentRecord.ID_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.ID_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByName(){
       groupBy(PaymentRecord.NAME_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByNameAs(String retName){
       groupBy(retName, PaymentRecord.NAME_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.NAME_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByCode(){
       groupBy(PaymentRecord.CODE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByCodeAs(String retName){
       groupBy(retName, PaymentRecord.CODE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.CODE_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByAmount(){
       groupBy(PaymentRecord.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByAmountAs(String retName){
       groupBy(retName, PaymentRecord.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.AMOUNT_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByCurrency(){
       groupBy(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.CURRENCY_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByStatus(){
       groupBy(PaymentRecord.STATUS_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByStatusAs(String retName){
       groupBy(retName, PaymentRecord.STATUS_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.STATUS_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByCreatedAt(){
       groupBy(PaymentRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, PaymentRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.CREATED_AT_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByUpdatedAt(){
       groupBy(PaymentRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, PaymentRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.UPDATED_AT_PROPERTY, function);
       return this;
    }
    public PaymentRecordRequest<T> groupByInvoiceWith(InvoiceRequest subRequest){
       groupBy(PaymentRecord.INVOICE_PROPERTY, subRequest);
       return this;
    }
    public PaymentRecordRequest<T> groupByInvoice(){
       groupBy(PaymentRecord.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByInvoiceAs(String retName){
       groupBy(retName, PaymentRecord.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByInvoiceWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.INVOICE_PROPERTY, function);
       return this;
    }

    public PaymentRecordRequest<T> groupByVersion(){
       groupBy(PaymentRecord.VERSION_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByVersionAs(String retName){
       groupBy(retName, PaymentRecord.VERSION_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, PaymentRecord.VERSION_PROPERTY, function);
       return this;
    }



    public PaymentRecordRequest<T> orderByIdAscending(){
       addOrderByAscending(PaymentRecord.ID_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByIdDescending(){
       addOrderByDescending(PaymentRecord.ID_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByNameAscending(){
       addOrderByAscending(PaymentRecord.NAME_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByNameDescending(){
       addOrderByDescending(PaymentRecord.NAME_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PaymentRecord.NAME_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PaymentRecord.NAME_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByCodeAscending(){
       addOrderByAscending(PaymentRecord.CODE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByCodeDescending(){
       addOrderByDescending(PaymentRecord.CODE_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PaymentRecord.CODE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PaymentRecord.CODE_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByAmountAscending(){
       addOrderByAscending(PaymentRecord.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByAmountDescending(){
       addOrderByDescending(PaymentRecord.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PaymentRecord.CURRENCY_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByStatusAscending(){
       addOrderByAscending(PaymentRecord.STATUS_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByStatusDescending(){
       addOrderByDescending(PaymentRecord.STATUS_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PaymentRecord.STATUS_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PaymentRecord.STATUS_PROPERTY);
       return this;
    }
    public PaymentRecordRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(PaymentRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(PaymentRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(PaymentRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(PaymentRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByInvoiceAscending(){
       addOrderByAscending(PaymentRecord.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByInvoiceDescending(){
       addOrderByDescending(PaymentRecord.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByVersionAscending(){
       addOrderByAscending(PaymentRecord.VERSION_PROPERTY);
       return this;
    }

    public PaymentRecordRequest<T> orderByVersionDescending(){
       addOrderByDescending(PaymentRecord.VERSION_PROPERTY);
       return this;
    }


    public InvoiceRequest rollUpToInvoice(){
       InvoiceRequest invoice = Q.invoices().unlimited();
       this.withInvoiceMatching(invoice)
           .groupByInvoiceWith(invoice);
       return invoice;
    }



   public PaymentRecordRequest<T> facetByInvoiceAs(String facetName, InvoiceRequest invoice){
       return facetByInvoiceAs(facetName, invoice, true);
   }

   public PaymentRecordRequest<T> facetByInvoiceAs(String facetName, InvoiceRequest invoice, boolean includeAllFacets){
       addFacet(facetName, PaymentRecord.INVOICE_PROPERTY, invoice, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public PaymentRecordRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PaymentRecordRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PaymentRecordRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PaymentRecordRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PaymentRecordRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}