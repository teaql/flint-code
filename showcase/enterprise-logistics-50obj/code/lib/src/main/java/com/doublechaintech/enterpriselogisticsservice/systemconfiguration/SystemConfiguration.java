package com.doublechaintech.enterpriselogisticsservice.systemconfiguration;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class SystemConfiguration extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SystemConfiguration";

    public static final String CONFIG_KEY_PROPERTY = "configKey";
    public static final String CONFIG_VALUE_PROPERTY = "configValue";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private String configKey;
    private String configValue;
    private String description;
    private LocalDateTime updatedAt;

    public String getConfigKey(){
        return this.configKey;
    }
    public String getConfigValue(){
        return this.configValue;
    }
    public String getDescription(){
        return this.description;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SystemConfiguration updateConfigKey(String configKey){
        configKey = (configKey == null ? null : configKey.trim());
        if(Objects.equals(this.configKey, configKey)){
            return this;
        }
        handleUpdate(CONFIG_KEY_PROPERTY, getConfigKey(), configKey);
        this.configKey = configKey;
        return this;
    }
    public SystemConfiguration updateConfigValue(String configValue){
        configValue = (configValue == null ? null : configValue.trim());
        if(Objects.equals(this.configValue, configValue)){
            return this;
        }
        handleUpdate(CONFIG_VALUE_PROPERTY, getConfigValue(), configValue);
        this.configValue = configValue;
        return this;
    }
    public SystemConfiguration updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public SystemConfiguration updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static SystemConfiguration refer(Long id){
        SystemConfiguration refer = new SystemConfiguration();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public SystemConfiguration comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<SystemConfiguration> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "configKey": this.configKey = (value == null ? null : ((String)value).trim()); break;

            case "configValue": this.configValue = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "configKey": return this.configKey;
            case "configValue": return this.configValue;
            case "description": return this.description;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}