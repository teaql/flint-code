package com.doublechaintech.enterpriselogisticsservice.pickupaddress;

import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
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

    public static final String ADDRESS_ID_PROPERTY = "addressId";
    public static final String MOVING_ORDER_PROPERTY = "movingOrder";
    public static final String ADDRESS_LINE1_PROPERTY = "addressLine1";
    public static final String ADDRESS_LINE2_PROPERTY = "addressLine2";
    public static final String CITY_PROPERTY = "city";
    public static final String STATE_PROPERTY = "state";
    public static final String ZIP_CODE_PROPERTY = "zipCode";
    public static final String COUNTRY_PROPERTY = "country";
    public static final String CONTACT_NAME_PROPERTY = "contactName";
    public static final String CONTACT_PHONE_PROPERTY = "contactPhone";
    public static final String CREATE_TIME_PROPERTY = "createTime";
    private String addressId;
    private MovingOrder movingOrder;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String contactName;
    private String contactPhone;
    private LocalDateTime createTime;

    public String getAddressId(){
        return this.addressId;
    }
    public MovingOrder getMovingOrder(){
        return this.movingOrder;
    }
    public String getAddressLine1(){
        return this.addressLine1;
    }
    public String getAddressLine2(){
        return this.addressLine2;
    }
    public String getCity(){
        return this.city;
    }
    public String getState(){
        return this.state;
    }
    public String getZipCode(){
        return this.zipCode;
    }
    public String getCountry(){
        return this.country;
    }
    public String getContactName(){
        return this.contactName;
    }
    public String getContactPhone(){
        return this.contactPhone;
    }
    public LocalDateTime getCreateTime(){
        return this.createTime;
    }
    public PickupAddress updateAddressId(String addressId){
        addressId = (addressId == null ? null : addressId.trim());
        if(Objects.equals(this.addressId, addressId)){
            return this;
        }
        handleUpdate(ADDRESS_ID_PROPERTY, getAddressId(), addressId);
        this.addressId = addressId;
        return this;
    }
    public PickupAddress updateMovingOrder(MovingOrder movingOrder){
        if(Objects.equals(this.movingOrder, movingOrder)){
            return this;
        }
        handleUpdate(MOVING_ORDER_PROPERTY, getMovingOrder(), movingOrder);
        this.movingOrder = movingOrder;
        return this;
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
    public PickupAddress updateState(String state){
        state = (state == null ? null : state.trim());
        if(Objects.equals(this.state, state)){
            return this;
        }
        handleUpdate(STATE_PROPERTY, getState(), state);
        this.state = state;
        return this;
    }
    public PickupAddress updateZipCode(String zipCode){
        zipCode = (zipCode == null ? null : zipCode.trim());
        if(Objects.equals(this.zipCode, zipCode)){
            return this;
        }
        handleUpdate(ZIP_CODE_PROPERTY, getZipCode(), zipCode);
        this.zipCode = zipCode;
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
    public PickupAddress updateContactName(String contactName){
        contactName = (contactName == null ? null : contactName.trim());
        if(Objects.equals(this.contactName, contactName)){
            return this;
        }
        handleUpdate(CONTACT_NAME_PROPERTY, getContactName(), contactName);
        this.contactName = contactName;
        return this;
    }
    public PickupAddress updateContactPhone(String contactPhone){
        contactPhone = (contactPhone == null ? null : contactPhone.trim());
        if(Objects.equals(this.contactPhone, contactPhone)){
            return this;
        }
        handleUpdate(CONTACT_PHONE_PROPERTY, getContactPhone(), contactPhone);
        this.contactPhone = contactPhone;
        return this;
    }
    public PickupAddress updateCreateTime(LocalDateTime createTime){
        if(Objects.equals(this.createTime, createTime)){
            return this;
        }
        handleUpdate(CREATE_TIME_PROPERTY, getCreateTime(), createTime);
        this.createTime = createTime;
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
            case "addressId": this.addressId = (value == null ? null : ((String)value).trim()); break;

            case "movingOrder": this.movingOrder = (MovingOrder) value; break;

            case "addressLine1": this.addressLine1 = (value == null ? null : ((String)value).trim()); break;

            case "addressLine2": this.addressLine2 = (value == null ? null : ((String)value).trim()); break;

            case "city": this.city = (value == null ? null : ((String)value).trim()); break;

            case "state": this.state = (value == null ? null : ((String)value).trim()); break;

            case "zipCode": this.zipCode = (value == null ? null : ((String)value).trim()); break;

            case "country": this.country = (value == null ? null : ((String)value).trim()); break;

            case "contactName": this.contactName = (value == null ? null : ((String)value).trim()); break;

            case "contactPhone": this.contactPhone = (value == null ? null : ((String)value).trim()); break;

            case "createTime": this.createTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "addressId": return this.addressId;
            case "movingOrder": return this.movingOrder;
            case "addressLine1": return this.addressLine1;
            case "addressLine2": return this.addressLine2;
            case "city": return this.city;
            case "state": return this.state;
            case "zipCode": return this.zipCode;
            case "country": return this.country;
            case "contactName": return this.contactName;
            case "contactPhone": return this.contactPhone;
            case "createTime": return this.createTime;
            default: return super.__internalGet(property);
        }
    }

}