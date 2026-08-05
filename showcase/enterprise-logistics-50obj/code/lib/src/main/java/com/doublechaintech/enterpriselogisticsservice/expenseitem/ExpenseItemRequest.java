package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        return selectId().selectName().selectDescription().selectAmount().selectCurrency().selectExpenseType().selectExpenseDate().selectEmployee().selectStatus().selectVersion();
    }

    public ExpenseItemRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public ExpenseItemRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectDescription().selectAmount().selectCurrency().selectExpenseType().selectExpenseDate().selectEmployee().selectStatus().selectVersion();
    }

    public ExpenseItemRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectDescription().selectAmount().selectCurrency().selectExpenseType().selectExpenseDate().selectEmployee().selectStatus().selectVersion();
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
    public ExpenseItemRequest<T> selectDescription(){
       selectProperty(ExpenseItem.DESCRIPTION_PROPERTY);
       return this;
    }

    /**
     * fill the description with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  description) to fetch description property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectDescription(){
       unselectProperty(ExpenseItem.DESCRIPTION_PROPERTY);
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
    public ExpenseItemRequest<T> selectExpenseType(){
       selectProperty(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }

    /**
     * fill the expenseType with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  expenseType) to fetch expenseType property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectExpenseType(){
       unselectProperty(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectExpenseDate(){
       selectProperty(ExpenseItem.EXPENSE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the expenseDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  expenseDate) to fetch expenseDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectExpenseDate(){
       unselectProperty(ExpenseItem.EXPENSE_DATE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectEmployee(){
       selectProperty(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }

    /**
     * fill the employee with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  employee) to fetch employee property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectEmployee(){
       unselectProperty(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> selectStatus(){
       selectProperty(ExpenseItem.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public ExpenseItemRequest<T> unselectStatus(){
       unselectProperty(ExpenseItem.STATUS_PROPERTY);
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



    public ExpenseItemRequest<T> filterByDescription(String... description){
      if (description == null || description.length == 0) {
        throw new IllegalArgumentException("filterByDescription parameter description cannot be empty");
      }
      return appendSearchCriteria(createDescriptionCriteria(Operator.EQUAL, (Object[])description));
    }

    public ExpenseItemRequest<T> withDescription(Operator operator, Object... values){
       return appendSearchCriteria(createDescriptionCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withDescriptionIsUnknown(){
       return withDescription(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withDescriptionIsKnown(){
       return withDescription(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDescriptionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.DESCRIPTION_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withDescriptionGreaterThan(String description){
       return withDescription(Operator.GREATER_THAN, description);
    }

    public ExpenseItemRequest<T> withDescriptionGreaterThanOrEqualTo(String description){
       return withDescription(Operator.GREATER_THAN_OR_EQUAL, description);
    }

    public ExpenseItemRequest<T> withDescriptionLessThan(String description){
       return withDescription(Operator.LESS_THAN, description);
    }

    public ExpenseItemRequest<T> withDescriptionLessThanOrEqualTo(String description){
       return withDescription(Operator.LESS_THAN_OR_EQUAL, description);
    }

    public ExpenseItemRequest<T> withDescriptionBetween(String startOfDescription, String endOfDescription){
       return withDescription(Operator.BETWEEN, startOfDescription, endOfDescription);
    }
    public ExpenseItemRequest<T> withDescriptionStartingWith(String description){
       return withDescription(Operator.BEGIN_WITH, description);
    }
    public ExpenseItemRequest<T> withDescriptionContaining(String description){
       return withDescription(Operator.CONTAIN, description);
    }

    public ExpenseItemRequest<T> withDescriptionEndingWith(String description){
       return withDescription(Operator.END_WITH, description);
    }

    public ExpenseItemRequest<T> withDescriptionIs(String description){
       return withDescription(Operator.EQUAL, description);
    }

    public ExpenseItemRequest<T> withDescriptionSoundingLike(String description){
       return withDescription(Operator.SOUNDS_LIKE, description);
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



    public ExpenseItemRequest<T> filterByExpenseType(String... expenseType){
      if (expenseType == null || expenseType.length == 0) {
        throw new IllegalArgumentException("filterByExpenseType parameter expenseType cannot be empty");
      }
      return appendSearchCriteria(createExpenseTypeCriteria(Operator.EQUAL, (Object[])expenseType));
    }

    public ExpenseItemRequest<T> withExpenseType(Operator operator, Object... values){
       return appendSearchCriteria(createExpenseTypeCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withExpenseTypeIsUnknown(){
       return withExpenseType(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withExpenseTypeIsKnown(){
       return withExpenseType(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createExpenseTypeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.EXPENSE_TYPE_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withExpenseTypeGreaterThan(String expenseType){
       return withExpenseType(Operator.GREATER_THAN, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeGreaterThanOrEqualTo(String expenseType){
       return withExpenseType(Operator.GREATER_THAN_OR_EQUAL, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeLessThan(String expenseType){
       return withExpenseType(Operator.LESS_THAN, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeLessThanOrEqualTo(String expenseType){
       return withExpenseType(Operator.LESS_THAN_OR_EQUAL, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeBetween(String startOfExpenseType, String endOfExpenseType){
       return withExpenseType(Operator.BETWEEN, startOfExpenseType, endOfExpenseType);
    }
    public ExpenseItemRequest<T> withExpenseTypeStartingWith(String expenseType){
       return withExpenseType(Operator.BEGIN_WITH, expenseType);
    }
    public ExpenseItemRequest<T> withExpenseTypeContaining(String expenseType){
       return withExpenseType(Operator.CONTAIN, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeEndingWith(String expenseType){
       return withExpenseType(Operator.END_WITH, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeIs(String expenseType){
       return withExpenseType(Operator.EQUAL, expenseType);
    }

    public ExpenseItemRequest<T> withExpenseTypeSoundingLike(String expenseType){
       return withExpenseType(Operator.SOUNDS_LIKE, expenseType);
    }



    public ExpenseItemRequest<T> filterByExpenseDate(LocalDate... expenseDate){
      if (expenseDate == null || expenseDate.length == 0) {
        throw new IllegalArgumentException("filterByExpenseDate parameter expenseDate cannot be empty");
      }
      return appendSearchCriteria(createExpenseDateCriteria(Operator.EQUAL, (Object[])expenseDate));
    }

    public ExpenseItemRequest<T> withExpenseDate(Operator operator, Object... values){
       return appendSearchCriteria(createExpenseDateCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withExpenseDateIsUnknown(){
       return withExpenseDate(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withExpenseDateIsKnown(){
       return withExpenseDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createExpenseDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.EXPENSE_DATE_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withExpenseDateGreaterThan(LocalDate expenseDate){
       return withExpenseDate(Operator.GREATER_THAN, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateGreaterThanOrEqualTo(LocalDate expenseDate){
       return withExpenseDate(Operator.GREATER_THAN_OR_EQUAL, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateLessThan(LocalDate expenseDate){
       return withExpenseDate(Operator.LESS_THAN, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateLessThanOrEqualTo(LocalDate expenseDate){
       return withExpenseDate(Operator.LESS_THAN_OR_EQUAL, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateBetween(LocalDate startOfExpenseDate, LocalDate endOfExpenseDate){
       return withExpenseDate(Operator.BETWEEN, startOfExpenseDate, endOfExpenseDate);
    }
    public ExpenseItemRequest<T> withExpenseDateBefore(LocalDate expenseDate){
       return withExpenseDate(Operator.LESS_THAN, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateBefore(Date expenseDate){
       return withExpenseDate(Operator.LESS_THAN, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateAfter(LocalDate expenseDate){
       return withExpenseDate(Operator.GREATER_THAN, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateAfter(Date expenseDate){
       return withExpenseDate(Operator.GREATER_THAN, expenseDate);
    }

    public ExpenseItemRequest<T> withExpenseDateBetween(Date startOfExpenseDate, Date endOfExpenseDate){
       return withExpenseDate(Operator.BETWEEN, startOfExpenseDate, endOfExpenseDate);
    }




    public ExpenseItemRequest<T> filterByEmployee(String... employee){
      if (employee == null || employee.length == 0) {
        throw new IllegalArgumentException("filterByEmployee parameter employee cannot be empty");
      }
      return appendSearchCriteria(createEmployeeCriteria(Operator.EQUAL, (Object[])employee));
    }

    public ExpenseItemRequest<T> withEmployee(Operator operator, Object... values){
       return appendSearchCriteria(createEmployeeCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withEmployeeIsUnknown(){
       return withEmployee(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withEmployeeIsKnown(){
       return withEmployee(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmployeeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.EMPLOYEE_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withEmployeeGreaterThan(String employee){
       return withEmployee(Operator.GREATER_THAN, employee);
    }

    public ExpenseItemRequest<T> withEmployeeGreaterThanOrEqualTo(String employee){
       return withEmployee(Operator.GREATER_THAN_OR_EQUAL, employee);
    }

    public ExpenseItemRequest<T> withEmployeeLessThan(String employee){
       return withEmployee(Operator.LESS_THAN, employee);
    }

    public ExpenseItemRequest<T> withEmployeeLessThanOrEqualTo(String employee){
       return withEmployee(Operator.LESS_THAN_OR_EQUAL, employee);
    }

    public ExpenseItemRequest<T> withEmployeeBetween(String startOfEmployee, String endOfEmployee){
       return withEmployee(Operator.BETWEEN, startOfEmployee, endOfEmployee);
    }
    public ExpenseItemRequest<T> withEmployeeStartingWith(String employee){
       return withEmployee(Operator.BEGIN_WITH, employee);
    }
    public ExpenseItemRequest<T> withEmployeeContaining(String employee){
       return withEmployee(Operator.CONTAIN, employee);
    }

    public ExpenseItemRequest<T> withEmployeeEndingWith(String employee){
       return withEmployee(Operator.END_WITH, employee);
    }

    public ExpenseItemRequest<T> withEmployeeIs(String employee){
       return withEmployee(Operator.EQUAL, employee);
    }

    public ExpenseItemRequest<T> withEmployeeSoundingLike(String employee){
       return withEmployee(Operator.SOUNDS_LIKE, employee);
    }



    public ExpenseItemRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public ExpenseItemRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public ExpenseItemRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public ExpenseItemRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(ExpenseItem.STATUS_PROPERTY, operator, values);
    }

    public ExpenseItemRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public ExpenseItemRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public ExpenseItemRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public ExpenseItemRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public ExpenseItemRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public ExpenseItemRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public ExpenseItemRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public ExpenseItemRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public ExpenseItemRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public ExpenseItemRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
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

    public ExpenseItemRequest<T> groupByDescription(){
       groupBy(ExpenseItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByDescriptionAs(String retName){
       groupBy(retName, ExpenseItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByDescriptionWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.DESCRIPTION_PROPERTY, function);
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

    public ExpenseItemRequest<T> groupByExpenseType(){
       groupBy(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByExpenseTypeAs(String retName){
       groupBy(retName, ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByExpenseTypeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.EXPENSE_TYPE_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByExpenseDate(){
       groupBy(ExpenseItem.EXPENSE_DATE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByExpenseDateAs(String retName){
       groupBy(retName, ExpenseItem.EXPENSE_DATE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByExpenseDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.EXPENSE_DATE_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByEmployee(){
       groupBy(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByEmployeeAs(String retName){
       groupBy(retName, ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByEmployeeWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.EMPLOYEE_PROPERTY, function);
       return this;
    }

    public ExpenseItemRequest<T> groupByStatus(){
       groupBy(ExpenseItem.STATUS_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByStatusAs(String retName){
       groupBy(retName, ExpenseItem.STATUS_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, ExpenseItem.STATUS_PROPERTY, function);
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
    public ExpenseItemRequest<T> orderByDescriptionAscending(){
       addOrderByAscending(ExpenseItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByDescriptionDescending(){
       addOrderByDescending(ExpenseItem.DESCRIPTION_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByDescriptionAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.DESCRIPTION_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByDescriptionDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.DESCRIPTION_PROPERTY);
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
    public ExpenseItemRequest<T> orderByExpenseTypeAscending(){
       addOrderByAscending(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByExpenseTypeDescending(){
       addOrderByDescending(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByExpenseTypeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByExpenseTypeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.EXPENSE_TYPE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByExpenseDateAscending(){
       addOrderByAscending(ExpenseItem.EXPENSE_DATE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByExpenseDateDescending(){
       addOrderByDescending(ExpenseItem.EXPENSE_DATE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByEmployeeAscending(){
       addOrderByAscending(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByEmployeeDescending(){
       addOrderByDescending(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByEmployeeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByEmployeeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.EMPLOYEE_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByStatusAscending(){
       addOrderByAscending(ExpenseItem.STATUS_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByStatusDescending(){
       addOrderByDescending(ExpenseItem.STATUS_PROPERTY);
       return this;
    }
    public ExpenseItemRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(ExpenseItem.STATUS_PROPERTY);
       return this;
    }

    public ExpenseItemRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(ExpenseItem.STATUS_PROPERTY);
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