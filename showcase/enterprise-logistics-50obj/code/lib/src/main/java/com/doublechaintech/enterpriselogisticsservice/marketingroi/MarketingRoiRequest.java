package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignRequest;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelRequest;
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
        return selectId().selectCampaignIdOnly().selectChannelIdOnly().selectSpend().selectRevenue().selectRoiPercentage().selectReportDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public MarketingRoiRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public MarketingRoiRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectCampaign().selectChannel().selectSpend().selectRevenue().selectRoiPercentage().selectReportDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public MarketingRoiRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectCampaign().selectChannel().selectSpend().selectRevenue().selectRoiPercentage().selectReportDate().selectCreatedTime().selectUpdatedTime().selectVersion();
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
    public MarketingRoiRequest<T> selectChannelIdOnly(){
       selectProperty(MarketingRoi.CHANNEL_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> selectChannel(){
        return selectChannelWith(Q.salesChannels().unlimited().selectSelf());
    }

    public MarketingRoiRequest<T> selectChannelWith(SalesChannelRequest channel){
       selectProperty(MarketingRoi.CHANNEL_PROPERTY);
       enhanceRelation(MarketingRoi.CHANNEL_PROPERTY, channel);
       return this;
    }

    public MarketingRoiRequest<T> unselectChannel(){
       unselectProperty(MarketingRoi.CHANNEL_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectSpend(){
       selectProperty(MarketingRoi.SPEND_PROPERTY);
       return this;
    }

    /**
     * fill the spend with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  spend) to fetch spend property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the spend with customized aggrFunction, TEAQL uses ({aggrFunction}(spend) AS spend to fetch spend property.
     * @param aggrFunction  aggrFunction
     */
    public MarketingRoiRequest<T> selectSpend(AggrFunction aggrFunction){
       selectProperty(MarketingRoi.SPEND_PROPERTY, aggrFunction);
       return this;
    }


    public MarketingRoiRequest<T> unselectSpend(){
       unselectProperty(MarketingRoi.SPEND_PROPERTY);
       return this;
    }
    public MarketingRoiRequest<T> selectRevenue(){
       selectProperty(MarketingRoi.REVENUE_PROPERTY);
       return this;
    }

    /**
     * fill the revenue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  revenue) to fetch revenue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the revenue with customized aggrFunction, TEAQL uses ({aggrFunction}(revenue) AS revenue to fetch revenue property.
     * @param aggrFunction  aggrFunction
     */
    public MarketingRoiRequest<T> selectRevenue(AggrFunction aggrFunction){
       selectProperty(MarketingRoi.REVENUE_PROPERTY, aggrFunction);
       return this;
    }


    public MarketingRoiRequest<T> unselectRevenue(){
       unselectProperty(MarketingRoi.REVENUE_PROPERTY);
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
    public MarketingRoiRequest<T> selectUpdatedTime(){
       selectProperty(MarketingRoi.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MarketingRoiRequest<T> unselectUpdatedTime(){
       unselectProperty(MarketingRoi.UPDATED_TIME_PROPERTY);
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

    public MarketingRoiRequest<T> filterByChannel(SalesChannel... channel){
      if (channel == null || channel.length == 0) {
        throw new IllegalArgumentException("filterByChannel parameter channel cannot be empty");
      }
      return appendSearchCriteria(createChannelCriteria(Operator.EQUAL, (Object[])channel));
    }

    public MarketingRoiRequest<T> withChannel(Operator operator, Object... values){
       return appendSearchCriteria(createChannelCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withChannelIsUnknown(){
       return withChannel(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withChannelIsKnown(){
       return withChannel(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createChannelCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.CHANNEL_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> filterByChannel(Long channel){
      if(channel == null){
         return this;
      }
      return withChannel(Operator.EQUAL, channel);
    }
    public MarketingRoiRequest<T> withChannelMatching(SalesChannelRequest channel){
       return appendSearchCriteria(new SubQuerySearchCriteria(MarketingRoi.CHANNEL_PROPERTY, channel, SalesChannel.ID_PROPERTY));
    }

    public MarketingRoiRequest<T> filterBySpend(BigDecimal... spend){
      if (spend == null || spend.length == 0) {
        throw new IllegalArgumentException("filterBySpend parameter spend cannot be empty");
      }
      return appendSearchCriteria(createSpendCriteria(Operator.EQUAL, (Object[])spend));
    }

    public MarketingRoiRequest<T> withSpend(Operator operator, Object... values){
       return appendSearchCriteria(createSpendCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withSpendIsUnknown(){
       return withSpend(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withSpendIsKnown(){
       return withSpend(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createSpendCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.SPEND_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withSpendGreaterThan(BigDecimal spend){
       return withSpend(Operator.GREATER_THAN, spend);
    }

    public MarketingRoiRequest<T> withSpendGreaterThanOrEqualTo(BigDecimal spend){
       return withSpend(Operator.GREATER_THAN_OR_EQUAL, spend);
    }

    public MarketingRoiRequest<T> withSpendLessThan(BigDecimal spend){
       return withSpend(Operator.LESS_THAN, spend);
    }

    public MarketingRoiRequest<T> withSpendLessThanOrEqualTo(BigDecimal spend){
       return withSpend(Operator.LESS_THAN_OR_EQUAL, spend);
    }

    public MarketingRoiRequest<T> withSpendBetween(BigDecimal startOfSpend, BigDecimal endOfSpend){
       return withSpend(Operator.BETWEEN, startOfSpend, endOfSpend);
    }



    public MarketingRoiRequest<T> filterByRevenue(BigDecimal... revenue){
      if (revenue == null || revenue.length == 0) {
        throw new IllegalArgumentException("filterByRevenue parameter revenue cannot be empty");
      }
      return appendSearchCriteria(createRevenueCriteria(Operator.EQUAL, (Object[])revenue));
    }

    public MarketingRoiRequest<T> withRevenue(Operator operator, Object... values){
       return appendSearchCriteria(createRevenueCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withRevenueIsUnknown(){
       return withRevenue(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withRevenueIsKnown(){
       return withRevenue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRevenueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.REVENUE_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withRevenueGreaterThan(BigDecimal revenue){
       return withRevenue(Operator.GREATER_THAN, revenue);
    }

    public MarketingRoiRequest<T> withRevenueGreaterThanOrEqualTo(BigDecimal revenue){
       return withRevenue(Operator.GREATER_THAN_OR_EQUAL, revenue);
    }

    public MarketingRoiRequest<T> withRevenueLessThan(BigDecimal revenue){
       return withRevenue(Operator.LESS_THAN, revenue);
    }

    public MarketingRoiRequest<T> withRevenueLessThanOrEqualTo(BigDecimal revenue){
       return withRevenue(Operator.LESS_THAN_OR_EQUAL, revenue);
    }

    public MarketingRoiRequest<T> withRevenueBetween(BigDecimal startOfRevenue, BigDecimal endOfRevenue){
       return withRevenue(Operator.BETWEEN, startOfRevenue, endOfRevenue);
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




    public MarketingRoiRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public MarketingRoiRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public MarketingRoiRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public MarketingRoiRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MarketingRoi.UPDATED_TIME_PROPERTY, operator, values);
    }

    public MarketingRoiRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public MarketingRoiRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public MarketingRoiRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
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
    public MarketingRoiRequest minSpend(){
        return minSpendAs(prefix("minOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest minSpendAs(String retName){
        super.min(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest maxSpend(){
        return maxSpendAs(prefix("maxOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest maxSpendAs(String retName){
        super.max(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sumSpend(){
        return sumSpendAs(prefix("sumOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest sumSpendAs(String retName){
        super.sum(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest avgSpend(){
        return avgSpendAs(prefix("avgOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest avgSpendAs(String retName){
        super.avg(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest standardDeviationSpend(){
        return standardDeviationSpendAs(prefix("standardDeviationOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest standardDeviationSpendAs(String retName){
        super.standardDeviation(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest squareRootOfPopulationStandardDeviationSpend(){
        return squareRootOfPopulationStandardDeviationSpendAs(prefix("squareRootOfPopulationStandardDeviationOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest squareRootOfPopulationStandardDeviationSpendAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sampleVarianceSpend(){
        return sampleVarianceSpendAs(prefix("sampleVarianceOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest sampleVarianceSpendAs(String retName){
        super.sampleVariance(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest samplePopulationVarianceSpend(){
        return samplePopulationVarianceSpendAs(prefix("samplePopulationVarianceOf",MarketingRoi.SPEND_PROPERTY));
    }

    public MarketingRoiRequest samplePopulationVarianceSpendAs(String retName){
        super.samplePopulationVariance(retName, MarketingRoi.SPEND_PROPERTY);
        return this;
    }
    public MarketingRoiRequest minRevenue(){
        return minRevenueAs(prefix("minOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest minRevenueAs(String retName){
        super.min(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest maxRevenue(){
        return maxRevenueAs(prefix("maxOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest maxRevenueAs(String retName){
        super.max(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sumRevenue(){
        return sumRevenueAs(prefix("sumOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest sumRevenueAs(String retName){
        super.sum(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest avgRevenue(){
        return avgRevenueAs(prefix("avgOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest avgRevenueAs(String retName){
        super.avg(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest standardDeviationRevenue(){
        return standardDeviationRevenueAs(prefix("standardDeviationOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest standardDeviationRevenueAs(String retName){
        super.standardDeviation(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest squareRootOfPopulationStandardDeviationRevenue(){
        return squareRootOfPopulationStandardDeviationRevenueAs(prefix("squareRootOfPopulationStandardDeviationOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest squareRootOfPopulationStandardDeviationRevenueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest sampleVarianceRevenue(){
        return sampleVarianceRevenueAs(prefix("sampleVarianceOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest sampleVarianceRevenueAs(String retName){
        super.sampleVariance(retName, MarketingRoi.REVENUE_PROPERTY);
        return this;
    }
    public MarketingRoiRequest samplePopulationVarianceRevenue(){
        return samplePopulationVarianceRevenueAs(prefix("samplePopulationVarianceOf",MarketingRoi.REVENUE_PROPERTY));
    }

    public MarketingRoiRequest samplePopulationVarianceRevenueAs(String retName){
        super.samplePopulationVariance(retName, MarketingRoi.REVENUE_PROPERTY);
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

    public MarketingRoiRequest<T> groupByChannelWithDetails(){
       return groupByChannelWithDetails(Q.salesChannels().unlimited());
    }

    public MarketingRoiRequest<T> groupByChannelWithDetails(SalesChannelRequest subRequest){
       aggregate(MarketingRoi.CHANNEL_PROPERTY, subRequest);
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
    public MarketingRoiRequest<T> groupByChannelWith(SalesChannelRequest subRequest){
       groupBy(MarketingRoi.CHANNEL_PROPERTY, subRequest);
       return this;
    }
    public MarketingRoiRequest<T> groupByChannel(){
       groupBy(MarketingRoi.CHANNEL_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByChannelAs(String retName){
       groupBy(retName, MarketingRoi.CHANNEL_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByChannelWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.CHANNEL_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupBySpend(){
       groupBy(MarketingRoi.SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupBySpendAs(String retName){
       groupBy(retName, MarketingRoi.SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupBySpendWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.SPEND_PROPERTY, function);
       return this;
    }

    public MarketingRoiRequest<T> groupByRevenue(){
       groupBy(MarketingRoi.REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByRevenueAs(String retName){
       groupBy(retName, MarketingRoi.REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByRevenueWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.REVENUE_PROPERTY, function);
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

    public MarketingRoiRequest<T> groupByUpdatedTime(){
       groupBy(MarketingRoi.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, MarketingRoi.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MarketingRoi.UPDATED_TIME_PROPERTY, function);
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

    public MarketingRoiRequest<T> orderByChannelAscending(){
       addOrderByAscending(MarketingRoi.CHANNEL_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByChannelDescending(){
       addOrderByDescending(MarketingRoi.CHANNEL_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderBySpendAscending(){
       addOrderByAscending(MarketingRoi.SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderBySpendDescending(){
       addOrderByDescending(MarketingRoi.SPEND_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByRevenueAscending(){
       addOrderByAscending(MarketingRoi.REVENUE_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByRevenueDescending(){
       addOrderByDescending(MarketingRoi.REVENUE_PROPERTY);
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

    public MarketingRoiRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(MarketingRoi.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MarketingRoiRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(MarketingRoi.UPDATED_TIME_PROPERTY);
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

    public SalesChannelRequest rollUpToChannel(){
       SalesChannelRequest channel = Q.salesChannels().unlimited();
       this.withChannelMatching(channel)
           .groupByChannelWith(channel);
       return channel;
    }









   public MarketingRoiRequest<T> facetByCampaignAs(String facetName, PromotionCampaignRequest campaign){
       return facetByCampaignAs(facetName, campaign, true);
   }

   public MarketingRoiRequest<T> facetByCampaignAs(String facetName, PromotionCampaignRequest campaign, boolean includeAllFacets){
       addFacet(facetName, MarketingRoi.CAMPAIGN_PROPERTY, campaign, includeAllFacets);
       return this;
   }
   public MarketingRoiRequest<T> facetByChannelAs(String facetName, SalesChannelRequest channel){
       return facetByChannelAs(facetName, channel, true);
   }

   public MarketingRoiRequest<T> facetByChannelAs(String facetName, SalesChannelRequest channel, boolean includeAllFacets){
       addFacet(facetName, MarketingRoi.CHANNEL_PROPERTY, channel, includeAllFacets);
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