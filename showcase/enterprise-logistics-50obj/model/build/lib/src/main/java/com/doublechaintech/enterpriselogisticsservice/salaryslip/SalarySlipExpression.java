package com.doublechaintech.enterpriselogisticsservice.salaryslip;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SalarySlipExpression<T, E, U extends SalarySlip> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SalarySlipExpression(Expression<T, U> expression){
        super(expression);
    }

    public SalarySlipExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SalarySlipExpression<T, U, U> updateId(Long id){
        return new SalarySlipExpression(this, $it -> {((SalarySlip)$it).__internalSet("id", id); return this;});
     }

     public SalarySlipExpression<T, U, U> save(UserContext userContext){
        return new SalarySlipExpression(this, $it -> ((SalarySlip)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SalarySlipExpression<T, U, U> save(String intent, UserContext userContext){
        return new SalarySlipExpression(this, $it -> ((SalarySlip)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public StaffMemberExpression<T, U, StaffMember> getStaff(){
       return new StaffMemberExpression(this, $it ->  ((SalarySlip)$it).getStaff());
    }

    public SalarySlipExpression<T, U, U> updateStaff(StaffMember staff){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateStaff(staff));
    }

    public Expression<T, String> getPeriod(){
       return apply(SalarySlip::getPeriod);
    }
    public SalarySlipExpression<T, U, U> updatePeriod(String period){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updatePeriod(period));
    }

    public Expression<T, String> getBaseSalary(){
       return apply(SalarySlip::getBaseSalary);
    }
    public SalarySlipExpression<T, U, U> updateBaseSalary(String baseSalary){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateBaseSalary(baseSalary));
    }

    public Expression<T, String> getBonus(){
       return apply(SalarySlip::getBonus);
    }
    public SalarySlipExpression<T, U, U> updateBonus(String bonus){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateBonus(bonus));
    }

    public Expression<T, String> getDeductions(){
       return apply(SalarySlip::getDeductions);
    }
    public SalarySlipExpression<T, U, U> updateDeductions(String deductions){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateDeductions(deductions));
    }

    public Expression<T, String> getNetPay(){
       return apply(SalarySlip::getNetPay);
    }
    public SalarySlipExpression<T, U, U> updateNetPay(String netPay){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateNetPay(netPay));
    }

    public Expression<T, String> getStatus(){
       return apply(SalarySlip::getStatus);
    }
    public SalarySlipExpression<T, U, U> updateStatus(String status){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(SalarySlip::getCreatedAt);
    }
    public SalarySlipExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(SalarySlip::getUpdatedAt);
    }
    public SalarySlipExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new SalarySlipExpression(this, $it ->  ((SalarySlip)$it).updateUpdatedAt(updatedAt));
    }

}