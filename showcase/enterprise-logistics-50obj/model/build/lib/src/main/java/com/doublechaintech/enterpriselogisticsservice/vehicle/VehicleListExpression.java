package com.doublechaintech.enterpriselogisticsservice.vehicle;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class VehicleListExpression<T, E, U extends Vehicle> extends SmartListExpression<T, E, U> {
    public VehicleListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public VehicleListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public VehicleExpression<T, U, U> first() {
       return new VehicleExpression(super.first());
    }

    public VehicleExpression<T, U, U> get(int index) {
      return new VehicleExpression(super.get(index));
    }
}