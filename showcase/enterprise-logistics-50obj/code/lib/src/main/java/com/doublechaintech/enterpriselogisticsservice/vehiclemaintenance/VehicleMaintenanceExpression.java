package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

public class VehicleMaintenanceExpression<T, E, U extends VehicleMaintenance> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public VehicleMaintenanceExpression(Expression<T, U> expression){
        super(expression);
    }

    public VehicleMaintenanceExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public VehicleMaintenanceExpression<T, U, U> updateId(Long id){
        return new VehicleMaintenanceExpression(this, $it -> {((VehicleMaintenance)$it).__internalSet("id", id); return this;});
     }

     public VehicleMaintenanceExpression<T, U, U> save(UserContext userContext){
        return new VehicleMaintenanceExpression(this, $it -> ((VehicleMaintenance)$it).auditAs("Saved by Expression").save(userContext));
     }

     public VehicleMaintenanceExpression<T, U, U> save(String intent, UserContext userContext){
        return new VehicleMaintenanceExpression(this, $it -> ((VehicleMaintenance)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getServiceType(){
       return apply(VehicleMaintenance::getServiceType);
    }
    public VehicleMaintenanceExpression<T, U, U> updateServiceType(String serviceType){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateServiceType(serviceType));
    }

    public Expression<T, String> getDescription(){
       return apply(VehicleMaintenance::getDescription);
    }
    public VehicleMaintenanceExpression<T, U, U> updateDescription(String description){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateDescription(description));
    }

    public Expression<T, BigDecimal> getCost(){
       return apply(VehicleMaintenance::getCost);
    }
    public VehicleMaintenanceExpression<T, U, U> updateCost(BigDecimal cost){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateCost(cost));
    }

    public Expression<T, LocalDate> getScheduledDate(){
       return apply(VehicleMaintenance::getScheduledDate);
    }
    public VehicleMaintenanceExpression<T, U, U> updateScheduledDate(LocalDate scheduledDate){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateScheduledDate(scheduledDate));
    }

    public Expression<T, LocalDate> getCompletedDate(){
       return apply(VehicleMaintenance::getCompletedDate);
    }
    public VehicleMaintenanceExpression<T, U, U> updateCompletedDate(LocalDate completedDate){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateCompletedDate(completedDate));
    }

    public Expression<T, String> getStatus(){
       return apply(VehicleMaintenance::getStatus);
    }
    public VehicleMaintenanceExpression<T, U, U> updateStatus(String status){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateStatus(status));
    }

    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((VehicleMaintenance)$it).getVehicle());
    }

    public VehicleMaintenanceExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateVehicle(vehicle));
    }

}