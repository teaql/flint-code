package com.doublechaintech.enterpriselogisticsservice.staffmember;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class StaffMemberListExpression<T, E, U extends StaffMember> extends SmartListExpression<T, E, U> {
    public StaffMemberListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public StaffMemberListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public StaffMemberExpression<T, U, U> first() {
       return new StaffMemberExpression(super.first());
    }

    public StaffMemberExpression<T, U, U> get(int index) {
      return new StaffMemberExpression(super.get(index));
    }
}