package com.doublechaintech.enterpriselogisticsservice.customerloyalty;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class CustomerLoyaltyListExpression<T, E, U extends CustomerLoyalty> extends SmartListExpression<T, E, U> {
    public CustomerLoyaltyListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public CustomerLoyaltyListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public CustomerLoyaltyExpression<T, U, U> first() {
       return new CustomerLoyaltyExpression(super.first());
    }

    public CustomerLoyaltyExpression<T, U, U> get(int index) {
      return new CustomerLoyaltyExpression(super.get(index));
    }
}