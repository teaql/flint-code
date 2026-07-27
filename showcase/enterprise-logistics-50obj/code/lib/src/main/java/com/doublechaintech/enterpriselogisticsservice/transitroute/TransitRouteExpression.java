package com.doublechaintech.enterpriselogisticsservice.transitroute;

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


    public Expression<T, String> getRouteCode(){
       return apply(TransitRoute::getRouteCode);
    }
    public TransitRouteExpression<T, U, U> updateRouteCode(String routeCode){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateRouteCode(routeCode));
    }

    public Expression<T, String> getOriginCity(){
       return apply(TransitRoute::getOriginCity);
    }
    public TransitRouteExpression<T, U, U> updateOriginCity(String originCity){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateOriginCity(originCity));
    }

    public Expression<T, String> getDestinationCity(){
       return apply(TransitRoute::getDestinationCity);
    }
    public TransitRouteExpression<T, U, U> updateDestinationCity(String destinationCity){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateDestinationCity(destinationCity));
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

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(TransitRoute::getCreatedTime);
    }
    public TransitRouteExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(TransitRoute::getUpdatedTime);
    }
    public TransitRouteExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new TransitRouteExpression(this, $it ->  ((TransitRoute)$it).updateUpdatedTime(updatedTime));
    }

}