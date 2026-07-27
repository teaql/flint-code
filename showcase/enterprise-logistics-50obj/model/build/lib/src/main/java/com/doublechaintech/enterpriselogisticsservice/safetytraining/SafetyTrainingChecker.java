package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SafetyTrainingChecker implements Checker<SafetyTraining>{

    public String type(){
        return SafetyTraining.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, SafetyTraining safetyTraining, ObjectLocation _parentLocation){
        if(needCheck(_ctx, safetyTraining)){
            markAsChecked(_ctx, safetyTraining);
            doCheck(_ctx, safetyTraining, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, SafetyTraining safetyTraining, ObjectLocation _parentLocation){
      if((safetyTraining == null)){
         return;
      }
      if(safetyTraining.newItem()){
        if(safetyTraining.getCreatedAt() == null){
           safetyTraining.updateCreatedAt(java.time.LocalDateTime.now());
        }if(safetyTraining.getUpdatedAt() == null){
           safetyTraining.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(safetyTraining.updateItem()){
        safetyTraining.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkStaff(_ctx, safetyTraining.getProperty(SafetyTraining.STAFF_PROPERTY), newLocation(_parentLocation, SafetyTraining.STAFF_PROPERTY));
      checkCourseName(_ctx, safetyTraining.getProperty(SafetyTraining.COURSE_NAME_PROPERTY), newLocation(_parentLocation, SafetyTraining.COURSE_NAME_PROPERTY));
      checkCompletionDate(_ctx, safetyTraining.getProperty(SafetyTraining.COMPLETION_DATE_PROPERTY), newLocation(_parentLocation, SafetyTraining.COMPLETION_DATE_PROPERTY));
      checkCertificateNumber(_ctx, safetyTraining.getProperty(SafetyTraining.CERTIFICATE_NUMBER_PROPERTY), newLocation(_parentLocation, SafetyTraining.CERTIFICATE_NUMBER_PROPERTY));
      checkStatus(_ctx, safetyTraining.getProperty(SafetyTraining.STATUS_PROPERTY), newLocation(_parentLocation, SafetyTraining.STATUS_PROPERTY));
      checkCreatedAt(_ctx, safetyTraining.getProperty(SafetyTraining.CREATED_AT_PROPERTY), newLocation(_parentLocation, SafetyTraining.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, safetyTraining.getProperty(SafetyTraining.UPDATED_AT_PROPERTY), newLocation(_parentLocation, SafetyTraining.UPDATED_AT_PROPERTY));
    }

    public void checkStaff(UserContext _ctx, StaffMember staff, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, staff);
    if((staff == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, staff, _parentLocation);
    }
    public void checkCourseName(UserContext _ctx, String courseName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, courseName);
    if((courseName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, courseName);

    }
    public void checkCompletionDate(UserContext _ctx, LocalDate completionDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, completionDate);
    if((completionDate == null)){
        return;
    }
    }
    public void checkCertificateNumber(UserContext _ctx, String certificateNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, certificateNumber);
    if((certificateNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, certificateNumber);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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