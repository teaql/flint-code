package com.doublechaintech.enterpriselogisticsservice.staffmember;

import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanChecker;
import com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItem;
import com.doublechaintech.enterpriselogisticsservice.expenseitem.ExpenseItemChecker;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewChecker;
import com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTraining;
import com.doublechaintech.enterpriselogisticsservice.safetytraining.SafetyTrainingChecker;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipChecker;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class StaffMemberChecker implements Checker<StaffMember>{

    public String type(){
        return StaffMember.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, StaffMember staffMember, ObjectLocation _parentLocation){
        if(needCheck(_ctx, staffMember)){
            markAsChecked(_ctx, staffMember);
            doCheck(_ctx, staffMember, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, StaffMember staffMember, ObjectLocation _parentLocation){
      if((staffMember == null)){
         return;
      }
      if(staffMember.newItem()){
        if(staffMember.getCreatedAt() == null){
           staffMember.updateCreatedAt(java.time.LocalDateTime.now());
        }if(staffMember.getUpdatedAt() == null){
           staffMember.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(staffMember.updateItem()){
        staffMember.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, staffMember.getProperty(StaffMember.NAME_PROPERTY), newLocation(_parentLocation, StaffMember.NAME_PROPERTY));
      checkEmail(_ctx, staffMember.getProperty(StaffMember.EMAIL_PROPERTY), newLocation(_parentLocation, StaffMember.EMAIL_PROPERTY));
      checkPhone(_ctx, staffMember.getProperty(StaffMember.PHONE_PROPERTY), newLocation(_parentLocation, StaffMember.PHONE_PROPERTY));
      checkHireDate(_ctx, staffMember.getProperty(StaffMember.HIRE_DATE_PROPERTY), newLocation(_parentLocation, StaffMember.HIRE_DATE_PROPERTY));
      checkStatus(_ctx, staffMember.getProperty(StaffMember.STATUS_PROPERTY), newLocation(_parentLocation, StaffMember.STATUS_PROPERTY));
      checkDepartment(_ctx, staffMember.getProperty(StaffMember.DEPARTMENT_PROPERTY), newLocation(_parentLocation, StaffMember.DEPARTMENT_PROPERTY));
      checkJobTitle(_ctx, staffMember.getProperty(StaffMember.JOB_TITLE_PROPERTY), newLocation(_parentLocation, StaffMember.JOB_TITLE_PROPERTY));
      checkManager(_ctx, staffMember.getProperty(StaffMember.MANAGER_PROPERTY), newLocation(_parentLocation, StaffMember.MANAGER_PROPERTY));
      checkCreatedAt(_ctx, staffMember.getProperty(StaffMember.CREATED_AT_PROPERTY), newLocation(_parentLocation, StaffMember.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, staffMember.getProperty(StaffMember.UPDATED_AT_PROPERTY), newLocation(_parentLocation, StaffMember.UPDATED_AT_PROPERTY));
      for(int i = 0; staffMember.getDispatchPlanList() != null && i < staffMember.getDispatchPlanList().size(); i++){
         DispatchPlan dispatchPlan = staffMember.getDispatchPlanList().get(i);
         new DispatchPlanChecker().checkAndFix(_ctx, dispatchPlan, newLocation(_parentLocation, StaffMember.DISPATCH_PLAN_LIST_PROPERTY, i));
      }
      for(int i = 0; staffMember.getStaffMemberList() != null && i < staffMember.getStaffMemberList().size(); i++){
         StaffMember staffMember = staffMember.getStaffMemberList().get(i);
         new StaffMemberChecker().checkAndFix(_ctx, staffMember, newLocation(_parentLocation, StaffMember.STAFF_MEMBER_LIST_PROPERTY, i));
      }
      for(int i = 0; staffMember.getWorkedHoursList() != null && i < staffMember.getWorkedHoursList().size(); i++){
         WorkedHours workedHours = staffMember.getWorkedHoursList().get(i);
         new WorkedHoursChecker().checkAndFix(_ctx, workedHours, newLocation(_parentLocation, StaffMember.WORKED_HOURS_LIST_PROPERTY, i));
      }
      for(int i = 0; staffMember.getSalarySlipList() != null && i < staffMember.getSalarySlipList().size(); i++){
         SalarySlip salarySlip = staffMember.getSalarySlipList().get(i);
         new SalarySlipChecker().checkAndFix(_ctx, salarySlip, newLocation(_parentLocation, StaffMember.SALARY_SLIP_LIST_PROPERTY, i));
      }
      for(int i = 0; staffMember.getPerformanceReviewListAsStaff() != null && i < staffMember.getPerformanceReviewListAsStaff().size(); i++){
         PerformanceReview performanceReviewAsStaff = staffMember.getPerformanceReviewListAsStaff().get(i);
         new PerformanceReviewChecker().checkAndFix(_ctx, performanceReviewAsStaff, newLocation(_parentLocation, StaffMember.PERFORMANCE_REVIEW_LIST_AS_STAFF_PROPERTY, i));
      }
      for(int i = 0; staffMember.getPerformanceReviewListAsReviewer() != null && i < staffMember.getPerformanceReviewListAsReviewer().size(); i++){
         PerformanceReview performanceReviewAsReviewer = staffMember.getPerformanceReviewListAsReviewer().get(i);
         new PerformanceReviewChecker().checkAndFix(_ctx, performanceReviewAsReviewer, newLocation(_parentLocation, StaffMember.PERFORMANCE_REVIEW_LIST_AS_REVIEWER_PROPERTY, i));
      }
      for(int i = 0; staffMember.getSafetyTrainingList() != null && i < staffMember.getSafetyTrainingList().size(); i++){
         SafetyTraining safetyTraining = staffMember.getSafetyTrainingList().get(i);
         new SafetyTrainingChecker().checkAndFix(_ctx, safetyTraining, newLocation(_parentLocation, StaffMember.SAFETY_TRAINING_LIST_PROPERTY, i));
      }
      for(int i = 0; staffMember.getExpenseItemList() != null && i < staffMember.getExpenseItemList().size(); i++){
         ExpenseItem expenseItem = staffMember.getExpenseItemList().get(i);
         new ExpenseItemChecker().checkAndFix(_ctx, expenseItem, newLocation(_parentLocation, StaffMember.EXPENSE_ITEM_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkEmail(UserContext _ctx, String email, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, email);
    if((email == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, email);

    }
    public void checkPhone(UserContext _ctx, String phone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, phone);
    if((phone == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, phone);

    }
    public void checkHireDate(UserContext _ctx, LocalDate hireDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, hireDate);
    if((hireDate == null)){
        return;
    }
    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkDepartment(UserContext _ctx, String department, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, department);
    if((department == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, department);

    }
    public void checkJobTitle(UserContext _ctx, String jobTitle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, jobTitle);
    if((jobTitle == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, jobTitle);

    }
    public void checkManager(UserContext _ctx, StaffMember manager, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, manager);
    if((manager == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, manager, _parentLocation);
    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}