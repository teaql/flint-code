package com.doublechaintech.enterpriselogisticsservice.storagefee;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class StorageFeeListExpression<T, E, U extends StorageFee> extends SmartListExpression<T, E, U> {
    public StorageFeeListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public StorageFeeListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public StorageFeeExpression<T, U, U> first() {
       return new StorageFeeExpression(super.first());
    }

    public StorageFeeExpression<T, U, U> get(int index) {
      return new StorageFeeExpression(super.get(index));
    }
}