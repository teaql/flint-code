package com.doublechaintech.enterpriselogisticsservice.insurancepolicy;

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

public class InsurancePolicyRequest<T extends InsurancePolicy> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public InsurancePolicyRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public InsurancePolicyRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public InsurancePolicyRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public InsurancePolicyRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public InsurancePolicyRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public InsurancePolicyRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public InsurancePolicyRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (InsurancePolicyRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public InsurancePolicyRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public InsurancePolicyRequest<T> matchingAnyOf(InsurancePolicyRequest insurancePolicy){
        super.internalMatchAny(insurancePolicy);
        return this;
    }

    public InsurancePolicyRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public InsurancePolicyRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public InsurancePolicyRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public InsurancePolicyRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectPolicyNumber().selectProvider().selectCoverageAmount().selectPremium().selectStartDate().selectEndDate().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public InsurancePolicyRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public InsurancePolicyRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectPolicyNumber().selectProvider().selectCoverageAmount().selectPremium().selectStartDate().selectEndDate().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public InsurancePolicyRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectPolicyNumber().selectProvider().selectCoverageAmount().selectPremium().selectStartDate().selectEndDate().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public InsurancePolicyRequest<T> selectId(){
       selectProperty(InsurancePolicy.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectId(){
       unselectProperty(InsurancePolicy.ID_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectPolicyNumber(){
       selectProperty(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the policyNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  policyNumber) to fetch policyNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectPolicyNumber(){
       unselectProperty(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectProvider(){
       selectProperty(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }

    /**
     * fill the provider with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  provider) to fetch provider property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectProvider(){
       unselectProperty(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectCoverageAmount(){
       selectProperty(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the coverageAmount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  coverageAmount) to fetch coverageAmount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the coverageAmount with customized aggrFunction, TEAQL uses ({aggrFunction}(coverageAmount) AS coverageAmount to fetch coverageAmount property.
     * @param aggrFunction  aggrFunction
     */
    public InsurancePolicyRequest<T> selectCoverageAmount(AggrFunction aggrFunction){
       selectProperty(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public InsurancePolicyRequest<T> unselectCoverageAmount(){
       unselectProperty(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectPremium(){
       selectProperty(InsurancePolicy.PREMIUM_PROPERTY);
       return this;
    }

    /**
     * fill the premium with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  premium) to fetch premium property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the premium with customized aggrFunction, TEAQL uses ({aggrFunction}(premium) AS premium to fetch premium property.
     * @param aggrFunction  aggrFunction
     */
    public InsurancePolicyRequest<T> selectPremium(AggrFunction aggrFunction){
       selectProperty(InsurancePolicy.PREMIUM_PROPERTY, aggrFunction);
       return this;
    }


    public InsurancePolicyRequest<T> unselectPremium(){
       unselectProperty(InsurancePolicy.PREMIUM_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectStartDate(){
       selectProperty(InsurancePolicy.START_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the startDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startDate) to fetch startDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectStartDate(){
       unselectProperty(InsurancePolicy.START_DATE_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectEndDate(){
       selectProperty(InsurancePolicy.END_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the endDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endDate) to fetch endDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectEndDate(){
       unselectProperty(InsurancePolicy.END_DATE_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectStatus(){
       selectProperty(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectStatus(){
       unselectProperty(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectCreatedTime(){
       selectProperty(InsurancePolicy.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectCreatedTime(){
       unselectProperty(InsurancePolicy.CREATED_TIME_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectUpdatedTime(){
       selectProperty(InsurancePolicy.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectUpdatedTime(){
       unselectProperty(InsurancePolicy.UPDATED_TIME_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> selectVersion(){
       selectProperty(InsurancePolicy.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InsurancePolicyRequest<T> unselectVersion(){
       unselectProperty(InsurancePolicy.VERSION_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.ID_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public InsurancePolicyRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public InsurancePolicyRequest<T> filterByPolicyNumber(String... policyNumber){
      if (policyNumber == null || policyNumber.length == 0) {
        throw new IllegalArgumentException("filterByPolicyNumber parameter policyNumber cannot be empty");
      }
      return appendSearchCriteria(createPolicyNumberCriteria(Operator.EQUAL, (Object[])policyNumber));
    }

    public InsurancePolicyRequest<T> withPolicyNumber(Operator operator, Object... values){
       return appendSearchCriteria(createPolicyNumberCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withPolicyNumberIsUnknown(){
       return withPolicyNumber(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withPolicyNumberIsKnown(){
       return withPolicyNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPolicyNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.POLICY_NUMBER_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withPolicyNumberGreaterThan(String policyNumber){
       return withPolicyNumber(Operator.GREATER_THAN, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberGreaterThanOrEqualTo(String policyNumber){
       return withPolicyNumber(Operator.GREATER_THAN_OR_EQUAL, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberLessThan(String policyNumber){
       return withPolicyNumber(Operator.LESS_THAN, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberLessThanOrEqualTo(String policyNumber){
       return withPolicyNumber(Operator.LESS_THAN_OR_EQUAL, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberBetween(String startOfPolicyNumber, String endOfPolicyNumber){
       return withPolicyNumber(Operator.BETWEEN, startOfPolicyNumber, endOfPolicyNumber);
    }
    public InsurancePolicyRequest<T> withPolicyNumberStartingWith(String policyNumber){
       return withPolicyNumber(Operator.BEGIN_WITH, policyNumber);
    }
    public InsurancePolicyRequest<T> withPolicyNumberContaining(String policyNumber){
       return withPolicyNumber(Operator.CONTAIN, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberEndingWith(String policyNumber){
       return withPolicyNumber(Operator.END_WITH, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberIs(String policyNumber){
       return withPolicyNumber(Operator.EQUAL, policyNumber);
    }

    public InsurancePolicyRequest<T> withPolicyNumberSoundingLike(String policyNumber){
       return withPolicyNumber(Operator.SOUNDS_LIKE, policyNumber);
    }



    public InsurancePolicyRequest<T> filterByProvider(String... provider){
      if (provider == null || provider.length == 0) {
        throw new IllegalArgumentException("filterByProvider parameter provider cannot be empty");
      }
      return appendSearchCriteria(createProviderCriteria(Operator.EQUAL, (Object[])provider));
    }

    public InsurancePolicyRequest<T> withProvider(Operator operator, Object... values){
       return appendSearchCriteria(createProviderCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withProviderIsUnknown(){
       return withProvider(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withProviderIsKnown(){
       return withProvider(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createProviderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.PROVIDER_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withProviderGreaterThan(String provider){
       return withProvider(Operator.GREATER_THAN, provider);
    }

    public InsurancePolicyRequest<T> withProviderGreaterThanOrEqualTo(String provider){
       return withProvider(Operator.GREATER_THAN_OR_EQUAL, provider);
    }

    public InsurancePolicyRequest<T> withProviderLessThan(String provider){
       return withProvider(Operator.LESS_THAN, provider);
    }

    public InsurancePolicyRequest<T> withProviderLessThanOrEqualTo(String provider){
       return withProvider(Operator.LESS_THAN_OR_EQUAL, provider);
    }

    public InsurancePolicyRequest<T> withProviderBetween(String startOfProvider, String endOfProvider){
       return withProvider(Operator.BETWEEN, startOfProvider, endOfProvider);
    }
    public InsurancePolicyRequest<T> withProviderStartingWith(String provider){
       return withProvider(Operator.BEGIN_WITH, provider);
    }
    public InsurancePolicyRequest<T> withProviderContaining(String provider){
       return withProvider(Operator.CONTAIN, provider);
    }

    public InsurancePolicyRequest<T> withProviderEndingWith(String provider){
       return withProvider(Operator.END_WITH, provider);
    }

    public InsurancePolicyRequest<T> withProviderIs(String provider){
       return withProvider(Operator.EQUAL, provider);
    }

    public InsurancePolicyRequest<T> withProviderSoundingLike(String provider){
       return withProvider(Operator.SOUNDS_LIKE, provider);
    }



    public InsurancePolicyRequest<T> filterByCoverageAmount(BigDecimal... coverageAmount){
      if (coverageAmount == null || coverageAmount.length == 0) {
        throw new IllegalArgumentException("filterByCoverageAmount parameter coverageAmount cannot be empty");
      }
      return appendSearchCriteria(createCoverageAmountCriteria(Operator.EQUAL, (Object[])coverageAmount));
    }

    public InsurancePolicyRequest<T> withCoverageAmount(Operator operator, Object... values){
       return appendSearchCriteria(createCoverageAmountCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withCoverageAmountIsUnknown(){
       return withCoverageAmount(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withCoverageAmountIsKnown(){
       return withCoverageAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCoverageAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withCoverageAmountGreaterThan(BigDecimal coverageAmount){
       return withCoverageAmount(Operator.GREATER_THAN, coverageAmount);
    }

    public InsurancePolicyRequest<T> withCoverageAmountGreaterThanOrEqualTo(BigDecimal coverageAmount){
       return withCoverageAmount(Operator.GREATER_THAN_OR_EQUAL, coverageAmount);
    }

    public InsurancePolicyRequest<T> withCoverageAmountLessThan(BigDecimal coverageAmount){
       return withCoverageAmount(Operator.LESS_THAN, coverageAmount);
    }

    public InsurancePolicyRequest<T> withCoverageAmountLessThanOrEqualTo(BigDecimal coverageAmount){
       return withCoverageAmount(Operator.LESS_THAN_OR_EQUAL, coverageAmount);
    }

    public InsurancePolicyRequest<T> withCoverageAmountBetween(BigDecimal startOfCoverageAmount, BigDecimal endOfCoverageAmount){
       return withCoverageAmount(Operator.BETWEEN, startOfCoverageAmount, endOfCoverageAmount);
    }



    public InsurancePolicyRequest<T> filterByPremium(BigDecimal... premium){
      if (premium == null || premium.length == 0) {
        throw new IllegalArgumentException("filterByPremium parameter premium cannot be empty");
      }
      return appendSearchCriteria(createPremiumCriteria(Operator.EQUAL, (Object[])premium));
    }

    public InsurancePolicyRequest<T> withPremium(Operator operator, Object... values){
       return appendSearchCriteria(createPremiumCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withPremiumIsUnknown(){
       return withPremium(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withPremiumIsKnown(){
       return withPremium(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPremiumCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.PREMIUM_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withPremiumGreaterThan(BigDecimal premium){
       return withPremium(Operator.GREATER_THAN, premium);
    }

    public InsurancePolicyRequest<T> withPremiumGreaterThanOrEqualTo(BigDecimal premium){
       return withPremium(Operator.GREATER_THAN_OR_EQUAL, premium);
    }

    public InsurancePolicyRequest<T> withPremiumLessThan(BigDecimal premium){
       return withPremium(Operator.LESS_THAN, premium);
    }

    public InsurancePolicyRequest<T> withPremiumLessThanOrEqualTo(BigDecimal premium){
       return withPremium(Operator.LESS_THAN_OR_EQUAL, premium);
    }

    public InsurancePolicyRequest<T> withPremiumBetween(BigDecimal startOfPremium, BigDecimal endOfPremium){
       return withPremium(Operator.BETWEEN, startOfPremium, endOfPremium);
    }



    public InsurancePolicyRequest<T> filterByStartDate(LocalDate... startDate){
      if (startDate == null || startDate.length == 0) {
        throw new IllegalArgumentException("filterByStartDate parameter startDate cannot be empty");
      }
      return appendSearchCriteria(createStartDateCriteria(Operator.EQUAL, (Object[])startDate));
    }

    public InsurancePolicyRequest<T> withStartDate(Operator operator, Object... values){
       return appendSearchCriteria(createStartDateCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withStartDateIsUnknown(){
       return withStartDate(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withStartDateIsKnown(){
       return withStartDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.START_DATE_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withStartDateGreaterThan(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateGreaterThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN_OR_EQUAL, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateLessThan(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateLessThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN_OR_EQUAL, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateBetween(LocalDate startOfStartDate, LocalDate endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }
    public InsurancePolicyRequest<T> withStartDateBefore(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateBefore(Date startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateAfter(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateAfter(Date startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public InsurancePolicyRequest<T> withStartDateBetween(Date startOfStartDate, Date endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }




    public InsurancePolicyRequest<T> filterByEndDate(LocalDate... endDate){
      if (endDate == null || endDate.length == 0) {
        throw new IllegalArgumentException("filterByEndDate parameter endDate cannot be empty");
      }
      return appendSearchCriteria(createEndDateCriteria(Operator.EQUAL, (Object[])endDate));
    }

    public InsurancePolicyRequest<T> withEndDate(Operator operator, Object... values){
       return appendSearchCriteria(createEndDateCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withEndDateIsUnknown(){
       return withEndDate(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withEndDateIsKnown(){
       return withEndDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.END_DATE_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withEndDateGreaterThan(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateGreaterThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN_OR_EQUAL, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateLessThan(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateLessThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN_OR_EQUAL, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateBetween(LocalDate startOfEndDate, LocalDate endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }
    public InsurancePolicyRequest<T> withEndDateBefore(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateBefore(Date endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateAfter(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateAfter(Date endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public InsurancePolicyRequest<T> withEndDateBetween(Date startOfEndDate, Date endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }




    public InsurancePolicyRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public InsurancePolicyRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.STATUS_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public InsurancePolicyRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public InsurancePolicyRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public InsurancePolicyRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public InsurancePolicyRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public InsurancePolicyRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public InsurancePolicyRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public InsurancePolicyRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public InsurancePolicyRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public InsurancePolicyRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public InsurancePolicyRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public InsurancePolicyRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.CREATED_TIME_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public InsurancePolicyRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public InsurancePolicyRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public InsurancePolicyRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public InsurancePolicyRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.UPDATED_TIME_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public InsurancePolicyRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public InsurancePolicyRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public InsurancePolicyRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public InsurancePolicyRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public InsurancePolicyRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public InsurancePolicyRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InsurancePolicy.VERSION_PROPERTY, operator, values);
    }

    public InsurancePolicyRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public InsurancePolicyRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public InsurancePolicyRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public InsurancePolicyRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public InsurancePolicyRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public InsurancePolicyRequest<T> count(){
        super.count();
        return this;
    }
    public InsurancePolicyRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public InsurancePolicyRequest minCoverageAmount(){
        return minCoverageAmountAs(prefix("minOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest minCoverageAmountAs(String retName){
        super.min(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest maxCoverageAmount(){
        return maxCoverageAmountAs(prefix("maxOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest maxCoverageAmountAs(String retName){
        super.max(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest sumCoverageAmount(){
        return sumCoverageAmountAs(prefix("sumOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest sumCoverageAmountAs(String retName){
        super.sum(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest avgCoverageAmount(){
        return avgCoverageAmountAs(prefix("avgOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest avgCoverageAmountAs(String retName){
        super.avg(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest standardDeviationCoverageAmount(){
        return standardDeviationCoverageAmountAs(prefix("standardDeviationOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest standardDeviationCoverageAmountAs(String retName){
        super.standardDeviation(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest squareRootOfPopulationStandardDeviationCoverageAmount(){
        return squareRootOfPopulationStandardDeviationCoverageAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest squareRootOfPopulationStandardDeviationCoverageAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest sampleVarianceCoverageAmount(){
        return sampleVarianceCoverageAmountAs(prefix("sampleVarianceOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest sampleVarianceCoverageAmountAs(String retName){
        super.sampleVariance(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest samplePopulationVarianceCoverageAmount(){
        return samplePopulationVarianceCoverageAmountAs(prefix("samplePopulationVarianceOf",InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
    }

    public InsurancePolicyRequest samplePopulationVarianceCoverageAmountAs(String retName){
        super.samplePopulationVariance(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest minPremium(){
        return minPremiumAs(prefix("minOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest minPremiumAs(String retName){
        super.min(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest maxPremium(){
        return maxPremiumAs(prefix("maxOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest maxPremiumAs(String retName){
        super.max(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest sumPremium(){
        return sumPremiumAs(prefix("sumOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest sumPremiumAs(String retName){
        super.sum(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest avgPremium(){
        return avgPremiumAs(prefix("avgOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest avgPremiumAs(String retName){
        super.avg(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest standardDeviationPremium(){
        return standardDeviationPremiumAs(prefix("standardDeviationOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest standardDeviationPremiumAs(String retName){
        super.standardDeviation(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest squareRootOfPopulationStandardDeviationPremium(){
        return squareRootOfPopulationStandardDeviationPremiumAs(prefix("squareRootOfPopulationStandardDeviationOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest squareRootOfPopulationStandardDeviationPremiumAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest sampleVariancePremium(){
        return sampleVariancePremiumAs(prefix("sampleVarianceOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest sampleVariancePremiumAs(String retName){
        super.sampleVariance(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }
    public InsurancePolicyRequest samplePopulationVariancePremium(){
        return samplePopulationVariancePremiumAs(prefix("samplePopulationVarianceOf",InsurancePolicy.PREMIUM_PROPERTY));
    }

    public InsurancePolicyRequest samplePopulationVariancePremiumAs(String retName){
        super.samplePopulationVariance(retName, InsurancePolicy.PREMIUM_PROPERTY);
        return this;
    }

    public InsurancePolicyRequest<T> groupById(){
       groupBy(InsurancePolicy.ID_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByIdAs(String retName){
       groupBy(retName, InsurancePolicy.ID_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.ID_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByPolicyNumber(){
       groupBy(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByPolicyNumberAs(String retName){
       groupBy(retName, InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByPolicyNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.POLICY_NUMBER_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByProvider(){
       groupBy(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByProviderAs(String retName){
       groupBy(retName, InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByProviderWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.PROVIDER_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByCoverageAmount(){
       groupBy(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByCoverageAmountAs(String retName){
       groupBy(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByCoverageAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByPremium(){
       groupBy(InsurancePolicy.PREMIUM_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByPremiumAs(String retName){
       groupBy(retName, InsurancePolicy.PREMIUM_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByPremiumWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.PREMIUM_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByStartDate(){
       groupBy(InsurancePolicy.START_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByStartDateAs(String retName){
       groupBy(retName, InsurancePolicy.START_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByStartDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.START_DATE_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByEndDate(){
       groupBy(InsurancePolicy.END_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByEndDateAs(String retName){
       groupBy(retName, InsurancePolicy.END_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByEndDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.END_DATE_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByStatus(){
       groupBy(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByStatusAs(String retName){
       groupBy(retName, InsurancePolicy.STATUS_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.STATUS_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByCreatedTime(){
       groupBy(InsurancePolicy.CREATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, InsurancePolicy.CREATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByUpdatedTime(){
       groupBy(InsurancePolicy.UPDATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, InsurancePolicy.UPDATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public InsurancePolicyRequest<T> groupByVersion(){
       groupBy(InsurancePolicy.VERSION_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByVersionAs(String retName){
       groupBy(retName, InsurancePolicy.VERSION_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, InsurancePolicy.VERSION_PROPERTY, function);
       return this;
    }



    public InsurancePolicyRequest<T> orderByIdAscending(){
       addOrderByAscending(InsurancePolicy.ID_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByIdDescending(){
       addOrderByDescending(InsurancePolicy.ID_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByPolicyNumberAscending(){
       addOrderByAscending(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByPolicyNumberDescending(){
       addOrderByDescending(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> orderByPolicyNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByPolicyNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(InsurancePolicy.POLICY_NUMBER_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> orderByProviderAscending(){
       addOrderByAscending(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByProviderDescending(){
       addOrderByDescending(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> orderByProviderAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByProviderDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(InsurancePolicy.PROVIDER_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> orderByCoverageAmountAscending(){
       addOrderByAscending(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByCoverageAmountDescending(){
       addOrderByDescending(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByPremiumAscending(){
       addOrderByAscending(InsurancePolicy.PREMIUM_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByPremiumDescending(){
       addOrderByDescending(InsurancePolicy.PREMIUM_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByStartDateAscending(){
       addOrderByAscending(InsurancePolicy.START_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByStartDateDescending(){
       addOrderByDescending(InsurancePolicy.START_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByEndDateAscending(){
       addOrderByAscending(InsurancePolicy.END_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByEndDateDescending(){
       addOrderByDescending(InsurancePolicy.END_DATE_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByStatusAscending(){
       addOrderByAscending(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByStatusDescending(){
       addOrderByDescending(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(InsurancePolicy.STATUS_PROPERTY);
       return this;
    }
    public InsurancePolicyRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(InsurancePolicy.CREATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(InsurancePolicy.CREATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(InsurancePolicy.UPDATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(InsurancePolicy.UPDATED_TIME_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByVersionAscending(){
       addOrderByAscending(InsurancePolicy.VERSION_PROPERTY);
       return this;
    }

    public InsurancePolicyRequest<T> orderByVersionDescending(){
       addOrderByDescending(InsurancePolicy.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public InsurancePolicyRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public InsurancePolicyRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public InsurancePolicyRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public InsurancePolicyRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public InsurancePolicyRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}