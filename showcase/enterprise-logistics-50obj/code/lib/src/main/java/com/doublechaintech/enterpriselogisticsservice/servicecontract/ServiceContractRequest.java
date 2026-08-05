package com.doublechaintech.enterpriselogisticsservice.servicecontract;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest;
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

public class ServiceContractRequest<T extends ServiceContract> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public ServiceContractRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public ServiceContractRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public ServiceContractRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public ServiceContractRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public ServiceContractRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public ServiceContractRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public ServiceContractRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (ServiceContractRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public ServiceContractRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public ServiceContractRequest<T> matchingAnyOf(ServiceContractRequest serviceContract){
        super.internalMatchAny(serviceContract);
        return this;
    }

    public ServiceContractRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public ServiceContractRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public ServiceContractRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public ServiceContractRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectContractNumber().selectTitle().selectStartDate().selectEndDate().selectStatus().selectTotalValue().selectCurrency().selectCorporateCustomerIdOnly().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public ServiceContractRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ServiceContractRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectContractNumber().selectTitle().selectStartDate().selectEndDate().selectStatus().selectTotalValue().selectCurrency().selectCorporateCustomer().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public ServiceContractRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectContractNumber().selectTitle().selectStartDate().selectEndDate().selectStatus().selectTotalValue().selectCurrency().selectCorporateCustomer().selectCreatedTime().selectUpdateTime().selectVersion();
    }


    public ServiceContractRequest<T> selectId(){
       selectProperty(ServiceContract.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectId(){
       unselectProperty(ServiceContract.ID_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectContractNumber(){
       selectProperty(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the contractNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  contractNumber) to fetch contractNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectContractNumber(){
       unselectProperty(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectTitle(){
       selectProperty(ServiceContract.TITLE_PROPERTY);
       return this;
    }

    /**
     * fill the title with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  title) to fetch title property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectTitle(){
       unselectProperty(ServiceContract.TITLE_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectStartDate(){
       selectProperty(ServiceContract.START_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the startDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startDate) to fetch startDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectStartDate(){
       unselectProperty(ServiceContract.START_DATE_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectEndDate(){
       selectProperty(ServiceContract.END_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the endDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endDate) to fetch endDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectEndDate(){
       unselectProperty(ServiceContract.END_DATE_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectStatus(){
       selectProperty(ServiceContract.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectStatus(){
       unselectProperty(ServiceContract.STATUS_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectTotalValue(){
       selectProperty(ServiceContract.TOTAL_VALUE_PROPERTY);
       return this;
    }

    /**
     * fill the totalValue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalValue) to fetch totalValue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalValue with customized aggrFunction, TEAQL uses ({aggrFunction}(totalValue) AS totalValue to fetch totalValue property.
     * @param aggrFunction  aggrFunction
     */
    public ServiceContractRequest<T> selectTotalValue(AggrFunction aggrFunction){
       selectProperty(ServiceContract.TOTAL_VALUE_PROPERTY, aggrFunction);
       return this;
    }


    public ServiceContractRequest<T> unselectTotalValue(){
       unselectProperty(ServiceContract.TOTAL_VALUE_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectCurrency(){
       selectProperty(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectCurrency(){
       unselectProperty(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectCorporateCustomerIdOnly(){
       selectProperty(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> selectCorporateCustomer(){
        return selectCorporateCustomerWith(Q.corporateCustomers().unlimited().selectSelf());
    }

    public ServiceContractRequest<T> selectCorporateCustomerWith(CorporateCustomerRequest corporateCustomer){
       selectProperty(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       enhanceRelation(ServiceContract.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer);
       return this;
    }

    public ServiceContractRequest<T> unselectCorporateCustomer(){
       unselectProperty(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectCreatedTime(){
       selectProperty(ServiceContract.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectCreatedTime(){
       unselectProperty(ServiceContract.CREATED_TIME_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectUpdateTime(){
       selectProperty(ServiceContract.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectUpdateTime(){
       unselectProperty(ServiceContract.UPDATE_TIME_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> selectVersion(){
       selectProperty(ServiceContract.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ServiceContractRequest<T> unselectVersion(){
       unselectProperty(ServiceContract.VERSION_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.ID_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public ServiceContractRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public ServiceContractRequest<T> filterByContractNumber(String... contractNumber){
      if (contractNumber == null || contractNumber.length == 0) {
        throw new IllegalArgumentException("filterByContractNumber parameter contractNumber cannot be empty");
      }
      return appendSearchCriteria(createContractNumberCriteria(Operator.EQUAL, (Object[])contractNumber));
    }

    public ServiceContractRequest<T> withContractNumber(Operator operator, Object... values){
       return appendSearchCriteria(createContractNumberCriteria(operator, values));
    }

    public ServiceContractRequest<T> withContractNumberIsUnknown(){
       return withContractNumber(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withContractNumberIsKnown(){
       return withContractNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContractNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.CONTRACT_NUMBER_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withContractNumberGreaterThan(String contractNumber){
       return withContractNumber(Operator.GREATER_THAN, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberGreaterThanOrEqualTo(String contractNumber){
       return withContractNumber(Operator.GREATER_THAN_OR_EQUAL, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberLessThan(String contractNumber){
       return withContractNumber(Operator.LESS_THAN, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberLessThanOrEqualTo(String contractNumber){
       return withContractNumber(Operator.LESS_THAN_OR_EQUAL, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberBetween(String startOfContractNumber, String endOfContractNumber){
       return withContractNumber(Operator.BETWEEN, startOfContractNumber, endOfContractNumber);
    }
    public ServiceContractRequest<T> withContractNumberStartingWith(String contractNumber){
       return withContractNumber(Operator.BEGIN_WITH, contractNumber);
    }
    public ServiceContractRequest<T> withContractNumberContaining(String contractNumber){
       return withContractNumber(Operator.CONTAIN, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberEndingWith(String contractNumber){
       return withContractNumber(Operator.END_WITH, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberIs(String contractNumber){
       return withContractNumber(Operator.EQUAL, contractNumber);
    }

    public ServiceContractRequest<T> withContractNumberSoundingLike(String contractNumber){
       return withContractNumber(Operator.SOUNDS_LIKE, contractNumber);
    }



    public ServiceContractRequest<T> filterByTitle(String... title){
      if (title == null || title.length == 0) {
        throw new IllegalArgumentException("filterByTitle parameter title cannot be empty");
      }
      return appendSearchCriteria(createTitleCriteria(Operator.EQUAL, (Object[])title));
    }

    public ServiceContractRequest<T> withTitle(Operator operator, Object... values){
       return appendSearchCriteria(createTitleCriteria(operator, values));
    }

    public ServiceContractRequest<T> withTitleIsUnknown(){
       return withTitle(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withTitleIsKnown(){
       return withTitle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTitleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.TITLE_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withTitleGreaterThan(String title){
       return withTitle(Operator.GREATER_THAN, title);
    }

    public ServiceContractRequest<T> withTitleGreaterThanOrEqualTo(String title){
       return withTitle(Operator.GREATER_THAN_OR_EQUAL, title);
    }

    public ServiceContractRequest<T> withTitleLessThan(String title){
       return withTitle(Operator.LESS_THAN, title);
    }

    public ServiceContractRequest<T> withTitleLessThanOrEqualTo(String title){
       return withTitle(Operator.LESS_THAN_OR_EQUAL, title);
    }

    public ServiceContractRequest<T> withTitleBetween(String startOfTitle, String endOfTitle){
       return withTitle(Operator.BETWEEN, startOfTitle, endOfTitle);
    }
    public ServiceContractRequest<T> withTitleStartingWith(String title){
       return withTitle(Operator.BEGIN_WITH, title);
    }
    public ServiceContractRequest<T> withTitleContaining(String title){
       return withTitle(Operator.CONTAIN, title);
    }

    public ServiceContractRequest<T> withTitleEndingWith(String title){
       return withTitle(Operator.END_WITH, title);
    }

    public ServiceContractRequest<T> withTitleIs(String title){
       return withTitle(Operator.EQUAL, title);
    }

    public ServiceContractRequest<T> withTitleSoundingLike(String title){
       return withTitle(Operator.SOUNDS_LIKE, title);
    }



    public ServiceContractRequest<T> filterByStartDate(LocalDate... startDate){
      if (startDate == null || startDate.length == 0) {
        throw new IllegalArgumentException("filterByStartDate parameter startDate cannot be empty");
      }
      return appendSearchCriteria(createStartDateCriteria(Operator.EQUAL, (Object[])startDate));
    }

    public ServiceContractRequest<T> withStartDate(Operator operator, Object... values){
       return appendSearchCriteria(createStartDateCriteria(operator, values));
    }

    public ServiceContractRequest<T> withStartDateIsUnknown(){
       return withStartDate(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withStartDateIsKnown(){
       return withStartDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.START_DATE_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withStartDateGreaterThan(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public ServiceContractRequest<T> withStartDateGreaterThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN_OR_EQUAL, startDate);
    }

    public ServiceContractRequest<T> withStartDateLessThan(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public ServiceContractRequest<T> withStartDateLessThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN_OR_EQUAL, startDate);
    }

    public ServiceContractRequest<T> withStartDateBetween(LocalDate startOfStartDate, LocalDate endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }
    public ServiceContractRequest<T> withStartDateBefore(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public ServiceContractRequest<T> withStartDateBefore(Date startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public ServiceContractRequest<T> withStartDateAfter(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public ServiceContractRequest<T> withStartDateAfter(Date startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public ServiceContractRequest<T> withStartDateBetween(Date startOfStartDate, Date endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }




    public ServiceContractRequest<T> filterByEndDate(LocalDate... endDate){
      if (endDate == null || endDate.length == 0) {
        throw new IllegalArgumentException("filterByEndDate parameter endDate cannot be empty");
      }
      return appendSearchCriteria(createEndDateCriteria(Operator.EQUAL, (Object[])endDate));
    }

    public ServiceContractRequest<T> withEndDate(Operator operator, Object... values){
       return appendSearchCriteria(createEndDateCriteria(operator, values));
    }

    public ServiceContractRequest<T> withEndDateIsUnknown(){
       return withEndDate(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withEndDateIsKnown(){
       return withEndDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.END_DATE_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withEndDateGreaterThan(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public ServiceContractRequest<T> withEndDateGreaterThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN_OR_EQUAL, endDate);
    }

    public ServiceContractRequest<T> withEndDateLessThan(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public ServiceContractRequest<T> withEndDateLessThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN_OR_EQUAL, endDate);
    }

    public ServiceContractRequest<T> withEndDateBetween(LocalDate startOfEndDate, LocalDate endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }
    public ServiceContractRequest<T> withEndDateBefore(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public ServiceContractRequest<T> withEndDateBefore(Date endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public ServiceContractRequest<T> withEndDateAfter(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public ServiceContractRequest<T> withEndDateAfter(Date endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public ServiceContractRequest<T> withEndDateBetween(Date startOfEndDate, Date endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }




    public ServiceContractRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public ServiceContractRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public ServiceContractRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.STATUS_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public ServiceContractRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public ServiceContractRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public ServiceContractRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public ServiceContractRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public ServiceContractRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public ServiceContractRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public ServiceContractRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public ServiceContractRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public ServiceContractRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public ServiceContractRequest<T> filterByTotalValue(BigDecimal... totalValue){
      if (totalValue == null || totalValue.length == 0) {
        throw new IllegalArgumentException("filterByTotalValue parameter totalValue cannot be empty");
      }
      return appendSearchCriteria(createTotalValueCriteria(Operator.EQUAL, (Object[])totalValue));
    }

    public ServiceContractRequest<T> withTotalValue(Operator operator, Object... values){
       return appendSearchCriteria(createTotalValueCriteria(operator, values));
    }

    public ServiceContractRequest<T> withTotalValueIsUnknown(){
       return withTotalValue(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withTotalValueIsKnown(){
       return withTotalValue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalValueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.TOTAL_VALUE_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withTotalValueGreaterThan(BigDecimal totalValue){
       return withTotalValue(Operator.GREATER_THAN, totalValue);
    }

    public ServiceContractRequest<T> withTotalValueGreaterThanOrEqualTo(BigDecimal totalValue){
       return withTotalValue(Operator.GREATER_THAN_OR_EQUAL, totalValue);
    }

    public ServiceContractRequest<T> withTotalValueLessThan(BigDecimal totalValue){
       return withTotalValue(Operator.LESS_THAN, totalValue);
    }

    public ServiceContractRequest<T> withTotalValueLessThanOrEqualTo(BigDecimal totalValue){
       return withTotalValue(Operator.LESS_THAN_OR_EQUAL, totalValue);
    }

    public ServiceContractRequest<T> withTotalValueBetween(BigDecimal startOfTotalValue, BigDecimal endOfTotalValue){
       return withTotalValue(Operator.BETWEEN, startOfTotalValue, endOfTotalValue);
    }



    public ServiceContractRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public ServiceContractRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public ServiceContractRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.CURRENCY_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public ServiceContractRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public ServiceContractRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public ServiceContractRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public ServiceContractRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public ServiceContractRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public ServiceContractRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public ServiceContractRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public ServiceContractRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public ServiceContractRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public ServiceContractRequest<T> filterByCorporateCustomer(CorporateCustomer... corporateCustomer){
      if (corporateCustomer == null || corporateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByCorporateCustomer parameter corporateCustomer cannot be empty");
      }
      return appendSearchCriteria(createCorporateCustomerCriteria(Operator.EQUAL, (Object[])corporateCustomer));
    }

    public ServiceContractRequest<T> withCorporateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCorporateCustomerCriteria(operator, values));
    }

    public ServiceContractRequest<T> withCorporateCustomerIsUnknown(){
       return withCorporateCustomer(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withCorporateCustomerIsKnown(){
       return withCorporateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCorporateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.CORPORATE_CUSTOMER_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> filterByCorporateCustomer(Long corporateCustomer){
      if(corporateCustomer == null){
         return this;
      }
      return withCorporateCustomer(Operator.EQUAL, corporateCustomer);
    }
    public ServiceContractRequest<T> withCorporateCustomerMatching(CorporateCustomerRequest corporateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(ServiceContract.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, CorporateCustomer.ID_PROPERTY));
    }

    public ServiceContractRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public ServiceContractRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public ServiceContractRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.CREATED_TIME_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public ServiceContractRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public ServiceContractRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public ServiceContractRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public ServiceContractRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public ServiceContractRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.UPDATE_TIME_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public ServiceContractRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public ServiceContractRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public ServiceContractRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public ServiceContractRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public ServiceContractRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public ServiceContractRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ServiceContract.VERSION_PROPERTY, operator, values);
    }

    public ServiceContractRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public ServiceContractRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public ServiceContractRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public ServiceContractRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public ServiceContractRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public ServiceContractRequest<T> count(){
        super.count();
        return this;
    }
    public ServiceContractRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public ServiceContractRequest minTotalValue(){
        return minTotalValueAs(prefix("minOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest minTotalValueAs(String retName){
        super.min(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest maxTotalValue(){
        return maxTotalValueAs(prefix("maxOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest maxTotalValueAs(String retName){
        super.max(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest sumTotalValue(){
        return sumTotalValueAs(prefix("sumOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest sumTotalValueAs(String retName){
        super.sum(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest avgTotalValue(){
        return avgTotalValueAs(prefix("avgOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest avgTotalValueAs(String retName){
        super.avg(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest standardDeviationTotalValue(){
        return standardDeviationTotalValueAs(prefix("standardDeviationOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest standardDeviationTotalValueAs(String retName){
        super.standardDeviation(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest squareRootOfPopulationStandardDeviationTotalValue(){
        return squareRootOfPopulationStandardDeviationTotalValueAs(prefix("squareRootOfPopulationStandardDeviationOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest squareRootOfPopulationStandardDeviationTotalValueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest sampleVarianceTotalValue(){
        return sampleVarianceTotalValueAs(prefix("sampleVarianceOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest sampleVarianceTotalValueAs(String retName){
        super.sampleVariance(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest samplePopulationVarianceTotalValue(){
        return samplePopulationVarianceTotalValueAs(prefix("samplePopulationVarianceOf",ServiceContract.TOTAL_VALUE_PROPERTY));
    }

    public ServiceContractRequest samplePopulationVarianceTotalValueAs(String retName){
        super.samplePopulationVariance(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public ServiceContractRequest<T> groupByCorporateCustomerWithDetails(){
       return groupByCorporateCustomerWithDetails(Q.corporateCustomers().unlimited());
    }

    public ServiceContractRequest<T> groupByCorporateCustomerWithDetails(CorporateCustomerRequest subRequest){
       aggregate(ServiceContract.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }





    public ServiceContractRequest<T> groupById(){
       groupBy(ServiceContract.ID_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByIdAs(String retName){
       groupBy(retName, ServiceContract.ID_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.ID_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByContractNumber(){
       groupBy(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByContractNumberAs(String retName){
       groupBy(retName, ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByContractNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.CONTRACT_NUMBER_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByTitle(){
       groupBy(ServiceContract.TITLE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByTitleAs(String retName){
       groupBy(retName, ServiceContract.TITLE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByTitleWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.TITLE_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByStartDate(){
       groupBy(ServiceContract.START_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByStartDateAs(String retName){
       groupBy(retName, ServiceContract.START_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByStartDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.START_DATE_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByEndDate(){
       groupBy(ServiceContract.END_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByEndDateAs(String retName){
       groupBy(retName, ServiceContract.END_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByEndDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.END_DATE_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByStatus(){
       groupBy(ServiceContract.STATUS_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByStatusAs(String retName){
       groupBy(retName, ServiceContract.STATUS_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.STATUS_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByTotalValue(){
       groupBy(ServiceContract.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByTotalValueAs(String retName){
       groupBy(retName, ServiceContract.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByTotalValueWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.TOTAL_VALUE_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByCurrency(){
       groupBy(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, ServiceContract.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.CURRENCY_PROPERTY, function);
       return this;
    }
    public ServiceContractRequest<T> groupByCorporateCustomerWith(CorporateCustomerRequest subRequest){
       groupBy(ServiceContract.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public ServiceContractRequest<T> groupByCorporateCustomer(){
       groupBy(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByCorporateCustomerAs(String retName){
       groupBy(retName, ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByCorporateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.CORPORATE_CUSTOMER_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByCreatedTime(){
       groupBy(ServiceContract.CREATED_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, ServiceContract.CREATED_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByUpdateTime(){
       groupBy(ServiceContract.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, ServiceContract.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public ServiceContractRequest<T> groupByVersion(){
       groupBy(ServiceContract.VERSION_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByVersionAs(String retName){
       groupBy(retName, ServiceContract.VERSION_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ServiceContract.VERSION_PROPERTY, function);
       return this;
    }



    public ServiceContractRequest<T> orderByIdAscending(){
       addOrderByAscending(ServiceContract.ID_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByIdDescending(){
       addOrderByDescending(ServiceContract.ID_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByContractNumberAscending(){
       addOrderByAscending(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByContractNumberDescending(){
       addOrderByDescending(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByContractNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByContractNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceContract.CONTRACT_NUMBER_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByTitleAscending(){
       addOrderByAscending(ServiceContract.TITLE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByTitleDescending(){
       addOrderByDescending(ServiceContract.TITLE_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByTitleAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceContract.TITLE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByTitleDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceContract.TITLE_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByStartDateAscending(){
       addOrderByAscending(ServiceContract.START_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByStartDateDescending(){
       addOrderByDescending(ServiceContract.START_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByEndDateAscending(){
       addOrderByAscending(ServiceContract.END_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByEndDateDescending(){
       addOrderByDescending(ServiceContract.END_DATE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByStatusAscending(){
       addOrderByAscending(ServiceContract.STATUS_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByStatusDescending(){
       addOrderByDescending(ServiceContract.STATUS_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceContract.STATUS_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceContract.STATUS_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByTotalValueAscending(){
       addOrderByAscending(ServiceContract.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByTotalValueDescending(){
       addOrderByDescending(ServiceContract.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ServiceContract.CURRENCY_PROPERTY);
       return this;
    }
    public ServiceContractRequest<T> orderByCorporateCustomerAscending(){
       addOrderByAscending(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByCorporateCustomerDescending(){
       addOrderByDescending(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(ServiceContract.CREATED_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(ServiceContract.CREATED_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(ServiceContract.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(ServiceContract.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByVersionAscending(){
       addOrderByAscending(ServiceContract.VERSION_PROPERTY);
       return this;
    }

    public ServiceContractRequest<T> orderByVersionDescending(){
       addOrderByDescending(ServiceContract.VERSION_PROPERTY);
       return this;
    }


    public CorporateCustomerRequest rollUpToCorporateCustomer(){
       CorporateCustomerRequest corporateCustomer = Q.corporateCustomers().unlimited();
       this.withCorporateCustomerMatching(corporateCustomer)
           .groupByCorporateCustomerWith(corporateCustomer);
       return corporateCustomer;
    }





   public ServiceContractRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer){
       return facetByCorporateCustomerAs(facetName, corporateCustomer, true);
   }

   public ServiceContractRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer, boolean includeAllFacets){
       addFacet(facetName, ServiceContract.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public ServiceContractRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public ServiceContractRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public ServiceContractRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public ServiceContractRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public ServiceContractRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}