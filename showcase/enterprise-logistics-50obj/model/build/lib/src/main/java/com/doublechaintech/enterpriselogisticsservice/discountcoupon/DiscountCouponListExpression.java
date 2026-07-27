package com.doublechaintech.enterpriselogisticsservice.discountcoupon;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class DiscountCouponListExpression<T, E, U extends DiscountCoupon> extends SmartListExpression<T, E, U> {
    public DiscountCouponListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public DiscountCouponListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public DiscountCouponExpression<T, U, U> first() {
       return new DiscountCouponExpression(super.first());
    }

    public DiscountCouponExpression<T, U, U> get(int index) {
      return new DiscountCouponExpression(super.get(index));
    }
}