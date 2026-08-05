package com.doublechaintech.enterpriselogisticsservice.servicecontract;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceContractChecker implements Checker<ServiceContract>{

    public String type(){
        return ServiceContract.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, ServiceContract serviceContract, ObjectLocation _parentLocation){
        if(needCheck(_ctx, serviceContract)){
            markAsChecked(_ctx, serviceContract);
            doCheck(_ctx, serviceContract, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, ServiceContract serviceContract, ObjectLocation _parentLocation){
      if((serviceContract == null)){
         return;
      }
      if(serviceContract.newItem()){
        if(serviceContract.getCreatedTime() == null){
           serviceContract.updateCreatedTime(java.time.LocalDateTime.now());
        }if(serviceContract.getUpdatedTime() == null){
           serviceContract.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(serviceContract.updateItem()){
        serviceContract.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkContractNumber(_ctx, serviceContract.getProperty(ServiceContract.CONTRACT_NUMBER_PROPERTY), newLocation(_parentLocation, ServiceContract.CONTRACT_NUMBER_PROPERTY));
      checkTitle(_ctx, serviceContract.getProperty(ServiceContract.TITLE_PROPERTY), newLocation(_parentLocation, ServiceContract.TITLE_PROPERTY));
      checkStartDate(_ctx, serviceContract.getProperty(ServiceContract.START_DATE_PROPERTY), newLocation(_parentLocation, ServiceContract.START_DATE_PROPERTY));
      checkEndDate(_ctx, serviceContract.getProperty(ServiceContract.END_DATE_PROPERTY), newLocation(_parentLocation, ServiceContract.END_DATE_PROPERTY));
      checkStatus(_ctx, serviceContract.getProperty(ServiceContract.STATUS_PROPERTY), newLocation(_parentLocation, ServiceContract.STATUS_PROPERTY));
      checkTotalValue(_ctx, serviceContract.getProperty(ServiceContract.TOTAL_VALUE_PROPERTY), newLocation(_parentLocation, ServiceContract.TOTAL_VALUE_PROPERTY));
      checkCreatedTime(_ctx, serviceContract.getProperty(ServiceContract.CREATED_TIME_PROPERTY), newLocation(_parentLocation, ServiceContract.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, serviceContract.getProperty(ServiceContract.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, ServiceContract.UPDATED_TIME_PROPERTY));
    }

    public void checkContractNumber(UserContext _ctx, String contractNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, contractNumber);
    if((contractNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, contractNumber);

    }
    public void checkTitle(UserContext _ctx, String title, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, title);
    if((title == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, title);

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
    public void checkTotalValue(UserContext _ctx, BigDecimal totalValue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalValue);
    if((totalValue == null)){
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