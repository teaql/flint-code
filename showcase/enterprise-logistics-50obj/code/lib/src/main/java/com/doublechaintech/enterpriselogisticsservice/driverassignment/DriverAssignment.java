package com.doublechaintech.enterpriselogisticsservice.driverassignment;

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
public class DriverAssignment extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "DriverAssignment";

    public static final String START_TIME_PROPERTY = "startTime";
    public static final String END_TIME_PROPERTY = "endTime";
    public static final String STATUS_PROPERTY = "status";
    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String DRIVER_PROPERTY = "driver";
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Vehicle vehicle;
    private String driver;

    public LocalDateTime getStartTime(){
        return this.startTime;
    }
    public LocalDateTime getEndTime(){
        return this.endTime;
    }
    public String getStatus(){
        return this.status;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public String getDriver(){
        return this.driver;
    }
    public DriverAssignment updateStartTime(LocalDateTime startTime){
        if(Objects.equals(this.startTime, startTime)){
            return this;
        }
        handleUpdate(START_TIME_PROPERTY, getStartTime(), startTime);
        this.startTime = startTime;
        return this;
    }
    public DriverAssignment updateEndTime(LocalDateTime endTime){
        if(Objects.equals(this.endTime, endTime)){
            return this;
        }
        handleUpdate(END_TIME_PROPERTY, getEndTime(), endTime);
        this.endTime = endTime;
        return this;
    }
    public DriverAssignment updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public DriverAssignment updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
        return this;
    }
    public DriverAssignment updateDriver(String driver){
        driver = (driver == null ? null : driver.trim());
        if(Objects.equals(this.driver, driver)){
            return this;
        }
        handleUpdate(DRIVER_PROPERTY, getDriver(), driver);
        this.driver = driver;
        return this;
    }

    public static DriverAssignment refer(Long id){
        DriverAssignment refer = new DriverAssignment();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public DriverAssignment comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<DriverAssignment> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "startTime": this.startTime = (LocalDateTime) value; break;

            case "endTime": this.endTime = (LocalDateTime) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "driver": this.driver = (value == null ? null : ((String)value).trim()); break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "startTime": return this.startTime;
            case "endTime": return this.endTime;
            case "status": return this.status;
            case "vehicle": return this.vehicle;
            case "driver": return this.driver;
            default: return super.__internalGet(property);
        }
    }

}