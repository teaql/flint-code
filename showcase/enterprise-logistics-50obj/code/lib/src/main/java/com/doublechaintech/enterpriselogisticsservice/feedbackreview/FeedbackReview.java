package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
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
public class FeedbackReview extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "FeedbackReview";

    public static final String RATING_PROPERTY = "rating";
    public static final String TITLE_PROPERTY = "title";
    public static final String COMMENT_PROPERTY = "comment";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private Integer rating;
    private String title;
    private String comment;
    private MovingOrder movingOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer getRating(){
        return this.rating;
    }
    public String getTitle(){
        return this.title;
    }
    public String getComment(){
        return this.comment;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public FeedbackReview updateRating(Integer rating){
        if(Objects.equals(this.rating, rating)){
            return this;
        }
        handleUpdate(RATING_PROPERTY, getRating(), rating);
        this.rating = rating;
        return this;
    }
    public FeedbackReview updateTitle(String title){
        title = (title == null ? null : title.trim());
        if(Objects.equals(this.title, title)){
            return this;
        }
        handleUpdate(TITLE_PROPERTY, getTitle(), title);
        this.title = title;
        return this;
    }
    public FeedbackReview updateComment(String comment){
        comment = (comment == null ? null : comment.trim());
        if(Objects.equals(this.comment, comment)){
            return this;
        }
        handleUpdate(COMMENT_PROPERTY, getComment(), comment);
        this.comment = comment;
        return this;
    }
    public FeedbackReview updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
    }
    public FeedbackReview updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public FeedbackReview updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static FeedbackReview refer(Long id){
        FeedbackReview refer = new FeedbackReview();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public FeedbackReview comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<FeedbackReview> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "rating": this.rating = (Integer) value; break;

            case "title": this.title = (value == null ? null : ((String)value).trim()); break;

            case "comment": this.comment = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "rating": return this.rating;
            case "title": return this.title;
            case "comment": return this.comment;
            case "movingOrder": return this.movingOrder;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}