package com.doublechaintech.enterpriselogisticsservice.fuellog;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FuelLogChecker implements Checker<FuelLog>{

    public String type(){
        return FuelLog.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, FuelLog fuelLog, ObjectLocation _parentLocation){
        if(needCheck(_ctx, fuelLog)){
            markAsChecked(_ctx, fuelLog);
            doCheck(_ctx, fuelLog, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, FuelLog fuelLog, ObjectLocation _parentLocation){
      if((fuelLog == null)){
         return;
      }
      if(fuelLog.newItem()){
        if(fuelLog.getCreatedAt() == null){
           fuelLog.updateCreatedAt(java.time.LocalDateTime.now());
        }
      }else if(fuelLog.updateItem()){
      }
      checkVehicle(_ctx, fuelLog.getProperty(FuelLog.VEHICLE_PROPERTY), newLocation(_parentLocation, FuelLog.VEHICLE_PROPERTY));
      checkFuelAmountLiters(_ctx, fuelLog.getProperty(FuelLog.FUEL_AMOUNT_LITERS_PROPERTY), newLocation(_parentLocation, FuelLog.FUEL_AMOUNT_LITERS_PROPERTY));
      checkCost(_ctx, fuelLog.getProperty(FuelLog.COST_PROPERTY), newLocation(_parentLocation, FuelLog.COST_PROPERTY));
      checkDate(_ctx, fuelLog.getProperty(FuelLog.DATE_PROPERTY), newLocation(_parentLocation, FuelLog.DATE_PROPERTY));
      checkCreatedAt(_ctx, fuelLog.getProperty(FuelLog.CREATED_AT_PROPERTY), newLocation(_parentLocation, FuelLog.CREATED_AT_PROPERTY));
    }

    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
    public void checkFuelAmountLiters(UserContext _ctx, String fuelAmountLiters, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, fuelAmountLiters);
    if((fuelAmountLiters == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, fuelAmountLiters);

    }
    public void checkCost(UserContext _ctx, String cost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, cost);
    if((cost == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, cost);

    }
    public void checkDate(UserContext _ctx, LocalDate date, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, date);
    if((date == null)){
        return;
    }
    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
}