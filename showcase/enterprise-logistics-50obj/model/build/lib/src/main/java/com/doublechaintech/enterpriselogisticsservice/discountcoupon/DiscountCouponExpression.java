package com.doublechaintech.enterpriselogisticsservice.discountcoupon;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class DiscountCouponExpression<T, E, U extends DiscountCoupon> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public DiscountCouponExpression(Expression<T, U> expression){
        super(expression);
    }

    public DiscountCouponExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public DiscountCouponExpression<T, U, U> updateId(Long id){
        return new DiscountCouponExpression(this, $it -> {((DiscountCoupon)$it).__internalSet("id", id); return this;});
     }

     public DiscountCouponExpression<T, U, U> save(UserContext userContext){
        return new DiscountCouponExpression(this, $it -> ((DiscountCoupon)$it).auditAs("Saved by Expression").save(userContext));
     }

     public DiscountCouponExpression<T, U, U> save(String intent, UserContext userContext){
        return new DiscountCouponExpression(this, $it -> ((DiscountCoupon)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getCode(){
       return apply(DiscountCoupon::getCode);
    }
    public DiscountCouponExpression<T, U, U> updateCode(String code){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateCode(code));
    }

    public Expression<T, BigDecimal> getDiscountPercentage(){
       return apply(DiscountCoupon::getDiscountPercentage);
    }
    public DiscountCouponExpression<T, U, U> updateDiscountPercentage(BigDecimal discountPercentage){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateDiscountPercentage(discountPercentage));
    }

    public Expression<T, Integer> getMaxUses(){
       return apply(DiscountCoupon::getMaxUses);
    }
    public DiscountCouponExpression<T, U, U> updateMaxUses(Integer maxUses){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateMaxUses(maxUses));
    }

    public Expression<T, Integer> getCurrentUses(){
       return apply(DiscountCoupon::getCurrentUses);
    }
    public DiscountCouponExpression<T, U, U> updateCurrentUses(Integer currentUses){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateCurrentUses(currentUses));
    }

    public Expression<T, LocalDate> getExpiryDate(){
       return apply(DiscountCoupon::getExpiryDate);
    }
    public DiscountCouponExpression<T, U, U> updateExpiryDate(LocalDate expiryDate){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateExpiryDate(expiryDate));
    }

    public Expression<T, String> getStatus(){
       return apply(DiscountCoupon::getStatus);
    }
    public DiscountCouponExpression<T, U, U> updateStatus(String status){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(DiscountCoupon::getCreatedTime);
    }
    public DiscountCouponExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(DiscountCoupon::getUpdateTime);
    }
    public DiscountCouponExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateUpdateTime(updateTime));
    }

}