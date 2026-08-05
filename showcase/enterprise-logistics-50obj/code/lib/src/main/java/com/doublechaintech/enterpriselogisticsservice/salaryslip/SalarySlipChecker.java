package com.doublechaintech.enterpriselogisticsservice.salaryslip;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.time.LocalDateTime;

public class SalarySlipChecker implements Checker<SalarySlip>{

    public String type(){
        return SalarySlip.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, SalarySlip salarySlip, ObjectLocation _parentLocation){
        if(needCheck(_ctx, salarySlip)){
            markAsChecked(_ctx, salarySlip);
            doCheck(_ctx, salarySlip, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, SalarySlip salarySlip, ObjectLocation _parentLocation){
      if((salarySlip == null)){
         return;
      }
      if(salarySlip.newItem()){
        if(salarySlip.getCreatedAt() == null){
           salarySlip.updateCreatedAt(java.time.LocalDateTime.now());
        }if(salarySlip.getUpdatedAt() == null){
           salarySlip.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(salarySlip.updateItem()){
        salarySlip.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkStaff(_ctx, salarySlip.getProperty(SalarySlip.STAFF_PROPERTY), newLocation(_parentLocation, SalarySlip.STAFF_PROPERTY));
      checkPeriod(_ctx, salarySlip.getProperty(SalarySlip.PERIOD_PROPERTY), newLocation(_parentLocation, SalarySlip.PERIOD_PROPERTY));
      checkBaseSalary(_ctx, salarySlip.getProperty(SalarySlip.BASE_SALARY_PROPERTY), newLocation(_parentLocation, SalarySlip.BASE_SALARY_PROPERTY));
      checkBonus(_ctx, salarySlip.getProperty(SalarySlip.BONUS_PROPERTY), newLocation(_parentLocation, SalarySlip.BONUS_PROPERTY));
      checkDeductions(_ctx, salarySlip.getProperty(SalarySlip.DEDUCTIONS_PROPERTY), newLocation(_parentLocation, SalarySlip.DEDUCTIONS_PROPERTY));
      checkNetPay(_ctx, salarySlip.getProperty(SalarySlip.NET_PAY_PROPERTY), newLocation(_parentLocation, SalarySlip.NET_PAY_PROPERTY));
      checkStatus(_ctx, salarySlip.getProperty(SalarySlip.STATUS_PROPERTY), newLocation(_parentLocation, SalarySlip.STATUS_PROPERTY));
      checkCreatedAt(_ctx, salarySlip.getProperty(SalarySlip.CREATED_AT_PROPERTY), newLocation(_parentLocation, SalarySlip.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, salarySlip.getProperty(SalarySlip.UPDATED_AT_PROPERTY), newLocation(_parentLocation, SalarySlip.UPDATED_AT_PROPERTY));
    }

    public void checkStaff(UserContext _ctx, StaffMember staff, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, staff);
    if((staff == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, staff, _parentLocation);
    }
    public void checkPeriod(UserContext _ctx, String period, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, period);
    if((period == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, period);

    }
    public void checkBaseSalary(UserContext _ctx, String baseSalary, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, baseSalary);
    if((baseSalary == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, baseSalary);

    }
    public void checkBonus(UserContext _ctx, String bonus, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, bonus);
    if((bonus == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, bonus);

    }
    public void checkDeductions(UserContext _ctx, String deductions, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, deductions);
    if((deductions == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, deductions);

    }
    public void checkNetPay(UserContext _ctx, String netPay, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, netPay);
    if((netPay == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, netPay);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

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