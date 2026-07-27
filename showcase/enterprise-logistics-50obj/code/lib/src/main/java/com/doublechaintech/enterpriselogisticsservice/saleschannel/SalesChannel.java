package com.doublechaintech.enterpriselogisticsservice.saleschannel;

import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
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
public class SalesChannel extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SalesChannel";

    public static final String NAME_PROPERTY = "name";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String CHANNEL_TYPE_PROPERTY = "channelType";
    public static final String IS_ACTIVE_PROPERTY = "isActive";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    public static final String MARKETING_ROI_LIST_PROPERTY = "marketingRoiList";
    private String name;
    private String description;
    private String channelType;
    private Boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private SmartList<MarketingRoi> marketingRoiList;

    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public String getChannelType(){
        return this.channelType;
    }
    public Boolean isIsActive(){
        return this.isActive;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public SmartList<MarketingRoi> getMarketingRoiList(){
        return this.marketingRoiList;
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
    public SalesChannel updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
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
    public SalesChannel updateIsActive(Boolean isActive){
        if(Objects.equals(this.isActive, isActive)){
            return this;
        }
        handleUpdate(IS_ACTIVE_PROPERTY, isIsActive(), isActive);
        this.isActive = isActive;
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
    public SalesChannel updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }
    public SalesChannel addMarketingRoi(MarketingRoi marketingRoi){
        if (marketingRoi == null){
            return this;
        }

        if(null == this.marketingRoiList){
            this.marketingRoiList = new SmartList<>();
        }

        this.marketingRoiList.add(marketingRoi);
        marketingRoi.cacheRelation(MarketingRoi.CHANNEL_PROPERTY, this);
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

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "channelType": this.channelType = (value == null ? null : ((String)value).trim()); break;

            case "isActive": this.isActive = (Boolean) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            case "marketingRoiList": this.marketingRoiList = (SmartList<MarketingRoi>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "description": return this.description;
            case "channelType": return this.channelType;
            case "isActive": return this.isActive;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            case "marketingRoiList": return this.marketingRoiList;
            default: return super.__internalGet(property);
        }
    }

}