package com.doublechaintech.enterpriselogisticsservice.servicecontract;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
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
public class ServiceContract extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "ServiceContract";

    public static final String CONTRACT_NUMBER_PROPERTY = "contractNumber";
    public static final String TITLE_PROPERTY = "title";
    public static final String START_DATE_PROPERTY = "startDate";
    public static final String END_DATE_PROPERTY = "endDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String TOTAL_VALUE_PROPERTY = "totalValue";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String CORPORATE_CUSTOMER_PROPERTY = "corporateCustomer";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String contractNumber;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private BigDecimal totalValue;
    private String currency;
    private CorporateCustomer corporateCustomer;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getContractNumber(){
        return this.contractNumber;
    }
    public String getTitle(){
        return this.title;
    }
    public LocalDate getStartDate(){
        return this.startDate;
    }
    public LocalDate getEndDate(){
        return this.endDate;
    }
    public String getStatus(){
        return this.status;
    }
    public BigDecimal getTotalValue(){
        return this.totalValue;
    }
    public String getCurrency(){
        return this.currency;
    }
    public CorporateCustomer getCorporateCustomer(){
        return this.corporateCustomer;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public ServiceContract updateContractNumber(String contractNumber){
        contractNumber = (contractNumber == null ? null : contractNumber.trim());
        if(Objects.equals(this.contractNumber, contractNumber)){
            return this;
        }
        handleUpdate(CONTRACT_NUMBER_PROPERTY, getContractNumber(), contractNumber);
        this.contractNumber = contractNumber;
        return this;
    }
    public ServiceContract updateTitle(String title){
        title = (title == null ? null : title.trim());
        if(Objects.equals(this.title, title)){
            return this;
        }
        handleUpdate(TITLE_PROPERTY, getTitle(), title);
        this.title = title;
        return this;
    }
    public ServiceContract updateStartDate(LocalDate startDate){
        if(Objects.equals(this.startDate, startDate)){
            return this;
        }
        handleUpdate(START_DATE_PROPERTY, getStartDate(), startDate);
        this.startDate = startDate;
        return this;
    }
    public ServiceContract updateEndDate(LocalDate endDate){
        if(Objects.equals(this.endDate, endDate)){
            return this;
        }
        handleUpdate(END_DATE_PROPERTY, getEndDate(), endDate);
        this.endDate = endDate;
        return this;
    }
    public ServiceContract updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public ServiceContract updateTotalValue(BigDecimal totalValue){
        if(Objects.equals(this.totalValue, totalValue)){
            return this;
        }
        handleUpdate(TOTAL_VALUE_PROPERTY, getTotalValue(), totalValue);
        this.totalValue = totalValue;
        return this;
    }
    public ServiceContract updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public ServiceContract updateCorporateCustomer(CorporateCustomer corporateCustomer){
        if(Objects.equals(this.corporateCustomer, corporateCustomer)){
            return this;
        }
        handleUpdate(CORPORATE_CUSTOMER_PROPERTY, getCorporateCustomer(), corporateCustomer);
        this.corporateCustomer = corporateCustomer;
        return this;
    }
    public ServiceContract updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public ServiceContract updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static ServiceContract refer(Long id){
        ServiceContract refer = new ServiceContract();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public ServiceContract comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<ServiceContract> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "contractNumber": this.contractNumber = (value == null ? null : ((String)value).trim()); break;

            case "title": this.title = (value == null ? null : ((String)value).trim()); break;

            case "startDate": this.startDate = (LocalDate) value; break;

            case "endDate": this.endDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "totalValue": this.totalValue = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "corporateCustomer": this.corporateCustomer = (CorporateCustomer) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "contractNumber": return this.contractNumber;
            case "title": return this.title;
            case "startDate": return this.startDate;
            case "endDate": return this.endDate;
            case "status": return this.status;
            case "totalValue": return this.totalValue;
            case "currency": return this.currency;
            case "corporateCustomer": return this.corporateCustomer;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}