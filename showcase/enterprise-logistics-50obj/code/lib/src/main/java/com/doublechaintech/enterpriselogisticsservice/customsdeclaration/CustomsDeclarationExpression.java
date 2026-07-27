package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class CustomsDeclarationExpression<T, E, U extends CustomsDeclaration> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CustomsDeclarationExpression(Expression<T, U> expression){
        super(expression);
    }

    public CustomsDeclarationExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CustomsDeclarationExpression<T, U, U> updateId(Long id){
        return new CustomsDeclarationExpression(this, $it -> {((CustomsDeclaration)$it).__internalSet("id", id); return this;});
     }

     public CustomsDeclarationExpression<T, U, U> save(UserContext userContext){
        return new CustomsDeclarationExpression(this, $it -> ((CustomsDeclaration)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CustomsDeclarationExpression<T, U, U> save(String intent, UserContext userContext){
        return new CustomsDeclarationExpression(this, $it -> ((CustomsDeclaration)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getDeclarationNumber(){
       return apply(CustomsDeclaration::getDeclarationNumber);
    }
    public CustomsDeclarationExpression<T, U, U> updateDeclarationNumber(String declarationNumber){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateDeclarationNumber(declarationNumber));
    }

    public Expression<T, String> getOriginCountry(){
       return apply(CustomsDeclaration::getOriginCountry);
    }
    public CustomsDeclarationExpression<T, U, U> updateOriginCountry(String originCountry){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateOriginCountry(originCountry));
    }

    public Expression<T, String> getDestinationCountry(){
       return apply(CustomsDeclaration::getDestinationCountry);
    }
    public CustomsDeclarationExpression<T, U, U> updateDestinationCountry(String destinationCountry){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateDestinationCountry(destinationCountry));
    }

    public Expression<T, BigDecimal> getTotalValue(){
       return apply(CustomsDeclaration::getTotalValue);
    }
    public CustomsDeclarationExpression<T, U, U> updateTotalValue(BigDecimal totalValue){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateTotalValue(totalValue));
    }

    public Expression<T, String> getStatus(){
       return apply(CustomsDeclaration::getStatus);
    }
    public CustomsDeclarationExpression<T, U, U> updateStatus(String status){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateStatus(status));
    }

    public MovingOrderExpression<T, U, MovingOrder> getMovingOrder(){
       return new MovingOrderExpression(this, $it ->  ((CustomsDeclaration)$it).getMovingOrder());
    }

    public CustomsDeclarationExpression<T, U, U> updateMovingOrder(MovingOrder movingOrder){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateMovingOrder(movingOrder));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(CustomsDeclaration::getCreatedTime);
    }
    public CustomsDeclarationExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(CustomsDeclaration::getUpdateTime);
    }
    public CustomsDeclarationExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateUpdateTime(updateTime));
    }

}