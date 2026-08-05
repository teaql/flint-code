package com.doublechaintech.movingcompanyservice.payment;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PaymentChecker implements Checker<Payment>{

    public String type(){
        return Payment.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Payment payment, ObjectLocation _parentLocation){
        if(needCheck(_ctx, payment)){
            markAsChecked(_ctx, payment);
            doCheck(_ctx, payment, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Payment payment, ObjectLocation _parentLocation){
      if((payment == null)){
         return;
      }
      if(payment.newItem()){
        if(payment.getCreateTime() == null){
           payment.updateCreateTime(java.time.LocalDateTime.now());
        }if(payment.getUpdateTime() == null){
           payment.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(payment.updateItem()){
        payment.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkAmount(_ctx, payment.getProperty(Payment.AMOUNT_PROPERTY), newLocation(_parentLocation, Payment.AMOUNT_PROPERTY));
      checkPaymentMethod(_ctx, payment.getProperty(Payment.PAYMENT_METHOD_PROPERTY), newLocation(_parentLocation, Payment.PAYMENT_METHOD_PROPERTY));
      checkTransactionRef(_ctx, payment.getProperty(Payment.TRANSACTION_REF_PROPERTY), newLocation(_parentLocation, Payment.TRANSACTION_REF_PROPERTY));
      checkPaymentDate(_ctx, payment.getProperty(Payment.PAYMENT_DATE_PROPERTY), newLocation(_parentLocation, Payment.PAYMENT_DATE_PROPERTY));
      checkStatus(_ctx, payment.getProperty(Payment.STATUS_PROPERTY), newLocation(_parentLocation, Payment.STATUS_PROPERTY));
      checkInvoice(_ctx, payment.getProperty(Payment.INVOICE_PROPERTY), newLocation(_parentLocation, Payment.INVOICE_PROPERTY));
      checkCustomer(_ctx, payment.getProperty(Payment.CUSTOMER_PROPERTY), newLocation(_parentLocation, Payment.CUSTOMER_PROPERTY));
      checkCreateTime(_ctx, payment.getProperty(Payment.CREATE_TIME_PROPERTY), newLocation(_parentLocation, Payment.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, payment.getProperty(Payment.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, Payment.UPDATE_TIME_PROPERTY));
    }

    public void checkAmount(UserContext _ctx, BigDecimal amount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, amount);
    if((amount == null)){
        return;
    }
    }
    public void checkPaymentMethod(UserContext _ctx, String paymentMethod, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, paymentMethod);
    if((paymentMethod == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, paymentMethod);

    }
    public void checkTransactionRef(UserContext _ctx, String transactionRef, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, transactionRef);
    if((transactionRef == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, transactionRef);

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
    public void checkInvoice(UserContext _ctx, String invoice, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, invoice);
    if((invoice == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, invoice);

    }
    public void checkCustomer(UserContext _ctx, String customer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customer);
    if((customer == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, customer);

    }
    public void checkCreateTime(UserContext _ctx, LocalDateTime createTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createTime);
    if((createTime == null)){
        return;
    }
    }
    public void checkUpdateTime(UserContext _ctx, LocalDateTime updateTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updateTime);
    if((updateTime == null)){
        return;
    }
    }
}