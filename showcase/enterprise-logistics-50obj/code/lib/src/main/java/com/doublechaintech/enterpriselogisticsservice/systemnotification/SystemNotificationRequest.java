package com.doublechaintech.enterpriselogisticsservice.systemnotification;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class SystemNotificationRequest<T extends SystemNotification> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public SystemNotificationRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public SystemNotificationRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public SystemNotificationRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public SystemNotificationRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public SystemNotificationRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public SystemNotificationRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public SystemNotificationRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (SystemNotificationRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public SystemNotificationRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public SystemNotificationRequest<T> matchingAnyOf(SystemNotificationRequest systemNotification){
        super.internalMatchAny(systemNotification);
        return this;
    }

    public SystemNotificationRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public SystemNotificationRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public SystemNotificationRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public SystemNotificationRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectNotificationType().selectTitle().selectContent().selectIsRead().selectRecipientId().selectCreatedAt().selectVersion();
    }

    public SystemNotificationRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SystemNotificationRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectNotificationType().selectTitle().selectContent().selectIsRead().selectRecipientId().selectCreatedAt().selectVersion();
    }

    public SystemNotificationRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectNotificationType().selectTitle().selectContent().selectIsRead().selectRecipientId().selectCreatedAt().selectVersion();
    }


    public SystemNotificationRequest<T> selectId(){
       selectProperty(SystemNotification.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectId(){
       unselectProperty(SystemNotification.ID_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectNotificationType(){
       selectProperty(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the notificationType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  notificationType) to fetch notificationType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectNotificationType(){
       unselectProperty(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectTitle(){
       selectProperty(SystemNotification.TITLE_PROPERTY);
       return this;
    }

    /**
     * fill the title with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  title) to fetch title property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectTitle(){
       unselectProperty(SystemNotification.TITLE_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectContent(){
       selectProperty(SystemNotification.CONTENT_PROPERTY);
       return this;
    }

    /**
     * fill the content with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  content) to fetch content property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectContent(){
       unselectProperty(SystemNotification.CONTENT_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectIsRead(){
       selectProperty(SystemNotification.IS_READ_PROPERTY);
       return this;
    }

    /**
     * fill the isRead with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  isRead) to fetch isRead property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectIsRead(){
       unselectProperty(SystemNotification.IS_READ_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectRecipientId(){
       selectProperty(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }

    /**
     * fill the recipientId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  recipientId) to fetch recipientId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectRecipientId(){
       unselectProperty(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectCreatedAt(){
       selectProperty(SystemNotification.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectCreatedAt(){
       unselectProperty(SystemNotification.CREATED_AT_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> selectVersion(){
       selectProperty(SystemNotification.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SystemNotificationRequest<T> unselectVersion(){
       unselectProperty(SystemNotification.VERSION_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.ID_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public SystemNotificationRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public SystemNotificationRequest<T> filterByNotificationType(String... notificationType){
      if (notificationType == null || notificationType.length == 0) {
        throw new IllegalArgumentException("filterByNotificationType parameter notificationType cannot be empty");
      }
      return appendSearchCriteria(createNotificationTypeCriteria(Operator.EQUAL, (Object[])notificationType));
    }

    public SystemNotificationRequest<T> withNotificationType(Operator operator, Object... values){
       return appendSearchCriteria(createNotificationTypeCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withNotificationTypeIsUnknown(){
       return withNotificationType(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withNotificationTypeIsKnown(){
       return withNotificationType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNotificationTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.NOTIFICATION_TYPE_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withNotificationTypeGreaterThan(String notificationType){
       return withNotificationType(Operator.GREATER_THAN, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeGreaterThanOrEqualTo(String notificationType){
       return withNotificationType(Operator.GREATER_THAN_OR_EQUAL, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeLessThan(String notificationType){
       return withNotificationType(Operator.LESS_THAN, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeLessThanOrEqualTo(String notificationType){
       return withNotificationType(Operator.LESS_THAN_OR_EQUAL, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeBetween(String startOfNotificationType, String endOfNotificationType){
       return withNotificationType(Operator.BETWEEN, startOfNotificationType, endOfNotificationType);
    }
    public SystemNotificationRequest<T> withNotificationTypeStartingWith(String notificationType){
       return withNotificationType(Operator.BEGIN_WITH, notificationType);
    }
    public SystemNotificationRequest<T> withNotificationTypeContaining(String notificationType){
       return withNotificationType(Operator.CONTAIN, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeEndingWith(String notificationType){
       return withNotificationType(Operator.END_WITH, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeIs(String notificationType){
       return withNotificationType(Operator.EQUAL, notificationType);
    }

    public SystemNotificationRequest<T> withNotificationTypeSoundingLike(String notificationType){
       return withNotificationType(Operator.SOUNDS_LIKE, notificationType);
    }



    public SystemNotificationRequest<T> filterByTitle(String... title){
      if (title == null || title.length == 0) {
        throw new IllegalArgumentException("filterByTitle parameter title cannot be empty");
      }
      return appendSearchCriteria(createTitleCriteria(Operator.EQUAL, (Object[])title));
    }

    public SystemNotificationRequest<T> withTitle(Operator operator, Object... values){
       return appendSearchCriteria(createTitleCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withTitleIsUnknown(){
       return withTitle(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withTitleIsKnown(){
       return withTitle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTitleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.TITLE_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withTitleGreaterThan(String title){
       return withTitle(Operator.GREATER_THAN, title);
    }

    public SystemNotificationRequest<T> withTitleGreaterThanOrEqualTo(String title){
       return withTitle(Operator.GREATER_THAN_OR_EQUAL, title);
    }

    public SystemNotificationRequest<T> withTitleLessThan(String title){
       return withTitle(Operator.LESS_THAN, title);
    }

    public SystemNotificationRequest<T> withTitleLessThanOrEqualTo(String title){
       return withTitle(Operator.LESS_THAN_OR_EQUAL, title);
    }

    public SystemNotificationRequest<T> withTitleBetween(String startOfTitle, String endOfTitle){
       return withTitle(Operator.BETWEEN, startOfTitle, endOfTitle);
    }
    public SystemNotificationRequest<T> withTitleStartingWith(String title){
       return withTitle(Operator.BEGIN_WITH, title);
    }
    public SystemNotificationRequest<T> withTitleContaining(String title){
       return withTitle(Operator.CONTAIN, title);
    }

    public SystemNotificationRequest<T> withTitleEndingWith(String title){
       return withTitle(Operator.END_WITH, title);
    }

    public SystemNotificationRequest<T> withTitleIs(String title){
       return withTitle(Operator.EQUAL, title);
    }

    public SystemNotificationRequest<T> withTitleSoundingLike(String title){
       return withTitle(Operator.SOUNDS_LIKE, title);
    }



    public SystemNotificationRequest<T> filterByContent(String... content){
      if (content == null || content.length == 0) {
        throw new IllegalArgumentException("filterByContent parameter content cannot be empty");
      }
      return appendSearchCriteria(createContentCriteria(Operator.EQUAL, (Object[])content));
    }

    public SystemNotificationRequest<T> withContent(Operator operator, Object... values){
       return appendSearchCriteria(createContentCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withContentIsUnknown(){
       return withContent(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withContentIsKnown(){
       return withContent(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContentCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.CONTENT_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withContentGreaterThan(String content){
       return withContent(Operator.GREATER_THAN, content);
    }

    public SystemNotificationRequest<T> withContentGreaterThanOrEqualTo(String content){
       return withContent(Operator.GREATER_THAN_OR_EQUAL, content);
    }

    public SystemNotificationRequest<T> withContentLessThan(String content){
       return withContent(Operator.LESS_THAN, content);
    }

    public SystemNotificationRequest<T> withContentLessThanOrEqualTo(String content){
       return withContent(Operator.LESS_THAN_OR_EQUAL, content);
    }

    public SystemNotificationRequest<T> withContentBetween(String startOfContent, String endOfContent){
       return withContent(Operator.BETWEEN, startOfContent, endOfContent);
    }
    public SystemNotificationRequest<T> withContentStartingWith(String content){
       return withContent(Operator.BEGIN_WITH, content);
    }
    public SystemNotificationRequest<T> withContentContaining(String content){
       return withContent(Operator.CONTAIN, content);
    }

    public SystemNotificationRequest<T> withContentEndingWith(String content){
       return withContent(Operator.END_WITH, content);
    }

    public SystemNotificationRequest<T> withContentIs(String content){
       return withContent(Operator.EQUAL, content);
    }

    public SystemNotificationRequest<T> withContentSoundingLike(String content){
       return withContent(Operator.SOUNDS_LIKE, content);
    }



    public SystemNotificationRequest<T> filterByIsRead(String... isRead){
      if (isRead == null || isRead.length == 0) {
        throw new IllegalArgumentException("filterByIsRead parameter isRead cannot be empty");
      }
      return appendSearchCriteria(createIsReadCriteria(Operator.EQUAL, (Object[])isRead));
    }

    public SystemNotificationRequest<T> withIsRead(Operator operator, Object... values){
       return appendSearchCriteria(createIsReadCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withIsReadIsUnknown(){
       return withIsRead(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withIsReadIsKnown(){
       return withIsRead(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIsReadCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.IS_READ_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withIsReadGreaterThan(String isRead){
       return withIsRead(Operator.GREATER_THAN, isRead);
    }

    public SystemNotificationRequest<T> withIsReadGreaterThanOrEqualTo(String isRead){
       return withIsRead(Operator.GREATER_THAN_OR_EQUAL, isRead);
    }

    public SystemNotificationRequest<T> withIsReadLessThan(String isRead){
       return withIsRead(Operator.LESS_THAN, isRead);
    }

    public SystemNotificationRequest<T> withIsReadLessThanOrEqualTo(String isRead){
       return withIsRead(Operator.LESS_THAN_OR_EQUAL, isRead);
    }

    public SystemNotificationRequest<T> withIsReadBetween(String startOfIsRead, String endOfIsRead){
       return withIsRead(Operator.BETWEEN, startOfIsRead, endOfIsRead);
    }
    public SystemNotificationRequest<T> withIsReadStartingWith(String isRead){
       return withIsRead(Operator.BEGIN_WITH, isRead);
    }
    public SystemNotificationRequest<T> withIsReadContaining(String isRead){
       return withIsRead(Operator.CONTAIN, isRead);
    }

    public SystemNotificationRequest<T> withIsReadEndingWith(String isRead){
       return withIsRead(Operator.END_WITH, isRead);
    }

    public SystemNotificationRequest<T> withIsReadIs(String isRead){
       return withIsRead(Operator.EQUAL, isRead);
    }

    public SystemNotificationRequest<T> withIsReadSoundingLike(String isRead){
       return withIsRead(Operator.SOUNDS_LIKE, isRead);
    }



    public SystemNotificationRequest<T> filterByRecipientId(String... recipientId){
      if (recipientId == null || recipientId.length == 0) {
        throw new IllegalArgumentException("filterByRecipientId parameter recipientId cannot be empty");
      }
      return appendSearchCriteria(createRecipientIdCriteria(Operator.EQUAL, (Object[])recipientId));
    }

    public SystemNotificationRequest<T> withRecipientId(Operator operator, Object... values){
       return appendSearchCriteria(createRecipientIdCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withRecipientIdIsUnknown(){
       return withRecipientId(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withRecipientIdIsKnown(){
       return withRecipientId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRecipientIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.RECIPIENT_ID_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withRecipientIdGreaterThan(String recipientId){
       return withRecipientId(Operator.GREATER_THAN, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdGreaterThanOrEqualTo(String recipientId){
       return withRecipientId(Operator.GREATER_THAN_OR_EQUAL, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdLessThan(String recipientId){
       return withRecipientId(Operator.LESS_THAN, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdLessThanOrEqualTo(String recipientId){
       return withRecipientId(Operator.LESS_THAN_OR_EQUAL, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdBetween(String startOfRecipientId, String endOfRecipientId){
       return withRecipientId(Operator.BETWEEN, startOfRecipientId, endOfRecipientId);
    }
    public SystemNotificationRequest<T> withRecipientIdStartingWith(String recipientId){
       return withRecipientId(Operator.BEGIN_WITH, recipientId);
    }
    public SystemNotificationRequest<T> withRecipientIdContaining(String recipientId){
       return withRecipientId(Operator.CONTAIN, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdEndingWith(String recipientId){
       return withRecipientId(Operator.END_WITH, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdIs(String recipientId){
       return withRecipientId(Operator.EQUAL, recipientId);
    }

    public SystemNotificationRequest<T> withRecipientIdSoundingLike(String recipientId){
       return withRecipientId(Operator.SOUNDS_LIKE, recipientId);
    }



    public SystemNotificationRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public SystemNotificationRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.CREATED_AT_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public SystemNotificationRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SystemNotificationRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public SystemNotificationRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public SystemNotificationRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public SystemNotificationRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public SystemNotificationRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SystemNotification.VERSION_PROPERTY, operator, values);
    }

    public SystemNotificationRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public SystemNotificationRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public SystemNotificationRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public SystemNotificationRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public SystemNotificationRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public SystemNotificationRequest<T> count(){
        super.count();
        return this;
    }
    public SystemNotificationRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public SystemNotificationRequest<T> groupById(){
       groupBy(SystemNotification.ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByIdAs(String retName){
       groupBy(retName, SystemNotification.ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.ID_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByNotificationType(){
       groupBy(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByNotificationTypeAs(String retName){
       groupBy(retName, SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByNotificationTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.NOTIFICATION_TYPE_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByTitle(){
       groupBy(SystemNotification.TITLE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByTitleAs(String retName){
       groupBy(retName, SystemNotification.TITLE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByTitleWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.TITLE_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByContent(){
       groupBy(SystemNotification.CONTENT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByContentAs(String retName){
       groupBy(retName, SystemNotification.CONTENT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByContentWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.CONTENT_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByIsRead(){
       groupBy(SystemNotification.IS_READ_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByIsReadAs(String retName){
       groupBy(retName, SystemNotification.IS_READ_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByIsReadWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.IS_READ_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByRecipientId(){
       groupBy(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByRecipientIdAs(String retName){
       groupBy(retName, SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByRecipientIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.RECIPIENT_ID_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByCreatedAt(){
       groupBy(SystemNotification.CREATED_AT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, SystemNotification.CREATED_AT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.CREATED_AT_PROPERTY, function);
       return this;
    }

    public SystemNotificationRequest<T> groupByVersion(){
       groupBy(SystemNotification.VERSION_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByVersionAs(String retName){
       groupBy(retName, SystemNotification.VERSION_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SystemNotification.VERSION_PROPERTY, function);
       return this;
    }



    public SystemNotificationRequest<T> orderByIdAscending(){
       addOrderByAscending(SystemNotification.ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByIdDescending(){
       addOrderByDescending(SystemNotification.ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByNotificationTypeAscending(){
       addOrderByAscending(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByNotificationTypeDescending(){
       addOrderByDescending(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByNotificationTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByNotificationTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemNotification.NOTIFICATION_TYPE_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByTitleAscending(){
       addOrderByAscending(SystemNotification.TITLE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByTitleDescending(){
       addOrderByDescending(SystemNotification.TITLE_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByTitleAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemNotification.TITLE_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByTitleDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemNotification.TITLE_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByContentAscending(){
       addOrderByAscending(SystemNotification.CONTENT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByContentDescending(){
       addOrderByDescending(SystemNotification.CONTENT_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByContentAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemNotification.CONTENT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByContentDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemNotification.CONTENT_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByIsReadAscending(){
       addOrderByAscending(SystemNotification.IS_READ_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByIsReadDescending(){
       addOrderByDescending(SystemNotification.IS_READ_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByIsReadAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemNotification.IS_READ_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByIsReadDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemNotification.IS_READ_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByRecipientIdAscending(){
       addOrderByAscending(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByRecipientIdDescending(){
       addOrderByDescending(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByRecipientIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByRecipientIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SystemNotification.RECIPIENT_ID_PROPERTY);
       return this;
    }
    public SystemNotificationRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(SystemNotification.CREATED_AT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(SystemNotification.CREATED_AT_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByVersionAscending(){
       addOrderByAscending(SystemNotification.VERSION_PROPERTY);
       return this;
    }

    public SystemNotificationRequest<T> orderByVersionDescending(){
       addOrderByDescending(SystemNotification.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public SystemNotificationRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public SystemNotificationRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public SystemNotificationRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public SystemNotificationRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public SystemNotificationRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}