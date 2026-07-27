package com.doublechaintech.enterpriselogisticsservice.promotioncampaign;

import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PromotionCampaignChecker implements Checker<PromotionCampaign>{

    public String type(){
        return PromotionCampaign.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, PromotionCampaign promotionCampaign, ObjectLocation _parentLocation){
        if(needCheck(_ctx, promotionCampaign)){
            markAsChecked(_ctx, promotionCampaign);
            doCheck(_ctx, promotionCampaign, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, PromotionCampaign promotionCampaign, ObjectLocation _parentLocation){
      if((promotionCampaign == null)){
         return;
      }
      if(promotionCampaign.newItem()){
        if(promotionCampaign.getCreatedTime() == null){
           promotionCampaign.updateCreatedTime(java.time.LocalDateTime.now());
        }if(promotionCampaign.getUpdatedTime() == null){
           promotionCampaign.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(promotionCampaign.updateItem()){
        promotionCampaign.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, promotionCampaign.getProperty(PromotionCampaign.NAME_PROPERTY), newLocation(_parentLocation, PromotionCampaign.NAME_PROPERTY));
      checkDescription(_ctx, promotionCampaign.getProperty(PromotionCampaign.DESCRIPTION_PROPERTY), newLocation(_parentLocation, PromotionCampaign.DESCRIPTION_PROPERTY));
      checkStartDate(_ctx, promotionCampaign.getProperty(PromotionCampaign.START_DATE_PROPERTY), newLocation(_parentLocation, PromotionCampaign.START_DATE_PROPERTY));
      checkEndDate(_ctx, promotionCampaign.getProperty(PromotionCampaign.END_DATE_PROPERTY), newLocation(_parentLocation, PromotionCampaign.END_DATE_PROPERTY));
      checkBudget(_ctx, promotionCampaign.getProperty(PromotionCampaign.BUDGET_PROPERTY), newLocation(_parentLocation, PromotionCampaign.BUDGET_PROPERTY));
      checkStatus(_ctx, promotionCampaign.getProperty(PromotionCampaign.STATUS_PROPERTY), newLocation(_parentLocation, PromotionCampaign.STATUS_PROPERTY));
      checkCreatedTime(_ctx, promotionCampaign.getProperty(PromotionCampaign.CREATED_TIME_PROPERTY), newLocation(_parentLocation, PromotionCampaign.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, promotionCampaign.getProperty(PromotionCampaign.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, PromotionCampaign.UPDATED_TIME_PROPERTY));
      for(int i = 0; promotionCampaign.getMarketingRoiList() != null && i < promotionCampaign.getMarketingRoiList().size(); i++){
         MarketingRoi marketingRoi = promotionCampaign.getMarketingRoiList().get(i);
         new MarketingRoiChecker().checkAndFix(_ctx, marketingRoi, newLocation(_parentLocation, PromotionCampaign.MARKETING_ROI_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkStartDate(UserContext _ctx, LocalDate startDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, startDate);
    if((startDate == null)){
        return;
    }
    }
    public void checkEndDate(UserContext _ctx, LocalDate endDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, endDate);
    if((endDate == null)){
        return;
    }
    }
    public void checkBudget(UserContext _ctx, BigDecimal budget, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, budget);
    if((budget == null)){
        return;
    }
    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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