package com.doublechaintech.enterpriselogisticsservice.driverassignment;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
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
        if(driverAssignment.getStartTime() == null){
           driverAssignment.updateStartTime(java.time.LocalDateTime.now());
        }if(driverAssignment.getEndTime() == null){
           driverAssignment.updateEndTime(java.time.LocalDateTime.now());
        }
      }else if(driverAssignment.updateItem()){
        driverAssignment.updateEndTime(java.time.LocalDateTime.now());
      }
      checkStartTime(_ctx, driverAssignment.getProperty(DriverAssignment.START_TIME_PROPERTY), newLocation(_parentLocation, DriverAssignment.START_TIME_PROPERTY));
      checkEndTime(_ctx, driverAssignment.getProperty(DriverAssignment.END_TIME_PROPERTY), newLocation(_parentLocation, DriverAssignment.END_TIME_PROPERTY));
      checkStatus(_ctx, driverAssignment.getProperty(DriverAssignment.STATUS_PROPERTY), newLocation(_parentLocation, DriverAssignment.STATUS_PROPERTY));
      checkVehicle(_ctx, driverAssignment.getProperty(DriverAssignment.VEHICLE_PROPERTY), newLocation(_parentLocation, DriverAssignment.VEHICLE_PROPERTY));
      checkDriver(_ctx, driverAssignment.getProperty(DriverAssignment.DRIVER_PROPERTY), newLocation(_parentLocation, DriverAssignment.DRIVER_PROPERTY));
    }

    public void checkStartTime(UserContext _ctx, LocalDateTime startTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, startTime);
    if((startTime == null)){
        return;
    }
    }
    public void checkEndTime(UserContext _ctx, LocalDateTime endTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, endTime);
    if((endTime == null)){
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
}