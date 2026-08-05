package com.doublechaintech.enterpriselogisticsservice.transitroute;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class TransitRouteListExpression<T, E, U extends TransitRoute> extends SmartListExpression<T, E, U> {
    public TransitRouteListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public TransitRouteListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public TransitRouteExpression<T, U, U> first() {
       return new TransitRouteExpression(super.first());
    }

    public TransitRouteExpression<T, U, U> get(int index) {
      return new TransitRouteExpression(super.get(index));
    }
}