package com.doublechaintech.enterpriselogisticsservice.field;

import io.teaql.core.UserContext;
import io.teaql.core.checker.Checker;
import io.teaql.core.checker.ObjectLocation;

public class FieldChecker implements Checker<Field>{

    public String type(){
        return Field.INTERNAL_TYPE;
    }

    public void checkAndFix(UserContext _ctx, Field field, ObjectLocation _parentLocation){
        if(needCheck(_ctx, field)){
            markAsChecked(_ctx, field);
            doCheck(_ctx, field, _parentLocation);
        }
    }

    public void doCheck(UserContext _ctx, Field field, ObjectLocation _parentLocation){
      if((field == null)){
         return;
      }
      if(field.newItem()){
      }else if(field.updateItem()){
      }
      checkName(_ctx, field.getProperty(Field.NAME_PROPERTY), newLocation(_parentLocation, Field.NAME_PROPERTY));
    }

    public void checkName(UserContext _ctx, String name, ObjectLocation _parentLocation){
    requiredCheck(_ctx, _parentLocation, name);
    if((name == null)){
        return;
    }
    maxStringCheck(_ctx, _parentLocation, 100, name);

    }
}