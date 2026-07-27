package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
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
public class SafetyTraining extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SafetyTraining";

    public static final String STAFF_PROPERTY = "staff";
    public static final String COURSE_NAME_PROPERTY = "courseName";
    public static final String COMPLETION_DATE_PROPERTY = "completionDate";
    public static final String CERTIFICATE_NUMBER_PROPERTY = "certificateNumber";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private StaffMember staff;
    private String courseName;
    private LocalDate completionDate;
    private String certificateNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StaffMember getStaff(){
        return this.staff;
    }
    public String getCourseName(){
        return this.courseName;
    }
    public LocalDate getCompletionDate(){
        return this.completionDate;
    }
    public String getCertificateNumber(){
        return this.certificateNumber;
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
    public SafetyTraining updateStaff(StaffMember staff){
        if(Objects.equals(this.staff, staff)){
            return this;
        }
        handleUpdate(STAFF_PROPERTY, getStaff(), staff);
        this.staff = staff;
        return this;
    }
    public SafetyTraining updateCourseName(String courseName){
        courseName = (courseName == null ? null : courseName.trim());
        if(Objects.equals(this.courseName, courseName)){
            return this;
        }
        handleUpdate(COURSE_NAME_PROPERTY, getCourseName(), courseName);
        this.courseName = courseName;
        return this;
    }
    public SafetyTraining updateCompletionDate(LocalDate completionDate){
        if(Objects.equals(this.completionDate, completionDate)){
            return this;
        }
        handleUpdate(COMPLETION_DATE_PROPERTY, getCompletionDate(), completionDate);
        this.completionDate = completionDate;
        return this;
    }
    public SafetyTraining updateCertificateNumber(String certificateNumber){
        certificateNumber = (certificateNumber == null ? null : certificateNumber.trim());
        if(Objects.equals(this.certificateNumber, certificateNumber)){
            return this;
        }
        handleUpdate(CERTIFICATE_NUMBER_PROPERTY, getCertificateNumber(), certificateNumber);
        this.certificateNumber = certificateNumber;
        return this;
    }
    public SafetyTraining updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public SafetyTraining updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public SafetyTraining updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static SafetyTraining refer(Long id){
        SafetyTraining refer = new SafetyTraining();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public SafetyTraining comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<SafetyTraining> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "staff": this.staff = (StaffMember) value; break;

            case "courseName": this.courseName = (value == null ? null : ((String)value).trim()); break;

            case "completionDate": this.completionDate = (LocalDate) value; break;

            case "certificateNumber": this.certificateNumber = (value == null ? null : ((String)value).trim()); break;

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
            case "courseName": return this.courseName;
            case "completionDate": return this.completionDate;
            case "certificateNumber": return this.certificateNumber;
            case "status": return this.status;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}