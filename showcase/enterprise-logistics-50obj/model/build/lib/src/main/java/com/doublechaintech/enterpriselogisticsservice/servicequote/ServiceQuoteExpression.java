package com.doublechaintech.enterpriselogisticsservice.servicequote;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomerExpression;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomerExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

public class ServiceQuoteExpression<T, E, U extends ServiceQuote> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public ServiceQuoteExpression(Expression<T, U> expression){
        super(expression);
    }

    public ServiceQuoteExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public ServiceQuoteExpression<T, U, U> updateId(Long id){
        return new ServiceQuoteExpression(this, $it -> {((ServiceQuote)$it).__internalSet("id", id); return this;});
     }

     public ServiceQuoteExpression<T, U, U> save(UserContext userContext){
        return new ServiceQuoteExpression(this, $it -> ((ServiceQuote)$it).auditAs("Saved by Expression").save(userContext));
     }

     public ServiceQuoteExpression<T, U, U> save(String intent, UserContext userContext){
        return new ServiceQuoteExpression(this, $it -> ((ServiceQuote)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getQuoteNumber(){
       return apply(ServiceQuote::getQuoteNumber);
    }
    public ServiceQuoteExpression<T, U, U> updateQuoteNumber(String quoteNumber){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateQuoteNumber(quoteNumber));
    }

    public Expression<T, String> getDescription(){
       return apply(ServiceQuote::getDescription);
    }
    public ServiceQuoteExpression<T, U, U> updateDescription(String description){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateDescription(description));
    }

    public Expression<T, BigDecimal> getEstimatedCost(){
       return apply(ServiceQuote::getEstimatedCost);
    }
    public ServiceQuoteExpression<T, U, U> updateEstimatedCost(BigDecimal estimatedCost){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateEstimatedCost(estimatedCost));
    }

    public Expression<T, String> getCurrency(){
       return apply(ServiceQuote::getCurrency);
    }
    public ServiceQuoteExpression<T, U, U> updateCurrency(String currency){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateCurrency(currency));
    }

    public Expression<T, String> getStatus(){
       return apply(ServiceQuote::getStatus);
    }
    public ServiceQuoteExpression<T, U, U> updateStatus(String status){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateStatus(status));
    }

    public Expression<T, LocalDate> getValidUntil(){
       return apply(ServiceQuote::getValidUntil);
    }
    public ServiceQuoteExpression<T, U, U> updateValidUntil(LocalDate validUntil){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateValidUntil(validUntil));
    }

    public PrivateCustomerExpression<T, U, PrivateCustomer> getPrivateCustomer(){
       return new PrivateCustomerExpression(this, $it ->  ((ServiceQuote)$it).getPrivateCustomer());
    }

    public ServiceQuoteExpression<T, U, U> updatePrivateCustomer(PrivateCustomer privateCustomer){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updatePrivateCustomer(privateCustomer));
    }

    public CorporateCustomerExpression<T, U, CorporateCustomer> getCorporateCustomer(){
       return new CorporateCustomerExpression(this, $it ->  ((ServiceQuote)$it).getCorporateCustomer());
    }

    public ServiceQuoteExpression<T, U, U> updateCorporateCustomer(CorporateCustomer corporateCustomer){
       return new ServiceQuoteExpression(this, $it ->  ((ServiceQuote)$it).updateCorporateCustomer(corporateCustomer));
    }

}