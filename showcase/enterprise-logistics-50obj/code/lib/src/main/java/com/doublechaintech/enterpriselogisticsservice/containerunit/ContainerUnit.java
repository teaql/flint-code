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
    public static final String UNIT_NUMBER_PROPERTY = "unitNumber";
    public static final String ITEM_COUNT_PROPERTY = "itemCount";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private StorageContainer storageContainer;
    private String unitNumber;
    private Integer itemCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public StorageContainer getStorageContainer(){
        return this.storageContainer;
    }
    public String getUnitNumber(){
        return this.unitNumber;
    }
    public Integer getItemCount(){
        return this.itemCount;
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
    public ContainerUnit updateUnitNumber(String unitNumber){
        unitNumber = (unitNumber == null ? null : unitNumber.trim());
        if(Objects.equals(this.unitNumber, unitNumber)){
            return this;
        }
        handleUpdate(UNIT_NUMBER_PROPERTY, getUnitNumber(), unitNumber);
        this.unitNumber = unitNumber;
        return this;
    }
    public ContainerUnit updateItemCount(Integer itemCount){
        if(Objects.equals(this.itemCount, itemCount)){
            return this;
        }
        handleUpdate(ITEM_COUNT_PROPERTY, getItemCount(), itemCount);
        this.itemCount = itemCount;
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

            case "unitNumber": this.unitNumber = (value == null ? null : ((String)value).trim()); break;

            case "itemCount": this.itemCount = (Integer) value; break;

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
            case "unitNumber": return this.unitNumber;
            case "itemCount": return this.itemCount;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}