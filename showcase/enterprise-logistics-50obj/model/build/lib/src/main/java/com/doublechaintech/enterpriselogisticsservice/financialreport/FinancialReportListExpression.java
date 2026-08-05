package com.doublechaintech.enterpriselogisticsservice.financialreport;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class FinancialReportListExpression<T, E, U extends FinancialReport> extends SmartListExpression<T, E, U> {
    public FinancialReportListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public FinancialReportListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public FinancialReportExpression<T, U, U> first() {
       return new FinancialReportExpression(super.first());
    }

    public FinancialReportExpression<T, U, U> get(int index) {
      return new FinancialReportExpression(super.get(index));
    }
}