package com.doublechaintech.enterpriselogisticsservice.servicecontract;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class ServiceContractExpression<T, E, U extends ServiceContract> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public ServiceContractExpression(Expression<T, U> expression){
        super(expression);
    }

    public ServiceContractExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public ServiceContractExpression<T, U, U> updateId(Long id){
        return new ServiceContractExpression(this, $it -> {((ServiceContract)$it).__internalSet("id", id); return this;});
     }

     public ServiceContractExpression<T, U, U> save(UserContext userContext){
        return new ServiceContractExpression(this, $it -> ((ServiceContract)$it).auditAs("Saved by Expression").save(userContext));
     }

     public ServiceContractExpression<T, U, U> save(String intent, UserContext userContext){
        return new ServiceContractExpression(this, $it -> ((ServiceContract)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getContractNumber(){
       return apply(ServiceContract::getContractNumber);
    }
    public ServiceContractExpression<T, U, U> updateContractNumber(String contractNumber){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateContractNumber(contractNumber));
    }

    public Expression<T, String> getTitle(){
       return apply(ServiceContract::getTitle);
    }
    public ServiceContractExpression<T, U, U> updateTitle(String title){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateTitle(title));
    }

    public Expression<T, LocalDate> getStartDate(){
       return apply(ServiceContract::getStartDate);
    }
    public ServiceContractExpression<T, U, U> updateStartDate(LocalDate startDate){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateStartDate(startDate));
    }

    public Expression<T, LocalDate> getEndDate(){
       return apply(ServiceContract::getEndDate);
    }
    public ServiceContractExpression<T, U, U> updateEndDate(LocalDate endDate){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateEndDate(endDate));
    }

    public Expression<T, String> getStatus(){
       return apply(ServiceContract::getStatus);
    }
    public ServiceContractExpression<T, U, U> updateStatus(String status){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateStatus(status));
    }

    public Expression<T, BigDecimal> getTotalValue(){
       return apply(ServiceContract::getTotalValue);
    }
    public ServiceContractExpression<T, U, U> updateTotalValue(BigDecimal totalValue){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateTotalValue(totalValue));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(ServiceContract::getCreatedTime);
    }
    public ServiceContractExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(ServiceContract::getUpdatedTime);
    }
    public ServiceContractExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new ServiceContractExpression(this, $it ->  ((ServiceContract)$it).updateUpdatedTime(updatedTime));
    }

}