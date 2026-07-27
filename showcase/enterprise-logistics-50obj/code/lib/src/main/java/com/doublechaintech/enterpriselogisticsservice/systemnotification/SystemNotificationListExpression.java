package com.doublechaintech.enterpriselogisticsservice.systemnotification;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class SystemNotificationListExpression<T, E, U extends SystemNotification> extends SmartListExpression<T, E, U> {
    public SystemNotificationListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public SystemNotificationListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public SystemNotificationExpression<T, U, U> first() {
       return new SystemNotificationExpression(super.first());
    }

    public SystemNotificationExpression<T, U, U> get(int index) {
      return new SystemNotificationExpression(super.get(index));
    }
}