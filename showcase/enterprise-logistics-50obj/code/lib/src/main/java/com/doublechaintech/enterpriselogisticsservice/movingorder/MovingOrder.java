package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
import java.math.BigDecimal;
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

    public static final String ORDER_NUMBER_PROPERTY = "orderNumber";
    public static final String STATUS_PROPERTY = "status";
    public static final String CUSTOMER_PROPERTY = "customer";
    public static final String PICKUP_ADDRESS_PROPERTY = "pickupAddress";
    public static final String DELIVERY_ADDRESS_PROPERTY = "deliveryAddress";
    public static final String TOTAL_WEIGHT_PROPERTY = "totalWeight";
    public static final String TOTAL_VOLUME_PROPERTY = "totalVolume";
    public static final String ESTIMATED_COST_PROPERTY = "estimatedCost";
    public static final String ACTUAL_COST_PROPERTY = "actualCost";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    public static final String DISPATCH_PLAN_LIST_PROPERTY = "dispatchPlanList";
    public static final String CARGO_ITEM_LIST_PROPERTY = "cargoItemList";
    public static final String FEEDBACK_REVIEW_LIST_PROPERTY = "feedbackReviewList";
    public static final String INVOICE_LIST_PROPERTY = "invoiceList";
    public static final String CLAIMS_RECORD_LIST_PROPERTY = "claimsRecordList";
    public static final String CUSTOMS_DECLARATION_LIST_PROPERTY = "customsDeclarationList";
    private String orderNumber;
    private String status;
    private PrivateCustomer customer;
    private PickupAddress pickupAddress;
    private PickupAddress deliveryAddress;
    private BigDecimal totalWeight;
    private BigDecimal totalVolume;
    private BigDecimal estimatedCost;
    private BigDecimal actualCost;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private SmartList<DispatchPlan> dispatchPlanList;
    private SmartList<CargoItem> cargoItemList;
    private SmartList<FeedbackReview> feedbackReviewList;
    private SmartList<Invoice> invoiceList;
    private SmartList<ClaimsRecord> claimsRecordList;
    private SmartList<CustomsDeclaration> customsDeclarationList;

    public String getOrderNumber(){
        return this.orderNumber;
    }
    public String getStatus(){
        return this.status;
    }
    public PrivateCustomer getCustomer(){
        return this.customer;
    }
    public PickupAddress getPickupAddress(){
        return this.pickupAddress;
    }
    public PickupAddress getDeliveryAddress(){
        return this.deliveryAddress;
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
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public SmartList<DispatchPlan> getDispatchPlanList(){
        return this.dispatchPlanList;
    }
    public SmartList<CargoItem> getCargoItemList(){
        return this.cargoItemList;
    }
    public SmartList<FeedbackReview> getFeedbackReviewList(){
        return this.feedbackReviewList;
    }
    public SmartList<Invoice> getInvoiceList(){
        return this.invoiceList;
    }
    public SmartList<ClaimsRecord> getClaimsRecordList(){
        return this.claimsRecordList;
    }
    public SmartList<CustomsDeclaration> getCustomsDeclarationList(){
        return this.customsDeclarationList;
    }
    public MovingOrder updateOrderNumber(String orderNumber){
        orderNumber = (orderNumber == null ? null : orderNumber.trim());
        if(Objects.equals(this.orderNumber, orderNumber)){
            return this;
        }
        handleUpdate(ORDER_NUMBER_PROPERTY, getOrderNumber(), orderNumber);
        this.orderNumber = orderNumber;
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
    public MovingOrder updateCustomer(PrivateCustomer customer){
        if(Objects.equals(this.customer, customer)){
            return this;
        }
        handleUpdate(CUSTOMER_PROPERTY, getCustomer(), customer);
        this.customer = customer;
        return this;
    }
    public MovingOrder updatePickupAddress(PickupAddress pickupAddress){
        if(Objects.equals(this.pickupAddress, pickupAddress)){
            return this;
        }
        handleUpdate(PICKUP_ADDRESS_PROPERTY, getPickupAddress(), pickupAddress);
        this.pickupAddress = pickupAddress;
        return this;
    }
    public MovingOrder updateDeliveryAddress(PickupAddress deliveryAddress){
        if(Objects.equals(this.deliveryAddress, deliveryAddress)){
            return this;
        }
        handleUpdate(DELIVERY_ADDRESS_PROPERTY, getDeliveryAddress(), deliveryAddress);
        this.deliveryAddress = deliveryAddress;
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
    public MovingOrder updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public MovingOrder updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
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
    public MovingOrder addFeedbackReview(FeedbackReview feedbackReview){
        if (feedbackReview == null){
            return this;
        }

        if(null == this.feedbackReviewList){
            this.feedbackReviewList = new SmartList<>();
        }

        this.feedbackReviewList.add(feedbackReview);
        feedbackReview.cacheRelation(FeedbackReview.MOVING_ORDER_PROPERTY, this);
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
    public MovingOrder addClaimsRecord(ClaimsRecord claimsRecord){
        if (claimsRecord == null){
            return this;
        }

        if(null == this.claimsRecordList){
            this.claimsRecordList = new SmartList<>();
        }

        this.claimsRecordList.add(claimsRecord);
        claimsRecord.cacheRelation(ClaimsRecord.MOVING_ORDER_PROPERTY, this);
        return this;
    }
    public MovingOrder addCustomsDeclaration(CustomsDeclaration customsDeclaration){
        if (customsDeclaration == null){
            return this;
        }

        if(null == this.customsDeclarationList){
            this.customsDeclarationList = new SmartList<>();
        }

        this.customsDeclarationList.add(customsDeclaration);
        customsDeclaration.cacheRelation(CustomsDeclaration.MOVING_ORDER_PROPERTY, this);
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
            case "orderNumber": this.orderNumber = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "customer": this.customer = (PrivateCustomer) value; break;

            case "pickupAddress": this.pickupAddress = (PickupAddress) value; break;

            case "deliveryAddress": this.deliveryAddress = (PickupAddress) value; break;

            case "totalWeight": this.totalWeight = (BigDecimal) value; break;

            case "totalVolume": this.totalVolume = (BigDecimal) value; break;

            case "estimatedCost": this.estimatedCost = (BigDecimal) value; break;

            case "actualCost": this.actualCost = (BigDecimal) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            case "dispatchPlanList": this.dispatchPlanList = (SmartList<DispatchPlan>) value; break;
            case "cargoItemList": this.cargoItemList = (SmartList<CargoItem>) value; break;
            case "feedbackReviewList": this.feedbackReviewList = (SmartList<FeedbackReview>) value; break;
            case "invoiceList": this.invoiceList = (SmartList<Invoice>) value; break;
            case "claimsRecordList": this.claimsRecordList = (SmartList<ClaimsRecord>) value; break;
            case "customsDeclarationList": this.customsDeclarationList = (SmartList<CustomsDeclaration>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "orderNumber": return this.orderNumber;
            case "status": return this.status;
            case "customer": return this.customer;
            case "pickupAddress": return this.pickupAddress;
            case "deliveryAddress": return this.deliveryAddress;
            case "totalWeight": return this.totalWeight;
            case "totalVolume": return this.totalVolume;
            case "estimatedCost": return this.estimatedCost;
            case "actualCost": return this.actualCost;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            case "dispatchPlanList": return this.dispatchPlanList;
            case "cargoItemList": return this.cargoItemList;
            case "feedbackReviewList": return this.feedbackReviewList;
            case "invoiceList": return this.invoiceList;
            case "claimsRecordList": return this.claimsRecordList;
            case "customsDeclarationList": return this.customsDeclarationList;
            default: return super.__internalGet(property);
        }
    }

}