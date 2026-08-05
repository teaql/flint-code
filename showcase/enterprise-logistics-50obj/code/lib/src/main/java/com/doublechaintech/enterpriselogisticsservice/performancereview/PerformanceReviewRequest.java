package com.doublechaintech.enterpriselogisticsservice.performancereview;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class PerformanceReviewRequest<T extends PerformanceReview> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public PerformanceReviewRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public PerformanceReviewRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public PerformanceReviewRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public PerformanceReviewRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public PerformanceReviewRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public PerformanceReviewRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public PerformanceReviewRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (PerformanceReviewRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public PerformanceReviewRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public PerformanceReviewRequest<T> matchingAnyOf(PerformanceReviewRequest performanceReview){
        super.internalMatchAny(performanceReview);
        return this;
    }

    public PerformanceReviewRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public PerformanceReviewRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public PerformanceReviewRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public PerformanceReviewRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectStaffIdOnly().selectReviewerIdOnly().selectReviewDate().selectScore().selectComments().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public PerformanceReviewRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public PerformanceReviewRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectStaff().selectReviewer().selectReviewDate().selectScore().selectComments().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public PerformanceReviewRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectStaff().selectReviewer().selectReviewDate().selectScore().selectComments().selectStatus().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public PerformanceReviewRequest<T> selectId(){
       selectProperty(PerformanceReview.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectId(){
       unselectProperty(PerformanceReview.ID_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectStaffIdOnly(){
       selectProperty(PerformanceReview.STAFF_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> selectStaff(){
        return selectStaffWith(Q.staffMembers().unlimited().selectSelf());
    }

    public PerformanceReviewRequest<T> selectStaffWith(StaffMemberRequest staff){
       selectProperty(PerformanceReview.STAFF_PROPERTY);
       enhanceRelation(PerformanceReview.STAFF_PROPERTY, staff);
       return this;
    }

    public PerformanceReviewRequest<T> unselectStaff(){
       unselectProperty(PerformanceReview.STAFF_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectReviewerIdOnly(){
       selectProperty(PerformanceReview.REVIEWER_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> selectReviewer(){
        return selectReviewerWith(Q.staffMembers().unlimited().selectSelf());
    }

    public PerformanceReviewRequest<T> selectReviewerWith(StaffMemberRequest reviewer){
       selectProperty(PerformanceReview.REVIEWER_PROPERTY);
       enhanceRelation(PerformanceReview.REVIEWER_PROPERTY, reviewer);
       return this;
    }

    public PerformanceReviewRequest<T> unselectReviewer(){
       unselectProperty(PerformanceReview.REVIEWER_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectReviewDate(){
       selectProperty(PerformanceReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the reviewDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  reviewDate) to fetch reviewDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectReviewDate(){
       unselectProperty(PerformanceReview.REVIEW_DATE_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectScore(){
       selectProperty(PerformanceReview.SCORE_PROPERTY);
       return this;
    }

    /**
     * fill the score with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  score) to fetch score property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectScore(){
       unselectProperty(PerformanceReview.SCORE_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectComments(){
       selectProperty(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }

    /**
     * fill the comments with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  comments) to fetch comments property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectComments(){
       unselectProperty(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectStatus(){
       selectProperty(PerformanceReview.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectStatus(){
       unselectProperty(PerformanceReview.STATUS_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectCreatedAt(){
       selectProperty(PerformanceReview.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectCreatedAt(){
       unselectProperty(PerformanceReview.CREATED_AT_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectUpdatedAt(){
       selectProperty(PerformanceReview.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectUpdatedAt(){
       unselectProperty(PerformanceReview.UPDATED_AT_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> selectVersion(){
       selectProperty(PerformanceReview.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public PerformanceReviewRequest<T> unselectVersion(){
       unselectProperty(PerformanceReview.VERSION_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.ID_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public PerformanceReviewRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public PerformanceReviewRequest<T> filterByStaff(StaffMember... staff){
      if (staff == null || staff.length == 0) {
        throw new IllegalArgumentException("filterByStaff parameter staff cannot be empty");
      }
      return appendSearchCriteria(createStaffCriteria(Operator.EQUAL, (Object[])staff));
    }

    public PerformanceReviewRequest<T> withStaff(Operator operator, Object... values){
       return appendSearchCriteria(createStaffCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withStaffIsUnknown(){
       return withStaff(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withStaffIsKnown(){
       return withStaff(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStaffCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.STAFF_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> filterByStaff(Long staff){
      if(staff == null){
         return this;
      }
      return withStaff(Operator.EQUAL, staff);
    }
    public PerformanceReviewRequest<T> withStaffMatching(StaffMemberRequest staff){
       return appendSearchCriteria(new SubQuerySearchCriteria(PerformanceReview.STAFF_PROPERTY, staff, StaffMember.ID_PROPERTY));
    }

    public PerformanceReviewRequest<T> filterByReviewer(StaffMember... reviewer){
      if (reviewer == null || reviewer.length == 0) {
        throw new IllegalArgumentException("filterByReviewer parameter reviewer cannot be empty");
      }
      return appendSearchCriteria(createReviewerCriteria(Operator.EQUAL, (Object[])reviewer));
    }

    public PerformanceReviewRequest<T> withReviewer(Operator operator, Object... values){
       return appendSearchCriteria(createReviewerCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withReviewerIsUnknown(){
       return withReviewer(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withReviewerIsKnown(){
       return withReviewer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createReviewerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.REVIEWER_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> filterByReviewer(Long reviewer){
      if(reviewer == null){
         return this;
      }
      return withReviewer(Operator.EQUAL, reviewer);
    }
    public PerformanceReviewRequest<T> withReviewerMatching(StaffMemberRequest reviewer){
       return appendSearchCriteria(new SubQuerySearchCriteria(PerformanceReview.REVIEWER_PROPERTY, reviewer, StaffMember.ID_PROPERTY));
    }

    public PerformanceReviewRequest<T> filterByReviewDate(LocalDate... reviewDate){
      if (reviewDate == null || reviewDate.length == 0) {
        throw new IllegalArgumentException("filterByReviewDate parameter reviewDate cannot be empty");
      }
      return appendSearchCriteria(createReviewDateCriteria(Operator.EQUAL, (Object[])reviewDate));
    }

    public PerformanceReviewRequest<T> withReviewDate(Operator operator, Object... values){
       return appendSearchCriteria(createReviewDateCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withReviewDateIsUnknown(){
       return withReviewDate(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withReviewDateIsKnown(){
       return withReviewDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createReviewDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.REVIEW_DATE_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withReviewDateGreaterThan(LocalDate reviewDate){
       return withReviewDate(Operator.GREATER_THAN, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateGreaterThanOrEqualTo(LocalDate reviewDate){
       return withReviewDate(Operator.GREATER_THAN_OR_EQUAL, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateLessThan(LocalDate reviewDate){
       return withReviewDate(Operator.LESS_THAN, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateLessThanOrEqualTo(LocalDate reviewDate){
       return withReviewDate(Operator.LESS_THAN_OR_EQUAL, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateBetween(LocalDate startOfReviewDate, LocalDate endOfReviewDate){
       return withReviewDate(Operator.BETWEEN, startOfReviewDate, endOfReviewDate);
    }
    public PerformanceReviewRequest<T> withReviewDateBefore(LocalDate reviewDate){
       return withReviewDate(Operator.LESS_THAN, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateBefore(Date reviewDate){
       return withReviewDate(Operator.LESS_THAN, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateAfter(LocalDate reviewDate){
       return withReviewDate(Operator.GREATER_THAN, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateAfter(Date reviewDate){
       return withReviewDate(Operator.GREATER_THAN, reviewDate);
    }

    public PerformanceReviewRequest<T> withReviewDateBetween(Date startOfReviewDate, Date endOfReviewDate){
       return withReviewDate(Operator.BETWEEN, startOfReviewDate, endOfReviewDate);
    }




    public PerformanceReviewRequest<T> filterByScore(String... score){
      if (score == null || score.length == 0) {
        throw new IllegalArgumentException("filterByScore parameter score cannot be empty");
      }
      return appendSearchCriteria(createScoreCriteria(Operator.EQUAL, (Object[])score));
    }

    public PerformanceReviewRequest<T> withScore(Operator operator, Object... values){
       return appendSearchCriteria(createScoreCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withScoreIsUnknown(){
       return withScore(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withScoreIsKnown(){
       return withScore(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createScoreCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.SCORE_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withScoreGreaterThan(String score){
       return withScore(Operator.GREATER_THAN, score);
    }

    public PerformanceReviewRequest<T> withScoreGreaterThanOrEqualTo(String score){
       return withScore(Operator.GREATER_THAN_OR_EQUAL, score);
    }

    public PerformanceReviewRequest<T> withScoreLessThan(String score){
       return withScore(Operator.LESS_THAN, score);
    }

    public PerformanceReviewRequest<T> withScoreLessThanOrEqualTo(String score){
       return withScore(Operator.LESS_THAN_OR_EQUAL, score);
    }

    public PerformanceReviewRequest<T> withScoreBetween(String startOfScore, String endOfScore){
       return withScore(Operator.BETWEEN, startOfScore, endOfScore);
    }
    public PerformanceReviewRequest<T> withScoreStartingWith(String score){
       return withScore(Operator.BEGIN_WITH, score);
    }
    public PerformanceReviewRequest<T> withScoreContaining(String score){
       return withScore(Operator.CONTAIN, score);
    }

    public PerformanceReviewRequest<T> withScoreEndingWith(String score){
       return withScore(Operator.END_WITH, score);
    }

    public PerformanceReviewRequest<T> withScoreIs(String score){
       return withScore(Operator.EQUAL, score);
    }

    public PerformanceReviewRequest<T> withScoreSoundingLike(String score){
       return withScore(Operator.SOUNDS_LIKE, score);
    }



    public PerformanceReviewRequest<T> filterByComments(String... comments){
      if (comments == null || comments.length == 0) {
        throw new IllegalArgumentException("filterByComments parameter comments cannot be empty");
      }
      return appendSearchCriteria(createCommentsCriteria(Operator.EQUAL, (Object[])comments));
    }

    public PerformanceReviewRequest<T> withComments(Operator operator, Object... values){
       return appendSearchCriteria(createCommentsCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withCommentsIsUnknown(){
       return withComments(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withCommentsIsKnown(){
       return withComments(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCommentsCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.COMMENTS_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withCommentsGreaterThan(String comments){
       return withComments(Operator.GREATER_THAN, comments);
    }

    public PerformanceReviewRequest<T> withCommentsGreaterThanOrEqualTo(String comments){
       return withComments(Operator.GREATER_THAN_OR_EQUAL, comments);
    }

    public PerformanceReviewRequest<T> withCommentsLessThan(String comments){
       return withComments(Operator.LESS_THAN, comments);
    }

    public PerformanceReviewRequest<T> withCommentsLessThanOrEqualTo(String comments){
       return withComments(Operator.LESS_THAN_OR_EQUAL, comments);
    }

    public PerformanceReviewRequest<T> withCommentsBetween(String startOfComments, String endOfComments){
       return withComments(Operator.BETWEEN, startOfComments, endOfComments);
    }
    public PerformanceReviewRequest<T> withCommentsStartingWith(String comments){
       return withComments(Operator.BEGIN_WITH, comments);
    }
    public PerformanceReviewRequest<T> withCommentsContaining(String comments){
       return withComments(Operator.CONTAIN, comments);
    }

    public PerformanceReviewRequest<T> withCommentsEndingWith(String comments){
       return withComments(Operator.END_WITH, comments);
    }

    public PerformanceReviewRequest<T> withCommentsIs(String comments){
       return withComments(Operator.EQUAL, comments);
    }

    public PerformanceReviewRequest<T> withCommentsSoundingLike(String comments){
       return withComments(Operator.SOUNDS_LIKE, comments);
    }



    public PerformanceReviewRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public PerformanceReviewRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.STATUS_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public PerformanceReviewRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public PerformanceReviewRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public PerformanceReviewRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public PerformanceReviewRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public PerformanceReviewRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public PerformanceReviewRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public PerformanceReviewRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public PerformanceReviewRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public PerformanceReviewRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public PerformanceReviewRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public PerformanceReviewRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.CREATED_AT_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public PerformanceReviewRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public PerformanceReviewRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public PerformanceReviewRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public PerformanceReviewRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.UPDATED_AT_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public PerformanceReviewRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public PerformanceReviewRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public PerformanceReviewRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public PerformanceReviewRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public PerformanceReviewRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public PerformanceReviewRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(PerformanceReview.VERSION_PROPERTY, operator, values);
    }

    public PerformanceReviewRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public PerformanceReviewRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public PerformanceReviewRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public PerformanceReviewRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public PerformanceReviewRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public PerformanceReviewRequest<T> count(){
        super.count();
        return this;
    }
    public PerformanceReviewRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public PerformanceReviewRequest<T> groupByStaffWithDetails(){
       return groupByStaffWithDetails(Q.staffMembers().unlimited());
    }

    public PerformanceReviewRequest<T> groupByStaffWithDetails(StaffMemberRequest subRequest){
       aggregate(PerformanceReview.STAFF_PROPERTY, subRequest);
       return this;
    }

    public PerformanceReviewRequest<T> groupByReviewerWithDetails(){
       return groupByReviewerWithDetails(Q.staffMembers().unlimited());
    }

    public PerformanceReviewRequest<T> groupByReviewerWithDetails(StaffMemberRequest subRequest){
       aggregate(PerformanceReview.REVIEWER_PROPERTY, subRequest);
       return this;
    }









    public PerformanceReviewRequest<T> groupById(){
       groupBy(PerformanceReview.ID_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByIdAs(String retName){
       groupBy(retName, PerformanceReview.ID_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.ID_PROPERTY, function);
       return this;
    }
    public PerformanceReviewRequest<T> groupByStaffWith(StaffMemberRequest subRequest){
       groupBy(PerformanceReview.STAFF_PROPERTY, subRequest);
       return this;
    }
    public PerformanceReviewRequest<T> groupByStaff(){
       groupBy(PerformanceReview.STAFF_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByStaffAs(String retName){
       groupBy(retName, PerformanceReview.STAFF_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByStaffWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.STAFF_PROPERTY, function);
       return this;
    }
    public PerformanceReviewRequest<T> groupByReviewerWith(StaffMemberRequest subRequest){
       groupBy(PerformanceReview.REVIEWER_PROPERTY, subRequest);
       return this;
    }
    public PerformanceReviewRequest<T> groupByReviewer(){
       groupBy(PerformanceReview.REVIEWER_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByReviewerAs(String retName){
       groupBy(retName, PerformanceReview.REVIEWER_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByReviewerWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.REVIEWER_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByReviewDate(){
       groupBy(PerformanceReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByReviewDateAs(String retName){
       groupBy(retName, PerformanceReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByReviewDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.REVIEW_DATE_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByScore(){
       groupBy(PerformanceReview.SCORE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByScoreAs(String retName){
       groupBy(retName, PerformanceReview.SCORE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByScoreWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.SCORE_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByComments(){
       groupBy(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByCommentsAs(String retName){
       groupBy(retName, PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByCommentsWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.COMMENTS_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByStatus(){
       groupBy(PerformanceReview.STATUS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByStatusAs(String retName){
       groupBy(retName, PerformanceReview.STATUS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.STATUS_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByCreatedAt(){
       groupBy(PerformanceReview.CREATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, PerformanceReview.CREATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.CREATED_AT_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByUpdatedAt(){
       groupBy(PerformanceReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, PerformanceReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public PerformanceReviewRequest<T> groupByVersion(){
       groupBy(PerformanceReview.VERSION_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByVersionAs(String retName){
       groupBy(retName, PerformanceReview.VERSION_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, PerformanceReview.VERSION_PROPERTY, function);
       return this;
    }



    public PerformanceReviewRequest<T> orderByIdAscending(){
       addOrderByAscending(PerformanceReview.ID_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByIdDescending(){
       addOrderByDescending(PerformanceReview.ID_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByStaffAscending(){
       addOrderByAscending(PerformanceReview.STAFF_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByStaffDescending(){
       addOrderByDescending(PerformanceReview.STAFF_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByReviewerAscending(){
       addOrderByAscending(PerformanceReview.REVIEWER_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByReviewerDescending(){
       addOrderByDescending(PerformanceReview.REVIEWER_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByReviewDateAscending(){
       addOrderByAscending(PerformanceReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByReviewDateDescending(){
       addOrderByDescending(PerformanceReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByScoreAscending(){
       addOrderByAscending(PerformanceReview.SCORE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByScoreDescending(){
       addOrderByDescending(PerformanceReview.SCORE_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> orderByScoreAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PerformanceReview.SCORE_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByScoreDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PerformanceReview.SCORE_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> orderByCommentsAscending(){
       addOrderByAscending(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByCommentsDescending(){
       addOrderByDescending(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> orderByCommentsAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByCommentsDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PerformanceReview.COMMENTS_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> orderByStatusAscending(){
       addOrderByAscending(PerformanceReview.STATUS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByStatusDescending(){
       addOrderByDescending(PerformanceReview.STATUS_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(PerformanceReview.STATUS_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(PerformanceReview.STATUS_PROPERTY);
       return this;
    }
    public PerformanceReviewRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(PerformanceReview.CREATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(PerformanceReview.CREATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(PerformanceReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(PerformanceReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByVersionAscending(){
       addOrderByAscending(PerformanceReview.VERSION_PROPERTY);
       return this;
    }

    public PerformanceReviewRequest<T> orderByVersionDescending(){
       addOrderByDescending(PerformanceReview.VERSION_PROPERTY);
       return this;
    }


    public StaffMemberRequest rollUpToStaff(){
       StaffMemberRequest staff = Q.staffMembers().unlimited();
       this.withStaffMatching(staff)
           .groupByStaffWith(staff);
       return staff;
    }

    public StaffMemberRequest rollUpToReviewer(){
       StaffMemberRequest reviewer = Q.staffMembers().unlimited();
       this.withReviewerMatching(reviewer)
           .groupByReviewerWith(reviewer);
       return reviewer;
    }









   public PerformanceReviewRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff){
       return facetByStaffAs(facetName, staff, true);
   }

   public PerformanceReviewRequest<T> facetByStaffAs(String facetName, StaffMemberRequest staff, boolean includeAllFacets){
       addFacet(facetName, PerformanceReview.STAFF_PROPERTY, staff, includeAllFacets);
       return this;
   }
   public PerformanceReviewRequest<T> facetByReviewerAs(String facetName, StaffMemberRequest reviewer){
       return facetByReviewerAs(facetName, reviewer, true);
   }

   public PerformanceReviewRequest<T> facetByReviewerAs(String facetName, StaffMemberRequest reviewer, boolean includeAllFacets){
       addFacet(facetName, PerformanceReview.REVIEWER_PROPERTY, reviewer, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public PerformanceReviewRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public PerformanceReviewRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public PerformanceReviewRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public PerformanceReviewRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public PerformanceReviewRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}