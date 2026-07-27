package com.doublechaintech.enterpriselogisticsservice.workedhours;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
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
public class WorkedHours extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "WorkedHours";

    public static final String STAFF_PROPERTY = "staff";
    public static final String SHIFT_PROPERTY = "shift";
    public static final String HOURS_WORKED_PROPERTY = "hoursWorked";
    public static final String DATE_PROPERTY = "date";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private StaffMember staff;
    private WorkShift shift;
    private String hoursWorked;
    private LocalDate date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StaffMember getStaff(){
        return this.staff;
    }
    public WorkShift getShift(){
        return this.shift;
    }
    public String getHoursWorked(){
        return this.hoursWorked;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public WorkedHours updateStaff(StaffMember staff){
        if(Objects.equals(this.staff, staff)){
            return this;
        }
        handleUpdate(STAFF_PROPERTY, getStaff(), staff);
        this.staff = staff;
        return this;
    }
    public WorkedHours updateShift(WorkShift shift){
        if(Objects.equals(this.shift, shift)){
            return this;
        }
        handleUpdate(SHIFT_PROPERTY, getShift(), shift);
        this.shift = shift;
        return this;
    }
    public WorkedHours updateHoursWorked(String hoursWorked){
        hoursWorked = (hoursWorked == null ? null : hoursWorked.trim());
        if(Objects.equals(this.hoursWorked, hoursWorked)){
            return this;
        }
        handleUpdate(HOURS_WORKED_PROPERTY, getHoursWorked(), hoursWorked);
        this.hoursWorked = hoursWorked;
        return this;
    }
    public WorkedHours updateDate(LocalDate date){
        if(Objects.equals(this.date, date)){
            return this;
        }
        handleUpdate(DATE_PROPERTY, getDate(), date);
        this.date = date;
        return this;
    }
    public WorkedHours updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public WorkedHours updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static WorkedHours refer(Long id){
        WorkedHours refer = new WorkedHours();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public WorkedHours comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<WorkedHours> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "staff": this.staff = (StaffMember) value; break;

            case "shift": this.shift = (WorkShift) value; break;

            case "hoursWorked": this.hoursWorked = (value == null ? null : ((String)value).trim()); break;

            case "date": this.date = (LocalDate) value; break;

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
            case "shift": return this.shift;
            case "hoursWorked": return this.hoursWorked;
            case "date": return this.date;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}