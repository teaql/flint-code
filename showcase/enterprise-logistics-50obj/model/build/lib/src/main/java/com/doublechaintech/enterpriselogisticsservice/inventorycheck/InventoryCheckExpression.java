package com.doublechaintech.enterpriselogisticsservice.inventorycheck;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class InventoryCheckExpression<T, E, U extends InventoryCheck> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public InventoryCheckExpression(Expression<T, U> expression){
        super(expression);
    }

    public InventoryCheckExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public InventoryCheckExpression<T, U, U> updateId(Long id){
        return new InventoryCheckExpression(this, $it -> {((InventoryCheck)$it).__internalSet("id", id); return this;});
     }

     public InventoryCheckExpression<T, U, U> save(UserContext userContext){
        return new InventoryCheckExpression(this, $it -> ((InventoryCheck)$it).auditAs("Saved by Expression").save(userContext));
     }

     public InventoryCheckExpression<T, U, U> save(String intent, UserContext userContext){
        return new InventoryCheckExpression(this, $it -> ((InventoryCheck)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public WarehouseExpression<T, U, Warehouse> getWarehouse(){
       return new WarehouseExpression(this, $it ->  ((InventoryCheck)$it).getWarehouse());
    }

    public InventoryCheckExpression<T, U, U> updateWarehouse(Warehouse warehouse){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateWarehouse(warehouse));
    }

    public Expression<T, String> getCheckDate(){
       return apply(InventoryCheck::getCheckDate);
    }
    public InventoryCheckExpression<T, U, U> updateCheckDate(String checkDate){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateCheckDate(checkDate));
    }

    public Expression<T, Integer> getTotalItems(){
       return apply(InventoryCheck::getTotalItems);
    }
    public InventoryCheckExpression<T, U, U> updateTotalItems(Integer totalItems){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateTotalItems(totalItems));
    }

    public Expression<T, Integer> getDiscrepancies(){
       return apply(InventoryCheck::getDiscrepancies);
    }
    public InventoryCheckExpression<T, U, U> updateDiscrepancies(Integer discrepancies){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateDiscrepancies(discrepancies));
    }

    public Expression<T, String> getStatus(){
       return apply(InventoryCheck::getStatus);
    }
    public InventoryCheckExpression<T, U, U> updateStatus(String status){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(InventoryCheck::getCreateTime);
    }
    public InventoryCheckExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(InventoryCheck::getUpdateTime);
    }
    public InventoryCheckExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new InventoryCheckExpression(this, $it ->  ((InventoryCheck)$it).updateUpdateTime(updateTime));
    }

}