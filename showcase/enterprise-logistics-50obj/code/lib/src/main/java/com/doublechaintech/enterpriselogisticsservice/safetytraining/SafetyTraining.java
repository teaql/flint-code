package com.doublechaintech.enterpriselogisticsservice.safetytraining;

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

    public static final String TITLE_PROPERTY = "title";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String DURATION_HOURS_PROPERTY = "durationHours";
    public static final String COMPLETION_DATE_PROPERTY = "completionDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private String title;
    private String description;
    private String durationHours;
    private LocalDate completionDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public String getDurationHours(){
        return this.durationHours;
    }
    public LocalDate getCompletionDate(){
        return this.completionDate;
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
    public SafetyTraining updateTitle(String title){
        title = (title == null ? null : title.trim());
        if(Objects.equals(this.title, title)){
            return this;
        }
        handleUpdate(TITLE_PROPERTY, getTitle(), title);
        this.title = title;
        return this;
    }
    public SafetyTraining updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public SafetyTraining updateDurationHours(String durationHours){
        durationHours = (durationHours == null ? null : durationHours.trim());
        if(Objects.equals(this.durationHours, durationHours)){
            return this;
        }
        handleUpdate(DURATION_HOURS_PROPERTY, getDurationHours(), durationHours);
        this.durationHours = durationHours;
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
            case "title": this.title = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "durationHours": this.durationHours = (value == null ? null : ((String)value).trim()); break;

            case "completionDate": this.completionDate = (LocalDate) value; break;

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
            case "title": return this.title;
            case "description": return this.description;
            case "durationHours": return this.durationHours;
            case "completionDate": return this.completionDate;
            case "status": return this.status;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}