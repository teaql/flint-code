package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class AccessPermissionListExpression<T, E, U extends AccessPermission> extends SmartListExpression<T, E, U> {
    public AccessPermissionListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public AccessPermissionListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public AccessPermissionExpression<T, U, U> first() {
       return new AccessPermissionExpression(super.first());
    }

    public AccessPermissionExpression<T, U, U> get(int index) {
      return new AccessPermissionExpression(super.get(index));
    }
}