package com.doublechaintech.enterpriselogisticsservice.systemconfiguration;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class SystemConfigurationListExpression<T, E, U extends SystemConfiguration> extends SmartListExpression<T, E, U> {
    public SystemConfigurationListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public SystemConfigurationListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public SystemConfigurationExpression<T, U, U> first() {
       return new SystemConfigurationExpression(super.first());
    }

    public SystemConfigurationExpression<T, U, U> get(int index) {
      return new SystemConfigurationExpression(super.get(index));
    }
}