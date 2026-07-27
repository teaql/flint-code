package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

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
        if(feedbackReview.getCreatedAt() == null){
           feedbackReview.updateCreatedAt(java.time.LocalDateTime.now());
        }if(feedbackReview.getUpdatedAt() == null){
           feedbackReview.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(feedbackReview.updateItem()){
        feedbackReview.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkRating(_ctx, feedbackReview.getProperty(FeedbackReview.RATING_PROPERTY), newLocation(_parentLocation, FeedbackReview.RATING_PROPERTY));
      checkTitle(_ctx, feedbackReview.getProperty(FeedbackReview.TITLE_PROPERTY), newLocation(_parentLocation, FeedbackReview.TITLE_PROPERTY));
      checkComment(_ctx, feedbackReview.getProperty(FeedbackReview.COMMENT_PROPERTY), newLocation(_parentLocation, FeedbackReview.COMMENT_PROPERTY));
      checkMovingOrder(_ctx, feedbackReview.getProperty(FeedbackReview.MOVING_ORDER_PROPERTY), newLocation(_parentLocation, FeedbackReview.MOVING_ORDER_PROPERTY));
      checkCreatedAt(_ctx, feedbackReview.getProperty(FeedbackReview.CREATED_AT_PROPERTY), newLocation(_parentLocation, FeedbackReview.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, feedbackReview.getProperty(FeedbackReview.UPDATED_AT_PROPERTY), newLocation(_parentLocation, FeedbackReview.UPDATED_AT_PROPERTY));
    }

    public void checkRating(UserContext _ctx, Integer rating, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, rating);
    if((rating == null)){
        return;
    }
    }
    public void checkTitle(UserContext _ctx, String title, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, title);
    if((title == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, title);

    }
    public void checkComment(UserContext _ctx, String comment, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, comment);
    if((comment == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, comment);

    }
    public void checkMovingOrder(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, movingOrder);
    if((movingOrder == null)){
        return;
    }
    new MovingOrderChecker().checkAndFix(_ctx, movingOrder, _parentLocation);
    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}