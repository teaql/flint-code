package com.doublechaintech.enterpriselogisticsservice.servicequote;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class ServiceQuoteListExpression<T, E, U extends ServiceQuote> extends SmartListExpression<T, E, U> {
    public ServiceQuoteListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public ServiceQuoteListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public ServiceQuoteExpression<T, U, U> first() {
       return new ServiceQuoteExpression(super.first());
    }

    public ServiceQuoteExpression<T, U, U> get(int index) {
      return new ServiceQuoteExpression(super.get(index));
    }
}