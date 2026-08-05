package com.doublechaintech.enterpriselogisticsservice.taxrecord;

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

public class TaxRecordRequest<T extends TaxRecord> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public TaxRecordRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public TaxRecordRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public TaxRecordRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public TaxRecordRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public TaxRecordRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public TaxRecordRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public TaxRecordRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (TaxRecordRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public TaxRecordRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public TaxRecordRequest<T> matchingAnyOf(TaxRecordRequest taxRecord){
        super.internalMatchAny(taxRecord);
        return this;
    }

    public TaxRecordRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public TaxRecordRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public TaxRecordRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public TaxRecordRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectTaxRate().selectTaxType().selectCreatedAt().selectUpdatedAt().selectInvoiceIdOnly().selectVersion();
    }

    public TaxRecordRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TaxRecordRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectTaxRate().selectTaxType().selectCreatedAt().selectUpdatedAt().selectInvoice().selectVersion();
    }

    public TaxRecordRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectTaxRate().selectTaxType().selectCreatedAt().selectUpdatedAt().selectInvoice().selectVersion();
    }


    public TaxRecordRequest<T> selectId(){
       selectProperty(TaxRecord.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectId(){
       unselectProperty(TaxRecord.ID_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectName(){
       selectProperty(TaxRecord.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectName(){
       unselectProperty(TaxRecord.NAME_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectCode(){
       selectProperty(TaxRecord.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectCode(){
       unselectProperty(TaxRecord.CODE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectAmount(){
       selectProperty(TaxRecord.AMOUNT_PROPERTY);
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
    public TaxRecordRequest<T> selectAmount(AggrFunction aggrFunction){
       selectProperty(TaxRecord.AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public TaxRecordRequest<T> unselectAmount(){
       unselectProperty(TaxRecord.AMOUNT_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectCurrency(){
       selectProperty(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectCurrency(){
       unselectProperty(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectTaxRate(){
       selectProperty(TaxRecord.TAX_RATE_PROPERTY);
       return this;
    }

    /**
     * fill the taxRate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  taxRate) to fetch taxRate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the taxRate with customized aggrFunction, TEAQL uses ({aggrFunction}(taxRate) AS taxRate to fetch taxRate property.
     * @param aggrFunction  aggrFunction
     */
    public TaxRecordRequest<T> selectTaxRate(AggrFunction aggrFunction){
       selectProperty(TaxRecord.TAX_RATE_PROPERTY, aggrFunction);
       return this;
    }


    public TaxRecordRequest<T> unselectTaxRate(){
       unselectProperty(TaxRecord.TAX_RATE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectTaxType(){
       selectProperty(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the taxType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  taxType) to fetch taxType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectTaxType(){
       unselectProperty(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectCreatedAt(){
       selectProperty(TaxRecord.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectCreatedAt(){
       unselectProperty(TaxRecord.CREATED_AT_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectUpdatedAt(){
       selectProperty(TaxRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectUpdatedAt(){
       unselectProperty(TaxRecord.UPDATED_AT_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectInvoiceIdOnly(){
       selectProperty(TaxRecord.INVOICE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> selectInvoice(){
        return selectInvoiceWith(Q.invoices().unlimited().selectSelf());
    }

    public TaxRecordRequest<T> selectInvoiceWith(InvoiceRequest invoice){
       selectProperty(TaxRecord.INVOICE_PROPERTY);
       enhanceRelation(TaxRecord.INVOICE_PROPERTY, invoice);
       return this;
    }

    public TaxRecordRequest<T> unselectInvoice(){
       unselectProperty(TaxRecord.INVOICE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectVersion(){
       selectProperty(TaxRecord.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectVersion(){
       unselectProperty(TaxRecord.VERSION_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.ID_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public TaxRecordRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public TaxRecordRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public TaxRecordRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public TaxRecordRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.NAME_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public TaxRecordRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public TaxRecordRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public TaxRecordRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public TaxRecordRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public TaxRecordRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public TaxRecordRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public TaxRecordRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public TaxRecordRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public TaxRecordRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public TaxRecordRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public TaxRecordRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public TaxRecordRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.CODE_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public TaxRecordRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public TaxRecordRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public TaxRecordRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public TaxRecordRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public TaxRecordRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public TaxRecordRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public TaxRecordRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public TaxRecordRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public TaxRecordRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public TaxRecordRequest<T> filterByAmount(BigDecimal... amount){
      if (amount == null || amount.length == 0) {
        throw new IllegalArgumentException("filterByAmount parameter amount cannot be empty");
      }
      return appendSearchCriteria(createAmountCriteria(Operator.EQUAL, (Object[])amount));
    }

    public TaxRecordRequest<T> withAmount(Operator operator, Object... values){
       return appendSearchCriteria(createAmountCriteria(operator, values));
    }

    public TaxRecordRequest<T> withAmountIsUnknown(){
       return withAmount(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withAmountIsKnown(){
       return withAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.AMOUNT_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withAmountGreaterThan(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN, amount);
    }

    public TaxRecordRequest<T> withAmountGreaterThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN_OR_EQUAL, amount);
    }

    public TaxRecordRequest<T> withAmountLessThan(BigDecimal amount){
       return withAmount(Operator.LESS_THAN, amount);
    }

    public TaxRecordRequest<T> withAmountLessThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.LESS_THAN_OR_EQUAL, amount);
    }

    public TaxRecordRequest<T> withAmountBetween(BigDecimal startOfAmount, BigDecimal endOfAmount){
       return withAmount(Operator.BETWEEN, startOfAmount, endOfAmount);
    }



    public TaxRecordRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public TaxRecordRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public TaxRecordRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.CURRENCY_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public TaxRecordRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public TaxRecordRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public TaxRecordRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public TaxRecordRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public TaxRecordRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public TaxRecordRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public TaxRecordRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public TaxRecordRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public TaxRecordRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public TaxRecordRequest<T> filterByTaxRate(BigDecimal... taxRate){
      if (taxRate == null || taxRate.length == 0) {
        throw new IllegalArgumentException("filterByTaxRate parameter taxRate cannot be empty");
      }
      return appendSearchCriteria(createTaxRateCriteria(Operator.EQUAL, (Object[])taxRate));
    }

    public TaxRecordRequest<T> withTaxRate(Operator operator, Object... values){
       return appendSearchCriteria(createTaxRateCriteria(operator, values));
    }

    public TaxRecordRequest<T> withTaxRateIsUnknown(){
       return withTaxRate(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withTaxRateIsKnown(){
       return withTaxRate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTaxRateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.TAX_RATE_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withTaxRateGreaterThan(BigDecimal taxRate){
       return withTaxRate(Operator.GREATER_THAN, taxRate);
    }

    public TaxRecordRequest<T> withTaxRateGreaterThanOrEqualTo(BigDecimal taxRate){
       return withTaxRate(Operator.GREATER_THAN_OR_EQUAL, taxRate);
    }

    public TaxRecordRequest<T> withTaxRateLessThan(BigDecimal taxRate){
       return withTaxRate(Operator.LESS_THAN, taxRate);
    }

    public TaxRecordRequest<T> withTaxRateLessThanOrEqualTo(BigDecimal taxRate){
       return withTaxRate(Operator.LESS_THAN_OR_EQUAL, taxRate);
    }

    public TaxRecordRequest<T> withTaxRateBetween(BigDecimal startOfTaxRate, BigDecimal endOfTaxRate){
       return withTaxRate(Operator.BETWEEN, startOfTaxRate, endOfTaxRate);
    }



    public TaxRecordRequest<T> filterByTaxType(String... taxType){
      if (taxType == null || taxType.length == 0) {
        throw new IllegalArgumentException("filterByTaxType parameter taxType cannot be empty");
      }
      return appendSearchCriteria(createTaxTypeCriteria(Operator.EQUAL, (Object[])taxType));
    }

    public TaxRecordRequest<T> withTaxType(Operator operator, Object... values){
       return appendSearchCriteria(createTaxTypeCriteria(operator, values));
    }

    public TaxRecordRequest<T> withTaxTypeIsUnknown(){
       return withTaxType(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withTaxTypeIsKnown(){
       return withTaxType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTaxTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.TAX_TYPE_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withTaxTypeGreaterThan(String taxType){
       return withTaxType(Operator.GREATER_THAN, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeGreaterThanOrEqualTo(String taxType){
       return withTaxType(Operator.GREATER_THAN_OR_EQUAL, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeLessThan(String taxType){
       return withTaxType(Operator.LESS_THAN, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeLessThanOrEqualTo(String taxType){
       return withTaxType(Operator.LESS_THAN_OR_EQUAL, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeBetween(String startOfTaxType, String endOfTaxType){
       return withTaxType(Operator.BETWEEN, startOfTaxType, endOfTaxType);
    }
    public TaxRecordRequest<T> withTaxTypeStartingWith(String taxType){
       return withTaxType(Operator.BEGIN_WITH, taxType);
    }
    public TaxRecordRequest<T> withTaxTypeContaining(String taxType){
       return withTaxType(Operator.CONTAIN, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeEndingWith(String taxType){
       return withTaxType(Operator.END_WITH, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeIs(String taxType){
       return withTaxType(Operator.EQUAL, taxType);
    }

    public TaxRecordRequest<T> withTaxTypeSoundingLike(String taxType){
       return withTaxType(Operator.SOUNDS_LIKE, taxType);
    }



    public TaxRecordRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public TaxRecordRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public TaxRecordRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.CREATED_AT_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public TaxRecordRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public TaxRecordRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public TaxRecordRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public TaxRecordRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public TaxRecordRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.UPDATED_AT_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public TaxRecordRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public TaxRecordRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public TaxRecordRequest<T> filterByInvoice(Invoice... invoice){
      if (invoice == null || invoice.length == 0) {
        throw new IllegalArgumentException("filterByInvoice parameter invoice cannot be empty");
      }
      return appendSearchCriteria(createInvoiceCriteria(Operator.EQUAL, (Object[])invoice));
    }

    public TaxRecordRequest<T> withInvoice(Operator operator, Object... values){
       return appendSearchCriteria(createInvoiceCriteria(operator, values));
    }

    public TaxRecordRequest<T> withInvoiceIsUnknown(){
       return withInvoice(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withInvoiceIsKnown(){
       return withInvoice(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createInvoiceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.INVOICE_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> filterByInvoice(Long invoice){
      if(invoice == null){
         return this;
      }
      return withInvoice(Operator.EQUAL, invoice);
    }
    public TaxRecordRequest<T> withInvoiceMatching(InvoiceRequest invoice){
       return appendSearchCriteria(new SubQuerySearchCriteria(TaxRecord.INVOICE_PROPERTY, invoice, Invoice.ID_PROPERTY));
    }

    public TaxRecordRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public TaxRecordRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public TaxRecordRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.VERSION_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public TaxRecordRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public TaxRecordRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public TaxRecordRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public TaxRecordRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public TaxRecordRequest<T> count(){
        super.count();
        return this;
    }
    public TaxRecordRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public TaxRecordRequest minAmount(){
        return minAmountAs(prefix("minOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest minAmountAs(String retName){
        super.min(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest maxAmount(){
        return maxAmountAs(prefix("maxOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest maxAmountAs(String retName){
        super.max(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest sumAmount(){
        return sumAmountAs(prefix("sumOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest sumAmountAs(String retName){
        super.sum(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest avgAmount(){
        return avgAmountAs(prefix("avgOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest avgAmountAs(String retName){
        super.avg(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest standardDeviationAmount(){
        return standardDeviationAmountAs(prefix("standardDeviationOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest standardDeviationAmountAs(String retName){
        super.standardDeviation(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest squareRootOfPopulationStandardDeviationAmount(){
        return squareRootOfPopulationStandardDeviationAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest squareRootOfPopulationStandardDeviationAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest sampleVarianceAmount(){
        return sampleVarianceAmountAs(prefix("sampleVarianceOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest sampleVarianceAmountAs(String retName){
        super.sampleVariance(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest samplePopulationVarianceAmount(){
        return samplePopulationVarianceAmountAs(prefix("samplePopulationVarianceOf",TaxRecord.AMOUNT_PROPERTY));
    }

    public TaxRecordRequest samplePopulationVarianceAmountAs(String retName){
        super.samplePopulationVariance(retName, TaxRecord.AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest minTaxRate(){
        return minTaxRateAs(prefix("minOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest minTaxRateAs(String retName){
        super.min(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest maxTaxRate(){
        return maxTaxRateAs(prefix("maxOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest maxTaxRateAs(String retName){
        super.max(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest sumTaxRate(){
        return sumTaxRateAs(prefix("sumOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest sumTaxRateAs(String retName){
        super.sum(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest avgTaxRate(){
        return avgTaxRateAs(prefix("avgOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest avgTaxRateAs(String retName){
        super.avg(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest standardDeviationTaxRate(){
        return standardDeviationTaxRateAs(prefix("standardDeviationOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest standardDeviationTaxRateAs(String retName){
        super.standardDeviation(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest squareRootOfPopulationStandardDeviationTaxRate(){
        return squareRootOfPopulationStandardDeviationTaxRateAs(prefix("squareRootOfPopulationStandardDeviationOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest squareRootOfPopulationStandardDeviationTaxRateAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest sampleVarianceTaxRate(){
        return sampleVarianceTaxRateAs(prefix("sampleVarianceOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest sampleVarianceTaxRateAs(String retName){
        super.sampleVariance(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest samplePopulationVarianceTaxRate(){
        return samplePopulationVarianceTaxRateAs(prefix("samplePopulationVarianceOf",TaxRecord.TAX_RATE_PROPERTY));
    }

    public TaxRecordRequest samplePopulationVarianceTaxRateAs(String retName){
        super.samplePopulationVariance(retName, TaxRecord.TAX_RATE_PROPERTY);
        return this;
    }
    public TaxRecordRequest<T> groupByInvoiceWithDetails(){
       return groupByInvoiceWithDetails(Q.invoices().unlimited());
    }

    public TaxRecordRequest<T> groupByInvoiceWithDetails(InvoiceRequest subRequest){
       aggregate(TaxRecord.INVOICE_PROPERTY, subRequest);
       return this;
    }



    public TaxRecordRequest<T> groupById(){
       groupBy(TaxRecord.ID_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByIdAs(String retName){
       groupBy(retName, TaxRecord.ID_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.ID_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByName(){
       groupBy(TaxRecord.NAME_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByNameAs(String retName){
       groupBy(retName, TaxRecord.NAME_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.NAME_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByCode(){
       groupBy(TaxRecord.CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByCodeAs(String retName){
       groupBy(retName, TaxRecord.CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.CODE_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByAmount(){
       groupBy(TaxRecord.AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByAmountAs(String retName){
       groupBy(retName, TaxRecord.AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.AMOUNT_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByCurrency(){
       groupBy(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, TaxRecord.CURRENCY_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.CURRENCY_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxRate(){
       groupBy(TaxRecord.TAX_RATE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxRateAs(String retName){
       groupBy(retName, TaxRecord.TAX_RATE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxRateWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.TAX_RATE_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxType(){
       groupBy(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxTypeAs(String retName){
       groupBy(retName, TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.TAX_TYPE_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByCreatedAt(){
       groupBy(TaxRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, TaxRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.CREATED_AT_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByUpdatedAt(){
       groupBy(TaxRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, TaxRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.UPDATED_AT_PROPERTY, function);
       return this;
    }
    public TaxRecordRequest<T> groupByInvoiceWith(InvoiceRequest subRequest){
       groupBy(TaxRecord.INVOICE_PROPERTY, subRequest);
       return this;
    }
    public TaxRecordRequest<T> groupByInvoice(){
       groupBy(TaxRecord.INVOICE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByInvoiceAs(String retName){
       groupBy(retName, TaxRecord.INVOICE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByInvoiceWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.INVOICE_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByVersion(){
       groupBy(TaxRecord.VERSION_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByVersionAs(String retName){
       groupBy(retName, TaxRecord.VERSION_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.VERSION_PROPERTY, function);
       return this;
    }



    public TaxRecordRequest<T> orderByIdAscending(){
       addOrderByAscending(TaxRecord.ID_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByIdDescending(){
       addOrderByDescending(TaxRecord.ID_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByNameAscending(){
       addOrderByAscending(TaxRecord.NAME_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByNameDescending(){
       addOrderByDescending(TaxRecord.NAME_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.NAME_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.NAME_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByCodeAscending(){
       addOrderByAscending(TaxRecord.CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByCodeDescending(){
       addOrderByDescending(TaxRecord.CODE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.CODE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByAmountAscending(){
       addOrderByAscending(TaxRecord.AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByAmountDescending(){
       addOrderByDescending(TaxRecord.AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.CURRENCY_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByTaxRateAscending(){
       addOrderByAscending(TaxRecord.TAX_RATE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxRateDescending(){
       addOrderByDescending(TaxRecord.TAX_RATE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxTypeAscending(){
       addOrderByAscending(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxTypeDescending(){
       addOrderByDescending(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByTaxTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.TAX_TYPE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(TaxRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(TaxRecord.CREATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(TaxRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(TaxRecord.UPDATED_AT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByInvoiceAscending(){
       addOrderByAscending(TaxRecord.INVOICE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByInvoiceDescending(){
       addOrderByDescending(TaxRecord.INVOICE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByVersionAscending(){
       addOrderByAscending(TaxRecord.VERSION_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByVersionDescending(){
       addOrderByDescending(TaxRecord.VERSION_PROPERTY);
       return this;
    }


    public InvoiceRequest rollUpToInvoice(){
       InvoiceRequest invoice = Q.invoices().unlimited();
       this.withInvoiceMatching(invoice)
           .groupByInvoiceWith(invoice);
       return invoice;
    }



   public TaxRecordRequest<T> facetByInvoiceAs(String facetName, InvoiceRequest invoice){
       return facetByInvoiceAs(facetName, invoice, true);
   }

   public TaxRecordRequest<T> facetByInvoiceAs(String facetName, InvoiceRequest invoice, boolean includeAllFacets){
       addFacet(facetName, TaxRecord.INVOICE_PROPERTY, invoice, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public TaxRecordRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public TaxRecordRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public TaxRecordRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public TaxRecordRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public TaxRecordRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}