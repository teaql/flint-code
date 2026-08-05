package com.doublechaintech.enterpriselogisticsservice.taxrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TaxRecordChecker implements Checker<TaxRecord>{

    public String type(){
        return TaxRecord.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, TaxRecord taxRecord, ObjectLocation _parentLocation){
        if(needCheck(_ctx, taxRecord)){
            markAsChecked(_ctx, taxRecord);
            doCheck(_ctx, taxRecord, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, TaxRecord taxRecord, ObjectLocation _parentLocation){
      if((taxRecord == null)){
         return;
      }
      if(taxRecord.newItem()){
        if(taxRecord.getCreatedAt() == null){
           taxRecord.updateCreatedAt(java.time.LocalDateTime.now());
        }if(taxRecord.getUpdatedAt() == null){
           taxRecord.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(taxRecord.updateItem()){
        taxRecord.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, taxRecord.getProperty(TaxRecord.NAME_PROPERTY), newLocation(_parentLocation, TaxRecord.NAME_PROPERTY));
      checkCode(_ctx, taxRecord.getProperty(TaxRecord.CODE_PROPERTY), newLocation(_parentLocation, TaxRecord.CODE_PROPERTY));
      checkAmount(_ctx, taxRecord.getProperty(TaxRecord.AMOUNT_PROPERTY), newLocation(_parentLocation, TaxRecord.AMOUNT_PROPERTY));
      checkCurrency(_ctx, taxRecord.getProperty(TaxRecord.CURRENCY_PROPERTY), newLocation(_parentLocation, TaxRecord.CURRENCY_PROPERTY));
      checkTaxRate(_ctx, taxRecord.getProperty(TaxRecord.TAX_RATE_PROPERTY), newLocation(_parentLocation, TaxRecord.TAX_RATE_PROPERTY));
      checkTaxType(_ctx, taxRecord.getProperty(TaxRecord.TAX_TYPE_PROPERTY), newLocation(_parentLocation, TaxRecord.TAX_TYPE_PROPERTY));
      checkCreatedAt(_ctx, taxRecord.getProperty(TaxRecord.CREATED_AT_PROPERTY), newLocation(_parentLocation, TaxRecord.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, taxRecord.getProperty(TaxRecord.UPDATED_AT_PROPERTY), newLocation(_parentLocation, TaxRecord.UPDATED_AT_PROPERTY));
      checkInvoice(_ctx, taxRecord.getProperty(TaxRecord.INVOICE_PROPERTY), newLocation(_parentLocation, TaxRecord.INVOICE_PROPERTY));
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
    public void checkTaxRate(UserContext _ctx, BigDecimal taxRate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, taxRate);
    if((taxRate == null)){
        return;
    }
    }
    public void checkTaxType(UserContext _ctx, String taxType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, taxType);
    if((taxType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, taxType);

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