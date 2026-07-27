package com.doublechaintech.enterpriselogisticsservice.vehicle;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentRequest;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogRequest;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogRequest;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceRequest;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class VehicleRequest<T extends Vehicle> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public VehicleRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public VehicleRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public VehicleRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public VehicleRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public VehicleRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public VehicleRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public VehicleRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (VehicleRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public VehicleRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public VehicleRequest<T> matchingAnyOf(VehicleRequest vehicle){
        super.internalMatchAny(vehicle);
        return this;
    }

    public VehicleRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public VehicleRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public VehicleRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public VehicleRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectLicensePlate().selectMake().selectModel().selectYear().selectCapacityKg().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public VehicleRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public VehicleRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectLicensePlate().selectMake().selectModel().selectYear().selectCapacityKg().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public VehicleRequest<T> selectChildren(){
        super.selectAny();
        selectDispatchPlanList().selectDriverAssignmentList().selectGpsLogList().selectFuelLogList().selectVehicleMaintenanceList().selectTelematicsDeviceList();
        return selectId().selectName().selectLicensePlate().selectMake().selectModel().selectYear().selectCapacityKg().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public VehicleRequest<T> selectId(){
       selectProperty(Vehicle.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectId(){
       unselectProperty(Vehicle.ID_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectName(){
       selectProperty(Vehicle.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectName(){
       unselectProperty(Vehicle.NAME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectLicensePlate(){
       selectProperty(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }

    /**
     * fill the licensePlate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  licensePlate) to fetch licensePlate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectLicensePlate(){
       unselectProperty(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectMake(){
       selectProperty(Vehicle.MAKE_PROPERTY);
       return this;
    }

    /**
     * fill the make with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  make) to fetch make property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectMake(){
       unselectProperty(Vehicle.MAKE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectModel(){
       selectProperty(Vehicle.MODEL_PROPERTY);
       return this;
    }

    /**
     * fill the model with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  model) to fetch model property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectModel(){
       unselectProperty(Vehicle.MODEL_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectYear(){
       selectProperty(Vehicle.YEAR_PROPERTY);
       return this;
    }

    /**
     * fill the year with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  year) to fetch year property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the year with customized aggrFunction, TEAQL uses ({aggrFunction}(year) AS year to fetch year property.
     * @param aggrFunction  aggrFunction
     */
    public VehicleRequest<T> selectYear(AggrFunction aggrFunction){
       selectProperty(Vehicle.YEAR_PROPERTY, aggrFunction);
       return this;
    }


    public VehicleRequest<T> unselectYear(){
       unselectProperty(Vehicle.YEAR_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectCapacityKg(){
       selectProperty(Vehicle.CAPACITY_KG_PROPERTY);
       return this;
    }

    /**
     * fill the capacityKg with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  capacityKg) to fetch capacityKg property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the capacityKg with customized aggrFunction, TEAQL uses ({aggrFunction}(capacityKg) AS capacityKg to fetch capacityKg property.
     * @param aggrFunction  aggrFunction
     */
    public VehicleRequest<T> selectCapacityKg(AggrFunction aggrFunction){
       selectProperty(Vehicle.CAPACITY_KG_PROPERTY, aggrFunction);
       return this;
    }


    public VehicleRequest<T> unselectCapacityKg(){
       unselectProperty(Vehicle.CAPACITY_KG_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectStatus(){
       selectProperty(Vehicle.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectStatus(){
       unselectProperty(Vehicle.STATUS_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectCreatedAt(){
       selectProperty(Vehicle.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectCreatedAt(){
       unselectProperty(Vehicle.CREATED_AT_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectUpdatedAt(){
       selectProperty(Vehicle.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectUpdatedAt(){
       unselectProperty(Vehicle.UPDATED_AT_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectVersion(){
       selectProperty(Vehicle.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectVersion(){
       unselectProperty(Vehicle.VERSION_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectDispatchPlanList(){
       return selectDispatchPlanListWith(Q.dispatchPlans().selectSelf());
    }

    public VehicleRequest<T> selectDispatchPlanListWith(DispatchPlanRequest dispatchPlanList){
       enhanceRelation(Vehicle.DISPATCH_PLAN_LIST_PROPERTY, dispatchPlanList);
       return this;
    }
    public VehicleRequest<T> selectDriverAssignmentList(){
       return selectDriverAssignmentListWith(Q.driverAssignments().selectSelf());
    }

    public VehicleRequest<T> selectDriverAssignmentListWith(DriverAssignmentRequest driverAssignmentList){
       enhanceRelation(Vehicle.DRIVER_ASSIGNMENT_LIST_PROPERTY, driverAssignmentList);
       return this;
    }
    public VehicleRequest<T> selectGpsLogList(){
       return selectGpsLogListWith(Q.gpsLogs().selectSelf());
    }

    public VehicleRequest<T> selectGpsLogListWith(GpsLogRequest gpsLogList){
       enhanceRelation(Vehicle.GPS_LOG_LIST_PROPERTY, gpsLogList);
       return this;
    }
    public VehicleRequest<T> selectFuelLogList(){
       return selectFuelLogListWith(Q.fuelLogs().selectSelf());
    }

    public VehicleRequest<T> selectFuelLogListWith(FuelLogRequest fuelLogList){
       enhanceRelation(Vehicle.FUEL_LOG_LIST_PROPERTY, fuelLogList);
       return this;
    }
    public VehicleRequest<T> selectVehicleMaintenanceList(){
       return selectVehicleMaintenanceListWith(Q.vehicleMaintenances().selectSelf());
    }

    public VehicleRequest<T> selectVehicleMaintenanceListWith(VehicleMaintenanceRequest vehicleMaintenanceList){
       enhanceRelation(Vehicle.VEHICLE_MAINTENANCE_LIST_PROPERTY, vehicleMaintenanceList);
       return this;
    }
    public VehicleRequest<T> selectTelematicsDeviceList(){
       return selectTelematicsDeviceListWith(Q.telematicsDevices().selectSelf());
    }

    public VehicleRequest<T> selectTelematicsDeviceListWith(TelematicsDeviceRequest telematicsDeviceList){
       enhanceRelation(Vehicle.TELEMATICS_DEVICE_LIST_PROPERTY, telematicsDeviceList);
       return this;
    }

    public VehicleRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.ID_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public VehicleRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public VehicleRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public VehicleRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public VehicleRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public VehicleRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.NAME_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public VehicleRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public VehicleRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public VehicleRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public VehicleRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public VehicleRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public VehicleRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public VehicleRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public VehicleRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public VehicleRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public VehicleRequest<T> filterByLicensePlate(String... licensePlate){
      if (licensePlate == null || licensePlate.length == 0) {
        throw new IllegalArgumentException("filterByLicensePlate parameter licensePlate cannot be empty");
      }
      return appendSearchCriteria(createLicensePlateCriteria(Operator.EQUAL, (Object[])licensePlate));
    }

    public VehicleRequest<T> withLicensePlate(Operator operator, Object... values){
       return appendSearchCriteria(createLicensePlateCriteria(operator, values));
    }

    public VehicleRequest<T> withLicensePlateIsUnknown(){
       return withLicensePlate(Operator.IS_NULL);
    }

    public VehicleRequest<T> withLicensePlateIsKnown(){
       return withLicensePlate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLicensePlateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.LICENSE_PLATE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withLicensePlateGreaterThan(String licensePlate){
       return withLicensePlate(Operator.GREATER_THAN, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateGreaterThanOrEqualTo(String licensePlate){
       return withLicensePlate(Operator.GREATER_THAN_OR_EQUAL, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateLessThan(String licensePlate){
       return withLicensePlate(Operator.LESS_THAN, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateLessThanOrEqualTo(String licensePlate){
       return withLicensePlate(Operator.LESS_THAN_OR_EQUAL, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateBetween(String startOfLicensePlate, String endOfLicensePlate){
       return withLicensePlate(Operator.BETWEEN, startOfLicensePlate, endOfLicensePlate);
    }
    public VehicleRequest<T> withLicensePlateStartingWith(String licensePlate){
       return withLicensePlate(Operator.BEGIN_WITH, licensePlate);
    }
    public VehicleRequest<T> withLicensePlateContaining(String licensePlate){
       return withLicensePlate(Operator.CONTAIN, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateEndingWith(String licensePlate){
       return withLicensePlate(Operator.END_WITH, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateIs(String licensePlate){
       return withLicensePlate(Operator.EQUAL, licensePlate);
    }

    public VehicleRequest<T> withLicensePlateSoundingLike(String licensePlate){
       return withLicensePlate(Operator.SOUNDS_LIKE, licensePlate);
    }



    public VehicleRequest<T> filterByMake(String... make){
      if (make == null || make.length == 0) {
        throw new IllegalArgumentException("filterByMake parameter make cannot be empty");
      }
      return appendSearchCriteria(createMakeCriteria(Operator.EQUAL, (Object[])make));
    }

    public VehicleRequest<T> withMake(Operator operator, Object... values){
       return appendSearchCriteria(createMakeCriteria(operator, values));
    }

    public VehicleRequest<T> withMakeIsUnknown(){
       return withMake(Operator.IS_NULL);
    }

    public VehicleRequest<T> withMakeIsKnown(){
       return withMake(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMakeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.MAKE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withMakeGreaterThan(String make){
       return withMake(Operator.GREATER_THAN, make);
    }

    public VehicleRequest<T> withMakeGreaterThanOrEqualTo(String make){
       return withMake(Operator.GREATER_THAN_OR_EQUAL, make);
    }

    public VehicleRequest<T> withMakeLessThan(String make){
       return withMake(Operator.LESS_THAN, make);
    }

    public VehicleRequest<T> withMakeLessThanOrEqualTo(String make){
       return withMake(Operator.LESS_THAN_OR_EQUAL, make);
    }

    public VehicleRequest<T> withMakeBetween(String startOfMake, String endOfMake){
       return withMake(Operator.BETWEEN, startOfMake, endOfMake);
    }
    public VehicleRequest<T> withMakeStartingWith(String make){
       return withMake(Operator.BEGIN_WITH, make);
    }
    public VehicleRequest<T> withMakeContaining(String make){
       return withMake(Operator.CONTAIN, make);
    }

    public VehicleRequest<T> withMakeEndingWith(String make){
       return withMake(Operator.END_WITH, make);
    }

    public VehicleRequest<T> withMakeIs(String make){
       return withMake(Operator.EQUAL, make);
    }

    public VehicleRequest<T> withMakeSoundingLike(String make){
       return withMake(Operator.SOUNDS_LIKE, make);
    }



    public VehicleRequest<T> filterByModel(String... model){
      if (model == null || model.length == 0) {
        throw new IllegalArgumentException("filterByModel parameter model cannot be empty");
      }
      return appendSearchCriteria(createModelCriteria(Operator.EQUAL, (Object[])model));
    }

    public VehicleRequest<T> withModel(Operator operator, Object... values){
       return appendSearchCriteria(createModelCriteria(operator, values));
    }

    public VehicleRequest<T> withModelIsUnknown(){
       return withModel(Operator.IS_NULL);
    }

    public VehicleRequest<T> withModelIsKnown(){
       return withModel(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createModelCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.MODEL_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withModelGreaterThan(String model){
       return withModel(Operator.GREATER_THAN, model);
    }

    public VehicleRequest<T> withModelGreaterThanOrEqualTo(String model){
       return withModel(Operator.GREATER_THAN_OR_EQUAL, model);
    }

    public VehicleRequest<T> withModelLessThan(String model){
       return withModel(Operator.LESS_THAN, model);
    }

    public VehicleRequest<T> withModelLessThanOrEqualTo(String model){
       return withModel(Operator.LESS_THAN_OR_EQUAL, model);
    }

    public VehicleRequest<T> withModelBetween(String startOfModel, String endOfModel){
       return withModel(Operator.BETWEEN, startOfModel, endOfModel);
    }
    public VehicleRequest<T> withModelStartingWith(String model){
       return withModel(Operator.BEGIN_WITH, model);
    }
    public VehicleRequest<T> withModelContaining(String model){
       return withModel(Operator.CONTAIN, model);
    }

    public VehicleRequest<T> withModelEndingWith(String model){
       return withModel(Operator.END_WITH, model);
    }

    public VehicleRequest<T> withModelIs(String model){
       return withModel(Operator.EQUAL, model);
    }

    public VehicleRequest<T> withModelSoundingLike(String model){
       return withModel(Operator.SOUNDS_LIKE, model);
    }



    public VehicleRequest<T> filterByYear(Integer... year){
      if (year == null || year.length == 0) {
        throw new IllegalArgumentException("filterByYear parameter year cannot be empty");
      }
      return appendSearchCriteria(createYearCriteria(Operator.EQUAL, (Object[])year));
    }

    public VehicleRequest<T> withYear(Operator operator, Object... values){
       return appendSearchCriteria(createYearCriteria(operator, values));
    }

    public VehicleRequest<T> withYearIsUnknown(){
       return withYear(Operator.IS_NULL);
    }

    public VehicleRequest<T> withYearIsKnown(){
       return withYear(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createYearCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.YEAR_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withYearGreaterThan(Integer year){
       return withYear(Operator.GREATER_THAN, year);
    }

    public VehicleRequest<T> withYearGreaterThanOrEqualTo(Integer year){
       return withYear(Operator.GREATER_THAN_OR_EQUAL, year);
    }

    public VehicleRequest<T> withYearLessThan(Integer year){
       return withYear(Operator.LESS_THAN, year);
    }

    public VehicleRequest<T> withYearLessThanOrEqualTo(Integer year){
       return withYear(Operator.LESS_THAN_OR_EQUAL, year);
    }

    public VehicleRequest<T> withYearBetween(Integer startOfYear, Integer endOfYear){
       return withYear(Operator.BETWEEN, startOfYear, endOfYear);
    }



    public VehicleRequest<T> filterByCapacityKg(BigDecimal... capacityKg){
      if (capacityKg == null || capacityKg.length == 0) {
        throw new IllegalArgumentException("filterByCapacityKg parameter capacityKg cannot be empty");
      }
      return appendSearchCriteria(createCapacityKgCriteria(Operator.EQUAL, (Object[])capacityKg));
    }

    public VehicleRequest<T> withCapacityKg(Operator operator, Object... values){
       return appendSearchCriteria(createCapacityKgCriteria(operator, values));
    }

    public VehicleRequest<T> withCapacityKgIsUnknown(){
       return withCapacityKg(Operator.IS_NULL);
    }

    public VehicleRequest<T> withCapacityKgIsKnown(){
       return withCapacityKg(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCapacityKgCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.CAPACITY_KG_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withCapacityKgGreaterThan(BigDecimal capacityKg){
       return withCapacityKg(Operator.GREATER_THAN, capacityKg);
    }

    public VehicleRequest<T> withCapacityKgGreaterThanOrEqualTo(BigDecimal capacityKg){
       return withCapacityKg(Operator.GREATER_THAN_OR_EQUAL, capacityKg);
    }

    public VehicleRequest<T> withCapacityKgLessThan(BigDecimal capacityKg){
       return withCapacityKg(Operator.LESS_THAN, capacityKg);
    }

    public VehicleRequest<T> withCapacityKgLessThanOrEqualTo(BigDecimal capacityKg){
       return withCapacityKg(Operator.LESS_THAN_OR_EQUAL, capacityKg);
    }

    public VehicleRequest<T> withCapacityKgBetween(BigDecimal startOfCapacityKg, BigDecimal endOfCapacityKg){
       return withCapacityKg(Operator.BETWEEN, startOfCapacityKg, endOfCapacityKg);
    }



    public VehicleRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public VehicleRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public VehicleRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public VehicleRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.STATUS_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public VehicleRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public VehicleRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public VehicleRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public VehicleRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public VehicleRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public VehicleRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public VehicleRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public VehicleRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public VehicleRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public VehicleRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public VehicleRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public VehicleRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public VehicleRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.CREATED_AT_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public VehicleRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public VehicleRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public VehicleRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public VehicleRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public VehicleRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public VehicleRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public VehicleRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public VehicleRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public VehicleRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public VehicleRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public VehicleRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public VehicleRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public VehicleRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.UPDATED_AT_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public VehicleRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public VehicleRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public VehicleRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public VehicleRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public VehicleRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public VehicleRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.VERSION_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public VehicleRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public VehicleRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public VehicleRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public VehicleRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public VehicleRequest<T> withDispatchPlanListMatching(DispatchPlanRequest dispatchPlanRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, dispatchPlanRequest, DispatchPlan.VEHICLE_PROPERTY));
    }

    public VehicleRequest<T> withoutDispatchPlanListMatching(DispatchPlanRequest dispatchPlanRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, dispatchPlanRequest, DispatchPlan.VEHICLE_PROPERTY)));
    }

    public VehicleRequest<T> haveDispatchPlans(){
        return withDispatchPlanListMatching(Q.dispatchPlans().unlimited());
    }

    public VehicleRequest<T> haveNoDispatchPlans(){
        return withoutDispatchPlanListMatching(Q.dispatchPlans().unlimited());
    }
    public VehicleRequest<T> withDriverAssignmentListMatching(DriverAssignmentRequest driverAssignmentRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, driverAssignmentRequest, DriverAssignment.VEHICLE_PROPERTY));
    }

    public VehicleRequest<T> withoutDriverAssignmentListMatching(DriverAssignmentRequest driverAssignmentRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, driverAssignmentRequest, DriverAssignment.VEHICLE_PROPERTY)));
    }

    public VehicleRequest<T> haveDriverAssignments(){
        return withDriverAssignmentListMatching(Q.driverAssignments().unlimited());
    }

    public VehicleRequest<T> haveNoDriverAssignments(){
        return withoutDriverAssignmentListMatching(Q.driverAssignments().unlimited());
    }
    public VehicleRequest<T> withGpsLogListMatching(GpsLogRequest gpsLogRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, gpsLogRequest, GpsLog.VEHICLE_PROPERTY));
    }

    public VehicleRequest<T> withoutGpsLogListMatching(GpsLogRequest gpsLogRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, gpsLogRequest, GpsLog.VEHICLE_PROPERTY)));
    }

    public VehicleRequest<T> haveGpsLogs(){
        return withGpsLogListMatching(Q.gpsLogs().unlimited());
    }

    public VehicleRequest<T> haveNoGpsLogs(){
        return withoutGpsLogListMatching(Q.gpsLogs().unlimited());
    }
    public VehicleRequest<T> withFuelLogListMatching(FuelLogRequest fuelLogRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, fuelLogRequest, FuelLog.VEHICLE_PROPERTY));
    }

    public VehicleRequest<T> withoutFuelLogListMatching(FuelLogRequest fuelLogRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, fuelLogRequest, FuelLog.VEHICLE_PROPERTY)));
    }

    public VehicleRequest<T> haveFuelLogs(){
        return withFuelLogListMatching(Q.fuelLogs().unlimited());
    }

    public VehicleRequest<T> haveNoFuelLogs(){
        return withoutFuelLogListMatching(Q.fuelLogs().unlimited());
    }
    public VehicleRequest<T> withVehicleMaintenanceListMatching(VehicleMaintenanceRequest vehicleMaintenanceRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, vehicleMaintenanceRequest, VehicleMaintenance.VEHICLE_PROPERTY));
    }

    public VehicleRequest<T> withoutVehicleMaintenanceListMatching(VehicleMaintenanceRequest vehicleMaintenanceRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, vehicleMaintenanceRequest, VehicleMaintenance.VEHICLE_PROPERTY)));
    }

    public VehicleRequest<T> haveVehicleMaintenances(){
        return withVehicleMaintenanceListMatching(Q.vehicleMaintenances().unlimited());
    }

    public VehicleRequest<T> haveNoVehicleMaintenances(){
        return withoutVehicleMaintenanceListMatching(Q.vehicleMaintenances().unlimited());
    }
    public VehicleRequest<T> withTelematicsDeviceListMatching(TelematicsDeviceRequest telematicsDeviceRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, telematicsDeviceRequest, TelematicsDevice.VEHICLE_PROPERTY));
    }

    public VehicleRequest<T> withoutTelematicsDeviceListMatching(TelematicsDeviceRequest telematicsDeviceRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Vehicle.ID_PROPERTY, telematicsDeviceRequest, TelematicsDevice.VEHICLE_PROPERTY)));
    }

    public VehicleRequest<T> haveTelematicsDevices(){
        return withTelematicsDeviceListMatching(Q.telematicsDevices().unlimited());
    }

    public VehicleRequest<T> haveNoTelematicsDevices(){
        return withoutTelematicsDeviceListMatching(Q.telematicsDevices().unlimited());
    }

    public VehicleRequest<T> count(){
        super.count();
        return this;
    }
    public VehicleRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public VehicleRequest minYear(){
        return minYearAs(prefix("minOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest minYearAs(String retName){
        super.min(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest maxYear(){
        return maxYearAs(prefix("maxOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest maxYearAs(String retName){
        super.max(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest sumYear(){
        return sumYearAs(prefix("sumOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest sumYearAs(String retName){
        super.sum(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest avgYear(){
        return avgYearAs(prefix("avgOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest avgYearAs(String retName){
        super.avg(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest standardDeviationYear(){
        return standardDeviationYearAs(prefix("standardDeviationOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest standardDeviationYearAs(String retName){
        super.standardDeviation(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest squareRootOfPopulationStandardDeviationYear(){
        return squareRootOfPopulationStandardDeviationYearAs(prefix("squareRootOfPopulationStandardDeviationOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest squareRootOfPopulationStandardDeviationYearAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest sampleVarianceYear(){
        return sampleVarianceYearAs(prefix("sampleVarianceOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest sampleVarianceYearAs(String retName){
        super.sampleVariance(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest samplePopulationVarianceYear(){
        return samplePopulationVarianceYearAs(prefix("samplePopulationVarianceOf",Vehicle.YEAR_PROPERTY));
    }

    public VehicleRequest samplePopulationVarianceYearAs(String retName){
        super.samplePopulationVariance(retName, Vehicle.YEAR_PROPERTY);
        return this;
    }
    public VehicleRequest minCapacityKg(){
        return minCapacityKgAs(prefix("minOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest minCapacityKgAs(String retName){
        super.min(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest maxCapacityKg(){
        return maxCapacityKgAs(prefix("maxOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest maxCapacityKgAs(String retName){
        super.max(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest sumCapacityKg(){
        return sumCapacityKgAs(prefix("sumOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest sumCapacityKgAs(String retName){
        super.sum(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest avgCapacityKg(){
        return avgCapacityKgAs(prefix("avgOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest avgCapacityKgAs(String retName){
        super.avg(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest standardDeviationCapacityKg(){
        return standardDeviationCapacityKgAs(prefix("standardDeviationOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest standardDeviationCapacityKgAs(String retName){
        super.standardDeviation(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest squareRootOfPopulationStandardDeviationCapacityKg(){
        return squareRootOfPopulationStandardDeviationCapacityKgAs(prefix("squareRootOfPopulationStandardDeviationOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest squareRootOfPopulationStandardDeviationCapacityKgAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest sampleVarianceCapacityKg(){
        return sampleVarianceCapacityKgAs(prefix("sampleVarianceOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest sampleVarianceCapacityKgAs(String retName){
        super.sampleVariance(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest samplePopulationVarianceCapacityKg(){
        return samplePopulationVarianceCapacityKgAs(prefix("samplePopulationVarianceOf",Vehicle.CAPACITY_KG_PROPERTY));
    }

    public VehicleRequest samplePopulationVarianceCapacityKgAs(String retName){
        super.samplePopulationVariance(retName, Vehicle.CAPACITY_KG_PROPERTY);
        return this;
    }
    public VehicleRequest<T> groupByDispatchPlansWithDetails(DispatchPlanRequest subRequest){
       aggregate(Vehicle.DISPATCH_PLAN_LIST_PROPERTY, subRequest);
       return this;
    }
    public VehicleRequest<T> groupByDriverAssignmentsWithDetails(DriverAssignmentRequest subRequest){
       aggregate(Vehicle.DRIVER_ASSIGNMENT_LIST_PROPERTY, subRequest);
       return this;
    }
    public VehicleRequest<T> groupByGpsLogsWithDetails(GpsLogRequest subRequest){
       aggregate(Vehicle.GPS_LOG_LIST_PROPERTY, subRequest);
       return this;
    }
    public VehicleRequest<T> groupByFuelLogsWithDetails(FuelLogRequest subRequest){
       aggregate(Vehicle.FUEL_LOG_LIST_PROPERTY, subRequest);
       return this;
    }
    public VehicleRequest<T> groupByVehicleMaintenancesWithDetails(VehicleMaintenanceRequest subRequest){
       aggregate(Vehicle.VEHICLE_MAINTENANCE_LIST_PROPERTY, subRequest);
       return this;
    }
    public VehicleRequest<T> groupByTelematicsDevicesWithDetails(TelematicsDeviceRequest subRequest){
       aggregate(Vehicle.TELEMATICS_DEVICE_LIST_PROPERTY, subRequest);
       return this;
    }

    public VehicleRequest<T> groupById(){
       groupBy(Vehicle.ID_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByIdAs(String retName){
       groupBy(retName, Vehicle.ID_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.ID_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByName(){
       groupBy(Vehicle.NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByNameAs(String retName){
       groupBy(retName, Vehicle.NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.NAME_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByLicensePlate(){
       groupBy(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByLicensePlateAs(String retName){
       groupBy(retName, Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByLicensePlateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.LICENSE_PLATE_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByMake(){
       groupBy(Vehicle.MAKE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByMakeAs(String retName){
       groupBy(retName, Vehicle.MAKE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByMakeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.MAKE_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByModel(){
       groupBy(Vehicle.MODEL_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByModelAs(String retName){
       groupBy(retName, Vehicle.MODEL_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByModelWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.MODEL_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByYear(){
       groupBy(Vehicle.YEAR_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByYearAs(String retName){
       groupBy(retName, Vehicle.YEAR_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByYearWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.YEAR_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByCapacityKg(){
       groupBy(Vehicle.CAPACITY_KG_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCapacityKgAs(String retName){
       groupBy(retName, Vehicle.CAPACITY_KG_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCapacityKgWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.CAPACITY_KG_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByStatus(){
       groupBy(Vehicle.STATUS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByStatusAs(String retName){
       groupBy(retName, Vehicle.STATUS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.STATUS_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByCreatedAt(){
       groupBy(Vehicle.CREATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, Vehicle.CREATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.CREATED_AT_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByUpdatedAt(){
       groupBy(Vehicle.UPDATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, Vehicle.UPDATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByVersion(){
       groupBy(Vehicle.VERSION_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Vehicle.VERSION_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.VERSION_PROPERTY, function);
       return this;
    }



    public VehicleRequest<T> orderByIdAscending(){
       addOrderByAscending(Vehicle.ID_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByIdDescending(){
       addOrderByDescending(Vehicle.ID_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByNameAscending(){
       addOrderByAscending(Vehicle.NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByNameDescending(){
       addOrderByDescending(Vehicle.NAME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.NAME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByLicensePlateAscending(){
       addOrderByAscending(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByLicensePlateDescending(){
       addOrderByDescending(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByLicensePlateAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByLicensePlateDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.LICENSE_PLATE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByMakeAscending(){
       addOrderByAscending(Vehicle.MAKE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByMakeDescending(){
       addOrderByDescending(Vehicle.MAKE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByMakeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.MAKE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByMakeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.MAKE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByModelAscending(){
       addOrderByAscending(Vehicle.MODEL_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByModelDescending(){
       addOrderByDescending(Vehicle.MODEL_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByModelAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.MODEL_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByModelDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.MODEL_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByYearAscending(){
       addOrderByAscending(Vehicle.YEAR_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByYearDescending(){
       addOrderByDescending(Vehicle.YEAR_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByCapacityKgAscending(){
       addOrderByAscending(Vehicle.CAPACITY_KG_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByCapacityKgDescending(){
       addOrderByDescending(Vehicle.CAPACITY_KG_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByStatusAscending(){
       addOrderByAscending(Vehicle.STATUS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByStatusDescending(){
       addOrderByDescending(Vehicle.STATUS_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.STATUS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.STATUS_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(Vehicle.CREATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(Vehicle.CREATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(Vehicle.UPDATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(Vehicle.UPDATED_AT_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByVersionAscending(){
       addOrderByAscending(Vehicle.VERSION_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByVersionDescending(){
       addOrderByDescending(Vehicle.VERSION_PROPERTY);
       return this;
    }


    public VehicleRequest<T> statsFromDispatchPlansAs(String name, DispatchPlanRequest subRequest){
       return statsFromDispatchPlansAs(name, subRequest, false);
    }

    public VehicleRequest<T> statsFromDispatchPlansAs(String name, DispatchPlanRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(DispatchPlan.VEHICLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public VehicleRequest<T> statsFromDispatchPlans(DispatchPlanRequest subRequest){
       return statsFromDispatchPlansAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest<T> statsFromDriverAssignmentsAs(String name, DriverAssignmentRequest subRequest){
       return statsFromDriverAssignmentsAs(name, subRequest, false);
    }

    public VehicleRequest<T> statsFromDriverAssignmentsAs(String name, DriverAssignmentRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(DriverAssignment.VEHICLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public VehicleRequest<T> statsFromDriverAssignments(DriverAssignmentRequest subRequest){
       return statsFromDriverAssignmentsAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest<T> statsFromGpsLogsAs(String name, GpsLogRequest subRequest){
       return statsFromGpsLogsAs(name, subRequest, false);
    }

    public VehicleRequest<T> statsFromGpsLogsAs(String name, GpsLogRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(GpsLog.VEHICLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public VehicleRequest<T> statsFromGpsLogs(GpsLogRequest subRequest){
       return statsFromGpsLogsAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest<T> statsFromFuelLogsAs(String name, FuelLogRequest subRequest){
       return statsFromFuelLogsAs(name, subRequest, false);
    }

    public VehicleRequest<T> statsFromFuelLogsAs(String name, FuelLogRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(FuelLog.VEHICLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public VehicleRequest<T> statsFromFuelLogs(FuelLogRequest subRequest){
       return statsFromFuelLogsAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest<T> statsFromVehicleMaintenancesAs(String name, VehicleMaintenanceRequest subRequest){
       return statsFromVehicleMaintenancesAs(name, subRequest, false);
    }

    public VehicleRequest<T> statsFromVehicleMaintenancesAs(String name, VehicleMaintenanceRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(VehicleMaintenance.VEHICLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public VehicleRequest<T> statsFromVehicleMaintenances(VehicleMaintenanceRequest subRequest){
       return statsFromVehicleMaintenancesAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest<T> statsFromTelematicsDevicesAs(String name, TelematicsDeviceRequest subRequest){
       return statsFromTelematicsDevicesAs(name, subRequest, false);
    }

    public VehicleRequest<T> statsFromTelematicsDevicesAs(String name, TelematicsDeviceRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(TelematicsDevice.VEHICLE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public VehicleRequest<T> statsFromTelematicsDevices(TelematicsDeviceRequest subRequest){
       return statsFromTelematicsDevicesAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest<T> countDispatchPlans(){
        return countDispatchPlansAs("Count");
    }

    public VehicleRequest<T> countDispatchPlansAs(String name){
        return countDispatchPlansWith(name, Q.dispatchPlans().unlimited());
    }

    public VehicleRequest<T> countDispatchPlansWith(String name, DispatchPlanRequest subRequest){
        return statsFromDispatchPlansAs(name, subRequest.count(), true);
    }
    public VehicleRequest<T> countDriverAssignments(){
        return countDriverAssignmentsAs("Count");
    }

    public VehicleRequest<T> countDriverAssignmentsAs(String name){
        return countDriverAssignmentsWith(name, Q.driverAssignments().unlimited());
    }

    public VehicleRequest<T> countDriverAssignmentsWith(String name, DriverAssignmentRequest subRequest){
        return statsFromDriverAssignmentsAs(name, subRequest.count(), true);
    }
    public VehicleRequest<T> countGpsLogs(){
        return countGpsLogsAs("Count");
    }

    public VehicleRequest<T> countGpsLogsAs(String name){
        return countGpsLogsWith(name, Q.gpsLogs().unlimited());
    }

    public VehicleRequest<T> countGpsLogsWith(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.count(), true);
    }
    public VehicleRequest<T> countFuelLogs(){
        return countFuelLogsAs("Count");
    }

    public VehicleRequest<T> countFuelLogsAs(String name){
        return countFuelLogsWith(name, Q.fuelLogs().unlimited());
    }

    public VehicleRequest<T> countFuelLogsWith(String name, FuelLogRequest subRequest){
        return statsFromFuelLogsAs(name, subRequest.count(), true);
    }
    public VehicleRequest<T> countVehicleMaintenances(){
        return countVehicleMaintenancesAs("Count");
    }

    public VehicleRequest<T> countVehicleMaintenancesAs(String name){
        return countVehicleMaintenancesWith(name, Q.vehicleMaintenances().unlimited());
    }

    public VehicleRequest<T> countVehicleMaintenancesWith(String name, VehicleMaintenanceRequest subRequest){
        return statsFromVehicleMaintenancesAs(name, subRequest.count(), true);
    }
    public VehicleRequest<T> countTelematicsDevices(){
        return countTelematicsDevicesAs("Count");
    }

    public VehicleRequest<T> countTelematicsDevicesAs(String name){
        return countTelematicsDevicesWith(name, Q.telematicsDevices().unlimited());
    }

    public VehicleRequest<T> countTelematicsDevicesWith(String name, TelematicsDeviceRequest subRequest){
        return statsFromTelematicsDevicesAs(name, subRequest.count(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public VehicleRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public VehicleRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public VehicleRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public VehicleRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public VehicleRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}