package com.doublechaintech.movingcompanyservice.movingevent;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class MovingEventChecker implements Checker<MovingEvent>{

    public String type(){
        return MovingEvent.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, MovingEvent movingEvent, ObjectLocation _parentLocation){
        if(needCheck(_ctx, movingEvent)){
            markAsChecked(_ctx, movingEvent);
            doCheck(_ctx, movingEvent, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, MovingEvent movingEvent, ObjectLocation _parentLocation){
      if((movingEvent == null)){
         return;
      }
      if(movingEvent.newItem()){
        if(movingEvent.getCreateTime() == null){
           movingEvent.updateCreateTime(java.time.LocalDateTime.now());
        }if(movingEvent.getUpdateTime() == null){
           movingEvent.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(movingEvent.updateItem()){
        movingEvent.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkCustomer(_ctx, movingEvent.getProperty(MovingEvent.CUSTOMER_PROPERTY), newLocation(_parentLocation, MovingEvent.CUSTOMER_PROPERTY));
      checkRoute(_ctx, movingEvent.getProperty(MovingEvent.ROUTE_PROPERTY), newLocation(_parentLocation, MovingEvent.ROUTE_PROPERTY));
      checkTimeSlot(_ctx, movingEvent.getProperty(MovingEvent.TIME_SLOT_PROPERTY), newLocation(_parentLocation, MovingEvent.TIME_SLOT_PROPERTY));
      checkStatus(_ctx, movingEvent.getProperty(MovingEvent.STATUS_PROPERTY), newLocation(_parentLocation, MovingEvent.STATUS_PROPERTY));
      checkScheduledDate(_ctx, movingEvent.getProperty(MovingEvent.SCHEDULED_DATE_PROPERTY), newLocation(_parentLocation, MovingEvent.SCHEDULED_DATE_PROPERTY));
      checkNotes(_ctx, movingEvent.getProperty(MovingEvent.NOTES_PROPERTY), newLocation(_parentLocation, MovingEvent.NOTES_PROPERTY));
      checkCreateTime(_ctx, movingEvent.getProperty(MovingEvent.CREATE_TIME_PROPERTY), newLocation(_parentLocation, MovingEvent.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, movingEvent.getProperty(MovingEvent.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, MovingEvent.UPDATE_TIME_PROPERTY));
    }

    public void checkCustomer(UserContext _ctx, String customer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customer);
    if((customer == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, customer);

    }
    public void checkRoute(UserContext _ctx, String route, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, route);
    if((route == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, route);

    }
    public void checkTimeSlot(UserContext _ctx, String timeSlot, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, timeSlot);
    if((timeSlot == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, timeSlot);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkScheduledDate(UserContext _ctx, LocalDate scheduledDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, scheduledDate);
    if((scheduledDate == null)){
        return;
    }
    }
    public void checkNotes(UserContext _ctx, String notes, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, notes);
    if((notes == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, notes);

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