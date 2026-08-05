package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class MarketingRoiListExpression<T, E, U extends MarketingRoi> extends SmartListExpression<T, E, U> {
    public MarketingRoiListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public MarketingRoiListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public MarketingRoiExpression<T, U, U> first() {
       return new MarketingRoiExpression(super.first());
    }

    public MarketingRoiExpression<T, U, U> get(int index) {
      return new MarketingRoiExpression(super.get(index));
    }
}