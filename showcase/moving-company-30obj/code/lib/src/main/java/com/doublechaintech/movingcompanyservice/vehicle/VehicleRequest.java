package com.doublechaintech.movingcompanyservice.vehicle;

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
        return selectId().selectInternalType().selectDisplayName().selectVehicleType().selectLicensePlate().selectCapacityCubicMeters().selectPurchaseDate().selectStatus().selectLastMaintenanceDate().selectNextMaintenanceDate().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public VehicleRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public VehicleRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectInternalType().selectDisplayName().selectVehicleType().selectLicensePlate().selectCapacityCubicMeters().selectPurchaseDate().selectStatus().selectLastMaintenanceDate().selectNextMaintenanceDate().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public VehicleRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectInternalType().selectDisplayName().selectVehicleType().selectLicensePlate().selectCapacityCubicMeters().selectPurchaseDate().selectStatus().selectLastMaintenanceDate().selectNextMaintenanceDate().selectCreateTime().selectUpdateTime().selectVersion();
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
    public VehicleRequest<T> selectInternalType(){
       selectProperty(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the internalType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  internalType) to fetch internalType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectInternalType(){
       unselectProperty(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectDisplayName(){
       selectProperty(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the displayName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  displayName) to fetch displayName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectDisplayName(){
       unselectProperty(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectVehicleType(){
       selectProperty(Vehicle.VEHICLE_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the vehicleType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  vehicleType) to fetch vehicleType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectVehicleType(){
       unselectProperty(Vehicle.VEHICLE_TYPE_PROPERTY);
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
    public VehicleRequest<T> selectCapacityCubicMeters(){
       selectProperty(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
       return this;
    }

    /**
     * fill the capacityCubicMeters with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  capacityCubicMeters) to fetch capacityCubicMeters property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the capacityCubicMeters with customized aggrFunction, TEAQL uses ({aggrFunction}(capacityCubicMeters) AS capacityCubicMeters to fetch capacityCubicMeters property.
     * @param aggrFunction  aggrFunction
     */
    public VehicleRequest<T> selectCapacityCubicMeters(AggrFunction aggrFunction){
       selectProperty(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY, aggrFunction);
       return this;
    }


    public VehicleRequest<T> unselectCapacityCubicMeters(){
       unselectProperty(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectPurchaseDate(){
       selectProperty(Vehicle.PURCHASE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the purchaseDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  purchaseDate) to fetch purchaseDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectPurchaseDate(){
       unselectProperty(Vehicle.PURCHASE_DATE_PROPERTY);
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
    public VehicleRequest<T> selectLastMaintenanceDate(){
       selectProperty(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the lastMaintenanceDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  lastMaintenanceDate) to fetch lastMaintenanceDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectLastMaintenanceDate(){
       unselectProperty(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectNextMaintenanceDate(){
       selectProperty(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the nextMaintenanceDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  nextMaintenanceDate) to fetch nextMaintenanceDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectNextMaintenanceDate(){
       unselectProperty(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectCreateTime(){
       selectProperty(Vehicle.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectCreateTime(){
       unselectProperty(Vehicle.CREATE_TIME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> selectUpdateTime(){
       selectProperty(Vehicle.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public VehicleRequest<T> unselectUpdateTime(){
       unselectProperty(Vehicle.UPDATE_TIME_PROPERTY);
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



    public VehicleRequest<T> filterByInternalType(String... internalType){
      if (internalType == null || internalType.length == 0) {
        throw new IllegalArgumentException("filterByInternalType parameter internalType cannot be empty");
      }
      return appendSearchCriteria(createInternalTypeCriteria(Operator.EQUAL, (Object[])internalType));
    }

    public VehicleRequest<T> withInternalType(Operator operator, Object... values){
       return appendSearchCriteria(createInternalTypeCriteria(operator, values));
    }

    public VehicleRequest<T> withInternalTypeIsUnknown(){
       return withInternalType(Operator.IS_NULL);
    }

    public VehicleRequest<T> withInternalTypeIsKnown(){
       return withInternalType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createInternalTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.INTERNAL_TYPE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withInternalTypeGreaterThan(String internalType){
       return withInternalType(Operator.GREATER_THAN, internalType);
    }

    public VehicleRequest<T> withInternalTypeGreaterThanOrEqualTo(String internalType){
       return withInternalType(Operator.GREATER_THAN_OR_EQUAL, internalType);
    }

    public VehicleRequest<T> withInternalTypeLessThan(String internalType){
       return withInternalType(Operator.LESS_THAN, internalType);
    }

    public VehicleRequest<T> withInternalTypeLessThanOrEqualTo(String internalType){
       return withInternalType(Operator.LESS_THAN_OR_EQUAL, internalType);
    }

    public VehicleRequest<T> withInternalTypeBetween(String startOfInternalType, String endOfInternalType){
       return withInternalType(Operator.BETWEEN, startOfInternalType, endOfInternalType);
    }
    public VehicleRequest<T> withInternalTypeStartingWith(String internalType){
       return withInternalType(Operator.BEGIN_WITH, internalType);
    }
    public VehicleRequest<T> withInternalTypeContaining(String internalType){
       return withInternalType(Operator.CONTAIN, internalType);
    }

    public VehicleRequest<T> withInternalTypeEndingWith(String internalType){
       return withInternalType(Operator.END_WITH, internalType);
    }

    public VehicleRequest<T> withInternalTypeIs(String internalType){
       return withInternalType(Operator.EQUAL, internalType);
    }

    public VehicleRequest<T> withInternalTypeSoundingLike(String internalType){
       return withInternalType(Operator.SOUNDS_LIKE, internalType);
    }



    public VehicleRequest<T> filterByDisplayName(String... displayName){
      if (displayName == null || displayName.length == 0) {
        throw new IllegalArgumentException("filterByDisplayName parameter displayName cannot be empty");
      }
      return appendSearchCriteria(createDisplayNameCriteria(Operator.EQUAL, (Object[])displayName));
    }

    public VehicleRequest<T> withDisplayName(Operator operator, Object... values){
       return appendSearchCriteria(createDisplayNameCriteria(operator, values));
    }

    public VehicleRequest<T> withDisplayNameIsUnknown(){
       return withDisplayName(Operator.IS_NULL);
    }

    public VehicleRequest<T> withDisplayNameIsKnown(){
       return withDisplayName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDisplayNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.DISPLAY_NAME_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withDisplayNameGreaterThan(String displayName){
       return withDisplayName(Operator.GREATER_THAN, displayName);
    }

    public VehicleRequest<T> withDisplayNameGreaterThanOrEqualTo(String displayName){
       return withDisplayName(Operator.GREATER_THAN_OR_EQUAL, displayName);
    }

    public VehicleRequest<T> withDisplayNameLessThan(String displayName){
       return withDisplayName(Operator.LESS_THAN, displayName);
    }

    public VehicleRequest<T> withDisplayNameLessThanOrEqualTo(String displayName){
       return withDisplayName(Operator.LESS_THAN_OR_EQUAL, displayName);
    }

    public VehicleRequest<T> withDisplayNameBetween(String startOfDisplayName, String endOfDisplayName){
       return withDisplayName(Operator.BETWEEN, startOfDisplayName, endOfDisplayName);
    }
    public VehicleRequest<T> withDisplayNameStartingWith(String displayName){
       return withDisplayName(Operator.BEGIN_WITH, displayName);
    }
    public VehicleRequest<T> withDisplayNameContaining(String displayName){
       return withDisplayName(Operator.CONTAIN, displayName);
    }

    public VehicleRequest<T> withDisplayNameEndingWith(String displayName){
       return withDisplayName(Operator.END_WITH, displayName);
    }

    public VehicleRequest<T> withDisplayNameIs(String displayName){
       return withDisplayName(Operator.EQUAL, displayName);
    }

    public VehicleRequest<T> withDisplayNameSoundingLike(String displayName){
       return withDisplayName(Operator.SOUNDS_LIKE, displayName);
    }



    public VehicleRequest<T> filterByVehicleType(String... vehicleType){
      if (vehicleType == null || vehicleType.length == 0) {
        throw new IllegalArgumentException("filterByVehicleType parameter vehicleType cannot be empty");
      }
      return appendSearchCriteria(createVehicleTypeCriteria(Operator.EQUAL, (Object[])vehicleType));
    }

    public VehicleRequest<T> withVehicleType(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleTypeCriteria(operator, values));
    }

    public VehicleRequest<T> withVehicleTypeIsUnknown(){
       return withVehicleType(Operator.IS_NULL);
    }

    public VehicleRequest<T> withVehicleTypeIsKnown(){
       return withVehicleType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.VEHICLE_TYPE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withVehicleTypeGreaterThan(String vehicleType){
       return withVehicleType(Operator.GREATER_THAN, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeGreaterThanOrEqualTo(String vehicleType){
       return withVehicleType(Operator.GREATER_THAN_OR_EQUAL, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeLessThan(String vehicleType){
       return withVehicleType(Operator.LESS_THAN, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeLessThanOrEqualTo(String vehicleType){
       return withVehicleType(Operator.LESS_THAN_OR_EQUAL, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeBetween(String startOfVehicleType, String endOfVehicleType){
       return withVehicleType(Operator.BETWEEN, startOfVehicleType, endOfVehicleType);
    }
    public VehicleRequest<T> withVehicleTypeStartingWith(String vehicleType){
       return withVehicleType(Operator.BEGIN_WITH, vehicleType);
    }
    public VehicleRequest<T> withVehicleTypeContaining(String vehicleType){
       return withVehicleType(Operator.CONTAIN, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeEndingWith(String vehicleType){
       return withVehicleType(Operator.END_WITH, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeIs(String vehicleType){
       return withVehicleType(Operator.EQUAL, vehicleType);
    }

    public VehicleRequest<T> withVehicleTypeSoundingLike(String vehicleType){
       return withVehicleType(Operator.SOUNDS_LIKE, vehicleType);
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



    public VehicleRequest<T> filterByCapacityCubicMeters(BigDecimal... capacityCubicMeters){
      if (capacityCubicMeters == null || capacityCubicMeters.length == 0) {
        throw new IllegalArgumentException("filterByCapacityCubicMeters parameter capacityCubicMeters cannot be empty");
      }
      return appendSearchCriteria(createCapacityCubicMetersCriteria(Operator.EQUAL, (Object[])capacityCubicMeters));
    }

    public VehicleRequest<T> withCapacityCubicMeters(Operator operator, Object... values){
       return appendSearchCriteria(createCapacityCubicMetersCriteria(operator, values));
    }

    public VehicleRequest<T> withCapacityCubicMetersIsUnknown(){
       return withCapacityCubicMeters(Operator.IS_NULL);
    }

    public VehicleRequest<T> withCapacityCubicMetersIsKnown(){
       return withCapacityCubicMeters(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCapacityCubicMetersCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withCapacityCubicMetersGreaterThan(BigDecimal capacityCubicMeters){
       return withCapacityCubicMeters(Operator.GREATER_THAN, capacityCubicMeters);
    }

    public VehicleRequest<T> withCapacityCubicMetersGreaterThanOrEqualTo(BigDecimal capacityCubicMeters){
       return withCapacityCubicMeters(Operator.GREATER_THAN_OR_EQUAL, capacityCubicMeters);
    }

    public VehicleRequest<T> withCapacityCubicMetersLessThan(BigDecimal capacityCubicMeters){
       return withCapacityCubicMeters(Operator.LESS_THAN, capacityCubicMeters);
    }

    public VehicleRequest<T> withCapacityCubicMetersLessThanOrEqualTo(BigDecimal capacityCubicMeters){
       return withCapacityCubicMeters(Operator.LESS_THAN_OR_EQUAL, capacityCubicMeters);
    }

    public VehicleRequest<T> withCapacityCubicMetersBetween(BigDecimal startOfCapacityCubicMeters, BigDecimal endOfCapacityCubicMeters){
       return withCapacityCubicMeters(Operator.BETWEEN, startOfCapacityCubicMeters, endOfCapacityCubicMeters);
    }



    public VehicleRequest<T> filterByPurchaseDate(LocalDate... purchaseDate){
      if (purchaseDate == null || purchaseDate.length == 0) {
        throw new IllegalArgumentException("filterByPurchaseDate parameter purchaseDate cannot be empty");
      }
      return appendSearchCriteria(createPurchaseDateCriteria(Operator.EQUAL, (Object[])purchaseDate));
    }

    public VehicleRequest<T> withPurchaseDate(Operator operator, Object... values){
       return appendSearchCriteria(createPurchaseDateCriteria(operator, values));
    }

    public VehicleRequest<T> withPurchaseDateIsUnknown(){
       return withPurchaseDate(Operator.IS_NULL);
    }

    public VehicleRequest<T> withPurchaseDateIsKnown(){
       return withPurchaseDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPurchaseDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.PURCHASE_DATE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withPurchaseDateGreaterThan(LocalDate purchaseDate){
       return withPurchaseDate(Operator.GREATER_THAN, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateGreaterThanOrEqualTo(LocalDate purchaseDate){
       return withPurchaseDate(Operator.GREATER_THAN_OR_EQUAL, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateLessThan(LocalDate purchaseDate){
       return withPurchaseDate(Operator.LESS_THAN, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateLessThanOrEqualTo(LocalDate purchaseDate){
       return withPurchaseDate(Operator.LESS_THAN_OR_EQUAL, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateBetween(LocalDate startOfPurchaseDate, LocalDate endOfPurchaseDate){
       return withPurchaseDate(Operator.BETWEEN, startOfPurchaseDate, endOfPurchaseDate);
    }
    public VehicleRequest<T> withPurchaseDateBefore(LocalDate purchaseDate){
       return withPurchaseDate(Operator.LESS_THAN, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateBefore(Date purchaseDate){
       return withPurchaseDate(Operator.LESS_THAN, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateAfter(LocalDate purchaseDate){
       return withPurchaseDate(Operator.GREATER_THAN, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateAfter(Date purchaseDate){
       return withPurchaseDate(Operator.GREATER_THAN, purchaseDate);
    }

    public VehicleRequest<T> withPurchaseDateBetween(Date startOfPurchaseDate, Date endOfPurchaseDate){
       return withPurchaseDate(Operator.BETWEEN, startOfPurchaseDate, endOfPurchaseDate);
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



    public VehicleRequest<T> filterByLastMaintenanceDate(LocalDate... lastMaintenanceDate){
      if (lastMaintenanceDate == null || lastMaintenanceDate.length == 0) {
        throw new IllegalArgumentException("filterByLastMaintenanceDate parameter lastMaintenanceDate cannot be empty");
      }
      return appendSearchCriteria(createLastMaintenanceDateCriteria(Operator.EQUAL, (Object[])lastMaintenanceDate));
    }

    public VehicleRequest<T> withLastMaintenanceDate(Operator operator, Object... values){
       return appendSearchCriteria(createLastMaintenanceDateCriteria(operator, values));
    }

    public VehicleRequest<T> withLastMaintenanceDateIsUnknown(){
       return withLastMaintenanceDate(Operator.IS_NULL);
    }

    public VehicleRequest<T> withLastMaintenanceDateIsKnown(){
       return withLastMaintenanceDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLastMaintenanceDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withLastMaintenanceDateGreaterThan(LocalDate lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.GREATER_THAN, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateGreaterThanOrEqualTo(LocalDate lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.GREATER_THAN_OR_EQUAL, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateLessThan(LocalDate lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.LESS_THAN, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateLessThanOrEqualTo(LocalDate lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.LESS_THAN_OR_EQUAL, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateBetween(LocalDate startOfLastMaintenanceDate, LocalDate endOfLastMaintenanceDate){
       return withLastMaintenanceDate(Operator.BETWEEN, startOfLastMaintenanceDate, endOfLastMaintenanceDate);
    }
    public VehicleRequest<T> withLastMaintenanceDateBefore(LocalDate lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.LESS_THAN, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateBefore(Date lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.LESS_THAN, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateAfter(LocalDate lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.GREATER_THAN, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateAfter(Date lastMaintenanceDate){
       return withLastMaintenanceDate(Operator.GREATER_THAN, lastMaintenanceDate);
    }

    public VehicleRequest<T> withLastMaintenanceDateBetween(Date startOfLastMaintenanceDate, Date endOfLastMaintenanceDate){
       return withLastMaintenanceDate(Operator.BETWEEN, startOfLastMaintenanceDate, endOfLastMaintenanceDate);
    }




    public VehicleRequest<T> filterByNextMaintenanceDate(LocalDate... nextMaintenanceDate){
      if (nextMaintenanceDate == null || nextMaintenanceDate.length == 0) {
        throw new IllegalArgumentException("filterByNextMaintenanceDate parameter nextMaintenanceDate cannot be empty");
      }
      return appendSearchCriteria(createNextMaintenanceDateCriteria(Operator.EQUAL, (Object[])nextMaintenanceDate));
    }

    public VehicleRequest<T> withNextMaintenanceDate(Operator operator, Object... values){
       return appendSearchCriteria(createNextMaintenanceDateCriteria(operator, values));
    }

    public VehicleRequest<T> withNextMaintenanceDateIsUnknown(){
       return withNextMaintenanceDate(Operator.IS_NULL);
    }

    public VehicleRequest<T> withNextMaintenanceDateIsKnown(){
       return withNextMaintenanceDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNextMaintenanceDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withNextMaintenanceDateGreaterThan(LocalDate nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.GREATER_THAN, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateGreaterThanOrEqualTo(LocalDate nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.GREATER_THAN_OR_EQUAL, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateLessThan(LocalDate nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.LESS_THAN, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateLessThanOrEqualTo(LocalDate nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.LESS_THAN_OR_EQUAL, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateBetween(LocalDate startOfNextMaintenanceDate, LocalDate endOfNextMaintenanceDate){
       return withNextMaintenanceDate(Operator.BETWEEN, startOfNextMaintenanceDate, endOfNextMaintenanceDate);
    }
    public VehicleRequest<T> withNextMaintenanceDateBefore(LocalDate nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.LESS_THAN, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateBefore(Date nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.LESS_THAN, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateAfter(LocalDate nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.GREATER_THAN, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateAfter(Date nextMaintenanceDate){
       return withNextMaintenanceDate(Operator.GREATER_THAN, nextMaintenanceDate);
    }

    public VehicleRequest<T> withNextMaintenanceDateBetween(Date startOfNextMaintenanceDate, Date endOfNextMaintenanceDate){
       return withNextMaintenanceDate(Operator.BETWEEN, startOfNextMaintenanceDate, endOfNextMaintenanceDate);
    }




    public VehicleRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public VehicleRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public VehicleRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public VehicleRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.CREATE_TIME_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public VehicleRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public VehicleRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public VehicleRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public VehicleRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public VehicleRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public VehicleRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public VehicleRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public VehicleRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public VehicleRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public VehicleRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public VehicleRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public VehicleRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public VehicleRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Vehicle.UPDATE_TIME_PROPERTY, operator, values);
    }

    public VehicleRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public VehicleRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public VehicleRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
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


    public VehicleRequest<T> count(){
        super.count();
        return this;
    }
    public VehicleRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public VehicleRequest minCapacityCubicMeters(){
        return minCapacityCubicMetersAs(prefix("minOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest minCapacityCubicMetersAs(String retName){
        super.min(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest maxCapacityCubicMeters(){
        return maxCapacityCubicMetersAs(prefix("maxOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest maxCapacityCubicMetersAs(String retName){
        super.max(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest sumCapacityCubicMeters(){
        return sumCapacityCubicMetersAs(prefix("sumOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest sumCapacityCubicMetersAs(String retName){
        super.sum(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest avgCapacityCubicMeters(){
        return avgCapacityCubicMetersAs(prefix("avgOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest avgCapacityCubicMetersAs(String retName){
        super.avg(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest standardDeviationCapacityCubicMeters(){
        return standardDeviationCapacityCubicMetersAs(prefix("standardDeviationOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest standardDeviationCapacityCubicMetersAs(String retName){
        super.standardDeviation(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest squareRootOfPopulationStandardDeviationCapacityCubicMeters(){
        return squareRootOfPopulationStandardDeviationCapacityCubicMetersAs(prefix("squareRootOfPopulationStandardDeviationOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest squareRootOfPopulationStandardDeviationCapacityCubicMetersAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest sampleVarianceCapacityCubicMeters(){
        return sampleVarianceCapacityCubicMetersAs(prefix("sampleVarianceOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest sampleVarianceCapacityCubicMetersAs(String retName){
        super.sampleVariance(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
        return this;
    }
    public VehicleRequest samplePopulationVarianceCapacityCubicMeters(){
        return samplePopulationVarianceCapacityCubicMetersAs(prefix("samplePopulationVarianceOf",Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
    }

    public VehicleRequest samplePopulationVarianceCapacityCubicMetersAs(String retName){
        super.samplePopulationVariance(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
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

    public VehicleRequest<T> groupByInternalType(){
       groupBy(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByInternalTypeAs(String retName){
       groupBy(retName, Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByInternalTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.INTERNAL_TYPE_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByDisplayName(){
       groupBy(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByDisplayNameAs(String retName){
       groupBy(retName, Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByDisplayNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.DISPLAY_NAME_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByVehicleType(){
       groupBy(Vehicle.VEHICLE_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByVehicleTypeAs(String retName){
       groupBy(retName, Vehicle.VEHICLE_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByVehicleTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.VEHICLE_TYPE_PROPERTY, function);
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

    public VehicleRequest<T> groupByCapacityCubicMeters(){
       groupBy(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCapacityCubicMetersAs(String retName){
       groupBy(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCapacityCubicMetersWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByPurchaseDate(){
       groupBy(Vehicle.PURCHASE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByPurchaseDateAs(String retName){
       groupBy(retName, Vehicle.PURCHASE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByPurchaseDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.PURCHASE_DATE_PROPERTY, function);
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

    public VehicleRequest<T> groupByLastMaintenanceDate(){
       groupBy(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByLastMaintenanceDateAs(String retName){
       groupBy(retName, Vehicle.LAST_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByLastMaintenanceDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.LAST_MAINTENANCE_DATE_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByNextMaintenanceDate(){
       groupBy(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByNextMaintenanceDateAs(String retName){
       groupBy(retName, Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByNextMaintenanceDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByCreateTime(){
       groupBy(Vehicle.CREATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, Vehicle.CREATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public VehicleRequest<T> groupByUpdateTime(){
       groupBy(Vehicle.UPDATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, Vehicle.UPDATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Vehicle.UPDATE_TIME_PROPERTY, function);
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

    public VehicleRequest<T> orderByInternalTypeAscending(){
       addOrderByAscending(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByInternalTypeDescending(){
       addOrderByDescending(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByInternalTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByInternalTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.INTERNAL_TYPE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByDisplayNameAscending(){
       addOrderByAscending(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByDisplayNameDescending(){
       addOrderByDescending(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByDisplayNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByDisplayNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.DISPLAY_NAME_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByVehicleTypeAscending(){
       addOrderByAscending(Vehicle.VEHICLE_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByVehicleTypeDescending(){
       addOrderByDescending(Vehicle.VEHICLE_TYPE_PROPERTY);
       return this;
    }
    public VehicleRequest<T> orderByVehicleTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Vehicle.VEHICLE_TYPE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByVehicleTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Vehicle.VEHICLE_TYPE_PROPERTY);
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
    public VehicleRequest<T> orderByCapacityCubicMetersAscending(){
       addOrderByAscending(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByCapacityCubicMetersDescending(){
       addOrderByDescending(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByPurchaseDateAscending(){
       addOrderByAscending(Vehicle.PURCHASE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByPurchaseDateDescending(){
       addOrderByDescending(Vehicle.PURCHASE_DATE_PROPERTY);
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
    public VehicleRequest<T> orderByLastMaintenanceDateAscending(){
       addOrderByAscending(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByLastMaintenanceDateDescending(){
       addOrderByDescending(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByNextMaintenanceDateAscending(){
       addOrderByAscending(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByNextMaintenanceDateDescending(){
       addOrderByDescending(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(Vehicle.CREATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(Vehicle.CREATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(Vehicle.UPDATE_TIME_PROPERTY);
       return this;
    }

    public VehicleRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(Vehicle.UPDATE_TIME_PROPERTY);
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