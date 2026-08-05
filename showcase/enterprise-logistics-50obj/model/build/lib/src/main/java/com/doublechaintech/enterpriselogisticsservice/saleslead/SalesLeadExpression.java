package com.doublechaintech.enterpriselogisticsservice.saleslead;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SalesLeadExpression<T, E, U extends SalesLead> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SalesLeadExpression(Expression<T, U> expression){
        super(expression);
    }

    public SalesLeadExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SalesLeadExpression<T, U, U> updateId(Long id){
        return new SalesLeadExpression(this, $it -> {((SalesLead)$it).__internalSet("id", id); return this;});
     }

     public SalesLeadExpression<T, U, U> save(UserContext userContext){
        return new SalesLeadExpression(this, $it -> ((SalesLead)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SalesLeadExpression<T, U, U> save(String intent, UserContext userContext){
        return new SalesLeadExpression(this, $it -> ((SalesLead)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(SalesLead::getName);
    }
    public SalesLeadExpression<T, U, U> updateName(String name){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateName(name));
    }

    public Expression<T, String> getEmail(){
       return apply(SalesLead::getEmail);
    }
    public SalesLeadExpression<T, U, U> updateEmail(String email){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateEmail(email));
    }

    public Expression<T, Integer> getPhone(){
       return apply(SalesLead::getPhone);
    }
    public SalesLeadExpression<T, U, U> updatePhone(Integer phone){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updatePhone(phone));
    }

    public Expression<T, String> getSource(){
       return apply(SalesLead::getSource);
    }
    public SalesLeadExpression<T, U, U> updateSource(String source){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateSource(source));
    }

    public Expression<T, String> getStatus(){
       return apply(SalesLead::getStatus);
    }
    public SalesLeadExpression<T, U, U> updateStatus(String status){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateStatus(status));
    }

    public Expression<T, BigDecimal> getEstimatedValue(){
       return apply(SalesLead::getEstimatedValue);
    }
    public SalesLeadExpression<T, U, U> updateEstimatedValue(BigDecimal estimatedValue){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateEstimatedValue(estimatedValue));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(SalesLead::getCreatedTime);
    }
    public SalesLeadExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(SalesLead::getUpdateTime);
    }
    public SalesLeadExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateUpdateTime(updateTime));
    }

}