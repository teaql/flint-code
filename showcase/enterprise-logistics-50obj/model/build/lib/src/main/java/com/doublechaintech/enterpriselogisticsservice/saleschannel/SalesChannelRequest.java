package com.doublechaintech.enterpriselogisticsservice.saleschannel;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
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
        return selectId().selectName().selectChannelType().selectUrl().selectStatus().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public SalesChannelRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SalesChannelRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectChannelType().selectUrl().selectStatus().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public SalesChannelRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectChannelType().selectUrl().selectStatus().selectCreatedTime().selectUpdateTime().selectVersion();
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
    public SalesChannelRequest<T> selectUrl(){
       selectProperty(SalesChannel.URL_PROPERTY);
       return this;
    }

    /**
     * fill the url with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  url) to fetch url property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectUrl(){
       unselectProperty(SalesChannel.URL_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> selectStatus(){
       selectProperty(SalesChannel.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectStatus(){
       unselectProperty(SalesChannel.STATUS_PROPERTY);
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
    public SalesChannelRequest<T> selectUpdateTime(){
       selectProperty(SalesChannel.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesChannelRequest<T> unselectUpdateTime(){
       unselectProperty(SalesChannel.UPDATE_TIME_PROPERTY);
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



    public SalesChannelRequest<T> filterByUrl(String... url){
      if (url == null || url.length == 0) {
        throw new IllegalArgumentException("filterByUrl parameter url cannot be empty");
      }
      return appendSearchCriteria(createUrlCriteria(Operator.EQUAL, (Object[])url));
    }

    public SalesChannelRequest<T> withUrl(Operator operator, Object... values){
       return appendSearchCriteria(createUrlCriteria(operator, values));
    }

    public SalesChannelRequest<T> withUrlIsUnknown(){
       return withUrl(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withUrlIsKnown(){
       return withUrl(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUrlCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.URL_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withUrlGreaterThan(String url){
       return withUrl(Operator.GREATER_THAN, url);
    }

    public SalesChannelRequest<T> withUrlGreaterThanOrEqualTo(String url){
       return withUrl(Operator.GREATER_THAN_OR_EQUAL, url);
    }

    public SalesChannelRequest<T> withUrlLessThan(String url){
       return withUrl(Operator.LESS_THAN, url);
    }

    public SalesChannelRequest<T> withUrlLessThanOrEqualTo(String url){
       return withUrl(Operator.LESS_THAN_OR_EQUAL, url);
    }

    public SalesChannelRequest<T> withUrlBetween(String startOfUrl, String endOfUrl){
       return withUrl(Operator.BETWEEN, startOfUrl, endOfUrl);
    }
    public SalesChannelRequest<T> withUrlStartingWith(String url){
       return withUrl(Operator.BEGIN_WITH, url);
    }
    public SalesChannelRequest<T> withUrlContaining(String url){
       return withUrl(Operator.CONTAIN, url);
    }

    public SalesChannelRequest<T> withUrlEndingWith(String url){
       return withUrl(Operator.END_WITH, url);
    }

    public SalesChannelRequest<T> withUrlIs(String url){
       return withUrl(Operator.EQUAL, url);
    }

    public SalesChannelRequest<T> withUrlSoundingLike(String url){
       return withUrl(Operator.SOUNDS_LIKE, url);
    }



    public SalesChannelRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public SalesChannelRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public SalesChannelRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.STATUS_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public SalesChannelRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public SalesChannelRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public SalesChannelRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public SalesChannelRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public SalesChannelRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public SalesChannelRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public SalesChannelRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public SalesChannelRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public SalesChannelRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
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




    public SalesChannelRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public SalesChannelRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public SalesChannelRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public SalesChannelRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesChannel.UPDATE_TIME_PROPERTY, operator, values);
    }

    public SalesChannelRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public SalesChannelRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public SalesChannelRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
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


    public SalesChannelRequest<T> count(){
        super.count();
        return this;
    }
    public SalesChannelRequest<T> countAs(String retName){
        super.count(retName);
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

    public SalesChannelRequest<T> groupByUrl(){
       groupBy(SalesChannel.URL_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByUrlAs(String retName){
       groupBy(retName, SalesChannel.URL_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByUrlWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.URL_PROPERTY, function);
       return this;
    }

    public SalesChannelRequest<T> groupByStatus(){
       groupBy(SalesChannel.STATUS_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByStatusAs(String retName){
       groupBy(retName, SalesChannel.STATUS_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.STATUS_PROPERTY, function);
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

    public SalesChannelRequest<T> groupByUpdateTime(){
       groupBy(SalesChannel.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, SalesChannel.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesChannel.UPDATE_TIME_PROPERTY, function);
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
    public SalesChannelRequest<T> orderByUrlAscending(){
       addOrderByAscending(SalesChannel.URL_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByUrlDescending(){
       addOrderByDescending(SalesChannel.URL_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByUrlAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesChannel.URL_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByUrlDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesChannel.URL_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByStatusAscending(){
       addOrderByAscending(SalesChannel.STATUS_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByStatusDescending(){
       addOrderByDescending(SalesChannel.STATUS_PROPERTY);
       return this;
    }
    public SalesChannelRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesChannel.STATUS_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesChannel.STATUS_PROPERTY);
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

    public SalesChannelRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(SalesChannel.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesChannelRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(SalesChannel.UPDATE_TIME_PROPERTY);
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