package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactChecker;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyChecker;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewChecker;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

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
      }else if(corporateCustomer.updateItem()){
      }
      checkName(_ctx, corporateCustomer.getProperty(CorporateCustomer.NAME_PROPERTY), newLocation(_parentLocation, CorporateCustomer.NAME_PROPERTY));
      checkContactPerson(_ctx, corporateCustomer.getProperty(CorporateCustomer.CONTACT_PERSON_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CONTACT_PERSON_PROPERTY));
      checkPhone(_ctx, corporateCustomer.getProperty(CorporateCustomer.PHONE_PROPERTY), newLocation(_parentLocation, CorporateCustomer.PHONE_PROPERTY));
      checkEmail(_ctx, corporateCustomer.getProperty(CorporateCustomer.EMAIL_PROPERTY), newLocation(_parentLocation, CorporateCustomer.EMAIL_PROPERTY));
      checkAddress(_ctx, corporateCustomer.getProperty(CorporateCustomer.ADDRESS_PROPERTY), newLocation(_parentLocation, CorporateCustomer.ADDRESS_PROPERTY));
      checkCity(_ctx, corporateCustomer.getProperty(CorporateCustomer.CITY_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CITY_PROPERTY));
      checkCountry(_ctx, corporateCustomer.getProperty(CorporateCustomer.COUNTRY_PROPERTY), newLocation(_parentLocation, CorporateCustomer.COUNTRY_PROPERTY));
      checkTaxId(_ctx, corporateCustomer.getProperty(CorporateCustomer.TAX_ID_PROPERTY), newLocation(_parentLocation, CorporateCustomer.TAX_ID_PROPERTY));
      checkCustomerType(_ctx, corporateCustomer.getProperty(CorporateCustomer.CUSTOMER_TYPE_PROPERTY), newLocation(_parentLocation, CorporateCustomer.CUSTOMER_TYPE_PROPERTY));
      for(int i = 0; corporateCustomer.getCustomerContactList() != null && i < corporateCustomer.getCustomerContactList().size(); i++){
         CustomerContact customerContact = corporateCustomer.getCustomerContactList().get(i);
         new CustomerContactChecker().checkAndFix(_ctx, customerContact, newLocation(_parentLocation, CorporateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, i));
      }
      for(int i = 0; corporateCustomer.getServiceQuoteList() != null && i < corporateCustomer.getServiceQuoteList().size(); i++){
         ServiceQuote serviceQuote = corporateCustomer.getServiceQuoteList().get(i);
         new ServiceQuoteChecker().checkAndFix(_ctx, serviceQuote, newLocation(_parentLocation, CorporateCustomer.SERVICE_QUOTE_LIST_PROPERTY, i));
      }
      for(int i = 0; corporateCustomer.getFeedbackReviewList() != null && i < corporateCustomer.getFeedbackReviewList().size(); i++){
         FeedbackReview feedbackReview = corporateCustomer.getFeedbackReviewList().get(i);
         new FeedbackReviewChecker().checkAndFix(_ctx, feedbackReview, newLocation(_parentLocation, CorporateCustomer.FEEDBACK_REVIEW_LIST_PROPERTY, i));
      }
      for(int i = 0; corporateCustomer.getCustomerLoyaltyList() != null && i < corporateCustomer.getCustomerLoyaltyList().size(); i++){
         CustomerLoyalty customerLoyalty = corporateCustomer.getCustomerLoyaltyList().get(i);
         new CustomerLoyaltyChecker().checkAndFix(_ctx, customerLoyalty, newLocation(_parentLocation, CorporateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkContactPerson(UserContext _ctx, String contactPerson, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, contactPerson);
    if((contactPerson == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, contactPerson);

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
    public void checkAddress(UserContext _ctx, String address, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, address);
    if((address == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, address);

    }
    public void checkCity(UserContext _ctx, String city, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, city);
    if((city == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, city);

    }
    public void checkCountry(UserContext _ctx, String country, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, country);
    if((country == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, country);

    }
    public void checkTaxId(UserContext _ctx, String taxId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, taxId);
    if((taxId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, taxId);

    }
    public void checkCustomerType(UserContext _ctx, String customerType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customerType);
    if((customerType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, customerType);

    }
}