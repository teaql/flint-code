package com.doublechaintech.enterpriselogisticsservice.inventorycheck;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class InventoryCheckListExpression<T, E, U extends InventoryCheck> extends SmartListExpression<T, E, U> {
    public InventoryCheckListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public InventoryCheckListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public InventoryCheckExpression<T, U, U> first() {
       return new InventoryCheckExpression(super.first());
    }

    public InventoryCheckExpression<T, U, U> get(int index) {
      return new InventoryCheckExpression(super.get(index));
    }
}