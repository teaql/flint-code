package com.doublechaintech.enterpriselogisticsservice.timeslot;

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


    public Expression<T, String> getSlotCode(){
       return apply(TimeSlot::getSlotCode);
    }
    public TimeSlotExpression<T, U, U> updateSlotCode(String slotCode){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateSlotCode(slotCode));
    }

    public Expression<T, LocalDateTime> getStartTime(){
       return apply(TimeSlot::getStartTime);
    }
    public TimeSlotExpression<T, U, U> updateStartTime(LocalDateTime startTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateStartTime(startTime));
    }

    public Expression<T, LocalDateTime> getEndTime(){
       return apply(TimeSlot::getEndTime);
    }
    public TimeSlotExpression<T, U, U> updateEndTime(LocalDateTime endTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateEndTime(endTime));
    }

    public Expression<T, Integer> getCapacity(){
       return apply(TimeSlot::getCapacity);
    }
    public TimeSlotExpression<T, U, U> updateCapacity(Integer capacity){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateCapacity(capacity));
    }

    public Expression<T, Integer> getAvailableSpots(){
       return apply(TimeSlot::getAvailableSpots);
    }
    public TimeSlotExpression<T, U, U> updateAvailableSpots(Integer availableSpots){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateAvailableSpots(availableSpots));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(TimeSlot::getCreatedTime);
    }
    public TimeSlotExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(TimeSlot::getUpdatedTime);
    }
    public TimeSlotExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new TimeSlotExpression(this, $it ->  ((TimeSlot)$it).updateUpdatedTime(updatedTime));
    }

}