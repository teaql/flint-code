package com.doublechaintech.enterpriselogisticsservice.dispatchplan;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberChecker;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class DispatchPlanChecker implements Checker<DispatchPlan>{

    public String type(){
        return DispatchPlan.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, DispatchPlan dispatchPlan, ObjectLocation _parentLocation){
        if(needCheck(_ctx, dispatchPlan)){
            markAsChecked(_ctx, dispatchPlan);
            doCheck(_ctx, dispatchPlan, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, DispatchPlan dispatchPlan, ObjectLocation _parentLocation){
      if((dispatchPlan == null)){
         return;
      }
      if(dispatchPlan.newItem()){
        if(dispatchPlan.getScheduledDeparture() == null){
           dispatchPlan.updateScheduledDeparture(java.time.LocalDateTime.now());
        }if(dispatchPlan.getScheduledArrival() == null){
           dispatchPlan.updateScheduledArrival(java.time.LocalDateTime.now());
        }if(dispatchPlan.getCreatedTime() == null){
           dispatchPlan.updateCreatedTime(java.time.LocalDateTime.now());
        }if(dispatchPlan.getUpdatedTime() == null){
           dispatchPlan.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(dispatchPlan.updateItem()){
        dispatchPlan.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkPlanNumber(_ctx, dispatchPlan.getProperty(DispatchPlan.PLAN_NUMBER_PROPERTY), newLocation(_parentLocation, DispatchPlan.PLAN_NUMBER_PROPERTY));
      checkStatus(_ctx, dispatchPlan.getProperty(DispatchPlan.STATUS_PROPERTY), newLocation(_parentLocation, DispatchPlan.STATUS_PROPERTY));
      checkMovingOrder(_ctx, dispatchPlan.getProperty(DispatchPlan.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, DispatchPlan.MOVING_ORDER_PROPERTY));
      checkVehicle(_ctx, dispatchPlan.getProperty(DispatchPlan.VEHICLE_PROPERTY), newLocation(_parentLocation, DispatchPlan.VEHICLE_PROPERTY));
      checkDriver(_ctx, dispatchPlan.getProperty(DispatchPlan.DRIVER_PROPERTY), newLocation(_parentLocation, DispatchPlan.DRIVER_PROPERTY));
      checkScheduledDeparture(_ctx, dispatchPlan.getProperty(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY), newLocation(_parentLocation, DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY));
      checkScheduledArrival(_ctx, dispatchPlan.getProperty(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY), newLocation(_parentLocation, DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY));
      checkCreatedTime(_ctx, dispatchPlan.getProperty(DispatchPlan.CREATED_TIME_PROPERTY), newLocation(_parentLocation, DispatchPlan.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, dispatchPlan.getProperty(DispatchPlan.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, DispatchPlan.UPDATED_TIME_PROPERTY));
    }

    public void checkPlanNumber(UserContext _ctx, String planNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, planNumber);
    if((planNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, planNumber);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
    }
    public void checkVehicle(UserContext _ctx, Vehicle vehicle, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, vehicle);
    if((vehicle == null)){
        return;
    }
    new VehicleChecker().checkAndFix(_ctx, vehicle, _parentLocation);
    }
    public void checkDriver(UserContext _ctx, StaffMember driver, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, driver);
    if((driver == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, driver, _parentLocation);
    }
    public void checkScheduledDeparture(UserContext _ctx, LocalDateTime scheduledDeparture, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, scheduledDeparture);
    if((scheduledDeparture == null)){
        return;
    }
    }
    public void checkScheduledArrival(UserContext _ctx, LocalDateTime scheduledArrival, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, scheduledArrival);
    if((scheduledArrival == null)){
        return;
    }
    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}