package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class CorporateCustomerListExpression<T, E, U extends CorporateCustomer> extends SmartListExpression<T, E, U> {
    public CorporateCustomerListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public CorporateCustomerListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public CorporateCustomerExpression<T, U, U> first() {
       return new CorporateCustomerExpression(super.first());
    }

    public CorporateCustomerExpression<T, U, U> get(int index) {
      return new CorporateCustomerExpression(super.get(index));
    }
}