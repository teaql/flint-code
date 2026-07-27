package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class ExpenseItemListExpression<T, E, U extends ExpenseItem> extends SmartListExpression<T, E, U> {
    public ExpenseItemListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public ExpenseItemListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public ExpenseItemExpression<T, U, U> first() {
       return new ExpenseItemExpression(super.first());
    }

    public ExpenseItemExpression<T, U, U> get(int index) {
      return new ExpenseItemExpression(super.get(index));
    }
}