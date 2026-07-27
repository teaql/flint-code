package com.doublechaintech.enterpriselogisticsservice.auditlog;

import com.doublechaintech.enterpriselogisticsservice.useraccount.UserAccount;
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
public class AuditLog extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "AuditLog";

    public static final String ACTION_PROPERTY = "action";
    public static final String ENTITY_TYPE_PROPERTY = "entityType";
    public static final String ENTITY_ID_PROPERTY = "entityId";
    public static final String USER_ACCOUNT_PROPERTY = "userAccount";
    public static final String IP_ADDRESS_PROPERTY = "ipAddress";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    private String action;
    private String entityType;
    private String entityId;
    private UserAccount userAccount;
    private String ipAddress;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;

    public String getAction(){
        return this.action;
    }
    public String getEntityType(){
        return this.entityType;
    }
    public String getEntityId(){
        return this.entityId;
    }
    public UserAccount getUserAccount(){
        return this.userAccount;
    }
    public String getIpAddress(){
        return this.ipAddress;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public AuditLog updateAction(String action){
        action = (action == null ? null : action.trim());
        if(Objects.equals(this.action, action)){
            return this;
        }
        handleUpdate(ACTION_PROPERTY, getAction(), action);
        this.action = action;
        return this;
    }
    public AuditLog updateEntityType(String entityType){
        entityType = (entityType == null ? null : entityType.trim());
        if(Objects.equals(this.entityType, entityType)){
            return this;
        }
        handleUpdate(ENTITY_TYPE_PROPERTY, getEntityType(), entityType);
        this.entityType = entityType;
        return this;
    }
    public AuditLog updateEntityId(String entityId){
        entityId = (entityId == null ? null : entityId.trim());
        if(Objects.equals(this.entityId, entityId)){
            return this;
        }
        handleUpdate(ENTITY_ID_PROPERTY, getEntityId(), entityId);
        this.entityId = entityId;
        return this;
    }
    public AuditLog updateUserAccount(UserAccount userAccount){
        if(Objects.equals(this.userAccount, userAccount)){
            return this;
        }
        handleUpdate(USER_ACCOUNT_PROPERTY, getUserAccount(), userAccount);
        this.userAccount = userAccount;
        return this;
    }
    public AuditLog updateIpAddress(String ipAddress){
        ipAddress = (ipAddress == null ? null : ipAddress.trim());
        if(Objects.equals(this.ipAddress, ipAddress)){
            return this;
        }
        handleUpdate(IP_ADDRESS_PROPERTY, getIpAddress(), ipAddress);
        this.ipAddress = ipAddress;
        return this;
    }
    public AuditLog updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public AuditLog updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }

    public static AuditLog refer(Long id){
        AuditLog refer = new AuditLog();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public AuditLog comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<AuditLog> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "action": this.action = (value == null ? null : ((String)value).trim()); break;

            case "entityType": this.entityType = (value == null ? null : ((String)value).trim()); break;

            case "entityId": this.entityId = (value == null ? null : ((String)value).trim()); break;

            case "userAccount": this.userAccount = (UserAccount) value; break;

            case "ipAddress": this.ipAddress = (value == null ? null : ((String)value).trim()); break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "action": return this.action;
            case "entityType": return this.entityType;
            case "entityId": return this.entityId;
            case "userAccount": return this.userAccount;
            case "ipAddress": return this.ipAddress;
            case "createdTime": return this.createdTime;
            case "updateTime": return this.updateTime;
            default: return super.__internalGet(property);
        }
    }

}