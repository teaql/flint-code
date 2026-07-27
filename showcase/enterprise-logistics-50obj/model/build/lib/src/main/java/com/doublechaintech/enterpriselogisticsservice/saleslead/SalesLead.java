package com.doublechaintech.enterpriselogisticsservice.saleslead;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
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
    public static final String EMAIL_PROPERTY = "email";
    public static final String PHONE_PROPERTY = "phone";
    public static final String SOURCE_PROPERTY = "source";
    public static final String STATUS_PROPERTY = "status";
    public static final String ESTIMATED_VALUE_PROPERTY = "estimatedValue";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String name;
    private String email;
    private Integer phone;
    private String source;
    private String status;
    private BigDecimal estimatedValue;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public Integer getPhone(){
        return this.phone;
    }
    public String getSource(){
        return this.source;
    }
    public String getStatus(){
        return this.status;
    }
    public BigDecimal getEstimatedValue(){
        return this.estimatedValue;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
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
    public SalesLead updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public SalesLead updatePhone(Integer phone){
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
    public SalesLead updateEstimatedValue(BigDecimal estimatedValue){
        if(Objects.equals(this.estimatedValue, estimatedValue)){
            return this;
        }
        handleUpdate(ESTIMATED_VALUE_PROPERTY, getEstimatedValue(), estimatedValue);
        this.estimatedValue = estimatedValue;
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
    public SalesLead updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
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

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (Integer) value; break;

            case "source": this.source = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "estimatedValue": this.estimatedValue = (BigDecimal) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

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
            case "source": return this.source;
            case "status": return this.status;
            case "estimatedValue": return this.estimatedValue;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}