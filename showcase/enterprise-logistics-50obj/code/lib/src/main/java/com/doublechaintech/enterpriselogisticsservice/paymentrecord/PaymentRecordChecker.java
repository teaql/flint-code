package com.doublechaintech.enterpriselogisticsservice.paymentrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        if(paymentRecord.getCreatedAt() == null){
           paymentRecord.updateCreatedAt(java.time.LocalDateTime.now());
        }if(paymentRecord.getUpdatedAt() == null){
           paymentRecord.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(paymentRecord.updateItem()){
        paymentRecord.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, paymentRecord.getProperty(PaymentRecord.NAME_PROPERTY), newLocation(_parentLocation, PaymentRecord.NAME_PROPERTY));
      checkCode(_ctx, paymentRecord.getProperty(PaymentRecord.CODE_PROPERTY), newLocation(_parentLocation, PaymentRecord.CODE_PROPERTY));
      checkAmount(_ctx, paymentRecord.getProperty(PaymentRecord.AMOUNT_PROPERTY), newLocation(_parentLocation, PaymentRecord.AMOUNT_PROPERTY));
      checkCurrency(_ctx, paymentRecord.getProperty(PaymentRecord.CURRENCY_PROPERTY), newLocation(_parentLocation, PaymentRecord.CURRENCY_PROPERTY));
      checkStatus(_ctx, paymentRecord.getProperty(PaymentRecord.STATUS_PROPERTY), newLocation(_parentLocation, PaymentRecord.STATUS_PROPERTY));
      checkCreatedAt(_ctx, paymentRecord.getProperty(PaymentRecord.CREATED_AT_PROPERTY), newLocation(_parentLocation, PaymentRecord.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, paymentRecord.getProperty(PaymentRecord.UPDATED_AT_PROPERTY), newLocation(_parentLocation, PaymentRecord.UPDATED_AT_PROPERTY));
      checkInvoice(_ctx, paymentRecord.getProperty(PaymentRecord.INVOICE_PROPERTY), newLocation(_parentLocation, PaymentRecord.INVOICE_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkCode(UserContext _ctx, String code, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, code);
    if((code == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, code);

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
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
    public void checkInvoice(UserContext _ctx, Invoice invoice, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, invoice);
    if((invoice == null)){
        return;
    }
    new InvoiceChecker().checkAndFix(_ctx, invoice, _parentLocation);
    }
}