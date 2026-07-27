package com.doublechaintech.enterpriselogisticsservice.workedhours;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberChecker;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkedHoursChecker implements Checker<WorkedHours>{

    public String type(){
        return WorkedHours.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, WorkedHours workedHours, ObjectLocation _parentLocation){
        if(needCheck(_ctx, workedHours)){
            markAsChecked(_ctx, workedHours);
            doCheck(_ctx, workedHours, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, WorkedHours workedHours, ObjectLocation _parentLocation){
      if((workedHours == null)){
         return;
      }
      if(workedHours.newItem()){
        if(workedHours.getCreatedAt() == null){
           workedHours.updateCreatedAt(java.time.LocalDateTime.now());
        }if(workedHours.getUpdatedAt() == null){
           workedHours.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(workedHours.updateItem()){
        workedHours.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkStaff(_ctx, workedHours.getProperty(WorkedHours.STAFF_PROPERTY), newLocation(_parentLocation, WorkedHours.STAFF_PROPERTY));
      checkShift(_ctx, workedHours.getProperty(WorkedHours.SHIFT_PROPERTY), newLocation(_parentLocation, WorkedHours.SHIFT_PROPERTY));
      checkDate(_ctx, workedHours.getProperty(WorkedHours.DATE_PROPERTY), newLocation(_parentLocation, WorkedHours.DATE_PROPERTY));
      checkHoursWorked(_ctx, workedHours.getProperty(WorkedHours.HOURS_WORKED_PROPERTY), newLocation(_parentLocation, WorkedHours.HOURS_WORKED_PROPERTY));
      checkCreatedAt(_ctx, workedHours.getProperty(WorkedHours.CREATED_AT_PROPERTY), newLocation(_parentLocation, WorkedHours.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, workedHours.getProperty(WorkedHours.UPDATED_AT_PROPERTY), newLocation(_parentLocation, WorkedHours.UPDATED_AT_PROPERTY));
    }

    public void checkStaff(UserContext _ctx, StaffMember staff, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, staff);
    if((staff == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, staff, _parentLocation);
    }
    public void checkShift(UserContext _ctx, WorkShift shift, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, shift);
    if((shift == null)){
        return;
    }
    new WorkShiftChecker().checkAndFix(_ctx, shift, _parentLocation);
    }
    public void checkDate(UserContext _ctx, LocalDate date, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, date);
    if((date == null)){
        return;
    }
    }
    public void checkHoursWorked(UserContext _ctx, String hoursWorked, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, hoursWorked);
    if((hoursWorked == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, hoursWorked);

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