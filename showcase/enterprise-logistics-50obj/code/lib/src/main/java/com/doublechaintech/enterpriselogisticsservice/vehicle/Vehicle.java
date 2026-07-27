package com.doublechaintech.enterpriselogisticsservice.vehicle;

import com.doublechaintech.enterpriselogisticsservice.dispatchplan.DispatchPlan;
import com.doublechaintech.enterpriselogisticsservice.driverassignment.DriverAssignment;
import com.doublechaintech.enterpriselogisticsservice.fuellog.FuelLog;
import com.doublechaintech.enterpriselogisticsservice.telematicsdevice.TelematicsDevice;
import com.doublechaintech.enterpriselogisticsservice.vehiclemaintenance.VehicleMaintenance;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * [TEAQL AI WARNING]
 * TeaQL was explicitly designed to PREVENT AI hallucinations and random guessing.
 * DO NOT GUESS METHOD NAMES!
 * The methods listed below are the ONLY valid ways to interact with this entity.
 * If you encounter compilation errors (e.g., method not found), DO NOT guess another method name.
 * Read the method signatures in this file before proceeding.
 */
public class Vehicle extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "Vehicle";

    public static final String PLATE_NUMBER_PROPERTY = "plateNumber";
    public static final String VIN_PROPERTY = "vin";
    public static final String MAKE_PROPERTY = "make";
    public static final String MODEL_PROPERTY = "model";
    public static final String YEAR_PROPERTY = "year";
    public static final String CAPACITY_KG_PROPERTY = "capacityKg";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String DISPATCH_PLAN_LIST_PROPERTY = "dispatchPlanList";
    public static final String TELEMATICS_DEVICE_LIST_PROPERTY = "telematicsDeviceList";
    public static final String FUEL_LOG_LIST_PROPERTY = "fuelLogList";
    public static final String VEHICLE_MAINTENANCE_LIST_PROPERTY = "vehicleMaintenanceList";
    public static final String DRIVER_ASSIGNMENT_LIST_PROPERTY = "driverAssignmentList";
    private String plateNumber;
    private String vin;
    private String make;
    private String model;
    private Integer year;
    private Integer capacityKg;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<DispatchPlan> dispatchPlanList;
    private SmartList<TelematicsDevice> telematicsDeviceList;
    private SmartList<FuelLog> fuelLogList;
    private SmartList<VehicleMaintenance> vehicleMaintenanceList;
    private SmartList<DriverAssignment> driverAssignmentList;

    public String getPlateNumber(){
        return this.plateNumber;
    }
    public String getVin(){
        return this.vin;
    }
    public String getMake(){
        return this.make;
    }
    public String getModel(){
        return this.model;
    }
    public Integer getYear(){
        return this.year;
    }
    public Integer getCapacityKg(){
        return this.capacityKg;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SmartList<DispatchPlan> getDispatchPlanList(){
        return this.dispatchPlanList;
    }
    public SmartList<TelematicsDevice> getTelematicsDeviceList(){
        return this.telematicsDeviceList;
    }
    public SmartList<FuelLog> getFuelLogList(){
        return this.fuelLogList;
    }
    public SmartList<VehicleMaintenance> getVehicleMaintenanceList(){
        return this.vehicleMaintenanceList;
    }
    public SmartList<DriverAssignment> getDriverAssignmentList(){
        return this.driverAssignmentList;
    }
    public Vehicle updatePlateNumber(String plateNumber){
        plateNumber = (plateNumber == null ? null : plateNumber.trim());
        if(Objects.equals(this.plateNumber, plateNumber)){
            return this;
        }
        handleUpdate(PLATE_NUMBER_PROPERTY, getPlateNumber(), plateNumber);
        this.plateNumber = plateNumber;
        return this;
    }
    public Vehicle updateVin(String vin){
        vin = (vin == null ? null : vin.trim());
        if(Objects.equals(this.vin, vin)){
            return this;
        }
        handleUpdate(VIN_PROPERTY, getVin(), vin);
        this.vin = vin;
        return this;
    }
    public Vehicle updateMake(String make){
        make = (make == null ? null : make.trim());
        if(Objects.equals(this.make, make)){
            return this;
        }
        handleUpdate(MAKE_PROPERTY, getMake(), make);
        this.make = make;
        return this;
    }
    public Vehicle updateModel(String model){
        model = (model == null ? null : model.trim());
        if(Objects.equals(this.model, model)){
            return this;
        }
        handleUpdate(MODEL_PROPERTY, getModel(), model);
        this.model = model;
        return this;
    }
    public Vehicle updateYear(Integer year){
        if(Objects.equals(this.year, year)){
            return this;
        }
        handleUpdate(YEAR_PROPERTY, getYear(), year);
        this.year = year;
        return this;
    }
    public Vehicle updateCapacityKg(Integer capacityKg){
        if(Objects.equals(this.capacityKg, capacityKg)){
            return this;
        }
        handleUpdate(CAPACITY_KG_PROPERTY, getCapacityKg(), capacityKg);
        this.capacityKg = capacityKg;
        return this;
    }
    public Vehicle updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public Vehicle updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public Vehicle updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public Vehicle addDispatchPlan(DispatchPlan dispatchPlan){
        if (dispatchPlan == null){
            return this;
        }

        if(null == this.dispatchPlanList){
            this.dispatchPlanList = new SmartList<>();
        }

        this.dispatchPlanList.add(dispatchPlan);
        dispatchPlan.cacheRelation(DispatchPlan.VEHICLE_PROPERTY, this);
        return this;
    }
    public Vehicle addTelematicsDevice(TelematicsDevice telematicsDevice){
        if (telematicsDevice == null){
            return this;
        }

        if(null == this.telematicsDeviceList){
            this.telematicsDeviceList = new SmartList<>();
        }

        this.telematicsDeviceList.add(telematicsDevice);
        telematicsDevice.cacheRelation(TelematicsDevice.VEHICLE_PROPERTY, this);
        return this;
    }
    public Vehicle addFuelLog(FuelLog fuelLog){
        if (fuelLog == null){
            return this;
        }

        if(null == this.fuelLogList){
            this.fuelLogList = new SmartList<>();
        }

        this.fuelLogList.add(fuelLog);
        fuelLog.cacheRelation(FuelLog.VEHICLE_PROPERTY, this);
        return this;
    }
    public Vehicle addVehicleMaintenance(VehicleMaintenance vehicleMaintenance){
        if (vehicleMaintenance == null){
            return this;
        }

        if(null == this.vehicleMaintenanceList){
            this.vehicleMaintenanceList = new SmartList<>();
        }

        this.vehicleMaintenanceList.add(vehicleMaintenance);
        vehicleMaintenance.cacheRelation(VehicleMaintenance.VEHICLE_PROPERTY, this);
        return this;
    }
    public Vehicle addDriverAssignment(DriverAssignment driverAssignment){
        if (driverAssignment == null){
            return this;
        }

        if(null == this.driverAssignmentList){
            this.driverAssignmentList = new SmartList<>();
        }

        this.driverAssignmentList.add(driverAssignment);
        driverAssignment.cacheRelation(DriverAssignment.VEHICLE_PROPERTY, this);
        return this;
    }

    public static Vehicle refer(Long id){
        Vehicle refer = new Vehicle();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public Vehicle comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<Vehicle> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "plateNumber": this.plateNumber = (value == null ? null : ((String)value).trim()); break;

            case "vin": this.vin = (value == null ? null : ((String)value).trim()); break;

            case "make": this.make = (value == null ? null : ((String)value).trim()); break;

            case "model": this.model = (value == null ? null : ((String)value).trim()); break;

            case "year": this.year = (Integer) value; break;

            case "capacityKg": this.capacityKg = (Integer) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "dispatchPlanList": this.dispatchPlanList = (SmartList<DispatchPlan>) value; break;
            case "telematicsDeviceList": this.telematicsDeviceList = (SmartList<TelematicsDevice>) value; break;
            case "fuelLogList": this.fuelLogList = (SmartList<FuelLog>) value; break;
            case "vehicleMaintenanceList": this.vehicleMaintenanceList = (SmartList<VehicleMaintenance>) value; break;
            case "driverAssignmentList": this.driverAssignmentList = (SmartList<DriverAssignment>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "plateNumber": return this.plateNumber;
            case "vin": return this.vin;
            case "make": return this.make;
            case "model": return this.model;
            case "year": return this.year;
            case "capacityKg": return this.capacityKg;
            case "status": return this.status;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "dispatchPlanList": return this.dispatchPlanList;
            case "telematicsDeviceList": return this.telematicsDeviceList;
            case "fuelLogList": return this.fuelLogList;
            case "vehicleMaintenanceList": return this.vehicleMaintenanceList;
            case "driverAssignmentList": return this.driverAssignmentList;
            default: return super.__internalGet(property);
        }
    }

}