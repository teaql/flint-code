package com.doublechaintech.enterpriselogisticsservice.warehouse;

import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheck;
import com.doublechaintech.enterpriselogisticsservice.inventorycheck.InventoryCheckListExpression;
import com.doublechaintech.enterpriselogisticsservice.pallet.Pallet;
import com.doublechaintech.enterpriselogisticsservice.pallet.PalletListExpression;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerListExpression;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class WarehouseExpression<T, E, U extends Warehouse> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public WarehouseExpression(Expression<T, U> expression){
        super(expression);
    }

    public WarehouseExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public WarehouseExpression<T, U, U> updateId(Long id){
        return new WarehouseExpression(this, $it -> {((Warehouse)$it).__internalSet("id", id); return this;});
     }

     public WarehouseExpression<T, U, U> save(UserContext userContext){
        return new WarehouseExpression(this, $it -> ((Warehouse)$it).auditAs("Saved by Expression").save(userContext));
     }

     public WarehouseExpression<T, U, U> save(String intent, UserContext userContext){
        return new WarehouseExpression(this, $it -> ((Warehouse)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(Warehouse::getName);
    }
    public WarehouseExpression<T, U, U> updateName(String name){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateName(name));
    }

    public Expression<T, String> getCode(){
       return apply(Warehouse::getCode);
    }
    public WarehouseExpression<T, U, U> updateCode(String code){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateCode(code));
    }

    public Expression<T, String> getAddress(){
       return apply(Warehouse::getAddress);
    }
    public WarehouseExpression<T, U, U> updateAddress(String address){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateAddress(address));
    }

    public Expression<T, String> getCity(){
       return apply(Warehouse::getCity);
    }
    public WarehouseExpression<T, U, U> updateCity(String city){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateCity(city));
    }

    public Expression<T, String> getCountry(){
       return apply(Warehouse::getCountry);
    }
    public WarehouseExpression<T, U, U> updateCountry(String country){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateCountry(country));
    }

    public Expression<T, BigDecimal> getCapacity(){
       return apply(Warehouse::getCapacity);
    }
    public WarehouseExpression<T, U, U> updateCapacity(BigDecimal capacity){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateCapacity(capacity));
    }

    public Expression<T, String> getStatus(){
       return apply(Warehouse::getStatus);
    }
    public WarehouseExpression<T, U, U> updateStatus(String status){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(Warehouse::getCreateTime);
    }
    public WarehouseExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(Warehouse::getUpdateTime);
    }
    public WarehouseExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).updateUpdateTime(updateTime));
    }

    public StorageContainerListExpression<T, U, StorageContainer> getStorageContainerList(){
        return new StorageContainerListExpression(this, $it ->  ((Warehouse)$it).getStorageContainerList());
    }
    public InventoryCheckListExpression<T, U, InventoryCheck> getInventoryCheckList(){
        return new InventoryCheckListExpression(this, $it ->  ((Warehouse)$it).getInventoryCheckList());
    }
    public PalletListExpression<T, U, Pallet> getPalletList(){
        return new PalletListExpression(this, $it ->  ((Warehouse)$it).getPalletList());
    }
    public StorageFeeListExpression<T, U, StorageFee> getStorageFeeList(){
        return new StorageFeeListExpression(this, $it ->  ((Warehouse)$it).getStorageFeeList());
    }
    public WarehouseExpression<T, U, U> addStorageContainer(StorageContainer storageContainer){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).addStorageContainer(storageContainer));
    }
    public WarehouseExpression<T, U, U> addInventoryCheck(InventoryCheck inventoryCheck){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).addInventoryCheck(inventoryCheck));
    }
    public WarehouseExpression<T, U, U> addPallet(Pallet pallet){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).addPallet(pallet));
    }
    public WarehouseExpression<T, U, U> addStorageFee(StorageFee storageFee){
       return new WarehouseExpression(this, $it ->  ((Warehouse)$it).addStorageFee(storageFee));
    }
}