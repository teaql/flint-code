package com.doublechaintech.enterpriselogisticsservice.performancereview;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PerformanceReviewChecker implements Checker<PerformanceReview>{

    public String type(){
        return PerformanceReview.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, PerformanceReview performanceReview, ObjectLocation _parentLocation){
        if(needCheck(_ctx, performanceReview)){
            markAsChecked(_ctx, performanceReview);
            doCheck(_ctx, performanceReview, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, PerformanceReview performanceReview, ObjectLocation _parentLocation){
      if((performanceReview == null)){
         return;
      }
      if(performanceReview.newItem()){
        if(performanceReview.getCreatedAt() == null){
           performanceReview.updateCreatedAt(java.time.LocalDateTime.now());
        }if(performanceReview.getUpdatedAt() == null){
           performanceReview.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(performanceReview.updateItem()){
        performanceReview.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkStaff(_ctx, performanceReview.getProperty(PerformanceReview.STAFF_PROPERTY), newLocation(_parentLocation, PerformanceReview.STAFF_PROPERTY));
      checkReviewer(_ctx, performanceReview.getProperty(PerformanceReview.REVIEWER_PROPERTY), newLocation(_parentLocation, PerformanceReview.REVIEWER_PROPERTY));
      checkReviewDate(_ctx, performanceReview.getProperty(PerformanceReview.REVIEW_DATE_PROPERTY), newLocation(_parentLocation, PerformanceReview.REVIEW_DATE_PROPERTY));
      checkScore(_ctx, performanceReview.getProperty(PerformanceReview.SCORE_PROPERTY), newLocation(_parentLocation, PerformanceReview.SCORE_PROPERTY));
      checkComments(_ctx, performanceReview.getProperty(PerformanceReview.COMMENTS_PROPERTY), newLocation(_parentLocation, PerformanceReview.COMMENTS_PROPERTY));
      checkStatus(_ctx, performanceReview.getProperty(PerformanceReview.STATUS_PROPERTY), newLocation(_parentLocation, PerformanceReview.STATUS_PROPERTY));
      checkCreatedAt(_ctx, performanceReview.getProperty(PerformanceReview.CREATED_AT_PROPERTY), newLocation(_parentLocation, PerformanceReview.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, performanceReview.getProperty(PerformanceReview.UPDATED_AT_PROPERTY), newLocation(_parentLocation, PerformanceReview.UPDATED_AT_PROPERTY));
    }

    public void checkStaff(UserContext _ctx, StaffMember staff, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, staff);
    if((staff == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, staff, _parentLocation);
    }
    public void checkReviewer(UserContext _ctx, StaffMember reviewer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, reviewer);
    if((reviewer == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, reviewer, _parentLocation);
    }
    public void checkReviewDate(UserContext _ctx, LocalDate reviewDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, reviewDate);
    if((reviewDate == null)){
        return;
    }
    }
    public void checkScore(UserContext _ctx, String score, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, score);
    if((score == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, score);

    }
    public void checkComments(UserContext _ctx, String comments, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, comments);
    if((comments == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, comments);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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