package com.doublechaintech.enterpriselogisticsservice.vehicle;

import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlanListExpression;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignmentListExpression;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLogListExpression;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLog;
import com.doublechaintech.enterpriselogisticsservice.gpslog.GpsLogListExpression;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDeviceListExpression;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenanceListExpression;
import io.teaql.core.UserContext;
import io.teaql.core.value.BaseEntityExpression;
import io.teaql.core.value.Expression;
import io.teaql.core.value.ExpressionAdaptor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;

public class VehicleExpression<T, E, U extends Vehicle> extends ExpressionAdaptor<T, E, U> implements BaseEntityExpression<T, U> {
    public VehicleExpression(Expression<T, U> expression){
        super(expression);
    }

    public VehicleExpression(Expression<T, E> expression, Function<E, U> function){
        super(expression, function);
    }

     public VehicleExpression<T, U, U> updateId(Long id){
        return new VehicleExpression(this, $it -> {((Vehicle)$it).__internalSet("id", id); return this;});
     }

     public VehicleExpression<T, U, U> save(UserContext userContext){
        return new VehicleExpression(this, $it -> ((Vehicle)$it).auditAs("Saved by Expression").save(userContext));
     }

     public VehicleExpression<T, U, U> save(String intent, UserContext userContext){
        return new VehicleExpression(this, $it -> ((Vehicle)$it).auditAs(intent).save(userContext));
     }

     public boolean isNull() {
        return resolve() == null;
     }


    public Expression<T, String> getName(){
       return apply(Vehicle::getName);
    }
    public VehicleExpression<T, U, U> updateName(String name){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateName(name));
    }

    public Expression<T, String> getLicensePlate(){
       return apply(Vehicle::getLicensePlate);
    }
    public VehicleExpression<T, U, U> updateLicensePlate(String licensePlate){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateLicensePlate(licensePlate));
    }

    public Expression<T, String> getMake(){
       return apply(Vehicle::getMake);
    }
    public VehicleExpression<T, U, U> updateMake(String make){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateMake(make));
    }

    public Expression<T, String> getModel(){
       return apply(Vehicle::getModel);
    }
    public VehicleExpression<T, U, U> updateModel(String model){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateModel(model));
    }

    public Expression<T, Integer> getYear(){
       return apply(Vehicle::getYear);
    }
    public VehicleExpression<T, U, U> updateYear(Integer year){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateYear(year));
    }

    public Expression<T, BigDecimal> getCapacityKg(){
       return apply(Vehicle::getCapacityKg);
    }
    public VehicleExpression<T, U, U> updateCapacityKg(BigDecimal capacityKg){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateCapacityKg(capacityKg));
    }

    public Expression<T, String> getStatus(){
       return apply(Vehicle::getStatus);
    }
    public VehicleExpression<T, U, U> updateStatus(String status){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateStatus(status));
    }

    public Expression<T, LocalDateTime> getCreatedAt(){
       return apply(Vehicle::getCreatedAt);
    }
    public VehicleExpression<T, U, U> updateCreatedAt(LocalDateTime createdAt){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateCreatedAt(createdAt));
    }

    public Expression<T, LocalDateTime> getUpdatedAt(){
       return apply(Vehicle::getUpdatedAt);
    }
    public VehicleExpression<T, U, U> updateUpdatedAt(LocalDateTime updatedAt){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).updateUpdatedAt(updatedAt));
    }

    public DispatchPlanListExpression<T, U, DispatchPlan> getDispatchPlanList(){
        return new DispatchPlanListExpression(this, $it ->  ((Vehicle)$it).getDispatchPlanList());
    }
    public DriverAssignmentListExpression<T, U, DriverAssignment> getDriverAssignmentList(){
        return new DriverAssignmentListExpression(this, $it ->  ((Vehicle)$it).getDriverAssignmentList());
    }
    public GpsLogListExpression<T, U, GpsLog> getGpsLogList(){
        return new GpsLogListExpression(this, $it ->  ((Vehicle)$it).getGpsLogList());
    }
    public FuelLogListExpression<T, U, FuelLog> getFuelLogList(){
        return new FuelLogListExpression(this, $it ->  ((Vehicle)$it).getFuelLogList());
    }
    public VehicleMaintenanceListExpression<T, U, VehicleMaintenance> getVehicleMaintenanceList(){
        return new VehicleMaintenanceListExpression(this, $it ->  ((Vehicle)$it).getVehicleMaintenanceList());
    }
    public TelematicsDeviceListExpression<T, U, TelematicsDevice> getTelematicsDeviceList(){
        return new TelematicsDeviceListExpression(this, $it ->  ((Vehicle)$it).getTelematicsDeviceList());
    }
    public VehicleExpression<T, U, U> addDispatchPlan(DispatchPlan dispatchPlan){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).addDispatchPlan(dispatchPlan));
    }
    public VehicleExpression<T, U, U> addDriverAssignment(DriverAssignment driverAssignment){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).addDriverAssignment(driverAssignment));
    }
    public VehicleExpression<T, U, U> addGpsLog(GpsLog gpsLog){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).addGpsLog(gpsLog));
    }
    public VehicleExpression<T, U, U> addFuelLog(FuelLog fuelLog){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).addFuelLog(fuelLog));
    }
    public VehicleExpression<T, U, U> addVehicleMaintenance(VehicleMaintenance vehicleMaintenance){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).addVehicleMaintenance(vehicleMaintenance));
    }
    public VehicleExpression<T, U, U> addTelematicsDevice(TelematicsDevice telematicsDevice){
       return new VehicleExpression(this, $it ->  ((Vehicle)$it).addTelematicsDevice(telematicsDevice));
    }
}