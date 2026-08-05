package com.doublechaintech.enterpriselogisticsservice.pallet;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import com.doublechaintech.enterpriselogisticsservice.warehouse.WarehouseExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PalletExpression<T, E, U extends Pallet> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PalletExpression(Expression<T, U> expression){
        super(expression);
    }

    public PalletExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PalletExpression<T, U, U> updateId(Long id){
        return new PalletExpression(this, $it -> {((Pallet)$it).__internalSet("id", id); return this;});
     }

     public PalletExpression<T, U, U> save(UserContext userContext){
        return new PalletExpression(this, $it -> ((Pallet)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PalletExpression<T, U, U> save(String intent, UserContext userContext){
        return new PalletExpression(this, $it -> ((Pallet)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public WarehouseExpression<T, U, Warehouse> getWarehouse(){
       return new WarehouseExpression(this, $it ->  ((Pallet)$it).getWarehouse());
    }

    public PalletExpression<T, U, U> updateWarehouse(Warehouse warehouse){
       return new PalletExpression(this, $it ->  ((Pallet)$it).updateWarehouse(warehouse));
    }

    public Expression<T, String> getPalletId(){
       return apply(Pallet::getPalletId);
    }
    public PalletExpression<T, U, U> updatePalletId(String palletId){
       return new PalletExpression(this, $it ->  ((Pallet)$it).updatePalletId(palletId));
    }

    public Expression<T, BigDecimal> getLoadWeight(){
       return apply(Pallet::getLoadWeight);
    }
    public PalletExpression<T, U, U> updateLoadWeight(BigDecimal loadWeight){
       return new PalletExpression(this, $it ->  ((Pallet)$it).updateLoadWeight(loadWeight));
    }

    public Expression<T, String> getStatus(){
       return apply(Pallet::getStatus);
    }
    public PalletExpression<T, U, U> updateStatus(String status){
       return new PalletExpression(this, $it ->  ((Pallet)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(Pallet::getCreateTime);
    }
    public PalletExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new PalletExpression(this, $it ->  ((Pallet)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(Pallet::getUpdateTime);
    }
    public PalletExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new PalletExpression(this, $it ->  ((Pallet)$it).updateUpdateTime(updateTime));
    }

}