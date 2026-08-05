package com.doublechaintech.enterpriselogisticsservice.claimsrecord;

import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy;
import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyChecker;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClaimsRecordChecker implements Checker<ClaimsRecord>{

    public String type(){
        return ClaimsRecord.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, ClaimsRecord claimsRecord, ObjectLocation _parentLocation){
        if(needCheck(_ctx, claimsRecord)){
            markAsChecked(_ctx, claimsRecord);
            doCheck(_ctx, claimsRecord, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, ClaimsRecord claimsRecord, ObjectLocation _parentLocation){
      if((claimsRecord == null)){
         return;
      }
      if(claimsRecord.newItem()){
        if(claimsRecord.getCreatedTime() == null){
           claimsRecord.updateCreatedTime(java.time.LocalDateTime.now());
        }if(claimsRecord.getUpdateTime() == null){
           claimsRecord.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(claimsRecord.updateItem()){
        claimsRecord.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkClaimNumber(_ctx, claimsRecord.getProperty(ClaimsRecord.CLAIM_NUMBER_PROPERTY), newLocation(_parentLocation, ClaimsRecord.CLAIM_NUMBER_PROPERTY));
      checkDescription(_ctx, claimsRecord.getProperty(ClaimsRecord.DESCRIPTION_PROPERTY), newLocation(_parentLocation, ClaimsRecord.DESCRIPTION_PROPERTY));
      checkClaimAmount(_ctx, claimsRecord.getProperty(ClaimsRecord.CLAIM_AMOUNT_PROPERTY), newLocation(_parentLocation, ClaimsRecord.CLAIM_AMOUNT_PROPERTY));
      checkStatus(_ctx, claimsRecord.getProperty(ClaimsRecord.STATUS_PROPERTY), newLocation(_parentLocation, ClaimsRecord.STATUS_PROPERTY));
      checkResolutionDate(_ctx, claimsRecord.getProperty(ClaimsRecord.RESOLUTION_DATE_PROPERTY), newLocation(_parentLocation, ClaimsRecord.RESOLUTION_DATE_PROPERTY));
      checkMovingOrder(_ctx, claimsRecord.getProperty(ClaimsRecord.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, ClaimsRecord.MOVING_ORDER_PROPERTY));
      checkInsurancePolicy(_ctx, claimsRecord.getProperty(ClaimsRecord.INSURANCE_POLICY_PROPERTY), newLocation(_parentLocation, ClaimsRecord.INSURANCE_POLICY_PROPERTY));
      checkCreatedTime(_ctx, claimsRecord.getProperty(ClaimsRecord.CREATED_TIME_PROPERTY), newLocation(_parentLocation, ClaimsRecord.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, claimsRecord.getProperty(ClaimsRecord.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, ClaimsRecord.UPDATE_TIME_PROPERTY));
    }

    public void checkClaimNumber(UserContext _ctx, String claimNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, claimNumber);
    if((claimNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, claimNumber);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkClaimAmount(UserContext _ctx, BigDecimal claimAmount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, claimAmount);
    if((claimAmount == null)){
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
    public void checkResolutionDate(UserContext _ctx, LocalDate resolutionDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, resolutionDate);
    if((resolutionDate == null)){
        return;
    }
    }
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
    }
    public void checkInsurancePolicy(UserContext _ctx, InsurancePolicy insurancePolicy, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, insurancePolicy);
    if((insurancePolicy == null)){
        return;
    }
    new InsurancePolicyChecker().checkAndFix(_ctx, insurancePolicy, _parentLocation);
    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
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