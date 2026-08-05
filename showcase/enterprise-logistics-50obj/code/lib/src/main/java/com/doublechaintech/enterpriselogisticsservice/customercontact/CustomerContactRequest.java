package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomerContactRequest<T extends CustomerContact> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public CustomerContactRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public CustomerContactRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public CustomerContactRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public CustomerContactRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public CustomerContactRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public CustomerContactRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public CustomerContactRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (CustomerContactRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public CustomerContactRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public CustomerContactRequest<T> matchingAnyOf(CustomerContactRequest customerContact){
        super.internalMatchAny(customerContact);
        return this;
    }

    public CustomerContactRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public CustomerContactRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public CustomerContactRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public CustomerContactRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectFirstName().selectLastName().selectEmail().selectPhone().selectIsPrimary().selectPrivateCustomerIdOnly().selectCorporateCustomerIdOnly().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public CustomerContactRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CustomerContactRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectFirstName().selectLastName().selectEmail().selectPhone().selectIsPrimary().selectPrivateCustomer().selectCorporateCustomer().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public CustomerContactRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectFirstName().selectLastName().selectEmail().selectPhone().selectIsPrimary().selectPrivateCustomer().selectCorporateCustomer().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public CustomerContactRequest<T> selectId(){
       selectProperty(CustomerContact.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectId(){
       unselectProperty(CustomerContact.ID_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectFirstName(){
       selectProperty(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the firstName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  firstName) to fetch firstName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectFirstName(){
       unselectProperty(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectLastName(){
       selectProperty(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the lastName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  lastName) to fetch lastName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectLastName(){
       unselectProperty(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectEmail(){
       selectProperty(CustomerContact.EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the email with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  email) to fetch email property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectEmail(){
       unselectProperty(CustomerContact.EMAIL_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectPhone(){
       selectProperty(CustomerContact.PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the phone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  phone) to fetch phone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectPhone(){
       unselectProperty(CustomerContact.PHONE_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectIsPrimary(){
       selectProperty(CustomerContact.IS_PRIMARY_PROPERTY);
       return this;
    }

    /**
     * fill the isPrimary with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  isPrimary) to fetch isPrimary property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectIsPrimary(){
       unselectProperty(CustomerContact.IS_PRIMARY_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectPrivateCustomerIdOnly(){
       selectProperty(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> selectPrivateCustomer(){
        return selectPrivateCustomerWith(Q.privateCustomers().unlimited().selectSelf());
    }

    public CustomerContactRequest<T> selectPrivateCustomerWith(PrivateCustomerRequest privateCustomer){
       selectProperty(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       enhanceRelation(CustomerContact.PRIVATE_CUSTOMER_PROPERTY, privateCustomer);
       return this;
    }

    public CustomerContactRequest<T> unselectPrivateCustomer(){
       unselectProperty(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectCorporateCustomerIdOnly(){
       selectProperty(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> selectCorporateCustomer(){
        return selectCorporateCustomerWith(Q.corporateCustomers().unlimited().selectSelf());
    }

    public CustomerContactRequest<T> selectCorporateCustomerWith(CorporateCustomerRequest corporateCustomer){
       selectProperty(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       enhanceRelation(CustomerContact.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer);
       return this;
    }

    public CustomerContactRequest<T> unselectCorporateCustomer(){
       unselectProperty(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectCreatedAt(){
       selectProperty(CustomerContact.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectCreatedAt(){
       unselectProperty(CustomerContact.CREATED_AT_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectUpdatedAt(){
       selectProperty(CustomerContact.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectUpdatedAt(){
       unselectProperty(CustomerContact.UPDATED_AT_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> selectVersion(){
       selectProperty(CustomerContact.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectVersion(){
       unselectProperty(CustomerContact.VERSION_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.ID_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public CustomerContactRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public CustomerContactRequest<T> filterByFirstName(String... firstName){
      if (firstName == null || firstName.length == 0) {
        throw new IllegalArgumentException("filterByFirstName parameter firstName cannot be empty");
      }
      return appendSearchCriteria(createFirstNameCriteria(Operator.EQUAL, (Object[])firstName));
    }

    public CustomerContactRequest<T> withFirstName(Operator operator, Object... values){
       return appendSearchCriteria(createFirstNameCriteria(operator, values));
    }

    public CustomerContactRequest<T> withFirstNameIsUnknown(){
       return withFirstName(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withFirstNameIsKnown(){
       return withFirstName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createFirstNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.FIRST_NAME_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withFirstNameGreaterThan(String firstName){
       return withFirstName(Operator.GREATER_THAN, firstName);
    }

    public CustomerContactRequest<T> withFirstNameGreaterThanOrEqualTo(String firstName){
       return withFirstName(Operator.GREATER_THAN_OR_EQUAL, firstName);
    }

    public CustomerContactRequest<T> withFirstNameLessThan(String firstName){
       return withFirstName(Operator.LESS_THAN, firstName);
    }

    public CustomerContactRequest<T> withFirstNameLessThanOrEqualTo(String firstName){
       return withFirstName(Operator.LESS_THAN_OR_EQUAL, firstName);
    }

    public CustomerContactRequest<T> withFirstNameBetween(String startOfFirstName, String endOfFirstName){
       return withFirstName(Operator.BETWEEN, startOfFirstName, endOfFirstName);
    }
    public CustomerContactRequest<T> withFirstNameStartingWith(String firstName){
       return withFirstName(Operator.BEGIN_WITH, firstName);
    }
    public CustomerContactRequest<T> withFirstNameContaining(String firstName){
       return withFirstName(Operator.CONTAIN, firstName);
    }

    public CustomerContactRequest<T> withFirstNameEndingWith(String firstName){
       return withFirstName(Operator.END_WITH, firstName);
    }

    public CustomerContactRequest<T> withFirstNameIs(String firstName){
       return withFirstName(Operator.EQUAL, firstName);
    }

    public CustomerContactRequest<T> withFirstNameSoundingLike(String firstName){
       return withFirstName(Operator.SOUNDS_LIKE, firstName);
    }



    public CustomerContactRequest<T> filterByLastName(String... lastName){
      if (lastName == null || lastName.length == 0) {
        throw new IllegalArgumentException("filterByLastName parameter lastName cannot be empty");
      }
      return appendSearchCriteria(createLastNameCriteria(Operator.EQUAL, (Object[])lastName));
    }

    public CustomerContactRequest<T> withLastName(Operator operator, Object... values){
       return appendSearchCriteria(createLastNameCriteria(operator, values));
    }

    public CustomerContactRequest<T> withLastNameIsUnknown(){
       return withLastName(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withLastNameIsKnown(){
       return withLastName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLastNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.LAST_NAME_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withLastNameGreaterThan(String lastName){
       return withLastName(Operator.GREATER_THAN, lastName);
    }

    public CustomerContactRequest<T> withLastNameGreaterThanOrEqualTo(String lastName){
       return withLastName(Operator.GREATER_THAN_OR_EQUAL, lastName);
    }

    public CustomerContactRequest<T> withLastNameLessThan(String lastName){
       return withLastName(Operator.LESS_THAN, lastName);
    }

    public CustomerContactRequest<T> withLastNameLessThanOrEqualTo(String lastName){
       return withLastName(Operator.LESS_THAN_OR_EQUAL, lastName);
    }

    public CustomerContactRequest<T> withLastNameBetween(String startOfLastName, String endOfLastName){
       return withLastName(Operator.BETWEEN, startOfLastName, endOfLastName);
    }
    public CustomerContactRequest<T> withLastNameStartingWith(String lastName){
       return withLastName(Operator.BEGIN_WITH, lastName);
    }
    public CustomerContactRequest<T> withLastNameContaining(String lastName){
       return withLastName(Operator.CONTAIN, lastName);
    }

    public CustomerContactRequest<T> withLastNameEndingWith(String lastName){
       return withLastName(Operator.END_WITH, lastName);
    }

    public CustomerContactRequest<T> withLastNameIs(String lastName){
       return withLastName(Operator.EQUAL, lastName);
    }

    public CustomerContactRequest<T> withLastNameSoundingLike(String lastName){
       return withLastName(Operator.SOUNDS_LIKE, lastName);
    }



    public CustomerContactRequest<T> filterByEmail(String... email){
      if (email == null || email.length == 0) {
        throw new IllegalArgumentException("filterByEmail parameter email cannot be empty");
      }
      return appendSearchCriteria(createEmailCriteria(Operator.EQUAL, (Object[])email));
    }

    public CustomerContactRequest<T> withEmail(Operator operator, Object... values){
       return appendSearchCriteria(createEmailCriteria(operator, values));
    }

    public CustomerContactRequest<T> withEmailIsUnknown(){
       return withEmail(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withEmailIsKnown(){
       return withEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.EMAIL_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withEmailGreaterThan(String email){
       return withEmail(Operator.GREATER_THAN, email);
    }

    public CustomerContactRequest<T> withEmailGreaterThanOrEqualTo(String email){
       return withEmail(Operator.GREATER_THAN_OR_EQUAL, email);
    }

    public CustomerContactRequest<T> withEmailLessThan(String email){
       return withEmail(Operator.LESS_THAN, email);
    }

    public CustomerContactRequest<T> withEmailLessThanOrEqualTo(String email){
       return withEmail(Operator.LESS_THAN_OR_EQUAL, email);
    }

    public CustomerContactRequest<T> withEmailBetween(String startOfEmail, String endOfEmail){
       return withEmail(Operator.BETWEEN, startOfEmail, endOfEmail);
    }
    public CustomerContactRequest<T> withEmailStartingWith(String email){
       return withEmail(Operator.BEGIN_WITH, email);
    }
    public CustomerContactRequest<T> withEmailContaining(String email){
       return withEmail(Operator.CONTAIN, email);
    }

    public CustomerContactRequest<T> withEmailEndingWith(String email){
       return withEmail(Operator.END_WITH, email);
    }

    public CustomerContactRequest<T> withEmailIs(String email){
       return withEmail(Operator.EQUAL, email);
    }

    public CustomerContactRequest<T> withEmailSoundingLike(String email){
       return withEmail(Operator.SOUNDS_LIKE, email);
    }



    public CustomerContactRequest<T> filterByPhone(String... phone){
      if (phone == null || phone.length == 0) {
        throw new IllegalArgumentException("filterByPhone parameter phone cannot be empty");
      }
      return appendSearchCriteria(createPhoneCriteria(Operator.EQUAL, (Object[])phone));
    }

    public CustomerContactRequest<T> withPhone(Operator operator, Object... values){
       return appendSearchCriteria(createPhoneCriteria(operator, values));
    }

    public CustomerContactRequest<T> withPhoneIsUnknown(){
       return withPhone(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withPhoneIsKnown(){
       return withPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.PHONE_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withPhoneGreaterThan(String phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public CustomerContactRequest<T> withPhoneGreaterThanOrEqualTo(String phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public CustomerContactRequest<T> withPhoneLessThan(String phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public CustomerContactRequest<T> withPhoneLessThanOrEqualTo(String phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public CustomerContactRequest<T> withPhoneBetween(String startOfPhone, String endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }
    public CustomerContactRequest<T> withPhoneStartingWith(String phone){
       return withPhone(Operator.BEGIN_WITH, phone);
    }
    public CustomerContactRequest<T> withPhoneContaining(String phone){
       return withPhone(Operator.CONTAIN, phone);
    }

    public CustomerContactRequest<T> withPhoneEndingWith(String phone){
       return withPhone(Operator.END_WITH, phone);
    }

    public CustomerContactRequest<T> withPhoneIs(String phone){
       return withPhone(Operator.EQUAL, phone);
    }

    public CustomerContactRequest<T> withPhoneSoundingLike(String phone){
       return withPhone(Operator.SOUNDS_LIKE, phone);
    }



    public CustomerContactRequest<T> filterByIsPrimary(Boolean... isPrimary){
      if (isPrimary == null || isPrimary.length == 0) {
        throw new IllegalArgumentException("filterByIsPrimary parameter isPrimary cannot be empty");
      }
      return appendSearchCriteria(createIsPrimaryCriteria(Operator.EQUAL, (Object[])isPrimary));
    }

    public CustomerContactRequest<T> withIsPrimary(Operator operator, Object... values){
       return appendSearchCriteria(createIsPrimaryCriteria(operator, values));
    }

    public CustomerContactRequest<T> withIsPrimaryIsUnknown(){
       return withIsPrimary(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withIsPrimaryIsKnown(){
       return withIsPrimary(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIsPrimaryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.IS_PRIMARY_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> whichIsIsPrimary(){
       return withIsPrimary(Operator.EQUAL, true);
    }

    public CustomerContactRequest<T> whichIsNotIsPrimary(){
       return withIsPrimary(Operator.EQUAL, false);
    }


    public CustomerContactRequest<T> filterByPrivateCustomer(PrivateCustomer... privateCustomer){
      if (privateCustomer == null || privateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByPrivateCustomer parameter privateCustomer cannot be empty");
      }
      return appendSearchCriteria(createPrivateCustomerCriteria(Operator.EQUAL, (Object[])privateCustomer));
    }

    public CustomerContactRequest<T> withPrivateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createPrivateCustomerCriteria(operator, values));
    }

    public CustomerContactRequest<T> withPrivateCustomerIsUnknown(){
       return withPrivateCustomer(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withPrivateCustomerIsKnown(){
       return withPrivateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPrivateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.PRIVATE_CUSTOMER_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> filterByPrivateCustomer(Long privateCustomer){
      if(privateCustomer == null){
         return this;
      }
      return withPrivateCustomer(Operator.EQUAL, privateCustomer);
    }
    public CustomerContactRequest<T> withPrivateCustomerMatching(PrivateCustomerRequest privateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(CustomerContact.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, PrivateCustomer.ID_PROPERTY));
    }

    public CustomerContactRequest<T> filterByCorporateCustomer(CorporateCustomer... corporateCustomer){
      if (corporateCustomer == null || corporateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByCorporateCustomer parameter corporateCustomer cannot be empty");
      }
      return appendSearchCriteria(createCorporateCustomerCriteria(Operator.EQUAL, (Object[])corporateCustomer));
    }

    public CustomerContactRequest<T> withCorporateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCorporateCustomerCriteria(operator, values));
    }

    public CustomerContactRequest<T> withCorporateCustomerIsUnknown(){
       return withCorporateCustomer(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withCorporateCustomerIsKnown(){
       return withCorporateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCorporateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.CORPORATE_CUSTOMER_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> filterByCorporateCustomer(Long corporateCustomer){
      if(corporateCustomer == null){
         return this;
      }
      return withCorporateCustomer(Operator.EQUAL, corporateCustomer);
    }
    public CustomerContactRequest<T> withCorporateCustomerMatching(CorporateCustomerRequest corporateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(CustomerContact.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, CorporateCustomer.ID_PROPERTY));
    }

    public CustomerContactRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public CustomerContactRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public CustomerContactRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.CREATED_AT_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public CustomerContactRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public CustomerContactRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public CustomerContactRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public CustomerContactRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public CustomerContactRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.UPDATED_AT_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public CustomerContactRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public CustomerContactRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public CustomerContactRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public CustomerContactRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public CustomerContactRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.VERSION_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public CustomerContactRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public CustomerContactRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public CustomerContactRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public CustomerContactRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public CustomerContactRequest<T> count(){
        super.count();
        return this;
    }
    public CustomerContactRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public CustomerContactRequest<T> groupByPrivateCustomerWithDetails(){
       return groupByPrivateCustomerWithDetails(Q.privateCustomers().unlimited());
    }

    public CustomerContactRequest<T> groupByPrivateCustomerWithDetails(PrivateCustomerRequest subRequest){
       aggregate(CustomerContact.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }

    public CustomerContactRequest<T> groupByCorporateCustomerWithDetails(){
       return groupByCorporateCustomerWithDetails(Q.corporateCustomers().unlimited());
    }

    public CustomerContactRequest<T> groupByCorporateCustomerWithDetails(CorporateCustomerRequest subRequest){
       aggregate(CustomerContact.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }





    public CustomerContactRequest<T> groupById(){
       groupBy(CustomerContact.ID_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByIdAs(String retName){
       groupBy(retName, CustomerContact.ID_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.ID_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByFirstName(){
       groupBy(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByFirstNameAs(String retName){
       groupBy(retName, CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByFirstNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.FIRST_NAME_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByLastName(){
       groupBy(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByLastNameAs(String retName){
       groupBy(retName, CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByLastNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.LAST_NAME_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByEmail(){
       groupBy(CustomerContact.EMAIL_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByEmailAs(String retName){
       groupBy(retName, CustomerContact.EMAIL_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.EMAIL_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByPhone(){
       groupBy(CustomerContact.PHONE_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByPhoneAs(String retName){
       groupBy(retName, CustomerContact.PHONE_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.PHONE_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByIsPrimary(){
       groupBy(CustomerContact.IS_PRIMARY_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByIsPrimaryAs(String retName){
       groupBy(retName, CustomerContact.IS_PRIMARY_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByIsPrimaryWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.IS_PRIMARY_PROPERTY, function);
       return this;
    }
    public CustomerContactRequest<T> groupByPrivateCustomerWith(PrivateCustomerRequest subRequest){
       groupBy(CustomerContact.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public CustomerContactRequest<T> groupByPrivateCustomer(){
       groupBy(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByPrivateCustomerAs(String retName){
       groupBy(retName, CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByPrivateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.PRIVATE_CUSTOMER_PROPERTY, function);
       return this;
    }
    public CustomerContactRequest<T> groupByCorporateCustomerWith(CorporateCustomerRequest subRequest){
       groupBy(CustomerContact.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public CustomerContactRequest<T> groupByCorporateCustomer(){
       groupBy(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByCorporateCustomerAs(String retName){
       groupBy(retName, CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByCorporateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.CORPORATE_CUSTOMER_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByCreatedAt(){
       groupBy(CustomerContact.CREATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, CustomerContact.CREATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.CREATED_AT_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByUpdatedAt(){
       groupBy(CustomerContact.UPDATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, CustomerContact.UPDATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public CustomerContactRequest<T> groupByVersion(){
       groupBy(CustomerContact.VERSION_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByVersionAs(String retName){
       groupBy(retName, CustomerContact.VERSION_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.VERSION_PROPERTY, function);
       return this;
    }



    public CustomerContactRequest<T> orderByIdAscending(){
       addOrderByAscending(CustomerContact.ID_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByIdDescending(){
       addOrderByDescending(CustomerContact.ID_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByFirstNameAscending(){
       addOrderByAscending(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByFirstNameDescending(){
       addOrderByDescending(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByFirstNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByFirstNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerContact.FIRST_NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByLastNameAscending(){
       addOrderByAscending(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByLastNameDescending(){
       addOrderByDescending(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByLastNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByLastNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerContact.LAST_NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByEmailAscending(){
       addOrderByAscending(CustomerContact.EMAIL_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByEmailDescending(){
       addOrderByDescending(CustomerContact.EMAIL_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerContact.EMAIL_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerContact.EMAIL_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByPhoneAscending(){
       addOrderByAscending(CustomerContact.PHONE_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByPhoneDescending(){
       addOrderByDescending(CustomerContact.PHONE_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerContact.PHONE_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerContact.PHONE_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByIsPrimaryAscending(){
       addOrderByAscending(CustomerContact.IS_PRIMARY_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByIsPrimaryDescending(){
       addOrderByDescending(CustomerContact.IS_PRIMARY_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByPrivateCustomerAscending(){
       addOrderByAscending(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByPrivateCustomerDescending(){
       addOrderByDescending(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByCorporateCustomerAscending(){
       addOrderByAscending(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByCorporateCustomerDescending(){
       addOrderByDescending(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(CustomerContact.CREATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(CustomerContact.CREATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(CustomerContact.UPDATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(CustomerContact.UPDATED_AT_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByVersionAscending(){
       addOrderByAscending(CustomerContact.VERSION_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByVersionDescending(){
       addOrderByDescending(CustomerContact.VERSION_PROPERTY);
       return this;
    }


    public PrivateCustomerRequest rollUpToPrivateCustomer(){
       PrivateCustomerRequest privateCustomer = Q.privateCustomers().unlimited();
       this.withPrivateCustomerMatching(privateCustomer)
           .groupByPrivateCustomerWith(privateCustomer);
       return privateCustomer;
    }

    public CorporateCustomerRequest rollUpToCorporateCustomer(){
       CorporateCustomerRequest corporateCustomer = Q.corporateCustomers().unlimited();
       this.withCorporateCustomerMatching(corporateCustomer)
           .groupByCorporateCustomerWith(corporateCustomer);
       return corporateCustomer;
    }





   public CustomerContactRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer){
       return facetByPrivateCustomerAs(facetName, privateCustomer, true);
   }

   public CustomerContactRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer, boolean includeAllFacets){
       addFacet(facetName, CustomerContact.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, includeAllFacets);
       return this;
   }
   public CustomerContactRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer){
       return facetByCorporateCustomerAs(facetName, corporateCustomer, true);
   }

   public CustomerContactRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer, boolean includeAllFacets){
       addFacet(facetName, CustomerContact.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public CustomerContactRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public CustomerContactRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public CustomerContactRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public CustomerContactRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public CustomerContactRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}