package com.doublechaintech.enterpriselogisticsservice.userrole;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class UserRoleChecker implements Checker<UserRole>{

    public String type(){
        return UserRole.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, UserRole userRole, ObjectLocation _parentLocation){
        if(needCheck(_ctx, userRole)){
            markAsChecked(_ctx, userRole);
            doCheck(_ctx, userRole, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, UserRole userRole, ObjectLocation _parentLocation){
      if((userRole == null)){
         return;
      }
      if(userRole.newItem()){
        if(userRole.getCreatedAt() == null){
           userRole.updateCreatedAt(java.time.LocalDateTime.now());
        }
      }else if(userRole.updateItem()){
      }
      checkRoleName(_ctx, userRole.getProperty(UserRole.ROLE_NAME_PROPERTY), newLocation(_parentLocation, UserRole.ROLE_NAME_PROPERTY));
      checkDescription(_ctx, userRole.getProperty(UserRole.DESCRIPTION_PROPERTY), newLocation(_parentLocation, UserRole.DESCRIPTION_PROPERTY));
      checkIsSystem(_ctx, userRole.getProperty(UserRole.IS_SYSTEM_PROPERTY), newLocation(_parentLocation, UserRole.IS_SYSTEM_PROPERTY));
      checkCreatedAt(_ctx, userRole.getProperty(UserRole.CREATED_AT_PROPERTY), newLocation(_parentLocation, UserRole.CREATED_AT_PROPERTY));
    }

    public void checkRoleName(UserContext _ctx, String roleName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, roleName);
    if((roleName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, roleName);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkIsSystem(UserContext _ctx, String isSystem, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, isSystem);
    if((isSystem == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, isSystem);

    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
}