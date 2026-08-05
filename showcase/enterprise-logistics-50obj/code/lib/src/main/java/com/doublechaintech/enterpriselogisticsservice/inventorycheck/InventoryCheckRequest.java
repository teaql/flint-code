package com.doublechaintech.enterpriselogisticsservice.inventorycheck;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class InventoryCheckRequest<T extends InventoryCheck> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public InventoryCheckRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public InventoryCheckRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public InventoryCheckRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public InventoryCheckRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public InventoryCheckRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public InventoryCheckRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public InventoryCheckRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (InventoryCheckRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public InventoryCheckRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public InventoryCheckRequest<T> matchingAnyOf(InventoryCheckRequest inventoryCheck){
        super.internalMatchAny(inventoryCheck);
        return this;
    }

    public InventoryCheckRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public InventoryCheckRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public InventoryCheckRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public InventoryCheckRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectWarehouseIdOnly().selectCheckDate().selectChecker().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public InventoryCheckRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public InventoryCheckRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectWarehouse().selectCheckDate().selectChecker().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public InventoryCheckRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectWarehouse().selectCheckDate().selectChecker().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public InventoryCheckRequest<T> selectId(){
       selectProperty(InventoryCheck.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectId(){
       unselectProperty(InventoryCheck.ID_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectWarehouseIdOnly(){
       selectProperty(InventoryCheck.WAREHOUSE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> selectWarehouse(){
        return selectWarehouseWith(Q.warehouses().unlimited().selectSelf());
    }

    public InventoryCheckRequest<T> selectWarehouseWith(WarehouseRequest warehouse){
       selectProperty(InventoryCheck.WAREHOUSE_PROPERTY);
       enhanceRelation(InventoryCheck.WAREHOUSE_PROPERTY, warehouse);
       return this;
    }

    public InventoryCheckRequest<T> unselectWarehouse(){
       unselectProperty(InventoryCheck.WAREHOUSE_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectCheckDate(){
       selectProperty(InventoryCheck.CHECK_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the checkDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  checkDate) to fetch checkDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectCheckDate(){
       unselectProperty(InventoryCheck.CHECK_DATE_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectChecker(){
       selectProperty(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }

    /**
     * fill the checker with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  checker) to fetch checker property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectChecker(){
       unselectProperty(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectStatus(){
       selectProperty(InventoryCheck.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectStatus(){
       unselectProperty(InventoryCheck.STATUS_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectCreateTime(){
       selectProperty(InventoryCheck.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectCreateTime(){
       unselectProperty(InventoryCheck.CREATE_TIME_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectUpdateTime(){
       selectProperty(InventoryCheck.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectUpdateTime(){
       unselectProperty(InventoryCheck.UPDATE_TIME_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> selectVersion(){
       selectProperty(InventoryCheck.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public InventoryCheckRequest<T> unselectVersion(){
       unselectProperty(InventoryCheck.VERSION_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.ID_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public InventoryCheckRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public InventoryCheckRequest<T> filterByWarehouse(Warehouse... warehouse){
      if (warehouse == null || warehouse.length == 0) {
        throw new IllegalArgumentException("filterByWarehouse parameter warehouse cannot be empty");
      }
      return appendSearchCriteria(createWarehouseCriteria(Operator.EQUAL, (Object[])warehouse));
    }

    public InventoryCheckRequest<T> withWarehouse(Operator operator, Object... values){
       return appendSearchCriteria(createWarehouseCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withWarehouseIsUnknown(){
       return withWarehouse(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withWarehouseIsKnown(){
       return withWarehouse(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createWarehouseCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.WAREHOUSE_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> filterByWarehouse(Long warehouse){
      if(warehouse == null){
         return this;
      }
      return withWarehouse(Operator.EQUAL, warehouse);
    }
    public InventoryCheckRequest<T> withWarehouseMatching(WarehouseRequest warehouse){
       return appendSearchCriteria(new SubQuerySearchCriteria(InventoryCheck.WAREHOUSE_PROPERTY, warehouse, Warehouse.ID_PROPERTY));
    }

    public InventoryCheckRequest<T> filterByCheckDate(LocalDate... checkDate){
      if (checkDate == null || checkDate.length == 0) {
        throw new IllegalArgumentException("filterByCheckDate parameter checkDate cannot be empty");
      }
      return appendSearchCriteria(createCheckDateCriteria(Operator.EQUAL, (Object[])checkDate));
    }

    public InventoryCheckRequest<T> withCheckDate(Operator operator, Object... values){
       return appendSearchCriteria(createCheckDateCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withCheckDateIsUnknown(){
       return withCheckDate(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withCheckDateIsKnown(){
       return withCheckDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCheckDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.CHECK_DATE_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withCheckDateGreaterThan(LocalDate checkDate){
       return withCheckDate(Operator.GREATER_THAN, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateGreaterThanOrEqualTo(LocalDate checkDate){
       return withCheckDate(Operator.GREATER_THAN_OR_EQUAL, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateLessThan(LocalDate checkDate){
       return withCheckDate(Operator.LESS_THAN, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateLessThanOrEqualTo(LocalDate checkDate){
       return withCheckDate(Operator.LESS_THAN_OR_EQUAL, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateBetween(LocalDate startOfCheckDate, LocalDate endOfCheckDate){
       return withCheckDate(Operator.BETWEEN, startOfCheckDate, endOfCheckDate);
    }
    public InventoryCheckRequest<T> withCheckDateBefore(LocalDate checkDate){
       return withCheckDate(Operator.LESS_THAN, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateBefore(Date checkDate){
       return withCheckDate(Operator.LESS_THAN, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateAfter(LocalDate checkDate){
       return withCheckDate(Operator.GREATER_THAN, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateAfter(Date checkDate){
       return withCheckDate(Operator.GREATER_THAN, checkDate);
    }

    public InventoryCheckRequest<T> withCheckDateBetween(Date startOfCheckDate, Date endOfCheckDate){
       return withCheckDate(Operator.BETWEEN, startOfCheckDate, endOfCheckDate);
    }




    public InventoryCheckRequest<T> filterByChecker(String... checker){
      if (checker == null || checker.length == 0) {
        throw new IllegalArgumentException("filterByChecker parameter checker cannot be empty");
      }
      return appendSearchCriteria(createCheckerCriteria(Operator.EQUAL, (Object[])checker));
    }

    public InventoryCheckRequest<T> withChecker(Operator operator, Object... values){
       return appendSearchCriteria(createCheckerCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withCheckerIsUnknown(){
       return withChecker(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withCheckerIsKnown(){
       return withChecker(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCheckerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.CHECKER_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withCheckerGreaterThan(String checker){
       return withChecker(Operator.GREATER_THAN, checker);
    }

    public InventoryCheckRequest<T> withCheckerGreaterThanOrEqualTo(String checker){
       return withChecker(Operator.GREATER_THAN_OR_EQUAL, checker);
    }

    public InventoryCheckRequest<T> withCheckerLessThan(String checker){
       return withChecker(Operator.LESS_THAN, checker);
    }

    public InventoryCheckRequest<T> withCheckerLessThanOrEqualTo(String checker){
       return withChecker(Operator.LESS_THAN_OR_EQUAL, checker);
    }

    public InventoryCheckRequest<T> withCheckerBetween(String startOfChecker, String endOfChecker){
       return withChecker(Operator.BETWEEN, startOfChecker, endOfChecker);
    }
    public InventoryCheckRequest<T> withCheckerStartingWith(String checker){
       return withChecker(Operator.BEGIN_WITH, checker);
    }
    public InventoryCheckRequest<T> withCheckerContaining(String checker){
       return withChecker(Operator.CONTAIN, checker);
    }

    public InventoryCheckRequest<T> withCheckerEndingWith(String checker){
       return withChecker(Operator.END_WITH, checker);
    }

    public InventoryCheckRequest<T> withCheckerIs(String checker){
       return withChecker(Operator.EQUAL, checker);
    }

    public InventoryCheckRequest<T> withCheckerSoundingLike(String checker){
       return withChecker(Operator.SOUNDS_LIKE, checker);
    }



    public InventoryCheckRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public InventoryCheckRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.STATUS_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public InventoryCheckRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public InventoryCheckRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public InventoryCheckRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public InventoryCheckRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public InventoryCheckRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public InventoryCheckRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public InventoryCheckRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public InventoryCheckRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public InventoryCheckRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public InventoryCheckRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public InventoryCheckRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.CREATE_TIME_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public InventoryCheckRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public InventoryCheckRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public InventoryCheckRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public InventoryCheckRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.UPDATE_TIME_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public InventoryCheckRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public InventoryCheckRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public InventoryCheckRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public InventoryCheckRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public InventoryCheckRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public InventoryCheckRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(InventoryCheck.VERSION_PROPERTY, operator, values);
    }

    public InventoryCheckRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public InventoryCheckRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public InventoryCheckRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public InventoryCheckRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public InventoryCheckRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public InventoryCheckRequest<T> count(){
        super.count();
        return this;
    }
    public InventoryCheckRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public InventoryCheckRequest<T> groupByWarehouseWithDetails(){
       return groupByWarehouseWithDetails(Q.warehouses().unlimited());
    }

    public InventoryCheckRequest<T> groupByWarehouseWithDetails(WarehouseRequest subRequest){
       aggregate(InventoryCheck.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }








    public InventoryCheckRequest<T> groupById(){
       groupBy(InventoryCheck.ID_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByIdAs(String retName){
       groupBy(retName, InventoryCheck.ID_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.ID_PROPERTY, function);
       return this;
    }
    public InventoryCheckRequest<T> groupByWarehouseWith(WarehouseRequest subRequest){
       groupBy(InventoryCheck.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }
    public InventoryCheckRequest<T> groupByWarehouse(){
       groupBy(InventoryCheck.WAREHOUSE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByWarehouseAs(String retName){
       groupBy(retName, InventoryCheck.WAREHOUSE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByWarehouseWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.WAREHOUSE_PROPERTY, function);
       return this;
    }

    public InventoryCheckRequest<T> groupByCheckDate(){
       groupBy(InventoryCheck.CHECK_DATE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByCheckDateAs(String retName){
       groupBy(retName, InventoryCheck.CHECK_DATE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByCheckDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.CHECK_DATE_PROPERTY, function);
       return this;
    }

    public InventoryCheckRequest<T> groupByChecker(){
       groupBy(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByCheckerAs(String retName){
       groupBy(retName, InventoryCheck.CHECKER_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByCheckerWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.CHECKER_PROPERTY, function);
       return this;
    }

    public InventoryCheckRequest<T> groupByStatus(){
       groupBy(InventoryCheck.STATUS_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByStatusAs(String retName){
       groupBy(retName, InventoryCheck.STATUS_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.STATUS_PROPERTY, function);
       return this;
    }

    public InventoryCheckRequest<T> groupByCreateTime(){
       groupBy(InventoryCheck.CREATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, InventoryCheck.CREATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public InventoryCheckRequest<T> groupByUpdateTime(){
       groupBy(InventoryCheck.UPDATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, InventoryCheck.UPDATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public InventoryCheckRequest<T> groupByVersion(){
       groupBy(InventoryCheck.VERSION_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByVersionAs(String retName){
       groupBy(retName, InventoryCheck.VERSION_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, InventoryCheck.VERSION_PROPERTY, function);
       return this;
    }



    public InventoryCheckRequest<T> orderByIdAscending(){
       addOrderByAscending(InventoryCheck.ID_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByIdDescending(){
       addOrderByDescending(InventoryCheck.ID_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByWarehouseAscending(){
       addOrderByAscending(InventoryCheck.WAREHOUSE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByWarehouseDescending(){
       addOrderByDescending(InventoryCheck.WAREHOUSE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByCheckDateAscending(){
       addOrderByAscending(InventoryCheck.CHECK_DATE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByCheckDateDescending(){
       addOrderByDescending(InventoryCheck.CHECK_DATE_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByCheckerAscending(){
       addOrderByAscending(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByCheckerDescending(){
       addOrderByDescending(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> orderByCheckerAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByCheckerDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(InventoryCheck.CHECKER_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> orderByStatusAscending(){
       addOrderByAscending(InventoryCheck.STATUS_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByStatusDescending(){
       addOrderByDescending(InventoryCheck.STATUS_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(InventoryCheck.STATUS_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(InventoryCheck.STATUS_PROPERTY);
       return this;
    }
    public InventoryCheckRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(InventoryCheck.CREATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(InventoryCheck.CREATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(InventoryCheck.UPDATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(InventoryCheck.UPDATE_TIME_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByVersionAscending(){
       addOrderByAscending(InventoryCheck.VERSION_PROPERTY);
       return this;
    }

    public InventoryCheckRequest<T> orderByVersionDescending(){
       addOrderByDescending(InventoryCheck.VERSION_PROPERTY);
       return this;
    }


    public WarehouseRequest rollUpToWarehouse(){
       WarehouseRequest warehouse = Q.warehouses().unlimited();
       this.withWarehouseMatching(warehouse)
           .groupByWarehouseWith(warehouse);
       return warehouse;
    }








   public InventoryCheckRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse){
       return facetByWarehouseAs(facetName, warehouse, true);
   }

   public InventoryCheckRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse, boolean includeAllFacets){
       addFacet(facetName, InventoryCheck.WAREHOUSE_PROPERTY, warehouse, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public InventoryCheckRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public InventoryCheckRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public InventoryCheckRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public InventoryCheckRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public InventoryCheckRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}