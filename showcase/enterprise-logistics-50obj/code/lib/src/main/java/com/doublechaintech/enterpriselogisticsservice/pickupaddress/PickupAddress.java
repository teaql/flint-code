package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import io.teaql.core.SmartList;
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
public class PickupAddress extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "PickupAddress";

    public static final String ADDRESS_LINE1_PROPERTY = "addressLine1";
    public static final String ADDRESS_LINE2_PROPERTY = "addressLine2";
    public static final String CITY_PROPERTY = "city";
    public static final String STATE_PROVINCE_PROPERTY = "stateProvince";
    public static final String POSTAL_CODE_PROPERTY = "postalCode";
    public static final String COUNTRY_PROPERTY = "country";
    public static final String LATITUDE_PROPERTY = "latitude";
    public static final String LONGITUDE_PROPERTY = "longitude";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    public static final String MOVING_ORDER_LIST_AS_PICKUP_ADDRESS_PROPERTY = "movingOrderListAsPickupAddress";
    public static final String MOVING_ORDER_LIST_AS_DELIVERY_ADDRESS_PROPERTY = "movingOrderListAsDeliveryAddress";
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
    private BigDecimal latitude;
    private String longitude;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private SmartList<MovingOrder> movingOrderListAsPickupAddress;
    private SmartList<MovingOrder> movingOrderListAsDeliveryAddress;

    public String getAddressLine1(){
        return this.addressLine1;
    }
    public String getAddressLine2(){
        return this.addressLine2;
    }
    public String getCity(){
        return this.city;
    }
    public String getStateProvince(){
        return this.stateProvince;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public String getCountry(){
        return this.country;
    }
    public BigDecimal getLatitude(){
        return this.latitude;
    }
    public String getLongitude(){
        return this.longitude;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public SmartList<MovingOrder> getMovingOrderListAsPickupAddress(){
        return this.movingOrderListAsPickupAddress;
    }
    public SmartList<MovingOrder> getMovingOrderListAsDeliveryAddress(){
        return this.movingOrderListAsDeliveryAddress;
    }
    public PickupAddress updateAddressLine1(String addressLine1){
        addressLine1 = (addressLine1 == null ? null : addressLine1.trim());
        if(Objects.equals(this.addressLine1, addressLine1)){
            return this;
        }
        handleUpdate(ADDRESS_LINE1_PROPERTY, getAddressLine1(), addressLine1);
        this.addressLine1 = addressLine1;
        return this;
    }
    public PickupAddress updateAddressLine2(String addressLine2){
        addressLine2 = (addressLine2 == null ? null : addressLine2.trim());
        if(Objects.equals(this.addressLine2, addressLine2)){
            return this;
        }
        handleUpdate(ADDRESS_LINE2_PROPERTY, getAddressLine2(), addressLine2);
        this.addressLine2 = addressLine2;
        return this;
    }
    public PickupAddress updateCity(String city){
        city = (city == null ? null : city.trim());
        if(Objects.equals(this.city, city)){
            return this;
        }
        handleUpdate(CITY_PROPERTY, getCity(), city);
        this.city = city;
        return this;
    }
    public PickupAddress updateStateProvince(String stateProvince){
        stateProvince = (stateProvince == null ? null : stateProvince.trim());
        if(Objects.equals(this.stateProvince, stateProvince)){
            return this;
        }
        handleUpdate(STATE_PROVINCE_PROPERTY, getStateProvince(), stateProvince);
        this.stateProvince = stateProvince;
        return this;
    }
    public PickupAddress updatePostalCode(String postalCode){
        postalCode = (postalCode == null ? null : postalCode.trim());
        if(Objects.equals(this.postalCode, postalCode)){
            return this;
        }
        handleUpdate(POSTAL_CODE_PROPERTY, getPostalCode(), postalCode);
        this.postalCode = postalCode;
        return this;
    }
    public PickupAddress updateCountry(String country){
        country = (country == null ? null : country.trim());
        if(Objects.equals(this.country, country)){
            return this;
        }
        handleUpdate(COUNTRY_PROPERTY, getCountry(), country);
        this.country = country;
        return this;
    }
    public PickupAddress updateLatitude(BigDecimal latitude){
        if(Objects.equals(this.latitude, latitude)){
            return this;
        }
        handleUpdate(LATITUDE_PROPERTY, getLatitude(), latitude);
        this.latitude = latitude;
        return this;
    }
    public PickupAddress updateLongitude(String longitude){
        longitude = (longitude == null ? null : longitude.trim());
        if(Objects.equals(this.longitude, longitude)){
            return this;
        }
        handleUpdate(LONGITUDE_PROPERTY, getLongitude(), longitude);
        this.longitude = longitude;
        return this;
    }
    public PickupAddress updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public PickupAddress updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }
    public PickupAddress addMovingOrderAsPickupAddress(MovingOrder movingOrder){
        if (movingOrder == null){
            return this;
        }

        if(null == this.movingOrderListAsPickupAddress){
            this.movingOrderListAsPickupAddress = new SmartList<>();
        }

        this.movingOrderListAsPickupAddress.add(movingOrder);
        movingOrder.cacheRelation(MovingOrder.PICKUP_ADDRESS_PROPERTY, this);
        return this;
    }
    public PickupAddress addMovingOrderAsDeliveryAddress(MovingOrder movingOrder){
        if (movingOrder == null){
            return this;
        }

        if(null == this.movingOrderListAsDeliveryAddress){
            this.movingOrderListAsDeliveryAddress = new SmartList<>();
        }

        this.movingOrderListAsDeliveryAddress.add(movingOrder);
        movingOrder.cacheRelation(MovingOrder.DELIVERY_ADDRESS_PROPERTY, this);
        return this;
    }

    public static PickupAddress refer(Long id){
        PickupAddress refer = new PickupAddress();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public PickupAddress comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<PickupAddress> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "addressLine1": this.addressLine1 = (value == null ? null : ((String)value).trim()); break;

            case "addressLine2": this.addressLine2 = (value == null ? null : ((String)value).trim()); break;

            case "city": this.city = (value == null ? null : ((String)value).trim()); break;

            case "stateProvince": this.stateProvince = (value == null ? null : ((String)value).trim()); break;

            case "postalCode": this.postalCode = (value == null ? null : ((String)value).trim()); break;

            case "country": this.country = (value == null ? null : ((String)value).trim()); break;

            case "latitude": this.latitude = (BigDecimal) value; break;

            case "longitude": this.longitude = (value == null ? null : ((String)value).trim()); break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            case "movingOrderListAsPickupAddress": this.movingOrderListAsPickupAddress = (SmartList<MovingOrder>) value; break;
            case "movingOrderListAsDeliveryAddress": this.movingOrderListAsDeliveryAddress = (SmartList<MovingOrder>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "addressLine1": return this.addressLine1;
            case "addressLine2": return this.addressLine2;
            case "city": return this.city;
            case "stateProvince": return this.stateProvince;
            case "postalCode": return this.postalCode;
            case "country": return this.country;
            case "latitude": return this.latitude;
            case "longitude": return this.longitude;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            case "movingOrderListAsPickupAddress": return this.movingOrderListAsPickupAddress;
            case "movingOrderListAsDeliveryAddress": return this.movingOrderListAsDeliveryAddress;
            default: return super.__internalGet(property);
        }
    }

}