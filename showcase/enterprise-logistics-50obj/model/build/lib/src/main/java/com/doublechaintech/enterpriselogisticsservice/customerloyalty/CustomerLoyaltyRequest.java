package com.doublechaintech.enterpriselogisticsservice.customerloyalty;

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

public class CustomerLoyaltyRequest<T extends CustomerLoyalty> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public CustomerLoyaltyRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public CustomerLoyaltyRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public CustomerLoyaltyRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public CustomerLoyaltyRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public CustomerLoyaltyRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public CustomerLoyaltyRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public CustomerLoyaltyRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (CustomerLoyaltyRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public CustomerLoyaltyRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public CustomerLoyaltyRequest<T> matchingAnyOf(CustomerLoyaltyRequest customerLoyalty){
        super.internalMatchAny(customerLoyalty);
        return this;
    }

    public CustomerLoyaltyRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public CustomerLoyaltyRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public CustomerLoyaltyRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public CustomerLoyaltyRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectPoints().selectTier().selectPrivateCustomerIdOnly().selectCorporateCustomerIdOnly().selectVersion();
    }

    public CustomerLoyaltyRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public CustomerLoyaltyRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectPoints().selectTier().selectPrivateCustomer().selectCorporateCustomer().selectVersion();
    }

    public CustomerLoyaltyRequest<T> selectChildren(){
        super.selectAny();
        return selectId().selectPoints().selectTier().selectPrivateCustomer().selectCorporateCustomer().selectVersion();
    }


    public CustomerLoyaltyRequest<T> selectId(){
       selectProperty(CustomerLoyalty.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerLoyaltyRequest<T> unselectId(){
       unselectProperty(CustomerLoyalty.ID_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> selectPoints(){
       selectProperty(CustomerLoyalty.POINTS_PROPERTY);
       return this;
    }

    /**
     * fill the points with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  points) to fetch points property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the points with customized aggrFunction, TEAQL uses ({aggrFunction}(points) AS points to fetch points property.
     * @param aggrFunction  aggrFunction
     */
    public CustomerLoyaltyRequest<T> selectPoints(AggrFunction aggrFunction){
       selectProperty(CustomerLoyalty.POINTS_PROPERTY, aggrFunction);
       return this;
    }


    public CustomerLoyaltyRequest<T> unselectPoints(){
       unselectProperty(CustomerLoyalty.POINTS_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> selectTier(){
       selectProperty(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }

    /**
     * fill the tier with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  tier) to fetch tier property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerLoyaltyRequest<T> unselectTier(){
       unselectProperty(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> selectPrivateCustomerIdOnly(){
       selectProperty(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> selectPrivateCustomer(){
        return selectPrivateCustomerWith(Q.privateCustomers().unlimited().selectSelf());
    }

    public CustomerLoyaltyRequest<T> selectPrivateCustomerWith(PrivateCustomerRequest privateCustomer){
       selectProperty(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       enhanceRelation(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, privateCustomer);
       return this;
    }

    public CustomerLoyaltyRequest<T> unselectPrivateCustomer(){
       unselectProperty(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> selectCorporateCustomerIdOnly(){
       selectProperty(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> selectCorporateCustomer(){
        return selectCorporateCustomerWith(Q.corporateCustomers().unlimited().selectSelf());
    }

    public CustomerLoyaltyRequest<T> selectCorporateCustomerWith(CorporateCustomerRequest corporateCustomer){
       selectProperty(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       enhanceRelation(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer);
       return this;
    }

    public CustomerLoyaltyRequest<T> unselectCorporateCustomer(){
       unselectProperty(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> selectVersion(){
       selectProperty(CustomerLoyalty.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public CustomerLoyaltyRequest<T> unselectVersion(){
       unselectProperty(CustomerLoyalty.VERSION_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerLoyalty.ID_PROPERTY, operator, values);
    }

    public CustomerLoyaltyRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public CustomerLoyaltyRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public CustomerLoyaltyRequest<T> filterByPoints(Integer... points){
      if (points == null || points.length == 0) {
        throw new IllegalArgumentException("filterByPoints parameter points cannot be empty");
      }
      return appendSearchCriteria(createPointsCriteria(Operator.EQUAL, (Object[])points));
    }

    public CustomerLoyaltyRequest<T> withPoints(Operator operator, Object... values){
       return appendSearchCriteria(createPointsCriteria(operator, values));
    }

    public CustomerLoyaltyRequest<T> withPointsIsUnknown(){
       return withPoints(Operator.IS_NULL);
    }

    public CustomerLoyaltyRequest<T> withPointsIsKnown(){
       return withPoints(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPointsCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerLoyalty.POINTS_PROPERTY, operator, values);
    }

    public CustomerLoyaltyRequest<T> withPointsGreaterThan(Integer points){
       return withPoints(Operator.GREATER_THAN, points);
    }

    public CustomerLoyaltyRequest<T> withPointsGreaterThanOrEqualTo(Integer points){
       return withPoints(Operator.GREATER_THAN_OR_EQUAL, points);
    }

    public CustomerLoyaltyRequest<T> withPointsLessThan(Integer points){
       return withPoints(Operator.LESS_THAN, points);
    }

    public CustomerLoyaltyRequest<T> withPointsLessThanOrEqualTo(Integer points){
       return withPoints(Operator.LESS_THAN_OR_EQUAL, points);
    }

    public CustomerLoyaltyRequest<T> withPointsBetween(Integer startOfPoints, Integer endOfPoints){
       return withPoints(Operator.BETWEEN, startOfPoints, endOfPoints);
    }



    public CustomerLoyaltyRequest<T> filterByTier(String... tier){
      if (tier == null || tier.length == 0) {
        throw new IllegalArgumentException("filterByTier parameter tier cannot be empty");
      }
      return appendSearchCriteria(createTierCriteria(Operator.EQUAL, (Object[])tier));
    }

    public CustomerLoyaltyRequest<T> withTier(Operator operator, Object... values){
       return appendSearchCriteria(createTierCriteria(operator, values));
    }

    public CustomerLoyaltyRequest<T> withTierIsUnknown(){
       return withTier(Operator.IS_NULL);
    }

    public CustomerLoyaltyRequest<T> withTierIsKnown(){
       return withTier(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTierCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerLoyalty.TIER_PROPERTY, operator, values);
    }

    public CustomerLoyaltyRequest<T> withTierGreaterThan(String tier){
       return withTier(Operator.GREATER_THAN, tier);
    }

    public CustomerLoyaltyRequest<T> withTierGreaterThanOrEqualTo(String tier){
       return withTier(Operator.GREATER_THAN_OR_EQUAL, tier);
    }

    public CustomerLoyaltyRequest<T> withTierLessThan(String tier){
       return withTier(Operator.LESS_THAN, tier);
    }

    public CustomerLoyaltyRequest<T> withTierLessThanOrEqualTo(String tier){
       return withTier(Operator.LESS_THAN_OR_EQUAL, tier);
    }

    public CustomerLoyaltyRequest<T> withTierBetween(String startOfTier, String endOfTier){
       return withTier(Operator.BETWEEN, startOfTier, endOfTier);
    }
    public CustomerLoyaltyRequest<T> withTierStartingWith(String tier){
       return withTier(Operator.BEGIN_WITH, tier);
    }
    public CustomerLoyaltyRequest<T> withTierContaining(String tier){
       return withTier(Operator.CONTAIN, tier);
    }

    public CustomerLoyaltyRequest<T> withTierEndingWith(String tier){
       return withTier(Operator.END_WITH, tier);
    }

    public CustomerLoyaltyRequest<T> withTierIs(String tier){
       return withTier(Operator.EQUAL, tier);
    }

    public CustomerLoyaltyRequest<T> withTierSoundingLike(String tier){
       return withTier(Operator.SOUNDS_LIKE, tier);
    }



    public CustomerLoyaltyRequest<T> filterByPrivateCustomer(PrivateCustomer... privateCustomer){
      if (privateCustomer == null || privateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByPrivateCustomer parameter privateCustomer cannot be empty");
      }
      return appendSearchCriteria(createPrivateCustomerCriteria(Operator.EQUAL, (Object[])privateCustomer));
    }

    public CustomerLoyaltyRequest<T> withPrivateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createPrivateCustomerCriteria(operator, values));
    }

    public CustomerLoyaltyRequest<T> withPrivateCustomerIsUnknown(){
       return withPrivateCustomer(Operator.IS_NULL);
    }

    public CustomerLoyaltyRequest<T> withPrivateCustomerIsKnown(){
       return withPrivateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPrivateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, operator, values);
    }

    public CustomerLoyaltyRequest<T> filterByPrivateCustomer(Long privateCustomer){
      if(privateCustomer == null){
         return this;
      }
      return withPrivateCustomer(Operator.EQUAL, privateCustomer);
    }
    public CustomerLoyaltyRequest<T> withPrivateCustomerMatching(PrivateCustomerRequest privateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, PrivateCustomer.ID_PROPERTY));
    }

    public CustomerLoyaltyRequest<T> filterByCorporateCustomer(CorporateCustomer... corporateCustomer){
      if (corporateCustomer == null || corporateCustomer.length == 0) {
        throw new IllegalArgumentException("filterByCorporateCustomer parameter corporateCustomer cannot be empty");
      }
      return appendSearchCriteria(createCorporateCustomerCriteria(Operator.EQUAL, (Object[])corporateCustomer));
    }

    public CustomerLoyaltyRequest<T> withCorporateCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCorporateCustomerCriteria(operator, values));
    }

    public CustomerLoyaltyRequest<T> withCorporateCustomerIsUnknown(){
       return withCorporateCustomer(Operator.IS_NULL);
    }

    public CustomerLoyaltyRequest<T> withCorporateCustomerIsKnown(){
       return withCorporateCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCorporateCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, operator, values);
    }

    public CustomerLoyaltyRequest<T> filterByCorporateCustomer(Long corporateCustomer){
      if(corporateCustomer == null){
         return this;
      }
      return withCorporateCustomer(Operator.EQUAL, corporateCustomer);
    }
    public CustomerLoyaltyRequest<T> withCorporateCustomerMatching(CorporateCustomerRequest corporateCustomer){
       return appendSearchCriteria(new SubQuerySearchCriteria(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, CorporateCustomer.ID_PROPERTY));
    }

    public CustomerLoyaltyRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public CustomerLoyaltyRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public CustomerLoyaltyRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public CustomerLoyaltyRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(CustomerLoyalty.VERSION_PROPERTY, operator, values);
    }

    public CustomerLoyaltyRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public CustomerLoyaltyRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public CustomerLoyaltyRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public CustomerLoyaltyRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public CustomerLoyaltyRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }


    public CustomerLoyaltyRequest<T> count(){
        super.count();
        return this;
    }
    public CustomerLoyaltyRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public CustomerLoyaltyRequest minPoints(){
        return minPointsAs(prefix("minOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest minPointsAs(String retName){
        super.min(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest maxPoints(){
        return maxPointsAs(prefix("maxOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest maxPointsAs(String retName){
        super.max(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest sumPoints(){
        return sumPointsAs(prefix("sumOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest sumPointsAs(String retName){
        super.sum(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest avgPoints(){
        return avgPointsAs(prefix("avgOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest avgPointsAs(String retName){
        super.avg(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest standardDeviationPoints(){
        return standardDeviationPointsAs(prefix("standardDeviationOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest standardDeviationPointsAs(String retName){
        super.standardDeviation(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest squareRootOfPopulationStandardDeviationPoints(){
        return squareRootOfPopulationStandardDeviationPointsAs(prefix("squareRootOfPopulationStandardDeviationOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest squareRootOfPopulationStandardDeviationPointsAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest sampleVariancePoints(){
        return sampleVariancePointsAs(prefix("sampleVarianceOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest sampleVariancePointsAs(String retName){
        super.sampleVariance(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest samplePopulationVariancePoints(){
        return samplePopulationVariancePointsAs(prefix("samplePopulationVarianceOf",CustomerLoyalty.POINTS_PROPERTY));
    }

    public CustomerLoyaltyRequest samplePopulationVariancePointsAs(String retName){
        super.samplePopulationVariance(retName, CustomerLoyalty.POINTS_PROPERTY);
        return this;
    }
    public CustomerLoyaltyRequest<T> groupByPrivateCustomerWithDetails(){
       return groupByPrivateCustomerWithDetails(Q.privateCustomers().unlimited());
    }

    public CustomerLoyaltyRequest<T> groupByPrivateCustomerWithDetails(PrivateCustomerRequest subRequest){
       aggregate(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByCorporateCustomerWithDetails(){
       return groupByCorporateCustomerWithDetails(Q.corporateCustomers().unlimited());
    }

    public CustomerLoyaltyRequest<T> groupByCorporateCustomerWithDetails(CorporateCustomerRequest subRequest){
       aggregate(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }



    public CustomerLoyaltyRequest<T> groupById(){
       groupBy(CustomerLoyalty.ID_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByIdAs(String retName){
       groupBy(retName, CustomerLoyalty.ID_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerLoyalty.ID_PROPERTY, function);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByPoints(){
       groupBy(CustomerLoyalty.POINTS_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByPointsAs(String retName){
       groupBy(retName, CustomerLoyalty.POINTS_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByPointsWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerLoyalty.POINTS_PROPERTY, function);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByTier(){
       groupBy(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByTierAs(String retName){
       groupBy(retName, CustomerLoyalty.TIER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByTierWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerLoyalty.TIER_PROPERTY, function);
       return this;
    }
    public CustomerLoyaltyRequest<T> groupByPrivateCustomerWith(PrivateCustomerRequest subRequest){
       groupBy(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public CustomerLoyaltyRequest<T> groupByPrivateCustomer(){
       groupBy(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByPrivateCustomerAs(String retName){
       groupBy(retName, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByPrivateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, function);
       return this;
    }
    public CustomerLoyaltyRequest<T> groupByCorporateCustomerWith(CorporateCustomerRequest subRequest){
       groupBy(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public CustomerLoyaltyRequest<T> groupByCorporateCustomer(){
       groupBy(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByCorporateCustomerAs(String retName){
       groupBy(retName, CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByCorporateCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, function);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByVersion(){
       groupBy(CustomerLoyalty.VERSION_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByVersionAs(String retName){
       groupBy(retName, CustomerLoyalty.VERSION_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, CustomerLoyalty.VERSION_PROPERTY, function);
       return this;
    }



    public CustomerLoyaltyRequest<T> orderByIdAscending(){
       addOrderByAscending(CustomerLoyalty.ID_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByIdDescending(){
       addOrderByDescending(CustomerLoyalty.ID_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByPointsAscending(){
       addOrderByAscending(CustomerLoyalty.POINTS_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByPointsDescending(){
       addOrderByDescending(CustomerLoyalty.POINTS_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByTierAscending(){
       addOrderByAscending(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByTierDescending(){
       addOrderByDescending(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> orderByTierAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByTierDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(CustomerLoyalty.TIER_PROPERTY);
       return this;
    }
    public CustomerLoyaltyRequest<T> orderByPrivateCustomerAscending(){
       addOrderByAscending(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByPrivateCustomerDescending(){
       addOrderByDescending(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByCorporateCustomerAscending(){
       addOrderByAscending(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByCorporateCustomerDescending(){
       addOrderByDescending(CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByVersionAscending(){
       addOrderByAscending(CustomerLoyalty.VERSION_PROPERTY);
       return this;
    }

    public CustomerLoyaltyRequest<T> orderByVersionDescending(){
       addOrderByDescending(CustomerLoyalty.VERSION_PROPERTY);
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



   public CustomerLoyaltyRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer){
       return facetByPrivateCustomerAs(facetName, privateCustomer, true);
   }

   public CustomerLoyaltyRequest<T> facetByPrivateCustomerAs(String facetName, PrivateCustomerRequest privateCustomer, boolean includeAllFacets){
       addFacet(facetName, CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, privateCustomer, includeAllFacets);
       return this;
   }
   public CustomerLoyaltyRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer){
       return facetByCorporateCustomerAs(facetName, corporateCustomer, true);
   }

   public CustomerLoyaltyRequest<T> facetByCorporateCustomerAs(String facetName, CorporateCustomerRequest corporateCustomer, boolean includeAllFacets){
       addFacet(facetName, CustomerLoyalty.CORPORATE_CUSTOMER_PROPERTY, corporateCustomer, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public CustomerLoyaltyRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public CustomerLoyaltyRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public CustomerLoyaltyRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public CustomerLoyaltyRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public CustomerLoyaltyRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}