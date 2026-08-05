package com.doublechaintech.enterpriselogisticsservice.storagefee;

import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerExpression;
import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class StorageFeeExpression<T, E, U extends StorageFee> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public StorageFeeExpression(Expression<T, U> expression){
        super(expression);
    }

    public StorageFeeExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public StorageFeeExpression<T, U, U> updateId(Long id){
        return new StorageFeeExpression(this, $it -> {((StorageFee)$it).__internalSet("id", id); return this;});
     }

     public StorageFeeExpression<T, U, U> save(UserContext userContext){
        return new StorageFeeExpression(this, $it -> ((StorageFee)$it).auditAs("Saved by Expression").save(userContext));
     }

     public StorageFeeExpression<T, U, U> save(String intent, UserContext userContext){
        return new StorageFeeExpression(this, $it -> ((StorageFee)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public WarehouseExpression<T, U, Warehouse> getWarehouse(){
       return new WarehouseExpression(this, $it ->  ((StorageFee)$it).getWarehouse());
    }

    public StorageFeeExpression<T, U, U> updateWarehouse(Warehouse warehouse){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateWarehouse(warehouse));
    }

    public StorageContainerExpression<T, U, StorageContainer> getContainer(){
       return new StorageContainerExpression(this, $it ->  ((StorageFee)$it).getContainer());
    }

    public StorageFeeExpression<T, U, U> updateContainer(StorageContainer container){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateContainer(container));
    }

    public Expression<T, BigDecimal> getAmount(){
       return apply(StorageFee::getAmount);
    }
    public StorageFeeExpression<T, U, U> updateAmount(BigDecimal amount){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateAmount(amount));
    }

    public Expression<T, String> getCurrency(){
       return apply(StorageFee::getCurrency);
    }
    public StorageFeeExpression<T, U, U> updateCurrency(String currency){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateCurrency(currency));
    }

    public Expression<T, String> getPeriod(){
       return apply(StorageFee::getPeriod);
    }
    public StorageFeeExpression<T, U, U> updatePeriod(String period){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updatePeriod(period));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(StorageFee::getCreateTime);
    }
    public StorageFeeExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(StorageFee::getUpdateTime);
    }
    public StorageFeeExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new StorageFeeExpression(this, $it ->  ((StorageFee)$it).updateUpdateTime(updateTime));
    }

}