package com.doublechaintech.movingcompanyservice.vehicle;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
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
public class Vehicle extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "Vehicle";

    public static final String INTERNAL_TYPE_PROPERTY = "internalType";
    public static final String DISPLAY_NAME_PROPERTY = "displayName";
    public static final String VEHICLE_TYPE_PROPERTY = "vehicleType";
    public static final String LICENSE_PLATE_PROPERTY = "licensePlate";
    public static final String CAPACITY_CUBIC_METERS_PROPERTY = "capacityCubicMeters";
    public static final String PURCHASE_DATE_PROPERTY = "purchaseDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String LAST_MAINTENANCE_DATE_PROPERTY = "lastMaintenanceDate";
    public static final String NEXT_MAINTENANCE_DATE_PROPERTY = "nextMaintenanceDate";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String internalType;
    private String displayName;
    private String vehicleType;
    private String licensePlate;
    private BigDecimal capacityCubicMeters;
    private LocalDate purchaseDate;
    private String status;
    private LocalDate lastMaintenanceDate;
    private LocalDate nextMaintenanceDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getInternalType(){
        return this.internalType;
    }
    public String getDisplayName(){
        return this.displayName;
    }
    public String getVehicleType(){
        return this.vehicleType;
    }
    public String getLicensePlate(){
        return this.licensePlate;
    }
    public BigDecimal getCapacityCubicMeters(){
        return this.capacityCubicMeters;
    }
    public LocalDate getPurchaseDate(){
        return this.purchaseDate;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDate getLastMaintenanceDate(){
        return this.lastMaintenanceDate;
    }
    public LocalDate getNextMaintenanceDate(){
        return this.nextMaintenanceDate;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public Vehicle updateInternalType(String internalType){
        internalType = (internalType == null ? null : internalType.trim());
        if(Objects.equals(this.internalType, internalType)){
            return this;
        }
        handleUpdate(INTERNAL_TYPE_PROPERTY, getInternalType(), internalType);
        this.internalType = internalType;
        return this;
    }
    public Vehicle updateDisplayName(String displayName){
        displayName = (displayName == null ? null : displayName.trim());
        if(Objects.equals(this.displayName, displayName)){
            return this;
        }
        handleUpdate(DISPLAY_NAME_PROPERTY, getDisplayName(), displayName);
        this.displayName = displayName;
        return this;
    }
    public Vehicle updateVehicleType(String vehicleType){
        vehicleType = (vehicleType == null ? null : vehicleType.trim());
        if(Objects.equals(this.vehicleType, vehicleType)){
            return this;
        }
        handleUpdate(VEHICLE_TYPE_PROPERTY, getVehicleType(), vehicleType);
        this.vehicleType = vehicleType;
        return this;
    }
    public Vehicle updateLicensePlate(String licensePlate){
        licensePlate = (licensePlate == null ? null : licensePlate.trim());
        if(Objects.equals(this.licensePlate, licensePlate)){
            return this;
        }
        handleUpdate(LICENSE_PLATE_PROPERTY, getLicensePlate(), licensePlate);
        this.licensePlate = licensePlate;
        return this;
    }
    public Vehicle updateCapacityCubicMeters(BigDecimal capacityCubicMeters){
        if(Objects.equals(this.capacityCubicMeters, capacityCubicMeters)){
            return this;
        }
        handleUpdate(CAPACITY_CUBIC_METERS_PROPERTY, getCapacityCubicMeters(), capacityCubicMeters);
        this.capacityCubicMeters = capacityCubicMeters;
        return this;
    }
    public Vehicle updatePurchaseDate(LocalDate purchaseDate){
        if(Objects.equals(this.purchaseDate, purchaseDate)){
            return this;
        }
        handleUpdate(PURCHASE_DATE_PROPERTY, getPurchaseDate(), purchaseDate);
        this.purchaseDate = purchaseDate;
        return this;
    }
    public Vehicle updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public Vehicle updateLastMaintenanceDate(LocalDate lastMaintenanceDate){
        if(Objects.equals(this.lastMaintenanceDate, lastMaintenanceDate)){
            return this;
        }
        handleUpdate(LAST_MAINTENANCE_DATE_PROPERTY, getLastMaintenanceDate(), lastMaintenanceDate);
        this.lastMaintenanceDate = lastMaintenanceDate;
        return this;
    }
    public Vehicle updateNextMaintenanceDate(LocalDate nextMaintenanceDate){
        if(Objects.equals(this.nextMaintenanceDate, nextMaintenanceDate)){
            return this;
        }
        handleUpdate(NEXT_MAINTENANCE_DATE_PROPERTY, getNextMaintenanceDate(), nextMaintenanceDate);
        this.nextMaintenanceDate = nextMaintenanceDate;
        return this;
    }
    public Vehicle updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public Vehicle updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static Vehicle refer(Long id){
        Vehicle refer = new Vehicle();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public Vehicle comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<Vehicle> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "internalType": this.internalType = (value == null ? null : ((String)value).trim()); break;

            case "displayName": this.displayName = (value == null ? null : ((String)value).trim()); break;

            case "vehicleType": this.vehicleType = (value == null ? null : ((String)value).trim()); break;

            case "licensePlate": this.licensePlate = (value == null ? null : ((String)value).trim()); break;

            case "capacityCubicMeters": this.capacityCubicMeters = (BigDecimal) value; break;

            case "purchaseDate": this.purchaseDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "lastMaintenanceDate": this.lastMaintenanceDate = (LocalDate) value; break;

            case "nextMaintenanceDate": this.nextMaintenanceDate = (LocalDate) value; break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "internalType": return this.internalType;
            case "displayName": return this.displayName;
            case "vehicleType": return this.vehicleType;
            case "licensePlate": return this.licensePlate;
            case "capacityCubicMeters": return this.capacityCubicMeters;
            case "purchaseDate": return this.purchaseDate;
            case "status": return this.status;
            case "lastMaintenanceDate": return this.lastMaintenanceDate;
            case "nextMaintenanceDate": return this.nextMaintenanceDate;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}