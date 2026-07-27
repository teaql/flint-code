package com.doublechaintech.enterpriselogisticsservice.auditlog;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class AuditLogListExpression<T, E, U extends AuditLog> extends SmartListExpression<T, E, U> {
    public AuditLogListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public AuditLogListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public AuditLogExpression<T, U, U> first() {
       return new AuditLogExpression(super.first());
    }

    public AuditLogExpression<T, U, U> get(int index) {
      return new AuditLogExpression(super.get(index));
    }
}