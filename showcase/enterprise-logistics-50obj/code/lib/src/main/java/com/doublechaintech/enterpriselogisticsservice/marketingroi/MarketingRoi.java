package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
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
public class MarketingRoi extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "MarketingRoi";

    public static final String CAMPAIGN_PROPERTY = "campaign";
    public static final String CHANNEL_PROPERTY = "channel";
    public static final String SPEND_PROPERTY = "spend";
    public static final String REVENUE_PROPERTY = "revenue";
    public static final String ROI_PERCENTAGE_PROPERTY = "roiPercentage";
    public static final String REPORT_DATE_PROPERTY = "reportDate";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private PromotionCampaign campaign;
    private SalesChannel channel;
    private BigDecimal spend;
    private BigDecimal revenue;
    private BigDecimal roiPercentage;
    private LocalDate reportDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public PromotionCampaign getCampaign(){
        return this.campaign;
    }
    public SalesChannel getChannel(){
        return this.channel;
    }
    public BigDecimal getSpend(){
        return this.spend;
    }
    public BigDecimal getRevenue(){
        return this.revenue;
    }
    public BigDecimal getRoiPercentage(){
        return this.roiPercentage;
    }
    public LocalDate getReportDate(){
        return this.reportDate;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public MarketingRoi updateCampaign(PromotionCampaign campaign){
        if(Objects.equals(this.campaign, campaign)){
            return this;
        }
        handleUpdate(CAMPAIGN_PROPERTY, getCampaign(), campaign);
        this.campaign = campaign;
        return this;
    }
    public MarketingRoi updateChannel(SalesChannel channel){
        if(Objects.equals(this.channel, channel)){
            return this;
        }
        handleUpdate(CHANNEL_PROPERTY, getChannel(), channel);
        this.channel = channel;
        return this;
    }
    public MarketingRoi updateSpend(BigDecimal spend){
        if(Objects.equals(this.spend, spend)){
            return this;
        }
        handleUpdate(SPEND_PROPERTY, getSpend(), spend);
        this.spend = spend;
        return this;
    }
    public MarketingRoi updateRevenue(BigDecimal revenue){
        if(Objects.equals(this.revenue, revenue)){
            return this;
        }
        handleUpdate(REVENUE_PROPERTY, getRevenue(), revenue);
        this.revenue = revenue;
        return this;
    }
    public MarketingRoi updateRoiPercentage(BigDecimal roiPercentage){
        if(Objects.equals(this.roiPercentage, roiPercentage)){
            return this;
        }
        handleUpdate(ROI_PERCENTAGE_PROPERTY, getRoiPercentage(), roiPercentage);
        this.roiPercentage = roiPercentage;
        return this;
    }
    public MarketingRoi updateReportDate(LocalDate reportDate){
        if(Objects.equals(this.reportDate, reportDate)){
            return this;
        }
        handleUpdate(REPORT_DATE_PROPERTY, getReportDate(), reportDate);
        this.reportDate = reportDate;
        return this;
    }
    public MarketingRoi updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public MarketingRoi updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static MarketingRoi refer(Long id){
        MarketingRoi refer = new MarketingRoi();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public MarketingRoi comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<MarketingRoi> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "campaign": this.campaign = (PromotionCampaign) value; break;

            case "channel": this.channel = (SalesChannel) value; break;

            case "spend": this.spend = (BigDecimal) value; break;

            case "revenue": this.revenue = (BigDecimal) value; break;

            case "roiPercentage": this.roiPercentage = (BigDecimal) value; break;

            case "reportDate": this.reportDate = (LocalDate) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "campaign": return this.campaign;
            case "channel": return this.channel;
            case "spend": return this.spend;
            case "revenue": return this.revenue;
            case "roiPercentage": return this.roiPercentage;
            case "reportDate": return this.reportDate;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}