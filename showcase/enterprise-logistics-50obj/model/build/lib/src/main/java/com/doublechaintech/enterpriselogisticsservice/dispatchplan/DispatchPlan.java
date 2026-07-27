package com.doublechaintech.enterpriselogisticsservice.dispatchplan;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
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
public class DispatchPlan extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "DispatchPlan";

    public static final String PLAN_ID_PROPERTY = "planId";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String DRIVER_PROPERTY = "driver";
    public static final String STATUS_PROPERTY = "status";
    public static final String SCHEDULED_DEPARTURE_PROPERTY = "scheduledDeparture";
    public static final String SCHEDULED_ARRIVAL_PROPERTY = "scheduledArrival";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String planId;
    private MovingOrder movingOrder;
    private Vehicle vehicle;
    private StaffMember driver;
    private String status;
    private String scheduledDeparture;
    private String scheduledArrival;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getPlanId(){
        return this.planId;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public StaffMember getDriver(){
        return this.driver;
    }
    public String getStatus(){
        return this.status;
    }
    public String getScheduledDeparture(){
        return this.scheduledDeparture;
    }
    public String getScheduledArrival(){
        return this.scheduledArrival;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public DispatchPlan updatePlanId(String planId){
        planId = (planId == null ? null : planId.trim());
        if(Objects.equals(this.planId, planId)){
            return this;
        }
        handleUpdate(PLAN_ID_PROPERTY, getPlanId(), planId);
        this.planId = planId;
        return this;
    }
    public DispatchPlan updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public DispatchPlan updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
        return this;
    }
    public DispatchPlan updateDriver(StaffMember driver){
        if(Objects.equals(this.driver, driver)){
            return this;
        }
        handleUpdate(DRIVER_PROPERTY, getDriver(), driver);
        this.driver = driver;
        return this;
    }
    public DispatchPlan updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public DispatchPlan updateScheduledDeparture(String scheduledDeparture){
        scheduledDeparture = (scheduledDeparture == null ? null : scheduledDeparture.trim());
        if(Objects.equals(this.scheduledDeparture, scheduledDeparture)){
            return this;
        }
        handleUpdate(SCHEDULED_DEPARTURE_PROPERTY, getScheduledDeparture(), scheduledDeparture);
        this.scheduledDeparture = scheduledDeparture;
        return this;
    }
    public DispatchPlan updateScheduledArrival(String scheduledArrival){
        scheduledArrival = (scheduledArrival == null ? null : scheduledArrival.trim());
        if(Objects.equals(this.scheduledArrival, scheduledArrival)){
            return this;
        }
        handleUpdate(SCHEDULED_ARRIVAL_PROPERTY, getScheduledArrival(), scheduledArrival);
        this.scheduledArrival = scheduledArrival;
        return this;
    }
    public DispatchPlan updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public DispatchPlan updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static DispatchPlan refer(Long id){
        DispatchPlan refer = new DispatchPlan();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public DispatchPlan comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<DispatchPlan> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "planId": this.planId = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "driver": this.driver = (StaffMember) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "scheduledDeparture": this.scheduledDeparture = (value == null ? null : ((String)value).trim()); break;

            case "scheduledArrival": this.scheduledArrival = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "planId": return this.planId;
            case "movingOrder": return this.movingOrder;
            case "vehicle": return this.vehicle;
            case "driver": return this.driver;
            case "status": return this.status;
            case "scheduledDeparture": return this.scheduledDeparture;
            case "scheduledArrival": return this.scheduledArrival;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}