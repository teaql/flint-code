package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.Q;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemRequest;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanRequest;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceRequest;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressRequest;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerRequest;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotRequest;
import io.teaql.core.AggrFunction;
import io.teaql.core.BaseRequest;
import io.teaql.core.PropertyReference;
import io.teaql.core.SearchCriteria;
import io.teaql.core.SubQuerySearchCriteria;
import io.teaql.core.criteria.Operator;
import io.teaql.core.criteria.TwoOperatorCriteria;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        return selectId().selectOrderId().selectCustomerIdOnly().selectStatus().selectTotalWeight().selectTotalVolume().selectEstimatedCost().selectActualCost().selectPickupDate().selectDeliveryDate().selectSpecialInstructions().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public MovingOrderRequest<T> selectSelfFields(){
        return selectSelf();
    }

    public MovingOrderRequest<T> selectAll(){
        super.selectAll();
        return selectId().selectOrderId().selectCustomer().selectStatus().selectTotalWeight().selectTotalVolume().selectEstimatedCost().selectActualCost().selectPickupDate().selectDeliveryDate().selectSpecialInstructions().selectCreateTime().selectUpdateTime().selectVersion();
    }

    public MovingOrderRequest<T> selectChildren(){
        super.selectAny();
        selectDispatchPlanList().selectTimeSlotList().selectCargoItemList().selectPickupAddressList().selectInvoiceList();
        return selectId().selectOrderId().selectCustomer().selectStatus().selectTotalWeight().selectTotalVolume().selectEstimatedCost().selectActualCost().selectPickupDate().selectDeliveryDate().selectSpecialInstructions().selectCreateTime().selectUpdateTime().selectVersion();
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
    public MovingOrderRequest<T> selectOrderId(){
       selectProperty(MovingOrder.ORDER_ID_PROPERTY);
       return this;
    }

    /**
     * fill the orderId with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  orderId) to fetch orderId property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectOrderId(){
       unselectProperty(MovingOrder.ORDER_ID_PROPERTY);
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
    public MovingOrderRequest<T> selectPickupDate(){
       selectProperty(MovingOrder.PICKUP_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the pickupDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  pickupDate) to fetch pickupDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectPickupDate(){
       unselectProperty(MovingOrder.PICKUP_DATE_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectDeliveryDate(){
       selectProperty(MovingOrder.DELIVERY_DATE_PROPERTY);
       return this;
    }

    /**
     * fill the deliveryDate with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  deliveryDate) to fetch deliveryDate property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectDeliveryDate(){
       unselectProperty(MovingOrder.DELIVERY_DATE_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectSpecialInstructions(){
       selectProperty(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }

    /**
     * fill the specialInstructions with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  specialInstructions) to fetch specialInstructions property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectSpecialInstructions(){
       unselectProperty(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectCreateTime(){
       selectProperty(MovingOrder.CREATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the createTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  createTime) to fetch createTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectCreateTime(){
       unselectProperty(MovingOrder.CREATE_TIME_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> selectUpdateTime(){
       selectProperty(MovingOrder.UPDATE_TIME_PROPERTY);
       return this;
    }

    /**
     * fill the updateTime with customized rawSqlSegment, TEAQL uses ({rawSqlSegment} AS  updateTime) to fetch updateTime property.
     * @param rawSqlSegment  customized rawSqlSegment
     */




    public MovingOrderRequest<T> unselectUpdateTime(){
       unselectProperty(MovingOrder.UPDATE_TIME_PROPERTY);
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
    public MovingOrderRequest<T> selectTimeSlotList(){
       return selectTimeSlotListWith(Q.timeSlots().selectSelf());
    }

    public MovingOrderRequest<T> selectTimeSlotListWith(TimeSlotRequest timeSlotList){
       enhanceRelation(MovingOrder.TIME_SLOT_LIST_PROPERTY, timeSlotList);
       return this;
    }
    public MovingOrderRequest<T> selectCargoItemList(){
       return selectCargoItemListWith(Q.cargoItems().selectSelf());
    }

    public MovingOrderRequest<T> selectCargoItemListWith(CargoItemRequest cargoItemList){
       enhanceRelation(MovingOrder.CARGO_ITEM_LIST_PROPERTY, cargoItemList);
       return this;
    }
    public MovingOrderRequest<T> selectPickupAddressList(){
       return selectPickupAddressListWith(Q.pickupAddresses().selectSelf());
    }

    public MovingOrderRequest<T> selectPickupAddressListWith(PickupAddressRequest pickupAddressList){
       enhanceRelation(MovingOrder.PICKUP_ADDRESS_LIST_PROPERTY, pickupAddressList);
       return this;
    }
    public MovingOrderRequest<T> selectInvoiceList(){
       return selectInvoiceListWith(Q.invoices().selectSelf());
    }

    public MovingOrderRequest<T> selectInvoiceListWith(InvoiceRequest invoiceList){
       enhanceRelation(MovingOrder.INVOICE_LIST_PROPERTY, invoiceList);
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



    public MovingOrderRequest<T> filterByOrderId(String... orderId){
      if (orderId == null || orderId.length == 0) {
        throw new IllegalArgumentException("filterByOrderId parameter orderId cannot be empty");
      }
      return appendSearchCriteria(createOrderIdCriteria(Operator.EQUAL, (Object[])orderId));
    }

    public MovingOrderRequest<T> withOrderId(Operator operator, Object... values){
       return appendSearchCriteria(createOrderIdCriteria(operator, values));
    }

    public MovingOrderRequest<T> withOrderIdIsUnknown(){
       return withOrderId(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withOrderIdIsKnown(){
       return withOrderId(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createOrderIdCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.ORDER_ID_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withOrderIdGreaterThan(String orderId){
       return withOrderId(Operator.GREATER_THAN, orderId);
    }

    public MovingOrderRequest<T> withOrderIdGreaterThanOrEqualTo(String orderId){
       return withOrderId(Operator.GREATER_THAN_OR_EQUAL, orderId);
    }

    public MovingOrderRequest<T> withOrderIdLessThan(String orderId){
       return withOrderId(Operator.LESS_THAN, orderId);
    }

    public MovingOrderRequest<T> withOrderIdLessThanOrEqualTo(String orderId){
       return withOrderId(Operator.LESS_THAN_OR_EQUAL, orderId);
    }

    public MovingOrderRequest<T> withOrderIdBetween(String startOfOrderId, String endOfOrderId){
       return withOrderId(Operator.BETWEEN, startOfOrderId, endOfOrderId);
    }
    public MovingOrderRequest<T> withOrderIdStartingWith(String orderId){
       return withOrderId(Operator.BEGIN_WITH, orderId);
    }
    public MovingOrderRequest<T> withOrderIdContaining(String orderId){
       return withOrderId(Operator.CONTAIN, orderId);
    }

    public MovingOrderRequest<T> withOrderIdEndingWith(String orderId){
       return withOrderId(Operator.END_WITH, orderId);
    }

    public MovingOrderRequest<T> withOrderIdIs(String orderId){
       return withOrderId(Operator.EQUAL, orderId);
    }

    public MovingOrderRequest<T> withOrderIdSoundingLike(String orderId){
       return withOrderId(Operator.SOUNDS_LIKE, orderId);
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



    public MovingOrderRequest<T> filterByPickupDate(LocalDate... pickupDate){
      if (pickupDate == null || pickupDate.length == 0) {
        throw new IllegalArgumentException("filterByPickupDate parameter pickupDate cannot be empty");
      }
      return appendSearchCriteria(createPickupDateCriteria(Operator.EQUAL, (Object[])pickupDate));
    }

    public MovingOrderRequest<T> withPickupDate(Operator operator, Object... values){
       return appendSearchCriteria(createPickupDateCriteria(operator, values));
    }

    public MovingOrderRequest<T> withPickupDateIsUnknown(){
       return withPickupDate(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withPickupDateIsKnown(){
       return withPickupDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createPickupDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.PICKUP_DATE_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withPickupDateGreaterThan(LocalDate pickupDate){
       return withPickupDate(Operator.GREATER_THAN, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateGreaterThanOrEqualTo(LocalDate pickupDate){
       return withPickupDate(Operator.GREATER_THAN_OR_EQUAL, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateLessThan(LocalDate pickupDate){
       return withPickupDate(Operator.LESS_THAN, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateLessThanOrEqualTo(LocalDate pickupDate){
       return withPickupDate(Operator.LESS_THAN_OR_EQUAL, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateBetween(LocalDate startOfPickupDate, LocalDate endOfPickupDate){
       return withPickupDate(Operator.BETWEEN, startOfPickupDate, endOfPickupDate);
    }
    public MovingOrderRequest<T> withPickupDateBefore(LocalDate pickupDate){
       return withPickupDate(Operator.LESS_THAN, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateBefore(Date pickupDate){
       return withPickupDate(Operator.LESS_THAN, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateAfter(LocalDate pickupDate){
       return withPickupDate(Operator.GREATER_THAN, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateAfter(Date pickupDate){
       return withPickupDate(Operator.GREATER_THAN, pickupDate);
    }

    public MovingOrderRequest<T> withPickupDateBetween(Date startOfPickupDate, Date endOfPickupDate){
       return withPickupDate(Operator.BETWEEN, startOfPickupDate, endOfPickupDate);
    }




    public MovingOrderRequest<T> filterByDeliveryDate(LocalDate... deliveryDate){
      if (deliveryDate == null || deliveryDate.length == 0) {
        throw new IllegalArgumentException("filterByDeliveryDate parameter deliveryDate cannot be empty");
      }
      return appendSearchCriteria(createDeliveryDateCriteria(Operator.EQUAL, (Object[])deliveryDate));
    }

    public MovingOrderRequest<T> withDeliveryDate(Operator operator, Object... values){
       return appendSearchCriteria(createDeliveryDateCriteria(operator, values));
    }

    public MovingOrderRequest<T> withDeliveryDateIsUnknown(){
       return withDeliveryDate(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withDeliveryDateIsKnown(){
       return withDeliveryDate(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createDeliveryDateCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.DELIVERY_DATE_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withDeliveryDateGreaterThan(LocalDate deliveryDate){
       return withDeliveryDate(Operator.GREATER_THAN, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateGreaterThanOrEqualTo(LocalDate deliveryDate){
       return withDeliveryDate(Operator.GREATER_THAN_OR_EQUAL, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateLessThan(LocalDate deliveryDate){
       return withDeliveryDate(Operator.LESS_THAN, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateLessThanOrEqualTo(LocalDate deliveryDate){
       return withDeliveryDate(Operator.LESS_THAN_OR_EQUAL, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateBetween(LocalDate startOfDeliveryDate, LocalDate endOfDeliveryDate){
       return withDeliveryDate(Operator.BETWEEN, startOfDeliveryDate, endOfDeliveryDate);
    }
    public MovingOrderRequest<T> withDeliveryDateBefore(LocalDate deliveryDate){
       return withDeliveryDate(Operator.LESS_THAN, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateBefore(Date deliveryDate){
       return withDeliveryDate(Operator.LESS_THAN, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateAfter(LocalDate deliveryDate){
       return withDeliveryDate(Operator.GREATER_THAN, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateAfter(Date deliveryDate){
       return withDeliveryDate(Operator.GREATER_THAN, deliveryDate);
    }

    public MovingOrderRequest<T> withDeliveryDateBetween(Date startOfDeliveryDate, Date endOfDeliveryDate){
       return withDeliveryDate(Operator.BETWEEN, startOfDeliveryDate, endOfDeliveryDate);
    }




    public MovingOrderRequest<T> filterBySpecialInstructions(String... specialInstructions){
      if (specialInstructions == null || specialInstructions.length == 0) {
        throw new IllegalArgumentException("filterBySpecialInstructions parameter specialInstructions cannot be empty");
      }
      return appendSearchCriteria(createSpecialInstructionsCriteria(Operator.EQUAL, (Object[])specialInstructions));
    }

    public MovingOrderRequest<T> withSpecialInstructions(Operator operator, Object... values){
       return appendSearchCriteria(createSpecialInstructionsCriteria(operator, values));
    }

    public MovingOrderRequest<T> withSpecialInstructionsIsUnknown(){
       return withSpecialInstructions(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withSpecialInstructionsIsKnown(){
       return withSpecialInstructions(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createSpecialInstructionsCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withSpecialInstructionsGreaterThan(String specialInstructions){
       return withSpecialInstructions(Operator.GREATER_THAN, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsGreaterThanOrEqualTo(String specialInstructions){
       return withSpecialInstructions(Operator.GREATER_THAN_OR_EQUAL, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsLessThan(String specialInstructions){
       return withSpecialInstructions(Operator.LESS_THAN, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsLessThanOrEqualTo(String specialInstructions){
       return withSpecialInstructions(Operator.LESS_THAN_OR_EQUAL, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsBetween(String startOfSpecialInstructions, String endOfSpecialInstructions){
       return withSpecialInstructions(Operator.BETWEEN, startOfSpecialInstructions, endOfSpecialInstructions);
    }
    public MovingOrderRequest<T> withSpecialInstructionsStartingWith(String specialInstructions){
       return withSpecialInstructions(Operator.BEGIN_WITH, specialInstructions);
    }
    public MovingOrderRequest<T> withSpecialInstructionsContaining(String specialInstructions){
       return withSpecialInstructions(Operator.CONTAIN, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsEndingWith(String specialInstructions){
       return withSpecialInstructions(Operator.END_WITH, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsIs(String specialInstructions){
       return withSpecialInstructions(Operator.EQUAL, specialInstructions);
    }

    public MovingOrderRequest<T> withSpecialInstructionsSoundingLike(String specialInstructions){
       return withSpecialInstructions(Operator.SOUNDS_LIKE, specialInstructions);
    }



    public MovingOrderRequest<T> filterByCreateTime(LocalDateTime... createTime){
      if (createTime == null || createTime.length == 0) {
        throw new IllegalArgumentException("filterByCreateTime parameter createTime cannot be empty");
      }
      return appendSearchCriteria(createCreateTimeCriteria(Operator.EQUAL, (Object[])createTime));
    }

    public MovingOrderRequest<T> withCreateTime(Operator operator, Object... values){
       return appendSearchCriteria(createCreateTimeCriteria(operator, values));
    }

    public MovingOrderRequest<T> withCreateTimeIsUnknown(){
       return withCreateTime(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withCreateTimeIsKnown(){
       return withCreateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createCreateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.CREATE_TIME_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withCreateTimeGreaterThan(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeGreaterThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN_OR_EQUAL, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeLessThan(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeLessThanOrEqualTo(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN_OR_EQUAL, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeBetween(LocalDateTime startOfCreateTime, LocalDateTime endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }
    public MovingOrderRequest<T> withCreateTimeBefore(LocalDateTime createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeBefore(Date createTime){
       return withCreateTime(Operator.LESS_THAN, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeAfter(LocalDateTime createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeAfter(Date createTime){
       return withCreateTime(Operator.GREATER_THAN, createTime);
    }

    public MovingOrderRequest<T> withCreateTimeBetween(Date startOfCreateTime, Date endOfCreateTime){
       return withCreateTime(Operator.BETWEEN, startOfCreateTime, endOfCreateTime);
    }




    public MovingOrderRequest<T> filterByUpdateTime(LocalDateTime... updateTime){
      if (updateTime == null || updateTime.length == 0) {
        throw new IllegalArgumentException("filterByUpdateTime parameter updateTime cannot be empty");
      }
      return appendSearchCriteria(createUpdateTimeCriteria(Operator.EQUAL, (Object[])updateTime));
    }

    public MovingOrderRequest<T> withUpdateTime(Operator operator, Object... values){
       return appendSearchCriteria(createUpdateTimeCriteria(operator, values));
    }

    public MovingOrderRequest<T> withUpdateTimeIsUnknown(){
       return withUpdateTime(Operator.IS_NULL);
    }

    public MovingOrderRequest<T> withUpdateTimeIsKnown(){
       return withUpdateTime(Operator.IS_NOT_NULL);
    }

    public SearchCriteria createUpdateTimeCriteria(Operator operator, Object... values) {
        return createBasicSearchCriteria(MovingOrder.UPDATE_TIME_PROPERTY, operator, values);
    }

    public MovingOrderRequest<T> withUpdateTimeGreaterThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeGreaterThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN_OR_EQUAL, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeLessThan(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeLessThanOrEqualTo(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN_OR_EQUAL, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeBetween(LocalDateTime startOfUpdateTime, LocalDateTime endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
    }
    public MovingOrderRequest<T> withUpdateTimeBefore(LocalDateTime updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeBefore(Date updateTime){
       return withUpdateTime(Operator.LESS_THAN, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeAfter(LocalDateTime updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeAfter(Date updateTime){
       return withUpdateTime(Operator.GREATER_THAN, updateTime);
    }

    public MovingOrderRequest<T> withUpdateTimeBetween(Date startOfUpdateTime, Date endOfUpdateTime){
       return withUpdateTime(Operator.BETWEEN, startOfUpdateTime, endOfUpdateTime);
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
    public MovingOrderRequest<T> withTimeSlotListMatching(TimeSlotRequest timeSlotRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, timeSlotRequest, TimeSlot.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutTimeSlotListMatching(TimeSlotRequest timeSlotRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, timeSlotRequest, TimeSlot.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> haveTimeSlots(){
        return withTimeSlotListMatching(Q.timeSlots().unlimited());
    }

    public MovingOrderRequest<T> haveNoTimeSlots(){
        return withoutTimeSlotListMatching(Q.timeSlots().unlimited());
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
    public MovingOrderRequest<T> withPickupAddressListMatching(PickupAddressRequest pickupAddressRequest){
        return appendSearchCriteria(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, pickupAddressRequest, PickupAddress.MOVING_ORDER_PROPERTY));
    }

    public MovingOrderRequest<T> withoutPickupAddressListMatching(PickupAddressRequest pickupAddressRequest){
        return appendSearchCriteria(SearchCriteria.not(new SubQuerySearchCriteria(MovingOrder.ID_PROPERTY, pickupAddressRequest, PickupAddress.MOVING_ORDER_PROPERTY)));
    }

    public MovingOrderRequest<T> havePickupAddresses(){
        return withPickupAddressListMatching(Q.pickupAddresses().unlimited());
    }

    public MovingOrderRequest<T> haveNoPickupAddresses(){
        return withoutPickupAddressListMatching(Q.pickupAddresses().unlimited());
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












    public MovingOrderRequest<T> groupByDispatchPlansWithDetails(DispatchPlanRequest subRequest){
       aggregate(MovingOrder.DISPATCH_PLAN_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByTimeSlotsWithDetails(TimeSlotRequest subRequest){
       aggregate(MovingOrder.TIME_SLOT_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByCargoItemsWithDetails(CargoItemRequest subRequest){
       aggregate(MovingOrder.CARGO_ITEM_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByPickupAddressesWithDetails(PickupAddressRequest subRequest){
       aggregate(MovingOrder.PICKUP_ADDRESS_LIST_PROPERTY, subRequest);
       return this;
    }
    public MovingOrderRequest<T> groupByInvoicesWithDetails(InvoiceRequest subRequest){
       aggregate(MovingOrder.INVOICE_LIST_PROPERTY, subRequest);
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

    public MovingOrderRequest<T> groupByOrderId(){
       groupBy(MovingOrder.ORDER_ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByOrderIdAs(String retName){
       groupBy(retName, MovingOrder.ORDER_ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByOrderIdWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.ORDER_ID_PROPERTY, function);
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

    public MovingOrderRequest<T> groupByPickupDate(){
       groupBy(MovingOrder.PICKUP_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByPickupDateAs(String retName){
       groupBy(retName, MovingOrder.PICKUP_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByPickupDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.PICKUP_DATE_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByDeliveryDate(){
       groupBy(MovingOrder.DELIVERY_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByDeliveryDateAs(String retName){
       groupBy(retName, MovingOrder.DELIVERY_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByDeliveryDateWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.DELIVERY_DATE_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupBySpecialInstructions(){
       groupBy(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupBySpecialInstructionsAs(String retName){
       groupBy(retName, MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupBySpecialInstructionsWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByCreateTime(){
       groupBy(MovingOrder.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByCreateTimeAs(String retName){
       groupBy(retName, MovingOrder.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByCreateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.CREATE_TIME_PROPERTY, function);
       return this;
    }

    public MovingOrderRequest<T> groupByUpdateTime(){
       groupBy(MovingOrder.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByUpdateTimeAs(String retName){
       groupBy(retName, MovingOrder.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> groupByUpdateTimeWithFunction(String retName, AggrFunction function){
       groupBy(retName, MovingOrder.UPDATE_TIME_PROPERTY, function);
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

    public MovingOrderRequest<T> orderByOrderIdAscending(){
       addOrderByAscending(MovingOrder.ORDER_ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByOrderIdDescending(){
       addOrderByDescending(MovingOrder.ORDER_ID_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderByOrderIdAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingOrder.ORDER_ID_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByOrderIdDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingOrder.ORDER_ID_PROPERTY);
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

    public MovingOrderRequest<T> orderByPickupDateAscending(){
       addOrderByAscending(MovingOrder.PICKUP_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByPickupDateDescending(){
       addOrderByDescending(MovingOrder.PICKUP_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByDeliveryDateAscending(){
       addOrderByAscending(MovingOrder.DELIVERY_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByDeliveryDateDescending(){
       addOrderByDescending(MovingOrder.DELIVERY_DATE_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderBySpecialInstructionsAscending(){
       addOrderByAscending(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderBySpecialInstructionsDescending(){
       addOrderByDescending(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderBySpecialInstructionsAscendingUsingGBK(){
       addOrderByAscendingUsingGBK(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderBySpecialInstructionsDescendingUsingGBK(){
       addOrderByDescendingUsingGBK(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY);
       return this;
    }
    public MovingOrderRequest<T> orderByCreateTimeAscending(){
       addOrderByAscending(MovingOrder.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByCreateTimeDescending(){
       addOrderByDescending(MovingOrder.CREATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByUpdateTimeAscending(){
       addOrderByAscending(MovingOrder.UPDATE_TIME_PROPERTY);
       return this;
    }

    public MovingOrderRequest<T> orderByUpdateTimeDescending(){
       addOrderByDescending(MovingOrder.UPDATE_TIME_PROPERTY);
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
    public MovingOrderRequest<T> statsFromTimeSlotsAs(String name, TimeSlotRequest subRequest){
       return statsFromTimeSlotsAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromTimeSlotsAs(String name, TimeSlotRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(TimeSlot.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromTimeSlots(TimeSlotRequest subRequest){
       return statsFromTimeSlotsAs(REFINEMENTS, subRequest);
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
    public MovingOrderRequest<T> statsFromPickupAddressesAs(String name, PickupAddressRequest subRequest){
       return statsFromPickupAddressesAs(name, subRequest, false);
    }

    public MovingOrderRequest<T> statsFromPickupAddressesAs(String name, PickupAddressRequest subRequest, boolean singleResult){
       subRequest.setPartitionProperty(PickupAddress.MOVING_ORDER_PROPERTY);
       addAggregateDynamicProperty(name, subRequest, singleResult);
       return this;
    }

    public MovingOrderRequest<T> statsFromPickupAddresses(PickupAddressRequest subRequest){
       return statsFromPickupAddressesAs(REFINEMENTS, subRequest);
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
    public PrivateCustomerRequest rollUpToCustomer(){
       PrivateCustomerRequest customer = Q.privateCustomers().unlimited();
       this.withCustomerMatching(customer)
           .groupByCustomerWith(customer);
       return customer;
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
    public MovingOrderRequest<T> countTimeSlots(){
        return countTimeSlotsAs("Count");
    }

    public MovingOrderRequest<T> countTimeSlotsAs(String name){
        return countTimeSlotsWith(name, Q.timeSlots().unlimited());
    }

    public MovingOrderRequest<T> countTimeSlotsWith(String name, TimeSlotRequest subRequest){
        return statsFromTimeSlotsAs(name, subRequest.count(), true);
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
    public MovingOrderRequest<T> countPickupAddresses(){
        return countPickupAddressesAs("Count");
    }

    public MovingOrderRequest<T> countPickupAddressesAs(String name){
        return countPickupAddressesWith(name, Q.pickupAddresses().unlimited());
    }

    public MovingOrderRequest<T> countPickupAddressesWith(String name, PickupAddressRequest subRequest){
        return statsFromPickupAddressesAs(name, subRequest.count(), true);
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
    public MovingOrderRequest<T> minValueOfCargoItems(){
        return minValueOfCargoItemsAs("minValueOfCargoItems");
    }

    public MovingOrderRequest<T> minValueOfCargoItemsAs(String name){
        return minValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> minValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.minValue(), true);
    }
    public MovingOrderRequest<T> maxValueOfCargoItems(){
        return maxValueOfCargoItemsAs("maxValueOfCargoItems");
    }

    public MovingOrderRequest<T> maxValueOfCargoItemsAs(String name){
        return maxValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> maxValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.maxValue(), true);
    }
    public MovingOrderRequest<T> sumValueOfCargoItems(){
        return sumValueOfCargoItemsAs("sumValueOfCargoItems");
    }

    public MovingOrderRequest<T> sumValueOfCargoItemsAs(String name){
        return sumValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> sumValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.sumValue(), true);
    }
    public MovingOrderRequest<T> avgValueOfCargoItems(){
        return avgValueOfCargoItemsAs("avgValueOfCargoItems");
    }

    public MovingOrderRequest<T> avgValueOfCargoItemsAs(String name){
        return avgValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> avgValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.avgValue(), true);
    }
    public MovingOrderRequest<T> standardDeviationValueOfCargoItems(){
        return standardDeviationValueOfCargoItemsAs("stdDevValueOfCargoItems");
    }

    public MovingOrderRequest<T> standardDeviationValueOfCargoItemsAs(String name){
        return standardDeviationValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> standardDeviationValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.standardDeviationValue(), true);
    }
    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationValueOfCargoItems(){
        return squareRootOfPopulationStandardDeviationValueOfCargoItemsAs("stdDevPopValueOfCargoItems");
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationValueOfCargoItemsAs(String name){
        return squareRootOfPopulationStandardDeviationValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> squareRootOfPopulationStandardDeviationValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.squareRootOfPopulationStandardDeviationValue(), true);
    }
    public MovingOrderRequest<T> sampleVarianceValueOfCargoItems(){
        return sampleVarianceValueOfCargoItemsAs("varSampValueOfCargoItems");
    }

    public MovingOrderRequest<T> sampleVarianceValueOfCargoItemsAs(String name){
        return sampleVarianceValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> sampleVarianceValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.sampleVarianceValue(), true);
    }
    public MovingOrderRequest<T> samplePopulationVarianceValueOfCargoItems(){
        return samplePopulationVarianceValueOfCargoItemsAs("varPopValueOfCargoItems");
    }

    public MovingOrderRequest<T> samplePopulationVarianceValueOfCargoItemsAs(String name){
        return samplePopulationVarianceValueOfCargoItemsAs(name, Q.cargoItems().unlimited());
    }

    public MovingOrderRequest<T> samplePopulationVarianceValueOfCargoItemsAs(String name, CargoItemRequest subRequest){
        return statsFromCargoItemsAs(name, subRequest.samplePopulationVarianceValue(), true);
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

   public MovingOrderRequest<T> facetByCustomerAs(String facetName, PrivateCustomerRequest customer){
       return facetByCustomerAs(facetName, customer, true);
   }

   public MovingOrderRequest<T> facetByCustomerAs(String facetName, PrivateCustomerRequest customer, boolean includeAllFacets){
       addFacet(facetName, MovingOrder.CUSTOMER_PROPERTY, customer, includeAllFacets);
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