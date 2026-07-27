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

    public Expression<T, String> getDescription(){
       return apply(DiscountCoupon::getDescription);
    }
    public DiscountCouponExpression<T, U, U> updateDescription(String description){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateDescription(description));
    }

    public Expression<T, BigDecimal> getDiscountPercentage(){
       return apply(DiscountCoupon::getDiscountPercentage);
    }
    public DiscountCouponExpression<T, U, U> updateDiscountPercentage(BigDecimal discountPercentage){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateDiscountPercentage(discountPercentage));
    }

    public Expression<T, BigDecimal> getMinOrderAmount(){
       return apply(DiscountCoupon::getMinOrderAmount);
    }
    public DiscountCouponExpression<T, U, U> updateMinOrderAmount(BigDecimal minOrderAmount){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateMinOrderAmount(minOrderAmount));
    }

    public Expression<T, BigDecimal> getMaxDiscountAmount(){
       return apply(DiscountCoupon::getMaxDiscountAmount);
    }
    public DiscountCouponExpression<T, U, U> updateMaxDiscountAmount(BigDecimal maxDiscountAmount){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateMaxDiscountAmount(maxDiscountAmount));
    }

    public Expression<T, Integer> getUsageLimit(){
       return apply(DiscountCoupon::getUsageLimit);
    }
    public DiscountCouponExpression<T, U, U> updateUsageLimit(Integer usageLimit){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateUsageLimit(usageLimit));
    }

    public Expression<T, Integer> getUsedCount(){
       return apply(DiscountCoupon::getUsedCount);
    }
    public DiscountCouponExpression<T, U, U> updateUsedCount(Integer usedCount){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateUsedCount(usedCount));
    }

    public Expression<T, LocalDate> getStartDate(){
       return apply(DiscountCoupon::getStartDate);
    }
    public DiscountCouponExpression<T, U, U> updateStartDate(LocalDate startDate){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateStartDate(startDate));
    }

    public Expression<T, LocalDate> getEndDate(){
       return apply(DiscountCoupon::getEndDate);
    }
    public DiscountCouponExpression<T, U, U> updateEndDate(LocalDate endDate){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateEndDate(endDate));
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

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(DiscountCoupon::getUpdatedTime);
    }
    public DiscountCouponExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new DiscountCouponExpression(this, $it ->  ((DiscountCoupon)$it).updateUpdatedTime(updatedTime));
    }

}