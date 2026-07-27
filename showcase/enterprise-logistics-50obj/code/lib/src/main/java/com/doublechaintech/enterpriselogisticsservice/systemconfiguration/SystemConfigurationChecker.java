package com.doublechaintech.enterpriselogisticsservice.systemconfiguration;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class SystemConfigurationChecker implements Checker<SystemConfiguration>{

    public String type(){
        return SystemConfiguration.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, SystemConfiguration systemConfiguration, ObjectLocation _parentLocation){
        if(needCheck(_ctx, systemConfiguration)){
            markAsChecked(_ctx, systemConfiguration);
            doCheck(_ctx, systemConfiguration, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, SystemConfiguration systemConfiguration, ObjectLocation _parentLocation){
      if((systemConfiguration == null)){
         return;
      }
      if(systemConfiguration.newItem()){
        if(systemConfiguration.getUpdatedAt() == null){
           systemConfiguration.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(systemConfiguration.updateItem()){
        systemConfiguration.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkConfigKey(_ctx, systemConfiguration.getProperty(SystemConfiguration.CONFIG_KEY_PROPERTY), newLocation(_parentLocation, SystemConfiguration.CONFIG_KEY_PROPERTY));
      checkConfigValue(_ctx, systemConfiguration.getProperty(SystemConfiguration.CONFIG_VALUE_PROPERTY), newLocation(_parentLocation, SystemConfiguration.CONFIG_VALUE_PROPERTY));
      checkDescription(_ctx, systemConfiguration.getProperty(SystemConfiguration.DESCRIPTION_PROPERTY), newLocation(_parentLocation, SystemConfiguration.DESCRIPTION_PROPERTY));
      checkUpdatedAt(_ctx, systemConfiguration.getProperty(SystemConfiguration.UPDATED_AT_PROPERTY), newLocation(_parentLocation, SystemConfiguration.UPDATED_AT_PROPERTY));
    }

    public void checkConfigKey(UserContext _ctx, String configKey, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, configKey);
    if((configKey == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, configKey);

    }
    public void checkConfigValue(UserContext _ctx, String configValue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, configValue);
    if((configValue == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, configValue);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}