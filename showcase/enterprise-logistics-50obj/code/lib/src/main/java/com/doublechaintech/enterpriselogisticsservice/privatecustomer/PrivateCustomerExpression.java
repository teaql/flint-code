package com.doublechaintech.enterpriselogisticsservice.privatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactListExpression;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyListExpression;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderListExpression;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteListExpression;
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

    public Expression<T, String> getPhone(){
       return apply(PrivateCustomer::getPhone);
    }
    public PrivateCustomerExpression<T, U, U> updatePhone(String phone){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updatePhone(phone));
    }

    public Expression<T, String> getEmail(){
       return apply(PrivateCustomer::getEmail);
    }
    public PrivateCustomerExpression<T, U, U> updateEmail(String email){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateEmail(email));
    }

    public Expression<T, String> getAddressLine1(){
       return apply(PrivateCustomer::getAddressLine1);
    }
    public PrivateCustomerExpression<T, U, U> updateAddressLine1(String addressLine1){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateAddressLine1(addressLine1));
    }

    public Expression<T, String> getAddressLine2(){
       return apply(PrivateCustomer::getAddressLine2);
    }
    public PrivateCustomerExpression<T, U, U> updateAddressLine2(String addressLine2){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateAddressLine2(addressLine2));
    }

    public Expression<T, String> getCity(){
       return apply(PrivateCustomer::getCity);
    }
    public PrivateCustomerExpression<T, U, U> updateCity(String city){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCity(city));
    }

    public Expression<T, String> getState(){
       return apply(PrivateCustomer::getState);
    }
    public PrivateCustomerExpression<T, U, U> updateState(String state){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateState(state));
    }

    public Expression<T, String> getZipCode(){
       return apply(PrivateCustomer::getZipCode);
    }
    public PrivateCustomerExpression<T, U, U> updateZipCode(String zipCode){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateZipCode(zipCode));
    }

    public Expression<T, String> getCountry(){
       return apply(PrivateCustomer::getCountry);
    }
    public PrivateCustomerExpression<T, U, U> updateCountry(String country){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCountry(country));
    }

    public Expression<T, String> getCustomerType(){
       return apply(PrivateCustomer::getCustomerType);
    }
    public PrivateCustomerExpression<T, U, U> updateCustomerType(String customerType){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCustomerType(customerType));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(PrivateCustomer::getCreatedAt);
    }
    public PrivateCustomerExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(PrivateCustomer::getUpdatedAt);
    }
    public PrivateCustomerExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateUpdatedAt(updatedAt));
    }

    public MovingOrderListExpression<T, U, MovingOrder> getMovingOrderList(){
        return new MovingOrderListExpression(this, $it ->  ((PrivateCustomer)$it).getMovingOrderList());
    }
    public CustomerContactListExpression<T, U, CustomerContact> getCustomerContactList(){
        return new CustomerContactListExpression(this, $it ->  ((PrivateCustomer)$it).getCustomerContactList());
    }
    public ServiceQuoteListExpression<T, U, ServiceQuote> getServiceQuoteList(){
        return new ServiceQuoteListExpression(this, $it ->  ((PrivateCustomer)$it).getServiceQuoteList());
    }
    public CustomerLoyaltyListExpression<T, U, CustomerLoyalty> getCustomerLoyaltyList(){
        return new CustomerLoyaltyListExpression(this, $it ->  ((PrivateCustomer)$it).getCustomerLoyaltyList());
    }
    public PrivateCustomerExpression<T, U, U> addMovingOrder(MovingOrder movingOrder){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addMovingOrder(movingOrder));
    }
    public PrivateCustomerExpression<T, U, U> addCustomerContact(CustomerContact customerContact){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addCustomerContact(customerContact));
    }
    public PrivateCustomerExpression<T, U, U> addServiceQuote(ServiceQuote serviceQuote){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addServiceQuote(serviceQuote));
    }
    public PrivateCustomerExpression<T, U, U> addCustomerLoyalty(CustomerLoyalty customerLoyalty){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addCustomerLoyalty(customerLoyalty));
    }
}