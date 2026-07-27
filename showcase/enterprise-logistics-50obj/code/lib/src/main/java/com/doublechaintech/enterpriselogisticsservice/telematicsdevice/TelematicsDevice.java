package com.doublechaintech.enterpriselogisticsservice.telematicsdevice;

import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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
public class TelematicsDevice extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "TelematicsDevice";

    public static final String DEVICE_ID_PROPERTY = "deviceId";
    public static final String IMEI_PROPERTY = "imei";
    public static final String STATUS_PROPERTY = "status";
    public static final String VEHICLE_PROPERTY = "vehicle";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String GPS_LOG_LIST_PROPERTY = "gpsLogList";
    private String deviceId;
    private String imei;
    private String status;
    private Vehicle vehicle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<GpsLog> gpsLogList;

    public String getDeviceId(){
        return this.deviceId;
    }
    public String getImei(){
        return this.imei;
    }
    public String getStatus(){
        return this.status;
    }
    public Vehicle getVehicle(){
        return this.vehicle;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SmartList<GpsLog> getGpsLogList(){
        return this.gpsLogList;
    }
    public TelematicsDevice updateDeviceId(String deviceId){
        deviceId = (deviceId == null ? null : deviceId.trim());
        if(Objects.equals(this.deviceId, deviceId)){
            return this;
        }
        handleUpdate(DEVICE_ID_PROPERTY, getDeviceId(), deviceId);
        this.deviceId = deviceId;
        return this;
    }
    public TelematicsDevice updateImei(String imei){
        imei = (imei == null ? null : imei.trim());
        if(Objects.equals(this.imei, imei)){
            return this;
        }
        handleUpdate(IMEI_PROPERTY, getImei(), imei);
        this.imei = imei;
        return this;
    }
    public TelematicsDevice updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public TelematicsDevice updateVehicle(Vehicle vehicle){
        if(Objects.equals(this.vehicle, vehicle)){
            return this;
        }
        handleUpdate(VEHICLE_PROPERTY, getVehicle(), vehicle);
        this.vehicle = vehicle;
        return this;
    }
    public TelematicsDevice updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public TelematicsDevice updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public TelematicsDevice addGpsLog(GpsLog gpsLog){
        if (gpsLog == null){
            return this;
        }

        if(null == this.gpsLogList){
            this.gpsLogList = new SmartList<>();
        }

        this.gpsLogList.add(gpsLog);
        gpsLog.cacheRelation(GpsLog.DEVICE_PROPERTY, this);
        return this;
    }

    public static TelematicsDevice refer(Long id){
        TelematicsDevice refer = new TelematicsDevice();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public TelematicsDevice comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<TelematicsDevice> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "deviceId": this.deviceId = (value == null ? null : ((String)value).trim()); break;

            case "imei": this.imei = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "vehicle": this.vehicle = (Vehicle) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "gpsLogList": this.gpsLogList = (SmartList<GpsLog>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "deviceId": return this.deviceId;
            case "imei": return this.imei;
            case "status": return this.status;
            case "vehicle": return this.vehicle;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "gpsLogList": return this.gpsLogList;
            default: return super.__internalGet(property);
        }
    }

}