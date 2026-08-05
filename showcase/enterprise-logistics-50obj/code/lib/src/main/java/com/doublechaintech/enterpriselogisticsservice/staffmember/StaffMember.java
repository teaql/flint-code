package com.doublechaintech.enterpriselogisticsservice.staffmember;

import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.performancereview.PerformanceReview;
import com.doublechaintech.enterpriselogisticsservice.salaryslip.SalarySlip;
import com.doublechaintech.enterpriselogisticsservice.saleslead.SalesLead;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class StaffMember extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "StaffMember";

    public static final String NAME_PROPERTY = "name";
    public static final String EMAIL_PROPERTY = "email";
    public static final String PHONE_PROPERTY = "phone";
    public static final String HIRE_DATE_PROPERTY = "hireDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String DEPARTMENT_PROPERTY = "department";
    public static final String JOB_TITLE_PROPERTY = "jobTitle";
    public static final String MANAGER_PROPERTY = "manager";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String DISPATCH_PLAN_LIST_PROPERTY = "dispatchPlanList";
    public static final String STAFF_MEMBER_LIST_PROPERTY = "staffMemberList";
    public static final String WORKED_HOURS_LIST_PROPERTY = "workedHoursList";
    public static final String SALARY_SLIP_LIST_PROPERTY = "salarySlipList";
    public static final String PERFORMANCE_REVIEW_LIST_AS_STAFF_PROPERTY = "performanceReviewListAsStaff";
    public static final String PERFORMANCE_REVIEW_LIST_AS_REVIEWER_PROPERTY = "performanceReviewListAsReviewer";
    public static final String SALES_LEAD_LIST_PROPERTY = "salesLeadList";
    private String name;
    private String email;
    private String phone;
    private LocalDate hireDate;
    private String status;
    private String department;
    private String jobTitle;
    private StaffMember manager;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<DispatchPlan> dispatchPlanList;
    private SmartList<StaffMember> staffMemberList;
    private SmartList<WorkedHours> workedHoursList;
    private SmartList<SalarySlip> salarySlipList;
    private SmartList<PerformanceReview> performanceReviewListAsStaff;
    private SmartList<PerformanceReview> performanceReviewListAsReviewer;
    private SmartList<SalesLead> salesLeadList;

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public LocalDate getHireDate(){
        return this.hireDate;
    }
    public String getStatus(){
        return this.status;
    }
    public String getDepartment(){
        return this.department;
    }
    public String getJobTitle(){
        return this.jobTitle;
    }
    public StaffMember getManager(){
        return this.manager;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SmartList<DispatchPlan> getDispatchPlanList(){
        return this.dispatchPlanList;
    }
    public SmartList<StaffMember> getStaffMemberList(){
        return this.staffMemberList;
    }
    public SmartList<WorkedHours> getWorkedHoursList(){
        return this.workedHoursList;
    }
    public SmartList<SalarySlip> getSalarySlipList(){
        return this.salarySlipList;
    }
    public SmartList<PerformanceReview> getPerformanceReviewListAsStaff(){
        return this.performanceReviewListAsStaff;
    }
    public SmartList<PerformanceReview> getPerformanceReviewListAsReviewer(){
        return this.performanceReviewListAsReviewer;
    }
    public SmartList<SalesLead> getSalesLeadList(){
        return this.salesLeadList;
    }
    public StaffMember updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public StaffMember updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public StaffMember updatePhone(String phone){
        phone = (phone == null ? null : phone.trim());
        if(Objects.equals(this.phone, phone)){
            return this;
        }
        handleUpdate(PHONE_PROPERTY, getPhone(), phone);
        this.phone = phone;
        return this;
    }
    public StaffMember updateHireDate(LocalDate hireDate){
        if(Objects.equals(this.hireDate, hireDate)){
            return this;
        }
        handleUpdate(HIRE_DATE_PROPERTY, getHireDate(), hireDate);
        this.hireDate = hireDate;
        return this;
    }
    public StaffMember updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public StaffMember updateDepartment(String department){
        department = (department == null ? null : department.trim());
        if(Objects.equals(this.department, department)){
            return this;
        }
        handleUpdate(DEPARTMENT_PROPERTY, getDepartment(), department);
        this.department = department;
        return this;
    }
    public StaffMember updateJobTitle(String jobTitle){
        jobTitle = (jobTitle == null ? null : jobTitle.trim());
        if(Objects.equals(this.jobTitle, jobTitle)){
            return this;
        }
        handleUpdate(JOB_TITLE_PROPERTY, getJobTitle(), jobTitle);
        this.jobTitle = jobTitle;
        return this;
    }
    public StaffMember updateManager(StaffMember manager){
        if(Objects.equals(this.manager, manager)){
            return this;
        }
        handleUpdate(MANAGER_PROPERTY, getManager(), manager);
        this.manager = manager;
        return this;
    }
    public StaffMember updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public StaffMember updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public StaffMember addDispatchPlan(DispatchPlan dispatchPlan){
        if (dispatchPlan == null){
            return this;
        }

        if(null == this.dispatchPlanList){
            this.dispatchPlanList = new SmartList<>();
        }

        this.dispatchPlanList.add(dispatchPlan);
        dispatchPlan.cacheRelation(DispatchPlan.DRIVER_PROPERTY, this);
        return this;
    }
    public StaffMember addStaffMember(StaffMember staffMember){
        if (staffMember == null){
            return this;
        }

        if(null == this.staffMemberList){
            this.staffMemberList = new SmartList<>();
        }

        this.staffMemberList.add(staffMember);
        staffMember.cacheRelation(StaffMember.MANAGER_PROPERTY, this);
        return this;
    }
    public StaffMember addWorkedHours(WorkedHours workedHours){
        if (workedHours == null){
            return this;
        }

        if(null == this.workedHoursList){
            this.workedHoursList = new SmartList<>();
        }

        this.workedHoursList.add(workedHours);
        workedHours.cacheRelation(WorkedHours.STAFF_PROPERTY, this);
        return this;
    }
    public StaffMember addSalarySlip(SalarySlip salarySlip){
        if (salarySlip == null){
            return this;
        }

        if(null == this.salarySlipList){
            this.salarySlipList = new SmartList<>();
        }

        this.salarySlipList.add(salarySlip);
        salarySlip.cacheRelation(SalarySlip.STAFF_PROPERTY, this);
        return this;
    }
    public StaffMember addPerformanceReviewAsStaff(PerformanceReview performanceReview){
        if (performanceReview == null){
            return this;
        }

        if(null == this.performanceReviewListAsStaff){
            this.performanceReviewListAsStaff = new SmartList<>();
        }

        this.performanceReviewListAsStaff.add(performanceReview);
        performanceReview.cacheRelation(PerformanceReview.STAFF_PROPERTY, this);
        return this;
    }
    public StaffMember addPerformanceReviewAsReviewer(PerformanceReview performanceReview){
        if (performanceReview == null){
            return this;
        }

        if(null == this.performanceReviewListAsReviewer){
            this.performanceReviewListAsReviewer = new SmartList<>();
        }

        this.performanceReviewListAsReviewer.add(performanceReview);
        performanceReview.cacheRelation(PerformanceReview.REVIEWER_PROPERTY, this);
        return this;
    }
    public StaffMember addSalesLead(SalesLead salesLead){
        if (salesLead == null){
            return this;
        }

        if(null == this.salesLeadList){
            this.salesLeadList = new SmartList<>();
        }

        this.salesLeadList.add(salesLead);
        salesLead.cacheRelation(SalesLead.ASSIGNED_TO_PROPERTY, this);
        return this;
    }

    public static StaffMember refer(Long id){
        StaffMember refer = new StaffMember();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public StaffMember comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<StaffMember> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (value == null ? null : ((String)value).trim()); break;

            case "hireDate": this.hireDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "department": this.department = (value == null ? null : ((String)value).trim()); break;

            case "jobTitle": this.jobTitle = (value == null ? null : ((String)value).trim()); break;

            case "manager": this.manager = (StaffMember) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "dispatchPlanList": this.dispatchPlanList = (SmartList<DispatchPlan>) value; break;
            case "staffMemberList": this.staffMemberList = (SmartList<StaffMember>) value; break;
            case "workedHoursList": this.workedHoursList = (SmartList<WorkedHours>) value; break;
            case "salarySlipList": this.salarySlipList = (SmartList<SalarySlip>) value; break;
            case "performanceReviewListAsStaff": this.performanceReviewListAsStaff = (SmartList<PerformanceReview>) value; break;
            case "performanceReviewListAsReviewer": this.performanceReviewListAsReviewer = (SmartList<PerformanceReview>) value; break;
            case "salesLeadList": this.salesLeadList = (SmartList<SalesLead>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "email": return this.email;
            case "phone": return this.phone;
            case "hireDate": return this.hireDate;
            case "status": return this.status;
            case "department": return this.department;
            case "jobTitle": return this.jobTitle;
            case "manager": return this.manager;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "dispatchPlanList": return this.dispatchPlanList;
            case "staffMemberList": return this.staffMemberList;
            case "workedHoursList": return this.workedHoursList;
            case "salarySlipList": return this.salarySlipList;
            case "performanceReviewListAsStaff": return this.performanceReviewListAsStaff;
            case "performanceReviewListAsReviewer": return this.performanceReviewListAsReviewer;
            case "salesLeadList": return this.salesLeadList;
            default: return super.__internalGet(property);
        }
    }

}