package com.doublechaintech.enterpriselogisticsservice.cargoitem;

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
public class CargoItem extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "CargoItem";

    public static final String ITEM_CODE_PROPERTY = "itemCode";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String WEIGHT_KG_PROPERTY = "weightKg";
    public static final String VOLUME_M3_PROPERTY = "volumeM3";
    public static final String FRAGILE_PROPERTY = "fragile";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String itemCode;
    private String description;
    private BigDecimal weightKg;
    private BigDecimal volumeM3;
    private Boolean fragile;
    private MovingOrder movingOrder;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getItemCode(){
        return this.itemCode;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getWeightKg(){
        return this.weightKg;
    }
    public BigDecimal getVolumeM3(){
        return this.volumeM3;
    }
    public Boolean isFragile(){
        return this.fragile;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public CargoItem updateItemCode(String itemCode){
        itemCode = (itemCode == null ? null : itemCode.trim());
        if(Objects.equals(this.itemCode, itemCode)){
            return this;
        }
        handleUpdate(ITEM_CODE_PROPERTY, getItemCode(), itemCode);
        this.itemCode = itemCode;
        return this;
    }
    public CargoItem updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public CargoItem updateWeightKg(BigDecimal weightKg){
        if(Objects.equals(this.weightKg, weightKg)){
            return this;
        }
        handleUpdate(WEIGHT_KG_PROPERTY, getWeightKg(), weightKg);
        this.weightKg = weightKg;
        return this;
    }
    public CargoItem updateVolumeM3(BigDecimal volumeM3){
        if(Objects.equals(this.volumeM3, volumeM3)){
            return this;
        }
        handleUpdate(VOLUME_M3_PROPERTY, getVolumeM3(), volumeM3);
        this.volumeM3 = volumeM3;
        return this;
    }
    public CargoItem updateFragile(Boolean fragile){
        if(Objects.equals(this.fragile, fragile)){
            return this;
        }
        handleUpdate(FRAGILE_PROPERTY, isFragile(), fragile);
        this.fragile = fragile;
        return this;
    }
    public CargoItem updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public CargoItem updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public CargoItem updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static CargoItem refer(Long id){
        CargoItem refer = new CargoItem();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public CargoItem comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<CargoItem> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "itemCode": this.itemCode = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "weightKg": this.weightKg = (BigDecimal) value; break;

            case "volumeM3": this.volumeM3 = (BigDecimal) value; break;

            case "fragile": this.fragile = (Boolean) value; break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "itemCode": return this.itemCode;
            case "description": return this.description;
            case "weightKg": return this.weightKg;
            case "volumeM3": return this.volumeM3;
            case "fragile": return this.fragile;
            case "movingOrder": return this.movingOrder;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}