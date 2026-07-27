package com.doublechaintech.enterpriselogisticsservice.staffmember;

import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanListExpression;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReviewListExpression;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlipListExpression;
import com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead;
import com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLeadListExpression;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class StaffMemberExpression<T, E, U extends StaffMember> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public StaffMemberExpression(Expression<T, U> expression){
        super(expression);
    }

    public StaffMemberExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public StaffMemberExpression<T, U, U> updateId(Long id){
        return new StaffMemberExpression(this, $it -> {((StaffMember)$it).__internalSet("id", id); return this;});
     }

     public StaffMemberExpression<T, U, U> save(UserContext userContext){
        return new StaffMemberExpression(this, $it -> ((StaffMember)$it).auditAs("Saved by Expression").save(userContext));
     }

     public StaffMemberExpression<T, U, U> save(String intent, UserContext userContext){
        return new StaffMemberExpression(this, $it -> ((StaffMember)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(StaffMember::getName);
    }
    public StaffMemberExpression<T, U, U> updateName(String name){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateName(name));
    }

    public Expression<T, String> getEmail(){
       return apply(StaffMember::getEmail);
    }
    public StaffMemberExpression<T, U, U> updateEmail(String email){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateEmail(email));
    }

    public Expression<T, String> getPhone(){
       return apply(StaffMember::getPhone);
    }
    public StaffMemberExpression<T, U, U> updatePhone(String phone){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updatePhone(phone));
    }

    public Expression<T, LocalDate> getHireDate(){
       return apply(StaffMember::getHireDate);
    }
    public StaffMemberExpression<T, U, U> updateHireDate(LocalDate hireDate){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateHireDate(hireDate));
    }

    public Expression<T, String> getStatus(){
       return apply(StaffMember::getStatus);
    }
    public StaffMemberExpression<T, U, U> updateStatus(String status){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateStatus(status));
    }

    public Expression<T, String> getDepartment(){
       return apply(StaffMember::getDepartment);
    }
    public StaffMemberExpression<T, U, U> updateDepartment(String department){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateDepartment(department));
    }

    public Expression<T, String> getJobTitle(){
       return apply(StaffMember::getJobTitle);
    }
    public StaffMemberExpression<T, U, U> updateJobTitle(String jobTitle){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateJobTitle(jobTitle));
    }

    public StaffMemberExpression<T, U, StaffMember> getManager(){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).getManager());
    }

    public StaffMemberExpression<T, U, U> updateManager(StaffMember manager){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateManager(manager));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(StaffMember::getCreatedAt);
    }
    public StaffMemberExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(StaffMember::getUpdatedAt);
    }
    public StaffMemberExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).updateUpdatedAt(updatedAt));
    }

    public DispatchPlanListExpression<T, U, DispatchPlan> getDispatchPlanList(){
        return new DispatchPlanListExpression(this, $it ->  ((StaffMember)$it).getDispatchPlanList());
    }
    public StaffMemberListExpression<T, U, StaffMember> getStaffMemberList(){
        return new StaffMemberListExpression(this, $it ->  ((StaffMember)$it).getStaffMemberList());
    }
    public WorkedHoursListExpression<T, U, WorkedHours> getWorkedHoursList(){
        return new WorkedHoursListExpression(this, $it ->  ((StaffMember)$it).getWorkedHoursList());
    }
    public SalarySlipListExpression<T, U, SalarySlip> getSalarySlipList(){
        return new SalarySlipListExpression(this, $it ->  ((StaffMember)$it).getSalarySlipList());
    }
    public PerformanceReviewListExpression<T, U, PerformanceReview> getPerformanceReviewListAsStaff(){
        return new PerformanceReviewListExpression(this, $it ->  ((StaffMember)$it).getPerformanceReviewListAsStaff());
    }
    public PerformanceReviewListExpression<T, U, PerformanceReview> getPerformanceReviewListAsReviewer(){
        return new PerformanceReviewListExpression(this, $it ->  ((StaffMember)$it).getPerformanceReviewListAsReviewer());
    }
    public SalesLeadListExpression<T, U, SalesLead> getSalesLeadList(){
        return new SalesLeadListExpression(this, $it ->  ((StaffMember)$it).getSalesLeadList());
    }
    public StaffMemberExpression<T, U, U> addDispatchPlan(DispatchPlan dispatchPlan){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addDispatchPlan(dispatchPlan));
    }
    public StaffMemberExpression<T, U, U> addStaffMember(StaffMember staffMember){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addStaffMember(staffMember));
    }
    public StaffMemberExpression<T, U, U> addWorkedHours(WorkedHours workedHours){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addWorkedHours(workedHours));
    }
    public StaffMemberExpression<T, U, U> addSalarySlip(SalarySlip salarySlip){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addSalarySlip(salarySlip));
    }
    public StaffMemberExpression<T, U, U> addPerformanceReviewAsStaff(PerformanceReview performanceReview){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addPerformanceReviewAsStaff(performanceReview));
    }
    public StaffMemberExpression<T, U, U> addPerformanceReviewAsReviewer(PerformanceReview performanceReview){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addPerformanceReviewAsReviewer(performanceReview));
    }
    public StaffMemberExpression<T, U, U> addSalesLead(SalesLead salesLead){
       return new StaffMemberExpression(this, $it ->  ((StaffMember)$it).addSalesLead(salesLead));
    }
}