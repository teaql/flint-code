package com.doublechaintech.enterpriselogisticsservice.userrole;

import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission;
import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermissionListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
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


    public Expression<T, String> getName(){
       return apply(UserRole::getName);
    }
    public UserRoleExpression<T, U, U> updateName(String name){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateName(name));
    }

    public Expression<T, String> getCode(){
       return apply(UserRole::getCode);
    }
    public UserRoleExpression<T, U, U> updateCode(String code){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateCode(code));
    }

    public Expression<T, String> getDescription(){
       return apply(UserRole::getDescription);
    }
    public UserRoleExpression<T, U, U> updateDescription(String description){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).updateDescription(description));
    }

    public AccessPermissionListExpression<T, U, AccessPermission> getAccessPermissionList(){
        return new AccessPermissionListExpression(this, $it ->  ((UserRole)$it).getAccessPermissionList());
    }
    public UserRoleExpression<T, U, U> addAccessPermission(AccessPermission accessPermission){
       return new UserRoleExpression(this, $it ->  ((UserRole)$it).addAccessPermission(accessPermission));
    }
}