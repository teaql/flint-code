package com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance;

import io.teaql.core.SmartList;
import io.teaql.core.value.Expression;
import io.teaql.core.value.SmartListExpression;
import java.util.function.Function;

public class VehicleMaintenanceListExpression<T, E, U extends VehicleMaintenance> extends SmartListExpression<T, E, U> {
    public VehicleMaintenanceListExpression(Expression<T, SmartList<U>> expression){
        super(expression);
    }

    public VehicleMaintenanceListExpression(Expression<T, E> expression, Function<E, SmartList<U>> function){
        super(expression, function);
    }

    public VehicleMaintenanceExpression<T, U, U> first() {
       return new VehicleMaintenanceExpression(super.first());
    }

    public VehicleMaintenanceExpression<T, U, U> get(int index) {
      return new VehicleMaintenanceExpression(super.get(index));
    }
}