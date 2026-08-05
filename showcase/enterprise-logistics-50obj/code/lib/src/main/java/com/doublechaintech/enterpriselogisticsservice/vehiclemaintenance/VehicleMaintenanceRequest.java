package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class VehicleMaintenanceRequest<T extends VehicleMaintenance> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public VehicleMaintenanceRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public VehicleMaintenanceRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public VehicleMaintenanceRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public VehicleMaintenanceRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public VehicleMaintenanceRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public VehicleMaintenanceRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public VehicleMaintenanceRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (VehicleMaintenanceRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public VehicleMaintenanceRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public VehicleMaintenanceRequest<T> matchingAnyOf(VehicleMaintenanceRequest vehicleMaintenance){
        super.internalMatchAny(vehicleMaintenance);
        return this;
    }

    public VehicleMaintenanceRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public VehicleMaintenanceRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public VehicleMaintenanceRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public VehicleMaintenanceRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectServiceType().selectDescription().selectCost().selectScheduledDate().selectCompletedDate().selectStatus().selectVehicleIdOnly().selectVersion();
    }

    public VehicleMaintenanceRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public VehicleMaintenanceRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectServiceType().selectDescription().selectCost().selectScheduledDate().selectCompletedDate().selectStatus().selectVehicle().selectVersion();
    }

    public VehicleMaintenanceRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectServiceType().selectDescription().selectCost().selectScheduledDate().selectCompletedDate().selectStatus().selectVehicle().selectVersion();
    }


    public VehicleMaintenanceRequest<T> selectId(){
       selectProperty(VehicleMaintenance.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectId(){
       unselectProperty(VehicleMaintenance.ID_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectServiceType(){
       selectProperty(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the serviceType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  serviceType) to fetch serviceType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectServiceType(){
       unselectProperty(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectDescription(){
       selectProperty(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectDescription(){
       unselectProperty(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectCost(){
       selectProperty(VehicleMaintenance.COST_PROPERTY);
       return this;
    }

    /**
     * fill the cost with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  cost) to fetch cost property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the cost with customized aggrFunction, TEAQL uses ({aggrFunction}(cost) AS cost to fetch cost property.
     * @param aggrFunction  aggrFunction
     */
    public VehicleMaintenanceRequest<T> selectCost(AggrFunction aggrFunction){
       selectProperty(VehicleMaintenance.COST_PROPERTY, aggrFunction);
       return this;
    }


    public VehicleMaintenanceRequest<T> unselectCost(){
       unselectProperty(VehicleMaintenance.COST_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectScheduledDate(){
       selectProperty(VehicleMaintenance.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the scheduledDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  scheduledDate) to fetch scheduledDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectScheduledDate(){
       unselectProperty(VehicleMaintenance.SCHEDULED_DATE_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectCompletedDate(){
       selectProperty(VehicleMaintenance.COMPLETED_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the completedDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  completedDate) to fetch completedDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectCompletedDate(){
       unselectProperty(VehicleMaintenance.COMPLETED_DATE_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectStatus(){
       selectProperty(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectStatus(){
       unselectProperty(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectVehicleIdOnly(){
       selectProperty(VehicleMaintenance.VEHICLE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> selectVehicle(){
        return selectVehicleWith(Q.vehicles().unlimited().selectSelf());
    }

    public VehicleMaintenanceRequest<T> selectVehicleWith(VehicleRequest vehicle){
       selectProperty(VehicleMaintenance.VEHICLE_PROPERTY);
       enhanceRelation(VehicleMaintenance.VEHICLE_PROPERTY, vehicle);
       return this;
    }

    public VehicleMaintenanceRequest<T> unselectVehicle(){
       unselectProperty(VehicleMaintenance.VEHICLE_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> selectVersion(){
       selectProperty(VehicleMaintenance.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleMaintenanceRequest<T> unselectVersion(){
       unselectProperty(VehicleMaintenance.VERSION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.ID_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public VehicleMaintenanceRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public VehicleMaintenanceRequest<T> filterByServiceType(String... serviceType){
      if (serviceType == null || serviceType.length == 0) {
        throw new IllegalArgumentException("filterByServiceType parameter serviceType cannot be empty");
      }
      return appendSearchCriteria(createServiceTypeCriteria(Operator.EQUAL, (Object[])serviceType));
    }

    public VehicleMaintenanceRequest<T> withServiceType(Operator operator, Object... values){
       return appendSearchCriteria(createServiceTypeCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withServiceTypeIsUnknown(){
       return withServiceType(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeIsKnown(){
       return withServiceType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createServiceTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.SERVICE_TYPE_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeGreaterThan(String serviceType){
       return withServiceType(Operator.GREATER_THAN, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeGreaterThanOrEqualTo(String serviceType){
       return withServiceType(Operator.GREATER_THAN_OR_EQUAL, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeLessThan(String serviceType){
       return withServiceType(Operator.LESS_THAN, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeLessThanOrEqualTo(String serviceType){
       return withServiceType(Operator.LESS_THAN_OR_EQUAL, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeBetween(String startOfServiceType, String endOfServiceType){
       return withServiceType(Operator.BETWEEN, startOfServiceType, endOfServiceType);
    }
    public VehicleMaintenanceRequest<T> withServiceTypeStartingWith(String serviceType){
       return withServiceType(Operator.BEGIN_WITH, serviceType);
    }
    public VehicleMaintenanceRequest<T> withServiceTypeContaining(String serviceType){
       return withServiceType(Operator.CONTAIN, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeEndingWith(String serviceType){
       return withServiceType(Operator.END_WITH, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeIs(String serviceType){
       return withServiceType(Operator.EQUAL, serviceType);
    }

    public VehicleMaintenanceRequest<T> withServiceTypeSoundingLike(String serviceType){
       return withServiceType(Operator.SOUNDS_LIKE, serviceType);
    }



    public VehicleMaintenanceRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public VehicleMaintenanceRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.DESCRIPTION_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public VehicleMaintenanceRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public VehicleMaintenanceRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public VehicleMaintenanceRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public VehicleMaintenanceRequest<T> filterByCost(BigDecimal... cost){
      if (cost == null || cost.length == 0) {
        throw new IllegalArgumentException("filterByCost parameter cost cannot be empty");
      }
      return appendSearchCriteria(createCostCriteria(Operator.EQUAL, (Object[])cost));
    }

    public VehicleMaintenanceRequest<T> withCost(Operator operator, Object... values){
       return appendSearchCriteria(createCostCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withCostIsUnknown(){
       return withCost(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withCostIsKnown(){
       return withCost(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCostCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.COST_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withCostGreaterThan(BigDecimal cost){
       return withCost(Operator.GREATER_THAN, cost);
    }

    public VehicleMaintenanceRequest<T> withCostGreaterThanOrEqualTo(BigDecimal cost){
       return withCost(Operator.GREATER_THAN_OR_EQUAL, cost);
    }

    public VehicleMaintenanceRequest<T> withCostLessThan(BigDecimal cost){
       return withCost(Operator.LESS_THAN, cost);
    }

    public VehicleMaintenanceRequest<T> withCostLessThanOrEqualTo(BigDecimal cost){
       return withCost(Operator.LESS_THAN_OR_EQUAL, cost);
    }

    public VehicleMaintenanceRequest<T> withCostBetween(BigDecimal startOfCost, BigDecimal endOfCost){
       return withCost(Operator.BETWEEN, startOfCost, endOfCost);
    }



    public VehicleMaintenanceRequest<T> filterByScheduledDate(LocalDate... scheduledDate){
      if (scheduledDate == null || scheduledDate.length == 0) {
        throw new IllegalArgumentException("filterByScheduledDate parameter scheduledDate cannot be empty");
      }
      return appendSearchCriteria(createScheduledDateCriteria(Operator.EQUAL, (Object[])scheduledDate));
    }

    public VehicleMaintenanceRequest<T> withScheduledDate(Operator operator, Object... values){
       return appendSearchCriteria(createScheduledDateCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withScheduledDateIsUnknown(){
       return withScheduledDate(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateIsKnown(){
       return withScheduledDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createScheduledDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.SCHEDULED_DATE_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateGreaterThan(LocalDate scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateGreaterThanOrEqualTo(LocalDate scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN_OR_EQUAL, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateLessThan(LocalDate scheduledDate){
       return withScheduledDate(Operator.LESS_THAN, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateLessThanOrEqualTo(LocalDate scheduledDate){
       return withScheduledDate(Operator.LESS_THAN_OR_EQUAL, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateBetween(LocalDate startOfScheduledDate, LocalDate endOfScheduledDate){
       return withScheduledDate(Operator.BETWEEN, startOfScheduledDate, endOfScheduledDate);
    }
    public VehicleMaintenanceRequest<T> withScheduledDateBefore(LocalDate scheduledDate){
       return withScheduledDate(Operator.LESS_THAN, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateBefore(Date scheduledDate){
       return withScheduledDate(Operator.LESS_THAN, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateAfter(LocalDate scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateAfter(Date scheduledDate){
       return withScheduledDate(Operator.GREATER_THAN, scheduledDate);
    }

    public VehicleMaintenanceRequest<T> withScheduledDateBetween(Date startOfScheduledDate, Date endOfScheduledDate){
       return withScheduledDate(Operator.BETWEEN, startOfScheduledDate, endOfScheduledDate);
    }




    public VehicleMaintenanceRequest<T> filterByCompletedDate(LocalDate... completedDate){
      if (completedDate == null || completedDate.length == 0) {
        throw new IllegalArgumentException("filterByCompletedDate parameter completedDate cannot be empty");
      }
      return appendSearchCriteria(createCompletedDateCriteria(Operator.EQUAL, (Object[])completedDate));
    }

    public VehicleMaintenanceRequest<T> withCompletedDate(Operator operator, Object... values){
       return appendSearchCriteria(createCompletedDateCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withCompletedDateIsUnknown(){
       return withCompletedDate(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateIsKnown(){
       return withCompletedDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCompletedDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.COMPLETED_DATE_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateGreaterThan(LocalDate completedDate){
       return withCompletedDate(Operator.GREATER_THAN, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateGreaterThanOrEqualTo(LocalDate completedDate){
       return withCompletedDate(Operator.GREATER_THAN_OR_EQUAL, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateLessThan(LocalDate completedDate){
       return withCompletedDate(Operator.LESS_THAN, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateLessThanOrEqualTo(LocalDate completedDate){
       return withCompletedDate(Operator.LESS_THAN_OR_EQUAL, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateBetween(LocalDate startOfCompletedDate, LocalDate endOfCompletedDate){
       return withCompletedDate(Operator.BETWEEN, startOfCompletedDate, endOfCompletedDate);
    }
    public VehicleMaintenanceRequest<T> withCompletedDateBefore(LocalDate completedDate){
       return withCompletedDate(Operator.LESS_THAN, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateBefore(Date completedDate){
       return withCompletedDate(Operator.LESS_THAN, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateAfter(LocalDate completedDate){
       return withCompletedDate(Operator.GREATER_THAN, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateAfter(Date completedDate){
       return withCompletedDate(Operator.GREATER_THAN, completedDate);
    }

    public VehicleMaintenanceRequest<T> withCompletedDateBetween(Date startOfCompletedDate, Date endOfCompletedDate){
       return withCompletedDate(Operator.BETWEEN, startOfCompletedDate, endOfCompletedDate);
    }




    public VehicleMaintenanceRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public VehicleMaintenanceRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.STATUS_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public VehicleMaintenanceRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public VehicleMaintenanceRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public VehicleMaintenanceRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public VehicleMaintenanceRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public VehicleMaintenanceRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public VehicleMaintenanceRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public VehicleMaintenanceRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public VehicleMaintenanceRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public VehicleMaintenanceRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public VehicleMaintenanceRequest<T> filterByVehicle(Vehicle... vehicle){
      if (vehicle == null || vehicle.length == 0) {
        throw new IllegalArgumentException("filterByVehicle parameter vehicle cannot be empty");
      }
      return appendSearchCriteria(createVehicleCriteria(Operator.EQUAL, (Object[])vehicle));
    }

    public VehicleMaintenanceRequest<T> withVehicle(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withVehicleIsUnknown(){
       return withVehicle(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withVehicleIsKnown(){
       return withVehicle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.VEHICLE_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> filterByVehicle(Long vehicle){
      if(vehicle == null){
         return this;
      }
      return withVehicle(Operator.EQUAL, vehicle);
    }
    public VehicleMaintenanceRequest<T> withVehicleMatching(VehicleRequest vehicle){
       return appendSearchCriteria(new SubQuerySearchCriteria(VehicleMaintenance.VEHICLE_PROPERTY, vehicle, Vehicle.ID_PROPERTY));
    }

    public VehicleMaintenanceRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public VehicleMaintenanceRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public VehicleMaintenanceRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public VehicleMaintenanceRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(VehicleMaintenance.VERSION_PROPERTY, operator, values);
    }

    public VehicleMaintenanceRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public VehicleMaintenanceRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public VehicleMaintenanceRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public VehicleMaintenanceRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public VehicleMaintenanceRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public VehicleMaintenanceRequest<T> count(){
        super.count();
        return this;
    }
    public VehicleMaintenanceRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public VehicleMaintenanceRequest minCost(){
        return minCostAs(prefix("minOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest minCostAs(String retName){
        super.min(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest maxCost(){
        return maxCostAs(prefix("maxOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest maxCostAs(String retName){
        super.max(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest sumCost(){
        return sumCostAs(prefix("sumOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest sumCostAs(String retName){
        super.sum(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest avgCost(){
        return avgCostAs(prefix("avgOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest avgCostAs(String retName){
        super.avg(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest standardDeviationCost(){
        return standardDeviationCostAs(prefix("standardDeviationOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest standardDeviationCostAs(String retName){
        super.standardDeviation(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest squareRootOfPopulationStandardDeviationCost(){
        return squareRootOfPopulationStandardDeviationCostAs(prefix("squareRootOfPopulationStandardDeviationOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest squareRootOfPopulationStandardDeviationCostAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest sampleVarianceCost(){
        return sampleVarianceCostAs(prefix("sampleVarianceOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest sampleVarianceCostAs(String retName){
        super.sampleVariance(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest samplePopulationVarianceCost(){
        return samplePopulationVarianceCostAs(prefix("samplePopulationVarianceOf",VehicleMaintenance.COST_PROPERTY));
    }

    public VehicleMaintenanceRequest samplePopulationVarianceCostAs(String retName){
        super.samplePopulationVariance(retName, VehicleMaintenance.COST_PROPERTY);
        return this;
    }
    public VehicleMaintenanceRequest<T> groupByVehicleWithDetails(){
       return groupByVehicleWithDetails(Q.vehicles().unlimited());
    }

    public VehicleMaintenanceRequest<T> groupByVehicleWithDetails(VehicleRequest subRequest){
       aggregate(VehicleMaintenance.VEHICLE_PROPERTY, subRequest);
       return this;
    }



    public VehicleMaintenanceRequest<T> groupById(){
       groupBy(VehicleMaintenance.ID_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByIdAs(String retName){
       groupBy(retName, VehicleMaintenance.ID_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.ID_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByServiceType(){
       groupBy(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByServiceTypeAs(String retName){
       groupBy(retName, VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByServiceTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.SERVICE_TYPE_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByDescription(){
       groupBy(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByCost(){
       groupBy(VehicleMaintenance.COST_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByCostAs(String retName){
       groupBy(retName, VehicleMaintenance.COST_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByCostWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.COST_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByScheduledDate(){
       groupBy(VehicleMaintenance.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByScheduledDateAs(String retName){
       groupBy(retName, VehicleMaintenance.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByScheduledDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.SCHEDULED_DATE_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByCompletedDate(){
       groupBy(VehicleMaintenance.COMPLETED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByCompletedDateAs(String retName){
       groupBy(retName, VehicleMaintenance.COMPLETED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByCompletedDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.COMPLETED_DATE_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByStatus(){
       groupBy(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByStatusAs(String retName){
       groupBy(retName, VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.STATUS_PROPERTY, function);
       return this;
    }
    public VehicleMaintenanceRequest<T> groupByVehicleWith(VehicleRequest subRequest){
       groupBy(VehicleMaintenance.VEHICLE_PROPERTY, subRequest);
       return this;
    }
    public VehicleMaintenanceRequest<T> groupByVehicle(){
       groupBy(VehicleMaintenance.VEHICLE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByVehicleAs(String retName){
       groupBy(retName, VehicleMaintenance.VEHICLE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByVehicleWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.VEHICLE_PROPERTY, function);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByVersion(){
       groupBy(VehicleMaintenance.VERSION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByVersionAs(String retName){
       groupBy(retName, VehicleMaintenance.VERSION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, VehicleMaintenance.VERSION_PROPERTY, function);
       return this;
    }



    public VehicleMaintenanceRequest<T> orderByIdAscending(){
       addOrderByAscending(VehicleMaintenance.ID_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByIdDescending(){
       addOrderByDescending(VehicleMaintenance.ID_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByServiceTypeAscending(){
       addOrderByAscending(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByServiceTypeDescending(){
       addOrderByDescending(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> orderByServiceTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByServiceTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(VehicleMaintenance.SERVICE_TYPE_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(VehicleMaintenance.DESCRIPTION_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> orderByCostAscending(){
       addOrderByAscending(VehicleMaintenance.COST_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByCostDescending(){
       addOrderByDescending(VehicleMaintenance.COST_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByScheduledDateAscending(){
       addOrderByAscending(VehicleMaintenance.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByScheduledDateDescending(){
       addOrderByDescending(VehicleMaintenance.SCHEDULED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByCompletedDateAscending(){
       addOrderByAscending(VehicleMaintenance.COMPLETED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByCompletedDateDescending(){
       addOrderByDescending(VehicleMaintenance.COMPLETED_DATE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByStatusAscending(){
       addOrderByAscending(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByStatusDescending(){
       addOrderByDescending(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(VehicleMaintenance.STATUS_PROPERTY);
       return this;
    }
    public VehicleMaintenanceRequest<T> orderByVehicleAscending(){
       addOrderByAscending(VehicleMaintenance.VEHICLE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByVehicleDescending(){
       addOrderByDescending(VehicleMaintenance.VEHICLE_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByVersionAscending(){
       addOrderByAscending(VehicleMaintenance.VERSION_PROPERTY);
       return this;
    }

    public VehicleMaintenanceRequest<T> orderByVersionDescending(){
       addOrderByDescending(VehicleMaintenance.VERSION_PROPERTY);
       return this;
    }


    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }



   public VehicleMaintenanceRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle){
       return facetByVehicleAs(facetName, vehicle, true);
   }

   public VehicleMaintenanceRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle, boolean includeAllFacets){
       addFacet(facetName, VehicleMaintenance.VEHICLE_PROPERTY, vehicle, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public VehicleMaintenanceRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public VehicleMaintenanceRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public VehicleMaintenanceRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public VehicleMaintenanceRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public VehicleMaintenanceRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}