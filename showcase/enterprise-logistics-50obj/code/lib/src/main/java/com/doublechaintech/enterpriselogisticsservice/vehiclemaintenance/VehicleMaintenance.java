package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public static final String SERVICE_TYPE_PROPERTY = "serviceType";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String COST_PROPERTY = "cost";
    public static final String SCHEDULED_DATE_PROPERTY = "scheduledDate";
    public static final String COMPLETED_DATE_PROPERTY = "completedDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String VEHICLE_PROPERTY = "vehicle";
    private String serviceType;
    private String description;
    private BigDecimal cost;
    private LocalDate scheduledDate;
    private LocalDate completedDate;
    private String status;
    private Vehicle vehicle;

    public String getServiceType(){
        return this.serviceType;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getCost(){
        return this.cost;
    }
    public LocalDate getScheduledDate(){
        return this.scheduledDate;
    }
    public LocalDate getCompletedDate(){
        return this.completedDate;
    }
    public String getStatus(){
        return this.status;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
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
    public VehicleMaintenance updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public VehicleMaintenance updateCost(BigDecimal cost){
        if(Objects.equals(this.cost, cost)){
            return this;
        }
        handleUpdate(COST_PROPERTY, getCost(), cost);
        this.cost = cost;
        return this;
    }
    public VehicleMaintenance updateScheduledDate(LocalDate scheduledDate){
        if(Objects.equals(this.scheduledDate, scheduledDate)){
            return this;
        }
        handleUpdate(SCHEDULED_DATE_PROPERTY, getScheduledDate(), scheduledDate);
        this.scheduledDate = scheduledDate;
        return this;
    }
    public VehicleMaintenance updateCompletedDate(LocalDate completedDate){
        if(Objects.equals(this.completedDate, completedDate)){
            return this;
        }
        handleUpdate(COMPLETED_DATE_PROPERTY, getCompletedDate(), completedDate);
        this.completedDate = completedDate;
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
    public VehicleMaintenance updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
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
            case "serviceType": this.serviceType = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "cost": this.cost = (BigDecimal) value; break;

            case "scheduledDate": this.scheduledDate = (LocalDate) value; break;

            case "completedDate": this.completedDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "vehicle": this.vehicle = (Vehicle) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "serviceType": return this.serviceType;
            case "description": return this.description;
            case "cost": return this.cost;
            case "scheduledDate": return this.scheduledDate;
            case "completedDate": return this.completedDate;
            case "status": return this.status;
            case "vehicle": return this.vehicle;
            default: return super.__internalGet(property);
        }
    }

}