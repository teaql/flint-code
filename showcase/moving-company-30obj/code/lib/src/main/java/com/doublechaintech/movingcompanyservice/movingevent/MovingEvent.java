package com.doublechaintech.movingcompanyservice.movingevent;

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
public class MovingEvent extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "MovingEvent";

    public static final String CUSTOMER_PROPERTY = "customer";
    public static final String ROUTE_PROPERTY = "route";
    public static final String TIME_SLOT_PROPERTY = "timeSlot";
    public static final String STATUS_PROPERTY = "status";
    public static final String SCHEDULED_DATE_PROPERTY = "scheduledDate";
    public static final String NOTES_PROPERTY = "notes";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String customer;
    private String route;
    private String timeSlot;
    private String status;
    private LocalDate scheduledDate;
    private String notes;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getCustomer(){
        return this.customer;
    }
    public String getRoute(){
        return this.route;
    }
    public String getTimeSlot(){
        return this.timeSlot;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDate getScheduledDate(){
        return this.scheduledDate;
    }
    public String getNotes(){
        return this.notes;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public MovingEvent updateCustomer(String customer){
        customer = (customer == null ? null : customer.trim());
        if(Objects.equals(this.customer, customer)){
            return this;
        }
        handleUpdate(CUSTOMER_PROPERTY, getCustomer(), customer);
        this.customer = customer;
        return this;
    }
    public MovingEvent updateRoute(String route){
        route = (route == null ? null : route.trim());
        if(Objects.equals(this.route, route)){
            return this;
        }
        handleUpdate(ROUTE_PROPERTY, getRoute(), route);
        this.route = route;
        return this;
    }
    public MovingEvent updateTimeSlot(String timeSlot){
        timeSlot = (timeSlot == null ? null : timeSlot.trim());
        if(Objects.equals(this.timeSlot, timeSlot)){
            return this;
        }
        handleUpdate(TIME_SLOT_PROPERTY, getTimeSlot(), timeSlot);
        this.timeSlot = timeSlot;
        return this;
    }
    public MovingEvent updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public MovingEvent updateScheduledDate(LocalDate scheduledDate){
        if(Objects.equals(this.scheduledDate, scheduledDate)){
            return this;
        }
        handleUpdate(SCHEDULED_DATE_PROPERTY, getScheduledDate(), scheduledDate);
        this.scheduledDate = scheduledDate;
        return this;
    }
    public MovingEvent updateNotes(String notes){
        notes = (notes == null ? null : notes.trim());
        if(Objects.equals(this.notes, notes)){
            return this;
        }
        handleUpdate(NOTES_PROPERTY, getNotes(), notes);
        this.notes = notes;
        return this;
    }
    public MovingEvent updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public MovingEvent updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static MovingEvent refer(Long id){
        MovingEvent refer = new MovingEvent();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public MovingEvent comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<MovingEvent> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "customer": this.customer = (value == null ? null : ((String)value).trim()); break;

            case "route": this.route = (value == null ? null : ((String)value).trim()); break;

            case "timeSlot": this.timeSlot = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "scheduledDate": this.scheduledDate = (LocalDate) value; break;

            case "notes": this.notes = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "customer": return this.customer;
            case "route": return this.route;
            case "timeSlot": return this.timeSlot;
            case "status": return this.status;
            case "scheduledDate": return this.scheduledDate;
            case "notes": return this.notes;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}