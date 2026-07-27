package com.doublechaintech.enterpriselogisticsservice.cargoitem;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
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

public class CargoItemRequest<T extends CargoItem> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public CargoItemRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public CargoItemRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public CargoItemRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public CargoItemRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public CargoItemRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public CargoItemRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public CargoItemRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (CargoItemRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public CargoItemRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public CargoItemRequest<T> matchingAnyOf(CargoItemRequest cargoItem){
        super.internalMatchAny(cargoItem);
        return this;
    }

    public CargoItemRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public CargoItemRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public CargoItemRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public CargoItemRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectItemCode().selectDescription().selectWeightKg().selectVolumeM3().selectFragile().selectMovingOrderIdOnly().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public CargoItemRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CargoItemRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectItemCode().selectDescription().selectWeightKg().selectVolumeM3().selectFragile().selectMovingOrder().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public CargoItemRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectItemCode().selectDescription().selectWeightKg().selectVolumeM3().selectFragile().selectMovingOrder().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public CargoItemRequest<T> selectId(){
       selectProperty(CargoItem.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectId(){
       unselectProperty(CargoItem.ID_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectItemCode(){
       selectProperty(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the itemCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  itemCode) to fetch itemCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectItemCode(){
       unselectProperty(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectDescription(){
       selectProperty(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectDescription(){
       unselectProperty(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectWeightKg(){
       selectProperty(CargoItem.WEIGHT_KG_PROPERTY);
       return this;
    }

    /**
     * fill the weightKg with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  weightKg) to fetch weightKg property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the weightKg with customized aggrFunction, TEAQL uses ({aggrFunction}(weightKg) AS weightKg to fetch weightKg property.
     * @param aggrFunction  aggrFunction
     */
    public CargoItemRequest<T> selectWeightKg(AggrFunction aggrFunction){
       selectProperty(CargoItem.WEIGHT_KG_PROPERTY, aggrFunction);
       return this;
    }


    public CargoItemRequest<T> unselectWeightKg(){
       unselectProperty(CargoItem.WEIGHT_KG_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectVolumeM3(){
       selectProperty(CargoItem.VOLUME_M3_PROPERTY);
       return this;
    }

    /**
     * fill the volumeM3 with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  volumeM3) to fetch volumeM3 property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the volumeM3 with customized aggrFunction, TEAQL uses ({aggrFunction}(volumeM3) AS volumeM3 to fetch volumeM3 property.
     * @param aggrFunction  aggrFunction
     */
    public CargoItemRequest<T> selectVolumeM3(AggrFunction aggrFunction){
       selectProperty(CargoItem.VOLUME_M3_PROPERTY, aggrFunction);
       return this;
    }


    public CargoItemRequest<T> unselectVolumeM3(){
       unselectProperty(CargoItem.VOLUME_M3_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectFragile(){
       selectProperty(CargoItem.FRAGILE_PROPERTY);
       return this;
    }

    /**
     * fill the fragile with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  fragile) to fetch fragile property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectFragile(){
       unselectProperty(CargoItem.FRAGILE_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectMovingOrderIdOnly(){
       selectProperty(CargoItem.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public CargoItemRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(CargoItem.MOVING_ORDER_PROPERTY);
       enhanceRelation(CargoItem.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public CargoItemRequest<T> unselectMovingOrder(){
       unselectProperty(CargoItem.MOVING_ORDER_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectCreatedTime(){
       selectProperty(CargoItem.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectCreatedTime(){
       unselectProperty(CargoItem.CREATED_TIME_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectUpdatedTime(){
       selectProperty(CargoItem.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectUpdatedTime(){
       unselectProperty(CargoItem.UPDATED_TIME_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> selectVersion(){
       selectProperty(CargoItem.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CargoItemRequest<T> unselectVersion(){
       unselectProperty(CargoItem.VERSION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.ID_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public CargoItemRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public CargoItemRequest<T> filterByItemCode(String... itemCode){
      if (itemCode == null || itemCode.length == 0) {
        throw new IllegalArgumentException("filterByItemCode parameter itemCode cannot be empty");
      }
      return appendSearchCriteria(createItemCodeCriteria(Operator.EQUAL, (Object[])itemCode));
    }

    public CargoItemRequest<T> withItemCode(Operator operator, Object... values){
       return appendSearchCriteria(createItemCodeCriteria(operator, values));
    }

    public CargoItemRequest<T> withItemCodeIsUnknown(){
       return withItemCode(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withItemCodeIsKnown(){
       return withItemCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createItemCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.ITEM_CODE_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withItemCodeGreaterThan(String itemCode){
       return withItemCode(Operator.GREATER_THAN, itemCode);
    }

    public CargoItemRequest<T> withItemCodeGreaterThanOrEqualTo(String itemCode){
       return withItemCode(Operator.GREATER_THAN_OR_EQUAL, itemCode);
    }

    public CargoItemRequest<T> withItemCodeLessThan(String itemCode){
       return withItemCode(Operator.LESS_THAN, itemCode);
    }

    public CargoItemRequest<T> withItemCodeLessThanOrEqualTo(String itemCode){
       return withItemCode(Operator.LESS_THAN_OR_EQUAL, itemCode);
    }

    public CargoItemRequest<T> withItemCodeBetween(String startOfItemCode, String endOfItemCode){
       return withItemCode(Operator.BETWEEN, startOfItemCode, endOfItemCode);
    }
    public CargoItemRequest<T> withItemCodeStartingWith(String itemCode){
       return withItemCode(Operator.BEGIN_WITH, itemCode);
    }
    public CargoItemRequest<T> withItemCodeContaining(String itemCode){
       return withItemCode(Operator.CONTAIN, itemCode);
    }

    public CargoItemRequest<T> withItemCodeEndingWith(String itemCode){
       return withItemCode(Operator.END_WITH, itemCode);
    }

    public CargoItemRequest<T> withItemCodeIs(String itemCode){
       return withItemCode(Operator.EQUAL, itemCode);
    }

    public CargoItemRequest<T> withItemCodeSoundingLike(String itemCode){
       return withItemCode(Operator.SOUNDS_LIKE, itemCode);
    }



    public CargoItemRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public CargoItemRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public CargoItemRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.DESCRIPTION_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public CargoItemRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public CargoItemRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public CargoItemRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public CargoItemRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public CargoItemRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public CargoItemRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public CargoItemRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public CargoItemRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public CargoItemRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public CargoItemRequest<T> filterByWeightKg(BigDecimal... weightKg){
      if (weightKg == null || weightKg.length == 0) {
        throw new IllegalArgumentException("filterByWeightKg parameter weightKg cannot be empty");
      }
      return appendSearchCriteria(createWeightKgCriteria(Operator.EQUAL, (Object[])weightKg));
    }

    public CargoItemRequest<T> withWeightKg(Operator operator, Object... values){
       return appendSearchCriteria(createWeightKgCriteria(operator, values));
    }

    public CargoItemRequest<T> withWeightKgIsUnknown(){
       return withWeightKg(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withWeightKgIsKnown(){
       return withWeightKg(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createWeightKgCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.WEIGHT_KG_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withWeightKgGreaterThan(BigDecimal weightKg){
       return withWeightKg(Operator.GREATER_THAN, weightKg);
    }

    public CargoItemRequest<T> withWeightKgGreaterThanOrEqualTo(BigDecimal weightKg){
       return withWeightKg(Operator.GREATER_THAN_OR_EQUAL, weightKg);
    }

    public CargoItemRequest<T> withWeightKgLessThan(BigDecimal weightKg){
       return withWeightKg(Operator.LESS_THAN, weightKg);
    }

    public CargoItemRequest<T> withWeightKgLessThanOrEqualTo(BigDecimal weightKg){
       return withWeightKg(Operator.LESS_THAN_OR_EQUAL, weightKg);
    }

    public CargoItemRequest<T> withWeightKgBetween(BigDecimal startOfWeightKg, BigDecimal endOfWeightKg){
       return withWeightKg(Operator.BETWEEN, startOfWeightKg, endOfWeightKg);
    }



    public CargoItemRequest<T> filterByVolumeM3(BigDecimal... volumeM3){
      if (volumeM3 == null || volumeM3.length == 0) {
        throw new IllegalArgumentException("filterByVolumeM3 parameter volumeM3 cannot be empty");
      }
      return appendSearchCriteria(createVolumeM3Criteria(Operator.EQUAL, (Object[])volumeM3));
    }

    public CargoItemRequest<T> withVolumeM3(Operator operator, Object... values){
       return appendSearchCriteria(createVolumeM3Criteria(operator, values));
    }

    public CargoItemRequest<T> withVolumeM3IsUnknown(){
       return withVolumeM3(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withVolumeM3IsKnown(){
       return withVolumeM3(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVolumeM3Criteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.VOLUME_M3_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withVolumeM3GreaterThan(BigDecimal volumeM3){
       return withVolumeM3(Operator.GREATER_THAN, volumeM3);
    }

    public CargoItemRequest<T> withVolumeM3GreaterThanOrEqualTo(BigDecimal volumeM3){
       return withVolumeM3(Operator.GREATER_THAN_OR_EQUAL, volumeM3);
    }

    public CargoItemRequest<T> withVolumeM3LessThan(BigDecimal volumeM3){
       return withVolumeM3(Operator.LESS_THAN, volumeM3);
    }

    public CargoItemRequest<T> withVolumeM3LessThanOrEqualTo(BigDecimal volumeM3){
       return withVolumeM3(Operator.LESS_THAN_OR_EQUAL, volumeM3);
    }

    public CargoItemRequest<T> withVolumeM3Between(BigDecimal startOfVolumeM3, BigDecimal endOfVolumeM3){
       return withVolumeM3(Operator.BETWEEN, startOfVolumeM3, endOfVolumeM3);
    }



    public CargoItemRequest<T> filterByFragile(Boolean... fragile){
      if (fragile == null || fragile.length == 0) {
        throw new IllegalArgumentException("filterByFragile parameter fragile cannot be empty");
      }
      return appendSearchCriteria(createFragileCriteria(Operator.EQUAL, (Object[])fragile));
    }

    public CargoItemRequest<T> withFragile(Operator operator, Object... values){
       return appendSearchCriteria(createFragileCriteria(operator, values));
    }

    public CargoItemRequest<T> withFragileIsUnknown(){
       return withFragile(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withFragileIsKnown(){
       return withFragile(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createFragileCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.FRAGILE_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> whichIsFragile(){
       return withFragile(Operator.EQUAL, true);
    }

    public CargoItemRequest<T> whichIsNotFragile(){
       return withFragile(Operator.EQUAL, false);
    }


    public CargoItemRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public CargoItemRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public CargoItemRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.MOVING_ORDER_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public CargoItemRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(CargoItem.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public CargoItemRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public CargoItemRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public CargoItemRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.CREATED_TIME_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public CargoItemRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CargoItemRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public CargoItemRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public CargoItemRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public CargoItemRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.UPDATED_TIME_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public CargoItemRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public CargoItemRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public CargoItemRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public CargoItemRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public CargoItemRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public CargoItemRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CargoItem.VERSION_PROPERTY, operator, values);
    }

    public CargoItemRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public CargoItemRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public CargoItemRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public CargoItemRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public CargoItemRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public CargoItemRequest<T> count(){
        super.count();
        return this;
    }
    public CargoItemRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public CargoItemRequest minWeightKg(){
        return minWeightKgAs(prefix("minOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest minWeightKgAs(String retName){
        super.min(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest maxWeightKg(){
        return maxWeightKgAs(prefix("maxOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest maxWeightKgAs(String retName){
        super.max(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest sumWeightKg(){
        return sumWeightKgAs(prefix("sumOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest sumWeightKgAs(String retName){
        super.sum(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest avgWeightKg(){
        return avgWeightKgAs(prefix("avgOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest avgWeightKgAs(String retName){
        super.avg(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest standardDeviationWeightKg(){
        return standardDeviationWeightKgAs(prefix("standardDeviationOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest standardDeviationWeightKgAs(String retName){
        super.standardDeviation(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest squareRootOfPopulationStandardDeviationWeightKg(){
        return squareRootOfPopulationStandardDeviationWeightKgAs(prefix("squareRootOfPopulationStandardDeviationOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest squareRootOfPopulationStandardDeviationWeightKgAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest sampleVarianceWeightKg(){
        return sampleVarianceWeightKgAs(prefix("sampleVarianceOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest sampleVarianceWeightKgAs(String retName){
        super.sampleVariance(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest samplePopulationVarianceWeightKg(){
        return samplePopulationVarianceWeightKgAs(prefix("samplePopulationVarianceOf",CargoItem.WEIGHT_KG_PROPERTY));
    }

    public CargoItemRequest samplePopulationVarianceWeightKgAs(String retName){
        super.samplePopulationVariance(retName, CargoItem.WEIGHT_KG_PROPERTY);
        return this;
    }
    public CargoItemRequest minVolumeM3(){
        return minVolumeM3As(prefix("minOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest minVolumeM3As(String retName){
        super.min(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest maxVolumeM3(){
        return maxVolumeM3As(prefix("maxOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest maxVolumeM3As(String retName){
        super.max(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest sumVolumeM3(){
        return sumVolumeM3As(prefix("sumOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest sumVolumeM3As(String retName){
        super.sum(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest avgVolumeM3(){
        return avgVolumeM3As(prefix("avgOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest avgVolumeM3As(String retName){
        super.avg(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest standardDeviationVolumeM3(){
        return standardDeviationVolumeM3As(prefix("standardDeviationOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest standardDeviationVolumeM3As(String retName){
        super.standardDeviation(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest squareRootOfPopulationStandardDeviationVolumeM3(){
        return squareRootOfPopulationStandardDeviationVolumeM3As(prefix("squareRootOfPopulationStandardDeviationOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest squareRootOfPopulationStandardDeviationVolumeM3As(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest sampleVarianceVolumeM3(){
        return sampleVarianceVolumeM3As(prefix("sampleVarianceOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest sampleVarianceVolumeM3As(String retName){
        super.sampleVariance(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest samplePopulationVarianceVolumeM3(){
        return samplePopulationVarianceVolumeM3As(prefix("samplePopulationVarianceOf",CargoItem.VOLUME_M3_PROPERTY));
    }

    public CargoItemRequest samplePopulationVarianceVolumeM3As(String retName){
        super.samplePopulationVariance(retName, CargoItem.VOLUME_M3_PROPERTY);
        return this;
    }
    public CargoItemRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public CargoItemRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(CargoItem.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }





    public CargoItemRequest<T> groupById(){
       groupBy(CargoItem.ID_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByIdAs(String retName){
       groupBy(retName, CargoItem.ID_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.ID_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByItemCode(){
       groupBy(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByItemCodeAs(String retName){
       groupBy(retName, CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByItemCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.ITEM_CODE_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByDescription(){
       groupBy(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByWeightKg(){
       groupBy(CargoItem.WEIGHT_KG_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByWeightKgAs(String retName){
       groupBy(retName, CargoItem.WEIGHT_KG_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByWeightKgWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.WEIGHT_KG_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByVolumeM3(){
       groupBy(CargoItem.VOLUME_M3_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByVolumeM3As(String retName){
       groupBy(retName, CargoItem.VOLUME_M3_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByVolumeM3WithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.VOLUME_M3_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByFragile(){
       groupBy(CargoItem.FRAGILE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByFragileAs(String retName){
       groupBy(retName, CargoItem.FRAGILE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByFragileWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.FRAGILE_PROPERTY, function);
       return this;
    }
    public CargoItemRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(CargoItem.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public CargoItemRequest<T> groupByMovingOrder(){
       groupBy(CargoItem.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, CargoItem.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.MOVING_ORDER_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByCreatedTime(){
       groupBy(CargoItem.CREATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, CargoItem.CREATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByUpdatedTime(){
       groupBy(CargoItem.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, CargoItem.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public CargoItemRequest<T> groupByVersion(){
       groupBy(CargoItem.VERSION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByVersionAs(String retName){
       groupBy(retName, CargoItem.VERSION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CargoItem.VERSION_PROPERTY, function);
       return this;
    }



    public CargoItemRequest<T> orderByIdAscending(){
       addOrderByAscending(CargoItem.ID_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByIdDescending(){
       addOrderByDescending(CargoItem.ID_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByItemCodeAscending(){
       addOrderByAscending(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByItemCodeDescending(){
       addOrderByDescending(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> orderByItemCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByItemCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CargoItem.ITEM_CODE_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CargoItem.DESCRIPTION_PROPERTY);
       return this;
    }
    public CargoItemRequest<T> orderByWeightKgAscending(){
       addOrderByAscending(CargoItem.WEIGHT_KG_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByWeightKgDescending(){
       addOrderByDescending(CargoItem.WEIGHT_KG_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByVolumeM3Ascending(){
       addOrderByAscending(CargoItem.VOLUME_M3_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByVolumeM3Descending(){
       addOrderByDescending(CargoItem.VOLUME_M3_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByFragileAscending(){
       addOrderByAscending(CargoItem.FRAGILE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByFragileDescending(){
       addOrderByDescending(CargoItem.FRAGILE_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(CargoItem.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(CargoItem.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(CargoItem.CREATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(CargoItem.CREATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(CargoItem.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(CargoItem.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByVersionAscending(){
       addOrderByAscending(CargoItem.VERSION_PROPERTY);
       return this;
    }

    public CargoItemRequest<T> orderByVersionDescending(){
       addOrderByDescending(CargoItem.VERSION_PROPERTY);
       return this;
    }


    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }





   public CargoItemRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public CargoItemRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, CargoItem.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public CargoItemRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public CargoItemRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public CargoItemRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public CargoItemRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public CargoItemRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}