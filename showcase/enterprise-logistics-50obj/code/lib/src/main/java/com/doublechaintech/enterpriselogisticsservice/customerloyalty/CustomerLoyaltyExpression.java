package com.doublechaintech.enterpriselogisticsservice.customerloyalty;

import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class CustomerLoyaltyExpression<T, E, U extends CustomerLoyalty> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CustomerLoyaltyExpression(Expression<T, U> expression){
        super(expression);
    }

    public CustomerLoyaltyExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CustomerLoyaltyExpression<T, U, U> updateId(Long id){
        return new CustomerLoyaltyExpression(this, $it -> {((CustomerLoyalty)$it).__internalSet("id", id); return this;});
     }

     public CustomerLoyaltyExpression<T, U, U> save(UserContext userContext){
        return new CustomerLoyaltyExpression(this, $it -> ((CustomerLoyalty)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CustomerLoyaltyExpression<T, U, U> save(String intent, UserContext userContext){
        return new CustomerLoyaltyExpression(this, $it -> ((CustomerLoyalty)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, Integer> getPoints(){
       return apply(CustomerLoyalty::getPoints);
    }
    public CustomerLoyaltyExpression<T, U, U> updatePoints(Integer points){
       return new CustomerLoyaltyExpression(this, $it ->  ((CustomerLoyalty)$it).updatePoints(points));
    }

    public Expression<T, String> getTier(){
       return apply(CustomerLoyalty::getTier);
    }
    public CustomerLoyaltyExpression<T, U, U> updateTier(String tier){
       return new CustomerLoyaltyExpression(this, $it ->  ((CustomerLoyalty)$it).updateTier(tier));
    }

    public PrivateCustomerExpression<T, U, PrivateCustomer> getPrivateCustomer(){
       return new PrivateCustomerExpression(this, $it ->  ((CustomerLoyalty)$it).getPrivateCustomer());
    }

    public CustomerLoyaltyExpression<T, U, U> updatePrivateCustomer(PrivateCustomer privateCustomer){
       return new CustomerLoyaltyExpression(this, $it ->  ((CustomerLoyalty)$it).updatePrivateCustomer(privateCustomer));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(CustomerLoyalty::getCreatedAt);
    }
    public CustomerLoyaltyExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new CustomerLoyaltyExpression(this, $it ->  ((CustomerLoyalty)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(CustomerLoyalty::getUpdatedAt);
    }
    public CustomerLoyaltyExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new CustomerLoyaltyExpression(this, $it ->  ((CustomerLoyalty)$it).updateUpdatedAt(updatedAt));
    }

}