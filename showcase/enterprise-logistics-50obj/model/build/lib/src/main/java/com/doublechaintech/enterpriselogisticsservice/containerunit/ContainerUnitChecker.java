package com.doublechaintech.enterpriselogisticsservice.containerunit;

import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class ContainerUnitChecker implements Checker<ContainerUnit>{

    public String type(){
        return ContainerUnit.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, ContainerUnit containerUnit, ObjectLocation _parentLocation){
        if(needCheck(_ctx, containerUnit)){
            markAsChecked(_ctx, containerUnit);
            doCheck(_ctx, containerUnit, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, ContainerUnit containerUnit, ObjectLocation _parentLocation){
      if((containerUnit == null)){
         return;
      }
      if(containerUnit.newItem()){
        if(containerUnit.getCreateTime() == null){
           containerUnit.updateCreateTime(java.time.LocalDateTime.now());
        }if(containerUnit.getUpdateTime() == null){
           containerUnit.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(containerUnit.updateItem()){
        containerUnit.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkStorageContainer(_ctx, containerUnit.getProperty(ContainerUnit.STORAGE_CONTAINER_PROPERTY), newLocation(_parentLocation, ContainerUnit.STORAGE_CONTAINER_PROPERTY));
      checkUnitType(_ctx, containerUnit.getProperty(ContainerUnit.UNIT_TYPE_PROPERTY), newLocation(_parentLocation, ContainerUnit.UNIT_TYPE_PROPERTY));
      checkQuantity(_ctx, containerUnit.getProperty(ContainerUnit.QUANTITY_PROPERTY), newLocation(_parentLocation, ContainerUnit.QUANTITY_PROPERTY));
      checkCreateTime(_ctx, containerUnit.getProperty(ContainerUnit.CREATE_TIME_PROPERTY), newLocation(_parentLocation, ContainerUnit.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, containerUnit.getProperty(ContainerUnit.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, ContainerUnit.UPDATE_TIME_PROPERTY));
    }

    public void checkStorageContainer(UserContext _ctx, StorageContainer storageContainer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, storageContainer);
    if((storageContainer == null)){
        return;
    }
    new StorageContainerChecker().checkAndFix(_ctx, storageContainer, _parentLocation);
    }
    public void checkUnitType(UserContext _ctx, String unitType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, unitType);
    if((unitType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, unitType);

    }
    public void checkQuantity(UserContext _ctx, Integer quantity, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, quantity);
    if((quantity == null)){
        return;
    }
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