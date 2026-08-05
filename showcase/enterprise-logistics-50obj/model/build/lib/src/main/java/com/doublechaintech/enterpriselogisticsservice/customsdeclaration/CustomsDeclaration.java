package com.doublechaintech.enterpriselogisticsservice.customsdeclaration;

import io.teaql.core.Audited;
import io.teaql.core.BaseEntity;
import io.teaql.core.EntityStatus;
import io.teaql.core.FrameworkInternal;
import io.teaql.core.RemoteInput;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class CustomsDeclaration extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "CustomsDeclaration";

    public static final String DECLARATION_NUMBER_PROPERTY = "declarationNumber";
    public static final String PORT_OF_ENTRY_PROPERTY = "portOfEntry";
    public static final String COUNTRY_OF_ORIGIN_PROPERTY = "countryOfOrigin";
    public static final String DECLARED_VALUE_PROPERTY = "declaredValue";
    public static final String STATUS_PROPERTY = "status";
    public static final String CLEARANCE_DATE_PROPERTY = "clearanceDate";
    public static final String CREATED_TIME_PROPERTY = "createdTime";
    public static final String UPDATED_TIME_PROPERTY = "updatedTime";
    private String declarationNumber;
    private String portOfEntry;
    private String countryOfOrigin;
    private BigDecimal declaredValue;
    private String status;
    private LocalDate clearanceDate;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public String getDeclarationNumber(){
        return this.declarationNumber;
    }
    public String getPortOfEntry(){
        return this.portOfEntry;
    }
    public String getCountryOfOrigin(){
        return this.countryOfOrigin;
    }
    public BigDecimal getDeclaredValue(){
        return this.declaredValue;
    }
    public String getStatus(){
        return this.status;
    }
    public LocalDate getClearanceDate(){
        return this.clearanceDate;
    }
    public LocalDateTime getCreatedTime(){
        return this.createdTime;
    }
    public LocalDateTime getUpdatedTime(){
        return this.updatedTime;
    }
    public CustomsDeclaration updateDeclarationNumber(String declarationNumber){
        declarationNumber = (declarationNumber == null ? null : declarationNumber.trim());
        if(Objects.equals(this.declarationNumber, declarationNumber)){
            return this;
        }
        handleUpdate(DECLARATION_NUMBER_PROPERTY, getDeclarationNumber(), declarationNumber);
        this.declarationNumber = declarationNumber;
        return this;
    }
    public CustomsDeclaration updatePortOfEntry(String portOfEntry){
        portOfEntry = (portOfEntry == null ? null : portOfEntry.trim());
        if(Objects.equals(this.portOfEntry, portOfEntry)){
            return this;
        }
        handleUpdate(PORT_OF_ENTRY_PROPERTY, getPortOfEntry(), portOfEntry);
        this.portOfEntry = portOfEntry;
        return this;
    }
    public CustomsDeclaration updateCountryOfOrigin(String countryOfOrigin){
        countryOfOrigin = (countryOfOrigin == null ? null : countryOfOrigin.trim());
        if(Objects.equals(this.countryOfOrigin, countryOfOrigin)){
            return this;
        }
        handleUpdate(COUNTRY_OF_ORIGIN_PROPERTY, getCountryOfOrigin(), countryOfOrigin);
        this.countryOfOrigin = countryOfOrigin;
        return this;
    }
    public CustomsDeclaration updateDeclaredValue(BigDecimal declaredValue){
        if(Objects.equals(this.declaredValue, declaredValue)){
            return this;
        }
        handleUpdate(DECLARED_VALUE_PROPERTY, getDeclaredValue(), declaredValue);
        this.declaredValue = declaredValue;
        return this;
    }
    public CustomsDeclaration updateStatus(String status){
        status = (status == null ? null : status.trim());
        if(Objects.equals(this.status, status)){
            return this;
        }
        handleUpdate(STATUS_PROPERTY, getStatus(), status);
        this.status = status;
        return this;
    }
    public CustomsDeclaration updateClearanceDate(LocalDate clearanceDate){
        if(Objects.equals(this.clearanceDate, clearanceDate)){
            return this;
        }
        handleUpdate(CLEARANCE_DATE_PROPERTY, getClearanceDate(), clearanceDate);
        this.clearanceDate = clearanceDate;
        return this;
    }
    public CustomsDeclaration updateCreatedTime(LocalDateTime createdTime){
        if(Objects.equals(this.createdTime, createdTime)){
            return this;
        }
        handleUpdate(CREATED_TIME_PROPERTY, getCreatedTime(), createdTime);
        this.createdTime = createdTime;
        return this;
    }
    public CustomsDeclaration updateUpdatedTime(LocalDateTime updatedTime){
        if(Objects.equals(this.updatedTime, updatedTime)){
            return this;
        }
        handleUpdate(UPDATED_TIME_PROPERTY, getUpdatedTime(), updatedTime);
        this.updatedTime = updatedTime;
        return this;
    }

    public static CustomsDeclaration refer(Long id){
        CustomsDeclaration refer = new CustomsDeclaration();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public CustomsDeclaration comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<CustomsDeclaration> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "declarationNumber": this.declarationNumber = (value == null ? null : ((String)value).trim()); break;

            case "portOfEntry": this.portOfEntry = (value == null ? null : ((String)value).trim()); break;

            case "countryOfOrigin": this.countryOfOrigin = (value == null ? null : ((String)value).trim()); break;

            case "declaredValue": this.declaredValue = (BigDecimal) value; break;

            case "status": this.status = (value == null ? null : ((String)value).trim()); break;

            case "clearanceDate": this.clearanceDate = (LocalDate) value; break;

            case "createdTime": this.createdTime = (LocalDateTime) value; break;

            case "updatedTime": this.updatedTime = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "declarationNumber": return this.declarationNumber;
            case "portOfEntry": return this.portOfEntry;
            case "countryOfOrigin": return this.countryOfOrigin;
            case "declaredValue": return this.declaredValue;
            case "status": return this.status;
            case "clearanceDate": return this.clearanceDate;
            case "createdTime": return this.createdTime;
            case "updatedTime": return this.updatedTime;
            default: return super.__internalGet(property);
        }
    }

}