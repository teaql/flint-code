package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class CustomsDeclarationListExpression<T, E, U extends CustomsDeclaration> extends SmartListExpression<T, E, U> {
    public CustomsDeclarationListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public CustomsDeclarationListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public CustomsDeclarationExpression<T, U, U> first() {
       return new CustomsDeclarationExpression(super.first());
    }

    public CustomsDeclarationExpression<T, U, U> get(int index) {
      return new CustomsDeclarationExpression(super.get(index));
    }
}