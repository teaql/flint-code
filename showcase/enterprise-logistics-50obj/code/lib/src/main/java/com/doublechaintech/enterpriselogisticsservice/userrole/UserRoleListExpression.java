package com.doublechaintech.enterpriselogisticsservice.userrole;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class UserRoleListExpression<T, E, U extends UserRole> extends SmartListExpression<T, E, U> {
    public UserRoleListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public UserRoleListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public UserRoleExpression<T, U, U> first() {
       return new UserRoleExpression(super.first());
    }

    public UserRoleExpression<T, U, U> get(int index) {
      return new UserRoleExpression(super.get(index));
    }
}