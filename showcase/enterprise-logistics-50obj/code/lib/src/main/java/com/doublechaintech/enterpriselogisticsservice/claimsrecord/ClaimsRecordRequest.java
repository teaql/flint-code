package com.doublechaintech.enterpriselogisticsservice.claimsrecord;

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

public class ClaimsRecordRequest<T extends ClaimsRecord> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public ClaimsRecordRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public ClaimsRecordRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public ClaimsRecordRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public ClaimsRecordRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public ClaimsRecordRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public ClaimsRecordRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public ClaimsRecordRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (ClaimsRecordRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public ClaimsRecordRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public ClaimsRecordRequest<T> matchingAnyOf(ClaimsRecordRequest claimsRecord){
        super.internalMatchAny(claimsRecord);
        return this;
    }

    public ClaimsRecordRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public ClaimsRecordRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public ClaimsRecordRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public ClaimsRecordRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectClaimNumber().selectClaimAmount().selectStatus().selectDescription().selectResolutionDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public ClaimsRecordRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ClaimsRecordRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectClaimNumber().selectClaimAmount().selectStatus().selectDescription().selectResolutionDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public ClaimsRecordRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectClaimNumber().selectClaimAmount().selectStatus().selectDescription().selectResolutionDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public ClaimsRecordRequest<T> selectId(){
       selectProperty(ClaimsRecord.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectId(){
       unselectProperty(ClaimsRecord.ID_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectClaimNumber(){
       selectProperty(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the claimNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  claimNumber) to fetch claimNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectClaimNumber(){
       unselectProperty(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectClaimAmount(){
       selectProperty(ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the claimAmount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  claimAmount) to fetch claimAmount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the claimAmount with customized aggrFunction, TEAQL uses ({aggrFunction}(claimAmount) AS claimAmount to fetch claimAmount property.
     * @param aggrFunction  aggrFunction
     */
    public ClaimsRecordRequest<T> selectClaimAmount(AggrFunction aggrFunction){
       selectProperty(ClaimsRecord.CLAIM_AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public ClaimsRecordRequest<T> unselectClaimAmount(){
       unselectProperty(ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectStatus(){
       selectProperty(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectStatus(){
       unselectProperty(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectDescription(){
       selectProperty(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectDescription(){
       unselectProperty(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectResolutionDate(){
       selectProperty(ClaimsRecord.RESOLUTION_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the resolutionDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  resolutionDate) to fetch resolutionDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectResolutionDate(){
       unselectProperty(ClaimsRecord.RESOLUTION_DATE_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectCreatedTime(){
       selectProperty(ClaimsRecord.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectCreatedTime(){
       unselectProperty(ClaimsRecord.CREATED_TIME_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectUpdatedTime(){
       selectProperty(ClaimsRecord.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectUpdatedTime(){
       unselectProperty(ClaimsRecord.UPDATED_TIME_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> selectVersion(){
       selectProperty(ClaimsRecord.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ClaimsRecordRequest<T> unselectVersion(){
       unselectProperty(ClaimsRecord.VERSION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.ID_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public ClaimsRecordRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public ClaimsRecordRequest<T> filterByClaimNumber(String... claimNumber){
      if (claimNumber == null || claimNumber.length == 0) {
        throw new IllegalArgumentException("filterByClaimNumber parameter claimNumber cannot be empty");
      }
      return appendSearchCriteria(createClaimNumberCriteria(Operator.EQUAL, (Object[])claimNumber));
    }

    public ClaimsRecordRequest<T> withClaimNumber(Operator operator, Object... values){
       return appendSearchCriteria(createClaimNumberCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withClaimNumberIsUnknown(){
       return withClaimNumber(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withClaimNumberIsKnown(){
       return withClaimNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createClaimNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.CLAIM_NUMBER_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withClaimNumberGreaterThan(String claimNumber){
       return withClaimNumber(Operator.GREATER_THAN, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberGreaterThanOrEqualTo(String claimNumber){
       return withClaimNumber(Operator.GREATER_THAN_OR_EQUAL, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberLessThan(String claimNumber){
       return withClaimNumber(Operator.LESS_THAN, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberLessThanOrEqualTo(String claimNumber){
       return withClaimNumber(Operator.LESS_THAN_OR_EQUAL, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberBetween(String startOfClaimNumber, String endOfClaimNumber){
       return withClaimNumber(Operator.BETWEEN, startOfClaimNumber, endOfClaimNumber);
    }
    public ClaimsRecordRequest<T> withClaimNumberStartingWith(String claimNumber){
       return withClaimNumber(Operator.BEGIN_WITH, claimNumber);
    }
    public ClaimsRecordRequest<T> withClaimNumberContaining(String claimNumber){
       return withClaimNumber(Operator.CONTAIN, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberEndingWith(String claimNumber){
       return withClaimNumber(Operator.END_WITH, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberIs(String claimNumber){
       return withClaimNumber(Operator.EQUAL, claimNumber);
    }

    public ClaimsRecordRequest<T> withClaimNumberSoundingLike(String claimNumber){
       return withClaimNumber(Operator.SOUNDS_LIKE, claimNumber);
    }



    public ClaimsRecordRequest<T> filterByClaimAmount(BigDecimal... claimAmount){
      if (claimAmount == null || claimAmount.length == 0) {
        throw new IllegalArgumentException("filterByClaimAmount parameter claimAmount cannot be empty");
      }
      return appendSearchCriteria(createClaimAmountCriteria(Operator.EQUAL, (Object[])claimAmount));
    }

    public ClaimsRecordRequest<T> withClaimAmount(Operator operator, Object... values){
       return appendSearchCriteria(createClaimAmountCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withClaimAmountIsUnknown(){
       return withClaimAmount(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withClaimAmountIsKnown(){
       return withClaimAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createClaimAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.CLAIM_AMOUNT_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withClaimAmountGreaterThan(BigDecimal claimAmount){
       return withClaimAmount(Operator.GREATER_THAN, claimAmount);
    }

    public ClaimsRecordRequest<T> withClaimAmountGreaterThanOrEqualTo(BigDecimal claimAmount){
       return withClaimAmount(Operator.GREATER_THAN_OR_EQUAL, claimAmount);
    }

    public ClaimsRecordRequest<T> withClaimAmountLessThan(BigDecimal claimAmount){
       return withClaimAmount(Operator.LESS_THAN, claimAmount);
    }

    public ClaimsRecordRequest<T> withClaimAmountLessThanOrEqualTo(BigDecimal claimAmount){
       return withClaimAmount(Operator.LESS_THAN_OR_EQUAL, claimAmount);
    }

    public ClaimsRecordRequest<T> withClaimAmountBetween(BigDecimal startOfClaimAmount, BigDecimal endOfClaimAmount){
       return withClaimAmount(Operator.BETWEEN, startOfClaimAmount, endOfClaimAmount);
    }



    public ClaimsRecordRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public ClaimsRecordRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.STATUS_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public ClaimsRecordRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public ClaimsRecordRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public ClaimsRecordRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public ClaimsRecordRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public ClaimsRecordRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public ClaimsRecordRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public ClaimsRecordRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public ClaimsRecordRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public ClaimsRecordRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public ClaimsRecordRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public ClaimsRecordRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.DESCRIPTION_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public ClaimsRecordRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public ClaimsRecordRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public ClaimsRecordRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public ClaimsRecordRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public ClaimsRecordRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public ClaimsRecordRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public ClaimsRecordRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public ClaimsRecordRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public ClaimsRecordRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public ClaimsRecordRequest<T> filterByResolutionDate(LocalDate... resolutionDate){
      if (resolutionDate == null || resolutionDate.length == 0) {
        throw new IllegalArgumentException("filterByResolutionDate parameter resolutionDate cannot be empty");
      }
      return appendSearchCriteria(createResolutionDateCriteria(Operator.EQUAL, (Object[])resolutionDate));
    }

    public ClaimsRecordRequest<T> withResolutionDate(Operator operator, Object... values){
       return appendSearchCriteria(createResolutionDateCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withResolutionDateIsUnknown(){
       return withResolutionDate(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withResolutionDateIsKnown(){
       return withResolutionDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createResolutionDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.RESOLUTION_DATE_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withResolutionDateGreaterThan(LocalDate resolutionDate){
       return withResolutionDate(Operator.GREATER_THAN, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateGreaterThanOrEqualTo(LocalDate resolutionDate){
       return withResolutionDate(Operator.GREATER_THAN_OR_EQUAL, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateLessThan(LocalDate resolutionDate){
       return withResolutionDate(Operator.LESS_THAN, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateLessThanOrEqualTo(LocalDate resolutionDate){
       return withResolutionDate(Operator.LESS_THAN_OR_EQUAL, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateBetween(LocalDate startOfResolutionDate, LocalDate endOfResolutionDate){
       return withResolutionDate(Operator.BETWEEN, startOfResolutionDate, endOfResolutionDate);
    }
    public ClaimsRecordRequest<T> withResolutionDateBefore(LocalDate resolutionDate){
       return withResolutionDate(Operator.LESS_THAN, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateBefore(Date resolutionDate){
       return withResolutionDate(Operator.LESS_THAN, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateAfter(LocalDate resolutionDate){
       return withResolutionDate(Operator.GREATER_THAN, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateAfter(Date resolutionDate){
       return withResolutionDate(Operator.GREATER_THAN, resolutionDate);
    }

    public ClaimsRecordRequest<T> withResolutionDateBetween(Date startOfResolutionDate, Date endOfResolutionDate){
       return withResolutionDate(Operator.BETWEEN, startOfResolutionDate, endOfResolutionDate);
    }




    public ClaimsRecordRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public ClaimsRecordRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.CREATED_TIME_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public ClaimsRecordRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public ClaimsRecordRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public ClaimsRecordRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public ClaimsRecordRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.UPDATED_TIME_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public ClaimsRecordRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public ClaimsRecordRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public ClaimsRecordRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public ClaimsRecordRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public ClaimsRecordRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public ClaimsRecordRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ClaimsRecord.VERSION_PROPERTY, operator, values);
    }

    public ClaimsRecordRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public ClaimsRecordRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public ClaimsRecordRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public ClaimsRecordRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public ClaimsRecordRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public ClaimsRecordRequest<T> count(){
        super.count();
        return this;
    }
    public ClaimsRecordRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public ClaimsRecordRequest minClaimAmount(){
        return minClaimAmountAs(prefix("minOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest minClaimAmountAs(String retName){
        super.min(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest maxClaimAmount(){
        return maxClaimAmountAs(prefix("maxOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest maxClaimAmountAs(String retName){
        super.max(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest sumClaimAmount(){
        return sumClaimAmountAs(prefix("sumOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest sumClaimAmountAs(String retName){
        super.sum(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest avgClaimAmount(){
        return avgClaimAmountAs(prefix("avgOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest avgClaimAmountAs(String retName){
        super.avg(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest standardDeviationClaimAmount(){
        return standardDeviationClaimAmountAs(prefix("standardDeviationOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest standardDeviationClaimAmountAs(String retName){
        super.standardDeviation(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest squareRootOfPopulationStandardDeviationClaimAmount(){
        return squareRootOfPopulationStandardDeviationClaimAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest squareRootOfPopulationStandardDeviationClaimAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest sampleVarianceClaimAmount(){
        return sampleVarianceClaimAmountAs(prefix("sampleVarianceOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest sampleVarianceClaimAmountAs(String retName){
        super.sampleVariance(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }
    public ClaimsRecordRequest samplePopulationVarianceClaimAmount(){
        return samplePopulationVarianceClaimAmountAs(prefix("samplePopulationVarianceOf",ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
    }

    public ClaimsRecordRequest samplePopulationVarianceClaimAmountAs(String retName){
        super.samplePopulationVariance(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
        return this;
    }

    public ClaimsRecordRequest<T> groupById(){
       groupBy(ClaimsRecord.ID_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByIdAs(String retName){
       groupBy(retName, ClaimsRecord.ID_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.ID_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByClaimNumber(){
       groupBy(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByClaimNumberAs(String retName){
       groupBy(retName, ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByClaimNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.CLAIM_NUMBER_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByClaimAmount(){
       groupBy(ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByClaimAmountAs(String retName){
       groupBy(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByClaimAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.CLAIM_AMOUNT_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByStatus(){
       groupBy(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByStatusAs(String retName){
       groupBy(retName, ClaimsRecord.STATUS_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.STATUS_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByDescription(){
       groupBy(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByResolutionDate(){
       groupBy(ClaimsRecord.RESOLUTION_DATE_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByResolutionDateAs(String retName){
       groupBy(retName, ClaimsRecord.RESOLUTION_DATE_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByResolutionDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.RESOLUTION_DATE_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByCreatedTime(){
       groupBy(ClaimsRecord.CREATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, ClaimsRecord.CREATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByUpdatedTime(){
       groupBy(ClaimsRecord.UPDATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, ClaimsRecord.UPDATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public ClaimsRecordRequest<T> groupByVersion(){
       groupBy(ClaimsRecord.VERSION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByVersionAs(String retName){
       groupBy(retName, ClaimsRecord.VERSION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ClaimsRecord.VERSION_PROPERTY, function);
       return this;
    }



    public ClaimsRecordRequest<T> orderByIdAscending(){
       addOrderByAscending(ClaimsRecord.ID_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByIdDescending(){
       addOrderByDescending(ClaimsRecord.ID_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByClaimNumberAscending(){
       addOrderByAscending(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByClaimNumberDescending(){
       addOrderByDescending(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> orderByClaimNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByClaimNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ClaimsRecord.CLAIM_NUMBER_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> orderByClaimAmountAscending(){
       addOrderByAscending(ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByClaimAmountDescending(){
       addOrderByDescending(ClaimsRecord.CLAIM_AMOUNT_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByStatusAscending(){
       addOrderByAscending(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByStatusDescending(){
       addOrderByDescending(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ClaimsRecord.STATUS_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ClaimsRecord.DESCRIPTION_PROPERTY);
       return this;
    }
    public ClaimsRecordRequest<T> orderByResolutionDateAscending(){
       addOrderByAscending(ClaimsRecord.RESOLUTION_DATE_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByResolutionDateDescending(){
       addOrderByDescending(ClaimsRecord.RESOLUTION_DATE_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(ClaimsRecord.CREATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(ClaimsRecord.CREATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(ClaimsRecord.UPDATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(ClaimsRecord.UPDATED_TIME_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByVersionAscending(){
       addOrderByAscending(ClaimsRecord.VERSION_PROPERTY);
       return this;
    }

    public ClaimsRecordRequest<T> orderByVersionDescending(){
       addOrderByDescending(ClaimsRecord.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public ClaimsRecordRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public ClaimsRecordRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public ClaimsRecordRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public ClaimsRecordRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public ClaimsRecordRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}