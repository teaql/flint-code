package com.doublechaintech.movingcompanyservice.vehicle;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class VehicleExpression<T, E, U extends Vehicle> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public VehicleExpression(Expression<T, U> expression){
        super(expression);
    }

    public VehicleExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public VehicleExpression<T, U, U> updateId(Long id){
        return new VehicleExpression(this, $it -> {((Vehicle)$it).__internalSet("id", id); return this;});
     }

     public VehicleExpression<T, U, U> save(UserContext userContext){
        return new VehicleExpression(this, $it -> ((Vehicle)$it).auditAs("Saved by Expression").save(userContext));
     }

     public VehicleExpression<T, U, U> save(String intent, UserContext userContext){
        return new VehicleExpression(this, $it -> ((Vehicle)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getInternalType(){
       return apply(Vehicle::getInternalType);
    }
    public VehicleExpression<T, U, U> updateInternalType(String internalType){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateInternalType(internalType));
    }

    public Expression<T, String> getDisplayName(){
       return apply(Vehicle::getDisplayName);
    }
    public VehicleExpression<T, U, U> updateDisplayName(String displayName){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateDisplayName(displayName));
    }

    public Expression<T, String> getVehicleType(){
       return apply(Vehicle::getVehicleType);
    }
    public VehicleExpression<T, U, U> updateVehicleType(String vehicleType){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateVehicleType(vehicleType));
    }

    public Expression<T, String> getLicensePlate(){
       return apply(Vehicle::getLicensePlate);
    }
    public VehicleExpression<T, U, U> updateLicensePlate(String licensePlate){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateLicensePlate(licensePlate));
    }

    public Expression<T, BigDecimal> getCapacityCubicMeters(){
       return apply(Vehicle::getCapacityCubicMeters);
    }
    public VehicleExpression<T, U, U> updateCapacityCubicMeters(BigDecimal capacityCubicMeters){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateCapacityCubicMeters(capacityCubicMeters));
    }

    public Expression<T, LocalDate> getPurchaseDate(){
       return apply(Vehicle::getPurchaseDate);
    }
    public VehicleExpression<T, U, U> updatePurchaseDate(LocalDate purchaseDate){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updatePurchaseDate(purchaseDate));
    }

    public Expression<T, String> getStatus(){
       return apply(Vehicle::getStatus);
    }
    public VehicleExpression<T, U, U> updateStatus(String status){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateStatus(status));
    }

    public Expression<T, LocalDate> getLastMaintenanceDate(){
       return apply(Vehicle::getLastMaintenanceDate);
    }
    public VehicleExpression<T, U, U> updateLastMaintenanceDate(LocalDate lastMaintenanceDate){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateLastMaintenanceDate(lastMaintenanceDate));
    }

    public Expression<T, LocalDate> getNextMaintenanceDate(){
       return apply(Vehicle::getNextMaintenanceDate);
    }
    public VehicleExpression<T, U, U> updateNextMaintenanceDate(LocalDate nextMaintenanceDate){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateNextMaintenanceDate(nextMaintenanceDate));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(Vehicle::getCreateTime);
    }
    public VehicleExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(Vehicle::getUpdateTime);
    }
    public VehicleExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateUpdateTime(updateTime));
    }

}