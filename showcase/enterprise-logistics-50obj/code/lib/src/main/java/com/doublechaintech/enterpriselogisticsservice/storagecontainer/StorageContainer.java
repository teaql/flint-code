package com.doublechaintech.enterpriselogisticsservice.storagecontainer;

import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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
public class StorageContainer extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "StorageContainer";

    public static final String CONTAINER_ID_PROPERTY = "containerId";
    public static final String WAREHOUSE_PROPERTY = "warehouse";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    public static final String CONTAINER_UNIT_LIST_PROPERTY = "containerUnitList";
    private String containerId;
    private Warehouse warehouse;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private SmartList<ContainerUnit> containerUnitList;

    public String getContainerId(){
        return this.containerId;
    }
    public Warehouse getWarehouse(){
        return this.warehouse;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public SmartList<ContainerUnit> getContainerUnitList(){
        return this.containerUnitList;
    }
    public StorageContainer updateContainerId(String containerId){
        containerId = (containerId == null ? null : containerId.trim());
        if(Objects.equals(this.containerId, containerId)){
            return this;
        }
        handleUpdate(CONTAINER_ID_PROPERTY, getContainerId(), containerId);
        this.containerId = containerId;
        return this;
    }
    public StorageContainer updateWarehouse(Warehouse warehouse){
        if(Objects.equals(this.warehouse, warehouse)){
            return this;
        }
        handleUpdate(WAREHOUSE_PROPERTY, getWarehouse(), warehouse);
        this.warehouse = warehouse;
        return this;
    }
    public StorageContainer updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public StorageContainer updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public StorageContainer updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }
    public StorageContainer addContainerUnit(ContainerUnit containerUnit){
        if (containerUnit == null){
            return this;
        }

        if(null == this.containerUnitList){
            this.containerUnitList = new SmartList<>();
        }

        this.containerUnitList.add(containerUnit);
        containerUnit.cacheRelation(ContainerUnit.STORAGE_CONTAINER_PROPERTY, this);
        return this;
    }

    public static StorageContainer refer(Long id){
        StorageContainer refer = new StorageContainer();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public StorageContainer comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<StorageContainer> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "containerId": this.containerId = (value == null ? null : ((String)value).trim()); break;

            case "warehouse": this.warehouse = (Warehouse) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            case "containerUnitList": this.containerUnitList = (SmartList<ContainerUnit>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "containerId": return this.containerId;
            case "warehouse": return this.warehouse;
            case "status": return this.status;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            case "containerUnitList": return this.containerUnitList;
            default: return super.__internalGet(property);
        }
    }

}