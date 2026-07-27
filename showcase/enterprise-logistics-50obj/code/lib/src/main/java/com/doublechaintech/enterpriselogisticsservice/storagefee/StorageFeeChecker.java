package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerChecker;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StorageFeeChecker implements Checker<StorageFee>{

    public String type(){
        return StorageFee.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, StorageFee storageFee, ObjectLocation _parentLocation){
        if(needCheck(_ctx, storageFee)){
            markAsChecked(_ctx, storageFee);
            doCheck(_ctx, storageFee, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, StorageFee storageFee, ObjectLocation _parentLocation){
      if((storageFee == null)){
         return;
      }
      if(storageFee.newItem()){
        if(storageFee.getCreateTime() == null){
           storageFee.updateCreateTime(java.time.LocalDateTime.now());
        }if(storageFee.getUpdateTime() == null){
           storageFee.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(storageFee.updateItem()){
        storageFee.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkWarehouse(_ctx, storageFee.getProperty(StorageFee.WAREHOUSE_PROPERTY), newLocation(_parentLocation, StorageFee.WAREHOUSE_PROPERTY));
      checkContainer(_ctx, storageFee.getProperty(StorageFee.CONTAINER_PROPERTY), newLocation(_parentLocation, StorageFee.CONTAINER_PROPERTY));
      checkAmount(_ctx, storageFee.getProperty(StorageFee.AMOUNT_PROPERTY), newLocation(_parentLocation, StorageFee.AMOUNT_PROPERTY));
      checkCurrency(_ctx, storageFee.getProperty(StorageFee.CURRENCY_PROPERTY), newLocation(_parentLocation, StorageFee.CURRENCY_PROPERTY));
      checkPeriod(_ctx, storageFee.getProperty(StorageFee.PERIOD_PROPERTY), newLocation(_parentLocation, StorageFee.PERIOD_PROPERTY));
      checkCreateTime(_ctx, storageFee.getProperty(StorageFee.CREATE_TIME_PROPERTY), newLocation(_parentLocation, StorageFee.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, storageFee.getProperty(StorageFee.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, StorageFee.UPDATE_TIME_PROPERTY));
    }

    public void checkWarehouse(UserContext _ctx, Warehouse warehouse, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, warehouse);
    if((warehouse == null)){
        return;
    }
    new WarehouseChecker().checkAndFix(_ctx, warehouse, _parentLocation);
    }
    public void checkContainer(UserContext _ctx, StorageContainer container, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, container);
    if((container == null)){
        return;
    }
    new StorageContainerChecker().checkAndFix(_ctx, container, _parentLocation);
    }
    public void checkAmount(UserContext _ctx, BigDecimal amount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, amount);
    if((amount == null)){
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
    public void checkPeriod(UserContext _ctx, String period, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, period);
    if((period == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, period);

    }
    public void checkCreateTime(UserContext _ctx, LocalDateTime createTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createTime);
    if((createTime == null)){
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