package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
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

    public Expression<T, String> getComment(){
       return apply(FeedbackReview::getComment);
    }
    public FeedbackReviewExpression<T, U, U> updateComment(String comment){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateComment(comment));
    }

    public Expression<T, LocalDate> getReviewDate(){
       return apply(FeedbackReview::getReviewDate);
    }
    public FeedbackReviewExpression<T, U, U> updateReviewDate(LocalDate reviewDate){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateReviewDate(reviewDate));
    }

    public PrivateCustomerExpression<T, U, PrivateCustomer> getPrivateCustomer(){
       return new PrivateCustomerExpression(this, $it ->  ((FeedbackReview)$it).getPrivateCustomer());
    }

    public FeedbackReviewExpression<T, U, U> updatePrivateCustomer(PrivateCustomer privateCustomer){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updatePrivateCustomer(privateCustomer));
    }

    public CorporateCustomerExpression<T, U, CorporateCustomer> getCorporateCustomer(){
       return new CorporateCustomerExpression(this, $it ->  ((FeedbackReview)$it).getCorporateCustomer());
    }

    public FeedbackReviewExpression<T, U, U> updateCorporateCustomer(CorporateCustomer corporateCustomer){
       return new FeedbackReviewExpression(this, $it ->  ((FeedbackReview)$it).updateCorporateCustomer(corporateCustomer));
    }

}