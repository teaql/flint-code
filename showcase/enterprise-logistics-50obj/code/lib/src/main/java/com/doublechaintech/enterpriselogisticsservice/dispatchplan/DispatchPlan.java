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

    public static final String PLAN_NUMBER_PROPERTY = "planNumber";
    public static final String STATUS_PROPERTY = "status";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String DRIVER_PROPERTY = "driver";
    public static final String SCHEDULED_DEPARTURE_PROPERTY = "scheduledDeparture";
    public static final String SCHEDULED_ARRIVAL_PROPERTY = "scheduledArrival";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String planNumber;
    private String status;
    private MovingOrder movingOrder;
    private Vehicle vehicle;
    private StaffMember driver;
    private LocalDateTime scheduledDeparture;
    private LocalDateTime scheduledArrival;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getPlanNumber(){
        return this.planNumber;
    }
    public String getStatus(){
        return this.status;
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
    public LocalDateTime getScheduledDeparture(){
        return this.scheduledDeparture;
    }
    public LocalDateTime getScheduledArrival(){
        return this.scheduledArrival;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public DispatchPlan updatePlanNumber(String planNumber){
        planNumber = (planNumber == null ? null : planNumber.trim());
        if(Objects.equals(this.planNumber, planNumber)){
            return this;
        }
        handleUpdate(PLAN_NUMBER_PROPERTY, getPlanNumber(), planNumber);
        this.planNumber = planNumber;
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
    public DispatchPlan updateScheduledDeparture(LocalDateTime scheduledDeparture){
        if(Objects.equals(this.scheduledDeparture, scheduledDeparture)){
            return this;
        }
        handleUpdate(SCHEDULED_DEPARTURE_PROPERTY, getScheduledDeparture(), scheduledDeparture);
        this.scheduledDeparture = scheduledDeparture;
        return this;
    }
    public DispatchPlan updateScheduledArrival(LocalDateTime scheduledArrival){
        if(Objects.equals(this.scheduledArrival, scheduledArrival)){
            return this;
        }
        handleUpdate(SCHEDULED_ARRIVAL_PROPERTY, getScheduledArrival(), scheduledArrival);
        this.scheduledArrival = scheduledArrival;
        return this;
    }
    public DispatchPlan updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public DispatchPlan updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
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
            case "planNumber": this.planNumber = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "driver": this.driver = (StaffMember) value; break;

            case "scheduledDeparture": this.scheduledDeparture = (LocalDateTime) value; break;

            case "scheduledArrival": this.scheduledArrival = (LocalDateTime) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "planNumber": return this.planNumber;
            case "status": return this.status;
            case "movingOrder": return this.movingOrder;
            case "vehicle": return this.vehicle;
            case "driver": return this.driver;
            case "scheduledDeparture": return this.scheduledDeparture;
            case "scheduledArrival": return this.scheduledArrival;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}