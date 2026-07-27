package com.doublechaintech.enterpriselogisticsservice.useraccount;

import com.doublechaintech.enterpriselogisticsservice.auditlog.AuditLog;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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
public class UserAccount extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "UserAccount";

    public static final String USERNAME_PROPERTY = "username";
    public static final String EMAIL_PROPERTY = "email";
    public static final String PHONE_PROPERTY = "phone";
    public static final String STATUS_PROPERTY = "status";
    public static final String PASSWORD_HASH_PROPERTY = "passwordHash";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String AUDIT_LOG_LIST_PROPERTY = "auditLogList";
    private String username;
    private String email;
    private String phone;
    private String status;
    private String passwordHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<AuditLog> auditLogList;

    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getStatus(){
        return this.status;
    }
    public String getPasswordHash(){
        return this.passwordHash;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SmartList<AuditLog> getAuditLogList(){
        return this.auditLogList;
    }
    public UserAccount updateUsername(String username){
        username = (username == null ? null : username.trim());
        if(Objects.equals(this.username, username)){
            return this;
        }
        handleUpdate(USERNAME_PROPERTY, getUsername(), username);
        this.username = username;
        return this;
    }
    public UserAccount updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public UserAccount updatePhone(String phone){
        phone = (phone == null ? null : phone.trim());
        if(Objects.equals(this.phone, phone)){
            return this;
        }
        handleUpdate(PHONE_PROPERTY, getPhone(), phone);
        this.phone = phone;
        return this;
    }
    public UserAccount updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public UserAccount updatePasswordHash(String passwordHash){
        passwordHash = (passwordHash == null ? null : passwordHash.trim());
        if(Objects.equals(this.passwordHash, passwordHash)){
            return this;
        }
        handleUpdate(PASSWORD_HASH_PROPERTY, getPasswordHash(), passwordHash);
        this.passwordHash = passwordHash;
        return this;
    }
    public UserAccount updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public UserAccount updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public UserAccount addAuditLog(AuditLog auditLog){
        if (auditLog == null){
            return this;
        }

        if(null == this.auditLogList){
            this.auditLogList = new SmartList<>();
        }

        this.auditLogList.add(auditLog);
        auditLog.cacheRelation(AuditLog.USER_ACCOUNT_PROPERTY, this);
        return this;
    }

    public static UserAccount refer(Long id){
        UserAccount refer = new UserAccount();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public UserAccount comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<UserAccount> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "username": this.username = (value == null ? null : ((String)value).trim()); break;

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "passwordHash": this.passwordHash = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "auditLogList": this.auditLogList = (SmartList<AuditLog>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "username": return this.username;
            case "email": return this.email;
            case "phone": return this.phone;
            case "status": return this.status;
            case "passwordHash": return this.passwordHash;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "auditLogList": return this.auditLogList;
            default: return super.__internalGet(property);
        }
    }

}