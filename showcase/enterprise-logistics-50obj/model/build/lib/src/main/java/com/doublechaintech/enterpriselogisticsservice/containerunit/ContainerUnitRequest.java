package com.doublechaintech.enterpriselogisticsservice.containerunit;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class ContainerUnitRequest<T extends ContainerUnit> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public ContainerUnitRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public ContainerUnitRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public ContainerUnitRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public ContainerUnitRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public ContainerUnitRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public ContainerUnitRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public ContainerUnitRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (ContainerUnitRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public ContainerUnitRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public ContainerUnitRequest<T> matchingAnyOf(ContainerUnitRequest containerUnit){
        super.internalMatchAny(containerUnit);
        return this;
    }

    public ContainerUnitRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public ContainerUnitRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public ContainerUnitRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public ContainerUnitRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectStorageContainerIdOnly().selectUnitType().selectQuantity().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public ContainerUnitRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ContainerUnitRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectStorageContainer().selectUnitType().selectQuantity().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public ContainerUnitRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectStorageContainer().selectUnitType().selectQuantity().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public ContainerUnitRequest<T> selectId(){
       selectProperty(ContainerUnit.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ContainerUnitRequest<T> unselectId(){
       unselectProperty(ContainerUnit.ID_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectStorageContainerIdOnly(){
       selectProperty(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> selectStorageContainer(){
        return selectStorageContainerWith(Q.storageContainers().unlimited().selectSelf());
    }

    public ContainerUnitRequest<T> selectStorageContainerWith(StorageContainerRequest storageContainer){
       selectProperty(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       enhanceRelation(ContainerUnit.STORAGE_CONTAINER_PROPERTY, storageContainer);
       return this;
    }

    public ContainerUnitRequest<T> unselectStorageContainer(){
       unselectProperty(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectUnitType(){
       selectProperty(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the unitType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  unitType) to fetch unitType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ContainerUnitRequest<T> unselectUnitType(){
       unselectProperty(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectQuantity(){
       selectProperty(ContainerUnit.QUANTITY_PROPERTY);
       return this;
    }

    /**
     * fill the quantity with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  quantity) to fetch quantity property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the quantity with customized aggrFunction, TEAQL uses ({aggrFunction}(quantity) AS quantity to fetch quantity property.
     * @param aggrFunction  aggrFunction
     */
    public ContainerUnitRequest<T> selectQuantity(AggrFunction aggrFunction){
       selectProperty(ContainerUnit.QUANTITY_PROPERTY, aggrFunction);
       return this;
    }


    public ContainerUnitRequest<T> unselectQuantity(){
       unselectProperty(ContainerUnit.QUANTITY_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectCreateTime(){
       selectProperty(ContainerUnit.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ContainerUnitRequest<T> unselectCreateTime(){
       unselectProperty(ContainerUnit.CREATE_TIME_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectUpdateTime(){
       selectProperty(ContainerUnit.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ContainerUnitRequest<T> unselectUpdateTime(){
       unselectProperty(ContainerUnit.UPDATE_TIME_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectVersion(){
       selectProperty(ContainerUnit.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ContainerUnitRequest<T> unselectVersion(){
       unselectProperty(ContainerUnit.VERSION_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.ID_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public ContainerUnitRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public ContainerUnitRequest<T> filterByStorageContainer(StorageContainer... storageContainer){
      if (storageContainer == null || storageContainer.length == 0) {
        throw new IllegalArgumentException("filterByStorageContainer parameter storageContainer cannot be empty");
      }
      return appendSearchCriteria(createStorageContainerCriteria(Operator.EQUAL, (Object[])storageContainer));
    }

    public ContainerUnitRequest<T> withStorageContainer(Operator operator, Object... values){
       return appendSearchCriteria(createStorageContainerCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withStorageContainerIsUnknown(){
       return withStorageContainer(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withStorageContainerIsKnown(){
       return withStorageContainer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStorageContainerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.STORAGE_CONTAINER_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> filterByStorageContainer(Long storageContainer){
      if(storageContainer == null){
         return this;
      }
      return withStorageContainer(Operator.EQUAL, storageContainer);
    }
    public ContainerUnitRequest<T> withStorageContainerMatching(StorageContainerRequest storageContainer){
       return appendSearchCriteria(new SubQuerySearchCriteria(ContainerUnit.STORAGE_CONTAINER_PROPERTY, storageContainer, StorageContainer.ID_PROPERTY));
    }

    public ContainerUnitRequest<T> filterByUnitType(String... unitType){
      if (unitType == null || unitType.length == 0) {
        throw new IllegalArgumentException("filterByUnitType parameter unitType cannot be empty");
      }
      return appendSearchCriteria(createUnitTypeCriteria(Operator.EQUAL, (Object[])unitType));
    }

    public ContainerUnitRequest<T> withUnitType(Operator operator, Object... values){
       return appendSearchCriteria(createUnitTypeCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withUnitTypeIsUnknown(){
       return withUnitType(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withUnitTypeIsKnown(){
       return withUnitType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUnitTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.UNIT_TYPE_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withUnitTypeGreaterThan(String unitType){
       return withUnitType(Operator.GREATER_THAN, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeGreaterThanOrEqualTo(String unitType){
       return withUnitType(Operator.GREATER_THAN_OR_EQUAL, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeLessThan(String unitType){
       return withUnitType(Operator.LESS_THAN, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeLessThanOrEqualTo(String unitType){
       return withUnitType(Operator.LESS_THAN_OR_EQUAL, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeBetween(String startOfUnitType, String endOfUnitType){
       return withUnitType(Operator.BETWEEN, startOfUnitType, endOfUnitType);
    }
    public ContainerUnitRequest<T> withUnitTypeStartingWith(String unitType){
       return withUnitType(Operator.BEGIN_WITH, unitType);
    }
    public ContainerUnitRequest<T> withUnitTypeContaining(String unitType){
       return withUnitType(Operator.CONTAIN, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeEndingWith(String unitType){
       return withUnitType(Operator.END_WITH, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeIs(String unitType){
       return withUnitType(Operator.EQUAL, unitType);
    }

    public ContainerUnitRequest<T> withUnitTypeSoundingLike(String unitType){
       return withUnitType(Operator.SOUNDS_LIKE, unitType);
    }



    public ContainerUnitRequest<T> filterByQuantity(Integer... quantity){
      if (quantity == null || quantity.length == 0) {
        throw new IllegalArgumentException("filterByQuantity parameter quantity cannot be empty");
      }
      return appendSearchCriteria(createQuantityCriteria(Operator.EQUAL, (Object[])quantity));
    }

    public ContainerUnitRequest<T> withQuantity(Operator operator, Object... values){
       return appendSearchCriteria(createQuantityCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withQuantityIsUnknown(){
       return withQuantity(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withQuantityIsKnown(){
       return withQuantity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createQuantityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.QUANTITY_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withQuantityGreaterThan(Integer quantity){
       return withQuantity(Operator.GREATER_THAN, quantity);
    }

    public ContainerUnitRequest<T> withQuantityGreaterThanOrEqualTo(Integer quantity){
       return withQuantity(Operator.GREATER_THAN_OR_EQUAL, quantity);
    }

    public ContainerUnitRequest<T> withQuantityLessThan(Integer quantity){
       return withQuantity(Operator.LESS_THAN, quantity);
    }

    public ContainerUnitRequest<T> withQuantityLessThanOrEqualTo(Integer quantity){
       return withQuantity(Operator.LESS_THAN_OR_EQUAL, quantity);
    }

    public ContainerUnitRequest<T> withQuantityBetween(Integer startOfQuantity, Integer endOfQuantity){
       return withQuantity(Operator.BETWEEN, startOfQuantity, endOfQuantity);
    }



    public ContainerUnitRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public ContainerUnitRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.CREATE_TIME_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public ContainerUnitRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public ContainerUnitRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public ContainerUnitRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public ContainerUnitRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.UPDATE_TIME_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public ContainerUnitRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public ContainerUnitRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public ContainerUnitRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public ContainerUnitRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.VERSION_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public ContainerUnitRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public ContainerUnitRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public ContainerUnitRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public ContainerUnitRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public ContainerUnitRequest<T> count(){
        super.count();
        return this;
    }
    public ContainerUnitRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public ContainerUnitRequest minQuantity(){
        return minQuantityAs(prefix("minOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest minQuantityAs(String retName){
        super.min(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest maxQuantity(){
        return maxQuantityAs(prefix("maxOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest maxQuantityAs(String retName){
        super.max(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest sumQuantity(){
        return sumQuantityAs(prefix("sumOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest sumQuantityAs(String retName){
        super.sum(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest avgQuantity(){
        return avgQuantityAs(prefix("avgOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest avgQuantityAs(String retName){
        super.avg(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest standardDeviationQuantity(){
        return standardDeviationQuantityAs(prefix("standardDeviationOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest standardDeviationQuantityAs(String retName){
        super.standardDeviation(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest squareRootOfPopulationStandardDeviationQuantity(){
        return squareRootOfPopulationStandardDeviationQuantityAs(prefix("squareRootOfPopulationStandardDeviationOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest squareRootOfPopulationStandardDeviationQuantityAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest sampleVarianceQuantity(){
        return sampleVarianceQuantityAs(prefix("sampleVarianceOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest sampleVarianceQuantityAs(String retName){
        super.sampleVariance(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest samplePopulationVarianceQuantity(){
        return samplePopulationVarianceQuantityAs(prefix("samplePopulationVarianceOf",ContainerUnit.QUANTITY_PROPERTY));
    }

    public ContainerUnitRequest samplePopulationVarianceQuantityAs(String retName){
        super.samplePopulationVariance(retName, ContainerUnit.QUANTITY_PROPERTY);
        return this;
    }
    public ContainerUnitRequest<T> groupByStorageContainerWithDetails(){
       return groupByStorageContainerWithDetails(Q.storageContainers().unlimited());
    }

    public ContainerUnitRequest<T> groupByStorageContainerWithDetails(StorageContainerRequest subRequest){
       aggregate(ContainerUnit.STORAGE_CONTAINER_PROPERTY, subRequest);
       return this;
    }







    public ContainerUnitRequest<T> groupById(){
       groupBy(ContainerUnit.ID_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByIdAs(String retName){
       groupBy(retName, ContainerUnit.ID_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.ID_PROPERTY, function);
       return this;
    }
    public ContainerUnitRequest<T> groupByStorageContainerWith(StorageContainerRequest subRequest){
       groupBy(ContainerUnit.STORAGE_CONTAINER_PROPERTY, subRequest);
       return this;
    }
    public ContainerUnitRequest<T> groupByStorageContainer(){
       groupBy(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByStorageContainerAs(String retName){
       groupBy(retName, ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByStorageContainerWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.STORAGE_CONTAINER_PROPERTY, function);
       return this;
    }

    public ContainerUnitRequest<T> groupByUnitType(){
       groupBy(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByUnitTypeAs(String retName){
       groupBy(retName, ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByUnitTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.UNIT_TYPE_PROPERTY, function);
       return this;
    }

    public ContainerUnitRequest<T> groupByQuantity(){
       groupBy(ContainerUnit.QUANTITY_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByQuantityAs(String retName){
       groupBy(retName, ContainerUnit.QUANTITY_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByQuantityWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.QUANTITY_PROPERTY, function);
       return this;
    }

    public ContainerUnitRequest<T> groupByCreateTime(){
       groupBy(ContainerUnit.CREATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, ContainerUnit.CREATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public ContainerUnitRequest<T> groupByUpdateTime(){
       groupBy(ContainerUnit.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, ContainerUnit.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public ContainerUnitRequest<T> groupByVersion(){
       groupBy(ContainerUnit.VERSION_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByVersionAs(String retName){
       groupBy(retName, ContainerUnit.VERSION_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.VERSION_PROPERTY, function);
       return this;
    }



    public ContainerUnitRequest<T> orderByIdAscending(){
       addOrderByAscending(ContainerUnit.ID_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByIdDescending(){
       addOrderByDescending(ContainerUnit.ID_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByStorageContainerAscending(){
       addOrderByAscending(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByStorageContainerDescending(){
       addOrderByDescending(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUnitTypeAscending(){
       addOrderByAscending(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUnitTypeDescending(){
       addOrderByDescending(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> orderByUnitTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUnitTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ContainerUnit.UNIT_TYPE_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> orderByQuantityAscending(){
       addOrderByAscending(ContainerUnit.QUANTITY_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByQuantityDescending(){
       addOrderByDescending(ContainerUnit.QUANTITY_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(ContainerUnit.CREATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(ContainerUnit.CREATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(ContainerUnit.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(ContainerUnit.UPDATE_TIME_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByVersionAscending(){
       addOrderByAscending(ContainerUnit.VERSION_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByVersionDescending(){
       addOrderByDescending(ContainerUnit.VERSION_PROPERTY);
       return this;
    }


    public StorageContainerRequest rollUpToStorageContainer(){
       StorageContainerRequest storageContainer = Q.storageContainers().unlimited();
       this.withStorageContainerMatching(storageContainer)
           .groupByStorageContainerWith(storageContainer);
       return storageContainer;
    }







   public ContainerUnitRequest<T> facetByStorageContainerAs(String facetName, StorageContainerRequest storageContainer){
       return facetByStorageContainerAs(facetName, storageContainer, true);
   }

   public ContainerUnitRequest<T> facetByStorageContainerAs(String facetName, StorageContainerRequest storageContainer, boolean includeAllFacets){
       addFacet(facetName, ContainerUnit.STORAGE_CONTAINER_PROPERTY, storageContainer, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public ContainerUnitRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public ContainerUnitRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public ContainerUnitRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public ContainerUnitRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public ContainerUnitRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}