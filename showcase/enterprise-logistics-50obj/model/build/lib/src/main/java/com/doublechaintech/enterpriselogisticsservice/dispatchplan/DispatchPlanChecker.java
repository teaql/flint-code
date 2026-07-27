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
        if(dispatchPlan.getCreateTime() == null){
           dispatchPlan.updateCreateTime(java.time.LocalDateTime.now());
        }if(dispatchPlan.getUpdateTime() == null){
           dispatchPlan.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(dispatchPlan.updateItem()){
        dispatchPlan.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkPlanId(_ctx, dispatchPlan.getProperty(DispatchPlan.PLAN_ID_PROPERTY), newLocation(_parentLocation, DispatchPlan.PLAN_ID_PROPERTY));
      checkMovingOrder(_ctx, dispatchPlan.getProperty(DispatchPlan.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, DispatchPlan.MOVING_ORDER_PROPERTY));
      checkVehicle(_ctx, dispatchPlan.getProperty(DispatchPlan.VEHICLE_PROPERTY), newLocation(_parentLocation, DispatchPlan.VEHICLE_PROPERTY));
      checkDriver(_ctx, dispatchPlan.getProperty(DispatchPlan.DRIVER_PROPERTY), newLocation(_parentLocation, DispatchPlan.DRIVER_PROPERTY));
      checkStatus(_ctx, dispatchPlan.getProperty(DispatchPlan.STATUS_PROPERTY), newLocation(_parentLocation, DispatchPlan.STATUS_PROPERTY));
      checkScheduledDeparture(_ctx, dispatchPlan.getProperty(DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY), newLocation(_parentLocation, DispatchPlan.SCHEDULED_DEPARTURE_PROPERTY));
      checkScheduledArrival(_ctx, dispatchPlan.getProperty(DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY), newLocation(_parentLocation, DispatchPlan.SCHEDULED_ARRIVAL_PROPERTY));
      checkCreateTime(_ctx, dispatchPlan.getProperty(DispatchPlan.CREATE_TIME_PROPERTY), newLocation(_parentLocation, DispatchPlan.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, dispatchPlan.getProperty(DispatchPlan.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, DispatchPlan.UPDATE_TIME_PROPERTY));
    }

    public void checkPlanId(UserContext _ctx, String planId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, planId);
    if((planId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, planId);

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
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkScheduledDeparture(UserContext _ctx, String scheduledDeparture, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, scheduledDeparture);
    if((scheduledDeparture == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, scheduledDeparture);

    }
    public void checkScheduledArrival(UserContext _ctx, String scheduledArrival, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, scheduledArrival);
    if((scheduledArrival == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, scheduledArrival);

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