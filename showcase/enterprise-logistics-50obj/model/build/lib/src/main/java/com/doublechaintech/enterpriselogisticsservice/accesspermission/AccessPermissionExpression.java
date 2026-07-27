package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import com.doublechaintech.enterpriselogisticsservice.userrole.UserRole;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRoleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.util.function.Function;

public class AccessPermissionExpression<T, E, U extends AccessPermission> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public AccessPermissionExpression(Expression<T, U> expression){
        super(expression);
    }

    public AccessPermissionExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public AccessPermissionExpression<T, U, U> updateId(Long id){
        return new AccessPermissionExpression(this, $it -> {((AccessPermission)$it).__internalSet("id", id); return this;});
     }

     public AccessPermissionExpression<T, U, U> save(UserContext userContext){
        return new AccessPermissionExpression(this, $it -> ((AccessPermission)$it).auditAs("Saved by Expression").save(userContext));
     }

     public AccessPermissionExpression<T, U, U> save(String intent, UserContext userContext){
        return new AccessPermissionExpression(this, $it -> ((AccessPermission)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(AccessPermission::getName);
    }
    public AccessPermissionExpression<T, U, U> updateName(String name){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateName(name));
    }

    public Expression<T, String> getResource(){
       return apply(AccessPermission::getResource);
    }
    public AccessPermissionExpression<T, U, U> updateResource(String resource){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateResource(resource));
    }

    public Expression<T, String> getAction(){
       return apply(AccessPermission::getAction);
    }
    public AccessPermissionExpression<T, U, U> updateAction(String action){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateAction(action));
    }

    public UserRoleExpression<T, U, UserRole> getRole(){
       return new UserRoleExpression(this, $it ->  ((AccessPermission)$it).getRole());
    }

    public AccessPermissionExpression<T, U, U> updateRoleToAdmin(){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateRoleToAdmin());
    }
    public AccessPermissionExpression<T, U, U> updateRoleToDispatcher(){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateRoleToDispatcher());
    }
    public AccessPermissionExpression<T, U, U> updateRoleToDriver(){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateRoleToDriver());
    }
    public AccessPermissionExpression<T, U, U> updateRoleToCs(){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateRoleToCs());
    }

}