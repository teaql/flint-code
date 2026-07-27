package com.doublechaintech.enterpriselogisticsservice.userrole;

import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission;
import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

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
      }else if(userRole.updateItem()){
      }
      checkName(_ctx, userRole.getProperty(UserRole.NAME_PROPERTY), newLocation(_parentLocation, UserRole.NAME_PROPERTY));
      checkCode(_ctx, userRole.getProperty(UserRole.CODE_PROPERTY), newLocation(_parentLocation, UserRole.CODE_PROPERTY));
      checkDescription(_ctx, userRole.getProperty(UserRole.DESCRIPTION_PROPERTY), newLocation(_parentLocation, UserRole.DESCRIPTION_PROPERTY));
      for(int i = 0; userRole.getAccessPermissionList() != null && i < userRole.getAccessPermissionList().size(); i++){
         AccessPermission accessPermission = userRole.getAccessPermissionList().get(i);
         new AccessPermissionChecker().checkAndFix(_ctx, accessPermission, newLocation(_parentLocation, UserRole.ACCESS_PERMISSION_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkCode(UserContext _ctx, String code, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, code);
    if((code == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, code);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
}