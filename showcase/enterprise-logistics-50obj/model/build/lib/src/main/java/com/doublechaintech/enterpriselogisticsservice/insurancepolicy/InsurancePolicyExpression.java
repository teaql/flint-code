package com.doublechaintech.enterpriselogisticsservice.insurancepolicy;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class InsurancePolicyExpression<T, E, U extends InsurancePolicy> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public InsurancePolicyExpression(Expression<T, U> expression){
        super(expression);
    }

    public InsurancePolicyExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public InsurancePolicyExpression<T, U, U> updateId(Long id){
        return new InsurancePolicyExpression(this, $it -> {((InsurancePolicy)$it).__internalSet("id", id); return this;});
     }

     public InsurancePolicyExpression<T, U, U> save(UserContext userContext){
        return new InsurancePolicyExpression(this, $it -> ((InsurancePolicy)$it).auditAs("Saved by Expression").save(userContext));
     }

     public InsurancePolicyExpression<T, U, U> save(String intent, UserContext userContext){
        return new InsurancePolicyExpression(this, $it -> ((InsurancePolicy)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getPolicyNumber(){
       return apply(InsurancePolicy::getPolicyNumber);
    }
    public InsurancePolicyExpression<T, U, U> updatePolicyNumber(String policyNumber){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updatePolicyNumber(policyNumber));
    }

    public Expression<T, String> getProvider(){
       return apply(InsurancePolicy::getProvider);
    }
    public InsurancePolicyExpression<T, U, U> updateProvider(String provider){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateProvider(provider));
    }

    public Expression<T, BigDecimal> getCoverageAmount(){
       return apply(InsurancePolicy::getCoverageAmount);
    }
    public InsurancePolicyExpression<T, U, U> updateCoverageAmount(BigDecimal coverageAmount){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateCoverageAmount(coverageAmount));
    }

    public Expression<T, BigDecimal> getPremium(){
       return apply(InsurancePolicy::getPremium);
    }
    public InsurancePolicyExpression<T, U, U> updatePremium(BigDecimal premium){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updatePremium(premium));
    }

    public Expression<T, LocalDate> getStartDate(){
       return apply(InsurancePolicy::getStartDate);
    }
    public InsurancePolicyExpression<T, U, U> updateStartDate(LocalDate startDate){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateStartDate(startDate));
    }

    public Expression<T, LocalDate> getEndDate(){
       return apply(InsurancePolicy::getEndDate);
    }
    public InsurancePolicyExpression<T, U, U> updateEndDate(LocalDate endDate){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateEndDate(endDate));
    }

    public Expression<T, String> getStatus(){
       return apply(InsurancePolicy::getStatus);
    }
    public InsurancePolicyExpression<T, U, U> updateStatus(String status){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(InsurancePolicy::getCreatedTime);
    }
    public InsurancePolicyExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(InsurancePolicy::getUpdatedTime);
    }
    public InsurancePolicyExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new InsurancePolicyExpression(this, $it ->  ((InsurancePolicy)$it).updateUpdatedTime(updatedTime));
    }

}