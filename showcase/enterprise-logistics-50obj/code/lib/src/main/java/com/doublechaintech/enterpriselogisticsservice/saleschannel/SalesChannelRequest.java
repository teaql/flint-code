package com.doublechaintech.enterpriselogisticsservice.saleschannel;

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
import java.time.LocalDateTime;
import java.util.Date;

public class SalesChannelRequest<T extends SalesChannel> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public SalesChannelRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public SalesChannelRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public SalesChannelRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public SalesChannelRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public SalesChannelRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public SalesChannelRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public SalesChannelRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (SalesChannelRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public SalesChannelRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public SalesChannelRequest<T> matchingAnyOf(SalesChannelRequest salesChannel){
        super.internalMatchAny(salesChannel);
        return this;
    }

    public SalesChannelRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public SalesChannelRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public SalesChannelRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public SalesChannelRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectDescription().selectChannelType().selectIsActive().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public SalesChannelRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SalesChannelRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectDescription().selectChannelType().selectIsActive().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public SalesChannelRequest<T> selectChildren(){
        super.selectAny();
        selectMarketingRoiList();
        return selectId().selectName().selectDescription().selectChannelType().selectIsActive().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public SalesChannelRequest<T> selectId(){
       selectProperty(SalesChannel.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectId(){
       unselectProperty(SalesChannel.ID_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectName(){
       selectProperty(SalesChannel.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectName(){
       unselectProperty(SalesChannel.NAME_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectDescription(){
       selectProperty(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectDescription(){
       unselectProperty(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectChannelType(){
       selectProperty(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the channelType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  channelType) to fetch channelType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectChannelType(){
       unselectProperty(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectIsActive(){
       selectProperty(SalesChannel.IS_ACTIVE_PROPERTY);
       return this;
    }

    /**
     * fill the isActive with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  isActive) to fetch isActive property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectIsActive(){
       unselectProperty(SalesChannel.IS_ACTIVE_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectCreatedTime(){
       selectProperty(SalesChannel.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectCreatedTime(){
       unselectProperty(SalesChannel.CREATED_TIME_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectUpdatedTime(){
       selectProperty(SalesChannel.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectUpdatedTime(){
       unselectProperty(SalesChannel.UPDATED_TIME_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectVersion(){
       selectProperty(SalesChannel.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectVersion(){
       unselectProperty(SalesChannel.VERSION_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectMarketingRoiList(){
       return selectMarketingRoiListWith(Q.marketingRois().selectSelf());
    }

    public SalesChannelRequest<T> selectMarketingRoiListWith(MarketingRoiRequest marketingRoiList){
       enhanceRelation(SalesChannel.MARKETING_ROI_LIST_PROPERTY, marketingRoiList);
       return this;
    }

    public SalesChannelRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.ID_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public SalesChannelRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public SalesChannelRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public SalesChannelRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public SalesChannelRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.NAME_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public SalesChannelRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public SalesChannelRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public SalesChannelRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public SalesChannelRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public SalesChannelRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public SalesChannelRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public SalesChannelRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public SalesChannelRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public SalesChannelRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public SalesChannelRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public SalesChannelRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public SalesChannelRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.DESCRIPTION_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public SalesChannelRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public SalesChannelRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public SalesChannelRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public SalesChannelRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public SalesChannelRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public SalesChannelRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public SalesChannelRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public SalesChannelRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public SalesChannelRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
    }



    public SalesChannelRequest<T> filterByChannelType(String... channelType){
      if (channelType == null || channelType.length == 0) {
        throw new IllegalArgumentException("filterByChannelType parameter channelType cannot be empty");
      }
      return appendSearchCriteria(createChannelTypeCriteria(Operator.EQUAL, (Object[])channelType));
    }

    public SalesChannelRequest<T> withChannelType(Operator operator, Object... values){
       return appendSearchCriteria(createChannelTypeCriteria(operator, values));
    }

    public SalesChannelRequest<T> withChannelTypeIsUnknown(){
       return withChannelType(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withChannelTypeIsKnown(){
       return withChannelType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createChannelTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.CHANNEL_TYPE_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withChannelTypeGreaterThan(String channelType){
       return withChannelType(Operator.GREATER_THAN, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeGreaterThanOrEqualTo(String channelType){
       return withChannelType(Operator.GREATER_THAN_OR_EQUAL, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeLessThan(String channelType){
       return withChannelType(Operator.LESS_THAN, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeLessThanOrEqualTo(String channelType){
       return withChannelType(Operator.LESS_THAN_OR_EQUAL, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeBetween(String startOfChannelType, String endOfChannelType){
       return withChannelType(Operator.BETWEEN, startOfChannelType, endOfChannelType);
    }
    public SalesChannelRequest<T> withChannelTypeStartingWith(String channelType){
       return withChannelType(Operator.BEGIN_WITH, channelType);
    }
    public SalesChannelRequest<T> withChannelTypeContaining(String channelType){
       return withChannelType(Operator.CONTAIN, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeEndingWith(String channelType){
       return withChannelType(Operator.END_WITH, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeIs(String channelType){
       return withChannelType(Operator.EQUAL, channelType);
    }

    public SalesChannelRequest<T> withChannelTypeSoundingLike(String channelType){
       return withChannelType(Operator.SOUNDS_LIKE, channelType);
    }



    public SalesChannelRequest<T> filterByIsActive(Boolean... isActive){
      if (isActive == null || isActive.length == 0) {
        throw new IllegalArgumentException("filterByIsActive parameter isActive cannot be empty");
      }
      return appendSearchCriteria(createIsActiveCriteria(Operator.EQUAL, (Object[])isActive));
    }

    public SalesChannelRequest<T> withIsActive(Operator operator, Object... values){
       return appendSearchCriteria(createIsActiveCriteria(operator, values));
    }

    public SalesChannelRequest<T> withIsActiveIsUnknown(){
       return withIsActive(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withIsActiveIsKnown(){
       return withIsActive(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIsActiveCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.IS_ACTIVE_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> whichIsIsActive(){
       return withIsActive(Operator.EQUAL, true);
    }

    public SalesChannelRequest<T> whichIsNotIsActive(){
       return withIsActive(Operator.EQUAL, false);
    }


    public SalesChannelRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public SalesChannelRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public SalesChannelRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.CREATED_TIME_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public SalesChannelRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public SalesChannelRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public SalesChannelRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public SalesChannelRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public SalesChannelRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.UPDATED_TIME_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public SalesChannelRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public SalesChannelRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public SalesChannelRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public SalesChannelRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public SalesChannelRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.VERSION_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public SalesChannelRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public SalesChannelRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public SalesChannelRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public SalesChannelRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public SalesChannelRequest<T> withMarketingRoiListMatching(MarketingRoiRequest marketingRoiRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(SalesChannel.ID_PROPERTY, marketingRoiRequest, MarketingRoi.CHANNEL_PROPERTY));
    }

    public SalesChannelRequest<T> withoutMarketingRoiListMatching(MarketingRoiRequest marketingRoiRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(SalesChannel.ID_PROPERTY, marketingRoiRequest, MarketingRoi.CHANNEL_PROPERTY)));
    }

    public SalesChannelRequest<T> haveMarketingRois(){
        return withMarketingRoiListMatching(Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> haveNoMarketingRois(){
        return withoutMarketingRoiListMatching(Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> count(){
        super.count();
        return this;
    }
    public SalesChannelRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public SalesChannelRequest<T> groupByMarketingRoisWithDetails(MarketingRoiRequest subRequest){
       aggregate(SalesChannel.MARKETING_ROI_LIST_PROPERTY, subRequest);
       return this;
    }

    public SalesChannelRequest<T> groupById(){
       groupBy(SalesChannel.ID_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByIdAs(String retName){
       groupBy(retName, SalesChannel.ID_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.ID_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByName(){
       groupBy(SalesChannel.NAME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByNameAs(String retName){
       groupBy(retName, SalesChannel.NAME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.NAME_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByDescription(){
       groupBy(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.DESCRIPTION_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByChannelType(){
       groupBy(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByChannelTypeAs(String retName){
       groupBy(retName, SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByChannelTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.CHANNEL_TYPE_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByIsActive(){
       groupBy(SalesChannel.IS_ACTIVE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByIsActiveAs(String retName){
       groupBy(retName, SalesChannel.IS_ACTIVE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByIsActiveWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.IS_ACTIVE_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByCreatedTime(){
       groupBy(SalesChannel.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, SalesChannel.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByUpdatedTime(){
       groupBy(SalesChannel.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, SalesChannel.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByVersion(){
       groupBy(SalesChannel.VERSION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByVersionAs(String retName){
       groupBy(retName, SalesChannel.VERSION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.VERSION_PROPERTY, function);
       return this;
    }



    public SalesChannelRequest<T> orderByIdAscending(){
       addOrderByAscending(SalesChannel.ID_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByIdDescending(){
       addOrderByDescending(SalesChannel.ID_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByNameAscending(){
       addOrderByAscending(SalesChannel.NAME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByNameDescending(){
       addOrderByDescending(SalesChannel.NAME_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesChannel.NAME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesChannel.NAME_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesChannel.DESCRIPTION_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByChannelTypeAscending(){
       addOrderByAscending(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByChannelTypeDescending(){
       addOrderByDescending(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByChannelTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByChannelTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesChannel.CHANNEL_TYPE_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByIsActiveAscending(){
       addOrderByAscending(SalesChannel.IS_ACTIVE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByIsActiveDescending(){
       addOrderByDescending(SalesChannel.IS_ACTIVE_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(SalesChannel.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(SalesChannel.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(SalesChannel.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(SalesChannel.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByVersionAscending(){
       addOrderByAscending(SalesChannel.VERSION_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByVersionDescending(){
       addOrderByDescending(SalesChannel.VERSION_PROPERTY);
       return this;
    }


    public SalesChannelRequest<T> statsFromMarketingRoisAs(String name, MarketingRoiRequest subRequest){
       return statsFromMarketingRoisAs(name, subRequest, false);
    }

    public SalesChannelRequest<T> statsFromMarketingRoisAs(String name, MarketingRoiRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(MarketingRoi.CHANNEL_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public SalesChannelRequest<T> statsFromMarketingRois(MarketingRoiRequest subRequest){
       return statsFromMarketingRoisAs(REFINEMENTS, subRequest);
    }
    public SalesChannelRequest<T> countMarketingRois(){
        return countMarketingRoisAs("Count");
    }

    public SalesChannelRequest<T> countMarketingRoisAs(String name){
        return countMarketingRoisWith(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> countMarketingRoisWith(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.count(), true);
    }
    public SalesChannelRequest<T> minSpendOfMarketingRois(){
        return minSpendOfMarketingRoisAs("minSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> minSpendOfMarketingRoisAs(String name){
        return minSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> minSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.minSpend(), true);
    }
    public SalesChannelRequest<T> maxSpendOfMarketingRois(){
        return maxSpendOfMarketingRoisAs("maxSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> maxSpendOfMarketingRoisAs(String name){
        return maxSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> maxSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.maxSpend(), true);
    }
    public SalesChannelRequest<T> sumSpendOfMarketingRois(){
        return sumSpendOfMarketingRoisAs("sumSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> sumSpendOfMarketingRoisAs(String name){
        return sumSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> sumSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sumSpend(), true);
    }
    public SalesChannelRequest<T> avgSpendOfMarketingRois(){
        return avgSpendOfMarketingRoisAs("avgSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> avgSpendOfMarketingRoisAs(String name){
        return avgSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> avgSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.avgSpend(), true);
    }
    public SalesChannelRequest<T> standardDeviationSpendOfMarketingRois(){
        return standardDeviationSpendOfMarketingRoisAs("stdDevSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> standardDeviationSpendOfMarketingRoisAs(String name){
        return standardDeviationSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> standardDeviationSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.standardDeviationSpend(), true);
    }
    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationSpendOfMarketingRois(){
        return squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs("stdDevPopSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs(String name){
        return squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.squareRootOfPopulationStandardDeviationSpend(), true);
    }
    public SalesChannelRequest<T> sampleVarianceSpendOfMarketingRois(){
        return sampleVarianceSpendOfMarketingRoisAs("varSampSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> sampleVarianceSpendOfMarketingRoisAs(String name){
        return sampleVarianceSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> sampleVarianceSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sampleVarianceSpend(), true);
    }
    public SalesChannelRequest<T> samplePopulationVarianceSpendOfMarketingRois(){
        return samplePopulationVarianceSpendOfMarketingRoisAs("varPopSpendOfMarketingRois");
    }

    public SalesChannelRequest<T> samplePopulationVarianceSpendOfMarketingRoisAs(String name){
        return samplePopulationVarianceSpendOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> samplePopulationVarianceSpendOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.samplePopulationVarianceSpend(), true);
    }
    public SalesChannelRequest<T> minRevenueOfMarketingRois(){
        return minRevenueOfMarketingRoisAs("minRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> minRevenueOfMarketingRoisAs(String name){
        return minRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> minRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.minRevenue(), true);
    }
    public SalesChannelRequest<T> maxRevenueOfMarketingRois(){
        return maxRevenueOfMarketingRoisAs("maxRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> maxRevenueOfMarketingRoisAs(String name){
        return maxRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> maxRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.maxRevenue(), true);
    }
    public SalesChannelRequest<T> sumRevenueOfMarketingRois(){
        return sumRevenueOfMarketingRoisAs("sumRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> sumRevenueOfMarketingRoisAs(String name){
        return sumRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> sumRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sumRevenue(), true);
    }
    public SalesChannelRequest<T> avgRevenueOfMarketingRois(){
        return avgRevenueOfMarketingRoisAs("avgRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> avgRevenueOfMarketingRoisAs(String name){
        return avgRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> avgRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.avgRevenue(), true);
    }
    public SalesChannelRequest<T> standardDeviationRevenueOfMarketingRois(){
        return standardDeviationRevenueOfMarketingRoisAs("stdDevRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> standardDeviationRevenueOfMarketingRoisAs(String name){
        return standardDeviationRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> standardDeviationRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.standardDeviationRevenue(), true);
    }
    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationRevenueOfMarketingRois(){
        return squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs("stdDevPopRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs(String name){
        return squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.squareRootOfPopulationStandardDeviationRevenue(), true);
    }
    public SalesChannelRequest<T> sampleVarianceRevenueOfMarketingRois(){
        return sampleVarianceRevenueOfMarketingRoisAs("varSampRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> sampleVarianceRevenueOfMarketingRoisAs(String name){
        return sampleVarianceRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> sampleVarianceRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sampleVarianceRevenue(), true);
    }
    public SalesChannelRequest<T> samplePopulationVarianceRevenueOfMarketingRois(){
        return samplePopulationVarianceRevenueOfMarketingRoisAs("varPopRevenueOfMarketingRois");
    }

    public SalesChannelRequest<T> samplePopulationVarianceRevenueOfMarketingRoisAs(String name){
        return samplePopulationVarianceRevenueOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> samplePopulationVarianceRevenueOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.samplePopulationVarianceRevenue(), true);
    }
    public SalesChannelRequest<T> minRoiPercentageOfMarketingRois(){
        return minRoiPercentageOfMarketingRoisAs("minRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> minRoiPercentageOfMarketingRoisAs(String name){
        return minRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> minRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.minRoiPercentage(), true);
    }
    public SalesChannelRequest<T> maxRoiPercentageOfMarketingRois(){
        return maxRoiPercentageOfMarketingRoisAs("maxRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> maxRoiPercentageOfMarketingRoisAs(String name){
        return maxRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> maxRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.maxRoiPercentage(), true);
    }
    public SalesChannelRequest<T> sumRoiPercentageOfMarketingRois(){
        return sumRoiPercentageOfMarketingRoisAs("sumRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> sumRoiPercentageOfMarketingRoisAs(String name){
        return sumRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> sumRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sumRoiPercentage(), true);
    }
    public SalesChannelRequest<T> avgRoiPercentageOfMarketingRois(){
        return avgRoiPercentageOfMarketingRoisAs("avgRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> avgRoiPercentageOfMarketingRoisAs(String name){
        return avgRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> avgRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.avgRoiPercentage(), true);
    }
    public SalesChannelRequest<T> standardDeviationRoiPercentageOfMarketingRois(){
        return standardDeviationRoiPercentageOfMarketingRoisAs("stdDevRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> standardDeviationRoiPercentageOfMarketingRoisAs(String name){
        return standardDeviationRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> standardDeviationRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.standardDeviationRoiPercentage(), true);
    }
    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRois(){
        return squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs("stdDevPopRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs(String name){
        return squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> squareRootOfPopulationStandardDeviationRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.squareRootOfPopulationStandardDeviationRoiPercentage(), true);
    }
    public SalesChannelRequest<T> sampleVarianceRoiPercentageOfMarketingRois(){
        return sampleVarianceRoiPercentageOfMarketingRoisAs("varSampRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> sampleVarianceRoiPercentageOfMarketingRoisAs(String name){
        return sampleVarianceRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> sampleVarianceRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.sampleVarianceRoiPercentage(), true);
    }
    public SalesChannelRequest<T> samplePopulationVarianceRoiPercentageOfMarketingRois(){
        return samplePopulationVarianceRoiPercentageOfMarketingRoisAs("varPopRoiPercentageOfMarketingRois");
    }

    public SalesChannelRequest<T> samplePopulationVarianceRoiPercentageOfMarketingRoisAs(String name){
        return samplePopulationVarianceRoiPercentageOfMarketingRoisAs(name, Q.marketingRois().unlimited());
    }

    public SalesChannelRequest<T> samplePopulationVarianceRoiPercentageOfMarketingRoisAs(String name, MarketingRoiRequest subRequest){
        return statsFromMarketingRoisAs(name, subRequest.samplePopulationVarianceRoiPercentage(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public SalesChannelRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public SalesChannelRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public SalesChannelRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public SalesChannelRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public SalesChannelRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}