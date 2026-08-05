package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;

public class FeedbackReviewChecker implements Checker<FeedbackReview>{

    public String type(){
        return FeedbackReview.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, FeedbackReview feedbackReview, ObjectLocation _parentLocation){
        if(needCheck(_ctx, feedbackReview)){
            markAsChecked(_ctx, feedbackReview);
            doCheck(_ctx, feedbackReview, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, FeedbackReview feedbackReview, ObjectLocation _parentLocation){
      if((feedbackReview == null)){
         return;
      }
      if(feedbackReview.newItem()){
      }else if(feedbackReview.updateItem()){
      }
      checkRating(_ctx, feedbackReview.getProperty(FeedbackReview.RATING_PROPERTY), newLocation(_parentLocation, FeedbackReview.RATING_PROPERTY));
      checkComment(_ctx, feedbackReview.getProperty(FeedbackReview.COMMENT_PROPERTY), newLocation(_parentLocation, FeedbackReview.COMMENT_PROPERTY));
      checkReviewDate(_ctx, feedbackReview.getProperty(FeedbackReview.REVIEW_DATE_PROPERTY), newLocation(_parentLocation, FeedbackReview.REVIEW_DATE_PROPERTY));
      checkPrivateCustomer(_ctx, feedbackReview.getProperty(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, FeedbackReview.PRIVATE_CUSTOMER_PROPERTY));
      checkCorporateCustomer(_ctx, feedbackReview.getProperty(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY), newLocation(_parentLocation, FeedbackReview.CORPORATE_CUSTOMER_PROPERTY));
    }

    public void checkRating(UserContext _ctx, Integer rating, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, rating);
    if((rating == null)){
        return;
    }
    }
    public void checkComment(UserContext _ctx, String comment, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, comment);
    if((comment == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, comment);

    }
    public void checkReviewDate(UserContext _ctx, LocalDate reviewDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, reviewDate);
    if((reviewDate == null)){
        return;
    }
    }
    public void checkPrivateCustomer(UserContext _ctx, PrivateCustomer privateCustomer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, privateCustomer);
    if((privateCustomer == null)){
        return;
    }
    new PrivateCustomerChecker().checkAndFix(_ctx, privateCustomer, _parentLocation);
    }
    public void checkCorporateCustomer(UserContext _ctx, CorporateCustomer corporateCustomer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, corporateCustomer);
    if((corporateCustomer == null)){
        return;
    }
    new CorporateCustomerChecker().checkAndFix(_ctx, corporateCustomer, _parentLocation);
    }
}