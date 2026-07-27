package com.doublechaintech.enterpriselogisticsservice.auditlog;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class AuditLogRequest<T extends AuditLog> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public AuditLogRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public AuditLogRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public AuditLogRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public AuditLogRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public AuditLogRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public AuditLogRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public AuditLogRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (AuditLogRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public AuditLogRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public AuditLogRequest<T> matchingAnyOf(AuditLogRequest auditLog){
        super.internalMatchAny(auditLog);
        return this;
    }

    public AuditLogRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public AuditLogRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public AuditLogRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public AuditLogRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectAction().selectEntityType().selectEntityId().selectUserId().selectIpAddress().selectDetails().selectCreatedTime().selectVersion();
    }

    public AuditLogRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public AuditLogRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectAction().selectEntityType().selectEntityId().selectUserId().selectIpAddress().selectDetails().selectCreatedTime().selectVersion();
    }

    public AuditLogRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectAction().selectEntityType().selectEntityId().selectUserId().selectIpAddress().selectDetails().selectCreatedTime().selectVersion();
    }


    public AuditLogRequest<T> selectId(){
       selectProperty(AuditLog.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectId(){
       unselectProperty(AuditLog.ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectAction(){
       selectProperty(AuditLog.ACTION_PROPERTY);
       return this;
    }

    /**
     * fill the action with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  action) to fetch action property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectAction(){
       unselectProperty(AuditLog.ACTION_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectEntityType(){
       selectProperty(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the entityType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  entityType) to fetch entityType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectEntityType(){
       unselectProperty(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectEntityId(){
       selectProperty(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }

    /**
     * fill the entityId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  entityId) to fetch entityId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectEntityId(){
       unselectProperty(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectUserId(){
       selectProperty(AuditLog.USER_ID_PROPERTY);
       return this;
    }

    /**
     * fill the userId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  userId) to fetch userId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectUserId(){
       unselectProperty(AuditLog.USER_ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectIpAddress(){
       selectProperty(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }

    /**
     * fill the ipAddress with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  ipAddress) to fetch ipAddress property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectIpAddress(){
       unselectProperty(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectDetails(){
       selectProperty(AuditLog.DETAILS_PROPERTY);
       return this;
    }

    /**
     * fill the details with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  details) to fetch details property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectDetails(){
       unselectProperty(AuditLog.DETAILS_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectCreatedTime(){
       selectProperty(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectCreatedTime(){
       unselectProperty(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> selectVersion(){
       selectProperty(AuditLog.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectVersion(){
       unselectProperty(AuditLog.VERSION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.ID_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public AuditLogRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public AuditLogRequest<T> filterByAction(String... action){
      if (action == null || action.length == 0) {
        throw new IllegalArgumentException("filterByAction parameter action cannot be empty");
      }
      return appendSearchCriteria(createActionCriteria(Operator.EQUAL, (Object[])action));
    }

    public AuditLogRequest<T> withAction(Operator operator, Object... values){
       return appendSearchCriteria(createActionCriteria(operator, values));
    }

    public AuditLogRequest<T> withActionIsUnknown(){
       return withAction(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withActionIsKnown(){
       return withAction(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createActionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.ACTION_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withActionGreaterThan(String action){
       return withAction(Operator.GREATER_THAN, action);
    }

    public AuditLogRequest<T> withActionGreaterThanOrEqualTo(String action){
       return withAction(Operator.GREATER_THAN_OR_EQUAL, action);
    }

    public AuditLogRequest<T> withActionLessThan(String action){
       return withAction(Operator.LESS_THAN, action);
    }

    public AuditLogRequest<T> withActionLessThanOrEqualTo(String action){
       return withAction(Operator.LESS_THAN_OR_EQUAL, action);
    }

    public AuditLogRequest<T> withActionBetween(String startOfAction, String endOfAction){
       return withAction(Operator.BETWEEN, startOfAction, endOfAction);
    }
    public AuditLogRequest<T> withActionStartingWith(String action){
       return withAction(Operator.BEGIN_WITH, action);
    }
    public AuditLogRequest<T> withActionContaining(String action){
       return withAction(Operator.CONTAIN, action);
    }

    public AuditLogRequest<T> withActionEndingWith(String action){
       return withAction(Operator.END_WITH, action);
    }

    public AuditLogRequest<T> withActionIs(String action){
       return withAction(Operator.EQUAL, action);
    }

    public AuditLogRequest<T> withActionSoundingLike(String action){
       return withAction(Operator.SOUNDS_LIKE, action);
    }



    public AuditLogRequest<T> filterByEntityType(String... entityType){
      if (entityType == null || entityType.length == 0) {
        throw new IllegalArgumentException("filterByEntityType parameter entityType cannot be empty");
      }
      return appendSearchCriteria(createEntityTypeCriteria(Operator.EQUAL, (Object[])entityType));
    }

    public AuditLogRequest<T> withEntityType(Operator operator, Object... values){
       return appendSearchCriteria(createEntityTypeCriteria(operator, values));
    }

    public AuditLogRequest<T> withEntityTypeIsUnknown(){
       return withEntityType(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withEntityTypeIsKnown(){
       return withEntityType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEntityTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.ENTITY_TYPE_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withEntityTypeGreaterThan(String entityType){
       return withEntityType(Operator.GREATER_THAN, entityType);
    }

    public AuditLogRequest<T> withEntityTypeGreaterThanOrEqualTo(String entityType){
       return withEntityType(Operator.GREATER_THAN_OR_EQUAL, entityType);
    }

    public AuditLogRequest<T> withEntityTypeLessThan(String entityType){
       return withEntityType(Operator.LESS_THAN, entityType);
    }

    public AuditLogRequest<T> withEntityTypeLessThanOrEqualTo(String entityType){
       return withEntityType(Operator.LESS_THAN_OR_EQUAL, entityType);
    }

    public AuditLogRequest<T> withEntityTypeBetween(String startOfEntityType, String endOfEntityType){
       return withEntityType(Operator.BETWEEN, startOfEntityType, endOfEntityType);
    }
    public AuditLogRequest<T> withEntityTypeStartingWith(String entityType){
       return withEntityType(Operator.BEGIN_WITH, entityType);
    }
    public AuditLogRequest<T> withEntityTypeContaining(String entityType){
       return withEntityType(Operator.CONTAIN, entityType);
    }

    public AuditLogRequest<T> withEntityTypeEndingWith(String entityType){
       return withEntityType(Operator.END_WITH, entityType);
    }

    public AuditLogRequest<T> withEntityTypeIs(String entityType){
       return withEntityType(Operator.EQUAL, entityType);
    }

    public AuditLogRequest<T> withEntityTypeSoundingLike(String entityType){
       return withEntityType(Operator.SOUNDS_LIKE, entityType);
    }



    public AuditLogRequest<T> filterByEntityId(String... entityId){
      if (entityId == null || entityId.length == 0) {
        throw new IllegalArgumentException("filterByEntityId parameter entityId cannot be empty");
      }
      return appendSearchCriteria(createEntityIdCriteria(Operator.EQUAL, (Object[])entityId));
    }

    public AuditLogRequest<T> withEntityId(Operator operator, Object... values){
       return appendSearchCriteria(createEntityIdCriteria(operator, values));
    }

    public AuditLogRequest<T> withEntityIdIsUnknown(){
       return withEntityId(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withEntityIdIsKnown(){
       return withEntityId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEntityIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.ENTITY_ID_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withEntityIdGreaterThan(String entityId){
       return withEntityId(Operator.GREATER_THAN, entityId);
    }

    public AuditLogRequest<T> withEntityIdGreaterThanOrEqualTo(String entityId){
       return withEntityId(Operator.GREATER_THAN_OR_EQUAL, entityId);
    }

    public AuditLogRequest<T> withEntityIdLessThan(String entityId){
       return withEntityId(Operator.LESS_THAN, entityId);
    }

    public AuditLogRequest<T> withEntityIdLessThanOrEqualTo(String entityId){
       return withEntityId(Operator.LESS_THAN_OR_EQUAL, entityId);
    }

    public AuditLogRequest<T> withEntityIdBetween(String startOfEntityId, String endOfEntityId){
       return withEntityId(Operator.BETWEEN, startOfEntityId, endOfEntityId);
    }
    public AuditLogRequest<T> withEntityIdStartingWith(String entityId){
       return withEntityId(Operator.BEGIN_WITH, entityId);
    }
    public AuditLogRequest<T> withEntityIdContaining(String entityId){
       return withEntityId(Operator.CONTAIN, entityId);
    }

    public AuditLogRequest<T> withEntityIdEndingWith(String entityId){
       return withEntityId(Operator.END_WITH, entityId);
    }

    public AuditLogRequest<T> withEntityIdIs(String entityId){
       return withEntityId(Operator.EQUAL, entityId);
    }

    public AuditLogRequest<T> withEntityIdSoundingLike(String entityId){
       return withEntityId(Operator.SOUNDS_LIKE, entityId);
    }



    public AuditLogRequest<T> filterByUserId(String... userId){
      if (userId == null || userId.length == 0) {
        throw new IllegalArgumentException("filterByUserId parameter userId cannot be empty");
      }
      return appendSearchCriteria(createUserIdCriteria(Operator.EQUAL, (Object[])userId));
    }

    public AuditLogRequest<T> withUserId(Operator operator, Object... values){
       return appendSearchCriteria(createUserIdCriteria(operator, values));
    }

    public AuditLogRequest<T> withUserIdIsUnknown(){
       return withUserId(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withUserIdIsKnown(){
       return withUserId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUserIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.USER_ID_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withUserIdGreaterThan(String userId){
       return withUserId(Operator.GREATER_THAN, userId);
    }

    public AuditLogRequest<T> withUserIdGreaterThanOrEqualTo(String userId){
       return withUserId(Operator.GREATER_THAN_OR_EQUAL, userId);
    }

    public AuditLogRequest<T> withUserIdLessThan(String userId){
       return withUserId(Operator.LESS_THAN, userId);
    }

    public AuditLogRequest<T> withUserIdLessThanOrEqualTo(String userId){
       return withUserId(Operator.LESS_THAN_OR_EQUAL, userId);
    }

    public AuditLogRequest<T> withUserIdBetween(String startOfUserId, String endOfUserId){
       return withUserId(Operator.BETWEEN, startOfUserId, endOfUserId);
    }
    public AuditLogRequest<T> withUserIdStartingWith(String userId){
       return withUserId(Operator.BEGIN_WITH, userId);
    }
    public AuditLogRequest<T> withUserIdContaining(String userId){
       return withUserId(Operator.CONTAIN, userId);
    }

    public AuditLogRequest<T> withUserIdEndingWith(String userId){
       return withUserId(Operator.END_WITH, userId);
    }

    public AuditLogRequest<T> withUserIdIs(String userId){
       return withUserId(Operator.EQUAL, userId);
    }

    public AuditLogRequest<T> withUserIdSoundingLike(String userId){
       return withUserId(Operator.SOUNDS_LIKE, userId);
    }



    public AuditLogRequest<T> filterByIpAddress(String... ipAddress){
      if (ipAddress == null || ipAddress.length == 0) {
        throw new IllegalArgumentException("filterByIpAddress parameter ipAddress cannot be empty");
      }
      return appendSearchCriteria(createIpAddressCriteria(Operator.EQUAL, (Object[])ipAddress));
    }

    public AuditLogRequest<T> withIpAddress(Operator operator, Object... values){
       return appendSearchCriteria(createIpAddressCriteria(operator, values));
    }

    public AuditLogRequest<T> withIpAddressIsUnknown(){
       return withIpAddress(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withIpAddressIsKnown(){
       return withIpAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIpAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.IP_ADDRESS_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withIpAddressGreaterThan(String ipAddress){
       return withIpAddress(Operator.GREATER_THAN, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressGreaterThanOrEqualTo(String ipAddress){
       return withIpAddress(Operator.GREATER_THAN_OR_EQUAL, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressLessThan(String ipAddress){
       return withIpAddress(Operator.LESS_THAN, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressLessThanOrEqualTo(String ipAddress){
       return withIpAddress(Operator.LESS_THAN_OR_EQUAL, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressBetween(String startOfIpAddress, String endOfIpAddress){
       return withIpAddress(Operator.BETWEEN, startOfIpAddress, endOfIpAddress);
    }
    public AuditLogRequest<T> withIpAddressStartingWith(String ipAddress){
       return withIpAddress(Operator.BEGIN_WITH, ipAddress);
    }
    public AuditLogRequest<T> withIpAddressContaining(String ipAddress){
       return withIpAddress(Operator.CONTAIN, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressEndingWith(String ipAddress){
       return withIpAddress(Operator.END_WITH, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressIs(String ipAddress){
       return withIpAddress(Operator.EQUAL, ipAddress);
    }

    public AuditLogRequest<T> withIpAddressSoundingLike(String ipAddress){
       return withIpAddress(Operator.SOUNDS_LIKE, ipAddress);
    }



    public AuditLogRequest<T> filterByDetails(String... details){
      if (details == null || details.length == 0) {
        throw new IllegalArgumentException("filterByDetails parameter details cannot be empty");
      }
      return appendSearchCriteria(createDetailsCriteria(Operator.EQUAL, (Object[])details));
    }

    public AuditLogRequest<T> withDetails(Operator operator, Object... values){
       return appendSearchCriteria(createDetailsCriteria(operator, values));
    }

    public AuditLogRequest<T> withDetailsIsUnknown(){
       return withDetails(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withDetailsIsKnown(){
       return withDetails(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDetailsCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.DETAILS_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withDetailsGreaterThan(String details){
       return withDetails(Operator.GREATER_THAN, details);
    }

    public AuditLogRequest<T> withDetailsGreaterThanOrEqualTo(String details){
       return withDetails(Operator.GREATER_THAN_OR_EQUAL, details);
    }

    public AuditLogRequest<T> withDetailsLessThan(String details){
       return withDetails(Operator.LESS_THAN, details);
    }

    public AuditLogRequest<T> withDetailsLessThanOrEqualTo(String details){
       return withDetails(Operator.LESS_THAN_OR_EQUAL, details);
    }

    public AuditLogRequest<T> withDetailsBetween(String startOfDetails, String endOfDetails){
       return withDetails(Operator.BETWEEN, startOfDetails, endOfDetails);
    }
    public AuditLogRequest<T> withDetailsStartingWith(String details){
       return withDetails(Operator.BEGIN_WITH, details);
    }
    public AuditLogRequest<T> withDetailsContaining(String details){
       return withDetails(Operator.CONTAIN, details);
    }

    public AuditLogRequest<T> withDetailsEndingWith(String details){
       return withDetails(Operator.END_WITH, details);
    }

    public AuditLogRequest<T> withDetailsIs(String details){
       return withDetails(Operator.EQUAL, details);
    }

    public AuditLogRequest<T> withDetailsSoundingLike(String details){
       return withDetails(Operator.SOUNDS_LIKE, details);
    }



    public AuditLogRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public AuditLogRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public AuditLogRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.CREATED_TIME_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public AuditLogRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public AuditLogRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public AuditLogRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public AuditLogRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public AuditLogRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.VERSION_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public AuditLogRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public AuditLogRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public AuditLogRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public AuditLogRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public AuditLogRequest<T> count(){
        super.count();
        return this;
    }
    public AuditLogRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }

    public AuditLogRequest<T> groupById(){
       groupBy(AuditLog.ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByIdAs(String retName){
       groupBy(retName, AuditLog.ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.ID_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByAction(){
       groupBy(AuditLog.ACTION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByActionAs(String retName){
       groupBy(retName, AuditLog.ACTION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByActionWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.ACTION_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByEntityType(){
       groupBy(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByEntityTypeAs(String retName){
       groupBy(retName, AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByEntityTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.ENTITY_TYPE_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByEntityId(){
       groupBy(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByEntityIdAs(String retName){
       groupBy(retName, AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByEntityIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.ENTITY_ID_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByUserId(){
       groupBy(AuditLog.USER_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByUserIdAs(String retName){
       groupBy(retName, AuditLog.USER_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByUserIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.USER_ID_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByIpAddress(){
       groupBy(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByIpAddressAs(String retName){
       groupBy(retName, AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByIpAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.IP_ADDRESS_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByDetails(){
       groupBy(AuditLog.DETAILS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByDetailsAs(String retName){
       groupBy(retName, AuditLog.DETAILS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByDetailsWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.DETAILS_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByCreatedTime(){
       groupBy(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public AuditLogRequest<T> groupByVersion(){
       groupBy(AuditLog.VERSION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByVersionAs(String retName){
       groupBy(retName, AuditLog.VERSION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.VERSION_PROPERTY, function);
       return this;
    }



    public AuditLogRequest<T> orderByIdAscending(){
       addOrderByAscending(AuditLog.ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByIdDescending(){
       addOrderByDescending(AuditLog.ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByActionAscending(){
       addOrderByAscending(AuditLog.ACTION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByActionDescending(){
       addOrderByDescending(AuditLog.ACTION_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByActionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AuditLog.ACTION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByActionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AuditLog.ACTION_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByEntityTypeAscending(){
       addOrderByAscending(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByEntityTypeDescending(){
       addOrderByDescending(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByEntityTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByEntityTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AuditLog.ENTITY_TYPE_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByEntityIdAscending(){
       addOrderByAscending(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByEntityIdDescending(){
       addOrderByDescending(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByEntityIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByEntityIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AuditLog.ENTITY_ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByUserIdAscending(){
       addOrderByAscending(AuditLog.USER_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByUserIdDescending(){
       addOrderByDescending(AuditLog.USER_ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByUserIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AuditLog.USER_ID_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByUserIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AuditLog.USER_ID_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByIpAddressAscending(){
       addOrderByAscending(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByIpAddressDescending(){
       addOrderByDescending(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByIpAddressAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByIpAddressDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AuditLog.IP_ADDRESS_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByDetailsAscending(){
       addOrderByAscending(AuditLog.DETAILS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByDetailsDescending(){
       addOrderByDescending(AuditLog.DETAILS_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByDetailsAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(AuditLog.DETAILS_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByDetailsDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(AuditLog.DETAILS_PROPERTY);
       return this;
    }
    public AuditLogRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByVersionAscending(){
       addOrderByAscending(AuditLog.VERSION_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByVersionDescending(){
       addOrderByDescending(AuditLog.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public AuditLogRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public AuditLogRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public AuditLogRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public AuditLogRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public AuditLogRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}