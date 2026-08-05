package com.doublechaintech.enterpriselogisticsservice.financialreport;

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

public class FinancialReportRequest<T extends FinancialReport> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public FinancialReportRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public FinancialReportRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public FinancialReportRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public FinancialReportRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public FinancialReportRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public FinancialReportRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public FinancialReportRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (FinancialReportRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public FinancialReportRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public FinancialReportRequest<T> matchingAnyOf(FinancialReportRequest financialReport){
        super.internalMatchAny(financialReport);
        return this;
    }

    public FinancialReportRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public FinancialReportRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public FinancialReportRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public FinancialReportRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectCode().selectTotalRevenue().selectTotalExpenses().selectPeriodStart().selectPeriodEnd().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public FinancialReportRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public FinancialReportRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectCode().selectTotalRevenue().selectTotalExpenses().selectPeriodStart().selectPeriodEnd().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public FinancialReportRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectName().selectCode().selectTotalRevenue().selectTotalExpenses().selectPeriodStart().selectPeriodEnd().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public FinancialReportRequest<T> selectId(){
       selectProperty(FinancialReport.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectId(){
       unselectProperty(FinancialReport.ID_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectName(){
       selectProperty(FinancialReport.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectName(){
       unselectProperty(FinancialReport.NAME_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectCode(){
       selectProperty(FinancialReport.CODE_PROPERTY);
       return this;
    }

    /**
     * fill the code with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  code) to fetch code property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectCode(){
       unselectProperty(FinancialReport.CODE_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectTotalRevenue(){
       selectProperty(FinancialReport.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    /**
     * fill the totalRevenue with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalRevenue) to fetch totalRevenue property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalRevenue with customized aggrFunction, TEAQL uses ({aggrFunction}(totalRevenue) AS totalRevenue to fetch totalRevenue property.
     * @param aggrFunction  aggrFunction
     */
    public FinancialReportRequest<T> selectTotalRevenue(AggrFunction aggrFunction){
       selectProperty(FinancialReport.TOTAL_REVENUE_PROPERTY, aggrFunction);
       return this;
    }


    public FinancialReportRequest<T> unselectTotalRevenue(){
       unselectProperty(FinancialReport.TOTAL_REVENUE_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectTotalExpenses(){
       selectProperty(FinancialReport.TOTAL_EXPENSES_PROPERTY);
       return this;
    }

    /**
     * fill the totalExpenses with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalExpenses) to fetch totalExpenses property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalExpenses with customized aggrFunction, TEAQL uses ({aggrFunction}(totalExpenses) AS totalExpenses to fetch totalExpenses property.
     * @param aggrFunction  aggrFunction
     */
    public FinancialReportRequest<T> selectTotalExpenses(AggrFunction aggrFunction){
       selectProperty(FinancialReport.TOTAL_EXPENSES_PROPERTY, aggrFunction);
       return this;
    }


    public FinancialReportRequest<T> unselectTotalExpenses(){
       unselectProperty(FinancialReport.TOTAL_EXPENSES_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectPeriodStart(){
       selectProperty(FinancialReport.PERIOD_START_PROPERTY);
       return this;
    }

    /**
     * fill the periodStart with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  periodStart) to fetch periodStart property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectPeriodStart(){
       unselectProperty(FinancialReport.PERIOD_START_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectPeriodEnd(){
       selectProperty(FinancialReport.PERIOD_END_PROPERTY);
       return this;
    }

    /**
     * fill the periodEnd with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  periodEnd) to fetch periodEnd property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectPeriodEnd(){
       unselectProperty(FinancialReport.PERIOD_END_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectCreatedAt(){
       selectProperty(FinancialReport.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectCreatedAt(){
       unselectProperty(FinancialReport.CREATED_AT_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectUpdatedAt(){
       selectProperty(FinancialReport.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectUpdatedAt(){
       unselectProperty(FinancialReport.UPDATED_AT_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> selectVersion(){
       selectProperty(FinancialReport.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FinancialReportRequest<T> unselectVersion(){
       unselectProperty(FinancialReport.VERSION_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.ID_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public FinancialReportRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public FinancialReportRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public FinancialReportRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public FinancialReportRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.NAME_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public FinancialReportRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public FinancialReportRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public FinancialReportRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public FinancialReportRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public FinancialReportRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public FinancialReportRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public FinancialReportRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public FinancialReportRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public FinancialReportRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public FinancialReportRequest<T> filterByCode(String... code){
      if (code == null || code.length == 0) {
        throw new IllegalArgumentException("filterByCode parameter code cannot be empty");
      }
      return appendSearchCriteria(createCodeCriteria(Operator.EQUAL, (Object[])code));
    }

    public FinancialReportRequest<T> withCode(Operator operator, Object... values){
       return appendSearchCriteria(createCodeCriteria(operator, values));
    }

    public FinancialReportRequest<T> withCodeIsUnknown(){
       return withCode(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withCodeIsKnown(){
       return withCode(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCodeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.CODE_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withCodeGreaterThan(String code){
       return withCode(Operator.GREATER_THAN, code);
    }

    public FinancialReportRequest<T> withCodeGreaterThanOrEqualTo(String code){
       return withCode(Operator.GREATER_THAN_OR_EQUAL, code);
    }

    public FinancialReportRequest<T> withCodeLessThan(String code){
       return withCode(Operator.LESS_THAN, code);
    }

    public FinancialReportRequest<T> withCodeLessThanOrEqualTo(String code){
       return withCode(Operator.LESS_THAN_OR_EQUAL, code);
    }

    public FinancialReportRequest<T> withCodeBetween(String startOfCode, String endOfCode){
       return withCode(Operator.BETWEEN, startOfCode, endOfCode);
    }
    public FinancialReportRequest<T> withCodeStartingWith(String code){
       return withCode(Operator.BEGIN_WITH, code);
    }
    public FinancialReportRequest<T> withCodeContaining(String code){
       return withCode(Operator.CONTAIN, code);
    }

    public FinancialReportRequest<T> withCodeEndingWith(String code){
       return withCode(Operator.END_WITH, code);
    }

    public FinancialReportRequest<T> withCodeIs(String code){
       return withCode(Operator.EQUAL, code);
    }

    public FinancialReportRequest<T> withCodeSoundingLike(String code){
       return withCode(Operator.SOUNDS_LIKE, code);
    }



    public FinancialReportRequest<T> filterByTotalRevenue(BigDecimal... totalRevenue){
      if (totalRevenue == null || totalRevenue.length == 0) {
        throw new IllegalArgumentException("filterByTotalRevenue parameter totalRevenue cannot be empty");
      }
      return appendSearchCriteria(createTotalRevenueCriteria(Operator.EQUAL, (Object[])totalRevenue));
    }

    public FinancialReportRequest<T> withTotalRevenue(Operator operator, Object... values){
       return appendSearchCriteria(createTotalRevenueCriteria(operator, values));
    }

    public FinancialReportRequest<T> withTotalRevenueIsUnknown(){
       return withTotalRevenue(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withTotalRevenueIsKnown(){
       return withTotalRevenue(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalRevenueCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.TOTAL_REVENUE_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withTotalRevenueGreaterThan(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.GREATER_THAN, totalRevenue);
    }

    public FinancialReportRequest<T> withTotalRevenueGreaterThanOrEqualTo(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.GREATER_THAN_OR_EQUAL, totalRevenue);
    }

    public FinancialReportRequest<T> withTotalRevenueLessThan(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.LESS_THAN, totalRevenue);
    }

    public FinancialReportRequest<T> withTotalRevenueLessThanOrEqualTo(BigDecimal totalRevenue){
       return withTotalRevenue(Operator.LESS_THAN_OR_EQUAL, totalRevenue);
    }

    public FinancialReportRequest<T> withTotalRevenueBetween(BigDecimal startOfTotalRevenue, BigDecimal endOfTotalRevenue){
       return withTotalRevenue(Operator.BETWEEN, startOfTotalRevenue, endOfTotalRevenue);
    }



    public FinancialReportRequest<T> filterByTotalExpenses(BigDecimal... totalExpenses){
      if (totalExpenses == null || totalExpenses.length == 0) {
        throw new IllegalArgumentException("filterByTotalExpenses parameter totalExpenses cannot be empty");
      }
      return appendSearchCriteria(createTotalExpensesCriteria(Operator.EQUAL, (Object[])totalExpenses));
    }

    public FinancialReportRequest<T> withTotalExpenses(Operator operator, Object... values){
       return appendSearchCriteria(createTotalExpensesCriteria(operator, values));
    }

    public FinancialReportRequest<T> withTotalExpensesIsUnknown(){
       return withTotalExpenses(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withTotalExpensesIsKnown(){
       return withTotalExpenses(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalExpensesCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.TOTAL_EXPENSES_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withTotalExpensesGreaterThan(BigDecimal totalExpenses){
       return withTotalExpenses(Operator.GREATER_THAN, totalExpenses);
    }

    public FinancialReportRequest<T> withTotalExpensesGreaterThanOrEqualTo(BigDecimal totalExpenses){
       return withTotalExpenses(Operator.GREATER_THAN_OR_EQUAL, totalExpenses);
    }

    public FinancialReportRequest<T> withTotalExpensesLessThan(BigDecimal totalExpenses){
       return withTotalExpenses(Operator.LESS_THAN, totalExpenses);
    }

    public FinancialReportRequest<T> withTotalExpensesLessThanOrEqualTo(BigDecimal totalExpenses){
       return withTotalExpenses(Operator.LESS_THAN_OR_EQUAL, totalExpenses);
    }

    public FinancialReportRequest<T> withTotalExpensesBetween(BigDecimal startOfTotalExpenses, BigDecimal endOfTotalExpenses){
       return withTotalExpenses(Operator.BETWEEN, startOfTotalExpenses, endOfTotalExpenses);
    }



    public FinancialReportRequest<T> filterByPeriodStart(LocalDate... periodStart){
      if (periodStart == null || periodStart.length == 0) {
        throw new IllegalArgumentException("filterByPeriodStart parameter periodStart cannot be empty");
      }
      return appendSearchCriteria(createPeriodStartCriteria(Operator.EQUAL, (Object[])periodStart));
    }

    public FinancialReportRequest<T> withPeriodStart(Operator operator, Object... values){
       return appendSearchCriteria(createPeriodStartCriteria(operator, values));
    }

    public FinancialReportRequest<T> withPeriodStartIsUnknown(){
       return withPeriodStart(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withPeriodStartIsKnown(){
       return withPeriodStart(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPeriodStartCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.PERIOD_START_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withPeriodStartGreaterThan(LocalDate periodStart){
       return withPeriodStart(Operator.GREATER_THAN, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartGreaterThanOrEqualTo(LocalDate periodStart){
       return withPeriodStart(Operator.GREATER_THAN_OR_EQUAL, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartLessThan(LocalDate periodStart){
       return withPeriodStart(Operator.LESS_THAN, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartLessThanOrEqualTo(LocalDate periodStart){
       return withPeriodStart(Operator.LESS_THAN_OR_EQUAL, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartBetween(LocalDate startOfPeriodStart, LocalDate endOfPeriodStart){
       return withPeriodStart(Operator.BETWEEN, startOfPeriodStart, endOfPeriodStart);
    }
    public FinancialReportRequest<T> withPeriodStartBefore(LocalDate periodStart){
       return withPeriodStart(Operator.LESS_THAN, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartBefore(Date periodStart){
       return withPeriodStart(Operator.LESS_THAN, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartAfter(LocalDate periodStart){
       return withPeriodStart(Operator.GREATER_THAN, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartAfter(Date periodStart){
       return withPeriodStart(Operator.GREATER_THAN, periodStart);
    }

    public FinancialReportRequest<T> withPeriodStartBetween(Date startOfPeriodStart, Date endOfPeriodStart){
       return withPeriodStart(Operator.BETWEEN, startOfPeriodStart, endOfPeriodStart);
    }




    public FinancialReportRequest<T> filterByPeriodEnd(LocalDate... periodEnd){
      if (periodEnd == null || periodEnd.length == 0) {
        throw new IllegalArgumentException("filterByPeriodEnd parameter periodEnd cannot be empty");
      }
      return appendSearchCriteria(createPeriodEndCriteria(Operator.EQUAL, (Object[])periodEnd));
    }

    public FinancialReportRequest<T> withPeriodEnd(Operator operator, Object... values){
       return appendSearchCriteria(createPeriodEndCriteria(operator, values));
    }

    public FinancialReportRequest<T> withPeriodEndIsUnknown(){
       return withPeriodEnd(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withPeriodEndIsKnown(){
       return withPeriodEnd(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPeriodEndCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.PERIOD_END_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withPeriodEndGreaterThan(LocalDate periodEnd){
       return withPeriodEnd(Operator.GREATER_THAN, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndGreaterThanOrEqualTo(LocalDate periodEnd){
       return withPeriodEnd(Operator.GREATER_THAN_OR_EQUAL, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndLessThan(LocalDate periodEnd){
       return withPeriodEnd(Operator.LESS_THAN, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndLessThanOrEqualTo(LocalDate periodEnd){
       return withPeriodEnd(Operator.LESS_THAN_OR_EQUAL, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndBetween(LocalDate startOfPeriodEnd, LocalDate endOfPeriodEnd){
       return withPeriodEnd(Operator.BETWEEN, startOfPeriodEnd, endOfPeriodEnd);
    }
    public FinancialReportRequest<T> withPeriodEndBefore(LocalDate periodEnd){
       return withPeriodEnd(Operator.LESS_THAN, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndBefore(Date periodEnd){
       return withPeriodEnd(Operator.LESS_THAN, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndAfter(LocalDate periodEnd){
       return withPeriodEnd(Operator.GREATER_THAN, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndAfter(Date periodEnd){
       return withPeriodEnd(Operator.GREATER_THAN, periodEnd);
    }

    public FinancialReportRequest<T> withPeriodEndBetween(Date startOfPeriodEnd, Date endOfPeriodEnd){
       return withPeriodEnd(Operator.BETWEEN, startOfPeriodEnd, endOfPeriodEnd);
    }




    public FinancialReportRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public FinancialReportRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public FinancialReportRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.CREATED_AT_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public FinancialReportRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FinancialReportRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public FinancialReportRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public FinancialReportRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public FinancialReportRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.UPDATED_AT_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public FinancialReportRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public FinancialReportRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public FinancialReportRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public FinancialReportRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public FinancialReportRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public FinancialReportRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FinancialReport.VERSION_PROPERTY, operator, values);
    }

    public FinancialReportRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public FinancialReportRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public FinancialReportRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public FinancialReportRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public FinancialReportRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public FinancialReportRequest<T> count(){
        super.count();
        return this;
    }
    public FinancialReportRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public FinancialReportRequest minTotalRevenue(){
        return minTotalRevenueAs(prefix("minOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest minTotalRevenueAs(String retName){
        super.min(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest maxTotalRevenue(){
        return maxTotalRevenueAs(prefix("maxOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest maxTotalRevenueAs(String retName){
        super.max(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest sumTotalRevenue(){
        return sumTotalRevenueAs(prefix("sumOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest sumTotalRevenueAs(String retName){
        super.sum(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest avgTotalRevenue(){
        return avgTotalRevenueAs(prefix("avgOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest avgTotalRevenueAs(String retName){
        super.avg(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest standardDeviationTotalRevenue(){
        return standardDeviationTotalRevenueAs(prefix("standardDeviationOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest standardDeviationTotalRevenueAs(String retName){
        super.standardDeviation(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest squareRootOfPopulationStandardDeviationTotalRevenue(){
        return squareRootOfPopulationStandardDeviationTotalRevenueAs(prefix("squareRootOfPopulationStandardDeviationOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest squareRootOfPopulationStandardDeviationTotalRevenueAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest sampleVarianceTotalRevenue(){
        return sampleVarianceTotalRevenueAs(prefix("sampleVarianceOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest sampleVarianceTotalRevenueAs(String retName){
        super.sampleVariance(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest samplePopulationVarianceTotalRevenue(){
        return samplePopulationVarianceTotalRevenueAs(prefix("samplePopulationVarianceOf",FinancialReport.TOTAL_REVENUE_PROPERTY));
    }

    public FinancialReportRequest samplePopulationVarianceTotalRevenueAs(String retName){
        super.samplePopulationVariance(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
        return this;
    }
    public FinancialReportRequest minTotalExpenses(){
        return minTotalExpensesAs(prefix("minOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest minTotalExpensesAs(String retName){
        super.min(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest maxTotalExpenses(){
        return maxTotalExpensesAs(prefix("maxOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest maxTotalExpensesAs(String retName){
        super.max(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest sumTotalExpenses(){
        return sumTotalExpensesAs(prefix("sumOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest sumTotalExpensesAs(String retName){
        super.sum(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest avgTotalExpenses(){
        return avgTotalExpensesAs(prefix("avgOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest avgTotalExpensesAs(String retName){
        super.avg(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest standardDeviationTotalExpenses(){
        return standardDeviationTotalExpensesAs(prefix("standardDeviationOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest standardDeviationTotalExpensesAs(String retName){
        super.standardDeviation(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest squareRootOfPopulationStandardDeviationTotalExpenses(){
        return squareRootOfPopulationStandardDeviationTotalExpensesAs(prefix("squareRootOfPopulationStandardDeviationOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest squareRootOfPopulationStandardDeviationTotalExpensesAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest sampleVarianceTotalExpenses(){
        return sampleVarianceTotalExpensesAs(prefix("sampleVarianceOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest sampleVarianceTotalExpensesAs(String retName){
        super.sampleVariance(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }
    public FinancialReportRequest samplePopulationVarianceTotalExpenses(){
        return samplePopulationVarianceTotalExpensesAs(prefix("samplePopulationVarianceOf",FinancialReport.TOTAL_EXPENSES_PROPERTY));
    }

    public FinancialReportRequest samplePopulationVarianceTotalExpensesAs(String retName){
        super.samplePopulationVariance(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
        return this;
    }

    public FinancialReportRequest<T> groupById(){
       groupBy(FinancialReport.ID_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByIdAs(String retName){
       groupBy(retName, FinancialReport.ID_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.ID_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByName(){
       groupBy(FinancialReport.NAME_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByNameAs(String retName){
       groupBy(retName, FinancialReport.NAME_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.NAME_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByCode(){
       groupBy(FinancialReport.CODE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByCodeAs(String retName){
       groupBy(retName, FinancialReport.CODE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByCodeWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.CODE_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByTotalRevenue(){
       groupBy(FinancialReport.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByTotalRevenueAs(String retName){
       groupBy(retName, FinancialReport.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByTotalRevenueWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.TOTAL_REVENUE_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByTotalExpenses(){
       groupBy(FinancialReport.TOTAL_EXPENSES_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByTotalExpensesAs(String retName){
       groupBy(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByTotalExpensesWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.TOTAL_EXPENSES_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByPeriodStart(){
       groupBy(FinancialReport.PERIOD_START_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByPeriodStartAs(String retName){
       groupBy(retName, FinancialReport.PERIOD_START_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByPeriodStartWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.PERIOD_START_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByPeriodEnd(){
       groupBy(FinancialReport.PERIOD_END_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByPeriodEndAs(String retName){
       groupBy(retName, FinancialReport.PERIOD_END_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByPeriodEndWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.PERIOD_END_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByCreatedAt(){
       groupBy(FinancialReport.CREATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, FinancialReport.CREATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.CREATED_AT_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByUpdatedAt(){
       groupBy(FinancialReport.UPDATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, FinancialReport.UPDATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public FinancialReportRequest<T> groupByVersion(){
       groupBy(FinancialReport.VERSION_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByVersionAs(String retName){
       groupBy(retName, FinancialReport.VERSION_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, FinancialReport.VERSION_PROPERTY, function);
       return this;
    }



    public FinancialReportRequest<T> orderByIdAscending(){
       addOrderByAscending(FinancialReport.ID_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByIdDescending(){
       addOrderByDescending(FinancialReport.ID_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByNameAscending(){
       addOrderByAscending(FinancialReport.NAME_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByNameDescending(){
       addOrderByDescending(FinancialReport.NAME_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FinancialReport.NAME_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FinancialReport.NAME_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> orderByCodeAscending(){
       addOrderByAscending(FinancialReport.CODE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByCodeDescending(){
       addOrderByDescending(FinancialReport.CODE_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> orderByCodeAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FinancialReport.CODE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByCodeDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FinancialReport.CODE_PROPERTY);
       return this;
    }
    public FinancialReportRequest<T> orderByTotalRevenueAscending(){
       addOrderByAscending(FinancialReport.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByTotalRevenueDescending(){
       addOrderByDescending(FinancialReport.TOTAL_REVENUE_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByTotalExpensesAscending(){
       addOrderByAscending(FinancialReport.TOTAL_EXPENSES_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByTotalExpensesDescending(){
       addOrderByDescending(FinancialReport.TOTAL_EXPENSES_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByPeriodStartAscending(){
       addOrderByAscending(FinancialReport.PERIOD_START_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByPeriodStartDescending(){
       addOrderByDescending(FinancialReport.PERIOD_START_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByPeriodEndAscending(){
       addOrderByAscending(FinancialReport.PERIOD_END_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByPeriodEndDescending(){
       addOrderByDescending(FinancialReport.PERIOD_END_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(FinancialReport.CREATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(FinancialReport.CREATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(FinancialReport.UPDATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(FinancialReport.UPDATED_AT_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByVersionAscending(){
       addOrderByAscending(FinancialReport.VERSION_PROPERTY);
       return this;
    }

    public FinancialReportRequest<T> orderByVersionDescending(){
       addOrderByDescending(FinancialReport.VERSION_PROPERTY);
       return this;
    }





    /**
     * get topN records
     * @param topN  records number
     */
    public FinancialReportRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public FinancialReportRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public FinancialReportRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public FinancialReportRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public FinancialReportRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}