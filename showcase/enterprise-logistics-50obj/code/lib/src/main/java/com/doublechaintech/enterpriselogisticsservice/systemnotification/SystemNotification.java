package com.doublechaintech.enterpriselogisticsservice.systemnotification;

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
public class SystemNotification extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "SystemNotification";

    public static final String NOTIFICATION_TYPE_PROPERTY = "notificationType";
    public static final String TITLE_PROPERTY = "title";
    public static final String CONTENT_PROPERTY = "content";
    public static final String IS_READ_PROPERTY = "isRead";
    public static final String RECIPIENT_ID_PROPERTY = "recipientId";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    private String notificationType;
    private String title;
    private String content;
    private String isRead;
    private String recipientId;
    private LocalDateTime createdAt;

    public String getNotificationType(){
        return this.notificationType;
    }
    public String getTitle(){
        return this.title;
    }
    public String getContent(){
        return this.content;
    }
    public String getIsRead(){
        return this.isRead;
    }
    public String getRecipientId(){
        return this.recipientId;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public SystemNotification updateNotificationType(String notificationType){
        notificationType = (notificationType == null ? null : notificationType.trim());
        if(Objects.equals(this.notificationType, notificationType)){
            return this;
        }
        handleUpdate(NOTIFICATION_TYPE_PROPERTY, getNotificationType(), notificationType);
        this.notificationType = notificationType;
        return this;
    }
    public SystemNotification updateTitle(String title){
        title = (title == null ? null : title.trim());
        if(Objects.equals(this.title, title)){
            return this;
        }
        handleUpdate(TITLE_PROPERTY, getTitle(), title);
        this.title = title;
        return this;
    }
    public SystemNotification updateContent(String content){
        content = (content == null ? null : content.trim());
        if(Objects.equals(this.content, content)){
            return this;
        }
        handleUpdate(CONTENT_PROPERTY, getContent(), content);
        this.content = content;
        return this;
    }
    public SystemNotification updateIsRead(String isRead){
        isRead = (isRead == null ? null : isRead.trim());
        if(Objects.equals(this.isRead, isRead)){
            return this;
        }
        handleUpdate(IS_READ_PROPERTY, getIsRead(), isRead);
        this.isRead = isRead;
        return this;
    }
    public SystemNotification updateRecipientId(String recipientId){
        recipientId = (recipientId == null ? null : recipientId.trim());
        if(Objects.equals(this.recipientId, recipientId)){
            return this;
        }
        handleUpdate(RECIPIENT_ID_PROPERTY, getRecipientId(), recipientId);
        this.recipientId = recipientId;
        return this;
    }
    public SystemNotification updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }

    public static SystemNotification refer(Long id){
        SystemNotification refer = new SystemNotification();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public SystemNotification comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<SystemNotification> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "notificationType": this.notificationType = (value == null ? null : ((String)value).trim()); break;

            case "title": this.title = (value == null ? null : ((String)value).trim()); break;

            case "content": this.content = (value == null ? null : ((String)value).trim()); break;

            case "isRead": this.isRead = (value == null ? null : ((String)value).trim()); break;

            case "recipientId": this.recipientId = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "notificationType": return this.notificationType;
            case "title": return this.title;
            case "content": return this.content;
            case "isRead": return this.isRead;
            case "recipientId": return this.recipientId;
            case "createdAt": return this.createdAt;
            default: return super.__internalGet(property);
        }
    }

}