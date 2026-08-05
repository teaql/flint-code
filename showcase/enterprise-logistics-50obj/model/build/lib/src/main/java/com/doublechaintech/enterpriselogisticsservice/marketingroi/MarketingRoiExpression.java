package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class MarketingRoiExpression<T, E, U extends MarketingRoi> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public MarketingRoiExpression(Expression<T, U> expression){
        super(expression);
    }

    public MarketingRoiExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public MarketingRoiExpression<T, U, U> updateId(Long id){
        return new MarketingRoiExpression(this, $it -> {((MarketingRoi)$it).__internalSet("id", id); return this;});
     }

     public MarketingRoiExpression<T, U, U> save(UserContext userContext){
        return new MarketingRoiExpression(this, $it -> ((MarketingRoi)$it).auditAs("Saved by Expression").save(userContext));
     }

     public MarketingRoiExpression<T, U, U> save(String intent, UserContext userContext){
        return new MarketingRoiExpression(this, $it -> ((MarketingRoi)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public PromotionCampaignExpression<T, U, PromotionCampaign> getCampaign(){
       return new PromotionCampaignExpression(this, $it ->  ((MarketingRoi)$it).getCampaign());
    }

    public MarketingRoiExpression<T, U, U> updateCampaign(PromotionCampaign campaign){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateCampaign(campaign));
    }

    public Expression<T, BigDecimal> getTotalSpend(){
       return apply(MarketingRoi::getTotalSpend);
    }
    public MarketingRoiExpression<T, U, U> updateTotalSpend(BigDecimal totalSpend){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateTotalSpend(totalSpend));
    }

    public Expression<T, BigDecimal> getTotalRevenue(){
       return apply(MarketingRoi::getTotalRevenue);
    }
    public MarketingRoiExpression<T, U, U> updateTotalRevenue(BigDecimal totalRevenue){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateTotalRevenue(totalRevenue));
    }

    public Expression<T, BigDecimal> getRoiPercentage(){
       return apply(MarketingRoi::getRoiPercentage);
    }
    public MarketingRoiExpression<T, U, U> updateRoiPercentage(BigDecimal roiPercentage){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateRoiPercentage(roiPercentage));
    }

    public Expression<T, LocalDate> getReportDate(){
       return apply(MarketingRoi::getReportDate);
    }
    public MarketingRoiExpression<T, U, U> updateReportDate(LocalDate reportDate){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateReportDate(reportDate));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(MarketingRoi::getCreatedTime);
    }
    public MarketingRoiExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdateTime(){
       return apply(MarketingRoi::getUpdateTime);
    }
    public MarketingRoiExpression<T, U, U> updateUpdateTime(LocalDateTime updateTime){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateUpdateTime(updateTime));
    }

}