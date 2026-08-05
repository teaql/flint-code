package com.doublechaintech.enterpriselogisticsservice.salaryslip;

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
import java.time.LocalDateTime;
import java.util.Date;

public class SalarySlipRequest<T extends SalarySlip> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public SalarySlipRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public SalarySlipRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public SalarySlipRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public SalarySlipRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public SalarySlipRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public SalarySlipRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public SalarySlipRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (SalarySlipRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public SalarySlipRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public SalarySlipRequest<T> matchingAnyOf(SalarySlipRequest salarySlip){
        super.internalMatchAny(salarySlip);
        return this;
    }

    public SalarySlipRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public SalarySlipRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public SalarySlipRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public SalarySlipRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectStaffIdOnly().selectPeriod().selectBaseSalary().selectBonus().selectDeductions().selectNetPay().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public SalarySlipRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public SalarySlipRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectStaff().selectPeriod().selectBaseSalary().selectBonus().selectDeductions().selectNetPay().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public SalarySlipRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectStaff().selectPeriod().selectBaseSalary().selectBonus().selectDeductions().selectNetPay().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public SalarySlipRequest<T> selectId(){
       selectProperty(SalarySlip.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectId(){
       unselectProperty(SalarySlip.ID_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectStaffIdOnly(){
       selectProperty(SalarySlip.STAFF_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> selectStaff(){
        return selectStaffWith(Q.staffMembers().unlimited().selectSelf());
    }

    public SalarySlipRequest<T> selectStaffWith(StaffMemberRequest staff){
       selectProperty(SalarySlip.STAFF_PROPERTY);
       enhanceRelation(SalarySlip.STAFF_PROPERTY, staff);
       return this;
    }

    public SalarySlipRequest<T> unselectStaff(){
       unselectProperty(SalarySlip.STAFF_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectPeriod(){
       selectProperty(SalarySlip.PERIOD_PROPERTY);
       return this;
    }

    /**
     * fill the period with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  period) to fetch period property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectPeriod(){
       unselectProperty(SalarySlip.PERIOD_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectBaseSalary(){
       selectProperty(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }

    /**
     * fill the baseSalary with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  baseSalary) to fetch baseSalary property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectBaseSalary(){
       unselectProperty(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectBonus(){
       selectProperty(SalarySlip.BONUS_PROPERTY);
       return this;
    }

    /**
     * fill the bonus with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  bonus) to fetch bonus property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectBonus(){
       unselectProperty(SalarySlip.BONUS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectDeductions(){
       selectProperty(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }

    /**
     * fill the deductions with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  deductions) to fetch deductions property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectDeductions(){
       unselectProperty(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectNetPay(){
       selectProperty(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }

    /**
     * fill the netPay with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  netPay) to fetch netPay property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectNetPay(){
       unselectProperty(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectStatus(){
       selectProperty(SalarySlip.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectStatus(){
       unselectProperty(SalarySlip.STATUS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectCreatedAt(){
       selectProperty(SalarySlip.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectCreatedAt(){
       unselectProperty(SalarySlip.CREATED_AT_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectUpdatedAt(){
       selectProperty(SalarySlip.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectUpdatedAt(){
       unselectProperty(SalarySlip.UPDATED_AT_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> selectVersion(){
       selectProperty(SalarySlip.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public SalarySlipRequest<T> unselectVersion(){
       unselectProperty(SalarySlip.VERSION_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.ID_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public SalarySlipRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public SalarySlipRequest<T> filterByStaff(StaffMember... staff){
      if (staff == null || staff.length == 0) {
        throw new IllegalArgumentException("filterByStaff parameter staff cannot be empty");
      }
      return appendSearchCriteria(createStaffCriteria(Operator.EQUAL, (Object[])staff));
    }

    public SalarySlipRequest<T> withStaff(Operator operator, Object... values){
       return appendSearchCriteria(createStaffCriteria(operator, values));
    }

    public SalarySlipRequest<T> withStaffIsUnknown(){
       return withStaff(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withStaffIsKnown(){
       return withStaff(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStaffCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.STAFF_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> filterByStaff(Long staff){
      if(staff == null){
         return this;
      }
      return withStaff(Operator.EQUAL, staff);
    }
    public SalarySlipRequest<T> withStaffMatching(StaffMemberRequest staff){
       return appendSearchCriteria(new SubQuerySearchCriteria(SalarySlip.STAFF_PROPERTY, staff, StaffMember.ID_PROPERTY));
    }

    public SalarySlipRequest<T> filterByPeriod(String... period){
      if (period == null || period.length == 0) {
        throw new IllegalArgumentException("filterByPeriod parameter period cannot be empty");
      }
      return appendSearchCriteria(createPeriodCriteria(Operator.EQUAL, (Object[])period));
    }

    public SalarySlipRequest<T> withPeriod(Operator operator, Object... values){
       return appendSearchCriteria(createPeriodCriteria(operator, values));
    }

    public SalarySlipRequest<T> withPeriodIsUnknown(){
       return withPeriod(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withPeriodIsKnown(){
       return withPeriod(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPeriodCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.PERIOD_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withPeriodGreaterThan(String period){
       return withPeriod(Operator.GREATER_THAN, period);
    }

    public SalarySlipRequest<T> withPeriodGreaterThanOrEqualTo(String period){
       return withPeriod(Operator.GREATER_THAN_OR_EQUAL, period);
    }

    public SalarySlipRequest<T> withPeriodLessThan(String period){
       return withPeriod(Operator.LESS_THAN, period);
    }

    public SalarySlipRequest<T> withPeriodLessThanOrEqualTo(String period){
       return withPeriod(Operator.LESS_THAN_OR_EQUAL, period);
    }

    public SalarySlipRequest<T> withPeriodBetween(String startOfPeriod, String endOfPeriod){
       return withPeriod(Operator.BETWEEN, startOfPeriod, endOfPeriod);
    }
    public SalarySlipRequest<T> withPeriodStartingWith(String period){
       return withPeriod(Operator.BEGIN_WITH, period);
    }
    public SalarySlipRequest<T> withPeriodContaining(String period){
       return withPeriod(Operator.CONTAIN, period);
    }

    public SalarySlipRequest<T> withPeriodEndingWith(String period){
       return withPeriod(Operator.END_WITH, period);
    }

    public SalarySlipRequest<T> withPeriodIs(String period){
       return withPeriod(Operator.EQUAL, period);
    }

    public SalarySlipRequest<T> withPeriodSoundingLike(String period){
       return withPeriod(Operator.SOUNDS_LIKE, period);
    }



    public SalarySlipRequest<T> filterByBaseSalary(String... baseSalary){
      if (baseSalary == null || baseSalary.length == 0) {
        throw new IllegalArgumentException("filterByBaseSalary parameter baseSalary cannot be empty");
      }
      return appendSearchCriteria(createBaseSalaryCriteria(Operator.EQUAL, (Object[])baseSalary));
    }

    public SalarySlipRequest<T> withBaseSalary(Operator operator, Object... values){
       return appendSearchCriteria(createBaseSalaryCriteria(operator, values));
    }

    public SalarySlipRequest<T> withBaseSalaryIsUnknown(){
       return withBaseSalary(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withBaseSalaryIsKnown(){
       return withBaseSalary(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createBaseSalaryCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.BASE_SALARY_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withBaseSalaryGreaterThan(String baseSalary){
       return withBaseSalary(Operator.GREATER_THAN, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalaryGreaterThanOrEqualTo(String baseSalary){
       return withBaseSalary(Operator.GREATER_THAN_OR_EQUAL, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalaryLessThan(String baseSalary){
       return withBaseSalary(Operator.LESS_THAN, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalaryLessThanOrEqualTo(String baseSalary){
       return withBaseSalary(Operator.LESS_THAN_OR_EQUAL, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalaryBetween(String startOfBaseSalary, String endOfBaseSalary){
       return withBaseSalary(Operator.BETWEEN, startOfBaseSalary, endOfBaseSalary);
    }
    public SalarySlipRequest<T> withBaseSalaryStartingWith(String baseSalary){
       return withBaseSalary(Operator.BEGIN_WITH, baseSalary);
    }
    public SalarySlipRequest<T> withBaseSalaryContaining(String baseSalary){
       return withBaseSalary(Operator.CONTAIN, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalaryEndingWith(String baseSalary){
       return withBaseSalary(Operator.END_WITH, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalaryIs(String baseSalary){
       return withBaseSalary(Operator.EQUAL, baseSalary);
    }

    public SalarySlipRequest<T> withBaseSalarySoundingLike(String baseSalary){
       return withBaseSalary(Operator.SOUNDS_LIKE, baseSalary);
    }



    public SalarySlipRequest<T> filterByBonus(String... bonus){
      if (bonus == null || bonus.length == 0) {
        throw new IllegalArgumentException("filterByBonus parameter bonus cannot be empty");
      }
      return appendSearchCriteria(createBonusCriteria(Operator.EQUAL, (Object[])bonus));
    }

    public SalarySlipRequest<T> withBonus(Operator operator, Object... values){
       return appendSearchCriteria(createBonusCriteria(operator, values));
    }

    public SalarySlipRequest<T> withBonusIsUnknown(){
       return withBonus(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withBonusIsKnown(){
       return withBonus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createBonusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.BONUS_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withBonusGreaterThan(String bonus){
       return withBonus(Operator.GREATER_THAN, bonus);
    }

    public SalarySlipRequest<T> withBonusGreaterThanOrEqualTo(String bonus){
       return withBonus(Operator.GREATER_THAN_OR_EQUAL, bonus);
    }

    public SalarySlipRequest<T> withBonusLessThan(String bonus){
       return withBonus(Operator.LESS_THAN, bonus);
    }

    public SalarySlipRequest<T> withBonusLessThanOrEqualTo(String bonus){
       return withBonus(Operator.LESS_THAN_OR_EQUAL, bonus);
    }

    public SalarySlipRequest<T> withBonusBetween(String startOfBonus, String endOfBonus){
       return withBonus(Operator.BETWEEN, startOfBonus, endOfBonus);
    }
    public SalarySlipRequest<T> withBonusStartingWith(String bonus){
       return withBonus(Operator.BEGIN_WITH, bonus);
    }
    public SalarySlipRequest<T> withBonusContaining(String bonus){
       return withBonus(Operator.CONTAIN, bonus);
    }

    public SalarySlipRequest<T> withBonusEndingWith(String bonus){
       return withBonus(Operator.END_WITH, bonus);
    }

    public SalarySlipRequest<T> withBonusIs(String bonus){
       return withBonus(Operator.EQUAL, bonus);
    }

    public SalarySlipRequest<T> withBonusSoundingLike(String bonus){
       return withBonus(Operator.SOUNDS_LIKE, bonus);
    }



    public SalarySlipRequest<T> filterByDeductions(String... deductions){
      if (deductions == null || deductions.length == 0) {
        throw new IllegalArgumentException("filterByDeductions parameter deductions cannot be empty");
      }
      return appendSearchCriteria(createDeductionsCriteria(Operator.EQUAL, (Object[])deductions));
    }

    public SalarySlipRequest<T> withDeductions(Operator operator, Object... values){
       return appendSearchCriteria(createDeductionsCriteria(operator, values));
    }

    public SalarySlipRequest<T> withDeductionsIsUnknown(){
       return withDeductions(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withDeductionsIsKnown(){
       return withDeductions(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeductionsCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.DEDUCTIONS_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withDeductionsGreaterThan(String deductions){
       return withDeductions(Operator.GREATER_THAN, deductions);
    }

    public SalarySlipRequest<T> withDeductionsGreaterThanOrEqualTo(String deductions){
       return withDeductions(Operator.GREATER_THAN_OR_EQUAL, deductions);
    }

    public SalarySlipRequest<T> withDeductionsLessThan(String deductions){
       return withDeductions(Operator.LESS_THAN, deductions);
    }

    public SalarySlipRequest<T> withDeductionsLessThanOrEqualTo(String deductions){
       return withDeductions(Operator.LESS_THAN_OR_EQUAL, deductions);
    }

    public SalarySlipRequest<T> withDeductionsBetween(String startOfDeductions, String endOfDeductions){
       return withDeductions(Operator.BETWEEN, startOfDeductions, endOfDeductions);
    }
    public SalarySlipRequest<T> withDeductionsStartingWith(String deductions){
       return withDeductions(Operator.BEGIN_WITH, deductions);
    }
    public SalarySlipRequest<T> withDeductionsContaining(String deductions){
       return withDeductions(Operator.CONTAIN, deductions);
    }

    public SalarySlipRequest<T> withDeductionsEndingWith(String deductions){
       return withDeductions(Operator.END_WITH, deductions);
    }

    public SalarySlipRequest<T> withDeductionsIs(String deductions){
       return withDeductions(Operator.EQUAL, deductions);
    }

    public SalarySlipRequest<T> withDeductionsSoundingLike(String deductions){
       return withDeductions(Operator.SOUNDS_LIKE, deductions);
    }



    public SalarySlipRequest<T> filterByNetPay(String... netPay){
      if (netPay == null || netPay.length == 0) {
        throw new IllegalArgumentException("filterByNetPay parameter netPay cannot be empty");
      }
      return appendSearchCriteria(createNetPayCriteria(Operator.EQUAL, (Object[])netPay));
    }

    public SalarySlipRequest<T> withNetPay(Operator operator, Object... values){
       return appendSearchCriteria(createNetPayCriteria(operator, values));
    }

    public SalarySlipRequest<T> withNetPayIsUnknown(){
       return withNetPay(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withNetPayIsKnown(){
       return withNetPay(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNetPayCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.NET_PAY_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withNetPayGreaterThan(String netPay){
       return withNetPay(Operator.GREATER_THAN, netPay);
    }

    public SalarySlipRequest<T> withNetPayGreaterThanOrEqualTo(String netPay){
       return withNetPay(Operator.GREATER_THAN_OR_EQUAL, netPay);
    }

    public SalarySlipRequest<T> withNetPayLessThan(String netPay){
       return withNetPay(Operator.LESS_THAN, netPay);
    }

    public SalarySlipRequest<T> withNetPayLessThanOrEqualTo(String netPay){
       return withNetPay(Operator.LESS_THAN_OR_EQUAL, netPay);
    }

    public SalarySlipRequest<T> withNetPayBetween(String startOfNetPay, String endOfNetPay){
       return withNetPay(Operator.BETWEEN, startOfNetPay, endOfNetPay);
    }
    public SalarySlipRequest<T> withNetPayStartingWith(String netPay){
       return withNetPay(Operator.BEGIN_WITH, netPay);
    }
    public SalarySlipRequest<T> withNetPayContaining(String netPay){
       return withNetPay(Operator.CONTAIN, netPay);
    }

    public SalarySlipRequest<T> withNetPayEndingWith(String netPay){
       return withNetPay(Operator.END_WITH, netPay);
    }

    public SalarySlipRequest<T> withNetPayIs(String netPay){
       return withNetPay(Operator.EQUAL, netPay);
    }

    public SalarySlipRequest<T> withNetPaySoundingLike(String netPay){
       return withNetPay(Operator.SOUNDS_LIKE, netPay);
    }



    public SalarySlipRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public SalarySlipRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public SalarySlipRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.STATUS_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public SalarySlipRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public SalarySlipRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public SalarySlipRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public SalarySlipRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public SalarySlipRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public SalarySlipRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public SalarySlipRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public SalarySlipRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public SalarySlipRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public SalarySlipRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public SalarySlipRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public SalarySlipRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.CREATED_AT_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public SalarySlipRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public SalarySlipRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public SalarySlipRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public SalarySlipRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public SalarySlipRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.UPDATED_AT_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public SalarySlipRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public SalarySlipRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public SalarySlipRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public SalarySlipRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public SalarySlipRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public SalarySlipRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(SalarySlip.VERSION_PROPERTY, operator, values);
    }

    public SalarySlipRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public SalarySlipRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public SalarySlipRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public SalarySlipRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public SalarySlipRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public SalarySlipRequest<T> count(){
        super.count();
        return this;
    }
    public SalarySlipRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public SalarySlipRequest<T> groupByStaffWithDetails(){
       return groupByStaffWithDetails(Q.staffMembers().unlimited());
    }

    public SalarySlipRequest<T> groupByStaffWithDetails(StaffMemberRequest subRequest){
       aggregate(SalarySlip.STAFF_PROPERTY, subRequest);
       return this;
    }











    public SalarySlipRequest<T> groupById(){
       groupBy(SalarySlip.ID_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByIdAs(String retName){
       groupBy(retName, SalarySlip.ID_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.ID_PROPERTY, function);
       return this;
    }
    public SalarySlipRequest<T> groupByStaffWith(StaffMemberRequest subRequest){
       groupBy(SalarySlip.STAFF_PROPERTY, subRequest);
       return this;
    }
    public SalarySlipRequest<T> groupByStaff(){
       groupBy(SalarySlip.STAFF_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByStaffAs(String retName){
       groupBy(retName, SalarySlip.STAFF_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByStaffWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.STAFF_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByPeriod(){
       groupBy(SalarySlip.PERIOD_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByPeriodAs(String retName){
       groupBy(retName, SalarySlip.PERIOD_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByPeriodWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.PERIOD_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByBaseSalary(){
       groupBy(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByBaseSalaryAs(String retName){
       groupBy(retName, SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByBaseSalaryWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.BASE_SALARY_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByBonus(){
       groupBy(SalarySlip.BONUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByBonusAs(String retName){
       groupBy(retName, SalarySlip.BONUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByBonusWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.BONUS_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByDeductions(){
       groupBy(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByDeductionsAs(String retName){
       groupBy(retName, SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByDeductionsWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.DEDUCTIONS_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByNetPay(){
       groupBy(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByNetPayAs(String retName){
       groupBy(retName, SalarySlip.NET_PAY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByNetPayWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.NET_PAY_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByStatus(){
       groupBy(SalarySlip.STATUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByStatusAs(String retName){
       groupBy(retName, SalarySlip.STATUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.STATUS_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByCreatedAt(){
       groupBy(SalarySlip.CREATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, SalarySlip.CREATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.CREATED_AT_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByUpdatedAt(){
       groupBy(SalarySlip.UPDATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, SalarySlip.UPDATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public SalarySlipRequest<T> groupByVersion(){
       groupBy(SalarySlip.VERSION_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByVersionAs(String retName){
       groupBy(retName, SalarySlip.VERSION_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, SalarySlip.VERSION_PROPERTY, function);
       return this;
    }



    public SalarySlipRequest<T> orderByIdAscending(){
       addOrderByAscending(SalarySlip.ID_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByIdDescending(){
       addOrderByDescending(SalarySlip.ID_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByStaffAscending(){
       addOrderByAscending(SalarySlip.STAFF_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByStaffDescending(){
       addOrderByDescending(SalarySlip.STAFF_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByPeriodAscending(){
       addOrderByAscending(SalarySlip.PERIOD_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByPeriodDescending(){
       addOrderByDescending(SalarySlip.PERIOD_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByPeriodAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalarySlip.PERIOD_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByPeriodDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalarySlip.PERIOD_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByBaseSalaryAscending(){
       addOrderByAscending(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByBaseSalaryDescending(){
       addOrderByDescending(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByBaseSalaryAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByBaseSalaryDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalarySlip.BASE_SALARY_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByBonusAscending(){
       addOrderByAscending(SalarySlip.BONUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByBonusDescending(){
       addOrderByDescending(SalarySlip.BONUS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByBonusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalarySlip.BONUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByBonusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalarySlip.BONUS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByDeductionsAscending(){
       addOrderByAscending(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByDeductionsDescending(){
       addOrderByDescending(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByDeductionsAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByDeductionsDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalarySlip.DEDUCTIONS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByNetPayAscending(){
       addOrderByAscending(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByNetPayDescending(){
       addOrderByDescending(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByNetPayAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByNetPayDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalarySlip.NET_PAY_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByStatusAscending(){
       addOrderByAscending(SalarySlip.STATUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByStatusDescending(){
       addOrderByDescending(SalarySlip.STATUS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(SalarySlip.STATUS_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(SalarySlip.STATUS_PROPERTY);
       return this;
    }
    public SalarySlipRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(SalarySlip.CREATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(SalarySlip.CREATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(SalarySlip.UPDATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(SalarySlip.UPDATED_AT_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByVersionAscending(){
       addOrderByAscending(SalarySlip.VERSION_PROPERTY);
       return this;
    }

    public SalarySlipRequest<T> orderByVersionDescending(){
       addOrderByDescending(SalarySlip.VERSION_PROPERTY);
       return this;
    }


    public StaffMemberRequest rollUpToStaff(){
       StaffMemberRequest staff = Q.staffMembers().unlimited();
       this.withStaffMatching(staff)
           .groupByStaffWith(staff);
       return staff;
    }











   public SalarySlipRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff){
       return facetByStaffAs(facetName, staff, true);
   }

   public SalarySlipRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff, boolean includeAllFacets){
       addFacet(facetName, SalarySlip.STAFF_PROPERTY, staff, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public SalarySlipRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public SalarySlipRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public SalarySlipRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public SalarySlipRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public SalarySlipRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}