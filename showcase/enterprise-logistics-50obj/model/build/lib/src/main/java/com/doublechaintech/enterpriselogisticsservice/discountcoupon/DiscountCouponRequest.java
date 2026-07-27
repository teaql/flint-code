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
        return selectId().selectCode().selectDiscountPercentage().selectMaxUses().selectCurrentUses().selectExpiryDate().selectStatus().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public DiscountCouponRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public DiscountCouponRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectCode().selectDiscountPercentage().selectMaxUses().selectCurrentUses().selectExpiryDate().selectStatus().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public DiscountCouponRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectCode().selectDiscountPercentage().selectMaxUses().selectCurrentUses().selectExpiryDate().selectStatus().selectCreatedTime().selectUpdateTime().selectVersion();
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
    public DiscountCouponRequest<T> selectMaxUses(){
       selectProperty(DiscountCoupon.MAX_USES_PROPERTY);
       return this;
    }

    /**
     * fill the maxUses with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  maxUses) to fetch maxUses property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the maxUses with customized aggrFunction, TEAQL uses ({aggrFunction}(maxUses) AS maxUses to fetch maxUses property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectMaxUses(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.MAX_USES_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectMaxUses(){
       unselectProperty(DiscountCoupon.MAX_USES_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectCurrentUses(){
       selectProperty(DiscountCoupon.CURRENT_USES_PROPERTY);
       return this;
    }

    /**
     * fill the currentUses with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currentUses) to fetch currentUses property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the currentUses with customized aggrFunction, TEAQL uses ({aggrFunction}(currentUses) AS currentUses to fetch currentUses property.
     * @param aggrFunction  aggrFunction
     */
    public DiscountCouponRequest<T> selectCurrentUses(AggrFunction aggrFunction){
       selectProperty(DiscountCoupon.CURRENT_USES_PROPERTY, aggrFunction);
       return this;
    }


    public DiscountCouponRequest<T> unselectCurrentUses(){
       unselectProperty(DiscountCoupon.CURRENT_USES_PROPERTY);
       return this;
    }
    public DiscountCouponRequest<T> selectExpiryDate(){
       selectProperty(DiscountCoupon.EXPIRY_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the expiryDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  expiryDate) to fetch expiryDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectExpiryDate(){
       unselectProperty(DiscountCoupon.EXPIRY_DATE_PROPERTY);
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
    public DiscountCouponRequest<T> selectUpdateTime(){
       selectProperty(DiscountCoupon.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public DiscountCouponRequest<T> unselectUpdateTime(){
       unselectProperty(DiscountCoupon.UPDATE_TIME_PROPERTY);
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



    public DiscountCouponRequest<T> filterByMaxUses(Integer... maxUses){
      if (maxUses == null || maxUses.length == 0) {
        throw new IllegalArgumentException("filterByMaxUses parameter maxUses cannot be empty");
      }
      return appendSearchCriteria(createMaxUsesCriteria(Operator.EQUAL, (Object[])maxUses));
    }

    public DiscountCouponRequest<T> withMaxUses(Operator operator, Object... values){
       return appendSearchCriteria(createMaxUsesCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withMaxUsesIsUnknown(){
       return withMaxUses(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withMaxUsesIsKnown(){
       return withMaxUses(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMaxUsesCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.MAX_USES_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withMaxUsesGreaterThan(Integer maxUses){
       return withMaxUses(Operator.GREATER_THAN, maxUses);
    }

    public DiscountCouponRequest<T> withMaxUsesGreaterThanOrEqualTo(Integer maxUses){
       return withMaxUses(Operator.GREATER_THAN_OR_EQUAL, maxUses);
    }

    public DiscountCouponRequest<T> withMaxUsesLessThan(Integer maxUses){
       return withMaxUses(Operator.LESS_THAN, maxUses);
    }

    public DiscountCouponRequest<T> withMaxUsesLessThanOrEqualTo(Integer maxUses){
       return withMaxUses(Operator.LESS_THAN_OR_EQUAL, maxUses);
    }

    public DiscountCouponRequest<T> withMaxUsesBetween(Integer startOfMaxUses, Integer endOfMaxUses){
       return withMaxUses(Operator.BETWEEN, startOfMaxUses, endOfMaxUses);
    }



    public DiscountCouponRequest<T> filterByCurrentUses(Integer... currentUses){
      if (currentUses == null || currentUses.length == 0) {
        throw new IllegalArgumentException("filterByCurrentUses parameter currentUses cannot be empty");
      }
      return appendSearchCriteria(createCurrentUsesCriteria(Operator.EQUAL, (Object[])currentUses));
    }

    public DiscountCouponRequest<T> withCurrentUses(Operator operator, Object... values){
       return appendSearchCriteria(createCurrentUsesCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withCurrentUsesIsUnknown(){
       return withCurrentUses(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withCurrentUsesIsKnown(){
       return withCurrentUses(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrentUsesCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.CURRENT_USES_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withCurrentUsesGreaterThan(Integer currentUses){
       return withCurrentUses(Operator.GREATER_THAN, currentUses);
    }

    public DiscountCouponRequest<T> withCurrentUsesGreaterThanOrEqualTo(Integer currentUses){
       return withCurrentUses(Operator.GREATER_THAN_OR_EQUAL, currentUses);
    }

    public DiscountCouponRequest<T> withCurrentUsesLessThan(Integer currentUses){
       return withCurrentUses(Operator.LESS_THAN, currentUses);
    }

    public DiscountCouponRequest<T> withCurrentUsesLessThanOrEqualTo(Integer currentUses){
       return withCurrentUses(Operator.LESS_THAN_OR_EQUAL, currentUses);
    }

    public DiscountCouponRequest<T> withCurrentUsesBetween(Integer startOfCurrentUses, Integer endOfCurrentUses){
       return withCurrentUses(Operator.BETWEEN, startOfCurrentUses, endOfCurrentUses);
    }



    public DiscountCouponRequest<T> filterByExpiryDate(LocalDate... expiryDate){
      if (expiryDate == null || expiryDate.length == 0) {
        throw new IllegalArgumentException("filterByExpiryDate parameter expiryDate cannot be empty");
      }
      return appendSearchCriteria(createExpiryDateCriteria(Operator.EQUAL, (Object[])expiryDate));
    }

    public DiscountCouponRequest<T> withExpiryDate(Operator operator, Object... values){
       return appendSearchCriteria(createExpiryDateCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withExpiryDateIsUnknown(){
       return withExpiryDate(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withExpiryDateIsKnown(){
       return withExpiryDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createExpiryDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.EXPIRY_DATE_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withExpiryDateGreaterThan(LocalDate expiryDate){
       return withExpiryDate(Operator.GREATER_THAN, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateGreaterThanOrEqualTo(LocalDate expiryDate){
       return withExpiryDate(Operator.GREATER_THAN_OR_EQUAL, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateLessThan(LocalDate expiryDate){
       return withExpiryDate(Operator.LESS_THAN, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateLessThanOrEqualTo(LocalDate expiryDate){
       return withExpiryDate(Operator.LESS_THAN_OR_EQUAL, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateBetween(LocalDate startOfExpiryDate, LocalDate endOfExpiryDate){
       return withExpiryDate(Operator.BETWEEN, startOfExpiryDate, endOfExpiryDate);
    }
    public DiscountCouponRequest<T> withExpiryDateBefore(LocalDate expiryDate){
       return withExpiryDate(Operator.LESS_THAN, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateBefore(Date expiryDate){
       return withExpiryDate(Operator.LESS_THAN, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateAfter(LocalDate expiryDate){
       return withExpiryDate(Operator.GREATER_THAN, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateAfter(Date expiryDate){
       return withExpiryDate(Operator.GREATER_THAN, expiryDate);
    }

    public DiscountCouponRequest<T> withExpiryDateBetween(Date startOfExpiryDate, Date endOfExpiryDate){
       return withExpiryDate(Operator.BETWEEN, startOfExpiryDate, endOfExpiryDate);
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




    public DiscountCouponRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public DiscountCouponRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public DiscountCouponRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public DiscountCouponRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(DiscountCoupon.UPDATE_TIME_PROPERTY, operator, values);
    }

    public DiscountCouponRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public DiscountCouponRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public DiscountCouponRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
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
    public DiscountCouponRequest minMaxUses(){
        return minMaxUsesAs(prefix("minOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest minMaxUsesAs(String retName){
        super.min(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxMaxUses(){
        return maxMaxUsesAs(prefix("maxOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest maxMaxUsesAs(String retName){
        super.max(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumMaxUses(){
        return sumMaxUsesAs(prefix("sumOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest sumMaxUsesAs(String retName){
        super.sum(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgMaxUses(){
        return avgMaxUsesAs(prefix("avgOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest avgMaxUsesAs(String retName){
        super.avg(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationMaxUses(){
        return standardDeviationMaxUsesAs(prefix("standardDeviationOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationMaxUsesAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationMaxUses(){
        return squareRootOfPopulationStandardDeviationMaxUsesAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationMaxUsesAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceMaxUses(){
        return sampleVarianceMaxUsesAs(prefix("sampleVarianceOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceMaxUsesAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceMaxUses(){
        return samplePopulationVarianceMaxUsesAs(prefix("samplePopulationVarianceOf",DiscountCoupon.MAX_USES_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceMaxUsesAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.MAX_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest minCurrentUses(){
        return minCurrentUsesAs(prefix("minOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest minCurrentUsesAs(String retName){
        super.min(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest maxCurrentUses(){
        return maxCurrentUsesAs(prefix("maxOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest maxCurrentUsesAs(String retName){
        super.max(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sumCurrentUses(){
        return sumCurrentUsesAs(prefix("sumOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest sumCurrentUsesAs(String retName){
        super.sum(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest avgCurrentUses(){
        return avgCurrentUsesAs(prefix("avgOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest avgCurrentUsesAs(String retName){
        super.avg(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest standardDeviationCurrentUses(){
        return standardDeviationCurrentUsesAs(prefix("standardDeviationOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest standardDeviationCurrentUsesAs(String retName){
        super.standardDeviation(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest squareRootOfPopulationStandardDeviationCurrentUses(){
        return squareRootOfPopulationStandardDeviationCurrentUsesAs(prefix("squareRootOfPopulationStandardDeviationOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest squareRootOfPopulationStandardDeviationCurrentUsesAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest sampleVarianceCurrentUses(){
        return sampleVarianceCurrentUsesAs(prefix("sampleVarianceOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest sampleVarianceCurrentUsesAs(String retName){
        super.sampleVariance(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
        return this;
    }
    public DiscountCouponRequest samplePopulationVarianceCurrentUses(){
        return samplePopulationVarianceCurrentUsesAs(prefix("samplePopulationVarianceOf",DiscountCoupon.CURRENT_USES_PROPERTY));
    }

    public DiscountCouponRequest samplePopulationVarianceCurrentUsesAs(String retName){
        super.samplePopulationVariance(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
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

    public DiscountCouponRequest<T> groupByMaxUses(){
       groupBy(DiscountCoupon.MAX_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByMaxUsesAs(String retName){
       groupBy(retName, DiscountCoupon.MAX_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByMaxUsesWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.MAX_USES_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByCurrentUses(){
       groupBy(DiscountCoupon.CURRENT_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByCurrentUsesAs(String retName){
       groupBy(retName, DiscountCoupon.CURRENT_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByCurrentUsesWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.CURRENT_USES_PROPERTY, function);
       return this;
    }

    public DiscountCouponRequest<T> groupByExpiryDate(){
       groupBy(DiscountCoupon.EXPIRY_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByExpiryDateAs(String retName){
       groupBy(retName, DiscountCoupon.EXPIRY_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByExpiryDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.EXPIRY_DATE_PROPERTY, function);
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

    public DiscountCouponRequest<T> groupByUpdateTime(){
       groupBy(DiscountCoupon.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, DiscountCoupon.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, DiscountCoupon.UPDATE_TIME_PROPERTY, function);
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
    public DiscountCouponRequest<T> orderByDiscountPercentageAscending(){
       addOrderByAscending(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByDiscountPercentageDescending(){
       addOrderByDescending(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByMaxUsesAscending(){
       addOrderByAscending(DiscountCoupon.MAX_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByMaxUsesDescending(){
       addOrderByDescending(DiscountCoupon.MAX_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByCurrentUsesAscending(){
       addOrderByAscending(DiscountCoupon.CURRENT_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByCurrentUsesDescending(){
       addOrderByDescending(DiscountCoupon.CURRENT_USES_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByExpiryDateAscending(){
       addOrderByAscending(DiscountCoupon.EXPIRY_DATE_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByExpiryDateDescending(){
       addOrderByDescending(DiscountCoupon.EXPIRY_DATE_PROPERTY);
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

    public DiscountCouponRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(DiscountCoupon.UPDATE_TIME_PROPERTY);
       return this;
    }

    public DiscountCouponRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(DiscountCoupon.UPDATE_TIME_PROPERTY);
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