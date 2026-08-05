package com.doublechaintech.enterpriselogisticsservice.claimsrecord;

import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy;
import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicyExpression;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class ClaimsRecordExpression<T, E, U extends ClaimsRecord> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public ClaimsRecordExpression(Expression<T, U> expression){
        super(expression);
    }

    public ClaimsRecordExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public ClaimsRecordExpression<T, U, U> updateId(Long id){
        return new ClaimsRecordExpression(this, $it -> {((ClaimsRecord)$it).__internalSet("id", id); return this;});
     }

     public ClaimsRecordExpression<T, U, U> save(UserContext userContext){
        return new ClaimsRecordExpression(this, $it -> ((ClaimsRecord)$it).auditAs("Saved by Expression").save(userContext));
     }

     public ClaimsRecordExpression<T, U, U> save(String intent, UserContext userContext){
        return new ClaimsRecordExpression(this, $it -> ((ClaimsRecord)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getClaimNumber(){
       return apply(ClaimsRecord::getClaimNumber);
    }
    public ClaimsRecordExpression<T, U, U> updateClaimNumber(String claimNumber){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateClaimNumber(claimNumber));
    }

    public Expression<T, String> getDescription(){
       return apply(ClaimsRecord::getDescription);
    }
    public ClaimsRecordExpression<T, U, U> updateDescription(String description){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateDescription(description));
    }

    public Expression<T, BigDecimal> getClaimAmount(){
       return apply(ClaimsRecord::getClaimAmount);
    }
    public ClaimsRecordExpression<T, U, U> updateClaimAmount(BigDecimal claimAmount){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateClaimAmount(claimAmount));
    }

    public Expression<T, String> getStatus(){
       return apply(ClaimsRecord::getStatus);
    }
    public ClaimsRecordExpression<T, U, U> updateStatus(String status){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateStatus(status));
    }

    public Expression<T, LocalDate> getResolutionDate(){
       return apply(ClaimsRecord::getResolutionDate);
    }
    public ClaimsRecordExpression<T, U, U> updateResolutionDate(LocalDate resolutionDate){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateResolutionDate(resolutionDate));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((ClaimsRecord)$it).getMovingOrder());
    }

    public ClaimsRecordExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateMovingOrder(movingOrder));
    }

    public InsurancePolicyExpression<T, U, InsurancePolicy> getInsurancePolicy(){
       return new InsurancePolicyExpression(this, $it ->  ((ClaimsRecord)$it).getInsurancePolicy());
    }

    public ClaimsRecordExpression<T, U, U> updateInsurancePolicy(InsurancePolicy insurancePolicy){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateInsurancePolicy(insurancePolicy));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(ClaimsRecord::getCreatedTime);
    }
    public ClaimsRecordExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(ClaimsRecord::getUpdateTime);
    }
    public ClaimsRecordExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new ClaimsRecordExpression(this, $it ->  ((ClaimsRecord)$it).updateUpdateTime(updateTime));
    }

}