package com.doublechaintech.enterpriselogisticsservice.timeslot;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
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
        return selectId().selectSlotId().selectMovingOrderIdOnly().selectStartTime().selectEndTime().selectStatus().selectCreateTime().selectVersion();
    }

    public TimeSlotRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TimeSlotRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectSlotId().selectMovingOrder().selectStartTime().selectEndTime().selectStatus().selectCreateTime().selectVersion();
    }

    public TimeSlotRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectSlotId().selectMovingOrder().selectStartTime().selectEndTime().selectStatus().selectCreateTime().selectVersion();
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
    public TimeSlotRequest<T> selectSlotId(){
       selectProperty(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }

    /**
     * fill the slotId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  slotId) to fetch slotId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectSlotId(){
       unselectProperty(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectMovingOrderIdOnly(){
       selectProperty(TimeSlot.MOVING_ORDER_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public TimeSlotRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(TimeSlot.MOVING_ORDER_PROPERTY);
       enhanceRelation(TimeSlot.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public TimeSlotRequest<T> unselectMovingOrder(){
       unselectProperty(TimeSlot.MOVING_ORDER_PROPERTY);
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
    public TimeSlotRequest<T> selectStatus(){
       selectProperty(TimeSlot.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectStatus(){
       unselectProperty(TimeSlot.STATUS_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> selectCreateTime(){
       selectProperty(TimeSlot.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TimeSlotRequest<T> unselectCreateTime(){
       unselectProperty(TimeSlot.CREATE_TIME_PROPERTY);
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



    public TimeSlotRequest<T> filterBySlotId(String... slotId){
      if (slotId == null || slotId.length == 0) {
        throw new IllegalArgumentException("filterBySlotId parameter slotId cannot be empty");
      }
      return appendSearchCriteria(createSlotIdCriteria(Operator.EQUAL, (Object[])slotId));
    }

    public TimeSlotRequest<T> withSlotId(Operator operator, Object... values){
       return appendSearchCriteria(createSlotIdCriteria(operator, values));
    }

    public TimeSlotRequest<T> withSlotIdIsUnknown(){
       return withSlotId(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withSlotIdIsKnown(){
       return withSlotId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createSlotIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.SLOT_ID_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withSlotIdGreaterThan(String slotId){
       return withSlotId(Operator.GREATER_THAN, slotId);
    }

    public TimeSlotRequest<T> withSlotIdGreaterThanOrEqualTo(String slotId){
       return withSlotId(Operator.GREATER_THAN_OR_EQUAL, slotId);
    }

    public TimeSlotRequest<T> withSlotIdLessThan(String slotId){
       return withSlotId(Operator.LESS_THAN, slotId);
    }

    public TimeSlotRequest<T> withSlotIdLessThanOrEqualTo(String slotId){
       return withSlotId(Operator.LESS_THAN_OR_EQUAL, slotId);
    }

    public TimeSlotRequest<T> withSlotIdBetween(String startOfSlotId, String endOfSlotId){
       return withSlotId(Operator.BETWEEN, startOfSlotId, endOfSlotId);
    }
    public TimeSlotRequest<T> withSlotIdStartingWith(String slotId){
       return withSlotId(Operator.BEGIN_WITH, slotId);
    }
    public TimeSlotRequest<T> withSlotIdContaining(String slotId){
       return withSlotId(Operator.CONTAIN, slotId);
    }

    public TimeSlotRequest<T> withSlotIdEndingWith(String slotId){
       return withSlotId(Operator.END_WITH, slotId);
    }

    public TimeSlotRequest<T> withSlotIdIs(String slotId){
       return withSlotId(Operator.EQUAL, slotId);
    }

    public TimeSlotRequest<T> withSlotIdSoundingLike(String slotId){
       return withSlotId(Operator.SOUNDS_LIKE, slotId);
    }



    public TimeSlotRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public TimeSlotRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public TimeSlotRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.MOVING_ORDER_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public TimeSlotRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(TimeSlot.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public TimeSlotRequest<T> filterByStartTime(String... startTime){
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

    public TimeSlotRequest<T> withStartTimeGreaterThan(String startTime){
       return withStartTime(Operator.GREATER_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeGreaterThanOrEqualTo(String startTime){
       return withStartTime(Operator.GREATER_THAN_OR_EQUAL, startTime);
    }

    public TimeSlotRequest<T> withStartTimeLessThan(String startTime){
       return withStartTime(Operator.LESS_THAN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeLessThanOrEqualTo(String startTime){
       return withStartTime(Operator.LESS_THAN_OR_EQUAL, startTime);
    }

    public TimeSlotRequest<T> withStartTimeBetween(String startOfStartTime, String endOfStartTime){
       return withStartTime(Operator.BETWEEN, startOfStartTime, endOfStartTime);
    }
    public TimeSlotRequest<T> withStartTimeStartingWith(String startTime){
       return withStartTime(Operator.BEGIN_WITH, startTime);
    }
    public TimeSlotRequest<T> withStartTimeContaining(String startTime){
       return withStartTime(Operator.CONTAIN, startTime);
    }

    public TimeSlotRequest<T> withStartTimeEndingWith(String startTime){
       return withStartTime(Operator.END_WITH, startTime);
    }

    public TimeSlotRequest<T> withStartTimeIs(String startTime){
       return withStartTime(Operator.EQUAL, startTime);
    }

    public TimeSlotRequest<T> withStartTimeSoundingLike(String startTime){
       return withStartTime(Operator.SOUNDS_LIKE, startTime);
    }



    public TimeSlotRequest<T> filterByEndTime(String... endTime){
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

    public TimeSlotRequest<T> withEndTimeGreaterThan(String endTime){
       return withEndTime(Operator.GREATER_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeGreaterThanOrEqualTo(String endTime){
       return withEndTime(Operator.GREATER_THAN_OR_EQUAL, endTime);
    }

    public TimeSlotRequest<T> withEndTimeLessThan(String endTime){
       return withEndTime(Operator.LESS_THAN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeLessThanOrEqualTo(String endTime){
       return withEndTime(Operator.LESS_THAN_OR_EQUAL, endTime);
    }

    public TimeSlotRequest<T> withEndTimeBetween(String startOfEndTime, String endOfEndTime){
       return withEndTime(Operator.BETWEEN, startOfEndTime, endOfEndTime);
    }
    public TimeSlotRequest<T> withEndTimeStartingWith(String endTime){
       return withEndTime(Operator.BEGIN_WITH, endTime);
    }
    public TimeSlotRequest<T> withEndTimeContaining(String endTime){
       return withEndTime(Operator.CONTAIN, endTime);
    }

    public TimeSlotRequest<T> withEndTimeEndingWith(String endTime){
       return withEndTime(Operator.END_WITH, endTime);
    }

    public TimeSlotRequest<T> withEndTimeIs(String endTime){
       return withEndTime(Operator.EQUAL, endTime);
    }

    public TimeSlotRequest<T> withEndTimeSoundingLike(String endTime){
       return withEndTime(Operator.SOUNDS_LIKE, endTime);
    }



    public TimeSlotRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public TimeSlotRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public TimeSlotRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.STATUS_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public TimeSlotRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public TimeSlotRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public TimeSlotRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public TimeSlotRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public TimeSlotRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public TimeSlotRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public TimeSlotRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public TimeSlotRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public TimeSlotRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public TimeSlotRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public TimeSlotRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public TimeSlotRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public TimeSlotRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TimeSlot.CREATE_TIME_PROPERTY, operator, values);
    }

    public TimeSlotRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public TimeSlotRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public TimeSlotRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
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
    public TimeSlotRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public TimeSlotRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(TimeSlot.MOVING_ORDER_PROPERTY, subRequest);
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

    public TimeSlotRequest<T> groupBySlotId(){
       groupBy(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupBySlotIdAs(String retName){
       groupBy(retName, TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupBySlotIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.SLOT_ID_PROPERTY, function);
       return this;
    }
    public TimeSlotRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(TimeSlot.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public TimeSlotRequest<T> groupByMovingOrder(){
       groupBy(TimeSlot.MOVING_ORDER_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, TimeSlot.MOVING_ORDER_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.MOVING_ORDER_PROPERTY, function);
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

    public TimeSlotRequest<T> groupByStatus(){
       groupBy(TimeSlot.STATUS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByStatusAs(String retName){
       groupBy(retName, TimeSlot.STATUS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.STATUS_PROPERTY, function);
       return this;
    }

    public TimeSlotRequest<T> groupByCreateTime(){
       groupBy(TimeSlot.CREATE_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, TimeSlot.CREATE_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TimeSlot.CREATE_TIME_PROPERTY, function);
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

    public TimeSlotRequest<T> orderBySlotIdAscending(){
       addOrderByAscending(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderBySlotIdDescending(){
       addOrderByDescending(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderBySlotIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderBySlotIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TimeSlot.SLOT_ID_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(TimeSlot.MOVING_ORDER_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(TimeSlot.MOVING_ORDER_PROPERTY);
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
    public TimeSlotRequest<T> orderByStartTimeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TimeSlot.START_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByStartTimeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TimeSlot.START_TIME_PROPERTY);
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
    public TimeSlotRequest<T> orderByEndTimeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TimeSlot.END_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByEndTimeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TimeSlot.END_TIME_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderByStatusAscending(){
       addOrderByAscending(TimeSlot.STATUS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByStatusDescending(){
       addOrderByDescending(TimeSlot.STATUS_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TimeSlot.STATUS_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TimeSlot.STATUS_PROPERTY);
       return this;
    }
    public TimeSlotRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(TimeSlot.CREATE_TIME_PROPERTY);
       return this;
    }

    public TimeSlotRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(TimeSlot.CREATE_TIME_PROPERTY);
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


    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }







   public TimeSlotRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public TimeSlotRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, TimeSlot.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
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