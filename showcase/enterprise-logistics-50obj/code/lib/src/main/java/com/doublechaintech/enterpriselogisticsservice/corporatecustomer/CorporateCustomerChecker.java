package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactChecker;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContractChecker;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class CorporateCustomerChecker implements Checker<CorporateCustomer>{

    public String type(){
        return CorporateCustomer.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, CorporateCustomer corporateCustomer, ObjectLocation _parentLocation){
        if(needCheck(_ctx, corporateCustomer)){
            markAsChecked(_ctx, corporateCustomer);
            doCheck(_ctx, corporateCustomer, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, CorporateCustomer corporateCustomer, ObjectLocation _parentLocation){
      if((corporateCustomer == null)){
         return;
      }
      if(corporateCustomer.newItem()){
        if(corporateCustomer.getCreatedAt() == null){
           corporateCustomer.updateCreatedAt(java.time.LocalDateTime.now());
        }if(corporateCustomer.getUpdatedAt() == null){
           corporateCustomer.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(corporateCustomer.updateItem()){
        corporateCustomer.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, corporateCustomer.getProperty(CorporateCustomer.NAME_PROPERTY), newLocation(_parentLocation, CorporateCustomer.NAME_PROPERTY));
      checkRegistrationNumber(_ctx, corporateCustomer.getProperty(CorporateCustomer.REGISTRATION_NUMBER_PROPERTY), newLocation(_parentLocation, CorporateCustomer.REGISTRATION_NUMBER_PROPERTY));
      checkIndustry(_ctx, corporateCustomer.getProperty(CorporateCustomer.INDUSTRY_PROPERTY), newLocation(_parentLocation, CorporateCustomer.INDUSTRY_PROPERTY));
      checkEmployeeCount(_ctx, corporateCustomer.getProperty(CorporateCustomer.EMPLOYEE_COUNT_PROPERTY), newLocation(_parentLocation, CorporateCustomer.EMPLOYEE_COUNT_PROPERTY));
      checkBillingAddress(_ctx, corporateCustomer.getProperty(CorporateCustomer.BILLING_ADDRESS_PROPERTY), newLocation(_parentLocation, CorporateCustomer.BILLING_ADDRESS_PROPERTY));
      checkContactEmail(_ctx, corporateCustomer.getProperty(CorporateCustomer.CONTACT_EMAIL_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CONTACT_EMAIL_PROPERTY));
      checkContactPhone(_ctx, corporateCustomer.getProperty(CorporateCustomer.CONTACT_PHONE_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CONTACT_PHONE_PROPERTY));
      checkCustomerType(_ctx, corporateCustomer.getProperty(CorporateCustomer.CUSTOMER_TYPE_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CUSTOMER_TYPE_PROPERTY));
      checkCreatedAt(_ctx, corporateCustomer.getProperty(CorporateCustomer.CREATED_AT_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, corporateCustomer.getProperty(CorporateCustomer.UPDATED_AT_PROPERTY), newLocation(_parentLocation, CorporateCustomer.UPDATED_AT_PROPERTY));
      for(int i = 0; corporateCustomer.getCustomerContactList() != null && i < corporateCustomer.getCustomerContactList().size(); i++){
         CustomerContact customerContact = corporateCustomer.getCustomerContactList().get(i);
         new CustomerContactChecker().checkAndFix(_ctx, customerContact, newLocation(_parentLocation, CorporateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, i));
      }
      for(int i = 0; corporateCustomer.getServiceQuoteList() != null && i < corporateCustomer.getServiceQuoteList().size(); i++){
         ServiceQuote serviceQuote = corporateCustomer.getServiceQuoteList().get(i);
         new ServiceQuoteChecker().checkAndFix(_ctx, serviceQuote, newLocation(_parentLocation, CorporateCustomer.SERVICE_QUOTE_LIST_PROPERTY, i));
      }
      for(int i = 0; corporateCustomer.getServiceContractList() != null && i < corporateCustomer.getServiceContractList().size(); i++){
         ServiceContract serviceContract = corporateCustomer.getServiceContractList().get(i);
         new ServiceContractChecker().checkAndFix(_ctx, serviceContract, newLocation(_parentLocation, CorporateCustomer.SERVICE_CONTRACT_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkRegistrationNumber(UserContext _ctx, String registrationNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, registrationNumber);
    if((registrationNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, registrationNumber);

    }
    public void checkIndustry(UserContext _ctx, String industry, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, industry);
    if((industry == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, industry);

    }
    public void checkEmployeeCount(UserContext _ctx, Integer employeeCount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, employeeCount);
    if((employeeCount == null)){
        return;
    }
    }
    public void checkBillingAddress(UserContext _ctx, String billingAddress, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, billingAddress);
    if((billingAddress == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, billingAddress);

    }
    public void checkContactEmail(UserContext _ctx, String contactEmail, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, contactEmail);
    if((contactEmail == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, contactEmail);

    }
    public void checkContactPhone(UserContext _ctx, String contactPhone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, contactPhone);
    if((contactPhone == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, contactPhone);

    }
    public void checkCustomerType(UserContext _ctx, String customerType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customerType);
    if((customerType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, customerType);

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