package com.doublechaintech.enterpriselogisticsservice.servicequote;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
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
public class ServiceQuote extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "ServiceQuote";

    public static final String QUOTE_NUMBER_PROPERTY = "quoteNumber";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String ESTIMATED_COST_PROPERTY = "estimatedCost";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String STATUS_PROPERTY = "status";
    public static final String VALID_UNTIL_PROPERTY = "validUntil";
    public static final String PRIVATE_CUSTOMER_PROPERTY = "privateCustomer";
    public static final String CORPORATE_CUSTOMER_PROPERTY = "corporateCustomer";
    private String quoteNumber;
    private String description;
    private BigDecimal estimatedCost;
    private String currency;
    private String status;
    private LocalDate validUntil;
    private PrivateCustomer privateCustomer;
    private CorporateCustomer corporateCustomer;

    public String getQuoteNumber(){
        return this.quoteNumber;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getEstimatedCost(){
        return this.estimatedCost;
    }
    public String getCurrency(){
        return this.currency;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDate getValidUntil(){
        return this.validUntil;
    }
    public PrivateCustomer getPrivateCustomer(){
        return this.privateCustomer;
    }
    public CorporateCustomer getCorporateCustomer(){
        return this.corporateCustomer;
    }
    public ServiceQuote updateQuoteNumber(String quoteNumber){
        quoteNumber = (quoteNumber == null ? null : quoteNumber.trim());
        if(Objects.equals(this.quoteNumber, quoteNumber)){
            return this;
        }
        handleUpdate(QUOTE_NUMBER_PROPERTY, getQuoteNumber(), quoteNumber);
        this.quoteNumber = quoteNumber;
        return this;
    }
    public ServiceQuote updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public ServiceQuote updateEstimatedCost(BigDecimal estimatedCost){
        if(Objects.equals(this.estimatedCost, estimatedCost)){
            return this;
        }
        handleUpdate(ESTIMATED_COST_PROPERTY, getEstimatedCost(), estimatedCost);
        this.estimatedCost = estimatedCost;
        return this;
    }
    public ServiceQuote updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public ServiceQuote updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public ServiceQuote updateValidUntil(LocalDate validUntil){
        if(Objects.equals(this.validUntil, validUntil)){
            return this;
        }
        handleUpdate(VALID_UNTIL_PROPERTY, getValidUntil(), validUntil);
        this.validUntil = validUntil;
        return this;
    }
    public ServiceQuote updatePrivateCustomer(PrivateCustomer privateCustomer){
        if(Objects.equals(this.privateCustomer, privateCustomer)){
            return this;
        }
        handleUpdate(PRIVATE_CUSTOMER_PROPERTY, getPrivateCustomer(), privateCustomer);
        this.privateCustomer = privateCustomer;
        return this;
    }
    public ServiceQuote updateCorporateCustomer(CorporateCustomer corporateCustomer){
        if(Objects.equals(this.corporateCustomer, corporateCustomer)){
            return this;
        }
        handleUpdate(CORPORATE_CUSTOMER_PROPERTY, getCorporateCustomer(), corporateCustomer);
        this.corporateCustomer = corporateCustomer;
        return this;
    }

    public static ServiceQuote refer(Long id){
        ServiceQuote refer = new ServiceQuote();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public ServiceQuote comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<ServiceQuote> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "quoteNumber": this.quoteNumber = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "estimatedCost": this.estimatedCost = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "validUntil": this.validUntil = (LocalDate) value; break;

            case "privateCustomer": this.privateCustomer = (PrivateCustomer) value; break;

            case "corporateCustomer": this.corporateCustomer = (CorporateCustomer) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "quoteNumber": return this.quoteNumber;
            case "description": return this.description;
            case "estimatedCost": return this.estimatedCost;
            case "currency": return this.currency;
            case "status": return this.status;
            case "validUntil": return this.validUntil;
            case "privateCustomer": return this.privateCustomer;
            case "corporateCustomer": return this.corporateCustomer;
            default: return super.__internalGet(property);
        }
    }

}