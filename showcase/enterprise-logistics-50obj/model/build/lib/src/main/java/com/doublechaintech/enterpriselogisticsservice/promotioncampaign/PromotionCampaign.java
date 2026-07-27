package com.doublechaintech.enterpriselogisticsservice.promotioncampaign;

import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
import java.math.BigDecimal;
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
public class PromotionCampaign extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "PromotionCampaign";

    public static final String NAME_PROPERTY = "name";
    public static final String START_DATE_PROPERTY = "startDate";
    public static final String END_DATE_PROPERTY = "endDate";
    public static final String BUDGET_PROPERTY = "budget";
    public static final String STATUS_PROPERTY = "status";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    public static final String MARKETING_ROI_LIST_PROPERTY = "marketingRoiList";
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal budget;
    private String status;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;
    private SmartList<MarketingRoi> marketingRoiList;

    public String getName(){
        return this.name;
    }
    public LocalDate getStartDate(){
        return this.startDate;
    }
    public LocalDate getEndDate(){
        return this.endDate;
    }
    public BigDecimal getBudget(){
        return this.budget;
    }
    public String getStatus(){
        return this.status;
    }
    public String getDescription(){
        return this.description;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public SmartList<MarketingRoi> getMarketingRoiList(){
        return this.marketingRoiList;
    }
    public PromotionCampaign updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public PromotionCampaign updateStartDate(LocalDate startDate){
        if(Objects.equals(this.startDate, startDate)){
            return this;
        }
        handleUpdate(START_DATE_PROPERTY, getStartDate(), startDate);
        this.startDate = startDate;
        return this;
    }
    public PromotionCampaign updateEndDate(LocalDate endDate){
        if(Objects.equals(this.endDate, endDate)){
            return this;
        }
        handleUpdate(END_DATE_PROPERTY, getEndDate(), endDate);
        this.endDate = endDate;
        return this;
    }
    public PromotionCampaign updateBudget(BigDecimal budget){
        if(Objects.equals(this.budget, budget)){
            return this;
        }
        handleUpdate(BUDGET_PROPERTY, getBudget(), budget);
        this.budget = budget;
        return this;
    }
    public PromotionCampaign updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public PromotionCampaign updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public PromotionCampaign updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public PromotionCampaign updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }
    public PromotionCampaign addMarketingRoi(MarketingRoi marketingRoi){
        if (marketingRoi == null){
            return this;
        }

        if(null == this.marketingRoiList){
            this.marketingRoiList = new SmartList<>();
        }

        this.marketingRoiList.add(marketingRoi);
        marketingRoi.cacheRelation(MarketingRoi.CAMPAIGN_PROPERTY, this);
        return this;
    }

    public static PromotionCampaign refer(Long id){
        PromotionCampaign refer = new PromotionCampaign();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public PromotionCampaign comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<PromotionCampaign> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "startDate": this.startDate = (LocalDate) value; break;

            case "endDate": this.endDate = (LocalDate) value; break;

            case "budget": this.budget = (BigDecimal) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            case "marketingRoiList": this.marketingRoiList = (SmartList<MarketingRoi>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "startDate": return this.startDate;
            case "endDate": return this.endDate;
            case "budget": return this.budget;
            case "status": return this.status;
            case "description": return this.description;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            case "marketingRoiList": return this.marketingRoiList;
            default: return super.__internalGet(property);
        }
    }

}