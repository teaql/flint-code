package com.doublechaintech.enterpriselogisticsservice.taxrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class TaxRecord extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "TaxRecord";

    public static final String NAME_PROPERTY = "name";
    public static final String TAX_CODE_PROPERTY = "taxCode";
    public static final String TAX_AMOUNT_PROPERTY = "taxAmount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String TAX_RATE_PROPERTY = "taxRate";
    public static final String TAX_PERIOD_PROPERTY = "taxPeriod";
    public static final String FILING_STATUS_PROPERTY = "filingStatus";
    public static final String INVOICE_PROPERTY = "invoice";
    private String name;
    private String taxCode;
    private BigDecimal taxAmount;
    private String currency;
    private BigDecimal taxRate;
    private String taxPeriod;
    private String filingStatus;
    private Invoice invoice;

    public String getName(){
        return this.name;
    }
    public String getTaxCode(){
        return this.taxCode;
    }
    public BigDecimal getTaxAmount(){
        return this.taxAmount;
    }
    public String getCurrency(){
        return this.currency;
    }
    public BigDecimal getTaxRate(){
        return this.taxRate;
    }
    public String getTaxPeriod(){
        return this.taxPeriod;
    }
    public String getFilingStatus(){
        return this.filingStatus;
    }
    public Invoice getInvoice(){
        return this.invoice;
    }
    public TaxRecord updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public TaxRecord updateTaxCode(String taxCode){
        taxCode = (taxCode == null ? null : taxCode.trim());
        if(Objects.equals(this.taxCode, taxCode)){
            return this;
        }
        handleUpdate(TAX_CODE_PROPERTY, getTaxCode(), taxCode);
        this.taxCode = taxCode;
        return this;
    }
    public TaxRecord updateTaxAmount(BigDecimal taxAmount){
        if(Objects.equals(this.taxAmount, taxAmount)){
            return this;
        }
        handleUpdate(TAX_AMOUNT_PROPERTY, getTaxAmount(), taxAmount);
        this.taxAmount = taxAmount;
        return this;
    }
    public TaxRecord updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public TaxRecord updateTaxRate(BigDecimal taxRate){
        if(Objects.equals(this.taxRate, taxRate)){
            return this;
        }
        handleUpdate(TAX_RATE_PROPERTY, getTaxRate(), taxRate);
        this.taxRate = taxRate;
        return this;
    }
    public TaxRecord updateTaxPeriod(String taxPeriod){
        taxPeriod = (taxPeriod == null ? null : taxPeriod.trim());
        if(Objects.equals(this.taxPeriod, taxPeriod)){
            return this;
        }
        handleUpdate(TAX_PERIOD_PROPERTY, getTaxPeriod(), taxPeriod);
        this.taxPeriod = taxPeriod;
        return this;
    }
    public TaxRecord updateFilingStatus(String filingStatus){
        filingStatus = (filingStatus == null ? null : filingStatus.trim());
        if(Objects.equals(this.filingStatus, filingStatus)){
            return this;
        }
        handleUpdate(FILING_STATUS_PROPERTY, getFilingStatus(), filingStatus);
        this.filingStatus = filingStatus;
        return this;
    }
    public TaxRecord updateInvoice(Invoice invoice){
        if(Objects.equals(this.invoice, invoice)){
            return this;
        }
        handleUpdate(INVOICE_PROPERTY, getInvoice(), invoice);
        this.invoice = invoice;
        return this;
    }

    public static TaxRecord refer(Long id){
        TaxRecord refer = new TaxRecord();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public TaxRecord comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<TaxRecord> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "taxCode": this.taxCode = (value == null ? null : ((String)value).trim()); break;

            case "taxAmount": this.taxAmount = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "taxRate": this.taxRate = (BigDecimal) value; break;

            case "taxPeriod": this.taxPeriod = (value == null ? null : ((String)value).trim()); break;

            case "filingStatus": this.filingStatus = (value == null ? null : ((String)value).trim()); break;

            case "invoice": this.invoice = (Invoice) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "taxCode": return this.taxCode;
            case "taxAmount": return this.taxAmount;
            case "currency": return this.currency;
            case "taxRate": return this.taxRate;
            case "taxPeriod": return this.taxPeriod;
            case "filingStatus": return this.filingStatus;
            case "invoice": return this.invoice;
            default: return super.__internalGet(property);
        }
    }

}