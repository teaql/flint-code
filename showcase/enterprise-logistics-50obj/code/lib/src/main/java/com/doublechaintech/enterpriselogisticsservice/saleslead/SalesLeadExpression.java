package com.doublechaintech.enterpriselogisticsservice.saleslead;

import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMember;
import com.doublechaintech.enterpriselogisticsservice.staffmember.StaffMemberExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.time.LocalDateTime;
import java.util.function.Function;

public class SalesLeadExpression<T, E, U extends SalesLead> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public SalesLeadExpression(Expression<T, U> expression){
        super(expression);
    }

    public SalesLeadExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public SalesLeadExpression<T, U, U> updateId(Long id){
        return new SalesLeadExpression(this, $it -> {((SalesLead)$it).__internalSet("id", id); return this;});
     }

     public SalesLeadExpression<T, U, U> save(UserContext userContext){
        return new SalesLeadExpression(this, $it -> ((SalesLead)$it).auditAs("Saved by Expression").save(userContext));
     }

     public SalesLeadExpression<T, U, U> save(String intent, UserContext userContext){
        return new SalesLeadExpression(this, $it -> ((SalesLead)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(SalesLead::getName);
    }
    public SalesLeadExpression<T, U, U> updateName(String name){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateName(name));
    }

    public Expression<T, String> getCompany(){
       return apply(SalesLead::getCompany);
    }
    public SalesLeadExpression<T, U, U> updateCompany(String company){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateCompany(company));
    }

    public Expression<T, String> getEmail(){
       return apply(SalesLead::getEmail);
    }
    public SalesLeadExpression<T, U, U> updateEmail(String email){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateEmail(email));
    }

    public Expression<T, String> getPhone(){
       return apply(SalesLead::getPhone);
    }
    public SalesLeadExpression<T, U, U> updatePhone(String phone){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updatePhone(phone));
    }

    public Expression<T, String> getSource(){
       return apply(SalesLead::getSource);
    }
    public SalesLeadExpression<T, U, U> updateSource(String source){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateSource(source));
    }

    public Expression<T, String> getStatus(){
       return apply(SalesLead::getStatus);
    }
    public SalesLeadExpression<T, U, U> updateStatus(String status){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateStatus(status));
    }

    public StaffMemberExpression<T, U, StaffMember> getAssignedTo(){
       return new StaffMemberExpression(this, $it ->  ((SalesLead)$it).getAssignedTo());
    }

    public SalesLeadExpression<T, U, U> updateAssignedTo(StaffMember assignedTo){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateAssignedTo(assignedTo));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(SalesLead::getCreatedTime);
    }
    public SalesLeadExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(SalesLead::getUpdatedTime);
    }
    public SalesLeadExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new SalesLeadExpression(this, $it ->  ((SalesLead)$it).updateUpdatedTime(updatedTime));
    }

}