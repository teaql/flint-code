package com.doublechaintech.enterpriselogisticsservice.fuellog;

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

public class FuelLogRequest<T extends FuelLog> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public FuelLogRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public FuelLogRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public FuelLogRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public FuelLogRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public FuelLogRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public FuelLogRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public FuelLogRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (FuelLogRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public FuelLogRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public FuelLogRequest<T> matchingAnyOf(FuelLogRequest fuelLog){
        super.internalMatchAny(fuelLog);
        return this;
    }

    public FuelLogRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public FuelLogRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public FuelLogRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public FuelLogRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectLiters().selectCost().selectOdometerKm().selectStationName().selectDate().selectVehicleIdOnly().selectVersion();
    }

    public FuelLogRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public FuelLogRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectLiters().selectCost().selectOdometerKm().selectStationName().selectDate().selectVehicle().selectVersion();
    }

    public FuelLogRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectLiters().selectCost().selectOdometerKm().selectStationName().selectDate().selectVehicle().selectVersion();
    }


    public FuelLogRequest<T> selectId(){
       selectProperty(FuelLog.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FuelLogRequest<T> unselectId(){
       unselectProperty(FuelLog.ID_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectLiters(){
       selectProperty(FuelLog.LITERS_PROPERTY);
       return this;
    }

    /**
     * fill the liters with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  liters) to fetch liters property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the liters with customized aggrFunction, TEAQL uses ({aggrFunction}(liters) AS liters to fetch liters property.
     * @param aggrFunction  aggrFunction
     */
    public FuelLogRequest<T> selectLiters(AggrFunction aggrFunction){
       selectProperty(FuelLog.LITERS_PROPERTY, aggrFunction);
       return this;
    }


    public FuelLogRequest<T> unselectLiters(){
       unselectProperty(FuelLog.LITERS_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectCost(){
       selectProperty(FuelLog.COST_PROPERTY);
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
    public FuelLogRequest<T> selectCost(AggrFunction aggrFunction){
       selectProperty(FuelLog.COST_PROPERTY, aggrFunction);
       return this;
    }


    public FuelLogRequest<T> unselectCost(){
       unselectProperty(FuelLog.COST_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectOdometerKm(){
       selectProperty(FuelLog.ODOMETER_KM_PROPERTY);
       return this;
    }

    /**
     * fill the odometerKm with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  odometerKm) to fetch odometerKm property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the odometerKm with customized aggrFunction, TEAQL uses ({aggrFunction}(odometerKm) AS odometerKm to fetch odometerKm property.
     * @param aggrFunction  aggrFunction
     */
    public FuelLogRequest<T> selectOdometerKm(AggrFunction aggrFunction){
       selectProperty(FuelLog.ODOMETER_KM_PROPERTY, aggrFunction);
       return this;
    }


    public FuelLogRequest<T> unselectOdometerKm(){
       unselectProperty(FuelLog.ODOMETER_KM_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectStationName(){
       selectProperty(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the stationName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  stationName) to fetch stationName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FuelLogRequest<T> unselectStationName(){
       unselectProperty(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectDate(){
       selectProperty(FuelLog.DATE_PROPERTY);
       return this;
    }

    /**
     * fill the date with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  date) to fetch date property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FuelLogRequest<T> unselectDate(){
       unselectProperty(FuelLog.DATE_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectVehicleIdOnly(){
       selectProperty(FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> selectVehicle(){
        return selectVehicleWith(Q.vehicles().unlimited().selectSelf());
    }

    public FuelLogRequest<T> selectVehicleWith(VehicleRequest vehicle){
       selectProperty(FuelLog.VEHICLE_PROPERTY);
       enhanceRelation(FuelLog.VEHICLE_PROPERTY, vehicle);
       return this;
    }

    public FuelLogRequest<T> unselectVehicle(){
       unselectProperty(FuelLog.VEHICLE_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> selectVersion(){
       selectProperty(FuelLog.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FuelLogRequest<T> unselectVersion(){
       unselectProperty(FuelLog.VERSION_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.ID_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public FuelLogRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public FuelLogRequest<T> filterByLiters(BigDecimal... liters){
      if (liters == null || liters.length == 0) {
        throw new IllegalArgumentException("filterByLiters parameter liters cannot be empty");
      }
      return appendSearchCriteria(createLitersCriteria(Operator.EQUAL, (Object[])liters));
    }

    public FuelLogRequest<T> withLiters(Operator operator, Object... values){
       return appendSearchCriteria(createLitersCriteria(operator, values));
    }

    public FuelLogRequest<T> withLitersIsUnknown(){
       return withLiters(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withLitersIsKnown(){
       return withLiters(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLitersCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.LITERS_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withLitersGreaterThan(BigDecimal liters){
       return withLiters(Operator.GREATER_THAN, liters);
    }

    public FuelLogRequest<T> withLitersGreaterThanOrEqualTo(BigDecimal liters){
       return withLiters(Operator.GREATER_THAN_OR_EQUAL, liters);
    }

    public FuelLogRequest<T> withLitersLessThan(BigDecimal liters){
       return withLiters(Operator.LESS_THAN, liters);
    }

    public FuelLogRequest<T> withLitersLessThanOrEqualTo(BigDecimal liters){
       return withLiters(Operator.LESS_THAN_OR_EQUAL, liters);
    }

    public FuelLogRequest<T> withLitersBetween(BigDecimal startOfLiters, BigDecimal endOfLiters){
       return withLiters(Operator.BETWEEN, startOfLiters, endOfLiters);
    }



    public FuelLogRequest<T> filterByCost(BigDecimal... cost){
      if (cost == null || cost.length == 0) {
        throw new IllegalArgumentException("filterByCost parameter cost cannot be empty");
      }
      return appendSearchCriteria(createCostCriteria(Operator.EQUAL, (Object[])cost));
    }

    public FuelLogRequest<T> withCost(Operator operator, Object... values){
       return appendSearchCriteria(createCostCriteria(operator, values));
    }

    public FuelLogRequest<T> withCostIsUnknown(){
       return withCost(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withCostIsKnown(){
       return withCost(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCostCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.COST_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withCostGreaterThan(BigDecimal cost){
       return withCost(Operator.GREATER_THAN, cost);
    }

    public FuelLogRequest<T> withCostGreaterThanOrEqualTo(BigDecimal cost){
       return withCost(Operator.GREATER_THAN_OR_EQUAL, cost);
    }

    public FuelLogRequest<T> withCostLessThan(BigDecimal cost){
       return withCost(Operator.LESS_THAN, cost);
    }

    public FuelLogRequest<T> withCostLessThanOrEqualTo(BigDecimal cost){
       return withCost(Operator.LESS_THAN_OR_EQUAL, cost);
    }

    public FuelLogRequest<T> withCostBetween(BigDecimal startOfCost, BigDecimal endOfCost){
       return withCost(Operator.BETWEEN, startOfCost, endOfCost);
    }



    public FuelLogRequest<T> filterByOdometerKm(Integer... odometerKm){
      if (odometerKm == null || odometerKm.length == 0) {
        throw new IllegalArgumentException("filterByOdometerKm parameter odometerKm cannot be empty");
      }
      return appendSearchCriteria(createOdometerKmCriteria(Operator.EQUAL, (Object[])odometerKm));
    }

    public FuelLogRequest<T> withOdometerKm(Operator operator, Object... values){
       return appendSearchCriteria(createOdometerKmCriteria(operator, values));
    }

    public FuelLogRequest<T> withOdometerKmIsUnknown(){
       return withOdometerKm(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withOdometerKmIsKnown(){
       return withOdometerKm(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createOdometerKmCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.ODOMETER_KM_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withOdometerKmGreaterThan(Integer odometerKm){
       return withOdometerKm(Operator.GREATER_THAN, odometerKm);
    }

    public FuelLogRequest<T> withOdometerKmGreaterThanOrEqualTo(Integer odometerKm){
       return withOdometerKm(Operator.GREATER_THAN_OR_EQUAL, odometerKm);
    }

    public FuelLogRequest<T> withOdometerKmLessThan(Integer odometerKm){
       return withOdometerKm(Operator.LESS_THAN, odometerKm);
    }

    public FuelLogRequest<T> withOdometerKmLessThanOrEqualTo(Integer odometerKm){
       return withOdometerKm(Operator.LESS_THAN_OR_EQUAL, odometerKm);
    }

    public FuelLogRequest<T> withOdometerKmBetween(Integer startOfOdometerKm, Integer endOfOdometerKm){
       return withOdometerKm(Operator.BETWEEN, startOfOdometerKm, endOfOdometerKm);
    }



    public FuelLogRequest<T> filterByStationName(String... stationName){
      if (stationName == null || stationName.length == 0) {
        throw new IllegalArgumentException("filterByStationName parameter stationName cannot be empty");
      }
      return appendSearchCriteria(createStationNameCriteria(Operator.EQUAL, (Object[])stationName));
    }

    public FuelLogRequest<T> withStationName(Operator operator, Object... values){
       return appendSearchCriteria(createStationNameCriteria(operator, values));
    }

    public FuelLogRequest<T> withStationNameIsUnknown(){
       return withStationName(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withStationNameIsKnown(){
       return withStationName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStationNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.STATION_NAME_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withStationNameGreaterThan(String stationName){
       return withStationName(Operator.GREATER_THAN, stationName);
    }

    public FuelLogRequest<T> withStationNameGreaterThanOrEqualTo(String stationName){
       return withStationName(Operator.GREATER_THAN_OR_EQUAL, stationName);
    }

    public FuelLogRequest<T> withStationNameLessThan(String stationName){
       return withStationName(Operator.LESS_THAN, stationName);
    }

    public FuelLogRequest<T> withStationNameLessThanOrEqualTo(String stationName){
       return withStationName(Operator.LESS_THAN_OR_EQUAL, stationName);
    }

    public FuelLogRequest<T> withStationNameBetween(String startOfStationName, String endOfStationName){
       return withStationName(Operator.BETWEEN, startOfStationName, endOfStationName);
    }
    public FuelLogRequest<T> withStationNameStartingWith(String stationName){
       return withStationName(Operator.BEGIN_WITH, stationName);
    }
    public FuelLogRequest<T> withStationNameContaining(String stationName){
       return withStationName(Operator.CONTAIN, stationName);
    }

    public FuelLogRequest<T> withStationNameEndingWith(String stationName){
       return withStationName(Operator.END_WITH, stationName);
    }

    public FuelLogRequest<T> withStationNameIs(String stationName){
       return withStationName(Operator.EQUAL, stationName);
    }

    public FuelLogRequest<T> withStationNameSoundingLike(String stationName){
       return withStationName(Operator.SOUNDS_LIKE, stationName);
    }



    public FuelLogRequest<T> filterByDate(LocalDate... date){
      if (date == null || date.length == 0) {
        throw new IllegalArgumentException("filterByDate parameter date cannot be empty");
      }
      return appendSearchCriteria(createDateCriteria(Operator.EQUAL, (Object[])date));
    }

    public FuelLogRequest<T> withDate(Operator operator, Object... values){
       return appendSearchCriteria(createDateCriteria(operator, values));
    }

    public FuelLogRequest<T> withDateIsUnknown(){
       return withDate(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withDateIsKnown(){
       return withDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.DATE_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withDateGreaterThan(LocalDate date){
       return withDate(Operator.GREATER_THAN, date);
    }

    public FuelLogRequest<T> withDateGreaterThanOrEqualTo(LocalDate date){
       return withDate(Operator.GREATER_THAN_OR_EQUAL, date);
    }

    public FuelLogRequest<T> withDateLessThan(LocalDate date){
       return withDate(Operator.LESS_THAN, date);
    }

    public FuelLogRequest<T> withDateLessThanOrEqualTo(LocalDate date){
       return withDate(Operator.LESS_THAN_OR_EQUAL, date);
    }

    public FuelLogRequest<T> withDateBetween(LocalDate startOfDate, LocalDate endOfDate){
       return withDate(Operator.BETWEEN, startOfDate, endOfDate);
    }
    public FuelLogRequest<T> withDateBefore(LocalDate date){
       return withDate(Operator.LESS_THAN, date);
    }

    public FuelLogRequest<T> withDateBefore(Date date){
       return withDate(Operator.LESS_THAN, date);
    }

    public FuelLogRequest<T> withDateAfter(LocalDate date){
       return withDate(Operator.GREATER_THAN, date);
    }

    public FuelLogRequest<T> withDateAfter(Date date){
       return withDate(Operator.GREATER_THAN, date);
    }

    public FuelLogRequest<T> withDateBetween(Date startOfDate, Date endOfDate){
       return withDate(Operator.BETWEEN, startOfDate, endOfDate);
    }




    public FuelLogRequest<T> filterByVehicle(Vehicle... vehicle){
      if (vehicle == null || vehicle.length == 0) {
        throw new IllegalArgumentException("filterByVehicle parameter vehicle cannot be empty");
      }
      return appendSearchCriteria(createVehicleCriteria(Operator.EQUAL, (Object[])vehicle));
    }

    public FuelLogRequest<T> withVehicle(Operator operator, Object... values){
       return appendSearchCriteria(createVehicleCriteria(operator, values));
    }

    public FuelLogRequest<T> withVehicleIsUnknown(){
       return withVehicle(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withVehicleIsKnown(){
       return withVehicle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVehicleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.VEHICLE_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> filterByVehicle(Long vehicle){
      if(vehicle == null){
         return this;
      }
      return withVehicle(Operator.EQUAL, vehicle);
    }
    public FuelLogRequest<T> withVehicleMatching(VehicleRequest vehicle){
       return appendSearchCriteria(new SubQuerySearchCriteria(FuelLog.VEHICLE_PROPERTY, vehicle, Vehicle.ID_PROPERTY));
    }

    public FuelLogRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public FuelLogRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public FuelLogRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.VERSION_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public FuelLogRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public FuelLogRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public FuelLogRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public FuelLogRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public FuelLogRequest<T> count(){
        super.count();
        return this;
    }
    public FuelLogRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public FuelLogRequest minLiters(){
        return minLitersAs(prefix("minOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest minLitersAs(String retName){
        super.min(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest maxLiters(){
        return maxLitersAs(prefix("maxOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest maxLitersAs(String retName){
        super.max(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest sumLiters(){
        return sumLitersAs(prefix("sumOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest sumLitersAs(String retName){
        super.sum(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest avgLiters(){
        return avgLitersAs(prefix("avgOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest avgLitersAs(String retName){
        super.avg(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest standardDeviationLiters(){
        return standardDeviationLitersAs(prefix("standardDeviationOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest standardDeviationLitersAs(String retName){
        super.standardDeviation(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest squareRootOfPopulationStandardDeviationLiters(){
        return squareRootOfPopulationStandardDeviationLitersAs(prefix("squareRootOfPopulationStandardDeviationOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest squareRootOfPopulationStandardDeviationLitersAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest sampleVarianceLiters(){
        return sampleVarianceLitersAs(prefix("sampleVarianceOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest sampleVarianceLitersAs(String retName){
        super.sampleVariance(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest samplePopulationVarianceLiters(){
        return samplePopulationVarianceLitersAs(prefix("samplePopulationVarianceOf",FuelLog.LITERS_PROPERTY));
    }

    public FuelLogRequest samplePopulationVarianceLitersAs(String retName){
        super.samplePopulationVariance(retName, FuelLog.LITERS_PROPERTY);
        return this;
    }
    public FuelLogRequest minCost(){
        return minCostAs(prefix("minOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest minCostAs(String retName){
        super.min(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest maxCost(){
        return maxCostAs(prefix("maxOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest maxCostAs(String retName){
        super.max(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest sumCost(){
        return sumCostAs(prefix("sumOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest sumCostAs(String retName){
        super.sum(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest avgCost(){
        return avgCostAs(prefix("avgOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest avgCostAs(String retName){
        super.avg(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest standardDeviationCost(){
        return standardDeviationCostAs(prefix("standardDeviationOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest standardDeviationCostAs(String retName){
        super.standardDeviation(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest squareRootOfPopulationStandardDeviationCost(){
        return squareRootOfPopulationStandardDeviationCostAs(prefix("squareRootOfPopulationStandardDeviationOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest squareRootOfPopulationStandardDeviationCostAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest sampleVarianceCost(){
        return sampleVarianceCostAs(prefix("sampleVarianceOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest sampleVarianceCostAs(String retName){
        super.sampleVariance(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest samplePopulationVarianceCost(){
        return samplePopulationVarianceCostAs(prefix("samplePopulationVarianceOf",FuelLog.COST_PROPERTY));
    }

    public FuelLogRequest samplePopulationVarianceCostAs(String retName){
        super.samplePopulationVariance(retName, FuelLog.COST_PROPERTY);
        return this;
    }
    public FuelLogRequest minOdometerKm(){
        return minOdometerKmAs(prefix("minOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest minOdometerKmAs(String retName){
        super.min(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest maxOdometerKm(){
        return maxOdometerKmAs(prefix("maxOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest maxOdometerKmAs(String retName){
        super.max(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest sumOdometerKm(){
        return sumOdometerKmAs(prefix("sumOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest sumOdometerKmAs(String retName){
        super.sum(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest avgOdometerKm(){
        return avgOdometerKmAs(prefix("avgOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest avgOdometerKmAs(String retName){
        super.avg(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest standardDeviationOdometerKm(){
        return standardDeviationOdometerKmAs(prefix("standardDeviationOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest standardDeviationOdometerKmAs(String retName){
        super.standardDeviation(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest squareRootOfPopulationStandardDeviationOdometerKm(){
        return squareRootOfPopulationStandardDeviationOdometerKmAs(prefix("squareRootOfPopulationStandardDeviationOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest squareRootOfPopulationStandardDeviationOdometerKmAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest sampleVarianceOdometerKm(){
        return sampleVarianceOdometerKmAs(prefix("sampleVarianceOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest sampleVarianceOdometerKmAs(String retName){
        super.sampleVariance(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest samplePopulationVarianceOdometerKm(){
        return samplePopulationVarianceOdometerKmAs(prefix("samplePopulationVarianceOf",FuelLog.ODOMETER_KM_PROPERTY));
    }

    public FuelLogRequest samplePopulationVarianceOdometerKmAs(String retName){
        super.samplePopulationVariance(retName, FuelLog.ODOMETER_KM_PROPERTY);
        return this;
    }
    public FuelLogRequest<T> groupByVehicleWithDetails(){
       return groupByVehicleWithDetails(Q.vehicles().unlimited());
    }

    public FuelLogRequest<T> groupByVehicleWithDetails(VehicleRequest subRequest){
       aggregate(FuelLog.VEHICLE_PROPERTY, subRequest);
       return this;
    }



    public FuelLogRequest<T> groupById(){
       groupBy(FuelLog.ID_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByIdAs(String retName){
       groupBy(retName, FuelLog.ID_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.ID_PROPERTY, function);
       return this;
    }

    public FuelLogRequest<T> groupByLiters(){
       groupBy(FuelLog.LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByLitersAs(String retName){
       groupBy(retName, FuelLog.LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByLitersWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.LITERS_PROPERTY, function);
       return this;
    }

    public FuelLogRequest<T> groupByCost(){
       groupBy(FuelLog.COST_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByCostAs(String retName){
       groupBy(retName, FuelLog.COST_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByCostWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.COST_PROPERTY, function);
       return this;
    }

    public FuelLogRequest<T> groupByOdometerKm(){
       groupBy(FuelLog.ODOMETER_KM_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByOdometerKmAs(String retName){
       groupBy(retName, FuelLog.ODOMETER_KM_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByOdometerKmWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.ODOMETER_KM_PROPERTY, function);
       return this;
    }

    public FuelLogRequest<T> groupByStationName(){
       groupBy(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByStationNameAs(String retName){
       groupBy(retName, FuelLog.STATION_NAME_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByStationNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.STATION_NAME_PROPERTY, function);
       return this;
    }

    public FuelLogRequest<T> groupByDate(){
       groupBy(FuelLog.DATE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByDateAs(String retName){
       groupBy(retName, FuelLog.DATE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.DATE_PROPERTY, function);
       return this;
    }
    public FuelLogRequest<T> groupByVehicleWith(VehicleRequest subRequest){
       groupBy(FuelLog.VEHICLE_PROPERTY, subRequest);
       return this;
    }
    public FuelLogRequest<T> groupByVehicle(){
       groupBy(FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByVehicleAs(String retName){
       groupBy(retName, FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByVehicleWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.VEHICLE_PROPERTY, function);
       return this;
    }

    public FuelLogRequest<T> groupByVersion(){
       groupBy(FuelLog.VERSION_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByVersionAs(String retName){
       groupBy(retName, FuelLog.VERSION_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.VERSION_PROPERTY, function);
       return this;
    }



    public FuelLogRequest<T> orderByIdAscending(){
       addOrderByAscending(FuelLog.ID_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByIdDescending(){
       addOrderByDescending(FuelLog.ID_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByLitersAscending(){
       addOrderByAscending(FuelLog.LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByLitersDescending(){
       addOrderByDescending(FuelLog.LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByCostAscending(){
       addOrderByAscending(FuelLog.COST_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByCostDescending(){
       addOrderByDescending(FuelLog.COST_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByOdometerKmAscending(){
       addOrderByAscending(FuelLog.ODOMETER_KM_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByOdometerKmDescending(){
       addOrderByDescending(FuelLog.ODOMETER_KM_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByStationNameAscending(){
       addOrderByAscending(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByStationNameDescending(){
       addOrderByDescending(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> orderByStationNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByStationNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FuelLog.STATION_NAME_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> orderByDateAscending(){
       addOrderByAscending(FuelLog.DATE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByDateDescending(){
       addOrderByDescending(FuelLog.DATE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByVehicleAscending(){
       addOrderByAscending(FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByVehicleDescending(){
       addOrderByDescending(FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByVersionAscending(){
       addOrderByAscending(FuelLog.VERSION_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByVersionDescending(){
       addOrderByDescending(FuelLog.VERSION_PROPERTY);
       return this;
    }


    public VehicleRequest rollUpToVehicle(){
       VehicleRequest vehicle = Q.vehicles().unlimited();
       this.withVehicleMatching(vehicle)
           .groupByVehicleWith(vehicle);
       return vehicle;
    }



   public FuelLogRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle){
       return facetByVehicleAs(facetName, vehicle, true);
   }

   public FuelLogRequest<T> facetByVehicleAs(String facetName, VehicleRequest vehicle, boolean includeAllFacets){
       addFacet(facetName, FuelLog.VEHICLE_PROPERTY, vehicle, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public FuelLogRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public FuelLogRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public FuelLogRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public FuelLogRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public FuelLogRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}