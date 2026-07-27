package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
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
    public static final String TOTAL_SPEND_PROPERTY = "totalSpend";
    public static final String TOTAL_REVENUE_PROPERTY = "totalRevenue";
    public static final String ROI_PERCENTAGE_PROPERTY = "roiPercentage";
    public static final String REPORT_DATE_PROPERTY = "reportDate";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private PromotionCampaign campaign;
    private BigDecimal totalSpend;
    private BigDecimal totalRevenue;
    private BigDecimal roiPercentage;
    private LocalDate reportDate;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public PromotionCampaign getCampaign(){
        return this.campaign;
    }
    public BigDecimal getTotalSpend(){
        return this.totalSpend;
    }
    public BigDecimal getTotalRevenue(){
        return this.totalRevenue;
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
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public MarketingRoi updateCampaign(PromotionCampaign campaign){
        if(Objects.equals(this.campaign, campaign)){
            return this;
        }
        handleUpdate(CAMPAIGN_PROPERTY, getCampaign(), campaign);
        this.campaign = campaign;
        return this;
    }
    public MarketingRoi updateTotalSpend(BigDecimal totalSpend){
        if(Objects.equals(this.totalSpend, totalSpend)){
            return this;
        }
        handleUpdate(TOTAL_SPEND_PROPERTY, getTotalSpend(), totalSpend);
        this.totalSpend = totalSpend;
        return this;
    }
    public MarketingRoi updateTotalRevenue(BigDecimal totalRevenue){
        if(Objects.equals(this.totalRevenue, totalRevenue)){
            return this;
        }
        handleUpdate(TOTAL_REVENUE_PROPERTY, getTotalRevenue(), totalRevenue);
        this.totalRevenue = totalRevenue;
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
    public MarketingRoi updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
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

            case "totalSpend": this.totalSpend = (BigDecimal) value; break;

            case "totalRevenue": this.totalRevenue = (BigDecimal) value; break;

            case "roiPercentage": this.roiPercentage = (BigDecimal) value; break;

            case "reportDate": this.reportDate = (LocalDate) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "campaign": return this.campaign;
            case "totalSpend": return this.totalSpend;
            case "totalRevenue": return this.totalRevenue;
            case "roiPercentage": return this.roiPercentage;
            case "reportDate": return this.reportDate;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}