package com.doublechaintech.enterpriselogisticsservice.gpslog;

import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
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

    public static final String LATITUDE_PROPERTY = "latitude";
    public static final String LONGITUDE_PROPERTY = "longitude";
    public static final String SPEED_KMH_PROPERTY = "speedKmh";
    public static final String HEADING_PROPERTY = "heading";
    public static final String TIMESTAMP_PROPERTY = "timestamp";
    public static final String DEVICE_PROPERTY = "device";
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Integer speedKmh;
    private Integer heading;
    private LocalDateTime timestamp;
    private TelematicsDevice device;

    public BigDecimal getLatitude(){
        return this.latitude;
    }
    public BigDecimal getLongitude(){
        return this.longitude;
    }
    public Integer getSpeedKmh(){
        return this.speedKmh;
    }
    public Integer getHeading(){
        return this.heading;
    }
    public LocalDateTime getTimestamp(){
        return this.timestamp;
    }
    public TelematicsDevice getDevice(){
        return this.device;
    }
    public GpsLog updateLatitude(BigDecimal latitude){
        if(Objects.equals(this.latitude, latitude)){
            return this;
        }
        handleUpdate(LATITUDE_PROPERTY, getLatitude(), latitude);
        this.latitude = latitude;
        return this;
    }
    public GpsLog updateLongitude(BigDecimal longitude){
        if(Objects.equals(this.longitude, longitude)){
            return this;
        }
        handleUpdate(LONGITUDE_PROPERTY, getLongitude(), longitude);
        this.longitude = longitude;
        return this;
    }
    public GpsLog updateSpeedKmh(Integer speedKmh){
        if(Objects.equals(this.speedKmh, speedKmh)){
            return this;
        }
        handleUpdate(SPEED_KMH_PROPERTY, getSpeedKmh(), speedKmh);
        this.speedKmh = speedKmh;
        return this;
    }
    public GpsLog updateHeading(Integer heading){
        if(Objects.equals(this.heading, heading)){
            return this;
        }
        handleUpdate(HEADING_PROPERTY, getHeading(), heading);
        this.heading = heading;
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
    public GpsLog updateDevice(TelematicsDevice device){
        if(Objects.equals(this.device, device)){
            return this;
        }
        handleUpdate(DEVICE_PROPERTY, getDevice(), device);
        this.device = device;
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
            case "latitude": this.latitude = (BigDecimal) value; break;

            case "longitude": this.longitude = (BigDecimal) value; break;

            case "speedKmh": this.speedKmh = (Integer) value; break;

            case "heading": this.heading = (Integer) value; break;

            case "timestamp": this.timestamp = (LocalDateTime) value; break;

            case "device": this.device = (TelematicsDevice) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "latitude": return this.latitude;
            case "longitude": return this.longitude;
            case "speedKmh": return this.speedKmh;
            case "heading": return this.heading;
            case "timestamp": return this.timestamp;
            case "device": return this.device;
            default: return super.__internalGet(property);
        }
    }

}