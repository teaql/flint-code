package com.doublechaintech.enterpriselogisticsservice.performancereview;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PerformanceReviewExpression<T, E, U extends PerformanceReview> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PerformanceReviewExpression(Expression<T, U> expression){
        super(expression);
    }

    public PerformanceReviewExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PerformanceReviewExpression<T, U, U> updateId(Long id){
        return new PerformanceReviewExpression(this, $it -> {((PerformanceReview)$it).__internalSet("id", id); return this;});
     }

     public PerformanceReviewExpression<T, U, U> save(UserContext userContext){
        return new PerformanceReviewExpression(this, $it -> ((PerformanceReview)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PerformanceReviewExpression<T, U, U> save(String intent, UserContext userContext){
        return new PerformanceReviewExpression(this, $it -> ((PerformanceReview)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public StaffMemberExpression<T, U, StaffMember> getStaff(){
       return new StaffMemberExpression(this, $it ->  ((PerformanceReview)$it).getStaff());
    }

    public PerformanceReviewExpression<T, U, U> updateStaff(StaffMember staff){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateStaff(staff));
    }

    public StaffMemberExpression<T, U, StaffMember> getReviewer(){
       return new StaffMemberExpression(this, $it ->  ((PerformanceReview)$it).getReviewer());
    }

    public PerformanceReviewExpression<T, U, U> updateReviewer(StaffMember reviewer){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateReviewer(reviewer));
    }

    public Expression<T, LocalDate> getReviewDate(){
       return apply(PerformanceReview::getReviewDate);
    }
    public PerformanceReviewExpression<T, U, U> updateReviewDate(LocalDate reviewDate){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateReviewDate(reviewDate));
    }

    public Expression<T, String> getScore(){
       return apply(PerformanceReview::getScore);
    }
    public PerformanceReviewExpression<T, U, U> updateScore(String score){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateScore(score));
    }

    public Expression<T, String> getComments(){
       return apply(PerformanceReview::getComments);
    }
    public PerformanceReviewExpression<T, U, U> updateComments(String comments){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateComments(comments));
    }

    public Expression<T, String> getStatus(){
       return apply(PerformanceReview::getStatus);
    }
    public PerformanceReviewExpression<T, U, U> updateStatus(String status){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(PerformanceReview::getCreatedAt);
    }
    public PerformanceReviewExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(PerformanceReview::getUpdatedAt);
    }
    public PerformanceReviewExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new PerformanceReviewExpression(this, $it ->  ((PerformanceReview)$it).updateUpdatedAt(updatedAt));
    }

}