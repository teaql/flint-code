package com.doublechaintech.enterpriselogisticsservice.saleslead;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
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
        return selectId().selectName().selectCompany().selectEmail().selectPhone().selectSource().selectStatus().selectAssignedToIdOnly().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public SalesLeadRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SalesLeadRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCompany().selectEmail().selectPhone().selectSource().selectStatus().selectAssignedTo().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public SalesLeadRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectCompany().selectEmail().selectPhone().selectSource().selectStatus().selectAssignedTo().selectCreatedTime().selectUpdatedTime().selectVersion();
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
    public SalesLeadRequest<T> selectCompany(){
       selectProperty(SalesLead.COMPANY_PROPERTY);
       return this;
    }

    /**
     * fill the company with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  company) to fetch company property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectCompany(){
       unselectProperty(SalesLead.COMPANY_PROPERTY);
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
    public SalesLeadRequest<T> selectAssignedToIdOnly(){
       selectProperty(SalesLead.ASSIGNED_TO_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> selectAssignedTo(){
        return selectAssignedToWith(Q.staffMembers().unlimited().selectSelf());
    }

    public SalesLeadRequest<T> selectAssignedToWith(StaffMemberRequest assignedTo){
       selectProperty(SalesLead.ASSIGNED_TO_PROPERTY);
       enhanceRelation(SalesLead.ASSIGNED_TO_PROPERTY, assignedTo);
       return this;
    }

    public SalesLeadRequest<T> unselectAssignedTo(){
       unselectProperty(SalesLead.ASSIGNED_TO_PROPERTY);
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
    public SalesLeadRequest<T> selectUpdatedTime(){
       selectProperty(SalesLead.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalesLeadRequest<T> unselectUpdatedTime(){
       unselectProperty(SalesLead.UPDATED_TIME_PROPERTY);
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



    public SalesLeadRequest<T> filterByCompany(String... company){
      if (company == null || company.length == 0) {
        throw new IllegalArgumentException("filterByCompany parameter company cannot be empty");
      }
      return appendSearchCriteria(createCompanyCriteria(Operator.EQUAL, (Object[])company));
    }

    public SalesLeadRequest<T> withCompany(Operator operator, Object... values){
       return appendSearchCriteria(createCompanyCriteria(operator, values));
    }

    public SalesLeadRequest<T> withCompanyIsUnknown(){
       return withCompany(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withCompanyIsKnown(){
       return withCompany(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCompanyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.COMPANY_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withCompanyGreaterThan(String company){
       return withCompany(Operator.GREATER_THAN, company);
    }

    public SalesLeadRequest<T> withCompanyGreaterThanOrEqualTo(String company){
       return withCompany(Operator.GREATER_THAN_OR_EQUAL, company);
    }

    public SalesLeadRequest<T> withCompanyLessThan(String company){
       return withCompany(Operator.LESS_THAN, company);
    }

    public SalesLeadRequest<T> withCompanyLessThanOrEqualTo(String company){
       return withCompany(Operator.LESS_THAN_OR_EQUAL, company);
    }

    public SalesLeadRequest<T> withCompanyBetween(String startOfCompany, String endOfCompany){
       return withCompany(Operator.BETWEEN, startOfCompany, endOfCompany);
    }
    public SalesLeadRequest<T> withCompanyStartingWith(String company){
       return withCompany(Operator.BEGIN_WITH, company);
    }
    public SalesLeadRequest<T> withCompanyContaining(String company){
       return withCompany(Operator.CONTAIN, company);
    }

    public SalesLeadRequest<T> withCompanyEndingWith(String company){
       return withCompany(Operator.END_WITH, company);
    }

    public SalesLeadRequest<T> withCompanyIs(String company){
       return withCompany(Operator.EQUAL, company);
    }

    public SalesLeadRequest<T> withCompanySoundingLike(String company){
       return withCompany(Operator.SOUNDS_LIKE, company);
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



    public SalesLeadRequest<T> filterByPhone(String... phone){
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

    public SalesLeadRequest<T> withPhoneGreaterThan(String phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public SalesLeadRequest<T> withPhoneGreaterThanOrEqualTo(String phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public SalesLeadRequest<T> withPhoneLessThan(String phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public SalesLeadRequest<T> withPhoneLessThanOrEqualTo(String phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public SalesLeadRequest<T> withPhoneBetween(String startOfPhone, String endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }
    public SalesLeadRequest<T> withPhoneStartingWith(String phone){
       return withPhone(Operator.BEGIN_WITH, phone);
    }
    public SalesLeadRequest<T> withPhoneContaining(String phone){
       return withPhone(Operator.CONTAIN, phone);
    }

    public SalesLeadRequest<T> withPhoneEndingWith(String phone){
       return withPhone(Operator.END_WITH, phone);
    }

    public SalesLeadRequest<T> withPhoneIs(String phone){
       return withPhone(Operator.EQUAL, phone);
    }

    public SalesLeadRequest<T> withPhoneSoundingLike(String phone){
       return withPhone(Operator.SOUNDS_LIKE, phone);
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



    public SalesLeadRequest<T> filterByAssignedTo(StaffMember... assignedTo){
      if (assignedTo == null || assignedTo.length == 0) {
        throw new IllegalArgumentException("filterByAssignedTo parameter assignedTo cannot be empty");
      }
      return appendSearchCriteria(createAssignedToCriteria(Operator.EQUAL, (Object[])assignedTo));
    }

    public SalesLeadRequest<T> withAssignedTo(Operator operator, Object... values){
       return appendSearchCriteria(createAssignedToCriteria(operator, values));
    }

    public SalesLeadRequest<T> withAssignedToIsUnknown(){
       return withAssignedTo(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withAssignedToIsKnown(){
       return withAssignedTo(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAssignedToCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.ASSIGNED_TO_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> filterByAssignedTo(Long assignedTo){
      if(assignedTo == null){
         return this;
      }
      return withAssignedTo(Operator.EQUAL, assignedTo);
    }
    public SalesLeadRequest<T> withAssignedToMatching(StaffMemberRequest assignedTo){
       return appendSearchCriteria(new SubQuerySearchCriteria(SalesLead.ASSIGNED_TO_PROPERTY, assignedTo, StaffMember.ID_PROPERTY));
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




    public SalesLeadRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public SalesLeadRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public SalesLeadRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public SalesLeadRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalesLead.UPDATED_TIME_PROPERTY, operator, values);
    }

    public SalesLeadRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public SalesLeadRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public SalesLeadRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
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
    public SalesLeadRequest<T> groupByAssignedToWithDetails(){
       return groupByAssignedToWithDetails(Q.staffMembers().unlimited());
    }

    public SalesLeadRequest<T> groupByAssignedToWithDetails(StaffMemberRequest subRequest){
       aggregate(SalesLead.ASSIGNED_TO_PROPERTY, subRequest);
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

    public SalesLeadRequest<T> groupByCompany(){
       groupBy(SalesLead.COMPANY_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByCompanyAs(String retName){
       groupBy(retName, SalesLead.COMPANY_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByCompanyWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.COMPANY_PROPERTY, function);
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
    public SalesLeadRequest<T> groupByAssignedToWith(StaffMemberRequest subRequest){
       groupBy(SalesLead.ASSIGNED_TO_PROPERTY, subRequest);
       return this;
    }
    public SalesLeadRequest<T> groupByAssignedTo(){
       groupBy(SalesLead.ASSIGNED_TO_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByAssignedToAs(String retName){
       groupBy(retName, SalesLead.ASSIGNED_TO_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByAssignedToWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.ASSIGNED_TO_PROPERTY, function);
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

    public SalesLeadRequest<T> groupByUpdatedTime(){
       groupBy(SalesLead.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, SalesLead.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalesLead.UPDATED_TIME_PROPERTY, function);
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
    public SalesLeadRequest<T> orderByCompanyAscending(){
       addOrderByAscending(SalesLead.COMPANY_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByCompanyDescending(){
       addOrderByDescending(SalesLead.COMPANY_PROPERTY);
       return this;
    }
    public SalesLeadRequest<T> orderByCompanyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesLead.COMPANY_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByCompanyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesLead.COMPANY_PROPERTY);
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
    public SalesLeadRequest<T> orderByPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalesLead.PHONE_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalesLead.PHONE_PROPERTY);
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
    public SalesLeadRequest<T> orderByAssignedToAscending(){
       addOrderByAscending(SalesLead.ASSIGNED_TO_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByAssignedToDescending(){
       addOrderByDescending(SalesLead.ASSIGNED_TO_PROPERTY);
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

    public SalesLeadRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(SalesLead.UPDATED_TIME_PROPERTY);
       return this;
    }

    public SalesLeadRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(SalesLead.UPDATED_TIME_PROPERTY);
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


    public StaffMemberRequest rollUpToAssignedTo(){
       StaffMemberRequest assignedTo = Q.staffMembers().unlimited();
       this.withAssignedToMatching(assignedTo)
           .groupByAssignedToWith(assignedTo);
       return assignedTo;
    }





   public SalesLeadRequest<T> facetByAssignedToAs(String facetName, StaffMemberRequest assignedTo){
       return facetByAssignedToAs(facetName, assignedTo, true);
   }

   public SalesLeadRequest<T> facetByAssignedToAs(String facetName, StaffMemberRequest assignedTo, boolean includeAllFacets){
       addFacet(facetName, SalesLead.ASSIGNED_TO_PROPERTY, assignedTo, includeAllFacets);
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