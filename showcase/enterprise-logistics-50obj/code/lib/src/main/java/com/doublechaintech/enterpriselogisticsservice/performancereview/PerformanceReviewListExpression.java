package com.doublechaintech.enterpriselogisticsservice.performancereview;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class PerformanceReviewListExpression<T, E, U extends PerformanceReview> extends SmartListExpression<T, E, U> {
    public PerformanceReviewListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public PerformanceReviewListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public PerformanceReviewExpression<T, U, U> first() {
       return new PerformanceReviewExpression(super.first());
    }

    public PerformanceReviewExpression<T, U, U> get(int index) {
      return new PerformanceReviewExpression(super.get(index));
    }
}