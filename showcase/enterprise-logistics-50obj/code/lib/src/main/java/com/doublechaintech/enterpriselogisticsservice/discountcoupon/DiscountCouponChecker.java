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
        }if(discountCoupon.getUpdatedTime() == null){
           discountCoupon.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(discountCoupon.updateItem()){
        discountCoupon.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkCode(_ctx, discountCoupon.getProperty(DiscountCoupon.CODE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.CODE_PROPERTY));
      checkDescription(_ctx, discountCoupon.getProperty(DiscountCoupon.DESCRIPTION_PROPERTY), newLocation(_parentLocation, DiscountCoupon.DESCRIPTION_PROPERTY));
      checkDiscountPercentage(_ctx, discountCoupon.getProperty(DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.DISCOUNT_PERCENTAGE_PROPERTY));
      checkMinOrderAmount(_ctx, discountCoupon.getProperty(DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY), newLocation(_parentLocation, DiscountCoupon.MIN_ORDER_AMOUNT_PROPERTY));
      checkMaxDiscountAmount(_ctx, discountCoupon.getProperty(DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY), newLocation(_parentLocation, DiscountCoupon.MAX_DISCOUNT_AMOUNT_PROPERTY));
      checkUsageLimit(_ctx, discountCoupon.getProperty(DiscountCoupon.USAGE_LIMIT_PROPERTY), newLocation(_parentLocation, DiscountCoupon.USAGE_LIMIT_PROPERTY));
      checkUsedCount(_ctx, discountCoupon.getProperty(DiscountCoupon.USED_COUNT_PROPERTY), newLocation(_parentLocation, DiscountCoupon.USED_COUNT_PROPERTY));
      checkStartDate(_ctx, discountCoupon.getProperty(DiscountCoupon.START_DATE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.START_DATE_PROPERTY));
      checkEndDate(_ctx, discountCoupon.getProperty(DiscountCoupon.END_DATE_PROPERTY), newLocation(_parentLocation, DiscountCoupon.END_DATE_PROPERTY));
      checkStatus(_ctx, discountCoupon.getProperty(DiscountCoupon.STATUS_PROPERTY), newLocation(_parentLocation, DiscountCoupon.STATUS_PROPERTY));
      checkCreatedTime(_ctx, discountCoupon.getProperty(DiscountCoupon.CREATED_TIME_PROPERTY), newLocation(_parentLocation, DiscountCoupon.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, discountCoupon.getProperty(DiscountCoupon.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, DiscountCoupon.UPDATED_TIME_PROPERTY));
    }

    public void checkCode(UserContext _ctx, String code, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, code);
    if((code == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, code);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkDiscountPercentage(UserContext _ctx, BigDecimal discountPercentage, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, discountPercentage);
    if((discountPercentage == null)){
        return;
    }
    }
    public void checkMinOrderAmount(UserContext _ctx, BigDecimal minOrderAmount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, minOrderAmount);
    if((minOrderAmount == null)){
        return;
    }
    }
    public void checkMaxDiscountAmount(UserContext _ctx, BigDecimal maxDiscountAmount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, maxDiscountAmount);
    if((maxDiscountAmount == null)){
        return;
    }
    }
    public void checkUsageLimit(UserContext _ctx, Integer usageLimit, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, usageLimit);
    if((usageLimit == null)){
        return;
    }
    }
    public void checkUsedCount(UserContext _ctx, Integer usedCount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, usedCount);
    if((usedCount == null)){
        return;
    }
    }
    public void checkStartDate(UserContext _ctx, LocalDate startDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, startDate);
    if((startDate == null)){
        return;
    }
    }
    public void checkEndDate(UserContext _ctx, LocalDate endDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, endDate);
    if((endDate == null)){
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
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}