package com.doublechaintech.enterpriselogisticsservice.fuellog;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class FuelLogListExpression<T, E, U extends FuelLog> extends SmartListExpression<T, E, U> {
    public FuelLogListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public FuelLogListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public FuelLogExpression<T, U, U> first() {
       return new FuelLogExpression(super.first());
    }

    public FuelLogExpression<T, U, U> get(int index) {
      return new FuelLogExpression(super.get(index));
    }
}