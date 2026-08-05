package com.doublechaintech.enterpriselogisticsservice.timeslot;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class TimeSlotRequest<T extends TimeSlot> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public TimeSlotRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public TimeSlotRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public TimeSlotRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public TimeSlotRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public TimeSlotRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public TimeSlotRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public TimeSlotRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (TimeSlotRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public TimeSlotRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public TimeSlotRequest<T> matchingAnyOf(TimeSlotRequest timeSlot){
        super.internalMatchAny(timeSlot);
        return this;
    }

    public TimeSlotRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public TimeSlotRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public TimeSlotRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public TimeSlotRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectSlotCode().selectStartTime().selectEndTime().selectCapacity().selectAvailableSpots().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public TimeSlotRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TimeSlotRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectSlotCode().selectStartTime().selectEndTime().selectCapacity().selectAvailableSpots().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public TimeSlotRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectSlotCode().selectStartTime().selectEndTime().selectCapacity().selectAvailableSpots().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public TimeSlotRequest<T> selectId(){
       selectProperty(TimeSlot.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectId(){
       unselectProperty(TimeSlot.ID_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectSlotCode(){
       selectProperty(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the slotCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  slotCode) to fetch slotCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectSlotCode(){
       unselectProperty(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectStartTime(){
       selectProperty(TimeSlot.START_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the startTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startTime) to fetch startTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectStartTime(){
       unselectProperty(TimeSlot.START_TIME_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectEndTime(){
       selectProperty(TimeSlot.END_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the endTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endTime) to fetch endTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectEndTime(){
       unselectProperty(TimeSlot.END_TIME_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectCapacity(){
       selectProperty(TimeSlot.CAPACITY_PROPERTY);
       return this;
    }

    /**
     * fill the capacity with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  capacity) to fetch capacity property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the capacity with customized aggrFunction, TEAQL uses ({aggrFunction}(capacity) AS capacity to fetch capacity property.
     * @param aggrFunction  aggrFunction
     */
    public TimeSlotRequest<T> selectCapacity(AggrFunction aggrFunction){
       selectProperty(TimeSlot.CAPACITY_PROPERTY, aggrFunction);
       return this;
    }


    public TimeSlotRequest<T> unselectCapacity(){
       unselectProperty(TimeSlot.CAPACITY_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectAvailableSpots(){
       selectProperty(TimeSlot.AVAILABLE_SPOTS_PROPERTY);
       return this;
    }

    /**
     * fill the availableSpots with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  availableSpots) to fetch availableSpots property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the availableSpots with customized aggrFunction, TEAQL uses ({aggrFunction}(availableSpots) AS availableSpots to fetch availableSpots property.
     * @param aggrFunction  aggrFunction
     */
    public TimeSlotRequest<T> selectAvailableSpots(AggrFunction aggrFunction){
       selectProperty(TimeSlot.AVAILABLE_SPOTS_PROPERTY, aggrFunction);
       return this;
    }


    public TimeSlotRequest<T> unselectAvailableSpots(){
       unselectProperty(TimeSlot.AVAILABLE_SPOTS_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectCreatedTime(){
       selectProperty(TimeSlot.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectCreatedTime(){
       unselectProperty(TimeSlot.CREATED_TIME_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectUpdatedTime(){
       selectProperty(TimeSlot.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectUpdatedTime(){
       unselectProperty(TimeSlot.UPDATED_TIME_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectVersion(){
       selectProperty(TimeSlot.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectVersion(){
       unselectProperty(TimeSlot.VERSION_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.ID_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public TimeSlotRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public TimeSlotRequest<T> filterBySlotCode(String... slotCode){
      if (slotCode == null || slotCode.length == 0) {
        throw new IllegalArgumentException("filterBySlotCode parameter slotCode cannot be empty");
      }
      return appendSearchCriteria(createSlotCodeCriteria(Operator.EQUAL, (Object[])slotCode));
    }

    public TimeSlotRequest<T> withSlotCode(Operator operator, Object... values){
       return appendSearchCriteria(createSlotCodeCriteria(operator, values));
    }

    public TimeSlotRequest<T> withSlotCodeIsUnknown(){
       return withSlotCode(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withSlotCodeIsKnown(){
       return withSlotCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createSlotCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.SLOT_CODE_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withSlotCodeGreaterThan(String slotCode){
       return withSlotCode(Operator.GREATER_THAN, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeGreaterThanOrEqualTo(String slotCode){
       return withSlotCode(Operator.GREATER_THAN_OR_EQUAL, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeLessThan(String slotCode){
       return withSlotCode(Operator.LESS_THAN, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeLessThanOrEqualTo(String slotCode){
       return withSlotCode(Operator.LESS_THAN_OR_EQUAL, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeBetween(String startOfSlotCode, String endOfSlotCode){
       return withSlotCode(Operator.BETWEEN, startOfSlotCode, endOfSlotCode);
    }
    public TimeSlotRequest<T> withSlotCodeStartingWith(String slotCode){
       return withSlotCode(Operator.BEGIN_WITH, slotCode);
    }
    public TimeSlotRequest<T> withSlotCodeContaining(String slotCode){
       return withSlotCode(Operator.CONTAIN, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeEndingWith(String slotCode){
       return withSlotCode(Operator.END_WITH, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeIs(String slotCode){
       return withSlotCode(Operator.EQUAL, slotCode);
    }

    public TimeSlotRequest<T> withSlotCodeSoundingLike(String slotCode){
       return withSlotCode(Operator.SOUNDS_LIKE, slotCode);
    }



    public TimeSlotRequest<T> filterByStartTime(LocalDateTime... startTime){
      if (startTime == null || startTime.length == 0) {
        throw new IllegalArgumentException("filterByStartTime parameter startTime cannot be empty");
      }
      return appendSearchCriteria(createStartTimeCriteria(Operator.EQUAL, (Object[])startTime));
    }

    public TimeSlotRequest<T> withStartTime(Operator operator, Object... values){
       return appendSearchCriteria(createStartTimeCriteria(operator, values));
    }

    public TimeSlotRequest<T> withStartTimeIsUnknown(){
       return withStartTime(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withStartTimeIsKnown(){
       return withStartTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.START_TIME_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withStartTimeGreaterThan(LocalDateTime startTime){
       return withStartTime(Operator.GREATER_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeGreaterThanOrEqualTo(LocalDateTime startTime){
       return withStartTime(Operator.GREATER_THAN_OR_EQUAL, startTime);
    }

    public TimeSlotRequest<T> withStartTimeLessThan(LocalDateTime startTime){
       return withStartTime(Operator.LESS_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeLessThanOrEqualTo(LocalDateTime startTime){
       return withStartTime(Operator.LESS_THAN_OR_EQUAL, startTime);
    }

    public TimeSlotRequest<T> withStartTimeBetween(LocalDateTime startOfStartTime, LocalDateTime endOfStartTime){
       return withStartTime(Operator.BETWEEN, startOfStartTime, endOfStartTime);
    }
    public TimeSlotRequest<T> withStartTimeBefore(LocalDateTime startTime){
       return withStartTime(Operator.LESS_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeBefore(Date startTime){
       return withStartTime(Operator.LESS_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeAfter(LocalDateTime startTime){
       return withStartTime(Operator.GREATER_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeAfter(Date startTime){
       return withStartTime(Operator.GREATER_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeBetween(Date startOfStartTime, Date endOfStartTime){
       return withStartTime(Operator.BETWEEN, startOfStartTime, endOfStartTime);
    }




    public TimeSlotRequest<T> filterByEndTime(LocalDateTime... endTime){
      if (endTime == null || endTime.length == 0) {
        throw new IllegalArgumentException("filterByEndTime parameter endTime cannot be empty");
      }
      return appendSearchCriteria(createEndTimeCriteria(Operator.EQUAL, (Object[])endTime));
    }

    public TimeSlotRequest<T> withEndTime(Operator operator, Object... values){
       return appendSearchCriteria(createEndTimeCriteria(operator, values));
    }

    public TimeSlotRequest<T> withEndTimeIsUnknown(){
       return withEndTime(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withEndTimeIsKnown(){
       return withEndTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.END_TIME_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withEndTimeGreaterThan(LocalDateTime endTime){
       return withEndTime(Operator.GREATER_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeGreaterThanOrEqualTo(LocalDateTime endTime){
       return withEndTime(Operator.GREATER_THAN_OR_EQUAL, endTime);
    }

    public TimeSlotRequest<T> withEndTimeLessThan(LocalDateTime endTime){
       return withEndTime(Operator.LESS_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeLessThanOrEqualTo(LocalDateTime endTime){
       return withEndTime(Operator.LESS_THAN_OR_EQUAL, endTime);
    }

    public TimeSlotRequest<T> withEndTimeBetween(LocalDateTime startOfEndTime, LocalDateTime endOfEndTime){
       return withEndTime(Operator.BETWEEN, startOfEndTime, endOfEndTime);
    }
    public TimeSlotRequest<T> withEndTimeBefore(LocalDateTime endTime){
       return withEndTime(Operator.LESS_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeBefore(Date endTime){
       return withEndTime(Operator.LESS_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeAfter(LocalDateTime endTime){
       return withEndTime(Operator.GREATER_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeAfter(Date endTime){
       return withEndTime(Operator.GREATER_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeBetween(Date startOfEndTime, Date endOfEndTime){
       return withEndTime(Operator.BETWEEN, startOfEndTime, endOfEndTime);
    }




    public TimeSlotRequest<T> filterByCapacity(Integer... capacity){
      if (capacity == null || capacity.length == 0) {
        throw new IllegalArgumentException("filterByCapacity parameter capacity cannot be empty");
      }
      return appendSearchCriteria(createCapacityCriteria(Operator.EQUAL, (Object[])capacity));
    }

    public TimeSlotRequest<T> withCapacity(Operator operator, Object... values){
       return appendSearchCriteria(createCapacityCriteria(operator, values));
    }

    public TimeSlotRequest<T> withCapacityIsUnknown(){
       return withCapacity(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withCapacityIsKnown(){
       return withCapacity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCapacityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.CAPACITY_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withCapacityGreaterThan(Integer capacity){
       return withCapacity(Operator.GREATER_THAN, capacity);
    }

    public TimeSlotRequest<T> withCapacityGreaterThanOrEqualTo(Integer capacity){
       return withCapacity(Operator.GREATER_THAN_OR_EQUAL, capacity);
    }

    public TimeSlotRequest<T> withCapacityLessThan(Integer capacity){
       return withCapacity(Operator.LESS_THAN, capacity);
    }

    public TimeSlotRequest<T> withCapacityLessThanOrEqualTo(Integer capacity){
       return withCapacity(Operator.LESS_THAN_OR_EQUAL, capacity);
    }

    public TimeSlotRequest<T> withCapacityBetween(Integer startOfCapacity, Integer endOfCapacity){
       return withCapacity(Operator.BETWEEN, startOfCapacity, endOfCapacity);
    }



    public TimeSlotRequest<T> filterByAvailableSpots(Integer... availableSpots){
      if (availableSpots == null || availableSpots.length == 0) {
        throw new IllegalArgumentException("filterByAvailableSpots parameter availableSpots cannot be empty");
      }
      return appendSearchCriteria(createAvailableSpotsCriteria(Operator.EQUAL, (Object[])availableSpots));
    }

    public TimeSlotRequest<T> withAvailableSpots(Operator operator, Object... values){
       return appendSearchCriteria(createAvailableSpotsCriteria(operator, values));
    }

    public TimeSlotRequest<T> withAvailableSpotsIsUnknown(){
       return withAvailableSpots(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withAvailableSpotsIsKnown(){
       return withAvailableSpots(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAvailableSpotsCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.AVAILABLE_SPOTS_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withAvailableSpotsGreaterThan(Integer availableSpots){
       return withAvailableSpots(Operator.GREATER_THAN, availableSpots);
    }

    public TimeSlotRequest<T> withAvailableSpotsGreaterThanOrEqualTo(Integer availableSpots){
       return withAvailableSpots(Operator.GREATER_THAN_OR_EQUAL, availableSpots);
    }

    public TimeSlotRequest<T> withAvailableSpotsLessThan(Integer availableSpots){
       return withAvailableSpots(Operator.LESS_THAN, availableSpots);
    }

    public TimeSlotRequest<T> withAvailableSpotsLessThanOrEqualTo(Integer availableSpots){
       return withAvailableSpots(Operator.LESS_THAN_OR_EQUAL, availableSpots);
    }

    public TimeSlotRequest<T> withAvailableSpotsBetween(Integer startOfAvailableSpots, Integer endOfAvailableSpots){
       return withAvailableSpots(Operator.BETWEEN, startOfAvailableSpots, endOfAvailableSpots);
    }



    public TimeSlotRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public TimeSlotRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public TimeSlotRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.CREATED_TIME_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public TimeSlotRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public TimeSlotRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public TimeSlotRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public TimeSlotRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public TimeSlotRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.UPDATED_TIME_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public TimeSlotRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public TimeSlotRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public TimeSlotRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public TimeSlotRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public TimeSlotRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.VERSION_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public TimeSlotRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public TimeSlotRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public TimeSlotRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public TimeSlotRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public TimeSlotRequest<T> count(){
        super.count();
        return this;
    }
    public TimeSlotRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public TimeSlotRequest minCapacity(){
        return minCapacityAs(prefix("minOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest minCapacityAs(String retName){
        super.min(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest maxCapacity(){
        return maxCapacityAs(prefix("maxOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest maxCapacityAs(String retName){
        super.max(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest sumCapacity(){
        return sumCapacityAs(prefix("sumOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest sumCapacityAs(String retName){
        super.sum(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest avgCapacity(){
        return avgCapacityAs(prefix("avgOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest avgCapacityAs(String retName){
        super.avg(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest standardDeviationCapacity(){
        return standardDeviationCapacityAs(prefix("standardDeviationOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest standardDeviationCapacityAs(String retName){
        super.standardDeviation(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest squareRootOfPopulationStandardDeviationCapacity(){
        return squareRootOfPopulationStandardDeviationCapacityAs(prefix("squareRootOfPopulationStandardDeviationOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest squareRootOfPopulationStandardDeviationCapacityAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest sampleVarianceCapacity(){
        return sampleVarianceCapacityAs(prefix("sampleVarianceOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest sampleVarianceCapacityAs(String retName){
        super.sampleVariance(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest samplePopulationVarianceCapacity(){
        return samplePopulationVarianceCapacityAs(prefix("samplePopulationVarianceOf",TimeSlot.CAPACITY_PROPERTY));
    }

    public TimeSlotRequest samplePopulationVarianceCapacityAs(String retName){
        super.samplePopulationVariance(retName, TimeSlot.CAPACITY_PROPERTY);
        return this;
    }
    public TimeSlotRequest minAvailableSpots(){
        return minAvailableSpotsAs(prefix("minOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest minAvailableSpotsAs(String retName){
        super.min(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest maxAvailableSpots(){
        return maxAvailableSpotsAs(prefix("maxOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest maxAvailableSpotsAs(String retName){
        super.max(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest sumAvailableSpots(){
        return sumAvailableSpotsAs(prefix("sumOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest sumAvailableSpotsAs(String retName){
        super.sum(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest avgAvailableSpots(){
        return avgAvailableSpotsAs(prefix("avgOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest avgAvailableSpotsAs(String retName){
        super.avg(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest standardDeviationAvailableSpots(){
        return standardDeviationAvailableSpotsAs(prefix("standardDeviationOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest standardDeviationAvailableSpotsAs(String retName){
        super.standardDeviation(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest squareRootOfPopulationStandardDeviationAvailableSpots(){
        return squareRootOfPopulationStandardDeviationAvailableSpotsAs(prefix("squareRootOfPopulationStandardDeviationOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest squareRootOfPopulationStandardDeviationAvailableSpotsAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest sampleVarianceAvailableSpots(){
        return sampleVarianceAvailableSpotsAs(prefix("sampleVarianceOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest sampleVarianceAvailableSpotsAs(String retName){
        super.sampleVariance(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }
    public TimeSlotRequest samplePopulationVarianceAvailableSpots(){
        return samplePopulationVarianceAvailableSpotsAs(prefix("samplePopulationVarianceOf",TimeSlot.AVAILABLE_SPOTS_PROPERTY));
    }

    public TimeSlotRequest samplePopulationVarianceAvailableSpotsAs(String retName){
        super.samplePopulationVariance(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
        return this;
    }

    public TimeSlotRequest<T> groupById(){
       groupBy(TimeSlot.ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByIdAs(String retName){
       groupBy(retName, TimeSlot.ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.ID_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupBySlotCode(){
       groupBy(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupBySlotCodeAs(String retName){
       groupBy(retName, TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupBySlotCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.SLOT_CODE_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByStartTime(){
       groupBy(TimeSlot.START_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByStartTimeAs(String retName){
       groupBy(retName, TimeSlot.START_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByStartTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.START_TIME_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByEndTime(){
       groupBy(TimeSlot.END_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByEndTimeAs(String retName){
       groupBy(retName, TimeSlot.END_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByEndTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.END_TIME_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByCapacity(){
       groupBy(TimeSlot.CAPACITY_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByCapacityAs(String retName){
       groupBy(retName, TimeSlot.CAPACITY_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByCapacityWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.CAPACITY_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByAvailableSpots(){
       groupBy(TimeSlot.AVAILABLE_SPOTS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByAvailableSpotsAs(String retName){
       groupBy(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByAvailableSpotsWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.AVAILABLE_SPOTS_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByCreatedTime(){
       groupBy(TimeSlot.CREATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, TimeSlot.CREATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByUpdatedTime(){
       groupBy(TimeSlot.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, TimeSlot.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByVersion(){
       groupBy(TimeSlot.VERSION_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByVersionAs(String retName){
       groupBy(retName, TimeSlot.VERSION_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.VERSION_PROPERTY, function);
       return this;
    }



    public TimeSlotRequest<T> orderByIdAscending(){
       addOrderByAscending(TimeSlot.ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByIdDescending(){
       addOrderByDescending(TimeSlot.ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderBySlotCodeAscending(){
       addOrderByAscending(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderBySlotCodeDescending(){
       addOrderByDescending(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderBySlotCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderBySlotCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TimeSlot.SLOT_CODE_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderByStartTimeAscending(){
       addOrderByAscending(TimeSlot.START_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByStartTimeDescending(){
       addOrderByDescending(TimeSlot.START_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByEndTimeAscending(){
       addOrderByAscending(TimeSlot.END_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByEndTimeDescending(){
       addOrderByDescending(TimeSlot.END_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByCapacityAscending(){
       addOrderByAscending(TimeSlot.CAPACITY_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByCapacityDescending(){
       addOrderByDescending(TimeSlot.CAPACITY_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByAvailableSpotsAscending(){
       addOrderByAscending(TimeSlot.AVAILABLE_SPOTS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByAvailableSpotsDescending(){
       addOrderByDescending(TimeSlot.AVAILABLE_SPOTS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(TimeSlot.CREATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(TimeSlot.CREATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(TimeSlot.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(TimeSlot.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByVersionAscending(){
       addOrderByAscending(TimeSlot.VERSION_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByVersionDescending(){
       addOrderByDescending(TimeSlot.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public TimeSlotRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public TimeSlotRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public TimeSlotRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public TimeSlotRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public TimeSlotRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}