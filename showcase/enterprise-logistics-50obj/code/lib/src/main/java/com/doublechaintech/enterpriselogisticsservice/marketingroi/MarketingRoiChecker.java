package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignChecker;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelChecker;
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
        }if(marketingRoi.getUpdatedTime() == null){
           marketingRoi.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(marketingRoi.updateItem()){
        marketingRoi.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkCampaign(_ctx, marketingRoi.getProperty(MarketingRoi.CAMPAIGN_PROPERTY), newLocation(_parentLocation, MarketingRoi.CAMPAIGN_PROPERTY));
      checkChannel(_ctx, marketingRoi.getProperty(MarketingRoi.CHANNEL_PROPERTY), newLocation(_parentLocation, MarketingRoi.CHANNEL_PROPERTY));
      checkSpend(_ctx, marketingRoi.getProperty(MarketingRoi.SPEND_PROPERTY), newLocation(_parentLocation, MarketingRoi.SPEND_PROPERTY));
      checkRevenue(_ctx, marketingRoi.getProperty(MarketingRoi.REVENUE_PROPERTY), newLocation(_parentLocation, MarketingRoi.REVENUE_PROPERTY));
      checkRoiPercentage(_ctx, marketingRoi.getProperty(MarketingRoi.ROI_PERCENTAGE_PROPERTY), newLocation(_parentLocation, MarketingRoi.ROI_PERCENTAGE_PROPERTY));
      checkReportDate(_ctx, marketingRoi.getProperty(MarketingRoi.REPORT_DATE_PROPERTY), newLocation(_parentLocation, MarketingRoi.REPORT_DATE_PROPERTY));
      checkCreatedTime(_ctx, marketingRoi.getProperty(MarketingRoi.CREATED_TIME_PROPERTY), newLocation(_parentLocation, MarketingRoi.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, marketingRoi.getProperty(MarketingRoi.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, MarketingRoi.UPDATED_TIME_PROPERTY));
    }

    public void checkCampaign(UserContext _ctx, PromotionCampaign campaign, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, campaign);
    if((campaign == null)){
        return;
    }
    new PromotionCampaignChecker().checkAndFix(_ctx, campaign, _parentLocation);
    }
    public void checkChannel(UserContext _ctx, SalesChannel channel, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, channel);
    if((channel == null)){
        return;
    }
    new SalesChannelChecker().checkAndFix(_ctx, channel, _parentLocation);
    }
    public void checkSpend(UserContext _ctx, BigDecimal spend, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, spend);
    if((spend == null)){
        return;
    }
    }
    public void checkRevenue(UserContext _ctx, BigDecimal revenue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, revenue);
    if((revenue == null)){
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
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}