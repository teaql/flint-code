package com.doublechaintech.movingcompanyservice.payment;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class PaymentRequest<T extends Payment> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PaymentRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PaymentRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PaymentRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PaymentRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PaymentRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PaymentRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PaymentRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PaymentRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PaymentRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PaymentRequest<T> matchingAnyOf(PaymentRequest payment){
        super.internalMatchAny(payment);
        return this;
    }

    public PaymentRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PaymentRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PaymentRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PaymentRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectAmount().selectPaymentMethod().selectTransactionRef().selectPaymentDate().selectStatus().selectInvoice().selectCustomer().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public PaymentRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PaymentRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectAmount().selectPaymentMethod().selectTransactionRef().selectPaymentDate().selectStatus().selectInvoice().selectCustomer().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public PaymentRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectAmount().selectPaymentMethod().selectTransactionRef().selectPaymentDate().selectStatus().selectInvoice().selectCustomer().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public PaymentRequest<T> selectId(){
       selectProperty(Payment.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectId(){
       unselectProperty(Payment.ID_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectAmount(){
       selectProperty(Payment.AMOUNT_PROPERTY);
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
    public PaymentRequest<T> selectAmount(AggrFunction aggrFunction){
       selectProperty(Payment.AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public PaymentRequest<T> unselectAmount(){
       unselectProperty(Payment.AMOUNT_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectPaymentMethod(){
       selectProperty(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }

    /**
     * fill the paymentMethod with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  paymentMethod) to fetch paymentMethod property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectPaymentMethod(){
       unselectProperty(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectTransactionRef(){
       selectProperty(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }

    /**
     * fill the transactionRef with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  transactionRef) to fetch transactionRef property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectTransactionRef(){
       unselectProperty(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectPaymentDate(){
       selectProperty(Payment.PAYMENT_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the paymentDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  paymentDate) to fetch paymentDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectPaymentDate(){
       unselectProperty(Payment.PAYMENT_DATE_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectStatus(){
       selectProperty(Payment.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectStatus(){
       unselectProperty(Payment.STATUS_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectInvoice(){
       selectProperty(Payment.INVOICE_PROPERTY);
       return this;
    }

    /**
     * fill the invoice with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  invoice) to fetch invoice property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectInvoice(){
       unselectProperty(Payment.INVOICE_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectCustomer(){
       selectProperty(Payment.CUSTOMER_PROPERTY);
       return this;
    }

    /**
     * fill the customer with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  customer) to fetch customer property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectCustomer(){
       unselectProperty(Payment.CUSTOMER_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectCreateTime(){
       selectProperty(Payment.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectCreateTime(){
       unselectProperty(Payment.CREATE_TIME_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectUpdateTime(){
       selectProperty(Payment.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectUpdateTime(){
       unselectProperty(Payment.UPDATE_TIME_PROPERTY);
       return this;
    }
    public PaymentRequest<T> selectVersion(){
       selectProperty(Payment.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PaymentRequest<T> unselectVersion(){
       unselectProperty(Payment.VERSION_PROPERTY);
       return this;
    }

    public PaymentRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.ID_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PaymentRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PaymentRequest<T> filterByAmount(BigDecimal... amount){
      if (amount == null || amount.length == 0) {
        throw new IllegalArgumentException("filterByAmount parameter amount cannot be empty");
      }
      return appendSearchCriteria(createAmountCriteria(Operator.EQUAL, (Object[])amount));
    }

    public PaymentRequest<T> withAmount(Operator operator, Object... values){
       return appendSearchCriteria(createAmountCriteria(operator, values));
    }

    public PaymentRequest<T> withAmountIsUnknown(){
       return withAmount(Operator.IS_NULL);
    }

    public PaymentRequest<T> withAmountIsKnown(){
       return withAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.AMOUNT_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withAmountGreaterThan(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN, amount);
    }

    public PaymentRequest<T> withAmountGreaterThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN_OR_EQUAL, amount);
    }

    public PaymentRequest<T> withAmountLessThan(BigDecimal amount){
       return withAmount(Operator.LESS_THAN, amount);
    }

    public PaymentRequest<T> withAmountLessThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.LESS_THAN_OR_EQUAL, amount);
    }

    public PaymentRequest<T> withAmountBetween(BigDecimal startOfAmount, BigDecimal endOfAmount){
       return withAmount(Operator.BETWEEN, startOfAmount, endOfAmount);
    }



    public PaymentRequest<T> filterByPaymentMethod(String... paymentMethod){
      if (paymentMethod == null || paymentMethod.length == 0) {
        throw new IllegalArgumentException("filterByPaymentMethod parameter paymentMethod cannot be empty");
      }
      return appendSearchCriteria(createPaymentMethodCriteria(Operator.EQUAL, (Object[])paymentMethod));
    }

    public PaymentRequest<T> withPaymentMethod(Operator operator, Object... values){
       return appendSearchCriteria(createPaymentMethodCriteria(operator, values));
    }

    public PaymentRequest<T> withPaymentMethodIsUnknown(){
       return withPaymentMethod(Operator.IS_NULL);
    }

    public PaymentRequest<T> withPaymentMethodIsKnown(){
       return withPaymentMethod(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPaymentMethodCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.PAYMENT_METHOD_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withPaymentMethodGreaterThan(String paymentMethod){
       return withPaymentMethod(Operator.GREATER_THAN, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodGreaterThanOrEqualTo(String paymentMethod){
       return withPaymentMethod(Operator.GREATER_THAN_OR_EQUAL, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodLessThan(String paymentMethod){
       return withPaymentMethod(Operator.LESS_THAN, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodLessThanOrEqualTo(String paymentMethod){
       return withPaymentMethod(Operator.LESS_THAN_OR_EQUAL, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodBetween(String startOfPaymentMethod, String endOfPaymentMethod){
       return withPaymentMethod(Operator.BETWEEN, startOfPaymentMethod, endOfPaymentMethod);
    }
    public PaymentRequest<T> withPaymentMethodStartingWith(String paymentMethod){
       return withPaymentMethod(Operator.BEGIN_WITH, paymentMethod);
    }
    public PaymentRequest<T> withPaymentMethodContaining(String paymentMethod){
       return withPaymentMethod(Operator.CONTAIN, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodEndingWith(String paymentMethod){
       return withPaymentMethod(Operator.END_WITH, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodIs(String paymentMethod){
       return withPaymentMethod(Operator.EQUAL, paymentMethod);
    }

    public PaymentRequest<T> withPaymentMethodSoundingLike(String paymentMethod){
       return withPaymentMethod(Operator.SOUNDS_LIKE, paymentMethod);
    }



    public PaymentRequest<T> filterByTransactionRef(String... transactionRef){
      if (transactionRef == null || transactionRef.length == 0) {
        throw new IllegalArgumentException("filterByTransactionRef parameter transactionRef cannot be empty");
      }
      return appendSearchCriteria(createTransactionRefCriteria(Operator.EQUAL, (Object[])transactionRef));
    }

    public PaymentRequest<T> withTransactionRef(Operator operator, Object... values){
       return appendSearchCriteria(createTransactionRefCriteria(operator, values));
    }

    public PaymentRequest<T> withTransactionRefIsUnknown(){
       return withTransactionRef(Operator.IS_NULL);
    }

    public PaymentRequest<T> withTransactionRefIsKnown(){
       return withTransactionRef(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTransactionRefCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.TRANSACTION_REF_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withTransactionRefGreaterThan(String transactionRef){
       return withTransactionRef(Operator.GREATER_THAN, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefGreaterThanOrEqualTo(String transactionRef){
       return withTransactionRef(Operator.GREATER_THAN_OR_EQUAL, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefLessThan(String transactionRef){
       return withTransactionRef(Operator.LESS_THAN, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefLessThanOrEqualTo(String transactionRef){
       return withTransactionRef(Operator.LESS_THAN_OR_EQUAL, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefBetween(String startOfTransactionRef, String endOfTransactionRef){
       return withTransactionRef(Operator.BETWEEN, startOfTransactionRef, endOfTransactionRef);
    }
    public PaymentRequest<T> withTransactionRefStartingWith(String transactionRef){
       return withTransactionRef(Operator.BEGIN_WITH, transactionRef);
    }
    public PaymentRequest<T> withTransactionRefContaining(String transactionRef){
       return withTransactionRef(Operator.CONTAIN, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefEndingWith(String transactionRef){
       return withTransactionRef(Operator.END_WITH, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefIs(String transactionRef){
       return withTransactionRef(Operator.EQUAL, transactionRef);
    }

    public PaymentRequest<T> withTransactionRefSoundingLike(String transactionRef){
       return withTransactionRef(Operator.SOUNDS_LIKE, transactionRef);
    }



    public PaymentRequest<T> filterByPaymentDate(LocalDate... paymentDate){
      if (paymentDate == null || paymentDate.length == 0) {
        throw new IllegalArgumentException("filterByPaymentDate parameter paymentDate cannot be empty");
      }
      return appendSearchCriteria(createPaymentDateCriteria(Operator.EQUAL, (Object[])paymentDate));
    }

    public PaymentRequest<T> withPaymentDate(Operator operator, Object... values){
       return appendSearchCriteria(createPaymentDateCriteria(operator, values));
    }

    public PaymentRequest<T> withPaymentDateIsUnknown(){
       return withPaymentDate(Operator.IS_NULL);
    }

    public PaymentRequest<T> withPaymentDateIsKnown(){
       return withPaymentDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPaymentDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.PAYMENT_DATE_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withPaymentDateGreaterThan(LocalDate paymentDate){
       return withPaymentDate(Operator.GREATER_THAN, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateGreaterThanOrEqualTo(LocalDate paymentDate){
       return withPaymentDate(Operator.GREATER_THAN_OR_EQUAL, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateLessThan(LocalDate paymentDate){
       return withPaymentDate(Operator.LESS_THAN, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateLessThanOrEqualTo(LocalDate paymentDate){
       return withPaymentDate(Operator.LESS_THAN_OR_EQUAL, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateBetween(LocalDate startOfPaymentDate, LocalDate endOfPaymentDate){
       return withPaymentDate(Operator.BETWEEN, startOfPaymentDate, endOfPaymentDate);
    }
    public PaymentRequest<T> withPaymentDateBefore(LocalDate paymentDate){
       return withPaymentDate(Operator.LESS_THAN, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateBefore(Date paymentDate){
       return withPaymentDate(Operator.LESS_THAN, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateAfter(LocalDate paymentDate){
       return withPaymentDate(Operator.GREATER_THAN, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateAfter(Date paymentDate){
       return withPaymentDate(Operator.GREATER_THAN, paymentDate);
    }

    public PaymentRequest<T> withPaymentDateBetween(Date startOfPaymentDate, Date endOfPaymentDate){
       return withPaymentDate(Operator.BETWEEN, startOfPaymentDate, endOfPaymentDate);
    }




    public PaymentRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public PaymentRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public PaymentRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public PaymentRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.STATUS_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public PaymentRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public PaymentRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public PaymentRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public PaymentRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public PaymentRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public PaymentRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public PaymentRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public PaymentRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public PaymentRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public PaymentRequest<T> filterByInvoice(String... invoice){
      if (invoice == null || invoice.length == 0) {
        throw new IllegalArgumentException("filterByInvoice parameter invoice cannot be empty");
      }
      return appendSearchCriteria(createInvoiceCriteria(Operator.EQUAL, (Object[])invoice));
    }

    public PaymentRequest<T> withInvoice(Operator operator, Object... values){
       return appendSearchCriteria(createInvoiceCriteria(operator, values));
    }

    public PaymentRequest<T> withInvoiceIsUnknown(){
       return withInvoice(Operator.IS_NULL);
    }

    public PaymentRequest<T> withInvoiceIsKnown(){
       return withInvoice(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createInvoiceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.INVOICE_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withInvoiceGreaterThan(String invoice){
       return withInvoice(Operator.GREATER_THAN, invoice);
    }

    public PaymentRequest<T> withInvoiceGreaterThanOrEqualTo(String invoice){
       return withInvoice(Operator.GREATER_THAN_OR_EQUAL, invoice);
    }

    public PaymentRequest<T> withInvoiceLessThan(String invoice){
       return withInvoice(Operator.LESS_THAN, invoice);
    }

    public PaymentRequest<T> withInvoiceLessThanOrEqualTo(String invoice){
       return withInvoice(Operator.LESS_THAN_OR_EQUAL, invoice);
    }

    public PaymentRequest<T> withInvoiceBetween(String startOfInvoice, String endOfInvoice){
       return withInvoice(Operator.BETWEEN, startOfInvoice, endOfInvoice);
    }
    public PaymentRequest<T> withInvoiceStartingWith(String invoice){
       return withInvoice(Operator.BEGIN_WITH, invoice);
    }
    public PaymentRequest<T> withInvoiceContaining(String invoice){
       return withInvoice(Operator.CONTAIN, invoice);
    }

    public PaymentRequest<T> withInvoiceEndingWith(String invoice){
       return withInvoice(Operator.END_WITH, invoice);
    }

    public PaymentRequest<T> withInvoiceIs(String invoice){
       return withInvoice(Operator.EQUAL, invoice);
    }

    public PaymentRequest<T> withInvoiceSoundingLike(String invoice){
       return withInvoice(Operator.SOUNDS_LIKE, invoice);
    }



    public PaymentRequest<T> filterByCustomer(String... customer){
      if (customer == null || customer.length == 0) {
        throw new IllegalArgumentException("filterByCustomer parameter customer cannot be empty");
      }
      return appendSearchCriteria(createCustomerCriteria(Operator.EQUAL, (Object[])customer));
    }

    public PaymentRequest<T> withCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCustomerCriteria(operator, values));
    }

    public PaymentRequest<T> withCustomerIsUnknown(){
       return withCustomer(Operator.IS_NULL);
    }

    public PaymentRequest<T> withCustomerIsKnown(){
       return withCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.CUSTOMER_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withCustomerGreaterThan(String customer){
       return withCustomer(Operator.GREATER_THAN, customer);
    }

    public PaymentRequest<T> withCustomerGreaterThanOrEqualTo(String customer){
       return withCustomer(Operator.GREATER_THAN_OR_EQUAL, customer);
    }

    public PaymentRequest<T> withCustomerLessThan(String customer){
       return withCustomer(Operator.LESS_THAN, customer);
    }

    public PaymentRequest<T> withCustomerLessThanOrEqualTo(String customer){
       return withCustomer(Operator.LESS_THAN_OR_EQUAL, customer);
    }

    public PaymentRequest<T> withCustomerBetween(String startOfCustomer, String endOfCustomer){
       return withCustomer(Operator.BETWEEN, startOfCustomer, endOfCustomer);
    }
    public PaymentRequest<T> withCustomerStartingWith(String customer){
       return withCustomer(Operator.BEGIN_WITH, customer);
    }
    public PaymentRequest<T> withCustomerContaining(String customer){
       return withCustomer(Operator.CONTAIN, customer);
    }

    public PaymentRequest<T> withCustomerEndingWith(String customer){
       return withCustomer(Operator.END_WITH, customer);
    }

    public PaymentRequest<T> withCustomerIs(String customer){
       return withCustomer(Operator.EQUAL, customer);
    }

    public PaymentRequest<T> withCustomerSoundingLike(String customer){
       return withCustomer(Operator.SOUNDS_LIKE, customer);
    }



    public PaymentRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public PaymentRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public PaymentRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public PaymentRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.CREATE_TIME_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PaymentRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public PaymentRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PaymentRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public PaymentRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public PaymentRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PaymentRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PaymentRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PaymentRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PaymentRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public PaymentRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public PaymentRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public PaymentRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public PaymentRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.UPDATE_TIME_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public PaymentRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PaymentRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public PaymentRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PaymentRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PaymentRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PaymentRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Payment.VERSION_PROPERTY, operator, values);
    }

    public PaymentRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PaymentRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PaymentRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PaymentRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PaymentRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public PaymentRequest<T> count(){
        super.count();
        return this;
    }
    public PaymentRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PaymentRequest minAmount(){
        return minAmountAs(prefix("minOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest minAmountAs(String retName){
        super.min(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest maxAmount(){
        return maxAmountAs(prefix("maxOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest maxAmountAs(String retName){
        super.max(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest sumAmount(){
        return sumAmountAs(prefix("sumOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest sumAmountAs(String retName){
        super.sum(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest avgAmount(){
        return avgAmountAs(prefix("avgOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest avgAmountAs(String retName){
        super.avg(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest standardDeviationAmount(){
        return standardDeviationAmountAs(prefix("standardDeviationOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest standardDeviationAmountAs(String retName){
        super.standardDeviation(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest squareRootOfPopulationStandardDeviationAmount(){
        return squareRootOfPopulationStandardDeviationAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest squareRootOfPopulationStandardDeviationAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest sampleVarianceAmount(){
        return sampleVarianceAmountAs(prefix("sampleVarianceOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest sampleVarianceAmountAs(String retName){
        super.sampleVariance(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }
    public PaymentRequest samplePopulationVarianceAmount(){
        return samplePopulationVarianceAmountAs(prefix("samplePopulationVarianceOf",Payment.AMOUNT_PROPERTY));
    }

    public PaymentRequest samplePopulationVarianceAmountAs(String retName){
        super.samplePopulationVariance(retName, Payment.AMOUNT_PROPERTY);
        return this;
    }

    public PaymentRequest<T> groupById(){
       groupBy(Payment.ID_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByIdAs(String retName){
       groupBy(retName, Payment.ID_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.ID_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByAmount(){
       groupBy(Payment.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByAmountAs(String retName){
       groupBy(retName, Payment.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.AMOUNT_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByPaymentMethod(){
       groupBy(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByPaymentMethodAs(String retName){
       groupBy(retName, Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByPaymentMethodWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.PAYMENT_METHOD_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByTransactionRef(){
       groupBy(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByTransactionRefAs(String retName){
       groupBy(retName, Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByTransactionRefWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.TRANSACTION_REF_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByPaymentDate(){
       groupBy(Payment.PAYMENT_DATE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByPaymentDateAs(String retName){
       groupBy(retName, Payment.PAYMENT_DATE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByPaymentDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.PAYMENT_DATE_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByStatus(){
       groupBy(Payment.STATUS_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByStatusAs(String retName){
       groupBy(retName, Payment.STATUS_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.STATUS_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByInvoice(){
       groupBy(Payment.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByInvoiceAs(String retName){
       groupBy(retName, Payment.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByInvoiceWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.INVOICE_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByCustomer(){
       groupBy(Payment.CUSTOMER_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByCustomerAs(String retName){
       groupBy(retName, Payment.CUSTOMER_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.CUSTOMER_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByCreateTime(){
       groupBy(Payment.CREATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, Payment.CREATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByUpdateTime(){
       groupBy(Payment.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, Payment.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public PaymentRequest<T> groupByVersion(){
       groupBy(Payment.VERSION_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Payment.VERSION_PROPERTY);
       return this;
    }

    public PaymentRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Payment.VERSION_PROPERTY, function);
       return this;
    }



    public PaymentRequest<T> orderByIdAscending(){
       addOrderByAscending(Payment.ID_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByIdDescending(){
       addOrderByDescending(Payment.ID_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByAmountAscending(){
       addOrderByAscending(Payment.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByAmountDescending(){
       addOrderByDescending(Payment.AMOUNT_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByPaymentMethodAscending(){
       addOrderByAscending(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByPaymentMethodDescending(){
       addOrderByDescending(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByPaymentMethodAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByPaymentMethodDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Payment.PAYMENT_METHOD_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByTransactionRefAscending(){
       addOrderByAscending(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByTransactionRefDescending(){
       addOrderByDescending(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByTransactionRefAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByTransactionRefDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Payment.TRANSACTION_REF_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByPaymentDateAscending(){
       addOrderByAscending(Payment.PAYMENT_DATE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByPaymentDateDescending(){
       addOrderByDescending(Payment.PAYMENT_DATE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByStatusAscending(){
       addOrderByAscending(Payment.STATUS_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByStatusDescending(){
       addOrderByDescending(Payment.STATUS_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Payment.STATUS_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Payment.STATUS_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByInvoiceAscending(){
       addOrderByAscending(Payment.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByInvoiceDescending(){
       addOrderByDescending(Payment.INVOICE_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByInvoiceAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Payment.INVOICE_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByInvoiceDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Payment.INVOICE_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByCustomerAscending(){
       addOrderByAscending(Payment.CUSTOMER_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByCustomerDescending(){
       addOrderByDescending(Payment.CUSTOMER_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByCustomerAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Payment.CUSTOMER_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByCustomerDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Payment.CUSTOMER_PROPERTY);
       return this;
    }
    public PaymentRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(Payment.CREATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(Payment.CREATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(Payment.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(Payment.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByVersionAscending(){
       addOrderByAscending(Payment.VERSION_PROPERTY);
       return this;
    }

    public PaymentRequest<T> orderByVersionDescending(){
       addOrderByDescending(Payment.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public PaymentRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PaymentRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PaymentRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PaymentRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PaymentRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}