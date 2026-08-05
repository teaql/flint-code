package com.doublechaintech.enterpriselogisticsservice.taxrecord;

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
public class TaxRecord extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "TaxRecord";

    public static final String NAME_PROPERTY = "name";
    public static final String CODE_PROPERTY = "code";
    public static final String AMOUNT_PROPERTY = "amount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String TAX_RATE_PROPERTY = "taxRate";
    public static final String TAX_TYPE_PROPERTY = "taxType";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String INVOICE_PROPERTY = "invoice";
    private String name;
    private String code;
    private BigDecimal amount;
    private String currency;
    private BigDecimal taxRate;
    private String taxType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Invoice invoice;

    public String getName(){
        return this.name;
    }
    public String getCode(){
        return this.code;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public String getCurrency(){
        return this.currency;
    }
    public BigDecimal getTaxRate(){
        return this.taxRate;
    }
    public String getTaxType(){
        return this.taxType;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
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
    public TaxRecord updateCode(String code){
        code = (code == null ? null : code.trim());
        if(Objects.equals(this.code, code)){
            return this;
        }
        handleUpdate(CODE_PROPERTY, getCode(), code);
        this.code = code;
        return this;
    }
    public TaxRecord updateAmount(BigDecimal amount){
        if(Objects.equals(this.amount, amount)){
            return this;
        }
        handleUpdate(AMOUNT_PROPERTY, getAmount(), amount);
        this.amount = amount;
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
    public TaxRecord updateTaxType(String taxType){
        taxType = (taxType == null ? null : taxType.trim());
        if(Objects.equals(this.taxType, taxType)){
            return this;
        }
        handleUpdate(TAX_TYPE_PROPERTY, getTaxType(), taxType);
        this.taxType = taxType;
        return this;
    }
    public TaxRecord updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public TaxRecord updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
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

            case "code": this.code = (value == null ? null : ((String)value).trim()); break;

            case "amount": this.amount = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "taxRate": this.taxRate = (BigDecimal) value; break;

            case "taxType": this.taxType = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "invoice": this.invoice = (Invoice) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "code": return this.code;
            case "amount": return this.amount;
            case "currency": return this.currency;
            case "taxRate": return this.taxRate;
            case "taxType": return this.taxType;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "invoice": return this.invoice;
            default: return super.__internalGet(property);
        }
    }

}