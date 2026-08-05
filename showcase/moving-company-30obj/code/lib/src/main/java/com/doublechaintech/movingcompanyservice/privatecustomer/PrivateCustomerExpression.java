package com.doublechaintech.movingcompanyservice.privatecustomer;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PrivateCustomerExpression<T, E, U extends PrivateCustomer> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PrivateCustomerExpression(Expression<T, U> expression){
        super(expression);
    }

    public PrivateCustomerExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PrivateCustomerExpression<T, U, U> updateId(Long id){
        return new PrivateCustomerExpression(this, $it -> {((PrivateCustomer)$it).__internalSet("id", id); return this;});
     }

     public PrivateCustomerExpression<T, U, U> save(UserContext userContext){
        return new PrivateCustomerExpression(this, $it -> ((PrivateCustomer)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PrivateCustomerExpression<T, U, U> save(String intent, UserContext userContext){
        return new PrivateCustomerExpression(this, $it -> ((PrivateCustomer)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(PrivateCustomer::getName);
    }
    public PrivateCustomerExpression<T, U, U> updateName(String name){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateName(name));
    }

    public Expression<T, String> getEmail(){
       return apply(PrivateCustomer::getEmail);
    }
    public PrivateCustomerExpression<T, U, U> updateEmail(String email){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateEmail(email));
    }

    public Expression<T, Integer> getPhone(){
       return apply(PrivateCustomer::getPhone);
    }
    public PrivateCustomerExpression<T, U, U> updatePhone(Integer phone){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updatePhone(phone));
    }

    public Expression<T, String> getAddress(){
       return apply(PrivateCustomer::getAddress);
    }
    public PrivateCustomerExpression<T, U, U> updateAddress(String address){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateAddress(address));
    }

    public Expression<T, String> getIdNumber(){
       return apply(PrivateCustomer::getIdNumber);
    }
    public PrivateCustomerExpression<T, U, U> updateIdNumber(String idNumber){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateIdNumber(idNumber));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(PrivateCustomer::getCreateTime);
    }
    public PrivateCustomerExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(PrivateCustomer::getUpdateTime);
    }
    public PrivateCustomerExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateUpdateTime(updateTime));
    }

}