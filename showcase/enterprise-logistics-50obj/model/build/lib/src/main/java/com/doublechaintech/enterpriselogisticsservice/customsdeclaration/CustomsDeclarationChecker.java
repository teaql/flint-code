package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomsDeclarationChecker implements Checker<CustomsDeclaration>{

    public String type(){
        return CustomsDeclaration.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, CustomsDeclaration customsDeclaration, ObjectLocation _parentLocation){
        if(needCheck(_ctx, customsDeclaration)){
            markAsChecked(_ctx, customsDeclaration);
            doCheck(_ctx, customsDeclaration, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, CustomsDeclaration customsDeclaration, ObjectLocation _parentLocation){
      if((customsDeclaration == null)){
         return;
      }
      if(customsDeclaration.newItem()){
        if(customsDeclaration.getCreatedTime() == null){
           customsDeclaration.updateCreatedTime(java.time.LocalDateTime.now());
        }if(customsDeclaration.getUpdatedTime() == null){
           customsDeclaration.updateUpdatedTime(java.time.LocalDateTime.now());
        }
      }else if(customsDeclaration.updateItem()){
        customsDeclaration.updateUpdatedTime(java.time.LocalDateTime.now());
      }
      checkDeclarationNumber(_ctx, customsDeclaration.getProperty(CustomsDeclaration.DECLARATION_NUMBER_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.DECLARATION_NUMBER_PROPERTY));
      checkPortOfEntry(_ctx, customsDeclaration.getProperty(CustomsDeclaration.PORT_OF_ENTRY_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.PORT_OF_ENTRY_PROPERTY));
      checkCountryOfOrigin(_ctx, customsDeclaration.getProperty(CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.COUNTRY_OF_ORIGIN_PROPERTY));
      checkDeclaredValue(_ctx, customsDeclaration.getProperty(CustomsDeclaration.DECLARED_VALUE_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.DECLARED_VALUE_PROPERTY));
      checkStatus(_ctx, customsDeclaration.getProperty(CustomsDeclaration.STATUS_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.STATUS_PROPERTY));
      checkClearanceDate(_ctx, customsDeclaration.getProperty(CustomsDeclaration.CLEARANCE_DATE_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.CLEARANCE_DATE_PROPERTY));
      checkCreatedTime(_ctx, customsDeclaration.getProperty(CustomsDeclaration.CREATED_TIME_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.CREATED_TIME_PROPERTY));
      checkUpdatedTime(_ctx, customsDeclaration.getProperty(CustomsDeclaration.UPDATED_TIME_PROPERTY), newLocation(_parentLocation, CustomsDeclaration.UPDATED_TIME_PROPERTY));
    }

    public void checkDeclarationNumber(UserContext _ctx, String declarationNumber, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, declarationNumber);
    if((declarationNumber == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, declarationNumber);

    }
    public void checkPortOfEntry(UserContext _ctx, String portOfEntry, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, portOfEntry);
    if((portOfEntry == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, portOfEntry);

    }
    public void checkCountryOfOrigin(UserContext _ctx, String countryOfOrigin, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, countryOfOrigin);
    if((countryOfOrigin == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, countryOfOrigin);

    }
    public void checkDeclaredValue(UserContext _ctx, BigDecimal declaredValue, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, declaredValue);
    if((declaredValue == null)){
        return;
    }
    }
    public void checkStatus(UserContext _ctx, String status, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, status);
    if((status == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, status);

    }
    public void checkClearanceDate(UserContext _ctx, LocalDate clearanceDate, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, clearanceDate);
    if((clearanceDate == null)){
        return;
    }
    }
    public void checkCreatedTime(UserContext _ctx, LocalDateTime createdTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, createdTime);
    if((createdTime == null)){
        return;
    }
    }
    public void checkUpdatedTime(UserContext _ctx, LocalDateTime updatedTime, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, updatedTime);
    if((updatedTime == null)){
        return;
    }
    }
}