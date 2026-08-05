package com.doublechaintech.enterpriselogisticsservice.storagecontainer;

import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnit;
import com.doublechaintech.enterpriselogisticsservice.containerunit.ContainerUnitListExpression;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFee;
import com.doublechaintech.enterpriselogisticsservice.storagefee.StorageFeeListExpression;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class StorageContainerExpression<T, E, U extends StorageContainer> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public StorageContainerExpression(Expression<T, U> expression){
        super(expression);
    }

    public StorageContainerExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public StorageContainerExpression<T, U, U> updateId(Long id){
        return new StorageContainerExpression(this, $it -> {((StorageContainer)$it).__internalSet("id", id); return this;});
     }

     public StorageContainerExpression<T, U, U> save(UserContext userContext){
        return new StorageContainerExpression(this, $it -> ((StorageContainer)$it).auditAs("Saved by Expression").save(userContext));
     }

     public StorageContainerExpression<T, U, U> save(String intent, UserContext userContext){
        return new StorageContainerExpression(this, $it -> ((StorageContainer)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getContainerId(){
       return apply(StorageContainer::getContainerId);
    }
    public StorageContainerExpression<T, U, U> updateContainerId(String containerId){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).updateContainerId(containerId));
    }

    public WarehouseExpression<T, U, Warehouse> getWarehouse(){
       return new WarehouseExpression(this, $it ->  ((StorageContainer)$it).getWarehouse());
    }

    public StorageContainerExpression<T, U, U> updateWarehouse(Warehouse warehouse){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).updateWarehouse(warehouse));
    }

    public Expression<T, String> getStatus(){
       return apply(StorageContainer::getStatus);
    }
    public StorageContainerExpression<T, U, U> updateStatus(String status){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(StorageContainer::getCreateTime);
    }
    public StorageContainerExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(StorageContainer::getUpdateTime);
    }
    public StorageContainerExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).updateUpdateTime(updateTime));
    }

    public ContainerUnitListExpression<T, U, ContainerUnit> getContainerUnitList(){
        return new ContainerUnitListExpression(this, $it ->  ((StorageContainer)$it).getContainerUnitList());
    }
    public StorageFeeListExpression<T, U, StorageFee> getStorageFeeList(){
        return new StorageFeeListExpression(this, $it ->  ((StorageContainer)$it).getStorageFeeList());
    }
    public StorageContainerExpression<T, U, U> addContainerUnit(ContainerUnit containerUnit){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).addContainerUnit(containerUnit));
    }
    public StorageContainerExpression<T, U, U> addStorageFee(StorageFee storageFee){
       return new StorageContainerExpression(this, $it ->  ((StorageContainer)$it).addStorageFee(storageFee));
    }
}