package com.doublechaintech.movingcompanyservice.movingevent;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class MovingEventExpression<T, E, U extends MovingEvent> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public MovingEventExpression(Expression<T, U> expression){
        super(expression);
    }

    public MovingEventExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public MovingEventExpression<T, U, U> updateId(Long id){
        return new MovingEventExpression(this, $it -> {((MovingEvent)$it).__internalSet("id", id); return this;});
     }

     public MovingEventExpression<T, U, U> save(UserContext userContext){
        return new MovingEventExpression(this, $it -> ((MovingEvent)$it).auditAs("Saved by Expression").save(userContext));
     }

     public MovingEventExpression<T, U, U> save(String intent, UserContext userContext){
        return new MovingEventExpression(this, $it -> ((MovingEvent)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getCustomer(){
       return apply(MovingEvent::getCustomer);
    }
    public MovingEventExpression<T, U, U> updateCustomer(String customer){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateCustomer(customer));
    }

    public Expression<T, String> getRoute(){
       return apply(MovingEvent::getRoute);
    }
    public MovingEventExpression<T, U, U> updateRoute(String route){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateRoute(route));
    }

    public Expression<T, String> getTimeSlot(){
       return apply(MovingEvent::getTimeSlot);
    }
    public MovingEventExpression<T, U, U> updateTimeSlot(String timeSlot){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateTimeSlot(timeSlot));
    }

    public Expression<T, String> getStatus(){
       return apply(MovingEvent::getStatus);
    }
    public MovingEventExpression<T, U, U> updateStatus(String status){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateStatus(status));
    }

    public Expression<T, LocalDate> getScheduledDate(){
       return apply(MovingEvent::getScheduledDate);
    }
    public MovingEventExpression<T, U, U> updateScheduledDate(LocalDate scheduledDate){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateScheduledDate(scheduledDate));
    }

    public Expression<T, String> getNotes(){
       return apply(MovingEvent::getNotes);
    }
    public MovingEventExpression<T, U, U> updateNotes(String notes){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateNotes(notes));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(MovingEvent::getCreateTime);
    }
    public MovingEventExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(MovingEvent::getUpdateTime);
    }
    public MovingEventExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new MovingEventExpression(this, $it ->  ((MovingEvent)$it).updateUpdateTime(updateTime));
    }

}