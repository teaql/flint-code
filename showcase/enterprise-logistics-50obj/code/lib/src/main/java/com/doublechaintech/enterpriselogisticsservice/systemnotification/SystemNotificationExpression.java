package com.doublechaintech.enterpriselogisticsservice.systemnotification;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SystemNotificationExpression<T, E, U extends SystemNotification> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SystemNotificationExpression(Expression<T, U> expression){
        super(expression);
    }

    public SystemNotificationExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SystemNotificationExpression<T, U, U> updateId(Long id){
        return new SystemNotificationExpression(this, $it -> {((SystemNotification)$it).__internalSet("id", id); return this;});
     }

     public SystemNotificationExpression<T, U, U> save(UserContext userContext){
        return new SystemNotificationExpression(this, $it -> ((SystemNotification)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SystemNotificationExpression<T, U, U> save(String intent, UserContext userContext){
        return new SystemNotificationExpression(this, $it -> ((SystemNotification)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getNotificationType(){
       return apply(SystemNotification::getNotificationType);
    }
    public SystemNotificationExpression<T, U, U> updateNotificationType(String notificationType){
       return new SystemNotificationExpression(this, $it ->  ((SystemNotification)$it).updateNotificationType(notificationType));
    }

    public Expression<T, String> getTitle(){
       return apply(SystemNotification::getTitle);
    }
    public SystemNotificationExpression<T, U, U> updateTitle(String title){
       return new SystemNotificationExpression(this, $it ->  ((SystemNotification)$it).updateTitle(title));
    }

    public Expression<T, String> getContent(){
       return apply(SystemNotification::getContent);
    }
    public SystemNotificationExpression<T, U, U> updateContent(String content){
       return new SystemNotificationExpression(this, $it ->  ((SystemNotification)$it).updateContent(content));
    }

    public Expression<T, String> getIsRead(){
       return apply(SystemNotification::getIsRead);
    }
    public SystemNotificationExpression<T, U, U> updateIsRead(String isRead){
       return new SystemNotificationExpression(this, $it ->  ((SystemNotification)$it).updateIsRead(isRead));
    }

    public Expression<T, String> getRecipientId(){
       return apply(SystemNotification::getRecipientId);
    }
    public SystemNotificationExpression<T, U, U> updateRecipientId(String recipientId){
       return new SystemNotificationExpression(this, $it ->  ((SystemNotification)$it).updateRecipientId(recipientId));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(SystemNotification::getCreatedAt);
    }
    public SystemNotificationExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new SystemNotificationExpression(this, $it ->  ((SystemNotification)$it).updateCreatedAt(createdAt));
    }

}