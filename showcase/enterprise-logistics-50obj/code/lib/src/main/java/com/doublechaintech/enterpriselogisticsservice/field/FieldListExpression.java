package com.doublechaintech.enterpriselogisticsservice.field;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class FieldListExpression<T, E, U extends Field> extends SmartListExpression<T, E, U> {
    public FieldListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public FieldListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public FieldExpression<T, U, U> first() {
       return new FieldExpression(super.first());
    }

    public FieldExpression<T, U, U> get(int index) {
      return new FieldExpression(super.get(index));
    }
}