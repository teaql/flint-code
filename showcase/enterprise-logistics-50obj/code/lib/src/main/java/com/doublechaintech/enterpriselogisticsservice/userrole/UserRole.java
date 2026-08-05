package com.doublechaintech.enterpriselogisticsservice.userrole;

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
public class UserRole extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "UserRole";

    public static final String ROLE_NAME_PROPERTY = "roleName";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String IS_SYSTEM_PROPERTY = "isSystem";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    private String roleName;
    private String description;
    private String isSystem;
    private LocalDateTime createdAt;

    public String getRoleName(){
        return this.roleName;
    }
    public String getDescription(){
        return this.description;
    }
    public String getIsSystem(){
        return this.isSystem;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public UserRole updateRoleName(String roleName){
        roleName = (roleName == null ? null : roleName.trim());
        if(Objects.equals(this.roleName, roleName)){
            return this;
        }
        handleUpdate(ROLE_NAME_PROPERTY, getRoleName(), roleName);
        this.roleName = roleName;
        return this;
    }
    public UserRole updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public UserRole updateIsSystem(String isSystem){
        isSystem = (isSystem == null ? null : isSystem.trim());
        if(Objects.equals(this.isSystem, isSystem)){
            return this;
        }
        handleUpdate(IS_SYSTEM_PROPERTY, getIsSystem(), isSystem);
        this.isSystem = isSystem;
        return this;
    }
    public UserRole updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }

    public static UserRole refer(Long id){
        UserRole refer = new UserRole();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public UserRole comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<UserRole> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "roleName": this.roleName = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "isSystem": this.isSystem = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "roleName": return this.roleName;
            case "description": return this.description;
            case "isSystem": return this.isSystem;
            case "createdAt": return this.createdAt;
            default: return super.__internalGet(property);
        }
    }

}