package com.doublechaintech.enterpriselogisticsservice.gpslog;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
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
        if(gpsLog.getCreatedAt() == null){
           gpsLog.updateCreatedAt(java.time.LocalDateTime.now());
        }
      }else if(gpsLog.updateItem()){
      }
      checkVehicle(_ctx, gpsLog.getProperty(GpsLog.VEHICLE_PROPERTY), newLocation(_parentLocation, GpsLog.VEHICLE_PROPERTY));
      checkLatitude(_ctx, gpsLog.getProperty(GpsLog.LATITUDE_PROPERTY), newLocation(_parentLocation, GpsLog.LATITUDE_PROPERTY));
      checkLongitude(_ctx, gpsLog.getProperty(GpsLog.LONGITUDE_PROPERTY), newLocation(_parentLocation, GpsLog.LONGITUDE_PROPERTY));
      checkTimestamp(_ctx, gpsLog.getProperty(GpsLog.TIMESTAMP_PROPERTY), newLocation(_parentLocation, GpsLog.TIMESTAMP_PROPERTY));
      checkSpeedKmh(_ctx, gpsLog.getProperty(GpsLog.SPEED_KMH_PROPERTY), newLocation(_parentLocation, GpsLog.SPEED_KMH_PROPERTY));
      checkCreatedAt(_ctx, gpsLog.getProperty(GpsLog.CREATED_AT_PROPERTY), newLocation(_parentLocation, GpsLog.CREATED_AT_PROPERTY));
    }

    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
    public void checkLatitude(UserContext _ctx, String latitude, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, latitude);
    if((latitude == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, latitude);

    }
    public void checkLongitude(UserContext _ctx, String longitude, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, longitude);
    if((longitude == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, longitude);

    }
    public void checkTimestamp(UserContext _ctx, LocalDateTime timestamp, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, timestamp);
    if((timestamp == null)){
        return;
    }
    }
    public void checkSpeedKmh(UserContext _ctx, String speedKmh, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, speedKmh);
    if((speedKmh == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, speedKmh);

    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
}