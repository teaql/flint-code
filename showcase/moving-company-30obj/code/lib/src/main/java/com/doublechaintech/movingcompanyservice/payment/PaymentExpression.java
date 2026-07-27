package com.doublechaintech.movingcompanyservice.payment;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PaymentExpression<T, E, U extends Payment> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PaymentExpression(Expression<T, U> expression){
        super(expression);
    }

    public PaymentExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PaymentExpression<T, U, U> updateId(Long id){
        return new PaymentExpression(this, $it -> {((Payment)$it).__internalSet("id", id); return this;});
     }

     public PaymentExpression<T, U, U> save(UserContext userContext){
        return new PaymentExpression(this, $it -> ((Payment)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PaymentExpression<T, U, U> save(String intent, UserContext userContext){
        return new PaymentExpression(this, $it -> ((Payment)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, BigDecimal> getAmount(){
       return apply(Payment::getAmount);
    }
    public PaymentExpression<T, U, U> updateAmount(BigDecimal amount){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateAmount(amount));
    }

    public Expression<T, String> getPaymentMethod(){
       return apply(Payment::getPaymentMethod);
    }
    public PaymentExpression<T, U, U> updatePaymentMethod(String paymentMethod){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updatePaymentMethod(paymentMethod));
    }

    public Expression<T, String> getTransactionRef(){
       return apply(Payment::getTransactionRef);
    }
    public PaymentExpression<T, U, U> updateTransactionRef(String transactionRef){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateTransactionRef(transactionRef));
    }

    public Expression<T, LocalDate> getPaymentDate(){
       return apply(Payment::getPaymentDate);
    }
    public PaymentExpression<T, U, U> updatePaymentDate(LocalDate paymentDate){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updatePaymentDate(paymentDate));
    }

    public Expression<T, String> getStatus(){
       return apply(Payment::getStatus);
    }
    public PaymentExpression<T, U, U> updateStatus(String status){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateStatus(status));
    }

    public Expression<T, String> getInvoice(){
       return apply(Payment::getInvoice);
    }
    public PaymentExpression<T, U, U> updateInvoice(String invoice){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateInvoice(invoice));
    }

    public Expression<T, String> getCustomer(){
       return apply(Payment::getCustomer);
    }
    public PaymentExpression<T, U, U> updateCustomer(String customer){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateCustomer(customer));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(Payment::getCreateTime);
    }
    public PaymentExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(Payment::getUpdateTime);
    }
    public PaymentExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new PaymentExpression(this, $it ->  ((Payment)$it).updateUpdateTime(updateTime));
    }

}