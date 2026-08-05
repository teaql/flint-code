package com.doublechaintech.enterpriselogisticsservice.pallet;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class PalletChecker implements Checker<Pallet>{

    public String type(){
        return Pallet.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Pallet pallet, ObjectLocation _parentLocation){
        if(needCheck(_ctx, pallet)){
            markAsChecked(_ctx, pallet);
            doCheck(_ctx, pallet, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Pallet pallet, ObjectLocation _parentLocation){
      if((pallet == null)){
         return;
      }
      if(pallet.newItem()){
        if(pallet.getCreateTime() == null){
           pallet.updateCreateTime(java.time.LocalDateTime.now());
        }if(pallet.getUpdateTime() == null){
           pallet.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(pallet.updateItem()){
        pallet.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkWarehouse(_ctx, pallet.getProperty(Pallet.WAREHOUSE_PROPERTY), newLocation(_parentLocation, Pallet.WAREHOUSE_PROPERTY));
      checkPalletId(_ctx, pallet.getProperty(Pallet.PALLET_ID_PROPERTY), newLocation(_parentLocation, Pallet.PALLET_ID_PROPERTY));
      checkStatus(_ctx, pallet.getProperty(Pallet.STATUS_PROPERTY), newLocation(_parentLocation, Pallet.STATUS_PROPERTY));
      checkCreateTime(_ctx, pallet.getProperty(Pallet.CREATE_TIME_PROPERTY), newLocation(_parentLocation, Pallet.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, pallet.getProperty(Pallet.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, Pallet.UPDATE_TIME_PROPERTY));
    }

    public void checkWarehouse(UserContext _ctx, Warehouse warehouse, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, warehouse);
    if((warehouse == null)){
        return;
    }
    new WarehouseChecker().checkAndFix(_ctx, warehouse, _parentLocation);
    }
    public void checkPalletId(UserContext _ctx, String palletId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, palletId);
    if((palletId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, palletId);

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