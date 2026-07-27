package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;

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
      }else if(expenseItem.updateItem()){
      }
      checkName(_ctx, expenseItem.getProperty(ExpenseItem.NAME_PROPERTY), newLocation(_parentLocation, ExpenseItem.NAME_PROPERTY));
      checkDescription(_ctx, expenseItem.getProperty(ExpenseItem.DESCRIPTION_PROPERTY), newLocation(_parentLocation, ExpenseItem.DESCRIPTION_PROPERTY));
      checkAmount(_ctx, expenseItem.getProperty(ExpenseItem.AMOUNT_PROPERTY), newLocation(_parentLocation, ExpenseItem.AMOUNT_PROPERTY));
      checkCurrency(_ctx, expenseItem.getProperty(ExpenseItem.CURRENCY_PROPERTY), newLocation(_parentLocation, ExpenseItem.CURRENCY_PROPERTY));
      checkExpenseType(_ctx, expenseItem.getProperty(ExpenseItem.EXPENSE_TYPE_PROPERTY), newLocation(_parentLocation, ExpenseItem.EXPENSE_TYPE_PROPERTY));
      checkExpenseDate(_ctx, expenseItem.getProperty(ExpenseItem.EXPENSE_DATE_PROPERTY), newLocation(_parentLocation, ExpenseItem.EXPENSE_DATE_PROPERTY));
      checkEmployee(_ctx, expenseItem.getProperty(ExpenseItem.EMPLOYEE_PROPERTY), newLocation(_parentLocation, ExpenseItem.EMPLOYEE_PROPERTY));
      checkStatus(_ctx, expenseItem.getProperty(ExpenseItem.STATUS_PROPERTY), newLocation(_parentLocation, ExpenseItem.STATUS_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
    public void checkDescription(UserContext _ctx, String description, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, description);
    if((description == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, description);

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
    public void checkExpenseType(UserContext _ctx, String expenseType, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, expenseType);
    if((expenseType == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, expenseType);

    }
    public void checkExpenseDate(UserContext _ctx, LocalDate expenseDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, expenseDate);
    if((expenseDate == null)){
        return;
    }
    }
    public void checkEmployee(UserContext _ctx, String employee, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, employee);
    if((employee == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, employee);

    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
}