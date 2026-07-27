package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
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


    public Expression<T, String> getName(){
       return apply(CustomerContact::getName);
    }
    public CustomerContactExpression<T, U, U> updateName(String name){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateName(name));
    }

    public Expression<T, String> getPhone(){
       return apply(CustomerContact::getPhone);
    }
    public CustomerContactExpression<T, U, U> updatePhone(String phone){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updatePhone(phone));
    }

    public Expression<T, String> getEmail(){
       return apply(CustomerContact::getEmail);
    }
    public CustomerContactExpression<T, U, U> updateEmail(String email){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateEmail(email));
    }

    public Expression<T, String> getRelationship(){
       return apply(CustomerContact::getRelationship);
    }
    public CustomerContactExpression<T, U, U> updateRelationship(String relationship){
       return new CustomerContactExpression(this, $it ->  ((CustomerContact)$it).updateRelationship(relationship));
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

}