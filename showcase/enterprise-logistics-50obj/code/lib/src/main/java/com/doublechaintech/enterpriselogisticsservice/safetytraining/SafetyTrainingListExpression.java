package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class SafetyTrainingListExpression<T, E, U extends SafetyTraining> extends SmartListExpression<T, E, U> {
    public SafetyTrainingListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public SafetyTrainingListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public SafetyTrainingExpression<T, U, U> first() {
       return new SafetyTrainingExpression(super.first());
    }

    public SafetyTrainingExpression<T, U, U> get(int index) {
      return new SafetyTrainingExpression(super.get(index));
    }
}