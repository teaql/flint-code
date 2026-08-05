package com.doublechaintech.enterpriselogisticsservice.warehouse;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck;
import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckRequest;
import com.doublechaintech.enterpriselogisticsservice.pallet.Pallet;
import com.doublechaintech.enterpriselogisticsservice.pallet.PalletRequest;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerRequest;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeRequest;
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

public class WarehouseRequest<T extends Warehouse> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public WarehouseRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public WarehouseRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public WarehouseRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public WarehouseRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public WarehouseRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public WarehouseRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public WarehouseRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (WarehouseRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public WarehouseRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public WarehouseRequest<T> matchingAnyOf(WarehouseRequest warehouse){
        super.internalMatchAny(warehouse);
        return this;
    }

    public WarehouseRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public WarehouseRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public WarehouseRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public WarehouseRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectCode().selectAddress().selectCity().selectCountry().selectCapacity().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public WarehouseRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public WarehouseRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectAddress().selectCity().selectCountry().selectCapacity().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public WarehouseRequest<T> selectChildren(){
        super.selectAny();
        selectStorageContainerList().selectInventoryCheckList().selectPalletList().selectStorageFeeList();
        return selectId().selectName().selectCode().selectAddress().selectCity().selectCountry().selectCapacity().selectStatus().selectCreateTime().selectUpdateTime().selectVersion();
    }


    public WarehouseRequest<T> selectId(){
       selectProperty(Warehouse.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectId(){
       unselectProperty(Warehouse.ID_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectName(){
       selectProperty(Warehouse.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectName(){
       unselectProperty(Warehouse.NAME_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectCode(){
       selectProperty(Warehouse.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectCode(){
       unselectProperty(Warehouse.CODE_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectAddress(){
       selectProperty(Warehouse.ADDRESS_PROPERTY);
       return this;
    }

    /**
     * fill the address with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  address) to fetch address property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectAddress(){
       unselectProperty(Warehouse.ADDRESS_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectCity(){
       selectProperty(Warehouse.CITY_PROPERTY);
       return this;
    }

    /**
     * fill the city with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  city) to fetch city property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectCity(){
       unselectProperty(Warehouse.CITY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectCountry(){
       selectProperty(Warehouse.COUNTRY_PROPERTY);
       return this;
    }

    /**
     * fill the country with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  country) to fetch country property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectCountry(){
       unselectProperty(Warehouse.COUNTRY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectCapacity(){
       selectProperty(Warehouse.CAPACITY_PROPERTY);
       return this;
    }

    /**
     * fill the capacity with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  capacity) to fetch capacity property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the capacity with customized aggrFunction, TEAQL uses ({aggrFunction}(capacity) AS capacity to fetch capacity property.
     * @param aggrFunction  aggrFunction
     */
    public WarehouseRequest<T> selectCapacity(AggrFunction aggrFunction){
       selectProperty(Warehouse.CAPACITY_PROPERTY, aggrFunction);
       return this;
    }


    public WarehouseRequest<T> unselectCapacity(){
       unselectProperty(Warehouse.CAPACITY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectStatus(){
       selectProperty(Warehouse.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectStatus(){
       unselectProperty(Warehouse.STATUS_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectCreateTime(){
       selectProperty(Warehouse.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectCreateTime(){
       unselectProperty(Warehouse.CREATE_TIME_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectUpdateTime(){
       selectProperty(Warehouse.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectUpdateTime(){
       unselectProperty(Warehouse.UPDATE_TIME_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectVersion(){
       selectProperty(Warehouse.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public WarehouseRequest<T> unselectVersion(){
       unselectProperty(Warehouse.VERSION_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> selectStorageContainerList(){
       return selectStorageContainerListWith(Q.storageContainers().selectSelf());
    }

    public WarehouseRequest<T> selectStorageContainerListWith(StorageContainerRequest storageContainerList){
       enhanceRelation(Warehouse.STORAGE_CONTAINER_LIST_PROPERTY, storageContainerList);
       return this;
    }
    public WarehouseRequest<T> selectInventoryCheckList(){
       return selectInventoryCheckListWith(Q.inventoryChecks().selectSelf());
    }

    public WarehouseRequest<T> selectInventoryCheckListWith(InventoryCheckRequest inventoryCheckList){
       enhanceRelation(Warehouse.INVENTORY_CHECK_LIST_PROPERTY, inventoryCheckList);
       return this;
    }
    public WarehouseRequest<T> selectPalletList(){
       return selectPalletListWith(Q.pallets().selectSelf());
    }

    public WarehouseRequest<T> selectPalletListWith(PalletRequest palletList){
       enhanceRelation(Warehouse.PALLET_LIST_PROPERTY, palletList);
       return this;
    }
    public WarehouseRequest<T> selectStorageFeeList(){
       return selectStorageFeeListWith(Q.storageFees().selectSelf());
    }

    public WarehouseRequest<T> selectStorageFeeListWith(StorageFeeRequest storageFeeList){
       enhanceRelation(Warehouse.STORAGE_FEE_LIST_PROPERTY, storageFeeList);
       return this;
    }

    public WarehouseRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.ID_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public WarehouseRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public WarehouseRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public WarehouseRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public WarehouseRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.NAME_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public WarehouseRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public WarehouseRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public WarehouseRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public WarehouseRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public WarehouseRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public WarehouseRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public WarehouseRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public WarehouseRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public WarehouseRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public WarehouseRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public WarehouseRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public WarehouseRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.CODE_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public WarehouseRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public WarehouseRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public WarehouseRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public WarehouseRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public WarehouseRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public WarehouseRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public WarehouseRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public WarehouseRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public WarehouseRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public WarehouseRequest<T> filterByAddress(String... address){
      if (address == null || address.length == 0) {
        throw new IllegalArgumentException("filterByAddress parameter address cannot be empty");
      }
      return appendSearchCriteria(createAddressCriteria(Operator.EQUAL, (Object[])address));
    }

    public WarehouseRequest<T> withAddress(Operator operator, Object... values){
       return appendSearchCriteria(createAddressCriteria(operator, values));
    }

    public WarehouseRequest<T> withAddressIsUnknown(){
       return withAddress(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withAddressIsKnown(){
       return withAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.ADDRESS_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withAddressGreaterThan(String address){
       return withAddress(Operator.GREATER_THAN, address);
    }

    public WarehouseRequest<T> withAddressGreaterThanOrEqualTo(String address){
       return withAddress(Operator.GREATER_THAN_OR_EQUAL, address);
    }

    public WarehouseRequest<T> withAddressLessThan(String address){
       return withAddress(Operator.LESS_THAN, address);
    }

    public WarehouseRequest<T> withAddressLessThanOrEqualTo(String address){
       return withAddress(Operator.LESS_THAN_OR_EQUAL, address);
    }

    public WarehouseRequest<T> withAddressBetween(String startOfAddress, String endOfAddress){
       return withAddress(Operator.BETWEEN, startOfAddress, endOfAddress);
    }
    public WarehouseRequest<T> withAddressStartingWith(String address){
       return withAddress(Operator.BEGIN_WITH, address);
    }
    public WarehouseRequest<T> withAddressContaining(String address){
       return withAddress(Operator.CONTAIN, address);
    }

    public WarehouseRequest<T> withAddressEndingWith(String address){
       return withAddress(Operator.END_WITH, address);
    }

    public WarehouseRequest<T> withAddressIs(String address){
       return withAddress(Operator.EQUAL, address);
    }

    public WarehouseRequest<T> withAddressSoundingLike(String address){
       return withAddress(Operator.SOUNDS_LIKE, address);
    }



    public WarehouseRequest<T> filterByCity(String... city){
      if (city == null || city.length == 0) {
        throw new IllegalArgumentException("filterByCity parameter city cannot be empty");
      }
      return appendSearchCriteria(createCityCriteria(Operator.EQUAL, (Object[])city));
    }

    public WarehouseRequest<T> withCity(Operator operator, Object... values){
       return appendSearchCriteria(createCityCriteria(operator, values));
    }

    public WarehouseRequest<T> withCityIsUnknown(){
       return withCity(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withCityIsKnown(){
       return withCity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.CITY_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withCityGreaterThan(String city){
       return withCity(Operator.GREATER_THAN, city);
    }

    public WarehouseRequest<T> withCityGreaterThanOrEqualTo(String city){
       return withCity(Operator.GREATER_THAN_OR_EQUAL, city);
    }

    public WarehouseRequest<T> withCityLessThan(String city){
       return withCity(Operator.LESS_THAN, city);
    }

    public WarehouseRequest<T> withCityLessThanOrEqualTo(String city){
       return withCity(Operator.LESS_THAN_OR_EQUAL, city);
    }

    public WarehouseRequest<T> withCityBetween(String startOfCity, String endOfCity){
       return withCity(Operator.BETWEEN, startOfCity, endOfCity);
    }
    public WarehouseRequest<T> withCityStartingWith(String city){
       return withCity(Operator.BEGIN_WITH, city);
    }
    public WarehouseRequest<T> withCityContaining(String city){
       return withCity(Operator.CONTAIN, city);
    }

    public WarehouseRequest<T> withCityEndingWith(String city){
       return withCity(Operator.END_WITH, city);
    }

    public WarehouseRequest<T> withCityIs(String city){
       return withCity(Operator.EQUAL, city);
    }

    public WarehouseRequest<T> withCitySoundingLike(String city){
       return withCity(Operator.SOUNDS_LIKE, city);
    }



    public WarehouseRequest<T> filterByCountry(String... country){
      if (country == null || country.length == 0) {
        throw new IllegalArgumentException("filterByCountry parameter country cannot be empty");
      }
      return appendSearchCriteria(createCountryCriteria(Operator.EQUAL, (Object[])country));
    }

    public WarehouseRequest<T> withCountry(Operator operator, Object... values){
       return appendSearchCriteria(createCountryCriteria(operator, values));
    }

    public WarehouseRequest<T> withCountryIsUnknown(){
       return withCountry(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withCountryIsKnown(){
       return withCountry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCountryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.COUNTRY_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withCountryGreaterThan(String country){
       return withCountry(Operator.GREATER_THAN, country);
    }

    public WarehouseRequest<T> withCountryGreaterThanOrEqualTo(String country){
       return withCountry(Operator.GREATER_THAN_OR_EQUAL, country);
    }

    public WarehouseRequest<T> withCountryLessThan(String country){
       return withCountry(Operator.LESS_THAN, country);
    }

    public WarehouseRequest<T> withCountryLessThanOrEqualTo(String country){
       return withCountry(Operator.LESS_THAN_OR_EQUAL, country);
    }

    public WarehouseRequest<T> withCountryBetween(String startOfCountry, String endOfCountry){
       return withCountry(Operator.BETWEEN, startOfCountry, endOfCountry);
    }
    public WarehouseRequest<T> withCountryStartingWith(String country){
       return withCountry(Operator.BEGIN_WITH, country);
    }
    public WarehouseRequest<T> withCountryContaining(String country){
       return withCountry(Operator.CONTAIN, country);
    }

    public WarehouseRequest<T> withCountryEndingWith(String country){
       return withCountry(Operator.END_WITH, country);
    }

    public WarehouseRequest<T> withCountryIs(String country){
       return withCountry(Operator.EQUAL, country);
    }

    public WarehouseRequest<T> withCountrySoundingLike(String country){
       return withCountry(Operator.SOUNDS_LIKE, country);
    }



    public WarehouseRequest<T> filterByCapacity(BigDecimal... capacity){
      if (capacity == null || capacity.length == 0) {
        throw new IllegalArgumentException("filterByCapacity parameter capacity cannot be empty");
      }
      return appendSearchCriteria(createCapacityCriteria(Operator.EQUAL, (Object[])capacity));
    }

    public WarehouseRequest<T> withCapacity(Operator operator, Object... values){
       return appendSearchCriteria(createCapacityCriteria(operator, values));
    }

    public WarehouseRequest<T> withCapacityIsUnknown(){
       return withCapacity(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withCapacityIsKnown(){
       return withCapacity(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCapacityCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.CAPACITY_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withCapacityGreaterThan(BigDecimal capacity){
       return withCapacity(Operator.GREATER_THAN, capacity);
    }

    public WarehouseRequest<T> withCapacityGreaterThanOrEqualTo(BigDecimal capacity){
       return withCapacity(Operator.GREATER_THAN_OR_EQUAL, capacity);
    }

    public WarehouseRequest<T> withCapacityLessThan(BigDecimal capacity){
       return withCapacity(Operator.LESS_THAN, capacity);
    }

    public WarehouseRequest<T> withCapacityLessThanOrEqualTo(BigDecimal capacity){
       return withCapacity(Operator.LESS_THAN_OR_EQUAL, capacity);
    }

    public WarehouseRequest<T> withCapacityBetween(BigDecimal startOfCapacity, BigDecimal endOfCapacity){
       return withCapacity(Operator.BETWEEN, startOfCapacity, endOfCapacity);
    }



    public WarehouseRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public WarehouseRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public WarehouseRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.STATUS_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public WarehouseRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public WarehouseRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public WarehouseRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public WarehouseRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public WarehouseRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public WarehouseRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public WarehouseRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public WarehouseRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public WarehouseRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public WarehouseRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public WarehouseRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public WarehouseRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.CREATE_TIME_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public WarehouseRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public WarehouseRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public WarehouseRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public WarehouseRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public WarehouseRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public WarehouseRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public WarehouseRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public WarehouseRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public WarehouseRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public WarehouseRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public WarehouseRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public WarehouseRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.UPDATE_TIME_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public WarehouseRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public WarehouseRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }




    public WarehouseRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public WarehouseRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public WarehouseRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public WarehouseRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(Warehouse.VERSION_PROPERTY, operator, values);
    }

    public WarehouseRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public WarehouseRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public WarehouseRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public WarehouseRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public WarehouseRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public WarehouseRequest<T> withStorageContainerListMatching(StorageContainerRequest storageContainerRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, storageContainerRequest, StorageContainer.WAREHOUSE_PROPERTY));
    }

    public WarehouseRequest<T> withoutStorageContainerListMatching(StorageContainerRequest storageContainerRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, storageContainerRequest, StorageContainer.WAREHOUSE_PROPERTY)));
    }

    public WarehouseRequest<T> haveStorageContainers(){
        return withStorageContainerListMatching(Q.storageContainers().unlimited());
    }

    public WarehouseRequest<T> haveNoStorageContainers(){
        return withoutStorageContainerListMatching(Q.storageContainers().unlimited());
    }
    public WarehouseRequest<T> withInventoryCheckListMatching(InventoryCheckRequest inventoryCheckRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, inventoryCheckRequest, InventoryCheck.WAREHOUSE_PROPERTY));
    }

    public WarehouseRequest<T> withoutInventoryCheckListMatching(InventoryCheckRequest inventoryCheckRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, inventoryCheckRequest, InventoryCheck.WAREHOUSE_PROPERTY)));
    }

    public WarehouseRequest<T> haveInventoryChecks(){
        return withInventoryCheckListMatching(Q.inventoryChecks().unlimited());
    }

    public WarehouseRequest<T> haveNoInventoryChecks(){
        return withoutInventoryCheckListMatching(Q.inventoryChecks().unlimited());
    }
    public WarehouseRequest<T> withPalletListMatching(PalletRequest palletRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, palletRequest, Pallet.WAREHOUSE_PROPERTY));
    }

    public WarehouseRequest<T> withoutPalletListMatching(PalletRequest palletRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, palletRequest, Pallet.WAREHOUSE_PROPERTY)));
    }

    public WarehouseRequest<T> havePallets(){
        return withPalletListMatching(Q.pallets().unlimited());
    }

    public WarehouseRequest<T> haveNoPallets(){
        return withoutPalletListMatching(Q.pallets().unlimited());
    }
    public WarehouseRequest<T> withStorageFeeListMatching(StorageFeeRequest storageFeeRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, storageFeeRequest, StorageFee.WAREHOUSE_PROPERTY));
    }

    public WarehouseRequest<T> withoutStorageFeeListMatching(StorageFeeRequest storageFeeRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(Warehouse.ID_PROPERTY, storageFeeRequest, StorageFee.WAREHOUSE_PROPERTY)));
    }

    public WarehouseRequest<T> haveStorageFees(){
        return withStorageFeeListMatching(Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> haveNoStorageFees(){
        return withoutStorageFeeListMatching(Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> count(){
        super.count();
        return this;
    }
    public WarehouseRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public WarehouseRequest minCapacity(){
        return minCapacityAs(prefix("minOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest minCapacityAs(String retName){
        super.min(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest maxCapacity(){
        return maxCapacityAs(prefix("maxOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest maxCapacityAs(String retName){
        super.max(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest sumCapacity(){
        return sumCapacityAs(prefix("sumOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest sumCapacityAs(String retName){
        super.sum(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest avgCapacity(){
        return avgCapacityAs(prefix("avgOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest avgCapacityAs(String retName){
        super.avg(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest standardDeviationCapacity(){
        return standardDeviationCapacityAs(prefix("standardDeviationOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest standardDeviationCapacityAs(String retName){
        super.standardDeviation(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest squareRootOfPopulationStandardDeviationCapacity(){
        return squareRootOfPopulationStandardDeviationCapacityAs(prefix("squareRootOfPopulationStandardDeviationOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest squareRootOfPopulationStandardDeviationCapacityAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest sampleVarianceCapacity(){
        return sampleVarianceCapacityAs(prefix("sampleVarianceOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest sampleVarianceCapacityAs(String retName){
        super.sampleVariance(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest samplePopulationVarianceCapacity(){
        return samplePopulationVarianceCapacityAs(prefix("samplePopulationVarianceOf",Warehouse.CAPACITY_PROPERTY));
    }

    public WarehouseRequest samplePopulationVarianceCapacityAs(String retName){
        super.samplePopulationVariance(retName, Warehouse.CAPACITY_PROPERTY);
        return this;
    }
    public WarehouseRequest<T> groupByStorageContainersWithDetails(StorageContainerRequest subRequest){
       aggregate(Warehouse.STORAGE_CONTAINER_LIST_PROPERTY, subRequest);
       return this;
    }
    public WarehouseRequest<T> groupByInventoryChecksWithDetails(InventoryCheckRequest subRequest){
       aggregate(Warehouse.INVENTORY_CHECK_LIST_PROPERTY, subRequest);
       return this;
    }
    public WarehouseRequest<T> groupByPalletsWithDetails(PalletRequest subRequest){
       aggregate(Warehouse.PALLET_LIST_PROPERTY, subRequest);
       return this;
    }
    public WarehouseRequest<T> groupByStorageFeesWithDetails(StorageFeeRequest subRequest){
       aggregate(Warehouse.STORAGE_FEE_LIST_PROPERTY, subRequest);
       return this;
    }

    public WarehouseRequest<T> groupById(){
       groupBy(Warehouse.ID_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByIdAs(String retName){
       groupBy(retName, Warehouse.ID_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.ID_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByName(){
       groupBy(Warehouse.NAME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByNameAs(String retName){
       groupBy(retName, Warehouse.NAME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.NAME_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByCode(){
       groupBy(Warehouse.CODE_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCodeAs(String retName){
       groupBy(retName, Warehouse.CODE_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.CODE_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByAddress(){
       groupBy(Warehouse.ADDRESS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByAddressAs(String retName){
       groupBy(retName, Warehouse.ADDRESS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.ADDRESS_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByCity(){
       groupBy(Warehouse.CITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCityAs(String retName){
       groupBy(retName, Warehouse.CITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCityWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.CITY_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByCountry(){
       groupBy(Warehouse.COUNTRY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCountryAs(String retName){
       groupBy(retName, Warehouse.COUNTRY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCountryWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.COUNTRY_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByCapacity(){
       groupBy(Warehouse.CAPACITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCapacityAs(String retName){
       groupBy(retName, Warehouse.CAPACITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCapacityWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.CAPACITY_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByStatus(){
       groupBy(Warehouse.STATUS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByStatusAs(String retName){
       groupBy(retName, Warehouse.STATUS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.STATUS_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByCreateTime(){
       groupBy(Warehouse.CREATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, Warehouse.CREATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByUpdateTime(){
       groupBy(Warehouse.UPDATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, Warehouse.UPDATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.UPDATE_TIME_PROPERTY, function);
       return this;
    }

    public WarehouseRequest<T> groupByVersion(){
       groupBy(Warehouse.VERSION_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByVersionAs(String retName){
       groupBy(retName, Warehouse.VERSION_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, Warehouse.VERSION_PROPERTY, function);
       return this;
    }



    public WarehouseRequest<T> orderByIdAscending(){
       addOrderByAscending(Warehouse.ID_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByIdDescending(){
       addOrderByDescending(Warehouse.ID_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByNameAscending(){
       addOrderByAscending(Warehouse.NAME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByNameDescending(){
       addOrderByDescending(Warehouse.NAME_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Warehouse.NAME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Warehouse.NAME_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCodeAscending(){
       addOrderByAscending(Warehouse.CODE_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCodeDescending(){
       addOrderByDescending(Warehouse.CODE_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Warehouse.CODE_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Warehouse.CODE_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByAddressAscending(){
       addOrderByAscending(Warehouse.ADDRESS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByAddressDescending(){
       addOrderByDescending(Warehouse.ADDRESS_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByAddressAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Warehouse.ADDRESS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByAddressDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Warehouse.ADDRESS_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCityAscending(){
       addOrderByAscending(Warehouse.CITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCityDescending(){
       addOrderByDescending(Warehouse.CITY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCityAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Warehouse.CITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCityDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Warehouse.CITY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCountryAscending(){
       addOrderByAscending(Warehouse.COUNTRY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCountryDescending(){
       addOrderByDescending(Warehouse.COUNTRY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCountryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Warehouse.COUNTRY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCountryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Warehouse.COUNTRY_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCapacityAscending(){
       addOrderByAscending(Warehouse.CAPACITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCapacityDescending(){
       addOrderByDescending(Warehouse.CAPACITY_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByStatusAscending(){
       addOrderByAscending(Warehouse.STATUS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByStatusDescending(){
       addOrderByDescending(Warehouse.STATUS_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(Warehouse.STATUS_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(Warehouse.STATUS_PROPERTY);
       return this;
    }
    public WarehouseRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(Warehouse.CREATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(Warehouse.CREATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(Warehouse.UPDATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(Warehouse.UPDATE_TIME_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByVersionAscending(){
       addOrderByAscending(Warehouse.VERSION_PROPERTY);
       return this;
    }

    public WarehouseRequest<T> orderByVersionDescending(){
       addOrderByDescending(Warehouse.VERSION_PROPERTY);
       return this;
    }


    public WarehouseRequest<T> statsFromStorageContainersAs(String name, StorageContainerRequest subRequest){
       return statsFromStorageContainersAs(name, subRequest, false);
    }

    public WarehouseRequest<T> statsFromStorageContainersAs(String name, StorageContainerRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(StorageContainer.WAREHOUSE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public WarehouseRequest<T> statsFromStorageContainers(StorageContainerRequest subRequest){
       return statsFromStorageContainersAs(REFINEMENTS, subRequest);
    }
    public WarehouseRequest<T> statsFromInventoryChecksAs(String name, InventoryCheckRequest subRequest){
       return statsFromInventoryChecksAs(name, subRequest, false);
    }

    public WarehouseRequest<T> statsFromInventoryChecksAs(String name, InventoryCheckRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(InventoryCheck.WAREHOUSE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public WarehouseRequest<T> statsFromInventoryChecks(InventoryCheckRequest subRequest){
       return statsFromInventoryChecksAs(REFINEMENTS, subRequest);
    }
    public WarehouseRequest<T> statsFromPalletsAs(String name, PalletRequest subRequest){
       return statsFromPalletsAs(name, subRequest, false);
    }

    public WarehouseRequest<T> statsFromPalletsAs(String name, PalletRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(Pallet.WAREHOUSE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public WarehouseRequest<T> statsFromPallets(PalletRequest subRequest){
       return statsFromPalletsAs(REFINEMENTS, subRequest);
    }
    public WarehouseRequest<T> statsFromStorageFeesAs(String name, StorageFeeRequest subRequest){
       return statsFromStorageFeesAs(name, subRequest, false);
    }

    public WarehouseRequest<T> statsFromStorageFeesAs(String name, StorageFeeRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(StorageFee.WAREHOUSE_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public WarehouseRequest<T> statsFromStorageFees(StorageFeeRequest subRequest){
       return statsFromStorageFeesAs(REFINEMENTS, subRequest);
    }
    public WarehouseRequest<T> countStorageContainers(){
        return countStorageContainersAs("Count");
    }

    public WarehouseRequest<T> countStorageContainersAs(String name){
        return countStorageContainersWith(name, Q.storageContainers().unlimited());
    }

    public WarehouseRequest<T> countStorageContainersWith(String name, StorageContainerRequest subRequest){
        return statsFromStorageContainersAs(name, subRequest.count(), true);
    }
    public WarehouseRequest<T> countInventoryChecks(){
        return countInventoryChecksAs("Count");
    }

    public WarehouseRequest<T> countInventoryChecksAs(String name){
        return countInventoryChecksWith(name, Q.inventoryChecks().unlimited());
    }

    public WarehouseRequest<T> countInventoryChecksWith(String name, InventoryCheckRequest subRequest){
        return statsFromInventoryChecksAs(name, subRequest.count(), true);
    }
    public WarehouseRequest<T> countPallets(){
        return countPalletsAs("Count");
    }

    public WarehouseRequest<T> countPalletsAs(String name){
        return countPalletsWith(name, Q.pallets().unlimited());
    }

    public WarehouseRequest<T> countPalletsWith(String name, PalletRequest subRequest){
        return statsFromPalletsAs(name, subRequest.count(), true);
    }
    public WarehouseRequest<T> countStorageFees(){
        return countStorageFeesAs("Count");
    }

    public WarehouseRequest<T> countStorageFeesAs(String name){
        return countStorageFeesWith(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> countStorageFeesWith(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.count(), true);
    }
    public WarehouseRequest<T> minAmountOfStorageFees(){
        return minAmountOfStorageFeesAs("minAmountOfStorageFees");
    }

    public WarehouseRequest<T> minAmountOfStorageFeesAs(String name){
        return minAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> minAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.minAmount(), true);
    }
    public WarehouseRequest<T> maxAmountOfStorageFees(){
        return maxAmountOfStorageFeesAs("maxAmountOfStorageFees");
    }

    public WarehouseRequest<T> maxAmountOfStorageFeesAs(String name){
        return maxAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> maxAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.maxAmount(), true);
    }
    public WarehouseRequest<T> sumAmountOfStorageFees(){
        return sumAmountOfStorageFeesAs("sumAmountOfStorageFees");
    }

    public WarehouseRequest<T> sumAmountOfStorageFeesAs(String name){
        return sumAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> sumAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.sumAmount(), true);
    }
    public WarehouseRequest<T> avgAmountOfStorageFees(){
        return avgAmountOfStorageFeesAs("avgAmountOfStorageFees");
    }

    public WarehouseRequest<T> avgAmountOfStorageFeesAs(String name){
        return avgAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> avgAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.avgAmount(), true);
    }
    public WarehouseRequest<T> standardDeviationAmountOfStorageFees(){
        return standardDeviationAmountOfStorageFeesAs("stdDevAmountOfStorageFees");
    }

    public WarehouseRequest<T> standardDeviationAmountOfStorageFeesAs(String name){
        return standardDeviationAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> standardDeviationAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.standardDeviationAmount(), true);
    }
    public WarehouseRequest<T> squareRootOfPopulationStandardDeviationAmountOfStorageFees(){
        return squareRootOfPopulationStandardDeviationAmountOfStorageFeesAs("stdDevPopAmountOfStorageFees");
    }

    public WarehouseRequest<T> squareRootOfPopulationStandardDeviationAmountOfStorageFeesAs(String name){
        return squareRootOfPopulationStandardDeviationAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> squareRootOfPopulationStandardDeviationAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.squareRootOfPopulationStandardDeviationAmount(), true);
    }
    public WarehouseRequest<T> sampleVarianceAmountOfStorageFees(){
        return sampleVarianceAmountOfStorageFeesAs("varSampAmountOfStorageFees");
    }

    public WarehouseRequest<T> sampleVarianceAmountOfStorageFeesAs(String name){
        return sampleVarianceAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> sampleVarianceAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.sampleVarianceAmount(), true);
    }
    public WarehouseRequest<T> samplePopulationVarianceAmountOfStorageFees(){
        return samplePopulationVarianceAmountOfStorageFeesAs("varPopAmountOfStorageFees");
    }

    public WarehouseRequest<T> samplePopulationVarianceAmountOfStorageFeesAs(String name){
        return samplePopulationVarianceAmountOfStorageFeesAs(name, Q.storageFees().unlimited());
    }

    public WarehouseRequest<T> samplePopulationVarianceAmountOfStorageFeesAs(String name, StorageFeeRequest subRequest){
        return statsFromStorageFeesAs(name, subRequest.samplePopulationVarianceAmount(), true);
    }



    /**
     * get topN records
     * @param topN  records number
     */
    public WarehouseRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public WarehouseRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public WarehouseRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public WarehouseRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public WarehouseRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}