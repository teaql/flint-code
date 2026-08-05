package com.doublechaintech.movingcompanyservice.platform;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class PlatformChecker implements Checker<Platform>{

    public String type(){
        return Platform.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Platform platform, ObjectLocation _parentLocation){
        if(needCheck(_ctx, platform)){
            markAsChecked(_ctx, platform);
            doCheck(_ctx, platform, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Platform platform, ObjectLocation _parentLocation){
      if((platform == null)){
         return;
      }
      if(platform.newItem()){
        if(platform.getCreateTime() == null){
           platform.updateCreateTime(java.time.LocalDateTime.now());
        }if(platform.getUpdateTime() == null){
           platform.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(platform.updateItem()){
        platform.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkVersion(_ctx, platform.getProperty(Platform.VERSION_PROPERTY), newLocation(_parentLocation, Platform.VERSION_PROPERTY));
      checkApiVersion(_ctx, platform.getProperty(Platform.API_VERSION_PROPERTY), newLocation(_parentLocation, Platform.API_VERSION_PROPERTY));
      checkMaintenanceMode(_ctx, platform.getProperty(Platform.MAINTENANCE_MODE_PROPERTY), newLocation(_parentLocation, Platform.MAINTENANCE_MODE_PROPERTY));
      checkCreateTime(_ctx, platform.getProperty(Platform.CREATE_TIME_PROPERTY), newLocation(_parentLocation, Platform.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, platform.getProperty(Platform.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, Platform.UPDATE_TIME_PROPERTY));
    }

    public void checkVersion(UserContext _ctx, String version, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, version);
    if((version == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, version);

    }
    public void checkApiVersion(UserContext _ctx, String apiVersion, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, apiVersion);
    if((apiVersion == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, apiVersion);

    }
    public void checkMaintenanceMode(UserContext _ctx, Boolean maintenanceMode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, maintenanceMode);
    if((maintenanceMode == null)){
        return;
    }
    }
    public void checkCreateTime(UserContext _ctx, LocalDateTime createTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createTime);
    if((createTime == null)){
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