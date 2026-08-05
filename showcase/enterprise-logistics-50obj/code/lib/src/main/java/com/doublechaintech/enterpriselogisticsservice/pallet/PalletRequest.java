package com.doublechaintech.enterpriselogisticsservice.pallet;

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
import java.time.LocalDateTime;
import java.util.Date;

public class PalletRequest<T extends Pallet> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PalletRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PalletRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PalletRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PalletRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PalletRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PalletRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PalletRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PalletRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PalletRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PalletRequest<T> matchingAnyOf(PalletRequest pallet){
        super.internalMatchAny(pallet);
        return this;
    }

    public PalletRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PalletRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PalletRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PalletRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectWarehouseIdOnly().selectPalletId().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public PalletRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PalletRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectWarehouse().selectPalletId().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public PalletRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectWarehouse().selectPalletId().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public PalletRequest<T> selectId(){
       selectProperty(Pallet.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PalletRequest<T> unselectId(){
       unselectProperty(Pallet.ID_PROPERTY);
       return this;
    }
    public PalletRequest<T> selectWarehouseIdOnly(){
       selectProperty(Pallet.WAREHOUSE_PROPERTY);
       return this;
    }

    public PalletRequest<T> selectWarehouse(){
        return selectWarehouseWith(Q.warehouses().unlimited().selectSelf());
    }

    public PalletRequest<T> selectWarehouseWith(WarehouseRequest warehouse){
       selectProperty(Pallet.WAREHOUSE_PROPERTY);
       enhanceRelation(Pallet.WAREHOUSE_PROPERTY, warehouse);
       return this;
    }

    public PalletRequest<T> unselectWarehouse(){
       unselectProperty(Pallet.WAREHOUSE_PROPERTY);
       return this;
    }
    public PalletRequest<T> selectPalletId(){
       selectProperty(Pallet.PALLET_ID_PROPERTY);
       return this;
    }

    /**
     * fill the palletId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  palletId) to fetch palletId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PalletRequest<T> unselectPalletId(){
       unselectProperty(Pallet.PALLET_ID_PROPERTY);
       return this;
    }
    public PalletRequest<T> selectStatus(){
       selectProperty(Pallet.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PalletRequest<T> unselectStatus(){
       unselectProperty(Pallet.STATUS_PROPERTY);
       return this;
    }
    public PalletRequest<T> selectCreateTime(){
       selectProperty(Pallet.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PalletRequest<T> unselectCreateTime(){
       unselectProperty(Pallet.CREATE_TIME_PROPERTY);
       return this;
    }
    public PalletRequest<T> selectUpdateTime(){
       selectProperty(Pallet.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PalletRequest<T> unselectUpdateTime(){
       unselectProperty(Pallet.UPDATE_TIME_PROPERTY);
       return this;
    }
    public PalletRequest<T> selectVersion(){
       selectProperty(Pallet.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PalletRequest<T> unselectVersion(){
       unselectProperty(Pallet.VERSION_PROPERTY);
       return this;
    }

    public PalletRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.ID_PROPERTY, operator, values);
    }

    public PalletRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PalletRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PalletRequest<T> filterByWarehouse(Warehouse... warehouse){
      if (warehouse == null || warehouse.length == 0) {
        throw new IllegalArgumentException("filterByWarehouse parameter warehouse cannot be empty");
      }
      return appendSearchCriteria(createWarehouseCriteria(Operator.EQUAL, (Object[])warehouse));
    }

    public PalletRequest<T> withWarehouse(Operator operator, Object... values){
       return appendSearchCriteria(createWarehouseCriteria(operator, values));
    }

    public PalletRequest<T> withWarehouseIsUnknown(){
       return withWarehouse(Operator.IS_NULL);
    }

    public PalletRequest<T> withWarehouseIsKnown(){
       return withWarehouse(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createWarehouseCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.WAREHOUSE_PROPERTY, operator, values);
    }

    public PalletRequest<T> filterByWarehouse(Long warehouse){
      if(warehouse == null){
         return this;
      }
      return withWarehouse(Operator.EQUAL, warehouse);
    }
    public PalletRequest<T> withWarehouseMatching(WarehouseRequest warehouse){
       return appendSearchCriteria(new SubQuerySearchCriteria(Pallet.WAREHOUSE_PROPERTY, warehouse, Warehouse.ID_PROPERTY));
    }

    public PalletRequest<T> filterByPalletId(String... palletId){
      if (palletId == null || palletId.length == 0) {
        throw new IllegalArgumentException("filterByPalletId parameter palletId cannot be empty");
      }
      return appendSearchCriteria(createPalletIdCriteria(Operator.EQUAL, (Object[])palletId));
    }

    public PalletRequest<T> withPalletId(Operator operator, Object... values){
       return appendSearchCriteria(createPalletIdCriteria(operator, values));
    }

    public PalletRequest<T> withPalletIdIsUnknown(){
       return withPalletId(Operator.IS_NULL);
    }

    public PalletRequest<T> withPalletIdIsKnown(){
       return withPalletId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPalletIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.PALLET_ID_PROPERTY, operator, values);
    }

    public PalletRequest<T> withPalletIdGreaterThan(String palletId){
       return withPalletId(Operator.GREATER_THAN, palletId);
    }

    public PalletRequest<T> withPalletIdGreaterThanOrEqualTo(String palletId){
       return withPalletId(Operator.GREATER_THAN_OR_EQUAL, palletId);
    }

    public PalletRequest<T> withPalletIdLessThan(String palletId){
       return withPalletId(Operator.LESS_THAN, palletId);
    }

    public PalletRequest<T> withPalletIdLessThanOrEqualTo(String palletId){
       return withPalletId(Operator.LESS_THAN_OR_EQUAL, palletId);
    }

    public PalletRequest<T> withPalletIdBetween(String startOfPalletId, String endOfPalletId){
       return withPalletId(Operator.BETWEEN, startOfPalletId, endOfPalletId);
    }
    public PalletRequest<T> withPalletIdStartingWith(String palletId){
       return withPalletId(Operator.BEGIN_WITH, palletId);
    }
    public PalletRequest<T> withPalletIdContaining(String palletId){
       return withPalletId(Operator.CONTAIN, palletId);
    }

    public PalletRequest<T> withPalletIdEndingWith(String palletId){
       return withPalletId(Operator.END_WITH, palletId);
    }

    public PalletRequest<T> withPalletIdIs(String palletId){
       return withPalletId(Operator.EQUAL, palletId);
    }

    public PalletRequest<T> withPalletIdSoundingLike(String palletId){
       return withPalletId(Operator.SOUNDS_LIKE, palletId);
    }



    public PalletRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public PalletRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public PalletRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public PalletRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.STATUS_PROPERTY, operator, values);
    }

    public PalletRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public PalletRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public PalletRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public PalletRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public PalletRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public PalletRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public PalletRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public PalletRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public PalletRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public PalletRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public PalletRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public PalletRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public PalletRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public PalletRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.CREATE_TIME_PROPERTY, operator, values);
    }

    public PalletRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PalletRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public PalletRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PalletRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public PalletRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public PalletRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PalletRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PalletRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PalletRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PalletRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public PalletRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public PalletRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public PalletRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public PalletRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.UPDATE_TIME_PROPERTY, operator, values);
    }

    public PalletRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PalletRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public PalletRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PalletRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public PalletRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public PalletRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PalletRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PalletRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PalletRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PalletRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public PalletRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PalletRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PalletRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PalletRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Pallet.VERSION_PROPERTY, operator, values);
    }

    public PalletRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PalletRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PalletRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PalletRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PalletRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public PalletRequest<T> count(){
        super.count();
        return this;
    }
    public PalletRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PalletRequest<T> groupByWarehouseWithDetails(){
       return groupByWarehouseWithDetails(Q.warehouses().unlimited());
    }

    public PalletRequest<T> groupByWarehouseWithDetails(WarehouseRequest subRequest){
       aggregate(Pallet.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }







    public PalletRequest<T> groupById(){
       groupBy(Pallet.ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByIdAs(String retName){
       groupBy(retName, Pallet.ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.ID_PROPERTY, function);
       return this;
    }
    public PalletRequest<T> groupByWarehouseWith(WarehouseRequest subRequest){
       groupBy(Pallet.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }
    public PalletRequest<T> groupByWarehouse(){
       groupBy(Pallet.WAREHOUSE_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByWarehouseAs(String retName){
       groupBy(retName, Pallet.WAREHOUSE_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByWarehouseWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.WAREHOUSE_PROPERTY, function);
       return this;
    }

    public PalletRequest<T> groupByPalletId(){
       groupBy(Pallet.PALLET_ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByPalletIdAs(String retName){
       groupBy(retName, Pallet.PALLET_ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByPalletIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.PALLET_ID_PROPERTY, function);
       return this;
    }

    public PalletRequest<T> groupByStatus(){
       groupBy(Pallet.STATUS_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByStatusAs(String retName){
       groupBy(retName, Pallet.STATUS_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.STATUS_PROPERTY, function);
       return this;
    }

    public PalletRequest<T> groupByCreateTime(){
       groupBy(Pallet.CREATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, Pallet.CREATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public PalletRequest<T> groupByUpdateTime(){
       groupBy(Pallet.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, Pallet.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public PalletRequest<T> groupByVersion(){
       groupBy(Pallet.VERSION_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Pallet.VERSION_PROPERTY);
       return this;
    }

    public PalletRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Pallet.VERSION_PROPERTY, function);
       return this;
    }



    public PalletRequest<T> orderByIdAscending(){
       addOrderByAscending(Pallet.ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByIdDescending(){
       addOrderByDescending(Pallet.ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByWarehouseAscending(){
       addOrderByAscending(Pallet.WAREHOUSE_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByWarehouseDescending(){
       addOrderByDescending(Pallet.WAREHOUSE_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByPalletIdAscending(){
       addOrderByAscending(Pallet.PALLET_ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByPalletIdDescending(){
       addOrderByDescending(Pallet.PALLET_ID_PROPERTY);
       return this;
    }
    public PalletRequest<T> orderByPalletIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Pallet.PALLET_ID_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByPalletIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Pallet.PALLET_ID_PROPERTY);
       return this;
    }
    public PalletRequest<T> orderByStatusAscending(){
       addOrderByAscending(Pallet.STATUS_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByStatusDescending(){
       addOrderByDescending(Pallet.STATUS_PROPERTY);
       return this;
    }
    public PalletRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Pallet.STATUS_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Pallet.STATUS_PROPERTY);
       return this;
    }
    public PalletRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(Pallet.CREATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(Pallet.CREATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(Pallet.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(Pallet.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByVersionAscending(){
       addOrderByAscending(Pallet.VERSION_PROPERTY);
       return this;
    }

    public PalletRequest<T> orderByVersionDescending(){
       addOrderByDescending(Pallet.VERSION_PROPERTY);
       return this;
    }


    public WarehouseRequest rollUpToWarehouse(){
       WarehouseRequest warehouse = Q.warehouses().unlimited();
       this.withWarehouseMatching(warehouse)
           .groupByWarehouseWith(warehouse);
       return warehouse;
    }







   public PalletRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse){
       return facetByWarehouseAs(facetName, warehouse, true);
   }

   public PalletRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse, boolean includeAllFacets){
       addFacet(facetName, Pallet.WAREHOUSE_PROPERTY, warehouse, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public PalletRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PalletRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PalletRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PalletRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PalletRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}