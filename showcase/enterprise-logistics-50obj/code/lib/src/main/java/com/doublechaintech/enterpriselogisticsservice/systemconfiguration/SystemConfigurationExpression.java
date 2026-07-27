package com.doublechaintech.enterpriselogisticsservice.systemconfiguration;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SystemConfigurationExpression<T, E, U extends SystemConfiguration> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SystemConfigurationExpression(Expression<T, U> expression){
        super(expression);
    }

    public SystemConfigurationExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SystemConfigurationExpression<T, U, U> updateId(Long id){
        return new SystemConfigurationExpression(this, $it -> {((SystemConfiguration)$it).__internalSet("id", id); return this;});
     }

     public SystemConfigurationExpression<T, U, U> save(UserContext userContext){
        return new SystemConfigurationExpression(this, $it -> ((SystemConfiguration)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SystemConfigurationExpression<T, U, U> save(String intent, UserContext userContext){
        return new SystemConfigurationExpression(this, $it -> ((SystemConfiguration)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getConfigKey(){
       return apply(SystemConfiguration::getConfigKey);
    }
    public SystemConfigurationExpression<T, U, U> updateConfigKey(String configKey){
       return new SystemConfigurationExpression(this, $it ->  ((SystemConfiguration)$it).updateConfigKey(configKey));
    }

    public Expression<T, String> getConfigValue(){
       return apply(SystemConfiguration::getConfigValue);
    }
    public SystemConfigurationExpression<T, U, U> updateConfigValue(String configValue){
       return new SystemConfigurationExpression(this, $it ->  ((SystemConfiguration)$it).updateConfigValue(configValue));
    }

    public Expression<T, String> getDescription(){
       return apply(SystemConfiguration::getDescription);
    }
    public SystemConfigurationExpression<T, U, U> updateDescription(String description){
       return new SystemConfigurationExpression(this, $it ->  ((SystemConfiguration)$it).updateDescription(description));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(SystemConfiguration::getUpdatedAt);
    }
    public SystemConfigurationExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new SystemConfigurationExpression(this, $it ->  ((SystemConfiguration)$it).updateUpdatedAt(updatedAt));
    }

}