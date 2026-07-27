package com.doublechaintech.enterpriselogisticsservice.servicecontract;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class ServiceContractListExpression<T, E, U extends ServiceContract> extends SmartListExpression<T, E, U> {
    public ServiceContractListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public ServiceContractListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public ServiceContractExpression<T, U, U> first() {
       return new ServiceContractExpression(super.first());
    }

    public ServiceContractExpression<T, U, U> get(int index) {
      return new ServiceContractExpression(super.get(index));
    }
}