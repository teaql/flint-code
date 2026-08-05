package com.doublechaintech.enterpriselogisticsservice.paymentrecord;

import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class PaymentRecord extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "PaymentRecord";

    public static final String NAME_PROPERTY = "name";
    public static final String REFERENCE_CODE_PROPERTY = "referenceCode";
    public static final String AMOUNT_PROPERTY = "amount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String PAYMENT_METHOD_PROPERTY = "paymentMethod";
    public static final String PAYMENT_DATE_PROPERTY = "paymentDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String INVOICE_PROPERTY = "invoice";
    private String name;
    private String referenceCode;
    private BigDecimal amount;
    private String currency;
    private String paymentMethod;
    private LocalDate paymentDate;
    private String status;
    private Invoice invoice;

    public String getName(){
        return this.name;
    }
    public String getReferenceCode(){
        return this.referenceCode;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public String getCurrency(){
        return this.currency;
    }
    public String getPaymentMethod(){
        return this.paymentMethod;
    }
    public LocalDate getPaymentDate(){
        return this.paymentDate;
    }
    public String getStatus(){
        return this.status;
    }
    public Invoice getInvoice(){
        return this.invoice;
    }
    public PaymentRecord updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public PaymentRecord updateReferenceCode(String referenceCode){
        referenceCode = (referenceCode == null ? null : referenceCode.trim());
        if(Objects.equals(this.referenceCode, referenceCode)){
            return this;
        }
        handleUpdate(REFERENCE_CODE_PROPERTY, getReferenceCode(), referenceCode);
        this.referenceCode = referenceCode;
        return this;
    }
    public PaymentRecord updateAmount(BigDecimal amount){
        if(Objects.equals(this.amount, amount)){
            return this;
        }
        handleUpdate(AMOUNT_PROPERTY, getAmount(), amount);
        this.amount = amount;
        return this;
    }
    public PaymentRecord updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public PaymentRecord updatePaymentMethod(String paymentMethod){
        paymentMethod = (paymentMethod == null ? null : paymentMethod.trim());
        if(Objects.equals(this.paymentMethod, paymentMethod)){
            return this;
        }
        handleUpdate(PAYMENT_METHOD_PROPERTY, getPaymentMethod(), paymentMethod);
        this.paymentMethod = paymentMethod;
        return this;
    }
    public PaymentRecord updatePaymentDate(LocalDate paymentDate){
        if(Objects.equals(this.paymentDate, paymentDate)){
            return this;
        }
        handleUpdate(PAYMENT_DATE_PROPERTY, getPaymentDate(), paymentDate);
        this.paymentDate = paymentDate;
        return this;
    }
    public PaymentRecord updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public PaymentRecord updateInvoice(Invoice invoice){
        if(Objects.equals(this.invoice, invoice)){
            return this;
        }
        handleUpdate(INVOICE_PROPERTY, getInvoice(), invoice);
        this.invoice = invoice;
        return this;
    }

    public static PaymentRecord refer(Long id){
        PaymentRecord refer = new PaymentRecord();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public PaymentRecord comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<PaymentRecord> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "referenceCode": this.referenceCode = (value == null ? null : ((String)value).trim()); break;

            case "amount": this.amount = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "paymentMethod": this.paymentMethod = (value == null ? null : ((String)value).trim()); break;

            case "paymentDate": this.paymentDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "invoice": this.invoice = (Invoice) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "referenceCode": return this.referenceCode;
            case "amount": return this.amount;
            case "currency": return this.currency;
            case "paymentMethod": return this.paymentMethod;
            case "paymentDate": return this.paymentDate;
            case "status": return this.status;
            case "invoice": return this.invoice;
            default: return super.__internalGet(property);
        }
    }

}