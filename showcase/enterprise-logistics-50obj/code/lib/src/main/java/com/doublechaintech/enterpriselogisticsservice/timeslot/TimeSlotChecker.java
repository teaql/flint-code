package com.doublechaintech.enterpriselogisticsservice.timeslot;

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
        if(timeSlot.getStartTime() == null){
           timeSlot.updateStartTime(java.time.LocalDateTime.now());
        }if(timeSlot.getEndTime() == null){
           timeSlot.updateEndTime(java.time.LocalDateTime.now());
        }if(timeSlot.getCreatedTime() == null){
           timeSlot.updateCreatedTime(java.time.LocalDateTime.now());
        }if(timeSlot.getUpdatedTime() == null){
           timeSlot.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(timeSlot.updateItem()){
        timeSlot.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkSlotCode(_ctx, timeSlot.getProperty(TimeSlot.SLOT_CODE_PROPERTY), newLocation(_parentLocation, TimeSlot.SLOT_CODE_PROPERTY));
      checkStartTime(_ctx, timeSlot.getProperty(TimeSlot.START_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.START_TIME_PROPERTY));
      checkEndTime(_ctx, timeSlot.getProperty(TimeSlot.END_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.END_TIME_PROPERTY));
      checkCapacity(_ctx, timeSlot.getProperty(TimeSlot.CAPACITY_PROPERTY), newLocation(_parentLocation, TimeSlot.CAPACITY_PROPERTY));
      checkAvailableSpots(_ctx, timeSlot.getProperty(TimeSlot.AVAILABLE_SPOTS_PROPERTY), newLocation(_parentLocation, TimeSlot.AVAILABLE_SPOTS_PROPERTY));
      checkCreatedTime(_ctx, timeSlot.getProperty(TimeSlot.CREATED_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, timeSlot.getProperty(TimeSlot.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, TimeSlot.UPDATED_TIME_PROPERTY));
    }

    public void checkSlotCode(UserContext _ctx, String slotCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, slotCode);
    if((slotCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, slotCode);

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
    public void checkCapacity(UserContext _ctx, Integer capacity, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, capacity);
    if((capacity == null)){
        return;
    }
    }
    public void checkAvailableSpots(UserContext _ctx, Integer availableSpots, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, availableSpots);
    if((availableSpots == null)){
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