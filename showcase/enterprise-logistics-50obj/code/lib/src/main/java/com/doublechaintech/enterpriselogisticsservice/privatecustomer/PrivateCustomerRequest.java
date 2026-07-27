package com.doublechaintech.enterpriselogisticsservice.privatecustomer;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactRequest;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyRequest;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
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
        return selectId().selectName().selectPhone().selectEmail().selectAddressLine1().selectAddressLine2().selectCity().selectState().selectZipCode().selectCountry().selectCustomerType().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public PrivateCustomerRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PrivateCustomerRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectPhone().selectEmail().selectAddressLine1().selectAddressLine2().selectCity().selectState().selectZipCode().selectCountry().selectCustomerType().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public PrivateCustomerRequest<T> selectChildren(){
        super.selectAny();
        selectMovingOrderList().selectCustomerContactList().selectServiceQuoteList().selectCustomerLoyaltyList();
        return selectId().selectName().selectPhone().selectEmail().selectAddressLine1().selectAddressLine2().selectCity().selectState().selectZipCode().selectCountry().selectCustomerType().selectCreatedAt().selectUpdatedAt().selectVersion();
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
    public PrivateCustomerRequest<T> selectPhone(){
       selectProperty(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the phone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  phone) to fetch phone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectPhone(){
       unselectProperty(PrivateCustomer.PHONE_PROPERTY);
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
    public PrivateCustomerRequest<T> selectAddressLine1(){
       selectProperty(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    /**
     * fill the addressLine1 with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  addressLine1) to fetch addressLine1 property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectAddressLine1(){
       unselectProperty(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectAddressLine2(){
       selectProperty(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    /**
     * fill the addressLine2 with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  addressLine2) to fetch addressLine2 property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectAddressLine2(){
       unselectProperty(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectCity(){
       selectProperty(PrivateCustomer.CITY_PROPERTY);
       return this;
    }

    /**
     * fill the city with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  city) to fetch city property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectCity(){
       unselectProperty(PrivateCustomer.CITY_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectState(){
       selectProperty(PrivateCustomer.STATE_PROPERTY);
       return this;
    }

    /**
     * fill the state with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  state) to fetch state property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectState(){
       unselectProperty(PrivateCustomer.STATE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectZipCode(){
       selectProperty(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the zipCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  zipCode) to fetch zipCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectZipCode(){
       unselectProperty(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectCountry(){
       selectProperty(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    /**
     * fill the country with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  country) to fetch country property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectCountry(){
       unselectProperty(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectCustomerType(){
       selectProperty(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the customerType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  customerType) to fetch customerType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectCustomerType(){
       unselectProperty(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectCreatedAt(){
       selectProperty(PrivateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectCreatedAt(){
       unselectProperty(PrivateCustomer.CREATED_AT_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> selectUpdatedAt(){
       selectProperty(PrivateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PrivateCustomerRequest<T> unselectUpdatedAt(){
       unselectProperty(PrivateCustomer.UPDATED_AT_PROPERTY);
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
    public PrivateCustomerRequest<T> selectMovingOrderList(){
       return selectMovingOrderListWith(Q.movingOrders().selectSelf());
    }

    public PrivateCustomerRequest<T> selectMovingOrderListWith(MovingOrderRequest movingOrderList){
       enhanceRelation(PrivateCustomer.MOVING_ORDER_LIST_PROPERTY, movingOrderList);
       return this;
    }
    public PrivateCustomerRequest<T> selectCustomerContactList(){
       return selectCustomerContactListWith(Q.customerContacts().selectSelf());
    }

    public PrivateCustomerRequest<T> selectCustomerContactListWith(CustomerContactRequest customerContactList){
       enhanceRelation(PrivateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, customerContactList);
       return this;
    }
    public PrivateCustomerRequest<T> selectServiceQuoteList(){
       return selectServiceQuoteListWith(Q.serviceQuotes().selectSelf());
    }

    public PrivateCustomerRequest<T> selectServiceQuoteListWith(ServiceQuoteRequest serviceQuoteList){
       enhanceRelation(PrivateCustomer.SERVICE_QUOTE_LIST_PROPERTY, serviceQuoteList);
       return this;
    }
    public PrivateCustomerRequest<T> selectCustomerLoyaltyList(){
       return selectCustomerLoyaltyListWith(Q.customerLoyalties().selectSelf());
    }

    public PrivateCustomerRequest<T> selectCustomerLoyaltyListWith(CustomerLoyaltyRequest customerLoyaltyList){
       enhanceRelation(PrivateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, customerLoyaltyList);
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



    public PrivateCustomerRequest<T> filterByPhone(String... phone){
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

    public PrivateCustomerRequest<T> withPhoneGreaterThan(String phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public PrivateCustomerRequest<T> withPhoneGreaterThanOrEqualTo(String phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public PrivateCustomerRequest<T> withPhoneLessThan(String phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public PrivateCustomerRequest<T> withPhoneLessThanOrEqualTo(String phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public PrivateCustomerRequest<T> withPhoneBetween(String startOfPhone, String endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }
    public PrivateCustomerRequest<T> withPhoneStartingWith(String phone){
       return withPhone(Operator.BEGIN_WITH, phone);
    }
    public PrivateCustomerRequest<T> withPhoneContaining(String phone){
       return withPhone(Operator.CONTAIN, phone);
    }

    public PrivateCustomerRequest<T> withPhoneEndingWith(String phone){
       return withPhone(Operator.END_WITH, phone);
    }

    public PrivateCustomerRequest<T> withPhoneIs(String phone){
       return withPhone(Operator.EQUAL, phone);
    }

    public PrivateCustomerRequest<T> withPhoneSoundingLike(String phone){
       return withPhone(Operator.SOUNDS_LIKE, phone);
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



    public PrivateCustomerRequest<T> filterByAddressLine1(String... addressLine1){
      if (addressLine1 == null || addressLine1.length == 0) {
        throw new IllegalArgumentException("filterByAddressLine1 parameter addressLine1 cannot be empty");
      }
      return appendSearchCriteria(createAddressLine1Criteria(Operator.EQUAL, (Object[])addressLine1));
    }

    public PrivateCustomerRequest<T> withAddressLine1(Operator operator, Object... values){
       return appendSearchCriteria(createAddressLine1Criteria(operator, values));
    }

    public PrivateCustomerRequest<T> withAddressLine1IsUnknown(){
       return withAddressLine1(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withAddressLine1IsKnown(){
       return withAddressLine1(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressLine1Criteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.ADDRESS_LINE1_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withAddressLine1GreaterThan(String addressLine1){
       return withAddressLine1(Operator.GREATER_THAN, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1GreaterThanOrEqualTo(String addressLine1){
       return withAddressLine1(Operator.GREATER_THAN_OR_EQUAL, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1LessThan(String addressLine1){
       return withAddressLine1(Operator.LESS_THAN, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1LessThanOrEqualTo(String addressLine1){
       return withAddressLine1(Operator.LESS_THAN_OR_EQUAL, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1Between(String startOfAddressLine1, String endOfAddressLine1){
       return withAddressLine1(Operator.BETWEEN, startOfAddressLine1, endOfAddressLine1);
    }
    public PrivateCustomerRequest<T> withAddressLine1StartingWith(String addressLine1){
       return withAddressLine1(Operator.BEGIN_WITH, addressLine1);
    }
    public PrivateCustomerRequest<T> withAddressLine1Containing(String addressLine1){
       return withAddressLine1(Operator.CONTAIN, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1EndingWith(String addressLine1){
       return withAddressLine1(Operator.END_WITH, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1Is(String addressLine1){
       return withAddressLine1(Operator.EQUAL, addressLine1);
    }

    public PrivateCustomerRequest<T> withAddressLine1SoundingLike(String addressLine1){
       return withAddressLine1(Operator.SOUNDS_LIKE, addressLine1);
    }



    public PrivateCustomerRequest<T> filterByAddressLine2(String... addressLine2){
      if (addressLine2 == null || addressLine2.length == 0) {
        throw new IllegalArgumentException("filterByAddressLine2 parameter addressLine2 cannot be empty");
      }
      return appendSearchCriteria(createAddressLine2Criteria(Operator.EQUAL, (Object[])addressLine2));
    }

    public PrivateCustomerRequest<T> withAddressLine2(Operator operator, Object... values){
       return appendSearchCriteria(createAddressLine2Criteria(operator, values));
    }

    public PrivateCustomerRequest<T> withAddressLine2IsUnknown(){
       return withAddressLine2(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withAddressLine2IsKnown(){
       return withAddressLine2(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressLine2Criteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.ADDRESS_LINE2_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withAddressLine2GreaterThan(String addressLine2){
       return withAddressLine2(Operator.GREATER_THAN, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2GreaterThanOrEqualTo(String addressLine2){
       return withAddressLine2(Operator.GREATER_THAN_OR_EQUAL, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2LessThan(String addressLine2){
       return withAddressLine2(Operator.LESS_THAN, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2LessThanOrEqualTo(String addressLine2){
       return withAddressLine2(Operator.LESS_THAN_OR_EQUAL, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2Between(String startOfAddressLine2, String endOfAddressLine2){
       return withAddressLine2(Operator.BETWEEN, startOfAddressLine2, endOfAddressLine2);
    }
    public PrivateCustomerRequest<T> withAddressLine2StartingWith(String addressLine2){
       return withAddressLine2(Operator.BEGIN_WITH, addressLine2);
    }
    public PrivateCustomerRequest<T> withAddressLine2Containing(String addressLine2){
       return withAddressLine2(Operator.CONTAIN, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2EndingWith(String addressLine2){
       return withAddressLine2(Operator.END_WITH, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2Is(String addressLine2){
       return withAddressLine2(Operator.EQUAL, addressLine2);
    }

    public PrivateCustomerRequest<T> withAddressLine2SoundingLike(String addressLine2){
       return withAddressLine2(Operator.SOUNDS_LIKE, addressLine2);
    }



    public PrivateCustomerRequest<T> filterByCity(String... city){
      if (city == null || city.length == 0) {
        throw new IllegalArgumentException("filterByCity parameter city cannot be empty");
      }
      return appendSearchCriteria(createCityCriteria(Operator.EQUAL, (Object[])city));
    }

    public PrivateCustomerRequest<T> withCity(Operator operator, Object... values){
       return appendSearchCriteria(createCityCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withCityIsUnknown(){
       return withCity(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withCityIsKnown(){
       return withCity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.CITY_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withCityGreaterThan(String city){
       return withCity(Operator.GREATER_THAN, city);
    }

    public PrivateCustomerRequest<T> withCityGreaterThanOrEqualTo(String city){
       return withCity(Operator.GREATER_THAN_OR_EQUAL, city);
    }

    public PrivateCustomerRequest<T> withCityLessThan(String city){
       return withCity(Operator.LESS_THAN, city);
    }

    public PrivateCustomerRequest<T> withCityLessThanOrEqualTo(String city){
       return withCity(Operator.LESS_THAN_OR_EQUAL, city);
    }

    public PrivateCustomerRequest<T> withCityBetween(String startOfCity, String endOfCity){
       return withCity(Operator.BETWEEN, startOfCity, endOfCity);
    }
    public PrivateCustomerRequest<T> withCityStartingWith(String city){
       return withCity(Operator.BEGIN_WITH, city);
    }
    public PrivateCustomerRequest<T> withCityContaining(String city){
       return withCity(Operator.CONTAIN, city);
    }

    public PrivateCustomerRequest<T> withCityEndingWith(String city){
       return withCity(Operator.END_WITH, city);
    }

    public PrivateCustomerRequest<T> withCityIs(String city){
       return withCity(Operator.EQUAL, city);
    }

    public PrivateCustomerRequest<T> withCitySoundingLike(String city){
       return withCity(Operator.SOUNDS_LIKE, city);
    }



    public PrivateCustomerRequest<T> filterByState(String... state){
      if (state == null || state.length == 0) {
        throw new IllegalArgumentException("filterByState parameter state cannot be empty");
      }
      return appendSearchCriteria(createStateCriteria(Operator.EQUAL, (Object[])state));
    }

    public PrivateCustomerRequest<T> withState(Operator operator, Object... values){
       return appendSearchCriteria(createStateCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withStateIsUnknown(){
       return withState(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withStateIsKnown(){
       return withState(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.STATE_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withStateGreaterThan(String state){
       return withState(Operator.GREATER_THAN, state);
    }

    public PrivateCustomerRequest<T> withStateGreaterThanOrEqualTo(String state){
       return withState(Operator.GREATER_THAN_OR_EQUAL, state);
    }

    public PrivateCustomerRequest<T> withStateLessThan(String state){
       return withState(Operator.LESS_THAN, state);
    }

    public PrivateCustomerRequest<T> withStateLessThanOrEqualTo(String state){
       return withState(Operator.LESS_THAN_OR_EQUAL, state);
    }

    public PrivateCustomerRequest<T> withStateBetween(String startOfState, String endOfState){
       return withState(Operator.BETWEEN, startOfState, endOfState);
    }
    public PrivateCustomerRequest<T> withStateStartingWith(String state){
       return withState(Operator.BEGIN_WITH, state);
    }
    public PrivateCustomerRequest<T> withStateContaining(String state){
       return withState(Operator.CONTAIN, state);
    }

    public PrivateCustomerRequest<T> withStateEndingWith(String state){
       return withState(Operator.END_WITH, state);
    }

    public PrivateCustomerRequest<T> withStateIs(String state){
       return withState(Operator.EQUAL, state);
    }

    public PrivateCustomerRequest<T> withStateSoundingLike(String state){
       return withState(Operator.SOUNDS_LIKE, state);
    }



    public PrivateCustomerRequest<T> filterByZipCode(String... zipCode){
      if (zipCode == null || zipCode.length == 0) {
        throw new IllegalArgumentException("filterByZipCode parameter zipCode cannot be empty");
      }
      return appendSearchCriteria(createZipCodeCriteria(Operator.EQUAL, (Object[])zipCode));
    }

    public PrivateCustomerRequest<T> withZipCode(Operator operator, Object... values){
       return appendSearchCriteria(createZipCodeCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withZipCodeIsUnknown(){
       return withZipCode(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withZipCodeIsKnown(){
       return withZipCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createZipCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.ZIP_CODE_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withZipCodeGreaterThan(String zipCode){
       return withZipCode(Operator.GREATER_THAN, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeGreaterThanOrEqualTo(String zipCode){
       return withZipCode(Operator.GREATER_THAN_OR_EQUAL, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeLessThan(String zipCode){
       return withZipCode(Operator.LESS_THAN, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeLessThanOrEqualTo(String zipCode){
       return withZipCode(Operator.LESS_THAN_OR_EQUAL, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeBetween(String startOfZipCode, String endOfZipCode){
       return withZipCode(Operator.BETWEEN, startOfZipCode, endOfZipCode);
    }
    public PrivateCustomerRequest<T> withZipCodeStartingWith(String zipCode){
       return withZipCode(Operator.BEGIN_WITH, zipCode);
    }
    public PrivateCustomerRequest<T> withZipCodeContaining(String zipCode){
       return withZipCode(Operator.CONTAIN, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeEndingWith(String zipCode){
       return withZipCode(Operator.END_WITH, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeIs(String zipCode){
       return withZipCode(Operator.EQUAL, zipCode);
    }

    public PrivateCustomerRequest<T> withZipCodeSoundingLike(String zipCode){
       return withZipCode(Operator.SOUNDS_LIKE, zipCode);
    }



    public PrivateCustomerRequest<T> filterByCountry(String... country){
      if (country == null || country.length == 0) {
        throw new IllegalArgumentException("filterByCountry parameter country cannot be empty");
      }
      return appendSearchCriteria(createCountryCriteria(Operator.EQUAL, (Object[])country));
    }

    public PrivateCustomerRequest<T> withCountry(Operator operator, Object... values){
       return appendSearchCriteria(createCountryCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withCountryIsUnknown(){
       return withCountry(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withCountryIsKnown(){
       return withCountry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCountryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.COUNTRY_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withCountryGreaterThan(String country){
       return withCountry(Operator.GREATER_THAN, country);
    }

    public PrivateCustomerRequest<T> withCountryGreaterThanOrEqualTo(String country){
       return withCountry(Operator.GREATER_THAN_OR_EQUAL, country);
    }

    public PrivateCustomerRequest<T> withCountryLessThan(String country){
       return withCountry(Operator.LESS_THAN, country);
    }

    public PrivateCustomerRequest<T> withCountryLessThanOrEqualTo(String country){
       return withCountry(Operator.LESS_THAN_OR_EQUAL, country);
    }

    public PrivateCustomerRequest<T> withCountryBetween(String startOfCountry, String endOfCountry){
       return withCountry(Operator.BETWEEN, startOfCountry, endOfCountry);
    }
    public PrivateCustomerRequest<T> withCountryStartingWith(String country){
       return withCountry(Operator.BEGIN_WITH, country);
    }
    public PrivateCustomerRequest<T> withCountryContaining(String country){
       return withCountry(Operator.CONTAIN, country);
    }

    public PrivateCustomerRequest<T> withCountryEndingWith(String country){
       return withCountry(Operator.END_WITH, country);
    }

    public PrivateCustomerRequest<T> withCountryIs(String country){
       return withCountry(Operator.EQUAL, country);
    }

    public PrivateCustomerRequest<T> withCountrySoundingLike(String country){
       return withCountry(Operator.SOUNDS_LIKE, country);
    }



    public PrivateCustomerRequest<T> filterByCustomerType(String... customerType){
      if (customerType == null || customerType.length == 0) {
        throw new IllegalArgumentException("filterByCustomerType parameter customerType cannot be empty");
      }
      return appendSearchCriteria(createCustomerTypeCriteria(Operator.EQUAL, (Object[])customerType));
    }

    public PrivateCustomerRequest<T> withCustomerType(Operator operator, Object... values){
       return appendSearchCriteria(createCustomerTypeCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withCustomerTypeIsUnknown(){
       return withCustomerType(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withCustomerTypeIsKnown(){
       return withCustomerType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCustomerTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.CUSTOMER_TYPE_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withCustomerTypeGreaterThan(String customerType){
       return withCustomerType(Operator.GREATER_THAN, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeGreaterThanOrEqualTo(String customerType){
       return withCustomerType(Operator.GREATER_THAN_OR_EQUAL, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeLessThan(String customerType){
       return withCustomerType(Operator.LESS_THAN, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeLessThanOrEqualTo(String customerType){
       return withCustomerType(Operator.LESS_THAN_OR_EQUAL, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeBetween(String startOfCustomerType, String endOfCustomerType){
       return withCustomerType(Operator.BETWEEN, startOfCustomerType, endOfCustomerType);
    }
    public PrivateCustomerRequest<T> withCustomerTypeStartingWith(String customerType){
       return withCustomerType(Operator.BEGIN_WITH, customerType);
    }
    public PrivateCustomerRequest<T> withCustomerTypeContaining(String customerType){
       return withCustomerType(Operator.CONTAIN, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeEndingWith(String customerType){
       return withCustomerType(Operator.END_WITH, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeIs(String customerType){
       return withCustomerType(Operator.EQUAL, customerType);
    }

    public PrivateCustomerRequest<T> withCustomerTypeSoundingLike(String customerType){
       return withCustomerType(Operator.SOUNDS_LIKE, customerType);
    }



    public PrivateCustomerRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public PrivateCustomerRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.CREATED_AT_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public PrivateCustomerRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PrivateCustomerRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public PrivateCustomerRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public PrivateCustomerRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public PrivateCustomerRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public PrivateCustomerRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PrivateCustomer.UPDATED_AT_PROPERTY, operator, values);
    }

    public PrivateCustomerRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public PrivateCustomerRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PrivateCustomerRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
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

    public PrivateCustomerRequest<T> withMovingOrderListMatching(MovingOrderRequest movingOrderRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, movingOrderRequest, MovingOrder.CUSTOMER_PROPERTY));
    }

    public PrivateCustomerRequest<T> withoutMovingOrderListMatching(MovingOrderRequest movingOrderRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, movingOrderRequest, MovingOrder.CUSTOMER_PROPERTY)));
    }

    public PrivateCustomerRequest<T> haveMovingOrders(){
        return withMovingOrderListMatching(Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> haveNoMovingOrders(){
        return withoutMovingOrderListMatching(Q.movingOrders().unlimited());
    }
    public PrivateCustomerRequest<T> withCustomerContactListMatching(CustomerContactRequest customerContactRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, customerContactRequest, CustomerContact.PRIVATE_CUSTOMER_PROPERTY));
    }

    public PrivateCustomerRequest<T> withoutCustomerContactListMatching(CustomerContactRequest customerContactRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, customerContactRequest, CustomerContact.PRIVATE_CUSTOMER_PROPERTY)));
    }

    public PrivateCustomerRequest<T> haveCustomerContacts(){
        return withCustomerContactListMatching(Q.customerContacts().unlimited());
    }

    public PrivateCustomerRequest<T> haveNoCustomerContacts(){
        return withoutCustomerContactListMatching(Q.customerContacts().unlimited());
    }
    public PrivateCustomerRequest<T> withServiceQuoteListMatching(ServiceQuoteRequest serviceQuoteRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, serviceQuoteRequest, ServiceQuote.PRIVATE_CUSTOMER_PROPERTY));
    }

    public PrivateCustomerRequest<T> withoutServiceQuoteListMatching(ServiceQuoteRequest serviceQuoteRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, serviceQuoteRequest, ServiceQuote.PRIVATE_CUSTOMER_PROPERTY)));
    }

    public PrivateCustomerRequest<T> haveServiceQuotes(){
        return withServiceQuoteListMatching(Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> haveNoServiceQuotes(){
        return withoutServiceQuoteListMatching(Q.serviceQuotes().unlimited());
    }
    public PrivateCustomerRequest<T> withCustomerLoyaltyListMatching(CustomerLoyaltyRequest customerLoyaltyRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, customerLoyaltyRequest, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY));
    }

    public PrivateCustomerRequest<T> withoutCustomerLoyaltyListMatching(CustomerLoyaltyRequest customerLoyaltyRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PrivateCustomer.ID_PROPERTY, customerLoyaltyRequest, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY)));
    }

    public PrivateCustomerRequest<T> haveCustomerLoyalties(){
        return withCustomerLoyaltyListMatching(Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> haveNoCustomerLoyalties(){
        return withoutCustomerLoyaltyListMatching(Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> count(){
        super.count();
        return this;
    }
    public PrivateCustomerRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PrivateCustomerRequest<T> groupByMovingOrdersWithDetails(MovingOrderRequest subRequest){
       aggregate(PrivateCustomer.MOVING_ORDER_LIST_PROPERTY, subRequest);
       return this;
    }
    public PrivateCustomerRequest<T> groupByCustomerContactsWithDetails(CustomerContactRequest subRequest){
       aggregate(PrivateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, subRequest);
       return this;
    }
    public PrivateCustomerRequest<T> groupByServiceQuotesWithDetails(ServiceQuoteRequest subRequest){
       aggregate(PrivateCustomer.SERVICE_QUOTE_LIST_PROPERTY, subRequest);
       return this;
    }
    public PrivateCustomerRequest<T> groupByCustomerLoyaltiesWithDetails(CustomerLoyaltyRequest subRequest){
       aggregate(PrivateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, subRequest);
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

    public PrivateCustomerRequest<T> groupByAddressLine1(){
       groupBy(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressLine1As(String retName){
       groupBy(retName, PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressLine1WithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.ADDRESS_LINE1_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressLine2(){
       groupBy(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressLine2As(String retName){
       groupBy(retName, PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByAddressLine2WithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.ADDRESS_LINE2_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCity(){
       groupBy(PrivateCustomer.CITY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCityAs(String retName){
       groupBy(retName, PrivateCustomer.CITY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCityWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.CITY_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByState(){
       groupBy(PrivateCustomer.STATE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByStateAs(String retName){
       groupBy(retName, PrivateCustomer.STATE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByStateWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.STATE_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByZipCode(){
       groupBy(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByZipCodeAs(String retName){
       groupBy(retName, PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByZipCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.ZIP_CODE_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCountry(){
       groupBy(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCountryAs(String retName){
       groupBy(retName, PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCountryWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.COUNTRY_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCustomerType(){
       groupBy(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCustomerTypeAs(String retName){
       groupBy(retName, PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCustomerTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.CUSTOMER_TYPE_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCreatedAt(){
       groupBy(PrivateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, PrivateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.CREATED_AT_PROPERTY, function);
       return this;
    }

    public PrivateCustomerRequest<T> groupByUpdatedAt(){
       groupBy(PrivateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, PrivateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, PrivateCustomer.UPDATED_AT_PROPERTY, function);
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
    public PrivateCustomerRequest<T> orderByPhoneAscending(){
       addOrderByAscending(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByPhoneDescending(){
       addOrderByDescending(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.PHONE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.PHONE_PROPERTY);
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
    public PrivateCustomerRequest<T> orderByAddressLine1Ascending(){
       addOrderByAscending(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressLine1Descending(){
       addOrderByDescending(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByAddressLine1AscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressLine1DescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.ADDRESS_LINE1_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByAddressLine2Ascending(){
       addOrderByAscending(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressLine2Descending(){
       addOrderByDescending(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByAddressLine2AscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByAddressLine2DescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.ADDRESS_LINE2_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCityAscending(){
       addOrderByAscending(PrivateCustomer.CITY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCityDescending(){
       addOrderByDescending(PrivateCustomer.CITY_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCityAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.CITY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCityDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.CITY_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByStateAscending(){
       addOrderByAscending(PrivateCustomer.STATE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByStateDescending(){
       addOrderByDescending(PrivateCustomer.STATE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByStateAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.STATE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByStateDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.STATE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByZipCodeAscending(){
       addOrderByAscending(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByZipCodeDescending(){
       addOrderByDescending(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByZipCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByZipCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.ZIP_CODE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCountryAscending(){
       addOrderByAscending(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCountryDescending(){
       addOrderByDescending(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCountryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCountryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.COUNTRY_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCustomerTypeAscending(){
       addOrderByAscending(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCustomerTypeDescending(){
       addOrderByDescending(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCustomerTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCustomerTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PrivateCustomer.CUSTOMER_TYPE_PROPERTY);
       return this;
    }
    public PrivateCustomerRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(PrivateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(PrivateCustomer.CREATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(PrivateCustomer.UPDATED_AT_PROPERTY);
       return this;
    }

    public PrivateCustomerRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(PrivateCustomer.UPDATED_AT_PROPERTY);
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


    public PrivateCustomerRequest<T> statsFromMovingOrdersAs(String name, MovingOrderRequest subRequest){
       return statsFromMovingOrdersAs(name, subRequest, false);
    }

    public PrivateCustomerRequest<T> statsFromMovingOrdersAs(String name, MovingOrderRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(MovingOrder.CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PrivateCustomerRequest<T> statsFromMovingOrders(MovingOrderRequest subRequest){
       return statsFromMovingOrdersAs(REFINEMENTS, subRequest);
    }
    public PrivateCustomerRequest<T> statsFromCustomerContactsAs(String name, CustomerContactRequest subRequest){
       return statsFromCustomerContactsAs(name, subRequest, false);
    }

    public PrivateCustomerRequest<T> statsFromCustomerContactsAs(String name, CustomerContactRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(CustomerContact.PRIVATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PrivateCustomerRequest<T> statsFromCustomerContacts(CustomerContactRequest subRequest){
       return statsFromCustomerContactsAs(REFINEMENTS, subRequest);
    }
    public PrivateCustomerRequest<T> statsFromServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
       return statsFromServiceQuotesAs(name, subRequest, false);
    }

    public PrivateCustomerRequest<T> statsFromServiceQuotesAs(String name, ServiceQuoteRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PrivateCustomerRequest<T> statsFromServiceQuotes(ServiceQuoteRequest subRequest){
       return statsFromServiceQuotesAs(REFINEMENTS, subRequest);
    }
    public PrivateCustomerRequest<T> statsFromCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
       return statsFromCustomerLoyaltiesAs(name, subRequest, false);
    }

    public PrivateCustomerRequest<T> statsFromCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PrivateCustomerRequest<T> statsFromCustomerLoyalties(CustomerLoyaltyRequest subRequest){
       return statsFromCustomerLoyaltiesAs(REFINEMENTS, subRequest);
    }
    public PrivateCustomerRequest<T> countMovingOrders(){
        return countMovingOrdersAs("Count");
    }

    public PrivateCustomerRequest<T> countMovingOrdersAs(String name){
        return countMovingOrdersWith(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> countMovingOrdersWith(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.count(), true);
    }
    public PrivateCustomerRequest<T> countCustomerContacts(){
        return countCustomerContactsAs("Count");
    }

    public PrivateCustomerRequest<T> countCustomerContactsAs(String name){
        return countCustomerContactsWith(name, Q.customerContacts().unlimited());
    }

    public PrivateCustomerRequest<T> countCustomerContactsWith(String name, CustomerContactRequest subRequest){
        return statsFromCustomerContactsAs(name, subRequest.count(), true);
    }
    public PrivateCustomerRequest<T> countServiceQuotes(){
        return countServiceQuotesAs("Count");
    }

    public PrivateCustomerRequest<T> countServiceQuotesAs(String name){
        return countServiceQuotesWith(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> countServiceQuotesWith(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.count(), true);
    }
    public PrivateCustomerRequest<T> countCustomerLoyalties(){
        return countCustomerLoyaltiesAs("Count");
    }

    public PrivateCustomerRequest<T> countCustomerLoyaltiesAs(String name){
        return countCustomerLoyaltiesWith(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> countCustomerLoyaltiesWith(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.count(), true);
    }
    public PrivateCustomerRequest<T> minTotalWeightOfMovingOrders(){
        return minTotalWeightOfMovingOrdersAs("minTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> minTotalWeightOfMovingOrdersAs(String name){
        return minTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> minTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.minTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> maxTotalWeightOfMovingOrders(){
        return maxTotalWeightOfMovingOrdersAs("maxTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> maxTotalWeightOfMovingOrdersAs(String name){
        return maxTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> maxTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.maxTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> sumTotalWeightOfMovingOrders(){
        return sumTotalWeightOfMovingOrdersAs("sumTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sumTotalWeightOfMovingOrdersAs(String name){
        return sumTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sumTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sumTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> avgTotalWeightOfMovingOrders(){
        return avgTotalWeightOfMovingOrdersAs("avgTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> avgTotalWeightOfMovingOrdersAs(String name){
        return avgTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> avgTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.avgTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> standardDeviationTotalWeightOfMovingOrders(){
        return standardDeviationTotalWeightOfMovingOrdersAs("stdDevTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> standardDeviationTotalWeightOfMovingOrdersAs(String name){
        return standardDeviationTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> standardDeviationTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.standardDeviationTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrders(){
        return squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAs("stdDevPopTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAs(String name){
        return squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> sampleVarianceTotalWeightOfMovingOrders(){
        return sampleVarianceTotalWeightOfMovingOrdersAs("varSampTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sampleVarianceTotalWeightOfMovingOrdersAs(String name){
        return sampleVarianceTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sampleVarianceTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sampleVarianceTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> samplePopulationVarianceTotalWeightOfMovingOrders(){
        return samplePopulationVarianceTotalWeightOfMovingOrdersAs("varPopTotalWeightOfMovingOrders");
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAs(String name){
        return samplePopulationVarianceTotalWeightOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.samplePopulationVarianceTotalWeight(), true);
    }
    public PrivateCustomerRequest<T> minTotalVolumeOfMovingOrders(){
        return minTotalVolumeOfMovingOrdersAs("minTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> minTotalVolumeOfMovingOrdersAs(String name){
        return minTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> minTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.minTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> maxTotalVolumeOfMovingOrders(){
        return maxTotalVolumeOfMovingOrdersAs("maxTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> maxTotalVolumeOfMovingOrdersAs(String name){
        return maxTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> maxTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.maxTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> sumTotalVolumeOfMovingOrders(){
        return sumTotalVolumeOfMovingOrdersAs("sumTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sumTotalVolumeOfMovingOrdersAs(String name){
        return sumTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sumTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sumTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> avgTotalVolumeOfMovingOrders(){
        return avgTotalVolumeOfMovingOrdersAs("avgTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> avgTotalVolumeOfMovingOrdersAs(String name){
        return avgTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> avgTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.avgTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> standardDeviationTotalVolumeOfMovingOrders(){
        return standardDeviationTotalVolumeOfMovingOrdersAs("stdDevTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> standardDeviationTotalVolumeOfMovingOrdersAs(String name){
        return standardDeviationTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> standardDeviationTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.standardDeviationTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrders(){
        return squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAs("stdDevPopTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAs(String name){
        return squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> sampleVarianceTotalVolumeOfMovingOrders(){
        return sampleVarianceTotalVolumeOfMovingOrdersAs("varSampTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAs(String name){
        return sampleVarianceTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sampleVarianceTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrders(){
        return samplePopulationVarianceTotalVolumeOfMovingOrdersAs("varPopTotalVolumeOfMovingOrders");
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAs(String name){
        return samplePopulationVarianceTotalVolumeOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.samplePopulationVarianceTotalVolume(), true);
    }
    public PrivateCustomerRequest<T> minEstimatedCostOfMovingOrders(){
        return minEstimatedCostOfMovingOrdersAs("minEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> minEstimatedCostOfMovingOrdersAs(String name){
        return minEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> minEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.minEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> maxEstimatedCostOfMovingOrders(){
        return maxEstimatedCostOfMovingOrdersAs("maxEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> maxEstimatedCostOfMovingOrdersAs(String name){
        return maxEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> maxEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.maxEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> sumEstimatedCostOfMovingOrders(){
        return sumEstimatedCostOfMovingOrdersAs("sumEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sumEstimatedCostOfMovingOrdersAs(String name){
        return sumEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sumEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sumEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> avgEstimatedCostOfMovingOrders(){
        return avgEstimatedCostOfMovingOrdersAs("avgEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> avgEstimatedCostOfMovingOrdersAs(String name){
        return avgEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> avgEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.avgEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> standardDeviationEstimatedCostOfMovingOrders(){
        return standardDeviationEstimatedCostOfMovingOrdersAs("stdDevEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> standardDeviationEstimatedCostOfMovingOrdersAs(String name){
        return standardDeviationEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> standardDeviationEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.standardDeviationEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrders(){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAs("stdDevPopEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAs(String name){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.squareRootOfPopulationStandardDeviationEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> sampleVarianceEstimatedCostOfMovingOrders(){
        return sampleVarianceEstimatedCostOfMovingOrdersAs("varSampEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAs(String name){
        return sampleVarianceEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sampleVarianceEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrders(){
        return samplePopulationVarianceEstimatedCostOfMovingOrdersAs("varPopEstimatedCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAs(String name){
        return samplePopulationVarianceEstimatedCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.samplePopulationVarianceEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> minActualCostOfMovingOrders(){
        return minActualCostOfMovingOrdersAs("minActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> minActualCostOfMovingOrdersAs(String name){
        return minActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> minActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.minActualCost(), true);
    }
    public PrivateCustomerRequest<T> maxActualCostOfMovingOrders(){
        return maxActualCostOfMovingOrdersAs("maxActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> maxActualCostOfMovingOrdersAs(String name){
        return maxActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> maxActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.maxActualCost(), true);
    }
    public PrivateCustomerRequest<T> sumActualCostOfMovingOrders(){
        return sumActualCostOfMovingOrdersAs("sumActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sumActualCostOfMovingOrdersAs(String name){
        return sumActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sumActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sumActualCost(), true);
    }
    public PrivateCustomerRequest<T> avgActualCostOfMovingOrders(){
        return avgActualCostOfMovingOrdersAs("avgActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> avgActualCostOfMovingOrdersAs(String name){
        return avgActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> avgActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.avgActualCost(), true);
    }
    public PrivateCustomerRequest<T> standardDeviationActualCostOfMovingOrders(){
        return standardDeviationActualCostOfMovingOrdersAs("stdDevActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> standardDeviationActualCostOfMovingOrdersAs(String name){
        return standardDeviationActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> standardDeviationActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.standardDeviationActualCost(), true);
    }
    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrders(){
        return squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAs("stdDevPopActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAs(String name){
        return squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.squareRootOfPopulationStandardDeviationActualCost(), true);
    }
    public PrivateCustomerRequest<T> sampleVarianceActualCostOfMovingOrders(){
        return sampleVarianceActualCostOfMovingOrdersAs("varSampActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> sampleVarianceActualCostOfMovingOrdersAs(String name){
        return sampleVarianceActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> sampleVarianceActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.sampleVarianceActualCost(), true);
    }
    public PrivateCustomerRequest<T> samplePopulationVarianceActualCostOfMovingOrders(){
        return samplePopulationVarianceActualCostOfMovingOrdersAs("varPopActualCostOfMovingOrders");
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAs(String name){
        return samplePopulationVarianceActualCostOfMovingOrdersAs(name, Q.movingOrders().unlimited());
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAs(name, subRequest.samplePopulationVarianceActualCost(), true);
    }
    public PrivateCustomerRequest<T> minEstimatedCostOfServiceQuotes(){
        return minEstimatedCostOfServiceQuotesAs("minEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> minEstimatedCostOfServiceQuotesAs(String name){
        return minEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> minEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.minEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> maxEstimatedCostOfServiceQuotes(){
        return maxEstimatedCostOfServiceQuotesAs("maxEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> maxEstimatedCostOfServiceQuotesAs(String name){
        return maxEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> maxEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.maxEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> sumEstimatedCostOfServiceQuotes(){
        return sumEstimatedCostOfServiceQuotesAs("sumEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> sumEstimatedCostOfServiceQuotesAs(String name){
        return sumEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> sumEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.sumEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> avgEstimatedCostOfServiceQuotes(){
        return avgEstimatedCostOfServiceQuotesAs("avgEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> avgEstimatedCostOfServiceQuotesAs(String name){
        return avgEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> avgEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.avgEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> standardDeviationEstimatedCostOfServiceQuotes(){
        return standardDeviationEstimatedCostOfServiceQuotesAs("stdDevEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> standardDeviationEstimatedCostOfServiceQuotesAs(String name){
        return standardDeviationEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> standardDeviationEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.standardDeviationEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotes(){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs("stdDevPopEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs(String name){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.squareRootOfPopulationStandardDeviationEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> sampleVarianceEstimatedCostOfServiceQuotes(){
        return sampleVarianceEstimatedCostOfServiceQuotesAs("varSampEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> sampleVarianceEstimatedCostOfServiceQuotesAs(String name){
        return sampleVarianceEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> sampleVarianceEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.sampleVarianceEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfServiceQuotes(){
        return samplePopulationVarianceEstimatedCostOfServiceQuotesAs("varPopEstimatedCostOfServiceQuotes");
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfServiceQuotesAs(String name){
        return samplePopulationVarianceEstimatedCostOfServiceQuotesAs(name, Q.serviceQuotes().unlimited());
    }

    public PrivateCustomerRequest<T> samplePopulationVarianceEstimatedCostOfServiceQuotesAs(String name, ServiceQuoteRequest subRequest){
        return statsFromServiceQuotesAs(name, subRequest.samplePopulationVarianceEstimatedCost(), true);
    }
    public PrivateCustomerRequest<T> minPointsOfCustomerLoyalties(){
        return minPointsOfCustomerLoyaltiesAs("minPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> minPointsOfCustomerLoyaltiesAs(String name){
        return minPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> minPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.minPoints(), true);
    }
    public PrivateCustomerRequest<T> maxPointsOfCustomerLoyalties(){
        return maxPointsOfCustomerLoyaltiesAs("maxPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> maxPointsOfCustomerLoyaltiesAs(String name){
        return maxPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> maxPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.maxPoints(), true);
    }
    public PrivateCustomerRequest<T> sumPointsOfCustomerLoyalties(){
        return sumPointsOfCustomerLoyaltiesAs("sumPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> sumPointsOfCustomerLoyaltiesAs(String name){
        return sumPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> sumPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.sumPoints(), true);
    }
    public PrivateCustomerRequest<T> avgPointsOfCustomerLoyalties(){
        return avgPointsOfCustomerLoyaltiesAs("avgPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> avgPointsOfCustomerLoyaltiesAs(String name){
        return avgPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> avgPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.avgPoints(), true);
    }
    public PrivateCustomerRequest<T> standardDeviationPointsOfCustomerLoyalties(){
        return standardDeviationPointsOfCustomerLoyaltiesAs("stdDevPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> standardDeviationPointsOfCustomerLoyaltiesAs(String name){
        return standardDeviationPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> standardDeviationPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.standardDeviationPoints(), true);
    }
    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationPointsOfCustomerLoyalties(){
        return squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs("stdDevPopPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs(String name){
        return squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> squareRootOfPopulationStandardDeviationPointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.squareRootOfPopulationStandardDeviationPoints(), true);
    }
    public PrivateCustomerRequest<T> sampleVariancePointsOfCustomerLoyalties(){
        return sampleVariancePointsOfCustomerLoyaltiesAs("varSampPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> sampleVariancePointsOfCustomerLoyaltiesAs(String name){
        return sampleVariancePointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> sampleVariancePointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.sampleVariancePoints(), true);
    }
    public PrivateCustomerRequest<T> samplePopulationVariancePointsOfCustomerLoyalties(){
        return samplePopulationVariancePointsOfCustomerLoyaltiesAs("varPopPointsOfCustomerLoyalties");
    }

    public PrivateCustomerRequest<T> samplePopulationVariancePointsOfCustomerLoyaltiesAs(String name){
        return samplePopulationVariancePointsOfCustomerLoyaltiesAs(name, Q.customerLoyalties().unlimited());
    }

    public PrivateCustomerRequest<T> samplePopulationVariancePointsOfCustomerLoyaltiesAs(String name, CustomerLoyaltyRequest subRequest){
        return statsFromCustomerLoyaltiesAs(name, subRequest.samplePopulationVariancePoints(), true);
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