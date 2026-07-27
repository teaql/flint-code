package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
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

    public Expression<T, String> getStateProvince(){
       return apply(PickupAddress::getStateProvince);
    }
    public PickupAddressExpression<T, U, U> updateStateProvince(String stateProvince){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateStateProvince(stateProvince));
    }

    public Expression<T, String> getPostalCode(){
       return apply(PickupAddress::getPostalCode);
    }
    public PickupAddressExpression<T, U, U> updatePostalCode(String postalCode){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updatePostalCode(postalCode));
    }

    public Expression<T, String> getCountry(){
       return apply(PickupAddress::getCountry);
    }
    public PickupAddressExpression<T, U, U> updateCountry(String country){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateCountry(country));
    }

    public Expression<T, BigDecimal> getLatitude(){
       return apply(PickupAddress::getLatitude);
    }
    public PickupAddressExpression<T, U, U> updateLatitude(BigDecimal latitude){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateLatitude(latitude));
    }

    public Expression<T, String> getLongitude(){
       return apply(PickupAddress::getLongitude);
    }
    public PickupAddressExpression<T, U, U> updateLongitude(String longitude){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateLongitude(longitude));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(PickupAddress::getCreatedTime);
    }
    public PickupAddressExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(PickupAddress::getUpdatedTime);
    }
    public PickupAddressExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).updateUpdatedTime(updatedTime));
    }

    public MovingOrderListExpression<T, U, MovingOrder> getMovingOrderListAsPickupAddress(){
        return new MovingOrderListExpression(this, $it ->  ((PickupAddress)$it).getMovingOrderListAsPickupAddress());
    }
    public MovingOrderListExpression<T, U, MovingOrder> getMovingOrderListAsDeliveryAddress(){
        return new MovingOrderListExpression(this, $it ->  ((PickupAddress)$it).getMovingOrderListAsDeliveryAddress());
    }
    public PickupAddressExpression<T, U, U> addMovingOrderAsPickupAddress(MovingOrder movingOrder){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).addMovingOrderAsPickupAddress(movingOrder));
    }
    public PickupAddressExpression<T, U, U> addMovingOrderAsDeliveryAddress(MovingOrder movingOrder){
       return new PickupAddressExpression(this, $it ->  ((PickupAddress)$it).addMovingOrderAsDeliveryAddress(movingOrder));
    }
}