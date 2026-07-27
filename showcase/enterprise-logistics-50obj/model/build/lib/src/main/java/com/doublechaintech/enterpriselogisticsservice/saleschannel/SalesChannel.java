package com.doublechaintech.enterpriselogisticsservice.saleschannel;

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
public class SalesChannel extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SalesChannel";

    public static final String NAME_PROPERTY = "name";
    public static final String CHANNEL_TYPE_PROPERTY = "channelType";
    public static final String URL_PROPERTY = "url";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String name;
    private String channelType;
    private String url;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getName(){
        return this.name;
    }
    public String getChannelType(){
        return this.channelType;
    }
    public String getUrl(){
        return this.url;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public SalesChannel updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public SalesChannel updateChannelType(String channelType){
        channelType = (channelType == null ? null : channelType.trim());
        if(Objects.equals(this.channelType, channelType)){
            return this;
        }
        handleUpdate(CHANNEL_TYPE_PROPERTY, getChannelType(), channelType);
        this.channelType = channelType;
        return this;
    }
    public SalesChannel updateUrl(String url){
        url = (url == null ? null : url.trim());
        if(Objects.equals(this.url, url)){
            return this;
        }
        handleUpdate(URL_PROPERTY, getUrl(), url);
        this.url = url;
        return this;
    }
    public SalesChannel updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public SalesChannel updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public SalesChannel updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static SalesChannel refer(Long id){
        SalesChannel refer = new SalesChannel();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public SalesChannel comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<SalesChannel> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "channelType": this.channelType = (value == null ? null : ((String)value).trim()); break;

            case "url": this.url = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "channelType": return this.channelType;
            case "url": return this.url;
            case "status": return this.status;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}