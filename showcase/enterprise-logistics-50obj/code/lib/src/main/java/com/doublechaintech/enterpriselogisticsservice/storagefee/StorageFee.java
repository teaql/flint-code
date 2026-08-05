package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
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
public class StorageFee extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "StorageFee";

    public static final String WAREHOUSE_PROPERTY = "warehouse";
    public static final String CONTAINER_PROPERTY = "container";
    public static final String AMOUNT_PROPERTY = "amount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String PERIOD_PROPERTY = "period";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private Warehouse warehouse;
    private StorageContainer container;
    private BigDecimal amount;
    private String currency;
    private String period;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public Warehouse getWarehouse(){
        return this.warehouse;
    }
    public StorageContainer getContainer(){
        return this.container;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public String getCurrency(){
        return this.currency;
    }
    public String getPeriod(){
        return this.period;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public StorageFee updateWarehouse(Warehouse warehouse){
        if(Objects.equals(this.warehouse, warehouse)){
            return this;
        }
        handleUpdate(WAREHOUSE_PROPERTY, getWarehouse(), warehouse);
        this.warehouse = warehouse;
        return this;
    }
    public StorageFee updateContainer(StorageContainer container){
        if(Objects.equals(this.container, container)){
            return this;
        }
        handleUpdate(CONTAINER_PROPERTY, getContainer(), container);
        this.container = container;
        return this;
    }
    public StorageFee updateAmount(BigDecimal amount){
        if(Objects.equals(this.amount, amount)){
            return this;
        }
        handleUpdate(AMOUNT_PROPERTY, getAmount(), amount);
        this.amount = amount;
        return this;
    }
    public StorageFee updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public StorageFee updatePeriod(String period){
        period = (period == null ? null : period.trim());
        if(Objects.equals(this.period, period)){
            return this;
        }
        handleUpdate(PERIOD_PROPERTY, getPeriod(), period);
        this.period = period;
        return this;
    }
    public StorageFee updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public StorageFee updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static StorageFee refer(Long id){
        StorageFee refer = new StorageFee();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public StorageFee comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<StorageFee> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "warehouse": this.warehouse = (Warehouse) value; break;

            case "container": this.container = (StorageContainer) value; break;

            case "amount": this.amount = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "period": this.period = (value == null ? null : ((String)value).trim()); break;

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
            case "container": return this.container;
            case "amount": return this.amount;
            case "currency": return this.currency;
            case "period": return this.period;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}