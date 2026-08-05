package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

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
      }else if(customerContact.updateItem()){
      }
      checkName(_ctx, customerContact.getProperty(CustomerContact.NAME_PROPERTY), newLocation(_parentLocation, CustomerContact.NAME_PROPERTY));
      checkPhone(_ctx, customerContact.getProperty(CustomerContact.PHONE_PROPERTY), newLocation(_parentLocation, CustomerContact.PHONE_PROPERTY));
      checkEmail(_ctx, customerContact.getProperty(CustomerContact.EMAIL_PROPERTY), newLocation(_parentLocation, CustomerContact.EMAIL_PROPERTY));
      checkRelationship(_ctx, customerContact.getProperty(CustomerContact.RELATIONSHIP_PROPERTY), newLocation(_parentLocation, CustomerContact.RELATIONSHIP_PROPERTY));
      checkPrivateCustomer(_ctx, customerContact.getProperty(CustomerContact.PRIVATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerContact.PRIVATE_CUSTOMER_PROPERTY));
      checkCorporateCustomer(_ctx, customerContact.getProperty(CustomerContact.CORPORATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerContact.CORPORATE_CUSTOMER_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkPhone(UserContext _ctx, String phone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, phone);
    if((phone == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, phone);

    }
    public void checkEmail(UserContext _ctx, String email, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, email);
    if((email == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, email);

    }
    public void checkRelationship(UserContext _ctx, String relationship, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, relationship);
    if((relationship == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, relationship);

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
}