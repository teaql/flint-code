package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactListExpression;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractListExpression;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class CorporateCustomerExpression<T, E, U extends CorporateCustomer> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CorporateCustomerExpression(Expression<T, U> expression){
        super(expression);
    }

    public CorporateCustomerExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CorporateCustomerExpression<T, U, U> updateId(Long id){
        return new CorporateCustomerExpression(this, $it -> {((CorporateCustomer)$it).__internalSet("id", id); return this;});
     }

     public CorporateCustomerExpression<T, U, U> save(UserContext userContext){
        return new CorporateCustomerExpression(this, $it -> ((CorporateCustomer)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CorporateCustomerExpression<T, U, U> save(String intent, UserContext userContext){
        return new CorporateCustomerExpression(this, $it -> ((CorporateCustomer)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(CorporateCustomer::getName);
    }
    public CorporateCustomerExpression<T, U, U> updateName(String name){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateName(name));
    }

    public Expression<T, String> getRegistrationNumber(){
       return apply(CorporateCustomer::getRegistrationNumber);
    }
    public CorporateCustomerExpression<T, U, U> updateRegistrationNumber(String registrationNumber){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateRegistrationNumber(registrationNumber));
    }

    public Expression<T, String> getIndustry(){
       return apply(CorporateCustomer::getIndustry);
    }
    public CorporateCustomerExpression<T, U, U> updateIndustry(String industry){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateIndustry(industry));
    }

    public Expression<T, Integer> getEmployeeCount(){
       return apply(CorporateCustomer::getEmployeeCount);
    }
    public CorporateCustomerExpression<T, U, U> updateEmployeeCount(Integer employeeCount){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateEmployeeCount(employeeCount));
    }

    public Expression<T, String> getBillingAddress(){
       return apply(CorporateCustomer::getBillingAddress);
    }
    public CorporateCustomerExpression<T, U, U> updateBillingAddress(String billingAddress){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateBillingAddress(billingAddress));
    }

    public Expression<T, String> getContactEmail(){
       return apply(CorporateCustomer::getContactEmail);
    }
    public CorporateCustomerExpression<T, U, U> updateContactEmail(String contactEmail){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateContactEmail(contactEmail));
    }

    public Expression<T, String> getContactPhone(){
       return apply(CorporateCustomer::getContactPhone);
    }
    public CorporateCustomerExpression<T, U, U> updateContactPhone(String contactPhone){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateContactPhone(contactPhone));
    }

    public Expression<T, String> getCustomerType(){
       return apply(CorporateCustomer::getCustomerType);
    }
    public CorporateCustomerExpression<T, U, U> updateCustomerType(String customerType){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateCustomerType(customerType));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(CorporateCustomer::getCreatedAt);
    }
    public CorporateCustomerExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(CorporateCustomer::getUpdatedAt);
    }
    public CorporateCustomerExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateUpdatedAt(updatedAt));
    }

    public CustomerContactListExpression<T, U, CustomerContact> getCustomerContactList(){
        return new CustomerContactListExpression(this, $it ->  ((CorporateCustomer)$it).getCustomerContactList());
    }
    public ServiceQuoteListExpression<T, U, ServiceQuote> getServiceQuoteList(){
        return new ServiceQuoteListExpression(this, $it ->  ((CorporateCustomer)$it).getServiceQuoteList());
    }
    public ServiceContractListExpression<T, U, ServiceContract> getServiceContractList(){
        return new ServiceContractListExpression(this, $it ->  ((CorporateCustomer)$it).getServiceContractList());
    }
    public CorporateCustomerExpression<T, U, U> addCustomerContact(CustomerContact customerContact){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addCustomerContact(customerContact));
    }
    public CorporateCustomerExpression<T, U, U> addServiceQuote(ServiceQuote serviceQuote){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addServiceQuote(serviceQuote));
    }
    public CorporateCustomerExpression<T, U, U> addServiceContract(ServiceContract serviceContract){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addServiceContract(serviceContract));
    }
}