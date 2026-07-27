package com.doublechaintech.enterpriselogisticsservice.dispatchplan;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class DispatchPlanRequest<T extends DispatchPlan> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public DispatchPlanRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public DispatchPlanRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public DispatchPlanRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public DispatchPlanRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public DispatchPlanRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public DispatchPlanRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public DispatchPlanRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (DispatchPlanRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public DispatchPlanRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public DispatchPlanRequest<T> matchingAnyOf(DispatchPlanRequest dispatchPlan){
        super.internalMatchAny(dispatchPlan);
        return this;
    }

    public DispatchPlanRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public DispatchPlanRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public DispatchPlanRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public DispatchPlanRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectPlanId().selectMovingOrderIdOnly().selectVehicleIdOnly().selectDriverIdOnly().selectStatus().selectScheduledDeparture().selectScheduledArrival().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public DispatchPlanRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public DispatchPlanRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectPlanId().selectMovingOrder().selectVehicle().selectDriver().selectStatus().selectScheduledDeparture().selectScheduledArrival().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public DispatchPlanRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectPlanId().selectMovingOrder().selectVehicle().selectDriver().selectStatus().selectScheduledDeparture().selectScheduledArrival().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public DispatchPlanRequest<T> selectId(){
       selectProperty(DispatchPlan.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectId(){
       unselectProperty(DispatchPlan.ID_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectPlanId(){
       selectProperty(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }

    /**
     * fill the planId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  planId) to fetch planId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectPlanId(){
       unselectProperty(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectMovingOrderIdOnly(){
       selectProperty(DispatchPlan.MOVING_ORDER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public DispatchPlanRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(DispatchPlan.MOVING_ORDER_PROPERTY);
       enhanceRelation(DispatchPlan.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public DispatchPlanRequest<T> unselectMovingOrder(){
       unselectProperty(DispatchPlan.MOVING_ORDER_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectVehicleIdOnly(){
       selectProperty(DispatchPlan.VEHICLE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> selectVehicle(){
        return selectVehicleWith(Q.vehicles().unlimited().selectSelf());
    }

    public DispatchPlanRequest<T> selectVehicleWith(VehicleRequest vehicle){
       selectProperty(DispatchPlan.VEHICLE_PROPERTY);
       enhanceRelation(DispatchPlan.VEHICLE_PROPERTY, vehicle);
       return this;
    }

    public DispatchPlanRequest<T> unselectVehicle(){
       unselectProperty(DispatchPlan.VEHICLE_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectDriverIdOnly(){
       selectProperty(DispatchPlan.DRIVER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> selectDriver(){
        return selectDriverWith(Q.staffMembers().unlimited().selectSelf());
    }

    public DispatchPlanRequest<T> selectDriverWith(StaffMemberRequest driver){
       selectProperty(DispatchPlan.DRIVER_PROPERTY);
       enhanceRelation(DispatchPlan.DRIVER_PROPERTY, driver);
       return this;
    }

    public DispatchPlanRequest<T> unselectDriver(){
       unselectProperty(DispatchPlan.DRIVER_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectStatus(){
       selectProperty(DispatchPlan.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectStatus(){
       unselectProperty(DispatchPlan.STATUS_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectScheduledDeparture(){
       selectProperty(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }

    /**
     * fill the scheduledDeparture with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  scheduledDeparture) to fetch scheduledDeparture property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectScheduledDeparture(){
       unselectProperty(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectScheduledArrival(){
       selectProperty(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }

    /**
     * fill the scheduledArrival with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  scheduledArrival) to fetch scheduledArrival property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectScheduledArrival(){
       unselectProperty(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectCreateTime(){
       selectProperty(DispatchPlan.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectCreateTime(){
       unselectProperty(DispatchPlan.CREATE_TIME_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectUpdateTime(){
       selectProperty(DispatchPlan.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectUpdateTime(){
       unselectProperty(DispatchPlan.UPDATE_TIME_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectVersion(){
       selectProperty(DispatchPlan.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectVersion(){
       unselectProperty(DispatchPlan.VERSION_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.ID_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public DispatchPlanRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public DispatchPlanRequest<T> filterByPlanId(String... planId){
      if (planId == null || planId.length == 0) {
        throw new IllegalArgumentException("filterByPlanId parameter planId cannot be empty");
      }
      return appendSearchCriteria(createPlanIdCriteria(Operator.EQUAL, (Object[])planId));
    }

    public DispatchPlanRequest<T> withPlanId(Operator operator, Object... values){
       return appendSearchCriteria(createPlanIdCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withPlanIdIsUnknown(){
       return withPlanId(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withPlanIdIsKnown(){
       return withPlanId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPlanIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.PLAN_ID_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withPlanIdGreaterThan(String planId){
       return withPlanId(Operator.GREATER_THAN, planId);
    }

    public DispatchPlanRequest<T> withPlanIdGreaterThanOrEqualTo(String planId){
       return withPlanId(Operator.GREATER_THAN_OR_EQUAL, planId);
    }

    public DispatchPlanRequest<T> withPlanIdLessThan(String planId){
       return withPlanId(Operator.LESS_THAN, planId);
    }

    public DispatchPlanRequest<T> withPlanIdLessThanOrEqualTo(String planId){
       return withPlanId(Operator.LESS_THAN_OR_EQUAL, planId);
    }

    public DispatchPlanRequest<T> withPlanIdBetween(String startOfPlanId, String endOfPlanId){
       return withPlanId(Operator.BETWEEN, startOfPlanId, endOfPlanId);
    }
    public DispatchPlanRequest<T> withPlanIdStartingWith(String planId){
       return withPlanId(Operator.BEGIN_WITH, planId);
    }
    public DispatchPlanRequest<T> withPlanIdContaining(String planId){
       return withPlanId(Operator.CONTAIN, planId);
    }

    public DispatchPlanRequest<T> withPlanIdEndingWith(String planId){
       return withPlanId(Operator.END_WITH, planId);
    }

    public DispatchPlanRequest<T> withPlanIdIs(String planId){
       return withPlanId(Operator.EQUAL, planId);
    }

    public DispatchPlanRequest<T> withPlanIdSoundingLike(String planId){
       return withPlanId(Operator.SOUNDS_LIKE, planId);
    }



    public DispatchPlanRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public DispatchPlanRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.MOVING_ORDER_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public DispatchPlanRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(DispatchPlan.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public DispatchPlanRequest<T> filterByVehicle(Vehicle... vehicle){
      if (vehicle == null || vehicle.length == 0) {
        throw new IllegalArgumentException("filterByVehicle parameter vehicle cannot be empty");
      }
      return appendSearchCriteria(createVehicleCriteria(Operator.EQUAL, (Object[])vehicle));
    }

    public DispatchPlanRequest<T> withVehicle(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withVehicleIsUnknown(){
       return withVehicle(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withVehicleIsKnown(){
       return withVehicle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.VEHICLE_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> filterByVehicle(Long vehicle){
      if(vehicle == null){
         return this;
      }
      return withVehicle(Operator.EQUAL, vehicle);
    }
    public DispatchPlanRequest<T> withVehicleMatching(VehicleRequest vehicle){
       return appendSearchCriteria(new SubQuerySearchCriteria(DispatchPlan.VEHICLE_PROPERTY, vehicle, Vehicle.ID_PROPERTY));
    }

    public DispatchPlanRequest<T> filterByDriver(StaffMember... driver){
      if (driver == null || driver.length == 0) {
        throw new IllegalArgumentException("filterByDriver parameter driver cannot be empty");
      }
      return appendSearchCriteria(createDriverCriteria(Operator.EQUAL, (Object[])driver));
    }

    public DispatchPlanRequest<T> withDriver(Operator operator, Object... values){
       return appendSearchCriteria(createDriverCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withDriverIsUnknown(){
       return withDriver(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withDriverIsKnown(){
       return withDriver(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDriverCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.DRIVER_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> filterByDriver(Long driver){
      if(driver == null){
         return this;
      }
      return withDriver(Operator.EQUAL, driver);
    }
    public DispatchPlanRequest<T> withDriverMatching(StaffMemberRequest driver){
       return appendSearchCriteria(new SubQuerySearchCriteria(DispatchPlan.DRIVER_PROPERTY, driver, StaffMember.ID_PROPERTY));
    }

    public DispatchPlanRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public DispatchPlanRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.STATUS_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public DispatchPlanRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public DispatchPlanRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public DispatchPlanRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public DispatchPlanRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public DispatchPlanRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public DispatchPlanRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public DispatchPlanRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public DispatchPlanRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public DispatchPlanRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public DispatchPlanRequest<T> filterByScheduledDeparture(String... scheduledDeparture){
      if (scheduledDeparture == null || scheduledDeparture.length == 0) {
        throw new IllegalArgumentException("filterByScheduledDeparture parameter scheduledDeparture cannot be empty");
      }
      return appendSearchCriteria(createScheduledDepartureCriteria(Operator.EQUAL, (Object[])scheduledDeparture));
    }

    public DispatchPlanRequest<T> withScheduledDeparture(Operator operator, Object... values){
       return appendSearchCriteria(createScheduledDepartureCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withScheduledDepartureIsUnknown(){
       return withScheduledDeparture(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withScheduledDepartureIsKnown(){
       return withScheduledDeparture(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createScheduledDepartureCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withScheduledDepartureGreaterThan(String scheduledDeparture){
       return withScheduledDeparture(Operator.GREATER_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureGreaterThanOrEqualTo(String scheduledDeparture){
       return withScheduledDeparture(Operator.GREATER_THAN_OR_EQUAL, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureLessThan(String scheduledDeparture){
       return withScheduledDeparture(Operator.LESS_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureLessThanOrEqualTo(String scheduledDeparture){
       return withScheduledDeparture(Operator.LESS_THAN_OR_EQUAL, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureBetween(String startOfScheduledDeparture, String endOfScheduledDeparture){
       return withScheduledDeparture(Operator.BETWEEN, startOfScheduledDeparture, endOfScheduledDeparture);
    }
    public DispatchPlanRequest<T> withScheduledDepartureStartingWith(String scheduledDeparture){
       return withScheduledDeparture(Operator.BEGIN_WITH, scheduledDeparture);
    }
    public DispatchPlanRequest<T> withScheduledDepartureContaining(String scheduledDeparture){
       return withScheduledDeparture(Operator.CONTAIN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureEndingWith(String scheduledDeparture){
       return withScheduledDeparture(Operator.END_WITH, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureIs(String scheduledDeparture){
       return withScheduledDeparture(Operator.EQUAL, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureSoundingLike(String scheduledDeparture){
       return withScheduledDeparture(Operator.SOUNDS_LIKE, scheduledDeparture);
    }



    public DispatchPlanRequest<T> filterByScheduledArrival(String... scheduledArrival){
      if (scheduledArrival == null || scheduledArrival.length == 0) {
        throw new IllegalArgumentException("filterByScheduledArrival parameter scheduledArrival cannot be empty");
      }
      return appendSearchCriteria(createScheduledArrivalCriteria(Operator.EQUAL, (Object[])scheduledArrival));
    }

    public DispatchPlanRequest<T> withScheduledArrival(Operator operator, Object... values){
       return appendSearchCriteria(createScheduledArrivalCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withScheduledArrivalIsUnknown(){
       return withScheduledArrival(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withScheduledArrivalIsKnown(){
       return withScheduledArrival(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createScheduledArrivalCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withScheduledArrivalGreaterThan(String scheduledArrival){
       return withScheduledArrival(Operator.GREATER_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalGreaterThanOrEqualTo(String scheduledArrival){
       return withScheduledArrival(Operator.GREATER_THAN_OR_EQUAL, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalLessThan(String scheduledArrival){
       return withScheduledArrival(Operator.LESS_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalLessThanOrEqualTo(String scheduledArrival){
       return withScheduledArrival(Operator.LESS_THAN_OR_EQUAL, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalBetween(String startOfScheduledArrival, String endOfScheduledArrival){
       return withScheduledArrival(Operator.BETWEEN, startOfScheduledArrival, endOfScheduledArrival);
    }
    public DispatchPlanRequest<T> withScheduledArrivalStartingWith(String scheduledArrival){
       return withScheduledArrival(Operator.BEGIN_WITH, scheduledArrival);
    }
    public DispatchPlanRequest<T> withScheduledArrivalContaining(String scheduledArrival){
       return withScheduledArrival(Operator.CONTAIN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalEndingWith(String scheduledArrival){
       return withScheduledArrival(Operator.END_WITH, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalIs(String scheduledArrival){
       return withScheduledArrival(Operator.EQUAL, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalSoundingLike(String scheduledArrival){
       return withScheduledArrival(Operator.SOUNDS_LIKE, scheduledArrival);
    }



    public DispatchPlanRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public DispatchPlanRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.CREATE_TIME_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public DispatchPlanRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public DispatchPlanRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public DispatchPlanRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public DispatchPlanRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.UPDATE_TIME_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public DispatchPlanRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public DispatchPlanRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public DispatchPlanRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public DispatchPlanRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.VERSION_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public DispatchPlanRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public DispatchPlanRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public DispatchPlanRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public DispatchPlanRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public DispatchPlanRequest<T> count(){
        super.count();
        return this;
    }
    public DispatchPlanRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public DispatchPlanRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public DispatchPlanRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(DispatchPlan.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }

    public DispatchPlanRequest<T> groupByVehicleWithDetails(){
       return groupByVehicleWithDetails(Q.vehicles().unlimited());
    }

    public DispatchPlanRequest<T> groupByVehicleWithDetails(VehicleRequest subRequest){
       aggregate(DispatchPlan.VEHICLE_PROPERTY, subRequest);
       return this;
    }

    public DispatchPlanRequest<T> groupByDriverWithDetails(){
       return groupByDriverWithDetails(Q.staffMembers().unlimited());
    }

    public DispatchPlanRequest<T> groupByDriverWithDetails(StaffMemberRequest subRequest){
       aggregate(DispatchPlan.DRIVER_PROPERTY, subRequest);
       return this;
    }








    public DispatchPlanRequest<T> groupById(){
       groupBy(DispatchPlan.ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByIdAs(String retName){
       groupBy(retName, DispatchPlan.ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.ID_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByPlanId(){
       groupBy(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByPlanIdAs(String retName){
       groupBy(retName, DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByPlanIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.PLAN_ID_PROPERTY, function);
       return this;
    }
    public DispatchPlanRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(DispatchPlan.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public DispatchPlanRequest<T> groupByMovingOrder(){
       groupBy(DispatchPlan.MOVING_ORDER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, DispatchPlan.MOVING_ORDER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.MOVING_ORDER_PROPERTY, function);
       return this;
    }
    public DispatchPlanRequest<T> groupByVehicleWith(VehicleRequest subRequest){
       groupBy(DispatchPlan.VEHICLE_PROPERTY, subRequest);
       return this;
    }
    public DispatchPlanRequest<T> groupByVehicle(){
       groupBy(DispatchPlan.VEHICLE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByVehicleAs(String retName){
       groupBy(retName, DispatchPlan.VEHICLE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByVehicleWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.VEHICLE_PROPERTY, function);
       return this;
    }
    public DispatchPlanRequest<T> groupByDriverWith(StaffMemberRequest subRequest){
       groupBy(DispatchPlan.DRIVER_PROPERTY, subRequest);
       return this;
    }
    public DispatchPlanRequest<T> groupByDriver(){
       groupBy(DispatchPlan.DRIVER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByDriverAs(String retName){
       groupBy(retName, DispatchPlan.DRIVER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByDriverWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.DRIVER_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByStatus(){
       groupBy(DispatchPlan.STATUS_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByStatusAs(String retName){
       groupBy(retName, DispatchPlan.STATUS_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.STATUS_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByScheduledDeparture(){
       groupBy(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByScheduledDepartureAs(String retName){
       groupBy(retName, DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByScheduledDepartureWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByScheduledArrival(){
       groupBy(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByScheduledArrivalAs(String retName){
       groupBy(retName, DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByScheduledArrivalWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByCreateTime(){
       groupBy(DispatchPlan.CREATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, DispatchPlan.CREATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByUpdateTime(){
       groupBy(DispatchPlan.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, DispatchPlan.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByVersion(){
       groupBy(DispatchPlan.VERSION_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByVersionAs(String retName){
       groupBy(retName, DispatchPlan.VERSION_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.VERSION_PROPERTY, function);
       return this;
    }



    public DispatchPlanRequest<T> orderByIdAscending(){
       addOrderByAscending(DispatchPlan.ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByIdDescending(){
       addOrderByDescending(DispatchPlan.ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByPlanIdAscending(){
       addOrderByAscending(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByPlanIdDescending(){
       addOrderByDescending(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByPlanIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByPlanIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DispatchPlan.PLAN_ID_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(DispatchPlan.MOVING_ORDER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(DispatchPlan.MOVING_ORDER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByVehicleAscending(){
       addOrderByAscending(DispatchPlan.VEHICLE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByVehicleDescending(){
       addOrderByDescending(DispatchPlan.VEHICLE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByDriverAscending(){
       addOrderByAscending(DispatchPlan.DRIVER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByDriverDescending(){
       addOrderByDescending(DispatchPlan.DRIVER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByStatusAscending(){
       addOrderByAscending(DispatchPlan.STATUS_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByStatusDescending(){
       addOrderByDescending(DispatchPlan.STATUS_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DispatchPlan.STATUS_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DispatchPlan.STATUS_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByScheduledDepartureAscending(){
       addOrderByAscending(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByScheduledDepartureDescending(){
       addOrderByDescending(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByScheduledDepartureAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByScheduledDepartureDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByScheduledArrivalAscending(){
       addOrderByAscending(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByScheduledArrivalDescending(){
       addOrderByDescending(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByScheduledArrivalAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByScheduledArrivalDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(DispatchPlan.CREATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(DispatchPlan.CREATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(DispatchPlan.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(DispatchPlan.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByVersionAscending(){
       addOrderByAscending(DispatchPlan.VERSION_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByVersionDescending(){
       addOrderByDescending(DispatchPlan.VERSION_PROPERTY);
       return this;
    }


    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }

    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }

    public StaffMemberRequest rollUpToDriver(){
       StaffMemberRequest driver = Q.staffMembers().unlimited();
       this.withDriverMatching(driver)
           .groupByDriverWith(driver);
       return driver;
    }








   public DispatchPlanRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public DispatchPlanRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, DispatchPlan.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
       return this;
   }
   public DispatchPlanRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle){
       return facetByVehicleAs(facetName, vehicle, true);
   }

   public DispatchPlanRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle, boolean includeAllFacets){
       addFacet(facetName, DispatchPlan.VEHICLE_PROPERTY, vehicle, includeAllFacets);
       return this;
   }
   public DispatchPlanRequest<T> facetByDriverAs(String facetName, StaffMemberRequest driver){
       return facetByDriverAs(facetName, driver, true);
   }

   public DispatchPlanRequest<T> facetByDriverAs(String facetName, StaffMemberRequest driver, boolean includeAllFacets){
       addFacet(facetName, DispatchPlan.DRIVER_PROPERTY, driver, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public DispatchPlanRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public DispatchPlanRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public DispatchPlanRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public DispatchPlanRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public DispatchPlanRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}