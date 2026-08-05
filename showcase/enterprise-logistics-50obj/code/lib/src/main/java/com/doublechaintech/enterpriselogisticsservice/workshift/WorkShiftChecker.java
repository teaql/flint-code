package com.doublechaintech.enterpriselogisticsservice.workshift;

import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WorkShiftChecker implements Checker<WorkShift>{

    public String type(){
        return WorkShift.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, WorkShift workShift, ObjectLocation _parentLocation){
        if(needCheck(_ctx, workShift)){
            markAsChecked(_ctx, workShift);
            doCheck(_ctx, workShift, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, WorkShift workShift, ObjectLocation _parentLocation){
      if((workShift == null)){
         return;
      }
      if(workShift.newItem()){
        if(workShift.getCreatedAt() == null){
           workShift.updateCreatedAt(java.time.LocalDateTime.now());
        }if(workShift.getUpdatedAt() == null){
           workShift.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(workShift.updateItem()){
        workShift.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, workShift.getProperty(WorkShift.NAME_PROPERTY), newLocation(_parentLocation, WorkShift.NAME_PROPERTY));
      checkStartTime(_ctx, workShift.getProperty(WorkShift.START_TIME_PROPERTY), newLocation(_parentLocation, WorkShift.START_TIME_PROPERTY));
      checkEndTime(_ctx, workShift.getProperty(WorkShift.END_TIME_PROPERTY), newLocation(_parentLocation, WorkShift.END_TIME_PROPERTY));
      checkShiftDate(_ctx, workShift.getProperty(WorkShift.SHIFT_DATE_PROPERTY), newLocation(_parentLocation, WorkShift.SHIFT_DATE_PROPERTY));
      checkCreatedAt(_ctx, workShift.getProperty(WorkShift.CREATED_AT_PROPERTY), newLocation(_parentLocation, WorkShift.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, workShift.getProperty(WorkShift.UPDATED_AT_PROPERTY), newLocation(_parentLocation, WorkShift.UPDATED_AT_PROPERTY));
      for(int i = 0; workShift.getWorkedHoursList() != null && i < workShift.getWorkedHoursList().size(); i++){
         WorkedHours workedHours = workShift.getWorkedHoursList().get(i);
         new WorkedHoursChecker().checkAndFix(_ctx, workedHours, newLocation(_parentLocation, WorkShift.WORKED_HOURS_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkStartTime(UserContext _ctx, LocalTime startTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, startTime);
    if((startTime == null)){
        return;
    }
    }
    public void checkEndTime(UserContext _ctx, LocalTime endTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, endTime);
    if((endTime == null)){
        return;
    }
    }
    public void checkShiftDate(UserContext _ctx, LocalDate shiftDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, shiftDate);
    if((shiftDate == null)){
        return;
    }
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