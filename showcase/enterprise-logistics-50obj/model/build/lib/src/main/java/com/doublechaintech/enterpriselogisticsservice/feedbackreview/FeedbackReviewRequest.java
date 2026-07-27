package com.doublechaintech.enterpriselogisticsservice.feedbackreview;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerRequest;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.time.LocalDate;
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
        return selectId().selectRating().selectComment().selectReviewDate().selectPrivateCustomerIdOnly().selectCorporateCustomerIdOnly().selectVersion();
    }

    public FeedbackReviewRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public FeedbackReviewRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectRating().selectComment().selectReviewDate().selectPrivateCustomer().selectCorporateCustomer().selectVersion();
    }

    public FeedbackReviewRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectRating().selectComment().selectReviewDate().selectPrivateCustomer().selectCorporateCustomer().selectVersion();
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
    public FeedbackReviewRequest<T> selectReviewDate(){
       selectProperty(FeedbackReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the reviewDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  reviewDate) to fetch reviewDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public FeedbackReviewRequest<T> unselectReviewDate(){
       unselectProperty(FeedbackReview.REVIEW_DATE_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectPrivateCustomerIdOnly(){
       selectProperty(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> selectPrivateCustomer(){
        return selectPrivateCustomerWith(Q.privateCustomers().unlimited().selectSelf());
    }

    public FeedbackReviewRequest<T> selectPrivateCustomerWith(PrivateCustomerRequest privateCustomer){
       selectProperty(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       enhanceRelation(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, privateCustomer);
       return this;
    }

    public FeedbackReviewRequest<T> unselectPrivateCustomer(){
       unselectProperty(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }
    public FeedbackReviewRequest<T> selectCorporateCustomerIdOnly(){
       selectProperty(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> selectCorporateCustomer(){
        return selectCorporateCustomerWith(Q.corporateCustomers().unlimited().selectSelf());
    }

    public FeedbackReviewRequest<T> selectCorporateCustomerWith(CorporateCustomerRequest corporateCustomer){
       selectProperty(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
       enhanceRelation(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer);
       return this;
    }

    public FeedbackReviewRequest<T> unselectCorporateCustomer(){
       unselectProperty(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
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



    public FeedbackReviewRequest<T> filterByReviewDate(LocalDate... reviewDate){
      if (reviewDate == null || reviewDate.length == 0) {
        throw new IllegalArgumentException("filterByReviewDate parameter reviewDate cannot be empty");
      }
      return appendSearchCriteria(createReviewDateCriteria(Operator.EQUAL, (Object[])reviewDate));
    }

    public FeedbackReviewRequest<T> withReviewDate(Operator operator, Object... values){
       return appendSearchCriteria(createReviewDateCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withReviewDateIsUnknown(){
       return withReviewDate(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withReviewDateIsKnown(){
       return withReviewDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createReviewDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.REVIEW_DATE_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> withReviewDateGreaterThan(LocalDate reviewDate){
       return withReviewDate(Operator.GREATER_THAN, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateGreaterThanOrEqualTo(LocalDate reviewDate){
       return withReviewDate(Operator.GREATER_THAN_OR_EQUAL, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateLessThan(LocalDate reviewDate){
       return withReviewDate(Operator.LESS_THAN, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateLessThanOrEqualTo(LocalDate reviewDate){
       return withReviewDate(Operator.LESS_THAN_OR_EQUAL, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateBetween(LocalDate startOfReviewDate, LocalDate endOfReviewDate){
       return withReviewDate(Operator.BETWEEN, startOfReviewDate, endOfReviewDate);
    }
    public FeedbackReviewRequest<T> withReviewDateBefore(LocalDate reviewDate){
       return withReviewDate(Operator.LESS_THAN, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateBefore(Date reviewDate){
       return withReviewDate(Operator.LESS_THAN, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateAfter(LocalDate reviewDate){
       return withReviewDate(Operator.GREATER_THAN, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateAfter(Date reviewDate){
       return withReviewDate(Operator.GREATER_THAN, reviewDate);
    }

    public FeedbackReviewRequest<T> withReviewDateBetween(Date startOfReviewDate, Date endOfReviewDate){
       return withReviewDate(Operator.BETWEEN, startOfReviewDate, endOfReviewDate);
    }




    public FeedbackReviewRequest<T> filterByPrivateCustomer(PrivateCustomer... privateCustomer){
      if (privateCustomer == null || privateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByPrivateCustomer parameter privateCustomer cannot be empty");
      }
      return appendSearchCriteria(createPrivateCustomerCriteria(Operator.EQUAL, (Object[])privateCustomer));
    }

    public FeedbackReviewRequest<T> withPrivateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createPrivateCustomerCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withPrivateCustomerIsUnknown(){
       return withPrivateCustomer(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withPrivateCustomerIsKnown(){
       return withPrivateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPrivateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> filterByPrivateCustomer(Long privateCustomer){
      if(privateCustomer == null){
         return this;
      }
      return withPrivateCustomer(Operator.EQUAL, privateCustomer);
    }
    public FeedbackReviewRequest<T> withPrivateCustomerMatching(PrivateCustomerRequest privateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, PrivateCustomer.ID_PROPERTY));
    }

    public FeedbackReviewRequest<T> filterByCorporateCustomer(CorporateCustomer... corporateCustomer){
      if (corporateCustomer == null || corporateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByCorporateCustomer parameter corporateCustomer cannot be empty");
      }
      return appendSearchCriteria(createCorporateCustomerCriteria(Operator.EQUAL, (Object[])corporateCustomer));
    }

    public FeedbackReviewRequest<T> withCorporateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCorporateCustomerCriteria(operator, values));
    }

    public FeedbackReviewRequest<T> withCorporateCustomerIsUnknown(){
       return withCorporateCustomer(Operator.IS_NULL);
    }

    public FeedbackReviewRequest<T> withCorporateCustomerIsKnown(){
       return withCorporateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCorporateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, operator, values);
    }

    public FeedbackReviewRequest<T> filterByCorporateCustomer(Long corporateCustomer){
      if(corporateCustomer == null){
         return this;
      }
      return withCorporateCustomer(Operator.EQUAL, corporateCustomer);
    }
    public FeedbackReviewRequest<T> withCorporateCustomerMatching(CorporateCustomerRequest corporateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, CorporateCustomer.ID_PROPERTY));
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
    public FeedbackReviewRequest<T> groupByPrivateCustomerWithDetails(){
       return groupByPrivateCustomerWithDetails(Q.privateCustomers().unlimited());
    }

    public FeedbackReviewRequest<T> groupByPrivateCustomerWithDetails(PrivateCustomerRequest subRequest){
       aggregate(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCorporateCustomerWithDetails(){
       return groupByCorporateCustomerWithDetails(Q.corporateCustomers().unlimited());
    }

    public FeedbackReviewRequest<T> groupByCorporateCustomerWithDetails(CorporateCustomerRequest subRequest){
       aggregate(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, subRequest);
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

    public FeedbackReviewRequest<T> groupByReviewDate(){
       groupBy(FeedbackReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByReviewDateAs(String retName){
       groupBy(retName, FeedbackReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByReviewDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.REVIEW_DATE_PROPERTY, function);
       return this;
    }
    public FeedbackReviewRequest<T> groupByPrivateCustomerWith(PrivateCustomerRequest subRequest){
       groupBy(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public FeedbackReviewRequest<T> groupByPrivateCustomer(){
       groupBy(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByPrivateCustomerAs(String retName){
       groupBy(retName, FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByPrivateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, function);
       return this;
    }
    public FeedbackReviewRequest<T> groupByCorporateCustomerWith(CorporateCustomerRequest subRequest){
       groupBy(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public FeedbackReviewRequest<T> groupByCorporateCustomer(){
       groupBy(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCorporateCustomerAs(String retName){
       groupBy(retName, FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> groupByCorporateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, function);
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
    public FeedbackReviewRequest<T> orderByReviewDateAscending(){
       addOrderByAscending(FeedbackReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByReviewDateDescending(){
       addOrderByDescending(FeedbackReview.REVIEW_DATE_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByPrivateCustomerAscending(){
       addOrderByAscending(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByPrivateCustomerDescending(){
       addOrderByDescending(FeedbackReview.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByCorporateCustomerAscending(){
       addOrderByAscending(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public FeedbackReviewRequest<T> orderByCorporateCustomerDescending(){
       addOrderByDescending(FeedbackReview.CORPORATE_CUSTOMER_PROPERTY);
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


    public PrivateCustomerRequest rollUpToPrivateCustomer(){
       PrivateCustomerRequest privateCustomer = Q.privateCustomers().unlimited();
       this.withPrivateCustomerMatching(privateCustomer)
           .groupByPrivateCustomerWith(privateCustomer);
       return privateCustomer;
    }

    public CorporateCustomerRequest rollUpToCorporateCustomer(){
       CorporateCustomerRequest corporateCustomer = Q.corporateCustomers().unlimited();
       this.withCorporateCustomerMatching(corporateCustomer)
           .groupByCorporateCustomerWith(corporateCustomer);
       return corporateCustomer;
    }



   public FeedbackReviewRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer){
       return facetByPrivateCustomerAs(facetName, privateCustomer, true);
   }

   public FeedbackReviewRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer, boolean includeAllFacets){
       addFacet(facetName, FeedbackReview.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, includeAllFacets);
       return this;
   }
   public FeedbackReviewRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer){
       return facetByCorporateCustomerAs(facetName, corporateCustomer, true);
   }

   public FeedbackReviewRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer, boolean includeAllFacets){
       addFacet(facetName, FeedbackReview.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, includeAllFacets);
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