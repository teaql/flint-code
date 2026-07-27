package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class CustomerContactExpression<T, E, U extends CustomerContact> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CustomerContactExpression(Expression<T, U> expression){
        super(expression);
    }

    public CustomerContactExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CustomerContactExpression<T, U, U> updateId(Long id){
        return new CustomerContactExpression(this, $it -> {((CustomerContact)$it).__internalSet("id", id); return this;});
     }

     public CustomerContactExpression<T, U, U> save(UserContext userContext){
        return new CustomerContactExpression(this, $it -> ((CustomerContact)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CustomerContactExpression<T, U, U> save(String intent, UserContext userContext){
        return new CustomerContactExpression(this, $it -> ((CustomerContact)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getFirstName(){
       return apply(CustomerContact::getFirstName);
    }
    public CustomerContactExpression<T, U, U> updateFirstName(String firstName){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateFirstName(firstName));
    }

    public Expression<T, String> getLastName(){
       return apply(CustomerContact::getLastName);
    }
    public CustomerContactExpression<T, U, U> updateLastName(String lastName){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateLastName(lastName));
    }

    public Expression<T, String> getEmail(){
       return apply(CustomerContact::getEmail);
    }
    public CustomerContactExpression<T, U, U> updateEmail(String email){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateEmail(email));
    }

    public Expression<T, String> getPhone(){
       return apply(CustomerContact::getPhone);
    }
    public CustomerContactExpression<T, U, U> updatePhone(String phone){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updatePhone(phone));
    }

    public Expression<T, Boolean> isIsPrimary(){
       return apply(CustomerContact::isIsPrimary);
    }
    public CustomerContactExpression<T, U, U> updateIsPrimary(Boolean isPrimary){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateIsPrimary(isPrimary));
    }

    public PrivateCustomerExpression<T, U, PrivateCustomer> getPrivateCustomer(){
       return new PrivateCustomerExpression(this, $it ->  ((CustomerContact)$it).getPrivateCustomer());
    }

    public CustomerContactExpression<T, U, U> updatePrivateCustomer(PrivateCustomer privateCustomer){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updatePrivateCustomer(privateCustomer));
    }

    public CorporateCustomerExpression<T, U, CorporateCustomer> getCorporateCustomer(){
       return new CorporateCustomerExpression(this, $it ->  ((CustomerContact)$it).getCorporateCustomer());
    }

    public CustomerContactExpression<T, U, U> updateCorporateCustomer(CorporateCustomer corporateCustomer){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateCorporateCustomer(corporateCustomer));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(CustomerContact::getCreatedAt);
    }
    public CustomerContactExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(CustomerContact::getUpdatedAt);
    }
    public CustomerContactExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateUpdatedAt(updatedAt));
    }

}