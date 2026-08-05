package com.doublechaintech.enterpriselogisticsservice.auditlog;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class AuditLogExpression<T, E, U extends AuditLog> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public AuditLogExpression(Expression<T, U> expression){
        super(expression);
    }

    public AuditLogExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public AuditLogExpression<T, U, U> updateId(Long id){
        return new AuditLogExpression(this, $it -> {((AuditLog)$it).__internalSet("id", id); return this;});
     }

     public AuditLogExpression<T, U, U> save(UserContext userContext){
        return new AuditLogExpression(this, $it -> ((AuditLog)$it).auditAs("Saved by Expression").save(userContext));
     }

     public AuditLogExpression<T, U, U> save(String intent, UserContext userContext){
        return new AuditLogExpression(this, $it -> ((AuditLog)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getAction(){
       return apply(AuditLog::getAction);
    }
    public AuditLogExpression<T, U, U> updateAction(String action){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateAction(action));
    }

    public Expression<T, String> getEntityType(){
       return apply(AuditLog::getEntityType);
    }
    public AuditLogExpression<T, U, U> updateEntityType(String entityType){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateEntityType(entityType));
    }

    public Expression<T, String> getEntityId(){
       return apply(AuditLog::getEntityId);
    }
    public AuditLogExpression<T, U, U> updateEntityId(String entityId){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateEntityId(entityId));
    }

    public Expression<T, String> getUserId(){
       return apply(AuditLog::getUserId);
    }
    public AuditLogExpression<T, U, U> updateUserId(String userId){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateUserId(userId));
    }

    public Expression<T, String> getIpAddress(){
       return apply(AuditLog::getIpAddress);
    }
    public AuditLogExpression<T, U, U> updateIpAddress(String ipAddress){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateIpAddress(ipAddress));
    }

    public Expression<T, String> getDetails(){
       return apply(AuditLog::getDetails);
    }
    public AuditLogExpression<T, U, U> updateDetails(String details){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateDetails(details));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(AuditLog::getCreatedTime);
    }
    public AuditLogExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new AuditLogExpression(this, $it ->  ((AuditLog)$it).updateCreatedTime(createdTime));
    }

}