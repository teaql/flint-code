package com.doublechaintech.enterpriselogisticsservice.useraccount;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class UserAccountChecker implements Checker<UserAccount>{

    public String type(){
        return UserAccount.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, UserAccount userAccount, ObjectLocation _parentLocation){
        if(needCheck(_ctx, userAccount)){
            markAsChecked(_ctx, userAccount);
            doCheck(_ctx, userAccount, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, UserAccount userAccount, ObjectLocation _parentLocation){
      if((userAccount == null)){
         return;
      }
      if(userAccount.newItem()){
        if(userAccount.getCreateTime() == null){
           userAccount.updateCreateTime(java.time.LocalDateTime.now());
        }if(userAccount.getUpdateTime() == null){
           userAccount.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(userAccount.updateItem()){
        userAccount.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, userAccount.getProperty(UserAccount.NAME_PROPERTY), newLocation(_parentLocation, UserAccount.NAME_PROPERTY));
      checkEmail(_ctx, userAccount.getProperty(UserAccount.EMAIL_PROPERTY), newLocation(_parentLocation, UserAccount.EMAIL_PROPERTY));
      checkPhone(_ctx, userAccount.getProperty(UserAccount.PHONE_PROPERTY), newLocation(_parentLocation, UserAccount.PHONE_PROPERTY));
      checkPasswordHash(_ctx, userAccount.getProperty(UserAccount.PASSWORD_HASH_PROPERTY), newLocation(_parentLocation, UserAccount.PASSWORD_HASH_PROPERTY));
      checkStatus(_ctx, userAccount.getProperty(UserAccount.STATUS_PROPERTY), newLocation(_parentLocation, UserAccount.STATUS_PROPERTY));
      checkCreateTime(_ctx, userAccount.getProperty(UserAccount.CREATE_TIME_PROPERTY), newLocation(_parentLocation, UserAccount.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, userAccount.getProperty(UserAccount.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, UserAccount.UPDATE_TIME_PROPERTY));
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
    public void checkPhone(UserContext _ctx, String phone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, phone);
    if((phone == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, phone);

    }
    public void checkPasswordHash(UserContext _ctx, String passwordHash, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, passwordHash);
    if((passwordHash == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, passwordHash);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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