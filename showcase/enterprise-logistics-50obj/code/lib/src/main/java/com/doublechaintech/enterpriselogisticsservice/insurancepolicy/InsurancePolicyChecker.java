package com.doublechaintech.enterpriselogisticsservice.insurancepolicy;

import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class InsurancePolicyChecker implements Checker<InsurancePolicy>{

    public String type(){
        return InsurancePolicy.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, InsurancePolicy insurancePolicy, ObjectLocation _parentLocation){
        if(needCheck(_ctx, insurancePolicy)){
            markAsChecked(_ctx, insurancePolicy);
            doCheck(_ctx, insurancePolicy, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, InsurancePolicy insurancePolicy, ObjectLocation _parentLocation){
      if((insurancePolicy == null)){
         return;
      }
      if(insurancePolicy.newItem()){
        if(insurancePolicy.getCreatedTime() == null){
           insurancePolicy.updateCreatedTime(java.time.LocalDateTime.now());
        }if(insurancePolicy.getUpdateTime() == null){
           insurancePolicy.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(insurancePolicy.updateItem()){
        insurancePolicy.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkPolicyNumber(_ctx, insurancePolicy.getProperty(InsurancePolicy.POLICY_NUMBER_PROPERTY), newLocation(_parentLocation, InsurancePolicy.POLICY_NUMBER_PROPERTY));
      checkProvider(_ctx, insurancePolicy.getProperty(InsurancePolicy.PROVIDER_PROPERTY), newLocation(_parentLocation, InsurancePolicy.PROVIDER_PROPERTY));
      checkCoverageAmount(_ctx, insurancePolicy.getProperty(InsurancePolicy.COVERAGE_AMOUNT_PROPERTY), newLocation(_parentLocation, InsurancePolicy.COVERAGE_AMOUNT_PROPERTY));
      checkPremium(_ctx, insurancePolicy.getProperty(InsurancePolicy.PREMIUM_PROPERTY), newLocation(_parentLocation, InsurancePolicy.PREMIUM_PROPERTY));
      checkStartDate(_ctx, insurancePolicy.getProperty(InsurancePolicy.START_DATE_PROPERTY), newLocation(_parentLocation, InsurancePolicy.START_DATE_PROPERTY));
      checkEndDate(_ctx, insurancePolicy.getProperty(InsurancePolicy.END_DATE_PROPERTY), newLocation(_parentLocation, InsurancePolicy.END_DATE_PROPERTY));
      checkStatus(_ctx, insurancePolicy.getProperty(InsurancePolicy.STATUS_PROPERTY), newLocation(_parentLocation, InsurancePolicy.STATUS_PROPERTY));
      checkCreatedTime(_ctx, insurancePolicy.getProperty(InsurancePolicy.CREATED_TIME_PROPERTY), newLocation(_parentLocation, InsurancePolicy.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, insurancePolicy.getProperty(InsurancePolicy.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, InsurancePolicy.UPDATE_TIME_PROPERTY));
      for(int i = 0; insurancePolicy.getClaimsRecordList() != null && i < insurancePolicy.getClaimsRecordList().size(); i++){
         ClaimsRecord claimsRecord = insurancePolicy.getClaimsRecordList().get(i);
         new ClaimsRecordChecker().checkAndFix(_ctx, claimsRecord, newLocation(_parentLocation, InsurancePolicy.CLAIMS_RECORD_LIST_PROPERTY, i));
      }
    }

    public void checkPolicyNumber(UserContext _ctx, String policyNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, policyNumber);
    if((policyNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, policyNumber);

    }
    public void checkProvider(UserContext _ctx, String provider, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, provider);
    if((provider == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, provider);

    }
    public void checkCoverageAmount(UserContext _ctx, BigDecimal coverageAmount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, coverageAmount);
    if((coverageAmount == null)){
        return;
    }
    }
    public void checkPremium(UserContext _ctx, BigDecimal premium, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, premium);
    if((premium == null)){
        return;
    }
    }
    public void checkStartDate(UserContext _ctx, LocalDate startDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, startDate);
    if((startDate == null)){
        return;
    }
    }
    public void checkEndDate(UserContext _ctx, LocalDate endDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, endDate);
    if((endDate == null)){
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