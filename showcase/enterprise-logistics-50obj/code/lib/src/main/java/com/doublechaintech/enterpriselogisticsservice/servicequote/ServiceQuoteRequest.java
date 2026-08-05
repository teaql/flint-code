package com.doublechaintech.enterpriselogisticsservice.servicequote;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ServiceQuoteRequest<T extends ServiceQuote> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public ServiceQuoteRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public ServiceQuoteRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public ServiceQuoteRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public ServiceQuoteRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public ServiceQuoteRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public ServiceQuoteRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public ServiceQuoteRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (ServiceQuoteRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public ServiceQuoteRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public ServiceQuoteRequest<T> matchingAnyOf(ServiceQuoteRequest serviceQuote){
        super.internalMatchAny(serviceQuote);
        return this;
    }

    public ServiceQuoteRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public ServiceQuoteRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public ServiceQuoteRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public ServiceQuoteRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectQuoteNumber().selectEstimatedCost().selectCurrency().selectValidUntil().selectStatus().selectPrivateCustomerIdOnly().selectCorporateCustomerIdOnly().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public ServiceQuoteRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ServiceQuoteRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectQuoteNumber().selectEstimatedCost().selectCurrency().selectValidUntil().selectStatus().selectPrivateCustomer().selectCorporateCustomer().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public ServiceQuoteRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectQuoteNumber().selectEstimatedCost().selectCurrency().selectValidUntil().selectStatus().selectPrivateCustomer().selectCorporateCustomer().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public ServiceQuoteRequest<T> selectId(){
       selectProperty(ServiceQuote.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectId(){
       unselectProperty(ServiceQuote.ID_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectQuoteNumber(){
       selectProperty(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the quoteNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  quoteNumber) to fetch quoteNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectQuoteNumber(){
       unselectProperty(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectEstimatedCost(){
       selectProperty(ServiceQuote.ESTIMATED_COST_PROPERTY);
       return this;
    }

    /**
     * fill the estimatedCost with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  estimatedCost) to fetch estimatedCost property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the estimatedCost with customized aggrFunction, TEAQL uses ({aggrFunction}(estimatedCost) AS estimatedCost to fetch estimatedCost property.
     * @param aggrFunction  aggrFunction
     */
    public ServiceQuoteRequest<T> selectEstimatedCost(AggrFunction aggrFunction){
       selectProperty(ServiceQuote.ESTIMATED_COST_PROPERTY, aggrFunction);
       return this;
    }


    public ServiceQuoteRequest<T> unselectEstimatedCost(){
       unselectProperty(ServiceQuote.ESTIMATED_COST_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectCurrency(){
       selectProperty(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectCurrency(){
       unselectProperty(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectValidUntil(){
       selectProperty(ServiceQuote.VALID_UNTIL_PROPERTY);
       return this;
    }

    /**
     * fill the validUntil with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  validUntil) to fetch validUntil property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectValidUntil(){
       unselectProperty(ServiceQuote.VALID_UNTIL_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectStatus(){
       selectProperty(ServiceQuote.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectStatus(){
       unselectProperty(ServiceQuote.STATUS_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectPrivateCustomerIdOnly(){
       selectProperty(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> selectPrivateCustomer(){
        return selectPrivateCustomerWith(Q.privateCustomers().unlimited().selectSelf());
    }

    public ServiceQuoteRequest<T> selectPrivateCustomerWith(PrivateCustomerRequest privateCustomer){
       selectProperty(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       enhanceRelation(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, privateCustomer);
       return this;
    }

    public ServiceQuoteRequest<T> unselectPrivateCustomer(){
       unselectProperty(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectCorporateCustomerIdOnly(){
       selectProperty(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> selectCorporateCustomer(){
        return selectCorporateCustomerWith(Q.corporateCustomers().unlimited().selectSelf());
    }

    public ServiceQuoteRequest<T> selectCorporateCustomerWith(CorporateCustomerRequest corporateCustomer){
       selectProperty(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       enhanceRelation(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer);
       return this;
    }

    public ServiceQuoteRequest<T> unselectCorporateCustomer(){
       unselectProperty(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectCreatedAt(){
       selectProperty(ServiceQuote.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectCreatedAt(){
       unselectProperty(ServiceQuote.CREATED_AT_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectUpdatedAt(){
       selectProperty(ServiceQuote.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectUpdatedAt(){
       unselectProperty(ServiceQuote.UPDATED_AT_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> selectVersion(){
       selectProperty(ServiceQuote.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceQuoteRequest<T> unselectVersion(){
       unselectProperty(ServiceQuote.VERSION_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.ID_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public ServiceQuoteRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public ServiceQuoteRequest<T> filterByQuoteNumber(String... quoteNumber){
      if (quoteNumber == null || quoteNumber.length == 0) {
        throw new IllegalArgumentException("filterByQuoteNumber parameter quoteNumber cannot be empty");
      }
      return appendSearchCriteria(createQuoteNumberCriteria(Operator.EQUAL, (Object[])quoteNumber));
    }

    public ServiceQuoteRequest<T> withQuoteNumber(Operator operator, Object... values){
       return appendSearchCriteria(createQuoteNumberCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withQuoteNumberIsUnknown(){
       return withQuoteNumber(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withQuoteNumberIsKnown(){
       return withQuoteNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createQuoteNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.QUOTE_NUMBER_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withQuoteNumberGreaterThan(String quoteNumber){
       return withQuoteNumber(Operator.GREATER_THAN, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberGreaterThanOrEqualTo(String quoteNumber){
       return withQuoteNumber(Operator.GREATER_THAN_OR_EQUAL, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberLessThan(String quoteNumber){
       return withQuoteNumber(Operator.LESS_THAN, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberLessThanOrEqualTo(String quoteNumber){
       return withQuoteNumber(Operator.LESS_THAN_OR_EQUAL, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberBetween(String startOfQuoteNumber, String endOfQuoteNumber){
       return withQuoteNumber(Operator.BETWEEN, startOfQuoteNumber, endOfQuoteNumber);
    }
    public ServiceQuoteRequest<T> withQuoteNumberStartingWith(String quoteNumber){
       return withQuoteNumber(Operator.BEGIN_WITH, quoteNumber);
    }
    public ServiceQuoteRequest<T> withQuoteNumberContaining(String quoteNumber){
       return withQuoteNumber(Operator.CONTAIN, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberEndingWith(String quoteNumber){
       return withQuoteNumber(Operator.END_WITH, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberIs(String quoteNumber){
       return withQuoteNumber(Operator.EQUAL, quoteNumber);
    }

    public ServiceQuoteRequest<T> withQuoteNumberSoundingLike(String quoteNumber){
       return withQuoteNumber(Operator.SOUNDS_LIKE, quoteNumber);
    }



    public ServiceQuoteRequest<T> filterByEstimatedCost(BigDecimal... estimatedCost){
      if (estimatedCost == null || estimatedCost.length == 0) {
        throw new IllegalArgumentException("filterByEstimatedCost parameter estimatedCost cannot be empty");
      }
      return appendSearchCriteria(createEstimatedCostCriteria(Operator.EQUAL, (Object[])estimatedCost));
    }

    public ServiceQuoteRequest<T> withEstimatedCost(Operator operator, Object... values){
       return appendSearchCriteria(createEstimatedCostCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withEstimatedCostIsUnknown(){
       return withEstimatedCost(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withEstimatedCostIsKnown(){
       return withEstimatedCost(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEstimatedCostCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.ESTIMATED_COST_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withEstimatedCostGreaterThan(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.GREATER_THAN, estimatedCost);
    }

    public ServiceQuoteRequest<T> withEstimatedCostGreaterThanOrEqualTo(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.GREATER_THAN_OR_EQUAL, estimatedCost);
    }

    public ServiceQuoteRequest<T> withEstimatedCostLessThan(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.LESS_THAN, estimatedCost);
    }

    public ServiceQuoteRequest<T> withEstimatedCostLessThanOrEqualTo(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.LESS_THAN_OR_EQUAL, estimatedCost);
    }

    public ServiceQuoteRequest<T> withEstimatedCostBetween(BigDecimal startOfEstimatedCost, BigDecimal endOfEstimatedCost){
       return withEstimatedCost(Operator.BETWEEN, startOfEstimatedCost, endOfEstimatedCost);
    }



    public ServiceQuoteRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public ServiceQuoteRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.CURRENCY_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public ServiceQuoteRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public ServiceQuoteRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public ServiceQuoteRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public ServiceQuoteRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public ServiceQuoteRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public ServiceQuoteRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public ServiceQuoteRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public ServiceQuoteRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public ServiceQuoteRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public ServiceQuoteRequest<T> filterByValidUntil(LocalDate... validUntil){
      if (validUntil == null || validUntil.length == 0) {
        throw new IllegalArgumentException("filterByValidUntil parameter validUntil cannot be empty");
      }
      return appendSearchCriteria(createValidUntilCriteria(Operator.EQUAL, (Object[])validUntil));
    }

    public ServiceQuoteRequest<T> withValidUntil(Operator operator, Object... values){
       return appendSearchCriteria(createValidUntilCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withValidUntilIsUnknown(){
       return withValidUntil(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withValidUntilIsKnown(){
       return withValidUntil(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createValidUntilCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.VALID_UNTIL_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withValidUntilGreaterThan(LocalDate validUntil){
       return withValidUntil(Operator.GREATER_THAN, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilGreaterThanOrEqualTo(LocalDate validUntil){
       return withValidUntil(Operator.GREATER_THAN_OR_EQUAL, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilLessThan(LocalDate validUntil){
       return withValidUntil(Operator.LESS_THAN, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilLessThanOrEqualTo(LocalDate validUntil){
       return withValidUntil(Operator.LESS_THAN_OR_EQUAL, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilBetween(LocalDate startOfValidUntil, LocalDate endOfValidUntil){
       return withValidUntil(Operator.BETWEEN, startOfValidUntil, endOfValidUntil);
    }
    public ServiceQuoteRequest<T> withValidUntilBefore(LocalDate validUntil){
       return withValidUntil(Operator.LESS_THAN, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilBefore(Date validUntil){
       return withValidUntil(Operator.LESS_THAN, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilAfter(LocalDate validUntil){
       return withValidUntil(Operator.GREATER_THAN, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilAfter(Date validUntil){
       return withValidUntil(Operator.GREATER_THAN, validUntil);
    }

    public ServiceQuoteRequest<T> withValidUntilBetween(Date startOfValidUntil, Date endOfValidUntil){
       return withValidUntil(Operator.BETWEEN, startOfValidUntil, endOfValidUntil);
    }




    public ServiceQuoteRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public ServiceQuoteRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.STATUS_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public ServiceQuoteRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public ServiceQuoteRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public ServiceQuoteRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public ServiceQuoteRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public ServiceQuoteRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public ServiceQuoteRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public ServiceQuoteRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public ServiceQuoteRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public ServiceQuoteRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public ServiceQuoteRequest<T> filterByPrivateCustomer(PrivateCustomer... privateCustomer){
      if (privateCustomer == null || privateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByPrivateCustomer parameter privateCustomer cannot be empty");
      }
      return appendSearchCriteria(createPrivateCustomerCriteria(Operator.EQUAL, (Object[])privateCustomer));
    }

    public ServiceQuoteRequest<T> withPrivateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createPrivateCustomerCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withPrivateCustomerIsUnknown(){
       return withPrivateCustomer(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withPrivateCustomerIsKnown(){
       return withPrivateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPrivateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> filterByPrivateCustomer(Long privateCustomer){
      if(privateCustomer == null){
         return this;
      }
      return withPrivateCustomer(Operator.EQUAL, privateCustomer);
    }
    public ServiceQuoteRequest<T> withPrivateCustomerMatching(PrivateCustomerRequest privateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, PrivateCustomer.ID_PROPERTY));
    }

    public ServiceQuoteRequest<T> filterByCorporateCustomer(CorporateCustomer... corporateCustomer){
      if (corporateCustomer == null || corporateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByCorporateCustomer parameter corporateCustomer cannot be empty");
      }
      return appendSearchCriteria(createCorporateCustomerCriteria(Operator.EQUAL, (Object[])corporateCustomer));
    }

    public ServiceQuoteRequest<T> withCorporateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCorporateCustomerCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withCorporateCustomerIsUnknown(){
       return withCorporateCustomer(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withCorporateCustomerIsKnown(){
       return withCorporateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCorporateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> filterByCorporateCustomer(Long corporateCustomer){
      if(corporateCustomer == null){
         return this;
      }
      return withCorporateCustomer(Operator.EQUAL, corporateCustomer);
    }
    public ServiceQuoteRequest<T> withCorporateCustomerMatching(CorporateCustomerRequest corporateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, CorporateCustomer.ID_PROPERTY));
    }

    public ServiceQuoteRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public ServiceQuoteRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.CREATED_AT_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public ServiceQuoteRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public ServiceQuoteRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public ServiceQuoteRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public ServiceQuoteRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.UPDATED_AT_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public ServiceQuoteRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public ServiceQuoteRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public ServiceQuoteRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public ServiceQuoteRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public ServiceQuoteRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public ServiceQuoteRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceQuote.VERSION_PROPERTY, operator, values);
    }

    public ServiceQuoteRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public ServiceQuoteRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public ServiceQuoteRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public ServiceQuoteRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public ServiceQuoteRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public ServiceQuoteRequest<T> count(){
        super.count();
        return this;
    }
    public ServiceQuoteRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public ServiceQuoteRequest minEstimatedCost(){
        return minEstimatedCostAs(prefix("minOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest minEstimatedCostAs(String retName){
        super.min(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest maxEstimatedCost(){
        return maxEstimatedCostAs(prefix("maxOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest maxEstimatedCostAs(String retName){
        super.max(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest sumEstimatedCost(){
        return sumEstimatedCostAs(prefix("sumOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest sumEstimatedCostAs(String retName){
        super.sum(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest avgEstimatedCost(){
        return avgEstimatedCostAs(prefix("avgOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest avgEstimatedCostAs(String retName){
        super.avg(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest standardDeviationEstimatedCost(){
        return standardDeviationEstimatedCostAs(prefix("standardDeviationOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest standardDeviationEstimatedCostAs(String retName){
        super.standardDeviation(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest squareRootOfPopulationStandardDeviationEstimatedCost(){
        return squareRootOfPopulationStandardDeviationEstimatedCostAs(prefix("squareRootOfPopulationStandardDeviationOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest squareRootOfPopulationStandardDeviationEstimatedCostAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest sampleVarianceEstimatedCost(){
        return sampleVarianceEstimatedCostAs(prefix("sampleVarianceOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest sampleVarianceEstimatedCostAs(String retName){
        super.sampleVariance(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest samplePopulationVarianceEstimatedCost(){
        return samplePopulationVarianceEstimatedCostAs(prefix("samplePopulationVarianceOf",ServiceQuote.ESTIMATED_COST_PROPERTY));
    }

    public ServiceQuoteRequest samplePopulationVarianceEstimatedCostAs(String retName){
        super.samplePopulationVariance(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public ServiceQuoteRequest<T> groupByPrivateCustomerWithDetails(){
       return groupByPrivateCustomerWithDetails(Q.privateCustomers().unlimited());
    }

    public ServiceQuoteRequest<T> groupByPrivateCustomerWithDetails(PrivateCustomerRequest subRequest){
       aggregate(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCorporateCustomerWithDetails(){
       return groupByCorporateCustomerWithDetails(Q.corporateCustomers().unlimited());
    }

    public ServiceQuoteRequest<T> groupByCorporateCustomerWithDetails(CorporateCustomerRequest subRequest){
       aggregate(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }





    public ServiceQuoteRequest<T> groupById(){
       groupBy(ServiceQuote.ID_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByIdAs(String retName){
       groupBy(retName, ServiceQuote.ID_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.ID_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByQuoteNumber(){
       groupBy(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByQuoteNumberAs(String retName){
       groupBy(retName, ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByQuoteNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.QUOTE_NUMBER_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByEstimatedCost(){
       groupBy(ServiceQuote.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByEstimatedCostAs(String retName){
       groupBy(retName, ServiceQuote.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByEstimatedCostWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.ESTIMATED_COST_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCurrency(){
       groupBy(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.CURRENCY_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByValidUntil(){
       groupBy(ServiceQuote.VALID_UNTIL_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByValidUntilAs(String retName){
       groupBy(retName, ServiceQuote.VALID_UNTIL_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByValidUntilWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.VALID_UNTIL_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByStatus(){
       groupBy(ServiceQuote.STATUS_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByStatusAs(String retName){
       groupBy(retName, ServiceQuote.STATUS_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.STATUS_PROPERTY, function);
       return this;
    }
    public ServiceQuoteRequest<T> groupByPrivateCustomerWith(PrivateCustomerRequest subRequest){
       groupBy(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public ServiceQuoteRequest<T> groupByPrivateCustomer(){
       groupBy(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByPrivateCustomerAs(String retName){
       groupBy(retName, ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByPrivateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, function);
       return this;
    }
    public ServiceQuoteRequest<T> groupByCorporateCustomerWith(CorporateCustomerRequest subRequest){
       groupBy(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public ServiceQuoteRequest<T> groupByCorporateCustomer(){
       groupBy(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCorporateCustomerAs(String retName){
       groupBy(retName, ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCorporateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCreatedAt(){
       groupBy(ServiceQuote.CREATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, ServiceQuote.CREATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.CREATED_AT_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByUpdatedAt(){
       groupBy(ServiceQuote.UPDATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, ServiceQuote.UPDATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public ServiceQuoteRequest<T> groupByVersion(){
       groupBy(ServiceQuote.VERSION_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByVersionAs(String retName){
       groupBy(retName, ServiceQuote.VERSION_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceQuote.VERSION_PROPERTY, function);
       return this;
    }



    public ServiceQuoteRequest<T> orderByIdAscending(){
       addOrderByAscending(ServiceQuote.ID_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByIdDescending(){
       addOrderByDescending(ServiceQuote.ID_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByQuoteNumberAscending(){
       addOrderByAscending(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByQuoteNumberDescending(){
       addOrderByDescending(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> orderByQuoteNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByQuoteNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceQuote.QUOTE_NUMBER_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> orderByEstimatedCostAscending(){
       addOrderByAscending(ServiceQuote.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByEstimatedCostDescending(){
       addOrderByDescending(ServiceQuote.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceQuote.CURRENCY_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> orderByValidUntilAscending(){
       addOrderByAscending(ServiceQuote.VALID_UNTIL_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByValidUntilDescending(){
       addOrderByDescending(ServiceQuote.VALID_UNTIL_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByStatusAscending(){
       addOrderByAscending(ServiceQuote.STATUS_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByStatusDescending(){
       addOrderByDescending(ServiceQuote.STATUS_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceQuote.STATUS_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceQuote.STATUS_PROPERTY);
       return this;
    }
    public ServiceQuoteRequest<T> orderByPrivateCustomerAscending(){
       addOrderByAscending(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByPrivateCustomerDescending(){
       addOrderByDescending(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCorporateCustomerAscending(){
       addOrderByAscending(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCorporateCustomerDescending(){
       addOrderByDescending(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(ServiceQuote.CREATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(ServiceQuote.CREATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(ServiceQuote.UPDATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(ServiceQuote.UPDATED_AT_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByVersionAscending(){
       addOrderByAscending(ServiceQuote.VERSION_PROPERTY);
       return this;
    }

    public ServiceQuoteRequest<T> orderByVersionDescending(){
       addOrderByDescending(ServiceQuote.VERSION_PROPERTY);
       return this;
    }


    public PrivateCustomerRequest rollUpToPrivateCustomer(){
       PrivateCustomerRequest privateCustomer = Q.privateCustomers().unlimited();
       this.withPrivateCustomerMatching(privateCustomer)
           .groupByPrivateCustomerWith(privateCustomer);
       return privateCustomer;
    }

    public CorporateCustomerRequest rollUpToCorporateCustomer(){
       CorporateCustomerRequest corporateCustomer = Q.corporateCustomers().unlimited();
       this.withCorporateCustomerMatching(corporateCustomer)
           .groupByCorporateCustomerWith(corporateCustomer);
       return corporateCustomer;
    }





   public ServiceQuoteRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer){
       return facetByPrivateCustomerAs(facetName, privateCustomer, true);
   }

   public ServiceQuoteRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer, boolean includeAllFacets){
       addFacet(facetName, ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, includeAllFacets);
       return this;
   }
   public ServiceQuoteRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer){
       return facetByCorporateCustomerAs(facetName, corporateCustomer, true);
   }

   public ServiceQuoteRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer, boolean includeAllFacets){
       addFacet(facetName, ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public ServiceQuoteRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public ServiceQuoteRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public ServiceQuoteRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public ServiceQuoteRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public ServiceQuoteRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}