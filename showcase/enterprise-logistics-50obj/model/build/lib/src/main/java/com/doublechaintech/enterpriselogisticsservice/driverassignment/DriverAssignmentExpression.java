package com.doublechaintech.enterpriselogisticsservice.driverassignment;

import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class DriverAssignmentExpression<T, E, U extends DriverAssignment> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public DriverAssignmentExpression(Expression<T, U> expression){
        super(expression);
    }

    public DriverAssignmentExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public DriverAssignmentExpression<T, U, U> updateId(Long id){
        return new DriverAssignmentExpression(this, $it -> {((DriverAssignment)$it).__internalSet("id", id); return this;});
     }

     public DriverAssignmentExpression<T, U, U> save(UserContext userContext){
        return new DriverAssignmentExpression(this, $it -> ((DriverAssignment)$it).auditAs("Saved by Expression").save(userContext));
     }

     public DriverAssignmentExpression<T, U, U> save(String intent, UserContext userContext){
        return new DriverAssignmentExpression(this, $it -> ((DriverAssignment)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((DriverAssignment)$it).getVehicle());
    }

    public DriverAssignmentExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new DriverAssignmentExpression(this, $it ->  ((DriverAssignment)$it).updateVehicle(vehicle));
    }

    public Expression<T, String> getDriver(){
       return apply(DriverAssignment::getDriver);
    }
    public DriverAssignmentExpression<T, U, U> updateDriver(String driver){
       return new DriverAssignmentExpression(this, $it ->  ((DriverAssignment)$it).updateDriver(driver));
    }

    public Expression<T, LocalDate> getStartDate(){
       return apply(DriverAssignment::getStartDate);
    }
    public DriverAssignmentExpression<T, U, U> updateStartDate(LocalDate startDate){
       return new DriverAssignmentExpression(this, $it ->  ((DriverAssignment)$it).updateStartDate(startDate));
    }

    public Expression<T, LocalDate> getEndDate(){
       return apply(DriverAssignment::getEndDate);
    }
    public DriverAssignmentExpression<T, U, U> updateEndDate(LocalDate endDate){
       return new DriverAssignmentExpression(this, $it ->  ((DriverAssignment)$it).updateEndDate(endDate));
    }

    public Expression<T, String> getStatus(){
       return apply(DriverAssignment::getStatus);
    }
    public DriverAssignmentExpression<T, U, U> updateStatus(String status){
       return new DriverAssignmentExpression(this, $it ->  ((DriverAssignment)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(DriverAssignment::getCreatedAt);
    }
    public DriverAssignmentExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new DriverAssignmentExpression(this, $it ->  ((DriverAssignment)$it).updateCreatedAt(createdAt));
    }

}