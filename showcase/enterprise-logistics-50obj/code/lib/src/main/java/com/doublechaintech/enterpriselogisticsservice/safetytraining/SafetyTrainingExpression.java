package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SafetyTrainingExpression<T, E, U extends SafetyTraining> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SafetyTrainingExpression(Expression<T, U> expression){
        super(expression);
    }

    public SafetyTrainingExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SafetyTrainingExpression<T, U, U> updateId(Long id){
        return new SafetyTrainingExpression(this, $it -> {((SafetyTraining)$it).__internalSet("id", id); return this;});
     }

     public SafetyTrainingExpression<T, U, U> save(UserContext userContext){
        return new SafetyTrainingExpression(this, $it -> ((SafetyTraining)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SafetyTrainingExpression<T, U, U> save(String intent, UserContext userContext){
        return new SafetyTrainingExpression(this, $it -> ((SafetyTraining)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getTitle(){
       return apply(SafetyTraining::getTitle);
    }
    public SafetyTrainingExpression<T, U, U> updateTitle(String title){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateTitle(title));
    }

    public Expression<T, String> getDescription(){
       return apply(SafetyTraining::getDescription);
    }
    public SafetyTrainingExpression<T, U, U> updateDescription(String description){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateDescription(description));
    }

    public Expression<T, String> getDurationHours(){
       return apply(SafetyTraining::getDurationHours);
    }
    public SafetyTrainingExpression<T, U, U> updateDurationHours(String durationHours){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateDurationHours(durationHours));
    }

    public Expression<T, LocalDate> getCompletionDate(){
       return apply(SafetyTraining::getCompletionDate);
    }
    public SafetyTrainingExpression<T, U, U> updateCompletionDate(LocalDate completionDate){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateCompletionDate(completionDate));
    }

    public Expression<T, String> getStatus(){
       return apply(SafetyTraining::getStatus);
    }
    public SafetyTrainingExpression<T, U, U> updateStatus(String status){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(SafetyTraining::getCreatedAt);
    }
    public SafetyTrainingExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(SafetyTraining::getUpdatedAt);
    }
    public SafetyTrainingExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateUpdatedAt(updatedAt));
    }

}