package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PickupAddressExpression<T, E, U extends PickupAddress> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PickupAddressExpression(Expression<T, U> expression){
        super(expression);
    }

    public PickupAddressExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PickupAddressExpression<T, U, U> updateId(Long id){
        return new PickupAddressExpression(this, $it -> {((PickupAddress)$it).__internalSet("id", id); return this;});
     }

     public PickupAddressExpression<T, U, U> save(UserContext userContext){
        return new PickupAddressExpression(this, $it -> ((PickupAddress)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PickupAddressExpression<T, U, U> save(String intent, UserContext userContext){
        return new PickupAddressExpression(this, $it -> ((PickupAddress)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getAddressId(){
       return apply(PickupAddress::getAddressId);
    }
    public PickupAddressExpression<T, U, U> updateAddressId(String addressId){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateAddressId(addressId));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((PickupAddress)$it).getMovingOrder());
    }

    public PickupAddressExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateMovingOrder(movingOrder));
    }

    public Expression<T, String> getAddressLine1(){
       return apply(PickupAddress::getAddressLine1);
    }
    public PickupAddressExpression<T, U, U> updateAddressLine1(String addressLine1){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateAddressLine1(addressLine1));
    }

    public Expression<T, String> getAddressLine2(){
       return apply(PickupAddress::getAddressLine2);
    }
    public PickupAddressExpression<T, U, U> updateAddressLine2(String addressLine2){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateAddressLine2(addressLine2));
    }

    public Expression<T, String> getCity(){
       return apply(PickupAddress::getCity);
    }
    public PickupAddressExpression<T, U, U> updateCity(String city){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateCity(city));
    }

    public Expression<T, String> getState(){
       return apply(PickupAddress::getState);
    }
    public PickupAddressExpression<T, U, U> updateState(String state){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateState(state));
    }

    public Expression<T, String> getZipCode(){
       return apply(PickupAddress::getZipCode);
    }
    public PickupAddressExpression<T, U, U> updateZipCode(String zipCode){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateZipCode(zipCode));
    }

    public Expression<T, String> getCountry(){
       return apply(PickupAddress::getCountry);
    }
    public PickupAddressExpression<T, U, U> updateCountry(String country){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateCountry(country));
    }

    public Expression<T, String> getContactName(){
       return apply(PickupAddress::getContactName);
    }
    public PickupAddressExpression<T, U, U> updateContactName(String contactName){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateContactName(contactName));
    }

    public Expression<T, String> getContactPhone(){
       return apply(PickupAddress::getContactPhone);
    }
    public PickupAddressExpression<T, U, U> updateContactPhone(String contactPhone){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateContactPhone(contactPhone));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(PickupAddress::getCreateTime);
    }
    public PickupAddressExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateCreateTime(createTime));
    }

}