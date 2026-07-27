package com.doublechaintech.enterpriselogisticsservice.workshift;

import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHours;
import com.doublechaintech.enterpriselogisticsservice.workedhours.WorkedHoursListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.Function;

public class WorkShiftExpression<T, E, U extends WorkShift> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public WorkShiftExpression(Expression<T, U> expression){
        super(expression);
    }

    public WorkShiftExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public WorkShiftExpression<T, U, U> updateId(Long id){
        return new WorkShiftExpression(this, $it -> {((WorkShift)$it).__internalSet("id", id); return this;});
     }

     public WorkShiftExpression<T, U, U> save(UserContext userContext){
        return new WorkShiftExpression(this, $it -> ((WorkShift)$it).auditAs("Saved by Expression").save(userContext));
     }

     public WorkShiftExpression<T, U, U> save(String intent, UserContext userContext){
        return new WorkShiftExpression(this, $it -> ((WorkShift)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(WorkShift::getName);
    }
    public WorkShiftExpression<T, U, U> updateName(String name){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).updateName(name));
    }

    public Expression<T, LocalTime> getStartTime(){
       return apply(WorkShift::getStartTime);
    }
    public WorkShiftExpression<T, U, U> updateStartTime(LocalTime startTime){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).updateStartTime(startTime));
    }

    public Expression<T, LocalTime> getEndTime(){
       return apply(WorkShift::getEndTime);
    }
    public WorkShiftExpression<T, U, U> updateEndTime(LocalTime endTime){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).updateEndTime(endTime));
    }

    public Expression<T, LocalDate> getShiftDate(){
       return apply(WorkShift::getShiftDate);
    }
    public WorkShiftExpression<T, U, U> updateShiftDate(LocalDate shiftDate){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).updateShiftDate(shiftDate));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(WorkShift::getCreatedAt);
    }
    public WorkShiftExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(WorkShift::getUpdatedAt);
    }
    public WorkShiftExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).updateUpdatedAt(updatedAt));
    }

    public WorkedHoursListExpression<T, U, WorkedHours> getWorkedHoursList(){
        return new WorkedHoursListExpression(this, $it ->  ((WorkShift)$it).getWorkedHoursList());
    }
    public WorkShiftExpression<T, U, U> addWorkedHours(WorkedHours workedHours){
       return new WorkShiftExpression(this, $it ->  ((WorkShift)$it).addWorkedHours(workedHours));
    }
}