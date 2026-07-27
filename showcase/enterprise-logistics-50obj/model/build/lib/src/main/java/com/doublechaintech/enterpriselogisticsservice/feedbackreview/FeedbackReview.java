package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.time.LocalDate;
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
    public static final String COMMENT_PROPERTY = "comment";
    public static final String REVIEW_DATE_PROPERTY = "reviewDate";
    public static final String PRIVATE_CUSTOMER_PROPERTY = "privateCustomer";
    public static final String CORPORATE_CUSTOMER_PROPERTY = "corporateCustomer";
    private Integer rating;
    private String comment;
    private LocalDate reviewDate;
    private PrivateCustomer privateCustomer;
    private CorporateCustomer corporateCustomer;

    public Integer getRating(){
        return this.rating;
    }
    public String getComment(){
        return this.comment;
    }
    public LocalDate getReviewDate(){
        return this.reviewDate;
    }
    public PrivateCustomer getPrivateCustomer(){
        return this.privateCustomer;
    }
    public CorporateCustomer getCorporateCustomer(){
        return this.corporateCustomer;
    }
    public FeedbackReview updateRating(Integer rating){
        if(Objects.equals(this.rating, rating)){
            return this;
        }
        handleUpdate(RATING_PROPERTY, getRating(), rating);
        this.rating = rating;
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
    public FeedbackReview updateReviewDate(LocalDate reviewDate){
        if(Objects.equals(this.reviewDate, reviewDate)){
            return this;
        }
        handleUpdate(REVIEW_DATE_PROPERTY, getReviewDate(), reviewDate);
        this.reviewDate = reviewDate;
        return this;
    }
    public FeedbackReview updatePrivateCustomer(PrivateCustomer privateCustomer){
        if(Objects.equals(this.privateCustomer, privateCustomer)){
            return this;
        }
        handleUpdate(PRIVATE_CUSTOMER_PROPERTY, getPrivateCustomer(), privateCustomer);
        this.privateCustomer = privateCustomer;
        return this;
    }
    public FeedbackReview updateCorporateCustomer(CorporateCustomer corporateCustomer){
        if(Objects.equals(this.corporateCustomer, corporateCustomer)){
            return this;
        }
        handleUpdate(CORPORATE_CUSTOMER_PROPERTY, getCorporateCustomer(), corporateCustomer);
        this.corporateCustomer = corporateCustomer;
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

            case "comment": this.comment = (value == null ? null : ((String)value).trim()); break;

            case "reviewDate": this.reviewDate = (LocalDate) value; break;

            case "privateCustomer": this.privateCustomer = (PrivateCustomer) value; break;

            case "corporateCustomer": this.corporateCustomer = (CorporateCustomer) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "rating": return this.rating;
            case "comment": return this.comment;
            case "reviewDate": return this.reviewDate;
            case "privateCustomer": return this.privateCustomer;
            case "corporateCustomer": return this.corporateCustomer;
            default: return super.__internalGet(property);
        }
    }

}