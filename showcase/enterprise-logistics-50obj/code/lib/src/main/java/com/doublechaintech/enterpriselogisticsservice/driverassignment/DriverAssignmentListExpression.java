package com.doublechaintech.enterpriselogisticsservice.driverassignment;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class DriverAssignmentListExpression<T, E, U extends DriverAssignment> extends SmartListExpression<T, E, U> {
    public DriverAssignmentListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public DriverAssignmentListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public DriverAssignmentExpression<T, U, U> first() {
       return new DriverAssignmentExpression(super.first());
    }

    public DriverAssignmentExpression<T, U, U> get(int index) {
      return new DriverAssignmentExpression(super.get(index));
    }
}