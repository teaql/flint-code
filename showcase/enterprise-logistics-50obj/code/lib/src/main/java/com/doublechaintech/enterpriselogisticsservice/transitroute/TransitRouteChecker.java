package com.doublechaintech.enterpriselogisticsservice.transitroute;

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
        if(transitRoute.getCreatedTime() == null){
           transitRoute.updateCreatedTime(java.time.LocalDateTime.now());
        }if(transitRoute.getUpdatedTime() == null){
           transitRoute.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(transitRoute.updateItem()){
        transitRoute.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkRouteCode(_ctx, transitRoute.getProperty(TransitRoute.ROUTE_CODE_PROPERTY), newLocation(_parentLocation, TransitRoute.ROUTE_CODE_PROPERTY));
      checkOriginCity(_ctx, transitRoute.getProperty(TransitRoute.ORIGIN_CITY_PROPERTY), newLocation(_parentLocation, TransitRoute.ORIGIN_CITY_PROPERTY));
      checkDestinationCity(_ctx, transitRoute.getProperty(TransitRoute.DESTINATION_CITY_PROPERTY), newLocation(_parentLocation, TransitRoute.DESTINATION_CITY_PROPERTY));
      checkDistanceKm(_ctx, transitRoute.getProperty(TransitRoute.DISTANCE_KM_PROPERTY), newLocation(_parentLocation, TransitRoute.DISTANCE_KM_PROPERTY));
      checkEstimatedDurationHours(_ctx, transitRoute.getProperty(TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY), newLocation(_parentLocation, TransitRoute.ESTIMATED_DURATION_HOURS_PROPERTY));
      checkCreatedTime(_ctx, transitRoute.getProperty(TransitRoute.CREATED_TIME_PROPERTY), newLocation(_parentLocation, TransitRoute.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, transitRoute.getProperty(TransitRoute.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, TransitRoute.UPDATED_TIME_PROPERTY));
    }

    public void checkRouteCode(UserContext _ctx, String routeCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, routeCode);
    if((routeCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, routeCode);

    }
    public void checkOriginCity(UserContext _ctx, String originCity, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, originCity);
    if((originCity == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, originCity);

    }
    public void checkDestinationCity(UserContext _ctx, String destinationCity, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, destinationCity);
    if((destinationCity == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, destinationCity);

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