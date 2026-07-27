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

    public static final String ITEM_ID_PROPERTY = "itemId";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String CATEGORY_PROPERTY = "category";
    public static final String WEIGHT_KG_PROPERTY = "weightKg";
    public static final String VOLUME_M3_PROPERTY = "volumeM3";
    public static final String VALUE_PROPERTY = "value";
    public static final String FRAGILE_PROPERTY = "fragile";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    private String itemId;
    private MovingOrder movingOrder;
    private String description;
    private String category;
    private BigDecimal weightKg;
    private BigDecimal volumeM3;
    private BigDecimal value;
    private Boolean fragile;
    private LocalDateTime createTime;

    public String getItemId(){
        return this.itemId;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCategory(){
        return this.category;
    }
    public BigDecimal getWeightKg(){
        return this.weightKg;
    }
    public BigDecimal getVolumeM3(){
        return this.volumeM3;
    }
    public BigDecimal getValue(){
        return this.value;
    }
    public Boolean isFragile(){
        return this.fragile;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public CargoItem updateItemId(String itemId){
        itemId = (itemId == null ? null : itemId.trim());
        if(Objects.equals(this.itemId, itemId)){
            return this;
        }
        handleUpdate(ITEM_ID_PROPERTY, getItemId(), itemId);
        this.itemId = itemId;
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
    public CargoItem updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public CargoItem updateCategory(String category){
        category = (category == null ? null : category.trim());
        if(Objects.equals(this.category, category)){
            return this;
        }
        handleUpdate(CATEGORY_PROPERTY, getCategory(), category);
        this.category = category;
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
    public CargoItem updateValue(BigDecimal value){
        if(Objects.equals(this.value, value)){
            return this;
        }
        handleUpdate(VALUE_PROPERTY, getValue(), value);
        this.value = value;
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
    public CargoItem updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
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
            case "itemId": this.itemId = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "category": this.category = (value == null ? null : ((String)value).trim()); break;

            case "weightKg": this.weightKg = (BigDecimal) value; break;

            case "volumeM3": this.volumeM3 = (BigDecimal) value; break;

            case "value": this.value = (BigDecimal) value; break;

            case "fragile": this.fragile = (Boolean) value; break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "itemId": return this.itemId;
            case "movingOrder": return this.movingOrder;
            case "description": return this.description;
            case "category": return this.category;
            case "weightKg": return this.weightKg;
            case "volumeM3": return this.volumeM3;
            case "value": return this.value;
            case "fragile": return this.fragile;
            case "createTime": return this.createTime;
            default: return super.__internalGet(property);
        }
    }

}