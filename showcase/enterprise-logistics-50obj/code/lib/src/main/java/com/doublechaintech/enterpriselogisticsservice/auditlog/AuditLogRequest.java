package com.doublechaintech.enterpriselogisticsservice.auditlog;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount;
import com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccountRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
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
        return selectId().selectAction().selectEntityType().selectEntityId().selectUserAccountIdOnly().selectIpAddress().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public AuditLogRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public AuditLogRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectAction().selectEntityType().selectEntityId().selectUserAccount().selectIpAddress().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public AuditLogRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectAction().selectEntityType().selectEntityId().selectUserAccount().selectIpAddress().selectCreatedTime().selectUpdateTime().selectVersion();
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
    public AuditLogRequest<T> selectUserAccountIdOnly(){
       selectProperty(AuditLog.USER_ACCOUNT_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> selectUserAccount(){
        return selectUserAccountWith(Q.userAccounts().unlimited().selectSelf());
    }

    public AuditLogRequest<T> selectUserAccountWith(UserAccountRequest userAccount){
       selectProperty(AuditLog.USER_ACCOUNT_PROPERTY);
       enhanceRelation(AuditLog.USER_ACCOUNT_PROPERTY, userAccount);
       return this;
    }

    public AuditLogRequest<T> unselectUserAccount(){
       unselectProperty(AuditLog.USER_ACCOUNT_PROPERTY);
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
    public AuditLogRequest<T> selectUpdateTime(){
       selectProperty(AuditLog.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public AuditLogRequest<T> unselectUpdateTime(){
       unselectProperty(AuditLog.UPDATE_TIME_PROPERTY);
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



    public AuditLogRequest<T> filterByUserAccount(UserAccount... userAccount){
      if (userAccount == null || userAccount.length == 0) {
        throw new IllegalArgumentException("filterByUserAccount parameter userAccount cannot be empty");
      }
      return appendSearchCriteria(createUserAccountCriteria(Operator.EQUAL, (Object[])userAccount));
    }

    public AuditLogRequest<T> withUserAccount(Operator operator, Object... values){
       return appendSearchCriteria(createUserAccountCriteria(operator, values));
    }

    public AuditLogRequest<T> withUserAccountIsUnknown(){
       return withUserAccount(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withUserAccountIsKnown(){
       return withUserAccount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUserAccountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.USER_ACCOUNT_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> filterByUserAccount(Long userAccount){
      if(userAccount == null){
         return this;
      }
      return withUserAccount(Operator.EQUAL, userAccount);
    }
    public AuditLogRequest<T> withUserAccountMatching(UserAccountRequest userAccount){
       return appendSearchCriteria(new SubQuerySearchCriteria(AuditLog.USER_ACCOUNT_PROPERTY, userAccount, UserAccount.ID_PROPERTY));
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




    public AuditLogRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public AuditLogRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public AuditLogRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public AuditLogRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(AuditLog.UPDATE_TIME_PROPERTY, operator, values);
    }

    public AuditLogRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public AuditLogRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public AuditLogRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
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
    public AuditLogRequest<T> groupByUserAccountWithDetails(){
       return groupByUserAccountWithDetails(Q.userAccounts().unlimited());
    }

    public AuditLogRequest<T> groupByUserAccountWithDetails(UserAccountRequest subRequest){
       aggregate(AuditLog.USER_ACCOUNT_PROPERTY, subRequest);
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
    public AuditLogRequest<T> groupByUserAccountWith(UserAccountRequest subRequest){
       groupBy(AuditLog.USER_ACCOUNT_PROPERTY, subRequest);
       return this;
    }
    public AuditLogRequest<T> groupByUserAccount(){
       groupBy(AuditLog.USER_ACCOUNT_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByUserAccountAs(String retName){
       groupBy(retName, AuditLog.USER_ACCOUNT_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByUserAccountWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.USER_ACCOUNT_PROPERTY, function);
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

    public AuditLogRequest<T> groupByUpdateTime(){
       groupBy(AuditLog.UPDATE_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, AuditLog.UPDATE_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, AuditLog.UPDATE_TIME_PROPERTY, function);
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
    public AuditLogRequest<T> orderByUserAccountAscending(){
       addOrderByAscending(AuditLog.USER_ACCOUNT_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByUserAccountDescending(){
       addOrderByDescending(AuditLog.USER_ACCOUNT_PROPERTY);
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
    public AuditLogRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(AuditLog.CREATED_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(AuditLog.UPDATE_TIME_PROPERTY);
       return this;
    }

    public AuditLogRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(AuditLog.UPDATE_TIME_PROPERTY);
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


    public UserAccountRequest rollUpToUserAccount(){
       UserAccountRequest userAccount = Q.userAccounts().unlimited();
       this.withUserAccountMatching(userAccount)
           .groupByUserAccountWith(userAccount);
       return userAccount;
    }






   public AuditLogRequest<T> facetByUserAccountAs(String facetName, UserAccountRequest userAccount){
       return facetByUserAccountAs(facetName, userAccount, true);
   }

   public AuditLogRequest<T> facetByUserAccountAs(String facetName, UserAccountRequest userAccount, boolean includeAllFacets){
       addFacet(facetName, AuditLog.USER_ACCOUNT_PROPERTY, userAccount, includeAllFacets);
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