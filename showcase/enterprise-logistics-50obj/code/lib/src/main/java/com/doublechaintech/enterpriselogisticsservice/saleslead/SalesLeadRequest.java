package com.doublechaintech.enterpriselogisticsservice.saleslead;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class SalesLeadRequest<T extends SalesLead> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public SalesLeadRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public SalesLeadRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public SalesLeadRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public SalesLeadRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public SalesLeadRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public SalesLeadRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public SalesLeadRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (SalesLeadRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public SalesLeadRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public SalesLeadRequest<T> matchingAnyOf(SalesLeadRequest salesLead){
        super.internalMatchAny(salesLead);
        return this;
    }

    public SalesLeadRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public SalesLeadRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public SalesLeadRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public SalesLeadRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectEmail().selectPhone().selectSource().selectStatus().selectEstimatedValue().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public SalesLeadRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SalesLeadRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectEmail().selectPhone().selectSource().selectStatus().selectEstimatedValue().selectCreatedTime().selectUpdateTime().selectVersion();
    }

    public SalesLeadRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectEmail().selectPhone().selectSource().selectStatus().selectEstimatedValue().selectCreatedTime().selectUpdateTime().selectVersion();
    }


    public SalesLeadRequest<T> selectId(){
       selectProperty(SalesLead.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectId(){
       unselectProperty(SalesLead.ID_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectName(){
       selectProperty(SalesLead.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectName(){
       unselectProperty(SalesLead.NAME_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectEmail(){
       selectProperty(SalesLead.EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the email with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  email) to fetch email property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectEmail(){
       unselectProperty(SalesLead.EMAIL_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectPhone(){
       selectProperty(SalesLead.PHONE_PROPERTY);
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
    public SalesLeadRequest<T> selectPhone(AggrFunction aggrFunction){
       selectProperty(SalesLead.PHONE_PROPERTY, aggrFunction);
       return this;
    }


    public SalesLeadRequest<T> unselectPhone(){
       unselectProperty(SalesLead.PHONE_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectSource(){
       selectProperty(SalesLead.SOURCE_PROPERTY);
       return this;
    }

    /**
     * fill the source with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  source) to fetch source property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectSource(){
       unselectProperty(SalesLead.SOURCE_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectStatus(){
       selectProperty(SalesLead.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectStatus(){
       unselectProperty(SalesLead.STATUS_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectEstimatedValue(){
       selectProperty(SalesLead.ESTIMATED_VALUE_PROPERTY);
       return this;
    }

    /**
     * fill the estimatedValue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  estimatedValue) to fetch estimatedValue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the estimatedValue with customized aggrFunction, TEAQL uses ({aggrFunction}(estimatedValue) AS estimatedValue to fetch estimatedValue property.
     * @param aggrFunction  aggrFunction
     */
    public SalesLeadRequest<T> selectEstimatedValue(AggrFunction aggrFunction){
       selectProperty(SalesLead.ESTIMATED_VALUE_PROPERTY, aggrFunction);
       return this;
    }


    public SalesLeadRequest<T> unselectEstimatedValue(){
       unselectProperty(SalesLead.ESTIMATED_VALUE_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectCreatedTime(){
       selectProperty(SalesLead.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectCreatedTime(){
       unselectProperty(SalesLead.CREATED_TIME_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectUpdateTime(){
       selectProperty(SalesLead.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectUpdateTime(){
       unselectProperty(SalesLead.UPDATE_TIME_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> selectVersion(){
       selectProperty(SalesLead.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectVersion(){
       unselectProperty(SalesLead.VERSION_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.ID_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public SalesLeadRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public SalesLeadRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public SalesLeadRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public SalesLeadRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.NAME_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public SalesLeadRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public SalesLeadRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public SalesLeadRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public SalesLeadRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public SalesLeadRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public SalesLeadRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public SalesLeadRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public SalesLeadRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public SalesLeadRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public SalesLeadRequest<T> filterByEmail(String... email){
      if (email == null || email.length == 0) {
        throw new IllegalArgumentException("filterByEmail parameter email cannot be empty");
      }
      return appendSearchCriteria(createEmailCriteria(Operator.EQUAL, (Object[])email));
    }

    public SalesLeadRequest<T> withEmail(Operator operator, Object... values){
       return appendSearchCriteria(createEmailCriteria(operator, values));
    }

    public SalesLeadRequest<T> withEmailIsUnknown(){
       return withEmail(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withEmailIsKnown(){
       return withEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.EMAIL_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withEmailGreaterThan(String email){
       return withEmail(Operator.GREATER_THAN, email);
    }

    public SalesLeadRequest<T> withEmailGreaterThanOrEqualTo(String email){
       return withEmail(Operator.GREATER_THAN_OR_EQUAL, email);
    }

    public SalesLeadRequest<T> withEmailLessThan(String email){
       return withEmail(Operator.LESS_THAN, email);
    }

    public SalesLeadRequest<T> withEmailLessThanOrEqualTo(String email){
       return withEmail(Operator.LESS_THAN_OR_EQUAL, email);
    }

    public SalesLeadRequest<T> withEmailBetween(String startOfEmail, String endOfEmail){
       return withEmail(Operator.BETWEEN, startOfEmail, endOfEmail);
    }
    public SalesLeadRequest<T> withEmailStartingWith(String email){
       return withEmail(Operator.BEGIN_WITH, email);
    }
    public SalesLeadRequest<T> withEmailContaining(String email){
       return withEmail(Operator.CONTAIN, email);
    }

    public SalesLeadRequest<T> withEmailEndingWith(String email){
       return withEmail(Operator.END_WITH, email);
    }

    public SalesLeadRequest<T> withEmailIs(String email){
       return withEmail(Operator.EQUAL, email);
    }

    public SalesLeadRequest<T> withEmailSoundingLike(String email){
       return withEmail(Operator.SOUNDS_LIKE, email);
    }



    public SalesLeadRequest<T> filterByPhone(Integer... phone){
      if (phone == null || phone.length == 0) {
        throw new IllegalArgumentException("filterByPhone parameter phone cannot be empty");
      }
      return appendSearchCriteria(createPhoneCriteria(Operator.EQUAL, (Object[])phone));
    }

    public SalesLeadRequest<T> withPhone(Operator operator, Object... values){
       return appendSearchCriteria(createPhoneCriteria(operator, values));
    }

    public SalesLeadRequest<T> withPhoneIsUnknown(){
       return withPhone(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withPhoneIsKnown(){
       return withPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.PHONE_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withPhoneGreaterThan(Integer phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public SalesLeadRequest<T> withPhoneGreaterThanOrEqualTo(Integer phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public SalesLeadRequest<T> withPhoneLessThan(Integer phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public SalesLeadRequest<T> withPhoneLessThanOrEqualTo(Integer phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public SalesLeadRequest<T> withPhoneBetween(Integer startOfPhone, Integer endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }



    public SalesLeadRequest<T> filterBySource(String... source){
      if (source == null || source.length == 0) {
        throw new IllegalArgumentException("filterBySource parameter source cannot be empty");
      }
      return appendSearchCriteria(createSourceCriteria(Operator.EQUAL, (Object[])source));
    }

    public SalesLeadRequest<T> withSource(Operator operator, Object... values){
       return appendSearchCriteria(createSourceCriteria(operator, values));
    }

    public SalesLeadRequest<T> withSourceIsUnknown(){
       return withSource(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withSourceIsKnown(){
       return withSource(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createSourceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.SOURCE_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withSourceGreaterThan(String source){
       return withSource(Operator.GREATER_THAN, source);
    }

    public SalesLeadRequest<T> withSourceGreaterThanOrEqualTo(String source){
       return withSource(Operator.GREATER_THAN_OR_EQUAL, source);
    }

    public SalesLeadRequest<T> withSourceLessThan(String source){
       return withSource(Operator.LESS_THAN, source);
    }

    public SalesLeadRequest<T> withSourceLessThanOrEqualTo(String source){
       return withSource(Operator.LESS_THAN_OR_EQUAL, source);
    }

    public SalesLeadRequest<T> withSourceBetween(String startOfSource, String endOfSource){
       return withSource(Operator.BETWEEN, startOfSource, endOfSource);
    }
    public SalesLeadRequest<T> withSourceStartingWith(String source){
       return withSource(Operator.BEGIN_WITH, source);
    }
    public SalesLeadRequest<T> withSourceContaining(String source){
       return withSource(Operator.CONTAIN, source);
    }

    public SalesLeadRequest<T> withSourceEndingWith(String source){
       return withSource(Operator.END_WITH, source);
    }

    public SalesLeadRequest<T> withSourceIs(String source){
       return withSource(Operator.EQUAL, source);
    }

    public SalesLeadRequest<T> withSourceSoundingLike(String source){
       return withSource(Operator.SOUNDS_LIKE, source);
    }



    public SalesLeadRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public SalesLeadRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public SalesLeadRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.STATUS_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public SalesLeadRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public SalesLeadRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public SalesLeadRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public SalesLeadRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public SalesLeadRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public SalesLeadRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public SalesLeadRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public SalesLeadRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public SalesLeadRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public SalesLeadRequest<T> filterByEstimatedValue(BigDecimal... estimatedValue){
      if (estimatedValue == null || estimatedValue.length == 0) {
        throw new IllegalArgumentException("filterByEstimatedValue parameter estimatedValue cannot be empty");
      }
      return appendSearchCriteria(createEstimatedValueCriteria(Operator.EQUAL, (Object[])estimatedValue));
    }

    public SalesLeadRequest<T> withEstimatedValue(Operator operator, Object... values){
       return appendSearchCriteria(createEstimatedValueCriteria(operator, values));
    }

    public SalesLeadRequest<T> withEstimatedValueIsUnknown(){
       return withEstimatedValue(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withEstimatedValueIsKnown(){
       return withEstimatedValue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEstimatedValueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.ESTIMATED_VALUE_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withEstimatedValueGreaterThan(BigDecimal estimatedValue){
       return withEstimatedValue(Operator.GREATER_THAN, estimatedValue);
    }

    public SalesLeadRequest<T> withEstimatedValueGreaterThanOrEqualTo(BigDecimal estimatedValue){
       return withEstimatedValue(Operator.GREATER_THAN_OR_EQUAL, estimatedValue);
    }

    public SalesLeadRequest<T> withEstimatedValueLessThan(BigDecimal estimatedValue){
       return withEstimatedValue(Operator.LESS_THAN, estimatedValue);
    }

    public SalesLeadRequest<T> withEstimatedValueLessThanOrEqualTo(BigDecimal estimatedValue){
       return withEstimatedValue(Operator.LESS_THAN_OR_EQUAL, estimatedValue);
    }

    public SalesLeadRequest<T> withEstimatedValueBetween(BigDecimal startOfEstimatedValue, BigDecimal endOfEstimatedValue){
       return withEstimatedValue(Operator.BETWEEN, startOfEstimatedValue, endOfEstimatedValue);
    }



    public SalesLeadRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public SalesLeadRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public SalesLeadRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.CREATED_TIME_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public SalesLeadRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public SalesLeadRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public SalesLeadRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public SalesLeadRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public SalesLeadRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.UPDATE_TIME_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public SalesLeadRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public SalesLeadRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public SalesLeadRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public SalesLeadRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public SalesLeadRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.VERSION_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public SalesLeadRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public SalesLeadRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public SalesLeadRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public SalesLeadRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public SalesLeadRequest<T> count(){
        super.count();
        return this;
    }
    public SalesLeadRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public SalesLeadRequest minPhone(){
        return minPhoneAs(prefix("minOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest minPhoneAs(String retName){
        super.min(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest maxPhone(){
        return maxPhoneAs(prefix("maxOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest maxPhoneAs(String retName){
        super.max(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest sumPhone(){
        return sumPhoneAs(prefix("sumOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest sumPhoneAs(String retName){
        super.sum(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest avgPhone(){
        return avgPhoneAs(prefix("avgOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest avgPhoneAs(String retName){
        super.avg(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest standardDeviationPhone(){
        return standardDeviationPhoneAs(prefix("standardDeviationOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest standardDeviationPhoneAs(String retName){
        super.standardDeviation(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest squareRootOfPopulationStandardDeviationPhone(){
        return squareRootOfPopulationStandardDeviationPhoneAs(prefix("squareRootOfPopulationStandardDeviationOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest squareRootOfPopulationStandardDeviationPhoneAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest sampleVariancePhone(){
        return sampleVariancePhoneAs(prefix("sampleVarianceOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest sampleVariancePhoneAs(String retName){
        super.sampleVariance(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest samplePopulationVariancePhone(){
        return samplePopulationVariancePhoneAs(prefix("samplePopulationVarianceOf",SalesLead.PHONE_PROPERTY));
    }

    public SalesLeadRequest samplePopulationVariancePhoneAs(String retName){
        super.samplePopulationVariance(retName, SalesLead.PHONE_PROPERTY);
        return this;
    }
    public SalesLeadRequest minEstimatedValue(){
        return minEstimatedValueAs(prefix("minOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest minEstimatedValueAs(String retName){
        super.min(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest maxEstimatedValue(){
        return maxEstimatedValueAs(prefix("maxOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest maxEstimatedValueAs(String retName){
        super.max(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest sumEstimatedValue(){
        return sumEstimatedValueAs(prefix("sumOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest sumEstimatedValueAs(String retName){
        super.sum(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest avgEstimatedValue(){
        return avgEstimatedValueAs(prefix("avgOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest avgEstimatedValueAs(String retName){
        super.avg(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest standardDeviationEstimatedValue(){
        return standardDeviationEstimatedValueAs(prefix("standardDeviationOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest standardDeviationEstimatedValueAs(String retName){
        super.standardDeviation(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest squareRootOfPopulationStandardDeviationEstimatedValue(){
        return squareRootOfPopulationStandardDeviationEstimatedValueAs(prefix("squareRootOfPopulationStandardDeviationOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest squareRootOfPopulationStandardDeviationEstimatedValueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest sampleVarianceEstimatedValue(){
        return sampleVarianceEstimatedValueAs(prefix("sampleVarianceOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest sampleVarianceEstimatedValueAs(String retName){
        super.sampleVariance(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }
    public SalesLeadRequest samplePopulationVarianceEstimatedValue(){
        return samplePopulationVarianceEstimatedValueAs(prefix("samplePopulationVarianceOf",SalesLead.ESTIMATED_VALUE_PROPERTY));
    }

    public SalesLeadRequest samplePopulationVarianceEstimatedValueAs(String retName){
        super.samplePopulationVariance(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
        return this;
    }

    public SalesLeadRequest<T> groupById(){
       groupBy(SalesLead.ID_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByIdAs(String retName){
       groupBy(retName, SalesLead.ID_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.ID_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByName(){
       groupBy(SalesLead.NAME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByNameAs(String retName){
       groupBy(retName, SalesLead.NAME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.NAME_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByEmail(){
       groupBy(SalesLead.EMAIL_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByEmailAs(String retName){
       groupBy(retName, SalesLead.EMAIL_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.EMAIL_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByPhone(){
       groupBy(SalesLead.PHONE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByPhoneAs(String retName){
       groupBy(retName, SalesLead.PHONE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.PHONE_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupBySource(){
       groupBy(SalesLead.SOURCE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupBySourceAs(String retName){
       groupBy(retName, SalesLead.SOURCE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupBySourceWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.SOURCE_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByStatus(){
       groupBy(SalesLead.STATUS_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByStatusAs(String retName){
       groupBy(retName, SalesLead.STATUS_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.STATUS_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByEstimatedValue(){
       groupBy(SalesLead.ESTIMATED_VALUE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByEstimatedValueAs(String retName){
       groupBy(retName, SalesLead.ESTIMATED_VALUE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByEstimatedValueWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.ESTIMATED_VALUE_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByCreatedTime(){
       groupBy(SalesLead.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, SalesLead.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByUpdateTime(){
       groupBy(SalesLead.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, SalesLead.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public SalesLeadRequest<T> groupByVersion(){
       groupBy(SalesLead.VERSION_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByVersionAs(String retName){
       groupBy(retName, SalesLead.VERSION_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.VERSION_PROPERTY, function);
       return this;
    }



    public SalesLeadRequest<T> orderByIdAscending(){
       addOrderByAscending(SalesLead.ID_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByIdDescending(){
       addOrderByDescending(SalesLead.ID_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByNameAscending(){
       addOrderByAscending(SalesLead.NAME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByNameDescending(){
       addOrderByDescending(SalesLead.NAME_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesLead.NAME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesLead.NAME_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByEmailAscending(){
       addOrderByAscending(SalesLead.EMAIL_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByEmailDescending(){
       addOrderByDescending(SalesLead.EMAIL_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesLead.EMAIL_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesLead.EMAIL_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByPhoneAscending(){
       addOrderByAscending(SalesLead.PHONE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByPhoneDescending(){
       addOrderByDescending(SalesLead.PHONE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderBySourceAscending(){
       addOrderByAscending(SalesLead.SOURCE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderBySourceDescending(){
       addOrderByDescending(SalesLead.SOURCE_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderBySourceAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesLead.SOURCE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderBySourceDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesLead.SOURCE_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByStatusAscending(){
       addOrderByAscending(SalesLead.STATUS_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByStatusDescending(){
       addOrderByDescending(SalesLead.STATUS_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesLead.STATUS_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesLead.STATUS_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByEstimatedValueAscending(){
       addOrderByAscending(SalesLead.ESTIMATED_VALUE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByEstimatedValueDescending(){
       addOrderByDescending(SalesLead.ESTIMATED_VALUE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(SalesLead.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(SalesLead.CREATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(SalesLead.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(SalesLead.UPDATE_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByVersionAscending(){
       addOrderByAscending(SalesLead.VERSION_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByVersionDescending(){
       addOrderByDescending(SalesLead.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public SalesLeadRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public SalesLeadRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public SalesLeadRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public SalesLeadRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public SalesLeadRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}