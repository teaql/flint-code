package com.doublechaintech.enterpriselogisticsservice.safetytraining;

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
      checkTitle(_ctx, safetyTraining.getProperty(SafetyTraining.TITLE_PROPERTY), newLocation(_parentLocation, SafetyTraining.TITLE_PROPERTY));
      checkDescription(_ctx, safetyTraining.getProperty(SafetyTraining.DESCRIPTION_PROPERTY), newLocation(_parentLocation, SafetyTraining.DESCRIPTION_PROPERTY));
      checkDurationHours(_ctx, safetyTraining.getProperty(SafetyTraining.DURATION_HOURS_PROPERTY), newLocation(_parentLocation, SafetyTraining.DURATION_HOURS_PROPERTY));
      checkCompletionDate(_ctx, safetyTraining.getProperty(SafetyTraining.COMPLETION_DATE_PROPERTY), newLocation(_parentLocation, SafetyTraining.COMPLETION_DATE_PROPERTY));
      checkStatus(_ctx, safetyTraining.getProperty(SafetyTraining.STATUS_PROPERTY), newLocation(_parentLocation, SafetyTraining.STATUS_PROPERTY));
      checkCreatedAt(_ctx, safetyTraining.getProperty(SafetyTraining.CREATED_AT_PROPERTY), newLocation(_parentLocation, SafetyTraining.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, safetyTraining.getProperty(SafetyTraining.UPDATED_AT_PROPERTY), newLocation(_parentLocation, SafetyTraining.UPDATED_AT_PROPERTY));
    }

    public void checkTitle(UserContext _ctx, String title, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, title);
    if((title == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, title);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkDurationHours(UserContext _ctx, String durationHours, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, durationHours);
    if((durationHours == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, durationHours);

    }
    public void checkCompletionDate(UserContext _ctx, LocalDate completionDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, completionDate);
    if((completionDate == null)){
        return;
    }
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