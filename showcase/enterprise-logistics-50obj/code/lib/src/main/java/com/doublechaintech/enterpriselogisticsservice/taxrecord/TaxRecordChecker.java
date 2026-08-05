package com.doublechaintech.enterpriselogisticsservice.taxrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;

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
      }else if(taxRecord.updateItem()){
      }
      checkName(_ctx, taxRecord.getProperty(TaxRecord.NAME_PROPERTY), newLocation(_parentLocation, TaxRecord.NAME_PROPERTY));
      checkTaxCode(_ctx, taxRecord.getProperty(TaxRecord.TAX_CODE_PROPERTY), newLocation(_parentLocation, TaxRecord.TAX_CODE_PROPERTY));
      checkTaxAmount(_ctx, taxRecord.getProperty(TaxRecord.TAX_AMOUNT_PROPERTY), newLocation(_parentLocation, TaxRecord.TAX_AMOUNT_PROPERTY));
      checkCurrency(_ctx, taxRecord.getProperty(TaxRecord.CURRENCY_PROPERTY), newLocation(_parentLocation, TaxRecord.CURRENCY_PROPERTY));
      checkTaxRate(_ctx, taxRecord.getProperty(TaxRecord.TAX_RATE_PROPERTY), newLocation(_parentLocation, TaxRecord.TAX_RATE_PROPERTY));
      checkTaxPeriod(_ctx, taxRecord.getProperty(TaxRecord.TAX_PERIOD_PROPERTY), newLocation(_parentLocation, TaxRecord.TAX_PERIOD_PROPERTY));
      checkFilingStatus(_ctx, taxRecord.getProperty(TaxRecord.FILING_STATUS_PROPERTY), newLocation(_parentLocation, TaxRecord.FILING_STATUS_PROPERTY));
      checkInvoice(_ctx, taxRecord.getProperty(TaxRecord.INVOICE_PROPERTY), newLocation(_parentLocation, TaxRecord.INVOICE_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkTaxCode(UserContext _ctx, String taxCode, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, taxCode);
    if((taxCode == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, taxCode);

    }
    public void checkTaxAmount(UserContext _ctx, BigDecimal taxAmount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, taxAmount);
    if((taxAmount == null)){
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
    public void checkTaxPeriod(UserContext _ctx, String taxPeriod, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, taxPeriod);
    if((taxPeriod == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, taxPeriod);

    }
    public void checkFilingStatus(UserContext _ctx, String filingStatus, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, filingStatus);
    if((filingStatus == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, filingStatus);

    }
    public void checkInvoice(UserContext _ctx, Invoice invoice, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, invoice);
    if((invoice == null)){
        return;
    }
    new InvoiceChecker().checkAndFix(_ctx, invoice, _parentLocation);
    }
}