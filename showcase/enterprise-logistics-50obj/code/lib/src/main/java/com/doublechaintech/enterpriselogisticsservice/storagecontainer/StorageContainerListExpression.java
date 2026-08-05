package com.doublechaintech.enterpriselogisticsservice.storagecontainer;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class StorageContainerListExpression<T, E, U extends StorageContainer> extends SmartListExpression<T, E, U> {
    public StorageContainerListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public StorageContainerListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public StorageContainerExpression<T, U, U> first() {
       return new StorageContainerExpression(super.first());
    }

    public StorageContainerExpression<T, U, U> get(int index) {
      return new StorageContainerExpression(super.get(index));
    }
}