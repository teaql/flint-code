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
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        return selectId().selectVehicleIdOnly().selectFuelAmountLiters().selectCost().selectDate().selectCreatedAt().selectVersion();
    }

    public FuelLogRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public FuelLogRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectVehicle().selectFuelAmountLiters().selectCost().selectDate().selectCreatedAt().selectVersion();
    }

    public FuelLogRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectVehicle().selectFuelAmountLiters().selectCost().selectDate().selectCreatedAt().selectVersion();
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
    public FuelLogRequest<T> selectFuelAmountLiters(){
       selectProperty(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
       return this;
    }

    /**
     * fill the fuelAmountLiters with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  fuelAmountLiters) to fetch fuelAmountLiters property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FuelLogRequest<T> unselectFuelAmountLiters(){
       unselectProperty(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
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




    public FuelLogRequest<T> unselectCost(){
       unselectProperty(FuelLog.COST_PROPERTY);
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
    public FuelLogRequest<T> selectCreatedAt(){
       selectProperty(FuelLog.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FuelLogRequest<T> unselectCreatedAt(){
       unselectProperty(FuelLog.CREATED_AT_PROPERTY);
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

    public FuelLogRequest<T> filterByFuelAmountLiters(String... fuelAmountLiters){
      if (fuelAmountLiters == null || fuelAmountLiters.length == 0) {
        throw new IllegalArgumentException("filterByFuelAmountLiters parameter fuelAmountLiters cannot be empty");
      }
      return appendSearchCriteria(createFuelAmountLitersCriteria(Operator.EQUAL, (Object[])fuelAmountLiters));
    }

    public FuelLogRequest<T> withFuelAmountLiters(Operator operator, Object... values){
       return appendSearchCriteria(createFuelAmountLitersCriteria(operator, values));
    }

    public FuelLogRequest<T> withFuelAmountLitersIsUnknown(){
       return withFuelAmountLiters(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withFuelAmountLitersIsKnown(){
       return withFuelAmountLiters(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createFuelAmountLitersCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withFuelAmountLitersGreaterThan(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.GREATER_THAN, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersGreaterThanOrEqualTo(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.GREATER_THAN_OR_EQUAL, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersLessThan(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.LESS_THAN, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersLessThanOrEqualTo(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.LESS_THAN_OR_EQUAL, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersBetween(String startOfFuelAmountLiters, String endOfFuelAmountLiters){
       return withFuelAmountLiters(Operator.BETWEEN, startOfFuelAmountLiters, endOfFuelAmountLiters);
    }
    public FuelLogRequest<T> withFuelAmountLitersStartingWith(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.BEGIN_WITH, fuelAmountLiters);
    }
    public FuelLogRequest<T> withFuelAmountLitersContaining(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.CONTAIN, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersEndingWith(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.END_WITH, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersIs(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.EQUAL, fuelAmountLiters);
    }

    public FuelLogRequest<T> withFuelAmountLitersSoundingLike(String fuelAmountLiters){
       return withFuelAmountLiters(Operator.SOUNDS_LIKE, fuelAmountLiters);
    }



    public FuelLogRequest<T> filterByCost(String... cost){
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

    public FuelLogRequest<T> withCostGreaterThan(String cost){
       return withCost(Operator.GREATER_THAN, cost);
    }

    public FuelLogRequest<T> withCostGreaterThanOrEqualTo(String cost){
       return withCost(Operator.GREATER_THAN_OR_EQUAL, cost);
    }

    public FuelLogRequest<T> withCostLessThan(String cost){
       return withCost(Operator.LESS_THAN, cost);
    }

    public FuelLogRequest<T> withCostLessThanOrEqualTo(String cost){
       return withCost(Operator.LESS_THAN_OR_EQUAL, cost);
    }

    public FuelLogRequest<T> withCostBetween(String startOfCost, String endOfCost){
       return withCost(Operator.BETWEEN, startOfCost, endOfCost);
    }
    public FuelLogRequest<T> withCostStartingWith(String cost){
       return withCost(Operator.BEGIN_WITH, cost);
    }
    public FuelLogRequest<T> withCostContaining(String cost){
       return withCost(Operator.CONTAIN, cost);
    }

    public FuelLogRequest<T> withCostEndingWith(String cost){
       return withCost(Operator.END_WITH, cost);
    }

    public FuelLogRequest<T> withCostIs(String cost){
       return withCost(Operator.EQUAL, cost);
    }

    public FuelLogRequest<T> withCostSoundingLike(String cost){
       return withCost(Operator.SOUNDS_LIKE, cost);
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




    public FuelLogRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public FuelLogRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public FuelLogRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public FuelLogRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FuelLog.CREATED_AT_PROPERTY, operator, values);
    }

    public FuelLogRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public FuelLogRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FuelLogRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
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

    public FuelLogRequest<T> groupByFuelAmountLiters(){
       groupBy(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByFuelAmountLitersAs(String retName){
       groupBy(retName, FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByFuelAmountLitersWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.FUEL_AMOUNT_LITERS_PROPERTY, function);
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

    public FuelLogRequest<T> groupByCreatedAt(){
       groupBy(FuelLog.CREATED_AT_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, FuelLog.CREATED_AT_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, FuelLog.CREATED_AT_PROPERTY, function);
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

    public FuelLogRequest<T> orderByVehicleAscending(){
       addOrderByAscending(FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByVehicleDescending(){
       addOrderByDescending(FuelLog.VEHICLE_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByFuelAmountLitersAscending(){
       addOrderByAscending(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByFuelAmountLitersDescending(){
       addOrderByDescending(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
       return this;
    }
    public FuelLogRequest<T> orderByFuelAmountLitersAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByFuelAmountLitersDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY);
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
    public FuelLogRequest<T> orderByCostAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FuelLog.COST_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByCostDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FuelLog.COST_PROPERTY);
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

    public FuelLogRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(FuelLog.CREATED_AT_PROPERTY);
       return this;
    }

    public FuelLogRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(FuelLog.CREATED_AT_PROPERTY);
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