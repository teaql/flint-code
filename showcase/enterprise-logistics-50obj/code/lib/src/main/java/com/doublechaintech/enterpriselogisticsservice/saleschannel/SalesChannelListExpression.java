package com.doublechaintech.enterpriselogisticsservice.saleschannel;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class SalesChannelListExpression<T, E, U extends SalesChannel> extends SmartListExpression<T, E, U> {
    public SalesChannelListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public SalesChannelListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public SalesChannelExpression<T, U, U> first() {
       return new SalesChannelExpression(super.first());
    }

    public SalesChannelExpression<T, U, U> get(int index) {
      return new SalesChannelExpression(super.get(index));
    }
}