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
    public static final String DISCOUNT_PERCENTAGE_PROPERTY = "discountPercentage";
    public static final String MAX_USES_PROPERTY = "maxUses";
    public static final String CURRENT_USES_PROPERTY = "currentUses";
    public static final String EXPIRY_DATE_PROPERTY = "expiryDate";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String code;
    private BigDecimal discountPercentage;
    private Integer maxUses;
    private Integer currentUses;
    private LocalDate expiryDate;
    private String status;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getCode(){
        return this.code;
    }
    public BigDecimal getDiscountPercentage(){
        return this.discountPercentage;
    }
    public Integer getMaxUses(){
        return this.maxUses;
    }
    public Integer getCurrentUses(){
        return this.currentUses;
    }
    public LocalDate getExpiryDate(){
        return this.expiryDate;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
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
    public DiscountCoupon updateDiscountPercentage(BigDecimal discountPercentage){
        if(Objects.equals(this.discountPercentage, discountPercentage)){
            return this;
        }
        handleUpdate(DISCOUNT_PERCENTAGE_PROPERTY, getDiscountPercentage(), discountPercentage);
        this.discountPercentage = discountPercentage;
        return this;
    }
    public DiscountCoupon updateMaxUses(Integer maxUses){
        if(Objects.equals(this.maxUses, maxUses)){
            return this;
        }
        handleUpdate(MAX_USES_PROPERTY, getMaxUses(), maxUses);
        this.maxUses = maxUses;
        return this;
    }
    public DiscountCoupon updateCurrentUses(Integer currentUses){
        if(Objects.equals(this.currentUses, currentUses)){
            return this;
        }
        handleUpdate(CURRENT_USES_PROPERTY, getCurrentUses(), currentUses);
        this.currentUses = currentUses;
        return this;
    }
    public DiscountCoupon updateExpiryDate(LocalDate expiryDate){
        if(Objects.equals(this.expiryDate, expiryDate)){
            return this;
        }
        handleUpdate(EXPIRY_DATE_PROPERTY, getExpiryDate(), expiryDate);
        this.expiryDate = expiryDate;
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
    public DiscountCoupon updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
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

            case "discountPercentage": this.discountPercentage = (BigDecimal) value; break;

            case "maxUses": this.maxUses = (Integer) value; break;

            case "currentUses": this.currentUses = (Integer) value; break;

            case "expiryDate": this.expiryDate = (LocalDate) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "code": return this.code;
            case "discountPercentage": return this.discountPercentage;
            case "maxUses": return this.maxUses;
            case "currentUses": return this.currentUses;
            case "expiryDate": return this.expiryDate;
            case "status": return this.status;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}