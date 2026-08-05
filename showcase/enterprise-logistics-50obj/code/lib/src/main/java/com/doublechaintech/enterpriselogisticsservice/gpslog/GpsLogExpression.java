package com.doublechaintech.enterpriselogisticsservice.gpslog;

import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
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


    public Expression<T, BigDecimal> getLatitude(){
       return apply(GpsLog::getLatitude);
    }
    public GpsLogExpression<T, U, U> updateLatitude(BigDecimal latitude){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateLatitude(latitude));
    }

    public Expression<T, BigDecimal> getLongitude(){
       return apply(GpsLog::getLongitude);
    }
    public GpsLogExpression<T, U, U> updateLongitude(BigDecimal longitude){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateLongitude(longitude));
    }

    public Expression<T, Integer> getSpeedKmh(){
       return apply(GpsLog::getSpeedKmh);
    }
    public GpsLogExpression<T, U, U> updateSpeedKmh(Integer speedKmh){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateSpeedKmh(speedKmh));
    }

    public Expression<T, Integer> getHeading(){
       return apply(GpsLog::getHeading);
    }
    public GpsLogExpression<T, U, U> updateHeading(Integer heading){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateHeading(heading));
    }

    public Expression<T, LocalDateTime> getTimestamp(){
       return apply(GpsLog::getTimestamp);
    }
    public GpsLogExpression<T, U, U> updateTimestamp(LocalDateTime timestamp){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateTimestamp(timestamp));
    }

    public TelematicsDeviceExpression<T, U, TelematicsDevice> getDevice(){
       return new TelematicsDeviceExpression(this, $it ->  ((GpsLog)$it).getDevice());
    }

    public GpsLogExpression<T, U, U> updateDevice(TelematicsDevice device){
       return new GpsLogExpression(this, $it ->  ((GpsLog)$it).updateDevice(device));
    }

}