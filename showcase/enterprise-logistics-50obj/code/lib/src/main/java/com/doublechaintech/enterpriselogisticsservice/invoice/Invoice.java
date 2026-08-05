package com.doublechaintech.enterpriselogisticsservice.invoice;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.paymentrecord.PaymentRecord;
import com.doublechaintech.enterpriselogisticsservice.taxrecord.TaxRecord;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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
public class Invoice extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "Invoice";

    public static final String NAME_PROPERTY = "name";
    public static final String CODE_PROPERTY = "code";
    public static final String AMOUNT_PROPERTY = "amount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String STATUS_PROPERTY = "status";
    public static final String ISSUE_DATE_PROPERTY = "issueDate";
    public static final String DUE_DATE_PROPERTY = "dueDate";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String CUSTOMER_PROPERTY = "customer";
    public static final String PAYMENT_RECORD_LIST_PROPERTY = "paymentRecordList";
    public static final String TAX_RECORD_LIST_PROPERTY = "taxRecordList";
    private String name;
    private String code;
    private BigDecimal amount;
    private String currency;
    private String status;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private MovingOrder movingOrder;
    private String customer;
    private SmartList<PaymentRecord> paymentRecordList;
    private SmartList<TaxRecord> taxRecordList;

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
    public String getStatus(){
        return this.status;
    }
    public LocalDate getIssueDate(){
        return this.issueDate;
    }
    public LocalDate getDueDate(){
        return this.dueDate;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public String getCustomer(){
        return this.customer;
    }
    public SmartList<PaymentRecord> getPaymentRecordList(){
        return this.paymentRecordList;
    }
    public SmartList<TaxRecord> getTaxRecordList(){
        return this.taxRecordList;
    }
    public Invoice updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public Invoice updateCode(String code){
        code = (code == null ? null : code.trim());
        if(Objects.equals(this.code, code)){
            return this;
        }
        handleUpdate(CODE_PROPERTY, getCode(), code);
        this.code = code;
        return this;
    }
    public Invoice updateAmount(BigDecimal amount){
        if(Objects.equals(this.amount, amount)){
            return this;
        }
        handleUpdate(AMOUNT_PROPERTY, getAmount(), amount);
        this.amount = amount;
        return this;
    }
    public Invoice updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public Invoice updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public Invoice updateIssueDate(LocalDate issueDate){
        if(Objects.equals(this.issueDate, issueDate)){
            return this;
        }
        handleUpdate(ISSUE_DATE_PROPERTY, getIssueDate(), issueDate);
        this.issueDate = issueDate;
        return this;
    }
    public Invoice updateDueDate(LocalDate dueDate){
        if(Objects.equals(this.dueDate, dueDate)){
            return this;
        }
        handleUpdate(DUE_DATE_PROPERTY, getDueDate(), dueDate);
        this.dueDate = dueDate;
        return this;
    }
    public Invoice updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public Invoice updateCustomer(String customer){
        customer = (customer == null ? null : customer.trim());
        if(Objects.equals(this.customer, customer)){
            return this;
        }
        handleUpdate(CUSTOMER_PROPERTY, getCustomer(), customer);
        this.customer = customer;
        return this;
    }
    public Invoice addPaymentRecord(PaymentRecord paymentRecord){
        if (paymentRecord == null){
            return this;
        }

        if(null == this.paymentRecordList){
            this.paymentRecordList = new SmartList<>();
        }

        this.paymentRecordList.add(paymentRecord);
        paymentRecord.cacheRelation(PaymentRecord.INVOICE_PROPERTY, this);
        return this;
    }
    public Invoice addTaxRecord(TaxRecord taxRecord){
        if (taxRecord == null){
            return this;
        }

        if(null == this.taxRecordList){
            this.taxRecordList = new SmartList<>();
        }

        this.taxRecordList.add(taxRecord);
        taxRecord.cacheRelation(TaxRecord.INVOICE_PROPERTY, this);
        return this;
    }

    public static Invoice refer(Long id){
        Invoice refer = new Invoice();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public Invoice comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<Invoice> auditAs(String action) {
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

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "issueDate": this.issueDate = (LocalDate) value; break;

            case "dueDate": this.dueDate = (LocalDate) value; break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "customer": this.customer = (value == null ? null : ((String)value).trim()); break;

            case "paymentRecordList": this.paymentRecordList = (SmartList<PaymentRecord>) value; break;
            case "taxRecordList": this.taxRecordList = (SmartList<TaxRecord>) value; break;
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
            case "status": return this.status;
            case "issueDate": return this.issueDate;
            case "dueDate": return this.dueDate;
            case "movingOrder": return this.movingOrder;
            case "customer": return this.customer;
            case "paymentRecordList": return this.paymentRecordList;
            case "taxRecordList": return this.taxRecordList;
            default: return super.__internalGet(property);
        }
    }

}