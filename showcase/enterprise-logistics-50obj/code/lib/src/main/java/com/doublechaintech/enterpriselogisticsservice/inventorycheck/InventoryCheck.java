package com.doublechaintech.enterpriselogisticsservice.inventorycheck;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
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
public class InventoryCheck extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "InventoryCheck";

    public static final String WAREHOUSE_PROPERTY = "warehouse";
    public static final String CHECK_DATE_PROPERTY = "checkDate";
    public static final String TOTAL_ITEMS_PROPERTY = "totalItems";
    public static final String DISCREPANCIES_PROPERTY = "discrepancies";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private Warehouse warehouse;
    private String checkDate;
    private Integer totalItems;
    private Integer discrepancies;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Warehouse getWarehouse(){
        return this.warehouse;
    }
    public String getCheckDate(){
        return this.checkDate;
    }
    public Integer getTotalItems(){
        return this.totalItems;
    }
    public Integer getDiscrepancies(){
        return this.discrepancies;
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
    public InventoryCheck updateWarehouse(Warehouse warehouse){
        if(Objects.equals(this.warehouse, warehouse)){
            return this;
        }
        handleUpdate(WAREHOUSE_PROPERTY, getWarehouse(), warehouse);
        this.warehouse = warehouse;
        return this;
    }
    public InventoryCheck updateCheckDate(String checkDate){
        checkDate = (checkDate == null ? null : checkDate.trim());
        if(Objects.equals(this.checkDate, checkDate)){
            return this;
        }
        handleUpdate(CHECK_DATE_PROPERTY, getCheckDate(), checkDate);
        this.checkDate = checkDate;
        return this;
    }
    public InventoryCheck updateTotalItems(Integer totalItems){
        if(Objects.equals(this.totalItems, totalItems)){
            return this;
        }
        handleUpdate(TOTAL_ITEMS_PROPERTY, getTotalItems(), totalItems);
        this.totalItems = totalItems;
        return this;
    }
    public InventoryCheck updateDiscrepancies(Integer discrepancies){
        if(Objects.equals(this.discrepancies, discrepancies)){
            return this;
        }
        handleUpdate(DISCREPANCIES_PROPERTY, getDiscrepancies(), discrepancies);
        this.discrepancies = discrepancies;
        return this;
    }
    public InventoryCheck updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public InventoryCheck updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public InventoryCheck updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static InventoryCheck refer(Long id){
        InventoryCheck refer = new InventoryCheck();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public InventoryCheck comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<InventoryCheck> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "warehouse": this.warehouse = (Warehouse) value; break;

            case "checkDate": this.checkDate = (value == null ? null : ((String)value).trim()); break;

            case "totalItems": this.totalItems = (Integer) value; break;

            case "discrepancies": this.discrepancies = (Integer) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "warehouse": return this.warehouse;
            case "checkDate": return this.checkDate;
            case "totalItems": return this.totalItems;
            case "discrepancies": return this.discrepancies;
            case "status": return this.status;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}