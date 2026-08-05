package com.doublechaintech.enterpriselogisticsservice.customerloyalty;

import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
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
public class CustomerLoyalty extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "CustomerLoyalty";

    public static final String POINTS_PROPERTY = "points";
    public static final String TIER_PROPERTY = "tier";
    public static final String PRIVATE_CUSTOMER_PROPERTY = "privateCustomer";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private Integer points;
    private String tier;
    private PrivateCustomer privateCustomer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getPoints(){
        return this.points;
    }
    public String getTier(){
        return this.tier;
    }
    public PrivateCustomer getPrivateCustomer(){
        return this.privateCustomer;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public CustomerLoyalty updatePoints(Integer points){
        if(Objects.equals(this.points, points)){
            return this;
        }
        handleUpdate(POINTS_PROPERTY, getPoints(), points);
        this.points = points;
        return this;
    }
    public CustomerLoyalty updateTier(String tier){
        tier = (tier == null ? null : tier.trim());
        if(Objects.equals(this.tier, tier)){
            return this;
        }
        handleUpdate(TIER_PROPERTY, getTier(), tier);
        this.tier = tier;
        return this;
    }
    public CustomerLoyalty updatePrivateCustomer(PrivateCustomer privateCustomer){
        if(Objects.equals(this.privateCustomer, privateCustomer)){
            return this;
        }
        handleUpdate(PRIVATE_CUSTOMER_PROPERTY, getPrivateCustomer(), privateCustomer);
        this.privateCustomer = privateCustomer;
        return this;
    }
    public CustomerLoyalty updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public CustomerLoyalty updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static CustomerLoyalty refer(Long id){
        CustomerLoyalty refer = new CustomerLoyalty();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public CustomerLoyalty comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<CustomerLoyalty> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "points": this.points = (Integer) value; break;

            case "tier": this.tier = (value == null ? null : ((String)value).trim()); break;

            case "privateCustomer": this.privateCustomer = (PrivateCustomer) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "points": return this.points;
            case "tier": return this.tier;
            case "privateCustomer": return this.privateCustomer;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}