package com.doublechaintech.enterpriselogisticsservice.movingorder;

import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItem;
import com.doublechaintech.enterpriselogisticsservice.cargoitem.CargoItemListExpression;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecord;
import com.doublechaintech.enterpriselogisticsservice.claimsrecord.ClaimsRecordListExpression;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclaration;
import com.doublechaintech.enterpriselogisticsservice.customsdeclaration.CustomsDeclarationListExpression;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanListExpression;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewListExpression;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceListExpression;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddress;
import com.doublechaintech.enterpriselogisticsservice.pickupaddress.PickupAddressExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
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


    public Expression<T, String> getOrderNumber(){
       return apply(MovingOrder::getOrderNumber);
    }
    public MovingOrderExpression<T, U, U> updateOrderNumber(String orderNumber){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateOrderNumber(orderNumber));
    }

    public Expression<T, String> getStatus(){
       return apply(MovingOrder::getStatus);
    }
    public MovingOrderExpression<T, U, U> updateStatus(String status){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateStatus(status));
    }

    public PrivateCustomerExpression<T, U, PrivateCustomer> getCustomer(){
       return new PrivateCustomerExpression(this, $it ->  ((MovingOrder)$it).getCustomer());
    }

    public MovingOrderExpression<T, U, U> updateCustomer(PrivateCustomer customer){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateCustomer(customer));
    }

    public PickupAddressExpression<T, U, PickupAddress> getPickupAddress(){
       return new PickupAddressExpression(this, $it ->  ((MovingOrder)$it).getPickupAddress());
    }

    public MovingOrderExpression<T, U, U> updatePickupAddress(PickupAddress pickupAddress){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updatePickupAddress(pickupAddress));
    }

    public PickupAddressExpression<T, U, PickupAddress> getDeliveryAddress(){
       return new PickupAddressExpression(this, $it ->  ((MovingOrder)$it).getDeliveryAddress());
    }

    public MovingOrderExpression<T, U, U> updateDeliveryAddress(PickupAddress deliveryAddress){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateDeliveryAddress(deliveryAddress));
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

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(MovingOrder::getCreatedTime);
    }
    public MovingOrderExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(MovingOrder::getUpdatedTime);
    }
    public MovingOrderExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).updateUpdatedTime(updatedTime));
    }

    public DispatchPlanListExpression<T, U, DispatchPlan> getDispatchPlanList(){
        return new DispatchPlanListExpression(this, $it ->  ((MovingOrder)$it).getDispatchPlanList());
    }
    public CargoItemListExpression<T, U, CargoItem> getCargoItemList(){
        return new CargoItemListExpression(this, $it ->  ((MovingOrder)$it).getCargoItemList());
    }
    public FeedbackReviewListExpression<T, U, FeedbackReview> getFeedbackReviewList(){
        return new FeedbackReviewListExpression(this, $it ->  ((MovingOrder)$it).getFeedbackReviewList());
    }
    public InvoiceListExpression<T, U, Invoice> getInvoiceList(){
        return new InvoiceListExpression(this, $it ->  ((MovingOrder)$it).getInvoiceList());
    }
    public ClaimsRecordListExpression<T, U, ClaimsRecord> getClaimsRecordList(){
        return new ClaimsRecordListExpression(this, $it ->  ((MovingOrder)$it).getClaimsRecordList());
    }
    public CustomsDeclarationListExpression<T, U, CustomsDeclaration> getCustomsDeclarationList(){
        return new CustomsDeclarationListExpression(this, $it ->  ((MovingOrder)$it).getCustomsDeclarationList());
    }
    public MovingOrderExpression<T, U, U> addDispatchPlan(DispatchPlan dispatchPlan){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addDispatchPlan(dispatchPlan));
    }
    public MovingOrderExpression<T, U, U> addCargoItem(CargoItem cargoItem){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addCargoItem(cargoItem));
    }
    public MovingOrderExpression<T, U, U> addFeedbackReview(FeedbackReview feedbackReview){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addFeedbackReview(feedbackReview));
    }
    public MovingOrderExpression<T, U, U> addInvoice(Invoice invoice){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addInvoice(invoice));
    }
    public MovingOrderExpression<T, U, U> addClaimsRecord(ClaimsRecord claimsRecord){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addClaimsRecord(claimsRecord));
    }
    public MovingOrderExpression<T, U, U> addCustomsDeclaration(CustomsDeclaration customsDeclaration){
       return new MovingOrderExpression(this, $it ->  ((MovingOrder)$it).addCustomsDeclaration(customsDeclaration));
    }
}