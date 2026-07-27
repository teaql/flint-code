package com.doublechaintech.enterpriselogisticsservice.dispatchplan;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import com.doublechaintech.enterpriselogisticsservice.vehicle.Vehicle;
import com.doublechaintech.enterpriselogisticsservice.vehicle.VehicleExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class DispatchPlanExpression<T, E, U extends DispatchPlan> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public DispatchPlanExpression(Expression<T, U> expression){
        super(expression);
    }

    public DispatchPlanExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public DispatchPlanExpression<T, U, U> updateId(Long id){
        return new DispatchPlanExpression(this, $it -> {((DispatchPlan)$it).__internalSet("id", id); return this;});
     }

     public DispatchPlanExpression<T, U, U> save(UserContext userContext){
        return new DispatchPlanExpression(this, $it -> ((DispatchPlan)$it).auditAs("Saved by Expression").save(userContext));
     }

     public DispatchPlanExpression<T, U, U> save(String intent, UserContext userContext){
        return new DispatchPlanExpression(this, $it -> ((DispatchPlan)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getPlanId(){
       return apply(DispatchPlan::getPlanId);
    }
    public DispatchPlanExpression<T, U, U> updatePlanId(String planId){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updatePlanId(planId));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((DispatchPlan)$it).getMovingOrder());
    }

    public DispatchPlanExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateMovingOrder(movingOrder));
    }

    public VehicleExpression<T, U, Vehicle> getVehicle(){
       return new VehicleExpression(this, $it ->  ((DispatchPlan)$it).getVehicle());
    }

    public DispatchPlanExpression<T, U, U> updateVehicle(Vehicle vehicle){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateVehicle(vehicle));
    }

    public StaffMemberExpression<T, U, StaffMember> getDriver(){
       return new StaffMemberExpression(this, $it ->  ((DispatchPlan)$it).getDriver());
    }

    public DispatchPlanExpression<T, U, U> updateDriver(StaffMember driver){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateDriver(driver));
    }

    public Expression<T, String> getStatus(){
       return apply(DispatchPlan::getStatus);
    }
    public DispatchPlanExpression<T, U, U> updateStatus(String status){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateStatus(status));
    }

    public Expression<T, String> getScheduledDeparture(){
       return apply(DispatchPlan::getScheduledDeparture);
    }
    public DispatchPlanExpression<T, U, U> updateScheduledDeparture(String scheduledDeparture){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateScheduledDeparture(scheduledDeparture));
    }

    public Expression<T, String> getScheduledArrival(){
       return apply(DispatchPlan::getScheduledArrival);
    }
    public DispatchPlanExpression<T, U, U> updateScheduledArrival(String scheduledArrival){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateScheduledArrival(scheduledArrival));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(DispatchPlan::getCreateTime);
    }
    public DispatchPlanExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(DispatchPlan::getUpdateTime);
    }
    public DispatchPlanExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new DispatchPlanExpression(this, $it ->  ((DispatchPlan)$it).updateUpdateTime(updateTime));
    }

}