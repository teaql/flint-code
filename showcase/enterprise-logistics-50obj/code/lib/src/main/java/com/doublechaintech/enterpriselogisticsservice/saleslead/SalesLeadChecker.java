package com.doublechaintech.enterpriselogisticsservice.saleslead;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SalesLeadChecker implements Checker<SalesLead>{

    public String type(){
        return SalesLead.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, SalesLead salesLead, ObjectLocation _parentLocation){
        if(needCheck(_ctx, salesLead)){
            markAsChecked(_ctx, salesLead);
            doCheck(_ctx, salesLead, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, SalesLead salesLead, ObjectLocation _parentLocation){
      if((salesLead == null)){
         return;
      }
      if(salesLead.newItem()){
        if(salesLead.getCreatedTime() == null){
           salesLead.updateCreatedTime(java.time.LocalDateTime.now());
        }if(salesLead.getUpdateTime() == null){
           salesLead.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(salesLead.updateItem()){
        salesLead.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, salesLead.getProperty(SalesLead.NAME_PROPERTY), newLocation(_parentLocation, SalesLead.NAME_PROPERTY));
      checkEmail(_ctx, salesLead.getProperty(SalesLead.EMAIL_PROPERTY), newLocation(_parentLocation, SalesLead.EMAIL_PROPERTY));
      checkPhone(_ctx, salesLead.getProperty(SalesLead.PHONE_PROPERTY), newLocation(_parentLocation, SalesLead.PHONE_PROPERTY));
      checkSource(_ctx, salesLead.getProperty(SalesLead.SOURCE_PROPERTY), newLocation(_parentLocation, SalesLead.SOURCE_PROPERTY));
      checkStatus(_ctx, salesLead.getProperty(SalesLead.STATUS_PROPERTY), newLocation(_parentLocation, SalesLead.STATUS_PROPERTY));
      checkEstimatedValue(_ctx, salesLead.getProperty(SalesLead.ESTIMATED_VALUE_PROPERTY), newLocation(_parentLocation, SalesLead.ESTIMATED_VALUE_PROPERTY));
      checkCreatedTime(_ctx, salesLead.getProperty(SalesLead.CREATED_TIME_PROPERTY), newLocation(_parentLocation, SalesLead.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, salesLead.getProperty(SalesLead.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, SalesLead.UPDATE_TIME_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkEmail(UserContext _ctx, String email, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, email);
    if((email == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, email);

    }
    public void checkPhone(UserContext _ctx, Integer phone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, phone);
    if((phone == null)){
        return;
    }
    }
    public void checkSource(UserContext _ctx, String source, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, source);
    if((source == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, source);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkEstimatedValue(UserContext _ctx, BigDecimal estimatedValue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, estimatedValue);
    if((estimatedValue == null)){
        return;
    }
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