package com.doublechaintech.enterpriselogisticsservice.cargoitem;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CargoItemChecker implements Checker<CargoItem>{

    public String type(){
        return CargoItem.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, CargoItem cargoItem, ObjectLocation _parentLocation){
        if(needCheck(_ctx, cargoItem)){
            markAsChecked(_ctx, cargoItem);
            doCheck(_ctx, cargoItem, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, CargoItem cargoItem, ObjectLocation _parentLocation){
      if((cargoItem == null)){
         return;
      }
      if(cargoItem.newItem()){
        if(cargoItem.getCreatedTime() == null){
           cargoItem.updateCreatedTime(java.time.LocalDateTime.now());
        }if(cargoItem.getUpdatedTime() == null){
           cargoItem.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(cargoItem.updateItem()){
        cargoItem.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkItemCode(_ctx, cargoItem.getProperty(CargoItem.ITEM_CODE_PROPERTY), newLocation(_parentLocation, CargoItem.ITEM_CODE_PROPERTY));
      checkDescription(_ctx, cargoItem.getProperty(CargoItem.DESCRIPTION_PROPERTY), newLocation(_parentLocation, CargoItem.DESCRIPTION_PROPERTY));
      checkWeightKg(_ctx, cargoItem.getProperty(CargoItem.WEIGHT_KG_PROPERTY), newLocation(_parentLocation, CargoItem.WEIGHT_KG_PROPERTY));
      checkVolumeM3(_ctx, cargoItem.getProperty(CargoItem.VOLUME_M3_PROPERTY), newLocation(_parentLocation, CargoItem.VOLUME_M3_PROPERTY));
      checkFragile(_ctx, cargoItem.getProperty(CargoItem.FRAGILE_PROPERTY), newLocation(_parentLocation, CargoItem.FRAGILE_PROPERTY));
      checkMovingOrder(_ctx, cargoItem.getProperty(CargoItem.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, CargoItem.MOVING_ORDER_PROPERTY));
      checkCreatedTime(_ctx, cargoItem.getProperty(CargoItem.CREATED_TIME_PROPERTY), newLocation(_parentLocation, CargoItem.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, cargoItem.getProperty(CargoItem.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, CargoItem.UPDATED_TIME_PROPERTY));
    }

    public void checkItemCode(UserContext _ctx, String itemCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, itemCode);
    if((itemCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, itemCode);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

    }
    public void checkWeightKg(UserContext _ctx, BigDecimal weightKg, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, weightKg);
    if((weightKg == null)){
        return;
    }
    }
    public void checkVolumeM3(UserContext _ctx, BigDecimal volumeM3, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, volumeM3);
    if((volumeM3 == null)){
        return;
    }
    }
    public void checkFragile(UserContext _ctx, Boolean fragile, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, fragile);
    if((fragile == null)){
        return;
    }
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
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}