package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class StorageFeeExpression<T, E, U extends StorageFee> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public StorageFeeExpression(Expression<T, U> expression){
        super(expression);
    }

    public StorageFeeExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public StorageFeeExpression<T, U, U> updateId(Long id){
        return new StorageFeeExpression(this, $it -> {((StorageFee)$it).__internalSet("id", id); return this;});
     }

     public StorageFeeExpression<T, U, U> save(UserContext userContext){
        return new StorageFeeExpression(this, $it -> ((StorageFee)$it).auditAs("Saved by Expression").save(userContext));
     }

     public StorageFeeExpression<T, U, U> save(String intent, UserContext userContext){
        return new StorageFeeExpression(this, $it -> ((StorageFee)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public InvoiceExpression<T, U, Invoice> getInvoice(){
       return new InvoiceExpression(this, $it ->  ((StorageFee)$it).getInvoice());
    }

    public StorageFeeExpression<T, U, U> updateInvoice(Invoice invoice){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateInvoice(invoice));
    }

    public Expression<T, BigDecimal> getFeeAmount(){
       return apply(StorageFee::getFeeAmount);
    }
    public StorageFeeExpression<T, U, U> updateFeeAmount(BigDecimal feeAmount){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateFeeAmount(feeAmount));
    }

    public Expression<T, String> getCurrency(){
       return apply(StorageFee::getCurrency);
    }
    public StorageFeeExpression<T, U, U> updateCurrency(String currency){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateCurrency(currency));
    }

    public Expression<T, String> getPeriodStart(){
       return apply(StorageFee::getPeriodStart);
    }
    public StorageFeeExpression<T, U, U> updatePeriodStart(String periodStart){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updatePeriodStart(periodStart));
    }

    public Expression<T, String> getPeriodEnd(){
       return apply(StorageFee::getPeriodEnd);
    }
    public StorageFeeExpression<T, U, U> updatePeriodEnd(String periodEnd){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updatePeriodEnd(periodEnd));
    }

    public Expression<T, String> getStatus(){
       return apply(StorageFee::getStatus);
    }
    public StorageFeeExpression<T, U, U> updateStatus(String status){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(StorageFee::getCreateTime);
    }
    public StorageFeeExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(StorageFee::getUpdateTime);
    }
    public StorageFeeExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateUpdateTime(updateTime));
    }

}