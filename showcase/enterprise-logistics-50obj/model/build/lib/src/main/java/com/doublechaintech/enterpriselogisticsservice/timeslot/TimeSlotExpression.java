package com.doublechaintech.enterpriselogisticsservice.timeslot;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class TimeSlotExpression<T, E, U extends TimeSlot> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public TimeSlotExpression(Expression<T, U> expression){
        super(expression);
    }

    public TimeSlotExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public TimeSlotExpression<T, U, U> updateId(Long id){
        return new TimeSlotExpression(this, $it -> {((TimeSlot)$it).__internalSet("id", id); return this;});
     }

     public TimeSlotExpression<T, U, U> save(UserContext userContext){
        return new TimeSlotExpression(this, $it -> ((TimeSlot)$it).auditAs("Saved by Expression").save(userContext));
     }

     public TimeSlotExpression<T, U, U> save(String intent, UserContext userContext){
        return new TimeSlotExpression(this, $it -> ((TimeSlot)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getSlotId(){
       return apply(TimeSlot::getSlotId);
    }
    public TimeSlotExpression<T, U, U> updateSlotId(String slotId){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateSlotId(slotId));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((TimeSlot)$it).getMovingOrder());
    }

    public TimeSlotExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateMovingOrder(movingOrder));
    }

    public Expression<T, String> getStartTime(){
       return apply(TimeSlot::getStartTime);
    }
    public TimeSlotExpression<T, U, U> updateStartTime(String startTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateStartTime(startTime));
    }

    public Expression<T, String> getEndTime(){
       return apply(TimeSlot::getEndTime);
    }
    public TimeSlotExpression<T, U, U> updateEndTime(String endTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateEndTime(endTime));
    }

    public Expression<T, String> getStatus(){
       return apply(TimeSlot::getStatus);
    }
    public TimeSlotExpression<T, U, U> updateStatus(String status){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(TimeSlot::getCreateTime);
    }
    public TimeSlotExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateCreateTime(createTime));
    }

}