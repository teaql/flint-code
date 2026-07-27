package com.doublechaintech.enterpriselogisticsservice.invoice;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class InvoiceListExpression<T, E, U extends Invoice> extends SmartListExpression<T, E, U> {
    public InvoiceListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public InvoiceListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public InvoiceExpression<T, U, U> first() {
       return new InvoiceExpression(super.first());
    }

    public InvoiceExpression<T, U, U> get(int index) {
      return new InvoiceExpression(super.get(index));
    }
}