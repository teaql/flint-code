package com.doublechaintech.enterpriselogisticsservice.userrole;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class UserRoleExpression<T, E, U extends UserRole> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public UserRoleExpression(Expression<T, U> expression){
        super(expression);
    }

    public UserRoleExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public UserRoleExpression<T, U, U> updateId(Long id){
        return new UserRoleExpression(this, $it -> {((UserRole)$it).__internalSet("id", id); return this;});
     }

     public UserRoleExpression<T, U, U> save(UserContext userContext){
        return new UserRoleExpression(this, $it -> ((UserRole)$it).auditAs("Saved by Expression").save(userContext));
     }

     public UserRoleExpression<T, U, U> save(String intent, UserContext userContext){
        return new UserRoleExpression(this, $it -> ((UserRole)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getRoleName(){
       return apply(UserRole::getRoleName);
    }
    public UserRoleExpression<T, U, U> updateRoleName(String roleName){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateRoleName(roleName));
    }

    public Expression<T, String> getDescription(){
       return apply(UserRole::getDescription);
    }
    public UserRoleExpression<T, U, U> updateDescription(String description){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateDescription(description));
    }

    public Expression<T, String> getIsSystem(){
       return apply(UserRole::getIsSystem);
    }
    public UserRoleExpression<T, U, U> updateIsSystem(String isSystem){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateIsSystem(isSystem));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(UserRole::getCreatedAt);
    }
    public UserRoleExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateCreatedAt(createdAt));
    }

}