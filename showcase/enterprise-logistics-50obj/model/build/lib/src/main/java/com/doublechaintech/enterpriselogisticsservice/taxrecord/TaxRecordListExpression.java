package com.doublechaintech.enterpriselogisticsservice.taxrecord;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class TaxRecordListExpression<T, E, U extends TaxRecord> extends SmartListExpression<T, E, U> {
    public TaxRecordListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public TaxRecordListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public TaxRecordExpression<T, U, U> first() {
       return new TaxRecordExpression(super.first());
    }

    public TaxRecordExpression<T, U, U> get(int index) {
      return new TaxRecordExpression(super.get(index));
    }
}