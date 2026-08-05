package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MarketingRoiChecker implements Checker<MarketingRoi>{

    public String type(){
        return MarketingRoi.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, MarketingRoi marketingRoi, ObjectLocation _parentLocation){
        if(needCheck(_ctx, marketingRoi)){
            markAsChecked(_ctx, marketingRoi);
            doCheck(_ctx, marketingRoi, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, MarketingRoi marketingRoi, ObjectLocation _parentLocation){
      if((marketingRoi == null)){
         return;
      }
      if(marketingRoi.newItem()){
        if(marketingRoi.getCreatedTime() == null){
           marketingRoi.updateCreatedTime(java.time.LocalDateTime.now());
        }if(marketingRoi.getUpdateTime() == null){
           marketingRoi.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(marketingRoi.updateItem()){
        marketingRoi.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkCampaign(_ctx, marketingRoi.getProperty(MarketingRoi.CAMPAIGN_PROPERTY), newLocation(_parentLocation, MarketingRoi.CAMPAIGN_PROPERTY));
      checkTotalSpend(_ctx, marketingRoi.getProperty(MarketingRoi.TOTAL_SPEND_PROPERTY), newLocation(_parentLocation, MarketingRoi.TOTAL_SPEND_PROPERTY));
      checkTotalRevenue(_ctx, marketingRoi.getProperty(MarketingRoi.TOTAL_REVENUE_PROPERTY), newLocation(_parentLocation, MarketingRoi.TOTAL_REVENUE_PROPERTY));
      checkRoiPercentage(_ctx, marketingRoi.getProperty(MarketingRoi.ROI_PERCENTAGE_PROPERTY), newLocation(_parentLocation, MarketingRoi.ROI_PERCENTAGE_PROPERTY));
      checkReportDate(_ctx, marketingRoi.getProperty(MarketingRoi.REPORT_DATE_PROPERTY), newLocation(_parentLocation, MarketingRoi.REPORT_DATE_PROPERTY));
      checkCreatedTime(_ctx, marketingRoi.getProperty(MarketingRoi.CREATED_TIME_PROPERTY), newLocation(_parentLocation, MarketingRoi.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, marketingRoi.getProperty(MarketingRoi.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, MarketingRoi.UPDATE_TIME_PROPERTY));
    }

    public void checkCampaign(UserContext _ctx, PromotionCampaign campaign, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, campaign);
    if((campaign == null)){
        return;
    }
    new PromotionCampaignChecker().checkAndFix(_ctx, campaign, _parentLocation);
    }
    public void checkTotalSpend(UserContext _ctx, BigDecimal totalSpend, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalSpend);
    if((totalSpend == null)){
        return;
    }
    }
    public void checkTotalRevenue(UserContext _ctx, BigDecimal totalRevenue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalRevenue);
    if((totalRevenue == null)){
        return;
    }
    }
    public void checkRoiPercentage(UserContext _ctx, BigDecimal roiPercentage, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, roiPercentage);
    if((roiPercentage == null)){
        return;
    }
    }
    public void checkReportDate(UserContext _ctx, LocalDate reportDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, reportDate);
    if((reportDate == null)){
        return;
    }
    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
    public void checkUpdateTime(UserContext _ctx, LocalDateTime updateTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updateTime);
    if((updateTime == null)){
        return;
    }
    }
}