package com.doublechaintech.enterpriselogisticsservice.salaryslip;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
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
public class SalarySlip extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SalarySlip";

    public static final String STAFF_PROPERTY = "staff";
    public static final String PERIOD_PROPERTY = "period";
    public static final String BASE_SALARY_PROPERTY = "baseSalary";
    public static final String BONUS_PROPERTY = "bonus";
    public static final String DEDUCTIONS_PROPERTY = "deductions";
    public static final String NET_PAY_PROPERTY = "netPay";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private StaffMember staff;
    private String period;
    private String baseSalary;
    private String bonus;
    private String deductions;
    private String netPay;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StaffMember getStaff(){
        return this.staff;
    }
    public String getPeriod(){
        return this.period;
    }
    public String getBaseSalary(){
        return this.baseSalary;
    }
    public String getBonus(){
        return this.bonus;
    }
    public String getDeductions(){
        return this.deductions;
    }
    public String getNetPay(){
        return this.netPay;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SalarySlip updateStaff(StaffMember staff){
        if(Objects.equals(this.staff, staff)){
            return this;
        }
        handleUpdate(STAFF_PROPERTY, getStaff(), staff);
        this.staff = staff;
        return this;
    }
    public SalarySlip updatePeriod(String period){
        period = (period == null ? null : period.trim());
        if(Objects.equals(this.period, period)){
            return this;
        }
        handleUpdate(PERIOD_PROPERTY, getPeriod(), period);
        this.period = period;
        return this;
    }
    public SalarySlip updateBaseSalary(String baseSalary){
        baseSalary = (baseSalary == null ? null : baseSalary.trim());
        if(Objects.equals(this.baseSalary, baseSalary)){
            return this;
        }
        handleUpdate(BASE_SALARY_PROPERTY, getBaseSalary(), baseSalary);
        this.baseSalary = baseSalary;
        return this;
    }
    public SalarySlip updateBonus(String bonus){
        bonus = (bonus == null ? null : bonus.trim());
        if(Objects.equals(this.bonus, bonus)){
            return this;
        }
        handleUpdate(BONUS_PROPERTY, getBonus(), bonus);
        this.bonus = bonus;
        return this;
    }
    public SalarySlip updateDeductions(String deductions){
        deductions = (deductions == null ? null : deductions.trim());
        if(Objects.equals(this.deductions, deductions)){
            return this;
        }
        handleUpdate(DEDUCTIONS_PROPERTY, getDeductions(), deductions);
        this.deductions = deductions;
        return this;
    }
    public SalarySlip updateNetPay(String netPay){
        netPay = (netPay == null ? null : netPay.trim());
        if(Objects.equals(this.netPay, netPay)){
            return this;
        }
        handleUpdate(NET_PAY_PROPERTY, getNetPay(), netPay);
        this.netPay = netPay;
        return this;
    }
    public SalarySlip updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public SalarySlip updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public SalarySlip updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static SalarySlip refer(Long id){
        SalarySlip refer = new SalarySlip();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public SalarySlip comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<SalarySlip> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "staff": this.staff = (StaffMember) value; break;

            case "period": this.period = (value == null ? null : ((String)value).trim()); break;

            case "baseSalary": this.baseSalary = (value == null ? null : ((String)value).trim()); break;

            case "bonus": this.bonus = (value == null ? null : ((String)value).trim()); break;

            case "deductions": this.deductions = (value == null ? null : ((String)value).trim()); break;

            case "netPay": this.netPay = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "staff": return this.staff;
            case "period": return this.period;
            case "baseSalary": return this.baseSalary;
            case "bonus": return this.bonus;
            case "deductions": return this.deductions;
            case "netPay": return this.netPay;
            case "status": return this.status;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}