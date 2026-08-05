package com.doublechaintech.enterpriselogisticsservice.cargoitem;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class CargoItemExpression<T, E, U extends CargoItem> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CargoItemExpression(Expression<T, U> expression){
        super(expression);
    }

    public CargoItemExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CargoItemExpression<T, U, U> updateId(Long id){
        return new CargoItemExpression(this, $it -> {((CargoItem)$it).__internalSet("id", id); return this;});
     }

     public CargoItemExpression<T, U, U> save(UserContext userContext){
        return new CargoItemExpression(this, $it -> ((CargoItem)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CargoItemExpression<T, U, U> save(String intent, UserContext userContext){
        return new CargoItemExpression(this, $it -> ((CargoItem)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getItemId(){
       return apply(CargoItem::getItemId);
    }
    public CargoItemExpression<T, U, U> updateItemId(String itemId){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateItemId(itemId));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((CargoItem)$it).getMovingOrder());
    }

    public CargoItemExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateMovingOrder(movingOrder));
    }

    public Expression<T, String> getDescription(){
       return apply(CargoItem::getDescription);
    }
    public CargoItemExpression<T, U, U> updateDescription(String description){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateDescription(description));
    }

    public Expression<T, String> getCategory(){
       return apply(CargoItem::getCategory);
    }
    public CargoItemExpression<T, U, U> updateCategory(String category){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateCategory(category));
    }

    public Expression<T, BigDecimal> getWeightKg(){
       return apply(CargoItem::getWeightKg);
    }
    public CargoItemExpression<T, U, U> updateWeightKg(BigDecimal weightKg){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateWeightKg(weightKg));
    }

    public Expression<T, BigDecimal> getVolumeM3(){
       return apply(CargoItem::getVolumeM3);
    }
    public CargoItemExpression<T, U, U> updateVolumeM3(BigDecimal volumeM3){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateVolumeM3(volumeM3));
    }

    public Expression<T, BigDecimal> getValue(){
       return apply(CargoItem::getValue);
    }
    public CargoItemExpression<T, U, U> updateValue(BigDecimal value){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateValue(value));
    }

    public Expression<T, Boolean> isFragile(){
       return apply(CargoItem::isFragile);
    }
    public CargoItemExpression<T, U, U> updateFragile(Boolean fragile){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateFragile(fragile));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(CargoItem::getCreateTime);
    }
    public CargoItemExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new CargoItemExpression(this, $it ->  ((CargoItem)$it).updateCreateTime(createTime));
    }

}