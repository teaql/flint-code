package com.doublechaintech.enterpriselogisticsservice.cargoitem;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class CargoItemListExpression<T, E, U extends CargoItem> extends SmartListExpression<T, E, U> {
    public CargoItemListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public CargoItemListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public CargoItemExpression<T, U, U> first() {
       return new CargoItemExpression(super.first());
    }

    public CargoItemExpression<T, U, U> get(int index) {
      return new CargoItemExpression(super.get(index));
    }
}