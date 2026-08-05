package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class PickupAddressChecker implements Checker<PickupAddress>{

    public String type(){
        return PickupAddress.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, PickupAddress pickupAddress, ObjectLocation _parentLocation){
        if(needCheck(_ctx, pickupAddress)){
            markAsChecked(_ctx, pickupAddress);
            doCheck(_ctx, pickupAddress, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, PickupAddress pickupAddress, ObjectLocation _parentLocation){
      if((pickupAddress == null)){
         return;
      }
      if(pickupAddress.newItem()){
        if(pickupAddress.getCreateTime() == null){
           pickupAddress.updateCreateTime(java.time.LocalDateTime.now());
        }
      }else if(pickupAddress.updateItem()){
      }
      checkAddressId(_ctx, pickupAddress.getProperty(PickupAddress.ADDRESS_ID_PROPERTY), newLocation(_parentLocation, PickupAddress.ADDRESS_ID_PROPERTY));
      checkMovingOrder(_ctx, pickupAddress.getProperty(PickupAddress.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, PickupAddress.MOVING_ORDER_PROPERTY));
      checkAddressLine1(_ctx, pickupAddress.getProperty(PickupAddress.ADDRESS_LINE1_PROPERTY), newLocation(_parentLocation, PickupAddress.ADDRESS_LINE1_PROPERTY));
      checkAddressLine2(_ctx, pickupAddress.getProperty(PickupAddress.ADDRESS_LINE2_PROPERTY), newLocation(_parentLocation, PickupAddress.ADDRESS_LINE2_PROPERTY));
      checkCity(_ctx, pickupAddress.getProperty(PickupAddress.CITY_PROPERTY), newLocation(_parentLocation, PickupAddress.CITY_PROPERTY));
      checkState(_ctx, pickupAddress.getProperty(PickupAddress.STATE_PROPERTY), newLocation(_parentLocation, PickupAddress.STATE_PROPERTY));
      checkZipCode(_ctx, pickupAddress.getProperty(PickupAddress.ZIP_CODE_PROPERTY), newLocation(_parentLocation, PickupAddress.ZIP_CODE_PROPERTY));
      checkCountry(_ctx, pickupAddress.getProperty(PickupAddress.COUNTRY_PROPERTY), newLocation(_parentLocation, PickupAddress.COUNTRY_PROPERTY));
      checkContactName(_ctx, pickupAddress.getProperty(PickupAddress.CONTACT_NAME_PROPERTY), newLocation(_parentLocation, PickupAddress.CONTACT_NAME_PROPERTY));
      checkContactPhone(_ctx, pickupAddress.getProperty(PickupAddress.CONTACT_PHONE_PROPERTY), newLocation(_parentLocation, PickupAddress.CONTACT_PHONE_PROPERTY));
      checkCreateTime(_ctx, pickupAddress.getProperty(PickupAddress.CREATE_TIME_PROPERTY), newLocation(_parentLocation, PickupAddress.CREATE_TIME_PROPERTY));
    }

    public void checkAddressId(UserContext _ctx, String addressId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, addressId);
    if((addressId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, addressId);

    }
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
    }
    public void checkAddressLine1(UserContext _ctx, String addressLine1, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, addressLine1);
    if((addressLine1 == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, addressLine1);

    }
    public void checkAddressLine2(UserContext _ctx, String addressLine2, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, addressLine2);
    if((addressLine2 == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, addressLine2);

    }
    public void checkCity(UserContext _ctx, String city, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, city);
    if((city == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, city);

    }
    public void checkState(UserContext _ctx, String state, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, state);
    if((state == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, state);

    }
    public void checkZipCode(UserContext _ctx, String zipCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, zipCode);
    if((zipCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, zipCode);

    }
    public void checkCountry(UserContext _ctx, String country, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, country);
    if((country == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, country);

    }
    public void checkContactName(UserContext _ctx, String contactName, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, contactName);
    if((contactName == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, contactName);

    }
    public void checkContactPhone(UserContext _ctx, String contactPhone, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, contactPhone);
    if((contactPhone == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, contactPhone);

    }
    public void checkCreateTime(UserContext _ctx, LocalDateTime createTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createTime);
    if((createTime == null)){
        return;
    }
    }
}