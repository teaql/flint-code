package com.doublechaintech.enterpriselogisticsservice.servicequote;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ServiceQuoteChecker implements Checker<ServiceQuote>{

    public String type(){
        return ServiceQuote.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, ServiceQuote serviceQuote, ObjectLocation _parentLocation){
        if(needCheck(_ctx, serviceQuote)){
            markAsChecked(_ctx, serviceQuote);
            doCheck(_ctx, serviceQuote, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, ServiceQuote serviceQuote, ObjectLocation _parentLocation){
      if((serviceQuote == null)){
         return;
      }
      if(serviceQuote.newItem()){
        if(serviceQuote.getCreatedAt() == null){
           serviceQuote.updateCreatedAt(java.time.LocalDateTime.now());
        }if(serviceQuote.getUpdatedAt() == null){
           serviceQuote.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(serviceQuote.updateItem()){
        serviceQuote.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkQuoteNumber(_ctx, serviceQuote.getProperty(ServiceQuote.QUOTE_NUMBER_PROPERTY), newLocation(_parentLocation, ServiceQuote.QUOTE_NUMBER_PROPERTY));
      checkEstimatedCost(_ctx, serviceQuote.getProperty(ServiceQuote.ESTIMATED_COST_PROPERTY), newLocation(_parentLocation, ServiceQuote.ESTIMATED_COST_PROPERTY));
      checkCurrency(_ctx, serviceQuote.getProperty(ServiceQuote.CURRENCY_PROPERTY), newLocation(_parentLocation, ServiceQuote.CURRENCY_PROPERTY));
      checkValidUntil(_ctx, serviceQuote.getProperty(ServiceQuote.VALID_UNTIL_PROPERTY), newLocation(_parentLocation, ServiceQuote.VALID_UNTIL_PROPERTY));
      checkStatus(_ctx, serviceQuote.getProperty(ServiceQuote.STATUS_PROPERTY), newLocation(_parentLocation, ServiceQuote.STATUS_PROPERTY));
      checkPrivateCustomer(_ctx, serviceQuote.getProperty(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, ServiceQuote.PRIVATE_CUSTOMER_PROPERTY));
      checkCorporateCustomer(_ctx, serviceQuote.getProperty(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, ServiceQuote.CORPORATE_CUSTOMER_PROPERTY));
      checkCreatedAt(_ctx, serviceQuote.getProperty(ServiceQuote.CREATED_AT_PROPERTY), newLocation(_parentLocation, ServiceQuote.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, serviceQuote.getProperty(ServiceQuote.UPDATED_AT_PROPERTY), newLocation(_parentLocation, ServiceQuote.UPDATED_AT_PROPERTY));
    }

    public void checkQuoteNumber(UserContext _ctx, String quoteNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, quoteNumber);
    if((quoteNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, quoteNumber);

    }
    public void checkEstimatedCost(UserContext _ctx, BigDecimal estimatedCost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, estimatedCost);
    if((estimatedCost == null)){
        return;
    }
    }
    public void checkCurrency(UserContext _ctx, String currency, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, currency);
    if((currency == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, currency);

    }
    public void checkValidUntil(UserContext _ctx, LocalDate validUntil, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, validUntil);
    if((validUntil == null)){
        return;
    }
    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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