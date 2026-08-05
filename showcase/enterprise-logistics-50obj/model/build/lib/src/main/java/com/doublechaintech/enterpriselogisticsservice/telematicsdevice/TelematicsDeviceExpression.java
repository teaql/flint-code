package com.doublechaintech.enterpriselogisticsservice.telematicsdevice;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class TelematicsDeviceExpression<T, E, U extends TelematicsDevice> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public TelematicsDeviceExpression(Expression<T, U> expression){
        super(expression);
    }

    public TelematicsDeviceExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public TelematicsDeviceExpression<T, U, U> updateId(Long id){
        return new TelematicsDeviceExpression(this, $it -> {((TelematicsDevice)$it).__internalSet("id", id); return this;});
     }

     public TelematicsDeviceExpression<T, U, U> save(UserContext userContext){
        return new TelematicsDeviceExpression(this, $it -> ((TelematicsDevice)$it).auditAs("Saved by Expression").save(userContext));
     }

     public TelematicsDeviceExpression<T, U, U> save(String intent, UserContext userContext){
        return new TelematicsDeviceExpression(this, $it -> ((TelematicsDevice)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getDeviceId(){
       return apply(TelematicsDevice::getDeviceId);
    }
    public TelematicsDeviceExpression<T, U, U> updateDeviceId(String deviceId){
       return new TelematicsDeviceExpression(this, $it ->  ((TelematicsDevice)$it).updateDeviceId(deviceId));
    }

    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((TelematicsDevice)$it).getVehicle());
    }

    public TelematicsDeviceExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new TelematicsDeviceExpression(this, $it ->  ((TelematicsDevice)$it).updateVehicle(vehicle));
    }

    public Expression<T, String> getFirmwareVersion(){
       return apply(TelematicsDevice::getFirmwareVersion);
    }
    public TelematicsDeviceExpression<T, U, U> updateFirmwareVersion(String firmwareVersion){
       return new TelematicsDeviceExpression(this, $it ->  ((TelematicsDevice)$it).updateFirmwareVersion(firmwareVersion));
    }

    public Expression<T, String> getStatus(){
       return apply(TelematicsDevice::getStatus);
    }
    public TelematicsDeviceExpression<T, U, U> updateStatus(String status){
       return new TelematicsDeviceExpression(this, $it ->  ((TelematicsDevice)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(TelematicsDevice::getCreatedAt);
    }
    public TelematicsDeviceExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new TelematicsDeviceExpression(this, $it ->  ((TelematicsDevice)$it).updateCreatedAt(createdAt));
    }

}