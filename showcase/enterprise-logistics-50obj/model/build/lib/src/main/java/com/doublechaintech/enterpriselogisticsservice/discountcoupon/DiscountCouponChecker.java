package com.doublechaintech.enterpriselogisticsservice.discountcoupon;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DiscountCouponChecker implements Checker<DiscountCoupon>{

    public String type(){
        return DiscountCoupon.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, DiscountCoupon discountCoupon, ObjectLocation _parentLocation){
        if(needCheck(_ctx, discountCoupon)){
            markAsChecked(_ctx, discountCoupon);
            doCheck(_ctx, discountCoupon, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, DiscountCoupon discountCoupon, ObjectLocation _parentLocation){
      if((discountCoupon == null)){
         return;
      }
      if(discountCoupon.newItem()){
        if(discountCoupon.getCreatedTime() == null){
           discountCoupon.updateCreatedTime(java.time.LocalDateTime.now());
        }if(discountCoupon.getUpdateTime() == null){
           discountCoupon.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(discountCoupon.updateItem()){
        discountCoupon.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkCode(_ctx, discountCoupon.getProperty(DiscountCoupon.CODE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.CODE_PROPERTY));
      checkDiscountPercentage(_ctx, discountCoupon.getProperty(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
      checkMaxUses(_ctx, discountCoupon.getProperty(DiscountCoupon.MAX_USES_PROPERTY), newLocation(_parentLocation, DiscountCoupon.MAX_USES_PROPERTY));
      checkCurrentUses(_ctx, discountCoupon.getProperty(DiscountCoupon.CURRENT_USES_PROPERTY), newLocation(_parentLocation, DiscountCoupon.CURRENT_USES_PROPERTY));
      checkExpiryDate(_ctx, discountCoupon.getProperty(DiscountCoupon.EXPIRY_DATE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.EXPIRY_DATE_PROPERTY));
      checkStatus(_ctx, discountCoupon.getProperty(DiscountCoupon.STATUS_PROPERTY), newLocation(_parentLocation, DiscountCoupon.STATUS_PROPERTY));
      checkCreatedTime(_ctx, discountCoupon.getProperty(DiscountCoupon.CREATED_TIME_PROPERTY), newLocation(_parentLocation, DiscountCoupon.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, discountCoupon.getProperty(DiscountCoupon.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, DiscountCoupon.UPDATE_TIME_PROPERTY));
    }

    public void checkCode(UserContext _ctx, String code, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, code);
    if((code == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, code);

    }
    public void checkDiscountPercentage(UserContext _ctx, BigDecimal discountPercentage, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, discountPercentage);
    if((discountPercentage == null)){
        return;
    }
    }
    public void checkMaxUses(UserContext _ctx, Integer maxUses, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, maxUses);
    if((maxUses == null)){
        return;
    }
    }
    public void checkCurrentUses(UserContext _ctx, Integer currentUses, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, currentUses);
    if((currentUses == null)){
        return;
    }
    }
    public void checkExpiryDate(UserContext _ctx, LocalDate expiryDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, expiryDate);
    if((expiryDate == null)){
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
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
    public void checkUpdateTime(UserContext _ctx, LocalDateTime updateTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updateTime);
    if((updateTime == null)){
        return;
    }
    }
}