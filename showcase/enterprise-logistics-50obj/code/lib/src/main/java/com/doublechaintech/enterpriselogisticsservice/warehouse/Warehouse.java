package com.doublechaintech.enterpriselogisticsservice.warehouse;

import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck;
import com.doublechaintech.enterpriselogisticsservice.pallet.Pallet;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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
public class Warehouse extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "Warehouse";

    public static final String NAME_PROPERTY = "name";
    public static final String CODE_PROPERTY = "code";
    public static final String ADDRESS_PROPERTY = "address";
    public static final String CITY_PROPERTY = "city";
    public static final String COUNTRY_PROPERTY = "country";
    public static final String CAPACITY_PROPERTY = "capacity";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    public static final String STORAGE_CONTAINER_LIST_PROPERTY = "storageContainerList";
    public static final String INVENTORY_CHECK_LIST_PROPERTY = "inventoryCheckList";
    public static final String PALLET_LIST_PROPERTY = "palletList";
    public static final String STORAGE_FEE_LIST_PROPERTY = "storageFeeList";
    private String name;
    private String code;
    private String address;
    private String city;
    private String country;
    private BigDecimal capacity;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private SmartList<StorageContainer> storageContainerList;
    private SmartList<InventoryCheck> inventoryCheckList;
    private SmartList<Pallet> palletList;
    private SmartList<StorageFee> storageFeeList;

    public String getName(){
        return this.name;
    }
    public String getCode(){
        return this.code;
    }
    public String getAddress(){
        return this.address;
    }
    public String getCity(){
        return this.city;
    }
    public String getCountry(){
        return this.country;
    }
    public BigDecimal getCapacity(){
        return this.capacity;
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
    public SmartList<StorageContainer> getStorageContainerList(){
        return this.storageContainerList;
    }
    public SmartList<InventoryCheck> getInventoryCheckList(){
        return this.inventoryCheckList;
    }
    public SmartList<Pallet> getPalletList(){
        return this.palletList;
    }
    public SmartList<StorageFee> getStorageFeeList(){
        return this.storageFeeList;
    }
    public Warehouse updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public Warehouse updateCode(String code){
        code = (code == null ? null : code.trim());
        if(Objects.equals(this.code, code)){
            return this;
        }
        handleUpdate(CODE_PROPERTY, getCode(), code);
        this.code = code;
        return this;
    }
    public Warehouse updateAddress(String address){
        address = (address == null ? null : address.trim());
        if(Objects.equals(this.address, address)){
            return this;
        }
        handleUpdate(ADDRESS_PROPERTY, getAddress(), address);
        this.address = address;
        return this;
    }
    public Warehouse updateCity(String city){
        city = (city == null ? null : city.trim());
        if(Objects.equals(this.city, city)){
            return this;
        }
        handleUpdate(CITY_PROPERTY, getCity(), city);
        this.city = city;
        return this;
    }
    public Warehouse updateCountry(String country){
        country = (country == null ? null : country.trim());
        if(Objects.equals(this.country, country)){
            return this;
        }
        handleUpdate(COUNTRY_PROPERTY, getCountry(), country);
        this.country = country;
        return this;
    }
    public Warehouse updateCapacity(BigDecimal capacity){
        if(Objects.equals(this.capacity, capacity)){
            return this;
        }
        handleUpdate(CAPACITY_PROPERTY, getCapacity(), capacity);
        this.capacity = capacity;
        return this;
    }
    public Warehouse updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public Warehouse updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public Warehouse updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }
    public Warehouse addStorageContainer(StorageContainer storageContainer){
        if (storageContainer == null){
            return this;
        }

        if(null == this.storageContainerList){
            this.storageContainerList = new SmartList<>();
        }

        this.storageContainerList.add(storageContainer);
        storageContainer.cacheRelation(StorageContainer.WAREHOUSE_PROPERTY, this);
        return this;
    }
    public Warehouse addInventoryCheck(InventoryCheck inventoryCheck){
        if (inventoryCheck == null){
            return this;
        }

        if(null == this.inventoryCheckList){
            this.inventoryCheckList = new SmartList<>();
        }

        this.inventoryCheckList.add(inventoryCheck);
        inventoryCheck.cacheRelation(InventoryCheck.WAREHOUSE_PROPERTY, this);
        return this;
    }
    public Warehouse addPallet(Pallet pallet){
        if (pallet == null){
            return this;
        }

        if(null == this.palletList){
            this.palletList = new SmartList<>();
        }

        this.palletList.add(pallet);
        pallet.cacheRelation(Pallet.WAREHOUSE_PROPERTY, this);
        return this;
    }
    public Warehouse addStorageFee(StorageFee storageFee){
        if (storageFee == null){
            return this;
        }

        if(null == this.storageFeeList){
            this.storageFeeList = new SmartList<>();
        }

        this.storageFeeList.add(storageFee);
        storageFee.cacheRelation(StorageFee.WAREHOUSE_PROPERTY, this);
        return this;
    }

    public static Warehouse refer(Long id){
        Warehouse refer = new Warehouse();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public Warehouse comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<Warehouse> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "code": this.code = (value == null ? null : ((String)value).trim()); break;

            case "address": this.address = (value == null ? null : ((String)value).trim()); break;

            case "city": this.city = (value == null ? null : ((String)value).trim()); break;

            case "country": this.country = (value == null ? null : ((String)value).trim()); break;

            case "capacity": this.capacity = (BigDecimal) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            case "storageContainerList": this.storageContainerList = (SmartList<StorageContainer>) value; break;
            case "inventoryCheckList": this.inventoryCheckList = (SmartList<InventoryCheck>) value; break;
            case "palletList": this.palletList = (SmartList<Pallet>) value; break;
            case "storageFeeList": this.storageFeeList = (SmartList<StorageFee>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "code": return this.code;
            case "address": return this.address;
            case "city": return this.city;
            case "country": return this.country;
            case "capacity": return this.capacity;
            case "status": return this.status;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            case "storageContainerList": return this.storageContainerList;
            case "inventoryCheckList": return this.inventoryCheckList;
            case "palletList": return this.palletList;
            case "storageFeeList": return this.storageFeeList;
            default: return super.__internalGet(property);
        }
    }

}