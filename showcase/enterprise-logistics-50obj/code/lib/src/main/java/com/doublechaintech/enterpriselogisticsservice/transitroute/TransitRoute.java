package com.doublechaintech.enterpriselogisticsservice.transitroute;

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

    public static final String ROUTE_CODE_PROPERTY = "routeCode";
    public static final String ORIGIN_CITY_PROPERTY = "originCity";
    public static final String DESTINATION_CITY_PROPERTY = "destinationCity";
    public static final String DISTANCE_KM_PROPERTY = "distanceKm";
    public static final String ESTIMATED_DURATION_HOURS_PROPERTY = "estimatedDurationHours";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String routeCode;
    private String originCity;
    private String destinationCity;
    private BigDecimal distanceKm;
    private BigDecimal estimatedDurationHours;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getRouteCode(){
        return this.routeCode;
    }
    public String getOriginCity(){
        return this.originCity;
    }
    public String getDestinationCity(){
        return this.destinationCity;
    }
    public BigDecimal getDistanceKm(){
        return this.distanceKm;
    }
    public BigDecimal getEstimatedDurationHours(){
        return this.estimatedDurationHours;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public TransitRoute updateRouteCode(String routeCode){
        routeCode = (routeCode == null ? null : routeCode.trim());
        if(Objects.equals(this.routeCode, routeCode)){
            return this;
        }
        handleUpdate(ROUTE_CODE_PROPERTY, getRouteCode(), routeCode);
        this.routeCode = routeCode;
        return this;
    }
    public TransitRoute updateOriginCity(String originCity){
        originCity = (originCity == null ? null : originCity.trim());
        if(Objects.equals(this.originCity, originCity)){
            return this;
        }
        handleUpdate(ORIGIN_CITY_PROPERTY, getOriginCity(), originCity);
        this.originCity = originCity;
        return this;
    }
    public TransitRoute updateDestinationCity(String destinationCity){
        destinationCity = (destinationCity == null ? null : destinationCity.trim());
        if(Objects.equals(this.destinationCity, destinationCity)){
            return this;
        }
        handleUpdate(DESTINATION_CITY_PROPERTY, getDestinationCity(), destinationCity);
        this.destinationCity = destinationCity;
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
    public TransitRoute updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public TransitRoute updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
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
            case "routeCode": this.routeCode = (value == null ? null : ((String)value).trim()); break;

            case "originCity": this.originCity = (value == null ? null : ((String)value).trim()); break;

            case "destinationCity": this.destinationCity = (value == null ? null : ((String)value).trim()); break;

            case "distanceKm": this.distanceKm = (BigDecimal) value; break;

            case "estimatedDurationHours": this.estimatedDurationHours = (BigDecimal) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "routeCode": return this.routeCode;
            case "originCity": return this.originCity;
            case "destinationCity": return this.destinationCity;
            case "distanceKm": return this.distanceKm;
            case "estimatedDurationHours": return this.estimatedDurationHours;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}