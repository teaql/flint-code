package com.doublechaintech.enterpriselogisticsservice.systemnotification;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class SystemNotificationChecker implements Checker<SystemNotification>{

    public String type(){
        return SystemNotification.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, SystemNotification systemNotification, ObjectLocation _parentLocation){
        if(needCheck(_ctx, systemNotification)){
            markAsChecked(_ctx, systemNotification);
            doCheck(_ctx, systemNotification, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, SystemNotification systemNotification, ObjectLocation _parentLocation){
      if((systemNotification == null)){
         return;
      }
      if(systemNotification.newItem()){
        if(systemNotification.getCreatedAt() == null){
           systemNotification.updateCreatedAt(java.time.LocalDateTime.now());
        }
      }else if(systemNotification.updateItem()){
      }
      checkNotificationType(_ctx, systemNotification.getProperty(SystemNotification.NOTIFICATION_TYPE_PROPERTY), newLocation(_parentLocation, SystemNotification.NOTIFICATION_TYPE_PROPERTY));
      checkTitle(_ctx, systemNotification.getProperty(SystemNotification.TITLE_PROPERTY), newLocation(_parentLocation, SystemNotification.TITLE_PROPERTY));
      checkContent(_ctx, systemNotification.getProperty(SystemNotification.CONTENT_PROPERTY), newLocation(_parentLocation, SystemNotification.CONTENT_PROPERTY));
      checkIsRead(_ctx, systemNotification.getProperty(SystemNotification.IS_READ_PROPERTY), newLocation(_parentLocation, SystemNotification.IS_READ_PROPERTY));
      checkRecipientId(_ctx, systemNotification.getProperty(SystemNotification.RECIPIENT_ID_PROPERTY), newLocation(_parentLocation, SystemNotification.RECIPIENT_ID_PROPERTY));
      checkCreatedAt(_ctx, systemNotification.getProperty(SystemNotification.CREATED_AT_PROPERTY), newLocation(_parentLocation, SystemNotification.CREATED_AT_PROPERTY));
    }

    public void checkNotificationType(UserContext _ctx, String notificationType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, notificationType);
    if((notificationType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, notificationType);

    }
    public void checkTitle(UserContext _ctx, String title, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, title);
    if((title == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, title);

    }
    public void checkContent(UserContext _ctx, String content, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, content);
    if((content == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, content);

    }
    public void checkIsRead(UserContext _ctx, String isRead, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, isRead);
    if((isRead == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, isRead);

    }
    public void checkRecipientId(UserContext _ctx, String recipientId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, recipientId);
    if((recipientId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, recipientId);

    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
}