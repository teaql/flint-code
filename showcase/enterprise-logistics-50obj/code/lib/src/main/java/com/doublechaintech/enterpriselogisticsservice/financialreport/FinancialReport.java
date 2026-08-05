package com.doublechaintech.enterpriselogisticsservice.financialreport;

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
public class FinancialReport extends BaseEntity implements RemoteInput {
    public static String INTERNAL_TYPE = "FinancialReport";

    public static final String NAME_PROPERTY = "name";
    public static final String CODE_PROPERTY = "code";
    public static final String TOTAL_REVENUE_PROPERTY = "totalRevenue";
    public static final String TOTAL_EXPENSES_PROPERTY = "totalExpenses";
    public static final String PERIOD_START_PROPERTY = "periodStart";
    public static final String PERIOD_END_PROPERTY = "periodEnd";
    public static final String CREATED_AT_PROPERTY = "createdAt";
    public static final String UPDATED_AT_PROPERTY = "updatedAt";
    private String name;
    private String code;
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getName(){
        return this.name;
    }
    public String getCode(){
        return this.code;
    }
    public BigDecimal getTotalRevenue(){
        return this.totalRevenue;
    }
    public BigDecimal getTotalExpenses(){
        return this.totalExpenses;
    }
    public LocalDate getPeriodStart(){
        return this.periodStart;
    }
    public LocalDate getPeriodEnd(){
        return this.periodEnd;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }
    public FinancialReport updateName(String name){
        name = (name == null ? null : name.trim());
        if(Objects.equals(this.name, name)){
            return this;
        }
        handleUpdate(NAME_PROPERTY, getName(), name);
        this.name = name;
        return this;
    }
    public FinancialReport updateCode(String code){
        code = (code == null ? null : code.trim());
        if(Objects.equals(this.code, code)){
            return this;
        }
        handleUpdate(CODE_PROPERTY, getCode(), code);
        this.code = code;
        return this;
    }
    public FinancialReport updateTotalRevenue(BigDecimal totalRevenue){
        if(Objects.equals(this.totalRevenue, totalRevenue)){
            return this;
        }
        handleUpdate(TOTAL_REVENUE_PROPERTY, getTotalRevenue(), totalRevenue);
        this.totalRevenue = totalRevenue;
        return this;
    }
    public FinancialReport updateTotalExpenses(BigDecimal totalExpenses){
        if(Objects.equals(this.totalExpenses, totalExpenses)){
            return this;
        }
        handleUpdate(TOTAL_EXPENSES_PROPERTY, getTotalExpenses(), totalExpenses);
        this.totalExpenses = totalExpenses;
        return this;
    }
    public FinancialReport updatePeriodStart(LocalDate periodStart){
        if(Objects.equals(this.periodStart, periodStart)){
            return this;
        }
        handleUpdate(PERIOD_START_PROPERTY, getPeriodStart(), periodStart);
        this.periodStart = periodStart;
        return this;
    }
    public FinancialReport updatePeriodEnd(LocalDate periodEnd){
        if(Objects.equals(this.periodEnd, periodEnd)){
            return this;
        }
        handleUpdate(PERIOD_END_PROPERTY, getPeriodEnd(), periodEnd);
        this.periodEnd = periodEnd;
        return this;
    }
    public FinancialReport updateCreatedAt(LocalDateTime createdAt){
        if(Objects.equals(this.createdAt, createdAt)){
            return this;
        }
        handleUpdate(CREATED_AT_PROPERTY, getCreatedAt(), createdAt);
        this.createdAt = createdAt;
        return this;
    }
    public FinancialReport updateUpdatedAt(LocalDateTime updatedAt){
        if(Objects.equals(this.updatedAt, updatedAt)){
            return this;
        }
        handleUpdate(UPDATED_AT_PROPERTY, getUpdatedAt(), updatedAt);
        this.updatedAt = updatedAt;
        return this;
    }

    public static FinancialReport refer(Long id){
        FinancialReport refer = new FinancialReport();
        refer.__internalSet("id", id);
        refer.set$status(EntityStatus.REFER);
        return refer;
    }
    @Override
    public String typeName(){
        return INTERNAL_TYPE;
    }

    public FinancialReport comment(String comment){
        this.setComment(comment);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Audited<FinancialReport> auditAs(String action) {
        return super.auditAs(action);
    }

    // ===== Framework Internal: generated switch dispatch =====
    @Override
    @FrameworkInternal
    public void __internalSet(String property, Object value) {
        switch (property) {
            case "name": this.name = (value == null ? null : ((String)value).trim()); break;

            case "code": this.code = (value == null ? null : ((String)value).trim()); break;

            case "totalRevenue": this.totalRevenue = (BigDecimal) value; break;

            case "totalExpenses": this.totalExpenses = (BigDecimal) value; break;

            case "periodStart": this.periodStart = (LocalDate) value; break;

            case "periodEnd": this.periodEnd = (LocalDate) value; break;

            case "createdAt": this.createdAt = (LocalDateTime) value; break;

            case "updatedAt": this.updatedAt = (LocalDateTime) value; break;

            default: super.__internalSet(property, value);
        }
    }

    @Override
    @FrameworkInternal
    public Object __internalGet(String property) {
        switch (property) {
            case "name": return this.name;
            case "code": return this.code;
            case "totalRevenue": return this.totalRevenue;
            case "totalExpenses": return this.totalExpenses;
            case "periodStart": return this.periodStart;
            case "periodEnd": return this.periodEnd;
            case "createdAt": return this.createdAt;
            case "updatedAt": return this.updatedAt;
            default: return super.__internalGet(property);
        }
    }

}