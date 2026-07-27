package com.doublechaintech.enterpriselogisticsservice.invoice;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordRequest;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class InvoiceRequest<T extends Invoice> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public InvoiceRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public InvoiceRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public InvoiceRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public InvoiceRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public InvoiceRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public InvoiceRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public InvoiceRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (InvoiceRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public InvoiceRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public InvoiceRequest<T> matchingAnyOf(InvoiceRequest invoice){
        super.internalMatchAny(invoice);
        return this;
    }

    public InvoiceRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public InvoiceRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public InvoiceRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public InvoiceRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectStatus().selectIssueDate().selectDueDate().selectMovingOrderIdOnly().selectCustomer().selectVersion();
    }

    public InvoiceRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public InvoiceRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectStatus().selectIssueDate().selectDueDate().selectMovingOrder().selectCustomer().selectVersion();
    }

    public InvoiceRequest<T> selectChildren(){
        super.selectAny();
        selectPaymentRecordList().selectTaxRecordList();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectStatus().selectIssueDate().selectDueDate().selectMovingOrder().selectCustomer().selectVersion();
    }


    public InvoiceRequest<T> selectId(){
       selectProperty(Invoice.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectId(){
       unselectProperty(Invoice.ID_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectName(){
       selectProperty(Invoice.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectName(){
       unselectProperty(Invoice.NAME_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectCode(){
       selectProperty(Invoice.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectCode(){
       unselectProperty(Invoice.CODE_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectAmount(){
       selectProperty(Invoice.AMOUNT_PROPERTY);
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
    public InvoiceRequest<T> selectAmount(AggrFunction aggrFunction){
       selectProperty(Invoice.AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public InvoiceRequest<T> unselectAmount(){
       unselectProperty(Invoice.AMOUNT_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectCurrency(){
       selectProperty(Invoice.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectCurrency(){
       unselectProperty(Invoice.CURRENCY_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectStatus(){
       selectProperty(Invoice.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectStatus(){
       unselectProperty(Invoice.STATUS_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectIssueDate(){
       selectProperty(Invoice.ISSUE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the issueDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  issueDate) to fetch issueDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectIssueDate(){
       unselectProperty(Invoice.ISSUE_DATE_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectDueDate(){
       selectProperty(Invoice.DUE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the dueDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  dueDate) to fetch dueDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectDueDate(){
       unselectProperty(Invoice.DUE_DATE_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectMovingOrderIdOnly(){
       selectProperty(Invoice.MOVING_ORDER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public InvoiceRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(Invoice.MOVING_ORDER_PROPERTY);
       enhanceRelation(Invoice.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public InvoiceRequest<T> unselectMovingOrder(){
       unselectProperty(Invoice.MOVING_ORDER_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectCustomer(){
       selectProperty(Invoice.CUSTOMER_PROPERTY);
       return this;
    }

    /**
     * fill the customer with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  customer) to fetch customer property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectCustomer(){
       unselectProperty(Invoice.CUSTOMER_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectVersion(){
       selectProperty(Invoice.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InvoiceRequest<T> unselectVersion(){
       unselectProperty(Invoice.VERSION_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> selectPaymentRecordList(){
       return selectPaymentRecordListWith(Q.paymentRecords().selectSelf());
    }

    public InvoiceRequest<T> selectPaymentRecordListWith(PaymentRecordRequest paymentRecordList){
       enhanceRelation(Invoice.PAYMENT_RECORD_LIST_PROPERTY, paymentRecordList);
       return this;
    }
    public InvoiceRequest<T> selectTaxRecordList(){
       return selectTaxRecordListWith(Q.taxRecords().selectSelf());
    }

    public InvoiceRequest<T> selectTaxRecordListWith(TaxRecordRequest taxRecordList){
       enhanceRelation(Invoice.TAX_RECORD_LIST_PROPERTY, taxRecordList);
       return this;
    }

    public InvoiceRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.ID_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public InvoiceRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public InvoiceRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public InvoiceRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public InvoiceRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.NAME_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public InvoiceRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public InvoiceRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public InvoiceRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public InvoiceRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public InvoiceRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public InvoiceRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public InvoiceRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public InvoiceRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public InvoiceRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public InvoiceRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public InvoiceRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public InvoiceRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.CODE_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public InvoiceRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public InvoiceRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public InvoiceRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public InvoiceRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public InvoiceRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public InvoiceRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public InvoiceRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public InvoiceRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public InvoiceRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public InvoiceRequest<T> filterByAmount(BigDecimal... amount){
      if (amount == null || amount.length == 0) {
        throw new IllegalArgumentException("filterByAmount parameter amount cannot be empty");
      }
      return appendSearchCriteria(createAmountCriteria(Operator.EQUAL, (Object[])amount));
    }

    public InvoiceRequest<T> withAmount(Operator operator, Object... values){
       return appendSearchCriteria(createAmountCriteria(operator, values));
    }

    public InvoiceRequest<T> withAmountIsUnknown(){
       return withAmount(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withAmountIsKnown(){
       return withAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.AMOUNT_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withAmountGreaterThan(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN, amount);
    }

    public InvoiceRequest<T> withAmountGreaterThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN_OR_EQUAL, amount);
    }

    public InvoiceRequest<T> withAmountLessThan(BigDecimal amount){
       return withAmount(Operator.LESS_THAN, amount);
    }

    public InvoiceRequest<T> withAmountLessThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.LESS_THAN_OR_EQUAL, amount);
    }

    public InvoiceRequest<T> withAmountBetween(BigDecimal startOfAmount, BigDecimal endOfAmount){
       return withAmount(Operator.BETWEEN, startOfAmount, endOfAmount);
    }



    public InvoiceRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public InvoiceRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public InvoiceRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.CURRENCY_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public InvoiceRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public InvoiceRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public InvoiceRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public InvoiceRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public InvoiceRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public InvoiceRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public InvoiceRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public InvoiceRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public InvoiceRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public InvoiceRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public InvoiceRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public InvoiceRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.STATUS_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public InvoiceRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public InvoiceRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public InvoiceRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public InvoiceRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public InvoiceRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public InvoiceRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public InvoiceRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public InvoiceRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public InvoiceRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public InvoiceRequest<T> filterByIssueDate(LocalDate... issueDate){
      if (issueDate == null || issueDate.length == 0) {
        throw new IllegalArgumentException("filterByIssueDate parameter issueDate cannot be empty");
      }
      return appendSearchCriteria(createIssueDateCriteria(Operator.EQUAL, (Object[])issueDate));
    }

    public InvoiceRequest<T> withIssueDate(Operator operator, Object... values){
       return appendSearchCriteria(createIssueDateCriteria(operator, values));
    }

    public InvoiceRequest<T> withIssueDateIsUnknown(){
       return withIssueDate(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withIssueDateIsKnown(){
       return withIssueDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIssueDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.ISSUE_DATE_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withIssueDateGreaterThan(LocalDate issueDate){
       return withIssueDate(Operator.GREATER_THAN, issueDate);
    }

    public InvoiceRequest<T> withIssueDateGreaterThanOrEqualTo(LocalDate issueDate){
       return withIssueDate(Operator.GREATER_THAN_OR_EQUAL, issueDate);
    }

    public InvoiceRequest<T> withIssueDateLessThan(LocalDate issueDate){
       return withIssueDate(Operator.LESS_THAN, issueDate);
    }

    public InvoiceRequest<T> withIssueDateLessThanOrEqualTo(LocalDate issueDate){
       return withIssueDate(Operator.LESS_THAN_OR_EQUAL, issueDate);
    }

    public InvoiceRequest<T> withIssueDateBetween(LocalDate startOfIssueDate, LocalDate endOfIssueDate){
       return withIssueDate(Operator.BETWEEN, startOfIssueDate, endOfIssueDate);
    }
    public InvoiceRequest<T> withIssueDateBefore(LocalDate issueDate){
       return withIssueDate(Operator.LESS_THAN, issueDate);
    }

    public InvoiceRequest<T> withIssueDateBefore(Date issueDate){
       return withIssueDate(Operator.LESS_THAN, issueDate);
    }

    public InvoiceRequest<T> withIssueDateAfter(LocalDate issueDate){
       return withIssueDate(Operator.GREATER_THAN, issueDate);
    }

    public InvoiceRequest<T> withIssueDateAfter(Date issueDate){
       return withIssueDate(Operator.GREATER_THAN, issueDate);
    }

    public InvoiceRequest<T> withIssueDateBetween(Date startOfIssueDate, Date endOfIssueDate){
       return withIssueDate(Operator.BETWEEN, startOfIssueDate, endOfIssueDate);
    }




    public InvoiceRequest<T> filterByDueDate(LocalDate... dueDate){
      if (dueDate == null || dueDate.length == 0) {
        throw new IllegalArgumentException("filterByDueDate parameter dueDate cannot be empty");
      }
      return appendSearchCriteria(createDueDateCriteria(Operator.EQUAL, (Object[])dueDate));
    }

    public InvoiceRequest<T> withDueDate(Operator operator, Object... values){
       return appendSearchCriteria(createDueDateCriteria(operator, values));
    }

    public InvoiceRequest<T> withDueDateIsUnknown(){
       return withDueDate(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withDueDateIsKnown(){
       return withDueDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDueDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.DUE_DATE_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withDueDateGreaterThan(LocalDate dueDate){
       return withDueDate(Operator.GREATER_THAN, dueDate);
    }

    public InvoiceRequest<T> withDueDateGreaterThanOrEqualTo(LocalDate dueDate){
       return withDueDate(Operator.GREATER_THAN_OR_EQUAL, dueDate);
    }

    public InvoiceRequest<T> withDueDateLessThan(LocalDate dueDate){
       return withDueDate(Operator.LESS_THAN, dueDate);
    }

    public InvoiceRequest<T> withDueDateLessThanOrEqualTo(LocalDate dueDate){
       return withDueDate(Operator.LESS_THAN_OR_EQUAL, dueDate);
    }

    public InvoiceRequest<T> withDueDateBetween(LocalDate startOfDueDate, LocalDate endOfDueDate){
       return withDueDate(Operator.BETWEEN, startOfDueDate, endOfDueDate);
    }
    public InvoiceRequest<T> withDueDateBefore(LocalDate dueDate){
       return withDueDate(Operator.LESS_THAN, dueDate);
    }

    public InvoiceRequest<T> withDueDateBefore(Date dueDate){
       return withDueDate(Operator.LESS_THAN, dueDate);
    }

    public InvoiceRequest<T> withDueDateAfter(LocalDate dueDate){
       return withDueDate(Operator.GREATER_THAN, dueDate);
    }

    public InvoiceRequest<T> withDueDateAfter(Date dueDate){
       return withDueDate(Operator.GREATER_THAN, dueDate);
    }

    public InvoiceRequest<T> withDueDateBetween(Date startOfDueDate, Date endOfDueDate){
       return withDueDate(Operator.BETWEEN, startOfDueDate, endOfDueDate);
    }




    public InvoiceRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public InvoiceRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public InvoiceRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.MOVING_ORDER_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public InvoiceRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(Invoice.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public InvoiceRequest<T> filterByCustomer(String... customer){
      if (customer == null || customer.length == 0) {
        throw new IllegalArgumentException("filterByCustomer parameter customer cannot be empty");
      }
      return appendSearchCriteria(createCustomerCriteria(Operator.EQUAL, (Object[])customer));
    }

    public InvoiceRequest<T> withCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCustomerCriteria(operator, values));
    }

    public InvoiceRequest<T> withCustomerIsUnknown(){
       return withCustomer(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withCustomerIsKnown(){
       return withCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.CUSTOMER_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withCustomerGreaterThan(String customer){
       return withCustomer(Operator.GREATER_THAN, customer);
    }

    public InvoiceRequest<T> withCustomerGreaterThanOrEqualTo(String customer){
       return withCustomer(Operator.GREATER_THAN_OR_EQUAL, customer);
    }

    public InvoiceRequest<T> withCustomerLessThan(String customer){
       return withCustomer(Operator.LESS_THAN, customer);
    }

    public InvoiceRequest<T> withCustomerLessThanOrEqualTo(String customer){
       return withCustomer(Operator.LESS_THAN_OR_EQUAL, customer);
    }

    public InvoiceRequest<T> withCustomerBetween(String startOfCustomer, String endOfCustomer){
       return withCustomer(Operator.BETWEEN, startOfCustomer, endOfCustomer);
    }
    public InvoiceRequest<T> withCustomerStartingWith(String customer){
       return withCustomer(Operator.BEGIN_WITH, customer);
    }
    public InvoiceRequest<T> withCustomerContaining(String customer){
       return withCustomer(Operator.CONTAIN, customer);
    }

    public InvoiceRequest<T> withCustomerEndingWith(String customer){
       return withCustomer(Operator.END_WITH, customer);
    }

    public InvoiceRequest<T> withCustomerIs(String customer){
       return withCustomer(Operator.EQUAL, customer);
    }

    public InvoiceRequest<T> withCustomerSoundingLike(String customer){
       return withCustomer(Operator.SOUNDS_LIKE, customer);
    }



    public InvoiceRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public InvoiceRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public InvoiceRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public InvoiceRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Invoice.VERSION_PROPERTY, operator, values);
    }

    public InvoiceRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public InvoiceRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public InvoiceRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public InvoiceRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public InvoiceRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public InvoiceRequest<T> withPaymentRecordListMatching(PaymentRecordRequest paymentRecordRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Invoice.ID_PROPERTY, paymentRecordRequest, PaymentRecord.INVOICE_PROPERTY));
    }

    public InvoiceRequest<T> withoutPaymentRecordListMatching(PaymentRecordRequest paymentRecordRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Invoice.ID_PROPERTY, paymentRecordRequest, PaymentRecord.INVOICE_PROPERTY)));
    }

    public InvoiceRequest<T> havePaymentRecords(){
        return withPaymentRecordListMatching(Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> haveNoPaymentRecords(){
        return withoutPaymentRecordListMatching(Q.paymentRecords().unlimited());
    }
    public InvoiceRequest<T> withTaxRecordListMatching(TaxRecordRequest taxRecordRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Invoice.ID_PROPERTY, taxRecordRequest, TaxRecord.INVOICE_PROPERTY));
    }

    public InvoiceRequest<T> withoutTaxRecordListMatching(TaxRecordRequest taxRecordRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Invoice.ID_PROPERTY, taxRecordRequest, TaxRecord.INVOICE_PROPERTY)));
    }

    public InvoiceRequest<T> haveTaxRecords(){
        return withTaxRecordListMatching(Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> haveNoTaxRecords(){
        return withoutTaxRecordListMatching(Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> count(){
        super.count();
        return this;
    }
    public InvoiceRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public InvoiceRequest minAmount(){
        return minAmountAs(prefix("minOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest minAmountAs(String retName){
        super.min(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest maxAmount(){
        return maxAmountAs(prefix("maxOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest maxAmountAs(String retName){
        super.max(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest sumAmount(){
        return sumAmountAs(prefix("sumOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest sumAmountAs(String retName){
        super.sum(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest avgAmount(){
        return avgAmountAs(prefix("avgOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest avgAmountAs(String retName){
        super.avg(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest standardDeviationAmount(){
        return standardDeviationAmountAs(prefix("standardDeviationOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest standardDeviationAmountAs(String retName){
        super.standardDeviation(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest squareRootOfPopulationStandardDeviationAmount(){
        return squareRootOfPopulationStandardDeviationAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest squareRootOfPopulationStandardDeviationAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest sampleVarianceAmount(){
        return sampleVarianceAmountAs(prefix("sampleVarianceOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest sampleVarianceAmountAs(String retName){
        super.sampleVariance(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest samplePopulationVarianceAmount(){
        return samplePopulationVarianceAmountAs(prefix("samplePopulationVarianceOf",Invoice.AMOUNT_PROPERTY));
    }

    public InvoiceRequest samplePopulationVarianceAmountAs(String retName){
        super.samplePopulationVariance(retName, Invoice.AMOUNT_PROPERTY);
        return this;
    }
    public InvoiceRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public InvoiceRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(Invoice.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }



    public InvoiceRequest<T> groupByPaymentRecordsWithDetails(PaymentRecordRequest subRequest){
       aggregate(Invoice.PAYMENT_RECORD_LIST_PROPERTY, subRequest);
       return this;
    }
    public InvoiceRequest<T> groupByTaxRecordsWithDetails(TaxRecordRequest subRequest){
       aggregate(Invoice.TAX_RECORD_LIST_PROPERTY, subRequest);
       return this;
    }

    public InvoiceRequest<T> groupById(){
       groupBy(Invoice.ID_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByIdAs(String retName){
       groupBy(retName, Invoice.ID_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.ID_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByName(){
       groupBy(Invoice.NAME_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByNameAs(String retName){
       groupBy(retName, Invoice.NAME_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.NAME_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByCode(){
       groupBy(Invoice.CODE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByCodeAs(String retName){
       groupBy(retName, Invoice.CODE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.CODE_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByAmount(){
       groupBy(Invoice.AMOUNT_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByAmountAs(String retName){
       groupBy(retName, Invoice.AMOUNT_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.AMOUNT_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByCurrency(){
       groupBy(Invoice.CURRENCY_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, Invoice.CURRENCY_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.CURRENCY_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByStatus(){
       groupBy(Invoice.STATUS_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByStatusAs(String retName){
       groupBy(retName, Invoice.STATUS_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.STATUS_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByIssueDate(){
       groupBy(Invoice.ISSUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByIssueDateAs(String retName){
       groupBy(retName, Invoice.ISSUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByIssueDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.ISSUE_DATE_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByDueDate(){
       groupBy(Invoice.DUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByDueDateAs(String retName){
       groupBy(retName, Invoice.DUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByDueDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.DUE_DATE_PROPERTY, function);
       return this;
    }
    public InvoiceRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(Invoice.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public InvoiceRequest<T> groupByMovingOrder(){
       groupBy(Invoice.MOVING_ORDER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, Invoice.MOVING_ORDER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.MOVING_ORDER_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByCustomer(){
       groupBy(Invoice.CUSTOMER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByCustomerAs(String retName){
       groupBy(retName, Invoice.CUSTOMER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.CUSTOMER_PROPERTY, function);
       return this;
    }

    public InvoiceRequest<T> groupByVersion(){
       groupBy(Invoice.VERSION_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Invoice.VERSION_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Invoice.VERSION_PROPERTY, function);
       return this;
    }



    public InvoiceRequest<T> orderByIdAscending(){
       addOrderByAscending(Invoice.ID_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByIdDescending(){
       addOrderByDescending(Invoice.ID_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByNameAscending(){
       addOrderByAscending(Invoice.NAME_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByNameDescending(){
       addOrderByDescending(Invoice.NAME_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Invoice.NAME_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Invoice.NAME_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByCodeAscending(){
       addOrderByAscending(Invoice.CODE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCodeDescending(){
       addOrderByDescending(Invoice.CODE_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Invoice.CODE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Invoice.CODE_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByAmountAscending(){
       addOrderByAscending(Invoice.AMOUNT_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByAmountDescending(){
       addOrderByDescending(Invoice.AMOUNT_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(Invoice.CURRENCY_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(Invoice.CURRENCY_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Invoice.CURRENCY_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Invoice.CURRENCY_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByStatusAscending(){
       addOrderByAscending(Invoice.STATUS_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByStatusDescending(){
       addOrderByDescending(Invoice.STATUS_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Invoice.STATUS_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Invoice.STATUS_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByIssueDateAscending(){
       addOrderByAscending(Invoice.ISSUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByIssueDateDescending(){
       addOrderByDescending(Invoice.ISSUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByDueDateAscending(){
       addOrderByAscending(Invoice.DUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByDueDateDescending(){
       addOrderByDescending(Invoice.DUE_DATE_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(Invoice.MOVING_ORDER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(Invoice.MOVING_ORDER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCustomerAscending(){
       addOrderByAscending(Invoice.CUSTOMER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCustomerDescending(){
       addOrderByDescending(Invoice.CUSTOMER_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByCustomerAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Invoice.CUSTOMER_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByCustomerDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Invoice.CUSTOMER_PROPERTY);
       return this;
    }
    public InvoiceRequest<T> orderByVersionAscending(){
       addOrderByAscending(Invoice.VERSION_PROPERTY);
       return this;
    }

    public InvoiceRequest<T> orderByVersionDescending(){
       addOrderByDescending(Invoice.VERSION_PROPERTY);
       return this;
    }


    public InvoiceRequest<T> statsFromPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
       return statsFromPaymentRecordsAs(name, subRequest, false);
    }

    public InvoiceRequest<T> statsFromPaymentRecordsAs(String name, PaymentRecordRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(PaymentRecord.INVOICE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public InvoiceRequest<T> statsFromPaymentRecords(PaymentRecordRequest subRequest){
       return statsFromPaymentRecordsAs(REFINEMENTS, subRequest);
    }
    public InvoiceRequest<T> statsFromTaxRecordsAs(String name, TaxRecordRequest subRequest){
       return statsFromTaxRecordsAs(name, subRequest, false);
    }

    public InvoiceRequest<T> statsFromTaxRecordsAs(String name, TaxRecordRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(TaxRecord.INVOICE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public InvoiceRequest<T> statsFromTaxRecords(TaxRecordRequest subRequest){
       return statsFromTaxRecordsAs(REFINEMENTS, subRequest);
    }
    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }



    public InvoiceRequest<T> countPaymentRecords(){
        return countPaymentRecordsAs("Count");
    }

    public InvoiceRequest<T> countPaymentRecordsAs(String name){
        return countPaymentRecordsWith(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> countPaymentRecordsWith(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.count(), true);
    }
    public InvoiceRequest<T> countTaxRecords(){
        return countTaxRecordsAs("Count");
    }

    public InvoiceRequest<T> countTaxRecordsAs(String name){
        return countTaxRecordsWith(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> countTaxRecordsWith(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.count(), true);
    }
    public InvoiceRequest<T> minAmountOfPaymentRecords(){
        return minAmountOfPaymentRecordsAs("minAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> minAmountOfPaymentRecordsAs(String name){
        return minAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> minAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.minAmount(), true);
    }
    public InvoiceRequest<T> maxAmountOfPaymentRecords(){
        return maxAmountOfPaymentRecordsAs("maxAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> maxAmountOfPaymentRecordsAs(String name){
        return maxAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> maxAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.maxAmount(), true);
    }
    public InvoiceRequest<T> sumAmountOfPaymentRecords(){
        return sumAmountOfPaymentRecordsAs("sumAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> sumAmountOfPaymentRecordsAs(String name){
        return sumAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> sumAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.sumAmount(), true);
    }
    public InvoiceRequest<T> avgAmountOfPaymentRecords(){
        return avgAmountOfPaymentRecordsAs("avgAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> avgAmountOfPaymentRecordsAs(String name){
        return avgAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> avgAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.avgAmount(), true);
    }
    public InvoiceRequest<T> standardDeviationAmountOfPaymentRecords(){
        return standardDeviationAmountOfPaymentRecordsAs("stdDevAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> standardDeviationAmountOfPaymentRecordsAs(String name){
        return standardDeviationAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> standardDeviationAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.standardDeviationAmount(), true);
    }
    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationAmountOfPaymentRecords(){
        return squareRootOfPopulationStandardDeviationAmountOfPaymentRecordsAs("stdDevPopAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationAmountOfPaymentRecordsAs(String name){
        return squareRootOfPopulationStandardDeviationAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.squareRootOfPopulationStandardDeviationAmount(), true);
    }
    public InvoiceRequest<T> sampleVarianceAmountOfPaymentRecords(){
        return sampleVarianceAmountOfPaymentRecordsAs("varSampAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> sampleVarianceAmountOfPaymentRecordsAs(String name){
        return sampleVarianceAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> sampleVarianceAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.sampleVarianceAmount(), true);
    }
    public InvoiceRequest<T> samplePopulationVarianceAmountOfPaymentRecords(){
        return samplePopulationVarianceAmountOfPaymentRecordsAs("varPopAmountOfPaymentRecords");
    }

    public InvoiceRequest<T> samplePopulationVarianceAmountOfPaymentRecordsAs(String name){
        return samplePopulationVarianceAmountOfPaymentRecordsAs(name, Q.paymentRecords().unlimited());
    }

    public InvoiceRequest<T> samplePopulationVarianceAmountOfPaymentRecordsAs(String name, PaymentRecordRequest subRequest){
        return statsFromPaymentRecordsAs(name, subRequest.samplePopulationVarianceAmount(), true);
    }
    public InvoiceRequest<T> minTaxAmountOfTaxRecords(){
        return minTaxAmountOfTaxRecordsAs("minTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> minTaxAmountOfTaxRecordsAs(String name){
        return minTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> minTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.minTaxAmount(), true);
    }
    public InvoiceRequest<T> maxTaxAmountOfTaxRecords(){
        return maxTaxAmountOfTaxRecordsAs("maxTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> maxTaxAmountOfTaxRecordsAs(String name){
        return maxTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> maxTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.maxTaxAmount(), true);
    }
    public InvoiceRequest<T> sumTaxAmountOfTaxRecords(){
        return sumTaxAmountOfTaxRecordsAs("sumTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> sumTaxAmountOfTaxRecordsAs(String name){
        return sumTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> sumTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.sumTaxAmount(), true);
    }
    public InvoiceRequest<T> avgTaxAmountOfTaxRecords(){
        return avgTaxAmountOfTaxRecordsAs("avgTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> avgTaxAmountOfTaxRecordsAs(String name){
        return avgTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> avgTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.avgTaxAmount(), true);
    }
    public InvoiceRequest<T> standardDeviationTaxAmountOfTaxRecords(){
        return standardDeviationTaxAmountOfTaxRecordsAs("stdDevTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> standardDeviationTaxAmountOfTaxRecordsAs(String name){
        return standardDeviationTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> standardDeviationTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.standardDeviationTaxAmount(), true);
    }
    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationTaxAmountOfTaxRecords(){
        return squareRootOfPopulationStandardDeviationTaxAmountOfTaxRecordsAs("stdDevPopTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationTaxAmountOfTaxRecordsAs(String name){
        return squareRootOfPopulationStandardDeviationTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.squareRootOfPopulationStandardDeviationTaxAmount(), true);
    }
    public InvoiceRequest<T> sampleVarianceTaxAmountOfTaxRecords(){
        return sampleVarianceTaxAmountOfTaxRecordsAs("varSampTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> sampleVarianceTaxAmountOfTaxRecordsAs(String name){
        return sampleVarianceTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> sampleVarianceTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.sampleVarianceTaxAmount(), true);
    }
    public InvoiceRequest<T> samplePopulationVarianceTaxAmountOfTaxRecords(){
        return samplePopulationVarianceTaxAmountOfTaxRecordsAs("varPopTaxAmountOfTaxRecords");
    }

    public InvoiceRequest<T> samplePopulationVarianceTaxAmountOfTaxRecordsAs(String name){
        return samplePopulationVarianceTaxAmountOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> samplePopulationVarianceTaxAmountOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.samplePopulationVarianceTaxAmount(), true);
    }
    public InvoiceRequest<T> minTaxRateOfTaxRecords(){
        return minTaxRateOfTaxRecordsAs("minTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> minTaxRateOfTaxRecordsAs(String name){
        return minTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> minTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.minTaxRate(), true);
    }
    public InvoiceRequest<T> maxTaxRateOfTaxRecords(){
        return maxTaxRateOfTaxRecordsAs("maxTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> maxTaxRateOfTaxRecordsAs(String name){
        return maxTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> maxTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.maxTaxRate(), true);
    }
    public InvoiceRequest<T> sumTaxRateOfTaxRecords(){
        return sumTaxRateOfTaxRecordsAs("sumTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> sumTaxRateOfTaxRecordsAs(String name){
        return sumTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> sumTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.sumTaxRate(), true);
    }
    public InvoiceRequest<T> avgTaxRateOfTaxRecords(){
        return avgTaxRateOfTaxRecordsAs("avgTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> avgTaxRateOfTaxRecordsAs(String name){
        return avgTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> avgTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.avgTaxRate(), true);
    }
    public InvoiceRequest<T> standardDeviationTaxRateOfTaxRecords(){
        return standardDeviationTaxRateOfTaxRecordsAs("stdDevTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> standardDeviationTaxRateOfTaxRecordsAs(String name){
        return standardDeviationTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> standardDeviationTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.standardDeviationTaxRate(), true);
    }
    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationTaxRateOfTaxRecords(){
        return squareRootOfPopulationStandardDeviationTaxRateOfTaxRecordsAs("stdDevPopTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationTaxRateOfTaxRecordsAs(String name){
        return squareRootOfPopulationStandardDeviationTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> squareRootOfPopulationStandardDeviationTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.squareRootOfPopulationStandardDeviationTaxRate(), true);
    }
    public InvoiceRequest<T> sampleVarianceTaxRateOfTaxRecords(){
        return sampleVarianceTaxRateOfTaxRecordsAs("varSampTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> sampleVarianceTaxRateOfTaxRecordsAs(String name){
        return sampleVarianceTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> sampleVarianceTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.sampleVarianceTaxRate(), true);
    }
    public InvoiceRequest<T> samplePopulationVarianceTaxRateOfTaxRecords(){
        return samplePopulationVarianceTaxRateOfTaxRecordsAs("varPopTaxRateOfTaxRecords");
    }

    public InvoiceRequest<T> samplePopulationVarianceTaxRateOfTaxRecordsAs(String name){
        return samplePopulationVarianceTaxRateOfTaxRecordsAs(name, Q.taxRecords().unlimited());
    }

    public InvoiceRequest<T> samplePopulationVarianceTaxRateOfTaxRecordsAs(String name, TaxRecordRequest subRequest){
        return statsFromTaxRecordsAs(name, subRequest.samplePopulationVarianceTaxRate(), true);
    }

   public InvoiceRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public InvoiceRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, Invoice.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public InvoiceRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public InvoiceRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public InvoiceRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public InvoiceRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public InvoiceRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}