package com.doublechaintech.enterpriselogisticsservice.timeslot;

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
public class TimeSlot extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "TimeSlot";

    public static final String SLOT_CODE_PROPERTY = "slotCode";
    public static final String START_TIME_PROPERTY = "startTime";
    public static final String END_TIME_PROPERTY = "endTime";
    public static final String CAPACITY_PROPERTY = "capacity";
    public static final String AVAILABLE_SPOTS_PROPERTY = "availableSpots";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String slotCode;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer capacity;
    private Integer availableSpots;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getSlotCode(){
        return this.slotCode;
    }
    public LocalDateTime getStartTime(){
        return this.startTime;
    }
    public LocalDateTime getEndTime(){
        return this.endTime;
    }
    public Integer getCapacity(){
        return this.capacity;
    }
    public Integer getAvailableSpots(){
        return this.availableSpots;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public TimeSlot updateSlotCode(String slotCode){
        slotCode = (slotCode == null ? null : slotCode.trim());
        if(Objects.equals(this.slotCode, slotCode)){
            return this;
        }
        handleUpdate(SLOT_CODE_PROPERTY, getSlotCode(), slotCode);
        this.slotCode = slotCode;
        return this;
    }
    public TimeSlot updateStartTime(LocalDateTime startTime){
        if(Objects.equals(this.startTime, startTime)){
            return this;
        }
        handleUpdate(START_TIME_PROPERTY, getStartTime(), startTime);
        this.startTime = startTime;
        return this;
    }
    public TimeSlot updateEndTime(LocalDateTime endTime){
        if(Objects.equals(this.endTime, endTime)){
            return this;
        }
        handleUpdate(END_TIME_PROPERTY, getEndTime(), endTime);
        this.endTime = endTime;
        return this;
    }
    public TimeSlot updateCapacity(Integer capacity){
        if(Objects.equals(this.capacity, capacity)){
            return this;
        }
        handleUpdate(CAPACITY_PROPERTY, getCapacity(), capacity);
        this.capacity = capacity;
        return this;
    }
    public TimeSlot updateAvailableSpots(Integer availableSpots){
        if(Objects.equals(this.availableSpots, availableSpots)){
            return this;
        }
        handleUpdate(AVAILABLE_SPOTS_PROPERTY, getAvailableSpots(), availableSpots);
        this.availableSpots = availableSpots;
        return this;
    }
    public TimeSlot updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public TimeSlot updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static TimeSlot refer(Long id){
        TimeSlot refer = new TimeSlot();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public TimeSlot comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<TimeSlot> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "slotCode": this.slotCode = (value == null ? null : ((String)value).trim()); break;

            case "startTime": this.startTime = (LocalDateTime) value; break;

            case "endTime": this.endTime = (LocalDateTime) value; break;

            case "capacity": this.capacity = (Integer) value; break;

            case "availableSpots": this.availableSpots = (Integer) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "slotCode": return this.slotCode;
            case "startTime": return this.startTime;
            case "endTime": return this.endTime;
            case "capacity": return this.capacity;
            case "availableSpots": return this.availableSpots;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}