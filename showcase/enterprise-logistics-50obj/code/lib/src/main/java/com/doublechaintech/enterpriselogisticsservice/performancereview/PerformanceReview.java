package com.doublechaintech.enterpriselogisticsservice.performancereview;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.time.LocalDate;
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
public class PerformanceReview extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "PerformanceReview";

    public static final String STAFF_PROPERTY = "staff";
    public static final String REVIEWER_PROPERTY = "reviewer";
    public static final String REVIEW_DATE_PROPERTY = "reviewDate";
    public static final String SCORE_PROPERTY = "score";
    public static final String COMMENTS_PROPERTY = "comments";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private StaffMember staff;
    private StaffMember reviewer;
    private LocalDate reviewDate;
    private String score;
    private String comments;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StaffMember getStaff(){
        return this.staff;
    }
    public StaffMember getReviewer(){
        return this.reviewer;
    }
    public LocalDate getReviewDate(){
        return this.reviewDate;
    }
    public String getScore(){
        return this.score;
    }
    public String getComments(){
        return this.comments;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public PerformanceReview updateStaff(StaffMember staff){
        if(Objects.equals(this.staff, staff)){
            return this;
        }
        handleUpdate(STAFF_PROPERTY, getStaff(), staff);
        this.staff = staff;
        return this;
    }
    public PerformanceReview updateReviewer(StaffMember reviewer){
        if(Objects.equals(this.reviewer, reviewer)){
            return this;
        }
        handleUpdate(REVIEWER_PROPERTY, getReviewer(), reviewer);
        this.reviewer = reviewer;
        return this;
    }
    public PerformanceReview updateReviewDate(LocalDate reviewDate){
        if(Objects.equals(this.reviewDate, reviewDate)){
            return this;
        }
        handleUpdate(REVIEW_DATE_PROPERTY, getReviewDate(), reviewDate);
        this.reviewDate = reviewDate;
        return this;
    }
    public PerformanceReview updateScore(String score){
        score = (score == null ? null : score.trim());
        if(Objects.equals(this.score, score)){
            return this;
        }
        handleUpdate(SCORE_PROPERTY, getScore(), score);
        this.score = score;
        return this;
    }
    public PerformanceReview updateComments(String comments){
        comments = (comments == null ? null : comments.trim());
        if(Objects.equals(this.comments, comments)){
            return this;
        }
        handleUpdate(COMMENTS_PROPERTY, getComments(), comments);
        this.comments = comments;
        return this;
    }
    public PerformanceReview updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public PerformanceReview updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public PerformanceReview updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static PerformanceReview refer(Long id){
        PerformanceReview refer = new PerformanceReview();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public PerformanceReview comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<PerformanceReview> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "staff": this.staff = (StaffMember) value; break;

            case "reviewer": this.reviewer = (StaffMember) value; break;

            case "reviewDate": this.reviewDate = (LocalDate) value; break;

            case "score": this.score = (value == null ? null : ((String)value).trim()); break;

            case "comments": this.comments = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "staff": return this.staff;
            case "reviewer": return this.reviewer;
            case "reviewDate": return this.reviewDate;
            case "score": return this.score;
            case "comments": return this.comments;
            case "status": return this.status;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}