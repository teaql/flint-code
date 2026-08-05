package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class AccessPermission extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "AccessPermission";

    public static final String PERMISSION_CODE_PROPERTY = "permissionCode";
    public static final String RESOURCE_PROPERTY = "resource";
    public static final String ACTION_PROPERTY = "action";
    public static final String DESCRIPTION_PROPERTY = "description";
    private String permissionCode;
    private String resource;
    private String action;
    private String description;

    public String getPermissionCode(){
        return this.permissionCode;
    }
    public String getResource(){
        return this.resource;
    }
    public String getAction(){
        return this.action;
    }
    public String getDescription(){
        return this.description;
    }
    public AccessPermission updatePermissionCode(String permissionCode){
        permissionCode = (permissionCode == null ? null : permissionCode.trim());
        if(Objects.equals(this.permissionCode, permissionCode)){
            return this;
        }
        handleUpdate(PERMISSION_CODE_PROPERTY, getPermissionCode(), permissionCode);
        this.permissionCode = permissionCode;
        return this;
    }
    public AccessPermission updateResource(String resource){
        resource = (resource == null ? null : resource.trim());
        if(Objects.equals(this.resource, resource)){
            return this;
        }
        handleUpdate(RESOURCE_PROPERTY, getResource(), resource);
        this.resource = resource;
        return this;
    }
    public AccessPermission updateAction(String action){
        action = (action == null ? null : action.trim());
        if(Objects.equals(this.action, action)){
            return this;
        }
        handleUpdate(ACTION_PROPERTY, getAction(), action);
        this.action = action;
        return this;
    }
    public AccessPermission updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }

    public static AccessPermission refer(Long id){
        AccessPermission refer = new AccessPermission();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public AccessPermission comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<AccessPermission> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "permissionCode": this.permissionCode = (value == null ? null : ((String)value).trim()); break;

            case "resource": this.resource = (value == null ? null : ((String)value).trim()); break;

            case "action": this.action = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "permissionCode": return this.permissionCode;
            case "resource": return this.resource;
            case "action": return this.action;
            case "description": return this.description;
            default: return super.__internalGet(property);
        }
    }

}