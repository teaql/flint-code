package com.doublechaintech.enterpriselogisticsservice.marketingroi;

import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaign;
import com.doublechaintech.enterpriselogisticsservice.promotioncampaign.PromotionCampaignExpression;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannel;
import com.doublechaintech.enterpriselogisticsservice.saleschannel.SalesChannelExpression;
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

    public SalesChannelExpression<T, U, SalesChannel> getChannel(){
       return new SalesChannelExpression(this, $it ->  ((MarketingRoi)$it).getChannel());
    }

    public MarketingRoiExpression<T, U, U> updateChannel(SalesChannel channel){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateChannel(channel));
    }

    public Expression<T, BigDecimal> getSpend(){
       return apply(MarketingRoi::getSpend);
    }
    public MarketingRoiExpression<T, U, U> updateSpend(BigDecimal spend){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateSpend(spend));
    }

    public Expression<T, BigDecimal> getRevenue(){
       return apply(MarketingRoi::getRevenue);
    }
    public MarketingRoiExpression<T, U, U> updateRevenue(BigDecimal revenue){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateRevenue(revenue));
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

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(MarketingRoi::getUpdatedTime);
    }
    public MarketingRoiExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new MarketingRoiExpression(this, $it ->  ((MarketingRoi)$it).updateUpdatedTime(updatedTime));
    }

}