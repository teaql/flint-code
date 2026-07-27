package com.doublechaintech.enterpriselogisticsservice.saleschannel;

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
        }if(salesChannel.getUpdateTime() == null){
           salesChannel.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(salesChannel.updateItem()){
        salesChannel.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, salesChannel.getProperty(SalesChannel.NAME_PROPERTY), newLocation(_parentLocation, SalesChannel.NAME_PROPERTY));
      checkChannelType(_ctx, salesChannel.getProperty(SalesChannel.CHANNEL_TYPE_PROPERTY), newLocation(_parentLocation, SalesChannel.CHANNEL_TYPE_PROPERTY));
      checkUrl(_ctx, salesChannel.getProperty(SalesChannel.URL_PROPERTY), newLocation(_parentLocation, SalesChannel.URL_PROPERTY));
      checkStatus(_ctx, salesChannel.getProperty(SalesChannel.STATUS_PROPERTY), newLocation(_parentLocation, SalesChannel.STATUS_PROPERTY));
      checkCreatedTime(_ctx, salesChannel.getProperty(SalesChannel.CREATED_TIME_PROPERTY), newLocation(_parentLocation, SalesChannel.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, salesChannel.getProperty(SalesChannel.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, SalesChannel.UPDATE_TIME_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkChannelType(UserContext _ctx, String channelType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, channelType);
    if((channelType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, channelType);

    }
    public void checkUrl(UserContext _ctx, String url, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, url);
    if((url == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, url);

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
    public void checkUpdateTime(UserContext _ctx, LocalDateTime updateTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updateTime);
    if((updateTime == null)){
        return;
    }
    }
}