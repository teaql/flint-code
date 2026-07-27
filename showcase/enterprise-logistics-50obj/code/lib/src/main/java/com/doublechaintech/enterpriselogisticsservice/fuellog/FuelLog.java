package com.doublechaintech.enterpriselogisticsservice.fuellog;

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
public class FuelLog extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "FuelLog";

    public static final String LITERS_PROPERTY = "liters";
    public static final String COST_PROPERTY = "cost";
    public static final String ODOMETER_KM_PROPERTY = "odometerKm";
    public static final String STATION_NAME_PROPERTY = "stationName";
    public static final String DATE_PROPERTY = "date";
    public static final String VEHICLE_PROPERTY = "vehicle";
    private BigDecimal liters;
    private BigDecimal cost;
    private Integer odometerKm;
    private String stationName;
    private LocalDate date;
    private Vehicle vehicle;

    public BigDecimal getLiters(){
        return this.liters;
    }
    public BigDecimal getCost(){
        return this.cost;
    }
    public Integer getOdometerKm(){
        return this.odometerKm;
    }
    public String getStationName(){
        return this.stationName;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public FuelLog updateLiters(BigDecimal liters){
        if(Objects.equals(this.liters, liters)){
            return this;
        }
        handleUpdate(LITERS_PROPERTY, getLiters(), liters);
        this.liters = liters;
        return this;
    }
    public FuelLog updateCost(BigDecimal cost){
        if(Objects.equals(this.cost, cost)){
            return this;
        }
        handleUpdate(COST_PROPERTY, getCost(), cost);
        this.cost = cost;
        return this;
    }
    public FuelLog updateOdometerKm(Integer odometerKm){
        if(Objects.equals(this.odometerKm, odometerKm)){
            return this;
        }
        handleUpdate(ODOMETER_KM_PROPERTY, getOdometerKm(), odometerKm);
        this.odometerKm = odometerKm;
        return this;
    }
    public FuelLog updateStationName(String stationName){
        stationName = (stationName == null ? null : stationName.trim());
        if(Objects.equals(this.stationName, stationName)){
            return this;
        }
        handleUpdate(STATION_NAME_PROPERTY, getStationName(), stationName);
        this.stationName = stationName;
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
    public FuelLog updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
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
            case "liters": this.liters = (BigDecimal) value; break;

            case "cost": this.cost = (BigDecimal) value; break;

            case "odometerKm": this.odometerKm = (Integer) value; break;

            case "stationName": this.stationName = (value == null ? null : ((String)value).trim()); break;

            case "date": this.date = (LocalDate) value; break;

            case "vehicle": this.vehicle = (Vehicle) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "liters": return this.liters;
            case "cost": return this.cost;
            case "odometerKm": return this.odometerKm;
            case "stationName": return this.stationName;
            case "date": return this.date;
            case "vehicle": return this.vehicle;
            default: return super.__internalGet(property);
        }
    }

}