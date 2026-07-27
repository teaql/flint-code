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
        return selectId().selectStorageContainerIdOnly().selectUnitNumber().selectItemCount().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public ContainerUnitRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ContainerUnitRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectStorageContainer().selectUnitNumber().selectItemCount().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public ContainerUnitRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectStorageContainer().selectUnitNumber().selectItemCount().selectCreateTime().selectUpdateTime().selectVersion();
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
    public ContainerUnitRequest<T> selectUnitNumber(){
       selectProperty(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the unitNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  unitNumber) to fetch unitNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ContainerUnitRequest<T> unselectUnitNumber(){
       unselectProperty(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> selectItemCount(){
       selectProperty(ContainerUnit.ITEM_COUNT_PROPERTY);
       return this;
    }

    /**
     * fill the itemCount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  itemCount) to fetch itemCount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the itemCount with customized aggrFunction, TEAQL uses ({aggrFunction}(itemCount) AS itemCount to fetch itemCount property.
     * @param aggrFunction  aggrFunction
     */
    public ContainerUnitRequest<T> selectItemCount(AggrFunction aggrFunction){
       selectProperty(ContainerUnit.ITEM_COUNT_PROPERTY, aggrFunction);
       return this;
    }


    public ContainerUnitRequest<T> unselectItemCount(){
       unselectProperty(ContainerUnit.ITEM_COUNT_PROPERTY);
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

    public ContainerUnitRequest<T> filterByUnitNumber(String... unitNumber){
      if (unitNumber == null || unitNumber.length == 0) {
        throw new IllegalArgumentException("filterByUnitNumber parameter unitNumber cannot be empty");
      }
      return appendSearchCriteria(createUnitNumberCriteria(Operator.EQUAL, (Object[])unitNumber));
    }

    public ContainerUnitRequest<T> withUnitNumber(Operator operator, Object... values){
       return appendSearchCriteria(createUnitNumberCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withUnitNumberIsUnknown(){
       return withUnitNumber(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withUnitNumberIsKnown(){
       return withUnitNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUnitNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.UNIT_NUMBER_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withUnitNumberGreaterThan(String unitNumber){
       return withUnitNumber(Operator.GREATER_THAN, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberGreaterThanOrEqualTo(String unitNumber){
       return withUnitNumber(Operator.GREATER_THAN_OR_EQUAL, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberLessThan(String unitNumber){
       return withUnitNumber(Operator.LESS_THAN, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberLessThanOrEqualTo(String unitNumber){
       return withUnitNumber(Operator.LESS_THAN_OR_EQUAL, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberBetween(String startOfUnitNumber, String endOfUnitNumber){
       return withUnitNumber(Operator.BETWEEN, startOfUnitNumber, endOfUnitNumber);
    }
    public ContainerUnitRequest<T> withUnitNumberStartingWith(String unitNumber){
       return withUnitNumber(Operator.BEGIN_WITH, unitNumber);
    }
    public ContainerUnitRequest<T> withUnitNumberContaining(String unitNumber){
       return withUnitNumber(Operator.CONTAIN, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberEndingWith(String unitNumber){
       return withUnitNumber(Operator.END_WITH, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberIs(String unitNumber){
       return withUnitNumber(Operator.EQUAL, unitNumber);
    }

    public ContainerUnitRequest<T> withUnitNumberSoundingLike(String unitNumber){
       return withUnitNumber(Operator.SOUNDS_LIKE, unitNumber);
    }



    public ContainerUnitRequest<T> filterByItemCount(Integer... itemCount){
      if (itemCount == null || itemCount.length == 0) {
        throw new IllegalArgumentException("filterByItemCount parameter itemCount cannot be empty");
      }
      return appendSearchCriteria(createItemCountCriteria(Operator.EQUAL, (Object[])itemCount));
    }

    public ContainerUnitRequest<T> withItemCount(Operator operator, Object... values){
       return appendSearchCriteria(createItemCountCriteria(operator, values));
    }

    public ContainerUnitRequest<T> withItemCountIsUnknown(){
       return withItemCount(Operator.IS_NULL);
    }

    public ContainerUnitRequest<T> withItemCountIsKnown(){
       return withItemCount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createItemCountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ContainerUnit.ITEM_COUNT_PROPERTY, operator, values);
    }

    public ContainerUnitRequest<T> withItemCountGreaterThan(Integer itemCount){
       return withItemCount(Operator.GREATER_THAN, itemCount);
    }

    public ContainerUnitRequest<T> withItemCountGreaterThanOrEqualTo(Integer itemCount){
       return withItemCount(Operator.GREATER_THAN_OR_EQUAL, itemCount);
    }

    public ContainerUnitRequest<T> withItemCountLessThan(Integer itemCount){
       return withItemCount(Operator.LESS_THAN, itemCount);
    }

    public ContainerUnitRequest<T> withItemCountLessThanOrEqualTo(Integer itemCount){
       return withItemCount(Operator.LESS_THAN_OR_EQUAL, itemCount);
    }

    public ContainerUnitRequest<T> withItemCountBetween(Integer startOfItemCount, Integer endOfItemCount){
       return withItemCount(Operator.BETWEEN, startOfItemCount, endOfItemCount);
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
    public ContainerUnitRequest minItemCount(){
        return minItemCountAs(prefix("minOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest minItemCountAs(String retName){
        super.min(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest maxItemCount(){
        return maxItemCountAs(prefix("maxOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest maxItemCountAs(String retName){
        super.max(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest sumItemCount(){
        return sumItemCountAs(prefix("sumOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest sumItemCountAs(String retName){
        super.sum(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest avgItemCount(){
        return avgItemCountAs(prefix("avgOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest avgItemCountAs(String retName){
        super.avg(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest standardDeviationItemCount(){
        return standardDeviationItemCountAs(prefix("standardDeviationOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest standardDeviationItemCountAs(String retName){
        super.standardDeviation(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest squareRootOfPopulationStandardDeviationItemCount(){
        return squareRootOfPopulationStandardDeviationItemCountAs(prefix("squareRootOfPopulationStandardDeviationOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest squareRootOfPopulationStandardDeviationItemCountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest sampleVarianceItemCount(){
        return sampleVarianceItemCountAs(prefix("sampleVarianceOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest sampleVarianceItemCountAs(String retName){
        super.sampleVariance(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
        return this;
    }
    public ContainerUnitRequest samplePopulationVarianceItemCount(){
        return samplePopulationVarianceItemCountAs(prefix("samplePopulationVarianceOf",ContainerUnit.ITEM_COUNT_PROPERTY));
    }

    public ContainerUnitRequest samplePopulationVarianceItemCountAs(String retName){
        super.samplePopulationVariance(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
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

    public ContainerUnitRequest<T> groupByUnitNumber(){
       groupBy(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByUnitNumberAs(String retName){
       groupBy(retName, ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByUnitNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.UNIT_NUMBER_PROPERTY, function);
       return this;
    }

    public ContainerUnitRequest<T> groupByItemCount(){
       groupBy(ContainerUnit.ITEM_COUNT_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByItemCountAs(String retName){
       groupBy(retName, ContainerUnit.ITEM_COUNT_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> groupByItemCountWithFunction(String retName, AggrFunction function){
       groupBy(retName, ContainerUnit.ITEM_COUNT_PROPERTY, function);
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

    public ContainerUnitRequest<T> orderByUnitNumberAscending(){
       addOrderByAscending(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUnitNumberDescending(){
       addOrderByDescending(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> orderByUnitNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByUnitNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ContainerUnit.UNIT_NUMBER_PROPERTY);
       return this;
    }
    public ContainerUnitRequest<T> orderByItemCountAscending(){
       addOrderByAscending(ContainerUnit.ITEM_COUNT_PROPERTY);
       return this;
    }

    public ContainerUnitRequest<T> orderByItemCountDescending(){
       addOrderByDescending(ContainerUnit.ITEM_COUNT_PROPERTY);
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