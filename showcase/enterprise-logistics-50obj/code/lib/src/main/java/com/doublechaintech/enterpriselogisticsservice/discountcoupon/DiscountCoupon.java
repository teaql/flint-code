package com.doublechaintech.enterpriselogisticsservice.discountcoupon;

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
public class DiscountCoupon extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "DiscountCoupon";

    public static final String CODE_PROPERTY = "code";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String DISCOUNT_PERCENTAGE_PROPERTY = "discountPercentage";
    public static final String MIN_ORDER_AMOUNT_PROPERTY = "minOrderAmount";
    public static final String MAX_DISCOUNT_AMOUNT_PROPERTY = "maxDiscountAmount";
    public static final String USAGE_LIMIT_PROPERTY = "usageLimit";
    public static final String USED_COUNT_PROPERTY = "usedCount";
    public static final String START_DATE_PROPERTY = "startDate";
    public static final String END_DATE_PROPERTY = "endDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String code;
    private String description;
    private BigDecimal discountPercentage;
    private BigDecimal minOrderAmount;
    private BigDecimal maxDiscountAmount;
    private Integer usageLimit;
    private Integer usedCount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getCode(){
        return this.code;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getDiscountPercentage(){
        return this.discountPercentage;
    }
    public BigDecimal getMinOrderAmount(){
        return this.minOrderAmount;
    }
    public BigDecimal getMaxDiscountAmount(){
        return this.maxDiscountAmount;
    }
    public Integer getUsageLimit(){
        return this.usageLimit;
    }
    public Integer getUsedCount(){
        return this.usedCount;
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
    public DiscountCoupon updateCode(String code){
        code = (code == null ? null : code.trim());
        if(Objects.equals(this.code, code)){
            return this;
        }
        handleUpdate(CODE_PROPERTY, getCode(), code);
        this.code = code;
        return this;
    }
    public DiscountCoupon updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public DiscountCoupon updateDiscountPercentage(BigDecimal discountPercentage){
        if(Objects.equals(this.discountPercentage, discountPercentage)){
            return this;
        }
        handleUpdate(DISCOUNT_PERCENTAGE_PROPERTY, getDiscountPercentage(), discountPercentage);
        this.discountPercentage = discountPercentage;
        return this;
    }
    public DiscountCoupon updateMinOrderAmount(BigDecimal minOrderAmount){
        if(Objects.equals(this.minOrderAmount, minOrderAmount)){
            return this;
        }
        handleUpdate(MIN_ORDER_AMOUNT_PROPERTY, getMinOrderAmount(), minOrderAmount);
        this.minOrderAmount = minOrderAmount;
        return this;
    }
    public DiscountCoupon updateMaxDiscountAmount(BigDecimal maxDiscountAmount){
        if(Objects.equals(this.maxDiscountAmount, maxDiscountAmount)){
            return this;
        }
        handleUpdate(MAX_DISCOUNT_AMOUNT_PROPERTY, getMaxDiscountAmount(), maxDiscountAmount);
        this.maxDiscountAmount = maxDiscountAmount;
        return this;
    }
    public DiscountCoupon updateUsageLimit(Integer usageLimit){
        if(Objects.equals(this.usageLimit, usageLimit)){
            return this;
        }
        handleUpdate(USAGE_LIMIT_PROPERTY, getUsageLimit(), usageLimit);
        this.usageLimit = usageLimit;
        return this;
    }
    public DiscountCoupon updateUsedCount(Integer usedCount){
        if(Objects.equals(this.usedCount, usedCount)){
            return this;
        }
        handleUpdate(USED_COUNT_PROPERTY, getUsedCount(), usedCount);
        this.usedCount = usedCount;
        return this;
    }
    public DiscountCoupon updateStartDate(LocalDate startDate){
        if(Objects.equals(this.startDate, startDate)){
            return this;
        }
        handleUpdate(START_DATE_PROPERTY, getStartDate(), startDate);
        this.startDate = startDate;
        return this;
    }
    public DiscountCoupon updateEndDate(LocalDate endDate){
        if(Objects.equals(this.endDate, endDate)){
            return this;
        }
        handleUpdate(END_DATE_PROPERTY, getEndDate(), endDate);
        this.endDate = endDate;
        return this;
    }
    public DiscountCoupon updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public DiscountCoupon updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public DiscountCoupon updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static DiscountCoupon refer(Long id){
        DiscountCoupon refer = new DiscountCoupon();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public DiscountCoupon comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<DiscountCoupon> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "code": this.code = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "discountPercentage": this.discountPercentage = (BigDecimal) value; break;

            case "minOrderAmount": this.minOrderAmount = (BigDecimal) value; break;

            case "maxDiscountAmount": this.maxDiscountAmount = (BigDecimal) value; break;

            case "usageLimit": this.usageLimit = (Integer) value; break;

            case "usedCount": this.usedCount = (Integer) value; break;

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
            case "code": return this.code;
            case "description": return this.description;
            case "discountPercentage": return this.discountPercentage;
            case "minOrderAmount": return this.minOrderAmount;
            case "maxDiscountAmount": return this.maxDiscountAmount;
            case "usageLimit": return this.usageLimit;
            case "usedCount": return this.usedCount;
            case "startDate": return this.startDate;
            case "endDate": return this.endDate;
            case "status": return this.status;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}