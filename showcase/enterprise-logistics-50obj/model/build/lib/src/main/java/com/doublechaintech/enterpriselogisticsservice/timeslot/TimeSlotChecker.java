package com.doublechaintech.enterpriselogisticsservice.timeslot;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class TimeSlotChecker implements Checker<TimeSlot>{

    public String type(){
        return TimeSlot.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, TimeSlot timeSlot, ObjectLocation _parentLocation){
        if(needCheck(_ctx, timeSlot)){
            markAsChecked(_ctx, timeSlot);
            doCheck(_ctx, timeSlot, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, TimeSlot timeSlot, ObjectLocation _parentLocation){
      if((timeSlot == null)){
         return;
      }
      if(timeSlot.newItem()){
        if(timeSlot.getCreateTime() == null){
           timeSlot.updateCreateTime(java.time.LocalDateTime.now());
        }
      }else if(timeSlot.updateItem()){
      }
      checkSlotId(_ctx, timeSlot.getProperty(TimeSlot.SLOT_ID_PROPERTY), newLocation(_parentLocation, TimeSlot.SLOT_ID_PROPERTY));
      checkMovingOrder(_ctx, timeSlot.getProperty(TimeSlot.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, TimeSlot.MOVING_ORDER_PROPERTY));
      checkStartTime(_ctx, timeSlot.getProperty(TimeSlot.START_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.START_TIME_PROPERTY));
      checkEndTime(_ctx, timeSlot.getProperty(TimeSlot.END_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.END_TIME_PROPERTY));
      checkStatus(_ctx, timeSlot.getProperty(TimeSlot.STATUS_PROPERTY), newLocation(_parentLocation, TimeSlot.STATUS_PROPERTY));
      checkCreateTime(_ctx, timeSlot.getProperty(TimeSlot.CREATE_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.CREATE_TIME_PROPERTY));
    }

    public void checkSlotId(UserContext _ctx, String slotId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, slotId);
    if((slotId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, slotId);

    }
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
    }
    public void checkStartTime(UserContext _ctx, String startTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, startTime);
    if((startTime == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, startTime);

    }
    public void checkEndTime(UserContext _ctx, String endTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, endTime);
    if((endTime == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, endTime);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkCreateTime(UserContext _ctx, LocalDateTime createTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createTime);
    if((createTime == null)){
        return;
    }
    }
}