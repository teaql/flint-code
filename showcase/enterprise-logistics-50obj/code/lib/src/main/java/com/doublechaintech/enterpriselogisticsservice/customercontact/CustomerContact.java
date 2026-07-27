package com.doublechaintech.enterpriselogisticsservice.customercontact;

import com.doublechaintech.enterpriselogisticsservice.corporatecustomer.CorporateCustomer;
import com.doublechaintech.enterpriselogisticsservice.privatecustomer.PrivateCustomer;
import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
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

    public static final String NAME_PROPERTY = "name";
    public static final String PHONE_PROPERTY = "phone";
    public static final String EMAIL_PROPERTY = "email";
    public static final String RELATIONSHIP_PROPERTY = "relationship";
    public static final String PRIVATE_CUSTOMER_PROPERTY = "privateCustomer";
    public static final String CORPORATE_CUSTOMER_PROPERTY = "corporateCustomer";
    private String name;
    private String phone;
    private String email;
    private String relationship;
    private PrivateCustomer privateCustomer;
    private CorporateCustomer corporateCustomer;

    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
    public String getEmail(){
        return this.email;
    }
    public String getRelationship(){
        return this.relationship;
    }
    public PrivateCustomer getPrivateCustomer(){
        return this.privateCustomer;
    }
    public CorporateCustomer getCorporateCustomer(){
        return this.corporateCustomer;
    }
    public CustomerContact updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
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
    public CustomerContact updateEmail(String email){
        email = (email == null ? null : email.trim());
        if(Objects.equals(this.email, email)){
            return this;
        }
        handleUpdate(EMAIL_PROPERTY, getEmail(), email);
        this.email = email;
        return this;
    }
    public CustomerContact updateRelationship(String relationship){
        relationship = (relationship == null ? null : relationship.trim());
        if(Objects.equals(this.relationship, relationship)){
            return this;
        }
        handleUpdate(RELATIONSHIP_PROPERTY, getRelationship(), relationship);
        this.relationship = relationship;
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
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "phone": this.phone = (value == null ? null : ((String)value).trim()); break;

            case "email": this.email = (value == null ? null : ((String)value).trim()); break;

            case "relationship": this.relationship = (value == null ? null : ((String)value).trim()); break;

            case "privateCustomer": this.privateCustomer = (PrivateCustomer) value; break;

            case "corporateCustomer": this.corporateCustomer = (CorporateCustomer) value; break;

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
            case "relationship": return this.relationship;
            case "privateCustomer": return this.privateCustomer;
            case "corporateCustomer": return this.corporateCustomer;
            default: return super.__internalGet(property);
        }
    }

}