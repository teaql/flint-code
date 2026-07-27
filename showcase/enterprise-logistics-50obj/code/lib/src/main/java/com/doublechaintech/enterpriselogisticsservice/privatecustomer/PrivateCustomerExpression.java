package com.doublechaintech.enterpriselogisticsservice.privatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactListExpression;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyListExpression;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewListExpression;
import com.doublechaintech.enterpriselogisticsservice.invoice.Invoice;
import com.doublechaintech.enterpriselogisticsservice.invoice.InvoiceListExpression;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrderListExpression;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.util.function.Function;

public class PrivateCustomerExpression<T, E, U extends PrivateCustomer> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PrivateCustomerExpression(Expression<T, U> expression){
        super(expression);
    }

    public PrivateCustomerExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PrivateCustomerExpression<T, U, U> updateId(Long id){
        return new PrivateCustomerExpression(this, $it -> {((PrivateCustomer)$it).__internalSet("id", id); return this;});
     }

     public PrivateCustomerExpression<T, U, U> save(UserContext userContext){
        return new PrivateCustomerExpression(this, $it -> ((PrivateCustomer)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PrivateCustomerExpression<T, U, U> save(String intent, UserContext userContext){
        return new PrivateCustomerExpression(this, $it -> ((PrivateCustomer)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(PrivateCustomer::getName);
    }
    public PrivateCustomerExpression<T, U, U> updateName(String name){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateName(name));
    }

    public Expression<T, String> getPhone(){
       return apply(PrivateCustomer::getPhone);
    }
    public PrivateCustomerExpression<T, U, U> updatePhone(String phone){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updatePhone(phone));
    }

    public Expression<T, String> getEmail(){
       return apply(PrivateCustomer::getEmail);
    }
    public PrivateCustomerExpression<T, U, U> updateEmail(String email){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateEmail(email));
    }

    public Expression<T, String> getAddress(){
       return apply(PrivateCustomer::getAddress);
    }
    public PrivateCustomerExpression<T, U, U> updateAddress(String address){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateAddress(address));
    }

    public Expression<T, String> getCity(){
       return apply(PrivateCustomer::getCity);
    }
    public PrivateCustomerExpression<T, U, U> updateCity(String city){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCity(city));
    }

    public Expression<T, String> getCountry(){
       return apply(PrivateCustomer::getCountry);
    }
    public PrivateCustomerExpression<T, U, U> updateCountry(String country){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCountry(country));
    }

    public Expression<T, String> getCustomerType(){
       return apply(PrivateCustomer::getCustomerType);
    }
    public PrivateCustomerExpression<T, U, U> updateCustomerType(String customerType){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).updateCustomerType(customerType));
    }

    public MovingOrderListExpression<T, U, MovingOrder> getMovingOrderList(){
        return new MovingOrderListExpression(this, $it ->  ((PrivateCustomer)$it).getMovingOrderList());
    }
    public CustomerContactListExpression<T, U, CustomerContact> getCustomerContactList(){
        return new CustomerContactListExpression(this, $it ->  ((PrivateCustomer)$it).getCustomerContactList());
    }
    public ServiceQuoteListExpression<T, U, ServiceQuote> getServiceQuoteList(){
        return new ServiceQuoteListExpression(this, $it ->  ((PrivateCustomer)$it).getServiceQuoteList());
    }
    public FeedbackReviewListExpression<T, U, FeedbackReview> getFeedbackReviewList(){
        return new FeedbackReviewListExpression(this, $it ->  ((PrivateCustomer)$it).getFeedbackReviewList());
    }
    public CustomerLoyaltyListExpression<T, U, CustomerLoyalty> getCustomerLoyaltyList(){
        return new CustomerLoyaltyListExpression(this, $it ->  ((PrivateCustomer)$it).getCustomerLoyaltyList());
    }
    public InvoiceListExpression<T, U, Invoice> getInvoiceList(){
        return new InvoiceListExpression(this, $it ->  ((PrivateCustomer)$it).getInvoiceList());
    }
    public PrivateCustomerExpression<T, U, U> addMovingOrder(MovingOrder movingOrder){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addMovingOrder(movingOrder));
    }
    public PrivateCustomerExpression<T, U, U> addCustomerContact(CustomerContact customerContact){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addCustomerContact(customerContact));
    }
    public PrivateCustomerExpression<T, U, U> addServiceQuote(ServiceQuote serviceQuote){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addServiceQuote(serviceQuote));
    }
    public PrivateCustomerExpression<T, U, U> addFeedbackReview(FeedbackReview feedbackReview){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addFeedbackReview(feedbackReview));
    }
    public PrivateCustomerExpression<T, U, U> addCustomerLoyalty(CustomerLoyalty customerLoyalty){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addCustomerLoyalty(customerLoyalty));
    }
    public PrivateCustomerExpression<T, U, U> addInvoice(Invoice invoice){
       return new PrivateCustomerExpression(this, $it ->  ((PrivateCustomer)$it).addInvoice(invoice));
    }
}