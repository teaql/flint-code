package com.doublechaintech.enterpriselogisticsservice.saleschannel;

import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SalesChannelExpression<T, E, U extends SalesChannel> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SalesChannelExpression(Expression<T, U> expression){
        super(expression);
    }

    public SalesChannelExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SalesChannelExpression<T, U, U> updateId(Long id){
        return new SalesChannelExpression(this, $it -> {((SalesChannel)$it).__internalSet("id", id); return this;});
     }

     public SalesChannelExpression<T, U, U> save(UserContext userContext){
        return new SalesChannelExpression(this, $it -> ((SalesChannel)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SalesChannelExpression<T, U, U> save(String intent, UserContext userContext){
        return new SalesChannelExpression(this, $it -> ((SalesChannel)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(SalesChannel::getName);
    }
    public SalesChannelExpression<T, U, U> updateName(String name){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateName(name));
    }

    public Expression<T, String> getDescription(){
       return apply(SalesChannel::getDescription);
    }
    public SalesChannelExpression<T, U, U> updateDescription(String description){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateDescription(description));
    }

    public Expression<T, String> getChannelType(){
       return apply(SalesChannel::getChannelType);
    }
    public SalesChannelExpression<T, U, U> updateChannelType(String channelType){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateChannelType(channelType));
    }

    public Expression<T, Boolean> isIsActive(){
       return apply(SalesChannel::isIsActive);
    }
    public SalesChannelExpression<T, U, U> updateIsActive(Boolean isActive){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateIsActive(isActive));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(SalesChannel::getCreatedTime);
    }
    public SalesChannelExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(SalesChannel::getUpdatedTime);
    }
    public SalesChannelExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateUpdatedTime(updatedTime));
    }

    public MarketingRoiListExpression<T, U, MarketingRoi> getMarketingRoiList(){
        return new MarketingRoiListExpression(this, $it ->  ((SalesChannel)$it).getMarketingRoiList());
    }
    public SalesChannelExpression<T, U, U> addMarketingRoi(MarketingRoi marketingRoi){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).addMarketingRoi(marketingRoi));
    }
}