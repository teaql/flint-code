package com.doublechaintech.enterpriselogisticsservice.transitroute;

import com.doublechaintech.enterpriselogisticsservice.warehouse.Warehouse;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
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
public class TransitRoute extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "TransitRoute";

    public static final String ROUTE_ID_PROPERTY = "routeId";
    public static final String NAME_PROPERTY = "name";
    public static final String ORIGIN_WAREHOUSE_PROPERTY = "originWarehouse";
    public static final String DESTINATION_WAREHOUSE_PROPERTY = "destinationWarehouse";
    public static final String DISTANCE_KM_PROPERTY = "distanceKm";
    public static final String ESTIMATED_DURATION_HOURS_PROPERTY = "estimatedDurationHours";
    public static final String STATUS_PROPERTY = "status";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    private String routeId;
    private String name;
    private Warehouse originWarehouse;
    private Warehouse destinationWarehouse;
    private BigDecimal distanceKm;
    private BigDecimal estimatedDurationHours;
    private String status;
    private LocalDateTime createTime;

    public String getRouteId(){
        return this.routeId;
    }
    public String getName(){
        return this.name;
    }
    public Warehouse getOriginWarehouse(){
        return this.originWarehouse;
    }
    public Warehouse getDestinationWarehouse(){
        return this.destinationWarehouse;
    }
    public BigDecimal getDistanceKm(){
        return this.distanceKm;
    }
    public BigDecimal getEstimatedDurationHours(){
        return this.estimatedDurationHours;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public TransitRoute updateRouteId(String routeId){
        routeId = (routeId == null ? null : routeId.trim());
        if(Objects.equals(this.routeId, routeId)){
            return this;
        }
        handleUpdate(ROUTE_ID_PROPERTY, getRouteId(), routeId);
        this.routeId = routeId;
        return this;
    }
    public TransitRoute updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public TransitRoute updateOriginWarehouse(Warehouse originWarehouse){
        if(Objects.equals(this.originWarehouse, originWarehouse)){
            return this;
        }
        handleUpdate(ORIGIN_WAREHOUSE_PROPERTY, getOriginWarehouse(), originWarehouse);
        this.originWarehouse = originWarehouse;
        return this;
    }
    public TransitRoute updateDestinationWarehouse(Warehouse destinationWarehouse){
        if(Objects.equals(this.destinationWarehouse, destinationWarehouse)){
            return this;
        }
        handleUpdate(DESTINATION_WAREHOUSE_PROPERTY, getDestinationWarehouse(), destinationWarehouse);
        this.destinationWarehouse = destinationWarehouse;
        return this;
    }
    public TransitRoute updateDistanceKm(BigDecimal distanceKm){
        if(Objects.equals(this.distanceKm, distanceKm)){
            return this;
        }
        handleUpdate(DISTANCE_KM_PROPERTY, getDistanceKm(), distanceKm);
        this.distanceKm = distanceKm;
        return this;
    }
    public TransitRoute updateEstimatedDurationHours(BigDecimal estimatedDurationHours){
        if(Objects.equals(this.estimatedDurationHours, estimatedDurationHours)){
            return this;
        }
        handleUpdate(ESTIMATED_DURATION_HOURS_PROPERTY, getEstimatedDurationHours(), estimatedDurationHours);
        this.estimatedDurationHours = estimatedDurationHours;
        return this;
    }
    public TransitRoute updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public TransitRoute updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
        return this;
    }

    public static TransitRoute refer(Long id){
        TransitRoute refer = new TransitRoute();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public TransitRoute comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<TransitRoute> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "routeId": this.routeId = (value == null ? null : ((String)value).trim()); break;

            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "originWarehouse": this.originWarehouse = (Warehouse) value; break;

            case "destinationWarehouse": this.destinationWarehouse = (Warehouse) value; break;

            case "distanceKm": this.distanceKm = (BigDecimal) value; break;

            case "estimatedDurationHours": this.estimatedDurationHours = (BigDecimal) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "routeId": return this.routeId;
            case "name": return this.name;
            case "originWarehouse": return this.originWarehouse;
            case "destinationWarehouse": return this.destinationWarehouse;
            case "distanceKm": return this.distanceKm;
            case "estimatedDurationHours": return this.estimatedDurationHours;
            case "status": return this.status;
            case "createTime": return this.createTime;
            default: return super.__internalGet(property);
        }
    }

}