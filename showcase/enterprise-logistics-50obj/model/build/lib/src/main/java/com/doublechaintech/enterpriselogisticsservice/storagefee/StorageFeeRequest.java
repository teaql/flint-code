package com.doublechaintech.enterpriselogisticsservice.storagefee;

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

public class StorageFeeRequest<T extends StorageFee> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public StorageFeeRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public StorageFeeRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public StorageFeeRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public StorageFeeRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public StorageFeeRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public StorageFeeRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public StorageFeeRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (StorageFeeRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public StorageFeeRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public StorageFeeRequest<T> matchingAnyOf(StorageFeeRequest storageFee){
        super.internalMatchAny(storageFee);
        return this;
    }

    public StorageFeeRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public StorageFeeRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public StorageFeeRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public StorageFeeRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectInvoiceIdOnly().selectFeeAmount().selectCurrency().selectPeriodStart().selectPeriodEnd().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public StorageFeeRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public StorageFeeRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectInvoice().selectFeeAmount().selectCurrency().selectPeriodStart().selectPeriodEnd().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public StorageFeeRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectInvoice().selectFeeAmount().selectCurrency().selectPeriodStart().selectPeriodEnd().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public StorageFeeRequest<T> selectId(){
       selectProperty(StorageFee.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectId(){
       unselectProperty(StorageFee.ID_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectInvoiceIdOnly(){
       selectProperty(StorageFee.INVOICE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> selectInvoice(){
        return selectInvoiceWith(Q.invoices().unlimited().selectSelf());
    }

    public StorageFeeRequest<T> selectInvoiceWith(InvoiceRequest invoice){
       selectProperty(StorageFee.INVOICE_PROPERTY);
       enhanceRelation(StorageFee.INVOICE_PROPERTY, invoice);
       return this;
    }

    public StorageFeeRequest<T> unselectInvoice(){
       unselectProperty(StorageFee.INVOICE_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectFeeAmount(){
       selectProperty(StorageFee.FEE_AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the feeAmount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  feeAmount) to fetch feeAmount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the feeAmount with customized aggrFunction, TEAQL uses ({aggrFunction}(feeAmount) AS feeAmount to fetch feeAmount property.
     * @param aggrFunction  aggrFunction
     */
    public StorageFeeRequest<T> selectFeeAmount(AggrFunction aggrFunction){
       selectProperty(StorageFee.FEE_AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public StorageFeeRequest<T> unselectFeeAmount(){
       unselectProperty(StorageFee.FEE_AMOUNT_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectCurrency(){
       selectProperty(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectCurrency(){
       unselectProperty(StorageFee.CURRENCY_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectPeriodStart(){
       selectProperty(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }

    /**
     * fill the periodStart with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  periodStart) to fetch periodStart property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectPeriodStart(){
       unselectProperty(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectPeriodEnd(){
       selectProperty(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }

    /**
     * fill the periodEnd with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  periodEnd) to fetch periodEnd property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectPeriodEnd(){
       unselectProperty(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectStatus(){
       selectProperty(StorageFee.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectStatus(){
       unselectProperty(StorageFee.STATUS_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectCreateTime(){
       selectProperty(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectCreateTime(){
       unselectProperty(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectUpdateTime(){
       selectProperty(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectUpdateTime(){
       unselectProperty(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectVersion(){
       selectProperty(StorageFee.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectVersion(){
       unselectProperty(StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.ID_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public StorageFeeRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public StorageFeeRequest<T> filterByInvoice(Invoice... invoice){
      if (invoice == null || invoice.length == 0) {
        throw new IllegalArgumentException("filterByInvoice parameter invoice cannot be empty");
      }
      return appendSearchCriteria(createInvoiceCriteria(Operator.EQUAL, (Object[])invoice));
    }

    public StorageFeeRequest<T> withInvoice(Operator operator, Object... values){
       return appendSearchCriteria(createInvoiceCriteria(operator, values));
    }

    public StorageFeeRequest<T> withInvoiceIsUnknown(){
       return withInvoice(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withInvoiceIsKnown(){
       return withInvoice(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createInvoiceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.INVOICE_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> filterByInvoice(Long invoice){
      if(invoice == null){
         return this;
      }
      return withInvoice(Operator.EQUAL, invoice);
    }
    public StorageFeeRequest<T> withInvoiceMatching(InvoiceRequest invoice){
       return appendSearchCriteria(new SubQuerySearchCriteria(StorageFee.INVOICE_PROPERTY, invoice, Invoice.ID_PROPERTY));
    }

    public StorageFeeRequest<T> filterByFeeAmount(BigDecimal... feeAmount){
      if (feeAmount == null || feeAmount.length == 0) {
        throw new IllegalArgumentException("filterByFeeAmount parameter feeAmount cannot be empty");
      }
      return appendSearchCriteria(createFeeAmountCriteria(Operator.EQUAL, (Object[])feeAmount));
    }

    public StorageFeeRequest<T> withFeeAmount(Operator operator, Object... values){
       return appendSearchCriteria(createFeeAmountCriteria(operator, values));
    }

    public StorageFeeRequest<T> withFeeAmountIsUnknown(){
       return withFeeAmount(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withFeeAmountIsKnown(){
       return withFeeAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createFeeAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.FEE_AMOUNT_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withFeeAmountGreaterThan(BigDecimal feeAmount){
       return withFeeAmount(Operator.GREATER_THAN, feeAmount);
    }

    public StorageFeeRequest<T> withFeeAmountGreaterThanOrEqualTo(BigDecimal feeAmount){
       return withFeeAmount(Operator.GREATER_THAN_OR_EQUAL, feeAmount);
    }

    public StorageFeeRequest<T> withFeeAmountLessThan(BigDecimal feeAmount){
       return withFeeAmount(Operator.LESS_THAN, feeAmount);
    }

    public StorageFeeRequest<T> withFeeAmountLessThanOrEqualTo(BigDecimal feeAmount){
       return withFeeAmount(Operator.LESS_THAN_OR_EQUAL, feeAmount);
    }

    public StorageFeeRequest<T> withFeeAmountBetween(BigDecimal startOfFeeAmount, BigDecimal endOfFeeAmount){
       return withFeeAmount(Operator.BETWEEN, startOfFeeAmount, endOfFeeAmount);
    }



    public StorageFeeRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public StorageFeeRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public StorageFeeRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.CURRENCY_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public StorageFeeRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public StorageFeeRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public StorageFeeRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public StorageFeeRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public StorageFeeRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public StorageFeeRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public StorageFeeRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public StorageFeeRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public StorageFeeRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public StorageFeeRequest<T> filterByPeriodStart(String... periodStart){
      if (periodStart == null || periodStart.length == 0) {
        throw new IllegalArgumentException("filterByPeriodStart parameter periodStart cannot be empty");
      }
      return appendSearchCriteria(createPeriodStartCriteria(Operator.EQUAL, (Object[])periodStart));
    }

    public StorageFeeRequest<T> withPeriodStart(Operator operator, Object... values){
       return appendSearchCriteria(createPeriodStartCriteria(operator, values));
    }

    public StorageFeeRequest<T> withPeriodStartIsUnknown(){
       return withPeriodStart(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withPeriodStartIsKnown(){
       return withPeriodStart(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPeriodStartCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.PERIOD_START_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withPeriodStartGreaterThan(String periodStart){
       return withPeriodStart(Operator.GREATER_THAN, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartGreaterThanOrEqualTo(String periodStart){
       return withPeriodStart(Operator.GREATER_THAN_OR_EQUAL, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartLessThan(String periodStart){
       return withPeriodStart(Operator.LESS_THAN, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartLessThanOrEqualTo(String periodStart){
       return withPeriodStart(Operator.LESS_THAN_OR_EQUAL, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartBetween(String startOfPeriodStart, String endOfPeriodStart){
       return withPeriodStart(Operator.BETWEEN, startOfPeriodStart, endOfPeriodStart);
    }
    public StorageFeeRequest<T> withPeriodStartStartingWith(String periodStart){
       return withPeriodStart(Operator.BEGIN_WITH, periodStart);
    }
    public StorageFeeRequest<T> withPeriodStartContaining(String periodStart){
       return withPeriodStart(Operator.CONTAIN, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartEndingWith(String periodStart){
       return withPeriodStart(Operator.END_WITH, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartIs(String periodStart){
       return withPeriodStart(Operator.EQUAL, periodStart);
    }

    public StorageFeeRequest<T> withPeriodStartSoundingLike(String periodStart){
       return withPeriodStart(Operator.SOUNDS_LIKE, periodStart);
    }



    public StorageFeeRequest<T> filterByPeriodEnd(String... periodEnd){
      if (periodEnd == null || periodEnd.length == 0) {
        throw new IllegalArgumentException("filterByPeriodEnd parameter periodEnd cannot be empty");
      }
      return appendSearchCriteria(createPeriodEndCriteria(Operator.EQUAL, (Object[])periodEnd));
    }

    public StorageFeeRequest<T> withPeriodEnd(Operator operator, Object... values){
       return appendSearchCriteria(createPeriodEndCriteria(operator, values));
    }

    public StorageFeeRequest<T> withPeriodEndIsUnknown(){
       return withPeriodEnd(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withPeriodEndIsKnown(){
       return withPeriodEnd(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPeriodEndCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.PERIOD_END_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withPeriodEndGreaterThan(String periodEnd){
       return withPeriodEnd(Operator.GREATER_THAN, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndGreaterThanOrEqualTo(String periodEnd){
       return withPeriodEnd(Operator.GREATER_THAN_OR_EQUAL, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndLessThan(String periodEnd){
       return withPeriodEnd(Operator.LESS_THAN, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndLessThanOrEqualTo(String periodEnd){
       return withPeriodEnd(Operator.LESS_THAN_OR_EQUAL, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndBetween(String startOfPeriodEnd, String endOfPeriodEnd){
       return withPeriodEnd(Operator.BETWEEN, startOfPeriodEnd, endOfPeriodEnd);
    }
    public StorageFeeRequest<T> withPeriodEndStartingWith(String periodEnd){
       return withPeriodEnd(Operator.BEGIN_WITH, periodEnd);
    }
    public StorageFeeRequest<T> withPeriodEndContaining(String periodEnd){
       return withPeriodEnd(Operator.CONTAIN, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndEndingWith(String periodEnd){
       return withPeriodEnd(Operator.END_WITH, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndIs(String periodEnd){
       return withPeriodEnd(Operator.EQUAL, periodEnd);
    }

    public StorageFeeRequest<T> withPeriodEndSoundingLike(String periodEnd){
       return withPeriodEnd(Operator.SOUNDS_LIKE, periodEnd);
    }



    public StorageFeeRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public StorageFeeRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public StorageFeeRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.STATUS_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public StorageFeeRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public StorageFeeRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public StorageFeeRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public StorageFeeRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public StorageFeeRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public StorageFeeRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public StorageFeeRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public StorageFeeRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public StorageFeeRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public StorageFeeRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public StorageFeeRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public StorageFeeRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.CREATE_TIME_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public StorageFeeRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public StorageFeeRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public StorageFeeRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public StorageFeeRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.UPDATE_TIME_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public StorageFeeRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public StorageFeeRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public StorageFeeRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public StorageFeeRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.VERSION_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public StorageFeeRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public StorageFeeRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public StorageFeeRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public StorageFeeRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public StorageFeeRequest<T> count(){
        super.count();
        return this;
    }
    public StorageFeeRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public StorageFeeRequest minFeeAmount(){
        return minFeeAmountAs(prefix("minOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest minFeeAmountAs(String retName){
        super.min(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest maxFeeAmount(){
        return maxFeeAmountAs(prefix("maxOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest maxFeeAmountAs(String retName){
        super.max(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest sumFeeAmount(){
        return sumFeeAmountAs(prefix("sumOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest sumFeeAmountAs(String retName){
        super.sum(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest avgFeeAmount(){
        return avgFeeAmountAs(prefix("avgOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest avgFeeAmountAs(String retName){
        super.avg(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest standardDeviationFeeAmount(){
        return standardDeviationFeeAmountAs(prefix("standardDeviationOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest standardDeviationFeeAmountAs(String retName){
        super.standardDeviation(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest squareRootOfPopulationStandardDeviationFeeAmount(){
        return squareRootOfPopulationStandardDeviationFeeAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest squareRootOfPopulationStandardDeviationFeeAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest sampleVarianceFeeAmount(){
        return sampleVarianceFeeAmountAs(prefix("sampleVarianceOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest sampleVarianceFeeAmountAs(String retName){
        super.sampleVariance(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest samplePopulationVarianceFeeAmount(){
        return samplePopulationVarianceFeeAmountAs(prefix("samplePopulationVarianceOf",StorageFee.FEE_AMOUNT_PROPERTY));
    }

    public StorageFeeRequest samplePopulationVarianceFeeAmountAs(String retName){
        super.samplePopulationVariance(retName, StorageFee.FEE_AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest<T> groupByInvoiceWithDetails(){
       return groupByInvoiceWithDetails(Q.invoices().unlimited());
    }

    public StorageFeeRequest<T> groupByInvoiceWithDetails(InvoiceRequest subRequest){
       aggregate(StorageFee.INVOICE_PROPERTY, subRequest);
       return this;
    }










    public StorageFeeRequest<T> groupById(){
       groupBy(StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByIdAs(String retName){
       groupBy(retName, StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.ID_PROPERTY, function);
       return this;
    }
    public StorageFeeRequest<T> groupByInvoiceWith(InvoiceRequest subRequest){
       groupBy(StorageFee.INVOICE_PROPERTY, subRequest);
       return this;
    }
    public StorageFeeRequest<T> groupByInvoice(){
       groupBy(StorageFee.INVOICE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByInvoiceAs(String retName){
       groupBy(retName, StorageFee.INVOICE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByInvoiceWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.INVOICE_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByFeeAmount(){
       groupBy(StorageFee.FEE_AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByFeeAmountAs(String retName){
       groupBy(retName, StorageFee.FEE_AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByFeeAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.FEE_AMOUNT_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByCurrency(){
       groupBy(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.CURRENCY_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodStart(){
       groupBy(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodStartAs(String retName){
       groupBy(retName, StorageFee.PERIOD_START_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodStartWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.PERIOD_START_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodEnd(){
       groupBy(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodEndAs(String retName){
       groupBy(retName, StorageFee.PERIOD_END_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodEndWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.PERIOD_END_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByStatus(){
       groupBy(StorageFee.STATUS_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByStatusAs(String retName){
       groupBy(retName, StorageFee.STATUS_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.STATUS_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByCreateTime(){
       groupBy(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByUpdateTime(){
       groupBy(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByVersion(){
       groupBy(StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByVersionAs(String retName){
       groupBy(retName, StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.VERSION_PROPERTY, function);
       return this;
    }



    public StorageFeeRequest<T> orderByIdAscending(){
       addOrderByAscending(StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByIdDescending(){
       addOrderByDescending(StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByInvoiceAscending(){
       addOrderByAscending(StorageFee.INVOICE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByInvoiceDescending(){
       addOrderByDescending(StorageFee.INVOICE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByFeeAmountAscending(){
       addOrderByAscending(StorageFee.FEE_AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByFeeAmountDescending(){
       addOrderByDescending(StorageFee.FEE_AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(StorageFee.CURRENCY_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageFee.CURRENCY_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByPeriodStartAscending(){
       addOrderByAscending(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByPeriodStartDescending(){
       addOrderByDescending(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByPeriodStartAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByPeriodStartDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageFee.PERIOD_START_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByPeriodEndAscending(){
       addOrderByAscending(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByPeriodEndDescending(){
       addOrderByDescending(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByPeriodEndAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByPeriodEndDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageFee.PERIOD_END_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByStatusAscending(){
       addOrderByAscending(StorageFee.STATUS_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByStatusDescending(){
       addOrderByDescending(StorageFee.STATUS_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageFee.STATUS_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageFee.STATUS_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByVersionAscending(){
       addOrderByAscending(StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByVersionDescending(){
       addOrderByDescending(StorageFee.VERSION_PROPERTY);
       return this;
    }


    public InvoiceRequest rollUpToInvoice(){
       InvoiceRequest invoice = Q.invoices().unlimited();
       this.withInvoiceMatching(invoice)
           .groupByInvoiceWith(invoice);
       return invoice;
    }










   public StorageFeeRequest<T> facetByInvoiceAs(String facetName, InvoiceRequest invoice){
       return facetByInvoiceAs(facetName, invoice, true);
   }

   public StorageFeeRequest<T> facetByInvoiceAs(String facetName, InvoiceRequest invoice, boolean includeAllFacets){
       addFacet(facetName, StorageFee.INVOICE_PROPERTY, invoice, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public StorageFeeRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public StorageFeeRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public StorageFeeRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public StorageFeeRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public StorageFeeRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}