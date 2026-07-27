package com.doublechaintech.enterpriselogisticsservice.useraccount;

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


    public Expression<T, String> getName(){
       return apply(UserAccount::getName);
    }
    public UserAccountExpression<T, U, U> updateName(String name){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateName(name));
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

    public Expression<T, String> getPasswordHash(){
       return apply(UserAccount::getPasswordHash);
    }
    public UserAccountExpression<T, U, U> updatePasswordHash(String passwordHash){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updatePasswordHash(passwordHash));
    }

    public Expression<T, String> getStatus(){
       return apply(UserAccount::getStatus);
    }
    public UserAccountExpression<T, U, U> updateStatus(String status){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(UserAccount::getCreateTime);
    }
    public UserAccountExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(UserAccount::getUpdateTime);
    }
    public UserAccountExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new UserAccountExpression(this, $it ->  ((UserAccount)$it).updateUpdateTime(updateTime));
    }

}