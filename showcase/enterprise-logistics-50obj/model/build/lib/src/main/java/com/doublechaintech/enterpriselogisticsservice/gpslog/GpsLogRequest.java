package com.doublechaintech.enterpriselogisticsservice.gpslog;

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

public class GpsLogRequest<T extends GpsLog> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public GpsLogRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public GpsLogRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public GpsLogRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public GpsLogRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public GpsLogRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public GpsLogRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public GpsLogRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (GpsLogRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public GpsLogRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public GpsLogRequest<T> matchingAnyOf(GpsLogRequest gpsLog){
        super.internalMatchAny(gpsLog);
        return this;
    }

    public GpsLogRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public GpsLogRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public GpsLogRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public GpsLogRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectVehicleIdOnly().selectLatitude().selectLongitude().selectTimestamp().selectSpeedKmh().selectCreatedAt().selectVersion();
    }

    public GpsLogRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public GpsLogRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectVehicle().selectLatitude().selectLongitude().selectTimestamp().selectSpeedKmh().selectCreatedAt().selectVersion();
    }

    public GpsLogRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectVehicle().selectLatitude().selectLongitude().selectTimestamp().selectSpeedKmh().selectCreatedAt().selectVersion();
    }


    public GpsLogRequest<T> selectId(){
       selectProperty(GpsLog.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectId(){
       unselectProperty(GpsLog.ID_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectVehicleIdOnly(){
       selectProperty(GpsLog.VEHICLE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> selectVehicle(){
        return selectVehicleWith(Q.vehicles().unlimited().selectSelf());
    }

    public GpsLogRequest<T> selectVehicleWith(VehicleRequest vehicle){
       selectProperty(GpsLog.VEHICLE_PROPERTY);
       enhanceRelation(GpsLog.VEHICLE_PROPERTY, vehicle);
       return this;
    }

    public GpsLogRequest<T> unselectVehicle(){
       unselectProperty(GpsLog.VEHICLE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectLatitude(){
       selectProperty(GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    /**
     * fill the latitude with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  latitude) to fetch latitude property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectLatitude(){
       unselectProperty(GpsLog.LATITUDE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectLongitude(){
       selectProperty(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }

    /**
     * fill the longitude with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  longitude) to fetch longitude property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectLongitude(){
       unselectProperty(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectTimestamp(){
       selectProperty(GpsLog.TIMESTAMP_PROPERTY);
       return this;
    }

    /**
     * fill the timestamp with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  timestamp) to fetch timestamp property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectTimestamp(){
       unselectProperty(GpsLog.TIMESTAMP_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectSpeedKmh(){
       selectProperty(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    /**
     * fill the speedKmh with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  speedKmh) to fetch speedKmh property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectSpeedKmh(){
       unselectProperty(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectCreatedAt(){
       selectProperty(GpsLog.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectCreatedAt(){
       unselectProperty(GpsLog.CREATED_AT_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectVersion(){
       selectProperty(GpsLog.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public GpsLogRequest<T> unselectVersion(){
       unselectProperty(GpsLog.VERSION_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.ID_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public GpsLogRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public GpsLogRequest<T> filterByVehicle(Vehicle... vehicle){
      if (vehicle == null || vehicle.length == 0) {
        throw new IllegalArgumentException("filterByVehicle parameter vehicle cannot be empty");
      }
      return appendSearchCriteria(createVehicleCriteria(Operator.EQUAL, (Object[])vehicle));
    }

    public GpsLogRequest<T> withVehicle(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleCriteria(operator, values));
    }

    public GpsLogRequest<T> withVehicleIsUnknown(){
       return withVehicle(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withVehicleIsKnown(){
       return withVehicle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.VEHICLE_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> filterByVehicle(Long vehicle){
      if(vehicle == null){
         return this;
      }
      return withVehicle(Operator.EQUAL, vehicle);
    }
    public GpsLogRequest<T> withVehicleMatching(VehicleRequest vehicle){
       return appendSearchCriteria(new SubQuerySearchCriteria(GpsLog.VEHICLE_PROPERTY, vehicle, Vehicle.ID_PROPERTY));
    }

    public GpsLogRequest<T> filterByLatitude(String... latitude){
      if (latitude == null || latitude.length == 0) {
        throw new IllegalArgumentException("filterByLatitude parameter latitude cannot be empty");
      }
      return appendSearchCriteria(createLatitudeCriteria(Operator.EQUAL, (Object[])latitude));
    }

    public GpsLogRequest<T> withLatitude(Operator operator, Object... values){
       return appendSearchCriteria(createLatitudeCriteria(operator, values));
    }

    public GpsLogRequest<T> withLatitudeIsUnknown(){
       return withLatitude(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withLatitudeIsKnown(){
       return withLatitude(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLatitudeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.LATITUDE_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withLatitudeGreaterThan(String latitude){
       return withLatitude(Operator.GREATER_THAN, latitude);
    }

    public GpsLogRequest<T> withLatitudeGreaterThanOrEqualTo(String latitude){
       return withLatitude(Operator.GREATER_THAN_OR_EQUAL, latitude);
    }

    public GpsLogRequest<T> withLatitudeLessThan(String latitude){
       return withLatitude(Operator.LESS_THAN, latitude);
    }

    public GpsLogRequest<T> withLatitudeLessThanOrEqualTo(String latitude){
       return withLatitude(Operator.LESS_THAN_OR_EQUAL, latitude);
    }

    public GpsLogRequest<T> withLatitudeBetween(String startOfLatitude, String endOfLatitude){
       return withLatitude(Operator.BETWEEN, startOfLatitude, endOfLatitude);
    }
    public GpsLogRequest<T> withLatitudeStartingWith(String latitude){
       return withLatitude(Operator.BEGIN_WITH, latitude);
    }
    public GpsLogRequest<T> withLatitudeContaining(String latitude){
       return withLatitude(Operator.CONTAIN, latitude);
    }

    public GpsLogRequest<T> withLatitudeEndingWith(String latitude){
       return withLatitude(Operator.END_WITH, latitude);
    }

    public GpsLogRequest<T> withLatitudeIs(String latitude){
       return withLatitude(Operator.EQUAL, latitude);
    }

    public GpsLogRequest<T> withLatitudeSoundingLike(String latitude){
       return withLatitude(Operator.SOUNDS_LIKE, latitude);
    }



    public GpsLogRequest<T> filterByLongitude(String... longitude){
      if (longitude == null || longitude.length == 0) {
        throw new IllegalArgumentException("filterByLongitude parameter longitude cannot be empty");
      }
      return appendSearchCriteria(createLongitudeCriteria(Operator.EQUAL, (Object[])longitude));
    }

    public GpsLogRequest<T> withLongitude(Operator operator, Object... values){
       return appendSearchCriteria(createLongitudeCriteria(operator, values));
    }

    public GpsLogRequest<T> withLongitudeIsUnknown(){
       return withLongitude(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withLongitudeIsKnown(){
       return withLongitude(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLongitudeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.LONGITUDE_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withLongitudeGreaterThan(String longitude){
       return withLongitude(Operator.GREATER_THAN, longitude);
    }

    public GpsLogRequest<T> withLongitudeGreaterThanOrEqualTo(String longitude){
       return withLongitude(Operator.GREATER_THAN_OR_EQUAL, longitude);
    }

    public GpsLogRequest<T> withLongitudeLessThan(String longitude){
       return withLongitude(Operator.LESS_THAN, longitude);
    }

    public GpsLogRequest<T> withLongitudeLessThanOrEqualTo(String longitude){
       return withLongitude(Operator.LESS_THAN_OR_EQUAL, longitude);
    }

    public GpsLogRequest<T> withLongitudeBetween(String startOfLongitude, String endOfLongitude){
       return withLongitude(Operator.BETWEEN, startOfLongitude, endOfLongitude);
    }
    public GpsLogRequest<T> withLongitudeStartingWith(String longitude){
       return withLongitude(Operator.BEGIN_WITH, longitude);
    }
    public GpsLogRequest<T> withLongitudeContaining(String longitude){
       return withLongitude(Operator.CONTAIN, longitude);
    }

    public GpsLogRequest<T> withLongitudeEndingWith(String longitude){
       return withLongitude(Operator.END_WITH, longitude);
    }

    public GpsLogRequest<T> withLongitudeIs(String longitude){
       return withLongitude(Operator.EQUAL, longitude);
    }

    public GpsLogRequest<T> withLongitudeSoundingLike(String longitude){
       return withLongitude(Operator.SOUNDS_LIKE, longitude);
    }



    public GpsLogRequest<T> filterByTimestamp(LocalDateTime... timestamp){
      if (timestamp == null || timestamp.length == 0) {
        throw new IllegalArgumentException("filterByTimestamp parameter timestamp cannot be empty");
      }
      return appendSearchCriteria(createTimestampCriteria(Operator.EQUAL, (Object[])timestamp));
    }

    public GpsLogRequest<T> withTimestamp(Operator operator, Object... values){
       return appendSearchCriteria(createTimestampCriteria(operator, values));
    }

    public GpsLogRequest<T> withTimestampIsUnknown(){
       return withTimestamp(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withTimestampIsKnown(){
       return withTimestamp(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTimestampCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.TIMESTAMP_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withTimestampGreaterThan(LocalDateTime timestamp){
       return withTimestamp(Operator.GREATER_THAN, timestamp);
    }

    public GpsLogRequest<T> withTimestampGreaterThanOrEqualTo(LocalDateTime timestamp){
       return withTimestamp(Operator.GREATER_THAN_OR_EQUAL, timestamp);
    }

    public GpsLogRequest<T> withTimestampLessThan(LocalDateTime timestamp){
       return withTimestamp(Operator.LESS_THAN, timestamp);
    }

    public GpsLogRequest<T> withTimestampLessThanOrEqualTo(LocalDateTime timestamp){
       return withTimestamp(Operator.LESS_THAN_OR_EQUAL, timestamp);
    }

    public GpsLogRequest<T> withTimestampBetween(LocalDateTime startOfTimestamp, LocalDateTime endOfTimestamp){
       return withTimestamp(Operator.BETWEEN, startOfTimestamp, endOfTimestamp);
    }
    public GpsLogRequest<T> withTimestampBefore(LocalDateTime timestamp){
       return withTimestamp(Operator.LESS_THAN, timestamp);
    }

    public GpsLogRequest<T> withTimestampBefore(Date timestamp){
       return withTimestamp(Operator.LESS_THAN, timestamp);
    }

    public GpsLogRequest<T> withTimestampAfter(LocalDateTime timestamp){
       return withTimestamp(Operator.GREATER_THAN, timestamp);
    }

    public GpsLogRequest<T> withTimestampAfter(Date timestamp){
       return withTimestamp(Operator.GREATER_THAN, timestamp);
    }

    public GpsLogRequest<T> withTimestampBetween(Date startOfTimestamp, Date endOfTimestamp){
       return withTimestamp(Operator.BETWEEN, startOfTimestamp, endOfTimestamp);
    }




    public GpsLogRequest<T> filterBySpeedKmh(String... speedKmh){
      if (speedKmh == null || speedKmh.length == 0) {
        throw new IllegalArgumentException("filterBySpeedKmh parameter speedKmh cannot be empty");
      }
      return appendSearchCriteria(createSpeedKmhCriteria(Operator.EQUAL, (Object[])speedKmh));
    }

    public GpsLogRequest<T> withSpeedKmh(Operator operator, Object... values){
       return appendSearchCriteria(createSpeedKmhCriteria(operator, values));
    }

    public GpsLogRequest<T> withSpeedKmhIsUnknown(){
       return withSpeedKmh(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withSpeedKmhIsKnown(){
       return withSpeedKmh(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createSpeedKmhCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.SPEED_KMH_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withSpeedKmhGreaterThan(String speedKmh){
       return withSpeedKmh(Operator.GREATER_THAN, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhGreaterThanOrEqualTo(String speedKmh){
       return withSpeedKmh(Operator.GREATER_THAN_OR_EQUAL, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhLessThan(String speedKmh){
       return withSpeedKmh(Operator.LESS_THAN, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhLessThanOrEqualTo(String speedKmh){
       return withSpeedKmh(Operator.LESS_THAN_OR_EQUAL, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhBetween(String startOfSpeedKmh, String endOfSpeedKmh){
       return withSpeedKmh(Operator.BETWEEN, startOfSpeedKmh, endOfSpeedKmh);
    }
    public GpsLogRequest<T> withSpeedKmhStartingWith(String speedKmh){
       return withSpeedKmh(Operator.BEGIN_WITH, speedKmh);
    }
    public GpsLogRequest<T> withSpeedKmhContaining(String speedKmh){
       return withSpeedKmh(Operator.CONTAIN, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhEndingWith(String speedKmh){
       return withSpeedKmh(Operator.END_WITH, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhIs(String speedKmh){
       return withSpeedKmh(Operator.EQUAL, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhSoundingLike(String speedKmh){
       return withSpeedKmh(Operator.SOUNDS_LIKE, speedKmh);
    }



    public GpsLogRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public GpsLogRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public GpsLogRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.CREATED_AT_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public GpsLogRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public GpsLogRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public GpsLogRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public GpsLogRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public GpsLogRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.VERSION_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public GpsLogRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public GpsLogRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public GpsLogRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public GpsLogRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public GpsLogRequest<T> count(){
        super.count();
        return this;
    }
    public GpsLogRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public GpsLogRequest<T> groupByVehicleWithDetails(){
       return groupByVehicleWithDetails(Q.vehicles().unlimited());
    }

    public GpsLogRequest<T> groupByVehicleWithDetails(VehicleRequest subRequest){
       aggregate(GpsLog.VEHICLE_PROPERTY, subRequest);
       return this;
    }








    public GpsLogRequest<T> groupById(){
       groupBy(GpsLog.ID_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByIdAs(String retName){
       groupBy(retName, GpsLog.ID_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.ID_PROPERTY, function);
       return this;
    }
    public GpsLogRequest<T> groupByVehicleWith(VehicleRequest subRequest){
       groupBy(GpsLog.VEHICLE_PROPERTY, subRequest);
       return this;
    }
    public GpsLogRequest<T> groupByVehicle(){
       groupBy(GpsLog.VEHICLE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByVehicleAs(String retName){
       groupBy(retName, GpsLog.VEHICLE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByVehicleWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.VEHICLE_PROPERTY, function);
       return this;
    }

    public GpsLogRequest<T> groupByLatitude(){
       groupBy(GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByLatitudeAs(String retName){
       groupBy(retName, GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByLatitudeWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.LATITUDE_PROPERTY, function);
       return this;
    }

    public GpsLogRequest<T> groupByLongitude(){
       groupBy(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByLongitudeAs(String retName){
       groupBy(retName, GpsLog.LONGITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByLongitudeWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.LONGITUDE_PROPERTY, function);
       return this;
    }

    public GpsLogRequest<T> groupByTimestamp(){
       groupBy(GpsLog.TIMESTAMP_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByTimestampAs(String retName){
       groupBy(retName, GpsLog.TIMESTAMP_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByTimestampWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.TIMESTAMP_PROPERTY, function);
       return this;
    }

    public GpsLogRequest<T> groupBySpeedKmh(){
       groupBy(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupBySpeedKmhAs(String retName){
       groupBy(retName, GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupBySpeedKmhWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.SPEED_KMH_PROPERTY, function);
       return this;
    }

    public GpsLogRequest<T> groupByCreatedAt(){
       groupBy(GpsLog.CREATED_AT_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, GpsLog.CREATED_AT_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.CREATED_AT_PROPERTY, function);
       return this;
    }

    public GpsLogRequest<T> groupByVersion(){
       groupBy(GpsLog.VERSION_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByVersionAs(String retName){
       groupBy(retName, GpsLog.VERSION_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.VERSION_PROPERTY, function);
       return this;
    }



    public GpsLogRequest<T> orderByIdAscending(){
       addOrderByAscending(GpsLog.ID_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByIdDescending(){
       addOrderByDescending(GpsLog.ID_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByVehicleAscending(){
       addOrderByAscending(GpsLog.VEHICLE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByVehicleDescending(){
       addOrderByDescending(GpsLog.VEHICLE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByLatitudeAscending(){
       addOrderByAscending(GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByLatitudeDescending(){
       addOrderByDescending(GpsLog.LATITUDE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> orderByLatitudeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByLatitudeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(GpsLog.LATITUDE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> orderByLongitudeAscending(){
       addOrderByAscending(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByLongitudeDescending(){
       addOrderByDescending(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> orderByLongitudeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByLongitudeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(GpsLog.LONGITUDE_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> orderByTimestampAscending(){
       addOrderByAscending(GpsLog.TIMESTAMP_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByTimestampDescending(){
       addOrderByDescending(GpsLog.TIMESTAMP_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderBySpeedKmhAscending(){
       addOrderByAscending(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderBySpeedKmhDescending(){
       addOrderByDescending(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> orderBySpeedKmhAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderBySpeedKmhDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(GpsLog.CREATED_AT_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(GpsLog.CREATED_AT_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByVersionAscending(){
       addOrderByAscending(GpsLog.VERSION_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByVersionDescending(){
       addOrderByDescending(GpsLog.VERSION_PROPERTY);
       return this;
    }


    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }








   public GpsLogRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle){
       return facetByVehicleAs(facetName, vehicle, true);
   }

   public GpsLogRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle, boolean includeAllFacets){
       addFacet(facetName, GpsLog.VEHICLE_PROPERTY, vehicle, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public GpsLogRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public GpsLogRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public GpsLogRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public GpsLogRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public GpsLogRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}