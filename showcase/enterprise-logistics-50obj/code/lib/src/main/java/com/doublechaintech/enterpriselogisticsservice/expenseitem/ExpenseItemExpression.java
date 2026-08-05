package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

public class ExpenseItemExpression<T, E, U extends ExpenseItem> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public ExpenseItemExpression(Expression<T, U> expression){
        super(expression);
    }

    public ExpenseItemExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public ExpenseItemExpression<T, U, U> updateId(Long id){
        return new ExpenseItemExpression(this, $it -> {((ExpenseItem)$it).__internalSet("id", id); return this;});
     }

     public ExpenseItemExpression<T, U, U> save(UserContext userContext){
        return new ExpenseItemExpression(this, $it -> ((ExpenseItem)$it).auditAs("Saved by Expression").save(userContext));
     }

     public ExpenseItemExpression<T, U, U> save(String intent, UserContext userContext){
        return new ExpenseItemExpression(this, $it -> ((ExpenseItem)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(ExpenseItem::getName);
    }
    public ExpenseItemExpression<T, U, U> updateName(String name){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateName(name));
    }

    public Expression<T, String> getDescription(){
       return apply(ExpenseItem::getDescription);
    }
    public ExpenseItemExpression<T, U, U> updateDescription(String description){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateDescription(description));
    }

    public Expression<T, BigDecimal> getAmount(){
       return apply(ExpenseItem::getAmount);
    }
    public ExpenseItemExpression<T, U, U> updateAmount(BigDecimal amount){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateAmount(amount));
    }

    public Expression<T, String> getCurrency(){
       return apply(ExpenseItem::getCurrency);
    }
    public ExpenseItemExpression<T, U, U> updateCurrency(String currency){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateCurrency(currency));
    }

    public Expression<T, String> getExpenseType(){
       return apply(ExpenseItem::getExpenseType);
    }
    public ExpenseItemExpression<T, U, U> updateExpenseType(String expenseType){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateExpenseType(expenseType));
    }

    public Expression<T, LocalDate> getExpenseDate(){
       return apply(ExpenseItem::getExpenseDate);
    }
    public ExpenseItemExpression<T, U, U> updateExpenseDate(LocalDate expenseDate){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateExpenseDate(expenseDate));
    }

    public Expression<T, String> getEmployee(){
       return apply(ExpenseItem::getEmployee);
    }
    public ExpenseItemExpression<T, U, U> updateEmployee(String employee){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateEmployee(employee));
    }

    public Expression<T, String> getStatus(){
       return apply(ExpenseItem::getStatus);
    }
    public ExpenseItemExpression<T, U, U> updateStatus(String status){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateStatus(status));
    }

}