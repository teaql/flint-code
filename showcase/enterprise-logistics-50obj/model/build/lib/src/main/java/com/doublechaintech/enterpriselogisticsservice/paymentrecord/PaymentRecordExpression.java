package com.doublechaintech.enterpriselogisticsservice.paymentrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PaymentRecordExpression<T, E, U extends PaymentRecord> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PaymentRecordExpression(Expression<T, U> expression){
        super(expression);
    }

    public PaymentRecordExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PaymentRecordExpression<T, U, U> updateId(Long id){
        return new PaymentRecordExpression(this, $it -> {((PaymentRecord)$it).__internalSet("id", id); return this;});
     }

     public PaymentRecordExpression<T, U, U> save(UserContext userContext){
        return new PaymentRecordExpression(this, $it -> ((PaymentRecord)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PaymentRecordExpression<T, U, U> save(String intent, UserContext userContext){
        return new PaymentRecordExpression(this, $it -> ((PaymentRecord)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(PaymentRecord::getName);
    }
    public PaymentRecordExpression<T, U, U> updateName(String name){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateName(name));
    }

    public Expression<T, String> getCode(){
       return apply(PaymentRecord::getCode);
    }
    public PaymentRecordExpression<T, U, U> updateCode(String code){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateCode(code));
    }

    public Expression<T, BigDecimal> getAmount(){
       return apply(PaymentRecord::getAmount);
    }
    public PaymentRecordExpression<T, U, U> updateAmount(BigDecimal amount){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateAmount(amount));
    }

    public Expression<T, String> getCurrency(){
       return apply(PaymentRecord::getCurrency);
    }
    public PaymentRecordExpression<T, U, U> updateCurrency(String currency){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateCurrency(currency));
    }

    public Expression<T, String> getStatus(){
       return apply(PaymentRecord::getStatus);
    }
    public PaymentRecordExpression<T, U, U> updateStatus(String status){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(PaymentRecord::getCreatedAt);
    }
    public PaymentRecordExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(PaymentRecord::getUpdatedAt);
    }
    public PaymentRecordExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateUpdatedAt(updatedAt));
    }

    public InvoiceExpression<T, U, Invoice> getInvoice(){
       return new InvoiceExpression(this, $it ->  ((PaymentRecord)$it).getInvoice());
    }

    public PaymentRecordExpression<T, U, U> updateInvoice(Invoice invoice){
       return new PaymentRecordExpression(this, $it ->  ((PaymentRecord)$it).updateInvoice(invoice));
    }

}