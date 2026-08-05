package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

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

public class CustomsDeclarationRequest<T extends CustomsDeclaration> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public CustomsDeclarationRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public CustomsDeclarationRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public CustomsDeclarationRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public CustomsDeclarationRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public CustomsDeclarationRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public CustomsDeclarationRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public CustomsDeclarationRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (CustomsDeclarationRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public CustomsDeclarationRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public CustomsDeclarationRequest<T> matchingAnyOf(CustomsDeclarationRequest customsDeclaration){
        super.internalMatchAny(customsDeclaration);
        return this;
    }

    public CustomsDeclarationRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public CustomsDeclarationRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public CustomsDeclarationRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public CustomsDeclarationRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectDeclarationNumber().selectOriginCountry().selectDestinationCountry().selectTotalValue().selectStatus().selectMovingOrderIdOnly().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public CustomsDeclarationRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CustomsDeclarationRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectDeclarationNumber().selectOriginCountry().selectDestinationCountry().selectTotalValue().selectStatus().selectMovingOrder().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public CustomsDeclarationRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectDeclarationNumber().selectOriginCountry().selectDestinationCountry().selectTotalValue().selectStatus().selectMovingOrder().selectCreatedTime().selectUpdateTime().selectVersion();
    }


    public CustomsDeclarationRequest<T> selectId(){
       selectProperty(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectId(){
       unselectProperty(CustomsDeclaration.ID_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectDeclarationNumber(){
       selectProperty(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the declarationNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  declarationNumber) to fetch declarationNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectDeclarationNumber(){
       unselectProperty(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectOriginCountry(){
       selectProperty(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }

    /**
     * fill the originCountry with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  originCountry) to fetch originCountry property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectOriginCountry(){
       unselectProperty(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectDestinationCountry(){
       selectProperty(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }

    /**
     * fill the destinationCountry with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  destinationCountry) to fetch destinationCountry property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectDestinationCountry(){
       unselectProperty(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectTotalValue(){
       selectProperty(CustomsDeclaration.TOTAL_VALUE_PROPERTY);
       return this;
    }

    /**
     * fill the totalValue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalValue) to fetch totalValue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalValue with customized aggrFunction, TEAQL uses ({aggrFunction}(totalValue) AS totalValue to fetch totalValue property.
     * @param aggrFunction  aggrFunction
     */
    public CustomsDeclarationRequest<T> selectTotalValue(AggrFunction aggrFunction){
       selectProperty(CustomsDeclaration.TOTAL_VALUE_PROPERTY, aggrFunction);
       return this;
    }


    public CustomsDeclarationRequest<T> unselectTotalValue(){
       unselectProperty(CustomsDeclaration.TOTAL_VALUE_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectStatus(){
       selectProperty(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectStatus(){
       unselectProperty(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectMovingOrderIdOnly(){
       selectProperty(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public CustomsDeclarationRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       enhanceRelation(CustomsDeclaration.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public CustomsDeclarationRequest<T> unselectMovingOrder(){
       unselectProperty(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectCreatedTime(){
       selectProperty(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectCreatedTime(){
       unselectProperty(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectUpdateTime(){
       selectProperty(CustomsDeclaration.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectUpdateTime(){
       unselectProperty(CustomsDeclaration.UPDATE_TIME_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectVersion(){
       selectProperty(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectVersion(){
       unselectProperty(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.ID_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public CustomsDeclarationRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public CustomsDeclarationRequest<T> filterByDeclarationNumber(String... declarationNumber){
      if (declarationNumber == null || declarationNumber.length == 0) {
        throw new IllegalArgumentException("filterByDeclarationNumber parameter declarationNumber cannot be empty");
      }
      return appendSearchCriteria(createDeclarationNumberCriteria(Operator.EQUAL, (Object[])declarationNumber));
    }

    public CustomsDeclarationRequest<T> withDeclarationNumber(Operator operator, Object... values){
       return appendSearchCriteria(createDeclarationNumberCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberIsUnknown(){
       return withDeclarationNumber(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberIsKnown(){
       return withDeclarationNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeclarationNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberGreaterThan(String declarationNumber){
       return withDeclarationNumber(Operator.GREATER_THAN, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberGreaterThanOrEqualTo(String declarationNumber){
       return withDeclarationNumber(Operator.GREATER_THAN_OR_EQUAL, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberLessThan(String declarationNumber){
       return withDeclarationNumber(Operator.LESS_THAN, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberLessThanOrEqualTo(String declarationNumber){
       return withDeclarationNumber(Operator.LESS_THAN_OR_EQUAL, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberBetween(String startOfDeclarationNumber, String endOfDeclarationNumber){
       return withDeclarationNumber(Operator.BETWEEN, startOfDeclarationNumber, endOfDeclarationNumber);
    }
    public CustomsDeclarationRequest<T> withDeclarationNumberStartingWith(String declarationNumber){
       return withDeclarationNumber(Operator.BEGIN_WITH, declarationNumber);
    }
    public CustomsDeclarationRequest<T> withDeclarationNumberContaining(String declarationNumber){
       return withDeclarationNumber(Operator.CONTAIN, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberEndingWith(String declarationNumber){
       return withDeclarationNumber(Operator.END_WITH, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberIs(String declarationNumber){
       return withDeclarationNumber(Operator.EQUAL, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberSoundingLike(String declarationNumber){
       return withDeclarationNumber(Operator.SOUNDS_LIKE, declarationNumber);
    }



    public CustomsDeclarationRequest<T> filterByOriginCountry(String... originCountry){
      if (originCountry == null || originCountry.length == 0) {
        throw new IllegalArgumentException("filterByOriginCountry parameter originCountry cannot be empty");
      }
      return appendSearchCriteria(createOriginCountryCriteria(Operator.EQUAL, (Object[])originCountry));
    }

    public CustomsDeclarationRequest<T> withOriginCountry(Operator operator, Object... values){
       return appendSearchCriteria(createOriginCountryCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withOriginCountryIsUnknown(){
       return withOriginCountry(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withOriginCountryIsKnown(){
       return withOriginCountry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createOriginCountryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withOriginCountryGreaterThan(String originCountry){
       return withOriginCountry(Operator.GREATER_THAN, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountryGreaterThanOrEqualTo(String originCountry){
       return withOriginCountry(Operator.GREATER_THAN_OR_EQUAL, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountryLessThan(String originCountry){
       return withOriginCountry(Operator.LESS_THAN, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountryLessThanOrEqualTo(String originCountry){
       return withOriginCountry(Operator.LESS_THAN_OR_EQUAL, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountryBetween(String startOfOriginCountry, String endOfOriginCountry){
       return withOriginCountry(Operator.BETWEEN, startOfOriginCountry, endOfOriginCountry);
    }
    public CustomsDeclarationRequest<T> withOriginCountryStartingWith(String originCountry){
       return withOriginCountry(Operator.BEGIN_WITH, originCountry);
    }
    public CustomsDeclarationRequest<T> withOriginCountryContaining(String originCountry){
       return withOriginCountry(Operator.CONTAIN, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountryEndingWith(String originCountry){
       return withOriginCountry(Operator.END_WITH, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountryIs(String originCountry){
       return withOriginCountry(Operator.EQUAL, originCountry);
    }

    public CustomsDeclarationRequest<T> withOriginCountrySoundingLike(String originCountry){
       return withOriginCountry(Operator.SOUNDS_LIKE, originCountry);
    }



    public CustomsDeclarationRequest<T> filterByDestinationCountry(String... destinationCountry){
      if (destinationCountry == null || destinationCountry.length == 0) {
        throw new IllegalArgumentException("filterByDestinationCountry parameter destinationCountry cannot be empty");
      }
      return appendSearchCriteria(createDestinationCountryCriteria(Operator.EQUAL, (Object[])destinationCountry));
    }

    public CustomsDeclarationRequest<T> withDestinationCountry(Operator operator, Object... values){
       return appendSearchCriteria(createDestinationCountryCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withDestinationCountryIsUnknown(){
       return withDestinationCountry(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryIsKnown(){
       return withDestinationCountry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDestinationCountryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryGreaterThan(String destinationCountry){
       return withDestinationCountry(Operator.GREATER_THAN, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryGreaterThanOrEqualTo(String destinationCountry){
       return withDestinationCountry(Operator.GREATER_THAN_OR_EQUAL, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryLessThan(String destinationCountry){
       return withDestinationCountry(Operator.LESS_THAN, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryLessThanOrEqualTo(String destinationCountry){
       return withDestinationCountry(Operator.LESS_THAN_OR_EQUAL, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryBetween(String startOfDestinationCountry, String endOfDestinationCountry){
       return withDestinationCountry(Operator.BETWEEN, startOfDestinationCountry, endOfDestinationCountry);
    }
    public CustomsDeclarationRequest<T> withDestinationCountryStartingWith(String destinationCountry){
       return withDestinationCountry(Operator.BEGIN_WITH, destinationCountry);
    }
    public CustomsDeclarationRequest<T> withDestinationCountryContaining(String destinationCountry){
       return withDestinationCountry(Operator.CONTAIN, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryEndingWith(String destinationCountry){
       return withDestinationCountry(Operator.END_WITH, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountryIs(String destinationCountry){
       return withDestinationCountry(Operator.EQUAL, destinationCountry);
    }

    public CustomsDeclarationRequest<T> withDestinationCountrySoundingLike(String destinationCountry){
       return withDestinationCountry(Operator.SOUNDS_LIKE, destinationCountry);
    }



    public CustomsDeclarationRequest<T> filterByTotalValue(BigDecimal... totalValue){
      if (totalValue == null || totalValue.length == 0) {
        throw new IllegalArgumentException("filterByTotalValue parameter totalValue cannot be empty");
      }
      return appendSearchCriteria(createTotalValueCriteria(Operator.EQUAL, (Object[])totalValue));
    }

    public CustomsDeclarationRequest<T> withTotalValue(Operator operator, Object... values){
       return appendSearchCriteria(createTotalValueCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withTotalValueIsUnknown(){
       return withTotalValue(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withTotalValueIsKnown(){
       return withTotalValue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalValueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.TOTAL_VALUE_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withTotalValueGreaterThan(BigDecimal totalValue){
       return withTotalValue(Operator.GREATER_THAN, totalValue);
    }

    public CustomsDeclarationRequest<T> withTotalValueGreaterThanOrEqualTo(BigDecimal totalValue){
       return withTotalValue(Operator.GREATER_THAN_OR_EQUAL, totalValue);
    }

    public CustomsDeclarationRequest<T> withTotalValueLessThan(BigDecimal totalValue){
       return withTotalValue(Operator.LESS_THAN, totalValue);
    }

    public CustomsDeclarationRequest<T> withTotalValueLessThanOrEqualTo(BigDecimal totalValue){
       return withTotalValue(Operator.LESS_THAN_OR_EQUAL, totalValue);
    }

    public CustomsDeclarationRequest<T> withTotalValueBetween(BigDecimal startOfTotalValue, BigDecimal endOfTotalValue){
       return withTotalValue(Operator.BETWEEN, startOfTotalValue, endOfTotalValue);
    }



    public CustomsDeclarationRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public CustomsDeclarationRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.STATUS_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public CustomsDeclarationRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public CustomsDeclarationRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public CustomsDeclarationRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public CustomsDeclarationRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public CustomsDeclarationRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public CustomsDeclarationRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public CustomsDeclarationRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public CustomsDeclarationRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public CustomsDeclarationRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public CustomsDeclarationRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public CustomsDeclarationRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.MOVING_ORDER_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public CustomsDeclarationRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(CustomsDeclaration.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public CustomsDeclarationRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public CustomsDeclarationRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.CREATED_TIME_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public CustomsDeclarationRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public CustomsDeclarationRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public CustomsDeclarationRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.UPDATE_TIME_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public CustomsDeclarationRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public CustomsDeclarationRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public CustomsDeclarationRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public CustomsDeclarationRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.VERSION_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public CustomsDeclarationRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public CustomsDeclarationRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public CustomsDeclarationRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public CustomsDeclarationRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public CustomsDeclarationRequest<T> count(){
        super.count();
        return this;
    }
    public CustomsDeclarationRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public CustomsDeclarationRequest minTotalValue(){
        return minTotalValueAs(prefix("minOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest minTotalValueAs(String retName){
        super.min(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest maxTotalValue(){
        return maxTotalValueAs(prefix("maxOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest maxTotalValueAs(String retName){
        super.max(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest sumTotalValue(){
        return sumTotalValueAs(prefix("sumOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest sumTotalValueAs(String retName){
        super.sum(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest avgTotalValue(){
        return avgTotalValueAs(prefix("avgOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest avgTotalValueAs(String retName){
        super.avg(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest standardDeviationTotalValue(){
        return standardDeviationTotalValueAs(prefix("standardDeviationOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest standardDeviationTotalValueAs(String retName){
        super.standardDeviation(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest squareRootOfPopulationStandardDeviationTotalValue(){
        return squareRootOfPopulationStandardDeviationTotalValueAs(prefix("squareRootOfPopulationStandardDeviationOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest squareRootOfPopulationStandardDeviationTotalValueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest sampleVarianceTotalValue(){
        return sampleVarianceTotalValueAs(prefix("sampleVarianceOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest sampleVarianceTotalValueAs(String retName){
        super.sampleVariance(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest samplePopulationVarianceTotalValue(){
        return samplePopulationVarianceTotalValueAs(prefix("samplePopulationVarianceOf",CustomsDeclaration.TOTAL_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest samplePopulationVarianceTotalValueAs(String retName){
        super.samplePopulationVariance(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public CustomsDeclarationRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(CustomsDeclaration.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }





    public CustomsDeclarationRequest<T> groupById(){
       groupBy(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByIdAs(String retName){
       groupBy(retName, CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.ID_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclarationNumber(){
       groupBy(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclarationNumberAs(String retName){
       groupBy(retName, CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclarationNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.DECLARATION_NUMBER_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByOriginCountry(){
       groupBy(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByOriginCountryAs(String retName){
       groupBy(retName, CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByOriginCountryWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDestinationCountry(){
       groupBy(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDestinationCountryAs(String retName){
       groupBy(retName, CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDestinationCountryWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByTotalValue(){
       groupBy(CustomsDeclaration.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByTotalValueAs(String retName){
       groupBy(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByTotalValueWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.TOTAL_VALUE_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByStatus(){
       groupBy(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByStatusAs(String retName){
       groupBy(retName, CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.STATUS_PROPERTY, function);
       return this;
    }
    public CustomsDeclarationRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(CustomsDeclaration.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public CustomsDeclarationRequest<T> groupByMovingOrder(){
       groupBy(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, CustomsDeclaration.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.MOVING_ORDER_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCreatedTime(){
       groupBy(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByUpdateTime(){
       groupBy(CustomsDeclaration.UPDATE_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, CustomsDeclaration.UPDATE_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByVersion(){
       groupBy(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByVersionAs(String retName){
       groupBy(retName, CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.VERSION_PROPERTY, function);
       return this;
    }



    public CustomsDeclarationRequest<T> orderByIdAscending(){
       addOrderByAscending(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByIdDescending(){
       addOrderByDescending(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclarationNumberAscending(){
       addOrderByAscending(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclarationNumberDescending(){
       addOrderByDescending(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByDeclarationNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclarationNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByOriginCountryAscending(){
       addOrderByAscending(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByOriginCountryDescending(){
       addOrderByDescending(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByOriginCountryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByOriginCountryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByDestinationCountryAscending(){
       addOrderByAscending(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDestinationCountryDescending(){
       addOrderByDescending(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByDestinationCountryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDestinationCountryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByTotalValueAscending(){
       addOrderByAscending(CustomsDeclaration.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByTotalValueDescending(){
       addOrderByDescending(CustomsDeclaration.TOTAL_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByStatusAscending(){
       addOrderByAscending(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByStatusDescending(){
       addOrderByDescending(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(CustomsDeclaration.UPDATE_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(CustomsDeclaration.UPDATE_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByVersionAscending(){
       addOrderByAscending(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByVersionDescending(){
       addOrderByDescending(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }


    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }





   public CustomsDeclarationRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public CustomsDeclarationRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, CustomsDeclaration.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public CustomsDeclarationRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public CustomsDeclarationRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public CustomsDeclarationRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public CustomsDeclarationRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public CustomsDeclarationRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}