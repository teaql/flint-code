package com.doublechaintech.enterpriselogisticsservice.useraccount;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog;
import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class UserAccountRequest<T extends UserAccount> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public UserAccountRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public UserAccountRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public UserAccountRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public UserAccountRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public UserAccountRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public UserAccountRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public UserAccountRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (UserAccountRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public UserAccountRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public UserAccountRequest<T> matchingAnyOf(UserAccountRequest userAccount){
        super.internalMatchAny(userAccount);
        return this;
    }

    public UserAccountRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public UserAccountRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public UserAccountRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public UserAccountRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectUsername().selectEmail().selectPhone().selectStatus().selectPasswordHash().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public UserAccountRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public UserAccountRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectUsername().selectEmail().selectPhone().selectStatus().selectPasswordHash().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public UserAccountRequest<T> selectChildren(){
        super.selectAny();
        selectAuditLogList();
        return selectId().selectUsername().selectEmail().selectPhone().selectStatus().selectPasswordHash().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public UserAccountRequest<T> selectId(){
       selectProperty(UserAccount.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectId(){
       unselectProperty(UserAccount.ID_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectUsername(){
       selectProperty(UserAccount.USERNAME_PROPERTY);
       return this;
    }

    /**
     * fill the username with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  username) to fetch username property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectUsername(){
       unselectProperty(UserAccount.USERNAME_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectEmail(){
       selectProperty(UserAccount.EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the email with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  email) to fetch email property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectEmail(){
       unselectProperty(UserAccount.EMAIL_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectPhone(){
       selectProperty(UserAccount.PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the phone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  phone) to fetch phone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectPhone(){
       unselectProperty(UserAccount.PHONE_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectStatus(){
       selectProperty(UserAccount.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectStatus(){
       unselectProperty(UserAccount.STATUS_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectPasswordHash(){
       selectProperty(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }

    /**
     * fill the passwordHash with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  passwordHash) to fetch passwordHash property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectPasswordHash(){
       unselectProperty(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectCreatedAt(){
       selectProperty(UserAccount.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectCreatedAt(){
       unselectProperty(UserAccount.CREATED_AT_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectUpdatedAt(){
       selectProperty(UserAccount.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectUpdatedAt(){
       unselectProperty(UserAccount.UPDATED_AT_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectVersion(){
       selectProperty(UserAccount.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectVersion(){
       unselectProperty(UserAccount.VERSION_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectAuditLogList(){
       return selectAuditLogListWith(Q.auditLogs().selectSelf());
    }

    public UserAccountRequest<T> selectAuditLogListWith(AuditLogRequest auditLogList){
       enhanceRelation(UserAccount.AUDIT_LOG_LIST_PROPERTY, auditLogList);
       return this;
    }

    public UserAccountRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.ID_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public UserAccountRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public UserAccountRequest<T> filterByUsername(String... username){
      if (username == null || username.length == 0) {
        throw new IllegalArgumentException("filterByUsername parameter username cannot be empty");
      }
      return appendSearchCriteria(createUsernameCriteria(Operator.EQUAL, (Object[])username));
    }

    public UserAccountRequest<T> withUsername(Operator operator, Object... values){
       return appendSearchCriteria(createUsernameCriteria(operator, values));
    }

    public UserAccountRequest<T> withUsernameIsUnknown(){
       return withUsername(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withUsernameIsKnown(){
       return withUsername(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUsernameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.USERNAME_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withUsernameGreaterThan(String username){
       return withUsername(Operator.GREATER_THAN, username);
    }

    public UserAccountRequest<T> withUsernameGreaterThanOrEqualTo(String username){
       return withUsername(Operator.GREATER_THAN_OR_EQUAL, username);
    }

    public UserAccountRequest<T> withUsernameLessThan(String username){
       return withUsername(Operator.LESS_THAN, username);
    }

    public UserAccountRequest<T> withUsernameLessThanOrEqualTo(String username){
       return withUsername(Operator.LESS_THAN_OR_EQUAL, username);
    }

    public UserAccountRequest<T> withUsernameBetween(String startOfUsername, String endOfUsername){
       return withUsername(Operator.BETWEEN, startOfUsername, endOfUsername);
    }
    public UserAccountRequest<T> withUsernameStartingWith(String username){
       return withUsername(Operator.BEGIN_WITH, username);
    }
    public UserAccountRequest<T> withUsernameContaining(String username){
       return withUsername(Operator.CONTAIN, username);
    }

    public UserAccountRequest<T> withUsernameEndingWith(String username){
       return withUsername(Operator.END_WITH, username);
    }

    public UserAccountRequest<T> withUsernameIs(String username){
       return withUsername(Operator.EQUAL, username);
    }

    public UserAccountRequest<T> withUsernameSoundingLike(String username){
       return withUsername(Operator.SOUNDS_LIKE, username);
    }



    public UserAccountRequest<T> filterByEmail(String... email){
      if (email == null || email.length == 0) {
        throw new IllegalArgumentException("filterByEmail parameter email cannot be empty");
      }
      return appendSearchCriteria(createEmailCriteria(Operator.EQUAL, (Object[])email));
    }

    public UserAccountRequest<T> withEmail(Operator operator, Object... values){
       return appendSearchCriteria(createEmailCriteria(operator, values));
    }

    public UserAccountRequest<T> withEmailIsUnknown(){
       return withEmail(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withEmailIsKnown(){
       return withEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.EMAIL_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withEmailGreaterThan(String email){
       return withEmail(Operator.GREATER_THAN, email);
    }

    public UserAccountRequest<T> withEmailGreaterThanOrEqualTo(String email){
       return withEmail(Operator.GREATER_THAN_OR_EQUAL, email);
    }

    public UserAccountRequest<T> withEmailLessThan(String email){
       return withEmail(Operator.LESS_THAN, email);
    }

    public UserAccountRequest<T> withEmailLessThanOrEqualTo(String email){
       return withEmail(Operator.LESS_THAN_OR_EQUAL, email);
    }

    public UserAccountRequest<T> withEmailBetween(String startOfEmail, String endOfEmail){
       return withEmail(Operator.BETWEEN, startOfEmail, endOfEmail);
    }
    public UserAccountRequest<T> withEmailStartingWith(String email){
       return withEmail(Operator.BEGIN_WITH, email);
    }
    public UserAccountRequest<T> withEmailContaining(String email){
       return withEmail(Operator.CONTAIN, email);
    }

    public UserAccountRequest<T> withEmailEndingWith(String email){
       return withEmail(Operator.END_WITH, email);
    }

    public UserAccountRequest<T> withEmailIs(String email){
       return withEmail(Operator.EQUAL, email);
    }

    public UserAccountRequest<T> withEmailSoundingLike(String email){
       return withEmail(Operator.SOUNDS_LIKE, email);
    }



    public UserAccountRequest<T> filterByPhone(String... phone){
      if (phone == null || phone.length == 0) {
        throw new IllegalArgumentException("filterByPhone parameter phone cannot be empty");
      }
      return appendSearchCriteria(createPhoneCriteria(Operator.EQUAL, (Object[])phone));
    }

    public UserAccountRequest<T> withPhone(Operator operator, Object... values){
       return appendSearchCriteria(createPhoneCriteria(operator, values));
    }

    public UserAccountRequest<T> withPhoneIsUnknown(){
       return withPhone(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withPhoneIsKnown(){
       return withPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.PHONE_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withPhoneGreaterThan(String phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public UserAccountRequest<T> withPhoneGreaterThanOrEqualTo(String phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public UserAccountRequest<T> withPhoneLessThan(String phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public UserAccountRequest<T> withPhoneLessThanOrEqualTo(String phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public UserAccountRequest<T> withPhoneBetween(String startOfPhone, String endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }
    public UserAccountRequest<T> withPhoneStartingWith(String phone){
       return withPhone(Operator.BEGIN_WITH, phone);
    }
    public UserAccountRequest<T> withPhoneContaining(String phone){
       return withPhone(Operator.CONTAIN, phone);
    }

    public UserAccountRequest<T> withPhoneEndingWith(String phone){
       return withPhone(Operator.END_WITH, phone);
    }

    public UserAccountRequest<T> withPhoneIs(String phone){
       return withPhone(Operator.EQUAL, phone);
    }

    public UserAccountRequest<T> withPhoneSoundingLike(String phone){
       return withPhone(Operator.SOUNDS_LIKE, phone);
    }



    public UserAccountRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public UserAccountRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public UserAccountRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.STATUS_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public UserAccountRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public UserAccountRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public UserAccountRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public UserAccountRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public UserAccountRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public UserAccountRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public UserAccountRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public UserAccountRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public UserAccountRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public UserAccountRequest<T> filterByPasswordHash(String... passwordHash){
      if (passwordHash == null || passwordHash.length == 0) {
        throw new IllegalArgumentException("filterByPasswordHash parameter passwordHash cannot be empty");
      }
      return appendSearchCriteria(createPasswordHashCriteria(Operator.EQUAL, (Object[])passwordHash));
    }

    public UserAccountRequest<T> withPasswordHash(Operator operator, Object... values){
       return appendSearchCriteria(createPasswordHashCriteria(operator, values));
    }

    public UserAccountRequest<T> withPasswordHashIsUnknown(){
       return withPasswordHash(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withPasswordHashIsKnown(){
       return withPasswordHash(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPasswordHashCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.PASSWORD_HASH_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withPasswordHashGreaterThan(String passwordHash){
       return withPasswordHash(Operator.GREATER_THAN, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashGreaterThanOrEqualTo(String passwordHash){
       return withPasswordHash(Operator.GREATER_THAN_OR_EQUAL, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashLessThan(String passwordHash){
       return withPasswordHash(Operator.LESS_THAN, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashLessThanOrEqualTo(String passwordHash){
       return withPasswordHash(Operator.LESS_THAN_OR_EQUAL, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashBetween(String startOfPasswordHash, String endOfPasswordHash){
       return withPasswordHash(Operator.BETWEEN, startOfPasswordHash, endOfPasswordHash);
    }
    public UserAccountRequest<T> withPasswordHashStartingWith(String passwordHash){
       return withPasswordHash(Operator.BEGIN_WITH, passwordHash);
    }
    public UserAccountRequest<T> withPasswordHashContaining(String passwordHash){
       return withPasswordHash(Operator.CONTAIN, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashEndingWith(String passwordHash){
       return withPasswordHash(Operator.END_WITH, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashIs(String passwordHash){
       return withPasswordHash(Operator.EQUAL, passwordHash);
    }

    public UserAccountRequest<T> withPasswordHashSoundingLike(String passwordHash){
       return withPasswordHash(Operator.SOUNDS_LIKE, passwordHash);
    }



    public UserAccountRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public UserAccountRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public UserAccountRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.CREATED_AT_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public UserAccountRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public UserAccountRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public UserAccountRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public UserAccountRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public UserAccountRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.UPDATED_AT_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public UserAccountRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public UserAccountRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public UserAccountRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public UserAccountRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public UserAccountRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.VERSION_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public UserAccountRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public UserAccountRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public UserAccountRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public UserAccountRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public UserAccountRequest<T> withAuditLogListMatching(AuditLogRequest auditLogRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(UserAccount.ID_PROPERTY, auditLogRequest, AuditLog.USER_ACCOUNT_PROPERTY));
    }

    public UserAccountRequest<T> withoutAuditLogListMatching(AuditLogRequest auditLogRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(UserAccount.ID_PROPERTY, auditLogRequest, AuditLog.USER_ACCOUNT_PROPERTY)));
    }

    public UserAccountRequest<T> haveAuditLogs(){
        return withAuditLogListMatching(Q.auditLogs().unlimited());
    }

    public UserAccountRequest<T> haveNoAuditLogs(){
        return withoutAuditLogListMatching(Q.auditLogs().unlimited());
    }

    public UserAccountRequest<T> count(){
        super.count();
        return this;
    }
    public UserAccountRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public UserAccountRequest<T> groupByAuditLogsWithDetails(AuditLogRequest subRequest){
       aggregate(UserAccount.AUDIT_LOG_LIST_PROPERTY, subRequest);
       return this;
    }

    public UserAccountRequest<T> groupById(){
       groupBy(UserAccount.ID_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByIdAs(String retName){
       groupBy(retName, UserAccount.ID_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.ID_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByUsername(){
       groupBy(UserAccount.USERNAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByUsernameAs(String retName){
       groupBy(retName, UserAccount.USERNAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByUsernameWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.USERNAME_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByEmail(){
       groupBy(UserAccount.EMAIL_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByEmailAs(String retName){
       groupBy(retName, UserAccount.EMAIL_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.EMAIL_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByPhone(){
       groupBy(UserAccount.PHONE_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByPhoneAs(String retName){
       groupBy(retName, UserAccount.PHONE_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.PHONE_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByStatus(){
       groupBy(UserAccount.STATUS_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByStatusAs(String retName){
       groupBy(retName, UserAccount.STATUS_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.STATUS_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByPasswordHash(){
       groupBy(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByPasswordHashAs(String retName){
       groupBy(retName, UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByPasswordHashWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.PASSWORD_HASH_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByCreatedAt(){
       groupBy(UserAccount.CREATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, UserAccount.CREATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.CREATED_AT_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByUpdatedAt(){
       groupBy(UserAccount.UPDATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, UserAccount.UPDATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByVersion(){
       groupBy(UserAccount.VERSION_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByVersionAs(String retName){
       groupBy(retName, UserAccount.VERSION_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.VERSION_PROPERTY, function);
       return this;
    }



    public UserAccountRequest<T> orderByIdAscending(){
       addOrderByAscending(UserAccount.ID_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByIdDescending(){
       addOrderByDescending(UserAccount.ID_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUsernameAscending(){
       addOrderByAscending(UserAccount.USERNAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUsernameDescending(){
       addOrderByDescending(UserAccount.USERNAME_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByUsernameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserAccount.USERNAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUsernameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserAccount.USERNAME_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByEmailAscending(){
       addOrderByAscending(UserAccount.EMAIL_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByEmailDescending(){
       addOrderByDescending(UserAccount.EMAIL_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserAccount.EMAIL_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserAccount.EMAIL_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByPhoneAscending(){
       addOrderByAscending(UserAccount.PHONE_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByPhoneDescending(){
       addOrderByDescending(UserAccount.PHONE_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserAccount.PHONE_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserAccount.PHONE_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByStatusAscending(){
       addOrderByAscending(UserAccount.STATUS_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByStatusDescending(){
       addOrderByDescending(UserAccount.STATUS_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserAccount.STATUS_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserAccount.STATUS_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByPasswordHashAscending(){
       addOrderByAscending(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByPasswordHashDescending(){
       addOrderByDescending(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByPasswordHashAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByPasswordHashDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserAccount.PASSWORD_HASH_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(UserAccount.CREATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(UserAccount.CREATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(UserAccount.UPDATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(UserAccount.UPDATED_AT_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByVersionAscending(){
       addOrderByAscending(UserAccount.VERSION_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByVersionDescending(){
       addOrderByDescending(UserAccount.VERSION_PROPERTY);
       return this;
    }


    public UserAccountRequest<T> statsFromAuditLogsAs(String name, AuditLogRequest subRequest){
       return statsFromAuditLogsAs(name, subRequest, false);
    }

    public UserAccountRequest<T> statsFromAuditLogsAs(String name, AuditLogRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(AuditLog.USER_ACCOUNT_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public UserAccountRequest<T> statsFromAuditLogs(AuditLogRequest subRequest){
       return statsFromAuditLogsAs(REFINEMENTS, subRequest);
    }
    public UserAccountRequest<T> countAuditLogs(){
        return countAuditLogsAs("Count");
    }

    public UserAccountRequest<T> countAuditLogsAs(String name){
        return countAuditLogsWith(name, Q.auditLogs().unlimited());
    }

    public UserAccountRequest<T> countAuditLogsWith(String name, AuditLogRequest subRequest){
        return statsFromAuditLogsAs(name, subRequest.count(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public UserAccountRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public UserAccountRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public UserAccountRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public UserAccountRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public UserAccountRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}