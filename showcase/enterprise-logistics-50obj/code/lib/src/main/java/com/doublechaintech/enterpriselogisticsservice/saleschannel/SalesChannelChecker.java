package com.doublechaintech.enterpriselogisticsservice.saleschannel;

import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class SalesChannelChecker implements Checker<SalesChannel>{

    public String type(){
        return SalesChannel.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, SalesChannel salesChannel, ObjectLocation _parentLocation){
        if(needCheck(_ctx, salesChannel)){
            markAsChecked(_ctx, salesChannel);
            doCheck(_ctx, salesChannel, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, SalesChannel salesChannel, ObjectLocation _parentLocation){
      if((salesChannel == null)){
         return;
      }
      if(salesChannel.newItem()){
        if(salesChannel.getCreatedTime() == null){
           salesChannel.updateCreatedTime(java.time.LocalDateTime.now());
        }if(salesChannel.getUpdatedTime() == null){
           salesChannel.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(salesChannel.updateItem()){
        salesChannel.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, salesChannel.getProperty(SalesChannel.NAME_PROPERTY), newLocation(_parentLocation, SalesChannel.NAME_PROPERTY));
      checkDescription(_ctx, salesChannel.getProperty(SalesChannel.DESCRIPTION_PROPERTY), newLocation(_parentLocation, SalesChannel.DESCRIPTION_PROPERTY));
      checkChannelType(_ctx, salesChannel.getProperty(SalesChannel.CHANNEL_TYPE_PROPERTY), newLocation(_parentLocation, SalesChannel.CHANNEL_TYPE_PROPERTY));
      checkIsActive(_ctx, salesChannel.getProperty(SalesChannel.IS_ACTIVE_PROPERTY), newLocation(_parentLocation, SalesChannel.IS_ACTIVE_PROPERTY));
      checkCreatedTime(_ctx, salesChannel.getProperty(SalesChannel.CREATED_TIME_PROPERTY), newLocation(_parentLocation, SalesChannel.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, salesChannel.getProperty(SalesChannel.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, SalesChannel.UPDATED_TIME_PROPERTY));
      for(int i = 0; salesChannel.getMarketingRoiList() != null && i < salesChannel.getMarketingRoiList().size(); i++){
         MarketingRoi marketingRoi = salesChannel.getMarketingRoiList().get(i);
         new MarketingRoiChecker().checkAndFix(_ctx, marketingRoi, newLocation(_parentLocation, SalesChannel.MARKETING_ROI_LIST_PROPERTY, i));
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
    public void checkChannelType(UserContext _ctx, String channelType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, channelType);
    if((channelType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, channelType);

    }
    public void checkIsActive(UserContext _ctx, Boolean isActive, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, isActive);
    if((isActive == null)){
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