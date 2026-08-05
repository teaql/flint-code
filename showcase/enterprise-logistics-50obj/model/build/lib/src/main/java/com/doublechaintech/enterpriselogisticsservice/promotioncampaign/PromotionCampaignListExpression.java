package com.doublechaintech.enterpriselogisticsservice.promotioncampaign;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class PromotionCampaignListExpression<T, E, U extends PromotionCampaign> extends SmartListExpression<T, E, U> {
    public PromotionCampaignListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public PromotionCampaignListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public PromotionCampaignExpression<T, U, U> first() {
       return new PromotionCampaignExpression(super.first());
    }

    public PromotionCampaignExpression<T, U, U> get(int index) {
      return new PromotionCampaignExpression(super.get(index));
    }
}