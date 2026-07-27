package com.doublechaintech.enterpriselogisticsservice.auditlog;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class AuditLogChecker implements Checker<AuditLog>{

    public String type(){
        return AuditLog.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, AuditLog auditLog, ObjectLocation _parentLocation){
        if(needCheck(_ctx, auditLog)){
            markAsChecked(_ctx, auditLog);
            doCheck(_ctx, auditLog, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, AuditLog auditLog, ObjectLocation _parentLocation){
      if((auditLog == null)){
         return;
      }
      if(auditLog.newItem()){
        if(auditLog.getCreatedTime() == null){
           auditLog.updateCreatedTime(java.time.LocalDateTime.now());
        }
      }else if(auditLog.updateItem()){
      }
      checkAction(_ctx, auditLog.getProperty(AuditLog.ACTION_PROPERTY), newLocation(_parentLocation, AuditLog.ACTION_PROPERTY));
      checkEntityType(_ctx, auditLog.getProperty(AuditLog.ENTITY_TYPE_PROPERTY), newLocation(_parentLocation, AuditLog.ENTITY_TYPE_PROPERTY));
      checkEntityId(_ctx, auditLog.getProperty(AuditLog.ENTITY_ID_PROPERTY), newLocation(_parentLocation, AuditLog.ENTITY_ID_PROPERTY));
      checkUserId(_ctx, auditLog.getProperty(AuditLog.USER_ID_PROPERTY), newLocation(_parentLocation, AuditLog.USER_ID_PROPERTY));
      checkIpAddress(_ctx, auditLog.getProperty(AuditLog.IP_ADDRESS_PROPERTY), newLocation(_parentLocation, AuditLog.IP_ADDRESS_PROPERTY));
      checkDetails(_ctx, auditLog.getProperty(AuditLog.DETAILS_PROPERTY), newLocation(_parentLocation, AuditLog.DETAILS_PROPERTY));
      checkCreatedTime(_ctx, auditLog.getProperty(AuditLog.CREATED_TIME_PROPERTY), newLocation(_parentLocation, AuditLog.CREATED_TIME_PROPERTY));
    }

    public void checkAction(UserContext _ctx, String action, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, action);
    if((action == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, action);

    }
    public void checkEntityType(UserContext _ctx, String entityType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, entityType);
    if((entityType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, entityType);

    }
    public void checkEntityId(UserContext _ctx, String entityId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, entityId);
    if((entityId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, entityId);

    }
    public void checkUserId(UserContext _ctx, String userId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, userId);
    if((userId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, userId);

    }
    public void checkIpAddress(UserContext _ctx, String ipAddress, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, ipAddress);
    if((ipAddress == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, ipAddress);

    }
    public void checkDetails(UserContext _ctx, String details, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, details);
    if((details == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, details);

    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
}