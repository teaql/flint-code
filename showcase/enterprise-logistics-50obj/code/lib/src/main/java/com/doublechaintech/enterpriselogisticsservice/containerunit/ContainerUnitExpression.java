package com.doublechaintech.enterpriselogisticsservice.containerunit;

import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainer;
import com.doublechaintech.enterpriselogisticsservice.storagecontainer.StorageContainerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class ContainerUnitExpression<T, E, U extends ContainerUnit> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public ContainerUnitExpression(Expression<T, U> expression){
        super(expression);
    }

    public ContainerUnitExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public ContainerUnitExpression<T, U, U> updateId(Long id){
        return new ContainerUnitExpression(this, $it -> {((ContainerUnit)$it).__internalSet("id", id); return this;});
     }

     public ContainerUnitExpression<T, U, U> save(UserContext userContext){
        return new ContainerUnitExpression(this, $it -> ((ContainerUnit)$it).auditAs("Saved by Expression").save(userContext));
     }

     public ContainerUnitExpression<T, U, U> save(String intent, UserContext userContext){
        return new ContainerUnitExpression(this, $it -> ((ContainerUnit)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public StorageContainerExpression<T, U, StorageContainer> getStorageContainer(){
       return new StorageContainerExpression(this, $it ->  ((ContainerUnit)$it).getStorageContainer());
    }

    public ContainerUnitExpression<T, U, U> updateStorageContainer(StorageContainer storageContainer){
       return new ContainerUnitExpression(this, $it ->  ((ContainerUnit)$it).updateStorageContainer(storageContainer));
    }

    public Expression<T, String> getUnitNumber(){
       return apply(ContainerUnit::getUnitNumber);
    }
    public ContainerUnitExpression<T, U, U> updateUnitNumber(String unitNumber){
       return new ContainerUnitExpression(this, $it ->  ((ContainerUnit)$it).updateUnitNumber(unitNumber));
    }

    public Expression<T, Integer> getItemCount(){
       return apply(ContainerUnit::getItemCount);
    }
    public ContainerUnitExpression<T, U, U> updateItemCount(Integer itemCount){
       return new ContainerUnitExpression(this, $it ->  ((ContainerUnit)$it).updateItemCount(itemCount));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(ContainerUnit::getCreateTime);
    }
    public ContainerUnitExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new ContainerUnitExpression(this, $it ->  ((ContainerUnit)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(ContainerUnit::getUpdateTime);
    }
    public ContainerUnitExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new ContainerUnitExpression(this, $it ->  ((ContainerUnit)$it).updateUpdateTime(updateTime));
    }

}