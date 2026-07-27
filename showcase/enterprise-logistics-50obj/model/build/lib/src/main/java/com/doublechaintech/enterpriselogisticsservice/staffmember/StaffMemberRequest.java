package com.doublechaintech.enterpriselogisticsservice.staffmember;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest;
import com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem;
import com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemRequest;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewRequest;
import com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining;
import com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingRequest;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipRequest;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class StaffMemberRequest<T extends StaffMember> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public StaffMemberRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public StaffMemberRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public StaffMemberRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public StaffMemberRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public StaffMemberRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public StaffMemberRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public StaffMemberRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (StaffMemberRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public StaffMemberRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public StaffMemberRequest<T> matchingAnyOf(StaffMemberRequest staffMember){
        super.internalMatchAny(staffMember);
        return this;
    }

    public StaffMemberRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public StaffMemberRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public StaffMemberRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public StaffMemberRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectName().selectEmail().selectPhone().selectHireDate().selectStatus().selectDepartment().selectJobTitle().selectManagerIdOnly().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public StaffMemberRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public StaffMemberRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectName().selectEmail().selectPhone().selectHireDate().selectStatus().selectDepartment().selectJobTitle().selectManager().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public StaffMemberRequest<T> selectChildren(){
        super.selectAny();
        selectDispatchPlanList().selectStaffMemberList().selectWorkedHoursList().selectSalarySlipList().selectPerformanceReviewListAsStaff().selectPerformanceReviewListAsReviewer().selectSafetyTrainingList().selectExpenseItemList();
        return selectId().selectName().selectEmail().selectPhone().selectHireDate().selectStatus().selectDepartment().selectJobTitle().selectManager().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public StaffMemberRequest<T> selectId(){
       selectProperty(StaffMember.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectId(){
       unselectProperty(StaffMember.ID_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectName(){
       selectProperty(StaffMember.NAME_PROPERTY);
       return this;
    }

    /**
     * fill the name with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  name) to fetch name property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectName(){
       unselectProperty(StaffMember.NAME_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectEmail(){
       selectProperty(StaffMember.EMAIL_PROPERTY);
       return this;
    }

    /**
     * fill the email with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  email) to fetch email property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectEmail(){
       unselectProperty(StaffMember.EMAIL_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectPhone(){
       selectProperty(StaffMember.PHONE_PROPERTY);
       return this;
    }

    /**
     * fill the phone with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  phone) to fetch phone property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectPhone(){
       unselectProperty(StaffMember.PHONE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectHireDate(){
       selectProperty(StaffMember.HIRE_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the hireDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  hireDate) to fetch hireDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectHireDate(){
       unselectProperty(StaffMember.HIRE_DATE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectStatus(){
       selectProperty(StaffMember.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectStatus(){
       unselectProperty(StaffMember.STATUS_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectDepartment(){
       selectProperty(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }

    /**
     * fill the department with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  department) to fetch department property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectDepartment(){
       unselectProperty(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectJobTitle(){
       selectProperty(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }

    /**
     * fill the jobTitle with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  jobTitle) to fetch jobTitle property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectJobTitle(){
       unselectProperty(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectManagerIdOnly(){
       selectProperty(StaffMember.MANAGER_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> selectManager(){
        return selectManagerWith(Q.staffMembers().unlimited().selectSelf());
    }

    public StaffMemberRequest<T> selectManagerWith(StaffMemberRequest manager){
       selectProperty(StaffMember.MANAGER_PROPERTY);
       enhanceRelation(StaffMember.MANAGER_PROPERTY, manager);
       return this;
    }

    public StaffMemberRequest<T> unselectManager(){
       unselectProperty(StaffMember.MANAGER_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectCreatedAt(){
       selectProperty(StaffMember.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectCreatedAt(){
       unselectProperty(StaffMember.CREATED_AT_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectUpdatedAt(){
       selectProperty(StaffMember.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectUpdatedAt(){
       unselectProperty(StaffMember.UPDATED_AT_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectVersion(){
       selectProperty(StaffMember.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public StaffMemberRequest<T> unselectVersion(){
       unselectProperty(StaffMember.VERSION_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> selectDispatchPlanList(){
       return selectDispatchPlanListWith(Q.dispatchPlans().selectSelf());
    }

    public StaffMemberRequest<T> selectDispatchPlanListWith(DispatchPlanRequest dispatchPlanList){
       enhanceRelation(StaffMember.DISPATCH_PLAN_LIST_PROPERTY, dispatchPlanList);
       return this;
    }
    public StaffMemberRequest<T> selectStaffMemberList(){
       return selectStaffMemberListWith(Q.staffMembers().selectSelf());
    }

    public StaffMemberRequest<T> selectStaffMemberListWith(StaffMemberRequest staffMemberList){
       enhanceRelation(StaffMember.STAFF_MEMBER_LIST_PROPERTY, staffMemberList);
       return this;
    }
    public StaffMemberRequest<T> selectWorkedHoursList(){
       return selectWorkedHoursListWith(Q.workedHourses().selectSelf());
    }

    public StaffMemberRequest<T> selectWorkedHoursListWith(WorkedHoursRequest workedHoursList){
       enhanceRelation(StaffMember.WORKED_HOURS_LIST_PROPERTY, workedHoursList);
       return this;
    }
    public StaffMemberRequest<T> selectSalarySlipList(){
       return selectSalarySlipListWith(Q.salarySlips().selectSelf());
    }

    public StaffMemberRequest<T> selectSalarySlipListWith(SalarySlipRequest salarySlipList){
       enhanceRelation(StaffMember.SALARY_SLIP_LIST_PROPERTY, salarySlipList);
       return this;
    }
    public StaffMemberRequest<T> selectPerformanceReviewListAsStaff(){
       return selectPerformanceReviewListAsStaffWith(Q.performanceReviews().selectSelf());
    }

    public StaffMemberRequest<T> selectPerformanceReviewListAsStaffWith(PerformanceReviewRequest performanceReviewListAsStaff){
       enhanceRelation(StaffMember.PERFORMANCE_REVIEW_LIST_AS_STAFF_PROPERTY, performanceReviewListAsStaff);
       return this;
    }
    public StaffMemberRequest<T> selectPerformanceReviewListAsReviewer(){
       return selectPerformanceReviewListAsReviewerWith(Q.performanceReviews().selectSelf());
    }

    public StaffMemberRequest<T> selectPerformanceReviewListAsReviewerWith(PerformanceReviewRequest performanceReviewListAsReviewer){
       enhanceRelation(StaffMember.PERFORMANCE_REVIEW_LIST_AS_REVIEWER_PROPERTY, performanceReviewListAsReviewer);
       return this;
    }
    public StaffMemberRequest<T> selectSafetyTrainingList(){
       return selectSafetyTrainingListWith(Q.safetyTrainings().selectSelf());
    }

    public StaffMemberRequest<T> selectSafetyTrainingListWith(SafetyTrainingRequest safetyTrainingList){
       enhanceRelation(StaffMember.SAFETY_TRAINING_LIST_PROPERTY, safetyTrainingList);
       return this;
    }
    public StaffMemberRequest<T> selectExpenseItemList(){
       return selectExpenseItemListWith(Q.expenseItems().selectSelf());
    }

    public StaffMemberRequest<T> selectExpenseItemListWith(ExpenseItemRequest expenseItemList){
       enhanceRelation(StaffMember.EXPENSE_ITEM_LIST_PROPERTY, expenseItemList);
       return this;
    }

    public StaffMemberRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.ID_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public StaffMemberRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public StaffMemberRequest<T> filterByName(String... name){
      if (name == null || name.length == 0) {
        throw new IllegalArgumentException("filterByName parameter name cannot be empty");
      }
      return appendSearchCriteria(createNameCriteria(Operator.EQUAL, (Object[])name));
    }

    public StaffMemberRequest<T> withName(Operator operator, Object... values){
       return appendSearchCriteria(createNameCriteria(operator, values));
    }

    public StaffMemberRequest<T> withNameIsUnknown(){
       return withName(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withNameIsKnown(){
       return withName(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createNameCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.NAME_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withNameGreaterThan(String name){
       return withName(Operator.GREATER_THAN, name);
    }

    public StaffMemberRequest<T> withNameGreaterThanOrEqualTo(String name){
       return withName(Operator.GREATER_THAN_OR_EQUAL, name);
    }

    public StaffMemberRequest<T> withNameLessThan(String name){
       return withName(Operator.LESS_THAN, name);
    }

    public StaffMemberRequest<T> withNameLessThanOrEqualTo(String name){
       return withName(Operator.LESS_THAN_OR_EQUAL, name);
    }

    public StaffMemberRequest<T> withNameBetween(String startOfName, String endOfName){
       return withName(Operator.BETWEEN, startOfName, endOfName);
    }
    public StaffMemberRequest<T> withNameStartingWith(String name){
       return withName(Operator.BEGIN_WITH, name);
    }
    public StaffMemberRequest<T> withNameContaining(String name){
       return withName(Operator.CONTAIN, name);
    }

    public StaffMemberRequest<T> withNameEndingWith(String name){
       return withName(Operator.END_WITH, name);
    }

    public StaffMemberRequest<T> withNameIs(String name){
       return withName(Operator.EQUAL, name);
    }

    public StaffMemberRequest<T> withNameSoundingLike(String name){
       return withName(Operator.SOUNDS_LIKE, name);
    }



    public StaffMemberRequest<T> filterByEmail(String... email){
      if (email == null || email.length == 0) {
        throw new IllegalArgumentException("filterByEmail parameter email cannot be empty");
      }
      return appendSearchCriteria(createEmailCriteria(Operator.EQUAL, (Object[])email));
    }

    public StaffMemberRequest<T> withEmail(Operator operator, Object... values){
       return appendSearchCriteria(createEmailCriteria(operator, values));
    }

    public StaffMemberRequest<T> withEmailIsUnknown(){
       return withEmail(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withEmailIsKnown(){
       return withEmail(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEmailCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.EMAIL_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withEmailGreaterThan(String email){
       return withEmail(Operator.GREATER_THAN, email);
    }

    public StaffMemberRequest<T> withEmailGreaterThanOrEqualTo(String email){
       return withEmail(Operator.GREATER_THAN_OR_EQUAL, email);
    }

    public StaffMemberRequest<T> withEmailLessThan(String email){
       return withEmail(Operator.LESS_THAN, email);
    }

    public StaffMemberRequest<T> withEmailLessThanOrEqualTo(String email){
       return withEmail(Operator.LESS_THAN_OR_EQUAL, email);
    }

    public StaffMemberRequest<T> withEmailBetween(String startOfEmail, String endOfEmail){
       return withEmail(Operator.BETWEEN, startOfEmail, endOfEmail);
    }
    public StaffMemberRequest<T> withEmailStartingWith(String email){
       return withEmail(Operator.BEGIN_WITH, email);
    }
    public StaffMemberRequest<T> withEmailContaining(String email){
       return withEmail(Operator.CONTAIN, email);
    }

    public StaffMemberRequest<T> withEmailEndingWith(String email){
       return withEmail(Operator.END_WITH, email);
    }

    public StaffMemberRequest<T> withEmailIs(String email){
       return withEmail(Operator.EQUAL, email);
    }

    public StaffMemberRequest<T> withEmailSoundingLike(String email){
       return withEmail(Operator.SOUNDS_LIKE, email);
    }



    public StaffMemberRequest<T> filterByPhone(String... phone){
      if (phone == null || phone.length == 0) {
        throw new IllegalArgumentException("filterByPhone parameter phone cannot be empty");
      }
      return appendSearchCriteria(createPhoneCriteria(Operator.EQUAL, (Object[])phone));
    }

    public StaffMemberRequest<T> withPhone(Operator operator, Object... values){
       return appendSearchCriteria(createPhoneCriteria(operator, values));
    }

    public StaffMemberRequest<T> withPhoneIsUnknown(){
       return withPhone(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withPhoneIsKnown(){
       return withPhone(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPhoneCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.PHONE_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withPhoneGreaterThan(String phone){
       return withPhone(Operator.GREATER_THAN, phone);
    }

    public StaffMemberRequest<T> withPhoneGreaterThanOrEqualTo(String phone){
       return withPhone(Operator.GREATER_THAN_OR_EQUAL, phone);
    }

    public StaffMemberRequest<T> withPhoneLessThan(String phone){
       return withPhone(Operator.LESS_THAN, phone);
    }

    public StaffMemberRequest<T> withPhoneLessThanOrEqualTo(String phone){
       return withPhone(Operator.LESS_THAN_OR_EQUAL, phone);
    }

    public StaffMemberRequest<T> withPhoneBetween(String startOfPhone, String endOfPhone){
       return withPhone(Operator.BETWEEN, startOfPhone, endOfPhone);
    }
    public StaffMemberRequest<T> withPhoneStartingWith(String phone){
       return withPhone(Operator.BEGIN_WITH, phone);
    }
    public StaffMemberRequest<T> withPhoneContaining(String phone){
       return withPhone(Operator.CONTAIN, phone);
    }

    public StaffMemberRequest<T> withPhoneEndingWith(String phone){
       return withPhone(Operator.END_WITH, phone);
    }

    public StaffMemberRequest<T> withPhoneIs(String phone){
       return withPhone(Operator.EQUAL, phone);
    }

    public StaffMemberRequest<T> withPhoneSoundingLike(String phone){
       return withPhone(Operator.SOUNDS_LIKE, phone);
    }



    public StaffMemberRequest<T> filterByHireDate(LocalDate... hireDate){
      if (hireDate == null || hireDate.length == 0) {
        throw new IllegalArgumentException("filterByHireDate parameter hireDate cannot be empty");
      }
      return appendSearchCriteria(createHireDateCriteria(Operator.EQUAL, (Object[])hireDate));
    }

    public StaffMemberRequest<T> withHireDate(Operator operator, Object... values){
       return appendSearchCriteria(createHireDateCriteria(operator, values));
    }

    public StaffMemberRequest<T> withHireDateIsUnknown(){
       return withHireDate(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withHireDateIsKnown(){
       return withHireDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createHireDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.HIRE_DATE_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withHireDateGreaterThan(LocalDate hireDate){
       return withHireDate(Operator.GREATER_THAN, hireDate);
    }

    public StaffMemberRequest<T> withHireDateGreaterThanOrEqualTo(LocalDate hireDate){
       return withHireDate(Operator.GREATER_THAN_OR_EQUAL, hireDate);
    }

    public StaffMemberRequest<T> withHireDateLessThan(LocalDate hireDate){
       return withHireDate(Operator.LESS_THAN, hireDate);
    }

    public StaffMemberRequest<T> withHireDateLessThanOrEqualTo(LocalDate hireDate){
       return withHireDate(Operator.LESS_THAN_OR_EQUAL, hireDate);
    }

    public StaffMemberRequest<T> withHireDateBetween(LocalDate startOfHireDate, LocalDate endOfHireDate){
       return withHireDate(Operator.BETWEEN, startOfHireDate, endOfHireDate);
    }
    public StaffMemberRequest<T> withHireDateBefore(LocalDate hireDate){
       return withHireDate(Operator.LESS_THAN, hireDate);
    }

    public StaffMemberRequest<T> withHireDateBefore(Date hireDate){
       return withHireDate(Operator.LESS_THAN, hireDate);
    }

    public StaffMemberRequest<T> withHireDateAfter(LocalDate hireDate){
       return withHireDate(Operator.GREATER_THAN, hireDate);
    }

    public StaffMemberRequest<T> withHireDateAfter(Date hireDate){
       return withHireDate(Operator.GREATER_THAN, hireDate);
    }

    public StaffMemberRequest<T> withHireDateBetween(Date startOfHireDate, Date endOfHireDate){
       return withHireDate(Operator.BETWEEN, startOfHireDate, endOfHireDate);
    }




    public StaffMemberRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public StaffMemberRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public StaffMemberRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.STATUS_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public StaffMemberRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public StaffMemberRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public StaffMemberRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public StaffMemberRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public StaffMemberRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public StaffMemberRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public StaffMemberRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public StaffMemberRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public StaffMemberRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public StaffMemberRequest<T> filterByDepartment(String... department){
      if (department == null || department.length == 0) {
        throw new IllegalArgumentException("filterByDepartment parameter department cannot be empty");
      }
      return appendSearchCriteria(createDepartmentCriteria(Operator.EQUAL, (Object[])department));
    }

    public StaffMemberRequest<T> withDepartment(Operator operator, Object... values){
       return appendSearchCriteria(createDepartmentCriteria(operator, values));
    }

    public StaffMemberRequest<T> withDepartmentIsUnknown(){
       return withDepartment(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withDepartmentIsKnown(){
       return withDepartment(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDepartmentCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.DEPARTMENT_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withDepartmentGreaterThan(String department){
       return withDepartment(Operator.GREATER_THAN, department);
    }

    public StaffMemberRequest<T> withDepartmentGreaterThanOrEqualTo(String department){
       return withDepartment(Operator.GREATER_THAN_OR_EQUAL, department);
    }

    public StaffMemberRequest<T> withDepartmentLessThan(String department){
       return withDepartment(Operator.LESS_THAN, department);
    }

    public StaffMemberRequest<T> withDepartmentLessThanOrEqualTo(String department){
       return withDepartment(Operator.LESS_THAN_OR_EQUAL, department);
    }

    public StaffMemberRequest<T> withDepartmentBetween(String startOfDepartment, String endOfDepartment){
       return withDepartment(Operator.BETWEEN, startOfDepartment, endOfDepartment);
    }
    public StaffMemberRequest<T> withDepartmentStartingWith(String department){
       return withDepartment(Operator.BEGIN_WITH, department);
    }
    public StaffMemberRequest<T> withDepartmentContaining(String department){
       return withDepartment(Operator.CONTAIN, department);
    }

    public StaffMemberRequest<T> withDepartmentEndingWith(String department){
       return withDepartment(Operator.END_WITH, department);
    }

    public StaffMemberRequest<T> withDepartmentIs(String department){
       return withDepartment(Operator.EQUAL, department);
    }

    public StaffMemberRequest<T> withDepartmentSoundingLike(String department){
       return withDepartment(Operator.SOUNDS_LIKE, department);
    }



    public StaffMemberRequest<T> filterByJobTitle(String... jobTitle){
      if (jobTitle == null || jobTitle.length == 0) {
        throw new IllegalArgumentException("filterByJobTitle parameter jobTitle cannot be empty");
      }
      return appendSearchCriteria(createJobTitleCriteria(Operator.EQUAL, (Object[])jobTitle));
    }

    public StaffMemberRequest<T> withJobTitle(Operator operator, Object... values){
       return appendSearchCriteria(createJobTitleCriteria(operator, values));
    }

    public StaffMemberRequest<T> withJobTitleIsUnknown(){
       return withJobTitle(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withJobTitleIsKnown(){
       return withJobTitle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createJobTitleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.JOB_TITLE_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withJobTitleGreaterThan(String jobTitle){
       return withJobTitle(Operator.GREATER_THAN, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleGreaterThanOrEqualTo(String jobTitle){
       return withJobTitle(Operator.GREATER_THAN_OR_EQUAL, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleLessThan(String jobTitle){
       return withJobTitle(Operator.LESS_THAN, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleLessThanOrEqualTo(String jobTitle){
       return withJobTitle(Operator.LESS_THAN_OR_EQUAL, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleBetween(String startOfJobTitle, String endOfJobTitle){
       return withJobTitle(Operator.BETWEEN, startOfJobTitle, endOfJobTitle);
    }
    public StaffMemberRequest<T> withJobTitleStartingWith(String jobTitle){
       return withJobTitle(Operator.BEGIN_WITH, jobTitle);
    }
    public StaffMemberRequest<T> withJobTitleContaining(String jobTitle){
       return withJobTitle(Operator.CONTAIN, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleEndingWith(String jobTitle){
       return withJobTitle(Operator.END_WITH, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleIs(String jobTitle){
       return withJobTitle(Operator.EQUAL, jobTitle);
    }

    public StaffMemberRequest<T> withJobTitleSoundingLike(String jobTitle){
       return withJobTitle(Operator.SOUNDS_LIKE, jobTitle);
    }



    public StaffMemberRequest<T> filterByManager(StaffMember... manager){
      if (manager == null || manager.length == 0) {
        throw new IllegalArgumentException("filterByManager parameter manager cannot be empty");
      }
      return appendSearchCriteria(createManagerCriteria(Operator.EQUAL, (Object[])manager));
    }

    public StaffMemberRequest<T> withManager(Operator operator, Object... values){
       return appendSearchCriteria(createManagerCriteria(operator, values));
    }

    public StaffMemberRequest<T> withManagerIsUnknown(){
       return withManager(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withManagerIsKnown(){
       return withManager(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createManagerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.MANAGER_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> filterByManager(Long manager){
      if(manager == null){
         return this;
      }
      return withManager(Operator.EQUAL, manager);
    }
    public StaffMemberRequest<T> withManagerMatching(StaffMemberRequest manager){
       return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.MANAGER_PROPERTY, manager, StaffMember.ID_PROPERTY));
    }

    public StaffMemberRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public StaffMemberRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public StaffMemberRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.CREATED_AT_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public StaffMemberRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public StaffMemberRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public StaffMemberRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public StaffMemberRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public StaffMemberRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.UPDATED_AT_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public StaffMemberRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public StaffMemberRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public StaffMemberRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public StaffMemberRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public StaffMemberRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public StaffMemberRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(StaffMember.VERSION_PROPERTY, operator, values);
    }

    public StaffMemberRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public StaffMemberRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public StaffMemberRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public StaffMemberRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public StaffMemberRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public StaffMemberRequest<T> withDispatchPlanListMatching(DispatchPlanRequest dispatchPlanRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, dispatchPlanRequest, DispatchPlan.DRIVER_PROPERTY));
    }

    public StaffMemberRequest<T> withoutDispatchPlanListMatching(DispatchPlanRequest dispatchPlanRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, dispatchPlanRequest, DispatchPlan.DRIVER_PROPERTY)));
    }

    public StaffMemberRequest<T> haveDispatchPlans(){
        return withDispatchPlanListMatching(Q.dispatchPlans().unlimited());
    }

    public StaffMemberRequest<T> haveNoDispatchPlans(){
        return withoutDispatchPlanListMatching(Q.dispatchPlans().unlimited());
    }
    public StaffMemberRequest<T> withStaffMemberListMatching(StaffMemberRequest staffMemberRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, staffMemberRequest, StaffMember.MANAGER_PROPERTY));
    }

    public StaffMemberRequest<T> withoutStaffMemberListMatching(StaffMemberRequest staffMemberRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, staffMemberRequest, StaffMember.MANAGER_PROPERTY)));
    }

    public StaffMemberRequest<T> haveStaffMembers(){
        return withStaffMemberListMatching(Q.staffMembers().unlimited());
    }

    public StaffMemberRequest<T> haveNoStaffMembers(){
        return withoutStaffMemberListMatching(Q.staffMembers().unlimited());
    }
    public StaffMemberRequest<T> withWorkedHoursListMatching(WorkedHoursRequest workedHoursRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, workedHoursRequest, WorkedHours.STAFF_PROPERTY));
    }

    public StaffMemberRequest<T> withoutWorkedHoursListMatching(WorkedHoursRequest workedHoursRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, workedHoursRequest, WorkedHours.STAFF_PROPERTY)));
    }

    public StaffMemberRequest<T> haveWorkedHourses(){
        return withWorkedHoursListMatching(Q.workedHourses().unlimited());
    }

    public StaffMemberRequest<T> haveNoWorkedHourses(){
        return withoutWorkedHoursListMatching(Q.workedHourses().unlimited());
    }
    public StaffMemberRequest<T> withSalarySlipListMatching(SalarySlipRequest salarySlipRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, salarySlipRequest, SalarySlip.STAFF_PROPERTY));
    }

    public StaffMemberRequest<T> withoutSalarySlipListMatching(SalarySlipRequest salarySlipRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, salarySlipRequest, SalarySlip.STAFF_PROPERTY)));
    }

    public StaffMemberRequest<T> haveSalarySlips(){
        return withSalarySlipListMatching(Q.salarySlips().unlimited());
    }

    public StaffMemberRequest<T> haveNoSalarySlips(){
        return withoutSalarySlipListMatching(Q.salarySlips().unlimited());
    }
    public StaffMemberRequest<T> withPerformanceReviewListAsStaffMatching(PerformanceReviewRequest performanceReviewAsStaffRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, performanceReviewAsStaffRequest, PerformanceReview.STAFF_PROPERTY));
    }

    public StaffMemberRequest<T> withoutPerformanceReviewListAsStaffMatching(PerformanceReviewRequest performanceReviewAsStaffRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, performanceReviewAsStaffRequest, PerformanceReview.STAFF_PROPERTY)));
    }

    public StaffMemberRequest<T> havePerformanceReviewsAsStaff(){
        return withPerformanceReviewListAsStaffMatching(Q.performanceReviews().unlimited());
    }

    public StaffMemberRequest<T> haveNoPerformanceReviewsAsStaff(){
        return withoutPerformanceReviewListAsStaffMatching(Q.performanceReviews().unlimited());
    }
    public StaffMemberRequest<T> withPerformanceReviewListAsReviewerMatching(PerformanceReviewRequest performanceReviewAsReviewerRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, performanceReviewAsReviewerRequest, PerformanceReview.REVIEWER_PROPERTY));
    }

    public StaffMemberRequest<T> withoutPerformanceReviewListAsReviewerMatching(PerformanceReviewRequest performanceReviewAsReviewerRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, performanceReviewAsReviewerRequest, PerformanceReview.REVIEWER_PROPERTY)));
    }

    public StaffMemberRequest<T> havePerformanceReviewsAsReviewer(){
        return withPerformanceReviewListAsReviewerMatching(Q.performanceReviews().unlimited());
    }

    public StaffMemberRequest<T> haveNoPerformanceReviewsAsReviewer(){
        return withoutPerformanceReviewListAsReviewerMatching(Q.performanceReviews().unlimited());
    }
    public StaffMemberRequest<T> withSafetyTrainingListMatching(SafetyTrainingRequest safetyTrainingRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, safetyTrainingRequest, SafetyTraining.STAFF_PROPERTY));
    }

    public StaffMemberRequest<T> withoutSafetyTrainingListMatching(SafetyTrainingRequest safetyTrainingRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, safetyTrainingRequest, SafetyTraining.STAFF_PROPERTY)));
    }

    public StaffMemberRequest<T> haveSafetyTrainings(){
        return withSafetyTrainingListMatching(Q.safetyTrainings().unlimited());
    }

    public StaffMemberRequest<T> haveNoSafetyTrainings(){
        return withoutSafetyTrainingListMatching(Q.safetyTrainings().unlimited());
    }
    public StaffMemberRequest<T> withExpenseItemListMatching(ExpenseItemRequest expenseItemRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, expenseItemRequest, ExpenseItem.STAFF_MEMBER_PROPERTY));
    }

    public StaffMemberRequest<T> withoutExpenseItemListMatching(ExpenseItemRequest expenseItemRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(StaffMember.ID_PROPERTY, expenseItemRequest, ExpenseItem.STAFF_MEMBER_PROPERTY)));
    }

    public StaffMemberRequest<T> haveExpenseItems(){
        return withExpenseItemListMatching(Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> haveNoExpenseItems(){
        return withoutExpenseItemListMatching(Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> count(){
        super.count();
        return this;
    }
    public StaffMemberRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public StaffMemberRequest<T> groupByManagerWithDetails(){
       return groupByManagerWithDetails(Q.staffMembers().unlimited());
    }

    public StaffMemberRequest<T> groupByManagerWithDetails(StaffMemberRequest subRequest){
       aggregate(StaffMember.MANAGER_PROPERTY, subRequest);
       return this;
    }




    public StaffMemberRequest<T> groupByDispatchPlansWithDetails(DispatchPlanRequest subRequest){
       aggregate(StaffMember.DISPATCH_PLAN_LIST_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupByStaffMembersWithDetails(StaffMemberRequest subRequest){
       aggregate(StaffMember.STAFF_MEMBER_LIST_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupByWorkedHoursesWithDetails(WorkedHoursRequest subRequest){
       aggregate(StaffMember.WORKED_HOURS_LIST_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupBySalarySlipsWithDetails(SalarySlipRequest subRequest){
       aggregate(StaffMember.SALARY_SLIP_LIST_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupByPerformanceReviewsAsStaffWithDetails(PerformanceReviewRequest subRequest){
       aggregate(StaffMember.PERFORMANCE_REVIEW_LIST_AS_STAFF_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupByPerformanceReviewsAsReviewerWithDetails(PerformanceReviewRequest subRequest){
       aggregate(StaffMember.PERFORMANCE_REVIEW_LIST_AS_REVIEWER_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupBySafetyTrainingsWithDetails(SafetyTrainingRequest subRequest){
       aggregate(StaffMember.SAFETY_TRAINING_LIST_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupByExpenseItemsWithDetails(ExpenseItemRequest subRequest){
       aggregate(StaffMember.EXPENSE_ITEM_LIST_PROPERTY, subRequest);
       return this;
    }

    public StaffMemberRequest<T> groupById(){
       groupBy(StaffMember.ID_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByIdAs(String retName){
       groupBy(retName, StaffMember.ID_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.ID_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByName(){
       groupBy(StaffMember.NAME_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByNameAs(String retName){
       groupBy(retName, StaffMember.NAME_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByNameWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.NAME_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByEmail(){
       groupBy(StaffMember.EMAIL_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByEmailAs(String retName){
       groupBy(retName, StaffMember.EMAIL_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByEmailWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.EMAIL_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByPhone(){
       groupBy(StaffMember.PHONE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByPhoneAs(String retName){
       groupBy(retName, StaffMember.PHONE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByPhoneWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.PHONE_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByHireDate(){
       groupBy(StaffMember.HIRE_DATE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByHireDateAs(String retName){
       groupBy(retName, StaffMember.HIRE_DATE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByHireDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.HIRE_DATE_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByStatus(){
       groupBy(StaffMember.STATUS_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByStatusAs(String retName){
       groupBy(retName, StaffMember.STATUS_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.STATUS_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByDepartment(){
       groupBy(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByDepartmentAs(String retName){
       groupBy(retName, StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByDepartmentWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.DEPARTMENT_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByJobTitle(){
       groupBy(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByJobTitleAs(String retName){
       groupBy(retName, StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByJobTitleWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.JOB_TITLE_PROPERTY, function);
       return this;
    }
    public StaffMemberRequest<T> groupByManagerWith(StaffMemberRequest subRequest){
       groupBy(StaffMember.MANAGER_PROPERTY, subRequest);
       return this;
    }
    public StaffMemberRequest<T> groupByManager(){
       groupBy(StaffMember.MANAGER_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByManagerAs(String retName){
       groupBy(retName, StaffMember.MANAGER_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByManagerWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.MANAGER_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByCreatedAt(){
       groupBy(StaffMember.CREATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, StaffMember.CREATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.CREATED_AT_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByUpdatedAt(){
       groupBy(StaffMember.UPDATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, StaffMember.UPDATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public StaffMemberRequest<T> groupByVersion(){
       groupBy(StaffMember.VERSION_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByVersionAs(String retName){
       groupBy(retName, StaffMember.VERSION_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, StaffMember.VERSION_PROPERTY, function);
       return this;
    }



    public StaffMemberRequest<T> orderByIdAscending(){
       addOrderByAscending(StaffMember.ID_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByIdDescending(){
       addOrderByDescending(StaffMember.ID_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByNameAscending(){
       addOrderByAscending(StaffMember.NAME_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByNameDescending(){
       addOrderByDescending(StaffMember.NAME_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByNameAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StaffMember.NAME_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByNameDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StaffMember.NAME_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByEmailAscending(){
       addOrderByAscending(StaffMember.EMAIL_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByEmailDescending(){
       addOrderByDescending(StaffMember.EMAIL_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByEmailAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StaffMember.EMAIL_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByEmailDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StaffMember.EMAIL_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByPhoneAscending(){
       addOrderByAscending(StaffMember.PHONE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByPhoneDescending(){
       addOrderByDescending(StaffMember.PHONE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByPhoneAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StaffMember.PHONE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByPhoneDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StaffMember.PHONE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByHireDateAscending(){
       addOrderByAscending(StaffMember.HIRE_DATE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByHireDateDescending(){
       addOrderByDescending(StaffMember.HIRE_DATE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByStatusAscending(){
       addOrderByAscending(StaffMember.STATUS_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByStatusDescending(){
       addOrderByDescending(StaffMember.STATUS_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StaffMember.STATUS_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StaffMember.STATUS_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByDepartmentAscending(){
       addOrderByAscending(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByDepartmentDescending(){
       addOrderByDescending(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByDepartmentAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByDepartmentDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StaffMember.DEPARTMENT_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByJobTitleAscending(){
       addOrderByAscending(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByJobTitleDescending(){
       addOrderByDescending(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByJobTitleAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByJobTitleDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(StaffMember.JOB_TITLE_PROPERTY);
       return this;
    }
    public StaffMemberRequest<T> orderByManagerAscending(){
       addOrderByAscending(StaffMember.MANAGER_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByManagerDescending(){
       addOrderByDescending(StaffMember.MANAGER_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(StaffMember.CREATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(StaffMember.CREATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(StaffMember.UPDATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(StaffMember.UPDATED_AT_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByVersionAscending(){
       addOrderByAscending(StaffMember.VERSION_PROPERTY);
       return this;
    }

    public StaffMemberRequest<T> orderByVersionDescending(){
       addOrderByDescending(StaffMember.VERSION_PROPERTY);
       return this;
    }


    public StaffMemberRequest<T> statsFromDispatchPlansAs(String name, DispatchPlanRequest subRequest){
       return statsFromDispatchPlansAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromDispatchPlansAs(String name, DispatchPlanRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(DispatchPlan.DRIVER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromDispatchPlans(DispatchPlanRequest subRequest){
       return statsFromDispatchPlansAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromStaffMembersAs(String name, StaffMemberRequest subRequest){
       return statsFromStaffMembersAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromStaffMembersAs(String name, StaffMemberRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(StaffMember.MANAGER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromStaffMembers(StaffMemberRequest subRequest){
       return statsFromStaffMembersAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromWorkedHoursesAs(String name, WorkedHoursRequest subRequest){
       return statsFromWorkedHoursesAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromWorkedHoursesAs(String name, WorkedHoursRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(WorkedHours.STAFF_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromWorkedHourses(WorkedHoursRequest subRequest){
       return statsFromWorkedHoursesAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromSalarySlipsAs(String name, SalarySlipRequest subRequest){
       return statsFromSalarySlipsAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromSalarySlipsAs(String name, SalarySlipRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(SalarySlip.STAFF_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromSalarySlips(SalarySlipRequest subRequest){
       return statsFromSalarySlipsAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromPerformanceReviewsAsStaffAs(String name, PerformanceReviewRequest subRequest){
       return statsFromPerformanceReviewsAsStaffAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromPerformanceReviewsAsStaffAs(String name, PerformanceReviewRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(PerformanceReview.STAFF_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromPerformanceReviewsAsStaff(PerformanceReviewRequest subRequest){
       return statsFromPerformanceReviewsAsStaffAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromPerformanceReviewsAsReviewerAs(String name, PerformanceReviewRequest subRequest){
       return statsFromPerformanceReviewsAsReviewerAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromPerformanceReviewsAsReviewerAs(String name, PerformanceReviewRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(PerformanceReview.REVIEWER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromPerformanceReviewsAsReviewer(PerformanceReviewRequest subRequest){
       return statsFromPerformanceReviewsAsReviewerAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromSafetyTrainingsAs(String name, SafetyTrainingRequest subRequest){
       return statsFromSafetyTrainingsAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromSafetyTrainingsAs(String name, SafetyTrainingRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(SafetyTraining.STAFF_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromSafetyTrainings(SafetyTrainingRequest subRequest){
       return statsFromSafetyTrainingsAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest<T> statsFromExpenseItemsAs(String name, ExpenseItemRequest subRequest){
       return statsFromExpenseItemsAs(name, subRequest, false);
    }

    public StaffMemberRequest<T> statsFromExpenseItemsAs(String name, ExpenseItemRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(ExpenseItem.STAFF_MEMBER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public StaffMemberRequest<T> statsFromExpenseItems(ExpenseItemRequest subRequest){
       return statsFromExpenseItemsAs(REFINEMENTS, subRequest);
    }
    public StaffMemberRequest rollUpToManager(){
       StaffMemberRequest manager = Q.staffMembers().unlimited();
       this.withManagerMatching(manager)
           .groupByManagerWith(manager);
       return manager;
    }




    public StaffMemberRequest<T> countDispatchPlans(){
        return countDispatchPlansAs("Count");
    }

    public StaffMemberRequest<T> countDispatchPlansAs(String name){
        return countDispatchPlansWith(name, Q.dispatchPlans().unlimited());
    }

    public StaffMemberRequest<T> countDispatchPlansWith(String name, DispatchPlanRequest subRequest){
        return statsFromDispatchPlansAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countStaffMembers(){
        return countStaffMembersAs("Count");
    }

    public StaffMemberRequest<T> countStaffMembersAs(String name){
        return countStaffMembersWith(name, Q.staffMembers().unlimited());
    }

    public StaffMemberRequest<T> countStaffMembersWith(String name, StaffMemberRequest subRequest){
        return statsFromStaffMembersAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countWorkedHourses(){
        return countWorkedHoursesAs("Count");
    }

    public StaffMemberRequest<T> countWorkedHoursesAs(String name){
        return countWorkedHoursesWith(name, Q.workedHourses().unlimited());
    }

    public StaffMemberRequest<T> countWorkedHoursesWith(String name, WorkedHoursRequest subRequest){
        return statsFromWorkedHoursesAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countSalarySlips(){
        return countSalarySlipsAs("Count");
    }

    public StaffMemberRequest<T> countSalarySlipsAs(String name){
        return countSalarySlipsWith(name, Q.salarySlips().unlimited());
    }

    public StaffMemberRequest<T> countSalarySlipsWith(String name, SalarySlipRequest subRequest){
        return statsFromSalarySlipsAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countPerformanceReviewsAsStaff(){
        return countPerformanceReviewsAsStaffAs("Count");
    }

    public StaffMemberRequest<T> countPerformanceReviewsAsStaffAs(String name){
        return countPerformanceReviewsAsStaffWith(name, Q.performanceReviews().unlimited());
    }

    public StaffMemberRequest<T> countPerformanceReviewsAsStaffWith(String name, PerformanceReviewRequest subRequest){
        return statsFromPerformanceReviewsAsStaffAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countPerformanceReviewsAsReviewer(){
        return countPerformanceReviewsAsReviewerAs("Count");
    }

    public StaffMemberRequest<T> countPerformanceReviewsAsReviewerAs(String name){
        return countPerformanceReviewsAsReviewerWith(name, Q.performanceReviews().unlimited());
    }

    public StaffMemberRequest<T> countPerformanceReviewsAsReviewerWith(String name, PerformanceReviewRequest subRequest){
        return statsFromPerformanceReviewsAsReviewerAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countSafetyTrainings(){
        return countSafetyTrainingsAs("Count");
    }

    public StaffMemberRequest<T> countSafetyTrainingsAs(String name){
        return countSafetyTrainingsWith(name, Q.safetyTrainings().unlimited());
    }

    public StaffMemberRequest<T> countSafetyTrainingsWith(String name, SafetyTrainingRequest subRequest){
        return statsFromSafetyTrainingsAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> countExpenseItems(){
        return countExpenseItemsAs("Count");
    }

    public StaffMemberRequest<T> countExpenseItemsAs(String name){
        return countExpenseItemsWith(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> countExpenseItemsWith(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.count(), true);
    }
    public StaffMemberRequest<T> minAmountOfExpenseItems(){
        return minAmountOfExpenseItemsAs("minAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> minAmountOfExpenseItemsAs(String name){
        return minAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> minAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.minAmount(), true);
    }
    public StaffMemberRequest<T> maxAmountOfExpenseItems(){
        return maxAmountOfExpenseItemsAs("maxAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> maxAmountOfExpenseItemsAs(String name){
        return maxAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> maxAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.maxAmount(), true);
    }
    public StaffMemberRequest<T> sumAmountOfExpenseItems(){
        return sumAmountOfExpenseItemsAs("sumAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> sumAmountOfExpenseItemsAs(String name){
        return sumAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> sumAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.sumAmount(), true);
    }
    public StaffMemberRequest<T> avgAmountOfExpenseItems(){
        return avgAmountOfExpenseItemsAs("avgAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> avgAmountOfExpenseItemsAs(String name){
        return avgAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> avgAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.avgAmount(), true);
    }
    public StaffMemberRequest<T> standardDeviationAmountOfExpenseItems(){
        return standardDeviationAmountOfExpenseItemsAs("stdDevAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> standardDeviationAmountOfExpenseItemsAs(String name){
        return standardDeviationAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> standardDeviationAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.standardDeviationAmount(), true);
    }
    public StaffMemberRequest<T> squareRootOfPopulationStandardDeviationAmountOfExpenseItems(){
        return squareRootOfPopulationStandardDeviationAmountOfExpenseItemsAs("stdDevPopAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> squareRootOfPopulationStandardDeviationAmountOfExpenseItemsAs(String name){
        return squareRootOfPopulationStandardDeviationAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> squareRootOfPopulationStandardDeviationAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.squareRootOfPopulationStandardDeviationAmount(), true);
    }
    public StaffMemberRequest<T> sampleVarianceAmountOfExpenseItems(){
        return sampleVarianceAmountOfExpenseItemsAs("varSampAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> sampleVarianceAmountOfExpenseItemsAs(String name){
        return sampleVarianceAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> sampleVarianceAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.sampleVarianceAmount(), true);
    }
    public StaffMemberRequest<T> samplePopulationVarianceAmountOfExpenseItems(){
        return samplePopulationVarianceAmountOfExpenseItemsAs("varPopAmountOfExpenseItems");
    }

    public StaffMemberRequest<T> samplePopulationVarianceAmountOfExpenseItemsAs(String name){
        return samplePopulationVarianceAmountOfExpenseItemsAs(name, Q.expenseItems().unlimited());
    }

    public StaffMemberRequest<T> samplePopulationVarianceAmountOfExpenseItemsAs(String name, ExpenseItemRequest subRequest){
        return statsFromExpenseItemsAs(name, subRequest.samplePopulationVarianceAmount(), true);
    }

   public StaffMemberRequest<T> facetByManagerAs(String facetName, StaffMemberRequest manager){
       return facetByManagerAs(facetName, manager, true);
   }

   public StaffMemberRequest<T> facetByManagerAs(String facetName, StaffMemberRequest manager, boolean includeAllFacets){
       addFacet(facetName, StaffMember.MANAGER_PROPERTY, manager, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public StaffMemberRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public StaffMemberRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public StaffMemberRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public StaffMemberRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public StaffMemberRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}