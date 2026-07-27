package com.doublechaintech.movingcompanyservice.vehicle;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleChecker implements Checker<Vehicle>{

    public String type(){
        return Vehicle.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
        if(needCheck(_ctx, vehicle)){
            markAsChecked(_ctx, vehicle);
            doCheck(_ctx, vehicle, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
      if((vehicle == null)){
         return;
      }
      if(vehicle.newItem()){
        if(vehicle.getCreateTime() == null){
           vehicle.updateCreateTime(java.time.LocalDateTime.now());
        }if(vehicle.getUpdateTime() == null){
           vehicle.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(vehicle.updateItem()){
        vehicle.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkInternalType(_ctx, vehicle.getProperty(Vehicle.INTERNAL_TYPE_PROPERTY), newLocation(_parentLocation, Vehicle.INTERNAL_TYPE_PROPERTY));
      checkDisplayName(_ctx, vehicle.getProperty(Vehicle.DISPLAY_NAME_PROPERTY), newLocation(_parentLocation, Vehicle.DISPLAY_NAME_PROPERTY));
      checkVehicleType(_ctx, vehicle.getProperty(Vehicle.VEHICLE_TYPE_PROPERTY), newLocation(_parentLocation, Vehicle.VEHICLE_TYPE_PROPERTY));
      checkLicensePlate(_ctx, vehicle.getProperty(Vehicle.LICENSE_PLATE_PROPERTY), newLocation(_parentLocation, Vehicle.LICENSE_PLATE_PROPERTY));
      checkCapacityCubicMeters(_ctx, vehicle.getProperty(Vehicle.CAPACITY_CUBIC_METERS_PROPERTY), newLocation(_parentLocation, Vehicle.CAPACITY_CUBIC_METERS_PROPERTY));
      checkPurchaseDate(_ctx, vehicle.getProperty(Vehicle.PURCHASE_DATE_PROPERTY), newLocation(_parentLocation, Vehicle.PURCHASE_DATE_PROPERTY));
      checkStatus(_ctx, vehicle.getProperty(Vehicle.STATUS_PROPERTY), newLocation(_parentLocation, Vehicle.STATUS_PROPERTY));
      checkLastMaintenanceDate(_ctx, vehicle.getProperty(Vehicle.LAST_MAINTENANCE_DATE_PROPERTY), newLocation(_parentLocation, Vehicle.LAST_MAINTENANCE_DATE_PROPERTY));
      checkNextMaintenanceDate(_ctx, vehicle.getProperty(Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY), newLocation(_parentLocation, Vehicle.NEXT_MAINTENANCE_DATE_PROPERTY));
      checkCreateTime(_ctx, vehicle.getProperty(Vehicle.CREATE_TIME_PROPERTY), newLocation(_parentLocation, Vehicle.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, vehicle.getProperty(Vehicle.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, Vehicle.UPDATE_TIME_PROPERTY));
    }

    public void checkInternalType(UserContext _ctx, String internalType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, internalType);
    if((internalType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, internalType);

    }
    public void checkDisplayName(UserContext _ctx, String displayName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, displayName);
    if((displayName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, displayName);

    }
    public void checkVehicleType(UserContext _ctx, String vehicleType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicleType);
    if((vehicleType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, vehicleType);

    }
    public void checkLicensePlate(UserContext _ctx, String licensePlate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, licensePlate);
    if((licensePlate == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, licensePlate);

    }
    public void checkCapacityCubicMeters(UserContext _ctx, BigDecimal capacityCubicMeters, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, capacityCubicMeters);
    if((capacityCubicMeters == null)){
        return;
    }
    }
    public void checkPurchaseDate(UserContext _ctx, LocalDate purchaseDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, purchaseDate);
    if((purchaseDate == null)){
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
    public void checkLastMaintenanceDate(UserContext _ctx, LocalDate lastMaintenanceDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, lastMaintenanceDate);
    if((lastMaintenanceDate == null)){
        return;
    }
    }
    public void checkNextMaintenanceDate(UserContext _ctx, LocalDate nextMaintenanceDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, nextMaintenanceDate);
    if((nextMaintenanceDate == null)){
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