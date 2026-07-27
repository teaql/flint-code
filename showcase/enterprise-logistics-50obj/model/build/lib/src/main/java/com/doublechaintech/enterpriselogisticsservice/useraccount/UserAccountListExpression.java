package com.doublechaintech.enterpriselogisticsservice.useraccount;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class UserAccountListExpression<T, E, U extends UserAccount> extends SmartListExpression<T, E, U> {
    public UserAccountListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public UserAccountListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public UserAccountExpression<T, U, U> first() {
       return new UserAccountExpression(super.first());
    }

    public UserAccountExpression<T, U, U> get(int index) {
      return new UserAccountExpression(super.get(index));
    }
}