package com.doublechaintech.enterpriselogisticsservice.safetytraining;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
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


    public StaffMemberExpression<T, U, StaffMember> getStaff(){
       return new StaffMemberExpression(this, $it ->  ((SafetyTraining)$it).getStaff());
    }

    public SafetyTrainingExpression<T, U, U> updateStaff(StaffMember staff){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateStaff(staff));
    }

    public Expression<T, String> getCourseName(){
       return apply(SafetyTraining::getCourseName);
    }
    public SafetyTrainingExpression<T, U, U> updateCourseName(String courseName){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateCourseName(courseName));
    }

    public Expression<T, LocalDate> getCompletionDate(){
       return apply(SafetyTraining::getCompletionDate);
    }
    public SafetyTrainingExpression<T, U, U> updateCompletionDate(LocalDate completionDate){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateCompletionDate(completionDate));
    }

    public Expression<T, String> getCertificateNumber(){
       return apply(SafetyTraining::getCertificateNumber);
    }
    public SafetyTrainingExpression<T, U, U> updateCertificateNumber(String certificateNumber){
       return new SafetyTrainingExpression(this, $it ->  ((SafetyTraining)$it).updateCertificateNumber(certificateNumber));
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