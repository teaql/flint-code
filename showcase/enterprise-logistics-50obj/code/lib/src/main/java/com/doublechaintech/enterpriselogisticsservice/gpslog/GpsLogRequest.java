package com.doublechaintech.enterpriselogisticsservice.gpslog;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceRequest;
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
        return selectId().selectLatitude().selectLongitude().selectSpeedKmh().selectHeading().selectTimestamp().selectDeviceIdOnly().selectVersion();
    }

    public GpsLogRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public GpsLogRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectLatitude().selectLongitude().selectSpeedKmh().selectHeading().selectTimestamp().selectDevice().selectVersion();
    }

    public GpsLogRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectLatitude().selectLongitude().selectSpeedKmh().selectHeading().selectTimestamp().selectDevice().selectVersion();
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
    public GpsLogRequest<T> selectLatitude(){
       selectProperty(GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    /**
     * fill the latitude with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  latitude) to fetch latitude property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the latitude with customized aggrFunction, TEAQL uses ({aggrFunction}(latitude) AS latitude to fetch latitude property.
     * @param aggrFunction  aggrFunction
     */
    public GpsLogRequest<T> selectLatitude(AggrFunction aggrFunction){
       selectProperty(GpsLog.LATITUDE_PROPERTY, aggrFunction);
       return this;
    }


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


    /**
     * fill the longitude with customized aggrFunction, TEAQL uses ({aggrFunction}(longitude) AS longitude to fetch longitude property.
     * @param aggrFunction  aggrFunction
     */
    public GpsLogRequest<T> selectLongitude(AggrFunction aggrFunction){
       selectProperty(GpsLog.LONGITUDE_PROPERTY, aggrFunction);
       return this;
    }


    public GpsLogRequest<T> unselectLongitude(){
       unselectProperty(GpsLog.LONGITUDE_PROPERTY);
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


    /**
     * fill the speedKmh with customized aggrFunction, TEAQL uses ({aggrFunction}(speedKmh) AS speedKmh to fetch speedKmh property.
     * @param aggrFunction  aggrFunction
     */
    public GpsLogRequest<T> selectSpeedKmh(AggrFunction aggrFunction){
       selectProperty(GpsLog.SPEED_KMH_PROPERTY, aggrFunction);
       return this;
    }


    public GpsLogRequest<T> unselectSpeedKmh(){
       unselectProperty(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }
    public GpsLogRequest<T> selectHeading(){
       selectProperty(GpsLog.HEADING_PROPERTY);
       return this;
    }

    /**
     * fill the heading with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  heading) to fetch heading property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the heading with customized aggrFunction, TEAQL uses ({aggrFunction}(heading) AS heading to fetch heading property.
     * @param aggrFunction  aggrFunction
     */
    public GpsLogRequest<T> selectHeading(AggrFunction aggrFunction){
       selectProperty(GpsLog.HEADING_PROPERTY, aggrFunction);
       return this;
    }


    public GpsLogRequest<T> unselectHeading(){
       unselectProperty(GpsLog.HEADING_PROPERTY);
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
    public GpsLogRequest<T> selectDeviceIdOnly(){
       selectProperty(GpsLog.DEVICE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> selectDevice(){
        return selectDeviceWith(Q.telematicsDevices().unlimited().selectSelf());
    }

    public GpsLogRequest<T> selectDeviceWith(TelematicsDeviceRequest device){
       selectProperty(GpsLog.DEVICE_PROPERTY);
       enhanceRelation(GpsLog.DEVICE_PROPERTY, device);
       return this;
    }

    public GpsLogRequest<T> unselectDevice(){
       unselectProperty(GpsLog.DEVICE_PROPERTY);
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



    public GpsLogRequest<T> filterByLatitude(BigDecimal... latitude){
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

    public GpsLogRequest<T> withLatitudeGreaterThan(BigDecimal latitude){
       return withLatitude(Operator.GREATER_THAN, latitude);
    }

    public GpsLogRequest<T> withLatitudeGreaterThanOrEqualTo(BigDecimal latitude){
       return withLatitude(Operator.GREATER_THAN_OR_EQUAL, latitude);
    }

    public GpsLogRequest<T> withLatitudeLessThan(BigDecimal latitude){
       return withLatitude(Operator.LESS_THAN, latitude);
    }

    public GpsLogRequest<T> withLatitudeLessThanOrEqualTo(BigDecimal latitude){
       return withLatitude(Operator.LESS_THAN_OR_EQUAL, latitude);
    }

    public GpsLogRequest<T> withLatitudeBetween(BigDecimal startOfLatitude, BigDecimal endOfLatitude){
       return withLatitude(Operator.BETWEEN, startOfLatitude, endOfLatitude);
    }



    public GpsLogRequest<T> filterByLongitude(BigDecimal... longitude){
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

    public GpsLogRequest<T> withLongitudeGreaterThan(BigDecimal longitude){
       return withLongitude(Operator.GREATER_THAN, longitude);
    }

    public GpsLogRequest<T> withLongitudeGreaterThanOrEqualTo(BigDecimal longitude){
       return withLongitude(Operator.GREATER_THAN_OR_EQUAL, longitude);
    }

    public GpsLogRequest<T> withLongitudeLessThan(BigDecimal longitude){
       return withLongitude(Operator.LESS_THAN, longitude);
    }

    public GpsLogRequest<T> withLongitudeLessThanOrEqualTo(BigDecimal longitude){
       return withLongitude(Operator.LESS_THAN_OR_EQUAL, longitude);
    }

    public GpsLogRequest<T> withLongitudeBetween(BigDecimal startOfLongitude, BigDecimal endOfLongitude){
       return withLongitude(Operator.BETWEEN, startOfLongitude, endOfLongitude);
    }



    public GpsLogRequest<T> filterBySpeedKmh(Integer... speedKmh){
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

    public GpsLogRequest<T> withSpeedKmhGreaterThan(Integer speedKmh){
       return withSpeedKmh(Operator.GREATER_THAN, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhGreaterThanOrEqualTo(Integer speedKmh){
       return withSpeedKmh(Operator.GREATER_THAN_OR_EQUAL, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhLessThan(Integer speedKmh){
       return withSpeedKmh(Operator.LESS_THAN, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhLessThanOrEqualTo(Integer speedKmh){
       return withSpeedKmh(Operator.LESS_THAN_OR_EQUAL, speedKmh);
    }

    public GpsLogRequest<T> withSpeedKmhBetween(Integer startOfSpeedKmh, Integer endOfSpeedKmh){
       return withSpeedKmh(Operator.BETWEEN, startOfSpeedKmh, endOfSpeedKmh);
    }



    public GpsLogRequest<T> filterByHeading(Integer... heading){
      if (heading == null || heading.length == 0) {
        throw new IllegalArgumentException("filterByHeading parameter heading cannot be empty");
      }
      return appendSearchCriteria(createHeadingCriteria(Operator.EQUAL, (Object[])heading));
    }

    public GpsLogRequest<T> withHeading(Operator operator, Object... values){
       return appendSearchCriteria(createHeadingCriteria(operator, values));
    }

    public GpsLogRequest<T> withHeadingIsUnknown(){
       return withHeading(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withHeadingIsKnown(){
       return withHeading(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createHeadingCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.HEADING_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> withHeadingGreaterThan(Integer heading){
       return withHeading(Operator.GREATER_THAN, heading);
    }

    public GpsLogRequest<T> withHeadingGreaterThanOrEqualTo(Integer heading){
       return withHeading(Operator.GREATER_THAN_OR_EQUAL, heading);
    }

    public GpsLogRequest<T> withHeadingLessThan(Integer heading){
       return withHeading(Operator.LESS_THAN, heading);
    }

    public GpsLogRequest<T> withHeadingLessThanOrEqualTo(Integer heading){
       return withHeading(Operator.LESS_THAN_OR_EQUAL, heading);
    }

    public GpsLogRequest<T> withHeadingBetween(Integer startOfHeading, Integer endOfHeading){
       return withHeading(Operator.BETWEEN, startOfHeading, endOfHeading);
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




    public GpsLogRequest<T> filterByDevice(TelematicsDevice... device){
      if (device == null || device.length == 0) {
        throw new IllegalArgumentException("filterByDevice parameter device cannot be empty");
      }
      return appendSearchCriteria(createDeviceCriteria(Operator.EQUAL, (Object[])device));
    }

    public GpsLogRequest<T> withDevice(Operator operator, Object... values){
       return appendSearchCriteria(createDeviceCriteria(operator, values));
    }

    public GpsLogRequest<T> withDeviceIsUnknown(){
       return withDevice(Operator.IS_NULL);
    }

    public GpsLogRequest<T> withDeviceIsKnown(){
       return withDevice(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeviceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(GpsLog.DEVICE_PROPERTY, operator, values);
    }

    public GpsLogRequest<T> filterByDevice(Long device){
      if(device == null){
         return this;
      }
      return withDevice(Operator.EQUAL, device);
    }
    public GpsLogRequest<T> withDeviceMatching(TelematicsDeviceRequest device){
       return appendSearchCriteria(new SubQuerySearchCriteria(GpsLog.DEVICE_PROPERTY, device, TelematicsDevice.ID_PROPERTY));
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
    public GpsLogRequest minLatitude(){
        return minLatitudeAs(prefix("minOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest minLatitudeAs(String retName){
        super.min(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest maxLatitude(){
        return maxLatitudeAs(prefix("maxOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest maxLatitudeAs(String retName){
        super.max(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest sumLatitude(){
        return sumLatitudeAs(prefix("sumOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest sumLatitudeAs(String retName){
        super.sum(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest avgLatitude(){
        return avgLatitudeAs(prefix("avgOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest avgLatitudeAs(String retName){
        super.avg(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest standardDeviationLatitude(){
        return standardDeviationLatitudeAs(prefix("standardDeviationOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest standardDeviationLatitudeAs(String retName){
        super.standardDeviation(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest squareRootOfPopulationStandardDeviationLatitude(){
        return squareRootOfPopulationStandardDeviationLatitudeAs(prefix("squareRootOfPopulationStandardDeviationOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest squareRootOfPopulationStandardDeviationLatitudeAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest sampleVarianceLatitude(){
        return sampleVarianceLatitudeAs(prefix("sampleVarianceOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest sampleVarianceLatitudeAs(String retName){
        super.sampleVariance(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest samplePopulationVarianceLatitude(){
        return samplePopulationVarianceLatitudeAs(prefix("samplePopulationVarianceOf",GpsLog.LATITUDE_PROPERTY));
    }

    public GpsLogRequest samplePopulationVarianceLatitudeAs(String retName){
        super.samplePopulationVariance(retName, GpsLog.LATITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest minLongitude(){
        return minLongitudeAs(prefix("minOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest minLongitudeAs(String retName){
        super.min(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest maxLongitude(){
        return maxLongitudeAs(prefix("maxOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest maxLongitudeAs(String retName){
        super.max(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest sumLongitude(){
        return sumLongitudeAs(prefix("sumOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest sumLongitudeAs(String retName){
        super.sum(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest avgLongitude(){
        return avgLongitudeAs(prefix("avgOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest avgLongitudeAs(String retName){
        super.avg(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest standardDeviationLongitude(){
        return standardDeviationLongitudeAs(prefix("standardDeviationOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest standardDeviationLongitudeAs(String retName){
        super.standardDeviation(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest squareRootOfPopulationStandardDeviationLongitude(){
        return squareRootOfPopulationStandardDeviationLongitudeAs(prefix("squareRootOfPopulationStandardDeviationOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest squareRootOfPopulationStandardDeviationLongitudeAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest sampleVarianceLongitude(){
        return sampleVarianceLongitudeAs(prefix("sampleVarianceOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest sampleVarianceLongitudeAs(String retName){
        super.sampleVariance(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest samplePopulationVarianceLongitude(){
        return samplePopulationVarianceLongitudeAs(prefix("samplePopulationVarianceOf",GpsLog.LONGITUDE_PROPERTY));
    }

    public GpsLogRequest samplePopulationVarianceLongitudeAs(String retName){
        super.samplePopulationVariance(retName, GpsLog.LONGITUDE_PROPERTY);
        return this;
    }
    public GpsLogRequest minSpeedKmh(){
        return minSpeedKmhAs(prefix("minOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest minSpeedKmhAs(String retName){
        super.min(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest maxSpeedKmh(){
        return maxSpeedKmhAs(prefix("maxOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest maxSpeedKmhAs(String retName){
        super.max(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest sumSpeedKmh(){
        return sumSpeedKmhAs(prefix("sumOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest sumSpeedKmhAs(String retName){
        super.sum(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest avgSpeedKmh(){
        return avgSpeedKmhAs(prefix("avgOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest avgSpeedKmhAs(String retName){
        super.avg(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest standardDeviationSpeedKmh(){
        return standardDeviationSpeedKmhAs(prefix("standardDeviationOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest standardDeviationSpeedKmhAs(String retName){
        super.standardDeviation(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest squareRootOfPopulationStandardDeviationSpeedKmh(){
        return squareRootOfPopulationStandardDeviationSpeedKmhAs(prefix("squareRootOfPopulationStandardDeviationOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest squareRootOfPopulationStandardDeviationSpeedKmhAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest sampleVarianceSpeedKmh(){
        return sampleVarianceSpeedKmhAs(prefix("sampleVarianceOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest sampleVarianceSpeedKmhAs(String retName){
        super.sampleVariance(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest samplePopulationVarianceSpeedKmh(){
        return samplePopulationVarianceSpeedKmhAs(prefix("samplePopulationVarianceOf",GpsLog.SPEED_KMH_PROPERTY));
    }

    public GpsLogRequest samplePopulationVarianceSpeedKmhAs(String retName){
        super.samplePopulationVariance(retName, GpsLog.SPEED_KMH_PROPERTY);
        return this;
    }
    public GpsLogRequest minHeading(){
        return minHeadingAs(prefix("minOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest minHeadingAs(String retName){
        super.min(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest maxHeading(){
        return maxHeadingAs(prefix("maxOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest maxHeadingAs(String retName){
        super.max(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest sumHeading(){
        return sumHeadingAs(prefix("sumOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest sumHeadingAs(String retName){
        super.sum(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest avgHeading(){
        return avgHeadingAs(prefix("avgOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest avgHeadingAs(String retName){
        super.avg(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest standardDeviationHeading(){
        return standardDeviationHeadingAs(prefix("standardDeviationOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest standardDeviationHeadingAs(String retName){
        super.standardDeviation(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest squareRootOfPopulationStandardDeviationHeading(){
        return squareRootOfPopulationStandardDeviationHeadingAs(prefix("squareRootOfPopulationStandardDeviationOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest squareRootOfPopulationStandardDeviationHeadingAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest sampleVarianceHeading(){
        return sampleVarianceHeadingAs(prefix("sampleVarianceOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest sampleVarianceHeadingAs(String retName){
        super.sampleVariance(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest samplePopulationVarianceHeading(){
        return samplePopulationVarianceHeadingAs(prefix("samplePopulationVarianceOf",GpsLog.HEADING_PROPERTY));
    }

    public GpsLogRequest samplePopulationVarianceHeadingAs(String retName){
        super.samplePopulationVariance(retName, GpsLog.HEADING_PROPERTY);
        return this;
    }
    public GpsLogRequest<T> groupByDeviceWithDetails(){
       return groupByDeviceWithDetails(Q.telematicsDevices().unlimited());
    }

    public GpsLogRequest<T> groupByDeviceWithDetails(TelematicsDeviceRequest subRequest){
       aggregate(GpsLog.DEVICE_PROPERTY, subRequest);
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

    public GpsLogRequest<T> groupByHeading(){
       groupBy(GpsLog.HEADING_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByHeadingAs(String retName){
       groupBy(retName, GpsLog.HEADING_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByHeadingWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.HEADING_PROPERTY, function);
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
    public GpsLogRequest<T> groupByDeviceWith(TelematicsDeviceRequest subRequest){
       groupBy(GpsLog.DEVICE_PROPERTY, subRequest);
       return this;
    }
    public GpsLogRequest<T> groupByDevice(){
       groupBy(GpsLog.DEVICE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByDeviceAs(String retName){
       groupBy(retName, GpsLog.DEVICE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> groupByDeviceWithFunction(String retName, AggrFunction function){
       groupBy(retName, GpsLog.DEVICE_PROPERTY, function);
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

    public GpsLogRequest<T> orderByLatitudeAscending(){
       addOrderByAscending(GpsLog.LATITUDE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByLatitudeDescending(){
       addOrderByDescending(GpsLog.LATITUDE_PROPERTY);
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

    public GpsLogRequest<T> orderBySpeedKmhAscending(){
       addOrderByAscending(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderBySpeedKmhDescending(){
       addOrderByDescending(GpsLog.SPEED_KMH_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByHeadingAscending(){
       addOrderByAscending(GpsLog.HEADING_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByHeadingDescending(){
       addOrderByDescending(GpsLog.HEADING_PROPERTY);
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

    public GpsLogRequest<T> orderByDeviceAscending(){
       addOrderByAscending(GpsLog.DEVICE_PROPERTY);
       return this;
    }

    public GpsLogRequest<T> orderByDeviceDescending(){
       addOrderByDescending(GpsLog.DEVICE_PROPERTY);
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


    public TelematicsDeviceRequest rollUpToDevice(){
       TelematicsDeviceRequest device = Q.telematicsDevices().unlimited();
       this.withDeviceMatching(device)
           .groupByDeviceWith(device);
       return device;
    }



   public GpsLogRequest<T> facetByDeviceAs(String facetName, TelematicsDeviceRequest device){
       return facetByDeviceAs(facetName, device, true);
   }

   public GpsLogRequest<T> facetByDeviceAs(String facetName, TelematicsDeviceRequest device, boolean includeAllFacets){
       addFacet(facetName, GpsLog.DEVICE_PROPERTY, device, includeAllFacets);
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