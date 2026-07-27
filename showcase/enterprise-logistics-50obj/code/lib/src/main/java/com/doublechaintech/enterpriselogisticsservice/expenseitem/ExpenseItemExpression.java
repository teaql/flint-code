package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public Expression<T, String> getCode(){
       return apply(ExpenseItem::getCode);
    }
    public ExpenseItemExpression<T, U, U> updateCode(String code){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateCode(code));
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

    public Expression<T, String> getCategory(){
       return apply(ExpenseItem::getCategory);
    }
    public ExpenseItemExpression<T, U, U> updateCategory(String category){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateCategory(category));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(ExpenseItem::getCreatedAt);
    }
    public ExpenseItemExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(ExpenseItem::getUpdatedAt);
    }
    public ExpenseItemExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateUpdatedAt(updatedAt));
    }

    public StaffMemberExpression<T, U, StaffMember> getStaffMember(){
       return new StaffMemberExpression(this, $it ->  ((ExpenseItem)$it).getStaffMember());
    }

    public ExpenseItemExpression<T, U, U> updateStaffMember(StaffMember staffMember){
       return new ExpenseItemExpression(this, $it ->  ((ExpenseItem)$it).updateStaffMember(staffMember));
    }

}