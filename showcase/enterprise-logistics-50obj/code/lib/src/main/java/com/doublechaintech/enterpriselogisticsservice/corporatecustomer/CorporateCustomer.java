package com.doublechaintech.enterpriselogisticsservice.corporatecustomer;

import com.doublechaintech.enterpriselogisticsservice.customercontact.CustomerContact;
import com.doublechaintech.enterpriselogisticsservice.servicecontract.ServiceContract;
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
public class CorporateCustomer extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "CorporateCustomer";

    public static final String NAME_PROPERTY = "name";
    public static final String REGISTRATION_NUMBER_PROPERTY = "registrationNumber";
    public static final String INDUSTRY_PROPERTY = "industry";
    public static final String EMPLOYEE_COUNT_PROPERTY = "employeeCount";
    public static final String BILLING_ADDRESS_PROPERTY = "billingAddress";
    public static final String CONTACT_EMAIL_PROPERTY = "contactEmail";
    public static final String CONTACT_PHONE_PROPERTY = "contactPhone";
    public static final String CUSTOMER_TYPE_PROPERTY = "customerType";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    public static final String CUSTOMER_CONTACT_LIST_PROPERTY = "customerContactList";
    public static final String SERVICE_QUOTE_LIST_PROPERTY = "serviceQuoteList";
    public static final String SERVICE_CONTRACT_LIST_PROPERTY = "serviceContractList";
    private String name;
    private String registrationNumber;
    private String industry;
    private Integer employeeCount;
    private String billingAddress;
    private String contactEmail;
    private String contactPhone;
    private String customerType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SmartList<CustomerContact> customerContactList;
    private SmartList<ServiceQuote> serviceQuoteList;
    private SmartList<ServiceContract> serviceContractList;

    public String getName(){
        return this.name;
    }
    public String getRegistrationNumber(){
        return this.registrationNumber;
    }
    public String getIndustry(){
        return this.industry;
    }
    public Integer getEmployeeCount(){
        return this.employeeCount;
    }
    public String getBillingAddress(){
        return this.billingAddress;
    }
    public String getContactEmail(){
        return this.contactEmail;
    }
    public String getContactPhone(){
        return this.contactPhone;
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
    public SmartList<CustomerContact> getCustomerContactList(){
        return this.customerContactList;
    }
    public SmartList<ServiceQuote> getServiceQuoteList(){
        return this.serviceQuoteList;
    }
    public SmartList<ServiceContract> getServiceContractList(){
        return this.serviceContractList;
    }
    public CorporateCustomer updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public CorporateCustomer updateRegistrationNumber(String registrationNumber){
        registrationNumber = (registrationNumber == null ? null : registrationNumber.trim());
        if(Objects.equals(this.registrationNumber, registrationNumber)){
            return this;
        }
        handleUpdate(REGISTRATION_NUMBER_PROPERTY, getRegistrationNumber(), registrationNumber);
        this.registrationNumber = registrationNumber;
        return this;
    }
    public CorporateCustomer updateIndustry(String industry){
        industry = (industry == null ? null : industry.trim());
        if(Objects.equals(this.industry, industry)){
            return this;
        }
        handleUpdate(INDUSTRY_PROPERTY, getIndustry(), industry);
        this.industry = industry;
        return this;
    }
    public CorporateCustomer updateEmployeeCount(Integer employeeCount){
        if(Objects.equals(this.employeeCount, employeeCount)){
            return this;
        }
        handleUpdate(EMPLOYEE_COUNT_PROPERTY, getEmployeeCount(), employeeCount);
        this.employeeCount = employeeCount;
        return this;
    }
    public CorporateCustomer updateBillingAddress(String billingAddress){
        billingAddress = (billingAddress == null ? null : billingAddress.trim());
        if(Objects.equals(this.billingAddress, billingAddress)){
            return this;
        }
        handleUpdate(BILLING_ADDRESS_PROPERTY, getBillingAddress(), billingAddress);
        this.billingAddress = billingAddress;
        return this;
    }
    public CorporateCustomer updateContactEmail(String contactEmail){
        contactEmail = (contactEmail == null ? null : contactEmail.trim());
        if(Objects.equals(this.contactEmail, contactEmail)){
            return this;
        }
        handleUpdate(CONTACT_EMAIL_PROPERTY, getContactEmail(), contactEmail);
        this.contactEmail = contactEmail;
        return this;
    }
    public CorporateCustomer updateContactPhone(String contactPhone){
        contactPhone = (contactPhone == null ? null : contactPhone.trim());
        if(Objects.equals(this.contactPhone, contactPhone)){
            return this;
        }
        handleUpdate(CONTACT_PHONE_PROPERTY, getContactPhone(), contactPhone);
        this.contactPhone = contactPhone;
        return this;
    }
    public CorporateCustomer updateCustomerType(String customerType){
        customerType = (customerType == null ? null : customerType.trim());
        if(Objects.equals(this.customerType, customerType)){
            return this;
        }
        handleUpdate(CUSTOMER_TYPE_PROPERTY, getCustomerType(), customerType);
        this.customerType = customerType;
        return this;
    }
    public CorporateCustomer updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public CorporateCustomer updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }
    public CorporateCustomer addCustomerContact(CustomerContact customerContact){
        if (customerContact == null){
            return this;
        }

        if(null == this.customerContactList){
            this.customerContactList = new SmartList<>();
        }

        this.customerContactList.add(customerContact);
        customerContact.cacheRelation(CustomerContact.CORPORATE_CUSTOMER_PROPERTY, this);
        return this;
    }
    public CorporateCustomer addServiceQuote(ServiceQuote serviceQuote){
        if (serviceQuote == null){
            return this;
        }

        if(null == this.serviceQuoteList){
            this.serviceQuoteList = new SmartList<>();
        }

        this.serviceQuoteList.add(serviceQuote);
        serviceQuote.cacheRelation(ServiceQuote.CORPORATE_CUSTOMER_PROPERTY, this);
        return this;
    }
    public CorporateCustomer addServiceContract(ServiceContract serviceContract){
        if (serviceContract == null){
            return this;
        }

        if(null == this.serviceContractList){
            this.serviceContractList = new SmartList<>();
        }

        this.serviceContractList.add(serviceContract);
        serviceContract.cacheRelation(ServiceContract.CORPORATE_CUSTOMER_PROPERTY, this);
        return this;
    }

    public static CorporateCustomer refer(Long id){
        CorporateCustomer refer = new CorporateCustomer();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public CorporateCustomer comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<CorporateCustomer> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "registrationNumber": this.registrationNumber = (value == null ? null : ((String)value).trim()); break;

            case "industry": this.industry = (value == null ? null : ((String)value).trim()); break;

            case "employeeCount": this.employeeCount = (Integer) value; break;

            case "billingAddress": this.billingAddress = (value == null ? null : ((String)value).trim()); break;

            case "contactEmail": this.contactEmail = (value == null ? null : ((String)value).trim()); break;

            case "contactPhone": this.contactPhone = (value == null ? null : ((String)value).trim()); break;

            case "customerType": this.customerType = (value == null ? null : ((String)value).trim()); break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            case "customerContactList": this.customerContactList = (SmartList<CustomerContact>) value; break;
            case "serviceQuoteList": this.serviceQuoteList = (SmartList<ServiceQuote>) value; break;
            case "serviceContractList": this.serviceContractList = (SmartList<ServiceContract>) value; break;
            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "registrationNumber": return this.registrationNumber;
            case "industry": return this.industry;
            case "employeeCount": return this.employeeCount;
            case "billingAddress": return this.billingAddress;
            case "contactEmail": return this.contactEmail;
            case "contactPhone": return this.contactPhone;
            case "customerType": return this.customerType;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            case "customerContactList": return this.customerContactList;
            case "serviceQuoteList": return this.serviceQuoteList;
            case "serviceContractList": return this.serviceContractList;
            default: return super.__internalGet(property);
        }
    }

}