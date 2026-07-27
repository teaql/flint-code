package com.doublechaintech.enterpriselogisticsservice.gpslog;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class GpsLogExpression<T, E, U extends GpsLog> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public GpsLogExpression(Expression<T, U> expression){
        super(expression);
    }

    public GpsLogExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public GpsLogExpression<T, U, U> updateId(Long id){
        return new GpsLogExpression(this, $it -> {((GpsLog)$it).__internalSet("id", id); return this;});
     }

     public GpsLogExpression<T, U, U> save(UserContext userContext){
        return new GpsLogExpression(this, $it -> ((GpsLog)$it).auditAs("Saved by Expression").save(userContext));
     }

     public GpsLogExpression<T, U, U> save(String intent, UserContext userContext){
        return new GpsLogExpression(this, $it -> ((GpsLog)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((GpsLog)$it).getVehicle());
    }

    public GpsLogExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateVehicle(vehicle));
    }

    public Expression<T, String> getLatitude(){
       return apply(GpsLog::getLatitude);
    }
    public GpsLogExpression<T, U, U> updateLatitude(String latitude){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateLatitude(latitude));
    }

    public Expression<T, String> getLongitude(){
       return apply(GpsLog::getLongitude);
    }
    public GpsLogExpression<T, U, U> updateLongitude(String longitude){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateLongitude(longitude));
    }

    public Expression<T, LocalDateTime> getTimestamp(){
       return apply(GpsLog::getTimestamp);
    }
    public GpsLogExpression<T, U, U> updateTimestamp(LocalDateTime timestamp){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateTimestamp(timestamp));
    }

    public Expression<T, String> getSpeedKmh(){
       return apply(GpsLog::getSpeedKmh);
    }
    public GpsLogExpression<T, U, U> updateSpeedKmh(String speedKmh){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateSpeedKmh(speedKmh));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(GpsLog::getCreatedAt);
    }
    public GpsLogExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateCreatedAt(createdAt));
    }

}