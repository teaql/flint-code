package com.doublechaintech.enterpriselogisticsservice.transitroute;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseRequest;
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

public class TransitRouteRequest<T extends TransitRoute> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public TransitRouteRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public TransitRouteRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public TransitRouteRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public TransitRouteRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public TransitRouteRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public TransitRouteRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public TransitRouteRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (TransitRouteRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public TransitRouteRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public TransitRouteRequest<T> matchingAnyOf(TransitRouteRequest transitRoute){
        super.internalMatchAny(transitRoute);
        return this;
    }

    public TransitRouteRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public TransitRouteRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public TransitRouteRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public TransitRouteRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectRouteId().selectName().selectOriginWarehouseIdOnly().selectDestinationWarehouseIdOnly().selectDistanceKm().selectEstimatedDurationHours().selectStatus().selectCreateTime().selectVersion();
    }

    public TransitRouteRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TransitRouteRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectRouteId().selectName().selectOriginWarehouse().selectDestinationWarehouse().selectDistanceKm().selectEstimatedDurationHours().selectStatus().selectCreateTime().selectVersion();
    }

    public TransitRouteRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectRouteId().selectName().selectOriginWarehouse().selectDestinationWarehouse().selectDistanceKm().selectEstimatedDurationHours().selectStatus().selectCreateTime().selectVersion();
    }


    public TransitRouteRequest<T> selectId(){
       selectProperty(TransitRoute.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectId(){
       unselectProperty(TransitRoute.ID_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectRouteId(){
       selectProperty(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }

    /**
     * fill the routeId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  routeId) to fetch routeId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectRouteId(){
       unselectProperty(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectName(){
       selectProperty(TransitRoute.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectName(){
       unselectProperty(TransitRoute.NAME_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectOriginWarehouseIdOnly(){
       selectProperty(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> selectOriginWarehouse(){
        return selectOriginWarehouseWith(Q.warehouses().unlimited().selectSelf());
    }

    public TransitRouteRequest<T> selectOriginWarehouseWith(WarehouseRequest originWarehouse){
       selectProperty(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       enhanceRelation(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, originWarehouse);
       return this;
    }

    public TransitRouteRequest<T> unselectOriginWarehouse(){
       unselectProperty(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectDestinationWarehouseIdOnly(){
       selectProperty(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> selectDestinationWarehouse(){
        return selectDestinationWarehouseWith(Q.warehouses().unlimited().selectSelf());
    }

    public TransitRouteRequest<T> selectDestinationWarehouseWith(WarehouseRequest destinationWarehouse){
       selectProperty(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       enhanceRelation(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, destinationWarehouse);
       return this;
    }

    public TransitRouteRequest<T> unselectDestinationWarehouse(){
       unselectProperty(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectDistanceKm(){
       selectProperty(TransitRoute.DISTANCE_KM_PROPERTY);
       return this;
    }

    /**
     * fill the distanceKm with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  distanceKm) to fetch distanceKm property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the distanceKm with customized aggrFunction, TEAQL uses ({aggrFunction}(distanceKm) AS distanceKm to fetch distanceKm property.
     * @param aggrFunction  aggrFunction
     */
    public TransitRouteRequest<T> selectDistanceKm(AggrFunction aggrFunction){
       selectProperty(TransitRoute.DISTANCE_KM_PROPERTY, aggrFunction);
       return this;
    }


    public TransitRouteRequest<T> unselectDistanceKm(){
       unselectProperty(TransitRoute.DISTANCE_KM_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectEstimatedDurationHours(){
       selectProperty(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
       return this;
    }

    /**
     * fill the estimatedDurationHours with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  estimatedDurationHours) to fetch estimatedDurationHours property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the estimatedDurationHours with customized aggrFunction, TEAQL uses ({aggrFunction}(estimatedDurationHours) AS estimatedDurationHours to fetch estimatedDurationHours property.
     * @param aggrFunction  aggrFunction
     */
    public TransitRouteRequest<T> selectEstimatedDurationHours(AggrFunction aggrFunction){
       selectProperty(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY, aggrFunction);
       return this;
    }


    public TransitRouteRequest<T> unselectEstimatedDurationHours(){
       unselectProperty(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectStatus(){
       selectProperty(TransitRoute.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectStatus(){
       unselectProperty(TransitRoute.STATUS_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectCreateTime(){
       selectProperty(TransitRoute.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectCreateTime(){
       unselectProperty(TransitRoute.CREATE_TIME_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectVersion(){
       selectProperty(TransitRoute.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectVersion(){
       unselectProperty(TransitRoute.VERSION_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.ID_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public TransitRouteRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public TransitRouteRequest<T> filterByRouteId(String... routeId){
      if (routeId == null || routeId.length == 0) {
        throw new IllegalArgumentException("filterByRouteId parameter routeId cannot be empty");
      }
      return appendSearchCriteria(createRouteIdCriteria(Operator.EQUAL, (Object[])routeId));
    }

    public TransitRouteRequest<T> withRouteId(Operator operator, Object... values){
       return appendSearchCriteria(createRouteIdCriteria(operator, values));
    }

    public TransitRouteRequest<T> withRouteIdIsUnknown(){
       return withRouteId(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withRouteIdIsKnown(){
       return withRouteId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRouteIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.ROUTE_ID_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withRouteIdGreaterThan(String routeId){
       return withRouteId(Operator.GREATER_THAN, routeId);
    }

    public TransitRouteRequest<T> withRouteIdGreaterThanOrEqualTo(String routeId){
       return withRouteId(Operator.GREATER_THAN_OR_EQUAL, routeId);
    }

    public TransitRouteRequest<T> withRouteIdLessThan(String routeId){
       return withRouteId(Operator.LESS_THAN, routeId);
    }

    public TransitRouteRequest<T> withRouteIdLessThanOrEqualTo(String routeId){
       return withRouteId(Operator.LESS_THAN_OR_EQUAL, routeId);
    }

    public TransitRouteRequest<T> withRouteIdBetween(String startOfRouteId, String endOfRouteId){
       return withRouteId(Operator.BETWEEN, startOfRouteId, endOfRouteId);
    }
    public TransitRouteRequest<T> withRouteIdStartingWith(String routeId){
       return withRouteId(Operator.BEGIN_WITH, routeId);
    }
    public TransitRouteRequest<T> withRouteIdContaining(String routeId){
       return withRouteId(Operator.CONTAIN, routeId);
    }

    public TransitRouteRequest<T> withRouteIdEndingWith(String routeId){
       return withRouteId(Operator.END_WITH, routeId);
    }

    public TransitRouteRequest<T> withRouteIdIs(String routeId){
       return withRouteId(Operator.EQUAL, routeId);
    }

    public TransitRouteRequest<T> withRouteIdSoundingLike(String routeId){
       return withRouteId(Operator.SOUNDS_LIKE, routeId);
    }



    public TransitRouteRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public TransitRouteRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public TransitRouteRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.NAME_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public TransitRouteRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public TransitRouteRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public TransitRouteRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public TransitRouteRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public TransitRouteRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public TransitRouteRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public TransitRouteRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public TransitRouteRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public TransitRouteRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public TransitRouteRequest<T> filterByOriginWarehouse(Warehouse... originWarehouse){
      if (originWarehouse == null || originWarehouse.length == 0) {
        throw new IllegalArgumentException("filterByOriginWarehouse parameter originWarehouse cannot be empty");
      }
      return appendSearchCriteria(createOriginWarehouseCriteria(Operator.EQUAL, (Object[])originWarehouse));
    }

    public TransitRouteRequest<T> withOriginWarehouse(Operator operator, Object... values){
       return appendSearchCriteria(createOriginWarehouseCriteria(operator, values));
    }

    public TransitRouteRequest<T> withOriginWarehouseIsUnknown(){
       return withOriginWarehouse(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withOriginWarehouseIsKnown(){
       return withOriginWarehouse(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createOriginWarehouseCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> filterByOriginWarehouse(Long originWarehouse){
      if(originWarehouse == null){
         return this;
      }
      return withOriginWarehouse(Operator.EQUAL, originWarehouse);
    }
    public TransitRouteRequest<T> withOriginWarehouseMatching(WarehouseRequest originWarehouse){
       return appendSearchCriteria(new SubQuerySearchCriteria(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, originWarehouse, Warehouse.ID_PROPERTY));
    }

    public TransitRouteRequest<T> filterByDestinationWarehouse(Warehouse... destinationWarehouse){
      if (destinationWarehouse == null || destinationWarehouse.length == 0) {
        throw new IllegalArgumentException("filterByDestinationWarehouse parameter destinationWarehouse cannot be empty");
      }
      return appendSearchCriteria(createDestinationWarehouseCriteria(Operator.EQUAL, (Object[])destinationWarehouse));
    }

    public TransitRouteRequest<T> withDestinationWarehouse(Operator operator, Object... values){
       return appendSearchCriteria(createDestinationWarehouseCriteria(operator, values));
    }

    public TransitRouteRequest<T> withDestinationWarehouseIsUnknown(){
       return withDestinationWarehouse(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withDestinationWarehouseIsKnown(){
       return withDestinationWarehouse(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDestinationWarehouseCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> filterByDestinationWarehouse(Long destinationWarehouse){
      if(destinationWarehouse == null){
         return this;
      }
      return withDestinationWarehouse(Operator.EQUAL, destinationWarehouse);
    }
    public TransitRouteRequest<T> withDestinationWarehouseMatching(WarehouseRequest destinationWarehouse){
       return appendSearchCriteria(new SubQuerySearchCriteria(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, destinationWarehouse, Warehouse.ID_PROPERTY));
    }

    public TransitRouteRequest<T> filterByDistanceKm(BigDecimal... distanceKm){
      if (distanceKm == null || distanceKm.length == 0) {
        throw new IllegalArgumentException("filterByDistanceKm parameter distanceKm cannot be empty");
      }
      return appendSearchCriteria(createDistanceKmCriteria(Operator.EQUAL, (Object[])distanceKm));
    }

    public TransitRouteRequest<T> withDistanceKm(Operator operator, Object... values){
       return appendSearchCriteria(createDistanceKmCriteria(operator, values));
    }

    public TransitRouteRequest<T> withDistanceKmIsUnknown(){
       return withDistanceKm(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withDistanceKmIsKnown(){
       return withDistanceKm(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDistanceKmCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.DISTANCE_KM_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withDistanceKmGreaterThan(BigDecimal distanceKm){
       return withDistanceKm(Operator.GREATER_THAN, distanceKm);
    }

    public TransitRouteRequest<T> withDistanceKmGreaterThanOrEqualTo(BigDecimal distanceKm){
       return withDistanceKm(Operator.GREATER_THAN_OR_EQUAL, distanceKm);
    }

    public TransitRouteRequest<T> withDistanceKmLessThan(BigDecimal distanceKm){
       return withDistanceKm(Operator.LESS_THAN, distanceKm);
    }

    public TransitRouteRequest<T> withDistanceKmLessThanOrEqualTo(BigDecimal distanceKm){
       return withDistanceKm(Operator.LESS_THAN_OR_EQUAL, distanceKm);
    }

    public TransitRouteRequest<T> withDistanceKmBetween(BigDecimal startOfDistanceKm, BigDecimal endOfDistanceKm){
       return withDistanceKm(Operator.BETWEEN, startOfDistanceKm, endOfDistanceKm);
    }



    public TransitRouteRequest<T> filterByEstimatedDurationHours(BigDecimal... estimatedDurationHours){
      if (estimatedDurationHours == null || estimatedDurationHours.length == 0) {
        throw new IllegalArgumentException("filterByEstimatedDurationHours parameter estimatedDurationHours cannot be empty");
      }
      return appendSearchCriteria(createEstimatedDurationHoursCriteria(Operator.EQUAL, (Object[])estimatedDurationHours));
    }

    public TransitRouteRequest<T> withEstimatedDurationHours(Operator operator, Object... values){
       return appendSearchCriteria(createEstimatedDurationHoursCriteria(operator, values));
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursIsUnknown(){
       return withEstimatedDurationHours(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursIsKnown(){
       return withEstimatedDurationHours(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEstimatedDurationHoursCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursGreaterThan(BigDecimal estimatedDurationHours){
       return withEstimatedDurationHours(Operator.GREATER_THAN, estimatedDurationHours);
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursGreaterThanOrEqualTo(BigDecimal estimatedDurationHours){
       return withEstimatedDurationHours(Operator.GREATER_THAN_OR_EQUAL, estimatedDurationHours);
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursLessThan(BigDecimal estimatedDurationHours){
       return withEstimatedDurationHours(Operator.LESS_THAN, estimatedDurationHours);
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursLessThanOrEqualTo(BigDecimal estimatedDurationHours){
       return withEstimatedDurationHours(Operator.LESS_THAN_OR_EQUAL, estimatedDurationHours);
    }

    public TransitRouteRequest<T> withEstimatedDurationHoursBetween(BigDecimal startOfEstimatedDurationHours, BigDecimal endOfEstimatedDurationHours){
       return withEstimatedDurationHours(Operator.BETWEEN, startOfEstimatedDurationHours, endOfEstimatedDurationHours);
    }



    public TransitRouteRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public TransitRouteRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public TransitRouteRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.STATUS_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public TransitRouteRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public TransitRouteRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public TransitRouteRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public TransitRouteRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public TransitRouteRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public TransitRouteRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public TransitRouteRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public TransitRouteRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public TransitRouteRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public TransitRouteRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public TransitRouteRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public TransitRouteRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.CREATE_TIME_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public TransitRouteRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public TransitRouteRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public TransitRouteRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public TransitRouteRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public TransitRouteRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.VERSION_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public TransitRouteRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public TransitRouteRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public TransitRouteRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public TransitRouteRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public TransitRouteRequest<T> count(){
        super.count();
        return this;
    }
    public TransitRouteRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public TransitRouteRequest minDistanceKm(){
        return minDistanceKmAs(prefix("minOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest minDistanceKmAs(String retName){
        super.min(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest maxDistanceKm(){
        return maxDistanceKmAs(prefix("maxOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest maxDistanceKmAs(String retName){
        super.max(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest sumDistanceKm(){
        return sumDistanceKmAs(prefix("sumOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest sumDistanceKmAs(String retName){
        super.sum(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest avgDistanceKm(){
        return avgDistanceKmAs(prefix("avgOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest avgDistanceKmAs(String retName){
        super.avg(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest standardDeviationDistanceKm(){
        return standardDeviationDistanceKmAs(prefix("standardDeviationOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest standardDeviationDistanceKmAs(String retName){
        super.standardDeviation(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest squareRootOfPopulationStandardDeviationDistanceKm(){
        return squareRootOfPopulationStandardDeviationDistanceKmAs(prefix("squareRootOfPopulationStandardDeviationOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest squareRootOfPopulationStandardDeviationDistanceKmAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest sampleVarianceDistanceKm(){
        return sampleVarianceDistanceKmAs(prefix("sampleVarianceOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest sampleVarianceDistanceKmAs(String retName){
        super.sampleVariance(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest samplePopulationVarianceDistanceKm(){
        return samplePopulationVarianceDistanceKmAs(prefix("samplePopulationVarianceOf",TransitRoute.DISTANCE_KM_PROPERTY));
    }

    public TransitRouteRequest samplePopulationVarianceDistanceKmAs(String retName){
        super.samplePopulationVariance(retName, TransitRoute.DISTANCE_KM_PROPERTY);
        return this;
    }
    public TransitRouteRequest minEstimatedDurationHours(){
        return minEstimatedDurationHoursAs(prefix("minOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest minEstimatedDurationHoursAs(String retName){
        super.min(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest maxEstimatedDurationHours(){
        return maxEstimatedDurationHoursAs(prefix("maxOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest maxEstimatedDurationHoursAs(String retName){
        super.max(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest sumEstimatedDurationHours(){
        return sumEstimatedDurationHoursAs(prefix("sumOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest sumEstimatedDurationHoursAs(String retName){
        super.sum(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest avgEstimatedDurationHours(){
        return avgEstimatedDurationHoursAs(prefix("avgOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest avgEstimatedDurationHoursAs(String retName){
        super.avg(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest standardDeviationEstimatedDurationHours(){
        return standardDeviationEstimatedDurationHoursAs(prefix("standardDeviationOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest standardDeviationEstimatedDurationHoursAs(String retName){
        super.standardDeviation(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest squareRootOfPopulationStandardDeviationEstimatedDurationHours(){
        return squareRootOfPopulationStandardDeviationEstimatedDurationHoursAs(prefix("squareRootOfPopulationStandardDeviationOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest squareRootOfPopulationStandardDeviationEstimatedDurationHoursAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest sampleVarianceEstimatedDurationHours(){
        return sampleVarianceEstimatedDurationHoursAs(prefix("sampleVarianceOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest sampleVarianceEstimatedDurationHoursAs(String retName){
        super.sampleVariance(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest samplePopulationVarianceEstimatedDurationHours(){
        return samplePopulationVarianceEstimatedDurationHoursAs(prefix("samplePopulationVarianceOf",TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
    }

    public TransitRouteRequest samplePopulationVarianceEstimatedDurationHoursAs(String retName){
        super.samplePopulationVariance(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
        return this;
    }
    public TransitRouteRequest<T> groupByOriginWarehouseWithDetails(){
       return groupByOriginWarehouseWithDetails(Q.warehouses().unlimited());
    }

    public TransitRouteRequest<T> groupByOriginWarehouseWithDetails(WarehouseRequest subRequest){
       aggregate(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, subRequest);
       return this;
    }

    public TransitRouteRequest<T> groupByDestinationWarehouseWithDetails(){
       return groupByDestinationWarehouseWithDetails(Q.warehouses().unlimited());
    }

    public TransitRouteRequest<T> groupByDestinationWarehouseWithDetails(WarehouseRequest subRequest){
       aggregate(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, subRequest);
       return this;
    }







    public TransitRouteRequest<T> groupById(){
       groupBy(TransitRoute.ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByIdAs(String retName){
       groupBy(retName, TransitRoute.ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.ID_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByRouteId(){
       groupBy(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByRouteIdAs(String retName){
       groupBy(retName, TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByRouteIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.ROUTE_ID_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByName(){
       groupBy(TransitRoute.NAME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByNameAs(String retName){
       groupBy(retName, TransitRoute.NAME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.NAME_PROPERTY, function);
       return this;
    }
    public TransitRouteRequest<T> groupByOriginWarehouseWith(WarehouseRequest subRequest){
       groupBy(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, subRequest);
       return this;
    }
    public TransitRouteRequest<T> groupByOriginWarehouse(){
       groupBy(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByOriginWarehouseAs(String retName){
       groupBy(retName, TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByOriginWarehouseWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, function);
       return this;
    }
    public TransitRouteRequest<T> groupByDestinationWarehouseWith(WarehouseRequest subRequest){
       groupBy(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, subRequest);
       return this;
    }
    public TransitRouteRequest<T> groupByDestinationWarehouse(){
       groupBy(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByDestinationWarehouseAs(String retName){
       groupBy(retName, TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByDestinationWarehouseWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByDistanceKm(){
       groupBy(TransitRoute.DISTANCE_KM_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByDistanceKmAs(String retName){
       groupBy(retName, TransitRoute.DISTANCE_KM_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByDistanceKmWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.DISTANCE_KM_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByEstimatedDurationHours(){
       groupBy(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByEstimatedDurationHoursAs(String retName){
       groupBy(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByEstimatedDurationHoursWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByStatus(){
       groupBy(TransitRoute.STATUS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByStatusAs(String retName){
       groupBy(retName, TransitRoute.STATUS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.STATUS_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByCreateTime(){
       groupBy(TransitRoute.CREATE_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, TransitRoute.CREATE_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByVersion(){
       groupBy(TransitRoute.VERSION_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByVersionAs(String retName){
       groupBy(retName, TransitRoute.VERSION_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.VERSION_PROPERTY, function);
       return this;
    }



    public TransitRouteRequest<T> orderByIdAscending(){
       addOrderByAscending(TransitRoute.ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByIdDescending(){
       addOrderByDescending(TransitRoute.ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByRouteIdAscending(){
       addOrderByAscending(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByRouteIdDescending(){
       addOrderByDescending(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByRouteIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByRouteIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TransitRoute.ROUTE_ID_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByNameAscending(){
       addOrderByAscending(TransitRoute.NAME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByNameDescending(){
       addOrderByDescending(TransitRoute.NAME_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TransitRoute.NAME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TransitRoute.NAME_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByOriginWarehouseAscending(){
       addOrderByAscending(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByOriginWarehouseDescending(){
       addOrderByDescending(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByDestinationWarehouseAscending(){
       addOrderByAscending(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByDestinationWarehouseDescending(){
       addOrderByDescending(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByDistanceKmAscending(){
       addOrderByAscending(TransitRoute.DISTANCE_KM_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByDistanceKmDescending(){
       addOrderByDescending(TransitRoute.DISTANCE_KM_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByEstimatedDurationHoursAscending(){
       addOrderByAscending(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByEstimatedDurationHoursDescending(){
       addOrderByDescending(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByStatusAscending(){
       addOrderByAscending(TransitRoute.STATUS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByStatusDescending(){
       addOrderByDescending(TransitRoute.STATUS_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TransitRoute.STATUS_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TransitRoute.STATUS_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(TransitRoute.CREATE_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(TransitRoute.CREATE_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByVersionAscending(){
       addOrderByAscending(TransitRoute.VERSION_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByVersionDescending(){
       addOrderByDescending(TransitRoute.VERSION_PROPERTY);
       return this;
    }


    public WarehouseRequest rollUpToOriginWarehouse(){
       WarehouseRequest originWarehouse = Q.warehouses().unlimited();
       this.withOriginWarehouseMatching(originWarehouse)
           .groupByOriginWarehouseWith(originWarehouse);
       return originWarehouse;
    }

    public WarehouseRequest rollUpToDestinationWarehouse(){
       WarehouseRequest destinationWarehouse = Q.warehouses().unlimited();
       this.withDestinationWarehouseMatching(destinationWarehouse)
           .groupByDestinationWarehouseWith(destinationWarehouse);
       return destinationWarehouse;
    }







   public TransitRouteRequest<T> facetByOriginWarehouseAs(String facetName, WarehouseRequest originWarehouse){
       return facetByOriginWarehouseAs(facetName, originWarehouse, true);
   }

   public TransitRouteRequest<T> facetByOriginWarehouseAs(String facetName, WarehouseRequest originWarehouse, boolean includeAllFacets){
       addFacet(facetName, TransitRoute.ORIGIN_WAREHOUSE_PROPERTY, originWarehouse, includeAllFacets);
       return this;
   }
   public TransitRouteRequest<T> facetByDestinationWarehouseAs(String facetName, WarehouseRequest destinationWarehouse){
       return facetByDestinationWarehouseAs(facetName, destinationWarehouse, true);
   }

   public TransitRouteRequest<T> facetByDestinationWarehouseAs(String facetName, WarehouseRequest destinationWarehouse, boolean includeAllFacets){
       addFacet(facetName, TransitRoute.DESTINATION_WAREHOUSE_PROPERTY, destinationWarehouse, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public TransitRouteRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public TransitRouteRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public TransitRouteRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public TransitRouteRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public TransitRouteRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}