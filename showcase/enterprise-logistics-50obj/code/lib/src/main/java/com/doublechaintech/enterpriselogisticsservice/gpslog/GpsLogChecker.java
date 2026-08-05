package com.doublechaintech.enterpriselogisticsservice.gpslog;

import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GpsLogChecker implements Checker<GpsLog>{

    public String type(){
        return GpsLog.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, GpsLog gpsLog, ObjectLocation _parentLocation){
        if(needCheck(_ctx, gpsLog)){
            markAsChecked(_ctx, gpsLog);
            doCheck(_ctx, gpsLog, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, GpsLog gpsLog, ObjectLocation _parentLocation){
      if((gpsLog == null)){
         return;
      }
      if(gpsLog.newItem()){
        if(gpsLog.getTimestamp() == null){
           gpsLog.updateTimestamp(java.time.LocalDateTime.now());
        }
      }else if(gpsLog.updateItem()){
      }
      checkLatitude(_ctx, gpsLog.getProperty(GpsLog.LATITUDE_PROPERTY), newLocation(_parentLocation, GpsLog.LATITUDE_PROPERTY));
      checkLongitude(_ctx, gpsLog.getProperty(GpsLog.LONGITUDE_PROPERTY), newLocation(_parentLocation, GpsLog.LONGITUDE_PROPERTY));
      checkSpeedKmh(_ctx, gpsLog.getProperty(GpsLog.SPEED_KMH_PROPERTY), newLocation(_parentLocation, GpsLog.SPEED_KMH_PROPERTY));
      checkHeading(_ctx, gpsLog.getProperty(GpsLog.HEADING_PROPERTY), newLocation(_parentLocation, GpsLog.HEADING_PROPERTY));
      checkTimestamp(_ctx, gpsLog.getProperty(GpsLog.TIMESTAMP_PROPERTY), newLocation(_parentLocation, GpsLog.TIMESTAMP_PROPERTY));
      checkDevice(_ctx, gpsLog.getProperty(GpsLog.DEVICE_PROPERTY), newLocation(_parentLocation, GpsLog.DEVICE_PROPERTY));
    }

    public void checkLatitude(UserContext _ctx, BigDecimal latitude, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, latitude);
    if((latitude == null)){
        return;
    }
    }
    public void checkLongitude(UserContext _ctx, BigDecimal longitude, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, longitude);
    if((longitude == null)){
        return;
    }
    }
    public void checkSpeedKmh(UserContext _ctx, Integer speedKmh, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, speedKmh);
    if((speedKmh == null)){
        return;
    }
    }
    public void checkHeading(UserContext _ctx, Integer heading, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, heading);
    if((heading == null)){
        return;
    }
    }
    public void checkTimestamp(UserContext _ctx, LocalDateTime timestamp, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, timestamp);
    if((timestamp == null)){
        return;
    }
    }
    public void checkDevice(UserContext _ctx, TelematicsDevice device, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, device);
    if((device == null)){
        return;
    }
    new TelematicsDeviceChecker().checkAndFix(_ctx, device, _parentLocation);
    }
}