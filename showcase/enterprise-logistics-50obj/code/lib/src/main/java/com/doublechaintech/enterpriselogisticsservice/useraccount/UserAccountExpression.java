package com.doublechaintech.enterpriselogisticsservice.useraccount;

import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog;
import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLogListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class UserAccountExpression<T, E, U extends UserAccount> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public UserAccountExpression(Expression<T, U> expression){
        super(expression);
    }

    public UserAccountExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public UserAccountExpression<T, U, U> updateId(Long id){
        return new UserAccountExpression(this, $it -> {((UserAccount)$it).__internalSet("id", id); return this;});
     }

     public UserAccountExpression<T, U, U> save(UserContext userContext){
        return new UserAccountExpression(this, $it -> ((UserAccount)$it).auditAs("Saved by Expression").save(userContext));
     }

     public UserAccountExpression<T, U, U> save(String intent, UserContext userContext){
        return new UserAccountExpression(this, $it -> ((UserAccount)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getUsername(){
       return apply(UserAccount::getUsername);
    }
    public UserAccountExpression<T, U, U> updateUsername(String username){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateUsername(username));
    }

    public Expression<T, String> getEmail(){
       return apply(UserAccount::getEmail);
    }
    public UserAccountExpression<T, U, U> updateEmail(String email){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateEmail(email));
    }

    public Expression<T, String> getPhone(){
       return apply(UserAccount::getPhone);
    }
    public UserAccountExpression<T, U, U> updatePhone(String phone){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updatePhone(phone));
    }

    public Expression<T, String> getStatus(){
       return apply(UserAccount::getStatus);
    }
    public UserAccountExpression<T, U, U> updateStatus(String status){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateStatus(status));
    }

    public Expression<T, String> getPasswordHash(){
       return apply(UserAccount::getPasswordHash);
    }
    public UserAccountExpression<T, U, U> updatePasswordHash(String passwordHash){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updatePasswordHash(passwordHash));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(UserAccount::getCreatedAt);
    }
    public UserAccountExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(UserAccount::getUpdatedAt);
    }
    public UserAccountExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateUpdatedAt(updatedAt));
    }

    public AuditLogListExpression<T, U, AuditLog> getAuditLogList(){
        return new AuditLogListExpression(this, $it ->  ((UserAccount)$it).getAuditLogList());
    }
    public UserAccountExpression<T, U, U> addAuditLog(AuditLog auditLog){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).addAuditLog(auditLog));
    }
}