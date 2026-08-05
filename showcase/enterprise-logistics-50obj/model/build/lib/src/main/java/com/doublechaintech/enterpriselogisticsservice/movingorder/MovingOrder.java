package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.timeslot.TimeSlot;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class MovingOrder extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "MovingOrder";

    public static final String ORDER_ID_PROPERTY = "orderId";
    public static final String CUSTOMER_PROPERTY = "customer";
    public static final String STATUS_PROPERTY = "status";
    public static final String TOTAL_WEIGHT_PROPERTY = "totalWeight";
    public static final String TOTAL_VOLUME_PROPERTY = "totalVolume";
    public static final String ESTIMATED_COST_PROPERTY = "estimatedCost";
    public static final String ACTUAL_COST_PROPERTY = "actualCost";
    public static final String PICKUP_DATE_PROPERTY = "pickupDate";
    public static final String DELIVERY_DATE_PROPERTY = "deliveryDate";
    public static final String SPECIAL_INSTRUCTIONS_PROPERTY = "specialInstructions";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    public static final String UPDATE_TIME_PROPERTY = "updateTime";
    public static final String DISPATCH_PLAN_LIST_PROPERTY = "dispatchPlanList";
    public static final String TIME_SLOT_LIST_PROPERTY = "timeSlotList";
    public static final String CARGO_ITEM_LIST_PROPERTY = "cargoItemList";
    public static final String PICKUP_ADDRESS_LIST_PROPERTY = "pickupAddressList";
    public static final String INVOICE_LIST_PROPERTY = "invoiceList";
    private String orderId;
    private PrivateCustomer customer;
    private String status;
    private BigDecimal totalWeight;
    private BigDecimal totalVolume;
    private BigDecimal estimatedCost;
    private BigDecimal actualCost;
    private LocalDate pickupDate;
    private LocalDate deliveryDate;
    private String specialInstructions;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private SmartList<DispatchPlan> dispatchPlanList;
    private SmartList<TimeSlot> timeSlotList;
    private SmartList<CargoItem> cargoItemList;
    private SmartList<PickupAddress> pickupAddressList;
    private SmartList<Invoice> invoiceList;

    public String getOrderId(){
        return this.orderId;
    }
    public PrivateCustomer getCustomer(){
        return this.customer;
    }
    public String getStatus(){
        return this.status;
    }
    public BigDecimal getTotalWeight(){
        return this.totalWeight;
    }
    public BigDecimal getTotalVolume(){
        return this.totalVolume;
    }
    public BigDecimal getEstimatedCost(){
        return this.estimatedCost;
    }
    public BigDecimal getActualCost(){
        return this.actualCost;
    }
    public LocalDate getPickupDate(){
        return this.pickupDate;
    }
    public LocalDate getDeliveryDate(){
        return this.deliveryDate;
    }
    public String getSpecialInstructions(){
        return this.specialInstructions;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    public SmartList<DispatchPlan> getDispatchPlanList(){
        return this.dispatchPlanList;
    }
    public SmartList<TimeSlot> getTimeSlotList(){
        return this.timeSlotList;
    }
    public SmartList<CargoItem> getCargoItemList(){
        return this.cargoItemList;
    }
    public SmartList<PickupAddress> getPickupAddressList(){
        return this.pickupAddressList;
    }
    public SmartList<Invoice> getInvoiceList(){
        return this.invoiceList;
    }
    public MovingOrder updateOrderId(String orderId){
        orderId = (orderId == null ? null : orderId.trim());
        if(Objects.equals(this.orderId, orderId)){
            return this;
        }
        handleUpdate(ORDER_ID_PROPERTY, getOrderId(), orderId);
        this.orderId = orderId;
        return this;
    }
    public MovingOrder updateCustomer(PrivateCustomer customer){
        if(Objects.equals(this.customer, customer)){
            return this;
        }
        handleUpdate(CUSTOMER_PROPERTY, getCustomer(), customer);
        this.customer = customer;
        return this;
    }
    public MovingOrder updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public MovingOrder updateTotalWeight(BigDecimal totalWeight){
        if(Objects.equals(this.totalWeight, totalWeight)){
            return this;
        }
        handleUpdate(TOTAL_WEIGHT_PROPERTY, getTotalWeight(), totalWeight);
        this.totalWeight = totalWeight;
        return this;
    }
    public MovingOrder updateTotalVolume(BigDecimal totalVolume){
        if(Objects.equals(this.totalVolume, totalVolume)){
            return this;
        }
        handleUpdate(TOTAL_VOLUME_PROPERTY, getTotalVolume(), totalVolume);
        this.totalVolume = totalVolume;
        return this;
    }
    public MovingOrder updateEstimatedCost(BigDecimal estimatedCost){
        if(Objects.equals(this.estimatedCost, estimatedCost)){
            return this;
        }
        handleUpdate(ESTIMATED_COST_PROPERTY, getEstimatedCost(), estimatedCost);
        this.estimatedCost = estimatedCost;
        return this;
    }
    public MovingOrder updateActualCost(BigDecimal actualCost){
        if(Objects.equals(this.actualCost, actualCost)){
            return this;
        }
        handleUpdate(ACTUAL_COST_PROPERTY, getActualCost(), actualCost);
        this.actualCost = actualCost;
        return this;
    }
    public MovingOrder updatePickupDate(LocalDate pickupDate){
        if(Objects.equals(this.pickupDate, pickupDate)){
            return this;
        }
        handleUpdate(PICKUP_DATE_PROPERTY, getPickupDate(), pickupDate);
        this.pickupDate = pickupDate;
        return this;
    }
    public MovingOrder updateDeliveryDate(LocalDate deliveryDate){
        if(Objects.equals(this.deliveryDate, deliveryDate)){
            return this;
        }
        handleUpdate(DELIVERY_DATE_PROPERTY, getDeliveryDate(), deliveryDate);
        this.deliveryDate = deliveryDate;
        return this;
    }
    public MovingOrder updateSpecialInstructions(String specialInstructions){
        specialInstructions = (specialInstructions == null ? null : specialInstructions.trim());
        if(Objects.equals(this.specialInstructions, specialInstructions)){
            return this;
        }
        handleUpdate(SPECIAL_INSTRUCTIONS_PROPERTY, getSpecialInstructions(), specialInstructions);
        this.specialInstructions = specialInstructions;
        return this;
    }
    public MovingOrder updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }
    public MovingOrder updateUpdateTime(LocalDateTime updateTime){
        if(Objects.equals(this.updateTime, updateTime)){
            return this;
        }
        handleUpdate(UPDATE_TIME_PROPERTY, getUpdateTime(), updateTime);
        this.updateTime = updateTime;
        return this;
    }
    public MovingOrder addDispatchPlan(DispatchPlan dispatchPlan){
        if (dispatchPlan == null){
            return this;
        }

        if(null == this.dispatchPlanList){
            this.dispatchPlanList = new SmartList<>();
        }

        this.dispatchPlanList.add(dispatchPlan);
        dispatchPlan.cacheRelation(DispatchPlan.MOVING_ORDER_PROPERTY, this);
        return this;
    }
    public MovingOrder addTimeSlot(TimeSlot timeSlot){
        if (timeSlot == null){
            return this;
        }

        if(null == this.timeSlotList){
            this.timeSlotList = new SmartList<>();
        }

        this.timeSlotList.add(timeSlot);
        timeSlot.cacheRelation(TimeSlot.MOVING_ORDER_PROPERTY, this);
        return this;
    }
    public MovingOrder addCargoItem(CargoItem cargoItem){
        if (cargoItem == null){
            return this;
        }

        if(null == this.cargoItemList){
            this.cargoItemList = new SmartList<>();
        }

        this.cargoItemList.add(cargoItem);
        cargoItem.cacheRelation(CargoItem.MOVING_ORDER_PROPERTY, this);
        return this;
    }
    public MovingOrder addPickupAddress(PickupAddress pickupAddress){
        if (pickupAddress == null){
            return this;
        }

        if(null == this.pickupAddressList){
            this.pickupAddressList = new SmartList<>();
        }

        this.pickupAddressList.add(pickupAddress);
        pickupAddress.cacheRelation(PickupAddress.MOVING_ORDER_PROPERTY, this);
        return this;
    }
    public MovingOrder addInvoice(Invoice invoice){
        if (invoice == null){
            return this;
        }

        if(null == this.invoiceList){
            this.invoiceList = new SmartList<>();
        }

        this.invoiceList.add(invoice);
        invoice.cacheRelation(Invoice.MOVING_ORDER_PROPERTY, this);
        return this;
    }

    public static MovingOrder refer(Long id){
        MovingOrder refer = new MovingOrder();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public MovingOrder comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<MovingOrder> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "orderId": this.orderId = (value == null ? null : ((String)value).trim()); break;

            case "customer": this.customer = (PrivateCustomer) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "totalWeight": this.totalWeight = (BigDecimal) value; break;

            case "totalVolume": this.totalVolume = (BigDecimal) value; break;

            case "estimatedCost": this.estimatedCost = (BigDecimal) value; break;

            case "actualCost": this.actualCost = (BigDecimal) value; break;

            case "pickupDate": this.pickupDate = (LocalDate) value; break;

            case "deliveryDate": this.deliveryDate = (LocalDate) value; break;

            case "specialInstructions": this.specialInstructions = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            case "updateTime": this.updateTime = (LocalDateTime) value; break;

            case "dispatchPlanList": this.dispatchPlanList = (SmartList<DispatchPlan>) value; break;
            case "timeSlotList": this.timeSlotList = (SmartList<TimeSlot>) value; break;
            case "cargoItemList": this.cargoItemList = (SmartList<CargoItem>) value; break;
            case "pickupAddressList": this.pickupAddressList = (SmartList<PickupAddress>) value; break;
            case "invoiceList": this.invoiceList = (SmartList<Invoice>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "orderId": return this.orderId;
            case "customer": return this.customer;
            case "status": return this.status;
            case "totalWeight": return this.totalWeight;
            case "totalVolume": return this.totalVolume;
            case "estimatedCost": return this.estimatedCost;
            case "actualCost": return this.actualCost;
            case "pickupDate": return this.pickupDate;
            case "deliveryDate": return this.deliveryDate;
            case "specialInstructions": return this.specialInstructions;
            case "createTime": return this.createTime;
            case "updateTime": return this.updateTime;
            case "dispatchPlanList": return this.dispatchPlanList;
            case "timeSlotList": return this.timeSlotList;
            case "cargoItemList": return this.cargoItemList;
            case "pickupAddressList": return this.pickupAddressList;
            case "invoiceList": return this.invoiceList;
            default: return super.__internalGet(property);
        }
    }

}