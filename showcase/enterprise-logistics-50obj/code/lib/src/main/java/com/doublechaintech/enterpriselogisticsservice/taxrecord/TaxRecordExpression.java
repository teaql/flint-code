package com.doublechaintech.enterpriselogisticsservice.taxrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.util.function.Function;

public class TaxRecordExpression<T, E, U extends TaxRecord> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public TaxRecordExpression(Expression<T, U> expression){
        super(expression);
    }

    public TaxRecordExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public TaxRecordExpression<T, U, U> updateId(Long id){
        return new TaxRecordExpression(this, $it -> {((TaxRecord)$it).__internalSet("id", id); return this;});
     }

     public TaxRecordExpression<T, U, U> save(UserContext userContext){
        return new TaxRecordExpression(this, $it -> ((TaxRecord)$it).auditAs("Saved by Expression").save(userContext));
     }

     public TaxRecordExpression<T, U, U> save(String intent, UserContext userContext){
        return new TaxRecordExpression(this, $it -> ((TaxRecord)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(TaxRecord::getName);
    }
    public TaxRecordExpression<T, U, U> updateName(String name){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateName(name));
    }

    public Expression<T, String> getTaxCode(){
       return apply(TaxRecord::getTaxCode);
    }
    public TaxRecordExpression<T, U, U> updateTaxCode(String taxCode){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateTaxCode(taxCode));
    }

    public Expression<T, BigDecimal> getTaxAmount(){
       return apply(TaxRecord::getTaxAmount);
    }
    public TaxRecordExpression<T, U, U> updateTaxAmount(BigDecimal taxAmount){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateTaxAmount(taxAmount));
    }

    public Expression<T, String> getCurrency(){
       return apply(TaxRecord::getCurrency);
    }
    public TaxRecordExpression<T, U, U> updateCurrency(String currency){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateCurrency(currency));
    }

    public Expression<T, BigDecimal> getTaxRate(){
       return apply(TaxRecord::getTaxRate);
    }
    public TaxRecordExpression<T, U, U> updateTaxRate(BigDecimal taxRate){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateTaxRate(taxRate));
    }

    public Expression<T, String> getTaxPeriod(){
       return apply(TaxRecord::getTaxPeriod);
    }
    public TaxRecordExpression<T, U, U> updateTaxPeriod(String taxPeriod){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateTaxPeriod(taxPeriod));
    }

    public Expression<T, String> getFilingStatus(){
       return apply(TaxRecord::getFilingStatus);
    }
    public TaxRecordExpression<T, U, U> updateFilingStatus(String filingStatus){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateFilingStatus(filingStatus));
    }

    public InvoiceExpression<T, U, Invoice> getInvoice(){
       return new InvoiceExpression(this, $it ->  ((TaxRecord)$it).getInvoice());
    }

    public TaxRecordExpression<T, U, U> updateInvoice(Invoice invoice){
       return new TaxRecordExpression(this, $it ->  ((TaxRecord)$it).updateInvoice(invoice));
    }

}