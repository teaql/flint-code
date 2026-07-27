package com.doublechaintech.movingcompanyservice.privatecustomer;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class PrivateCustomerChecker implements Checker<PrivateCustomer>{

    public String type(){
        return PrivateCustomer.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, PrivateCustomer privateCustomer, ObjectLocation _parentLocation){
        if(needCheck(_ctx, privateCustomer)){
            markAsChecked(_ctx, privateCustomer);
            doCheck(_ctx, privateCustomer, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, PrivateCustomer privateCustomer, ObjectLocation _parentLocation){
      if((privateCustomer == null)){
         return;
      }
      if(privateCustomer.newItem()){
        if(privateCustomer.getCreateTime() == null){
           privateCustomer.updateCreateTime(java.time.LocalDateTime.now());
        }if(privateCustomer.getUpdateTime() == null){
           privateCustomer.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(privateCustomer.updateItem()){
        privateCustomer.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, privateCustomer.getProperty(PrivateCustomer.NAME_PROPERTY), newLocation(_parentLocation, PrivateCustomer.NAME_PROPERTY));
      checkEmail(_ctx, privateCustomer.getProperty(PrivateCustomer.EMAIL_PROPERTY), newLocation(_parentLocation, PrivateCustomer.EMAIL_PROPERTY));
      checkPhone(_ctx, privateCustomer.getProperty(PrivateCustomer.PHONE_PROPERTY), newLocation(_parentLocation, PrivateCustomer.PHONE_PROPERTY));
      checkAddress(_ctx, privateCustomer.getProperty(PrivateCustomer.ADDRESS_PROPERTY), newLocation(_parentLocation, PrivateCustomer.ADDRESS_PROPERTY));
      checkIdNumber(_ctx, privateCustomer.getProperty(PrivateCustomer.ID_NUMBER_PROPERTY), newLocation(_parentLocation, PrivateCustomer.ID_NUMBER_PROPERTY));
      checkCreateTime(_ctx, privateCustomer.getProperty(PrivateCustomer.CREATE_TIME_PROPERTY), newLocation(_parentLocation, PrivateCustomer.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, privateCustomer.getProperty(PrivateCustomer.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, PrivateCustomer.UPDATE_TIME_PROPERTY));
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
    public void checkAddress(UserContext _ctx, String address, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, address);
    if((address == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, address);

    }
    public void checkIdNumber(UserContext _ctx, String idNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, idNumber);
    if((idNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, idNumber);

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