package com.doublechaintech.enterpriselogisticsservice.warehouse;

import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck;
import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckChecker;
import com.doublechaintech.enterpriselogisticsservice.pallet.Pallet;
import com.doublechaintech.enterpriselogisticsservice.pallet.PalletChecker;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerChecker;
import com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRoute;
import com.doublechaintech.enterpriselogisticsservice.transitroute.TransitRouteChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WarehouseChecker implements Checker<Warehouse>{

    public String type(){
        return Warehouse.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Warehouse warehouse, ObjectLocation _parentLocation){
        if(needCheck(_ctx, warehouse)){
            markAsChecked(_ctx, warehouse);
            doCheck(_ctx, warehouse, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Warehouse warehouse, ObjectLocation _parentLocation){
      if((warehouse == null)){
         return;
      }
      if(warehouse.newItem()){
        if(warehouse.getCreateTime() == null){
           warehouse.updateCreateTime(java.time.LocalDateTime.now());
        }if(warehouse.getUpdateTime() == null){
           warehouse.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(warehouse.updateItem()){
        warehouse.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkName(_ctx, warehouse.getProperty(Warehouse.NAME_PROPERTY), newLocation(_parentLocation, Warehouse.NAME_PROPERTY));
      checkCode(_ctx, warehouse.getProperty(Warehouse.CODE_PROPERTY), newLocation(_parentLocation, Warehouse.CODE_PROPERTY));
      checkAddress(_ctx, warehouse.getProperty(Warehouse.ADDRESS_PROPERTY), newLocation(_parentLocation, Warehouse.ADDRESS_PROPERTY));
      checkCity(_ctx, warehouse.getProperty(Warehouse.CITY_PROPERTY), newLocation(_parentLocation, Warehouse.CITY_PROPERTY));
      checkCountry(_ctx, warehouse.getProperty(Warehouse.COUNTRY_PROPERTY), newLocation(_parentLocation, Warehouse.COUNTRY_PROPERTY));
      checkCapacity(_ctx, warehouse.getProperty(Warehouse.CAPACITY_PROPERTY), newLocation(_parentLocation, Warehouse.CAPACITY_PROPERTY));
      checkStatus(_ctx, warehouse.getProperty(Warehouse.STATUS_PROPERTY), newLocation(_parentLocation, Warehouse.STATUS_PROPERTY));
      checkCreateTime(_ctx, warehouse.getProperty(Warehouse.CREATE_TIME_PROPERTY), newLocation(_parentLocation, Warehouse.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, warehouse.getProperty(Warehouse.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, Warehouse.UPDATE_TIME_PROPERTY));
      for(int i = 0; warehouse.getTransitRouteListAsOriginWarehouse() != null && i < warehouse.getTransitRouteListAsOriginWarehouse().size(); i++){
         TransitRoute transitRouteAsOriginWarehouse = warehouse.getTransitRouteListAsOriginWarehouse().get(i);
         new TransitRouteChecker().checkAndFix(_ctx, transitRouteAsOriginWarehouse, newLocation(_parentLocation, Warehouse.TRANSIT_ROUTE_LIST_AS_ORIGIN_WAREHOUSE_PROPERTY, i));
      }
      for(int i = 0; warehouse.getTransitRouteListAsDestinationWarehouse() != null && i < warehouse.getTransitRouteListAsDestinationWarehouse().size(); i++){
         TransitRoute transitRouteAsDestinationWarehouse = warehouse.getTransitRouteListAsDestinationWarehouse().get(i);
         new TransitRouteChecker().checkAndFix(_ctx, transitRouteAsDestinationWarehouse, newLocation(_parentLocation, Warehouse.TRANSIT_ROUTE_LIST_AS_DESTINATION_WAREHOUSE_PROPERTY, i));
      }
      for(int i = 0; warehouse.getStorageContainerList() != null && i < warehouse.getStorageContainerList().size(); i++){
         StorageContainer storageContainer = warehouse.getStorageContainerList().get(i);
         new StorageContainerChecker().checkAndFix(_ctx, storageContainer, newLocation(_parentLocation, Warehouse.STORAGE_CONTAINER_LIST_PROPERTY, i));
      }
      for(int i = 0; warehouse.getInventoryCheckList() != null && i < warehouse.getInventoryCheckList().size(); i++){
         InventoryCheck inventoryCheck = warehouse.getInventoryCheckList().get(i);
         new InventoryCheckChecker().checkAndFix(_ctx, inventoryCheck, newLocation(_parentLocation, Warehouse.INVENTORY_CHECK_LIST_PROPERTY, i));
      }
      for(int i = 0; warehouse.getPalletList() != null && i < warehouse.getPalletList().size(); i++){
         Pallet pallet = warehouse.getPalletList().get(i);
         new PalletChecker().checkAndFix(_ctx, pallet, newLocation(_parentLocation, Warehouse.PALLET_LIST_PROPERTY, i));
      }
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkCode(UserContext _ctx, String code, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, code);
    if((code == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, code);

    }
    public void checkAddress(UserContext _ctx, String address, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, address);
    if((address == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, address);

    }
    public void checkCity(UserContext _ctx, String city, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, city);
    if((city == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, city);

    }
    public void checkCountry(UserContext _ctx, String country, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, country);
    if((country == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, country);

    }
    public void checkCapacity(UserContext _ctx, BigDecimal capacity, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, capacity);
    if((capacity == null)){
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