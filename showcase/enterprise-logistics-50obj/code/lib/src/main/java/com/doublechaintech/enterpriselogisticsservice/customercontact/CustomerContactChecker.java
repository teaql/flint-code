package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class CustomerContactChecker implements Checker<CustomerContact>{

    public String type(){
        return CustomerContact.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, CustomerContact customerContact, ObjectLocation _parentLocation){
        if(needCheck(_ctx, customerContact)){
            markAsChecked(_ctx, customerContact);
            doCheck(_ctx, customerContact, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, CustomerContact customerContact, ObjectLocation _parentLocation){
      if((customerContact == null)){
         return;
      }
      if(customerContact.newItem()){
        if(customerContact.getCreatedAt() == null){
           customerContact.updateCreatedAt(java.time.LocalDateTime.now());
        }if(customerContact.getUpdatedAt() == null){
           customerContact.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(customerContact.updateItem()){
        customerContact.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkFirstName(_ctx, customerContact.getProperty(CustomerContact.FIRST_NAME_PROPERTY), newLocation(_parentLocation, CustomerContact.FIRST_NAME_PROPERTY));
      checkLastName(_ctx, customerContact.getProperty(CustomerContact.LAST_NAME_PROPERTY), newLocation(_parentLocation, CustomerContact.LAST_NAME_PROPERTY));
      checkEmail(_ctx, customerContact.getProperty(CustomerContact.EMAIL_PROPERTY), newLocation(_parentLocation, CustomerContact.EMAIL_PROPERTY));
      checkPhone(_ctx, customerContact.getProperty(CustomerContact.PHONE_PROPERTY), newLocation(_parentLocation, CustomerContact.PHONE_PROPERTY));
      checkIsPrimary(_ctx, customerContact.getProperty(CustomerContact.IS_PRIMARY_PROPERTY), newLocation(_parentLocation, CustomerContact.IS_PRIMARY_PROPERTY));
      checkPrivateCustomer(_ctx, customerContact.getProperty(CustomerContact.PRIVATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerContact.PRIVATE_CUSTOMER_PROPERTY));
      checkCorporateCustomer(_ctx, customerContact.getProperty(CustomerContact.CORPORATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerContact.CORPORATE_CUSTOMER_PROPERTY));
      checkCreatedAt(_ctx, customerContact.getProperty(CustomerContact.CREATED_AT_PROPERTY), newLocation(_parentLocation, CustomerContact.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, customerContact.getProperty(CustomerContact.UPDATED_AT_PROPERTY), newLocation(_parentLocation, CustomerContact.UPDATED_AT_PROPERTY));
    }

    public void checkFirstName(UserContext _ctx, String firstName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, firstName);
    if((firstName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, firstName);

    }
    public void checkLastName(UserContext _ctx, String lastName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, lastName);
    if((lastName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, lastName);

    }
    public void checkEmail(UserContext _ctx, String email, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, email);
    if((email == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, email);

    }
    public void checkPhone(UserContext _ctx, String phone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, phone);
    if((phone == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, phone);

    }
    public void checkIsPrimary(UserContext _ctx, Boolean isPrimary, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, isPrimary);
    if((isPrimary == null)){
        return;
    }
    }
    public void checkPrivateCustomer(UserContext _ctx, PrivateCustomer privateCustomer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, privateCustomer);
    if((privateCustomer == null)){
        return;
    }
    new PrivateCustomerChecker().checkAndFix(_ctx, privateCustomer, _parentLocation);
    }
    public void checkCorporateCustomer(UserContext _ctx, CorporateCustomer corporateCustomer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, corporateCustomer);
    if((corporateCustomer == null)){
        return;
    }
    new CorporateCustomerChecker().checkAndFix(_ctx, corporateCustomer, _parentLocation);
    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}