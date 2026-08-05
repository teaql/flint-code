package com.doublechaintech.enterpriselogisticsservice.userrole;

import com.doublechaintech.enterpriselogisticsservice.accesspermission.AccessPermission;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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

    public static final String NAME_PROPERTY = "name";
    public static final String CODE_PROPERTY = "code";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String ACCESS_PERMISSION_LIST_PROPERTY = "accessPermissionList";
    private String name;
    private String code;
    private String description;
    private SmartList<AccessPermission> accessPermissionList;

    public String getName(){
        return this.name;
    }
    public String getCode(){
        return this.code;
    }
    public String getDescription(){
        return this.description;
    }
    public SmartList<AccessPermission> getAccessPermissionList(){
        return this.accessPermissionList;
    }
    public UserRole updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public UserRole updateCode(String code){
        code = (code == null ? null : code.trim());
        if(Objects.equals(this.code, code)){
            return this;
        }
        handleUpdate(CODE_PROPERTY, getCode(), code);
        this.code = code;
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
    public UserRole addAccessPermission(AccessPermission accessPermission){
        if (accessPermission == null){
            return this;
        }

        if(null == this.accessPermissionList){
            this.accessPermissionList = new SmartList<>();
        }

        this.accessPermissionList.add(accessPermission);
        accessPermission.cacheRelation(AccessPermission.ROLE_PROPERTY, this);
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
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "code": this.code = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "accessPermissionList": this.accessPermissionList = (SmartList<AccessPermission>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "code": return this.code;
            case "description": return this.description;
            case "accessPermissionList": return this.accessPermissionList;
            default: return super.__internalGet(property);
        }
    }

}