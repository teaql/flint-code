package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public class CustomsDeclarationExpression<T, E, U extends CustomsDeclaration> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public CustomsDeclarationExpression(Expression<T, U> expression){
        super(expression);
    }

    public CustomsDeclarationExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public CustomsDeclarationExpression<T, U, U> updateId(Long id){
        return new CustomsDeclarationExpression(this, $it -> {((CustomsDeclaration)$it).__internalSet("id", id); return this;});
     }

     public CustomsDeclarationExpression<T, U, U> save(UserContext userContext){
        return new CustomsDeclarationExpression(this, $it -> ((CustomsDeclaration)$it).auditAs("Saved by Expression").save(userContext));
     }

     public CustomsDeclarationExpression<T, U, U> save(String intent, UserContext userContext){
        return new CustomsDeclarationExpression(this, $it -> ((CustomsDeclaration)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getDeclarationNumber(){
       return apply(CustomsDeclaration::getDeclarationNumber);
    }
    public CustomsDeclarationExpression<T, U, U> updateDeclarationNumber(String declarationNumber){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateDeclarationNumber(declarationNumber));
    }

    public Expression<T, String> getPortOfEntry(){
       return apply(CustomsDeclaration::getPortOfEntry);
    }
    public CustomsDeclarationExpression<T, U, U> updatePortOfEntry(String portOfEntry){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updatePortOfEntry(portOfEntry));
    }

    public Expression<T, String> getCountryOfOrigin(){
       return apply(CustomsDeclaration::getCountryOfOrigin);
    }
    public CustomsDeclarationExpression<T, U, U> updateCountryOfOrigin(String countryOfOrigin){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateCountryOfOrigin(countryOfOrigin));
    }

    public Expression<T, BigDecimal> getDeclaredValue(){
       return apply(CustomsDeclaration::getDeclaredValue);
    }
    public CustomsDeclarationExpression<T, U, U> updateDeclaredValue(BigDecimal declaredValue){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateDeclaredValue(declaredValue));
    }

    public Expression<T, String> getStatus(){
       return apply(CustomsDeclaration::getStatus);
    }
    public CustomsDeclarationExpression<T, U, U> updateStatus(String status){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateStatus(status));
    }

    public Expression<T, LocalDate> getClearanceDate(){
       return apply(CustomsDeclaration::getClearanceDate);
    }
    public CustomsDeclarationExpression<T, U, U> updateClearanceDate(LocalDate clearanceDate){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateClearanceDate(clearanceDate));
    }

    public Expression<T, LocalDateTime> getCreatedTime(){
       return apply(CustomsDeclaration::getCreatedTime);
    }
    public CustomsDeclarationExpression<T, U, U> updateCreatedTime(LocalDateTime createdTime){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateCreatedTime(createdTime));
    }

    public Expression<T, LocalDateTime> getUpdatedTime(){
       return apply(CustomsDeclaration::getUpdatedTime);
    }
    public CustomsDeclarationExpression<T, U, U> updateUpdatedTime(LocalDateTime updatedTime){
       return new CustomsDeclarationExpression(this, $it ->  ((CustomsDeclaration)$it).updateUpdatedTime(updatedTime));
    }

}