package com.doublechaintech.enterpriselogisticsservice.workedhours;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShift;
import com.doublechaintech.enterpriselogisticsservice.workshift.WorkShiftExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class WorkedHoursExpression<T, E, U extends WorkedHours> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public WorkedHoursExpression(Expression<T, U> expression){
        super(expression);
    }

    public WorkedHoursExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public WorkedHoursExpression<T, U, U> updateId(Long id){
        return new WorkedHoursExpression(this, $it -> {((WorkedHours)$it).__internalSet("id", id); return this;});
     }

     public WorkedHoursExpression<T, U, U> save(UserContext userContext){
        return new WorkedHoursExpression(this, $it -> ((WorkedHours)$it).auditAs("Saved by Expression").save(userContext));
     }

     public WorkedHoursExpression<T, U, U> save(String intent, UserContext userContext){
        return new WorkedHoursExpression(this, $it -> ((WorkedHours)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public StaffMemberExpression<T, U, StaffMember> getStaff(){
       return new StaffMemberExpression(this, $it ->  ((WorkedHours)$it).getStaff());
    }

    public WorkedHoursExpression<T, U, U> updateStaff(StaffMember staff){
       return new WorkedHoursExpression(this, $it ->  ((WorkedHours)$it).updateStaff(staff));
    }

    public WorkShiftExpression<T, U, WorkShift> getShift(){
       return new WorkShiftExpression(this, $it ->  ((WorkedHours)$it).getShift());
    }

    public WorkedHoursExpression<T, U, U> updateShift(WorkShift shift){
       return new WorkedHoursExpression(this, $it ->  ((WorkedHours)$it).updateShift(shift));
    }

    public Expression<T, LocalDate> getDate(){
       return apply(WorkedHours::getDate);
    }
    public WorkedHoursExpression<T, U, U> updateDate(LocalDate date){
       return new WorkedHoursExpression(this, $it ->  ((WorkedHours)$it).updateDate(date));
    }

    public Expression<T, String> getHoursWorked(){
       return apply(WorkedHours::getHoursWorked);
    }
    public WorkedHoursExpression<T, U, U> updateHoursWorked(String hoursWorked){
       return new WorkedHoursExpression(this, $it ->  ((WorkedHours)$it).updateHoursWorked(hoursWorked));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(WorkedHours::getCreatedAt);
    }
    public WorkedHoursExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new WorkedHoursExpression(this, $it ->  ((WorkedHours)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(WorkedHours::getUpdatedAt);
    }
    public WorkedHoursExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new WorkedHoursExpression(this, $it ->  ((WorkedHours)$it).updateUpdatedAt(updatedAt));
    }

}