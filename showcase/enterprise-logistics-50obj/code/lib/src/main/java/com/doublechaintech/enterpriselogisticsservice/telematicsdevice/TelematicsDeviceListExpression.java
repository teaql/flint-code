package com.doublechaintech.enterpriselogisticsservice.telematicsdevice;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class TelematicsDeviceListExpression<T, E, U extends TelematicsDevice> extends SmartListExpression<T, E, U> {
    public TelematicsDeviceListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public TelematicsDeviceListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public TelematicsDeviceExpression<T, U, U> first() {
       return new TelematicsDeviceExpression(super.first());
    }

    public TelematicsDeviceExpression<T, U, U> get(int index) {
      return new TelematicsDeviceExpression(super.get(index));
    }
}