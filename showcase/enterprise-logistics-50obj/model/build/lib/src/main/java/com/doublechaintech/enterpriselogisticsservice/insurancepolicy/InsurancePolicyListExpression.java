package com.doublechaintech.enterpriselogisticsservice.insurancepolicy;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class InsurancePolicyListExpression<T, E, U extends InsurancePolicy> extends SmartListExpression<T, E, U> {
    public InsurancePolicyListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public InsurancePolicyListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public InsurancePolicyExpression<T, U, U> first() {
       return new InsurancePolicyExpression(super.first());
    }

    public InsurancePolicyExpression<T, U, U> get(int index) {
      return new InsurancePolicyExpression(super.get(index));
    }
}