package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;

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
      }else if(vehicleMaintenance.updateItem()){
      }
      checkServiceType(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.SERVICE_TYPE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.SERVICE_TYPE_PROPERTY));
      checkDescription(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.DESCRIPTION_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.DESCRIPTION_PROPERTY));
      checkCost(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.COST_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.COST_PROPERTY));
      checkScheduledDate(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.SCHEDULED_DATE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.SCHEDULED_DATE_PROPERTY));
      checkCompletedDate(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.COMPLETED_DATE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.COMPLETED_DATE_PROPERTY));
      checkStatus(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.STATUS_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.STATUS_PROPERTY));
      checkVehicle(_ctx, vehicleMaintenance.getProperty(VehicleMaintenance.VEHICLE_PROPERTY), newLocation(_parentLocation, VehicleMaintenance.VEHICLE_PROPERTY));
    }

    public void checkServiceType(UserContext _ctx, String serviceType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, serviceType);
    if((serviceType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, serviceType);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkCost(UserContext _ctx, BigDecimal cost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, cost);
    if((cost == null)){
        return;
    }
    }
    public void checkScheduledDate(UserContext _ctx, LocalDate scheduledDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, scheduledDate);
    if((scheduledDate == null)){
        return;
    }
    }
    public void checkCompletedDate(UserContext _ctx, LocalDate completedDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, completedDate);
    if((completedDate == null)){
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
}