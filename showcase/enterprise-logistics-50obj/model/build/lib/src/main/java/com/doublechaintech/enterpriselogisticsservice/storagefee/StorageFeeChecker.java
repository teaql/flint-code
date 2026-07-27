package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StorageFeeChecker implements Checker<StorageFee>{

    public String type(){
        return StorageFee.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, StorageFee storageFee, ObjectLocation _parentLocation){
        if(needCheck(_ctx, storageFee)){
            markAsChecked(_ctx, storageFee);
            doCheck(_ctx, storageFee, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, StorageFee storageFee, ObjectLocation _parentLocation){
      if((storageFee == null)){
         return;
      }
      if(storageFee.newItem()){
        if(storageFee.getCreateTime() == null){
           storageFee.updateCreateTime(java.time.LocalDateTime.now());
        }if(storageFee.getUpdateTime() == null){
           storageFee.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(storageFee.updateItem()){
        storageFee.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkInvoice(_ctx, storageFee.getProperty(StorageFee.INVOICE_PROPERTY), newLocation(_parentLocation, StorageFee.INVOICE_PROPERTY));
      checkFeeAmount(_ctx, storageFee.getProperty(StorageFee.FEE_AMOUNT_PROPERTY), newLocation(_parentLocation, StorageFee.FEE_AMOUNT_PROPERTY));
      checkCurrency(_ctx, storageFee.getProperty(StorageFee.CURRENCY_PROPERTY), newLocation(_parentLocation, StorageFee.CURRENCY_PROPERTY));
      checkPeriodStart(_ctx, storageFee.getProperty(StorageFee.PERIOD_START_PROPERTY), newLocation(_parentLocation, StorageFee.PERIOD_START_PROPERTY));
      checkPeriodEnd(_ctx, storageFee.getProperty(StorageFee.PERIOD_END_PROPERTY), newLocation(_parentLocation, StorageFee.PERIOD_END_PROPERTY));
      checkStatus(_ctx, storageFee.getProperty(StorageFee.STATUS_PROPERTY), newLocation(_parentLocation, StorageFee.STATUS_PROPERTY));
      checkCreateTime(_ctx, storageFee.getProperty(StorageFee.CREATE_TIME_PROPERTY), newLocation(_parentLocation, StorageFee.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, storageFee.getProperty(StorageFee.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, StorageFee.UPDATE_TIME_PROPERTY));
    }

    public void checkInvoice(UserContext _ctx, Invoice invoice, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, invoice);
    if((invoice == null)){
        return;
    }
    new InvoiceChecker().checkAndFix(_ctx, invoice, _parentLocation);
    }
    public void checkFeeAmount(UserContext _ctx, BigDecimal feeAmount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, feeAmount);
    if((feeAmount == null)){
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
    public void checkPeriodStart(UserContext _ctx, String periodStart, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, periodStart);
    if((periodStart == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, periodStart);

    }
    public void checkPeriodEnd(UserContext _ctx, String periodEnd, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, periodEnd);
    if((periodEnd == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, periodEnd);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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