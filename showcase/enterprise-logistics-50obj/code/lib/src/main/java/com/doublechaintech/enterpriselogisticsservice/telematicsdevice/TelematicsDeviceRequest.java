package com.doublechaintech.enterpriselogisticsservice.telematicsdevice;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogRequest;
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
        return selectId().selectDeviceId().selectImei().selectStatus().selectVehicleIdOnly().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public TelematicsDeviceRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TelematicsDeviceRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectDeviceId().selectImei().selectStatus().selectVehicle().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public TelematicsDeviceRequest<T> selectChildren(){
        super.selectAny();
        selectGpsLogList();
        return selectId().selectDeviceId().selectImei().selectStatus().selectVehicle().selectCreatedAt().selectUpdatedAt().selectVersion();
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
    public TelematicsDeviceRequest<T> selectImei(){
       selectProperty(TelematicsDevice.IMEI_PROPERTY);
       return this;
    }

    /**
     * fill the imei with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  imei) to fetch imei property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectImei(){
       unselectProperty(TelematicsDevice.IMEI_PROPERTY);
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
    public TelematicsDeviceRequest<T> selectUpdatedAt(){
       selectProperty(TelematicsDevice.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TelematicsDeviceRequest<T> unselectUpdatedAt(){
       unselectProperty(TelematicsDevice.UPDATED_AT_PROPERTY);
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
    public TelematicsDeviceRequest<T> selectGpsLogList(){
       return selectGpsLogListWith(Q.gpsLogs().selectSelf());
    }

    public TelematicsDeviceRequest<T> selectGpsLogListWith(GpsLogRequest gpsLogList){
       enhanceRelation(TelematicsDevice.GPS_LOG_LIST_PROPERTY, gpsLogList);
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



    public TelematicsDeviceRequest<T> filterByImei(String... imei){
      if (imei == null || imei.length == 0) {
        throw new IllegalArgumentException("filterByImei parameter imei cannot be empty");
      }
      return appendSearchCriteria(createImeiCriteria(Operator.EQUAL, (Object[])imei));
    }

    public TelematicsDeviceRequest<T> withImei(Operator operator, Object... values){
       return appendSearchCriteria(createImeiCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withImeiIsUnknown(){
       return withImei(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withImeiIsKnown(){
       return withImei(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createImeiCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.IMEI_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withImeiGreaterThan(String imei){
       return withImei(Operator.GREATER_THAN, imei);
    }

    public TelematicsDeviceRequest<T> withImeiGreaterThanOrEqualTo(String imei){
       return withImei(Operator.GREATER_THAN_OR_EQUAL, imei);
    }

    public TelematicsDeviceRequest<T> withImeiLessThan(String imei){
       return withImei(Operator.LESS_THAN, imei);
    }

    public TelematicsDeviceRequest<T> withImeiLessThanOrEqualTo(String imei){
       return withImei(Operator.LESS_THAN_OR_EQUAL, imei);
    }

    public TelematicsDeviceRequest<T> withImeiBetween(String startOfImei, String endOfImei){
       return withImei(Operator.BETWEEN, startOfImei, endOfImei);
    }
    public TelematicsDeviceRequest<T> withImeiStartingWith(String imei){
       return withImei(Operator.BEGIN_WITH, imei);
    }
    public TelematicsDeviceRequest<T> withImeiContaining(String imei){
       return withImei(Operator.CONTAIN, imei);
    }

    public TelematicsDeviceRequest<T> withImeiEndingWith(String imei){
       return withImei(Operator.END_WITH, imei);
    }

    public TelematicsDeviceRequest<T> withImeiIs(String imei){
       return withImei(Operator.EQUAL, imei);
    }

    public TelematicsDeviceRequest<T> withImeiSoundingLike(String imei){
       return withImei(Operator.SOUNDS_LIKE, imei);
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




    public TelematicsDeviceRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public TelematicsDeviceRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public TelematicsDeviceRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TelematicsDevice.UPDATED_AT_PROPERTY, operator, values);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public TelematicsDeviceRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public TelematicsDeviceRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
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

    public TelematicsDeviceRequest<T> withGpsLogListMatching(GpsLogRequest gpsLogRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(TelematicsDevice.ID_PROPERTY, gpsLogRequest, GpsLog.DEVICE_PROPERTY));
    }

    public TelematicsDeviceRequest<T> withoutGpsLogListMatching(GpsLogRequest gpsLogRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(TelematicsDevice.ID_PROPERTY, gpsLogRequest, GpsLog.DEVICE_PROPERTY)));
    }

    public TelematicsDeviceRequest<T> haveGpsLogs(){
        return withGpsLogListMatching(Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> haveNoGpsLogs(){
        return withoutGpsLogListMatching(Q.gpsLogs().unlimited());
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




    public TelematicsDeviceRequest<T> groupByGpsLogsWithDetails(GpsLogRequest subRequest){
       aggregate(TelematicsDevice.GPS_LOG_LIST_PROPERTY, subRequest);
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

    public TelematicsDeviceRequest<T> groupByImei(){
       groupBy(TelematicsDevice.IMEI_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByImeiAs(String retName){
       groupBy(retName, TelematicsDevice.IMEI_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByImeiWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.IMEI_PROPERTY, function);
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

    public TelematicsDeviceRequest<T> groupByUpdatedAt(){
       groupBy(TelematicsDevice.UPDATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, TelematicsDevice.UPDATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, TelematicsDevice.UPDATED_AT_PROPERTY, function);
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
    public TelematicsDeviceRequest<T> orderByImeiAscending(){
       addOrderByAscending(TelematicsDevice.IMEI_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByImeiDescending(){
       addOrderByDescending(TelematicsDevice.IMEI_PROPERTY);
       return this;
    }
    public TelematicsDeviceRequest<T> orderByImeiAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TelematicsDevice.IMEI_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByImeiDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TelematicsDevice.IMEI_PROPERTY);
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
    public TelematicsDeviceRequest<T> orderByVehicleAscending(){
       addOrderByAscending(TelematicsDevice.VEHICLE_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByVehicleDescending(){
       addOrderByDescending(TelematicsDevice.VEHICLE_PROPERTY);
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

    public TelematicsDeviceRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(TelematicsDevice.UPDATED_AT_PROPERTY);
       return this;
    }

    public TelematicsDeviceRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(TelematicsDevice.UPDATED_AT_PROPERTY);
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


    public TelematicsDeviceRequest<T> statsFromGpsLogsAs(String name, GpsLogRequest subRequest){
       return statsFromGpsLogsAs(name, subRequest, false);
    }

    public TelematicsDeviceRequest<T> statsFromGpsLogsAs(String name, GpsLogRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(GpsLog.DEVICE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public TelematicsDeviceRequest<T> statsFromGpsLogs(GpsLogRequest subRequest){
       return statsFromGpsLogsAs(REFINEMENTS, subRequest);
    }
    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }




    public TelematicsDeviceRequest<T> countGpsLogs(){
        return countGpsLogsAs("Count");
    }

    public TelematicsDeviceRequest<T> countGpsLogsAs(String name){
        return countGpsLogsWith(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> countGpsLogsWith(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.count(), true);
    }
    public TelematicsDeviceRequest<T> minLatitudeOfGpsLogs(){
        return minLatitudeOfGpsLogsAs("minLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> minLatitudeOfGpsLogsAs(String name){
        return minLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> minLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.minLatitude(), true);
    }
    public TelematicsDeviceRequest<T> maxLatitudeOfGpsLogs(){
        return maxLatitudeOfGpsLogsAs("maxLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> maxLatitudeOfGpsLogsAs(String name){
        return maxLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> maxLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.maxLatitude(), true);
    }
    public TelematicsDeviceRequest<T> sumLatitudeOfGpsLogs(){
        return sumLatitudeOfGpsLogsAs("sumLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sumLatitudeOfGpsLogsAs(String name){
        return sumLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sumLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sumLatitude(), true);
    }
    public TelematicsDeviceRequest<T> avgLatitudeOfGpsLogs(){
        return avgLatitudeOfGpsLogsAs("avgLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> avgLatitudeOfGpsLogsAs(String name){
        return avgLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> avgLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.avgLatitude(), true);
    }
    public TelematicsDeviceRequest<T> standardDeviationLatitudeOfGpsLogs(){
        return standardDeviationLatitudeOfGpsLogsAs("stdDevLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> standardDeviationLatitudeOfGpsLogsAs(String name){
        return standardDeviationLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> standardDeviationLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.standardDeviationLatitude(), true);
    }
    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationLatitudeOfGpsLogs(){
        return squareRootOfPopulationStandardDeviationLatitudeOfGpsLogsAs("stdDevPopLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationLatitudeOfGpsLogsAs(String name){
        return squareRootOfPopulationStandardDeviationLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.squareRootOfPopulationStandardDeviationLatitude(), true);
    }
    public TelematicsDeviceRequest<T> sampleVarianceLatitudeOfGpsLogs(){
        return sampleVarianceLatitudeOfGpsLogsAs("varSampLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sampleVarianceLatitudeOfGpsLogsAs(String name){
        return sampleVarianceLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sampleVarianceLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sampleVarianceLatitude(), true);
    }
    public TelematicsDeviceRequest<T> samplePopulationVarianceLatitudeOfGpsLogs(){
        return samplePopulationVarianceLatitudeOfGpsLogsAs("varPopLatitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceLatitudeOfGpsLogsAs(String name){
        return samplePopulationVarianceLatitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceLatitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.samplePopulationVarianceLatitude(), true);
    }
    public TelematicsDeviceRequest<T> minLongitudeOfGpsLogs(){
        return minLongitudeOfGpsLogsAs("minLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> minLongitudeOfGpsLogsAs(String name){
        return minLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> minLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.minLongitude(), true);
    }
    public TelematicsDeviceRequest<T> maxLongitudeOfGpsLogs(){
        return maxLongitudeOfGpsLogsAs("maxLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> maxLongitudeOfGpsLogsAs(String name){
        return maxLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> maxLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.maxLongitude(), true);
    }
    public TelematicsDeviceRequest<T> sumLongitudeOfGpsLogs(){
        return sumLongitudeOfGpsLogsAs("sumLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sumLongitudeOfGpsLogsAs(String name){
        return sumLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sumLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sumLongitude(), true);
    }
    public TelematicsDeviceRequest<T> avgLongitudeOfGpsLogs(){
        return avgLongitudeOfGpsLogsAs("avgLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> avgLongitudeOfGpsLogsAs(String name){
        return avgLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> avgLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.avgLongitude(), true);
    }
    public TelematicsDeviceRequest<T> standardDeviationLongitudeOfGpsLogs(){
        return standardDeviationLongitudeOfGpsLogsAs("stdDevLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> standardDeviationLongitudeOfGpsLogsAs(String name){
        return standardDeviationLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> standardDeviationLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.standardDeviationLongitude(), true);
    }
    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationLongitudeOfGpsLogs(){
        return squareRootOfPopulationStandardDeviationLongitudeOfGpsLogsAs("stdDevPopLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationLongitudeOfGpsLogsAs(String name){
        return squareRootOfPopulationStandardDeviationLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.squareRootOfPopulationStandardDeviationLongitude(), true);
    }
    public TelematicsDeviceRequest<T> sampleVarianceLongitudeOfGpsLogs(){
        return sampleVarianceLongitudeOfGpsLogsAs("varSampLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sampleVarianceLongitudeOfGpsLogsAs(String name){
        return sampleVarianceLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sampleVarianceLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sampleVarianceLongitude(), true);
    }
    public TelematicsDeviceRequest<T> samplePopulationVarianceLongitudeOfGpsLogs(){
        return samplePopulationVarianceLongitudeOfGpsLogsAs("varPopLongitudeOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceLongitudeOfGpsLogsAs(String name){
        return samplePopulationVarianceLongitudeOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceLongitudeOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.samplePopulationVarianceLongitude(), true);
    }
    public TelematicsDeviceRequest<T> minSpeedKmhOfGpsLogs(){
        return minSpeedKmhOfGpsLogsAs("minSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> minSpeedKmhOfGpsLogsAs(String name){
        return minSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> minSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.minSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> maxSpeedKmhOfGpsLogs(){
        return maxSpeedKmhOfGpsLogsAs("maxSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> maxSpeedKmhOfGpsLogsAs(String name){
        return maxSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> maxSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.maxSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> sumSpeedKmhOfGpsLogs(){
        return sumSpeedKmhOfGpsLogsAs("sumSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sumSpeedKmhOfGpsLogsAs(String name){
        return sumSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sumSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sumSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> avgSpeedKmhOfGpsLogs(){
        return avgSpeedKmhOfGpsLogsAs("avgSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> avgSpeedKmhOfGpsLogsAs(String name){
        return avgSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> avgSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.avgSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> standardDeviationSpeedKmhOfGpsLogs(){
        return standardDeviationSpeedKmhOfGpsLogsAs("stdDevSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> standardDeviationSpeedKmhOfGpsLogsAs(String name){
        return standardDeviationSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> standardDeviationSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.standardDeviationSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationSpeedKmhOfGpsLogs(){
        return squareRootOfPopulationStandardDeviationSpeedKmhOfGpsLogsAs("stdDevPopSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationSpeedKmhOfGpsLogsAs(String name){
        return squareRootOfPopulationStandardDeviationSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.squareRootOfPopulationStandardDeviationSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> sampleVarianceSpeedKmhOfGpsLogs(){
        return sampleVarianceSpeedKmhOfGpsLogsAs("varSampSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sampleVarianceSpeedKmhOfGpsLogsAs(String name){
        return sampleVarianceSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sampleVarianceSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sampleVarianceSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> samplePopulationVarianceSpeedKmhOfGpsLogs(){
        return samplePopulationVarianceSpeedKmhOfGpsLogsAs("varPopSpeedKmhOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceSpeedKmhOfGpsLogsAs(String name){
        return samplePopulationVarianceSpeedKmhOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceSpeedKmhOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.samplePopulationVarianceSpeedKmh(), true);
    }
    public TelematicsDeviceRequest<T> minHeadingOfGpsLogs(){
        return minHeadingOfGpsLogsAs("minHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> minHeadingOfGpsLogsAs(String name){
        return minHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> minHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.minHeading(), true);
    }
    public TelematicsDeviceRequest<T> maxHeadingOfGpsLogs(){
        return maxHeadingOfGpsLogsAs("maxHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> maxHeadingOfGpsLogsAs(String name){
        return maxHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> maxHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.maxHeading(), true);
    }
    public TelematicsDeviceRequest<T> sumHeadingOfGpsLogs(){
        return sumHeadingOfGpsLogsAs("sumHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sumHeadingOfGpsLogsAs(String name){
        return sumHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sumHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sumHeading(), true);
    }
    public TelematicsDeviceRequest<T> avgHeadingOfGpsLogs(){
        return avgHeadingOfGpsLogsAs("avgHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> avgHeadingOfGpsLogsAs(String name){
        return avgHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> avgHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.avgHeading(), true);
    }
    public TelematicsDeviceRequest<T> standardDeviationHeadingOfGpsLogs(){
        return standardDeviationHeadingOfGpsLogsAs("stdDevHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> standardDeviationHeadingOfGpsLogsAs(String name){
        return standardDeviationHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> standardDeviationHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.standardDeviationHeading(), true);
    }
    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationHeadingOfGpsLogs(){
        return squareRootOfPopulationStandardDeviationHeadingOfGpsLogsAs("stdDevPopHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationHeadingOfGpsLogsAs(String name){
        return squareRootOfPopulationStandardDeviationHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> squareRootOfPopulationStandardDeviationHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.squareRootOfPopulationStandardDeviationHeading(), true);
    }
    public TelematicsDeviceRequest<T> sampleVarianceHeadingOfGpsLogs(){
        return sampleVarianceHeadingOfGpsLogsAs("varSampHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> sampleVarianceHeadingOfGpsLogsAs(String name){
        return sampleVarianceHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> sampleVarianceHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.sampleVarianceHeading(), true);
    }
    public TelematicsDeviceRequest<T> samplePopulationVarianceHeadingOfGpsLogs(){
        return samplePopulationVarianceHeadingOfGpsLogsAs("varPopHeadingOfGpsLogs");
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceHeadingOfGpsLogsAs(String name){
        return samplePopulationVarianceHeadingOfGpsLogsAs(name, Q.gpsLogs().unlimited());
    }

    public TelematicsDeviceRequest<T> samplePopulationVarianceHeadingOfGpsLogsAs(String name, GpsLogRequest subRequest){
        return statsFromGpsLogsAs(name, subRequest.samplePopulationVarianceHeading(), true);
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