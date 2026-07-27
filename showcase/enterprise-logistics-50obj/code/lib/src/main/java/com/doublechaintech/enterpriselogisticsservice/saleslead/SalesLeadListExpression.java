package com.doublechaintech.enterpriselogisticsservice.saleslead;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class SalesLeadListExpression<T, E, U extends SalesLead> extends SmartListExpression<T, E, U> {
    public SalesLeadListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public SalesLeadListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public SalesLeadExpression<T, U, U> first() {
       return new SalesLeadExpression(super.first());
    }

    public SalesLeadExpression<T, U, U> get(int index) {
      return new SalesLeadExpression(super.get(index));
    }
}