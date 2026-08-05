package com.doublechaintech.enterpriselogisticsservice.promotioncampaign;

import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoi;
import com.doublechaintech.enterpriselogisticsservice.marketingroi.MarketingRoiListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class PromotionCampaignExpression<T, E, U extends PromotionCampaign> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public PromotionCampaignExpression(Expression<T, U> expression){
        super(expression);
    }

    public PromotionCampaignExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public PromotionCampaignExpression<T, U, U> updateId(Long id){
        return new PromotionCampaignExpression(this, $it -> {((PromotionCampaign)$it).__internalSet("id", id); return this;});
     }

     public PromotionCampaignExpression<T, U, U> save(UserContext userContext){
        return new PromotionCampaignExpression(this, $it -> ((PromotionCampaign)$it).auditAs("Saved by Expression").save(userContext));
     }

     public PromotionCampaignExpression<T, U, U> save(String intent, UserContext userContext){
        return new PromotionCampaignExpression(this, $it -> ((PromotionCampaign)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(PromotionCampaign::getName);
    }
    public PromotionCampaignExpression<T, U, U> updateName(String name){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateName(name));
    }

    public Expression<T, String> getDescription(){
       return apply(PromotionCampaign::getDescription);
    }
    public PromotionCampaignExpression<T, U, U> updateDescription(String description){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateDescription(description));
    }

    public Expression<T, LocalDate> getStartDate(){
       return apply(PromotionCampaign::getStartDate);
    }
    public PromotionCampaignExpression<T, U, U> updateStartDate(LocalDate startDate){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateStartDate(startDate));
    }

    public Expression<T, LocalDate> getEndDate(){
       return apply(PromotionCampaign::getEndDate);
    }
    public PromotionCampaignExpression<T, U, U> updateEndDate(LocalDate endDate){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateEndDate(endDate));
    }

    public Expression<T, BigDecimal> getBudget(){
       return apply(PromotionCampaign::getBudget);
    }
    public PromotionCampaignExpression<T, U, U> updateBudget(BigDecimal budget){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateBudget(budget));
    }

    public Expression<T, String> getStatus(){
       return apply(PromotionCampaign::getStatus);
    }
    public PromotionCampaignExpression<T, U, U> updateStatus(String status){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(PromotionCampaign::getCreatedTime);
    }
    public PromotionCampaignExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(PromotionCampaign::getUpdatedTime);
    }
    public PromotionCampaignExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).updateUpdatedTime(updatedTime));
    }

    public MarketingRoiListExpression<T, U, MarketingRoi> getMarketingRoiList(){
        return new MarketingRoiListExpression(this, $it ->  ((PromotionCampaign)$it).getMarketingRoiList());
    }
    public PromotionCampaignExpression<T, U, U> addMarketingRoi(MarketingRoi marketingRoi){
       return new PromotionCampaignExpression(this, $it ->  ((PromotionCampaign)$it).addMarketingRoi(marketingRoi));
    }
}