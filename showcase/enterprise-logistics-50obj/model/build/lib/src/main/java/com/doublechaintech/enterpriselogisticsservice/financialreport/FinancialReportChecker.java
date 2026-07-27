package com.doublechaintech.enterpriselogisticsservice.financialreport;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FinancialReportChecker implements Checker<FinancialReport>{

    public String type(){
        return FinancialReport.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, FinancialReport financialReport, ObjectLocation _parentLocation){
        if(needCheck(_ctx, financialReport)){
            markAsChecked(_ctx, financialReport);
            doCheck(_ctx, financialReport, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, FinancialReport financialReport, ObjectLocation _parentLocation){
      if((financialReport == null)){
         return;
      }
      if(financialReport.newItem()){
        if(financialReport.getCreatedAt() == null){
           financialReport.updateCreatedAt(java.time.LocalDateTime.now());
        }if(financialReport.getUpdatedAt() == null){
           financialReport.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(financialReport.updateItem()){
        financialReport.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, financialReport.getProperty(FinancialReport.NAME_PROPERTY), newLocation(_parentLocation, FinancialReport.NAME_PROPERTY));
      checkCode(_ctx, financialReport.getProperty(FinancialReport.CODE_PROPERTY), newLocation(_parentLocation, FinancialReport.CODE_PROPERTY));
      checkTotalRevenue(_ctx, financialReport.getProperty(FinancialReport.TOTAL_REVENUE_PROPERTY), newLocation(_parentLocation, FinancialReport.TOTAL_REVENUE_PROPERTY));
      checkTotalExpenses(_ctx, financialReport.getProperty(FinancialReport.TOTAL_EXPENSES_PROPERTY), newLocation(_parentLocation, FinancialReport.TOTAL_EXPENSES_PROPERTY));
      checkPeriodStart(_ctx, financialReport.getProperty(FinancialReport.PERIOD_START_PROPERTY), newLocation(_parentLocation, FinancialReport.PERIOD_START_PROPERTY));
      checkPeriodEnd(_ctx, financialReport.getProperty(FinancialReport.PERIOD_END_PROPERTY), newLocation(_parentLocation, FinancialReport.PERIOD_END_PROPERTY));
      checkCreatedAt(_ctx, financialReport.getProperty(FinancialReport.CREATED_AT_PROPERTY), newLocation(_parentLocation, FinancialReport.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, financialReport.getProperty(FinancialReport.UPDATED_AT_PROPERTY), newLocation(_parentLocation, FinancialReport.UPDATED_AT_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkCode(UserContext _ctx, String code, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, code);
    if((code == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, code);

    }
    public void checkTotalRevenue(UserContext _ctx, BigDecimal totalRevenue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalRevenue);
    if((totalRevenue == null)){
        return;
    }
    }
    public void checkTotalExpenses(UserContext _ctx, BigDecimal totalExpenses, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, totalExpenses);
    if((totalExpenses == null)){
        return;
    }
    }
    public void checkPeriodStart(UserContext _ctx, LocalDate periodStart, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, periodStart);
    if((periodStart == null)){
        return;
    }
    }
    public void checkPeriodEnd(UserContext _ctx, LocalDate periodEnd, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, periodEnd);
    if((periodEnd == null)){
        return;
    }
    }
    public void checkCreatedAt(UserContext _ctx, LocalDateTime createdAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdAt);
    if((createdAt == null)){
        return;
    }
    }
    public void checkUpdatedAt(UserContext _ctx, LocalDateTime updatedAt, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedAt);
    if((updatedAt == null)){
        return;
    }
    }
}