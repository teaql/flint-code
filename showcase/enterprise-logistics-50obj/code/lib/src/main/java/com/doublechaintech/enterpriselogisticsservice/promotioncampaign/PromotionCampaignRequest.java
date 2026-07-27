package com.doublechaintech.enterpriselogisticsservice.promotioncampaign;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class PromotionCampaignRequest<T extends PromotionCampaign> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PromotionCampaignRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PromotionCampaignRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PromotionCampaignRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PromotionCampaignRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PromotionCampaignRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PromotionCampaignRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PromotionCampaignRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PromotionCampaignRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PromotionCampaignRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PromotionCampaignRequest<T> matchingAnyOf(PromotionCampaignRequest promotionCampaign){
        super.internalMatchAny(promotionCampaign);
        return this;
    }

    public PromotionCampaignRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PromotionCampaignRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PromotionCampaignRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PromotionCampaignRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectDescription().selectStartDate().selectEndDate().selectBudget().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public PromotionCampaignRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PromotionCampaignRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectDescription().selectStartDate().selectEndDate().selectBudget().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public PromotionCampaignRequest<T> selectChildren(){
        super.selectAny();
        selectMarketingRoiList();
        return selectId().selectName().selectDescription().selectStartDate().selectEndDate().selectBudget().selectStatus().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public PromotionCampaignRequest<T> selectId(){
       selectProperty(PromotionCampaign.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectId(){
       unselectProperty(PromotionCampaign.ID_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectName(){
       selectProperty(PromotionCampaign.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectName(){
       unselectProperty(PromotionCampaign.NAME_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectDescription(){
       selectProperty(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectDescription(){
       unselectProperty(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectStartDate(){
       selectProperty(PromotionCampaign.START_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the startDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  startDate) to fetch startDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectStartDate(){
       unselectProperty(PromotionCampaign.START_DATE_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectEndDate(){
       selectProperty(PromotionCampaign.END_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the endDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  endDate) to fetch endDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectEndDate(){
       unselectProperty(PromotionCampaign.END_DATE_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectBudget(){
       selectProperty(PromotionCampaign.BUDGET_PROPERTY);
       return this;
    }

    /**
     * fill the budget with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  budget) to fetch budget property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the budget with customized aggrFunction, TEAQL uses ({aggrFunction}(budget) AS budget to fetch budget property.
     * @param aggrFunction  aggrFunction
     */
    public PromotionCampaignRequest<T> selectBudget(AggrFunction aggrFunction){
       selectProperty(PromotionCampaign.BUDGET_PROPERTY, aggrFunction);
       return this;
    }


    public PromotionCampaignRequest<T> unselectBudget(){
       unselectProperty(PromotionCampaign.BUDGET_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectStatus(){
       selectProperty(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectStatus(){
       unselectProperty(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectCreatedTime(){
       selectProperty(PromotionCampaign.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectCreatedTime(){
       unselectProperty(PromotionCampaign.CREATED_TIME_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectUpdatedTime(){
       selectProperty(PromotionCampaign.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectUpdatedTime(){
       unselectProperty(PromotionCampaign.UPDATED_TIME_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectVersion(){
       selectProperty(PromotionCampaign.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PromotionCampaignRequest<T> unselectVersion(){
       unselectProperty(PromotionCampaign.VERSION_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> selectMarketingRoiList(){
       return selectMarketingRoiListWith(Q.marketingRois().selectSelf());
    }

    public PromotionCampaignRequest<T> selectMarketingRoiListWith(MarketingRoiRequest marketingRoiList){
       enhanceRelation(PromotionCampaign.MARKETING_ROI_LIST_PROPERTY, marketingRoiList);
       return this;
    }

    public PromotionCampaignRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.ID_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PromotionCampaignRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PromotionCampaignRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public PromotionCampaignRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.NAME_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public PromotionCampaignRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public PromotionCampaignRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public PromotionCampaignRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public PromotionCampaignRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public PromotionCampaignRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public PromotionCampaignRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public PromotionCampaignRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public PromotionCampaignRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public PromotionCampaignRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public PromotionCampaignRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public PromotionCampaignRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.DESCRIPTION_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public PromotionCampaignRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public PromotionCampaignRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public PromotionCampaignRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public PromotionCampaignRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public PromotionCampaignRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public PromotionCampaignRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public PromotionCampaignRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public PromotionCampaignRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public PromotionCampaignRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public PromotionCampaignRequest<T> filterByStartDate(LocalDate... startDate){
      if (startDate == null || startDate.length == 0) {
        throw new IllegalArgumentException("filterByStartDate parameter startDate cannot be empty");
      }
      return appendSearchCriteria(createStartDateCriteria(Operator.EQUAL, (Object[])startDate));
    }

    public PromotionCampaignRequest<T> withStartDate(Operator operator, Object... values){
       return appendSearchCriteria(createStartDateCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withStartDateIsUnknown(){
       return withStartDate(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withStartDateIsKnown(){
       return withStartDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStartDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.START_DATE_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withStartDateGreaterThan(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateGreaterThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN_OR_EQUAL, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateLessThan(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateLessThanOrEqualTo(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN_OR_EQUAL, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateBetween(LocalDate startOfStartDate, LocalDate endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }
    public PromotionCampaignRequest<T> withStartDateBefore(LocalDate startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateBefore(Date startDate){
       return withStartDate(Operator.LESS_THAN, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateAfter(LocalDate startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateAfter(Date startDate){
       return withStartDate(Operator.GREATER_THAN, startDate);
    }

    public PromotionCampaignRequest<T> withStartDateBetween(Date startOfStartDate, Date endOfStartDate){
       return withStartDate(Operator.BETWEEN, startOfStartDate, endOfStartDate);
    }




    public PromotionCampaignRequest<T> filterByEndDate(LocalDate... endDate){
      if (endDate == null || endDate.length == 0) {
        throw new IllegalArgumentException("filterByEndDate parameter endDate cannot be empty");
      }
      return appendSearchCriteria(createEndDateCriteria(Operator.EQUAL, (Object[])endDate));
    }

    public PromotionCampaignRequest<T> withEndDate(Operator operator, Object... values){
       return appendSearchCriteria(createEndDateCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withEndDateIsUnknown(){
       return withEndDate(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withEndDateIsKnown(){
       return withEndDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEndDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.END_DATE_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withEndDateGreaterThan(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateGreaterThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN_OR_EQUAL, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateLessThan(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateLessThanOrEqualTo(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN_OR_EQUAL, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateBetween(LocalDate startOfEndDate, LocalDate endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }
    public PromotionCampaignRequest<T> withEndDateBefore(LocalDate endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateBefore(Date endDate){
       return withEndDate(Operator.LESS_THAN, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateAfter(LocalDate endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateAfter(Date endDate){
       return withEndDate(Operator.GREATER_THAN, endDate);
    }

    public PromotionCampaignRequest<T> withEndDateBetween(Date startOfEndDate, Date endOfEndDate){
       return withEndDate(Operator.BETWEEN, startOfEndDate, endOfEndDate);
    }




    public PromotionCampaignRequest<T> filterByBudget(BigDecimal... budget){
      if (budget == null || budget.length == 0) {
        throw new IllegalArgumentException("filterByBudget parameter budget cannot be empty");
      }
      return appendSearchCriteria(createBudgetCriteria(Operator.EQUAL, (Object[])budget));
    }

    public PromotionCampaignRequest<T> withBudget(Operator operator, Object... values){
       return appendSearchCriteria(createBudgetCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withBudgetIsUnknown(){
       return withBudget(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withBudgetIsKnown(){
       return withBudget(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createBudgetCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.BUDGET_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withBudgetGreaterThan(BigDecimal budget){
       return withBudget(Operator.GREATER_THAN, budget);
    }

    public PromotionCampaignRequest<T> withBudgetGreaterThanOrEqualTo(BigDecimal budget){
       return withBudget(Operator.GREATER_THAN_OR_EQUAL, budget);
    }

    public PromotionCampaignRequest<T> withBudgetLessThan(BigDecimal budget){
       return withBudget(Operator.LESS_THAN, budget);
    }

    public PromotionCampaignRequest<T> withBudgetLessThanOrEqualTo(BigDecimal budget){
       return withBudget(Operator.LESS_THAN_OR_EQUAL, budget);
    }

    public PromotionCampaignRequest<T> withBudgetBetween(BigDecimal startOfBudget, BigDecimal endOfBudget){
       return withBudget(Operator.BETWEEN, startOfBudget, endOfBudget);
    }



    public PromotionCampaignRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public PromotionCampaignRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.STATUS_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public PromotionCampaignRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public PromotionCampaignRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public PromotionCampaignRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public PromotionCampaignRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public PromotionCampaignRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public PromotionCampaignRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public PromotionCampaignRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public PromotionCampaignRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public PromotionCampaignRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public PromotionCampaignRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public PromotionCampaignRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.CREATED_TIME_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public PromotionCampaignRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public PromotionCampaignRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public PromotionCampaignRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public PromotionCampaignRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.UPDATED_TIME_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public PromotionCampaignRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public PromotionCampaignRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public PromotionCampaignRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PromotionCampaignRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PromotionCampaignRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PromotionCampaignRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PromotionCampaign.VERSION_PROPERTY, operator, values);
    }

    public PromotionCampaignRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PromotionCampaignRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PromotionCampaignRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PromotionCampaignRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PromotionCampaignRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public PromotionCampaignRequest<T> withMarketingRoiListMatching(MarketingRoiRequest marketingRoiRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PromotionCampaign.ID_PROPERTY, marketingRoiRequest, MarketingRoi.CAMPAIGN_PROPERTY));
    }

    public PromotionCampaignRequest<T> withoutMarketingRoiListMatching(MarketingRoiRequest marketingRoiRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PromotionCampaign.ID_PROPERTY, marketingRoiRequest, MarketingRoi.CAMPAIGN_PROPERTY)));
    }

    public PromotionCampaignRequest<T> haveMarketingRois(){
        return withMarketingRoiListMatching(Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> haveNoMarketingRois(){
        return withoutMarketingRoiListMatching(Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> count(){
        super.count();
        return this;
    }
    public PromotionCampaignRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PromotionCampaignRequest minBudget(){
        return minBudgetAs(prefix("minOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest minBudgetAs(String retName){
        super.min(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest maxBudget(){
        return maxBudgetAs(prefix("maxOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest maxBudgetAs(String retName){
        super.max(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest sumBudget(){
        return sumBudgetAs(prefix("sumOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest sumBudgetAs(String retName){
        super.sum(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest avgBudget(){
        return avgBudgetAs(prefix("avgOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest avgBudgetAs(String retName){
        super.avg(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest standardDeviationBudget(){
        return standardDeviationBudgetAs(prefix("standardDeviationOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest standardDeviationBudgetAs(String retName){
        super.standardDeviation(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest squareRootOfPopulationStandardDeviationBudget(){
        return squareRootOfPopulationStandardDeviationBudgetAs(prefix("squareRootOfPopulationStandardDeviationOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest squareRootOfPopulationStandardDeviationBudgetAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest sampleVarianceBudget(){
        return sampleVarianceBudgetAs(prefix("sampleVarianceOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest sampleVarianceBudgetAs(String retName){
        super.sampleVariance(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest samplePopulationVarianceBudget(){
        return samplePopulationVarianceBudgetAs(prefix("samplePopulationVarianceOf",PromotionCampaign.BUDGET_PROPERTY));
    }

    public PromotionCampaignRequest samplePopulationVarianceBudgetAs(String retName){
        super.samplePopulationVariance(retName, PromotionCampaign.BUDGET_PROPERTY);
        return this;
    }
    public PromotionCampaignRequest<T> groupByMarketingRoisWithDetails(MarketingRoiRequest subRequest){
       aggregate(PromotionCampaign.MARKETING_ROI_LIST_PROPERTY, subRequest);
       return this;
    }

    public PromotionCampaignRequest<T> groupById(){
       groupBy(PromotionCampaign.ID_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByIdAs(String retName){
       groupBy(retName, PromotionCampaign.ID_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.ID_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByName(){
       groupBy(PromotionCampaign.NAME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByNameAs(String retName){
       groupBy(retName, PromotionCampaign.NAME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.NAME_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByDescription(){
       groupBy(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByStartDate(){
       groupBy(PromotionCampaign.START_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByStartDateAs(String retName){
       groupBy(retName, PromotionCampaign.START_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByStartDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.START_DATE_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByEndDate(){
       groupBy(PromotionCampaign.END_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByEndDateAs(String retName){
       groupBy(retName, PromotionCampaign.END_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByEndDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.END_DATE_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByBudget(){
       groupBy(PromotionCampaign.BUDGET_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByBudgetAs(String retName){
       groupBy(retName, PromotionCampaign.BUDGET_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByBudgetWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.BUDGET_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByStatus(){
       groupBy(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByStatusAs(String retName){
       groupBy(retName, PromotionCampaign.STATUS_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.STATUS_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByCreatedTime(){
       groupBy(PromotionCampaign.CREATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, PromotionCampaign.CREATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByUpdatedTime(){
       groupBy(PromotionCampaign.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, PromotionCampaign.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public PromotionCampaignRequest<T> groupByVersion(){
       groupBy(PromotionCampaign.VERSION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByVersionAs(String retName){
       groupBy(retName, PromotionCampaign.VERSION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, PromotionCampaign.VERSION_PROPERTY, function);
       return this;
    }



    public PromotionCampaignRequest<T> orderByIdAscending(){
       addOrderByAscending(PromotionCampaign.ID_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByIdDescending(){
       addOrderByDescending(PromotionCampaign.ID_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByNameAscending(){
       addOrderByAscending(PromotionCampaign.NAME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByNameDescending(){
       addOrderByDescending(PromotionCampaign.NAME_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PromotionCampaign.NAME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PromotionCampaign.NAME_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PromotionCampaign.DESCRIPTION_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> orderByStartDateAscending(){
       addOrderByAscending(PromotionCampaign.START_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByStartDateDescending(){
       addOrderByDescending(PromotionCampaign.START_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByEndDateAscending(){
       addOrderByAscending(PromotionCampaign.END_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByEndDateDescending(){
       addOrderByDescending(PromotionCampaign.END_DATE_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByBudgetAscending(){
       addOrderByAscending(PromotionCampaign.BUDGET_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByBudgetDescending(){
       addOrderByDescending(PromotionCampaign.BUDGET_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByStatusAscending(){
       addOrderByAscending(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByStatusDescending(){
       addOrderByDescending(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PromotionCampaign.STATUS_PROPERTY);
       return this;
    }
    public PromotionCampaignRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(PromotionCampaign.CREATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(PromotionCampaign.CREATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(PromotionCampaign.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(PromotionCampaign.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByVersionAscending(){
       addOrderByAscending(PromotionCampaign.VERSION_PROPERTY);
       return this;
    }

    public PromotionCampaignRequest<T> orderByVersionDescending(){
       addOrderByDescending(PromotionCampaign.VERSION_PROPERTY);
       return this;
    }


    public PromotionCampaignRequest<T> statsFromMarketingRoisAs(String name, MarketingRoiRequest subRequest){
       return statsFromMarketingRoisAs(name, subRequest, false);
    }

    public PromotionCampaignRequest<T> statsFromMarketingRoisAs(String name, MarketingRoiRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(MarketingRoi.CAMPAIGN_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PromotionCampaignRequest<T> statsFromMarketingRois(MarketingRoiRequest subRequest){
       return statsFromMarketingRoisAs(REFINEMENTS, subRequest);
    }
    public PromotionCampaignRequest<T> countMarketingRois(){
        return countMarketingRoisAs("Count");
    }

    public PromotionCampaignRequest<T> countMarketingRoisAs(String name){
        return countMarketingRoisWith(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> countMarketingRoisWith(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.count(), true);
    }
    public PromotionCampaignRequest<T> minSpendOfMarketingRois(){
        return minSpendOfMarketingRoisAs("minSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> minSpendOfMarketingRoisAs(String name){
        return minSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> minSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.minSpend(), true);
    }
    public PromotionCampaignRequest<T> maxSpendOfMarketingRois(){
        return maxSpendOfMarketingRoisAs("maxSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> maxSpendOfMarketingRoisAs(String name){
        return maxSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> maxSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.maxSpend(), true);
    }
    public PromotionCampaignRequest<T> sumSpendOfMarketingRois(){
        return sumSpendOfMarketingRoisAs("sumSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> sumSpendOfMarketingRoisAs(String name){
        return sumSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> sumSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sumSpend(), true);
    }
    public PromotionCampaignRequest<T> avgSpendOfMarketingRois(){
        return avgSpendOfMarketingRoisAs("avgSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> avgSpendOfMarketingRoisAs(String name){
        return avgSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> avgSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.avgSpend(), true);
    }
    public PromotionCampaignRequest<T> standardDeviationSpendOfMarketingRois(){
        return standardDeviationSpendOfMarketingRoisAs("stdDevSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> standardDeviationSpendOfMarketingRoisAs(String name){
        return standardDeviationSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> standardDeviationSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.standardDeviationSpend(), true);
    }
    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationSpendOfMarketingRois(){
        return squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs("stdDevPopSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs(String name){
        return squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.squareRootOfPopulationStandardDeviationSpend(), true);
    }
    public PromotionCampaignRequest<T> sampleVarianceSpendOfMarketingRois(){
        return sampleVarianceSpendOfMarketingRoisAs("varSampSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> sampleVarianceSpendOfMarketingRoisAs(String name){
        return sampleVarianceSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> sampleVarianceSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sampleVarianceSpend(), true);
    }
    public PromotionCampaignRequest<T> samplePopulationVarianceSpendOfMarketingRois(){
        return samplePopulationVarianceSpendOfMarketingRoisAs("varPopSpendOfMarketingRois");
    }

    public PromotionCampaignRequest<T> samplePopulationVarianceSpendOfMarketingRoisAs(String name){
        return samplePopulationVarianceSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> samplePopulationVarianceSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.samplePopulationVarianceSpend(), true);
    }
    public PromotionCampaignRequest<T> minRevenueOfMarketingRois(){
        return minRevenueOfMarketingRoisAs("minRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> minRevenueOfMarketingRoisAs(String name){
        return minRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> minRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.minRevenue(), true);
    }
    public PromotionCampaignRequest<T> maxRevenueOfMarketingRois(){
        return maxRevenueOfMarketingRoisAs("maxRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> maxRevenueOfMarketingRoisAs(String name){
        return maxRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> maxRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.maxRevenue(), true);
    }
    public PromotionCampaignRequest<T> sumRevenueOfMarketingRois(){
        return sumRevenueOfMarketingRoisAs("sumRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> sumRevenueOfMarketingRoisAs(String name){
        return sumRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> sumRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sumRevenue(), true);
    }
    public PromotionCampaignRequest<T> avgRevenueOfMarketingRois(){
        return avgRevenueOfMarketingRoisAs("avgRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> avgRevenueOfMarketingRoisAs(String name){
        return avgRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> avgRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.avgRevenue(), true);
    }
    public PromotionCampaignRequest<T> standardDeviationRevenueOfMarketingRois(){
        return standardDeviationRevenueOfMarketingRoisAs("stdDevRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> standardDeviationRevenueOfMarketingRoisAs(String name){
        return standardDeviationRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> standardDeviationRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.standardDeviationRevenue(), true);
    }
    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationRevenueOfMarketingRois(){
        return squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs("stdDevPopRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs(String name){
        return squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.squareRootOfPopulationStandardDeviationRevenue(), true);
    }
    public PromotionCampaignRequest<T> sampleVarianceRevenueOfMarketingRois(){
        return sampleVarianceRevenueOfMarketingRoisAs("varSampRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> sampleVarianceRevenueOfMarketingRoisAs(String name){
        return sampleVarianceRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> sampleVarianceRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sampleVarianceRevenue(), true);
    }
    public PromotionCampaignRequest<T> samplePopulationVarianceRevenueOfMarketingRois(){
        return samplePopulationVarianceRevenueOfMarketingRoisAs("varPopRevenueOfMarketingRois");
    }

    public PromotionCampaignRequest<T> samplePopulationVarianceRevenueOfMarketingRoisAs(String name){
        return samplePopulationVarianceRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> samplePopulationVarianceRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.samplePopulationVarianceRevenue(), true);
    }
    public PromotionCampaignRequest<T> minRoiPercentageOfMarketingRois(){
        return minRoiPercentageOfMarketingRoisAs("minRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> minRoiPercentageOfMarketingRoisAs(String name){
        return minRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> minRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.minRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> maxRoiPercentageOfMarketingRois(){
        return maxRoiPercentageOfMarketingRoisAs("maxRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> maxRoiPercentageOfMarketingRoisAs(String name){
        return maxRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> maxRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.maxRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> sumRoiPercentageOfMarketingRois(){
        return sumRoiPercentageOfMarketingRoisAs("sumRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> sumRoiPercentageOfMarketingRoisAs(String name){
        return sumRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> sumRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sumRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> avgRoiPercentageOfMarketingRois(){
        return avgRoiPercentageOfMarketingRoisAs("avgRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> avgRoiPercentageOfMarketingRoisAs(String name){
        return avgRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> avgRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.avgRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> standardDeviationRoiPercentageOfMarketingRois(){
        return standardDeviationRoiPercentageOfMarketingRoisAs("stdDevRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> standardDeviationRoiPercentageOfMarketingRoisAs(String name){
        return standardDeviationRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> standardDeviationRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.standardDeviationRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRois(){
        return squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs("stdDevPopRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs(String name){
        return squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.squareRootOfPopulationStandardDeviationRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> sampleVarianceRoiPercentageOfMarketingRois(){
        return sampleVarianceRoiPercentageOfMarketingRoisAs("varSampRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> sampleVarianceRoiPercentageOfMarketingRoisAs(String name){
        return sampleVarianceRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> sampleVarianceRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sampleVarianceRoiPercentage(), true);
    }
    public PromotionCampaignRequest<T> samplePopulationVarianceRoiPercentageOfMarketingRois(){
        return samplePopulationVarianceRoiPercentageOfMarketingRoisAs("varPopRoiPercentageOfMarketingRois");
    }

    public PromotionCampaignRequest<T> samplePopulationVarianceRoiPercentageOfMarketingRoisAs(String name){
        return samplePopulationVarianceRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public PromotionCampaignRequest<T> samplePopulationVarianceRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.samplePopulationVarianceRoiPercentage(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public PromotionCampaignRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PromotionCampaignRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PromotionCampaignRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PromotionCampaignRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PromotionCampaignRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}