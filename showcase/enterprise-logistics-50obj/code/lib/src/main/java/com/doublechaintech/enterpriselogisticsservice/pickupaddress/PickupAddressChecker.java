package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
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
        if(pickupAddress.getCreatedTime() == null){
           pickupAddress.updateCreatedTime(java.time.LocalDateTime.now());
        }if(pickupAddress.getUpdatedTime() == null){
           pickupAddress.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(pickupAddress.updateItem()){
        pickupAddress.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkAddressLine1(_ctx, pickupAddress.getProperty(PickupAddress.ADDRESS_LINE1_PROPERTY), newLocation(_parentLocation, PickupAddress.ADDRESS_LINE1_PROPERTY));
      checkAddressLine2(_ctx, pickupAddress.getProperty(PickupAddress.ADDRESS_LINE2_PROPERTY), newLocation(_parentLocation, PickupAddress.ADDRESS_LINE2_PROPERTY));
      checkCity(_ctx, pickupAddress.getProperty(PickupAddress.CITY_PROPERTY), newLocation(_parentLocation, PickupAddress.CITY_PROPERTY));
      checkStateProvince(_ctx, pickupAddress.getProperty(PickupAddress.STATE_PROVINCE_PROPERTY), newLocation(_parentLocation, PickupAddress.STATE_PROVINCE_PROPERTY));
      checkPostalCode(_ctx, pickupAddress.getProperty(PickupAddress.POSTAL_CODE_PROPERTY), newLocation(_parentLocation, PickupAddress.POSTAL_CODE_PROPERTY));
      checkCountry(_ctx, pickupAddress.getProperty(PickupAddress.COUNTRY_PROPERTY), newLocation(_parentLocation, PickupAddress.COUNTRY_PROPERTY));
      checkLatitude(_ctx, pickupAddress.getProperty(PickupAddress.LATITUDE_PROPERTY), newLocation(_parentLocation, PickupAddress.LATITUDE_PROPERTY));
      checkLongitude(_ctx, pickupAddress.getProperty(PickupAddress.LONGITUDE_PROPERTY), newLocation(_parentLocation, PickupAddress.LONGITUDE_PROPERTY));
      checkCreatedTime(_ctx, pickupAddress.getProperty(PickupAddress.CREATED_TIME_PROPERTY), newLocation(_parentLocation, PickupAddress.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, pickupAddress.getProperty(PickupAddress.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, PickupAddress.UPDATED_TIME_PROPERTY));
      for(int i = 0; pickupAddress.getMovingOrderListAsPickupAddress() != null && i < pickupAddress.getMovingOrderListAsPickupAddress().size(); i++){
         MovingOrder movingOrderAsPickupAddress = pickupAddress.getMovingOrderListAsPickupAddress().get(i);
         new MovingOrderChecker().checkAndFix(_ctx, movingOrderAsPickupAddress, newLocation(_parentLocation, PickupAddress.MOVING_ORDER_LIST_AS_PICKUP_ADDRESS_PROPERTY, i));
      }
      for(int i = 0; pickupAddress.getMovingOrderListAsDeliveryAddress() != null && i < pickupAddress.getMovingOrderListAsDeliveryAddress().size(); i++){
         MovingOrder movingOrderAsDeliveryAddress = pickupAddress.getMovingOrderListAsDeliveryAddress().get(i);
         new MovingOrderChecker().checkAndFix(_ctx, movingOrderAsDeliveryAddress, newLocation(_parentLocation, PickupAddress.MOVING_ORDER_LIST_AS_DELIVERY_ADDRESS_PROPERTY, i));
      }
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
    public void checkStateProvince(UserContext _ctx, String stateProvince, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, stateProvince);
    if((stateProvince == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, stateProvince);

    }
    public void checkPostalCode(UserContext _ctx, String postalCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, postalCode);
    if((postalCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, postalCode);

    }
    public void checkCountry(UserContext _ctx, String country, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, country);
    if((country == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, country);

    }
    public void checkLatitude(UserContext _ctx, BigDecimal latitude, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, latitude);
    if((latitude == null)){
        return;
    }
    }
    public void checkLongitude(UserContext _ctx, String longitude, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, longitude);
    if((longitude == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, longitude);

    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}