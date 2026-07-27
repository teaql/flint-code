package com.doublechaintech.enterpriselogisticsservice.financialreport;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class FinancialReportExpression<T, E, U extends FinancialReport> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public FinancialReportExpression(Expression<T, U> expression){
        super(expression);
    }

    public FinancialReportExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public FinancialReportExpression<T, U, U> updateId(Long id){
        return new FinancialReportExpression(this, $it -> {((FinancialReport)$it).__internalSet("id", id); return this;});
     }

     public FinancialReportExpression<T, U, U> save(UserContext userContext){
        return new FinancialReportExpression(this, $it -> ((FinancialReport)$it).auditAs("Saved by Expression").save(userContext));
     }

     public FinancialReportExpression<T, U, U> save(String intent, UserContext userContext){
        return new FinancialReportExpression(this, $it -> ((FinancialReport)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(FinancialReport::getName);
    }
    public FinancialReportExpression<T, U, U> updateName(String name){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updateName(name));
    }

    public Expression<T, String> getCode(){
       return apply(FinancialReport::getCode);
    }
    public FinancialReportExpression<T, U, U> updateCode(String code){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updateCode(code));
    }

    public Expression<T, BigDecimal> getTotalRevenue(){
       return apply(FinancialReport::getTotalRevenue);
    }
    public FinancialReportExpression<T, U, U> updateTotalRevenue(BigDecimal totalRevenue){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updateTotalRevenue(totalRevenue));
    }

    public Expression<T, BigDecimal> getTotalExpenses(){
       return apply(FinancialReport::getTotalExpenses);
    }
    public FinancialReportExpression<T, U, U> updateTotalExpenses(BigDecimal totalExpenses){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updateTotalExpenses(totalExpenses));
    }

    public Expression<T, LocalDate> getPeriodStart(){
       return apply(FinancialReport::getPeriodStart);
    }
    public FinancialReportExpression<T, U, U> updatePeriodStart(LocalDate periodStart){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updatePeriodStart(periodStart));
    }

    public Expression<T, LocalDate> getPeriodEnd(){
       return apply(FinancialReport::getPeriodEnd);
    }
    public FinancialReportExpression<T, U, U> updatePeriodEnd(LocalDate periodEnd){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updatePeriodEnd(periodEnd));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(FinancialReport::getCreatedAt);
    }
    public FinancialReportExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(FinancialReport::getUpdatedAt);
    }
    public FinancialReportExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new FinancialReportExpression(this, $it ->  ((FinancialReport)$it).updateUpdatedAt(updatedAt));
    }

}