package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class PickupAddressListExpression<T, E, U extends PickupAddress> extends SmartListExpression<T, E, U> {
    public PickupAddressListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public PickupAddressListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public PickupAddressExpression<T, U, U> first() {
       return new PickupAddressExpression(super.first());
    }

    public PickupAddressExpression<T, U, U> get(int index) {
      return new PickupAddressExpression(super.get(index));
    }
}