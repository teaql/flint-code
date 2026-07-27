package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemListExpression;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanListExpression;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceListExpression;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressListExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlotListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class MovingOrderExpression<T, E, U extends MovingOrder> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public MovingOrderExpression(Expression<T, U> expression){
        super(expression);
    }

    public MovingOrderExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public MovingOrderExpression<T, U, U> updateId(Long id){
        return new MovingOrderExpression(this, $it -> {((MovingOrder)$it).__internalSet("id", id); return this;});
     }

     public MovingOrderExpression<T, U, U> save(UserContext userContext){
        return new MovingOrderExpression(this, $it -> ((MovingOrder)$it).auditAs("Saved by Expression").save(userContext));
     }

     public MovingOrderExpression<T, U, U> save(String intent, UserContext userContext){
        return new MovingOrderExpression(this, $it -> ((MovingOrder)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getOrderId(){
       return apply(MovingOrder::getOrderId);
    }
    public MovingOrderExpression<T, U, U> updateOrderId(String orderId){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateOrderId(orderId));
    }

    public PrivateCustomerExpression<T, U, PrivateCustomer> getCustomer(){
       return new PrivateCustomerExpression(this, $it ->  ((MovingOrder)$it).getCustomer());
    }

    public MovingOrderExpression<T, U, U> updateCustomer(PrivateCustomer customer){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateCustomer(customer));
    }

    public Expression<T, String> getStatus(){
       return apply(MovingOrder::getStatus);
    }
    public MovingOrderExpression<T, U, U> updateStatus(String status){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateStatus(status));
    }

    public Expression<T, BigDecimal> getTotalWeight(){
       return apply(MovingOrder::getTotalWeight);
    }
    public MovingOrderExpression<T, U, U> updateTotalWeight(BigDecimal totalWeight){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateTotalWeight(totalWeight));
    }

    public Expression<T, BigDecimal> getTotalVolume(){
       return apply(MovingOrder::getTotalVolume);
    }
    public MovingOrderExpression<T, U, U> updateTotalVolume(BigDecimal totalVolume){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateTotalVolume(totalVolume));
    }

    public Expression<T, BigDecimal> getEstimatedCost(){
       return apply(MovingOrder::getEstimatedCost);
    }
    public MovingOrderExpression<T, U, U> updateEstimatedCost(BigDecimal estimatedCost){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateEstimatedCost(estimatedCost));
    }

    public Expression<T, BigDecimal> getActualCost(){
       return apply(MovingOrder::getActualCost);
    }
    public MovingOrderExpression<T, U, U> updateActualCost(BigDecimal actualCost){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateActualCost(actualCost));
    }

    public Expression<T, LocalDate> getPickupDate(){
       return apply(MovingOrder::getPickupDate);
    }
    public MovingOrderExpression<T, U, U> updatePickupDate(LocalDate pickupDate){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updatePickupDate(pickupDate));
    }

    public Expression<T, LocalDate> getDeliveryDate(){
       return apply(MovingOrder::getDeliveryDate);
    }
    public MovingOrderExpression<T, U, U> updateDeliveryDate(LocalDate deliveryDate){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateDeliveryDate(deliveryDate));
    }

    public Expression<T, String> getSpecialInstructions(){
       return apply(MovingOrder::getSpecialInstructions);
    }
    public MovingOrderExpression<T, U, U> updateSpecialInstructions(String specialInstructions){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateSpecialInstructions(specialInstructions));
    }

    public Expression<T, LocalDateTime> getCreateTime(){
       return apply(MovingOrder::getCreateTime);
    }
    public MovingOrderExpression<T, U, U> updateCreateTime(LocalDateTime createTime){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateCreateTime(createTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(MovingOrder::getUpdateTime);
    }
    public MovingOrderExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateUpdateTime(updateTime));
    }

    public DispatchPlanListExpression<T, U, DispatchPlan> getDispatchPlanList(){
        return new DispatchPlanListExpression(this, $it ->  ((MovingOrder)$it).getDispatchPlanList());
    }
    public TimeSlotListExpression<T, U, TimeSlot> getTimeSlotList(){
        return new TimeSlotListExpression(this, $it ->  ((MovingOrder)$it).getTimeSlotList());
    }
    public CargoItemListExpression<T, U, CargoItem> getCargoItemList(){
        return new CargoItemListExpression(this, $it ->  ((MovingOrder)$it).getCargoItemList());
    }
    public PickupAddressListExpression<T, U, PickupAddress> getPickupAddressList(){
        return new PickupAddressListExpression(this, $it ->  ((MovingOrder)$it).getPickupAddressList());
    }
    public InvoiceListExpression<T, U, Invoice> getInvoiceList(){
        return new InvoiceListExpression(this, $it ->  ((MovingOrder)$it).getInvoiceList());
    }
    public MovingOrderExpression<T, U, U> addDispatchPlan(DispatchPlan dispatchPlan){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addDispatchPlan(dispatchPlan));
    }
    public MovingOrderExpression<T, U, U> addTimeSlot(TimeSlot timeSlot){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addTimeSlot(timeSlot));
    }
    public MovingOrderExpression<T, U, U> addCargoItem(CargoItem cargoItem){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addCargoItem(cargoItem));
    }
    public MovingOrderExpression<T, U, U> addPickupAddress(PickupAddress pickupAddress){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addPickupAddress(pickupAddress));
    }
    public MovingOrderExpression<T, U, U> addInvoice(Invoice invoice){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addInvoice(invoice));
    }
}