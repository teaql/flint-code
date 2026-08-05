package com.doublechaintech.enterpriselogisticsservice.paymentrecord;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class PaymentRecordListExpression<T, E, U extends PaymentRecord> extends SmartListExpression<T, E, U> {
    public PaymentRecordListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public PaymentRecordListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public PaymentRecordExpression<T, U, U> first() {
       return new PaymentRecordExpression(super.first());
    }

    public PaymentRecordExpression<T, U, U> get(int index) {
      return new PaymentRecordExpression(super.get(index));
    }
}