package com.doublechaintech.enterpriselogisticsservice.inventorycheck;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class InventoryCheckChecker implements Checker<InventoryCheck>{

    public String type(){
        return InventoryCheck.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, InventoryCheck inventoryCheck, ObjectLocation _parentLocation){
        if(needCheck(_ctx, inventoryCheck)){
            markAsChecked(_ctx, inventoryCheck);
            doCheck(_ctx, inventoryCheck, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, InventoryCheck inventoryCheck, ObjectLocation _parentLocation){
      if((inventoryCheck == null)){
         return;
      }
      if(inventoryCheck.newItem()){
        if(inventoryCheck.getCreateTime() == null){
           inventoryCheck.updateCreateTime(java.time.LocalDateTime.now());
        }if(inventoryCheck.getUpdateTime() == null){
           inventoryCheck.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(inventoryCheck.updateItem()){
        inventoryCheck.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkWarehouse(_ctx, inventoryCheck.getProperty(InventoryCheck.WAREHOUSE_PROPERTY), newLocation(_parentLocation, InventoryCheck.WAREHOUSE_PROPERTY));
      checkCheckDate(_ctx, inventoryCheck.getProperty(InventoryCheck.CHECK_DATE_PROPERTY), newLocation(_parentLocation, InventoryCheck.CHECK_DATE_PROPERTY));
      checkTotalItems(_ctx, inventoryCheck.getProperty(InventoryCheck.TOTAL_ITEMS_PROPERTY), newLocation(_parentLocation, InventoryCheck.TOTAL_ITEMS_PROPERTY));
      checkDiscrepancies(_ctx, inventoryCheck.getProperty(InventoryCheck.DISCREPANCIES_PROPERTY), newLocation(_parentLocation, InventoryCheck.DISCREPANCIES_PROPERTY));
      checkStatus(_ctx, inventoryCheck.getProperty(InventoryCheck.STATUS_PROPERTY), newLocation(_parentLocation, InventoryCheck.STATUS_PROPERTY));
      checkCreateTime(_ctx, inventoryCheck.getProperty(InventoryCheck.CREATE_TIME_PROPERTY), newLocation(_parentLocation, InventoryCheck.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, inventoryCheck.getProperty(InventoryCheck.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, InventoryCheck.UPDATE_TIME_PROPERTY));
    }

    public void checkWarehouse(UserContext _ctx, Warehouse warehouse, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, warehouse);
    if((warehouse == null)){
        return;
    }
    new WarehouseChecker().checkAndFix(_ctx, warehouse, _parentLocation);
    }
    public void checkCheckDate(UserContext _ctx, String checkDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, checkDate);
    if((checkDate == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, checkDate);

    }
    public void checkTotalItems(UserContext _ctx, Integer totalItems, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalItems);
    if((totalItems == null)){
        return;
    }
    }
    public void checkDiscrepancies(UserContext _ctx, Integer discrepancies, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, discrepancies);
    if((discrepancies == null)){
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