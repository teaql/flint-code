package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberChecker;
import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseItemChecker implements Checker<ExpenseItem>{

    public String type(){
        return ExpenseItem.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, ExpenseItem expenseItem, ObjectLocation _parentLocation){
        if(needCheck(_ctx, expenseItem)){
            markAsChecked(_ctx, expenseItem);
            doCheck(_ctx, expenseItem, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, ExpenseItem expenseItem, ObjectLocation _parentLocation){
      if((expenseItem == null)){
         return;
      }
      if(expenseItem.newItem()){
        if(expenseItem.getCreatedAt() == null){
           expenseItem.updateCreatedAt(java.time.LocalDateTime.now());
        }if(expenseItem.getUpdatedAt() == null){
           expenseItem.updateUpdatedAt(java.time.LocalDateTime.now());
        }
      }else if(expenseItem.updateItem()){
        expenseItem.updateUpdatedAt(java.time.LocalDateTime.now());
      }
      checkName(_ctx, expenseItem.getProperty(ExpenseItem.NAME_PROPERTY), newLocation(_parentLocation, ExpenseItem.NAME_PROPERTY));
      checkCode(_ctx, expenseItem.getProperty(ExpenseItem.CODE_PROPERTY), newLocation(_parentLocation, ExpenseItem.CODE_PROPERTY));
      checkAmount(_ctx, expenseItem.getProperty(ExpenseItem.AMOUNT_PROPERTY), newLocation(_parentLocation, ExpenseItem.AMOUNT_PROPERTY));
      checkCurrency(_ctx, expenseItem.getProperty(ExpenseItem.CURRENCY_PROPERTY), newLocation(_parentLocation, ExpenseItem.CURRENCY_PROPERTY));
      checkCategory(_ctx, expenseItem.getProperty(ExpenseItem.CATEGORY_PROPERTY), newLocation(_parentLocation, ExpenseItem.CATEGORY_PROPERTY));
      checkCreatedAt(_ctx, expenseItem.getProperty(ExpenseItem.CREATED_AT_PROPERTY), newLocation(_parentLocation, ExpenseItem.CREATED_AT_PROPERTY));
      checkUpdatedAt(_ctx, expenseItem.getProperty(ExpenseItem.UPDATED_AT_PROPERTY), newLocation(_parentLocation, ExpenseItem.UPDATED_AT_PROPERTY));
      checkStaffMember(_ctx, expenseItem.getProperty(ExpenseItem.STAFF_MEMBER_PROPERTY), newLocation(_parentLocation, ExpenseItem.STAFF_MEMBER_PROPERTY));
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
    public void checkAmount(UserContext _ctx, BigDecimal amount, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, amount);
    if((amount == null)){
        return;
    }
    }
    public void checkCurrency(UserContext _ctx, String currency, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, currency);
    if((currency == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, currency);

    }
    public void checkCategory(UserContext _ctx, String category, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, category);
    if((category == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, category);

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
    public void checkStaffMember(UserContext _ctx, StaffMember staffMember, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, staffMember);
    if((staffMember == null)){
        return;
    }
    new StaffMemberChecker().checkAndFix(_ctx, staffMember, _parentLocation);
    }
}