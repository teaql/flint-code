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
        return selectId().selectName().selectTaxCode().selectTaxAmount().selectCurrency().selectTaxRate().selectTaxPeriod().selectFilingStatus().selectInvoiceIdOnly().selectVersion();
    }

    public TaxRecordRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TaxRecordRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectTaxCode().selectTaxAmount().selectCurrency().selectTaxRate().selectTaxPeriod().selectFilingStatus().selectInvoice().selectVersion();
    }

    public TaxRecordRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectTaxCode().selectTaxAmount().selectCurrency().selectTaxRate().selectTaxPeriod().selectFilingStatus().selectInvoice().selectVersion();
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
    public TaxRecordRequest<T> selectTaxCode(){
       selectProperty(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the taxCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  taxCode) to fetch taxCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectTaxCode(){
       unselectProperty(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectTaxAmount(){
       selectProperty(TaxRecord.TAX_AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the taxAmount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  taxAmount) to fetch taxAmount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the taxAmount with customized aggrFunction, TEAQL uses ({aggrFunction}(taxAmount) AS taxAmount to fetch taxAmount property.
     * @param aggrFunction  aggrFunction
     */
    public TaxRecordRequest<T> selectTaxAmount(AggrFunction aggrFunction){
       selectProperty(TaxRecord.TAX_AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public TaxRecordRequest<T> unselectTaxAmount(){
       unselectProperty(TaxRecord.TAX_AMOUNT_PROPERTY);
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
    public TaxRecordRequest<T> selectTaxPeriod(){
       selectProperty(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }

    /**
     * fill the taxPeriod with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  taxPeriod) to fetch taxPeriod property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectTaxPeriod(){
       unselectProperty(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> selectFilingStatus(){
       selectProperty(TaxRecord.FILING_STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the filingStatus with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  filingStatus) to fetch filingStatus property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TaxRecordRequest<T> unselectFilingStatus(){
       unselectProperty(TaxRecord.FILING_STATUS_PROPERTY);
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



    public TaxRecordRequest<T> filterByTaxCode(String... taxCode){
      if (taxCode == null || taxCode.length == 0) {
        throw new IllegalArgumentException("filterByTaxCode parameter taxCode cannot be empty");
      }
      return appendSearchCriteria(createTaxCodeCriteria(Operator.EQUAL, (Object[])taxCode));
    }

    public TaxRecordRequest<T> withTaxCode(Operator operator, Object... values){
       return appendSearchCriteria(createTaxCodeCriteria(operator, values));
    }

    public TaxRecordRequest<T> withTaxCodeIsUnknown(){
       return withTaxCode(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withTaxCodeIsKnown(){
       return withTaxCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTaxCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.TAX_CODE_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withTaxCodeGreaterThan(String taxCode){
       return withTaxCode(Operator.GREATER_THAN, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeGreaterThanOrEqualTo(String taxCode){
       return withTaxCode(Operator.GREATER_THAN_OR_EQUAL, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeLessThan(String taxCode){
       return withTaxCode(Operator.LESS_THAN, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeLessThanOrEqualTo(String taxCode){
       return withTaxCode(Operator.LESS_THAN_OR_EQUAL, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeBetween(String startOfTaxCode, String endOfTaxCode){
       return withTaxCode(Operator.BETWEEN, startOfTaxCode, endOfTaxCode);
    }
    public TaxRecordRequest<T> withTaxCodeStartingWith(String taxCode){
       return withTaxCode(Operator.BEGIN_WITH, taxCode);
    }
    public TaxRecordRequest<T> withTaxCodeContaining(String taxCode){
       return withTaxCode(Operator.CONTAIN, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeEndingWith(String taxCode){
       return withTaxCode(Operator.END_WITH, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeIs(String taxCode){
       return withTaxCode(Operator.EQUAL, taxCode);
    }

    public TaxRecordRequest<T> withTaxCodeSoundingLike(String taxCode){
       return withTaxCode(Operator.SOUNDS_LIKE, taxCode);
    }



    public TaxRecordRequest<T> filterByTaxAmount(BigDecimal... taxAmount){
      if (taxAmount == null || taxAmount.length == 0) {
        throw new IllegalArgumentException("filterByTaxAmount parameter taxAmount cannot be empty");
      }
      return appendSearchCriteria(createTaxAmountCriteria(Operator.EQUAL, (Object[])taxAmount));
    }

    public TaxRecordRequest<T> withTaxAmount(Operator operator, Object... values){
       return appendSearchCriteria(createTaxAmountCriteria(operator, values));
    }

    public TaxRecordRequest<T> withTaxAmountIsUnknown(){
       return withTaxAmount(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withTaxAmountIsKnown(){
       return withTaxAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTaxAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.TAX_AMOUNT_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withTaxAmountGreaterThan(BigDecimal taxAmount){
       return withTaxAmount(Operator.GREATER_THAN, taxAmount);
    }

    public TaxRecordRequest<T> withTaxAmountGreaterThanOrEqualTo(BigDecimal taxAmount){
       return withTaxAmount(Operator.GREATER_THAN_OR_EQUAL, taxAmount);
    }

    public TaxRecordRequest<T> withTaxAmountLessThan(BigDecimal taxAmount){
       return withTaxAmount(Operator.LESS_THAN, taxAmount);
    }

    public TaxRecordRequest<T> withTaxAmountLessThanOrEqualTo(BigDecimal taxAmount){
       return withTaxAmount(Operator.LESS_THAN_OR_EQUAL, taxAmount);
    }

    public TaxRecordRequest<T> withTaxAmountBetween(BigDecimal startOfTaxAmount, BigDecimal endOfTaxAmount){
       return withTaxAmount(Operator.BETWEEN, startOfTaxAmount, endOfTaxAmount);
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



    public TaxRecordRequest<T> filterByTaxPeriod(String... taxPeriod){
      if (taxPeriod == null || taxPeriod.length == 0) {
        throw new IllegalArgumentException("filterByTaxPeriod parameter taxPeriod cannot be empty");
      }
      return appendSearchCriteria(createTaxPeriodCriteria(Operator.EQUAL, (Object[])taxPeriod));
    }

    public TaxRecordRequest<T> withTaxPeriod(Operator operator, Object... values){
       return appendSearchCriteria(createTaxPeriodCriteria(operator, values));
    }

    public TaxRecordRequest<T> withTaxPeriodIsUnknown(){
       return withTaxPeriod(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withTaxPeriodIsKnown(){
       return withTaxPeriod(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTaxPeriodCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.TAX_PERIOD_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withTaxPeriodGreaterThan(String taxPeriod){
       return withTaxPeriod(Operator.GREATER_THAN, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodGreaterThanOrEqualTo(String taxPeriod){
       return withTaxPeriod(Operator.GREATER_THAN_OR_EQUAL, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodLessThan(String taxPeriod){
       return withTaxPeriod(Operator.LESS_THAN, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodLessThanOrEqualTo(String taxPeriod){
       return withTaxPeriod(Operator.LESS_THAN_OR_EQUAL, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodBetween(String startOfTaxPeriod, String endOfTaxPeriod){
       return withTaxPeriod(Operator.BETWEEN, startOfTaxPeriod, endOfTaxPeriod);
    }
    public TaxRecordRequest<T> withTaxPeriodStartingWith(String taxPeriod){
       return withTaxPeriod(Operator.BEGIN_WITH, taxPeriod);
    }
    public TaxRecordRequest<T> withTaxPeriodContaining(String taxPeriod){
       return withTaxPeriod(Operator.CONTAIN, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodEndingWith(String taxPeriod){
       return withTaxPeriod(Operator.END_WITH, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodIs(String taxPeriod){
       return withTaxPeriod(Operator.EQUAL, taxPeriod);
    }

    public TaxRecordRequest<T> withTaxPeriodSoundingLike(String taxPeriod){
       return withTaxPeriod(Operator.SOUNDS_LIKE, taxPeriod);
    }



    public TaxRecordRequest<T> filterByFilingStatus(String... filingStatus){
      if (filingStatus == null || filingStatus.length == 0) {
        throw new IllegalArgumentException("filterByFilingStatus parameter filingStatus cannot be empty");
      }
      return appendSearchCriteria(createFilingStatusCriteria(Operator.EQUAL, (Object[])filingStatus));
    }

    public TaxRecordRequest<T> withFilingStatus(Operator operator, Object... values){
       return appendSearchCriteria(createFilingStatusCriteria(operator, values));
    }

    public TaxRecordRequest<T> withFilingStatusIsUnknown(){
       return withFilingStatus(Operator.IS_NULL);
    }

    public TaxRecordRequest<T> withFilingStatusIsKnown(){
       return withFilingStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createFilingStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TaxRecord.FILING_STATUS_PROPERTY, operator, values);
    }

    public TaxRecordRequest<T> withFilingStatusGreaterThan(String filingStatus){
       return withFilingStatus(Operator.GREATER_THAN, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusGreaterThanOrEqualTo(String filingStatus){
       return withFilingStatus(Operator.GREATER_THAN_OR_EQUAL, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusLessThan(String filingStatus){
       return withFilingStatus(Operator.LESS_THAN, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusLessThanOrEqualTo(String filingStatus){
       return withFilingStatus(Operator.LESS_THAN_OR_EQUAL, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusBetween(String startOfFilingStatus, String endOfFilingStatus){
       return withFilingStatus(Operator.BETWEEN, startOfFilingStatus, endOfFilingStatus);
    }
    public TaxRecordRequest<T> withFilingStatusStartingWith(String filingStatus){
       return withFilingStatus(Operator.BEGIN_WITH, filingStatus);
    }
    public TaxRecordRequest<T> withFilingStatusContaining(String filingStatus){
       return withFilingStatus(Operator.CONTAIN, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusEndingWith(String filingStatus){
       return withFilingStatus(Operator.END_WITH, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusIs(String filingStatus){
       return withFilingStatus(Operator.EQUAL, filingStatus);
    }

    public TaxRecordRequest<T> withFilingStatusSoundingLike(String filingStatus){
       return withFilingStatus(Operator.SOUNDS_LIKE, filingStatus);
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
    public TaxRecordRequest minTaxAmount(){
        return minTaxAmountAs(prefix("minOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest minTaxAmountAs(String retName){
        super.min(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest maxTaxAmount(){
        return maxTaxAmountAs(prefix("maxOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest maxTaxAmountAs(String retName){
        super.max(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest sumTaxAmount(){
        return sumTaxAmountAs(prefix("sumOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest sumTaxAmountAs(String retName){
        super.sum(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest avgTaxAmount(){
        return avgTaxAmountAs(prefix("avgOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest avgTaxAmountAs(String retName){
        super.avg(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest standardDeviationTaxAmount(){
        return standardDeviationTaxAmountAs(prefix("standardDeviationOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest standardDeviationTaxAmountAs(String retName){
        super.standardDeviation(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest squareRootOfPopulationStandardDeviationTaxAmount(){
        return squareRootOfPopulationStandardDeviationTaxAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest squareRootOfPopulationStandardDeviationTaxAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest sampleVarianceTaxAmount(){
        return sampleVarianceTaxAmountAs(prefix("sampleVarianceOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest sampleVarianceTaxAmountAs(String retName){
        super.sampleVariance(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
        return this;
    }
    public TaxRecordRequest samplePopulationVarianceTaxAmount(){
        return samplePopulationVarianceTaxAmountAs(prefix("samplePopulationVarianceOf",TaxRecord.TAX_AMOUNT_PROPERTY));
    }

    public TaxRecordRequest samplePopulationVarianceTaxAmountAs(String retName){
        super.samplePopulationVariance(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
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

    public TaxRecordRequest<T> groupByTaxCode(){
       groupBy(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxCodeAs(String retName){
       groupBy(retName, TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.TAX_CODE_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxAmount(){
       groupBy(TaxRecord.TAX_AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxAmountAs(String retName){
       groupBy(retName, TaxRecord.TAX_AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.TAX_AMOUNT_PROPERTY, function);
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

    public TaxRecordRequest<T> groupByTaxPeriod(){
       groupBy(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxPeriodAs(String retName){
       groupBy(retName, TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByTaxPeriodWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.TAX_PERIOD_PROPERTY, function);
       return this;
    }

    public TaxRecordRequest<T> groupByFilingStatus(){
       groupBy(TaxRecord.FILING_STATUS_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByFilingStatusAs(String retName){
       groupBy(retName, TaxRecord.FILING_STATUS_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> groupByFilingStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, TaxRecord.FILING_STATUS_PROPERTY, function);
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
    public TaxRecordRequest<T> orderByTaxCodeAscending(){
       addOrderByAscending(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxCodeDescending(){
       addOrderByDescending(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByTaxCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.TAX_CODE_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByTaxAmountAscending(){
       addOrderByAscending(TaxRecord.TAX_AMOUNT_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxAmountDescending(){
       addOrderByDescending(TaxRecord.TAX_AMOUNT_PROPERTY);
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

    public TaxRecordRequest<T> orderByTaxPeriodAscending(){
       addOrderByAscending(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxPeriodDescending(){
       addOrderByDescending(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByTaxPeriodAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByTaxPeriodDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.TAX_PERIOD_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByFilingStatusAscending(){
       addOrderByAscending(TaxRecord.FILING_STATUS_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByFilingStatusDescending(){
       addOrderByDescending(TaxRecord.FILING_STATUS_PROPERTY);
       return this;
    }
    public TaxRecordRequest<T> orderByFilingStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TaxRecord.FILING_STATUS_PROPERTY);
       return this;
    }

    public TaxRecordRequest<T> orderByFilingStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TaxRecord.FILING_STATUS_PROPERTY);
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