package com.doublechaintech.enterpriselogisticsservice.vehicle;

import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanChecker;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentChecker;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogChecker;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogChecker;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceChecker;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
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
        if(vehicle.getCreatedAt() == null){
           vehicle.updateCreatedAt(java.time.LocalDateTime.now());
        }if(vehicle.getUpdatedAt() == null){
           vehicle.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(vehicle.updateItem()){
        vehicle.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, vehicle.getProperty(Vehicle.NAME_PROPERTY), newLocation(_parentLocation, Vehicle.NAME_PROPERTY));
      checkLicensePlate(_ctx, vehicle.getProperty(Vehicle.LICENSE_PLATE_PROPERTY), newLocation(_parentLocation, Vehicle.LICENSE_PLATE_PROPERTY));
      checkMake(_ctx, vehicle.getProperty(Vehicle.MAKE_PROPERTY), newLocation(_parentLocation, Vehicle.MAKE_PROPERTY));
      checkModel(_ctx, vehicle.getProperty(Vehicle.MODEL_PROPERTY), newLocation(_parentLocation, Vehicle.MODEL_PROPERTY));
      checkYear(_ctx, vehicle.getProperty(Vehicle.YEAR_PROPERTY), newLocation(_parentLocation, Vehicle.YEAR_PROPERTY));
      checkCapacityKg(_ctx, vehicle.getProperty(Vehicle.CAPACITY_KG_PROPERTY), newLocation(_parentLocation, Vehicle.CAPACITY_KG_PROPERTY));
      checkStatus(_ctx, vehicle.getProperty(Vehicle.STATUS_PROPERTY), newLocation(_parentLocation, Vehicle.STATUS_PROPERTY));
      checkCreatedAt(_ctx, vehicle.getProperty(Vehicle.CREATED_AT_PROPERTY), newLocation(_parentLocation, Vehicle.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, vehicle.getProperty(Vehicle.UPDATED_AT_PROPERTY), newLocation(_parentLocation, Vehicle.UPDATED_AT_PROPERTY));
      for(int i = 0; vehicle.getDispatchPlanList() != null && i < vehicle.getDispatchPlanList().size(); i++){
         DispatchPlan dispatchPlan = vehicle.getDispatchPlanList().get(i);
         new DispatchPlanChecker().checkAndFix(_ctx, dispatchPlan, newLocation(_parentLocation, Vehicle.DISPATCH_PLAN_LIST_PROPERTY, i));
      }
      for(int i = 0; vehicle.getDriverAssignmentList() != null && i < vehicle.getDriverAssignmentList().size(); i++){
         DriverAssignment driverAssignment = vehicle.getDriverAssignmentList().get(i);
         new DriverAssignmentChecker().checkAndFix(_ctx, driverAssignment, newLocation(_parentLocation, Vehicle.DRIVER_ASSIGNMENT_LIST_PROPERTY, i));
      }
      for(int i = 0; vehicle.getGpsLogList() != null && i < vehicle.getGpsLogList().size(); i++){
         GpsLog gpsLog = vehicle.getGpsLogList().get(i);
         new GpsLogChecker().checkAndFix(_ctx, gpsLog, newLocation(_parentLocation, Vehicle.GPS_LOG_LIST_PROPERTY, i));
      }
      for(int i = 0; vehicle.getFuelLogList() != null && i < vehicle.getFuelLogList().size(); i++){
         FuelLog fuelLog = vehicle.getFuelLogList().get(i);
         new FuelLogChecker().checkAndFix(_ctx, fuelLog, newLocation(_parentLocation, Vehicle.FUEL_LOG_LIST_PROPERTY, i));
      }
      for(int i = 0; vehicle.getVehicleMaintenanceList() != null && i < vehicle.getVehicleMaintenanceList().size(); i++){
         VehicleMaintenance vehicleMaintenance = vehicle.getVehicleMaintenanceList().get(i);
         new VehicleMaintenanceChecker().checkAndFix(_ctx, vehicleMaintenance, newLocation(_parentLocation, Vehicle.VEHICLE_MAINTENANCE_LIST_PROPERTY, i));
      }
      for(int i = 0; vehicle.getTelematicsDeviceList() != null && i < vehicle.getTelematicsDeviceList().size(); i++){
         TelematicsDevice telematicsDevice = vehicle.getTelematicsDeviceList().get(i);
         new TelematicsDeviceChecker().checkAndFix(_ctx, telematicsDevice, newLocation(_parentLocation, Vehicle.TELEMATICS_DEVICE_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkLicensePlate(UserContext _ctx, String licensePlate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, licensePlate);
    if((licensePlate == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, licensePlate);

    }
    public void checkMake(UserContext _ctx, String make, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, make);
    if((make == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, make);

    }
    public void checkModel(UserContext _ctx, String model, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, model);
    if((model == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, model);

    }
    public void checkYear(UserContext _ctx, Integer year, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, year);
    if((year == null)){
        return;
    }
    }
    public void checkCapacityKg(UserContext _ctx, BigDecimal capacityKg, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, capacityKg);
    if((capacityKg == null)){
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
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}