package com.doublechaintech.enterpriselogisticsservice.transitroute;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
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
        return selectId().selectRouteCode().selectOriginCity().selectDestinationCity().selectDistanceKm().selectEstimatedDurationHours().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public TransitRouteRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public TransitRouteRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectRouteCode().selectOriginCity().selectDestinationCity().selectDistanceKm().selectEstimatedDurationHours().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public TransitRouteRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectRouteCode().selectOriginCity().selectDestinationCity().selectDistanceKm().selectEstimatedDurationHours().selectCreatedTime().selectUpdatedTime().selectVersion();
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
    public TransitRouteRequest<T> selectRouteCode(){
       selectProperty(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the routeCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  routeCode) to fetch routeCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectRouteCode(){
       unselectProperty(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectOriginCity(){
       selectProperty(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }

    /**
     * fill the originCity with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  originCity) to fetch originCity property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectOriginCity(){
       unselectProperty(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectDestinationCity(){
       selectProperty(TransitRoute.DESTINATION_CITY_PROPERTY);
       return this;
    }

    /**
     * fill the destinationCity with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  destinationCity) to fetch destinationCity property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectDestinationCity(){
       unselectProperty(TransitRoute.DESTINATION_CITY_PROPERTY);
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
    public TransitRouteRequest<T> selectCreatedTime(){
       selectProperty(TransitRoute.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectCreatedTime(){
       unselectProperty(TransitRoute.CREATED_TIME_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> selectUpdatedTime(){
       selectProperty(TransitRoute.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public TransitRouteRequest<T> unselectUpdatedTime(){
       unselectProperty(TransitRoute.UPDATED_TIME_PROPERTY);
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



    public TransitRouteRequest<T> filterByRouteCode(String... routeCode){
      if (routeCode == null || routeCode.length == 0) {
        throw new IllegalArgumentException("filterByRouteCode parameter routeCode cannot be empty");
      }
      return appendSearchCriteria(createRouteCodeCriteria(Operator.EQUAL, (Object[])routeCode));
    }

    public TransitRouteRequest<T> withRouteCode(Operator operator, Object... values){
       return appendSearchCriteria(createRouteCodeCriteria(operator, values));
    }

    public TransitRouteRequest<T> withRouteCodeIsUnknown(){
       return withRouteCode(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withRouteCodeIsKnown(){
       return withRouteCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRouteCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.ROUTE_CODE_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withRouteCodeGreaterThan(String routeCode){
       return withRouteCode(Operator.GREATER_THAN, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeGreaterThanOrEqualTo(String routeCode){
       return withRouteCode(Operator.GREATER_THAN_OR_EQUAL, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeLessThan(String routeCode){
       return withRouteCode(Operator.LESS_THAN, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeLessThanOrEqualTo(String routeCode){
       return withRouteCode(Operator.LESS_THAN_OR_EQUAL, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeBetween(String startOfRouteCode, String endOfRouteCode){
       return withRouteCode(Operator.BETWEEN, startOfRouteCode, endOfRouteCode);
    }
    public TransitRouteRequest<T> withRouteCodeStartingWith(String routeCode){
       return withRouteCode(Operator.BEGIN_WITH, routeCode);
    }
    public TransitRouteRequest<T> withRouteCodeContaining(String routeCode){
       return withRouteCode(Operator.CONTAIN, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeEndingWith(String routeCode){
       return withRouteCode(Operator.END_WITH, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeIs(String routeCode){
       return withRouteCode(Operator.EQUAL, routeCode);
    }

    public TransitRouteRequest<T> withRouteCodeSoundingLike(String routeCode){
       return withRouteCode(Operator.SOUNDS_LIKE, routeCode);
    }



    public TransitRouteRequest<T> filterByOriginCity(String... originCity){
      if (originCity == null || originCity.length == 0) {
        throw new IllegalArgumentException("filterByOriginCity parameter originCity cannot be empty");
      }
      return appendSearchCriteria(createOriginCityCriteria(Operator.EQUAL, (Object[])originCity));
    }

    public TransitRouteRequest<T> withOriginCity(Operator operator, Object... values){
       return appendSearchCriteria(createOriginCityCriteria(operator, values));
    }

    public TransitRouteRequest<T> withOriginCityIsUnknown(){
       return withOriginCity(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withOriginCityIsKnown(){
       return withOriginCity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createOriginCityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.ORIGIN_CITY_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withOriginCityGreaterThan(String originCity){
       return withOriginCity(Operator.GREATER_THAN, originCity);
    }

    public TransitRouteRequest<T> withOriginCityGreaterThanOrEqualTo(String originCity){
       return withOriginCity(Operator.GREATER_THAN_OR_EQUAL, originCity);
    }

    public TransitRouteRequest<T> withOriginCityLessThan(String originCity){
       return withOriginCity(Operator.LESS_THAN, originCity);
    }

    public TransitRouteRequest<T> withOriginCityLessThanOrEqualTo(String originCity){
       return withOriginCity(Operator.LESS_THAN_OR_EQUAL, originCity);
    }

    public TransitRouteRequest<T> withOriginCityBetween(String startOfOriginCity, String endOfOriginCity){
       return withOriginCity(Operator.BETWEEN, startOfOriginCity, endOfOriginCity);
    }
    public TransitRouteRequest<T> withOriginCityStartingWith(String originCity){
       return withOriginCity(Operator.BEGIN_WITH, originCity);
    }
    public TransitRouteRequest<T> withOriginCityContaining(String originCity){
       return withOriginCity(Operator.CONTAIN, originCity);
    }

    public TransitRouteRequest<T> withOriginCityEndingWith(String originCity){
       return withOriginCity(Operator.END_WITH, originCity);
    }

    public TransitRouteRequest<T> withOriginCityIs(String originCity){
       return withOriginCity(Operator.EQUAL, originCity);
    }

    public TransitRouteRequest<T> withOriginCitySoundingLike(String originCity){
       return withOriginCity(Operator.SOUNDS_LIKE, originCity);
    }



    public TransitRouteRequest<T> filterByDestinationCity(String... destinationCity){
      if (destinationCity == null || destinationCity.length == 0) {
        throw new IllegalArgumentException("filterByDestinationCity parameter destinationCity cannot be empty");
      }
      return appendSearchCriteria(createDestinationCityCriteria(Operator.EQUAL, (Object[])destinationCity));
    }

    public TransitRouteRequest<T> withDestinationCity(Operator operator, Object... values){
       return appendSearchCriteria(createDestinationCityCriteria(operator, values));
    }

    public TransitRouteRequest<T> withDestinationCityIsUnknown(){
       return withDestinationCity(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withDestinationCityIsKnown(){
       return withDestinationCity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDestinationCityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.DESTINATION_CITY_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withDestinationCityGreaterThan(String destinationCity){
       return withDestinationCity(Operator.GREATER_THAN, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCityGreaterThanOrEqualTo(String destinationCity){
       return withDestinationCity(Operator.GREATER_THAN_OR_EQUAL, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCityLessThan(String destinationCity){
       return withDestinationCity(Operator.LESS_THAN, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCityLessThanOrEqualTo(String destinationCity){
       return withDestinationCity(Operator.LESS_THAN_OR_EQUAL, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCityBetween(String startOfDestinationCity, String endOfDestinationCity){
       return withDestinationCity(Operator.BETWEEN, startOfDestinationCity, endOfDestinationCity);
    }
    public TransitRouteRequest<T> withDestinationCityStartingWith(String destinationCity){
       return withDestinationCity(Operator.BEGIN_WITH, destinationCity);
    }
    public TransitRouteRequest<T> withDestinationCityContaining(String destinationCity){
       return withDestinationCity(Operator.CONTAIN, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCityEndingWith(String destinationCity){
       return withDestinationCity(Operator.END_WITH, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCityIs(String destinationCity){
       return withDestinationCity(Operator.EQUAL, destinationCity);
    }

    public TransitRouteRequest<T> withDestinationCitySoundingLike(String destinationCity){
       return withDestinationCity(Operator.SOUNDS_LIKE, destinationCity);
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



    public TransitRouteRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public TransitRouteRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public TransitRouteRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.CREATED_TIME_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public TransitRouteRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public TransitRouteRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public TransitRouteRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public TransitRouteRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public TransitRouteRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public TransitRouteRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(TransitRoute.UPDATED_TIME_PROPERTY, operator, values);
    }

    public TransitRouteRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public TransitRouteRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public TransitRouteRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
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

    public TransitRouteRequest<T> groupByRouteCode(){
       groupBy(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByRouteCodeAs(String retName){
       groupBy(retName, TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByRouteCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.ROUTE_CODE_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByOriginCity(){
       groupBy(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByOriginCityAs(String retName){
       groupBy(retName, TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByOriginCityWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.ORIGIN_CITY_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByDestinationCity(){
       groupBy(TransitRoute.DESTINATION_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByDestinationCityAs(String retName){
       groupBy(retName, TransitRoute.DESTINATION_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByDestinationCityWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.DESTINATION_CITY_PROPERTY, function);
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

    public TransitRouteRequest<T> groupByCreatedTime(){
       groupBy(TransitRoute.CREATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, TransitRoute.CREATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public TransitRouteRequest<T> groupByUpdatedTime(){
       groupBy(TransitRoute.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, TransitRoute.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, TransitRoute.UPDATED_TIME_PROPERTY, function);
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

    public TransitRouteRequest<T> orderByRouteCodeAscending(){
       addOrderByAscending(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByRouteCodeDescending(){
       addOrderByDescending(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByRouteCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByRouteCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TransitRoute.ROUTE_CODE_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByOriginCityAscending(){
       addOrderByAscending(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByOriginCityDescending(){
       addOrderByDescending(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByOriginCityAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByOriginCityDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TransitRoute.ORIGIN_CITY_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByDestinationCityAscending(){
       addOrderByAscending(TransitRoute.DESTINATION_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByDestinationCityDescending(){
       addOrderByDescending(TransitRoute.DESTINATION_CITY_PROPERTY);
       return this;
    }
    public TransitRouteRequest<T> orderByDestinationCityAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(TransitRoute.DESTINATION_CITY_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByDestinationCityDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(TransitRoute.DESTINATION_CITY_PROPERTY);
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

    public TransitRouteRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(TransitRoute.CREATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(TransitRoute.CREATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(TransitRoute.UPDATED_TIME_PROPERTY);
       return this;
    }

    public TransitRouteRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(TransitRoute.UPDATED_TIME_PROPERTY);
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