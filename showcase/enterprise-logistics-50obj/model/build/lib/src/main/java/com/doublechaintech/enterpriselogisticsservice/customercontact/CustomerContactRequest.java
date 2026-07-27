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
        return selectId().selectName().selectPhone().selectEmail().selectRelationship().selectPrivateCustomerIdOnly().selectCorporateCustomerIdOnly().selectVersion();
    }

    public CustomerContactRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CustomerContactRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectPhone().selectEmail().selectRelationship().selectPrivateCustomer().selectCorporateCustomer().selectVersion();
    }

    public CustomerContactRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectPhone().selectEmail().selectRelationship().selectPrivateCustomer().selectCorporateCustomer().selectVersion();
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
    public CustomerContactRequest<T> selectName(){
       selectProperty(CustomerContact.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectName(){
       unselectProperty(CustomerContact.NAME_PROPERTY);
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
    public CustomerContactRequest<T> selectRelationship(){
       selectProperty(CustomerContact.RELATIONSHIP_PROPERTY);
       return this;
    }

    /**
     * fill the relationship with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  relationship) to fetch relationship property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerContactRequest<T> unselectRelationship(){
       unselectProperty(CustomerContact.RELATIONSHIP_PROPERTY);
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



    public CustomerContactRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public CustomerContactRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public CustomerContactRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.NAME_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public CustomerContactRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public CustomerContactRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public CustomerContactRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public CustomerContactRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public CustomerContactRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public CustomerContactRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public CustomerContactRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public CustomerContactRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public CustomerContactRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
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



    public CustomerContactRequest<T> filterByRelationship(String... relationship){
      if (relationship == null || relationship.length == 0) {
        throw new IllegalArgumentException("filterByRelationship parameter relationship cannot be empty");
      }
      return appendSearchCriteria(createRelationshipCriteria(Operator.EQUAL, (Object[])relationship));
    }

    public CustomerContactRequest<T> withRelationship(Operator operator, Object... values){
       return appendSearchCriteria(createRelationshipCriteria(operator, values));
    }

    public CustomerContactRequest<T> withRelationshipIsUnknown(){
       return withRelationship(Operator.IS_NULL);
    }

    public CustomerContactRequest<T> withRelationshipIsKnown(){
       return withRelationship(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRelationshipCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerContact.RELATIONSHIP_PROPERTY, operator, values);
    }

    public CustomerContactRequest<T> withRelationshipGreaterThan(String relationship){
       return withRelationship(Operator.GREATER_THAN, relationship);
    }

    public CustomerContactRequest<T> withRelationshipGreaterThanOrEqualTo(String relationship){
       return withRelationship(Operator.GREATER_THAN_OR_EQUAL, relationship);
    }

    public CustomerContactRequest<T> withRelationshipLessThan(String relationship){
       return withRelationship(Operator.LESS_THAN, relationship);
    }

    public CustomerContactRequest<T> withRelationshipLessThanOrEqualTo(String relationship){
       return withRelationship(Operator.LESS_THAN_OR_EQUAL, relationship);
    }

    public CustomerContactRequest<T> withRelationshipBetween(String startOfRelationship, String endOfRelationship){
       return withRelationship(Operator.BETWEEN, startOfRelationship, endOfRelationship);
    }
    public CustomerContactRequest<T> withRelationshipStartingWith(String relationship){
       return withRelationship(Operator.BEGIN_WITH, relationship);
    }
    public CustomerContactRequest<T> withRelationshipContaining(String relationship){
       return withRelationship(Operator.CONTAIN, relationship);
    }

    public CustomerContactRequest<T> withRelationshipEndingWith(String relationship){
       return withRelationship(Operator.END_WITH, relationship);
    }

    public CustomerContactRequest<T> withRelationshipIs(String relationship){
       return withRelationship(Operator.EQUAL, relationship);
    }

    public CustomerContactRequest<T> withRelationshipSoundingLike(String relationship){
       return withRelationship(Operator.SOUNDS_LIKE, relationship);
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

    public CustomerContactRequest<T> groupByName(){
       groupBy(CustomerContact.NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByNameAs(String retName){
       groupBy(retName, CustomerContact.NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.NAME_PROPERTY, function);
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

    public CustomerContactRequest<T> groupByRelationship(){
       groupBy(CustomerContact.RELATIONSHIP_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByRelationshipAs(String retName){
       groupBy(retName, CustomerContact.RELATIONSHIP_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> groupByRelationshipWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerContact.RELATIONSHIP_PROPERTY, function);
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

    public CustomerContactRequest<T> orderByNameAscending(){
       addOrderByAscending(CustomerContact.NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByNameDescending(){
       addOrderByDescending(CustomerContact.NAME_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerContact.NAME_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerContact.NAME_PROPERTY);
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
    public CustomerContactRequest<T> orderByRelationshipAscending(){
       addOrderByAscending(CustomerContact.RELATIONSHIP_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByRelationshipDescending(){
       addOrderByDescending(CustomerContact.RELATIONSHIP_PROPERTY);
       return this;
    }
    public CustomerContactRequest<T> orderByRelationshipAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerContact.RELATIONSHIP_PROPERTY);
       return this;
    }

    public CustomerContactRequest<T> orderByRelationshipDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerContact.RELATIONSHIP_PROPERTY);
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