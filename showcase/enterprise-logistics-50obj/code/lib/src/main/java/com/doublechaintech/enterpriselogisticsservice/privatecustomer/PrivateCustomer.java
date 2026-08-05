package com.doublechaintech.enterpriselogisticsservice.privatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.customerloyalty.CustomerLoyalty;
import com.doublechaintech.enterpriselogisticsservice.movingorder.MovingOrder;
import com.doublechaintech.enterpriselogisticsservice.servicequote.ServiceQuote;
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
public class PrivateCustomer extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "PrivateCustomer";

    public static final String NAME_PROPERTY = "name";
    public static final String PHONE_PROPERTY = "phone";
    public static final String EMAIL_PROPERTY = "email";
    public static final String ADDRESS_LINE1_PROPERTY = "addressLine1";
    public static final String ADDRESS_LINE2_PROPERTY = "addressLine2";
    public static final String CITY_PROPERTY = "city";
    public static final String STATE_PROPERTY = "state";
    public static final String ZIP_CODE_PROPERTY = "zipCode";
    public static final String COUNTRY_PROPERTY = "country";
    public static final String CUSTOMER_TYPE_PROPERTY = "customerType";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String MOVING_ORDER_LIST_PROPERTY = "movingOrderList";
    public static final String CUSTOMER_CONTACT_LIST_PROPERTY = "customerContactList";
    public static final String SERVICE_QUOTE_LIST_PROPERTY = "serviceQuoteList";
    public static final String CUSTOMER_LOYALTY_LIST_PROPERTY = "customerLoyaltyList";
    private String name;
    private String phone;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String customerType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<MovingOrder> movingOrderList;
    private SmartList<CustomerContact> customerContactList;
    private SmartList<ServiceQuote> serviceQuoteList;
    private SmartList<CustomerLoyalty> customerLoyaltyList;

    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
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
    public String getCustomerType(){
        return this.customerType;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public SmartList<MovingOrder> getMovingOrderList(){
        return this.movingOrderList;
    }
    public SmartList<CustomerContact> getCustomerContactList(){
        return this.customerContactList;
    }
    public SmartList<ServiceQuote> getServiceQuoteList(){
        return this.serviceQuoteList;
    }
    public SmartList<CustomerLoyalty> getCustomerLoyaltyList(){
        return this.customerLoyaltyList;
    }
    public PrivateCustomer updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public PrivateCustomer updatePhone(String phone){
        phone = (phone == null ? null : phone.trim());
        if(Objects.equals(this.phone, phone)){
            return this;
        }
        handleUpdate(PHONE_PROPERTY, getPhone(), phone);
        this.phone = phone;
        return this;
    }
    public PrivateCustomer updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public PrivateCustomer updateAddressLine1(String addressLine1){
        addressLine1 = (addressLine1 == null ? null : addressLine1.trim());
        if(Objects.equals(this.addressLine1, addressLine1)){
            return this;
        }
        handleUpdate(ADDRESS_LINE1_PROPERTY, getAddressLine1(), addressLine1);
        this.addressLine1 = addressLine1;
        return this;
    }
    public PrivateCustomer updateAddressLine2(String addressLine2){
        addressLine2 = (addressLine2 == null ? null : addressLine2.trim());
        if(Objects.equals(this.addressLine2, addressLine2)){
            return this;
        }
        handleUpdate(ADDRESS_LINE2_PROPERTY, getAddressLine2(), addressLine2);
        this.addressLine2 = addressLine2;
        return this;
    }
    public PrivateCustomer updateCity(String city){
        city = (city == null ? null : city.trim());
        if(Objects.equals(this.city, city)){
            return this;
        }
        handleUpdate(CITY_PROPERTY, getCity(), city);
        this.city = city;
        return this;
    }
    public PrivateCustomer updateState(String state){
        state = (state == null ? null : state.trim());
        if(Objects.equals(this.state, state)){
            return this;
        }
        handleUpdate(STATE_PROPERTY, getState(), state);
        this.state = state;
        return this;
    }
    public PrivateCustomer updateZipCode(String zipCode){
        zipCode = (zipCode == null ? null : zipCode.trim());
        if(Objects.equals(this.zipCode, zipCode)){
            return this;
        }
        handleUpdate(ZIP_CODE_PROPERTY, getZipCode(), zipCode);
        this.zipCode = zipCode;
        return this;
    }
    public PrivateCustomer updateCountry(String country){
        country = (country == null ? null : country.trim());
        if(Objects.equals(this.country, country)){
            return this;
        }
        handleUpdate(COUNTRY_PROPERTY, getCountry(), country);
        this.country = country;
        return this;
    }
    public PrivateCustomer updateCustomerType(String customerType){
        customerType = (customerType == null ? null : customerType.trim());
        if(Objects.equals(this.customerType, customerType)){
            return this;
        }
        handleUpdate(CUSTOMER_TYPE_PROPERTY, getCustomerType(), customerType);
        this.customerType = customerType;
        return this;
    }
    public PrivateCustomer updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public PrivateCustomer updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public PrivateCustomer addMovingOrder(MovingOrder movingOrder){
        if (movingOrder == null){
            return this;
        }

        if(null == this.movingOrderList){
            this.movingOrderList = new SmartList<>();
        }

        this.movingOrderList.add(movingOrder);
        movingOrder.cacheRelation(MovingOrder.CUSTOMER_PROPERTY, this);
        return this;
    }
    public PrivateCustomer addCustomerContact(CustomerContact customerContact){
        if (customerContact == null){
            return this;
        }

        if(null == this.customerContactList){
            this.customerContactList = new SmartList<>();
        }

        this.customerContactList.add(customerContact);
        customerContact.cacheRelation(CustomerContact.PRIVATE_CUSTOMER_PROPERTY, this);
        return this;
    }
    public PrivateCustomer addServiceQuote(ServiceQuote serviceQuote){
        if (serviceQuote == null){
            return this;
        }

        if(null == this.serviceQuoteList){
            this.serviceQuoteList = new SmartList<>();
        }

        this.serviceQuoteList.add(serviceQuote);
        serviceQuote.cacheRelation(ServiceQuote.PRIVATE_CUSTOMER_PROPERTY, this);
        return this;
    }
    public PrivateCustomer addCustomerLoyalty(CustomerLoyalty customerLoyalty){
        if (customerLoyalty == null){
            return this;
        }

        if(null == this.customerLoyaltyList){
            this.customerLoyaltyList = new SmartList<>();
        }

        this.customerLoyaltyList.add(customerLoyalty);
        customerLoyalty.cacheRelation(CustomerLoyalty.PRIVATE_CUSTOMER_PROPERTY, this);
        return this;
    }

    public static PrivateCustomer refer(Long id){
        PrivateCustomer refer = new PrivateCustomer();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public PrivateCustomer comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<PrivateCustomer> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (value == null ? null : ((String)value).trim()); break;

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "addressLine1": this.addressLine1 = (value == null ? null : ((String)value).trim()); break;

            case "addressLine2": this.addressLine2 = (value == null ? null : ((String)value).trim()); break;

            case "city": this.city = (value == null ? null : ((String)value).trim()); break;

            case "state": this.state = (value == null ? null : ((String)value).trim()); break;

            case "zipCode": this.zipCode = (value == null ? null : ((String)value).trim()); break;

            case "country": this.country = (value == null ? null : ((String)value).trim()); break;

            case "customerType": this.customerType = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "movingOrderList": this.movingOrderList = (SmartList<MovingOrder>) value; break;
            case "customerContactList": this.customerContactList = (SmartList<CustomerContact>) value; break;
            case "serviceQuoteList": this.serviceQuoteList = (SmartList<ServiceQuote>) value; break;
            case "customerLoyaltyList": this.customerLoyaltyList = (SmartList<CustomerLoyalty>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "phone": return this.phone;
            case "email": return this.email;
            case "addressLine1": return this.addressLine1;
            case "addressLine2": return this.addressLine2;
            case "city": return this.city;
            case "state": return this.state;
            case "zipCode": return this.zipCode;
            case "country": return this.country;
            case "customerType": return this.customerType;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "movingOrderList": return this.movingOrderList;
            case "customerContactList": return this.customerContactList;
            case "serviceQuoteList": return this.serviceQuoteList;
            case "customerLoyaltyList": return this.customerLoyaltyList;
            default: return super.__internalGet(property);
        }
    }

}