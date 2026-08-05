package com.doublechaintech.enterpriselogisticsservice.workedhours;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class WorkedHoursListExpression<T, E, U extends WorkedHours> extends SmartListExpression<T, E, U> {
    public WorkedHoursListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public WorkedHoursListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public WorkedHoursExpression<T, U, U> first() {
       return new WorkedHoursExpression(super.first());
    }

    public WorkedHoursExpression<T, U, U> get(int index) {
      return new WorkedHoursExpression(super.get(index));
    }
}