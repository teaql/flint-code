package com.doublechaintech.enterpriselogisticsservice.transitroute;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class TransitRouteExpression<T, E, U extends TransitRoute> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public TransitRouteExpression(Expression<T, U> expression){
        super(expression);
    }

    public TransitRouteExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public TransitRouteExpression<T, U, U> updateId(Long id){
        return new TransitRouteExpression(this, $it -> {((TransitRoute)$it).__internalSet("id", id); return this;});
     }

     public TransitRouteExpression<T, U, U> save(UserContext userContext){
        return new TransitRouteExpression(this, $it -> ((TransitRoute)$it).auditAs("Saved by Expression").save(userContext));
     }

     public TransitRouteExpression<T, U, U> save(String intent, UserContext userContext){
        return new TransitRouteExpression(this, $it -> ((TransitRoute)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getRouteId(){
       return apply(TransitRoute::getRouteId);
    }
    public TransitRouteExpression<T, U, U> updateRouteId(String routeId){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateRouteId(routeId));
    }

    public Expression<T, String> getName(){
       return apply(TransitRoute::getName);
    }
    public TransitRouteExpression<T, U, U> updateName(String name){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateName(name));
    }

    public WarehouseExpression<T, U, Warehouse> getOriginWarehouse(){
       return new WarehouseExpression(this, $it ->  ((TransitRoute)$it).getOriginWarehouse());
    }

    public TransitRouteExpression<T, U, U> updateOriginWarehouse(Warehouse originWarehouse){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateOriginWarehouse(originWarehouse));
    }

    public WarehouseExpression<T, U, Warehouse> getDestinationWarehouse(){
       return new WarehouseExpression(this, $it ->  ((TransitRoute)$it).getDestinationWarehouse());
    }

    public TransitRouteExpression<T, U, U> updateDestinationWarehouse(Warehouse destinationWarehouse){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateDestinationWarehouse(destinationWarehouse));
    }

    public Expression<T, BigDecimal> getDistanceKm(){
       return apply(TransitRoute::getDistanceKm);
    }
    public TransitRouteExpression<T, U, U> updateDistanceKm(BigDecimal distanceKm){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateDistanceKm(distanceKm));
    }

    public Expression<T, BigDecimal> getEstimatedDurationHours(){
       return apply(TransitRoute::getEstimatedDurationHours);
    }
    public TransitRouteExpression<T, U, U> updateEstimatedDurationHours(BigDecimal estimatedDurationHours){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateEstimatedDurationHours(estimatedDurationHours));
    }

    public Expression<T, String> getStatus(){
       return apply(TransitRoute::getStatus);
    }
    public TransitRouteExpression<T, U, U> updateStatus(String status){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(TransitRoute::getCreateTime);
    }
    public TransitRouteExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateCreateTime(createTime));
    }

}