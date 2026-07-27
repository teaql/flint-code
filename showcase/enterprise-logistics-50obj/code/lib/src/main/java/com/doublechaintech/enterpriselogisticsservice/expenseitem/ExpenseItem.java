package com.doublechaintech.enterpriselogisticsservice.expenseitem;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class ExpenseItem extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "ExpenseItem";

    public static final String NAME_PROPERTY = "name";
    public static final String DESCRIPTION_PROPERTY = "description";
    public static final String AMOUNT_PROPERTY = "amount";
    public static final String CURRENCY_PROPERTY = "currency";
    public static final String EXPENSE_TYPE_PROPERTY = "expenseType";
    public static final String EXPENSE_DATE_PROPERTY = "expenseDate";
    public static final String EMPLOYEE_PROPERTY = "employee";
    public static final String STATUS_PROPERTY = "status";
    private String name;
    private String description;
    private BigDecimal amount;
    private String currency;
    private String expenseType;
    private LocalDate expenseDate;
    private String employee;
    private String status;

    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public BigDecimal getAmount(){
        return this.amount;
    }
    public String getCurrency(){
        return this.currency;
    }
    public String getExpenseType(){
        return this.expenseType;
    }
    public LocalDate getExpenseDate(){
        return this.expenseDate;
    }
    public String getEmployee(){
        return this.employee;
    }
    public String getStatus(){
        return this.status;
    }
    public ExpenseItem updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public ExpenseItem updateDescription(String description){
        description = (description == null ? null : description.trim());
        if(Objects.equals(this.description, description)){
            return this;
        }
        handleUpdate(DESCRIPTION_PROPERTY, getDescription(), description);
        this.description = description;
        return this;
    }
    public ExpenseItem updateAmount(BigDecimal amount){
        if(Objects.equals(this.amount, amount)){
            return this;
        }
        handleUpdate(AMOUNT_PROPERTY, getAmount(), amount);
        this.amount = amount;
        return this;
    }
    public ExpenseItem updateCurrency(String currency){
        currency = (currency == null ? null : currency.trim());
        if(Objects.equals(this.currency, currency)){
            return this;
        }
        handleUpdate(CURRENCY_PROPERTY, getCurrency(), currency);
        this.currency = currency;
        return this;
    }
    public ExpenseItem updateExpenseType(String expenseType){
        expenseType = (expenseType == null ? null : expenseType.trim());
        if(Objects.equals(this.expenseType, expenseType)){
            return this;
        }
        handleUpdate(EXPENSE_TYPE_PROPERTY, getExpenseType(), expenseType);
        this.expenseType = expenseType;
        return this;
    }
    public ExpenseItem updateExpenseDate(LocalDate expenseDate){
        if(Objects.equals(this.expenseDate, expenseDate)){
            return this;
        }
        handleUpdate(EXPENSE_DATE_PROPERTY, getExpenseDate(), expenseDate);
        this.expenseDate = expenseDate;
        return this;
    }
    public ExpenseItem updateEmployee(String employee){
        employee = (employee == null ? null : employee.trim());
        if(Objects.equals(this.employee, employee)){
            return this;
        }
        handleUpdate(EMPLOYEE_PROPERTY, getEmployee(), employee);
        this.employee = employee;
        return this;
    }
    public ExpenseItem updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }

    public static ExpenseItem refer(Long id){
        ExpenseItem refer = new ExpenseItem();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public ExpenseItem comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<ExpenseItem> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "description": this.description = (value == null ? null : ((String)value).trim()); break;

            case "amount": this.amount = (BigDecimal) value; break;

            case "currency": this.currency = (value == null ? null : ((String)value).trim()); break;

            case "expenseType": this.expenseType = (value == null ? null : ((String)value).trim()); break;

            case "expenseDate": this.expenseDate = (LocalDate) value; break;

            case "employee": this.employee = (value == null ? null : ((String)value).trim()); break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "description": return this.description;
            case "amount": return this.amount;
            case "currency": return this.currency;
            case "expenseType": return this.expenseType;
            case "expenseDate": return this.expenseDate;
            case "employee": return this.employee;
            case "status": return this.status;
            default: return super.__internalGet(property);
        }
    }

}