package com.doublechaintech.enterpriselogisticsservice.containerunit;

import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
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
public class ContainerUnit extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "ContainerUnit";

    public static final String STORAGE_CONTAINER_PROPERTY = "storageContainer";
    public static final String UNIT_TYPE_PROPERTY = "unitType";
    public static final String QUANTITY_PROPERTY = "quantity";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private StorageContainer storageContainer;
    private String unitType;
    private Integer quantity;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public StorageContainer getStorageContainer(){
        return this.storageContainer;
    }
    public String getUnitType(){
        return this.unitType;
    }
    public Integer getQuantity(){
        return this.quantity;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public ContainerUnit updateStorageContainer(StorageContainer storageContainer){
        if(Objects.equals(this.storageContainer, storageContainer)){
            return this;
        }
        handleUpdate(STORAGE_CONTAINER_PROPERTY, getStorageContainer(), storageContainer);
        this.storageContainer = storageContainer;
        return this;
    }
    public ContainerUnit updateUnitType(String unitType){
        unitType = (unitType == null ? null : unitType.trim());
        if(Objects.equals(this.unitType, unitType)){
            return this;
        }
        handleUpdate(UNIT_TYPE_PROPERTY, getUnitType(), unitType);
        this.unitType = unitType;
        return this;
    }
    public ContainerUnit updateQuantity(Integer quantity){
        if(Objects.equals(this.quantity, quantity)){
            return this;
        }
        handleUpdate(QUANTITY_PROPERTY, getQuantity(), quantity);
        this.quantity = quantity;
        return this;
    }
    public ContainerUnit updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public ContainerUnit updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static ContainerUnit refer(Long id){
        ContainerUnit refer = new ContainerUnit();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public ContainerUnit comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<ContainerUnit> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "storageContainer": this.storageContainer = (StorageContainer) value; break;

            case "unitType": this.unitType = (value == null ? null : ((String)value).trim()); break;

            case "quantity": this.quantity = (Integer) value; break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "storageContainer": return this.storageContainer;
            case "unitType": return this.unitType;
            case "quantity": return this.quantity;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}