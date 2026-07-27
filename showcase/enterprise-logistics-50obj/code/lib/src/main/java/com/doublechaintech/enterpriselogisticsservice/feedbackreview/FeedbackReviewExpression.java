package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class FeedbackReviewExpression<T, E, U extends FeedbackReview> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public FeedbackReviewExpression(Expression<T, U> expression){
        super(expression);
    }

    public FeedbackReviewExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public FeedbackReviewExpression<T, U, U> updateId(Long id){
        return new FeedbackReviewExpression(this, $it -> {((FeedbackReview)$it).__internalSet("id", id); return this;});
     }

     public FeedbackReviewExpression<T, U, U> save(UserContext userContext){
        return new FeedbackReviewExpression(this, $it -> ((FeedbackReview)$it).auditAs("Saved by Expression").save(userContext));
     }

     public FeedbackReviewExpression<T, U, U> save(String intent, UserContext userContext){
        return new FeedbackReviewExpression(this, $it -> ((FeedbackReview)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, Integer> getRating(){
       return apply(FeedbackReview::getRating);
    }
    public FeedbackReviewExpression<T, U, U> updateRating(Integer rating){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateRating(rating));
    }

    public Expression<T, String> getTitle(){
       return apply(FeedbackReview::getTitle);
    }
    public FeedbackReviewExpression<T, U, U> updateTitle(String title){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateTitle(title));
    }

    public Expression<T, String> getComment(){
       return apply(FeedbackReview::getComment);
    }
    public FeedbackReviewExpression<T, U, U> updateComment(String comment){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateComment(comment));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((FeedbackReview)$it).getMovingOrder());
    }

    public FeedbackReviewExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateMovingOrder(movingOrder));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(FeedbackReview::getCreatedAt);
    }
    public FeedbackReviewExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(FeedbackReview::getUpdatedAt);
    }
    public FeedbackReviewExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateUpdatedAt(updatedAt));
    }

}