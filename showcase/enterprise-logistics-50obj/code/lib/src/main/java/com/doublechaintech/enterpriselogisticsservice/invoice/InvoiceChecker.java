package com.doublechaintech.enterpriselogisticsservice.invoice;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecordChecker;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecordChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceChecker implements Checker<Invoice>{

    public String type(){
        return Invoice.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Invoice invoice, ObjectLocation _parentLocation){
        if(needCheck(_ctx, invoice)){
            markAsChecked(_ctx, invoice);
            doCheck(_ctx, invoice, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Invoice invoice, ObjectLocation _parentLocation){
      if((invoice == null)){
         return;
      }
      if(invoice.newItem()){
      }else if(invoice.updateItem()){
      }
      checkName(_ctx, invoice.getProperty(Invoice.NAME_PROPERTY), newLocation(_parentLocation, Invoice.NAME_PROPERTY));
      checkCode(_ctx, invoice.getProperty(Invoice.CODE_PROPERTY), newLocation(_parentLocation, Invoice.CODE_PROPERTY));
      checkAmount(_ctx, invoice.getProperty(Invoice.AMOUNT_PROPERTY), newLocation(_parentLocation, Invoice.AMOUNT_PROPERTY));
      checkCurrency(_ctx, invoice.getProperty(Invoice.CURRENCY_PROPERTY), newLocation(_parentLocation, Invoice.CURRENCY_PROPERTY));
      checkStatus(_ctx, invoice.getProperty(Invoice.STATUS_PROPERTY), newLocation(_parentLocation, Invoice.STATUS_PROPERTY));
      checkIssueDate(_ctx, invoice.getProperty(Invoice.ISSUE_DATE_PROPERTY), newLocation(_parentLocation, Invoice.ISSUE_DATE_PROPERTY));
      checkDueDate(_ctx, invoice.getProperty(Invoice.DUE_DATE_PROPERTY), newLocation(_parentLocation, Invoice.DUE_DATE_PROPERTY));
      checkMovingOrder(_ctx, invoice.getProperty(Invoice.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, Invoice.MOVING_ORDER_PROPERTY));
      checkCustomer(_ctx, invoice.getProperty(Invoice.CUSTOMER_PROPERTY), newLocation(_parentLocation, Invoice.CUSTOMER_PROPERTY));
      for(int i = 0; invoice.getPaymentRecordList() != null && i < invoice.getPaymentRecordList().size(); i++){
         PaymentRecord paymentRecord = invoice.getPaymentRecordList().get(i);
         new PaymentRecordChecker().checkAndFix(_ctx, paymentRecord, newLocation(_parentLocation, Invoice.PAYMENT_RECORD_LIST_PROPERTY, i));
      }
      for(int i = 0; invoice.getTaxRecordList() != null && i < invoice.getTaxRecordList().size(); i++){
         TaxRecord taxRecord = invoice.getTaxRecordList().get(i);
         new TaxRecordChecker().checkAndFix(_ctx, taxRecord, newLocation(_parentLocation, Invoice.TAX_RECORD_LIST_PROPERTY, i));
      }
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
    public void checkIssueDate(UserContext _ctx, LocalDate issueDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, issueDate);
    if((issueDate == null)){
        return;
    }
    }
    public void checkDueDate(UserContext _ctx, LocalDate dueDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, dueDate);
    if((dueDate == null)){
        return;
    }
    }
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
    }
    public void checkCustomer(UserContext _ctx, String customer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customer);
    if((customer == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, customer);

    }
}