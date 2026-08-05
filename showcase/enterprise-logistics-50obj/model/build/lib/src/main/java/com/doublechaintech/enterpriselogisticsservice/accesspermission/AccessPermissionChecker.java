package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import com.doublechaintech.enterpriselogisticsservice.userrole.UserRole;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

public class AccessPermissionChecker implements Checker<AccessPermission>{

    public String type(){
        return AccessPermission.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, AccessPermission accessPermission, ObjectLocation _parentLocation){
        if(needCheck(_ctx, accessPermission)){
            markAsChecked(_ctx, accessPermission);
            doCheck(_ctx, accessPermission, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, AccessPermission accessPermission, ObjectLocation _parentLocation){
      if((accessPermission == null)){
         return;
      }
      if(accessPermission.newItem()){
      }else if(accessPermission.updateItem()){
      }
      checkName(_ctx, accessPermission.getProperty(AccessPermission.NAME_PROPERTY), newLocation(_parentLocation, AccessPermission.NAME_PROPERTY));
      checkResource(_ctx, accessPermission.getProperty(AccessPermission.RESOURCE_PROPERTY), newLocation(_parentLocation, AccessPermission.RESOURCE_PROPERTY));
      checkAction(_ctx, accessPermission.getProperty(AccessPermission.ACTION_PROPERTY), newLocation(_parentLocation, AccessPermission.ACTION_PROPERTY));
      checkRole(_ctx, accessPermission.getProperty(AccessPermission.ROLE_PROPERTY), newLocation(_parentLocation, AccessPermission.ROLE_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkResource(UserContext _ctx, String resource, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, resource);
    if((resource == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, resource);

    }
    public void checkAction(UserContext _ctx, String action, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, action);
    if((action == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, action);

    }
    public void checkRole(UserContext _ctx, UserRole role, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, role);
    if((role == null)){
        return;
    }
    new UserRoleChecker().checkAndFix(_ctx, role, _parentLocation);
    }
}