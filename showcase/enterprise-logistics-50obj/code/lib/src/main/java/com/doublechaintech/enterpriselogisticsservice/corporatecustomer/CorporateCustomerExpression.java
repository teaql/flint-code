package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContactListExpression;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyaltyListExpression;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReview;
import com.doublechaintech.enterpriselogisticsservice.feedbackreview.FeedbackReviewListExpression;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuoteListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.util.function.Function;

public class CorporateCustomerExpression<T, E, U extends CorporateCustomer> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CorporateCustomerExpression(Expression<T, U> expression){
        super(expression);
    }

    public CorporateCustomerExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CorporateCustomerExpression<T, U, U> updateId(Long id){
        return new CorporateCustomerExpression(this, $it -> {((CorporateCustomer)$it).__internalSet("id", id); return this;});
     }

     public CorporateCustomerExpression<T, U, U> save(UserContext userContext){
        return new CorporateCustomerExpression(this, $it -> ((CorporateCustomer)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CorporateCustomerExpression<T, U, U> save(String intent, UserContext userContext){
        return new CorporateCustomerExpression(this, $it -> ((CorporateCustomer)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(CorporateCustomer::getName);
    }
    public CorporateCustomerExpression<T, U, U> updateName(String name){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateName(name));
    }

    public Expression<T, String> getContactPerson(){
       return apply(CorporateCustomer::getContactPerson);
    }
    public CorporateCustomerExpression<T, U, U> updateContactPerson(String contactPerson){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateContactPerson(contactPerson));
    }

    public Expression<T, String> getPhone(){
       return apply(CorporateCustomer::getPhone);
    }
    public CorporateCustomerExpression<T, U, U> updatePhone(String phone){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updatePhone(phone));
    }

    public Expression<T, String> getEmail(){
       return apply(CorporateCustomer::getEmail);
    }
    public CorporateCustomerExpression<T, U, U> updateEmail(String email){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateEmail(email));
    }

    public Expression<T, String> getAddress(){
       return apply(CorporateCustomer::getAddress);
    }
    public CorporateCustomerExpression<T, U, U> updateAddress(String address){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateAddress(address));
    }

    public Expression<T, String> getCity(){
       return apply(CorporateCustomer::getCity);
    }
    public CorporateCustomerExpression<T, U, U> updateCity(String city){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateCity(city));
    }

    public Expression<T, String> getCountry(){
       return apply(CorporateCustomer::getCountry);
    }
    public CorporateCustomerExpression<T, U, U> updateCountry(String country){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateCountry(country));
    }

    public Expression<T, String> getTaxId(){
       return apply(CorporateCustomer::getTaxId);
    }
    public CorporateCustomerExpression<T, U, U> updateTaxId(String taxId){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateTaxId(taxId));
    }

    public Expression<T, String> getCustomerType(){
       return apply(CorporateCustomer::getCustomerType);
    }
    public CorporateCustomerExpression<T, U, U> updateCustomerType(String customerType){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).updateCustomerType(customerType));
    }

    public CustomerContactListExpression<T, U, CustomerContact> getCustomerContactList(){
        return new CustomerContactListExpression(this, $it ->  ((CorporateCustomer)$it).getCustomerContactList());
    }
    public ServiceQuoteListExpression<T, U, ServiceQuote> getServiceQuoteList(){
        return new ServiceQuoteListExpression(this, $it ->  ((CorporateCustomer)$it).getServiceQuoteList());
    }
    public FeedbackReviewListExpression<T, U, FeedbackReview> getFeedbackReviewList(){
        return new FeedbackReviewListExpression(this, $it ->  ((CorporateCustomer)$it).getFeedbackReviewList());
    }
    public CustomerLoyaltyListExpression<T, U, CustomerLoyalty> getCustomerLoyaltyList(){
        return new CustomerLoyaltyListExpression(this, $it ->  ((CorporateCustomer)$it).getCustomerLoyaltyList());
    }
    public CorporateCustomerExpression<T, U, U> addCustomerContact(CustomerContact customerContact){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addCustomerContact(customerContact));
    }
    public CorporateCustomerExpression<T, U, U> addServiceQuote(ServiceQuote serviceQuote){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addServiceQuote(serviceQuote));
    }
    public CorporateCustomerExpression<T, U, U> addFeedbackReview(FeedbackReview feedbackReview){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addFeedbackReview(feedbackReview));
    }
    public CorporateCustomerExpression<T, U, U> addCustomerLoyalty(CustomerLoyalty customerLoyalty){
       return new CorporateCustomerExpression(this, $it ->  ((CorporateCustomer)$it).addCustomerLoyalty(customerLoyalty));
    }
}