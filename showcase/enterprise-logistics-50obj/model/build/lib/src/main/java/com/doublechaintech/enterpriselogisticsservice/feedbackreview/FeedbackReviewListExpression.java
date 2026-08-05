package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class FeedbackReviewListExpression<T, E, U extends FeedbackReview> extends SmartListExpression<T, E, U> {
    public FeedbackReviewListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public FeedbackReviewListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public FeedbackReviewExpression<T, U, U> first() {
       return new FeedbackReviewExpression(super.first());
    }

    public FeedbackReviewExpression<T, U, U> get(int index) {
      return new FeedbackReviewExpression(super.get(index));
    }
}