package com.doublechaintech.enterpriselogisticsservice.saleschannel;

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

    public Expression<T, String> getChannelType(){
       return apply(SalesChannel::getChannelType);
    }
    public SalesChannelExpression<T, U, U> updateChannelType(String channelType){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateChannelType(channelType));
    }

    public Expression<T, String> getUrl(){
       return apply(SalesChannel::getUrl);
    }
    public SalesChannelExpression<T, U, U> updateUrl(String url){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateUrl(url));
    }

    public Expression<T, String> getStatus(){
       return apply(SalesChannel::getStatus);
    }
    public SalesChannelExpression<T, U, U> updateStatus(String status){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(SalesChannel::getCreatedTime);
    }
    public SalesChannelExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(SalesChannel::getUpdateTime);
    }
    public SalesChannelExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new SalesChannelExpression(this, $it ->  ((SalesChannel)$it).updateUpdateTime(updateTime));
    }

}