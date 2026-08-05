package com.doublechaintech.enterpriselogisticsservice.field;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.util.function.Function;

public class FieldExpression<T, E, U extends Field> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public FieldExpression(Expression<T, U> expression){
        super(expression);
    }

    public FieldExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public FieldExpression<T, U, U> updateId(Long id){
        return new FieldExpression(this, $it -> {((Field)$it).__internalSet("id", id); return this;});
     }

     public FieldExpression<T, U, U> save(UserContext userContext){
        return new FieldExpression(this, $it -> ((Field)$it).auditAs("Saved by Expression").save(userContext));
     }

     public FieldExpression<T, U, U> save(String intent, UserContext userContext){
        return new FieldExpression(this, $it -> ((Field)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(Field::getName);
    }
    public FieldExpression<T, U, U> updateName(String name){
       return new FieldExpression(this, $it ->  ((Field)$it).updateName(name));
    }

}