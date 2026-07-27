package com.doublechaintech.enterpriselogisticsservice.driverassignment;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DriverAssignmentRequest<T extends DriverAssignment> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public DriverAssignmentRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public DriverAssignmentRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public DriverAssignmentRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public DriverAssignmentRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public DriverAssignmentRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public DriverAssignmentRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public DriverAssignmentRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (DriverAssignmentRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public DriverAssignmentRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public DriverAssignmentRequest<T> matchingAnyOf(DriverAssignmentRequest driverAssignment){
        super.internalMatchAny(driverAssignment);
        return this;
    }

    public DriverAssignmentRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public DriverAssignmentRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public DriverAssignmentRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public DriverAssignmentRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectVehicleIdOnly().selectDriver().selectStartDate().selectEndDate().selectStatus().selectCreatedAt().selectVersion();
    }

    public DriverAssignmentRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public DriverAssignmentRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectVehicle().selectDriver().selectStartDate().selectEndDate().selectStatus().selectCreatedAt().selectVersion();
    }

    public DriverAssignmentRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectVehicle().selectDriver().selectStartDate().selectEndDate().selectStatus().selectCreatedAt().selectVersion();
    }


    public DriverAssignmentRequest<T> selectId(){
       selectProperty(DriverAssignment.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectId(){
       unselectProperty(DriverAssignment.ID_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectVehicleIdOnly(){
       selectProperty(DriverAssignment.VEHICLE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> selectVehicle(){
        return selectVehicleWith(Q.vehicles().unlimited().selectSelf());
    }

    public DriverAssignmentRequest<T> selectVehicleWith(VehicleRequest vehicle){
       selectProperty(DriverAssignment.VEHICLE_PROPERTY);
       enhanceRelation(DriverAssignment.VEHICLE_PROPERTY, vehicle);
       return this;
    }

    public DriverAssignmentRequest<T> unselectVehicle(){
       unselectProperty(DriverAssignment.VEHICLE_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectDriver(){
       selectProperty(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }

    /**
     * fill the driver with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  driver) to fetch driver property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectDriver(){
       unselectProperty(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectStartDate(){
       selectProperty(DriverAssignment.START_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the startDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startDate) to fetch startDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectStartDate(){
       unselectProperty(DriverAssignment.START_DATE_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectEndDate(){
       selectProperty(DriverAssignment.END_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the endDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endDate) to fetch endDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectEndDate(){
       unselectProperty(DriverAssignment.END_DATE_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectStatus(){
       selectProperty(DriverAssignment.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectStatus(){
       unselectProperty(DriverAssignment.STATUS_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectCreatedAt(){
       selectProperty(DriverAssignment.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectCreatedAt(){
       unselectProperty(DriverAssignment.CREATED_AT_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> selectVersion(){
       selectProperty(DriverAssignment.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DriverAssignmentRequest<T> unselectVersion(){
       unselectProperty(DriverAssignment.VERSION_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.ID_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public DriverAssignmentRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public DriverAssignmentRequest<T> filterByVehicle(Vehicle... vehicle){
      if (vehicle == null || vehicle.length == 0) {
        throw new IllegalArgumentException("filterByVehicle parameter vehicle cannot be empty");
      }
      return appendSearchCriteria(createVehicleCriteria(Operator.EQUAL, (Object[])vehicle));
    }

    public DriverAssignmentRequest<T> withVehicle(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withVehicleIsUnknown(){
       return withVehicle(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withVehicleIsKnown(){
       return withVehicle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.VEHICLE_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> filterByVehicle(Long vehicle){
      if(vehicle == null){
         return this;
      }
      return withVehicle(Operator.EQUAL, vehicle);
    }
    public DriverAssignmentRequest<T> withVehicleMatching(VehicleRequest vehicle){
       return appendSearchCriteria(new SubQuerySearchCriteria(DriverAssignment.VEHICLE_PROPERTY, vehicle, Vehicle.ID_PROPERTY));
    }

    public DriverAssignmentRequest<T> filterByDriver(String... driver){
      if (driver == null || driver.length == 0) {
        throw new IllegalArgumentException("filterByDriver parameter driver cannot be empty");
      }
      return appendSearchCriteria(createDriverCriteria(Operator.EQUAL, (Object[])driver));
    }

    public DriverAssignmentRequest<T> withDriver(Operator operator, Object... values){
       return appendSearchCriteria(createDriverCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withDriverIsUnknown(){
       return withDriver(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withDriverIsKnown(){
       return withDriver(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDriverCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.DRIVER_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withDriverGreaterThan(String driver){
       return withDriver(Operator.GREATER_THAN, driver);
    }

    public DriverAssignmentRequest<T> withDriverGreaterThanOrEqualTo(String driver){
       return withDriver(Operator.GREATER_THAN_OR_EQUAL, driver);
    }

    public DriverAssignmentRequest<T> withDriverLessThan(String driver){
       return withDriver(Operator.LESS_THAN, driver);
    }

    public DriverAssignmentRequest<T> withDriverLessThanOrEqualTo(String driver){
       return withDriver(Operator.LESS_THAN_OR_EQUAL, driver);
    }

    public DriverAssignmentRequest<T> withDriverBetween(String startOfDriver, String endOfDriver){
       return withDriver(Operator.BETWEEN, startOfDriver, endOfDriver);
    }
    public DriverAssignmentRequest<T> withDriverStartingWith(String driver){
       return withDriver(Operator.BEGIN_WITH, driver);
    }
    public DriverAssignmentRequest<T> withDriverContaining(String driver){
       return withDriver(Operator.CONTAIN, driver);
    }

    public DriverAssignmentRequest<T> withDriverEndingWith(String driver){
       return withDriver(Operator.END_WITH, driver);
    }

    public DriverAssignmentRequest<T> withDriverIs(String driver){
       return withDriver(Operator.EQUAL, driver);
    }

    public DriverAssignmentRequest<T> withDriverSoundingLike(String driver){
       return withDriver(Operator.SOUNDS_LIKE, driver);
    }



    public DriverAssignmentRequest<T> filterByStartDate(LocalDate... startDate){
      if (startDate == null || startDate.length == 0) {
        throw new IllegalArgumentException("filterByStartDate parameter startDate cannot be empty");
      }
      return appendSearchCriteria(createStartDateCriteria(Operator.EQUAL, (Object[])startDate));
    }

    public DriverAssignmentRequest<T> withStartDate(Operator operator, Object... values){
       return appendSearchCriteria(createStartDateCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withStartDateIsUnknown(){
       return withStartDate(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withStartDateIsKnown(){
       return withStartDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.START_DATE_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withStartDateGreaterThan(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateGreaterThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN_OR_EQUAL, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateLessThan(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateLessThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN_OR_EQUAL, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateBetween(LocalDate startOfStartDate, LocalDate endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }
    public DriverAssignmentRequest<T> withStartDateBefore(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateBefore(Date startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateAfter(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateAfter(Date startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public DriverAssignmentRequest<T> withStartDateBetween(Date startOfStartDate, Date endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }




    public DriverAssignmentRequest<T> filterByEndDate(LocalDate... endDate){
      if (endDate == null || endDate.length == 0) {
        throw new IllegalArgumentException("filterByEndDate parameter endDate cannot be empty");
      }
      return appendSearchCriteria(createEndDateCriteria(Operator.EQUAL, (Object[])endDate));
    }

    public DriverAssignmentRequest<T> withEndDate(Operator operator, Object... values){
       return appendSearchCriteria(createEndDateCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withEndDateIsUnknown(){
       return withEndDate(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withEndDateIsKnown(){
       return withEndDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.END_DATE_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withEndDateGreaterThan(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateGreaterThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN_OR_EQUAL, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateLessThan(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateLessThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN_OR_EQUAL, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateBetween(LocalDate startOfEndDate, LocalDate endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }
    public DriverAssignmentRequest<T> withEndDateBefore(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateBefore(Date endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateAfter(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateAfter(Date endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public DriverAssignmentRequest<T> withEndDateBetween(Date startOfEndDate, Date endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }




    public DriverAssignmentRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public DriverAssignmentRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.STATUS_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public DriverAssignmentRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public DriverAssignmentRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public DriverAssignmentRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public DriverAssignmentRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public DriverAssignmentRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public DriverAssignmentRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public DriverAssignmentRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public DriverAssignmentRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public DriverAssignmentRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public DriverAssignmentRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public DriverAssignmentRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.CREATED_AT_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public DriverAssignmentRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public DriverAssignmentRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public DriverAssignmentRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public DriverAssignmentRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public DriverAssignmentRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public DriverAssignmentRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DriverAssignment.VERSION_PROPERTY, operator, values);
    }

    public DriverAssignmentRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public DriverAssignmentRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public DriverAssignmentRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public DriverAssignmentRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public DriverAssignmentRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public DriverAssignmentRequest<T> count(){
        super.count();
        return this;
    }
    public DriverAssignmentRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public DriverAssignmentRequest<T> groupByVehicleWithDetails(){
       return groupByVehicleWithDetails(Q.vehicles().unlimited());
    }

    public DriverAssignmentRequest<T> groupByVehicleWithDetails(VehicleRequest subRequest){
       aggregate(DriverAssignment.VEHICLE_PROPERTY, subRequest);
       return this;
    }








    public DriverAssignmentRequest<T> groupById(){
       groupBy(DriverAssignment.ID_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByIdAs(String retName){
       groupBy(retName, DriverAssignment.ID_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.ID_PROPERTY, function);
       return this;
    }
    public DriverAssignmentRequest<T> groupByVehicleWith(VehicleRequest subRequest){
       groupBy(DriverAssignment.VEHICLE_PROPERTY, subRequest);
       return this;
    }
    public DriverAssignmentRequest<T> groupByVehicle(){
       groupBy(DriverAssignment.VEHICLE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByVehicleAs(String retName){
       groupBy(retName, DriverAssignment.VEHICLE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByVehicleWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.VEHICLE_PROPERTY, function);
       return this;
    }

    public DriverAssignmentRequest<T> groupByDriver(){
       groupBy(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByDriverAs(String retName){
       groupBy(retName, DriverAssignment.DRIVER_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByDriverWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.DRIVER_PROPERTY, function);
       return this;
    }

    public DriverAssignmentRequest<T> groupByStartDate(){
       groupBy(DriverAssignment.START_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByStartDateAs(String retName){
       groupBy(retName, DriverAssignment.START_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByStartDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.START_DATE_PROPERTY, function);
       return this;
    }

    public DriverAssignmentRequest<T> groupByEndDate(){
       groupBy(DriverAssignment.END_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByEndDateAs(String retName){
       groupBy(retName, DriverAssignment.END_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByEndDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.END_DATE_PROPERTY, function);
       return this;
    }

    public DriverAssignmentRequest<T> groupByStatus(){
       groupBy(DriverAssignment.STATUS_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByStatusAs(String retName){
       groupBy(retName, DriverAssignment.STATUS_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.STATUS_PROPERTY, function);
       return this;
    }

    public DriverAssignmentRequest<T> groupByCreatedAt(){
       groupBy(DriverAssignment.CREATED_AT_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, DriverAssignment.CREATED_AT_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.CREATED_AT_PROPERTY, function);
       return this;
    }

    public DriverAssignmentRequest<T> groupByVersion(){
       groupBy(DriverAssignment.VERSION_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByVersionAs(String retName){
       groupBy(retName, DriverAssignment.VERSION_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, DriverAssignment.VERSION_PROPERTY, function);
       return this;
    }



    public DriverAssignmentRequest<T> orderByIdAscending(){
       addOrderByAscending(DriverAssignment.ID_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByIdDescending(){
       addOrderByDescending(DriverAssignment.ID_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByVehicleAscending(){
       addOrderByAscending(DriverAssignment.VEHICLE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByVehicleDescending(){
       addOrderByDescending(DriverAssignment.VEHICLE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByDriverAscending(){
       addOrderByAscending(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByDriverDescending(){
       addOrderByDescending(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> orderByDriverAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByDriverDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DriverAssignment.DRIVER_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> orderByStartDateAscending(){
       addOrderByAscending(DriverAssignment.START_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByStartDateDescending(){
       addOrderByDescending(DriverAssignment.START_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByEndDateAscending(){
       addOrderByAscending(DriverAssignment.END_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByEndDateDescending(){
       addOrderByDescending(DriverAssignment.END_DATE_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByStatusAscending(){
       addOrderByAscending(DriverAssignment.STATUS_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByStatusDescending(){
       addOrderByDescending(DriverAssignment.STATUS_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DriverAssignment.STATUS_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DriverAssignment.STATUS_PROPERTY);
       return this;
    }
    public DriverAssignmentRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(DriverAssignment.CREATED_AT_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(DriverAssignment.CREATED_AT_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByVersionAscending(){
       addOrderByAscending(DriverAssignment.VERSION_PROPERTY);
       return this;
    }

    public DriverAssignmentRequest<T> orderByVersionDescending(){
       addOrderByDescending(DriverAssignment.VERSION_PROPERTY);
       return this;
    }


    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }








   public DriverAssignmentRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle){
       return facetByVehicleAs(facetName, vehicle, true);
   }

   public DriverAssignmentRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle, boolean includeAllFacets){
       addFacet(facetName, DriverAssignment.VEHICLE_PROPERTY, vehicle, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public DriverAssignmentRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public DriverAssignmentRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public DriverAssignmentRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public DriverAssignmentRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public DriverAssignmentRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}