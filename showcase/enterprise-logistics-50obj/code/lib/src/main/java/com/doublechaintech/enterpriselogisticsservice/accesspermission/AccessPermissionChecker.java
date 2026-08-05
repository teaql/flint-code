package com.doublechaintech.enterpriselogisticsservice.accesspermission;

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
      checkPermissionCode(_ctx, accessPermission.getProperty(AccessPermission.PERMISSION_CODE_PROPERTY), newLocation(_parentLocation, AccessPermission.PERMISSION_CODE_PROPERTY));
      checkResource(_ctx, accessPermission.getProperty(AccessPermission.RESOURCE_PROPERTY), newLocation(_parentLocation, AccessPermission.RESOURCE_PROPERTY));
      checkAction(_ctx, accessPermission.getProperty(AccessPermission.ACTION_PROPERTY), newLocation(_parentLocation, AccessPermission.ACTION_PROPERTY));
      checkDescription(_ctx, accessPermission.getProperty(AccessPermission.DESCRIPTION_PROPERTY), newLocation(_parentLocation, AccessPermission.DESCRIPTION_PROPERTY));
    }

    public void checkPermissionCode(UserContext _ctx, String permissionCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, permissionCode);
    if((permissionCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, permissionCode);

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
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
}