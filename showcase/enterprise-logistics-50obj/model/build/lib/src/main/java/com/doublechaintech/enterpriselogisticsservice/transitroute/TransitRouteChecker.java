package com.doublechaintech.enterpriselogisticsservice.transitroute;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransitRouteChecker implements Checker<TransitRoute>{

    public String type(){
        return TransitRoute.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, TransitRoute transitRoute, ObjectLocation _parentLocation){
        if(needCheck(_ctx, transitRoute)){
            markAsChecked(_ctx, transitRoute);
            doCheck(_ctx, transitRoute, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, TransitRoute transitRoute, ObjectLocation _parentLocation){
      if((transitRoute == null)){
         return;
      }
      if(transitRoute.newItem()){
        if(transitRoute.getCreateTime() == null){
           transitRoute.updateCreateTime(java.time.LocalDateTime.now());
        }
      }else if(transitRoute.updateItem()){
      }
      checkRouteId(_ctx, transitRoute.getProperty(TransitRoute.ROUTE_ID_PROPERTY), newLocation(_parentLocation, TransitRoute.ROUTE_ID_PROPERTY));
      checkName(_ctx, transitRoute.getProperty(TransitRoute.NAME_PROPERTY), newLocation(_parentLocation, TransitRoute.NAME_PROPERTY));
      checkOriginWarehouse(_ctx, transitRoute.getProperty(TransitRoute.ORIGIN_WAREHOUSE_PROPERTY), newLocation(_parentLocation, TransitRoute.ORIGIN_WAREHOUSE_PROPERTY));
      checkDestinationWarehouse(_ctx, transitRoute.getProperty(TransitRoute.DESTINATION_WAREHOUSE_PROPERTY), newLocation(_parentLocation, TransitRoute.DESTINATION_WAREHOUSE_PROPERTY));
      checkDistanceKm(_ctx, transitRoute.getProperty(TransitRoute.DISTANCE_KM_PROPERTY), newLocation(_parentLocation, TransitRoute.DISTANCE_KM_PROPERTY));
      checkEstimatedDurationHours(_ctx, transitRoute.getProperty(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY), newLocation(_parentLocation, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
      checkStatus(_ctx, transitRoute.getProperty(TransitRoute.STATUS_PROPERTY), newLocation(_parentLocation, TransitRoute.STATUS_PROPERTY));
      checkCreateTime(_ctx, transitRoute.getProperty(TransitRoute.CREATE_TIME_PROPERTY), newLocation(_parentLocation, TransitRoute.CREATE_TIME_PROPERTY));
    }

    public void checkRouteId(UserContext _ctx, String routeId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, routeId);
    if((routeId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, routeId);

    }
    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkOriginWarehouse(UserContext _ctx, Warehouse originWarehouse, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, originWarehouse);
    if((originWarehouse == null)){
        return;
    }
    new WarehouseChecker().checkAndFix(_ctx, originWarehouse, _parentLocation);
    }
    public void checkDestinationWarehouse(UserContext _ctx, Warehouse destinationWarehouse, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, destinationWarehouse);
    if((destinationWarehouse == null)){
        return;
    }
    new WarehouseChecker().checkAndFix(_ctx, destinationWarehouse, _parentLocation);
    }
    public void checkDistanceKm(UserContext _ctx, BigDecimal distanceKm, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, distanceKm);
    if((distanceKm == null)){
        return;
    }
    }
    public void checkEstimatedDurationHours(UserContext _ctx, BigDecimal estimatedDurationHours, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, estimatedDurationHours);
    if((estimatedDurationHours == null)){
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
}