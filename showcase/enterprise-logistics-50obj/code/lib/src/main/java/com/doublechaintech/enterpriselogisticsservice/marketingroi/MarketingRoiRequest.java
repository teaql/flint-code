package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignRequest;
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

public class MarketingRoiRequest<T extends MarketingRoi> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public MarketingRoiRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public MarketingRoiRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public MarketingRoiRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public MarketingRoiRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public MarketingRoiRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public MarketingRoiRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public MarketingRoiRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (MarketingRoiRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public MarketingRoiRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public MarketingRoiRequest<T> matchingAnyOf(MarketingRoiRequest marketingRoi){
        super.internalMatchAny(marketingRoi);
        return this;
    }

    public MarketingRoiRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public MarketingRoiRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public MarketingRoiRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public MarketingRoiRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectCampaignIdOnly().selectTotalSpend().selectTotalRevenue().selectRoiPercentage().selectReportDate().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public MarketingRoiRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public MarketingRoiRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectCampaign().selectTotalSpend().selectTotalRevenue().selectRoiPercentage().selectReportDate().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public MarketingRoiRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectCampaign().selectTotalSpend().selectTotalRevenue().selectRoiPercentage().selectReportDate().selectCreatedTime().selectUpdateTime().selectVersion();
    }


    public MarketingRoiRequest<T> selectId(){
       selectProperty(MarketingRoi.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MarketingRoiRequest<T> unselectId(){
       unselectProperty(MarketingRoi.ID_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectCampaignIdOnly(){
       selectProperty(MarketingRoi.CAMPAIGN_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> selectCampaign(){
        return selectCampaignWith(Q.promotionCampaigns().unlimited().selectSelf());
    }

    public MarketingRoiRequest<T> selectCampaignWith(PromotionCampaignRequest campaign){
       selectProperty(MarketingRoi.CAMPAIGN_PROPERTY);
       enhanceRelation(MarketingRoi.CAMPAIGN_PROPERTY, campaign);
       return this;
    }

    public MarketingRoiRequest<T> unselectCampaign(){
       unselectProperty(MarketingRoi.CAMPAIGN_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectTotalSpend(){
       selectProperty(MarketingRoi.TOTAL_SPEND_PROPERTY);
       return this;
    }

    /**
     * fill the totalSpend with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalSpend) to fetch totalSpend property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalSpend with customized aggrFunction, TEAQL uses ({aggrFunction}(totalSpend) AS totalSpend to fetch totalSpend property.
     * @param aggrFunction  aggrFunction
     */
    public MarketingRoiRequest<T> selectTotalSpend(AggrFunction aggrFunction){
       selectProperty(MarketingRoi.TOTAL_SPEND_PROPERTY, aggrFunction);
       return this;
    }


    public MarketingRoiRequest<T> unselectTotalSpend(){
       unselectProperty(MarketingRoi.TOTAL_SPEND_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectTotalRevenue(){
       selectProperty(MarketingRoi.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    /**
     * fill the totalRevenue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalRevenue) to fetch totalRevenue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalRevenue with customized aggrFunction, TEAQL uses ({aggrFunction}(totalRevenue) AS totalRevenue to fetch totalRevenue property.
     * @param aggrFunction  aggrFunction
     */
    public MarketingRoiRequest<T> selectTotalRevenue(AggrFunction aggrFunction){
       selectProperty(MarketingRoi.TOTAL_REVENUE_PROPERTY, aggrFunction);
       return this;
    }


    public MarketingRoiRequest<T> unselectTotalRevenue(){
       unselectProperty(MarketingRoi.TOTAL_REVENUE_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectRoiPercentage(){
       selectProperty(MarketingRoi.ROI_PERCENTAGE_PROPERTY);
       return this;
    }

    /**
     * fill the roiPercentage with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  roiPercentage) to fetch roiPercentage property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the roiPercentage with customized aggrFunction, TEAQL uses ({aggrFunction}(roiPercentage) AS roiPercentage to fetch roiPercentage property.
     * @param aggrFunction  aggrFunction
     */
    public MarketingRoiRequest<T> selectRoiPercentage(AggrFunction aggrFunction){
       selectProperty(MarketingRoi.ROI_PERCENTAGE_PROPERTY, aggrFunction);
       return this;
    }


    public MarketingRoiRequest<T> unselectRoiPercentage(){
       unselectProperty(MarketingRoi.ROI_PERCENTAGE_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectReportDate(){
       selectProperty(MarketingRoi.REPORT_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the reportDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  reportDate) to fetch reportDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MarketingRoiRequest<T> unselectReportDate(){
       unselectProperty(MarketingRoi.REPORT_DATE_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectCreatedTime(){
       selectProperty(MarketingRoi.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MarketingRoiRequest<T> unselectCreatedTime(){
       unselectProperty(MarketingRoi.CREATED_TIME_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectUpdateTime(){
       selectProperty(MarketingRoi.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MarketingRoiRequest<T> unselectUpdateTime(){
       unselectProperty(MarketingRoi.UPDATE_TIME_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectVersion(){
       selectProperty(MarketingRoi.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MarketingRoiRequest<T> unselectVersion(){
       unselectProperty(MarketingRoi.VERSION_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.ID_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public MarketingRoiRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public MarketingRoiRequest<T> filterByCampaign(PromotionCampaign... campaign){
      if (campaign == null || campaign.length == 0) {
        throw new IllegalArgumentException("filterByCampaign parameter campaign cannot be empty");
      }
      return appendSearchCriteria(createCampaignCriteria(Operator.EQUAL, (Object[])campaign));
    }

    public MarketingRoiRequest<T> withCampaign(Operator operator, Object... values){
       return appendSearchCriteria(createCampaignCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withCampaignIsUnknown(){
       return withCampaign(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withCampaignIsKnown(){
       return withCampaign(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCampaignCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.CAMPAIGN_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> filterByCampaign(Long campaign){
      if(campaign == null){
         return this;
      }
      return withCampaign(Operator.EQUAL, campaign);
    }
    public MarketingRoiRequest<T> withCampaignMatching(PromotionCampaignRequest campaign){
       return appendSearchCriteria(new SubQuerySearchCriteria(MarketingRoi.CAMPAIGN_PROPERTY, campaign, PromotionCampaign.ID_PROPERTY));
    }

    public MarketingRoiRequest<T> filterByTotalSpend(BigDecimal... totalSpend){
      if (totalSpend == null || totalSpend.length == 0) {
        throw new IllegalArgumentException("filterByTotalSpend parameter totalSpend cannot be empty");
      }
      return appendSearchCriteria(createTotalSpendCriteria(Operator.EQUAL, (Object[])totalSpend));
    }

    public MarketingRoiRequest<T> withTotalSpend(Operator operator, Object... values){
       return appendSearchCriteria(createTotalSpendCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withTotalSpendIsUnknown(){
       return withTotalSpend(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withTotalSpendIsKnown(){
       return withTotalSpend(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalSpendCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.TOTAL_SPEND_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withTotalSpendGreaterThan(BigDecimal totalSpend){
       return withTotalSpend(Operator.GREATER_THAN, totalSpend);
    }

    public MarketingRoiRequest<T> withTotalSpendGreaterThanOrEqualTo(BigDecimal totalSpend){
       return withTotalSpend(Operator.GREATER_THAN_OR_EQUAL, totalSpend);
    }

    public MarketingRoiRequest<T> withTotalSpendLessThan(BigDecimal totalSpend){
       return withTotalSpend(Operator.LESS_THAN, totalSpend);
    }

    public MarketingRoiRequest<T> withTotalSpendLessThanOrEqualTo(BigDecimal totalSpend){
       return withTotalSpend(Operator.LESS_THAN_OR_EQUAL, totalSpend);
    }

    public MarketingRoiRequest<T> withTotalSpendBetween(BigDecimal startOfTotalSpend, BigDecimal endOfTotalSpend){
       return withTotalSpend(Operator.BETWEEN, startOfTotalSpend, endOfTotalSpend);
    }



    public MarketingRoiRequest<T> filterByTotalRevenue(BigDecimal... totalRevenue){
      if (totalRevenue == null || totalRevenue.length == 0) {
        throw new IllegalArgumentException("filterByTotalRevenue parameter totalRevenue cannot be empty");
      }
      return appendSearchCriteria(createTotalRevenueCriteria(Operator.EQUAL, (Object[])totalRevenue));
    }

    public MarketingRoiRequest<T> withTotalRevenue(Operator operator, Object... values){
       return appendSearchCriteria(createTotalRevenueCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withTotalRevenueIsUnknown(){
       return withTotalRevenue(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withTotalRevenueIsKnown(){
       return withTotalRevenue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalRevenueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.TOTAL_REVENUE_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withTotalRevenueGreaterThan(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.GREATER_THAN, totalRevenue);
    }

    public MarketingRoiRequest<T> withTotalRevenueGreaterThanOrEqualTo(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.GREATER_THAN_OR_EQUAL, totalRevenue);
    }

    public MarketingRoiRequest<T> withTotalRevenueLessThan(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.LESS_THAN, totalRevenue);
    }

    public MarketingRoiRequest<T> withTotalRevenueLessThanOrEqualTo(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.LESS_THAN_OR_EQUAL, totalRevenue);
    }

    public MarketingRoiRequest<T> withTotalRevenueBetween(BigDecimal startOfTotalRevenue, BigDecimal endOfTotalRevenue){
       return withTotalRevenue(Operator.BETWEEN, startOfTotalRevenue, endOfTotalRevenue);
    }



    public MarketingRoiRequest<T> filterByRoiPercentage(BigDecimal... roiPercentage){
      if (roiPercentage == null || roiPercentage.length == 0) {
        throw new IllegalArgumentException("filterByRoiPercentage parameter roiPercentage cannot be empty");
      }
      return appendSearchCriteria(createRoiPercentageCriteria(Operator.EQUAL, (Object[])roiPercentage));
    }

    public MarketingRoiRequest<T> withRoiPercentage(Operator operator, Object... values){
       return appendSearchCriteria(createRoiPercentageCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withRoiPercentageIsUnknown(){
       return withRoiPercentage(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withRoiPercentageIsKnown(){
       return withRoiPercentage(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRoiPercentageCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.ROI_PERCENTAGE_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withRoiPercentageGreaterThan(BigDecimal roiPercentage){
       return withRoiPercentage(Operator.GREATER_THAN, roiPercentage);
    }

    public MarketingRoiRequest<T> withRoiPercentageGreaterThanOrEqualTo(BigDecimal roiPercentage){
       return withRoiPercentage(Operator.GREATER_THAN_OR_EQUAL, roiPercentage);
    }

    public MarketingRoiRequest<T> withRoiPercentageLessThan(BigDecimal roiPercentage){
       return withRoiPercentage(Operator.LESS_THAN, roiPercentage);
    }

    public MarketingRoiRequest<T> withRoiPercentageLessThanOrEqualTo(BigDecimal roiPercentage){
       return withRoiPercentage(Operator.LESS_THAN_OR_EQUAL, roiPercentage);
    }

    public MarketingRoiRequest<T> withRoiPercentageBetween(BigDecimal startOfRoiPercentage, BigDecimal endOfRoiPercentage){
       return withRoiPercentage(Operator.BETWEEN, startOfRoiPercentage, endOfRoiPercentage);
    }



    public MarketingRoiRequest<T> filterByReportDate(LocalDate... reportDate){
      if (reportDate == null || reportDate.length == 0) {
        throw new IllegalArgumentException("filterByReportDate parameter reportDate cannot be empty");
      }
      return appendSearchCriteria(createReportDateCriteria(Operator.EQUAL, (Object[])reportDate));
    }

    public MarketingRoiRequest<T> withReportDate(Operator operator, Object... values){
       return appendSearchCriteria(createReportDateCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withReportDateIsUnknown(){
       return withReportDate(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withReportDateIsKnown(){
       return withReportDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createReportDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.REPORT_DATE_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withReportDateGreaterThan(LocalDate reportDate){
       return withReportDate(Operator.GREATER_THAN, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateGreaterThanOrEqualTo(LocalDate reportDate){
       return withReportDate(Operator.GREATER_THAN_OR_EQUAL, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateLessThan(LocalDate reportDate){
       return withReportDate(Operator.LESS_THAN, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateLessThanOrEqualTo(LocalDate reportDate){
       return withReportDate(Operator.LESS_THAN_OR_EQUAL, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateBetween(LocalDate startOfReportDate, LocalDate endOfReportDate){
       return withReportDate(Operator.BETWEEN, startOfReportDate, endOfReportDate);
    }
    public MarketingRoiRequest<T> withReportDateBefore(LocalDate reportDate){
       return withReportDate(Operator.LESS_THAN, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateBefore(Date reportDate){
       return withReportDate(Operator.LESS_THAN, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateAfter(LocalDate reportDate){
       return withReportDate(Operator.GREATER_THAN, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateAfter(Date reportDate){
       return withReportDate(Operator.GREATER_THAN, reportDate);
    }

    public MarketingRoiRequest<T> withReportDateBetween(Date startOfReportDate, Date endOfReportDate){
       return withReportDate(Operator.BETWEEN, startOfReportDate, endOfReportDate);
    }




    public MarketingRoiRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public MarketingRoiRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.CREATED_TIME_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public MarketingRoiRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public MarketingRoiRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public MarketingRoiRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public MarketingRoiRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.UPDATE_TIME_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public MarketingRoiRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MarketingRoiRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public MarketingRoiRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public MarketingRoiRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.VERSION_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public MarketingRoiRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public MarketingRoiRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public MarketingRoiRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public MarketingRoiRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public MarketingRoiRequest<T> count(){
        super.count();
        return this;
    }
    public MarketingRoiRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public MarketingRoiRequest minTotalSpend(){
        return minTotalSpendAs(prefix("minOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest minTotalSpendAs(String retName){
        super.min(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest maxTotalSpend(){
        return maxTotalSpendAs(prefix("maxOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest maxTotalSpendAs(String retName){
        super.max(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sumTotalSpend(){
        return sumTotalSpendAs(prefix("sumOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest sumTotalSpendAs(String retName){
        super.sum(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest avgTotalSpend(){
        return avgTotalSpendAs(prefix("avgOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest avgTotalSpendAs(String retName){
        super.avg(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest standardDeviationTotalSpend(){
        return standardDeviationTotalSpendAs(prefix("standardDeviationOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest standardDeviationTotalSpendAs(String retName){
        super.standardDeviation(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest squareRootOfPopulationStandardDeviationTotalSpend(){
        return squareRootOfPopulationStandardDeviationTotalSpendAs(prefix("squareRootOfPopulationStandardDeviationOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest squareRootOfPopulationStandardDeviationTotalSpendAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sampleVarianceTotalSpend(){
        return sampleVarianceTotalSpendAs(prefix("sampleVarianceOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest sampleVarianceTotalSpendAs(String retName){
        super.sampleVariance(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest samplePopulationVarianceTotalSpend(){
        return samplePopulationVarianceTotalSpendAs(prefix("samplePopulationVarianceOf",MarketingRoi.TOTAL_SPEND_PROPERTY));
    }

    public MarketingRoiRequest samplePopulationVarianceTotalSpendAs(String retName){
        super.samplePopulationVariance(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest minTotalRevenue(){
        return minTotalRevenueAs(prefix("minOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest minTotalRevenueAs(String retName){
        super.min(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest maxTotalRevenue(){
        return maxTotalRevenueAs(prefix("maxOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest maxTotalRevenueAs(String retName){
        super.max(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sumTotalRevenue(){
        return sumTotalRevenueAs(prefix("sumOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest sumTotalRevenueAs(String retName){
        super.sum(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest avgTotalRevenue(){
        return avgTotalRevenueAs(prefix("avgOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest avgTotalRevenueAs(String retName){
        super.avg(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest standardDeviationTotalRevenue(){
        return standardDeviationTotalRevenueAs(prefix("standardDeviationOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest standardDeviationTotalRevenueAs(String retName){
        super.standardDeviation(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest squareRootOfPopulationStandardDeviationTotalRevenue(){
        return squareRootOfPopulationStandardDeviationTotalRevenueAs(prefix("squareRootOfPopulationStandardDeviationOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest squareRootOfPopulationStandardDeviationTotalRevenueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sampleVarianceTotalRevenue(){
        return sampleVarianceTotalRevenueAs(prefix("sampleVarianceOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest sampleVarianceTotalRevenueAs(String retName){
        super.sampleVariance(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest samplePopulationVarianceTotalRevenue(){
        return samplePopulationVarianceTotalRevenueAs(prefix("samplePopulationVarianceOf",MarketingRoi.TOTAL_REVENUE_PROPERTY));
    }

    public MarketingRoiRequest samplePopulationVarianceTotalRevenueAs(String retName){
        super.samplePopulationVariance(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest minRoiPercentage(){
        return minRoiPercentageAs(prefix("minOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest minRoiPercentageAs(String retName){
        super.min(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest maxRoiPercentage(){
        return maxRoiPercentageAs(prefix("maxOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest maxRoiPercentageAs(String retName){
        super.max(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sumRoiPercentage(){
        return sumRoiPercentageAs(prefix("sumOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest sumRoiPercentageAs(String retName){
        super.sum(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest avgRoiPercentage(){
        return avgRoiPercentageAs(prefix("avgOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest avgRoiPercentageAs(String retName){
        super.avg(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest standardDeviationRoiPercentage(){
        return standardDeviationRoiPercentageAs(prefix("standardDeviationOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest standardDeviationRoiPercentageAs(String retName){
        super.standardDeviation(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest squareRootOfPopulationStandardDeviationRoiPercentage(){
        return squareRootOfPopulationStandardDeviationRoiPercentageAs(prefix("squareRootOfPopulationStandardDeviationOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest squareRootOfPopulationStandardDeviationRoiPercentageAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sampleVarianceRoiPercentage(){
        return sampleVarianceRoiPercentageAs(prefix("sampleVarianceOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest sampleVarianceRoiPercentageAs(String retName){
        super.sampleVariance(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest samplePopulationVarianceRoiPercentage(){
        return samplePopulationVarianceRoiPercentageAs(prefix("samplePopulationVarianceOf",MarketingRoi.ROI_PERCENTAGE_PROPERTY));
    }

    public MarketingRoiRequest samplePopulationVarianceRoiPercentageAs(String retName){
        super.samplePopulationVariance(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest<T> groupByCampaignWithDetails(){
       return groupByCampaignWithDetails(Q.promotionCampaigns().unlimited());
    }

    public MarketingRoiRequest<T> groupByCampaignWithDetails(PromotionCampaignRequest subRequest){
       aggregate(MarketingRoi.CAMPAIGN_PROPERTY, subRequest);
       return this;
    }









    public MarketingRoiRequest<T> groupById(){
       groupBy(MarketingRoi.ID_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByIdAs(String retName){
       groupBy(retName, MarketingRoi.ID_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.ID_PROPERTY, function);
       return this;
    }
    public MarketingRoiRequest<T> groupByCampaignWith(PromotionCampaignRequest subRequest){
       groupBy(MarketingRoi.CAMPAIGN_PROPERTY, subRequest);
       return this;
    }
    public MarketingRoiRequest<T> groupByCampaign(){
       groupBy(MarketingRoi.CAMPAIGN_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByCampaignAs(String retName){
       groupBy(retName, MarketingRoi.CAMPAIGN_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByCampaignWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.CAMPAIGN_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByTotalSpend(){
       groupBy(MarketingRoi.TOTAL_SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByTotalSpendAs(String retName){
       groupBy(retName, MarketingRoi.TOTAL_SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByTotalSpendWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.TOTAL_SPEND_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByTotalRevenue(){
       groupBy(MarketingRoi.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByTotalRevenueAs(String retName){
       groupBy(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByTotalRevenueWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.TOTAL_REVENUE_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByRoiPercentage(){
       groupBy(MarketingRoi.ROI_PERCENTAGE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByRoiPercentageAs(String retName){
       groupBy(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByRoiPercentageWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.ROI_PERCENTAGE_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByReportDate(){
       groupBy(MarketingRoi.REPORT_DATE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByReportDateAs(String retName){
       groupBy(retName, MarketingRoi.REPORT_DATE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByReportDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.REPORT_DATE_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByCreatedTime(){
       groupBy(MarketingRoi.CREATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, MarketingRoi.CREATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByUpdateTime(){
       groupBy(MarketingRoi.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, MarketingRoi.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByVersion(){
       groupBy(MarketingRoi.VERSION_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByVersionAs(String retName){
       groupBy(retName, MarketingRoi.VERSION_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.VERSION_PROPERTY, function);
       return this;
    }



    public MarketingRoiRequest<T> orderByIdAscending(){
       addOrderByAscending(MarketingRoi.ID_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByIdDescending(){
       addOrderByDescending(MarketingRoi.ID_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByCampaignAscending(){
       addOrderByAscending(MarketingRoi.CAMPAIGN_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByCampaignDescending(){
       addOrderByDescending(MarketingRoi.CAMPAIGN_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByTotalSpendAscending(){
       addOrderByAscending(MarketingRoi.TOTAL_SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByTotalSpendDescending(){
       addOrderByDescending(MarketingRoi.TOTAL_SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByTotalRevenueAscending(){
       addOrderByAscending(MarketingRoi.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByTotalRevenueDescending(){
       addOrderByDescending(MarketingRoi.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByRoiPercentageAscending(){
       addOrderByAscending(MarketingRoi.ROI_PERCENTAGE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByRoiPercentageDescending(){
       addOrderByDescending(MarketingRoi.ROI_PERCENTAGE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByReportDateAscending(){
       addOrderByAscending(MarketingRoi.REPORT_DATE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByReportDateDescending(){
       addOrderByDescending(MarketingRoi.REPORT_DATE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(MarketingRoi.CREATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(MarketingRoi.CREATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(MarketingRoi.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(MarketingRoi.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByVersionAscending(){
       addOrderByAscending(MarketingRoi.VERSION_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByVersionDescending(){
       addOrderByDescending(MarketingRoi.VERSION_PROPERTY);
       return this;
    }


    public PromotionCampaignRequest rollUpToCampaign(){
       PromotionCampaignRequest campaign = Q.promotionCampaigns().unlimited();
       this.withCampaignMatching(campaign)
           .groupByCampaignWith(campaign);
       return campaign;
    }









   public MarketingRoiRequest<T> facetByCampaignAs(String facetName, PromotionCampaignRequest campaign){
       return facetByCampaignAs(facetName, campaign, true);
   }

   public MarketingRoiRequest<T> facetByCampaignAs(String facetName, PromotionCampaignRequest campaign, boolean includeAllFacets){
       addFacet(facetName, MarketingRoi.CAMPAIGN_PROPERTY, campaign, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public MarketingRoiRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public MarketingRoiRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public MarketingRoiRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public MarketingRoiRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public MarketingRoiRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}