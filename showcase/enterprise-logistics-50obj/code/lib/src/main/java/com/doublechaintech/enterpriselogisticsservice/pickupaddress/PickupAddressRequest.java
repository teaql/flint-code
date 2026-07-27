package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class PickupAddressRequest<T extends PickupAddress> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PickupAddressRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PickupAddressRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PickupAddressRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PickupAddressRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PickupAddressRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PickupAddressRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PickupAddressRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PickupAddressRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PickupAddressRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PickupAddressRequest<T> matchingAnyOf(PickupAddressRequest pickupAddress){
        super.internalMatchAny(pickupAddress);
        return this;
    }

    public PickupAddressRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PickupAddressRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PickupAddressRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PickupAddressRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectAddressId().selectMovingOrderIdOnly().selectAddressLine1().selectAddressLine2().selectCity().selectState().selectZipCode().selectCountry().selectContactName().selectContactPhone().selectCreateTime().selectVersion();
    }

    public PickupAddressRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PickupAddressRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectAddressId().selectMovingOrder().selectAddressLine1().selectAddressLine2().selectCity().selectState().selectZipCode().selectCountry().selectContactName().selectContactPhone().selectCreateTime().selectVersion();
    }

    public PickupAddressRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectAddressId().selectMovingOrder().selectAddressLine1().selectAddressLine2().selectCity().selectState().selectZipCode().selectCountry().selectContactName().selectContactPhone().selectCreateTime().selectVersion();
    }


    public PickupAddressRequest<T> selectId(){
       selectProperty(PickupAddress.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectId(){
       unselectProperty(PickupAddress.ID_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectAddressId(){
       selectProperty(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }

    /**
     * fill the addressId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  addressId) to fetch addressId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectAddressId(){
       unselectProperty(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectMovingOrderIdOnly(){
       selectProperty(PickupAddress.MOVING_ORDER_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public PickupAddressRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(PickupAddress.MOVING_ORDER_PROPERTY);
       enhanceRelation(PickupAddress.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public PickupAddressRequest<T> unselectMovingOrder(){
       unselectProperty(PickupAddress.MOVING_ORDER_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectAddressLine1(){
       selectProperty(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    /**
     * fill the addressLine1 with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  addressLine1) to fetch addressLine1 property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectAddressLine1(){
       unselectProperty(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectAddressLine2(){
       selectProperty(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    /**
     * fill the addressLine2 with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  addressLine2) to fetch addressLine2 property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectAddressLine2(){
       unselectProperty(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectCity(){
       selectProperty(PickupAddress.CITY_PROPERTY);
       return this;
    }

    /**
     * fill the city with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  city) to fetch city property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectCity(){
       unselectProperty(PickupAddress.CITY_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectState(){
       selectProperty(PickupAddress.STATE_PROPERTY);
       return this;
    }

    /**
     * fill the state with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  state) to fetch state property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectState(){
       unselectProperty(PickupAddress.STATE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectZipCode(){
       selectProperty(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the zipCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  zipCode) to fetch zipCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectZipCode(){
       unselectProperty(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectCountry(){
       selectProperty(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }

    /**
     * fill the country with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  country) to fetch country property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectCountry(){
       unselectProperty(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectContactName(){
       selectProperty(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }

    /**
     * fill the contactName with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  contactName) to fetch contactName property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectContactName(){
       unselectProperty(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectContactPhone(){
       selectProperty(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the contactPhone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  contactPhone) to fetch contactPhone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectContactPhone(){
       unselectProperty(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectCreateTime(){
       selectProperty(PickupAddress.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectCreateTime(){
       unselectProperty(PickupAddress.CREATE_TIME_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectVersion(){
       selectProperty(PickupAddress.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectVersion(){
       unselectProperty(PickupAddress.VERSION_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.ID_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PickupAddressRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PickupAddressRequest<T> filterByAddressId(String... addressId){
      if (addressId == null || addressId.length == 0) {
        throw new IllegalArgumentException("filterByAddressId parameter addressId cannot be empty");
      }
      return appendSearchCriteria(createAddressIdCriteria(Operator.EQUAL, (Object[])addressId));
    }

    public PickupAddressRequest<T> withAddressId(Operator operator, Object... values){
       return appendSearchCriteria(createAddressIdCriteria(operator, values));
    }

    public PickupAddressRequest<T> withAddressIdIsUnknown(){
       return withAddressId(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withAddressIdIsKnown(){
       return withAddressId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.ADDRESS_ID_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withAddressIdGreaterThan(String addressId){
       return withAddressId(Operator.GREATER_THAN, addressId);
    }

    public PickupAddressRequest<T> withAddressIdGreaterThanOrEqualTo(String addressId){
       return withAddressId(Operator.GREATER_THAN_OR_EQUAL, addressId);
    }

    public PickupAddressRequest<T> withAddressIdLessThan(String addressId){
       return withAddressId(Operator.LESS_THAN, addressId);
    }

    public PickupAddressRequest<T> withAddressIdLessThanOrEqualTo(String addressId){
       return withAddressId(Operator.LESS_THAN_OR_EQUAL, addressId);
    }

    public PickupAddressRequest<T> withAddressIdBetween(String startOfAddressId, String endOfAddressId){
       return withAddressId(Operator.BETWEEN, startOfAddressId, endOfAddressId);
    }
    public PickupAddressRequest<T> withAddressIdStartingWith(String addressId){
       return withAddressId(Operator.BEGIN_WITH, addressId);
    }
    public PickupAddressRequest<T> withAddressIdContaining(String addressId){
       return withAddressId(Operator.CONTAIN, addressId);
    }

    public PickupAddressRequest<T> withAddressIdEndingWith(String addressId){
       return withAddressId(Operator.END_WITH, addressId);
    }

    public PickupAddressRequest<T> withAddressIdIs(String addressId){
       return withAddressId(Operator.EQUAL, addressId);
    }

    public PickupAddressRequest<T> withAddressIdSoundingLike(String addressId){
       return withAddressId(Operator.SOUNDS_LIKE, addressId);
    }



    public PickupAddressRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public PickupAddressRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public PickupAddressRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.MOVING_ORDER_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public PickupAddressRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(PickupAddress.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public PickupAddressRequest<T> filterByAddressLine1(String... addressLine1){
      if (addressLine1 == null || addressLine1.length == 0) {
        throw new IllegalArgumentException("filterByAddressLine1 parameter addressLine1 cannot be empty");
      }
      return appendSearchCriteria(createAddressLine1Criteria(Operator.EQUAL, (Object[])addressLine1));
    }

    public PickupAddressRequest<T> withAddressLine1(Operator operator, Object... values){
       return appendSearchCriteria(createAddressLine1Criteria(operator, values));
    }

    public PickupAddressRequest<T> withAddressLine1IsUnknown(){
       return withAddressLine1(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withAddressLine1IsKnown(){
       return withAddressLine1(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressLine1Criteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.ADDRESS_LINE1_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withAddressLine1GreaterThan(String addressLine1){
       return withAddressLine1(Operator.GREATER_THAN, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1GreaterThanOrEqualTo(String addressLine1){
       return withAddressLine1(Operator.GREATER_THAN_OR_EQUAL, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1LessThan(String addressLine1){
       return withAddressLine1(Operator.LESS_THAN, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1LessThanOrEqualTo(String addressLine1){
       return withAddressLine1(Operator.LESS_THAN_OR_EQUAL, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1Between(String startOfAddressLine1, String endOfAddressLine1){
       return withAddressLine1(Operator.BETWEEN, startOfAddressLine1, endOfAddressLine1);
    }
    public PickupAddressRequest<T> withAddressLine1StartingWith(String addressLine1){
       return withAddressLine1(Operator.BEGIN_WITH, addressLine1);
    }
    public PickupAddressRequest<T> withAddressLine1Containing(String addressLine1){
       return withAddressLine1(Operator.CONTAIN, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1EndingWith(String addressLine1){
       return withAddressLine1(Operator.END_WITH, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1Is(String addressLine1){
       return withAddressLine1(Operator.EQUAL, addressLine1);
    }

    public PickupAddressRequest<T> withAddressLine1SoundingLike(String addressLine1){
       return withAddressLine1(Operator.SOUNDS_LIKE, addressLine1);
    }



    public PickupAddressRequest<T> filterByAddressLine2(String... addressLine2){
      if (addressLine2 == null || addressLine2.length == 0) {
        throw new IllegalArgumentException("filterByAddressLine2 parameter addressLine2 cannot be empty");
      }
      return appendSearchCriteria(createAddressLine2Criteria(Operator.EQUAL, (Object[])addressLine2));
    }

    public PickupAddressRequest<T> withAddressLine2(Operator operator, Object... values){
       return appendSearchCriteria(createAddressLine2Criteria(operator, values));
    }

    public PickupAddressRequest<T> withAddressLine2IsUnknown(){
       return withAddressLine2(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withAddressLine2IsKnown(){
       return withAddressLine2(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressLine2Criteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.ADDRESS_LINE2_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withAddressLine2GreaterThan(String addressLine2){
       return withAddressLine2(Operator.GREATER_THAN, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2GreaterThanOrEqualTo(String addressLine2){
       return withAddressLine2(Operator.GREATER_THAN_OR_EQUAL, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2LessThan(String addressLine2){
       return withAddressLine2(Operator.LESS_THAN, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2LessThanOrEqualTo(String addressLine2){
       return withAddressLine2(Operator.LESS_THAN_OR_EQUAL, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2Between(String startOfAddressLine2, String endOfAddressLine2){
       return withAddressLine2(Operator.BETWEEN, startOfAddressLine2, endOfAddressLine2);
    }
    public PickupAddressRequest<T> withAddressLine2StartingWith(String addressLine2){
       return withAddressLine2(Operator.BEGIN_WITH, addressLine2);
    }
    public PickupAddressRequest<T> withAddressLine2Containing(String addressLine2){
       return withAddressLine2(Operator.CONTAIN, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2EndingWith(String addressLine2){
       return withAddressLine2(Operator.END_WITH, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2Is(String addressLine2){
       return withAddressLine2(Operator.EQUAL, addressLine2);
    }

    public PickupAddressRequest<T> withAddressLine2SoundingLike(String addressLine2){
       return withAddressLine2(Operator.SOUNDS_LIKE, addressLine2);
    }



    public PickupAddressRequest<T> filterByCity(String... city){
      if (city == null || city.length == 0) {
        throw new IllegalArgumentException("filterByCity parameter city cannot be empty");
      }
      return appendSearchCriteria(createCityCriteria(Operator.EQUAL, (Object[])city));
    }

    public PickupAddressRequest<T> withCity(Operator operator, Object... values){
       return appendSearchCriteria(createCityCriteria(operator, values));
    }

    public PickupAddressRequest<T> withCityIsUnknown(){
       return withCity(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withCityIsKnown(){
       return withCity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.CITY_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withCityGreaterThan(String city){
       return withCity(Operator.GREATER_THAN, city);
    }

    public PickupAddressRequest<T> withCityGreaterThanOrEqualTo(String city){
       return withCity(Operator.GREATER_THAN_OR_EQUAL, city);
    }

    public PickupAddressRequest<T> withCityLessThan(String city){
       return withCity(Operator.LESS_THAN, city);
    }

    public PickupAddressRequest<T> withCityLessThanOrEqualTo(String city){
       return withCity(Operator.LESS_THAN_OR_EQUAL, city);
    }

    public PickupAddressRequest<T> withCityBetween(String startOfCity, String endOfCity){
       return withCity(Operator.BETWEEN, startOfCity, endOfCity);
    }
    public PickupAddressRequest<T> withCityStartingWith(String city){
       return withCity(Operator.BEGIN_WITH, city);
    }
    public PickupAddressRequest<T> withCityContaining(String city){
       return withCity(Operator.CONTAIN, city);
    }

    public PickupAddressRequest<T> withCityEndingWith(String city){
       return withCity(Operator.END_WITH, city);
    }

    public PickupAddressRequest<T> withCityIs(String city){
       return withCity(Operator.EQUAL, city);
    }

    public PickupAddressRequest<T> withCitySoundingLike(String city){
       return withCity(Operator.SOUNDS_LIKE, city);
    }



    public PickupAddressRequest<T> filterByState(String... state){
      if (state == null || state.length == 0) {
        throw new IllegalArgumentException("filterByState parameter state cannot be empty");
      }
      return appendSearchCriteria(createStateCriteria(Operator.EQUAL, (Object[])state));
    }

    public PickupAddressRequest<T> withState(Operator operator, Object... values){
       return appendSearchCriteria(createStateCriteria(operator, values));
    }

    public PickupAddressRequest<T> withStateIsUnknown(){
       return withState(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withStateIsKnown(){
       return withState(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.STATE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withStateGreaterThan(String state){
       return withState(Operator.GREATER_THAN, state);
    }

    public PickupAddressRequest<T> withStateGreaterThanOrEqualTo(String state){
       return withState(Operator.GREATER_THAN_OR_EQUAL, state);
    }

    public PickupAddressRequest<T> withStateLessThan(String state){
       return withState(Operator.LESS_THAN, state);
    }

    public PickupAddressRequest<T> withStateLessThanOrEqualTo(String state){
       return withState(Operator.LESS_THAN_OR_EQUAL, state);
    }

    public PickupAddressRequest<T> withStateBetween(String startOfState, String endOfState){
       return withState(Operator.BETWEEN, startOfState, endOfState);
    }
    public PickupAddressRequest<T> withStateStartingWith(String state){
       return withState(Operator.BEGIN_WITH, state);
    }
    public PickupAddressRequest<T> withStateContaining(String state){
       return withState(Operator.CONTAIN, state);
    }

    public PickupAddressRequest<T> withStateEndingWith(String state){
       return withState(Operator.END_WITH, state);
    }

    public PickupAddressRequest<T> withStateIs(String state){
       return withState(Operator.EQUAL, state);
    }

    public PickupAddressRequest<T> withStateSoundingLike(String state){
       return withState(Operator.SOUNDS_LIKE, state);
    }



    public PickupAddressRequest<T> filterByZipCode(String... zipCode){
      if (zipCode == null || zipCode.length == 0) {
        throw new IllegalArgumentException("filterByZipCode parameter zipCode cannot be empty");
      }
      return appendSearchCriteria(createZipCodeCriteria(Operator.EQUAL, (Object[])zipCode));
    }

    public PickupAddressRequest<T> withZipCode(Operator operator, Object... values){
       return appendSearchCriteria(createZipCodeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withZipCodeIsUnknown(){
       return withZipCode(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withZipCodeIsKnown(){
       return withZipCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createZipCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.ZIP_CODE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withZipCodeGreaterThan(String zipCode){
       return withZipCode(Operator.GREATER_THAN, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeGreaterThanOrEqualTo(String zipCode){
       return withZipCode(Operator.GREATER_THAN_OR_EQUAL, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeLessThan(String zipCode){
       return withZipCode(Operator.LESS_THAN, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeLessThanOrEqualTo(String zipCode){
       return withZipCode(Operator.LESS_THAN_OR_EQUAL, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeBetween(String startOfZipCode, String endOfZipCode){
       return withZipCode(Operator.BETWEEN, startOfZipCode, endOfZipCode);
    }
    public PickupAddressRequest<T> withZipCodeStartingWith(String zipCode){
       return withZipCode(Operator.BEGIN_WITH, zipCode);
    }
    public PickupAddressRequest<T> withZipCodeContaining(String zipCode){
       return withZipCode(Operator.CONTAIN, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeEndingWith(String zipCode){
       return withZipCode(Operator.END_WITH, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeIs(String zipCode){
       return withZipCode(Operator.EQUAL, zipCode);
    }

    public PickupAddressRequest<T> withZipCodeSoundingLike(String zipCode){
       return withZipCode(Operator.SOUNDS_LIKE, zipCode);
    }



    public PickupAddressRequest<T> filterByCountry(String... country){
      if (country == null || country.length == 0) {
        throw new IllegalArgumentException("filterByCountry parameter country cannot be empty");
      }
      return appendSearchCriteria(createCountryCriteria(Operator.EQUAL, (Object[])country));
    }

    public PickupAddressRequest<T> withCountry(Operator operator, Object... values){
       return appendSearchCriteria(createCountryCriteria(operator, values));
    }

    public PickupAddressRequest<T> withCountryIsUnknown(){
       return withCountry(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withCountryIsKnown(){
       return withCountry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCountryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.COUNTRY_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withCountryGreaterThan(String country){
       return withCountry(Operator.GREATER_THAN, country);
    }

    public PickupAddressRequest<T> withCountryGreaterThanOrEqualTo(String country){
       return withCountry(Operator.GREATER_THAN_OR_EQUAL, country);
    }

    public PickupAddressRequest<T> withCountryLessThan(String country){
       return withCountry(Operator.LESS_THAN, country);
    }

    public PickupAddressRequest<T> withCountryLessThanOrEqualTo(String country){
       return withCountry(Operator.LESS_THAN_OR_EQUAL, country);
    }

    public PickupAddressRequest<T> withCountryBetween(String startOfCountry, String endOfCountry){
       return withCountry(Operator.BETWEEN, startOfCountry, endOfCountry);
    }
    public PickupAddressRequest<T> withCountryStartingWith(String country){
       return withCountry(Operator.BEGIN_WITH, country);
    }
    public PickupAddressRequest<T> withCountryContaining(String country){
       return withCountry(Operator.CONTAIN, country);
    }

    public PickupAddressRequest<T> withCountryEndingWith(String country){
       return withCountry(Operator.END_WITH, country);
    }

    public PickupAddressRequest<T> withCountryIs(String country){
       return withCountry(Operator.EQUAL, country);
    }

    public PickupAddressRequest<T> withCountrySoundingLike(String country){
       return withCountry(Operator.SOUNDS_LIKE, country);
    }



    public PickupAddressRequest<T> filterByContactName(String... contactName){
      if (contactName == null || contactName.length == 0) {
        throw new IllegalArgumentException("filterByContactName parameter contactName cannot be empty");
      }
      return appendSearchCriteria(createContactNameCriteria(Operator.EQUAL, (Object[])contactName));
    }

    public PickupAddressRequest<T> withContactName(Operator operator, Object... values){
       return appendSearchCriteria(createContactNameCriteria(operator, values));
    }

    public PickupAddressRequest<T> withContactNameIsUnknown(){
       return withContactName(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withContactNameIsKnown(){
       return withContactName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContactNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.CONTACT_NAME_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withContactNameGreaterThan(String contactName){
       return withContactName(Operator.GREATER_THAN, contactName);
    }

    public PickupAddressRequest<T> withContactNameGreaterThanOrEqualTo(String contactName){
       return withContactName(Operator.GREATER_THAN_OR_EQUAL, contactName);
    }

    public PickupAddressRequest<T> withContactNameLessThan(String contactName){
       return withContactName(Operator.LESS_THAN, contactName);
    }

    public PickupAddressRequest<T> withContactNameLessThanOrEqualTo(String contactName){
       return withContactName(Operator.LESS_THAN_OR_EQUAL, contactName);
    }

    public PickupAddressRequest<T> withContactNameBetween(String startOfContactName, String endOfContactName){
       return withContactName(Operator.BETWEEN, startOfContactName, endOfContactName);
    }
    public PickupAddressRequest<T> withContactNameStartingWith(String contactName){
       return withContactName(Operator.BEGIN_WITH, contactName);
    }
    public PickupAddressRequest<T> withContactNameContaining(String contactName){
       return withContactName(Operator.CONTAIN, contactName);
    }

    public PickupAddressRequest<T> withContactNameEndingWith(String contactName){
       return withContactName(Operator.END_WITH, contactName);
    }

    public PickupAddressRequest<T> withContactNameIs(String contactName){
       return withContactName(Operator.EQUAL, contactName);
    }

    public PickupAddressRequest<T> withContactNameSoundingLike(String contactName){
       return withContactName(Operator.SOUNDS_LIKE, contactName);
    }



    public PickupAddressRequest<T> filterByContactPhone(String... contactPhone){
      if (contactPhone == null || contactPhone.length == 0) {
        throw new IllegalArgumentException("filterByContactPhone parameter contactPhone cannot be empty");
      }
      return appendSearchCriteria(createContactPhoneCriteria(Operator.EQUAL, (Object[])contactPhone));
    }

    public PickupAddressRequest<T> withContactPhone(Operator operator, Object... values){
       return appendSearchCriteria(createContactPhoneCriteria(operator, values));
    }

    public PickupAddressRequest<T> withContactPhoneIsUnknown(){
       return withContactPhone(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withContactPhoneIsKnown(){
       return withContactPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createContactPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.CONTACT_PHONE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withContactPhoneGreaterThan(String contactPhone){
       return withContactPhone(Operator.GREATER_THAN, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneGreaterThanOrEqualTo(String contactPhone){
       return withContactPhone(Operator.GREATER_THAN_OR_EQUAL, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneLessThan(String contactPhone){
       return withContactPhone(Operator.LESS_THAN, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneLessThanOrEqualTo(String contactPhone){
       return withContactPhone(Operator.LESS_THAN_OR_EQUAL, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneBetween(String startOfContactPhone, String endOfContactPhone){
       return withContactPhone(Operator.BETWEEN, startOfContactPhone, endOfContactPhone);
    }
    public PickupAddressRequest<T> withContactPhoneStartingWith(String contactPhone){
       return withContactPhone(Operator.BEGIN_WITH, contactPhone);
    }
    public PickupAddressRequest<T> withContactPhoneContaining(String contactPhone){
       return withContactPhone(Operator.CONTAIN, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneEndingWith(String contactPhone){
       return withContactPhone(Operator.END_WITH, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneIs(String contactPhone){
       return withContactPhone(Operator.EQUAL, contactPhone);
    }

    public PickupAddressRequest<T> withContactPhoneSoundingLike(String contactPhone){
       return withContactPhone(Operator.SOUNDS_LIKE, contactPhone);
    }



    public PickupAddressRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public PickupAddressRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.CREATE_TIME_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public PickupAddressRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public PickupAddressRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public PickupAddressRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PickupAddressRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PickupAddressRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.VERSION_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PickupAddressRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PickupAddressRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PickupAddressRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PickupAddressRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public PickupAddressRequest<T> count(){
        super.count();
        return this;
    }
    public PickupAddressRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PickupAddressRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(PickupAddress.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }












    public PickupAddressRequest<T> groupById(){
       groupBy(PickupAddress.ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByIdAs(String retName){
       groupBy(retName, PickupAddress.ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.ID_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressId(){
       groupBy(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressIdAs(String retName){
       groupBy(retName, PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.ADDRESS_ID_PROPERTY, function);
       return this;
    }
    public PickupAddressRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(PickupAddress.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public PickupAddressRequest<T> groupByMovingOrder(){
       groupBy(PickupAddress.MOVING_ORDER_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, PickupAddress.MOVING_ORDER_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.MOVING_ORDER_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressLine1(){
       groupBy(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressLine1As(String retName){
       groupBy(retName, PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressLine1WithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.ADDRESS_LINE1_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressLine2(){
       groupBy(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressLine2As(String retName){
       groupBy(retName, PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByAddressLine2WithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.ADDRESS_LINE2_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByCity(){
       groupBy(PickupAddress.CITY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCityAs(String retName){
       groupBy(retName, PickupAddress.CITY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCityWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.CITY_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByState(){
       groupBy(PickupAddress.STATE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByStateAs(String retName){
       groupBy(retName, PickupAddress.STATE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByStateWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.STATE_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByZipCode(){
       groupBy(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByZipCodeAs(String retName){
       groupBy(retName, PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByZipCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.ZIP_CODE_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByCountry(){
       groupBy(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCountryAs(String retName){
       groupBy(retName, PickupAddress.COUNTRY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCountryWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.COUNTRY_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByContactName(){
       groupBy(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByContactNameAs(String retName){
       groupBy(retName, PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByContactNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.CONTACT_NAME_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByContactPhone(){
       groupBy(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByContactPhoneAs(String retName){
       groupBy(retName, PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByContactPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.CONTACT_PHONE_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByCreateTime(){
       groupBy(PickupAddress.CREATE_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, PickupAddress.CREATE_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByVersion(){
       groupBy(PickupAddress.VERSION_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByVersionAs(String retName){
       groupBy(retName, PickupAddress.VERSION_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.VERSION_PROPERTY, function);
       return this;
    }



    public PickupAddressRequest<T> orderByIdAscending(){
       addOrderByAscending(PickupAddress.ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByIdDescending(){
       addOrderByDescending(PickupAddress.ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressIdAscending(){
       addOrderByAscending(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressIdDescending(){
       addOrderByDescending(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByAddressIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.ADDRESS_ID_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(PickupAddress.MOVING_ORDER_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(PickupAddress.MOVING_ORDER_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressLine1Ascending(){
       addOrderByAscending(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressLine1Descending(){
       addOrderByDescending(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByAddressLine1AscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressLine1DescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.ADDRESS_LINE1_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByAddressLine2Ascending(){
       addOrderByAscending(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressLine2Descending(){
       addOrderByDescending(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByAddressLine2AscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByAddressLine2DescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.ADDRESS_LINE2_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByCityAscending(){
       addOrderByAscending(PickupAddress.CITY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByCityDescending(){
       addOrderByDescending(PickupAddress.CITY_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByCityAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.CITY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByCityDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.CITY_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByStateAscending(){
       addOrderByAscending(PickupAddress.STATE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByStateDescending(){
       addOrderByDescending(PickupAddress.STATE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByStateAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.STATE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByStateDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.STATE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByZipCodeAscending(){
       addOrderByAscending(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByZipCodeDescending(){
       addOrderByDescending(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByZipCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByZipCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.ZIP_CODE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByCountryAscending(){
       addOrderByAscending(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByCountryDescending(){
       addOrderByDescending(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByCountryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByCountryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.COUNTRY_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByContactNameAscending(){
       addOrderByAscending(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByContactNameDescending(){
       addOrderByDescending(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByContactNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByContactNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.CONTACT_NAME_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByContactPhoneAscending(){
       addOrderByAscending(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByContactPhoneDescending(){
       addOrderByDescending(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByContactPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByContactPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.CONTACT_PHONE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(PickupAddress.CREATE_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(PickupAddress.CREATE_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByVersionAscending(){
       addOrderByAscending(PickupAddress.VERSION_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByVersionDescending(){
       addOrderByDescending(PickupAddress.VERSION_PROPERTY);
       return this;
    }


    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }












   public PickupAddressRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public PickupAddressRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, PickupAddress.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public PickupAddressRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PickupAddressRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PickupAddressRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PickupAddressRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PickupAddressRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}