package com.doublechaintech.enterpriselogisticsservice.driverassignment;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DriverAssignmentChecker implements Checker<DriverAssignment>{

    public String type(){
        return DriverAssignment.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, DriverAssignment driverAssignment, ObjectLocation _parentLocation){
        if(needCheck(_ctx, driverAssignment)){
            markAsChecked(_ctx, driverAssignment);
            doCheck(_ctx, driverAssignment, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, DriverAssignment driverAssignment, ObjectLocation _parentLocation){
      if((driverAssignment == null)){
         return;
      }
      if(driverAssignment.newItem()){
        if(driverAssignment.getCreatedAt() == null){
           driverAssignment.updateCreatedAt(java.time.LocalDateTime.now());
        }
      }else if(driverAssignment.updateItem()){
      }
      checkVehicle(_ctx, driverAssignment.getProperty(DriverAssignment.VEHICLE_PROPERTY), newLocation(_parentLocation, DriverAssignment.VEHICLE_PROPERTY));
      checkDriver(_ctx, driverAssignment.getProperty(DriverAssignment.DRIVER_PROPERTY), newLocation(_parentLocation, DriverAssignment.DRIVER_PROPERTY));
      checkStartDate(_ctx, driverAssignment.getProperty(DriverAssignment.START_DATE_PROPERTY), newLocation(_parentLocation, DriverAssignment.START_DATE_PROPERTY));
      checkEndDate(_ctx, driverAssignment.getProperty(DriverAssignment.END_DATE_PROPERTY), newLocation(_parentLocation, DriverAssignment.END_DATE_PROPERTY));
      checkStatus(_ctx, driverAssignment.getProperty(DriverAssignment.STATUS_PROPERTY), newLocation(_parentLocation, DriverAssignment.STATUS_PROPERTY));
      checkCreatedAt(_ctx, driverAssignment.getProperty(DriverAssignment.CREATED_AT_PROPERTY), newLocation(_parentLocation, DriverAssignment.CREATED_AT_PROPERTY));
    }

    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
    public void checkDriver(UserContext _ctx, String driver, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, driver);
    if((driver == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, driver);

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
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
}