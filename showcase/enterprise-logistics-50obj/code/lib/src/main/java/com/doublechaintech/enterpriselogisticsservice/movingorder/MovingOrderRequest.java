package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemRequest;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordRequest;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationRequest;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewRequest;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressRequest;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class MovingOrderRequest<T extends MovingOrder> extends BaseRequest<T> {

    /**
     * @deprecated AI agents and business code must use the generated Q facade
     *             instead of constructing request builders directly.
     */
    @Deprecated
    public MovingOrderRequest(Class<T> returnType){
        super(returnType);
        selectId();
        selectVersion();
    }

    public MovingOrderRequest<T> comment(String comment){
         super.internalComment(comment);
         return this;
    }

    // purpose() 继承自 BaseRequest，返回 ExecutableRequest（终结方法）

    public MovingOrderRequest<T> returnType(Class<? extends T> returnType){
        super.setReturnType(returnType);
        return this;
    }

    public MovingOrderRequest<T> enableAggregationCache(long cacheExpiredMillis){
        super.enableAggregationCache();
        super.aggregateCacheTime(cacheExpiredMillis);
        return this;
    }

    public MovingOrderRequest<T> enableAggregationCache(){
        return enableAggregationCache(0l);
    }


    public MovingOrderRequest<T> propagateAggregationCache(long cacheExpiredMillis){
        super.propagateAggregationCache(cacheExpiredMillis);
        return this;
    }

    public MovingOrderRequest<T> appendSearchCriteria(SearchCriteria searchCriteria){
        return (MovingOrderRequest<T>)super.appendSearchCriteria(searchCriteria);
    }

    public MovingOrderRequest<T> filter(String property1, Operator operator, String property2){
        return appendSearchCriteria(new TwoOperatorCriteria(operator, new PropertyReference(property1), new PropertyReference(property2)));
    }


    public MovingOrderRequest<T> matchingAnyOf(MovingOrderRequest movingOrder){
        super.internalMatchAny(movingOrder);
        return this;
    }

    public MovingOrderRequest<T> enhanceChildrenIfNeeded(){
        return this;
    }

    public MovingOrderRequest<T> withDeletedRows(){
        super.withDeletedRows();
        return this;
    }

    public MovingOrderRequest<T> deletedRowsOnly(){
        super.deletedRowsOnly();
        return this;
    }

    public MovingOrderRequest<T> selectSelf(){
        super.selectSelf();
        return selectId().selectOrderNumber().selectStatus().selectCustomerIdOnly().selectPickupAddressIdOnly().selectDeliveryAddressIdOnly().selectTotalWeight().selectTotalVolume().selectEstimatedCost().selectActualCost().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public MovingOrderRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public MovingOrderRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectOrderNumber().selectStatus().selectCustomer().selectPickupAddress().selectDeliveryAddress().selectTotalWeight().selectTotalVolume().selectEstimatedCost().selectActualCost().selectCreatedTime().selectUpdatedTime().selectVersion();
    }

    public MovingOrderRequest<T> selectChildren(){
        super.selectAny();
        selectDispatchPlanList().selectCargoItemList().selectFeedbackReviewList().selectInvoiceList().selectClaimsRecordList().selectCustomsDeclarationList();
        return selectId().selectOrderNumber().selectStatus().selectCustomer().selectPickupAddress().selectDeliveryAddress().selectTotalWeight().selectTotalVolume().selectEstimatedCost().selectActualCost().selectCreatedTime().selectUpdatedTime().selectVersion();
    }


    public MovingOrderRequest<T> selectId(){
       selectProperty(MovingOrder.ID_PROPERTY);
       return this;
    }

    /**
     * fill the id with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  id) to fetch id property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectId(){
       unselectProperty(MovingOrder.ID_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectOrderNumber(){
       selectProperty(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }

    /**
     * fill the orderNumber with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  orderNumber) to fetch orderNumber property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectOrderNumber(){
       unselectProperty(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectStatus(){
       selectProperty(MovingOrder.STATUS_PROPERTY);
       return this;
    }

    /**
     * fill the status with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  status) to fetch status property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectStatus(){
       unselectProperty(MovingOrder.STATUS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectCustomerIdOnly(){
       selectProperty(MovingOrder.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> selectCustomer(){
        return selectCustomerWith(Q.privateCustomers().unlimited().selectSelf());
    }

    public MovingOrderRequest<T> selectCustomerWith(PrivateCustomerRequest customer){
       selectProperty(MovingOrder.CUSTOMER_PROPERTY);
       enhanceRelation(MovingOrder.CUSTOMER_PROPERTY, customer);
       return this;
    }

    public MovingOrderRequest<T> unselectCustomer(){
       unselectProperty(MovingOrder.CUSTOMER_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectPickupAddressIdOnly(){
       selectProperty(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> selectPickupAddress(){
        return selectPickupAddressWith(Q.pickupAddresses().unlimited().selectSelf());
    }

    public MovingOrderRequest<T> selectPickupAddressWith(PickupAddressRequest pickupAddress){
       selectProperty(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       enhanceRelation(MovingOrder.PICKUP_ADDRESS_PROPERTY, pickupAddress);
       return this;
    }

    public MovingOrderRequest<T> unselectPickupAddress(){
       unselectProperty(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectDeliveryAddressIdOnly(){
       selectProperty(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> selectDeliveryAddress(){
        return selectDeliveryAddressWith(Q.pickupAddresses().unlimited().selectSelf());
    }

    public MovingOrderRequest<T> selectDeliveryAddressWith(PickupAddressRequest deliveryAddress){
       selectProperty(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       enhanceRelation(MovingOrder.DELIVERY_ADDRESS_PROPERTY, deliveryAddress);
       return this;
    }

    public MovingOrderRequest<T> unselectDeliveryAddress(){
       unselectProperty(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectTotalWeight(){
       selectProperty(MovingOrder.TOTAL_WEIGHT_PROPERTY);
       return this;
    }

    /**
     * fill the totalWeight with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalWeight) to fetch totalWeight property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalWeight with customized aggrFunction, TEAQL uses ({aggrFunction}(totalWeight) AS totalWeight to fetch totalWeight property.
     * @param aggrFunction  aggrFunction
     */
    public MovingOrderRequest<T> selectTotalWeight(AggrFunction aggrFunction){
       selectProperty(MovingOrder.TOTAL_WEIGHT_PROPERTY, aggrFunction);
       return this;
    }


    public MovingOrderRequest<T> unselectTotalWeight(){
       unselectProperty(MovingOrder.TOTAL_WEIGHT_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectTotalVolume(){
       selectProperty(MovingOrder.TOTAL_VOLUME_PROPERTY);
       return this;
    }

    /**
     * fill the totalVolume with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  totalVolume) to fetch totalVolume property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the totalVolume with customized aggrFunction, TEAQL uses ({aggrFunction}(totalVolume) AS totalVolume to fetch totalVolume property.
     * @param aggrFunction  aggrFunction
     */
    public MovingOrderRequest<T> selectTotalVolume(AggrFunction aggrFunction){
       selectProperty(MovingOrder.TOTAL_VOLUME_PROPERTY, aggrFunction);
       return this;
    }


    public MovingOrderRequest<T> unselectTotalVolume(){
       unselectProperty(MovingOrder.TOTAL_VOLUME_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectEstimatedCost(){
       selectProperty(MovingOrder.ESTIMATED_COST_PROPERTY);
       return this;
    }

    /**
     * fill the estimatedCost with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  estimatedCost) to fetch estimatedCost property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the estimatedCost with customized aggrFunction, TEAQL uses ({aggrFunction}(estimatedCost) AS estimatedCost to fetch estimatedCost property.
     * @param aggrFunction  aggrFunction
     */
    public MovingOrderRequest<T> selectEstimatedCost(AggrFunction aggrFunction){
       selectProperty(MovingOrder.ESTIMATED_COST_PROPERTY, aggrFunction);
       return this;
    }


    public MovingOrderRequest<T> unselectEstimatedCost(){
       unselectProperty(MovingOrder.ESTIMATED_COST_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectActualCost(){
       selectProperty(MovingOrder.ACTUAL_COST_PROPERTY);
       return this;
    }

    /**
     * fill the actualCost with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  actualCost) to fetch actualCost property.
     * @param rawSqlSegment  customized rawSqlSegment
     */


    /**
     * fill the actualCost with customized aggrFunction, TEAQL uses ({aggrFunction}(actualCost) AS actualCost to fetch actualCost property.
     * @param aggrFunction  aggrFunction
     */
    public MovingOrderRequest<T> selectActualCost(AggrFunction aggrFunction){
       selectProperty(MovingOrder.ACTUAL_COST_PROPERTY, aggrFunction);
       return this;
    }


    public MovingOrderRequest<T> unselectActualCost(){
       unselectProperty(MovingOrder.ACTUAL_COST_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectCreatedTime(){
       selectProperty(MovingOrder.CREATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createdTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createdTime) to fetch createdTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectCreatedTime(){
       unselectProperty(MovingOrder.CREATED_TIME_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectUpdatedTime(){
       selectProperty(MovingOrder.UPDATED_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updatedTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updatedTime) to fetch updatedTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectUpdatedTime(){
       unselectProperty(MovingOrder.UPDATED_TIME_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectVersion(){
       selectProperty(MovingOrder.VERSION_PROPERTY);
       return this;
    }

    /**
     * fill the version with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  version) to fetch version property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectVersion(){
       unselectProperty(MovingOrder.VERSION_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectDispatchPlanList(){
       return selectDispatchPlanListWith(Q.dispatchPlans().selectSelf());
    }

    public MovingOrderRequest<T> selectDispatchPlanListWith(DispatchPlanRequest dispatchPlanList){
       enhanceRelation(MovingOrder.DISPATCH_PLAN_LIST_PROPERTY, dispatchPlanList);
       return this;
    }
    public MovingOrderRequest<T> selectCargoItemList(){
       return selectCargoItemListWith(Q.cargoItems().selectSelf());
    }

    public MovingOrderRequest<T> selectCargoItemListWith(CargoItemRequest cargoItemList){
       enhanceRelation(MovingOrder.CARGO_ITEM_LIST_PROPERTY, cargoItemList);
       return this;
    }
    public MovingOrderRequest<T> selectFeedbackReviewList(){
       return selectFeedbackReviewListWith(Q.feedbackReviews().selectSelf());
    }

    public MovingOrderRequest<T> selectFeedbackReviewListWith(FeedbackReviewRequest feedbackReviewList){
       enhanceRelation(MovingOrder.FEEDBACK_REVIEW_LIST_PROPERTY, feedbackReviewList);
       return this;
    }
    public MovingOrderRequest<T> selectInvoiceList(){
       return selectInvoiceListWith(Q.invoices().selectSelf());
    }

    public MovingOrderRequest<T> selectInvoiceListWith(InvoiceRequest invoiceList){
       enhanceRelation(MovingOrder.INVOICE_LIST_PROPERTY, invoiceList);
       return this;
    }
    public MovingOrderRequest<T> selectClaimsRecordList(){
       return selectClaimsRecordListWith(Q.claimsRecords().selectSelf());
    }

    public MovingOrderRequest<T> selectClaimsRecordListWith(ClaimsRecordRequest claimsRecordList){
       enhanceRelation(MovingOrder.CLAIMS_RECORD_LIST_PROPERTY, claimsRecordList);
       return this;
    }
    public MovingOrderRequest<T> selectCustomsDeclarationList(){
       return selectCustomsDeclarationListWith(Q.customsDeclarations().selectSelf());
    }

    public MovingOrderRequest<T> selectCustomsDeclarationListWith(CustomsDeclarationRequest customsDeclarationList){
       enhanceRelation(MovingOrder.CUSTOMS_DECLARATION_LIST_PROPERTY, customsDeclarationList);
       return this;
    }

    public MovingOrderRequest<T> withId(Operator operator, Object... values){
       return appendSearchCriteria(createIdCriteria(operator, values));
    }

    public SearchCriteria createIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.ID_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withIdIs(Long id){
       return withId(Operator.EQUAL, id);
    }
    public MovingOrderRequest<T> withIdIn(Long... id){
       return withId(Operator.EQUAL, (Object[])id);
    }



    public MovingOrderRequest<T> filterByOrderNumber(String... orderNumber){
      if (orderNumber == null || orderNumber.length == 0) {
        throw new IllegalArgumentException("filterByOrderNumber parameter orderNumber cannot be empty");
      }
      return appendSearchCriteria(createOrderNumberCriteria(Operator.EQUAL, (Object[])orderNumber));
    }

    public MovingOrderRequest<T> withOrderNumber(Operator operator, Object... values){
       return appendSearchCriteria(createOrderNumberCriteria(operator, values));
    }

    public MovingOrderRequest<T> withOrderNumberIsUnknown(){
       return withOrderNumber(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withOrderNumberIsKnown(){
       return withOrderNumber(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createOrderNumberCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.ORDER_NUMBER_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withOrderNumberGreaterThan(String orderNumber){
       return withOrderNumber(Operator.GREATER_THAN, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberGreaterThanOrEqualTo(String orderNumber){
       return withOrderNumber(Operator.GREATER_THAN_OR_EQUAL, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberLessThan(String orderNumber){
       return withOrderNumber(Operator.LESS_THAN, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberLessThanOrEqualTo(String orderNumber){
       return withOrderNumber(Operator.LESS_THAN_OR_EQUAL, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberBetween(String startOfOrderNumber, String endOfOrderNumber){
       return withOrderNumber(Operator.BETWEEN, startOfOrderNumber, endOfOrderNumber);
    }
    public MovingOrderRequest<T> withOrderNumberStartingWith(String orderNumber){
       return withOrderNumber(Operator.BEGIN_WITH, orderNumber);
    }
    public MovingOrderRequest<T> withOrderNumberContaining(String orderNumber){
       return withOrderNumber(Operator.CONTAIN, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberEndingWith(String orderNumber){
       return withOrderNumber(Operator.END_WITH, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberIs(String orderNumber){
       return withOrderNumber(Operator.EQUAL, orderNumber);
    }

    public MovingOrderRequest<T> withOrderNumberSoundingLike(String orderNumber){
       return withOrderNumber(Operator.SOUNDS_LIKE, orderNumber);
    }



    public MovingOrderRequest<T> filterByStatus(String... status){
      if (status == null || status.length == 0) {
        throw new IllegalArgumentException("filterByStatus parameter status cannot be empty");
      }
      return appendSearchCriteria(createStatusCriteria(Operator.EQUAL, (Object[])status));
    }

    public MovingOrderRequest<T> withStatus(Operator operator, Object... values){
       return appendSearchCriteria(createStatusCriteria(operator, values));
    }

    public MovingOrderRequest<T> withStatusIsUnknown(){
       return withStatus(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withStatusIsKnown(){
       return withStatus(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createStatusCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.STATUS_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withStatusGreaterThan(String status){
       return withStatus(Operator.GREATER_THAN, status);
    }

    public MovingOrderRequest<T> withStatusGreaterThanOrEqualTo(String status){
       return withStatus(Operator.GREATER_THAN_OR_EQUAL, status);
    }

    public MovingOrderRequest<T> withStatusLessThan(String status){
       return withStatus(Operator.LESS_THAN, status);
    }

    public MovingOrderRequest<T> withStatusLessThanOrEqualTo(String status){
       return withStatus(Operator.LESS_THAN_OR_EQUAL, status);
    }

    public MovingOrderRequest<T> withStatusBetween(String startOfStatus, String endOfStatus){
       return withStatus(Operator.BETWEEN, startOfStatus, endOfStatus);
    }
    public MovingOrderRequest<T> withStatusStartingWith(String status){
       return withStatus(Operator.BEGIN_WITH, status);
    }
    public MovingOrderRequest<T> withStatusContaining(String status){
       return withStatus(Operator.CONTAIN, status);
    }

    public MovingOrderRequest<T> withStatusEndingWith(String status){
       return withStatus(Operator.END_WITH, status);
    }

    public MovingOrderRequest<T> withStatusIs(String status){
       return withStatus(Operator.EQUAL, status);
    }

    public MovingOrderRequest<T> withStatusSoundingLike(String status){
       return withStatus(Operator.SOUNDS_LIKE, status);
    }



    public MovingOrderRequest<T> filterByCustomer(PrivateCustomer... customer){
      if (customer == null || customer.length == 0) {
        throw new IllegalArgumentException("filterByCustomer parameter customer cannot be empty");
      }
      return appendSearchCriteria(createCustomerCriteria(Operator.EQUAL, (Object[])customer));
    }

    public MovingOrderRequest<T> withCustomer(Operator operator, Object... values){
       return appendSearchCriteria(createCustomerCriteria(operator, values));
    }

    public MovingOrderRequest<T> withCustomerIsUnknown(){
       return withCustomer(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withCustomerIsKnown(){
       return withCustomer(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCustomerCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.CUSTOMER_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> filterByCustomer(Long customer){
      if(customer == null){
         return this;
      }
      return withCustomer(Operator.EQUAL, customer);
    }
    public MovingOrderRequest<T> withCustomerMatching(PrivateCustomerRequest customer){
       return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.CUSTOMER_PROPERTY, customer, PrivateCustomer.ID_PROPERTY));
    }

    public MovingOrderRequest<T> filterByPickupAddress(PickupAddress... pickupAddress){
      if (pickupAddress == null || pickupAddress.length == 0) {
        throw new IllegalArgumentException("filterByPickupAddress parameter pickupAddress cannot be empty");
      }
      return appendSearchCriteria(createPickupAddressCriteria(Operator.EQUAL, (Object[])pickupAddress));
    }

    public MovingOrderRequest<T> withPickupAddress(Operator operator, Object... values){
       return appendSearchCriteria(createPickupAddressCriteria(operator, values));
    }

    public MovingOrderRequest<T> withPickupAddressIsUnknown(){
       return withPickupAddress(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withPickupAddressIsKnown(){
       return withPickupAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPickupAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.PICKUP_ADDRESS_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> filterByPickupAddress(Long pickupAddress){
      if(pickupAddress == null){
         return this;
      }
      return withPickupAddress(Operator.EQUAL, pickupAddress);
    }
    public MovingOrderRequest<T> withPickupAddressMatching(PickupAddressRequest pickupAddress){
       return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.PICKUP_ADDRESS_PROPERTY, pickupAddress, PickupAddress.ID_PROPERTY));
    }

    public MovingOrderRequest<T> filterByDeliveryAddress(PickupAddress... deliveryAddress){
      if (deliveryAddress == null || deliveryAddress.length == 0) {
        throw new IllegalArgumentException("filterByDeliveryAddress parameter deliveryAddress cannot be empty");
      }
      return appendSearchCriteria(createDeliveryAddressCriteria(Operator.EQUAL, (Object[])deliveryAddress));
    }

    public MovingOrderRequest<T> withDeliveryAddress(Operator operator, Object... values){
       return appendSearchCriteria(createDeliveryAddressCriteria(operator, values));
    }

    public MovingOrderRequest<T> withDeliveryAddressIsUnknown(){
       return withDeliveryAddress(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withDeliveryAddressIsKnown(){
       return withDeliveryAddress(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeliveryAddressCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.DELIVERY_ADDRESS_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> filterByDeliveryAddress(Long deliveryAddress){
      if(deliveryAddress == null){
         return this;
      }
      return withDeliveryAddress(Operator.EQUAL, deliveryAddress);
    }
    public MovingOrderRequest<T> withDeliveryAddressMatching(PickupAddressRequest deliveryAddress){
       return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.DELIVERY_ADDRESS_PROPERTY, deliveryAddress, PickupAddress.ID_PROPERTY));
    }

    public MovingOrderRequest<T> filterByTotalWeight(BigDecimal... totalWeight){
      if (totalWeight == null || totalWeight.length == 0) {
        throw new IllegalArgumentException("filterByTotalWeight parameter totalWeight cannot be empty");
      }
      return appendSearchCriteria(createTotalWeightCriteria(Operator.EQUAL, (Object[])totalWeight));
    }

    public MovingOrderRequest<T> withTotalWeight(Operator operator, Object... values){
       return appendSearchCriteria(createTotalWeightCriteria(operator, values));
    }

    public MovingOrderRequest<T> withTotalWeightIsUnknown(){
       return withTotalWeight(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withTotalWeightIsKnown(){
       return withTotalWeight(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalWeightCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.TOTAL_WEIGHT_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withTotalWeightGreaterThan(BigDecimal totalWeight){
       return withTotalWeight(Operator.GREATER_THAN, totalWeight);
    }

    public MovingOrderRequest<T> withTotalWeightGreaterThanOrEqualTo(BigDecimal totalWeight){
       return withTotalWeight(Operator.GREATER_THAN_OR_EQUAL, totalWeight);
    }

    public MovingOrderRequest<T> withTotalWeightLessThan(BigDecimal totalWeight){
       return withTotalWeight(Operator.LESS_THAN, totalWeight);
    }

    public MovingOrderRequest<T> withTotalWeightLessThanOrEqualTo(BigDecimal totalWeight){
       return withTotalWeight(Operator.LESS_THAN_OR_EQUAL, totalWeight);
    }

    public MovingOrderRequest<T> withTotalWeightBetween(BigDecimal startOfTotalWeight, BigDecimal endOfTotalWeight){
       return withTotalWeight(Operator.BETWEEN, startOfTotalWeight, endOfTotalWeight);
    }



    public MovingOrderRequest<T> filterByTotalVolume(BigDecimal... totalVolume){
      if (totalVolume == null || totalVolume.length == 0) {
        throw new IllegalArgumentException("filterByTotalVolume parameter totalVolume cannot be empty");
      }
      return appendSearchCriteria(createTotalVolumeCriteria(Operator.EQUAL, (Object[])totalVolume));
    }

    public MovingOrderRequest<T> withTotalVolume(Operator operator, Object... values){
       return appendSearchCriteria(createTotalVolumeCriteria(operator, values));
    }

    public MovingOrderRequest<T> withTotalVolumeIsUnknown(){
       return withTotalVolume(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withTotalVolumeIsKnown(){
       return withTotalVolume(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createTotalVolumeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.TOTAL_VOLUME_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withTotalVolumeGreaterThan(BigDecimal totalVolume){
       return withTotalVolume(Operator.GREATER_THAN, totalVolume);
    }

    public MovingOrderRequest<T> withTotalVolumeGreaterThanOrEqualTo(BigDecimal totalVolume){
       return withTotalVolume(Operator.GREATER_THAN_OR_EQUAL, totalVolume);
    }

    public MovingOrderRequest<T> withTotalVolumeLessThan(BigDecimal totalVolume){
       return withTotalVolume(Operator.LESS_THAN, totalVolume);
    }

    public MovingOrderRequest<T> withTotalVolumeLessThanOrEqualTo(BigDecimal totalVolume){
       return withTotalVolume(Operator.LESS_THAN_OR_EQUAL, totalVolume);
    }

    public MovingOrderRequest<T> withTotalVolumeBetween(BigDecimal startOfTotalVolume, BigDecimal endOfTotalVolume){
       return withTotalVolume(Operator.BETWEEN, startOfTotalVolume, endOfTotalVolume);
    }



    public MovingOrderRequest<T> filterByEstimatedCost(BigDecimal... estimatedCost){
      if (estimatedCost == null || estimatedCost.length == 0) {
        throw new IllegalArgumentException("filterByEstimatedCost parameter estimatedCost cannot be empty");
      }
      return appendSearchCriteria(createEstimatedCostCriteria(Operator.EQUAL, (Object[])estimatedCost));
    }

    public MovingOrderRequest<T> withEstimatedCost(Operator operator, Object... values){
       return appendSearchCriteria(createEstimatedCostCriteria(operator, values));
    }

    public MovingOrderRequest<T> withEstimatedCostIsUnknown(){
       return withEstimatedCost(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withEstimatedCostIsKnown(){
       return withEstimatedCost(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createEstimatedCostCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.ESTIMATED_COST_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withEstimatedCostGreaterThan(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.GREATER_THAN, estimatedCost);
    }

    public MovingOrderRequest<T> withEstimatedCostGreaterThanOrEqualTo(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.GREATER_THAN_OR_EQUAL, estimatedCost);
    }

    public MovingOrderRequest<T> withEstimatedCostLessThan(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.LESS_THAN, estimatedCost);
    }

    public MovingOrderRequest<T> withEstimatedCostLessThanOrEqualTo(BigDecimal estimatedCost){
       return withEstimatedCost(Operator.LESS_THAN_OR_EQUAL, estimatedCost);
    }

    public MovingOrderRequest<T> withEstimatedCostBetween(BigDecimal startOfEstimatedCost, BigDecimal endOfEstimatedCost){
       return withEstimatedCost(Operator.BETWEEN, startOfEstimatedCost, endOfEstimatedCost);
    }



    public MovingOrderRequest<T> filterByActualCost(BigDecimal... actualCost){
      if (actualCost == null || actualCost.length == 0) {
        throw new IllegalArgumentException("filterByActualCost parameter actualCost cannot be empty");
      }
      return appendSearchCriteria(createActualCostCriteria(Operator.EQUAL, (Object[])actualCost));
    }

    public MovingOrderRequest<T> withActualCost(Operator operator, Object... values){
       return appendSearchCriteria(createActualCostCriteria(operator, values));
    }

    public MovingOrderRequest<T> withActualCostIsUnknown(){
       return withActualCost(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withActualCostIsKnown(){
       return withActualCost(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createActualCostCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.ACTUAL_COST_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withActualCostGreaterThan(BigDecimal actualCost){
       return withActualCost(Operator.GREATER_THAN, actualCost);
    }

    public MovingOrderRequest<T> withActualCostGreaterThanOrEqualTo(BigDecimal actualCost){
       return withActualCost(Operator.GREATER_THAN_OR_EQUAL, actualCost);
    }

    public MovingOrderRequest<T> withActualCostLessThan(BigDecimal actualCost){
       return withActualCost(Operator.LESS_THAN, actualCost);
    }

    public MovingOrderRequest<T> withActualCostLessThanOrEqualTo(BigDecimal actualCost){
       return withActualCost(Operator.LESS_THAN_OR_EQUAL, actualCost);
    }

    public MovingOrderRequest<T> withActualCostBetween(BigDecimal startOfActualCost, BigDecimal endOfActualCost){
       return withActualCost(Operator.BETWEEN, startOfActualCost, endOfActualCost);
    }



    public MovingOrderRequest<T> filterByCreatedTime(LocalDateTime... createdTime){
      if (createdTime == null || createdTime.length == 0) {
        throw new IllegalArgumentException("filterByCreatedTime parameter createdTime cannot be empty");
      }
      return appendSearchCriteria(createCreatedTimeCriteria(Operator.EQUAL, (Object[])createdTime));
    }

    public MovingOrderRequest<T> withCreatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreatedTimeCriteria(operator, values));
    }

    public MovingOrderRequest<T> withCreatedTimeIsUnknown(){
       return withCreatedTime(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withCreatedTimeIsKnown(){
       return withCreatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.CREATED_TIME_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withCreatedTimeGreaterThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeGreaterThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN_OR_EQUAL, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeLessThan(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeLessThanOrEqualTo(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN_OR_EQUAL, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeBetween(LocalDateTime startOfCreatedTime, LocalDateTime endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }
    public MovingOrderRequest<T> withCreatedTimeBefore(LocalDateTime createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeBefore(Date createdTime){
       return withCreatedTime(Operator.LESS_THAN, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeAfter(LocalDateTime createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeAfter(Date createdTime){
       return withCreatedTime(Operator.GREATER_THAN, createdTime);
    }

    public MovingOrderRequest<T> withCreatedTimeBetween(Date startOfCreatedTime, Date endOfCreatedTime){
       return withCreatedTime(Operator.BETWEEN, startOfCreatedTime, endOfCreatedTime);
    }




    public MovingOrderRequest<T> filterByUpdatedTime(LocalDateTime... updatedTime){
      if (updatedTime == null || updatedTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdatedTime parameter updatedTime cannot be empty");
      }
      return appendSearchCriteria(createUpdatedTimeCriteria(Operator.EQUAL, (Object[])updatedTime));
    }

    public MovingOrderRequest<T> withUpdatedTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdatedTimeCriteria(operator, values));
    }

    public MovingOrderRequest<T> withUpdatedTimeIsUnknown(){
       return withUpdatedTime(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withUpdatedTimeIsKnown(){
       return withUpdatedTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdatedTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.UPDATED_TIME_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withUpdatedTimeGreaterThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeGreaterThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN_OR_EQUAL, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeLessThan(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeLessThanOrEqualTo(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN_OR_EQUAL, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeBetween(LocalDateTime startOfUpdatedTime, LocalDateTime endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }
    public MovingOrderRequest<T> withUpdatedTimeBefore(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeBefore(Date updatedTime){
       return withUpdatedTime(Operator.LESS_THAN, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeAfter(LocalDateTime updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeAfter(Date updatedTime){
       return withUpdatedTime(Operator.GREATER_THAN, updatedTime);
    }

    public MovingOrderRequest<T> withUpdatedTimeBetween(Date startOfUpdatedTime, Date endOfUpdatedTime){
       return withUpdatedTime(Operator.BETWEEN, startOfUpdatedTime, endOfUpdatedTime);
    }




    public MovingOrderRequest<T> filterByVersion(Long... version){
      if (version == null || version.length == 0) {
        throw new IllegalArgumentException("filterByVersion parameter version cannot be empty");
      }
      return appendSearchCriteria(createVersionCriteria(Operator.EQUAL, (Object[])version));
    }

    public MovingOrderRequest<T> withVersion(Operator operator, Object... values){
       return appendSearchCriteria(createVersionCriteria(operator, values));
    }

    public MovingOrderRequest<T> withVersionIsUnknown(){
       return withVersion(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withVersionIsKnown(){
       return withVersion(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createVersionCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.VERSION_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withVersionGreaterThan(Long version){
       return withVersion(Operator.GREATER_THAN, version);
    }

    public MovingOrderRequest<T> withVersionGreaterThanOrEqualTo(Long version){
       return withVersion(Operator.GREATER_THAN_OR_EQUAL, version);
    }

    public MovingOrderRequest<T> withVersionLessThan(Long version){
       return withVersion(Operator.LESS_THAN, version);
    }

    public MovingOrderRequest<T> withVersionLessThanOrEqualTo(Long version){
       return withVersion(Operator.LESS_THAN_OR_EQUAL, version);
    }

    public MovingOrderRequest<T> withVersionBetween(Long startOfVersion, Long endOfVersion){
       return withVersion(Operator.BETWEEN, startOfVersion, endOfVersion);
    }

    public MovingOrderRequest<T> withDispatchPlanListMatching(DispatchPlanRequest dispatchPlanRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, dispatchPlanRequest, DispatchPlan.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutDispatchPlanListMatching(DispatchPlanRequest dispatchPlanRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, dispatchPlanRequest, DispatchPlan.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveDispatchPlans(){
        return withDispatchPlanListMatching(Q.dispatchPlans().unlimited());
    }

    public MovingOrderRequest<T> haveNoDispatchPlans(){
        return withoutDispatchPlanListMatching(Q.dispatchPlans().unlimited());
    }
    public MovingOrderRequest<T> withCargoItemListMatching(CargoItemRequest cargoItemRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, cargoItemRequest, CargoItem.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutCargoItemListMatching(CargoItemRequest cargoItemRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, cargoItemRequest, CargoItem.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveCargoItems(){
        return withCargoItemListMatching(Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> haveNoCargoItems(){
        return withoutCargoItemListMatching(Q.cargoItems().unlimited());
    }
    public MovingOrderRequest<T> withFeedbackReviewListMatching(FeedbackReviewRequest feedbackReviewRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, feedbackReviewRequest, FeedbackReview.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutFeedbackReviewListMatching(FeedbackReviewRequest feedbackReviewRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, feedbackReviewRequest, FeedbackReview.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveFeedbackReviews(){
        return withFeedbackReviewListMatching(Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> haveNoFeedbackReviews(){
        return withoutFeedbackReviewListMatching(Q.feedbackReviews().unlimited());
    }
    public MovingOrderRequest<T> withInvoiceListMatching(InvoiceRequest invoiceRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, invoiceRequest, Invoice.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutInvoiceListMatching(InvoiceRequest invoiceRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, invoiceRequest, Invoice.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveInvoices(){
        return withInvoiceListMatching(Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> haveNoInvoices(){
        return withoutInvoiceListMatching(Q.invoices().unlimited());
    }
    public MovingOrderRequest<T> withClaimsRecordListMatching(ClaimsRecordRequest claimsRecordRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, claimsRecordRequest, ClaimsRecord.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutClaimsRecordListMatching(ClaimsRecordRequest claimsRecordRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, claimsRecordRequest, ClaimsRecord.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveClaimsRecords(){
        return withClaimsRecordListMatching(Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> haveNoClaimsRecords(){
        return withoutClaimsRecordListMatching(Q.claimsRecords().unlimited());
    }
    public MovingOrderRequest<T> withCustomsDeclarationListMatching(CustomsDeclarationRequest customsDeclarationRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, customsDeclarationRequest, CustomsDeclaration.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutCustomsDeclarationListMatching(CustomsDeclarationRequest customsDeclarationRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, customsDeclarationRequest, CustomsDeclaration.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveCustomsDeclarations(){
        return withCustomsDeclarationListMatching(Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> haveNoCustomsDeclarations(){
        return withoutCustomsDeclarationListMatching(Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> count(){
        super.count();
        return this;
    }
    public MovingOrderRequest<T> countAs(String retName){
        super.count(retName);
        return this;
    }
    public MovingOrderRequest minTotalWeight(){
        return minTotalWeightAs(prefix("minOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest minTotalWeightAs(String retName){
        super.min(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest maxTotalWeight(){
        return maxTotalWeightAs(prefix("maxOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest maxTotalWeightAs(String retName){
        super.max(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest sumTotalWeight(){
        return sumTotalWeightAs(prefix("sumOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest sumTotalWeightAs(String retName){
        super.sum(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest avgTotalWeight(){
        return avgTotalWeightAs(prefix("avgOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest avgTotalWeightAs(String retName){
        super.avg(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest standardDeviationTotalWeight(){
        return standardDeviationTotalWeightAs(prefix("standardDeviationOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest standardDeviationTotalWeightAs(String retName){
        super.standardDeviation(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest squareRootOfPopulationStandardDeviationTotalWeight(){
        return squareRootOfPopulationStandardDeviationTotalWeightAs(prefix("squareRootOfPopulationStandardDeviationOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest squareRootOfPopulationStandardDeviationTotalWeightAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest sampleVarianceTotalWeight(){
        return sampleVarianceTotalWeightAs(prefix("sampleVarianceOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest sampleVarianceTotalWeightAs(String retName){
        super.sampleVariance(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest samplePopulationVarianceTotalWeight(){
        return samplePopulationVarianceTotalWeightAs(prefix("samplePopulationVarianceOf",MovingOrder.TOTAL_WEIGHT_PROPERTY));
    }

    public MovingOrderRequest samplePopulationVarianceTotalWeightAs(String retName){
        super.samplePopulationVariance(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
        return this;
    }
    public MovingOrderRequest minTotalVolume(){
        return minTotalVolumeAs(prefix("minOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest minTotalVolumeAs(String retName){
        super.min(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest maxTotalVolume(){
        return maxTotalVolumeAs(prefix("maxOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest maxTotalVolumeAs(String retName){
        super.max(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest sumTotalVolume(){
        return sumTotalVolumeAs(prefix("sumOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest sumTotalVolumeAs(String retName){
        super.sum(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest avgTotalVolume(){
        return avgTotalVolumeAs(prefix("avgOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest avgTotalVolumeAs(String retName){
        super.avg(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest standardDeviationTotalVolume(){
        return standardDeviationTotalVolumeAs(prefix("standardDeviationOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest standardDeviationTotalVolumeAs(String retName){
        super.standardDeviation(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest squareRootOfPopulationStandardDeviationTotalVolume(){
        return squareRootOfPopulationStandardDeviationTotalVolumeAs(prefix("squareRootOfPopulationStandardDeviationOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest squareRootOfPopulationStandardDeviationTotalVolumeAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest sampleVarianceTotalVolume(){
        return sampleVarianceTotalVolumeAs(prefix("sampleVarianceOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest sampleVarianceTotalVolumeAs(String retName){
        super.sampleVariance(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest samplePopulationVarianceTotalVolume(){
        return samplePopulationVarianceTotalVolumeAs(prefix("samplePopulationVarianceOf",MovingOrder.TOTAL_VOLUME_PROPERTY));
    }

    public MovingOrderRequest samplePopulationVarianceTotalVolumeAs(String retName){
        super.samplePopulationVariance(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
        return this;
    }
    public MovingOrderRequest minEstimatedCost(){
        return minEstimatedCostAs(prefix("minOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest minEstimatedCostAs(String retName){
        super.min(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest maxEstimatedCost(){
        return maxEstimatedCostAs(prefix("maxOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest maxEstimatedCostAs(String retName){
        super.max(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest sumEstimatedCost(){
        return sumEstimatedCostAs(prefix("sumOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest sumEstimatedCostAs(String retName){
        super.sum(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest avgEstimatedCost(){
        return avgEstimatedCostAs(prefix("avgOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest avgEstimatedCostAs(String retName){
        super.avg(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest standardDeviationEstimatedCost(){
        return standardDeviationEstimatedCostAs(prefix("standardDeviationOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest standardDeviationEstimatedCostAs(String retName){
        super.standardDeviation(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest squareRootOfPopulationStandardDeviationEstimatedCost(){
        return squareRootOfPopulationStandardDeviationEstimatedCostAs(prefix("squareRootOfPopulationStandardDeviationOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest squareRootOfPopulationStandardDeviationEstimatedCostAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest sampleVarianceEstimatedCost(){
        return sampleVarianceEstimatedCostAs(prefix("sampleVarianceOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest sampleVarianceEstimatedCostAs(String retName){
        super.sampleVariance(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest samplePopulationVarianceEstimatedCost(){
        return samplePopulationVarianceEstimatedCostAs(prefix("samplePopulationVarianceOf",MovingOrder.ESTIMATED_COST_PROPERTY));
    }

    public MovingOrderRequest samplePopulationVarianceEstimatedCostAs(String retName){
        super.samplePopulationVariance(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest minActualCost(){
        return minActualCostAs(prefix("minOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest minActualCostAs(String retName){
        super.min(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest maxActualCost(){
        return maxActualCostAs(prefix("maxOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest maxActualCostAs(String retName){
        super.max(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest sumActualCost(){
        return sumActualCostAs(prefix("sumOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest sumActualCostAs(String retName){
        super.sum(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest avgActualCost(){
        return avgActualCostAs(prefix("avgOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest avgActualCostAs(String retName){
        super.avg(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest standardDeviationActualCost(){
        return standardDeviationActualCostAs(prefix("standardDeviationOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest standardDeviationActualCostAs(String retName){
        super.standardDeviation(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest squareRootOfPopulationStandardDeviationActualCost(){
        return squareRootOfPopulationStandardDeviationActualCostAs(prefix("squareRootOfPopulationStandardDeviationOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest squareRootOfPopulationStandardDeviationActualCostAs(String retName){
        super.squareRootOfPopulationStandardDeviation(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest sampleVarianceActualCost(){
        return sampleVarianceActualCostAs(prefix("sampleVarianceOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest sampleVarianceActualCostAs(String retName){
        super.sampleVariance(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest samplePopulationVarianceActualCost(){
        return samplePopulationVarianceActualCostAs(prefix("samplePopulationVarianceOf",MovingOrder.ACTUAL_COST_PROPERTY));
    }

    public MovingOrderRequest samplePopulationVarianceActualCostAs(String retName){
        super.samplePopulationVariance(retName, MovingOrder.ACTUAL_COST_PROPERTY);
        return this;
    }
    public MovingOrderRequest<T> groupByCustomerWithDetails(){
       return groupByCustomerWithDetails(Q.privateCustomers().unlimited());
    }

    public MovingOrderRequest<T> groupByCustomerWithDetails(PrivateCustomerRequest subRequest){
       aggregate(MovingOrder.CUSTOMER_PROPERTY, subRequest);
       return this;
    }

    public MovingOrderRequest<T> groupByPickupAddressWithDetails(){
       return groupByPickupAddressWithDetails(Q.pickupAddresses().unlimited());
    }

    public MovingOrderRequest<T> groupByPickupAddressWithDetails(PickupAddressRequest subRequest){
       aggregate(MovingOrder.PICKUP_ADDRESS_PROPERTY, subRequest);
       return this;
    }

    public MovingOrderRequest<T> groupByDeliveryAddressWithDetails(){
       return groupByDeliveryAddressWithDetails(Q.pickupAddresses().unlimited());
    }

    public MovingOrderRequest<T> groupByDeliveryAddressWithDetails(PickupAddressRequest subRequest){
       aggregate(MovingOrder.DELIVERY_ADDRESS_PROPERTY, subRequest);
       return this;
    }








    public MovingOrderRequest<T> groupByDispatchPlansWithDetails(DispatchPlanRequest subRequest){
       aggregate(MovingOrder.DISPATCH_PLAN_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByCargoItemsWithDetails(CargoItemRequest subRequest){
       aggregate(MovingOrder.CARGO_ITEM_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByFeedbackReviewsWithDetails(FeedbackReviewRequest subRequest){
       aggregate(MovingOrder.FEEDBACK_REVIEW_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByInvoicesWithDetails(InvoiceRequest subRequest){
       aggregate(MovingOrder.INVOICE_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByClaimsRecordsWithDetails(ClaimsRecordRequest subRequest){
       aggregate(MovingOrder.CLAIMS_RECORD_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByCustomsDeclarationsWithDetails(CustomsDeclarationRequest subRequest){
       aggregate(MovingOrder.CUSTOMS_DECLARATION_LIST_PROPERTY, subRequest);
       return this;
    }

    public MovingOrderRequest<T> groupById(){
       groupBy(MovingOrder.ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByIdAs(String retName){
       groupBy(retName, MovingOrder.ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.ID_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByOrderNumber(){
       groupBy(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByOrderNumberAs(String retName){
       groupBy(retName, MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByOrderNumberWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.ORDER_NUMBER_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByStatus(){
       groupBy(MovingOrder.STATUS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByStatusAs(String retName){
       groupBy(retName, MovingOrder.STATUS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByStatusWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.STATUS_PROPERTY, function);
       return this;
    }
    public MovingOrderRequest<T> groupByCustomerWith(PrivateCustomerRequest subRequest){
       groupBy(MovingOrder.CUSTOMER_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByCustomer(){
       groupBy(MovingOrder.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByCustomerAs(String retName){
       groupBy(retName, MovingOrder.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByCustomerWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.CUSTOMER_PROPERTY, function);
       return this;
    }
    public MovingOrderRequest<T> groupByPickupAddressWith(PickupAddressRequest subRequest){
       groupBy(MovingOrder.PICKUP_ADDRESS_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByPickupAddress(){
       groupBy(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByPickupAddressAs(String retName){
       groupBy(retName, MovingOrder.PICKUP_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByPickupAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.PICKUP_ADDRESS_PROPERTY, function);
       return this;
    }
    public MovingOrderRequest<T> groupByDeliveryAddressWith(PickupAddressRequest subRequest){
       groupBy(MovingOrder.DELIVERY_ADDRESS_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByDeliveryAddress(){
       groupBy(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByDeliveryAddressAs(String retName){
       groupBy(retName, MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByDeliveryAddressWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.DELIVERY_ADDRESS_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByTotalWeight(){
       groupBy(MovingOrder.TOTAL_WEIGHT_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByTotalWeightAs(String retName){
       groupBy(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByTotalWeightWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.TOTAL_WEIGHT_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByTotalVolume(){
       groupBy(MovingOrder.TOTAL_VOLUME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByTotalVolumeAs(String retName){
       groupBy(retName, MovingOrder.TOTAL_VOLUME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByTotalVolumeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.TOTAL_VOLUME_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByEstimatedCost(){
       groupBy(MovingOrder.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByEstimatedCostAs(String retName){
       groupBy(retName, MovingOrder.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByEstimatedCostWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.ESTIMATED_COST_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByActualCost(){
       groupBy(MovingOrder.ACTUAL_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByActualCostAs(String retName){
       groupBy(retName, MovingOrder.ACTUAL_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByActualCostWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.ACTUAL_COST_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByCreatedTime(){
       groupBy(MovingOrder.CREATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByCreatedTimeAs(String retName){
       groupBy(retName, MovingOrder.CREATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByCreatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.CREATED_TIME_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByUpdatedTime(){
       groupBy(MovingOrder.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByUpdatedTimeAs(String retName){
       groupBy(retName, MovingOrder.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByUpdatedTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.UPDATED_TIME_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByVersion(){
       groupBy(MovingOrder.VERSION_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByVersionAs(String retName){
       groupBy(retName, MovingOrder.VERSION_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByVersionWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.VERSION_PROPERTY, function);
       return this;
    }



    public MovingOrderRequest<T> orderByIdAscending(){
       addOrderByAscending(MovingOrder.ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByIdDescending(){
       addOrderByDescending(MovingOrder.ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByOrderNumberAscending(){
       addOrderByAscending(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByOrderNumberDescending(){
       addOrderByDescending(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderByOrderNumberAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByOrderNumberDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingOrder.ORDER_NUMBER_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderByStatusAscending(){
       addOrderByAscending(MovingOrder.STATUS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByStatusDescending(){
       addOrderByDescending(MovingOrder.STATUS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderByStatusAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingOrder.STATUS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByStatusDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingOrder.STATUS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderByCustomerAscending(){
       addOrderByAscending(MovingOrder.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByCustomerDescending(){
       addOrderByDescending(MovingOrder.CUSTOMER_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByPickupAddressAscending(){
       addOrderByAscending(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByPickupAddressDescending(){
       addOrderByDescending(MovingOrder.PICKUP_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByDeliveryAddressAscending(){
       addOrderByAscending(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByDeliveryAddressDescending(){
       addOrderByDescending(MovingOrder.DELIVERY_ADDRESS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByTotalWeightAscending(){
       addOrderByAscending(MovingOrder.TOTAL_WEIGHT_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByTotalWeightDescending(){
       addOrderByDescending(MovingOrder.TOTAL_WEIGHT_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByTotalVolumeAscending(){
       addOrderByAscending(MovingOrder.TOTAL_VOLUME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByTotalVolumeDescending(){
       addOrderByDescending(MovingOrder.TOTAL_VOLUME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByEstimatedCostAscending(){
       addOrderByAscending(MovingOrder.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByEstimatedCostDescending(){
       addOrderByDescending(MovingOrder.ESTIMATED_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByActualCostAscending(){
       addOrderByAscending(MovingOrder.ACTUAL_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByActualCostDescending(){
       addOrderByDescending(MovingOrder.ACTUAL_COST_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByCreatedTimeAscending(){
       addOrderByAscending(MovingOrder.CREATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByCreatedTimeDescending(){
       addOrderByDescending(MovingOrder.CREATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByUpdatedTimeAscending(){
       addOrderByAscending(MovingOrder.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByUpdatedTimeDescending(){
       addOrderByDescending(MovingOrder.UPDATED_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByVersionAscending(){
       addOrderByAscending(MovingOrder.VERSION_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByVersionDescending(){
       addOrderByDescending(MovingOrder.VERSION_PROPERTY);
       return this;
    }


    public MovingOrderRequest<T> statsFromDispatchPlansAs(String name, DispatchPlanRequest subRequest){
       return statsFromDispatchPlansAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromDispatchPlansAs(String name, DispatchPlanRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(DispatchPlan.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromDispatchPlans(DispatchPlanRequest subRequest){
       return statsFromDispatchPlansAs(REFINEMENTS, subRequest);
    }
    public MovingOrderRequest<T> statsFromCargoItemsAs(String name, CargoItemRequest subRequest){
       return statsFromCargoItemsAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromCargoItemsAs(String name, CargoItemRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(CargoItem.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromCargoItems(CargoItemRequest subRequest){
       return statsFromCargoItemsAs(REFINEMENTS, subRequest);
    }
    public MovingOrderRequest<T> statsFromFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
       return statsFromFeedbackReviewsAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(FeedbackReview.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromFeedbackReviews(FeedbackReviewRequest subRequest){
       return statsFromFeedbackReviewsAs(REFINEMENTS, subRequest);
    }
    public MovingOrderRequest<T> statsFromInvoicesAs(String name, InvoiceRequest subRequest){
       return statsFromInvoicesAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromInvoicesAs(String name, InvoiceRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(Invoice.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromInvoices(InvoiceRequest subRequest){
       return statsFromInvoicesAs(REFINEMENTS, subRequest);
    }
    public MovingOrderRequest<T> statsFromClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
       return statsFromClaimsRecordsAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromClaimsRecordsAs(String name, ClaimsRecordRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(ClaimsRecord.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromClaimsRecords(ClaimsRecordRequest subRequest){
       return statsFromClaimsRecordsAs(REFINEMENTS, subRequest);
    }
    public MovingOrderRequest<T> statsFromCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
       return statsFromCustomsDeclarationsAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(CustomsDeclaration.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromCustomsDeclarations(CustomsDeclarationRequest subRequest){
       return statsFromCustomsDeclarationsAs(REFINEMENTS, subRequest);
    }
    public PrivateCustomerRequest rollUpToCustomer(){
       PrivateCustomerRequest customer = Q.privateCustomers().unlimited();
       this.withCustomerMatching(customer)
           .groupByCustomerWith(customer);
       return customer;
    }

    public PickupAddressRequest rollUpToPickupAddress(){
       PickupAddressRequest pickupAddress = Q.pickupAddresses().unlimited();
       this.withPickupAddressMatching(pickupAddress)
           .groupByPickupAddressWith(pickupAddress);
       return pickupAddress;
    }

    public PickupAddressRequest rollUpToDeliveryAddress(){
       PickupAddressRequest deliveryAddress = Q.pickupAddresses().unlimited();
       this.withDeliveryAddressMatching(deliveryAddress)
           .groupByDeliveryAddressWith(deliveryAddress);
       return deliveryAddress;
    }








    public MovingOrderRequest<T> countDispatchPlans(){
        return countDispatchPlansAs("Count");
    }

    public MovingOrderRequest<T> countDispatchPlansAs(String name){
        return countDispatchPlansWith(name, Q.dispatchPlans().unlimited());
    }

    public MovingOrderRequest<T> countDispatchPlansWith(String name, DispatchPlanRequest subRequest){
        return statsFromDispatchPlansAs(name, subRequest.count(), true);
    }
    public MovingOrderRequest<T> countCargoItems(){
        return countCargoItemsAs("Count");
    }

    public MovingOrderRequest<T> countCargoItemsAs(String name){
        return countCargoItemsWith(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> countCargoItemsWith(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.count(), true);
    }
    public MovingOrderRequest<T> countFeedbackReviews(){
        return countFeedbackReviewsAs("Count");
    }

    public MovingOrderRequest<T> countFeedbackReviewsAs(String name){
        return countFeedbackReviewsWith(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> countFeedbackReviewsWith(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.count(), true);
    }
    public MovingOrderRequest<T> countInvoices(){
        return countInvoicesAs("Count");
    }

    public MovingOrderRequest<T> countInvoicesAs(String name){
        return countInvoicesWith(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> countInvoicesWith(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.count(), true);
    }
    public MovingOrderRequest<T> countClaimsRecords(){
        return countClaimsRecordsAs("Count");
    }

    public MovingOrderRequest<T> countClaimsRecordsAs(String name){
        return countClaimsRecordsWith(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> countClaimsRecordsWith(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.count(), true);
    }
    public MovingOrderRequest<T> countCustomsDeclarations(){
        return countCustomsDeclarationsAs("Count");
    }

    public MovingOrderRequest<T> countCustomsDeclarationsAs(String name){
        return countCustomsDeclarationsWith(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> countCustomsDeclarationsWith(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.count(), true);
    }
    public MovingOrderRequest<T> minWeightKgOfCargoItems(){
        return minWeightKgOfCargoItemsAs("minWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> minWeightKgOfCargoItemsAs(String name){
        return minWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> minWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.minWeightKg(), true);
    }
    public MovingOrderRequest<T> maxWeightKgOfCargoItems(){
        return maxWeightKgOfCargoItemsAs("maxWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> maxWeightKgOfCargoItemsAs(String name){
        return maxWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> maxWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.maxWeightKg(), true);
    }
    public MovingOrderRequest<T> sumWeightKgOfCargoItems(){
        return sumWeightKgOfCargoItemsAs("sumWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> sumWeightKgOfCargoItemsAs(String name){
        return sumWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> sumWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.sumWeightKg(), true);
    }
    public MovingOrderRequest<T> avgWeightKgOfCargoItems(){
        return avgWeightKgOfCargoItemsAs("avgWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> avgWeightKgOfCargoItemsAs(String name){
        return avgWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> avgWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.avgWeightKg(), true);
    }
    public MovingOrderRequest<T> standardDeviationWeightKgOfCargoItems(){
        return standardDeviationWeightKgOfCargoItemsAs("stdDevWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> standardDeviationWeightKgOfCargoItemsAs(String name){
        return standardDeviationWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.standardDeviationWeightKg(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationWeightKgOfCargoItems(){
        return squareRootOfPopulationStandardDeviationWeightKgOfCargoItemsAs("stdDevPopWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationWeightKgOfCargoItemsAs(String name){
        return squareRootOfPopulationStandardDeviationWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.squareRootOfPopulationStandardDeviationWeightKg(), true);
    }
    public MovingOrderRequest<T> sampleVarianceWeightKgOfCargoItems(){
        return sampleVarianceWeightKgOfCargoItemsAs("varSampWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> sampleVarianceWeightKgOfCargoItemsAs(String name){
        return sampleVarianceWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.sampleVarianceWeightKg(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceWeightKgOfCargoItems(){
        return samplePopulationVarianceWeightKgOfCargoItemsAs("varPopWeightKgOfCargoItems");
    }

    public MovingOrderRequest<T> samplePopulationVarianceWeightKgOfCargoItemsAs(String name){
        return samplePopulationVarianceWeightKgOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceWeightKgOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.samplePopulationVarianceWeightKg(), true);
    }
    public MovingOrderRequest<T> minVolumeM3OfCargoItems(){
        return minVolumeM3OfCargoItemsAs("minVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> minVolumeM3OfCargoItemsAs(String name){
        return minVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> minVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.minVolumeM3(), true);
    }
    public MovingOrderRequest<T> maxVolumeM3OfCargoItems(){
        return maxVolumeM3OfCargoItemsAs("maxVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> maxVolumeM3OfCargoItemsAs(String name){
        return maxVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> maxVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.maxVolumeM3(), true);
    }
    public MovingOrderRequest<T> sumVolumeM3OfCargoItems(){
        return sumVolumeM3OfCargoItemsAs("sumVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> sumVolumeM3OfCargoItemsAs(String name){
        return sumVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> sumVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.sumVolumeM3(), true);
    }
    public MovingOrderRequest<T> avgVolumeM3OfCargoItems(){
        return avgVolumeM3OfCargoItemsAs("avgVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> avgVolumeM3OfCargoItemsAs(String name){
        return avgVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> avgVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.avgVolumeM3(), true);
    }
    public MovingOrderRequest<T> standardDeviationVolumeM3OfCargoItems(){
        return standardDeviationVolumeM3OfCargoItemsAs("stdDevVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> standardDeviationVolumeM3OfCargoItemsAs(String name){
        return standardDeviationVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.standardDeviationVolumeM3(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationVolumeM3OfCargoItems(){
        return squareRootOfPopulationStandardDeviationVolumeM3OfCargoItemsAs("stdDevPopVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationVolumeM3OfCargoItemsAs(String name){
        return squareRootOfPopulationStandardDeviationVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.squareRootOfPopulationStandardDeviationVolumeM3(), true);
    }
    public MovingOrderRequest<T> sampleVarianceVolumeM3OfCargoItems(){
        return sampleVarianceVolumeM3OfCargoItemsAs("varSampVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> sampleVarianceVolumeM3OfCargoItemsAs(String name){
        return sampleVarianceVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.sampleVarianceVolumeM3(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceVolumeM3OfCargoItems(){
        return samplePopulationVarianceVolumeM3OfCargoItemsAs("varPopVolumeM3OfCargoItems");
    }

    public MovingOrderRequest<T> samplePopulationVarianceVolumeM3OfCargoItemsAs(String name){
        return samplePopulationVarianceVolumeM3OfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceVolumeM3OfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.samplePopulationVarianceVolumeM3(), true);
    }
    public MovingOrderRequest<T> minRatingOfFeedbackReviews(){
        return minRatingOfFeedbackReviewsAs("minRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> minRatingOfFeedbackReviewsAs(String name){
        return minRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> minRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.minRating(), true);
    }
    public MovingOrderRequest<T> maxRatingOfFeedbackReviews(){
        return maxRatingOfFeedbackReviewsAs("maxRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> maxRatingOfFeedbackReviewsAs(String name){
        return maxRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> maxRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.maxRating(), true);
    }
    public MovingOrderRequest<T> sumRatingOfFeedbackReviews(){
        return sumRatingOfFeedbackReviewsAs("sumRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> sumRatingOfFeedbackReviewsAs(String name){
        return sumRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> sumRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.sumRating(), true);
    }
    public MovingOrderRequest<T> avgRatingOfFeedbackReviews(){
        return avgRatingOfFeedbackReviewsAs("avgRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> avgRatingOfFeedbackReviewsAs(String name){
        return avgRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> avgRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.avgRating(), true);
    }
    public MovingOrderRequest<T> standardDeviationRatingOfFeedbackReviews(){
        return standardDeviationRatingOfFeedbackReviewsAs("stdDevRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> standardDeviationRatingOfFeedbackReviewsAs(String name){
        return standardDeviationRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.standardDeviationRating(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationRatingOfFeedbackReviews(){
        return squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs("stdDevPopRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs(String name){
        return squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.squareRootOfPopulationStandardDeviationRating(), true);
    }
    public MovingOrderRequest<T> sampleVarianceRatingOfFeedbackReviews(){
        return sampleVarianceRatingOfFeedbackReviewsAs("varSampRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> sampleVarianceRatingOfFeedbackReviewsAs(String name){
        return sampleVarianceRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.sampleVarianceRating(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceRatingOfFeedbackReviews(){
        return samplePopulationVarianceRatingOfFeedbackReviewsAs("varPopRatingOfFeedbackReviews");
    }

    public MovingOrderRequest<T> samplePopulationVarianceRatingOfFeedbackReviewsAs(String name){
        return samplePopulationVarianceRatingOfFeedbackReviewsAs(name, Q.feedbackReviews().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceRatingOfFeedbackReviewsAs(String name, FeedbackReviewRequest subRequest){
        return statsFromFeedbackReviewsAs(name, subRequest.samplePopulationVarianceRating(), true);
    }
    public MovingOrderRequest<T> minAmountOfInvoices(){
        return minAmountOfInvoicesAs("minAmountOfInvoices");
    }

    public MovingOrderRequest<T> minAmountOfInvoicesAs(String name){
        return minAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> minAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.minAmount(), true);
    }
    public MovingOrderRequest<T> maxAmountOfInvoices(){
        return maxAmountOfInvoicesAs("maxAmountOfInvoices");
    }

    public MovingOrderRequest<T> maxAmountOfInvoicesAs(String name){
        return maxAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> maxAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.maxAmount(), true);
    }
    public MovingOrderRequest<T> sumAmountOfInvoices(){
        return sumAmountOfInvoicesAs("sumAmountOfInvoices");
    }

    public MovingOrderRequest<T> sumAmountOfInvoicesAs(String name){
        return sumAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> sumAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.sumAmount(), true);
    }
    public MovingOrderRequest<T> avgAmountOfInvoices(){
        return avgAmountOfInvoicesAs("avgAmountOfInvoices");
    }

    public MovingOrderRequest<T> avgAmountOfInvoicesAs(String name){
        return avgAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> avgAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.avgAmount(), true);
    }
    public MovingOrderRequest<T> standardDeviationAmountOfInvoices(){
        return standardDeviationAmountOfInvoicesAs("stdDevAmountOfInvoices");
    }

    public MovingOrderRequest<T> standardDeviationAmountOfInvoicesAs(String name){
        return standardDeviationAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.standardDeviationAmount(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationAmountOfInvoices(){
        return squareRootOfPopulationStandardDeviationAmountOfInvoicesAs("stdDevPopAmountOfInvoices");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationAmountOfInvoicesAs(String name){
        return squareRootOfPopulationStandardDeviationAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.squareRootOfPopulationStandardDeviationAmount(), true);
    }
    public MovingOrderRequest<T> sampleVarianceAmountOfInvoices(){
        return sampleVarianceAmountOfInvoicesAs("varSampAmountOfInvoices");
    }

    public MovingOrderRequest<T> sampleVarianceAmountOfInvoicesAs(String name){
        return sampleVarianceAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.sampleVarianceAmount(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceAmountOfInvoices(){
        return samplePopulationVarianceAmountOfInvoicesAs("varPopAmountOfInvoices");
    }

    public MovingOrderRequest<T> samplePopulationVarianceAmountOfInvoicesAs(String name){
        return samplePopulationVarianceAmountOfInvoicesAs(name, Q.invoices().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceAmountOfInvoicesAs(String name, InvoiceRequest subRequest){
        return statsFromInvoicesAs(name, subRequest.samplePopulationVarianceAmount(), true);
    }
    public MovingOrderRequest<T> minClaimAmountOfClaimsRecords(){
        return minClaimAmountOfClaimsRecordsAs("minClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> minClaimAmountOfClaimsRecordsAs(String name){
        return minClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> minClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.minClaimAmount(), true);
    }
    public MovingOrderRequest<T> maxClaimAmountOfClaimsRecords(){
        return maxClaimAmountOfClaimsRecordsAs("maxClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> maxClaimAmountOfClaimsRecordsAs(String name){
        return maxClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> maxClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.maxClaimAmount(), true);
    }
    public MovingOrderRequest<T> sumClaimAmountOfClaimsRecords(){
        return sumClaimAmountOfClaimsRecordsAs("sumClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> sumClaimAmountOfClaimsRecordsAs(String name){
        return sumClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> sumClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.sumClaimAmount(), true);
    }
    public MovingOrderRequest<T> avgClaimAmountOfClaimsRecords(){
        return avgClaimAmountOfClaimsRecordsAs("avgClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> avgClaimAmountOfClaimsRecordsAs(String name){
        return avgClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> avgClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.avgClaimAmount(), true);
    }
    public MovingOrderRequest<T> standardDeviationClaimAmountOfClaimsRecords(){
        return standardDeviationClaimAmountOfClaimsRecordsAs("stdDevClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> standardDeviationClaimAmountOfClaimsRecordsAs(String name){
        return standardDeviationClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.standardDeviationClaimAmount(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationClaimAmountOfClaimsRecords(){
        return squareRootOfPopulationStandardDeviationClaimAmountOfClaimsRecordsAs("stdDevPopClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationClaimAmountOfClaimsRecordsAs(String name){
        return squareRootOfPopulationStandardDeviationClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.squareRootOfPopulationStandardDeviationClaimAmount(), true);
    }
    public MovingOrderRequest<T> sampleVarianceClaimAmountOfClaimsRecords(){
        return sampleVarianceClaimAmountOfClaimsRecordsAs("varSampClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> sampleVarianceClaimAmountOfClaimsRecordsAs(String name){
        return sampleVarianceClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.sampleVarianceClaimAmount(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceClaimAmountOfClaimsRecords(){
        return samplePopulationVarianceClaimAmountOfClaimsRecordsAs("varPopClaimAmountOfClaimsRecords");
    }

    public MovingOrderRequest<T> samplePopulationVarianceClaimAmountOfClaimsRecordsAs(String name){
        return samplePopulationVarianceClaimAmountOfClaimsRecordsAs(name, Q.claimsRecords().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceClaimAmountOfClaimsRecordsAs(String name, ClaimsRecordRequest subRequest){
        return statsFromClaimsRecordsAs(name, subRequest.samplePopulationVarianceClaimAmount(), true);
    }
    public MovingOrderRequest<T> minTotalValueOfCustomsDeclarations(){
        return minTotalValueOfCustomsDeclarationsAs("minTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> minTotalValueOfCustomsDeclarationsAs(String name){
        return minTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> minTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.minTotalValue(), true);
    }
    public MovingOrderRequest<T> maxTotalValueOfCustomsDeclarations(){
        return maxTotalValueOfCustomsDeclarationsAs("maxTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> maxTotalValueOfCustomsDeclarationsAs(String name){
        return maxTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> maxTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.maxTotalValue(), true);
    }
    public MovingOrderRequest<T> sumTotalValueOfCustomsDeclarations(){
        return sumTotalValueOfCustomsDeclarationsAs("sumTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> sumTotalValueOfCustomsDeclarationsAs(String name){
        return sumTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> sumTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.sumTotalValue(), true);
    }
    public MovingOrderRequest<T> avgTotalValueOfCustomsDeclarations(){
        return avgTotalValueOfCustomsDeclarationsAs("avgTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> avgTotalValueOfCustomsDeclarationsAs(String name){
        return avgTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> avgTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.avgTotalValue(), true);
    }
    public MovingOrderRequest<T> standardDeviationTotalValueOfCustomsDeclarations(){
        return standardDeviationTotalValueOfCustomsDeclarationsAs("stdDevTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> standardDeviationTotalValueOfCustomsDeclarationsAs(String name){
        return standardDeviationTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.standardDeviationTotalValue(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationTotalValueOfCustomsDeclarations(){
        return squareRootOfPopulationStandardDeviationTotalValueOfCustomsDeclarationsAs("stdDevPopTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationTotalValueOfCustomsDeclarationsAs(String name){
        return squareRootOfPopulationStandardDeviationTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.squareRootOfPopulationStandardDeviationTotalValue(), true);
    }
    public MovingOrderRequest<T> sampleVarianceTotalValueOfCustomsDeclarations(){
        return sampleVarianceTotalValueOfCustomsDeclarationsAs("varSampTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> sampleVarianceTotalValueOfCustomsDeclarationsAs(String name){
        return sampleVarianceTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.sampleVarianceTotalValue(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceTotalValueOfCustomsDeclarations(){
        return samplePopulationVarianceTotalValueOfCustomsDeclarationsAs("varPopTotalValueOfCustomsDeclarations");
    }

    public MovingOrderRequest<T> samplePopulationVarianceTotalValueOfCustomsDeclarationsAs(String name){
        return samplePopulationVarianceTotalValueOfCustomsDeclarationsAs(name, Q.customsDeclarations().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceTotalValueOfCustomsDeclarationsAs(String name, CustomsDeclarationRequest subRequest){
        return statsFromCustomsDeclarationsAs(name, subRequest.samplePopulationVarianceTotalValue(), true);
    }

   public MovingOrderRequest<T> facetByCustomerAs(String facetName, PrivateCustomerRequest customer){
       return facetByCustomerAs(facetName, customer, true);
   }

   public MovingOrderRequest<T> facetByCustomerAs(String facetName, PrivateCustomerRequest customer, boolean includeAllFacets){
       addFacet(facetName, MovingOrder.CUSTOMER_PROPERTY, customer, includeAllFacets);
       return this;
   }
   public MovingOrderRequest<T> facetByPickupAddressAs(String facetName, PickupAddressRequest pickupAddress){
       return facetByPickupAddressAs(facetName, pickupAddress, true);
   }

   public MovingOrderRequest<T> facetByPickupAddressAs(String facetName, PickupAddressRequest pickupAddress, boolean includeAllFacets){
       addFacet(facetName, MovingOrder.PICKUP_ADDRESS_PROPERTY, pickupAddress, includeAllFacets);
       return this;
   }
   public MovingOrderRequest<T> facetByDeliveryAddressAs(String facetName, PickupAddressRequest deliveryAddress){
       return facetByDeliveryAddressAs(facetName, deliveryAddress, true);
   }

   public MovingOrderRequest<T> facetByDeliveryAddressAs(String facetName, PickupAddressRequest deliveryAddress, boolean includeAllFacets){
       addFacet(facetName, MovingOrder.DELIVERY_ADDRESS_PROPERTY, deliveryAddress, includeAllFacets);
       return this;
   }


    /**
     * get topN records
     * @param topN  records number
     */
    public MovingOrderRequest<T> top(int topN) {
        super.top(topN);
        return this;
    }

    /**
     * get records from offset(inclusive) to offset+size(exclusive)
     * @param offset record offset
     * @param size records number
     */
    public MovingOrderRequest<T> offset(int offset, int size) {
        super.offset(offset, size);
        return this;
    }

    /**
     * retrieve all records
     */
    public MovingOrderRequest<T> unlimited() {
        super.unlimited();
        return this;
    }

    /**
     * get records of one page
     * @param pageNumber page number(1-based)
     * @param pageSize page size
     */
    public MovingOrderRequest<T> page(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return offset(offset, pageSize);
   }

    /**
     * get records of one page, default page size is 10
     * @param pageNumber page number(1-based)
     */
    public MovingOrderRequest<T> page(int pageNumber) {
        return page(pageNumber, 10);
   }
}