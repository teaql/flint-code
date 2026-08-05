package com.doublechaintech.enterpriselogisticsservice.containerunit;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class ContainerUnitListExpression<T, E, U extends ContainerUnit> extends SmartListExpression<T, E, U> {
    public ContainerUnitListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public ContainerUnitListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public ContainerUnitExpression<T, U, U> first() {
       return new ContainerUnitExpression(super.first());
    }

    public ContainerUnitExpression<T, U, U> get(int index) {
      return new ContainerUnitExpression(super.get(index));
    }
}