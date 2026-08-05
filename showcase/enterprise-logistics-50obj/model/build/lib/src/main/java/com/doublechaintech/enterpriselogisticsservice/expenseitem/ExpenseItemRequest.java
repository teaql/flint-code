package com.doublechaintech.enterpriselogisticsservice.expenseitem;

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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class ExpenseItemRequest<T extends ExpenseItem> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public ExpenseItemRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public ExpenseItemRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public ExpenseItemRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public ExpenseItemRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public ExpenseItemRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public ExpenseItemRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public ExpenseItemRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (ExpenseItemRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public ExpenseItemRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public ExpenseItemRequest<T> matchingAnyOf(ExpenseItemRequest expenseItem){
        super.internalMatchAny(expenseItem);
        return this;
    }

    public ExpenseItemRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public ExpenseItemRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public ExpenseItemRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public ExpenseItemRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectCategory().selectCreatedAt().selectUpdatedAt().selectStaffMemberIdOnly().selectVersion();
    }

    public ExpenseItemRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ExpenseItemRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectCategory().selectCreatedAt().selectUpdatedAt().selectStaffMember().selectVersion();
    }

    public ExpenseItemRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectCode().selectAmount().selectCurrency().selectCategory().selectCreatedAt().selectUpdatedAt().selectStaffMember().selectVersion();
    }


    public ExpenseItemRequest<T> selectId(){
       selectProperty(ExpenseItem.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectId(){
       unselectProperty(ExpenseItem.ID_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectName(){
       selectProperty(ExpenseItem.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectName(){
       unselectProperty(ExpenseItem.NAME_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectCode(){
       selectProperty(ExpenseItem.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectCode(){
       unselectProperty(ExpenseItem.CODE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectAmount(){
       selectProperty(ExpenseItem.AMOUNT_PROPERTY);
       return this;
    }

    /**
     * fill the amount with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  amount) to fetch amount property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the amount with customized aggrFunction, TEAQL uses ({aggrFunction}(amount) AS amount to fetch amount property.
     * @param aggrFunction  aggrFunction
     */
    public ExpenseItemRequest<T> selectAmount(AggrFunction aggrFunction){
       selectProperty(ExpenseItem.AMOUNT_PROPERTY, aggrFunction);
       return this;
    }


    public ExpenseItemRequest<T> unselectAmount(){
       unselectProperty(ExpenseItem.AMOUNT_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectCurrency(){
       selectProperty(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }

    /**
     * fill the currency with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  currency) to fetch currency property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectCurrency(){
       unselectProperty(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectCategory(){
       selectProperty(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }

    /**
     * fill the category with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  category) to fetch category property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectCategory(){
       unselectProperty(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectCreatedAt(){
       selectProperty(ExpenseItem.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectCreatedAt(){
       unselectProperty(ExpenseItem.CREATED_AT_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectUpdatedAt(){
       selectProperty(ExpenseItem.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectUpdatedAt(){
       unselectProperty(ExpenseItem.UPDATED_AT_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectStaffMemberIdOnly(){
       selectProperty(ExpenseItem.STAFF_MEMBER_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> selectStaffMember(){
        return selectStaffMemberWith(Q.staffMembers().unlimited().selectSelf());
    }

    public ExpenseItemRequest<T> selectStaffMemberWith(StaffMemberRequest staffMember){
       selectProperty(ExpenseItem.STAFF_MEMBER_PROPERTY);
       enhanceRelation(ExpenseItem.STAFF_MEMBER_PROPERTY, staffMember);
       return this;
    }

    public ExpenseItemRequest<T> unselectStaffMember(){
       unselectProperty(ExpenseItem.STAFF_MEMBER_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectVersion(){
       selectProperty(ExpenseItem.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectVersion(){
       unselectProperty(ExpenseItem.VERSION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.ID_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public ExpenseItemRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public ExpenseItemRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public ExpenseItemRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.NAME_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public ExpenseItemRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public ExpenseItemRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public ExpenseItemRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public ExpenseItemRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public ExpenseItemRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public ExpenseItemRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public ExpenseItemRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public ExpenseItemRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public ExpenseItemRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public ExpenseItemRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public ExpenseItemRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.CODE_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public ExpenseItemRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public ExpenseItemRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public ExpenseItemRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public ExpenseItemRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public ExpenseItemRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public ExpenseItemRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public ExpenseItemRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public ExpenseItemRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public ExpenseItemRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public ExpenseItemRequest<T> filterByAmount(BigDecimal... amount){
      if (amount == null || amount.length == 0) {
        throw new IllegalArgumentException("filterByAmount parameter amount cannot be empty");
      }
      return appendSearchCriteria(createAmountCriteria(Operator.EQUAL, (Object[])amount));
    }

    public ExpenseItemRequest<T> withAmount(Operator operator, Object... values){
       return appendSearchCriteria(createAmountCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withAmountIsUnknown(){
       return withAmount(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withAmountIsKnown(){
       return withAmount(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createAmountCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.AMOUNT_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withAmountGreaterThan(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN, amount);
    }

    public ExpenseItemRequest<T> withAmountGreaterThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.GREATER_THAN_OR_EQUAL, amount);
    }

    public ExpenseItemRequest<T> withAmountLessThan(BigDecimal amount){
       return withAmount(Operator.LESS_THAN, amount);
    }

    public ExpenseItemRequest<T> withAmountLessThanOrEqualTo(BigDecimal amount){
       return withAmount(Operator.LESS_THAN_OR_EQUAL, amount);
    }

    public ExpenseItemRequest<T> withAmountBetween(BigDecimal startOfAmount, BigDecimal endOfAmount){
       return withAmount(Operator.BETWEEN, startOfAmount, endOfAmount);
    }



    public ExpenseItemRequest<T> filterByCurrency(String... currency){
      if (currency == null || currency.length == 0) {
        throw new IllegalArgumentException("filterByCurrency parameter currency cannot be empty");
      }
      return appendSearchCriteria(createCurrencyCriteria(Operator.EQUAL, (Object[])currency));
    }

    public ExpenseItemRequest<T> withCurrency(Operator operator, Object... values){
       return appendSearchCriteria(createCurrencyCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withCurrencyIsUnknown(){
       return withCurrency(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withCurrencyIsKnown(){
       return withCurrency(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCurrencyCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.CURRENCY_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withCurrencyGreaterThan(String currency){
       return withCurrency(Operator.GREATER_THAN, currency);
    }

    public ExpenseItemRequest<T> withCurrencyGreaterThanOrEqualTo(String currency){
       return withCurrency(Operator.GREATER_THAN_OR_EQUAL, currency);
    }

    public ExpenseItemRequest<T> withCurrencyLessThan(String currency){
       return withCurrency(Operator.LESS_THAN, currency);
    }

    public ExpenseItemRequest<T> withCurrencyLessThanOrEqualTo(String currency){
       return withCurrency(Operator.LESS_THAN_OR_EQUAL, currency);
    }

    public ExpenseItemRequest<T> withCurrencyBetween(String startOfCurrency, String endOfCurrency){
       return withCurrency(Operator.BETWEEN, startOfCurrency, endOfCurrency);
    }
    public ExpenseItemRequest<T> withCurrencyStartingWith(String currency){
       return withCurrency(Operator.BEGIN_WITH, currency);
    }
    public ExpenseItemRequest<T> withCurrencyContaining(String currency){
       return withCurrency(Operator.CONTAIN, currency);
    }

    public ExpenseItemRequest<T> withCurrencyEndingWith(String currency){
       return withCurrency(Operator.END_WITH, currency);
    }

    public ExpenseItemRequest<T> withCurrencyIs(String currency){
       return withCurrency(Operator.EQUAL, currency);
    }

    public ExpenseItemRequest<T> withCurrencySoundingLike(String currency){
       return withCurrency(Operator.SOUNDS_LIKE, currency);
    }



    public ExpenseItemRequest<T> filterByCategory(String... category){
      if (category == null || category.length == 0) {
        throw new IllegalArgumentException("filterByCategory parameter category cannot be empty");
      }
      return appendSearchCriteria(createCategoryCriteria(Operator.EQUAL, (Object[])category));
    }

    public ExpenseItemRequest<T> withCategory(Operator operator, Object... values){
       return appendSearchCriteria(createCategoryCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withCategoryIsUnknown(){
       return withCategory(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withCategoryIsKnown(){
       return withCategory(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCategoryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.CATEGORY_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withCategoryGreaterThan(String category){
       return withCategory(Operator.GREATER_THAN, category);
    }

    public ExpenseItemRequest<T> withCategoryGreaterThanOrEqualTo(String category){
       return withCategory(Operator.GREATER_THAN_OR_EQUAL, category);
    }

    public ExpenseItemRequest<T> withCategoryLessThan(String category){
       return withCategory(Operator.LESS_THAN, category);
    }

    public ExpenseItemRequest<T> withCategoryLessThanOrEqualTo(String category){
       return withCategory(Operator.LESS_THAN_OR_EQUAL, category);
    }

    public ExpenseItemRequest<T> withCategoryBetween(String startOfCategory, String endOfCategory){
       return withCategory(Operator.BETWEEN, startOfCategory, endOfCategory);
    }
    public ExpenseItemRequest<T> withCategoryStartingWith(String category){
       return withCategory(Operator.BEGIN_WITH, category);
    }
    public ExpenseItemRequest<T> withCategoryContaining(String category){
       return withCategory(Operator.CONTAIN, category);
    }

    public ExpenseItemRequest<T> withCategoryEndingWith(String category){
       return withCategory(Operator.END_WITH, category);
    }

    public ExpenseItemRequest<T> withCategoryIs(String category){
       return withCategory(Operator.EQUAL, category);
    }

    public ExpenseItemRequest<T> withCategorySoundingLike(String category){
       return withCategory(Operator.SOUNDS_LIKE, category);
    }



    public ExpenseItemRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public ExpenseItemRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.CREATED_AT_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public ExpenseItemRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public ExpenseItemRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public ExpenseItemRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public ExpenseItemRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.UPDATED_AT_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public ExpenseItemRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public ExpenseItemRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public ExpenseItemRequest<T> filterByStaffMember(StaffMember... staffMember){
      if (staffMember == null || staffMember.length == 0) {
        throw new IllegalArgumentException("filterByStaffMember parameter staffMember cannot be empty");
      }
      return appendSearchCriteria(createStaffMemberCriteria(Operator.EQUAL, (Object[])staffMember));
    }

    public ExpenseItemRequest<T> withStaffMember(Operator operator, Object... values){
       return appendSearchCriteria(createStaffMemberCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withStaffMemberIsUnknown(){
       return withStaffMember(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withStaffMemberIsKnown(){
       return withStaffMember(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStaffMemberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.STAFF_MEMBER_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> filterByStaffMember(Long staffMember){
      if(staffMember == null){
         return this;
      }
      return withStaffMember(Operator.EQUAL, staffMember);
    }
    public ExpenseItemRequest<T> withStaffMemberMatching(StaffMemberRequest staffMember){
       return appendSearchCriteria(new SubQuerySearchCriteria(ExpenseItem.STAFF_MEMBER_PROPERTY, staffMember, StaffMember.ID_PROPERTY));
    }

    public ExpenseItemRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public ExpenseItemRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.VERSION_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public ExpenseItemRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public ExpenseItemRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public ExpenseItemRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public ExpenseItemRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public ExpenseItemRequest<T> count(){
        super.count();
        return this;
    }
    public ExpenseItemRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public ExpenseItemRequest minAmount(){
        return minAmountAs(prefix("minOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest minAmountAs(String retName){
        super.min(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest maxAmount(){
        return maxAmountAs(prefix("maxOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest maxAmountAs(String retName){
        super.max(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest sumAmount(){
        return sumAmountAs(prefix("sumOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest sumAmountAs(String retName){
        super.sum(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest avgAmount(){
        return avgAmountAs(prefix("avgOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest avgAmountAs(String retName){
        super.avg(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest standardDeviationAmount(){
        return standardDeviationAmountAs(prefix("standardDeviationOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest standardDeviationAmountAs(String retName){
        super.standardDeviation(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest squareRootOfPopulationStandardDeviationAmount(){
        return squareRootOfPopulationStandardDeviationAmountAs(prefix("squareRootOfPopulationStandardDeviationOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest squareRootOfPopulationStandardDeviationAmountAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest sampleVarianceAmount(){
        return sampleVarianceAmountAs(prefix("sampleVarianceOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest sampleVarianceAmountAs(String retName){
        super.sampleVariance(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest samplePopulationVarianceAmount(){
        return samplePopulationVarianceAmountAs(prefix("samplePopulationVarianceOf",ExpenseItem.AMOUNT_PROPERTY));
    }

    public ExpenseItemRequest samplePopulationVarianceAmountAs(String retName){
        super.samplePopulationVariance(retName, ExpenseItem.AMOUNT_PROPERTY);
        return this;
    }
    public ExpenseItemRequest<T> groupByStaffMemberWithDetails(){
       return groupByStaffMemberWithDetails(Q.staffMembers().unlimited());
    }

    public ExpenseItemRequest<T> groupByStaffMemberWithDetails(StaffMemberRequest subRequest){
       aggregate(ExpenseItem.STAFF_MEMBER_PROPERTY, subRequest);
       return this;
    }



    public ExpenseItemRequest<T> groupById(){
       groupBy(ExpenseItem.ID_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByIdAs(String retName){
       groupBy(retName, ExpenseItem.ID_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.ID_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByName(){
       groupBy(ExpenseItem.NAME_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByNameAs(String retName){
       groupBy(retName, ExpenseItem.NAME_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.NAME_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByCode(){
       groupBy(ExpenseItem.CODE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCodeAs(String retName){
       groupBy(retName, ExpenseItem.CODE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.CODE_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByAmount(){
       groupBy(ExpenseItem.AMOUNT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByAmountAs(String retName){
       groupBy(retName, ExpenseItem.AMOUNT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByAmountWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.AMOUNT_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByCurrency(){
       groupBy(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCurrencyAs(String retName){
       groupBy(retName, ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCurrencyWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.CURRENCY_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByCategory(){
       groupBy(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCategoryAs(String retName){
       groupBy(retName, ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCategoryWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.CATEGORY_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByCreatedAt(){
       groupBy(ExpenseItem.CREATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, ExpenseItem.CREATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.CREATED_AT_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByUpdatedAt(){
       groupBy(ExpenseItem.UPDATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, ExpenseItem.UPDATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.UPDATED_AT_PROPERTY, function);
       return this;
    }
    public ExpenseItemRequest<T> groupByStaffMemberWith(StaffMemberRequest subRequest){
       groupBy(ExpenseItem.STAFF_MEMBER_PROPERTY, subRequest);
       return this;
    }
    public ExpenseItemRequest<T> groupByStaffMember(){
       groupBy(ExpenseItem.STAFF_MEMBER_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByStaffMemberAs(String retName){
       groupBy(retName, ExpenseItem.STAFF_MEMBER_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByStaffMemberWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.STAFF_MEMBER_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByVersion(){
       groupBy(ExpenseItem.VERSION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByVersionAs(String retName){
       groupBy(retName, ExpenseItem.VERSION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.VERSION_PROPERTY, function);
       return this;
    }



    public ExpenseItemRequest<T> orderByIdAscending(){
       addOrderByAscending(ExpenseItem.ID_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByIdDescending(){
       addOrderByDescending(ExpenseItem.ID_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByNameAscending(){
       addOrderByAscending(ExpenseItem.NAME_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByNameDescending(){
       addOrderByDescending(ExpenseItem.NAME_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.NAME_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.NAME_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByCodeAscending(){
       addOrderByAscending(ExpenseItem.CODE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCodeDescending(){
       addOrderByDescending(ExpenseItem.CODE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.CODE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.CODE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByAmountAscending(){
       addOrderByAscending(ExpenseItem.AMOUNT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByAmountDescending(){
       addOrderByDescending(ExpenseItem.AMOUNT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCurrencyAscending(){
       addOrderByAscending(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCurrencyDescending(){
       addOrderByDescending(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByCurrencyAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCurrencyDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.CURRENCY_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByCategoryAscending(){
       addOrderByAscending(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCategoryDescending(){
       addOrderByDescending(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByCategoryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCategoryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.CATEGORY_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(ExpenseItem.CREATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(ExpenseItem.CREATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(ExpenseItem.UPDATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(ExpenseItem.UPDATED_AT_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByStaffMemberAscending(){
       addOrderByAscending(ExpenseItem.STAFF_MEMBER_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByStaffMemberDescending(){
       addOrderByDescending(ExpenseItem.STAFF_MEMBER_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByVersionAscending(){
       addOrderByAscending(ExpenseItem.VERSION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByVersionDescending(){
       addOrderByDescending(ExpenseItem.VERSION_PROPERTY);
       return this;
    }


    public StaffMemberRequest rollUpToStaffMember(){
       StaffMemberRequest staffMember = Q.staffMembers().unlimited();
       this.withStaffMemberMatching(staffMember)
           .groupByStaffMemberWith(staffMember);
       return staffMember;
    }



   public ExpenseItemRequest<T> facetByStaffMemberAs(String facetName, StaffMemberRequest staffMember){
       return facetByStaffMemberAs(facetName, staffMember, true);
   }

   public ExpenseItemRequest<T> facetByStaffMemberAs(String facetName, StaffMemberRequest staffMember, boolean includeAllFacets){
       addFacet(facetName, ExpenseItem.STAFF_MEMBER_PROPERTY, staffMember, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public ExpenseItemRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public ExpenseItemRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public ExpenseItemRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public ExpenseItemRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public ExpenseItemRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}