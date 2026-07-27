package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class CustomsDeclarationRequest<T extends CustomsDeclaration> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public CustomsDeclarationRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public CustomsDeclarationRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public CustomsDeclarationRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public CustomsDeclarationRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public CustomsDeclarationRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public CustomsDeclarationRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public CustomsDeclarationRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (CustomsDeclarationRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public CustomsDeclarationRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public CustomsDeclarationRequest<T> matchingAnyOf(CustomsDeclarationRequest customsDeclaration){
        super.internalMatchAny(customsDeclaration);
        return this;
    }

    public CustomsDeclarationRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public CustomsDeclarationRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public CustomsDeclarationRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public CustomsDeclarationRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectDeclarationNumber().selectPortOfEntry().selectCountryOfOrigin().selectDeclaredValue().selectStatus().selectClearanceDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public CustomsDeclarationRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CustomsDeclarationRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectDeclarationNumber().selectPortOfEntry().selectCountryOfOrigin().selectDeclaredValue().selectStatus().selectClearanceDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public CustomsDeclarationRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectDeclarationNumber().selectPortOfEntry().selectCountryOfOrigin().selectDeclaredValue().selectStatus().selectClearanceDate().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public CustomsDeclarationRequest<T> selectId(){
       selectProperty(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectId(){
       unselectProperty(CustomsDeclaration.ID_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectDeclarationNumber(){
       selectProperty(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the declarationNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  declarationNumber) to fetch declarationNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectDeclarationNumber(){
       unselectProperty(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectPortOfEntry(){
       selectProperty(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }

    /**
     * fill the portOfEntry with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  portOfEntry) to fetch portOfEntry property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectPortOfEntry(){
       unselectProperty(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectCountryOfOrigin(){
       selectProperty(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }

    /**
     * fill the countryOfOrigin with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  countryOfOrigin) to fetch countryOfOrigin property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectCountryOfOrigin(){
       unselectProperty(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectDeclaredValue(){
       selectProperty(CustomsDeclaration.DECLARED_VALUE_PROPERTY);
       return this;
    }

    /**
     * fill the declaredValue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  declaredValue) to fetch declaredValue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the declaredValue with customized aggrFunction, TEAQL uses ({aggrFunction}(declaredValue) AS declaredValue to fetch declaredValue property.
     * @param aggrFunction  aggrFunction
     */
    public CustomsDeclarationRequest<T> selectDeclaredValue(AggrFunction aggrFunction){
       selectProperty(CustomsDeclaration.DECLARED_VALUE_PROPERTY, aggrFunction);
       return this;
    }


    public CustomsDeclarationRequest<T> unselectDeclaredValue(){
       unselectProperty(CustomsDeclaration.DECLARED_VALUE_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectStatus(){
       selectProperty(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectStatus(){
       unselectProperty(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectClearanceDate(){
       selectProperty(CustomsDeclaration.CLEARANCE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the clearanceDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  clearanceDate) to fetch clearanceDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectClearanceDate(){
       unselectProperty(CustomsDeclaration.CLEARANCE_DATE_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectCreatedTime(){
       selectProperty(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectCreatedTime(){
       unselectProperty(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectUpdatedTime(){
       selectProperty(CustomsDeclaration.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectUpdatedTime(){
       unselectProperty(CustomsDeclaration.UPDATED_TIME_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> selectVersion(){
       selectProperty(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomsDeclarationRequest<T> unselectVersion(){
       unselectProperty(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.ID_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public CustomsDeclarationRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public CustomsDeclarationRequest<T> filterByDeclarationNumber(String... declarationNumber){
      if (declarationNumber == null || declarationNumber.length == 0) {
        throw new IllegalArgumentException("filterByDeclarationNumber parameter declarationNumber cannot be empty");
      }
      return appendSearchCriteria(createDeclarationNumberCriteria(Operator.EQUAL, (Object[])declarationNumber));
    }

    public CustomsDeclarationRequest<T> withDeclarationNumber(Operator operator, Object... values){
       return appendSearchCriteria(createDeclarationNumberCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberIsUnknown(){
       return withDeclarationNumber(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberIsKnown(){
       return withDeclarationNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeclarationNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberGreaterThan(String declarationNumber){
       return withDeclarationNumber(Operator.GREATER_THAN, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberGreaterThanOrEqualTo(String declarationNumber){
       return withDeclarationNumber(Operator.GREATER_THAN_OR_EQUAL, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberLessThan(String declarationNumber){
       return withDeclarationNumber(Operator.LESS_THAN, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberLessThanOrEqualTo(String declarationNumber){
       return withDeclarationNumber(Operator.LESS_THAN_OR_EQUAL, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberBetween(String startOfDeclarationNumber, String endOfDeclarationNumber){
       return withDeclarationNumber(Operator.BETWEEN, startOfDeclarationNumber, endOfDeclarationNumber);
    }
    public CustomsDeclarationRequest<T> withDeclarationNumberStartingWith(String declarationNumber){
       return withDeclarationNumber(Operator.BEGIN_WITH, declarationNumber);
    }
    public CustomsDeclarationRequest<T> withDeclarationNumberContaining(String declarationNumber){
       return withDeclarationNumber(Operator.CONTAIN, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberEndingWith(String declarationNumber){
       return withDeclarationNumber(Operator.END_WITH, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberIs(String declarationNumber){
       return withDeclarationNumber(Operator.EQUAL, declarationNumber);
    }

    public CustomsDeclarationRequest<T> withDeclarationNumberSoundingLike(String declarationNumber){
       return withDeclarationNumber(Operator.SOUNDS_LIKE, declarationNumber);
    }



    public CustomsDeclarationRequest<T> filterByPortOfEntry(String... portOfEntry){
      if (portOfEntry == null || portOfEntry.length == 0) {
        throw new IllegalArgumentException("filterByPortOfEntry parameter portOfEntry cannot be empty");
      }
      return appendSearchCriteria(createPortOfEntryCriteria(Operator.EQUAL, (Object[])portOfEntry));
    }

    public CustomsDeclarationRequest<T> withPortOfEntry(Operator operator, Object... values){
       return appendSearchCriteria(createPortOfEntryCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withPortOfEntryIsUnknown(){
       return withPortOfEntry(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryIsKnown(){
       return withPortOfEntry(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPortOfEntryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryGreaterThan(String portOfEntry){
       return withPortOfEntry(Operator.GREATER_THAN, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryGreaterThanOrEqualTo(String portOfEntry){
       return withPortOfEntry(Operator.GREATER_THAN_OR_EQUAL, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryLessThan(String portOfEntry){
       return withPortOfEntry(Operator.LESS_THAN, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryLessThanOrEqualTo(String portOfEntry){
       return withPortOfEntry(Operator.LESS_THAN_OR_EQUAL, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryBetween(String startOfPortOfEntry, String endOfPortOfEntry){
       return withPortOfEntry(Operator.BETWEEN, startOfPortOfEntry, endOfPortOfEntry);
    }
    public CustomsDeclarationRequest<T> withPortOfEntryStartingWith(String portOfEntry){
       return withPortOfEntry(Operator.BEGIN_WITH, portOfEntry);
    }
    public CustomsDeclarationRequest<T> withPortOfEntryContaining(String portOfEntry){
       return withPortOfEntry(Operator.CONTAIN, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryEndingWith(String portOfEntry){
       return withPortOfEntry(Operator.END_WITH, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntryIs(String portOfEntry){
       return withPortOfEntry(Operator.EQUAL, portOfEntry);
    }

    public CustomsDeclarationRequest<T> withPortOfEntrySoundingLike(String portOfEntry){
       return withPortOfEntry(Operator.SOUNDS_LIKE, portOfEntry);
    }



    public CustomsDeclarationRequest<T> filterByCountryOfOrigin(String... countryOfOrigin){
      if (countryOfOrigin == null || countryOfOrigin.length == 0) {
        throw new IllegalArgumentException("filterByCountryOfOrigin parameter countryOfOrigin cannot be empty");
      }
      return appendSearchCriteria(createCountryOfOriginCriteria(Operator.EQUAL, (Object[])countryOfOrigin));
    }

    public CustomsDeclarationRequest<T> withCountryOfOrigin(Operator operator, Object... values){
       return appendSearchCriteria(createCountryOfOriginCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginIsUnknown(){
       return withCountryOfOrigin(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginIsKnown(){
       return withCountryOfOrigin(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCountryOfOriginCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginGreaterThan(String countryOfOrigin){
       return withCountryOfOrigin(Operator.GREATER_THAN, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginGreaterThanOrEqualTo(String countryOfOrigin){
       return withCountryOfOrigin(Operator.GREATER_THAN_OR_EQUAL, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginLessThan(String countryOfOrigin){
       return withCountryOfOrigin(Operator.LESS_THAN, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginLessThanOrEqualTo(String countryOfOrigin){
       return withCountryOfOrigin(Operator.LESS_THAN_OR_EQUAL, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginBetween(String startOfCountryOfOrigin, String endOfCountryOfOrigin){
       return withCountryOfOrigin(Operator.BETWEEN, startOfCountryOfOrigin, endOfCountryOfOrigin);
    }
    public CustomsDeclarationRequest<T> withCountryOfOriginStartingWith(String countryOfOrigin){
       return withCountryOfOrigin(Operator.BEGIN_WITH, countryOfOrigin);
    }
    public CustomsDeclarationRequest<T> withCountryOfOriginContaining(String countryOfOrigin){
       return withCountryOfOrigin(Operator.CONTAIN, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginEndingWith(String countryOfOrigin){
       return withCountryOfOrigin(Operator.END_WITH, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginIs(String countryOfOrigin){
       return withCountryOfOrigin(Operator.EQUAL, countryOfOrigin);
    }

    public CustomsDeclarationRequest<T> withCountryOfOriginSoundingLike(String countryOfOrigin){
       return withCountryOfOrigin(Operator.SOUNDS_LIKE, countryOfOrigin);
    }



    public CustomsDeclarationRequest<T> filterByDeclaredValue(BigDecimal... declaredValue){
      if (declaredValue == null || declaredValue.length == 0) {
        throw new IllegalArgumentException("filterByDeclaredValue parameter declaredValue cannot be empty");
      }
      return appendSearchCriteria(createDeclaredValueCriteria(Operator.EQUAL, (Object[])declaredValue));
    }

    public CustomsDeclarationRequest<T> withDeclaredValue(Operator operator, Object... values){
       return appendSearchCriteria(createDeclaredValueCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withDeclaredValueIsUnknown(){
       return withDeclaredValue(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withDeclaredValueIsKnown(){
       return withDeclaredValue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeclaredValueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.DECLARED_VALUE_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withDeclaredValueGreaterThan(BigDecimal declaredValue){
       return withDeclaredValue(Operator.GREATER_THAN, declaredValue);
    }

    public CustomsDeclarationRequest<T> withDeclaredValueGreaterThanOrEqualTo(BigDecimal declaredValue){
       return withDeclaredValue(Operator.GREATER_THAN_OR_EQUAL, declaredValue);
    }

    public CustomsDeclarationRequest<T> withDeclaredValueLessThan(BigDecimal declaredValue){
       return withDeclaredValue(Operator.LESS_THAN, declaredValue);
    }

    public CustomsDeclarationRequest<T> withDeclaredValueLessThanOrEqualTo(BigDecimal declaredValue){
       return withDeclaredValue(Operator.LESS_THAN_OR_EQUAL, declaredValue);
    }

    public CustomsDeclarationRequest<T> withDeclaredValueBetween(BigDecimal startOfDeclaredValue, BigDecimal endOfDeclaredValue){
       return withDeclaredValue(Operator.BETWEEN, startOfDeclaredValue, endOfDeclaredValue);
    }



    public CustomsDeclarationRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public CustomsDeclarationRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.STATUS_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public CustomsDeclarationRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public CustomsDeclarationRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public CustomsDeclarationRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public CustomsDeclarationRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public CustomsDeclarationRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public CustomsDeclarationRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public CustomsDeclarationRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public CustomsDeclarationRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public CustomsDeclarationRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public CustomsDeclarationRequest<T> filterByClearanceDate(LocalDate... clearanceDate){
      if (clearanceDate == null || clearanceDate.length == 0) {
        throw new IllegalArgumentException("filterByClearanceDate parameter clearanceDate cannot be empty");
      }
      return appendSearchCriteria(createClearanceDateCriteria(Operator.EQUAL, (Object[])clearanceDate));
    }

    public CustomsDeclarationRequest<T> withClearanceDate(Operator operator, Object... values){
       return appendSearchCriteria(createClearanceDateCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withClearanceDateIsUnknown(){
       return withClearanceDate(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withClearanceDateIsKnown(){
       return withClearanceDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createClearanceDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.CLEARANCE_DATE_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withClearanceDateGreaterThan(LocalDate clearanceDate){
       return withClearanceDate(Operator.GREATER_THAN, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateGreaterThanOrEqualTo(LocalDate clearanceDate){
       return withClearanceDate(Operator.GREATER_THAN_OR_EQUAL, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateLessThan(LocalDate clearanceDate){
       return withClearanceDate(Operator.LESS_THAN, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateLessThanOrEqualTo(LocalDate clearanceDate){
       return withClearanceDate(Operator.LESS_THAN_OR_EQUAL, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateBetween(LocalDate startOfClearanceDate, LocalDate endOfClearanceDate){
       return withClearanceDate(Operator.BETWEEN, startOfClearanceDate, endOfClearanceDate);
    }
    public CustomsDeclarationRequest<T> withClearanceDateBefore(LocalDate clearanceDate){
       return withClearanceDate(Operator.LESS_THAN, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateBefore(Date clearanceDate){
       return withClearanceDate(Operator.LESS_THAN, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateAfter(LocalDate clearanceDate){
       return withClearanceDate(Operator.GREATER_THAN, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateAfter(Date clearanceDate){
       return withClearanceDate(Operator.GREATER_THAN, clearanceDate);
    }

    public CustomsDeclarationRequest<T> withClearanceDateBetween(Date startOfClearanceDate, Date endOfClearanceDate){
       return withClearanceDate(Operator.BETWEEN, startOfClearanceDate, endOfClearanceDate);
    }




    public CustomsDeclarationRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public CustomsDeclarationRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.CREATED_TIME_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public CustomsDeclarationRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public CustomsDeclarationRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public CustomsDeclarationRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public CustomsDeclarationRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.UPDATED_TIME_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public CustomsDeclarationRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public CustomsDeclarationRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public CustomsDeclarationRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public CustomsDeclarationRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public CustomsDeclarationRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public CustomsDeclarationRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomsDeclaration.VERSION_PROPERTY, operator, values);
    }

    public CustomsDeclarationRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public CustomsDeclarationRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public CustomsDeclarationRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public CustomsDeclarationRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public CustomsDeclarationRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public CustomsDeclarationRequest<T> count(){
        super.count();
        return this;
    }
    public CustomsDeclarationRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public CustomsDeclarationRequest minDeclaredValue(){
        return minDeclaredValueAs(prefix("minOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest minDeclaredValueAs(String retName){
        super.min(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest maxDeclaredValue(){
        return maxDeclaredValueAs(prefix("maxOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest maxDeclaredValueAs(String retName){
        super.max(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest sumDeclaredValue(){
        return sumDeclaredValueAs(prefix("sumOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest sumDeclaredValueAs(String retName){
        super.sum(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest avgDeclaredValue(){
        return avgDeclaredValueAs(prefix("avgOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest avgDeclaredValueAs(String retName){
        super.avg(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest standardDeviationDeclaredValue(){
        return standardDeviationDeclaredValueAs(prefix("standardDeviationOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest standardDeviationDeclaredValueAs(String retName){
        super.standardDeviation(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest squareRootOfPopulationStandardDeviationDeclaredValue(){
        return squareRootOfPopulationStandardDeviationDeclaredValueAs(prefix("squareRootOfPopulationStandardDeviationOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest squareRootOfPopulationStandardDeviationDeclaredValueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest sampleVarianceDeclaredValue(){
        return sampleVarianceDeclaredValueAs(prefix("sampleVarianceOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest sampleVarianceDeclaredValueAs(String retName){
        super.sampleVariance(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }
    public CustomsDeclarationRequest samplePopulationVarianceDeclaredValue(){
        return samplePopulationVarianceDeclaredValueAs(prefix("samplePopulationVarianceOf",CustomsDeclaration.DECLARED_VALUE_PROPERTY));
    }

    public CustomsDeclarationRequest samplePopulationVarianceDeclaredValueAs(String retName){
        super.samplePopulationVariance(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
        return this;
    }

    public CustomsDeclarationRequest<T> groupById(){
       groupBy(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByIdAs(String retName){
       groupBy(retName, CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.ID_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclarationNumber(){
       groupBy(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclarationNumberAs(String retName){
       groupBy(retName, CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclarationNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.DECLARATION_NUMBER_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByPortOfEntry(){
       groupBy(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByPortOfEntryAs(String retName){
       groupBy(retName, CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByPortOfEntryWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.PORT_OF_ENTRY_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCountryOfOrigin(){
       groupBy(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCountryOfOriginAs(String retName){
       groupBy(retName, CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCountryOfOriginWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclaredValue(){
       groupBy(CustomsDeclaration.DECLARED_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclaredValueAs(String retName){
       groupBy(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByDeclaredValueWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.DECLARED_VALUE_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByStatus(){
       groupBy(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByStatusAs(String retName){
       groupBy(retName, CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.STATUS_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByClearanceDate(){
       groupBy(CustomsDeclaration.CLEARANCE_DATE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByClearanceDateAs(String retName){
       groupBy(retName, CustomsDeclaration.CLEARANCE_DATE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByClearanceDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.CLEARANCE_DATE_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCreatedTime(){
       groupBy(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByUpdatedTime(){
       groupBy(CustomsDeclaration.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, CustomsDeclaration.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByVersion(){
       groupBy(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByVersionAs(String retName){
       groupBy(retName, CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomsDeclaration.VERSION_PROPERTY, function);
       return this;
    }



    public CustomsDeclarationRequest<T> orderByIdAscending(){
       addOrderByAscending(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByIdDescending(){
       addOrderByDescending(CustomsDeclaration.ID_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclarationNumberAscending(){
       addOrderByAscending(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclarationNumberDescending(){
       addOrderByDescending(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByDeclarationNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclarationNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByPortOfEntryAscending(){
       addOrderByAscending(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByPortOfEntryDescending(){
       addOrderByDescending(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByPortOfEntryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByPortOfEntryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByCountryOfOriginAscending(){
       addOrderByAscending(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByCountryOfOriginDescending(){
       addOrderByDescending(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByCountryOfOriginAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByCountryOfOriginDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByDeclaredValueAscending(){
       addOrderByAscending(CustomsDeclaration.DECLARED_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByDeclaredValueDescending(){
       addOrderByDescending(CustomsDeclaration.DECLARED_VALUE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByStatusAscending(){
       addOrderByAscending(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByStatusDescending(){
       addOrderByDescending(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomsDeclaration.STATUS_PROPERTY);
       return this;
    }
    public CustomsDeclarationRequest<T> orderByClearanceDateAscending(){
       addOrderByAscending(CustomsDeclaration.CLEARANCE_DATE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByClearanceDateDescending(){
       addOrderByDescending(CustomsDeclaration.CLEARANCE_DATE_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(CustomsDeclaration.CREATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(CustomsDeclaration.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(CustomsDeclaration.UPDATED_TIME_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByVersionAscending(){
       addOrderByAscending(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }

    public CustomsDeclarationRequest<T> orderByVersionDescending(){
       addOrderByDescending(CustomsDeclaration.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public CustomsDeclarationRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public CustomsDeclarationRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public CustomsDeclarationRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public CustomsDeclarationRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public CustomsDeclarationRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}