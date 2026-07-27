package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemChecker;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanChecker;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceChecker;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressChecker;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerChecker;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        if(movingOrder.getCreateTime() == null){
           movingOrder.updateCreateTime(java.time.LocalDateTime.now());
        }if(movingOrder.getUpdateTime() == null){
           movingOrder.updateUpdateTime(java.time.LocalDateTime.now());
        }
      }else if(movingOrder.updateItem()){
        movingOrder.updateUpdateTime(java.time.LocalDateTime.now());
      }
      checkOrderId(_ctx, movingOrder.getProperty(MovingOrder.ORDER_ID_PROPERTY), newLocation(_parentLocation, MovingOrder.ORDER_ID_PROPERTY));
      checkCustomer(_ctx, movingOrder.getProperty(MovingOrder.CUSTOMER_PROPERTY), newLocation(_parentLocation, MovingOrder.CUSTOMER_PROPERTY));
      checkStatus(_ctx, movingOrder.getProperty(MovingOrder.STATUS_PROPERTY), newLocation(_parentLocation, MovingOrder.STATUS_PROPERTY));
      checkTotalWeight(_ctx, movingOrder.getProperty(MovingOrder.TOTAL_WEIGHT_PROPERTY), newLocation(_parentLocation, MovingOrder.TOTAL_WEIGHT_PROPERTY));
      checkTotalVolume(_ctx, movingOrder.getProperty(MovingOrder.TOTAL_VOLUME_PROPERTY), newLocation(_parentLocation, MovingOrder.TOTAL_VOLUME_PROPERTY));
      checkEstimatedCost(_ctx, movingOrder.getProperty(MovingOrder.ESTIMATED_COST_PROPERTY), newLocation(_parentLocation, MovingOrder.ESTIMATED_COST_PROPERTY));
      checkActualCost(_ctx, movingOrder.getProperty(MovingOrder.ACTUAL_COST_PROPERTY), newLocation(_parentLocation, MovingOrder.ACTUAL_COST_PROPERTY));
      checkPickupDate(_ctx, movingOrder.getProperty(MovingOrder.PICKUP_DATE_PROPERTY), newLocation(_parentLocation, MovingOrder.PICKUP_DATE_PROPERTY));
      checkDeliveryDate(_ctx, movingOrder.getProperty(MovingOrder.DELIVERY_DATE_PROPERTY), newLocation(_parentLocation, MovingOrder.DELIVERY_DATE_PROPERTY));
      checkSpecialInstructions(_ctx, movingOrder.getProperty(MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY), newLocation(_parentLocation, MovingOrder.SPECIAL_INSTRUCTIONS_PROPERTY));
      checkCreateTime(_ctx, movingOrder.getProperty(MovingOrder.CREATE_TIME_PROPERTY), newLocation(_parentLocation, MovingOrder.CREATE_TIME_PROPERTY));
      checkUpdateTime(_ctx, movingOrder.getProperty(MovingOrder.UPDATE_TIME_PROPERTY), newLocation(_parentLocation, MovingOrder.UPDATE_TIME_PROPERTY));
      for(int i = 0; movingOrder.getDispatchPlanList() != null && i < movingOrder.getDispatchPlanList().size(); i++){
         DispatchPlan dispatchPlan = movingOrder.getDispatchPlanList().get(i);
         new DispatchPlanChecker().checkAndFix(_ctx, dispatchPlan, newLocation(_parentLocation, MovingOrder.DISPATCH_PLAN_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getTimeSlotList() != null && i < movingOrder.getTimeSlotList().size(); i++){
         TimeSlot timeSlot = movingOrder.getTimeSlotList().get(i);
         new TimeSlotChecker().checkAndFix(_ctx, timeSlot, newLocation(_parentLocation, MovingOrder.TIME_SLOT_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getCargoItemList() != null && i < movingOrder.getCargoItemList().size(); i++){
         CargoItem cargoItem = movingOrder.getCargoItemList().get(i);
         new CargoItemChecker().checkAndFix(_ctx, cargoItem, newLocation(_parentLocation, MovingOrder.CARGO_ITEM_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getPickupAddressList() != null && i < movingOrder.getPickupAddressList().size(); i++){
         PickupAddress pickupAddress = movingOrder.getPickupAddressList().get(i);
         new PickupAddressChecker().checkAndFix(_ctx, pickupAddress, newLocation(_parentLocation, MovingOrder.PICKUP_ADDRESS_LIST_PROPERTY, i));
      }
      for(int i = 0; movingOrder.getInvoiceList() != null && i < movingOrder.getInvoiceList().size(); i++){
         Invoice invoice = movingOrder.getInvoiceList().get(i);
         new InvoiceChecker().checkAndFix(_ctx, invoice, newLocation(_parentLocation, MovingOrder.INVOICE_LIST_PROPERTY, i));
      }
    }

    public void checkOrderId(UserContext _ctx, String orderId, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, orderId);
    if((orderId == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, orderId);

    }
    public void checkCustomer(UserContext _ctx, PrivateCustomer customer, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, customer);
    if((customer == null)){
        return;
    }
    new PrivateCustomerChecker().checkAndFix(_ctx, customer, _parentLocation);
    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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
    public void checkPickupDate(UserContext _ctx, LocalDate pickupDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, pickupDate);
    if((pickupDate == null)){
        return;
    }
    }
    public void checkDeliveryDate(UserContext _ctx, LocalDate deliveryDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, deliveryDate);
    if((deliveryDate == null)){
        return;
    }
    }
    public void checkSpecialInstructions(UserContext _ctx, String specialInstructions, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, specialInstructions);
    if((specialInstructions == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, specialInstructions);

    }
    public void checkCreateTime(UserContext _ctx, LocalDateTime createTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createTime);
    if((createTime == null)){
        return;
    }
    }
    public void checkUpdateTime(UserContext _ctx, LocalDateTime updateTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updateTime);
    if((updateTime == null)){
        return;
    }
    }
}