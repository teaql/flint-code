package com.doublechaintech.movingcompanyservice.movingevent;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class MovingEventListExpression<T, E, U extends MovingEvent> extends SmartListExpression<T, E, U> {
    public MovingEventListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public MovingEventListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public MovingEventExpression<T, U, U> first() {
       return new MovingEventExpression(super.first());
    }

    public MovingEventExpression<T, U, U> get(int index) {
      return new MovingEventExpression(super.get(index));
    }
}