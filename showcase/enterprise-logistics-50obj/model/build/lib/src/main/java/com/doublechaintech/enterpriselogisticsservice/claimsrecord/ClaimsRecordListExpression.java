package com.doublechaintech.enterpriselogisticsservice.claimsrecord;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class ClaimsRecordListExpression<T, E, U extends ClaimsRecord> extends SmartListExpression<T, E, U> {
    public ClaimsRecordListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public ClaimsRecordListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public ClaimsRecordExpression<T, U, U> first() {
       return new ClaimsRecordExpression(super.first());
    }

    public ClaimsRecordExpression<T, U, U> get(int index) {
      return new ClaimsRecordExpression(super.get(index));
    }
}