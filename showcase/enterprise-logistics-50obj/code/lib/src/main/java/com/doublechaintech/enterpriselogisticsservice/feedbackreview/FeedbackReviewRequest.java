package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDateTime;
import java.util.Date;

public class FeedbackReviewRequest<T extends FeedbackReview> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public FeedbackReviewRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public FeedbackReviewRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public FeedbackReviewRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public FeedbackReviewRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public FeedbackReviewRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public FeedbackReviewRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public FeedbackReviewRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (FeedbackReviewRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public FeedbackReviewRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public FeedbackReviewRequest<T> matchingAnyOf(FeedbackReviewRequest feedbackReview){
        super.internalMatchAny(feedbackReview);
        return this;
    }

    public FeedbackReviewRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public FeedbackReviewRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public FeedbackReviewRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public FeedbackReviewRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectRating().selectTitle().selectComment().selectMovingOrderIdOnly().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public FeedbackReviewRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public FeedbackReviewRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectRating().selectTitle().selectComment().selectMovingOrder().selectCreatedAt().selectUpdatedAt().selectVersion();
    }

    public FeedbackReviewRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectRating().selectTitle().selectComment().selectMovingOrder().selectCreatedAt().selectUpdatedAt().selectVersion();
    }


    public FeedbackReviewRequest<T> selectId(){
       selectProperty(FeedbackReview.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectId(){
       unselectProperty(FeedbackReview.ID_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectRating(){
       selectProperty(FeedbackReview.RATING_PROPERTY);
       return this;
    }

    /**
     * fill the rating with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  rating) to fetch rating property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the rating with customized aggrFunction, TEAQL uses ({aggrFunction}(rating) AS rating to fetch rating property.
     * @param aggrFunction  aggrFunction
     */
    public FeedbackReviewRequest<T> selectRating(AggrFunction aggrFunction){
       selectProperty(FeedbackReview.RATING_PROPERTY, aggrFunction);
       return this;
    }


    public FeedbackReviewRequest<T> unselectRating(){
       unselectProperty(FeedbackReview.RATING_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectTitle(){
       selectProperty(FeedbackReview.TITLE_PROPERTY);
       return this;
    }

    /**
     * fill the title with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  title) to fetch title property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectTitle(){
       unselectProperty(FeedbackReview.TITLE_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectComment(){
       selectProperty(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }

    /**
     * fill the comment with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  comment) to fetch comment property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectComment(){
       unselectProperty(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectMovingOrderIdOnly(){
       selectProperty(FeedbackReview.MOVING_ORDER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> selectMovingOrder(){
        return selectMovingOrderWith(Q.movingOrders().unlimited().selectSelf());
    }

    public FeedbackReviewRequest<T> selectMovingOrderWith(MovingOrderRequest movingOrder){
       selectProperty(FeedbackReview.MOVING_ORDER_PROPERTY);
       enhanceRelation(FeedbackReview.MOVING_ORDER_PROPERTY, movingOrder);
       return this;
    }

    public FeedbackReviewRequest<T> unselectMovingOrder(){
       unselectProperty(FeedbackReview.MOVING_ORDER_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectCreatedAt(){
       selectProperty(FeedbackReview.CREATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the createdAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdAt) to fetch createdAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectCreatedAt(){
       unselectProperty(FeedbackReview.CREATED_AT_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectUpdatedAt(){
       selectProperty(FeedbackReview.UPDATED_AT_PROPERTY);
       return this;
    }

    /**
     * fill the updatedAt with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedAt) to fetch updatedAt property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectUpdatedAt(){
       unselectProperty(FeedbackReview.UPDATED_AT_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectVersion(){
       selectProperty(FeedbackReview.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectVersion(){
       unselectProperty(FeedbackReview.VERSION_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.ID_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public FeedbackReviewRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public FeedbackReviewRequest<T> filterByRating(Integer... rating){
      if (rating == null || rating.length == 0) {
        throw new IllegalArgumentException("filterByRating parameter rating cannot be empty");
      }
      return appendSearchCriteria(createRatingCriteria(Operator.EQUAL, (Object[])rating));
    }

    public FeedbackReviewRequest<T> withRating(Operator operator, Object... values){
       return appendSearchCriteria(createRatingCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withRatingIsUnknown(){
       return withRating(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withRatingIsKnown(){
       return withRating(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createRatingCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.RATING_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withRatingGreaterThan(Integer rating){
       return withRating(Operator.GREATER_THAN, rating);
    }

    public FeedbackReviewRequest<T> withRatingGreaterThanOrEqualTo(Integer rating){
       return withRating(Operator.GREATER_THAN_OR_EQUAL, rating);
    }

    public FeedbackReviewRequest<T> withRatingLessThan(Integer rating){
       return withRating(Operator.LESS_THAN, rating);
    }

    public FeedbackReviewRequest<T> withRatingLessThanOrEqualTo(Integer rating){
       return withRating(Operator.LESS_THAN_OR_EQUAL, rating);
    }

    public FeedbackReviewRequest<T> withRatingBetween(Integer startOfRating, Integer endOfRating){
       return withRating(Operator.BETWEEN, startOfRating, endOfRating);
    }



    public FeedbackReviewRequest<T> filterByTitle(String... title){
      if (title == null || title.length == 0) {
        throw new IllegalArgumentException("filterByTitle parameter title cannot be empty");
      }
      return appendSearchCriteria(createTitleCriteria(Operator.EQUAL, (Object[])title));
    }

    public FeedbackReviewRequest<T> withTitle(Operator operator, Object... values){
       return appendSearchCriteria(createTitleCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withTitleIsUnknown(){
       return withTitle(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withTitleIsKnown(){
       return withTitle(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTitleCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.TITLE_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withTitleGreaterThan(String title){
       return withTitle(Operator.GREATER_THAN, title);
    }

    public FeedbackReviewRequest<T> withTitleGreaterThanOrEqualTo(String title){
       return withTitle(Operator.GREATER_THAN_OR_EQUAL, title);
    }

    public FeedbackReviewRequest<T> withTitleLessThan(String title){
       return withTitle(Operator.LESS_THAN, title);
    }

    public FeedbackReviewRequest<T> withTitleLessThanOrEqualTo(String title){
       return withTitle(Operator.LESS_THAN_OR_EQUAL, title);
    }

    public FeedbackReviewRequest<T> withTitleBetween(String startOfTitle, String endOfTitle){
       return withTitle(Operator.BETWEEN, startOfTitle, endOfTitle);
    }
    public FeedbackReviewRequest<T> withTitleStartingWith(String title){
       return withTitle(Operator.BEGIN_WITH, title);
    }
    public FeedbackReviewRequest<T> withTitleContaining(String title){
       return withTitle(Operator.CONTAIN, title);
    }

    public FeedbackReviewRequest<T> withTitleEndingWith(String title){
       return withTitle(Operator.END_WITH, title);
    }

    public FeedbackReviewRequest<T> withTitleIs(String title){
       return withTitle(Operator.EQUAL, title);
    }

    public FeedbackReviewRequest<T> withTitleSoundingLike(String title){
       return withTitle(Operator.SOUNDS_LIKE, title);
    }



    public FeedbackReviewRequest<T> filterByComment(String... comment){
      if (comment == null || comment.length == 0) {
        throw new IllegalArgumentException("filterByComment parameter comment cannot be empty");
      }
      return appendSearchCriteria(createCommentCriteria(Operator.EQUAL, (Object[])comment));
    }

    public FeedbackReviewRequest<T> withComment(Operator operator, Object... values){
       return appendSearchCriteria(createCommentCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withCommentIsUnknown(){
       return withComment(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withCommentIsKnown(){
       return withComment(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCommentCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.COMMENT_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withCommentGreaterThan(String comment){
       return withComment(Operator.GREATER_THAN, comment);
    }

    public FeedbackReviewRequest<T> withCommentGreaterThanOrEqualTo(String comment){
       return withComment(Operator.GREATER_THAN_OR_EQUAL, comment);
    }

    public FeedbackReviewRequest<T> withCommentLessThan(String comment){
       return withComment(Operator.LESS_THAN, comment);
    }

    public FeedbackReviewRequest<T> withCommentLessThanOrEqualTo(String comment){
       return withComment(Operator.LESS_THAN_OR_EQUAL, comment);
    }

    public FeedbackReviewRequest<T> withCommentBetween(String startOfComment, String endOfComment){
       return withComment(Operator.BETWEEN, startOfComment, endOfComment);
    }
    public FeedbackReviewRequest<T> withCommentStartingWith(String comment){
       return withComment(Operator.BEGIN_WITH, comment);
    }
    public FeedbackReviewRequest<T> withCommentContaining(String comment){
       return withComment(Operator.CONTAIN, comment);
    }

    public FeedbackReviewRequest<T> withCommentEndingWith(String comment){
       return withComment(Operator.END_WITH, comment);
    }

    public FeedbackReviewRequest<T> withCommentIs(String comment){
       return withComment(Operator.EQUAL, comment);
    }

    public FeedbackReviewRequest<T> withCommentSoundingLike(String comment){
       return withComment(Operator.SOUNDS_LIKE, comment);
    }



    public FeedbackReviewRequest<T> filterByMovingOrder(MovingOrder... movingOrder){
      if (movingOrder == null || movingOrder.length == 0) {
        throw new IllegalArgumentException("filterByMovingOrder parameter movingOrder cannot be empty");
      }
      return appendSearchCriteria(createMovingOrderCriteria(Operator.EQUAL, (Object[])movingOrder));
    }

    public FeedbackReviewRequest<T> withMovingOrder(Operator operator, Object... values){
       return appendSearchCriteria(createMovingOrderCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withMovingOrderIsUnknown(){
       return withMovingOrder(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withMovingOrderIsKnown(){
       return withMovingOrder(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createMovingOrderCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.MOVING_ORDER_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> filterByMovingOrder(Long movingOrder){
      if(movingOrder == null){
         return this;
      }
      return withMovingOrder(Operator.EQUAL, movingOrder);
    }
    public FeedbackReviewRequest<T> withMovingOrderMatching(MovingOrderRequest movingOrder){
       return appendSearchCriteria(new SubQuerySearchCriteria(FeedbackReview.MOVING_ORDER_PROPERTY, movingOrder, MovingOrder.ID_PROPERTY));
    }

    public FeedbackReviewRequest<T> filterByCreatedAt(LocalDateTime... createdAt){
      if (createdAt == null || createdAt.length == 0) {
        throw new IllegalArgumentException("filterByCreatedAt parameter createdAt cannot be empty");
      }
      return appendSearchCriteria(createCreatedAtCriteria(Operator.EQUAL, (Object[])createdAt));
    }

    public FeedbackReviewRequest<T> withCreatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedAtCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withCreatedAtIsUnknown(){
       return withCreatedAt(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withCreatedAtIsKnown(){
       return withCreatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.CREATED_AT_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withCreatedAtGreaterThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtGreaterThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN_OR_EQUAL, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtLessThan(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtLessThanOrEqualTo(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN_OR_EQUAL, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtBetween(LocalDateTime startOfCreatedAt, LocalDateTime endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }
    public FeedbackReviewRequest<T> withCreatedAtBefore(LocalDateTime createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtBefore(Date createdAt){
       return withCreatedAt(Operator.LESS_THAN, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtAfter(LocalDateTime createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtAfter(Date createdAt){
       return withCreatedAt(Operator.GREATER_THAN, createdAt);
    }

    public FeedbackReviewRequest<T> withCreatedAtBetween(Date startOfCreatedAt, Date endOfCreatedAt){
       return withCreatedAt(Operator.BETWEEN, startOfCreatedAt, endOfCreatedAt);
    }




    public FeedbackReviewRequest<T> filterByUpdatedAt(LocalDateTime... updatedAt){
      if (updatedAt == null || updatedAt.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedAt parameter updatedAt cannot be empty");
      }
      return appendSearchCriteria(createUpdatedAtCriteria(Operator.EQUAL, (Object[])updatedAt));
    }

    public FeedbackReviewRequest<T> withUpdatedAt(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedAtCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withUpdatedAtIsUnknown(){
       return withUpdatedAt(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withUpdatedAtIsKnown(){
       return withUpdatedAt(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedAtCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.UPDATED_AT_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withUpdatedAtGreaterThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtGreaterThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN_OR_EQUAL, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtLessThan(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtLessThanOrEqualTo(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN_OR_EQUAL, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtBetween(LocalDateTime startOfUpdatedAt, LocalDateTime endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }
    public FeedbackReviewRequest<T> withUpdatedAtBefore(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtBefore(Date updatedAt){
       return withUpdatedAt(Operator.LESS_THAN, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtAfter(LocalDateTime updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtAfter(Date updatedAt){
       return withUpdatedAt(Operator.GREATER_THAN, updatedAt);
    }

    public FeedbackReviewRequest<T> withUpdatedAtBetween(Date startOfUpdatedAt, Date endOfUpdatedAt){
       return withUpdatedAt(Operator.BETWEEN, startOfUpdatedAt, endOfUpdatedAt);
    }




    public FeedbackReviewRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public FeedbackReviewRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.VERSION_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public FeedbackReviewRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public FeedbackReviewRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public FeedbackReviewRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public FeedbackReviewRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public FeedbackReviewRequest<T> count(){
        super.count();
        return this;
    }
    public FeedbackReviewRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public FeedbackReviewRequest minRating(){
        return minRatingAs(prefix("minOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest minRatingAs(String retName){
        super.min(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest maxRating(){
        return maxRatingAs(prefix("maxOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest maxRatingAs(String retName){
        super.max(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest sumRating(){
        return sumRatingAs(prefix("sumOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest sumRatingAs(String retName){
        super.sum(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest avgRating(){
        return avgRatingAs(prefix("avgOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest avgRatingAs(String retName){
        super.avg(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest standardDeviationRating(){
        return standardDeviationRatingAs(prefix("standardDeviationOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest standardDeviationRatingAs(String retName){
        super.standardDeviation(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest squareRootOfPopulationStandardDeviationRating(){
        return squareRootOfPopulationStandardDeviationRatingAs(prefix("squareRootOfPopulationStandardDeviationOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest squareRootOfPopulationStandardDeviationRatingAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest sampleVarianceRating(){
        return sampleVarianceRatingAs(prefix("sampleVarianceOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest sampleVarianceRatingAs(String retName){
        super.sampleVariance(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest samplePopulationVarianceRating(){
        return samplePopulationVarianceRatingAs(prefix("samplePopulationVarianceOf",FeedbackReview.RATING_PROPERTY));
    }

    public FeedbackReviewRequest samplePopulationVarianceRatingAs(String retName){
        super.samplePopulationVariance(retName, FeedbackReview.RATING_PROPERTY);
        return this;
    }
    public FeedbackReviewRequest<T> groupByMovingOrderWithDetails(){
       return groupByMovingOrderWithDetails(Q.movingOrders().unlimited());
    }

    public FeedbackReviewRequest<T> groupByMovingOrderWithDetails(MovingOrderRequest subRequest){
       aggregate(FeedbackReview.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }





    public FeedbackReviewRequest<T> groupById(){
       groupBy(FeedbackReview.ID_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByIdAs(String retName){
       groupBy(retName, FeedbackReview.ID_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.ID_PROPERTY, function);
       return this;
    }

    public FeedbackReviewRequest<T> groupByRating(){
       groupBy(FeedbackReview.RATING_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByRatingAs(String retName){
       groupBy(retName, FeedbackReview.RATING_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByRatingWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.RATING_PROPERTY, function);
       return this;
    }

    public FeedbackReviewRequest<T> groupByTitle(){
       groupBy(FeedbackReview.TITLE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByTitleAs(String retName){
       groupBy(retName, FeedbackReview.TITLE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByTitleWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.TITLE_PROPERTY, function);
       return this;
    }

    public FeedbackReviewRequest<T> groupByComment(){
       groupBy(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCommentAs(String retName){
       groupBy(retName, FeedbackReview.COMMENT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCommentWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.COMMENT_PROPERTY, function);
       return this;
    }
    public FeedbackReviewRequest<T> groupByMovingOrderWith(MovingOrderRequest subRequest){
       groupBy(FeedbackReview.MOVING_ORDER_PROPERTY, subRequest);
       return this;
    }
    public FeedbackReviewRequest<T> groupByMovingOrder(){
       groupBy(FeedbackReview.MOVING_ORDER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByMovingOrderAs(String retName){
       groupBy(retName, FeedbackReview.MOVING_ORDER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByMovingOrderWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.MOVING_ORDER_PROPERTY, function);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCreatedAt(){
       groupBy(FeedbackReview.CREATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCreatedAtAs(String retName){
       groupBy(retName, FeedbackReview.CREATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCreatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.CREATED_AT_PROPERTY, function);
       return this;
    }

    public FeedbackReviewRequest<T> groupByUpdatedAt(){
       groupBy(FeedbackReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByUpdatedAtAs(String retName){
       groupBy(retName, FeedbackReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByUpdatedAtWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.UPDATED_AT_PROPERTY, function);
       return this;
    }

    public FeedbackReviewRequest<T> groupByVersion(){
       groupBy(FeedbackReview.VERSION_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByVersionAs(String retName){
       groupBy(retName, FeedbackReview.VERSION_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.VERSION_PROPERTY, function);
       return this;
    }



    public FeedbackReviewRequest<T> orderByIdAscending(){
       addOrderByAscending(FeedbackReview.ID_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByIdDescending(){
       addOrderByDescending(FeedbackReview.ID_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByRatingAscending(){
       addOrderByAscending(FeedbackReview.RATING_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByRatingDescending(){
       addOrderByDescending(FeedbackReview.RATING_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByTitleAscending(){
       addOrderByAscending(FeedbackReview.TITLE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByTitleDescending(){
       addOrderByDescending(FeedbackReview.TITLE_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> orderByTitleAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FeedbackReview.TITLE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByTitleDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FeedbackReview.TITLE_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> orderByCommentAscending(){
       addOrderByAscending(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByCommentDescending(){
       addOrderByDescending(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> orderByCommentAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByCommentDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(FeedbackReview.COMMENT_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> orderByMovingOrderAscending(){
       addOrderByAscending(FeedbackReview.MOVING_ORDER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByMovingOrderDescending(){
       addOrderByDescending(FeedbackReview.MOVING_ORDER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByCreatedAtAscending(){
       addOrderByAscending(FeedbackReview.CREATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByCreatedAtDescending(){
       addOrderByDescending(FeedbackReview.CREATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByUpdatedAtAscending(){
       addOrderByAscending(FeedbackReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByUpdatedAtDescending(){
       addOrderByDescending(FeedbackReview.UPDATED_AT_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByVersionAscending(){
       addOrderByAscending(FeedbackReview.VERSION_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByVersionDescending(){
       addOrderByDescending(FeedbackReview.VERSION_PROPERTY);
       return this;
    }


    public MovingOrderRequest rollUpToMovingOrder(){
       MovingOrderRequest movingOrder = Q.movingOrders().unlimited();
       this.withMovingOrderMatching(movingOrder)
           .groupByMovingOrderWith(movingOrder);
       return movingOrder;
    }





   public FeedbackReviewRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder){
       return facetByMovingOrderAs(facetName, movingOrder, true);
   }

   public FeedbackReviewRequest<T> facetByMovingOrderAs(String facetName, MovingOrderRequest movingOrder, boolean includeAllFacets){
       addFacet(facetName, FeedbackReview.MOVING_ORDER_PROPERTY, movingOrder, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public FeedbackReviewRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public FeedbackReviewRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public FeedbackReviewRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public FeedbackReviewRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public FeedbackReviewRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}