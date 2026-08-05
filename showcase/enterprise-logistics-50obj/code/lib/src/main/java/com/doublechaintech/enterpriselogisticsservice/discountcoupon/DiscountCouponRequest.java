package com.doublechaintech.enterpriselogisticsservice.discountcoupon;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DiscountCouponRequest<T extends DiscountCoupon> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public DiscountCouponRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public DiscountCouponRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public DiscountCouponRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public DiscountCouponRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public DiscountCouponRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public DiscountCouponRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public DiscountCouponRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (DiscountCouponRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public DiscountCouponRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public DiscountCouponRequest<T> matchingAnyOf(DiscountCouponRequest discountCoupon){
        super.internalMatchAny(discountCoupon);
        return this;
    }

    public DiscountCouponRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public DiscountCouponRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public DiscountCouponRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public DiscountCouponRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectCode().selectDescription().selectDiscountPercentage().selectMinOrderAmount().selectMaxDiscountAmount().selectUsageLimit().selectUsedCount().selectStartDate().selectEndDate().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public DiscountCouponRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public DiscountCouponRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectCode().selectDescription().selectDiscountPercentage().selectMinOrderAmount().selectMaxDiscountAmount().selectUsageLimit().selectUsedCount().selectStartDate().selectEndDate().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public DiscountCouponRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectCode().selectDescription().selectDiscountPercentage().selectMinOrderAmount().selectMaxDiscountAmount().selectUsageLimit().selectUsedCount().selectStartDate().selectEndDate().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public DiscountCouponRequest<T> selectId(){
       selectProperty(DiscountCoupon.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectId(){
       unselectProperty(DiscountCoupon.ID_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectCode(){
       selectProperty(DiscountCoupon.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectCode(){
       unselectProperty(DiscountCoupon.CODE_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectDescription(){
       selectProperty(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectDescription(){
       unselectProperty(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectDiscountPercentage(){
       selectProperty(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    /**
     * fill the discountPercentage with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  discountPercentage) to fetch discountPercentage property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the discountPercentage with customized aggrFunction, TEAQL uses ({aggrFunction}(discountPercentage) AS discountPercentage to fetch discountPercentage property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectDiscountPercentage(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectDiscountPercentage(){
       unselectProperty(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectMinOrderAmount(){
       selectProperty(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the minOrderAmount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  minOrderAmount) to fetch minOrderAmount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the minOrderAmount with customized aggrFunction, TEAQL uses ({aggrFunction}(minOrderAmount) AS minOrderAmount to fetch minOrderAmount property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectMinOrderAmount(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectMinOrderAmount(){
       unselectProperty(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectMaxDiscountAmount(){
       selectProperty(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the maxDiscountAmount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  maxDiscountAmount) to fetch maxDiscountAmount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the maxDiscountAmount with customized aggrFunction, TEAQL uses ({aggrFunction}(maxDiscountAmount) AS maxDiscountAmount to fetch maxDiscountAmount property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectMaxDiscountAmount(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectMaxDiscountAmount(){
       unselectProperty(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectUsageLimit(){
       selectProperty(DiscountCoupon.USAGE_LIMIT_PROPERTY);
       return this;
    }

    /**
     * fill the usageLimit with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  usageLimit) to fetch usageLimit property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the usageLimit with customized aggrFunction, TEAQL uses ({aggrFunction}(usageLimit) AS usageLimit to fetch usageLimit property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectUsageLimit(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.USAGE_LIMIT_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectUsageLimit(){
       unselectProperty(DiscountCoupon.USAGE_LIMIT_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectUsedCount(){
       selectProperty(DiscountCoupon.USED_COUNT_PROPERTY);
       return this;
    }

    /**
     * fill the usedCount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  usedCount) to fetch usedCount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the usedCount with customized aggrFunction, TEAQL uses ({aggrFunction}(usedCount) AS usedCount to fetch usedCount property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectUsedCount(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.USED_COUNT_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectUsedCount(){
       unselectProperty(DiscountCoupon.USED_COUNT_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectStartDate(){
       selectProperty(DiscountCoupon.START_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the startDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startDate) to fetch startDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectStartDate(){
       unselectProperty(DiscountCoupon.START_DATE_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectEndDate(){
       selectProperty(DiscountCoupon.END_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the endDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endDate) to fetch endDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectEndDate(){
       unselectProperty(DiscountCoupon.END_DATE_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectStatus(){
       selectProperty(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectStatus(){
       unselectProperty(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectCreatedTime(){
       selectProperty(DiscountCoupon.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectCreatedTime(){
       unselectProperty(DiscountCoupon.CREATED_TIME_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectUpdatedTime(){
       selectProperty(DiscountCoupon.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectUpdatedTime(){
       unselectProperty(DiscountCoupon.UPDATED_TIME_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectVersion(){
       selectProperty(DiscountCoupon.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectVersion(){
       unselectProperty(DiscountCoupon.VERSION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.ID_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public DiscountCouponRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public DiscountCouponRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public DiscountCouponRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.CODE_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public DiscountCouponRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public DiscountCouponRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public DiscountCouponRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public DiscountCouponRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public DiscountCouponRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public DiscountCouponRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public DiscountCouponRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public DiscountCouponRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public DiscountCouponRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public DiscountCouponRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public DiscountCouponRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.DESCRIPTION_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public DiscountCouponRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public DiscountCouponRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public DiscountCouponRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public DiscountCouponRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public DiscountCouponRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public DiscountCouponRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public DiscountCouponRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public DiscountCouponRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public DiscountCouponRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public DiscountCouponRequest<T> filterByDiscountPercentage(BigDecimal... discountPercentage){
      if (discountPercentage == null || discountPercentage.length == 0) {
        throw new IllegalArgumentException("filterByDiscountPercentage parameter discountPercentage cannot be empty");
      }
      return appendSearchCriteria(createDiscountPercentageCriteria(Operator.EQUAL, (Object[])discountPercentage));
    }

    public DiscountCouponRequest<T> withDiscountPercentage(Operator operator, Object... values){
       return appendSearchCriteria(createDiscountPercentageCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withDiscountPercentageIsUnknown(){
       return withDiscountPercentage(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withDiscountPercentageIsKnown(){
       return withDiscountPercentage(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDiscountPercentageCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withDiscountPercentageGreaterThan(BigDecimal discountPercentage){
       return withDiscountPercentage(Operator.GREATER_THAN, discountPercentage);
    }

    public DiscountCouponRequest<T> withDiscountPercentageGreaterThanOrEqualTo(BigDecimal discountPercentage){
       return withDiscountPercentage(Operator.GREATER_THAN_OR_EQUAL, discountPercentage);
    }

    public DiscountCouponRequest<T> withDiscountPercentageLessThan(BigDecimal discountPercentage){
       return withDiscountPercentage(Operator.LESS_THAN, discountPercentage);
    }

    public DiscountCouponRequest<T> withDiscountPercentageLessThanOrEqualTo(BigDecimal discountPercentage){
       return withDiscountPercentage(Operator.LESS_THAN_OR_EQUAL, discountPercentage);
    }

    public DiscountCouponRequest<T> withDiscountPercentageBetween(BigDecimal startOfDiscountPercentage, BigDecimal endOfDiscountPercentage){
       return withDiscountPercentage(Operator.BETWEEN, startOfDiscountPercentage, endOfDiscountPercentage);
    }



    public DiscountCouponRequest<T> filterByMinOrderAmount(BigDecimal... minOrderAmount){
      if (minOrderAmount == null || minOrderAmount.length == 0) {
        throw new IllegalArgumentException("filterByMinOrderAmount parameter minOrderAmount cannot be empty");
      }
      return appendSearchCriteria(createMinOrderAmountCriteria(Operator.EQUAL, (Object[])minOrderAmount));
    }

    public DiscountCouponRequest<T> withMinOrderAmount(Operator operator, Object... values){
       return appendSearchCriteria(createMinOrderAmountCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withMinOrderAmountIsUnknown(){
       return withMinOrderAmount(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withMinOrderAmountIsKnown(){
       return withMinOrderAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMinOrderAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withMinOrderAmountGreaterThan(BigDecimal minOrderAmount){
       return withMinOrderAmount(Operator.GREATER_THAN, minOrderAmount);
    }

    public DiscountCouponRequest<T> withMinOrderAmountGreaterThanOrEqualTo(BigDecimal minOrderAmount){
       return withMinOrderAmount(Operator.GREATER_THAN_OR_EQUAL, minOrderAmount);
    }

    public DiscountCouponRequest<T> withMinOrderAmountLessThan(BigDecimal minOrderAmount){
       return withMinOrderAmount(Operator.LESS_THAN, minOrderAmount);
    }

    public DiscountCouponRequest<T> withMinOrderAmountLessThanOrEqualTo(BigDecimal minOrderAmount){
       return withMinOrderAmount(Operator.LESS_THAN_OR_EQUAL, minOrderAmount);
    }

    public DiscountCouponRequest<T> withMinOrderAmountBetween(BigDecimal startOfMinOrderAmount, BigDecimal endOfMinOrderAmount){
       return withMinOrderAmount(Operator.BETWEEN, startOfMinOrderAmount, endOfMinOrderAmount);
    }



    public DiscountCouponRequest<T> filterByMaxDiscountAmount(BigDecimal... maxDiscountAmount){
      if (maxDiscountAmount == null || maxDiscountAmount.length == 0) {
        throw new IllegalArgumentException("filterByMaxDiscountAmount parameter maxDiscountAmount cannot be empty");
      }
      return appendSearchCriteria(createMaxDiscountAmountCriteria(Operator.EQUAL, (Object[])maxDiscountAmount));
    }

    public DiscountCouponRequest<T> withMaxDiscountAmount(Operator operator, Object... values){
       return appendSearchCriteria(createMaxDiscountAmountCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountIsUnknown(){
       return withMaxDiscountAmount(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountIsKnown(){
       return withMaxDiscountAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMaxDiscountAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountGreaterThan(BigDecimal maxDiscountAmount){
       return withMaxDiscountAmount(Operator.GREATER_THAN, maxDiscountAmount);
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountGreaterThanOrEqualTo(BigDecimal maxDiscountAmount){
       return withMaxDiscountAmount(Operator.GREATER_THAN_OR_EQUAL, maxDiscountAmount);
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountLessThan(BigDecimal maxDiscountAmount){
       return withMaxDiscountAmount(Operator.LESS_THAN, maxDiscountAmount);
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountLessThanOrEqualTo(BigDecimal maxDiscountAmount){
       return withMaxDiscountAmount(Operator.LESS_THAN_OR_EQUAL, maxDiscountAmount);
    }

    public DiscountCouponRequest<T> withMaxDiscountAmountBetween(BigDecimal startOfMaxDiscountAmount, BigDecimal endOfMaxDiscountAmount){
       return withMaxDiscountAmount(Operator.BETWEEN, startOfMaxDiscountAmount, endOfMaxDiscountAmount);
    }



    public DiscountCouponRequest<T> filterByUsageLimit(Integer... usageLimit){
      if (usageLimit == null || usageLimit.length == 0) {
        throw new IllegalArgumentException("filterByUsageLimit parameter usageLimit cannot be empty");
      }
      return appendSearchCriteria(createUsageLimitCriteria(Operator.EQUAL, (Object[])usageLimit));
    }

    public DiscountCouponRequest<T> withUsageLimit(Operator operator, Object... values){
       return appendSearchCriteria(createUsageLimitCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withUsageLimitIsUnknown(){
       return withUsageLimit(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withUsageLimitIsKnown(){
       return withUsageLimit(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUsageLimitCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.USAGE_LIMIT_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withUsageLimitGreaterThan(Integer usageLimit){
       return withUsageLimit(Operator.GREATER_THAN, usageLimit);
    }

    public DiscountCouponRequest<T> withUsageLimitGreaterThanOrEqualTo(Integer usageLimit){
       return withUsageLimit(Operator.GREATER_THAN_OR_EQUAL, usageLimit);
    }

    public DiscountCouponRequest<T> withUsageLimitLessThan(Integer usageLimit){
       return withUsageLimit(Operator.LESS_THAN, usageLimit);
    }

    public DiscountCouponRequest<T> withUsageLimitLessThanOrEqualTo(Integer usageLimit){
       return withUsageLimit(Operator.LESS_THAN_OR_EQUAL, usageLimit);
    }

    public DiscountCouponRequest<T> withUsageLimitBetween(Integer startOfUsageLimit, Integer endOfUsageLimit){
       return withUsageLimit(Operator.BETWEEN, startOfUsageLimit, endOfUsageLimit);
    }



    public DiscountCouponRequest<T> filterByUsedCount(Integer... usedCount){
      if (usedCount == null || usedCount.length == 0) {
        throw new IllegalArgumentException("filterByUsedCount parameter usedCount cannot be empty");
      }
      return appendSearchCriteria(createUsedCountCriteria(Operator.EQUAL, (Object[])usedCount));
    }

    public DiscountCouponRequest<T> withUsedCount(Operator operator, Object... values){
       return appendSearchCriteria(createUsedCountCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withUsedCountIsUnknown(){
       return withUsedCount(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withUsedCountIsKnown(){
       return withUsedCount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUsedCountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.USED_COUNT_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withUsedCountGreaterThan(Integer usedCount){
       return withUsedCount(Operator.GREATER_THAN, usedCount);
    }

    public DiscountCouponRequest<T> withUsedCountGreaterThanOrEqualTo(Integer usedCount){
       return withUsedCount(Operator.GREATER_THAN_OR_EQUAL, usedCount);
    }

    public DiscountCouponRequest<T> withUsedCountLessThan(Integer usedCount){
       return withUsedCount(Operator.LESS_THAN, usedCount);
    }

    public DiscountCouponRequest<T> withUsedCountLessThanOrEqualTo(Integer usedCount){
       return withUsedCount(Operator.LESS_THAN_OR_EQUAL, usedCount);
    }

    public DiscountCouponRequest<T> withUsedCountBetween(Integer startOfUsedCount, Integer endOfUsedCount){
       return withUsedCount(Operator.BETWEEN, startOfUsedCount, endOfUsedCount);
    }



    public DiscountCouponRequest<T> filterByStartDate(LocalDate... startDate){
      if (startDate == null || startDate.length == 0) {
        throw new IllegalArgumentException("filterByStartDate parameter startDate cannot be empty");
      }
      return appendSearchCriteria(createStartDateCriteria(Operator.EQUAL, (Object[])startDate));
    }

    public DiscountCouponRequest<T> withStartDate(Operator operator, Object... values){
       return appendSearchCriteria(createStartDateCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withStartDateIsUnknown(){
       return withStartDate(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withStartDateIsKnown(){
       return withStartDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.START_DATE_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withStartDateGreaterThan(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public DiscountCouponRequest<T> withStartDateGreaterThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN_OR_EQUAL, startDate);
    }

    public DiscountCouponRequest<T> withStartDateLessThan(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public DiscountCouponRequest<T> withStartDateLessThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN_OR_EQUAL, startDate);
    }

    public DiscountCouponRequest<T> withStartDateBetween(LocalDate startOfStartDate, LocalDate endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }
    public DiscountCouponRequest<T> withStartDateBefore(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public DiscountCouponRequest<T> withStartDateBefore(Date startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public DiscountCouponRequest<T> withStartDateAfter(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public DiscountCouponRequest<T> withStartDateAfter(Date startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public DiscountCouponRequest<T> withStartDateBetween(Date startOfStartDate, Date endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }




    public DiscountCouponRequest<T> filterByEndDate(LocalDate... endDate){
      if (endDate == null || endDate.length == 0) {
        throw new IllegalArgumentException("filterByEndDate parameter endDate cannot be empty");
      }
      return appendSearchCriteria(createEndDateCriteria(Operator.EQUAL, (Object[])endDate));
    }

    public DiscountCouponRequest<T> withEndDate(Operator operator, Object... values){
       return appendSearchCriteria(createEndDateCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withEndDateIsUnknown(){
       return withEndDate(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withEndDateIsKnown(){
       return withEndDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.END_DATE_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withEndDateGreaterThan(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public DiscountCouponRequest<T> withEndDateGreaterThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN_OR_EQUAL, endDate);
    }

    public DiscountCouponRequest<T> withEndDateLessThan(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public DiscountCouponRequest<T> withEndDateLessThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN_OR_EQUAL, endDate);
    }

    public DiscountCouponRequest<T> withEndDateBetween(LocalDate startOfEndDate, LocalDate endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }
    public DiscountCouponRequest<T> withEndDateBefore(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public DiscountCouponRequest<T> withEndDateBefore(Date endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public DiscountCouponRequest<T> withEndDateAfter(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public DiscountCouponRequest<T> withEndDateAfter(Date endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public DiscountCouponRequest<T> withEndDateBetween(Date startOfEndDate, Date endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }




    public DiscountCouponRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public DiscountCouponRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.STATUS_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public DiscountCouponRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public DiscountCouponRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public DiscountCouponRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public DiscountCouponRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public DiscountCouponRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public DiscountCouponRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public DiscountCouponRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public DiscountCouponRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public DiscountCouponRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public DiscountCouponRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public DiscountCouponRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.CREATED_TIME_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public DiscountCouponRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public DiscountCouponRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public DiscountCouponRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public DiscountCouponRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.UPDATED_TIME_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public DiscountCouponRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public DiscountCouponRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public DiscountCouponRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public DiscountCouponRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.VERSION_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public DiscountCouponRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public DiscountCouponRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public DiscountCouponRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public DiscountCouponRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public DiscountCouponRequest<T> count(){
        super.count();
        return this;
    }
    public DiscountCouponRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public DiscountCouponRequest minDiscountPercentage(){
        return minDiscountPercentageAs(prefix("minOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest minDiscountPercentageAs(String retName){
        super.min(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxDiscountPercentage(){
        return maxDiscountPercentageAs(prefix("maxOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest maxDiscountPercentageAs(String retName){
        super.max(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumDiscountPercentage(){
        return sumDiscountPercentageAs(prefix("sumOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest sumDiscountPercentageAs(String retName){
        super.sum(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgDiscountPercentage(){
        return avgDiscountPercentageAs(prefix("avgOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest avgDiscountPercentageAs(String retName){
        super.avg(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationDiscountPercentage(){
        return standardDeviationDiscountPercentageAs(prefix("standardDeviationOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationDiscountPercentageAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationDiscountPercentage(){
        return squareRootOfPopulationStandardDeviationDiscountPercentageAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationDiscountPercentageAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceDiscountPercentage(){
        return sampleVarianceDiscountPercentageAs(prefix("sampleVarianceOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceDiscountPercentageAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceDiscountPercentage(){
        return samplePopulationVarianceDiscountPercentageAs(prefix("samplePopulationVarianceOf",DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceDiscountPercentageAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
        return this;
    }
    public DiscountCouponRequest minMinOrderAmount(){
        return minMinOrderAmountAs(prefix("minOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest minMinOrderAmountAs(String retName){
        super.min(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxMinOrderAmount(){
        return maxMinOrderAmountAs(prefix("maxOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest maxMinOrderAmountAs(String retName){
        super.max(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumMinOrderAmount(){
        return sumMinOrderAmountAs(prefix("sumOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest sumMinOrderAmountAs(String retName){
        super.sum(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgMinOrderAmount(){
        return avgMinOrderAmountAs(prefix("avgOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest avgMinOrderAmountAs(String retName){
        super.avg(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationMinOrderAmount(){
        return standardDeviationMinOrderAmountAs(prefix("standardDeviationOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationMinOrderAmountAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationMinOrderAmount(){
        return squareRootOfPopulationStandardDeviationMinOrderAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationMinOrderAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceMinOrderAmount(){
        return sampleVarianceMinOrderAmountAs(prefix("sampleVarianceOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceMinOrderAmountAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceMinOrderAmount(){
        return samplePopulationVarianceMinOrderAmountAs(prefix("samplePopulationVarianceOf",DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceMinOrderAmountAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest minMaxDiscountAmount(){
        return minMaxDiscountAmountAs(prefix("minOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest minMaxDiscountAmountAs(String retName){
        super.min(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxMaxDiscountAmount(){
        return maxMaxDiscountAmountAs(prefix("maxOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest maxMaxDiscountAmountAs(String retName){
        super.max(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumMaxDiscountAmount(){
        return sumMaxDiscountAmountAs(prefix("sumOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest sumMaxDiscountAmountAs(String retName){
        super.sum(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgMaxDiscountAmount(){
        return avgMaxDiscountAmountAs(prefix("avgOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest avgMaxDiscountAmountAs(String retName){
        super.avg(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationMaxDiscountAmount(){
        return standardDeviationMaxDiscountAmountAs(prefix("standardDeviationOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationMaxDiscountAmountAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationMaxDiscountAmount(){
        return squareRootOfPopulationStandardDeviationMaxDiscountAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationMaxDiscountAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceMaxDiscountAmount(){
        return sampleVarianceMaxDiscountAmountAs(prefix("sampleVarianceOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceMaxDiscountAmountAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceMaxDiscountAmount(){
        return samplePopulationVarianceMaxDiscountAmountAs(prefix("samplePopulationVarianceOf",DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceMaxDiscountAmountAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest minUsageLimit(){
        return minUsageLimitAs(prefix("minOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest minUsageLimitAs(String retName){
        super.min(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxUsageLimit(){
        return maxUsageLimitAs(prefix("maxOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest maxUsageLimitAs(String retName){
        super.max(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumUsageLimit(){
        return sumUsageLimitAs(prefix("sumOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest sumUsageLimitAs(String retName){
        super.sum(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgUsageLimit(){
        return avgUsageLimitAs(prefix("avgOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest avgUsageLimitAs(String retName){
        super.avg(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationUsageLimit(){
        return standardDeviationUsageLimitAs(prefix("standardDeviationOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationUsageLimitAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationUsageLimit(){
        return squareRootOfPopulationStandardDeviationUsageLimitAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationUsageLimitAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceUsageLimit(){
        return sampleVarianceUsageLimitAs(prefix("sampleVarianceOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceUsageLimitAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceUsageLimit(){
        return samplePopulationVarianceUsageLimitAs(prefix("samplePopulationVarianceOf",DiscountCoupon.USAGE_LIMIT_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceUsageLimitAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest minUsedCount(){
        return minUsedCountAs(prefix("minOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest minUsedCountAs(String retName){
        super.min(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxUsedCount(){
        return maxUsedCountAs(prefix("maxOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest maxUsedCountAs(String retName){
        super.max(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumUsedCount(){
        return sumUsedCountAs(prefix("sumOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest sumUsedCountAs(String retName){
        super.sum(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgUsedCount(){
        return avgUsedCountAs(prefix("avgOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest avgUsedCountAs(String retName){
        super.avg(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationUsedCount(){
        return standardDeviationUsedCountAs(prefix("standardDeviationOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationUsedCountAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationUsedCount(){
        return squareRootOfPopulationStandardDeviationUsedCountAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationUsedCountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceUsedCount(){
        return sampleVarianceUsedCountAs(prefix("sampleVarianceOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceUsedCountAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceUsedCount(){
        return samplePopulationVarianceUsedCountAs(prefix("samplePopulationVarianceOf",DiscountCoupon.USED_COUNT_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceUsedCountAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.USED_COUNT_PROPERTY);
        return this;
    }

    public DiscountCouponRequest<T> groupById(){
       groupBy(DiscountCoupon.ID_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByIdAs(String retName){
       groupBy(retName, DiscountCoupon.ID_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.ID_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByCode(){
       groupBy(DiscountCoupon.CODE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByCodeAs(String retName){
       groupBy(retName, DiscountCoupon.CODE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.CODE_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByDescription(){
       groupBy(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByDiscountPercentage(){
       groupBy(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByDiscountPercentageAs(String retName){
       groupBy(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByDiscountPercentageWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByMinOrderAmount(){
       groupBy(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByMinOrderAmountAs(String retName){
       groupBy(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByMinOrderAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByMaxDiscountAmount(){
       groupBy(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByMaxDiscountAmountAs(String retName){
       groupBy(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByMaxDiscountAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByUsageLimit(){
       groupBy(DiscountCoupon.USAGE_LIMIT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUsageLimitAs(String retName){
       groupBy(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUsageLimitWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.USAGE_LIMIT_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByUsedCount(){
       groupBy(DiscountCoupon.USED_COUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUsedCountAs(String retName){
       groupBy(retName, DiscountCoupon.USED_COUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUsedCountWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.USED_COUNT_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByStartDate(){
       groupBy(DiscountCoupon.START_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByStartDateAs(String retName){
       groupBy(retName, DiscountCoupon.START_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByStartDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.START_DATE_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByEndDate(){
       groupBy(DiscountCoupon.END_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByEndDateAs(String retName){
       groupBy(retName, DiscountCoupon.END_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByEndDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.END_DATE_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByStatus(){
       groupBy(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByStatusAs(String retName){
       groupBy(retName, DiscountCoupon.STATUS_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.STATUS_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByCreatedTime(){
       groupBy(DiscountCoupon.CREATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, DiscountCoupon.CREATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByUpdatedTime(){
       groupBy(DiscountCoupon.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, DiscountCoupon.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByVersion(){
       groupBy(DiscountCoupon.VERSION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByVersionAs(String retName){
       groupBy(retName, DiscountCoupon.VERSION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.VERSION_PROPERTY, function);
       return this;
    }



    public DiscountCouponRequest<T> orderByIdAscending(){
       addOrderByAscending(DiscountCoupon.ID_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByIdDescending(){
       addOrderByDescending(DiscountCoupon.ID_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByCodeAscending(){
       addOrderByAscending(DiscountCoupon.CODE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByCodeDescending(){
       addOrderByDescending(DiscountCoupon.CODE_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DiscountCoupon.CODE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DiscountCoupon.CODE_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DiscountCoupon.DESCRIPTION_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> orderByDiscountPercentageAscending(){
       addOrderByAscending(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByDiscountPercentageDescending(){
       addOrderByDescending(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByMinOrderAmountAscending(){
       addOrderByAscending(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByMinOrderAmountDescending(){
       addOrderByDescending(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByMaxDiscountAmountAscending(){
       addOrderByAscending(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByMaxDiscountAmountDescending(){
       addOrderByDescending(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUsageLimitAscending(){
       addOrderByAscending(DiscountCoupon.USAGE_LIMIT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUsageLimitDescending(){
       addOrderByDescending(DiscountCoupon.USAGE_LIMIT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUsedCountAscending(){
       addOrderByAscending(DiscountCoupon.USED_COUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUsedCountDescending(){
       addOrderByDescending(DiscountCoupon.USED_COUNT_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByStartDateAscending(){
       addOrderByAscending(DiscountCoupon.START_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByStartDateDescending(){
       addOrderByDescending(DiscountCoupon.START_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByEndDateAscending(){
       addOrderByAscending(DiscountCoupon.END_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByEndDateDescending(){
       addOrderByDescending(DiscountCoupon.END_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByStatusAscending(){
       addOrderByAscending(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByStatusDescending(){
       addOrderByDescending(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(DiscountCoupon.STATUS_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(DiscountCoupon.CREATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(DiscountCoupon.CREATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(DiscountCoupon.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(DiscountCoupon.UPDATED_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByVersionAscending(){
       addOrderByAscending(DiscountCoupon.VERSION_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByVersionDescending(){
       addOrderByDescending(DiscountCoupon.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public DiscountCouponRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public DiscountCouponRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public DiscountCouponRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public DiscountCouponRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public DiscountCouponRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}