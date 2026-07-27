package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractRequest;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class CorporateCustomerRequest<T extends CorporateCustomer> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public CorporateCustomerRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public CorporateCustomerRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public CorporateCustomerRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public CorporateCustomerRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public CorporateCustomerRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public CorporateCustomerRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public CorporateCustomerRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (CorporateCustomerRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public CorporateCustomerRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public CorporateCustomerRequest<T> matchingAnyOf(CorporateCustomerRequest corporateCustomer){
        super.internalMatchAny(corporateCustomer);
        return this;
    }

    public CorporateCustomerRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public CorporateCustomerRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public CorporateCustomerRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public CorporateCustomerRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectRegistrationNumber().selectIndustry().selectEmployeeCount().selectBillingAddress().selectContactEmail().selectContactPhone().selectCustomerType().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public CorporateCustomerRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CorporateCustomerRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectRegistrationNumber().selectIndustry().selectEmployeeCount().selectBillingAddress().selectContactEmail().selectContactPhone().selectCustomerType().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public CorporateCustomerRequest<T> selectChildren(){
        super.selectAny();
        selectCustomerContactList().selectServiceQuoteList().selectServiceContractList();
        return selectId().selectName().selectRegistrationNumber().selectIndustry().selectEmployeeCount().selectBillingAddress().selectContactEmail().selectContactPhone().selectCustomerType().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public CorporateCustomerRequest<T> selectId(){
       selectProperty(CorporateCustomer.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectId(){
       unselectProperty(CorporateCustomer.ID_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectName(){
       selectProperty(CorporateCustomer.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectName(){
       unselectProperty(CorporateCustomer.NAME_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectRegistrationNumber(){
       selectProperty(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the registrationNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  registrationNumber) to fetch registrationNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectRegistrationNumber(){
       unselectProperty(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectIndustry(){
       selectProperty(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }

    /**
     * fill the industry with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  industry) to fetch industry property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectIndustry(){
       unselectProperty(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectEmployeeCount(){
       selectProperty(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
       return this;
    }

    /**
     * fill the employeeCount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  employeeCount) to fetch employeeCount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the employeeCount with customized aggrFunction, TEAQL uses ({aggrFunction}(employeeCount) AS employeeCount to fetch employeeCount property.
     * @param aggrFunction  aggrFunction
     */
    public CorporateCustomerRequest<T> selectEmployeeCount(AggrFunction aggrFunction){
       selectProperty(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY, aggrFunction);
       return this;
    }


    public CorporateCustomerRequest<T> unselectEmployeeCount(){
       unselectProperty(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectBillingAddress(){
       selectProperty(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }

    /**
     * fill the billingAddress with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  billingAddress) to fetch billingAddress property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectBillingAddress(){
       unselectProperty(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectContactEmail(){
       selectProperty(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the contactEmail with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  contactEmail) to fetch contactEmail property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectContactEmail(){
       unselectProperty(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectContactPhone(){
       selectProperty(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the contactPhone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  contactPhone) to fetch contactPhone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectContactPhone(){
       unselectProperty(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectCustomerType(){
       selectProperty(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the customerType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  customerType) to fetch customerType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectCustomerType(){
       unselectProperty(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectCreatedAt(){
       selectProperty(CorporateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectCreatedAt(){
       unselectProperty(CorporateCustomer.CREATED_AT_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectUpdatedAt(){
       selectProperty(CorporateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectUpdatedAt(){
       unselectProperty(CorporateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectVersion(){
       selectProperty(CorporateCustomer.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectVersion(){
       unselectProperty(CorporateCustomer.VERSION_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectCustomerContactList(){
       return selectCustomerContactListWith(Q.customerContacts().selectSelf());
    }

    public CorporateCustomerRequest<T> selectCustomerContactListWith(CustomerContactRequest customerContactList){
       enhanceRelation(CorporateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, customerContactList);
       return this;
    }
    public CorporateCustomerRequest<T> selectServiceQuoteList(){
       return selectServiceQuoteListWith(Q.serviceQuotes().selectSelf());
    }

    public CorporateCustomerRequest<T> selectServiceQuoteListWith(ServiceQuoteRequest serviceQuoteList){
       enhanceRelation(CorporateCustomer.SERVICE_QUOTE_LIST_PROPERTY, serviceQuoteList);
       return this;
    }
    public CorporateCustomerRequest<T> selectServiceContractList(){
       return selectServiceContractListWith(Q.serviceContracts().selectSelf());
    }

    public CorporateCustomerRequest<T> selectServiceContractListWith(ServiceContractRequest serviceContractList){
       enhanceRelation(CorporateCustomer.SERVICE_CONTRACT_LIST_PROPERTY, serviceContractList);
       return this;
    }

    public CorporateCustomerRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.ID_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public CorporateCustomerRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public CorporateCustomerRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public CorporateCustomerRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.NAME_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public CorporateCustomerRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public CorporateCustomerRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public CorporateCustomerRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public CorporateCustomerRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public CorporateCustomerRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public CorporateCustomerRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public CorporateCustomerRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public CorporateCustomerRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public CorporateCustomerRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public CorporateCustomerRequest<T> filterByRegistrationNumber(String... registrationNumber){
      if (registrationNumber == null || registrationNumber.length == 0) {
        throw new IllegalArgumentException("filterByRegistrationNumber parameter registrationNumber cannot be empty");
      }
      return appendSearchCriteria(createRegistrationNumberCriteria(Operator.EQUAL, (Object[])registrationNumber));
    }

    public CorporateCustomerRequest<T> withRegistrationNumber(Operator operator, Object... values){
       return appendSearchCriteria(createRegistrationNumberCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withRegistrationNumberIsUnknown(){
       return withRegistrationNumber(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberIsKnown(){
       return withRegistrationNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRegistrationNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberGreaterThan(String registrationNumber){
       return withRegistrationNumber(Operator.GREATER_THAN, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberGreaterThanOrEqualTo(String registrationNumber){
       return withRegistrationNumber(Operator.GREATER_THAN_OR_EQUAL, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberLessThan(String registrationNumber){
       return withRegistrationNumber(Operator.LESS_THAN, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberLessThanOrEqualTo(String registrationNumber){
       return withRegistrationNumber(Operator.LESS_THAN_OR_EQUAL, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberBetween(String startOfRegistrationNumber, String endOfRegistrationNumber){
       return withRegistrationNumber(Operator.BETWEEN, startOfRegistrationNumber, endOfRegistrationNumber);
    }
    public CorporateCustomerRequest<T> withRegistrationNumberStartingWith(String registrationNumber){
       return withRegistrationNumber(Operator.BEGIN_WITH, registrationNumber);
    }
    public CorporateCustomerRequest<T> withRegistrationNumberContaining(String registrationNumber){
       return withRegistrationNumber(Operator.CONTAIN, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberEndingWith(String registrationNumber){
       return withRegistrationNumber(Operator.END_WITH, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberIs(String registrationNumber){
       return withRegistrationNumber(Operator.EQUAL, registrationNumber);
    }

    public CorporateCustomerRequest<T> withRegistrationNumberSoundingLike(String registrationNumber){
       return withRegistrationNumber(Operator.SOUNDS_LIKE, registrationNumber);
    }



    public CorporateCustomerRequest<T> filterByIndustry(String... industry){
      if (industry == null || industry.length == 0) {
        throw new IllegalArgumentException("filterByIndustry parameter industry cannot be empty");
      }
      return appendSearchCriteria(createIndustryCriteria(Operator.EQUAL, (Object[])industry));
    }

    public CorporateCustomerRequest<T> withIndustry(Operator operator, Object... values){
       return appendSearchCriteria(createIndustryCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withIndustryIsUnknown(){
       return withIndustry(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withIndustryIsKnown(){
       return withIndustry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createIndustryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.INDUSTRY_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withIndustryGreaterThan(String industry){
       return withIndustry(Operator.GREATER_THAN, industry);
    }

    public CorporateCustomerRequest<T> withIndustryGreaterThanOrEqualTo(String industry){
       return withIndustry(Operator.GREATER_THAN_OR_EQUAL, industry);
    }

    public CorporateCustomerRequest<T> withIndustryLessThan(String industry){
       return withIndustry(Operator.LESS_THAN, industry);
    }

    public CorporateCustomerRequest<T> withIndustryLessThanOrEqualTo(String industry){
       return withIndustry(Operator.LESS_THAN_OR_EQUAL, industry);
    }

    public CorporateCustomerRequest<T> withIndustryBetween(String startOfIndustry, String endOfIndustry){
       return withIndustry(Operator.BETWEEN, startOfIndustry, endOfIndustry);
    }
    public CorporateCustomerRequest<T> withIndustryStartingWith(String industry){
       return withIndustry(Operator.BEGIN_WITH, industry);
    }
    public CorporateCustomerRequest<T> withIndustryContaining(String industry){
       return withIndustry(Operator.CONTAIN, industry);
    }

    public CorporateCustomerRequest<T> withIndustryEndingWith(String industry){
       return withIndustry(Operator.END_WITH, industry);
    }

    public CorporateCustomerRequest<T> withIndustryIs(String industry){
       return withIndustry(Operator.EQUAL, industry);
    }

    public CorporateCustomerRequest<T> withIndustrySoundingLike(String industry){
       return withIndustry(Operator.SOUNDS_LIKE, industry);
    }



    public CorporateCustomerRequest<T> filterByEmployeeCount(Integer... employeeCount){
      if (employeeCount == null || employeeCount.length == 0) {
        throw new IllegalArgumentException("filterByEmployeeCount parameter employeeCount cannot be empty");
      }
      return appendSearchCriteria(createEmployeeCountCriteria(Operator.EQUAL, (Object[])employeeCount));
    }

    public CorporateCustomerRequest<T> withEmployeeCount(Operator operator, Object... values){
       return appendSearchCriteria(createEmployeeCountCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withEmployeeCountIsUnknown(){
       return withEmployeeCount(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withEmployeeCountIsKnown(){
       return withEmployeeCount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmployeeCountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withEmployeeCountGreaterThan(Integer employeeCount){
       return withEmployeeCount(Operator.GREATER_THAN, employeeCount);
    }

    public CorporateCustomerRequest<T> withEmployeeCountGreaterThanOrEqualTo(Integer employeeCount){
       return withEmployeeCount(Operator.GREATER_THAN_OR_EQUAL, employeeCount);
    }

    public CorporateCustomerRequest<T> withEmployeeCountLessThan(Integer employeeCount){
       return withEmployeeCount(Operator.LESS_THAN, employeeCount);
    }

    public CorporateCustomerRequest<T> withEmployeeCountLessThanOrEqualTo(Integer employeeCount){
       return withEmployeeCount(Operator.LESS_THAN_OR_EQUAL, employeeCount);
    }

    public CorporateCustomerRequest<T> withEmployeeCountBetween(Integer startOfEmployeeCount, Integer endOfEmployeeCount){
       return withEmployeeCount(Operator.BETWEEN, startOfEmployeeCount, endOfEmployeeCount);
    }



    public CorporateCustomerRequest<T> filterByBillingAddress(String... billingAddress){
      if (billingAddress == null || billingAddress.length == 0) {
        throw new IllegalArgumentException("filterByBillingAddress parameter billingAddress cannot be empty");
      }
      return appendSearchCriteria(createBillingAddressCriteria(Operator.EQUAL, (Object[])billingAddress));
    }

    public CorporateCustomerRequest<T> withBillingAddress(Operator operator, Object... values){
       return appendSearchCriteria(createBillingAddressCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withBillingAddressIsUnknown(){
       return withBillingAddress(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withBillingAddressIsKnown(){
       return withBillingAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createBillingAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.BILLING_ADDRESS_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withBillingAddressGreaterThan(String billingAddress){
       return withBillingAddress(Operator.GREATER_THAN, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressGreaterThanOrEqualTo(String billingAddress){
       return withBillingAddress(Operator.GREATER_THAN_OR_EQUAL, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressLessThan(String billingAddress){
       return withBillingAddress(Operator.LESS_THAN, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressLessThanOrEqualTo(String billingAddress){
       return withBillingAddress(Operator.LESS_THAN_OR_EQUAL, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressBetween(String startOfBillingAddress, String endOfBillingAddress){
       return withBillingAddress(Operator.BETWEEN, startOfBillingAddress, endOfBillingAddress);
    }
    public CorporateCustomerRequest<T> withBillingAddressStartingWith(String billingAddress){
       return withBillingAddress(Operator.BEGIN_WITH, billingAddress);
    }
    public CorporateCustomerRequest<T> withBillingAddressContaining(String billingAddress){
       return withBillingAddress(Operator.CONTAIN, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressEndingWith(String billingAddress){
       return withBillingAddress(Operator.END_WITH, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressIs(String billingAddress){
       return withBillingAddress(Operator.EQUAL, billingAddress);
    }

    public CorporateCustomerRequest<T> withBillingAddressSoundingLike(String billingAddress){
       return withBillingAddress(Operator.SOUNDS_LIKE, billingAddress);
    }



    public CorporateCustomerRequest<T> filterByContactEmail(String... contactEmail){
      if (contactEmail == null || contactEmail.length == 0) {
        throw new IllegalArgumentException("filterByContactEmail parameter contactEmail cannot be empty");
      }
      return appendSearchCriteria(createContactEmailCriteria(Operator.EQUAL, (Object[])contactEmail));
    }

    public CorporateCustomerRequest<T> withContactEmail(Operator operator, Object... values){
       return appendSearchCriteria(createContactEmailCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withContactEmailIsUnknown(){
       return withContactEmail(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withContactEmailIsKnown(){
       return withContactEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContactEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.CONTACT_EMAIL_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withContactEmailGreaterThan(String contactEmail){
       return withContactEmail(Operator.GREATER_THAN, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailGreaterThanOrEqualTo(String contactEmail){
       return withContactEmail(Operator.GREATER_THAN_OR_EQUAL, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailLessThan(String contactEmail){
       return withContactEmail(Operator.LESS_THAN, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailLessThanOrEqualTo(String contactEmail){
       return withContactEmail(Operator.LESS_THAN_OR_EQUAL, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailBetween(String startOfContactEmail, String endOfContactEmail){
       return withContactEmail(Operator.BETWEEN, startOfContactEmail, endOfContactEmail);
    }
    public CorporateCustomerRequest<T> withContactEmailStartingWith(String contactEmail){
       return withContactEmail(Operator.BEGIN_WITH, contactEmail);
    }
    public CorporateCustomerRequest<T> withContactEmailContaining(String contactEmail){
       return withContactEmail(Operator.CONTAIN, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailEndingWith(String contactEmail){
       return withContactEmail(Operator.END_WITH, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailIs(String contactEmail){
       return withContactEmail(Operator.EQUAL, contactEmail);
    }

    public CorporateCustomerRequest<T> withContactEmailSoundingLike(String contactEmail){
       return withContactEmail(Operator.SOUNDS_LIKE, contactEmail);
    }



    public CorporateCustomerRequest<T> filterByContactPhone(String... contactPhone){
      if (contactPhone == null || contactPhone.length == 0) {
        throw new IllegalArgumentException("filterByContactPhone parameter contactPhone cannot be empty");
      }
      return appendSearchCriteria(createContactPhoneCriteria(Operator.EQUAL, (Object[])contactPhone));
    }

    public CorporateCustomerRequest<T> withContactPhone(Operator operator, Object... values){
       return appendSearchCriteria(createContactPhoneCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withContactPhoneIsUnknown(){
       return withContactPhone(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withContactPhoneIsKnown(){
       return withContactPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContactPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.CONTACT_PHONE_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withContactPhoneGreaterThan(String contactPhone){
       return withContactPhone(Operator.GREATER_THAN, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneGreaterThanOrEqualTo(String contactPhone){
       return withContactPhone(Operator.GREATER_THAN_OR_EQUAL, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneLessThan(String contactPhone){
       return withContactPhone(Operator.LESS_THAN, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneLessThanOrEqualTo(String contactPhone){
       return withContactPhone(Operator.LESS_THAN_OR_EQUAL, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneBetween(String startOfContactPhone, String endOfContactPhone){
       return withContactPhone(Operator.BETWEEN, startOfContactPhone, endOfContactPhone);
    }
    public CorporateCustomerRequest<T> withContactPhoneStartingWith(String contactPhone){
       return withContactPhone(Operator.BEGIN_WITH, contactPhone);
    }
    public CorporateCustomerRequest<T> withContactPhoneContaining(String contactPhone){
       return withContactPhone(Operator.CONTAIN, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneEndingWith(String contactPhone){
       return withContactPhone(Operator.END_WITH, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneIs(String contactPhone){
       return withContactPhone(Operator.EQUAL, contactPhone);
    }

    public CorporateCustomerRequest<T> withContactPhoneSoundingLike(String contactPhone){
       return withContactPhone(Operator.SOUNDS_LIKE, contactPhone);
    }



    public CorporateCustomerRequest<T> filterByCustomerType(String... customerType){
      if (customerType == null || customerType.length == 0) {
        throw new IllegalArgumentException("filterByCustomerType parameter customerType cannot be empty");
      }
      return appendSearchCriteria(createCustomerTypeCriteria(Operator.EQUAL, (Object[])customerType));
    }

    public CorporateCustomerRequest<T> withCustomerType(Operator operator, Object... values){
       return appendSearchCriteria(createCustomerTypeCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withCustomerTypeIsUnknown(){
       return withCustomerType(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withCustomerTypeIsKnown(){
       return withCustomerType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCustomerTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.CUSTOMER_TYPE_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withCustomerTypeGreaterThan(String customerType){
       return withCustomerType(Operator.GREATER_THAN, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeGreaterThanOrEqualTo(String customerType){
       return withCustomerType(Operator.GREATER_THAN_OR_EQUAL, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeLessThan(String customerType){
       return withCustomerType(Operator.LESS_THAN, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeLessThanOrEqualTo(String customerType){
       return withCustomerType(Operator.LESS_THAN_OR_EQUAL, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeBetween(String startOfCustomerType, String endOfCustomerType){
       return withCustomerType(Operator.BETWEEN, startOfCustomerType, endOfCustomerType);
    }
    public CorporateCustomerRequest<T> withCustomerTypeStartingWith(String customerType){
       return withCustomerType(Operator.BEGIN_WITH, customerType);
    }
    public CorporateCustomerRequest<T> withCustomerTypeContaining(String customerType){
       return withCustomerType(Operator.CONTAIN, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeEndingWith(String customerType){
       return withCustomerType(Operator.END_WITH, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeIs(String customerType){
       return withCustomerType(Operator.EQUAL, customerType);
    }

    public CorporateCustomerRequest<T> withCustomerTypeSoundingLike(String customerType){
       return withCustomerType(Operator.SOUNDS_LIKE, customerType);
    }



    public CorporateCustomerRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public CorporateCustomerRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.CREATED_AT_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public CorporateCustomerRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public CorporateCustomerRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public CorporateCustomerRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public CorporateCustomerRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.UPDATED_AT_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public CorporateCustomerRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public CorporateCustomerRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public CorporateCustomerRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public CorporateCustomerRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.VERSION_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public CorporateCustomerRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public CorporateCustomerRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public CorporateCustomerRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public CorporateCustomerRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public CorporateCustomerRequest<T> withCustomerContactListMatching(CustomerContactRequest customerContactRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, customerContactRequest, CustomerContact.CORPORATE_CUSTOMER_PROPERTY));
    }

    public CorporateCustomerRequest<T> withoutCustomerContactListMatching(CustomerContactRequest customerContactRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, customerContactRequest, CustomerContact.CORPORATE_CUSTOMER_PROPERTY)));
    }

    public CorporateCustomerRequest<T> haveCustomerContacts(){
        return withCustomerContactListMatching(Q.customerContacts().unlimited());
    }

    public CorporateCustomerRequest<T> haveNoCustomerContacts(){
        return withoutCustomerContactListMatching(Q.customerContacts().unlimited());
    }
    public CorporateCustomerRequest<T> withServiceQuoteListMatching(ServiceQuoteRequest serviceQuoteRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, serviceQuoteRequest, ServiceQuote.CORPORATE_CUSTOMER_PROPERTY));
    }

    public CorporateCustomerRequest<T> withoutServiceQuoteListMatching(ServiceQuoteRequest serviceQuoteRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, serviceQuoteRequest, ServiceQuote.CORPORATE_CUSTOMER_PROPERTY)));
    }

    public CorporateCustomerRequest<T> haveServiceQuotes(){
        return withServiceQuoteListMatching(Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> haveNoServiceQuotes(){
        return withoutServiceQuoteListMatching(Q.serviceQuotes().unlimited());
    }
    public CorporateCustomerRequest<T> withServiceContractListMatching(ServiceContractRequest serviceContractRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, serviceContractRequest, ServiceContract.CORPORATE_CUSTOMER_PROPERTY));
    }

    public CorporateCustomerRequest<T> withoutServiceContractListMatching(ServiceContractRequest serviceContractRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, serviceContractRequest, ServiceContract.CORPORATE_CUSTOMER_PROPERTY)));
    }

    public CorporateCustomerRequest<T> haveServiceContracts(){
        return withServiceContractListMatching(Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> haveNoServiceContracts(){
        return withoutServiceContractListMatching(Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> count(){
        super.count();
        return this;
    }
    public CorporateCustomerRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public CorporateCustomerRequest minEmployeeCount(){
        return minEmployeeCountAs(prefix("minOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest minEmployeeCountAs(String retName){
        super.min(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest maxEmployeeCount(){
        return maxEmployeeCountAs(prefix("maxOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest maxEmployeeCountAs(String retName){
        super.max(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest sumEmployeeCount(){
        return sumEmployeeCountAs(prefix("sumOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest sumEmployeeCountAs(String retName){
        super.sum(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest avgEmployeeCount(){
        return avgEmployeeCountAs(prefix("avgOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest avgEmployeeCountAs(String retName){
        super.avg(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest standardDeviationEmployeeCount(){
        return standardDeviationEmployeeCountAs(prefix("standardDeviationOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest standardDeviationEmployeeCountAs(String retName){
        super.standardDeviation(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest squareRootOfPopulationStandardDeviationEmployeeCount(){
        return squareRootOfPopulationStandardDeviationEmployeeCountAs(prefix("squareRootOfPopulationStandardDeviationOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest squareRootOfPopulationStandardDeviationEmployeeCountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest sampleVarianceEmployeeCount(){
        return sampleVarianceEmployeeCountAs(prefix("sampleVarianceOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest sampleVarianceEmployeeCountAs(String retName){
        super.sampleVariance(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest samplePopulationVarianceEmployeeCount(){
        return samplePopulationVarianceEmployeeCountAs(prefix("samplePopulationVarianceOf",CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
    }

    public CorporateCustomerRequest samplePopulationVarianceEmployeeCountAs(String retName){
        super.samplePopulationVariance(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
        return this;
    }
    public CorporateCustomerRequest<T> groupByCustomerContactsWithDetails(CustomerContactRequest subRequest){
       aggregate(CorporateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, subRequest);
       return this;
    }
    public CorporateCustomerRequest<T> groupByServiceQuotesWithDetails(ServiceQuoteRequest subRequest){
       aggregate(CorporateCustomer.SERVICE_QUOTE_LIST_PROPERTY, subRequest);
       return this;
    }
    public CorporateCustomerRequest<T> groupByServiceContractsWithDetails(ServiceContractRequest subRequest){
       aggregate(CorporateCustomer.SERVICE_CONTRACT_LIST_PROPERTY, subRequest);
       return this;
    }

    public CorporateCustomerRequest<T> groupById(){
       groupBy(CorporateCustomer.ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByIdAs(String retName){
       groupBy(retName, CorporateCustomer.ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.ID_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByName(){
       groupBy(CorporateCustomer.NAME_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByNameAs(String retName){
       groupBy(retName, CorporateCustomer.NAME_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.NAME_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByRegistrationNumber(){
       groupBy(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByRegistrationNumberAs(String retName){
       groupBy(retName, CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByRegistrationNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.REGISTRATION_NUMBER_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByIndustry(){
       groupBy(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByIndustryAs(String retName){
       groupBy(retName, CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByIndustryWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.INDUSTRY_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByEmployeeCount(){
       groupBy(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByEmployeeCountAs(String retName){
       groupBy(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByEmployeeCountWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByBillingAddress(){
       groupBy(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByBillingAddressAs(String retName){
       groupBy(retName, CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByBillingAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.BILLING_ADDRESS_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactEmail(){
       groupBy(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactEmailAs(String retName){
       groupBy(retName, CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.CONTACT_EMAIL_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactPhone(){
       groupBy(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactPhoneAs(String retName){
       groupBy(retName, CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.CONTACT_PHONE_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCustomerType(){
       groupBy(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCustomerTypeAs(String retName){
       groupBy(retName, CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCustomerTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.CUSTOMER_TYPE_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCreatedAt(){
       groupBy(CorporateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, CorporateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.CREATED_AT_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByUpdatedAt(){
       groupBy(CorporateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, CorporateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByVersion(){
       groupBy(CorporateCustomer.VERSION_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByVersionAs(String retName){
       groupBy(retName, CorporateCustomer.VERSION_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.VERSION_PROPERTY, function);
       return this;
    }



    public CorporateCustomerRequest<T> orderByIdAscending(){
       addOrderByAscending(CorporateCustomer.ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByIdDescending(){
       addOrderByDescending(CorporateCustomer.ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByNameAscending(){
       addOrderByAscending(CorporateCustomer.NAME_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByNameDescending(){
       addOrderByDescending(CorporateCustomer.NAME_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.NAME_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.NAME_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByRegistrationNumberAscending(){
       addOrderByAscending(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByRegistrationNumberDescending(){
       addOrderByDescending(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByRegistrationNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByRegistrationNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByIndustryAscending(){
       addOrderByAscending(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByIndustryDescending(){
       addOrderByDescending(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByIndustryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByIndustryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.INDUSTRY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByEmployeeCountAscending(){
       addOrderByAscending(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByEmployeeCountDescending(){
       addOrderByDescending(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByBillingAddressAscending(){
       addOrderByAscending(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByBillingAddressDescending(){
       addOrderByDescending(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByBillingAddressAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByBillingAddressDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.BILLING_ADDRESS_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByContactEmailAscending(){
       addOrderByAscending(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByContactEmailDescending(){
       addOrderByDescending(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByContactEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByContactEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.CONTACT_EMAIL_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByContactPhoneAscending(){
       addOrderByAscending(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByContactPhoneDescending(){
       addOrderByDescending(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByContactPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByContactPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.CONTACT_PHONE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCustomerTypeAscending(){
       addOrderByAscending(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCustomerTypeDescending(){
       addOrderByDescending(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCustomerTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCustomerTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(CorporateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(CorporateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(CorporateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(CorporateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByVersionAscending(){
       addOrderByAscending(CorporateCustomer.VERSION_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByVersionDescending(){
       addOrderByDescending(CorporateCustomer.VERSION_PROPERTY);
       return this;
    }


    public CorporateCustomerRequest<T> statsFromCustomerContactsAs(String name, CustomerContactRequest subRequest){
       return statsFromCustomerContactsAs(name, subRequest, false);
    }

    public CorporateCustomerRequest<T> statsFromCustomerContactsAs(String name, CustomerContactRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(CustomerContact.CORPORATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public CorporateCustomerRequest<T> statsFromCustomerContacts(CustomerContactRequest subRequest){
       return statsFromCustomerContactsAs(REFINEMENTS, subRequest);
    }
    public CorporateCustomerRequest<T> statsFromServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
       return statsFromServiceQuotesAs(name, subRequest, false);
    }

    public CorporateCustomerRequest<T> statsFromServiceQuotesAs(String name, ServiceQuoteRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public CorporateCustomerRequest<T> statsFromServiceQuotes(ServiceQuoteRequest subRequest){
       return statsFromServiceQuotesAs(REFINEMENTS, subRequest);
    }
    public CorporateCustomerRequest<T> statsFromServiceContractsAs(String name, ServiceContractRequest subRequest){
       return statsFromServiceContractsAs(name, subRequest, false);
    }

    public CorporateCustomerRequest<T> statsFromServiceContractsAs(String name, ServiceContractRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(ServiceContract.CORPORATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public CorporateCustomerRequest<T> statsFromServiceContracts(ServiceContractRequest subRequest){
       return statsFromServiceContractsAs(REFINEMENTS, subRequest);
    }
    public CorporateCustomerRequest<T> countCustomerContacts(){
        return countCustomerContactsAs("Count");
    }

    public CorporateCustomerRequest<T> countCustomerContactsAs(String name){
        return countCustomerContactsWith(name, Q.customerContacts().unlimited());
    }

    public CorporateCustomerRequest<T> countCustomerContactsWith(String name, CustomerContactRequest subRequest){
        return statsFromCustomerContactsAs(name, subRequest.count(), true);
    }
    public CorporateCustomerRequest<T> countServiceQuotes(){
        return countServiceQuotesAs("Count");
    }

    public CorporateCustomerRequest<T> countServiceQuotesAs(String name){
        return countServiceQuotesWith(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> countServiceQuotesWith(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.count(), true);
    }
    public CorporateCustomerRequest<T> countServiceContracts(){
        return countServiceContractsAs("Count");
    }

    public CorporateCustomerRequest<T> countServiceContractsAs(String name){
        return countServiceContractsWith(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> countServiceContractsWith(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.count(), true);
    }
    public CorporateCustomerRequest<T> minEstimatedCostOfServiceQuotes(){
        return minEstimatedCostOfServiceQuotesAs("minEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> minEstimatedCostOfServiceQuotesAs(String name){
        return minEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> minEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.minEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> maxEstimatedCostOfServiceQuotes(){
        return maxEstimatedCostOfServiceQuotesAs("maxEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> maxEstimatedCostOfServiceQuotesAs(String name){
        return maxEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> maxEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.maxEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> sumEstimatedCostOfServiceQuotes(){
        return sumEstimatedCostOfServiceQuotesAs("sumEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> sumEstimatedCostOfServiceQuotesAs(String name){
        return sumEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> sumEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.sumEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> avgEstimatedCostOfServiceQuotes(){
        return avgEstimatedCostOfServiceQuotesAs("avgEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> avgEstimatedCostOfServiceQuotesAs(String name){
        return avgEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> avgEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.avgEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> standardDeviationEstimatedCostOfServiceQuotes(){
        return standardDeviationEstimatedCostOfServiceQuotesAs("stdDevEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> standardDeviationEstimatedCostOfServiceQuotesAs(String name){
        return standardDeviationEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> standardDeviationEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.standardDeviationEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotes(){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs("stdDevPopEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs(String name){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.squareRootOfPopulationStandardDeviationEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> sampleVarianceEstimatedCostOfServiceQuotes(){
        return sampleVarianceEstimatedCostOfServiceQuotesAs("varSampEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> sampleVarianceEstimatedCostOfServiceQuotesAs(String name){
        return sampleVarianceEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> sampleVarianceEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.sampleVarianceEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfServiceQuotes(){
        return samplePopulationVarianceEstimatedCostOfServiceQuotesAs("varPopEstimatedCostOfServiceQuotes");
    }

    public CorporateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfServiceQuotesAs(String name){
        return samplePopulationVarianceEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public CorporateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.samplePopulationVarianceEstimatedCost(), true);
    }
    public CorporateCustomerRequest<T> minTotalValueOfServiceContracts(){
        return minTotalValueOfServiceContractsAs("minTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> minTotalValueOfServiceContractsAs(String name){
        return minTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> minTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.minTotalValue(), true);
    }
    public CorporateCustomerRequest<T> maxTotalValueOfServiceContracts(){
        return maxTotalValueOfServiceContractsAs("maxTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> maxTotalValueOfServiceContractsAs(String name){
        return maxTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> maxTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.maxTotalValue(), true);
    }
    public CorporateCustomerRequest<T> sumTotalValueOfServiceContracts(){
        return sumTotalValueOfServiceContractsAs("sumTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> sumTotalValueOfServiceContractsAs(String name){
        return sumTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> sumTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.sumTotalValue(), true);
    }
    public CorporateCustomerRequest<T> avgTotalValueOfServiceContracts(){
        return avgTotalValueOfServiceContractsAs("avgTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> avgTotalValueOfServiceContractsAs(String name){
        return avgTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> avgTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.avgTotalValue(), true);
    }
    public CorporateCustomerRequest<T> standardDeviationTotalValueOfServiceContracts(){
        return standardDeviationTotalValueOfServiceContractsAs("stdDevTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> standardDeviationTotalValueOfServiceContractsAs(String name){
        return standardDeviationTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> standardDeviationTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.standardDeviationTotalValue(), true);
    }
    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalValueOfServiceContracts(){
        return squareRootOfPopulationStandardDeviationTotalValueOfServiceContractsAs("stdDevPopTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalValueOfServiceContractsAs(String name){
        return squareRootOfPopulationStandardDeviationTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalValue(), true);
    }
    public CorporateCustomerRequest<T> sampleVarianceTotalValueOfServiceContracts(){
        return sampleVarianceTotalValueOfServiceContractsAs("varSampTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> sampleVarianceTotalValueOfServiceContractsAs(String name){
        return sampleVarianceTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> sampleVarianceTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.sampleVarianceTotalValue(), true);
    }
    public CorporateCustomerRequest<T> samplePopulationVarianceTotalValueOfServiceContracts(){
        return samplePopulationVarianceTotalValueOfServiceContractsAs("varPopTotalValueOfServiceContracts");
    }

    public CorporateCustomerRequest<T> samplePopulationVarianceTotalValueOfServiceContractsAs(String name){
        return samplePopulationVarianceTotalValueOfServiceContractsAs(name, Q.serviceContracts().unlimited());
    }

    public CorporateCustomerRequest<T> samplePopulationVarianceTotalValueOfServiceContractsAs(String name, ServiceContractRequest subRequest){
        return statsFromServiceContractsAs(name, subRequest.samplePopulationVarianceTotalValue(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public CorporateCustomerRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public CorporateCustomerRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public CorporateCustomerRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public CorporateCustomerRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public CorporateCustomerRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}