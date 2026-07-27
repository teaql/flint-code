package com.doublechaintech.enterpriselogisticsservice.paymentrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRecordChecker implements Checker<PaymentRecord>{

    public String type(){
        return PaymentRecord.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, PaymentRecord paymentRecord, ObjectLocation _parentLocation){
        if(needCheck(_ctx, paymentRecord)){
            markAsChecked(_ctx, paymentRecord);
            doCheck(_ctx, paymentRecord, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, PaymentRecord paymentRecord, ObjectLocation _parentLocation){
      if((paymentRecord == null)){
         return;
      }
      if(paymentRecord.newItem()){
      }else if(paymentRecord.updateItem()){
      }
      checkName(_ctx, paymentRecord.getProperty(PaymentRecord.NAME_PROPERTY), newLocation(_parentLocation, PaymentRecord.NAME_PROPERTY));
      checkReferenceCode(_ctx, paymentRecord.getProperty(PaymentRecord.REFERENCE_CODE_PROPERTY), newLocation(_parentLocation, PaymentRecord.REFERENCE_CODE_PROPERTY));
      checkAmount(_ctx, paymentRecord.getProperty(PaymentRecord.AMOUNT_PROPERTY), newLocation(_parentLocation, PaymentRecord.AMOUNT_PROPERTY));
      checkCurrency(_ctx, paymentRecord.getProperty(PaymentRecord.CURRENCY_PROPERTY), newLocation(_parentLocation, PaymentRecord.CURRENCY_PROPERTY));
      checkPaymentMethod(_ctx, paymentRecord.getProperty(PaymentRecord.PAYMENT_METHOD_PROPERTY), newLocation(_parentLocation, PaymentRecord.PAYMENT_METHOD_PROPERTY));
      checkPaymentDate(_ctx, paymentRecord.getProperty(PaymentRecord.PAYMENT_DATE_PROPERTY), newLocation(_parentLocation, PaymentRecord.PAYMENT_DATE_PROPERTY));
      checkStatus(_ctx, paymentRecord.getProperty(PaymentRecord.STATUS_PROPERTY), newLocation(_parentLocation, PaymentRecord.STATUS_PROPERTY));
      checkInvoice(_ctx, paymentRecord.getProperty(PaymentRecord.INVOICE_PROPERTY), newLocation(_parentLocation, PaymentRecord.INVOICE_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkReferenceCode(UserContext _ctx, String referenceCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, referenceCode);
    if((referenceCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, referenceCode);

    }
    public void checkAmount(UserContext _ctx, BigDecimal amount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, amount);
    if((amount == null)){
        return;
    }
    }
    public void checkCurrency(UserContext _ctx, String currency, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, currency);
    if((currency == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, currency);

    }
    public void checkPaymentMethod(UserContext _ctx, String paymentMethod, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, paymentMethod);
    if((paymentMethod == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, paymentMethod);

    }
    public void checkPaymentDate(UserContext _ctx, LocalDate paymentDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, paymentDate);
    if((paymentDate == null)){
        return;
    }
    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkInvoice(UserContext _ctx, Invoice invoice, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, invoice);
    if((invoice == null)){
        return;
    }
    new InvoiceChecker().checkAndFix(_ctx, invoice, _parentLocation);
    }
}