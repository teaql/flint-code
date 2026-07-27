package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest;
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

public class StorageFeeRequest<T extends StorageFee> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public StorageFeeRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public StorageFeeRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public StorageFeeRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public StorageFeeRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public StorageFeeRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public StorageFeeRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public StorageFeeRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (StorageFeeRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public StorageFeeRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public StorageFeeRequest<T> matchingAnyOf(StorageFeeRequest storageFee){
        super.internalMatchAny(storageFee);
        return this;
    }

    public StorageFeeRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public StorageFeeRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public StorageFeeRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public StorageFeeRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectWarehouseIdOnly().selectContainerIdOnly().selectAmount().selectCurrency().selectPeriod().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public StorageFeeRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public StorageFeeRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectWarehouse().selectContainer().selectAmount().selectCurrency().selectPeriod().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public StorageFeeRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectWarehouse().selectContainer().selectAmount().selectCurrency().selectPeriod().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public StorageFeeRequest<T> selectId(){
       selectProperty(StorageFee.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectId(){
       unselectProperty(StorageFee.ID_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectWarehouseIdOnly(){
       selectProperty(StorageFee.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> selectWarehouse(){
        return selectWarehouseWith(Q.warehouses().unlimited().selectSelf());
    }

    public StorageFeeRequest<T> selectWarehouseWith(WarehouseRequest warehouse){
       selectProperty(StorageFee.WAREHOUSE_PROPERTY);
       enhanceRelation(StorageFee.WAREHOUSE_PROPERTY, warehouse);
       return this;
    }

    public StorageFeeRequest<T> unselectWarehouse(){
       unselectProperty(StorageFee.WAREHOUSE_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectContainerIdOnly(){
       selectProperty(StorageFee.CONTAINER_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> selectContainer(){
        return selectContainerWith(Q.storageContainers().unlimited().selectSelf());
    }

    public StorageFeeRequest<T> selectContainerWith(StorageContainerRequest container){
       selectProperty(StorageFee.CONTAINER_PROPERTY);
       enhanceRelation(StorageFee.CONTAINER_PROPERTY, container);
       return this;
    }

    public StorageFeeRequest<T> unselectContainer(){
       unselectProperty(StorageFee.CONTAINER_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectAmount(){
       selectProperty(StorageFee.AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the amount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  amount) to fetch amount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the amount with customized aggrFunction, TEAQL uses ({aggrFunction}(amount) AS amount to fetch amount property.
     * @param aggrFunction  aggrFunction
     */
    public StorageFeeRequest<T> selectAmount(AggrFunction aggrFunction){
       selectProperty(StorageFee.AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public StorageFeeRequest<T> unselectAmount(){
       unselectProperty(StorageFee.AMOUNT_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectCurrency(){
       selectProperty(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectCurrency(){
       unselectProperty(StorageFee.CURRENCY_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectPeriod(){
       selectProperty(StorageFee.PERIOD_PROPERTY);
       return this;
    }

    /**
     * fill the period with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  period) to fetch period property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectPeriod(){
       unselectProperty(StorageFee.PERIOD_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectCreateTime(){
       selectProperty(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectCreateTime(){
       unselectProperty(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectUpdateTime(){
       selectProperty(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectUpdateTime(){
       unselectProperty(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> selectVersion(){
       selectProperty(StorageFee.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StorageFeeRequest<T> unselectVersion(){
       unselectProperty(StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.ID_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public StorageFeeRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public StorageFeeRequest<T> filterByWarehouse(Warehouse... warehouse){
      if (warehouse == null || warehouse.length == 0) {
        throw new IllegalArgumentException("filterByWarehouse parameter warehouse cannot be empty");
      }
      return appendSearchCriteria(createWarehouseCriteria(Operator.EQUAL, (Object[])warehouse));
    }

    public StorageFeeRequest<T> withWarehouse(Operator operator, Object... values){
       return appendSearchCriteria(createWarehouseCriteria(operator, values));
    }

    public StorageFeeRequest<T> withWarehouseIsUnknown(){
       return withWarehouse(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withWarehouseIsKnown(){
       return withWarehouse(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createWarehouseCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.WAREHOUSE_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> filterByWarehouse(Long warehouse){
      if(warehouse == null){
         return this;
      }
      return withWarehouse(Operator.EQUAL, warehouse);
    }
    public StorageFeeRequest<T> withWarehouseMatching(WarehouseRequest warehouse){
       return appendSearchCriteria(new SubQuerySearchCriteria(StorageFee.WAREHOUSE_PROPERTY, warehouse, Warehouse.ID_PROPERTY));
    }

    public StorageFeeRequest<T> filterByContainer(StorageContainer... container){
      if (container == null || container.length == 0) {
        throw new IllegalArgumentException("filterByContainer parameter container cannot be empty");
      }
      return appendSearchCriteria(createContainerCriteria(Operator.EQUAL, (Object[])container));
    }

    public StorageFeeRequest<T> withContainer(Operator operator, Object... values){
       return appendSearchCriteria(createContainerCriteria(operator, values));
    }

    public StorageFeeRequest<T> withContainerIsUnknown(){
       return withContainer(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withContainerIsKnown(){
       return withContainer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContainerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.CONTAINER_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> filterByContainer(Long container){
      if(container == null){
         return this;
      }
      return withContainer(Operator.EQUAL, container);
    }
    public StorageFeeRequest<T> withContainerMatching(StorageContainerRequest container){
       return appendSearchCriteria(new SubQuerySearchCriteria(StorageFee.CONTAINER_PROPERTY, container, StorageContainer.ID_PROPERTY));
    }

    public StorageFeeRequest<T> filterByAmount(BigDecimal... amount){
      if (amount == null || amount.length == 0) {
        throw new IllegalArgumentException("filterByAmount parameter amount cannot be empty");
      }
      return appendSearchCriteria(createAmountCriteria(Operator.EQUAL, (Object[])amount));
    }

    public StorageFeeRequest<T> withAmount(Operator operator, Object... values){
       return appendSearchCriteria(createAmountCriteria(operator, values));
    }

    public StorageFeeRequest<T> withAmountIsUnknown(){
       return withAmount(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withAmountIsKnown(){
       return withAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.AMOUNT_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withAmountGreaterThan(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN, amount);
    }

    public StorageFeeRequest<T> withAmountGreaterThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN_OR_EQUAL, amount);
    }

    public StorageFeeRequest<T> withAmountLessThan(BigDecimal amount){
       return withAmount(Operator.LESS_THAN, amount);
    }

    public StorageFeeRequest<T> withAmountLessThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.LESS_THAN_OR_EQUAL, amount);
    }

    public StorageFeeRequest<T> withAmountBetween(BigDecimal startOfAmount, BigDecimal endOfAmount){
       return withAmount(Operator.BETWEEN, startOfAmount, endOfAmount);
    }



    public StorageFeeRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public StorageFeeRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public StorageFeeRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.CURRENCY_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public StorageFeeRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public StorageFeeRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public StorageFeeRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public StorageFeeRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public StorageFeeRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public StorageFeeRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public StorageFeeRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public StorageFeeRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public StorageFeeRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public StorageFeeRequest<T> filterByPeriod(String... period){
      if (period == null || period.length == 0) {
        throw new IllegalArgumentException("filterByPeriod parameter period cannot be empty");
      }
      return appendSearchCriteria(createPeriodCriteria(Operator.EQUAL, (Object[])period));
    }

    public StorageFeeRequest<T> withPeriod(Operator operator, Object... values){
       return appendSearchCriteria(createPeriodCriteria(operator, values));
    }

    public StorageFeeRequest<T> withPeriodIsUnknown(){
       return withPeriod(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withPeriodIsKnown(){
       return withPeriod(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPeriodCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.PERIOD_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withPeriodGreaterThan(String period){
       return withPeriod(Operator.GREATER_THAN, period);
    }

    public StorageFeeRequest<T> withPeriodGreaterThanOrEqualTo(String period){
       return withPeriod(Operator.GREATER_THAN_OR_EQUAL, period);
    }

    public StorageFeeRequest<T> withPeriodLessThan(String period){
       return withPeriod(Operator.LESS_THAN, period);
    }

    public StorageFeeRequest<T> withPeriodLessThanOrEqualTo(String period){
       return withPeriod(Operator.LESS_THAN_OR_EQUAL, period);
    }

    public StorageFeeRequest<T> withPeriodBetween(String startOfPeriod, String endOfPeriod){
       return withPeriod(Operator.BETWEEN, startOfPeriod, endOfPeriod);
    }
    public StorageFeeRequest<T> withPeriodStartingWith(String period){
       return withPeriod(Operator.BEGIN_WITH, period);
    }
    public StorageFeeRequest<T> withPeriodContaining(String period){
       return withPeriod(Operator.CONTAIN, period);
    }

    public StorageFeeRequest<T> withPeriodEndingWith(String period){
       return withPeriod(Operator.END_WITH, period);
    }

    public StorageFeeRequest<T> withPeriodIs(String period){
       return withPeriod(Operator.EQUAL, period);
    }

    public StorageFeeRequest<T> withPeriodSoundingLike(String period){
       return withPeriod(Operator.SOUNDS_LIKE, period);
    }



    public StorageFeeRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public StorageFeeRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public StorageFeeRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.CREATE_TIME_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public StorageFeeRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public StorageFeeRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public StorageFeeRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public StorageFeeRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public StorageFeeRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.UPDATE_TIME_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public StorageFeeRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public StorageFeeRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public StorageFeeRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public StorageFeeRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public StorageFeeRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public StorageFeeRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StorageFee.VERSION_PROPERTY, operator, values);
    }

    public StorageFeeRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public StorageFeeRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public StorageFeeRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public StorageFeeRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public StorageFeeRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public StorageFeeRequest<T> count(){
        super.count();
        return this;
    }
    public StorageFeeRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public StorageFeeRequest minAmount(){
        return minAmountAs(prefix("minOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest minAmountAs(String retName){
        super.min(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest maxAmount(){
        return maxAmountAs(prefix("maxOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest maxAmountAs(String retName){
        super.max(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest sumAmount(){
        return sumAmountAs(prefix("sumOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest sumAmountAs(String retName){
        super.sum(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest avgAmount(){
        return avgAmountAs(prefix("avgOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest avgAmountAs(String retName){
        super.avg(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest standardDeviationAmount(){
        return standardDeviationAmountAs(prefix("standardDeviationOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest standardDeviationAmountAs(String retName){
        super.standardDeviation(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest squareRootOfPopulationStandardDeviationAmount(){
        return squareRootOfPopulationStandardDeviationAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest squareRootOfPopulationStandardDeviationAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest sampleVarianceAmount(){
        return sampleVarianceAmountAs(prefix("sampleVarianceOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest sampleVarianceAmountAs(String retName){
        super.sampleVariance(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest samplePopulationVarianceAmount(){
        return samplePopulationVarianceAmountAs(prefix("samplePopulationVarianceOf",StorageFee.AMOUNT_PROPERTY));
    }

    public StorageFeeRequest samplePopulationVarianceAmountAs(String retName){
        super.samplePopulationVariance(retName, StorageFee.AMOUNT_PROPERTY);
        return this;
    }
    public StorageFeeRequest<T> groupByWarehouseWithDetails(){
       return groupByWarehouseWithDetails(Q.warehouses().unlimited());
    }

    public StorageFeeRequest<T> groupByWarehouseWithDetails(WarehouseRequest subRequest){
       aggregate(StorageFee.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }

    public StorageFeeRequest<T> groupByContainerWithDetails(){
       return groupByContainerWithDetails(Q.storageContainers().unlimited());
    }

    public StorageFeeRequest<T> groupByContainerWithDetails(StorageContainerRequest subRequest){
       aggregate(StorageFee.CONTAINER_PROPERTY, subRequest);
       return this;
    }








    public StorageFeeRequest<T> groupById(){
       groupBy(StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByIdAs(String retName){
       groupBy(retName, StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.ID_PROPERTY, function);
       return this;
    }
    public StorageFeeRequest<T> groupByWarehouseWith(WarehouseRequest subRequest){
       groupBy(StorageFee.WAREHOUSE_PROPERTY, subRequest);
       return this;
    }
    public StorageFeeRequest<T> groupByWarehouse(){
       groupBy(StorageFee.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByWarehouseAs(String retName){
       groupBy(retName, StorageFee.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByWarehouseWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.WAREHOUSE_PROPERTY, function);
       return this;
    }
    public StorageFeeRequest<T> groupByContainerWith(StorageContainerRequest subRequest){
       groupBy(StorageFee.CONTAINER_PROPERTY, subRequest);
       return this;
    }
    public StorageFeeRequest<T> groupByContainer(){
       groupBy(StorageFee.CONTAINER_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByContainerAs(String retName){
       groupBy(retName, StorageFee.CONTAINER_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByContainerWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.CONTAINER_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByAmount(){
       groupBy(StorageFee.AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByAmountAs(String retName){
       groupBy(retName, StorageFee.AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.AMOUNT_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByCurrency(){
       groupBy(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.CURRENCY_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriod(){
       groupBy(StorageFee.PERIOD_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodAs(String retName){
       groupBy(retName, StorageFee.PERIOD_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByPeriodWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.PERIOD_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByCreateTime(){
       groupBy(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByUpdateTime(){
       groupBy(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public StorageFeeRequest<T> groupByVersion(){
       groupBy(StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByVersionAs(String retName){
       groupBy(retName, StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, StorageFee.VERSION_PROPERTY, function);
       return this;
    }



    public StorageFeeRequest<T> orderByIdAscending(){
       addOrderByAscending(StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByIdDescending(){
       addOrderByDescending(StorageFee.ID_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByWarehouseAscending(){
       addOrderByAscending(StorageFee.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByWarehouseDescending(){
       addOrderByDescending(StorageFee.WAREHOUSE_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByContainerAscending(){
       addOrderByAscending(StorageFee.CONTAINER_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByContainerDescending(){
       addOrderByDescending(StorageFee.CONTAINER_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByAmountAscending(){
       addOrderByAscending(StorageFee.AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByAmountDescending(){
       addOrderByDescending(StorageFee.AMOUNT_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(StorageFee.CURRENCY_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageFee.CURRENCY_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageFee.CURRENCY_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByPeriodAscending(){
       addOrderByAscending(StorageFee.PERIOD_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByPeriodDescending(){
       addOrderByDescending(StorageFee.PERIOD_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByPeriodAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StorageFee.PERIOD_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByPeriodDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StorageFee.PERIOD_PROPERTY);
       return this;
    }
    public StorageFeeRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(StorageFee.CREATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(StorageFee.UPDATE_TIME_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByVersionAscending(){
       addOrderByAscending(StorageFee.VERSION_PROPERTY);
       return this;
    }

    public StorageFeeRequest<T> orderByVersionDescending(){
       addOrderByDescending(StorageFee.VERSION_PROPERTY);
       return this;
    }


    public WarehouseRequest rollUpToWarehouse(){
       WarehouseRequest warehouse = Q.warehouses().unlimited();
       this.withWarehouseMatching(warehouse)
           .groupByWarehouseWith(warehouse);
       return warehouse;
    }

    public StorageContainerRequest rollUpToContainer(){
       StorageContainerRequest container = Q.storageContainers().unlimited();
       this.withContainerMatching(container)
           .groupByContainerWith(container);
       return container;
    }








   public StorageFeeRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse){
       return facetByWarehouseAs(facetName, warehouse, true);
   }

   public StorageFeeRequest<T> facetByWarehouseAs(String facetName, WarehouseRequest warehouse, boolean includeAllFacets){
       addFacet(facetName, StorageFee.WAREHOUSE_PROPERTY, warehouse, includeAllFacets);
       return this;
   }
   public StorageFeeRequest<T> facetByContainerAs(String facetName, StorageContainerRequest container){
       return facetByContainerAs(facetName, container, true);
   }

   public StorageFeeRequest<T> facetByContainerAs(String facetName, StorageContainerRequest container, boolean includeAllFacets){
       addFacet(facetName, StorageFee.CONTAINER_PROPERTY, container, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public StorageFeeRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public StorageFeeRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public StorageFeeRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public StorageFeeRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public StorageFeeRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}