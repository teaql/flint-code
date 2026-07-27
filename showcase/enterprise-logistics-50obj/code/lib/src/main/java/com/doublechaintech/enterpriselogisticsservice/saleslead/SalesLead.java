package com.doublechaintech.enterpriselogisticsservice.saleslead;

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
public class SalesLead extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SalesLead";

    public static final String NAME_PROPERTY = "name";
    public static final String COMPANY_PROPERTY = "company";
    public static final String EMAIL_PROPERTY = "email";
    public static final String PHONE_PROPERTY = "phone";
    public static final String SOURCE_PROPERTY = "source";
    public static final String STATUS_PROPERTY = "status";
    public static final String ASSIGNED_TO_PROPERTY = "assignedTo";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String name;
    private String company;
    private String email;
    private String phone;
    private String source;
    private String status;
    private StaffMember assignedTo;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getName(){
        return this.name;
    }
    public String getCompany(){
        return this.company;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getSource(){
        return this.source;
    }
    public String getStatus(){
        return this.status;
    }
    public StaffMember getAssignedTo(){
        return this.assignedTo;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public SalesLead updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public SalesLead updateCompany(String company){
        company = (company == null ? null : company.trim());
        if(Objects.equals(this.company, company)){
            return this;
        }
        handleUpdate(COMPANY_PROPERTY, getCompany(), company);
        this.company = company;
        return this;
    }
    public SalesLead updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public SalesLead updatePhone(String phone){
        phone = (phone == null ? null : phone.trim());
        if(Objects.equals(this.phone, phone)){
            return this;
        }
        handleUpdate(PHONE_PROPERTY, getPhone(), phone);
        this.phone = phone;
        return this;
    }
    public SalesLead updateSource(String source){
        source = (source == null ? null : source.trim());
        if(Objects.equals(this.source, source)){
            return this;
        }
        handleUpdate(SOURCE_PROPERTY, getSource(), source);
        this.source = source;
        return this;
    }
    public SalesLead updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public SalesLead updateAssignedTo(StaffMember assignedTo){
        if(Objects.equals(this.assignedTo, assignedTo)){
            return this;
        }
        handleUpdate(ASSIGNED_TO_PROPERTY, getAssignedTo(), assignedTo);
        this.assignedTo = assignedTo;
        return this;
    }
    public SalesLead updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public SalesLead updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static SalesLead refer(Long id){
        SalesLead refer = new SalesLead();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public SalesLead comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<SalesLead> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "company": this.company = (value == null ? null : ((String)value).trim()); break;

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (value == null ? null : ((String)value).trim()); break;

            case "source": this.source = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "assignedTo": this.assignedTo = (StaffMember) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "company": return this.company;
            case "email": return this.email;
            case "phone": return this.phone;
            case "source": return this.source;
            case "status": return this.status;
            case "assignedTo": return this.assignedTo;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}