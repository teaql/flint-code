package com.doublechaintech.enterpriselogisticsservice.timeslot;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
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

    public static final String SLOT_ID_PROPERTY = "slotId";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String START_TIME_PROPERTY = "startTime";
    public static final String END_TIME_PROPERTY = "endTime";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    private String slotId;
    private MovingOrder movingOrder;
    private String startTime;
    private String endTime;
    private String status;
    private LocalDateTime createTime;

    public String getSlotId(){
        return this.slotId;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public TimeSlot updateSlotId(String slotId){
        slotId = (slotId == null ? null : slotId.trim());
        if(Objects.equals(this.slotId, slotId)){
            return this;
        }
        handleUpdate(SLOT_ID_PROPERTY, getSlotId(), slotId);
        this.slotId = slotId;
        return this;
    }
    public TimeSlot updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public TimeSlot updateStartTime(String startTime){
        startTime = (startTime == null ? null : startTime.trim());
        if(Objects.equals(this.startTime, startTime)){
            return this;
        }
        handleUpdate(START_TIME_PROPERTY, getStartTime(), startTime);
        this.startTime = startTime;
        return this;
    }
    public TimeSlot updateEndTime(String endTime){
        endTime = (endTime == null ? null : endTime.trim());
        if(Objects.equals(this.endTime, endTime)){
            return this;
        }
        handleUpdate(END_TIME_PROPERTY, getEndTime(), endTime);
        this.endTime = endTime;
        return this;
    }
    public TimeSlot updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public TimeSlot updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
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
            case "slotId": this.slotId = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "startTime": this.startTime = (value == null ? null : ((String)value).trim()); break;

            case "endTime": this.endTime = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "slotId": return this.slotId;
            case "movingOrder": return this.movingOrder;
            case "startTime": return this.startTime;
            case "endTime": return this.endTime;
            case "status": return this.status;
            case "createTime": return this.createTime;
            default: return super.__internalGet(property);
        }
    }

}