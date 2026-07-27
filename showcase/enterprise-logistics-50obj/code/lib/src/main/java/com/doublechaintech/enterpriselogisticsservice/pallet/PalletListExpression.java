package com.doublechaintech.enterpriselogisticsservice.pallet;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class PalletListExpression<T, E, U extends Pallet> extends SmartListExpression<T, E, U> {
    public PalletListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public PalletListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public PalletExpression<T, U, U> first() {
       return new PalletExpression(super.first());
    }

    public PalletExpression<T, U, U> get(int index) {
      return new PalletExpression(super.get(index));
    }
}