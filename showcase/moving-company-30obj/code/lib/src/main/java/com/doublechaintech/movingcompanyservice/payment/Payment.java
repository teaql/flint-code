package com.doublechaintech.movingcompanyservice.payment;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Payment extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "Payment";

    public static final String AMOUNT_PROPERTY = "amount";
    public static final String PAYMENT_METHOD_PROPERTY = "paymentMethod";
    public static final String TRANSACTION_REF_PROPERTY = "transactionRef";
    public static final String PAYMENT_DATE_PROPERTY = "paymentDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String INVOICE_PROPERTY = "invoice";
    public static final String CUSTOMER_PROPERTY = "customer";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private BigDecimal amount;
    private String paymentMethod;
    private String transactionRef;
    private LocalDate paymentDate;
    private String status;
    private String invoice;
    private String customer;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public BigDecimal getAmount(){
        return this.amount;
    }
    public String getPaymentMethod(){
        return this.paymentMethod;
    }
    public String getTransactionRef(){
        return this.transactionRef;
    }
    public LocalDate getPaymentDate(){
        return this.paymentDate;
    }
    public String getStatus(){
        return this.status;
    }
    public String getInvoice(){
        return this.invoice;
    }
    public String getCustomer(){
        return this.customer;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public Payment updateAmount(BigDecimal amount){
        if(Objects.equals(this.amount, amount)){
            return this;
        }
        handleUpdate(AMOUNT_PROPERTY, getAmount(), amount);
        this.amount = amount;
        return this;
    }
    public Payment updatePaymentMethod(String paymentMethod){
        paymentMethod = (paymentMethod == null ? null : paymentMethod.trim());
        if(Objects.equals(this.paymentMethod, paymentMethod)){
            return this;
        }
        handleUpdate(PAYMENT_METHOD_PROPERTY, getPaymentMethod(), paymentMethod);
        this.paymentMethod = paymentMethod;
        return this;
    }
    public Payment updateTransactionRef(String transactionRef){
        transactionRef = (transactionRef == null ? null : transactionRef.trim());
        if(Objects.equals(this.transactionRef, transactionRef)){
            return this;
        }
        handleUpdate(TRANSACTION_REF_PROPERTY, getTransactionRef(), transactionRef);
        this.transactionRef = transactionRef;
        return this;
    }
    public Payment updatePaymentDate(LocalDate paymentDate){
        if(Objects.equals(this.paymentDate, paymentDate)){
            return this;
        }
        handleUpdate(PAYMENT_DATE_PROPERTY, getPaymentDate(), paymentDate);
        this.paymentDate = paymentDate;
        return this;
    }
    public Payment updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public Payment updateInvoice(String invoice){
        invoice = (invoice == null ? null : invoice.trim());
        if(Objects.equals(this.invoice, invoice)){
            return this;
        }
        handleUpdate(INVOICE_PROPERTY, getInvoice(), invoice);
        this.invoice = invoice;
        return this;
    }
    public Payment updateCustomer(String customer){
        customer = (customer == null ? null : customer.trim());
        if(Objects.equals(this.customer, customer)){
            return this;
        }
        handleUpdate(CUSTOMER_PROPERTY, getCustomer(), customer);
        this.customer = customer;
        return this;
    }
    public Payment updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public Payment updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static Payment refer(Long id){
        Payment refer = new Payment();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public Payment comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<Payment> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "amount": this.amount = (BigDecimal) value; break;

            case "paymentMethod": this.paymentMethod = (value == null ? null : ((String)value).trim()); break;

            case "transactionRef": this.transactionRef = (value == null ? null : ((String)value).trim()); break;

            case "paymentDate": this.paymentDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "invoice": this.invoice = (value == null ? null : ((String)value).trim()); break;

            case "customer": this.customer = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "amount": return this.amount;
            case "paymentMethod": return this.paymentMethod;
            case "transactionRef": return this.transactionRef;
            case "paymentDate": return this.paymentDate;
            case "status": return this.status;
            case "invoice": return this.invoice;
            case "customer": return this.customer;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}