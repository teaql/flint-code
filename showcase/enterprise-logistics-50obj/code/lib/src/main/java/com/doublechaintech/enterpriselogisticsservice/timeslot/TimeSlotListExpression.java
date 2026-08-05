package com.doublechaintech.enterpriselogisticsservice.timeslot;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class TimeSlotListExpression<T, E, U extends TimeSlot> extends SmartListExpression<T, E, U> {
    public TimeSlotListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public TimeSlotListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public TimeSlotExpression<T, U, U> first() {
       return new TimeSlotExpression(super.first());
    }

    public TimeSlotExpression<T, U, U> get(int index) {
      return new TimeSlotExpression(super.get(index));
    }
}