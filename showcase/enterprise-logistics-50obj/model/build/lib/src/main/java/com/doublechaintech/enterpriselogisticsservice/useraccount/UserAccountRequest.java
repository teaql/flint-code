package com.doublechaintech.enterpriselogisticsservice.useraccount;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
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
        return selectId().selectName().selectEmail().selectPhone().selectPasswordHash().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public UserAccountRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public UserAccountRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectEmail().selectPhone().selectPasswordHash().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public UserAccountRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectEmail().selectPhone().selectPasswordHash().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
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
    public UserAccountRequest<T> selectName(){
       selectProperty(UserAccount.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectName(){
       unselectProperty(UserAccount.NAME_PROPERTY);
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
    public UserAccountRequest<T> selectCreateTime(){
       selectProperty(UserAccount.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectCreateTime(){
       unselectProperty(UserAccount.CREATE_TIME_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> selectUpdateTime(){
       selectProperty(UserAccount.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public UserAccountRequest<T> unselectUpdateTime(){
       unselectProperty(UserAccount.UPDATE_TIME_PROPERTY);
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



    public UserAccountRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public UserAccountRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public UserAccountRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.NAME_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public UserAccountRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public UserAccountRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public UserAccountRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public UserAccountRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public UserAccountRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public UserAccountRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public UserAccountRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public UserAccountRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public UserAccountRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
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



    public UserAccountRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public UserAccountRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public UserAccountRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.CREATE_TIME_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public UserAccountRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public UserAccountRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public UserAccountRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public UserAccountRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public UserAccountRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public UserAccountRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public UserAccountRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public UserAccountRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public UserAccountRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public UserAccountRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public UserAccountRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public UserAccountRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public UserAccountRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(UserAccount.UPDATE_TIME_PROPERTY, operator, values);
    }

    public UserAccountRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public UserAccountRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public UserAccountRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
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


    public UserAccountRequest<T> count(){
        super.count();
        return this;
    }
    public UserAccountRequest<T> countAs(String retName){
        super.count(retName);
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

    public UserAccountRequest<T> groupByName(){
       groupBy(UserAccount.NAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByNameAs(String retName){
       groupBy(retName, UserAccount.NAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.NAME_PROPERTY, function);
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

    public UserAccountRequest<T> groupByCreateTime(){
       groupBy(UserAccount.CREATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, UserAccount.CREATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public UserAccountRequest<T> groupByUpdateTime(){
       groupBy(UserAccount.UPDATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, UserAccount.UPDATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, UserAccount.UPDATE_TIME_PROPERTY, function);
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

    public UserAccountRequest<T> orderByNameAscending(){
       addOrderByAscending(UserAccount.NAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByNameDescending(){
       addOrderByDescending(UserAccount.NAME_PROPERTY);
       return this;
    }
    public UserAccountRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(UserAccount.NAME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(UserAccount.NAME_PROPERTY);
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
    public UserAccountRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(UserAccount.CREATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(UserAccount.CREATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(UserAccount.UPDATE_TIME_PROPERTY);
       return this;
    }

    public UserAccountRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(UserAccount.UPDATE_TIME_PROPERTY);
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