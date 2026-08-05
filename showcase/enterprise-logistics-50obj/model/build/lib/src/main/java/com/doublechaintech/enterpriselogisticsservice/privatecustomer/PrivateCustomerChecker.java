package com.doublechaintech.enterpriselogisticsservice.privatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactChecker;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyChecker;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewChecker;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

public class PrivateCustomerChecker implements Checker<PrivateCustomer>{

    public String type(){
        return PrivateCustomer.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, PrivateCustomer privateCustomer, ObjectLocation _parentLocation){
        if(needCheck(_ctx, privateCustomer)){
            markAsChecked(_ctx, privateCustomer);
            doCheck(_ctx, privateCustomer, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, PrivateCustomer privateCustomer, ObjectLocation _parentLocation){
      if((privateCustomer == null)){
         return;
      }
      if(privateCustomer.newItem()){
      }else if(privateCustomer.updateItem()){
      }
      checkName(_ctx, privateCustomer.getProperty(PrivateCustomer.NAME_PROPERTY), newLocation(_parentLocation, PrivateCustomer.NAME_PROPERTY));
      checkPhone(_ctx, privateCustomer.getProperty(PrivateCustomer.PHONE_PROPERTY), newLocation(_parentLocation, PrivateCustomer.PHONE_PROPERTY));
      checkEmail(_ctx, privateCustomer.getProperty(PrivateCustomer.EMAIL_PROPERTY), newLocation(_parentLocation, PrivateCustomer.EMAIL_PROPERTY));
      checkAddress(_ctx, privateCustomer.getProperty(PrivateCustomer.ADDRESS_PROPERTY), newLocation(_parentLocation, PrivateCustomer.ADDRESS_PROPERTY));
      checkCity(_ctx, privateCustomer.getProperty(PrivateCustomer.CITY_PROPERTY), newLocation(_parentLocation, PrivateCustomer.CITY_PROPERTY));
      checkCountry(_ctx, privateCustomer.getProperty(PrivateCustomer.COUNTRY_PROPERTY), newLocation(_parentLocation, PrivateCustomer.COUNTRY_PROPERTY));
      checkCustomerType(_ctx, privateCustomer.getProperty(PrivateCustomer.CUSTOMER_TYPE_PROPERTY), newLocation(_parentLocation, PrivateCustomer.CUSTOMER_TYPE_PROPERTY));
      for(int i = 0; privateCustomer.getMovingOrderList() != null && i < privateCustomer.getMovingOrderList().size(); i++){
         MovingOrder movingOrder = privateCustomer.getMovingOrderList().get(i);
         new MovingOrderChecker().checkAndFix(_ctx, movingOrder, newLocation(_parentLocation, PrivateCustomer.MOVING_ORDER_LIST_PROPERTY, i));
      }
      for(int i = 0; privateCustomer.getCustomerContactList() != null && i < privateCustomer.getCustomerContactList().size(); i++){
         CustomerContact customerContact = privateCustomer.getCustomerContactList().get(i);
         new CustomerContactChecker().checkAndFix(_ctx, customerContact, newLocation(_parentLocation, PrivateCustomer.CUSTOMER_CONTACT_LIST_PROPERTY, i));
      }
      for(int i = 0; privateCustomer.getServiceQuoteList() != null && i < privateCustomer.getServiceQuoteList().size(); i++){
         ServiceQuote serviceQuote = privateCustomer.getServiceQuoteList().get(i);
         new ServiceQuoteChecker().checkAndFix(_ctx, serviceQuote, newLocation(_parentLocation, PrivateCustomer.SERVICE_QUOTE_LIST_PROPERTY, i));
      }
      for(int i = 0; privateCustomer.getFeedbackReviewList() != null && i < privateCustomer.getFeedbackReviewList().size(); i++){
         FeedbackReview feedbackReview = privateCustomer.getFeedbackReviewList().get(i);
         new FeedbackReviewChecker().checkAndFix(_ctx, feedbackReview, newLocation(_parentLocation, PrivateCustomer.FEEDBACK_REVIEW_LIST_PROPERTY, i));
      }
      for(int i = 0; privateCustomer.getCustomerLoyaltyList() != null && i < privateCustomer.getCustomerLoyaltyList().size(); i++){
         CustomerLoyalty customerLoyalty = privateCustomer.getCustomerLoyaltyList().get(i);
         new CustomerLoyaltyChecker().checkAndFix(_ctx, customerLoyalty, newLocation(_parentLocation, PrivateCustomer.CUSTOMER_LOYALTY_LIST_PROPERTY, i));
      }
      for(int i = 0; privateCustomer.getInvoiceList() != null && i < privateCustomer.getInvoiceList().size(); i++){
         Invoice invoice = privateCustomer.getInvoiceList().get(i);
         new InvoiceChecker().checkAndFix(_ctx, invoice, newLocation(_parentLocation, PrivateCustomer.INVOICE_LIST_PROPERTY, i));
      }
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
    public void checkCustomerType(UserContext _ctx, String customerType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customerType);
    if((customerType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, customerType);

    }
}