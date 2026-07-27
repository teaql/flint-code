package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
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
public class CustomerContact extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "CustomerContact";

    public static final String FIRST_NAME_PROPERTY = "firstName";
    public static final String LAST_NAME_PROPERTY = "lastName";
    public static final String EMAIL_PROPERTY = "email";
    public static final String PHONE_PROPERTY = "phone";
    public static final String IS_PRIMARY_PROPERTY = "isPrimary";
    public static final String PRIVATE_CUSTOMER_PROPERTY = "privateCustomer";
    public static final String CORPORATE_CUSTOMER_PROPERTY = "corporateCustomer";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean isPrimary;
    private PrivateCustomer privateCustomer;
    private CorporateCustomer corporateCustomer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){
        return this.phone;
    }
    public Boolean isIsPrimary(){
        return this.isPrimary;
    }
    public PrivateCustomer getPrivateCustomer(){
        return this.privateCustomer;
    }
    public CorporateCustomer getCorporateCustomer(){
        return this.corporateCustomer;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public CustomerContact updateFirstName(String firstName){
        firstName = (firstName == null ? null : firstName.trim());
        if(Objects.equals(this.firstName, firstName)){
            return this;
        }
        handleUpdate(FIRST_NAME_PROPERTY, getFirstName(), firstName);
        this.firstName = firstName;
        return this;
    }
    public CustomerContact updateLastName(String lastName){
        lastName = (lastName == null ? null : lastName.trim());
        if(Objects.equals(this.lastName, lastName)){
            return this;
        }
        handleUpdate(LAST_NAME_PROPERTY, getLastName(), lastName);
        this.lastName = lastName;
        return this;
    }
    public CustomerContact updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public CustomerContact updatePhone(String phone){
        phone = (phone == null ? null : phone.trim());
        if(Objects.equals(this.phone, phone)){
            return this;
        }
        handleUpdate(PHONE_PROPERTY, getPhone(), phone);
        this.phone = phone;
        return this;
    }
    public CustomerContact updateIsPrimary(Boolean isPrimary){
        if(Objects.equals(this.isPrimary, isPrimary)){
            return this;
        }
        handleUpdate(IS_PRIMARY_PROPERTY, isIsPrimary(), isPrimary);
        this.isPrimary = isPrimary;
        return this;
    }
    public CustomerContact updatePrivateCustomer(PrivateCustomer privateCustomer){
        if(Objects.equals(this.privateCustomer, privateCustomer)){
            return this;
        }
        handleUpdate(PRIVATE_CUSTOMER_PROPERTY, getPrivateCustomer(), privateCustomer);
        this.privateCustomer = privateCustomer;
        return this;
    }
    public CustomerContact updateCorporateCustomer(CorporateCustomer corporateCustomer){
        if(Objects.equals(this.corporateCustomer, corporateCustomer)){
            return this;
        }
        handleUpdate(CORPORATE_CUSTOMER_PROPERTY, getCorporateCustomer(), corporateCustomer);
        this.corporateCustomer = corporateCustomer;
        return this;
    }
    public CustomerContact updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public CustomerContact updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static CustomerContact refer(Long id){
        CustomerContact refer = new CustomerContact();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public CustomerContact comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<CustomerContact> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "firstName": this.firstName = (value == null ? null : ((String)value).trim()); break;

            case "lastName": this.lastName = (value == null ? null : ((String)value).trim()); break;

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (value == null ? null : ((String)value).trim()); break;

            case "isPrimary": this.isPrimary = (Boolean) value; break;

            case "privateCustomer": this.privateCustomer = (PrivateCustomer) value; break;

            case "corporateCustomer": this.corporateCustomer = (CorporateCustomer) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "firstName": return this.firstName;
            case "lastName": return this.lastName;
            case "email": return this.email;
            case "phone": return this.phone;
            case "isPrimary": return this.isPrimary;
            case "privateCustomer": return this.privateCustomer;
            case "corporateCustomer": return this.corporateCustomer;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}