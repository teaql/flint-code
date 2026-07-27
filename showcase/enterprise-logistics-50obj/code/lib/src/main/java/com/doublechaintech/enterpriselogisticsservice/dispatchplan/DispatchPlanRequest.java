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
        return selectId().selectPlanNumber().selectStatus().selectMovingOrderIdOnly().selectVehicleIdOnly().selectDriverIdOnly().selectScheduledDeparture().selectScheduledArrival().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public DispatchPlanRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public DispatchPlanRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectPlanNumber().selectStatus().selectMovingOrder().selectVehicle().selectDriver().selectScheduledDeparture().selectScheduledArrival().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public DispatchPlanRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectPlanNumber().selectStatus().selectMovingOrder().selectVehicle().selectDriver().selectScheduledDeparture().selectScheduledArrival().selectCreatedTime().selectUpdatedTime().selectVersion();
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
    public DispatchPlanRequest<T> selectPlanNumber(){
       selectProperty(DispatchPlan.PLAN_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the planNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  planNumber) to fetch planNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectPlanNumber(){
       unselectProperty(DispatchPlan.PLAN_NUMBER_PROPERTY);
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
    public DispatchPlanRequest<T> selectCreatedTime(){
       selectProperty(DispatchPlan.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectCreatedTime(){
       unselectProperty(DispatchPlan.CREATED_TIME_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> selectUpdatedTime(){
       selectProperty(DispatchPlan.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DispatchPlanRequest<T> unselectUpdatedTime(){
       unselectProperty(DispatchPlan.UPDATED_TIME_PROPERTY);
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



    public DispatchPlanRequest<T> filterByPlanNumber(String... planNumber){
      if (planNumber == null || planNumber.length == 0) {
        throw new IllegalArgumentException("filterByPlanNumber parameter planNumber cannot be empty");
      }
      return appendSearchCriteria(createPlanNumberCriteria(Operator.EQUAL, (Object[])planNumber));
    }

    public DispatchPlanRequest<T> withPlanNumber(Operator operator, Object... values){
       return appendSearchCriteria(createPlanNumberCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withPlanNumberIsUnknown(){
       return withPlanNumber(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withPlanNumberIsKnown(){
       return withPlanNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPlanNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.PLAN_NUMBER_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withPlanNumberGreaterThan(String planNumber){
       return withPlanNumber(Operator.GREATER_THAN, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberGreaterThanOrEqualTo(String planNumber){
       return withPlanNumber(Operator.GREATER_THAN_OR_EQUAL, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberLessThan(String planNumber){
       return withPlanNumber(Operator.LESS_THAN, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberLessThanOrEqualTo(String planNumber){
       return withPlanNumber(Operator.LESS_THAN_OR_EQUAL, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberBetween(String startOfPlanNumber, String endOfPlanNumber){
       return withPlanNumber(Operator.BETWEEN, startOfPlanNumber, endOfPlanNumber);
    }
    public DispatchPlanRequest<T> withPlanNumberStartingWith(String planNumber){
       return withPlanNumber(Operator.BEGIN_WITH, planNumber);
    }
    public DispatchPlanRequest<T> withPlanNumberContaining(String planNumber){
       return withPlanNumber(Operator.CONTAIN, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberEndingWith(String planNumber){
       return withPlanNumber(Operator.END_WITH, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberIs(String planNumber){
       return withPlanNumber(Operator.EQUAL, planNumber);
    }

    public DispatchPlanRequest<T> withPlanNumberSoundingLike(String planNumber){
       return withPlanNumber(Operator.SOUNDS_LIKE, planNumber);
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

    public DispatchPlanRequest<T> filterByScheduledDeparture(LocalDateTime... scheduledDeparture){
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

    public DispatchPlanRequest<T> withScheduledDepartureGreaterThan(LocalDateTime scheduledDeparture){
       return withScheduledDeparture(Operator.GREATER_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureGreaterThanOrEqualTo(LocalDateTime scheduledDeparture){
       return withScheduledDeparture(Operator.GREATER_THAN_OR_EQUAL, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureLessThan(LocalDateTime scheduledDeparture){
       return withScheduledDeparture(Operator.LESS_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureLessThanOrEqualTo(LocalDateTime scheduledDeparture){
       return withScheduledDeparture(Operator.LESS_THAN_OR_EQUAL, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureBetween(LocalDateTime startOfScheduledDeparture, LocalDateTime endOfScheduledDeparture){
       return withScheduledDeparture(Operator.BETWEEN, startOfScheduledDeparture, endOfScheduledDeparture);
    }
    public DispatchPlanRequest<T> withScheduledDepartureBefore(LocalDateTime scheduledDeparture){
       return withScheduledDeparture(Operator.LESS_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureBefore(Date scheduledDeparture){
       return withScheduledDeparture(Operator.LESS_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureAfter(LocalDateTime scheduledDeparture){
       return withScheduledDeparture(Operator.GREATER_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureAfter(Date scheduledDeparture){
       return withScheduledDeparture(Operator.GREATER_THAN, scheduledDeparture);
    }

    public DispatchPlanRequest<T> withScheduledDepartureBetween(Date startOfScheduledDeparture, Date endOfScheduledDeparture){
       return withScheduledDeparture(Operator.BETWEEN, startOfScheduledDeparture, endOfScheduledDeparture);
    }




    public DispatchPlanRequest<T> filterByScheduledArrival(LocalDateTime... scheduledArrival){
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

    public DispatchPlanRequest<T> withScheduledArrivalGreaterThan(LocalDateTime scheduledArrival){
       return withScheduledArrival(Operator.GREATER_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalGreaterThanOrEqualTo(LocalDateTime scheduledArrival){
       return withScheduledArrival(Operator.GREATER_THAN_OR_EQUAL, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalLessThan(LocalDateTime scheduledArrival){
       return withScheduledArrival(Operator.LESS_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalLessThanOrEqualTo(LocalDateTime scheduledArrival){
       return withScheduledArrival(Operator.LESS_THAN_OR_EQUAL, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalBetween(LocalDateTime startOfScheduledArrival, LocalDateTime endOfScheduledArrival){
       return withScheduledArrival(Operator.BETWEEN, startOfScheduledArrival, endOfScheduledArrival);
    }
    public DispatchPlanRequest<T> withScheduledArrivalBefore(LocalDateTime scheduledArrival){
       return withScheduledArrival(Operator.LESS_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalBefore(Date scheduledArrival){
       return withScheduledArrival(Operator.LESS_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalAfter(LocalDateTime scheduledArrival){
       return withScheduledArrival(Operator.GREATER_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalAfter(Date scheduledArrival){
       return withScheduledArrival(Operator.GREATER_THAN, scheduledArrival);
    }

    public DispatchPlanRequest<T> withScheduledArrivalBetween(Date startOfScheduledArrival, Date endOfScheduledArrival){
       return withScheduledArrival(Operator.BETWEEN, startOfScheduledArrival, endOfScheduledArrival);
    }




    public DispatchPlanRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public DispatchPlanRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.CREATED_TIME_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public DispatchPlanRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public DispatchPlanRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public DispatchPlanRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public DispatchPlanRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public DispatchPlanRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public DispatchPlanRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DispatchPlan.UPDATED_TIME_PROPERTY, operator, values);
    }

    public DispatchPlanRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public DispatchPlanRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public DispatchPlanRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
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

    public DispatchPlanRequest<T> groupByPlanNumber(){
       groupBy(DispatchPlan.PLAN_NUMBER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByPlanNumberAs(String retName){
       groupBy(retName, DispatchPlan.PLAN_NUMBER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByPlanNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.PLAN_NUMBER_PROPERTY, function);
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

    public DispatchPlanRequest<T> groupByCreatedTime(){
       groupBy(DispatchPlan.CREATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, DispatchPlan.CREATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public DispatchPlanRequest<T> groupByUpdatedTime(){
       groupBy(DispatchPlan.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, DispatchPlan.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DispatchPlan.UPDATED_TIME_PROPERTY, function);
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

    public DispatchPlanRequest<T> orderByPlanNumberAscending(){
       addOrderByAscending(DispatchPlan.PLAN_NUMBER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByPlanNumberDescending(){
       addOrderByDescending(DispatchPlan.PLAN_NUMBER_PROPERTY);
       return this;
    }
    public DispatchPlanRequest<T> orderByPlanNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DispatchPlan.PLAN_NUMBER_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByPlanNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DispatchPlan.PLAN_NUMBER_PROPERTY);
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

    public DispatchPlanRequest<T> orderByScheduledDepartureAscending(){
       addOrderByAscending(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByScheduledDepartureDescending(){
       addOrderByDescending(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY);
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

    public DispatchPlanRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(DispatchPlan.CREATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(DispatchPlan.CREATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(DispatchPlan.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DispatchPlanRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(DispatchPlan.UPDATED_TIME_PROPERTY);
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