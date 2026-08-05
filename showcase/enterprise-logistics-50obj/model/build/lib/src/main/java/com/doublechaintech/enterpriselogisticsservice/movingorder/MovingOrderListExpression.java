package com.doublechaintech.enterpriselogisticsservice.movingorder;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class MovingOrderListExpression<T, E, U extends MovingOrder> extends SmartListExpression<T, E, U> {
    public MovingOrderListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public MovingOrderListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public MovingOrderExpression<T, U, U> first() {
       return new MovingOrderExpression(super.first());
    }

    public MovingOrderExpression<T, U, U> get(int index) {
      return new MovingOrderExpression(super.get(index));
    }
}