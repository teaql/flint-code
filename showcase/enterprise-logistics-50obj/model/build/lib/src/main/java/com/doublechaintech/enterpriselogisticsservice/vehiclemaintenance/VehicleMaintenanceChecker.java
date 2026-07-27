package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleMaintenanceChecker implements Checker<VehicleMaintenance>{

    public String type(){
        return VehicleMaintenance.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, VehicleMaintenance vehicleMaintenance, ObjectLocation _parentLocation){
        if(needCheck(_ctx, vehicleMaintenance)){
            markAsChecked(_ctx, vehicleMaintenance);
            doCheck(_ctx, vehicleMaintenance, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, VehicleMaintenance vehicleMaintenance, ObjectLocation _parentLocation){
      if((vehicleMaintenance == null)){
         return;
      }
      if(vehicleMaintenance.newItem()){
        if(vehicleMaintenance.getCreatedAt() == null){
           vehicleMaintenance.updateCreatedAt(java.time.LocalDateTime.now());
        }
      }else if(vehicleMaintenance.updateItem()){
      }
      checkVehicle(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.VEHICLE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.VEHICLE_PROPERTY));
      checkServiceType(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.SERVICE_TYPE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.SERVICE_TYPE_PROPERTY));
      checkServiceDate(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.SERVICE_DATE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.SERVICE_DATE_PROPERTY));
      checkCost(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.COST_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.COST_PROPERTY));
      checkStatus(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.STATUS_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.STATUS_PROPERTY));
      checkCreatedAt(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.CREATED_AT_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.CREATED_AT_PROPERTY));
    }

    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
    public void checkServiceType(UserContext _ctx, String serviceType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, serviceType);
    if((serviceType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, serviceType);

    }
    public void checkServiceDate(UserContext _ctx, LocalDate serviceDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, serviceDate);
    if((serviceDate == null)){
        return;
    }
    }
    public void checkCost(UserContext _ctx, String cost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, cost);
    if((cost == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, cost);

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