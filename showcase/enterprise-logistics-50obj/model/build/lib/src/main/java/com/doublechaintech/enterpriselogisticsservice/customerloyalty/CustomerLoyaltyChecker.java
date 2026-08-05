package com.doublechaintech.enterpriselogisticsservice.customerloyalty;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

public class CustomerLoyaltyChecker implements Checker<CustomerLoyalty>{

    public String type(){
        return CustomerLoyalty.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, CustomerLoyalty customerLoyalty, ObjectLocation _parentLocation){
        if(needCheck(_ctx, customerLoyalty)){
            markAsChecked(_ctx, customerLoyalty);
            doCheck(_ctx, customerLoyalty, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, CustomerLoyalty customerLoyalty, ObjectLocation _parentLocation){
      if((customerLoyalty == null)){
         return;
      }
      if(customerLoyalty.newItem()){
      }else if(customerLoyalty.updateItem()){
      }
      checkPoints(_ctx, customerLoyalty.getProperty(CustomerLoyalty.POINTS_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.POINTS_PROPERTY));
      checkTier(_ctx, customerLoyalty.getProperty(CustomerLoyalty.TIER_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.TIER_PROPERTY));
      checkPrivateCustomer(_ctx, customerLoyalty.getProperty(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY));
      checkCorporateCustomer(_ctx, customerLoyalty.getProperty(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY));
    }

    public void checkPoints(UserContext _ctx, Integer points, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, points);
    if((points == null)){
        return;
    }
    }
    public void checkTier(UserContext _ctx, String tier, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, tier);
    if((tier == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, tier);

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