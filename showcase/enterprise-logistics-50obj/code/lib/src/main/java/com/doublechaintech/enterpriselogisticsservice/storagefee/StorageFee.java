package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class StorageFee extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "StorageFee";

    public static final String INVOICE_PROPERTY = "invoice";
    public static final String FEE_AMOUNT_PROPERTY = "feeAmount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String PERIOD_START_PROPERTY = "periodStart";
    public static final String PERIOD_END_PROPERTY = "periodEnd";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private Invoice invoice;
    private BigDecimal feeAmount;
    private String currency;
    private String periodStart;
    private String periodEnd;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Invoice getInvoice(){
        return this.invoice;
    }
    public BigDecimal getFeeAmount(){
        return this.feeAmount;
    }
    public String getCurrency(){
        return this.currency;
    }
    public String getPeriodStart(){
        return this.periodStart;
    }
    public String getPeriodEnd(){
        return this.periodEnd;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public StorageFee updateInvoice(Invoice invoice){
        if(Objects.equals(this.invoice, invoice)){
            return this;
        }
        handleUpdate(INVOICE_PROPERTY, getInvoice(), invoice);
        this.invoice = invoice;
        return this;
    }
    public StorageFee updateFeeAmount(BigDecimal feeAmount){
        if(Objects.equals(this.feeAmount, feeAmount)){
            return this;
        }
        handleUpdate(FEE_AMOUNT_PROPERTY, getFeeAmount(), feeAmount);
        this.feeAmount = feeAmount;
        return this;
    }
    public StorageFee updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public StorageFee updatePeriodStart(String periodStart){
        periodStart = (periodStart == null ? null : periodStart.trim());
        if(Objects.equals(this.periodStart, periodStart)){
            return this;
        }
        handleUpdate(PERIOD_START_PROPERTY, getPeriodStart(), periodStart);
        this.periodStart = periodStart;
        return this;
    }
    public StorageFee updatePeriodEnd(String periodEnd){
        periodEnd = (periodEnd == null ? null : periodEnd.trim());
        if(Objects.equals(this.periodEnd, periodEnd)){
            return this;
        }
        handleUpdate(PERIOD_END_PROPERTY, getPeriodEnd(), periodEnd);
        this.periodEnd = periodEnd;
        return this;
    }
    public StorageFee updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public StorageFee updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public StorageFee updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static StorageFee refer(Long id){
        StorageFee refer = new StorageFee();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public StorageFee comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<StorageFee> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "invoice": this.invoice = (Invoice) value; break;

            case "feeAmount": this.feeAmount = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "periodStart": this.periodStart = (value == null ? null : ((String)value).trim()); break;

            case "periodEnd": this.periodEnd = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "invoice": return this.invoice;
            case "feeAmount": return this.feeAmount;
            case "currency": return this.currency;
            case "periodStart": return this.periodStart;
            case "periodEnd": return this.periodEnd;
            case "status": return this.status;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}