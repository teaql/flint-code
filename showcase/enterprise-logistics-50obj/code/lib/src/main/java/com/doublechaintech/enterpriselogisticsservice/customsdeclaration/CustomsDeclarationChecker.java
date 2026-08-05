package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CustomsDeclarationChecker implements Checker<CustomsDeclaration>{

    public String type(){
        return CustomsDeclaration.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, CustomsDeclaration customsDeclaration, ObjectLocation _parentLocation){
        if(needCheck(_ctx, customsDeclaration)){
            markAsChecked(_ctx, customsDeclaration);
            doCheck(_ctx, customsDeclaration, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, CustomsDeclaration customsDeclaration, ObjectLocation _parentLocation){
      if((customsDeclaration == null)){
         return;
      }
      if(customsDeclaration.newItem()){
        if(customsDeclaration.getCreatedTime() == null){
           customsDeclaration.updateCreatedTime(java.time.LocalDateTime.now());
        }if(customsDeclaration.getUpdateTime() == null){
           customsDeclaration.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(customsDeclaration.updateItem()){
        customsDeclaration.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkDeclarationNumber(_ctx, customsDeclaration.getProperty(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.DECLARATION_NUMBER_PROPERTY));
      checkOriginCountry(_ctx, customsDeclaration.getProperty(CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.ORIGIN_COUNTRY_PROPERTY));
      checkDestinationCountry(_ctx, customsDeclaration.getProperty(CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.DESTINATION_COUNTRY_PROPERTY));
      checkTotalValue(_ctx, customsDeclaration.getProperty(CustomsDeclaration.TOTAL_VALUE_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.TOTAL_VALUE_PROPERTY));
      checkStatus(_ctx, customsDeclaration.getProperty(CustomsDeclaration.STATUS_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.STATUS_PROPERTY));
      checkMovingOrder(_ctx, customsDeclaration.getProperty(CustomsDeclaration.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.MOVING_ORDER_PROPERTY));
      checkCreatedTime(_ctx, customsDeclaration.getProperty(CustomsDeclaration.CREATED_TIME_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.CREATED_TIME_PROPERTY));
      checkUpdateTime(_ctx, customsDeclaration.getProperty(CustomsDeclaration.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.UPDATE_TIME_PROPERTY));
    }

    public void checkDeclarationNumber(UserContext _ctx, String declarationNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, declarationNumber);
    if((declarationNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, declarationNumber);

    }
    public void checkOriginCountry(UserContext _ctx, String originCountry, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, originCountry);
    if((originCountry == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, originCountry);

    }
    public void checkDestinationCountry(UserContext _ctx, String destinationCountry, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, destinationCountry);
    if((destinationCountry == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, destinationCountry);

    }
    public void checkTotalValue(UserContext _ctx, BigDecimal totalValue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalValue);
    if((totalValue == null)){
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
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
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