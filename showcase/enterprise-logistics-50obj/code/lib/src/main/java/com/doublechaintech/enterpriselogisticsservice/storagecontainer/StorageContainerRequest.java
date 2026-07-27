package com.doublechaintech.enterpriselogisticsservice.storagecontainer;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit;
import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitRequest;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class StorageContainerRequest<T extends StorageContainer> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public StorageContainerRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public StorageContainerRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public StorageContainerRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public StorageContainerRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public StorageContainerRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public StorageContainerRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public StorageContainerRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (StorageContainerRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public StorageContainerRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public StorageContainerRequest<T> matchingAnyOf(StorageContainerRequest storageContainer){
        super.internalMatchAny(storageContainer);
        return this;
    }

    public StorageContainerRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public StorageContainerRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public StorageContainerRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public StorageContainerRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectContainerId().selectWarehouseIdOnly().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public StorageContainerRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public StorageContainerRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectContainerId().selectWarehouse().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public StorageContainerRequest<T> selectChildren(){
        super.selectAny();
        selectContainerUnitList();
        return selectId().selectContainerId().selectWarehouse().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public StorageContainerRequest<T> selectId(){
       selectProperty(StorageContainer.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageContainerRequest<T> unselectId(){
       unselectProperty(StorageContainer.ID_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectContainerId(){
       selectProperty(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }

    /**
     * fill the containerId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  containerId) to fetch containerId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageContainerRequest<T> unselectContainerId(){
       unselectProperty(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectWarehouseIdOnly(){
       selectProperty(StorageContainer.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> selectWarehouse(){
        return selectWarehouseWith(Q.warehouses().unlimited().selectSelf());
    }

    public StorageContainerRequest<T> selectWarehouseWith(WarehouseRequest warehouse){
       selectProperty(StorageContainer.WAREHOUSE_PROPERTY);
       enhanceRelation(StorageContainer.WAREHOUSE_PROPERTY, warehouse);
       return this;
    }

    public StorageContainerRequest<T> unselectWarehouse(){
       unselectProperty(StorageContainer.WAREHOUSE_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectStatus(){
       selectProperty(StorageContainer.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageContainerRequest<T> unselectStatus(){
       unselectProperty(StorageContainer.STATUS_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectCreateTime(){
       selectProperty(StorageContainer.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageContainerRequest<T> unselectCreateTime(){
       unselectProperty(StorageContainer.CREATE_TIME_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectUpdateTime(){
       selectProperty(StorageContainer.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageContainerRequest<T> unselectUpdateTime(){
       unselectProperty(StorageContainer.UPDATE_TIME_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectVersion(){
       selectProperty(StorageContainer.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageContainerRequest<T> unselectVersion(){
       unselectProperty(StorageContainer.VERSION_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> selectContainerUnitList(){
       return selectContainerUnitListWith(Q.containerUnits().selectSelf());
    }

    public StorageContainerRequest<T> selectContainerUnitListWith(ContainerUnitRequest containerUnitList){
       enhanceRelation(StorageContainer.CONTAINER_UNIT_LIST_PROPERTY, containerUnitList);
       return this;
    }

    public StorageContainerRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.ID_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public StorageContainerRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public StorageContainerRequest<T> filterByContainerId(String... containerId){
      if (containerId == null || containerId.length == 0) {
        throw new IllegalArgumentException("filterByContainerId parameter containerId cannot be empty");
      }
      return appendSearchCriteria(createContainerIdCriteria(Operator.EQUAL, (Object[])containerId));
    }

    public StorageContainerRequest<T> withContainerId(Operator operator, Object... values){
       return appendSearchCriteria(createContainerIdCriteria(operator, values));
    }

    public StorageContainerRequest<T> withContainerIdIsUnknown(){
       return withContainerId(Operator.IS_NULL);
    }

    public StorageContainerRequest<T> withContainerIdIsKnown(){
       return withContainerId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContainerIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.CONTAINER_ID_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> withContainerIdGreaterThan(String containerId){
       return withContainerId(Operator.GREATER_THAN, containerId);
    }

    public StorageContainerRequest<T> withContainerIdGreaterThanOrEqualTo(String containerId){
       return withContainerId(Operator.GREATER_THAN_OR_EQUAL, containerId);
    }

    public StorageContainerRequest<T> withContainerIdLessThan(String containerId){
       return withContainerId(Operator.LESS_THAN, containerId);
    }

    public StorageContainerRequest<T> withContainerIdLessThanOrEqualTo(String containerId){
       return withContainerId(Operator.LESS_THAN_OR_EQUAL, containerId);
    }

    public StorageContainerRequest<T> withContainerIdBetween(String startOfContainerId, String endOfContainerId){
       return withContainerId(Operator.BETWEEN, startOfContainerId, endOfContainerId);
    }
    public StorageContainerRequest<T> withContainerIdStartingWith(String containerId){
       return withContainerId(Operator.BEGIN_WITH, containerId);
    }
    public StorageContainerRequest<T> withContainerIdContaining(String containerId){
       return withContainerId(Operator.CONTAIN, containerId);
    }

    public StorageContainerRequest<T> withContainerIdEndingWith(String containerId){
       return withContainerId(Operator.END_WITH, containerId);
    }

    public StorageContainerRequest<T> withContainerIdIs(String containerId){
       return withContainerId(Operator.EQUAL, containerId);
    }

    public StorageContainerRequest<T> withContainerIdSoundingLike(String containerId){
       return withContainerId(Operator.SOUNDS_LIKE, containerId);
    }



    public StorageContainerRequest<T> filterByWarehouse(Warehouse... warehouse){
      if (warehouse == null || warehouse.length == 0) {
        throw new IllegalArgumentException("filterByWarehouse parameter warehouse cannot be empty");
      }
      return appendSearchCriteria(createWarehouseCriteria(Operator.EQUAL, (Object[])warehouse));
    }

    public StorageContainerRequest<T> withWarehouse(Operator operator, Object... values){
       return appendSearchCriteria(createWarehouseCriteria(operator, values));
    }

    public StorageContainerRequest<T> withWarehouseIsUnknown(){
       return withWarehouse(Operator.IS_NULL);
    }

    public StorageContainerRequest<T> withWarehouseIsKnown(){
       return withWarehouse(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createWarehouseCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.WAREHOUSE_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> filterByWarehouse(Long warehouse){
      if(warehouse == null){
         return this;
      }
      return withWarehouse(Operator.EQUAL, warehouse);
    }
    public StorageContainerRequest<T> withWarehouseMatching(WarehouseRequest warehouse){
       return appendSearchCriteria(new SubQuerySearchCriteria(StorageContainer.WAREHOUSE_PROPERTY, warehouse, Warehouse.ID_PROPERTY));
    }

    public StorageContainerRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public StorageContainerRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public StorageContainerRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public StorageContainerRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.STATUS_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public StorageContainerRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public StorageContainerRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public StorageContainerRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public StorageContainerRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public StorageContainerRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public StorageContainerRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public StorageContainerRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public StorageContainerRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public StorageContainerRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public StorageContainerRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public StorageContainerRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public StorageContainerRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public StorageContainerRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.CREATE_TIME_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public StorageContainerRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageContainerRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public StorageContainerRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public StorageContainerRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public StorageContainerRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public StorageContainerRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.UPDATE_TIME_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public StorageContainerRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageContainerRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public StorageContainerRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public StorageContainerRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public StorageContainerRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public StorageContainerRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageContainer.VERSION_PROPERTY, operator, values);
    }

    public StorageContainerRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public StorageContainerRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public StorageContainerRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public StorageContainerRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public StorageContainerRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public StorageContainerRequest<T> withContainerUnitListMatching(ContainerUnitRequest containerUnitRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StorageContainer.ID_PROPERTY, containerUnitRequest, ContainerUnit.STORAGE_CONTAINER_PROPERTY));
    }

    public StorageContainerRequest<T> withoutContainerUnitListMatching(ContainerUnitRequest containerUnitRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StorageContainer.ID_PROPERTY, containerUnitRequest, ContainerUnit.STORAGE_CONTAINER_PROPERTY)));
    }

    public StorageContainerRequest<T> haveContainerUnits(){
        return withContainerUnitListMatching(Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> haveNoContainerUnits(){
        return withoutContainerUnitListMatching(Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> count(){
        super.count();
        return this;
    }
    public StorageContainerRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public StorageContainerRequest<T> groupByWarehouseWithDetails(){
       return groupByWarehouseWithDetails(Q.warehouses().unlimited());
    }

    public StorageContainerRequest<T> groupByWarehouseWithDetails(WarehouseRequest subRequest){
       aggregate(StorageContainer.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }





    public StorageContainerRequest<T> groupByContainerUnitsWithDetails(ContainerUnitRequest subRequest){
       aggregate(StorageContainer.CONTAINER_UNIT_LIST_PROPERTY, subRequest);
       return this;
    }

    public StorageContainerRequest<T> groupById(){
       groupBy(StorageContainer.ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByIdAs(String retName){
       groupBy(retName, StorageContainer.ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.ID_PROPERTY, function);
       return this;
    }

    public StorageContainerRequest<T> groupByContainerId(){
       groupBy(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByContainerIdAs(String retName){
       groupBy(retName, StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByContainerIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.CONTAINER_ID_PROPERTY, function);
       return this;
    }
    public StorageContainerRequest<T> groupByWarehouseWith(WarehouseRequest subRequest){
       groupBy(StorageContainer.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }
    public StorageContainerRequest<T> groupByWarehouse(){
       groupBy(StorageContainer.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByWarehouseAs(String retName){
       groupBy(retName, StorageContainer.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByWarehouseWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.WAREHOUSE_PROPERTY, function);
       return this;
    }

    public StorageContainerRequest<T> groupByStatus(){
       groupBy(StorageContainer.STATUS_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByStatusAs(String retName){
       groupBy(retName, StorageContainer.STATUS_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.STATUS_PROPERTY, function);
       return this;
    }

    public StorageContainerRequest<T> groupByCreateTime(){
       groupBy(StorageContainer.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, StorageContainer.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public StorageContainerRequest<T> groupByUpdateTime(){
       groupBy(StorageContainer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, StorageContainer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public StorageContainerRequest<T> groupByVersion(){
       groupBy(StorageContainer.VERSION_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByVersionAs(String retName){
       groupBy(retName, StorageContainer.VERSION_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageContainer.VERSION_PROPERTY, function);
       return this;
    }



    public StorageContainerRequest<T> orderByIdAscending(){
       addOrderByAscending(StorageContainer.ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByIdDescending(){
       addOrderByDescending(StorageContainer.ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByContainerIdAscending(){
       addOrderByAscending(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByContainerIdDescending(){
       addOrderByDescending(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> orderByContainerIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByContainerIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageContainer.CONTAINER_ID_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> orderByWarehouseAscending(){
       addOrderByAscending(StorageContainer.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByWarehouseDescending(){
       addOrderByDescending(StorageContainer.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByStatusAscending(){
       addOrderByAscending(StorageContainer.STATUS_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByStatusDescending(){
       addOrderByDescending(StorageContainer.STATUS_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageContainer.STATUS_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageContainer.STATUS_PROPERTY);
       return this;
    }
    public StorageContainerRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(StorageContainer.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(StorageContainer.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(StorageContainer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(StorageContainer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByVersionAscending(){
       addOrderByAscending(StorageContainer.VERSION_PROPERTY);
       return this;
    }

    public StorageContainerRequest<T> orderByVersionDescending(){
       addOrderByDescending(StorageContainer.VERSION_PROPERTY);
       return this;
    }


    public StorageContainerRequest<T> statsFromContainerUnitsAs(String name, ContainerUnitRequest subRequest){
       return statsFromContainerUnitsAs(name, subRequest, false);
    }

    public StorageContainerRequest<T> statsFromContainerUnitsAs(String name, ContainerUnitRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(ContainerUnit.STORAGE_CONTAINER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StorageContainerRequest<T> statsFromContainerUnits(ContainerUnitRequest subRequest){
       return statsFromContainerUnitsAs(REFINEMENTS, subRequest);
    }
    public WarehouseRequest rollUpToWarehouse(){
       WarehouseRequest warehouse = Q.warehouses().unlimited();
       this.withWarehouseMatching(warehouse)
           .groupByWarehouseWith(warehouse);
       return warehouse;
    }





    public StorageContainerRequest<T> countContainerUnits(){
        return countContainerUnitsAs("Count");
    }

    public StorageContainerRequest<T> countContainerUnitsAs(String name){
        return countContainerUnitsWith(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> countContainerUnitsWith(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.count(), true);
    }
    public StorageContainerRequest<T> minQuantityOfContainerUnits(){
        return minQuantityOfContainerUnitsAs("minQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> minQuantityOfContainerUnitsAs(String name){
        return minQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> minQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.minQuantity(), true);
    }
    public StorageContainerRequest<T> maxQuantityOfContainerUnits(){
        return maxQuantityOfContainerUnitsAs("maxQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> maxQuantityOfContainerUnitsAs(String name){
        return maxQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> maxQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.maxQuantity(), true);
    }
    public StorageContainerRequest<T> sumQuantityOfContainerUnits(){
        return sumQuantityOfContainerUnitsAs("sumQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> sumQuantityOfContainerUnitsAs(String name){
        return sumQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> sumQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.sumQuantity(), true);
    }
    public StorageContainerRequest<T> avgQuantityOfContainerUnits(){
        return avgQuantityOfContainerUnitsAs("avgQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> avgQuantityOfContainerUnitsAs(String name){
        return avgQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> avgQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.avgQuantity(), true);
    }
    public StorageContainerRequest<T> standardDeviationQuantityOfContainerUnits(){
        return standardDeviationQuantityOfContainerUnitsAs("stdDevQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> standardDeviationQuantityOfContainerUnitsAs(String name){
        return standardDeviationQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> standardDeviationQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.standardDeviationQuantity(), true);
    }
    public StorageContainerRequest<T> squareRootOfPopulationStandardDeviationQuantityOfContainerUnits(){
        return squareRootOfPopulationStandardDeviationQuantityOfContainerUnitsAs("stdDevPopQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> squareRootOfPopulationStandardDeviationQuantityOfContainerUnitsAs(String name){
        return squareRootOfPopulationStandardDeviationQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> squareRootOfPopulationStandardDeviationQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.squareRootOfPopulationStandardDeviationQuantity(), true);
    }
    public StorageContainerRequest<T> sampleVarianceQuantityOfContainerUnits(){
        return sampleVarianceQuantityOfContainerUnitsAs("varSampQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> sampleVarianceQuantityOfContainerUnitsAs(String name){
        return sampleVarianceQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> sampleVarianceQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.sampleVarianceQuantity(), true);
    }
    public StorageContainerRequest<T> samplePopulationVarianceQuantityOfContainerUnits(){
        return samplePopulationVarianceQuantityOfContainerUnitsAs("varPopQuantityOfContainerUnits");
    }

    public StorageContainerRequest<T> samplePopulationVarianceQuantityOfContainerUnitsAs(String name){
        return samplePopulationVarianceQuantityOfContainerUnitsAs(name, Q.containerUnits().unlimited());
    }

    public StorageContainerRequest<T> samplePopulationVarianceQuantityOfContainerUnitsAs(String name, ContainerUnitRequest subRequest){
        return statsFromContainerUnitsAs(name, subRequest.samplePopulationVarianceQuantity(), true);
    }

   public StorageContainerRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse){
       return facetByWarehouseAs(facetName, warehouse, true);
   }

   public StorageContainerRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse, boolean includeAllFacets){
       addFacet(facetName, StorageContainer.WAREHOUSE_PROPERTY, warehouse, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public StorageContainerRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public StorageContainerRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public StorageContainerRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public StorageContainerRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public StorageContainerRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}