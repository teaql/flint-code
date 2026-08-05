package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
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
public class CustomsDeclaration extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "CustomsDeclaration";

    public static final String DECLARATION_NUMBER_PROPERTY = "declarationNumber";
    public static final String ORIGIN_COUNTRY_PROPERTY = "originCountry";
    public static final String DESTINATION_COUNTRY_PROPERTY = "destinationCountry";
    public static final String TOTAL_VALUE_PROPERTY = "totalValue";
    public static final String STATUS_PROPERTY = "status";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String declarationNumber;
    private String originCountry;
    private String destinationCountry;
    private BigDecimal totalValue;
    private String status;
    private MovingOrder movingOrder;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getDeclarationNumber(){
        return this.declarationNumber;
    }
    public String getOriginCountry(){
        return this.originCountry;
    }
    public String getDestinationCountry(){
        return this.destinationCountry;
    }
    public BigDecimal getTotalValue(){
        return this.totalValue;
    }
    public String getStatus(){
        return this.status;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public CustomsDeclaration updateDeclarationNumber(String declarationNumber){
        declarationNumber = (declarationNumber == null ? null : declarationNumber.trim());
        if(Objects.equals(this.declarationNumber, declarationNumber)){
            return this;
        }
        handleUpdate(DECLARATION_NUMBER_PROPERTY, getDeclarationNumber(), declarationNumber);
        this.declarationNumber = declarationNumber;
        return this;
    }
    public CustomsDeclaration updateOriginCountry(String originCountry){
        originCountry = (originCountry == null ? null : originCountry.trim());
        if(Objects.equals(this.originCountry, originCountry)){
            return this;
        }
        handleUpdate(ORIGIN_COUNTRY_PROPERTY, getOriginCountry(), originCountry);
        this.originCountry = originCountry;
        return this;
    }
    public CustomsDeclaration updateDestinationCountry(String destinationCountry){
        destinationCountry = (destinationCountry == null ? null : destinationCountry.trim());
        if(Objects.equals(this.destinationCountry, destinationCountry)){
            return this;
        }
        handleUpdate(DESTINATION_COUNTRY_PROPERTY, getDestinationCountry(), destinationCountry);
        this.destinationCountry = destinationCountry;
        return this;
    }
    public CustomsDeclaration updateTotalValue(BigDecimal totalValue){
        if(Objects.equals(this.totalValue, totalValue)){
            return this;
        }
        handleUpdate(TOTAL_VALUE_PROPERTY, getTotalValue(), totalValue);
        this.totalValue = totalValue;
        return this;
    }
    public CustomsDeclaration updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public CustomsDeclaration updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public CustomsDeclaration updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public CustomsDeclaration updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static CustomsDeclaration refer(Long id){
        CustomsDeclaration refer = new CustomsDeclaration();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public CustomsDeclaration comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<CustomsDeclaration> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "declarationNumber": this.declarationNumber = (value == null ? null : ((String)value).trim()); break;

            case "originCountry": this.originCountry = (value == null ? null : ((String)value).trim()); break;

            case "destinationCountry": this.destinationCountry = (value == null ? null : ((String)value).trim()); break;

            case "totalValue": this.totalValue = (BigDecimal) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "declarationNumber": return this.declarationNumber;
            case "originCountry": return this.originCountry;
            case "destinationCountry": return this.destinationCountry;
            case "totalValue": return this.totalValue;
            case "status": return this.status;
            case "movingOrder": return this.movingOrder;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}