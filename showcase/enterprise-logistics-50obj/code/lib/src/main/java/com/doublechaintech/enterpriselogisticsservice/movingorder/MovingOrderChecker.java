package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemChecker;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordChecker;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationChecker;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanChecker;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewChecker;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovingOrderChecker implements Checker<MovingOrder>{

    public String type(){
        return MovingOrder.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
        if(needCheck(_ctx, movingOrder)){
            markAsChecked(_ctx, movingOrder);
            doCheck(_ctx, movingOrder, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, MovingOrder movingOrder, ObjectLocation _parentLocation){
      if((movingOrder == null)){
         return;
      }
      if(movingOrder.newItem()){
        if(movingOrder.getCreatedTime() == null){
           movingOrder.updateCreatedTime(java.time.LocalDateTime.now());
        }if(movingOrder.getUpdatedTime() == null){
           movingOrder.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(movingOrder.updateItem()){
        movingOrder.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkOrderNumber(_ctx, movingOrder.getProperty(MovingOrder.ORDER_NUMBER_PROPERTY), newLocation(_parentLocation, MovingOrder.ORDER_NUMBER_PROPERTY));
      checkStatus(_ctx, movingOrder.getProperty(MovingOrder.STATUS_PROPERTY), newLocation(_parentLocation, MovingOrder.STATUS_PROPERTY));
      checkCustomer(_ctx, movingOrder.getProperty(MovingOrder.CUSTOMER_PROPERTY), newLocation(_parentLocation, MovingOrder.CUSTOMER_PROPERTY));
      checkPickupAddress(_ctx, movingOrder.getProperty(MovingOrder.PICKUP_ADDRESS_PROPERTY), newLocation(_parentLocation, MovingOrder.PICKUP_ADDRESS_PROPERTY));
      checkDeliveryAddress(_ctx, movingOrder.getProperty(MovingOrder.DELIVERY_ADDRESS_PROPERTY), newLocation(_parentLocation, MovingOrder.DELIVERY_ADDRESS_PROPERTY));
      checkTotalWeight(_ctx, movingOrder.getProperty(MovingOrder.TOTAL_WEIGHT_PROPERTY), newLocation(_parentLocation, MovingOrder.TOTAL_WEIGHT_PROPERTY));
      checkTotalVolume(_ctx, movingOrder.getProperty(MovingOrder.TOTAL_VOLUME_PROPERTY), newLocation(_parentLocation, MovingOrder.TOTAL_VOLUME_PROPERTY));
      checkEstimatedCost(_ctx, movingOrder.getProperty(MovingOrder.ESTIMATED_COST_PROPERTY), newLocation(_parentLocation, MovingOrder.ESTIMATED_COST_PROPERTY));
      checkActualCost(_ctx, movingOrder.getProperty(MovingOrder.ACTUAL_COST_PROPERTY), newLocation(_parentLocation, MovingOrder.ACTUAL_COST_PROPERTY));
      checkCreatedTime(_ctx, movingOrder.getProperty(MovingOrder.CREATED_TIME_PROPERTY), newLocation(_parentLocation, MovingOrder.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, movingOrder.getProperty(MovingOrder.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, MovingOrder.UPDATED_TIME_PROPERTY));
      for(int i = 0; movingOrder.getDispatchPlanList() != null && i < movingOrder.getDispatchPlanList().size(); i++){
         DispatchPlan dispatchPlan = movingOrder.getDispatchPlanList().get(i);
         new DispatchPlanChecker().checkAndFix(_ctx, dispatchPlan, newLocation(_parentLocation, MovingOrder.DISPATCH_PLAN_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getCargoItemList() != null && i < movingOrder.getCargoItemList().size(); i++){
         CargoItem cargoItem = movingOrder.getCargoItemList().get(i);
         new CargoItemChecker().checkAndFix(_ctx, cargoItem, newLocation(_parentLocation, MovingOrder.CARGO_ITEM_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getFeedbackReviewList() != null && i < movingOrder.getFeedbackReviewList().size(); i++){
         FeedbackReview feedbackReview = movingOrder.getFeedbackReviewList().get(i);
         new FeedbackReviewChecker().checkAndFix(_ctx, feedbackReview, newLocation(_parentLocation, MovingOrder.FEEDBACK_REVIEW_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getInvoiceList() != null && i < movingOrder.getInvoiceList().size(); i++){
         Invoice invoice = movingOrder.getInvoiceList().get(i);
         new InvoiceChecker().checkAndFix(_ctx, invoice, newLocation(_parentLocation, MovingOrder.INVOICE_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getClaimsRecordList() != null && i < movingOrder.getClaimsRecordList().size(); i++){
         ClaimsRecord claimsRecord = movingOrder.getClaimsRecordList().get(i);
         new ClaimsRecordChecker().checkAndFix(_ctx, claimsRecord, newLocation(_parentLocation, MovingOrder.CLAIMS_RECORD_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getCustomsDeclarationList() != null && i < movingOrder.getCustomsDeclarationList().size(); i++){
         CustomsDeclaration customsDeclaration = movingOrder.getCustomsDeclarationList().get(i);
         new CustomsDeclarationChecker().checkAndFix(_ctx, customsDeclaration, newLocation(_parentLocation, MovingOrder.CUSTOMS_DECLARATION_LIST_PROPERTY, i));
      }
    }

    public void checkOrderNumber(UserContext _ctx, String orderNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, orderNumber);
    if((orderNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, orderNumber);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkCustomer(UserContext _ctx, PrivateCustomer customer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customer);
    if((customer == null)){
        return;
    }
    new PrivateCustomerChecker().checkAndFix(_ctx, customer, _parentLocation);
    }
    public void checkPickupAddress(UserContext _ctx, PickupAddress pickupAddress, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, pickupAddress);
    if((pickupAddress == null)){
        return;
    }
    new PickupAddressChecker().checkAndFix(_ctx, pickupAddress, _parentLocation);
    }
    public void checkDeliveryAddress(UserContext _ctx, PickupAddress deliveryAddress, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, deliveryAddress);
    if((deliveryAddress == null)){
        return;
    }
    new PickupAddressChecker().checkAndFix(_ctx, deliveryAddress, _parentLocation);
    }
    public void checkTotalWeight(UserContext _ctx, BigDecimal totalWeight, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalWeight);
    if((totalWeight == null)){
        return;
    }
    }
    public void checkTotalVolume(UserContext _ctx, BigDecimal totalVolume, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalVolume);
    if((totalVolume == null)){
        return;
    }
    }
    public void checkEstimatedCost(UserContext _ctx, BigDecimal estimatedCost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, estimatedCost);
    if((estimatedCost == null)){
        return;
    }
    }
    public void checkActualCost(UserContext _ctx, BigDecimal actualCost, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, actualCost);
    if((actualCost == null)){
        return;
    }
    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}