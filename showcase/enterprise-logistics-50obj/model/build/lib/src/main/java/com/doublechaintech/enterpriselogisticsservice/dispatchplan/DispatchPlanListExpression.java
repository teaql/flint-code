package com.doublechaintech.enterpriselogisticsservice.dispatchplan;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class DispatchPlanListExpression<T, E, U extends DispatchPlan> extends SmartListExpression<T, E, U> {
    public DispatchPlanListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public DispatchPlanListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public DispatchPlanExpression<T, U, U> first() {
       return new DispatchPlanExpression(super.first());
    }

    public DispatchPlanExpression<T, U, U> get(int index) {
      return new DispatchPlanExpression(super.get(index));
    }
}