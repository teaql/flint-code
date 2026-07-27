package com.doublechaintech.enterpriselogisticsservice.telematicsdevice;

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
import java.time.LocalDateTime;
import java.util.Date;

public class TelematicsDeviceRequest<T extends TelematicsDevice> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public TelematicsDeviceRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public TelematicsDeviceRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public TelematicsDeviceRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public TelematicsDeviceRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public TelematicsDeviceRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public TelematicsDeviceRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public TelematicsDeviceRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (TelematicsDeviceRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public TelematicsDeviceRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public TelematicsDeviceRequest<T> matchingAnyOf(TelematicsDeviceRequest telematicsDevice){
        super.internalMatchAny(telematicsDevice);
        return this;
    }

    public TelematicsDeviceRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public TelematicsDeviceRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public TelematicsDeviceRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public TelematicsDeviceRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectDeviceId().selectVehicleIdOnly().selectFirmwareVersion().selectStatus().selectCreatedAt().selectVersion();
    }

    public TelematicsDeviceRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TelematicsDeviceRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectDeviceId().selectVehicle().selectFirmwareVersion().selectStatus().selectCreatedAt().selectVersion();
    }

    public TelematicsDeviceRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectDeviceId().selectVehicle().selectFirmwareVersion().selectStatus().selectCreatedAt().selectVersion();
    }


    public TelematicsDeviceRequest<T> selectId(){
       selectProperty(TelematicsDevice.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectId(){
       unselectProperty(TelematicsDevice.ID_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> selectDeviceId(){
       selectProperty(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }

    /**
     * fill the deviceId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  deviceId) to fetch deviceId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectDeviceId(){
       unselectProperty(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> selectVehicleIdOnly(){
       selectProperty(TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> selectVehicle(){
        return selectVehicleWith(Q.vehicles().unlimited().selectSelf());
    }

    public TelematicsDeviceRequest<T> selectVehicleWith(VehicleRequest vehicle){
       selectProperty(TelematicsDevice.VEHICLE_PROPERTY);
       enhanceRelation(TelematicsDevice.VEHICLE_PROPERTY, vehicle);
       return this;
    }

    public TelematicsDeviceRequest<T> unselectVehicle(){
       unselectProperty(TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> selectFirmwareVersion(){
       selectProperty(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the firmwareVersion with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  firmwareVersion) to fetch firmwareVersion property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectFirmwareVersion(){
       unselectProperty(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> selectStatus(){
       selectProperty(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectStatus(){
       unselectProperty(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> selectCreatedAt(){
       selectProperty(TelematicsDevice.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectCreatedAt(){
       unselectProperty(TelematicsDevice.CREATED_AT_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> selectVersion(){
       selectProperty(TelematicsDevice.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectVersion(){
       unselectProperty(TelematicsDevice.VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.ID_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public TelematicsDeviceRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public TelematicsDeviceRequest<T> filterByDeviceId(String... deviceId){
      if (deviceId == null || deviceId.length == 0) {
        throw new IllegalArgumentException("filterByDeviceId parameter deviceId cannot be empty");
      }
      return appendSearchCriteria(createDeviceIdCriteria(Operator.EQUAL, (Object[])deviceId));
    }

    public TelematicsDeviceRequest<T> withDeviceId(Operator operator, Object... values){
       return appendSearchCriteria(createDeviceIdCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withDeviceIdIsUnknown(){
       return withDeviceId(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withDeviceIdIsKnown(){
       return withDeviceId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeviceIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.DEVICE_ID_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withDeviceIdGreaterThan(String deviceId){
       return withDeviceId(Operator.GREATER_THAN, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdGreaterThanOrEqualTo(String deviceId){
       return withDeviceId(Operator.GREATER_THAN_OR_EQUAL, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdLessThan(String deviceId){
       return withDeviceId(Operator.LESS_THAN, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdLessThanOrEqualTo(String deviceId){
       return withDeviceId(Operator.LESS_THAN_OR_EQUAL, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdBetween(String startOfDeviceId, String endOfDeviceId){
       return withDeviceId(Operator.BETWEEN, startOfDeviceId, endOfDeviceId);
    }
    public TelematicsDeviceRequest<T> withDeviceIdStartingWith(String deviceId){
       return withDeviceId(Operator.BEGIN_WITH, deviceId);
    }
    public TelematicsDeviceRequest<T> withDeviceIdContaining(String deviceId){
       return withDeviceId(Operator.CONTAIN, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdEndingWith(String deviceId){
       return withDeviceId(Operator.END_WITH, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdIs(String deviceId){
       return withDeviceId(Operator.EQUAL, deviceId);
    }

    public TelematicsDeviceRequest<T> withDeviceIdSoundingLike(String deviceId){
       return withDeviceId(Operator.SOUNDS_LIKE, deviceId);
    }



    public TelematicsDeviceRequest<T> filterByVehicle(Vehicle... vehicle){
      if (vehicle == null || vehicle.length == 0) {
        throw new IllegalArgumentException("filterByVehicle parameter vehicle cannot be empty");
      }
      return appendSearchCriteria(createVehicleCriteria(Operator.EQUAL, (Object[])vehicle));
    }

    public TelematicsDeviceRequest<T> withVehicle(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withVehicleIsUnknown(){
       return withVehicle(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withVehicleIsKnown(){
       return withVehicle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.VEHICLE_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> filterByVehicle(Long vehicle){
      if(vehicle == null){
         return this;
      }
      return withVehicle(Operator.EQUAL, vehicle);
    }
    public TelematicsDeviceRequest<T> withVehicleMatching(VehicleRequest vehicle){
       return appendSearchCriteria(new SubQuerySearchCriteria(TelematicsDevice.VEHICLE_PROPERTY, vehicle, Vehicle.ID_PROPERTY));
    }

    public TelematicsDeviceRequest<T> filterByFirmwareVersion(String... firmwareVersion){
      if (firmwareVersion == null || firmwareVersion.length == 0) {
        throw new IllegalArgumentException("filterByFirmwareVersion parameter firmwareVersion cannot be empty");
      }
      return appendSearchCriteria(createFirmwareVersionCriteria(Operator.EQUAL, (Object[])firmwareVersion));
    }

    public TelematicsDeviceRequest<T> withFirmwareVersion(Operator operator, Object... values){
       return appendSearchCriteria(createFirmwareVersionCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionIsUnknown(){
       return withFirmwareVersion(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionIsKnown(){
       return withFirmwareVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createFirmwareVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.FIRMWARE_VERSION_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionGreaterThan(String firmwareVersion){
       return withFirmwareVersion(Operator.GREATER_THAN, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionGreaterThanOrEqualTo(String firmwareVersion){
       return withFirmwareVersion(Operator.GREATER_THAN_OR_EQUAL, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionLessThan(String firmwareVersion){
       return withFirmwareVersion(Operator.LESS_THAN, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionLessThanOrEqualTo(String firmwareVersion){
       return withFirmwareVersion(Operator.LESS_THAN_OR_EQUAL, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionBetween(String startOfFirmwareVersion, String endOfFirmwareVersion){
       return withFirmwareVersion(Operator.BETWEEN, startOfFirmwareVersion, endOfFirmwareVersion);
    }
    public TelematicsDeviceRequest<T> withFirmwareVersionStartingWith(String firmwareVersion){
       return withFirmwareVersion(Operator.BEGIN_WITH, firmwareVersion);
    }
    public TelematicsDeviceRequest<T> withFirmwareVersionContaining(String firmwareVersion){
       return withFirmwareVersion(Operator.CONTAIN, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionEndingWith(String firmwareVersion){
       return withFirmwareVersion(Operator.END_WITH, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionIs(String firmwareVersion){
       return withFirmwareVersion(Operator.EQUAL, firmwareVersion);
    }

    public TelematicsDeviceRequest<T> withFirmwareVersionSoundingLike(String firmwareVersion){
       return withFirmwareVersion(Operator.SOUNDS_LIKE, firmwareVersion);
    }



    public TelematicsDeviceRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public TelematicsDeviceRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.STATUS_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public TelematicsDeviceRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public TelematicsDeviceRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public TelematicsDeviceRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public TelematicsDeviceRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public TelematicsDeviceRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public TelematicsDeviceRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public TelematicsDeviceRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public TelematicsDeviceRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public TelematicsDeviceRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public TelematicsDeviceRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public TelematicsDeviceRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.CREATED_AT_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public TelematicsDeviceRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public TelematicsDeviceRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public TelematicsDeviceRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public TelematicsDeviceRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.VERSION_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public TelematicsDeviceRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public TelematicsDeviceRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public TelematicsDeviceRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public TelematicsDeviceRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public TelematicsDeviceRequest<T> count(){
        super.count();
        return this;
    }
    public TelematicsDeviceRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public TelematicsDeviceRequest<T> groupByVehicleWithDetails(){
       return groupByVehicleWithDetails(Q.vehicles().unlimited());
    }

    public TelematicsDeviceRequest<T> groupByVehicleWithDetails(VehicleRequest subRequest){
       aggregate(TelematicsDevice.VEHICLE_PROPERTY, subRequest);
       return this;
    }






    public TelematicsDeviceRequest<T> groupById(){
       groupBy(TelematicsDevice.ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByIdAs(String retName){
       groupBy(retName, TelematicsDevice.ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.ID_PROPERTY, function);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByDeviceId(){
       groupBy(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByDeviceIdAs(String retName){
       groupBy(retName, TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByDeviceIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.DEVICE_ID_PROPERTY, function);
       return this;
    }
    public TelematicsDeviceRequest<T> groupByVehicleWith(VehicleRequest subRequest){
       groupBy(TelematicsDevice.VEHICLE_PROPERTY, subRequest);
       return this;
    }
    public TelematicsDeviceRequest<T> groupByVehicle(){
       groupBy(TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByVehicleAs(String retName){
       groupBy(retName, TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByVehicleWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.VEHICLE_PROPERTY, function);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByFirmwareVersion(){
       groupBy(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByFirmwareVersionAs(String retName){
       groupBy(retName, TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByFirmwareVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.FIRMWARE_VERSION_PROPERTY, function);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByStatus(){
       groupBy(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByStatusAs(String retName){
       groupBy(retName, TelematicsDevice.STATUS_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.STATUS_PROPERTY, function);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByCreatedAt(){
       groupBy(TelematicsDevice.CREATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, TelematicsDevice.CREATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.CREATED_AT_PROPERTY, function);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByVersion(){
       groupBy(TelematicsDevice.VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByVersionAs(String retName){
       groupBy(retName, TelematicsDevice.VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.VERSION_PROPERTY, function);
       return this;
    }



    public TelematicsDeviceRequest<T> orderByIdAscending(){
       addOrderByAscending(TelematicsDevice.ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByIdDescending(){
       addOrderByDescending(TelematicsDevice.ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByDeviceIdAscending(){
       addOrderByAscending(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByDeviceIdDescending(){
       addOrderByDescending(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByDeviceIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByDeviceIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TelematicsDevice.DEVICE_ID_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByVehicleAscending(){
       addOrderByAscending(TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByVehicleDescending(){
       addOrderByDescending(TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByFirmwareVersionAscending(){
       addOrderByAscending(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByFirmwareVersionDescending(){
       addOrderByDescending(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByFirmwareVersionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByFirmwareVersionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TelematicsDevice.FIRMWARE_VERSION_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByStatusAscending(){
       addOrderByAscending(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByStatusDescending(){
       addOrderByDescending(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TelematicsDevice.STATUS_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(TelematicsDevice.CREATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(TelematicsDevice.CREATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByVersionAscending(){
       addOrderByAscending(TelematicsDevice.VERSION_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByVersionDescending(){
       addOrderByDescending(TelematicsDevice.VERSION_PROPERTY);
       return this;
    }


    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }






   public TelematicsDeviceRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle){
       return facetByVehicleAs(facetName, vehicle, true);
   }

   public TelematicsDeviceRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle, boolean includeAllFacets){
       addFacet(facetName, TelematicsDevice.VEHICLE_PROPERTY, vehicle, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public TelematicsDeviceRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public TelematicsDeviceRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public TelematicsDeviceRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public TelematicsDeviceRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public TelematicsDeviceRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}