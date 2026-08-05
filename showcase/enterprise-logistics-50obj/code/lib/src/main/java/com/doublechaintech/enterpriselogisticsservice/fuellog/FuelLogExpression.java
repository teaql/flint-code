package com.doublechaintech.enterpriselogisticsservice.fuellog;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

public class FuelLogExpression<T, E, U extends FuelLog> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public FuelLogExpression(Expression<T, U> expression){
        super(expression);
    }

    public FuelLogExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public FuelLogExpression<T, U, U> updateId(Long id){
        return new FuelLogExpression(this, $it -> {((FuelLog)$it).__internalSet("id", id); return this;});
     }

     public FuelLogExpression<T, U, U> save(UserContext userContext){
        return new FuelLogExpression(this, $it -> ((FuelLog)$it).auditAs("Saved by Expression").save(userContext));
     }

     public FuelLogExpression<T, U, U> save(String intent, UserContext userContext){
        return new FuelLogExpression(this, $it -> ((FuelLog)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, BigDecimal> getLiters(){
       return apply(FuelLog::getLiters);
    }
    public FuelLogExpression<T, U, U> updateLiters(BigDecimal liters){
       return new FuelLogExpression(this, $it ->  ((FuelLog)$it).updateLiters(liters));
    }

    public Expression<T, BigDecimal> getCost(){
       return apply(FuelLog::getCost);
    }
    public FuelLogExpression<T, U, U> updateCost(BigDecimal cost){
       return new FuelLogExpression(this, $it ->  ((FuelLog)$it).updateCost(cost));
    }

    public Expression<T, Integer> getOdometerKm(){
       return apply(FuelLog::getOdometerKm);
    }
    public FuelLogExpression<T, U, U> updateOdometerKm(Integer odometerKm){
       return new FuelLogExpression(this, $it ->  ((FuelLog)$it).updateOdometerKm(odometerKm));
    }

    public Expression<T, String> getStationName(){
       return apply(FuelLog::getStationName);
    }
    public FuelLogExpression<T, U, U> updateStationName(String stationName){
       return new FuelLogExpression(this, $it ->  ((FuelLog)$it).updateStationName(stationName));
    }

    public Expression<T, LocalDate> getDate(){
       return apply(FuelLog::getDate);
    }
    public FuelLogExpression<T, U, U> updateDate(LocalDate date){
       return new FuelLogExpression(this, $it ->  ((FuelLog)$it).updateDate(date));
    }

    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((FuelLog)$it).getVehicle());
    }

    public FuelLogExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new FuelLogExpression(this, $it ->  ((FuelLog)$it).updateVehicle(vehicle));
    }

}