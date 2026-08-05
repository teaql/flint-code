package com.doublechaintech.movingcompanyservice.platform;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PlatformExpression<T, E, U extends Platform> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PlatformExpression(Expression<T, U> expression){
        super(expression);
    }

    public PlatformExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PlatformExpression<T, U, U> updateId(Long id){
        return new PlatformExpression(this, $it -> {((Platform)$it).__internalSet("id", id); return this;});
     }

     public PlatformExpression<T, U, U> save(UserContext userContext){
        return new PlatformExpression(this, $it -> ((Platform)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PlatformExpression<T, U, U> save(String intent, UserContext userContext){
        return new PlatformExpression(this, $it -> ((Platform)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getVersion(){
       return apply(Platform::getVersion);
    }
    public PlatformExpression<T, U, U> updateVersion(String version){
       return new PlatformExpression(this, $it ->  ((Platform)$it).updateVersion(version));
    }

    public Expression<T, String> getApiVersion(){
       return apply(Platform::getApiVersion);
    }
    public PlatformExpression<T, U, U> updateApiVersion(String apiVersion){
       return new PlatformExpression(this, $it ->  ((Platform)$it).updateApiVersion(apiVersion));
    }

    public Expression<T, Boolean> isMaintenanceMode(){
       return apply(Platform::isMaintenanceMode);
    }
    public PlatformExpression<T, U, U> updateMaintenanceMode(Boolean maintenanceMode){
       return new PlatformExpression(this, $it ->  ((Platform)$it).updateMaintenanceMode(maintenanceMode));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(Platform::getCreateTime);
    }
    public PlatformExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new PlatformExpression(this, $it ->  ((Platform)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(Platform::getUpdateTime);
    }
    public PlatformExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new PlatformExpression(this, $it ->  ((Platform)$it).updateUpdateTime(updateTime));
    }

}