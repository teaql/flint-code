package com.doublechaintech.enterpriselogisticsservice.fuellog;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;

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
      }else if(fuelLog.updateItem()){
      }
      checkLiters(_ctx, fuelLog.getProperty(FuelLog.LITERS_PROPERTY), newLocation(_parentLocation, FuelLog.LITERS_PROPERTY));
      checkCost(_ctx, fuelLog.getProperty(FuelLog.COST_PROPERTY), newLocation(_parentLocation, FuelLog.COST_PROPERTY));
      checkOdometerKm(_ctx, fuelLog.getProperty(FuelLog.ODOMETER_KM_PROPERTY), newLocation(_parentLocation, FuelLog.ODOMETER_KM_PROPERTY));
      checkStationName(_ctx, fuelLog.getProperty(FuelLog.STATION_NAME_PROPERTY), newLocation(_parentLocation, FuelLog.STATION_NAME_PROPERTY));
      checkDate(_ctx, fuelLog.getProperty(FuelLog.DATE_PROPERTY), newLocation(_parentLocation, FuelLog.DATE_PROPERTY));
      checkVehicle(_ctx, fuelLog.getProperty(FuelLog.VEHICLE_PROPERTY), newLocation(_parentLocation, FuelLog.VEHICLE_PROPERTY));
    }

    public void checkLiters(UserContext _ctx, BigDecimal liters, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, liters);
    if((liters == null)){
        return;
    }
    }
    public void checkCost(UserContext _ctx, BigDecimal cost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, cost);
    if((cost == null)){
        return;
    }
    }
    public void checkOdometerKm(UserContext _ctx, Integer odometerKm, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, odometerKm);
    if((odometerKm == null)){
        return;
    }
    }
    public void checkStationName(UserContext _ctx, String stationName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, stationName);
    if((stationName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, stationName);

    }
    public void checkDate(UserContext _ctx, LocalDate date, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, date);
    if((date == null)){
        return;
    }
    }
    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
}