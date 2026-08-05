package com.doublechaintech.enterpriselogisticsservice.claimsrecord;

import com.doublechaintech.enterpriselogisticsservice.insurancepolicy.InsurancePolicy;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
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
public class ClaimsRecord extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "ClaimsRecord";

    public static final String CLAIM_NUMBER_PROPERTY = "claimNumber";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String CLAIM_AMOUNT_PROPERTY = "claimAmount";
    public static final String STATUS_PROPERTY = "status";
    public static final String RESOLUTION_DATE_PROPERTY = "resolutionDate";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String INSURANCE_POLICY_PROPERTY = "insurancePolicy";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String claimNumber;
    private String description;
    private BigDecimal claimAmount;
    private String status;
    private LocalDate resolutionDate;
    private MovingOrder movingOrder;
    private InsurancePolicy insurancePolicy;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getClaimNumber(){
        return this.claimNumber;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getClaimAmount(){
        return this.claimAmount;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDate getResolutionDate(){
        return this.resolutionDate;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public InsurancePolicy getInsurancePolicy(){
        return this.insurancePolicy;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public ClaimsRecord updateClaimNumber(String claimNumber){
        claimNumber = (claimNumber == null ? null : claimNumber.trim());
        if(Objects.equals(this.claimNumber, claimNumber)){
            return this;
        }
        handleUpdate(CLAIM_NUMBER_PROPERTY, getClaimNumber(), claimNumber);
        this.claimNumber = claimNumber;
        return this;
    }
    public ClaimsRecord updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public ClaimsRecord updateClaimAmount(BigDecimal claimAmount){
        if(Objects.equals(this.claimAmount, claimAmount)){
            return this;
        }
        handleUpdate(CLAIM_AMOUNT_PROPERTY, getClaimAmount(), claimAmount);
        this.claimAmount = claimAmount;
        return this;
    }
    public ClaimsRecord updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public ClaimsRecord updateResolutionDate(LocalDate resolutionDate){
        if(Objects.equals(this.resolutionDate, resolutionDate)){
            return this;
        }
        handleUpdate(RESOLUTION_DATE_PROPERTY, getResolutionDate(), resolutionDate);
        this.resolutionDate = resolutionDate;
        return this;
    }
    public ClaimsRecord updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public ClaimsRecord updateInsurancePolicy(InsurancePolicy insurancePolicy){
        if(Objects.equals(this.insurancePolicy, insurancePolicy)){
            return this;
        }
        handleUpdate(INSURANCE_POLICY_PROPERTY, getInsurancePolicy(), insurancePolicy);
        this.insurancePolicy = insurancePolicy;
        return this;
    }
    public ClaimsRecord updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public ClaimsRecord updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static ClaimsRecord refer(Long id){
        ClaimsRecord refer = new ClaimsRecord();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public ClaimsRecord comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<ClaimsRecord> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "claimNumber": this.claimNumber = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "claimAmount": this.claimAmount = (BigDecimal) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "resolutionDate": this.resolutionDate = (LocalDate) value; break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "insurancePolicy": this.insurancePolicy = (InsurancePolicy) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "claimNumber": return this.claimNumber;
            case "description": return this.description;
            case "claimAmount": return this.claimAmount;
            case "status": return this.status;
            case "resolutionDate": return this.resolutionDate;
            case "movingOrder": return this.movingOrder;
            case "insurancePolicy": return this.insurancePolicy;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}