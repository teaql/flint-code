package com.doublechaintech.enterpriselogisticsservice.gpslog;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class GpsLogListExpression<T, E, U extends GpsLog> extends SmartListExpression<T, E, U> {
    public GpsLogListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public GpsLogListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public GpsLogExpression<T, U, U> first() {
       return new GpsLogExpression(super.first());
    }

    public GpsLogExpression<T, U, U> get(int index) {
      return new GpsLogExpression(super.get(index));
    }
}