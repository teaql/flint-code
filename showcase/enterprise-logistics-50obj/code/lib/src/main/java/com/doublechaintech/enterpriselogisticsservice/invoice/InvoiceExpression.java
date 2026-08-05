package com.doublechaintech.enterpriselogisticsservice.invoice;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordListExpression;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

public class InvoiceExpression<T, E, U extends Invoice> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public InvoiceExpression(Expression<T, U> expression){
        super(expression);
    }

    public InvoiceExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public InvoiceExpression<T, U, U> updateId(Long id){
        return new InvoiceExpression(this, $it -> {((Invoice)$it).__internalSet("id", id); return this;});
     }

     public InvoiceExpression<T, U, U> save(UserContext userContext){
        return new InvoiceExpression(this, $it -> ((Invoice)$it).auditAs("Saved by Expression").save(userContext));
     }

     public InvoiceExpression<T, U, U> save(String intent, UserContext userContext){
        return new InvoiceExpression(this, $it -> ((Invoice)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(Invoice::getName);
    }
    public InvoiceExpression<T, U, U> updateName(String name){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateName(name));
    }

    public Expression<T, String> getCode(){
       return apply(Invoice::getCode);
    }
    public InvoiceExpression<T, U, U> updateCode(String code){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateCode(code));
    }

    public Expression<T, BigDecimal> getAmount(){
       return apply(Invoice::getAmount);
    }
    public InvoiceExpression<T, U, U> updateAmount(BigDecimal amount){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateAmount(amount));
    }

    public Expression<T, String> getCurrency(){
       return apply(Invoice::getCurrency);
    }
    public InvoiceExpression<T, U, U> updateCurrency(String currency){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateCurrency(currency));
    }

    public Expression<T, String> getStatus(){
       return apply(Invoice::getStatus);
    }
    public InvoiceExpression<T, U, U> updateStatus(String status){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateStatus(status));
    }

    public Expression<T, LocalDate> getIssueDate(){
       return apply(Invoice::getIssueDate);
    }
    public InvoiceExpression<T, U, U> updateIssueDate(LocalDate issueDate){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateIssueDate(issueDate));
    }

    public Expression<T, LocalDate> getDueDate(){
       return apply(Invoice::getDueDate);
    }
    public InvoiceExpression<T, U, U> updateDueDate(LocalDate dueDate){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateDueDate(dueDate));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((Invoice)$it).getMovingOrder());
    }

    public InvoiceExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateMovingOrder(movingOrder));
    }

    public Expression<T, String> getCustomer(){
       return apply(Invoice::getCustomer);
    }
    public InvoiceExpression<T, U, U> updateCustomer(String customer){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).updateCustomer(customer));
    }

    public PaymentRecordListExpression<T, U, PaymentRecord> getPaymentRecordList(){
        return new PaymentRecordListExpression(this, $it ->  ((Invoice)$it).getPaymentRecordList());
    }
    public TaxRecordListExpression<T, U, TaxRecord> getTaxRecordList(){
        return new TaxRecordListExpression(this, $it ->  ((Invoice)$it).getTaxRecordList());
    }
    public InvoiceExpression<T, U, U> addPaymentRecord(PaymentRecord paymentRecord){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).addPaymentRecord(paymentRecord));
    }
    public InvoiceExpression<T, U, U> addTaxRecord(TaxRecord taxRecord){
       return new InvoiceExpression(this, $it ->  ((Invoice)$it).addTaxRecord(taxRecord));
    }
}