package com.doublechaintech.enterpriselogisticsservice.privatecustomer;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class PrivateCustomerListExpression<T, E, U extends PrivateCustomer> extends SmartListExpression<T, E, U> {
    public PrivateCustomerListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public PrivateCustomerListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public PrivateCustomerExpression<T, U, U> first() {
       return new PrivateCustomerExpression(super.first());
    }

    public PrivateCustomerExpression<T, U, U> get(int index) {
      return new PrivateCustomerExpression(super.get(index));
    }
}