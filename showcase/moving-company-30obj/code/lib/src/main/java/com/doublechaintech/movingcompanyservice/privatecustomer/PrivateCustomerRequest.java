package com.doublechaintech.movingcompanyservice.privatecustomer;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class PrivateCustomerRequest<T extends PrivateCustomer> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PrivateCustomerRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PrivateCustomerRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PrivateCustomerRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PrivateCustomerRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PrivateCustomerRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PrivateCustomerRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PrivateCustomerRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PrivateCustomerRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PrivateCustomerRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PrivateCustomerRequest<T> matchingAnyOf(PrivateCustomerRequest privateCustomer){
        super.internalMatchAny(privateCustomer);
        return this;
    }

    public PrivateCustomerRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PrivateCustomerRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PrivateCustomerRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PrivateCustomerRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectEmail().selectPhone().selectAddress().selectIdNumber().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public PrivateCustomerRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PrivateCustomerRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectEmail().selectPhone().selectAddress().selectIdNumber().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public PrivateCustomerRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectEmail().selectPhone().selectAddress().selectIdNumber().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public PrivateCustomerRequest<T> selectId(){
       selectProperty(PrivateCustomer.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectId(){
       unselectProperty(PrivateCustomer.ID_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectName(){
       selectProperty(PrivateCustomer.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectName(){
       unselectProperty(PrivateCustomer.NAME_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectEmail(){
       selectProperty(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the email with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  email) to fetch email property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectEmail(){
       unselectProperty(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectPhone(){
       selectProperty(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the phone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  phone) to fetch phone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the phone with customized aggrFunction, TEAQL uses ({aggrFunction}(phone) AS phone to fetch phone property.
     * @param aggrFunction  aggrFunction
     */
    public PrivateCustomerRequest<T> selectPhone(AggrFunction aggrFunction){
       selectProperty(PrivateCustomer.PHONE_PROPERTY, aggrFunction);
       return this;
    }


    public PrivateCustomerRequest<T> unselectPhone(){
       unselectProperty(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectAddress(){
       selectProperty(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    /**
     * fill the address with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  address) to fetch address property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectAddress(){
       unselectProperty(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectIdNumber(){
       selectProperty(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the idNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  idNumber) to fetch idNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectIdNumber(){
       unselectProperty(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectCreateTime(){
       selectProperty(PrivateCustomer.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectCreateTime(){
       unselectProperty(PrivateCustomer.CREATE_TIME_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectUpdateTime(){
       selectProperty(PrivateCustomer.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectUpdateTime(){
       unselectProperty(PrivateCustomer.UPDATE_TIME_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectVersion(){
       selectProperty(PrivateCustomer.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectVersion(){
       unselectProperty(PrivateCustomer.VERSION_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.ID_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PrivateCustomerRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PrivateCustomerRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public PrivateCustomerRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.NAME_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public PrivateCustomerRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public PrivateCustomerRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public PrivateCustomerRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public PrivateCustomerRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public PrivateCustomerRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public PrivateCustomerRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public PrivateCustomerRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public PrivateCustomerRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public PrivateCustomerRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public PrivateCustomerRequest<T> filterByEmail(String... email){
      if (email == null || email.length == 0) {
        throw new IllegalArgumentException("filterByEmail parameter email cannot be empty");
      }
      return appendSearchCriteria(createEmailCriteria(Operator.EQUAL, (Object[])email));
    }

    public PrivateCustomerRequest<T> withEmail(Operator operator, Object... values){
       return appendSearchCriteria(createEmailCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withEmailIsUnknown(){
       return withEmail(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withEmailIsKnown(){
       return withEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.EMAIL_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withEmailGreaterThan(String email){
       return withEmail(Operator.GREATER_THAN, email);
    }

    public PrivateCustomerRequest<T> withEmailGreaterThanOrEqualTo(String email){
       return withEmail(Operator.GREATER_THAN_OR_EQUAL, email);
    }

    public PrivateCustomerRequest<T> withEmailLessThan(String email){
       return withEmail(Operator.LESS_THAN, email);
    }

    public PrivateCustomerRequest<T> withEmailLessThanOrEqualTo(String email){
       return withEmail(Operator.LESS_THAN_OR_EQUAL, email);
    }

    public PrivateCustomerRequest<T> withEmailBetween(String startOfEmail, String endOfEmail){
       return withEmail(Operator.BETWEEN, startOfEmail, endOfEmail);
    }
    public PrivateCustomerRequest<T> withEmailStartingWith(String email){
       return withEmail(Operator.BEGIN_WITH, email);
    }
    public PrivateCustomerRequest<T> withEmailContaining(String email){
       return withEmail(Operator.CONTAIN, email);
    }

    public PrivateCustomerRequest<T> withEmailEndingWith(String email){
       return withEmail(Operator.END_WITH, email);
    }

    public PrivateCustomerRequest<T> withEmailIs(String email){
       return withEmail(Operator.EQUAL, email);
    }

    public PrivateCustomerRequest<T> withEmailSoundingLike(String email){
       return withEmail(Operator.SOUNDS_LIKE, email);
    }



    public PrivateCustomerRequest<T> filterByPhone(Integer... phone){
      if (phone == null || phone.length == 0) {
        throw new IllegalArgumentException("filterByPhone parameter phone cannot be empty");
      }
      return appendSearchCriteria(createPhoneCriteria(Operator.EQUAL, (Object[])phone));
    }

    public PrivateCustomerRequest<T> withPhone(Operator operator, Object... values){
       return appendSearchCriteria(createPhoneCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withPhoneIsUnknown(){
       return withPhone(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withPhoneIsKnown(){
       return withPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.PHONE_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withPhoneGreaterThan(Integer phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public PrivateCustomerRequest<T> withPhoneGreaterThanOrEqualTo(Integer phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public PrivateCustomerRequest<T> withPhoneLessThan(Integer phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public PrivateCustomerRequest<T> withPhoneLessThanOrEqualTo(Integer phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public PrivateCustomerRequest<T> withPhoneBetween(Integer startOfPhone, Integer endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }



    public PrivateCustomerRequest<T> filterByAddress(String... address){
      if (address == null || address.length == 0) {
        throw new IllegalArgumentException("filterByAddress parameter address cannot be empty");
      }
      return appendSearchCriteria(createAddressCriteria(Operator.EQUAL, (Object[])address));
    }

    public PrivateCustomerRequest<T> withAddress(Operator operator, Object... values){
       return appendSearchCriteria(createAddressCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withAddressIsUnknown(){
       return withAddress(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withAddressIsKnown(){
       return withAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.ADDRESS_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withAddressGreaterThan(String address){
       return withAddress(Operator.GREATER_THAN, address);
    }

    public PrivateCustomerRequest<T> withAddressGreaterThanOrEqualTo(String address){
       return withAddress(Operator.GREATER_THAN_OR_EQUAL, address);
    }

    public PrivateCustomerRequest<T> withAddressLessThan(String address){
       return withAddress(Operator.LESS_THAN, address);
    }

    public PrivateCustomerRequest<T> withAddressLessThanOrEqualTo(String address){
       return withAddress(Operator.LESS_THAN_OR_EQUAL, address);
    }

    public PrivateCustomerRequest<T> withAddressBetween(String startOfAddress, String endOfAddress){
       return withAddress(Operator.BETWEEN, startOfAddress, endOfAddress);
    }
    public PrivateCustomerRequest<T> withAddressStartingWith(String address){
       return withAddress(Operator.BEGIN_WITH, address);
    }
    public PrivateCustomerRequest<T> withAddressContaining(String address){
       return withAddress(Operator.CONTAIN, address);
    }

    public PrivateCustomerRequest<T> withAddressEndingWith(String address){
       return withAddress(Operator.END_WITH, address);
    }

    public PrivateCustomerRequest<T> withAddressIs(String address){
       return withAddress(Operator.EQUAL, address);
    }

    public PrivateCustomerRequest<T> withAddressSoundingLike(String address){
       return withAddress(Operator.SOUNDS_LIKE, address);
    }



    public PrivateCustomerRequest<T> filterByIdNumber(String... idNumber){
      if (idNumber == null || idNumber.length == 0) {
        throw new IllegalArgumentException("filterByIdNumber parameter idNumber cannot be empty");
      }
      return appendSearchCriteria(createIdNumberCriteria(Operator.EQUAL, (Object[])idNumber));
    }

    public PrivateCustomerRequest<T> withIdNumber(Operator operator, Object... values){
       return appendSearchCriteria(createIdNumberCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withIdNumberIsUnknown(){
       return withIdNumber(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withIdNumberIsKnown(){
       return withIdNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIdNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.ID_NUMBER_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withIdNumberGreaterThan(String idNumber){
       return withIdNumber(Operator.GREATER_THAN, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberGreaterThanOrEqualTo(String idNumber){
       return withIdNumber(Operator.GREATER_THAN_OR_EQUAL, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberLessThan(String idNumber){
       return withIdNumber(Operator.LESS_THAN, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberLessThanOrEqualTo(String idNumber){
       return withIdNumber(Operator.LESS_THAN_OR_EQUAL, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberBetween(String startOfIdNumber, String endOfIdNumber){
       return withIdNumber(Operator.BETWEEN, startOfIdNumber, endOfIdNumber);
    }
    public PrivateCustomerRequest<T> withIdNumberStartingWith(String idNumber){
       return withIdNumber(Operator.BEGIN_WITH, idNumber);
    }
    public PrivateCustomerRequest<T> withIdNumberContaining(String idNumber){
       return withIdNumber(Operator.CONTAIN, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberEndingWith(String idNumber){
       return withIdNumber(Operator.END_WITH, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberIs(String idNumber){
       return withIdNumber(Operator.EQUAL, idNumber);
    }

    public PrivateCustomerRequest<T> withIdNumberSoundingLike(String idNumber){
       return withIdNumber(Operator.SOUNDS_LIKE, idNumber);
    }



    public PrivateCustomerRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public PrivateCustomerRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.CREATE_TIME_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public PrivateCustomerRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PrivateCustomerRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public PrivateCustomerRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public PrivateCustomerRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.UPDATE_TIME_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public PrivateCustomerRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public PrivateCustomerRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public PrivateCustomerRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PrivateCustomerRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.VERSION_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PrivateCustomerRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PrivateCustomerRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PrivateCustomerRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PrivateCustomerRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public PrivateCustomerRequest<T> count(){
        super.count();
        return this;
    }
    public PrivateCustomerRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PrivateCustomerRequest minPhone(){
        return minPhoneAs(prefix("minOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest minPhoneAs(String retName){
        super.min(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest maxPhone(){
        return maxPhoneAs(prefix("maxOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest maxPhoneAs(String retName){
        super.max(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest sumPhone(){
        return sumPhoneAs(prefix("sumOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest sumPhoneAs(String retName){
        super.sum(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest avgPhone(){
        return avgPhoneAs(prefix("avgOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest avgPhoneAs(String retName){
        super.avg(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest standardDeviationPhone(){
        return standardDeviationPhoneAs(prefix("standardDeviationOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest standardDeviationPhoneAs(String retName){
        super.standardDeviation(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest squareRootOfPopulationStandardDeviationPhone(){
        return squareRootOfPopulationStandardDeviationPhoneAs(prefix("squareRootOfPopulationStandardDeviationOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest squareRootOfPopulationStandardDeviationPhoneAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest sampleVariancePhone(){
        return sampleVariancePhoneAs(prefix("sampleVarianceOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest sampleVariancePhoneAs(String retName){
        super.sampleVariance(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }
    public PrivateCustomerRequest samplePopulationVariancePhone(){
        return samplePopulationVariancePhoneAs(prefix("samplePopulationVarianceOf",PrivateCustomer.PHONE_PROPERTY));
    }

    public PrivateCustomerRequest samplePopulationVariancePhoneAs(String retName){
        super.samplePopulationVariance(retName, PrivateCustomer.PHONE_PROPERTY);
        return this;
    }

    public PrivateCustomerRequest<T> groupById(){
       groupBy(PrivateCustomer.ID_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByIdAs(String retName){
       groupBy(retName, PrivateCustomer.ID_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.ID_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByName(){
       groupBy(PrivateCustomer.NAME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByNameAs(String retName){
       groupBy(retName, PrivateCustomer.NAME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.NAME_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByEmail(){
       groupBy(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByEmailAs(String retName){
       groupBy(retName, PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.EMAIL_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByPhone(){
       groupBy(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByPhoneAs(String retName){
       groupBy(retName, PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.PHONE_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddress(){
       groupBy(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressAs(String retName){
       groupBy(retName, PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.ADDRESS_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByIdNumber(){
       groupBy(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByIdNumberAs(String retName){
       groupBy(retName, PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByIdNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.ID_NUMBER_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCreateTime(){
       groupBy(PrivateCustomer.CREATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, PrivateCustomer.CREATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByUpdateTime(){
       groupBy(PrivateCustomer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, PrivateCustomer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByVersion(){
       groupBy(PrivateCustomer.VERSION_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByVersionAs(String retName){
       groupBy(retName, PrivateCustomer.VERSION_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.VERSION_PROPERTY, function);
       return this;
    }



    public PrivateCustomerRequest<T> orderByIdAscending(){
       addOrderByAscending(PrivateCustomer.ID_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByIdDescending(){
       addOrderByDescending(PrivateCustomer.ID_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByNameAscending(){
       addOrderByAscending(PrivateCustomer.NAME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByNameDescending(){
       addOrderByDescending(PrivateCustomer.NAME_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.NAME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.NAME_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByEmailAscending(){
       addOrderByAscending(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByEmailDescending(){
       addOrderByDescending(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.EMAIL_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByPhoneAscending(){
       addOrderByAscending(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByPhoneDescending(){
       addOrderByDescending(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressAscending(){
       addOrderByAscending(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressDescending(){
       addOrderByDescending(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByAddressAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.ADDRESS_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByIdNumberAscending(){
       addOrderByAscending(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByIdNumberDescending(){
       addOrderByDescending(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByIdNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByIdNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.ID_NUMBER_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(PrivateCustomer.CREATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(PrivateCustomer.CREATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(PrivateCustomer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(PrivateCustomer.UPDATE_TIME_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByVersionAscending(){
       addOrderByAscending(PrivateCustomer.VERSION_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByVersionDescending(){
       addOrderByDescending(PrivateCustomer.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public PrivateCustomerRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PrivateCustomerRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PrivateCustomerRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PrivateCustomerRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PrivateCustomerRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}