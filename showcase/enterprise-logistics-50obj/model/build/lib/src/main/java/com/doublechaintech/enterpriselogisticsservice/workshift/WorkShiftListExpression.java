package com.doublechaintech.enterpriselogisticsservice.workshift;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class WorkShiftListExpression<T, E, U extends WorkShift> extends SmartListExpression<T, E, U> {
    public WorkShiftListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public WorkShiftListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public WorkShiftExpression<T, U, U> first() {
       return new WorkShiftExpression(super.first());
    }

    public WorkShiftExpression<T, U, U> get(int index) {
      return new WorkShiftExpression(super.get(index));
    }
}