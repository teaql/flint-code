package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyRequest;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewRequest;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;

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
        return selectId().selectName().selectContactPerson().selectPhone().selectEmail().selectAddress().selectCity().selectCountry().selectTaxId().selectCustomerType().selectVersion();
    }

    public CorporateCustomerRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CorporateCustomerRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectContactPerson().selectPhone().selectEmail().selectAddress().selectCity().selectCountry().selectTaxId().selectCustomerType().selectVersion();
    }

    public CorporateCustomerRequest<T> selectChildren(){
        super.selectAny();
        selectCustomerContactList().selectServiceQuoteList().selectFeedbackReviewList().selectCustomerLoyaltyList();
        return selectId().selectName().selectContactPerson().selectPhone().selectEmail().selectAddress().selectCity().selectCountry().selectTaxId().selectCustomerType().selectVersion();
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
    public CorporateCustomerRequest<T> selectContactPerson(){
       selectProperty(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }

    /**
     * fill the contactPerson with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  contactPerson) to fetch contactPerson property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectContactPerson(){
       unselectProperty(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectPhone(){
       selectProperty(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the phone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  phone) to fetch phone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectPhone(){
       unselectProperty(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectEmail(){
       selectProperty(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the email with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  email) to fetch email property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectEmail(){
       unselectProperty(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectAddress(){
       selectProperty(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    /**
     * fill the address with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  address) to fetch address property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectAddress(){
       unselectProperty(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectCity(){
       selectProperty(CorporateCustomer.CITY_PROPERTY);
       return this;
    }

    /**
     * fill the city with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  city) to fetch city property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectCity(){
       unselectProperty(CorporateCustomer.CITY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectCountry(){
       selectProperty(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    /**
     * fill the country with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  country) to fetch country property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectCountry(){
       unselectProperty(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> selectTaxId(){
       selectProperty(CorporateCustomer.TAX_ID_PROPERTY);
       return this;
    }

    /**
     * fill the taxId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  taxId) to fetch taxId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CorporateCustomerRequest<T> unselectTaxId(){
       unselectProperty(CorporateCustomer.TAX_ID_PROPERTY);
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
    public CorporateCustomerRequest<T> selectFeedbackReviewList(){
       return selectFeedbackReviewListWith(Q.feedbackReviews().selectSelf());
    }

    public CorporateCustomerRequest<T> selectFeedbackReviewListWith(FeedbackReviewRequest feedbackReviewList){
       enhanceRelation(CorporateCustomer.FEEDBACK_REVIEW_LIST_PROPERTY, feedbackReviewList);
       return this;
    }
    public CorporateCustomerRequest<T> selectCustomerLoyaltyList(){
       return selectCustomerLoyaltyListWith(Q.customerLoyalties().selectSelf());
    }

    public CorporateCustomerRequest<T> selectCustomerLoyaltyListWith(CustomerLoyaltyRequest customerLoyaltyList){
       enhanceRelation(CorporateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, customerLoyaltyList);
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



    public CorporateCustomerRequest<T> filterByContactPerson(String... contactPerson){
      if (contactPerson == null || contactPerson.length == 0) {
        throw new IllegalArgumentException("filterByContactPerson parameter contactPerson cannot be empty");
      }
      return appendSearchCriteria(createContactPersonCriteria(Operator.EQUAL, (Object[])contactPerson));
    }

    public CorporateCustomerRequest<T> withContactPerson(Operator operator, Object... values){
       return appendSearchCriteria(createContactPersonCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withContactPersonIsUnknown(){
       return withContactPerson(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withContactPersonIsKnown(){
       return withContactPerson(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContactPersonCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.CONTACT_PERSON_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withContactPersonGreaterThan(String contactPerson){
       return withContactPerson(Operator.GREATER_THAN, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonGreaterThanOrEqualTo(String contactPerson){
       return withContactPerson(Operator.GREATER_THAN_OR_EQUAL, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonLessThan(String contactPerson){
       return withContactPerson(Operator.LESS_THAN, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonLessThanOrEqualTo(String contactPerson){
       return withContactPerson(Operator.LESS_THAN_OR_EQUAL, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonBetween(String startOfContactPerson, String endOfContactPerson){
       return withContactPerson(Operator.BETWEEN, startOfContactPerson, endOfContactPerson);
    }
    public CorporateCustomerRequest<T> withContactPersonStartingWith(String contactPerson){
       return withContactPerson(Operator.BEGIN_WITH, contactPerson);
    }
    public CorporateCustomerRequest<T> withContactPersonContaining(String contactPerson){
       return withContactPerson(Operator.CONTAIN, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonEndingWith(String contactPerson){
       return withContactPerson(Operator.END_WITH, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonIs(String contactPerson){
       return withContactPerson(Operator.EQUAL, contactPerson);
    }

    public CorporateCustomerRequest<T> withContactPersonSoundingLike(String contactPerson){
       return withContactPerson(Operator.SOUNDS_LIKE, contactPerson);
    }



    public CorporateCustomerRequest<T> filterByPhone(String... phone){
      if (phone == null || phone.length == 0) {
        throw new IllegalArgumentException("filterByPhone parameter phone cannot be empty");
      }
      return appendSearchCriteria(createPhoneCriteria(Operator.EQUAL, (Object[])phone));
    }

    public CorporateCustomerRequest<T> withPhone(Operator operator, Object... values){
       return appendSearchCriteria(createPhoneCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withPhoneIsUnknown(){
       return withPhone(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withPhoneIsKnown(){
       return withPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.PHONE_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withPhoneGreaterThan(String phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public CorporateCustomerRequest<T> withPhoneGreaterThanOrEqualTo(String phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public CorporateCustomerRequest<T> withPhoneLessThan(String phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public CorporateCustomerRequest<T> withPhoneLessThanOrEqualTo(String phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public CorporateCustomerRequest<T> withPhoneBetween(String startOfPhone, String endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }
    public CorporateCustomerRequest<T> withPhoneStartingWith(String phone){
       return withPhone(Operator.BEGIN_WITH, phone);
    }
    public CorporateCustomerRequest<T> withPhoneContaining(String phone){
       return withPhone(Operator.CONTAIN, phone);
    }

    public CorporateCustomerRequest<T> withPhoneEndingWith(String phone){
       return withPhone(Operator.END_WITH, phone);
    }

    public CorporateCustomerRequest<T> withPhoneIs(String phone){
       return withPhone(Operator.EQUAL, phone);
    }

    public CorporateCustomerRequest<T> withPhoneSoundingLike(String phone){
       return withPhone(Operator.SOUNDS_LIKE, phone);
    }



    public CorporateCustomerRequest<T> filterByEmail(String... email){
      if (email == null || email.length == 0) {
        throw new IllegalArgumentException("filterByEmail parameter email cannot be empty");
      }
      return appendSearchCriteria(createEmailCriteria(Operator.EQUAL, (Object[])email));
    }

    public CorporateCustomerRequest<T> withEmail(Operator operator, Object... values){
       return appendSearchCriteria(createEmailCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withEmailIsUnknown(){
       return withEmail(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withEmailIsKnown(){
       return withEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.EMAIL_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withEmailGreaterThan(String email){
       return withEmail(Operator.GREATER_THAN, email);
    }

    public CorporateCustomerRequest<T> withEmailGreaterThanOrEqualTo(String email){
       return withEmail(Operator.GREATER_THAN_OR_EQUAL, email);
    }

    public CorporateCustomerRequest<T> withEmailLessThan(String email){
       return withEmail(Operator.LESS_THAN, email);
    }

    public CorporateCustomerRequest<T> withEmailLessThanOrEqualTo(String email){
       return withEmail(Operator.LESS_THAN_OR_EQUAL, email);
    }

    public CorporateCustomerRequest<T> withEmailBetween(String startOfEmail, String endOfEmail){
       return withEmail(Operator.BETWEEN, startOfEmail, endOfEmail);
    }
    public CorporateCustomerRequest<T> withEmailStartingWith(String email){
       return withEmail(Operator.BEGIN_WITH, email);
    }
    public CorporateCustomerRequest<T> withEmailContaining(String email){
       return withEmail(Operator.CONTAIN, email);
    }

    public CorporateCustomerRequest<T> withEmailEndingWith(String email){
       return withEmail(Operator.END_WITH, email);
    }

    public CorporateCustomerRequest<T> withEmailIs(String email){
       return withEmail(Operator.EQUAL, email);
    }

    public CorporateCustomerRequest<T> withEmailSoundingLike(String email){
       return withEmail(Operator.SOUNDS_LIKE, email);
    }



    public CorporateCustomerRequest<T> filterByAddress(String... address){
      if (address == null || address.length == 0) {
        throw new IllegalArgumentException("filterByAddress parameter address cannot be empty");
      }
      return appendSearchCriteria(createAddressCriteria(Operator.EQUAL, (Object[])address));
    }

    public CorporateCustomerRequest<T> withAddress(Operator operator, Object... values){
       return appendSearchCriteria(createAddressCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withAddressIsUnknown(){
       return withAddress(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withAddressIsKnown(){
       return withAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.ADDRESS_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withAddressGreaterThan(String address){
       return withAddress(Operator.GREATER_THAN, address);
    }

    public CorporateCustomerRequest<T> withAddressGreaterThanOrEqualTo(String address){
       return withAddress(Operator.GREATER_THAN_OR_EQUAL, address);
    }

    public CorporateCustomerRequest<T> withAddressLessThan(String address){
       return withAddress(Operator.LESS_THAN, address);
    }

    public CorporateCustomerRequest<T> withAddressLessThanOrEqualTo(String address){
       return withAddress(Operator.LESS_THAN_OR_EQUAL, address);
    }

    public CorporateCustomerRequest<T> withAddressBetween(String startOfAddress, String endOfAddress){
       return withAddress(Operator.BETWEEN, startOfAddress, endOfAddress);
    }
    public CorporateCustomerRequest<T> withAddressStartingWith(String address){
       return withAddress(Operator.BEGIN_WITH, address);
    }
    public CorporateCustomerRequest<T> withAddressContaining(String address){
       return withAddress(Operator.CONTAIN, address);
    }

    public CorporateCustomerRequest<T> withAddressEndingWith(String address){
       return withAddress(Operator.END_WITH, address);
    }

    public CorporateCustomerRequest<T> withAddressIs(String address){
       return withAddress(Operator.EQUAL, address);
    }

    public CorporateCustomerRequest<T> withAddressSoundingLike(String address){
       return withAddress(Operator.SOUNDS_LIKE, address);
    }



    public CorporateCustomerRequest<T> filterByCity(String... city){
      if (city == null || city.length == 0) {
        throw new IllegalArgumentException("filterByCity parameter city cannot be empty");
      }
      return appendSearchCriteria(createCityCriteria(Operator.EQUAL, (Object[])city));
    }

    public CorporateCustomerRequest<T> withCity(Operator operator, Object... values){
       return appendSearchCriteria(createCityCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withCityIsUnknown(){
       return withCity(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withCityIsKnown(){
       return withCity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.CITY_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withCityGreaterThan(String city){
       return withCity(Operator.GREATER_THAN, city);
    }

    public CorporateCustomerRequest<T> withCityGreaterThanOrEqualTo(String city){
       return withCity(Operator.GREATER_THAN_OR_EQUAL, city);
    }

    public CorporateCustomerRequest<T> withCityLessThan(String city){
       return withCity(Operator.LESS_THAN, city);
    }

    public CorporateCustomerRequest<T> withCityLessThanOrEqualTo(String city){
       return withCity(Operator.LESS_THAN_OR_EQUAL, city);
    }

    public CorporateCustomerRequest<T> withCityBetween(String startOfCity, String endOfCity){
       return withCity(Operator.BETWEEN, startOfCity, endOfCity);
    }
    public CorporateCustomerRequest<T> withCityStartingWith(String city){
       return withCity(Operator.BEGIN_WITH, city);
    }
    public CorporateCustomerRequest<T> withCityContaining(String city){
       return withCity(Operator.CONTAIN, city);
    }

    public CorporateCustomerRequest<T> withCityEndingWith(String city){
       return withCity(Operator.END_WITH, city);
    }

    public CorporateCustomerRequest<T> withCityIs(String city){
       return withCity(Operator.EQUAL, city);
    }

    public CorporateCustomerRequest<T> withCitySoundingLike(String city){
       return withCity(Operator.SOUNDS_LIKE, city);
    }



    public CorporateCustomerRequest<T> filterByCountry(String... country){
      if (country == null || country.length == 0) {
        throw new IllegalArgumentException("filterByCountry parameter country cannot be empty");
      }
      return appendSearchCriteria(createCountryCriteria(Operator.EQUAL, (Object[])country));
    }

    public CorporateCustomerRequest<T> withCountry(Operator operator, Object... values){
       return appendSearchCriteria(createCountryCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withCountryIsUnknown(){
       return withCountry(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withCountryIsKnown(){
       return withCountry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCountryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.COUNTRY_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withCountryGreaterThan(String country){
       return withCountry(Operator.GREATER_THAN, country);
    }

    public CorporateCustomerRequest<T> withCountryGreaterThanOrEqualTo(String country){
       return withCountry(Operator.GREATER_THAN_OR_EQUAL, country);
    }

    public CorporateCustomerRequest<T> withCountryLessThan(String country){
       return withCountry(Operator.LESS_THAN, country);
    }

    public CorporateCustomerRequest<T> withCountryLessThanOrEqualTo(String country){
       return withCountry(Operator.LESS_THAN_OR_EQUAL, country);
    }

    public CorporateCustomerRequest<T> withCountryBetween(String startOfCountry, String endOfCountry){
       return withCountry(Operator.BETWEEN, startOfCountry, endOfCountry);
    }
    public CorporateCustomerRequest<T> withCountryStartingWith(String country){
       return withCountry(Operator.BEGIN_WITH, country);
    }
    public CorporateCustomerRequest<T> withCountryContaining(String country){
       return withCountry(Operator.CONTAIN, country);
    }

    public CorporateCustomerRequest<T> withCountryEndingWith(String country){
       return withCountry(Operator.END_WITH, country);
    }

    public CorporateCustomerRequest<T> withCountryIs(String country){
       return withCountry(Operator.EQUAL, country);
    }

    public CorporateCustomerRequest<T> withCountrySoundingLike(String country){
       return withCountry(Operator.SOUNDS_LIKE, country);
    }



    public CorporateCustomerRequest<T> filterByTaxId(String... taxId){
      if (taxId == null || taxId.length == 0) {
        throw new IllegalArgumentException("filterByTaxId parameter taxId cannot be empty");
      }
      return appendSearchCriteria(createTaxIdCriteria(Operator.EQUAL, (Object[])taxId));
    }

    public CorporateCustomerRequest<T> withTaxId(Operator operator, Object... values){
       return appendSearchCriteria(createTaxIdCriteria(operator, values));
    }

    public CorporateCustomerRequest<T> withTaxIdIsUnknown(){
       return withTaxId(Operator.IS_NULL);
    }

    public CorporateCustomerRequest<T> withTaxIdIsKnown(){
       return withTaxId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTaxIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CorporateCustomer.TAX_ID_PROPERTY, operator, values);
    }

    public CorporateCustomerRequest<T> withTaxIdGreaterThan(String taxId){
       return withTaxId(Operator.GREATER_THAN, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdGreaterThanOrEqualTo(String taxId){
       return withTaxId(Operator.GREATER_THAN_OR_EQUAL, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdLessThan(String taxId){
       return withTaxId(Operator.LESS_THAN, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdLessThanOrEqualTo(String taxId){
       return withTaxId(Operator.LESS_THAN_OR_EQUAL, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdBetween(String startOfTaxId, String endOfTaxId){
       return withTaxId(Operator.BETWEEN, startOfTaxId, endOfTaxId);
    }
    public CorporateCustomerRequest<T> withTaxIdStartingWith(String taxId){
       return withTaxId(Operator.BEGIN_WITH, taxId);
    }
    public CorporateCustomerRequest<T> withTaxIdContaining(String taxId){
       return withTaxId(Operator.CONTAIN, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdEndingWith(String taxId){
       return withTaxId(Operator.END_WITH, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdIs(String taxId){
       return withTaxId(Operator.EQUAL, taxId);
    }

    public CorporateCustomerRequest<T> withTaxIdSoundingLike(String taxId){
       return withTaxId(Operator.SOUNDS_LIKE, taxId);
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
    public CorporateCustomerRequest<T> withFeedbackReviewListMatching(FeedbackReviewRequest feedbackReviewRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, feedbackReviewRequest, FeedbackReview.CORPORATE_CUSTOMER_PROPERTY));
    }

    public CorporateCustomerRequest<T> withoutFeedbackReviewListMatching(FeedbackReviewRequest feedbackReviewRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, feedbackReviewRequest, FeedbackReview.CORPORATE_CUSTOMER_PROPERTY)));
    }

    public CorporateCustomerRequest<T> haveFeedbackReviews(){
        return withFeedbackReviewListMatching(Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> haveNoFeedbackReviews(){
        return withoutFeedbackReviewListMatching(Q.feedbackReviews().unlimited());
    }
    public CorporateCustomerRequest<T> withCustomerLoyaltyListMatching(CustomerLoyaltyRequest customerLoyaltyRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, customerLoyaltyRequest, CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY));
    }

    public CorporateCustomerRequest<T> withoutCustomerLoyaltyListMatching(CustomerLoyaltyRequest customerLoyaltyRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(CorporateCustomer.ID_PROPERTY, customerLoyaltyRequest, CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY)));
    }

    public CorporateCustomerRequest<T> haveCustomerLoyalties(){
        return withCustomerLoyaltyListMatching(Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> haveNoCustomerLoyalties(){
        return withoutCustomerLoyaltyListMatching(Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> count(){
        super.count();
        return this;
    }
    public CorporateCustomerRequest<T> countAs(String retName){
        super.count(retName);
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
    public CorporateCustomerRequest<T> groupByFeedbackReviewsWithDetails(FeedbackReviewRequest subRequest){
       aggregate(CorporateCustomer.FEEDBACK_REVIEW_LIST_PROPERTY, subRequest);
       return this;
    }
    public CorporateCustomerRequest<T> groupByCustomerLoyaltiesWithDetails(CustomerLoyaltyRequest subRequest){
       aggregate(CorporateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, subRequest);
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

    public CorporateCustomerRequest<T> groupByContactPerson(){
       groupBy(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactPersonAs(String retName){
       groupBy(retName, CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByContactPersonWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.CONTACT_PERSON_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByPhone(){
       groupBy(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByPhoneAs(String retName){
       groupBy(retName, CorporateCustomer.PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.PHONE_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByEmail(){
       groupBy(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByEmailAs(String retName){
       groupBy(retName, CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.EMAIL_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByAddress(){
       groupBy(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByAddressAs(String retName){
       groupBy(retName, CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.ADDRESS_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCity(){
       groupBy(CorporateCustomer.CITY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCityAs(String retName){
       groupBy(retName, CorporateCustomer.CITY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCityWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.CITY_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCountry(){
       groupBy(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCountryAs(String retName){
       groupBy(retName, CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByCountryWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.COUNTRY_PROPERTY, function);
       return this;
    }

    public CorporateCustomerRequest<T> groupByTaxId(){
       groupBy(CorporateCustomer.TAX_ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByTaxIdAs(String retName){
       groupBy(retName, CorporateCustomer.TAX_ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> groupByTaxIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CorporateCustomer.TAX_ID_PROPERTY, function);
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
    public CorporateCustomerRequest<T> orderByContactPersonAscending(){
       addOrderByAscending(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByContactPersonDescending(){
       addOrderByDescending(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByContactPersonAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByContactPersonDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.CONTACT_PERSON_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByPhoneAscending(){
       addOrderByAscending(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByPhoneDescending(){
       addOrderByDescending(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.PHONE_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByEmailAscending(){
       addOrderByAscending(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByEmailDescending(){
       addOrderByDescending(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.EMAIL_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByAddressAscending(){
       addOrderByAscending(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByAddressDescending(){
       addOrderByDescending(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByAddressAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByAddressDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.ADDRESS_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCityAscending(){
       addOrderByAscending(CorporateCustomer.CITY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCityDescending(){
       addOrderByDescending(CorporateCustomer.CITY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCityAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.CITY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCityDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.CITY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCountryAscending(){
       addOrderByAscending(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCountryDescending(){
       addOrderByDescending(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByCountryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByCountryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.COUNTRY_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByTaxIdAscending(){
       addOrderByAscending(CorporateCustomer.TAX_ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByTaxIdDescending(){
       addOrderByDescending(CorporateCustomer.TAX_ID_PROPERTY);
       return this;
    }
    public CorporateCustomerRequest<T> orderByTaxIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CorporateCustomer.TAX_ID_PROPERTY);
       return this;
    }

    public CorporateCustomerRequest<T> orderByTaxIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CorporateCustomer.TAX_ID_PROPERTY);
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
    public CorporateCustomerRequest<T> statsFromFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
       return statsFromFeedbackReviewsAs(name, subRequest, false);
    }

    public CorporateCustomerRequest<T> statsFromFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public CorporateCustomerRequest<T> statsFromFeedbackReviews(FeedbackReviewRequest subRequest){
       return statsFromFeedbackReviewsAs(REFINEMENTS, subRequest);
    }
    public CorporateCustomerRequest<T> statsFromCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
       return statsFromCustomerLoyaltiesAs(name, subRequest, false);
    }

    public CorporateCustomerRequest<T> statsFromCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public CorporateCustomerRequest<T> statsFromCustomerLoyalties(CustomerLoyaltyRequest subRequest){
       return statsFromCustomerLoyaltiesAs(REFINEMENTS, subRequest);
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
    public CorporateCustomerRequest<T> countFeedbackReviews(){
        return countFeedbackReviewsAs("Count");
    }

    public CorporateCustomerRequest<T> countFeedbackReviewsAs(String name){
        return countFeedbackReviewsWith(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> countFeedbackReviewsWith(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.count(), true);
    }
    public CorporateCustomerRequest<T> countCustomerLoyalties(){
        return countCustomerLoyaltiesAs("Count");
    }

    public CorporateCustomerRequest<T> countCustomerLoyaltiesAs(String name){
        return countCustomerLoyaltiesWith(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> countCustomerLoyaltiesWith(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.count(), true);
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
    public CorporateCustomerRequest<T> minRatingOfFeedbackReviews(){
        return minRatingOfFeedbackReviewsAs("minRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> minRatingOfFeedbackReviewsAs(String name){
        return minRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> minRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.minRating(), true);
    }
    public CorporateCustomerRequest<T> maxRatingOfFeedbackReviews(){
        return maxRatingOfFeedbackReviewsAs("maxRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> maxRatingOfFeedbackReviewsAs(String name){
        return maxRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> maxRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.maxRating(), true);
    }
    public CorporateCustomerRequest<T> sumRatingOfFeedbackReviews(){
        return sumRatingOfFeedbackReviewsAs("sumRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> sumRatingOfFeedbackReviewsAs(String name){
        return sumRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> sumRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.sumRating(), true);
    }
    public CorporateCustomerRequest<T> avgRatingOfFeedbackReviews(){
        return avgRatingOfFeedbackReviewsAs("avgRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> avgRatingOfFeedbackReviewsAs(String name){
        return avgRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> avgRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.avgRating(), true);
    }
    public CorporateCustomerRequest<T> standardDeviationRatingOfFeedbackReviews(){
        return standardDeviationRatingOfFeedbackReviewsAs("stdDevRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> standardDeviationRatingOfFeedbackReviewsAs(String name){
        return standardDeviationRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> standardDeviationRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.standardDeviationRating(), true);
    }
    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationRatingOfFeedbackReviews(){
        return squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs("stdDevPopRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs(String name){
        return squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.squareRootOfPopulationStandardDeviationRating(), true);
    }
    public CorporateCustomerRequest<T> sampleVarianceRatingOfFeedbackReviews(){
        return sampleVarianceRatingOfFeedbackReviewsAs("varSampRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> sampleVarianceRatingOfFeedbackReviewsAs(String name){
        return sampleVarianceRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> sampleVarianceRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.sampleVarianceRating(), true);
    }
    public CorporateCustomerRequest<T> samplePopulationVarianceRatingOfFeedbackReviews(){
        return samplePopulationVarianceRatingOfFeedbackReviewsAs("varPopRatingOfFeedbackReviews");
    }

    public CorporateCustomerRequest<T> samplePopulationVarianceRatingOfFeedbackReviewsAs(String name){
        return samplePopulationVarianceRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public CorporateCustomerRequest<T> samplePopulationVarianceRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.samplePopulationVarianceRating(), true);
    }
    public CorporateCustomerRequest<T> minPointsOfCustomerLoyalties(){
        return minPointsOfCustomerLoyaltiesAs("minPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> minPointsOfCustomerLoyaltiesAs(String name){
        return minPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> minPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.minPoints(), true);
    }
    public CorporateCustomerRequest<T> maxPointsOfCustomerLoyalties(){
        return maxPointsOfCustomerLoyaltiesAs("maxPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> maxPointsOfCustomerLoyaltiesAs(String name){
        return maxPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> maxPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.maxPoints(), true);
    }
    public CorporateCustomerRequest<T> sumPointsOfCustomerLoyalties(){
        return sumPointsOfCustomerLoyaltiesAs("sumPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> sumPointsOfCustomerLoyaltiesAs(String name){
        return sumPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> sumPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.sumPoints(), true);
    }
    public CorporateCustomerRequest<T> avgPointsOfCustomerLoyalties(){
        return avgPointsOfCustomerLoyaltiesAs("avgPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> avgPointsOfCustomerLoyaltiesAs(String name){
        return avgPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> avgPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.avgPoints(), true);
    }
    public CorporateCustomerRequest<T> standardDeviationPointsOfCustomerLoyalties(){
        return standardDeviationPointsOfCustomerLoyaltiesAs("stdDevPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> standardDeviationPointsOfCustomerLoyaltiesAs(String name){
        return standardDeviationPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> standardDeviationPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.standardDeviationPoints(), true);
    }
    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationPointsOfCustomerLoyalties(){
        return squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs("stdDevPopPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs(String name){
        return squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.squareRootOfPopulationStandardDeviationPoints(), true);
    }
    public CorporateCustomerRequest<T> sampleVariancePointsOfCustomerLoyalties(){
        return sampleVariancePointsOfCustomerLoyaltiesAs("varSampPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> sampleVariancePointsOfCustomerLoyaltiesAs(String name){
        return sampleVariancePointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> sampleVariancePointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.sampleVariancePoints(), true);
    }
    public CorporateCustomerRequest<T> samplePopulationVariancePointsOfCustomerLoyalties(){
        return samplePopulationVariancePointsOfCustomerLoyaltiesAs("varPopPointsOfCustomerLoyalties");
    }

    public CorporateCustomerRequest<T> samplePopulationVariancePointsOfCustomerLoyaltiesAs(String name){
        return samplePopulationVariancePointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public CorporateCustomerRequest<T> samplePopulationVariancePointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.samplePopulationVariancePoints(), true);
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