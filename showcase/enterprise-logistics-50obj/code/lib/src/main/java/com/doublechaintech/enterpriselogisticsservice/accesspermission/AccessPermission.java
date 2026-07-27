package com.doublechaintech.enterpriselogisticsservice.accesspermission;

import com.doublechaintech.enterpriselogisticsservice.Constants;
import com.doublechaintech.enterpriselogisticsservice.userrole.UserRole;
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

    public static final String NAME_PROPERTY = "name";
    public static final String RESOURCE_PROPERTY = "resource";
    public static final String ACTION_PROPERTY = "action";
    public static final String ROLE_PROPERTY = "role";
    private String name;
    private String resource;
    private String action;
    private UserRole role;

    public String getName(){
        return this.name;
    }
    public String getResource(){
        return this.resource;
    }
    public String getAction(){
        return this.action;
    }
    public UserRole getRole(){
        return this.role;
    }
    public AccessPermission updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
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
    protected AccessPermission updateRole(UserRole role){
        if(Objects.equals(this.role, role)){
            return this;
        }
        handleUpdate(ROLE_PROPERTY, getRole(), role);
        this.role = role;
        return this;
    }
    public boolean isRoleAdmin(){
        return Objects.equals(getRole(), Constants.USER_ROLE_ADMIN);
    }

    public AccessPermission updateRoleToAdmin(){
        return updateRole(Constants.USER_ROLE_ADMIN);
    }
    public boolean isRoleDispatcher(){
        return Objects.equals(getRole(), Constants.USER_ROLE_DISPATCHER);
    }

    public AccessPermission updateRoleToDispatcher(){
        return updateRole(Constants.USER_ROLE_DISPATCHER);
    }
    public boolean isRoleDriver(){
        return Objects.equals(getRole(), Constants.USER_ROLE_DRIVER);
    }

    public AccessPermission updateRoleToDriver(){
        return updateRole(Constants.USER_ROLE_DRIVER);
    }
    public boolean isRoleCs(){
        return Objects.equals(getRole(), Constants.USER_ROLE_CS);
    }

    public AccessPermission updateRoleToCs(){
        return updateRole(Constants.USER_ROLE_CS);
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
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "resource": this.resource = (value == null ? null : ((String)value).trim()); break;

            case "action": this.action = (value == null ? null : ((String)value).trim()); break;

            case "role": this.role = (UserRole) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "resource": return this.resource;
            case "action": return this.action;
            case "role": return this.role;
            default: return super.__internalGet(property);
        }
    }

}