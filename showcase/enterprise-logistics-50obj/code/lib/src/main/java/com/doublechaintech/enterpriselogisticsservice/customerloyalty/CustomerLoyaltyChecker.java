package com.doublechaintech.enterpriselogisticsservice.customerloyalty;

import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

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
        if(customerLoyalty.getCreatedAt() == null){
           customerLoyalty.updateCreatedAt(java.time.LocalDateTime.now());
        }if(customerLoyalty.getUpdatedAt() == null){
           customerLoyalty.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(customerLoyalty.updateItem()){
        customerLoyalty.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkPoints(_ctx, customerLoyalty.getProperty(CustomerLoyalty.POINTS_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.POINTS_PROPERTY));
      checkTier(_ctx, customerLoyalty.getProperty(CustomerLoyalty.TIER_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.TIER_PROPERTY));
      checkPrivateCustomer(_ctx, customerLoyalty.getProperty(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY));
      checkCreatedAt(_ctx, customerLoyalty.getProperty(CustomerLoyalty.CREATED_AT_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, customerLoyalty.getProperty(CustomerLoyalty.UPDATED_AT_PROPERTY), newLocation(_parentLocation, CustomerLoyalty.UPDATED_AT_PROPERTY));
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