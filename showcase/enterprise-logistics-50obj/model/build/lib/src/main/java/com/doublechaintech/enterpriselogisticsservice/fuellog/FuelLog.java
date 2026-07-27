package com.doublechaintech.enterpriselogisticsservice.fuellog;

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
public class FuelLog extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "FuelLog";

    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String FUEL_AMOUNT_LITERS_PROPERTY = "fuelAmountLiters";
    public static final String COST_PROPERTY = "cost";
    public static final String DATE_PROPERTY = "date";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    private Vehicle vehicle;
    private String fuelAmountLiters;
    private String cost;
    private LocalDate date;
    private LocalDateTime createdAt;

    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public String getFuelAmountLiters(){
        return this.fuelAmountLiters;
    }
    public String getCost(){
        return this.cost;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public FuelLog updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
        return this;
    }
    public FuelLog updateFuelAmountLiters(String fuelAmountLiters){
        fuelAmountLiters = (fuelAmountLiters == null ? null : fuelAmountLiters.trim());
        if(Objects.equals(this.fuelAmountLiters, fuelAmountLiters)){
            return this;
        }
        handleUpdate(FUEL_AMOUNT_LITERS_PROPERTY, getFuelAmountLiters(), fuelAmountLiters);
        this.fuelAmountLiters = fuelAmountLiters;
        return this;
    }
    public FuelLog updateCost(String cost){
        cost = (cost == null ? null : cost.trim());
        if(Objects.equals(this.cost, cost)){
            return this;
        }
        handleUpdate(COST_PROPERTY, getCost(), cost);
        this.cost = cost;
        return this;
    }
    public FuelLog updateDate(LocalDate date){
        if(Objects.equals(this.date, date)){
            return this;
        }
        handleUpdate(DATE_PROPERTY, getDate(), date);
        this.date = date;
        return this;
    }
    public FuelLog updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }

    public static FuelLog refer(Long id){
        FuelLog refer = new FuelLog();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public FuelLog comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<FuelLog> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "fuelAmountLiters": this.fuelAmountLiters = (value == null ? null : ((String)value).trim()); break;

            case "cost": this.cost = (value == null ? null : ((String)value).trim()); break;

            case "date": this.date = (LocalDate) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "vehicle": return this.vehicle;
            case "fuelAmountLiters": return this.fuelAmountLiters;
            case "cost": return this.cost;
            case "date": return this.date;
            case "createdAt": return this.createdAt;
            default: return super.__internalGet(property);
        }
    }

}