package com.doublechaintech.enterpriselogisticsservice.storagecontainer;

import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit;
import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitChecker;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class StorageContainerChecker implements Checker<StorageContainer>{

    public String type(){
        return StorageContainer.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, StorageContainer storageContainer, ObjectLocation _parentLocation){
        if(needCheck(_ctx, storageContainer)){
            markAsChecked(_ctx, storageContainer);
            doCheck(_ctx, storageContainer, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, StorageContainer storageContainer, ObjectLocation _parentLocation){
      if((storageContainer == null)){
         return;
      }
      if(storageContainer.newItem()){
        if(storageContainer.getCreateTime() == null){
           storageContainer.updateCreateTime(java.time.LocalDateTime.now());
        }if(storageContainer.getUpdateTime() == null){
           storageContainer.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(storageContainer.updateItem()){
        storageContainer.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkContainerId(_ctx, storageContainer.getProperty(StorageContainer.CONTAINER_ID_PROPERTY), newLocation(_parentLocation, StorageContainer.CONTAINER_ID_PROPERTY));
      checkWarehouse(_ctx, storageContainer.getProperty(StorageContainer.WAREHOUSE_PROPERTY), newLocation(_parentLocation, StorageContainer.WAREHOUSE_PROPERTY));
      checkStatus(_ctx, storageContainer.getProperty(StorageContainer.STATUS_PROPERTY), newLocation(_parentLocation, StorageContainer.STATUS_PROPERTY));
      checkCreateTime(_ctx, storageContainer.getProperty(StorageContainer.CREATE_TIME_PROPERTY), newLocation(_parentLocation, StorageContainer.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, storageContainer.getProperty(StorageContainer.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, StorageContainer.UPDATE_TIME_PROPERTY));
      for(int i = 0; storageContainer.getContainerUnitList() != null && i < storageContainer.getContainerUnitList().size(); i++){
         ContainerUnit containerUnit = storageContainer.getContainerUnitList().get(i);
         new ContainerUnitChecker().checkAndFix(_ctx, containerUnit, newLocation(_parentLocation, StorageContainer.CONTAINER_UNIT_LIST_PROPERTY, i));
      }
    }

    public void checkContainerId(UserContext _ctx, String containerId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, containerId);
    if((containerId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, containerId);

    }
    public void checkWarehouse(UserContext _ctx, Warehouse warehouse, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, warehouse);
    if((warehouse == null)){
        return;
    }
    new WarehouseChecker().checkAndFix(_ctx, warehouse, _parentLocation);
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