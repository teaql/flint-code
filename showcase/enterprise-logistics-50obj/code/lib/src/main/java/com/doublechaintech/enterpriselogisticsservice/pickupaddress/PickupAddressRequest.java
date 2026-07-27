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
import java.math.BigDecimal;
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
        return selectId().selectAddressLine1().selectAddressLine2().selectCity().selectStateProvince().selectPostalCode().selectCountry().selectLatitude().selectLongitude().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public PickupAddressRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PickupAddressRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectAddressLine1().selectAddressLine2().selectCity().selectStateProvince().selectPostalCode().selectCountry().selectLatitude().selectLongitude().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public PickupAddressRequest<T> selectChildren(){
        super.selectAny();
        selectMovingOrderListAsPickupAddress().selectMovingOrderListAsDeliveryAddress();
        return selectId().selectAddressLine1().selectAddressLine2().selectCity().selectStateProvince().selectPostalCode().selectCountry().selectLatitude().selectLongitude().selectCreatedTime().selectUpdatedTime().selectVersion();
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
    public PickupAddressRequest<T> selectStateProvince(){
       selectProperty(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }

    /**
     * fill the stateProvince with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  stateProvince) to fetch stateProvince property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectStateProvince(){
       unselectProperty(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectPostalCode(){
       selectProperty(PickupAddress.POSTAL_CODE_PROPERTY);
       return this;
    }

    /**
     * fill the postalCode with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  postalCode) to fetch postalCode property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectPostalCode(){
       unselectProperty(PickupAddress.POSTAL_CODE_PROPERTY);
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
    public PickupAddressRequest<T> selectLatitude(){
       selectProperty(PickupAddress.LATITUDE_PROPERTY);
       return this;
    }

    /**
     * fill the latitude with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  latitude) to fetch latitude property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the latitude with customized aggrFunction, TEAQL uses ({aggrFunction}(latitude) AS latitude to fetch latitude property.
     * @param aggrFunction  aggrFunction
     */
    public PickupAddressRequest<T> selectLatitude(AggrFunction aggrFunction){
       selectProperty(PickupAddress.LATITUDE_PROPERTY, aggrFunction);
       return this;
    }


    public PickupAddressRequest<T> unselectLatitude(){
       unselectProperty(PickupAddress.LATITUDE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectLongitude(){
       selectProperty(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }

    /**
     * fill the longitude with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  longitude) to fetch longitude property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectLongitude(){
       unselectProperty(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectCreatedTime(){
       selectProperty(PickupAddress.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectCreatedTime(){
       unselectProperty(PickupAddress.CREATED_TIME_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> selectUpdatedTime(){
       selectProperty(PickupAddress.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PickupAddressRequest<T> unselectUpdatedTime(){
       unselectProperty(PickupAddress.UPDATED_TIME_PROPERTY);
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
    public PickupAddressRequest<T> selectMovingOrderListAsPickupAddress(){
       return selectMovingOrderListAsPickupAddressWith(Q.movingOrders().selectSelf());
    }

    public PickupAddressRequest<T> selectMovingOrderListAsPickupAddressWith(MovingOrderRequest movingOrderListAsPickupAddress){
       enhanceRelation(PickupAddress.MOVING_ORDER_LIST_AS_PICKUP_ADDRESS_PROPERTY, movingOrderListAsPickupAddress);
       return this;
    }
    public PickupAddressRequest<T> selectMovingOrderListAsDeliveryAddress(){
       return selectMovingOrderListAsDeliveryAddressWith(Q.movingOrders().selectSelf());
    }

    public PickupAddressRequest<T> selectMovingOrderListAsDeliveryAddressWith(MovingOrderRequest movingOrderListAsDeliveryAddress){
       enhanceRelation(PickupAddress.MOVING_ORDER_LIST_AS_DELIVERY_ADDRESS_PROPERTY, movingOrderListAsDeliveryAddress);
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



    public PickupAddressRequest<T> filterByStateProvince(String... stateProvince){
      if (stateProvince == null || stateProvince.length == 0) {
        throw new IllegalArgumentException("filterByStateProvince parameter stateProvince cannot be empty");
      }
      return appendSearchCriteria(createStateProvinceCriteria(Operator.EQUAL, (Object[])stateProvince));
    }

    public PickupAddressRequest<T> withStateProvince(Operator operator, Object... values){
       return appendSearchCriteria(createStateProvinceCriteria(operator, values));
    }

    public PickupAddressRequest<T> withStateProvinceIsUnknown(){
       return withStateProvince(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withStateProvinceIsKnown(){
       return withStateProvince(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStateProvinceCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.STATE_PROVINCE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withStateProvinceGreaterThan(String stateProvince){
       return withStateProvince(Operator.GREATER_THAN, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceGreaterThanOrEqualTo(String stateProvince){
       return withStateProvince(Operator.GREATER_THAN_OR_EQUAL, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceLessThan(String stateProvince){
       return withStateProvince(Operator.LESS_THAN, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceLessThanOrEqualTo(String stateProvince){
       return withStateProvince(Operator.LESS_THAN_OR_EQUAL, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceBetween(String startOfStateProvince, String endOfStateProvince){
       return withStateProvince(Operator.BETWEEN, startOfStateProvince, endOfStateProvince);
    }
    public PickupAddressRequest<T> withStateProvinceStartingWith(String stateProvince){
       return withStateProvince(Operator.BEGIN_WITH, stateProvince);
    }
    public PickupAddressRequest<T> withStateProvinceContaining(String stateProvince){
       return withStateProvince(Operator.CONTAIN, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceEndingWith(String stateProvince){
       return withStateProvince(Operator.END_WITH, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceIs(String stateProvince){
       return withStateProvince(Operator.EQUAL, stateProvince);
    }

    public PickupAddressRequest<T> withStateProvinceSoundingLike(String stateProvince){
       return withStateProvince(Operator.SOUNDS_LIKE, stateProvince);
    }



    public PickupAddressRequest<T> filterByPostalCode(String... postalCode){
      if (postalCode == null || postalCode.length == 0) {
        throw new IllegalArgumentException("filterByPostalCode parameter postalCode cannot be empty");
      }
      return appendSearchCriteria(createPostalCodeCriteria(Operator.EQUAL, (Object[])postalCode));
    }

    public PickupAddressRequest<T> withPostalCode(Operator operator, Object... values){
       return appendSearchCriteria(createPostalCodeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withPostalCodeIsUnknown(){
       return withPostalCode(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withPostalCodeIsKnown(){
       return withPostalCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPostalCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.POSTAL_CODE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withPostalCodeGreaterThan(String postalCode){
       return withPostalCode(Operator.GREATER_THAN, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeGreaterThanOrEqualTo(String postalCode){
       return withPostalCode(Operator.GREATER_THAN_OR_EQUAL, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeLessThan(String postalCode){
       return withPostalCode(Operator.LESS_THAN, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeLessThanOrEqualTo(String postalCode){
       return withPostalCode(Operator.LESS_THAN_OR_EQUAL, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeBetween(String startOfPostalCode, String endOfPostalCode){
       return withPostalCode(Operator.BETWEEN, startOfPostalCode, endOfPostalCode);
    }
    public PickupAddressRequest<T> withPostalCodeStartingWith(String postalCode){
       return withPostalCode(Operator.BEGIN_WITH, postalCode);
    }
    public PickupAddressRequest<T> withPostalCodeContaining(String postalCode){
       return withPostalCode(Operator.CONTAIN, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeEndingWith(String postalCode){
       return withPostalCode(Operator.END_WITH, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeIs(String postalCode){
       return withPostalCode(Operator.EQUAL, postalCode);
    }

    public PickupAddressRequest<T> withPostalCodeSoundingLike(String postalCode){
       return withPostalCode(Operator.SOUNDS_LIKE, postalCode);
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



    public PickupAddressRequest<T> filterByLatitude(BigDecimal... latitude){
      if (latitude == null || latitude.length == 0) {
        throw new IllegalArgumentException("filterByLatitude parameter latitude cannot be empty");
      }
      return appendSearchCriteria(createLatitudeCriteria(Operator.EQUAL, (Object[])latitude));
    }

    public PickupAddressRequest<T> withLatitude(Operator operator, Object... values){
       return appendSearchCriteria(createLatitudeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withLatitudeIsUnknown(){
       return withLatitude(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withLatitudeIsKnown(){
       return withLatitude(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLatitudeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.LATITUDE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withLatitudeGreaterThan(BigDecimal latitude){
       return withLatitude(Operator.GREATER_THAN, latitude);
    }

    public PickupAddressRequest<T> withLatitudeGreaterThanOrEqualTo(BigDecimal latitude){
       return withLatitude(Operator.GREATER_THAN_OR_EQUAL, latitude);
    }

    public PickupAddressRequest<T> withLatitudeLessThan(BigDecimal latitude){
       return withLatitude(Operator.LESS_THAN, latitude);
    }

    public PickupAddressRequest<T> withLatitudeLessThanOrEqualTo(BigDecimal latitude){
       return withLatitude(Operator.LESS_THAN_OR_EQUAL, latitude);
    }

    public PickupAddressRequest<T> withLatitudeBetween(BigDecimal startOfLatitude, BigDecimal endOfLatitude){
       return withLatitude(Operator.BETWEEN, startOfLatitude, endOfLatitude);
    }



    public PickupAddressRequest<T> filterByLongitude(String... longitude){
      if (longitude == null || longitude.length == 0) {
        throw new IllegalArgumentException("filterByLongitude parameter longitude cannot be empty");
      }
      return appendSearchCriteria(createLongitudeCriteria(Operator.EQUAL, (Object[])longitude));
    }

    public PickupAddressRequest<T> withLongitude(Operator operator, Object... values){
       return appendSearchCriteria(createLongitudeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withLongitudeIsUnknown(){
       return withLongitude(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withLongitudeIsKnown(){
       return withLongitude(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createLongitudeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.LONGITUDE_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withLongitudeGreaterThan(String longitude){
       return withLongitude(Operator.GREATER_THAN, longitude);
    }

    public PickupAddressRequest<T> withLongitudeGreaterThanOrEqualTo(String longitude){
       return withLongitude(Operator.GREATER_THAN_OR_EQUAL, longitude);
    }

    public PickupAddressRequest<T> withLongitudeLessThan(String longitude){
       return withLongitude(Operator.LESS_THAN, longitude);
    }

    public PickupAddressRequest<T> withLongitudeLessThanOrEqualTo(String longitude){
       return withLongitude(Operator.LESS_THAN_OR_EQUAL, longitude);
    }

    public PickupAddressRequest<T> withLongitudeBetween(String startOfLongitude, String endOfLongitude){
       return withLongitude(Operator.BETWEEN, startOfLongitude, endOfLongitude);
    }
    public PickupAddressRequest<T> withLongitudeStartingWith(String longitude){
       return withLongitude(Operator.BEGIN_WITH, longitude);
    }
    public PickupAddressRequest<T> withLongitudeContaining(String longitude){
       return withLongitude(Operator.CONTAIN, longitude);
    }

    public PickupAddressRequest<T> withLongitudeEndingWith(String longitude){
       return withLongitude(Operator.END_WITH, longitude);
    }

    public PickupAddressRequest<T> withLongitudeIs(String longitude){
       return withLongitude(Operator.EQUAL, longitude);
    }

    public PickupAddressRequest<T> withLongitudeSoundingLike(String longitude){
       return withLongitude(Operator.SOUNDS_LIKE, longitude);
    }



    public PickupAddressRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public PickupAddressRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.CREATED_TIME_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public PickupAddressRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public PickupAddressRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public PickupAddressRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public PickupAddressRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public PickupAddressRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public PickupAddressRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PickupAddress.UPDATED_TIME_PROPERTY, operator, values);
    }

    public PickupAddressRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public PickupAddressRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public PickupAddressRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
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

    public PickupAddressRequest<T> withMovingOrderListAsPickupAddressMatching(MovingOrderRequest movingOrderAsPickupAddressRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PickupAddress.ID_PROPERTY, movingOrderAsPickupAddressRequest, MovingOrder.PICKUP_ADDRESS_PROPERTY));
    }

    public PickupAddressRequest<T> withoutMovingOrderListAsPickupAddressMatching(MovingOrderRequest movingOrderAsPickupAddressRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PickupAddress.ID_PROPERTY, movingOrderAsPickupAddressRequest, MovingOrder.PICKUP_ADDRESS_PROPERTY)));
    }

    public PickupAddressRequest<T> haveMovingOrdersAsPickupAddress(){
        return withMovingOrderListAsPickupAddressMatching(Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> haveNoMovingOrdersAsPickupAddress(){
        return withoutMovingOrderListAsPickupAddressMatching(Q.movingOrders().unlimited());
    }
    public PickupAddressRequest<T> withMovingOrderListAsDeliveryAddressMatching(MovingOrderRequest movingOrderAsDeliveryAddressRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(PickupAddress.ID_PROPERTY, movingOrderAsDeliveryAddressRequest, MovingOrder.DELIVERY_ADDRESS_PROPERTY));
    }

    public PickupAddressRequest<T> withoutMovingOrderListAsDeliveryAddressMatching(MovingOrderRequest movingOrderAsDeliveryAddressRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(PickupAddress.ID_PROPERTY, movingOrderAsDeliveryAddressRequest, MovingOrder.DELIVERY_ADDRESS_PROPERTY)));
    }

    public PickupAddressRequest<T> haveMovingOrdersAsDeliveryAddress(){
        return withMovingOrderListAsDeliveryAddressMatching(Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> haveNoMovingOrdersAsDeliveryAddress(){
        return withoutMovingOrderListAsDeliveryAddressMatching(Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> count(){
        super.count();
        return this;
    }
    public PickupAddressRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PickupAddressRequest minLatitude(){
        return minLatitudeAs(prefix("minOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest minLatitudeAs(String retName){
        super.min(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest maxLatitude(){
        return maxLatitudeAs(prefix("maxOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest maxLatitudeAs(String retName){
        super.max(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest sumLatitude(){
        return sumLatitudeAs(prefix("sumOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest sumLatitudeAs(String retName){
        super.sum(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest avgLatitude(){
        return avgLatitudeAs(prefix("avgOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest avgLatitudeAs(String retName){
        super.avg(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest standardDeviationLatitude(){
        return standardDeviationLatitudeAs(prefix("standardDeviationOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest standardDeviationLatitudeAs(String retName){
        super.standardDeviation(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest squareRootOfPopulationStandardDeviationLatitude(){
        return squareRootOfPopulationStandardDeviationLatitudeAs(prefix("squareRootOfPopulationStandardDeviationOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest squareRootOfPopulationStandardDeviationLatitudeAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest sampleVarianceLatitude(){
        return sampleVarianceLatitudeAs(prefix("sampleVarianceOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest sampleVarianceLatitudeAs(String retName){
        super.sampleVariance(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest samplePopulationVarianceLatitude(){
        return samplePopulationVarianceLatitudeAs(prefix("samplePopulationVarianceOf",PickupAddress.LATITUDE_PROPERTY));
    }

    public PickupAddressRequest samplePopulationVarianceLatitudeAs(String retName){
        super.samplePopulationVariance(retName, PickupAddress.LATITUDE_PROPERTY);
        return this;
    }
    public PickupAddressRequest<T> groupByMovingOrdersAsPickupAddressWithDetails(MovingOrderRequest subRequest){
       aggregate(PickupAddress.MOVING_ORDER_LIST_AS_PICKUP_ADDRESS_PROPERTY, subRequest);
       return this;
    }
    public PickupAddressRequest<T> groupByMovingOrdersAsDeliveryAddressWithDetails(MovingOrderRequest subRequest){
       aggregate(PickupAddress.MOVING_ORDER_LIST_AS_DELIVERY_ADDRESS_PROPERTY, subRequest);
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

    public PickupAddressRequest<T> groupByStateProvince(){
       groupBy(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByStateProvinceAs(String retName){
       groupBy(retName, PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByStateProvinceWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.STATE_PROVINCE_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByPostalCode(){
       groupBy(PickupAddress.POSTAL_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByPostalCodeAs(String retName){
       groupBy(retName, PickupAddress.POSTAL_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByPostalCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.POSTAL_CODE_PROPERTY, function);
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

    public PickupAddressRequest<T> groupByLatitude(){
       groupBy(PickupAddress.LATITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByLatitudeAs(String retName){
       groupBy(retName, PickupAddress.LATITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByLatitudeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.LATITUDE_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByLongitude(){
       groupBy(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByLongitudeAs(String retName){
       groupBy(retName, PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByLongitudeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.LONGITUDE_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByCreatedTime(){
       groupBy(PickupAddress.CREATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, PickupAddress.CREATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public PickupAddressRequest<T> groupByUpdatedTime(){
       groupBy(PickupAddress.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, PickupAddress.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, PickupAddress.UPDATED_TIME_PROPERTY, function);
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
    public PickupAddressRequest<T> orderByStateProvinceAscending(){
       addOrderByAscending(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByStateProvinceDescending(){
       addOrderByDescending(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByStateProvinceAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByStateProvinceDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.STATE_PROVINCE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByPostalCodeAscending(){
       addOrderByAscending(PickupAddress.POSTAL_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByPostalCodeDescending(){
       addOrderByDescending(PickupAddress.POSTAL_CODE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByPostalCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.POSTAL_CODE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByPostalCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.POSTAL_CODE_PROPERTY);
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
    public PickupAddressRequest<T> orderByLatitudeAscending(){
       addOrderByAscending(PickupAddress.LATITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByLatitudeDescending(){
       addOrderByDescending(PickupAddress.LATITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByLongitudeAscending(){
       addOrderByAscending(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByLongitudeDescending(){
       addOrderByDescending(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByLongitudeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByLongitudeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PickupAddress.LONGITUDE_PROPERTY);
       return this;
    }
    public PickupAddressRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(PickupAddress.CREATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(PickupAddress.CREATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(PickupAddress.UPDATED_TIME_PROPERTY);
       return this;
    }

    public PickupAddressRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(PickupAddress.UPDATED_TIME_PROPERTY);
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


    public PickupAddressRequest<T> statsFromMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
       return statsFromMovingOrdersAsPickupAddressAs(name, subRequest, false);
    }

    public PickupAddressRequest<T> statsFromMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PickupAddressRequest<T> statsFromMovingOrdersAsPickupAddress(MovingOrderRequest subRequest){
       return statsFromMovingOrdersAsPickupAddressAs(REFINEMENTS, subRequest);
    }
    public PickupAddressRequest<T> statsFromMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
       return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest, false);
    }

    public PickupAddressRequest<T> statsFromMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public PickupAddressRequest<T> statsFromMovingOrdersAsDeliveryAddress(MovingOrderRequest subRequest){
       return statsFromMovingOrdersAsDeliveryAddressAs(REFINEMENTS, subRequest);
    }
    public PickupAddressRequest<T> countMovingOrdersAsPickupAddress(){
        return countMovingOrdersAsPickupAddressAs("Count");
    }

    public PickupAddressRequest<T> countMovingOrdersAsPickupAddressAs(String name){
        return countMovingOrdersAsPickupAddressWith(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> countMovingOrdersAsPickupAddressWith(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.count(), true);
    }
    public PickupAddressRequest<T> countMovingOrdersAsDeliveryAddress(){
        return countMovingOrdersAsDeliveryAddressAs("Count");
    }

    public PickupAddressRequest<T> countMovingOrdersAsDeliveryAddressAs(String name){
        return countMovingOrdersAsDeliveryAddressWith(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> countMovingOrdersAsDeliveryAddressWith(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.count(), true);
    }
    public PickupAddressRequest<T> minTotalWeightOfMovingOrdersAsPickupAddress(){
        return minTotalWeightOfMovingOrdersAsPickupAddressAs("minTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> minTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return minTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.minTotalWeight(), true);
    }
    public PickupAddressRequest<T> maxTotalWeightOfMovingOrdersAsPickupAddress(){
        return maxTotalWeightOfMovingOrdersAsPickupAddressAs("maxTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> maxTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return maxTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.maxTotalWeight(), true);
    }
    public PickupAddressRequest<T> sumTotalWeightOfMovingOrdersAsPickupAddress(){
        return sumTotalWeightOfMovingOrdersAsPickupAddressAs("sumTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sumTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return sumTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sumTotalWeight(), true);
    }
    public PickupAddressRequest<T> avgTotalWeightOfMovingOrdersAsPickupAddress(){
        return avgTotalWeightOfMovingOrdersAsPickupAddressAs("avgTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> avgTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return avgTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.avgTotalWeight(), true);
    }
    public PickupAddressRequest<T> standardDeviationTotalWeightOfMovingOrdersAsPickupAddress(){
        return standardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs("stdDevTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> standardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return standardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.standardDeviationTotalWeight(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsPickupAddress(){
        return squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs("stdDevPopTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalWeight(), true);
    }
    public PickupAddressRequest<T> sampleVarianceTotalWeightOfMovingOrdersAsPickupAddress(){
        return sampleVarianceTotalWeightOfMovingOrdersAsPickupAddressAs("varSampTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sampleVarianceTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return sampleVarianceTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sampleVarianceTotalWeight(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAsPickupAddress(){
        return samplePopulationVarianceTotalWeightOfMovingOrdersAsPickupAddressAs("varPopTotalWeightOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAsPickupAddressAs(String name){
        return samplePopulationVarianceTotalWeightOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.samplePopulationVarianceTotalWeight(), true);
    }
    public PickupAddressRequest<T> minTotalVolumeOfMovingOrdersAsPickupAddress(){
        return minTotalVolumeOfMovingOrdersAsPickupAddressAs("minTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> minTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return minTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.minTotalVolume(), true);
    }
    public PickupAddressRequest<T> maxTotalVolumeOfMovingOrdersAsPickupAddress(){
        return maxTotalVolumeOfMovingOrdersAsPickupAddressAs("maxTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> maxTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return maxTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.maxTotalVolume(), true);
    }
    public PickupAddressRequest<T> sumTotalVolumeOfMovingOrdersAsPickupAddress(){
        return sumTotalVolumeOfMovingOrdersAsPickupAddressAs("sumTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sumTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return sumTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sumTotalVolume(), true);
    }
    public PickupAddressRequest<T> avgTotalVolumeOfMovingOrdersAsPickupAddress(){
        return avgTotalVolumeOfMovingOrdersAsPickupAddressAs("avgTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> avgTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return avgTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.avgTotalVolume(), true);
    }
    public PickupAddressRequest<T> standardDeviationTotalVolumeOfMovingOrdersAsPickupAddress(){
        return standardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs("stdDevTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> standardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return standardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.standardDeviationTotalVolume(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsPickupAddress(){
        return squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs("stdDevPopTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalVolume(), true);
    }
    public PickupAddressRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAsPickupAddress(){
        return sampleVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs("varSampTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return sampleVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sampleVarianceTotalVolume(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAsPickupAddress(){
        return samplePopulationVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs("varPopTotalVolumeOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs(String name){
        return samplePopulationVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.samplePopulationVarianceTotalVolume(), true);
    }
    public PickupAddressRequest<T> minEstimatedCostOfMovingOrdersAsPickupAddress(){
        return minEstimatedCostOfMovingOrdersAsPickupAddressAs("minEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> minEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return minEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.minEstimatedCost(), true);
    }
    public PickupAddressRequest<T> maxEstimatedCostOfMovingOrdersAsPickupAddress(){
        return maxEstimatedCostOfMovingOrdersAsPickupAddressAs("maxEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> maxEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return maxEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.maxEstimatedCost(), true);
    }
    public PickupAddressRequest<T> sumEstimatedCostOfMovingOrdersAsPickupAddress(){
        return sumEstimatedCostOfMovingOrdersAsPickupAddressAs("sumEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sumEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return sumEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sumEstimatedCost(), true);
    }
    public PickupAddressRequest<T> avgEstimatedCostOfMovingOrdersAsPickupAddress(){
        return avgEstimatedCostOfMovingOrdersAsPickupAddressAs("avgEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> avgEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return avgEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.avgEstimatedCost(), true);
    }
    public PickupAddressRequest<T> standardDeviationEstimatedCostOfMovingOrdersAsPickupAddress(){
        return standardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs("stdDevEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> standardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return standardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.standardDeviationEstimatedCost(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsPickupAddress(){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs("stdDevPopEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationEstimatedCost(), true);
    }
    public PickupAddressRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAsPickupAddress(){
        return sampleVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs("varSampEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return sampleVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sampleVarianceEstimatedCost(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAsPickupAddress(){
        return samplePopulationVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs("varPopEstimatedCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs(String name){
        return samplePopulationVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.samplePopulationVarianceEstimatedCost(), true);
    }
    public PickupAddressRequest<T> minActualCostOfMovingOrdersAsPickupAddress(){
        return minActualCostOfMovingOrdersAsPickupAddressAs("minActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> minActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return minActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.minActualCost(), true);
    }
    public PickupAddressRequest<T> maxActualCostOfMovingOrdersAsPickupAddress(){
        return maxActualCostOfMovingOrdersAsPickupAddressAs("maxActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> maxActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return maxActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.maxActualCost(), true);
    }
    public PickupAddressRequest<T> sumActualCostOfMovingOrdersAsPickupAddress(){
        return sumActualCostOfMovingOrdersAsPickupAddressAs("sumActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sumActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return sumActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sumActualCost(), true);
    }
    public PickupAddressRequest<T> avgActualCostOfMovingOrdersAsPickupAddress(){
        return avgActualCostOfMovingOrdersAsPickupAddressAs("avgActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> avgActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return avgActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.avgActualCost(), true);
    }
    public PickupAddressRequest<T> standardDeviationActualCostOfMovingOrdersAsPickupAddress(){
        return standardDeviationActualCostOfMovingOrdersAsPickupAddressAs("stdDevActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> standardDeviationActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return standardDeviationActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.standardDeviationActualCost(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsPickupAddress(){
        return squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsPickupAddressAs("stdDevPopActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationActualCost(), true);
    }
    public PickupAddressRequest<T> sampleVarianceActualCostOfMovingOrdersAsPickupAddress(){
        return sampleVarianceActualCostOfMovingOrdersAsPickupAddressAs("varSampActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> sampleVarianceActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return sampleVarianceActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.sampleVarianceActualCost(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAsPickupAddress(){
        return samplePopulationVarianceActualCostOfMovingOrdersAsPickupAddressAs("varPopActualCostOfMovingOrdersAsPickupAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAsPickupAddressAs(String name){
        return samplePopulationVarianceActualCostOfMovingOrdersAsPickupAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAsPickupAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsPickupAddressAs(name, subRequest.samplePopulationVarianceActualCost(), true);
    }
    public PickupAddressRequest<T> minTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return minTotalWeightOfMovingOrdersAsDeliveryAddressAs("minTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> minTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return minTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.minTotalWeight(), true);
    }
    public PickupAddressRequest<T> maxTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return maxTotalWeightOfMovingOrdersAsDeliveryAddressAs("maxTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> maxTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return maxTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.maxTotalWeight(), true);
    }
    public PickupAddressRequest<T> sumTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return sumTotalWeightOfMovingOrdersAsDeliveryAddressAs("sumTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sumTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return sumTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sumTotalWeight(), true);
    }
    public PickupAddressRequest<T> avgTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return avgTotalWeightOfMovingOrdersAsDeliveryAddressAs("avgTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> avgTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return avgTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.avgTotalWeight(), true);
    }
    public PickupAddressRequest<T> standardDeviationTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return standardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs("stdDevTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> standardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return standardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.standardDeviationTotalWeight(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs("stdDevPopTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalWeight(), true);
    }
    public PickupAddressRequest<T> sampleVarianceTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return sampleVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs("varSampTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sampleVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return sampleVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sampleVarianceTotalWeight(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAsDeliveryAddress(){
        return samplePopulationVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs("varPopTotalWeightOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name){
        return samplePopulationVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalWeightOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.samplePopulationVarianceTotalWeight(), true);
    }
    public PickupAddressRequest<T> minTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return minTotalVolumeOfMovingOrdersAsDeliveryAddressAs("minTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> minTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return minTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.minTotalVolume(), true);
    }
    public PickupAddressRequest<T> maxTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return maxTotalVolumeOfMovingOrdersAsDeliveryAddressAs("maxTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> maxTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return maxTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.maxTotalVolume(), true);
    }
    public PickupAddressRequest<T> sumTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return sumTotalVolumeOfMovingOrdersAsDeliveryAddressAs("sumTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sumTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return sumTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sumTotalVolume(), true);
    }
    public PickupAddressRequest<T> avgTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return avgTotalVolumeOfMovingOrdersAsDeliveryAddressAs("avgTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> avgTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return avgTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.avgTotalVolume(), true);
    }
    public PickupAddressRequest<T> standardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return standardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs("stdDevTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> standardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return standardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.standardDeviationTotalVolume(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs("stdDevPopTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalVolume(), true);
    }
    public PickupAddressRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return sampleVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs("varSampTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return sampleVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sampleVarianceTotalVolume(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAsDeliveryAddress(){
        return samplePopulationVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs("varPopTotalVolumeOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name){
        return samplePopulationVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceTotalVolumeOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.samplePopulationVarianceTotalVolume(), true);
    }
    public PickupAddressRequest<T> minEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return minEstimatedCostOfMovingOrdersAsDeliveryAddressAs("minEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> minEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return minEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.minEstimatedCost(), true);
    }
    public PickupAddressRequest<T> maxEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return maxEstimatedCostOfMovingOrdersAsDeliveryAddressAs("maxEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> maxEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return maxEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.maxEstimatedCost(), true);
    }
    public PickupAddressRequest<T> sumEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return sumEstimatedCostOfMovingOrdersAsDeliveryAddressAs("sumEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sumEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return sumEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sumEstimatedCost(), true);
    }
    public PickupAddressRequest<T> avgEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return avgEstimatedCostOfMovingOrdersAsDeliveryAddressAs("avgEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> avgEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return avgEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.avgEstimatedCost(), true);
    }
    public PickupAddressRequest<T> standardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return standardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs("stdDevEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> standardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return standardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.standardDeviationEstimatedCost(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs("stdDevPopEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationEstimatedCost(), true);
    }
    public PickupAddressRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return sampleVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs("varSampEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return sampleVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sampleVarianceEstimatedCost(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAsDeliveryAddress(){
        return samplePopulationVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs("varPopEstimatedCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return samplePopulationVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceEstimatedCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.samplePopulationVarianceEstimatedCost(), true);
    }
    public PickupAddressRequest<T> minActualCostOfMovingOrdersAsDeliveryAddress(){
        return minActualCostOfMovingOrdersAsDeliveryAddressAs("minActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> minActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return minActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> minActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.minActualCost(), true);
    }
    public PickupAddressRequest<T> maxActualCostOfMovingOrdersAsDeliveryAddress(){
        return maxActualCostOfMovingOrdersAsDeliveryAddressAs("maxActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> maxActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return maxActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> maxActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.maxActualCost(), true);
    }
    public PickupAddressRequest<T> sumActualCostOfMovingOrdersAsDeliveryAddress(){
        return sumActualCostOfMovingOrdersAsDeliveryAddressAs("sumActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sumActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return sumActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sumActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sumActualCost(), true);
    }
    public PickupAddressRequest<T> avgActualCostOfMovingOrdersAsDeliveryAddress(){
        return avgActualCostOfMovingOrdersAsDeliveryAddressAs("avgActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> avgActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return avgActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> avgActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.avgActualCost(), true);
    }
    public PickupAddressRequest<T> standardDeviationActualCostOfMovingOrdersAsDeliveryAddress(){
        return standardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs("stdDevActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> standardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return standardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> standardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.standardDeviationActualCost(), true);
    }
    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsDeliveryAddress(){
        return squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs("stdDevPopActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> squareRootOfPopulationStandardDeviationActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.squareRootOfPopulationStandardDeviationActualCost(), true);
    }
    public PickupAddressRequest<T> sampleVarianceActualCostOfMovingOrdersAsDeliveryAddress(){
        return sampleVarianceActualCostOfMovingOrdersAsDeliveryAddressAs("varSampActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> sampleVarianceActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return sampleVarianceActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> sampleVarianceActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.sampleVarianceActualCost(), true);
    }
    public PickupAddressRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAsDeliveryAddress(){
        return samplePopulationVarianceActualCostOfMovingOrdersAsDeliveryAddressAs("varPopActualCostOfMovingOrdersAsDeliveryAddress");
    }

    public PickupAddressRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAsDeliveryAddressAs(String name){
        return samplePopulationVarianceActualCostOfMovingOrdersAsDeliveryAddressAs(name, Q.movingOrders().unlimited());
    }

    public PickupAddressRequest<T> samplePopulationVarianceActualCostOfMovingOrdersAsDeliveryAddressAs(String name, MovingOrderRequest subRequest){
        return statsFromMovingOrdersAsDeliveryAddressAs(name, subRequest.samplePopulationVarianceActualCost(), true);
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