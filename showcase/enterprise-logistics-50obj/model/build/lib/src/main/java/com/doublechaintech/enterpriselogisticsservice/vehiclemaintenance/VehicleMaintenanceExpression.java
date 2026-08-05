package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((VehicleMaintenance)$it).getVehicle());
    }

    public VehicleMaintenanceExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateVehicle(vehicle));
    }

    public Expression<T, String> getServiceType(){
       return apply(VehicleMaintenance::getServiceType);
    }
    public VehicleMaintenanceExpression<T, U, U> updateServiceType(String serviceType){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateServiceType(serviceType));
    }

    public Expression<T, LocalDate> getServiceDate(){
       return apply(VehicleMaintenance::getServiceDate);
    }
    public VehicleMaintenanceExpression<T, U, U> updateServiceDate(LocalDate serviceDate){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateServiceDate(serviceDate));
    }

    public Expression<T, String> getCost(){
       return apply(VehicleMaintenance::getCost);
    }
    public VehicleMaintenanceExpression<T, U, U> updateCost(String cost){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateCost(cost));
    }

    public Expression<T, String> getStatus(){
       return apply(VehicleMaintenance::getStatus);
    }
    public VehicleMaintenanceExpression<T, U, U> updateStatus(String status){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(VehicleMaintenance::getCreatedAt);
    }
    public VehicleMaintenanceExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new VehicleMaintenanceExpression(this, $it ->  ((VehicleMaintenance)$it).updateCreatedAt(createdAt));
    }

}