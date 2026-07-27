package com.doublechaintech.movingcompanyservice.platform;

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
public class Platform extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "Platform";

    public static final String VERSION_PROPERTY = "version";
    public static final String API_VERSION_PROPERTY = "apiVersion";
    public static final String MAINTENANCE_MODE_PROPERTY = "maintenanceMode";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String version;
    private String apiVersion;
    private Boolean maintenanceMode;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public String getVersion(){
        return this.version;
    }
    public String getApiVersion(){
        return this.apiVersion;
    }
    public Boolean isMaintenanceMode(){
        return this.maintenanceMode;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public Platform updateVersion(String version){
        version = (version == null ? null : version.trim());
        if(Objects.equals(this.version, version)){
            return this;
        }
        handleUpdate(VERSION_PROPERTY, getVersion(), version);
        this.version = version;
        return this;
    }
    public Platform updateApiVersion(String apiVersion){
        apiVersion = (apiVersion == null ? null : apiVersion.trim());
        if(Objects.equals(this.apiVersion, apiVersion)){
            return this;
        }
        handleUpdate(API_VERSION_PROPERTY, getApiVersion(), apiVersion);
        this.apiVersion = apiVersion;
        return this;
    }
    public Platform updateMaintenanceMode(Boolean maintenanceMode){
        if(Objects.equals(this.maintenanceMode, maintenanceMode)){
            return this;
        }
        handleUpdate(MAINTENANCE_MODE_PROPERTY, isMaintenanceMode(), maintenanceMode);
        this.maintenanceMode = maintenanceMode;
        return this;
    }
    public Platform updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public Platform updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static Platform refer(Long id){
        Platform refer = new Platform();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public Platform comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<Platform> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "version": this.version = (value == null ? null : ((String)value).trim()); break;

            case "apiVersion": this.apiVersion = (value == null ? null : ((String)value).trim()); break;

            case "maintenanceMode": this.maintenanceMode = (Boolean) value; break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "version": return this.version;
            case "apiVersion": return this.apiVersion;
            case "maintenanceMode": return this.maintenanceMode;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}