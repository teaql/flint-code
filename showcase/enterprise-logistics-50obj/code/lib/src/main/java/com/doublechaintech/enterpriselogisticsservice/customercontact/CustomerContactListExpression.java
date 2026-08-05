package com.doublechaintech.enterpriselogisticsservice.customercontact;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class CustomerContactListExpression<T, E, U extends CustomerContact> extends SmartListExpression<T, E, U> {
    public CustomerContactListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public CustomerContactListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public CustomerContactExpression<T, U, U> first() {
       return new CustomerContactExpression(super.first());
    }

    public CustomerContactExpression<T, U, U> get(int index) {
      return new CustomerContactExpression(super.get(index));
    }
}