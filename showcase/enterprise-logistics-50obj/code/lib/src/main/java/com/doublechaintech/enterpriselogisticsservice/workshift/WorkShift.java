package com.doublechaintech.enterpriselogisticsservice.workshift;

import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class WorkShift extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "WorkShift";

    public static final String NAME_PROPERTY = "name";
    public static final String START_TIME_PROPERTY = "startTime";
    public static final String END_TIME_PROPERTY = "endTime";
    public static final String SHIFT_TYPE_PROPERTY = "shiftType";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String WORKED_HOURS_LIST_PROPERTY = "workedHoursList";
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private String shiftType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<WorkedHours> workedHoursList;

    public String getName(){
        return this.name;
    }
    public LocalTime getStartTime(){
        return this.startTime;
    }
    public LocalTime getEndTime(){
        return this.endTime;
    }
    public String getShiftType(){
        return this.shiftType;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SmartList<WorkedHours> getWorkedHoursList(){
        return this.workedHoursList;
    }
    public WorkShift updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public WorkShift updateStartTime(LocalTime startTime){
        if(Objects.equals(this.startTime, startTime)){
            return this;
        }
        handleUpdate(START_TIME_PROPERTY, getStartTime(), startTime);
        this.startTime = startTime;
        return this;
    }
    public WorkShift updateEndTime(LocalTime endTime){
        if(Objects.equals(this.endTime, endTime)){
            return this;
        }
        handleUpdate(END_TIME_PROPERTY, getEndTime(), endTime);
        this.endTime = endTime;
        return this;
    }
    public WorkShift updateShiftType(String shiftType){
        shiftType = (shiftType == null ? null : shiftType.trim());
        if(Objects.equals(this.shiftType, shiftType)){
            return this;
        }
        handleUpdate(SHIFT_TYPE_PROPERTY, getShiftType(), shiftType);
        this.shiftType = shiftType;
        return this;
    }
    public WorkShift updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public WorkShift updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public WorkShift addWorkedHours(WorkedHours workedHours){
        if (workedHours == null){
            return this;
        }

        if(null == this.workedHoursList){
            this.workedHoursList = new SmartList<>();
        }

        this.workedHoursList.add(workedHours);
        workedHours.cacheRelation(WorkedHours.SHIFT_PROPERTY, this);
        return this;
    }

    public static WorkShift refer(Long id){
        WorkShift refer = new WorkShift();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public WorkShift comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<WorkShift> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "startTime": this.startTime = (LocalTime) value; break;

            case "endTime": this.endTime = (LocalTime) value; break;

            case "shiftType": this.shiftType = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "workedHoursList": this.workedHoursList = (SmartList<WorkedHours>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "startTime": return this.startTime;
            case "endTime": return this.endTime;
            case "shiftType": return this.shiftType;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "workedHoursList": return this.workedHoursList;
            default: return super.__internalGet(property);
        }
    }

}