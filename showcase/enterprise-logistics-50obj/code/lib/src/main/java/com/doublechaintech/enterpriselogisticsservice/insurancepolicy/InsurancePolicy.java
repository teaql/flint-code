package com.doublechaintech.enterpriselogisticsservice.insurancepolicy;

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
public class InsurancePolicy extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "InsurancePolicy";

    public static final String POLICY_NUMBER_PROPERTY = "policyNumber";
    public static final String PROVIDER_PROPERTY = "provider";
    public static final String COVERAGE_AMOUNT_PROPERTY = "coverageAmount";
    public static final String PREMIUM_PROPERTY = "premium";
    public static final String START_DATE_PROPERTY = "startDate";
    public static final String END_DATE_PROPERTY = "endDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String policyNumber;
    private String provider;
    private BigDecimal coverageAmount;
    private BigDecimal premium;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getPolicyNumber(){
        return this.policyNumber;
    }
    public String getProvider(){
        return this.provider;
    }
    public BigDecimal getCoverageAmount(){
        return this.coverageAmount;
    }
    public BigDecimal getPremium(){
        return this.premium;
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
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public InsurancePolicy updatePolicyNumber(String policyNumber){
        policyNumber = (policyNumber == null ? null : policyNumber.trim());
        if(Objects.equals(this.policyNumber, policyNumber)){
            return this;
        }
        handleUpdate(POLICY_NUMBER_PROPERTY, getPolicyNumber(), policyNumber);
        this.policyNumber = policyNumber;
        return this;
    }
    public InsurancePolicy updateProvider(String provider){
        provider = (provider == null ? null : provider.trim());
        if(Objects.equals(this.provider, provider)){
            return this;
        }
        handleUpdate(PROVIDER_PROPERTY, getProvider(), provider);
        this.provider = provider;
        return this;
    }
    public InsurancePolicy updateCoverageAmount(BigDecimal coverageAmount){
        if(Objects.equals(this.coverageAmount, coverageAmount)){
            return this;
        }
        handleUpdate(COVERAGE_AMOUNT_PROPERTY, getCoverageAmount(), coverageAmount);
        this.coverageAmount = coverageAmount;
        return this;
    }
    public InsurancePolicy updatePremium(BigDecimal premium){
        if(Objects.equals(this.premium, premium)){
            return this;
        }
        handleUpdate(PREMIUM_PROPERTY, getPremium(), premium);
        this.premium = premium;
        return this;
    }
    public InsurancePolicy updateStartDate(LocalDate startDate){
        if(Objects.equals(this.startDate, startDate)){
            return this;
        }
        handleUpdate(START_DATE_PROPERTY, getStartDate(), startDate);
        this.startDate = startDate;
        return this;
    }
    public InsurancePolicy updateEndDate(LocalDate endDate){
        if(Objects.equals(this.endDate, endDate)){
            return this;
        }
        handleUpdate(END_DATE_PROPERTY, getEndDate(), endDate);
        this.endDate = endDate;
        return this;
    }
    public InsurancePolicy updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public InsurancePolicy updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public InsurancePolicy updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static InsurancePolicy refer(Long id){
        InsurancePolicy refer = new InsurancePolicy();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public InsurancePolicy comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<InsurancePolicy> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "policyNumber": this.policyNumber = (value == null ? null : ((String)value).trim()); break;

            case "provider": this.provider = (value == null ? null : ((String)value).trim()); break;

            case "coverageAmount": this.coverageAmount = (BigDecimal) value; break;

            case "premium": this.premium = (BigDecimal) value; break;

            case "startDate": this.startDate = (LocalDate) value; break;

            case "endDate": this.endDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "policyNumber": return this.policyNumber;
            case "provider": return this.provider;
            case "coverageAmount": return this.coverageAmount;
            case "premium": return this.premium;
            case "startDate": return this.startDate;
            case "endDate": return this.endDate;
            case "status": return this.status;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}