package com.doublechaintech.enterpriselogisticsservice.gpslog;

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
public class GpsLog extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "GpsLog";

    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String LATITUDE_PROPERTY = "latitude";
    public static final String LONGITUDE_PROPERTY = "longitude";
    public static final String TIMESTAMP_PROPERTY = "timestamp";
    public static final String SPEED_KMH_PROPERTY = "speedKmh";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    private Vehicle vehicle;
    private String latitude;
    private String longitude;
    private LocalDateTime timestamp;
    private String speedKmh;
    private LocalDateTime createdAt;

    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public String getLatitude(){
        return this.latitude;
    }
    public String getLongitude(){
        return this.longitude;
    }
    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }
    public String getSpeedKmh(){
        return this.speedKmh;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public GpsLog updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
        return this;
    }
    public GpsLog updateLatitude(String latitude){
        latitude = (latitude == null ? null : latitude.trim());
        if(Objects.equals(this.latitude, latitude)){
            return this;
        }
        handleUpdate(LATITUDE_PROPERTY, getLatitude(), latitude);
        this.latitude = latitude;
        return this;
    }
    public GpsLog updateLongitude(String longitude){
        longitude = (longitude == null ? null : longitude.trim());
        if(Objects.equals(this.longitude, longitude)){
            return this;
        }
        handleUpdate(LONGITUDE_PROPERTY, getLongitude(), longitude);
        this.longitude = longitude;
        return this;
    }
    public GpsLog updateTimestamp(LocalDateTime timestamp){
        if(Objects.equals(this.timestamp, timestamp)){
            return this;
        }
        handleUpdate(TIMESTAMP_PROPERTY, getTimestamp(), timestamp);
        this.timestamp = timestamp;
        return this;
    }
    public GpsLog updateSpeedKmh(String speedKmh){
        speedKmh = (speedKmh == null ? null : speedKmh.trim());
        if(Objects.equals(this.speedKmh, speedKmh)){
            return this;
        }
        handleUpdate(SPEED_KMH_PROPERTY, getSpeedKmh(), speedKmh);
        this.speedKmh = speedKmh;
        return this;
    }
    public GpsLog updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }

    public static GpsLog refer(Long id){
        GpsLog refer = new GpsLog();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public GpsLog comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<GpsLog> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "latitude": this.latitude = (value == null ? null : ((String)value).trim()); break;

            case "longitude": this.longitude = (value == null ? null : ((String)value).trim()); break;

            case "timestamp": this.timestamp = (LocalDateTime) value; break;

            case "speedKmh": this.speedKmh = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "vehicle": return this.vehicle;
            case "latitude": return this.latitude;
            case "longitude": return this.longitude;
            case "timestamp": return this.timestamp;
            case "speedKmh": return this.speedKmh;
            case "createdAt": return this.createdAt;
            default: return super.__internalGet(property);
        }
    }

}