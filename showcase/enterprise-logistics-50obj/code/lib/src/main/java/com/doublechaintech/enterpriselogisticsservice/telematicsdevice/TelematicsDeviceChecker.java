package com.doublechaintech.enterpriselogisticsservice.telematicsdevice;

import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogChecker;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class TelematicsDeviceChecker implements Checker<TelematicsDevice>{

    public String type(){
        return TelematicsDevice.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, TelematicsDevice telematicsDevice, ObjectLocation _parentLocation){
        if(needCheck(_ctx, telematicsDevice)){
            markAsChecked(_ctx, telematicsDevice);
            doCheck(_ctx, telematicsDevice, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, TelematicsDevice telematicsDevice, ObjectLocation _parentLocation){
      if((telematicsDevice == null)){
         return;
      }
      if(telematicsDevice.newItem()){
        if(telematicsDevice.getCreatedAt() == null){
           telematicsDevice.updateCreatedAt(java.time.LocalDateTime.now());
        }if(telematicsDevice.getUpdatedAt() == null){
           telematicsDevice.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(telematicsDevice.updateItem()){
        telematicsDevice.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkDeviceId(_ctx, telematicsDevice.getProperty(TelematicsDevice.DEVICE_ID_PROPERTY), newLocation(_parentLocation, TelematicsDevice.DEVICE_ID_PROPERTY));
      checkImei(_ctx, telematicsDevice.getProperty(TelematicsDevice.IMEI_PROPERTY), newLocation(_parentLocation, TelematicsDevice.IMEI_PROPERTY));
      checkStatus(_ctx, telematicsDevice.getProperty(TelematicsDevice.STATUS_PROPERTY), newLocation(_parentLocation, TelematicsDevice.STATUS_PROPERTY));
      checkVehicle(_ctx, telematicsDevice.getProperty(TelematicsDevice.VEHICLE_PROPERTY), newLocation(_parentLocation, TelematicsDevice.VEHICLE_PROPERTY));
      checkCreatedAt(_ctx, telematicsDevice.getProperty(TelematicsDevice.CREATED_AT_PROPERTY), newLocation(_parentLocation, TelematicsDevice.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, telematicsDevice.getProperty(TelematicsDevice.UPDATED_AT_PROPERTY), newLocation(_parentLocation, TelematicsDevice.UPDATED_AT_PROPERTY));
      for(int i = 0; telematicsDevice.getGpsLogList() != null && i < telematicsDevice.getGpsLogList().size(); i++){
         GpsLog gpsLog = telematicsDevice.getGpsLogList().get(i);
         new GpsLogChecker().checkAndFix(_ctx, gpsLog, newLocation(_parentLocation, TelematicsDevice.GPS_LOG_LIST_PROPERTY, i));
      }
    }

    public void checkDeviceId(UserContext _ctx, String deviceId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, deviceId);
    if((deviceId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, deviceId);

    }
    public void checkImei(UserContext _ctx, String imei, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, imei);
    if((imei == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, imei);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}