package com.doublechaintech.enterpriselogisticsservice.salaryslip;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class SalarySlipListExpression<T, E, U extends SalarySlip> extends SmartListExpression<T, E, U> {
    public SalarySlipListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public SalarySlipListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public SalarySlipExpression<T, U, U> first() {
       return new SalarySlipExpression(super.first());
    }

    public SalarySlipExpression<T, U, U> get(int index) {
      return new SalarySlipExpression(super.get(index));
    }
}