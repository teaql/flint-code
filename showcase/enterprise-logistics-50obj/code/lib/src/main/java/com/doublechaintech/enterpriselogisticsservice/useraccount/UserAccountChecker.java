package com.doublechaintech.enterpriselogisticsservice.useraccount;

import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog;
import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogChecker;
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
        if(userAccount.getCreatedAt() == null){
           userAccount.updateCreatedAt(java.time.LocalDateTime.now());
        }if(userAccount.getUpdatedAt() == null){
           userAccount.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(userAccount.updateItem()){
        userAccount.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkUsername(_ctx, userAccount.getProperty(UserAccount.USERNAME_PROPERTY), newLocation(_parentLocation, UserAccount.USERNAME_PROPERTY));
      checkEmail(_ctx, userAccount.getProperty(UserAccount.EMAIL_PROPERTY), newLocation(_parentLocation, UserAccount.EMAIL_PROPERTY));
      checkPhone(_ctx, userAccount.getProperty(UserAccount.PHONE_PROPERTY), newLocation(_parentLocation, UserAccount.PHONE_PROPERTY));
      checkStatus(_ctx, userAccount.getProperty(UserAccount.STATUS_PROPERTY), newLocation(_parentLocation, UserAccount.STATUS_PROPERTY));
      checkPasswordHash(_ctx, userAccount.getProperty(UserAccount.PASSWORD_HASH_PROPERTY), newLocation(_parentLocation, UserAccount.PASSWORD_HASH_PROPERTY));
      checkCreatedAt(_ctx, userAccount.getProperty(UserAccount.CREATED_AT_PROPERTY), newLocation(_parentLocation, UserAccount.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, userAccount.getProperty(UserAccount.UPDATED_AT_PROPERTY), newLocation(_parentLocation, UserAccount.UPDATED_AT_PROPERTY));
      for(int i = 0; userAccount.getAuditLogList() != null && i < userAccount.getAuditLogList().size(); i++){
         AuditLog auditLog = userAccount.getAuditLogList().get(i);
         new AuditLogChecker().checkAndFix(_ctx, auditLog, newLocation(_parentLocation, UserAccount.AUDIT_LOG_LIST_PROPERTY, i));
      }
    }

    public void checkUsername(UserContext _ctx, String username, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, username);
    if((username == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, username);

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
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkPasswordHash(UserContext _ctx, String passwordHash, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, passwordHash);
    if((passwordHash == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, passwordHash);

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