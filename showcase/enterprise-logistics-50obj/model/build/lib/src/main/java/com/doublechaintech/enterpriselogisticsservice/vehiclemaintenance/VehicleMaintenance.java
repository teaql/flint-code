package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
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
public class VehicleMaintenance extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "VehicleMaintenance";

    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String SERVICE_TYPE_PROPERTY = "serviceType";
    public static final String SERVICE_DATE_PROPERTY = "serviceDate";
    public static final String COST_PROPERTY = "cost";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    private Vehicle vehicle;
    private String serviceType;
    private LocalDate serviceDate;
    private String cost;
    private String status;
    private LocalDateTime createdAt;

    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public String getServiceType(){
        return this.serviceType;
    }
    public LocalDate getServiceDate(){
        return this.serviceDate;
    }
    public String getCost(){
        return this.cost;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public VehicleMaintenance updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
        return this;
    }
    public VehicleMaintenance updateServiceType(String serviceType){
        serviceType = (serviceType == null ? null : serviceType.trim());
        if(Objects.equals(this.serviceType, serviceType)){
            return this;
        }
        handleUpdate(SERVICE_TYPE_PROPERTY, getServiceType(), serviceType);
        this.serviceType = serviceType;
        return this;
    }
    public VehicleMaintenance updateServiceDate(LocalDate serviceDate){
        if(Objects.equals(this.serviceDate, serviceDate)){
            return this;
        }
        handleUpdate(SERVICE_DATE_PROPERTY, getServiceDate(), serviceDate);
        this.serviceDate = serviceDate;
        return this;
    }
    public VehicleMaintenance updateCost(String cost){
        cost = (cost == null ? null : cost.trim());
        if(Objects.equals(this.cost, cost)){
            return this;
        }
        handleUpdate(COST_PROPERTY, getCost(), cost);
        this.cost = cost;
        return this;
    }
    public VehicleMaintenance updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public VehicleMaintenance updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }

    public static VehicleMaintenance refer(Long id){
        VehicleMaintenance refer = new VehicleMaintenance();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public VehicleMaintenance comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<VehicleMaintenance> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "serviceType": this.serviceType = (value == null ? null : ((String)value).trim()); break;

            case "serviceDate": this.serviceDate = (LocalDate) value; break;

            case "cost": this.cost = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "vehicle": return this.vehicle;
            case "serviceType": return this.serviceType;
            case "serviceDate": return this.serviceDate;
            case "cost": return this.cost;
            case "status": return this.status;
            case "createdAt": return this.createdAt;
            default: return super.__internalGet(property);
        }
    }

}