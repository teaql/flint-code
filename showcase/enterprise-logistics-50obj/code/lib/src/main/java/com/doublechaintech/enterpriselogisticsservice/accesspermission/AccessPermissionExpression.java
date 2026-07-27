package com.doublechaintech.enterpriselogisticsservice.accesspermission;

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


    public Expression<T, String> getPermissionCode(){
       return apply(AccessPermission::getPermissionCode);
    }
    public AccessPermissionExpression<T, U, U> updatePermissionCode(String permissionCode){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updatePermissionCode(permissionCode));
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

    public Expression<T, String> getDescription(){
       return apply(AccessPermission::getDescription);
    }
    public AccessPermissionExpression<T, U, U> updateDescription(String description){
       return new AccessPermissionExpression(this, $it ->  ((AccessPermission)$it).updateDescription(description));
    }

}